/*
 * 
 * The UserInfo class is used to represent a user so that it records which user managed the stock.
 * A UserInfo object has two attributes (name and user ID) and should have methods to:
 *      • Get name of the user. Prompts the user to enter first name and surname.
 *      • Check if the name entered contains space(s).
 *      • Generate a user ID for user if a valid name is entered (contains space(s)). Take the first
 *        initial from the first name and add it to the full surname to generate the code. If there is
 *        no space, then set the user ID to the default value (“guest”)
 * 
 * For example,
 * If user’s full name is Ah Peng Wong, his user ID will be AWong.
 * If user’s full name is AhPengWong, his user ID will be guest.
 * 
 */

package app.model.entity;

// todo: ask lecturer
public class UserInfo {
    private String name;
    private String userId;

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}