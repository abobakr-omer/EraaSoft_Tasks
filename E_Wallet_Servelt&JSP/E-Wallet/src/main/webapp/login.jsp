<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String flashSuccessMessage =
            (String) session.getAttribute("successMessage");

    if (flashSuccessMessage != null) {
        session.removeAttribute("successMessage");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login | E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>
    <main class="auth-layout">
        <section class="brand-panel">
            <span class="brand">E-Wallet</span>
            <h1>Welcome back.</h1>
            <p>Log in to manage your balance and make secure transactions.</p>
        </section>

        <section class="card auth-card">
            <h2>Log in</h2>
            <p class="subtitle">Enter your wallet credentials.</p>

            <div class="message error ${empty errorMessage ? 'hidden' : ''}">
                ${errorMessage}
            </div>

            <% if (flashSuccessMessage != null) { %>
                <div class="message success">
                    <%= flashSuccessMessage %>
                </div>
            <% } %>

            <form method="post" action="${pageContext.request.contextPath}/AccountController">
                <input type="hidden" name="action" value="login">

                <label for="username">Username</label>
                <input id="username" name="username" value="${username}" required
                       autocomplete="username" placeholder="Your username">

                <label for="password">Password</label>
                <input id="password" name="password" type="password" required
                       autocomplete="current-password" placeholder="Your password">

                <button class="button primary" type="submit">Log in</button>
            </form>

            <p class="form-link">
                New to E-Wallet?
                <a href="${pageContext.request.contextPath}/signup.jsp">Create an account</a>
            </p>
        </section>
    </main>
</body>
</html>
