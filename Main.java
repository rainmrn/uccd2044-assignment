import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Main {
    private static UserInfo user;
    private static int maxNumOfProduct;

    public static void main(String[] args) {
        init();
        LocalDateTime currentDate = LocalDateTime.now();

        System.out.println("\nWelcome to the stock management system.");
        System.out.println("Date: " + currentDate.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.MEDIUM)));
        System.out.println("Current user: " + ConfigUtil.config.getString("name") + "\n");


        System.out.println("Max: " + maxNumOfProduct);
        resetApp();
        ScannerUtil.scanner.close();
    }

    private static void init() {
        ConfigUtil.initConfigFile();

        if (ConfigUtil.config.isEmpty()) {
            user = new UserInfo();
            user.promptName();
            maxNumOfProduct = getMaxNumOfProduct();

            ConfigUtil.config.put("name", user.getName());
            ConfigUtil.config.put("userId", user.getUserId());
            ConfigUtil.config.put("maxNumOfProduct", maxNumOfProduct);
            ConfigUtil.writeConfig();
        }
        else {
            readConfig();
        }
    }

    // Initialise user from config file
    private static void readConfig() {        
        user = new UserInfo();
        user.setName(ConfigUtil.config.getString("name"));
        user.setUserId(ConfigUtil.config.getString("userId"));
        maxNumOfProduct = ConfigUtil.config.getInt("maxNumOfProduct");
    }

    private static void resetApp() {
        System.out.println("Are you sure you want to reset the app? All changes made will be lost.");
        System.out.print("Y/N: ");
        String input = ScannerUtil.scanner.next();
        if (input.equalsIgnoreCase("y")) {
            ConfigUtil.config.clear();
            ConfigUtil.writeConfig();
            System.out.println("App reset. Restart to see changes.");
        } else {
            System.out.println("No changes were made.");
        }
    }

    private static int getMaxNumOfProduct() {
        while (true) {
            System.out.print("Enter the maximum number of products you wish to store in this application: ");
            int userInput = ScannerUtil.scanner.nextInt();
            if (userInput >= 0) {
                return userInput;
            }

            System.out.println("Only 0 or positive values!");
        }
    }
}