## 📁 Estructura del Proyecto

```text

com.eddevios.collections
│
├── MainActivity.kt             // Punto de entrada principal de la app, configura Compose y el tema
│
├── CollectionsApp.kt           // Aplicación principal
│
├── google-services.json        // Configuración de Firebase (No incluido en el repositorio)
│
├── data
│   ├── local
│   │   ├── dao
│   │   │   ├── CollectionDao.kt                 // Operaciones CRUD para colecciones (Room)
│   │   │   ├── CategoryDao.kt                   // Operaciones CRUD para categorías (Room)
│   │   │   └── CollectableDao.kt                // Operaciones CRUD para coleccionables (Room)
│   │   ├── entity
│   │   │   ├── CollectionEntity.kt              // Modelo de base de datos para colecciones
│   │   │   ├── CategoryEntity.kt                // Modelo de base de datos para categorías
│   │   │   └── CollectableEntity.kt             // Modelo de base de datos para coleccionables
│   │   └── db
│   │       └── AppDatabase.kt                   // Configuración principal de Room y acceso a DAOs
│   ├── model
│   │   ├── Collection.kt                        // Modelo de dominio para colecciones (sin Room)
│   │   ├── Category.kt                          // Modelo de dominio para categorías (sin Room)
│   │   └── Collectable.kt                       // Modelo de dominio para coleccionables (sin Room)
│   └── mapper
│       ├── CollectionMapper.kt                  // Conversiones
│       ├── CategoryMappers.kt                   // Conversiones
│       ├── CollectableMappers.kt                // Conversiones
│       └── ModelMapper.kt                       // Conversiones entre Entity ↔ Model (data layer)
│   
├── features
│   └── common
│       ├── components
│       │   ├── ReusableSearchBar.kt             // Componente UI para mostrar SearchBar
│       │   ├── SectionTitle.kt                  // Crea una sección con un título
│       │   └── CameraAndGalleryPicker.kt        // Componente reusable para seleccionar imágenes
│       ├── model
│       │   └── FilterOption.kt                  // Componente reusable para filtrar resultados
│       ├── permissions
│       │   ├── PermissionUtils.kt               // Solicita permisos al usuario
│       │   ├── RequestPermissions.kt            // Solicita permisos al usuario de cámara
│       │   └── RequestCameraPermission.kt       // Solicita permisos al usuario
│       └── utils
│           ├── ImageProcessor.kt                // Tratamiento común de cualquier foto 
│           └── ImageUtils.kt                    // Selector de galería procesado
│
├── ui
│   │
│   ├── ads
│   │   ├── AdMobBanner                          // Vista de Banner
│   │   ├── AdMobManager                         // Carga y muestra interstitial
│   │   ├── ShowInterstitialIfNeeded             // Mostrar Interstitial
│   │   └── InterstitialAdTracker                // Frecuencia para mostrar Interstitial
│   │
│   ├── collection
│   │   ├── components 
│   │   │   ├── CollectionItem.kt                // Modelo de Colección para UI
│   │   │   └── LargeImageCollectionItem.kt      // Vista detallada de un ítem de colección
│   │   ├── screen
│   │   │   ├── CollectionScreen.kt              // Pantalla principal de listado de colecciones
│   │   │   └── CreateCollectionScreen.kt        // Pantalla de creación/edición de colecciones
│   │   └── viewmodel
│   │       └── CollectionViewModel.kt           // Lógica de negocio para colecciones
│   │
│   ├── category
│   │   ├── components
│   │   │   ├── LargeImageCategoryItem.kt        // Componente UI para ítem de categoría en lista
│   │   │   └── CategoryItem.kt                  // Modelo de Categoría para UI
│   │   ├── screen
│   │   │   ├── CategoryScreen.kt                // Pantalla de listado de categorías
│   │   │   └── CreateCategoryScreen.kt          // Pantalla de creación/edición de categorías
│   │   └── viewmodel
│   │       └── CategoryViewModel.kt             // Lógica de negocio para categorías
│   │
│   ├── collectable
│   │   ├── components
│   │   │   ├── CollectableGridItem.kt           // Componente UI para ítem de coleccionables en Collection View
│   │   │   └── CollectableItem.kt               // Modelo de Coleccionables para UI
│   │   ├── screen
│   │   │   ├── CollectableScreen.kt             // Pantalla de listado de coleccionables
│   │   │   └── CreateCollectableScreen.kt       // Pantalla de creación/edición de coleccionables
│   │   └── viewmodel
│   │       └── CollectableViewModel.kt          // Lógica de negocio para coleccionables
│   │
│   ├── navigation
│   │   ├── NavGraph.kt                          // Configuración principal del grafo de navegación
│   │   └── Routes.kt                            // Definición de todas las rutas de navegación
│   │
│   └── theme
│       ├── Color.kt                             // Paleta de colores de la app (Material 3)
│       ├── Theme.kt                             // Configuración del tema principal de Compose
│       └── Type.kt                              // Tipografías y estilos de texto
│
├── utils
│   ├── AppConfig.kt                             // Configuración global (ej: timeout, límites de caracteres)
│   ├── ComposeUtils.kt                          // Funciones utilitarias para Compose (ej: estados debounced)
│   ├── Dialogs.kt                               // Diálogos reutilizables (confirmación, errores, etc)
│   ├── EmptyStateView.kt                        // Mostrar icono de vacio y opcion de crear(coleccion, etc...)
│   ├── HapticController.kt                      // Controlador para efectos de Vibración
│   ├── CrashlyticsLogger.kt                     // Singleton de Crashlytics
│   └── TextUtils.kt                             // Componente
│
└── di                              
    └── AppModule.kt                             // Configuración de Hilt para inyección de dependencias

```

## 🔧 Desarrollo

## 🔀 Flujo de datos completo

CollectionScreen (CollectionViewModel)
↓ (navigation)
CategoryScreen (CategoryViewModel)
↓ (navigation)
CollectableScreen (CollectableViewModel)

# Game Collections

**Game Collections** es una aplicación Android desarrollada en Kotlin con Jetpack Compose, diseñada para que los usuarios puedan crear, organizar y gestionar sus colecciones personales de forma intuitiva y eficiente. Permite definir colecciones, categorizarlas (opcionalmente o como parte del flujo) y añadir elementos coleccionables detallados, incluyendo la posibilidad de adjuntar imágenes.
## ✨Tipo de Aplicación
*   **La aplicación parece ser un gestor de colecciones personalizable, donde los usuarios pueden:
*   **Crear colecciones: Organizar elementos en grupos.
*   **Gestionar categorías: Clasificar las colecciones.

## ✨Servicios y Funcionalidades Clave
*   *Base de datos local (Room): Almacena datos de forma persistente en el dispositivo, permitiendo el acceso offline.
*   *Jetpack Compose: Interfaz de usuario moderna y declarativa.
*   *Navegación: Permite moverse entre las diferentes secciones de la app (colecciones, categorías, coleccionables).
*   *Gestión de imágenes: Permite a los usuarios adjuntar imágenes a los coleccionables y posiblemente a las colecciones.
*   *Publicidad (AdMob): Genera ingresos a través de banners e interstitials.
*   *Solicitud de permisos: Acceso a la cámara para adjuntar fotos.

Añadir coleccionables: Agregar elementos individuales a las colecciones, cada uno con sus propios detalles.
## ✨ Características Principales

*   **Gestión Completa de Colecciones:** Crea, visualiza, edita y elimina colecciones personalizadas.
*   **Organización por Categorías:** Agrupa y filtra tus colecciones mediante categorías para una mejor organización.
*   **Elementos Coleccionables Detallados:** Añade ítems individuales a tus colecciones, con campos específicos y la capacidad de adjuntar imágenes desde la galería o la cámara.
*   **Persistencia Local Robusta:** Todos los datos se guardan de forma segura en el dispositivo utilizando la base de datos Room, permitiendo el acceso offline.
*   **Interfaz de Usuario Moderna:** Desarrollada íntegramente con Jetpack Compose, ofreciendo una experiencia de usuario fluida, reactiva y visualmente atractiva.
*   **Navegación Intuitiva:** Flujo de navegación claro entre las pantallas de colecciones, categorías (si aplica como pantalla separada) y coleccionables.
*   **Gestión de Permisos:** Solicitud de permisos necesarios (ej. cámara, galería) de forma clara para el usuario.
*   **Monetización Integrada:** Incluye publicidad a través de AdMob (banners e intersticiales) para soportar el desarrollo de la aplicación.
*   **Componentes Reutilizables:** Estructura modular con componentes de UI y utilidades comunes para un desarrollo y mantenimiento más sencillos.

## 🛠️ Tecnologías y Arquitectura

*   **Lenguaje:** Kotlin
*   **UI Toolkit:** Jetpack Compose
*   **Base de Datos:** Room Persistence Library
*   **Arquitectura:** MVVM (Model-View-ViewModel) implícito por el uso de `ViewModel` y la separación de la lógica de negocio de la UI.
*   **Navegación:** Jetpack Navigation Component para Compose.
*   **Inyección de Dependencias:** Configurada a través de Hilt (inferido por `AppModule.kt` en el directorio `di`).
*   **Publicidad:** Google AdMob.
*   **Manejo de Imágenes:** Componentes y utilidades para la selección y procesamiento básico de imágenes.

## 🔀 Flujo de Datos Básico

La aplicación permite al usuario navegar generalmente de la siguiente manera:

1.  **Pantalla de Colecciones (`CollectionScreen`):** Muestra un listado de todas las colecciones creadas. El usuario puede seleccionar una colección existente, editar o crear una nueva.
*   Gestionado por `CollectionViewModel`.
2.  **(Opcional/Integrado) Pantalla de Categorías (`CategoryScreen`):** Permite visualizar o asignar categorías a las colecciones. Podría ser un paso intermedio o una forma de filtrar/organizar la vista de colecciones.
*   Gestionado por `CategoryViewModel`.
3.  **Pantalla de Coleccionables (`CollectableScreen`):** Al seleccionar una colección (y posiblemente una categoría), se muestra un listado de los elementos coleccionables dentro de ella. El usuario puede ver detalles de un coleccionable, editar o añadir nuevos.
*   Gestionado por `CollectableViewModel`.

Los datos se almacenan localmente usando entidades Room (`CollectionEntity`, `CategoryEntity`, `CollectableEntity`) y se mapean a modelos de dominio para su uso en la lógica de negocio y la UI.

## 🔀Posibles Mejoras o Extensiones

*   **Búsqueda: Permitir a los usuarios buscar colecciones, categorías o coleccionables.
*   **Importación/Exportación: Permitir a los usuarios exportar sus datos a un archivo y/o importarlos desde uno.
*   **Sincronización en la nube: Permitir a los usuarios sincronizar sus datos entre dispositivos.
*   **Funciones sociales: Permitir a los usuarios compartir sus colecciones con otros.
*   **Widgets: Mostrar información de las colecciones en la pantalla de inicio.
---

//app/manifest/
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
        android:name=".CollectionsApp"
        android:allowBackup="false"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Collections"
        tools:targetApi="35">
        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="${admobAppId}"/>

        <!-- ACTIVITY -->
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <!-- PROVEEDOR -->
        <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="${applicationId}.provider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths" />
        </provider>
    </application>

    <!-- CÁMARA -->
    <uses-feature
        android:name="android.hardware.camera"
        android:required="true" />
    <uses-feature
        android:name="android.hardware.camera.autofocus"
        android:required="true" />

    <!-- PERMISOS -->
    <uses-permission android:name="android.permission.POST_NOTIFICATIONS"/>
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.VIBRATE" />

    <!-- FORZAR ELIMINACIÓN DE PERMISOS MULTIMEDIA PARA CUMPLIR POLÍTICAS DE GOOGLE -->
    <uses-permission android:name="android.permission.READ_MEDIA_IMAGES" tools:node="remove" />
    <uses-permission android:name="android.permission.READ_MEDIA_VIDEO" tools:node="remove" />
    <uses-permission android:name="android.permission.READ_MEDIA_VISUAL_USER_SELECTED" tools:node="remove" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" tools:node="remove" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" tools:node="remove" />

</manifest>
```

/app/kotlin+java/com.eddevios.collections
package com.eddevios.collections

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.eddevios.collections.data.local.db.AppDatabase
import com.eddevios.collections.ui.navigation.NavGraph
import com.eddevios.collections.ui.theme.CollectionsTheme
import com.google.android.gms.ads.MobileAds
import com.eddevios.collections.utils.AppConfig
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
enableEdgeToEdge()
FirebaseApp.initializeApp(this)
MobileAds.initialize(this)
requestNotificationPermissionIfNeeded()
InitOneSignal()
AppDatabase.getDatabase(this)
//HideBottomBar()
setContent {
CollectionsTheme(dynamicColor = false) {
// A surface container using the 'background' color from the theme
Surface(
modifier = Modifier.fillMaxSize(),
color = MaterialTheme.colorScheme.background,
) {
NavGraph()
}
}
}
}

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = android.Manifest.permission.POST_NOTIFICATIONS
            if (checkSelfPermission(permission) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(permission), 1001)
            }
        }
    }

    private fun InitOneSignal() {
        OneSignal.Debug.logLevel = LogLevel.VERBOSE
        OneSignal.initWithContext(this, AppConfig.ONESIGNAL_ID)
        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(true)
        }
    }

    private fun HideBottomBar() {
        // Para Android 11+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let { controller ->
                controller.hide(WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            // Para versiones anteriores
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }
}

package com.eddevios.collections
import android.app.Application
import dagger.hilt.android.HiltAndroidApp
@HiltAndroidApp
class CollectionsApp : Application()

package com.eddevios.collections.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update
import com.eddevios.collections.data.local.entity.CategoryEntity
import com.eddevios.collections.data.local.entity.CollectionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
@Query("SELECT * FROM categories WHERE collectionId = :collectionId ORDER BY `order` ASC")
fun getCategoriesByCollectionId(collectionId: Int): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getCategoryById(id: Int): CategoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity): Long

    @Query("UPDATE categories SET `order` = :newOrder WHERE id = :id")
    suspend fun updateCategoryOrder(id: Int, newOrder: Int)

    @Update
    suspend fun updateCategory(category: CategoryEntity)

    @Query("DELETE FROM categories WHERE id = :id")
    suspend fun deleteCategoryById(id: Int)
}

package com.eddevios.collections.data.local.dao

import androidx.room.*
import com.eddevios.collections.data.local.entity.CollectableEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectableDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insert(collectable: CollectableEntity): Long

    @Update
    suspend fun update(collectable: CollectableEntity)

    @Query("DELETE FROM collectables WHERE id = :id")
    suspend fun deleteCollectableById(id: Int)

    @Query("SELECT * FROM collectables WHERE categoryId = :categoryId ORDER BY `order` ASC")
    fun getCollectablesByCategory(categoryId: Int): Flow<List<CollectableEntity>>

    @Query("SELECT * FROM collectables WHERE id = :collectableId")
    suspend fun getCollectableById(collectableId: Int): CollectableEntity?

    @Query("SELECT * FROM collectables WHERE isFavorite = 1 ORDER BY createdAt DESC")
    fun getFavorites(): Flow<List<CollectableEntity>>

    @Query("UPDATE collectables SET `order` = :newOrder WHERE id = :id")
    suspend fun updateCollectableOrder(id: Int, newOrder: Int)

    @Query("SELECT MAX(`order`) FROM collectables WHERE categoryId = :categoryId")
    suspend fun getMaxOrderForCategory(categoryId: Int): Int?
}

package com.eddevios.collections.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update
import com.eddevios.collections.data.local.entity.CollectionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionDao {
@Query("SELECT * FROM collections ORDER BY `order` ASC")
fun getAllCollections(): Flow<List<CollectionEntity>>

    @Query("SELECT * FROM collections WHERE id = :id")
    suspend fun getCollectionById(id: Int): CollectionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollection(collection: CollectionEntity)

    @Update
    suspend fun updateCollection(collection: CollectionEntity)

    @Query("UPDATE collections SET `order` = :newOrder WHERE id = :id")
    suspend fun updateCollectionOrder(id: Int, newOrder: Int)

    @Query("DELETE FROM collections WHERE id = :id")
    suspend fun deleteCollectionById(id: Int)
}

package com.eddevios.collections.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eddevios.collections.data.local.dao.CategoryDao
import com.eddevios.collections.data.local.dao.CollectableDao
import com.eddevios.collections.data.local.dao.CollectionDao
import com.eddevios.collections.data.local.entity.CategoryEntity
import com.eddevios.collections.data.local.entity.CollectableEntity
import com.eddevios.collections.data.local.entity.CollectionEntity

@Database(entities = [CollectionEntity::class, CategoryEntity::class, CollectableEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
abstract fun collectionDao(): CollectionDao
abstract fun categoryDao(): CategoryDao
abstract fun collectableDao(): CollectableDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "collection_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

package com.eddevios.collections.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
tableName = "categories",
foreignKeys = [
ForeignKey(
entity = CollectionEntity::class,
parentColumns = ["id"],
childColumns = ["collectionId"],
onDelete = ForeignKey.CASCADE
)
]
)
data class CategoryEntity(
@PrimaryKey(autoGenerate = true) val id: Int = 0,
val title: String,
val subtitle: String?,
val imageUri: String?,
val collectionId: Int, // Clave foránea
val order: Int,
val createdAt: Long,
val lastModifiedAt: Long,
val isFavorite: Boolean = false
)

package com.eddevios.collections.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
tableName = "collectables",
foreignKeys = [
ForeignKey(
entity = CategoryEntity::class,
parentColumns = ["id"],
childColumns = ["categoryId"],
onDelete = ForeignKey.CASCADE
)
]
)
data class CollectableEntity(
@PrimaryKey(autoGenerate = true) val id: Int = 0,
val title: String,
val subtitle: String?,
val imageUri: String?,
val comments: String?,
val categoryId: Int, // Clave foránea
val order: Int,
val createdAt: Long,
val lastModifiedAt: Long,
val isFavorite: Boolean = false
)

package com.eddevios.collections.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collections")
data class CollectionEntity(
@PrimaryKey(autoGenerate = true) val id: Int = 0,
val title: String,
val subtitle: String,
val imageUri: String?,
val order: Int,
val createdAt: Long,
val lastModifiedAt: Long,
val isFavorite: Boolean = false
)

package com.eddevios.collections.data.mapper

import com.eddevios.collections.data.local.entity.CategoryEntity
import com.eddevios.collections.ui.category.components.CategoryItem

fun CategoryItem.toEntity(): CategoryEntity {
return CategoryEntity(
id = this.id,
title = this.title,
subtitle = this.subtitle,
imageUri = this.imageUri,
collectionId = this.collectionId,
order = this.order,
createdAt = this.createdAt,
lastModifiedAt = System.currentTimeMillis()
)
}

fun CategoryEntity.toCategoryItem(): CategoryItem {
return CategoryItem(
id = this.id,
title = this.title,
subtitle = this.subtitle,
imageUri = this.imageUri,
collectionId = this.collectionId,
order = this.order,
createdAt = this.createdAt,
lastModifiedAt = this.lastModifiedAt
)
}

package com.eddevios.collections.data.mapper

import com.eddevios.collections.data.local.entity.CollectableEntity
import com.eddevios.collections.ui.collectable.components.CollectableItem

// data/mapper/CollectableMappers.kt
fun CollectableItem.toEntity(): CollectableEntity {
return CollectableEntity(
id = this.id,
title = this.title,
subtitle = this.subtitle,
imageUri = this.imageUri,
comments = this.comments,
categoryId = this.categoryId,
order = this.order,
createdAt = this.createdAt,
lastModifiedAt = System.currentTimeMillis(),
isFavorite = this.isFavorite
)
}

fun CollectableEntity.toCollectableItem(): CollectableItem {
return CollectableItem(
id = this.id,
title = this.title,
subtitle = this.subtitle,
imageUri = this.imageUri,
comments = this.comments,
categoryId = this.categoryId,
order = this.order,
createdAt = this.createdAt,
lastModifiedAt = this.lastModifiedAt,
isFavorite = this.isFavorite
)
}

package com.eddevios.collections.data.mapper

import com.eddevios.collections.data.local.entity.CollectionEntity
import com.eddevios.collections.ui.collection.components.CollectionItem

fun CollectionItem.toEntity(): CollectionEntity {
return CollectionEntity(
id = this.id,
title = this.title,
subtitle = this.subtitle,
imageUri = this.imageUri,
order = this.order,
createdAt = System.currentTimeMillis(),
lastModifiedAt = System.currentTimeMillis()
)
}

fun CollectionEntity.toCollectionItem(): CollectionItem {
return CollectionItem(
// Mantén las fechas existentes
id = this.id,
title = this.title,
subtitle = this.subtitle,
imageUri = this.imageUri,
order = this.order,
createdAt = this.createdAt
)
}

package com.eddevios.collections.data.mapper

import com.eddevios.collections.data.local.entity.CategoryEntity
import com.eddevios.collections.data.local.entity.CollectableEntity
import com.eddevios.collections.data.local.entity.CollectionEntity
import com.eddevios.collections.data.model.Category
import com.eddevios.collections.data.model.Collectable
import com.eddevios.collections.data.model.Collection

object ModelMapper {

    // Convierte CollectionEntity + lista de CategoryEntity -> Collection
    fun toCollection(
        entity: CollectionEntity,
        categories: List<CategoryEntity>
    ): Collection {
        return Collection(
            id = entity.id,
            title = entity.title,
            subtitle = entity.subtitle,
            imageUri = entity.imageUri,
            order = entity.order,
            createdAt = entity.createdAt,
            lastModifiedAt = entity.lastModifiedAt,
            isFavorite = entity.isFavorite,
            categories = categories.map { toCategory(it, emptyList()) }
        )
    }

    // Convierte CategoryEntity + lista de CollectableEntity -> Category
    fun toCategory(
        entity: CategoryEntity,
        collectables: List<CollectableEntity>
    ): Category {
        return Category(
            id = entity.id,
            title = entity.title,
            subtitle = entity.subtitle.toString(),
            imageUri = entity.imageUri,
            order = entity.order,
            createdAt = entity.createdAt,
            lastModifiedAt = entity.lastModifiedAt,
            isFavorite = entity.isFavorite,
            collectables = collectables.map { toCollectable(it) }
        )
    }

    // Convierte CollectableEntity -> Collectable
    fun toCollectable(entity: CollectableEntity): Collectable {
        return Collectable(
            id = entity.id,
            title = entity.title,
            subtitle = entity.subtitle,
            imageUri = entity.imageUri,
            comments = entity.comments,
            order = entity.order,
            createdAt = entity.createdAt,
            lastModifiedAt = entity.lastModifiedAt,
            isFavorite = entity.isFavorite
        )
    }
}

package com.eddevios.collections.data.model

data class Category(
val id: Int,
val title: String,
val subtitle: String,
val imageUri: String?,
val order: Int,
val createdAt: Long,
val lastModifiedAt: Long,
val isFavorite: Boolean,
val collectables: List<Collectable> = emptyList() // Anidación aquí
)

package com.eddevios.collections.data.model

data class Collectable(
val id: Int,
val title: String,
val subtitle: String?,
val imageUri: String?,
val comments: String?,
val order: Int,
val createdAt: Long,
val lastModifiedAt: Long,
val isFavorite: Boolean
)

package com.eddevios.collections.data.model

data class Collection(
val id: Int,
val title: String,
val subtitle: String,
val imageUri: String?,
val order: Int,
val createdAt: Long,
val lastModifiedAt: Long,
val isFavorite: Boolean,
val categories: List<Category> = emptyList()
)

package com.eddevios.collections.di

import android.content.Context
import androidx.room.Room
import com.eddevios.collections.data.local.dao.CategoryDao
import com.eddevios.collections.data.local.dao.CollectableDao
import com.eddevios.collections.data.local.dao.CollectionDao
import com.eddevios.collections.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext
import com.eddevios.collections.utils.CrashlyticsLogger

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "collection_database"
        ).build()

    @Provides
    fun provideCollectionDao(db: AppDatabase): CollectionDao = db.collectionDao()

    @Provides
    fun provideCategoryDao(db: AppDatabase): CategoryDao = db.categoryDao()

    @Provides
    fun provideCollectableDao(db: AppDatabase): CollectableDao = db.collectableDao()

    @Provides
    @Singleton
    fun provideCrashlyticsLogger(): CrashlyticsLogger {
        return CrashlyticsLogger()
    }
}

package com.eddevios.collections.features.common.components

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.core.content.FileProvider
import java.io.File

@Composable
fun CameraAndGalleryPicker(
context: Context,
onImageSelected: (Uri?) -> Unit
) {
var imageUri by remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            onImageSelected(imageUri)
        } else {
            Toast.makeText(context, "No se tomó la foto", Toast.LENGTH_SHORT).show()
        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        onImageSelected(uri)
    }

    Button(onClick = {
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photo_${System.currentTimeMillis()}.jpg")
        imageUri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)

        imageUri?.let { uri ->
            cameraLauncher.launch(uri)
        } ?: run {
            Toast.makeText(context, "No se pudo crear el archivo para la imagen", Toast.LENGTH_SHORT).show()
        }
    }) {
        Text("Abrir Cámara")
    }

    Button(onClick = { 
        galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) 
    }) {
        Text("Abrir Galería")
    }
}

package com.eddevios.collections.features.common.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color

@Composable
fun ReusableSearchBar(
query: String,
onQueryChange: (String) -> Unit,
onSearch: (String) -> Unit,
modifier: Modifier = Modifier,
placeholder: String = "Buscar"
) {
TextField(
value = query,
onValueChange = onQueryChange,
placeholder = { Text(placeholder) },
singleLine = true,
leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
trailingIcon = {
if (query.isNotEmpty()) {
IconButton(onClick = { onQueryChange("") }) {
Icon(Icons.Default.Close, contentDescription = "Borrar texto")
}
} else {
IconButton(onClick = { "" }) {
Icon(Icons.Filled.Search, contentDescription = "Buscar")
}
}
},
modifier = modifier,
shape = CircleShape,
colors = TextFieldDefaults.colors(
focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
focusedTextColor = MaterialTheme.colorScheme.onSurface,
focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
cursorColor = MaterialTheme.colorScheme.surfaceVariant,
focusedIndicatorColor = Color.Transparent,
unfocusedIndicatorColor = Color.Transparent
)
)
}

package com.eddevios.collections.features.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text

@Composable
fun SectionTitle(
text: String,
modifier: Modifier = Modifier,
style: TextStyle = MaterialTheme.typography.titleSmall
) {
Text(
text = text,
style = style,
color = MaterialTheme.colorScheme.primary,
modifier = modifier
.fillMaxWidth()
.padding(start = 24.dp, top = 8.dp, bottom = 8.dp)
)
}

package com.eddevios.collections.features.common.model

import androidx.compose.ui.graphics.vector.ImageVector

data class FilterOption(val label: String, val icon: ImageVector)

package com.eddevios.collections.features.common.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {

    /**
     * Comprueba si se han concedido todos los permisos especificados.
     *
     * @param context Contexto de la app.
     * @param permissions Array de permisos a verificar.
     * @return `true` si todos los permisos han sido concedidos, `false` en caso contrario.
     */
    fun hasPermissions(context: Context, permissions: Array<String>): Boolean {
        return permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    /**
     * Solicita permisos al usuario.
     *
     * @param activity Actividad desde la que se solicita el permiso.
     * @param permissions Array de permisos a solicitar.
     * @param requestCode Código único para identificar la solicitud.
     */
    fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }

    /**
     * Comprueba si el usuario ha denegado algún permiso y seleccionado "No volver a preguntar".
     *
     * @param activity Actividad desde la que se verifica el permiso.
     * @param permission Permiso específico a verificar.
     * @return `true` si se ha denegado con "No volver a preguntar", `false` en caso contrario.
     */
    fun shouldShowRationale(activity: Activity, permission: String): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
    }
}

package com.eddevios.collections.features.common.permissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun RequestCameraPermission(
onPermissionGranted: () -> Unit,
onPermissionDenied: () -> Unit
) {
val context = LocalContext.current
val permissionLauncher = rememberLauncherForActivityResult(
contract = ActivityResultContracts.RequestPermission()
) { isGranted ->
if (isGranted) {
onPermissionGranted()
} else {
onPermissionDenied()
}
}

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        } else {
            onPermissionGranted()
        }
    }
}

package com.eddevios.collections.features.common.permissions

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*

@Composable
fun RequestPermissions(
onPermissionsGranted: () -> Unit,
onPermissionsDenied: () -> Unit
) {
val permissionLauncher = rememberLauncherForActivityResult(
ActivityResultContracts.RequestMultiplePermissions()
) { permissions ->
val allGranted = permissions.all { it.value }
if (allGranted) {
onPermissionsGranted()
} else {
onPermissionsDenied()
}
}

    LaunchedEffect(Unit) {
        val permissions = arrayOf(Manifest.permission.CAMERA)
        permissionLauncher.launch(permissions)
    }
}

package com.eddevios.collections.features.common.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class ImageProcessor(private val context: Context) {

    suspend fun process(input: Uri): Uri = withContext(Dispatchers.IO) {

        /* ---------- 1. Dimensiones ---------- */
        val (w, h) = context.contentResolver.openInputStream(input)!!.use { stream ->
            val o = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeStream(stream, null, o)
            o.outWidth to o.outHeight
        }

        /* ---------- 2. inSampleSize “potencia-de-2” ---------- */
        val maxSide = 1280
        val largest   = maxOf(w, h)
        var sample    = 1
        while (largest / (sample * 2) >= maxSide) sample *= 2   // potencia de 2 recomendado por Android[4]

        val opts = BitmapFactory.Options().apply { inSampleSize = sample }
        val bitmap = context.contentResolver.openInputStream(input)!!.use {
            BitmapFactory.decodeStream(it, null, opts)!!
        }

        /* ---------- 3. Corregir rotación EXIF ---------- */
        val rotated = context.contentResolver.openInputStream(input)!!.use { stream ->
            val ei = ExifInterface(stream)
            val angle = when (ei.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )) {
                ExifInterface.ORIENTATION_ROTATE_90  -> 90
                ExifInterface.ORIENTATION_ROTATE_180 -> 180
                ExifInterface.ORIENTATION_ROTATE_270 -> 270
                else -> 0
            }
            if (angle != 0) {
                val m = Matrix().apply { postRotate(angle.toFloat()) }
                Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, m, true)
                    .also { bitmap.recycle() }
            } else bitmap
        }                                             // Solución basada en el uso de ExifInterface[5]

        /* ---------- 4. Comprimir ---------- */
        val file = File(context.filesDir, "img_${System.currentTimeMillis()}.jpg")
        FileOutputStream(file).use { out ->
            rotated.compress(Bitmap.CompressFormat.JPEG, 80, out)   // mantiene compatibilidad plena[6]
        }
        rotated.recycle()
        file.toUri()
    }
}

package com.eddevios.collections.features.common.utils

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch


@Composable
fun rememberImagePicker(
imageProcessor: ImageProcessor,
onImageReady: (Uri) -> Unit
) : () -> Unit {

    val scope = rememberCoroutineScope()
    val gallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri ?: return@rememberLauncherForActivityResult
        scope.launch {
            val processed = imageProcessor.process(uri)
            onImageReady(processed)
        }
    }
    return { gallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }
}

package com.eddevios.collections.ads

import android.view.Gravity
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.eddevios.collections.utils.AppConfig
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdMobBanner(
adUnitId: String = AppConfig.BANNER_AD_ID
) {
AndroidView(
modifier = Modifier.fillMaxWidth(),
factory = { context ->
AdView(context).apply {
setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, 320))
this.adUnitId = adUnitId
loadAd(AdRequest.Builder().build())

                // Centrar el contenido del AdView internamente
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER_HORIZONTAL
                }
            }
        }
    )
}

package com.eddevios.collections.ads

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import androidx.core.view.WindowCompat
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback

object AdMobManager {

    private var interstitialAd: InterstitialAd? = null

    fun loadInterstitial(context: Context, adUnitId: String, onAdLoaded: (() -> Unit)? = null) {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(context, adUnitId, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(ad: InterstitialAd) {
                interstitialAd = ad
                onAdLoaded?.invoke()
            }

            override fun onAdFailedToLoad(error: com.google.android.gms.ads.LoadAdError) {
                Log.d("AdMob", "Error cargando interstitial: ${error.message}")
                interstitialAd = null
            }
        })
    }
/*
fun showInterstitial(activity: Activity, onAdClosed: (() -> Unit)? = null) {
interstitialAd?.let { ad ->
ad.fullScreenContentCallback = object : com.google.android.gms.ads.FullScreenContentCallback() {
override fun onAdDismissedFullScreenContent() {
interstitialAd = null
onAdClosed?.invoke()
}

                override fun onAdFailedToShowFullScreenContent(adError: com.google.android.gms.ads.AdError) {
                    Log.d("AdMob", "Error mostrando interstitial: ${adError.message}")
                    interstitialAd = null
                    onAdClosed?.invoke()
                }
            }

            ad.show(activity)
        } ?: onAdClosed?.invoke()
    }
*/
fun showInterstitial(activity: Activity, onAdClosed: (() -> Unit)? = null) {
interstitialAd?.let { ad ->
// 1. Ajustar decor fitsSystemWindows para que el close button no quede oculto
WindowCompat.setDecorFitsSystemWindows(activity.window, true)

            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    // 3. Restaurar edge-to-edge tras cerrar el interstitial
                    WindowCompat.setDecorFitsSystemWindows(activity.window, false)
                    interstitialAd = null
                    onAdClosed?.invoke()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    WindowCompat.setDecorFitsSystemWindows(activity.window, false)
                    interstitialAd = null
                    onAdClosed?.invoke()
                }

                override fun onAdShowedFullScreenContent() {
                    // 2. Aquí podría ocultar barra de estado si además quieres inmersión completa
                    //activity.window.insetsController?.hide(WindowInsets.Type.statusBars())
                }
            }

            ad.show(activity)
        } ?: onAdClosed?.invoke()
    }

}

package com.eddevios.collections.ads

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.eddevios.collections.utils.AppConfig

@Composable
fun ShowInterstitialIfNeeded() {
val context = LocalContext.current
val activity = context as? Activity

    LaunchedEffect(Unit) {
        if (InterstitialAdTracker.shouldShowAd()) {
            AdMobManager.loadInterstitial(
                context = context,
                adUnitId = AppConfig.INTERSTITIAL_AD_ID
            ) {
                activity?.let {
                    AdMobManager.showInterstitial(it)
                }
            }
        }
    }
}

package com.eddevios.collections.ads
import com.eddevios.collections.utils.AppConfig

object InterstitialAdTracker {
private var interstitialCounter = 0
private var lastInterstitialTime = 0L

    fun shouldShowAd(): Boolean {
        interstitialCounter++
        val currentTime = System.currentTimeMillis()
        val timeSinceLastAd = currentTime - lastInterstitialTime
        val shouldShow = interstitialCounter % 3 == 0 && timeSinceLastAd >= AppConfig.INTERSTITIAL_TIME_INTERVAL
        if (shouldShow) {
            lastInterstitialTime = currentTime
        }
        return shouldShow
    }

    fun reset() {
        interstitialCounter = 0
        lastInterstitialTime = 0L
    }
}

package com.eddevios.collections.ui.category.components

data class CategoryItem(
val id: Int,
val title: String,
val subtitle: String?,
val imageUri: String?,
val collectionId: Int,
val order: Int,
val createdAt: Long,
val lastModifiedAt: Long
)

package com.eddevios.collections.ui.category.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.eddevios.collections.R
import com.eddevios.collections.ui.theme.CollectionsTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LargeImageCategoryItem(
category: CategoryItem,
isEditing: Boolean,
onClick: () -> Unit,
onEdit: () -> Unit,
onDelete: () -> Unit,
modifier: Modifier = Modifier
) {
Card(
modifier = modifier
.fillMaxWidth() // Ocupa todo el ancho, sin distancias laterales para la Card.
.height(220.dp) // Altura considerablemente mayor, puedes ajustarla.
.clickable(enabled = !isEditing, onClick = onClick), // Permite click solo si no se está editando.
shape = RoundedCornerShape(16.dp), // Esquinas más redondeadas para un look moderno.
elevation = CardDefaults.cardElevation(defaultElevation = 6.dp) // Elevación para dar profundidad.
) {
Box(modifier = Modifier.fillMaxSize()) { // Box permite superponer elementos.
// Imagen de fondo con fuerte presencia visual.
val imageModifier = Modifier
.fillMaxSize()
.clip(RoundedCornerShape(16.dp)) // Asegura que la imagen también tenga bordes redondeados si sobresale.

            if (category.imageUri != null) {
                GlideImage(
                    model = category.imageUri,
                    contentDescription = category.title, // Importante para accesibilidad.
                    modifier = imageModifier,
                    contentScale = ContentScale.Crop // Escala la imagen para llenar el espacio, recortando si es necesario.
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.default_image), // Imagen por defecto.
                    contentDescription = category.title,
                    modifier = imageModifier,
                    contentScale = ContentScale.Crop
                )
            }

            // Gradiente oscuro en la parte inferior para asegurar legibilidad del texto.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                            startY = 300f // Ajusta este valor para controlar dónde empieza el gradiente.
                        )
                    )
            )

            // Título y subtítulo superpuestos en la parte inferior.
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart) // Alinea el texto abajo a la izquierda.
                    .fillMaxWidth()
                    .padding(16.dp) // Padding interno para el texto.
            ) {
                Text(
                    text = category.title,
                    style = MaterialTheme.typography.headlineSmall.copy(color = Color.White), // Estilo para el título.
                    maxLines = 2, // Máximo dos líneas para el título.
                    overflow = TextOverflow.Ellipsis // Añade "..." si el texto es muy largo.
                )
                // Muestra el subtítulo solo si no está vacío.
                if (category.subtitle!!.isNotBlank()) {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = category.subtitle.orEmpty(),
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White.copy(alpha = 0.9f)),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Iconos de editar/borrar si `isEditing` es true.
            if (isEditing) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd) // Alinea los iconos arriba a la derecha.
                        .padding(8.dp)
                        .background(
                            Color.Black.copy(alpha = 0.4f), // Fondo semitransparente para los iconos.
                            RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 4.dp, vertical = 2.dp), // Padding interno para los iconos.
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onEdit,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar",
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Borrar",
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }
        }
    }
}

// --- Preview para el nuevo componente ---
@Preview(name = "LargeImageCollectionItem - Modo Claro", showBackground = true)
@Preview(name = "LargeImageCollectionItem - Modo Oscuro", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun LargeImageCollectionItemPreview() {
val dummyCollection = CategoryItem(
id = 1,
title = "Mi Colección Increíble con un Título Muy Largo",
subtitle = "Un subtítulo descriptivo y moderno",
imageUri = null, // Puedes poner una URL de imagen de prueba aquí
collectionId = 1,
order = 0,
createdAt = 0,
lastModifiedAt = 0
)
CollectionsTheme(dynamicColor = false) {
Surface(color = MaterialTheme.colorScheme.background) {
Column(modifier = Modifier.padding(16.dp)) {
LargeImageCategoryItem(
category = dummyCollection,
isEditing = true,
onClick = { },
onEdit = { },
onDelete = { }
)
Spacer(Modifier.height(16.dp))
LargeImageCategoryItem(
category = dummyCollection.copy(
id=2,
title = "Otra Colección",
subtitle = "" // Prueba sin subtítulo
),
isEditing = false,
onClick = { },
onEdit = { },
onDelete = { }
)
}
}
}
}

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

package com.eddevios.collections.ui.category.screen

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.eddevios.collections.R
import com.eddevios.collections.ads.AdMobBanner
import com.eddevios.collections.ui.category.viewmodel.CategoryViewModel
import com.eddevios.collections.utils.CameraDialog
import java.io.File
import com.eddevios.collections.utils.HapticController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.rememberCoroutineScope
import com.eddevios.collections.features.common.utils.ImageProcessor
import com.eddevios.collections.features.common.utils.rememberImagePicker
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCategoryScreen(
navController: NavController,
collectionId: Int,
viewModel: CategoryViewModel = hiltViewModel()) {

    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var isError by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val imageProcessor = remember { ImageProcessor(context) }
    val pickImage = rememberImagePicker(
        onImageReady = { processedUri -> imageUri = processedUri },
        imageProcessor = imageProcessor
    )

    // --- Lógica para seleccionar imagen ---
    val scope = rememberCoroutineScope()
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri != null) {
            scope.launch {
                imageUri = imageProcessor.process(photoUri!!)
            }
        }
    }

    fun openCamera() {
        val fileName = "temp_image_${System.currentTimeMillis()}.jpg"
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
        photoUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
        cameraLauncher.launch(photoUri!!)
    }

    // Lanzador para permisos de cámara
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(context, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        bottomBar = { Column(modifier = Modifier.navigationBarsPadding()) {
            AdMobBanner()
        } },
        topBar = {
            TopAppBar(
                title = { Text("Crear Categoría") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Mostrar imagen seleccionada o un placeholder
                if (imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Imagen seleccionada",
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painterResource(id = R.drawable.default_image),
                        contentDescription = null,
                        modifier = Modifier.size(180.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                // Botón para abrir el selector de imágenes
                Button(onClick = { showDialog = true }) {
                    Text(stringResource(R.string.image_select))
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = title,
                    onValueChange = {
                        title = it
                        isError = title.isEmpty()
                    },
                    label = { Text(stringResource(R.string.name_category)) },
                    isError = isError,
                    placeholder = { Text("Título...") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .heightIn(min = 40.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                // No solicitar el teclado aquí evita que se abra automáticamente
                            }
                        },
                    shape = CircleShape,
                    textStyle = MaterialTheme.typography.bodyMedium.copy( // Tamaño de texto reducido
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedPlaceholderColor = Color.LightGray,
                        cursorColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedIndicatorColor = Color.Transparent, // Sin línea inferior al estar enfocado
                        unfocusedIndicatorColor = Color.Transparent // Sin línea inferior al no estar enfocado
                    )
                )

                if (isError) {
                    Text(
                        text = "El nombre no puede estar vacío",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                TextField(
                    value = subtitle,
                    onValueChange = { subtitle = it },
                    label = { Text(stringResource(R.string.subtitle)) },
                    placeholder = { Text("Subtitulo...") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .heightIn(min = 40.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                // No solicitar el teclado aquí evita que se abra automáticamente
                            }
                        },
                    shape = CircleShape,
                    textStyle = MaterialTheme.typography.bodyMedium.copy( // Tamaño de texto reducido
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedPlaceholderColor = Color.LightGray,
                        cursorColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedIndicatorColor = Color.Transparent, // Sin línea inferior al estar enfocado
                        unfocusedIndicatorColor = Color.Transparent // Sin línea inferior al no estar enfocado
                    )
                )

                // Diálogo para elegir entre cámara o galería
                if (showDialog) {
                    CameraDialog(
                        title = stringResource(R.string.image_select),
                        message = stringResource(R.string.cam_or_img),
                        onTakePhoto = {
                            showDialog = false
                            when (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)) {
                                PackageManager.PERMISSION_GRANTED -> openCamera()
                                else -> cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        },
                        onPickFromGallery = {
                            showDialog = false
                            pickImage()
                        },
                        onDismiss = { showDialog = false }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        HapticController.oneShot(context, 70L)
                        if (title.isNotEmpty()) {
                            viewModel.addCategory(
                                title = title,
                                subtitle = subtitle,
                                imageUri = imageUri,
                                collectionId = collectionId
                            )
                            navController.navigateUp()
                        } else {
                            isError = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                ) {
                    Text(stringResource(R.string.save))
                }
            }
        }
    )
}

package com.eddevios.collections.ui.category.screen

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.eddevios.collections.R
import com.eddevios.collections.ads.AdMobBanner
import com.eddevios.collections.ui.category.viewmodel.CategoryViewModel
import com.eddevios.collections.utils.CameraDialog
import java.io.File
import com.eddevios.collections.utils.HapticController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.rememberCoroutineScope
import com.eddevios.collections.features.common.utils.ImageProcessor
import com.eddevios.collections.features.common.utils.rememberImagePicker
import kotlinx.coroutines.launch
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCategoryScreen(
navController: NavController,
categoryId: Int,
viewModel: CategoryViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = categoryId) {
        viewModel.loadCategoryById(categoryId)
    }

    val category by viewModel.selectedCategory.collectAsState()
    var title by rememberSaveable(category) { mutableStateOf(category?.title ?: "") }
    var subtitle by rememberSaveable(category) { mutableStateOf(category?.subtitle ?: "") }
    var imageUri by rememberSaveable(category) { mutableStateOf(category?.imageUri?.toUri()) }
    var isError by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current
    val imageProcessor = remember { ImageProcessor(context) }
    val pickImage = rememberImagePicker(
        onImageReady = { processedUri -> imageUri = processedUri },
        imageProcessor = imageProcessor
    )

    // --- Lógica para seleccionar imagen ---
    val scope = rememberCoroutineScope()
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri != null) {
            scope.launch {
                imageUri = imageProcessor.process(photoUri!!)
            }
        }
    }

    fun openCamera() {
        val fileName = "temp_image_${System.currentTimeMillis()}.jpg"
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
        photoUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
        cameraLauncher.launch(photoUri!!)
    }

    // Lanzador para permisos de cámara
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(context, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        bottomBar = { Column(modifier = Modifier.navigationBarsPadding()) {
            AdMobBanner()
        } },
        topBar = {
            TopAppBar(
                title = { Text("Editar Categoría") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues).padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Mostrar imagen seleccionada o un placeholder
                if (imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Imagen seleccionada",
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painterResource(id = R.drawable.default_image),
                        contentDescription = null,
                        modifier = Modifier.size(180.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                // Botón para abrir el selector de imágenes
                Button(onClick = { showDialog = true }) {
                    Text(stringResource(R.string.image_select))
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = title,
                    onValueChange = {
                        title = it
                        isError = title.isEmpty()
                    },
                    label = { Text(stringResource(R.string.name_category)) },
                    isError = isError,
                    placeholder = { Text("Título...") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .heightIn(min = 40.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                // No solicitar el teclado aquí evita que se abra automáticamente
                            }
                        },
                    shape = CircleShape,
                    textStyle = MaterialTheme.typography.bodyMedium.copy( // Tamaño de texto reducido
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedPlaceholderColor = Color.LightGray,
                        cursorColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedIndicatorColor = Color.Transparent, // Sin línea inferior al estar enfocado
                        unfocusedIndicatorColor = Color.Transparent // Sin línea inferior al no estar enfocado
                    )
                )

                if (isError) {
                    Text(
                        text = "El nombre no puede estar vacío",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                TextField(
                    value = subtitle,
                    onValueChange = { subtitle = it },
                    label = { Text(stringResource(R.string.subtitle)) },
                    placeholder = { Text("Subtitulo...") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .heightIn(min = 40.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                // No solicitar el teclado aquí evita que se abra automáticamente
                            }
                        },
                    shape = CircleShape,
                    textStyle = MaterialTheme.typography.bodyMedium.copy( // Tamaño de texto reducido
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedPlaceholderColor = Color.LightGray,
                        cursorColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedIndicatorColor = Color.Transparent, // Sin línea inferior al estar enfocado
                        unfocusedIndicatorColor = Color.Transparent // Sin línea inferior al no estar enfocado
                    )
                )

                // Diálogo para elegir entre cámara o galería
                if (showDialog) {
                    CameraDialog(
                        title = stringResource(R.string.image_select),
                        message = stringResource(R.string.cam_or_img),
                        onTakePhoto = {
                            showDialog = false
                            when (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)) {
                                PackageManager.PERMISSION_GRANTED -> openCamera()
                                else -> cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        },
                        onPickFromGallery = {
                            showDialog = false
                            pickImage()
                        },
                        onDismiss = { showDialog = false }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        HapticController.oneShot(context, 70L)
                        if (title.isNotEmpty()) {
                            viewModel.updateCategory(
                                id = categoryId,
                                title = title,
                                subtitle = subtitle,
                                imageUri = imageUri
                            )
                            navController.navigateUp()
                        } else {
                            isError = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                ) {
                    Text(stringResource(R.string.save))
                }
            }
        }
    )
}

package com.eddevios.collections.ui.category.screen

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.eddevios.collections.R
import com.eddevios.collections.ads.AdMobBanner
import com.eddevios.collections.ui.category.viewmodel.CategoryViewModel
import com.eddevios.collections.utils.CameraDialog
import java.io.File
import com.eddevios.collections.utils.HapticController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.rememberCoroutineScope
import com.eddevios.collections.features.common.utils.ImageProcessor
import com.eddevios.collections.features.common.utils.rememberImagePicker
import kotlinx.coroutines.launch
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCategoryScreen(
navController: NavController,
categoryId: Int,
viewModel: CategoryViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = categoryId) {
        viewModel.loadCategoryById(categoryId)
    }

    val category by viewModel.selectedCategory.collectAsState()
    var title by rememberSaveable(category) { mutableStateOf(category?.title ?: "") }
    var subtitle by rememberSaveable(category) { mutableStateOf(category?.subtitle ?: "") }
    var imageUri by rememberSaveable(category) { mutableStateOf(category?.imageUri?.toUri()) }
    var isError by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current
    val imageProcessor = remember { ImageProcessor(context) }
    val pickImage = rememberImagePicker(
        onImageReady = { processedUri -> imageUri = processedUri },
        imageProcessor = imageProcessor
    )

    // --- Lógica para seleccionar imagen ---
    val scope = rememberCoroutineScope()
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri != null) {
            scope.launch {
                imageUri = imageProcessor.process(photoUri!!)
            }
        }
    }

    fun openCamera() {
        val fileName = "temp_image_${System.currentTimeMillis()}.jpg"
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
        photoUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
        cameraLauncher.launch(photoUri!!)
    }

    // Lanzador para permisos de cámara
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(context, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        bottomBar = { Column(modifier = Modifier.navigationBarsPadding()) {
            AdMobBanner()
        } },
        topBar = {
            TopAppBar(
                title = { Text("Editar Categoría") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues).padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Mostrar imagen seleccionada o un placeholder
                if (imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Imagen seleccionada",
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painterResource(id = R.drawable.default_image),
                        contentDescription = null,
                        modifier = Modifier.size(180.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                // Botón para abrir el selector de imágenes
                Button(onClick = { showDialog = true }) {
                    Text(stringResource(R.string.image_select))
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = title,
                    onValueChange = {
                        title = it
                        isError = title.isEmpty()
                    },
                    label = { Text(stringResource(R.string.name_category)) },
                    isError = isError,
                    placeholder = { Text("Título...") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .heightIn(min = 40.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                // No solicitar el teclado aquí evita que se abra automáticamente
                            }
                        },
                    shape = CircleShape,
                    textStyle = MaterialTheme.typography.bodyMedium.copy( // Tamaño de texto reducido
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedPlaceholderColor = Color.LightGray,
                        cursorColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedIndicatorColor = Color.Transparent, // Sin línea inferior al estar enfocado
                        unfocusedIndicatorColor = Color.Transparent // Sin línea inferior al no estar enfocado
                    )
                )

                if (isError) {
                    Text(
                        text = "El nombre no puede estar vacío",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                TextField(
                    value = subtitle,
                    onValueChange = { subtitle = it },
                    label = { Text(stringResource(R.string.subtitle)) },
                    placeholder = { Text("Subtitulo...") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .heightIn(min = 40.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                // No solicitar el teclado aquí evita que se abra automáticamente
                            }
                        },
                    shape = CircleShape,
                    textStyle = MaterialTheme.typography.bodyMedium.copy( // Tamaño de texto reducido
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedPlaceholderColor = Color.LightGray,
                        cursorColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedIndicatorColor = Color.Transparent, // Sin línea inferior al estar enfocado
                        unfocusedIndicatorColor = Color.Transparent // Sin línea inferior al no estar enfocado
                    )
                )

                // Diálogo para elegir entre cámara o galería
                if (showDialog) {
                    CameraDialog(
                        title = stringResource(R.string.image_select),
                        message = stringResource(R.string.cam_or_img),
                        onTakePhoto = {
                            showDialog = false
                            when (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)) {
                                PackageManager.PERMISSION_GRANTED -> openCamera()
                                else -> cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        },
                        onPickFromGallery = {
                            showDialog = false
                            pickImage()
                        },
                        onDismiss = { showDialog = false }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        HapticController.oneShot(context, 70L)
                        if (title.isNotEmpty()) {
                            viewModel.updateCategory(
                                id = categoryId,
                                title = title,
                                subtitle = subtitle,
                                imageUri = imageUri
                            )
                            navController.navigateUp()
                        } else {
                            isError = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                ) {
                    Text(stringResource(R.string.save))
                }
            }
        }
    )
}

package com.eddevios.collections.ui.collectable.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign // Importación necesaria para TextAlign
import androidx.compose.ui.text.style.TextOverflow // Importación necesaria para TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.eddevios.collections.R
import com.eddevios.collections.ui.theme.CollectionsTheme

@SuppressLint("ResourceAsColor")
@Composable
fun CollectableGridItem(
collectable: CollectableItem,
isEditing: Boolean, // Aunque no se usa en este snippet, se mantiene por consistencia
imageElevation: Dp,
modifier: Modifier = Modifier // Este modifier viene de la LazyVerticalGrid (e.g., shadow, clickable)
) {
Column(
modifier = modifier, // El modifier externo se aplica a la Column principal
horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente
) {
// Box para la imagen y el indicador de favorito.
// Este Box mantendrá el aspect ratio y el clip para la parte visual superior.
Box(
modifier = Modifier
.fillMaxWidth() // La imagen ocupa el ancho disponible en la celda de la Column
.aspectRatio(0.707f) // Ratio A4 para la parte de la imagen
.clip(MaterialTheme.shapes.medium) // Clip para la parte de la imagen
) {
// Card que contiene la imagen
Card(
modifier = Modifier.fillMaxSize(), // La Card llena el Box de la imagen
shape = MaterialTheme.shapes.medium, // Forma de la Card
elevation = CardDefaults.cardElevation(defaultElevation = 2.dp) // Elevación de la Card
) {
Image(
painter = if (collectable.imageUri != null) {
rememberAsyncImagePainter(model = collectable.imageUri)
} else {
painterResource(id = R.drawable.default_image) // Imagen por defecto
},
contentDescription = collectable.title, // Descripción para accesibilidad
modifier = Modifier.fillMaxSize(),
contentScale = ContentScale.Crop // Escala y recorta la imagen para llenar el espacio
)
}

            // Indicador de Favorito (si aplica)
            if (collectable.isFavorite) {
                val cornerSize = 48.dp // Tamaño del área del indicador triangular

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd) // Alineado a la esquina inferior derecha del Box de la imagen
                        .size(cornerSize)
                ) {
                    // Triángulo en la esquina
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val trianglePath = Path().apply {
                            moveTo(0f, size.height)
                            lineTo(size.width, size.height)
                            lineTo(size.width, 0f)
                            close()
                        }
                        drawPath(
                            path = trianglePath,
                            color = Color(0xFFE2C15C) // Color amarillo para el triángulo
                        )
                    }

                    // Icono de Favorito superpuesto
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorito",
                        tint = Color.Black, // Color del icono para contraste
                        modifier = Modifier
                            .align(Alignment.BottomEnd) // Alineado dentro del Box del indicador
                            .padding(bottom = 4.dp, end = 4.dp) // Padding para el icono
                            .size(20.dp) // Tamaño del icono
                    )
                }
            }

        }
        // Texto del título, debajo del Box de la imagen
        Text(
            text = collectable.title ?: "", // Muestra el título o una cadena vacía si es nulo
            style = MaterialTheme.typography.bodyLarge, // Estilo del texto (puedes cambiarlo a titleSmall, etc.)
            maxLines = 1, // Asegura que el texto esté en una sola línea
            overflow = TextOverflow.Ellipsis, // Añade "..." si el texto es demasiado largo
            textAlign = TextAlign.Center, // Centra el texto horizontalmente
            modifier = Modifier
                .background(color = Color.Transparent)
                .fillMaxWidth() // Permite que el Text ocupe todo el ancho para el centrado
        )
    }
}

// --- PREVIEWS (sin cambios, pero el item se verá un poco más alto) ---
@Preview(name = "Light Mode - Not Favorite", showBackground = true, group = "CollectableGridItem")
@Preview(name = "Dark Mode - Not Favorite", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, group = "CollectableGridItem")
@Composable
private fun CollectableGridItemPreviewNotFavorite() {
CollectionsTheme (dynamicColor = false){
Surface(color = MaterialTheme.colorScheme.background) {
CollectableGridItem(
collectable = CollectableItem(
id = 1, title = "Un objeto coleccionable con un título largo", subtitle = null, imageUri = null, comments = null,
categoryId = 1, order = 0, createdAt = 0, lastModifiedAt = 0,
isFavorite = false
),
isEditing = false,
imageElevation = 2.dp,
modifier = Modifier.padding(8.dp).width(120.dp)
)
}
}
}

@Preview(name = "Light Mode - Favorite", showBackground = true, group = "CollectableGridItem")
@Preview(name = "Dark Mode - Favorite", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, group = "CollectableGridItem")
@Composable
private fun CollectableGridItemPreviewFavorite() {
CollectionsTheme (dynamicColor = false){
Surface(color = MaterialTheme.colorScheme.background) {
CollectableGridItem(
collectable = CollectableItem(
id = 1, title = "Otro Item", subtitle = null, imageUri = null, comments = null,
categoryId = 1, order = 0, createdAt = 0, lastModifiedAt = 0,
isFavorite = true
),
isEditing = false,
imageElevation = 2.dp,
modifier = Modifier.padding(8.dp).width(120.dp)
)
}
}
}

package com.eddevios.collections.ui.collectable.components

data class CollectableItem(
val id: Int,
val title: String,
val subtitle: String?,
val imageUri: String?,
val comments: String?,
val categoryId: Int,
val order: Int,
val createdAt: Long,
val lastModifiedAt: Long,
val isFavorite: Boolean
)

package com.eddevios.collections.ui.collectable.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.eddevios.collections.R
import com.eddevios.collections.ads.AdMobBanner
import com.eddevios.collections.ui.collectable.viewmodel.CollectableViewModel
import com.eddevios.collections.features.common.components.SectionTitle
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import com.eddevios.collections.data.local.entity.CollectableEntity
import com.eddevios.collections.ui.theme.CollectionsTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.eddevios.collections.utils.ConfirmDeleteDialog
import com.eddevios.collections.utils.HapticController
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectableDetailScreen(
navController: NavHostController,
viewModel: CollectableViewModel = hiltViewModel(),
collectableId: Int
) {
var showDeleteDialog by remember { mutableStateOf(false) }
var showFullScreenImage by remember { mutableStateOf<String?>(null) } // Para URI de imagen o null

    LaunchedEffect(collectableId) {
        viewModel.loadCollectableById(collectableId)
    }
    val item by viewModel.selectedCollectable.collectAsState()
    val sectionShape = RoundedCornerShape(12.dp)
    val context = LocalContext.current

    // Obtener la altura de la pantalla
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = { Column(modifier = Modifier.navigationBarsPadding()) {
            AdMobBanner()
        } },
        topBar = {
            TopAppBar(
                title = { Text(text = item?.title.orEmpty()) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        HapticController.oneShot(context, 200L)
                        showDeleteDialog = true
                    }) {
                        Icon(Icons.Default.Delete, "Eliminar")
                    }
                    IconButton(onClick = {
                        item?.let { navController.navigate("edit_collectable/${it.id}") }
                    }) {
                        Icon(Icons.Default.Edit, "Editar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { innerPadding ->
        item?.let { collectable ->
            // Column principal que permite el scroll de todo el contenido
            Column(
                modifier = Modifier
                    .padding(innerPadding) // Aplica el padding del Scaffold
                    .verticalScroll(rememberScrollState()) // Permite scroll vertical
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                // --- Sección Imagen ---
                Spacer(Modifier.height(12.dp))
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    shape = sectionShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    // Box que contendrá la imagen
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight / 2) // La imagen ocupa la mitad de la altura de la pantalla
                            .clip(sectionShape) // Mantiene las esquinas redondeadas de la Surface
                            .background(MaterialTheme.colorScheme.surfaceVariant) // Fondo si la imagen no llena
                            .clickable { // Permite click para ver a pantalla completa
                                // Usar imageUri si existe, sino la imagen por defecto (adaptar si es necesario)
                                val imageToShow = collectable.imageUri
                                // Solo muestra a pantalla completa si hay una URI (o la por defecto si se desea)
                                if (!imageToShow.isNullOrEmpty()) {
                                    showFullScreenImage = imageToShow
                                } else {
                                    // Opcional: decidir si la imagen por defecto también se puede ver a pantalla completa
                                    // Para este ejemplo, solo mostramos si hay una URI específica.
                                }
                            }
                    ) {
                        Image(
                            painter = if (!collectable.imageUri.isNullOrEmpty())
                                rememberAsyncImagePainter(collectable.imageUri)
                            else painterResource(id = R.drawable.default_image),
                            contentDescription = collectable.title,
                            modifier = Modifier.fillMaxSize(), // La imagen se ajusta al tamaño del Box
                            contentScale = ContentScale.Fit // Muestra la imagen completa, manteniendo aspect ratio
                        )
                        Icon(
                            imageVector = if (collectable.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorito",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                                .size(32.dp)
                                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.7f), CircleShape)
                        )
                    }
                }

                // --- Sección Título ---
                Spacer(Modifier.height(12.dp))
                Surface(
                    shape = sectionShape,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) { // Añadido padding interno
                        SectionTitle(text = stringResource(R.string.title))
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = collectable.title,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }

                // --- Sección Subtítulo ---
                if (!collectable.subtitle.isNullOrEmpty()) {
                    Spacer(Modifier.height(12.dp))
                    Surface(
                        shape = sectionShape,
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) { // Añadido padding interno
                            SectionTitle(text = stringResource(R.string.subtitle))
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = collectable.subtitle,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }

                // --- Sección Comentarios ---
                if (!collectable.comments.isNullOrEmpty()) {
                    Spacer(Modifier.height(12.dp))
                    Surface(
                        // color = Color.White, // Puedes usar MaterialTheme.colorScheme.surface
                        color = MaterialTheme.colorScheme.surface,
                        shape = sectionShape,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) { // Añadido padding interno
                            SectionTitle(text = stringResource(R.string.comments))
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = collectable.comments,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }

                Spacer(Modifier.height(12.dp)) // Espacio al final antes del AdMobBanner
            }

            // Diálogo de confirmación de borrado (existente)
            if (showDeleteDialog) {
                ConfirmDeleteDialog(
                    title = stringResource(R.string.delete_collectable),
                    message = stringResource(R.string.wish_delete, item?.title.orEmpty()),
                    confirmText = stringResource(R.string.delete),
                    cancelText = stringResource(R.string.cancel),
                    confirmColor = Color.Red,
                    onConfirm = {
                        item?.let {
                            viewModel.deleteCollectableById(it.id)
                            navController.popBackStack()
                        }
                        showDeleteDialog = false
                    },
                    onDismiss = { showDeleteDialog = false }
                )
            }
        }

        // Diálogo para mostrar la imagen a pantalla completa
        if (showFullScreenImage != null) {
            FullScreenImageViewer(
                imageUri = showFullScreenImage!!, // Sabemos que no es nulo aquí
                onDismiss = { showFullScreenImage = null }
            )
        }
    }
}

@Composable
fun FullScreenImageViewer(imageUri: String, onDismiss: () -> Unit) {
Dialog(
onDismissRequest = onDismiss,
properties = DialogProperties(
usePlatformDefaultWidth = false, // Para que ocupe todo el ancho
dismissOnBackPress = true,
dismissOnClickOutside = true
)
) {
Surface(
modifier = Modifier.fillMaxSize(), // Ocupa toda la pantalla del diálogo
color = Color.Black.copy(alpha = 0.9f) // Fondo oscuro semitransparente
) {
Box(modifier = Modifier.fillMaxSize()) {
Image(
painter = rememberAsyncImagePainter(model = imageUri),
contentDescription = "Imagen a pantalla completa",
modifier = Modifier
.fillMaxSize() // La imagen llena el Box
.padding(16.dp), // Un poco de padding para que no pegue a los bordes
contentScale = ContentScale.Fit // Muestra la imagen completa
)
IconButton(
onClick = onDismiss,
modifier = Modifier
.align(Alignment.TopEnd) // Botón en la esquina superior derecha
.padding(16.dp)
) {
Icon(
imageVector = Icons.Default.Close,
contentDescription = "Cerrar vista de imagen",
tint = Color.White // Icono blanco para contraste con fondo oscuro
)
}
}
}
}
}

@Composable
fun CollectableDetailContent(
collectable: CollectableEntity,
isFavorite: Boolean,
onBack: () -> Unit,
onDelete: () -> Unit,
onEdit: (Int) -> Unit
) {
//val collectable = CollectableEntity(id = 0, title = "Título", subtitle = "Subtítulo", imageUri = null, comments = "Comentarios", categoryId = 1, order = 1, createdAt = 1L, lastModifiedAt = 1L, isFavorite = false) // Ejemplo
//val isFavorite = false
val sectionShape = RoundedCornerShape(12.dp)

    // Fondo y scroll
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFF4))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Imagen
        SectionTitle(text = "Imagen")
        Surface(
            shape    = sectionShape,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(sectionShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Image(
                    painter = collectable.imageUri?.let { rememberAsyncImagePainter(it) }
                        ?: painterResource(R.drawable.default_image),
                    contentDescription = collectable.title,
                    modifier     = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop
                )
                Icon(
                    imageVector   = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorito",
                    tint          = MaterialTheme.colorScheme.primary,
                    modifier      = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(24.dp)
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.6f), CircleShape)
                        .clickable { /* opcional: toggle */ }
                )
            }
        }

        // Título
        SectionTitle(text = "Título")
        Surface(
            shape    = sectionShape,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text     = collectable.title,
                style    = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }

        // Subtítulo
        if (!collectable.subtitle.isNullOrEmpty()) {
            SectionTitle(text = "Subtítulo")
            Surface(
                color    = Color.White, // Consistent with original preview if needed, or use MaterialTheme.colorScheme.surface
                shape    = sectionShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text     = collectable.subtitle,
                    style    = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        // Comentarios
        if (!collectable.comments.isNullOrEmpty()) {
            SectionTitle(text = "Comentarios")
            Surface(
                shape    = sectionShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text     = collectable.comments,
                    style    = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview(name = "Modo Claro", showBackground = true)
@Preview(name = "Modo Oscuro", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CollectableDetailContentPreview() {
val mock = CollectableEntity(
id = 1,
title = "GameBoy Color",
subtitle = "Pokémon Edition",
comments = "Pantalla con arañazos leves",
imageUri = null,
categoryId = 1,
order = 0,
createdAt = 0L,
lastModifiedAt = 0L,
isFavorite = true
)

    CollectionsTheme {
        CollectableDetailContent(
            collectable = mock,
            isFavorite = true,
            onBack   = {},
            onDelete = {},
            onEdit   = {}
        )
    }
}

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

package com.eddevios.collections.ui.collectable.screen

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.eddevios.collections.ui.collectable.viewmodel.CollectableViewModel
import java.io.File
import androidx.compose.foundation.shape.CircleShape
import com.eddevios.collections.R
import androidx.compose.ui.res.stringResource
import com.eddevios.collections.ads.AdMobBanner
import com.eddevios.collections.utils.CameraDialog
import com.eddevios.collections.utils.HapticController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import com.eddevios.collections.features.common.utils.ImageProcessor
import com.eddevios.collections.features.common.utils.rememberImagePicker
import androidx.compose.material.icons.automirrored.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CreateCollectableScreen(
navController: NavController,
categoryId: Int,
viewModel: CollectableViewModel = hiltViewModel()) {

    // --- Estados para los campos ---
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    var comments by remember { mutableStateOf("") }
    var isFavorite by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // --- Estados auxiliares ---
    var titleError by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val imageProcessor = remember { ImageProcessor(context) }
    val pickImage = rememberImagePicker(
        onImageReady = { processedUri -> imageUri = processedUri },
        imageProcessor = imageProcessor
    )
    // --- Requester y Scope para el scroll automático ---
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    // --- Lógica para seleccionar imagen ---
    val scope = rememberCoroutineScope()
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri != null) {
            scope.launch {
                imageUri = imageProcessor.process(photoUri!!)
            }
        }
    }

    LaunchedEffect(categoryId) {
        viewModel.loadCollectablesAndTitle(categoryId)
    }

    fun openCamera() {
        val fileName = "temp_image_${System.currentTimeMillis()}.jpg"
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
        try {
            photoUri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider", // Asegúrate que el provider está en AndroidManifest.xml
                file
            )
            // Solo lanzar si photoUri no es null (FileProvider.getUriForFile puede fallar teóricamente)
            photoUri?.let { uri ->
                cameraLauncher.launch(uri)
            } ?: run {
                Log.e("CreateCollectableScreen", "photoUri es null después de FileProvider.getUriForFile")
                Toast.makeText(context, R.string.camera_error, Toast.LENGTH_SHORT).show()
            }

        } catch (e: Exception) {
            Log.e("CreateCollectableScreen", "Error creando FileProvider URI o lanzando cámara", e)
            Toast.makeText(context, R.string.camera_error, Toast.LENGTH_SHORT).show()
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(context, R.string.camera_denied, Toast.LENGTH_SHORT).show()
        }
    }

    // --- Composable UI ---
    Scaffold(
        bottomBar = { Column(modifier = Modifier.navigationBarsPadding()) {
            AdMobBanner()
        } },
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        topBar = {
            TopAppBar(
                title = { Text( stringResource(R.string.create_collectable)) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
// 1) Box con imagen + corazón superpuesto
Box(
modifier = Modifier
.size(180.dp)
.clip(MaterialTheme.shapes.medium)
.background(MaterialTheme.colorScheme.surfaceVariant) // opcional: fondo mientras carga
) {
// a) La propia imagen
Image(
painter = imageUri
?.let { rememberAsyncImagePainter(it) }
?: painterResource(id = R.drawable.default_image),
contentDescription = "Imagen del coleccionable",
modifier = Modifier.matchParentSize(),
contentScale = ContentScale.Crop
)

                // b) Toggle de favorito en esquina superior derecha
                IconToggleButton(
                    checked = isFavorite,
                    onCheckedChange = { isFavorite = it },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(24.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // --- Botón Seleccionar Imagen ---
            Button(onClick = { showDialog = true }) {
                Text(stringResource(R.string.image_select))
            }

            // 4) Campos de texto: Título, Subtítulo, Comentarios…
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    if (it.isNotBlank()) titleError = false
                },
                label = { Text("Título *") },
                isError = titleError,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            if (titleError) {
                Text(
                    text = stringResource(R.string.not_empty_title),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                )
            }

            OutlinedTextField(
                value = subtitle,
                onValueChange = { subtitle = it },
                label = { Text(stringResource(R.string.subtitle)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = comments,
                onValueChange = { comments = it },
                label = { Text(stringResource(R.string.comments)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp) // Altura fija en lugar de heightIn
                    .bringIntoViewRequester(bringIntoViewRequester)
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            scope.launch {
                                // Añadir delay para asegurar que el teclado aparezca primero
                                kotlinx.coroutines.delay(300)
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    },
                maxLines = 6,
                minLines = 3, // Añadir líneas mínimas
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary
                ),
                textStyle = LocalTextStyle.current.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    HapticController.oneShot(context, 70L)
                    titleError = title.isBlank()
                    if (!titleError) {
                        viewModel.addCollectable(
                            title = title,
                            subtitle = subtitle.takeIf { it.isNotBlank() },
                            imageUri = imageUri,
                            comments = comments.takeIf { it.isNotBlank() },
                            isFavorite = isFavorite,
                            categoryId = categoryId
                        )
                        navController.navigateUp()
                    } else {
                        Toast.makeText(context, R.string.not_empty_title, Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(stringResource(R.string.save))
            }
        }

        // --- Diálogo para elegir Cámara/Galería ---
        if (showDialog) {
            CameraDialog(
                title = stringResource(R.string.image_select),
                message = stringResource(R.string.cam_or_img),
                onTakePhoto = {
                    showDialog = false
                    when (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)) {
                        PackageManager.PERMISSION_GRANTED -> openCamera()
                        else -> cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                },
                onPickFromGallery = {
                    showDialog = false
                    pickImage()
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}

package com.eddevios.collections.ui.collectable.screen

import android.Manifest
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.eddevios.collections.R
import com.eddevios.collections.ui.collectable.viewmodel.CollectableViewModel
import android.content.pm.PackageManager
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.eddevios.collections.utils.CameraDialog
import java.io.File
import com.eddevios.collections.utils.HapticController
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import com.eddevios.collections.ads.AdMobBanner
import com.eddevios.collections.features.common.utils.ImageProcessor
import com.eddevios.collections.features.common.utils.rememberImagePicker
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun EditCollectableScreen(
navController: NavHostController,
viewModel: CollectableViewModel = hiltViewModel(),
collectableId: Int
) {
// 1) Carga inicial y estado observable
LaunchedEffect(collectableId) {
viewModel.loadCollectableById(collectableId)
}
val collectable by viewModel.selectedCollectable.collectAsState()

    // 2) Estados locales para editar
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    var comments by remember { mutableStateOf("") }
    var isFavorite by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // --- Estados auxiliares ---
    var titleError by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val imageProcessor = remember { ImageProcessor(context) }
    val pickImage = rememberImagePicker(
        onImageReady = { processedUri -> imageUri = processedUri },
        imageProcessor = imageProcessor
    )
    // --- Requester y Scope para el scroll automático ---
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    // 3) Inicializar campos al cargar el collectable
    LaunchedEffect(collectable) {
        collectable?.let {
            title      = it.title
            subtitle   = it.subtitle.orEmpty()
            comments   = it.comments.orEmpty()
            isFavorite = it.isFavorite
            imageUri   = it.imageUri?.toUri()
        }
    }

    // --- Lógica para seleccionar imagen ---
    val scope = rememberCoroutineScope()
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri != null) {
            scope.launch {
                imageUri = imageProcessor.process(photoUri!!)
            }
        }
    }

    fun openCamera() {
        val fileName = "temp_image_${System.currentTimeMillis()}.jpg"
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
        try {
            photoUri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider", // Asegúrate que el provider está en AndroidManifest.xml
                file
            )
            // Solo lanzar si photoUri no es null (FileProvider.getUriForFile puede fallar teóricamente)
            photoUri?.let { uri ->
                cameraLauncher.launch(uri)
            } ?: run {
                Log.e("CreateCollectableScreen", "photoUri es null después de FileProvider.getUriForFile")
                Toast.makeText(context, R.string.camera_error, Toast.LENGTH_SHORT).show()
            }

        } catch (e: Exception) {
            Log.e("CreateCollectableScreen", "Error creando FileProvider URI o lanzando cámara", e)
            Toast.makeText(context, R.string.camera_error, Toast.LENGTH_SHORT).show()
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(context, R.string.camera_denied, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        bottomBar = { Column(modifier = Modifier.navigationBarsPadding()) {
            AdMobBanner()
        } },
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.edit)) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->
        if (collectable != null) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Box con imagen + toggle favorito superpuesto
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    // 2) painterResource ya importado
                    Image(
                        painter = imageUri
                            ?.let { rememberAsyncImagePainter(it) }
                            ?: painterResource(id = R.drawable.default_image),
                        contentDescription = null,
                        modifier = Modifier.matchParentSize(),
                        contentScale = ContentScale.Crop
                    )
                    IconToggleButton(
                        checked = isFavorite,
                        onCheckedChange = { isFavorite = it },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .size(28.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f),
                                shape = CircleShape
                            )
                    ) {
                        /** 3) Icons.Filled.Favorite y FavoriteBorder ya importados **/
                        Icon(
                            imageVector = if (isFavorite)
                                Icons.Filled.Favorite
                            else
                                Icons.Filled.FavoriteBorder,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                // --- Botón Seleccionar Imagen ---
                Button(onClick = { showDialog = true }) {
                    Text(stringResource(R.string.image_select))
                }

                // Campos de texto
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(stringResource(R.string.title)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = subtitle,
                    onValueChange = { subtitle = it },
                    label = { Text(stringResource(R.string.subtitle)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = comments,
                    onValueChange = { comments = it },
                    label = { Text(stringResource(R.string.comments)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp) // Altura fija en lugar de heightIn
                        .bringIntoViewRequester(bringIntoViewRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                scope.launch {
                                    // Añadir delay para asegurar que el teclado aparezca primero
                                    kotlinx.coroutines.delay(300)
                                    bringIntoViewRequester.bringIntoView()
                                }
                            }
                        },
                    maxLines = 6,
                    minLines = 3, // Añadir líneas mínimas
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedBorderColor = MaterialTheme.colorScheme.primary
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        titleError = title.isBlank()
                        if (!titleError) {
                            HapticController.oneShot(context, 70L)
                            collectable?.let { orig ->
                                viewModel.updateCollectable(
                                    orig.copy(
                                        title = title,
                                        subtitle = subtitle.takeIf { it.isNotBlank() },
                                        comments = comments.takeIf { it.isNotBlank() },
                                        isFavorite = isFavorite,
                                        imageUri = imageUri?.toString()
                                    )
                                )
                                navController.popBackStack()
                            }
                        } else {
                            Toast.makeText(context, R.string.not_empty_title, Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(stringResource(R.string.save))
                }
            }
        }

        // --- Diálogo para elegir Cámara/Galería ---
        if (showDialog) {
            CameraDialog(
                title = stringResource(R.string.image_select),
                message = stringResource(R.string.cam_or_img),
                onTakePhoto = {
                    showDialog = false
                    when (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)) {
                        PackageManager.PERMISSION_GRANTED -> openCamera()
                        else -> cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                },
                onPickFromGallery = {
                    showDialog = false
                    pickImage()
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}

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

package com.eddevios.collections.ui.collection.components

data class CollectionItem(
val id: Int,
val title: String,
val subtitle: String,
val imageUri: String?,
val order: Int,
val createdAt: Long
)

package com.eddevios.collections.ui.collection.components // O donde prefieras ubicarlo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.eddevios.collections.R // Asegúrate que esta R es accesible
import com.eddevios.collections.ui.collection.viewmodel.CollectionViewModel
import com.eddevios.collections.ui.theme.CollectionsTheme // Tu tema de la app

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LargeImageCollectionItem(
collection: CollectionItem,
isEditing: Boolean,
onClick: () -> Unit,
onEdit: () -> Unit,
onDelete: () -> Unit,
modifier: Modifier = Modifier
) {
Card(
modifier = modifier
.fillMaxWidth() // Ocupa todo el ancho, sin distancias laterales para la Card.
.height(220.dp) // Altura considerablemente mayor, puedes ajustarla.
.clickable(enabled = !isEditing, onClick = onClick), // Permite click solo si no se está editando.
shape = RoundedCornerShape(16.dp), // Esquinas más redondeadas para un look moderno.
elevation = CardDefaults.cardElevation(defaultElevation = 6.dp) // Elevación para dar profundidad.
) {
Box(modifier = Modifier.fillMaxSize()) { // Box permite superponer elementos.
// Imagen de fondo con fuerte presencia visual.
val imageModifier = Modifier
.fillMaxSize()
.clip(RoundedCornerShape(16.dp)) // Asegura que la imagen también tenga bordes redondeados si sobresale.

            if (collection.imageUri != null) {
                GlideImage(
                    model = collection.imageUri,
                    contentDescription = collection.title, // Importante para accesibilidad.
                    modifier = imageModifier,
                    contentScale = ContentScale.Crop // Escala la imagen para llenar el espacio, recortando si es necesario.
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.default_image), // Imagen por defecto.
                    contentDescription = collection.title,
                    modifier = imageModifier,
                    contentScale = ContentScale.Crop
                )
            }

            // Gradiente oscuro en la parte inferior para asegurar legibilidad del texto.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                            startY = 300f // Ajusta este valor para controlar dónde empieza el gradiente.
                        )
                    )
            )

            // Título y subtítulo superpuestos en la parte inferior.
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart) // Alinea el texto abajo a la izquierda.
                    .fillMaxWidth()
                    .padding(16.dp) // Padding interno para el texto.
            ) {
                Text(
                    text = collection.title,
                    style = MaterialTheme.typography.headlineSmall.copy(color = Color.White), // Estilo para el título.
                    maxLines = 2, // Máximo dos líneas para el título.
                    overflow = TextOverflow.Ellipsis // Añade "..." si el texto es muy largo.
                )
                // Muestra el subtítulo solo si no está vacío.
                if (collection.subtitle.isNotBlank()) {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = collection.subtitle,
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White.copy(alpha = 0.9f)), // Estilo para el subtítulo.
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Iconos de editar/borrar si `isEditing` es true.
            if (isEditing) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd) // Alinea los iconos arriba a la derecha.
                        .padding(8.dp)
                        .background(
                            Color.Black.copy(alpha = 0.4f), // Fondo semitransparente para los iconos.
                            RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 4.dp, vertical = 2.dp), // Padding interno para los iconos.
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onEdit,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar",
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Borrar",
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }
        }
    }
}

// --- Preview para el nuevo componente ---
@Preview(name = "LargeImageCollectionItem - Modo Claro", showBackground = true)
@Preview(name = "LargeImageCollectionItem - Modo Oscuro", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun LargeImageCollectionItemPreview() {
val dummyCollection = CollectionItem(
id = 1,
title = "Mi Colección Increíble con un Título Muy Largo",
subtitle = "Un subtítulo descriptivo y moderno",
imageUri = null, // Puedes poner una URL de imagen de prueba aquí
order = 0,
createdAt = 0
)
CollectionsTheme(dynamicColor = false) {
Surface(color = MaterialTheme.colorScheme.background) {
Column(modifier = Modifier.padding(16.dp)) {
LargeImageCollectionItem(
collection = dummyCollection,
isEditing = true,
onClick = { },
onEdit = { },
onDelete = { }
)
Spacer(Modifier.height(16.dp))
LargeImageCollectionItem(
collection = dummyCollection.copy(
id=2,
title = "Otra Colección",
subtitle = "" // Prueba sin subtítulo
),
isEditing = false,
onClick = { },
onEdit = { },
onDelete = { }
)
}
}
}
}

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

package com.eddevios.collections.ui.collection.screen

import android.Manifest
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eddevios.collections.R
import android.content.pm.PackageManager
import android.widget.Toast
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import com.eddevios.collections.ui.collection.viewmodel.CollectionViewModel
import androidx.compose.ui.res.stringResource
import com.eddevios.collections.ads.AdMobBanner
import com.eddevios.collections.utils.CameraDialog
import com.eddevios.collections.utils.HapticController
import androidx.hilt.navigation.compose.hiltViewModel
import com.eddevios.collections.features.common.utils.ImageProcessor
import com.eddevios.collections.features.common.utils.rememberImagePicker
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCollectionScreen(
navController: NavController,
viewModel: CollectionViewModel = hiltViewModel()
) {
var title by remember { mutableStateOf("") }
var subtitle by remember { mutableStateOf("") }
var imageUri by remember { mutableStateOf<Uri?>(null) }
var isError by remember { mutableStateOf(false) }
var showDialog by remember { mutableStateOf(false) }
var photoUri by remember { mutableStateOf<Uri?>(null) }
val focusRequester = remember { FocusRequester() }
val context = LocalContext.current
val imageProcessor = remember { ImageProcessor(context) }
val packageName = context.packageName
val pickImage = rememberImagePicker(
onImageReady = { processedUri -> imageUri = processedUri },
imageProcessor = imageProcessor
)

    // Lanzador para la cámara
    val scope = rememberCoroutineScope()

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri != null) {
            scope.launch {
                imageUri = imageProcessor.process(photoUri!!)
            }
        }
    }

    fun openCamera() {
        val fileName = "temp_image_${System.currentTimeMillis()}.jpg"
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
        photoUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
        cameraLauncher.launch(photoUri!!)
    }

    // Lanzador para permisos de cámara
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(context, R.string.camera_denied, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        bottomBar = { Column(modifier = Modifier.navigationBarsPadding()) {
            AdMobBanner()
        } },
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.create_collection)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues).padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Mostrar imagen seleccionada o un placeholder
                if (imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Imagen seleccionada",
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painterResource(id = R.drawable.default_image),
                        contentDescription = null,
                        modifier = Modifier.size(180.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                // Botón para abrir el selector de imágenes
                Button(onClick = {
                    showDialog = true }) {
                    Text(stringResource(R.string.image_select))
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = title,
                    onValueChange = {
                        title = it
                        isError = title.isEmpty()
                    },
                    label = { Text(stringResource(R.string.name_collection)) },
                    isError = isError,
                    placeholder = { Text(stringResource(R.string.title)) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .heightIn(min = 40.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                // No solicitar el teclado aquí evita que se abra automáticamente
                            }
                        },
                    shape = CircleShape,
                    textStyle = MaterialTheme.typography.bodyMedium.copy( // Tamaño de texto reducido
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedPlaceholderColor = Color.LightGray,
                        cursorColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                if (isError) {
                    Text(
                        text = stringResource(R.string.not_empty_title),
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                TextField(
                    value = subtitle,
                    onValueChange = { subtitle = it },
                    label = { Text(stringResource(R.string.subtitle)) },
                    placeholder = { Text(stringResource(R.string.subtitle) + "...") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .heightIn(min = 40.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                // No solicitar el teclado aquí evita que se abra automáticamente
                            }
                        },
                    shape = CircleShape,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedPlaceholderColor = Color.LightGray,
                        cursorColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedIndicatorColor = Color.Transparent, // Sin línea inferior al estar enfocado
                        unfocusedIndicatorColor = Color.Transparent // Sin línea inferior al no estar enfocado
                    )
                )

                // Diálogo para elegir entre cámara o galería
                if (showDialog) {
                    CameraDialog(
                        title = stringResource(R.string.image_select),
                        message = stringResource(R.string.cam_or_img),
                        onTakePhoto = {
                            showDialog = false
                            when (ContextCompat.checkSelfPermission(
                                context, Manifest.permission.CAMERA
                            )) {
                                PackageManager.PERMISSION_GRANTED -> openCamera()
                                else -> cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        },
                        onPickFromGallery = {
                            showDialog = false
                            pickImage()
                        },
                        onDismiss = { showDialog = false }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        if (title.isNotEmpty()) {
                            HapticController.oneShot(context, 70L)
                            viewModel.addCollection(title, subtitle, imageUri, packageName)
                            navController.navigateUp()
                        } else { isError = true }
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                ) {
                    Text(stringResource(R.string.save))
                }
            }
        }
    )
}

package com.eddevios.collections.ui.collection.screen

import androidx.compose.material3.Scaffold
import android.Manifest
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eddevios.collections.R
import android.content.pm.PackageManager
import android.widget.Toast
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import com.eddevios.collections.ui.collection.viewmodel.CollectionViewModel
import androidx.compose.ui.res.stringResource
import com.eddevios.collections.ads.AdMobBanner
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import com.eddevios.collections.utils.HapticController
import com.eddevios.collections.utils.CameraDialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.eddevios.collections.features.common.utils.ImageProcessor
import com.eddevios.collections.features.common.utils.rememberImagePicker
import kotlinx.coroutines.launch
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCollectionScreen(
navController: NavController,
collectionId: Int,
viewModel: CollectionViewModel = hiltViewModel()
) {

    var isError by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current
    val packageName = context.packageName

    // 1) Recoge la lista completa y filtra la colección que te interesa
    val collections by viewModel.collections.collectAsState(initial = emptyList())  // [1]
    val collection = collections.firstOrNull { it.id == collectionId }

    // 2) Estados locales para los campos de UI (guardan título, subtítulo e imagen)
    var title by rememberSaveable { mutableStateOf("") }
    var subtitle by rememberSaveable { mutableStateOf("") }
    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }

    val imageProcessor = remember { ImageProcessor(context) }

    val pickImage = rememberImagePicker(
        imageProcessor = imageProcessor,
        onImageReady = { uri -> imageUri = uri }
    )

    // 3) Cuando 'collection' cambie, inicializa los estados
    LaunchedEffect(collection) {
        collection?.let {
            title = it.title
            subtitle = it.subtitle
            imageUri = it.imageUri?.toUri()
        }
    }

    // Lanzador para la cámara
    val scope = rememberCoroutineScope()

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri != null) {
            scope.launch {
                imageUri = imageProcessor.process(photoUri!!)
            }
        }
    }

    fun openCamera() {
        val fileName = "temp_image_${System.currentTimeMillis()}.jpg"
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
        photoUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
        cameraLauncher.launch(photoUri!!)
    }

    // Lanzador para permisos de cámara
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(context, R.string.camera_denied, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        bottomBar = { Column(modifier = Modifier.navigationBarsPadding()) {
            AdMobBanner()
        } },
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.edit)) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues).padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Mostrar imagen seleccionada o un placeholder
                if (imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Imagen seleccionada",
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painterResource(id = R.drawable.default_image),
                        contentDescription = null,
                        modifier = Modifier.size(180.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                // Botón para abrir el selector de imágenes
                Button(onClick = { showDialog = true }) {
                    Text(stringResource(R.string.image_select))
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = title,
                    onValueChange = {
                        title = it
                        isError = title.isEmpty()
                    },
                    label = { Text(stringResource(R.string.name_collection)) },
                    isError = isError,
                    placeholder = { Text(stringResource(R.string.title)) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .heightIn(min = 40.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                // No solicitar el teclado aquí evita que se abra automáticamente
                            }
                        },
                    shape = CircleShape,
                    textStyle = MaterialTheme.typography.bodyMedium.copy( // Tamaño de texto reducido
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedPlaceholderColor = Color.LightGray,
                        cursorColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                if (isError) {
                    Text(
                        text = stringResource(R.string.not_empty_title),
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                TextField(
                    value = subtitle,
                    onValueChange = { subtitle = it },
                    label = { Text(stringResource(R.string.subtitle)) },
                    placeholder = { Text(stringResource(R.string.subtitle) + "...") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .heightIn(min = 40.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                // No solicitar el teclado aquí evita que se abra automáticamente
                            }
                        },
                    shape = CircleShape,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedPlaceholderColor = Color.LightGray,
                        cursorColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedIndicatorColor = Color.Transparent, // Sin línea inferior al estar enfocado
                        unfocusedIndicatorColor = Color.Transparent // Sin línea inferior al no estar enfocado
                    )
                )

                // Diálogo para elegir entre cámara o galería
                if (showDialog) {
                    CameraDialog(
                        title = stringResource(R.string.image_select),
                        message = stringResource(R.string.cam_or_img),
                        onTakePhoto = {
                            showDialog = false
                            when (ContextCompat.checkSelfPermission(
                                context, Manifest.permission.CAMERA
                            )) {
                                PackageManager.PERMISSION_GRANTED -> openCamera()
                                else -> cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        },
                        onPickFromGallery = {
                            showDialog = false
                            pickImage()
                        },
                        onDismiss = { showDialog = false }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        if (title.isNotEmpty()) {
                            HapticController.oneShot(context, 70L)
                            viewModel.updateCollection(
                                id = collectionId,
                                title = title,
                                subtitle = subtitle,
                                imageUri = imageUri,
                                packageName = packageName
                            )
                            navController.navigateUp()
                        } else {
                            isError = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                ) {
                    Text(stringResource(R.string.save))
                }
            }
        }
    )
}

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

package com.eddevios.collections.ui.navigation

object Routes {
const val COLLECTIONS = "collections"
const val CREATE_COLLECTION = "create_collection"
const val EDIT_COLLECTION = "edit_collection/{collectionId}"
const val CATEGORY = "category/{collectionId}"
const val CREATE_CATEGORY = "create_category/{collectionId}"
const val EDIT_CATEGORY = "edit_category/{categoryId}"
const val COLLECTABLE = "collectable/{categoryId}"
const val CREATE_COLLECTABLE = "create_collectable/{categoryId}"
const val COLLECTABLE_DETAIL = "collectable_detail/{collectableId}"
const val EDIT_COLLECTABLE = "edit_collectable/{collectableId}"
}

package com.eddevios.collections.ui.theme

import androidx.compose.ui.graphics.Color

// Paleta de referencia
val Purple80               = Color(0xFFD0BCFF) // Lavanda claro
val PurpleGrey80           = Color(0xFFCCC2DC) // Gris lavanda
val Pink80                 = Color(0xFFEFB8C8) // Rosa pálido

val Purple40               = Color(0xFF6650A4) // Púrpura medio
val PurpleGrey40           = Color(0xFF625B71) // Lavanda oscura
val Pink40                 = Color(0xFF7D5260) // Granate

// Colores principales para modo oscuro
val DarkPrimary            = Color(0xFF121212) // Negro intenso
val DarkSecondary          = Color(0xFF1E1E1E) // Gris antracita
val AccentColor            = Color(0xFF111318) // Verde bosque oscuro
val TextColorLight         = Color(0xFFFFFFFF) // Blanco puro
val TextColorMuted         = Color(0xFF9B9999) // Gris claro

// Colores claros (opcional)
val LightPrimary           = Color(0xFFFFFFFF) // Blanco puro
val LightSecondary         = Color(0xFFF5F5F5) // Blanco humo
val LightAccentColor       = Color(0xFFC7C6C6) // Gris plata
val TextColorDark          = Color(0xFF000000) // Negro puro

// Colores comunes
val TextPlaceholderColor   = Color(0xFF706B6B) // Gris pizarra

// Azul principal (Material Design Blue 600)
val ModernBlueLight       = Color(0xFF1E88E5) // Azul vivo, claro y moderno
val ModernBlueDark        = Color(0xFF1565C0) // Azul oscuro elegante
val ModernBlueContainer   = Color(0xFF90CAF9) // Azul pastel para containers

// Azul claro para secundarios
val ModernBlueSecondary           = Color(0xFF90D5FF) // Azul claro, fresco y moderno [2]
val ModernBlueSecondaryContainer  = Color(0xFFD6F0FF) // Azul pastel, muy suave
val ModernBlueOnSecondary         = Color(0xFF00344F) // Azul oscuro para contraste

// Paleta light (Material You) - Cambia solo los primarios
val md_light_primary            = ModernBlueLight
val md_light_onPrimary          = Color(0xFFFFFFFF)
val md_light_primaryContainer   = ModernBlueContainer
val md_light_onPrimaryContainer = Color(0xFF001E36) // Azul muy oscuro para contraste

// Paleta light (Material You) - secundarios
val md_light_secondary             = ModernBlueSecondary
val md_light_onSecondary           = ModernBlueOnSecondary
val md_light_secondaryContainer    = ModernBlueSecondaryContainer
val md_light_onSecondaryContainer  = ModernBlueOnSecondary

val md_light_tertiary              = Color(0xFF386A96) // Azul acero
val md_light_onTertiary            = Color(0xFFFFFFFF) // Blanco puro
val md_light_tertiaryContainer     = Color(0xFFBFD8FF) // Azul pastel
val md_light_onTertiaryContainer   = Color(0xFF001E35) // Azul noche

val md_light_error                 = Color(0xFFBA1A1A) // Rojo óxido
val md_light_onError               = Color(0xFFFFFFFF) // Blanco puro
val md_light_errorContainer        = Color(0xFFFFDAD6) // Rosa pálido
val md_light_onErrorContainer      = Color(0xFF410002) // Rojo oscuro

val md_light_background            = Color(0xFFFDFDFD) // Blanco casi puro
val md_light_onBackground          = Color(0xFF1A1C1E) // Negro éreo
val md_light_surface               = Color(0xFFFDFDFD) // Blanco casi puro
val md_light_onSurface             = Color(0xFF1A1C1E) // Negro éreo

val md_light_surfaceVariant        = Color(0xFFE7E0EC) // Lavanda muy claro
val md_light_onSurfaceVariant      = Color(0xFF49454F) // Gris oscuro
val md_light_outline               = Color(0xFF7A757F) // Gris plata

// Paleta dark (Material You)
val md_dark_primary             = ModernBlueContainer
val md_dark_onPrimary           = Color(0xFF002F6C)
val md_dark_primaryContainer    = ModernBlueDark
val md_dark_onPrimaryContainer  = Color(0xFF90CAF9)

// Paleta dark (Material You) - secundarios
val md_dark_secondary              = ModernBlueSecondary
val md_dark_onSecondary            = ModernBlueOnSecondary
val md_dark_secondaryContainer     = ModernBlueSecondaryContainer
val md_dark_onSecondaryContainer   = ModernBlueOnSecondary

val md_dark_tertiary               = Color(0xFF9FC9FF) // Azul cielo claro
val md_dark_onTertiary             = Color(0xFF003257) // Azul muy oscuro
val md_dark_tertiaryContainer      = Color(0xFF1E4773) // Azul pizarra oscuro
val md_dark_onTertiaryContainer    = Color(0xFFBFD8FF) // Azul pastel

val md_dark_error                  = Color(0xFFFFB4AB) // Salmón claro
val md_dark_onError                = Color(0xFF690005) // Rojo oscuro
val md_dark_errorContainer         = Color(0xFF93000A) // Rojo sangre
val md_dark_onErrorContainer       = Color(0xFFFFDAD6) // Rosa pálido

val md_dark_background             = Color(0xFF1A1C1E) // Negro éreo
val md_dark_onBackground           = Color(0xFFE3E2E6) // Gris lavanda
val md_dark_surface                = Color(0xFF1A1C1E) // Negro éreo
val md_dark_onSurface              = Color(0xFFE3E2E6) // Gris lavanda

val md_dark_surfaceVariant         = Color(0xFF49454F) // Gris oscuro
val md_dark_onSurfaceVariant       = Color(0xFFCAC4D0) // Gris lavanda pálido
val md_dark_outline                = Color(0xFF938F99) // Gris medio

package com.eddevios.collections.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Colores específicos para el look de iOS
private val iOSLightBackground = Color(0xFFF2F2F7)  // Fondo gris claro de iOS
private val iOSLightSurface = Color(0xFFFFFFFF)     // Blanco puro para cards

private val iOSDarkBackground = Color(0xFF000000)    // Negro puro de iOS
private val iOSDarkSurface = Color(0xFF1C1C1E)      // Gris oscuro para cards en modo oscuro

private val LightColors = lightColorScheme(
primary = md_light_primary,
onPrimary = md_light_onPrimary,
primaryContainer = md_light_primaryContainer,
onPrimaryContainer = md_light_onPrimaryContainer,
secondary = md_light_secondary,
onSecondary = md_light_onSecondary,
secondaryContainer = md_light_secondaryContainer,
onSecondaryContainer = md_light_onSecondaryContainer,
tertiary = md_light_tertiary,
onTertiary = md_light_onTertiary,
tertiaryContainer = md_light_tertiaryContainer,
onTertiaryContainer = md_light_onTertiaryContainer,
error = md_light_error,
onError = md_light_onError,
errorContainer = md_light_errorContainer,
onErrorContainer = md_light_onErrorContainer,
//background = md_light_background,
onBackground = md_light_onBackground,
//surface = md_light_surface,
onSurface = md_light_onSurface,
//surfaceVariant = md_light_surfaceVariant,
onSurfaceVariant = md_light_onSurfaceVariant,
outline = md_light_outline,
background = iOSLightBackground,        // Fondo principal
surface = iOSLightSurface,             // Cards/superficies
surfaceVariant = iOSLightBackground,   // Variante del fondo
)

private val DarkColors = darkColorScheme(
primary = md_dark_primary,
onPrimary = md_dark_onPrimary,
primaryContainer = md_dark_primaryContainer,
onPrimaryContainer = md_dark_onPrimaryContainer,
secondary = md_dark_secondary,
onSecondary = md_dark_onSecondary,
secondaryContainer = md_dark_secondaryContainer,
onSecondaryContainer = md_dark_onSecondaryContainer,
tertiary = md_dark_tertiary,
onTertiary = md_dark_onTertiary,
tertiaryContainer = md_dark_tertiaryContainer,
onTertiaryContainer = md_dark_onTertiaryContainer,
error = md_dark_error,
onError = md_dark_onError,
errorContainer = md_dark_errorContainer,
onErrorContainer = md_dark_onErrorContainer,
//background = md_dark_background,
onBackground = md_dark_onBackground,
//surface = md_dark_surface,
onSurface = md_dark_onSurface,
//surfaceVariant = md_dark_surfaceVariant,
onSurfaceVariant = md_dark_onSurfaceVariant,
outline = md_dark_outline,
background = iOSDarkBackground,         // Fondo principal oscuro
surface = iOSDarkSurface,              // Cards oscuras
surfaceVariant = iOSDarkBackground,    // Variante del fondo oscuro
)

@Composable
fun CollectionsTheme(
darkTheme: Boolean = isSystemInDarkTheme(),
dynamicColor: Boolean = false,
content: @Composable () -> Unit
) {
val scheme = when {
dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
val context = LocalContext.current
if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
}
darkTheme -> DarkColors
else      -> LightColors
}

    MaterialTheme(
        colorScheme = scheme,
        typography = Typography,
        content = content
    )
}

package com.eddevios.collections.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
titleMedium = TextStyle(
fontFamily  = FontFamily.Default,
fontWeight  = FontWeight.Bold,
fontSize    = 18.sp
// color no se fija aquí
),
bodyMedium = TextStyle(
fontFamily  = FontFamily.Default,
fontWeight  = FontWeight.Normal,
fontSize    = 14.sp
)
)

package com.eddevios.collections.utils

object AppConfig {
// URL base de la API
const val API_BASE_URL = "https://api.eddevios.com/"
const val WEB_URL = "https://eddevios.com/"
    const val API_KEY="tu_api_key_de_pruebas"

    // Timeouts para las llamadas a la API (en segundos)
    const val API_CONNECT_TIMEOUT = 30L
    const val API_READ_TIMEOUT = 30L
    const val API_WRITE_TIMEOUT = 30L

    // Estos valores se inyectan desde local.properties vía Gradle para seguridad
    val ONESIGNAL_ID = BuildConfig.ONESIGNAL_ID
    val BANNER_AD_ID = BuildConfig.BANNER_AD_ID
    val INTERSTITIAL_AD_ID = BuildConfig.INTERSTITIAL_AD_ID
    val API_KEY = BuildConfig.API_KEY
}

package com.eddevios.collections.utils

import androidx.compose.runtime.*

@Composable
fun rememberDebouncedInput(
input: String,
delayMillis: Long = 300L
): String {
var debouncedInput by remember { mutableStateOf(input) }
LaunchedEffect(input) {
kotlinx.coroutines.delay(delayMillis)
debouncedInput = input
}
return debouncedInput
}

package com.eddevios.collections.utils

import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CrashlyticsLogger @Inject constructor() {

    private val crashlytics = FirebaseCrashlytics.getInstance()

    /**
     * Registra una excepción no fatal. Útil para errores controlados
     * en bloques try-catch que no deben cerrar la app.
     */
    fun logNonFatal(exception: Throwable) {
        crashlytics.recordException(exception)
    }

    /**
     * Añade una clave-valor personalizada al siguiente reporte de crash.
     * Útil para saber el estado de la app en el momento del error.
     */
    fun setCustomKey(key: String, value: String) {
        crashlytics.setCustomKey(key, value)
    }

    /**
     * Asocia un identificador de usuario a los reportes de crash.
     * Ayuda a saber cuántos usuarios afecta un mismo error.
     */
    fun setUserId(id: String) {
        crashlytics.setUserId(id)
    }
}

package com.eddevios.collections.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.eddevios.collections.R
import com.eddevios.collections.ui.category.components.CategoryItem
import com.eddevios.collections.ui.collectable.components.CollectableItem
import com.eddevios.collections.ui.collection.components.CollectionItem

import com.eddevios.collections.utils.HapticController

@Composable
fun ConfirmDeleteDialog(
title: String,
message: String = stringResource(R.string.sure),
confirmText: String = stringResource(R.string.delete),
cancelText: String = stringResource(R.string.cancel),
confirmColor: Color = Color.Red,
onConfirm: () -> Unit,
onDismiss: () -> Unit
) {
AlertDialog(
onDismissRequest = onDismiss,
title           = { Text(title) },
text            = { Text(message) },
confirmButton   = {
TextButton(onClick = onConfirm) {
Text(confirmText, color = confirmColor)
}
},
dismissButton   = {
TextButton(onClick = onDismiss) {
Text(cancelText)
}
}
)
}

@Composable
fun CameraDialog(
title: String,
message: String,
onTakePhoto: () -> Unit,
onPickFromGallery: () -> Unit,
onDismiss: () -> Unit
) {
val context = LocalContext.current
AlertDialog(
onDismissRequest = onDismiss,
title = { Text(title) },
text = { Text(message) },
confirmButton = {
HapticController.oneShot(context, 70L)
TextButton(onClick = onTakePhoto) {
Text(stringResource(R.string.camera))
}
},
dismissButton = {
TextButton(onClick = onPickFromGallery) {
Text(stringResource(R.string.gallery))
}
}
)
}

package com.eddevios.collections.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import com.eddevios.collections.R
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource

@Composable
fun EmptyStateView(
modifier: Modifier = Modifier,
iconResId: Int = R.drawable.square_stack_3d_up_slash,
primaryText: String,
secondaryText: String,
buttonText: String,
onButtonClick: () -> Unit
) {
Column(
modifier = modifier
.fillMaxSize()
.padding(32.dp),
verticalArrangement = Arrangement.Center,
horizontalAlignment = Alignment.CenterHorizontally
) {
Icon(
painter = painterResource(id = iconResId),

            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .size(64.dp)
                .clickable(onClick = onButtonClick)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            primaryText,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(8.dp))
        Text(
            secondaryText,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(24.dp))
        Button(onClick = onButtonClick) {
            Text(buttonText)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyCollectionsViewPreview() {
EmptyStateView(
iconResId = R.drawable.square_stack_3d_up_slash,
primaryText   = stringResource(R.string.no_collections_title),
secondaryText = stringResource(R.string.no_collections_title),
buttonText    = stringResource(R.string.create_collection),
onButtonClick = { /* Acción de ejemplo */ }
)
}

package com.eddevios.collections.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

object HapticController {

    /* ---------- helpers ---------- */

    private fun vibrator(context: Context): Vibrator? =
        context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator

    fun isSupported(context: Context): Boolean =
        vibrator(context)?.hasVibrator() == true

    /* ---------- disparo simple ---------- */

    fun oneShot(
        context: Context,
        duration: Long = 70L,
        amplitude: Int = VibrationEffect.DEFAULT_AMPLITUDE
    ) {
        vibrator(context)?.let { v ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(duration, amplitude))
            } else {
                @Suppress("DEPRECATION")
                v.vibrate(duration)
            }
        }
    }

    /* ---------- patrón genérico ---------- */

    fun vibratePattern(
        context: Context,
        pattern: LongArray,
        repeat: Int = -1
    ) {
        vibrator(context)?.let { v ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createWaveform(pattern, repeat))
            } else {
                @Suppress("DEPRECATION")
                v.vibrate(pattern, repeat)
            }
        }
    }

    /* ---------- patrones enriquecidos ---------- */

    fun rampAndHit(context: Context) {
        val timings = longArrayOf(0,10,20,30,40,50,60,70,80,90,100, 300, 1000)
        val amplitudes = intArrayOf(0,25,50,75,100,125,150,175,200,225,255, 255,0)
        vibrator(context)?.let { v ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createWaveform(timings, amplitudes, -1))
            } else {
                @Suppress("DEPRECATION") v.vibrate(timings, -1)
            }
        }
    }

    fun slowRiseAndClick(context: Context) {
        vibrator(context)?.let { v ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val effect = VibrationEffect
                    .startComposition()
                    .addPrimitive(VibrationEffect.Composition.PRIMITIVE_SLOW_RISE, 1f)
                    .addPrimitive(VibrationEffect.Composition.PRIMITIVE_CLICK, 1f)
                    .compose()
                v.vibrate(effect)
            } else {
                rampAndHit(context)              // fallback pre-API 30
            }
        }
    }
}

package com.eddevios.collections.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun HighlightedText(text: String, query: String) {
val startIndex = text.indexOf(query, ignoreCase = true)
if (startIndex != -1) {
val endIndex = startIndex + query.length
Text(buildAnnotatedString {
append(text.substring(0, startIndex))
withStyle(style = SpanStyle(color = Color.Red)) {
append(text.substring(startIndex, endIndex))
}
append(text.substring(endIndex))
})
} else {
Text(text)
}
}

//CONFIGURATION APP
//build.gradle.kts(Project)
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
alias(libs.plugins.android.application) apply false
alias(libs.plugins.kotlin.android) apply false
alias(libs.plugins.kotlin.compose) apply false
alias(libs.plugins.hilt) apply false
}

//build.gradle.kts(App)
plugins {
alias(libs.plugins.android.application)
alias(libs.plugins.kotlin.android)
alias(libs.plugins.kotlin.compose)
id("kotlin-kapt")
alias(libs.plugins.google.services)
alias(libs.plugins.firebase.crashlytics)
alias(libs.plugins.hilt)
}

android {
namespace = "com.eddevios.collections"
compileSdk = 35

    defaultConfig {
        applicationId = "com.eddevios.collections"
        minSdk = 24
        targetSdk = 35
        versionCode = 20
        versionName = "1.0.8"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            // IDs de TEST oficiales de Google
            buildConfigField("String", "BANNER_AD_ID", "\"ca-app-pub-3940256099942544/6300978111\"")
            buildConfigField("String", "INTERSTITIAL_AD_ID", "\"ca-app-pub-3940256099942544/1033173712\"")
            buildConfigField("String", "ONESIGNAL_ID", "\"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\"")
            buildConfigField("String", "API_KEY", "\"debug_key\"")
            manifestPlaceholders["admobAppId"] = "ca-app-pub-3940256099942544~3347511713"
        }
        release {
            isMinifyEnabled = true
            // IDs REALES leídos de local.properties (inyectados vía Gradle)
            buildConfigField("String", "BANNER_AD_ID", "\"${localProperties.getProperty("ADMOB_BANNER_ID")}\"")
            buildConfigField("String", "INTERSTITIAL_AD_ID", "\"${localProperties.getProperty("ADMOB_INTERSTITIAL_ID")}\"")
            buildConfigField("String", "ONESIGNAL_ID", "\"${localProperties.getProperty("ONESIGNAL_ID")}\"")
            buildConfigField("String", "API_KEY", "\"${localProperties.getProperty("API_KEY")}\"")
            manifestPlaceholders["admobAppId"] = localProperties.getProperty("ADMOB_APPLICATION_ID") ?: ""
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.navigation.compose)
    implementation(libs.onesignal)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.play.services.ads)
    implementation(libs.reorderable)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.androidx.core)
    kapt(libs.room.compiler)
    implementation(libs.postgrest.kt)
    implementation(libs.gotrue.kt)
    implementation(libs.ktor.client.android)
    // ►►► INYECCIÓN DE DEPENDENCIAS: (tipo: Dependency)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
    implementation(libs.glide.compose)
    implementation(libs.coil.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

//proguard-rules.pro
# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Firebase Crashlytics
-keep class com.google.firebase.crashlytics.** { *; }
-keep class com.google.android.datatransport.** { *; }
-keep class com.google.firebase.messaging.** { *; }


//gradle.properties
# Project-wide Gradle settings.
# IDE (e.g. Android Studio) users:
# Gradle settings configured through the IDE *will override*
# any settings specified in this file.
# For more details on how to configure your build environment visit
# http://www.gradle.org/docs/current/userguide/build_environment.html
# Specifies the JVM arguments used for the daemon process.
# The setting is particularly useful for tweaking memory settings.
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
# When configured, Gradle will run in incubating parallel mode.
# This option should only be used with decoupled projects. For more details, visit
# https://developer.android.com/r/tools/gradle-multi-project-decoupled-projects
# org.gradle.parallel=true
# AndroidX package structure to make it clearer which packages are bundled with the
# Android operating system, and which are packaged with your app's APK
# https://developer.android.com/topic/libraries/support-library/androidx-rn
android.useAndroidX=true
# Kotlin code style for this project: "official" or "obsolete":
kotlin.code.style=official
# Enables namespacing of each library's R class so that its R class includes only the
# resources declared in the library itself and none from the library's dependencies,
# thereby reducing the size of the R class for that library
android.nonTransitiveRClass=true

//gradle-wrapper.properties
#Fri Mar 28 20:54:34 CET 2025
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.13-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists

//libs.version.toml
[versions]
agp = "8.13.0"
coilCompose = "2.6.0"
firebaseBom = "33.12.0"
gotrueKt = "2.2.1"
kotlin = "2.0.21"
coreKtx = "1.16.0"
junit = "4.13.2"
junitVersion = "1.2.1"
espressoCore = "3.6.1"
ktorClientAndroid = "2.3.9"
lifecycleRuntimeKtx = "2.9.1"
activityCompose = "1.10.1"
composeBom = "2024.06.00"
navigationCompose = "2.9.0"
onesignal = "5.1.31"
postgrestKt = "2.2.1"
reorderable = "0.9.6"
room = "2.7.1"
hiltAndroid = "2.51.1"
hiltNavigationCompose = "1.2.0"
glideCompose = "1.0.0-beta01"
playServicesAds = "24.3.0"
firebaseCrashlyticsPlugin = "3.0.4"
googleServices = "4.4.2"

[libraries]
androidx-core = { module = "androidx.core:core", version.ref = "coreKtx" }
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
coil-compose = { module = "io.coil-kt:coil-compose", version.ref = "coilCompose" }
firebase-bom = { module = "com.google.firebase:firebase-bom", version.ref = "firebaseBom" }
gotrue-kt = { module = "io.github.jan-tennert.supabase:gotrue-kt", version.ref = "gotrueKt" }
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
androidx-ui = { group = "androidx.compose.ui", name = "ui" }
androidx-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
androidx-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
androidx-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
androidx-ui-test-manifest = { group = "androidx.compose.ui", name = "ui-test-manifest" }
androidx-ui-test-junit4 = { group = "androidx.compose.ui", name = "ui-test-junit4" }
androidx-material3 = { group = "androidx.compose.material3", name = "material3" }
androidx-material-icons-core = { group = "androidx.compose.material", name = "material-icons-core" }
androidx-material-icons-extended = { group = "androidx.compose.material", name = "material-icons-extended" }
ktor-client-android = { module = "io.ktor:ktor-client-android", version.ref = "ktorClientAndroid" }
navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigationCompose" }
onesignal = { module = "com.onesignal:OneSignal", version.ref = "onesignal" }
play-services-ads = { module = "com.google.android.gms:play-services-ads", version.ref = "playServicesAds" }
postgrest-kt = { module = "io.github.jan-tennert.supabase:postgrest-kt", version.ref = "postgrestKt" }
reorderable = { module = "org.burnoutcrew.composereorderable:reorderable", version.ref = "reorderable" }
room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "room" }
room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "room" }
hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hiltAndroid" }
hilt-navigation-compose = { module = "androidx.hilt:hilt-navigation-compose", version.ref = "hiltNavigationCompose" }
hilt-compiler = { group = "com.google.dagger", name = "hilt-android-compiler", version.ref = "hiltAndroid" }
glide-compose = { group = "com.github.bumptech.glide", name = "compose", version.ref = "glideCompose" }
firebase-crashlytics = { module = "com.google.firebase:firebase-crashlytics-ktx" }
firebase-analytics = { module = "com.google.firebase:firebase-analytics-ktx" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
google-services = { id = "com.google.gms.google-services", version.ref = "googleServices" }
firebase-crashlytics = { id = "com.google.firebase.crashlytics", version.ref = "firebaseCrashlyticsPlugin" }
hilt = { id = "com.google.dagger.hilt.android", version.ref = "hiltAndroid" }

//local.properties
## This file is automatically generated by Android Studio.
# Do not modify this file -- YOUR CHANGES WILL BE ERASED!
#
# This file should *NOT* be checked into Version Control Systems,
# as it contains information specific to your local configuration.
#
# Location of the SDK. This is only used by Gradle.
# For customization when using a Version Control System, please read the
# header note.
sdk.dir=C\:\\Users\\zerom\\AppData\\Local\\Android\\Sdk

//settings.gradle.kts
pluginManagement {
repositories {
google {
content {
includeGroupByRegex("com\\.android.*")
includeGroupByRegex("com\\.google.*")
includeGroupByRegex("androidx.*")
}
}
mavenCentral()
gradlePluginPortal()
}
}
dependencyResolutionManagement {
repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
repositories {
google()
mavenCentral()
}
}

rootProject.name = "Collections"
include(":app")
 




