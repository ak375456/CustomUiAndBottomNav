package com.example.animationincompose.hadith_api.domain.use_cases.generate_hadith

import com.example.animationincompose.hadith_api.domain.model.Hadith
import com.example.animationincompose.hadith_api.domain.repository.HadithRepository
import javax.inject.Inject

class GenerateHadithUseCase @Inject constructor(
    private val repository: HadithRepository
) {
    suspend operator fun invoke(): Hadith {
        return repository.getHadith()
    }
}