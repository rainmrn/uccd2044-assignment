public abstract class Product {
    private String name;
    private double price;
    private int quantity, productId;
    private boolean isActive = true;

    protected Product() {}
    protected Product(int productId, String name, int quantity, double price) {
        this.productId = productId;
        this.name = name;
        setQuantity(quantity);
        setPrice(price);
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

    public int getQuantity() {
        return quantity;
    }

    public boolean getIsActive() {
        return isActive;
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

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            this.quantity = 0;
        }
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /* --------------- Methods --------------- */
    
    public double getTotalInventoryValue() {
        return price * quantity;
    }

    public void addQuantity(int amount) {
        if (!this.isActive) {
            System.out.println("Product is discontinued. No changes were made.");
        } else {
            setQuantity(quantity + amount);
        }
    }

    public void deductQuantity(int amount) {
        if (!this.isActive) {
            System.out.println("Product is discontinued. No changes were made.");
        } else {
            setQuantity(quantity - amount);
        }
    }

    @Override
    public String toString() {
        return "Item number: " + productId +
        "\nProduct name: " + name +
        "\nQuantity available: " + quantity +
        "\nPrice (RM): " + price +
        "\nInventory value (RM): " + getTotalInventoryValue() +
        "\nProduct status: " + ((isActive) ? "Active" : "Discontinued") ;
    }
}
