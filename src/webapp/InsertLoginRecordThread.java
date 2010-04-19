package webapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class InsertLoginRecordThread implements Runnable {
	
	private int uID;
	
	public InsertLoginRecordThread(int cbID)
	{
		uID = cbID;
	}
	public void run(){
		//System.out.println("--------***InsertLoginRecordThread Start!!!!");
		Date now = Calendar.getInstance().getTime(); 
		SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlDate = SQL_DATE_FORMAT.format(now);
		
		String sUID = String.valueOf(uID);

		// create sql statement
		String loginRecordInsert = "Insert into login_history (uid, login_time) values ('" + sUID + "', '" + sqlDate + "')";
		//System.out.println("***InsertLoginRecordThread - Insert: " + loginRecordInsert);
		Connection conn = null;
		PreparedStatement ps = null;  // Or PreparedStatement if needed
		
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:comp/env");
		    DataSource ds = (DataSource)envContext.lookup("jdbc/Talkmidb");
			
		    conn = ds.getConnection();
		        
			ps = conn.prepareStatement(loginRecordInsert);
		    ps.executeUpdate(loginRecordInsert);
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		    // System.out.println("--------***InsertLoginRecordThread End!!!!");
		} catch (SQLException ex) {
		    // handle any errors
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
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
}
