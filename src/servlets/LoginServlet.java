package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import beans.TalkerBean;

import util.TalkmiDBUtil;
import util.ValidateData;
import webapp.InsertLoginRecordThread;


/**
 * Servlet implementation class for Servlet: Login
 *
 */
 public class LoginServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   //in seconds
   private static final int REMEMBERME_TIMEOUT = 60*60*24*365;
	 
   TalkerBean cb;
   DataSource ds;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}   	
			
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}  	
	public void init() throws ServletException {
	    try {
	    	//Create a datasource for pooled connections.
	    	ds = (DataSource)getServletContext().getAttribute("DBCPool");

	    	// Register the driver for non-pooled connections.
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    } catch (Exception e) {
	    	throw new ServletException(e.getMessage());
	    }
	}
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		String rememberMe = request.getParameter("rememberme");
		
		if(!ValidateData.validateUserName(un) || !ValidateData.validatePassword(pw)){
			System.out.println("validateData wrong");
			System.out.println("Login Failed!");
			response.sendRedirect("SignIn.jsp?login=f");
			return;
		}
		
		cb = new TalkerBean();
		cb.setUserName(un);
		cb.setPassword(pw);
		
		if (TalkmiDBUtil.validateLogin(cb)) {
			// insert login record into db
			Thread tInsertLoginRecord = new Thread(new InsertLoginRecordThread(cb.getUID()), "InsertLoginRecordThread");
			tInsertLoginRecord.start(); 
			
			// add TalkerBean to session
			request.getSession().setAttribute("talker", cb);
			request.getSession().setAttribute("username", cb.getUserName());
			
			// if rememberme checkbox was selected - save cookie
			if (rememberMe != null) {
				String rememberMeInfo = cb.getUserName()+"|"+cb.getPassword();
				Cookie cookie = new Cookie("rememberme", rememberMeInfo);
				cookie.setMaxAge(REMEMBERME_TIMEOUT);
				response.addCookie(cookie);
			}
			
			response.sendRedirect("TalkerHome.jsp");
		} else {
			System.out.println("Login Failed!");
			System.out.println("The username got is:" + un + " and pw is: " + pw);
			response.sendRedirect("SignIn.jsp?login=f");
		}
	}  
}