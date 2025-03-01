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
    final private static String CONFIG_PATH = "config.json";
    private static JSONObject config;
    private static File configFile = new File(CONFIG_PATH);
    private static UserInfo user;
    private static int maxNumOfProduct;

    public static void main(String[] args) {
        init();
        LocalDateTime currentDate = LocalDateTime.now();

        System.out.println("\nWelcome to the stock management system.");
        System.out.println("Date: " + currentDate.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.MEDIUM)));
        System.out.println("Current user: " + config.getString("name") + "\n");


        System.out.println("Max: " + maxNumOfProduct);
    }

    private static void init() {
        if (configFile.exists()) {
            readConfig();
        } else {
            user = new UserInfo();
            user.promptName();
            
            config = new JSONObject();
            config.put("name", user.getName());
            config.put("userId", user.getUserId());

            maxNumOfProduct = getMaxNumOfProduct();
            config.put("maxNumOfProduct", maxNumOfProduct);

            writeConfig();
        }
    }

    private static void writeConfig() {
        try (FileWriter configWriter = new FileWriter(configFile)) {
            configWriter.write(config.toString(4));
        } catch (IOException error) {
            System.err.println(error);
        }
    }

    // Initialise user from config file
    private static void readConfig() {
        try (FileReader configReader = new FileReader(configFile)) {
            config = new JSONObject(new JSONTokener(configReader));
        } catch (IOException error) {
            System.err.println(error);
        }
        
        user = new UserInfo();
        user.setName(config.getString("name"));
        user.setUserId(config.getString("userId"));
        maxNumOfProduct = config.getInt("maxNumOfProduct");
    }

    private static void resetApp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure you want to reset the app? All changes made will be lost.");
        System.out.print("Y/N: ");
        String input = scanner.next();
        if (input == "Y" || input == "y") {
            config.clear();
            writeConfig();
            System.out.println("App reset. Restart to see changes.");
        } else {
            System.out.println("No changes were made.");
        }
        scanner.close();
    }

    private static int getMaxNumOfProduct() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the maximum number of products you wish to store in this application: ");
            int userInput = scanner.nextInt();
            scanner.close();
            if (userInput >= 0) {
                return userInput;
            }

            System.out.println("Only 0 or positive values!");
        }
    }
}