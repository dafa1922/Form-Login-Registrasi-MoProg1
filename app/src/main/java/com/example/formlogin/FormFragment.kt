package com.example.formlogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class FormFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_form, container, false)

        val etNama = view.findViewById<TextInputEditText>(R.id.etNamaSeminar)
        val tilNama = view.findViewById<TextInputLayout>(R.id.tilNamaSeminar)
        val etEmail = view.findViewById<TextInputEditText>(R.id.etEmailSeminar)
        val tilEmail = view.findViewById<TextInputLayout>(R.id.tilEmailSeminar)
        val etPhone = view.findViewById<TextInputEditText>(R.id.etPhoneSeminar)
        val tilPhone = view.findViewById<TextInputLayout>(R.id.tilPhoneSeminar)

        val spinnerProdi = view.findViewById<Spinner>(R.id.spinnerProdi)
        val spinnerSeminar = view.findViewById<Spinner>(R.id.spinnerSeminar)
        val rgGender = view.findViewById<RadioGroup>(R.id.rgGenderSeminar)
        val cbAgreement = view.findViewById<CheckBox>(R.id.cbAgreement)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmitSeminar)

        val listProdi = arrayOf("- Pilih Jurusan","TIF", "TI", "DKV", "Bisnis Digital")
        val adapterProdi = ArrayAdapter(requireContext(), R.layout.spinner_item, listProdi)
        adapterProdi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProdi.adapter = adapterProdi

        val listSeminar = arrayOf("- Pilih Tema Seminar","Web Dev", "Mobile Apps", "Cyber Security", "AI & ML")
        val adapterSeminar = ArrayAdapter(requireContext(), R.layout.spinner_item, listSeminar)
        adapterSeminar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSeminar.adapter = adapterSeminar

        etNama.addTextChangedListener { tilNama.error = null }
        etEmail.addTextChangedListener { tilEmail.error = null }
        etPhone.addTextChangedListener {
            val input = it.toString()
            if (!input.startsWith("08")) {
                tilPhone.error = "Harus diawali 08"
            } else if (input.length < 10 || input.length > 13) {
                tilPhone.error = "Panjang 10-13 digit"
            } else {
                tilPhone.error = null
            }
        }

        btnSubmit.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val selectedGenderId = rgGender.checkedRadioButtonId

            tilNama.error = null
            tilEmail.error = null
            tilPhone.error = null

            if (nama.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(requireContext(), "Semua field wajib diisi!", Toast.LENGTH_SHORT).show()
                if (nama.isEmpty()) tilNama.error = "Nama wajib diisi"
                if (email.isEmpty()) tilEmail.error = "Email wajib diisi"
                if (phone.isEmpty()) tilPhone.error = "Nomor HP wajib diisi"
            } else if (!email.contains("@")) {
                tilEmail.error = "Email harus mengandung karakter @"
            } else if (!phone.startsWith("08")) {
                tilPhone.error = "Nomor HP harus diawali dengan 08"
            } else if (phone.length < 10 || phone.length > 13) {
                tilPhone.error = "Nomor HP harus berjumlah 10 - 13 digit"
            } else if (selectedGenderId == -1) {
                Toast.makeText(requireContext(), "Harap pilih jenis kelamin!", Toast.LENGTH_SHORT).show()
            } else if (!cbAgreement.isChecked) {
                Toast.makeText(requireContext(), "Centang persetujuan data terlebih dahulu!", Toast.LENGTH_SHORT).show()
            } else {
                val genderSelected = if (selectedGenderId == R.id.rbMaleSeminar) "Laki-laki" else "Perempuan"
                showConfirmDialog(
                    nama,
                    email,
                    phone,
                    genderSelected,
                    spinnerSeminar.selectedItem.toString(),
                    spinnerProdi.selectedItem.toString()
                )
            }
        }

        return view
    }

    private fun showConfirmDialog(nama: String, email: String, phone: String, gender: String, seminar: String, prodi: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Data")
            .setMessage("Nama: $nama\nEmail: $email\nNo HP: $phone\nSeminar: $seminar\nProdi: $prodi\n\nApakah data sudah benar?")
            .setPositiveButton("Ya") { _, _ ->
                val sharedPref = requireActivity().getSharedPreferences("TugasUTS", 0)
                val editor = sharedPref.edit()
                editor.putString("RES_NAMA", nama)
                editor.putString("RES_EMAIL", email)
                editor.putString("RES_PHONE", phone)
                editor.putString("RES_GENDER", gender)
                editor.putString("RES_SEMINAR", seminar)
                editor.putString("RES_PRODI", prodi)
                editor.apply()

                Toast.makeText(requireContext(), "Pendaftaran Berhasil!", Toast.LENGTH_SHORT).show()

                val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
                bottomNav.selectedItemId = R.id.nav_result
            }
            .setNegativeButton("Tidak", null)
            .show()
    }
}