package com.balajisiddi.util;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.xml.DOMConfigurator;


public class AppContextListener implements ServletContextListener{

	public static final Logger log= Logger.getAnonymousLogger();
	
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	ServletContext ctx = servletContextEvent.getServletContext();

    	//initialize DB Connection
    	String dbURL = ctx.getInitParameter("dbURL");
    	String user = ctx.getInitParameter("dbUser");
    	String pwd = ctx.getInitParameter("dbPassword");
    	
    	try {
			DBConnection connectionManager = new DBConnection(dbURL, user, pwd);
			ctx.setAttribute("DBConnection", connectionManager.getConnection());
			log.info("DB Connection initialized successfully.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	//initialize log4j
    	String log4jConfig = ctx.getInitParameter("log4j-config");
    	if(log4jConfig == null){
    		log.info("No log4j-config init param, initializing log4j with BasicConfigurator");
			BasicConfigurator.configure();
    	}else {
			String webAppPath = ctx.getRealPath("/");
			String log4jProp = webAppPath + log4jConfig;
			File log4jConfigFile = new File(log4jProp);
			if (log4jConfigFile.exists()) {
				log.info("Initializing log4j with: " + log4jProp);
				DOMConfigurator.configure(log4jProp);
			} else {
				log.info(log4jProp + " file not found, initializing log4j with BasicConfigurator");
				BasicConfigurator.configure();
			}
		}
    	log.info("log4j configured properly");
    }

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		Connection con = (Connection) servletContextEvent.getServletContext().getAttribute("DBConnection");
    	try {
    		log.info("Context Destroying.....");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
