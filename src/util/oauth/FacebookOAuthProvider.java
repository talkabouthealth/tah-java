package util.oauth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommonUtil;
import util.TalkmiDBUtil;
import webapp.InsertLoginRecordThread;
import beans.TalkerBean;

public class FacebookOAuthProvider implements OAuthServiceProvider {
	
	private static final String APP_ID = "131545373528131";
	private static final String APP_SECRET = "0620bead67e2ffa4e9e46f60b3376dec";
	private static final String CALLBACK_URL =
		"http://talkabouthealth.com:8080/tah-java/oauth?action=callback&type=facebook";
	
//Test settings	
//	private static final String APP_ID = "126479497379490";
//	private static final String APP_SECRET = "cd4606efec03ea8c5bd9ffb9d49000ff";
//	private static final String CALLBACK_URL =
//		"http://kan.dev.com:8080/tah-java/oauth?action=callback&type=facebook";
	

	@Override
	public String getAuthURL(HttpServletRequest request) {
		String authURL = null;
		try {
			authURL = "https://graph.facebook.com/oauth/authorize?" +
				"client_id="+APP_ID+"&redirect_uri="+URLEncoder.encode(CALLBACK_URL, "UTF-8")+
				"&scope=email,user_about_me,user_birthday";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return authURL;
	}

	@Override
	public void handleCallback(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
		if (code != null) {
			//user confirmed app - get access token
			String url = "https://graph.facebook.com/oauth/access_token";
			String params = 
			    "client_id="+APP_ID+
			    "&redirect_uri="+URLEncoder.encode(CALLBACK_URL, "UTF-8")+
			    "&client_secret="+APP_SECRET+
			    "&code="+URLEncoder.encode(code, "UTF-8");
			List<String> lines = CommonUtil.makeGET(url, params);
			
			//returned string is:
			//access_token=...token...&expires=5745
			String accessToken = null;
			for (String line : lines) {
				if (line.startsWith("access_token")) {
					int separatorIndex = line.lastIndexOf('&');
					accessToken = line.substring(13, separatorIndex);
				}
			}
			
			//parse Facebook id and email from reply
			String accountId = null;
			String userEmail = null;
			lines = CommonUtil.makeGET("https://graph.facebook.com/me", 
					"access_token="+URLEncoder.encode(accessToken, "UTF-8"));
			for (String line : lines) {
				if (line.startsWith("{")) {
					Pattern p = Pattern.compile("\"(\\w+)\":\"([@.\\s\\w]+)\"");
					Matcher m = p.matcher(line);
					while (m.find()) {
						String param = m.group(1);
						String value = m.group(2);
						if (param.equals("id")) {
							accountId = value;
						}
						else if (param.equals("email")) {
							userEmail = value;
						}
					}
				}
			}
			
			//login or signup
	        TalkerBean talker = TalkmiDBUtil.getTalkerByAccount("facebook", accountId);
	        if (talker != null) {
	        	// insert login record into db
				Thread tInsertLoginRecord = new Thread(new InsertLoginRecordThread(
						talker.getUID()), "InsertLoginRecordThread");
				tInsertLoginRecord.start();

				// add TalkerBean to session
				request.getSession().setAttribute("talker", talker);
				request.getSession().setAttribute("username", talker.getUserName());
				
				response.sendRedirect("redirect.jsp?url="+
			    		 URLEncoder.encode("TalkerHome.jsp", "UTF-8"));
	        }
	        else {
	        	 request.getSession().setAttribute("accounttype", "facebook");
			     request.getSession().setAttribute("accountid", accountId);
			     response.sendRedirect("redirect.jsp?url="+
			    		 URLEncoder.encode("SignUp.jsp?email="+userEmail+"&from=facebook", "UTF-8"));
	        }
		}
	}

}
