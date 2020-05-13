package com.balajisiddi.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet(value = "/createBook/*") 
public class BookInsertionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;    
//	private static final Logger LOGGER = Logger.getLogger(BookInsertionServlet.class.getName());
	private static final Logger log= Logger.getLogger(BookInsertionServlet.class);
	  
	public static String getBaseUrl(HttpServletRequest request) {    
		log.info("getting base url");
	    String scheme = request.getScheme() + "://";
	    String serverName = request.getServerName();
	    String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
	    String contextPath = request.getContextPath();  
	    return scheme + serverName + serverPort + contextPath;
	  }
	private void performBusinessLogic(HttpServletRequest request, HttpServletResponse response) {

		String query="SELECT * FROM STUDENT WHERE ID=26";
		Statement stmt;
		try {              
			Connection con= (Connection) getServletContext().getAttribute("DBConnection");
			if(con!=null) {     
				log.trace("Trace Message!");  
			    log.debug("Debug Message!");  
			    log.info("Info Message!");  
			    log.warn("Warn Message!");  
			    log.error("Error Message!");  
			    log.fatal("Fatal Message!");  
			}      
			stmt = con.createStatement();  
			ResultSet rs= stmt.executeQuery(query);   
			if(rs.next()){
				log.info(rs.getString(7));
			}   
			String username = request.getParameter("username");
			log.info(username);
		} catch (Exception e) {  
			e.printStackTrace();
		}
	
	}
	    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		performBusinessLogic(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		performBusinessLogic(req, resp);
	}
}
