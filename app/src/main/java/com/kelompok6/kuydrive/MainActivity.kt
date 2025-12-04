package com.kelompok6.kuydrive

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
// PENTING: Import ini wajib ada agar tidak crash
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mengatur padding agar UI tidak tertutup system bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        setupClickListeners()
        setupBottomNavigation()
    }

    private fun setupClickListeners() {
        // --- Bagian Atas (Search & Notif) ---
        // btnSearch di XML baru adalah LinearLayout, jadi ini BENAR
        val btnSearch = findViewById<LinearLayout>(R.id.btnSearch)
        btnSearch.setOnClickListener {
            Toast.makeText(this, "Fitur Pencarian diklik", Toast.LENGTH_SHORT).show()
        }

        val btnNotification = findViewById<FrameLayout>(R.id.btnNotification)
        btnNotification.setOnClickListener {
            Toast.makeText(this, "Membuka Notifikasi", Toast.LENGTH_SHORT).show()
        }

        // --- Fitur Utama (KuyRide) ---
        // PERBAIKAN UTAMA DI SINI:
        // Kita ganti LinearLayout menjadi CardView karena desainnya sudah berubah jadi Kapsul/CardView
        val btnKuyRide = findViewById<CardView>(R.id.btnKuyRide)
        btnKuyRide.setOnClickListener {
            val intent = Intent(this, OrderMapActivity::class.java)
            startActivity(intent)
        }

        // --- Voucher Promo ---
        val btnVoucher1 = findViewById<TextView>(R.id.btnVoucher1)
        btnVoucher1.setOnClickListener {
            Toast.makeText(this, "Voucher 20% berhasil diklaim!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // Set item yang aktif saat ini (Home)
        bottomNav.selectedItemId = R.id.nav_home

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true // Sudah di home
                R.id.nav_orders -> {
                    // Pastikan HistoryActivity.kt sudah dibuat agar ini jalan
                    val intent = Intent(this, HistoryActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.nav_account -> {
                    val intent = Intent(this, AccountActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }
    }
}