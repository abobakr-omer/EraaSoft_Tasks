/* ================================================= */
/* UPDATE PLAYER */
/* GET /show-player/{id}  then  PUT /update-player/{id} */
/* ================================================= */

const loadForm =
    document.getElementById("loadForm");

const updateForm =
    document.getElementById("updateForm");

const loadIdInput =
    document.getElementById("loadId");

const editCard =
    document.getElementById("editCard");



/* An ID passed in the query string loads the player straight away, */
/* so the Edit links on the list page land on a filled form. */

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



updateForm.addEventListener(
    "submit",
    function (event) {

        event.preventDefault();

        updatePlayer();

    }
);



/* ================================================= */
/* STEP 1 : LOAD THE CURRENT VALUES */
/* ================================================= */

async function loadPlayer() {

    clearMessage();

    hideResult();


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


        fillForm(player);


        showSuccess(
            `Player ${id} loaded. You can edit it below.`
        );

    } catch (error) {

        editCard.classList.add("hidden");

        showError(error.message);

    }

}



function fillForm(player) {

    document.getElementById(
        "playerId"
    ).value = player.id;


    document.getElementById(
        "playerName"
    ).value = player.name;


    document.getElementById(
        "playerNumber"
    ).value = player.number;


    document.getElementById(
        "playerSalary"
    ).value = player.salary;


    editCard.classList.remove("hidden");

}



/* ================================================= */
/* STEP 2 : SEND THE UPDATE */
/* ================================================= */

async function updatePlayer() {

    clearMessage();


    const id =
        document.getElementById(
            "playerId"
        ).value;


    /* The ID travels in the path, so the body is sent without one. */
    const player = {

        name:
            document.getElementById(
                "playerName"
            ).value.trim(),

        number:
            Number(
                document.getElementById(
                    "playerNumber"
                ).value
            ),

        salary:
            Number(
                document.getElementById(
                    "playerSalary"
                ).value
            )

    };


    try {

        const response =
            await fetch(
                `${API_BASE_URL}/update-player/${id}`,
                {
                    method: "PUT",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(player)
                }
            );


        const updatedPlayer =
            await handleResponse(response);


        showSuccess(
            `Player ${updatedPlayer.name} was updated successfully.`
        );


        showResult(updatedPlayer);

    } catch (error) {

        hideResult();

        showError(error.message);

    }

}
