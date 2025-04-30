package app;

import java.util.Scanner;

public class StockManagement {


	private static Product[] products;
	
	public static int getMaxProducts(Scanner scanner) {
		int maxProduct;
		
		do {			
			System.out.print("Enter the number of products to store: ");
		
			while(!scanner.hasNextInt()) {
				System.out.print("Invalid input! Please enter an positive integer: ");
				scanner.nextInt();
			 }
			 
			 maxProduct = scanner.nextInt();
			 System.out.println("");
			 
		 }while(maxProduct < 0);
		return maxProduct;
	}	
	
	
	



		
	   
	
	public static void main(String[] args) {
		
		
		
	}

}
