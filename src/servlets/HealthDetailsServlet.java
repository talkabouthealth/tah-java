package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.TalkmiDBUtil;
import beans.TalkerBean;
import beans.TalkerDiseaseBean;

public class HealthDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	} 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TalkerBean talker = (TalkerBean)request.getSession().getAttribute("talker");
		
		String diseaseStage = request.getParameter("diseasestage");
		String diseaseType = request.getParameter("diseasetype");
		String recurrent = request.getParameter("recurrent");
		String symptomDateStr = request.getParameter("symptomdate");
		String diagnoseDateStr = request.getParameter("diagnosedate");
		int talkerDiseaseId = Integer.parseInt(request.getParameter("talkerdiseaseid"));
		
		//parse params to bean
		TalkerDiseaseBean talkerDisease = new TalkerDiseaseBean();
		talkerDisease.setStageId(Integer.parseInt(diseaseStage));
		talkerDisease.setTypeId(Integer.parseInt(diseaseType));
		if (recurrent != null && recurrent.equalsIgnoreCase("y")) {
			talkerDisease.setRecurrent(true);
		}
		talkerDisease.setSymptomDate(parseDate(symptomDateStr));
		talkerDisease.setDiagnoseDate(parseDate(diagnoseDateStr));
		parseHealthItems(request, talkerDisease);
		
		talkerDisease.setUid(talker.getUID());
		
		//Save or update
		//TODO: unify save and update? todo with Mongo
		if (talkerDiseaseId != 0) {
			talkerDisease.setId(talkerDiseaseId);
			TalkmiDBUtil.updateTalkerDisease(talkerDisease);
		}
		else {
			TalkmiDBUtil.saveTalkerDisease(talkerDisease);
		}
		
		response.sendRedirect("HealthDetails.jsp");
	} 
	
	private void parseHealthItems(HttpServletRequest request,
			TalkerDiseaseBean talkerDisease) {
		@SuppressWarnings("unchecked")
		Enumeration<String> parameters = request.getParameterNames();
		Set<Integer> healthItems = new HashSet<Integer>();
		while (parameters.hasMoreElements()) {
			//Health item parameter name: 'healthitemID'
			String paramName = parameters.nextElement();
			if (paramName.startsWith("healthitem")) {
				int id = Integer.parseInt(paramName.substring(10));
				healthItems.add(id);
			}
		}
		talkerDisease.setHealthItems(healthItems);
	}

	private Date parseDate(String dateString) {
		try {
			Date date = dateFormat.parse(dateString);
			return date;
		} catch (ParseException e) {}
		return null;
	}
}