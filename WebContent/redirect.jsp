<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="author" content="Talkmi" /> 
	<meta name="keywords" content="support, health, talk" /> 
	<meta name="description" content="real-time support for your health issues" /> 
	<meta name="robots" content="all" /> 
	<title>TalkAboutHealth</title>
	
	<script type="text/javascript">
		function redirectParent() {
			var url = '<%= request.getParameter("url") %>';
			window.opener.document.location = url;
			window.close();
		}
	</script>
</head>
<body onload="redirectParent()">

<div id="container">
	Redirection. Please wait...
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