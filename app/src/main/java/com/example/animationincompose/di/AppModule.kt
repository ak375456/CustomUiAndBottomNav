package com.example.animationincompose.di

import com.example.animationincompose.hadith_api.data.local.database.dao.HadithDao
import com.example.animationincompose.hadith_api.data.remote.HadithApi
import com.example.animationincompose.hadith_api.data.repository.HadithRepositoryImpl
import com.example.animationincompose.hadith_api.domain.repository.HadithRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(api: HadithApi, hadithDao: HadithDao): HadithRepository {
        return HadithRepositoryImpl(api = api, hadithDao = hadithDao)
    }
}