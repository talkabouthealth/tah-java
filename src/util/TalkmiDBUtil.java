package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.HealthItemBean;
import beans.TalkerBean;
import beans.TalkerDiseaseBean;
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
		    
		    String sqlStatement = "SELECT topic_id, topic, display_time FROM topics ORDER BY display_time DESC LIMIT 20";
		    ps = conn.prepareStatement(sqlStatement);
		    rs = ps.executeQuery();
		    Map <Integer, TopicBean> mapTopics = new LinkedHashMap<Integer, TopicBean>(20);
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
			conn = getConnection();
		    
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
			conn = getConnection();
		    
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
		String sqlValidate = "select * from talkers where uname = ? and password = ?";
		//String sqlValidate = "select * from talkers";
		boolean validated = false; 
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
	    	ps = (PreparedStatement)conn.prepareStatement(sqlValidate);
		    ps.setString(1, cb.getUserName());
		    ps.setString(2, cb.getPassword());
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

	public static TalkerBean getTalkerByEmail(String email){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
		    
		    ps = conn.prepareStatement("SELECT * FROM talkers WHERE email = ?");
		    ps.setString(1, email);
		    rs = ps.executeQuery();
		    
		    TalkerBean user = null;
		    if (rs.next()) {
		    	user = new TalkerBean();
		    	user.parseSet(rs);
			}
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		   
		    return user;
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
	
	public static TalkerBean getTalkerByUsername(String userName){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
		    
		    ps = conn.prepareStatement("SELECT * FROM talkers WHERE uname = ?");
		    ps.setString(1, userName);
		    rs = ps.executeQuery();
		    
		    TalkerBean user = null;
		    if (rs.next()) {
		    	user = new TalkerBean();
		    	user.parseSet(rs);
			}
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		   
		    return user;
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
	
	public static void updateTalker(TalkerBean user) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
		    conn = getConnection();
		    
		    ps = conn.prepareStatement("UPDATE talkers " +
		    		"SET uname = ?, password = ?, email = ?, " +
		    		"invitations = ?, profilepreferences = ?, " +
		    		"notifyfrequency = ?, notifytime = ?, ctype = ?, " +
		    		"PrimaryIM = ? WHERE uid = ?");
		    ps.setString(1, user.getUserName());
		    ps.setString(2, user.getPassword());
		    ps.setString(3, user.getEmail());
		    ps.setInt(4, user.getInvitations());
		    ps.setInt(5, user.profilePreferencesToInt());
		    ps.setInt(6, user.getNfreq());
		    ps.setInt(7, user.getNtime());
		    ps.setString(8, user.getCtypeStr());
		    ps.setString(9, user.getIM());
		    ps.setInt(10, user.getUID());
		    
		    ps.executeUpdate();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
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
	
	public static Map<Integer, String> getValuesByDisease(String type, int diseaseId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
		    
		    ps = conn.prepareStatement("SELECT id, name FROM disease_"+type+" WHERE disease_id = ?");
		    ps.setInt(1, diseaseId);
		    rs = ps.executeQuery();
		    
		    Map<Integer, String> valuesMap = new HashMap<Integer, String>();
		    while (rs.next()) {
		    	valuesMap.put(rs.getInt(1), rs.getString(2));
			}
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		    
		    return valuesMap;
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
	
	private static Connection getConnection() throws NamingException, SQLException {
		Context initContext = new InitialContext();
	    Context envContext  = (Context)initContext.lookup("java:/comp/env");
	    DataSource ds = (DataSource)envContext.lookup(DATA_SOURCE_NAME);
	    Connection conn = ds.getConnection();
	    return conn;
	}
	
	public static void saveTalkerDisease(TalkerDiseaseBean talkerDisease) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
		    conn = getConnection();
		    
		    ps = conn.prepareStatement("INSERT INTO talker_disease VALUES " +
		    		"(NULL, ?, ?, ?, ?, ?, ?)");
		    ps.setInt(1, talkerDisease.getUid());
		    ps.setInt(2, talkerDisease.getStageId());
		    ps.setInt(3, talkerDisease.getTypeId());
		    ps.setBoolean(4, talkerDisease.isRecurrent());
		    //TODO: easier?
		    ps.setDate(5, new java.sql.Date(talkerDisease.getSymptomDate().getTime()));
		    ps.setDate(6, new java.sql.Date(talkerDisease.getDiagnoseDate().getTime()));
		    
		    ps.executeUpdate();
		    
		    ResultSet rs =  ps.getGeneratedKeys();
		    if (rs.next()) {
		    	int generatedId = rs.getInt(1);
			    saveHealthItems(generatedId, talkerDisease.getHealthItems());
		    }
		    
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
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
	
	public static void updateTalkerDisease(TalkerDiseaseBean talkerDisease) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
		    conn = getConnection();
		    
		    ps = conn.prepareStatement("UPDATE talker_disease SET " +
		    		"stage_id = ?, type_id = ?, recurrent = ?, symptom_date = ?, diagnose_date = ? " +
		    		"WHERE id = ?");
		    ps.setInt(1, talkerDisease.getStageId());
		    ps.setInt(2, talkerDisease.getTypeId());
		    ps.setBoolean(3, talkerDisease.isRecurrent());
		    //TODO: easier?
		    if (talkerDisease.getSymptomDate() != null) {
		    	ps.setDate(4, new java.sql.Date(talkerDisease.getSymptomDate().getTime()));
		    }
		    else {
		    	ps.setDate(4, null);
		    }
		    if (talkerDisease.getDiagnoseDate() != null) {
		    	ps.setDate(5, new java.sql.Date(talkerDisease.getDiagnoseDate().getTime()));
		    }
		    else {
		    	ps.setDate(5, null);
		    }
		    ps.setInt(6, talkerDisease.getId());
		    
		    ps.executeUpdate();
		    
		    saveHealthItems(talkerDisease.getId(), talkerDisease.getHealthItems());
		    
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
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
	
	private static void saveHealthItems(int talkerDiseaseId, Set<Integer> healthItems) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
		    conn = getConnection();
		    
		    //delete all items
		    ps = conn.prepareStatement("DELETE FROM talker_disease_items WHERE talker_disease_id = ?");
		    ps.setInt(1, talkerDiseaseId);
		    ps.executeUpdate();
		    ps.close();
		    
		    //add new
		    ps = conn.prepareStatement("INSERT INTO talker_disease_items VALUES (NULL, ?, ?)");
		    ps.setInt(1, talkerDiseaseId);
		    for (Integer healthItemId : healthItems) {
		    	ps.setInt(2, healthItemId);
		    	ps.executeUpdate();
		    }
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
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
	public static TalkerDiseaseBean getTalkerDisease(int userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
		    
		    ps = conn.prepareStatement("SELECT * FROM talker_disease WHERE uid = ?");
		    ps.setInt(1, userId);
		    rs = ps.executeQuery();
		    
		    TalkerDiseaseBean talkerDisease = new TalkerDiseaseBean();
		    while (rs.next()) {
		    	talkerDisease.setId(rs.getInt(1));
		    	talkerDisease.setStageId(rs.getInt(3));
		    	talkerDisease.setTypeId(rs.getInt(4));
		    	talkerDisease.setRecurrent(rs.getBoolean(5));
		    	talkerDisease.setSymptomDate(rs.getDate(6));
		    	talkerDisease.setDiagnoseDate(rs.getDate(7));
			}
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    
		    //get health items
		    ps = conn.prepareStatement("SELECT * FROM talker_disease_items WHERE talker_disease_id = ?");
		    ps.setInt(1, talkerDisease.getId());
		    rs = ps.executeQuery();
		    Set<Integer> healthItems = new HashSet<Integer>();
		    while (rs.next()) {
		    	healthItems.add(rs.getInt(3));
		    }
		    talkerDisease.setHealthItems(healthItems);
		    
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		    
		    return talkerDisease;
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
	
	public static HealthItemBean getHealthItemByName(String name, int diseaseId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
		    
		    ps = conn.prepareStatement(
		    		"SELECT t2.id, t2.name FROM health_items t1 " +
		    		"LEFT JOIN health_items AS t2 ON t2.parent_id = t1.id " +
		    		"WHERE t1.name = ? AND t2.disease_id = ?");
		    ps.setString(1, name);
		    ps.setInt(2, diseaseId);
		    rs = ps.executeQuery();
		    
		    HealthItemBean parentItem = new HealthItemBean(name);
		    Set<HealthItemBean> childrenSet = new LinkedHashSet<HealthItemBean>();
		    while (rs.next()) {
		    	childrenSet.add(new HealthItemBean(rs.getInt("id"), rs.getString("name")));
			}
		    parentItem.setChildren(childrenSet);		    
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    
		    ps = conn.prepareStatement(
		    		"SELECT t2.id, t2.name FROM health_items t1 " +
		    		"LEFT JOIN health_items AS t2 ON t2.parent_id = t1.id " +
		    		"WHERE t2.parent_id = ?");
		    for (HealthItemBean healthItem : parentItem.getChildren()) {
			    ps.setInt(1, healthItem.getId());
			    rs = ps.executeQuery();
			    
			    childrenSet = new TreeSet<HealthItemBean>();
			    while (rs.next()) {
			    	childrenSet.add(new HealthItemBean(rs.getInt("id"), rs.getString("name")));
				}
			    healthItem.setChildren(childrenSet);
			    
			    rs.close();
			    rs = null;
		    }
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		    
		    return parentItem;
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
}
