<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
	
	String sUserName = (String)session.getAttribute("username");
	if (sUserName != null) { 
		response.sendRedirect("TalkerHome.jsp");
	} else {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="author" content="Talkmi" /> 
	<meta name="keywords" content="support, health, talk" /> 
	<meta name="description" content="real-time support for your health issues" /> 
	<meta name="robots" content="all" /> 
	<title>Talkmi : Real-time support for your health issues</title>
	
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<style>
		body {
			margin: 0px;
			padding:0px;
			font-family:arial;
			font-size:11px;
			color:#000000;
			background:url(images/inner_bg.gif) repeat-x top;
		}
		#loginarea {
			margin: 30px 0;
		}
		#loginarea span {
			font-size: 17px;
		}
		#rightarea {
			padding-top: 30px;
		}
	</style>

	<!-- scripts -->
	<script language="javascript">
		function checkForm()
		{
			var emailField = document.getElementById('email');
			if (emailField.value == '' || emailField.value == 'Email') {
				alert("Please enter your email");
				return false;
			}
			
			return true;
		}
	</script>
</head>

<%
	String result = request.getParameter("result");
%>

<body>
	<div id="top_container">
		<div id="top">
			<div id="logo">
				<a href="index.jsp"><img src="images/spacer.gif" alt="Talk About Health" width="328" height="73" border="0" /></a>
			</div>
			<div id="innerbanner"></div>
			<div id="innermain">
				<div class="blacktext2" id="innerheading">Password Reset</div>
				<div id="innermiddlearea">
					<div id="loginarea">
					<%
						if (result != null && result.equals("ok")) {
							out.println("<span>Your username and password were sent to you email. Please sign in <a href='SignIn.jsp'>here</a>.</span>");
						}
						else {
					%>
					<form action="ForgotPassword" onsubmit="return checkForm();" name="forgotpassform" method="post" class="loginform">   
						<%
							if (result != null && result.equals("bademail")) {
								out.println("<font id=\"error\">Please input correct email.</font>");
							}
							if (result != null && result.equals("nouser")) {
								out.println("<font id=\"error\">Sorry, there are no user with this email in our database.</font>");
							}
						%>
						<h1>Please input your email</h1>
						<div class="fieldarea">
							<div class="fieldarealeft"></div>
							<div class="fieldareamid">
								<input id="email" name="email" type="text"  class="textfields" 
									onclick="this.value=''" value="Email" tabindex="1" />
							</div>
							<div class="fieldarearight"></div>
						</div>
						<div class="signupbutton">
							<input type="submit" value="Send new password" />
						</div>
						<div class="newusersignup">
							<a href="SignUp.jsp" class="bluetext12"></a>Not a User ? <br>
						  	<a href="SignUp.jsp" class="pinktext14">Create your account</a>
						</div>
					</form>
					<%
						}
					%>
					</div>
					<div id="rightarea">
						Or Sign in with one-click
						<div class="socialicons">
							<ul class="socialicons1">
								<li><a href="#"><img src="images/facebookicon.gif" width="150" height="22" border="0" /></a></li>
								<li><a href="#"><img src="images/twittericon.gif" width="150" height="22" border="0" /></a></li>
								<li><a href="#"><img src="images/googleicon.gif" width="150" height="22" border="0" /></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div id="bottom_container"></div>
			<div id="footerarea">
				<div id="footer">
					<div id="footerleft">
						<a href="#" class="footerlink">Blog</a>&nbsp;&nbsp;|&nbsp;
						<a href="faq.jsp" class="footerlink">Faq</a>&nbsp;&nbsp;|&nbsp;
						<a href="mailto:feedback@Talkmi.com" class="footerlink">Contact Us</a>&nbsp;&nbsp;|&nbsp;
						<a href="#" class="footerlink">About Us</a>&nbsp;&nbsp;|&nbsp;
						<a href="privacy.jsp" class="footerlink">Privacy Policy</a>&nbsp;&nbsp;|&nbsp;
						<a href="tos.jsp" class="footerlink">Terms of Service</a>
					</div>
					<div id="footerright"><img src="images/footerlogo.gif" width="237" height="25" alt="" /></div>
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