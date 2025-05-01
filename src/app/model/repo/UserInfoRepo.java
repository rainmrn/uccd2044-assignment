package app.model.repo;

import app.model.entity.UserInfo;

public class UserInfoRepo {
    public static UserInfo user = new UserInfo();

    public static String generateUserId() {
        return generateUserId(user.getName());
    }

    public static String generateUserId(String name) {
        String userId;

        if (name.contains(" ")) {
            userId = name.substring(0, 1) + name.substring(name.lastIndexOf(" ") + 1);
        } else {
            userId = "guest";
        }
        return userId;
    }
}
