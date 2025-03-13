package model.entity;

public class Refrigerator extends Product {
    private String doorDesign, color;
    private double capacity;

    public Refrigerator(
            int productId,
            String name,
            int stock,
            double price,
            String doorDesign,
            String color,
            double capacity,
            boolean isActive) {

        super(productId, name, stock, price, isActive);
        this.doorDesign = doorDesign;
        this.color = color;
        setCapacity(capacity);
    }

    /* --------------- Getter --------------- */

    public String getDoorDesign() {
        return doorDesign;
    }

    public String getColor() {
        return color;
    }

    public double getCapacity() {
        return capacity;
    }

    /* --------------- Setter --------------- */

    public void setDoorDesign(String doorDesign) {
        this.doorDesign = doorDesign;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCapacity(double capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            this.capacity = 1.0;
        }
    }

    /* --------------- Methods --------------- */

    @Override
    public String toString() {
        return "Item number: " + getProductId() +
                "\nProduct name: " + getName() +
                "\nDoor design: " + getDoorDesign() +
                "\nColor: " + getColor() +
                "\nCapacities (in litres): " + getCapacity() +
                "\nQuantity available: " + getStock() +
                "\nPrice (RM): " + getPrice() +
                "\nInventory value (RM): " + getTotalInventoryValue() +
                "\nProduct status: " + ((getIsActive()) ? "Active" : "Discontinued");
    }
}
