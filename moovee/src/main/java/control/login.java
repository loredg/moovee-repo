package control;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.IBeanDAO;
import model.PasswordHashing;
import model.User;
import model.UserDAO;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static UserDAO userDAO = new UserDAO();

	public login() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = PasswordHashing.toHash(request.getParameter("password"));
		User toMatch = null;
		String referer = (String)request.getAttribute("referer");
		RequestDispatcher dispatcherToLoginPage = request.getServletContext().getRequestDispatcher("/login.jsp");
		List<String> errors = new ArrayList<>();
		HttpSession session = request.getSession();

		try {
			toMatch = (User) userDAO.doRetrieveByEmail(email);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		if(toMatch == null || toMatch.getEmail() == null) {
			errors.add("This email is not registered. Please check for mistakes, or sign up if you don't have an account.");
			session.setAttribute("isLogged", false);
			request.setAttribute("errors", errors);
			dispatcherToLoginPage.forward(request, response);
			return;
		}else {
			if(!toMatch.getPassword().equals(password)) {
				errors.add("The password is wrong. Please retry.");
				session.setAttribute("isLogged", false);
				request.setAttribute("errors", errors);
				dispatcherToLoginPage.forward(request, response);
				return;
			}
		}

		session.setAttribute("isLogged", true);
		session.setAttribute("activeUser", toMatch);

		if (toMatch.isAdmin() == true) {
			session.setAttribute("isAdmin", true);
			response.sendRedirect("./admin/admin.jsp");
			return;
		}

		response.sendRedirect("./index.jsp");
	}

}
