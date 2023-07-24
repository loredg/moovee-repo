package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Order;

/**
 * Servlet implementation class Payment
 */
@WebServlet("/Payment")
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Payment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String addressID = request.getParameter("address");
		String userID = request.getParameter("id");
		
		System.out.println("user id: " + userID + " address id: " + addressID);
		Order order = (Order) request.getSession().getAttribute("orderPending");
		order.setAddressId(addressID);
		response.sendRedirect("./protected/payment.jsp");
	}

}
