package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class TalkerBean {
	private String userName;
	private String password;
	private String IM;
	private String email;
	private char gender;
	private Date dob;
	private int UID;
	private String MariStat;
	private String Category;
	private String city;
	private String state;
	private String country;
	private int itype;
	private int nfreq;
	private int ntime;
	
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
	
	public String getMariStat(){
		return MariStat;
	}
	public String getCategory(){
		return Category;
	}
	
	public String getCity(){
		return city;
	}
	public String getState(){
		return state;
	}
	public String getCountry(){
		return country;
	}
	public int getItype(){
		return itype;
	}
	public int getNfreq(){
		return nfreq;
	}
	public int getNtime(){
		return ntime;
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
	public void setMariStat(String value){
		MariStat = value;
	}
	public void setCategory(String value){
		Category = value;
	}
	
	public void setCity(String value){
		city = value;
	}
	public void setState(String value){
		state = value;
	}
	public void setCountry(String value){
		country = value;
	}
	public void setItype(int value){
		itype = value;
	}
	public void setNfreq(int value){
		nfreq = value;
	}
	public void setNtime(int value){
		ntime = value;
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
		setCity(set.getString("city"));
		setState(set.getString("state"));
		setCountry(set.getString("country"));
		setNfreq(set.getInt("notifyfrequency"));
		setNtime(set.getInt("notifytime"));
		setMariStat(set.getString("Marital_Stat"));
		setCategory(set.getString("category"));
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