<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="util.TalkmiDBUtil" %>
<% 
	response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
	
	String sUserName = (String)session.getAttribute("username");
	if (sUserName != null) { 
		response.sendRedirect("TalkerHome.jsp");
	} else {
		int numberOfMembers = TalkmiDBUtil.getNumberOfMembers();
%>
<%@ include file="header.jsp" %>
	<style type="text/css">
		body {
			background:url(images/pagebg.gif) repeat-x top;
		}
	</style>

	<!-- scripts -->
	<script language="javascript">
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
				signup();
		    }
			return true;
		}
		
		function signup() {
			if (checkForm()) {
				var newTopic = document.getElementById('newtopic').value;				
				document.location = "SignUp.jsp?newtopic="+encodeURIComponent(newTopic);
			}
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
			<div id="curvetophome"></div>
			<div id="arealefttextarea">
				<div id="comingsoonhead" class="bluetext30">&nbsp;Coming Soon</div>
				<div id="dashedborder" style="width:638px;"></div>
				<div id="arealeftgrey" class="blacktext14">
					&nbsp;Sign up to receive the lates on our beta program and launch.<br />
			  		<span class="pinktext14">&nbsp;(Your email won't be used for anything else.)</span>
			  	</div>
			  	<div id="homefields">
					<ul>
						<li><input name="textfield" type="text" class="textfields" 
							id="textfield" onclick="this.value=''"  value="Your Email Address..." />
						</li>
					</ul>    
			  	</div>
			  	<div id="arearightbutton"><a href="#"><img src="images/signupbutton.gif" width="126" height="46" border="0" /></a></div>
			  	<div id="arealefttext">Your email will remain safe and private</div>
			</div>
			<div id="curvebottom"></div>
			
			<!-- 
			<h1>Ask a question <span class="text24">or start a new conversation:</span></h1>
			<div id="curvetop"></div>
			<div id="lefttextarea">
				<form id="postform" name="postnewtopic" action="javascript:signup();" method="post" > 
					<textarea cols="" rows="" class="textarea" id="newtopic" name="newtopic" maxlength="40" 
						onKeyPress="submitenter(this,event)" TABINDEX=1
						onclick="if (this.value == 'Please enter your Conversation here ...') this.value=''"
						>Please enter your Conversation here ...</textarea>
				</form>
			</div>
			<div id="arealefttextarea">
				<div id="arealefttext">
					<p><a href="#" class="blacktext">&nbsp;Example Question</a></p>
				</div>
				<div id="arealeftgrey">
					<a href="#" onclick="signup()"><img src="images/startbutton.gif" width="206" height="46" border="0" /></a>
				</div>
			</div>
			<div id="curvebottom"></div>
			 -->
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
</div>
<%@ include file="footer.jsp" %>
<% }%>