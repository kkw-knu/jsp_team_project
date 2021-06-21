package jsp_project.controller;

import java.io.FileInputStream;

import java.io.IOException;

import java.util.HashMap;

import java.util.Iterator;

import java.util.Map;

import java.util.Properties;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletConfig;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebInitParam;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

 

import jsp_project.service.CommandProcess; 

@WebServlet(urlPatterns="*.ac",	

	initParams={@WebInitParam(name="config",value="/WEB-INF/command.properties")})

public class AcmdController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> commandMap = new HashMap<>();

	public void init(ServletConfig config) throws ServletException { 

	   	String props = config.getInitParameter("config");
	   	Properties pr = new Properties();
	    FileInputStream f = null;
	    try {
	          String configFilePath = 
	         		config.getServletContext().getRealPath(props);
	          f = new FileInputStream(configFilePath);
	          pr.load(f);
	    } catch (IOException e) { throw new ServletException(e);
	     } finally {
	          if (f != null) try { f.close(); } catch(IOException ex) {}
	     }
	     Iterator<Object> keyIter = pr.keySet().iterator();
	     while( keyIter.hasNext() ) {
	          String command = (String)keyIter.next(); 
	          String className = pr.getProperty(command); 
	          try {

	               Class<?> commandClass = Class.forName(className);
	               Object commandInstance = commandClass.newInstance();
	               commandMap.put(command, commandInstance);
	          } catch (Exception e) {
	               throw new ServletException(e);
	          }
	     }
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)

	    throws ServletException, IOException {
		String view = null;
	    CommandProcess com=null;
	    try { 
	    	  String command = request.getRequestURI();
		      command = command.substring(
		    		 request.getContextPath().length()+1); 
	          com = (CommandProcess)commandMap.get(command); 
	          view = com.requestPro(request, response);
	    } catch(Throwable e) { throw new ServletException(e); } 

	    RequestDispatcher dispatcher =
	      	request.getRequestDispatcher("Acmd/"+view);
	   dispatcher.forward(request, response);

	}
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    		request.setCharacterEncoding("utf-8");
    		doGet(request, response);

	}

}