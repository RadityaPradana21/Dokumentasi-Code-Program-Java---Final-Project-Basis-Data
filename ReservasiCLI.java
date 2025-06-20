package studiomusikreservasi;

import java.sql.*;
import java.util.Scanner;
import java.util.List;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;      


public class ReservasiCLI {
    private Connection conn;
    private Scanner sc;
    private BookingDAO bookingDAO;
    private DetailBookingDAO detailDAO;
    private JadwalBookingDAO jadwalDAO;

    public ReservasiCLI(Connection conn, Scanner sc) {
        this.conn = conn;
        this.sc = sc;
        this.bookingDAO = new BookingDAO(conn);
        this.detailDAO = new DetailBookingDAO(conn);
        this.jadwalDAO = new JadwalBookingDAO(conn);
    }

     public void menu() {
        while (true) {
            System.out.println("\n=== MENU RESERVASI ===");
            System.out.println("1. Booking Baru");
            System.out.println("2. Lihat Semua Booking");
            System.out.println("3. Lihat Semua Jadwal Booking");
            System.out.println("4. Update Status Pembayaran");
            System.out.println("5. Lihat Jadwal Available");
            System.out.println("6. Reset Jadwal Harian");  
            System.out.println("7. Rekap Penggunaan Mingguan");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            int pilih = sc.nextInt(); sc.nextLine();
            switch (pilih) {
                case 1: bookingBaru(); break;
                case 2: lihatBooking(); break;
                case 3: lihatSemuaJadwal(); break;
                case 4: updateStatus(); break;
                case 5: lihatJadwalAvailable(); break;
                case 6: resetJadwalHarian(); break; 
                case 7: rekapMingguan(); break;
                case 0: return;
                default: System.out.println("Pilihan salah.");
            }
        }
    }

    private void bookingBaru() {
        try {
            System.out.print("User ID: ");
            int userId = sc.nextInt(); sc.nextLine();
            System.out.print("Tanggal booking (YYYY-MM-DD): ");
            Date tanggal = Date.valueOf(sc.nextLine());
            System.out.print("Status pembayaran (belum/lunas): ");
            String status = sc.nextLine();
            System.out.print("Studio ID: ");
            int studioId = sc.nextInt(); sc.nextLine();
            System.out.print("Jam mulai: ");
            int jamMulai = sc.nextInt(); sc.nextLine();
            System.out.print("Jam selesai: ");
            int jamSelesai = sc.nextInt(); sc.nextLine();
            System.out.print("Total biaya: ");
            double total = sc.nextDouble(); sc.nextLine();

            // Cek jadwal bentrok dulu
            if (!jadwalDAO.isJadwalAvailable(tanggal, studioId, jamMulai, jamSelesai)) {
                System.out.println("Maaf, jadwal sudah bentrok dengan booking lain. Silakan pilih jam lain.");
                return; // batalkan proses booking
            }

            // Tampilkan jadwal available
            System.out.println("Jadwal yang tersedia:");
            jadwalDAO.getAvailableByTanggal(tanggal, studioId);

            // Insert booking
            int bookingId = bookingDAO.insertAndReturnId(new Booking(userId, tanggal, status, total));

            // Insert detail
            detailDAO.insert(new DetailBooking(bookingId, studioId, jamMulai, jamSelesai));

            System.out.println("Booking berhasil dibuat.");

        } catch (Exception e) {
            System.out.println("Gagal booking: " + e.getMessage());
        }
    }


    private void lihatBooking() {
        try {
            List<Booking> listBooking = bookingDAO.getAll();
            for (Booking b : listBooking) {
                System.out.printf("ID: %d | User ID: %d | Tanggal: %s | Status: %s | Total: %.2f\n",
                    b.getBookingId(),
                    b.getUserId(),
                    b.getTanggalBooking(),
                    b.getStatusPembayaran(),
                    b.getTotalBiaya());
            }
        } catch (SQLException e) {
            System.out.println("Error saat mengambil data booking: " + e.getMessage());
        }
    }
    
    private void lihatSemuaJadwal() {
        try {
            String sql = "SELECT jb.tanggal, sm.nama_studio, jb.jam, jb.status " +
                         "FROM jadwal_booking jb " +
                         "JOIN studio_musik sm ON jb.studio_id = sm.studio_id " +
                         "ORDER BY jb.tanggal, sm.nama_studio, jb.jam";
             try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                System.out.println("\n=== DATA JADWAL BOOKING ===");
                System.out.printf("%-12s | %-20s | %-5s | %-10s\n", "Tanggal", "Studio", "Jam", "Status");
                System.out.println("---------------------------------------------------------");

                boolean adaData = false;
                while (rs.next()) {
                    adaData = true;
                    System.out.printf("%-12s | %-20s | %-5d | %-10s\n",
                        rs.getDate("tanggal"),
                        rs.getString("nama_studio"),
                        rs.getInt("jam"),
                        rs.getString("status"));
                }

                if (!adaData) {
                    System.out.println("Belum ada data jadwal booking.");
                }

            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data jadwal: " + e.getMessage());
        }
    }




    private void updateStatus() {
        try {
            System.out.print("Booking ID: ");
            int id = sc.nextInt(); sc.nextLine();
            System.out.print("Status baru (belum/lunas): ");
            String status = sc.nextLine();
            bookingDAO.updateStatus(id, status);
            System.out.println("Status berhasil diupdate.");
        } catch (Exception e) {
            System.out.println("Gagal update status: " + e.getMessage());
        }
    }

    private void lihatJadwalAvailable() {
        try {
            System.out.print("Tanggal (YYYY-MM-DD): ");
            Date tgl = Date.valueOf(sc.nextLine());
            System.out.print("Studio ID: ");
            int studioId = sc.nextInt(); sc.nextLine();
            jadwalDAO.getAvailableByTanggal(tgl, studioId);
        } catch (Exception e) {
            System.out.println("Gagal lihat jadwal: " + e.getMessage());
        }
    }
    
    private void resetJadwalHarian() {
        try {
            System.out.println("PERHATIAN: Ini akan menghapus semua jadwal sebelumnya!");
            System.out.print("Apakah Anda yakin ingin reset jadwal? (y/n): ");
            String konfirmasi = sc.nextLine();

            if (!konfirmasi.equalsIgnoreCase("y")) {
                System.out.println("Reset jadwal dibatalkan.");
                return;
            }

            System.out.print("Masukkan tanggal jadwal baru (YYYY-MM-DD): ");
            Date tanggal = Date.valueOf(sc.nextLine());

            jadwalDAO.resetJadwalHarian(tanggal); // Method di DAO yg panggil SP

            System.out.println("Jadwal berhasil direset dan digenerate untuk tanggal " + tanggal);

        } catch (IllegalArgumentException e) {
            System.out.println("Format tanggal salah! Harap masukkan dengan format YYYY-MM-DD.");
        } catch (SQLException e) {
            System.out.println("Gagal mereset jadwal: " + e.getMessage());
        }
    }


    
    private int getWeekOfYear(Date date) {
        LocalDate localDate = date.toLocalDate();
        WeekFields weekFields = WeekFields.of(Locale.getDefault()); // Atau WeekFields.ISO kalau mau mulai minggu Senin
        return localDate.get(weekFields.weekOfWeekBasedYear());
    }
    
    private void rekapMingguan() {
        try {
            System.out.print("Masukkan tanggal (YYYY-MM-DD) salah satu booking di minggu itu: ");
            Date tanggal = Date.valueOf(sc.nextLine());
        
            // Hitung minggu ke berapa berdasarkan tanggal
            int mingguKe = getWeekOfYear(tanggal);
        
            System.out.println("Minggu ke-" + mingguKe + " terdeteksi. Menampilkan rekap...");
        
            // Panggil SP
            String sql = "{CALL sp_rekap_penggunaan_mingguan(?)}";
            try (CallableStatement cs = conn.prepareCall(sql)) {
                cs.setInt(1, mingguKe);
                boolean hasResult = cs.execute();

                if (hasResult) {
                    try (ResultSet rs = cs.getResultSet()) {
                        boolean adaData = false;
                        while (rs.next()) {
                            adaData = true;
                            String namaStudio = rs.getString("nama_studio");
                            int totalBooking = rs.getInt("total_booking");
                            int totalJam = rs.getInt("total_jam_penggunaan");
                            String alat = rs.getString("alat_terkait");
                            int totalAlat = rs.getInt("total_alat");

                            System.out.println("Studio: " + namaStudio);
                            System.out.println("Total Booking: " + totalBooking);
                            System.out.println("Total Jam Penggunaan: " + totalJam);
                            System.out.println("Alat Terkait: " + (alat != null ? alat : "-"));
                            System.out.println("Total Alat: " + totalAlat);
                            System.out.println("------------------------------------");
                        }

                        if (!adaData) {
                            System.out.println("Belum ada data booking pada minggu ke-" + mingguKe + ".");
                        }
                    }
                } else {
                    System.out.println("Belum ada data booking pada minggu ke-" + mingguKe + ".");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Format tanggal salah! Harap masukkan dengan format yyyy-MM-dd.");
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan rekap mingguan: " + e.getMessage());
        }
    }


}
