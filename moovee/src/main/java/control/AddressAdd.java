package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Address;
import model.AddressDAO;

/**
 * Servlet implementation class AddressAdd
 */
@WebServlet("/AddressAdd")
public class AddressAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AddressDAO addressDAO = new AddressDAO();
	
    public AddressAdd() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Address address = new Address();
		address.setUserId(request.getParameter("id"));
		address.setAddress(request.getParameter("address"));
		address.setZipCode(request.getParameter("zipCode"));
		address.setTown(request.getParameter("town"));
		address.setProvince(request.getParameter("province"));
		address.setRegion(request.getParameter("region"));
		address.setState(request.getParameter("state"));		
		try {
			addressDAO.doSave(address);
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/protected/payment.jsp");
		dispatcher.forward(request, response);
	}

}
