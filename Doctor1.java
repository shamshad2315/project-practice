import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Doctor1 {

    public static Connection getConnection() {
        try {
            // Load database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Replace the database URL, username, and password with your own
            String url = "jdbc:mysql://localhost:3306/your_database_name";
            String username = "your_username";
            String password = "your_password";

            // Establish connection
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

