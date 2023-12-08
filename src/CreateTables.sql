-- Jennifer and Mary Love

CREATE SCHEMA Pizzeria;
USE Pizzeria;

CREATE TABLE Pizzeria.customer (
    CustomerID INT NOT NULL AUTO_INCREMENT,
    CustomerFName VARCHAR(30),
    CustomerLName VARCHAR(30),
    CustomerPhone VARCHAR(20),
    CustomerAddr VARCHAR(50),
    PRIMARY KEY (CustomerID)
);

CREATE TABLE Pizzeria.`order` (
    OrderNum INT NOT NULL AUTO_INCREMENT,
    OrderType VARCHAR(10) NOT NULL,
    OrderCompletion INT NOT NULL,
    OrderDate DATE,
    OrderTime TIME,
    OrderBusinessCost FLOAT NOT NULL,
    OrderCustPrice FLOAT NOT NULL,
    CustomerID INT,
    PRIMARY KEY (OrderNum),
    FOREIGN KEY (CustomerID) REFERENCES Pizzeria.customer(CustomerID)
);

CREATE TABLE Pizzeria.pizza (
    PizzaID INT NOT NULL AUTO_INCREMENT,
    PizzaSize  VARCHAR(10) NOT NULL,
    PizzaState VARCHAR(15) NOT NULL,
    PizzaPrice DOUBLE NOT NULL,
    PizzaCost DOUBLE NOT NULL,
    PizzaCrust VARCHAR(30) NOT NULL,
    PizzaDate VARCHAR(30),
    OrderNum INT NOT NULL,
    PRIMARY KEY (PizzaID),
    FOREIGN KEY (OrderNum) REFERENCES Pizzeria.`order`(OrderNum)
);

CREATE TABLE Pizzeria.topping (
    ToppingKey INT NOT NULL AUTO_INCREMENT,
    ToppingName VARCHAR(30) NOT NULL,
    ToppingCustPrice FLOAT NOT NULL,
    ToppingBusCost FLOAT NOT NULL,
    ToppingAmtSmall FLOAT NOT NULL,
    ToppingAmtMed FLOAT NOT NULL,
    ToppingAmtLarge FLOAT NOT NULL,
    ToppingAmtXL FLOAT NOT NULL,
    ToppingMinInv INT NOT NULL,
    ToppingInv DOUBLE NOT NULL,
    PRIMARY KEY (ToppingKey)
);

CREATE TABLE Pizzeria.topping_pizza (
    ToppingKey INT NOT NULL,
    PizzaID INT NOT NULL,
    ToppingExtra BIT,
    PRIMARY KEY (ToppingKey, PizzaID),
    FOREIGN KEY (ToppingKey) REFERENCES Pizzeria.topping(ToppingKey),
    FOREIGN KEY (PizzaID) REFERENCES Pizzeria.pizza(PizzaID)
);

CREATE TABLE Pizzeria.pizza_base (
    BaseID INT NOT NULL AUTO_INCREMENT,
    BasePrice FLOAT NOT NULL,
    BaseCost FLOAT NOT NULL,
    BaseCrust VARCHAR(30) NOT NULL,
    BaseSize VARCHAR(15) NOT NULL,
    BaseDate DATE,
    PRIMARY KEY (BaseID)
);

CREATE TABLE Pizzeria.discount (
    DiscountID INT NOT NULL AUTO_INCREMENT,
    DiscountName VARCHAR(20) NOT NULL,
    DiscountDollar FLOAT,
    DiscountPct FLOAT,
    PRIMARY KEY (DiscountID)
);

CREATE TABLE Pizzeria.discount_pizza (
    DiscountID INT NOT NULL,
    PizzaID INT NOT NULL,
    PRIMARY KEY (DiscountID, PizzaID),
    FOREIGN KEY (DiscountID) REFERENCES Pizzeria.discount(DiscountID),
    FOREIGN KEY (PizzaID) REFERENCES Pizzeria.pizza(PizzaID)
);

CREATE TABLE Pizzeria.discount_order (
    DiscountID INT NOT NULL,
    OrderNum INT NOT NULL,
    PRIMARY KEY (DiscountID, OrderNum),
    FOREIGN KEY (DiscountID) REFERENCES Pizzeria.discount(DiscountID),
    FOREIGN KEY (OrderNum) REFERENCES Pizzeria.`order`(OrderNum)
);

CREATE TABLE Pizzeria.dinein (
    OrderNum INT NOT NULL,
    TableNum INT,
    PRIMARY KEY (OrderNum),
    FOREIGN KEY (OrderNum) REFERENCES Pizzeria.`order`(OrderNum)
);


CREATE TABLE Pizzeria.pickup (
    OrderNum INT NOT NULL,
    CustomerID INT,
    PRIMARY KEY (OrderNum),
    FOREIGN KEY (OrderNum) REFERENCES Pizzeria.`order`(OrderNum),
    FOREIGN KEY (CustomerID) REFERENCES Pizzeria.customer(CustomerID)
);
 
CREATE TABLE Pizzeria.delivery (
    OrderNum INT NOT NULL,
    CustomerID INT,
    PRIMARY KEY (OrderNum),
    FOREIGN KEY (OrderNum) REFERENCES Pizzeria.`order`(OrderNum),
    FOREIGN KEY (CustomerID) REFERENCES Pizzeria.customer(CustomerID)
);