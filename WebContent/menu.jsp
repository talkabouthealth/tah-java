<%
	if (session.getAttribute("username") != null) {
%>
<div class="chromestyle" id="chromemenu">
	<ul>
		<li><a href="#" rel="dropmenu1">You</a></li>
		<li><a href="#" rel="dropmenu2">Community</a></li>
		<li><a href="#" rel="dropmenu3">Settings</a></li>
		<li><a href="#" rel="dropmenu4">Help</a></li>
		<li><a href="Logout" >Logout</a></li>	
	</ul>
</div>
<!--1st drop down menu -->                                                   
<div id="dropmenu1" class="dropmenudiv">
	<a href="#">Profile</a>
	<a href="#">Conversation History</a>
	<a href="#">Followers</a>
	<a href="#">Following</a>
	<a href="#">Activity Stream</a>
</div>
<!--2nd drop down menu -->                                                
<div id="dropmenu2" class="dropmenudiv" style="width: 180px;">
	<a href="#">Search People</a>
	<a href="#">Browse People</a>
	<a href="#">Search Conversations</a>
	<a href="#">Browse Conversations</a>
</div>
<!--3rd drop down menu -->                                                   
<div id="dropmenu3" class="dropmenudiv" style="width: 180px;">
	<a href="EditProfile.jsp">Edit Profile</a>
	<a href="Settings.jsp">Notification Settings</a>
	<a href="HealthDetails.jsp">Edit Health Information</a>
	<a href="ProfilePreferences.jsp">Profile Preferences</a>
</div>
<div id="dropmenu4" class="dropmenudiv" style="width: 180px;">
	<a href="faq.jsp">FAQ</a>
	<a href="#">Enabling IM</a>
	<a href="#">IM Commands</a>
	<a href="#">Tips and Etiquette</a>
	<a href="#">Blog</a>
	<a href="#">Feedback</a>
	<a href="#">Share TalkAboutHealth</a>
</div>
<script type="text/javascript">
	cssdropdown.startchrome("chromemenu")
</script>
<%
	}
%>