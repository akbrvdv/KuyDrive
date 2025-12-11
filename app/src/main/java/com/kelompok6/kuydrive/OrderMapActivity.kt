package com.kelompok6.kuydrive

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class OrderMapActivity : AppCompatActivity() {

    // Komponen UI
    private lateinit var tvStatusTitle: TextView
    private lateinit var tvStatusSubtitle: TextView
    private lateinit var tvDestination: TextView
    private lateinit var ivStatusIllustration: ImageView
    private lateinit var btnAction: MaterialButton
    private lateinit var btnChat: MaterialButton
    private lateinit var cardStatus: MaterialCardView

    // Data State
    private var currentState = 0 // 0: Confirm, 1: Searching, 2: On Trip
    private val PRICE_TRIP = "Rp 12.000"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_map)

        // Inisialisasi View
        tvStatusTitle = findViewById(R.id.tvStatusTitle)
        tvStatusSubtitle = findViewById(R.id.tvStatusSubtitle)
        tvDestination = findViewById(R.id.tvDestination)
        ivStatusIllustration = findViewById(R.id.ivStatusIllustration)
        btnAction = findViewById(R.id.btnAction)
        btnChat = findViewById(R.id.btnChat)
        cardStatus = findViewById(R.id.cardDriverStatus)

        // Ambil data dari Home
        val tujuanUser = intent.getStringExtra("TUJUAN_USER") ?: "Lokasi Pilihan"
        tvDestination.text = tujuanUser

        // Set Toolbar back button
        findViewById<View>(R.id.toolbarOrderMap).setOnClickListener { finish() }

        // Setup awal tombol
        setupButtonLogic()
    }

    private fun setupButtonLogic() {
        btnAction.setOnClickListener {
            when (currentState) {
                0 -> startSearchingDriver() // Dari Confirm -> Search
                1 -> cancelOrder()          // Dari Search -> Cancel
                2 -> finishTrip()           // Dari Trip -> Finish
            }
        }
    }

    // --- LOGIC STATES ---

    // STATE 1: Mencari Driver
    private fun startSearchingDriver() {
        currentState = 1

        // Update UI jadi mode loading
        tvStatusTitle.text = "Mencari Driver..."
        tvStatusSubtitle.text = "Mohon tunggu sebentar"
        btnAction.text = "Batalkan"
        btnAction.setBackgroundColor(Color.parseColor("#E0E0E0")) // Jadi abu-abu

        // Simulasi delay 3 detik dapat driver
        Handler(Looper.getMainLooper()).postDelayed({
            driverFound()
        }, 3000)
    }

    // STATE 2: Driver Ditemukan & OTW
    private fun driverFound() {
        currentState = 2

        // Update UI Info Driver
        tvStatusTitle.text = "Driver Menjemput!"
        tvStatusSubtitle.text = "Budi (B 1234 KY) • Yamaha NMAX"
        ivStatusIllustration.setImageResource(R.drawable.icon_motor_baru) // Pastikan icon ada
        cardStatus.setCardBackgroundColor(Color.parseColor("#E8F5E9")) // Hijau muda

        // Munculkan tombol Chat & Update tombol utama
        btnChat.visibility = View.VISIBLE
        btnAction.text = "Sampai Tujuan" // Tombol rahasia simulasi selesai
        btnAction.setBackgroundColor(Color.parseColor("#00C853")) // Hijau
        btnAction.setTextColor(Color.WHITE)

        Toast.makeText(this, "Driver berhasil didapatkan!", Toast.LENGTH_LONG).show()
    }

    // STATE 3: Selesai Trip
    private fun finishTrip() {
        showRatingDialog()
    }

    private fun cancelOrder() {
        Toast.makeText(this, "Pesanan dibatalkan", Toast.LENGTH_SHORT).show()
        finish()
    }

    // --- DIALOG RATING ---
    private fun showRatingDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_rating, null)
        val btnSubmit = dialogView.findViewById<Button>(R.id.btnSubmitRating)
        val ratingBar = dialogView.findViewById<RatingBar>(R.id.ratingBar)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        btnSubmit.setOnClickListener {
            val stars = ratingBar.rating
            Toast.makeText(this, "Terima kasih atas rating $stars bintang!", Toast.LENGTH_SHORT).show()

            dialog.dismiss()

            // Balik ke Home & Hapus history activity agar gabisa di-back
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        dialog.show()
    }
}