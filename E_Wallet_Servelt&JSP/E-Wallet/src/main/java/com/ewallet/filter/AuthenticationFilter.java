package com.ewallet.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {
        "/wallet.jsp",
        "/deposit.jsp",
        "/withdraw.jsp",
        "/transfer.jsp",
        "/accountDetails.jsp",
        "/changePassword.jsp"
}, dispatcherTypes = {
        DispatcherType.REQUEST,
        DispatcherType.FORWARD
})
// Stops visitors from opening wallet pages before login.
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request =
                (HttpServletRequest) servletRequest;
        HttpServletResponse response =
                (HttpServletResponse) servletResponse;

        // Prevent protected pages from being displayed from browser cache.
        response.setHeader(
                "Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // false means do not create a new session for a visitor.
        HttpSession session = request.getSession(false);

        boolean isLoggedIn =
                session != null
                && session.getAttribute("loggedInUsername") != null;

        if (!isLoggedIn) {
            response.sendRedirect(
                    request.getContextPath() + "/login.jsp");
            return;
        }

        // The user is logged in, so continue to the requested page.
        chain.doFilter(request, response);
    }
}
