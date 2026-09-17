/* ================================================= */
/* DELETE EMPLOYEES BY IDS */
/* GET    /employee/all-employees-By-Ids/{ids} */
/* DELETE /employee/delete-employees-by-ids/{ids} */
/* ================================================= */

const loadForm =
    document.getElementById("loadForm");

const idsInput =
    document.getElementById("idsInput");

const confirmCard =
    document.getElementById("confirmCard");

const confirmText =
    document.getElementById("confirmText");

const previewPanel =
    document.getElementById("previewPanel");

const deleteButton =
    document.getElementById("deleteButton");


let selectedIds = null;



loadForm.addEventListener(
    "submit",
    function (event) {

        event.preventDefault();

        loadEmployees();

    }
);



deleteButton.addEventListener(
    "click",
    deleteEmployeesByIds
);



/* ================================================= */
/* STEP 1 : PREVIEW */
/* ================================================= */

async function loadEmployees() {

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


        if (!employees || employees.length === 0) {

            resetPreview();

            showError(
                "No employees found for those IDs."
            );

            return;

        }


        showPreview(employees, ids);

    } catch (error) {

        resetPreview();

        showError(error.message);

    }

}



function showPreview(employees, ids) {

    selectedIds = ids;


    renderEmployees(employees, false);


    confirmText.textContent =
        `${employees.length} employee(s) will be permanently removed.`;


    previewPanel.classList.remove("hidden");

    confirmCard.classList.remove("hidden");

}



function resetPreview() {

    selectedIds = null;


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

async function deleteEmployeesByIds() {

    if (!selectedIds) {
        return;
    }


    const confirmed =
        confirm(
            `Delete employees with IDs: ${selectedIds}?`
        );


    if (!confirmed) {
        return;
    }


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/delete-employees-by-ids/${selectedIds}`,
                {
                    method: "DELETE"
                }
            );


        await handleResponse(response);


        const deletedIds = selectedIds;


        resetPreview();

        idsInput.value = "";


        showSuccess(
            `Employees ${deletedIds} were deleted successfully.`
        );

    } catch (error) {

        showError(error.message);

    }

}
