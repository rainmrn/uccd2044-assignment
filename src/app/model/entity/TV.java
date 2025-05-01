package app.model.entity;
// subclass of the Product class

public class TV extends Product {
	// additional instance variable
	private String screenType;
	private String resolution;
	private int displaySize;

	// parameterized constructor
	public TV(String name, double price, int quantity_available, String product_id, String screenType,
			String resolution, int displaySize) {
		super(name, price, quantity_available, product_id);
		this.screenType = screenType;
		this.resolution = resolution;
		this.displaySize = displaySize;
	}

	// Getter & Setter
	public String getScreenType() {
		return screenType;
	}

	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public int getDisplaySize() {
		return displaySize;
	}

	public void setDisplaySize(int displaySize) {
		this.displaySize = displaySize;
	}

	// method
	public double calculateStockValue() {
		return super.totalInventoryValue();
	}

	@Override
	public String toString() {
		return "Item number         : " + super.getProductId()
				+ "\nProduct name        : " + super.getName()
				+ "\nScreenType          : " + screenType
				+ "\nResolution          : " + resolution
				+ "\nDisplay size        : " + displaySize
				+ "\nQuantity available  : " + super.getQuantity()
				+ "\nPrice (RM)          : " + super.getPrice()
				+ "\nInventory value (RM): " + super.totalInventoryValue()
				+ "\nProduct status      : " + (super.getStatus() ? "Active" : "Discontinue");
	}
}
