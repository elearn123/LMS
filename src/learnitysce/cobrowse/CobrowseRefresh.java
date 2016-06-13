package learnitysce.cobrowse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import learnityInit.Host;

import org.apache.ecs.html.Head;
import org.apache.ecs.html.Html;
import org.apache.ecs.html.Meta;
import org.apache.ecs.html.Title;

public class CobrowseRefresh extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6123508042058033686L;
	//private static final SimpleLogger log = new SimpleLogger(CobrowseRefresh.class,true);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
  
  		
  		response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	//String timeout=request.getParameter("timeout");
    	
        Html html=new Html();
        Head head = new Head()
        			         .addElement(new Meta()
                             .setHttpEquiv("Refresh")
                             .setContent("5; URL=./learnitysce.cobrowse.CobrowseRefresh"))
                             .addElement(new Title("LearnITy Eksathe: Cobrowsing")                                                                      
                         /*  .addElement(new Script()
                             .setLanguage("javascript")
                             .addElement(script))*/
                             );				
       
        html.addElement(head);
       // html.addElement(new Center().addElement(body)) ;
        out.print(html.toString());
		
		String filepath=Host.getAdminPath()+"/page.txt";
    	File page=new File(filepath);    	
    	  	
    	if(page.canRead())
    	{
    		FileInputStream fis = new FileInputStream(page);    	
    		int c;
	    	while((c = fis.read()) != -1){
	    	out.write(c);
	    	//System.out.println("Read");
	    	}    		
    	}
    	else
    	{
    		out.println("<center><b>No Page currently on Browse</b></center>");
    	}
		out.close();		
   }
   public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        
        doGet(request,response);
   }
}