package com.kelompok6.kuydrive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

        val etName = view.findViewById<EditText>(R.id.etName)
        val etEmail = view.findViewById<EditText>(R.id.etEmailReg)
        val etPhone = view.findViewById<EditText>(R.id.etPhone)
        val etPass = view.findViewById<EditText>(R.id.etPassReg)
        val cbTerms = view.findViewById<CheckBox>(R.id.cbTerms)
        val btnRegister = view.findViewById<Button>(R.id.btnRegister)
        val tvLogin = view.findViewById<TextView>(R.id.tvLoginLink)
        val btnBack = view.findViewById<View>(R.id.btnBack)

        btnRegister.setOnClickListener {
            if (etName.text.isEmpty() || etEmail.text.isEmpty() || etPass.text.isEmpty()) {
                Toast.makeText(requireContext(), "Data tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!cbTerms.isChecked) {
                Toast.makeText(requireContext(), "Setujui syarat & ketentuan dulu.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(requireContext(), "Akun berhasil dibuat! Silakan Login.", Toast.LENGTH_LONG).show()

            parentFragmentManager.popBackStack()
        }

        tvLogin.setOnClickListener { parentFragmentManager.popBackStack() }
        btnBack.setOnClickListener { parentFragmentManager.popBackStack() }
    }
}