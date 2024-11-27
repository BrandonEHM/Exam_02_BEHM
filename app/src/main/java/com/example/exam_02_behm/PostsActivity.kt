package com.example.exam_02_behm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam_02_behm.entities.PostEntity
import com.example.exam_02_behm.network.ClienteRetrofit
import kotlinx.coroutines.launch

class PostsActivity : AppCompatActivity() {

    private lateinit var postAdapter: PostAdapter
    private val postService = ClienteRetrofit.userService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        val userId = intent.getLongExtra("userId", -1)
        val recyclerView = findViewById<RecyclerView>(R.id.rvPosts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val posts = postService.getPostsByUser(userId)
            postAdapter = PostAdapter(posts) { post ->
                // Navegar a CommentsActivity
                val intent = Intent(this@PostsActivity, CommentsActivity::class.java)
                intent.putExtra("postId", post.id)
                startActivity(intent)
            }
            recyclerView.adapter = postAdapter
        }
    }
}
