package com.kelompok6.kuydrive

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class OrderMapActivity : AppCompatActivity() {

    private lateinit var tvStatusTitle: TextView
    private lateinit var tvStatusSubtitle: TextView
    private lateinit var tvDestination: TextView
    private lateinit var ivStatusIllustration: ImageView
    private lateinit var ivCenterMarker: ImageView
    private lateinit var btnAction: MaterialButton
    private lateinit var cardStatus: MaterialCardView
    private lateinit var etSearchLocation: EditText
    private lateinit var searchContainer: CardView

    private var currentState = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_map)

        tvStatusTitle = findViewById(R.id.tvStatusTitle)
        tvStatusSubtitle = findViewById(R.id.tvStatusSubtitle)
        tvDestination = findViewById(R.id.tvDestination)
        ivStatusIllustration = findViewById(R.id.ivStatusIllustration)
        ivCenterMarker = findViewById(R.id.ivCenterMarker)
        btnAction = findViewById(R.id.btnAction)
        cardStatus = findViewById(R.id.cardDriverStatus)
        etSearchLocation = findViewById(R.id.etSearchLocation)
        searchContainer = findViewById(R.id.searchContainer)

        // Tombol Back
        findViewById<View>(R.id.toolbarOrderMap).setOnClickListener { finish() }

        // Logic Pencarian Lokasi (DUMMY / PURA-PURA)
        etSearchLocation.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = v.text.toString()
                if (query.isNotEmpty()) {
                    // set teks tanpa cari koordinat
                    tvDestination.text = query
                    etSearchLocation.setText(query)

                    // Sembunyikan keyboard
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
                true
            } else false
        }

        // Logic Tombol Utama
        btnAction.setOnClickListener {
            when (currentState) {
                0 -> startBooking() // Klik "Pesan Sekarang"
                1 -> cancelOrder()  // Klik "Batalkan"
                2 -> finishTrip()   // Klik "Sampai Tujuan"
            }
        }
    }

    // --- LOGIKA SIMULASI ORDER ---

    private fun startBooking() {
        currentState = 1

        // Ubah UI ke mode "Mencari Driver"
        ivCenterMarker.visibility = View.GONE
        searchContainer.visibility = View.GONE
        cardStatus.visibility = View.VISIBLE

        tvStatusTitle.text = "Mencari Driver..."
        tvStatusSubtitle.text = "Mohon tunggu sebentar..."

        btnAction.text = "Batalkan"
        btnAction.setBackgroundColor(Color.LTGRAY)
        btnAction.setTextColor(Color.WHITE)

        // Simulasi delay 3 detik sebelum driver ketemu
        Handler(Looper.getMainLooper()).postDelayed({ driverFound() }, 3000)
    }

    private fun driverFound() {
        // jika user sudah membatalkan order saat loading, jangan jalankan
        if (currentState != 1) return

        currentState = 2
        tvStatusTitle.text = "Driver Menjemput!"
        tvStatusSubtitle.text = "Budi (AE 1234 XX) • Honda Beat"

        ivStatusIllustration.setImageResource(R.drawable.icon_motor_baru)
        ivStatusIllustration.setColorFilter(getColor(R.color.kuy_yellow))

        btnAction.text = "Sampai Tujuan"
        btnAction.setBackgroundColor(Color.parseColor("#00C853")) // Warna Hijau
        btnAction.setTextColor(Color.WHITE)

        Toast.makeText(this, "Driver Ditemukan!", Toast.LENGTH_SHORT).show()
    }

    private fun cancelOrder() {
        currentState = 0
        Toast.makeText(this, "Pesanan Dibatalkan", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun finishTrip() {
        showRatingDialog()
    }

    private fun showRatingDialog() {
        // Inflate layout dialog custom
        val dialogView = layoutInflater.inflate(R.layout.dialog_rating, null)
        val btnSubmit = dialogView.findViewById<Button>(R.id.btnSubmitRating)
        val ivDialogLogo = dialogView.findViewById<ImageView>(R.id.ivDialogLogo)

        ivDialogLogo.setColorFilter(getColor(R.color.kuy_yellow))

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        btnSubmit.setOnClickListener {
            dialog.dismiss()
            // kembali ke Main Activity dan hapus history
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        dialog.show()
    }
}