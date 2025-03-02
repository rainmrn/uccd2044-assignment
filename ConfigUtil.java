import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONTokener;

public class ConfigUtil {
    final private static String CONFIG_PATH = "config.json";
    public static File configFile = new File(CONFIG_PATH);
    public static File productFile = new File("products.json");
    public static JSONObject config = new JSONObject();
    public static JSONObject product = new JSONObject();

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

    public static void initProductFile() {
        if (!productFile.exists()) {
            writeProduct();
        }

        try (FileReader productReader = new FileReader(productFile)) {
            product = new JSONObject(new JSONTokener(productReader));
        } catch (IOException error) {
            System.err.println(error);
        }
    }

    public static void writeProduct() {
        try (FileWriter productWriter = new FileWriter(productFile)) {
            productWriter.write(product.toString(4));
        } catch (IOException error) {
            System.err.println(error);
        }
    }
}
