package com.example.animationincompose.hadith_api.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.animationincompose.hadith_api.data.local.database.entity.HadithEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HadithDao {

    @Insert
    suspend fun insertHadith(hadith: HadithEntity)

    @Query("SELECT * FROM hadith_table ORDER BY id DESC")
    fun getAllHadiths(): Flow<List<HadithEntity>>

    @Query("SELECT * FROM hadith_table WHERE id = :hadithId")
    suspend fun getHadithById(hadithId: Int): HadithEntity?
}