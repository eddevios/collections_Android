## 📁 Estructura del Proyecto

```text

com.eddevios.collections
│
├── MainActivity.kt             // Punto de entrada principal de la app, configura Compose y el tema
│
├── CollectionsApp.kt           // Aplicación principal
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
*   *Gestión de imágenes: Permite a los usuarios adjuntar imágenes a los coleccionables y colecciones utilizando el Photo Picker de Android (sin necesidad de permisos de almacenamiento).
*   *Publicidad (AdMob): Genera ingresos a través de banners e interstitials.
*   *Solicitud de permisos: Acceso a la cámara para tomar fotos directamente.

Añadir coleccionables: Agregar elementos individuales a las colecciones, cada uno con sus propios detalles.
## ✨ Características Principales

*   **Gestión Completa de Colecciones:** Crea, visualiza, edita y elimina colecciones personalizadas.
*   **Organización por Categorías:** Agrupa y filtra tus colecciones mediante categorías para una mejor organización.
*   **Elementos Coleccionables Detallados:** Añade ítems individuales a tus colecciones, con campos específicos y la capacidad de adjuntar imágenes de forma segura mediante el Photo Picker del sistema o la cámara.
*   **Persistencia Local Robusta:** Todos los datos se guardan de forma segura en el dispositivo utilizando la base de datos Room, permitiendo el acceso offline.
*   **Interfaz de Usuario Moderna:** Desarrollada íntegramente con Jetpack Compose, ofreciendo una experiencia de usuario fluida, reactiva y visualmente atractiva.
*   **Navegación Intuitiva:** Flujo de navegación claro entre las pantallas de colecciones, categorías (si aplica como pantalla separada) y coleccionables.
*   **Gestión de Privacidad:** Uso de APIs modernas (Photo Picker) para evitar solicitar permisos innecesarios de acceso a toda la galería del usuario.
*   **Monetización Integrada:** Incluye publicidad a través de AdMob (banners e intersticiales) para soportar el desarrollo de la aplicación.
*   **Componentes Reutilizables:** Estructura modular con componentes de UI y utilidades comunes para un desarrollo y mantenimiento más sencillos.

## 🛠️ Configuración y Seguridad

Este proyecto utiliza un sistema de inyección de claves mediante `local.properties` para mantener los IDs sensibles fuera del control de versiones (Git). 

### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/game-collections.git
```

### 2. Configurar `local.properties`
Crea un archivo llamado `local.properties` en la raíz del proyecto (este archivo está ignorado por Git por seguridad) y añade las siguientes claves con tus IDs reales:

```properties
# IDs de Producción
ADMOB_APPLICATION_ID=ca-app-pub-xxxxxxxxxxxxxxxx~xxxxxxxxxx
ADMOB_BANNER_ID=ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxx
ADMOB_INTERSTITIAL_ID=ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxx
ONESIGNAL_ID=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
API_KEY=tu_clave_de_api_aqui
```
*(Nota: Sustituye las `x` por tus claves reales obtenidas de las consolas de AdMob y OneSignal)*

### 3. Configurar Firebase
Para que las funcionalidades de Firebase (Analytics, Crashlytics) funcionen:
1.  Crea un proyecto en la [Consola de Firebase](https://console.firebase.google.com/).
2.  Añade una aplicación Android con el package name `com.eddevios.collections`.
3.  Descarga el archivo `google-services.json`.
4.  Colócalo en la carpeta `app/` de este proyecto (`app/google-services.json`).

### 4. Build Types
El proyecto está configurado para manejar automáticamente los entornos:
*   **Debug**: Utiliza automáticamente IDs de prueba de AdMob para evitar penalizaciones.
*   **Release**: Inyecta los IDs reales desde tu `local.properties`.

---

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