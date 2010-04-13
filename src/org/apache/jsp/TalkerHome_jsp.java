package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

import beans.TalkerBean;
import beans.TopicBean;

import java.util.Map;
import java.util.LinkedHashMap;
import util.TalkmiDBUtil;
import webapp.LiveConversationsSingleton;
import java.text.SimpleDateFormat;

public final class TalkerHome_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \n");
      out.write("\t\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> \n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" > \n");
      out.write("<head>\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n");
      out.write("\t<meta name=\"author\" content=\"Talkmi\" /> \n");
      out.write("\t<meta name=\"keywords\" content=\"support, health, talk\" /> \n");
      out.write("\t<meta name=\"description\" content=\"real-time support for your health issues\" /> \n");
      out.write("\t<meta name=\"robots\" content=\"all\" /> \n");
      out.write("\t<title>Talkmi: Real-time support for your health issues</title>\n");
      out.write("\n");
      out.write("\t<script type=\"text/JavaScript\" language=\"javascript\" src=\"/tah-java/js/validation.js\" charset=\"utf-8\"></script>    \n");
      out.write("\t<script type=\"text/JavaScript\" language=\"javascript\" src=\"/tah-java/js/talkerhome.js\" charset=\"utf-8\"></script>    \n");
      out.write("\t\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

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

      out.write("\n");
      out.write("\n");
      out.write("<SCRIPT TYPE=\"text/javascript\" charset=\"utf-8\">\n");
      out.write("\n");
      out.write("function submitenter(myfield,e)\n");
      out.write("{\n");
      out.write("var keycode;\n");
      out.write("if (window.event) keycode = window.event.keyCode;\n");
      out.write("else if (e) keycode = e.which;\n");
      out.write("else return true;\n");
      out.write("\n");
      out.write("if (keycode == 13)\n");
      out.write("   {\n");
      out.write("\t\tif (checkForm()) {\n");
      out.write("\t\t\tpostNewTopic(document.getElementById('newtopic'));\n");
      out.write("\t\t\t//myfield.form.submit();\n");
      out.write("\t\t\treturn false;\n");
      out.write("\t\t}\n");
      out.write("   }\n");
      out.write("   return true;\n");
      out.write("}\n");
      out.write("</SCRIPT>\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("<div class=\"top\">\n");
      out.write("\t<h5>\n");
      out.write("\t\t<a href=\"faq.jsp\"> FAQ |</a>\n");
      out.write("\t\t<a href=\"mailto:feedback@Talkmi.com\"> Feedback</a>\n");
      out.write("\t</h5>\n");
      out.write("\n");
      out.write("\t\t\t<a id=\"logout\" href=\"/tah-java/Logout\">Logout</a>\t\n");
      out.write("\t\t\t<div id=\"welcome\">\n");
      out.write("\t\t  \tWelcome! ");

					out.println(cb.getUserName());
				
      out.write("\n");
      out.write("\t\t\t</div>\n");
      out.write("</div>\n");
      out.write("</div>\t\n");
      out.write("<div class=\"centerback\">\n");
      out.write("\n");
      out.write("<div class=\"center\" id=\"home\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\t<div id=\"logo\" class=\"logo\">\n");
      out.write("\t\t<a href=\"/tah-java/index.jsp\"><h1><span class=\"talk\">Talk</span><span class=\"mi\">mi</span></h1></a>\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("\t<div class=\"topics\">\n");
      out.write("\t\t<div class=\"postbox\">\n");
      out.write("\t\t\t<div id=\"postbox\">\n");
      out.write("\t\t       \t<h3><span>Start a conversation:</span></h3>  \n");
      out.write("\t\t\t\t<form id=\"postform\" name=\"postnewtopic\" action=\"javascript:postNewTopic(document.getElementById('newtopic'));\" method=\"post\" > \n");
      out.write("\t\t\t\t\t <textarea id=\"newtopic\" type=\"text\" name=\"newtopic\" rows=\"2\" maxlength=\"40\" size=\"60\" onKeyPress=\"submitenter(this,event)\" TABINDEX=1></textarea>\n");
      out.write("\t\t    \t\t<input id=\"submitnewtopic\" type=\"submit\" value=\"Post it!\" onClick=\"return checkForm()\" TABINDEX=2>\n");
      out.write("\t\t\t\t</form>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t\t<div id=\"topicstable\">\n");
      out.write("\t\t\n");
      out.write("\t\t\t");

						int count = 0;
						for (TopicBean tbTalkmiTopic : mapTalkmiTopics.values()) {
					   				count++;
			
      out.write("\n");
      out.write("\t\t\t<p>\t\t\n");
      out.write("\t\t\t");

									if(count % 2 == 0){
										out.println("<div id=\"" + tbTalkmiTopic.getTopicID()+ "\" class=\"odd\">");
									} else {
										out.println("<div id=\"" + tbTalkmiTopic.getTopicID()+ "\" class=\"even\">");
									}
									out.println("<a href=\"javascript:joinTalk('" + tbTalkmiTopic.getTopicID() + "')\"><div class=\"box\">" + tbTalkmiTopic.getTopic() + "</div></a>");
						
      out.write("\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t");

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
						
      out.write("\n");
      out.write("\t\t\t</p>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<a href=\"javascript:moreOldTopics()\">\n");
      out.write("\t\t\t<div class=\"paging\" id=\"down\">\n");
      out.write("\t\t\t\t<span id=\"older\">Show Five Older Topics</span>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</a>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"bottom\">\n");
      out.write("\t<div id=\"footer\">\n");
      out.write("\t\t<ul>\n");
      out.write("\t\t\t<li><a href=\"index.jsp\"> Home</a>\n");
      out.write("\t\t\t<li><a href=\"faq.jsp\"> FAQ</a>\n");
      out.write("\t\t\t<li><a href=\"privacy.jsp\"> Privacy</a>\n");
      out.write("\t\t\t<li><a href=\"tos.jsp\"> Terms of Use</a>\n");
      out.write("\t\t\t<li><a href=\"mailto:feedback@Talkmi.com\"> Feedback</a>\n");
      out.write("\t\t</ul>\n");
      out.write("\t\t<p>Copyright Talkmi </p>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("<!-- begin Google Analytics -->\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("var gaJsHost = ((\"https:\" == document.location.protocol) ? \"https://ssl.\" : \"http://www.\");\n");
      out.write("document.write(unescape(\"%3Cscript src='\" + gaJsHost + \"google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E\"));\n");
      out.write("</script>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("try {\n");
      out.write("var pageTracker = _gat._getTracker(\"UA-13005583-1\");\n");
      out.write("pageTracker._trackPageview();\n");
      out.write("} catch(err) {}</script>\n");
      out.write("<!-- end Google Analytics -->\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
 }
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
