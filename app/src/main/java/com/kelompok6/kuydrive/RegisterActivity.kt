package com.kelompok6.kuydrive

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register) // Pastikan nama layout sesuai

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmailReg)
        val etPass = findViewById<EditText>(R.id.etPassReg)
        val cbTerms = findViewById<CheckBox>(R.id.cbTerms)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val tvLogin = findViewById<TextView>(R.id.tvLoginLink)
        val btnBack = findViewById<View>(R.id.btnBack)

        btnRegister.setOnClickListener {
            if (etName.text.isEmpty() || etEmail.text.isEmpty() || etPass.text.isEmpty()) {
                Toast.makeText(this, "Data tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!cbTerms.isChecked) {
                Toast.makeText(this, "Setujui syarat & ketentuan dulu.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Akun berhasil dibuat! Silakan Login.", Toast.LENGTH_LONG).show()

            // Kembali ke halaman Login (menutup activity ini)
            finish()
        }

        // Tombol login & Back fungsinya sama: menutup halaman register
        tvLogin.setOnClickListener { finish() }
        btnBack.setOnClickListener { finish() }
    }
}