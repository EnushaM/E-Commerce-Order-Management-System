package com.cts.modules;

public class Order {
	private int order_id;
	private int customer_id;
	private java.sql.Timestamp order_date;
	/*
	Timestamp: Precision can vary depending on the system and format used.
	java.sql.Timestamp: Specifically provides nanosecond precision. Specifically used in Java for JDBC operations with SQL TIMESTAMP data types.
	*/
	private double total_amount;
	private String status;
	/*
	Timestamp Represents an exact moment in time, usually with high precision and often in UTC. It is time zone aware.
	Datetime Represents a date and time combination without time zone information, focusing on the "local" time as entered or viewed.
	*/
	public Order(int order_id, int customer_id, java.sql.Timestamp order_date, double total_amount, String status) {
		super();
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.order_date = order_date;
		this.total_amount = total_amount;
		this.status = status;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public java.sql.Timestamp getOrder_date() {
		return order_date;
	}

	public void setOrder_date(java.sql.Timestamp order_date) {
		this.order_date = order_date;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	@Override
	public String toString() {

		return String.format(
			    "----------------------------------------------------------------------------------------\n" +
			    "| %-20s %-25s %-25s %-20s %-20s |\n" +
			    "| %-20d %-25d %-25s %-20.2f %-20s |\n" +
			    "----------------------------------------------------------------------------------------\n",
			    "Order Id", "Customer Id", "Order Date", "Total Amount", "Status",
			    order_id, customer_id, order_date, total_amount, status
			);

	}
}