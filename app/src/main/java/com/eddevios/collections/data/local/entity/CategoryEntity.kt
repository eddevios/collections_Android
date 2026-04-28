package com.eddevios.collections.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "categories",
    foreignKeys = [
        ForeignKey(
            entity = CollectionEntity::class,
            parentColumns = ["id"],
            childColumns = ["collectionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val subtitle: String?,
    val imageUri: String?,
    val collectionId: Int, // Clave foránea
    val order: Int,
    val createdAt: Long,
    val lastModifiedAt: Long,
    val isFavorite: Boolean = false
)
