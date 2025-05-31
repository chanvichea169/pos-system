import java.awt.*;
import java.util.List;
import javax.swing.*;

public class ProductGridView extends JScrollPane {
    private final JPanel productGrid;
    private final POSController controller;
    
    public ProductGridView(POSController controller) {
        this.controller = controller;
        productGrid = new JPanel();
        productGrid.setLayout(new BoxLayout(productGrid, BoxLayout.Y_AXIS));
        productGrid.setBackground(new Color(245, 245, 245));
        setViewportView(productGrid);
        setBorder(BorderFactory.createEmptyBorder());
    }
    
    public void displayProducts(List<Product> products) {
        productGrid.removeAll();
        
        if (products.isEmpty()) {
            JLabel emptyLabel = new JLabel("No products found", JLabel.CENTER);
            emptyLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            productGrid.add(emptyLabel);
        } else {
            JPanel gridPanel = new JPanel(new GridLayout(0, 4, 15, 15));
            gridPanel.setBackground(new Color(245, 245, 245));
            
            for (Product product : products) {
                gridPanel.add(new ProductCard(product, controller));
            }
            productGrid.add(gridPanel);
        }
        
        productGrid.revalidate();
        productGrid.repaint();
    }
}