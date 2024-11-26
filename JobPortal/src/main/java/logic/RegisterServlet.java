package logic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// taking inputs from url through the request.getParameter
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String role = request.getParameter("role");
		
		//To give response on webPage we use to printWriter class 
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		UserDAO userdao = new UserDAO();
		boolean result = userdao.addUser(name, email, pass, role);
		if(result) {
			out.print("Registered Successfully");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}else {
			out.print("You are already registered with this details please login");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
	}

	

}
