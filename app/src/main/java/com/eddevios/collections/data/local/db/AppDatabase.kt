package com.eddevios.collections.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eddevios.collections.data.local.dao.CategoryDao
import com.eddevios.collections.data.local.dao.CollectableDao
import com.eddevios.collections.data.local.dao.CollectionDao
import com.eddevios.collections.data.local.entity.CategoryEntity
import com.eddevios.collections.data.local.entity.CollectableEntity
import com.eddevios.collections.data.local.entity.CollectionEntity

@Database(entities = [CollectionEntity::class, CategoryEntity::class, CollectableEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun collectionDao(): CollectionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun collectableDao(): CollectableDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "collection_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}