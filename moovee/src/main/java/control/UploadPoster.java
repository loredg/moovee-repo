package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.IBeanDAO;
import model.MoviePoster;
import model.PosterDAO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet("/UploadPoster")
public class UploadPoster extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IBeanDAO<MoviePoster> posterDAO = new PosterDAO();

    public UploadPoster() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		out.write("Error: GET method is used but POST method is required");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		for(Part part : request.getParts()) {
			String filename = part.getSubmittedFileName();
			if(filename != null && !filename.equals("")) {
				MoviePoster poster = new MoviePoster();
				poster.setMovieID(id);
				poster.setPosterStream(part.getInputStream());
				try {
					posterDAO.doSave(poster);
				} catch (SQLException e) {
					System.err.println("Error: " + e.getMessage());
				}
			}
		}
		 
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
		dispatcher.forward(request, response);
	}

}
