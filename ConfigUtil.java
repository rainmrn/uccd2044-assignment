import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONTokener;

public class ConfigUtil {
    final private static String CONFIG_PATH = "config.json";
    public static File configFile = new File(CONFIG_PATH);
    public static JSONObject config = new JSONObject();

    private ConfigUtil() {};

    public static void initConfigFile() {
        if (!configFile.exists()) {
            writeConfig();
        }

        try (FileReader configReader = new FileReader(configFile)) {
            config = new JSONObject(new JSONTokener(configReader));
        } catch (IOException error) {
            System.err.println(error);
        }
    }

    public static void writeConfig() {
        try (FileWriter configWriter = new FileWriter(configFile)) {
            configWriter.write(config.toString(4));
        } catch (IOException error) {
            System.err.println(error);
        }
    }
}
