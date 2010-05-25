<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" > 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="author" content="Talkmi" /> 
	<meta name="keywords" content="support, health, talk" /> 
	<meta name="description" content="real-time support for your health issues" /> 
	<meta name="robots" content="all" /> 
	<title>Talkmi : Real-time support for your health issues</title>

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
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

String sUserName = (String)session.getAttribute("username");
if (sUserName != null) { 
	response.sendRedirect("TalkerHome.jsp");
} else {%>

	<style type="text/css" title="currentStyle" media="screen">
	 @import "/tah-java/css/index.css"; 
 	</style>

</head>

<body>
<div class="top">
	<h5>
		<a href="index.jsp"> Home |</a>
		<a href="faq.jsp"> FAQ |</a>
		<a href="mailto:feedback@Talkmi.com"> Feedback</a>
	</h5>
</div>	

<div class="center">
	<div id="logo" class="logo">
		<a href="/tah-java/index.jsp"><h1><span class="talk">Talk</span><span class="mi">mi</span></h1></a>
	</div>
	<div class="signin">
		<form id="loginform" action="/tah-java/Login" name="login" method="post" class="loginform">          	
				<h2>Sign in here
					
					<%
					String lf = request.getParameter("login");
					if (lf != null && lf.equals("f")) {
						out.println("<font id=\"error\">Login failed.</font>");
						out.println("test");
					} 
					%>
			
				</h2>
			
		    	<label><span>Username</span>
		    		<input 
		    			id="username" class="input-box" name="username" type="text" maxlength="25" size="25" value="kangaroo"  TABINDEX=1/>
			    	</input>
			    </label>
			   	<label><span>Password</span>
		    		<input id="password" class="input-box" name="password" type="password" maxlength="25" size="25" value="123456" TABINDEX=2>
			    	</input>
			    </label>
			    <br/>
			    <input type="checkbox" id="rememberme" name="rememberme"/>Remember me		
				<input id="login" type="submit" value="Continue" onClick="return checkForm()" TABINDEX=3></input>
		</form>	
		
	</div>
	<br>
	<br>
		<div class="signup">
			<a id="su" href="/tah-java/SignUp.jsp" TABINDEX=3>
			<div id="signup">
				<h3><span>Sign Up!</span></h3>
			</div>
			</a>	
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