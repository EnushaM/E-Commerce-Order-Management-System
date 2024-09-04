package com.cts.modules;

public class OrderItemCart {
	private int order_item_id;
	private int order_id;
	private int product_id;
	private String name;
	private double price;
	private int quantity;
	private double total_price;
	public OrderItemCart() {
		// TODO Auto-generated constructor stub
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getOrder_item_id() {
		return order_item_id;
	}
	public void setOrder_item_id(int order_item_id) {
		this.order_item_id = order_item_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public OrderItemCart(int order_item_id, int order_id, int product_id, String name, double price, int quantity,
			double total_price) {
		super();
		this.order_item_id = order_item_id;
		this.order_id = order_id;
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.total_price = total_price;
	}
	@Override
	public String toString() 
	{
		return "The Order Item Id is " + order_item_id + "\n" 
		+ "The Product Id is " + product_id + "\n" 
		+ "The Name of the Order Item is" + name+"\n"
		+" The Price of the Order Item is" + price +"\n"
		+ "The Quantity of the order item is "+ quantity + "\n"
		+ "The Total price of the Order Item is"+ total_price+"\n";
	}

}
