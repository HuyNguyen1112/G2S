-- 1)
SELECT DISTINCT c.customer_id, c.customer_name
FROM Customer c
INNER JOIN Orders o ON 
c.customer_id = o.customer_id;
GO

-- 2)
SELECT order_id, order_date, customer_id, employee_id, total
FROM Orders 
WHERE customer_id = 1;
GO

-- 3)
SELECT *
FROM LineItem
WHERE order_id = 2; 
GO

-- 4)
CREATE FUNCTION getOrderTotal (@orderId INT)
RETURNS DECIMAL(10,2)
AS
BEGIN
    DECLARE @total DECIMAL(10,2);

    SELECT @total = SUM(quantity * price)
    FROM LineItem
    WHERE order_id = @orderId;

    RETURN @total;
END;
GO

SELECT dbo.getOrderTotal(2) AS TotalPrice;
GO

-- 5)
CREATE PROCEDURE AddCustomer @name NVARCHAR(255)
AS
BEGIN
    INSERT INTO Customer
    VALUES (@name);
END;
GO

EXEC AddCustomer 'Quân';
GO 

-- 6)
CREATE PROCEDURE DeleteCustomer @cid INT
AS
BEGIN
    DELETE FROM LineItem
    WHERE order_id IN (SELECT order_id FROM Orders WHERE customer_id = @cid);

    DELETE FROM Orders
    WHERE customer_id = @cid;

    DELETE FROM Customer
    WHERE customer_id = @cid;
END;
GO

EXEC DeleteCustomer @cid = 3;
GO

-- 7)
CREATE PROCEDURE UpdateCustomer @cid INT, @new_name NVARCHAR(255)
AS
BEGIN
    UPDATE Customer
    SET customer_name = @new_name
    WHERE customer_id = @cid;
END
GO

EXEC UpdateCustomer 1, N'Huy Siêu ĐZ';
GO

-- 8)
INSERT INTO Orders(customer_id, employee_id, total)
VALUES (1, 1, 0);
GO

-- 9)
INSERT INTO LineItem(order_id, product_id, quantity, price)
VALUES (2, 2, 1, 1);
GO

-- 10)
UPDATE Orders
SET total = (
    SELECT SUM(quantity * price)
    FROM LineItem
    WHERE LineItem.order_id = Orders.order_id
)
WHERE order_id = 2;
