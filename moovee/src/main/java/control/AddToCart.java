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

import model.Cart;
import model.Movie;
import model.MovieDAO;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   MovieDAO movieDAO = new MovieDAO();
	
    public AddToCart() {
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
		String id = request.getParameter("add-to-cart");
		Cart cart = null;
		Movie toAdd = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("cart") == null) {
			cart = new Cart();
		}
		else {
			cart = (Cart)session.getAttribute("cart");
		}
		
		try {
			toAdd = movieDAO.doRetrieveByKey(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(toAdd != null) {
			cart.addToCart(toAdd);
		}
		
		session.setAttribute("cart", cart);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

}
