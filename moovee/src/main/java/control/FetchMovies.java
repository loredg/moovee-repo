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

import model.Contains;
import model.ContainsDAO;
import model.Movie;

/**
 * Servlet implementation class FetchMovies
 */
@WebServlet("/FetchMovies")
public class FetchMovies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  ContainsDAO containsDAO = new ContainsDAO();
	
    public FetchMovies() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Contains> containsList = new LinkedList<>();
		String id = request.getParameter("id");
		
		try {
			containsList = containsDAO.doRetrieveAllByOrder(id);
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
