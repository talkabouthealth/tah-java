package com.tah.web.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.tah.beans.TalkerBean;
import com.tah.dao.TalkerDAO;
import com.tah.util.CommonUtil;
import com.tah.util.DBUtil;
import com.tah.util.EmailUtil;
import com.tah.util.ValidateData;

/**
 * Servlet implementation class for Servlet: Login
 * TODO: clean up code and signup validation
 */
public class SignUpServlet extends HttpServlet implements Servlet {
	static final long serialVersionUID = 1L;
	
	private String un;
	private String pw;
	private String email;
	private String imService;
	private String imUsername;
	private String month;
	private String day;
	private String year;
	private String gender;
	private Date dob;
	private boolean newsletter;
	private String accountType;
	private String accountId;

	private String[] errors = { "n", "n" };

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		un = request.getParameter("username");
		pw = request.getParameter("password");
		email = request.getParameter("email");
		imService = request.getParameter("IMService");
		month = request.getParameter("month");
		day = request.getParameter("day");
		year = request.getParameter("year");
		//for now gender has default value
		gender = "M";

		newsletter = false;
		if (request.getParameter("newsletter") != null) {
			newsletter = true;
		}

		//check if user signed up through Twitter or Facebook
		//TODO: clear params from session?
		accountType = (String) request.getSession().getAttribute("accounttype");
		accountId = (String) request.getSession().getAttribute("accountid");

		// data validation
		if (!ValidateData.validateEmail(email)) {
			response.sendRedirect("SignUp.jsp?username=" + un
					+ "&email=notvalid" + "&month=" + month + "&day=" + day
					+ "&year=" + year);
			return;
		}
		// Other fields are validated using JavaScript on client,
		// so if server side validation fails - error is unknown and we can redirect to common error page
		if (!ValidateData.validateUserName(un)
				|| !ValidateData.validatePassword(pw)
				|| !ValidateData.validateMonth(month)
				|| !ValidateData.validateDay(day)
				|| !ValidateData.validateYear(year)) {
			Exception e = new Exception("Login Failed - Data Validation Error!!");
			e.printStackTrace();
			response.sendRedirect("Error.jsp");
			return;
		}

		imUsername = request.getParameter("imusername");
		if (imUsername.trim().length() == 0 || imUsername.equals("IM username")) {
			//if user didn't enter it - we parse from email
			int atIndex = email.indexOf('@');
			imUsername = email.substring(0, atIndex);
		}

		//hash pass to store in db
		pw = CommonUtil.hashPassword(pw);

		boolean match = checkUsernameAndEmail(un, email);
		if (match) {
			if (errors[1].equals("e")) {
				Exception e = new Exception(
						"Process New Talker - Error:  DB exception");
				e.printStackTrace();
				response.sendRedirect("Error.jsp");
				return;
			} else {
				// redirecting to SignUp page, will exhibit msg about errors
				response.sendRedirect("SignUp.jsp?username=" + errors[0]
						+ "&email=" + errors[1] + "&month=" + month + "&day="
						+ day + "&year=" + year + "&gender=" + gender);
			}
		} else {
			if (!insertTalkerInfo()) {
				Exception e = new Exception(
						"Process New Talker - Error:  DB exception");
				e.printStackTrace();
				response.sendRedirect("Error.jsp");
				return;
			}

			//Successful signup!
			CommonUtil.sendIMInvitation(imUsername, imService);
			EmailUtil.sendEmail(EmailUtil.WELCOME_TEMPLATE, email);

			/*
			 * TODO: same functionality in login/signup/rememberme - move to separate class (logic)
			 */
			TalkerBean talker = TalkerDAO.getByUserName(un);
			request.getSession().setAttribute("talker", talker);
			request.getSession().setAttribute("username", talker.getUserName());

			String params = "";
			//If user tried to create topic and signed up - show topic name in the field
			String newTopic = request.getParameter("newtopic");
			if (newTopic != null && newTopic.trim().length() != 0) {
				params = "?newtopic=" + URLEncoder.encode(newTopic, "UTF-8");
			}

			response.sendRedirect("TalkerHome.jsp" + params);
		}
	}
	
	public boolean checkUsernameAndEmail(String userName, String email) {
		boolean unameMatch = false;
		boolean emailMatch = false;

		errors[0] = un;
		TalkerBean talker = TalkerDAO.getByUserName(un);
		if (talker != null) {
			unameMatch = true;
			errors[0] = "u";
		}

		errors[1] = email;
		talker = TalkerDAO.getByEmail(email);
		if (talker != null) {
			emailMatch = true;
			errors[1] = "em";
		}

		return unameMatch || emailMatch;
	}

	public boolean insertTalkerInfo() {
		DBCollection talkersColl = DBUtil.getCollection("talkers");

		//TODO: move it to parameters parsing
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), Integer.parseInt(month), Integer
				.parseInt(day));
		dob = cal.getTime();
		Date now = Calendar.getInstance().getTime();

		//TODO: move this to DAO
		DBObject talker = BasicDBObjectBuilder.start().add("uname", un).add(
				"pass", pw).add("email", email).add("dob", dob).add("gender",
				gender).add("timestamp", now).add("im", imService).add(
				"im_uname", imUsername).add("newsletter", newsletter).add(
				"act_type", accountType).add("act_id", accountId).add(
				"invites", 100).get();

		talkersColl.save(talker);
		return true;
	}
}