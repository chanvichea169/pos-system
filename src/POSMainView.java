import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.*;

public class POSMainView extends JPanel {
    private final POSController controller;
    private ProductGridView productGridView;
    private OrderSummaryView orderSummaryView;
    private CategoryFilterView categoryFilterView;
    
    public POSMainView(POSController controller) {
        this.controller = Objects.requireNonNull(controller, "Controller cannot be null");
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Initialize components
        this.productGridView = new ProductGridView(controller);
        this.orderSummaryView = new OrderSummaryView(controller);
        this.categoryFilterView = new CategoryFilterView(controller);
        
        initializeUI();
    }
    
    private void initializeUI() {
        // Search and filter panel at top
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new SearchPanel(controller), BorderLayout.WEST);
        topPanel.add(categoryFilterView, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        
        // Product grid in center
        add(productGridView, BorderLayout.CENTER);
        
        // Order summary on right with scroll
        JScrollPane orderScrollPane = new JScrollPane(orderSummaryView);
        orderScrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(orderScrollPane, BorderLayout.EAST);
    }
    
    public void initializeCategoryFilters(Map<Integer, String> categories) {
        if (categoryFilterView == null) {
            throw new IllegalStateException("CategoryFilterView not initialized");
        }
        categoryFilterView.initializeButtons(categories);
    }

    public void displayProducts(List<Product> products) {
        if (productGridView == null) {
            throw new IllegalStateException("ProductGridView not initialized");
        }
        productGridView.displayProducts(products != null ? products : List.of());
    }
    
    public void updateOrderSummary(List<OrderItem> items, POSController.OrderSummary summary) {
        if (orderSummaryView == null) {
            throw new IllegalStateException("OrderSummaryView not initialized");
        }
        orderSummaryView.updateDisplay(
            items != null ? items : List.of(),
            summary != null ? summary : controller.new OrderSummary(0, 0, 0)
        );
    }
    public void showReceiptDialog(String receiptText) {
        JTextArea receiptArea = new JTextArea(receiptText);
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(receiptArea);
        scrollPane.setPreferredSize(new Dimension(400, 500));
        
        JOptionPane.showMessageDialog(
            this, 
            scrollPane, 
            "Order Receipt", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}