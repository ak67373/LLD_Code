package strategy;

import enums.ExpenseType;
import model.Expense;
import model.Split;
import model.User;

import java.util.List;

public class PercentExpense extends Expense {
    public PercentExpense(double amount, User paidBy, List<Split> splits) {
        super(amount, paidBy, splits, ExpenseType.PERCENT);
    }

    @Override
    public boolean validate() {
        double total = 0.0;
        for (Split split : splits) {
            // Convert amount back to percent based on total amount
            total += (split.getAmount() * 100.0) / amount;
        }
        return Math.abs(total - 100.0) < 0.01;
    }
}
