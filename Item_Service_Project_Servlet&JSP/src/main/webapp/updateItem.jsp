<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.item.model.Item" %>

<%@ include file="/WEB-INF/jspf/html-utils.jspf" %>

<%
	Item item=(Item)request.getAttribute("item");
	String validationError=(String)request.getAttribute("validationError");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Update Item</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>

<div class="page">

	<div class="container">

		<div class="navbar">
			<div>
				<div class="nav-title">Update Item</div>
				<div class="nav-subtitle">Edit the selected item information.</div>
			</div>

			<div class="nav-actions">
				<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ItemController?action=showItems">Back to Items</a>
			</div>
		</div>

		<div class="card">

			<div class="card-header">
				<div class="card-title">Item Information</div>
			</div>

			<div class="card-body">

				<% if(item==null) { %>

					<div class="alert alert-error">Item data was not found.</div>

				<% } else { %>

					<% if(validationError!=null) { %>
						<div class="alert alert-error"><%= escapeHtml(validationError) %></div>
					<% } %>

					<form action="${pageContext.request.contextPath}/ItemController" method="post">

						<input type="hidden" name="action" value="updateItem">
						<input type="hidden" name="itemId" value="<%= item.getId() %>">

						<div class="form-group">
							<label for="itemName">Item Name</label>
							<input type="text" id="itemName" name="itemName" value="<%= escapeHtml(item.getName()) %>" minlength="2" maxlength="100" required>
						</div>

						<div class="form-group">
							<label for="itemPrice">Price</label>
							<input type="number" id="itemPrice" name="itemPrice" value="<%= item.getPrice() %>" min="0.01" step="0.01" required>
						</div>

						<div class="form-group">
							<label for="itemTotalNumber">Total Number</label>
							<input type="number" id="itemTotalNumber" name="itemTotalNumber" value="<%= item.getTotalNumber() %>" min="0" step="1" required>
						</div>

						<div class="form-actions">
							<button type="submit" class="btn btn-success">Update Item</button>
							<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ItemController?action=showItems">Cancel</a>
						</div>

					</form>

				<% } %>

			</div>

		</div>

	</div>

</div>

</body>
</html>
