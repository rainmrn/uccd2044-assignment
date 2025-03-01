import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Main {
    private static UserInfo user;
    private static int maxNumOfProduct;
    private static Product[] productArray;

    public static void main(String[] args) {
        init();
        int selection = menu();

        Refrigerator r1 = new Refrigerator(1, "r1", 3, 45.0, "", "Black", 15);
        Refrigerator r2 = new Refrigerator(1, "r1", 3, 45.0, "", "Black", 15);
        Refrigerator r3 = new Refrigerator(1, "r1", 3, 45.0, "", "Black", 15);
        Refrigerator r4 = new Refrigerator(1, "r1", 3, 45.0, "", "Black", 15);
        Refrigerator r5 = new Refrigerator(1, "r1", 3, 45.0, "", "Black", 15);
        Refrigerator r6 = new Refrigerator(1, "r1", 3, 45.0, "", "Black", 15);

        productArray[0] = r1;
        productArray[1] = r2;
        productArray[2] = r3;
        productArray[3] = r4;
        productArray[4] = r5;
        productArray[5] = r6;
        displayProducts();
        // resetApp();
        ScannerUtil.scanner.close();
    }

    /**
     * Initializes the application configuration.
     * 
     * This method initializes the configuration file by calling the
     * ConfigUtil.initConfigFile() method. If the configuration is empty, it
     * prompts the user for their name, sets the maximum number of products,
     * and saves these details in the configuration file. If the configuration
     * is not empty, it reads the existing configuration.
     */
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
        } else {
            readConfig();
        }

        productArray = new Product[maxNumOfProduct];
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

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
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

    private static void displayProducts() {
        clearConsole();
        int i;
        // int pageSize = 3;
        for (i = 1; i <= productArray.length; i++) {
            System.out.println(i + ". " + productArray[i - 1].getName());

            // if (i % pageSize == 0) {
            // System.out.print("P/N: ");
            // String input = ScannerUtil.scanner.next();
            // if (input.equalsIgnoreCase("p")) {
            // i -= (pageSize * 2);
            // if (i < 0) i = 0;
            // }
            // clearConsole();
            // }

            if (productArray[i] == null) {
                break;
            }
        }
    }

    private static int menu() {
        while (true) {
            clearConsole();
            LocalDateTime currentDate = LocalDateTime.now();

            System.out.println("\nWelcome to the stock management system.");
            System.out.println("Date: " + currentDate
                    .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM)));
            System.out.println("Current user: " + ConfigUtil.config.getString("name") + "\n");
            System.out.print(
                    "1. View products\n" +
                    "2. Add stock\n" +
                    "3. Deduct stock\n" +
                    "4. Discontinue product\n" +
                    "0. Exit\n" +
                    "Please enter a menu option: ");
            int selection = ScannerUtil.scanner.nextInt();
            if (selection >= 0 && selection <= 4) {
                return selection;
            }
            clearConsole();
        }
    }
}