package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.LocalDate;

import model.Cart;
import model.Order;
import model.User;

@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Checkout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order = new Order();
		HttpSession session = request.getSession();
		User activeUser = (User)session.getAttribute("activeUser");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		List<String> errors = new ArrayList<>();
		
		if(cart == null) {
			System.out.println("Error: cart is null");
			response.sendRedirect("index.jsp");
			return;
		}
		
		if(activeUser == null) {
			errors.add("You must be logged in to proceed to checkout.");
			response.sendRedirect("login.jsp");
			return;
		}
		
		order.setMovies(cart.getMovies());
		order.setUserId(activeUser.getId());
		order.setTotal(cart.getTotalAmount());
		order.setDate(new LocalDate());
		
		session.setAttribute("orderPending", order);
		
		response.sendRedirect("./protected/checkout.jsp");
	}

}
