package repository;

import enums.ExpenseType;
import factory.ExpenseFactory;
import model.Expense;
import model.Group;
import model.Split;
import model.User;

import java.util.*;

public class ExpenseManager {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Map<String, Double>> balances = new HashMap<>();
    private Map<String, Group> groups = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getId(), user);
        balances.put(user.getId(), new HashMap<>());
    }

    public void addFriend(String userId1, String userId2) {
        User u1 = users.get(userId1);
        User u2 = users.get(userId2);
        if (u1 != null && u2 != null) {
            u1.addFriend(u2);
            u2.addFriend(u1);
        }
    }

    public void createGroup(String groupId, String groupName, List<String> memberIds) {
        Group group = new Group(groupId, groupName);
        for (String id : memberIds) {
            group.addMember(users.get(id));
        }
        groups.put(groupId, group);
    }

    public void addExpense(ExpenseType type, String paidById, double amount, List<String> userIds, List<Double> values) {
        addExpenseInternal(type, paidById, amount, userIds, values);
    }

    public void addGroupExpense(ExpenseType type, String groupId, String paidById, double amount, List<Double> values) {
        Group group = groups.get(groupId);
        if (group == null) {
            System.out.println("Group not found");
            return;
        }
        List<String> userIds = new ArrayList<>(group.getMembers());
        addExpenseInternal(type, paidById, amount, userIds, values);
    }

    private void addExpenseInternal(ExpenseType type, String paidById, double amount, List<String> userIds, List<Double> values) {
        User paidBy = users.get(paidById);
        List<Split> splits = new ArrayList<>();

        switch (type) {
            case EQUAL -> {
                int size = userIds.size();
                double share = Math.floor((amount / size) * 100) / 100.0;
                double remainder = amount - (share * size);
                for (int i = 0; i < size; i++) {
                    double amt = share + (i == 0 ? remainder : 0);
                    splits.add(new Split(users.get(userIds.get(i)), amt));
                }
            }
            case EXACT, PERCENT -> {
                for (int i = 0; i < userIds.size(); i++) {
                    double amt = type == ExpenseType.EXACT ? values.get(i) : (amount * values.get(i)) / 100.0;
                    splits.add(new Split(users.get(userIds.get(i)), amt));
                }
            }
        }

        Expense expense = ExpenseFactory.createExpense(type, amount, paidBy, splits);

        if (!expense.validate()) {
            System.out.println("Invalid expense");
            return;
        }

        for (Split split : splits) {
            if (split.getUser().getId().equals(paidById)) continue;
            String paidTo = paidBy.getId();
            String paidFrom = split.getUser().getId();
            double amt = split.getAmount();
            balances.get(paidFrom).put(paidTo, balances.get(paidFrom).getOrDefault(paidTo, 0.0) + amt);
        }
    }

    public void showBalance() {
        boolean hasBalance = false;
        for (String u1 : balances.keySet()) {
            for (String u2 : balances.get(u1).keySet()) {
                double amount = balances.get(u1).get(u2);
                if (amount > 0) {
                    System.out.printf("%s owes %s: %.2f\n", u1, u2, amount);
                    hasBalance = true;
                }
            }
        }
        if (!hasBalance) System.out.println("No balances");
    }

    public void showBalance(String userId) {
        boolean hasBalance = false;
        for (String other : balances.keySet()) {
            if (balances.get(userId).getOrDefault(other, 0.0) > 0) {
                System.out.printf("%s owes %s: %.2f\n", userId, other, balances.get(userId).get(other));
                hasBalance = true;
            } else if (balances.get(other).getOrDefault(userId, 0.0) > 0) {
                System.out.printf("%s owes %s: %.2f\n", other, userId, balances.get(other).get(userId));
                hasBalance = true;
            }
        }
        if (!hasBalance) System.out.println("No balances");
    }

    public void showGroupBalance(String groupId) {
        Group group = groups.get(groupId);
        if (group == null) {
            System.out.println("Group not found");
            return;
        }
        boolean hasBalance = false;
        for (String u1 : group.getMembers()) {
            for (String u2 : group.getMembers()) {
                if (u1.equals(u2)) continue;
                double amount = balances.get(u1).getOrDefault(u2, 0.0);
                if (amount > 0) {
                    System.out.printf("%s owes %s: %.2f\n", u1, u2, amount);
                    hasBalance = true;
                }
            }
        }
        if (!hasBalance) System.out.println("No balances in group");
    }
}
