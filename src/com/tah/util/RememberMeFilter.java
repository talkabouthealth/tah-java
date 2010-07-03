package com.tah.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.tah.beans.TalkerBean;
import com.tah.dao.TalkerDAO;
import com.tah.web.webapp.InsertLoginRecordThread;



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
		TalkerBean talker = new TalkerBean();
		talker.setUserName(valueArr[0]);
		talker.setPassword(valueArr[1]);
		
		//sign in
		if (TalkerDAO.validateLogin(talker)) {
			// insert login record into db
			Thread tInsertLoginRecord = new Thread(
					new InsertLoginRecordThread(talker.getId()), "InsertLoginRecordThread");
			tInsertLoginRecord.start(); 
			
			request.getSession().setAttribute("talker", talker);
			request.getSession().setAttribute("username", talker.getUserName());
		}
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	@Override
	public void destroy() {}
}
