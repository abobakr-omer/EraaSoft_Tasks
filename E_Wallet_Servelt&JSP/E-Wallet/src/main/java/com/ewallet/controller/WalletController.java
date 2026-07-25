package com.ewallet.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.ewallet.service.WalletService;
import com.ewallet.service.impl.WalletServiceImpl;
import com.ewallet.validation.Validator;

@WebServlet("/WalletController")
// Receives menu, deposit, withdraw, and transfer requests.
public class WalletController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/demo")
    private DataSource dataSource;

    private WalletService walletService;

    @Override
    public void init() throws ServletException {
        // Tomcat injects the DataSource configured in context.xml.
        walletService = new WalletServiceImpl(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            handleGet(request, response);
        } catch (Exception e) {
            showUnexpectedError(request, response, e);
        }
    }

    private void handleGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String username = getLoggedInUsername(request);

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String action = request.getParameter("action");

        if (action == null || "menu".equals(action)) {
            showWallet(request, response, username);
            return;
        }

        response.sendRedirect(
                request.getContextPath() + "/WalletController?action=menu");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            handlePost(request, response);
        } catch (Exception e) {
            showUnexpectedError(request, response, e);
        }
    }

    private void handlePost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String username = getLoggedInUsername(request);

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String action = request.getParameter("action");

        if ("deposit".equals(action)) {
            deposit(request, response, username);
            return;
        }

        if ("withdraw".equals(action)) {
            withdraw(request, response, username);
            return;
        }

        if ("transfer".equals(action)) {
            transfer(request, response, username);
            return;
        }

        response.sendRedirect(
                request.getContextPath() + "/WalletController?action=menu");
    }

    private void deposit(
            HttpServletRequest request,
            HttpServletResponse response,
            String username) throws ServletException, IOException {

        // Validate the form value before updating Oracle.
        double amount;
        String amountText = request.getParameter("amount");

        if (amountText == null || amountText.isBlank()) {
            showDepositError(request, response, "Deposit amount is required.");
            return;
        }

        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            showDepositError(request, response, "Amount must be a valid number.");
            return;
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            showDepositError(
                    request, response, "Deposit amount must be greater than zero.");
            return;
        }

        if (!walletService.deposit(username, amount)) {
            showDepositError(
                    request, response, "Deposit could not be completed.");
            return;
        }

        request.getSession().setAttribute(
                "successMessage", "Deposit completed successfully.");

        response.sendRedirect(
                request.getContextPath() + "/WalletController?action=menu");
    }

    private void showWallet(
            HttpServletRequest request,
            HttpServletResponse response,
            String username) throws ServletException, IOException {

        // Load the newest balance every time the wallet menu opens.
        Double balance = walletService.getBalance(username);

        if (balance == null) {
            request.setAttribute(
                    "errorMessage", "Could not load the wallet balance.");
        } else {
            request.setAttribute("balance", balance);
        }

        HttpSession session = request.getSession(false);
        Object successMessage = session.getAttribute("successMessage");

        if (successMessage != null) {
            request.setAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }

        request.getRequestDispatcher("/wallet.jsp").forward(request, response);
    }

    private void withdraw(
            HttpServletRequest request,
            HttpServletResponse response,
            String username) throws ServletException, IOException {

        // The service also checks the balance inside its UPDATE statement.
        String amountText = request.getParameter("amount");

        if (amountText == null || amountText.isBlank()) {
            showWithdrawError(request, response, "Withdraw amount is required.");
            return;
        }

        double amount;

        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            showWithdrawError(request, response, "Amount must be a valid number.");
            return;
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            showWithdrawError(
                    request, response, "Withdraw amount must be greater than zero.");
            return;
        }

        if (!walletService.withdraw(username, amount)) {
            showWithdrawError(
                    request,
                    response,
                    "Insufficient balance or account could not be found.");
            return;
        }

        request.getSession().setAttribute(
                "successMessage", "Withdrawal completed successfully.");

        response.sendRedirect(
                request.getContextPath() + "/WalletController?action=menu");
    }

    private void transfer(
            HttpServletRequest request,
            HttpServletResponse response,
            String sourceUsername) throws ServletException, IOException {

        // Validate receiver and amount before starting the transaction.
        String destinationUsername =
                trim(request.getParameter("destinationUsername"));
        String amountText = request.getParameter("amount");

        if (!Validator.isValidUsername(destinationUsername)) {
            showTransferError(
                    request,
                    response,
                    destinationUsername,
                    "Enter a valid destination username.");
            return;
        }

        if (sourceUsername.equalsIgnoreCase(destinationUsername)) {
            showTransferError(
                    request,
                    response,
                    destinationUsername,
                    "You cannot transfer money to yourself.");
            return;
        }

        if (amountText == null || amountText.isBlank()) {
            showTransferError(
                    request,
                    response,
                    destinationUsername,
                    "Transfer amount is required.");
            return;
        }

        double amount;

        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            showTransferError(
                    request,
                    response,
                    destinationUsername,
                    "Amount must be a valid number.");
            return;
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            showTransferError(
                    request,
                    response,
                    destinationUsername,
                    "Transfer amount must be greater than zero.");
            return;
        }

        String transferError = walletService.transfer(
                sourceUsername, destinationUsername, amount);

        if (transferError != null) {
            showTransferError(
                    request,
                    response,
                    destinationUsername,
                    transferError);
            return;
        }

        request.getSession().setAttribute(
                "successMessage",
                "Transferred " + amount + " EGP to "
                        + destinationUsername + " successfully.");

        response.sendRedirect(
                request.getContextPath() + "/WalletController?action=menu");
    }

    private void showDepositError(
            HttpServletRequest request,
            HttpServletResponse response,
            String message) throws ServletException, IOException {

        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("/deposit.jsp").forward(request, response);
    }

    private void showWithdrawError(
            HttpServletRequest request,
            HttpServletResponse response,
            String message) throws ServletException, IOException {

        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("/withdraw.jsp").forward(request, response);
    }

    private void showTransferError(
            HttpServletRequest request,
            HttpServletResponse response,
            String destinationUsername,
            String message) throws ServletException, IOException {

        request.setAttribute("destinationUsername", destinationUsername);
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("/transfer.jsp")
                .forward(request, response);
    }

    private void showUnexpectedError(
            HttpServletRequest request,
            HttpServletResponse response,
            Exception exception) throws ServletException, IOException {

        // Keep technical errors in Tomcat logs and show a friendly page.
        log("Unexpected wallet operation error", exception);
        request.setAttribute(
                "errorMessage",
                "The wallet operation could not be completed.");
        request.getRequestDispatcher("/error.jsp")
                .forward(request, response);
    }

    private String getLoggedInUsername(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return null;
        }

        return (String) session.getAttribute("loggedInUsername");
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }
}
