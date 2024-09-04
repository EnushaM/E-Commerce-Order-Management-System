package com.cts.dao;

import java.util.List;

import com.cts.exceptions.ProductDeletionException;
import com.cts.exceptions.ProductInsertionException;
import com.cts.exceptions.ProductNotFoundException;
import com.cts.exceptions.ProductSelectionException;
import com.cts.exceptions.ProductUpdationException;
import com.cts.modules.Customer;
import com.cts.modules.Product;

public interface ProductDao {
	Product addProduct(String name, String description, double price, int quantity_in_stock) throws ProductInsertionException;
	Product viewProductById(int product_id) throws ProductSelectionException, ProductNotFoundException;
	List<Product> viewProductbyName(String name) throws ProductSelectionException ;	
	public List<Product> viewAllProducts() throws ProductSelectionException;
	Product existProduct( int product_id) throws ProductNotFoundException;
	boolean deleteProduct(int product_id) throws ProductDeletionException;
	int updateProduct(int product_id, String existingName, String existingDescription, int existingQuantityInStock,
			double existingPrice, String name, String description, int quantityInStock, double price) throws ProductUpdationException;
}
