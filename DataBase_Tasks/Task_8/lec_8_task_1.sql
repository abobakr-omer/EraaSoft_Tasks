------------------------------------------------------------
-- 1. NATURAL JOIN
------------------------------------------------------------

SELECT location_id,
       city,
       country_id,
       country_name
FROM locations
NATURAL JOIN countries;


------------------------------------------------------------
-- 2. JOIN USING COUNTRY_ID
------------------------------------------------------------

SELECT location_id,
       city,
       country_id,
       country_name
FROM locations
JOIN countries
USING (country_id);


------------------------------------------------------------
-- 3. JOIN ON COUNTRY_ID
------------------------------------------------------------

SELECT l.location_id,
       l.city,
       l.country_id,
       c.country_name
FROM locations l
JOIN countries c
    ON l.country_id = c.country_id;


------------------------------------------------------------
-- 4. INNER JOIN
------------------------------------------------------------

SELECT l.location_id,
       l.city,
       l.country_id,
       c.country_name
FROM locations l
INNER JOIN countries c
    ON l.country_id = c.country_id;


------------------------------------------------------------
-- 5. LEFT JOIN
-- Returns all locations, even without a matching country
------------------------------------------------------------

SELECT l.location_id,
       l.city,
       l.country_id,
       c.country_name
FROM locations l
LEFT JOIN countries c
    ON l.country_id = c.country_id;


------------------------------------------------------------
-- 6. RIGHT JOIN
-- Returns all countries, even without a location
------------------------------------------------------------

SELECT l.location_id,
       l.city,
       c.country_id,
       c.country_name
FROM locations l
RIGHT JOIN countries c
    ON l.country_id = c.country_id;


------------------------------------------------------------
-- 7. FULL OUTER JOIN
-- Returns all locations and all countries
------------------------------------------------------------

SELECT l.location_id,
       l.city,
       COALESCE(l.country_id, c.country_id) AS country_id,
       c.country_name
FROM locations l
FULL OUTER JOIN countries c
    ON l.country_id = c.country_id;