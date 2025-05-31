import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class POSGridView extends JPanel {
    private JPanel productGrid;
    private JPanel orderPanel;
    private JButton printInvoiceButton;
    private JButton payOnCardButton;
    private JTextArea orderTextArea;
    private JLabel subtotalLabel, taxLabel, totalLabel;
    private List<OrderItem> orderItems = new ArrayList<>();
    private final double TAX_RATE = 0.10;
    private JButton placeOrderButton;
    private Map<Integer, ProductCard> productCards = new HashMap<>();
    private String currentPaymentMethod = "Cash";
    private JTextField searchField;
    private Map<Integer, String> categories = new HashMap<>();
    private JPanel categoryButtonPanel;
    private Map<String, JButton> categoryButtons = new LinkedHashMap<>();
    private String selectedCategory = "All";

    public POSGridView() {
        initializeUI();
        loadCategoriesFromDatabase();
        loadProductsFromDatabase();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(245, 245, 245));

        JLabel label = new JLabel("🍿 Snacks");
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));

        // Create control panel at the top
        JPanel controlPanel = new JPanel(new BorderLayout(10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        controlPanel.setBackground(new Color(245, 245, 245));

        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchPanel.setBackground(new Color(245, 245, 245));
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(250, 25)); 
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(3, 18, 3, 18)
        ));
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { filterProducts(); }
            @Override public void removeUpdate(DocumentEvent e) { filterProducts(); }
            @Override public void changedUpdate(DocumentEvent e) { filterProducts(); }
        });

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Smaller font
        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);

        // Category filter panel
        categoryButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        categoryButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        categoryButtonPanel.setBackground(new Color(245, 245, 245));

        // Add "All" button by default
        JButton allButton = createSquareCategoryButton("All", "🛒");
        allButton.setBackground(new Color(85, 242, 164));
        allButton.setForeground(Color.WHITE);
        categoryButtonPanel.add(allButton);
        categoryButtons.put("All", allButton);

        // Add components to control panel
        controlPanel.add(searchPanel, BorderLayout.WEST);
        controlPanel.add(categoryButtonPanel, BorderLayout.CENTER);

        // Product grid setup
        productGrid = new JPanel();
        productGrid.setLayout(new BoxLayout(productGrid, BoxLayout.Y_AXIS));
        productGrid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        productGrid.setBackground(new Color(245, 245, 245));

        JScrollPane productScrollPane = new JScrollPane(productGrid);
        productScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        productScrollPane.setPreferredSize(new Dimension(900, 650));
        productScrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Order summary panel
        orderPanel = createOrderPanel();

        // Add components to main panel
        add(controlPanel, BorderLayout.NORTH);
        add(productScrollPane, BorderLayout.CENTER);
        add(new JScrollPane(orderPanel), BorderLayout.EAST);
    }
    private JPanel createActionPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBackground(Color.WHITE);

        placeOrderButton = new JButton("Place Order");
        JButton clearButton = new JButton("Clear Order");
        printInvoiceButton = new JButton("Print Invoice");
        payOnCardButton = new JButton("Pay on Card");

        // Style all buttons consistently
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        placeOrderButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);
        printInvoiceButton.setFont(buttonFont);
        payOnCardButton.setFont(buttonFont);

        placeOrderButton.setBackground(new Color(50, 150, 50));
        clearButton.setBackground(new Color(200, 50, 50));
        printInvoiceButton.setBackground(new Color(70, 130, 180));
        payOnCardButton.setBackground(new Color(150, 100, 200));

        placeOrderButton.setForeground(Color.WHITE);
        clearButton.setForeground(Color.WHITE);
        printInvoiceButton.setForeground(Color.WHITE);
        payOnCardButton.setForeground(Color.WHITE);

        placeOrderButton.setFocusPainted(false);
        clearButton.setFocusPainted(false);
        printInvoiceButton.setFocusPainted(false);
        payOnCardButton.setFocusPainted(false);

        Border buttonBorder = BorderFactory.createEmptyBorder(10, 0, 10, 0);
        placeOrderButton.setBorder(buttonBorder);
        clearButton.setBorder(buttonBorder);
        printInvoiceButton.setBorder(buttonBorder);
        payOnCardButton.setBorder(buttonBorder);

        placeOrderButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        printInvoiceButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        payOnCardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        placeOrderButton.addActionListener(e -> placeOrder());
        clearButton.addActionListener(e -> clearOrder());
        printInvoiceButton.addActionListener(e -> printInvoice());
        payOnCardButton.addActionListener(e -> showCardPaymentForm());

        panel.add(placeOrderButton);
        panel.add(clearButton);
        panel.add(printInvoiceButton);
        panel.add(payOnCardButton);
        clearButton.addActionListener(e -> clearOrder());

        // Disable buttons initially
        printInvoiceButton.setEnabled(false);
        payOnCardButton.setEnabled(false);

        return panel;
    }

    private void printInvoice() {
        if (orderItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items to print!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            orderTextArea.print();
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "Error printing invoice: " + ex.getMessage(),
                    "Print Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private JButton createSquareCategoryButton(String categoryName, String icon) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        Dimension buttonSize = new Dimension(90, 90);
        button.setPreferredSize(buttonSize);
        button.setMinimumSize(buttonSize);
        button.setMaximumSize(buttonSize);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(categoryName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(iconLabel);
        contentPanel.add(Box.createVerticalStrut(8));
        contentPanel.add(nameLabel);
        contentPanel.add(Box.createVerticalGlue());

        button.add(contentPanel, BorderLayout.CENTER);

        button.setBackground(new Color(240, 240, 240));
        button.setForeground(new Color(70, 70, 70));
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> {
            for (JButton btn : categoryButtons.values()) {
                btn.setBackground(new Color(240, 240, 240));
                btn.setForeground(new Color(70, 70, 70));
            }

            button.setBackground(new Color(85, 242, 164));
            button.setForeground(Color.WHITE);
            
            selectedCategory = categoryName;
            filterProducts();
        });

        return button;
    }

    private String getCategoryIcon(String categoryName) {
        switch(categoryName.toLowerCase()) {
            case "coffee": return "🥤";    
            case "food": return "🍽";         
            case "snacks": return "🍿";       
            case "all": return "🛒";         
            case "drinks": return "🧃";
            case "chocolate": return "🧃";
            case "Desserts": return "🍰";     
            case "alcohol": return "🍺";      
            default: return "🪙";             

        }
    }

    private void filterProducts() {
        String searchText = searchField.getText().toLowerCase();
        
        productGrid.removeAll();

        JPanel productsPanel = new JPanel(new GridLayout(0, 4, 15, 15));
        productsPanel.setBackground(new Color(245, 245, 245));
        productsPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 20, 15));

        for (ProductCard card : productCards.values()) {
            boolean matchesSearch = card.getName().toLowerCase().contains(searchText);
            boolean matchesCategory = selectedCategory.equals("All") || 
                                   card.getCategoryName().equalsIgnoreCase(selectedCategory);

            if (matchesSearch && matchesCategory) {
                productsPanel.add(card);
            }
        }

        if (productsPanel.getComponentCount() == 0) {
            JLabel noResults = new JLabel("No products found", JLabel.CENTER);
            noResults.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            noResults.setForeground(Color.GRAY);
            productGrid.add(noResults);
        } else {
            productGrid.add(productsPanel);
        }

        productGrid.revalidate();
        productGrid.repaint();
    }

    private void loadCategoriesFromDatabase() {
        String query = "SELECT id, name FROM category";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                categories.put(id, name);

                String icon = getCategoryIcon(name);
                JButton button = createSquareCategoryButton(name, icon);
                categoryButtonPanel.add(button);
                categoryButtons.put(name, button);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading categories: " + e.getMessage());
            loadSampleCategories();
        }

        categoryButtonPanel.revalidate();
        categoryButtonPanel.repaint();
    }

    private void loadSampleCategories() {
        Map<String, String> sampleCategories = Map.of(
            "Beverages", "🥤",
            "Food", "🍽️",
            "Snacks", "🍿",
            "Drinks", "🍺",
            "Desserts", "🍮",
            "Meat", "🥓"

        );

        int id = 1;
        for (Map.Entry<String, String> entry : sampleCategories.entrySet()) {
            categories.put(id++, entry.getKey());
            JButton button = createSquareCategoryButton(entry.getKey(), entry.getValue());
            categoryButtonPanel.add(button);
            categoryButtons.put(entry.getKey(), button);
        }
    }

    private void loadProductsFromDatabase() {
        String query = "SELECT p.id, p.name, p.price, p.image, p.stock_qty, c.name AS category_name " +
                      "FROM product p LEFT JOIN category c ON p.CatID = c.id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String imageName = rs.getString("image");
                int stockQty = rs.getInt("stock_qty");
                String categoryName = rs.getString("category_name");

                String imagePath = "D:/Y3S2/javaII/Testing_Java/src/Products/" + imageName;
                addProduct(id, name, price, imagePath, stockQty, categoryName);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage());
            loadSampleProducts();
        }
    }

    private void loadSampleProducts() {
        addProduct(1, "Original Count Next Buyer With One New Veg", 23.99, "no_image.jpg", 5, "Food");
        addProduct(2, "Fresh Orange Juice With Real Food", 23.99, "no_image.jpg", 10, "Beverages");
        addProduct(3, "Hard Sun/Head With Truck Shop", 0.00, "no_image.jpg", 8, "Snacks");
        addProduct(4, "Focus Sales With Chicken", 16.00, "no_image.jpg", 7, "Food");
        addProduct(5, "Trading Vegetable Sales - Happy Fruit", 1.00, "no_image.jpg", 15, "Food");
        addProduct(6, "Orange Juice With Real Food on Sugar", 5.99, "no_image.jpg", 20, "Beverages");
        addProduct(7, "Orange Cream Buyer With Fresh Green", 0.00, "no_image.jpg", 5, "Snacks");
        addProduct(8, "Apple &Farm", 2.99, "no_image.jpg", 50, "Food");
    }

    private void addProduct(int productId, String name, double price, String imgPath, int stockQty, String categoryName) {
        ProductCard card = new ProductCard(productId, name, price, imgPath, stockQty, categoryName);
        productCards.put(productId, card);
        filterProducts();
    }

    private JPanel createOrderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            new MatteBorder(0, 1, 0, 0, new Color(220, 220, 220)),
            new EmptyBorder(5, 15, 5, 15)
        ));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(350, getHeight()));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        
        JLabel titleLabel = new JLabel("___________Order List__________", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(60, 60, 60));
        titlePanel.add(titleLabel, BorderLayout.WEST);
        
        panel.add(titlePanel);

        orderTextArea = new JTextArea(35, 40);
        orderTextArea.setEditable(false);
        orderTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        orderTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        orderTextArea.setBackground(new Color(250, 250, 250));
        panel.add(new JScrollPane(orderTextArea));

        panel.add(Box.createVerticalStrut(15));
        panel.add(createTotalsPanel());

        panel.add(Box.createVerticalStrut(15));
        panel.add(createPaymentPanel());

        panel.add(Box.createVerticalStrut(20));
        panel.add(createActionPanel());

        return panel;
    }

    private JPanel createTotalsPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        panel.setBackground(Color.WHITE);

        subtotalLabel = new JLabel("$0.00", JLabel.RIGHT);
        taxLabel = new JLabel("$0.00", JLabel.RIGHT);
        totalLabel = new JLabel("$0.00", JLabel.RIGHT);

        subtotalLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        taxLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalLabel.setForeground(new Color(50, 120, 200));

        panel.add(createTotalLabel("Subtotal:"));
        panel.add(subtotalLabel);
        panel.add(createTotalLabel("Tax (10%):"));
        panel.add(taxLabel);
        panel.add(createTotalLabel("Total:"));
        panel.add(totalLabel);

        return panel;
    }
    
    private JLabel createTotalLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(new Color(100, 100, 100));
        return label;
    }
    
    private JPanel createPaymentPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            "Payment Method",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(100, 100, 100)
        ));
        panel.setBackground(Color.WHITE);

        JButton cashButton = createPaymentButton("Cash", new Color(100, 200, 100));
        JButton cardButton = createPaymentButton("Card", new Color(100, 150, 200));
        JButton qrButton = createPaymentButton("QR", new Color(200, 150, 100));

        cashButton.addActionListener(e -> setPaymentMethod("Cash"));
        cardButton.addActionListener(e -> setPaymentMethod("Card"));
        qrButton.addActionListener(e -> {
            setPaymentMethod("QRCode");
            generateAndShowQRCode(calculateTotal());
        });

        panel.add(cashButton);
        panel.add(cardButton);
        panel.add(qrButton);

        return panel;
    }

    private JButton createPaymentButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void addToOrder(int productId, String name, double price) {
        ProductCard card = productCards.get(productId);

        if (card == null) {
            JOptionPane.showMessageDialog(this, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (card.getStockQty() <= 0) {
            JOptionPane.showMessageDialog(this, "Product out of stock!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        card.decreaseStock();

        for (OrderItem item : orderItems) {
            if (item.getProductId() == productId) {
                item.incrementQuantity();
                updateOrderDisplay();
                return;
            }
        }

        orderItems.add(new OrderItem(productId, name, price));
        updateOrderDisplay();
    }

    private void placeOrder() {
        if (orderItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items in order!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);

            int orderId = createOrderRecord(con);
            addOrderItems(con, orderId);
            updateProductStocksInDB(con);

            con.commit();
            showReceipt(orderId);
            clearOrder();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error processing order: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int createOrderRecord(Connection con) throws SQLException {
        int staffId = getLoggedInStaffId();
        double total = calculateTotal();

        String paymentMethod;
        switch(currentPaymentMethod.toUpperCase()) {
            case "CASH": paymentMethod = "CASH"; break;
            case "CARD":
            case "CREDIT CARD":
            case "DEBIT CARD": paymentMethod = "CARD"; break;
            case "QR":
            case "QR CODE":
            case "QRCODE": paymentMethod = "QRCODE"; break;
            default: throw new SQLException("Invalid payment method: " + currentPaymentMethod);
        }

        String sql = "INSERT INTO sale (sale_date, payment_method, total_amount, staff_id) " +
                     "VALUES (CURRENT_TIMESTAMP, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, paymentMethod);
            stmt.setDouble(2, total);
            stmt.setInt(3, staffId);

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("Failed to create order record");
    }

    private void generateAndShowQRCode(double amount) {
        try {
            String qrContent = "POS Payment\nAmount: $" + String.format("%.2f", amount) + 
                             "\nDate: " + new java.util.Date();

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(
                qrContent, 
                BarcodeFormat.QR_CODE, 
                300, 300
            );

            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            JLabel qrLabel = new JLabel(new ImageIcon(qrImage));
            
            JPanel qrPanel = new JPanel(new BorderLayout());
            qrPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            qrPanel.add(qrLabel, BorderLayout.CENTER);
            
            JLabel amountLabel = new JLabel("Amount: $" + String.format("%.2f", amount), JLabel.CENTER);
            amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            qrPanel.add(amountLabel, BorderLayout.SOUTH);
            
            JOptionPane.showMessageDialog(
                this, 
                qrPanel, 
                "Scan QR Code", 
                JOptionPane.PLAIN_MESSAGE
            );

        } catch (WriterException e) {
            JOptionPane.showMessageDialog(this, 
                "Failed to generate QR code: " + e.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setPaymentMethod(String method) {
        currentPaymentMethod = method;
        JOptionPane.showMessageDialog(this, "Payment method set to: " + method, 
            "Payment Method", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addOrderItems(Connection con, int saleId) throws SQLException {
        String sql = "INSERT INTO sale_details (qty, unit_price, pid, sale_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            for (OrderItem item : orderItems) {
                stmt.setInt(1, item.getQuantity()); 
                stmt.setDouble(2, item.getPrice()); 
                stmt.setInt(3, item.getProductId()); 
                stmt.setInt(4, saleId);          
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private int getLoggedInStaffId() {
        if (System.getProperty("staff.id") != null) {
            return Integer.parseInt(System.getProperty("staff.id"));
        }
        return 1; 
    }

    private void updateProductStocksInDB(Connection con) throws SQLException {
        String sql = "UPDATE product SET stock_qty = stock_qty - ? WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            for (OrderItem item : orderItems) {
                stmt.setInt(1, item.getQuantity());
                stmt.setInt(2, item.getProductId());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private double calculateTotal() {
        double subtotal = orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        return subtotal + (subtotal * TAX_RATE);
    }

    private void showReceipt(int orderId) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("=== Order Information ===\n");
        receipt.append("Order #").append(orderId).append("\n\n");
        receipt.append("Items:\n");

        for (OrderItem item : orderItems) {
            receipt.append(String.format("%-25s %2d x $%6.2f\n",
                    truncateName(item.getName()), item.getQuantity(), item.getPrice()));
        }
        JOptionPane.showMessageDialog(this, receipt.toString(), "Order Placed", JOptionPane.INFORMATION_MESSAGE);
    }


    private String truncateName(String name) {
        return name.length() > 30 ? name.substring(0, 27) + "..." : name;
    }

    private void showCardPaymentForm() {
        if (orderItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items in order!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog();
        dialog.setTitle("Card Payment Information");
        dialog.setModal(true);
        dialog.setSize(400, 300);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField cardNumberField = new JTextField();
        JTextField expiryField = new JTextField();
        JTextField cvvField = new JTextField();
        JTextField nameField = new JTextField();

        formPanel.add(new JLabel("Card Number:"));
        formPanel.add(cardNumberField);
        formPanel.add(new JLabel("Expiry (MM/YY):"));
        formPanel.add(expiryField);
        formPanel.add(new JLabel("CVV:"));
        formPanel.add(cvvField);
        formPanel.add(new JLabel("Cardholder Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Amount:"));
        formPanel.add(new JLabel(String.format("$%.2f", calculateTotal())));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton submitButton = new JButton("Submit Payment");
        JButton cancelButton = new JButton("Cancel");

        submitButton.addActionListener(e -> {
            if (validateCardPayment(cardNumberField.getText(), expiryField.getText(), 
                                  cvvField.getText(), nameField.getText())) {
                currentPaymentMethod = "Card";
                placeOrder();
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(cancelButton);
        buttonPanel.add(submitButton);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private boolean validateCardPayment(String cardNumber, String expiry, String cvv, String name) {
        if (cardNumber.replaceAll("\\s", "").length() != 16) {
            JOptionPane.showMessageDialog(this, "Please enter a valid 16-digit card number", 
                                        "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!expiry.matches("(0[1-9]|1[0-2])/[0-9]{2}")) {
            JOptionPane.showMessageDialog(this, "Please enter expiry date in MM/YY format", 
                                        "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (cvv.length() != 3 && cvv.length() != 4) {
            JOptionPane.showMessageDialog(this, "Please enter a valid 3 or 4-digit CVV", 
                                        "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter cardholder name", 
                                        "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void updateOrderDisplay() {
        StringBuilder sb = new StringBuilder();
        double subtotal = 0.0;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb.append("INVOICE\n");
        sb.append("Date: ").append(dateFormat.format(new Date())).append("\n");
        sb.append("--------------------------------\n");
        sb.append("ITEMS:                             qty x price\n");
        sb.append("--------------------------------\n");

        for (OrderItem item : orderItems) {
            sb.append(String.format("%-30s %2d x $%6.2f\n",
                    truncateName(item.getName()), item.getQuantity(), item.getPrice()));
            subtotal += item.getPrice() * item.getQuantity();
            sb.append("--------------------------------\n");
        }

        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;

        orderTextArea.setText(sb.toString());
        subtotalLabel.setText(String.format("$%.2f", subtotal));
        taxLabel.setText(String.format("$%.2f", tax));
        totalLabel.setText(String.format("$%.2f", total));

        boolean hasItems = !orderItems.isEmpty();
        placeOrderButton.setEnabled(hasItems);
        printInvoiceButton.setEnabled(hasItems);
        payOnCardButton.setEnabled(hasItems);
    }

    private class ProductCard extends JPanel {
        private int productId;
        private String name;
        private double price;
        private int stockQty;
        private String categoryName;
        private JLabel stockLabel;
        private JButton addButton;

        public ProductCard(int productId, String name, double price, String imgPath, int stockQty, String categoryName) {
            this.productId = productId;
            this.name = name;
            this.price = price;
            this.stockQty = stockQty;
            this.categoryName = categoryName != null ? categoryName : "Uncategorized";
            initializeUI(imgPath);
        }

        private void initializeUI(String imgPath) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(200, 260));
            setMaximumSize(new Dimension(200, 260));

            JLabel imgLabel = new JLabel();
            imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            imgLabel.setIcon(createImageIcon(imgPath));
            add(Box.createVerticalStrut(5));
            add(imgLabel);

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setBackground(Color.WHITE);
            infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel nameLabel = new JLabel("<html><center>" + name + "</center></html>", JLabel.CENTER);
            JLabel priceLabel = new JLabel(String.format("$%.2f", price), JLabel.CENTER);
            stockLabel = new JLabel("Stock: " + stockQty, JLabel.CENTER);
            JLabel categoryLabel = new JLabel(categoryName, JLabel.CENTER);

            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            stockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            categoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            priceLabel.setForeground(new Color(50, 120, 200));
            stockLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            stockLabel.setForeground(new Color(120, 120, 120));
            categoryLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
            categoryLabel.setForeground(Color.GRAY);

            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(nameLabel);
            infoPanel.add(priceLabel);
            infoPanel.add(stockLabel);
            infoPanel.add(categoryLabel);
            infoPanel.add(Box.createVerticalStrut(5));

            addButton = new JButton("Add to Order");
            addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            addButton.setBackground(new Color(50, 150, 50));
            addButton.setForeground(Color.WHITE);
            addButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
            addButton.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
            addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addButton.setPreferredSize(new Dimension(100, 45)); 
            addButton.setMaximumSize(new Dimension(160, 45));
            
            addButton.addActionListener(e -> {
                POSGridView.this.addToOrder(productId, name, price);
            });

            add(infoPanel);
            add(Box.createVerticalStrut(5));
            add(addButton);
            add(Box.createVerticalStrut(5));

            if (stockQty == 0) {
                setEnabled(false);
                addButton.setEnabled(false);
                addButton.setText("Out of Stock");
                addButton.setBackground(new Color(200, 50, 50));
            }
            if (stockQty < 10){
                stockLabel.setForeground(Color.red);
            }
        }
        public void resetStock(int quantity) {
            this.stockQty += quantity;
            stockLabel.setText("Stock: " + stockQty);

            if (stockQty > 0) {
                setEnabled(true);
                addButton.setEnabled(true);
                addButton.setText("Add to Order");
                addButton.setBackground(new Color(50, 150, 50));
            }
        }
        private ImageIcon createImageIcon(String imgPath) {
            File imgFile = new File(imgPath);
            if (imgFile.exists()) {
                return new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH));
            } else {
                BufferedImage placeholder = new BufferedImage(150, 120, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = placeholder.createGraphics();
                g2.setPaint(new Color(240, 240, 240));
                g2.fillRect(0, 0, 150, 120);
                g2.setPaint(new Color(180, 180, 180));
                g2.drawString("No Image", 50, 60);
                g2.dispose();
                return new ImageIcon(placeholder);
            }
        }

        public void decreaseStock() {
            if (stockQty > 0) {
                stockQty--;
                stockLabel.setText("Stock: " + stockQty);

                if (stockQty == 0) {
                    setEnabled(false);
                    addButton.setEnabled(false);
                    addButton.setText("Out of Stock");
                    addButton.setBackground(new Color(200, 50, 50));
                    JOptionPane.showMessageDialog(this, "Product out of stock!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                if (stockQty == 9){
                    JOptionPane.showMessageDialog(this, "Product less than 10. Pleas add product to stock more!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    stockLabel.setForeground(Color.red);
                }
            }
        }

        public int getStockQty() { return stockQty; }
        public String getName() { return name; }
        public String getCategoryName() { return categoryName; }
        public JButton getAddButton() { return addButton; }
    }

    private static class OrderItem {
        private int productId;
        private String name;
        private double price;
        private int quantity;

        public OrderItem(int productId, String name, double price) {
            this.productId = productId;
            this.name = name;
            this.price = price;
            this.quantity = 1;
        }

        public int getProductId() { return productId; }
        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getQuantity() { return quantity; }

        public void incrementQuantity() { quantity++; }
    }
    private void clearOrder() {
        // Reset stock quantities in product cards
        for (OrderItem item : orderItems) {
            ProductCard card = productCards.get(item.getProductId());
            if (card != null) {
                card.resetStock(item.getQuantity());
            }
        }

        // Clear the order items
        orderItems.clear();

        // Reset payment method to default
        currentPaymentMethod = "Cash";

        // Update the display
        updateOrderDisplay();

        // Disable order-related buttons
        printInvoiceButton.setEnabled(false);
        payOnCardButton.setEnabled(false);
    }
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1604, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
