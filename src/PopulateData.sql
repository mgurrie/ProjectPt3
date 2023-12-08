-- Jennifer and Mary Love
USE Pizzeria;

INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Pepperoni', 1.25, 0.2, 100, 50, 2, 2.75, 3.5, 4.5);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Sausage', 1.25, 0.15, 100, 50, 2.5, 3, 3.5, 4.25);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Ham', 1.5, 0.15, 78, 25, 2, 2.5, 3.25, 4);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Chicken', 1.75, 0.25, 56, 25, 1.5, 2, 2.25, 3);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Green Pepper', 0.5, 0.02, 79, 25, 1, 1.5, 2, 2.5);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Onion', 0.5, 0.02, 85, 25, 1, 1.5, 2, 2.75);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Roma Tomato', 0.75, 0.03, 86, 10, 2, 3, 3.5, 4.5);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Mushrooms', 0.75, 0.1, 52, 50, 1.5, 2, 2.5, 3);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Black Olives', 0.6, 0.1, 39, 25, 0.75, 1, 1.5, 2);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Pineapple', 1, 0.25, 15, 0, 1, 1.25, 1.75, 2);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Jalapenos', 0.5, 0.05, 64, 0 , 0.5, 0.75, 1.25, 1.75);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Banana Peppers', 0.5, 0.05, 36, 0, 0.6, 1, 1.3, 1.75);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Regular Cheese', 0.5, 0.12, 250, 50, 2, 3.5, 5, 7);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Four Cheese Blend', 1, 0.15, 150, 25, 2, 3.5, 5, 7);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Feta Cheese', 1.5, 0.18, 75, 0, 1.75, 3, 4, 5.5);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Goat Cheese', 1.5, 0.2, 54, 0, 1.6, 2.75, 4, 5.5);
INSERT INTO Pizzeria.topping (ToppingName, ToppingCustPrice, ToppingBusCost, ToppingInv, ToppingMinInv, ToppingAmtSmall, ToppingAmtMed, ToppingAmtLarge, ToppingAmtXL)
VALUES ('Bacon', 1.5, 0.25, 89, 0, 1, 1.5, 2, 3);


INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (3,0.5,'Thin', 'Small');
INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (3,0.75,'Original', 'Small');
INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (3.5, 1,'Pan', 'Small');
INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (4,2,'Gluten-Free', 'Small');
INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (5,1, 'Thin','Medium');
INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (5,1.5,'Original', 'Medium');
INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (6, 2.25,'Pan', 'Medium');
INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (6.25,3,'Gluten-Free', 'Medium');
INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (8,1.25, 'Thin','Large');
INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (8,2, 'Original', 'Large');
INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (9,3,'Pan', 'Large');
INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (9.5,4,'Gluten-Free', 'Large');
INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (10,2, 'Thin','XLarge');
INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (10,3, 'Original', 'XLarge');
INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (11.5,4.5,'Pan', 'XLarge');
INSERT INTO Pizzeria.pizza_base (BasePrice, BaseCost, BaseCrust, BaseSize)
VALUES (12.5,6,'Gluten-Free', 'XLarge');


INSERT INTO Pizzeria.discount (DiscountName, DiscountDollar, DiscountPct)
VALUES ('Employee', NULL, 15);
INSERT INTO Pizzeria.discount (DiscountName, DiscountDollar, DiscountPct)
VALUES ('Lunch Special Medium', 1, NULL);
INSERT INTO Pizzeria.discount (DiscountName, DiscountDollar, DiscountPct)
VALUES ('Lunch Special Large', 2, NULL);
INSERT INTO Pizzeria.discount (DiscountName, DiscountDollar, DiscountPct)
VALUES ('Specialty Pizza', 1.5, NULL);
INSERT INTO Pizzeria.discount (DiscountName, DiscountDollar, DiscountPct)
VALUES ('Happy Hour', NULL, 10);
INSERT INTO Pizzeria.discount (DiscountName, DiscountDollar, DiscountPct)
VALUES ('Gameday Special', NULL, 20);


INSERT INTO `Pizzeria`.`customer` (`CustomerID`, `CustomerFName`, `CustomerLName`, `CustomerPhone`, `CustomerAddr`) 
VALUES (2, 'Andrew ', 'Wilkes-Krier', '864-254-5861', '115 Party Blvd, Anderson SC 29621');
INSERT INTO `Pizzeria`.`customer` (`CustomerID`, `CustomerFName`, `CustomerLName`, `CustomerPhone`, `CustomerAddr`) 
VALUES (3, 'Matt', 'Engers', '864-474-9953', NULL);
INSERT INTO `Pizzeria`.`customer` (`CustomerID`, `CustomerFName`, `CustomerLName`, `CustomerPhone`, `CustomerAddr`) 
VALUES (4, 'Frank', 'Turner', '864-232-8944', '6745 Wessex St Anderson SC 29621');
INSERT INTO `Pizzeria`.`customer` (`CustomerID`, `CustomerFName`, `CustomerLName`, `CustomerPhone`, `CustomerAddr`) 
VALUES (5, 'Milo', 'Auckerman', '864-878-5679', '8879 Suburban Home, Anderson, SC 29621');


INSERT INTO Pizzeria.`order` (OrderType, OrderCompletion, OrderTime, OrderDate, OrderBusinessCost, OrderCustPrice, CustomerID)
VALUES ('dinein', 1, '12:03', '2023-03-05', 3.68, 20.75, NULL);
INSERT INTO Pizzeria.`order` (OrderType, OrderCompletion, OrderTime, OrderDate, OrderBusinessCost, OrderCustPrice, CustomerID)
VALUES ('dinein', 1, '12:05', '2023-04-03', 4.63,19.78,	NULL);
INSERT INTO Pizzeria.`order` (OrderType, OrderCompletion, OrderTime, OrderDate, OrderBusinessCost, OrderCustPrice, CustomerID)
VALUES ('pickup', 1, '21:30', '2023-03-03', 19.8, 89.28, 2);
INSERT INTO Pizzeria.`order` (OrderType, OrderCompletion, OrderTime, OrderDate, OrderBusinessCost, OrderCustPrice, CustomerID)
VALUES ('delivery', 1, '19:11', '2023-04-20', 23.62, 86.19, 2);
INSERT INTO Pizzeria.`order` (OrderType, OrderCompletion, OrderTime, OrderDate, OrderBusinessCost, OrderCustPrice, CustomerID)
VALUES ('pickup', 1, '17:30', '2023-03-02', 7.88, 27.45, 3);
INSERT INTO Pizzeria.`order` (OrderType, OrderCompletion, OrderTime, OrderDate, OrderBusinessCost, OrderCustPrice, CustomerID)
VALUES ('delivery', 1, '18:17', '2023-03-02', 4.24, 25.81, 4);
INSERT INTO Pizzeria.`order` (OrderType, OrderCompletion, OrderTime, OrderDate, OrderBusinessCost, OrderCustPrice, CustomerID)
VALUES ('delivery', 1, '20:32', '2023-04-13', 6, 37.25, 5);


INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('large', 1, 20.75, 3.68, 'thin', 1);
INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('medium', 1, 12.85, 3.23, 'pan', 2);
INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('small', 1, 6.93, 1.4, 'original', 2);
INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('large', 1, 14.88, 3.3, 'original', 3);
INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('large', 1, 14.88, 3.3, 'original', 3);
INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('large', 1, 14.88, 3.3, 'original', 3);
INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('large', 1, 14.88, 3.3, 'original', 3);
INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('large', 1, 14.88, 3.3, 'original', 3);
INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('large', 1, 14.88, 3.3, 'original', 3);
INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('xlarge', 1, 27.94, 9.19, 'original', 4);
INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('xlarge', 1, 31.5, 6.25, 'original', 4);
INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('xlarge', 1, 26.75, 8.18, 'original', 4);
INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('xlarge', 1, 27.45, 7.88, 'gluten-free', 5);
INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('large', 1, 25.81, 4.24, 'thin', 6);
INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('large', 1, 18, 2.75, 'thin', 7);
INSERT INTO Pizzeria.pizza (PizzaSize, PizzaState, PizzaPrice, PizzaCost, PizzaCrust, OrderNum)
VALUES ('large', 1, 19.25, 3.25, 'thin', 7);


INSERT INTO `Pizzeria`.`dinein` (`OrderNum`, `TableNum`) VALUES ('1', '21');
INSERT INTO `Pizzeria`.`dinein` (`OrderNum`, `TableNum`) VALUES ('2', '4');


INSERT INTO `Pizzeria`.`delivery` (`OrderNum`, `CustomerID`) VALUES ('4', '2');
INSERT INTO `Pizzeria`.`delivery` (`OrderNum`, `CustomerID`) VALUES ('6', '4');
INSERT INTO `Pizzeria`.`delivery` (`OrderNum`, `CustomerID`) VALUES ('7', '5');


INSERT INTO `Pizzeria`.`pickup` (`OrderNum`, `CustomerID`) VALUES ('3', '2');
INSERT INTO `Pizzeria`.`pickup` (`OrderNum`, `CustomerID`) VALUES ('5', '3');


INSERT INTO `Pizzeria`.`discount_order` (`DiscountID`, `OrderNum`) VALUES ('3', '1');
INSERT INTO `Pizzeria`.`discount_order` (`DiscountID`, `OrderNum`) VALUES ('2', '2');
INSERT INTO `Pizzeria`.`discount_order` (`DiscountID`, `OrderNum`) VALUES ('4', '2');
INSERT INTO `Pizzeria`.`discount_order` (`DiscountID`, `OrderNum`) VALUES ('4', '4');
INSERT INTO `Pizzeria`.`discount_order` (`DiscountID`, `OrderNum`) VALUES ('6', '4');
INSERT INTO `Pizzeria`.`discount_order` (`DiscountID`, `OrderNum`) VALUES ('4', '5');
INSERT INTO `Pizzeria`.`discount_order` (`DiscountID`, `OrderNum`) VALUES ('1', '7');


INSERT INTO `Pizzeria`.`discount_pizza` (`DiscountID`, `PizzaID`) VALUES ('3', '1');
INSERT INTO `Pizzeria`.`discount_pizza` (`DiscountID`, `PizzaID`) VALUES ('2', 2);
INSERT INTO `Pizzeria`.`discount_pizza` (`DiscountID`, `PizzaID`) VALUES ('4', 2);
INSERT INTO `Pizzeria`.`discount_pizza` (`DiscountID`, `PizzaID`) VALUES ('4', 11);
INSERT INTO `Pizzeria`.`discount_pizza` (`DiscountID`, `PizzaID`) VALUES ('4', 13);


INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 1, 1);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 1, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (2, 1, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (15, 2, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (9, 2, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (7, 2, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (8, 2, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (12, 2, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 3, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (12, 3, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (4, 3, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 4, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 5, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 6, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 7, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 8, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 9, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 4, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 5, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 6, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 7, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 8, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 9, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (14, 10, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 10, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (2, 10, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (14, 11, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (10, 11, 1);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (3, 11, 1);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (14, 12, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (17, 12, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (4, 12, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (5, 13, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (6, 13, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (7, 13, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (8, 13, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (9, 13, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (16, 13, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (4, 14, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (5, 14, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (6, 14, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (8, 14, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (14, 14, 1);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (14, 15, 1);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 16, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 16, 1);




