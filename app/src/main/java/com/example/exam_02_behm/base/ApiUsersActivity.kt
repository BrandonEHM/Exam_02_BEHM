package com.example.exam_02_behm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam_02_behm.data.AppDatabase
import com.example.exam_02_behm.entities.UserEntity
import com.example.exam_02_behm.repositories.UserRepository
import kotlinx.coroutines.launch

class ApiUsersActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository
    private lateinit var userAdapter: UserAdapter
    private var apiUsers: List<UserEntity> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_recycler_view)

        val database = AppDatabase.getDatabase(this)
        userRepository = UserRepository(database.userDao())

        val recyclerView = findViewById<RecyclerView>(R.id.rvCustomUsuarios)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchAndDisplayApiUsers(recyclerView)
    }

    private fun fetchAndDisplayApiUsers(recyclerView: RecyclerView) {
        lifecycleScope.launch {
            try {
                apiUsers = userRepository.fetchApiUsers()
                userAdapter = UserAdapter(apiUsers, { user ->
                    // Navegar a PostsActivity
                    val intent = Intent(this@ApiUsersActivity, PostsActivity::class.java)
                    intent.putExtra("userId", user.id)
                    startActivity(intent)
                }, { user ->
                    saveUserToLocalDatabase(user)
                })
                recyclerView.adapter = userAdapter
                Toast.makeText(this@ApiUsersActivity, "Usuarios cargados de la API", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@ApiUsersActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveUserToLocalDatabase(user: UserEntity) {
        lifecycleScope.launch {
            userRepository.saveUser(user)
            Toast.makeText(this@ApiUsersActivity, "${user.name} guardado localmente", Toast.LENGTH_SHORT).show()
        }
    }
}
