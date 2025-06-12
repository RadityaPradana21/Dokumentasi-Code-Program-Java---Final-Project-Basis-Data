/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studiomusikreservasi;

public class User {
    private int userId;
    private String nama;
    private String email;
    private String role; // admin atau musisi

    public User(int userId, String nama, String email, String role) {
        this.userId = userId;
        this.nama = nama;
        this.email = email;
        this.role = role;
    }

    public User(String nama, String email, String role) {
        this.nama = nama;
        this.email = email;
        this.role = role;
    }

    // Getter dan Setter
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", nama='" + nama + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

