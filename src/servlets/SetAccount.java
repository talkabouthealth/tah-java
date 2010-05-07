package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.TalkerBean;

/**
 * Servlet implementation class SetAccount
 */
public class SetAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource ds;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetAccount() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init()throws ServletException
    {
    	try{
    		ds = (DataSource)getServletContext().getAttribute("DBCPool");
        	// Register the driver for non-pooled connections.
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
    	}catch(Exception e){
    		throw new ServletException(e.getMessage());
    	}
    }
    
    public void processSetAccount(HttpServletRequest request)
    {
    	String primaryIM = request.getParameter("IMService");
    	String email = request.getParameter("email");
    	
    	HttpSession session = request.getSession();
		TalkerBean cb = (TalkerBean)session.getAttribute("talker");
		String oldUserName = cb.getUserName();
		
		String updateQuery = "UPDATE talkers SET email= ?, PrimaryIM= ? WHERE uname= ?";
		Connection conn = null; 
		PreparedStatement ps = null;  // Or PreparedStatement if needed
		
		
		try{
		conn = ds.getConnection();
	    
	    ps = conn.prepareStatement(updateQuery);
	    ps.setString(1, email);
	    ps.setString(2, primaryIM);
	    ps.setString(3, oldUserName);
	   
	    ps.executeUpdate();
	    
	    ps.close();
	    ps = null;
	    conn.close(); // Return to connection pool
	    conn = null;  // Make sure we don't close it twice
	    
	    cb.setEmail(email);
	    return;
		}catch (SQLException ex) {
		    // handle any errors
		    ex.printStackTrace();
			return;
		}catch (Exception ex) {
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
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processSetAccount(request);
		response.sendRedirect("TalkerHome.jsp");
	}

}
