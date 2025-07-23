import enums.ExpenseType;
import model.User;
import repository.ExpenseManager;

import java.util.*;

public class Main {
    // Question link -  https://workat.tech/machine-coding/practice/splitwise-problem-0kp2yneec2q2

    // Input
//    # Add expenses among users
//    EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
//    EXPENSE u1 1250 2 u2 u3 EXACT 370 880
//    EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
//
//# Show balances for specific users
//    SHOW u1
//    SHOW u4
//    SHOW
//
//# Add friends
//    ADD_FRIEND u1 u2
//    ADD_FRIEND u1 u3
//    ADD_FRIEND u2 u3
//
//# Create group with 3 members
//    CREATE_GROUP g1 GoaTrip u1 u2 u3
//
//# Group-based expense split
//    EXPENSE_GROUP g1 u1 1500 EQUAL
//    EXPENSE_GROUP g1 u2 900 EXACT 300 300 300
//    EXPENSE_GROUP g1 u3 1200 PERCENT 40 30 30
//
//# Show group balances
//    SHOW_GROUP g1
//
//# Global check
//    SHOW
//
//# Exit after tests
//            EXIT

    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();

        manager.addUser(new User("u1", "User1", "u1@mail.com", "111"));
        manager.addUser(new User("u2", "User2", "u2@mail.com", "222"));
        manager.addUser(new User("u3", "User3", "u3@mail.com", "333"));
        manager.addUser(new User("u4", "User4", "u4@mail.com", "444"));

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("EXIT")) break;

            String[] parts = line.split(" ");
            switch (parts[0]) {
                case "SHOW" -> {
                    if (parts.length == 1)
                        manager.showBalance();
                    else
                        manager.showBalance(parts[1]);
                }
                case "SHOW_GROUP" -> manager.showGroupBalance(parts[1]);
                case "EXPENSE" -> {
                    String paidBy = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    int n = Integer.parseInt(parts[3]);
                    List<String> userIds = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        userIds.add(parts[4 + i]);
                    }

                    ExpenseType type = ExpenseType.valueOf(parts[4 + n]);
                    List<Double> values = new ArrayList<>();

                    for (int i = 5 + n; i < parts.length; i++) {
                        values.add(Double.parseDouble(parts[i]));
                    }

                    manager.addExpense(type, paidBy, amount, userIds, values);
                }
                case "EXPENSE_GROUP" -> {
                    String groupId = parts[1];
                    String paidBy = parts[2];
                    double amount = Double.parseDouble(parts[3]);
                    ExpenseType type = ExpenseType.valueOf(parts[4]);
                    List<Double> values = new ArrayList<>();
                    for (int i = 5; i < parts.length; i++) {
                        values.add(Double.parseDouble(parts[i]));
                    }
                    manager.addGroupExpense(type, groupId, paidBy, amount, values);
                }
                case "ADD_FRIEND" -> manager.addFriend(parts[1], parts[2]);
                case "CREATE_GROUP" -> {
                    String groupId = parts[1];
                    String name = parts[2];
                    List<String> members = Arrays.asList(parts).subList(3, parts.length);
                    manager.createGroup(groupId, name, members);
                }
            }
        }

        sc.close();
    }
}