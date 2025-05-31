import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class frmUExpenseView extends javax.swing.JPanel {

    private frmExpenseAdd expenseForm = null;
    private static final String IMAGE_FOLDER = "D:/Y3S2/javaII/Testing_Java/src/Expenses/";
    public frmUExpenseView() {
        initComponents();
        
        tblExpense.getTableHeader().setPreferredSize(new java.awt.Dimension(tblExpense.getTableHeader().getPreferredSize().width, 40)); 

        tblExpense.getTableHeader().setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
                c.setBackground(new java.awt.Color(57, 117, 247));
                c.setForeground(new java.awt.Color(247, 249, 252));
                return c;
            }
        });
        tblExpense.getColumnModel().getColumn(5).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
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
        tblExpense.getColumnModel().getColumn(6).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
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
        tblExpense.getColumnModel().getColumn(7).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
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

        tblExpense.getColumnModel().getColumn(3).setPreferredWidth(100);

        tblExpense.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tblExpense.getColumnModel().getColumn(0).setPreferredWidth(90);  
        tblExpense.getColumnModel().getColumn(1).setPreferredWidth(478);
        tblExpense.getColumnModel().getColumn(2).setPreferredWidth(350);  
        tblExpense.getColumnModel().getColumn(3).setPreferredWidth(250);
        tblExpense.getColumnModel().getColumn(4).setPreferredWidth(170);  
        tblExpense.getColumnModel().getColumn(5).setPreferredWidth(100); 
        tblExpense.getColumnModel().getColumn(6).setPreferredWidth(80);  
        tblExpense.getColumnModel().getColumn(7).setPreferredWidth(80);
        tblExpense.getColumnModel().getColumn(8).setPreferredWidth(1);
        tblExpense.revalidate();
        tblExpense.repaint();

        populateStaffComboBox();
        
        fetchData();
    }
    private void populateStaffComboBox() {
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM staff"); 
            cbStaff.removeAllItems();
            cbStaff.addItem("All Staffs");
            while (rs.next()) {
                String staffName = rs.getString("name");
                cbStaff.addItem(staffName);
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading staff data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblExpense = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        txtStartDate = new com.toedter.calendar.JDateChooser();
        btnSearch = new javax.swing.JButton();
        txtEndDate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        cbStaff = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(253, 253, 253));
        setMinimumSize(new java.awt.Dimension(1280, 1920));

        tblExpense.setAutoCreateRowSorter(true);
        tblExpense.setFont(new java.awt.Font("Kh Dangrek", 0, 14));
        tblExpense.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "No", "Date", "Description", "Amount","SName","Picture","","",""
            }
        ));
        tblExpense.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblExpense.setDoubleBuffered(true);
        tblExpense.setDragEnabled(true);
        tblExpense.setFillsViewportHeight(true);
        tblExpense.setFocusCycleRoot(true);
        tblExpense.setFocusTraversalPolicyProvider(true);
        tblExpense.setInheritsPopupMenu(true);
        tblExpense.setRowHeight(50);
        tblExpense.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblExpense.setShowGrid(false);
        tblExpense.setSurrendersFocusOnKeystroke(true);
        jScrollPane2.setViewportView(tblExpense);
        tblExpense.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Bundle"); // NOI18N
        jLabel1.setText(bundle.getString("frmUExpenseView.jLabel1.text_1")); // NOI18N

        txtStartDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnSearch.setBackground(new java.awt.Color(51, 51, 255));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText(bundle.getString("frmUExpenseView.btnSearch.text")); // NOI18N
        btnSearch.setBorder(null);
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtEndDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText(bundle.getString("frmUExpenseView.jLabel2.text_1")); // NOI18N

        cbStaff.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStaffActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText(bundle.getString("frmUExpenseView.jLabel3.text")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText(bundle.getString("frmUExpenseView.jLabel4.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(832, 832, 832)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbStaff, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1646, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtEndDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(766, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchExpense();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cbStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStaffActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_cbStaffActionPerformed

    public void fetchData() {
        DefaultTableModel model = (DefaultTableModel) tblExpense.getModel();
        model.setRowCount(0);
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT e.ID, e.date, e.desc, e.amount, e.picture, e.SId, s.name AS staff_name FROM expense e JOIN staff s ON e.SId = s.ID ORDER BY e.ID ASC")) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Date format

            while (rs.next()) {
                Date expenseDate = rs.getDate("date"); // Fetch date from DB
                String formattedDate = (expenseDate != null) ? dateFormat.format(expenseDate) : "";

                String imageName = rs.getString("picture");
                ImageIcon imageIcon = getImageIcon(imageName);

                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    formattedDate, 
                    rs.getString("desc"),
                    rs.getDouble("amount"),
                    rs.getString("staff_name"),
                    imageIcon,
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
 
    public void searchExpense() {
        DefaultTableModel model = (DefaultTableModel) tblExpense.getModel();
        model.setRowCount(0);
        String imageFolder = "D:/Y3S2/javaII/Testing_Java/src/Expenses/";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            if (con == null) {
                throw new SQLException("Database connection failed.");
            }

            Date startDate = txtStartDate.getDate();
            Date endDate = txtEndDate.getDate();

            if (startDate != null && endDate != null && startDate.after(endDate)) {
                JOptionPane.showMessageDialog(null, "Start date cannot be later than end date.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String startKeyword = "";
            String endKeyword = "";
            if (startDate != null && endDate != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                startKeyword = sdf.format(startDate);
                endKeyword = sdf.format(endDate);
            }

            String selectedStaff = cbStaff.getSelectedItem().toString();
            String staffCondition = "";

            if (!selectedStaff.equals("All Staffs")) {
                staffCondition = " AND s.name = ?";
            }

            String sql = "SELECT e.ID, e.date, e.desc, e.amount, e.picture, s.name AS staff_name " +
                         "FROM expense e " +
                         "JOIN staff s ON e.SId = s.ID " +
                         "WHERE e.date BETWEEN ? AND ? " +
                         (staffCondition.isEmpty() ? "" : " " + staffCondition) + 
                         " ORDER BY e.ID ASC LIMIT 50";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, startKeyword);
            pstmt.setString(2, endKeyword);

            if (!selectedStaff.equals("All Staffs")) {
                pstmt.setString(3, selectedStaff);
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                String imageName = rs.getString("picture");
                ImageIcon imageIcon = null;
                if (imageName != null && !imageName.isEmpty()) {
                    String fullImagePath = imageFolder + imageName;
                    File imageFile = new File(fullImagePath);
                    if (imageFile.exists()) {
                        imageIcon = new ImageIcon(fullImagePath);
                        Image img = imageIcon.getImage();
                        Image scaledImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                        imageIcon = new ImageIcon(scaledImg);
                    }
                }

                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("date"),
                    rs.getString("desc"),
                    rs.getDouble("amount"),
                    rs.getString("staff_name"),
                    imageIcon,
                    new ImageIcon(""),
                    new ImageIcon(""),
                    imageName
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbStaff;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tblExpense;
    private com.toedter.calendar.JDateChooser txtEndDate;
    private com.toedter.calendar.JDateChooser txtStartDate;
    // End of variables declaration//GEN-END:variables
}
