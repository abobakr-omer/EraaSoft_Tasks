<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Withdraw | E-Wallet</title>
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
            <div class="operation-symbol withdraw-symbol">−</div>
            <h1>Withdraw money</h1>
            <p class="subtitle">Enter the amount you want to withdraw.</p>

            <div class="message error ${empty errorMessage ? 'hidden' : ''}">
                ${errorMessage}
            </div>

            <form method="post" action="${pageContext.request.contextPath}/WalletController">
                <input type="hidden" name="action" value="withdraw">

                <label for="amount">Amount in EGP</label>
                <input id="amount" name="amount" type="number" min="0.01" step="0.01"
                       required placeholder="0.00">

                <button class="button danger-button" type="submit">Confirm withdrawal</button>
            </form>
        </section>
    </main>
</body>
</html>
