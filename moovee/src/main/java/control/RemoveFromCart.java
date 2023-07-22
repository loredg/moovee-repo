package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.Movie;
import model.MovieDAO;

/**
 * Servlet implementation class RemoveFromCart
 */
@WebServlet("/RemoveFromCart")
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	MovieDAO movieDAO = new MovieDAO();
	
    public RemoveFromCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("removeFromCart");
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		Movie toRemove = new Movie();
		try {
			toRemove = movieDAO.doRetrieveByKey(id);
		}catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		if(toRemove != null) {
			cart.removeFromCart(toRemove);
		}
		else {
			System.out.println("toRemove is null");
		}
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/cart.jsp");
		dispatcher.forward(request, response);
	}

}
