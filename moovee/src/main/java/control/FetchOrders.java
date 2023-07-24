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

import model.Order;
import model.OrderDAO;

/**
 * Servlet implementation class FetchOrders
 */
@WebServlet("/FetchOrders")
public class FetchOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  OrderDAO orderDAO = new OrderDAO();
		
    public FetchOrders() {
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
		Collection<?> orders = null;
		System.out.println("Ciao");
		try {
			orders = orderDAO.doRetrieveAll("data");
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		Iterator<?> it = orders.iterator();
		while(it.hasNext()) {
			Order o = (Order) it.next();
			System.out.println(o.getOrderId());
		}

		
		request.setAttribute("orders", orders);
		response.sendRedirect("./admin/admin.jsp");
	}

}
