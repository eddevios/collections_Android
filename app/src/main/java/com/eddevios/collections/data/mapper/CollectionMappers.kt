package com.eddevios.collections.data.mapper

import com.eddevios.collections.data.local.entity.CollectionEntity
import com.eddevios.collections.ui.collection.components.CollectionItem

fun CollectionItem.toEntity(): CollectionEntity {
    return CollectionEntity(
        id = this.id,
        title = this.title,
        subtitle = this.subtitle,
        imageUri = this.imageUri,
        order = this.order,
        createdAt = System.currentTimeMillis(),
        lastModifiedAt = System.currentTimeMillis()
    )
}

fun CollectionEntity.toCollectionItem(): CollectionItem {
    return CollectionItem(
        // Mantén las fechas existentes
        id = this.id,
        title = this.title,
        subtitle = this.subtitle,
        imageUri = this.imageUri,
        order = this.order,
        createdAt = this.createdAt
    )
}
