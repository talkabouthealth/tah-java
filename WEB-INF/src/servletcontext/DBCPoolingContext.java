package servletcontext;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class DBCPoolingContext implements ServletContextListener{
	public void contextInitialized (ServletContextEvent sce){
		System.out.println("***** Initializing Context!!!");
		try {
			// Obtain our environment naming context
			Context envCtx = (Context) new InitialContext().lookup("java:comp/env");

			// Look up our data source
			DataSource  ds = (DataSource) envCtx.lookup("jdbc/TalkmiDB");

			sce.getServletContext().setAttribute("DBCPool", ds);
		} catch(NamingException e) { 
			e.printStackTrace();
		}
	}
	public void contextDestroyed(ServletContextEvent sce){
 
	}
}