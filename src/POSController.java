import java.sql.*;
import javax.swing.*;

public class POSController {
    private POSModel model;
    private POSMainView view;
    private String currentPaymentMethod = "Cash";
    private final double TAX_RATE = 0.10;
    
    public POSController() {
        model = new POSModel();
        view = new POSMainView(this);
        initializeData();
    }
    
    private void initializeData() {
        model.loadCategoriesFromDB();
        model.loadProductsFromDB();
        view.initializeCategoryFilters(model.getCategories());
        view.displayProducts(model.getAllProducts());
    }
    
    // Called from ProductCard when "Add to Order" is clicked
    public void addProductToOrder(int productId) {
        try {
            Product product = model.getProduct(productId);
            if (product == null) return;
            
            if (product.getStockQty() <= 0) {
                showMessage("Product out of stock!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            model.decreaseProductStock(productId);
            model.addOrderItem(new OrderItem(product));
            updateOrderDisplay();
            
        } catch (Exception e) {
            showMessage("Error adding product: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void placeOrder() {
        if (model.getOrderItems().isEmpty()) {
            showMessage("No items in order!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int orderId = model.saveOrderToDB(currentPaymentMethod);
            showReceipt(orderId);
            model.clearCurrentOrder();
            updateOrderDisplay();
            
        } catch (SQLException e) {
            showMessage("Order failed: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateOrderDisplay() {
        OrderSummary summary = calculateOrderSummary();
        view.updateOrderSummary(model.getOrderItems(), summary);
    }
    
    private OrderSummary calculateOrderSummary() {
        double subtotal = model.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;
        return new OrderSummary(subtotal, tax, total);
    }
    
    private void showReceipt(int orderId) {
        Receipt receipt = new Receipt(orderId, model.getOrderItems(), 
                                     calculateOrderSummary(), currentPaymentMethod);
        view.showReceiptDialog(receipt.toString());
    }
    
    // Other controller methods...
    public POSMainView getMainView() { return view; }
    public void setPaymentMethod(String method) { currentPaymentMethod = method; }
    public void filterProducts(String searchText, String category) {
        view.displayProducts(model.filterProducts(searchText, category));
    }
    private void showMessage(String msg, String title, int type) {
        JOptionPane.showMessageDialog(view, msg, title, type);
    }

    void showCardPaymentForm() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void clearOrder() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Supporting inner classes
    public class OrderSummary {
        public final double subtotal;
        public final double tax;
        public final double total;
        
        public OrderSummary(double subtotal, double tax, double total) {
            this.subtotal = subtotal;
            this.tax = tax;
            this.total = total;
        }
    }
}