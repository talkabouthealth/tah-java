package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.TalkerBean;

/**
 * Servlet implementation class for Servlet: Login
 *
 */
public class LogoutServlet extends HttpServlet {
	static final long serialVersionUID = 1L;
	TalkerBean cb;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (null == request.getSession().getAttribute("username")) {
			response.sendRedirect("index.jsp");
			return;
		}

		request.getSession().removeAttribute("talker");
		request.getSession().removeAttribute("username");
		request.getSession().invalidate();

		//Remove "rememberme" cookie
		Cookie cookie = new Cookie("rememberme", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		response.sendRedirect("index.jsp");
	}
}