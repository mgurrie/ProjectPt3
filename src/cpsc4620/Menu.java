package cpsc4620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
// import textio.TextIO;

/*
 * This file is where the front end magic happens.
 * 
 * You will have to write the methods for each of the menu options.
 * 
 * This file should not need to access your DB at all, it should make calls to the DBNinja that will do all the connections.
 * 
 * You can add and remove methods as you see necessary. But you MUST have all of the menu methods (including exit!)
 * 
 * Simply removing menu methods because you don't know how to implement it will result in a major error penalty (akin to your program crashing)
 * 
 * Speaking of crashing. Your program shouldn't do it. Use exceptions, or if statements, or whatever it is you need to do to keep your program from breaking.
 * 
 */

public class Menu {

	public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws SQLException, IOException {

		System.out.println("Welcome to Pizzas-R-Us!");
		
		int menu_option = 0;

		// present a menu of options and take their selection
		
		PrintMenu();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String option = reader.readLine();
		menu_option = Integer.parseInt(option);

		while (menu_option != 9) {
			switch (menu_option) {
			case 1:// enter order
				EnterOrder();
				break;
			case 2:// view customers
				viewCustomers();
				break;
			case 3:// enter customer
				EnterCustomer();
				break;
			case 4:// view order
				// open/closed/date
				ViewOrders();
				break;
			case 5:// mark order as complete
				MarkOrderAsComplete();
				break;
			case 6:// view inventory levels
				ViewInventoryLevels();
				break;
			case 7:// add to inventory
				AddInventory();
				break;
			case 8:// view reports
				PrintReports();
				break;
			case 10: // TESTER (DELETE AT END)
				autograder_compilation_check();
				break;
			}
			PrintMenu();
			option = reader.readLine();
			menu_option = Integer.parseInt(option);
		}

	}



	// handling table number for dine in orders
	static int tableNum;
	
	public static void setTableNum(int n) {
		tableNum = n;
	}

	public static int getTableNum() {
		return tableNum;
	}


	// allow for a new order to be placed
	public static void EnterOrder() throws SQLException, IOException 
	{

		/*
		 * EnterOrder should do the following:
		 * 
		 * Ask if the order is delivery, pickup, or dinein
		 *   if dine in....ask for table number
		 *   if pickup...
		 *   if delivery...
		 * 
		 * Then, build the pizza(s) for the order (there's a method for this)
		 *  until there are no more pizzas for the order
		 *  add the pizzas to the order
		 *
		 * Apply order discounts as needed (including to the DB)
		 * 
		 * return to menu
		 * 
		 * make sure you use the prompts below in the correct order!
		 */

		int newOrderID = DBNinja.getLastOrder().getOrderID() + 1;
		String userInput;
		int custID = 0;
		int discountID = 0;
		int tableNum = 0; 
		String orderType = "";
		String strDate = ""; 
		double custPrice = 0;
		double busPrice = 0; 
		int iscomplete = 0;

		// ORDER DATE
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		strDate = dateFormat.format(date);

		// ORDER TYPE
		System.out.println("Is this order for: \n1.) Dine-in\n2.) Pick-up\n3.) Delivery\nEnter the number of your choice:");
		userInput = reader.readLine();
		// validate user input
		while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3")){
			System.out.println("ERROR: I don't understand your input for: 'Is this order for: \n1.) Dine-in\n2.) Pick-up\n3.) Delivery\nEnter the number of your choice:'");
			userInput = reader.readLine();
		}
		// CASE: ORDER TYPE
		if (userInput.equals("1")){
			orderType = DBNinja.dine_in;
			System.out.println("What is the table number for this order?");
			tableNum = Integer.parseInt(reader.readLine());
			setTableNum(tableNum);
		}
		if (userInput.equals("2") || userInput.equals("3")) {
			if (userInput.equals("2")) {
				orderType = DBNinja.pickup;
			}
			if (userInput.equals("3")) {
				orderType = DBNinja.delivery;
			}

			// HANDLE CUSTOMER
			System.out.println("Is this order for an existing customer? Answer y/n: ");
			userInput = reader.readLine();

			// if user input is existing customer
			if (userInput.equals("y")) {
				System.out.println("Here's a list of the current customers: ");
				viewCustomers();
				ArrayList <Customer> customerList = DBNinja.getCustomerList();
				
				// get and validate customer number
				boolean custFound = false;
				do {
					System.out.println("Which customer is this order for? Enter ID Number:");
					custID = Integer.parseInt(reader.readLine());
					for (int i = 0; i < customerList.size(); i++) {
						if (custID == customerList.get(i).getCustID()) {
							custFound = true;
							break;
						}
					}
					if(custFound) break;

					System.out.println("ERROR: I don't understand your input for: Is this order an existing customer?");
					userInput = reader.readLine();
				} while(userInput.equals("y"));
			}

			// if user input is NOT an existing customer
			if (userInput.equals("n")) {
				custID = DBNinja.getLastCustomerID() + 1;
				if(orderType.equals(DBNinja.pickup) || orderType.equals(DBNinja.delivery)) {
					EnterCustomer();

					// delivery add address to customer database
					if(orderType.equals(DBNinja.delivery)) {
						String houseNum, street, city, state, zip;
						System.out.println("What is the House/Apt Number for this order? (e.g., 111)");
						houseNum = reader.readLine();
						System.out.println("What is the Street for this order? (e.g., Smile Street)");
						street = reader.readLine();
						System.out.println("What is the City for this order? (e.g., Greenville)");
						city = reader.readLine();
						System.out.println("What is the State for this order? (e.g., SC)");
						state = reader.readLine();
						System.out.println("What is the Zip Code for this order? (e.g., 20605)");
						zip = reader.readLine();
						String addr = houseNum + " " + street + "/n" + city + "/n" + state + "/n" + zip;
						
						DBNinja.updateCustomerAddr(custID, addr);
					}
				}
			}
		}

		
		// ADDING ORDER TO DATABASE
		Order o = new Order(newOrderID, custID, orderType, strDate, custPrice, busPrice, iscomplete);
		// System.out.println(o.toSimplePrint()); //TEST
		DBNinja.addOrder(o);


		// ADDING PIZZAS TO ORDER
		System.out.println("Let's build a pizza!");
		do {
			Pizza newPizza = buildPizza(newOrderID);
			o.addPizza(newPizza);
			DBNinja.addPizza(newPizza);
			System.out.println("Enter -1 to stop adding pizzas...Enter anything else to continue adding pizzas to the order.");
			userInput = reader.readLine();
		} while(!userInput.equals("-1"));
		// ADDING DISCOUNTS TO ORDER
		System.out.println("Do you want to add discounts to this order? Enter y/n?");
		userInput = reader.readLine();
		if(userInput.equals("y")) {
			// print order discounts
			ArrayList <Discount> currDiscountList = DBNinja.getDiscountList();
			for(int i = 0; i < currDiscountList.size(); i++) {
				System.out.println(currDiscountList.get(i).toString());
			}
			// enter a discount
			do {
				System.out.println("Which Order Discount do you want to add? Enter the DiscountID. Enter -1 to stop adding Discounts: ");
				discountID = Integer.parseInt(reader.readLine());
				if(discountID == -1) {
					break;
				}

				Discount thisDiscount = currDiscountList.get(discountID - 1);
				
				// add order discount to bridge table
				DBNinja.useOrderDiscount(o, thisDiscount);
			} while(discountID != -1);
		}
	
		
		System.out.println("Finished adding order...Returning to menu...");
	}
	
	
	public static void viewCustomers() throws SQLException, IOException 
	{
		/*
		 * Simply print out all of the customers from the database. 
		 */
		DBNinja.getCustomerList().forEach(System.out::println); 

	}
	

	// Enter a new customer in the database
	public static void EnterCustomer() throws SQLException, IOException 
	{
		Customer cust;

		System.out.println("What is this customer's name (first <space> last)");
		String names = reader.readLine();
		String [] names_split = names.split(" ");
		String lname = names_split[1];
		String fname = names_split[0];

		System.out.println("What is this customer's phone number (##########) (No dash/space)");
		String phone = reader.readLine();

		int custID = DBNinja.getLastCustomerID() + 1;
		
		cust = new Customer(custID, fname, lname, phone);

		DBNinja.addCustomer(cust);
	}

	// View any orders that are not marked as completed
	public static void ViewOrders() throws SQLException, IOException 
	{
		/*  
		* This method allows the user to select between three different views of the Order history:
		* The program must display:
		* a.	all open orders
		* b.	all completed orders 
		* c.	all the orders (open and completed) since a specific date (inclusive)
		* 
		* After displaying the list of orders (in a condensed format) must allow the user to select a specific order for viewing its details.  
		* The details include the full order type information, the pizza information (including pizza discounts), and the order discounts.
		* 
		*/ 
		int id;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Would you like to:\n(a) display all orders [open or closed]\n(b) display all open orders\n(c) display all completed [closed] orders\n(d) display orders since a specific date");
		String orderOption = reader.readLine();
		if (!orderOption.equals("a") && !orderOption.equals("b") && !orderOption.equals("c") && !orderOption.equals("d")) {
			System.out.println("I don't understand that input, returning to menu");
			System.out.println(orderOption);
			return;
		}
		if (orderOption.equals("a")){
			if (DBNinja.getOrders(false).size() == 0) {
				System.out.println("No orders to display, returning to menu.");
				return;
			}
			DBNinja.getOrders(false).forEach(System.out::println);
			System.out.println("Which order would you like to see in detail? Enter the number (-1 to exit): ");
			String option = reader.readLine();
			id = Integer.parseInt(option);
			if (id == -1){
				return;
			}
			else{
				System.out.println(DBNinja.getOrderbyID(id));
				return;
			}
		}
		if (orderOption.equals("b")){
			if (DBNinja.getOrders(true).size() == 0) {
				System.out.println("No orders to display, returning to menu.");
				return;
			}
			DBNinja.getOrders(true).forEach(System.out::println);
			System.out.println("Which order would you like to see in detail? Enter the number (-1 to exit): ");
			String option = reader.readLine();
			id = Integer.parseInt(option);
			if (id == -1){
				return;
			}
			else{
				System.out.println(DBNinja.getOrderbyID(id));
				return;
			}
		}

		if (orderOption.equals("c")){
			if (DBNinja.getClosedOrders(true).size() == 0) {
				System.out.println("No orders to display, returning to menu.");
				return;
			}
			DBNinja.getClosedOrders(true).forEach(System.out::println);
			System.out.println("Which order would you like to see in detail? Enter the number (-1 to exit): ");
			String option = reader.readLine();
			id = Integer.parseInt(option);
			if (id == -1){
				return;
			}
			else{
				System.out.println(DBNinja.getOrderbyID(id));
				return;
			}
		}

		if (orderOption.equals("d")){
			
			System.out.println("What is the date you want to restrict by? (FORMAT= YYYY-MM-DD)");
			String option = reader.readLine();
			if (DBNinja.getOrdersByDate(option).size() == 0) {
				System.out.println("No orders to display, returning to menu.");
				return;
			}
			DBNinja.getOrdersByDate(option).forEach(System.out::println);
			System.out.println("Which order would you like to see in detail? Enter the number (-1 to exit): ");
			option = reader.readLine();
			id = Integer.parseInt(option);
			if (id == -1){
				return;
			}
			else{
				System.out.println(DBNinja.getOrderbyID(id));
				return;
			}
		}
		
		// User Input Prompts...
		// System.out.println("Would you like to:\n(a) display all orders [open or closed]\n(b) display all open orders\n(c) display all completed [closed] orders\n(d) display orders since a specific date");
		// System.out.println("What is the date you want to restrict by? (FORMAT= YYYY-MM-DD)");
		// System.out.println("I don't understand that input, returning to menu");
		// System.out.println("Which order would you like to see in detail? Enter the number (-1 to exit): ");
		// System.out.println("Incorrect entry, returning to menu.");
		// System.out.println("No orders to display, returning to menu.");



	}

	
	// When an order is completed, we need to make sure it is marked as complete
	public static void MarkOrderAsComplete() throws SQLException, IOException 
	{
		/*
		 * All orders that are created through java (part 3, not the orders from part 2) should start as incomplete
		 * 
		 * When this method is called, you should print all of the "opoen" orders marked
		 * and allow the user to choose which of the incomplete orders they wish to mark as complete
		 * 
		 */
		int id;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		

		if (DBNinja.getOrders(true) == null ) {
			System.out.println("There are no open orders currently... returning to menu...");
		}
		else{
			DBNinja.getOrders(true).forEach(System.out::println); 
		}
		System.out.println("Which order would you like mark as complete? Enter the OrderID: ");
		String optionID = reader.readLine();
		id = Integer.parseInt(optionID);
		while (DBNinja.getOrderbyID(id) == null){
			System.out.println("Incorrect entry, not an option");
			System.out.println("Which order would you like mark as complete? Enter the OrderID: ");
			optionID = reader.readLine();
			id = Integer.parseInt(optionID);
		}
		
		DBNinja.completeOrder(DBNinja.getOrderbyID(id));


		// User Input Prompts...
		// System.out.println("There are no open orders currently... returning to menu...");
		// System.out.println("Which order would you like mark as complete? Enter the OrderID: ");
		// System.out.println("Incorrect entry, not an option");

		
		

	}

	public static void ViewInventoryLevels() throws SQLException, IOException 
	{
		/*
		 * Print the inventory. Display the topping ID, name, and current inventory
		*/
		DBNinja.printInventory();
		
		
		
	}


	public static void AddInventory() throws SQLException, IOException 
	{
		/*
		 * This should print the current inventory and then ask the user which topping (by ID) they want to add more to and how much to add
		 */
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int topingOption = 0;
		int addUnits = 0;

		DBNinja.printInventory();
		System.out.println("Which topping do you want to add inventory to? Enter the number: ");
		String option = reader.readLine();
		topingOption = Integer.parseInt(option);
		while (topingOption > 17 || topingOption < 1){
			System.out.println("Incorrect entry, not an option");
			System.out.println("Which topping do you want to add inventory to? Enter the number: ");
			option = reader.readLine();
			topingOption = Integer.parseInt(option);
		}
		System.out.println("How many units would you like to add? ");
		option = reader.readLine();
		addUnits = Integer.parseInt(option);
		Topping t = DBNinja.findToppingByKey(topingOption);
		DBNinja.addToInventory(t, addUnits);		
		
	}

	// A method that builds a pizza. Used in our add new order method
	public static Pizza buildPizza(int orderID) throws SQLException, IOException 
	{
		
		/*
		 * This is a helper method for first menu option.
		 * 
		 * It should ask which size pizza the user wants and the crustType.
		 * 
		 * Once the pizza is created, it should be added to the DB.
		 * 
		 * We also need to add toppings to the pizza. (Which means we not only need to add toppings here, but also our bridge table)
		 * 
		 * We then need to add pizza discounts (again, to here and to the database)
		 * 
		 * Once the discounts are added, we can return the pizza
		 */

		Pizza ret = null;
		
		// User Input Prompts...
		String size = "";
		String crust = "";
		int userInput = 0;

		// BASE PIZZA
		System.out.println("What size is the pizza?");
		System.out.println("1."+DBNinja.size_s);
		System.out.println("2."+DBNinja.size_m);
		System.out.println("3."+DBNinja.size_l);
		System.out.println("4."+DBNinja.size_xl);
		System.out.println("Enter the corresponding number: ");

		// set size
		userInput = Integer.parseInt(reader.readLine());

		switch(userInput) {
			case 1:
				size = DBNinja.size_s.toLowerCase();
				break;
			case 2:
				size = DBNinja.size_m.toLowerCase();
				break;
			case 3:
				size = DBNinja.size_l.toLowerCase();
				break;
			case 4:
				size = DBNinja.size_xl.toLowerCase();
				break;
			default:
				break;
		}

		System.out.println("What crust for this pizza?");
		System.out.println("1."+DBNinja.crust_thin);
		System.out.println("2."+DBNinja.crust_orig);
		System.out.println("3."+DBNinja.crust_pan);
		System.out.println("4."+DBNinja.crust_gf);
		System.out.println("Enter the corresponding number: ");

		// set crust
		userInput = Integer.parseInt(reader.readLine());
		switch(userInput) {
		case 1:
			crust = DBNinja.crust_thin.toLowerCase();
			break;
		case 2:
			crust = DBNinja.crust_orig.toLowerCase();
			break;
		case 3:
			crust = DBNinja.crust_pan.toLowerCase();
			break;
		case 4:
			crust = DBNinja.crust_gf.toLowerCase();
			break;
		default:
			break;
		}

		// getting information from db
		double baseCustPrice = DBNinja.getBaseCustPrice(size, crust);
		double baseBusCost = DBNinja.getBaseBusPrice(size, crust);

		// getting time
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    	Date date = new Date();  
		
		// base pizza information
		//System.out.println("new pizza id = " + DBNinja.getLastPizzaID() + 1);
		ret = new Pizza(DBNinja.getLastPizzaID() + 1, size, crust, orderID, "incomplete", 
			formatter.format(date), baseCustPrice, baseBusCost);
		

		// TOPPINGS
		ArrayList<Topping> currentToppings = DBNinja.getToppingList();
		userInput = 0;
		ArrayList<Topping> thisTopping = new ArrayList<>();
		String extraToppingInput;
		boolean extraToppingBool = false;
		double currInv = 0;
		double newInv = 0;
		double minInv = 0;

		System.out.println("Available Toppings:");
		// print toppings
		for (int i=0; i<currentToppings.size(); i++) {
			Topping current = currentToppings.get(i);
			System.out.println(current.toString());
		}
		
		int i = 0;
		do {
			System.out.println("Which topping do you want to add? Enter the TopID. Enter -1 to stop adding toppings: ");

			// user inputs toppings
			userInput = Integer.parseInt(reader.readLine());

			if(userInput == -1) break;

			// populate min, current and new inventory
			minInv = currentToppings.get(userInput-1).getMinINVT();
			currInv = currentToppings.get(userInput-1).getCurINVT();
			switch(size) {
			case DBNinja.size_s:
				newInv = currentToppings.get(userInput-1).getPerAMT();
				break;
			case DBNinja.size_m:
				newInv = currentToppings.get(userInput-1).getMedAMT();
				break;
			case DBNinja.size_l:
				newInv = currentToppings.get(userInput-1).getLgAMT();
				break;
			case DBNinja.size_xl:
				newInv = currentToppings.get(userInput-1).getXLAMT();
				break;
			default:
				break;
			}

			// check if topping inv is enough
			if(currInv - newInv < minInv) {
				System.out.println("We don't have enough of that topping to add it...");
				continue;
			}

			thisTopping.add(currentToppings.get(userInput-1));
			//System.out.println("enough top. current topping = " + thisTopping.get(i).toString());

			// get extra topping
			System.out.println("Do you want to add extra topping? Enter y/n");
			extraToppingInput = reader.readLine();
			if(extraToppingInput.equals("y")) {
				// check if topping inv is enough
				if((currInv - (2*newInv)) < minInv) {
					System.out.println("We don't have enough of that topping to add it...");
					continue;
				}
				else {
					extraToppingBool = true;
				}
			}
			
			// add topping to pizza in object
			ret.addToppings(thisTopping.get(i), extraToppingBool);

			i++;

		} while(userInput != -1);

		
		
		userInput = 0;



		// DISCOUNTS
		ArrayList<Discount> currentDiscounts = DBNinja.getDiscountList();
		ArrayList <Discount> thisDiscount = new ArrayList<>();

		System.out.println("Do you want to add discounts to this Pizza? Enter y/n?");
		// get user input
		String stringUserInput = reader.readLine();
		if(stringUserInput.equals("y")) {
			int j = 0;
			do {
				System.out.println("Which Pizza Discount do you want to add? Enter the DiscountID. Enter -1 to stop adding Discounts: ");
				// display discounts
				for (int l=0; l<currentDiscounts.size(); l++) {
					Discount current = currentDiscounts.get(l);
					System.out.println(current.toString());
				}
				// get user input
				userInput = Integer.parseInt(reader.readLine());
				if (userInput == -1) break;

				// add discount to pizza object
				thisDiscount.add(currentDiscounts.get(userInput-1));
				ret.addDiscounts(thisDiscount.get(j));
				j++;
		
				System.out.println("Do you want to add more discounts to this Pizza? Enter y/n?");
				stringUserInput = reader.readLine();
				if(stringUserInput.equals("n")) break;

			} while(userInput != -1 | !stringUserInput.equals("y"));
		}


		return ret;
	}
	
	
	public static void PrintReports() throws SQLException, NumberFormatException, IOException
	{
		/*
		 * This method asks the use which report they want to see and calls the DBNinja method to print the appropriate report.
		 * 
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Which report do you wish to print? Enter\n(a) ToppingPopularity\n(b) ProfitByPizza\n(c) ProfitByOrderType:");
		String reportOption = reader.readLine();
		if (!reportOption.equals("a") && !reportOption.equals("b") && !reportOption.equals("c")) {
			System.out.println("I don't understand that input... returning to menu...");
			return;
		}
		if (reportOption.equals("a")){
			DBNinja.printToppingPopReport();
		}
		if (reportOption.equals("b")){
			DBNinja.printProfitByPizzaReport();
		}
		if (reportOption.equals("c")){
			DBNinja.printProfitByOrderType();
		}

		// // User Input Prompts...
		// System.out.println("Which report do you wish to print? Enter\n(a) ToppingPopularity\n(b) ProfitByPizza\n(c) ProfitByOrderType:");
		// System.out.println("I don't understand that input... returning to menu...");
	}

	//Prompt - NO CODE SHOULD TAKE PLACE BELOW THIS LINE
	// DO NOT EDIT ANYTHING BELOW HERE, THIS IS NEEDED TESTING.
	// IF YOU EDIT SOMETHING BELOW, IT BREAKS THE AUTOGRADER WHICH MEANS YOUR GRADE WILL BE A 0 (zero)!!

	public static void PrintMenu() {
		System.out.println("\n\nPlease enter a menu option:");
		System.out.println("1. Enter a new order");
		System.out.println("2. View Customers ");
		System.out.println("3. Enter a new Customer ");
		System.out.println("4. View orders");
		System.out.println("5. Mark an order as completed");
		System.out.println("6. View Inventory Levels");
		System.out.println("7. Add Inventory");
		System.out.println("8. View Reports");
		System.out.println("9. Exit\n\n");
		System.out.println("Enter your option: ");
	}

	/*
	 * autograder controls....do not modiify!
	 */

	public final static String autograder_seed = "6f1b7ea9aac470402d48f7916ea6a010";

	
	private static void autograder_compilation_check() {

		try {
			Order o = (null);
			Pizza p = null;
			Topping t = null;
			Discount d = null;
			Customer c = null;
			ArrayList<Order> alo = null;
			ArrayList<Discount> ald = null;
			ArrayList<Customer> alc = null;
			ArrayList<Topping> alt = null;
			double v = 0.0;
			String s = "";

			DBNinja.addOrder(o);
			DBNinja.addPizza(p);
			DBNinja.useTopping(p, t, false);
			DBNinja.usePizzaDiscount(p, d);
			DBNinja.useOrderDiscount(o, d);
			DBNinja.addCustomer(c);
			DBNinja.completeOrder(o);
			alo = DBNinja.getOrders(false);
			o = DBNinja.getLastOrder();
			alo = DBNinja.getOrdersByDate("01/01/1999");
			ald = DBNinja.getDiscountList();
			d = DBNinja.findDiscountByName("Discount");
			alc = DBNinja.getCustomerList();
			c = DBNinja.findCustomerByPhone("0000000000");
			alt = DBNinja.getToppingList();
			t = DBNinja.findToppingByName("Topping");
			DBNinja.addToInventory(t, 1000.0);
			v = DBNinja.getBaseCustPrice("size", "crust");
			v = DBNinja.getBaseBusPrice("size", "crust");
			DBNinja.printInventory();
			DBNinja.printToppingPopReport();
			DBNinja.printProfitByPizzaReport();
			DBNinja.printProfitByOrderType();
			s = DBNinja.getCustomerName(0);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}


}


