package com.example.animationincompose.hadith_api.data.repository

import com.example.animationincompose.hadith_api.data.local.database.dao.HadithDao
import com.example.animationincompose.hadith_api.data.local.database.entity.HadithEntity
import com.example.animationincompose.hadith_api.data.remote.HadithApi
import com.example.animationincompose.hadith_api.domain.model.Hadith
import com.example.animationincompose.hadith_api.domain.repository.HadithRepository
import com.example.animationincompose.util.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.example.animationincompose.util.toDomainModel


class HadithRepositoryImpl @Inject constructor(
    private val api: HadithApi,
    private val hadithDao: HadithDao
) : HadithRepository {
    override suspend fun getHadith(): Hadith {
        val remoteHadith = api.getHadith() // Fetch from remote API
        val hadithEntity = remoteHadith.toEntity() // Convert to entity
        hadithDao.insertHadith(hadithEntity) // Save to local database
        return remoteHadith
    }

    override fun getAllHadiths(): Flow<List<Hadith>> {
        return hadithDao.getAllHadiths().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun getHadithById(hadithId: Int): Hadith? {
        return hadithDao.getHadithById(hadithId)?.toDomainModel()
    }

}