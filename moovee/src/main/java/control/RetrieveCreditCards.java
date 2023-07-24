package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CreditCard;
import model.CreditCardDAO;
import model.User;

/**
 * Servlet implementation class RetrieveCreditCards
 */
@WebServlet("/RetrieveCreditCards")
public class RetrieveCreditCards extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    CreditCardDAO creditCardDAO = new CreditCardDAO();
	
	public RetrieveCreditCards() {
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
		Collection<CreditCard> cards = new LinkedList<>();
		User user = (User)request.getSession().getAttribute("activeUser");
		try {
			cards = creditCardDAO.doRetrieveByUser(user.getId());
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		request.setAttribute("cards", cards);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/protected/payment.jsp");
		dispatcher.forward(request, response);
	}

}
