import commands.CommandExecutor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandExecutor executor = new CommandExecutor();

        while (true) {
            String line = scanner.nextLine();
            if (line.trim().equalsIgnoreCase("exit")) break;
            try {
                executor.execute(line.trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}