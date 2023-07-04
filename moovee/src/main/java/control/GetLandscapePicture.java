package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MovieDAO;

@WebServlet("/GetLandscapePicture")
public class GetLandscapePicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MovieDAO movieDAO = new MovieDAO();

    public GetLandscapePicture() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(id != null) {
			byte[] bt = null;
			try {
				bt = movieDAO.doRetrieveByKey(id).getLandscapePosterBytes();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
