<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>

<head>

    <title>Update Item</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            padding: 40px;
        }

        .container {
            width: 400px;
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

        label {
            display: block;
            margin-top: 15px;
            margin-bottom: 5px;
        }

        input {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            width: 100%;
            margin-top: 20px;
            padding: 10px;
            background-color: #ffc107;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #e0a800;
        }

        a {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #007bff;
            text-decoration: none;
        }

    </style>

</head>

<body>

<div class="container">

    <h1>Update Item</h1>

    <form action="${pageContext.request.contextPath}/items/update-item/${item.id}"
          method="post">

        <input type="hidden"
               name="id"
               value="${item.id}">

        <label>Name</label>

        <input type="text"
               name="name"
               value="<c:out value='${item.name}' />"
               required>

        <label>Price</label>

        <input type="number"
               name="price"
               step="0.01"
               value="${item.price}"
               required>

        <label>Quantity</label>

        <input type="number"
               name="quantity"
               value="${item.quantity}"
               required>

        <button type="submit">
            Update Item
        </button>

    </form>

    <a href="${pageContext.request.contextPath}/items/all-items">
        Back to Items
    </a>

</div>

</body>

</html>
