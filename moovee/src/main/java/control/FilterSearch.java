package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MovieDAO;

@WebServlet("/FilterSearch")
public class FilterSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MovieDAO movieDAO = new MovieDAO();
       
    public FilterSearch() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<?> movies = null;
		String filter = request.getParameter("filter");
		String filterValue = request.getParameter("filterValue");
		try {
			movies = movieDAO.doRetrieveByFilter(filter, filterValue);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		request.setAttribute("moviesSearched", movies);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/searchResult.jsp");
		dispatcher.forward(request, response);
	}

}
