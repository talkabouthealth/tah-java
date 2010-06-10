<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="util.TalkmiDBUtil" %>
<%@ page import="beans.TalkerBean" %>
<%@ page import="beans.TalkerDiseaseBean" %>
<%@page import="beans.HealthItemBean" %>
<%!
	//prints tree of items for given parent, checks given items
	public void printTree(HealthItemBean healthItem, Set<Integer> checkedHealthItems, JspWriter out) throws Exception {
		for (HealthItemBean item : healthItem.getChildren()) {
			if (item.getChildren() != null && item.getChildren().size() > 0) {
				out.println("<li><b>"+item.getName()+"</b></li>");
				printTree(item, checkedHealthItems, out);
			}
			else {
				out.print("<li>");
				out.print("<input name='healthitem"+item.getId()+"' type='checkbox' ");
				if (checkedHealthItems.contains(item.getId())) {
					out.print("checked='checked'");
				}
				out.print(" /> "+item.getName());
				out.println("</li>");
			}
		}
	}
%>
<%
TalkerBean talker = (TalkerBean)session.getAttribute("talker");
if (talker == null) { 
	response.sendRedirect("SignIn.jsp");
} else {
	//For now we have only one disease - Breast Cancer
	final int diseaseId = 1;
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	//Load data for selects
	Map<Integer, String> stagesMap = TalkmiDBUtil.getValuesByDisease("stage", diseaseId);
	Map<Integer, String> typesMap = TalkmiDBUtil.getValuesByDisease("type", diseaseId);
	
	TalkerDiseaseBean talkerDisease = TalkmiDBUtil.getTalkerDisease(talker.getUID());
	int talkerDiseaseId = 0;
	if (talkerDisease != null) {
		talkerDiseaseId = talkerDisease.getId();
	}
%>
<%@ include file="header.jsp" %>
<link href="css/drop-down-menu.css" type="text/css" rel="stylesheet" />
	<style>
		body {
			margin: 0px;
			padding:0px;
			font-family:arial;
			font-size:11px;
			color:#000000;
			background:url(images/inner_bg.gif) repeat-x top;
		}
		.signfields2 ul {
			padding-top: 0px;
		}
		.signfields2 ul li {
			background:none;
			font-size: 14px;
			line-height: 24px;
			height: 24px;
			padding: 0px;
			width:600px;
		}	
	</style>
</head>
<body>

<div id="top_container">
	<div id="top">
		<div id="logo">
			<a href="index.jsp"><img src="images/spacer.gif" alt="Talk About Health" width="328" height="73" border="0" /></a>
		</div>
		<div id="innerbanner"></div>
		<form id="healthdetailsform" name="healthdetailsform" action="HealthDetails" method="POST" />
		<input type="hidden" name="talkerdiseaseid" value="<%= talkerDiseaseId %>" />
		<div id="innermain">
			<div class="blacktext2" id="innerheading">General Health Details</div>
			<div id="innermiddlearea">
				<div id="notifactionbox">
					<div class="notifactionboxtop"></div>
					<div class="notifactionboxmid">
						<div class="notibluearea">
							<div class="notiblueareatop"></div>
							<div class="signfields2">
								<div class="row">
									<label>What is the stage?</label>
									<div>
										<select name="diseasestage" class="textarea1">
											<%
												for (Entry<Integer, String> entry : stagesMap.entrySet()) {
											    	out.print("<option value='"+entry.getKey()+"' ");
											    	if (talkerDisease != null && talkerDisease.getStageId() == entry.getKey()) {
											    		out.print("selected='selected'");
											    	}
											    	out.println(" >"+entry.getValue()+"</option>");
											    }
											%>
										</select>
									</div> 
								</div>
								<div class="row">
									<label>Disease type</label>
									<div>
										<select name="diseasetype" class="textarea1">
									 		<%
												for (Entry<Integer, String> entry : typesMap.entrySet()) {
													out.print("<option value='"+entry.getKey()+"' ");
											    	if (talkerDisease != null && talkerDisease.getTypeId() == entry.getKey()) {
											    		out.print("selected='selected'");
											    	}
											    	out.println(" >"+entry.getValue()+"</option>");
											    }
											%>
										</select>
									</div> 
								</div>
								<div class="row">
									<label>Is it recurrent?</label>
									<div>
										<select name="recurrent" class="textarea1">											
									 		<option value="n">No</option>
									 		<option value="y" 
									 		<%											
										 		if (talkerDisease != null && talkerDisease.isRecurrent()) {
										    		out.print(" selected='selected' ");
										    	}				
											%>
									 		>Yes</option>
										</select>
									</div> 
								</div>
								<div class="row">
									<label>When did the first symptom appear?</label>
									<div>
										<input name="symptomdate" type="text" class="textfields" 
											onclick="this.value=''" 
											<%											
										 		if (talkerDisease != null && talkerDisease.getSymptomDate() != null) {
										 			out.print("value='"+dateFormat.format(talkerDisease.getSymptomDate())+"'");
										    	}
										 		else {
										 			out.print("value='mm/dd/yyyy'");
										 		}
											%>
											/>
									</div> 
								</div>
								<div class="row">
									<label>When were you first diagnosed?</label>
									<div>
										<input name="diagnosedate" type="text" class="textfields" 
											onclick="this.value=''" 
											<%											
										 		if (talkerDisease != null && talkerDisease.getDiagnoseDate() != null) {
										 			out.print("value='"+dateFormat.format(talkerDisease.getDiagnoseDate())+"'");
										    	}
										 		else {
										 			out.print("value='mm/dd/yyyy'");
										 		}
											%>
											/>
									</div> 
								</div>
								<h1 style="margin-top: 15px;" >What were the initial symptoms you experienced?</h1>
								<ul>
									<%
										HealthItemBean symptomItem = TalkmiDBUtil.getHealthItemByName("symptoms", diseaseId);
										printTree(symptomItem, talkerDisease.getHealthItems(), out);
									%>
								</ul>
							</div>
							<div class="notiblueareabot"></div>
						</div>
						<div id="savebutton" onclick="document.healthdetailsform.submit();"><img src="images/save.gif" width="186" height="46" /></div>
					</div>
					<div class="notifactionboxbot"></div>
				</div>
				<div id="tipsbox">
					<b>Tips</b><br/><br/>
					Filling in your health details enables
					us to find others just like you for conversations.
					The more information you provide
					the better we can match you 
					with conversations and other members.
				</div>
			</div>
		</div>
		
		<div id="midnoticontainer">
			<div id="midnoticontainermain">
				<div id="boxarea_head2" class="blacktext2">Tests and Procedures</div>
				<div id="notifactionbox2">
					<div class="notifactionboxtop"></div>
					<div class="notifactionboxmid">
						<div class="notibluearea">
							<div class="notiblueareatop"></div>
							<div class="signfields2">								
								<h1>Which of the following tests have you had?</h1>
								<ul>
									<%
										HealthItemBean testsItem = TalkmiDBUtil.getHealthItemByName("tests", diseaseId);
										printTree(testsItem, talkerDisease.getHealthItems(), out);
									%>
								</ul>
							</div>
							<div class="notiblueareabot"></div>
						</div>
						<div class="notibluearea">
							<div class="notiblueareatop"></div>
							<div class="signfields2">								
								<h1>What procedures / surgeries have you had?</h1>
								<ul>
									<%
										HealthItemBean proceduresItem = TalkmiDBUtil.getHealthItemByName("procedures", diseaseId);
										printTree(proceduresItem, talkerDisease.getHealthItems(), out);
									%>
								</ul>
							</div>
							<div class="notiblueareabot"></div>
						</div>
						<div id="savebutton" onclick="document.healthdetailsform.submit();"><img src="images/save.gif" width="186" height="46" /></div>
					</div>
					<div class="notifactionboxbot"></div>
				</div>
			</div>
		</div>
		
		<div id="midnoticontainer">
			<div id="midnoticontainermain">
				<div id="boxarea_head2" class="blacktext2">Treatments and medications</div>
				<div id="notifactionbox2">
					<div class="notifactionboxtop"></div>
					<div class="notifactionboxmid">
						<div class="notibluearea">
							<div class="notiblueareatop"></div>
							<div class="signfields2">								
								<h1>What treatments / medications have you taken?</h1>
								<ul>
									<%
										HealthItemBean treatmentsItem = TalkmiDBUtil.getHealthItemByName("treatments", diseaseId);
										printTree(treatmentsItem, talkerDisease.getHealthItems(), out);
									%>
								</ul>
							</div>
							<div class="notiblueareabot"></div>
						</div>
						<div id="savebutton" onclick="document.healthdetailsform.submit();"><img src="images/save.gif" width="186" height="46" /></div>
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