package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import org.joda.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.IBeanDAO;
import model.Movie;
import model.MovieDAO;
/**
 * Servlet implementation class NewMovies
 */
@WebServlet("/NewMovies")
public class NewMovies extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IBeanDAO<Movie> movieDAO = new MovieDAO();
	
    public NewMovies() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Movie> newMovies = null;
		try {
			newMovies = movieDAO.doRetrieveSinceDate(new LocalDate().minusDays(7));
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		request.setAttribute("newMovies", newMovies);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
