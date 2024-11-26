package logic;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JobDAO;

@WebServlet("/DeleteJobServlet")
public class DeleteJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		int jobid = Integer.parseInt(request.getParameter("id"));
		JobDAO jobdao = new JobDAO();
		boolean result = jobdao.deleteJob(jobid);
		if(result) {
			RequestDispatcher rd = request.getRequestDispatcher("JobServlet");
			rd.forward(request, response);
		}
	}

	

}
