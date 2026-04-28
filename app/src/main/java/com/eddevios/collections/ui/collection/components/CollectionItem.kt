package com.eddevios.collections.ui.collection.components

data class CollectionItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageUri: String?,
    val order: Int,
    val createdAt: Long
)
