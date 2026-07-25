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

import com.ewallet.model.Account;
import com.ewallet.service.AccountService;
import com.ewallet.service.impl.AccountServiceImpl;
import com.ewallet.validation.Validator;

@WebServlet("/AccountController")
// Receives signup, login, password, details, and logout requests.
public class AccountController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/demo")
    private DataSource dataSource;

    private AccountService accountService;

    @Override
    public void init() throws ServletException {
        // Tomcat injects the DataSource configured in context.xml.
        accountService = new AccountServiceImpl(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("logout".equals(action)) {
                logout(request, response);
                return;
            }

            if ("details".equals(action)) {
                showAccountDetails(request, response);
                return;
            }

            response.sendRedirect(request.getContextPath() + "/signup.jsp");
        } catch (Exception e) {
            showUnexpectedError(request, response, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("signup".equals(action)) {
                signup(request, response);
                return;
            }

            if ("login".equals(action)) {
                login(request, response);
                return;
            }

            if ("changePassword".equals(action)) {
                changePassword(request, response);
                return;
            }

            response.sendRedirect(request.getContextPath() + "/signup.jsp");
        } catch (Exception e) {
            showUnexpectedError(request, response, e);
        }
    }

    private void signup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Read, validate, and save a new account.
        request.setCharacterEncoding("UTF-8");

        String username = trim(request.getParameter("username"));
        String password = request.getParameter("password");
        String phoneNumber = trim(request.getParameter("phoneNumber"));

        int age;
        try {
            age = Integer.parseInt(request.getParameter("age"));
        } catch (NumberFormatException e) {
            showSignupError(request, response, "Age must be a valid number.");
            return;
        }

        String validationError =
                Validator.validateSignup(username, password, age, phoneNumber);

        if (validationError != null) {
            showSignupError(request, response, validationError);
            return;
        }

        if (accountService.usernameExists(username)) {
            showSignupError(request, response, "This username is already used.");
            return;
        }

        if (accountService.phoneNumberExists(phoneNumber)) {
            showSignupError(request, response, "This phone number is already used.");
            return;
        }

        Account account = new Account(username, password, age, phoneNumber);

        if (!accountService.createAccount(account)) {
            showSignupError(request, response, "Account could not be created.");
            return;
        }

        request.getSession().setAttribute(
                "successMessage", "Account created successfully.");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Validate credentials and limit invalid attempts to three.
        String username = trim(request.getParameter("username"));
        String password = request.getParameter("password");

        if (username.isEmpty() || password == null || password.isEmpty()) {
            showLoginError(
                    request, response, "Username and password are required.");
            return;
        }

        HttpSession session = request.getSession();
        Integer attempts = (Integer) session.getAttribute("loginAttempts");
        attempts = attempts == null ? 0 : attempts;

        if (attempts >= 3) {
            showLoginError(
                    request,
                    response,
                    "Maximum login attempts reached. Please try again later.");
            return;
        }

        if (!accountService.authenticateAccount(username, password)) {
            attempts++;
            session.setAttribute("loginAttempts", attempts);

            int remainingAttempts = 3 - attempts;
            String message = remainingAttempts == 0
                    ? "Maximum login attempts reached. Please try again later."
                    : "Username or password is incorrect. "
                            + remainingAttempts + " attempt(s) remaining.";

            showLoginError(request, response, message);
            return;
        }

        Account account = accountService.findByUsername(username);
        String storedUsername =
                account == null ? username : account.getUsername();

        session.removeAttribute("loginAttempts");
        session.setAttribute("loggedInUsername", storedUsername);
        session.setMaxInactiveInterval(30 * 60);

        response.sendRedirect(
                request.getContextPath() + "/WalletController?action=menu");
    }

    private void changePassword(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Only the logged-in account is allowed to change its password.
        String username = getLoggedInUsername(request);

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (isEmpty(oldPassword) || isEmpty(newPassword)
                || isEmpty(confirmPassword)) {
            showChangePasswordError(
                    request, response, "All password fields are required.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showChangePasswordError(
                    request,
                    response,
                    "New password and confirmation do not match.");
            return;
        }

        if (!Validator.isValidPassword(newPassword)) {
            showChangePasswordError(
                    request,
                    response,
                    "New password must be at least 8 characters with "
                            + "uppercase, lowercase, and a number.");
            return;
        }

        if (oldPassword.equals(newPassword)) {
            showChangePasswordError(
                    request,
                    response,
                    "New password must be different from the old password.");
            return;
        }

        if (!accountService.changePassword(
                username, oldPassword, newPassword)) {
            showChangePasswordError(
                    request, response, "Current password is incorrect.");
            return;
        }

        request.setAttribute(
                "successMessage", "Password changed successfully.");
        request.getRequestDispatcher("/changePassword.jsp")
                .forward(request, response);
    }

    private void showAccountDetails(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Always fetch current details instead of using old session data.
        String username = getLoggedInUsername(request);

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Account account = accountService.findByUsername(username);

        if (account == null) {
            request.setAttribute(
                    "errorMessage", "Account could not be found.");
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
            return;
        }

        request.setAttribute("account", account);
        request.getRequestDispatcher("/accountDetails.jsp")
                .forward(request, response);
    }

    private void logout(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        // Invalidating the session removes all login information.
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        request.getSession(true).setAttribute(
                "successMessage", "You have logged out successfully. Goodbye!");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    private void showSignupError(
            HttpServletRequest request,
            HttpServletResponse response,
            String message) throws ServletException, IOException {
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("/signup.jsp")
                .forward(request, response);
    }

    private void showLoginError(
            HttpServletRequest request,
            HttpServletResponse response,
            String message) throws ServletException, IOException {
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("/login.jsp")
                .forward(request, response);
    }

    private void showChangePasswordError(
            HttpServletRequest request,
            HttpServletResponse response,
            String message) throws ServletException, IOException {
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("/changePassword.jsp")
                .forward(request, response);
    }

    private void showUnexpectedError(
            HttpServletRequest request,
            HttpServletResponse response,
            Exception exception) throws ServletException, IOException {
        // Log technical details on the server and show a safe message to the user.
        log("Unexpected account operation error", exception);
        request.setAttribute(
                "errorMessage",
                "The account operation could not be completed.");
        request.getRequestDispatcher("/error.jsp")
                .forward(request, response);
    }

    private String getLoggedInUsername(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session == null
                ? null
                : (String) session.getAttribute("loggedInUsername");
    }

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }
}
