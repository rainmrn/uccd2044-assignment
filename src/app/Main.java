package app;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main 
{
	


	public static void main(String[] args)
	{
		String username,addproducts,exit,choice;
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String sysdate = date.format(format);
		System.out.println("Welcome to SMS!\rDate and Time: "+sysdate);
		System.out.println("Group Member: \rChan Sin Ying\rChen Lee Peng\rLim Qi Xun\rOh Yi Heng\n");
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter your username: ");
		username = scanner.nextLine();
		System.out.println("Welcome "+ username + "!");		
		
		while (true){
			System.out.print("Do you wish to add any products? (y/n): ");
			addproducts=scanner.nextLine().trim().toLowerCase();
			if(addproducts.equals("y"))
			{
				boolean validChoice = false;
				while(!validChoice)
				{
					//display menu option
			        System.out.println("Menu Options: ");
			        System.out.println("1. View products");
			        System.out.println("2. Add stock");
			        System.out.println("3. Deduct stock");
			        System.out.println("4. Discontinue a product");
			        System.out.println("5. Exit");
			        System.out.print("Please enter a number: ");
			        choice = scanner.nextLine().trim();;
				
			        if (choice.equals("1")) 
			        {
			        	System.out.println("Viewing products...");
			            
			        } 
			        else if (choice.equals("2")) 
			        {
			        	System.out.println("Adding stock...");
			            
			        } 
			        else if (choice.equals("3")) 
			        {
			        	System.out.println("Deducting stock...");
			            
			        } 
			        else if (choice.equals("4")) 
			        {
			        	System.out.println("Discontinuing product...");
			            
			        } 
			        else if (choice.equals("5")) 
			        {
			        	System.out.println("Returning to main menu...\n");
			            validChoice = true; // break the menu loop
			        } 
			        else 
			        {
			            System.out.println("Invalid choice! Please try again.\n");
			        }
				}

			}
			else if (addproducts.equals("n"))
			{
				System.out.println("Please enter a zero value to exit the program.");
				exit =scanner.nextLine();
				if(exit.equals("0"))
				{
					System.out.println("Exiting the program...");
					scanner.close();
					return;
				}
				else 
				{
                    System.out.println("Invalid exit value! Returning to main menu.\n");
                }
			}
			else
				System.out.println("Invalid input! Please try again.\n");

		}
	}
}