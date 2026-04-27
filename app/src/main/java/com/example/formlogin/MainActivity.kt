package com.example.formlogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Mengatur padding sistem bars agar UI tidak tertutup status bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Inisialisasi Komponen berdasarkan ID di XML
        val etEmailLog = findViewById<EditText>(R.id.etEmailLog)
        val etPassLog = findViewById<EditText>(R.id.etPassLog)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        // 2. Logika Klik Tombol Login
        btnLogin.setOnClickListener {
            val emailInput = etEmailLog.text.toString()
            val passInput = etPassLog.text.toString()

            // Mengambil data akun yang sudah disimpan di SharedPreferences (dari RegisterActivity)
            val sharedPref = getSharedPreferences("UserAcc", MODE_PRIVATE)
            val savedEmail = sharedPref.getString("SAVED_EMAIL", null)
            val savedPass = sharedPref.getString("SAVED_PASS", null)

            if (emailInput.isNotEmpty() && passInput.isNotEmpty()) {
                // Validasi input user dengan data yang tersimpan di memori [cite: 27]
                if (emailInput == savedEmail && passInput == savedPass) {
                    Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()


                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish() // Menutup Login agar tidak bisa 'Back'
                } else {
                    Toast.makeText(this, "Email atau Password Salah!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Silakan isi Email dan Password!", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Logika Pindah ke Halaman Register
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}