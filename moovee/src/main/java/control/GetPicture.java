package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.IBeanDAO;
import model.Movie;
import model.MovieDAO;

@WebServlet("/GetPicture")
public class GetPicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IBeanDAO<Movie> movieDAO = new MovieDAO();
       

    public GetPicture() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(id != null) {
			byte[] bt = null;
			try {
				bt = movieDAO.doRetrieveByKey(id).getPosterBytes();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ServletOutputStream out = response.getOutputStream();
			if(bt!= null) {
				out.write(bt);
				response.setContentType("image/jpeg");
			}
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
