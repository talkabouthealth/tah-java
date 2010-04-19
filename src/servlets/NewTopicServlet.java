package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.tah.matcher.TAHmatcher;

import beans.LiveConversationBean;
import beans.TalkerBean;
import beans.TopicBean;

import webapp.LiveConversationsSingleton;


/**
 * Servlet implementation class for Servlet: Login
 *
 */
 public class NewTopicServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   DataSource ds;
   //TAHmatcher tm ;
   int Conv_Id = 100;
   String Conv_Title = "This is a test conversation";
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public NewTopicServlet() {
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
	public boolean insertTopic(TopicBean tb){
		// create sql statement
		SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlDate = SQL_DATE_FORMAT.format(tb.getCreationDate()); 
		//String sqlInsert = "Insert into topics (uid, topic, creation_date, display_time) values ('" + tb.getuID() + "', '" + tb.getTopic() + "', '" + sqlDate + "', '" + sqlDate + "')";
		String sqlInsert = "Insert into topics (uid, topic, creation_date, display_time) values ( ? , ? , ? , ? )";
		
		Connection conn = null;
		PreparedStatement ps = null;  // Or PreparedStatement if needed
		
		try {
			conn = ds.getConnection();
		    
			ps = conn.prepareStatement(sqlInsert);
			ps.setString(1, String.valueOf(tb.getuID()));
			ps.setString(2, tb.getTopic());
			ps.setString(3, sqlDate);
			ps.setString(4, sqlDate);
			ps.executeUpdate();
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
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
		    if (ps != null) {
		      try { ps.close(); } catch (SQLException e) { ; }
		      ps = null;
		    }
		    if (conn != null) {
		      try { conn.close(); } catch (SQLException e) { ; }
		      conn = null;
		    }
		}
		return true;
	}
public int queryTopicID(TopicBean tb){
		
		SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlDate = SQL_DATE_FORMAT.format(tb.getCreationDate());
				
		// create sql statement
		// String sqlQuery = "select topic_id from topics where uid = '" + tb.getuID() + "' and creation_date = '" + sqlDate + "'";
		String sqlQuery = "select topic_id from topics where uid = ? and creation_date = ?";
		Connection conn = null;
		PreparedStatement ps = null;  // Or PreparedStatement if needed
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			
		    ps = conn.prepareStatement(sqlQuery);
		    ps.setString(1, String.valueOf(tb.getuID()));
		    ps.setString(2, sqlDate);
		    rs = ps.executeQuery();
		    int topicID = 0;
		    if (rs.next()) {
		    	topicID =  rs.getInt("topic_id");
		    }
		    
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		    return topicID;
		} catch (SQLException ex) {
		    // handle any errors
			ex.printStackTrace();
			return 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
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
	public void writeNothing(HttpServletResponse response){
		// return topicid and topic to front page
		response.setContentType("text/html;charset=utf-8"); 
		response.setHeader("Cache-Control", "no-cache"); 
	    
		//newTopicVar = newTopicVar.replaceAll("'", "\\\\'");
		//get the PrintWriter object to write the html page 
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.println("|");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return;
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
				
		TalkerBean talker = (TalkerBean)session.getAttribute("talker");
		int uID = talker.getUID();
//		
//		tm = new TAHmatcher(uID, Conv_Id, Conv_Title);  // call the interface defined in TAH-matcher
//		try {
//			tm.matcher();
//			System.out.println(tm.getUserList().size());
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		System.out.println(tm.getUserList().size());
		
		// get new topic text
		//String newTopicVar = request.getParameter("newtopic");
		String newTopic = request.getParameter("newtopic");
		System.out.println("****NewTopicServlet - Topic: " + newTopic + " Talker: " + talker.getUserName());
		
		// initiate new conversation room
		
		// call notification module
		// start new thread
		
		// open talker window
		// start new thread
			
		// create topic bean
		TopicBean topic = new TopicBean();
		topic.setTopic(newTopic);
		topic.setuID(uID);
		
		Calendar cal = Calendar.getInstance();
		topic.setCreationDate(cal.getTime());
		
		// insert new topic into database
		if(!insertTopic(topic)) {
			Exception e = new Exception("DB Problem - Topic not inserted into DB");
			e.printStackTrace();
			writeNothing(response);
			return;
		}
		
		// query for the topicID
		int topicID = queryTopicID(topic);
		
		if (topicID != 0) {
			topic.setTopicID(topicID);
	    }
		else {
			Exception e = new Exception("DB Problem - Could not find topic id after inserting new topic into database!!");
			e.printStackTrace();
			writeNothing(response);
			return;
		}
		
		// create new LiveConvBean
		LiveConversationBean lcb = new LiveConversationBean();
		
		// add topic to LiveConvBean
		lcb.setTopic(topic);
		
		// add talker to LiveConvBean 
		lcb.addTalker(talker.getUID(), talker);
				
		// add LiveConvBean to LiveConversationSingleton
		LiveConversationsSingleton lcs = LiveConversationsSingleton.getReference();
    	lcs.addConversation(topicID, lcb);
		
		// add topic to TopicMap in session - keeps track of topics on the page so no duplicates
		Map<Integer, TopicBean> mTopics = (Map<Integer, TopicBean>)session.getAttribute("mapTalkmiTopics");
		mTopics.put(topicID, topic);
		
		newTopic = newTopic.replaceAll("'", "&#39;");
		newTopic = newTopic.replaceAll("\\|", "&#124;");
		
		// return topicid and topic to front page
		response.setContentType("text/html;charset=utf-8"); 
		response.setHeader("Cache-Control", "no-cache"); 
	    
		//get the PrintWriter object to write the html page 
		PrintWriter writer = response.getWriter(); 
		writer.println(String.valueOf(topicID) + "|" + newTopic);
		//close the write
		writer.close();
		//System.out.println("--------****NewTopicServlet - Ending!!!");
		// redirect to talker home
		//response.sendRedirect("TalkerHome.jsp");
	}   	  	    
}