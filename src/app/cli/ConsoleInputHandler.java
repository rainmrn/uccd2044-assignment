package app.cli;

import java.util.Scanner;

public class ConsoleInputHandler {

    public static Scanner scanner = new Scanner(System.in);

    private ConsoleInputHandler() {
    };

    public static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Not a number.");
            scanner.next();
            System.out.print(prompt);
        }

        return scanner.nextInt();
    }

    public static double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Not a valid decimal number.");
            scanner.next();
            System.out.print(prompt);
        }

        return scanner.nextDouble();
    }
}
