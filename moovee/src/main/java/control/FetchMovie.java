package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Movie;
import model.MovieDAO;

/**
 * Servlet implementation class FetchMovie
 */
@WebServlet("/FetchMovie")
public class FetchMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MovieDAO movieDAO = new MovieDAO();
	
    public FetchMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println(id);
		Movie m = new Movie();
		String referer = (String) request.getAttribute("referer");
		
		try {
			m = movieDAO.doRetrieveByKey(id);
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		request.setAttribute("movie", m);
		request.getServletContext().getRequestDispatcher(referer).forward(request, response);
	}

}
