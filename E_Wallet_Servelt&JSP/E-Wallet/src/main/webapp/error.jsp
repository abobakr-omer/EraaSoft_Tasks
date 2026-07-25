<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Error | E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>
    <main class="page-container">
        <section class="card error-card">
            <div class="operation-symbol error-symbol">!</div>
            <h1>Something went wrong</h1>
            <p>${empty errorMessage ? 'The operation could not be completed. Please try again.' : errorMessage}</p>
            <a class="button primary"
               href="${pageContext.request.contextPath}/WalletController?action=menu">
                Return to wallet
            </a>
        </section>
    </main>
</body>
</html>
