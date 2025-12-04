package com.kelompok6.kuydrive

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
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

        // Mengatur padding agar UI tidak tertutup system bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // --- Bagian Atas (Search & Notif) ---

        val btnSearch = findViewById<LinearLayout>(R.id.btnSearch)
        btnSearch.setOnClickListener {
            Toast.makeText(this, "Fitur Pencarian diklik", Toast.LENGTH_SHORT).show()
        }

        val btnNotification = findViewById<FrameLayout>(R.id.btnNotification)
        btnNotification.setOnClickListener {
            Toast.makeText(this, "Membuka Notifikasi", Toast.LENGTH_SHORT).show()
        }

        // --- Fitur Utama (KuyRide) ---

        val btnKuyRide = findViewById<LinearLayout>(R.id.btnKuyRide)
        btnKuyRide.setOnClickListener {
            Toast.makeText(this, "Membuka layanan KuyRide...", Toast.LENGTH_SHORT).show()
            // Contoh Intent untuk pindah ke Activity lain (misal MapsActivity):
            // val intent = Intent(this, MapsActivity::class.java)
            // startActivity(intent)
        }

        // --- Voucher Promo ---

        val btnVoucher1 = findViewById<TextView>(R.id.btnVoucher1)
        btnVoucher1.setOnClickListener {
            Toast.makeText(this, "Voucher 20% berhasil diklaim!", Toast.LENGTH_SHORT).show()
        }

        // --- Navigasi Bawah ---

        val navHome = findViewById<LinearLayout>(R.id.navHome)
        navHome.setOnClickListener {
            // Kita sudah di halaman Home, mungkin scroll ke atas atau refresh
            Toast.makeText(this, "Anda sudah di Beranda", Toast.LENGTH_SHORT).show()
        }

        val navOrders = findViewById<LinearLayout>(R.id.navOrders)
        navOrders.setOnClickListener {
            Toast.makeText(this, "Menu Pesanan", Toast.LENGTH_SHORT).show()
            // Logika pindah halaman:
            // val intent = Intent(this, OrdersActivity::class.java)
            // startActivity(intent)
        }

        val navAccount = findViewById<LinearLayout>(R.id.navAccount)
        navAccount.setOnClickListener {
            Toast.makeText(this, "Menu Akun", Toast.LENGTH_SHORT).show()
            // Contoh: Kembali ke halaman Login/Auth untuk logout
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
    }
}