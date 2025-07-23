package model;

import enums.ExpenseType;

import java.util.List;

public abstract class Expense {
    protected double amount;       // Total expense amount
    protected User paidBy;         // User who paid
    protected List<Split> splits;  // How it is divided
    protected ExpenseType type;    // Split type

    public Expense(double amount, User paidBy, List<Split> splits, ExpenseType type) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.type = type;
    }

    public abstract boolean validate(); // Ensure split values are valid
}
