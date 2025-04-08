package controller;

import java.util.ArrayList;
import model.Transaction;
import java.util.List;

public class AmountFilter implements TransactionFilter {
    private double minAmount;
    private double maxAmount;

    public AmountFilter(double minAmount, double maxAmount) {
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() >= minAmount && transaction.getAmount() <= maxAmount) {
                result.add(transaction);
            }
        }
        return result;
    }
}
