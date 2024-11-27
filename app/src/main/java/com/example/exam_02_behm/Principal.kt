package com.example.exam_02_behm


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSalir = findViewById<Button>(R.id.btnSalir)
        val btnCreditos = findViewById<Button>(R.id.btnCreditos)
        val btnListViewBasico = findViewById<Button>(R.id.charRecycle)


        btnListViewBasico.setOnClickListener {
            val intentBasicListView = Intent(this, MainActivity::class.java)
            startActivity(intentBasicListView)
        }


        btnSalir.setOnClickListener {
            finishAffinity()
        }

        btnCreditos.setOnClickListener {
            val intentCreditos = Intent(this, CreditosActivity::class.java)
            startActivity(intentCreditos)
        }
    }
}