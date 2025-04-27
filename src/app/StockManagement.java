package app;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StockManagement 
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
		ArrayList<product> products = new ArrayList<>();
		
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

	            //view products
	            if (choice.equals("1")) 
	            {
	            	if (products.isEmpty()) 
	            	{
	            	    System.out.println("No products available.\n");
	            	} 
	            	else 
	            	{
	            	    System.out.println("Available Products:");
	            	    System.out.println("----------------------------");
	            	    for (product p : products) 
	            	    {
	            	        p.display();
	            	    }
	            	}
	            } 
	            //add products
	            else if (choice.equals("2")) 
	            {
	            	System.out.print("Enter product name: ");
	            	String productName = scanner.nextLine();
	            	System.out.print("Enter product price: ");
	            	double productPrice = Double.parseDouble(scanner.nextLine());
	            	System.out.print("Enter product quantity: ");
	            	int productQuantity = Integer.parseInt(scanner.nextLine());
	            	product newProduct = new product(productName, productPrice, productQuantity);
	            	products.add(newProduct);
	            	System.out.println("Product added successfully!\n");
	            } 
	            //add stock
	            else if (choice.equals("3")) 
	            {
	            	System.out.println("Available Products:");
	            	System.out.println("----------------------------");
	            	for (product p : products) 
            	    {
            	        p.displayName();
            	    }
	                System.out.println("----------------------------\r");
	            	System.out.print("Enter product name to add stock: ");
	            	String nameToAdd = scanner.nextLine();
	            	boolean found = false;
	            	for (product p : products) 
	            	{
	            	    if (p.getName().equalsIgnoreCase(nameToAdd)) 
	            	    {
	            	        p.addStock();
	            	        found = true;
	            	        break;
	            	    }
	            	}
	            	if (!found) {
	            	    System.out.println("Product not found!\n");
	            	}
	            } 
	            //deduct stock from products
	            else if (choice.equals("4")) 
	            {
		            {
		            	System.out.println("Available Products:");
		            	System.out.println("----------------------------");
		            	for (product p : products) 
	            	    {
	            	        p.displayName();
	            	    }
		                System.out.println("----------------------------\r");
		            	System.out.print("Enter product name to deduct stock: ");
		            	String nameToAdd = scanner.nextLine();
		            	boolean found = false;
		            	for (product p : products) 
		            	{
		            	    if (p.getName().equalsIgnoreCase(nameToAdd)) 
		            	    {
		            	        p.deductStock();
		            	        System.out.println("Stock deducted successfully!\n");
		            	        found = true;
		            	        break;
		            	    }
		            	}
		            	if (!found) {
		            	    System.out.println("Product not found!\n");
		            	}
		            } 
	            } 
	            //discontinue a product
	            else if (choice.equals("5")) 
	            {
	            	System.out.println("Available Products:");
	            	System.out.println("----------------------------");
            	    for (product p : products) 
            	    {
            	        p.display();
            	    }
	                System.out.print("\rPlease enter the product name to discontinue: ");
	                String productToRemove = scanner.nextLine();
	                product toRemove = null;
	                boolean input = false;
	                
	                while(!input)
	                {
	                	for (product p : products) 
	                	{
	                		if (p.getName().equalsIgnoreCase(productToRemove)) 
	                		{
	                			toRemove = p;
	                			break;
	                		}
	                	}
	                	if (toRemove != null) 
	                	{
	                		products.remove(toRemove);
	                		System.out.println("Product discontinued successfully!\n");
	                		input = true;
	                	} 
	                	else 
	                	{
	                		System.out.println("Product not found!\n");
	                	}
	                } 
	            }
	            //exit the program
	            else if (choice.equals("6")) 
	            {
	            	System.out.println("Goodbye, " + username + "!\rUser ID: "); // add id in user info
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
