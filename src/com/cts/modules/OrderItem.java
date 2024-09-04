package com.cts.modules;
/*
The package modules contains the class modules for each four database in the E-Commerce database 
Each module contains the entities of the database with separate getter, setter methods for each 
entity and module Description function
*/
public class OrderItem {

	private int order_item_id;
	private int order_id;
	private int product_id;
	private int quantity;
	private double price;
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderItem(int product_id, int quantity, double price) {
		
		/*we're not including the order_item_id and order_id, since the order_id would be created only 
		  after the customer add the entire list of order items to the cart,and the
		  order_item_id will be added automatically as it is primary key and auto-incrementable
		*/
		super();
		this.product_id = product_id;
		this.quantity = quantity;
		this.price = price;
	}
	public int getOrder_item_id() {
		return order_item_id;
	}
	public void setOrder_item_id(int order_item_id) {
		this.order_item_id = order_item_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {

		return String.format(
			    "-------------------------------------------------------------\n" +
			    "| %-15s %-15s %-15s %-15s %-15s |\n" +
			    "| %-15d %-15d %-15d %-15d %-15.2f |\n" +
			    "-------------------------------------------------------------\n",
			    "Order Item Id", "Order Id", "Product Id", "Quantity", "Price",
			    order_id, order_id, product_id, quantity, price
			);

	}
}