package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.Movie;
import model.MovieDAO;

/**
 * Servlet implementation class Changeqty
 */
@WebServlet("/ChangeQty")
public class ChangeQty extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	MovieDAO movieDAO = new MovieDAO();
   
    public ChangeQty() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieID = (String)request.getParameter("movieID");
		Integer qty = Integer.parseInt(request.getParameter("movie-qty"));
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		Movie toChange = null;
		try {
			toChange = movieDAO.doRetrieveByKey(movieID);
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		cart.changeQty(toChange, qty);
	
		response.sendRedirect("cart.jsp");
	}

}
