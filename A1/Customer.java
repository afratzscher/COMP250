package Assignment1;

public class Customer {
	private String name;
	private int balance;
	private Basket cart;
	
	public Customer(String inputName, int num) {
		name = inputName;
		balance = num;
		cart = new Basket();
	}
	
	public String getName() {
		return name;		
	}
	
	public int getBalance() {
		return balance;
	}
	
	public Basket getBasket() {
		return cart;		
	}
	
	public int addFunds(int num) {
		if (num<0) {
			throw new IllegalArgumentException();
		}
		else { 
			balance += num;
			return balance;
		}	
	}
	
	public void addToBasket(MarketProduct product) {
		cart.add(product);
	}
	
	public boolean removeFromBasket(MarketProduct product) {
		return cart.remove(product);
	}
	
	public String checkOut() {
		if (balance < cart.getTotalCost()) {
			throw new IllegalStateException();
		}
		else {
			balance = balance - cart.getTotalCost();
			String toPrint = cart.toString();
			cart.clear();
			return (toPrint);
		}
	}
}
