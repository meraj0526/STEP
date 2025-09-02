import java.util.*;

class Product {
    private String productId;
    private String productName;
    private double price;
    private String category;
    private int stockQuantity;

    private static int totalProducts = 0;
    private static Set<String> categories = new HashSet<>();

    public Product(String productId, String productName, double price, String category, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        totalProducts++;
        categories.add(category);
    }

    // Getters
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getStockQuantity() { return stockQuantity; }

    public void reduceStock(int quantity) {
        if (quantity <= stockQuantity) {
            stockQuantity -= quantity;
        } else {
            throw new IllegalArgumentException("Not enough stock available.");
        }
    }

    public void increaseStock(int quantity) {
        stockQuantity += quantity;
    }

    public static int getTotalProducts() {
        return totalProducts;
    }

    public static Set<String> getCategories() {
        return categories;
    }

    public static Product findProductById(Product[] products, String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public static List<Product> getProductsByCategory(Product[] products, String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return productId + " | " + productName + " | " + category + " | $" + price + " | Stock: " + stockQuantity;
    }
}

class ShoppingCart {
    private String cartId;
    private String customerName;
    private Map<Product, Integer> items;
    private double cartTotal;

    public ShoppingCart(String cartId, String customerName) {
        this.cartId = cartId;
        this.customerName = customerName;
        this.items = new HashMap<>();
        this.cartTotal = 0.0;
    }

    public void addProduct(Product product, int quantity) {
        if (product.getStockQuantity() < quantity) {
            System.out.println("Not enough stock for " + product.getProductName());
            return;
        }
        items.put(product, items.getOrDefault(product, 0) + quantity);
        product.reduceStock(quantity);
        calculateTotal();
        System.out.println(quantity + " x " + product.getProductName() + " added to cart.");
    }

    public void removeProduct(String productId) {
        Product toRemove = null;
        for (Product p : items.keySet()) {
            if (p.getProductId().equals(productId)) {
                toRemove = p;
                break;
            }
        }
        if (toRemove != null) {
            int qty = items.remove(toRemove);
            toRemove.increaseStock(qty);
            calculateTotal();
            System.out.println(toRemove.getProductName() + " removed from cart.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    public void calculateTotal() {
        cartTotal = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            cartTotal += entry.getKey().getPrice() * entry.getValue();
        }
    }

    public void displayCart() {
        System.out.println("-------------------------------------------------");
        System.out.println("Shopping Cart for " + customerName + " (ID: " + cartId + ")");
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                System.out.println(entry.getKey().getProductName() + " x " + entry.getValue() +
                        " = $" + (entry.getKey().getPrice() * entry.getValue()));
            }
        }
        System.out.println("Cart Total: $" + cartTotal);
        System.out.println("-------------------------------------------------");
    }

    public void checkout() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty. Cannot checkout.");
            return;
        }
        displayCart();
        System.out.println("Checkout successful! Thank you for shopping, " + customerName);
        items.clear();
        cartTotal = 0.0;
    }
}

public class Assignment2_Meraj {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create products
        Product[] products = {
            new Product("P101", "Laptop", 75000, "Electronics", 10),
            new Product("P102", "Smartphone", 25000, "Electronics", 15),
            new Product("P103", "Headphones", 2000, "Electronics", 20),
            new Product("P104", "T-shirt", 500, "Clothing", 30),
            new Product("P105", "Jeans", 1500, "Clothing", 25),
            new Product("P106", "Microwave", 8000, "Appliances", 5),
            new Product("P107", "Refrigerator", 40000, "Appliances", 3),
            new Product("P108", "Book - Java", 600, "Books", 40),
            new Product("P109", "Book - Python", 700, "Books", 35),
            new Product("P110", "Shoes", 2000, "Clothing", 20),
        };

        ShoppingCart cart = new ShoppingCart("C001", "Meraj");

        int choice;
        do {
            System.out.println("\n==== Online Shopping Cart Menu ====");
            System.out.println("1. View All Products");
            System.out.println("2. View Products by Category");
            System.out.println("3. Add Product to Cart");
            System.out.println("4. Remove Product from Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    for (Product p : products) {
                        System.out.println(p);
                    }
                    break;
                case 2:
                    System.out.println("Available categories: " + Product.getCategories());
                    System.out.print("Enter category: ");
                    String category = sc.nextLine();
                    List<Product> filtered = Product.getProductsByCategory(products, category);
                    if (filtered.isEmpty()) {
                        System.out.println("No products found in this category.");
                    } else {
                        for (Product p : filtered) {
                            System.out.println(p);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter product ID to add: ");
                    String addId = sc.nextLine();
                    Product toAdd = Product.findProductById(products, addId);
                    if (toAdd != null) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        cart.addProduct(toAdd, qty);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter product ID to remove: ");
                    String removeId = sc.nextLine();
                    cart.removeProduct(removeId);
                    break;
                case 5:
                    cart.displayCart();
                    break;
                case 6:
                    cart.checkout();
                    break;
                case 7:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 7);

        sc.close();
    }
}
