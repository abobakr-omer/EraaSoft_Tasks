<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.item.model.Item" %>

<%@ include file="/WEB-INF/jspf/html-utils.jspf" %>

<%
	List<Item> items=(List<Item>)request.getAttribute("itemsData");
	String loggedInUsername="";

	if(session!=null && session.getAttribute("loggedInUsername")!=null) {
		loggedInUsername=(String)session.getAttribute("loggedInUsername");
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Items</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>

<div class="page">

	<div class="container">

		<div class="navbar">

			<div>
				<div class="nav-title">Items Dashboard</div>
				<div class="nav-subtitle">Manage items and their extra details.</div>
			</div>

			<div class="nav-actions">

				<span class="username-text">Welcome, <%= escapeHtml(loggedInUsername) %></span>

				<a class="btn" href="${pageContext.request.contextPath}/add-item.jsp">Add Item</a>

				<a class="btn btn-light" href="${pageContext.request.contextPath}/AccountController?action=profile">Profile</a>

				<form class="inline-form" action="${pageContext.request.contextPath}/AccountController" method="post">
					<input type="hidden" name="action" value="logout">
					<button type="submit" class="btn btn-secondary">Logout</button>
				</form>

			</div>

		</div>

		<div class="card">

			<div class="card-header">
				<div class="card-title">Items List</div>
			</div>

			<div class="card-body">

				<% if(items==null || items.isEmpty()) { %>

					<div class="empty-state">
						No items found. Click <strong>Add Item</strong> to create the first item.
					</div>

				<% } else { %>

					<div class="table-wrapper">

						<table>
							<thead>
								<tr>
									<th>ID</th>
									<th>Name</th>
									<th>Price</th>
									<th>Total Number</th>
									<th>Actions</th>
								</tr>
							</thead>

							<tbody>
								<% for(Item item:items) { %>

									<tr>
										<td><span class="badge"><%= item.getId() %></span></td>
										<td><%= escapeHtml(item.getName()) %></td>
										<td><%= item.getPrice() %></td>
										<td><%= item.getTotalNumber() %></td>
										<td>
											<div class="actions-cell">

												<a class="btn btn-success" href="<%= request.getContextPath() %>/ItemController?action=showItem&ID=<%= item.getId() %>">
													Update
												</a>

												<a class="btn" href="<%= request.getContextPath() %>/ItemController?action=showItemDetails&ID=<%= item.getId() %>">
													Details
												</a>

												<form class="inline-form" action="<%= request.getContextPath() %>/ItemController" method="post" onsubmit="return confirm('Delete this item?');">
													<input type="hidden" name="action" value="deleteItem">
													<input type="hidden" name="ID" value="<%= item.getId() %>">
													<button type="submit" class="btn btn-danger">Delete</button>
												</form>

											</div>
										</td>
									</tr>

								<% } %>
							</tbody>
						</table>

					</div>

				<% } %>

			</div>

		</div>

	</div>

</div>

</body>
</html>
