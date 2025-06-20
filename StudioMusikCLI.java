/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studiomusikreservasi;

/**
 *
 * @author Choirul Adji
 */
import java.sql.*;
import java.util.Scanner;

public class StudioMusikCLI {
    private Connection conn;
    private Scanner sc;

    public StudioMusikCLI(Connection conn, Scanner sc) {
        this.conn = conn;
        this.sc = sc;
    }

    public void menu() {
        while (true) {
            System.out.println("\n=== MENU STUDIO MUSIK ===");
            System.out.println("1. Tambah Studio");
            System.out.println("2. Lihat Studio");
            System.out.println("3. Update Studio");
            System.out.println("4. Hapus Studio");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            int pilih = sc.nextInt(); sc.nextLine();

            switch (pilih) {
                case 1: tambah(); break;
                case 2: lihat(); break;
                case 3: update(); break;
                case 4: hapus(); break;
                case 0: return;
                default: System.out.println("Pilihan salah.");
            }
        }
    }

    private void tambah() {
        try {
            System.out.print("Nama studio: ");
            String nama = sc.nextLine();
            System.out.print("Lokasi: ");
            String lokasi = sc.nextLine();
            System.out.print("Kapasitas: ");
            int kapasitas = sc.nextInt(); sc.nextLine();

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO studio_musik (nama_studio, lokasi, kapasitas) VALUES (?, ?, ?)");
            ps.setString(1, nama);
            ps.setString(2, lokasi);
            ps.setInt(3, kapasitas);
            ps.executeUpdate();
            System.out.println("Studio berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal tambah studio: " + e.getMessage());
        }
    }

    private void lihat() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM studio_musik");
            while (rs.next()) {
                System.out.printf("%d - %s, %s, kapasitas %d\n",
                        rs.getInt("studio_id"),
                        rs.getString("nama_studio"),
                        rs.getString("lokasi"),
                        rs.getInt("kapasitas"));
            }
        } catch (SQLException e) {
            System.out.println("Gagal lihat studio: " + e.getMessage());
        }
    }

    private void update() {
        try {
            System.out.print("ID studio: ");
            int id = sc.nextInt(); sc.nextLine();
            System.out.print("Nama baru: ");
            String nama = sc.nextLine();
            System.out.print("Lokasi baru: ");
            String lokasi = sc.nextLine();
            System.out.print("Kapasitas baru: ");
            int kapasitas = sc.nextInt(); sc.nextLine();

            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE studio_musik SET nama_studio = ?, lokasi = ?, kapasitas = ? WHERE studio_id = ?");
            ps.setString(1, nama);
            ps.setString(2, lokasi);
            ps.setInt(3, kapasitas);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Studio berhasil diupdate.");
            } else {
                System.out.println("Studio tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal update studio: " + e.getMessage());
        }
    }

    private void hapus() {
        try {
            System.out.print("ID studio: ");
            int id = sc.nextInt(); sc.nextLine();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM studio_musik WHERE studio_id = ?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Studio berhasil dihapus.");
            } else {
                System.out.println("Studio tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal hapus studio: " + e.getMessage());
        }
    }
}

