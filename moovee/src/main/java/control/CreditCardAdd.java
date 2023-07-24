package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import model.CreditCard;
import model.CreditCardDAO;
import model.User;

/**
 * Servlet implementation class CreditCardAdd
 */
@WebServlet("/CreditCardAdd")
public class CreditCardAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CreditCardDAO creditCardDAO = new CreditCardDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreditCardAdd() {
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
		String number = request.getParameter("cardNumber");
		number = number.replace("-", "");
		number = number.replace(" ", "");
		String cvc = request.getParameter("cvc");
		String expirationDate = request.getParameter("expirationDate");
		User user = (User)request.getSession().getAttribute("activeUser");
		CreditCard card = new CreditCard();
		
		DateTimeFormatter df = DateTimeFormat.forPattern("dd/MM/yy");
		if(expirationDate.substring(0, 2).equals("11") || expirationDate.substring(0, 2).equals("06") || expirationDate.substring(0, 2).equals("09") ||expirationDate.substring(0, 2).equals("04")) {
			expirationDate = "30/" + expirationDate;
		}
		else if(expirationDate.substring(0, 2).equals("02")) {
			expirationDate = "28/" + expirationDate;
		}
		else {
			expirationDate = "31/" + expirationDate;
		}
		LocalDate date = df.parseLocalDate(expirationDate);
		
		card.setNumber(number);
		card.setCvc(cvc);
		card.setExpiration(date);
		card.setUserId(user.getId());
		
		try {
			creditCardDAO.doSave(card);
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		response.sendRedirect("./protected/payment.jsp");
	}

}
