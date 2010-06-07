<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Welcome</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/drop-down-menu.css" type="text/css" rel="stylesheet" />
<style>
body {
	margin: 0px;
	padding:0px;
	font-family:arial;
	font-size:11px;
	color:#000000;
	background:url(images/innerpagebg.gif) repeat-x top;
	}
</style>


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
<div id="top_container">
<div id="top">
<div id="logo"><a href="#"><img src="images/spacer.gif" alt="Talk About Health" width="328" height="73" border="0" /></a> </div>
<div id="topnav">
<!--[if IE]><div id="IEroot"><![endif]-->
<!--[if gte IE 7]><div id="IEroot7"><![endif]-->
<!--[if IE 6]><div id="IEroot6"><![endif]-->
<div class="menu" style="float:right; padding-right:50px;">
  <ul>
    <li class="no-image"><a href="#" title="Our Solutions">You<!--[if IE 7]><!--></a><!--<![endif]-->
      <!--[if lte IE 6]><table><tr><td><![endif]-->
      <ul>
        <li><a href="EditProfile.jsp" title="Law">Profile</a></li>
		<li><a href="#" title="Law">Conversation History</a></li>
		<li><a href="#" title="Law">Followers</a></li>
		<li><a href="#" title="Law">Following</a></li>
        <li><a href="#" title="Law">Activity Stream</a></li>
      </ul>
      <div style="clear:both;"></div>
      <!--[if lte IE 6]></td></tr></table></a><![endif]-->
    </li>
    
    <li class="no-image"><a href="#" title="Our Solutions">Community<!--[if IE 7]><!--></a><!--<![endif]-->
      <!--[if lte IE 6]><table><tr><td><![endif]-->
      <ul>
        <li><a href="#" title="Law">Search People</a></li>
		<li><a href="#" title="Law">Browse People</a></li>
		<li><a href="#" title="Law">Search Conversations</a></li>
		<li><a href="#" title="Law">Browse Conversations</a></li>
       
      </ul>
      <div style="clear:both;"></div>
      <!--[if lte IE 6]></td></tr></table></a><![endif]-->
    </li>
    <li class="no-image"><a href="Settings.jsp" title="About Us">Settings<!--[if IE 7]><!--></a><!--<![endif]-->
      <!--[if lte IE 6]><table><tr><td><![endif]-->
      <ul>
        <li><a href="#" title="Law">Edit Profile</a></li>
		<li><a href="#" title="Law">Notification Settings</a></li>
		<li><a href="#" title="Law">Edit Health Information</a></li>
		<li><a href="#" title="Law">Profile Preferences</a></li>
	
      </ul>
      <!--[if lte IE 6]></td></tr></table></a><![endif]-->
    </li>
    <li class="no-image"><a href="#"  title="Shop">Help<!--[if IE 7]><!--></a><!--<![endif]-->
      <!--[if lte IE 6]><table><tr><td><![endif]-->
      <ul>
        <li><a href="#" title="Law">FAQ</a></li>
		<li><a href="#" title="Law">Enabling IM</a></li>
		<li><a href="#" title="Law">IM Commands</a></li>
		<li><a href="#" title="Law">Tips and Etiquette</a></li>
		<li><a href="#" title="Law">Blog</a></li>
        <li><a href="#" title="Law">Feedback</a></li>
        <li><a href="#" title="Law">Share TalkAboutHealth</a></li>
        </ul>
      <!--[if lte IE 6]></td></tr></table></a><![endif]-->
    </li>
    <li class="no-image"><a href="/tah-java/Logout" title="Customer Services">Logout<!--[if IE 7]><!--></a><!--<![endif]-->
      <!--[if lte IE 6]><table><tr><td><![endif]-->
      <!--[if lte IE 6]></td></tr></table></a><![endif]-->
    </li>
  </ul>
</div>
<!--[if IE]></div><![endif]-->
</div>

</div>
</div>
<div id="innerbanner"></div>
<div id="bottom_container">
<div id="middle_area">
<div id="arealeft">
<h1>Start <span class="text24">a New Conversation </span></h1>
<div id="curvetop"></div>
<div id="lefttextarea"><textarea cols="" rows="" class="textarea" value="Please enter your Conversation here ..." onclick="this.value=''" onblur="this.value='Please enter your Conversation here ...'">Please enter your Conversation here ...</textarea>
</div>
<div id="arealefttextarea">
<div id="arealefttext"><p><a href="#" class="blacktext">&nbsp;Example Question</a></p>
</div>
<div id="arealeftgrey"><a href="javascript:postNewTopic(document.getElementById('newtopic'));"><img src="images/startbutton.gif" width="206" height="46" border="0" /></a></div>
</div>
<div id="curvebottom"></div>
</div>
<div id="arearight">
<div id="rightcurvetop"></div>
<div id="rightmid">
<div id="imgarea"><img src="images/img.jpg" width="71" height="71" /></div>
<div class="pinktext" id="imghead">Murray Jtones<br />
  <span class="blacktext14">Advocate</span></div>
  <div class="bluetext14" id="textrightbox"><span class="blacktext">Conversations:</span><strong> 101</strong> <br />
    <span class="blacktext">Thanks You's:</span><strong> 10</strong> <br />
    <span class="blacktext">Friends :</span> <strong>30</strong> <br />
    <span class="blacktext">Member Since: </span><strong>Jan 10 </strong></div>
</div>
<div id="rightbot"></div>
</div>
<div id="innerarea_head">
<h1>Your  <span class="blacktext2">Community Conversations</span></h1>
</div>
<div id="innerboxarea">
<div  id="innerleft">
<div id="innerlefttop"></div>
<div id="innermid">
<div class="area">
<div class="arealeft2"><img src="images/img1.gif" width="71" height="71" /><br />
  <span class="bluetext11">Murray Jones</span> <span class="currenttext">Advocate</span></div>
  <div class="arearight">
    <div class="areatop"></div>
    <div class="areamid">
    <div class="areamidleft"><p><span class="blacktext14">Received 2 Different Diagnoses Today<br />
        <span class="bluetext12">3 people talking</span> | <span class="red12">Started 4 mins ago</span><br />
</span><span class="blacktext">Community: Breast Cancer</span></p></div>
<div class="join"><img src="images/join_conv.gif" width="178" height="27" /></div>

    </div>
  <div class="areabot"></div>
  </div>
</div>
<div class="area">
<div class="arealeft2"><img src="images/img3.gif" width="71" height="71" /><br />
  <span class="bluetext11">Murray Jones</span> <span class="currenttext">Advocate</span></div>
  <div class="arearight">
    <div class="areatop"></div>
    <div class="areamid"><p><span class="blacktext14">Talk Herceptin, What are the side effects?<br />
        <span class="bluetext12">18 people talking</span> | <span class="red12">Started 12 mins ago</span><br />
</span><span class="blacktext">Community: Breast Cancer</span></p>
<div class="join"><img src="images/talking_button.gif" width="111" height="44" /></div>

    </div>
  <div class="areabot"></div>
  </div>
</div>
<div class="area">
<div class="arealeft2"><img src="images/img4.gif" width="71" height="71" /><br />
  <span class="bluetext11">Murray Jones</span> <span class="currenttext">Advocate</span></div>
  <div class="arearight">
    <div class="areatop"></div>
    <div class="areamid"><p><span class="blacktext14">Mammography Recommendation Draws Strong Response<br />
        <span class="bluetext12">2 people talking</span> | <span class="red12">Started 10 mins ago</span><br />
</span><span class="blacktext">Community: Breast Cancer</span></p>
<div class="join"><img src="images/join_conv.gif" width="178" height="27" /></div>

    </div>
  <div class="areabot"></div>
  </div>
</div>
<div class="area">
<div class="arealeft2"><img src="images/img5.gif" width="71" height="71" /><br />
  <span class="bluetext11">Murray Jones</span> <span class="currenttext">Advocate</span></div>
  <div class="arearight">
    <div class="areatop"></div>
    <div class="areamid"><p><span class="blacktext14">What are some things you WISH people had said or done?<br />
        <span class="bluetext12">10 people talking</span> | <span class="red12">Started 4 hrs ago</span><br />
</span><span class="blacktext">Community: Breast Cancer</span></p>
<div class="join"><img src="images/join_conv.gif" width="178" height="27" /></div>

    </div>
  <div class="areabot"></div>
  </div>
</div>
<div class="area">
<div class="arealeft2"><img src="images/img.jpg" width="71" height="71" /><br />
  <span class="bluetext11">Murray Jones</span> <span class="currenttext">Advocate</span></div>
  <div class="arearight">
    <div class="areatop"></div>
    <div class="areamid"><p><span class="blacktext14">Best Hospitals & Cancer Centers for Breast Cancer<br />
        <span class="bluetext12">3 people talking</span> | <span class="red12">Started 4 mins ago</span><br />
</span><span class="blacktext">Community: Breast Cancer </span></p>
<div class="join"><img src="images/join_conv.gif" width="178" height="27" /></div>

    </div>
  <div class="areabot"></div>
  </div>
</div>
</div>
<div id="innerbot"></div>
</div>
<div id="innerright">
<div id="searcharea"><input name="" type="text" class="search"  value="Search Conversation" onclick="this.value=''" onblur="this.value='Search Conversation'"/>
</div>
<div id="searchright"><img src="images/searchbutton.gif" width="34" height="35" /></div>
<div class="innerrightbox">
<div class="innerrightop"></div>
<div class="innerrighmid"><span class="redtext">Tell us about you</span><br />
  <span class="blacktext14">Tell us more about you to be 
notified of the most relevant 
conversations to you</span><br /><br />
<span class="bluetext14">Treatments and Medications<br />
Personal Info<br />
Notification Settings</span></div>
<div class="innerrighbot"></div>
</div>
<div class="innerrightbox">
<div class="innerrightop"></div>
<div class="innerrighmid"><span class="redtext">Share TalkAboutHealth</span><br />
  <span class="blacktext14">Invite friends to join the community</span><br /><br />
<img src="images/invite.gif" /></div>
<div class="innerrighbot"></div>
</div>
</div>
</div>

</div>
</div>
<div id="footerarea">
<div id="footer">
<div id="footerleft"><a href="#" class="footerlink">Blog</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" class="footerlink">Faq</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" class="footerlink">Contact Us</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" class="footerlink">About Us</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" class="footerlink">Privacy Policy</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" class="footerlink">Terms of Service</a></div>
<div id="footerright"><img src="images/footerlogo.gif" width="237" height="25" alt="" /></div>
</div>
</div>

</body>
</html>

<% }%>