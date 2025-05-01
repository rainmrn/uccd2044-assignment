package app.cli;

import java.io.IOException;

public class ConsoleUtils {

    private ConsoleUtils() {};


    // Code taken from: https://stackoverflow.com/questions/2979383/how-to-clear-the-console-using-java
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException err) {
            err.printStackTrace();
        }
    }

    public static void pauseConsole() {
        System.out.print("\nPress ENTER key to continue...");
        try {
            System.in.read();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
