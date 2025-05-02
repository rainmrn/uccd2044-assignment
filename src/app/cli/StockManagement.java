package app.cli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import app.gui.StockManagementGUI;
import app.model.entity.*;
import app.model.repo.ProductRepo;
import app.model.repo.UserInfoRepo;

public class StockManagement {

	public static void main(String[] args) {
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentDateString = currentDate.format(format);

		ConsoleUtils.clearConsole();
		System.out.println("\n1 - Command Line Interface\n2 - Graphics User Interface\n");
		int selection;
		do {
			selection = ScannerUtils.readInt("Choose 1 or 2 (0 - Exit): ");
		} while (selection < 0 || selection > 2);

		if (selection == 2) {
			// Switch to GUI...
			StockManagementGUI.main(args);
			System.exit(0);
		} else if (selection == 0) {
			System.exit(0);
		}

		// Continue on CLI...

		ConsoleUtils.clearConsole();
		System.out.println("Welcome to Stockwise!\nDate and Time: " + currentDateString);
		System.out.println("\nGroup Member: \n - Chan Sin Ying\n - Chen Lee Peng\n - Lim Qi Xun\n - Oh Yi Heng\n");

		UserInfoRepo.user.setName(ScannerUtils.readLine("Enter your full name (first + last): "));
		UserInfoRepo.generateUserId();

		ConsoleUtils.clearConsole();
		System.out.println("Welcome " + UserInfoRepo.user.getName() + "!");

		int addProductOrNot;
		do {
		addProductOrNot = ScannerUtils.readInt("Do you want to add any products? (0 - No, exit / 1 - Yes!): ");
		} while (addProductOrNot < 0 || addProductOrNot > 1);

		if (addProductOrNot == 0) {
			System.out.println("We are deeply saddened to see you go :(");
			System.exit(0);
		}

		int maxProducts = getMaxProducts();

		for (int i = 0; i < maxProducts; i++) {
			addProduct();
			ConsoleUtils.pauseConsole();
		}

		int choice;
		do {
			choice = menuChoice();
			executeMenuChoice(choice);
		} while (choice != 0);

		ScannerUtils.close();
	}
	
	
	// Get the maximum number of products the user wishes to store in the system
	public static int getMaxProducts() {
		int maxProduct;

		do {
			maxProduct = ScannerUtils.readInt("Enter the number of products to store: ");

		} while (maxProduct < 0);
		return maxProduct;
	}	

	
	// Display menu and get choice
	public static int menuChoice() {
		ConsoleUtils.clearConsole();
		int choice;

		do {
			System.out.println("\n1. View products");
			System.out.println("2. Add stock");
			System.out.println("3. Deduct stock");
			System.out.println("4. Discontinue product");
			System.out.println("0. Exit");
			choice = ScannerUtils.readInt("Please enter a menu option: ");

			if (choice < 0 || choice > 4) {
				System.out.println("Please choose from 0 to 4.");
			}
		} while (choice < 0 || choice > 4);

		return choice;
	}

	// Execute methods based on menu choice
	public static void executeMenuChoice(int choice) {
		switch (choice) {
			
			case 1:
			viewProducts();
			ConsoleUtils.pauseConsole();
			break;
			case 2:
				ProductRepo.updateActiveProductList();
				if (!ProductRepo.hasActiveProduct()) {
					System.out.println("No active products available.");
				} else {
					addStock();
				}
				ConsoleUtils.pauseConsole();
				break;
			case 3:
				ProductRepo.updateActiveProductList();
				if (!ProductRepo.hasActiveProduct()) {
					System.out.println("No active products available.");
				} else {
					deductStock();
				}
				ConsoleUtils.pauseConsole();
				break;
			case 4:
				ProductRepo.updateActiveProductList();
				if (!ProductRepo.hasActiveProduct()) {
					System.out.println("No active products available.");
				} else {
					discontinueProduct();
				}
				ConsoleUtils.pauseConsole();
				break;
			case 0:
				System.out.println("Exiting system...");
				break;
			default:
				System.out.println("Invalid option!");
				ConsoleUtils.pauseConsole();
		}
	}

	// View products
	public static void viewProducts() {
		if (!ProductRepo.hasProduct()) {
			System.out.println("No products available.");
			return;
		} else {
			System.out.println("\n---------- Product List ----------");
			for (int i = 0; i < ProductRepo.productList.size(); i++) {
				Product product = ProductRepo.productList.get(i);
				System.out.println("\nProduct" + " [" + i + "]");
				System.out.println("-------------------");
				System.out.println(product);
			}
		}
	}


	// Display productList and let user to select product that they want to update
	public static int displayAndSelectProduct() {
		if (!ProductRepo.hasProduct()) {
			System.out.println("No products available.");
		}

		System.out.println("\n--- Product List ---");
		for (int i = 0; i < ProductRepo.productList.size(); i++) {
			System.out.println("[" + i + "]" + ": " + ProductRepo.productList.get(i).getName());
		}

		int index;
		do {
			index = ScannerUtils.readInt("Select product index: ");
			if (index < 0 || index >= ProductRepo.productList.size()) {
				System.out.println("Invalid product index. ");
			}
		} while (index < 0 || index >= ProductRepo.productList.size());

		return index;
	}

	// Display activeProductList and let user to select product that they want to
	// update
	public static int displayAndSelectActiveProduct() {
		System.out.println("\n--- Active Product List ---");
		for (int i = 0; i < ProductRepo.activeProductList.size(); i++) {
			System.out.println("[" + i + "]" + ": " + ProductRepo.activeProductList.get(i).getName());
		}

		int index;
		do {
			index = ScannerUtils.readInt("Select product index: ");
			if (index < 0 || index >= ProductRepo.activeProductList.size()) {
				System.out.println("Invalid product index. ");
			}
		} while (index < 0 || index >= ProductRepo.activeProductList.size());

		return index;
	}

	// Add stock
	public static void addStock() {
		int index = displayAndSelectActiveProduct();

		int qty;
		do {
			qty = ScannerUtils.readInt("Enter quantity to add: ");
			if (qty < 0) {
				System.out.println("Positive numbers only.");
			}
		} while (qty < 0);

		ProductRepo.activeProductList.get(index).addQuantity(qty);
		System.out.println("Stock added successfully.");
	}

	// Deduct stock
	public static void deductStock() {
		int index = displayAndSelectActiveProduct();
		int maxQty = ProductRepo.productList.get(index).getQuantity();

		int qty;
		do {
			String prompt = "Enter quantity to deduct (0 - " + maxQty + "): ";
			qty = ScannerUtils.readInt(prompt);

			if (qty < 0 || qty > maxQty) {
				System.out.println("Invalid quantity. Only " + maxQty + " available.");
			}
		} while (qty < 0 || qty > maxQty);

		ProductRepo.activeProductList.get(index).deductQuantity(qty);
		System.out.println("Stock deducted successfully.");
	}

	// Discontinue product
	public static void discontinueProduct() {
		int index = displayAndSelectActiveProduct();
		ProductRepo.activeProductList.get(index).setStatus(false);
		System.out.println("Product marked as discontinued.");
	}

	// Select to add a Refrigerator or TV product
	public static void addProduct() {
		int type;

		System.out.println("\n1 - Refrigerator\n2 - TV\n3 - Hair Dryer");
		do {
			type = ScannerUtils.readInt("Enter product type (0 - cancel): ");
			if (type < 0 || type > 3) {
				System.out.println("Only number 0 - 3 allowed!");
			}
		} while (type < 0 || type > 3);

		if (type == 0) {
			return;
		}
		if (type == 1) {
			addRefrigerator();
		}
		if (type == 2) {
			addTV();
		}
		if (type == 3) {
			addHairDryer();
		}
	}

	public static void addRefrigerator() {
		
		String name = ScannerUtils.readLine("Enter product name: ");
		String door = ScannerUtils.readLine("Enter door design: ");
		String color = ScannerUtils.readLine("Enter color: ");
		int cap = ScannerUtils.readInt("Enter capacity (L): ");
		int qty = ScannerUtils.readInt("Enter quantity available: ");
		double price = ScannerUtils.readDouble("Enter price: ");
		String itemNumber = ScannerUtils.readLine("Enter item number: ");

		ProductRepo.productList.add(new Refrigerator(name, price, qty, itemNumber, door, color, cap));
		System.out.print("New Refrigerator added.\n");
	}

	public static void addTV() {
		
		String name = ScannerUtils.readLine("Enter product name: ");
		String screen = ScannerUtils.readLine("Enter screen type: ");
		String res = ScannerUtils.readLine("Enter resolution: ");
		int size = ScannerUtils.readInt("Enter display size (inches): ");
		int qty = ScannerUtils.readInt("Enter quantity available: ");
		double price = ScannerUtils.readDouble("Enter price: ");
		String itemNumber = ScannerUtils.readLine("Enter item number: ");

		ProductRepo.productList.add(new TV(name, price, qty, itemNumber, screen, res, size));
		System.out.print("New TV added.\n");
	}

	public static void addHairDryer() {
		
		String name = ScannerUtils.readLine("Enter product name: ");
		String nozzleType = ScannerUtils.readLine("Enter nozzle type: ");
		String color = ScannerUtils.readLine("Enter color: ");
		int power = ScannerUtils.readInt("Enter power (watts): ");
		int qty = ScannerUtils.readInt("Enter quantity available: ");
		double price = ScannerUtils.readDouble("Enter price: ");
		String itemNumber = ScannerUtils.readLine("Enter item number: ");

		ProductRepo.productList.add(new HairDryer(name, price, qty, itemNumber, nozzleType, color, power));
		System.out.print("New Hair Dryer added.\n");
	}
}