# Inventory Management System (Console-Based)

A simple Java console-based Inventory Management System that allows users to manage products with features to add, view, update, delete, sort, and summarize inventory data. The data is persisted in a text file (`inventory.txt`).

---

## Features

- **Add Product**  
  Add new products with unique IDs, names, quantities, and prices.  
  Duplicate product IDs are prevented.

- **View Products**  
  Display all products in the inventory in a tabular format.

- **Update Product**  
  Update existing product details including ID, name, quantity, and price.  
  Prevents updating to an existing product ID (maintains uniqueness).

- **Delete Product**  
  Delete a product by its ID after validation.

- **Sort Products**  
  - Sort by product **name** (ascending)  
  - Sort by product **price** (ascending)

- **Inventory Summary**  
  Shows total number of products, total quantity of items, and total stock value (quantity × price).

- **Persistent Storage**  
  All inventory data is saved to and loaded from a local file named `inventory.txt`.

---

## Error Handling and Input Validation

- **Invalid input handling**:  
  - Non-numeric inputs for ID, quantity, or price prompt error messages and rejections.  
  - Negative numbers for ID, quantity, or price are rejected.  
  - Empty product names are rejected.

- **File handling**:  
  - The system automatically creates the `inventory.txt` file if it does not exist.  
  - Malformed lines in the inventory file are skipped with appropriate debug messages.  
  - Detailed error messages are displayed if file operations fail.

- **Duplicate ID prevention**:  
  - When adding or updating a product, the system checks for duplicate product IDs and rejects duplicates.

- **Product existence checks**:  
  - For update and delete operations, if the product ID is not found, an error message is displayed.

---

## Project Structure
├── src
│ ├── main
│ │ └── Main.java # Main application with menu-driven interface
│ ├── model
│ │ └── Product.java # Product class (id, name, quantity, price)
│ ├── dao
│ │ └── ProductDAO.java # Data Access Object for product CRUD and sorting
│ └── util
│ └── FileUtil.java # File handling utility for loading and saving products
├── inventory.txt # Inventory data file (created automatically)


---

## How to Use

1. **Compile** the project using your favorite IDE or `javac`.

2. **Run** the `Main` class.

3. Use the console menu to:

   - Add products with unique IDs.  
   - View all products.  
   - Update or delete existing products by ID.  
   - View sorted lists by name or price.  
   - View an inventory summary.

4. **Exit** the program by choosing `0`.

---

## Example Menu Output
=== Inventory Menu ===

1.Add Product
2.View Products
3.Update Product
4.Delete Product
5.View Products Sorted by Name
6.View Products Sorted by Price
7.Inventory Summary
0.Exit
Choose an option:
![Console](https://github.com/mridulx7/Inventory-Management-System-Console-Based-Review-2/blob/bb97247cc305014a8125e6f93846021f161eaa63/Console.png)
7.Inventory Summary
![Inventory Summary](https://github.com/mridulx7/Inventory-Management-System-Console-Based-Review-2/blob/bb97247cc305014a8125e6f93846021f161eaa63/Inventory%20Summary.png)

---

## Important Notes

- Product IDs must be unique integers.
- Quantity and price must be non-negative numbers.
- Product name must not be empty.
- Data is saved immediately after each add, update, or delete operation.
- The `inventory.txt` file is created automatically in the working directory if missing.

---
