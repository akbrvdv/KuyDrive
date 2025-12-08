package com.kelompok6.kuydrive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Pastikan nama layout sesuai

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegisterLink)

        // Pindah ke Halaman Register
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val emailInput = etEmail.text.toString()
            val passwordInput = etPassword.text.toString()

            if (emailInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(this, "Isi email dan password dulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Logika Login Sederhana
            if (emailInput == "admin@gmail.com" && passwordInput == "12345") {
                Toast.makeText(this, "Login Berhasil! Halo Admin.", Toast.LENGTH_SHORT).show()

                // Pindah ke MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Menutup LoginActivity agar tidak bisa kembali saat ditekan Back
            } else {
                Toast.makeText(this, "Email atau Password Salah!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}