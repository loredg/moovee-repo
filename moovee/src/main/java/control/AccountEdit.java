package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PasswordHashing;
import model.UserDAO;

/**
 * Servlet implementation class AccountEdit
 */
@WebServlet("/AccountEdit")
public class AccountEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   UserDAO userDAO = new UserDAO();
	
    public AccountEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = PasswordHashing.toHash(request.getParameter("password"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("Lname");
		
		try {
			userDAO.updateCredentials("nome", fname, id);
			userDAO.updateCredentials("cognome", lname, id);
			userDAO.updateCredentials("email", email, id);
			userDAO.updateCredentials("password", password, id);
			
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		response.sendRedirect("./protected/userPage.jsp");
	}

}
