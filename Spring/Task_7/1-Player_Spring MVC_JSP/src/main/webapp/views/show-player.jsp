<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>Player Details</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }

        .player-details {
            width: 300px;
            border: 1px solid black;
            padding: 20px;
        }

        p {
            margin: 10px 0;
        }

        a {
            display: inline-block;
            margin-top: 15px;
        }
    </style>

</head>

<body>

<h2>Player Details</h2>

<div class="player-details">

    <p>
        <strong>ID:</strong>
        ${player.id}
    </p>

    <p>
        <strong>Name:</strong>
        <c:out value="${player.name}"/>
    </p>

    <p>
        <strong>Number:</strong>
        ${player.number}
    </p>

    <p>
        <strong>Salary:</strong>
        ${player.salary}
    </p>

</div>

<a href="${pageContext.request.contextPath}/player/show-players">
    Back to Players
</a>

</body>
</html>

