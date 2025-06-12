/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studiomusikreservasi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudioMusikDAO {
    public boolean insert(StudioMusik studio) {
        String sql = "INSERT INTO studio_musik (nama_studio, lokasi, kapasitas) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, studio.getNamaStudio());
            stmt.setString(2, studio.getLokasi());
            stmt.setInt(3, studio.getKapasitas());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public StudioMusik getById(int id) {
        String sql = "SELECT * FROM studio_musik WHERE studio_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new StudioMusik(
                    rs.getInt("studio_id"),
                    rs.getString("nama_studio"),
                    rs.getString("lokasi"),
                    rs.getInt("kapasitas")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<StudioMusik> getAll() {
        List<StudioMusik> list = new ArrayList<>();
        String sql = "SELECT * FROM studio_musik";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                StudioMusik studio = new StudioMusik(
                    rs.getInt("studio_id"),
                    rs.getString("nama_studio"),
                    rs.getString("lokasi"),
                    rs.getInt("kapasitas")
                );
                list.add(studio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean update(StudioMusik studio) {
        String sql = "UPDATE studio_musik SET nama_studio = ?, lokasi = ?, kapasitas = ? WHERE studio_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, studio.getNamaStudio());
            stmt.setString(2, studio.getLokasi());
            stmt.setInt(3, studio.getKapasitas());
            stmt.setInt(4, studio.getStudioId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM studio_musik WHERE studio_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

