<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Change Password | E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body class="dashboard-body">
    <header class="topbar">
        <a class="brand"
           href="${pageContext.request.contextPath}/WalletController?action=menu">E-Wallet</a>
        <a class="back-link"
           href="${pageContext.request.contextPath}/WalletController?action=menu">← Back to wallet</a>
    </header>

    <main class="page-container">
        <section class="card operation-card">
            <h1>Change password</h1>
            <p class="subtitle">Choose a strong password that you have not used before.</p>

            <div class="message error ${empty errorMessage ? 'hidden' : ''}">
                ${errorMessage}
            </div>

            <div class="message success ${empty successMessage ? 'hidden' : ''}">
                ${successMessage}
            </div>

            <form method="post" action="${pageContext.request.contextPath}/AccountController">
                <input type="hidden" name="action" value="changePassword">

                <label for="oldPassword">Current password</label>
                <input id="oldPassword" name="oldPassword" type="password" required>

                <label for="newPassword">New password</label>
                <input id="newPassword" name="newPassword" type="password"
                       minlength="8" required>
                <small>Include uppercase, lowercase, and a number.</small>

                <label for="confirmPassword">Confirm new password</label>
                <input id="confirmPassword" name="confirmPassword" type="password"
                       minlength="8" required>

                <button class="button primary" type="submit">Update password</button>
            </form>
        </section>
    </main>
</body>
</html>
