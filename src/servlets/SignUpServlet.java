package servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import beans.TalkerBean;

import util.EmailUtil;
import util.ValidateData;


/**
 * Servlet implementation class for Servlet: Login
 *
 */
 public class SignUpServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   DataSource ds;
   private String un;
   private String pw;
   private String email;
   private String IM;
   private String month;
   private String day;
   private String year;
   private String gender;
   private String dob;
   private boolean newsletter;
   private SimpleDateFormat SQL_DATE_FORMAT;
   //private String zip;
   //private String city;
   //private String state;
      
   private String[] errors = {"n","n"};
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public SignUpServlet() {
		super();
	}  
	public void init() throws ServletException {
	    try {
	    	//Create a datasource for pooled connections.
	    	ds = (DataSource) getServletContext().
	    	getAttribute("DBCPool");

	    	// Register the driver for non-pooled connections.
	    	Class.forName("com.mysql.jdbc.Driver").
	    	newInstance();
	    } catch (Exception e) {
	    	throw new ServletException(e.getMessage());
	    }
	}
	public boolean checkUserName() {
		//String unCheckQuery = "Select uname from talkers where uname = '" + un + "'";
		String unCheckQuery = "Select uname from talkers where uname = ?";
		//System.out.println("***Process New Talker: Query: " + unCheckQuery);
    	
		Connection conn = null;
		PreparedStatement ps = null;  // Or PreparedStatement if needed
		ResultSet rs = null;
		boolean unMatch = false;
		try {
			conn = ds.getConnection();
		    ps = conn.prepareStatement(unCheckQuery);
		    ps.setString(1, un);
		    
		    rs = ps.executeQuery();
		    
		    // if row in result set, then user is validated
		    if (rs.next()) { 
		    	//System.out.println("***UN Result set has a row!!!");
		    	unMatch = true;
		    }
		    ps.close();
		    ps = null;
		    rs = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
	    	if (unMatch == true){
		    	//System.out.println("*** Username Match - user needs to try new user name!!");
		    	errors[0] = "u";
		    	return true;
		    } else {
		    	errors[0] = un;
		    	return false;
		    }
		} catch (SQLException ex) {
			    // handle any errors
			    ex.printStackTrace();
			    errors[1] = "e";
				return true;
		} catch (Exception ex) {
				ex.printStackTrace();
				errors[1] = "e";
				return true;
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
	
	public boolean insertTalkerInfo() {
		//System.out.println("*** Process New Talker - Insert Talker Info!!");
	    Calendar cal = Calendar.getInstance();
	    cal.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dob = SQL_DATE_FORMAT.format(cal.getTime());
		
		Date now = Calendar.getInstance().getTime(); 
		String sqlDate = SQL_DATE_FORMAT.format(now);
		
//		byte[] unencryptedPassword = pw.getBytes();
//		MessageDigest md = null;
//		try
//		{
//			md = MessageDigest.getInstance("MD5");
//		} catch(Exception e){}
//		
//		md.reset();
//		md.update(unencryptedPassword);
//		
//		byte[] encryptedPassword = md.digest();
//		StringBuffer pwBuf = new StringBuffer();
//		for(int i=0;i<encryptedPassword.length;i++)
//		{
//			if (((int) encryptedPassword[i] & 0xff) < 0x10) 
//			{
//				pwBuf.append("0");
//			}
//			pwBuf.append(Long.toString((int) encryptedPassword[i] & 0xff, 16));
//		}
//		pw = pwBuf.toString();
		
		
		//char g = gender.charAt(0);
		// insert info into database
		//String insertQuery = "Insert into talkers (uname, password, email, dob, gender, zip, city, state, passcode) values ('" + un + "', '" + pw + "', '" + email + "', '" + dob + "', '" + gender + "', '" + zip + "', '" + city + "', '"  + state + "', '" + sPassCode +  "')";
		String insertQuery = "Insert into talkers (uname, password,email, dob, gender, time_stamp, PrimaryIM, newsletter) " +
				"values (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;  // Or PreparedStatement if needed
		try {
			conn = ds.getConnection();
		    
		    ps = conn.prepareStatement(insertQuery);
		    ps.setString(1, un);
		    ps.setString(2, pw);
		    ps.setString(3, email);
		    ps.setString(4, dob);
		    ps.setString(5, gender);
		    ps.setString(6, sqlDate);
		    ps.setString(7, IM);
		    ps.setBoolean(8, newsletter);
		    
		    ps.executeUpdate();
		    
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		    return true;
		} catch (SQLException ex) {
			    // handle any errors
			    ex.printStackTrace();
				return false;
		} catch (Exception ex) {
				ex.printStackTrace();
				return false;
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
	public TalkerBean initializeTalkerBean(TalkerBean cb){
		// create sql statement
		//String sqlValidate = "select uid from talkers where uname='" + cb.getUserName() + "'";
		String sqlValidate = "select uid from talkers where uname= ?";
		
		Connection conn = null;
		ResultSet rs = null; 
		PreparedStatement ps = null;
	    try {
	    	conn = ds.getConnection();
		    
		    ps = conn.prepareStatement(sqlValidate);
		    ps.setString(1, cb.getUserName());
		    rs = ps.executeQuery();
		    
		    boolean success = false;
		    // if row in result set, then user is validated
		    if (rs.next()) { 
		    	cb.setUID(rs.getInt("uid"));
		    	success = true;
			}
		        
		    rs.close();
		    rs = null;
		    ps.close();
		    ps = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice]
		    if (success == true) {
		    	return cb;
		    } else {
		    	return null;
		    }
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
		//System.out.println("--------*** Process New Talker - Starting!!");
	    un = request.getParameter("username");
		pw = request.getParameter("password");
		email = request.getParameter("email");
		IM = request.getParameter("IMService");
		month = request.getParameter("month");
		day = request.getParameter("day");
		year = request.getParameter("year");
		//for now gender has default value
		gender = "M";
//		gender = request.getParameter("gender");
		//zip = request.getParameter("zip");
		//city = request.getParameter("city");
		//state = request.getParameter("state");
		//System.out.println(un+pw+email+month+day+year+gender);	
		
		newsletter = false;
		if (request.getParameter("newsletter") != null) {
			newsletter = true;
		}
		
		// data validation
		if (!ValidateData.validateEmail(email)) {
			response.sendRedirect("SignUp.jsp?username=" + un + "&email=notvalid"+ 
					"&month=" + month + "&day=" + day + "&year=" + year);
			return;
		}
		
		// Other fields are validated using JavaScript on client,
		// so if server side validation fails - error is unknown and we can redirect to common error page
		if(!ValidateData.validateUserName(un) || !ValidateData.validatePassword(pw) 
				|| !ValidateData.validateMonth(month) || !ValidateData.validateDay(day)|| !ValidateData.validateYear(year)) {
			Exception e = new Exception("Login Failed - Data Validation Error!!");
			e.printStackTrace();
			response.sendRedirect("Error.jsp");
		    return;
		}
		
		boolean match = checkUserName();
		if (match == true){
			//System.out.println("*** Process New Talker - Match = True");
			
		    if (errors[1].equals("e")) {
		    	Exception e = new Exception("Process New Talker - Error:  SQL or other exception");
				e.printStackTrace();
				response.sendRedirect("Error.jsp");
				return;
			} else {
				//System.out.println("*** Process New Talker - matching username or tnumber: " + errors[0] + errors[1] + email);
			    // redirecting to SignUp page, will exhibit msg about errors
				response.sendRedirect("SignUp.jsp?username=" + errors[0] + "&email=" + email + "&month=" + month + "&day=" + day + "&year=" + year + "&gender=" + gender);
			}
		} else {
			//System.out.println("*** Process New Talker - Inserting talker info into DB");
			
			if (!insertTalkerInfo()) {
				Exception e = new Exception("Process New Talker - Error:  SQL or other exception");
				e.printStackTrace();
				response.sendRedirect("Error.jsp");
				return;
			}
			
			//Successful signup!
			EmailUtil.sendEmail(EmailUtil.WELCOME_TEMPLATE, email);
				
			// create session
		    TalkerBean cb= new TalkerBean();
		    cb.setUserName(un);
		    cb.setPassword(pw);
		    cb.setIM(IM);
		    cb.setEmail(email);
		    cb.setGender(gender.charAt(0));
		    
		    try {
				cb.setDob(SQL_DATE_FORMAT.parse(dob));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			/*
			 * TODO: same functionality in login/signup/rememberme - move to separate class (logic)
			 */
			
		    // get uid
			initializeTalkerBean(cb);
			
			//System.out.println("*** Process New Talker - Setting Session Variables");
		    //set session variables
		    request.getSession().setAttribute("talker", cb);
		    request.getSession().setAttribute("username", cb.getUserName());
		    
		    String params = "";
		    //If user tried to create topic and signed up - save topic name in the field
		    String newTopic = request.getParameter("newtopic");
		    if (newTopic != null && newTopic.trim().length() != 0) {
		    	params = "?newtopic="+URLEncoder.encode(newTopic, "UTF-8");
		    }
				
		    response.sendRedirect("TalkerHome.jsp"+params);
		}
		//System.out.println("--------*** Process New Talker - End!!");
	}   	  	    
}