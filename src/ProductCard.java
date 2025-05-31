import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ProductCard extends JPanel {
    private final Product product;
    private final POSController controller;
    private JLabel stockLabel;
    
    public ProductCard(Product product, POSController controller) {
        this.product = product;
        this.controller = controller;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        setBackground(Color.WHITE);
        initializeUI();
    }
    
    private void initializeUI() {
        // Product image
        add(createImagePanel());
        
        // Product info
        JLabel nameLabel = new JLabel(product.getName(), JLabel.CENTER);
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JLabel priceLabel = new JLabel(String.format("$%.2f", product.getPrice()), JLabel.CENTER);
        priceLabel.setAlignmentX(CENTER_ALIGNMENT);
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        priceLabel.setForeground(new Color(50, 120, 200));
        
        stockLabel = new JLabel("Stock: " + product.getStockQty(), JLabel.CENTER);
        stockLabel.setAlignmentX(CENTER_ALIGNMENT);
        stockLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        JLabel categoryLabel = new JLabel(product.getCategory(), JLabel.CENTER);
        categoryLabel.setAlignmentX(CENTER_ALIGNMENT);
        categoryLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        
        add(Box.createVerticalStrut(5));
        add(nameLabel);
        add(priceLabel);
        add(stockLabel);
        add(categoryLabel);
        add(Box.createVerticalStrut(5));
        
        // Add to order button
        JButton addButton = new JButton("Add to Order");
        addButton.setAlignmentX(CENTER_ALIGNMENT);
        addButton.setBackground(new Color(50, 150, 50));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> controller.addProductToOrder(product.getId()));
        add(addButton);
    }
    
    private JPanel createImagePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.add(new JLabel(loadProductImage()));
        return panel;
    }
    
    private ImageIcon loadProductImage() {
        try {
            return new ImageIcon(product.getImagePath());
        } catch (Exception e) {
            // Return placeholder if image not found
            BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = img.createGraphics();
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(0, 0, 100, 100);
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawString("No Image", 25, 50);
            g2d.dispose();
            return new ImageIcon(img);
        }
    }
}