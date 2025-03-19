package com.example.animationincompose.di

import android.content.Context
import androidx.room.Room
import com.example.animationincompose.hadith_api.data.local.database.AppDatabase
import com.example.animationincompose.hadith_api.data.local.database.dao.HadithDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "hadith_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideHadithDao(database: AppDatabase): HadithDao {
        return database.hadithDao()
    }
}