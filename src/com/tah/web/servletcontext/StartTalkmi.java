package com.tah.web.servletcontext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tah.web.webapp.RunTalkmi;


public class StartTalkmi implements ServletContextListener{
	public void contextInitialized (ServletContextEvent sce){
		System.out.println("***Starting Talkmi Thread!!!");
		Thread tRunTalkmi = new Thread(new RunTalkmi(), "RunTalkmiThread");
    	tRunTalkmi.start();
    }
	public void contextDestroyed(ServletContextEvent sce){
 
	}
}