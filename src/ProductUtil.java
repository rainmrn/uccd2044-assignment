import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ProductUtil {
    public static File productFile = new File("products.json");
    public static JSONObject product = new JSONObject();

    private ProductUtil() {
    }

    public static void initProductFile() {
        if (!productFile.exists()) {
            resetProduct();
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

    public static void addProduct(Product product) {
        if (product instanceof Refrigerator) {
            addProductRefrigerator((Refrigerator) product); // casting to subclass
        } else if (product instanceof TV) {
            addProductTV((TV) product);
        }
    }

    public static void addProductRefrigerator(Refrigerator refrigerator) {
        JSONObject newProduct = new JSONObject();
        newProduct.put("type", "refrigerator");
        newProduct.put("isActive", true);
        newProduct.put("name", refrigerator.getName());
        newProduct.put("stock", refrigerator.getStock());
        newProduct.put("price", refrigerator.getPrice());
        newProduct.put("doorDesign", refrigerator.getDoorDesign());
        newProduct.put("color", refrigerator.getColor());
        newProduct.put("capacity", refrigerator.getCapacity());

        product.put(Integer.toString(refrigerator.getProductId()), newProduct);
    }

    public static void addProductTV(TV tv) {
        JSONObject newProduct = new JSONObject();
        newProduct.put("type", "tv");
        newProduct.put("isActive", true);
        newProduct.put("name", tv.getName());
        newProduct.put("stock", tv.getStock());
        newProduct.put("price", tv.getPrice());
        newProduct.put("screenType", tv.getScreenType());
        newProduct.put("displaySize", tv.getDisplaySize());
        newProduct.put("resolution", tv.getResolution());

        product.put(Integer.toString(tv.getProductId()), newProduct);
    }

    public static void resetProduct() {
        JSONObject productBackup = product;
        try {
            product = new JSONObject("{\r\n" + //
                    "    \"1\": {\r\n" + //
                    "        \"color\": \"black\",\r\n" + //
                    "        \"price\": 1000,\r\n" + //
                    "        \"name\": \"Samsung\",\r\n" + //
                    "        \"type\": \"refrigerator\",\r\n" + //
                    "        \"stock\": 10,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"doorDesign\": \"2-door\",\r\n" + //
                    "        \"capacity\": 500\r\n" + //
                    "    },\r\n" + //
                    "    \"2\": {\r\n" + //
                    "        \"color\": \"white\",\r\n" + //
                    "        \"price\": 1200,\r\n" + //
                    "        \"name\": \"LG\",\r\n" + //
                    "        \"type\": \"refrigerator\",\r\n" + //
                    "        \"stock\": 8,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"doorDesign\": \"3-door\",\r\n" + //
                    "        \"capacity\": 600\r\n" + //
                    "    },\r\n" + //
                    "    \"3\": {\r\n" + //
                    "        \"color\": \"silver\",\r\n" + //
                    "        \"price\": 1500,\r\n" + //
                    "        \"name\": \"Panasonic\",\r\n" + //
                    "        \"type\": \"refrigerator\",\r\n" + //
                    "        \"stock\": 3,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"doorDesign\": \"4-door\",\r\n" + //
                    "        \"capacity\": 700\r\n" + //
                    "    },\r\n" + //
                    "    \"4\": {\r\n" + //
                    "        \"color\": \"red\",\r\n" + //
                    "        \"price\": 1800,\r\n" + //
                    "        \"name\": \"Toshiba\",\r\n" + //
                    "        \"type\": \"refrigerator\",\r\n" + //
                    "        \"stock\": 2,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"doorDesign\": \"5-door\",\r\n" + //
                    "        \"capacity\": 800\r\n" + //
                    "    },\r\n" + //
                    "    \"5\": {\r\n" + //
                    "        \"color\": \"blue\",\r\n" + //
                    "        \"price\": 2000,\r\n" + //
                    "        \"name\": \"Hitachi\",\r\n" + //
                    "        \"type\": \"refrigerator\",\r\n" + //
                    "        \"stock\": 1,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"doorDesign\": \"6-door\",\r\n" + //
                    "        \"capacity\": 900\r\n" + //
                    "    },\r\n" + //
                    "    \"6\": {\r\n" + //
                    "        \"price\": 1000,\r\n" + //
                    "        \"name\": \"Samsung\",\r\n" + //
                    "        \"screenType\": \"LED\",\r\n" + //
                    "        \"type\": \"tv\",\r\n" + //
                    "        \"stock\": 10,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"resolution\": \"4K\",\r\n" + //
                    "        \"displaySize\": 50\r\n" + //
                    "    },\r\n" + //
                    "    \"7\": {\r\n" + //
                    "        \"price\": 1200,\r\n" + //
                    "        \"name\": \"LG\",\r\n" + //
                    "        \"screenType\": \"OLED\",\r\n" + //
                    "        \"type\": \"tv\",\r\n" + //
                    "        \"stock\": 5,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"resolution\": \"8K\",\r\n" + //
                    "        \"displaySize\": 55\r\n" + //
                    "    },\r\n" + //
                    "    \"8\": {\r\n" + //
                    "        \"price\": 1500,\r\n" + //
                    "        \"name\": \"Panasonic\",\r\n" + //
                    "        \"screenType\": \"QLED\",\r\n" + //
                    "        \"type\": \"tv\",\r\n" + //
                    "        \"stock\": 3,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"resolution\": \"12K\",\r\n" + //
                    "        \"displaySize\": 60\r\n" + //
                    "    },\r\n" + //
                    "    \"9\": {\r\n" + //
                    "        \"price\": 1800,\r\n" + //
                    "        \"name\": \"Toshiba\",\r\n" + //
                    "        \"screenType\": \"Plasma\",\r\n" + //
                    "        \"type\": \"tv\",\r\n" + //
                    "        \"stock\": 2,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"resolution\": \"16K\",\r\n" + //
                    "        \"displaySize\": 65\r\n" + //
                    "    },\r\n" + //
                    "    \"10\": {\r\n" + //
                    "        \"price\": 2000,\r\n" + //
                    "        \"name\": \"Hitachi\",\r\n" + //
                    "        \"screenType\": \"CRT\",\r\n" + //
                    "        \"type\": \"tv\",\r\n" + //
                    "        \"stock\": 1,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"resolution\": \"20K\",\r\n" + //
                    "        \"displaySize\": 70\r\n" + //
                    "    }\r\n" + //
                    "}");
        } catch (JSONException error) {
            System.err.println(error);
            System.out.println("Product reset unsuccessful.");
            product = productBackup;
        }
    }
}
