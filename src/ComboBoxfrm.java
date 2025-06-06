
import javax.swing.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class ComboBoxfrm extends javax.swing.JPanel {

     DefaultComboBoxModel<String> comboBoxModel;
     DefaultListModel<String> list1Model;
     DefaultListModel<String> list2Model;
    public ComboBoxfrm() {
        initComponents();
        comboBoxModel = new DefaultComboBoxModel<>();
        list1Model = new DefaultListModel<>();
        list2Model = new DefaultListModel<>();

        cbAction.setModel(comboBoxModel);
        lsStore1.setModel(list1Model);
        lsStore2.setModel(list2Model);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnUpdate = new javax.swing.JButton();
        cbAction = new javax.swing.JComboBox<>();
        btnDelete = new javax.swing.JButton();
        txtInput = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lsStore1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        lsStore2 = new javax.swing.JList<>();
        btnSend = new javax.swing.JButton();
        btnSendAll = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnADD = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1280, 1920));

        btnUpdate.setBackground(new java.awt.Color(51, 51, 255));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Bundle"); // NOI18N
        btnUpdate.setText(bundle.getString("ComboBoxfrm.btnUpdate.text")); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        cbAction.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbAction.setForeground(new java.awt.Color(255, 255, 255));
        cbAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbActionActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 0, 51));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText(bundle.getString("ComboBoxfrm.btnDelete.text")); // NOI18N
        btnDelete.setActionCommand(bundle.getString("ComboBoxfrm.btnDelete.actionCommand")); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed1(evt);
            }
        });

        txtInput.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtInput.setText(bundle.getString("ComboBoxfrm.txtInput.text")); // NOI18N
        txtInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInputActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText(bundle.getString("ComboBoxfrm.jLabel1.text")); // NOI18N

        lsStore1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lsStore1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lsStore1);

        jButton1.setBackground(new java.awt.Color(255, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText(bundle.getString("ComboBoxfrm.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lsStore2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lsStore2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(lsStore2);

        btnSend.setBackground(new java.awt.Color(51, 51, 255));
        btnSend.setForeground(new java.awt.Color(255, 255, 255));
        btnSend.setText(bundle.getString("ComboBoxfrm.btnSend.text")); // NOI18N
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnSendAll.setBackground(new java.awt.Color(51, 51, 255));
        btnSendAll.setForeground(new java.awt.Color(255, 255, 255));
        btnSendAll.setText(bundle.getString("ComboBoxfrm.btnSendAll.text")); // NOI18N
        btnSendAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendAllActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(255, 0, 51));
        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText(bundle.getString("ComboBoxfrm.btnClear.text")); // NOI18N
        btnClear.setActionCommand(bundle.getString("ComboBoxfrm.btnClear.actionCommand")); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnADD.setBackground(new java.awt.Color(51, 51, 255));
        btnADD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnADD.setForeground(new java.awt.Color(255, 255, 255));
        btnADD.setText(bundle.getString("ComboBoxfrm.btnADD.text")); // NOI18N
        btnADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDbtnAddActionPerformed(evt);
            }
        });

        btnSubmit.setBackground(new java.awt.Color(51, 102, 255));
        btnSubmit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText(bundle.getString("ComboBoxfrm.btnSubmit.text")); // NOI18N
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(270, 270, 270)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnADD, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbAction, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnSendAll, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(jLabel1)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbAction, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnADD, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSendAll, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        String input = txtInput.getText().trim();
        int selectedIndex = cbAction.getSelectedIndex();
        if (!input.isEmpty() && selectedIndex != -1) {
            comboBoxModel.removeElementAt(selectedIndex);
            comboBoxModel.insertElementAt(input, selectedIndex);
            cbAction.setSelectedIndex(selectedIndex);
            txtInput.setText("");

        }else{
            JOptionPane.showMessageDialog(this, "No item to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void cbActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbActionActionPerformed
        // TODO add your handling code here:
        int selectedIndex = cbAction.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedItem = comboBoxModel.getElementAt(selectedIndex);
            txtInput.setText(selectedItem);
        } else {
            txtInput.setText("");
        }
    }//GEN-LAST:event_cbActionActionPerformed

    private void btnDeleteActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed1

        int selectedIndex = cbAction.getSelectedIndex();

        if (selectedIndex >= 0) {
            comboBoxModel.removeElementAt(selectedIndex);

        } else {

            JOptionPane.showMessageDialog(this, "No item to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnDeleteActionPerformed1

    private void txtInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInputActionPerformed
        // TODO add your handling code here:
        txtInput.setText("");

    }//GEN-LAST:event_txtInputActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int[] selectedIndices = lsStore2.getSelectedIndices();
        if (selectedIndices.length > 0) {

            for (int i = selectedIndices.length - 1; i >= 0; i--) {
                list2Model.removeElementAt(selectedIndices[i]);
            }

            //JOptionPane.showMessageDialog(this, successMessage.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No item sent to list2.", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        int[] selectedIndices = lsStore1.getSelectedIndices();
        if (selectedIndices.length > 0) {
            StringBuilder successMessage = new StringBuilder("Successfully sent to list2:\n");

            for (int i = selectedIndices.length - 1; i >= 0; i--) {
                String value = list1Model.getElementAt(selectedIndices[i]);
                list2Model.addElement(value);
                list1Model.removeElementAt(selectedIndices[i]);
                successMessage.append(value).append("\n");
            }

            JOptionPane.showMessageDialog(this, successMessage.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No item sent to list2.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnSendAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendAllActionPerformed

        if (!list1Model.isEmpty()) {
            // Transfer all items from list1 to list2
            StringBuilder successMessage = new StringBuilder("Successfully sent to list2:\n");
            while (!list1Model.isEmpty()) {
                String value = list1Model.getElementAt(0);
                list2Model.addElement(value);
                list1Model.removeElementAt(0);
                successMessage.append(value).append("\n");
            }
            JOptionPane.showMessageDialog(this, successMessage.toString(),"Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No item to send.", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnSendAllActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        list2Model.clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnADDbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDbtnAddActionPerformed
        // TODO add your handling code here:
        boolean itemAdded = false;

        for (int i = 0; i < comboBoxModel.getSize(); i++) {
            String value = comboBoxModel.getElementAt(i);
            list1Model.addElement(value);
            itemAdded = true;
        }

        if (itemAdded) {

        } else {
            JOptionPane.showMessageDialog(this, "No items to add to list1.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnADDbtnAddActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        String input = txtInput.getText().trim();
        if (!input.isEmpty()) {
            comboBoxModel.addElement(input);
            txtInput.setText("");
        }else{
            JOptionPane.showMessageDialog(this, "Please input item to add.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSubmitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnSendAll;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnUpdate;
    public javax.swing.JComboBox<String> cbAction;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> lsStore1;
    private javax.swing.JList<String> lsStore2;
    public javax.swing.JTextField txtInput;
    // End of variables declaration//GEN-END:variables
}
