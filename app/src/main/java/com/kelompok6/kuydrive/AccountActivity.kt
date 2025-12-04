package com.kelompok6.kuydrive

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class AccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        setupUserData()
        setupLogoutButton()
        setupBottomNavigation()
    }

    private fun setupUserData() {
        // Ambil Data User (Nama & Email) dari SharedPreferences
        val userPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val name = userPref.getString("registered_name", "Pengguna KuyDrive")
        val email = userPref.getString("registered_email", "user@kuydrive.com")

        // Tampilkan ke TextView
        findViewById<TextView>(R.id.tvUserName).text = name
        findViewById<TextView>(R.id.tvUserEmail).text = email

        // Tampilkan Saldo (Contoh Data Dummy)
        findViewById<TextView>(R.id.tvBalance).text = "Rp 150.000"
    }

    private fun setupLogoutButton() {
        val btnLogout = findViewById<MaterialButton>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            // 1. Hapus Sesi Login
            val sessionPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
            sessionPref.edit().clear().apply()

            // 2. Kembali ke halaman Login (AuthActivity)
            val intent = Intent(this, AuthActivity::class.java)
            // Membersihkan tumpukan activity agar tombol back tidak kembali ke halaman akun
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun setupBottomNavigation() {
        // PERBAIKAN: Mendefinisikan variabel bottomNav dengan findViewById
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // Set item yang aktif saat ini (Account)
        bottomNav.selectedItemId = R.id.nav_account

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.nav_orders -> {
                    // Pastikan file HistoryActivity.kt sudah dibuat
                    val intent = Intent(this, HistoryActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.nav_account -> true // Sudah di halaman akun, diam saja
                else -> false
            }
        }
    }
}