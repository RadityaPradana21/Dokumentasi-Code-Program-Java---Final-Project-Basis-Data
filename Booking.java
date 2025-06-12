/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studiomusikreservasi;

import java.time.LocalDate;

public class Booking {
    private int bookingId;
    private int userId;
    private LocalDate tanggalBooking;

    public Booking(int userId, LocalDate tanggalBooking) {
        this.userId = userId;
        this.tanggalBooking = tanggalBooking;
    }

    public Booking(int bookingId, int userId, LocalDate tanggalBooking) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.tanggalBooking = tanggalBooking;
    }

    // Getter & Setter
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getTanggalBooking() {
        return tanggalBooking;
    }

    public void setTanggalBooking(LocalDate tanggalBooking) {
        this.tanggalBooking = tanggalBooking;
    }
}

