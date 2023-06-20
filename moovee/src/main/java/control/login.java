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
import model.User;
import model.UserDAO;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static IBeanDAO<?> userDAO = new UserDAO();
   
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
		String hashPassword = toHash(password);
		User toMatch = null;
		RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("/login");
		RequestDispatcher dispatcherToIndex = request.getRequestDispatcher("/index.jsp");
		List<String> errors = new ArrayList<>();		
		HttpSession session = request.getSession();
		
		try {
			toMatch = (User) userDAO.doRetrieveByKey(email);
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

	private String toHash(String password) {
		String hashString = null;
		try {
			java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			hashString = "";
			for(int i = 0; i < hash.length; i++) {
				hashString += Integer.toHexString((hash[i] & 0xFF) | 0x100).toLowerCase().substring(1, 3);
			}
		}catch(java.security.NoSuchAlgorithmException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		
		return hashString;
	}
	
}

