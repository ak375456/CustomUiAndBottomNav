package com.example.animationincompose.hadith_api.data.remote

import com.example.animationincompose.hadith_api.domain.model.Hadith
import com.example.animationincompose.util.Constants.KEY
import retrofit2.http.GET
import retrofit2.http.Headers

interface HadithApi {
    @Headers(
        "X-RapidAPI-Key: $KEY",
        "X-RapidAPI-Host: hadith2.p.rapidapi.com"

    )
    @GET("random")
    suspend fun getHadith(): Hadith

}