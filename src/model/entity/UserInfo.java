package model.entity;

// todo: ask lecturer


public class UserInfo {
    private String name;
    private String userId;

    public UserInfo() {
        name = "Default User";
        userId = "user";
    }

    public UserInfo(String name) {
        setName(name);
    }

    public UserInfo(String name, String userId) {
        setName(name);
        setUserId(userId);
    }

    public String getName() {
        if (name == null) {
            return "";
        }
        return name;
    }

    public String getUserId() {
        if (userId == null) {
            return "";
        }
        return userId;
    }

    public void setName(String name) {
        this.name = name;
        if (name.contains(" ")) {
            userId = name.substring(0, 1) + name.substring(name.lastIndexOf(" ") + 1);
        } else {
            userId = "guest";
        }
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
