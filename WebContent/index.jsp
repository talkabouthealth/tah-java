<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="util.TalkmiDBUtil" %>
<% 
	response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
	
	int numberOfMembers = TalkmiDBUtil.getNumberOfMembers();
	
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
	
	<style type="text/css">
		body {
			margin: 0px;
			padding:0px;
			font-family:arial;
			font-size:11px;
			color:#000000;
			background:url(images/pagebg.gif) repeat-x top;
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
</head>

<body>
<div id="top_container">
	<div id="top">
		<div id="logo">
			<a href="index.jsp"><img src="images/spacer.gif" alt="Talk About Health" width="328" height="73" border="0" /></a>
		</div>
		<div id="topright">
			<span class="blacktext">Sign in with your</span> <br />
  			<div class="icon">
  				<a href="#"><img src="images/twitter.gif" alt="Twitter" width="27" height="27" border="0" /></a>
  			</div>
  			<div class="icon">
  				<a href="#"><img src="images/facebook.gif" alt="facebook" width="27" height="27" border="0"/></a>
  			</div> 
		</div>
		<div id="topmid">
			<a href="SignUp.jsp" class="bluetext">Join Now</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="SignIn.jsp" class="redtext">Sign In</a>
		</div>
	</div>
	<div id="text_heading"></div>
</div>

<div id="banner"></div>

<div id="bottom_container">
	<div id="middle_area">
		<div id="arealeft">
			<h1>Ask a question <span class="text24">or start a new conversation:</span></h1>
			<div id="curvetop"></div>
			<div id="lefttextarea">
				<textarea cols="" rows="" class="textarea" value="Please enter your Conversation here ..." onclick="this.value=''" 
					onblur="this.value='Please enter your Conversation here ...'">Please enter your Conversation here ...</textarea>
			</div>
			<div id="arealefttextarea">
				<div id="arealefttext">
					<p><a href="#" class="blacktext">&nbsp;Example Question</a></p>
				</div>
				<div id="arealeftgrey">
					<a href="#"><img src="images/startbutton.gif" width="206" height="46" border="0" /></a>
				</div>
			</div>
			<div id="curvebottom"></div>
		</div>
		<div id="arearight">
			<h1>Current Support Communities</h1>
			<ul>
				<li>
					<span class="blacktext"><strong>Breast Cancer</strong></span><br />
  					<span class="greytext">3 active conversations |</span> 
  					<span class="bluetext2"><%= numberOfMembers %> members</span>
  				</li>
			</ul>
			<p>
				<span class="blacktext"><strong>Multiple Sclerosis</strong></span><br />
  				<span class="bluetext2">Coming Soon!</span>
  			</p>
		</div>
		<div id="boxarea_head">
			<h1>
				<span class="blacktext2">How does</span> 
				<span class="greentext">Talk</span><span class="blueext30">About</span><span class="pinktext30">Health</span>
				<span class="blacktext2"> work?</span>
			</h1>
		</div>		
		<div id="boxarea">
			<div class="box">
				<div class="box_top"><img src="images/1img.jpg" width="294" height="160" alt="" /></div>
				<div class="boxmid">
					Share your Question<br /> 
  					<p>Send a question or comment to<br /> <span class="bluetext14">Talkmi.</span><br /><br /></p>
  				</div>
				<div class="box_bot"></div>
			</div>
			<div class="box">
				<div class="box_top"><img src="images/2img.jpg" width="294" height="160" alt="" /></div>
					<div class="boxmid">
						<span class="greentext16">Talk</span><span class="blueext16">About</span><span class="pinktext16">Health</span>
						finds the right Group<br /> 
  						<p>Talkmi finds the perfect group for your needs.</p>
  					</div>
					<div class="box_bot"></div>
				</div>
			<div id="box3">
				<div class="box_top"><img src="images/3img.jpg" width="294" height="160" alt="" /></div>
				<div class="boxmid">
					Arrange Group Conversation<br /> 
  					<p>Within minutes have a live confidential group conversation.<br /><br /></p>
  				</div>
				<div class="box_bot"></div>
			</div>
		</div>
		<div id="bottomboxarea">
			<div class="bottomboxinnerarea">
				<div id="boxarea1">
					<h3><span class="red">Meet</span> others like you</h3>
					<p>Connect with others just like you, 
					develop friendships and 
					expand your support network</p>
				</div>
				<div class="colantext">
					<span class="blacktext14">Sometimes I need to talk to someone in the middle of the night</span>
					&nbsp; <img src="images/colanbottom.gif"  width="19" height="15" /> <br />  
  					<span class="bluetext14">John H.</span>
  				</div>
			</div>
			<div class="bottomboxinnerarea">
				<div id="boxarea2">
					<h3><span class="green">Learn </span> from peers</h3>
					<p>Be more prepared after 
					talking to experienced peers. 
					Request their advice and 
					opinions.</p>
				</div>
				<div class="colantext">
					<span class="blacktext14">
						I would like other people's opinions on best hospitals and physicians for my health issue&nbsp; 
					</span>
					<img src="images/colanbottom.gif" /> <br />
					<span class="bluetext14">Susan P.</span>
				</div>
			</div>
			<div class="bottomboxinnerarea2">
				<div id="boxarea3">
					<h3><span class="purple">Share  </span> your experiences</h3>
					<p>Feel better after sharing your 
					experiences with peers who 
					understand 24X7. Others will 
					learn from your experiences too.</p>
				</div>
			<div class="colantext">
				<span class="blacktext14">I want to meet others who understand what i am going through.</span>
				<img src="images/colanbottom.gif" /> <br />
			  	<span class="bluetext14">Delores B</span>
			</div>
		</div>
	</div>
</div>
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