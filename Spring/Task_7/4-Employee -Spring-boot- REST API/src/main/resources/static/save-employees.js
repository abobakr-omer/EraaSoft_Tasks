/* ================================================= */
/* SAVE MULTIPLE EMPLOYEES */
/* POST /employee/save-employees */
/* ================================================= */

const bulkSaveForm =
    document.getElementById("bulkSaveForm");



bulkSaveForm.addEventListener(
    "submit",
    function (event) {

        event.preventDefault();

        saveEmployeesList();

    }
);



async function saveEmployeesList() {

    clearMessage();


    const employees =
        readJsonArray("bulkSaveInput");


    if (!employees) {
        return;
    }


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/save-employees`,
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(employees)
                }
            );


        const savedEmployees =
            await handleResponse(response);


        showSuccess(
            `${savedEmployees.length} employee(s) saved successfully.`
        );


        showResult(savedEmployees);


        document.getElementById(
            "bulkSaveInput"
        ).value = "";

    } catch (error) {

        hideResult();

        showError(error.message);

    }

}



/* ================================================= */
/* EXAMPLE PAYLOAD */
/* ================================================= */

function fillExample() {

    const example = [
        {
            name: "Ahmed",
            age: 28,
            phoneNumber: "01000000000"
        },
        {
            name: "Mohamed",
            age: 30,
            phoneNumber: "01100000000"
        }
    ];


    document.getElementById(
        "bulkSaveInput"
    ).value =
        JSON.stringify(example, null, 2);

}
