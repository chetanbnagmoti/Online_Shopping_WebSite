package org.fi.shopping.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(loadOnStartup = 100 ,urlPatterns = {"/Authenticate"})
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection connection;
	PreparedStatement preAuthenticate;
	
   @Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		try {
			
			ServletContext application =getServletContext();
			
			String driverClass=application.getInitParameter("driverClass");
			String url =application.getInitParameter("url");
			String user=application.getInitParameter("dbUser");
			String pwd=application.getInitParameter("dbpaswword");
			
			Class.forName(driverClass);
			
		 connection = DriverManager.getConnection(url,user,pwd);
		 
		 application.setAttribute("golbalConnection", connection);
		 
		 System.out.println("Authenticate: " + connection);
		 
		 preAuthenticate = connection.prepareStatement("select * from users_0013 where userName=? and password=?");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		
		try {
			if(preAuthenticate!=null) {
				preAuthenticate.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out =response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Category Page</title>");
		out.println("<link rel=\"stylesheet\"\r\n"
				+ "	href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css\"\r\n"
				+ "	integrity=\"sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N\"\r\n"
				+ "	crossorigin=\"anonymous\">");
		out.println("</head>");
		
		
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		
		
		try {
			preAuthenticate.clearParameters();
			
			preAuthenticate.setString(1,userName);
			preAuthenticate.setString(2,password);
			

			try (ResultSet result = preAuthenticate.executeQuery()) {
				if(result.next()) {
					
					HttpSession session = request.getSession();
					
					session.setAttribute("userName", userName);
					
					response.sendRedirect("Category");
					
				}
				else {
					out.println("<div class='container m-1  text-center'>");
					out.println("<h3 class='font-weight-bolder text-danger'> Invalid username/Password </h3>");
					out.println("</div>");
					RequestDispatcher reqd = request.getRequestDispatcher("loginPage.html");
					reqd.include(request, response);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
