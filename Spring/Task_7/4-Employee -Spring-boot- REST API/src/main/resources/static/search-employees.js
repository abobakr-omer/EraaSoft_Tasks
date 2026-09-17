/* ================================================= */
/* SEARCH EMPLOYEES BY NAME */
/* GET /employee/search-by-derived/{name} */
/* GET /employee/search-by-native/{name} */
/* GET /employee/search-by-jpql/{name} */
/* ================================================= */

const searchForm =
    document.getElementById("searchForm");



searchForm.addEventListener(
    "submit",
    function (event) {

        event.preventDefault();

        searchEmployees();

    }
);



async function searchEmployees() {

    clearMessage();


    const name =
        document.getElementById(
            "searchName"
        ).value.trim();


    const type =
        document.getElementById(
            "searchType"
        ).value;


    if (!name) {

        showError(
            "Enter an employee name."
        );

        return;

    }


    /* Each option maps to its own endpoint on the controller. */
    let endpoint;


    if (type === "native") {

        endpoint =
            `/search-by-native/${encodeURIComponent(name)}`;

    } else if (type === "jpql") {

        endpoint =
            `/search-by-jpql/${encodeURIComponent(name)}`;

    } else {

        endpoint =
            `/search-by-derived/${encodeURIComponent(name)}`;
    }


    try {

        const response =
            await fetch(
                API_BASE_URL + endpoint
            );


        const employees =
            await handleResponse(response);


        renderEmployees(employees, true);


        if (employees.length === 0) {

            showSuccess(
                "No employees matched your search."
            );

        } else {

            showSuccess(
                `${employees.length} employee(s) found.`
            );
        }

    } catch (error) {

        showError(error.message);

    }

}
