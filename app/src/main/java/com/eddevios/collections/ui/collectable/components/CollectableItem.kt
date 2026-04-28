package com.eddevios.collections.ui.collectable.components

data class CollectableItem(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val imageUri: String?,
    val comments: String?,
    val categoryId: Int,
    val order: Int,
    val createdAt: Long,
    val lastModifiedAt: Long,
    val isFavorite: Boolean
)
