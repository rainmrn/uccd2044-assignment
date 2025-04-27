package app;
import java.util.Scanner;

public class product 
{
	private String name;
	private double price;
	private int quantity;

	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() 
	{
		return price;
	}
	
	public void setPrice(double price) 
	{
		this.price = price;
	}

	public int getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	
	public product(String name ,double price, int quantity)
	{
		this.setName(name);
		this.setPrice(price);
		this.setQuantity(quantity);
	}
	
	public void display() 
	{
        System.out.println("Name: " + name);
        System.out.println("Price: RM" + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("----------------------------\r");
    }
	
	public void displayName() 
	{
        System.out.println(name);
    }
	
	public void displayAmount() 
	{
        System.out.println("Quantity of the product: " + quantity);
    }
	

	public void addStock() 
	{
		Scanner scanner = new Scanner(System.in);
		int amount = 0;

		// Loop until a valid input is entered
		while (true) 
		{
			displayAmount();
			System.out.print("Enter the number of units to add: ");
        
			if (scanner.hasNextInt()) 
			{
				amount = scanner.nextInt();
            
				if (amount > 0) 
				{
					this.quantity += amount;
					System.out.println(amount + " units added. New stock: " + this.quantity);
					break; // Valid input, exit the loop
				} 
				else 
				{
					System.out.println("Please enter a positive number.");
				}
			} 
			else 
			{
				System.out.println("Invalid input! Please enter a valid number.");
				scanner.next(); // Clear the invalid input
				scanner.close();
			}
		}
	}


	public void deductStock()
	{
		Scanner scanner = new Scanner(System.in);
		int amount = 0;

		// Loop until a valid input is entered
		while (true) 
		{
			displayAmount();
			System.out.print("Enter the number of units to deduct: ");
        
			if (scanner.hasNextInt()) 
			{
				amount = scanner.nextInt();
            
				if (amount > 0 && amount <= this.quantity) 
				{
					this.quantity -= amount;
					System.out.println(amount + " units deducted. New stock: " + this.quantity);
					break; // Valid input, exit the loop
				} 
				else if (amount > this.quantity) 
				{
					System.out.println("Cannot deduct more than available stock (" + this.quantity + ").");
				} 
				else 
				{
					System.out.println("Please enter a positive number.");
				}
			} 
			else 
			{
				System.out.println("Invalid input! Please enter a valid number.");
				scanner.next(); // Clear the invalid input
				scanner.close();
			}
		}
	}

	
}
