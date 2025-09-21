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

        int price = InputManager.getPositiveInt("Enter the price: ");
        int quantity =  InputManager.getPositiveInt("Enter the quantity: ");

        Main.products.add(new Product(name, price, quantity));
    }

    public static Product getProduct(String message) {
        System.out.println(message);
        String name = sc.nextLine();
        Product product = null;

        for (int i = 0; i<Main.products.size(); i++) {
            if (Main.products.get(i).name.equalsIgnoreCase(name)) {
                product = Main.products.get(i);
                break;
            }
        }
        return product;
    }

    public static void editProduct() {
        getList();

        Product product = getProduct("Enter a name of the product you want to edit: ");

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

        Product product = getProduct("Enter the name of the product you want to delete: ");

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