package controller;

import java.util.ArrayList;
import java.util.List;
import model.Transaction;

public class CategoryFilter implements TransactionFilter {
    private String categoryToMatch;
    
    public CategoryFilter(String categoryToMatch) {
        this.categoryToMatch = categoryToMatch;
    }
    
    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getCategory().equalsIgnoreCase(categoryToMatch)) {
                result.add(transaction);
            }
        }
        return result;
    }
}
