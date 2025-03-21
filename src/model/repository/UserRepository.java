package model.repository;

import model.entity.UserInfo;

public class UserRepository {
    private static UserInfo currentUser = new UserInfo();
    // private static UserInfo[] userArray = new UserInfo[10];

    private UserRepository() {}

    public static UserInfo getCurrentUser() {
        return currentUser;
    }

    public static void createUser(String name) {
        currentUser.setName(name);
    }

    public static void createUser(String name, String userId) {
        currentUser.setName(name);
        currentUser.setUserId(userId);
    }
}
