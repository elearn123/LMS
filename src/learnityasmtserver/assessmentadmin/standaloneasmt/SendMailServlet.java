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

public class SendMailServlet extends HttpServlet
{

    public SendMailServlet()
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
	    
		PrintWriter printwriter = httpservletresponse.getWriter();
		httpservletresponse.setContentType("text/html");
		
		String sendmailfrom="";
		ResourceBundle rb = ResourceBundle.getBundle("portal1",Locale.getDefault());
		String key1= "mailsendfrom"; 
		sendmailfrom = rb.getString(key1);
		System.out.println("sendmailfrom=====SendMailServlet==============="+sendmailfrom);

		printwriter.println("sendmailfrom=====SendMailServlet==============="+sendmailfrom);
				

	    String assess_id = httpservletrequest.getParameter("AssessTitleSelect");
	    String student_id = httpservletrequest.getParameter("student_id");
	    System.out.println("===========student_id===SendMailServlet====="+student_id);
	    
	    String server_title = learnityasmtserver.assessmentadmin.dbconnection.DataBaseLayer.getMailServerTitle();
	    System.out.println("===========server_title====1=SendMailServlet==="+server_title);
	    if(server_title==null)
		    server_title = "";
	    System.out.println("===========server_title====2==SendMailServlet=="+server_title);
	    if(server_title.equals(""))
	    {
		    printwriter.println("<font color=red size=4>*No Active Mail Server Found</font>");
	    }
	    else
	    {
		    System.out.println("===========else==SendMailServlet=="+server_title);
	    if(learnityasmtserver.assessmentadmin.dbconnection.DataBaseLayer.IsStudentGiveTest(assess_id,student_id))
	    {
	    
		   			    
	    
				    String dirName=Host.getServerDocumentPath();
				    System.out.println("==================dirName====SendMailServlet======"+dirName);
        	
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
	   		 String assess_name=learnityasmtserver.assessmentadmin.dbconnection.DataBaseLayer.getAssessmentTitle(assess_id);
	            
	   		 String s = learnityasmtserver.assessmentadmin.dbconnection.DataBaseLayer.getMailId(student_id);
	   		 System.out.println("=============mail to=========="+s);
	   		 String s2 = httpservletrequest.getParameter("message");
			 System.out.println("=============mail form=========="+sendmailfrom);
	    
	   		 String s1 = "Assessment Result for "+assess_name;
	   		 if(s2==null)
		   		 s2="";
	   		 String s12="";
       	
			 boolean debug = false;
        
        		try
        		{	
				/*
				String smtpport="587";
				Properties properties = System.getProperties();
				properties.put("mail.smtp.host", "smtp.wordster.com");
				properties.put("mail.smtp.auth", "true");//SMTP Authentication
				properties.put("mail.smtp.port",smtpport);
				Authenticator auth = new SMTPAuthenticator();
				Session session = Session.getDefaultInstance(properties, auth);
				session.setDebug(debug);*/
				
					
				Properties properties = System.getProperties();
//            			properties.put("mail.smtp.host", "www.aunwesha.com");
				properties.put("mail.smtp.host", server_title);
				properties.setProperty("mail.user", "diptendu@aunwesha.com");
				properties.setProperty("mail.password", "diptendu123");
	    
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
				learnityasmtserver.assessmentadmin.dbconnection.DataBaseLayer.updateMailSentStatus(assess_id,student_id,"Mail successfully sent on "+strDate+" "+strTime);
				printwriter.println("<strong><b>Mail successfully sent!</b></strong>");
		
			}// try			
			catch(Exception e)
			{
				System.out.println("Exception e=="+e);
				e.printStackTrace();
			}
			catch(Throwable throwable)
			{
		
				learnityasmtserver.assessmentadmin.dbconnection.DataBaseLayer.updateMailSentStatus(assess_id,student_id,"Mail can not be sent on "+strDate+" "+strTime);
				printwriter.println("<strong><b>Mail can not be sent. View The log.</b></strong>");
				System.out.println("Exception "+throwable.getMessage());
				throwable.printStackTrace();
		
			}
	    	}//if IsStudentGiveTest
	    }//else
        
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws IOException, ServletException {
	    doGet(request, response);
		    }
		    /***************************for log*****************/	    
	/*public void buildLog(String msg,String path) throws IOException{
		String message = msg+"\n";
		FileWriter fos=new FileWriter(path,true);
		for(int i=0;i<message.length();i++){
	
	     		fos.append(message.charAt(i));
	   
		}
	  	fos.close();
	  	
}*/
		   /***********************End of log*************/		    
}