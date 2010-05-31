package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import webapp.InsertLoginRecordThread;

import beans.TalkerBean;

/**
 * Authenticates user based on the cookie.
 * @author kindcoder
 */
public class RememberMeFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		
		//only for non-authenticated user
		if (httpRequest.getSession().getAttribute("username") == null) {
			Cookie[] cookies = httpRequest.getCookies();
			
			//sign in if we find "rememberme" cookie
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equalsIgnoreCase("rememberme")) {
						signIn(cookie.getValue(), httpRequest);
						break;
					}
				}
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
	private void signIn(String cookieValue, HttpServletRequest request) {
		//cookie format is "user|password"
		String[] valueArr = cookieValue.split("\\|");
		TalkerBean cb = new TalkerBean();
		cb.setUserName(valueArr[0]);
		cb.setPassword(valueArr[1]);
		
		//sign in
		if (TalkmiDBUtil.validateLogin(cb)) {
			// insert login record into db
			Thread tInsertLoginRecord = new Thread(new InsertLoginRecordThread(cb.getUID()), "InsertLoginRecordThread");
			tInsertLoginRecord.start(); 
			
			request.getSession().setAttribute("talker", cb);
			request.getSession().setAttribute("username", cb.getUserName());
		}
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	@Override
	public void destroy() {}
}
