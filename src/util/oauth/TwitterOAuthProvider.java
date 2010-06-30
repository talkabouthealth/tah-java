package util.oauth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.TalkerBean;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import util.TalkmiDBUtil;
import webapp.InsertLoginRecordThread;

/**
 * Implemented using http://code.google.com/p/oauth-signpost/ library
 *
 */
public class TwitterOAuthProvider implements OAuthServiceProvider {
	
	private static final String CONSUMER_KEY = "0xkxCSpMbDPH2ltt3MwZA";
	private static final String CONSUMER_SECRET = "ybARF7Q7ffdBqbTE5FGlPaeSJejGtNHeoLhYmk2gL4";
	private static final String CALLBACK_URL =
		"http://talkabouthealth.com:8080/tah-java/oauth?action=callback&type=twitter";
	
//Test values
//	private static final String CONSUMER_KEY = "7VymbW3wmOOoQ892BqIsaA";
//	private static final String CONSUMER_SECRET = "s8aexaIBgMxAm4ZqQNayv5SAr6Wd1SKFVETUEPv0cmM";
//	private static final String CALLBACK_URL =
//		"http://kan.dev.com:8080/tah-java/oauth?action=callback&type=twitter";
	
	private OAuthConsumer consumer;
	private OAuthProvider provider;


	public TwitterOAuthProvider() {}
	
	public String getAuthURL(HttpServletRequest request) {
		consumer = new DefaultOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		provider = new DefaultOAuthProvider(
	            "http://twitter.com/oauth/request_token",
	            "http://twitter.com/oauth/access_token",
	            "http://twitter.com/oauth/authorize");
		
        String authURL = null;
        try {
			authURL = provider.retrieveRequestToken(consumer, CALLBACK_URL);
			
			//save token and token secret for next step of OAuth
//			request.getSession().setAttribute("twitter_token", consumer.getToken());
//			request.getSession().setAttribute("twitter_token_secret", consumer.getTokenSecret());
//			System.out.println(consumer.getToken()+ " :1: "+consumer.getTokenSecret());
			
			//TODO: not save this in session - check signpost code
			request.getSession().setAttribute("twitter_consumer", consumer);
			request.getSession().setAttribute("twitter_provider", provider);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authURL;
	}

	@Override
	public void handleCallback(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String oauthVerifier = request.getParameter("oauth_verifier");
//		String token = (String)request.getSession().getAttribute("twitter_token");
//		String tokenSecret = (String)request.getSession().getAttribute("twitter_token_secret");
		consumer = (OAuthConsumer)request.getSession().getAttribute("twitter_consumer");
		provider = (OAuthProvider)request.getSession().getAttribute("twitter_provider");
		try {
//			consumer.setTokenWithSecret(token, tokenSecret);
//			System.out.println(consumer.getToken()+ " :2: "+consumer.getTokenSecret());
			provider.retrieveAccessToken(consumer, oauthVerifier);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        URL url = new URL("http://api.twitter.com/1/account/verify_credentials.xml");
        HttpURLConnection req = (HttpURLConnection) url.openConnection();
        //sign the request
        try {
			consumer.sign(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
        //send the request
        req.connect();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String line = null;
        String screenName = null;
        String accountId = null;
        while ((line = br.readLine()) != null) {
        	//For now we get only screen_name
        	//Ex: <screen_name>kankangaroo</screen_name>
        	line = line.trim();			        	
        	if (line.startsWith("<screen_name>")) {
        		screenName = line.substring(13, line.length()-14);
        	}
        	
        	//Ex: <id>23090656</id>
        	if (line.startsWith("<id>")) {
        		accountId = line.substring(4, line.length()-5);
        	}
        }
        br.close();
        
        //login or signup
        TalkerBean talker = TalkmiDBUtil.getTalkerByAccount("twitter", accountId);
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
        	 request.getSession().setAttribute("accounttype", "twitter");
		     request.getSession().setAttribute("accountid", accountId);
		     response.sendRedirect("redirect.jsp?url="+
		    		 URLEncoder.encode("SignUp.jsp?username="+screenName+"&from=twitter", "UTF-8"));
        }
	}
}
