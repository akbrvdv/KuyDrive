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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        setupClickListeners()
        setupBottomNavigation()
    }

    private fun setupClickListeners() {
        val btnSearch = findViewById<LinearLayout>(R.id.btnSearch)
        btnSearch.setOnClickListener {
            Toast.makeText(this, "Fitur Pencarian diklik", Toast.LENGTH_SHORT).show()
        }

        val btnNotification = findViewById<FrameLayout>(R.id.btnNotification)
        btnNotification.setOnClickListener {
            Toast.makeText(this, "Membuka Notifikasi", Toast.LENGTH_SHORT).show()
        }

        val btnKuyRide = findViewById<CardView>(R.id.btnKuyRide)
        btnKuyRide.setOnClickListener {
            val intent = Intent(this, OrderMapActivity::class.java)
            startActivity(intent)
        }

        val btnVoucher1 = findViewById<TextView>(R.id.btnVoucher1)
        btnVoucher1.setOnClickListener {
            Toast.makeText(this, "Voucher 20% berhasil diklaim!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_home
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_orders -> {
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
