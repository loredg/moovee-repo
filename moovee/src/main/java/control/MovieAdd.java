package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.IBeanDAO;
import model.Movie;
import model.MovieDAO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet("/MovieAdd")
public class MovieAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static IBeanDAO<Movie> movieDAO = new MovieDAO();
    
    public MovieAdd() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		try {
			if(action != null && action.equals("add")) {
				String title = request.getParameter("title");
				String director = request.getParameter("director");
				String genre = request.getParameter("genre");
				Integer length = Integer.parseInt(request.getParameter("length"));
				Integer release_year = Integer.parseInt(request.getParameter("releaseYear"));
				Double price = Double.parseDouble(request.getParameter("price"));
				Integer qty = Integer.parseInt(request.getParameter("qty"));
				String plot = request.getParameter("plot");
				
				Movie movie = new Movie();
				movie.setTitle(title);
				movie.setDirector(director);
				movie.setGenre(genre);
				movie.setLength(length);
				movie.setReleaseYear(release_year);
				movie.setPrice(price);
				movie.setQty(qty);
				movie.setPlot(plot);
				
				for(Part part : request.getParts()) {
					String filename = part.getSubmittedFileName();
					if(filename != null && !filename.equals("")) {
						movie.setLandscapePosterStream(part.getInputStream());
					}
				}
				
				movieDAO.doSave(movie);
			}
			
			Collection<Movie> movies = movieDAO.doRetrieveAll("titolo");
			request.setAttribute("movies", movies);
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		response.sendRedirect("./admin/admin.jsp");
		
		/*
		 * RequestDispatcher dispatcher =
		 * request.getServletContext().getRequestDispatcher("/admin/admin.jsp");
		 * dispatcher.forward(request, response);
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
