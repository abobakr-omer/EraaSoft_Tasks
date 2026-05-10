CREATE TABLE Doctor (
    id      NUMBER(3) NOT NULL,
    name    VARCHAR2(50),
    salary  NUMBER(10,2),
    address VARCHAR2(100)
);

INSERT INTO Doctor (id, name, salary, address)
VALUES (1, 'Ahmed', 20000, 'Cairo');

INSERT INTO Doctor (id, name, salary, address)
VALUES (2, 'Mona', 18000, 'Giza');

INSERT INTO Doctor (id, name, salary, address)
VALUES (3, 'Ali', 22000, 'Alexandria');

INSERT INTO Doctor (id, name, salary, address)
VALUES (4, 'Sara', 19500, 'Mansoura');

INSERT INTO Doctor (id, name, salary, address)
VALUES (5, 'Omar', 25000, 'Tanta');

INSERT INTO Doctor (id, name, salary, address)
VALUES (6, 'Nada', 21000, 'Cairo');

INSERT INTO Doctor (id, name, salary, address)
VALUES (7, 'Hassan', 17000, 'Aswan');

INSERT INTO Doctor (id, name, salary, address)
VALUES (8, 'Laila', 23000, 'Luxor');

INSERT INTO Doctor (id, name, salary, address)
VALUES (9, 'Youssef', 24000, 'Zagazig');

INSERT INTO Doctor (id, name, salary, address)
VALUES (10, 'Salma', 20000, 'Ismailia');

SELECT * FROM Doctor;

UPDATE Doctor SET salary=20000 WHERE id=3;

DELETE FROM Doctor WHERE id=9;

SELECT name || '-salary: ' || salary AS "Doctor Salary" FROM Doctor;

SELECT salary*2 AS double_salary FROM Doctor;

SELECT *
FROM Doctor
WHERE salary IN (1000, 2000, 3000);

RENAME  Doctor TO PRD_DOCTOR;

SELECT * FROM PRD_DOCTOR;