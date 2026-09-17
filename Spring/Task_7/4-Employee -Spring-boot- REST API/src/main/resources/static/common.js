/* ================================================= */
/* SHARED HELPERS USED BY EVERY OPERATION PAGE */
/* ================================================= */

const API_BASE_URL = "/employee";



/* ================================================= */
/* HANDLE SPRING RESPONSE */
/* ================================================= */

async function handleResponse(response) {

    if (response.ok) {

        if (
            response.status === 204 ||
            response.headers.get(
                "content-length"
            ) === "0"
        ) {
            return null;
        }


        const text =
            await response.text();


        return text
            ? JSON.parse(text)
            : null;
    }


    let message =
        "Something went wrong.";


    try {

        const error =
            await response.json();


        if (error.message) {
            message = error.message;
        }

    } catch (error) {

        message =
            `Request failed with status ${response.status}`;
    }


    throw new Error(message);

}



/* ================================================= */
/* EMPLOYEE TABLE */
/* ================================================= */

/* Renders a list of employees into the table of the current page. */
/* withActions adds the per row links to the other operation pages. */

function renderEmployees(employees, withActions) {

    const table =
        document.getElementById(
            "employeeTable"
        );


    const emptyState =
        document.getElementById(
            "emptyState"
        );


    if (!table) {
        return;
    }


    table.innerHTML = "";


    if (!employees || employees.length === 0) {

        if (emptyState) {
            emptyState.classList.remove("hidden");
        }

        return;

    }


    if (emptyState) {
        emptyState.classList.add("hidden");
    }


    employees.forEach(employee => {

        const row =
            document.createElement("tr");


        const actionsCell =
            withActions
                ? `
            <td>

                <div class="actions-cell">

                    <a
                        class="btn btn-edit btn-small"
                        href="update-employee.html?id=${employee.id}"
                    >
                        Edit
                    </a>

                    <a
                        class="btn btn-danger btn-small"
                        href="delete-employee.html?id=${employee.id}"
                    >
                        Delete
                    </a>

                </div>

            </td>`
                : "";


        row.innerHTML = `

            <td>
                ${employee.id}
            </td>

            <td>
                ${escapeHtml(employee.name)}
            </td>

            <td>
                ${employee.age}
            </td>

            <td>
                ${escapeHtml(employee.phoneNumber)}
            </td>

            ${actionsCell}

        `;


        table.appendChild(row);

    });

}



/* ================================================= */
/* IDS INPUT */
/* ================================================= */

/* Reads a comma separated list of IDs and validates it. */
/* Returns null and shows an error when the input is not usable. */

function readIdList(inputId) {

    const ids =
        document.getElementById(
            inputId
        ).value.trim();


    if (!ids) {

        showError(
            "Enter one or more employee IDs."
        );

        return null;
    }


    const idArray =
        ids.split(",")
            .map(id => id.trim());


    const valid =
        idArray.every(
            id =>
                id !== "" &&
                !isNaN(id) &&
                Number(id) > 0
        );


    if (!valid) {

        showError(
            "IDs must be positive numbers separated by commas."
        );

        return null;
    }


    return idArray.join(",");

}



/* ================================================= */
/* JSON ARRAY INPUT */
/* ================================================= */

/* Parses a textarea that must contain a JSON array of employees. */
/* Returns null and shows an error when the text is not usable. */

function readJsonArray(inputId) {

    const text =
        document.getElementById(
            inputId
        ).value.trim();


    if (!text) {

        showError(
            "Enter employees JSON."
        );

        return null;

    }


    let employees;


    try {

        employees =
            JSON.parse(text);

    } catch (error) {

        showError(
            "Invalid JSON format."
        );

        return null;

    }


    if (!Array.isArray(employees)) {

        showError(
            "The JSON must contain an array of employees."
        );

        return null;

    }


    return employees;

}



/* ================================================= */
/* MESSAGES */
/* ================================================= */

function showSuccess(message) {

    const messageBox =
        document.getElementById(
            "message"
        );


    if (!messageBox) {
        return;
    }


    messageBox.innerHTML =
        `<div class="message message-success">
            ${escapeHtml(message)}
        </div>`;

}



function showError(message) {

    const messageBox =
        document.getElementById(
            "message"
        );


    if (!messageBox) {
        return;
    }


    messageBox.innerHTML =
        `<div class="message message-error">
            ${escapeHtml(message)}
        </div>`;

}



function clearMessage() {

    const messageBox =
        document.getElementById(
            "message"
        );


    if (messageBox) {
        messageBox.innerHTML = "";
    }

}



/* ================================================= */
/* JSON RESULT PANEL */
/* ================================================= */

function showResult(data) {

    const panel =
        document.getElementById(
            "resultPanel"
        );


    const output =
        document.getElementById(
            "resultJson"
        );


    if (!panel || !output) {
        return;
    }


    output.textContent =
        JSON.stringify(data, null, 2);


    panel.classList.remove("hidden");

}



function hideResult() {

    const panel =
        document.getElementById(
            "resultPanel"
        );


    if (panel) {
        panel.classList.add("hidden");
    }

}



/* ================================================= */
/* SECURITY / DISPLAY */
/* ================================================= */

function escapeHtml(value) {

    if (value === null || value === undefined) {
        return "";
    }


    return String(value)

        .replaceAll("&", "&amp;")

        .replaceAll("<", "&lt;")

        .replaceAll(">", "&gt;")

        .replaceAll('"', "&quot;")

        .replaceAll("'", "&#039;");
}
