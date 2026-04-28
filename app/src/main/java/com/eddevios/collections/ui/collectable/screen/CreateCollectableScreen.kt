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
