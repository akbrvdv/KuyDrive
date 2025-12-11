package com.kelompok6.kuydrive

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class OrderMapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_map)

        setupLogic()
    }

    private fun setupLogic() {
        val btnCancel = findViewById<MaterialButton>(R.id.btnCancelOrder)
        val btnChat = findViewById<MaterialButton>(R.id.btnChat)
        val tvStatusTitle = findViewById<TextView>(R.id.tvStatusTitle)
        val tvStatusSubtitle = findViewById<TextView>(R.id.tvStatusSubtitle)
        val ivStatusIllustration = findViewById<ImageView>(R.id.ivStatusIllustration)
        val cardDriverStatus = findViewById<MaterialCardView>(R.id.cardDriverStatus)

        btnCancel.setOnClickListener {
            Toast.makeText(this, "Pesanan Dibatalkan", Toast.LENGTH_SHORT).show()
            finish()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            tvStatusTitle.text = "Driver Ditemukan!"
            tvStatusSubtitle.text = "Driver sedang menjemputmu (Estimasi 3 menit)"
            ivStatusIllustration.setImageResource(R.drawable.icon_motor_baru)

            btnChat.visibility = View.VISIBLE

            cardDriverStatus.setCardBackgroundColor(android.graphics.Color.parseColor("#FFF8E1"))

            Toast.makeText(this, "Driver berhasil didapatkan!", Toast.LENGTH_LONG).show()
        }, 3000)
    }
}