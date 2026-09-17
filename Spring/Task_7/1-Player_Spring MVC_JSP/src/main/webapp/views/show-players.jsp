<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show Players</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }

        table {
            border-collapse: collapse;
            width: 70%;
            margin-top: 20px;
        }

        th,
        td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: lightgray;
        }

        .add-button {
            display: inline-block;
            padding: 8px 15px;
            background-color: green;
            color: white;
            text-decoration: none;
        }

        .show-button {
            padding: 5px 10px;
        }

        button {
            padding: 5px 10px;
            cursor: pointer;
        }
    </style>
</head>

<body>

<h2>Players</h2>

<a href="${pageContext.request.contextPath}/player/add-player" class="add-button">
    Add Player
</a>

<table>

    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Number</th>
        <th>Salary</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="player" items="${players}">

        <tr>
            <td>${player.id}</td>
            <td><c:out value="${player.name}"/></td>
            <td>${player.number}</td>
            <td>${player.salary}</td>

            <td>

                <a href="${pageContext.request.contextPath}/player/show-player/${player.id}" class="show-button">Show Player</a>

                <a href="${pageContext.request.contextPath}/player/edit-player/${player.id}">Edit Player</a>

                <form action="${pageContext.request.contextPath}/player/delete-player/${player.id}"
                      method="post"
                      style="display:inline;">

                    <button type="submit">
                        Delete
                    </button>

                </form>

            </td>
        </tr>

    </c:forEach>

</table>

</body>
</html>

