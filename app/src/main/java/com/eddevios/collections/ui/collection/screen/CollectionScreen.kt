package com.eddevios.collections.ui.collection.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.eddevios.collections.ads.ShowInterstitialIfNeeded
import com.eddevios.collections.ui.collection.viewmodel.CollectionViewModel
import com.eddevios.collections.data.mapper.toCollectionItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable
import com.eddevios.collections.R
import com.eddevios.collections.ads.AdMobBanner
import androidx.compose.foundation.layout.Box
import com.eddevios.collections.BuildConfig
import com.eddevios.collections.utils.EmptyStateView
import com.eddevios.collections.utils.ConfirmDeleteDialog
import com.eddevios.collections.ui.collection.components.CollectionItem
import androidx.compose.material.icons.filled.Edit
import org.burnoutcrew.reorderable.ReorderableItem
import com.eddevios.collections.features.common.components.ReusableSearchBar
import androidx.hilt.navigation.compose.hiltViewModel
import com.eddevios.collections.ui.collection.components.LargeImageCollectionItem
import com.eddevios.collections.utils.HapticController
import androidx.compose.foundation.layout.navigationBarsPadding

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CollectionScreen(
    navController: NavHostController,
    viewModel: CollectionViewModel = hiltViewModel()
) {

    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredCollections by viewModel.filteredCollections.collectAsState(emptyList())

    // Crea un FocusRequester
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current.applicationContext
    val packageName = context.packageName
    val collections by viewModel.collections.collectAsState(emptyList())
    ShowInterstitialIfNeeded()
    // Estado para habilitar el modo de edición
    var isEditing by remember { mutableStateOf(false) }

    // Estado para mostrar el cuadro de diálogo de confirmación de eliminación
    var collectionToDelete by remember { mutableStateOf<CollectionItem?>(null) }

    val reorderableState = rememberReorderableLazyListState(
        onMove = { from, to ->
            try {
                viewModel.moveCollection(from.index, to.index)
            } catch (e: Exception) {
                Log.e("CollectionScreen", "Error reordering collection", e)
            }
        }
    )

    Scaffold(
        bottomBar = {
            Column(modifier = Modifier.navigationBarsPadding()) {
                AdMobBanner()
        } },
        topBar = {
            Column {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)/* + " v."+ BuildConfig.VERSION_NAME*/) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface,
                        navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                        actionIconContentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    actions = {
                        // Botón de edición
                        IconButton(onClick = {
                            isEditing = !isEditing }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = if (isEditing) "Salir del modo edición" else "Activar modo edición"
                            )
                        }
                        // Botón para agregar nueva colección
                        IconButton(onClick = {
                            navController.navigate("create_collection") }) {
                            Icon(Icons.Default.Add, contentDescription = "Agregar colección")
                        }
                    },
                )
                Surface(
                    color = MaterialTheme.colorScheme.surface
                ) {
                    ReusableSearchBar(
                        query = searchQuery,
                        onQueryChange = viewModel::onQueryChanged,
                        onSearch = viewModel::onSearchTriggered,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .heightIn(min = 40.dp),
                        placeholder = stringResource(R.string.search_placeholder)
                    )
                }
            }
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                // 1) No hay colecciones y no se está buscando nada
                collections.isEmpty() && searchQuery.isBlank() -> {
                    EmptyStateView(
                        primaryText = stringResource(R.string.no_collections_title),
                        secondaryText = stringResource(R.string.no_collections_subtitle),
                        buttonText = stringResource(R.string.create_collection),
                        onButtonClick = { navController.navigate("create_collection") }
                    )
                }
                // 2) Hay búsqueda pero no hay resultados
                filteredCollections.isEmpty() && searchQuery.isNotBlank() -> {
                    EmptyStateView(
                        primaryText   = stringResource(R.string.not_result, searchQuery),
                        secondaryText = stringResource(R.string.no_collections_title),
                        buttonText    = stringResource(R.string.clear_search),
                        onButtonClick = { viewModel.clearSearch() }
                    )
                }

                // 3) Normal list
                else -> {
                    LazyColumn(
                        state = reorderableState.listState,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                            .reorderable(reorderableState)
                            .detectReorderAfterLongPress(reorderableState)
                    ) {
                        items(filteredCollections, key = { it.id }) { entity ->
                            // Envolver CollectionItemView con ReorderableItem
                            ReorderableItem(
                                reorderableState = reorderableState,
                                key = entity.id
                            ) { isDragging ->
                                val uiItem = entity.toCollectionItem()
                                LargeImageCollectionItem(
                                    collection = uiItem,
                                    isEditing = isEditing,
                                    onClick = {
                                        try {
                                            navController.navigate("category/${uiItem.id}")
                                        } catch (e: Exception) {
                                            Log.e("CollectionScreen", "Error navigating to category", e)
                                        }
                                    },
                                    onEdit = {
                                        try {
                                            navController.navigate("edit_collection/${uiItem.id}")
                                        } catch (e: Exception) {
                                            Log.e("CollectionScreen", "Error navigating to edit collection", e)
                                        }
                                    },
                                    onDelete = { collectionToDelete = uiItem },
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .animateItemPlacement()
                                )
                            }
                        }
                    }

                    collectionToDelete?.let { uiItem ->
                        ConfirmDeleteDialog(
                            title     = stringResource(R.string.delete_collection),
                            message = stringResource(R.string.wish_delete, uiItem.title),
                            onConfirm = {
                                HapticController.oneShot(context, 70L)
                                viewModel.deleteCollectionById(uiItem.id)
                                collectionToDelete = null
                            },
                            onDismiss = {
                                collectionToDelete = null
                            }
                        )
                    }
                }
            }
        }
    }
}