package org.fi.shopping.cart;

import java.util.Iterator;

import org.fi.shopping.pojo.CartItem;

public interface Cart {

	
	public void addCart(CartItem item);
	public void removeCart(CartItem item);
	public void removeAllCart(CartItem item);
	public Iterator<CartItem> getAllItems();
	
}
