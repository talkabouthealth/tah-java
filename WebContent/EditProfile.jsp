<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" > 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="author" content="Talkmi" /> 
	<meta name="keywords" content="support, health, talk" /> 
	<meta name="description" content="real-time support for your health issues" /> 
	<meta name="robots" content="all" /> 
	<title>Talkmi : Real-time support for your health issues</title>
	
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ page import="beans.TalkerBean" %>

<% 
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

String sUserName = (String)session.getAttribute("username");
if (sUserName == null) { 
	response.sendRedirect("index.jsp");
} else {
	TalkerBean cb = (TalkerBean)session.getAttribute("talker");
%>

<script>

/*
Auto tabbing script- By JavaScriptKit.com
http://www.javascriptkit.com
This credit MUST stay intact for use
*/

function autotab(original,destination){
if (original.getAttribute&&original.value.length==original.getAttribute("maxlength"))
destination.focus()
}

</script>

<script type="text/javascript" charset="utf-8">
function validateFormOnSubmit(updateprofile) {
var reason = "";

  reason += validateUsername(updateprofile.username);
  reason += validatePassword(updateprofile.password);
  reason += validateEmail(updateprofile.email);
  reason += validatePhone1(updateprofile.phone1);
  reason += validatePhone2(updateprofile.phone2);
  reason += validatePhone3(updateprofile.phone3);


      
  if (reason != "") {
    alert("Some fields need correction:\n" + reason);
    return false;
  }

  alert("All fields are filled correctly");
  return true;
}
function validateEmpty(fld) {
    var error = "";
 
    if (fld.value.length == 0) {
        fld.style.background = 'Yellow'; 
        error = "The required field has not been filled in.\n"
    } else {
        fld.style.background = 'White';
    }
    return error;  
}
function validateUsername(fld) {
    var error = "";
    var illegalChars = /\W/; // allow letters, numbers, and underscores
 
    if (fld.value == "") {
        fld.style.background = 'Yellow'; 
        error = "You didn't enter a username.\n";
    } else if ((fld.value.length < 1) || (fld.value.length > 15)) {
        fld.style.background = 'Yellow'; 
        error = "The username is the wrong length.\n";
    } else if (illegalChars.test(fld.value)) {
        fld.style.background = 'Yellow'; 
        error = "The username contains illegal characters.\n";
    } else {
        fld.style.background = 'White';
    }
    return error;
}
function validatePassword(fld) {
    var error = "";
    var illegalChars = /[\W_]/; // allow only letters and numbers 
 
    if (fld.value == "") {
        fld.style.background = 'Yellow';
        error = "You didn't enter a password.\n";
    } else if ((fld.value.length < 1) || (fld.value.length > 15)) {
        error = "The password is the wrong length. \n";
        fld.style.background = 'Yellow';
    } else if (illegalChars.test(fld.value)) {
        error = "The password contains illegal characters.\n";
        fld.style.background = 'Yellow';
    } else if (!((fld.value.search(/(a-z)+/)) && (fld.value.search(/(0-9)+/)))) {
        error = "The password must contain at least one numeral.\n";
        fld.style.background = 'Yellow';
    } else {
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
        fld.style.background = 'Yellow';
        error = "You didn't enter an email address.\n";
    } else if (!emailFilter.test(tfld)) {              //test email for illegal characters
        fld.style.background = 'Yellow';
        error = "Please enter a valid email address.\n";
    } else if (fld.value.match(illegalChars)) {
        fld.style.background = 'Yellow';
        error = "The email address contains illegal characters.\n";
    } else {
        fld.style.background = 'White';
    }
    return error;
}


</script>
	
</head>


<body>

	<div id="header">
	<h1><span>Talkmi</span></h1>
	
		<div id="box">
	
			<form id="form" name="updateprofile" onsubmit="return validateFormOnSubmit(this)" action="/tah-java/UpdateProfile" method="post" >

				
				<label><span>Username</span></label>
					<input id="username" name="username" maxlength="15" size="25" class="input-box" type="text" value="<%=cb.getUserName()%>"><br>
			  
				    <label><span>Gender</span></label>
			    	<input id="gender" name="gender" maxlength="1" size="3" type="text" class="input-box" value="<%=cb.getGender()%>"><br>
			 
			  	<label><span>Date of Birth <h5>(mm / dd / yyyy)</h5></span></label>
					<input id="month" name="month" maxlength="2" size="2" class="input-box2" type="text" onKeyup="autotab(this, document.signup.month)"  value="<%=cb.getDOBMonth()%>">
					<input id="day" name="day" maxlength=2" size="2" class="input-box2" type="text" onKeyup="autotab(this, document.signup.day)"  value="<%=cb.getDOBDay()%>">
					<input id="year" name="year" maxlength="4" size="4" class="input-box2" type="text"  value="<%=cb.getDOBYear()%>"><br><br>
			 
			    <label><span>Email</span></label>
			    	<input id="email" name="email" maxlength="45" size="25" type="text" class="input-box" value="<%=cb.getEmail()%>"><br>
			 	
			 	<label>ZipCode</label>
			 		<input id="zipcode" name="zipcode" type="text"> <br />
			 	
			 	<label>Patient/Caregiver/etc.</label>
			 		<select id="selection" name="selection">
			 		<option selected value='0'>Patient/Caregiver/other/etc. </option>
			 		<option value="Patient">Patient</option>
				 	<option value="Former Patient">Former Patient</option>
				 	<option value="Caregiver">Caregiver</option>
				 	<option value="Family member">Family member</option> 
					<option value="Friend">Friend</option>
					<option value="Physician">Physician</option>
					<option value="Nurse">Nurse</option> 
					</select>
					<br/>
				
				<label>Marital status</label>
			 		<select id="maritalstatus" name="maritalstatus" >
				 		<option selected value='Single'>Single</option>
						<option value="Married">Married/partnered relationship</option>
						<option value="Divorced">Divorced</option>
						<option value="Separated">Separated</option>
						<option value="Widowed">Widowed</option> 
					</select> <br />
				
				<label>Number of Children
					<select id="children" name="children" >
					<option selected value="1"> 1 </option>
					<option value="2"> 2 </option>
					<option value="3"> 3 </option>
					<option value="more"> More than 3 </option> 
					</select> 
				</label> <br />				
					
			  	<label><span>Password</span></label>
					<input id="password" name="password" maxlength="15" size="25" class="input-box"  type="password" value="<%=cb.getPassword()%>"><br />
					
					<input id="submit" name="submit" type=submit value="Update Profile"> <br />
			</form>
			
			<p><a href="TalkerHome.jsp">Cancel</a></p>
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
<% }%>