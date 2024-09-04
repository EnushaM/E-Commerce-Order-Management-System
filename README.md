# E-Commerce-Order-Management-System
This Project on the E-Commerce Order Management System focuses on different CRUD Operations performed on Four Different databases: the Product, the customer, the Order, and the Order Items. 
This is a menu-based console project developed using Core Java, Mysql, and JDBC Concepts
Database Schema:- Includes four different databases, 
Database Schema:
•	Product Table: product_id (Primary Key), name, description , price, quantity_in_stock
•	Customer Table: customer_id (Primary Key), name, email, phone_number, address
•	Order Table: order_id (Primary Key),	customer_id (Foreign Key references Customer Table),	order_date,	total_amount,	status (pending/confirmed/shipped/delivered/canceled)
•	OrderItem Table: order_item_id (Primary Key),	order_id (Foreign Key references Order Table),	product_id (Foreign Key references Product Table),	quantity,	price
Functionalities:
1.	Product Management:
a)	Add a new product b)	View product details c)	Update product information d)	Delete a product
2.	Customer Management: a)	Add a new customer b)	View customer details c)	Update customer information d)	Delete a customer
3.	Order Management: a)	Create a new order b)	View order details c)	Update order information d)	Cancel an order
The Project utilizes OOPS concepts such as Abstraction, Inheritance, Encapsulation Interface, Class, and packages to handle effectively, the fields of each database table, and to smoothly pass on the values from one functionality to another functionality, Class and Encapsulation are used to provide and visualization of how would the rows of dataset be.
The Design Pattern of Data Acess Objecta and Service layer is used effectively through interface and abstraction, for the specularity of database operation only inside the separate data files, which the service layer will utilize according to different business logics.
Exception Handling is done by creating custom exceptions for each of the crud operations and done for each database attribute, in addition to, the entity found, not found and mutability exceptions
