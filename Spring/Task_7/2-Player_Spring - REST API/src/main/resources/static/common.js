/* ================================================= */
/* SHARED HELPERS USED BY EVERY OPERATION PAGE */
/* ================================================= */

/* The REST controller maps every endpoint at the root, so there is no prefix. */
const API_BASE_URL = "";



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
/* DISPLAY HELPERS */
/* ================================================= */

function formatSalary(salary) {

    if (salary === null || salary === undefined) {
        return "";
    }


    return Number(salary).toLocaleString(
        "en-US",
        {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        }
    );

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
