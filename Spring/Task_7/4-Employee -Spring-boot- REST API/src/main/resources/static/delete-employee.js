/* ================================================= */
/* DELETE EMPLOYEE */
/* GET    /employee/all-employees-By-Ids/{id} */
/* DELETE /employee/delete-employee/{id} */
/* ================================================= */

const loadForm =
    document.getElementById("loadForm");

const loadIdInput =
    document.getElementById("loadId");

const confirmCard =
    document.getElementById("confirmCard");

const confirmText =
    document.getElementById("confirmText");

const previewPanel =
    document.getElementById("previewPanel");

const deleteButton =
    document.getElementById("deleteButton");


let selectedId = null;



/* An ID passed in the query string previews the employee straight away, */
/* so the Delete links on the list pages land on a filled page. */

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



deleteButton.addEventListener(
    "click",
    deleteEmployee
);



/* ================================================= */
/* STEP 1 : PREVIEW */
/* ================================================= */

async function loadEmployee() {

    clearMessage();


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

            resetPreview();

            showError(
                `No employee found with ID ${id}.`
            );

            return;

        }


        showPreview(employees[0]);

    } catch (error) {

        resetPreview();

        showError(error.message);

    }

}



function showPreview(employee) {

    selectedId = employee.id;


    renderEmployees([employee], false);


    confirmText.textContent =
        `Employee "${employee.name}" (ID ${employee.id}) will be permanently removed.`;


    previewPanel.classList.remove("hidden");

    confirmCard.classList.remove("hidden");

}



function resetPreview() {

    selectedId = null;


    const table =
        document.getElementById(
            "employeeTable"
        );


    table.innerHTML = "";


    previewPanel.classList.add("hidden");

    confirmCard.classList.add("hidden");

}



/* ================================================= */
/* STEP 2 : DELETE */
/* ================================================= */

async function deleteEmployee() {

    if (!selectedId) {
        return;
    }


    const confirmed =
        confirm(
            `Delete employee with ID ${selectedId}?`
        );


    if (!confirmed) {
        return;
    }


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/delete-employee/${selectedId}`,
                {
                    method: "DELETE"
                }
            );


        await handleResponse(response);


        const deletedId = selectedId;


        resetPreview();

        loadIdInput.value = "";


        showSuccess(
            `Employee ${deletedId} was deleted successfully.`
        );

    } catch (error) {

        showError(error.message);

    }

}
