package org.fi.shopping.pojo;

public class CartItem {
	
	private int categoryId;
	private int productsId;
	private float productsPrice;
	
	public CartItem() {
		
		// TODO Auto-generated constructor stub
	}

	public CartItem(int categoryId, int productsId, float productsPrice) {
		super();
		this.categoryId = categoryId;
		this.productsId = productsId;
		this.productsPrice = productsPrice;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getProductsId() {
		return productsId;
	}

	public void setProductsId(int productsId) {
		this.productsId = productsId;
	}

	public float getProductsPrice() {
		return productsPrice;
	}

	public void setProductsPrice(float productsPrice) {
		this.productsPrice = productsPrice;
	}
	
	
	
	
	

}
