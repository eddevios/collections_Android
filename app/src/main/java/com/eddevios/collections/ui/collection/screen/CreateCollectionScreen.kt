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