package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class faq_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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
      out.write("\t\r\n");
      out.write("\t<!-- scripts -->\r\n");
      out.write("\t\r\n");
      out.write("\t<script type=\"text/JavaScript\" charset=\"utf-8\">\r\n");
      out.write("\t  window.onload = function()\r\n");
      out.write("\t  {\r\n");
      out.write("\t    settings = {\r\n");
      out.write("\t      tl: { radius: 10 },\r\n");
      out.write("\t      tr: { radius: 10 },\r\n");
      out.write("\t      bl: { radius: 10 },\r\n");
      out.write("\t      br: { radius: 10 },\r\n");
      out.write("\t      antiAlias: true,\r\n");
      out.write("\t      autoPad: false\r\n");
      out.write("\t    }\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t    var cornersObj = new curvyCorners(settings, \"content\");\r\n");
      out.write("\t    cornersObj.applyCornersToAll();\r\n");
      out.write("\t  }\r\n");
      out.write("\t</script> \r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\t<!-- java  -->\r\n");
      out.write("\r\n");
      out.write("\t<!-- styles -->\r\n");
      out.write("\t<style type=\"text/css\" title=\"currentStyle\" media=\"screen\">\r\n");
      out.write("\t @import \"/Talkmi/css/info.css\"; \r\n");
      out.write("\t</style>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"top\">\r\n");
      out.write("\t<h5>\r\n");
      out.write("\t\t<a href=\"index.jsp\"> Home |</a>\r\n");
      out.write("\t\t<a href=\"faq.jsp\"> FAQ |</a>\r\n");
      out.write("\t\t<a href=\"feedback.jsp\"> Feedback</a>\r\n");
      out.write("\t</h5>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"center\">\r\n");
      out.write("\t<div id=\"logo\" class=\"logo\">\r\n");
      out.write("\t\t<a href=\"/Talkmi/index.jsp\"><h1><span>Talkmi</span></h1></a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"tagline\">\r\n");
      out.write("\t\t<h3>A Place For Conversations.</h3>\r\n");
      out.write("\t\t<h2>FAQ</h2>\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"content\">\r\n");
      out.write("\t<div id=\"content\">\r\n");
      out.write("\r\n");
      out.write("  <b>blah?</b>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  blah\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  blah\r\n");
      out.write("</p>\r\n");
      out.write("\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("blah</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <b>blah?</b>\r\n");
      out.write("</p>\r\n");
      out.write("\r\n");
      out.write("blah\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <b>blah</b>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("\r\n");
      out.write("  blah\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  blah\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("\r\n");
      out.write("  <b>blah</b>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  blah\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <b>blah</b>\r\n");
      out.write("\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  blah\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <b>blah</b>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("\r\n");
      out.write("blah\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <b>blah</b>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  blah\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <b>blah</b>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("blah\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <b>blah</b>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  blah\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <b>blah</b>\r\n");
      out.write("\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  blah \r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <b>blah</b>\r\n");
      out.write("</p>\r\n");
      out.write("\r\n");
      out.write("<p class=western style=TEXT-ALIGN:left>\r\n");
      out.write("  blah\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <b>blah</b>\r\n");
      out.write("\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  blah\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <b>blah</b>\r\n");
      out.write("</p>\r\n");
      out.write("\r\n");
      out.write("<p class=western>\r\n");
      out.write("  blah\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <b>blah</b>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("\r\n");
      out.write("  blah\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <b>blah</b>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("\r\n");
      out.write("  blah\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <b>blah</b>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  blah\r\n");
      out.write("\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <br>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  <b>blah</b>\r\n");
      out.write("</p>\r\n");
      out.write("<p class=western>\r\n");
      out.write("  blah\r\n");
      out.write("</p>\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"clear\">\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
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
      out.write("\r\n");
      out.write("<!-- end Google Analytics -->\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
