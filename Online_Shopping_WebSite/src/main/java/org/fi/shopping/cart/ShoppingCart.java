package org.fi.shopping.cart;

import java.util.ArrayList;
import java.util.Iterator;

import org.fi.shopping.pojo.CartItem;

public class ShoppingCart implements Cart {

	
	ArrayList<CartItem> cartItems = new ArrayList<>();
	
	@Override
	public void addCart(CartItem item) {
		if(item==null) {
			throw new CartException("Invalid Details");
		}
		cartItems.add(item);
	}
	
	@Override
	public void removeCart(CartItem item) {
		if(item==null) {
			throw new CartException("Invalid Details");
		}
		cartItems.remove(cartItems.indexOf(item)+1);
	}
	
	@Override
	public Iterator<CartItem> getAllItems() {
		if(!cartItems.isEmpty()) {
			return cartItems.iterator();
		}
		else {
			if(cartItems.isEmpty()) {
				return cartItems.iterator();
			}
			else {
				
				throw new CartException("NO product in the cart");
			}
		
			
		}
	}

	@Override
	public void removeAllCart(CartItem item) {
		if(item==null) {
			throw new CartException("Invalid Details");
		}
		cartItems.clear();;
		
	}
}
