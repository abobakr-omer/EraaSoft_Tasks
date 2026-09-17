/* ================================================= */
/* DELETE ALL EMPLOYEES */
/* GET    /employee/all-employees */
/* DELETE /employee/delete-all-employees */
/* ================================================= */

const confirmInput =
    document.getElementById("confirmInput");

const deleteButton =
    document.getElementById("deleteButton");



document.addEventListener(
    "DOMContentLoaded",
    loadEmployees
);



/* The button stays disabled until the word DELETE is typed, */
/* because this operation cannot be undone. */

confirmInput.addEventListener(
    "input",
    function () {

        deleteButton.disabled =
            confirmInput.value.trim().toUpperCase() !== "DELETE";

    }
);



deleteButton.addEventListener(
    "click",
    deleteAllEmployees
);



/* ================================================= */
/* SHOW WHAT WILL BE DELETED */
/* ================================================= */

async function loadEmployees() {

    clearMessage();


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/all-employees`
            );


        const employees =
            await handleResponse(response);


        renderEmployees(employees, false);

    } catch (error) {

        showError(error.message);

    }

}



/* ================================================= */
/* DELETE EVERYTHING */
/* ================================================= */

async function deleteAllEmployees() {

    const confirmed =
        confirm(
            "This will delete every employee. Continue?"
        );


    if (!confirmed) {
        return;
    }


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/delete-all-employees`,
                {
                    method: "DELETE"
                }
            );


        await handleResponse(response);


        confirmInput.value = "";

        deleteButton.disabled = true;


        await loadEmployees();


        showSuccess(
            "All employees were deleted successfully."
        );

    } catch (error) {

        showError(error.message);

    }

}
