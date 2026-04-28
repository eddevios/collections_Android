package com.eddevios.collections.ui.navigation

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eddevios.collections.ui.collection.screen.CollectionScreen
import com.eddevios.collections.ui.collection.screen.CreateCollectionScreen
import com.eddevios.collections.ui.category.screen.CategoryScreen
import com.eddevios.collections.ui.category.screen.CreateCategoryScreen
import com.eddevios.collections.ui.collectable.screen.CollectableDetailScreen
import com.eddevios.collections.ui.collectable.screen.CollectableScreen
import com.eddevios.collections.ui.collectable.screen.CreateCollectableScreen
import com.eddevios.collections.ui.collectable.screen.EditCollectableScreen
import com.eddevios.collections.ui.collection.screen.EditCollectionScreen
import com.eddevios.collections.ui.category.screen.EditCategoryScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val appContext = LocalContext.current.applicationContext as Application

    NavHost(navController = navController, startDestination = Routes.COLLECTIONS) {

        //COLLECTION SCREEN
        composable(Routes.COLLECTIONS) {
            CollectionScreen(
                navController = navController)
        }

        //CREATE COLLECTION SCREEN
        composable(Routes.CREATE_COLLECTION) {
            CreateCollectionScreen(
                navController = navController)
        }

        // EDIT COLLECTION SCREEN
        composable(
            Routes.EDIT_COLLECTION,
            arguments = listOf(navArgument("collectionId") { type = NavType.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt("collectionId") ?: return@composable
            EditCollectionScreen(
                navController = navController,
                collectionId = id
            )
        }

        // CATEGORY SCREEN
        composable(
            Routes.CATEGORY,
            arguments = listOf(navArgument("collectionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val collectionId = backStackEntry.arguments?.getInt("collectionId") ?: -1
            if (collectionId <= 0) {
                // Manejar error: ID inválido, quizás navegar atrás o mostrar mensaje
                Log.e("NavGraph", "ID de colección inválido recibido en ruta CATEGORY: $collectionId")
            } else {
                CategoryScreen(
                    navController = navController,
                    collectionId = collectionId
                )
            }
        }

        // CREATE CATEGORY SCREEN
        composable(
            Routes.CREATE_CATEGORY,
            arguments = listOf(navArgument("collectionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val collectionId = backStackEntry.arguments?.getInt("collectionId") ?: -1
            if (collectionId <= 0) {
                Log.e("DEBUG", "ID de colección inválido recibido en ruta CREATE_CATEGORY: $collectionId")
            } else {
                CreateCategoryScreen(
                    navController = navController,
                    collectionId = collectionId
                )
            }
        }

        // EDIT CATEGORY SCREEN
        composable(
            route = Routes.EDIT_CATEGORY,
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
        ) { backStack ->
            val categoryId = backStack.arguments?.getInt("categoryId") ?: return@composable

            EditCategoryScreen(
                navController = navController,
                categoryId = categoryId
            )
        }

        // COLLECTABLE SCREEN
        composable(
            // CAMBIO AQUÍ: Usa la ruta actualizada de Routes
            Routes.COLLECTABLE,
            // CAMBIO AQUÍ: Solo espera categoryId
            arguments = listOf(
                navArgument("categoryId") { type = NavType.IntType }
                // Elimina navArgument("categoryTitle")
            )
        ) { backStackEntry ->
            // CAMBIO AQUÍ: Solo extrae categoryId
            val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: -1
            // Elimina la extracción de categoryTitle

            if (categoryId <= 0) {
                Log.e("NavGraph", "ID de categoría inválido recibido en ruta COLLECTABLE: $categoryId")
                // Manejar error
            } else {
                Log.d("NavGraph", "Navegando a CollectableScreen con categoryId: $categoryId")
                CollectableScreen(
                    navController = navController,
                    categoryId = categoryId
                    // CAMBIO AQUÍ: Elimina el paso de categoryTitle
                )
            }
        }

        // COLLECTABLE DETAIL SCREEN
        composable(
            Routes.COLLECTABLE_DETAIL,
            arguments = listOf(navArgument("collectableId") { type = NavType.IntType })
        ) { backStackEntry ->
            val collectableId = backStackEntry.arguments?.getInt("collectableId") ?: -1
            if (collectableId > 0) {
                CollectableDetailScreen(
                    navController = navController,
                    collectableId = collectableId
                )
            }
        }

        // EDIT COLLECTABLE SCREEN
        composable(
            Routes.EDIT_COLLECTABLE,
            arguments = listOf(navArgument("collectableId") { type = NavType.IntType })
        ) { backStackEntry ->
            val collectableId = backStackEntry.arguments?.getInt("collectableId") ?: -1
            if (collectableId > 0) {
                EditCollectableScreen(
                    navController = navController,
                    collectableId = collectableId
                )
            }
        }

        // CREATE COLLECTABLE SCREEN (Asegúrate que esta ruta y composable estén correctos)
        composable(
            Routes.CREATE_COLLECTABLE,
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: -1
            if (categoryId <= 0) {
                Log.e("NavGraph", "ID de categoría inválido recibido en ruta CREATE_COLLECTABLE: $categoryId")
            } else {
                CreateCollectableScreen(
                    navController = navController,
                    categoryId = categoryId
                )
            }
        }
    }
}