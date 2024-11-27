package com.example.exam_02_behm.services

import com.example.exam_02_behm.entities.PostEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {
    @GET("/posts")
    suspend fun getPostsByUserId(@Query("userId") userId: Int): List<PostEntity>
}

