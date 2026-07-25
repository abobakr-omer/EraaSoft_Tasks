------------------------------------------------------------
-- QUESTION 1
-- Find the employee(s) with the highest salary
------------------------------------------------------------

CREATE TABLE Employees7_Q1 (
    employee_id7 NUMBER PRIMARY KEY,
    employee_name7 VARCHAR2(100),
    salary7 NUMBER(10,2)
);

INSERT INTO Employees7_Q1 (employee_id7, employee_name7, salary7) VALUES (1, 'Ahmed', 5000);
INSERT INTO Employees7_Q1 (employee_id7, employee_name7, salary7) VALUES (2, 'Sara', 9000);
INSERT INTO Employees7_Q1 (employee_id7, employee_name7, salary7) VALUES (3, 'Omar', 9000);

SELECT employee_name7, salary7
FROM Employees7_Q1
WHERE salary7 = (
    SELECT MAX(salary7)
    FROM Employees7_Q1
);


------------------------------------------------------------
-- QUESTION 2
-- Employees in the same department as Alice
------------------------------------------------------------

CREATE TABLE Employees7_Q2 (
    employee_id7 NUMBER PRIMARY KEY,
    employee_name7 VARCHAR2(100),
    department_id7 NUMBER
);

INSERT INTO Employees7_Q2 (employee_id7, employee_name7, department_id7) VALUES (1, 'Alice', 10);
INSERT INTO Employees7_Q2 (employee_id7, employee_name7, department_id7) VALUES (2, 'Bob', 10);
INSERT INTO Employees7_Q2 (employee_id7, employee_name7, department_id7) VALUES (3, 'Carol', 20);

SELECT employee_name7, department_id7
FROM Employees7_Q2
WHERE department_id7 = (
    SELECT department_id7
    FROM Employees7_Q2
    WHERE employee_name7 = 'Alice'
);


------------------------------------------------------------
-- QUESTION 3
-- Product with the lowest price
------------------------------------------------------------

CREATE TABLE Products7_Q3 (
    product_id7 NUMBER PRIMARY KEY,
    product_name7 VARCHAR2(100),
    price7 NUMBER(10,2)
);

INSERT INTO Products7_Q3 (product_id7, product_name7, price7) VALUES (1, 'Laptop', 1000);
INSERT INTO Products7_Q3 (product_id7, product_name7, price7) VALUES (2, 'Mouse', 20);
INSERT INTO Products7_Q3 (product_id7, product_name7, price7) VALUES (3, 'Keyboard', 50);

SELECT *
FROM Products7_Q3
WHERE price7 = (
    SELECT MIN(price7)
    FROM Products7_Q3
);


------------------------------------------------------------
-- QUESTION 4
-- Department of the highest-paid employee
------------------------------------------------------------

CREATE TABLE Departments7_Q4 (
    department_id7 NUMBER PRIMARY KEY,
    department_name7 VARCHAR2(100)
);

CREATE TABLE Employees7_Q4 (
    employee_id7 NUMBER PRIMARY KEY,
    employee_name7 VARCHAR2(100),
    salary7 NUMBER(10,2),
    department_id7 NUMBER,
    CONSTRAINT fk_emp_dept7_q4
        FOREIGN KEY (department_id7)
        REFERENCES Departments7_Q4(department_id7)
);

INSERT INTO Departments7_Q4 (department_id7, department_name7) VALUES (10, 'IT');
INSERT INTO Departments7_Q4 (department_id7, department_name7) VALUES (20, 'HR');

INSERT INTO Employees7_Q4 (employee_id7, employee_name7, salary7, department_id7) VALUES (1, 'Ahmed', 5000, 20);
INSERT INTO Employees7_Q4 (employee_id7, employee_name7, salary7, department_id7) VALUES (2, 'Sara', 12000, 10);
INSERT INTO Employees7_Q4 (employee_id7, employee_name7, salary7, department_id7) VALUES (3, 'Omar', 7000, 20);

SELECT department_name7
FROM Departments7_Q4
WHERE department_id7 = (
    SELECT department_id7
    FROM Employees7_Q4
    WHERE salary7 = (
        SELECT MAX(salary7)
        FROM Employees7_Q4
    )
);


------------------------------------------------------------
-- QUESTION 5
-- Manager of the most recently hired employee
------------------------------------------------------------

CREATE TABLE Employees7_Q5 (
    employee_id7 NUMBER PRIMARY KEY,
    employee_name7 VARCHAR2(100),
    hire_date7 DATE,
    manager_id7 NUMBER,
    CONSTRAINT fk_emp_manager7_q5
        FOREIGN KEY (manager_id7)
        REFERENCES Employees7_Q5(employee_id7)
);

-- Insert the manager first
INSERT INTO Employees7_Q5 (employee_id7, employee_name7, hire_date7, manager_id7) VALUES (1, 'Ahmed Manager', DATE '2018-01-01', NULL);
INSERT INTO Employees7_Q5 (employee_id7, employee_name7, hire_date7, manager_id7) VALUES (2, 'Sara', DATE '2023-05-10', 1);
INSERT INTO Employees7_Q5 (employee_id7, employee_name7, hire_date7, manager_id7) VALUES (3, 'Omar', DATE '2026-07-20', 1);

SELECT manager.employee_name7 AS manager_name
FROM Employees7_Q5 manager
WHERE manager.employee_id7 = (
    SELECT employee.manager_id7
    FROM Employees7_Q5 employee
    WHERE employee.hire_date7 = (
        SELECT MAX(hire_date7)
        FROM Employees7_Q5
    )
);


------------------------------------------------------------
-- QUESTION 6
-- Employee whose salary equals the average salary
------------------------------------------------------------

CREATE TABLE Employees7_Q6 (
    employee_id7 NUMBER PRIMARY KEY,
    employee_name7 VARCHAR2(100),
    salary7 NUMBER(10,2)
);

INSERT INTO Employees7_Q6 (employee_id7, employee_name7, salary7) VALUES (1, 'Ahmed', 5000);
INSERT INTO Employees7_Q6 (employee_id7, employee_name7, salary7) VALUES (2, 'Sara', 7000);
INSERT INTO Employees7_Q6 (employee_id7, employee_name7, salary7) VALUES (3, 'Omar', 9000);

SELECT employee_name7, salary7
FROM Employees7_Q6
WHERE salary7 = (
    SELECT AVG(salary7)
    FROM Employees7_Q6
);


------------------------------------------------------------
-- QUESTION 7
-- Order(s) with the earliest order date
------------------------------------------------------------

CREATE TABLE Orders7_Q7 (
    order_id7 NUMBER PRIMARY KEY,
    order_date7 DATE
);

INSERT INTO Orders7_Q7 (order_id7, order_date7) VALUES (101, DATE '2026-03-20');
INSERT INTO Orders7_Q7 (order_id7, order_date7) VALUES (102, DATE '2026-01-10');
INSERT INTO Orders7_Q7 (order_id7, order_date7) VALUES (103, DATE '2026-05-15');

SELECT *
FROM Orders7_Q7
WHERE order_date7 = (
    SELECT MIN(order_date7)
    FROM Orders7_Q7
);


------------------------------------------------------------
-- QUESTION 8
-- Employees earning more than employee 101
------------------------------------------------------------

CREATE TABLE Employees7_Q8 (
    employee_id7 NUMBER PRIMARY KEY,
    employee_name7 VARCHAR2(100),
    salary7 NUMBER(10,2)
);

INSERT INTO Employees7_Q8 (employee_id7, employee_name7, salary7) VALUES (101, 'Ahmed', 5000);
INSERT INTO Employees7_Q8 (employee_id7, employee_name7, salary7) VALUES (102, 'Sara', 8000);
INSERT INTO Employees7_Q8 (employee_id7, employee_name7, salary7) VALUES (103, 'Omar', 4000);

SELECT employee_name7, salary7
FROM Employees7_Q8
WHERE salary7 > (
    SELECT salary7
    FROM Employees7_Q8
    WHERE employee_id7 = 101
);


------------------------------------------------------------
-- QUESTION 9
-- Students with the same GPA as John Doe
------------------------------------------------------------

CREATE TABLE Students7_Q9 (
    student_id7 NUMBER PRIMARY KEY,
    student_name7 VARCHAR2(100),
    gpa7 NUMBER(3,2)
);

INSERT INTO Students7_Q9 (student_id7, student_name7, gpa7) VALUES (1, 'John Doe', 3.50);
INSERT INTO Students7_Q9 (student_id7, student_name7, gpa7) VALUES (2, 'Sara Ali', 3.50);
INSERT INTO Students7_Q9 (student_id7, student_name7, gpa7) VALUES (3, 'Omar Hassan', 3.20);

SELECT student_name7, gpa7
FROM Students7_Q9
WHERE gpa7 = (
    SELECT gpa7
    FROM Students7_Q9
    WHERE student_name7 = 'John Doe'
);


------------------------------------------------------------
-- QUESTION 10
-- Books with the same price as the most expensive
-- Science book
------------------------------------------------------------

CREATE TABLE Book_Categories7_Q10 (
    category_id7 NUMBER PRIMARY KEY,
    category_name7 VARCHAR2(100)
);

CREATE TABLE Books7_Q10 (
    book_id7 NUMBER PRIMARY KEY,
    book_title7 VARCHAR2(150),
    price7 NUMBER(10,2),
    category_id7 NUMBER,
    CONSTRAINT fk_book_category7_q10
        FOREIGN KEY (category_id7)
        REFERENCES Book_Categories7_Q10(category_id7)
);

INSERT INTO Book_Categories7_Q10 (category_id7, category_name7) VALUES (1, 'Science');
INSERT INTO Book_Categories7_Q10 (category_id7, category_name7) VALUES (2, 'History');

INSERT INTO Books7_Q10 (book_id7, book_title7, price7, category_id7) VALUES (1, 'Physics Basics', 50, 1);
INSERT INTO Books7_Q10 (book_id7, book_title7, price7, category_id7) VALUES (2, 'Biology Guide', 70, 1);
INSERT INTO Books7_Q10 (book_id7, book_title7, price7, category_id7) VALUES (3, 'Chemistry Guide', 70, 1);
INSERT INTO Books7_Q10 (book_id7, book_title7, price7, category_id7) VALUES (4, 'World History', 70, 2);

SELECT book_title7, price7
FROM Books7_Q10
WHERE price7 = (
    SELECT MAX(b.price7)
    FROM Books7_Q10 b
    JOIN Book_Categories7_Q10 c
        ON b.category_id7 = c.category_id7
    WHERE UPPER(c.category_name7) = 'SCIENCE'
);