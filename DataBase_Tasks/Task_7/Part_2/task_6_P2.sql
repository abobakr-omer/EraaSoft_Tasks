------------------------------------------------------------
-- QUESTION 1
-- Employees earning more than at least one employee
-- in department 10
------------------------------------------------------------

CREATE TABLE Employees8_Q1 (
    employee_id8 NUMBER PRIMARY KEY,
    employee_name8 VARCHAR2(100),
    salary8 NUMBER(10,2),
    department_id8 NUMBER
);

INSERT INTO Employees8_Q1 (employee_id8, employee_name8, salary8, department_id8) VALUES (1, 'Ahmed', 4000, 10);
INSERT INTO Employees8_Q1 (employee_id8, employee_name8, salary8, department_id8) VALUES (2, 'Sara', 6000, 10);
INSERT INTO Employees8_Q1 (employee_id8, employee_name8, salary8, department_id8) VALUES (3, 'Omar', 5000, 20);
INSERT INTO Employees8_Q1 (employee_id8, employee_name8, salary8, department_id8) VALUES (4, 'Mona', 8000, 30);

SELECT employee_name8, salary8
FROM Employees8_Q1
WHERE salary8 > ANY (
    SELECT salary8
    FROM Employees8_Q1
    WHERE department_id8 = 10
);


------------------------------------------------------------
-- QUESTION 2
-- Employees earning less than all employees
-- in department 20
------------------------------------------------------------

CREATE TABLE Employees8_Q2 (
    employee_id8 NUMBER PRIMARY KEY,
    employee_name8 VARCHAR2(100),
    salary8 NUMBER(10,2),
    department_id8 NUMBER
);

INSERT INTO Employees8_Q2 (employee_id8, employee_name8, salary8, department_id8) VALUES (1, 'Ali', 5000, 10);
INSERT INTO Employees8_Q2 (employee_id8, employee_name8, salary8, department_id8) VALUES (2, 'Laila', 7000, 30);
INSERT INTO Employees8_Q2 (employee_id8, employee_name8, salary8, department_id8) VALUES (3, 'Hana', 8000, 20);
INSERT INTO Employees8_Q2 (employee_id8, employee_name8, salary8, department_id8) VALUES (4, 'Youssef', 9000, 20);

SELECT employee_name8, salary8
FROM Employees8_Q2
WHERE salary8 < ALL (
    SELECT salary8
    FROM Employees8_Q2
    WHERE department_id8 = 20
);


------------------------------------------------------------
-- QUESTION 3
-- Products whose price equals any product price
-- in the Electronics category
------------------------------------------------------------

CREATE TABLE Categories8_Q3 (
    category_id8 NUMBER PRIMARY KEY,
    category_name8 VARCHAR2(100)
);

CREATE TABLE Products8_Q3 (
    product_id8 NUMBER PRIMARY KEY,
    product_name8 VARCHAR2(100),
    price8 NUMBER(10,2),
    category_id8 NUMBER,
    CONSTRAINT fk_product_category8_q3 FOREIGN KEY (category_id8) REFERENCES Categories8_Q3(category_id8)
);

INSERT INTO Categories8_Q3 (category_id8, category_name8) VALUES (1, 'Electronics');
INSERT INTO Categories8_Q3 (category_id8, category_name8) VALUES (2, 'Furniture');

INSERT INTO Products8_Q3 (product_id8, product_name8, price8, category_id8) VALUES (1, 'Keyboard', 50, 1);
INSERT INTO Products8_Q3 (product_id8, product_name8, price8, category_id8) VALUES (2, 'Mouse', 20, 1);
INSERT INTO Products8_Q3 (product_id8, product_name8, price8, category_id8) VALUES (3, 'Small Table', 50, 2);
INSERT INTO Products8_Q3 (product_id8, product_name8, price8, category_id8) VALUES (4, 'Chair', 100, 2);

SELECT product_name8, price8
FROM Products8_Q3
WHERE price8 IN (
    SELECT p.price8
    FROM Products8_Q3 p
    JOIN Categories8_Q3 c
        ON p.category_id8 = c.category_id8
    WHERE c.category_name8 = 'Electronics'
);


------------------------------------------------------------
-- QUESTION 4
-- Customers who ordered a product costing more than 1000
------------------------------------------------------------

CREATE TABLE Customers8_Q4 (
    customer_id8 NUMBER PRIMARY KEY,
    customer_name8 VARCHAR2(100)
);

CREATE TABLE Orders8_Q4 (
    order_id8 NUMBER PRIMARY KEY,
    customer_id8 NUMBER,
    CONSTRAINT fk_order_customer8_q4 FOREIGN KEY (customer_id8) REFERENCES Customers8_Q4(customer_id8)
);

CREATE TABLE Products8_Q4 (
    product_id8 NUMBER PRIMARY KEY,
    product_name8 VARCHAR2(100),
    price8 NUMBER(10,2)
);

CREATE TABLE Order_Details8_Q4 (
    order_id8 NUMBER,
    product_id8 NUMBER,
    quantity8 NUMBER,
    CONSTRAINT pk_order_details8_q4 PRIMARY KEY (order_id8, product_id8),
    CONSTRAINT fk_detail_order8_q4 FOREIGN KEY (order_id8) REFERENCES Orders8_Q4(order_id8),
    CONSTRAINT fk_detail_product8_q4 FOREIGN KEY (product_id8) REFERENCES Products8_Q4(product_id8)
);

INSERT INTO Customers8_Q4 (customer_id8, customer_name8) VALUES (1, 'Ahmed');
INSERT INTO Customers8_Q4 (customer_id8, customer_name8) VALUES (2, 'Sara');
INSERT INTO Customers8_Q4 (customer_id8, customer_name8) VALUES (3, 'Omar');

INSERT INTO Orders8_Q4 (order_id8, customer_id8) VALUES (101, 1);
INSERT INTO Orders8_Q4 (order_id8, customer_id8) VALUES (102, 2);
INSERT INTO Orders8_Q4 (order_id8, customer_id8) VALUES (103, 3);

INSERT INTO Products8_Q4 (product_id8, product_name8, price8) VALUES (201, 'Laptop', 1500);
INSERT INTO Products8_Q4 (product_id8, product_name8, price8) VALUES (202, 'Mouse', 20);
INSERT INTO Products8_Q4 (product_id8, product_name8, price8) VALUES (203, 'Phone', 1200);

INSERT INTO Order_Details8_Q4 (order_id8, product_id8, quantity8) VALUES (101, 201, 1);
INSERT INTO Order_Details8_Q4 (order_id8, product_id8, quantity8) VALUES (102, 202, 2);
INSERT INTO Order_Details8_Q4 (order_id8, product_id8, quantity8) VALUES (103, 203, 1);

SELECT customer_name8
FROM Customers8_Q4
WHERE customer_id8 IN (
    SELECT customer_id8
    FROM Orders8_Q4
    WHERE order_id8 IN (
        SELECT order_id8
        FROM Order_Details8_Q4
        WHERE product_id8 IN (
            SELECT product_id8
            FROM Products8_Q4
            WHERE price8 > 1000
        )
    )
);


------------------------------------------------------------
-- QUESTION 5
-- Employees sharing a job title with another employee
------------------------------------------------------------

CREATE TABLE Employees8_Q5 (
    employee_id8 NUMBER PRIMARY KEY,
    employee_name8 VARCHAR2(100),
    job_title8 VARCHAR2(100)
);

INSERT INTO Employees8_Q5 (employee_id8, employee_name8, job_title8) VALUES (1, 'Ahmed', 'Developer');
INSERT INTO Employees8_Q5 (employee_id8, employee_name8, job_title8) VALUES (2, 'Sara', 'Developer');
INSERT INTO Employees8_Q5 (employee_id8, employee_name8, job_title8) VALUES (3, 'Omar', 'Manager');
INSERT INTO Employees8_Q5 (employee_id8, employee_name8, job_title8) VALUES (4, 'Mona', 'Accountant');

SELECT employee_name8, job_title8
FROM Employees8_Q5
WHERE job_title8 IN (
    SELECT job_title8
    FROM Employees8_Q5
    GROUP BY job_title8
    HAVING COUNT(*) > 1
);


------------------------------------------------------------
-- QUESTION 6
-- Departments having more than one employee
------------------------------------------------------------

CREATE TABLE Departments8_Q6 (
    department_id8 NUMBER PRIMARY KEY,
    department_name8 VARCHAR2(100)
);

CREATE TABLE Employees8_Q6 (
    employee_id8 NUMBER PRIMARY KEY,
    employee_name8 VARCHAR2(100),
    department_id8 NUMBER,
    CONSTRAINT fk_employee_department8_q6 FOREIGN KEY (department_id8) REFERENCES Departments8_Q6(department_id8)
);

INSERT INTO Departments8_Q6 (department_id8, department_name8) VALUES (10, 'IT');
INSERT INTO Departments8_Q6 (department_id8, department_name8) VALUES (20, 'HR');
INSERT INTO Departments8_Q6 (department_id8, department_name8) VALUES (30, 'Sales');

INSERT INTO Employees8_Q6 (employee_id8, employee_name8, department_id8) VALUES (1, 'Ahmed', 10);
INSERT INTO Employees8_Q6 (employee_id8, employee_name8, department_id8) VALUES (2, 'Sara', 10);
INSERT INTO Employees8_Q6 (employee_id8, employee_name8, department_id8) VALUES (3, 'Omar', 20);
INSERT INTO Employees8_Q6 (employee_id8, employee_name8, department_id8) VALUES (4, 'Mona', 30);
INSERT INTO Employees8_Q6 (employee_id8, employee_name8, department_id8) VALUES (5, 'Ali', 30);

SELECT department_id8, department_name8
FROM Departments8_Q6
WHERE department_id8 IN (
    SELECT department_id8
    FROM Employees8_Q6
    GROUP BY department_id8
    HAVING COUNT(*) > 1
);


------------------------------------------------------------
-- QUESTION 7
-- Orders made by customers from cities where multiple
-- customers have placed orders
------------------------------------------------------------

CREATE TABLE Customers8_Q7 (
    customer_id8 NUMBER PRIMARY KEY,
    customer_name8 VARCHAR2(100),
    city8 VARCHAR2(100)
);

CREATE TABLE Orders8_Q7 (
    order_id8 NUMBER PRIMARY KEY,
    customer_id8 NUMBER,
    order_date8 DATE,
    CONSTRAINT fk_order_customer8_q7 FOREIGN KEY (customer_id8) REFERENCES Customers8_Q7(customer_id8)
);

INSERT INTO Customers8_Q7 (customer_id8, customer_name8, city8) VALUES (1, 'Ahmed', 'New York');
INSERT INTO Customers8_Q7 (customer_id8, customer_name8, city8) VALUES (2, 'Sara', 'New York');
INSERT INTO Customers8_Q7 (customer_id8, customer_name8, city8) VALUES (3, 'Omar', 'Cairo');
INSERT INTO Customers8_Q7 (customer_id8, customer_name8, city8) VALUES (4, 'Mona', 'London');

INSERT INTO Orders8_Q7 (order_id8, customer_id8, order_date8) VALUES (101, 1, DATE '2026-01-10');
INSERT INTO Orders8_Q7 (order_id8, customer_id8, order_date8) VALUES (102, 2, DATE '2026-02-15');
INSERT INTO Orders8_Q7 (order_id8, customer_id8, order_date8) VALUES (103, 3, DATE '2026-03-20');

SELECT *
FROM Orders8_Q7
WHERE customer_id8 IN (
    SELECT customer_id8
    FROM Customers8_Q7
    WHERE city8 IN (
        SELECT c.city8
        FROM Customers8_Q7 c
        JOIN Orders8_Q7 o
            ON c.customer_id8 = o.customer_id8
        GROUP BY c.city8
        HAVING COUNT(DISTINCT c.customer_id8) > 1
    )
);


------------------------------------------------------------
-- QUESTION 8
-- Books written by authors with more than one book
------------------------------------------------------------

CREATE TABLE Authors8_Q8 (
    author_id8 NUMBER PRIMARY KEY,
    author_name8 VARCHAR2(100)
);

CREATE TABLE Books8_Q8 (
    book_id8 NUMBER PRIMARY KEY,
    book_title8 VARCHAR2(150),
    author_id8 NUMBER,
    CONSTRAINT fk_book_author8_q8 FOREIGN KEY (author_id8) REFERENCES Authors8_Q8(author_id8)
);

INSERT INTO Authors8_Q8 (author_id8, author_name8) VALUES (1, 'John Smith');
INSERT INTO Authors8_Q8 (author_id8, author_name8) VALUES (2, 'Emily Brown');
INSERT INTO Authors8_Q8 (author_id8, author_name8) VALUES (3, 'David Jones');

INSERT INTO Books8_Q8 (book_id8, book_title8, author_id8) VALUES (101, 'Oracle Basics', 1);
INSERT INTO Books8_Q8 (book_id8, book_title8, author_id8) VALUES (102, 'Advanced SQL', 1);
INSERT INTO Books8_Q8 (book_id8, book_title8, author_id8) VALUES (103, 'Java Basics', 2);
INSERT INTO Books8_Q8 (book_id8, book_title8, author_id8) VALUES (104, 'Database Design', 3);
INSERT INTO Books8_Q8 (book_id8, book_title8, author_id8) VALUES (105, 'Data Modelling', 3);

SELECT book_title8, author_id8
FROM Books8_Q8
WHERE author_id8 IN (
    SELECT author_id8
    FROM Books8_Q8
    GROUP BY author_id8
    HAVING COUNT(*) > 1
);


------------------------------------------------------------
-- QUESTION 9
-- Students enrolled in courses taught by Dr. Smith
------------------------------------------------------------

CREATE TABLE Professors8_Q9 (
    professor_id8 NUMBER PRIMARY KEY,
    professor_name8 VARCHAR2(100)
);

CREATE TABLE Courses8_Q9 (
    course_id8 NUMBER PRIMARY KEY,
    course_name8 VARCHAR2(100),
    professor_id8 NUMBER,
    CONSTRAINT fk_course_professor8_q9 FOREIGN KEY (professor_id8) REFERENCES Professors8_Q9(professor_id8)
);

CREATE TABLE Students8_Q9 (
    student_id8 NUMBER PRIMARY KEY,
    student_name8 VARCHAR2(100)
);

CREATE TABLE Enrollments8_Q9 (
    enrollment_id8 NUMBER PRIMARY KEY,
    student_id8 NUMBER,
    course_id8 NUMBER,
    CONSTRAINT fk_enrollment_student8_q9 FOREIGN KEY (student_id8) REFERENCES Students8_Q9(student_id8),
    CONSTRAINT fk_enrollment_course8_q9 FOREIGN KEY (course_id8) REFERENCES Courses8_Q9(course_id8)
);

INSERT INTO Professors8_Q9 (professor_id8, professor_name8) VALUES (1, 'Dr. Smith');
INSERT INTO Professors8_Q9 (professor_id8, professor_name8) VALUES (2, 'Dr. Ali');

INSERT INTO Courses8_Q9 (course_id8, course_name8, professor_id8) VALUES (101, 'Oracle SQL', 1);
INSERT INTO Courses8_Q9 (course_id8, course_name8, professor_id8) VALUES (102, 'Database Design', 1);
INSERT INTO Courses8_Q9 (course_id8, course_name8, professor_id8) VALUES (103, 'Java', 2);

INSERT INTO Students8_Q9 (student_id8, student_name8) VALUES (201, 'Ahmed');
INSERT INTO Students8_Q9 (student_id8, student_name8) VALUES (202, 'Sara');
INSERT INTO Students8_Q9 (student_id8, student_name8) VALUES (203, 'Omar');

INSERT INTO Enrollments8_Q9 (enrollment_id8, student_id8, course_id8) VALUES (1, 201, 101);
INSERT INTO Enrollments8_Q9 (enrollment_id8, student_id8, course_id8) VALUES (2, 202, 102);
INSERT INTO Enrollments8_Q9 (enrollment_id8, student_id8, course_id8) VALUES (3, 203, 103);

SELECT student_name8
FROM Students8_Q9
WHERE student_id8 IN (
    SELECT student_id8
    FROM Enrollments8_Q9
    WHERE course_id8 IN (
        SELECT c.course_id8
        FROM Courses8_Q9 c
        JOIN Professors8_Q9 p
            ON c.professor_id8 = p.professor_id8
        WHERE p.professor_name8 = 'Dr. Smith'
    )
);


------------------------------------------------------------
-- QUESTION 10
-- Employees whose salary matches any salary
-- in department 30
------------------------------------------------------------

CREATE TABLE Employees8_Q10 (
    employee_id8 NUMBER PRIMARY KEY,
    employee_name8 VARCHAR2(100),
    salary8 NUMBER(10,2),
    department_id8 NUMBER
);

INSERT INTO Employees8_Q10 (employee_id8, employee_name8, salary8, department_id8) VALUES (1, 'Ahmed', 5000, 30);
INSERT INTO Employees8_Q10 (employee_id8, employee_name8, salary8, department_id8) VALUES (2, 'Sara', 7000, 30);
INSERT INTO Employees8_Q10 (employee_id8, employee_name8, salary8, department_id8) VALUES (3, 'Omar', 5000, 10);
INSERT INTO Employees8_Q10 (employee_id8, employee_name8, salary8, department_id8) VALUES (4, 'Mona', 9000, 20);
INSERT INTO Employees8_Q10 (employee_id8, employee_name8, salary8, department_id8) VALUES (5, 'Ali', 7000, 20);

SELECT employee_name8, salary8, department_id8
FROM Employees8_Q10
WHERE salary8 IN (
    SELECT salary8
    FROM Employees8_Q10
    WHERE department_id8 = 30
);