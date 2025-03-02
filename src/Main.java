import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.json.JSONObject;

public class Main {
    private static UserInfo user;
    private static int maxNumOfProduct;
    private static int numOfProduct = 0;
    private static int numOfActiveProduct = 0;
    private static Product[] productArray;
    private static Product[] activeProductArray;

    public static void main(String[] args) {
        init();
        int selection;
        do {
            selection = menu();
            switch (selection) {
                case 1:
                    displayProduct(false);
                    ConsoleUtil.pauseConsole();
                    break;
                case 2:
                    displayProduct(false);
                    System.out.print("\nSelect a product to add stock: ");
                    addStock(ScannerUtil.scanner.nextInt() - 1);
                    break;
                case 3:
                    displayProduct(false);
                    System.out.print("\nSelect a product to deduct stock: ");
                    deductStock(ScannerUtil.scanner.nextInt() - 1);
                    break;
                case 4:
                    displayProduct(true);
                    System.out.print("\nSelect a product to discontinue: ");
                    discontinueProduct(ScannerUtil.scanner.nextInt() - 1);
                    break;
                case 5:
                    addProductPrompt();
                    break;
                case 6:
                    resetApp();
                    break;
                default:
                    break;
            }
        } while (selection != 0 && selection != 6);
        ProductUtil.writeProduct();
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
        ProductUtil.initProductFile();

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

        readProduct();
    }

    // Initialise user from config file
    private static void readConfig() {
        user = new UserInfo();
        user.setName(ConfigUtil.config.getString("name"));
        user.setUserId(ConfigUtil.config.getString("userId"));
        maxNumOfProduct = ConfigUtil.config.getInt("maxNumOfProduct");
    }

    private static void readProduct() {
        productArray = new Product[maxNumOfProduct];
        JSONObject products = ProductUtil.product;

        int index = 0;
        for (String key : products.keySet()) {
            if (index >= maxNumOfProduct) {
                break;
            }
            JSONObject product = products.getJSONObject(key);
            String type = product.getString("type");

            if (type.equals("refrigerator")) {
                productArray[index++] = new Refrigerator(
                        Integer.parseInt(key),
                        product.getString("name"),
                        product.getInt("stock"),
                        product.getDouble("price"),
                        product.getString("doorDesign"),
                        product.getString("color"),
                        product.getDouble("capacity"),
                        product.getBoolean("isActive"));
            } else if (type.equals("tv")) {
                productArray[index++] = new TV(
                        Integer.parseInt(key),
                        product.getString("name"),
                        product.getInt("stock"),
                        product.getDouble("price"),
                        product.getString("screenType"),
                        product.getString("resolution"),
                        product.getDouble("displaySize"),
                        product.getBoolean("isActive"));
            }
        }

        numOfProduct = index;
    }

    private static void resetApp() {
        System.out.println("\nAre you sure you want to reset the app? All changes made will be lost.");
        System.out.print("Y/N: ");
        String input = ScannerUtil.scanner.next();
        if (input.equalsIgnoreCase("y")) {
            ConfigUtil.config.clear();
            ConfigUtil.writeConfig();
            ProductUtil.resetProduct();
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

    private static void getActiveProduct() {
        activeProductArray = new Product[numOfProduct];
        numOfActiveProduct = 0;
        for (int i = 0; i < numOfProduct; i++) {
            if (productArray[i].getIsActive()) {
                activeProductArray[numOfActiveProduct++] = productArray[i];
            }
        }
    }

    private static int menu() {
        while (true) {
            ConsoleUtil.clearConsole();
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
                            "5. Add product\n" +
                            "6. Reset app\n" +
                            "0. Exit\n" +
                            "Please enter a menu option: ");
            int selection = ScannerUtil.scanner.nextInt();
            if (selection >= 0 && selection <= 6) {
                return selection;
            }
            ConsoleUtil.clearConsole();
        }
    }

    private static void displayProduct(boolean onlyActiveProduct) {
        ConsoleUtil.clearConsole();
        // int pageSize = 3;
        if (onlyActiveProduct) {
            getActiveProduct();
            for (int i = 1; i <= numOfActiveProduct; i++) {
                System.out.println(i + ". " + activeProductArray[i - 1].getName());
            }
        } else {
            for (int i = 1; i <= numOfProduct; i++) {
                System.out.println(i + ". " + productArray[i - 1].getName());
            }

            // if (i % pageSize == 0) {
            // System.out.print("P/N: ");
            // String input = ScannerUtil.scanner.next();
            // if (input.equalsIgnoreCase("p")) {
            // i -= (pageSize * 2);
            // if (i < 0) i = 0;
            // }
            // ConsoleUtil.clearConsole();
            // }
        }
    }

    // Add stock to a product at index in the productArray
    private static void addStock(int index) {
        if (index >= numOfProduct || index < 0) {
            System.out.println("Product not found.");
            return;
        }

        while (true) {
            System.out.print("Amount to add: ");
            int amountToAdd = ScannerUtil.scanner.nextInt();
            if (amountToAdd >= 0) {
                productArray[index].addStock(amountToAdd);
                JSONObject productInConfig = ProductUtil.product
                        .getJSONObject(Integer.toString(productArray[index].getProductId()));
                productInConfig.put("stock", productInConfig.getInt("stock") + amountToAdd);
                break;
            }
        }
    }

    private static void deductStock(int index) {
        if (index >= numOfProduct || index < 0) {
            System.out.println("Product not found.");
            return;
        }

        while (true) {
            System.out.print("Amount to deduct: ");
            int amountToDeduct = ScannerUtil.scanner.nextInt();
            if (amountToDeduct >= 0 && productArray[index].getStock() >= amountToDeduct) {
                productArray[index].deductStock(amountToDeduct);
                JSONObject productInConfig = ProductUtil.product
                        .getJSONObject(Integer.toString(productArray[index].getProductId()));
                productInConfig.put("stock", productInConfig.getInt("stock") - amountToDeduct);
                break;
            }
        }
    }

    // Discontinue product from a list of active products
    private static void discontinueProduct(int index) {
        Product selectedProduct = activeProductArray[index];
        selectedProduct.setIsActive(false);

        // Make changes to product file
        JSONObject productInConfig = ProductUtil.product
                .getJSONObject(Integer.toString(selectedProduct.getProductId()));
        productInConfig.put("isActive", false);
    }

    private static void addProductPrompt() {
        if (numOfProduct == maxNumOfProduct) {
            System.out.println("Reached maximum number of products.");
            return;
        }
        while (true) {
            ConsoleUtil.clearConsole();
            System.out.println("1. Refrigerator\n" +
                    "2. TV");
            System.out.print("\nSelect product type to add: ");
            int input = ScannerUtil.scanner.nextInt();
            ScannerUtil.scanner.nextLine();
            if (input >= 1 && input <= 2) {
                switch (input) {
                    case 1:
                        addProductRefrigerator();
                        break;
                    case 2:
                        addProductTV();
                        break;
                    default:
                        break;
                }
                break;
            }
        }
    }

    private static void addProductRefrigerator() {
        ConsoleUtil.clearConsole();
        String name, doorDesign, color;
        int stock, productId;
        double capacity, price;

        System.out.println("Adding product of type: Refrigerator\n");
        System.out.print("Product ID: ");
        productId = ScannerUtil.scanner.nextInt();
        if (productExists(productId)) {
            System.out.println("Product with the same ID already exists.");
            ConsoleUtil.pauseConsole();
            return;
        }

        System.out.print("Name: ");
        name = ScannerUtil.scanner.nextLine();

        System.out.print("Door design: ");
        doorDesign = ScannerUtil.scanner.nextLine();

        System.out.print("Color: ");
        color = ScannerUtil.scanner.nextLine();

        System.out.print("Capacity (litres): ");
        capacity = ScannerUtil.scanner.nextDouble();

        System.out.print("Stock quantity: ");
        stock = ScannerUtil.scanner.nextInt();

        System.out.print("Price (RM): ");
        price = ScannerUtil.scanner.nextDouble();

        Product product = new Refrigerator(productId, name, stock, price, doorDesign, color, capacity, true);
        productArray[++numOfProduct] = product;
        ProductUtil.addProduct(product);
    }

    private static void addProductTV() {
        ConsoleUtil.clearConsole();
        String name, screenType, resolution;
        int stock, productId;
        double displaySize, price;

        System.out.println("Adding product of type: TV\n");
        System.out.print("Product ID: ");
        productId = ScannerUtil.scanner.nextInt();
        if (productExists(productId)) {
            System.out.println("Product with the same ID already exists.");
            ConsoleUtil.pauseConsole();
            return;
        }

        System.out.print("Name: ");
        name = ScannerUtil.scanner.nextLine();

        System.out.print("Screen type: ");
        screenType = ScannerUtil.scanner.nextLine();

        System.out.print("Resolution: ");
        resolution = ScannerUtil.scanner.nextLine();

        System.out.print("Display size (inches): ");
        displaySize = ScannerUtil.scanner.nextDouble();

        System.out.print("Stock quantity: ");
        stock = ScannerUtil.scanner.nextInt();

        System.out.print("Price (RM): ");
        price = ScannerUtil.scanner.nextDouble();

        Product product = new TV(productId, name, stock, price, screenType, resolution, displaySize, true);
        productArray[++numOfProduct] = product;
        ProductUtil.addProduct(product);
    }

    private static boolean productExists(int productId) {
        for (int i = 0; i < numOfProduct; i++) {
            if (productId == productArray[i].getProductId()) {
                return true;
            }
        }

        return false;
    }
}