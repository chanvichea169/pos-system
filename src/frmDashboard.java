import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class frmDashboard extends javax.swing.JPanel {

    public frmDashboard() {
        initComponents();
        
        // Initialize components
        lblTodaySales = new JLabel();
        lblWeeklySales = new JLabel();
        lblMonthlySales = new JLabel();
        lblSalesCount = new JLabel();
        lblTotalProducts = new JLabel();
        lblLowStock = new JLabel();
        lblTotalCategories = new JLabel();
        lblMonthlyExpenses = new JLabel();
        lblTodayExpenses = new JLabel();

        pnlRecentSales = new JPanel();
        pnlPaymentChart = new JPanel();
        pnlTopProducts = new JPanel();
        
        // Add components to layout
        add(lblTodaySales, BorderLayout.NORTH);
        add(lblWeeklySales, BorderLayout.NORTH);
        add(lblMonthlySales, BorderLayout.NORTH);
        add(lblSalesCount, BorderLayout.NORTH);
        add(lblTotalProducts, BorderLayout.NORTH);
        add(lblLowStock, BorderLayout.NORTH);
        add(lblTotalCategories, BorderLayout.NORTH);
        add(lblMonthlyExpenses, BorderLayout.NORTH);
        add(lblTodayExpenses, BorderLayout.NORTH);

        add(pnlRecentSales, BorderLayout.CENTER);
        add(pnlPaymentChart, BorderLayout.SOUTH);
        add(pnlTopProducts, BorderLayout.EAST);

        loadDashboardData();
    }

    private void loadDashboardData() {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                loadSalesSummary(conn);
                loadInventorySummary(conn);
                loadExpenseSummary(conn);
                loadRecentSales(conn);
                loadPaymentMethodChart(conn);
                loadTopProducts(conn);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to connect to database", 
                    "Connection Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void loadSalesSummary(Connection conn) throws SQLException {
        // Today's sales
        String todayQuery = "SELECT COALESCE(SUM(total_amount), 0) FROM sale WHERE DATE(sale_date) = CURDATE()";
        try (PreparedStatement stmt = conn.prepareStatement(todayQuery);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                lblTodaySales.setText(formatCurrency(rs.getDouble(1)));
            }
        }

        // Weekly sales
        String weeklyQuery = "SELECT COALESCE(SUM(total_amount), 0) FROM sale " +
                            "WHERE YEARWEEK(sale_date, 1) = YEARWEEK(CURDATE(), 1)";
        try (PreparedStatement stmt = conn.prepareStatement(weeklyQuery);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                lblWeeklySales.setText(formatCurrency(rs.getDouble(1)));
            }
        }

        // Monthly sales
        String monthlyQuery = "SELECT COALESCE(SUM(total_amount), 0) FROM sale " +
                             "WHERE MONTH(sale_date) = MONTH(CURDATE()) AND YEAR(sale_date) = YEAR(CURDATE())";
        try (PreparedStatement stmt = conn.prepareStatement(monthlyQuery);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                lblMonthlySales.setText(formatCurrency(rs.getDouble(1)));
            }
        }

        // Total sales count
        String countQuery = "SELECT COUNT(*) FROM sale WHERE DATE(sale_date) = CURDATE()";
        try (PreparedStatement stmt = conn.prepareStatement(countQuery);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                lblSalesCount.setText(rs.getInt(1) + " Sales Today");
            }
        }
    }
    
    private void loadInventorySummary(Connection conn) throws SQLException {
        // Total products
        String productQuery = "SELECT COUNT(*) FROM product";
        try (PreparedStatement stmt = conn.prepareStatement(productQuery);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                lblTotalProducts.setText(String.valueOf(rs.getInt(1)));
            }
        }

        // Low stock items (less than 5)
        String lowStockQuery = "SELECT COUNT(*) FROM product WHERE stock_qty < 5";
        try (PreparedStatement stmt = conn.prepareStatement(lowStockQuery);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                lblLowStock.setText(String.valueOf(rs.getInt(1)));
            }
        }

        // Total categories
        String categoryQuery = "SELECT COUNT(*) FROM category";
        try (PreparedStatement stmt = conn.prepareStatement(categoryQuery);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                lblTotalCategories.setText(String.valueOf(rs.getInt(1)));
            }
        }
    }
    
    private void loadExpenseSummary(Connection conn) throws SQLException {
        // Today's expenses
        String todayQuery = "SELECT COALESCE(SUM(amount), 0) FROM expense WHERE date = CURDATE()";
        try (PreparedStatement stmt = conn.prepareStatement(todayQuery);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                lblTodayExpenses.setText(formatCurrency(rs.getDouble(1)));
            }
        }

        // Monthly expenses
        String monthlyQuery = "SELECT COALESCE(SUM(amount), 0) FROM expense " +
                             "WHERE MONTH(date) = MONTH(CURDATE()) AND YEAR(date) = YEAR(CURDATE())";
        try (PreparedStatement stmt = conn.prepareStatement(monthlyQuery);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                lblMonthlyExpenses.setText(formatCurrency(rs.getDouble(1)));
            }
        }
    }
    
    private void loadRecentSales(Connection conn) throws SQLException {
        pnlRecentSales.removeAll();
        
        String query = "SELECT s.id, s.sale_date, s.total_amount, st.name as staff_name " +
                      "FROM sale s JOIN staff st ON s.staff_id = st.id " +
                      "ORDER BY s.sale_date DESC LIMIT 5";
        
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                JPanel salePanel = new JPanel();
                salePanel.setBackground(Color.WHITE);
                salePanel.setLayout(new BorderLayout());
                
                JLabel saleLabel = new JLabel();
                saleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                saleLabel.setText("<html><b>Sale #" + rs.getInt("id") + "</b> - " + 
                                 formatCurrency(rs.getDouble("total_amount")) + 
                                 "<br>Staff: " + rs.getString("staff_name") + 
                                 " - " + rs.getTimestamp("sale_date").toLocalDateTime().format(
                                     DateTimeFormatter.ofPattern("MMM dd, hh:mm a")) + "</html>");
                
                salePanel.add(saleLabel, BorderLayout.CENTER);
                salePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
                
                pnlRecentSales.add(salePanel);
            }
            
            pnlRecentSales.revalidate();
            pnlRecentSales.repaint();
        }
    }
    
    private void loadPaymentMethodChart(Connection conn) throws SQLException {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        String query = "SELECT payment_method, COUNT(*) as count FROM sale GROUP BY payment_method";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                dataset.setValue(rs.getString("payment_method"), rs.getInt("count"));
            }
            
            JFreeChart chart = ChartFactory.createPieChart(
                "Payment Methods", 
                dataset, 
                true, 
                true, 
                false
            );
            
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));
            plot.setSimpleLabels(true);
            
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(300, 250));
            pnlPaymentChart.removeAll();
            pnlPaymentChart.add(chartPanel);
            pnlPaymentChart.revalidate();
            pnlPaymentChart.repaint();
        }
    }
    
    private void loadTopProducts(Connection conn) throws SQLException {
        pnlTopProducts.removeAll();
        
        String query = "SELECT p.name, SUM(sd.qty) as total_qty " +
                      "FROM sale_details sd JOIN product p ON sd.pid = p.id " +
                      "GROUP BY p.name ORDER BY total_qty DESC LIMIT 5";
        
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            int rank = 1;
            while (rs.next()) {
                JPanel productPanel = new JPanel();
                productPanel.setBackground(Color.WHITE);
                productPanel.setLayout(new BorderLayout());
                
                JLabel productLabel = new JLabel();
                productLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                productLabel.setText("<html><b>" + rank + ". " + rs.getString("name") + "</b> - " + 
                                   rs.getInt("total_qty") + " units sold</html>");
                
                productPanel.add(productLabel, BorderLayout.CENTER);
                productPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
                
                pnlTopProducts.add(productPanel);
                rank++;
            }
            
            pnlTopProducts.revalidate();
            pnlTopProducts.repaint();
        }
    }
    
    private String formatCurrency(double amount) {
        DecimalFormat formatter = new DecimalFormat("$#,##0.00");
        return formatter.format(amount);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // </editor-fold>

    // End of variables declaration                   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblLowStock;
    private javax.swing.JLabel lblMonthlyExpenses;
    private javax.swing.JLabel lblMonthlySales;
    private javax.swing.JLabel lblSalesCount;
    private javax.swing.JLabel lblTodayExpenses;
    private javax.swing.JLabel lblTodaySales;
    private javax.swing.JLabel lblTotalCategories;
    private javax.swing.JLabel lblTotalProducts;
    private javax.swing.JLabel lblWeeklySales;
    private javax.swing.JPanel pnlPaymentChart;
    private javax.swing.JPanel pnlRecentSales;
    private javax.swing.JPanel pnlTopProducts;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
