package cpsc4620;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cpsc4620.DBConnector;
import cpsc4620.Pizza;

public class TestMain {

  private static Connection connection = null;

  public static void main(String args[]) {
    try {
      connection = DBConnector.make_connection();
      System.out.println("connection made");
    } catch (Exception e) {
      System.out.println(e);
    }
    
    // select data from database
    //printPizzas();

    /* 
    // modify data in the database
    Pizza p = new Pizza(1, "large", "thin", 1, "ready", "Jan 1, 2020",
			10.55, 5.30);
    insertPizza(p);
    printPizzas();

    deletePizza(p);
    printPizzas();

    */

    // disconnect from the database
    try {
      connection.close();
      System.out.println("closed connection!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /* 

  public static void printPizzas() {
    try (Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM Pizzeria.pizza")) {
      Pizza p;

      System.out.println("Pizza list:");
      while (rs.next()) {

        Integer pizzaID = rs.getInt("PizzaID");
        String size = rs.getString("PizzaSize");
        String crustType = rs.getString("PizzaCrust");
        Integer orderID = rs.getInt("OrderNum");
        String pizzaState = rs.getString("PizzaState");
        String pizzaDate = rs.getString("PizzaDate");
        Double custPrice = rs.getDouble("PizzaPrice");
        Double busPrice = rs.getDouble("PizzaCost");
        
        p = new Pizza(pizzaID, size, crustType, orderID, pizzaState, pizzaDate, custPrice, busPrice);

        System.out.println(p.toString());
      }
      System.out.println();
    } catch (SQLException e) {
      System.out.println(e);
    }
  }


  public static void insertPizza(Pizza p) {
    String insertPizza =
        "INSERT INTO Pizzeria.pizza (PizzaID, PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, PizzaDate, OrderNum) " + 
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement ps = connection.prepareStatement(insertPizza)) {
      System.out.println("Insert test: ");

      ps.setInt(1, p.getPizzaID());
      ps.setString(2, p.getSize());
      ps.setString(3, p.getPizzaState());
      ps.setDouble(4, p.getCustPrice());
      ps.setDouble(5, p.getBusPrice());
      ps.setString(6, p.getCrustType());
      ps.setString(7, p.getPizzaDate());
      ps.setInt(8, p.getOrderID());
      ps.executeUpdate();

      System.out.println(p.toString());
      System.out.println();

    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  private static void deletePizza(Pizza p) {
    String deletePizza = "DELETE FROM Pizzeria.pizza " + "WHERE Code = ?";

    try (PreparedStatement ps = connection.prepareStatement(deletePizza)) {
      System.out.println("Delete test: ");

      ps.setInt(1, p.getPizzaID());
      ps.executeUpdate();
      System.out.println(p.toString());

      System.out.println();
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  */

}
