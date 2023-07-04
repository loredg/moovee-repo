package control;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class MovieDelete
 */
@WebServlet("/MovieDelete")
public class MovieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static IBeanDAO<Movie> MovieDAO = new MovieDAO();

	public MovieDelete() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			if (action != null && action.equals("delete")) {
				String id = request.getParameter("delete_id");
				MovieDAO.doDelete(id);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		response.sendRedirect("./admin/admin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
