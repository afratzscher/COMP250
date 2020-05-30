package Assignment1;

public abstract class MarketProduct {
	private String name;
	
	public MarketProduct(String inputName) {
		name = inputName;
	}
	
	public final String getName() {
		return name;
	}
	
	public abstract int getCost();
	
	public abstract boolean equals(Object obj);
	
}