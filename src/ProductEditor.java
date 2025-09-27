import java.sql.*;

// class to add/edit/delete the product
public class ProductEditor {

    public static void getList() {
        String sql = "SELECT * FROM products";

        try (Connection conn = DataBaseManager.connect()) {
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                            ", Price: " + rs.getInt("price") + ", Quantity: " + rs.getInt("quantity") +
                            ", Category: " + rs.getString("category"));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addProduct() {
        String name = InputManager.getString("Enter a name of the product you want to add: ");

        // Перевірка, чи продукт уже існує
        String checkSql = "SELECT * FROM products WHERE name = ?";
        try (Connection conn = DataBaseManager.connect();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, name);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("There is already a product with the name " + name);
            } else {
                int price = InputManager.getPositiveInt("Enter the price: ");
                int quantity = InputManager.getPositiveInt("Enter the quantity: ");
                String category = Category.OTHER.toString(); // якщо у тебе enum Category

                // Додавання продукту в базу
                String insertSql = "INSERT INTO products(name, price, quantity, category) VALUES(?, ?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setString(1, name);
                    insertStmt.setInt(2, price);
                    insertStmt.setInt(3, quantity);
                    insertStmt.setString(4, category);
                    insertStmt.executeUpdate();
                }

                System.out.println("The product '" + name + "' has been successfully added.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editProduct() {
        getList();

        String name = InputManager.getString("Enter a name of the product you want to edit: ");

        String selectSql = "SELECT * FROM products WHERE name = ?";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            selectStmt.setString(1, name);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int newPrice = InputManager.getPositiveInt("Enter the new price: ");
                int newQuantity = InputManager.getPositiveInt("Enter the new quantity: ");

                // Оновлюємо запис у базі
                String updateSql = "UPDATE products SET price = ?, quantity = ? WHERE name = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, newPrice);
                    updateStmt.setInt(2, newQuantity);
                    updateStmt.setString(3, name);
                    updateStmt.executeUpdate();
                    System.out.println("Product has been successfully changed!");
                }
            } else {
                System.out.println("Product with name '" + name + "' not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct() {
        getList();
        String name = InputManager.getString("Enter a name of the product you want to delete: ");

        String selectSql = "SELECT * FROM products WHERE name = ?";

        try (Connection conn = DataBaseManager.connect();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            selectStmt.setString(1, name);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                try (PreparedStatement ps = conn.prepareStatement("DELETE FROM products WHERE name = ?")) {
                    ps.setString(1, name);
                    ps.executeUpdate();
                    System.out.println("Product has been successfully deleted!");
                }
            } else {
                System.out.println("Product with name '" + name + "' not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}