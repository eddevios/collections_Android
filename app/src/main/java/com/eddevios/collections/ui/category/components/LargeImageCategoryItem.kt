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