package app.model.entity;
//subclass of Product class

public class HairDryer extends Product {
	// additional instance variables
	private String nozzleType;
	private String color;
	private int powerWatts;

	// parameterized constructor
	public HairDryer(String name, double price, int quantityAvailable, String productID, String nozzleType,
			String color, int powerWatts) {
		super(name, price, quantityAvailable, productID);
		this.nozzleType = nozzleType;
		this.color = color;
		this.powerWatts = powerWatts;
	}

	// Getter & setter
	public String getNozzleType() {
		return nozzleType;
	}

	public void setNozzleType(String nozzleType) {
		this.nozzleType = nozzleType;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPowerWatts() {
		return powerWatts;
	}

	public void setPowerWatts(int powerWatts) {
		this.powerWatts = powerWatts;
	}

	// method
	public double calculateStockValue() {
		return super.totalInventoryValue();
	}

	@Override
	public String toString() {
		return "Item number         : " + super.getItemNumber() +
				"\nProduct name        : " + super.getName() +
				"\nNozzle Type         : " + nozzleType +
				"\nColor               : " + color +
				"\nPower (in Watts)    : " + powerWatts +
				"\nQuantity available  : " + super.getQuantity() +
				"\nPrice (RM)          : " + super.getPrice() +
				"\nInventory value (RM): " + super.totalInventoryValue() +
				"\nProduct status      : " + (super.getStatus() ? "Active" : "Discontinue") + "\n";
	}
}
