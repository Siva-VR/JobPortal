package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JobDAO;
import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class JobPostingServlet
 */
@WebServlet("/JobPostingServlet")
public class JobPostingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String des = request.getParameter("description");
		String cname = request.getParameter("companyName");
		String location = request.getParameter("location");
		double salary = Double.parseDouble(request.getParameter("salary"));
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		int uid = (int)session.getAttribute("userId");
		PrintWriter out = response.getWriter();
		
		JobDAO job = new JobDAO();
		boolean result =job.addJob(title, des, cname, location, salary,uid);
		if(result) {
			out.print("Job Posted sucessfully");
			RequestDispatcher rd = request.getRequestDispatcher("JobServlet");
			rd.include(request, response);
		}
		else {
			out.print("failed to post the job ");
			RequestDispatcher rd = request.getRequestDispatcher("JobServlet");
			rd.include(request, response);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
