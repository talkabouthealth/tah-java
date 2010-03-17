package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.TalkerBean;

/**
 * Servlet implementation class for Servlet: Login
 *
 */
 public class LogoutServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   TalkerBean cb;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
	}   	
			
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (null == request.getSession().getAttribute("username")) { 
			response.sendRedirect("index.jsp");
			return;
		}
				
		request.getSession().removeAttribute("talker");
		request.getSession().removeAttribute("username");
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}   	  	    
}