<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jspf/html-utils.jspf" %>

<%
	String forgotPasswordError=(String)request.getAttribute("forgotPasswordError");
	String username=(String)request.getAttribute("username");
	String email=(String)request.getAttribute("email");

	if(username==null) {
		username="";
	}

	if(email==null) {
		email="";
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Forgot Password</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
<script>
function validateForgotPasswordForm() {
	var newPassword=document.getElementById("newPassword").value;
	var confirmPassword=document.getElementById("confirmPassword").value;

	if(newPassword!==confirmPassword) {
		alert("New password and confirm password do not match.");
		return false;
	}

	return true;
}
</script>
</head>
<body>

<div class="auth-page">

	<div class="auth-card auth-card-wide">

		<h1 class="auth-title">Forgot Password</h1>

		<p class="auth-subtitle">
			Enter your username, email, and new password to reset your account password.
		</p>

		<% if(forgotPasswordError!=null) { %>
			<div class="alert alert-error"><%= escapeHtml(forgotPasswordError) %></div>
		<% } %>

		<form action="${pageContext.request.contextPath}/AccountController" method="post" onsubmit="return validateForgotPasswordForm();">

			<input type="hidden" name="action" value="forgotPassword">

			<div class="form-group">
				<label for="username">Username</label>
				<input type="text" id="username" name="username" value="<%= escapeHtml(username) %>" placeholder="Enter your username" minlength="3" maxlength="50" required>
			</div>

			<div class="form-group">
				<label for="email">Email Address</label>
				<input type="email" id="email" name="email" value="<%= escapeHtml(email) %>" placeholder="name@example.com" maxlength="100" required>
			</div>

			<div class="form-group">
				<label for="newPassword">New Password</label>
				<input type="password" id="newPassword" name="newPassword" placeholder="Enter new password" minlength="8" maxlength="100" required>
			</div>

			<div class="form-group">
				<label for="confirmPassword">Confirm New Password</label>
				<input type="password" id="confirmPassword" name="confirmPassword" placeholder="Enter new password again" minlength="8" maxlength="100" required>
			</div>

			<div class="form-actions">
				<button type="submit" class="btn">Reset Password</button>
				<a class="btn btn-secondary" href="${pageContext.request.contextPath}/login.jsp">Back to Login</a>
			</div>

		</form>

	</div>

</div>

</body>
</html>
