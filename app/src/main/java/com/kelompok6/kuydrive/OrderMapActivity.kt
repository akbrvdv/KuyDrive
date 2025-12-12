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

        findViewById<View>(R.id.toolbarOrderMap).setOnClickListener { finish() }

        etSearchLocation.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = v.text.toString()
                if (query.isNotEmpty()) {
                    tvDestination.text = query
                    etSearchLocation.setText(query)

                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
                true
            } else false
        }

        btnAction.setOnClickListener {
            when (currentState) {
                0 -> startBooking()
                1 -> cancelOrder()
                2 -> finishTrip()
            }
        }
    }
    private fun startBooking() {
        currentState = 1

        ivCenterMarker.visibility = View.GONE
        searchContainer.visibility = View.GONE
        cardStatus.visibility = View.VISIBLE

        tvStatusTitle.text = "Mencari Driver..."
        tvStatusSubtitle.text = "Mohon tunggu sebentar..."

        btnAction.text = "Batalkan"
        btnAction.setBackgroundColor(Color.LTGRAY)
        btnAction.setTextColor(Color.WHITE)

        Handler(Looper.getMainLooper()).postDelayed({ driverFound() }, 3000)
    }

    private fun driverFound() {
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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        dialog.show()
    }
}