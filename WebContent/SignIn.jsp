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
<%@ include file="header.jsp" %>

	<style>
		body {
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
			
			document.login.submit();
		}
	</script>
</head>

<body>
	<div id="top_container">
		<div id="top">
			<div id="logo">
				<a href="index.jsp"><img src="images/spacer.gif" alt="Talk About Health" width="328" height="73" border="0" /></a>
			</div>
		</div>
		<div id="innerbanner"></div>
		<div id="innermain">
			<div class="blacktext2" id="innerheading">Login to your Account</div>
			<div id="innermiddlearea">
				<div id="loginarea">
				<form id="loginform" action="Login" name="login" method="post" class="loginform">   
					<%
						String lf = request.getParameter("login");
						if (lf != null && lf.equals("f")) {
							out.println("<font id=\"error\">Login failed.</font>");
						} 
					%>
					<h1>Username or Email</h1>
					<div class="fieldarea">
						<div class="fieldarealeft"></div>
						<div class="fieldareamid">
							<input name="username" type="text"  class="textfields" id="username" 
								onclick="this.value=''" value="Username" tabindex="1" />
						</div>
						<div class="fieldarearight"></div>
					</div>
					<h1>Password</h1>
					<div class="fieldarea">
						<div class="fieldarealeft"></div>
						<div class="fieldareamid">
							<input name="password"  type="password" class="textfields" id="password" 
								onclick="this.value=''" value="123456" tabindex="2" /></div>
						<div class="fieldarearight"></div>
						<div class="checkboxarea">
							<input type="checkbox" id="rememberme" name="rememberme" class="checkboxclass" value="" />Remember me
						</div>
						<div class="forgotpassword"><a href="ForgotPassword.jsp" class="bluetext12">Forgot password?</a></div>
					</div>
					<div class="signupbutton">
						<a tabindex="3" onclick="checkForm()" href="#">
							<img src="images/signinbutton.gif" border="0" width="126" height="46" />
						</a>
					</div>
					<div class="newusersignup">
						<a href="SignUp.jsp" class="bluetext12"></a>Not a User ? <br>
					  	<a href="SignUp.jsp" class="pinktext14">Create your account</a>
					</div>
				</form>
				</div>
				
				<div id="rightarea">
					Or Sign in with one-click
					<div class="socialicons">
						<ul class="socialicons1">
							<li><a href="#"><img src="images/signinfacebook.gif" width="150" height="22" border="0" /></a></li>
							<li><a href="#"><img src="images/signintwitter.gif" width="150" height="22" border="0" /></a></li>
							<li><a href="#"><img src="images/signingoogle.gif" width="150" height="22" border="0" /></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="footer.jsp" %>
<% }%>