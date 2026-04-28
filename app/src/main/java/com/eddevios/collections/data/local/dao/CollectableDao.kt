package com.eddevios.collections.data.local.dao

import androidx.room.*
import com.eddevios.collections.data.local.entity.CollectableEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(collectable: CollectableEntity): Long

    @Update
    suspend fun update(collectable: CollectableEntity)

    @Query("DELETE FROM collectables WHERE id = :id")
    suspend fun deleteCollectableById(id: Int)

    @Query("SELECT * FROM collectables WHERE categoryId = :categoryId ORDER BY `order` ASC")
    fun getCollectablesByCategory(categoryId: Int): Flow<List<CollectableEntity>>

    @Query("SELECT * FROM collectables WHERE id = :collectableId")
    suspend fun getCollectableById(collectableId: Int): CollectableEntity?

    @Query("SELECT * FROM collectables WHERE isFavorite = 1 ORDER BY createdAt DESC")
    fun getFavorites(): Flow<List<CollectableEntity>>

    @Query("UPDATE collectables SET `order` = :newOrder WHERE id = :id")
    suspend fun updateCollectableOrder(id: Int, newOrder: Int)

    @Query("SELECT MAX(`order`) FROM collectables WHERE categoryId = :categoryId")
    suspend fun getMaxOrderForCategory(categoryId: Int): Int?
}
