package com.eddevios.collections.ui.category.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.eddevios.collections.data.mapper.toCategoryItem
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable
import com.eddevios.collections.ui.category.viewmodel.CategoryViewModel
import com.eddevios.collections.R
import com.eddevios.collections.ads.AdMobBanner
import com.eddevios.collections.ads.ShowInterstitialIfNeeded
import com.eddevios.collections.ui.category.components.CategoryItem
import com.eddevios.collections.ui.category.components.LargeImageCategoryItem
import com.eddevios.collections.utils.ConfirmDeleteDialog
import com.eddevios.collections.utils.EmptyStateView
import com.eddevios.collections.utils.HapticController
import com.eddevios.collections.features.common.components.ReusableSearchBar
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CategoryScreen(
    navController: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel(),
    collectionId: Int
) {
    var isEditing by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var categoryToDelete by remember { mutableStateOf<CategoryItem?>(null) }
    val categories by viewModel.categories.collectAsState(emptyList())
    val collectionTitle by viewModel.collectionTitle.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredCategories by viewModel.filteredCategories.collectAsState(emptyList())
    ShowInterstitialIfNeeded()
    val reorderableState = rememberReorderableLazyListState(
        onMove = { from, to ->
            // from.index y to.index son índices en filteredCategories
            val movedItemId = filteredCategories.getOrNull(from.index)?.id ?: return@rememberReorderableLazyListState
            val anchorId = filteredCategories.getOrNull(to.index)?.id

            // Busca los índices en la lista completa
            val originalFromIndex = categories.indexOfFirst { it.id == movedItemId }
            val finalToIndex = if (anchorId != null) {
                categories.indexOfFirst { it.id == anchorId }
            } else {
                categories.size - 1
            }
            if (originalFromIndex == -1 || finalToIndex == -1) return@rememberReorderableLazyListState

            viewModel.moveCategory(originalFromIndex, finalToIndex)
        }
    )

    LaunchedEffect(collectionId) { viewModel.loadCategories(collectionId) }

    Scaffold(
        bottomBar = { Column(modifier = Modifier.navigationBarsPadding()) {
            AdMobBanner()
        } },
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = collectionTitle
                                ?: stringResource(R.string.category)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface,
                        navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                        actionIconContentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    actions = {
                        IconButton(onClick = {
                            HapticController.oneShot(context, 70L)
                            isEditing = !isEditing }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = if (isEditing) "Salir del modo edición" else "Activar modo edición"
                                // tint ya debería estar bien por actionIconContentColor
                            )
                        }
                        IconButton(onClick = {
                            navController.navigate("create_category/$collectionId")
                        }) {
                            Icon(Icons.Default.Add, "Agregar categoría"
                            )
                        }
                    },
                )
                // Barra de búsqueda
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
                categories.isEmpty() && searchQuery.isBlank() -> {
                    EmptyStateView(
                        primaryText = stringResource(R.string.no_categories_title),
                        secondaryText = stringResource(R.string.no_categories_subtitle),
                        buttonText = stringResource(R.string.create_category),
                        onButtonClick = { navController.navigate("create_category/$collectionId") }
                    )
                }
                filteredCategories.isEmpty() && searchQuery.isNotBlank() -> {
                    EmptyStateView(
                        primaryText   = stringResource(R.string.not_result, searchQuery),
                        secondaryText = stringResource(R.string.no_categories_title),
                        buttonText    = stringResource(R.string.clear_search),
                        onButtonClick = { viewModel.clearSearch() }
                    )
                }
                else -> {
                    LazyColumn(
                        state = reorderableState.listState,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                            .reorderable(reorderableState)
                            .detectReorderAfterLongPress(reorderableState)
                    ) {

                        items(filteredCategories, key = { it.id }) { categoryEntity ->
                            ReorderableItem(reorderableState, key = categoryEntity.id) { isDragging ->
                                val uiItem = categoryEntity.toCategoryItem()
                                LargeImageCategoryItem(
                                    category = uiItem,
                                    isEditing = isEditing,
                                    onClick = { navController.navigate("collectable/${uiItem.id}") },
                                    onEdit = { navController.navigate("edit_category/${categoryEntity.id}") },
                                    onDelete = {
                                        HapticController.oneShot(context, 70L)
                                        categoryToDelete = uiItem
                                    },
                                    modifier = Modifier
                                        .padding(8.dp)
                                )
                            }
                        }
                    }

                    categoryToDelete?.let { uiItem ->
                        ConfirmDeleteDialog(
                            title     = stringResource(R.string.delete_category),
                            message = stringResource(R.string.wish_delete, uiItem.title),
                            onConfirm = {
                                viewModel.deleteCategoryById(uiItem.id)
                                categoryToDelete = null
                            },
                            onDismiss = {
                                categoryToDelete = null
                            }
                        )
                    }
                }
            }
        }
    }
}