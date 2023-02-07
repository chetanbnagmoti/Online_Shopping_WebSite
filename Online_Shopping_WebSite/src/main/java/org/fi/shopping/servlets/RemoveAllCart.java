package org.fi.shopping.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fi.shopping.cart.Cart;
import org.fi.shopping.cart.CartFactory;
import org.fi.shopping.pojo.CartItem;

/**
 * Servlet implementation class RemoveCart
 */
@WebServlet("/RemoveAllCart")
public class RemoveAllCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession(false);
		
		if(session==null) {
			response.sendRedirect("loginPage.html");
			return;
		}
		String tmp=request.getParameter("categoryId");
		int categoryId =Integer.parseInt(tmp);
		
		tmp=request.getParameter("productsId");
		int productsId =Integer.parseInt(tmp);
		
		tmp=request.getParameter("productsPrice");
		float productsPrice =Float.parseFloat(tmp);
		
		CartItem item = new CartItem(categoryId ,productsId,productsPrice);
		
		Cart objCart = (Cart) session.getAttribute("cart");
		
		if(objCart == null) {
			objCart =CartFactory.getInstance(getServletContext());
			session.setAttribute("cart", objCart);
			
			
			
			
		}
		objCart.removeAllCart(item);
		response.sendRedirect("ListCart");
	}
	

	
	
}
