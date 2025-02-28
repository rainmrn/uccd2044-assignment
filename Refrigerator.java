public class Refrigerator extends Product {
    private String doorDesign, color;
    private int capacity;

    public Refrigerator(
        int productId,
        String name,
        int quantity, 
        double price,
        String doorDesign,
        String color,
        int capacity ) {

        super(productId, name, quantity, price);
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

    public int getCapacity() {
        return capacity;
    }

    /* --------------- Setter --------------- */

    public void setDoorDesign(String doorDesign) {
        this.doorDesign = doorDesign;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            this.capacity = 1;
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
        "\nQuantity available: " + getQuantity() +
        "\nPrice (RM): " + getPrice() +
        "\nInventory value (RM): " + getTotalInventoryValue() +
        "\nProduct status: " + ((getIsActive()) ? "Active" : "Discontinued") ;
    }
}
