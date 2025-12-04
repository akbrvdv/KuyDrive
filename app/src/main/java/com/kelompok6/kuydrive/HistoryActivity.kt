package com.kelompok6.kuydrive

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history) // Pastikan file layout ini ada

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        // Pastikan ID di activity_history.xml adalah bottomNavigationHistory
        // Jika error merah di sini, ganti ID di XML-nya atau ganti kode ini
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationHistory)

        bottomNav.selectedItemId = R.id.nav_orders

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.nav_orders -> true // Sudah di halaman ini
                R.id.nav_account -> {
                    startActivity(Intent(this, AccountActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }
    }
}