// class for creating product objects
public class Product {
    public String name;
    protected int price;
    protected int quantity;
    Category category;

    public Product(String name, int price, int quantity, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    @Override
    public String toString() {
        return this.name + " " + this.price + "$ " + this.quantity + " (" + this.category + ")";
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
