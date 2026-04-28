package com.eddevios.collections.data.mapper

import com.eddevios.collections.data.local.entity.CollectableEntity
import com.eddevios.collections.ui.collectable.components.CollectableItem

// data/mapper/CollectableMappers.kt
fun CollectableItem.toEntity(): CollectableEntity {
    return CollectableEntity(
        id = this.id,
        title = this.title,
        subtitle = this.subtitle,
        imageUri = this.imageUri,
        comments = this.comments,
        categoryId = this.categoryId,
        order = this.order,
        createdAt = this.createdAt,
        lastModifiedAt = System.currentTimeMillis(),
        isFavorite = this.isFavorite
    )
}

fun CollectableEntity.toCollectableItem(): CollectableItem {
    return CollectableItem(
        id = this.id,
        title = this.title,
        subtitle = this.subtitle,
        imageUri = this.imageUri,
        comments = this.comments,
        categoryId = this.categoryId,
        order = this.order,
        createdAt = this.createdAt,
        lastModifiedAt = this.lastModifiedAt,
        isFavorite = this.isFavorite
    )
}