package com.example.recyclerview.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}