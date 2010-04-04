package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class TalkerBean {
	private String userName;
	private String password;
	private String email;
	private char gender;
	private Date dob;
	private int UID;
	
	public TalkerBean(){}
	
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
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
	public void parseLoginRequest(String un, String pw){
		setUserName(un);
		setPassword(pw);
	}
	public void parseSet(ResultSet set) throws SQLException{
		setUID(set.getInt("uid"));
		setUserName(set.getString("uname"));
		setPassword(set.getString("password"));
		setEmail(set.getString("email"));
		setGender(set.getString("gender").charAt(0));
		setDob(set.getDate("dob"));
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
}	