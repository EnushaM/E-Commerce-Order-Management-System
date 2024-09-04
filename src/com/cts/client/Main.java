package com.cts.client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.cts.exceptions.CustomerNotFoundException;
import com.cts.exceptions.CustomerSelectionException;
import com.cts.exceptions.OrderItemsDeletionException;
import com.cts.exceptions.OrderItemsSelectionException;
import com.cts.exceptions.OrderItemsUpdationException;
import com.cts.exceptions.OrderSelectionException;
import com.cts.exceptions.OrderUpdationException;
import com.cts.exceptions.ProductNotFoundException;
import com.cts.exceptions.ProductSelectionException;
import com.cts.exceptions.UpdateStockException;
import com.cts.service.CustomerService;
import com.cts.service.OrderService;
import com.cts.service.ProductService;
import com.cts.serviceimpl.CustomerServiceImplementation;
import com.cts.serviceimpl.OrderServiceImplementation;
import com.cts.serviceimpl.ProductServiceImplementation;
import com.cts.util.DataBase;

public class Main {

    private static Connection con;
    /*
    private static ProductManager productManager;
    private static CustomerManager customerManager;
    private static OrderManager orderManager;
    */
    private static ProductService productService;
    private static CustomerService customerService;
    private static OrderService orderService;	   
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws ProductSelectionException, ProductNotFoundException, CustomerNotFoundException, CustomerSelectionException, OrderSelectionException, OrderItemsSelectionException, OrderUpdationException, UpdateStockException, OrderItemsUpdationException, OrderItemsDeletionException {
        try {
            con = DataBase.getDatabaseConnection();
            
            /*
             * productManager = new ProductManager(con);
             * customerManager = new CustomerManager(con);
             * orderManager = new OrderManager(con);
             */
            
            
            productService=new ProductServiceImplementation(con);
            customerService=new CustomerServiceImplementation(con);
            orderService=new OrderServiceImplementation(con);
            menuOptions();

        } 
        //we'are not including the catch clause since, we used another try catch block for checking whether the connection is closed
        finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                    System.out.println("Database Connection Closed");
                }
            } catch (SQLException e) {
                System.out.println("Failed to close Database Connection");
                e.printStackTrace();
            }
        }
    }

    public static void menuOptions() throws ProductSelectionException, ProductNotFoundException, CustomerNotFoundException, CustomerSelectionException, OrderSelectionException, OrderItemsSelectionException, OrderUpdationException, UpdateStockException, OrderItemsUpdationException, OrderItemsDeletionException {
        int option;
        do {
            System.out.println("**************** Welcome to E-commerce Order Management System ****************");
            System.out.println("Choose (1) for Product Management");
            System.out.println("Choose (2) for Customer Management");
            System.out.println("Choose (3) for Order Management");
            System.out.println("Choose (4) to Exit the Application");
            System.out.print("And your Option is: ");
            option = sc.nextInt();
            sc.nextLine(); 
            switch (option) {
                case 1:
                	menuProductOptions();
                    break;
                case 2:
                    menuCustomerOptions();
                    break;
                case 3:
                	menuOrderOptions();
                    break;
                case 4:
                    System.out.println("Thank you. We believe that your queries have been done satisfyingly. Come Again");
                    break;
                default:
                    System.out.println("Sorry, Invalid Option, Choose the available option!");
            }
        } while (option != 4);
    }
    
    
	public static void menuProductOptions() throws ProductNotFoundException, ProductSelectionException {
		int option;
		do {
			System.out.println("**************** Welcome to Product Management Menu ****************");
			System.out.println("Below are the Options, Choose the needed");
			System.out.println("Choose (1) to Add a new Product");
			System.out.println("Choose (2) to View the details of an existing Product");
			System.out.println("Choose (3) to Update the details of an existing Product");
			System.out.println("Choose (4) to Delete an existing product");
			System.out.println("Choose (5) to View all the available Products");
			System.out.println("Choose (6) to Exit the Application, if all your queries are done");
			System.out.print("And your Option is: ");
			option = sc.nextInt();
			switch (option) {
			case 1:
				productService.addProduct();
				break;
			case 2:
				productService.viewProductBySearch();
				break;
			case 3:	
				productService.updateProduct();
				break;
			case 4:
				productService.deleteProduct();
				break;
			case 5:
				productService.viewAllProducts();
				break;
			case 6:
				System.out.println("Thank you. We believe that your queries have been done satisfyingly. Come Again");
				break;
			default:
				System.out.println("Sorry, Invalid Option, Choose only from the available option!");
			}
		} while (option != 6);
		
	}
	public static void menuCustomerOptions() throws CustomerNotFoundException, CustomerSelectionException {
		int option;
		// using do while to enter enter the option and to leave the loop, if the option
		// is not in the case list or the chosen option is exit
		do {
			System.out.println("**************** Welcome to customer Management Menu ****************");
			System.out.println("Below are the Options, Choose the needed");
			System.out.println("Choose (1) to Add a new customer");
			System.out.println("Choose (2) to View the details of an existing customer");
			System.out.println("Choose (3) to Update the details of an existing customer");
			System.out.println("Choose (4) to Delete an existing customer");
			System.out.println("Choose (5) to View all the available Customers");
			System.out.println("Choose (6) to Exit the Application, if all your queries are done");
			System.out.print("And your Option is: ");
			option = sc.nextInt();
			switch (option) {
			case 1:
				customerService.addCustomer();
				break;
			case 2:
				customerService.viewCustomerBySearch();
				break;
			case 3:
				customerService.updateCustomer();
				break;
			case 4:
				customerService.deleteCustomer();
				break;
			case 5:
				customerService.viewAllCustomers();
				break;
			case 6:
				System.out.println("Thank you. We believe that your queries have been done satisfyingly. Come Again");
				break;
			default:
				System.out.println("Sorry, Invalid Option, Choose the available option!");
			}
		} while (option != 6);

	}
    public static void menuOrderOptions() throws ProductSelectionException, OrderSelectionException, OrderItemsSelectionException, OrderUpdationException, CustomerSelectionException, UpdateStockException, OrderItemsUpdationException, OrderItemsDeletionException {
		int option;
		do {
			System.out.println("**************** Welcome to the Order Management Menu ****************");
			System.out.println("Below are the Options, Choose the needed");
			System.out.println("Choose (1) to Add a new Order");
			System.out.println("Choose (2) to View the details of an existing Order");
			System.out.println("Choose (3) to Update the details of an existing Order");
			System.out.println("Choose (4) to Cancel an existing Order");
			System.out.println("Choose (5) to View the Order Items of an existing Order");
			System.out.println("Choose (6) to Exit the Application, if all your queries are done");
			System.out.print("And your Option is: ");
			option = sc.nextInt();
			switch (option) {
			case 1:
				orderService.addOrder();
				break;
			case 2:
				orderService.viewOrder();
				break;
			case 3:
				orderService.updateOrder();
				break;
			case 4:
				orderService.cancelOrder();
				break;
			case 5:
				System.out.print("Enter the Order ID: ");
				orderService.viewOrderitems(sc.nextInt());
				break;
			case 6:
				System.out.println("Thank you. We believe that your queries have been done satisfyingly. Come Again");
				break;
			default:
				System.out.println("Sorry, Invalid Option, Choose the available option!");
			}
		} while (option != 6);
		
	}
}
