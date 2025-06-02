package model;

/**
 * Represents a product in the inventory system.
 * Contains basic details like ID, name, quantity, and price.
 */
public class Product {
    // Product ID (must be unique)
    private int id;

    // Name of the product
    private String name;

    // Quantity available in stock
    private int quantity;

    // Price of a single unit
    private double price;

    /**
     * Constructs a Product object with specified details.
     *
     * @param id       Unique identifier for the product
     * @param name     Name of the product
     * @param quantity Quantity available
     * @param price    Price per unit
     */
    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Returns the product ID.
     *
     * @return Product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the product name.
     *
     * @return Product name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the quantity in stock.
     *
     * @return Product quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the product price.
     *
     * @return Product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Updates the product name.
     *
     * @param name New name for the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Updates the quantity of the product.
     *
     * @param quantity New quantity value
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Updates the product price.
     *
     * @param price New price per unit
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the product details as a comma-separated string.
     * Useful for saving to text files.
     *
     * @return Comma-separated product details (id,name,quantity,price)
     */
    @Override
    public String toString() {
        return id + "," + name + "," + quantity + "," + price;
    }
}
