<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.EnumSet" %>
<%@ page import="com.tah.beans.TalkerBean" %>
<%@ page import="com.tah.beans.TalkerBean.ProfilePreference" %>
<%
	TalkerBean talker = (TalkerBean)session.getAttribute("talker");
	if (talker == null) { 
		response.sendRedirect("SignIn.jsp");
	} else {
		EnumSet<ProfilePreference> profilePreferences = talker.getProfilePreferences();
%>
<%@ include file="header.jsp" %>
	<style>
		body {
			background:url(images/inner_bg.gif) repeat-x top;
		}
		.notiblueareamid ul li {
			font-size: 14px;
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
		<div id="topnav">
			<%@ include file="menu.jsp" %>
		</div>
		<div id="innerbanner"></div>
		<form id="profilepreferencesform" name="profilepreferencesform" action="ProfilePreferences" method="POST" />
		<div id="innermain">
			<div class="blacktext2" id="innerheading">Profile Preferences</div>
			<div id="innermiddlearea">
				<div id="notifactionbox">
					<div class="notifactionboxtop"></div>
					<div class="notifactionboxmid">
						<%
							if (request.getParameter("result") != null) {
						%>
						<div class="notibluearea">
							<div class="notiblueareatop"></div>
							<div class="signfields2">
								<h4>Changes are saved!</h4>
							</div>
							<div class="notiblueareabot"></div>
						</div>
						<%
							}
						%>
						<div class="notibluearea">
							<div class="notiblueareatop"></div>
							<div class="notiblueareamid">							
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
						<a id="savebutton" href="#" onclick="document.profilepreferencesform.submit();">
							<img src="images/save.gif" width="186" height="46" border="0"/>
						</a>
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