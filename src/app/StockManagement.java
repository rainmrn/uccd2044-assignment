package app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import app.io.CLI;
import app.model.repo.ProductRepo;
import app.model.repo.UserInfoRepo;

public class StockManagement {

	public static void main(String[] args) {
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentDateString = currentDate.format(format);

		System.out.println("Welcome to SMS!\nDate and Time: " + currentDateString);
		System.out.println("Group Member: \nChan Sin Ying\nChen Lee Peng\nLim Qi Xun\nOh Yi Heng\n");

		UserInfoRepo.user.setName(CLI.getFullName());
		UserInfoRepo.user.setUserId(UserInfoRepo.generateUserId());
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
				break;
			case 2:
				if (!ProductRepo.hasProduct()) {
					System.out.println("No products available.");
					return;
				}
				CLI.showProductSelection();
				ProductRepo.addStock(CLI.getProductIndex(), CLI.getQuantity());
				System.out.println("Stock added successfully.");
				break;
			case 3:
				if (!ProductRepo.hasProduct()) {
					System.out.println("No products available.");
					return;
				}
				CLI.showProductSelection();
				ProductRepo.deductStock(CLI.getProductIndex(), CLI.getQuantity());
				System.out.println("Stock deducted successfully.");
				break;
			case 4:
				if (!ProductRepo.hasProduct()) {
					System.out.println("No products available.");
					return;
				}
				CLI.showProductSelection();
				ProductRepo.discontinueProduct(CLI.getProductIndex());
				System.out.println("Product marked as discontinued.");
				break;
			case 5:
				ProductRepo.addProduct(CLI.getNewProduct());
				System.out.println("Product added.");
				break;
			case 0:
				System.out.println("Exiting system...");
				break;
			default:
				System.out.println("Invalid option!");
		}
	}

}
