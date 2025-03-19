package com.example.animationincompose.util



import com.example.animationincompose.hadith_api.data.local.database.entity.HadithEntity
import com.example.animationincompose.hadith_api.domain.model.Hadith

fun HadithEntity.toDomainModel(): Hadith {
    return Hadith(
        id = id,
        hadith = hadith,
        narrator = narrator,
        reference = reference
    )
}

fun Hadith.toEntity(): HadithEntity {
    return HadithEntity(
        id = id,
        hadith = hadith,
        narrator = narrator,
        reference = reference
    )
}
