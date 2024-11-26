package logic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ApplicationDAO;

@WebServlet("/ApplicationServlet")
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int jobId = Integer.parseInt(request.getParameter("jobId"));
		HttpSession session = request.getSession();
		
        int userId = (int)session.getAttribute("userId");
        ApplicationDAO applicationDao = new ApplicationDAO();
        applicationDao.applyToJob(jobId,userId);
        response.getWriter().write("Application submitted successfully!");

	}

	

}
