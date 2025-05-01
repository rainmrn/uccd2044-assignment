package app;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StockManagement 
{

	public static void main(String[] args) 
	{
		String choice;
		Scanner scanner = new Scanner(System.in);
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String sysdate = date.format(format);
		System.out.println("Welcome to SMS!\rDate and Time: "+sysdate);
		System.out.println("Group Member: \rChan Sin Ying\rChen Lee Peng\rLim Qi Xun\rOh Yi Heng\n");
	
		UserInfo user = new UserInfo();
		user.promptName();
		System.out.println("Welcome, " + user.getName() + "!");
		while (true)
		{
			System.out.print("Do you wish to add any products? (y/n): ");
			choice = scanner.nextLine();
			choice.toLowerCase();
			if (choice.equals("y"))
			{
				
			}
			else if (choice.equals("n"))
			{
				String value; 
				System.out.print("Please enter '0' to exit the program: ");
				value = scanner.nextLine();
				if (value.equals("0"))
				{
					System.out.println("Exiting the program...");
					scanner.close();
					return;
				}
				else
					System.out.println("Zero value not inputed, return to top...\r");
			}
			else
			{
				System.out.println("Invalid input, please try again.");
			}
		}
		
	}

}
