package com.tah.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tah.beans.TalkerBean;
import com.tah.dao.TalkerDAO;
import com.tah.util.CommonUtil;
import com.tah.util.ValidateData;
import com.tah.web.webapp.InsertLoginRecordThread;

/**
 * Servlet implementation class for Servlet: Login
 *
 */
public class LoginServlet extends HttpServlet {
	
	static final long serialVersionUID = 1L;
	//in seconds
	private static final int REMEMBERME_TIMEOUT = 60 * 60 * 24 * 365;

	TalkerBean talker;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		String rememberMe = request.getParameter("rememberme");

		if (!ValidateData.validateUserName(un)
				|| !ValidateData.validatePassword(pw)) {
			System.err.println("Bad validation - login Failed!");
			response.sendRedirect("SignIn.jsp?login=f");
			return;
		}

		talker = new TalkerBean();
		talker.setUserName(un);
		talker.setPassword(CommonUtil.hashPassword(pw));

		if (TalkerDAO.validateLogin(talker)) {
			// insert login record into db
			Thread tInsertLoginRecord = new Thread(new InsertLoginRecordThread(
					talker.getId()), "InsertLoginRecordThread");
			tInsertLoginRecord.start();

			// add TalkerBean to session
			request.getSession().setAttribute("talker", talker);
			request.getSession().setAttribute("username", talker.getUserName());

			// if rememberme checkbox was selected - save cookie
			if (rememberMe != null) {
				String rememberMeInfo = talker.getUserName() + "|"
						+ talker.getPassword();
				Cookie cookie = new Cookie("rememberme", rememberMeInfo);
				cookie.setMaxAge(REMEMBERME_TIMEOUT);
				response.addCookie(cookie);
			}
			
			response.sendRedirect("TalkerHome.jsp");
		} else {
			System.out.println("Login Failed!");
			response.sendRedirect("SignIn.jsp?login=f");
		}
	}

}