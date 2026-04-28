package com.eddevios.collections.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collections")
data class CollectionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val subtitle: String,
    val imageUri: String?,
    val order: Int,
    val createdAt: Long,
    val lastModifiedAt: Long,
    val isFavorite: Boolean = false
)
