package caotinging.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<String, CartItem> cartItemMap = new HashMap<String, CartItem>();
	private double amount;
	public Map<String, CartItem> getCartItemMap() {
		return cartItemMap;
	}
	public void setCartItemMap(Map<String, CartItem> cartItemMap) {
		this.cartItemMap = cartItemMap;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
