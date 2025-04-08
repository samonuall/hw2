package controller;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import controller.AmountFilter;
import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AmountFilterTest {
    
    private AmountFilter filter;
    private List<Transaction> transactions;
    
    @Before
    public void setUp() {
        // Setup
        transactions = new ArrayList<>();
        transactions.add(new Transaction(25.00, "food"));
        transactions.add(new Transaction(50.00, "entertainment"));
        transactions.add(new Transaction(75.00, "travel"));
        transactions.add(new Transaction(100.00, "bills"));
    }
    
    @Test
    public void testAmountFilterMinMaxRange() {
        // Setup
        filter = new AmountFilter(40.00, 80.00);
        
        // Execution
        List<Transaction> filteredTransactions = filter.filter(transactions);
        
        // Validation
        assertEquals(2, filteredTransactions.size());
        assertEquals(50.00, filteredTransactions.get(0).getAmount(), 0.001);
        assertEquals(75.00, filteredTransactions.get(1).getAmount(), 0.001);
    }
    
    @Test
    public void testAmountFilterExactMatch() {
        // Setup
        filter = new AmountFilter(50.00, 50.00);
        
        // Execution
        List<Transaction> filteredTransactions = filter.filter(transactions);
        
        // Validation
        assertEquals(1, filteredTransactions.size());
        assertEquals(50.00, filteredTransactions.get(0).getAmount(), 0.001);
    }
    
    @Test
    public void testAmountFilterNoMatches() {
        // Setup
        filter = new AmountFilter(200.00, 300.00);
        
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
