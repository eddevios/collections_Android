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
