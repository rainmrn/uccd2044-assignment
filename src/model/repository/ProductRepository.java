package model.repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entity.Product;
import model.entity.Refrigerator;
import model.entity.TV;

public class ProductRepository {
    public static File productFile = new File("products.json");
    public static JSONObject productJson = new JSONObject();
    private static int numOfProduct, numOfActiveProduct, maxNumOfProduct;
    public static Product[] productArray;
    public static Product[] activeProductArray;
    public static ObservableList<Product> productList = FXCollections.observableArrayList();
    public static ObservableList<Product> activeProductList = FXCollections.observableArrayList();

    private ProductRepository() {
    }

    public static int getNumOfProducts() {
        return numOfProduct;
    }

    public static int getNumOfActiveProducts() {
        return numOfActiveProduct;
    }

    // excluding discontinued products
    public static int getTotalStockQuantity() {
        updateProductArray(maxNumOfProduct);

        int result = 0;
        for (int i = 0; i < numOfActiveProduct; i++) {
            result += activeProductList.get(i).getStock();
        }

        return result;
    }

    public static double getTotalInventoryValue() {
        updateProductArray(maxNumOfProduct);

        double result = 0.0;
        for (int i = 0; i < numOfActiveProduct; i++) {
            result += activeProductList.get(i).getTotalInventoryValue();
        }

        return result;
    }

    public static void setMaxNumOfProduct(int number) {
        if (number < 0) {
            maxNumOfProduct = 0;
        } else {
            maxNumOfProduct = number;
        }
    }

    public static int getMaxNumOfProduct() {
        // maxNumOfProduct = ConfigRepository.configJson.getInt("maxNumOfProduct");
        return maxNumOfProduct;
    }

    public static void initProductFile() {
        if (!productFile.exists()) {
            resetProduct();
            writeProductToFile();
        }

        try (FileReader productReader = new FileReader(productFile)) {
            productJson = new JSONObject(new JSONTokener(productReader));
        } catch (IOException error) {
            System.err.println(error);
        }
    }

    /**
     * Initialise productArray using productJson.
     * productJson is a JSONObject read from productFile.
     * 
     * @param maxNumOfProduct
     */
    public static void updateProductArray(int maxNumOfProduct) {
        productArray = new Product[maxNumOfProduct];

        // https://www.baeldung.com/java-iterator
        Iterator<String> productIterator = productJson.keys();
        numOfProduct = 0;

        while (productIterator.hasNext() && numOfProduct < maxNumOfProduct) {
            String currentProductKey = productIterator.next();
            JSONObject currentProductObject = productJson.getJSONObject(currentProductKey);

            System.out.println(currentProductObject);

            Product currentProduct;
            if (currentProductObject.getString("type").equals("refrigerator")) {
                currentProduct = new Refrigerator(
                        Integer.parseInt(currentProductKey),
                        currentProductObject.getString("name"),
                        currentProductObject.getInt("stock"),
                        currentProductObject.getDouble("price"),
                        currentProductObject.getString("doorDesign"),
                        currentProductObject.getString("color"),
                        currentProductObject.getDouble("capacity"),
                        currentProductObject.getBoolean("isActive"));
            } else if (currentProductObject.getString("type").equals("tv")) {
                currentProduct = new TV(
                        Integer.parseInt(currentProductKey),
                        currentProductObject.getString("name"),
                        currentProductObject.getInt("stock"),
                        currentProductObject.getDouble("price"),
                        currentProductObject.getString("screenType"),
                        currentProductObject.getString("resolution"),
                        currentProductObject.getDouble("displaySize"),
                        currentProductObject.getBoolean("isActive"));
            } else {
                continue;
            }

            productArray[numOfProduct++] = currentProduct;
            System.out.println("added product");
        }

        activeProductList.clear();
        numOfActiveProduct = 0;
        productList.clear();
        for (int i = 0; i < numOfProduct; i++) {
            productList.add(productArray[i]);
            if (productArray[i].getIsActive()) {
                activeProductList.add(productArray[i]);
                numOfActiveProduct++;
            }
        }
    }

    public static void updateActiveProductArray() {
        activeProductArray = new Product[numOfProduct];
        numOfActiveProduct = 0;
        for (int i = 0; i < numOfProduct; i++) {
            if (productArray[i].getIsActive()) {
                activeProductArray[numOfActiveProduct++] = productArray[i];
            }
        }
    }

    /**
     * Take the current productJson configuration and write it into the productFile.
     */
    public static void writeProductToFile() {
        try (FileWriter productWriter = new FileWriter(productFile)) {
            productWriter.write(productJson.toString(4));
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

        productJson.put(Integer.toString(refrigerator.getProductId()), newProduct);
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

        productJson.put(Integer.toString(tv.getProductId()), newProduct);
    }

    private static boolean productExists(int productId) {
        for (int i = 0; i < numOfProduct; i++) {
            if (productId == productArray[i].getProductId()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Resets productJson (a JSONObject) to its default values
     */
    public static void resetProduct() {
        JSONObject productBackup = productJson;
        try {
            productJson = new JSONObject("{\r\n" + //
                    "    \"1\": {\r\n" + //
                    "        \"color\": \"gold\",\r\n" + //
                    "        \"price\": 3999,\r\n" + //
                    "        \"name\": \"LG 508L Side-by-Side Fridge in Gold Finish\",\r\n" + //
                    "        \"type\": \"refrigerator\",\r\n" + //
                    "        \"stock\": 10,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"doorDesign\": \"side-by-side\",\r\n" + //
                    "        \"capacity\": 508\r\n" + //
                    "    },\r\n" + //
                    "    \"2\": {\r\n" + //
                    "        \"color\": \"gentle black matt\",\r\n" + //
                    "        \"price\": 2149,\r\n" + //
                    "        \"name\": \"Samsung 360L Refrigerator TMF -Optimal Fresh+ Gentle Black Matt\",\r\n" + //
                    "        \"type\": \"refrigerator\",\r\n" + //
                    "        \"stock\": 12,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"doorDesign\": \"top freezer\",\r\n" + //
                    "        \"capacity\": 360\r\n" + //
                    "    },\r\n" + //
                    "    \"3\": {\r\n" + //
                    "        \"color\": \"black\",\r\n" + //
                    "        \"price\": 5899,\r\n" + //
                    "        \"name\": \"Panasonic Premium French Door Refrigerator\",\r\n" + //
                    "        \"type\": \"refrigerator\",\r\n" + //
                    "        \"stock\": 5,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"doorDesign\": \"french door\",\r\n" + //
                    "        \"capacity\": 500\r\n" + //
                    "    },\r\n" + //
                    "    \"4\": {\r\n" + //
                    "        \"color\": \"white\",\r\n" + //
                    "        \"price\": 5699,\r\n" + //
                    "        \"name\": \"LG 694L Side-by-Side Fridge with Door-in-Door™ in White Glass Finish \",\r\n" + //
                    "        \"type\": \"refrigerator\",\r\n" + //
                    "        \"stock\": 4,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"doorDesign\": \"side-by-side\",\r\n" + //
                    "        \"capacity\": 655\r\n" + //
                    "    },\r\n" + //
                    "    \"5\": {\r\n" + //
                    "        \"color\": \"blue\",\r\n" + //
                    "        \"price\": 2000,\r\n" + //
                    "        \"name\": \"Hitachi HRTN5230MBBKMY\",\r\n" + //
                    "        \"type\": \"refrigerator\",\r\n" + //
                    "        \"stock\": 6,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"doorDesign\": \"top freezer\",\r\n" + //
                    "        \"capacity\": 210\r\n" + //
                    "    },\r\n" + //
                    "    \"6\": {\r\n" + //
                    "        \"price\": 5199,\r\n" + //
                    "        \"name\": \"Samsung 75\\\" QLED QE1D 4K Smart TV\",\r\n" + //
                    "        \"screenType\": \"QLED\",\r\n" + //
                    "        \"type\": \"tv\",\r\n" + //
                    "        \"stock\": 12,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"resolution\": \"4K\",\r\n" + //
                    "        \"displaySize\": 85\r\n" + //
                    "    },\r\n" + //
                    "    \"7\": {\r\n" + //
                    "        \"price\": 2999,\r\n" + //
                    "        \"name\": \"Samsung 55\\\" Crystal UHD DU8500 4K Smart TV\",\r\n" + //
                    "        \"screenType\": \"LED\",\r\n" + //
                    "        \"type\": \"tv\",\r\n" + //
                    "        \"stock\": 5,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"resolution\": \"4K\",\r\n" + //
                    "        \"displaySize\": 55\r\n" + //
                    "    },\r\n" + //
                    "    \"8\": {\r\n" + //
                    "        \"price\": 17999,\r\n" + //
                    "        \"name\": \"LG QNED91 MiniLED AI TV 86\\\" 120Hz HDR10 4K UHD\",\r\n" + //
                    "        \"screenType\": \"Mini-LED\",\r\n" + //
                    "        \"type\": \"tv\",\r\n" + //
                    "        \"stock\": 3,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"resolution\": \"4K\",\r\n" + //
                    "        \"displaySize\": 86\r\n" + //
                    "    },\r\n" + //
                    "    \"9\": {\r\n" + //
                    "        \"price\": 5199,\r\n" + //
                    "        \"name\": \"LG UHD AI TV UT80 75 inch HDR10 4K UHD\",\r\n" + //
                    "        \"screenType\": \"LED\",\r\n" + //
                    "        \"type\": \"tv\",\r\n" + //
                    "        \"stock\": 18,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"resolution\": \"4K\",\r\n" + //
                    "        \"displaySize\": 75\r\n" + //
                    "    },\r\n" + //
                    "    \"10\": {\r\n" + //
                    "        \"price\": 10799,\r\n" + //
                    "        \"name\": \"Sony BRAVIA 8\",\r\n" + //
                    "        \"screenType\": \"OLED\",\r\n" + //
                    "        \"type\": \"tv\",\r\n" + //
                    "        \"stock\": 10,\r\n" + //
                    "        \"isActive\": true,\r\n" + //
                    "        \"resolution\": \"4K\",\r\n" + //
                    "        \"displaySize\": 65\r\n" + //
                    "    }\r\n" + //
                    "}");

            writeProductToFile();
        } catch (JSONException error) {
            System.err.println(error);
            System.out.println("Product reset unsuccessful.");
            productJson = productBackup;
        }
    }
}
