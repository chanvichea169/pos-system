import java.awt.*;
import javax.swing.*;
class ButtonEditor extends DefaultCellEditor {

    private String buttonType;
    private JButton button;
    private JTable table;
    private String label;

    public ButtonEditor(JCheckBox checkBox, JTable table, String buttonType) {
        super(checkBox);
        this.table = table;
        this.buttonType = buttonType;
        button = new JButton(buttonType.equals("delete") ? "Delete" : "Edit");
        button.setOpaque(true);

        button.addActionListener(e -> handleButtonClick());
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        return button;
    }

    private void handleButtonClick() {
        int row = table.getSelectedRow();
        if ("delete".equals(buttonType)) {
            // Perform delete action on the selected row
            int id = (Integer) table.getValueAt(row, 0);  
            deleteRow(id);
        } else if ("edit".equals(buttonType)) {
            // Perform edit action on the selected row
            int id = (Integer) table.getValueAt(row, 0); 
            editRow(id);
        }
    }

    private void deleteRow(int id) {
        // Delete the row with the given ID (you should handle this with your database logic)
        System.out.println("Deleting row with ID: " + id);
    }

    private void editRow(int id) {
        // Edit the row with the given ID (you should handle this with your database logic)
        System.out.println("Editing row with ID: " + id);
    }

    public Object getCellEditorValue() {
        return label;
    }
}