import enums.ExpenseType;
import model.User;
import repository.ExpenseManager;

import java.util.*;

public class Main {
    // Question link -  https://workat.tech/machine-coding/practice/splitwise-problem-0kp2yneec2q2
    // Input
    //EXPENSE u1 1000 2 u2 u3 EQUAL
    //EXPENSE u1 1250 2 u2 u3 EXACT 370 880
    //EXPENSE u2 1200 3 u1 u3 u4 PERCENT 40 20 40
    //SHOW
    //SHOW u1

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
            if (parts[0].equals("SHOW")) {
                if (parts.length == 1)
                    manager.showBalance();
                else
                    manager.showBalance(parts[1]);
            } else if (parts[0].equals("EXPENSE")) {
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
        }

        sc.close();
    }
}