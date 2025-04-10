package controller;

import java.util.ArrayList;
import model.Transaction;
import java.util.List;

public class AmountFilter implements TransactionFilter {
    private final double minAmount;
    private final double maxAmount;

    public AmountFilter(double minAmount, double maxAmount) {
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> result = new ArrayList<>();
            for (Transaction transaction : transactions) {
                double amount = transaction.getAmount();
                if (amount >= minAmount && amount <= maxAmount) {
                    result.add(transaction);
                }
            }
        return result;
    }
}
