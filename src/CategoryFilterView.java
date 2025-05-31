import java.awt.*;
import java.util.Map;
import javax.swing.*;

public class CategoryFilterView extends JPanel {
    private final POSController controller;
    
    public CategoryFilterView(POSController controller) {
        this.controller = controller;
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(new Color(245, 245, 245));
    }
    
    public void initializeButtons(Map<Integer, String> categories) {
        removeAll();
        
        // Add "All" button
        JButton allBtn = createCategoryButton("All", "🛒");
        allBtn.setBackground(new Color(50, 120, 200));
        allBtn.setForeground(Color.WHITE);
        add(allBtn);
        
        // Add category buttons
        for (String category : categories.values()) {
            add(createCategoryButton(category, getCategoryIcon(category)));
        }
        
        revalidate();
        repaint();
    }
    
    private JButton createCategoryButton(String category, String icon) {
        JButton btn = new JButton();
        btn.setLayout(new BorderLayout());
        btn.setPreferredSize(new Dimension(90, 90));
        
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);
        
        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        
        JLabel nameLabel = new JLabel(category, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        content.add(iconLabel);
        content.add(Box.createVerticalStrut(5));
        content.add(nameLabel);
        
        btn.add(content, BorderLayout.CENTER);
        btn.setBackground(new Color(240, 240, 240));
        btn.addActionListener(e -> controller.filterProducts("", category));
        
        return btn;
    }
    
    private String getCategoryIcon(String category) {
        switch (category.toLowerCase()) {
            case "beverages": return "🥤";
            case "food": return "🍴";
            case "snacks": return "🍿";
            default: return "📦";
        }
    }
}