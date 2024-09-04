package com.cts.service;

import com.cts.exceptions.ProductNotFoundException;
import com.cts.exceptions.ProductSelectionException;

public interface ProductService {
	void viewProductBySearch() throws ProductNotFoundException, ProductSelectionException;
	void updateProduct() throws ProductSelectionException, ProductNotFoundException;
	void deleteProduct() throws ProductNotFoundException, ProductSelectionException;
	void viewAllProducts() throws ProductNotFoundException;
	void addProduct();
}
