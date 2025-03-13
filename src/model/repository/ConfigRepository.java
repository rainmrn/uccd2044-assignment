package model.repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONTokener;

import model.entity.UserInfo;

public class ConfigRepository {
    final private static String CONFIG_PATH = "config.json";
    public static File configFile = new File(CONFIG_PATH);
    public static JSONObject configJson = new JSONObject();

    private ConfigRepository() {
    };

    public static void readConfig() {
        // Bug: empty config.json file will cause exception
        if (!configFile.exists()) {
            writeConfigToFile();
        }

        try (FileReader configReader = new FileReader(configFile)) {
            configJson = new JSONObject(new JSONTokener(configReader));
            if (isValidConfig()) {
                initRepositoriesFromConfig();
            }
        } catch (IOException error) {
            System.err.println(error);
        }
    }

    public static boolean isValidConfig() {
        if (configJson.isEmpty()) {
            return false;
        }

        if (!configJson.has("name") || !configJson.has("userId") || !configJson.has("maxNumOfProduct")) {
            return false;
        }

        return true;
    }

    public static void initRepositoriesFromConfig() {
        UserRepository.createUser(
                configJson.getString("name"),
                configJson.getString("userId"));

        ProductRepository.setMaxNumOfProduct(ConfigRepository.configJson.getInt("maxNumOfProduct"));
    }

    public static void writeConfigToFile() {
        try (FileWriter configWriter = new FileWriter(configFile)) {
            configWriter.write(configJson.toString(4));
        } catch (IOException error) {
            System.err.println(error);
        }
    }

    public static boolean isExistingUser() {
        readConfig();

        if (configJson.isEmpty()) {
            return false;
        }

        if (!configJson.has("name")) {
            return false;
        }

        if (configJson.getString("name") == "") {
            return false;
        }

        return true;
    }

    public static void updateConfig() {
        UserInfo user = UserRepository.getCurrentUser();
        configJson.put("name", user.getName());
        configJson.put("userId", user.getUserId());

        configJson.put("maxNumOfProduct", ProductRepository.getMaxNumOfProduct());

        writeConfigToFile();
    }
}
