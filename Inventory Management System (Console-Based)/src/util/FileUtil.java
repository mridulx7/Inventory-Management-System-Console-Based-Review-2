package util;

import java.io.*;
import java.util.*;
import model.Product;

public class FileUtil {
    // Path of the inventory file used for saving/loading product data
    private static final String FILE_PATH = "inventory.txt";
   
    /**
     * Creates the inventory file if it doesn't exist.
     * Prints status messages about the creation result.
     */
    public static void initializeFile() {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {  // Check if file already exists
                if (file.createNewFile()) {  // Attempt to create new file
                    System.out.println("inventory.txt created successfully.");
                } else {
                    System.out.println("Failed to create inventory.txt.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error while creating the file: " + e.getMessage());
        }
    }

    /**
     * Returns the inventory file path.
     */
    public static String getFilePath() {
        return FILE_PATH;
    }

    /**
     * Reads product data from the inventory file.
     * Each line is expected to have 4 comma-separated values: id, name, quantity, price.
     * Skips malformed lines and prints debug info.
     * Returns a list of Product objects loaded from the file.
     */
    public static List<Product> loadFromFile() {
        List<Product> products = new ArrayList<>();
        File file = new File(FILE_PATH);

        // Debug: Print absolute path to help verify file location
        System.out.println("🔍 Looking for file at: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("File not found.");
            return products;  // Return empty list if file doesn't exist
        }

        // Try-with-resources to ensure BufferedReader is closed automatically
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {  // Read file line by line
                String[] parts = line.trim().split(",");  // Split line by comma
                if (parts.length == 4) {  // Expect exactly 4 parts per line
                    try {
                        // Parse and trim each part to create a Product
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        int quantity = Integer.parseInt(parts[2].trim());
                        double price = Double.parseDouble(parts[3].trim());
                        products.add(new Product(id, name, quantity, price));
                    } catch (NumberFormatException e) {
                        // Skip line if any number is not correctly formatted
                        System.out.println("Skipping malformed line: " + line);
                    }
                } else {
                    // Line does not have expected number of fields
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        return products;
    }

    /**
     * Saves the given list of products to the inventory file.
     * Each product is written as a single line with comma-separated values: id,name,quantity,price.
     */
    public static void saveToFile(List<Product> products) {
        // Try-with-resources to ensure PrintWriter is closed automatically
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Product p : products) {
                pw.println(p.getId() + "," + p.getName() + "," + p.getQuantity() + "," + p.getPrice());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

