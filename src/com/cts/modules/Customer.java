package com.cts.modules;

import java.math.*;

/*
The package modules contains the class modules for each four database in the E-Commerce database 
Each module contains the entities of the database with separate getter, setter methods for each 
entity and module Description function
*/
public class Customer {

	private int Customer_id;
	private String name;
	private String email;
	private long phone;
	private String address;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int Customer_id, String name, String email, long phone, String address) {
		super();
		this.Customer_id = Customer_id;
		this.name = name;
		this.email = email;
		this.phone=phone;
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCustomer_id() {
		return Customer_id;
	}
	public void setCustomer_id(int Customer_id) {
		this.Customer_id = Customer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format(
			    "-----------------------------------------------------------------------------------------\n" +
			    "| %-15s %-20s %-30s %-15s %-40s |\n" +
			    "| %-15d %-20s %-30s %-15s %-40s |\n" +
			    "-----------------------------------------------------------------------------------------\n",
			    "Customer Id", "Name", "E-mail", "Phone", "Address",
			    Customer_id, name, email, phone, address
			);
	}
	
	
}
