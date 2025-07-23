package factory;

import enums.ExpenseType;
import model.Expense;
import model.Split;
import model.User;
import strategy.EqualExpense;
import strategy.ExactExpense;
import strategy.PercentExpense;

import java.util.List;

public class ExpenseFactory {
    public static Expense createExpense(ExpenseType type, double amount, User paidBy, List<Split> splits) {
        return switch (type) {
            case EQUAL -> new EqualExpense(amount, paidBy, splits);
            case EXACT -> new ExactExpense(amount, paidBy, splits);
            case PERCENT -> new PercentExpense(amount, paidBy, splits);
        };
    }
}
