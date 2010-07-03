package com.tah.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tah.beans.TopicBean;


/**
 * Servlet implementation class for Servlet: Login
 *
 */
 public class MoreOldTopicsServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	 static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get user id from session
		HttpSession session = request.getSession();
		// check to make sure user is logged in
		if (session.getAttribute("username") == null) { 
			response.sendRedirect("index.jsp");
			return;
		}
		//System.out.println("--------***MoreOldTopicServlet:  Start");
		
		Map<Integer, TopicBean> mNextTopicList = new LinkedHashMap<Integer, TopicBean>(40);
		// get latest timestamp in session
		String sEarliestTimeStamp = (String)session.getAttribute("earliesttimestamp");
		////System.out.println("*** retrieveNextTopicServlet - Latest TimeStamp: " + sLatestTimeStamp);
			
//		mNextTopicList = queryNextTopics(sEarliestTimeStamp);
		
		// get page TopicMap from session
		Map<String, TopicBean> mTopics = (Map<String, TopicBean>)session.getAttribute("mapTalkmiTopics");
			
		response.setContentType("text/xml;charset=utf-8"); 
		response.setHeader("Cache-Control", "no-cache"); 
	    
		//get the PrintWriter object to write the html page 
		PrintWriter writer = response.getWriter(); 
		writer.println("<topics>");
		
		int count = 0;
		for (TopicBean tbTalkmiTopic : mNextTopicList.values()) {
	   		//System.out.println("TopicID: " + tbTalkmiTopic.getTopicID() + " Topic: " + tbTalkmiTopic.getTopic());
	   		// get next topic from NextTopicList
			if (!mTopics.containsKey(tbTalkmiTopic.getId())){
				count++;
				// add next topic in NextTopicList to TopicMap
				mTopics.put(tbTalkmiTopic.getId(), tbTalkmiTopic);
				// create xml for the topic
				writer.println("<topic><topicid>" + String.valueOf(tbTalkmiTopic.getId()) + "</topicid><topicvalue>" + tbTalkmiTopic.getTopic() + "</topicvalue></topic>");
				//System.out.println("<topic><topicid>" + String.valueOf(tbTalkmiTopic.getTopicID()) + "</topicid><topicvalue>" + tbTalkmiTopic.getTopic() + "</topicvalue></topic>");
				
				if (count == 5) { // setLatestTimeStamp in session
					SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					session.setAttribute("earliesttimestamp", SQL_DATE_FORMAT.format(tbTalkmiTopic.getDisplayTime()));
					break;
				}
			}	
		} 
		
		writer.println("</topics>");
		//close the write
		writer.close();
		//System.out.println("--------***MoreOldTopicServlet:  End");
	}
}