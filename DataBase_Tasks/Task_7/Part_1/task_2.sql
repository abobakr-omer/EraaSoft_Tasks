---------------- 1. Employees and Departments ----------------

CREATE TABLE Departments2 (
    department_id2 NUMBER PRIMARY KEY,
    department_name2 VARCHAR2(100)
);

CREATE TABLE Employees2 (
    employee_id2 NUMBER PRIMARY KEY,
    employee_name2 VARCHAR2(100),
    department_id2 NUMBER,
    CONSTRAINT fk_employee_department2
        FOREIGN KEY (department_id2)
        REFERENCES Departments2(department_id2)
);

SELECT employee_name2,department_name2 FROM Employees2 JOIN Departments2 USING(department_id2);


---------------- 2. Orders and Customers ----------------

CREATE TABLE Customers2 (
    customer_id2 NUMBER PRIMARY KEY,
    customer_name2 VARCHAR2(100)
);

CREATE TABLE Orders2 (
    order_id2 NUMBER PRIMARY KEY,
    order_date2 DATE,
    customer_id2 NUMBER,
    CONSTRAINT fk_order_customer2
        FOREIGN KEY (customer_id2)
        REFERENCES Customers2(customer_id2)
);

SELECT order_id2, order_date2, customer_name2
FROM Orders2
JOIN Customers2 USING (customer_id2);

---------------- 3. Products and Suppliers ----------------

CREATE TABLE Suppliers2 (
    supplier_id2 NUMBER PRIMARY KEY,
    supplier_name2 VARCHAR2(100)
);

CREATE TABLE Products2 (
    product_id2 NUMBER PRIMARY KEY,
    product_name2 VARCHAR2(100),
    price2 NUMBER(10,2),
    supplier_id2 NUMBER,
    CONSTRAINT fk_product_supplier2
        FOREIGN KEY (supplier_id2)
        REFERENCES Suppliers2(supplier_id2)
);

SELECT product_name2, supplier_name2
FROM Products2
JOIN Suppliers2 USING (supplier_id2);

---------------- 4. Students and Enrollments ----------------

CREATE TABLE Students2 (
    student_id2 NUMBER PRIMARY KEY,
    student_name2 VARCHAR2(100)
);

CREATE TABLE Enrollments2 (
    enrollment_id2 NUMBER PRIMARY KEY,
    student_id2 NUMBER,
    course_title2 VARCHAR2(100),
    CONSTRAINT fk_enrollment_student2
        FOREIGN KEY (student_id2)
        REFERENCES Students2(student_id2)
);

SELECT student_name2, course_title2
FROM Students2
JOIN Enrollments2 USING (student_id2);

---------------- 5. Invoices and Products ----------------

CREATE TABLE Invoices2 (
    invoice_id2 NUMBER PRIMARY KEY,
    invoice_number2 VARCHAR2(30),
    product_id2 NUMBER,
    quantity2 NUMBER,
    CONSTRAINT fk_invoice_product2
        FOREIGN KEY (product_id2)
        REFERENCES Products2(product_id2)
);

SELECT invoice_number2, product_name2
FROM Invoices2
JOIN Products2 USING (product_id2);


---------------- 6. Projects and Project Employees ----------------

CREATE TABLE Projects2 (
    project_id2 NUMBER PRIMARY KEY,
    project_name2 VARCHAR2(100)
);

CREATE TABLE Project_Employees2 (
    project_employee_id2 NUMBER PRIMARY KEY,
    project_id2 NUMBER,
    employee_name2 VARCHAR2(100),
    CONSTRAINT fk_project_employee2
        FOREIGN KEY (project_id2)
        REFERENCES Projects2(project_id2)
);

SELECT project_name2, employee_name2
FROM Projects2
JOIN Project_Employees2 USING (project_id2);
---------------- 7. Authors and Books ----------------

CREATE TABLE Authors2 (
    author_id2 NUMBER PRIMARY KEY,
    author_name2 VARCHAR2(100)
);

CREATE TABLE Books2 (
    book_id2 NUMBER PRIMARY KEY,
    book_title2 VARCHAR2(100),
    author_id2 NUMBER,
    CONSTRAINT fk_book_author2
        FOREIGN KEY (author_id2)
        REFERENCES Authors2(author_id2)
);

SELECT author_name2, book_title2
FROM Authors2
JOIN Books2 USING (author_id2);


---------------- 8. Sales Orders and Employees ----------------

CREATE TABLE Sales_Orders2 (
    sales_order_id2 NUMBER PRIMARY KEY,
    order_details2 VARCHAR2(200),
    employee_id2 NUMBER,
    CONSTRAINT fk_sales_employee2
        FOREIGN KEY (employee_id2)
        REFERENCES Employees2(employee_id2)
);

SELECT order_details2, employee_name2
FROM Sales_Orders2
JOIN Employees2 USING (employee_id2);


---------------- 9. Course Schedules and Instructors ----------------

CREATE TABLE Instructors2 (
    instructor_id2 NUMBER PRIMARY KEY,
    instructor_name2 VARCHAR2(100)
);

CREATE TABLE Course_Schedules2 (
    schedule_id2 NUMBER PRIMARY KEY,
    course_name2 VARCHAR2(100),
    schedule_time2 VARCHAR2(50),
    instructor_id2 NUMBER,
    CONSTRAINT fk_schedule_instructor2
        FOREIGN KEY (instructor_id2)
        REFERENCES Instructors2(instructor_id2)
);

SELECT course_name2, schedule_time2, instructor_name2
FROM Course_Schedules2
JOIN Instructors2 USING (instructor_id2);



---------------- 10. Transactions and Accounts ----------------

CREATE TABLE Accounts2 (
    account_id2 NUMBER PRIMARY KEY,
    account_holder_name2 VARCHAR2(100)
);

CREATE TABLE Transactions2 (
    transaction_id2 NUMBER PRIMARY KEY,
    transaction_date2 DATE,
    amount2 NUMBER(10,2),
    account_id2 NUMBER,
    CONSTRAINT fk_transaction_account2
        FOREIGN KEY (account_id2)
        REFERENCES Accounts2(account_id2)
);

SELECT transaction_id2, transaction_date2, amount2,
       account_holder_name2
FROM Transactions2
JOIN Accounts2 USING (account_id2);