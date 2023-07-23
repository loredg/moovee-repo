package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Address;
import model.AddressDAO;
import model.UserDAO;

/**
 * Servlet implementation class AddressAdd
 */
@WebServlet("/AddressAdd")
public class AddressAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AddressDAO addressDAO = new AddressDAO();
	UserDAO userDAO = new UserDAO();
	
    public AddressAdd() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Address address = new Address();
		String id = request.getParameter("id");
		System.out.println("ciao");
		address.setUserId(id);
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
			return;
		}
		
		Collection<Address> addresses = new LinkedList<>();
		addresses.add(address);
		
		request.getSession().setAttribute("addresses", addresses);
		response.sendRedirect("./protected/checkout.jsp");
	}

}
