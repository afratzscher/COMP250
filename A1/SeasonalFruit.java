package Assignment1;

public class SeasonalFruit extends Fruit {
	
	public SeasonalFruit(String inputName, double weight, int price) {
		super(inputName, weight, price);
	}
	
	public int getCost() {
		double cost = 0.85 * super.getCost();
		return ((int) cost);
	}
}
