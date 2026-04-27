# UTS Pemrograman Mobile - Seminar Registration App

Aplikasi pendaftaran seminar mahasiswa berbasis Android yang mengimplementasikan autentikasi sederhana, validasi input real-time, dan pengelolaan sesi pengguna.

## 📝 Deskripsi Proyek
Proyek ini dibuat untuk memenuhi tugas Ujian Tengah Semester (UTS) mata kuliah Pemrograman Mobile. Aplikasi ini memungkinkan pengguna untuk melakukan pendaftaran akun, login, dan mengisi formulir pendaftaran seminar dengan validasi data yang ketat.

## 🚀 Fitur Utama
* **Sistem Autentikasi**: Register dan Login menggunakan SharedPreferences.
* **Manajemen Sesi**: Fitur Logout dengan pembersihan Task (FLAG_ACTIVITY_CLEAR_TASK) untuk keamanan akun.
* **Validasi Real-time**: Feedback error langsung saat pengetikan (Nomor HP wajib diawali 08, panjang 10-13 digit, dan format email).
* **Dialog Konfirmasi**: Verifikasi data sebelum finalisasi pendaftaran.
* **E-Ticket Result**: Halaman hasil pendaftaran dengan desain kreatif menyerupai tiket seminar.
* **UI Modern**: Menggunakan Material Design Components (CardView, TextInputLayout, BottomNavigationView).

## 🎥 Video Penjelasan & Demo
Anda dapat melihat penjelasan lengkap mengenai alur logika kode dan demonstrasi aplikasi melalui tautan berikut:
👉 **(https://drive.google.com/drive/folders/1WYXxjiQ7CO84G8Eh-r-VFl814XrlJ67m?usp=sharing)]**

## 🛠️ Teknologi yang Digunakan
* **Bahasa**: Kotlin
* **IDE**: Android Studio Ladybug / 2024.2.1
* **Database Lokal**: SharedPreferences
* **Layout**: XML (ConstraintLayout, RelativeLayout, ScrollView)
* **Components**: Fragments, Material Design 3

## 📂 Struktur Folder Utama
* `MainActivity.kt`: Menangani logika login dan pengecekan akun.
* `RegisterActivity.kt`: Menangani pendaftaran akun baru.
* `HomeFragment.kt`: Halaman utama dengan info seminar dan fitur logout.
* `FormFragment.kt`: Jantung aplikasi yang menangani pendaftaran seminar dan validasi.
* `ResultFragment.kt`: Menampilkan bukti pendaftaran (E-Ticket).

## 👤 Pembuat
* **Nama**: DAFA IRSYAD NASHRULLAH
* **NIM**: 24552011306
* **Kelas**: TIF RP 24 D CNS

---
© 2026 UTS Pemrograman Mobile
