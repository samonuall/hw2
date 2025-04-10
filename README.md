# hw2

# New Functionality
1. Implemented transaction filtering using the Strategy Design Pattern:
    - Added `TransactionFilter` interface with two strategies: `AmountFilter` and `CategoryFilter`.
    - Users can filter transactions by amount (range) or category using radio buttons and input fields.
    - Filters are applied via the controller and results are dynamically displayed in the transaction table.
    - Input validation (reused from Homework 1) ensures only valid categories and amount ranges are accepted.
2. Unit tests were added to test basic functionality such as transactions, input handling, filters, etc.
3. Refactored Transaction class to be immutable and encapsulated the transaction list in ExpenseTrackerModel to improve modularity and support the Open-Closed Principle.