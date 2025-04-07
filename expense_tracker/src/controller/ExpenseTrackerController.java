package controller;

import view.ExpenseTrackerView;

import java.util.List;

import javax.swing.JOptionPane;

import model.ExpenseTrackerModel;
import model.Transaction;
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  private AmountFilter amountFilter;
  private CategoryFilter categoryFilter;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;
    
    // Add filter listener
    view.getApplyFilterBtn().addActionListener(e -> {
      // check for invalid filters
      String categoryFilter = view.getCategoryFilterValue();
      double minAmount = view.getMinAmountFilter();
      double maxAmount = view.getMaxAmountFilter();
      if (categoryFilter.length() > 0 && !InputValidation.isValidCategory(view.getCategoryFilterValue())) {
        JOptionPane.showMessageDialog(view, "Invalid category filter entered.");
        view.toFront();
      } else if (minAmount > maxAmount) {
        JOptionPane.showMessageDialog(view, "Invalid amount filter entered.");
        view.toFront();
      } else if (!InputValidation.isValidAmount(minAmount) || !InputValidation.isValidAmount(maxAmount)) {
        JOptionPane.showMessageDialog(view, "Invalid amount filter entered.");
        view.toFront();
      }
      refresh();
    });
  }

  public void refresh() {
    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // get filter from view
    String filter = view.getSelectedFilter();
    
    // Apply filter to transactions
    transactions = applyFilter(transactions, filter);

    // Pass to view
    view.refreshTable(transactions);
  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }

  public List<Transaction> applyFilter(List<Transaction> transactions, String filter) {
    
    if (filter.equals("amount")) {
      double minAmount = view.getMinAmountFilter();
      double maxAmount = view.getMaxAmountFilter();
      amountFilter = new AmountFilter(minAmount, maxAmount);
      return amountFilter.filter(transactions);
    } else if (filter.equals("category")) {
      String categoryValue = view.getCategoryFilterValue();
      categoryFilter = new CategoryFilter(categoryValue);
      return categoryFilter.filter(transactions);
    }

    return transactions; // Default return all transactions
  }
  
  // Other controller methods
}