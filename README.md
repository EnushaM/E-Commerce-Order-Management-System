# E-Commerce-Order-Management-System
This Project on the E-Commerce Order Management System focuses on different CRUD Operations performed on Four Different databases: the Product, the customer, the Order, and the Order Items. 
This is a menu-based console project developed using Core Java, Mysql, and JDBC Concepts

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

The Design Pattern of Data access Object and Service layer is used effectively through interface and abstraction, for the specularity of database operation only inside the separate data files, which the service layer will utilize according to different business logics.
Exception Handling is done by creating custom exceptions for each of the crud operations and done for each database attribute, in addition to, the entity found, not found, and mutability exceptions

How to Install and Run the Project:-
To be Installed:-

Java Development Kit (JDK) 8 or higher
Download JDK=> Ensure JAVA_HOME is set correctly and java and javac commands are accessible from the command line.
Download MySQL=> Ensure the MySQL server is running, and you have the necessary privileges to create and manage databases.
Integrated Development Environment (IDE)

Recommended: IntelliJ IDEA or Eclipse
Step 1: Clone the Repository
Clone the project repository from GitHub (or wherever your project is hosted) to your local machine.
bash
Copy code
git clone https://github.com/EnushaM/E-Commerce-Order-Management-System.git
cd E-Commerce-Order-Management-System

Step 2: Configure the Database
Create a Database:

Log in to your MySQL server and create a database for the project.
sql
Copy code
CREATE DATABASE ecommerceordermanagementsystem;
Set Up Tables:

Use the provided SQL scripts (usually located in a resources or sql folder) to create the necessary tables and insert initial data.
bash
Copy code
mysql -u your_username -p ecommerceordermanagementsystem < path/to/your/schema.sql
Update Database Configuration:

Update the database connection settings in the project (usually found in a config.properties file or directly in the code).
Example configuration might be found in src/main/resources/config.properties:
properties
Copy code
db.url=jdbc:mysql://localhost:3306/ecommerceordermanagementsystem
db.username=your_username
db.password=your_password
Step 3: Build the Project

Option 1: Manual Compilation
If you're not using Maven, you can compile the Java files manually using javac:

bash
Copy code
javac -d out -sourcepath src src/com/cts/client/main.java
Then, package the application into a JAR:

bash
Copy code
jar cvf mysql-connector-j-8.0.31.jar -C out/ .
Step 4: Run the Application
Once the project is built, you can run it using the following command:

bash
Copy code
java -jar target/mysql-connector-j-8.0.31.jar
Or, if you didn't package it into a JAR, you can run the MainApp class directly:

bash
Copy code
java -cp out com.cts.client.main

Step 5: Using the Application
Follow the prompts in the console to interact with the application. Depending on your implementation, you might be able to:
Manage Products: Add, update, delete, or view products.
Manage Customers: Add, update, delete, or view customer details.
Manage Orders: Create, update, cancel, or view orders.
Troubleshooting
Database Connection Issues: Ensure your MySQL server is running and that the database credentials in the configuration file are correct.
Compilation Errors: Double-check your Java version and classpath settings.


