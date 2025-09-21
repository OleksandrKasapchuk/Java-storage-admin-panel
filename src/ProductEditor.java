import java.util.Scanner;

// class to add/edit/delete the product
class ProductEditor {
    static Scanner sc = new Scanner(System.in);
    public static void getList() {
        for (Product p : Main.products) {
            System.out.println(p);
        }
    }

    public static void addProduct() {
        getList();
        String name = InputManager.getString("Enter a name of the product you want to add: ");

        if (getProduct(name) != null){
            System.out.println("There is already a product with the name " + name);
        } else {
            int price = InputManager.getPositiveInt("Enter the price: ");
            int quantity =  InputManager.getPositiveInt("Enter the quantity: ");
            Main.products.add(new Product(name, price, quantity, Category.OTHER));
            System.out.println("The product '" + name + "' has been successfully added.");
        }
    }

    public static Product getProduct(String name) {
        for (Product p : Main.products) {
            if (p.name.equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public static void editProduct() {
        getList();

        Product product = getProduct(InputManager.getString("Enter a name of the product you want to edit: "));

        if (product != null) {
            int newPrice = InputManager.getPositiveInt("Enter the new price: ");
            int newQuantity = InputManager.getPositiveInt("Enter the new quantity: ");

            product.setPrice(newPrice);
            product.setQuantity(newQuantity);

            System.out.println("Product has been successfully changed!");
            System.out.println(product);
        } else {
            System.out.println("There is no product with named like this");
        }
    }

    public static void deleteProduct() {
        getList();

        Product product = getProduct(InputManager.getString("Enter the name of the product you want to delete: "));

        if (product != null) {
            System.out.println("Product has been successfully deleted!");
            Main.products.remove(product);
            System.out.println("The product has been deleted! Here is the new product list");
            getList();
        } else {
            System.out.println("There is no product with named like this");
        }
    }
}