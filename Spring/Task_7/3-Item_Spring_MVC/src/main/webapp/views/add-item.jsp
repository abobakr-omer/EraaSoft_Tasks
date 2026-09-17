<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>

    <title>Add Item</title>

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
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
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

    <h1>Add Item</h1>

    <form action="${pageContext.request.contextPath}/items/add-item"
          method="post">

        <label>Name</label>

        <input type="text"
               name="name"
               required>

        <label>Price</label>

        <input type="number"
               name="price"
               step="0.01"
               required>

        <label>Quantity</label>

        <input type="number"
               name="quantity"
               required>

        <button type="submit">
            Add Item
        </button>

    </form>

    <a href="${pageContext.request.contextPath}/items/all-items">
        Back to Items
    </a>

</div>

</body>

</html>