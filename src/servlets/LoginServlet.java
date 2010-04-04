package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import util.ValidateData;
import webapp.InsertLoginRecordThread;

import beans.TalkerBean;

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
		String sqlValidate = "select * from talkers where uname= ? and password= ?";
		
		boolean validated = false;
		
		Connection conn = null;
		ResultSet rs = null; 
		PreparedStatement ps = null;
	    try {
	    	conn = ds.getConnection();
		    
		    ps = conn.prepareStatement(sqlValidate);
		    ps.setString(1, cb.getUserName());
		    ps.setString(2, cb.getPassword());
		    rs = ps.executeQuery();
		    
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
			response.sendRedirect("index.jsp?login=f");
		}
	}   	  	    
}