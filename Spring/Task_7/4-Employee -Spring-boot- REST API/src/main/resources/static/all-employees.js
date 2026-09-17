/* ================================================= */
/* SHOW ALL EMPLOYEES */
/* GET /employee/all-employees */
/* ================================================= */

document.addEventListener(
    "DOMContentLoaded",
    loadEmployees
);



async function loadEmployees() {

    clearMessage();


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/all-employees`
            );


        const employees =
            await handleResponse(response);


        renderEmployees(employees, true);

    } catch (error) {

        showError(error.message);

    }

}
