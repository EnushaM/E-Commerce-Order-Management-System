package com.cts.service;


public interface OrderService {

	void addOrder() ;
	void viewOrderById(int order_id) ;
	void cancelOrder() ;
	void viewOrderitems(int order_id) ;
	void viewAllOrders() ;
	void deleteOrder();
	void updateOrder();
	void viewOrder();

}
