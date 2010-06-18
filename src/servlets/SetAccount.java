package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.sendRedirect("TalkerHome.jsp");
	}

	public void processSetAccount(HttpServletRequest request) {
		String primaryIM = request.getParameter("IMService");
		String email = request.getParameter("email");

		TalkerBean talkerBean = (TalkerBean)request.getSession().getAttribute("talker");
		talkerBean.setEmail(email);
		talkerBean.setIM(primaryIM);
		
		TalkmiDBUtil.updateTalker(talkerBean);
	}
}
