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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String hashPassword = PasswordHashing.toHash(password);
		User toMatch = null;
		RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("/login");
		RequestDispatcher dispatcherToIndex = request.getRequestDispatcher("/index.jsp");
		List<String> errors = new ArrayList<>();		
		HttpSession session = request.getSession();
		
		try {
			toMatch = (User) userDAO.doRetrieveByEmail(email);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		if(toMatch.getUsername() == null) {
			 errors.add("Controllare l'email");
			 session.setAttribute("isLogged", false);
		}
		
		if(!toMatch.getPassword().equals(hashPassword)) {
			errors.add("La password e' errata");
			session.setAttribute("isLogged", false);
		}
		
		if(!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			dispatcherToLoginPage.forward(request, response);
			return;
		}

		session.setAttribute("isLogged", true);
		dispatcherToIndex.forward(request, response);
	}

	
}

