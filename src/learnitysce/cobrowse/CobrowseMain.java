package learnitysce.cobrowse;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
//import java.util.Enumeration;
//import cobrowse.*;
import org.grlea.log.*;

public class CobrowseMain extends HttpServlet {
	private static final SimpleLogger log = new SimpleLogger(CobrowseMain.class,true);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
  
  		
  		response.setContentType("text/html");
//    	PrintWriter out = response.getWriter();
    	
    	String urlname =request.getParameter("page");    	
    	
    	if(!urlname.startsWith("http"))
    	{
    		//System.out.println("in if");
    		urlname= "http://"+urlname;
    	}
    	
    	String strredirect=request.getParameter("redirect");    	
    	//System.out.println("urlname:"+urlname + " strredirect"+ strredirect);
    	log.debug("urlname:"+urlname + " strredirect"+ strredirect);
    	
    	int serverport=request.getServerPort();    	
    	//String ip= request.getRemoteAddr();
    	String ip= request.getServerName();
    	//String ip= request.getRemoteHost();
    	String context = request.getContextPath();
    	//System.out.println("serverport: "+serverport + " uRL :" +ip+ context);
    	    	  	    	
//  		String script ="\n function url() {"+
//  					   //"\n		alert(document.frm.page.value);	"+
//  					   "\n		if(document.frm.page.value == \"\"){"+
//  					   "\n			alert(\"Please enter a Url\");"+
//					   "\n		}" +
//					 /*"\n		else if((document.frm.page.value).substring(0,4)!=\"http\"){"+					   
 // 					   "\n			alert(\"Please enter http:// Protocol\");"+
//					   "\n		}"+		*/	   
//					   "\n		else{"+
//					   "\n			location.href =\"./cobrowse.CobrowseMain?redirect=1&page=\"+document.frm.page.value;"+					 				   
//					   "\n		}"+
//					   "\n	}";
					   			
        	Cobrowse.cobrowse(urlname,ip,serverport,context);
        	if (strredirect!=null) {
        	int iredirect = Integer.parseInt(strredirect);
        	
	        	if(iredirect==1)
	        	{
	        		//System.out.println("************************OK***********************");
	        		log.debug("************************OK***********************");
	        		response.sendRedirect("../servlets/TopCobrowse.html");
	        	}
        	}
        
       // }					   		
   }       
   
   public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        	
        	doGet(request,response);
   }
}