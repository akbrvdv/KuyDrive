package com.example.kuydrive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Logika Link Teks Bawah ("Login di sini")
        val tvLogin = view.findViewById<TextView>(R.id.tvLoginLink)
        tvLogin.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // --- TAMBAHAN BARU ---
        // Logika Tombol Panah Back di Header Kuning
        val btnBack = view.findViewById<View>(R.id.btnBack) // Bisa ImageView atau View
        btnBack.setOnClickListener {
            // Fungsinya sama, kembali ke halaman sebelumnya
            parentFragmentManager.popBackStack()
        }

        // Logika Tombol Daftar (Nanti diimplementasikan)
        // val btnRegister = view.findViewById<Button>(R.id.btnRegister)
    }

        // --- Logika Tombol Daftar (Nanti kita isi) ---
    }
}