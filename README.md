# ProjectPt3
cpsc 4620 project part 3
Program Requirements:
1. Add a new order to the database: You must be able to add a new order with pizzas to the
database. Use a combination of print statements and the BufferedReader reader object
provided to ask the user of your program for input. Remember, there’s more to adding an order
to the DB than just creating an order and sending it to the database via SQL. You need to create
all the pizzas that go in the order, update topping inventories as necessary, checking to make
sure a topping inventory never goes negative, and apply discounts to pizzas and orders. This
means you’ll be updating more than just the order table for this operation. Also, you can assume
that a topping can only be added to a pizza 1 time.
2. View customers: This option will display each customer and their associated information. The
customer information must be ordered by last name, first name and phone number. Most of
the functionality for this will be done in the DBNinja file and you will just call the
appropriate method(s) from the Menu class.
3. Enter a new customer: The program must be able to add the information for a new customer
and store it in the database. Be sure you prompt the user for all the necessary information
(phone numbers should be entered as ##########, 10-digits with no punctuation).
4. View orders: The program must be able to display orders sorted by order date/time from most
recent to oldest. The program must display:
a. all open orders
b. all completed orders
c. all orders (open and completed) since a specific date (inclusive)
5. View order details: This option is part of the “View orders” option defined in #4. After displaying
the list of orders as specified in “View orders” above, the program must allow the user to select a
specific order for viewing its details. The details include the full order type information, the pizza
information (including pizza discounts), and the order discounts.
6. Mark an order as completed: Once the kitchen has finished prepping an order, they need to be
able to mark it as completed. The program must display the open orders sorted by order
date/time and allow the user to select the order to “complete.”
7. View Inventory Levels: This option will display each topping and its current inventory level.
The toppings must be displayed sorted in alphabetical order.
8. Add Inventory: When the inventory level of an item runs low, the restaurant will restock that
item. When they do so, they need to increase the inventory by a specified amount. The program
needs to display the list of toppings and allow the user to select a topping and then specify how
many units to add. Note: this is not creating a new topping, just updating the inventory level of an
existing topping. Make sure that the inventory list is sorted in alphabetical order when displayed.
9. View Reports: The program must be able to select and display each of the 3 profitability reports
using the views created in Part 2. Specifically
(a) ToppingPopularity
(b) ProfitByPizza
(c) ProfitByOrderType
10. Modify the package DBConnector to contain your database connection information, this is
the same information you use to connect to the database via MySQL Workbench. You will use
DBNinja.connect_to_db method to open a connection to the database. Be aware of
how many open database connections you make and make sure the database is properly closed
when the application exits! The grading will NOT be done using your database. A new database
will be created using your SQL scripts and a new DBConnector class generated to connect to
that database. THEREFORE, the scripts you submit with your code must match what the
application is expecting! Otherwise, your application is guaranteed to fail!!
11. Your code needs to be secure, so any time you are adding any sort of parameter to a query that
is a String, you need to use PreparedStatements to prevent against SQL injections
attacks. If your query does not involve any parameters, or if your queries parameters are not
coming from a String variable, then you can use a regular Statement instead.
12. There are several helper methods in DBNinja with names like “get...” and “find...”, all of these
methods MUST be implemented, even if you choose not to use them. Basically, if it’s in
DBNinja, then you need to implement the method.
13. Watch the Pizza Application video in Canvas. Menu.java has all the user prompts you need to
match the output in the video. You must make sure your input/output matches the video.
Adding, removing or reordering the expected input/output will guarantee the autograder will
fail! Remember to use the BufferedReader object created in the main class (Menu) for all
your input operations.
14. Regarding Java: Use Java version 8 and do NOT remove the package cpsc4620 from your
code, all of your code must reside in the cpsc4620 package.
