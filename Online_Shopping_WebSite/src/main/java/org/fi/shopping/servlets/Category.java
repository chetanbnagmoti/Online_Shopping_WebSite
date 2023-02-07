package org.fi.shopping.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Category
 */
@WebServlet(loadOnStartup = 20 ,urlPatterns = {"/Category"})
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection connection;
	PreparedStatement preCategory;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		try {
			
			ServletContext appliction = getServletContext();
			
			connection =((Connection)appliction.getAttribute("golbalConnection"));
			
			System.out.println("Category :-" + connection);
			
		 preCategory = connection.prepareStatement("select * from category_0013 ");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    @Override
	public void destroy() {
		// TODO Auto-generated method stub
		
		try {
			if(preCategory!=null) {
				preCategory.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Category Page</title>");
		out.println("<link rel=\"stylesheet\"\r\n"
				+ "	href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css\"\r\n"
				+ "	integrity=\"sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N\"\r\n"
				+ "	crossorigin=\"anonymous\">");
		out.println("</head>");
		
		out.println("<body>");
		
		HttpSession session =request.getSession(false);
		
		if(session==null) {
			response.sendRedirect("loginPage.html");
			return;
		}
		out.println(" <h1 class='fs-5 text-center'> Welcome "+ session.getAttribute("userName")+"</h1><br/>");
		
		out.println("<table class='table table-bordered  table-light  table-hover mt-2 p-2'>");
		out.println("<thead class='thead-dark h4'>");
		out.println("<tr class=''>");
		out.println("<th>categoryId</th>");
		out.println("<th>categoryName</th>");
		out.println("<th>categoryDescription</th>");
		out.println("<th>categoryImageUrl</th>");
		out.println("</tr>");
		out.println("</thead>");
		
				try (ResultSet result = preCategory.executeQuery()) {
					while(result.next()) {
						out.println("<tr class='h5 text-center'>");
						out.println("<td>"+result.getInt("categoryId")+"</td>");
						out.println("<td><a href='Products?categoryId="+result.getInt("categoryId")+"'>" +result.getString("categoryName")+ "</a></td>");
						out.println("<td>"+result.getString("categoryDescription")+"</td>");
						out.println("<td><img src='Images/"+result.getString("categoryImageUrl")+ "' height='120px' width='120px'/></td>");
						out.println("</tr>");
					}
				
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}

}
