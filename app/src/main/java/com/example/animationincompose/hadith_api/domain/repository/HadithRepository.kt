package com.example.animationincompose.hadith_api.domain.repository

import com.example.animationincompose.hadith_api.domain.model.Hadith

interface HadithRepository {
    suspend fun getHadith(): Hadith
}