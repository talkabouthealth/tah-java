<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.EnumSet" %>
<%@ page import="beans.TalkerBean" %>
<%@ page import="beans.TalkerBean.ProfilePreference" %>
<%
	TalkerBean talker = (TalkerBean)session.getAttribute("talker");
	if (talker == null) { 
		response.sendRedirect("SignIn.jsp");
	} else {
		EnumSet<ProfilePreference> profilePreferences = talker.getProfilePreferences();
%>
<%@ include file="header.jsp" %>
	<link href="css/drop-down-menu.css" type="text/css" rel="stylesheet" />
	<style>
		body {
			margin: 0px;
			padding:0px;
			font-family:arial;
			font-size:11px;
			color:#000000;
			background:url(images/inner_bg.gif) repeat-x top;
		}
		.signfields2 ul {
			padding-top: 0px;
		}
		.signfields2 ul li {
			font-size: 14px;
			background: none;
			line-height: 28px;
			padding: 0px;
			width:600px;
		}	
	</style>
</head>
<body>

<div id="top_container">
	<div id="top">
		<div id="logo">
			<a href="index.jsp"><img src="images/spacer.gif" alt="Talk About Health" width="328" height="73" border="0" /></a>
		</div>
		<div id="innerbanner"></div>
		<form id="profilepreferencesform" name="profilepreferencesform" action="ProfilePreferences" method="POST" />
		<div id="innermain">
			<div class="blacktext2" id="innerheading">Profile Preferences</div>
			<div id="innermiddlearea">
				<div id="notifactionbox">
					<div class="notifactionboxtop"></div>
					<div class="notifactionboxmid">
						<div class="notibluearea">
							<div class="notiblueareatop"></div>
							<div class="signfields2">							
								<ul>
									<%
										for (ProfilePreference preference : ProfilePreference.values()) {
											out.print("<li>");
											out.print("<input type='checkbox' name='"+preference+"' ");
											if (profilePreferences.contains(preference)) {
												out.print("checked='checked'");
											}
											out.print(" /> "+preference.getDescription());
											out.println("</li>");
										}
									%>
								</ul>
							</div>
							<div class="notiblueareabot"></div>
						</div>
						<div id="savebutton" onclick="document.profilepreferencesform.submit();">
							<img src="images/save.gif" width="186" height="46" />
						</div>
					</div>
					<div class="notifactionboxbot"></div>
				</div>
				<div id="tipsbox">
					<b>Tips</b><br/><br/>
					<b>Why display information in your profile?</b><br/>
					Displaying your health details enables others
					just like you who are dealing with or have dealt with similar health issues
					to find you to request advice or just talk.
				</div>
			</div>
		</div>
		</form>
<%@ include file="footer.jsp" %>
<%
}
%>