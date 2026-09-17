/* ================================================= */
/* ADD PLAYER */
/* POST /save-player */
/* ================================================= */

const addForm =
    document.getElementById("addForm");



addForm.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        await savePlayer();

    }
);



async function savePlayer() {

    clearMessage();


    /* The service rejects a new player that already carries an ID, */
    /* so the body is sent without one. */
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
                `${API_BASE_URL}/save-player`,
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(player)
                }
            );


        const savedPlayer =
            await handleResponse(response);


        showSuccess(
            `Player ${savedPlayer.name} was created with ID ${savedPlayer.id}.`
        );


        showResult(savedPlayer);


        addForm.reset();

    } catch (error) {

        hideResult();

        showError(error.message);

    }

}
