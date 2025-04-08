package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import model.ExpenseTrackerModel;
import model.Transaction;

import java.util.List;

public class ExpenseTrackerModelTest {
    
    private ExpenseTrackerModel model;
    
    @Before
    public void setUp() {
        // Setup
        model = new ExpenseTrackerModel();
    }
    
    @Test
    public void testAddTransaction() {
        // Setup
        double amount = 50.00;
        String category = "food";
        Transaction transaction = new Transaction(amount, category);
        
        // Execution
        model.addTransaction(transaction);
        
        // Validation
        List<Transaction> transactions = model.getTransactions();
        assertEquals(1, transactions.size());
        assertEquals(amount, transactions.get(0).getAmount(), 0.001);
        assertEquals(category, transactions.get(0).getCategory());
        
        // No explicit cleanup needed
    }
    
    @Test
    public void testRemoveTransaction() {
        // Setup
        Transaction transaction = new Transaction(25.00, "food");
        model.addTransaction(transaction);
        
        // Execution
        model.removeTransaction(transaction);
        
        // Validation
        List<Transaction> transactions = model.getTransactions();
        assertEquals(0, transactions.size());
    }
    
    @Test
    public void testGetTransactions() {
        // Setup
        model.addTransaction(new Transaction(10.00, "food"));
        model.addTransaction(new Transaction(20.00, "travel"));
        
        // Execution
        List<Transaction> transactions = model.getTransactions();
        
        // Validation
        assertEquals(2, transactions.size());
        
        // Verify the list is unmodifiable (should throw exception)
        try {
            transactions.add(new Transaction(30.00, "bills"));
            fail("Should have thrown UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // Expected behavior
        }
    }
    
    @After
    public void tearDown() {
        model = null;
    }
}
