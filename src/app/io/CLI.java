package app.io;

import java.util.Scanner;

import app.model.entity.Product;
import app.model.entity.Refrigerator;
import app.model.entity.TV;
import app.model.repo.ProductRepo;

public class CLI {
    public static Scanner scanner = new Scanner(System.in);

    public static String getFullName() {
        System.out.print("Enter your full name (first + last): ");
        String name = scanner.nextLine();

        return name;
    }

    public static void promptMaxProduct() {
        int maxProduct;

        do {
            System.out.print("Enter the maximum number of products to store: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input! Please enter an positive integer: ");
                scanner.next();
            }

            maxProduct = scanner.nextInt();

        } while (maxProduct < 0);

        ProductRepo.setMaxProduct(maxProduct);
    }

    public static int getMenuChoice() {

        int choice;

        do {
            ConsoleUtils.clearConsole();
            System.out.println("\n1. View products");
            System.out.println("2. Add product");
            System.out.println("3. Add stock");
            System.out.println("4. Deduct stock");
            System.out.println("5. Discontinue product");
            System.out.println("0. Exit");

            choice = ConsoleInputHandler.readInt("Please enter a menu option: ");

        } while (choice < 0 || choice > 6);

        return choice;
    }

    public static void viewProducts() {
        if (!ProductRepo.hasProduct()) {
            System.out.println("No products available.");
            return;
        } else {
            System.out.println("\n---------- Product List ----------");

            int i = 1;
            for (Product product : ProductRepo.productArrayList) {
                System.out.println("\nProduct" + " [" + i + "]");
                System.out.println("-------------------");
                System.out.println(product);
                i++;
            }
        }
    }

    public static void showProductSelection() {
        System.out.println("\n--- Product List ---");

        for (int i = 0; i < ProductRepo.productArrayList.size(); i++) {
            System.out.println("[" + i + "]" + ": " + ProductRepo.productArrayList.get(i).getName());
        }
    }

    public static int getProductIndex() {
        int index;

        do {
            index = ConsoleInputHandler.readInt("Select product index: ");

        } while (index < 0 || index >= ProductRepo.productArrayList.size());

        return index;
    }

    public static int getQuantity() {
        int qty;

        do {
            qty = ConsoleInputHandler.readInt("Enter quantity: ");
        } while (qty < 0);

        return qty;
    }

    public static Product getNewProduct() {

        int type;

        do {
            type = ConsoleInputHandler.readInt("Enter product type (1 - Refrigerator, 2 - TV): ");

            if (type < 1 || type > 2) {
                System.out.println("Only number 1 or 2 allowed!");
            }

        } while (type != 1 && type != 2);

        if (type == 1) {
            return (Product) getNewRefrigerator();
        } else {
            return (Product) getNewTV();
        }
    }

    public static Refrigerator getNewRefrigerator() {

        scanner.nextLine(); // clear buffer

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter door design: ");
        String door = scanner.nextLine();

        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        int cap = ConsoleInputHandler.readInt("Enter capacity (L): ");
        int qty = ConsoleInputHandler.readInt("Enter quantity available: ");
        double price = ConsoleInputHandler.readDouble("Enter price: ");

        System.out.print("Enter item number: ");
        String id = scanner.nextLine();

        return new Refrigerator(name, price, qty, id, door, color, cap);
    }

    public static TV getNewTV() {

        scanner.nextLine(); // clear buffer

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter screen type: ");
        String screen = scanner.nextLine();

        System.out.print("Enter resolution: ");
        String res = scanner.nextLine();

        int size = ConsoleInputHandler.readInt("Enter display size: ");
        int qty = ConsoleInputHandler.readInt("Enter quantity available: ");
        double price = ConsoleInputHandler.readDouble("Enter price: ");

        System.out.print("Enter item number: ");
        String id = scanner.nextLine();

        return new TV(name, price, qty, id, screen, res, size);
    }
}
