<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Welcome</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="p7exp/tt2.css" rel="stylesheet" type="text/css">


<style>
body {
	margin: 0px;
	padding:0px;
	font-family:arial;
	font-size:11px;
	color:#000000;
	background:url(images/inner_bg.gif) repeat-x top;
	}
	
</style>

<!-- scripts -->
	<script language="javascript">
		function trim(stringToTrim) {
			return stringToTrim.replace(/^\s+|\s+$/g,"");
		}
	
		function checkForm()
		{
			//Prompt if missing userid and password
			if((document.login.username.value == "") && (document.login.password.value == ""))
			{
				alert("Please enter your username and password");
				document.login.username.focus();
				return false;
			}
			//Prompt if missing userid
			if(document.login.username.value == "")
			{
				alert("Please enter your username");
				document.login.username.focus();
				return false;
			}
	
			//Prompt if missing password
			if(document.login.password.value == "")
			{
				alert("Please enter your password");
				document.login.password.focus();
				return false;
			}
			
			return true;
		}
 
	</script>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", -1); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

String sUserName = (String)session.getAttribute("username");
if (sUserName != null) { 
	response.sendRedirect("TalkerHome.jsp");
} else {%>


</head>

<body>
<div id="top_container">
<div id="top">
<div id="logo"><a href="#"><img src="images/spacer.gif" alt="Talk About Health" width="328" height="73" border="0" /></a> </div>

<div id="innerbanner"></div>
<div id="innermain">
<div class="blacktext2" id="innerheading">Login to your Account</div>
<div id="innermiddlearea">
<form id="loginform" action="/tah-java/Login" name="login" method="post" class="loginform">
<div id="loginarea">
<h1>Username or Email</h1>
<div class="fieldarea">
<div class="fieldarealeft"></div>
<div class="fieldareamid"><input name="username" type="text"  class="textfields" id="textfield" onclick="this.value=''" value="Username" />
</div>
<div class="fieldarearight"></div>

<h1>Password</h1>
<div class="fieldarea">
<div class="fieldarealeft"></div>
<div class="fieldareamid"><input name="password"  type="password" class="textfields" id="textfield" onclick="this.value=''" value="Password" />

</div>

<div class="fieldarearight"></div>

</div>
<div class="checkboxarea"><input type="checkbox" class="checkboxclass" value="" />Remeber me</div>
<div class="forgotpassword"><a href="#" class="bluetext12">forgot password?</a></div>
</div>

<!--  <div class="signupbutton"><img src="images/signinbutton.gif" width="126" height="46" /></div> -->
<div class="signupbutton">
<input id="login" type="image" src="images/signinbutton.gif" value="Continue"  TABINDEX=3></input>
</div>
</form>
<div class="newusersignup"><a href="#" class="bluetext12"></a>Not a User ? <br>
  <a href="SignUp.jsp" class="pinktext14">Create your account</a></div>
</div>
<div id="rightarea">
 
   Or Sign in with one-click

  <div class="socialicons"><ul class="socialicons1">
	<li><a href="#"><img src="images/facebookicon.gif" width="150" height="22" border="0" /></a></li>
	<li><a href="#"><img src="images/twittericon.gif" width="150" height="22" border="0" /></a></li>
	<li><a href="#"><img src="images/googleicon.gif" width="150" height="22" border="0" /></a></li>
	
</ul></div>
</div>

</div>

</div>



<div id="bottom_container"></div>
<div id="footerarea">
<div id="footer">
<div id="footerleft"><a href="#" class="footerlink">Blog</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" class="footerlink">Faq</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" class="footerlink">Contact Us</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" class="footerlink">About Us</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" class="footerlink">Privacy Policy</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" class="footerlink">Terms of Service</a></div>
<div id="footerright"><img src="images/footerlogo.gif" width="237" height="25" alt="" /></div>
</div>
</div>

</body>
</html>
<% }%>