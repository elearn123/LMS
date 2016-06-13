/*
* ==============================================================================================
* Company		- 	Aunwesha Knowledge Technologies Pvt. Ltd.
* Author 		- 	Partha Pratim Dutta
* Designation     	- 	Consultant
* Date of Release 	- 	03/05/2008
* Description     	- 	
* ==============================================================================================
*/
package learnityasmtserver.assessmentadmin.standaloneasmt;

import java.io.*;
import java.io.PrintWriter;
//import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.activation.*;
import learnityInit.*;

public class SendMailServletAll extends HttpServlet
{

    public SendMailServletAll()
    {
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
	    
	        Calendar calendar = new GregorianCalendar();
		java.util.Date trialTime = new java.util.Date();
		calendar.setTime(trialTime);

		 String months[]={"January","Feburary","March", "April", "May","June",
							"July","August","September","October","November","December"};
		String strTime = calendar.get(Calendar.HOUR)+":"+ calendar.get(Calendar.MINUTE);
		String strDate = months[calendar.get(Calendar.MONTH)]+" "+ calendar.get(Calendar.DATE)+", "+ calendar.get(Calendar.YEAR);
	    
		String sendmailfrom="";
		ResourceBundle rb = ResourceBundle.getBundle("portal1",Locale.getDefault());
		String key1= "mailsendfrom"; 
		sendmailfrom = rb.getString(key1);
		System.out.println("sendmailfrom===================="+sendmailfrom);
		
		
		PrintWriter printwriter = httpservletresponse.getWriter();
		httpservletresponse.setContentType("text/html");
	    
	    String assess_id = httpservletrequest.getParameter("AssessTitleSelect");
	    String student_id = "";
	    String assess_name=learnityasmtserver.assessmentadmin.dbconnection.DataBaseLayer.getAssessmentTitle(assess_id);
	    
	    String server_title = learnityasmtserver.assessmentadmin.dbconnection.DataBaseLayer.getMailServerTitle();
	    if(server_title==null)
		    server_title = "";
	    
	    if(server_title.equals(""))
		    printwriter.println("<font color=red size=4>*No Active Mail Server Found</font>");
	    
	    else
	    {
		    System.out.println("assess_id===="+assess_id);
	    Vector registered_students = learnityasmtserver.assessmentadmin.dbconnection.DataBaseLayer.getStudentsForResult(assess_id);
	    
	    
	    System.out.println("registered_students===================="+registered_students);
	    
	
	    if(registered_students.size()!=0)
	    {
		    for(int j=0;j<registered_students.size();j++)
		    {
			    student_id = (String)registered_students.elementAt(j);
	    
	    
			    /********************for log******************/
			    /*File logfile=null;
					    String path = CoreAdminHost.getServerDocumentPath();
			    String strlog[]=new String[4];
			    strlog[0]="assessmentresult";
			    strlog[1]=assess_id;
			    strlog[2]=student_id;
			    strlog[3]="logs";
	    
			    for(int k=0;k<4;k++){
				    if(path == null)
					    throw new ServletException("Please supply logDir parameter");
	    
				    path=path+File.separator+strlog[k];
				    logfile = new File(path);
				    if(!logfile.exists())
					    logfile.mkdir();
			    }
	    
					    String log_file = path+File.separator+"log.txt";*/
			    
			   /********************* End of log ***********************/ 
	if(learnityasmtserver.assessmentadmin.dbconnection.DataBaseLayer.IsStudentGiveTest(assess_id,student_id))
	    {		    
		    String dirName=Host.getServerDocumentPath();
	    System.out.println("==================dirName=========="+dirName);
        	//String dirName=CoreAdminHost.getAdminPath();
	    File uploadfile=null;
	    String str[]=new String[3];
	    str[0]="assessmentresult";
	    str[1]=assess_id;                  
	    str[2]=student_id+File.separator;
	
	    for(int i=0;i<3;i++){
		    if(dirName == null)
			    throw new ServletException("Please supply uploadDir parameter");
		    dirName=dirName+File.separator+str[i];
		    uploadfile = new File(dirName);
		    if(!uploadfile.exists())
			    uploadfile.mkdir(); 
        	
	    } 
	    
	    
        
	    String s = learnityasmtserver.assessmentadmin.dbconnection.DataBaseLayer.getMailId(student_id);
	    System.out.println("s===================="+s);
	    String s2 = httpservletrequest.getParameter("message");
	    System.out.println("=============s2=========="+s2);
	    
	    String s1 = "Assessment Result for "+assess_name;
	    if(s2==null)
		    s2="";
	    String s12="";
       	
        
        
        try
        {
		Properties properties = System.getProperties();
           // properties.put("mail.smtp.host", "www.aunwesha.com");
		properties.put("mail.smtp.host", server_title);
	    
		Session session = Session.getInstance(properties, null);
		MimeMessage mimemessage = new MimeMessage(session);
	    
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(s2);
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		messageBodyPart = new MimeBodyPart();
		FileDataSource source = new FileDataSource(dirName+"assessresult.pdf");
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(source.getName());
		multipart.addBodyPart(messageBodyPart);
	    
		if(!s12.equals(""))
			System.out.println("Email "+s12);
		else
			s12 = sendmailfrom;
		mimemessage.setFrom(new InternetAddress(s12));
		InternetAddress ainternetaddress[] = {
			new InternetAddress(s)
		};
		mimemessage.setRecipients(javax.mail.Message.RecipientType.TO, ainternetaddress);
		mimemessage.setSubject(s1);
		mimemessage.setContent(s2, "text/plain");
		mimemessage.setContent(multipart);
		Transport.send(mimemessage);
		/********************* for log ***********************/
		//buildLog("Mail successfully sent on "+strDate+" "+strTime,log_file);
		/********************* End of log ***********************/
		learnityasmtserver.assessmentadmin.dbconnection.DataBaseLayer.updateMailSentStatus(assess_id,student_id,"Mail successfully sent on "+strDate+" "+strTime);
		printwriter.println("<strong><b>Mail successfully sent to "+student_id+"!</b></strong>");
		
	}
	catch(Throwable throwable)
	{
		/********************* for log ***********************/
		/*String msg = "Log for "+student_id+" : "+throwable.getMessage();
				buildLog(msg,log_file);*/
		/********************* End of log ***********************/
		learnityasmtserver.assessmentadmin.dbconnection.DataBaseLayer.updateMailSentStatus(assess_id,student_id,"Mail can not be sent on "+strDate+" "+strTime);
		printwriter.println("<strong><b>Mail can not be sent to "+student_id+". View The log.</b></strong>");
		System.out.println("Exception "+throwable.getMessage())  ;
		
	}
	    }
		    }
	    }
	    }
        
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws IOException, ServletException {
	    doGet(request, response);
		    }
	/********************* for log ***********************/	    
	/*public void buildLog(String msg,String path) throws IOException{
		String message = msg+"\n";
		FileWriter fos=new FileWriter(path,true);
		for(int i=0;i<message.length();i++){
	
	     		fos.append(message.charAt(i));
	   
		}
	  	fos.close();
	  	
} */
	/********************* End of log ***********************/		    		    
		    
}