package com.example.exam_02_behm.entities

data class CommentEntity(
    val postId: Long,
    val id: Long,
    val name: String,
    val email: String,
    val body: String
)