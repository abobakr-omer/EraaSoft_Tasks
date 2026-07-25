package com.item.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	// Runs once when the filter starts.
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// No startup work is needed for this filter.
	}

	// Checks every request and allows only authenticated users to open protected pages.
	@Override
	public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse,FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request=(HttpServletRequest)servletRequest;
		HttpServletResponse response=(HttpServletResponse)servletResponse;

		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader("Expires",0);

		String contextPath=request.getContextPath();
		String requestURI=request.getRequestURI();

		boolean isLoginPage=requestURI.equals(contextPath+"/login.jsp");
		boolean isSignupPage=requestURI.equals(contextPath+"/signup.jsp");
		boolean isForgotPasswordPage=requestURI.equals(contextPath+"/forgotPassword.jsp");
		boolean isErrorPage=requestURI.equals(contextPath+"/error.jsp");
		boolean isAccountController=requestURI.equals(contextPath+"/AccountController");

		boolean isStaticResource=requestURI.endsWith(".css")
				|| requestURI.endsWith(".js")
				|| requestURI.endsWith(".png")
				|| requestURI.endsWith(".jpg")
				|| requestURI.endsWith(".jpeg")
				|| requestURI.endsWith(".gif")
				|| requestURI.endsWith(".svg");

		if(isLoginPage || isSignupPage || isForgotPasswordPage || isErrorPage || isAccountController || isStaticResource) {
			chain.doFilter(request,response);
			return;
		}

		HttpSession session=request.getSession(false);
		boolean isLoggedIn=session!=null && session.getAttribute("loggedInUsername")!=null;

		if(!isLoggedIn) {
			response.sendRedirect(contextPath+"/login.jsp");
			return;
		}

		chain.doFilter(request,response);
	}

	// Runs once when the filter is destroyed.
	@Override
	public void destroy() {
		// No cleanup work is needed for this filter.
	}
}
