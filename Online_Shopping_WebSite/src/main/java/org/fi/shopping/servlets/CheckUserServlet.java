package org.fi.shopping.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckUserServlet
 */
@WebServlet("/CheckUser")
public class CheckUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection connection;
	PreparedStatement psCheckUser;
	
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ServletContext application = getServletContext();
		
		connection=(Connection) application.getAttribute("golbalConnection");
		try {
			psCheckUser=connection.prepareStatement("select * from users_0013 where userName=?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void destroy() {
		try {
			if(psCheckUser!=null) {
				psCheckUser.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName=request.getParameter("userName");
		
		try {
			psCheckUser.clearParameters();
			psCheckUser.setString(1, userName);
			
			try (ResultSet result = psCheckUser.executeQuery()) {
				PrintWriter out =response.getWriter();
				if(result.next()) {
					out.println("<fout color='red'>User Name Not Available.</font>");
					
				}
				else {
					out.println("<font color='green'>User Name Is Available.</font>");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Check User Fired !!!! ");
		//used to state that the browser should refresh the data with every request .
		//rather than using a cached copy of the response.
		response.setHeader("cache-control", "no-cache,no-store");
	}

}
