package util;

//TODO remove it before MongoDB?
public class ServerInfo {
	
	private static String DBUserName = "root";
	private static String DBPassword = "applepie";
	private static String DBServerAddress = "jdbc:mysql://localhost:3306/talkmidb";
	
	private ServerInfo() {}
	
	public static String getDBUserName() {
		return DBUserName; 
	}
	public static String getDBPassword() { 
		return DBPassword; 
	}
	public static String getDBServerAddress() { 
		return DBServerAddress; 
	}
}
