import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Admin
 */
public class frmProductAdd extends javax.swing.JFrame {
    private int productId;
    private String productName;
    private double productPrice;
    private int productStock;
    private String categoryId;
    private String imagePath;
    private frmProductView parentForm;
    public frmProductAdd(frmProductView parent, int id, String name, double price, int stock, String cId, String imagePath) {
        initComponents();
        this.parentForm = parent;
        this.productId = id;
        this.productName = name;
        this.productPrice = price;
        this.productStock = stock;
        this.categoryId = cId;
        this.imagePath = imagePath;

        txtName.setText(productName);
        txtPrice.setText(String.valueOf(productPrice));
        txtStock.setText(String.valueOf(productStock));

       
        populateCategoryComboBox();
        cbCatName.setSelectedItem(categoryId); 

        loadProductImage(); 
    }

    private void loadProductImage() {
        if (imagePath != null && !imagePath.isEmpty()) {
            File file = new File(imagePath);
            if (file.exists()) {
                try {
                    ImageIcon icon = new ImageIcon(imagePath);
                    Image img = icon.getImage();
                    Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    lblImage.setIcon(new ImageIcon(scaledImg));
                } catch (Exception e) {
                    System.out.println("Error loading image: " + e.getMessage());
                    lblImage.setIcon(null);
                }
            } else {
                System.out.println("Image file not found: " + imagePath);
                lblImage.setIcon(null);
            }
        } else {
            lblImage.setIcon(null);
        }
    }
 

    public frmProductAdd() {
        initComponents();
        populateCategoryComboBox();
        clickEnter();
        addHoverEffect(btnImage);
        addHoverEffect(btnClose);
        addHoverEffect(btnSave);
    }

    private void populateCategoryComboBox() {
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM Category"); 
            cbCatName.removeAllItems();
            while (rs.next()) {
                String CatName = rs.getString("name");
                cbCatName.addItem(CatName);
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading Category data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clickEnter(){
        txtName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnSaveActionPerformed(null);
                }
            }
        });
       
        cbCatName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnSaveActionPerformed(null);
                }
            }
        });
        btnImage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnSaveActionPerformed(null);
                }
            }
        });
        
    }
     
    private JButton focusedButton = null;

    private void addHoverEffect(JButton button) {
        Color originalColor = button.getBackground(); 

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button != focusedButton) {
                    button.setBackground(new Color(73, 71, 79)); 
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button != focusedButton) {
                    button.setBackground(originalColor); 
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Customer = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbCatName = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        btnImage = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(55, 58, 89));
        setFocusTraversalPolicyProvider(true);
        setFocusable(false);
        setForeground(new java.awt.Color(51, 102, 255));
        setLocation(new java.awt.Point(600, 200));
        setUndecorated(true);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Bundle"); // NOI18N
        jLabel2.setText(bundle.getString("frmProductAdd.jLabel2.text_1")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText(bundle.getString("frmProductAdd.jLabel3.text_1")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText(bundle.getString("frmProductAdd.jLabel4.text_1")); // NOI18N

        txtStock.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtStock.setText(bundle.getString("frmProductAdd.txtStock.text")); // NOI18N

        txtPrice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPrice.setText(bundle.getString("frmProductAdd.txtPrice.text")); // NOI18N

        btnSave.setBackground(new java.awt.Color(51, 51, 255));
        btnSave.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText(bundle.getString("frmProductAdd.btnSave.text_1")); // NOI18N
        btnSave.setBorder(null);
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClose.setBackground(new java.awt.Color(255, 51, 51));
        btnClose.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText(bundle.getString("frmProductAdd.btnClose.text_1")); // NOI18N
        btnClose.setBorder(null);
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(55, 58, 89));

        Customer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-product-65.png"))); // NOI18N
        Customer.setText(bundle.getString("frmProductAdd.Customer.text_1")); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(bundle.getString("frmProductAdd.jLabel1.text_1")); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(Customer)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Customer)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        cbCatName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbCatName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCatNameActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText(bundle.getString("frmProductAdd.jLabel6.text_1")); // NOI18N

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add-to-basket.png"))); // NOI18N
        lblImage.setLabelFor(btnImage);
        lblImage.setText(bundle.getString("frmProductAdd.lblImage.text_1")); // NOI18N
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageMouseClicked(evt);
            }
        });

        btnImage.setBackground(new java.awt.Color(51, 51, 255));
        btnImage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnImage.setForeground(new java.awt.Color(255, 255, 255));
        btnImage.setIcon(new javax.swing.ImageIcon("D:\\Y3S2\\javaII\\TestMainfrm_1\\src\\img\\icons8-upload-26 (1).png")); // NOI18N
        btnImage.setText(bundle.getString("frmProductAdd.btnImage.text_1")); // NOI18N
        btnImage.setActionCommand(bundle.getString("frmProductAdd.btnImage.actionCommand")); // NOI18N
        btnImage.setBorder(null);
        btnImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImage.setFocusCycleRoot(true);
        btnImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImageActionPerformed(evt);
            }
        });

        txtName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtName.setText(bundle.getString("frmProductAdd.txtName.text")); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText(bundle.getString("frmProductAdd.jLabel5.text")); // NOI18N

        btnAdd.setBackground(new java.awt.Color(51, 102, 255));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText(bundle.getString("frmProductAdd.btnAdd.text")); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtStock, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))))
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImage, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(147, 147, 147))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbCatName, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(103, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd)
                .addGap(101, 101, 101))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel2))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbCatName, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(btnAdd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnImage, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose(); 
    }//GEN-LAST:event_btnCloseActionPerformed
    private String selectedImagePath = "";
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
    String productName = txtName.getText().trim();  
    String priceText = txtPrice.getText().trim();       
    String stockText = txtStock.getText().trim();
    String categoryName = cbCatName.getSelectedItem() != null ? cbCatName.getSelectedItem().toString().trim() : "";

    if (productName.isEmpty() || priceText.isEmpty() || stockText.isEmpty() || categoryName.isEmpty()) {
        showMessage("Please fill all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    double price;
    int stockQty;

    try {
        price = Double.parseDouble(priceText);  // FIXED: Parse priceText
    } catch (NumberFormatException e) {
        showMessage("Invalid price format.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        stockQty = Integer.parseInt(stockText);
    } catch (NumberFormatException e) {
        showMessage("Invalid stock quantity format.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int categoryId = getCategoryIdByName(categoryName);
    if (categoryId == 0) {
        showMessage("Category not found.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (productId == 0) {
        saveProductToDatabase(productName, categoryName, price, stockQty, selectedImagePath);
        JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        resetForm();
    } else {
        updateProductInDatabase(productId, productName, categoryId, price, stockQty, selectedImagePath);
        JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    if (parentForm != null) {
        parentForm.fetchData();
    }

    dispose();
    resetForm();

} catch (SQLException ex) {
    Logger.getLogger(frmExpenseAdd.class.getName()).log(Level.SEVERE, "Database error", ex);
    showMessage("Error occurred while saving the product. Please try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
} catch (Exception ex) {
    Logger.getLogger(frmExpenseAdd.class.getName()).log(Level.SEVERE, "Unexpected error", ex);
    showMessage("An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}


    }//GEN-LAST:event_btnSaveActionPerformed
    private void showMessage(String message, String title, int messageType) {
        this.toFront();
        this.requestFocus();
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    private void cbCatNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCatNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCatNameActionPerformed

    private void btnImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImageActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            if (selectedFile != null) { 
                selectedImagePath = selectedFile.getAbsolutePath();

                ImageIcon imageIcon = new ImageIcon(selectedImagePath);
                Image image = imageIcon.getImage();
                Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                lblImage.setIcon(new ImageIcon(scaledImage));
            } else {
                System.out.println("No file was selected.");
            }
        } else {
            System.out.println("File selection canceled.");
        }

    }//GEN-LAST:event_btnImageActionPerformed

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblImageMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        frmCategoryAdd frm = new frmCategoryAdd();
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddActionPerformed
    private void saveProductToDatabase(String productName, String categoryName, double price, int stockQty, String selectedImagePath) 
        throws SQLException, IOException {
    
        int categoryId = getCategoryIdByName(categoryName); 

        if (categoryId == 0) {
            throw new SQLException("Invalid category name: " + categoryName);
        }

        String uploadDir = "D:/Y3S2/javaII/Testing_Java/src/Products/";
        String imageName = null;

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        if (selectedImagePath != null && !selectedImagePath.isEmpty()) {
            File sourceFile = new File(selectedImagePath);
            if (sourceFile.exists()) {
                String extension = selectedImagePath.substring(selectedImagePath.lastIndexOf("."));
                imageName = UUID.randomUUID().toString() + extension;
                File destinationFile = new File(uploadDir + imageName);
                Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } else {
                System.out.println("Error: Selected image file does not exist!");
            }
        }

        String query = "INSERT INTO product (name, price, stock_qty, image, CatID) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, productName);
            stmt.setDouble(2, price);
            stmt.setInt(3, stockQty);

            if (imageName != null) {
                stmt.setString(4, imageName);
            } else {
                stmt.setNull(4, java.sql.Types.VARCHAR);
            }

            stmt.setInt(5, categoryId);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Failed to insert the product.");
            }
        }
    }

    private void updateProductInDatabase(int productId, String productName, int catId, double price, int stockQty, String selectedImagePath) throws SQLException {
        String targetFolder = "D:/Y3S2/javaII/Testing_Java/src/Products/";
        String imageFileName = null;

        if (selectedImagePath != null && !selectedImagePath.isEmpty()) {
            File sourceFile = new File(selectedImagePath);
            imageFileName = sourceFile.getName();
            File targetFile = new File(targetFolder + imageFileName);

            try {
                Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new SQLException("Error saving image: " + e.getMessage());
            }
        } else {
            String query = "SELECT image FROM product WHERE id = ?";
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, productId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        imageFileName = rs.getString("image");
                    }
                }
            } catch (SQLException e) {
                throw new SQLException("Error retrieving current image: " + e.getMessage());
            }
        }

        String updateQuery = "UPDATE product SET name = ?, price = ?, stock_qty = ?, image = ?, CatID = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(updateQuery)) {
            stmt.setString(1, productName);
            stmt.setDouble(2, price);
            stmt.setInt(3, stockQty);

            if (imageFileName != null) {
                stmt.setString(4, imageFileName);
            } else {
                stmt.setNull(4, java.sql.Types.VARCHAR);
            }

            stmt.setInt(5, catId);
            stmt.setInt(6, productId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("No rows updated, please check the product ID.");
            }
        }
    }

    private int getCategoryIdByName(String categoryName) throws SQLException {
        int categoryId = 0;
        String query = "SELECT id FROM category WHERE name = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, categoryName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoryId = rs.getInt("id");
                }
            }
        }
        return categoryId;
    }

    private void resetForm() {
        txtStock.setText("");
        txtPrice.setText("");
        cbCatName.setSelectedIndex(0);
        lblImage.setIcon(new ImageIcon("add-to-basket.png"));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmExpenseAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmExpenseAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmExpenseAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmExpenseAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              //new frmProductAdd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Customer;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnImage;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbCatName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables

}
