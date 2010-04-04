package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import beans.TopicBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Login
 *
 */
 public class MoreNewTopicsServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   DataSource ds;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public MoreNewTopicsServlet() {
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
public Map<Integer, TopicBean> queryNextTopics(String sLatestTimeStamp){
		
	Connection conn = null;
	PreparedStatement ps = null;  // Or PreparedStatement if needed
	ResultSet rs = null;
	
	Date dNow = Calendar.getInstance().getTime();
	SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String now = SQL_DATE_FORMAT.format(dNow);
	
	Date dLTS = dNow;
	try {
		dLTS = SQL_DATE_FORMAT.parse(sLatestTimeStamp);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	////System.out.println("*** retrieveNextTopicServlet - Latest TimeStamp: Long: " + dLTS.getTime());
	long lLTS = dLTS.getTime() + 1000;
	////System.out.println("*** retrieveNextTopicServlet - Latest TimeStamp: " + lLTS);
	dLTS.setTime(lLTS);
	sLatestTimeStamp = SQL_DATE_FORMAT.format(dLTS);
	////System.out.println("*** retrieveNextTopicServlet - Latest TimeStamp: " + sLatestTimeStamp);
	
	try {
	    String sqlStatement = "SELECT topic_id, topic, display_time FROM topics WHERE display_time BETWEEN '" + sLatestTimeStamp + "' AND '" + now + "' ORDER BY display_time ASC LIMIT 40";		    
	    ////System.out.println("***QueryTopics - SQL: " + sqlStatement);
		
	    conn = ds.getConnection();
	    ps = conn.prepareStatement(sqlStatement);
	    rs = ps.executeQuery();
	    Map <Integer, TopicBean> mapNextTopics = new LinkedHashMap<Integer, TopicBean>(40);
	    
	    while (rs.next()){
	    	TopicBean tb = new TopicBean();
	    	tb.setTopicID(rs.getInt(1));
	    	tb.setTopic(rs.getString(2));
	    	
	    	String sDisplayTime = rs.getString(3);
	    	Date dDisplayTime = SQL_DATE_FORMAT.parse(sDisplayTime);
	    	tb.setDisplayTime(dDisplayTime);
			mapNextTopics.put(tb.getTopicID(), tb);
			////System.out.println("***queryNextTopics - queried: " + tb.getTopic());
	    }
	    rs.close();
	    rs = null;
	    ps.close();
	    ps = null;
	    conn.close(); // Return to connection pool
	    conn = null;  // Make sure we don't close it twice
	    
	    return mapNextTopics;
	} catch (SQLException ex) {
		    // handle any errors
		    ex.printStackTrace();
			return null;
	} catch (Exception ex) {
			ex.printStackTrace();
			return null;
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
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get user id from session
		HttpSession session = request.getSession();
		// check to make sure user is logged in
		if (session.getAttribute("username") == null) { 
			response.sendRedirect("index.jsp");
			return;
		}
		//System.out.println("--------***MoreNewTopicServlet:  Start");
		
		Map<Integer, TopicBean> mNextTopicList = new LinkedHashMap<Integer, TopicBean>(40);
		// get latest timestamp in session
		String sLatestTimeStamp = (String)session.getAttribute("latesttimestamp");
		////System.out.println("*** retrieveNextTopicServlet - Latest TimeStamp: " + sLatestTimeStamp);
			
		mNextTopicList = queryNextTopics(sLatestTimeStamp);
		//System.out.println("***MoreNewTopicServlet:  Number of topics queried: " + mNextTopicList.size());
		
		// get page TopicMap from session
		Map<Integer, TopicBean> mTopics = (Map<Integer, TopicBean>)session.getAttribute("mapTalkmiTopics");
			
		response.setContentType("text/xml;charset=utf-8"); 
		response.setHeader("Cache-Control", "no-cache"); 
	    
		//get the PrintWriter object to write the html page 
		PrintWriter writer = response.getWriter(); 
		writer.println("<topics>");
		
		int count = 0;
		for (TopicBean tbTalkmiTopic : mNextTopicList.values()) {
	   		// get next topic from NextTopicList
			if (!mTopics.containsKey(tbTalkmiTopic.getTopicID())){
				count++;
				// add next topic in NextTopicList to TopicMap
				mTopics.put(tbTalkmiTopic.getTopicID(), tbTalkmiTopic);
				// create xml for the topic
				writer.println("<topic><topicid>" + String.valueOf(tbTalkmiTopic.getTopicID()) + "</topicid><topicvalue>" + tbTalkmiTopic.getTopic() + "</topicvalue></topic>");
				//System.out.println("<topic><topicid>" + String.valueOf(tbTalkmiTopic.getTopicID()) + "</topicid><topicvalue>" + tbTalkmiTopic.getTopic() + "</topicvalue></topic>");
				
				if (count == 5) { // setLatestTimeStamp in session
					SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					session.setAttribute("latesttimestamp", SQL_DATE_FORMAT.format(tbTalkmiTopic.getDisplayTime()));
					break;
				}
			}	
		} 
		
		writer.println("</topics>");
		//close the write
		writer.close();
		//System.out.println("--------***MoreNewTopicServlet:  End");
	}
}