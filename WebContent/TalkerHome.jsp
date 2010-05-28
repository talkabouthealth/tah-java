<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" > 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="author" content="Talkmi" /> 
	<meta name="keywords" content="support, health, talk" /> 
	<meta name="description" content="real-time support for your health issues" /> 
	<meta name="robots" content="all" /> 
	<title>Talkmi: Real-time support for your health issues</title>

	<script type="text/JavaScript" language="javascript" src="/tah-java/js/validation.js" charset="utf-8"></script>    
	<script type="text/JavaScript" language="javascript" src="/tah-java/js/talkerhome.js" charset="utf-8"></script>    
	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="beans.TopicBean" %>
<%@ page import="beans.TalkerBean" %>
<%@ page import="util.TalkmiDBUtil" %>
<%@ page import="webapp.LiveConversationsSingleton" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
	response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
	//System.out.println("------------------***TalkerHome Starting!!!");

String sUserName = (String)session.getAttribute("username");
if (sUserName == null) { 
	response.sendRedirect("index.jsp");
} else {
	TalkerBean cb = (TalkerBean)session.getAttribute("talker");
	
	Map<Integer, TopicBean> mapTalkmiTopics = new LinkedHashMap<Integer, TopicBean>(40);
	
	LiveConversationsSingleton lcs = LiveConversationsSingleton.getReference();
	if(lcs.getLiveConversationMap().size() < 20) {
		mapTalkmiTopics = TalkmiDBUtil.queryTopics();
	}
	
	session.setAttribute("mapTalkmiTopics", mapTalkmiTopics);
%>

<script type="text/javascript">
			var username = "<%out.print(cb.getUserName());%>";
			</script>

<SCRIPT TYPE="text/javascript" charset="utf-8">

function submitenter(myfield,e)
{
var keycode;
if (window.event) keycode = window.event.keyCode;
else if (e) keycode = e.which;
else return true;

if (keycode == 13)
   {
		if (checkForm()) {
			postNewTopic(document.getElementById('newtopic'));
			//myfield.form.submit();
			return false;
		}
   }
   return true;
}

function open_chat(){
	window.open("http://talkabouthealth.com:5080/tah/chat.jsp?id="+username);
	alert("username: "+username);
}
</SCRIPT>

</head>


<body>
<div class="top">
	<h5>
		<a href="faq.jsp"> FAQ |</a>
		<a href="mailto:feedback@Talkmi.com"> Feedback</a>
	</h5>

			<a id="logout" href="/tah-java/Logout">Logout</a>
			<a id="Edit" href="EditProfile.jsp">EditProfile</a>
			<a id="Settings" href="Settings.jsp">NotificationSettings</a>
			<div id="welcome">
		  	Welcome! <%
						out.print(cb.getUserName());
					 %>

			</div>
</div>
</div>	
			
<div class="centerback">

<div class="center" id="home">

	<div id="logo" class="logo">
		<a href="/tah-java/index.jsp"><h1><span class="talk">Talk</span><span class="mi">mi</span></h1></a>
	</div>

	<div class="topics">
		<div class="postbox">
			<div id="postbox">
		       	<h3><span>Start a conversation:</span></h3>  
				<form id="postform" name="postnewtopic" action="javascript:postNewTopic(document.getElementById('newtopic'));" method="post" > 
					 <textarea id="newtopic" type="text" name="newtopic" rows="2" maxlength="40" size="60" onKeyPress="submitenter(this,event)" TABINDEX=1></textarea>
		    		<input id="submitnewtopic" type="submit" value="Post it!" onClick="open_chat();" TABINDEX=2>
				</form>
			</div>
		</div>
		
		<div id="topicstable">
		
			<%
						int count = 0;
						for (TopicBean tbTalkmiTopic : mapTalkmiTopics.values()) {
					   				count++;

			%>
			<p>		
			<%
									if(count % 2 == 0){
										out.println("<div id=\"" + tbTalkmiTopic.getTopicID()+ "\" class=\"odd\">");
									} else {
										out.println("<div id=\"" + tbTalkmiTopic.getTopicID()+ "\" class=\"even\">");
									}
									//out.println("<a href=\"javascript:joinTalk('" + tbTalkmiTopic.getTopicID() + "')\"><div class=\"box\">" + tbTalkmiTopic.getTopic() + "</div></a>");
									out.println("<a href=\"javascript:open_chat('" + tbTalkmiTopic.getTopicID() + "')\"><div class=\"box\">" + tbTalkmiTopic.getTopic() + "</div></a>");
			%>
			</div>
			
			<%
							if (count == 1) {
								SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								session.setAttribute("latesttimestamp", SQL_DATE_FORMAT.format(tbTalkmiTopic.getDisplayTime()));
								//System.out.println("Latest date: " + SQL_DATE_FORMAT.format(tbTalkmiTopic.getDisplayTime()));
							} else if (count == 40) {
								SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								session.setAttribute("earliesttimestamp", SQL_DATE_FORMAT.format(tbTalkmiTopic.getDisplayTime()));
								//System.out.println("Earliest date: " + SQL_DATE_FORMAT.format(tbTalkmiTopic.getDisplayTime()));
							}
					}
			%>
			</p>
		</div>
		<a href="javascript:moreOldTopics()">
			<div class="paging" id="down">
				<span id="older">Show Five Older Topics</span>
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