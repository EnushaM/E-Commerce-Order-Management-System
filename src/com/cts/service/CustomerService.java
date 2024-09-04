package com.cts.service;

import com.cts.exceptions.CustomerNotFoundException;
import com.cts.exceptions.CustomerSelectionException;

public interface CustomerService {

	void addCustomer();

	void viewCustomerById(int customer_id) throws CustomerNotFoundException;

	void viewCustomerBySearch() throws CustomerNotFoundException;

	void updateCustomer() throws CustomerSelectionException, CustomerNotFoundException;

	void deleteCustomer() throws CustomerSelectionException, CustomerNotFoundException;

	void viewAllCustomers() throws CustomerNotFoundException;

}
