/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studiomusikreservasi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlatMusikDAO {

    public boolean insert(AlatMusik alat) {
        String sql = "INSERT INTO alat_musik (nama_alat, studio_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alat.getNamaAlat());
            stmt.setInt(2, alat.getStudioId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public AlatMusik getById(int id) {
        String sql = "SELECT * FROM alat_musik WHERE alat_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new AlatMusik(
                    rs.getInt("alat_id"),
                    rs.getString("nama_alat"),
                    rs.getInt("studio_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<AlatMusik> getAll() {
        List<AlatMusik> list = new ArrayList<>();
        String sql = "SELECT * FROM alat_musik";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                AlatMusik alat = new AlatMusik(
                    rs.getInt("alat_id"),
                    rs.getString("nama_alat"),
                    rs.getInt("studio_id")
                );
                list.add(alat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean update(AlatMusik alat) {
        String sql = "UPDATE alat_musik SET nama_alat = ?, studio_id = ? WHERE alat_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alat.getNamaAlat());
            stmt.setInt(2, alat.getStudioId());
            stmt.setInt(3, alat.getAlatId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM alat_musik WHERE alat_id = ?";
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

