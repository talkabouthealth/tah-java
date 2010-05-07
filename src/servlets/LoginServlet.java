package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import beans.TalkerBean;

import util.ValidateData;
import webapp.InsertLoginRecordThread;


/**
 * Servlet implementation class for Servlet: Login
 *
 */
 public class LoginServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
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
	public boolean validateLogin(TalkerBean cb){
		// create sql statement
		String sqlValidate = "select * from talkers where uname= ?";
		//String sqlValidate = "select * from talkers";
		boolean validated = false; 
		
		Connection conn = null;
		ResultSet rs = null; 
		PreparedStatement ps = null;
	    try {
	    	conn = ds.getConnection();
	    	if (conn == null)
	    		System.out.println("Didn't get the connection!");
	    	if(!conn.isClosed())
	    		System.out.println("Get the connection!");
//	    	Statement stmt = conn.createStatement();
//	    	ResultSet rst = stmt.executeQuery("select * from talkers");
//	    	System.out.println("Another query is ok" + rst.next());
//	    	rst.close();
//	    	rst = null;
//	    	Statement state = conn.createStatement();
//            ResultSet qry = state.executeQuery("select * from talkers WHERE uname = 'm'");
//            System.out.println("Another query is : " + qry.getObject("uname").toString());
	    	
	    	ps = (PreparedStatement)conn.prepareStatement(sqlValidate);
		    
		    System.out.println("Username stored in Javabean!");
		    System.out.println("Password stored in Javabean!");
		    ps.setString(1, cb.getUserName());
		   // ps.setString(2, cb.getPassword());
		    rs = ps.executeQuery();
		    //System.out.println("Now the status of query for DB is:" + rs.first());
		    
		    // if row in result set, then user is validated
		    if (rs.next()) { 
		    	cb.parseSet(rs);
		    	validated = true;  	
		    }
		    
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice]
		    return validated;
		} catch (SQLException ex) {
		    // handle any errors
		    ex.printStackTrace();
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
		    // Always make sure result sets and statements are closed,
		    // and the connection is returned to the pool
		    if (rs != null) {
		      try { rs.close(); } catch (SQLException e) { ; }
		      rs = null;
		    }
		    if (ps != null) {
		      try { ps.close(); } catch (SQLException e) { ; }
		      ps = null;
		    }
		    if (conn != null) {
		      try { conn.close(); } catch (SQLException e) { ; }
		      conn = null;
		    }
		}
	}
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		
		if(!ValidateData.validateUserName(un) || !ValidateData.validatePassword(pw)){
			System.out.println("validateData wrong");
			System.out.println("Login Failed!");
			response.sendRedirect("index.jsp?login=f");
			return;
		}
		
		cb = new TalkerBean();
		cb.setUserName(un);
		cb.setPassword(pw);
		
		if (validateLogin(cb)) {
	
			// insert login record into db
			Thread tInsertLoginRecord = new Thread(new InsertLoginRecordThread(cb.getUID()), "InsertLoginRecordThread");
			tInsertLoginRecord.start(); 
			
			// add TalkerBean to session
			request.getSession().setAttribute("talker", cb);
			request.getSession().setAttribute("username", cb.getUserName());
			
			response.sendRedirect("TalkerHome.jsp");
		} else {
			System.out.println("Login Failed!");
			System.out.println("The username got is:" + un + " and pw is: " + pw);
			response.sendRedirect("index.jsp?login=f");
		}
	}  
}