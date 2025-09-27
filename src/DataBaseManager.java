import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseManager {
    private static final String URL = "jdbc:sqlite:storage.db"; // файл БД

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL);

            // Створюємо таблицю, якщо її ще немає
            String sql = "CREATE TABLE IF NOT EXISTS products (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL UNIQUE, price INTEGER NOT NULL, quantity INTEGER NOT NULL,  category TEXT NOT NULL );";

            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
            }

            return conn;

        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }
}
