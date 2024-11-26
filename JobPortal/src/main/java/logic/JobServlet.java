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
import model.User;

@WebServlet("/JobServlet")
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.print("<head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">\r\n"
				+ "  </head>");
		out.println("<body>");
		out.print("<i>"+email+"</i>");
		out.println("<h1>Post a Job</h1>");
		out.println("<form action='JobPostingServlet' method='post'>");
		out.println("<label>Job Title:</label><input type='text' name='title'><br>");
		out.println("<label>Description:</label><textarea name='description'></textarea><br>");
		out.println("<label>Company Name:</label><input type='text' name='companyName'><br>");
		out.println("<label>Location:</label><input type='text' name='location'><br>");
		out.println("<label>Salary:</label><input type='number' name='salary'><br>");
		out.println("<button type='submit'>Post Job</button>");
		out.println("</form>");
		
		JobDAO jobdao = new JobDAO();
		List<Job> jl = jobdao.getJobs();
		out.print("<div style='text-align:center;'>");
		out.print("<table class='table' border=1><tr><th>JobId</th><th>Title</th><th>CompanyName</th><th>Description</th><th>Location</th><th>Salary</th><th>Delete Jobs</th></tr>");
		for(Job j:jl) {	
			out.print("<tr>");
			out.print("<td>"+j.getId()+"</td>");
			out.print("<td>"+j.getTitle()+"</td>");
			out.print("<td>"+j.getCompanyName()+"</td>");
			out.print("<td>"+j.getDescription()+"</td>");
			out.print("<td>"+j.getLocation()+"</td>");
			out.print("<td>"+j.getSalary()+"</td>");
			out.print("<td><a href='DeleteJobServlet?id="+j.getId()+"'>Delete</a></td>");
			out.print("</tr>");
		}
		
		out.print("</table>");
		out.print("</div>");
		
		out.println("</body></html>");
		

	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
