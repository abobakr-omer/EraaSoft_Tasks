<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Items</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 40px;
        }

        .container {
            width: 80%;
            margin: auto;
            background-color: white;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .add-button {
            display: inline-block;
            margin-bottom: 20px;
            padding: 10px 16px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .add-button:hover {
            background-color: #218838;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th {
            background-color: #343a40;
            color: white;
            padding: 12px;
        }

        td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .view {
            color: #007bff;
            text-decoration: none;
            margin-right: 10px;
        }

        .update {
            color: #e0a800;
            text-decoration: none;
            margin-right: 10px;
        }

        .delete {
            color: #dc3545;
            text-decoration: none;
        }

    </style>

</head>

<body>

<div class="container">

    <h1>Items</h1>

    <a class="add-button"
       href="${pageContext.request.contextPath}/items/show-item-form">
        Add Item
    </a>

    <table>

        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="item" items="${items}">

            <tr>

                <td>${item.id}</td>

                <td><c:out value="${item.name}" /></td>

                <td>${item.price}</td>

                <td>${item.quantity}</td>

                <td>

                    <a class="view"
                       href="${pageContext.request.contextPath}/items/show-item/${item.id}">
                        View
                    </a>

                    <a class="update"
                       href="${pageContext.request.contextPath}/items/show-update-form/${item.id}">
                        Update
                    </a>

                    <form action="${pageContext.request.contextPath}/items/delete-item/${item.id}"
                          method="post" style="display: inline;">
                        <button class="delete" type="submit">Delete</button>
                    </form>

                </td>

            </tr>

        </c:forEach>

    </table>

</div>

</body>

</html>
