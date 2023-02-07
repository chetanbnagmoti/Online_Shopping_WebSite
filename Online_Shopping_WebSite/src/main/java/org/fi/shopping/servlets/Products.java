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
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Products
 */
@WebServlet("/Products")
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection connection;
	PreparedStatement preProduct;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		try {
			
			ServletContext application =getServletContext();
			
			connection =(Connection)application.getAttribute("golbalConnection");
		
			System.out.println("Product:- "+ connection);
		 preProduct = connection.prepareStatement("select * from products_0013 where categoryId=?");
		 
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
		try {
			if(preProduct!=null) {
				preProduct.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		out.println("<tr>");
		out.println("<th>productsName</th>");
		out.println("<th>productsDescriptio</th>");
		out.println("<th>productsImageUrl</th>");
		out.println("<th>productsPrice</th>");
		out.println("</tr>");
		out.println("</thead>");

		try {
			String tmp = request.getParameter("categoryId");
			int categoryId = Integer.parseInt(tmp);

			preProduct.setInt(1, categoryId);

			try (ResultSet result = preProduct.executeQuery()) {
				while (result.next()) {
					out.println("<tr class='h5 text-center'>");
					out.println("<td>" + result.getString("productsName") + "</td>");
					out.println("<td>" + result.getString("productsDescriptio") + "</td>");
					out.println("<td><img src='Images/" + result.getString("productsImageUrl")
							+ "' height='120px' width='120px' /></td>");
					out.println("<td>" + result.getString("productsPrice") + "</td>");
					out.println("<td><a class='btn btn-primary text-right text-dark' href='AddCart?categoryId="+ result.getInt("categoryId")+"&productsId="+result.getInt("productsId") + "&productsPrice=" + result.getFloat("productsPrice")+ "'>Add To Cart</a></td>");
					out.println("</tr>");
				}

				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
