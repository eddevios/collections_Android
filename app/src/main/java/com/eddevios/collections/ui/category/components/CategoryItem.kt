package com.eddevios.collections.ui.category.components

data class CategoryItem(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val imageUri: String?,
    val collectionId: Int,
    val order: Int,
    val createdAt: Long,
    val lastModifiedAt: Long
)