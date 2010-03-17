package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \r\n");
      out.write("\t\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> \r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" > \r\n");
      out.write("<head>\r\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
      out.write("\t<meta name=\"author\" content=\"Talkmi\" /> \r\n");
      out.write("\t<meta name=\"keywords\" content=\"support, health, talk\" /> \r\n");
      out.write("\t<meta name=\"description\" content=\"real-time support for your health issues\" /> \r\n");
      out.write("\t<meta name=\"robots\" content=\"all\" /> \r\n");
      out.write("\t<title>Talkmi : Real-time support for your health issues</title>\r\n");
      out.write("\r\n");
      out.write("\t<!-- scripts -->\r\n");
      out.write("\t<script language=\"javascript\">\r\n");
      out.write("\t\tfunction trim(stringToTrim) {\r\n");
      out.write("\t\t\treturn stringToTrim.replace(/^\\s+|\\s+$/g,\"\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\r\n");
      out.write("\t\tfunction checkForm()\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\t//Prompt if missing userid and password\r\n");
      out.write("\t\t\tif((document.login.username.value == \"\") && (document.login.password.value == \"\"))\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(\"Please enter your username and password\");\r\n");
      out.write("\t\t\t\tdocument.login.username.focus();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t//Prompt if missing userid\r\n");
      out.write("\t\t\tif(document.login.username.value == \"\")\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(\"Please enter your username\");\r\n");
      out.write("\t\t\t\tdocument.login.username.focus();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t//Prompt if missing password\r\n");
      out.write("\t\t\tif(document.login.password.value == \"\")\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(\"Please enter your password\");\r\n");
      out.write("\t\t\t\tdocument.login.password.focus();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\treturn true;\r\n");
      out.write("\t\t}\r\n");
      out.write(" \r\n");
      out.write("\t</script>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
 
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

String sUserName = (String)session.getAttribute("username");
if (sUserName != null) { 
	response.sendRedirect("TalkerHome.jsp");
} else {
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<style type=\"text/css\" title=\"currentStyle\" media=\"screen\">\r\n");
      out.write("\t @import \"/tah-java/css/index.css\"; \r\n");
      out.write(" \t</style>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"top\">\r\n");
      out.write("\t<h5>\r\n");
      out.write("\t\t<a href=\"index.jsp\"> Home |</a>\r\n");
      out.write("\t\t<a href=\"faq.jsp\"> FAQ |</a>\r\n");
      out.write("\t\t<a href=\"mailto:feedback@Talkmi.com\"> Feedback</a>\r\n");
      out.write("\t</h5>\r\n");
      out.write("</div>\t\r\n");
      out.write("\r\n");
      out.write("<div class=\"center\">\r\n");
      out.write("\t<div id=\"logo\" class=\"logo\">\r\n");
      out.write("\t\t<a href=\"/tah-java/index.jsp\"><h1><span class=\"talk\">Talk</span><span class=\"mi\">mi</span></h1></a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"signin\">\r\n");
      out.write("\t\t<form id=\"loginform\" action=\"/tah-java/Login\" name=\"login\" method=\"post\" class=\"loginform\">          \t\r\n");
      out.write("\t\t\t\t<h2>Sign in here\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t");

					String lf = request.getParameter("login");
					if (lf != null && lf.equals("f")) {
						out.println("<font id=\"error\">Login failed.</font>");
					} 
					
      out.write("\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t</h2>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t    \t<label><span>Username</span>\r\n");
      out.write("\t\t    \t\t<input \r\n");
      out.write("\t\t    \t\t\tid=\"username\" class=\"input-box\" name=\"username\" type=\"text\" maxlength=\"25\" size=\"25\"  TABINDEX=1/>\r\n");
      out.write("\t\t\t    \t</input>\r\n");
      out.write("\t\t\t    </label>\r\n");
      out.write("\t\t\t   \t<label><span>Password</span>\r\n");
      out.write("\t\t    \t\t<input id=\"password\" class=\"input-box\" name=\"password\" type=\"password\" maxlength=\"25\" size=\"25\" TABINDEX=2>\r\n");
      out.write("\t\t\t    \t</input>\r\n");
      out.write("\t\t\t    </label>\t\t\r\n");
      out.write("\t\t\t\t<input id=\"login\" type=\"submit\" value=\"Continue\" onClick=\"return checkForm()\" TABINDEX=3></input>\r\n");
      out.write("\t\t</form>\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<br>\r\n");
      out.write("\t<br>\r\n");
      out.write("\t\t<div class=\"signup\">\r\n");
      out.write("\t\t\t<a id=\"su\" href=\"/tah-java/SignUp.jsp\" TABINDEX=3>\r\n");
      out.write("\t\t\t<div id=\"signup\">\r\n");
      out.write("\t\t\t\t<h3><span>Sign Up!</span></h3>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t</a>\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("</div>\t\r\n");
      out.write("<div class=\"bottom\">\r\n");
      out.write("\t<div id=\"footer\">\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li><a href=\"index.jsp\"> Home</a>\r\n");
      out.write("\t\t\t<li><a href=\"faq.jsp\"> FAQ</a>\r\n");
      out.write("\t\t\t<li><a href=\"privacy.jsp\"> Privacy</a>\r\n");
      out.write("\t\t\t<li><a href=\"tos.jsp\"> Terms of Use</a>\r\n");
      out.write("\t\t\t<li><a href=\"mailto:feedback@Talkmi.com\"> Feedback</a>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<p>Copyright Talkmi </p>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- begin Google Analytics -->\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var gaJsHost = ((\"https:\" == document.location.protocol) ? \"https://ssl.\" : \"http://www.\");\r\n");
      out.write("document.write(unescape(\"%3Cscript src='\" + gaJsHost + \"google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E\"));\r\n");
      out.write("</script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("try {\r\n");
      out.write("var pageTracker = _gat._getTracker(\"UA-13005583-1\");\r\n");
      out.write("pageTracker._trackPageview();\r\n");
      out.write("} catch(err) {}</script>\r\n");
      out.write("<!-- end Google Analytics -->\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
