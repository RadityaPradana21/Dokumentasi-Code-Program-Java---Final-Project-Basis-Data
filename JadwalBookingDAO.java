package studiomusikreservasi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JadwalBookingDAO {

    // Insert jadwal booking baru
    public boolean insert(JadwalBooking jadwal) {
        String sql = "INSERT INTO jadwal_booking (studio_id, tanggal, jam, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, jadwal.getStudioId());
            stmt.setDate(2, jadwal.getTanggal());
            stmt.setInt(3, jadwal.getJam());
            stmt.setString(4, jadwal.getStatus());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update status jadwal booking berdasarkan jadwal_id
    public boolean updateStatus(int jadwalId, String status) {
        String sql = "UPDATE jadwal_booking SET status=? WHERE jadwal_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, jadwalId);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete jadwal booking
    public boolean delete(int jadwalId) {
        String sql = "DELETE FROM jadwal_booking WHERE jadwal_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, jadwalId);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get jadwal booking berdasarkan jadwal_id
    public JadwalBooking getById(int jadwalId) {
        String sql = "SELECT * FROM jadwal_booking WHERE jadwal_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, jadwalId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new JadwalBooking(
                    rs.getInt("jadwal_id"),
                    rs.getInt("studio_id"),
                    rs.getDate("tanggal"),
                    rs.getInt("jam"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get semua jadwal booking
    public List<JadwalBooking> getAll() {
        List<JadwalBooking> list = new ArrayList<>();
        String sql = "SELECT * FROM jadwal_booking";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                JadwalBooking jadwal = new JadwalBooking(
                    rs.getInt("jadwal_id"),
                    rs.getInt("studio_id"),
                    rs.getDate("tanggal"),
                    rs.getInt("jam"),
                    rs.getString("status")
                );
                list.add(jadwal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
