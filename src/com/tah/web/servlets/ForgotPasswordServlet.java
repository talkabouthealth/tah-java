package com.tah.web.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.tah.beans.TalkerBean;
import com.tah.dao.TalkerDAO;
import com.tah.util.CommonUtil;
import com.tah.util.EmailUtil;
import com.tah.util.ValidateData;

public class ForgotPasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	} 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		if(!ValidateData.validateEmail(email)) {
			response.sendRedirect("ForgotPassword.jsp?result=bademail");
			return;
		}
		
		TalkerBean user = TalkerDAO.getByEmail(email);
		if (user != null) {
			//generate new password
			SecureRandom random = new SecureRandom();
		    String newPassword = new BigInteger(60, random).toString(32);
		    
		    //change password for this user
		    user.setPassword(CommonUtil.hashPassword(newPassword));
		    TalkerDAO.updateTalker(user);
			
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("username", user.getUserName());
			vars.put("newpassword", newPassword);
			EmailUtil.sendEmail(EmailUtil.FORGOT_PASSWORD_TEMPLATE, user.getEmail(), vars);
			
			response.sendRedirect("ForgotPassword.jsp?result=ok");
		} else {
			response.sendRedirect("ForgotPassword.jsp?result=nouser");
		}
		
	} 

}
