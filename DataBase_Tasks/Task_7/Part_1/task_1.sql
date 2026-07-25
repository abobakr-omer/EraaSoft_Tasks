---------------- 1. Employees and Departments ----------------

CREATE TABLE Departments2 (
    department_id2 NUMBER PRIMARY KEY,
    department_name2 VARCHAR2(50)
);

CREATE TABLE Employees2 (
    employee_id2 NUMBER PRIMARY KEY,
    employee_name2 VARCHAR2(50),
    department_id2 NUMBER,
    CONSTRAINT fk_emp_department2
        FOREIGN KEY (department_id2)
        REFERENCES Departments2(department_id2)
);

SELECT employee_name2, department_name2
FROM Employees2
NATURAL JOIN Departments2;


---------------- 2. Orders and Customers ----------------

CREATE TABLE Customers2 (
    customer_id2 NUMBER PRIMARY KEY,
    customer_name2 VARCHAR2(50)
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
NATURAL JOIN Customers2;


---------------- 3. Students and Courses ----------------

CREATE TABLE Courses2 (
    course_id2 NUMBER PRIMARY KEY,
    course_name2 VARCHAR2(50)
);

CREATE TABLE Students2 (
    student_id2 NUMBER PRIMARY KEY,
    student_name2 VARCHAR2(50)
);

CREATE TABLE Enrollments2 (
    student_id2 NUMBER,
    course_id2 NUMBER,

    CONSTRAINT pk_enrollments2
        PRIMARY KEY (student_id2, course_id2),

    CONSTRAINT fk_enrollment_student2
        FOREIGN KEY (student_id2)
        REFERENCES Students2(student_id2),

    CONSTRAINT fk_enrollment_course2
        FOREIGN KEY (course_id2)
        REFERENCES Courses2(course_id2)
);

SELECT student_name2, course_name2
FROM Students2
NATURAL JOIN Enrollments2
NATURAL JOIN Courses2;


---------------- 4. Projects and Employees ----------------

CREATE TABLE Projects2 (
    project_id2 NUMBER PRIMARY KEY,
    project_name2 VARCHAR2(50)
);

CREATE TABLE Employee_Projects2 (
    employee_id2 NUMBER,
    project_id2 NUMBER,

    CONSTRAINT pk_employee_projects2
        PRIMARY KEY (employee_id2, project_id2),

    CONSTRAINT fk_ep_employee2
        FOREIGN KEY (employee_id2)
        REFERENCES Employees2(employee_id2),

    CONSTRAINT fk_ep_project2
        FOREIGN KEY (project_id2)
        REFERENCES Projects2(project_id2)
);

SELECT project_name2, employee_name2
FROM Projects2
NATURAL JOIN Employee_Projects2
NATURAL JOIN Employees2;


---------------- 5. Invoices and Products ----------------

CREATE TABLE Products2 (
    product_id2 NUMBER PRIMARY KEY,
    product_name2 VARCHAR2(50),
    price2 NUMBER(10,2)
);

CREATE TABLE Invoices2 (
    invoice_id2 NUMBER PRIMARY KEY,
    invoice_date2 DATE
);

CREATE TABLE Invoice_Details2 (
    invoice_id2 NUMBER,
    product_id2 NUMBER,
    quantity2 NUMBER,

    CONSTRAINT pk_invoice_details2
        PRIMARY KEY (invoice_id2, product_id2),

    CONSTRAINT fk_detail_invoice2
        FOREIGN KEY (invoice_id2)
        REFERENCES Invoices2(invoice_id2),

    CONSTRAINT fk_detail_product2
        FOREIGN KEY (product_id2)
        REFERENCES Products2(product_id2)
);

SELECT invoice_id2, product_name2, quantity2
FROM Invoices2
NATURAL JOIN Invoice_Details2
NATURAL JOIN Products2;


---------------- 6. Books and Authors ----------------

CREATE TABLE Authors2 (
    author_id2 NUMBER PRIMARY KEY,
    author_name2 VARCHAR2(50)
);

CREATE TABLE Books2 (
    book_id2 NUMBER PRIMARY KEY,
    book_name2 VARCHAR2(100),
    author_id2 NUMBER,
    CONSTRAINT fk_book_author2
        FOREIGN KEY (author_id2)
        REFERENCES Authors2(author_id2)
);

SELECT book_name2, author_name2
FROM Books2
NATURAL JOIN Authors2;


---------------- 7. Classes and Instructors ----------------

CREATE TABLE Instructors2 (
    instructor_id2 NUMBER PRIMARY KEY,
    instructor_name2 VARCHAR2(50)
);

CREATE TABLE Classes2 (
    class_id2 NUMBER PRIMARY KEY,
    class_time2 VARCHAR2(30),
    instructor_id2 NUMBER,
    CONSTRAINT fk_class_instructor2
        FOREIGN KEY (instructor_id2)
        REFERENCES Instructors2(instructor_id2)
);

SELECT class_id2, class_time2, instructor_name2
FROM Classes2
NATURAL JOIN Instructors2;


---------------- 8. Suppliers and Products ----------------

CREATE TABLE Suppliers2 (
    supplier_id2 NUMBER PRIMARY KEY,
    supplier_name2 VARCHAR2(50)
);

CREATE TABLE Product_Suppliers2 (
    product_id2 NUMBER,
    supplier_id2 NUMBER,

    CONSTRAINT pk_product_suppliers2
        PRIMARY KEY (product_id2, supplier_id2),

    CONSTRAINT fk_ps_product2
        FOREIGN KEY (product_id2)
        REFERENCES Products2(product_id2),

    CONSTRAINT fk_ps_supplier2
        FOREIGN KEY (supplier_id2)
        REFERENCES Suppliers2(supplier_id2)
);

SELECT supplier_name2, product_name2
FROM Suppliers2
NATURAL JOIN Product_Suppliers2
NATURAL JOIN Products2;


---------------- 9. Orders and Shipping ----------------

CREATE TABLE Shipping2 (
    shipping_id2 NUMBER PRIMARY KEY,
    order_id2 NUMBER,
    shipping_address2 VARCHAR2(100),
    shipping_date2 DATE,
    CONSTRAINT fk_shipping_order2
        FOREIGN KEY (order_id2)
        REFERENCES Orders2(order_id2)
);

SELECT order_id2, customer_name2, shipping_address2
FROM Customers2
NATURAL JOIN Orders2
NATURAL JOIN Shipping2;


---------------- 10. Employees and Jobs ----------------

CREATE TABLE Jobs2 (
    job_id2 NUMBER PRIMARY KEY,
    job_title2 VARCHAR2(50)
);

ALTER TABLE Employees2
ADD job_id2 NUMBER;

ALTER TABLE Employees2
ADD CONSTRAINT fk_employee_job2
FOREIGN KEY (job_id2)
REFERENCES Jobs2(job_id2);

SELECT employee_name2, job_title2
FROM Employees2
NATURAL JOIN Jobs2;