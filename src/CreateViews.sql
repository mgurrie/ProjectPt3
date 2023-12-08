-- Jennifer and Mary Love
USE Pizzeria;

DROP VIEW IF EXISTS Pizzeria.ToppingPopularity;

CREATE VIEW Pizzeria.ToppingPopularity AS
SELECT ToppingName AS Topping, 
SUM(CASE WHEN ToppingExtra = 1 THEN 2 WHEN ToppingExtra = 0 THEN 1 ELSE 0 END) AS ToppingCount
FROM Pizzeria.topping t
LEFT OUTER JOIN Pizzeria.topping_pizza pt
ON t.ToppingKey = pt.ToppingKey
GROUP BY ToppingName
ORDER BY ToppingCount DESC;


DROP VIEW IF EXISTS Pizzeria.ProfitByPizza;

CREATE VIEW Pizzeria.ProfitByPizza AS
SELECT 
    p.PizzaSize AS Size, 
    p.PizzaCrust AS Crust,
    ROUND(SUM(p.PizzaPrice - p.PizzaCost), 2) AS Profit,
    DATE_FORMAT(o.OrderDate, "%m/%Y") AS OrderMonth
FROM Pizzeria.pizza p
JOIN Pizzeria.`order` o ON o.OrderNum = p.OrderNum
GROUP BY p.PizzaSize, p.PizzaCrust
ORDER BY SUM(p.PizzaPrice - p.PizzaCost) DESC;


DROP VIEW IF EXISTS Pizzeria.ProfitByOrderType;

CREATE VIEW Pizzeria.ProfitByOrderType AS
SELECT 
	OrderType as customerType,
	DATE_FORMAT(OrderDate, "%m/%Y") AS OrderMonth,
    ROUND(SUM(OrderCustPrice), 2) AS TotalOrderPrice,
    ROUND(SUM(OrderBusinessCost), 2) AS TotalOrderCost,
    ROUND(SUM(OrderCustPrice) - SUM(OrderBusinessCost), 2) AS Profit
FROM Pizzeria.`order`
GROUP BY OrderType, OrderMonth
UNION
	(SELECT 
		'' AS CustomerType,
		'Grand Total' AS OrderMonth,
		ROUND(SUM(OrderCustPrice),2) AS TotalOrderPrice,
		ROUND(SUM(OrderBusinessCost),2) AS TotalOrderCost,
		ROUND(SUM(OrderCustPrice - OrderBusinessCost),2) AS Profit
	FROM Pizzeria.`order`)
	ORDER BY OrderMonth, Profit;