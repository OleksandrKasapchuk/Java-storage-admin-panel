

import java.util.*;

public class Main {
    public static ArrayList<Product> products = new ArrayList<>(
            Arrays.asList(
                    new Product("books", 100, 10, Category.BOOKS),
                    new Product("fruits", 50, 50, Category.FOOD),
                    new Product("laptops", 1000, 5, Category.ELECTRONICS)
            )
    );

    public static void main(String[] args) {
        System.out.println("Welcome to the market admin panel");

        outer:
        while(true) {
            int choice = InputManager.getInt("Choose an option (0- exit, 1 - get all products, 2 - add a product, 3 - delete a product, 4 - edit product)) ");

            switch(choice) {
                case 0:
                    System.out.println("Good Bye, see ya!");
                    break outer;
                case 1:
                    ProductEditor.getList();
                    break;
                case 2:
                    ProductEditor.addProduct();
                    break;
                case 3:
                    ProductEditor.deleteProduct();
                    break;
                case 4:
                    ProductEditor.editProduct();
            }
        }
    }
}
