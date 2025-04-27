package app;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main 
{
	


	public static void main(String[] args)
	{
		String username,choice;
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String sysdate = date.format(format);
		System.out.println("Welcome to SMS!\rDate and Time: "+sysdate);
		System.out.println("Group Member: \rChan Sin Ying\rChen Lee Peng\rLim Qi Xun\rOh Yi Heng\n");
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter your username: ");
		username = scanner.nextLine();
		System.out.println("Welcome "+ username + "!");		
		
		   while (true) 
		   {
	            // Display menu options
	            System.out.println("Menu Options:");
	            System.out.println("1. View products");
	            System.out.println("2. Add products");
	            System.out.println("3. Add stock");
	            System.out.println("4. Deduct stock");
	            System.out.println("5. Discontinue a product");
	            System.out.println("6. Exit program");
	            System.out.print("Please enter a number: ");
	            choice = scanner.nextLine().trim();

	            if (choice.equals("1")) 
	            {
	                System.out.println("Viewing products...\n");
	            } 
	            else if (choice.equals("2")) 
	            {
	                System.out.println("Adding new products...\n");
	                // Later: you can ask user for product name, price, stock, etc here
	            } 
	            else if (choice.equals("3")) 
	            {
	                System.out.println("Adding stock to existing products...\n");
	            } 
	            else if (choice.equals("4")) 
	            {
	                System.out.println("Deducting stock from products...\n");
	            } 
	            else if (choice.equals("5")) 
	            {
	                System.out.println("Discontinuing a product...\n");
	            } 
	            else if (choice.equals("6")) 
	            {
	                System.out.println("Exiting the program...");
	                scanner.close();
	                return;
	            } 
	            else {
	                System.out.println("Invalid choice! Please try again.\n");
	            }
	        }
	    }
}