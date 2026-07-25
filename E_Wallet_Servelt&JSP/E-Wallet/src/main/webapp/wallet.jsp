<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Wallet | E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body class="dashboard-body">
    <header class="topbar">
        <a class="brand"
           href="${pageContext.request.contextPath}/WalletController?action=menu">E-Wallet</a>
        <div class="topbar-user">
            <span>Hello, ${sessionScope.loggedInUsername}</span>
            <a class="button ghost small-button"
               href="${pageContext.request.contextPath}/AccountController?action=logout">Logout</a>
        </div>
    </header>

    <main class="dashboard">
        <section class="balance-card">
            <p>Available balance</p>
            <h1>${empty balance ? '0.00' : balance} EGP</h1>
        </section>

        <div class="message success ${empty successMessage ? 'hidden' : ''}">
            ${successMessage}
        </div>

        <div class="message error ${empty errorMessage ? 'hidden' : ''}">
            ${errorMessage}
        </div>

        <section>
            <h2>Wallet services</h2>
            <div class="feature-grid">
                <a class="feature-card" href="${pageContext.request.contextPath}/deposit.jsp">
                    <span class="feature-icon">+</span>
                    <h3>Deposit</h3>
                    <p>Add money to your wallet.</p>
                </a>

                <a class="feature-card" href="${pageContext.request.contextPath}/withdraw.jsp">
                    <span class="feature-icon">−</span>
                    <h3>Withdraw</h3>
                    <p>Withdraw from your balance.</p>
                </a>

                <a class="feature-card" href="${pageContext.request.contextPath}/transfer.jsp">
                    <span class="feature-icon">→</span>
                    <h3>Transfer</h3>
                    <p>Send money to another user.</p>
                </a>

                <a class="feature-card"
                   href="${pageContext.request.contextPath}/AccountController?action=details">
                    <span class="feature-icon">i</span>
                    <h3>Account details</h3>
                    <p>View your wallet information.</p>
                </a>

                <a class="feature-card" href="${pageContext.request.contextPath}/changePassword.jsp">
                    <span class="feature-icon">•</span>
                    <h3>Change password</h3>
                    <p>Update your account password.</p>
                </a>
            </div>
        </section>
    </main>
</body>
</html>
