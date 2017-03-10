package deliveryengine;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;



/**
 * Servlet implementation class FrontController
 */
@WebServlet("/servlet/FrontController")
public class FrontController extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private static final long ONE_SECOND_IN_MILLIS = TimeUnit.SECONDS.toMillis(1);

	

    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*enable caching*/
/*		response.setHeader("Cache-Control", "public");
		String unit_id = (String)request.getParameter("unit_id");
		String file_name = (String)request.getParameter("file_name");
		String UploadTime=DataBaseLayer.getUploadTime(unit_id,file_name);
		
		
		UploadTime=UploadTime.substring(0, UploadTime.length() - 2);

		 try {
			 long fileLastModified= new Long(UploadTime).longValue();
			 //fileLastModified=Long.valueOf(UploadTime);
			 response.setDateHeader("Last-Modified", fileLastModified);
		   
		//fileLastModified=Long.parseLong(UploadTime);
		
		//response.setDateHeader(HttpHeaders.LAST_MODIFIED, fileLastModified);
		
	
		
		//long ifModifiedSince = request.getDateHeader(HttpHeaders.IF_MODIFIED_SINCE);
		long ifModifiedSince =request.getDateHeader("If-Modified-Since");
		
		
		if (ifModifiedSince != -1L){
			boolean notModified =ifModifiedSince + ONE_SECOND_IN_MILLIS > fileLastModified; 
			 // That second is there because the header is in seconds, not millis.
			 if (notModified) {
				 response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
				 return;
				 
			 }
			 
			 
		}
		else{
*/
			doPost(request,response);	
/*		}
		
		 }catch (NumberFormatException e){
		       System.out.println("not a number"); 
		} 
	*/	
		
	}

	
	/*  @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)*/
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String html = "";
		WebContext wctx1 = WebContextFactory.get();
	//	javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String unit_id = (String)request.getParameter("unit_id");
		String file_name = (String)request.getParameter("file_name");
	    byte[] buffer=DataBaseLayer.getContentFile(unit_id,file_name);
	    String mimetype=getServletContext().getMimeType(file_name);
	    response.setContentType(mimetype);
        response.setContentLength(buffer.length);
        response.getOutputStream().write(buffer);


	}

}
