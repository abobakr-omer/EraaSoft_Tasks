package com.item.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.item.model.Account;
import com.item.service.AccountService;
import com.item.service.impl.AccountServiceImpl;

@WebServlet("/AccountController")
public class AccountController extends HttpServlet {

	@Resource(name="jdbc/demo")
	private DataSource dataSource;

	private AccountService accountService;

	// Creates the account service when the servlet starts.
	@Override
	public void init() throws ServletException {
		accountService=new AccountServiceImpl(dataSource);
	}

	// Sends the user to the generic error page with a clear message.
	private void redirectToErrorPage(HttpServletRequest request,HttpServletResponse response,String errorMessage) throws IOException {
		request.getSession().setAttribute("errorMessage",errorMessage);
		response.sendRedirect(request.getContextPath()+"/error.jsp");
	}

	// Returns the correct cookie path for the current web application.
	private String getCookiePath(HttpServletRequest request) {

		String contextPath=request.getContextPath();

		if(contextPath==null || contextPath.isEmpty()) {
			return "/";
		}

		return contextPath;
	}

	// Adds a cookie to remember simple user data such as username.
	private void addCookie(HttpServletResponse response,HttpServletRequest request,String name,String value,int maxAge) {

		Cookie cookie=new Cookie(name,value);
		cookie.setMaxAge(maxAge);
		cookie.setPath(getCookiePath(request));
		cookie.setHttpOnly(true);

		response.addCookie(cookie);
	}

	// Deletes an existing cookie by setting its max age to zero.
	private void deleteCookie(HttpServletResponse response,HttpServletRequest request,String name) {

		Cookie cookie=new Cookie(name,"");
		cookie.setMaxAge(0);
		cookie.setPath(getCookiePath(request));
		cookie.setHttpOnly(true);

		response.addCookie(cookie);
	}

	// Validates forgot password fields before resetting the password.
	private String validateForgotPasswordInput(String username,String email,String newPassword,String confirmPassword) {

		if(username==null || username.trim().isEmpty()) {
			return "Username is required.";
		}

		if(username.trim().length()<3 || username.trim().length()>50) {
			return "Username must be between 3 and 50 characters.";
		}

		if(email==null || email.trim().isEmpty()) {
			return "Email is required.";
		}

		if(!email.contains("@") || !email.contains(".")) {
			return "Please enter a valid email address.";
		}

		if(newPassword==null || newPassword.trim().isEmpty()) {
			return "New password is required.";
		}

		if(newPassword.length()<8 || newPassword.length()>100) {
			return "New password must be between 8 and 100 characters.";
		}

		if(confirmPassword==null || confirmPassword.trim().isEmpty()) {
			return "Confirm password is required.";
		}

		if(!newPassword.equals(confirmPassword)) {
			return "New password and confirm password do not match.";
		}

		return null;
	}

	private String validateSignupInput(String username,String email,String password,String confirmPassword) {
		if(username==null || username.trim().length()<3 || username.trim().length()>50) {
			return "Username must be between 3 and 50 characters.";
		}

		if(email==null || email.trim().length()>100 || !email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
			return "Please enter a valid email address.";
		}

		if(password==null || password.length()<8 || password.length()>100) {
			return "Password must be between 8 and 100 characters.";
		}

		if(!password.equals(confirmPassword)) {
			return "Password and confirm password do not match.";
		}

		return null;
	}

	// Handles all account actions based on the action request parameter.
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		try {

			String action=request.getParameter("action");

			if(action==null) {
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return;
			}

			switch(action) {

			case "signup":
				signup(request,response);
				break;

			case "login":
				login(request,response);
				break;

			case "logout":
				logout(request,response);
				break;

			case "profile":
				showProfile(request,response);
				break;

			case "deleteAccount":
				deleteAccount(request,response);
				break;

			case "forgotPassword":
				forgotPassword(request,response);
				break;

			default:
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				break;
			}

		} catch(Exception e) {
			e.printStackTrace();
			redirectToErrorPage(request,response,"Unable to complete the account operation. Please try again.");
		}
	}

	private void showProfile(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);

		if(session==null || session.getAttribute("loggedInUsername")==null) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}

		String username=(String)session.getAttribute("loggedInUsername");
		Account account=accountService.getAccountByUsername(username);

		if(account==null) {
			redirectToErrorPage(request,response,"Your profile could not be loaded. Please try again.");
			return;
		}

		request.setAttribute("account",account);
		request.getRequestDispatcher("/profile.jsp").forward(request,response);
	}

	// Handles form submissions by using the same action logic as doGet.
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	// Resets the password when username and email match an existing account.
	private void forgotPassword(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String newPassword=request.getParameter("newPassword");
		String confirmPassword=request.getParameter("confirmPassword");

		String validationError=validateForgotPasswordInput(username,email,newPassword,confirmPassword);

		if(validationError!=null) {
			request.setAttribute("forgotPasswordError",validationError);
			request.setAttribute("username",username);
			request.setAttribute("email",email);
			request.getRequestDispatcher("/forgotPassword.jsp").forward(request,response);
			return;
		}

		boolean isPasswordReset=accountService.resetPassword(username.trim(),email.trim(),newPassword);

		if(isPasswordReset) {
			request.getSession().setAttribute("loginMessage","Password has been reset successfully. Please login with your new password.");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}

		request.setAttribute("forgotPasswordError","Username and email do not match any account.");
		request.setAttribute("username",username);
		request.setAttribute("email",email);
		request.getRequestDispatcher("/forgotPassword.jsp").forward(request,response);
	}

	// Deletes the logged-in account after confirming the password.
	private void deleteAccount(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession(false);

		if(session==null || session.getAttribute("loggedInUsername")==null) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}

		String username=(String)session.getAttribute("loggedInUsername");
		String password=request.getParameter("password");

		if(password==null || password.trim().isEmpty()) {
			request.setAttribute("deleteAccountError","Password is required to delete your account.");
			request.setAttribute("account",accountService.getAccountByUsername(username));
			request.getRequestDispatcher("/profile.jsp").forward(request,response);
			return;
		}

		Account account=new Account();
		account.setUsername(username);
		account.setPassword(password);

		boolean isAuthenticated=accountService.authenticateAccount(account);

		if(!isAuthenticated) {
			request.setAttribute("deleteAccountError","Password is incorrect.");
			request.setAttribute("account",accountService.getAccountByUsername(username));
			request.getRequestDispatcher("/profile.jsp").forward(request,response);
			return;
		}

		boolean isDeleted=accountService.deleteAccountByUsername(username);

		if(isDeleted) {
			session.invalidate();
			deleteCookie(response,request,"rememberedUsername");
			deleteCookie(response,request,"signupUsername");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}

		redirectToErrorPage(request,response,"Account could not be deleted. Please try again.");
	}

	// Creates a new account and remembers the username for the login page.
	private void signup(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {

		String userName=request.getParameter("username");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String confirmPassword=request.getParameter("confirmPassword");
		String validationError=validateSignupInput(userName,email,password,confirmPassword);

		if(validationError!=null) {
			request.setAttribute("signupError",validationError);
			request.setAttribute("username",userName);
			request.setAttribute("email",email);
			request.getRequestDispatcher("/signup.jsp").forward(request,response);
			return;
		}

		Account account=new Account(userName.trim(),email.trim(),password);
		boolean isAccountCreated=accountService.createAccount(account);

		if(isAccountCreated) {
			addCookie(response,request,"signupUsername",userName,5*60);
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}

		redirectToErrorPage(request,response,"Account could not be created. The username or email may already exist.");
	}

	// Logs the user out by invalidating the current session.
	private void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {

		HttpSession session=request.getSession(false);

		if(session!=null) {
			session.invalidate();
		}

		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	// Authenticates the user and creates a new protected session.
	private void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		String username=request.getParameter("username");
		String password=request.getParameter("password");

		Account account=new Account();
		account.setUsername(username);
		account.setPassword(password);

		boolean isAuthenticated=accountService.authenticateAccount(account);

		if(!isAuthenticated) {
			request.setAttribute("loginError","The username or password is incorrect.");
			request.getRequestDispatcher("/login.jsp").forward(request,response);
			return;
		}

		HttpSession oldSession=request.getSession(false);

		if(oldSession!=null) {
			oldSession.invalidate();
		}

		HttpSession session=request.getSession(true);
		session.setAttribute("loggedInUsername",username);
		session.setMaxInactiveInterval(30*60);

		String rememberUsername=request.getParameter("rememberUsername");

		if("yes".equals(rememberUsername)) {
			addCookie(response,request,"rememberedUsername",username,7*24*60*60);
		} else {
			deleteCookie(response,request,"rememberedUsername");
		}

		deleteCookie(response,request,"signupUsername");
		response.sendRedirect(request.getContextPath()+"/ItemController?action=showItems");
	}
}
