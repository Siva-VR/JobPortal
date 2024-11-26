package logic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/adminServlet")
public class adminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.print("<head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">\r\n"
				+ "  </head>");
		//String email = request.getParameter("email");
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		
		out.print("<strong>WelCome "+email+"</strong>");
		out.print("<a href='logoutServlet'>LogOut</a>");
		out.print("<div style='text-align:center;'>");
		out.print("<h2>Admin Home</h2>");
		out.print("<a href='Users'>Show All Users Details</a><br>");
		out.print("<a href='GetUserByIdServlet'>Show User Details By ID</a>");
		out.print("</div>");
	}

}
