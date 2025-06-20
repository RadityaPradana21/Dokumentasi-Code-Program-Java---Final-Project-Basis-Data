---

# 🎶 Sistem Manajemen Booking Studio Musik 🎧

Final Project - Mata Kuliah Basis Data

Aplikasi ini merupakan sistem informasi berbasis Java & MySQL yang dirancang untuk membantu pengelolaan pemesanan studio musik secara digital. Melalui sistem ini, pengguna dapat mendaftar sebagai member, memesan studio musik, memilih alat musik yang tersedia, serta melihat jadwal booking dengan rapi dan terintegrasi.


---


## 👥 Anggota Kelompok

### Nama Lengkap	            NPM

## Ahmad Kusma Rizqi M.	 (240820201097)
## Raditya Taufiqul A. P. (240820201116)
## Choirul Wahyu Adji	   (240820201122)
## Dwi Anggita	           (240820201135)



---

## 🚀 Fitur Unggulan

Pendaftaran & Manajemen User Mencatat data user seperti nama, kontak, email, serta mengelompokkan berdasarkan peran.

Manajemen Studio Musik & Alat Musik Menyimpan data studio dan alat musik yang bisa digunakan saat booking.

Pemesanan Studio Musik Pengguna dapat memesan studio dan memilih alat musik dalam satu sesi booking.

Penjadwalan Otomatis Menampilkan slot waktu kosong dan menghindari bentrok jadwal.

CLI Modular Menggunakan konsep CLI terpisah (Command Line Interface) untuk setiap fitur utama, sehingga memudahkan pengembangan dan pemeliharaan.

CRUD Database Otomatis (DAO) Semua operasi data di-handle melalui DAO untuk memastikan struktur kode tetap bersih, reusable, dan sesuai standar OOP.



---

## 📁 Struktur File Penting

File/Folder	Deskripsi Singkat

UserCLI.java	Menu CLI untuk manajemen user
StudioCLI.java	Menu CLI untuk studio musik
AlatMusikCLI.java	Menu CLI untuk alat musik
ReservasiCLI.java	Menu CLI untuk pemesanan studio
UserDAO.java	Koneksi dan operasi database untuk user
StudioMusikDAO.java	Koneksi dan operasi database untuk studio
AlatMusikDAO.java	Koneksi dan operasi database untuk alat musik
BookingDAO.java	Proses booking dan penyimpanan ke database
Main.java	Entry point aplikasi
DBConnection.java	Koneksi database menggunakan JDBC



---

## 🛠️ Cara Menjalankan Program

### 💡 Prasyarat

Java Development Kit (JDK)

MySQL Server

JDBC Connector (mysql-connector-j-<versi>.jar)


### 📦 Step 1: Compile Semua File Java

javac -d bin -cp "lib/mysql-connector-j-8.0.33.jar" -sourcepath . *.java

### ▶️ Step 2: Jalankan Program

java -cp "bin;lib/mysql-connector-j-8.0.33.jar" Main


---

## 🧠 Tentang CLI & DAO

CLI (Command Line Interface)
Merupakan kelas turunan dari Main yang berfungsi mengelola menu utama per fitur (User, Studio, Alat Musik, Booking).
Tujuannya adalah untuk membuat source code lebih modular dan mudah dipahami.

DAO (Data Access Object)
Berfungsi sebagai jembatan antara aplikasi dan database MySQL. Dengan DAO, seluruh logika database (INSERT, SELECT, UPDATE, DELETE) dipisahkan dari logika tampilan/menu, sehingga mendukung prinsip clean code.



---
