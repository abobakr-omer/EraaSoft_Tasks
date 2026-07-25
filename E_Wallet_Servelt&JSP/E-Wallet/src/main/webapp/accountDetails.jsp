<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Account Details | E-Wallet</title>
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
        <section class="card details-card">
            <h1>Account details</h1>
            <p class="subtitle">Your current wallet information.</p>

            <dl class="details-list">
                <div>
                    <dt>Username</dt>
                    <dd>${account.username}</dd>
                </div>
                <div>
                    <dt>Phone number</dt>
                    <dd>${account.phoneNumber}</dd>
                </div>
                <div>
                    <dt>Age</dt>
                    <dd>${account.age}</dd>
                </div>
                <div>
                    <dt>Balance</dt>
                    <dd>${account.balance} EGP</dd>
                </div>
                <div>
                    <dt>Password</dt>
                    <dd>••••••••</dd>
                </div>
            </dl>
        </section>
    </main>
</body>
</html>
