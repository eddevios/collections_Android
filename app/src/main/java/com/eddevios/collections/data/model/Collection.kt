package com.eddevios.collections.data.model

data class Collection(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageUri: String?,
    val order: Int,
    val createdAt: Long,
    val lastModifiedAt: Long,
    val isFavorite: Boolean,
    val categories: List<Category> = emptyList()
)