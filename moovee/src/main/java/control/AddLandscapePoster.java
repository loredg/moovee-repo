package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.MovieDAO;


@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet("/AddLandscapePoster")
public class AddLandscapePoster extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MovieDAO movieDAO = new MovieDAO();
       
    public AddLandscapePoster() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		for(Part part : request.getParts()) {
			String filename = part.getSubmittedFileName();
			if(filename != null && !filename.equals("")) {
				try {
					movieDAO.addLandscapePoster(part.getInputStream(), id);
				}catch(SQLException e) {
					System.out.println("Error: " + e.getMessage());
				}
			}
		}
		/*
		 * RequestDispatcher dispatcher =
		 * request.getServletContext().getRequestDispatcher("/admin/admin.jsp");
		 * dispatcher.forward(request, response);
		 */
		
		response.sendRedirect("./admin/admin.jsp");
	}

}
