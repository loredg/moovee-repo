package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.IBeanDAO;
import model.Movie;
import model.MovieDAO;

@WebServlet("/MovieDisplay")
public class MovieDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IBeanDAO<Movie> movieDAO = new MovieDAO();

	public MovieDisplay() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Collection<Movie> movies = null;
		request.removeAttribute("movies");
		String referer = (String) request.getAttribute("referer");
		try {
			movies = movieDAO.doRetrieveAll("titolo");
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		request.setAttribute("movies", movies);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(referer);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
