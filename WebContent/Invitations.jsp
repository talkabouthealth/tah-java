<%@ page import="beans.TalkerBean" %>
<%
	TalkerBean talker = (TalkerBean)session.getAttribute("talker");
	int invitations = talker.getInvitations();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="author" content="Talkmi" /> 
	<meta name="keywords" content="support, health, talk" /> 
	<meta name="description" content="real-time support for your health issues" /> 
	<meta name="robots" content="all" /> 
	<title>TalkAboutHealth Invitations</title>
	
	<style type="text/css">
		body {
			margin: 0px;
			padding:0px;
			font-family:arial;
			font-size:14px;
			color:#000000;
		}
		#container {
			margin: 0px auto;
			padding: 10px;
			width: 500px;
		}
		#invitesform {
			background-color: #E2EEF3;
			text-align: left;
			padding: 10px;
		}
		textarea {
			border:1px solid black;
			height:5em;
			padding:5px;
			width:400px;
		}
		.error {color: red}
		.msg {font-weight:bold}
	</style>
</head>
<body>

<div id="container">
<%
	if (invitations <= 0) {
		out.println("<span class='label'>Sorry, you don't have invites!</span>");
	}
	else {
%>
You have <%= invitations %> TalkAboutHealth invitations left.<br/><br/>
<%
		String result = request.getParameter("result");
		if (result != null && result.equals("bademails")) {
			out.println("<span class='error'>Please input correct emails</span>");
		}
		else if (result != null && result.equals("noinvites")) {
			out.println("<span class='error'>Sorry, you don't have enough invites</span>");
		}
		else if (result != null && result.equals("ok")) {
			out.println("<span class='msg'>Invitations successfully sent!</span>");
		}
%>
<form id="invitesform" action="Invitations" method="post">
	<span class="label">To invite people, type their email addresses here (separated by commas):</span><br/>
	<textarea id="emails" name="emails"></textarea><br/><br/>
	
	<span class="label">Add a note to the invitation:</span><br/>
	<textarea id="note" name="note"></textarea><br/><br/>
	
	<input type="submit" value="Send invites" />
<form>
<%
	}
%>
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