package com.tah.web.servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tah.beans.TalkerBean;
import com.tah.beans.TalkerDiseaseBean;
import com.tah.dao.TalkerDiseaseDAO;
import com.tah.util.CommonUtil;

public class HealthDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
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
		
		//parse params to bean
		TalkerDiseaseBean talkerDisease = new TalkerDiseaseBean();
		talkerDisease.setStage(diseaseStage);
		talkerDisease.setType(diseaseType);
		if (recurrent != null && recurrent.equalsIgnoreCase("y")) {
			talkerDisease.setRecurrent(true);
		}
		talkerDisease.setSymptomDate(CommonUtil.parseDate(symptomDateStr));
		talkerDisease.setDiagnoseDate(CommonUtil.parseDate(diagnoseDateStr));
		parseHealthItems(request, talkerDisease);
		
		talkerDisease.setUid(talker.getId());
		
		//Save or update
		TalkerDiseaseDAO.saveTalkerDisease(talkerDisease);
		
		response.sendRedirect("HealthDetails.jsp?result=ok");
	} 
	
	private void parseHealthItems(HttpServletRequest request,
			TalkerDiseaseBean talkerDisease) {
		@SuppressWarnings("unchecked")
		Enumeration<String> parameters = request.getParameterNames();
		Set<String> healthItems = new HashSet<String>();
		while (parameters.hasMoreElements()) {
			//Health item parameter name: 'healthitemID'
			String paramName = parameters.nextElement();
			if (paramName.startsWith("healthitem")) {
				String id = paramName.substring(10);
				healthItems.add(id);
			}
		}
		talkerDisease.setHealthItems(healthItems);
	}
}