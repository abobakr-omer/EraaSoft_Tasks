/* ================================================= */
/* UPDATE EMPLOYEE */
/* GET /employee/all-employees-By-Ids/{id} */
/* PUT /employee/update-employee */
/* ================================================= */

const loadForm =
    document.getElementById("loadForm");

const updateForm =
    document.getElementById("updateForm");

const loadIdInput =
    document.getElementById("loadId");

const editCard =
    document.getElementById("editCard");



/* An ID passed in the query string loads the employee straight away, */
/* so the Edit links on the list pages land on a filled form. */

document.addEventListener(
    "DOMContentLoaded",
    function () {

        const id =
            new URLSearchParams(
                window.location.search
            ).get("id");


        if (id) {

            loadIdInput.value = id;

            loadEmployee();

        }

    }
);



loadForm.addEventListener(
    "submit",
    function (event) {

        event.preventDefault();

        loadEmployee();

    }
);



updateForm.addEventListener(
    "submit",
    function (event) {

        event.preventDefault();

        updateEmployee();

    }
);



/* ================================================= */
/* STEP 1 : LOAD THE CURRENT VALUES */
/* ================================================= */

async function loadEmployee() {

    clearMessage();

    hideResult();


    const id =
        loadIdInput.value.trim();


    if (!id || Number(id) <= 0) {

        showError(
            "Enter a positive employee ID."
        );

        return;

    }


    try {

        /* The controller has no single get endpoint, */
        /* so the list endpoint is called with one ID. */
        const response =
            await fetch(
                `${API_BASE_URL}/all-employees-By-Ids/${id}`
            );


        const employees =
            await handleResponse(response);


        if (!employees || employees.length === 0) {

            editCard.classList.add("hidden");

            showError(
                `No employee found with ID ${id}.`
            );

            return;

        }


        fillForm(employees[0]);


        showSuccess(
            `Employee ${id} loaded. You can edit it below.`
        );

    } catch (error) {

        editCard.classList.add("hidden");

        showError(error.message);

    }

}



function fillForm(employee) {

    document.getElementById(
        "employeeId"
    ).value = employee.id;


    document.getElementById(
        "employeeName"
    ).value = employee.name;


    document.getElementById(
        "employeeAge"
    ).value = employee.age;


    document.getElementById(
        "employeePhone"
    ).value = employee.phoneNumber;


    editCard.classList.remove("hidden");

}



/* ================================================= */
/* STEP 2 : SEND THE UPDATE */
/* ================================================= */

async function updateEmployee() {

    clearMessage();


    /* This endpoint reads the ID from the body, not from the path. */
    const employee = {

        id:
            Number(
                document.getElementById(
                    "employeeId"
                ).value
            ),

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
                `${API_BASE_URL}/update-employee`,
                {
                    method: "PUT",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(employee)
                }
            );


        const updatedEmployee =
            await handleResponse(response);


        showSuccess(
            `Employee ${updatedEmployee.name} was updated successfully.`
        );


        showResult(updatedEmployee);

    } catch (error) {

        hideResult();

        showError(error.message);

    }

}
