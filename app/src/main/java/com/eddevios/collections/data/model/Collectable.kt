package com.eddevios.collections.data.model

data class Collectable(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val imageUri: String?,
    val comments: String?,
    val order: Int,
    val createdAt: Long,
    val lastModifiedAt: Long,
    val isFavorite: Boolean
)
