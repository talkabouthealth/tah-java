package util;

import java.util.regex.Pattern;

public class ValidateData {
	private static final Pattern unpwPattern = Pattern.compile("([\\w-_\\.]){1,25}");
	private static final Pattern emailPattern = Pattern.compile("^[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.(([0-9]{1,3})|([a-zA-Z]{2,3})|(aero|coop|info|museum|name))$");
	private static final Pattern monthPattern = Pattern.compile("^[1-9]{1}$|^[01]{1}[0-9]{1}$");
	private static final Pattern dayPattern = Pattern.compile("^[1-9]{1}$|^[1-3]{1}[0-9]{1}$");
	private static final Pattern yearPattern = Pattern.compile("^[1-2]{1}[09]{1}[0-9]{1}[0-9]{1}$");
	private static final Pattern genderPattern = Pattern.compile("^[MF]{1}$");
	private static final Pattern topicPattern = Pattern.compile("([\\w\\s-_\\.,<>/?':;\"\\[\\]\\{\\}|\\\\!~`@#$%^&*()+=蒔]){1,140}");
	
	public static boolean validateUserName (String un) {
		//System.out.println("In UN validation");
		// username can only have alphanumeric, _,-, and . and cannot be greater than 25 characters
		if (!unpwPattern.matcher( un ).matches()) {
			return false;
		}	 	
		return true;
	}
	public static boolean validatePassword (String pw) {
		//System.out.println("In PW validation");
		// password can only have alphanumeric, _,-, and . and cannot be greater than 25 characters
		if (unpwPattern.matcher( pw ).matches()) {
			return true;
		}
		return false;
	}
	public static boolean validateEmail (String email) {
		//System.out.println("In Email validation");
		// password can only have alphanumeric, _,-, and . and cannot be greater than 25 characters
		if (emailPattern.matcher( email ).matches()) {
			return true;
		}
		return false;
	}
	public static boolean validateMonth (String month) {
		//System.out.println("In Month validation");
		// password can only have alphanumeric, _,-, and . and cannot be greater than 25 characters
		if (monthPattern.matcher( month ).matches()) {
			return true;
		}
		return false;
	}
	public static boolean validateDay (String day) {
		//System.out.println("In Day validation");
		// password can only have alphanumeric, _,-, and . and cannot be greater than 25 characters
		if (dayPattern.matcher( day ).matches()) {
			return true;
		}
		return false;
	}
	public static boolean validateYear (String year) {
		//System.out.println("In year validation");
		// password can only have alphanumeric, _,-, and . and cannot be greater than 25 characters
		if (yearPattern.matcher( year ).matches()) {
			return true;
		}
		return false;
	}
	public static boolean validateGender (String gender) {
		//System.out.println("In gender validation");
		// password can only have alphanumeric, _,-, and . and cannot be greater than 25 characters
		if (genderPattern.matcher( gender ).matches()) {
			return true;
		}
		return false;
	}
	public static boolean validateTopic (String topic) {
		//System.out.println("In topic validation");
		// password can only have alphanumeric, _,-, and . and cannot be greater than 25 characters
		if (topicPattern.matcher( topic ).matches()) {
			return true;
		}
		return false;
	}
}