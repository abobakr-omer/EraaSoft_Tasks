------------------------------------------------------------
-- 1. Customers and Orders
------------------------------------------------------------

CREATE TABLE Customers6 (
    customer_id6 NUMBER PRIMARY KEY,
    customer_name6 VARCHAR2(100)
);

CREATE TABLE Orders6 (
    order_id6 NUMBER PRIMARY KEY,
    order_date6 DATE,
    customer_id6 NUMBER,
    CONSTRAINT fk_order_customer6 FOREIGN KEY (customer_id6)
        REFERENCES Customers6(customer_id6)
);

INSERT INTO Customers6 VALUES (1, 'Ahmed');
INSERT INTO Customers6 VALUES (2, 'Sara');
INSERT INTO Customers6 VALUES (3, 'Omar');

INSERT INTO Orders6 VALUES (101, DATE '2026-07-20', 1);
INSERT INTO Orders6 VALUES (102, DATE '2026-07-21', 2);
INSERT INTO Orders6 VALUES (103, DATE '2026-07-22', NULL);

SELECT *
FROM Customers6
FULL OUTER JOIN Orders6
USING (customer_id6);


------------------------------------------------------------
-- 2. Employees and Projects
------------------------------------------------------------

CREATE TABLE Employees6 (
    employee_id6 NUMBER PRIMARY KEY,
    employee_name6 VARCHAR2(100)
);

CREATE TABLE Projects6 (
    project_id6 NUMBER PRIMARY KEY,
    project_name6 VARCHAR2(100),
    employee_id6 NUMBER,
    CONSTRAINT fk_project_employee6 FOREIGN KEY (employee_id6)
        REFERENCES Employees6(employee_id6)
);

INSERT INTO Employees6 VALUES (201, 'Ali');
INSERT INTO Employees6 VALUES (202, 'Mona');
INSERT INTO Employees6 VALUES (203, 'Laila');

INSERT INTO Projects6 VALUES (301, 'Website Project', 201);
INSERT INTO Projects6 VALUES (302, 'Database Project', 202);
INSERT INTO Projects6 VALUES (303, 'Mobile Project', NULL);

SELECT *
FROM Employees6
FULL OUTER JOIN Projects6
USING (employee_id6);


------------------------------------------------------------
-- 3. Products and Suppliers
------------------------------------------------------------

CREATE TABLE Suppliers6 (
    supplier_id6 NUMBER PRIMARY KEY,
    supplier_name6 VARCHAR2(100)
);

CREATE TABLE Products6 (
    product_id6 NUMBER PRIMARY KEY,
    product_name6 VARCHAR2(100),
    supplier_id6 NUMBER,
    CONSTRAINT fk_product_supplier6 FOREIGN KEY (supplier_id6)
        REFERENCES Suppliers6(supplier_id6)
);

INSERT INTO Suppliers6 VALUES (401, 'ABC Company');
INSERT INTO Suppliers6 VALUES (402, 'XYZ Company');
INSERT INTO Suppliers6 VALUES (403, 'Global Supplier');

INSERT INTO Products6 VALUES (501, 'Laptop', 401);
INSERT INTO Products6 VALUES (502, 'Printer', 402);
INSERT INTO Products6 VALUES (503, 'Unknown Product', NULL);

SELECT *
FROM Products6
FULL OUTER JOIN Suppliers6
USING (supplier_id6);


------------------------------------------------------------
-- 4. Students and Courses
------------------------------------------------------------

CREATE TABLE Courses6 (
    course_id6 NUMBER PRIMARY KEY,
    course_name6 VARCHAR2(100)
);

CREATE TABLE Students6 (
    student_id6 NUMBER PRIMARY KEY,
    student_name6 VARCHAR2(100),
    course_id6 NUMBER,
    CONSTRAINT fk_student_course6 FOREIGN KEY (course_id6)
        REFERENCES Courses6(course_id6)
);

INSERT INTO Courses6 VALUES (601, 'Oracle Database');
INSERT INTO Courses6 VALUES (602, 'Java Programming');
INSERT INTO Courses6 VALUES (603, 'Networks');

INSERT INTO Students6 VALUES (701, 'Hana', 601);
INSERT INTO Students6 VALUES (702, 'Youssef', 602);
INSERT INTO Students6 VALUES (703, 'Salma', NULL);

SELECT *
FROM Students6
FULL OUTER JOIN Courses6
USING (course_id6);


------------------------------------------------------------
-- 5. Authors and Books
------------------------------------------------------------

CREATE TABLE Authors6 (
    author_id6 NUMBER PRIMARY KEY,
    author_name6 VARCHAR2(100)
);

CREATE TABLE Books6 (
    book_id6 NUMBER PRIMARY KEY,
    book_title6 VARCHAR2(150),
    author_id6 NUMBER,
    CONSTRAINT fk_book_author6 FOREIGN KEY (author_id6)
        REFERENCES Authors6(author_id6)
);

INSERT INTO Authors6 VALUES (801, 'John Smith');
INSERT INTO Authors6 VALUES (802, 'Emily Brown');
INSERT INTO Authors6 VALUES (803, 'David Wilson');

INSERT INTO Books6 VALUES (901, 'Learning SQL', 801);
INSERT INTO Books6 VALUES (902, 'Oracle Basics', 802);
INSERT INTO Books6 VALUES (903, 'Unknown Author Book', NULL);

SELECT *
FROM Authors6
FULL OUTER JOIN Books6
USING (author_id6);


------------------------------------------------------------
-- 6. Employees and Departments
------------------------------------------------------------

CREATE TABLE Departments6 (
    department_id6 NUMBER PRIMARY KEY,
    department_name6 VARCHAR2(100)
);

CREATE TABLE Employees_Dep6 (
    employee_id6 NUMBER PRIMARY KEY,
    employee_name6 VARCHAR2(100),
    department_id6 NUMBER,
    CONSTRAINT fk_emp_department6 FOREIGN KEY (department_id6)
        REFERENCES Departments6(department_id6)
);

INSERT INTO Departments6 VALUES (10, 'IT');
INSERT INTO Departments6 VALUES (20, 'HR');
INSERT INTO Departments6 VALUES (30, 'Sales');

INSERT INTO Employees_Dep6 VALUES (1001, 'Ahmed', 10);
INSERT INTO Employees_Dep6 VALUES (1002, 'Sara', 20);
INSERT INTO Employees_Dep6 VALUES (1003, 'Omar', NULL);

SELECT *
FROM Employees_Dep6
FULL OUTER JOIN Departments6
USING (department_id6);


------------------------------------------------------------
-- 7. Transactions and Payment Methods
------------------------------------------------------------

CREATE TABLE Payment_Methods6 (
    payment_method_id6 NUMBER PRIMARY KEY,
    payment_method_name6 VARCHAR2(100)
);

CREATE TABLE Transactions6 (
    transaction_id6 NUMBER PRIMARY KEY,
    amount6 NUMBER(10,2),
    payment_method_id6 NUMBER,
    CONSTRAINT fk_transaction_method6 FOREIGN KEY (payment_method_id6)
        REFERENCES Payment_Methods6(payment_method_id6)
);

INSERT INTO Payment_Methods6 VALUES (1101, 'Cash');
INSERT INTO Payment_Methods6 VALUES (1102, 'Credit Card');
INSERT INTO Payment_Methods6 VALUES (1103, 'Bank Transfer');

INSERT INTO Transactions6 VALUES (1201, 500, 1101);
INSERT INTO Transactions6 VALUES (1202, 1000, 1102);
INSERT INTO Transactions6 VALUES (1203, 750, NULL);

SELECT *
FROM Transactions6
FULL OUTER JOIN Payment_Methods6
USING (payment_method_id6);


------------------------------------------------------------
-- 8. Customer Lists from Two Regions
------------------------------------------------------------

CREATE TABLE Customers_Region_A6 (
    customer_id6 NUMBER PRIMARY KEY,
    customer_name_a6 VARCHAR2(100)
);

CREATE TABLE Customers_Region_B6 (
    customer_id6 NUMBER PRIMARY KEY,
    customer_name_b6 VARCHAR2(100)
);

INSERT INTO Customers_Region_A6 VALUES (1, 'Ahmed');
INSERT INTO Customers_Region_A6 VALUES (2, 'Sara');
INSERT INTO Customers_Region_A6 VALUES (3, 'Omar');

INSERT INTO Customers_Region_B6 VALUES (2, 'Sara');
INSERT INTO Customers_Region_B6 VALUES (3, 'Omar');
INSERT INTO Customers_Region_B6 VALUES (4, 'Mona');

SELECT *
FROM Customers_Region_A6
FULL OUTER JOIN Customers_Region_B6
USING (customer_id6);


