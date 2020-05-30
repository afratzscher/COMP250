package Assignment1;

public class Jam extends MarketProduct {
	private int numJars;
	private int priceJar;
	
	public Jam (String inputName, int num, int price) {
		super(inputName);
		numJars = num;
		priceJar = price;
	}
	
	public int getCost() {
		return (numJars * priceJar);
	}

	public boolean equals(Object obj) {
		if(obj instanceof Jam) {
			if ((((MarketProduct) obj).getName()).equals(super.getName())) {
				if ((((MarketProduct)obj).getCost())==(getCost())) {
					if ((((Jam)obj).numJars)==(numJars)) {
						return true;
					}
				}
			}
		}
		return false;
	}

}

