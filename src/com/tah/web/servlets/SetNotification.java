package com.tah.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.tah.beans.TalkerBean;
import com.tah.dao.TalkerDAO;

/**
 * Servlet implementation class SetNotification
 */
public class SetNotification extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public SetNotification() {
		super();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processSetNotification(request);

		response.sendRedirect("Settings.jsp?setnotification=ok");
	}

	public void processSetNotification(HttpServletRequest request) {
		if ((request.getParameter("notifyFre") == null)
				|| (request.getParameter("notifyTime") == null)
				|| request.getParameter("cType") == null)
			return;

		int notifyfreq = Integer.parseInt(request.getParameter("notifyFre"));
		int notifytime = Integer.parseInt(request.getParameter("notifyTime"));
		String[] cTypes = request.getParameterValues("cType");
		
		TalkerBean talkerBean = (TalkerBean) request.getSession().getAttribute("talker");
		talkerBean.setNfreq(notifyfreq);
		talkerBean.setNtime(notifytime);
		talkerBean.setCtype(cTypes);
		
		TalkerDAO.updateTalker(talkerBean);
	}
}
