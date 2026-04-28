package com.eddevios.collections.di

import android.content.Context
import androidx.room.Room
import com.eddevios.collections.data.local.dao.CategoryDao
import com.eddevios.collections.data.local.dao.CollectableDao
import com.eddevios.collections.data.local.dao.CollectionDao
import com.eddevios.collections.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext
import com.eddevios.collections.utils.CrashlyticsLogger

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "collection_database"
        ).build()

    @Provides
    fun provideCollectionDao(db: AppDatabase): CollectionDao = db.collectionDao()

    @Provides
    fun provideCategoryDao(db: AppDatabase): CategoryDao = db.categoryDao()

    @Provides
    fun provideCollectableDao(db: AppDatabase): CollectableDao = db.collectableDao()

    @Provides
    @Singleton
    fun provideCrashlyticsLogger(): CrashlyticsLogger {
        return CrashlyticsLogger()
    }
}
