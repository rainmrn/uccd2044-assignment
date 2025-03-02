public class TV extends Product {
    private String screenType, resolution;
    private int displaySize;

    public TV(
        int productId,
        String name,
        int quantity, 
        double price,
        String screenType,
        String resolution,
        int displaySize,
        boolean isActive ) {

        super(productId, name, quantity, price, isActive);
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

    public int getDisplaySize() {
        return displaySize;
    }

    /* --------------- Setter --------------- */

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setDisplaySize(int displaySize) {
        if (displaySize > 0) {
            this.displaySize = displaySize;
        } else {
            this.displaySize = 1;
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
        "\nQuantity available: " + getQuantity() +
        "\nPrice (RM): " + getPrice() +
        "\nInventory value (RM): " + getTotalInventoryValue() +
        "\nProduct status: " + ((getIsActive()) ? "Active" : "Discontinued") ;
    }
}
