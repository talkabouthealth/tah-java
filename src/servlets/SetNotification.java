package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

import beans.TalkerBean;

/**
 * Servlet implementation class SetNotification
 */
public class SetNotification extends HttpServlet {
	private static final long serialVersionUID = 1L;     
 
	DataSource ds;
	private int notifyfreq;
	private int notifytime;
	
    public SetNotification() {
        super();
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
    
    public void processSetNotification(HttpServletRequest request)
    {
    	String temp = request.getParameter("notifyFre");
    	if(( request.getParameter("notifyFre")==null) || ( request.getParameter("notifyTime")== null) )
    		return;
    	
    	notifyfreq = Integer.parseInt(request.getParameter("notifyFre"));
    	notifytime = Integer.parseInt(request.getParameter("notifyTime"));
    	
    	System.out.println("Setting Notification!");
    	
    	HttpSession session = request.getSession();
    	TalkerBean cb = (TalkerBean)session.getAttribute("talker");
    	String oldUserName = cb.getUserName();
    	
    	String updateQuery = "UPDATE talkers SET notifyfrequency= ?, notifytime= ? WHERE uname= ?";
		Connection conn = null; 
		PreparedStatement ps = null;  // Or PreparedStatement if needed
    	
    	try{
    		conn = ds.getConnection();
    	    
    	    ps = conn.prepareStatement(updateQuery);
    	    ps.setInt(1, notifyfreq);
    	    ps.setInt(2, notifytime);
    	    ps.setString(3, oldUserName);
    	   
    	    ps.executeUpdate();
    	    
    	    ps.close();
    	    ps = null;
    	    conn.close(); // Return to connection pool
    	    conn = null;  // Make sure we don't close it twice
    	    
    	    cb.setNfreq(notifyfreq);
    	    cb.setNtime(notifytime);
    	    
    	    //cb.setEmail(email);
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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processSetNotification(request);
		
		response.sendRedirect("Settings.jsp");
	}

}
