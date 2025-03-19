package com.example.animationincompose.hadith_api.domain.model

data class Hadith(
    val id: Int = 0,
    val hadith: String,
    val narrator: String,
    val reference: String
)