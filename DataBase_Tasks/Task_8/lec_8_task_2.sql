------------------------------------------------------------
-- 1. NATURAL JOIN
------------------------------------------------------------

SELECT employee_id,
       job_id,
       job_title,
       start_date,
       end_date
FROM jobs
NATURAL JOIN job_history;


------------------------------------------------------------
-- 2. JOIN USING JOB_ID
------------------------------------------------------------

SELECT employee_id,
       job_id,
       job_title,
       start_date,
       end_date
FROM jobs
JOIN job_history
USING (job_id);


------------------------------------------------------------
-- 3. JOIN ON JOB_ID
------------------------------------------------------------

SELECT j.job_id,
       j.job_title,
       jh.employee_id,
       jh.start_date,
       jh.end_date
FROM jobs j
JOIN job_history jh
    ON j.job_id = jh.job_id;


------------------------------------------------------------
-- 4. INNER JOIN
------------------------------------------------------------

SELECT j.job_id,
       j.job_title,
       jh.employee_id,
       jh.start_date,
       jh.end_date
FROM jobs j
INNER JOIN job_history jh
    ON j.job_id = jh.job_id;


------------------------------------------------------------
-- 5. LEFT JOIN
-- Returns all jobs, including jobs with no history record
------------------------------------------------------------

SELECT j.job_id,
       j.job_title,
       jh.employee_id,
       jh.start_date,
       jh.end_date
FROM jobs j
LEFT JOIN job_history jh
    ON j.job_id = jh.job_id;


------------------------------------------------------------
-- 6. RIGHT JOIN
-- Returns all job-history rows
------------------------------------------------------------

SELECT j.job_id,
       j.job_title,
       jh.employee_id,
       jh.start_date,
       jh.end_date
FROM jobs j
RIGHT JOIN job_history jh
    ON j.job_id = jh.job_id;


------------------------------------------------------------
-- 7. FULL OUTER JOIN
-- Returns every job and every job-history record
------------------------------------------------------------

SELECT COALESCE(j.job_id, jh.job_id) AS job_id,
       j.job_title,
       jh.employee_id,
       jh.start_date,
       jh.end_date
FROM jobs j
FULL OUTER JOIN job_history jh
    ON j.job_id = jh.job_id;