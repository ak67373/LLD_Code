package strategy;

import enums.ExpenseType;
import model.Expense;
import model.Split;
import model.User;

import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(double amount, User paidBy, List<Split> splits) {
        super(amount, paidBy, splits, ExpenseType.EXACT);
    }

    @Override
    public boolean validate() {
        double total = 0;
        for (Split split : splits) {
            total += split.getAmount(); // Sum up all the parts
        }
        return Math.abs(total - amount) < 0.01; // Validate if sum matches original
    }
}