package com.example.exam_02_behm.network

import com.example.exam_02_behm.services.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ClienteRetrofit {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val userService: UserService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }
}