package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Address;
import model.AddressDAO;
import model.User;

/**
 * Servlet implementation class RetrieveAddresses
 */
@WebServlet("/RetrieveAddresses")
public class RetrieveAddresses extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	AddressDAO addressDAO = new AddressDAO();
   
    public RetrieveAddresses() {
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
		Collection<Address> addresses = new LinkedList<>();
		User user = (User)request.getSession().getAttribute("activeUser");
		String referer = (String) request.getAttribute("referer");
		try {
			addresses = addressDAO.doRetrieveByUser(user.getId());
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		request.setAttribute("addresses", addresses);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(referer);
		dispatcher.forward(request, response);
	}

}
