package com.example.formlogin

import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Agar layout terangkat saat keyboard muncul
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContentView(R.layout.activity_register)

        // 1. Inisialisasi Tombol Back
        val btnBack = findViewById<LinearLayout>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        // 2. Inisialisasi View Input
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val etConfirm = findViewById<TextInputEditText>(R.id.etConfirmPassword)

        // 3. Inisialisasi Layout Container
        val tilName = findViewById<TextInputLayout>(R.id.tilName)
        val tilEmail = findViewById<TextInputLayout>(R.id.tilEmail)
        val tilPassword = findViewById<TextInputLayout>(R.id.tilPassword)
        val tilConfirm = findViewById<TextInputLayout>(R.id.tilConfirmPassword)

        // 4. Inisialisasi RadioGroup & Checkbox
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val cbCoding = findViewById<CheckBox>(R.id.cbCoding)
        val cbReading = findViewById<CheckBox>(R.id.cbReading)
        val cbGaming = findViewById<CheckBox>(R.id.cbGaming)
        val cbMusic = findViewById<CheckBox>(R.id.cbMusic)
        val cbSports = findViewById<CheckBox>(R.id.cbSports)
        val listHobi = listOf(cbCoding, cbReading, cbGaming, cbMusic, cbSports)

        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // Pola Password: Min 8 Karakter, Huruf Besar, Kecil, Angka, dan Simbol
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*])(?=\\S+$).{8,}$".toRegex()

        // --- LOGIKA REAL-TIME ---
        etName.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                tilName.error = null
                tilName.isErrorEnabled = false
            }
        }

        etEmail.addTextChangedListener {
            if (it.toString().endsWith("@gmail.com")) {
                tilEmail.error = null
                tilEmail.isErrorEnabled = false
            }
        }

        etPassword.addTextChangedListener {
            if (it.toString().matches(passwordPattern)) {
                tilPassword.error = null
                tilPassword.isErrorEnabled = false
            }
        }

        etConfirm.addTextChangedListener {
            if (it.toString() == etPassword.text.toString()) {
                tilConfirm.error = null
                tilConfirm.isErrorEnabled = false
            }
        }

        // --- LOGIKA TOMBOL DAFTAR ---
        btnRegister.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val pass = etPassword.text.toString()
            val confirm = etConfirm.text.toString()
            val jumlahHobi = listHobi.count { it.isChecked }

            var isValid = true

            if (name.isEmpty()) {
                tilName.error = "Nama tidak boleh kosong"
                isValid = false
            }

            if (!email.endsWith("@gmail.com")) {
                tilEmail.error = "Gunakan format @gmail.com"
                isValid = false
            }

            if (!pass.matches(passwordPattern)) {
                tilPassword.error = "Harus ada Huruf Besar, Kecil, Angka, & Simbol (Min 8)"
                isValid = false
            }

            if (confirm != pass) {
                tilConfirm.error = "Password tidak cocok"
                isValid = false
            }

            if (jumlahHobi < 3) {
                Toast.makeText(this, "Pilih minimal 3 hobi!", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            if (rgGender.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Pilih jenis kelamin Anda", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            if (isValid) {
                // Memanggil dialog dengan mengirim data email dan password
                showConfirmDialog(email, pass)
            }
        }

        // --- LONG PRESS ACTION (Reset Form) ---
        btnRegister.setOnLongClickListener {
            etName.text?.clear()
            etEmail.text?.clear()
            etPassword.text?.clear()
            etConfirm.text?.clear()
            rgGender.clearCheck()
            listHobi.forEach { it.isChecked = false }
            showCustomToast("Formulir telah dibersihkan!")
            true
        }
    }


    private fun showConfirmDialog(emailBaru: String, passBaru: String) {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Data")
            .setMessage("Apakah data yang Anda masukkan sudah benar?")
            .setCancelable(false)
            .setPositiveButton("Ya") { _, _ ->
                // Simpan ke SharedPreferences agar bisa login di MainActivity
                val sharedPref = getSharedPreferences("UserAcc", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("SAVED_EMAIL", emailBaru)
                editor.putString("SAVED_PASS", passBaru)
                editor.apply()

                Toast.makeText(this, "Akun Berhasil Dibuat!", Toast.LENGTH_SHORT).show()
                finish() // Kembali ke halaman Login
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    private fun showCustomToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}