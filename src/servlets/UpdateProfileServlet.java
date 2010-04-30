package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.TalkerBean;


/**
 * Servlet implementation class for Servlet: Login
 *
 */
 public class UpdateProfileServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   DataSource ds;
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public UpdateProfileServlet() {
		super();
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
	public void processSQLUpdate(HttpServletRequest request) {
	//  get parameters sent through post
		String uname = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		// getting session and TalkerBean
		HttpSession session = request.getSession();
		TalkerBean cb = (TalkerBean)session.getAttribute("talker");
		String oldUserName = cb.getUserName();
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		String gender = request.getParameter("gender");
		
		Calendar cal = Calendar.getInstance();
	    //System.out.println("***ParseInt: " + year + month + day);
		cal.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dob = SQL_DATE_FORMAT.format(cal.getTime());
		//char g = gender.charAt(0);
		
		// check to make sure no duplicate UserName
		
		// if not duplicate, update info and change TalkerBean info in session
		String updateQuery = "UPDATE talkers SET uname= ?, password= ?, email= ?, dob= ?, gender = ? WHERE uname= ?";
		Connection conn = null;
		PreparedStatement ps = null;  // Or PreparedStatement if needed
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    DataSource ds = (DataSource)envContext.lookup("jdbc/Talkmidb");
		    conn = ds.getConnection();
		    
		    ps = conn.prepareStatement(updateQuery);
		    ps.setString(1, uname);
		    ps.setString(2, password);
		    ps.setString(3, email);
		    ps.setString(4, dob);
		    ps.setString(5, gender);
		    ps.setString(6, oldUserName);
		    
		    ps.executeUpdate();
		    
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		    
		    cb.setUserName(uname);
			cb.setPassword(password);
			cb.setEmail(email);
			cb.setGender(gender.charAt(0));
			try {
				cb.setDob(SQL_DATE_FORMAT.parse(dob));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    return;
		} catch (SQLException ex) {
			    // handle any errors
			    ex.printStackTrace();
				return;
		} catch (Exception ex) {
				ex.printStackTrace();
				return;
		}finally {
		    // Always make sure result sets and statements are closed,
		    // and the connection is returned to the pool
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
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processSQLUpdate(request);
		response.sendRedirect("TalkerHome.jsp");
	}   	  	    
}