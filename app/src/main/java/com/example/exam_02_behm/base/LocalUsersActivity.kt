package com.example.exam_02_behm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam_02_behm.data.AppDatabase
import com.example.exam_02_behm.entities.UserEntity
import com.example.exam_02_behm.repositories.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LocalUsersActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_recycler_view)

        val database = AppDatabase.getDatabase(this)
        userRepository = UserRepository(database.userDao())

        val recyclerView = findViewById<RecyclerView>(R.id.rvCustomUsuarios)
        recyclerView.layoutManager = LinearLayoutManager(this)

        displayLocalUsers(recyclerView)
    }

    private fun displayLocalUsers(recyclerView: RecyclerView) {
        lifecycleScope.launch {
            userRepository.getUsersFromDatabase().collect { userList ->
                userAdapter = UserAdapter(userList, { user ->
                    // Si deseas manejar un clic corto en esta actividad, coloca aquí la lógica
                    Toast.makeText(this@LocalUsersActivity, "Usuario seleccionado: ${user.name}", Toast.LENGTH_SHORT).show()
                }, { user ->
                    // Si deseas manejar un clic largo, coloca aquí la lógica (opcional)
                    Toast.makeText(this@LocalUsersActivity, "Clic largo en: ${user.name}", Toast.LENGTH_SHORT).show()
                })
                recyclerView.adapter = userAdapter

                if (userList.isEmpty()) {
                    Toast.makeText(this@LocalUsersActivity, "No hay usuarios almacenados", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
