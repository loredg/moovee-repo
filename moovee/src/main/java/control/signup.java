package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
 * Servlet implementation class signup
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IBeanDAO<User> userDAO = new UserDAO();

	public signup() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> errors = new ArrayList<>();
		String username = request.getParameter("username");
		RequestDispatcher dispatcherToSignUpPage = request.getServletContext().getRequestDispatcher("/signup.jsp");
		List<User> users = null;
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = PasswordHashing.toHash(request.getParameter("password"));
		String address = request.getParameter("address");

		try {
			users = (List<User>) userDAO.doRetrieveAll("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (users != null) {
			for (User u : users) {

				if (u.getUsername().equals(username)) {
					errors.add("This useraname is already in use. Please choose another one.");
					request.setAttribute("errors", errors);
					dispatcherToSignUpPage.forward(request, response);
					return;
				}

				if (u.getEmail().equals(email)) {
					errors.add("This email is already linked to an existing account. Please log in.");
					request.setAttribute("errors", errors);
					response.sendRedirect("/login.jsp");
					return;
				}
			}
		}

		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setAdmin(false);
		user.setPassword(password);
		user.setFname(request.getParameter("fname"));
		user.setLname(request.getParameter("lname"));

		try {
			System.out.println("ciao user: " + username + " " + email);
			userDAO.doSave(user);
		} catch (SQLException e) {
			e.getStackTrace();
		}

		session.setAttribute("hasLogged", true);
		session.setAttribute("isLogged", true);
		response.sendRedirect("./index.jsp");
	}

}
