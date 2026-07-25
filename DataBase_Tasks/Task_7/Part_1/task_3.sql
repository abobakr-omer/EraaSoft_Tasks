------------------------------------------------------------
-- 1. Employee names and manager names
------------------------------------------------------------

CREATE TABLE Employees_Manager3 (
    employee_id3 NUMBER PRIMARY KEY,
    name3 VARCHAR2(100),
    manager_id3 NUMBER,

    CONSTRAINT fk_employee_manager3
        FOREIGN KEY (manager_id3)
        REFERENCES Employees_Manager3(employee_id3)
);

SELECT e.name3 AS employee_name,
       m.name3 AS manager_name
FROM Employees_Manager3 e
JOIN Employees_Manager3 m
ON e.manager_id3 = m.employee_id3;


------------------------------------------------------------
-- 2. Customer names and salesperson names
------------------------------------------------------------

CREATE TABLE Salespeople3 (
    employee_id3 NUMBER PRIMARY KEY,
    name3 VARCHAR2(100)
);

CREATE TABLE Customers3 (
    customer_id3 NUMBER PRIMARY KEY,
    name3 VARCHAR2(100),
    salesperson_id3 NUMBER,

    CONSTRAINT fk_customer_salesperson3
        FOREIGN KEY (salesperson_id3)
        REFERENCES Salespeople3(employee_id3)
);

SELECT c.name3,s.name3 FROM Customers3 c JOIN Salespeople3 s
ON c.employee_id3 = s.salesperson_id3;
------------------------------------------------------------
-- 3. Order IDs and product IDs
-- Both tables contain order_id3
------------------------------------------------------------

CREATE TABLE Orders3 (
    order_id3 NUMBER PRIMARY KEY,
    order_date3 DATE
);

CREATE TABLE Order_Details3 (
    detail_id3 NUMBER PRIMARY KEY,
    order_id3 NUMBER,
    product_id3 NUMBER,
    quantity3 NUMBER,

    CONSTRAINT fk_order_detail3
        FOREIGN KEY (order_id3)
        REFERENCES Orders3(order_id3)
);

SELECT o.order_id3,
       od.product_id3
FROM Orders3 o
JOIN Order_Details3 od
    ON o.order_id3 = od.order_id3;


------------------------------------------------------------
-- 4. Student names and instructor names
-- Both tables contain name3
------------------------------------------------------------

CREATE TABLE Instructors3 (
    instructor_id3 NUMBER PRIMARY KEY,
    name3 VARCHAR2(100)
);

CREATE TABLE Students3 (
    student_id3 NUMBER PRIMARY KEY,
    name3 VARCHAR2(100),
    instructor_id3 NUMBER,

    CONSTRAINT fk_student_instructor3
        FOREIGN KEY (instructor_id3)
        REFERENCES Instructors3(instructor_id3)
);

SELECT s.name3 AS student_name,
       i.name3 AS instructor_name
FROM Students3 s
JOIN Instructors3 i
    ON s.instructor_id3 = i.instructor_id3;


------------------------------------------------------------
-- 5. Employee salaries and department budgets
-- Both tables contain amount3
------------------------------------------------------------

CREATE TABLE Departments_Budget3 (
    department_id3 NUMBER PRIMARY KEY,
    department_name3 VARCHAR2(100),
    amount3 NUMBER(12,2)
);

CREATE TABLE Employees_Salary3 (
    employee_id3 NUMBER PRIMARY KEY,
    employee_name3 VARCHAR2(100),
    amount3 NUMBER(10,2),
    department_id3 NUMBER,

    CONSTRAINT fk_employee_budget_department3
        FOREIGN KEY (department_id3)
        REFERENCES Departments_Budget3(department_id3)
);

SELECT e.employee_name3,
       e.amount3 AS employee_salary,
       d.amount3 AS department_budget
FROM Employees_Salary3 e
JOIN Departments_Budget3 d
    ON e.department_id3 = d.department_id3;


------------------------------------------------------------
-- 6. Project names and task names
-- Both tables contain name3
------------------------------------------------------------

CREATE TABLE Projects3 (
    project_id3 NUMBER PRIMARY KEY,
    name3 VARCHAR2(100)
);

CREATE TABLE Tasks3 (
    task_id3 NUMBER PRIMARY KEY,
    name3 VARCHAR2(100),
    project_id3 NUMBER,

    CONSTRAINT fk_task_project3
        FOREIGN KEY (project_id3)
        REFERENCES Projects3(project_id3)
);

SELECT p.name3 AS project_name,
       t.name3 AS task_name
FROM Projects3 p
JOIN Tasks3 t
    ON p.project_id3 = t.project_id3;


------------------------------------------------------------
-- 7. Course dates and exam dates
-- Both tables contain date3
------------------------------------------------------------

CREATE TABLE Courses3 (
    course_id3 NUMBER PRIMARY KEY,
    course_name3 VARCHAR2(100),
    date3 DATE
);

CREATE TABLE Exams3 (
    exam_id3 NUMBER PRIMARY KEY,
    course_id3 NUMBER,
    date3 DATE,

    CONSTRAINT fk_exam_course3
        FOREIGN KEY (course_id3)
        REFERENCES Courses3(course_id3)
);

SELECT c.course_name3,
       c.date3 AS course_date,
       e.date3 AS exam_date
FROM Courses3 c
JOIN Exams3 e
    ON c.course_id3 = e.course_id3;


------------------------------------------------------------
-- 8. Product names and category names
-- Both tables contain name3
------------------------------------------------------------

CREATE TABLE Categories3 (
    category_id3 NUMBER PRIMARY KEY,
    name3 VARCHAR2(100)
);

CREATE TABLE Products3 (
    product_id3 NUMBER PRIMARY KEY,
    name3 VARCHAR2(100),
    category_id3 NUMBER,

    CONSTRAINT fk_product_category3
        FOREIGN KEY (category_id3)
        REFERENCES Categories3(category_id3)
);

SELECT p.name3 AS product_name,
       c.name3 AS category_name
FROM Products3 p
JOIN Categories3 c
    ON p.category_id3 = c.category_id3;


------------------------------------------------------------
-- 9. Book titles and publisher names
-- Both tables contain name3
------------------------------------------------------------

CREATE TABLE Publishers3 (
    publisher_id3 NUMBER PRIMARY KEY,
    name3 VARCHAR2(100)
);

CREATE TABLE Books3 (
    book_id3 NUMBER PRIMARY KEY,
    name3 VARCHAR2(150),
    publisher_id3 NUMBER,

    CONSTRAINT fk_book_publisher3
        FOREIGN KEY (publisher_id3)
        REFERENCES Publishers3(publisher_id3)
);

SELECT b.name3 AS book_title,
       p.name3 AS publisher_name
FROM Books3 b
JOIN Publishers3 p
    ON b.publisher_id3 = p.publisher_id3;


------------------------------------------------------------
-- 10. Employee names and department locations
-- Both tables contain location3
------------------------------------------------------------

CREATE TABLE Departments_Location3 (
    department_id3 NUMBER PRIMARY KEY,
    department_name3 VARCHAR2(100),
    location3 VARCHAR2(100)
);

CREATE TABLE Employees_Location3 (
    employee_id3 NUMBER PRIMARY KEY,
    employee_name3 VARCHAR2(100),
    location3 VARCHAR2(100),
    department_id3 NUMBER,

    CONSTRAINT fk_employee_location_department3
        FOREIGN KEY (department_id3)
        REFERENCES Departments_Location3(department_id3)
);

SELECT e.employee_name3,
       d.location3 AS department_location
FROM Employees_Location3 e
JOIN Departments_Location3 d
    ON e.department_id3 = d.department_id3;