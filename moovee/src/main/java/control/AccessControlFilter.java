package control;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(filterName = "/AccessControlFilter", urlPatterns = "/*")
public class AccessControlFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		Boolean isAdmin = (Boolean) httpServletRequest.getSession().getAttribute("isAdmin");
		Boolean isLogged = (Boolean) httpServletRequest.getSession().getAttribute("isLogged");
		String path = httpServletRequest.getServletPath();
		
		if (path.contains("/admin/") && (isAdmin==null || !isAdmin)) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
			return;
		}
		else if(path.contains("/protected/") && (isLogged == null || !isLogged)) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
			return;
		}
		else if(path.contains("Checkout") && (isLogged == null || !isLogged)) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
			return;
		}
		//TODO: add restrictions to checkout, payment and account page, put in folder protected
		
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
    public void destroy() {
	}

	private static final long serialVersionUID = 1L;

}
