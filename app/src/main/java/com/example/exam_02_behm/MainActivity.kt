package com.example.exam_02_behm

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam_02_behm.data.AppDatabase
import com.example.exam_02_behm.entities.UserEntity

import com.example.exam_02_behm.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var userRepository: UserRepository
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custom_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val database = AppDatabase.getDatabase(this)
        userRepository = UserRepository(database.userDao())

        val recyclerView = findViewById<RecyclerView>(R.id.rvCustomUsuarios)
        recyclerView.layoutManager = LinearLayoutManager(this)

        if (isConnectedToInternet()) {
            startActivity(Intent(this, ApiUsersActivity::class.java))
            finish()
        } else {
            displayLocalUsers(recyclerView)
        }
    }

    private fun displayLocalUsers(recyclerView: RecyclerView) {
        lifecycleScope.launch {
            userRepository.getUsersFromDatabase().collect { userList ->
                userAdapter = UserAdapter(userList, { user ->
                    // Navegar a PostsActivity
                    val intent = Intent(this@MainActivity, PostsActivity::class.java)
                    intent.putExtra("userId", user.id)
                    startActivity(intent)
                }, { user ->
                    // Acción para clic largo (opcional)
                })
                recyclerView.adapter = userAdapter

                if (userList.isEmpty()) {
                    Toast.makeText(this@MainActivity, "No hay usuarios almacenados", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isConnectedToInternet(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
