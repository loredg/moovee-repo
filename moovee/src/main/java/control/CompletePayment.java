package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Contains;
import model.ContainsDAO;
import model.Movie;
import model.MovieDAO;
import model.Order;
import model.OrderDAO;
import model.User;

/**
 * Servlet implementation class CompletePayment
 */
@WebServlet("/CompletePayment")
public class CompletePayment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ContainsDAO containsDAO = new ContainsDAO();
	OrderDAO orderDAO = new OrderDAO();
	MovieDAO movieDAO = new MovieDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompletePayment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Order order = (Order) request.getSession().getAttribute("orderPending");
		order.complete();
		System.out.println(order.getAddressId());
		System.out.println(order.getUserId());
		System.out.println(order.getTotal());
		Contains contains = new Contains();
		User user = (User) request.getSession().getAttribute("activeUser");

		try {
			orderDAO.doSave(order);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		Collection<?> orders = null;
		try {
			orders = orderDAO.doRetrieveAll("data");
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		Iterator<?> it = orders.iterator();
		while (it.hasNext()) {
			Order o = (Order) it.next();
			for (Movie m : o.getMovies().keySet()) {
				contains.setOrderId(order.getOrderId());
				contains.setMovieId(m.getId());
				contains.setQty(order.getMovies().get(m));
				try {
					containsDAO.doSave(contains);
				} catch (SQLException e) {
					System.out.println("Error: " + e.getMessage());
				}
			}
		}

		response.sendRedirect("./protected/orderComplete.jsp");
	}

}
