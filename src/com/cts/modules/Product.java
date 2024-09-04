package com.cts.modules;
/*
The package modules contains the class modules for each four database in the E-Commerce database 
Each module contains the entities of the database with separate getter, setter methods for each 
entity and module Description function
*/
public class Product {

	int product_id;
	String name;
	String description;
	double price;
	int quantity_in_stock;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int product_id, String name, String description, double price, int quantity_in_stock) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.description = description;
		this.price=price;
		this.quantity_in_stock = quantity_in_stock;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity_in_stock() {
		return quantity_in_stock;
	}
	public void setQuantity_in_stock(int quantity_in_stock) {
		this.quantity_in_stock = quantity_in_stock;
	}
	@Override
	public String toString() {
		return "The Product's ID is "+product_id + "\n"
				+ "The Product's Name is " + name + "\n"
				+"The Product's Description is " + description + "\n"
				+"The Product's Price is " + price + "\n"
				+"The Product's Quantity available in stock is " + quantity_in_stock + "\n";
	}
	
	
}
