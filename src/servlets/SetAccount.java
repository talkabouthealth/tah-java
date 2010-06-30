package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommonUtil;
import util.TalkmiDBUtil;
import beans.TalkerBean;

/**
 * Servlet implementation class SetAccount
 */
public class SetAccount extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processSetAccount(request);
		response.sendRedirect("Settings.jsp?setaccount=ok#accountform");
	}

	public void processSetAccount(HttpServletRequest request) {
		String imService = request.getParameter("IMService");
		String imUsername = request.getParameter("imusername");
		String email = request.getParameter("email");
		TalkerBean talkerBean = (TalkerBean)request.getSession().getAttribute("talker");
		
		//if something was changed - send new invitation
		if (!talkerBean.getIM().equals(imService) || !talkerBean.getImUsername().equals(imUsername)) {
			CommonUtil.sendIMInvitation(imUsername, imService);
		}
		
		talkerBean.setEmail(email);
		talkerBean.setImUsername(imUsername);
		talkerBean.setIM(imService);
		
		TalkmiDBUtil.updateTalker(talkerBean);
	}
}
