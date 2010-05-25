package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import beans.TalkerBean;
import beans.TopicBean;


public class TalkmiDBUtil {

	private static final String DATA_SOURCE_NAME = "jdbc/talkmidb";

	public static Map<Integer, TopicBean> queryTopics(){
		
		Connection conn = null;
		PreparedStatement ps = null;  // Or PreparedStatement if needed
		ResultSet rs = null;
		
		try {
		    Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:comp/env");
		    DataSource ds = (DataSource)envContext.lookup(DATA_SOURCE_NAME);
		    conn = ds.getConnection();
		    
		    String sqlStatement = "SELECT topic_id, topic, display_time FROM topics ORDER BY display_time DESC LIMIT 40";
		    ps = conn.prepareStatement(sqlStatement);
		    rs = ps.executeQuery();
		    Map <Integer, TopicBean> mapTopics = new LinkedHashMap<Integer, TopicBean>(40);
		    while (rs.next()){
		    	TopicBean tb = new TopicBean();
		    	tb.setTopicID(rs.getInt(1));
		    	tb.setTopic(rs.getString(2));
		    	
		    	String sDisplayTime = rs.getString(3);
		    	SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
				Date dDisplayTime = SQL_DATE_FORMAT.parse(sDisplayTime);
		    	tb.setDisplayTime(dDisplayTime);
				
		    	mapTopics.put(tb.getTopicID(), tb);
			}
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		    return mapTopics;
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
	public static Map<Integer, TopicBean> queryOlderTopics(String sMaxDisplayTime){
		
		//System.out.println("***QueryTopics - MaxDisplayTime: " + sMaxDisplayTime);
		Connection conn = null;
		PreparedStatement ps = null;  // Or PreparedStatement if needed
		ResultSet rs = null;
		
		try {
		    Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    DataSource ds = (DataSource)envContext.lookup(DATA_SOURCE_NAME);
		    conn = ds.getConnection();
		    //2009-02-25 22:48:22
		    String sqlStatement = "SELECT topic_id, topic, display_time FROM topics WHERE display_time BETWEEN '0' AND '" + sMaxDisplayTime + "' ORDER BY display_time DESC LIMIT 40";		    
		    //System.out.println("***QueryTopics - SQL: " + sqlStatement);
			
		    ps = conn.prepareStatement(sqlStatement);
		    rs = ps.executeQuery();
		    Map <Integer, TopicBean> mapTopics = new LinkedHashMap<Integer, TopicBean>(40);
		    while (rs.next()){
		    	TopicBean tb = new TopicBean();
		    	tb.setTopicID(rs.getInt(1));
		    	tb.setTopic(rs.getString(2));
		    	
		    	String sDisplayTime = rs.getString(3);
		    	SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
				Date dDisplayTime = SQL_DATE_FORMAT.parse(sDisplayTime);
		    	tb.setDisplayTime(dDisplayTime);
				mapTopics.put(tb.getTopicID(), tb);
			}
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		   
		    return mapTopics;
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
	public static Map<Integer, TopicBean> queryNewerTopics(String sMinDisplayTime){
		
		//System.out.println("***QueryTopics - MinDisplayTime: " + sMinDisplayTime);
		Connection conn = null;
		PreparedStatement ps = null;  // Or PreparedStatement if needed
		ResultSet rs = null;
		
		Date dNow = Calendar.getInstance().getTime();
		SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = SQL_DATE_FORMAT.format(dNow);
		
		try {
		    Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    DataSource ds = (DataSource)envContext.lookup(DATA_SOURCE_NAME);
		    conn = ds.getConnection();
		    //2009-02-25 22:48:22
		    String sqlStatement = "SELECT topic_id, topic, display_time FROM topics WHERE display_time BETWEEN '" + sMinDisplayTime + "' AND '" + now + "' ORDER BY display_time ASC LIMIT 40";		    
		    //System.out.println("***QueryTopics - SQL: " + sqlStatement);
			
		    ps = conn.prepareStatement(sqlStatement);
		    rs = ps.executeQuery();
		    Map <Integer, TopicBean> mapTopics = new LinkedHashMap<Integer, TopicBean>(40);
		    
		    rs.setFetchDirection(ResultSet.FETCH_REVERSE);
		    rs.last();
		    while (rs.previous()){
		    	TopicBean tb = new TopicBean();
		    	tb.setTopicID(rs.getInt(1));
		    	tb.setTopic(rs.getString(2));
		    	
		    	String sDisplayTime = rs.getString(3);
		    	Date dDisplayTime = SQL_DATE_FORMAT.parse(sDisplayTime);
		    	tb.setDisplayTime(dDisplayTime);
				mapTopics.put(tb.getTopicID(), tb);
			}
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		    
		    return mapTopics;
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
	public static String queryLastActiveDate(String uid){
	
		Connection conn = null;
		PreparedStatement ps = null;  // Or PreparedStatement if needed
		ResultSet rs = null;
		
		try {
		    Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    DataSource ds = (DataSource)envContext.lookup(DATA_SOURCE_NAME);
		    conn = ds.getConnection();
		    
		    String sqlStatement = "SELECT login_time FROM login_history WHERE uid = '" + uid + "' ORDER BY login_time DESC LIMIT 1";		    
		    ps = conn.prepareStatement(sqlStatement);
		    rs = ps.executeQuery();
		    String lastActiveDate = null;
		    while (rs.next()){
		    	lastActiveDate = rs.getString(1);
		    	SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
				Date dlastActiveDate = SQL_DATE_FORMAT.parse(lastActiveDate);
		    	lastActiveDate = SQL_DATE_FORMAT.format(dlastActiveDate);
			}
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		    return lastActiveDate;
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

	public static Map<Integer, TopicBean> queryUserCreatedTopics(String uid){
		
		Connection conn = null;
		PreparedStatement ps = null;  // Or PreparedStatement if needed
		ResultSet rs = null;
		
		try {
		    Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    DataSource ds = (DataSource)envContext.lookup(DATA_SOURCE_NAME);
		    conn = ds.getConnection();
		    
		    String sqlStatement = "SELECT topic_id, topic, creation_date FROM topics WHERE uid = '" + uid + "' ORDER BY topic_id DESC LIMIT 40";
		    ps = conn.prepareStatement(sqlStatement);
		    rs = ps.executeQuery();
		    Map <Integer, TopicBean> mapTopics = new LinkedHashMap<Integer, TopicBean>(40);
		    while (rs.next()){
		    	TopicBean tb = new TopicBean();
		    	tb.setTopicID(rs.getInt(1));
		    	tb.setTopic(rs.getString(2));
		    	String sCreationDate = rs.getString(3);
		    	SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
				Date dCreationDate = SQL_DATE_FORMAT.parse(sCreationDate);
		    	tb.setCreationDate(dCreationDate);
				mapTopics.put(tb.getTopicID(), tb);
			}
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		    return mapTopics;
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
	public static String queryLastTalkTopic(String uid, String uid2){
	
		Connection conn = null;
		PreparedStatement ps = null;  // Or PreparedStatement if needed
		ResultSet rs = null;
		
		try {
		    Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    DataSource ds = (DataSource)envContext.lookup(DATA_SOURCE_NAME);
		    conn = ds.getConnection();
		    
		    String sqlStatement = "SELECT conversations.topic_id FROM conversations INNER JOIN topics ON conversations.topic_id = topics.topic_id WHERE ((conversations.uid1 = '" + uid + "' AND conversations.uid2 = '" + uid2 + "') OR (conversations.uid2 = '" + uid + "' AND conversations.uid1 = '" + uid + "')) ORDER BY conversations.end_time DESC LIMIT 1";
		    ps = conn.prepareStatement(sqlStatement);
		    rs = ps.executeQuery();
		    String sTopicID = null;
		    while (rs.next()){
		    	sTopicID = rs.getString(1);
		    }
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		    return sTopicID;
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
	public static void printTopicMapKeyValues(Map<Integer, TopicBean> m){
		for (Map.Entry<Integer, TopicBean> e : m.entrySet()) {
		    System.out.println(e.getKey() + ": " + e.getValue().getTopic());
		}
	}
	
	/**
	 * For now, there will only be the breast cancer community,
	 * so this method returns total number of users (talkers) in the DB.
	 */
	public static int getNumberOfMembers(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
		    Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    DataSource ds = (DataSource)envContext.lookup(DATA_SOURCE_NAME);
		    conn = ds.getConnection();
		    
		    ps = conn.prepareStatement("SELECT count(*) FROM talkers");
		    rs = ps.executeQuery();
		    
		    int numberOfMembers = 0;
		    if (rs.next()) {
		    	numberOfMembers = rs.getInt(1);
			}
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		   
		    return numberOfMembers;
		} catch (SQLException ex) {
			    // handle any errors
			    ex.printStackTrace();
				return -1;
		} catch (Exception ex) {
				ex.printStackTrace();
				return -1;
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
	
	/**
	 * # of conversations for given User ID
	 */
	public static int getNumberOfConversations(int uid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
		    Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    DataSource ds = (DataSource)envContext.lookup(DATA_SOURCE_NAME);
		    conn = ds.getConnection();
		    
		    ps = conn.prepareStatement("SELECT count(*) FROM topics WHERE uid = ?");
		    ps.setInt(1, uid);
		    rs = ps.executeQuery();
		    
		    int numberOfConversations = 0;
		    if (rs.next()) {
		    	numberOfConversations = rs.getInt(1);
			}
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		   
		    return numberOfConversations;
		} catch (SQLException ex) {
			    // handle any errors
			    ex.printStackTrace();
				return -1;
		} catch (Exception ex) {
				ex.printStackTrace();
				return -1;
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
	
	public static boolean validateLogin(TalkerBean cb){
		// create sql statement
		String sqlValidate = "select * from talkers where uname= ?";
		//String sqlValidate = "select * from talkers";
		boolean validated = false; 
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		    Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:comp/env");
		    DataSource ds = (DataSource)envContext.lookup(DATA_SOURCE_NAME);
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
}
