import java.awt.Component;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // For Edit icon in column 5
        if (column == 5) {
            setIcon(new ImageIcon(getClass().getResource("/img/o.png")));  // Edit icon
        }
        // For Delete icon in column 6
        else if (column == 6) {
            setIcon(new ImageIcon(getClass().getResource("/img/pos.png")));  // Delete icon
        }
        return this;
    }
}