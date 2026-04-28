package com.eddevios.collections.ui.collectable.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.eddevios.collections.data.mapper.toCollectableItem
import com.eddevios.collections.ui.collectable.viewmodel.CollectableViewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import com.eddevios.collections.ui.collectable.components.CollectableGridItem
import androidx.compose.ui.res.stringResource
import com.eddevios.collections.R
import com.eddevios.collections.ads.AdMobBanner
import com.eddevios.collections.ads.ShowInterstitialIfNeeded
import com.eddevios.collections.utils.EmptyStateView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import com.eddevios.collections.features.common.model.FilterOption
import com.eddevios.collections.features.common.components.ReusableSearchBar
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CollectableScreen(
    navController: NavHostController,
    viewModel: CollectableViewModel = hiltViewModel(),
    categoryId: Int
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchActive by viewModel.searchActive.collectAsState()
    val selectedFilterIndex by viewModel.selectedFilterIndex.collectAsState()
    val collectables by viewModel.collectables.collectAsState()
    val categoryTitle by viewModel.categoryTitle.collectAsState()
    var isEditing by remember { mutableStateOf(false) }

    ShowInterstitialIfNeeded()

    LaunchedEffect(categoryId) {
        if (categoryId > 0) {
            viewModel.loadCollectablesAndTitle(categoryId)
        } else {
            // Considera notificar el error a Crashlytics aquí también
            Log.e("CollectableScreen", "Received invalid categoryId: $categoryId")
        }
    }

    val gridState = rememberLazyGridState()

    Scaffold(
        bottomBar = { Column(modifier = Modifier.navigationBarsPadding()) {
            AdMobBanner()
        } },
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            categoryTitle ?: stringResource(R.string.loading)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver"
                            )
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
                            navController.navigate("create_collectable/$categoryId")
                        }) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Agregar coleccionable"
                            )
                        }
                    }
                )
                Surface(
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 0.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Barra de búsqueda redondeada
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

                        // Filtros debajo, integrados
                        SingleChoiceSegmentedButtonRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        ) {
                            val filterOptions = listOf(
                                FilterOption("", Icons.Default.SortByAlpha),
                                FilterOption("", Icons.Default.CalendarMonth),
                                FilterOption("", Icons.Default.Favorite)
                            )

                            filterOptions.forEachIndexed { index, option ->
                                SegmentedButton(
                                    shape = SegmentedButtonDefaults.itemShape(index, filterOptions.size),
                                    onClick = { viewModel.onFilterSelected(index) },
                                    selected = index == selectedFilterIndex,
                                    icon = {
                                        Icon(
                                            imageVector = option.icon,
                                            contentDescription = option.label,
                                            modifier = Modifier.size(SegmentedButtonDefaults.IconSize)
                                        )
                                    },
                                    label = { Text(option.label) }
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                // 1) Sin coleccionables ni búsqueda
                collectables.isEmpty() && searchQuery.isBlank() -> {
                    EmptyStateView(
                        primaryText   = stringResource(R.string.no_collectables_title),
                        secondaryText = stringResource(R.string.no_collectables_subtitle),
                        buttonText    = stringResource(R.string.create_collectable),
                        onButtonClick = { navController.navigate("create_collectable/$categoryId") }
                    )
                }
                // 2) Sin resultados tras buscar
                collectables.isEmpty() -> {
                    EmptyStateView(
                        primaryText   = stringResource(R.string.not_result, searchQuery),
                        secondaryText = stringResource(R.string.no_collectables_title),
                        buttonText    = stringResource(R.string.clear_search),
                        onButtonClick = { viewModel.clearSearch() }
                    )
                }
                // 3) Grid normal
                else -> {
                    LazyVerticalGrid(
                        columns            = GridCells.Adaptive(120.dp),
                        state              = gridState,
                        modifier           = Modifier.fillMaxSize(),
                        contentPadding     = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(collectables, key = { it.id }) { item ->
                            val elev = 2.dp
                            CollectableGridItem(
                                collectable = item.toCollectableItem(),
                                isEditing   = isEditing,
                                imageElevation = elev,
                                modifier    = Modifier
                                    .animateItemPlacement()
                                    .clickable {
                                        navController.navigate("collectable_detail/${item.id}")
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}