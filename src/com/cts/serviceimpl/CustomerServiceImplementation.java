package com.cts.serviceimpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cts.dao.CustomerDao;
import com.cts.daoimpl.CustomerDaoImplementation;
import com.cts.daoimpl.OrderDaoImplementation;
import com.cts.daoimpl.ProductDaoImplementation;
import com.cts.exceptions.CustomerDeletionException;
import com.cts.exceptions.CustomerInsertionException;
import com.cts.exceptions.CustomerNotFoundException;
import com.cts.exceptions.CustomerSelectionException;
import com.cts.exceptions.CustomerUpdationException;
import com.cts.modules.Customer;
import com.cts.service.CustomerService;

public class CustomerServiceImplementation implements CustomerService {
	private Scanner sc = new Scanner(System.in);
	private CustomerDao customerDao;
	int random;
	private String name;
	private String phone;
	private String email;
	private String address;
	private String existingName;
	private String existingPhone;
	private String existingEmail;
	private String existingAddress;
	private Customer customer;

	public CustomerServiceImplementation(Connection con) {

		customerDao = new CustomerDaoImplementation(con);
	}

	@Override
	public void viewCustomerById(int customer_id) throws CustomerNotFoundException {
		try {
			Customer customer = null;
			customer = customerDao.viewCustomerById(customer_id);
			if (customer != null) {
				System.out.println(customer);
			} else {
				System.out.println("Sorry, No Customers Found with the specified ID, Please search for any other customer");
			}
		}
		catch (CustomerSelectionException e) {
			// TODO: handle exception
			System.out.println(
					"Sorry, Failed to retrieve the customers in the database. Please Try again after some time.");
			e.printStackTrace();
		}
		
	}

	@Override
	public void viewCustomerBySearch() throws CustomerNotFoundException {
		try {
			System.out.println("Enter by How you want to search the customer");
			System.out.print("1. By Customer's ID \n" + "2. By Customer's Name \n");
			int search_by = sc.nextInt();
			sc.nextLine();
			switch (search_by) {
			case 1:
				System.out.print("Enter the needed Customer's ID: ");
				int customer_id = sc.nextInt();
				sc.nextLine();
				System.out.println("View your needed Customer's details here:-");

				viewCustomerById(customer_id);
				break;
			case 2:
				System.out.print("Enter the needed Customer's Name: ");
				String name = sc.nextLine();
				List<Customer> customersList = null;
				customersList = customerDao.viewCustomerbyName(name);
				if (customersList != null) {
					System.out.println("View your needed Product's details here:-");
					for (Customer customer : customersList) {
						System.out.print(customer);
						if (customersList.indexOf(customer) != customersList.size()-1) {
							System.out.println("-------------------------");
						}
					}
				} else {
					System.out.println(
							"Sorry, No Customers Found with the specified Name, Please Search for any other customer");
				}
				break;
			default:
				System.out.println("Invalid Option, Choose between Customer ID and Customer Name");
				break;
			}
		}
		catch (CustomerSelectionException e) {
			System.out.println(
					"Sorry, Failed to retrieve the customers in the database. Please Try again after some time.");
			e.printStackTrace();		}
		// TODO Auto-generated method stub
		

	}

	@Override
	public void updateCustomer() throws CustomerSelectionException, CustomerNotFoundException {
		try {
			System.out.print("Enter the Customer Id of customer to update: ");
			int customer_id = sc.nextInt();
			sc.nextLine();
			Customer existCustomer = customerDao.existCustomer(customer_id);
			if (existCustomer != null) {
				existingName = existCustomer.getName();
				existingPhone = existCustomer.getPhone();
				existingEmail = existCustomer.getEmail();
				existingAddress = existCustomer.getAddress();
			}

			System.out.println("Informing you that, Customer Id can't be updated, as it is the unique identifier");
			// Not involving the customer_id since Primary key can't be updated
			System.out.println("You can update the following details:");

			System.out
					.print("Enter the updated Customer's Name (leave blank to keep current Name: " + existingName + "): ");
			String name = sc.nextLine();
			if (name.isEmpty()) {
				name = existingName;
			}
			System.out.print(
					"Enter the updated Customer's Phone (leave blank to keep current Phone: " + existingPhone + "): ");
			String phone = sc.nextLine();
			if (phone.isEmpty()) {
				phone = existingPhone;
			}

			System.out.print(
					"Enter the updated Customer's Email (leave blank to keep current Email: " + existingEmail + "): ");
			String email = sc.nextLine();
			if (email.isEmpty()) {
				email = existingEmail;
			}

			System.out.print("Enter the updated Customer's Address (leave blank to keep current Address: " + existingAddress
					+ "): ");
			String address = sc.nextLine();
			if (address.isEmpty()) {
				address = existingAddress;
			}

			int rowsAffected = customerDao.updateCustomer(customer_id, existingName, existingPhone, existingEmail,
					existingAddress, name, phone, email, address);
			if (rowsAffected > 0) {
				System.out.println("View the updated Customer's details here:-");
				viewCustomerById(customer_id);
			} else {
				throw new CustomerNotFoundException("Sorry, Customer not Found, Search for any other customer");
			}
		}
		catch (CustomerUpdationException e) {
			// TODO: handle exception
			System.out.println("Sorry, there is an Error updating the Customer. Please try again after sometime");
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer() throws CustomerSelectionException, CustomerNotFoundException {
		try {
			System.out.print("Enter the Customer Id of customer to delete: ");
			int customer_id = sc.nextInt();
			sc.nextLine();
			customer = customerDao.viewCustomerById(customer_id);

			boolean done = customerDao.deleteCustomer(customer_id);
			if (done) {
				System.out.println("Customer deleted successfully.");
				System.out.println("View your deleted Customer's details here:-");
				viewCustomerById(customer_id);
			} else {
				throw new CustomerNotFoundException("Sorry, there is no Customer with ID " + customer_id + ". Please try again after sometime");

			}
		}
		catch (CustomerDeletionException e) {
			// TODO: handle exception
			System.out.println("Sorry, Failed to delete the ID, Please try again after some time.");
			e.printStackTrace();
		}
		
	}

	@Override
	public void viewAllCustomers() throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		
		try {
			List<Customer> customersList = new ArrayList<>();
			customersList = customerDao.viewAllCustomers();
			if (customersList != null) {
				for (Customer customer : customersList) {
					System.out.println(customer);
					if (customersList.indexOf(customer) != customersList.size()-1) {
						System.out.println("-------------------------");
					}
				}
			} else {
				throw new CustomerNotFoundException("Sorry, No Customers available now");
			}
		}
		catch (CustomerSelectionException e) {
			// TODO: handle exception
			System.out.println(
					"Sorry, Failed to retrieve the customers in the database. Please Try again after some time.");
			e.printStackTrace();
		}
	}

	@Override
	public void addCustomer() {
		try {
			// TODO Auto-generated method stub
			System.out.print("Enter any Random Number: ");
			random = sc.nextInt();
			sc.nextLine();

			// prompting the user for a random number input to avoid inline prompts, it can
			// be a considered similar to capcha

			System.out.print("Enter the Customer Name: ");
			String name = sc.nextLine().trim();

			System.out.print("Enter the Customer Email: ");
			String email = sc.nextLine().trim();

			System.out.print("Enter the Customer Phone Number: ");
			String phone = sc.nextLine().trim();

			System.out.print("Enter the Customer Address: ");
			String address = sc.nextLine().trim();

			Customer customer = null;
			customer = customerDao.addCustomer(name, email, phone, address);
			System.out.println("Customer added successfully");
			System.out.println("View your added Customer's details here:-");
			System.out.print(customer);
		}
		catch (CustomerInsertionException e) {
			// TODO: handle exception

			System.out.println("Sorry, there is an Error adding Customer. Please try again later.");
			e.printStackTrace();
		}
		
	}
}
