package model.entity;

import org.json.JSONObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.repository.ProductRepository;

public abstract class Product {
    private String name;
    private double price;
    private int stock, productId;
    private boolean isActive = true;

    protected Product() {
    }

    protected Product(int productId, String name, int stock, double price, boolean isActive) {
        this.productId = productId;
        this.name = name;
        setStock(stock);
        setPrice(price);
        setIsActive(isActive);
    }

    /* --------------- Getter --------------- */

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getProductId() {
        return productId;
    }

    public int getStock() {
        return stock;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public String getIsActiveString() {
        return (isActive ? "Active" : "Discontinued");
    }

    public String getType() {
        if (this instanceof Refrigerator) {
            return "Refrigerator";
        } else if (this instanceof TV) {
            return "TV";
        } else {
            return "Product";
        }
    }

    // for TableView
    public StringProperty isActiveProperty() {
        return (isActive ? new SimpleStringProperty("Active") : new SimpleStringProperty("Discontinued"));
    }

    /* --------------- Setter --------------- */

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        if (price >= 0.0) {
            this.price = price;
        } else {
            this.price = 0.9;
        }
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        } else {
            this.stock = 0;
        }
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;

        JSONObject itemInProductFile = ProductRepository.productJson.getJSONObject(Integer.toString(productId));
        itemInProductFile.put("isActive", isActive);
        ProductRepository.writeProductToFile();
    }

    /* --------------- Methods --------------- */

    public double getTotalInventoryValue() {
        return price * stock;
    }

    public void addStock(int amount) {
        if (!this.isActive) {
            System.out.println("Product is discontinued. No changes were made.");
        } else {
            setStock(stock + amount);

            JSONObject itemInProductFile = ProductRepository.productJson.getJSONObject(Integer.toString(productId));
            itemInProductFile.put("stock", itemInProductFile.getInt("stock") + amount);
            ProductRepository.writeProductToFile();
        }
    }

    public void deductStock(int amount) {
        if (!this.isActive) {
            System.out.println("Product is discontinued. No changes were made.");
        } else {
            setStock(stock - amount);

            JSONObject itemInProductFile = ProductRepository.productJson.getJSONObject(Integer.toString(productId));
            itemInProductFile.put("stock", itemInProductFile.getInt("stock") - amount);
            ProductRepository.writeProductToFile();
        }
    }

    @Override
    public String toString() {
        return "Item number: " + productId +
                "\nProduct name: " + name +
                "\nStock available: " + stock +
                "\nPrice (RM): " + price +
                "\nInventory value (RM): " + getTotalInventoryValue() +
                "\nProduct status: " + ((isActive) ? "Active" : "Discontinued");
    }
}
