<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tah.beans.TalkerBean" %>
<%
	TalkerBean talker = (TalkerBean)session.getAttribute("talker");
	if (talker == null) { 
		response.sendRedirect("SignIn.jsp");
	} else {
		
%>
<%@ include file="header.jsp" %>
	<style>
		body {
			background:url(images/inner_bg.gif) repeat-x top;
		}
		#userpicture {
			float: left;
			margin: 10px;
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
		<form id="uploadimageform" name="uploadimageform" enctype="multipart/form-data"
			action="UpdateProfile?action=uploadimage" method="POST" />
		<div id="innermain">
			<div class="blacktext2" id="innerheading">Upload Picture</div>
			<div id="innermiddlearea">
				<div id="notifactionbox">
					<div class="notifactionboxtop"></div>
					<div class="notifactionboxmid">
						<img id="userpicture" src="<%= talker.getImagePath() %>" width="100" height="100" />
						<h3>Actions:</h3>
						<input type="file" id="image" name="image" />
						<input id="submitaction" name="submitaction" type="submit" value="Upload" />
						<br/><br/>
						<input id="submitaction" name="submitaction" type="submit" value="Remove current image" />
					</div>
					<div class="notifactionboxbot"></div>
				</div>
			</div>
		</div>
		</form>
<%@ include file="footer.jsp" %>
<%
}
%>