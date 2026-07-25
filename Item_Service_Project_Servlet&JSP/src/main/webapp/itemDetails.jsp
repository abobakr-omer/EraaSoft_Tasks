<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.item.model.Item" %>
<%@ page import="com.item.model.ItemDetails" %>

<%@ include file="/WEB-INF/jspf/html-utils.jspf" %>

<%
	Item item=(Item)request.getAttribute("item");
	ItemDetails itemDetails=(ItemDetails)request.getAttribute("itemDetails");
	String validationError=(String)request.getAttribute("validationError");

	boolean hasSavedDetails=false;

	if(itemDetails!=null && itemDetails.getId()>0) {
		hasSavedDetails=true;
	}

	String description="";
	String category="";
	String manufacturer="";
	int warrantyMonths=0;

	if(itemDetails!=null) {
		description=itemDetails.getDescription();
		category=itemDetails.getCategory();
		manufacturer=itemDetails.getManufacturer();
		warrantyMonths=itemDetails.getWarrantyMonths();
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Item Details</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>

<div class="page">

	<div class="container">

		<div class="navbar">
			<div>
				<div class="nav-title">Item Details</div>
				<div class="nav-subtitle">View the item and manage its extra details.</div>
			</div>

			<div class="nav-actions">
				<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ItemController?action=showItems">Back to Items</a>
			</div>
		</div>

		<% if(item==null) { %>

			<div class="card">
				<div class="card-body">
					<div class="alert alert-error">Item data was not found.</div>
				</div>
			</div>

		<% } else { %>

			<div class="grid-two">

				<div class="card">

					<div class="card-header">
						<div class="card-title">Main Item Information</div>
					</div>

					<div class="card-body">

						<div class="info-row">
							<div class="info-label">Item ID</div>
							<div class="info-value"><span class="badge"><%= item.getId() %></span></div>
						</div>

						<div class="info-row">
							<div class="info-label">Name</div>
							<div class="info-value"><%= escapeHtml(item.getName()) %></div>
						</div>

						<div class="info-row">
							<div class="info-label">Price</div>
							<div class="info-value"><%= item.getPrice() %></div>
						</div>

						<div class="info-row">
							<div class="info-label">Total Number</div>
							<div class="info-value"><%= item.getTotalNumber() %></div>
						</div>

						<div class="form-actions">
							<a class="btn btn-success" href="${pageContext.request.contextPath}/ItemController?action=showItem&ID=<%= item.getId() %>">Update Main Item</a>
						</div>

					</div>

				</div>

				<div class="card">

					<div class="card-header">
						<div class="card-title">Extra Details</div>
					</div>

					<div class="card-body">

						<% if(validationError!=null) { %>
							<div class="alert alert-error"><%= escapeHtml(validationError) %></div>
						<% } %>

						<% if(!hasSavedDetails) { %>
							<div class="alert alert-warning">This item does not have details yet. Add details below.</div>
						<% } %>

						<form action="${pageContext.request.contextPath}/ItemController" method="post">

							<% if(hasSavedDetails) { %>
								<input type="hidden" name="action" value="updateItemDetails">
							<% } else { %>
								<input type="hidden" name="action" value="addItemDetails">
							<% } %>

							<input type="hidden" name="itemId" value="<%= item.getId() %>">

							<div class="form-group">
								<label for="description">Description</label>
								<textarea id="description" name="description" maxlength="500" placeholder="Enter item description"><%= escapeHtml(description) %></textarea>
							</div>

							<div class="form-group">
								<label for="category">Category</label>
								<input type="text" id="category" name="category" maxlength="100" placeholder="Enter category" value="<%= escapeHtml(category) %>">
							</div>

							<div class="form-group">
								<label for="manufacturer">Manufacturer</label>
								<input type="text" id="manufacturer" name="manufacturer" maxlength="100" placeholder="Enter manufacturer" value="<%= escapeHtml(manufacturer) %>">
							</div>

							<div class="form-group">
								<label for="warrantyMonths">Warranty Months</label>
								<input type="number" id="warrantyMonths" name="warrantyMonths" min="0" step="1" required value="<%= warrantyMonths %>">
							</div>

							<div class="form-actions">
								<% if(hasSavedDetails) { %>
									<button type="submit" class="btn btn-success">Update Details</button>
								<% } else { %>
									<button type="submit" class="btn">Add Details</button>
								<% } %>
							</div>

						</form>

						<% if(hasSavedDetails) { %>

							<form action="${pageContext.request.contextPath}/ItemController" method="post" onsubmit="return confirm('Delete these item details?');">
								<input type="hidden" name="action" value="deleteItemDetails">
								<input type="hidden" name="itemId" value="<%= item.getId() %>">

								<div class="form-actions">
									<button type="submit" class="btn btn-danger">Delete Details</button>
								</div>
							</form>

						<% } %>

					</div>

				</div>

			</div>

		<% } %>

	</div>

</div>

</body>
</html>
