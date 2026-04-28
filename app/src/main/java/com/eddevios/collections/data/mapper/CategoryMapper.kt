package com.eddevios.collections.data.mapper

import com.eddevios.collections.data.local.entity.CategoryEntity
import com.eddevios.collections.ui.category.components.CategoryItem

fun CategoryItem.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = this.id,
        title = this.title,
        subtitle = this.subtitle,
        imageUri = this.imageUri,
        collectionId = this.collectionId,
        order = this.order,
        createdAt = this.createdAt,
        lastModifiedAt = System.currentTimeMillis()
    )
}

fun CategoryEntity.toCategoryItem(): CategoryItem {
    return CategoryItem(
        id = this.id,
        title = this.title,
        subtitle = this.subtitle,
        imageUri = this.imageUri,
        collectionId = this.collectionId,
        order = this.order,
        createdAt = this.createdAt,
        lastModifiedAt = this.lastModifiedAt
    )
}
