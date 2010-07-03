package com.tah.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.tah.beans.LiveConversationBean;
import com.tah.beans.TalkerBean;
import com.tah.beans.TopicBean;
import com.tah.dao.TopicDAO;
import com.tah.web.webapp.LiveConversationsSingleton;

public class NewTopicServlet extends HttpServlet implements Servlet {
	static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// check to make sure user is logged in
		if (session.getAttribute("username") == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		TalkerBean talker = (TalkerBean) session.getAttribute("talker");
		
		// get new topic text
		String newTopic = request.getParameter("newtopic");
		System.out.println("****NewTopicServlet - Topic: " + newTopic
				+ " Talker: " + talker.getUserName());

		// initiate new conversation room

		// call notification module
		// start new thread

		// open talker window
		// start new thread

		// create topic bean
		TopicBean topic = new TopicBean();
		topic.setTopic(newTopic);
		topic.setUid(talker.getId());
		Date currentDate = Calendar.getInstance().getTime();
		topic.setCreationDate(currentDate);
		topic.setDisplayTime(currentDate);

		// insert new topic into database
		String topicId = TopicDAO.save(topic);
		if (topicId != null) {
			topic.setId(topicId);
		} else {
			Exception e = new Exception(
					"DB Problem - Topic not inserted into DB");
			e.printStackTrace();
			writeResponse(response, "|");
			return;
		}

		// create new LiveConvBean
		LiveConversationBean lcb = new LiveConversationBean();
		lcb.setTopic(topic);
		lcb.addTalker(talker.getId(), talker);

		// add LiveConvBean to LiveConversationSingleton
		LiveConversationsSingleton lcs = LiveConversationsSingleton
				.getReference();
		lcs.addConversation(topicId, lcb);

		// add topic to TopicMap in session - keeps track of topics on the page so no duplicates
		Map<String, TopicBean> mTopics = (Map<String, TopicBean>) session.getAttribute("mapTalkmiTopics");
		mTopics.put(topicId, topic);

		newTopic = newTopic.replaceAll("'", "&#39;");
		newTopic = newTopic.replaceAll("\\|", "&#124;");
		
		writeResponse(response, topicId + "|" + newTopic);
	}

	public void writeResponse(HttpServletResponse response, String data) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");

		//newTopicVar = newTopicVar.replaceAll("'", "\\\\'");
		//get the PrintWriter object to write the html page 
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.println(data);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
}
