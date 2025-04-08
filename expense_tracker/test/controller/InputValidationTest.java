package controller;

import static org.junit.Assert.*;
import org.junit.Test;

import controller.InputValidation;

public class InputValidationTest {
    
    @Test
    public void testValidAmount() {
        // Setup
        double validAmount = 50.00;
        
        // Execution
        boolean result = InputValidation.isValidAmount(validAmount);
        
        // Validation
        assertTrue(result);
    }
    
    @Test
    public void testInvalidAmountNegative() {
        // Setup
        double negativeAmount = -10.00;
        
        // Execution
        boolean result = InputValidation.isValidAmount(negativeAmount);
        
        // Validation
        assertFalse(result);
    }
    
    @Test
    public void testInvalidAmountZero() {
        // Setup
        double zeroAmount = 0.00;
        
        // Execution
        boolean result = InputValidation.isValidAmount(zeroAmount);
        
        // Validation
        assertFalse(result);
    }
    
    @Test
    public void testInvalidAmountTooLarge() {
        // Setup
        double largeAmount = 1500.00;
        
        // Execution
        boolean result = InputValidation.isValidAmount(largeAmount);
        
        // Validation
        assertFalse(result);
    }
    
    @Test
    public void testValidCategory() {
        // Setup
        String[] validCategories = {"food", "travel", "bills", "entertainment", "other"};
        
        for (String category : validCategories) {
            // Execution
            boolean result = InputValidation.isValidCategory(category);
            
            // Validation
            assertTrue("Category '" + category + "' should be valid", result);
        }
    }
    
    @Test
    public void testInvalidCategoryNull() {
        // Setup - null category
        
        // Execution
        boolean result = InputValidation.isValidCategory(null);
        
        // Validation
        assertFalse(result);
    }
    
    @Test
    public void testInvalidCategoryEmpty() {
        // Setup
        String emptyCategory = "";
        
        // Execution
        boolean result = InputValidation.isValidCategory(emptyCategory);
        
        // Validation
        assertFalse(result);
    }
    
    @Test
    public void testInvalidCategoryNonAlphabetic() {
        // Setup
        String nonAlphaCategory = "food123";
        
        // Execution
        boolean result = InputValidation.isValidCategory(nonAlphaCategory);
        
        // Validation
        assertFalse(result);
    }
    
    @Test
    public void testInvalidCategoryNotInList() {
        // Setup
        String invalidCategory = "shopping";
        
        // Execution
        boolean result = InputValidation.isValidCategory(invalidCategory);
        
        // Validation
        assertFalse(result);
    }
}
