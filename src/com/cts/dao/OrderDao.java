package com.cts.dao;

import java.util.List;
import java.util.Map;

import com.cts.exceptions.OrderDeletionException;
import com.cts.exceptions.OrderInsertionException;
import com.cts.exceptions.OrderItemsDeletionException;
import com.cts.exceptions.OrderItemsSelectionException;
import com.cts.exceptions.OrderItemsUpdationException;
import com.cts.exceptions.OrderSelectionException;
import com.cts.exceptions.OrderUpdationException;
import com.cts.exceptions.UpdateStockException;
import com.cts.modules.Order;
import com.cts.modules.OrderItem;
import com.cts.modules.OrderItemCart;
import com.cts.modules.Product;

public interface OrderDao {

	Order addOrder(int customer_id, List<OrderItem> orderItemsList, double total) throws OrderInsertionException;

	boolean updateOrderStatusforOrderId(int order_id, String message) throws OrderUpdationException;

	boolean insertOrderItemsFromList__UpdateQuantity_in_stock(int order_id, List<OrderItem> orderItemsList) throws UpdateStockException;

	List<OrderItemCart> viewOrderitems(int order_id) throws OrderItemsSelectionException;

	Order existOrder(int order_id) throws OrderSelectionException;

	Map<Integer, Integer> selectOrderItem(int order_id) throws OrderSelectionException;

	boolean deleteOrderItem(int order_id, int product_id) throws OrderItemsDeletionException;

	Product existProductInCart(int order_id, int product_id) throws OrderSelectionException;

	void updateOrderItem(int order_id, int product_id, int quantity, double price) throws OrderItemsUpdationException;

	double calculateNewTotalAmount(int order_id) throws OrderSelectionException;

	boolean updateOrderTotal(int order_id, double newTotalAmount) throws OrderUpdationException;

	Order viewOrderById(int order_id) throws OrderSelectionException;

	Map<String, Object> viewOrder(int order_id) throws OrderSelectionException;

	void updateProductStock(int product_id, int quantityToBeAdded) throws UpdateStockException;

	boolean cancelOrder(int order_id) throws OrderUpdationException, UpdateStockException;

	List<Order> viewAllProducts() throws OrderSelectionException;

	boolean deleteOrder(int order_id) throws OrderDeletionException;

	int updateOrder(int order_id, String existingName, String existingDescription, int existingQuantityInStock,
			double existingPrice, String name, String description, int quantityInStock, double price) throws OrderUpdationException;

	List<Order> viewAllOrders() throws OrderSelectionException;


}
