import java.time.LocalDateTime;
import java.util.*;

final class Product {
    private final String productId;
    private final String name;
    private final String category;
    private final String manufacturer;
    private final double basePrice;
    private final double weight;
    private final String[] features;
    private final Map<String, String> specifications;

    private Product(String productId, String name, String category, String manufacturer, double basePrice, double weight, String[] features, Map<String, String> specifications) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.basePrice = basePrice;
        this.weight = weight;
        this.features = Arrays.copyOf(features, features.length);
        this.specifications = new HashMap<>(specifications);
    }

    public static Product createElectronics(String id, String name, String manufacturer, double price, double weight, String[] features, Map<String, String> specs) {
        return new Product(id, name, "Electronics", manufacturer, price, weight, features, specs);
    }

    public static Product createClothing(String id, String name, String manufacturer, double price, double weight, String[] features, Map<String, String> specs) {
        return new Product(id, name, "Clothing", manufacturer, price, weight, features, specs);
    }

    public static Product createBooks(String id, String name, String manufacturer, double price, double weight, String[] features, Map<String, String> specs) {
        return new Product(id, name, "Books", manufacturer, price, weight, features, specs);
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getManufacturer() { return manufacturer; }
    public double getBasePrice() { return basePrice; }
    public double getWeight() { return weight; }
    public String[] getFeatures() { return Arrays.copyOf(features, features.length); }
    public Map<String, String> getSpecifications() { return new HashMap<>(specifications); }

    public final double calculateTax(String region) {
        switch (region.toUpperCase()) {
            case "US": return basePrice * 0.07;
            case "EU": return basePrice * 0.20;
            case "IN": return basePrice * 0.18;
            default: return basePrice * 0.10;
        }
    }

    public String toString() { return "Product{" + "id='" + productId + '\'' + ", name='" + name + '\'' + ", category='" + category + '\'' + '}'; }
}

class Customer {
    private final String customerId;
    private final String email;
    private String name;
    private String phoneNumber;
    private String preferredLanguage;
    private final String accountCreationDate;

    Customer(String id, String email, String creationDate) {
        this.customerId = id;
        this.email = email;
        this.accountCreationDate = creationDate;
    }

    String getCreditRating() { return "GOOD"; }
    public String getPublicProfile() { return "Customer{" + "id='" + customerId + '\'' + ", name='" + name + '\'' + '}'; }

    public String getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public void setName(String name) { if (name != null && !name.isBlank()) this.name = name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { if (phoneNumber != null && !phoneNumber.isBlank()) this.phoneNumber = phoneNumber; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { if (preferredLanguage != null && !preferredLanguage.isBlank()) this.preferredLanguage = preferredLanguage; }
    public String getAccountCreationDate() { return accountCreationDate; }

    public String toString() { return "Customer{" + "id='" + customerId + '\'' + ", email='" + email + '\'' + '}'; }
}

class ShoppingCart {
    private final String cartId;
    private final String customerId;
    private final List<Object> items = new ArrayList<>();
    private double totalAmount;
    private int itemCount;

    ShoppingCart(String cartId, String customerId) {
        this.cartId = cartId;
        this.customerId = customerId;
    }

    public boolean addItem(Object product, int quantity) {
        if (product instanceof Product && quantity > 0) {
            for (int i = 0; i < quantity; i++) items.add(product);
            itemCount += quantity;
            totalAmount += ((Product) product).getBasePrice() * quantity;
            totalAmount -= calculateDiscount();
            return true;
        }
        return false;
    }

    private double calculateDiscount() {
        if (itemCount > 5) return totalAmount * 0.05;
        return 0.0;
    }

    String getCartSummary() { return "Cart{" + "id='" + cartId + '\'' + ", items=" + itemCount + ", total=" + totalAmount + '}'; }
    public String toString() { return getCartSummary(); }
}

class Order {
    private final String orderId;
    private final LocalDateTime orderTime;

    Order(String id) {
        this.orderId = id;
        this.orderTime = LocalDateTime.now();
    }

    public String toString() { return "Order{" + "id='" + orderId + '\'' + ", time=" + orderTime + '}'; }
}

class PaymentProcessor {
    private final String processorId;
    private final String securityKey;

    PaymentProcessor(String id, String key) {
        this.processorId = id;
        this.securityKey = key;
    }

    public boolean processPayment(double amount) { return amount > 0; }
    public String toString() { return "PaymentProcessor{" + "id='" + processorId + '\'' + '}'; }
}

class ShippingCalculator {
    private final Map<String, Double> shippingRates;

    ShippingCalculator(Map<String, Double> rates) { this.shippingRates = new HashMap<>(rates); }
    public double calculateShipping(String region, double weight) { return shippingRates.getOrDefault(region, 10.0) * weight; }
}

public final class ECommerceSystem {
    private static final Map<String, Object> productCatalog = new HashMap<>();

    public static boolean processOrder(Object order, Object customer) {
        return (order instanceof Order) && (customer instanceof Customer);
    }

    public static void addProduct(Product product) { productCatalog.put(product.getProductId(), product); }
    public static Map<String, Object> getProductCatalog() { return new HashMap<>(productCatalog); }

    public String toString() { return "ECommerceSystem{" + "products=" + productCatalog.keySet() + '}'; }
}

class Demo {
    public static void main(String[] args) {
        Product laptop = Product.createElectronics("E101", "Laptop", "TechBrand", 1200.0, 2.5, new String[]{"SSD", "16GB RAM"}, Map.of("CPU", "i7"));
        Customer customer = new Customer("C001", "john@example.com", "2023-01-01");
        customer.setName("John Doe");
        ShoppingCart cart = new ShoppingCart("Cart001", customer.getCustomerId());
        cart.addItem(laptop, 2);
        Order order = new Order("O1001");
        PaymentProcessor processor = new PaymentProcessor("P01", "SEC123");
        ShippingCalculator shipping = new ShippingCalculator(Map.of("US", 5.0, "IN", 2.0));
        ECommerceSystem.addProduct(laptop);
        boolean processed = ECommerceSystem.processOrder(order, customer);
        System.out.println(cart);
        System.out.println(order);
        System.out.println("Payment: " + processor.processPayment(2400.0));
        System.out.println("Shipping: " + shipping.calculateShipping("US", laptop.getWeight()));
        System.out.println("Order Processed: " + processed);
    }
}