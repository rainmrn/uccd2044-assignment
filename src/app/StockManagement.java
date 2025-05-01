package app;

import java.util.ArrayList;
import java.util.Scanner;

public class StockManagement {
	
	private static ArrayList<Product> products = new ArrayList<>();
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in); 
		int maxProducts = getMaxProducts(scanner);
		products = new ArrayList<>();
		
		for(int i = 0; i < maxProducts; i++) { 
			addProduct(products, scanner); 
		}
		
		int choice;
		do {
			choice = menuChoice(scanner);
			executeMenuChoice(choice, products, scanner);
		}while(choice != 0);
		scanner.close();
	}
	
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
			 
		 }while(maxProduct < 0);
		return maxProduct;
	}	
	
	
	//Display products[] and let user to select product that they want to update
	public static int selectProduct(ArrayList<Product> products, Scanner scanner) {
		int index;

		if(products == null || products.isEmpty()) {
			System.out.println("No products available.");
		}
		
		System.out.println("\n--- Product List ---");
		
		for (int i = 0; i < products.size(); i++) {
			System.out.println("[" + i + "]" + ": " + products.get(i).getName());
		}
		
		do {
			System.out.print("Select product index: ");
			while (!scanner.hasNextInt()) {
				System.out.print("Invalid input. Enter a valid product index: ");
				scanner.next();
			}
			
		index = scanner.nextInt();
		System.out.println("");
		
		}while(index < 0 || index >= products.size());
		
		return index;
			
	}
	
	
	//Display menu and get choice
	public static int menuChoice(Scanner scanner) {
		int choice;
		
		do {
			System.out.println("\n1. View products");
			System.out.println("2. Add stock");
			System.out.println("3. Deduct stock");
			System.out.println("4. Discontinue product");
			System.out.println("0. Exit");
			System.out.print("Please enter a menu option: ");
			
			while(!scanner.hasNextInt()) {
				System.out.print("Please enter a valid menu option (0-4): ");
				scanner.next();
			}
			
			choice = scanner.nextInt();
	
		}while(choice < 0 || choice > 4);
		
		return choice;
	}
	
	
	//Add stock
	public static void addStock(ArrayList<Product> products, Scanner scanner) {
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
		
		products.get(index).addQuantity(qty);
		System.out.println("Stock updated successfully.");
	}
	
	
	//Deduct stock
	public static void deductStock(ArrayList<Product> products, Scanner scanner) {
		int index = selectProduct(products, scanner);
		int maxQty = products.get(index).getQuantity_available();
		int qty;
		
		do {
			System.out.print("Enter quantity to deduct (0 - " + maxQty + "): ");
			
			while(!scanner.hasNextInt()) {
				System.out.print("Invalid input! Enter a number (0 - " + maxQty + "): ");
				scanner.next();
			}
			
			qty = scanner.nextInt();
			
			if (qty < 0 || qty > maxQty) {
	            System.out.println("Invalid quantity. Only " + maxQty + " available.");
	        }
			
		}while(qty < 0 || qty > maxQty);
		
		products.get(index).deductQuantity(qty);
		System.out.println("Stock deducted successfully.");
	}
	
	
	//Discontinue product
	public static void discontinueProduct(ArrayList<Product> products, Scanner scanner) {
		int index = selectProduct(products, scanner);
		products.get(index).setStatus(false);
		System.out.println("Product marked as discontinued.");
	}
	
	
	//Execute methods based on menu choice
	public static void executeMenuChoice(int choice, ArrayList<Product> products, Scanner scanner) {
		switch(choice) {
		
		case 1:
			viewProducts(products);
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
	
	
	//Select to add a Refrigerator or TV product
	public static void addProduct(ArrayList<Product> products, Scanner scanner) {
		int type;
		
		do {
			System.out.print("\nEnter product type you want to store (1 - Refrigerator, 2 - TV): ");
			
			while (!scanner.hasNextInt()) {
				scanner.next();
				System.out.print("Only number 1 or 2 allowed! Try again: ");
			}
			type = scanner.nextInt();
			if(type < 1 || type > 2) {
				System.out.println("Only number 1 or 2 allowed!");
			}
			
		}while(type != 1 && type != 2);
		
		//Add Refrigerator
		if(type == 1) {
			addRefrigerator(scanner);
		}
		
		//Add TV
		if(type == 2) {
			addTV(scanner);
		}
	}
	
	
	//Add a Refrigerator
	public static void addRefrigerator(Scanner scanner) {
		scanner.nextLine(); //clear buffer
		
		System.out.print("Enter product name: ");
		String name = scanner.nextLine(); 
			
		System.out.print("Enter door design: ");
		String door = scanner.nextLine();
			
		System.out.print("Enter color: ");
		String color = scanner.nextLine();
			
		System.out.print("Enter capacity (L): ");
		int cap = scanner.nextInt(); 
			
		System.out.print("Enter quantity available: ");
		int qty = scanner.nextInt();
			
		System.out.print("Enter price: ");
		double price = scanner.nextDouble();
		scanner.nextLine(); //clear buffer
			
		System.out.print("Enter item number: ");
		String id = scanner.nextLine();

		products.add(new Refrigerator(name, price, qty, id, door, color, cap));
		System.out.print("New Refrigerator added.\n");
	}
	
	
	//Add a TV
	public static void addTV(Scanner scanner) {
		scanner.nextLine(); //clear buffer
		
		System.out.print("Enter product name: ");
		String name = scanner.nextLine();
		
		System.out.print("Enter screen type: ");
		String screen = scanner.nextLine();
		
		System.out.print("Enter resolution: ");
		String res = scanner.nextLine();
		
		System.out.print("Enter display size: ");
		int size = scanner.nextInt();
		
		System.out.print("Enter quantity available: ");
		int qty = scanner.nextInt();
		
		System.out.print("Enter price: ");
		double price = scanner.nextDouble();
		scanner.nextLine(); //clear buffer
		
		System.out.print("Enter item number: ");
		String id = scanner.nextLine();

		products.add(new TV(name, price, qty, id, screen, res, size));
		System.out.print("New TV added.\n");
	}
		

	//View products
	public static void viewProducts(ArrayList<Product> products) {
		if (products == null || products.isEmpty()) {
	        System.out.println("No products available.");
	        return;
	    }else {
	    	System.out.println("\n---------- Product List ----------");
	    	for (int i = 0; i < products.size(); i++) {
	            Product product = products.get(i);
	            System.out.println("\nProduct" + " [" + i + "]");
	            System.out.println("-------------------");
	            System.out.println(product);
	    	}
	    }

	}

}
