package model.entity;

public class TV extends Product {
    private String screenType, resolution;
    private double displaySize;

    public TV(
            int productId,
            String name,
            int stock,
            double price,
            String screenType,
            String resolution,
            double displaySize,
            boolean isActive) {

        super(productId, name, stock, price, isActive);
        this.screenType = screenType;
        this.resolution = resolution;
        setDisplaySize(displaySize);
    }

    /* --------------- Getter --------------- */

    public String getScreenType() {
        return screenType;
    }

    public String getResolution() {
        return resolution;
    }

    public double getDisplaySize() {
        return displaySize;
    }

    /* --------------- Setter --------------- */

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setDisplaySize(double displaySize) {
        if (displaySize > 0) {
            this.displaySize = displaySize;
        } else {
            this.displaySize = 1.0;
        }
    }

    /* --------------- Methods --------------- */

    @Override
    public String toString() {
        return "Item number: " + getProductId() +
                "\nProduct name: " + getName() +
                "\nScreen type: " + getScreenType() +
                "\nResolution: " + getResolution() +
                "\nDisplay size (in inches): " + getDisplaySize() +
                "\nQuantity available: " + getStock() +
                "\nPrice (RM): " + getPrice() +
                "\nInventory value (RM): " + getTotalInventoryValue() +
                "\nProduct status: " + ((getIsActive()) ? "Active" : "Discontinued");
    }
}
