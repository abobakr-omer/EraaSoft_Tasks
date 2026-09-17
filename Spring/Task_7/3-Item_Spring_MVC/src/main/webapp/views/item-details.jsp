<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>

<head>

    <title>Item Details</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            padding: 40px;
        }

        .container {
            width: 500px;
            margin: auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }

        td:first-child {
            font-weight: bold;
            width: 40%;
        }

        .buttons {
            margin-top: 20px;
            text-align: center;
        }

        .buttons a {
            display: inline-block;
            padding: 10px 15px;
            margin: 5px;
            text-decoration: none;
            border-radius: 5px;
        }

        .update {
            background-color: #ffc107;
            color: black;
        }

        .delete {
            background-color: #dc3545;
            color: white;
        }

        .back {
            background-color: #007bff;
            color: white;
        }

    </style>

</head>

<body>

<div class="container">

    <h1>Item Details</h1>

    <table>

        <tr>
            <td>ID</td>
            <td>${item.id}</td>
        </tr>

        <tr>
            <td>Name</td>
            <td><c:out value="${item.name}" /></td>
        </tr>

        <tr>
            <td>Price</td>
            <td>${item.price}</td>
        </tr>

        <tr>
            <td>Quantity</td>
            <td>${item.quantity}</td>
        </tr>

    </table>

    <div class="buttons">

        <a class="update"
           href="${pageContext.request.contextPath}/items/show-update-form/${item.id}">
            Update
        </a>

        <form action="${pageContext.request.contextPath}/items/delete-item/${item.id}"
              method="post" style="display: inline;">
            <button class="delete" type="submit">Delete</button>
        </form>

        <a class="back"
           href="${pageContext.request.contextPath}/items/all-items">
            Back
        </a>

    </div>

</div>

</body>

</html>
