import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Main {
    final private static String CONFIG_PATH = "config.json";
    private static JSONObject config;
    private static File configFile = new File(CONFIG_PATH);

    public static void main(String[] args) {
        initConfig();
        LocalDateTime currentDate = LocalDateTime.now();

        System.out.println("Welcome to the stock management system.");
        System.out.println("Date: " + currentDate.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.MEDIUM)));
        System.out.println("Current user: " + config.getString("name"));

    }

    private static void initConfig() {
        if (!configFile.exists()) {
            Scanner input = new Scanner(System.in);
            System.out.print("Hey! Enter your name: ");
            String name = input.nextLine();
            input.close();
            
            config = new JSONObject();
            config.put("name", name);
            writeConfig();
        } else {
            try (FileReader configReader = new FileReader(configFile)) {
                config = new JSONObject(new JSONTokener(configReader));
            } catch (IOException error) {
                System.err.println(error);
            }
        }
    }

    private static void writeConfig() {
        try (FileWriter configWriter = new FileWriter(configFile)) {
            configWriter.write(config.toString(4));
        } catch (IOException error) {
            System.err.println(error);
        }
    }
}