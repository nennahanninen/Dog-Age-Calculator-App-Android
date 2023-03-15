package com.example.dogagecalculator.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class FactResponse(val fact: String)

const val BASE_URL = "https://api.animality.xyz/"

interface FactApi {
    @GET("fact/dog")
    suspend fun getFact(): FactResponse

    companion object {
        val instance: FactApi by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FactApi::class.java)
        }
    }
}

