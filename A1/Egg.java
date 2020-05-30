package Assignment1;

public class Egg extends MarketProduct {	
	private int numEggs;	
	private int priceDozen;
	
	public Egg(String inputName, int num, int price) {
		super(inputName);
		priceDozen = price;
		numEggs = num;
	}
	
	public int getCost() {
		return (numEggs * priceDozen/12);
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Egg) {
			if ((((MarketProduct) obj).getName()).equals(super.getName())) {
				if ((((MarketProduct)obj).getCost())==(getCost())) {
					if ((((Egg)obj).numEggs)==(numEggs)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
