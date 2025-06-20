/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studiomusikreservasi;

/**
 *
 * @author Choirul Adji
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserCLI {
    private UserDAO userDAO;
    private Scanner scanner;

    public UserCLI(Connection conn) {
        this.userDAO = new UserDAO(conn);
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        while (true) {
            System.out.println("\n=== MENU USER ===");
            System.out.println("1. List semua user");
            System.out.println("2. Tambah user baru");
            System.out.println("3. Update user");
            System.out.println("4. Hapus user");
            System.out.println("5. Cari user by ID");
            System.out.println("0. Keluar");

            System.out.print("Pilih menu: ");
            int pilihan = Integer.parseInt(scanner.nextLine());

            try {
                switch (pilihan) {
                    case 1:
                        listUser();
                        break;
                    case 2:
                        tambahUser();
                        break;
                    case 3:
                        updateUser();
                        break;
                    case 4:
                        hapusUser();
                        break;
                    case 5:
                        cariUserById();
                        break;
                    case 0:
                        System.out.println("Keluar...");
                        return;
                    default:
                        System.out.println("Menu tidak valid!");
                }
            } catch (SQLException e) {
                System.out.println("Error DB: " + e.getMessage());
            }
        }
    }

    private void listUser() throws SQLException {
        List<User> list = userDAO.getAllUsers();
        System.out.println("\nDaftar User:");
        for (User u : list) {
            System.out.println(u);
        }
    }

    private void tambahUser() throws SQLException {
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Role (admin/musisi): ");
        String role = scanner.nextLine();

        boolean sukses = userDAO.addUser(nama, email, role);
        if (sukses) System.out.println("User berhasil ditambah.");
        else System.out.println("Gagal menambah user.");
    }

    private void updateUser() throws SQLException {
        System.out.print("ID user yang akan diupdate: ");
        int id = Integer.parseInt(scanner.nextLine());

        User u = userDAO.getUserById(id);
        if (u == null) {
            System.out.println("User tidak ditemukan.");
            return;
        }

        System.out.print("Nama baru (" + u.getNama() + "): ");
        String nama = scanner.nextLine();
        if (nama.isEmpty()) nama = u.getNama();

        System.out.print("Email baru (" + u.getEmail() + "): ");
        String email = scanner.nextLine();
        if (email.isEmpty()) email = u.getEmail();

        System.out.print("Role baru (" + u.getRole() + "): ");
        String role = scanner.nextLine();
        if (role.isEmpty()) role = u.getRole();

        boolean sukses = userDAO.updateUser(id, nama, email, role);
        if (sukses) System.out.println("User berhasil diupdate.");
        else System.out.println("Gagal mengupdate user.");
    }

    private void hapusUser() throws SQLException {
        System.out.print("ID user yang akan dihapus: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean sukses = userDAO.deleteUser(id);
        if (sukses) System.out.println("User berhasil dihapus.");
        else System.out.println("Gagal menghapus user.");
    }

    private void cariUserById() throws SQLException {
        System.out.print("Masukkan ID user: ");
        int id = Integer.parseInt(scanner.nextLine());

        User u = userDAO.getUserById(id);
        if (u == null) System.out.println("User tidak ditemukan.");
        else System.out.println(u);
    }
}

