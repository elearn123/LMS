package learnitysce.cobrowse;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import java.util.Enumeration;
import org.grlea.log.*;
//import cobrowse.*;

public class CobrowseSubMain extends HttpServlet {
	private static final SimpleLogger log = new SimpleLogger(CobrowseSubMain.class,true);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
  
  		
  		response.setContentType("text/html");
//    	PrintWriter out = response.getWriter();
    	
    	String urlname =request.getParameter("page"); 
    	//String strredirect=request.getParameter("redirect");    	
    	//System.out.println("urlname:"+urlname);  
    	log.debug("urlname:"+urlname);  	
    	int serverport=request.getServerPort();    	
    	String ip= request.getServerName();
    	//String ip= request.getRemoteHost();
    	String context = request.getContextPath();
    	    	  	    	  		
        //Cobrowse.cobrowse(urlname);
        Cobrowse.cobrowse(urlname,ip,serverport,context);
	response.sendRedirect("./learnitysce.cobrowse.CobrowseRefresh");            
   }    
   
   public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        	
        response.setContentType("text/html");
//    	PrintWriter out = response.getWriter();
    		
        String urlname =request.getParameter("page");           
    	//System.out.println("urlname in post:"+urlname); 
    	log.debug("urlname in post:"+urlname);     	
    	int serverport=request.getServerPort();    	
    	String ip= request.getServerName();
    	
    	String context = request.getContextPath();    	   
		
		HttpClient client = new HttpClient();
		PostMethod method2=new PostMethod(urlname);
				
    	Enumeration enu = request.getParameterNames();
    	while (enu.hasMoreElements())
		{
			String name = (String)enu.nextElement();			
			String values =  request.getParameter(name);
			//System.out.println(name +" : " +values);
			log.debug(name +" : " +values); 	
			method2.addParameter(name,values);		
		}
    	
    	client.executeMethod(method2);	
		
        Cobrowse.postcobrowse(urlname,ip,serverport,context,client,method2);
        // response.sendRedirect("./cobrowse.CobrowseRefresh");
   }
}