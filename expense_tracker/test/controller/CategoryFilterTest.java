package controller;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import controller.CategoryFilter;
import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CategoryFilterTest {
    
    private CategoryFilter filter;
    private List<Transaction> transactions;
    
    @Before
    public void setUp() {
        // Setup
        transactions = new ArrayList<>();
        transactions.add(new Transaction(25.00, "food"));
        transactions.add(new Transaction(35.00, "food"));
        transactions.add(new Transaction(50.00, "entertainment"));
        transactions.add(new Transaction(75.00, "travel"));
        transactions.add(new Transaction(100.00, "bills"));
    }
    
    @Test
    public void testCategoryFilterSingleMatch() {
        // Setup
        filter = new CategoryFilter("bills");
        
        // Execution
        List<Transaction> filteredTransactions = filter.filter(transactions);
        
        // Validation
        assertEquals(1, filteredTransactions.size());
        assertEquals("bills", filteredTransactions.get(0).getCategory());
    }
    
    @Test
    public void testCategoryFilterMultipleMatches() {
        // Setup
        filter = new CategoryFilter("food");
        
        // Execution
        List<Transaction> filteredTransactions = filter.filter(transactions);
        
        // Validation
        assertEquals(2, filteredTransactions.size());
        assertEquals("food", filteredTransactions.get(0).getCategory());
        assertEquals("food", filteredTransactions.get(1).getCategory());
    }
    
    @Test
    public void testCategoryFilterNoMatches() {
        // Setup
        filter = new CategoryFilter("other");
        
        // Execution
        List<Transaction> filteredTransactions = filter.filter(transactions);
        
        // Validation
        assertEquals(0, filteredTransactions.size());
    }
    
    @After
    public void tearDown() {
        filter = null;
        transactions = null;
    }
}
