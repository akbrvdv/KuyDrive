package com.kelompok6.kuydrive

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView

class PaymentMethodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}