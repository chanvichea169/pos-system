import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

public class MyButton extends JButton {

    public MyButton(String text) {
        super(text);
        
        // Set the background to transparent
        setOpaque(true);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setBackground(new Color(0, 0, 0, 0)); // Fully transparent
        
        // Rounded corners
        setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, javax.swing.JComponent c) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(c.getBackground());
                g2d.fill(new RoundRectangle2D.Float(0, 0, c.getWidth(), c.getHeight(), 30, 30)); // 30 for corner roundness
                super.paint(g, c);
                g2d.dispose();
            }
        });
        
        // Hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(255, 0, 0)); // Change color on hover (red)
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(0, 0, 0, 0)); // Transparent background when not hovered
            }
        });
    }
}
