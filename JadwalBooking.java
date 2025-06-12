/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studiomusikreservasi;

import java.sql.Date;

public class JadwalBooking {
    private int jadwalId;
    private int studioId;
    private Date tanggal;
    private int jam;
    private String status;

    // Constructor default
    public JadwalBooking() {}

    // Constructor tanpa jadwalId (untuk input baru)
    public JadwalBooking(int studioId, Date tanggal, int jam, String status) {
        this.studioId = studioId;
        this.tanggal = tanggal;
        this.jam = jam;
        this.status = status;
    }

    // Constructor lengkap (untuk data dari database)
    public JadwalBooking(int jadwalId, int studioId, Date tanggal, int jam, String status) {
        this.jadwalId = jadwalId;
        this.studioId = studioId;
        this.tanggal = tanggal;
        this.jam = jam;
        this.status = status;
    }

    // Getter & Setter
    public int getJadwalId() {
        return jadwalId;
    }

    public void setJadwalId(int jadwalId) {
        this.jadwalId = jadwalId;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public int getJam() {
        return jam;
    }

    public void setJam(int jam) {
        this.jam = jam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

