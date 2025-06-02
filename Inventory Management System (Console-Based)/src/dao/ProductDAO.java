package dao;

import java.util.Comparator;
import java.util.List;
import model.Product;
import util.FileUtil;

public class ProductDAO {

    /**
     * Adds a new product to the inventory.
     * Loads existing products, adds the new one, then saves all back to file.
     */
    public static void addProduct(Product product) {
        List<Product> products = FileUtil.loadFromFile();  // Load current products
        products.add(product);                             // Add new product
        FileUtil.saveToFile(products);                     // Save updated list
        System.out.println("Product added successfully!");
    }

    /**
     * Retrieves all products from the inventory file.
     */
    public static List<Product> getAllProducts() {
        return FileUtil.loadFromFile();
    }

    /**
     * Updates an existing product in the inventory using the old product ID.
     * Replaces the matching product entirely with the updated product object.
     * @param oldId ID of the product to be updated
     * @param updatedProduct New product data to replace the old one
     * @return true if update was successful, false if product ID was not found
     */
    public static boolean updateProduct(int oldId, Product updatedProduct) {
        List<Product> products = FileUtil.loadFromFile();  // Load all products
        boolean found = false;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == oldId) {        // Match old ID
                products.set(i, updatedProduct);           // Replace with updated product
                found = true;
                break;
            }
        }

        if (found) {
            FileUtil.saveToFile(products);                 // Save updated list
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product ID not found.");
        }

        return found;
    }

    /**
     * Deletes a product from the inventory based on its ID.
     * @param id ID of the product to delete
     * @return true if product was found and deleted, false otherwise
     */
    public static boolean deleteProduct(int id) {
        List<Product> products = FileUtil.loadFromFile();  // Load current products
        boolean found = products.removeIf(p -> p.getId() == id);  // Remove matching product

        if (found) {
            FileUtil.saveToFile(products);                 // Save updated list
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product ID not found.");
        }

        return found;
    }

    /**
     * Returns a list of all products sorted by product name in ascending order.
     */
    public static List<Product> getSortedByName() {
        List<Product> products = FileUtil.loadFromFile();  // Load current products
        products.sort(Comparator.comparing(Product::getName));  // Sort by name
        return products;
    }

    /**
     * Returns a list of all products sorted by price in ascending order.
     */
    public static List<Product> getSortedByPrice() {
        List<Product> products = FileUtil.loadFromFile();  // Load current products
        products.sort(Comparator.comparingDouble(Product::getPrice));  // Sort by price
        return products;
    }
}
