<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tah.beans.TalkerBean" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	String result = request.getParameter("result");
%>
<%@ include file="header.jsp" %>
<style>
	body {
		background:url(images/inner_bg.gif) repeat-x top;
	}
</style>
<script type="text/javascript" charset="utf-8">
function validatePasswordForm() {
	var reason = "";
	
	reason += validatePassword(document.changePasswordForm.newpassword);
	if (reason != "") {
	    alert(reason);
	    return false;
	}
	
	document.changePasswordForm.submit();
}

function validateProfileForm() {
  var reason = "";
  var updateprofile = document.updateprofile;
	
  reason += validateUsername(updateprofile.username);
  reason += validateEmail(updateprofile.email);
  reason += validateEmpty(updateprofile.birthdate);
    
  if (reason != "") {
    alert("Some fields need correction:\n" + reason);
    return false;
  }
	
  document.updateprofile.submit();
}
function validateEmpty(fld) {
    var error = "";
 
    if (fld.value.length == 0) {
        fld.style.background = 'Yellow'; 
        error = "The required field has not been filled in.\n"
    } else {
        fld.style.background = 'White';
    }
    return error;  
}
function validateUsername(fld) {
    var error = "";
    var illegalChars = /\W/; // allow letters, numbers, and underscores
 
    if (fld.value == "") {
        fld.style.background = 'Yellow'; 
        error = "You didn't enter a username.\n";
    } else if ((fld.value.length < 1) || (fld.value.length > 15)) {
        fld.style.background = 'Yellow'; 
        error = "The username is the wrong length.\n";
    } else if (illegalChars.test(fld.value)) {
        fld.style.background = 'Yellow'; 
        error = "The username contains illegal characters.\n";
    } else {
        fld.style.background = 'White';
    }
    return error;
}
function validatePassword(fld) {
    var error = "";
    var illegalChars = /[\W_]/; // allow only letters and numbers 
 
    if (fld.value == "") {
        fld.style.background = 'Yellow';
        error = "You didn't enter a password.\n";
    } else if ((fld.value.length < 1) || (fld.value.length > 15)) {
        error = "The password is the wrong length. \n";
        fld.style.background = 'Yellow';
    } else if (illegalChars.test(fld.value)) {
        error = "The password contains illegal characters.\n";
        fld.style.background = 'Yellow';
    } else if (!((fld.value.search(/(a-z)+/)) && (fld.value.search(/(0-9)+/)))) {
        error = "The password must contain at least one numeral.\n";
        fld.style.background = 'Yellow';
    } else {
        fld.style.background = 'White';
    }
   return error;
}  
function trim(s)
{
  return s.replace(/^\s+|\s+$/, '');
}
function validateEmail(fld){
    var error="";
    var tfld = trim(fld.value);                        // value of field with whitespace trimmed off
    var emailFilter = /^[^@]+@[^@.]+\.[^@]*\w\w$/ ;
    var illegalChars= /[\(\)\<\>\,\;\:\\\"\[\]]/ ;
   
    if (fld.value == "") {
        fld.style.background = 'Yellow';
        error = "You didn't enter an email address.\n";
    } else if (!emailFilter.test(tfld)) {              //test email for illegal characters
        fld.style.background = 'Yellow';
        error = "Please enter a valid email address.\n";
    } else if (fld.value.match(illegalChars)) {
        fld.style.background = 'Yellow';
        error = "The email address contains illegal characters.\n";
    } else {
        fld.style.background = 'White';
    }
    return error;
}

function display(){
	//document.updateprofile.maritalstatus.selectedIndex = 1;
	//document.updateprofile.maritalstatus.v
	//var valueIndex = document.updateprofile.maritalstatus.selectedIndex;
	//var selectedValue = document.updateprofile.maritalstatus.options[valueIndex];
	//document.updateprofile.maritalstatus.value = selectedValue;
	//document.updateprofile.submit();
} 
</script>
</head>
<body onLoad="display()">
<div id="top_container">
	<div id="top">
		<div id="logo">
			<a href="index.jsp"><img src="images/spacer.gif" alt="Talk About Health" width="328" height="73" border="0" /></a>
		</div>
		<div id="topnav">
			<%@ include file="menu.jsp" %>
		</div>
	</div>
	<div id="innerbanner"></div>
	<div id="innermain">
		<div class="blacktext2" id="innerheading">Personal Info and Password</div>
		<div id="innerpersonalarea">
			<div id="signupleft">
			<form id="form" name="updateprofile" action="UpdateProfile" method="post" >	
				<div id="personalleft">
					<%
						if (result != null) {
					%>
					<div class="personalmain" style="text-align: center">
						<%
							if (result.equals("okprofile")) {
								out.println("<span class=\"blacktext14\">Changes are saved!</span>");
							}
							else if (result.equals("sameusername")) {
								out.println("<font id=\"error\">This username is already taken. Please enter a different one.</font>");
							}
							else if (result.equals("sameemail")) {
								out.println("<font id=\"error\">This email is already registered. Please enter a different one.</font>");
							}
							else if (result.equals("baddate")) {
								out.println("<font id=\"error\">Please input correct Birth Date</font>");
							}
						%>
					</div>
					<%
						}
					%>
					<div class="personalmain">
						<div class="personaltextarea"><span class="blacktext14">Username</span></div>
						<div class="personaltextfield">
							<input id="username" name="username" maxlength="15" size="25" 
								class="personalfields" type="text" value="<%=cb.getUserName()%>" />
						</div>
					</div>
					<div class="personalmain">
						<div class="personaltextarea"><span class="blacktext14">Email</span></div>
						<div class="personaltextfield">
						  	<input name="email" type="text" class="personalfields" id="email" value="<%=cb.getEmail()%>" />
						</div>
					</div>
					<!-- 
					<div class="personalmain">
						<div class="personaltextarea"><span class="blacktext14">First Name</span></div>
						<div class="personaltextfield">
						  	<input name="textfield2" type="text" class="personalfields" id="textfield2" onclick="this.value=''"  value="Murray" />
						</div>
					</div>
					<div class="personalmain">
						<div class="personaltextarea"><span class="blacktext14">Last Name</span></div>
						<div class="personaltextfield">
							<input name="textfield3" type="text" class="personalfields" id="textfield3" onclick="this.value=''"  value="Jones" />
						</div>
					</div>
					-->
					<div class="personalmain">
						<div class="personaltextarea"><span class="blacktext14">Birth Date</span></div>
						<div class="personaltextfield">
							<input name="birthdate" type="text" class="personalfields" 
								id="birthdate" value="<%= dateFormat.format(cb.getDob()) %>" />
						</div>
					</div>
					<div class="personalmain">
						<div class="personaltextarea"><span class="blacktext14">Gender</span></div>
						<div class="personaltextfield">
							<select class="personalfields" id="gender" name="gender">
							  <option value="M"
							  <%
							  	if (cb.getGender().equals("M")) {
							  		out.println(" selected='selected' ");
							  	}
							  %>
							  >Male</option>
							  <option value="F"
							  <%
							  	if (cb.getGender().equals("F")) {
							  		out.println(" selected='selected' ");
							  	}
							  %>
							  >Female</option>
							</select>
						</div>
					</div>
					<div class="personalmain">
						<div class="personaltextarea"><span class="blacktext14">City</span></div>
						<div class="personaltextfield">
							<input id="city" name="city" type="text" class="personalfields" value="<%=cb.getCity()%>">
						</div>
					</div>
					<div class="personalmain">
						<div class="personaltextarea"><span class="blacktext14">State</span></div>
						<div class="personaltextfield">
							<input id="state" name="state" type="text" class="personalfields" value="<%=cb.getState()%>">
						</div>
					</div>
					<!-- 
					<div class="personalmain">
						<div class="personaltextarea"><span class="blacktext14">Zip Code</span></div>
						<div class="personaltextfield">
							<input name="textfield3" type="text" class="personalfields" id="textfield3" onclick="this.value=''"  value="10014" />
						</div>
					</div>
					-->
					<div class="personalmain">
						<div class="personaltextarea"><span class="blacktext14">Country</span></div>
						<div class="personaltextfield" style="border:0;">
							<input id="country" name="country" type="text" class="personalfields" value="<%=cb.getCountry() %>">
						</div>
					</div>
					<div class="personalmain">
						<div class="personaltextarea"><span class="blacktext14">Martial Status</span></div>
						<div class="personaltextfield">
							<select id="maritalstatus" name="maritalstatus" class="personalfields">
								<%
									String[] maritalStatusArr = new String[] {
										"Single", "Married/partnered relationship", "Divorced", "Separated", "Widowed"
									};
									for (String maritalStatus : maritalStatusArr) {
										out.print("<option value='"+maritalStatus+"' ");
										if (maritalStatus.equals(cb.getMariStat())) {
											out.print("selected='selected'");
										}
										out.println(" >"+maritalStatus+"</option>");
									}
								%>
							</select>
						</div>
					</div>
					<div class="personalmain">
						<div class="personaltextarea"><span class="blacktext14">No. Of Children</span></div>
						<div class="personaltextfield">
							<input id="children" name="children" type="text" class="personalfields" value="<%= cb.getChildrenNum() %>" />
						</div>
					</div>
					<!-- 
					<div class="personalmain">
					<div class="personaltextarea"><span class="blacktext14">Ages of Children</span></div>
						<div class="personalcheck">
							<div class="personalcheck1"><input name="" type="checkbox" value="" />&nbsp; <span class="blacktext14">new born</span></div>
							<div class="personalcheck2"><input name="" type="checkbox" value="" />&nbsp; <span class="blacktext14">6-12 years old</span></div>
							<div class="personalcheck1"><input name="" type="checkbox" value="" />&nbsp; <span class="blacktext14">1-2 years old</span></div>
							<div class="personalcheck2"><input name="" type="checkbox" value="" />&nbsp; <span class="blacktext14">12-18 years old</span></div>
							<div class="personalcheck1"><input name="" type="checkbox" value="" />&nbsp; <span class="blacktext14">1-2 years old</span></div>
							<div class="personalcheck2"><input name="" type="checkbox" value="" />&nbsp; <span class="blacktext14">12-18 years old</span></div>
						</div>
					</div>
					<div class="personalmain">
						<div class="personaltextarea"><span class="blacktext14">Patient, Caregiver etc</span></div>
						<div class="personaltextfield">
							<select id="selection" name="selection" class="personalfields">
						 		<option selected value='0'>Patient/Caregiver/other/etc. </option>
						 		<option value="Patient">Patient</option>
							 	<option value="Former Patient">Former Patient</option>
							 	<option value="Caregiver">Caregiver</option>
							 	<option value="Family member">Family member</option> 
								<option value="Friend">Friend</option>
								<option value="Physician">Physician</option>
								<option value="Nurse">Nurse</option> 
							</select>
						</div>
					</div>
					<div class="personalmain">
						<div class="personaltextarea"><span class="blacktext14">Web Page</span></div>
						<div class="personaltextfield">
							<input name="textfield2" type="text" class="personalfields" id="textfield2" 
								onclick="this.value=''"  value="http://talkabouthealth.com" />
						</div>
					</div>
					<div class="personalmain">
						<div class="personaltextarea2"><span class="blacktext14">Conversation Topic<br />
							  <span class="footerlink">Key words of what you 
								like to talk about i.e. cooking, 
								sports etc.</span></span>
						</div>
						<div class="textareabig">
							<textarea name="textarea" id="textarea" cols="45" rows="5" class="textarea3"></textarea>
						</div>
					</div>
					<div class="personalmain">
						<div class="personaltextarea"><span class="blacktext14">Bio</span></div>
						<div class="textareabig"><textarea name="textarea" id="textarea" cols="45" rows="5" class="textarea3"></textarea></div>
					</div>
					-->
					<div class="personalmain">
						<div class="personaltextarea"></div>
						<div class="personaltextarea" >
							<a href="#" onclick="validateProfileForm()">
								<img src="images/savechanges_btn.gif" width="186" height="46" border="0" /></a>
						</div>
					</div>
					<div class="personalmain"></div>
				</div>
				<div id="personalright">
					<div id="personalrightimg">
						<img src="images/imageuploadpic.gif" />
					</div>
					<div class="bluetext12" id="personalrighttext"><a href="UploadImage.jsp" class="bluetext12">Change Photo</a></div>
				</div>
			</form>
			</div>
			
			<div id="signupright">
				<div class="personalinfosignrightbox">
					<div class="personalinfosignrighttop"></div>
					<div class="signrighmid">
						<span class="pinktext14"><strong>Picture</strong></span>
						<span class="textgreylight12"><br />
						A real picture does add a personal touch, 
						but its not advised if you do not want others 
						to recognize you.</span>
						<span class="pinktext14"><strong><br /><br />Username visibility</strong></span>
						<span class="textgreylight12"><br />
						Your Username will be visibe to other 
						members. If you want to remain anonymous, 
						we recommend you make your user name 
						different than your real name.</span>
						<span class="pinktext14"><strong><br />
						<br />Why provide more information?</strong></span>
						<span class="textgreylight12"><br />
						Filling in your profile information enables us 
						to find others similar to you for conversations. 
						The more information we have, the better we 
						can match you..<br /><br />As well the more information everyone in the 
						community shares, the more we help each 
						other. We find others just like us develop 
						friendships and know that we can get the 
						support we need.</span></span><br />
						<br />
					</div>
					<div class="signrighbot"></div>
				</div>
			</div>	
		</div>
	</div>
</div>
<div id="personalarea">
	<div id="personalinner">
		<div id="personalleft1">
		<a name="passwordform"></a>
		<form id="changePasswordForm" name="changePasswordForm" action="UpdateProfile?action=changepassword" method="post">
			<%
				if (result != null) {
			%>
			<div class="personalmain" style="text-align: center">
				<%
					if (result.equals("badpass")) {
						out.println("<font id=\"error\">Current password is wrong</font>");
					}
					else if (result.equals("differentpass")) {
						out.println("<font id=\"error\">New and Confirm passwords are different</font>");
					}
					else if (result.equals("okpassword")) {
						out.println("<span class=\"blacktext14\">Password is changed!</span>");
					}
				%>
			</div>
			<%
				}
			%>
			<div class="personalmain">
				<div class="personaltextarea"><span class="blacktext14">Current Password</span></div>
				<div class="personaltextfield">
					<input name="curpassword" id="curpassword" type="password" class="personalfields" value="" />
				</div>
			</div>
			<div class="personalmain">
				<div class="personaltextarea"><span class="blacktext14">New Password</span></div>
				<div class="personaltextfield">
					<input name="newpassword" id="newpassword" type="password" class="personalfields" value="" />
				</div>
			</div>
			<div class="personalmain">
				<div class="personaltextarea"><span class="blacktext14">Confirm Password</span></div>
				<div class="personaltextfield">
					<input name="confirmpassword" id="confirmpassword" type="password" class="personalfields" value="" />
				</div>
			</div>
			<div class="personalmain">
				<div class="personaltextarea"></div>
				<div class="personaltextarea" style="height:46px; padding-top:2px;">
					<a href="#passwordform" onclick="validatePasswordForm()">
						<img src="images/changepass_btn.gif" width="186" height="46" border="0" />
					</a>
				</div>
			</div>
		</form>
		</div>
	</div>
</div>
<%@ include file="footer.jsp" %>
<% }%>