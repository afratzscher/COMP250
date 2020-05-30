package Assignment1;

public class Fruit extends MarketProduct{
	private double weightFruit;
	private int priceKg;
	
	public Fruit (String inputName, double weight, int price) {
		super(inputName);
		weightFruit = weight;
		priceKg = price;
	}
	
	public int getCost() {
		return ((int) (weightFruit*priceKg));
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Fruit) {
			if ((((MarketProduct) obj).getName()).equals(super.getName())) {
				if ((((Fruit)obj).weightFruit)==(weightFruit)) {
					if ((((MarketProduct)obj).getCost())==((int) (getCost()))) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
