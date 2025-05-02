package app.model.entity;
//subclass of Product class

public class Refrigerator extends Product {
	// additional instance variables
	private String doorDesign;
	private String color;
	private int capacity;

	// parameterized constructor
	public Refrigerator(String name, double price, int quantityAvailable, String productID, String doorDesign,
			String color, int capacity) {
		super(name, price, quantityAvailable, productID);
		this.doorDesign = doorDesign;
		this.color = color;
		this.capacity = capacity;
	}

	// Getter & setter
	public String getDoorDesign() {
		return doorDesign;
	}

	public void setDoorDesign(String doorDesign) {
		this.doorDesign = doorDesign;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	// method
	public double calculateStockValue() {
		return super.totalInventoryValue();
	}

	@Override
	public String toString() {
		return "Item number         : " + super.getProductId() +
				"\nProduct name        : " + super.getName() +
				"\nDoor Design         : " + doorDesign +
				"\nColor               : " + color +
				"\nCapacity (in Litres): " + capacity +
				"\nQuantity available  : " + super.getQuantity() +
				"\nPrice (RM)          : " + super.getPrice() +
				"\nInventory value (RM): " + super.totalInventoryValue() +
				"\nProduct status      : " + (super.getStatus() ? "Active" : "Discontinue") + "\n";
	}
}
