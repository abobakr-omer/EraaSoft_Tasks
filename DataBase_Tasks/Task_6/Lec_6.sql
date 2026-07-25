CREATE TABLE managers(
    manager_id NUMBER,
    first_name VARCHAR2(50),
    salary NUMBER,
    email VARCHAR2(100),
    CONSTRAINT demo_check CHECK (salary > 100 AND salary < 50000 AND upper(email) LIKE '%.COM')
);

CREATE TABLE departments (
    department_id NUMBER PRIMARY KEY,
    department_name VARCHAR2(100)
);

ALTER TABLE managers ADD CONSTRAINT demo_check CHECK(salary >500);
ALTER TABLE managers ADD CONSTRAINT DEMO_PK PRIMARY KEY(manager_id);
ALTER TABLE managers ADD CONSTRAINT ID_UNIQUE UNIQUE(manager_id);
ALTER TABLE managers ADD EMAIL VARCHAR2(50);
ALTER TABLE managers ADD department_id NUMBER;
ALTER TABLE managers ADD CONSTRAINT FK_DEP_ID FOREIGN KEY(department_id) REFERENCES departments(department_id);
ALTER TABLE managers MODIFY first_name NOT NULL;
ALTER TABLE managers MODIFY first_name CONSTRAINT NOT_FIRST_NULL NOT NULL;
ALTER TABLE managers DROP CONSTRAINT NOT_FIRST_NULL;
ALTER TABLE managers DISABLE CONSTRAINT NOT_FIRST_NULL;
ALTER TABLE managers ENABLE CONSTRAINT NOT_FIRST_NULL;
-----✅ 1. CHECK Constraint (Code Samples)--------------------
CREATE TABLE EMPLOYEES(
    EMPLOYEE_ID NUMBER PRIMARY KEY,
    AGE NUMBER,
    CONSTRAINT check_age CHECK(AGE>=18)
);

CREATE TABLE STAFF(
    STAFF_ID NUMBER PRIMARY KEY,
    SALARY NUMBER,
    CONSTRAINT check_salary CHECK(SALARY>=3000 AND SALARY<=10000)
);

ALTER TABLE PRODUCTS ADD CONSTRAINT check_price CHECK(PRICE>0);


CREATE TABLE STUDENTS(
    STUDENT_ID NUMBER PRIMARY KEY,
    GRADE CHAR(1),
    CONSTRAINT check_grade CHECK (grade IN ('A', 'B', 'C', 'D', 'E', 'F'))
);

---------------🛠️ 2. Adding Constraints via ALTER TABLE (Code Samples)------------------------

CREATE TABLE Customers (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);

ALTER TABLE Customers MODIFY email VARCHAR(100) NOT NULL;

CREATE TABLE Users (
    id INT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(100)
);

ALTER TABLE Users ADD CONSTRAINT unique_username UNIQUE(username);

CREATE TABLE Orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    order_date DATE
);

ALTER TABLE Orders ADD CONSTRAINT fk_orders_customer FOREIGN KEY(customer_id) REFERENCES Customers(id);

CREATE TABLE Accounts (
    account_id INT PRIMARY KEY,
    account_name VARCHAR(100),
    balance DECIMAL(10, 2)
);

ALTER TABLE Accounts ADD CONSTRAINT check_balance CHECK(balance>=0);


CREATE TABLE Departments (
    dept_id INT,
    dept_name VARCHAR(100)
);

ALTER TABLE Departments ADD CONSTRAINT pk_departments PRIMARY KEY(dept_id);

-------------❌ 3. Dropping (Removing) Constraints (Code Samples)--------------------------
CREATE TABLE Employees (
    employee_id NUMBER PRIMARY KEY,
    employee_name VARCHAR2(100),
    salary NUMBER,
    CONSTRAINT chk_salary CHECK (salary BETWEEN 3000 AND 10000)
);

ALTER TABLE Employees DROP CONSTRAINT chk_salary;

CREATE TABLE Users (
    user_id NUMBER PRIMARY KEY,
    username VARCHAR2(50),
    email VARCHAR2(100),
    CONSTRAINT uq_users_email UNIQUE (email)
);
ALTER TABLE Users DROP CONSTRAINT uq_users_email;



CREATE TABLE Products (
    product_id NUMBER,
    product_name VARCHAR2(100),
    price NUMBER(10, 2),
    CONSTRAINT pk_products PRIMARY KEY (product_id)
);
ALTER TABLE Products DROP CONSTRAINT pk_products;


CREATE TABLE Customers (
    id NUMBER PRIMARY KEY,
    customer_name VARCHAR2(100)
);




CREATE TABLE Orders (
    order_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    order_date DATE,
    CONSTRAINT fk_order_customer FOREIGN KEY (customer_id)
        REFERENCES Customers(id)
);

ALTER TABLE Orders DROP CONSTRAINT fk_order_customer;

CREATE TABLE Contacts (
    contact_id NUMBER PRIMARY KEY,
    contact_name VARCHAR2(100),
    phone VARCHAR2(20) CONSTRAINT nn_contacts_phone NOT NULL
);

ALTER TABLE Contacts DROP CONSTRAINT nn_contacts_phone;
------✏️ 4. Renaming Constraints (Code Samples)-------------------
CREATE TABLE Students (
    student_id INT,
    student_name VARCHAR(100),
    age INT,
    CONSTRAINT chk_age CHECK (age >= 18)
);

ALTER TABLE Students RENAME chk_age TO check_min_age ;

CREATE TABLE Departments (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(100)
);

ALTER TABLE Employees  RENAME fk_emp_dept  TO fk_employee_department ;


CREATE TABLE Employees (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(100),
    dept_id INT,
    CONSTRAINT fk_emp_dept FOREIGN KEY (dept_id)
        REFERENCES Departments(dept_id)
);
ALTER TABLE Employees  RENAME fk_emp_dept  TO fk_employee_department ;



CREATE TABLE Users (
    id INT,
    username VARCHAR(50),
    email VARCHAR(100),
    CONSTRAINT old_pk_users PRIMARY KEY (id),
    CONSTRAINT old_uk_username UNIQUE (username)
);

ALTER TABLE Users  RENAME old_pk_users  TO pk_users_id ;

ALTER TABLE Users  RENAME old_uk_username   TO uk_user_name ;

------------- SQL Server Syntax -------------

EXEC sp_rename
    'dbo.table_name.old_constraint_name',
    'new_constraint_name',
    'OBJECT';


------------- PostgreSQL Syntax -------------

ALTER TABLE table_name
RENAME CONSTRAINT old_constraint_name TO new_constraint_name;

---------------- 5. DISABLING CONSTRAINTS ----------------

-- Disable foreign key
ALTER TABLE Orders
DISABLE CONSTRAINT fk_customer_order;

-- Disable check constraint
ALTER TABLE Accounts
DISABLE CONSTRAINT check_balance;

-- Disable primary key
ALTER TABLE Departments
DISABLE CONSTRAINT pk_departments;




---------------- 6. ENABLING CONSTRAINTS ----------------

-- Enable foreign key
ALTER TABLE Orders
ENABLE CONSTRAINT fk_customer_order;

-- Enable check constraint
ALTER TABLE Accounts
ENABLE CONSTRAINT check_balance;

-- Enable salary check
ALTER TABLE Staff
ENABLE CONSTRAINT check_salary;

-- Enable primary key
ALTER TABLE Departments
ENABLE CONSTRAINT pk_departments;



---------------- ENABLE ONLY IF DISABLED ----------------

BEGIN
    FOR c IN (
        SELECT constraint_name
        FROM user_constraints
        WHERE table_name = 'ORDERS'
          AND constraint_name = 'FK_CUSTOMER_ORDER'
          AND status = 'DISABLED'
    )
    LOOP
        EXECUTE IMMEDIATE
        'ALTER TABLE Orders ENABLE CONSTRAINT '
        || c.constraint_name;
    END LOOP;
END;
/
