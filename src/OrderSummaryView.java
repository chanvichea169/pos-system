import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class OrderSummaryView extends JPanel {
    private final JTextArea orderTextArea;

    private JLabel subtotalLabel = null;
    private JLabel taxLabel = null;
    private JLabel totalLabel;
    private final POSController controller;
    
    public OrderSummaryView(POSController controller) {
        this.controller = controller;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
            new MatteBorder(0, 1, 0, 0, new Color(220, 220, 220)),
            new EmptyBorder(15, 15, 15, 15)
        ));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(350, 700));
        
        // Title
        JLabel title = new JLabel("Order Summary", JLabel.LEFT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(title);
        add(Box.createVerticalStrut(20));
        
        // Order items
        orderTextArea = new JTextArea(15, 30);
        orderTextArea.setEditable(false);
        orderTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(orderTextArea));
        add(Box.createVerticalStrut(20));
        
        // Totals
        add(createTotalsPanel());
        add(Box.createVerticalStrut(20));
        
        // Payment buttons
        add(createPaymentButtons());
        add(Box.createVerticalStrut(20));
        
        // Action buttons
        add(createActionButtons());
    }
    
    public void updateDisplay(List<OrderItem> items, POSController.OrderSummary summary) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        sb.append("Date: ").append(sdf.format(new Date())).append("\n");
        sb.append("--------------------------------\n");
        
        for (OrderItem item : items) {
            sb.append(String.format("%-20s %2d x $%6.2f\n", 
                item.getName(), item.getQuantity(), item.getPrice()));
        }
        
        orderTextArea.setText(sb.toString());
        subtotalLabel.setText(String.format("$%.2f", summary.subtotal));
        taxLabel.setText(String.format("$%.2f", summary.tax));
        totalLabel.setText(String.format("$%.2f", summary.total));
    }
    
    private JPanel createTotalsPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        
        subtotalLabel = new JLabel("$0.00", JLabel.RIGHT);
        taxLabel = new JLabel("$0.00", JLabel.RIGHT);
        totalLabel = new JLabel("$0.00", JLabel.RIGHT);
        
        panel.add(new JLabel("Subtotal:"));
        panel.add(subtotalLabel);
        panel.add(new JLabel("Tax (10%):"));
        panel.add(taxLabel);
        panel.add(new JLabel("Total:"));
        panel.add(totalLabel);
        
        return panel;
    }
    
    private JPanel createPaymentButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        
        JButton cashBtn = createPaymentButton("Cash", new Color(100, 200, 100));
        JButton cardBtn = createPaymentButton("Card", new Color(100, 150, 200));
        JButton qrBtn = createPaymentButton("QR", new Color(200, 150, 100));
        
        cashBtn.addActionListener(e -> controller.setPaymentMethod("Cash"));
        cardBtn.addActionListener(e -> controller.showCardPaymentForm());
        qrBtn.addActionListener(e -> {
            controller.setPaymentMethod("QR");
            // Show QR code dialog
        });
        
        panel.add(cashBtn);
        panel.add(cardBtn);
        panel.add(qrBtn);
        
        return panel;
    }
    
    private JButton createPaymentButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }
    
    private JPanel createActionButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        
        JButton placeOrderBtn = new JButton("Place Order");
        placeOrderBtn.setBackground(new Color(70, 130, 180));
        placeOrderBtn.setForeground(Color.WHITE);
        placeOrderBtn.addActionListener(e -> controller.placeOrder());
        
        JButton clearBtn = new JButton("Clear Order");
        clearBtn.setBackground(new Color(200, 50, 50));
        clearBtn.setForeground(Color.WHITE);
        clearBtn.addActionListener(e -> controller.clearOrder());
        
        panel.add(placeOrderBtn);
        panel.add(clearBtn);
        
        return panel;
    }
}