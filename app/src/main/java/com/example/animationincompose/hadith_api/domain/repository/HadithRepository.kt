package com.example.animationincompose.hadith_api.domain.repository

import com.example.animationincompose.hadith_api.domain.model.Hadith
import kotlinx.coroutines.flow.Flow

interface HadithRepository {
    suspend fun getHadith(): Hadith
    fun getAllHadiths(): Flow<List<Hadith>>
    suspend fun getHadithById(hadithId: Int): Hadith?
}