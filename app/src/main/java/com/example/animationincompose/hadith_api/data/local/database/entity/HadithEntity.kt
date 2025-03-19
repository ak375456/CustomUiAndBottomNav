package com.example.animationincompose.hadith_api.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hadith_table")
data class HadithEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val hadith: String,
    val narrator: String,
    val reference: String
)