package app;

import java.util.Scanner;

public class StockManagement {


	private static Product[] products;
	
	
	//Get the maximum number of products the user wishes to store in the system
	public static int getMaxProducts(Scanner scanner) {
		int maxProduct;
		
		do {			
			System.out.print("Enter the number of products to store: ");
		
			while(!scanner.hasNextInt()) {
				System.out.print("Invalid input! Please enter an positive integer: ");
				scanner.next();
			 }
			 
			 maxProduct = scanner.nextInt();
			 System.out.println("");
			 
		 }while(maxProduct < 0);
		return maxProduct;
	}	
	
	
	//Display products[] and let user to select product that they want to update
	public static int selectProduct(Product[] products, Scanner scanner) {
		int index;
		
		if(products.length == 0) {
			System.out.print("No any products added.");
		}
		
		System.out.println("\n--- Product List ---");
		
		for (int i = 0; i < products.length; i++) {
			System.out.println(i + ": " + products[i].getName());
		}
		
		do {
			System.out.print("Select product index: ");
			while (!scanner.hasNextInt()) {
				System.out.print("Invalid input. Enter a valid product index: ");
				scanner.next();
			}
			
		index = scanner.nextInt();
		System.out.println("");
		
		}while(index < 0 || index >= products.length);
		
		return index;
			
	}
	
	
	//Display menu and get choice
	public static int menuChoice(Scanner scanner) {
		int choice;
		
		System.out.println("\n1. View products");
		System.out.println("2. Add stock");
		System.out.println("3. Deduct stock");
		System.out.println("4. Discontinue product");
		System.out.println("0. Exit");
		System.out.print("Please enter a menu option: ");
		
		do {
			//Input is not an integer
			while (!scanner.hasNextInt()) {
				System.out.print("Invalid input! Enter a number (0-4): ");
				scanner.next();
			}
			System.out.print("Please enter a menu option. Enter a number (0-4): ");
			choice = scanner.nextInt();
			
		}while(choice < 0 || choice > 4);
		
		return choice;
	}
	
	
	//Add stock
	public static void addStock(Product[] products, Scanner scanner) {
		int index = selectProduct(products, scanner);
		int qty;
		
		System.out.print("Enter quantity to add: ");
		
		do {
			while (!scanner.hasNextInt()) {
				System.out.print("Invalid input! Enter a positive number: ");
				scanner.next();
			}
			qty = scanner.nextInt();
		}while(qty < 0);
		
		products[index].addQuantity(qty);
		System.out.println("Stock updated successfully.");
	}
	
	
	//Deduct stock
	public static void deductStock(Product[] products, Scanner scanner) {
		int index = selectProduct(products, scanner);
		int maxQty = products[index].getQuantity_available();
		int qty;
		
		System.out.print("Enter quantity to deduct: ");
		
		do {
			while(!scanner.hasNextInt()) {
				System.out.print("Invalid input! Enter a number (0 - " + maxQty + "): ");
				scanner.next();
			}
			qty = scanner.nextInt();
		}while(qty < 0 || qty > maxQty);
		
		products[index].deductQuantity(qty);
		System.out.println("Stock deducted successfully.");
	}
	
	
	//Discontinue product
	public static void discontinueProduct(Product[] products, Scanner scanner) {
		int index = selectProduct(products, scanner);
		products[index].setStatus(false);
		System.out.println("Product marked as discontinued.");
	}
	
	
	//Execute methods based on menu choice
	public static void executeMenuChoice(int choice, Product[] products, Scanner scanner) {
		switch(choice) {
		
		case 1:
			selectProduct(products, scanner);
			break;
		case 2:
			addStock(products, scanner);
			break;
		case 3:
			deductStock(products, scanner);
			break;
		case 4:
			discontinueProduct(products, scanner);
			break;
		case 0:
			System.out.println("Exiting system...");
			break;
		default:
			System.out.println("Invalid option!");
		}
	}
	



		
	   
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in); 
		/* int maxProducts = getMaxProducts(scanner);
		 * products = new Product[maxProducts];
		 */
		
		int choice;
		do {
			choice = menuChoice(scanner);
			
		}while(choice != 0);
		scanner.close();
	}

}
