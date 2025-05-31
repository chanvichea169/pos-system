import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Receipt {
    private final int orderId;
    private final List<OrderItem> items;
    private final POSController.OrderSummary summary;
    private final String paymentMethod;
    private final String date;
    
    public Receipt(int orderId, List<OrderItem> items, 
                  POSController.OrderSummary summary, String paymentMethod) {
        this.orderId = orderId;
        this.items = items;
        this.summary = summary;
        this.paymentMethod = paymentMethod;
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ORDER RECEIPT ===\n");
        sb.append("Order #").append(orderId).append("\n");
        sb.append("Date: ").append(date).append("\n\n");
        sb.append("ITEMS:\n");
        
        for (OrderItem item : items) {
            sb.append(String.format("%-20s %2d x $%6.2f\n", 
                truncate(item.getName(), 20), item.getQuantity(), item.getPrice()));
        }
        
        sb.append("\nSUBTOTAL: $").append(String.format("%.2f", summary.subtotal)).append("\n");
        sb.append("TAX (10%): $").append(String.format("%.2f", summary.tax)).append("\n");
        sb.append("TOTAL: $").append(String.format("%.2f", summary.total)).append("\n\n");
        sb.append("Payment Method: ").append(paymentMethod).append("\n");
        sb.append("Thank you for your business!");
        
        return sb.toString();
    }
    
    private String truncate(String s, int length) {
        return s.length() > length ? s.substring(0, length-3) + "..." : s;
    }
}