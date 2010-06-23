<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="beans.TalkerBean" %>
<%@ page import="java.util.List" %>
<%@page import="java.util.Arrays"%>
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
	
	int freq = cb.getNfreq();
%>
<%@ include file="header.jsp" %>
<style>
	body {
		background:url(images/inner_bg.gif) repeat-x top;
	}
</style>
<script language="JavaScript">
	function onChecked(i, t)
	{
		var len = document.setNotifyFrequency.notifyFre.length;
	
		//var temp = i-1;
		document.setNotifyFrequency.notifyFre[i-1].checked = true;
		document.setNotifyFrequency.notifyTime[t-1].checked = true;
	}
</script> 
</head>

<body onLoad="onChecked(<%=cb.getNfreq() %>, <%=cb.getNtime()%>)">
	<div id="top_container">
		<div id="top">
			<div id="logo">
				<a href="TalkerHome.jsp"><img src="images/spacer.gif" alt="Talk About Health" width="328" height="73" border="0" /></a>
			</div>
			<div id="topnav">
				<%@ include file="menu.jsp" %>
			</div>
		</div>
	</div>
	<div id="innerbanner"></div>
	<div id="innermain">
	<form id="NofifyFrequency" name="setNotifyFrequency" action="/tah-java/SetNotification" method="post"  >
		<div class="blacktext2" id="innerheading">Notification Settings</div>
		<div id="innermiddlearea">
			<div id="notifactionbox">
				<div class="notifactionboxtop"></div>
				<div class="notifactionboxmid">
					<%
						if (request.getParameter("setnotification") != null) {
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
							<h1>How often do you want to be notified?</h1>
							<ul>
								<li><input name="notifyFre" type="radio" value="1" class="radiobutton" /> 
								  <span class="blacktext" style="vertical-align:top;">more than 10 times per day</span></li>
								<li><input name="notifyFre" type="radio" value="2" class="radiobutton" /> 
								  <span class="blacktext" style="vertical-align:top;">5 to 10 times per day
									</span></li>
							  	<li><input name="notifyFre" type="radio" value="3" class="radiobutton" /> 
							  		<span class="blacktext" style="vertical-align:top;">2 to 5 times per day</span></li>
								<li><input name="notifyFre" type="radio" value="4" class="radiobutton" /> 
							  		<span class="blacktext" style="vertical-align:top;">1 time per day</span></li>
							</ul>
						</div>
						<div class="notiblueareabot"></div>
					</div>
					<div class="notibluearea">
						<div class="notiblueareatop"></div>
						<div class="notiblueareamid">
							<h1>When do you want to be notified?</h1>
							<ul>
								<li><input name="notifyTime" type="radio" value="1" class="radiobutton" /> 
								  <span class="blacktext" style="vertical-align:top;">whenever I am online</span></li>
								  <li><input name="notifyTime" type="radio" value="2" class="radiobutton" /> 
								  <span class="blacktext" style="vertical-align:top;">weekdays (9AM - 5PM)
								</span></li>
								  <li><input name="notifyTime" type="radio" value="3" class="radiobutton" /> 
								  <span class="blacktext" style="vertical-align:top;">weeknights (5PM - 10PM)</span></li>
								<li><input name="notifyTime" type="radio" value="4" class="radiobutton" /> 
								  <span class="blacktext" style="vertical-align:top;">weekends (Sat and Sun)</span></li>
							</ul>
						</div>
						<div class="notiblueareabot"></div>
					</div>
					<div class="notibluearea">
						<div class="notiblueareatop"></div>
						<div class="notiblueareamid">
							<h1>What types of conversations interest you the most?</h1>
							<ul>								
								<li>
								<%
									List<String> cTypeValue = Arrays.asList(cb.getCtype());
									String[] cTypeArr = new String[] {"Informational", 
										"Advice and opinions", "Meet new people", "Emotional support"};
									for (String cType : cTypeArr) {
										out.print("<li><input name='cType' type='checkbox' value='"+cType+"' ");
										if (cTypeValue.contains(cType)) {
											out.print("checked='checked' ");
										}
										out.println("/>&nbsp;<span class='blacktext' >"+cType+"</span></li>");
									}
								%>
							</ul>
						</div>
						<div class="notiblueareabot"></div>
					</div>
<!-- <div id="savebutton"><a href="/tah-java/SetNotification"><img src="images/save.gif" width="186" height="46" /></a></div> -->
					<input id="submit" name="submit1" type="image" src="images/save.gif" value="save changes"> 
				</div>
				<div class="notifactionboxbot"></div>
			</div>
		</div>
	</form>
	</div>
	
	<div id="midnoticontainer">
		<div id="midnoticontainermain">
		<a name="accountform"></a>
		<form id="primaryIM" name="setPrimaryIM" action="/tah-java/SetAccount" method="post">
			<div id="boxarea_head2" class="blacktext2">Accounts where we notify you</div>
			<div id="notifactionbox2">
				<div class="notifactionboxtop"></div>
				<div class="notifactionboxmid">
					<%
						if (request.getParameter("setaccount") != null) {
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
						<div class="signfields2">
							<h1>Instant Messenger</h1>
							<ul>
								<li><select name="IMService" class="textarea1">
								  <%
								  	String[] imserviceArr = new String[] {"Select an IM service",
										  "YahooIM", "WindowLive", "AOL", "GoogleTalk", "SkypeIM"};
								  	String imServiceValue = cb.getIM();
								  	for (String imservice : imserviceArr) {
								  		out.print("<option value='"+imservice+"' ");
								  		if (imservice.equalsIgnoreCase(imServiceValue)) {
								  			out.print("selected='selected' ");
								  		}
								  		out.print(">"+imservice+"</option>");
								  	}
								  %>
								</select></li>
								  <li><input name="textfield" type="text" class="textfields" 
								  	id="textfield" onclick="this.value=''" onblur="this.value='murrayjones'" value="murrayjones" /></li>
							</ul>
						</div>
						<div class="notiblueareabot"></div>
					</div>
					<div class="notibluearea">
						<div class="notiblueareatop"></div>
						<div class="signfields2">
							<h1>Email</h1>
							<ul>
							  <li><input name="email" type="text" class="textfields" id="textfield" value="<%=cb.getEmail() %>"/></li>
							</ul>
						</div>
						<div class="notiblueareabot"></div>
					</div>
<!-- <div id="savebutton"><img src="images/save.gif" width="186" height="46" /></div> -->
					<input id="submit" name="submit1" type="image" src="images/save.gif" value="save changes"> 
				</div>
				<div class="notifactionboxbot"></div>
			</div>
		</form>
		</div>
	</div>

<%@ include file="footer.jsp" %>
<% } %>