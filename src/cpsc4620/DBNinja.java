package cpsc4620;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.omg.CORBA.INTERNAL;

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
		//System.out.println("Insert test: ");
	
		ps.setString(1, o.getOrderType());
		ps.setInt(2, o.getIsComplete());
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDate = sdf.parse(o.getDate());
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			ps.setDate(3, sqlDate);
		} catch (ParseException e) {
            // Handle the exception if the string is not in the expected format
            e.printStackTrace();
        }
		ps.setDouble(4, o.getBusPrice());
		ps.setDouble(5, o.getCustPrice());
		if(o.getCustID() != 0) 
			ps.setDouble(6, o.getCustID());
		else
			ps.setNull(6, Types.NULL);
		ps.executeUpdate();
	
		//System.out.println("success order\n");
		//System.out.println();
	
		} catch (SQLException e) {
		System.out.println(e);
		}


		// UPDATING TYPE TABLES
		String insertOrderType;
		// update dine in table
		if(Objects.equals(o.getOrderType(), dine_in)) {
			insertOrderType =
			"INSERT INTO Pizzeria.dinein (OrderNum, TableNum) " + 
			"VALUES (?, ?)";

			try (PreparedStatement ps = conn.prepareStatement(insertOrderType)) {
			System.out.println(insertOrderType);

			ps.setInt(1, o.getOrderID());
			ps.setInt(2, Menu.getTableNum());
			ps.executeUpdate();

			System.out.println(Objects.equals(o.getOrderType(), "dinein"));

			} catch (SQLException e) {
			System.out.println(e);
			}
		}
		// update pickup table
		if(Objects.equals(o.getOrderType(), pickup)) {
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
		if(Objects.equals(o.getOrderType(), delivery )) {
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
			//System.out.println("Insert test: ");
		
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

		// add pizza toppings and discounts
		for (int k = 0; k < p.getToppings().size(); k++)
			useTopping(p, p.getToppings().get(k), p.getIsDoubleArray()[k]);
		for (int m = 0; m < p.getDiscounts().size(); m++) 
			usePizzaDiscount(p, p.getDiscounts().get(m));

		// update order cust prices and bus costs
		updateOrderCosts(p);
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
	}

	public static void updateOrderCosts(Pizza p) throws SQLException, IOException {

		/*
		 * helper function to update costs when adding pizza
		 */


		connect_to_db();
		String update =
         "UPDATE `Pizzeria`.`order` " + 
         "SET OrderCustPrice = (?), OrderBusinessCost = (?) " +
         "WHERE OrderNum = (?)";
        try (PreparedStatement ps = conn.prepareStatement(update)) {
			double newOrderCustPrice = getOrderbyID(p.getOrderID()).getCustPrice() + p.getCustPrice();
			double newOrderBusCost = getOrderbyID(p.getOrderID()).getBusPrice() + p.getBusPrice();
        
            ps.setDouble(1, newOrderCustPrice);
            ps.setDouble(2, newOrderBusCost);
            ps.setInt(3, p.getOrderID());
            ps.executeUpdate();
        
            System.out.println("updated order costs: " + newOrderCustPrice + " " + newOrderBusCost);
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
			//System.out.println("Get ToppingInv test: ");
		
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
		 "SET ToppingInv = (?)" +
		 "WHERE ToppingKey = (?)";
 
		try (PreparedStatement ps = conn.prepareStatement(update)) {
			//System.out.println("update inv test: ");
			//System.out.println(prevInv + " - " + toppingToRemove);
		
			ps.setDouble(1, (prevInv-toppingToRemove));
			ps.setInt(2, t.getTopID());
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
			System.out.println("Insert toppingpizza test: " + p.getPizzaID());
		
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


	public static int getLastPizzaID() throws SQLException, IOException {
		connect_to_db();
		int lastKey = 0;

		try {
			String select = "SELECT MAX(PizzaID) AS lastKey FROM pizza";
			PreparedStatement ready = conn.prepareStatement(select);
			ResultSet rs = ready.executeQuery(select);

			if (rs.next()) {
				lastKey = rs.getInt("lastKey");
				System.out.println("Last Key Number: " + lastKey);
			} else {
				System.out.println("No records in the table");
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

		return lastKey;
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
            //System.out.println("Insert discounpizza test: ");
        
            ps.setInt(1, d.getDiscountID());
            ps.setInt(2, p.getPizzaID());
            ps.executeUpdate();
        
            //System.out.println("complete discountpizza");
            //System.out.println();
    
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
		
		// INSERT INTO DATABASE
		String insertOrderDiscount =
         "INSERT INTO Pizzeria.discount_order (DiscountID, OrderNum) " + 
         "VALUES (?, ?)";
 
        try (PreparedStatement ps = conn.prepareStatement(insertOrderDiscount)) {
            //System.out.println("Insert discountorder test: ");
        
            ps.setInt(1, d.getDiscountID());
            ps.setInt(2, o.getOrderID());
            ps.executeUpdate();
        
            //System.out.println("complete discountorder");
            //System.out.println();
    
        } catch (SQLException e) {
            System.out.println(e);
        }

		// UPDATE ORDER COSTS
		o.addDiscount(d);
		String update =
         "UPDATE Pizzeria.order " + 
         "SET OrderCustPrice = (?)" +
         "WHERE OrderNum = (?)";
 
        try (PreparedStatement ps = conn.prepareStatement(update)) {
            //System.out.println("update inv test: ");
        
            ps.setDouble(1, o.getCustPrice());
            ps.setInt(2, o.getOrderID());
            ps.executeUpdate();
        
           // System.out.println("updated order costs: " +  o.getCustPrice());
          //  System.out.println();
    
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
		//System.out.println("Insert test: ");
	
		ps.setString(1, c.getFName());
		ps.setString(2, c.getLName());
		ps.setString(3, c.getPhone());
		ps.setString(4, c.getAddress());
		ps.executeUpdate();
	
		//System.out.println(c.toString());
		//System.out.println();
	
		} catch (SQLException e) {
		System.out.println(e);
		}
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
	}

	public static void updateCustomerAddr(int custID, String addr) throws SQLException, IOException {
		connect_to_db();
				
		/*
		 *  this method updates ciustomer address by id
		 */

		 String update =
         "UPDATE Pizzeria.customer " + 
         "SET CustomerAddr = (?)" +
         "WHERE CustomerID = (?)";
 
        try (PreparedStatement ps = conn.prepareStatement(update)) {
            //System.out.println("update customer addr test: ");
        
            ps.setString(1, addr);
            ps.setInt(2, custID);
            ps.executeUpdate();
    
        } catch (SQLException e) {
            System.out.println(e);
        }

		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();
	}

	public static int getLastCustomerID() throws SQLException, IOException {
		connect_to_db();
		int lastKey = 0;

		try {
			String select = "SELECT MAX(CustomerID) AS lastKey FROM customer";
			PreparedStatement ready = conn.prepareStatement(select);
			ResultSet rs = ready.executeQuery(select);

			if (rs.next()) {
				lastKey = rs.getInt("lastKey");
				//System.out.println("Last Key Number: " + lastKey);
			} else {
				System.out.println("No records in the table");
			}

		} catch (SQLException error) {
			System.out.println("Error getting customer key");
			while (error != null) {
				System.out.println("Message     : " + error.getMessage());
				error = error.getNextException();
			}
		}

		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		conn.close();

		return lastKey;
	}

	public static void completeOrder(Order o) throws SQLException, IOException {
		connect_to_db();
		/*
		 * Find the specifed order in the database and mark that order as complete in the database.
		 * 
		 */

		String updateOrderCompletion =
		 "UPDATE Pizzeria.order " + 
		 "SET OrderCompletion = (?) " +
		 "WHERE OrderNum = (?)";
 
		try (PreparedStatement ps = conn.prepareStatement(updateOrderCompletion)) {
			//System.out.println("Update order completion test: ");
		
			ps.setInt(1, 1);
			ps.setInt(2, o.getOrderID());
			ps.executeUpdate();
		
			//System.out.println("complete");
			//System.out.println();
	
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
		connect_to_db();
		ArrayList<Order> ordersBy = new ArrayList<>();
		//Needs to be fixed
		String orderDate;
		try {
			if (openOnly){
				orderDate = "SELECT * FROM `order` WHERE OrderCompletion = 0 ORDER BY OrderNum;";
			}
			else {
				orderDate = "SELECT * FROM `order` ORDER BY OrderNum;";
			}
			
			PreparedStatement ready = conn.prepareStatement(orderDate);
			ResultSet returnQ = ready.executeQuery(orderDate);

			while (returnQ.next()) {
				int orderID = returnQ.getInt(1);
				int custID = returnQ.getInt(8);
				String orderType = returnQ.getString(2);
				String datex = returnQ.getString(4);
				double custPrice = returnQ.getDouble(7);
				double busPrice = returnQ.getDouble(6);
				int iscomplete = returnQ.getInt(3);
				
				ordersBy.add(new Order(orderID, custID, orderType, datex, custPrice, busPrice, iscomplete));

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
		return ordersBy;
	}

	public static ArrayList<Order> getClosedOrders(boolean closed) throws SQLException, IOException {
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
		connect_to_db();
		ArrayList<Order> ordersBy = new ArrayList<>();
		//Needs to be fixed
		String orderDate;
		try {
			if (closed){
				orderDate = "SELECT * FROM `order` WHERE OrderCompletion = 1 ORDER BY OrderNum;";
			}
			else {
				orderDate = "SELECT * FROM `order`;";
			}
			
			PreparedStatement ready = conn.prepareStatement(orderDate);
			ResultSet returnQ = ready.executeQuery(orderDate);

			while (returnQ.next()) {
				int orderID = returnQ.getInt(1);
				int custID = returnQ.getInt(8);
				String orderType = returnQ.getString(2);
				String datex = returnQ.getString(4);
				double custPrice = returnQ.getDouble(7);
				double busPrice = returnQ.getDouble(6);
				int iscomplete = returnQ.getInt(3);
				
				ordersBy.add(new Order(orderID, custID, orderType, datex, custPrice, busPrice, iscomplete));

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
		return ordersBy;
	}

	
	public static Order getLastOrder() throws SQLException, IOException {
		/*
		 * Query the database for the LAST order added
		 * then return an Order object for that order.
		 * NOTE...there should ALWAYS be a "last order"!
		 */
		connect_to_db();
		Order lastOrder = null;

		try {

			String order = "SELECT * FROM `order` ORDER BY OrderNum DESC LIMIT 1";
			Statement ready = conn.createStatement();
			ResultSet returnQ = ready.executeQuery(order);

			if (returnQ.next()) {
				lastOrder = new Order(returnQ.getInt(1), returnQ.getInt(8), returnQ.getString(2), returnQ.getString(4), returnQ.getDouble(6),
				returnQ.getDouble(7), returnQ.getInt(3));
			}

		} catch (SQLException error) {
			System.out.println("Error getting customer name");
			while (error != null) {
				System.out.println("Message     : " + error.getMessage());
				error = error.getNextException();
			}
		}

		conn.close();
		return lastOrder;
	}

	public static ArrayList<Order> getOrdersByDate(String date) throws SQLException, IOException {
		/*
		 * Query the database for ALL the orders placed on a specific date
		 * and return a list of those orders.
		 *  
		 */
		connect_to_db();
		ArrayList<Order> ordersByDate = new ArrayList<>();

		try {

			String orderDate = "SELECT * FROM `order` WHERE OrderDate = " + date + " ORDER BY OrderDate;";
			PreparedStatement ready = conn.prepareStatement(orderDate);
			ResultSet returnQ = ready.executeQuery(orderDate);

			while (returnQ.next()) {
				int orderID = returnQ.getInt(1);
				int custID = returnQ.getInt(8);
				String orderType = returnQ.getString(2);
				String datex = returnQ.getString(4);
				double custPrice = returnQ.getDouble(7);
				double busPrice = returnQ.getDouble(6);
				int iscomplete = returnQ.getInt(3);
				
				ordersByDate.add(new Order(orderID, custID, orderType, datex, custPrice, busPrice, iscomplete));

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

	public static Order getOrderbyID(int id) throws SQLException, IOException {
		/*
		 * Query the database for ALL the orders placed on a specific date
		 * and return a list of those orders.
		 *  
		 */
		connect_to_db();
		Order idOrder = null;

		try {

			String order = "SELECT * FROM `order` WHERE OrderNum = " + id + ";";
			Statement ready = conn.createStatement();
			ResultSet returnQ = ready.executeQuery(order);

			if (returnQ.next()) {
				idOrder = new Order(returnQ.getInt(1), returnQ.getInt(8), returnQ.getString(2), returnQ.getString(4), returnQ.getDouble(6),
				returnQ.getDouble(7), returnQ.getInt(3));
			}

		} catch (SQLException error) {
			System.out.println("Error getting order by id");
			while (error != null) {
				System.out.println("Message     : " + error.getMessage());
				error = error.getNextException();
			}
		}

		conn.close();
		return idOrder;
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

			String disc = "Select DiscountID, DiscountName, DiscountDollar, DiscountPct From discount;";
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

	public static Discount findDiscountByName(String name) throws SQLException, IOException {
		/*
		 * Query the database for a discount using it's name.
		 * If found, then return an OrderDiscount object for the discount.
		 * If it's not found....then return null
		 *  
		 */
		connect_to_db();
		Discount discountFound = null;

		try {

		String query = "Select DiscountID From discount WHERE DiscountName=" + name + ";";
			Statement ready = conn.createStatement();
			ResultSet returnQ = ready.executeQuery(query);

			if (returnQ.next()) {
				discountFound = (new Discount(returnQ.getInt(1), returnQ.getString(2), returnQ.getDouble(3), returnQ.getBoolean(4)));
			
			}

		} catch (SQLException error) {
			System.out.println("Error getting customer name");
			while (error != null) {
				System.out.println("Message     : " + error.getMessage());
				error = error.getNextException();
			}
		}

		conn.close();
		return discountFound;
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
		// System.out.println(customer);
		return customer;
	}

	public static Customer findCustomerByPhone(String phoneNumber) throws SQLException, IOException {
		/*
		 * Query the database for a customer using a phone number.
		 * If found, then return a Customer object for the customer.
		 * If it's not found....then return null
		 *  
		 */

		connect_to_db();
		Customer customerFound = null;

		try {

			String customer = "Select CustomerFName, CustomerLName FROM customer WHERE CustomerPhone = " + phoneNumber + ";";
			Statement ready = conn.createStatement();
			ResultSet returnQ = ready.executeQuery(customer);

			if (returnQ.next()) {
				customerFound = (new Customer(returnQ.getInt(1), returnQ.getString(2), returnQ.getString(3), returnQ.getString(4)));
			
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
			String topp = "Select * FROM topping;";
			PreparedStatement ready = conn.prepareStatement(topp);
			ResultSet returnQ = ready.executeQuery(topp);

			while (returnQ.next()) {
				toppings.add(new Topping(returnQ.getInt(1), returnQ.getString(2), returnQ.getDouble(3), returnQ.getDouble(4), returnQ.getDouble(5),
				returnQ.getDouble(6), returnQ.getDouble(7), returnQ.getDouble(8), returnQ.getInt(9), returnQ.getInt(10)));
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

	public static Topping findToppingByName(String name) throws SQLException, IOException {
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
				toppingFound = new Topping(returnQ.getInt(1), returnQ.getString(2), returnQ.getDouble(3), returnQ.getDouble(4), returnQ.getDouble(5),
				returnQ.getDouble(6), returnQ.getDouble(7), returnQ.getDouble(8), returnQ.getInt(9), returnQ.getInt(10));
			
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

	public static Topping findToppingByKey(int key) throws SQLException, IOException {
		/*
		 * Query the database for the topping using it's name.
		 * If found, then return a Topping object for the topping.
		 * If it's not found....then return null
		 *  
		 */
		connect_to_db();
		Topping toppingFound = null;

		try {

			String topping = "Select * FROM topping WHERE ToppingKey = " + key + ";";
			Statement ready = conn.createStatement();
			ResultSet returnQ = ready.executeQuery(topping);

			if (returnQ.next()) {
				toppingFound = new Topping(returnQ.getInt(1), returnQ.getString(2), returnQ.getDouble(3), returnQ.getDouble(4), returnQ.getDouble(5),
				returnQ.getDouble(6), returnQ.getDouble(7), returnQ.getDouble(8), returnQ.getInt(9), returnQ.getInt(10));
			
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

		String selectInv = "SELECT ToppingKey, ToppingName, ToppingInv FROM Pizzeria.topping ORDER BY ToppingKey ASC";
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
		query2 = "Select CustomerFName, CustomerLName From customer WHERE CustomerID=?;";
		os = conn.prepareStatement(query2);
		os.setInt(1, CustID);
		rset2 = os.executeQuery();
		while(rset2.next())
		{
			cname2 = rset2.getString("CustomerFName") + " " + rset2.getString("CustomerLName"); // note the use of field names in the getSting methods
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

		getCustomerList();
		// getBaseCustPrice("Medium", "Pan");
	}

}