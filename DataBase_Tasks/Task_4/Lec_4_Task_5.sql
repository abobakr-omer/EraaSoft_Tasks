SELECT NAME,MARKS , DECODE(LEAST(FLOOR(MARKS / 10), 9),
        				9, 'A',
        				8, 'B',
        				7, 'C',
        				'F' ) AS GRADE FROM STUDENTS1;


CREATE TABLE ORDERS (
    order_id NUMBER,
    status   CHAR(1)
);

SELECT * FROM ORDERS;

INSERT INTO ORDERS (order_id, status) VALUES (1, 'P');
INSERT INTO ORDERS (order_id, status) VALUES (2, 'S');
INSERT INTO ORDERS (order_id, status) VALUES (3, 'D');

SELECT order_id,status,DECODE(STATUS,
					'P','Pending',
					'S','Shipped',
					'D','Delivered',
					'Unknown') AS status_full_form FROM ORDERS;