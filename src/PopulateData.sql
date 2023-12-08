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
VALUES (102, 'Andrew ', 'Wilkes-Krier', '864-254-5861', '115 Party Blvd, Anderson SC 29621');
INSERT INTO `Pizzeria`.`customer` (`CustomerID`, `CustomerFName`, `CustomerLName`, `CustomerPhone`, `CustomerAddr`) 
VALUES (103, 'Matt', 'Engers', '864-474-9953', NULL);
INSERT INTO `Pizzeria`.`customer` (`CustomerID`, `CustomerFName`, `CustomerLName`, `CustomerPhone`, `CustomerAddr`) 
VALUES (104, 'Frank', 'Turner', '864-232-8944', '6745 Wessex St Anderson SC 29621');
INSERT INTO `Pizzeria`.`customer` (`CustomerID`, `CustomerFName`, `CustomerLName`, `CustomerPhone`, `CustomerAddr`) 
VALUES (105, 'Milo', 'Auckerman', '864-878-5679', '8879 Suburban Home, Anderson, SC 29621');


INSERT INTO Pizzeria.`order` (OrderType, OrderCompletion, OrderTime, OrderDate, OrderBusinessCost, OrderCustPrice, CustomerID)
VALUES ('dinein', 1, '12:03', '2023-03-05', 3.68, 20.75, NULL);
INSERT INTO Pizzeria.`order` (OrderType, OrderCompletion, OrderTime, OrderDate, OrderBusinessCost, OrderCustPrice, CustomerID)
VALUES ('dinein', 1, '12:05', '2023-04-03', 4.63,19.78,	NULL);
INSERT INTO Pizzeria.`order` (OrderType, OrderCompletion, OrderTime, OrderDate, OrderBusinessCost, OrderCustPrice, CustomerID)
VALUES ('pickup', 1, '21:30', '2023-03-03', 19.8, 89.28, 102);
INSERT INTO Pizzeria.`order` (OrderType, OrderCompletion, OrderTime, OrderDate, OrderBusinessCost, OrderCustPrice, CustomerID)
VALUES ('delivery', 1, '19:11', '2023-04-20', 23.62, 86.19, 102);
INSERT INTO Pizzeria.`order` (OrderType, OrderCompletion, OrderTime, OrderDate, OrderBusinessCost, OrderCustPrice, CustomerID)
VALUES ('pickup', 1, '17:30', '2023-03-02', 7.88, 27.45, 103);
INSERT INTO Pizzeria.`order` (OrderType, OrderCompletion, OrderTime, OrderDate, OrderBusinessCost, OrderCustPrice, CustomerID)
VALUES ('delivery', 1, '18:17', '2023-03-02', 4.24, 25.81, 104);
INSERT INTO Pizzeria.`order` (OrderType, OrderCompletion, OrderTime, OrderDate, OrderBusinessCost, OrderCustPrice, CustomerID)
VALUES ('delivery', 1, '20:32', '2023-04-13', 6, 37.25, 105);


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


INSERT INTO `Pizzeria`.`delivery` (`OrderNum`, `CustomerID`) VALUES ('4', '102');
INSERT INTO `Pizzeria`.`delivery` (`OrderNum`, `CustomerID`) VALUES ('6', '104');
INSERT INTO `Pizzeria`.`delivery` (`OrderNum`, `CustomerID`) VALUES ('7', '105');


INSERT INTO `Pizzeria`.`pickup` (`OrderNum`, `CustomerID`) VALUES ('3', '102');
INSERT INTO `Pizzeria`.`pickup` (`OrderNum`, `CustomerID`) VALUES ('5', '103');


INSERT INTO `Pizzeria`.`discount_order` (`DiscountID`, `OrderNum`) VALUES ('3', '1');
INSERT INTO `Pizzeria`.`discount_order` (`DiscountID`, `OrderNum`) VALUES ('2', '2');
INSERT INTO `Pizzeria`.`discount_order` (`DiscountID`, `OrderNum`) VALUES ('4', '2');
INSERT INTO `Pizzeria`.`discount_order` (`DiscountID`, `OrderNum`) VALUES ('4', '4');
INSERT INTO `Pizzeria`.`discount_order` (`DiscountID`, `OrderNum`) VALUES ('6', '4');
INSERT INTO `Pizzeria`.`discount_order` (`DiscountID`, `OrderNum`) VALUES ('4', '5');
INSERT INTO `Pizzeria`.`discount_order` (`DiscountID`, `OrderNum`) VALUES ('1', '7');


INSERT INTO `Pizzeria`.`discount_pizza` (`DiscountID`, `PizzaID`) VALUES ('3', '1000');
INSERT INTO `Pizzeria`.`discount_pizza` (`DiscountID`, `PizzaID`) VALUES ('2', 1001);
INSERT INTO `Pizzeria`.`discount_pizza` (`DiscountID`, `PizzaID`) VALUES ('4', 1001);
INSERT INTO `Pizzeria`.`discount_pizza` (`DiscountID`, `PizzaID`) VALUES ('4', 1010);
INSERT INTO `Pizzeria`.`discount_pizza` (`DiscountID`, `PizzaID`) VALUES ('4', 1012);


INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 1000, 1);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 1000, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (2, 1000, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (15, 1001, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (9, 1001, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (7, 1001, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (8, 1001, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (12, 1001, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 1002, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (12, 1002, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (4, 1002, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 1003, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 1004, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 1005, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 1006, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 1007, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 1008, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 1003, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 1004, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 1005, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 1006, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 1007, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 1008, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (14, 1009, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 1009, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (2, 1009, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (14, 1010, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (10, 1010, 1);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (3, 1010, 1);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (14, 1011, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (17, 1011, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (4, 1011, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (5, 1012, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (6, 1012, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (7, 1012, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (8, 1012, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (9, 1012, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (16, 1012, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (4, 1013, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (5, 1013, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (6, 1013, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (8, 1013, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (14, 1013, 1);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (14, 1014, 1);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (13, 1015, 0);
INSERT INTO Pizzeria.topping_pizza (ToppingKey, PizzaID, ToppingExtra)
VALUES (1, 1015, 1);




