------------------------------------------------------------
-- 1. CREATE ADMIN USER
-- Run as SYS or SYSTEM
------------------------------------------------------------

CREATE USER admin IDENTIFIED BY Admin123;

GRANT CREATE SESSION TO admin;
GRANT CREATE TABLE TO admin;

ALTER USER admin QUOTA 10M ON USERS;


------------------------------------------------------------
-- 2. CONNECT AS ADMIN AND CREATE STUDENT TABLE
------------------------------------------------------------

CONNECT admin/Admin123;

CREATE TABLE Student (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100)
);


------------------------------------------------------------
-- GRANT HR INSERT, SELECT, UPDATE AND DELETE
------------------------------------------------------------

GRANT INSERT, SELECT, UPDATE, DELETE
ON Student
TO HR;


------------------------------------------------------------
-- 3. CONNECT TO HR
-- Replace hr_password with the real HR password
------------------------------------------------------------

CONNECT hr/hr_password;


------------------------------------------------------------
-- INSERT
------------------------------------------------------------

INSERT INTO admin.Student (id, name) VALUES (1, 'Ahmed');
INSERT INTO admin.Student (id, name) VALUES (2, 'Sara');

COMMIT;


------------------------------------------------------------
-- SELECT
------------------------------------------------------------

SELECT *
FROM admin.Student;


------------------------------------------------------------
-- UPDATE
------------------------------------------------------------

UPDATE admin.Student
SET name = 'Ahmed Ali'
WHERE id = 1;

COMMIT;


------------------------------------------------------------
-- DELETE
------------------------------------------------------------

DELETE FROM admin.Student
WHERE id = 2;

COMMIT;


------------------------------------------------------------
-- DISPLAY FINAL DATA
------------------------------------------------------------

SELECT *
FROM admin.Student;


------------------------------------------------------------
-- 4. REVOKE ALL TABLE PRIVILEGES FROM HR
-- Connect as ADMIN
------------------------------------------------------------

CONNECT admin/Admin123;

REVOKE ALL
ON Student
FROM HR;


------------------------------------------------------------
-- REVOKE ADMIN SYSTEM PRIVILEGES
-- Run as SYS or SYSTEM
------------------------------------------------------------

CONNECT system/system_password;

REVOKE CREATE TABLE FROM admin;
REVOKE CREATE SESSION FROM admin;

ALTER USER admin QUOTA 0 ON USERS;