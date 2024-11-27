package com.example.exam_02_behm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam_02_behm.entities.CommentEntity
import com.example.exam_02_behm.network.ClienteRetrofit
import kotlinx.coroutines.launch

class CommentsActivity : AppCompatActivity() {

    private lateinit var commentAdapter: CommentAdapter
    private val postService = ClienteRetrofit.userService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_list)

        val postId = intent.getLongExtra("postId", -1)
        val recyclerView = findViewById<RecyclerView>(R.id.rvComments)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val comments = postService.getCommentsByPost(postId)
            commentAdapter = CommentAdapter(comments)
            recyclerView.adapter = commentAdapter
        }
    }
}
