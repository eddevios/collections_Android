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
