package util;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.httpclient.HttpException;
import org.xml.sax.SAXException;

import com.sailthru.TriggerMailClient;

/**
 * Uses Sailthru API, http://sailthru.com/
 * @author kindcoder
 */
public class EmailUtil {
	
	private static final String SAILTHRU_APIKEY = "4007bc4d4b48586353eb44012172eaf3";
	private static final String SAILTHRU_SECRET = "4ba0a437f0f138fceba76dac5c33e567";
	
	public static final String WELCOME_TEMPLATE = "welcome";
	public static final String FORGOT_PASSWORD_TEMPLATE = "forgotpassword";
	
	public static void sendEmail(String templateName, String toEmail) {
		sendEmail(templateName, toEmail, null, null);
	}
	
	public static void sendEmail(String templateName, String toEmail, Map<String, String> vars) {
		sendEmail(templateName, toEmail, vars, null);
	}
	
	public static void sendEmail(String templateName, String toEmail, Map<String, String> vars, Map<String, String> options) {
		TriggerMailClient client;
		try {
			client = new TriggerMailClient(SAILTHRU_APIKEY, SAILTHRU_SECRET);
			//TODO: replace to real "toEmail" later
			client.send(templateName, "support@talkabouthealth.com", vars, options);
			
			//TODO: handle exceptions
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
