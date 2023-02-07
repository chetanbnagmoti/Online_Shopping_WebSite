package org.fi.shopping.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PayServlet
 */
@WebServlet("/Pay")
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection connection;
	PreparedStatement preAuthenticate;
	
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
				super.init(config);
				try {
					
					ServletContext appliction = getServletContext();
					
					connection =((Connection)appliction.getAttribute("golbalConnection"));
					
					System.out.println("preAuthenticate :-" + connection);
					
					preAuthenticate = connection.prepareStatement("select * from cards_0013 where cardNO=? and expiryDate=?");
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}

	
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session =request.getSession(false);
		
		if(session==null) {
			response.sendRedirect("loginPage.html");
			return;
		}
		
		
		PrintWriter out =response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Category Page</title>");
		out.println("<link rel=\"stylesheet\"\r\n"
				+ "	href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css\"\r\n"
				+ "	integrity=\"sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N\"\r\n"
				+ "	crossorigin=\"anonymous\">");
		out.println("</head>");
		
		out.println("<body>");
		
		String temp=request.getParameter("cardNo");
		int cardNO =Integer.parseInt(temp);
		String expiryDate=request.getParameter("expiryDate");
		
		
		
		try {
			preAuthenticate.clearParameters();
			
			preAuthenticate.setInt(1,cardNO);
			preAuthenticate.setString(2,expiryDate);
			
			System.out.println(preAuthenticate);
			
			try (ResultSet result = preAuthenticate.executeQuery()) {
				if(result.next()) {
					
//				/	HttpSession session = request.getSession();
					
					session.setAttribute("cardNO", cardNO);
					session.setAttribute("expiryDate", expiryDate);
					Float balance=result.getFloat("balance");
					session.setAttribute("balance", balance);
					
					response.sendRedirect("Trnas");
					
				}
				else {
					out.println("<div class='container m-1 text-center'>");
					out.println("<h3 class='font-weight-bolder text-danger'> Invalid username/Password </h3>");
					out.println("</div>");
					RequestDispatcher reqd = request.getRequestDispatcher("card.html");
					reqd.include(request, response);
				}
			}
			out.println("</body>");
			out.println("</html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
