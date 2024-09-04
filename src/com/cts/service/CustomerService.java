package com.cts.service;


public interface CustomerService {

	void addCustomer();

	void viewCustomerById(int customer_id);

	void viewCustomerBySearch();

	void updateCustomer();

	void deleteCustomer();

	void viewAllCustomers();

}
