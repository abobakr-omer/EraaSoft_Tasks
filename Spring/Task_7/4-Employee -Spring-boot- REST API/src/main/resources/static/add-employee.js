/* ================================================= */
/* ADD EMPLOYEE */
/* POST /employee/save-employee */
/* ================================================= */

const addForm =
    document.getElementById("addForm");



addForm.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        await saveEmployee();

    }
);



async function saveEmployee() {

    clearMessage();


    /* A new employee is sent without an ID so the database generates one. */
    const employee = {

        name:
            document.getElementById(
                "employeeName"
            ).value.trim(),

        age:
            Number(
                document.getElementById(
                    "employeeAge"
                ).value
            ),

        phoneNumber:
            document.getElementById(
                "employeePhone"
            ).value.trim()

    };


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/save-employee`,
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(employee)
                }
            );


        const savedEmployee =
            await handleResponse(response);


        showSuccess(
            `Employee ${savedEmployee.name} was created with ID ${savedEmployee.id}.`
        );


        showResult(savedEmployee);


        addForm.reset();

    } catch (error) {

        hideResult();

        showError(error.message);

    }

}
