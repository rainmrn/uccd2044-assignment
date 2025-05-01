package app.model.repo;

import app.model.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductRepo {
    private static int maxProduct;
    public static ObservableList<Product> productList = FXCollections.observableArrayList();
    public static ObservableList<Product> activeProductList = FXCollections.observableArrayList();
    public static String[] productTypes = { "Refrigerator", "TV" };

    public static void setMaxProduct(int input) {
        maxProduct = input;
    }

    public static int getMaxProduct() {
        return maxProduct;
    }

    public static boolean hasProduct() {
        if (productList.isEmpty() || productList == null) {
            return false;
        }

        return true;
    }
    public static boolean hasActiveProduct() {
        updateActiveProductList();
        if (activeProductList.isEmpty() || activeProductList == null) {
            return false;
        }

        return true;
    }

    public static void addStock(int index, int quantity) {
        productList.get(index).addQuantity(quantity);
        System.out.println("adding " + quantity + " to product " + index);
    }

    public static void deductStock(int index, int quantity) {
        productList.get(index).deductQuantity(quantity);
    }

    public static void discontinueProduct(int index) {
        productList.get(index).setStatus(false);
    }

    public static void addProduct(Product newProduct) {
        productList.add(newProduct);
    }

    
    public static int getNumOfProduct() {
        return productList.size();
    }

    public static int getNumOfActiveProduct() {
        return activeProductList.size();
    }
    
    public static int getTotalStockQuantity() {
        updateActiveProductList();

        int result = 0;
        for (Product product : activeProductList) {
            result += product.getQuantity();
        }
        return result;
    }

    public static double getTotalInventoryValue() {
        updateActiveProductList();

        double result = 0;
        for (Product product : activeProductList) {
            result += product.getPrice();
        }
        return result;
    }

    public static void updateActiveProductList() {
        activeProductList.clear();
        for (Product product : productList) {
            if (product.getStatus()) {
                activeProductList.add(product);
            }
        }
    }
}
