import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class frmUProductView extends javax.swing.JPanel {

    private frmProductAdd productForm = null;
    private static final String IMAGE_FOLDER = "D:/Y3S2/javaII/Testing_Java/src/Products/";
    public frmUProductView() {
        initComponents();
        
        tblProduct.getTableHeader().setPreferredSize(new java.awt.Dimension(tblProduct.getTableHeader().getPreferredSize().width, 40)); 

        tblProduct.getTableHeader().setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
                c.setBackground(new java.awt.Color(57, 117, 247));
                c.setForeground(new java.awt.Color(247, 249, 252));
                return c;
            }
        });
        tblProduct.getColumnModel().getColumn(5).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    JLabel label = new JLabel((ImageIcon) value);
                    label.setHorizontalAlignment(JLabel.CENTER); 
                    return label;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
        tblProduct.getColumnModel().getColumn(6).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    JLabel label = new JLabel((ImageIcon) value);
                    label.setHorizontalAlignment(JLabel.CENTER); 
                    return label;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
        tblProduct.getColumnModel().getColumn(7).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    JLabel label = new JLabel((ImageIcon) value);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    return label;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
        

        tblProduct.getColumnModel().getColumn(3).setPreferredWidth(100);

        tblProduct.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tblProduct.getColumnModel().getColumn(0).setPreferredWidth(90);  
        tblProduct.getColumnModel().getColumn(1).setPreferredWidth(478);
        tblProduct.getColumnModel().getColumn(2).setPreferredWidth(350);  
        tblProduct.getColumnModel().getColumn(3).setPreferredWidth(250);
        tblProduct.getColumnModel().getColumn(4).setPreferredWidth(170);  
        tblProduct.getColumnModel().getColumn(5).setPreferredWidth(100); 
        tblProduct.getColumnModel().getColumn(6).setPreferredWidth(80);  
        tblProduct.getColumnModel().getColumn(7).setPreferredWidth(80);
        tblProduct.getColumnModel().getColumn(8).setPreferredWidth(1);
        tblProduct.revalidate();
        tblProduct.repaint();
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchProduct(txtSearch.getText());
           
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchProduct(txtSearch.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // This method is needed for compatibility but you can leave it empty.
            }
        });

        fetchData();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(253, 253, 253));
        setMinimumSize(new java.awt.Dimension(1280, 1920));

        tblProduct.setAutoCreateRowSorter(true);
        tblProduct.setFont(new java.awt.Font("Kh Dangrek", 0, 14));
        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "No", "Name", "Price", "In_Stock","Category_Name","Picture","","",""
            }
        ));
        tblProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblProduct.setDoubleBuffered(true);
        tblProduct.setDragEnabled(true);
        tblProduct.setFillsViewportHeight(true);
        tblProduct.setFocusCycleRoot(true);
        tblProduct.setFocusTraversalPolicyProvider(true);
        tblProduct.setInheritsPopupMenu(true);
        tblProduct.setRowHeight(50);
        tblProduct.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblProduct.setShowGrid(false);
        tblProduct.setSurrendersFocusOnKeystroke(true);
        jScrollPane2.setViewportView(tblProduct);
        tblProduct.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Bundle"); // NOI18N
        jLabel1.setText(bundle.getString("frmUProductView.jLabel1.text")); // NOI18N

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSearch.setText(bundle.getString("frmUProductView.txtSearch.text")); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText(bundle.getString("frmUProductView.jLabel2.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1646, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 52, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(130, 130, 130))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(663, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        searchProduct(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchActionPerformed
    public void fetchData() {
        DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
        model.setRowCount(0); 
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.id, p.name, p.price, p.stock_qty, p.image, p.CatID, c.Catname FROM product p JOIN category c ON p.CatID = c.id ORDER BY p.id ASC")) {

            while (rs.next()) {
                
                int productId = rs.getInt("id");
                String productName = rs.getString("name");
                double productPrice = rs.getDouble("price");
                int productStockQty = rs.getInt("stock_qty");
                String imageName = rs.getString("image");
                String categoryName = rs.getString("Catname");

                ImageIcon productImageIcon = getImageIcon(imageName);

                model.addRow(new Object[]{
                    productId,
                    productName,
                    productPrice,
                    productStockQty,
                    categoryName,
                    productImageIcon,
                    new ImageIcon(""),
                    new ImageIcon(""),
                    imageName
                });
            }
        } catch (SQLException e) {
            showError("Database connection error: " + e.getMessage());
        }
    }

    private ImageIcon getImageIcon(String imageName) {
         if (imageName != null && !imageName.isEmpty()) {
             File imageFile = new File(IMAGE_FOLDER + imageName);
             if (imageFile.exists()) {
                 ImageIcon icon = new ImageIcon(IMAGE_FOLDER + imageName);
                 Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                 return new ImageIcon(img);
             }
         }
         return null;
     }
    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void searchProduct(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
        model.setRowCount(0); 
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (con == null) {
                throw new SQLException("Database connection failed.");
            }
            String sql = "SELECT * FROM product WHERE LOWER(name) LIKE LOWER(?) OR price LIKE ? ORDER BY id ASC";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock_qty"),
                    rs.getString("CatID"),
                    new ImageIcon("D:/Y3S2/javaII/Testing_Java/src/Products/" + rs.getString("image")), 
                    new ImageIcon(""),
                    new ImageIcon("")
                });
            }
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                showError("Error closing resources: " + e.getMessage());
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
