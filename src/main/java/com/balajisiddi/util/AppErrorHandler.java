package com.balajisiddi.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet(value = "/AppErrorHandler")
public class AppErrorHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final Logger log= Logger.getLogger(AppErrorHandler.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}
	
	private void processError(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// Analyze the servlet exception
		Throwable throwable = (Throwable) request
				.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request
				.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request
				.getAttribute("javax.servlet.error.servlet_name");
		if (servletName == null) {   
			servletName = "Unknown";
		}
		String requestUri = (String) request
				.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {  
			requestUri = "Unknown";
		}
		if(statusCode != 500){
	    	  log.info("Error Details");
	    	  log.info("Status Code: "+statusCode);
	    	  log.info("Requested URI:"+requestUri);
	      }else{
	    	  log.info("Exception Details");
	    	  log.info(">Servlet Name:"+servletName);
	    	  log.info("Exception Name:"+throwable.getClass().getName());
	    	  log.info("Requested URI:"+requestUri);
	    	  log.info("Exception Message:"+throwable.getMessage());
	      }
		request.getRequestDispatcher("404.jsp").forward(request, response);
	}
}
