package com.example.exam_02_behm.services


import com.example.exam_02_behm.entities.CommentEntity
import com.example.exam_02_behm.entities.PostEntity
import com.example.exam_02_behm.entities.UserEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface UserService {
    @GET("/users")
    suspend fun getAllUsers(): List<UserEntity>

    @GET("/posts")
    suspend fun getPostsByUser(@Query("userId") userId: Long): List<PostEntity>

    @GET("/comments")
    suspend fun getCommentsByPost(@Query("postId") postId: Long): List<CommentEntity>
}

