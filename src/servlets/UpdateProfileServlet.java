package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import util.TalkmiDBUtil;

import beans.TalkerBean;

/**
 * Servlet implementation class for Servlet: Login
 *
 */
public class UpdateProfileServlet extends HttpServlet {
	
	static final long serialVersionUID = 1L;
	DataSource ds;

	public void init() throws ServletException {
		try {
			//Create a datasource for pooled connections.
			ds = (DataSource) getServletContext().getAttribute("DBCPool");

			// Register the driver for non-pooled connections.
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("changepassword".equalsIgnoreCase(action)) {
			//change pass
			TalkerBean talker = (TalkerBean)request.getSession().getAttribute("talker");
			String curPassword = request.getParameter("curpassword");
			String newPassword = request.getParameter("newpassword");
			String confirmPassword = request.getParameter("confirmpassword");
			if (talker.getPassword().equals(curPassword)) {
				if (newPassword != null && newPassword.equals(confirmPassword)) {
					talker.setPassword(newPassword);
					TalkmiDBUtil.updateTalker(talker);
				}
				else {
					//TODO: report different pass error
				}
			}
			else {
				//TODO: report bad pass error
			}
			
		}
		else {
			processSQLUpdate(request);
		}
		response.sendRedirect("EditProfile.jsp");
	}

	public void processSQLUpdate(HttpServletRequest request) {
		//  get parameters sent through post
		String uname = request.getParameter("username");
		String email = request.getParameter("email");

		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String category = request.getParameter("selection");
		String maritalstatus = request.getParameter("maritalstatus");
		String childrenNum = request.getParameter("children");
		//System.out.println(maritalstatus);

		// getting session and TalkerBean
		HttpSession session = request.getSession();
		TalkerBean cb = (TalkerBean) session.getAttribute("talker");
		String oldUserName = cb.getUserName();
//		String month = request.getParameter("month");
//		String day = request.getParameter("day");
//		String year = request.getParameter("year");
		String gender = request.getParameter("gender");

		Calendar cal = Calendar.getInstance();
		//System.out.println("***ParseInt: " + year + month + day);
//		cal.set(Integer.parseInt(year), Integer.parseInt(month), Integer
//				.parseInt(day));
		SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String dob = SQL_DATE_FORMAT.format(cal.getTime());
		//char g = gender.charAt(0);

		// check to make sure no duplicate UserName

		// if not duplicate, update info and change TalkerBean info in session
		String updateQuery = "UPDATE talkers SET uname= ?, email= ?, dob= ?, gender = ?, Marital_Stat = ?, city = ?, state = ?, country = ?, category = ?, childrenNum = ? WHERE uname= ?";
		//String updateQuery = "UPDATE talkers SET uname= ?, password= ?, email= ?, dob= ?, gender = ? city = ? state = ? country = ? category = ? childrenNum = ? WHERE uname= ?";
		//String updateQuery = "UPDATE talkers SET uname= ?, password= ?, email= ?, dob= ?, gender = ? WHERE uname= ?";
		Connection conn = null;
		PreparedStatement ps = null; // Or PreparedStatement if needed
		try {
			//			Context initContext = new InitialContext();
			//		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
			//		    DataSource ds = (DataSource)envContext.lookup("jdbc/Talkmidb");
			conn = ds.getConnection();

			ps = conn.prepareStatement(updateQuery);
			ps.setString(1, uname);
			ps.setString(2, email);
			ps.setString(3, dob);
			ps.setString(4, gender);

			ps.setString(5, maritalstatus);
			ps.setString(6, city);
			ps.setString(7, state);
			ps.setString(8, country);
			ps.setString(9, category);
			ps.setInt(10, Integer.parseInt(childrenNum));

			ps.setString(11, oldUserName);

			ps.executeUpdate();

			ps.close();
			ps = null;
			conn.close(); // Return to connection pool
			conn = null; // Make sure we don't close it twice

			cb.setUserName(uname);
			cb.setEmail(email);
			cb.setGender(gender.charAt(0));
			cb.setCategory(category);
			cb.setMariStat(maritalstatus);
			cb.setCity(city);
			cb.setState(state);
			cb.setCountry(country);

			try {
				cb.setDob(SQL_DATE_FORMAT.parse(dob));
			} catch (ParseException e) {
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
		} finally {
			// Always make sure result sets and statements are closed,
			// and the connection is returned to the pool
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					;
				}
				ps = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					;
				}
				conn = null;
			}
		}
	}
}