package servlets;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.TalkmiDBUtil;

import beans.TalkerBean;
import beans.TalkerBean.ProfilePreference;

public class ProfilePreferencesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	} 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TalkerBean talker = (TalkerBean)request.getSession().getAttribute("talker");
		
		@SuppressWarnings("unchecked")
		Enumeration<String> parameters = request.getParameterNames();
		EnumSet<ProfilePreference> preferencesSet = EnumSet.noneOf(ProfilePreference.class);
		while (parameters.hasMoreElements()) {
			//try to parse all parameters to ProfilePreference enum
			String paramName = parameters.nextElement();
			try {
				ProfilePreference preference = ProfilePreference.valueOf(paramName);
				preferencesSet.add(preference);
			}
			catch (IllegalArgumentException iae) {}
		}
		
		talker.setProfilePreferences(preferencesSet);
		TalkmiDBUtil.updateTalker(talker);
		
		response.sendRedirect("ProfilePreferences.jsp?result=ok");
	} 
}
