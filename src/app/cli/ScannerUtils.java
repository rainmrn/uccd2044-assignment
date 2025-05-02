package app.cli;

import java.util.Scanner;

public class ScannerUtils {
    private static Scanner scanner = new Scanner(System.in);
    private ScannerUtils() {};

    public static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Not a number.");
            scanner.next();
            System.out.print(prompt);
        }

        int result = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        return result;
    }

    public static double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Not a valid decimal number.");
            scanner.next();
            System.out.print(prompt);
        }

        double result = scanner.nextDouble();
        scanner.nextLine(); // clear buffer
        return result;
    }

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static void close() {
        scanner.close();
    }
}
