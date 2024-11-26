package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

@WebServlet("/loginServlet")
public class MyLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String role1 = "candidate";
		String role2 = "employer";
		UserDAO userdao = new UserDAO();
		List<User>	ul = userdao.getAllUsers();
		User u = userdao.searchUser(email, pass, ul);
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		if(u!=null) {
			session.setAttribute("userId", u.getId());
		}
		if(email.equals("admin@gmail.com")&&pass.equals("admin123")) {
			RequestDispatcher rd = request.getRequestDispatcher("adminServlet");
			rd. forward(request, response);
		} 
		else if(u!=null &&email.equals(u.getEmail())&&pass.equals(u.getPassword())&& role1.equals(u.getRole())){
			RequestDispatcher rd = request.getRequestDispatcher("UserHomeServlet");
			rd. forward(request, response);
		}
		else if(u!=null&&email.equals(u.getEmail())&&pass.equals(u.getPassword())&& role2.equals(u.getRole())){
			RequestDispatcher rd = request.getRequestDispatcher("JobServlet");
			rd. forward(request, response);
		
		}
		else{
			response.getWriter().print("Login Failed");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
			}
	}

}
