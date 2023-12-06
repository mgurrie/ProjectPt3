package cpsc4620;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

		// order table
		String insertOrder =
		 "INSERT INTO Pizzeria.order (OrderType, OrderCompletion, OrderDate, OrderBusinessCost, OrderCustPrice, CustomerID) " + 
		 "VALUES (?, ?, ?, ?, ?, ?)";
 
		try (PreparedStatement ps = conn.prepareStatement(insertOrder)) {
		System.out.println("Insert test: ");
	
		ps.setString(1, o.getOrderType());
		ps.setInt(2, o.getIsComplete());
		ps.setDate(3, java.sql.Date.valueOf(o.getDate()));
		ps.setDouble(4, o.getBusPrice());
		ps.setDouble(5, o.getCustPrice());
		ps.setDouble(6, o.getCustID());
		ps.executeUpdate();
	
		System.out.println("success order\n");
		System.out.println();
	
		} catch (SQLException e) {
		System.out.println(e);
		}

		String insertOrderType;
		// update dine in table
		if(Objects.equals(o.getOrderType(), "dinein")) {
			insertOrderType =
			"INSERT INTO Pizzeria.dinein (OrderNum, TableNum) " + 
			"VALUES (?, ?)";

			try (PreparedStatement ps = conn.prepareStatement(insertOrderType)) {
			System.out.println(insertOrderType);

			ps.setInt(1, o.getOrderID());
			ps.executeUpdate();

			System.out.println(Objects.equals(o.getOrderType(), "dinein"));

			} catch (SQLException e) {
			System.out.println(e);
			}
		}
		// update pickup table
		if(Objects.equals(o.getOrderType(), "pickup")) {
			insertOrderType =
			"INSERT INTO Pizzeria.pickup (OrderNum, CustomerID) " + 
			"VALUES (?, ?)";

			try (PreparedStatement ps = conn.prepareStatement(insertOrderType)) {
			System.out.println(insertOrderType);

			ps.setInt(1, o.getOrderID());
			ps.setInt(2, o.getCustID());
			ps.executeUpdate();

			System.out.println(Objects.equals(o.getOrderType(), "pickup"));

			} catch (SQLException e) {
			System.out.println(e);
			}
		}
		// update delivery table
		if(Objects.equals(o.getOrderType(), "delivery")) {
			insertOrderType =
			"INSERT INTO Pizzeria.delivery (OrderNum, CustomerID) " + 
			"VALUES (?, ?)";

			try (PreparedStatement ps = conn.prepareStatement(insertOrderType)) {
			System.out.println(insertOrderType);

			ps.setInt(1, o.getOrderID());
			ps.setInt(2, o.getCustID());
			ps.executeUpdate();

			System.out.println(Objects.equals(o.getOrderType(), "delivery"));

			} catch (SQLException e) {
			System.out.println(e);
			}
		}

		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
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
		
		String insertPizza =
		 "INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, PizzaDate, OrderNum) " + 
		 "VALUES (?, ?, ?, ?, ?, ?, ?)";
 
		try (PreparedStatement ps = conn.prepareStatement(insertPizza)) {
		System.out.println("Insert test: ");

		// use pizza discount
	
		ps.setString(1, p.getSize());
		ps.setString(2, p.getPizzaState());
		ps.setDouble(3, p.getCustPrice());
		ps.setDouble(4, p.getBusPrice());
		ps.setString(5, p.getCrustType());
		ps.setString(6, p.getPizzaDate());
		ps.setInt(7, p.getOrderID());
		ps.executeUpdate();
	
		System.out.println(p.toString());
		System.out.println();
	
		} catch (SQLException e) {
		System.out.println(e);
		}
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
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

		// store values for inventory change amt
		double toppingToRemove = 0;
		String pizzaSize = p.getSize();
		if(pizzaSize.toLowerCase().equals("small"))
			toppingToRemove = t.getPerAMT();
		else if (pizzaSize.toLowerCase().equals("medium"))
			toppingToRemove = t.getMedAMT();
		else if (pizzaSize.toLowerCase().equals("large"))
			toppingToRemove = t.getLgAMT();
		else if (pizzaSize.toLowerCase().equals("xlarge"))
			toppingToRemove = t.getXLAMT();
		//add extra topping amount
		int extraTopping = 0;
		if(isDoubled) {
			extraTopping = 1;
			toppingToRemove = toppingToRemove * 2;
		}


		// get current topping inv
		double prevInv = 0;
		String getToppingInv = "SELECT ToppingInv FROM Pizzeria.topping WHERE ToppingKey = (?)";
		try (PreparedStatement ps = conn.prepareStatement(getToppingInv)) {
			System.out.println("Get ToppingInv test: ");
		
			ps.setInt(1, t.getTopID());
			
			try (ResultSet rs = ps.executeQuery()) {
                // Check if there are results
                if (rs.next()) {
                    // Retrieve the value from the result set
                    prevInv = rs.getDouble("ToppingInv");

                    // Use the retrieved value
                    System.out.println("Retrieved value: " + prevInv);
                } else {
                    System.out.println("No matching records found.");
                }
            }
		
			System.out.println("complete");
			System.out.println();
	
		} catch (SQLException e) {
			System.out.println(e);
		}
		

		// update topping inventory from topping t based on pizza size from pizza p
		String update =
		 "UPDATE Pizzeria.topping " + 
		 "SET ToppingInv = (?) - (?)" +
		 "WHERE ToppingKey = (?)";
 
		try (PreparedStatement ps = conn.prepareStatement(update)) {
			System.out.println("update inv test: ");
		
			ps.setDouble(1, prevInv);
			ps.setDouble(2, toppingToRemove);
			ps.setInt(3, t.getTopID());
			ps.executeUpdate();
		
			System.out.println(t.toString());
			System.out.println();
	
		} catch (SQLException e) {
			System.out.println(e);
		}

		
		// insert topping_pizza table withj pizzaID and toppingKey
		String insertToppingPizza =
         "INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra) " + 
         "VALUES (?, ?, ?)";
 
        try (PreparedStatement ps = conn.prepareStatement(insertToppingPizza)) {
			System.out.println("Insert toppingpizza test: ");
		
			ps.setInt(1, t.getTopID());
			ps.setInt(2, p.getPizzaID());
			ps.setInt(3, extraTopping);
			ps.executeUpdate();
		
			System.out.println("complete toppingpizza " + extraTopping);
			System.out.println();
    
        } catch (SQLException e) {
        	System.out.println(e);
        }
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
	}
	
	
	public static void usePizzaDiscount(Pizza p, Discount d) throws SQLException, IOException
	{
		connect_to_db();
		/*
		 * This method connects a discount with a Pizza in the database.
		 * 
		 * What that means will be specific to your implementatinon.
		 */
		
		// insert pizza and discount into pizza_discount bridge table
		String insertPizzaDiscount =
         "INSERT INTO Pizzeria.discount_pizza (DiscountID, PizzaID) " + 
         "VALUES (?, ?)";
 
        try (PreparedStatement ps = conn.prepareStatement(insertPizzaDiscount)) {
            System.out.println("Insert discounpizza test: ");
        
            ps.setInt(1, d.getDiscountID());
            ps.setInt(2, p.getPizzaID());
            ps.executeUpdate();
        
            System.out.println("complete discountpizza");
            System.out.println();
    
        } catch (SQLException e) {
            System.out.println(e);
        }

		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
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
		
		String insertOrderDiscount =
         "INSERT INTO Pizzeria.discount_order (DiscountID, OrderNum) " + 
         "VALUES (?, ?)";
 
        try (PreparedStatement ps = conn.prepareStatement(insertOrderDiscount)) {
            System.out.println("Insert discountorder test: ");
        
            ps.setInt(1, d.getDiscountID());
            ps.setInt(2, o.getOrderID());
            ps.executeUpdate();
        
            System.out.println("complete discountorder");
            System.out.println();
    
        } catch (SQLException e) {
            System.out.println(e);
        }

		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
	}
	
	public static void addCustomer(Customer c) throws SQLException, IOException {
		connect_to_db();
		/*
		 * This method adds a new customer to the database.
		 * 
		 */
				
		String insertCustomer =
		 "INSERT INTO Pizzeria.customer (CustomerFName, CustomerLName, CustomerPhone, CustomerAddr) " + 
		 "VALUES (?, ?, ?, ?)";
 
		try (PreparedStatement ps = conn.prepareStatement(insertCustomer)) {
		System.out.println("Insert test: ");
	
		ps.setString(1, c.getFName());
		ps.setString(2, c.getLName());
		ps.setString(3, c.getPhone());
		ps.setString(4, c.getAddress());
		ps.executeUpdate();
	
		System.out.println(c.toString());
		System.out.println();
	
		} catch (SQLException e) {
		System.out.println(e);
		}
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
	}

	public static void completeOrder(Order o) throws SQLException, IOException {
		connect_to_db();
		/*
		 * Find the specifed order in the database and mark that order as complete in the database.
		 * 
		 */

		String updateOrderComplete =
		 "UPDATE Pizzeria.order " + 
		 "SET OrderCompletion = (?) " +
		 "WHERE OrderNum = (?)";
 
		try (PreparedStatement ps = conn.prepareStatement(updateOrderComplete)) {
			System.out.println("Update order completion test: ");
		
			ps.setInt(1, 1);
			ps.setInt(2, o.getOrderID());
			ps.executeUpdate();
		
			System.out.println("complete");
			System.out.println();
	
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
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

			while (returnQ.next()) {
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
		ArrayList<Discount> discount = new ArrayList<>();
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
		ArrayList<Customer> customer = new ArrayList<>();
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
		Customer customerFound;

		try {

			String customer = "Select CustomerFName, CustomerLName FROM customer WHERE CustomerPhone = " + phoneNumber + ";";
			Statement ready = conn.createStatement();
			ResultSet returnQ = ready.executeQuery(customer);

			while (returnQ.next()) {
				customerFound = returnQ.getString(1) + returnQ.getString(2);
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
			ArrayList<Topping> toppings = new ArrayList<>();
			try {
			String topp = "Select ToppingName, ToppingKey FROM topping;";
			PreparedStatement ready = conn.prepareStatement(topp);
			ResultSet returnQ = ready.executeQuery(topp);

			while (returnQ.next()) {
				toppings.add(new Topping(returnQ.getInt(1), returnQ.getString(2), returnQ.getString(3), returnQ.getString(4)));
			}
		} 
		catch (SQLException error) {
			System.out.println("Error getting all toppings");
			while (error != null) {
				System.out.println("Message     : " + error.getMessage());
				error = error.getNextException();
			}
		}

		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
		return toppings;
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

		// get prev topping inv
		double prevInv = 0;

		String getToppingInv = "SELECT ToppingInv FROM Pizzeria.topping WHERE ToppingKey = (?)";
		try (PreparedStatement ps = conn.prepareStatement(getToppingInv)) {
			System.out.println("Get ToppingInv test: ");
		
			ps.setInt(1, t.getTopID());
			
			try (ResultSet rs = ps.executeQuery()) {
                // Check if there are results
                if (rs.next()) {
                    // Retrieve the value from the result set
                    prevInv = rs.getDouble("ToppingInv");

                    // Use the retrieved value
                    System.out.println("Retrieved value: " + prevInv);
                } else {
                    System.out.println("No matching records found.");
                }
            }
		
			System.out.println("complete");
			System.out.println();
	
		} catch (SQLException e) {
			System.out.println(e);
		}

		// update topping
		String updateToppingQty =
		 "UPDATE Pizzeria.topping " + 
		 "SET ToppingInv = (?) + (?) " +
		 "WHERE ToppingKey = (?)";
 
		try (PreparedStatement ps = conn.prepareStatement(updateToppingQty)) {
			System.out.println("Update test: ");
		
			ps.setDouble(1, quantity);
			ps.setDouble(2, prevInv);
			ps.setInt(3, t.getTopID());
			ps.executeUpdate();
		
			System.out.println("complete");
			System.out.println();
	
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
	}
	
	public static double getBaseCustPrice(String size, String crust) throws SQLException, IOException {
		connect_to_db();
		double basePrice = 0.0;
		/* 
		 * Query the database fro the base customer price for that size and crust pizza.
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
			System.out.println("Error getting base price");
			while (error != null) {
				System.out.println("Message     : " + error.getMessage());
				error = error.getNextException();
			}
		}

		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
		System.out.println(basePrice);
		return basePrice;
	}

	public static double getBaseBusPrice(String size, String crust) throws SQLException, IOException {
		connect_to_db();
		double basePrice = 0.0;
		/* 
		 * Query the database fro the base business price for that size and crust pizza.
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

		String selectInv = "SELECT ToppingName, ToppingInv FROM Pizzeria.topping ORDER BY ToppingName ASC";
		try (PreparedStatement ps = conn.prepareStatement(selectInv)) {
			try (ResultSet rs = ps.executeQuery()) {
				// get columns
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getMetaData().getColumnName(i) + "\t");
                }
                System.out.println(); // Move to the next line

                // Print data
                while (rs.next()) {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println(); // Move to the next line
                }
			}
		}
	
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
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

		String selectToppingPopView = "SELECT * FROM ToppingPopularity";
		try (PreparedStatement ps = conn.prepareStatement(selectToppingPopView)) {
			try (ResultSet rs = ps.executeQuery()) {
				// get rows
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getMetaData().getColumnName(i) + "\t");
                }
                System.out.println(); // Move to the next line

                // Print data
                while (rs.next()) {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println(); // Move to the next line
                }
			}
		}
	
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
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
		
		String selectProfitByPizza = "SELECT * FROM ProfitByPizza";
		try (PreparedStatement ps = conn.prepareStatement(selectProfitByPizza)) {
			try (ResultSet rs = ps.executeQuery()) {
				// get rows
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getMetaData().getColumnName(i) + "\t");
                }
                System.out.println(); // Move to the next line

                // Print data
                while (rs.next()) {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println(); // Move to the next line
                }
			}
		}
	
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
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
		
		String selectProfitByOrder = "SELECT * FROM ProfitByOrderType";
		try (PreparedStatement ps = conn.prepareStatement(selectProfitByOrder)) {
			try (ResultSet rs = ps.executeQuery()) {
				// get rows
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getMetaData().getColumnName(i) + "\t");
                }
                System.out.println(); // Move to the next line

                // Print data
                while (rs.next()) {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println(); // Move to the next line
                }
			}
		}
	
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
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

	// JENNY ADDED
	public static void main(String args[]) throws IOException, SQLException{

		// -- test add pizza --
		Pizza p = new Pizza(2, "small", "gluten-free", 1, "ready", "Jul 3, 2022",
			16.55, 10.55);
    	addPizza(p);

		// -- test add customer --
		Customer c = new Customer(1, "fname", "lname", "1234567891");
		c.setAddress("11 cochran rd", "city", "state", "29631");
		addCustomer(c);

		// // -- test add order --
		Order o = new Order(3, 102, "delivery", "2020-10-10", 15.23, 58.36, 0);
		addOrder(o);

		// -- test addToInv --
		/*Topping t = new Topping(2, "Cheese", 3.20, 4.20, 5.20, 6.20,
			5.20, 2.21, 1, 20);
		addToInventory(t, 10);*/

		// test update order completion
		//completeOrder(o);

		// -- test use topping --
		//useTopping(p, t, true);


		// -- test use discount --
		//Discount d = new Discount(1, "employee", 15, true);
		//useOrderDiscount(o, d);

		// -- test print toppings by popularity
		printInventory();

	}

}