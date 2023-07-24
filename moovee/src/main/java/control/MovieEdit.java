package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MovieDAO;

/**
 * Servlet implementation class MovieEdit
 */
@WebServlet("/MovieEdit")
public class MovieEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	MovieDAO movieDAO = new MovieDAO();
    
    public MovieEdit() {
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
		String title = request.getParameter("title");
		String director = request.getParameter("director");
		String genre = request.getParameter("genre");
		Double price = Double.parseDouble(request.getParameter("price"));
		Integer release_year = Integer.parseInt(request.getParameter("releaseYear"));
		Integer length = Integer.parseInt(request.getParameter("length"));
		Integer qty = Integer.parseInt(request.getParameter("qty"));
		String id = request.getParameter("id");
		
		try {
			if(!title.equals("") && (title != null)) {
			movieDAO.updateColumn("titolo",  title, id);
			}
			if(!director.equals("") && (director != null)) {
				movieDAO.updateColumn("regista",  director, id);
				}
			if(!genre.equals("") && (genre != null)) {
				movieDAO.updateColumn("genere",  genre, id);
				}
			if((price != 0.00) && (price != null) ) {
				movieDAO.updatePrice(price, id);
				}
			if((release_year!=0) && (release_year != null)) {
				movieDAO.updateInt("anno_uscita", release_year, id);
			}
			if((length != 0) && (length != null)) {
				movieDAO.updateInt("durata_min", length, id);
			}
			if((qty != 0) && (qty != null)) {
				movieDAO.updateInt("qta", qty, id);
			}
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		response.sendRedirect("./admin/admin.jsp");
	}

}
