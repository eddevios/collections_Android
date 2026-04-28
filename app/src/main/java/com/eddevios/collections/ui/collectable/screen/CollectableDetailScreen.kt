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