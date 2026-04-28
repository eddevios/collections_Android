package com.eddevios.collections.data.model

data class Category(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageUri: String?,
    val order: Int,
    val createdAt: Long,
    val lastModifiedAt: Long,
    val isFavorite: Boolean,
    val collectables: List<Collectable> = emptyList() // Anidación aquí
)
