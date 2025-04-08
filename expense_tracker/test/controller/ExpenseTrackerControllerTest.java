package controller;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import model.Transaction;
import view.ExpenseTrackerView;

import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ExpenseTrackerControllerTest {
    
    private ExpenseTrackerModel model;
    private ExpenseTrackerView mockView;
    private ExpenseTrackerController controller;
    
    @Before
    public void setUp() {
        // Setup - create model and mock view for testing
        model = new ExpenseTrackerModel();
        mockView = new MockExpenseTrackerView();
        controller = new ExpenseTrackerController(model, mockView);
    }
    
    @Test
    public void testAddValidTransaction() {
        // Setup - verify initially empty
        assertEquals(0, model.getTransactions().size());
        
        // Execution
        boolean result = controller.addTransaction(50.00, "food");
        
        // Validation
        assertTrue(result);
        assertEquals(1, model.getTransactions().size());
        Transaction addedTransaction = model.getTransactions().get(0);
        assertEquals(50.00, addedTransaction.getAmount(), 0.001);
        assertEquals("food", addedTransaction.getCategory());
    }
    
    @Test
    public void testAddInvalidTransactionAmount() {
        // Setup - verify initially empty
        assertEquals(0, model.getTransactions().size());
        
        // Execution - invalid amount (negative)
        boolean result = controller.addTransaction(-50.00, "food");
        
        // Validation
        assertFalse(result);
        assertEquals(0, model.getTransactions().size());
    }
    
    @Test
    public void testAddInvalidTransactionCategory() {
        // Setup - verify initially empty
        assertEquals(0, model.getTransactions().size());
        
        // Execution - invalid category
        boolean result = controller.addTransaction(50.00, "invalid");
        
        // Validation
        assertFalse(result);
        assertEquals(0, model.getTransactions().size());
    }
    
    @Test
    public void testFilterByAmount() {
        // Setup - add multiple transactions
        controller.addTransaction(25.00, "food");
        controller.addTransaction(75.00, "travel");
        controller.addTransaction(100.00, "bills");
        
        // Mock the view's filter settings
        ((MockExpenseTrackerView)mockView).setSelectedFilter("amount");
        ((MockExpenseTrackerView)mockView).setMinAmountFilter(50.00);
        ((MockExpenseTrackerView)mockView).setMaxAmountFilter(90.00);
        
        // Execution
        List<Transaction> filteredTransactions = controller.applyFilter(
            model.getTransactions(), 
            mockView.getSelectedFilter()
        );
        
        // Validation
        assertEquals(1, filteredTransactions.size());
        assertEquals(75.00, filteredTransactions.get(0).getAmount(), 0.001);
    }
    
    @Test
    public void testFilterByCategory() {
        // Setup - add multiple transactions
        controller.addTransaction(25.00, "food");
        controller.addTransaction(35.00, "food");
        controller.addTransaction(75.00, "travel");
        controller.addTransaction(100.00, "bills");
        
        // Mock the view's filter settings
        ((MockExpenseTrackerView)mockView).setSelectedFilter("category");
        ((MockExpenseTrackerView)mockView).setCategoryFilterValue("food");
        
        // Execution
        List<Transaction> filteredTransactions = controller.applyFilter(
            model.getTransactions(), 
            mockView.getSelectedFilter()
        );
        
        // Validation
        assertEquals(2, filteredTransactions.size());
        assertEquals("food", filteredTransactions.get(0).getCategory());
        assertEquals("food", filteredTransactions.get(1).getCategory());
    }
    
    @After
    public void tearDown() {
        model = null;
        mockView = null;
        controller = null;
    }
    
    // Mock view implementation for testing
    private class MockExpenseTrackerView extends ExpenseTrackerView {
        private String selectedFilter = "";
        private double minAmountFilter = 0.0;
        private double maxAmountFilter = 1000.0;
        private String categoryFilterValue = "";
        
        @Override
        public String getSelectedFilter() {
            return selectedFilter;
        }
        
        public void setSelectedFilter(String filter) {
            this.selectedFilter = filter;
        }
        
        @Override
        public double getMinAmountFilter() {
            return minAmountFilter;
        }
        
        public void setMinAmountFilter(double min) {
            this.minAmountFilter = min;
        }
        
        @Override
        public double getMaxAmountFilter() {
            return maxAmountFilter;
        }
        
        public void setMaxAmountFilter(double max) {
            this.maxAmountFilter = max;
        }
        
        @Override
        public String getCategoryFilterValue() {
            return categoryFilterValue;
        }
        
        public void setCategoryFilterValue(String category) {
            this.categoryFilterValue = category;
        }
        
        @Override
        public DefaultTableModel getTableModel() {
            return new DefaultTableModel();
        }
    }
}
