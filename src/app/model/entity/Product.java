package app.model.entity;

public abstract class Product {
	// data fields
	private String name;
	private double price;
	private int quantityAvailable;
	private String itemNumber;
	private boolean status;

	// default constructor
	public Product() {
		status = true;
	}

	// parameterized constructor
	public Product(String name, double price, int quantityAvailable, String itemNumber) {
		this(); // call default constructor to set status
		this.name = name;
		this.price = price;
		this.quantityAvailable = quantityAvailable;
		this.itemNumber = itemNumber;
	}

	// Getter & setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantityAvailable;
	}

	public void setQuantity(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public boolean getStatus() {
		return status;
	}
	public String getStatusText() {
		return status ? "Active" : "Discontinued";
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	// instance method
	public double totalInventoryValue() {
		return price * quantityAvailable;
	}

	public void addQuantity(int quantity) {
		if (status)
			quantityAvailable += quantity;
	}

	public void deductQuantity(int quantity) {
		if (status == true && quantity <= quantityAvailable)
			quantityAvailable -= quantity;
	}

	public String getType() {
		if (this instanceof Refrigerator) {
			return "Refrigerator";
		} else if (this instanceof TV) {
			return "TV";
		} else if (this instanceof HairDryer) {
			return "Hair Dryer";
		} else {
			return "Product";
		}
	}

	@Override
	public String toString() {
		return "Item number         : " + itemNumber +
				"\nProduct name        : " + name +
				"\nQuantity available  : " + quantityAvailable +
				"\nPrice (RM)          : " + price +
				"\nInventory Value (RM): " + totalInventoryValue() +
				"\nProduct status      : " + (getStatus() ? "Active" : "Discontinue");
	}
}
