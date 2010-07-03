package com.tah.web.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.tah.beans.TalkerBean;
import com.tah.dao.TalkerDAO;
import com.tah.util.EmailUtil;
import com.tah.util.ValidateData;

public class InvitationsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	} 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emails = request.getParameter("emails");
		String note = request.getParameter("note");
		TalkerBean talker = (TalkerBean)request.getSession().getAttribute("talker");
		
		//parse and validate emails
		Set<String> emailsToSend = new HashSet<String>();
		String[] emailsArr = emails.split(",");	
		for (String email : emailsArr) {
			email = email.trim();
			if (ValidateData.validateEmail(email)) {
				emailsToSend.add(email);
			}
		}
		
		if (emailsToSend.isEmpty()) {
			//incorrect emails
			response.sendRedirect("Invitations.jsp?result=bademails");
			return;
		}
		else if (emailsToSend.size() > talker.getInvitations()) {
			//not enought invites
			response.sendRedirect("Invitations.jsp?result=noinvites");
			return;
		}
		else {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("username", talker.getUserName());
			vars.put("invitation_note", note);
			for (String email : emailsToSend) {
				EmailUtil.sendEmail(EmailUtil.INVITATION_TEMPLATE, email, vars);
			}
			
			//decrease invitations count
			talker.setInvitations(talker.getInvitations()-emailsToSend.size());
			TalkerDAO.updateTalker(talker);
			
			response.sendRedirect("Invitations.jsp?result=ok");
		}
	} 

}