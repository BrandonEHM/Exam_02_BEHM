package com.example.exam_02_behm.services

import com.example.exam_02_behm.entities.CommentEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentService {
    @GET("/comments")
    suspend fun getCommentsByPostId(@Query("postId") postId: Int): List<CommentEntity>
}
