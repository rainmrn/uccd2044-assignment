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
        }
    }

    public void deductStock(int amount) {
        if (!this.isActive) {
            System.out.println("Product is discontinued. No changes were made.");
        } else {
            setStock(stock - amount);
        }
    }

    @Override
    public String toString() {
        return "Item number: " + productId +
                "\nProduct name: " + name +
                "\n Stock available: " + stock +
                "\nPrice (RM): " + price +
                "\nInventory value (RM): " + getTotalInventoryValue() +
                "\nProduct status: " + ((isActive) ? "Active" : "Discontinued");
    }
}
