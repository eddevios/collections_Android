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

