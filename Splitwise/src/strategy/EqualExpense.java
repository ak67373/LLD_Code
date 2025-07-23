package strategy;

import enums.ExpenseType;
import model.Expense;
import model.Split;
import model.User;

import java.util.List;

public class EqualExpense extends Expense {
    public EqualExpense(double amount, User paidBy, List<Split> splits) {
        super(amount, paidBy, splits, ExpenseType.EQUAL);
    }

    @Override
    public boolean validate() {
        return true; // No special validation needed
    }
}