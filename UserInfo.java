import java.util.Scanner;

// todo: ask lecturer
public class UserInfo {
    private String name;
    private String userId;

    public void promptName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your full name (first + last): ");
        name = input.nextLine();
        
        if (name.contains(" ")) {
            userId = name.substring(0, 1) + name.substring(name.lastIndexOf(" ") + 1);
            System.out.println("user id: " + userId);
        } else {
            userId = "guest";
        }
        input.close();
    }
}
