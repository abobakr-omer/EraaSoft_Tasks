CREATE TABLE Manager(
     id number(3) NOT NULL,
     name VARCHAR2(50),
     age number(3),
     birth_date DATE,
     address VARCHAR2(100)
);

SELECT * FROM Manager;

ALTER TABLE Manager DROP COLUMN address;

ALTER TABLE Manager ADD (
      city_address VARCHAR2(100),
      street number(5)
);

ALTER TABLE Manager RENAME COLUMN name TO full_name;


ALTER TABLE Manager READ ONLY; 


CREATE TABLE Owner AS SELECT id,name,birth_date FROM Manager;

SELECT * FROM Owner;


RENAME  Manager TO Master;

SELECT * FROM Master;

DROP TABLE Manager;

DROP TABLE Owner;