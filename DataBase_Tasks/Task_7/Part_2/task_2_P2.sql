------------------------------------------------------------
-- 1. Departments and Employees
------------------------------------------------------------

CREATE TABLE Departments5 (
    department_id5 NUMBER PRIMARY KEY,
    department_name5 VARCHAR2(100)
);

CREATE TABLE Employees5 (
    employee_id5 NUMBER PRIMARY KEY,
    employee_name5 VARCHAR2(100),
    department_id5 NUMBER,
    CONSTRAINT fk_employee_department5 FOREIGN KEY (department_id5) REFERENCES Departments5(department_id5)
);

INSERT INTO Departments5 VALUES (10, 'IT');
INSERT INTO Departments5 VALUES (20, 'HR');
INSERT INTO Departments5 VALUES (30, 'Sales');

INSERT INTO Employees5 VALUES (101, 'Ali', 10);
INSERT INTO Employees5 VALUES (102, 'Mona', 20);

SELECT *
FROM Employees5
RIGHT OUTER JOIN Departments5
USING (department_id5);


------------------------------------------------------------
-- 2. Orders and Customers
------------------------------------------------------------

CREATE TABLE Customers5 (
    customer_id5 NUMBER PRIMARY KEY,
    customer_name5 VARCHAR2(100)
);

CREATE TABLE Orders5 (
    order_id5 NUMBER PRIMARY KEY,
    order_date5 DATE,
    customer_id5 NUMBER,
    CONSTRAINT fk_order_customer5 FOREIGN KEY (customer_id5) REFERENCES Customers5(customer_id5)
);

INSERT INTO Customers5 VALUES (201, 'Ahmed');
INSERT INTO Customers5 VALUES (202, 'Sara');
INSERT INTO Customers5 VALUES (203, 'Omar');

INSERT INTO Orders5 VALUES (1001, DATE '2026-07-20', 201);
INSERT INTO Orders5 VALUES (1002, DATE '2026-07-21', 202);

SELECT *
FROM Orders5
RIGHT JOIN Customers5
USING (customer_id5);


------------------------------------------------------------
-- 3. Courses and Students
------------------------------------------------------------

CREATE TABLE Courses5 (
    course_id5 NUMBER PRIMARY KEY,
    course_name5 VARCHAR2(100)
);

CREATE TABLE Students5 (
    student_id5 NUMBER PRIMARY KEY,
    student_name5 VARCHAR2(100),
    course_id5 NUMBER,
    CONSTRAINT fk_student_course5 FOREIGN KEY (course_id5) REFERENCES Courses5(course_id5)
);

INSERT INTO Courses5 VALUES (301, 'Oracle Database');
INSERT INTO Courses5 VALUES (302, 'Java Programming');
INSERT INTO Courses5 VALUES (303, 'Networking');

INSERT INTO Students5 VALUES (401, 'Hana', 301);
INSERT INTO Students5 VALUES (402, 'Youssef', 302);

SELECT *
FROM Students5
RIGHT JOIN Courses5
USING (course_id5);


------------------------------------------------------------
-- 4. Projects and Assigned Employees
------------------------------------------------------------

CREATE TABLE Projects5 (
    project_id5 NUMBER PRIMARY KEY,
    project_name5 VARCHAR2(100)
);

CREATE TABLE Employees_Projects5 (
    assignment_id5 NUMBER PRIMARY KEY,
    employee_name5 VARCHAR2(100),
    project_id5 NUMBER,
    CONSTRAINT fk_employee_project5 FOREIGN KEY (project_id5) REFERENCES Projects5(project_id5)
);

INSERT INTO Projects5 VALUES (501, 'Website Project');
INSERT INTO Projects5 VALUES (502, 'Mobile Application');
INSERT INTO Projects5 VALUES (503, 'Database Project');

INSERT INTO Employees_Projects5 VALUES (1, 'Ali', 501);
INSERT INTO Employees_Projects5 VALUES (2, 'Mona', 502);

SELECT *
FROM Employees_Projects5
RIGHT OUTER JOIN Projects5
USING (project_id5);


------------------------------------------------------------
-- 5. Payment Methods and Transactions
------------------------------------------------------------

CREATE TABLE Payment_Methods5 (
    payment_method_id5 NUMBER PRIMARY KEY,
    payment_method_name5 VARCHAR2(100)
);

CREATE TABLE Transactions5 (
    transaction_id5 NUMBER PRIMARY KEY,
    amount5 NUMBER(10,2),
    payment_method_id5 NUMBER,
    CONSTRAINT fk_transaction_method5 FOREIGN KEY (payment_method_id5) REFERENCES Payment_Methods5(payment_method_id5)
);

INSERT INTO Payment_Methods5 VALUES (601, 'Cash');
INSERT INTO Payment_Methods5 VALUES (602, 'Credit Card');
INSERT INTO Payment_Methods5 VALUES (603, 'Bank Transfer');

INSERT INTO Transactions5 VALUES (7001, 500, 601);
INSERT INTO Transactions5 VALUES (7002, 1000, 602);

SELECT *
FROM Transactions5
RIGHT JOIN Payment_Methods5
USING (payment_method_id5);


------------------------------------------------------------
-- 6. Authors and Books
------------------------------------------------------------

CREATE TABLE Authors5 (
    author_id5 NUMBER PRIMARY KEY,
    author_name5 VARCHAR2(100)
);

CREATE TABLE Books5 (
    book_id5 NUMBER PRIMARY KEY,
    book_title5 VARCHAR2(150),
    author_id5 NUMBER,
    CONSTRAINT fk_book_author5 FOREIGN KEY (author_id5) REFERENCES Authors5(author_id5)
);

INSERT INTO Authors5 VALUES (701, 'John Smith');
INSERT INTO Authors5 VALUES (702, 'Emily Brown');
INSERT INTO Authors5 VALUES (703, 'David Wilson');

INSERT INTO Books5 VALUES (801, 'Learning SQL', 701);
INSERT INTO Books5 VALUES (802, 'Oracle Basics', 702);

SELECT *
FROM Books5
RIGHT OUTER JOIN Authors5
USING (author_id5);


------------------------------------------------------------
-- 7. Categories and Products
------------------------------------------------------------

CREATE TABLE Categories5 (
    category_id5 NUMBER PRIMARY KEY,
    category_name5 VARCHAR2(100)
);

CREATE TABLE Products5 (
    product_id5 NUMBER PRIMARY KEY,
    product_name5 VARCHAR2(100),
    category_id5 NUMBER,
    CONSTRAINT fk_product_category5 FOREIGN KEY (category_id5) REFERENCES Categories5(category_id5)
);

INSERT INTO Categories5 VALUES (901, 'Electronics');
INSERT INTO Categories5 VALUES (902, 'Furniture');
INSERT INTO Categories5 VALUES (903, 'Clothing');

INSERT INTO Products5 VALUES (1001, 'Laptop', 901);
INSERT INTO Products5 VALUES (1002, 'Table', 902);

SELECT *
FROM Products5
RIGHT JOIN Categories5
USING (category_id5);


------------------------------------------------------------
-- 8. Dorm Rooms and Students
------------------------------------------------------------

CREATE TABLE Dorm_Rooms5 (
    dorm_room_id5 NUMBER PRIMARY KEY,
    room_number5 VARCHAR2(20)
);

CREATE TABLE Students_Dorm5 (
    student_id5 NUMBER PRIMARY KEY,
    student_name5 VARCHAR2(100),
    dorm_room_id5 NUMBER,
    CONSTRAINT fk_student_dorm5 FOREIGN KEY (dorm_room_id5) REFERENCES Dorm_Rooms5(dorm_room_id5)
);

INSERT INTO Dorm_Rooms5 VALUES (1101, 'A-101');
INSERT INTO Dorm_Rooms5 VALUES (1102, 'A-102');
INSERT INTO Dorm_Rooms5 VALUES (1103, 'B-201');

INSERT INTO Students_Dorm5 VALUES (1201, 'Laila', 1101);
INSERT INTO Students_Dorm5 VALUES (1202, 'Omar', 1102);

SELECT *
FROM Students_Dorm5
RIGHT OUTER JOIN Dorm_Rooms5
USING (dorm_room_id5);


