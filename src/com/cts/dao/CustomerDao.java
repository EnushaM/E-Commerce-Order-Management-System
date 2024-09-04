package com.cts.dao;

import java.util.List;

import com.cts.exceptions.CustomerDeletionException;
import com.cts.exceptions.CustomerInsertionException;
import com.cts.exceptions.CustomerNotFoundException;
import com.cts.exceptions.CustomerSelectionException;
import com.cts.exceptions.CustomerUpdationException;
import com.cts.modules.Customer;
import com.cts.modules.Product;

public interface CustomerDao {
	Customer addCustomer(String name, String email, String phone, String address) throws CustomerInsertionException;

	Customer viewCustomerById(int customer_id) throws CustomerSelectionException, CustomerNotFoundException;

	List<Customer> viewCustomerbyName(String name) throws CustomerSelectionException, CustomerNotFoundException;

	List<Customer> viewAllCustomers() throws CustomerSelectionException;

	boolean deleteCustomer(int customer_id) throws CustomerDeletionException;

	int updateCustomer(int customer_id, String existingName, String existingPhone, String existingEmail,
			String existingAddress, String name, String phone, String email, String address) throws CustomerUpdationException;

	Customer existCustomer(int customer_id) throws CustomerSelectionException;
	

}
