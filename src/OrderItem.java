public class OrderItem {
    private final Product product;
    private int quantity;
    
    public OrderItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }
    
    public void incrementQuantity() { quantity++; }
    
    // Getters
    public int getProductId() { return product.getId(); }
    public String getName() { return product.getName(); }
    public double getPrice() { return product.getPrice(); }
    public int getQuantity() { return quantity; }
    public double getItemTotal() {  int price = 0;
    return price * quantity; }
}