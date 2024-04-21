package easyBallot.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class Registration
 */

@WebServlet("/register")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("name");
		String upwd = request.getParameter("pass");
		String uemail = request.getParameter("email");
		String ucontact = request.getParameter("contact");
		RequestDispatcher dispatcher = null;
		 Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyballot?useSSL=false","root","@Swayam9");
			PreparedStatement pst = con.prepareStatement("insert into users(uname,upwd,uemail,ucontact) values(?,?,?,?)");
			pst.setString(1, uname);
			pst.setString(2, upwd);
			pst.setString(3, uemail);
			pst.setString(4, ucontact);
			int rowcount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			
			if(rowcount > 0) {
				request.setAttribute("status", "success");
			}
			else {
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
		}
		
	}

}
