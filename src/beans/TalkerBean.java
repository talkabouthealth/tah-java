package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
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
	private String MariStat;
	private String Category;
	private String city;
	private String state;
	private String country;
	//conversations types
	private String[] ctype;
	private int nfreq;
	private int ntime;
	private int childrenNum;
	
	private String imagePath;
	
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
		setCity(set.getString("city"));
		setState(set.getString("state"));
		setCountry(set.getString("country"));
		setNfreq(set.getInt("notifyfrequency"));
		setNtime(set.getInt("notifytime"));
		setMariStat(set.getString("Marital_Stat"));
		setCategory(set.getString("category")); 
		setChildrenNum(set.getInt("childrenNum"));
		setImagePath(set.getString("imagepath"));
		
		parseProfilePreferences(set.getInt("profilepreferences"));
		parseCType(set.getString("ctype"));
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
	 * Convert from Integer to ProfilePreferences EnumSet and vice versa 
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
	
	private void parseCType(String dbCType) {
		if (dbCType != null && dbCType.length() > 2) {
			//remove [ and ] from string
			dbCType = dbCType.substring(1, dbCType.length()-1);
			ctype = dbCType.split(", ");
		}
		else {
			ctype = new String[]{};
		}
	}

	public String getMariStat() {
		return MariStat;
	}

	public void setMariStat(String mariStat) {
		MariStat = mariStat;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getNfreq() {
		return nfreq;
	}

	public void setNfreq(int nfreq) {
		this.nfreq = nfreq;
	}

	public int getNtime() {
		return ntime;
	}

	public void setNtime(int ntime) {
		this.ntime = ntime;
	}

	public String[] getCtype() {
		return ctype;
	}

	public String getCtypeStr() {
		return Arrays.toString(ctype);
	}
	
	public void setCtype(String[] ctype) {
		this.ctype = ctype;
	}

	public int getChildrenNum() {
		return childrenNum;
	}

	public void setChildrenNum(int childrenNum) {
		this.childrenNum = childrenNum;
	}

	public String getImagePath() {
		if (imagePath == null) {
			//return default
			return "images/img1.gif";
		}
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}	