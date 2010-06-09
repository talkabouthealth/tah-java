package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;

public class TalkerBean {
	
	public enum ProfilePreference {
		PERSONAL_INFO(1, "Display my Personal Info in my profile"),
		HEALTH_INFO(2, "Display my Health Info in my profile"),
		BASIC_INFO(4, "Display my Basic Info in my profile (Recognition level, " +
				"No. of conversations)"),
		FOLLOWERS(8, "Display my Followers in my profile"),
		FOLLOWING(16, "Display who I am Following in my profile"),
		CONVERSATIONS(32, "Display the Conversations I have started and joined in my profile"),
		COMMENTS(64, "Display my Comments in my profile"),
		BIO(128, "Display my Bio in my profile"),
		ACTIVITY_STREAM(256, "Display my Activity Stream in my profile");
		
		private int value;
		private String description;
		
		private ProfilePreference(int value, String description) {
			this.value = value;
			this.description = description;
		}

		public int getValue() {
			return value;
		}

		public String getDescription() {
			return description;
		}
	}
	
	private String userName;
	private String password;
	private String IM;
	private String email;
	private char gender;
	private Date dob;
	private int UID;
	private int invitations;
	
	private EnumSet<ProfilePreference> profilePreferences;
	
	public TalkerBean(){}
	
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	
	public String getIM(){
		return IM;
	}
	public int getUID() {
		return UID;
	}
	public void setUID(int value) {
		UID = value;
	}
	public void setUserName(String value) {
		userName = value;
	}
	public void setPassword(String value) {
		password = value;
	}
	
	public void setIM(String value){
		IM = value;
	}
	public void parseLoginRequest(String un, String pw){
		setUserName(un);
		setPassword(pw);
	}
	public void parseSet(ResultSet set) throws SQLException{
		setUID(set.getInt("uid"));
		setUserName(set.getString("uname"));
		setPassword(set.getString("password"));
		setIM(set.getString("PrimaryIM"));
		setEmail(set.getString("email"));
		setGender(set.getString("gender").charAt(0));
		setDob(set.getDate("dob"));
		setInvitations(set.getInt("invitations"));
		
		parseProfilePreferences(set.getInt("profilepreferences"));
	}
	public String getDOBYear() throws SQLException{
		Calendar cal=Calendar.getInstance();
        cal.setTime(dob);
		return String.valueOf(cal.get(Calendar.YEAR));
	}
	public String getDOBMonth() throws SQLException{
		Calendar cal=Calendar.getInstance();
        cal.setTime(dob);
		return String.valueOf(cal.get(Calendar.MONTH));
	}
	public String getDOBDay() throws SQLException{
		Calendar cal=Calendar.getInstance();	
        cal.setTime(dob);
		return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}

	public char getGender() {
		return gender;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDob() {
		return dob;
	}

	public int getInvitations() {
		return invitations;
	}

	public void setInvitations(int invitations) {
		this.invitations = invitations;
	}

	public EnumSet<ProfilePreference> getProfilePreferences() {
		return profilePreferences;
	}

	public void setProfilePreferences(EnumSet<ProfilePreference> profilePreferences) {
		this.profilePreferences = profilePreferences;
	}
	
	/* 
	 * Convert from Integer to ProfilePreference and vice versa 
	 * TODO: think about other place for this functions
	 */
	public int profilePreferencesToInt() {
		int dbValue = 0;
		for (ProfilePreference preference : profilePreferences) {
			dbValue |= preference.getValue();
		}
		return dbValue;
	}
	public void parseProfilePreferences(int dbValue) {
		profilePreferences = EnumSet.noneOf(ProfilePreference.class);
		for (ProfilePreference preference : ProfilePreference.values()) {
			if ((dbValue & preference.getValue()) != 0) {
				profilePreferences.add(preference);
			}
		}
	}
}	