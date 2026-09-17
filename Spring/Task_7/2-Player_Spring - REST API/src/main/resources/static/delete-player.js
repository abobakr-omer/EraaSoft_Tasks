/* ================================================= */
/* DELETE PLAYER */
/* GET /show-player/{id}  then  DELETE /delete-player/{id} */
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

const playerTable =
    document.getElementById("playerTable");

const deleteButton =
    document.getElementById("deleteButton");


let selectedId = null;



/* An ID passed in the query string previews the player straight away, */
/* so the Delete links on the list page land on a filled page. */

document.addEventListener(
    "DOMContentLoaded",
    function () {

        const id =
            new URLSearchParams(
                window.location.search
            ).get("id");


        if (id) {

            loadIdInput.value = id;

            loadPlayer();

        }

    }
);



loadForm.addEventListener(
    "submit",
    function (event) {

        event.preventDefault();

        loadPlayer();

    }
);



deleteButton.addEventListener(
    "click",
    deletePlayer
);



/* ================================================= */
/* STEP 1 : PREVIEW */
/* ================================================= */

async function loadPlayer() {

    clearMessage();


    const id =
        loadIdInput.value.trim();


    if (!id || Number(id) <= 0) {

        showError(
            "Enter a positive player ID."
        );

        return;

    }


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/show-player/${id}`
            );


        const player =
            await handleResponse(response);


        showPreview(player);

    } catch (error) {

        resetPreview();

        showError(error.message);

    }

}



function showPreview(player) {

    selectedId = player.id;


    playerTable.innerHTML = `

        <tr>

            <td>
                ${player.id}
            </td>

            <td>
                ${escapeHtml(player.name)}
            </td>

            <td>
                ${player.number}
            </td>

            <td>
                ${formatSalary(player.salary)}
            </td>

        </tr>

    `;


    confirmText.textContent =
        `Player "${player.name}" (ID ${player.id}) will be permanently removed.`;


    previewPanel.classList.remove("hidden");

    confirmCard.classList.remove("hidden");

}



function resetPreview() {

    selectedId = null;

    playerTable.innerHTML = "";

    previewPanel.classList.add("hidden");

    confirmCard.classList.add("hidden");

}



/* ================================================= */
/* STEP 2 : DELETE */
/* ================================================= */

async function deletePlayer() {

    if (!selectedId) {
        return;
    }


    const confirmed =
        confirm(
            `Delete player with ID ${selectedId}?`
        );


    if (!confirmed) {
        return;
    }


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/delete-player/${selectedId}`,
                {
                    method: "DELETE"
                }
            );


        await handleResponse(response);


        const deletedId = selectedId;


        resetPreview();

        loadIdInput.value = "";


        showSuccess(
            `Player ${deletedId} was deleted successfully.`
        );

    } catch (error) {

        showError(error.message);

    }

}
