package com.example.animationincompose.hadith_api.data.local.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animationincompose.hadith_api.data.local.database.dao.HadithDao
import com.example.animationincompose.hadith_api.data.local.database.entity.HadithEntity


@Database(entities = [HadithEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hadithDao(): HadithDao
}