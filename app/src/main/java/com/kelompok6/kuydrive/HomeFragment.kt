package com.kelompok6.kuydrive
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.page_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<LinearLayout>(R.id.btnSearch)?.setOnClickListener {
            Toast.makeText(context, "Cari lokasi...", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<CardView>(R.id.btnKuyRide)?.setOnClickListener {
            try {
                val intent = Intent(activity, OrderMapActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, "Fitur Map sedang disiapkan", Toast.LENGTH_SHORT).show()
            }
        }

        view.findViewById<TextView>(R.id.btnVoucher1)?.setOnClickListener {
            Toast.makeText(context, "Voucher Berhasil Diklaim!", Toast.LENGTH_SHORT).show()
        }
    }
}