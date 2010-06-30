package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.oauth.FacebookOAuthProvider;
import util.oauth.OAuthServiceProvider;
import util.oauth.TwitterOAuthProvider;


public class OAuthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	} 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String action = request.getParameter("action");
		
		if (type != null && action != null) {
			OAuthServiceProvider oauthProvider = getProvider(type);
			if (action.equals("getauth")) {
				response.sendRedirect(oauthProvider.getAuthURL(request));
			}
			else if (action.equals("callback")) {
				//TODO: decouple providers from request/response?
				try {
					oauthProvider.handleCallback(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}
		}
	}

	private OAuthServiceProvider getProvider(String serviceType) {
		if (serviceType.equalsIgnoreCase("twitter")) {
			return new TwitterOAuthProvider();
		}
		else if (serviceType.equalsIgnoreCase("facebook")) {
			return new FacebookOAuthProvider();
		}
		else {
			throw new IllegalArgumentException("Bad service type");
		}
	} 
	
}

