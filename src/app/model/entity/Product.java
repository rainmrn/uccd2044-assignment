package app.model.entity;

public abstract class Product {
	// data fields
	private String name;
	private double price;
	private int quantity_available;
	private String product_id;
	private boolean status;

	// default constructor
	public Product() {
		status = true;
	}

	// parameterized constructor
	public Product(String name, double price, int quantity_available, String product_id) {
		this(); // call default constructor to set status
		this.name = name;
		this.price = price;
		this.quantity_available = quantity_available;
		this.product_id = product_id;
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
		return quantity_available;
	}

	public void setQuantity(int quantity_available) {
		this.quantity_available = quantity_available;
	}

	public String getProductId() {
		return product_id;
	}

	public void setProductId(String product_id) {
		this.product_id = product_id;
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
		return price * quantity_available;
	}

	public void addQuantity(int quantity) {
		if (status)
			quantity_available += quantity;
	}

	public void deductQuantity(int quantity) {
		if (status == true && quantity <= quantity_available)
			quantity_available -= quantity;
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

	@Override
	public String toString() {
		return "Item number         : " + product_id +
				"\nProduct name        : " + name +
				"\nQuantity available  : " + quantity_available +
				"\nPrice (RM)          : " + price +
				"\nInventory Value (RM): " + totalInventoryValue() +
				"\nProduct status      : " + (getStatus() ? "Active" : "Discontinue");
	}
}
