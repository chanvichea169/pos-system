import java.sql.*;
import java.util.*;

public class POSModel {
    private Map<Integer, Product> products = new HashMap<>();
    private Map<Integer, String> categories = new LinkedHashMap<>();
    private List<OrderItem> currentOrder = new ArrayList<>();
    
    public void loadCategoriesFromDB() {
        // Database loading logic
        categories.put(1, "Beverages");
        categories.put(2, "Food");
        categories.put(3, "Snacks");
    }
    
    public void loadProductsFromDB() {
        // Database loading logic
        products.put(1, new Product(1, "Coffee", 2.99, 50, "Beverages", "coffee.jpg"));
        products.put(2, new Product(2, "Sandwich", 5.99, 30, "Food", "sandwich.jpg"));
    }
    
    public List<Product> filterProducts(String searchText, String category) {
        List<Product> filtered = new ArrayList<>();
        String searchLower = searchText.toLowerCase();
        
        for (Product p : products.values()) {
            boolean matchesSearch = p.getName().toLowerCase().contains(searchLower);
            boolean matchesCategory = "All".equals(category) || 
                                     p.getCategory().equalsIgnoreCase(category);
            
            if (matchesSearch && matchesCategory) {
                filtered.add(p);
            }
        }
        return filtered;
    }
    
    public int saveOrderToDB(String paymentMethod) throws SQLException {
        // Database save logic
        return new Random().nextInt(1000); // Return generated order ID
    }
    
    // Getters and setters
    public Map<Integer, String> getCategories() { return Collections.unmodifiableMap(categories); }
    public List<Product> getAllProducts() { return new ArrayList<>(products.values()); }
    public Product getProduct(int id) { return products.get(id); }
    public List<OrderItem> getOrderItems() { return Collections.unmodifiableList(currentOrder); }
    public void addOrderItem(OrderItem item) { currentOrder.add(item); }
    public void decreaseProductStock(int productId) { /* implementation */ }
    public void clearCurrentOrder() { currentOrder.clear(); }
}