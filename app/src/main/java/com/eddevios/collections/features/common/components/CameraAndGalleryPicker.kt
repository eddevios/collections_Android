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

