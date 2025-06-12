/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studiomusikreservasi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailBookingDAO {

    // Insert DetailBooking baru ke DB
    public boolean insert(DetailBooking detail) {
        String sql = "INSERT INTO detail_booking (booking_id, studio_id, jam_mulai, jam_selesai) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detail.getBookingId());
            stmt.setInt(2, detail.getStudioId());
            stmt.setInt(3, detail.getJamMulai());
            stmt.setInt(4, detail.getJamSelesai());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update DetailBooking berdasarkan detail_id
    public boolean update(DetailBooking detail) {
        String sql = "UPDATE detail_booking SET booking_id=?, studio_id=?, jam_mulai=?, jam_selesai=? WHERE detail_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detail.getBookingId());
            stmt.setInt(2, detail.getStudioId());
            stmt.setInt(3, detail.getJamMulai());
            stmt.setInt(4, detail.getJamSelesai());
            stmt.setInt(5, detail.getDetailBookingId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete DetailBooking berdasarkan detail_id
    public boolean delete(int detailId) {
        String sql = "DELETE FROM detail_booking WHERE detail_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detailId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get DetailBooking berdasarkan detail_id
    public DetailBooking getById(int detailId) {
        String sql = "SELECT * FROM detail_booking WHERE detail_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detailId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new DetailBooking(
                    rs.getInt("detail_id"),
                    rs.getInt("booking_id"),
                    rs.getInt("studio_id"),
                    rs.getInt("jam_mulai"),
                    rs.getInt("jam_selesai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get semua DetailBooking
    public List<DetailBooking> getAll() {
        List<DetailBooking> list = new ArrayList<>();
        String sql = "SELECT * FROM detail_booking";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DetailBooking detail = new DetailBooking(
                    rs.getInt("detail_id"),
                    rs.getInt("booking_id"),
                    rs.getInt("studio_id"),
                    rs.getInt("jam_mulai"),
                    rs.getInt("jam_selesai")
                );
                list.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

