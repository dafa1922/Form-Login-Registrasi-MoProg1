package com.example.formlogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        val tvNama = view.findViewById<TextView>(R.id.tvResNama)
        val tvEmail = view.findViewById<TextView>(R.id.tvResEmail)
        val tvPhone = view.findViewById<TextView>(R.id.tvResPhone)
        val tvGender = view.findViewById<TextView>(R.id.tvResGender)
        val tvSeminar = view.findViewById<TextView>(R.id.tvResSeminar)

        val sharedPref = requireActivity().getSharedPreferences("TugasUTS", 0)

        // Ambil data dan tampilkan
        tvNama.text = sharedPref.getString("RES_NAMA", "Belum Daftar")
        tvEmail.text = sharedPref.getString("RES_EMAIL", "-")
        tvPhone.text = sharedPref.getString("RES_PHONE", "-")
        tvGender.text = sharedPref.getString("RES_GENDER", "-")
        tvSeminar.text = sharedPref.getString("RES_SEMINAR", "-")

        return view
    }
}