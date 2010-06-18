package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.TalkmiDBUtil;
import beans.TalkerBean;

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

		response.sendRedirect("Settings.jsp");
	}

	public void processSetNotification(HttpServletRequest request) {
		if ((request.getParameter("notifyFre") == null)
				|| (request.getParameter("notifyTime") == null)
				|| request.getParameter("iType") == null)
			return;

		int notifyfreq = Integer.parseInt(request.getParameter("notifyFre"));
		int notifytime = Integer.parseInt(request.getParameter("notifyTime"));
		int interestT = Integer.parseInt(request.getParameter("iType"));

		System.out.println("Setting Notification!");

		TalkerBean talkerBean = (TalkerBean) request.getSession().getAttribute("talker");
		talkerBean.setNfreq(notifyfreq);
		talkerBean.setNtime(notifytime);
		talkerBean.setItype(interestT);
		
		TalkmiDBUtil.updateTalker(talkerBean);
	}
}
