package com.eddevios.collections.ui.collection.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.eddevios.collections.data.local.entity.CollectionEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import com.eddevios.collections.data.local.dao.CollectionDao
import com.eddevios.collections.utils.CrashlyticsLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val dao: CollectionDao,
    private val crashlyticsLogger: CrashlyticsLogger
) : ViewModel() {

    private val _collections = MutableStateFlow<List<CollectionEntity>>(emptyList())
    val collections = _collections.asStateFlow()
    // Estado activo del SearchBar
    private val _searchActive = MutableStateFlow(false)
    val searchActive: StateFlow<Boolean> = _searchActive.asStateFlow()
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun onQueryChanged(query: String) {
        _searchQuery.value = query
    }

    val filteredCollections: StateFlow<List<CollectionEntity>> = _collections
        .combine(_searchQuery) { list, query ->
            if (query.isBlank()) list
            else list.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.subtitle.contains(query, ignoreCase = true)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            try {
                dao.getAllCollections().collect { _collections.value = it }
            } catch (e: Exception) {
                crashlyticsLogger.setCustomKey("action", "init_load_collections")
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al cargar la colección inicial", e)
            }
        }
    }

    fun deleteCollectionById(id: Int) {
        viewModelScope.launch {
            try {
                dao.deleteCollectionById(id)
            } catch (e: Exception) {
                // 1. Añade contexto al reporte de error
                crashlyticsLogger.setCustomKey("action", "deleteCollection")
                crashlyticsLogger.setCustomKey("collection_id", id.toString())

                // 2. Registra la excepción en Crashlytics
                crashlyticsLogger.logNonFatal(e)

                // 3. (Opcional) Aún puedes mostrar un error al usuario o loguear localmente
                Log.e("ViewModelError", "Error al eliminar colección por ID: $id", e)
            }
        }
    }

    fun addCollection(title: String, subtitle: String, imageUri: Uri?, packageName: String) {
        viewModelScope.launch {
            try {
                val finalImageUri = imageUri?.toString()
                    ?: "android.resource://$packageName/drawable/default_image"
                if (title.isNotEmpty()) {
                    // Calcular el orden como el último índice disponible
                    val currentMaxOrder = _collections.value.maxOfOrNull { it.order } ?: -1
                    dao.insertCollection(
                        CollectionEntity(
                            title = title,
                            subtitle = subtitle,
                            imageUri = finalImageUri,
                            order = currentMaxOrder + 1,
                            createdAt = System.currentTimeMillis(),
                            lastModifiedAt = System.currentTimeMillis()
                        )
                    )
                } else {
                    Log.e("DEBUG", "Título vacío")
                    crashlyticsLogger.setCustomKey("validation_error", "Empty title on addCollection")
                }
            } catch (e: Exception) {
                // Aquí capturas errores de la base de datos u otros inesperados
                crashlyticsLogger.setCustomKey("action", "addCollection")
                crashlyticsLogger.setCustomKey("title_length", title.length.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al guardar colección", e)
            }
        }
    }

    fun updateCollection(
        id: Int,
        title: String,
        subtitle: String,
        imageUri: Uri?,
        packageName: String
    ) {
        viewModelScope.launch {
            try {
                val finalImageUri = imageUri?.toString()
                    ?: "android.resource://$packageName/drawable/default_image"

                val existing = _collections.value.firstOrNull { it.id == id }
                    ?: run { // Si no existe, es un estado inesperado. ¡Lo registramos!
                        crashlyticsLogger.setCustomKey("error_type", "update_non_existent")
                        crashlyticsLogger.setCustomKey("collection_id", id.toString())
                        crashlyticsLogger.logNonFatal(IllegalStateException("Intento de actualizar una colección que no existe en el estado local."))
                        return@launch
                    }

                val updated = existing.copy(
                    title = title,
                    subtitle = subtitle,
                    imageUri = finalImageUri,
                    lastModifiedAt = System.currentTimeMillis()
                )

                dao.updateCollection(updated)
            } catch (e: Exception) {
                crashlyticsLogger.setCustomKey("action", "updateCollection")
                crashlyticsLogger.setCustomKey("collection_id", id.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al actualizar la colección", e)
            }
        }
    }

    fun saveNewOrder(updatedList: List<CollectionEntity>) {
        viewModelScope.launch {
            try {
                // Es mucho mejor si esta operación se hace en una transacción en el DAO
                updatedList.forEachIndexed { index, collection ->
                    dao.updateCollectionOrder(collection.id, index)
                }
                // Actualiza la UI una sola vez, después de que todas las operaciones de DB tengan éxito
                _collections.value = updatedList
            } catch (e: Exception) {
                crashlyticsLogger.setCustomKey("action", "saveNewOrder")
                crashlyticsLogger.setCustomKey("list_size", updatedList.size.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al guardar el nuevo orden de colecciones", e)
            }
        }
    }

    fun moveCollection(fromIndex: Int, toIndex: Int) {
        viewModelScope.launch {
            try {
                val updatedList = _collections.value.toMutableList()
                val movedItem = updatedList.removeAt(fromIndex)
                updatedList.add(toIndex, movedItem)
                saveNewOrder(updatedList)
            } catch (e: Exception) { // Captura tanto IndexOutOfBoundsException como errores de DB
                crashlyticsLogger.setCustomKey("action", "moveCollection")
                crashlyticsLogger.setCustomKey("from_index", fromIndex.toString())
                crashlyticsLogger.setCustomKey("to_index", toIndex.toString())
                crashlyticsLogger.setCustomKey("list_size", _collections.value.size.toString())
                crashlyticsLogger.logNonFatal(e)
                Log.e("ViewModelError", "Error al mover la colección", e)
            }
        }
    }

    fun getMaxOrder(): Int? {
        return _collections.value.maxOfOrNull { it.order }
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