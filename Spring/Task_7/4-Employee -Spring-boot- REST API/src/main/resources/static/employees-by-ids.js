/* ================================================= */
/* GET EMPLOYEES BY IDS */
/* GET /employee/all-employees-By-Ids/{ids} */
/* ================================================= */

const idsForm =
    document.getElementById("idsForm");



idsForm.addEventListener(
    "submit",
    function (event) {

        event.preventDefault();

        getEmployeesByIds();

    }
);



async function getEmployeesByIds() {

    clearMessage();


    const ids =
        readIdList("idsInput");


    if (!ids) {
        return;
    }


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/all-employees-By-Ids/${ids}`
            );


        const employees =
            await handleResponse(response);


        renderEmployees(employees, true);


        showSuccess(
            `${employees.length} employee(s) loaded.`
        );

    } catch (error) {

        showError(error.message);

    }

}
