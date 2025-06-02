package main;

import dao.ProductDAO;
import java.util.List;
import java.util.Scanner;
import model.Product;
import util.FileUtil;

/**
 * Main class for the Inventory Management System (Console-Based).
 * Provides a menu-driven interface for adding, viewing, updating,
 * deleting, and sorting products.
 */
public class Main {

    /**
     * Entry point of the application.
     * Displays a menu to manage inventory items using console inputs.
     */
    public static void main(String[] args) {

        // Initialize inventory.txt file if not already created
        FileUtil.initializeFile();
        Scanner scanner = new Scanner(System.in);

        // Infinite loop for menu navigation
        while (true) {
            System.out.println("\n=== Inventory Menu ===");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. View Products Sorted by Name");
            System.out.println("6. View Products Sorted by Price");
            System.out.println("7. Inventory Summary");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                // Parse menu input
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    // Add Product
                    int id, qty;
                    double price;
                    String name;

                    try {
                        System.out.print("Enter product ID: ");
                        id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter product name: ");
                        name = scanner.nextLine();

                        System.out.print("Enter quantity: ");
                        qty = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter price: ");
                        price = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter numbers where required.");
                        break;
                    }

                    // Input validation
                    if (id < 0 || qty < 0 || price < 0 || name.trim().isEmpty()) {
                        System.out.println("Invalid input. Values must be non-negative and name must not be empty.");
                        break;
                    }

                    // Check for duplicate ID
                    List<Product> existingProducts = ProductDAO.getAllProducts();
                    boolean duplicateId = existingProducts.stream().anyMatch(p -> p.getId() == id);
                    if (duplicateId) {
                        System.out.println("Product with this ID already exists.");
                        break;
                    }

                    Product p = new Product(id, name, qty, price);
                    ProductDAO.addProduct(p);
                }

                case 2 -> {
                    // View Products
                    System.out.println("Attempting to load products...");
                    List<Product> productsList = ProductDAO.getAllProducts();

                    if (productsList.isEmpty()) {
                        System.out.println("No products found.");
                    } else {
                        System.out.println("\nID\tName\tQty\tPrice");
                        for (Product p : productsList) {
                            System.out.printf("%d\t%s\t%d\t%.2f\n",
                                    p.getId(), p.getName(), p.getQuantity(), p.getPrice());
                        }
                    }
                }

                case 3 -> {
                    // Update Product
                    int oldId, newId, qty;
                    double price;
                    String newName;

                    try {
                        System.out.print("Enter product ID to update: ");
                        oldId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter a valid number.");
                        break;
                    }

                    // Check if product exists
                    List<Product> existingProducts = ProductDAO.getAllProducts();
                    Product existing = existingProducts.stream().filter(p -> p.getId() == oldId).findFirst().orElse(null);
                    if (existing == null) {
                        System.out.println("Product ID not found.");
                        break;
                    }

                    try {
                        System.out.print("Enter new ID (current: " + oldId + "): ");
                        newId = Integer.parseInt(scanner.nextLine());

                        // Check for duplicate new ID
                        if (newId != oldId && existingProducts.stream().anyMatch(p -> p.getId() == newId)) {
                            System.out.println("Another product with this ID already exists.");
                            break;
                        }

                        System.out.print("Enter new name (current: " + existing.getName() + "): ");
                        newName = scanner.nextLine();
                        if (newName.trim().isEmpty()) newName = existing.getName();

                        System.out.print("Enter new quantity (current: " + existing.getQuantity() + "): ");
                        qty = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter new price (current: " + existing.getPrice() + "): ");
                        price = Double.parseDouble(scanner.nextLine());

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Use numbers only for ID, quantity, and price.");
                        break;
                    }

                    if (newId < 0 || qty < 0 || price < 0) {
                        System.out.println("Values must be non-negative.");
                        break;
                    }

                    ProductDAO.updateProduct(oldId, new Product(newId, newName, qty, price));
                }

                case 4 -> {
                    // Delete Product
                    int id;
                    try {
                        System.out.print("Enter product ID to delete: ");
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter a valid number.");
                        break;
                    }

                    // Check if product exists
                    List<Product> existingProducts = ProductDAO.getAllProducts();
                    boolean exists = existingProducts.stream().anyMatch(p -> p.getId() == id);
                    if (!exists) {
                        System.out.println("Product ID not found.");
                        break;
                    }

                    ProductDAO.deleteProduct(id);
                }

                case 5 -> {
                    // Sort by name
                    List<Product> sortedByName = ProductDAO.getSortedByName();
                    System.out.println("\nSorted by Name:\nID\tName\tQty\tPrice");
                    for (Product p : sortedByName) {
                        System.out.printf("%d\t%s\t%d\t%.2f\n",
                                p.getId(), p.getName(), p.getQuantity(), p.getPrice());
                    }
                }

                case 6 -> {
                    // Sort by price
                    List<Product> sortedByPrice = ProductDAO.getSortedByPrice();
                    System.out.println("\nSorted by Price:\nID\tName\tQty\tPrice");
                    for (Product p : sortedByPrice) {
                        System.out.printf("%d\t%s\t%d\t%.2f\n",
                                p.getId(), p.getName(), p.getQuantity(), p.getPrice());
                    }
                }

                case 7 -> {
                    // Inventory Summary
                    List<Product> products = ProductDAO.getAllProducts();
                    int totalItems = products.size();
                    int totalQty = products.stream().mapToInt(Product::getQuantity).sum();
                    double totalValue = products.stream().mapToDouble(p -> p.getPrice() * p.getQuantity()).sum();

                    System.out.println("\n=== Inventory Summary ===");
                    System.out.println("Total Products: " + totalItems);
                    System.out.println("Total Quantity: " + totalQty);
                    System.out.printf("Total Stock Value: %.2f\n", totalValue);
                }

                case 0 -> {
                    // Exit
                    System.out.println("Exiting...");
                    return;
                }

                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
