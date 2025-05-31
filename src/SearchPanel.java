import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchPanel extends JPanel {
    private final POSController controller;
    
    public SearchPanel(POSController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(245, 245, 245));
        
        JLabel searchLabel = new JLabel("Search:");
        JTextField searchField = new JTextField(20);
        
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { filter(); }
            public void insertUpdate(DocumentEvent e) { filter(); }
            public void removeUpdate(DocumentEvent e) { filter(); }
            
            private void filter() {
                controller.filterProducts(searchField.getText(), "All");
            }
        });
        
        add(searchLabel, BorderLayout.WEST);
        add(searchField, BorderLayout.CENTER);
    }
}