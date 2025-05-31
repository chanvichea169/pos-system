public class Product {
    private final int id;
    private final String name;
    private final double price;
    private int stockQty;
    private final String category;
    private final String imagePath;
    
    public Product(int id, String name, double price, int stockQty, 
                 String category, String imagePath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQty = stockQty;
        this.category = category;
        this.imagePath = imagePath;
    }
    
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQty() { return stockQty; }
    public String getCategory() { return category; }
    public String getImagePath() { return imagePath; }
    
    public void decreaseStock() { 
        if (stockQty > 0) stockQty--; 
    }
    public void increaseStock(int amount) { 
        stockQty += amount; 
    }
}