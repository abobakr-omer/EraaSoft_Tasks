/* ================================================= */
/* SHOW ALL PLAYERS */
/* GET /all-players */
/* ================================================= */

const playerTable =
    document.getElementById("playerTable");

const emptyState =
    document.getElementById("emptyState");



document.addEventListener(
    "DOMContentLoaded",
    loadPlayers
);



async function loadPlayers() {

    clearMessage();


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/all-players`
            );


        const players =
            await handleResponse(response);


        displayPlayers(players);

    } catch (error) {

        showError(error.message);

    }

}



function displayPlayers(players) {

    playerTable.innerHTML = "";


    if (!players || players.length === 0) {

        emptyState.classList.remove("hidden");

        return;

    }


    emptyState.classList.add("hidden");


    players.forEach(player => {

        const row =
            document.createElement("tr");


        /* Each action link carries the ID to its own operation page. */
        row.innerHTML = `

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
                        class="btn btn-secondary btn-small"
                        href="show-player.html?id=${player.id}"
                    >
                        View
                    </a>

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

        `;


        playerTable.appendChild(row);

    });

}
