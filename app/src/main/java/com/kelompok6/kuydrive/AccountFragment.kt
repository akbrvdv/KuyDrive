// File: app/src/main/java/com/kelompok6/kuydrive/AccountFragment.kt
package com.kelompok6.kuydrive

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.page_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Logout
        view.findViewById<MaterialButton>(R.id.btnLogout)?.setOnClickListener {
            // Hapus session
            val prefs = activity?.getSharedPreferences("UserSession", Context.MODE_PRIVATE)
            prefs?.edit()?.clear()?.apply()

            // Pindah ke Login
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            activity?.finish()
        }
    }
}