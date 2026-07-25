<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.item.model.Account" %>
<%@ include file="/WEB-INF/jspf/html-utils.jspf" %>

<%
	Account account=(Account)request.getAttribute("account");
	String deleteAccountError=(String)request.getAttribute("deleteAccountError");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Profile</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>

<div class="page">
	<div class="container profile-container">
		<div class="navbar">
			<div>
				<div class="nav-title">My Profile</div>
				<div class="nav-subtitle">View your account information and manage your account.</div>
			</div>

			<div class="nav-actions">
				<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ItemController?action=showItems">Back to Items</a>
			</div>
		</div>

		<% if(account==null) { %>
			<div class="card">
				<div class="card-body">
					<div class="alert alert-error">Profile information could not be loaded.</div>
				</div>
			</div>
		<% } else { %>
			<div class="profile-stack">
				<section class="card">
					<div class="card-header">
						<div class="card-title">Account Details</div>
					</div>

					<div class="card-body">
						<div class="profile-avatar" aria-hidden="true"><%= escapeHtml(account.getUsername().substring(0,1).toUpperCase()) %></div>

						<div class="info-row">
							<div class="info-label">Account ID</div>
							<div class="info-value"><span class="badge"><%= account.getId() %></span></div>
						</div>

						<div class="info-row">
							<div class="info-label">Username</div>
							<div class="info-value"><%= escapeHtml(account.getUsername()) %></div>
						</div>

						<div class="info-row">
							<div class="info-label">Email</div>
							<div class="info-value"><%= escapeHtml(account.getEmail()) %></div>
						</div>
					</div>
				</section>

				<section class="card danger-zone">
					<div class="card-header">
						<div>
							<div class="card-title">Delete Account</div>
							<div class="nav-subtitle">Permanently remove your account.</div>
						</div>
					</div>

					<div class="card-body">
						<div class="warning-box">This action cannot be undone. Confirm your password to continue.</div>

						<% if(deleteAccountError!=null) { %>
							<div class="alert alert-error"><%= escapeHtml(deleteAccountError) %></div>
						<% } %>

						<form action="${pageContext.request.contextPath}/AccountController" method="post" onsubmit="return confirm('Are you sure you want to permanently delete your account?');">
							<input type="hidden" name="action" value="deleteAccount">

							<div class="form-group">
								<label for="password">Confirm Password</label>
								<input type="password" id="password" name="password" autocomplete="current-password" placeholder="Enter your password" required>
							</div>

							<div class="form-actions">
								<button type="submit" class="btn btn-danger">Delete My Account</button>
							</div>
						</form>
					</div>
				</section>
			</div>
		<% } %>
	</div>
</div>

</body>
</html>
