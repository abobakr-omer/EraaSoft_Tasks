<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>

<%@ include file="/WEB-INF/jspf/html-utils.jspf" %>

<%
	String errorMessage="Something went wrong. Please try again.";
	HttpSession currentSession=request.getSession(false);

	if(currentSession!=null && currentSession.getAttribute("errorMessage")!=null) {
		errorMessage=(String)currentSession.getAttribute("errorMessage");
		currentSession.removeAttribute("errorMessage");
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Error</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>

<div class="auth-page">

	<div class="auth-card auth-card-wide">

		<h1 class="auth-title">Error</h1>

		<p class="auth-subtitle">
			The request could not be completed.
		</p>

		<div class="alert alert-error">
			<%= escapeHtml(errorMessage) %>
		</div>

		<div class="form-actions">
			<a class="btn" href="${pageContext.request.contextPath}/ItemController?action=showItems">Back to Items</a>
			<a class="btn btn-secondary" href="${pageContext.request.contextPath}/login.jsp">Login Page</a>
		</div>

	</div>

</div>

</body>
</html>
