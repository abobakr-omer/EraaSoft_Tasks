CREATE TABLE CUSTOMERS(
	FULL_NAME VARCHAR(100)	
);

INSERT INTO CUSTOMERS(FULL_NAME) VALUES ('  AHMED OMAR  ');
INSERT INTO CUSTOMERS(FULL_NAME) VALUES ('  BAKR OMAR  ');
INSERT INTO CUSTOMERS(FULL_NAME) VALUES ('  MOHAMED OMAR  ');
INSERT INTO CUSTOMERS(FULL_NAME) VALUES ('  #MOHAMED OMAR*  ');


SELECT S.*,TRIM( BOTH ' ' FROM FULL_NAME) TRM FROM CUSTOMERS S;

SELECT S.*,LTRIM(FULL_NAME,' ') TRM FROM CUSTOMERS S;

SELECT S.*,RTRIM(FULL_NAME,' ') TRM FROM CUSTOMERS S;

SELECT S.*,RTRIM(LTRIM(FULL_NAME,' #'),' *') TRM FROM CUSTOMERS S;

---------------------------------------------------------------------------------------------------
SELECT REPLACE('promotion','o',0) RPL FROM DUAL;

SELECT REPLACE('This is a basic course','basic','advanced') RPL FROM DUAL;

CREATE TABLE departments1 (
    dept_name VARCHAR2(50)
);

INSERT INTO departments1 (dept_name) VALUES ('HR');
INSERT INTO departments1 (dept_name) VALUES ('IT');
INSERT INTO departments1 (dept_name) VALUES ('Finance');

SELECT S.*,LPAD(dept_name,15,'*') FROM departments1 S;

SELECT S.*,RPAD(dept_name,15,'-') FROM departments1 S;
---------------------------------------------------------------------------------------------------
SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL;

SELECT TO_CHAR(SYSDATE,'Day, Month YYYY') FROM DUAL;

SELECT SALARY,TO_CHAR(SALARY,'FM99,999,99') FROM EMPLOYEES;

SELECT SALARY,TO_CHAR(SALARY,'FM$99,999,99') FROM EMPLOYEES;

SELECT TO_CHAR(SYSDATE,'YYYY/MM/DD HH24:MI:SS') FROM DUAL;
-----------------------------------------------------------------------------------------------
CREATE TABLE STUDENTS1(
	NAME VARCHAR2(100),
	MARKS NUMBER
);


INSERT INTO STUDENTS1(NAME,MARKS) VALUES('AHMED',99);
INSERT INTO STUDENTS1(NAME,MARKS) VALUES('BAKR',88);
INSERT INTO STUDENTS1(NAME,MARKS) VALUES('OMAR',76);
INSERT INTO STUDENTS1(NAME,MARKS) VALUES('MOHAMED',60);
INSERT INTO STUDENTS1(NAME,MARKS) VALUES('IBRAHIM',65);

SELECT * FROM STUDENTS1;

SELECT NAME,MARKS ,CASE
        	WHEN MARKS >= 90 THEN 'A'
        	WHEN MARKS >= 80 THEN 'B'
        	WHEN MARKS >= 70 THEN 'C'
        	ELSE 'F'
        END AS "GRADE"
		FROM STUDENTS1;
----------------------------------------------------------------------------------------
SELECT NAME,MARKS , DECODE(LEAST(FLOOR(MARKS / 10), 9),
        				9, 'A',
        				8, 'B',
        				7, 'C',
        				'F' ) AS GRADE FROM STUDENTS1;


CREATE TABLE status_log (
    status_code CHAR(1)
);

INSERT INTO status_log (status_code) VALUES ('N');
INSERT INTO status_log (status_code) VALUES ('I');
INSERT INTO status_log (status_code) VALUES ('C');

SELECT S.*,DECODE(status_code,
					'N','New',
					'I','In Progress',
					'C','Completed',
					'UnKnwon') AS new_status_log FROM status_log S;

CREATE TABLE products (
    product_name VARCHAR2(50),
    quantity NUMBER
);

INSERT INTO products (product_name, quantity) VALUES ('Laptop', 5);
INSERT INTO products (product_name, quantity) VALUES ('Mouse', 0);
INSERT INTO products (product_name, quantity) VALUES ('Keyboard', 10);

SELECT product_name,quantity,
						DECODE(quantity,
						0,'Out of Stock',
						'Available') AS stock_status FROM products;

SELECT FIRST_NAME ,DEPARTMENT_ID,
							DECODE(DEPARTMENT_ID,
							'HR',500,
							'IT',1000,
							'Sales',1500,
							300) AS BOUNS FROM employees;