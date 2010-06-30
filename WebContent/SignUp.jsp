<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>						
<%@ include file="header.jsp" %>
	<style>
		body {
			background:url(images/inner_bg.gif) repeat-x top;
		}	
	</style>
	
	<script type="text/javascript" charset="utf-8">
		function openTwitter() {
			var popupWindow = window.open("oauth?action=getauth&type=twitter", 
				"TwitterLogin", "width=800,height=400,toolbar=no,location=no,menubar=no");
		}
		function openFacebook() {
			var popupWindow = window.open("oauth?action=getauth&type=facebook", 
				"FacebookLogin", "width=1000,height=450,toolbar=no,location=no,menubar=no");
		}
		
		/* 
			TODO: move this to separate .js ? Merge with "validation.js"?
		*/
		function validateFormOnSubmit(signup) {
			var reason = "";
		
		  reason += validateUsername(signup.username);
		  reason += validatePassword(signup.password);
		  reason += validateEmail(signup.email);
		  reason += validateMonth(signup.month);  
		  reason += validateDay(signup.day);  
		  reason += validateYear(signup.year);  
		 // reason += validateGender(signup.gender);  
		//  reason += validateAge(signup.age);  
			
		  if (reason != "") {
		    alert("Some fields need correction:\n" + reason);
		    return false;
		  }
		
		  // alert("All fields are filled correctly");
		  document.signup.submit();
		}
		function validateEmpty(fld) {
		    var error = "";
		 
		    if (fld.value.length == 0) {
		        fld.style.background = 'orange'; 
		        error = "The required field has not been filled in.\n"
		    } else {
		        fld.style.background = 'White';
		    }
		    return error;  
		}
		function validateUsername(fld) {
		    var error = "";
		
		    var varUN = new RegExp("^[\\w-_\\.]{1,25}$");
		    if (fld.value == "") {
		        fld.style.background = 'orange'; 
		        error = "You didn't enter a username.\n";
		    } else if ((fld.value.length < 3) || (fld.value.length > 25)) {
		        fld.style.background = 'orange'; 
		        error = "Username must be between 3 and 25 Characters.\n";
		    } else if (fld.value.search(varUN) == -1) {
		        fld.style.background = 'orange'; 
		        error = "The username contains illegal characters. Only letters, numbers, underscores, dashes, and periods are allowed.\n";
		    } else {
		        fld.style.background = 'White';
		    }
		    /*varUN = new RegExp("^[\\w-_\\.]{1,25}$");
		    if(fld.value.search(varUN) == -1){
		    	fld.style.background = 'orange'; 
		        error = "The username contains illegal characters. Only letters, numbers, underscore, dash, and period are allowed.\n";
		    } else if (illegalChars.test(fld.value)) {
		        fld.style.background = 'orange'; 
		        error = "The username contains illegal characters. Only letters, numbers, underscore, dash, and period are allowed.\n";
		    } var illegalChars = /\W/; // allow letters, numbers, and underscores
		    */
		    return error;
		}
		
		function validatePassword(fld) {
		    var error = "";
		    var varPW = new RegExp("^[\\w-_\\.?!@#$%^&*()+=]{1,25}$");
		    if (fld.value == "") {
		        fld.style.background = 'orange';
		        error = "You didn't enter a password.\n";
		    } else if ((fld.value.length < 4) || (fld.value.length > 25)) {
		        error = "Password must be between 3 and 25 Characters. \n";
		        fld.style.background = 'orange';
		    } else if (fld.value.search(varPW) == -1) {
		        fld.style.background = 'orange'; 
		        error = "The username contains illegal characters. Only letters, numbers, and the following characters are allowed: !@#$%^&*()_-+=?\.n";
		    } /*else if (illegalChars.test(fld.value)) {
		        error = "The password contains illegal characters. Only numbers, letters, underscore, dash, and period allowed.  \n";
		        fld.style.background = 'orange';
		    } /*else if (!((fld.value.search(/(a-z)+/)) && (fld.value.search(/(0-9)+/)))) {
		        error = "The password must contain at least one numeral.\n";
		        fld.style.background = 'orange';
		    } var illegalChars = /[\W_]/; // allow only letters and numbers 
			  */else {
		        fld.style.background = 'White';
		    }
		   return error;
		}  
		function trim(s)
		{
		  return s.replace(/^\s+|\s+$/, '');
		}
		function validateEmail(fld) {
		    var error="";
		    var tfld = trim(fld.value);                        // value of field with whitespace trimmed off
		    var emailFilter = /^[^@]+@[^@.]+\.[^@]*\w\w$/ ;
		    var illegalChars= /[\(\)\<\>\,\;\:\\\"\[\]]/ ;
		   
		    if (fld.value == "") {
		        fld.style.background = 'orange';
		        error = "You didn't enter an email address.\n";
		    } else if (!emailFilter.test(tfld)) {              //test email for illegal characters
		        fld.style.background = 'orange';
		        error = "Please enter a valid email address.\n";
		    } else if (fld.value.match(illegalChars)) {
		        fld.style.background = 'orange';
		        error = "The email address contains illegal characters.\n";
		    } else {
		        fld.style.background = 'White';
		    }
		    return error;
		}
		
		
		function validateMonth(fld) {
		    var error = "";
		 
		    if (fld.value == 0) {
		        fld.style.background = 'orange'; 
		        error = "Please select your birth month.\n";
		    } else {
		        fld.style.background = 'White';
		    }
		    return error;
		}
		
		function validateDay(fld) {
		    var error = "";
		 
		    if (fld.value == 0) {
		        fld.style.background = 'orange'; 
		        error = "Please select your birth date.\n";
		    } else {
		        fld.style.background = 'White';
		    }
		    return error;
		}
		
		
		function validateYear(fld) {
		    var error = "";
		 
		    if (fld.value == 0) {
		        fld.style.background = 'orange'; 
		        error = "Please select your birth year.\n";
		    } else {
		        fld.style.background = 'White';
		    }
		    return error;
		}
		/*function validateYearr(fld) {
		    var error = "";
		    var Year_filter = /^[1-2][0-9][0-9][0-9]/
		    
		   if ((fld.value == "") || (fld.value == "yyyy")) {
		        error = "Please enter your birth year.\n";
		        fld.style.background = 'orange';
		    } else if (!Year_filter.test(fld.value)) {
			    error = "Please enter a valid year.\n";
			    fld.style.background = 'White';
			}
		    return error;
		} 
		
		function validateGender(fld) {
		    var error = "";
		 
		    if (fld.value == 0) {
		        fld.style.background = 'orange'; 
		        error = "Please choose a your gender.\n";
		    } else {
		        fld.style.background = 'White';
		    }
		    return error;
		}
		*/
		
		function validateGender(btn) {
		    var error = "";
		    var cnt = -1;
		
		    for (var i=btn.length-1; i > -1; i--){
		     if (btn[i].checked) {cnt = i; i = -1;}
		 	 } if (cnt > -1) {error = "";
		 	 } else {error = "Please choose a your gender.\n";
		 	 }
		 	 return error;
		}
		               
		function validateAge(fld)  {
			var error = "";
			
			if (!document.getElementById("age").checked){
		        fld.style.background = 'orange'; 
		        error = "You must agree to the Terms of Service and Age requirements";
			} else {
		        fld.style.background = 'White';
			}
			return error;
		}	
	</script>
</head>

<body>
	<div id="top_container">
		<div id="top">
			<div id="logo">
				<a href="index.jsp"><img src="images/spacer.gif" alt="Talk About Health" width="328" height="73" border="0" /></a>
			</div>	
		</div>
		<div id="innerbanner"></div>
		<div id="bottom_container">
			<div class="blacktext2" id="innerheading">Sign Up Now</div>
			<div id="innermiddlearea">
				<div id="signupleft">
				<form id="form" name="signup" action="SignUp" method="post">
					<%
						String from = request.getParameter("from");
						if (from != null) {
							out.println("<h6 class=\"blacktext14\">");
							out.println("Welcome! You've gotten started by connecting your <i>"+from+"</i> account.");
							out.println("<br/>Your TalkAboutHealth account needs just a few more details. "+
									"Please take a moment to provide them below.");
							out.println("</h6>");
						}
						else {
					%>
					<h6 class="blacktext14" style="text-align:left;">Save time entering your information</h6>
					<div class="socialicons2">
						<ul>
							<li><a href="#" onclick="openFacebook()">
									<img src="images/joinusingfacebook.gif" width="150" height="22" border="0" />
							</a></li>
							<li><a href="#" onclick="openTwitter()">
									<img src="images/joinusingtwitter.gif" width="150" height="22" border="0" />
							</a></li>
							<!-- <li><img src="images/JoinwithGoogle.gif" width="150" height="22" />&nbsp;&nbsp;</li> -->
						</ul>
					</div>
					<%
						}
					%>
					<h5 id="error">
						<%
							String unerror = request.getParameter("username");
							String password = request.getParameter("password");
							String email = request.getParameter("email");
							String IM = request.getParameter("IMService");
							String month = request.getParameter("month");
							String day = request.getParameter("day");
							String year = request.getParameter("year");
							String gender = request.getParameter("gender");
							String zip = request.getParameter("zip");
							String city = request.getParameter("city");
							String state = request.getParameter("state");
							
							
							if (unerror != null && unerror.equals("u")) {
								out.println("<p>That user name is already taken.</p>  Please enter a different user name.");
								unerror = "";
							} else if (unerror == null) {
								unerror = "Username";
							}
							/*if (tnerror != null && tnerror.equals("c")) {
								out.println("That telephone number is already in use.  Please enter a new telephone number.");
								tnerror = "";
							} else if (tnerror != null) {
								areacode = tnerror.substring(1,4);
								threeDigits = tnerror.substring(4,7);
								fourDigits = tnerror.substring(7,11);
							}*/
							
							if (email != null && email.equals("em")) {
								out.println("<p>That email is already registered.</p>  Please enter a different email.");
								email = "";
							} else if (email != null && email.equals("notvalid")) {
								out.println("Please enter a valid email address.");
								email = "";
							} else if (email == null) {
								email = "Email";
							}
							if (month == null){
								month = "";
							}if (day == null){
								day = "";
							}if (year == null){
								year = "";
							}if (gender == null){
								gender = "";
							}if (zip == null){
								zip = "";
							}if (city == null){
								city = "";
							}if (state == null){
								state = "";
							}
							
							//Parameter for creating topic directly from index (non-auth) page
							String newTopic = request.getParameter("newtopic");
							if (newTopic == null) {
								newTopic = "";
							}
						%>
					</h5>
					<div id="signfieldsleft">
						<div class="signfields1">
							<input type="hidden" id="newtopic" name="newtopic" value="<%= newTopic %>" />
							<ul>
								<li><input name="username" type="text" class="textfields" id="username" 
									onclick="this.value=''"  value="<%= unerror %>" /></li>
								<li><input name="password" type="password" class="textfields" id="password" 
									onclick="this.value=''"  value="Password" /></li>
								<li><input name="email" type="text" class="textfields" id="email" 
									onclick="this.value=''"  value="<%= email %>" /></li>
								<li>
									<select id="IMService" name="IMService" class="textarea1">
								  		<option selected value='0'>Select an IM service</option>
										<option value="YahooIM">YahooIM</option>
										<option value="WindowLive">WindowLive</option>
										<option value="AOL">AOL</option>
										<option value="GoogleTalk">GoogleTalk</option>
										<option value="SkypeIM">SkypeIM</option>
									</select>
								</li>
								<li><input name="imusername" id="imusername"  type="text" class="textfields" 
								  	onclick="this.value=''" value="IM username" /></li>
							</ul>
						</div>
						<div class="signfields1">
							<h5 class="blacktext14" style="border:0; ">Date of Birth</h5>
							<ul>
								<li>
									<select size="1"  name="month" class="textarea2" id="month">
									  	<option selected value='0'>Month</option>
										<option value="01">Jan</option>
										<option value="02">Feb</option>
										<option value="03">Mar</option>
										<option value="04">Apr</option>
										<option value="05">May</option>
										<option value="06">Jun</option>
										<option value="07">Jul</option>
										<option value="08">Aug</option>
										<option value="09">Sep</option>
										<option value="10">Oct</option>
										<option value="11">Nov</option>
										<option value="12">Dec</option> 
									</select>
									<select size="1" name="day" class="textarea2" id="day">
								  		<option selected value='0'>Day</option>
										<option value="1">01</option>
										<option value="2">02</option>
										<option value="3">03</option>
										<option value="4">04</option>
										<option value="5">05</option>
										<option value="6">06</option>
										<option value="7">07</option>
										<option value="8">08</option>
										<option value="9">09</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
										<option value="24">24</option>
										<option value="25">25</option>
										<option value="26">26</option>
										<option value="27">27</option>
										<option value="28">28</option>
										<option value="29">29</option>
										<option value="30">30</option>
										<option value="31">31</option>
									</select>
									<select size="1"  name="year" class="textarea2" id="year">
								  		<option selected value='0'>Year</option>
										<option value="1991">1991</option>
										<option value="1990">1990</option>
										<option value="1989">1989</option>
										<option value="1988">1988</option>
										<option value="1987">1987</option>
										<option value="1986">1986</option>
										<option value="1985">1985</option>
										<option value="1984">1984</option>
										<option value="1983">1983</option>
										<option value="1982">1982</option>
										<option value="1981">1981</option>
										<option value="1980">1980</option>
										<option value="1979">1979</option>
										<option value="1978">1978</option>
										<option value="1977">1977</option>
										<option value="1976">1976</option>
										<option value="1975">1975</option>
										<option value="1974">1974</option>
										<option value="1973">1973</option>
										<option value="1972">1972</option>
										<option value="1971">1971</option>
										<option value="1970">1970</option>
										<option value="1969">1969</option>
										<option value="1968">1968</option>
										<option value="1967">1967</option>
										<option value="1966">1966</option>
										<option value="1965">1965</option>
										<option value="1964">1964</option>
										<option value="1963">1963</option>
										<option value="1962">1962</option>
										<option value="1961">1961</option>
										<option value="1960">1960</option>
										<option value="1959">1959</option>
										<option value="1958">1958</option>
										<option value="1957">1957</option>
										<option value="1956">1956</option>
										<option value="1955">1955</option>
										<option value="1954">1954</option>
										<option value="1953">1953</option>
										<option value="1952">1952</option>
										<option value="1951">1951</option>
										<option value="1950">1950</option>
										<option value="1949">1949</option>
										<option value="1948">1948</option>
										<option value="1947">1947</option>
										<option value="1946">1946</option>
										<option value="1945">1945</option>
										<option value="1944">1944</option>
										<option value="1943">1943</option>
										<option value="1942">1942</option>
										<option value="1941">1941</option>
										<option value="1940">1940</option>
										<option value="1939">1939</option>
										<option value="1938">1938</option>
										<option value="1937">1937</option>
										<option value="1936">1936</option>
										<option value="1935">1935</option>
										<option value="1934">1934</option>
										<option value="1933">1933</option>
										<option value="1932">1932</option>
										<option value="1931">1931</option>
										<option value="1930">1930</option>
										<option value="1929">1929</option>
										<option value="1928">1928</option>
										<option value="1927">1927</option>
										<option value="1926">1926</option>
										<option value="1925">1925</option>
										<option value="1924">1924</option>
										<option value="1923">1923</option>
										<option value="1922">1922</option>
										<option value="1921">1921</option>
										<option value="1920">1920</option>
										<option value="1919">1919</option>
										<option value="1918">1918</option>
										<option value="1917">1917</option>
										<option value="1916">1916</option>
										<option value="1915">1915</option>
										<option value="1914">1914</option>
										<option value="1913">1913</option>
										<option value="1912">1912</option>
										<option value="1911">1911</option>
										<option value="1910">1910</option>
										<option value="1909">1909</option>
										<option value="1908">1908</option>
										<option value="1907">1907</option>
										<option value="1906">1906</option>
										<option value="1905">1905</option>
										<option value="1904">1904</option>
										<option value="1903">1903</option>
										<option value="1902">1902</option>
										<option value="1901">1901</option>
										<option value="1900">1900</option>              	
									</select>
								</li>
								<li>
									<select name="" class="textarea1">
								  		<option value="Your Community" selected="selected">Your Community</option>
									</select>
								</li>
								<li>
									<select name="" class="textarea1">
								  		<option value="Patient/Caregiver/Family Member" selected="selected">Patient/Caregiver/Family Member</option>
									</select>
								</li>
							</ul>
						</div>
					</div>
					<div id="signfieldsright" class="blacktext">
						<div id="usertext">How other users will recognize you.<br /> You might not want to associate your real name.</div>
						<div id="selectim">TalkAboutHealth notifies you of conversations via IM.</div>
						<div id="datebirth">You must be over 18 years old to use this service.</div>
					</div>
					<div id="signdown">
						<h4>By signing up you agree to the <a href="tos.jsp" class="pinktext14">Terms Of Service</a> 
							and <a href="privacy.jsp" class="pinktext14">Privacy Policy</a><br />
	   						<strong>Note:</strong> Your information will remain safe and private.
						</h4>
						<div id="checkboxarea2">
							<input id="newsletter" name="newsletter" type="checkbox" checked /> 
							Sign me up for the TalkAboutHealth newsletter
						</div>
						<div class="signupbutton">
							<a href="#" onclick="validateFormOnSubmit(document.signup);">
								<img src="images/signupbutton.gif" border="0" width="126" height="46" />
							</a>
						</div>
						<div id="alreadyuser">
							<span class="blacktext14">Already have an account?&nbsp;</span> 
							<a href="SignIn.jsp" class="pinktext16">Sign in</a>
						</div>
					</div>
				</form>
				</div>
				
				<div id="signupright">
					<div class="signrightbox">
						<div class="signrightop"></div>
						<div class="signrighmid">
							<span class="bluetext14">Get the health information you need in 
							real-time, 24X7.<br /><br />
							Advice and support from experienced 
							peers<br /><br />
							Meet and develop friendships with 
							others just like you.<br /><br />Share you story with others who 
							understand.</span>
						</div>
						<div class="signrighbot"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
			
<%@ include file="footer.jsp" %>