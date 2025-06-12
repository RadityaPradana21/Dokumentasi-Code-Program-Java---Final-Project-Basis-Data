import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/utsbasisdata?useSSL=false&serverTimezone=UTC";
    private static final String USER = "reservasi";
    private static final String PASSWORD = "godbless";

    public static Connection getConnection() {
        try {
            // Memastikan driver ter-load
            // System.out.println("Memuat driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL tidak ditemukan!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Koneksi ke database gagal!");
            e.printStackTrace();
        }
        return null; // Jika gagal, return null
    }
}
