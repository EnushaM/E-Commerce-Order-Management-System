package com.cts.service;

import com.cts.exceptions.CustomerSelectionException;
import com.cts.exceptions.OrderItemsDeletionException;
import com.cts.exceptions.OrderItemsSelectionException;
import com.cts.exceptions.OrderItemsUpdationException;
import com.cts.exceptions.OrderNotFoundException;
import com.cts.exceptions.OrderSelectionException;
import com.cts.exceptions.OrderUpdationException;
import com.cts.exceptions.ProductSelectionException;
import com.cts.exceptions.UpdateStockException;

public interface OrderService {

	void addOrder() throws ProductSelectionException, OrderSelectionException, OrderItemsSelectionException, OrderUpdationException, CustomerSelectionException, UpdateStockException;

	void viewOrderById(int order_id) throws OrderSelectionException, OrderNotFoundException;



	void cancelOrder() throws OrderUpdationException, UpdateStockException, OrderSelectionException;

	void viewOrderitems(int order_id) throws OrderSelectionException, OrderItemsSelectionException;

	void viewAllOrders() throws OrderNotFoundException;

	void deleteOrder() throws OrderSelectionException, OrderNotFoundException;

	void updateOrder() throws OrderSelectionException, OrderItemsSelectionException, OrderItemsUpdationException, ProductSelectionException, UpdateStockException, OrderItemsDeletionException;

	void viewOrder() throws OrderItemsSelectionException;

}
