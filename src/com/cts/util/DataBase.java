package com.cts.util;
import java.sql.*;
public class DataBase {
	private static final String url = "jdbc:mysql://localhost:3306/ecommerceOrdermanagementSystem";
	private static final String user = "root";
	private static final String password = "system";
	private static Connection con;
	public DataBase() {
		// TODO Auto-generated constructor stub


	}
	public static Connection getDatabaseConnection(){
        //return DriverManager.getConnection(URL, USER, PASSWORD);
        try {
        	con = DriverManager.getConnection(url, user, password);
        	System.out.println("Connected to the database successfully");
        } catch (SQLException e) {
        	System.out.println(e.toString() +"Couldn't Connect to the database. Try again after some time");
        	 System.exit(0);
        	// terminating the application if connection fails
        }
        return con;
    }
}

   




