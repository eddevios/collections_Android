package com.eddevios.collections.ui.category.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eddevios.collections.data.local.entity.CategoryEntity
import com.eddevios.collections.data.local.entity.CollectionEntity
import com.eddevios.collections.utils.CrashlyticsLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val dao: com.eddevios.collections.data.local.dao.CategoryDao,
    private val collectionDao: com.eddevios.collections.data.local.dao.CollectionDao,
    private val crashlyticsLogger: CrashlyticsLogger
) : ViewModel() {

    // 1) Título dinámico de la colección padre
    private val _collectionTitle = MutableStateFlow<String?>(null)
    val collectionTitle = _collectionTitle.asStateFlow()

    private val _categories = MutableStateFlow<List<CategoryEntity>>(emptyList())
    val categories = _categories.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // --- CÓDIGO NUEVO AÑADIDO ---
    // Estado para la categoría seleccionada en la pantalla de edición
    private val _selectedCategory = MutableStateFlow<CategoryEntity?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()

    // Estado activo del SearchBar
    private val _searchActive = MutableStateFlow(false)
    val searchActive: StateFlow<Boolean> = _searchActive.asStateFlow()

    fun onQueryChanged(query: String) {
        _searchQuery.value = query
    }

    // --- LOAD CATEGORY& BY ID ---
    fun loadCategoryById(categoryId: Int) {
        viewModelScope.launch {
            try {
                // Envolvemos la llamada a la base de datos en un try-catch
                _selectedCategory.value = withContext(Dispatchers.IO) {
                    dao.getCategoryById(categoryId)
                }
            } catch (e: Exception) {
                // Si algo falla, lo registramos
                crashlyticsLogger.setCustomKey("action", "loadCategoryById")
                crashlyticsLogger.setCustomKey("category_id", categoryId.toString())
                crashlyticsLogger.logNonFatal(e)

                // Y mantenemos el log local para depuración
                Log.e("ViewModelError", "Error al cargar la categoría por ID: $categoryId", e)
            }
        }
    }

    val filteredCategories: StateFlow<List<CategoryEntity>> = _categories
        .combine(_searchQuery) { list, query ->
            if (query.isBlank()) list
            else {
                // Busca coincidencias exactas (ignora mayúsculas/minúsculas)
                val exact = list.filter {
                    it.title.equals(query, ignoreCase = true) ||
                            (it.subtitle?.equals(query, ignoreCase = true) == true)
                }
                if (exact.isNotEmpty()) {
                    exact
                } else {
                    // Si no hay exactos, busca los que contienen la query
                    list.filter {
                        it.title.contains(query, ignoreCase = true) ||
                                (it.subtitle?.contains(query, ignoreCase = true) == true)
                    }
                }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    /**
     * Carga tanto el título de la colección como sus categorías hijas.
     */
    fun loadCategories(collectionId: Int) {
        if (collectionId <= 0) return

        viewModelScope.launch {
            try {
                val collection: CollectionEntity? = withContext(Dispatchers.IO) {
                    collectionDao.getCollectionById(collectionId)
                }
                _collectionTitle.value = collection?.title

                dao.getCategoriesByCollectionId(collectionId).collect {
                    _categories.value = it
                }
            } catch (e: Exception) {
                crashlyticsLogger.setCustomKey("action", "loadCategories")
                crashlyticsLogger.setCustomKey("collection_id", collectionId.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al cargar las categorías para la colección", e)
            }
        }
    }

    fun addCategory(
        title: String,
        subtitle: String,
        imageUri: Uri?,
        collectionId: Int
    ) {
        viewModelScope.launch {
            try {
                val collectionExists = withContext(Dispatchers.IO) {
                    collectionDao.getCollectionById(collectionId) != null
                }
                if (!collectionExists) {
                    // Esto es un error de estado, no una excepción, pero es valioso registrarlo.
                    crashlyticsLogger.setCustomKey("error_type", "add_to_non_existent_collection")
                    crashlyticsLogger.logNonFatal(IllegalStateException("Intento de añadir categoría a una colección inexistente: $collectionId"))
                    return@launch
                }

                val newCategory = CategoryEntity(
                    title = title,
                    subtitle = subtitle,
                    imageUri = imageUri?.toString(),
                    collectionId = collectionId,
                    order = _categories.value.size,
                    createdAt = System.currentTimeMillis(),
                    lastModifiedAt = System.currentTimeMillis()
                )
                dao.insertCategory(newCategory)
            } catch (e: Exception) {
                crashlyticsLogger.setCustomKey("action", "addCategory")
                crashlyticsLogger.setCustomKey("collection_id", collectionId.toString())
                crashlyticsLogger.setCustomKey("title_length", title.length.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al añadir la categoría", e)
            }
        }
    }

    fun updateCategory(id: Int, title: String, subtitle: String, imageUri: Uri?) {
        viewModelScope.launch {
            try {
                val existing = withContext(Dispatchers.IO) { dao.getCategoryById(id) }
                    ?: run {
                        crashlyticsLogger.setCustomKey("error_type", "update_non_existent_category")
                        crashlyticsLogger.setCustomKey("category_id", id.toString())
                        crashlyticsLogger.logNonFatal(IllegalStateException("Intento de actualizar una categoría que no existe: $id"))
                        return@launch
                    }

                val updated = existing.copy(
                    title = title,
                    subtitle = subtitle,
                    imageUri = imageUri?.toString(),
                    lastModifiedAt = System.currentTimeMillis()
                )
                dao.updateCategory(updated)
            } catch (e: Exception) {
                crashlyticsLogger.setCustomKey("action", "updateCategory")
                crashlyticsLogger.setCustomKey("category_id", id.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al actualizar la categoría", e)
            }
        }
    }

    fun moveCategory(fromIndex: Int, toIndex: Int) {
        viewModelScope.launch {
            try {
                val updated = _categories.value.toMutableList().apply {
                    val item = removeAt(fromIndex)
                    add(toIndex, item)
                }
                saveNewOrder(updated)
            } catch (e: Exception) { // Captura IndexOutOfBoundsException y errores de DB
                crashlyticsLogger.setCustomKey("action", "moveCategory")
                crashlyticsLogger.setCustomKey("from_index", fromIndex.toString())
                crashlyticsLogger.setCustomKey("to_index", toIndex.toString())
                crashlyticsLogger.setCustomKey("list_size", _categories.value.size.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al mover la categoría", e)
            }
        }
    }

    fun deleteCategoryById(id: Int) {
        viewModelScope.launch {
            try {
                dao.deleteCategoryById(id)
                // Actualiza la lista local solo si el borrado tuvo éxito
                _categories.value = _categories.value.filter { it.id != id }
            } catch (e: Exception) {
                crashlyticsLogger.setCustomKey("action", "deleteCategory")
                crashlyticsLogger.setCustomKey("category_id", id.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al borrar la categoría", e)
            }
        }
    }

    fun saveNewOrder(updatedList: List<CategoryEntity>) {
        viewModelScope.launch {
            try {
                // Realiza todas las escrituras a la DB
                updatedList.forEachIndexed { idx, cat ->
                    dao.updateCategoryOrder(cat.id, idx)
                }
                // Actualiza la UI una sola vez al final
                _categories.value = updatedList
            } catch (e: Exception) {
                crashlyticsLogger.setCustomKey("action", "saveNewOrder_Category")
                crashlyticsLogger.setCustomKey("list_size", updatedList.size.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al guardar el nuevo orden de categorías", e)
            }
        }
    }

    fun onSearchTriggered(query: String) {
        // Podrías hacer algo aquí si fuera necesario, como cerrar el teclado.
        Log.d("ViewModel", "Search triggered for: $query")
        onSearchActiveChanged(false) // Opcional: cerrar el searchbar al pulsar buscar
    }

    fun onSearchActiveChanged(isActive: Boolean) {
        _searchActive.value = isActive
    }

    fun clearSearch() {
        _searchQuery.value = ""
    }
}