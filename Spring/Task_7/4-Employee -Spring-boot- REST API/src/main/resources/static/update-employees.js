/* ================================================= */
/* UPDATE MULTIPLE EMPLOYEES */
/* PUT /employee/update-employees */
/* ================================================= */

const bulkUpdateForm =
    document.getElementById("bulkUpdateForm");



bulkUpdateForm.addEventListener(
    "submit",
    function (event) {

        event.preventDefault();

        updateEmployeesList();

    }
);



async function updateEmployeesList() {

    clearMessage();


    const employees =
        readJsonArray("bulkUpdateInput");


    if (!employees) {
        return;
    }


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/update-employees`,
                {
                    method: "PUT",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(employees)
                }
            );


        const updatedEmployees =
            await handleResponse(response);


        showSuccess(
            `${updatedEmployees.length} employee(s) updated successfully.`
        );


        showResult(updatedEmployees);


        document.getElementById(
            "bulkUpdateInput"
        ).value = "";

    } catch (error) {

        hideResult();

        showError(error.message);

    }

}



/* ================================================= */
/* LOAD THE CURRENT ROWS INTO THE TEXTAREA */
/* GET /employee/all-employees */
/* ================================================= */

/* Updating needs real IDs, so the current rows are fetched */
/* and pasted in as a starting point to edit. */

async function loadCurrentEmployees() {

    clearMessage();


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/all-employees`
            );


        const employees =
            await handleResponse(response);


        if (!employees || employees.length === 0) {

            showError(
                "There are no employees to update yet."
            );

            return;

        }


        document.getElementById(
            "bulkUpdateInput"
        ).value =
            JSON.stringify(employees, null, 2);


        showSuccess(
            `${employees.length} employee(s) loaded. Edit the values and submit.`
        );

    } catch (error) {

        showError(error.message);

    }

}
