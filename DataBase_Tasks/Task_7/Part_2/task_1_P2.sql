------------------------------------------------------------
-- 1. Managers, Departments and Employees
------------------------------------------------------------

CREATE TABLE Managers4 (
    manager_id4 NUMBER PRIMARY KEY,
    manager_name4 VARCHAR2(100)
);

CREATE TABLE Departments4 (
    department_id4 NUMBER PRIMARY KEY,
    department_name4 VARCHAR2(100),
    manager_id4 NUMBER,
    CONSTRAINT fk_department_manager4 FOREIGN KEY (manager_id4)
        REFERENCES Managers4(manager_id4)
);

CREATE TABLE Employees4 (
    employee_id4 NUMBER PRIMARY KEY,
    employee_name4 VARCHAR2(100),
    department_id4 NUMBER,
    CONSTRAINT fk_employee_department4 FOREIGN KEY (department_id4)
        REFERENCES Departments4(department_id4)
);

INSERT INTO Managers4 VALUES (1, 'Ahmed Manager');
INSERT INTO Managers4 VALUES (2, 'Sara Manager');

INSERT INTO Departments4 VALUES (10, 'IT', 1);
INSERT INTO Departments4 VALUES (20, 'HR', 2);
INSERT INTO Departments4 VALUES (30, 'Sales', NULL);

INSERT INTO Employees4 VALUES (101, 'Ali', 10);
INSERT INTO Employees4 VALUES (102, 'Mona', 20);
INSERT INTO Employees4 VALUES (103, 'Omar', NULL);

SELECT * FROM Employees4 LEFT JOIN Departments4 USING (department_id4);

------------------------------------------------------------
-- 2. Products and Categories
------------------------------------------------------------

CREATE TABLE Categories4 (
    category_id4 NUMBER PRIMARY KEY,
    category_name4 VARCHAR2(100)
);

CREATE TABLE Products4 (
    product_id4 NUMBER PRIMARY KEY,
    product_name4 VARCHAR2(100),
    category_id4 NUMBER,
    CONSTRAINT fk_product_category4 FOREIGN KEY (category_id4)
        REFERENCES Categories4(category_id4)
);

INSERT INTO Categories4 VALUES (1, 'Electronics');
INSERT INTO Categories4 VALUES (2, 'Furniture');

INSERT INTO Products4 VALUES (201, 'Laptop', 1);
INSERT INTO Products4 VALUES (202, 'Table', 2);
INSERT INTO Products4 VALUES (203, 'Unknown Product', NULL);

SELECT * FROM Products4 LEFT JOIN Categories4 USING (category_id4);
-------------------------------------
-- 3. Students and Courses
------------------------------------------------------------

CREATE TABLE Courses4 (
    course_id4 NUMBER PRIMARY KEY,
    course_name4 VARCHAR2(100)
);

CREATE TABLE Students4 (
    student_id4 NUMBER PRIMARY KEY,
    student_name4 VARCHAR2(100),
    course_id4 NUMBER,
    CONSTRAINT fk_student_course4 FOREIGN KEY (course_id4)
        REFERENCES Courses4(course_id4)
);

INSERT INTO Courses4 VALUES (401, 'Oracle Database');
INSERT INTO Courses4 VALUES (402, 'Java Programming');

INSERT INTO Students4 VALUES (301, 'Hana', 401);
INSERT INTO Students4 VALUES (302, 'Youssef', 402);
INSERT INTO Students4 VALUES (303, 'Laila', NULL);

SELECT * FROM Students4 LEFT JOIN Courses4 USING (course_id4);

------------------------------------------------------------
-- 4. Orders and Customers
------------------------------------------------------------

CREATE TABLE Customers4 (
    customer_id4 NUMBER PRIMARY KEY,
    customer_name4 VARCHAR2(100)
);

CREATE TABLE Orders4 (
    order_id4 NUMBER PRIMARY KEY,
    order_date4 DATE,
    customer_id4 NUMBER,
    CONSTRAINT fk_order_customer4 FOREIGN KEY (customer_id4)
        REFERENCES Customers4(customer_id4)
);

INSERT INTO Customers4 VALUES (501, 'Ahmed Customer');
INSERT INTO Customers4 VALUES (502, 'Sara Customer');

INSERT INTO Orders4 VALUES (601, DATE '2026-07-20', 501);
INSERT INTO Orders4 VALUES (602, DATE '2026-07-21', 502);
INSERT INTO Orders4 VALUES (603, DATE '2026-07-22', NULL);

SELECT * FROM Orders4 LEFT JOIN Customers4 USING (customer_id4);
	
------------------------------------------------------------
-- 5. Books and Authors
------------------------------------------------------------

CREATE TABLE Authors4 (
    author_id4 NUMBER PRIMARY KEY,
    author_name4 VARCHAR2(100)
);

CREATE TABLE Books4 (
    book_id4 NUMBER PRIMARY KEY,
    book_title4 VARCHAR2(150),
    author_id4 NUMBER,
    CONSTRAINT fk_book_author4 FOREIGN KEY (author_id4)
        REFERENCES Authors4(author_id4)
);

INSERT INTO Authors4 VALUES (701, 'John Smith');
INSERT INTO Authors4 VALUES (702, 'Emily Brown');

INSERT INTO Books4 VALUES (801, 'Learning SQL', 701);
INSERT INTO Books4 VALUES (802, 'Oracle Basics', 702);
INSERT INTO Books4 VALUES (803, 'Unknown Author Book', NULL);

SELECT * FROM Books4 LEFT JOIN Authors4 USING (author_id4);

------------------------------------------------------------
-- 6. Invoices and Payments
------------------------------------------------------------

CREATE TABLE Invoices4 (
    invoice_id4 NUMBER PRIMARY KEY,
    invoice_number4 VARCHAR2(30),
    total_amount4 NUMBER(10,2)
);

CREATE TABLE Payments4 (
    payment_id4 NUMBER PRIMARY KEY,
    invoice_id4 NUMBER,
    payment_status4 VARCHAR2(30),
    CONSTRAINT fk_payment_invoice4 FOREIGN KEY (invoice_id4)
        REFERENCES Invoices4(invoice_id4)
);

INSERT INTO Invoices4 VALUES (901, 'INV-001', 5000);
INSERT INTO Invoices4 VALUES (902, 'INV-002', 3500);
INSERT INTO Invoices4 VALUES (903, 'INV-003', 2000);

INSERT INTO Payments4 VALUES (1001, 901, 'PAID');
INSERT INTO Payments4 VALUES (1002, 902, 'PENDING');

SELECT * FROM Invoices4 LEFT JOIN Payments4 USING (invoice_id4);


------------------------------------------------------------
-- 7. Employees and Assigned Projects
------------------------------------------------------------

CREATE TABLE Projects_Assigned4 (
    assignment_id4 NUMBER PRIMARY KEY,
    employee_id4 NUMBER,
    project_name4 VARCHAR2(100),
    CONSTRAINT fk_project_employee4 FOREIGN KEY (employee_id4)
        REFERENCES Employees4(employee_id4)
);

INSERT INTO Projects_Assigned4 VALUES (1101, 101, 'Website Project');
INSERT INTO Projects_Assigned4 VALUES (1102, 102, 'HR System');

SELECT * FROM Employees4 LEFT JOIN Projects_Assigned4 USING (employee_id4);
