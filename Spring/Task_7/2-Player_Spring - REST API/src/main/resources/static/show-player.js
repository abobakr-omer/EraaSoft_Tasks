/* ================================================= */
/* FIND PLAYER BY ID */
/* GET /show-player/{id} */
/* ================================================= */

const findForm =
    document.getElementById("findForm");

const playerIdInput =
    document.getElementById("playerId");

const detailsPanel =
    document.getElementById("detailsPanel");

const playerTable =
    document.getElementById("playerTable");



/* An ID passed in the query string loads the player straight away, */
/* so the View links on the list page land on a filled page. */

document.addEventListener(
    "DOMContentLoaded",
    function () {

        const id =
            new URLSearchParams(
                window.location.search
            ).get("id");


        if (id) {

            playerIdInput.value = id;

            findPlayer();

        }

    }
);



findForm.addEventListener(
    "submit",
    function (event) {

        event.preventDefault();

        findPlayer();

    }
);



async function findPlayer() {

    clearMessage();


    const id =
        playerIdInput.value.trim();


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


        displayPlayer(player);

        showResult(player);


        showSuccess(
            `Player ${id} loaded successfully.`
        );

    } catch (error) {

        detailsPanel.classList.add("hidden");

        hideResult();

        showError(error.message);

    }

}



function displayPlayer(player) {

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

            <td>

                <div class="actions-cell">

                    <a
                        class="btn btn-edit btn-small"
                        href="update-player.html?id=${player.id}"
                    >
                        Edit
                    </a>

                    <a
                        class="btn btn-danger btn-small"
                        href="delete-player.html?id=${player.id}"
                    >
                        Delete
                    </a>

                </div>

            </td>

        </tr>

    `;


    detailsPanel.classList.remove("hidden");

}
