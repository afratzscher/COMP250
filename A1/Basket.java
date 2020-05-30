package Assignment1;

public class Basket {
	private MarketProduct[] ListofProducts; 
	
	public Basket() {
		ListofProducts = new MarketProduct[0];
	}
	
	public MarketProduct[] getProducts() {
		MarketProduct[] copy = new MarketProduct[ListofProducts.length];
		for (int i = 0; i < ListofProducts.length; i++) {
			copy[i] = ListofProducts[i];
		}
		return copy;
	}
	
	public void add(MarketProduct product) {
		MarketProduct[] newArray = new MarketProduct[ListofProducts.length + 1];
		for (int i = 0; i < ListofProducts.length; i++) {
			newArray[i] = ListofProducts[i];
		}
		newArray[ListofProducts.length] = product;
		
		ListofProducts = newArray;
	}
	
	public boolean remove(MarketProduct product) {
		MarketProduct[] newArray = new MarketProduct[ListofProducts.length-1];
		for (int i = 0; i < ListofProducts.length; i++) {
			if (ListofProducts[i].equals(product)) {
				ListofProducts[i] = null;
				for (int j = 0; j < i; j++) {
					newArray[j] = ListofProducts[j];
				}
				for (int j = ListofProducts.length-1; j > i; j--) {
					newArray[j-1] = ListofProducts[j];
				}
				ListofProducts = newArray;
				return true;
			}
		}	
		return false;
	}
	
	public void clear() {
		for (int i =0; i < ListofProducts.length; i++) {
			ListofProducts[i] = null;
		}
	}
	
	public int getNumOfProducts() {
		int numProduct = ListofProducts.length;
		return numProduct;
	}
	
	public int getSubTotal() {
		int subTotal = 0;
		for (int i=0; i < ListofProducts.length; i++) {
			int newSubTotal = ListofProducts[i].getCost();
			subTotal += newSubTotal;
		}
		return subTotal;
	}
	
	public int getTotalTax() {
		int taxTotal = 0;
		String location ="";
		for (int i=0; i< ListofProducts.length; i++) {
			location = ListofProducts[i].toString();
			if (location.contains("Jam")){
				int forTax = ((MarketProduct)(ListofProducts[i])).getCost();
				double toTax = 0.15 * forTax;
				taxTotal += toTax;
			}
		}
		return taxTotal;
	}
	
	public int getTotalCost() {
		int subTotal = getSubTotal();
		int taxTotal = getTotalTax();
		int totalCost = subTotal + taxTotal;
		return totalCost;
	}
	
	private String toDollars(int price) {
		int dollars = price/100;
		return String.valueOf(dollars);
	}
	
	private String toCents (int price) {
		int cents = ((price%100));
		if (cents < 10) {
			String centsDouble = "0";
			if (cents <= 0) {
				centsDouble = "-";
				return centsDouble;
			}
			centsDouble = "0" + String.valueOf(cents);
			return ("." + String.valueOf(centsDouble));
		}
		return ("." + String.valueOf(cents));
	}
	
	public String toString() {
		String toPrint = "";
		for (int i=0; i<ListofProducts.length; i++) {
			int price = ((MarketProduct)(ListofProducts[i])).getCost();
			String addedPrint = ((MarketProduct)(ListofProducts[i])).getName() 
					+ "\t" + toDollars(price) + toCents(price) + "\n";
			toPrint = toPrint + addedPrint;
		}
		toPrint = toPrint + "\n";
		toPrint = toPrint + "Subtotal\t" + toDollars(getSubTotal()) + toCents(getSubTotal());
		toPrint = toPrint + "\n";
		toPrint = toPrint + "Total Tax\t" + toDollars(getTotalTax()) + toCents(getTotalTax());
		toPrint = toPrint + "\n" + "\n" + "Total Cost\t" + toDollars(getTotalCost()) + toCents(getTotalCost());
		
		return toPrint;
	}
}

