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

public class AlatMusikCLI {
    private Connection conn;
    private Scanner sc;

    public AlatMusikCLI(Connection conn, Scanner sc) {
        this.conn = conn;
        this.sc = sc;
    }

    public void menu() {
        while (true) {
            System.out.println("\n=== MENU ALAT MUSIK ===");
            System.out.println("1. Tambah Alat Musik");
            System.out.println("2. Lihat Alat Musik");
            System.out.println("3. Update Alat Musik");
            System.out.println("4. Hapus Alat Musik");
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
            System.out.print("Nama alat musik: ");
            String nama = sc.nextLine();
            System.out.print("ID studio: ");
            int studioId = sc.nextInt(); sc.nextLine();

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO alat_musik (nama_alat, studio_id) VALUES (?, ?)");
            ps.setString(1, nama);
            ps.setInt(2, studioId);
            ps.executeUpdate();
            System.out.println("Alat musik berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal tambah alat musik: " + e.getMessage());
        }
    }

    private void lihat() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT am.alat_id, am.nama_alat, sm.nama_studio " +
                    "FROM alat_musik am LEFT JOIN studio_musik sm ON am.studio_id = sm.studio_id");
            while (rs.next()) {
                System.out.printf("%d - %s (Studio: %s)\n",
                        rs.getInt("alat_id"),
                        rs.getString("nama_alat"),
                        rs.getString("nama_studio"));
            }
        } catch (SQLException e) {
            System.out.println("Gagal lihat alat musik: " + e.getMessage());
        }
    }

    private void update() {
        try {
            System.out.print("ID alat musik: ");
            int id = sc.nextInt(); sc.nextLine();
            System.out.print("Nama baru: ");
            String nama = sc.nextLine();
            System.out.print("ID studio baru: ");
            int studioId = sc.nextInt(); sc.nextLine();

            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE alat_musik SET nama_alat = ?, studio_id = ? WHERE alat_id = ?");
            ps.setString(1, nama);
            ps.setInt(2, studioId);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Alat musik berhasil diupdate.");
            } else {
                System.out.println("Alat musik tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal update alat musik: " + e.getMessage());
        }
    }

    private void hapus() {
        try {
            System.out.print("ID alat musik: ");
            int id = sc.nextInt(); sc.nextLine();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM alat_musik WHERE alat_id = ?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Alat musik berhasil dihapus.");
            } else {
                System.out.println("Alat musik tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal hapus alat musik: " + e.getMessage());
        }
    }
}

