<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.tah.beans.TopicBean" %>
<%@ page import="com.tah.beans.TalkerBean" %>
<%@ page import="com.tah.dao.TopicDAO" %>
<%@ page import="com.tah.web.webapp.LiveConversationsSingleton" %>
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
		int numberOfConversations = TopicDAO.getNumberOfTopics(cb.getId());
		
		Map<String, TopicBean> mapTalkmiTopics = new LinkedHashMap<String, TopicBean>(40);
		
		LiveConversationsSingleton lcs = LiveConversationsSingleton.getReference();
		if(lcs.getLiveConversationMap().size() < 20) {
			mapTalkmiTopics = TopicDAO.queryTopics();
		}
		
		session.setAttribute("mapTalkmiTopics", mapTalkmiTopics);
		
		String newTopic = request.getParameter("newtopic");
		if (newTopic == null) {
			newTopic = "Please enter your Conversation here ...";
		}
%>
<%@ include file="header.jsp" %>
	<style>
		body {
			background:url(images/innerpagebg.gif) repeat-x top;
		}
	</style>

	<script type="text/JavaScript" language="javascript" src="js/validation.js" charset="utf-8"></script>    
	<script type="text/JavaScript" language="javascript" src="js/talkerhome.js" charset="utf-8"></script>    
	<script type="text/javascript">
		var username = "<%out.print(cb.getUserName());%>";
		
		function checkForm()
		{
			//Check topic name field
			var newTopicField = document.postnewtopic.newtopic;
			if((newTopicField.value == "") 
					|| (newTopicField.value == "Please enter your Conversation here ...")) {
				alert("Please enter topic name.");
				newTopicField.focus();
				return false;
			}
			
			return true;
		}

		function submitenter(myfield,e) {
			var keycode;
			if (window.event) keycode = window.event.keyCode;
			else if (e) keycode = e.which;
			else return true;
			
			if (keycode == 13) {
				createTopic();
		    }
			return true;
		}
		
		function createTopic() {
			if (checkForm()) {
				postNewTopic(document.getElementById('newtopic'));
				document.getElementById('newtopic').value = "Please enter your Conversation here ...";
				return false;
			}
		}
		
		function open_chat(){
			window.open("http://talkabouthealth.com:5080/tah/chat.jsp?id="+username);
		}
		
		function openInvitationsWindow() {
			window.open("Invitations.jsp", "TalkAboutHealthInvitations", "width=600,height=350");
		}
	</script>
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
	</div>
</div>

<div id="innerbanner"></div>
<div id="bottom_container">
	<div id="middle_area_inner">
		<div id="arealeftinner">
			<h1>Start <span class="text24">a New Conversation </span></h1>
			<div id="curvetop"></div>
			<div id="lefttextareainner">
				<form id="postform" name="postnewtopic" action="javascript:postNewTopic(document.getElementById('newtopic'));" method="post" > 
					<textarea cols="" rows="" class="textarea" id="newtopic" name="newtopic" maxlength="40" 
						onKeyPress="submitenter(this,event)" TABINDEX=1
						onclick="if (this.value == 'Please enter your Conversation here ...') this.value=''" ><%= newTopic %></textarea>
				</form>
			</div>
			<div id="arealefttextareainner">
				<div id="examplequestion">
					<p><a href="#" class="blacktext">&nbsp;Example Question</a></p>
				</div>
				<div id="greybg">
					<a href="#" id="submitnewtopic" onClick="createTopic();" TABINDEX=2>
						<img src="images/startbutton.gif" width="206" height="46" border="0" />
					</a>
				</div>
			</div>
			<div id="curvebottominner"></div>
		</div>
		<div id="arearightinner">
			<div id="rightcurvetop"></div>
			<div id="rightmid">
				<div id="imgarea"><img src="<%= cb.getImagePath() %>" width="71" height="71" /></div>
				<div class="pinktext" id="imghead">
					<%= cb.getUserName() %><br />
			  		<span class="blacktext14">Advocate</span>
			  	</div>
			  	<div class="bluetext14" id="textrightbox">
			  		<span class="blacktext">Conversations:</span><strong> <%= numberOfConversations %></strong> <br />
				    <span class="blacktext">Thanks You's:</span><strong> 10</strong> <br />
				    <span class="blacktext">Friends :</span> <strong>30</strong> <br />
				    <span class="blacktext">Member Since: </span><strong>Jan 10 </strong>
				</div>
			</div>
			<div id="rightbot"></div>
		</div>
		<div id="innerarea_head">
			<h1>Your  <span class="blacktext2">Community Conversations</span></h1>
		</div>
		<div id="innerboxarea">
			<div id="innerleft">
				<div id="innerlefttop"></div>
				<div id="innermid">
					<%
						int count = 0;
						for (TopicBean tbTalkmiTopic : mapTalkmiTopics.values()) {
							count++;
					%>
					<div class="area">
						<div class="arealeft2">
							<img src="images/img1.gif" width="71" height="71" /><br />
				  			<span class="bluetext11">Murray Jones</span> 
				  			<span class="blacktext11">Advocate</span>
				  		</div>
						<div class="arearight">
						    <div class="areatop"></div>
						    <div class="areamid">
						    	<div class="areamidleft">
						    		<p>
						    			<span class="blacktext14">
						    				<%= tbTalkmiTopic.getTopic() %><br />
				        					<span class="bluetext12">3 people talking</span> | 
				        					<span class="red12">Started 4 mins ago</span><br />
				        				</span>
				        				<span class="blacktext">Community: Breast Cancer</span>
				        			</p>
				        		</div>
								<div class="join">
									<a href="javascript:open_chat('<%= tbTalkmiTopic.getId()%>')">
										<img border="0" src="images/join_conv.gif" width="178" height="27" />
									</a>
								</div>
							</div>
			  				<div class="areabot"></div>
			 	 		</div>
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
				</div>
				<div id="innerbot"></div>
			</div>
			<div id="innerright">
				<div id="searcharea">
					<input name="" type="text" class="search"  value="Search Conversation" 
						onclick="this.value=''" onblur="this.value='Search Conversation'"/>
				</div>
				<div id="searchright"><img src="images/searchbutton.gif" width="34" height="35" /></div>
				<div class="innerrightbox">
					<div class="innerrightop"></div>
					<div class="innerrighmid">
						<span class="redtext">Tell us about you</span><br />
						<span class="blacktext14">Tell us more about you to be 
							notified of the most relevant 
							conversations to you
						</span><br /><br />
						<span class="bluetext14">Treatments and Medications<br />
							Personal Info<br />
							Notification Settings
						</span>
					</div>
					<div class="innerrighbot"></div>
				</div>
				<div class="innerrightbox">
					<div class="innerrightop"></div>
					<div class="innerrighmid">
						<span class="redtext">Share TalkAboutHealth</span><br />
			  			<span class="blacktext14">Invite friends to join the community</span><br /><br />
						<a href="#" onclick="openInvitationsWindow()"><img border="0" src="images/invite.gif" /></a>
					</div>
					<div class="innerrighbot"></div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="footer.jsp" %>
<% }%>