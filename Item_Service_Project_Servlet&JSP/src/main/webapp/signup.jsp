<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jspf/html-utils.jspf" %>

<%
	String signupError=(String)request.getAttribute("signupError");
	String username=(String)request.getAttribute("username");
	String email=(String)request.getAttribute("email");
	username=username==null ? "" : username;
	email=email==null ? "" : email;
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Signup</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
<script>
function validateSignupForm() {
	var password=document.getElementById("password").value;
	var confirmPassword=document.getElementById("confirmPassword").value;

	if(password!==confirmPassword) {
		alert("Password and confirm password do not match.");
		return false;
	}

	return true;
}
</script>
</head>
<body>

<div class="auth-page">

	<div class="auth-card">

		<h1 class="auth-title">Create Account</h1>

		<p class="auth-subtitle">
			Create your account to start managing items.
		</p>

		<% if(signupError!=null) { %>
			<div class="alert alert-error"><%= escapeHtml(signupError) %></div>
		<% } %>

		<form action="${pageContext.request.contextPath}/AccountController" method="post" onsubmit="return validateSignupForm();">

			<input type="hidden" name="action" value="signup">

			<div class="form-group">
				<label for="username">Username</label>
				<input type="text" id="username" name="username" value="<%= escapeHtml(username) %>" placeholder="Enter your username" minlength="3" maxlength="50" autocomplete="username" required>
			</div>

			<div class="form-group">
				<label for="email">Email Address</label>
				<input type="email" id="email" name="email" value="<%= escapeHtml(email) %>" placeholder="name@example.com" maxlength="100" autocomplete="email" required>
			</div>

			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" id="password" name="password" placeholder="At least 8 characters" minlength="8" maxlength="100" autocomplete="new-password" required>
			</div>

			<div class="form-group">
				<label for="confirmPassword">Confirm Password</label>
				<input type="password" id="confirmPassword" name="confirmPassword" placeholder="Enter the password again" minlength="8" maxlength="100" autocomplete="new-password" required>
			</div>

			<button type="submit" class="btn full-width">Create Account</button>

		</form>

		<p class="page-link">
			Already have an account?
			<a href="${pageContext.request.contextPath}/login.jsp">Login</a>
		</p>

	</div>

</div>

</body>
</html>
