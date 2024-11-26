package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JobDAO;
import model.Job;

@WebServlet("/UserHomeServlet")
public class UserHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.print(
				"<head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">\r\n"
						+ "  </head>");
		out.println("<body>");
		out.print("<i>" + email + "</i>");
		out.print("<a href='logoutServlet'>LogOut</a>");
		JobDAO jobdao = new JobDAO();
		List<Job> jl = jobdao.getJobs();
		//out.print("<div style='text-align:center;'>");
		out.println("<h1>Available Jobs</h1>");
		for (Job job : jl) {
			out.println("<div><hr>");
			out.println("<h3>" + job.getTitle() + "</h3>");
			out.println("<p>" + job.getDescription() + "</p>");
			out.println("<p>Company: " + job.getCompanyName() + "</p>");
			out.println("<p>Location: " + job.getLocation() + "</p>");
			out.println("<p>Salary: " + job.getSalary() + "</p>");
			out.println("<form action='ApplicationServlet' method='post'>");
			out.println("<input type='hidden' name='jobId' value='" + job.getId() + "'>");
			out.println("<button type='submit'>Apply</button>");
			out.println("</form>");
			out.println("</div><hr>");
		}

	}
}
