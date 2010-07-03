package com.tah.util.oauth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OAuthServiceProvider {
	
	public String getAuthURL(HttpServletRequest request);
	
	public void handleCallback(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
