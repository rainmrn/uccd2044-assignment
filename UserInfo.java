import java.util.Scanner;

// todo: ask lecturer
public class UserInfo {
    private String name;
    private String userId;

    public void promptName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Hey! Welcome aboard.");
        System.out.print("Enter your full name (first + last): ");
        name = input.nextLine();
        
        if (name.contains(" ")) {
            userId = name.substring(0, 1) + name.substring(name.lastIndexOf(" ") + 1);
        } else {
            userId = "guest";
        }
        input.close();
    }

    public String getName() {
        if (name == null) {
            promptName();
        }
        return name;
    }

    public String getUserId() {
        if (userId == null) {
            promptName();
        }
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
