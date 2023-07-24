package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Order;
import model.OrderDAO;
import model.User;
import model.UserDAO;

/**
 * Servlet implementation class FetchUser
 */
@WebServlet("/FetchUser")
public class FetchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	OrderDAO orderDAO = new OrderDAO();
	UserDAO userDAO = new UserDAO();
	
    public FetchUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("id");
		Order order = null;
		try {
			order = orderDAO.doRetrieveByKey(orderId);
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		User user = null;
		try {
			user = userDAO.doRetrieveByKey(order.getUserId());
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		request.setAttribute("user", user);
		response.sendRedirect("./admin/admin.jsp");
	}

}
