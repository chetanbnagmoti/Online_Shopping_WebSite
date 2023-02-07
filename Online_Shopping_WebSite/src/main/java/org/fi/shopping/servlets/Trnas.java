package org.fi.shopping.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Trnas
 */
@WebServlet("/Trnas")
public class Trnas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
	
	PreparedStatement preUpdate;
	PreparedStatement preamount;
   
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
try {
			
			ServletContext appliction = getServletContext();
			
			connection =((Connection)appliction.getAttribute("golbalConnection"));
			
			System.out.println("preamount :-" + connection);
			
			
			
			preamount=connection.prepareStatement("update cards_0013 set balance=? where cardNO=? and expiryDate=? ");

			preUpdate =connection.prepareStatement("insert into transaction(userName,amount,transactiondate,transactionStatus) values(?,?,?,?)");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {

		try {
			if(preUpdate!=null) {
				preUpdate.close();
			}
			if(preamount!=null) {
				preamount.close();
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
		
		HttpSession session =request.getSession();
		
		if(session==null) {
			response.sendRedirect("loginPage.html");
			return;
		}	
		
		PrintWriter out = response.getWriter();
		
		
		String userName=(String)(session.getAttribute("userName"));
		Date objDate =new Date();
		java.sql.Date date = new java.sql.Date(objDate.getTime());
		double total = (double)session.getAttribute("total");
		out.println(total);
		
		String expiryDate=(String)(session.getAttribute("expiryDate"));
		int cardNO=(int)(session.getAttribute("cardNO"));
		float balance=(float)(session.getAttribute("balance"));
		//balance=(float) (balance-total);
		
		try {
			if(balance>total) {
				balance=(float) (balance-total);	
				preUpdate.clearParameters();
				
				preUpdate.setString(1, userName);
				preUpdate.setDouble(2, total);
				preUpdate.setDate(3, date);
				preUpdate.setBoolean(4, true);
				
				preUpdate.executeUpdate();
				System.out.println("Trans go to");
				preamount.setFloat(1, balance);
				preamount.setInt(2,cardNO );
				preamount.setString(3,expiryDate );
				
				preamount.executeUpdate();
				response.sendRedirect("Complete.jsp");
				total=0.0;
				session.setAttribute("total", total);
				
			}
			else {
				preUpdate.clearParameters();
				
				preUpdate.setString(1, userName);
				preUpdate.setDouble(2, total);
				preUpdate.setDate(3, date);
				preUpdate.setBoolean(4, false);
				
				preUpdate.executeUpdate();
				response.sendRedirect("Sorry.jsp");
				System.out.println("login go to");
				total=0.0;
				session.setAttribute("total", total);
			}
			
			
			
			//response.sendRedirect("Category");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
