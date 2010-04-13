<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" > 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="author" content="Talkmi" /> 
	<meta name="keywords" content="support, health, talk" /> 
	<meta name="description" content="real-time support for your health issues" /> 
	<meta name="robots" content="all" /> 
	<title>Talkmi : Real-time support for your health issues</title>
	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript" charset="utf-8">
	function validateFormOnSubmit(signup) {
	var reason = "";
	
	  reason += validateUsername(signup.username);
	  reason += validatePassword(signup.password);
	  reason += validateEmail(signup.email);
	  reason += validateMonth(signup.month);  
	  reason += validateDay(signup.day);  
	  reason += validateYear(signup.year);  
	  reason += validateGender(signup.gender);  
	//  reason += validateAge(signup.age);  
		
	  if (reason != "") {
	    alert("Some fields need correction:\n" + reason);
	    return false;
	  }
	
	  // alert("All fields are filled correctly");
	  return true;
	}
	function validateEmpty(fld) {
	    var error = "";
	 
	    if (fld.value.length == 0) {
	        fld.style.background = 'orange'; 
	        error = "The required field has not been filled in.\n"
	    } else {
	        fld.style.background = 'White';
	    }
	    return error;  
	}
	function validateUsername(fld) {
	    var error = "";
	
	    var varUN = new RegExp("^[\\w-_\\.]{1,25}$");
	    if (fld.value == "") {
	        fld.style.background = 'orange'; 
	        error = "You didn't enter a username.\n";
	    } else if ((fld.value.length < 3) || (fld.value.length > 25)) {
	        fld.style.background = 'orange'; 
	        error = "Username must be between 3 and 25 Characters.\n";
	    } else if (fld.value.search(varUN) == -1) {
	        fld.style.background = 'orange'; 
	        error = "The username contains illegal characters. Only letters, numbers, underscores, dashes, and periods are allowed.\n";
	    } else {
	        fld.style.background = 'White';
	    }
	    /*varUN = new RegExp("^[\\w-_\\.]{1,25}$");
	    if(fld.value.search(varUN) == -1){
	    	fld.style.background = 'orange'; 
	        error = "The username contains illegal characters. Only letters, numbers, underscore, dash, and period are allowed.\n";
	    } else if (illegalChars.test(fld.value)) {
	        fld.style.background = 'orange'; 
	        error = "The username contains illegal characters. Only letters, numbers, underscore, dash, and period are allowed.\n";
	    } var illegalChars = /\W/; // allow letters, numbers, and underscores
	    */
	    return error;
	}
	
	function validatePassword(fld) {
	    var error = "";
	    var varPW = new RegExp("^[\\w-_\\.?!@#$%^&*()+=]{1,25}$");
	    if (fld.value == "") {
	        fld.style.background = 'orange';
	        error = "You didn't enter a password.\n";
	    } else if ((fld.value.length < 4) || (fld.value.length > 25)) {
	        error = "Password must be between 3 and 25 Characters. \n";
	        fld.style.background = 'orange';
	    } else if (fld.value.search(varPW) == -1) {
	        fld.style.background = 'orange'; 
	        error = "The username contains illegal characters. Only letters, numbers, and the following characters are allowed: !@#$%^&*()_-+=?\.n";
	    } /*else if (illegalChars.test(fld.value)) {
	        error = "The password contains illegal characters. Only numbers, letters, underscore, dash, and period allowed.  \n";
	        fld.style.background = 'orange';
	    } /*else if (!((fld.value.search(/(a-z)+/)) && (fld.value.search(/(0-9)+/)))) {
	        error = "The password must contain at least one numeral.\n";
	        fld.style.background = 'orange';
	    } var illegalChars = /[\W_]/; // allow only letters and numbers 
		  */else {
	        fld.style.background = 'White';
	    }
	   return error;
	}  
	function trim(s)
	{
	  return s.replace(/^\s+|\s+$/, '');
	}
	function validateEmail(fld) {
	    var error="";
	    var tfld = trim(fld.value);                        // value of field with whitespace trimmed off
	    var emailFilter = /^[^@]+@[^@.]+\.[^@]*\w\w$/ ;
	    var illegalChars= /[\(\)\<\>\,\;\:\\\"\[\]]/ ;
	   
	    if (fld.value == "") {
	        fld.style.background = 'orange';
	        error = "You didn't enter an email address.\n";
	    } else if (!emailFilter.test(tfld)) {              //test email for illegal characters
	        fld.style.background = 'orange';
	        error = "Please enter a valid email address.\n";
	    } else if (fld.value.match(illegalChars)) {
	        fld.style.background = 'orange';
	        error = "The email address contains illegal characters.\n";
	    } else {
	        fld.style.background = 'White';
	    }
	    return error;
	}
	
	
	function validateMonth(fld) {
	    var error = "";
	 
	    if (fld.value == 0) {
	        fld.style.background = 'orange'; 
	        error = "Please select your birth month.\n";
	    } else {
	        fld.style.background = 'White';
	    }
	    return error;
	}
	
	function validateDay(fld) {
	    var error = "";
	 
	    if (fld.value == 0) {
	        fld.style.background = 'orange'; 
	        error = "Please select your birth date.\n";
	    } else {
	        fld.style.background = 'White';
	    }
	    return error;
	}
	
	
	function validateYear(fld) {
	    var error = "";
	 
	    if (fld.value == 0) {
	        fld.style.background = 'orange'; 
	        error = "Please select your birth year.\n";
	    } else {
	        fld.style.background = 'White';
	    }
	    return error;
	}
	/*function validateYearr(fld) {
	    var error = "";
	    var Year_filter = /^[1-2][0-9][0-9][0-9]/
	    
	   if ((fld.value == "") || (fld.value == "yyyy")) {
	        error = "Please enter your birth year.\n";
	        fld.style.background = 'orange';
	    } else if (!Year_filter.test(fld.value)) {
		    error = "Please enter a valid year.\n";
		    fld.style.background = 'White';
		}
	    return error;
	} 
	
	function validateGender(fld) {
	    var error = "";
	 
	    if (fld.value == 0) {
	        fld.style.background = 'orange'; 
	        error = "Please choose a your gender.\n";
	    } else {
	        fld.style.background = 'White';
	    }
	    return error;
	}
	*/
	
	function validateGender(btn) {
	    var error = "";
	    var cnt = -1;
	
	    for (var i=btn.length-1; i > -1; i--){
	     if (btn[i].checked) {cnt = i; i = -1;}
	 	 } if (cnt > -1) {error = "";
	 	 } else {error = "Please choose a your gender.\n";
	 	 }
	 	 return error;
	}
	               
	function validateAge(fld)  {
		var error = "";
		
		if (!document.getElementById("age").checked){
	        fld.style.background = 'orange'; 
	        error = "You must agree to the Terms of Service and Age requirements";
		} else {
	        fld.style.background = 'White';
		}
		return error;
	}	
</script>

</head>

<body>
<div class="top">
	<h5>
		<a href="index.jsp"> Home |</a>
		<a href="faq.jsp"> FAQ |</a>
		<a href="feedback.jsp"> Feedback</a>
	</h5>
</div>	

<div class="center">
	<a href="/tah-java/index.jsp"><h1><span>Talkmi</span></h1></a>
	<div id="signup">	
	<div id="padding">


		    <h2>Create your account</h2>
			<form id="form" name="signup" onsubmit="return validateFormOnSubmit(this)" action="/tah-java/SignUp" method="post">
			<h5>
			<%@page import = "java.sql.*" %>
			<%@page import = "java.security.MessageDigest" %>
			<%
			String unerror = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String year = request.getParameter("year");
			String gender = request.getParameter("gender");
			String zip = request.getParameter("zip");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			
			if (unerror != null && unerror.equals("u")) {
				out.println("<p>That user name is already in taken.</p>  Please enter a different user name.");
				unerror = "";
			} else if (unerror == null) {
				unerror = "";
			}
			/*if (tnerror != null && tnerror.equals("c")) {
				out.println("That telephone number is already in use.  Please enter a new telephone number.");
				tnerror = "";
			} else if (tnerror != null) {
				areacode = tnerror.substring(1,4);
				threeDigits = tnerror.substring(4,7);
				fourDigits = tnerror.substring(7,11);
			}*/
			if (email == null){
				email = "";
			}
			if (month == null){
				month = "";
			}if (day == null){
				day = "";
			}if (year == null){
				year = "";
			}if (gender == null){
				gender = "";
			}if (zip == null){
				zip = "";
			}if (city == null){
				city = "";
			}if (state == null){
				state = "";
			}
			
	/*		byte[] unencryptedPassword = password.getBytes();
			MessageDigest md = null;
			try
			{
				md = MessageDigest.getInstance("MD5");
			} catch(Exception e){}
			
			md.reset();
			md.update(unencryptedPassword);
			
			byte[] encryptedPassword = md.digest();
			StringBuffer pwBuf = new StringBuffer();
			for(int i=0;i<encryptedPassword.length;i++)
			{
				if (((int) encryptedPassword[i] & 0xff) < 0x10) 
				{
					pwBuf.append("0");
				}
				pwBuf.append(Long.toString((int) encryptedPassword[i] & 0xff, 16));
			}
			String pw = pwBuf.toString();
			String URL = "jdbc:mysql://localhost:3306/testDB";
			Connection con = null;
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(URL, "root", "applepie");
				PreparedStatement ps = con.prepareStatement("INSERT INTO Register(user, pass, name, phone) VALUES()");
				ps.setString(1, unerror);
				ps.setString(2, pw);
				int i = ps.executeUpdate();
				ps.close();	
				con.close();
			}catch(Exception e){}  */
			%>
			</h5>
			
		    <label for="username" id="uname"><span>Username</span>
		        <input id="username" name="username" maxlength="25" size="25" type="text" class="box" value='<%= unerror %>' /><br />
		    </label>
		    <label for="password" id="passwd"><span>Password</span>
		    	<input id="password" name="password" maxlength="25" size="25" type="password" class="box" /><br />
		    </label>
		    <label for="email" id="mail"><span>Email</span>
		    	<input id="email" name="email" maxlength="45" size="25" type="text" class="box" value='<%= email %>' /><br />
			</label>

			<label for="mm,dd,yyyy" id="dob"><span>Date of Birth</span>
				<select id="mm" name="month" class="DOBbox">
					<option selected value='0'>month</option>
					<option value=01>Jan</option>
					<option value=02>Feb</option>
					<option value=03>Mar</option>
					<option value=04>Apr</option>
					<option value=05>May</option>
					
					<option value=06>Jun</option>
					<option value=07>Jul</option>
					<option value=08>Aug</option>
					<option value=09>Sep</option>
					<option value=10>Oct</option>
					<option value=11>Nov</option>
					
					<option value=12>Dec</option>                      
				</select> 
                <select id="dd" name="day"  class="DOBbox">
                	<option selected value='0'>day</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
					<option value="21">21</option>
					<option value="22">22</option>
					<option value="23">23</option>
					<option value="24">24</option>
					<option value="25">25</option>
					<option value="26">26</option>
					<option value="27">27</option>
					<option value="28">28</option>
					<option value="29">29</option>
					<option value="30">30</option>
					<option value="31">31</option>
              	</select>
                 <select id="yyyy" name="year"  class="DOBbox">
                	<option selected value='0'>year</option>
					<option value="1991">1991</option>
					<option value="1990">1990</option>
					<option value="1989">1989</option>
					<option value="1988">1988</option>
					<option value="1987">1987</option>
					<option value="1986">1986</option>
					<option value="1985">1985</option>
					<option value="1984">1984</option>
					<option value="1983">1983</option>
					<option value="1982">1982</option>
					<option value="1981">1981</option>
					<option value="1980">1980</option>
					<option value="1979">1979</option>
					<option value="1978">1978</option>
					<option value="1977">1977</option>
					<option value="1976">1976</option>
					<option value="1975">1975</option>
					<option value="1974">1974</option>
					<option value="1973">1973</option>
					<option value="1972">1972</option>
					<option value="1971">1971</option>
					<option value="1970">1970</option>
					<option value="1969">1969</option>
					<option value="1968">1968</option>
					<option value="1967">1967</option>
					<option value="1966">1966</option>
					<option value="1965">1965</option>
					<option value="1964">1964</option>
					<option value="1963">1963</option>
					<option value="1962">1962</option>
					<option value="1961">1961</option>
					<option value="1960">1960</option>
					<option value="1959">1959</option>
					<option value="1958">1958</option>
					<option value="1957">1957</option>
					<option value="1956">1956</option>
					<option value="1955">1955</option>
					<option value="1954">1954</option>
					<option value="1953">1953</option>
					<option value="1952">1952</option>
					<option value="1951">1951</option>
					<option value="1950">1950</option>
					<option value="1949">1949</option>
					<option value="1948">1948</option>
					<option value="1947">1947</option>
					<option value="1946">1946</option>
					<option value="1945">1945</option>
					<option value="1944">1944</option>
					<option value="1943">1943</option>
					<option value="1942">1942</option>
					<option value="1941">1941</option>
					<option value="1940">1940</option>
					<option value="1939">1939</option>
					<option value="1938">1938</option>
					<option value="1937">1937</option>
					<option value="1936">1936</option>
					<option value="1935">1935</option>
					<option value="1934">1934</option>
					<option value="1933">1933</option>
					<option value="1932">1932</option>
					<option value="1931">1931</option>
					<option value="1930">1930</option>
					<option value="1929">1929</option>
					<option value="1928">1928</option>
					<option value="1927">1927</option>
					<option value="1926">1926</option>
					<option value="1925">1925</option>
					<option value="1924">1924</option>
					<option value="1923">1923</option>
					<option value="1922">1922</option>
					<option value="1921">1921</option>
					<option value="1920">1920</option>
					<option value="1919">1919</option>
					<option value="1918">1918</option>
					<option value="1917">1917</option>
					<option value="1916">1916</option>
					<option value="1915">1915</option>
					<option value="1914">1914</option>
					<option value="1913">1913</option>
					<option value="1912">1912</option>
					<option value="1911">1911</option>
					<option value="1910">1910</option>
					<option value="1909">1909</option>
					<option value="1908">1908</option>
					<option value="1907">1907</option>
					<option value="1906">1906</option>
					<option value="1905">1905</option>
					<option value="1904">1904</option>
					<option value="1903">1903</option>
					<option value="1902">1902</option>
					<option value="1901">1901</option>
					<option value="1900">1900</option>              	
              	</select>              	
			</label>

			<div id="gender">
			<span>Gender</span>
		    <span for="male"><input type="radio" value="M" id="male" name="gender" />male</span>
		    <span for="female"><input type="radio" value="F" id="female" name="gender" />female</span>
  			</div>


		 <br />	
		 <font size="2" class="agreement">
		<!--<input type="checkbox" value="0" id="age" name="age" />-->
		<i>By clicking the "Create Account", you confirm  <p>that you are at least 18 years of age</p> <p>and agree to the <a href="tos.jsp">Terms of Service</a>.</p> 
		</i>
		</font>	
		<br />
		 	<input id="submit" name="submit" type="submit" value="Create Account" />
	 		
		</form>
		<br />
		<br />
		<div id="privacy">
		<a href="privacy.jsp">We value your privacy</a>
		</div>
	</div>
	</div>
	<div class="clear">
	</div>
</div>

<div class="bottom">
	<div id="footer">
		<ul>
			<li><a href="index.jsp"> Home</a>
			<li><a href="faq.jsp"> FAQ</a>
			<li><a href="privacy.jsp"> Privacy</a>
			<li><a href="tos.jsp"> Terms of Use</a>
			<li><a href="mailto:feedback@Talkmi.com"> Feedback</a>
		</ul>
		<p>Copyright Talkmi </p>
	</div>
</div>
<!-- begin Google Analytics -->
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-13005583-1");
pageTracker._trackPageview();
} catch(err) {}</script>
<!-- end Google Analytics -->
</body>
</html>