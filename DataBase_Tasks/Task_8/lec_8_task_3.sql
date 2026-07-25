-----------------------------------------------------------
-- Filter JOB_HISTORY using subqueries
-- Job IDs and selected department names
------------------------------------------------------------

SELECT jh.employee_id,
       jh.start_date,
       jh.end_date,
       jh.job_id,
       jh.department_id
FROM job_history jh
WHERE jh.job_id IN (
    SELECT j.job_id
    FROM jobs j
    WHERE j.job_id IN (
        'AD_ASST',
        'FI_MGR',
        'FI_ACCOUNT',
        'AC_MGR',
        'AC_ACCOUNT',
        'SA_MAN',
        'SA_REP',
        'PU_MAN'
    )
)
AND jh.department_id IN (
    SELECT d.department_id
    FROM departments d
    WHERE d.department_name IN (
        'Administration',
        'Marketing',
        'Purchasing',
        'Human Resources',
        'Shipping'
    )
);


------------------------------------------------------------
-- Display job title and department name as well
------------------------------------------------------------

SELECT jh.employee_id,
       jh.start_date,
       jh.end_date,
       jh.job_id,
       j.job_title,
       jh.department_id,
       d.department_name
FROM job_history jh
JOIN jobs j
    ON jh.job_id = j.job_id
JOIN departments d
    ON jh.department_id = d.department_id
WHERE jh.job_id IN (
    SELECT job_id
    FROM jobs
    WHERE job_id IN (
        'AD_ASST',
        'FI_MGR',
        'FI_ACCOUNT',
        'AC_MGR',
        'AC_ACCOUNT',
        'SA_MAN',
        'SA_REP',
        'PU_MAN'
    )
)
AND jh.department_id IN (
    SELECT department_id
    FROM departments
    WHERE department_name IN (
        'Administration',
        'Marketing',
        'Purchasing',
        'Human Resources',
        'Shipping'
    )
);