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

import dao.UserDAO;
import model.User;

@WebServlet("/Users")
public class ShowAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out  = response.getWriter();
		UserDAO userdao = new UserDAO();
		List<User> ul = userdao.getAllUsers();
		out.print("<head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">\r\n"
				+ "  </head>");
		//String email = request.getParameter("email");
		HttpSession session =request.getSession();
		String email = (String)session.getAttribute("email");
		out.print("<strong>WelCome"+email+"</strong>");
		out.print("<a href='logoutServlet'>LogOut</a>");
		out.print("<div style='text-align:center;'>");
		out.print("<table class='table' border=1><tr><th>UserId</th><th>Name</th><th>Email</th><th>Password</th><th>Role</th><th>Delete Users</th></tr>");
		for(User u :ul) {	
			out.print("<tr>");
			out.print("<td>"+u.getId()+"</td>");
			out.print("<td>"+u.getName()+"</td>");
			out.print("<td>"+u.getEmail()+"</td>");
			out.print("<td>"+u.getPassword()+"</td>");
			out.print("<td>"+u.getRole()+"</td>");
			out.print("<td><a href='DeleteServlet?id="+u.getId()+"'>Delete</a></td>");
			out.print("</tr>");
		}
		
		
		out.print("</table>");
		out.print("</div>");
	}

}
