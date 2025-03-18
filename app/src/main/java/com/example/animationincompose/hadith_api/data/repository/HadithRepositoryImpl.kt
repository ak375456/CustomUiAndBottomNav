package com.example.animationincompose.hadith_api.data.repository

import com.example.animationincompose.hadith_api.data.remote.HadithApi
import com.example.animationincompose.hadith_api.domain.model.Hadith
import com.example.animationincompose.hadith_api.domain.repository.HadithRepository
import javax.inject.Inject


class HadithRepositoryImpl @Inject constructor(
    private val api: HadithApi
) : HadithRepository {
    override suspend fun getHadith(): Hadith {
        return api.getHadith()
    }
}