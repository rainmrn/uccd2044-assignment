package app.model.repo;

import java.util.ArrayList;

import app.model.entity.Product;

public class ProductRepo {
    private static int maxProduct;
    public static ArrayList<Product> productArrayList = new ArrayList<Product>();
    public static String[] productTypes = {"Refrigerator", "TV"};

    public static void setMaxProduct(int input) {
        maxProduct = input;
    }
    public static int getMaxProduct() {
        return maxProduct;
    }

    public static boolean hasProduct() {
        if (productArrayList.isEmpty() || productArrayList == null) {
            return false;
        }

        return true;
    }

    public static int getNumOfProduct() {
        return productArrayList.size();
    }

    public static void addStock(int index, int quantity) {
        productArrayList.get(index).addQuantity(quantity);
    }
    public static void deductStock(int index, int quantity) {
        productArrayList.get(index).deductQuantity(quantity);
    }

    public static void discontinueProduct(int index) {
		productArrayList.get(index).setStatus(false);
	}


    public static void addProduct(Product newProduct) {
        productArrayList.add(newProduct);
	}
}
