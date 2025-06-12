/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studiomusikreservasi;

public class DetailBooking {
    private int detailBookingId;
    private int bookingId;
    private int studioId;
    private int jamMulai;
    private int jamSelesai;

    public DetailBooking(int bookingId, int studioId, int jamMulai, int jamSelesai) {
        this.bookingId = bookingId;
        this.studioId = studioId;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
    }

    public DetailBooking(int detailBookingId, int bookingId, int studioId, int jamMulai, int jamSelesai) {
        this.detailBookingId = detailBookingId;
        this.bookingId = bookingId;
        this.studioId = studioId;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
    }

    // Getter & Setter
    public int getDetailBookingId() {
        return detailBookingId;
    }

    public void setDetailBookingId(int detailBookingId) {
        this.detailBookingId = detailBookingId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public int getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(int jamMulai) {
        this.jamMulai = jamMulai;
    }

    public int getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(int jamSelesai) {
        this.jamSelesai = jamSelesai;
    }
}

