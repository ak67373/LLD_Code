import commands.CommandExecutor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CommandExecutor exec = new CommandExecutor();
        while (true) {
            String line = sc.nextLine().trim();
            if (line.equals("exit")) break;
            if (line.isEmpty()) continue;
            // Split into MAX 6 fields to keep copyIds intact
            exec.execute(line.split(" ", 6));
        }
        sc.close();
    }
}