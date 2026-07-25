<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.Cookie" %>

<%@ include file="/WEB-INF/jspf/html-utils.jspf" %>

<%
	String savedUsername="";
	boolean rememberChecked=false;

	Cookie[] cookies=request.getCookies();

	if(cookies!=null) {
		for(Cookie cookie:cookies) {

			if("rememberedUsername".equals(cookie.getName())) {
				savedUsername=cookie.getValue();
				rememberChecked=true;
			}

			if("signupUsername".equals(cookie.getName())) {
				savedUsername=cookie.getValue();
			}
		}
	}

	String loginError=(String)request.getAttribute("loginError");
	String loginMessage=null;

	if(request.getSession(false)!=null && request.getSession(false).getAttribute("loginMessage")!=null) {
		loginMessage=(String)request.getSession(false).getAttribute("loginMessage");
		request.getSession(false).removeAttribute("loginMessage");
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>

<div class="auth-page">

	<div class="auth-card">

		<h1 class="auth-title">Login</h1>

		<p class="auth-subtitle">
			Sign in to manage your items.
		</p>

		<% if(loginError!=null) { %>
			<div class="alert alert-error"><%= escapeHtml(loginError) %></div>
		<% } %>

		<% if(loginMessage!=null) { %>
			<div class="alert alert-success"><%= escapeHtml(loginMessage) %></div>
		<% } %>

		<form action="${pageContext.request.contextPath}/AccountController" method="post">

			<input type="hidden" name="action" value="login">

			<div class="form-group">
				<label for="username">Username</label>
				<input type="text" id="username" name="username" value="<%= escapeHtml(savedUsername) %>" placeholder="Enter your username" autocomplete="username" required>
			</div>

			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" id="password" name="password" placeholder="Enter your password" autocomplete="current-password" required>
			</div>

			<div class="login-options">

				<div class="remember-row">
					<input type="checkbox" id="rememberUsername" name="rememberUsername" value="yes" <%= rememberChecked ? "checked" : "" %>>
					<label for="rememberUsername">Remember username</label>
				</div>

				<a class="simple-link" href="${pageContext.request.contextPath}/forgotPassword.jsp">
					Forgot Password?
				</a>

			</div>

			<button type="submit" class="btn full-width">Login</button>

		</form>

		<p class="page-link">
			Don't have an account?
			<a href="${pageContext.request.contextPath}/signup.jsp">Create Account</a>
		</p>

	</div>

</div>

</body>
</html>
