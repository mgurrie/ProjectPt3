package cpsc4620;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;

/*
 * This file is where most of your code changes will occur You will write the code to retrieve
 * information from the database, or save information to the database
 * 
 * The class has several hard coded static variables used for the connection, you will need to
 * change those to your connection information
 * 
 * This class also has static string variables for pickup, delivery and dine-in. If your database
 * stores the strings differently (i.e "pick-up" vs "pickup") changing these static variables will
 * ensure that the comparison is checking for the right string in other places in the program. You
 * will also need to use these strings if you store this as boolean fields or an integer.
 * 
 * 
 */

/**
 * A utility class to help add and retrieve information from the database
 */

public final class DBNinja {
	private static Connection conn;

	// Change these variables to however you record dine-in, pick-up and delivery, and sizes and crusts
	public final static String pickup = "pickup";
	public final static String delivery = "delivery";
	public final static String dine_in = "dinein";

	public final static String size_s = "Small";
	public final static String size_m = "Medium";
	public final static String size_l = "Large";
	public final static String size_xl = "XLarge";

	public final static String crust_thin = "Thin";
	public final static String crust_orig = "Original";
	public final static String crust_pan = "Pan";
	public final static String crust_gf = "Gluten-Free";



	
	private static boolean connect_to_db() throws SQLException, IOException {

		try {
			conn = DBConnector.make_connection();
			return true;
		} catch (SQLException e) {
			return false;
		} catch (IOException e) {
			return false;
		}

	}

	
	public static void addOrder(Order o) throws SQLException, IOException 
	{
		connect_to_db();
		/*
		 * add code to add the order to the DB. Remember that we're not just
		 * adding the order to the order DB table, but we're also recording
		 * the necessary data for the delivery, dinein, and pickup tables
		 * 
		 */
	

		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}
	
	public static void addPizza(Pizza p) throws SQLException, IOException
	{
		connect_to_db();
		/*
		 * Add the code needed to insert the pizza into into the database.
		 * Keep in mind adding pizza discounts and toppings associated with the pizza,
		 * there are other methods below that may help with that process.
		 * 
		 */
		
		
		
		
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}
	
	
	public static void useTopping(Pizza p, Topping t, boolean isDoubled) throws SQLException, IOException //this method will update toppings inventory in SQL and add entities to the Pizzatops table. Pass in the p pizza that is using t topping
	{
		connect_to_db();
		/*
		 * This method should do 2 two things.
		 * - update the topping inventory every time we use t topping (accounting for extra toppings as well)
		 * - connect the topping to the pizza
		 *   What that means will be specific to your yimplementatinon.
		 * 
		 * Ideally, you should't let toppings go negative....but this should be dealt with BEFORE calling this method.
		 * 
		 */
		
		
		
		
		
		
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}
	
	
	public static void usePizzaDiscount(Pizza p, Discount d) throws SQLException, IOException
	{
		connect_to_db();
		/*
		 * This method connects a discount with a Pizza in the database.
		 * 
		 * What that means will be specific to your implementatinon.
		 */
		
		
		
		
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}
	
	public static void useOrderDiscount(Order o, Discount d) throws SQLException, IOException
	{
		connect_to_db();
		/*
		 * This method connects a discount with an order in the database
		 * 
		 * You might use this, you might not depending on where / how to want to update
		 * this information in the dabast
		 */
		
		
		
		
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}
	
	public static void addCustomer(Customer c) throws SQLException, IOException {
		connect_to_db();
		/*
		 * This method adds a new customer to the database.
		 * 
		 */
				
		
		
		
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}

	public static void completeOrder(Order o) throws SQLException, IOException {
		connect_to_db();
		/*
		 * Find the specifed order in the database and mark that order as complete in the database.
		 * 
		 */
		


		
		
		
		
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}


	public static ArrayList<Order> getOrders(boolean openOnly) throws SQLException, IOException {
		connect_to_db();
		/*
		 * Return an arraylist of all of the orders.
		 * 	openOnly == true => only return a list of open (ie orders that have not been marked as completed)
		 *           == false => return a list of all the orders in the database
		 * Remember that in Java, we account for supertypes and subtypes
		 * which means that when we create an arrayList of orders, that really
		 * means we have an arrayList of dineinOrders, deliveryOrders, and pickupOrders.
		 * 
		 * Don't forget to order the data coming from the database appropriately.
		 * 
		 */
		ArrayList<Order> ordersByDate = new ArrayList<>();

		try {

			String orderDate = "SELECT * FROM order WHERE OrderDate = " + date + " ORDER BY OrderDate;";
			
			PreparedStatement ready = conn.prepareStatement(orderDate);
			ResultSet returnQ = prep_selInv.executeQuery();
			ResultSet topUnit;

			while (queryReturn.next()) {
				int orderNum = returnQ.getInt(1);
				String orderType = returnQ.getNString(2);
				int orderCompleted = returnQ.getDouble(3);
				Date OrderDate = returnQ.getDouble(4);
				Float orderBuisness = returnQ.getDouble(5);
				Float CustPrice = returnQ.getDouble(6);
				int custID = returnQ.getInt(7);

				ordersByDate.add(new Order(orderNum, orderType, orderCompleted, OrderDate, orderBuisness, CustPrice, custID));

			}

		} catch (SQLException error) {
			System.out.println("Error getting all orders");
			while (error != null) {
				System.out.println("Message     : " + error.getMessage());
				error = error.getNextException();
			}
		}

		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
		return ordersByDate;
	}
	
	public static Order getLastOrder(){
		/*
		 * Query the database for the LAST order added
		 * then return an Order object for that order.
		 * NOTE...there should ALWAYS be a "last order"!
		 */
		




		 return null;
	}

	public static ArrayList<Order> getOrdersByDate(String date){
		/*
		 * Query the database for ALL the orders placed on a specific date
		 * and return a list of those orders.
		 *  
		 */
		ArrayList<Order> ordersByDate = new ArrayList<>();

		try {

			String orderDate = "SELECT * FROM order WHERE OrderDate = " + date + " ORDER BY OrderDate;";
			
			PreparedStatement ready = conn.prepareStatement(orderDate);
			ResultSet returnQ = prep_selInv.executeQuery();
			ResultSet topUnit;

			while (queryReturn.next()) {
				int orderNum = returnQ.getInt(1);
				String orderType = returnQ.getNString(2);
				int orderCompleted = returnQ.getDouble(3);
				Date OrderDate = returnQ.getDouble(4);
				Float orderBuisness = returnQ.getDouble(5);
				Float CustPrice = returnQ.getDouble(6);
				int custID = returnQ.getInt(7);

				ordersByDate.add(new Order(orderNum, orderType, orderCompleted, OrderDate, orderBuisness, CustPrice, custID));

			}

		} catch (SQLException error) {
			System.out.println("Error getting all orders");
			while (error != null) {
				System.out.println("Message     : " + error.getMessage());
				error = error.getNextException();
			}
		}

		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
		return ordersByDate;
	}

		
	public static ArrayList<Discount> getDiscountList() throws SQLException, IOException {
		connect_to_db();
		/* 
		 * Query the database for all the available discounts and 
		 * return them in an arrayList of discounts.
		 * 
		*/
		try {

			String disc = "Select DiscountID, DiscountName, DiscountValue, DiscountIsPercent From discount;";
			Statement ready1 = conn.createStatement();
			PreparedStatement ready = conn.prepareStatement(disc);

			ResultSet returnQ = ready.executeQuery();

			while (returnQ.next()) {
				int id = returnQ.getInt(1);
				String name = returnQ.getString(2);
				double value = returnQ.getDouble(3);
				boolean percent = returnQ.getBoolean(4);

				discount.add(new Discount(id, name, value, percent));
			}
		} catch (SQLException error) {
			System.out.println("Error getting all discount");
			while (error != null) {
				System.out.println("Message     : " + error.getMessage());
				error = error.getNextException();
			}
		}

		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
		return discount;
	}

	public static Discount findDiscountByName(String name){
		/*
		 * Query the database for a discount using it's name.
		 * If found, then return an OrderDiscount object for the discount.
		 * If it's not found....then return null
		 *  
		 */
		Int disID;
		OrderDiscount cname1;
		String query = "Select DiscountID From discount WHERE DiscountName=" + name + ";";
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			disID = rset.getInt(1); 
		}
		String query1 = "Select * From discount_order WHERE DiscountID=" + disID + ";";
		Statement stmt1 = conn.createStatement();
		ResultSet rset1 = stmt.executeQuery(query1);

		while(rset.next())
		{
			cname1 = rset.getInt(1) + rset.getInt(2); 
		}

		conn.close();
		if (cname1 != null){
			return cname1;
		}
		else {
			return null;
		}
	}

	public static ArrayList<Customer> getCustomerList() throws SQLException, IOException {
		connect_to_db();
		/*
		 * Query the data for all the customers and return an arrayList of all the customers. 
		 * Don't forget to order the data coming from the database appropriately.
		 * 
		*/
		/*
		 * return an arrayList of all the customers. These customers should
		 *print in alphabetical order, so account for that as you see fit.
		*/
		try {
			String cust = "Select CustomerID, CustomerFname, CustomerLname, CustomerPhone From customer where CustomerID != 0;";
			PreparedStatement ready = conn.prepareStatement(cust);
			ResultSet returnQ = ready.executeQuery(cust);

			while (returnQ.next()) {
				customer.add(new Customer(returnQ.getInt(1), returnQ.getString(2), returnQ.getString(3), returnQ.getString(4)));
			}
		} 
		catch (SQLException error) {
			System.out.println("Error getting all customer");
			while (error != null) {
				System.out.println("Message     : " + error.getMessage());
				error = error.getNextException();
			}
		}

		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
		return customer;
	}

	public static Customer findCustomerByPhone(String phoneNumber){
		/*
		 * Query the database for a customer using a phone number.
		 * If found, then return a Customer object for the customer.
		 * If it's not found....then return null
		 *  
		 */
		connect_to_db();
		String customerFound = "";

		try {

			String customer = "Select CustomerFName, CustomerLName FROM customer WHERE CustomerPhone = " + phoneNumber + ";";
			Statement ready = conn.createStatement();
			ResultSet returnQ = ready.executeQuery(customer);

			while (returnQ.next()) {
				ret = returnQ.getString(1) + " " + returnQ.getString(2);
			}
		} catch (SQLException error) {
			System.out.println("Error getting customer name");
			while (error != null) {
				System.out.println("Message     : " + error.getMessage());
				error = error.getNextException();
			}
		}

		conn.close();
		return customerFound;
	}


	public static ArrayList<Topping> getToppingList() throws SQLException, IOException {
		connect_to_db();
		/*
		 * Query the database for the aviable toppings and 
		 * return an arrayList of all the available toppings. 
		 * Don't forget to order the data coming from the database appropriately.
		 * 
		 */

				try {
			String topp = "Select ToppingName, ToppingKey FROM topping;";
			PreparedStatement ready = conn.prepareStatement(cust);
			ResultSet returnQ = ready.executeQuery(cust);

			while (returnQ.next()) {
				toppings.add(new Toppings(returnQ.getInt(1), returnQ.getString(2), returnQ.getString(3), returnQ.getString(4)));
			}
		} 
		catch (SQLException error) {
			System.out.println("Error getting all customers");
			while (error != null) {
				System.out.println("Message     : " + error.getMessage());
				error = error.getNextException();
			}
		}

		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
		return customer;
	}

	public static Topping findToppingByName(String name){
		/*
		 * Query the database for the topping using it's name.
		 * If found, then return a Topping object for the topping.
		 * If it's not found....then return null
		 *  
		 */
		connect_to_db();
		Topping toppingFound = null;

		try {

			String topping = "Select * FROM topping WHERE ToppingName = " + name + ";";
			Statement ready = conn.createStatement();
			ResultSet returnQ = ready.executeQuery(topping);

			if (returnQ.next()) {
				toppingFound = new Topping(
						returnQ.getInt("ToppingID")
				);
			}

		} catch (SQLException error) {
			System.out.println("Error getting customer name");
			while (error != null) {
				System.out.println("Message     : " + error.getMessage());
				error = error.getNextException();
			}
		}

		conn.close();
		return toppingFound;
	}

	public static void addToInventory(Topping t, double quantity) throws SQLException, IOException {
		connect_to_db();
		/*
		 * Updates the quantity of the topping in the database by the amount specified.
		 * 
		 * */


		
		
		
		
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}
	
	public static double getBaseCustPrice(String size, String crust) throws SQLException, IOException {
		connect_to_db();
		double basePrice = 0.0;
		/* 
		 * Query the database fro the base customer price for that size and crust pizza.
		 * 
		*/
		
		try {

			String cost = "SELECT BaseCost FROM pizza_base WHERE BaseSize = ? and BaseCrust = ?;";

			PreparedStatement ready = conn.prepareStatement(cost);
			ready.setString(1, size);
			ready.setString(2, crust);

			ResultSet returnQ = ready.executeQuery();

			while (returnQ.next()) {
				basePrice = returnQ.getDouble(1);

			}

		} catch (SQLException error) {
			System.out.println("Error getting base price");
			while (error != null) {
				System.out.println("Message     : " + error.getMessage());
				error = error.getNextException();
			}
		}

		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
		return basePrice;
	}

	public static double getBaseBusPrice(String size, String crust) throws SQLException, IOException {
		connect_to_db();
		/* 
		 * Query the database fro the base business price for that size and crust pizza.
		 * 
		*/
		try {

			String cost = "SELECT BasePrice FROM pizza_base WHERE BaseSize = ? and BaseCrust = ?;";

			PreparedStatement ready = conn.prepareStatement(cost);
			ready.setString(1, size);
			ready.setString(2, crust);

			ResultSet returnQ = ready.executeQuery();

			while (returnQ.next()) {
				basePrice = returnQ.getDouble(1);

			}

		} catch (SQLException error) {
			System.out.println("Error getting base buisness price");
			while (error != null) {
				System.out.println("Message     : " + error.getMessage());
				error = error.getNextException();
			}
		}

		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
		return basePrice;
	}

	public static void printInventory() throws SQLException, IOException {
		connect_to_db();
		/*
		 * Queries the database and prints the current topping list with quantities.
		 *  
		 * The result should be readable and sorted as indicated in the prompt.
		 * 
		 */


		
		
		
		
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION


	}
	
	public static void printToppingPopReport() throws SQLException, IOException
	{
		connect_to_db();
		/*
		 * Prints the ToppingPopularity view. Remember that this view
		 * needs to exist in your DB, so be sure you've run your createViews.sql
		 * files on your testing DB if you haven't already.
		 * 
		 * The result should be readable and sorted as indicated in the prompt.
		 * 
		 */


		
		
		
		
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}
	
	public static void printProfitByPizzaReport() throws SQLException, IOException
	{
		connect_to_db();
		/*
		 * Prints the ProfitByPizza view. Remember that this view
		 * needs to exist in your DB, so be sure you've run your createViews.sql
		 * files on your testing DB if you haven't already.
		 * 
		 * The result should be readable and sorted as indicated in the prompt.
		 * 
		 */
		
		
		
		
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}
	
	public static void printProfitByOrderType() throws SQLException, IOException
	{
		connect_to_db();
		/*
		 * Prints the ProfitByOrderType view. Remember that this view
		 * needs to exist in your DB, so be sure you've run your createViews.sql
		 * files on your testing DB if you haven't already.
		 * 
		 * The result should be readable and sorted as indicated in the prompt.
		 * 
		 */
		
		
		
		
		
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION	
	}
	
	
	
	public static String getCustomerName(int CustID) throws SQLException, IOException
	{
	/*
		 * This is a helper method to fetch and format the name of a customer
		 * based on a customer ID. This is an example of how to interact with 
		 * your database from Java.  It's used in the model solution for this project...so the code works!
		 * 
		 * OF COURSE....this code would only work in your application if the table & field names match!
		 *
		 */

		 connect_to_db();

		/* 
		//  * an example query using a constructed string...
		//  * remember, this style of query construction could be subject to sql injection attacks!
		//  * 
		//  */
		// String cname1 = "";
		// String query = "Select FName, LName From customer WHERE CustID=" + CustID + ";";
		// Statement stmt = conn.createStatement();
		// ResultSet rset = stmt.executeQuery(query);
		
		// while(rset.next())
		// {
		// 	cname1 = rset.getString(1) + " " + rset.getString(2); 
		// }

		/* 
		* an example of the same query using a prepared statement...
		* 
		*/
		String cname2 = "";
		PreparedStatement os;
		ResultSet rset2;
		String query2;
		query2 = "Select FName, LName From customer WHERE CustID=?;";
		os = conn.prepareStatement(query2);
		os.setInt(1, CustID);
		rset2 = os.executeQuery();
		while(rset2.next())
		{
			cname2 = rset2.getString("FName") + " " + rset2.getString("LName"); // note the use of field names in the getSting methods
		}

		conn.close();
		return cname2;
	}

	/*
	 * The next 3 private methods help get the individual components of a SQL datetime object. 
	 * You're welcome to keep them or remove them.
	 */
	private static int getYear(String date)// assumes date format 'YYYY-MM-DD HH:mm:ss'
	{
		return Integer.parseInt(date.substring(0,4));
	}
	private static int getMonth(String date)// assumes date format 'YYYY-MM-DD HH:mm:ss'
	{
		return Integer.parseInt(date.substring(5, 7));
	}
	private static int getDay(String date)// assumes date format 'YYYY-MM-DD HH:mm:ss'
	{
		return Integer.parseInt(date.substring(8, 10));
	}

	public static boolean checkDate(int year, int month, int day, String dateOfOrder)
	{
		if(getYear(dateOfOrder) > year)
			return true;
		else if(getYear(dateOfOrder) < year)
			return false;
		else
		{
			if(getMonth(dateOfOrder) > month)
				return true;
			else if(getMonth(dateOfOrder) < month)
				return false;
			else
			{
				if(getDay(dateOfOrder) >= day)
					return true;
				else
					return false;
			}
		}
	}


}