package model;

public class Split {
    private User user;             // The user who owes money
    private double amount;         // Amount owed

    public Split(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }
}
