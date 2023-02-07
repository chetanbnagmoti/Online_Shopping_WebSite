package org.fi.shopping.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fi.shopping.cart.Cart;
import org.fi.shopping.cart.ShoppingCart;
import org.fi.shopping.pojo.CartItem;


@WebServlet("/ListCart")
public class ListCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session =request.getSession(false);
		
		if(session==null) {
			response.sendRedirect("loginPage.html");
			return;
		}	
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>List-Cart-Page</title>");
		out.println("<link rel=\"stylesheet\"\r\n"
				+ "	href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css\"\r\n"
				+ "	integrity=\"sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N\"\r\n"
				+ "	crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		out.println(" <h1 class='fs-5 text-center'> Welcome "+ session.getAttribute("userName")+"</h1><br/>");
		
	Cart objCart =(Cart)session.getAttribute("cart");
		
		if(objCart== null) {
			
			out.println("Your cart is Empty");
			return;
		}
		
		out.println("<table class='table table-bordered  table-light  table-hover mt-2 p-2'>");
		out.println("<thead class='thead-dark h4'>");
		out.print("<tr>");
		out.println("<th>Category Id </th>");
		out.println("<th>Product Id </th>");
		out.println("<th>Price</th>");
		out.println("</tr>");
		out.println("</thead>");
		
		double total =0.0;
		
		Iterator<CartItem> iter = objCart.getAllItems();
		
		while(iter.hasNext()) {
			CartItem item = iter.next();
			
			out.println("<tr class='h5 text-center'>");
			out.println("<td>" + item.getCategoryId() + "</td>");
			out.println("<td>" + item.getProductsId() + "</td>");
			out.println("<td>" + item.getProductsPrice() + "</td>");
			out.println("<td><a class='btn btn-primary text-right text-dark' href='RemoveCart?categoryId="+ item.getCategoryId()+"&productsId="+item.getProductsId() + "&productsPrice=" + item.getProductsPrice()+ "'>Remove To Cart</a></td>");
			out.println("<td><a class='btn btn-warning text-right text-dark' href='RemoveAllCart?categoryId="+ item.getCategoryId()+"&productsId="+item.getProductsId() + "&productsPrice=" + item.getProductsPrice()+ "'>RemoveAll</a></td>");
			out.println("</tr>");
			total+=item.getProductsPrice();
			
			
		}
		out.println("</table></br>");
		out.println(" <b class='text-danger text-center h3 '> Total :"+ total + "</b><br/>");
		out.println("<a class='btn btn-primary text-dark' href='Category'>Continue Shopping </a> &nbsp;&nbsp;&nbsp;&nbsp;");
		out.println("<a class='btn btn-warning text-dark' href='card.html'>Continue Payment </a> &nbsp;&nbsp;&nbsp;&nbsp;");
		

		session.setAttribute("total", total);
		
		out.println("<a class='btn btn-success text-dark' href='Logout'>Sign Out</a><br/>");
		out.println("</body>");
		out.println("</html>");
	}

}
