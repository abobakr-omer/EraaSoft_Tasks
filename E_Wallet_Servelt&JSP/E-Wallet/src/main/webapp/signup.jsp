<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign Up | E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>
    <main class="auth-layout">
        <section class="brand-panel">
            <span class="brand">E-Wallet</span>
            <h1>Money management made simple.</h1>
            <p>Create your wallet, transfer money, and keep track of your balance.</p>
        </section>

        <section class="card auth-card">
            <h2>Create an account</h2>
            <p class="subtitle">Enter your information to open your wallet.</p>

            <div class="message error ${empty errorMessage ? 'hidden' : ''}">
                ${errorMessage}
            </div>

            <div class="message success ${empty successMessage ? 'hidden' : ''}">
                ${successMessage}
            </div>

            <form method="post" action="${pageContext.request.contextPath}/AccountController">
                <input type="hidden" name="action" value="signup">

                <label for="username">Username</label>
                <input id="username" name="username" value="${username}" required
                       minlength="5" maxlength="20"
                       pattern="[A-Z][A-Za-z0-9]{4,19}"
                       placeholder="Ahmed1">
                <small>Start with an uppercase letter; use 5-20 letters or numbers.</small>

                <label for="password">Password</label>
                <input id="password" name="password" type="password" required minlength="8"
                       placeholder="At least 8 characters">
                <small>Include uppercase, lowercase, and a number.</small>

                <label for="age">Age</label>
                <input id="age" name="age" type="number" value="${age}" min="18" required
                       placeholder="18 or older">

                <label for="phoneNumber">Egyptian phone number</label>
                <input id="phoneNumber" name="phoneNumber" value="${phoneNumber}" required
                       inputmode="numeric" pattern="01[0125][0-9]{8}" maxlength="11"
                       placeholder="01012345678">

                <button class="button primary" type="submit">Create account</button>
            </form>

            <p class="form-link">
                Already have an account?
                <a href="${pageContext.request.contextPath}/login.jsp">Log in</a>
            </p>
        </section>
    </main>
</body>
</html>
