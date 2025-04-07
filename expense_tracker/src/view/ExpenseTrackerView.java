package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;

import controller.InputValidation;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  
  // New filter components
  private JRadioButton noFilterRadio;
  private JRadioButton amountFilterRadio;
  private JRadioButton categoryFilterRadio;
  private JTextField minAmountField;
  private JTextField maxAmountField;
  private JTextField categoryFilterField;
  private JButton applyFilterBtn;

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(800, 500); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
    
    // Create filter panel
    JPanel filterPanel = new JPanel();
    filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
    filterPanel.setBorder(BorderFactory.createTitledBorder("Filter Transactions"));
    
    ButtonGroup filterGroup = new ButtonGroup();
    noFilterRadio = new JRadioButton("No Filter", true);
    amountFilterRadio = new JRadioButton("Filter by Amount");
    categoryFilterRadio = new JRadioButton("Filter by Category");
    
    filterGroup.add(noFilterRadio);
    filterGroup.add(amountFilterRadio);
    filterGroup.add(categoryFilterRadio);
    
    JPanel radioPanel = new JPanel();
    radioPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    radioPanel.add(noFilterRadio);
    radioPanel.add(amountFilterRadio);
    radioPanel.add(categoryFilterRadio);
    
    JPanel amountFilterPanel = new JPanel();
    amountFilterPanel.add(new JLabel("Min:"));
    minAmountField = new JTextField(5);
    amountFilterPanel.add(minAmountField);
    amountFilterPanel.add(new JLabel("Max:"));
    maxAmountField = new JTextField(5);
    amountFilterPanel.add(maxAmountField);
    
    JPanel categoryFilterPanel = new JPanel();
    categoryFilterPanel.add(new JLabel("Category:"));
    categoryFilterField = new JTextField(10);
    categoryFilterPanel.add(categoryFilterField);
    
    applyFilterBtn = new JButton("Apply Filter");
    
    filterPanel.add(radioPanel);
    filterPanel.add(amountFilterPanel);
    filterPanel.add(categoryFilterPanel);
    filterPanel.add(applyFilterBtn);
  
    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER);
    add(filterPanel, BorderLayout.EAST);
  
    // Set frame properties
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }  
  

  
  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  // New filter getter methods
  public String getSelectedFilter() {
    if (amountFilterRadio.isSelected()) {
      return "amount";
    } else if (categoryFilterRadio.isSelected()) {
      return "category";
    }
    return ""; // No filter
  }
  
  public double getMinAmountFilter() {
    try {
      return Double.parseDouble(minAmountField.getText());
    } catch (NumberFormatException e) {
      return 1; // Default minimum
    }
  }
  
  public double getMaxAmountFilter() {
    try {
      return Double.parseDouble(maxAmountField.getText());
    } catch (NumberFormatException e) {
      return 999; // Default maximum
    }
  }
  
  public String getCategoryFilterValue() {
    return categoryFilterField.getText();
  }
  
  public JButton getApplyFilterBtn() {
    return applyFilterBtn;
  }
}
