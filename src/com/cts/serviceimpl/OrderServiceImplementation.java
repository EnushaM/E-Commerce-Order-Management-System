package com.cts.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cts.dao.CustomerDao;
import com.cts.dao.OrderDao;
import com.cts.dao.ProductDao;
import com.cts.daoimpl.CustomerDaoImplementation;
import com.cts.daoimpl.OrderDaoImplementation;
import com.cts.daoimpl.ProductDaoImplementation;
import com.cts.exceptions.CustomerSelectionException;
import com.cts.exceptions.OrderDeletionException;
import com.cts.exceptions.OrderInsertionException;
import com.cts.exceptions.OrderItemsDeletionException;
import com.cts.exceptions.OrderItemsSelectionException;
import com.cts.exceptions.OrderItemsUpdationException;
import com.cts.exceptions.OrderNotFoundException;
import com.cts.exceptions.OrderSelectionException;
import com.cts.exceptions.OrderUpdationException;
import com.cts.exceptions.ProductNotFoundException;
import com.cts.exceptions.ProductSelectionException;
import com.cts.exceptions.UpdateStockException;
import com.cts.modules.Customer;
import com.cts.modules.Order;
import com.cts.modules.OrderItem;
import com.cts.modules.OrderItemCart;
import com.cts.modules.Product;
import com.cts.service.CustomerService;
import com.cts.service.OrderService;
import com.cts.service.ProductService;

public class OrderServiceImplementation implements OrderService {
	private Scanner sc = new Scanner(System.in);
	private CustomerDao customerDao;
	private CustomerService customerService;
	private ProductDao productDao;
	private ProductService productService;
	private OrderDao orderDao;
	private int existingQuantityInStock;
	private double existingPrice;
	private int random;
	private String name;
	private double price;
	private Timestamp existingOrderDate;
	private String existingStatus;
	private Order order;
	private Order existOrder;
	private List<OrderItemCart> orderItemCartsList;
	private Map<Integer, Integer> existingItems;


	public OrderServiceImplementation(Connection con) {
		// TODO Auto-generated constructor stub
		orderDao = new OrderDaoImplementation(con);

		customerDao = new CustomerDaoImplementation(con);
		customerService = new CustomerServiceImplementation(con);

		productDao = new ProductDaoImplementation(con);
		productService = new ProductServiceImplementation(con);
	}

	@Override
	public void viewOrderById(int order_id) {
		try {
			order = orderDao.viewOrderById(order_id);
			if (order != null) {
				System.out.println("View your needed Order's details here:-");
				System.out.println(order);
			}
		} catch (OrderSelectionException e) {
			System.out.println(e.toString());

		} catch (OrderNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}

	}

	@Override
	public void viewOrder() {
		try {
			System.out.print("Enter the Order Id to view:- ");
			int order_id = sc.nextInt();
			sc.nextLine();

			Map<String, Object> orderDetailsMap = orderDao.viewOrder(order_id);
			if (orderDetailsMap != null) {
				System.out.println("*********************** Order Details ***********************");

				for (Map.Entry<String, Object> entry : orderDetailsMap.entrySet()) {
					System.out.println(entry.getKey() + " : " + entry.getValue());
				}

				System.out.println("*********************** Order Details ***********************");

				// Calling the method to display order items
				viewOrderitems(order_id);

			}
		} catch (OrderSelectionException e) {
			System.out.println(e.toString());

		} catch (OrderNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}

	}

	@Override
	public void deleteOrder() {
		try {
			System.out.print("Enter the Order Id of order to delete: ");
			int order_id = sc.nextInt();
			sc.nextLine();
			order = orderDao.viewOrderById(order_id);

			boolean done = orderDao.deleteOrder(order_id);
			if (done) {
				System.out.println("Order deleted successfully.");
				viewOrderById(order_id);
			} 
		} catch (OrderDeletionException e) {
			// TODO: handle exception
			System.out.println(e.toString());


		} catch (OrderSelectionException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (OrderNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}

	}

	@Override
	public void viewAllOrders(){
		try {
			List<Order> ordersList = new ArrayList<>();
			ordersList = orderDao.viewAllOrders();
			if (ordersList != null) {
				System.out.println("These are all the Orders till Date\n");

				for (Order order : ordersList) {
					System.out.println(order);
					System.out.println("-------------------------");
				}
			} 

		} catch (OrderSelectionException e) {
			System.out.println(e.toString());

		}
		// TODO Auto-generated method stub
	}

	@Override
	public void addOrder()  {
		try {
			System.out.print("Customer Id (Enter -1 if you're a new user) : ");
			int customer_id = sc.nextInt();
			sc.nextLine();

			/*
			 * In this system, adding orders is initiated through customer_id, which when
			 * not present, prompts the user to register oneself into the customer database,
			 * assuming that the customer can either first register then add orders or can
			 * register one self in the adding orders section while registering their first
			 * order, customer can put -1 in the id prompt to register as a new one
			 */

			Customer existCustomer = customerDao.existCustomer(customer_id);
			if (existCustomer == null) {
				System.out.println("Customer Id doesn't exist.\nRegistering yourself as new Customer.");
				customerService.addCustomer();
			}
			List<OrderItem> orderItemsList = new ArrayList<>();
			boolean addMoreItems = false;
			do {
				System.out.print("Enter Product Id:- ");
				int product_id = sc.nextInt();
				sc.nextLine();
				Product existProduct = productDao.existProduct(product_id);
				existingPrice = existProduct.getPrice();
				existingQuantityInStock = existProduct.getQuantity_in_stock();

				System.out.print("Enter Quantity. (Quantity must be a positive value):- ");
				int quantity = sc.nextInt();
				sc.nextLine();

				if (quantity <= 0) {
					System.out.print("Invalid Quantity. (Quantity must be a positive value):-");
					continue;
				}

				if (quantity > existingQuantityInStock) {
					System.out.print(
							"Insufficient Stock for Item" + name + ", Available stock is" + existingQuantityInStock);
					continue;
				}

				orderItemsList.add(new OrderItem(product_id, quantity, price));
				System.out.print("Add more Items yes(y/Y) OR no(n/N) ");
			} while (addMoreItems = sc.nextLine().equalsIgnoreCase("y"));
			double total = 0;

			for (OrderItem orderItem : orderItemsList) {
				total += orderItem.getQuantity() * orderItem.getPrice();
			}

			Order order = null;
			order = orderDao.addOrder(customer_id, orderItemsList, total);
			if (order != null) {
				int order_id = order.getOrder_id();
				orderDao.insertOrderItemsFromList__UpdateQuantity_in_stock(order_id, orderItemsList);
				orderDao.updateOrderStatusforOrderId(order_id, "confirmed");
				System.out.println("Order added successfully");
				System.out.println("View your added Order's details here:-");
				System.out.print(order);
				viewOrderitems(order_id);
			}
		} catch (OrderInsertionException e) {
			System.out.println(e.toString());

		} catch (ProductNotFoundException e) {
			System.out.println(e.toString());

		} catch (CustomerSelectionException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (UpdateStockException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (OrderUpdationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}

	}

	@Override
	public void viewOrderitems(int order_id) {
		try {
			orderItemCartsList = orderDao.viewOrderitems(order_id);
		} catch (OrderItemsSelectionException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		if (orderItemCartsList != null) {
			System.out.println("*********************** Order Item Details ******************");
			for (OrderItemCart orderItemCart : orderItemCartsList) {
				System.out.println("Order Item ID: " + orderItemCart.getOrder_item_id() + "\n" + "Product ID: "
						+ orderItemCart.getProduct_id() + "\n" + "Product Name: " + orderItemCart.getName() + "\n"
						+ "Product Price: " + orderItemCart.getPrice() + "\n" + "Product Quantity: "
						+ orderItemCart.getQuantity() + "\n" + "Total Cost: " + orderItemCart.getTotal_price() + "\n");
				if (orderItemCartsList.indexOf(orderItemCart) != orderItemCartsList.size()) {
					System.out.print("----------------------------------------------");
				}
			}
			System.out.println("*********************** End of Order Item Details **************");
		}

	}

	@Override
	public void updateOrder() {
		try {
			System.out.print("Admin (a/A) or Customer (c/C)");
			String person = sc.nextLine().toLowerCase();
			System.out.print("Enter the Order ID: ");
			int order_id = sc.nextInt();
			sc.nextLine();
			/*
			 * checking if the order exist, if order not found, the user gets out of the
			 * function through "return" keyword
			 */
			existOrder = orderDao.existOrder(order_id);
			if (existOrder == null) {
				System.out.print("Sorry, Order Not Found");
				return;
			}
			existingOrderDate = existOrder.getOrder_date();
			existingStatus = existOrder.getStatus();
			switch (person) {
			case "a":
				System.out.print("Admin can only change the status of the Order");
				System.out.print("Status (Shipped or Deliverd): ");
				String status = sc.nextLine();

				if (!status.equalsIgnoreCase(existingStatus)) {
					orderDao.updateOrderStatusforOrderId(order_id, status);
					System.out.print("Order Status have been successfully changed to " + status);
				}
				break;
			case "c":
				/*
				 * if the order is found, it is checked whether the order is shipped or
				 * delivered, in both the conditions, the user can't update the order. it is
				 * considered that the average delivery and shipping time period to be 7 and 4
				 * days from the date of creating order, considering the time period of the
				 * order to be updateable is maximum of 3 days, which can be calculated by
				 * deducing the order date from the current date which is checked only post
				 * checking the shipment and delivery
				 */

				if (existingStatus.equalsIgnoreCase("shipped")) {
					System.out.println("This order is already " + existingStatus + " and cannot be updated.");
					return;
				}
				if (existingStatus.equalsIgnoreCase("delivered")) {
					System.out.println("This order is already " + existingStatus + " and cannot be updated.");
					return;
				}
				// Timestamp dbOrderDate =existingOrderDate;
				/* Convert the given Timestamp to LocalDateTime for smoother process */
				/*
				 * First, the Timestamp is converted to an Instant (a point in time in UTC).
				 * Then, the Instant is converted to a ZonedDateTime by applying the system's
				 * default time zone. Finally, the ZonedDateTime is converted to a LocalDateTime
				 * by stripping away the time zone information.
				 */

				LocalDateTime dbOrderDate = existingOrderDate.toInstant()// to get UTC time
						.atZone(ZoneId.systemDefault()) // to get the default current zone
						.toLocalDateTime();
				LocalDateTime currentDate = LocalDateTime.now();

				/*
				 * Chronounit- part of java.time Calculating the difference in days using
				 * ChronoUnit. ChronoUnit.DAYS is an enumeration in the java.time package that
				 * represents a standard set of date and time units. in ChronoUnit DAYS
				 * specifically refers to the number of days.
				 */

				int daysBetween = (int) ChronoUnit.DAYS.between(dbOrderDate, currentDate);

				if (daysBetween > 3) {
					System.out.print("Sorry, your order is to be shipped in no time. \n "
							+ "You can either cancel the existing order or create a new order");
					return;
				}

				/*
				 * moving to the exact update function, if none of the above conditions satisfy
				 */
				try {
					existingItems = orderDao.selectOrderItem(order_id);
				} catch (OrderSelectionException e) {
					// TODO Auto-generated catch block
					System.out.println(e.toString());
				}
				/*
				 * new item can be added through adding to array list
				 */
				List<OrderItem> newOrderItems = new ArrayList<>();
				boolean addItems = false;
				do {
					System.out.print("Enter the Product Id to update: ");
					int product_id = sc.nextInt();
					sc.nextLine();

					if (!existingItems.containsKey(product_id)) {
						System.out.println("Product ID " + product_id + " is not in the order. Please try again.");
						continue;
					}

					System.out.print("Enter the new quantity (0 to remove): ");
					int quantity = sc.nextInt();
					sc.nextLine();

					if (quantity < 0) {
						System.out.println("Quantity must be a non-negative number. Please try again.");
						continue;
					}

					if (quantity == 0) {
						/*
						 * to remove the product from the cart
						 */
						boolean deleted = orderDao.deleteOrderItem(order_id, product_id);
						if (deleted) {
							System.out.print("Selected Order Item deleted successfully.");
						}

						orderDao.updateProductStock(product_id, existingItems.get(product_id));
						/*
						 * if a product is removed from order cart before shipping or delivery, its
						 * quantity in stock is increased to the currently existing plus removed
						 * product's quantity from the cart
						 */
					}

					else {
						Product existProductInStock = productDao.existProduct(product_id);
						Product existProductinCart = orderDao.existProductInCart(order_id, product_id);

						if (existProductinCart == null) {
							System.out.println("You've not ordered this product");
							continue;
						}
						int existingStockQuantity = existProductInStock.getQuantity_in_stock();
						double existingStockPrice = existProductInStock.getPrice();

						int existingCartQuantity = existProductinCart.getQuantity_in_stock();
						/*
						 * new quantity needed must me less than already taken quantity plus currently
						 * available quantity in the stock
						 */
						if (quantity > existingStockQuantity + existingCartQuantity) {
							System.out.println("Insufficient stock for Product ID " + product_id + ". Available stock: "
									+ existingStockQuantity + existingCartQuantity);
							continue;
						}

						newOrderItems.add(new OrderItem(product_id, quantity, price));
						orderDao.updateProductStock(product_id, existingItems.get(product_id) - quantity);
						orderDao.updateOrderItem(order_id, product_id, quantity, price);

					}

					System.out.print("Update more items? (yes(y/Y) or no(n/N)): ");
					addItems = sc.nextLine().equalsIgnoreCase("y");
					/*
					 * Checking whether the user needs to update further
					 */
				} while (addItems);
				/*
				 * calculating the total amount of order all again instead of just adding the
				 * new added quantity
				 */
				double newTotalAmount = orderDao.calculateNewTotalAmount(order_id);
				boolean updated = orderDao.updateOrderTotal(order_id, newTotalAmount);
				System.out.println("Order updated successfully.\nYour updated Order details are here\n");
				viewOrderitems(order_id);
				break;
			default:
				System.out.print("Mention whether you are a ADMIN or a CUSTOMER");
				break;
			}
		} catch (OrderUpdationException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		} catch (ProductNotFoundException e) {
			
			System.out.println(e.toString());
		} catch (OrderSelectionException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (OrderNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (UpdateStockException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (OrderItemsUpdationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (OrderItemsDeletionException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}

	}

	@Override
	public void cancelOrder() {
		try {
			System.out.print("Enter the Order Id of the Order to update: ");
			int order_id = sc.nextInt();
			sc.nextLine();

			try {
				existOrder = orderDao.existOrder(order_id);
			} catch (OrderNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
			if (existOrder == null) {
				System.out.print("Sorry, Order Not Found");
				return;
			}
			/*
			 * when an order is cancelled first, status needed to be changed to be viewed by
			 * the admin to stop shipping and delivery second, quantity in stock needs to be
			 * increased
			 */
			boolean rowsAffected = orderDao.updateOrderStatusforOrderId(order_id, "cancelled");

			if (rowsAffected) {
				boolean cancelled = orderDao.cancelOrder(order_id);
				if (cancelled) {
					System.out.println("Order cancelled successfully");
				}
			}

		} catch (OrderUpdationException e) {
			System.out.println(e.toString());

		} catch (OrderSelectionException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (UpdateStockException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}

	}
	/*
	 * when the order is cancelled, its status is labelled as cancelled, but the
	 * order row in table is not deleted for account management of admin under
	 * "cancelled orders", but can be delted post a long time through Thread.sleep()
	 * functionality for further enhanced system but the order items row need not be
	 * deleted since it will be required for analysis forcancelled order items nor
	 * flagged by adding new status column which will be make the table look dirty
	 * as there will be multiple individual rows with same order id
	 * 
	 */
}
