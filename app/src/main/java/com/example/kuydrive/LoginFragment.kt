package com.example.kuydrive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menghubungkan fragment ini dengan layout XML-nya
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- Logika Navigasi ---

        // Cari ID TextView "Belum punya akun? Daftar" (Sesuaikan ID nanti dengan UI-mu)
        val tvRegister = view.findViewById<TextView>(R.id.tvRegisterLink)

        tvRegister.setOnClickListener {
            // Perintah untuk pindah ke RegisterFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RegisterFragment())
                .addToBackStack(null) // PENTING: Agar bisa kembali ke Login saat tekan tombol Back HP
                .commit()
        }

        // --- Logika Tombol Login (Nanti kita isi) ---
        // val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        // ...
    }
}