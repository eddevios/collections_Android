package com.eddevios.collections.data.mapper

import com.eddevios.collections.data.local.entity.CategoryEntity
import com.eddevios.collections.data.local.entity.CollectableEntity
import com.eddevios.collections.data.local.entity.CollectionEntity
import com.eddevios.collections.data.model.Category
import com.eddevios.collections.data.model.Collectable
import com.eddevios.collections.data.model.Collection

object ModelMapper {

    // Convierte CollectionEntity + lista de CategoryEntity -> Collection
    fun toCollection(
        entity: CollectionEntity,
        categories: List<CategoryEntity>
    ): Collection {
        return Collection(
            id = entity.id,
            title = entity.title,
            subtitle = entity.subtitle,
            imageUri = entity.imageUri,
            order = entity.order,
            createdAt = entity.createdAt,
            lastModifiedAt = entity.lastModifiedAt,
            isFavorite = entity.isFavorite,
            categories = categories.map { toCategory(it, emptyList()) }
        )
    }

    // Convierte CategoryEntity + lista de CollectableEntity -> Category
    fun toCategory(
        entity: CategoryEntity,
        collectables: List<CollectableEntity>
    ): Category {
        return Category(
            id = entity.id,
            title = entity.title,
            subtitle = entity.subtitle.toString(),
            imageUri = entity.imageUri,
            order = entity.order,
            createdAt = entity.createdAt,
            lastModifiedAt = entity.lastModifiedAt,
            isFavorite = entity.isFavorite,
            collectables = collectables.map { toCollectable(it) }
        )
    }

    // Convierte CollectableEntity -> Collectable
    fun toCollectable(entity: CollectableEntity): Collectable {
        return Collectable(
            id = entity.id,
            title = entity.title,
            subtitle = entity.subtitle,
            imageUri = entity.imageUri,
            comments = entity.comments,
            order = entity.order,
            createdAt = entity.createdAt,
            lastModifiedAt = entity.lastModifiedAt,
            isFavorite = entity.isFavorite
        )
    }
}
