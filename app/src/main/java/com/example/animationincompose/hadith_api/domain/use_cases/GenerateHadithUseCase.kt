package com.example.animationincompose.hadith_api.domain.use_cases

import com.example.animationincompose.hadith_api.domain.model.Hadith
import com.example.animationincompose.hadith_api.domain.repository.HadithRepository

class GenerateHadithUseCase constructor(
    private val repository: HadithRepository
) {
    suspend operator fun invoke(): Hadith {
        return repository.getHadith()
    }
}