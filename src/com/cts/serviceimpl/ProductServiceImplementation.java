package com.cts.serviceimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cts.dao.ProductDao;
import com.cts.daoimpl.ProductDaoImplementation;
import com.cts.exceptions.ProductDeletionException;
import com.cts.exceptions.ProductInsertionException;
import com.cts.exceptions.ProductNotFoundException;
import com.cts.exceptions.ProductSelectionException;
import com.cts.exceptions.ProductUpdationException;
import com.cts.modules.Product;
import com.cts.service.ProductService;

public class ProductServiceImplementation implements ProductService {
	private Scanner sc = new Scanner(System.in);
	private ProductDao productDao;
	private Product product;
	private String existingName;
	private String existingDescription;
	private int existingQuantityInStock;
	private double existingPrice;
	private int random;
	private String name;
	private double price;
	private int quantityInStock;
	private String description;
	private Connection con;
	private Product existProduct;

	/*
	 * the connection is declared before itself defining the connection in a
	 * separate constructor in a separate database file to avoid multiple driver
	 * connections to the sql database and multiple nested try catch to be able to
	 * manage the module class under a single connection to database the connection
	 * defined in database file, is provided here as a class field being initialized
	 * through constructor parameter
	 */
	public ProductServiceImplementation(Connection con) {
		productDao = new ProductDaoImplementation(con);
	}

	public void viewProductById(int product_id) {

		try {
			Product product = productDao.viewProductById(product_id);

			System.out.println(product);

		} catch (ProductSelectionException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}

	@Override
	public void viewProductBySearch() {
		// TODO Auto-generated method stub
		System.out.println("Enter by How you want to search the product");
		System.out.print("1. By Product's ID \n" + "2. By Product's Name \n");
		int search_by = sc.nextInt();
		sc.nextLine();
		switch (search_by) {
		case 1:
			System.out.print("Enter the needed Product's ID: ");
			int product_id = sc.nextInt();
			sc.nextLine();
			System.out.println("View your needed Product's details here:-");

			viewProductById(product_id);
			break;
		case 2:
			/*
			 * when searching using product name, admin may get ids of of multiple products,
			 * with same name where the admin can search the required product through
			 * checking manually with other columns values such as brand name which are
			 * unique and not null column values which is not available now ie. brand name
			 * which can be added in enhanced system.
			 */
			System.out.print("Enter the needed Product's Name: ");
			String name = sc.nextLine();
			List<Product> productsList = null;
			try {
				productsList = productDao.viewProductbyName(name);
			} catch (ProductSelectionException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}

			if (productsList != null) {
				for (Product product : productsList) {
					System.out.println("View your needed Product's details here:-");
					System.out.println(product);

					if (productsList.indexOf(product) != productsList.size() - 1) {
						System.out.println("-------------------------");
					}
				}
			}
			break;
		default:
			System.out.println("Invalid Option, Choose between Product ID and Product Name");
			break;
		}

	}

	@Override
	public void updateProduct() {
		// TODO Auto-generated method stub
		try {
			System.out.print("Enter the Product Id of product to update: ");
			int product_id = sc.nextInt();
			sc.nextLine();

			existProduct = productDao.existProduct(product_id);
			existingName = existProduct.getName();
			existingDescription = existProduct.getDescription();
			existingPrice = existProduct.getPrice();
			existingQuantityInStock = existProduct.getQuantity_in_stock();
			System.out.println("Informing you that, Product Id can't be updated, as it is the unique identifier");
			// Not involving the product_id since Primary key can't be updated
			System.out.println("You can update the following details:");

			System.out
					.print("Enter the updated Product Name (leave blank to keep current Name: " + existingName + "): ");
			String name = sc.nextLine();
			if (name.isEmpty()) {
				name = existingName;
			}
			System.out.print("Enter the updated Product Description (leave blank to keep current Description: "
					+ existingDescription + "): ");
			String description = sc.nextLine();
			if (description.isEmpty()) {
				description = existingDescription;
			}

			System.out.print(
					"Enter the updated Product Quantity to be added to Stock (leave blank to keep current Quantity: "
							+ existingQuantityInStock + "): ");
			String quantityInput = sc.nextLine();
			int quantityInStock = existingQuantityInStock;
			if (!quantityInput.isEmpty()) {
				try {
					quantityInStock = Integer.parseInt(quantityInput);
				} catch (NumberFormatException e) {
					System.out.println(e.toString() + "Invalid input for quantity. Keeping current value.");
				}
			}

			System.out.print(
					"Enter the updated Product Price (leave blank to keep current Price: " + existingPrice + "): ");
			String priceInput = sc.nextLine();
			double price = existingPrice;
			if (!priceInput.isEmpty()) {
				try {
					price = Integer.parseInt(priceInput);
				} catch (NumberFormatException e) {
					System.out.println(e.toString() + "Invalid input for quantity. Keeping current value.");
				}
			}
			int rowsAffected = productDao.updateProduct(product_id, existingName, existingDescription,
					existingQuantityInStock, existingPrice, name, description, quantityInStock, price);
			if (rowsAffected > 0) {
				System.out.println("Product updated successfully");
				System.out.println("View the updated Product's details here:-");
				viewProductById(product_id);
			}
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (ProductUpdationException e) {
			System.out.println(e.toString());

		}

	}

	@Override
	public void deleteProduct() {
		try {
			System.out.print("Enter the Product Id of product to delete: ");
			int product_id = sc.nextInt();
			sc.nextLine();

			product = productDao.existProduct(product_id);
			boolean done = productDao.deleteProduct(product_id);
			if (done) {
				System.out.println("Product deleted successfully.");
				System.out.println("The Deleted Product's details are here for you");
				System.out.println(product);
			}
		} catch (ProductNotFoundException e) {
			System.out.println(e.toString());

		} catch (ProductDeletionException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}

	}

	@Override
	public void viewAllProducts() {
		// TODO Auto-generated method stub

		try {
			List<Product> productsList = new ArrayList<>();
			productsList = productDao.viewAllProducts();
			if (productsList != null) {
				System.out.println("These are all the Products available\n");
				for (Product product : productsList) {
					System.out.println(product);
					if (productsList.indexOf(product) != productsList.size() - 1) {
						System.out.println("-------------------------");
					}
				}
			}
		} catch (ProductSelectionException e) {
			System.out.println(e.toString());

		}
	}

	@Override
	public void addProduct() {
		// TODO Auto-generated method stub
		try {
			System.out.print("Enter any Random Number: ");
			random = sc.nextInt();
			sc.nextLine();

			// prompting the user for a random number input to avoid inline prompts, it can
			// be a considered similar to capcha

			System.out.print("Enter the Product Name (Name must be less than 50 charcaters): ");
			name = sc.nextLine().trim();

			System.out.print("Enter the Product Price (Price value must be positive): ");
			price = sc.nextInt();
			sc.nextLine();

			System.out.print("Enter the Product Description: ");
			String description = sc.nextLine().trim();

			System.out.print("Enter the Product Quantity added to Stock: (Quantity value must be positive)");
			int quantity_in_stock = sc.nextInt();
			sc.nextLine();
			product = productDao.addProduct(name, description, price, quantity_in_stock);
			System.out.println("Product added successfully");
			System.out.println("View your added Product's details here:");
			System.out.print(product);
		} catch (ProductInsertionException e) {
			System.out.println(e.toString());

		}

	}
}
