package com.eddevios.collections.ui.collectable.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eddevios.collections.data.local.entity.CollectableEntity
import com.eddevios.collections.utils.CrashlyticsLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectableViewModel @Inject constructor(
    private val collectableDao: com.eddevios.collections.data.local.dao.CollectableDao,
    private val categoryDao: com.eddevios.collections.data.local.dao.CategoryDao,
    private val crashlyticsLogger: CrashlyticsLogger
) : ViewModel() {

    private val _collectables = MutableStateFlow<List<CollectableEntity>>(emptyList())

    // --- ESTADO PARA LA UI ---
    // Query de búsqueda
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Estado activo del SearchBar
    private val _searchActive = MutableStateFlow(false)
    val searchActive: StateFlow<Boolean> = _searchActive.asStateFlow()

    // Índice del filtro seleccionado (0: Alfa, 1: Fecha, 2: Fav)
    private val _selectedFilterIndex = MutableStateFlow(0)
    val selectedFilterIndex: StateFlow<Int> = _selectedFilterIndex.asStateFlow()

    // NUEVO: StateFlow para el título de la categoría
    private val _categoryTitle = MutableStateFlow<String?>(null)
    val categoryTitle: StateFlow<String?> = _categoryTitle.asStateFlow()

    // ① Reemplaza la var por un StateFlow
    private val _categoryId = MutableStateFlow(-1)
    val categoryId: StateFlow<Int> = _categoryId.asStateFlow()

    // ① Estado privado que guardará el collectable cargado
    private val _selectedCollectable = MutableStateFlow<CollectableEntity?>(null)
    // ② Estado público de solo lectura para la UI
    val selectedCollectable: StateFlow<CollectableEntity?> = _selectedCollectable.asStateFlow()

    // ② Simplifica loadCollectablesAndTitle
    fun loadCollectablesAndTitle(id: Int) {
        if (id <= 0 || _categoryId.value == id) return
        _categoryId.value = id

        viewModelScope.launch {
            try {
                _categoryTitle.value = withContext(Dispatchers.IO) {
                    categoryDao.getCategoryById(id)?.title
                }
            } catch (e: Exception) {
                crashlyticsLogger.setCustomKey("action", "loadCollectablesAndTitle_getCategory")
                crashlyticsLogger.setCustomKey("category_id", id.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al cargar el título de la categoría", e)
            }
        }
    }

    // ③ Nuevo flujo combinado
    val collectables: StateFlow<List<CollectableEntity>> = _categoryId
        .filter { it > 0 }                                       // solo IDs válidos
        .flatMapLatest { cid -> collectableDao.getCollectablesByCategory(cid) }
        .combine(searchQuery) { list, query ->
            if (query.isBlank()) list else list.filter {
                it.title.contains(query, true) ||
                        it.subtitle?.contains(query, true) == true ||
                        it.comments?.contains(query, true) == true
            }
        }
        .combine(selectedFilterIndex) { filtered, index ->
            when (index) {
                0 -> filtered.sortedBy { it.title.lowercase() }
                1 -> filtered.sortedByDescending { it.createdAt }
                2 -> filtered.sortedWith(compareByDescending<CollectableEntity> { it.isFavorite }
                    .thenBy { it.title.lowercase() })
                else -> filtered
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun loadCollectableById(id: Int) {
        viewModelScope.launch { // 1. La corrutina empieza en el hilo Principal (por defecto)
            try {
                val collectable = withContext(Dispatchers.IO) { // 2. Cambia TEMPORALMENTE al hilo de fondo
                    // Esta línea es la única que se ejecuta en Dispatchers.IO
                    collectableDao.getCollectableById(id)
                }
                // 3. La corrutina vuelve AUTOMÁTICAMENTE al hilo Principal
                // Esta actualización de estado es segura
                _selectedCollectable.value = collectable
            } catch (e: Exception) {
                // El logging también se ejecuta de forma segura en el hilo Principal
                crashlyticsLogger.setCustomKey("action", "loadCollectableById")
                crashlyticsLogger.setCustomKey("collectable_id", id.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al cargar el coleccionable por ID", e)
            }
        }
    }

    // --- FUNCIONES LLAMADAS DESDE LA UI ---
    fun onQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun onSearchActiveChanged(isActive: Boolean) {
        _searchActive.value = isActive
    }

    fun onFilterSelected(index: Int) {
        _selectedFilterIndex.value = index
    }

    fun onSearchTriggered(query: String) {
        // Podrías hacer algo aquí si fuera necesario, como cerrar el teclado.
        Log.d("ViewModel", "Search triggered for: $query")
        onSearchActiveChanged(false) // Opcional: cerrar el searchbar al pulsar buscar
    }

    fun addCollectable(
        title: String,
        subtitle: String?,
        imageUri: Uri?,
        comments: String?,
        isFavorite: Boolean,
        categoryId: Int
    ) {
        val cid = _categoryId.value
        if (cid <= 0) {
            Log.e("CollectableViewModel", "Cannot add collectable, invalid categoryId: $cid")
            return
        }
        viewModelScope.launch {
            try {
                val nextOrder = collectableDao.getMaxOrderForCategory(cid)?.plus(1) ?: 0
                val newCollectable = CollectableEntity(
                    title = title,
                    subtitle = subtitle,
                    imageUri = imageUri?.toString(),
                    comments = comments,
                    categoryId = cid,
                    order = nextOrder,
                    createdAt = System.currentTimeMillis(),
                    lastModifiedAt = System.currentTimeMillis(),
                    isFavorite = isFavorite
                )
                collectableDao.insert(newCollectable)
            } catch (e: Exception) {
                crashlyticsLogger.setCustomKey("action", "addCollectable")
                crashlyticsLogger.setCustomKey("category_id", cid.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al añadir coleccionable", e)
            }
        }
    }

    /** Actualiza un collectable existente en la base de datos */
    fun updateCollectable(updated: CollectableEntity) {
        viewModelScope.launch {
            try {
                collectableDao.update(updated)
            } catch (e: Exception) {
                crashlyticsLogger.setCustomKey("action", "updateCollectable")
                crashlyticsLogger.setCustomKey("collectable_id", updated.id.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al actualizar coleccionable", e)
            }
        }
    }

    fun deleteCollectableById(id: Int) {
        viewModelScope.launch {
            try {
                collectableDao.deleteCollectableById(id)
            } catch (e: Exception) {
                crashlyticsLogger.setCustomKey("action", "deleteCollectableById")
                crashlyticsLogger.setCustomKey("collectable_id", id.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al borrar coleccionable", e)
            }
        }
    }

    fun moveCollectable(fromIndex: Int, toIndex: Int) {
        viewModelScope.launch {
            try {
                val currentList = collectables.value // Usa el StateFlow público
                if (fromIndex !in currentList.indices || toIndex !in currentList.indices) {
                    throw IndexOutOfBoundsException("Índices inválidos para mover: from=$fromIndex, to=$toIndex, size=${currentList.size}")
                }
                val updatedList = currentList.toMutableList().apply {
                    val movedItem = removeAt(fromIndex)
                    add(toIndex, movedItem)
                }
                saveNewOrder(updatedList)
            } catch (e: Exception) { // Captura IndexOutOfBounds y errores de DB
                crashlyticsLogger.setCustomKey("action", "moveCollectable")
                crashlyticsLogger.setCustomKey("from_index", fromIndex.toString())
                crashlyticsLogger.setCustomKey("to_index", toIndex.toString())
                crashlyticsLogger.setCustomKey("list_size", collectables.value.size.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al mover coleccionable", e)
            }
        }
    }

    private fun saveNewOrder(updatedList: List<CollectableEntity>) { // Tipo correcto
        viewModelScope.launch {
            try {
                // Se puede optimizar con una transacción en el DAO
                updatedList.forEachIndexed { index, collectable ->
                    if (collectable.order != index) {
                        collectableDao.updateCollectableOrder(collectable.id, index)
                    }
                }
            } catch (e: Exception) {
                crashlyticsLogger.setCustomKey("action", "saveNewOrder_Collectable")
                crashlyticsLogger.setCustomKey("list_size", updatedList.size.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al guardar el nuevo orden de coleccionables", e)
            }
        }
    }

    fun setSelectedCollectableForPreview(collectable: CollectableEntity) {
        _selectedCollectable.value = collectable
    }

    fun clearSearch() {
        _searchQuery.value = ""
    }
}
