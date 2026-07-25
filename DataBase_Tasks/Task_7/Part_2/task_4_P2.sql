------------------------------------------------------------
-- 1. Employees earning more than the average salary
------------------------------------------------------------

CREATE TABLE Employees71 (
    employee_id71 NUMBER PRIMARY KEY,
    employee_name71 VARCHAR2(100),
    salary71 NUMBER(10,2)
);

INSERT INTO Employees71 VALUES (1, 'Ahmed', 5000);
INSERT INTO Employees71 VALUES (2, 'Sara', 9000);
INSERT INTO Employees71 VALUES (3, 'Omar', 7000);
INSERT INTO Employees71 VALUES (4, 'Mona', 12000);

-- Solution
SELECT employee_name71, salary71
FROM Employees71
WHERE salary71 > (
    SELECT AVG(salary71)
    FROM Employees71
);


------------------------------------------------------------
-- 2. Customers with the highest number of orders
------------------------------------------------------------

CREATE TABLE Customers72 (
    customer_id72 NUMBER PRIMARY KEY,
    customer_name72 VARCHAR2(100)
);

CREATE TABLE Orders72 (
    order_id72 NUMBER PRIMARY KEY,
    customer_id72 NUMBER,
    CONSTRAINT fk_order_customer72
        FOREIGN KEY (customer_id72)
        REFERENCES Customers72(customer_id72)
);

INSERT INTO Customers72 VALUES (1, 'Ahmed');
INSERT INTO Customers72 VALUES (2, 'Sara');
INSERT INTO Customers72 VALUES (3, 'Omar');

INSERT INTO Orders72 VALUES (101, 1);
INSERT INTO Orders72 VALUES (102, 1);
INSERT INTO Orders72 VALUES (103, 1);
INSERT INTO Orders72 VALUES (104, 2);
INSERT INTO Orders72 VALUES (105, 2);
INSERT INTO Orders72 VALUES (106, 2);
INSERT INTO Orders72 VALUES (107, 3);

-- Solution
SELECT c.customer_name72,
       COUNT(o.order_id72) AS order_count
FROM Customers72 c
JOIN Orders72 o
    ON c.customer_id72 = o.customer_id72
GROUP BY c.customer_id72, c.customer_name72
HAVING COUNT(o.order_id72) = (
    SELECT MAX(COUNT(*))
    FROM Orders72
    GROUP BY customer_id72
);


------------------------------------------------------------
-- 3. Products priced higher than ANY Accessories product
------------------------------------------------------------

CREATE TABLE Categories73 (
    category_id73 NUMBER PRIMARY KEY,
    category_name73 VARCHAR2(100)
);

CREATE TABLE Products73 (
    product_id73 NUMBER PRIMARY KEY,
    product_name73 VARCHAR2(100),
    price73 NUMBER(10,2),
    category_id73 NUMBER,
    CONSTRAINT fk_product_category73
        FOREIGN KEY (category_id73)
        REFERENCES Categories73(category_id73)
);

INSERT INTO Categories73 VALUES (1, 'Accessories');
INSERT INTO Categories73 VALUES (2, 'Electronics');

INSERT INTO Products73 VALUES (101, 'Mouse', 20, 1);
INSERT INTO Products73 VALUES (102, 'Keyboard', 40, 1);
INSERT INTO Products73 VALUES (103, 'USB Cable', 10, 1);
INSERT INTO Products73 VALUES (104, 'Laptop', 1000, 2);
INSERT INTO Products73 VALUES (105, 'Monitor', 300, 2);

-- Solution
SELECT product_name73, price73
FROM Products73
WHERE price73 > ANY (
    SELECT p.price73
    FROM Products73 p
    JOIN Categories73 c
        ON p.category_id73 = c.category_id73
    WHERE c.category_name73 = 'Accessories'
);


------------------------------------------------------------
-- 4. Employees in the same department as John Smith
------------------------------------------------------------

CREATE TABLE Employees74 (
    employee_id74 NUMBER PRIMARY KEY,
    employee_name74 VARCHAR2(100),
    department_id74 NUMBER
);

INSERT INTO Employees74 VALUES (1, 'John Smith', 10);
INSERT INTO Employees74 VALUES (2, 'Ali Hassan', 10);
INSERT INTO Employees74 VALUES (3, 'Sara Ahmed', 20);
INSERT INTO Employees74 VALUES (4, 'Mona Adel', 30);

-- Solution
SELECT employee_name74, department_id74
FROM Employees74
WHERE department_id74 = (
    SELECT department_id74
    FROM Employees74
    WHERE employee_name74 = 'John Smith'
);

------------------------------------------------------------
-- 5. Orders placed by customers from New York
------------------------------------------------------------

CREATE TABLE Customers75 (
    customer_id75 NUMBER PRIMARY KEY,
    customer_name75 VARCHAR2(100),
    city75 VARCHAR2(100)
);

CREATE TABLE Orders75 (
    order_id75 NUMBER PRIMARY KEY,
    order_date75 DATE,
    customer_id75 NUMBER,
    CONSTRAINT fk_order_customer75
        FOREIGN KEY (customer_id75)
        REFERENCES Customers75(customer_id75)
);

INSERT INTO Customers75 VALUES (1, 'Ahmed', 'New York');
INSERT INTO Customers75 VALUES (2, 'Sara', 'Cairo');
INSERT INTO Customers75 VALUES (3, 'Omar', 'New York');

INSERT INTO Orders75 VALUES (101, DATE '2026-01-10', 1);
INSERT INTO Orders75 VALUES (102, DATE '2026-01-11', 2);
INSERT INTO Orders75 VALUES (103, DATE '2026-01-12', 3);

-- Solution
SELECT order_id75, order_date75, customer_id75
FROM Orders75
WHERE customer_id75 IN (
    SELECT customer_id75
    FROM Customers75
    WHERE city75 = 'New York'
);


------------------------------------------------------------
-- 6. Departments that have no employees
------------------------------------------------------------

CREATE TABLE Departments76 (
    department_id76 NUMBER PRIMARY KEY,
    department_name76 VARCHAR2(100)
);

CREATE TABLE Employees76 (
    employee_id76 NUMBER PRIMARY KEY,
    employee_name76 VARCHAR2(100),
    department_id76 NUMBER,
    CONSTRAINT fk_employee_department76
        FOREIGN KEY (department_id76)
        REFERENCES Departments76(department_id76)
);

INSERT INTO Departments76 VALUES (10, 'IT');
INSERT INTO Departments76 VALUES (20, 'HR');
INSERT INTO Departments76 VALUES (30, 'Marketing');

INSERT INTO Employees76 VALUES (1, 'Ahmed', 10);
INSERT INTO Employees76 VALUES (2, 'Sara', 20);
INSERT INTO Employees76 VALUES (3, 'Omar', 10);

-- Solution
SELECT department_id76, department_name76
FROM Departments76 d
WHERE NOT EXISTS (
    SELECT 1
    FROM Employees76 e
    WHERE e.department_id76 = d.department_id76
);


------------------------------------------------------------
-- 7. Students who are not enrolled in any course
------------------------------------------------------------

CREATE TABLE Students77 (
    student_id77 NUMBER PRIMARY KEY,
    student_name77 VARCHAR2(100)
);

CREATE TABLE Enrollments77 (
    enrollment_id77 NUMBER PRIMARY KEY,
    student_id77 NUMBER,
    course_name77 VARCHAR2(100),
    CONSTRAINT fk_enrollment_student77
        FOREIGN KEY (student_id77)
        REFERENCES Students77(student_id77)
);

INSERT INTO Students77 VALUES (1, 'Hana');
INSERT INTO Students77 VALUES (2, 'Youssef');
INSERT INTO Students77 VALUES (3, 'Laila');
INSERT INTO Students77 VALUES (4, 'Adam');

INSERT INTO Enrollments77 VALUES (101, 1, 'Oracle Database');
INSERT INTO Enrollments77 VALUES (102, 2, 'Java Programming');

-- Solution
SELECT student_id77, student_name77
FROM Students77 s
WHERE NOT EXISTS (
    SELECT 1
    FROM Enrollments77 e
    WHERE e.student_id77 = s.student_id77
);


------------------------------------------------------------
-- 8. Retrieve the second-highest salary
------------------------------------------------------------

CREATE TABLE Employees78 (
    employee_id78 NUMBER PRIMARY KEY,
    employee_name78 VARCHAR2(100),
    salary78 NUMBER(10,2)
);

INSERT INTO Employees78 VALUES (1, 'Ahmed', 5000);
INSERT INTO Employees78 VALUES (2, 'Sara', 9000);
INSERT INTO Employees78 VALUES (3, 'Omar', 7000);
INSERT INTO Employees78 VALUES (4, 'Mona', 12000);
INSERT INTO Employees78 VALUES (5, 'Ali', 9000);

-- Solution
SELECT MAX(salary78) AS second_highest_salary
FROM Employees78
WHERE salary78 < (
    SELECT MAX(salary78)
    FROM Employees78
);


------------------------------------------------------------
-- 9. Products priced above the average product price
------------------------------------------------------------

CREATE TABLE Products79 (
    product_id79 NUMBER PRIMARY KEY,
    product_name79 VARCHAR2(100),
    price79 NUMBER(10,2)
);

INSERT INTO Products79 VALUES (1, 'Mouse', 20);
INSERT INTO Products79 VALUES (2, 'Keyboard', 40);
INSERT INTO Products79 VALUES (3, 'Laptop', 1000);
INSERT INTO Products79 VALUES (4, 'Monitor', 300);
INSERT INTO Products79 VALUES (5, 'USB Cable', 10);

-- Solution
SELECT product_name79, price79
FROM Products79
WHERE price79 > (
    SELECT AVG(price79)
    FROM Products79
);


------------------------------------------------------------
-- 10. Customers who ordered all products in category A
------------------------------------------------------------

CREATE TABLE Customers80 (
    customer_id80 NUMBER PRIMARY KEY,
    customer_name80 VARCHAR2(100)
);

CREATE TABLE Products80 (
    product_id80 NUMBER PRIMARY KEY,
    product_name80 VARCHAR2(100),
    category_name80 VARCHAR2(100)
);

CREATE TABLE Orders80 (
    order_id80 NUMBER PRIMARY KEY,
    customer_id80 NUMBER,
    CONSTRAINT fk_order_customer80
        FOREIGN KEY (customer_id80)
        REFERENCES Customers80(customer_id80)
);

CREATE TABLE Order_Details80 (
    order_id80 NUMBER,
    product_id80 NUMBER,
    quantity80 NUMBER,
    CONSTRAINT pk_order_details80
        PRIMARY KEY (order_id80, product_id80),
    CONSTRAINT fk_detail_order80
        FOREIGN KEY (order_id80)
        REFERENCES Orders80(order_id80),
    CONSTRAINT fk_detail_product80
        FOREIGN KEY (product_id80)
        REFERENCES Products80(product_id80)
);

INSERT INTO Customers80 VALUES (1, 'Ahmed');
INSERT INTO Customers80 VALUES (2, 'Sara');
INSERT INTO Customers80 VALUES (3, 'Omar');

INSERT INTO Products80 VALUES (101, 'Product A1', 'A');
INSERT INTO Products80 VALUES (102, 'Product A2', 'A');
INSERT INTO Products80 VALUES (103, 'Product B1', 'B');

INSERT INTO Orders80 VALUES (1001, 1);
INSERT INTO Orders80 VALUES (1002, 1);
INSERT INTO Orders80 VALUES (1003, 2);

-- Ahmed ordered both category A products
INSERT INTO Order_Details80 VALUES (1001, 101, 1);
INSERT INTO Order_Details80 VALUES (1002, 102, 1);

-- Sara ordered only one category A product
INSERT INTO Order_Details80 VALUES (1003, 101, 1);

-- Solution
SELECT c.customer_id80, c.customer_name80
FROM Customers80 c
WHERE NOT EXISTS (
    SELECT 1
    FROM Products80 p
    WHERE p.category_name80 = 'A'
      AND NOT EXISTS (
          SELECT 1
          FROM Orders80 o
          JOIN Order_Details80 od
              ON o.order_id80 = od.order_id80
          WHERE o.customer_id80 = c.customer_id80
            AND od.product_id80 = p.product_id80
      )
);