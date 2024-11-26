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

@WebServlet("/UserDetailsServlet")
public class UserDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
				int id = Integer.parseInt(request.getParameter("id"));
		PrintWriter out = response.getWriter();
		HttpSession session =request.getSession();
		String email = (String)session.getAttribute("Adminemail");
		out.print("<strong>WelCome "+email+"</strong>");

		UserDAO userdao = new UserDAO();
		List<User> ul = userdao.getAllUsers();
		User u = userdao.getUserById(id, ul);
		if (u != null) {
			out.print("<div style='float:center;'>");
			
			out.print("<table border=1><tr><th>UserId</th><th>Name</th><th>Email</th><th>Password</th><th>Role</th></tr>");
			out.print("<tr>");
			out.print("<td>" + u.getId() + "</td>");
			out.print("<td>" + u.getName() + "</td>");
			out.print("<td>" + u.getEmail() + "</td>");
			out.print("<td>" + u.getPassword() + "</td>");
			out.print("<td>" + u.getRole() + "</td>");
			out.print("</tr>");
			out.print("</table>");
			out.print("</div >");
		}else {
			out.print("<i>User doesn,t exit with this Id</i>");
		}
	}

}
