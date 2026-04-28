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
