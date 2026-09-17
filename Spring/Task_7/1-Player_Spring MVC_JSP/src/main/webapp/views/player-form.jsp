<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${empty player.id ? 'Add Player' : 'Edit Player'}</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }

        form {
            width: 300px;
        }

        label {
            display: block;
            margin-top: 10px;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            box-sizing: border-box;
        }

        button {
            margin-top: 15px;
            padding: 8px 15px;
        }

        a {
            display: inline-block;
            margin-top: 15px;
        }
    </style>
</head>
<body>

<h2>${empty player.id ? 'Add Player' : 'Edit Player'}</h2>

<c:if test="${not empty error}">
    <p role="alert"><c:out value="${error}"/></p>
</c:if>

<form action="${pageContext.request.contextPath}/player/save-player" method="post">
    <input type="hidden" name="id" value="${player.id}">

    <label for="name">Name:</label>
    <input id="name" type="text" name="name"
           value="<c:out value='${player.name}'/>" maxlength="255" required>

    <label for="number">Player Number:</label>
    <input id="number" type="number" name="number"
           value="${player.number}" min="0" required>

    <label for="salary">Salary:</label>
    <input id="salary" type="number" name="salary"
           value="${player.salary}" min="0" step="0.01" required>

    <button type="submit">Save Player</button>
</form>

<a href="${pageContext.request.contextPath}/player/show-players">Back to Players</a>

</body>
</html>

