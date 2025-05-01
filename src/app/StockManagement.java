package app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import app.cli.CLI;
import app.cli.ConsoleInputHandler;
import app.cli.ConsoleUtils;
import app.gui.StockManagementGUI;
import app.model.entity.Product;
import app.model.repo.ProductRepo;
import app.model.repo.UserInfoRepo;

public class StockManagement {

	public static void main(String[] args) {
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentDateString = currentDate.format(format);

		ConsoleUtils.clearConsole();
		System.out.println("1 - Command Line Interface\n2 - Graphics User Interface\n");
		int selection;
		do {
			selection = ConsoleInputHandler.readInt("Choose 1 or 2 (0 - Exit): ");
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

		UserInfoRepo.user.setName(CLI.getFullName());
		UserInfoRepo.user.setUserId(UserInfoRepo.generateUserId());

		ConsoleUtils.clearConsole();
		System.out.println("Welcome " + UserInfoRepo.user.getName() + "!");

		CLI.promptMaxProduct();

		int choice;
		do {
			choice = CLI.getMenuChoice();
			executeMenuChoice(choice);
		} while (choice != 0);

		CLI.scanner.close();
	}

	public static void executeMenuChoice(int choice) {
		switch (choice) {
			case 1:
				CLI.viewProducts();
				ConsoleUtils.pauseConsole();
				break;
			case 2:
				if (ProductRepo.getNumOfProduct() < ProductRepo.getMaxProduct()) {
					Product newProduct = CLI.getNewProduct();
					if (newProduct != null) {
						ProductRepo.addProduct(newProduct);
						System.out.println("Product added.");
					}
				} else {
					System.out.println("Max num of products reached.");
				}
				ConsoleUtils.pauseConsole();
				break;
			case 3:
				if (!ProductRepo.hasActiveProduct()) {
					System.out.println("No active products available.");
					ConsoleUtils.pauseConsole();
					return;
				}
				CLI.showProductSelection(true);
				ProductRepo.addStock(CLI.getProductIndex(), CLI.getQuantity());
				System.out.println("Stock added successfully.");
				ConsoleUtils.pauseConsole();
				break;
			case 4:
				if (!ProductRepo.hasActiveProduct()) {
					System.out.println("No active products available.");
					ConsoleUtils.pauseConsole();
					return;
				}
				CLI.showProductSelection(true);
				ProductRepo.deductStock(CLI.getProductIndex(), CLI.getQuantity());
				System.out.println("Stock deducted successfully.");
				ConsoleUtils.pauseConsole();
				break;
			case 5:
				if (!ProductRepo.hasActiveProduct()) {
					System.out.println("No active products available.");
					ConsoleUtils.pauseConsole();
					return;
				}
				CLI.showProductSelection(true);
				ProductRepo.discontinueProduct(CLI.getProductIndex());
				System.out.println("Product marked as discontinued.");
				ConsoleUtils.pauseConsole();
				break;
			case 0:
				System.out.println("Exiting system...");
				break;
			default:
				System.out.println("Invalid option!");
		}
	}

}
