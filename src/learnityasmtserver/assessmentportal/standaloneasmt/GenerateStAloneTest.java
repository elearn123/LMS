/*
* ==============================================================================================
* Company			- 	Aunwesha Knowledge Technologies Pvt. Ltd.
* Author 		    - 	Nayna Mallick
* Designation     	- 	Consultant
* Date of Release 	- 	25/04/2008
* Description     	- 	
* ==============================================================================================
*/
package learnityasmtserver.assessmentportal.standaloneasmt;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Vector;
import org.apache.ecs.*;
import org.apache.ecs.html.*;
import org.grlea.log.*;
import java.util.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Properties;
import learnityasmtserver.assessmentportal.dbconnection.*;



public class GenerateStAloneTest extends HttpServlet 
{
	public static final SimpleLogger log = new SimpleLogger(GenerateStAloneTest.class, true);// Create a SimpleLogger:
	private static final String SESSION_MODEL_QUESTIONS = "SESSION_MODEL_QUESTIONS";
	private static final String SESSION_MODEL_ANS = "SESSION_MODEL_ANS";
	private static final String SESSION_MODEL_PER_PAGE = "SESSION_MODEL_PER_PAGE";
	private static final String SESSION_MODEL_TIME="SESSION_MODEL_TIME";
	private static final String SESSION_MODEL_TEMPANS = "SESSION_MODEL_TEMPANS";
	private static final String ASSESSMENT_PROGRESS_ID = "ASSESSMENT_PROGRESS_ID";
	
        public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException
	 {
	 	Vector obj =null;
		Vector questionAll=null;
		Vector page_questions=null;
		Vector vQuestions =new Vector();
		Vector vAnsTemp = new Vector();
	 	response.setContentType("application/xhtml+xml");
		PrintWriter out = response.getWriter();
		response.setHeader("Pragma", "no-cache");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);
		
		HttpSession mysession=request.getSession(true);
		String student_id = (String)mysession.getAttribute("student_id");
		if(student_id!=null) {
			getResult(request, response, out,student_id,obj,questionAll,page_questions,vQuestions,vAnsTemp);
		}
	}
public void getResult(HttpServletRequest request, HttpServletResponse response, PrintWriter out,String student_id,Vector obj,Vector questionAll,Vector page_questions,Vector vQuestions,Vector vAnsTemp)
    		throws IOException, ServletException {

	        Calendar calendar = new GregorianCalendar(); 
	        Date trialTime = new Date();
	        calendar.setTime(trialTime);
	        String months[]={"January","Feburary","March", "April", "May","June",
						"July","August","September","October","November","December"};
                String strTime = calendar.get(Calendar.HOUR)+":"+ calendar.get(Calendar.MINUTE)+":"+ calendar.get(Calendar.SECOND); 
	        String strDate = months[calendar.get(Calendar.MONTH)]+" "+ calendar.get(Calendar.DATE)+", "+ calendar.get(Calendar.YEAR); 
	        String str11 = strDate.substring(0,strDate.indexOf(','));
       		String str4 = str11.substring(0,str11.indexOf(' '));
       		String str3 = str11.substring(str11.indexOf(' ')+1);
       		String str2 = strDate.substring(strDate.indexOf(',')+1);
       		int l1=months.length;
       		Integer month1=0;
       		for(int i11=0; i11<l1; i11++) {
       			String m=months[i11];
       			if(str4.equals(m)){
       				month1=i11+1;
       				break;
       			}
       		}
       		
       		String s_month1=Integer.toString(month1);
       		if(month1<10){
       			s_month1="0"+s_month1;
       		}
       		String df = str2+"-"+s_month1+"-"+str3;
	       
	        HttpSession mysession=request.getSession(true);
	
		System.out.println("==========student_id========="+student_id);
	
	String baseName = "portal1";
     	String refresh="";
	  try {
       			Locale dd=Locale.getDefault();
         		log.debug("INPUT LOCALE NAME IS "+Locale.getDefault());
          		double amt = 123456.78;
         		log.debug("LANGUAGE"+dd.getLanguage());
         
         		 ResourceBundle rb = ResourceBundle.getBundle("portal1",Locale.getDefault());      
			 String key1="";
              		 key1="refreshassessment";
			 refresh = rb.getString(key1);
	    		
        
           	} 
		catch (MissingResourceException e) {			
			System.out.println("Exception:=="+e);
			e.printStackTrace();
		}
	
/////////////////////////////////////////////////////////////////////////////////////
		ReportDataBaseLayer ob1=new  ReportDataBaseLayer();
		String checkModelstatus=request.getParameter("checkModelstatus");
		String newtest=request.getParameter("newtest");
		String assessment_id=request.getParameter("assessment_id");
		System.out.println("**GenerateStAloneTest******assessment_id******"+assessment_id);
		System.out.println("**GenerateStAloneTest******newtest******"+newtest);
		System.out.println("**GenerateStAloneTest******checkModelstatus******"+checkModelstatus);
		
		String save_state=request.getParameter("save_state");
		System.out.println("**GenerateStAloneTest******save_state******"+save_state);
		if(save_state!=null && save_state.equals("no")){
			Vector vv=ob1.isPreviousTestSubmitted(student_id,assessment_id);
			String pretest_id="";
			if(vv.size()!=0) 
				pretest_id=(String)vv.elementAt(2);
			System.out.println("pretest_id==="+pretest_id);	
			ob1.deleteprevioustest(student_id,assessment_id,pretest_id);
		}
		String timeparam=request.getParameter("disp2");
		if(timeparam != null)
			timeparam=timeparam.trim();
		System.out.println("timeparam=======1========"+timeparam);
			
		if(newtest!=null && newtest.equals("start")){
			vAnsTemp.removeAllElements();
		}
		Vector vAns = null;
		Vector vQuesIdAns=new Vector();
		String serial_no_questions=request.getParameter("serial_no");
		int serial_no=0;
		if(serial_no_questions!=null && !serial_no_questions.equals("0"))
	        {
			
			serial_no = Integer.parseInt(serial_no_questions);
		}
		else
		{
	              serial_no = 0;
	        }
		
		
		String preview_param = request.getParameter("preview_param");
		System.out.println("preview_param=="+preview_param);
		
		if((checkModelstatus!=null) && (preview_param == null))     //preview param by partha
		{
			mysession.removeAttribute(SESSION_MODEL_QUESTIONS);
			mysession.removeAttribute(SESSION_MODEL_ANS);
			mysession.removeAttribute(SESSION_MODEL_PER_PAGE);
			mysession.removeAttribute(SESSION_MODEL_TIME);
			mysession.removeAttribute(SESSION_MODEL_TEMPANS);
		}
		
		
		String TestStartTime=(String) mysession.getAttribute(SESSION_MODEL_TIME);
		if(TestStartTime==null ||TestStartTime.equals(""))
		{
	           mysession.setAttribute(SESSION_MODEL_TIME, strTime); 	
	        }
		String AssessmentTitle="";
		System.out.println("refresh=="+refresh);
		String refreshassessment="";
		if(refresh != null && !refresh.equals(""))
			refreshassessment="<meta http-equiv=\"refresh\" content=\""+refresh+"; url=javascript:autosave()\" />";	
		
               

		Vector vAssessmentDeffination=ob1.getAssessmentDeffination(assessment_id);
		String total_ques="";
		String total_time_left="";
		String ques_per_page="";
		if(assessment_id!=null && checkModelstatus!=null){
			System.out.println("assessment_id=="+assessment_id);
			Vector vAssessmentInfo=ob1.getAssessmentInfo(assessment_id);
			String AssessmentInfo1[] =(String[])vAssessmentInfo.elementAt(0);
			total_time_left=AssessmentInfo1[0];
			ques_per_page=AssessmentInfo1[1];
			System.out.println("total_time_left=="+total_time_left);
			Integer totalques=0;
			for(int k=0; k<vAssessmentDeffination.size(); k++) 
	 		{
	     			String AssessmentInfo[] =(String[])vAssessmentDeffination.elementAt(k);
	     			String no_of_questions=AssessmentInfo[1];
				Integer totalques2=Integer.parseInt(no_of_questions);
				System.out.println("totalques2=="+totalques2);
				System.out.println("totalques=b="+totalques);
			    	totalques=totalques+totalques2;
				System.out.println("totalques=a="+totalques);
				
			}// End of for 
			total_ques=Integer.toString(totalques);
			
			AssessmentTitle=ob1.getAssessmentTitle(assessment_id);
		
		}
		else
		{
			total_time_left=request.getParameter("total_time_left");
			total_ques=request.getParameter("total_ques");
			AssessmentTitle=request.getParameter("AssessmentTitle");
		}
		System.out.println("total_ques======"+total_ques);
		System.out.println("AssessmentTitle======"+AssessmentTitle);
   		int totalquestion=Integer.parseInt(total_ques); 
		
		obj = (Vector) mysession.getAttribute(SESSION_MODEL_QUESTIONS);
	        int No_Ques =0;
	        if(total_ques!=null && !total_ques.equals(""))
	        {
	              No_Ques = Integer.parseInt(total_ques);
	        }
		System.out.println("No_Ques======"+No_Ques);
		int no_question_available=0;
		String total_ques_retrive=request.getParameter("total_no_of_question_retrived");
		if(total_ques_retrive != null && !total_ques_retrive.equals(""))
	        {
			no_question_available = Integer.parseInt(total_ques_retrive);
		}
		else
	        {
	                no_question_available = 0;
	        }
		System.out.println("no_question_available======"+no_question_available);
		int no_question_retrived=0;
		String no_of_question_retrived=request.getParameter("no_of_question_retrived");
	        if(no_of_question_retrived!=null && !no_of_question_retrived.equals(""))
	        {
	              no_question_retrived = Integer.parseInt(no_of_question_retrived);
	        }
		System.out.println("no_question_retrived======"+no_question_retrived);
		//String ques_per_page="50";/************/
		//ques_per_page=total_ques;
		int no_question_per_page=0;
		if(newtest!=null && newtest.equals("start"))
		{
		}
		else{
			ques_per_page=request.getParameter("ques_per_page");
		}
		System.out.println("ques_per_page======"+ques_per_page);
	       	if(ques_per_page != null && !ques_per_page.equals(""))
	        {
			no_question_per_page = Integer.parseInt(ques_per_page);
		}
		else
	        {
	              no_question_per_page = No_Ques;
	        }
		
		int duration=0;
	        if(total_time_left!=null && !total_time_left.equals(""))
	        {
			duration = Integer.parseInt(total_time_left);
		}
		else
		{
	                duration = No_Ques;
	        }
		
	      String previous=request.getParameter("previous");
	      System.out.println("previous======"+previous);
	      int previous_int =0;
	      if(previous!=null){
	        	previous_int = Integer.parseInt(previous);
                	
	      }
	      System.out.println("previous_int======"+previous_int);
	      String test_id="";
	      if(newtest!=null && newtest.equals("start"))
	         {
			ob1.addTestDetails(student_id,assessment_id,strTime,total_ques,ques_per_page,total_time_left);
			test_id = ob1.getTestID(student_id,assessment_id);
			//ob1.addTestResult(student_id,strTime,total_ques,assessment_id,test_id);
			
	         }
		 System.out.println("newtest======"+newtest);
              if (page_questions != null && checkModelstatus==null ) {
                        Object o = mysession.getAttribute(SESSION_MODEL_ANS);
                        Vector vTempAns = (Vector) mysession.getAttribute(SESSION_MODEL_TEMPANS);
                        mysession.removeAttribute(SESSION_MODEL_TEMPANS);
	                if (o == null) {
				vAns = new Vector();
			}
			else {
				vAns = (Vector) o;
			}
			if(vTempAns !=null)
			{
				vAns.addElement((Vector)vTempAns.elementAt(0));
			}
                        
                        Vector vA = new Vector();
                        Vector vQ = new Vector();
			Vector vT = new Vector();
                        String IdMr=null;
                        
        	    for (int i = 0; i < page_questions.size(); ++i) {
			      Vector questionMatter = (Vector) page_questions.elementAt(i);
			      Vector res = (Vector) questionMatter.elementAt(0);
			      Vector question = (Vector) questionMatter.elementAt(1);
                              Integer iTitle = (Integer) res.elementAt(0);
			      String strQuesNo = "";
			      String str = "";
			     
			     switch (iTitle.intValue()) {
			         case 0 :
			             strQuesNo = (String) question.elementAt(0);
			             str = request.getParameter(strQuesNo);
			             vQ.addElement(strQuesNo); 
			             vA.addElement(str);
				     vT.addElement("MC"); 
			             break;
			         case 1 :
			             strQuesNo = (String) question.elementAt(0);
			             String str1[] = request.getParameterValues(strQuesNo);
			             IdMr=strQuesNo;
			      	     vQ.addElement(strQuesNo); 
			             vA.addElement(str1);
					 vT.addElement("MR"); 
			             break;
			         case 2 :
			             strQuesNo = (String) question.elementAt(0);
			             str = request.getParameter(strQuesNo);
			             vQ.addElement(strQuesNo); 
			             vA.addElement(str);
					 vT.addElement("MC"); 
			             break;
			        case 3 :
			             strQuesNo = (String) question.elementAt(0);
			             str = request.getParameter(strQuesNo);
			             vQ.addElement(strQuesNo); 
			             vA.addElement(str);
					 vT.addElement("MC"); 
			             break;
			        
			     }//end of switch case
			}//end of for
			
			Vector id_ans=new Vector();
			for(int i=0;i<vA.size();i++){
	                  		String itemid=(String )vQ.elementAt(i);
	                  		id_ans.addElement(itemid);
					String itypefans=(String )vT.elementAt(i);
	                  		String ansgiven="";
	                  		String ansgivenMr[]=new String[6];
	                  		if(itypefans != null && itypefans.equals("MR")){
	                  			ansgivenMr=(String[])vA.elementAt(i);
	                  			id_ans.addElement(ansgivenMr);
	                  		}
	                  		else{
	                  			
	                  			ansgiven=(String)vA.elementAt(i);
	                  			id_ans.addElement(ansgiven); 
	                  		}            		 
	                  		
	                }
			vAnsTemp.addElement(id_ans);
			vQuesIdAns = getResponses(vAnsTemp);
    		        if(previous_int!=0)
			{
				vAns.removeElementAt(vAns.size()-1);
	        		
			}
			else{
				vAns.addElement(vA);
				
			}
			log.debug("vAns==="+vAns);	
			mysession.setAttribute(SESSION_MODEL_ANS, vAns);
		  }	
                  
	        
		 
				 	 
		  if ((page_questions != null) && preview_param!=null) {
			  Object o = mysession.getAttribute(SESSION_MODEL_ANS);
			  Vector vTempAns = (Vector) mysession.getAttribute(SESSION_MODEL_TEMPANS);
			
			  mysession.removeAttribute(SESSION_MODEL_TEMPANS);
			  if (o == null) {
				  vAns = new Vector();
			  }
			  else {
				  vAns = (Vector) o;
			  }
			  if(vTempAns !=null)
			  {
				  vAns.addElement((Vector)vTempAns.elementAt(0));
			  }
			  
			  Vector vA = new Vector();
			  Vector vQ = new Vector();
			  Vector vT = new Vector();
			  String IdMr=null;
                        
			  for (int i = 0; i < page_questions.size(); ++i) {
				  Vector questionMatter = (Vector) page_questions.elementAt(i);
				  Vector res = (Vector) questionMatter.elementAt(0);
			      
			      
				  Vector question = (Vector) questionMatter.elementAt(1);
				  Integer iTitle = (Integer) res.elementAt(0);
				 
				  String strQuesNo = "";
				  String str = "";
			     
				  switch (iTitle.intValue()) {
					  case 0 :
						  strQuesNo = (String) question.elementAt(0);
						  str = request.getParameter(strQuesNo);
						  vQ.addElement(strQuesNo); 
			            
						  Vector vv = (Vector) vAns.lastElement();
						  String temp_ans = (String) vv.elementAt(i);
						  vA.addElement(temp_ans);
						  vT.addElement("MC"); 
						  break;
					  case 1 :
						  strQuesNo = (String) question.elementAt(0);
						  String str1[] = request.getParameterValues(strQuesNo);
						  IdMr=strQuesNo;
						  vQ.addElement(strQuesNo); 
						  vA.addElement(str1);
						  vT.addElement("MR"); 
						  break;
					  case 2 :
						  strQuesNo = (String) question.elementAt(0);
						  str = request.getParameter(strQuesNo);
						  vQ.addElement(strQuesNo); 
						  vA.addElement(str);
						  vT.addElement("MC"); 
						  break;
					  case 3 :
						  strQuesNo = (String) question.elementAt(0);
						  str = request.getParameter(strQuesNo);
						  vQ.addElement(strQuesNo); 
						  vA.addElement(str);
						  vT.addElement("MC"); 
						  break;
			        
				  }//end of switch case
			  }//end of for
			  
			  Vector id_ans=new Vector();
			  for(int i=0;i<vA.size();i++){
				  String itemid=(String )vQ.elementAt(i);
				  id_ans.addElement(itemid);
				  String itypefans=(String )vT.elementAt(i);
				  String ansgiven="";
				  String ansgivenMr[]=new String[6];
				  if(itypefans != null && itypefans.equals("MR")){
					  ansgivenMr=(String[])vA.elementAt(i);
					  id_ans.addElement(ansgivenMr);
				  }
				  else{
	                  			
					  ansgiven=(String)vA.elementAt(i);
					  id_ans.addElement(ansgiven); 
				  }            		 
	                  		
			  }
			  vAnsTemp.addElement(id_ans);
			  vQuesIdAns = getResponses(vAnsTemp);
			  if(previous_int!=0)
			  {
				  vAns.removeElementAt(vAns.size()-1);
	        		
			  }
			  else{
				  vAns.addElement(vA);
				
			  }	
			  mysession.setAttribute(SESSION_MODEL_ANS, vAns);
		  }				 
				 
				 
				 
		 
	        
	        int AllTotalTime=0;
	        if(checkModelstatus!=null)
		{
	           	if(total_time_left!=null && !total_time_left.equals(""))
	           	{
	             		 AllTotalTime = Integer.parseInt(total_time_left);
				 timeparam = AllTotalTime+":00";
	           	}
	           	else
	           	{
	              		AllTotalTime = No_Ques;
	           	}
	        }
	        else
	        {
	           	String All_total_time=request.getParameter("total_time");
	           	AllTotalTime = Integer.parseInt(All_total_time);	
	        }
	       String strButton ="";
	      if(previous_int!=1)
	      { 
	        if( No_Ques!=0)
	        {
			System.out.println("=================test1============");
	           	AllStAloneTestItems AllItems=new AllStAloneTestItems();
			System.out.println("=================test2============");
	           	if(obj==null){
				System.out.println("=================test3============");
	           			questionAll=AllItems.getAllItems(vAssessmentDeffination);
					System.out.println("=================test3.1============");
					mysession.removeAttribute(SESSION_MODEL_QUESTIONS);
	                		mysession.setAttribute(SESSION_MODEL_QUESTIONS, questionAll);
	                		obj = (Vector) mysession.getAttribute(SESSION_MODEL_QUESTIONS);
	                		no_question_available=questionAll.size();
			
				if(newtest!=null && newtest.equals("start"))
	         		{
					ob1.addProgressStatus(student_id,assessment_id,ques_per_page,strTime,total_time_left);
					mysession.removeAttribute(ASSESSMENT_PROGRESS_ID);
					mysession.setAttribute(ASSESSMENT_PROGRESS_ID, test_id);
	         		} 

		
	              	}
			System.out.println("=================test4========questionAll===="+questionAll);
			System.out.println("=================test5======obj======"+obj);
			System.out.println("=================no_question_available===1111==="+no_question_available);			
			page_questions=getQuestionsPerPage(obj,no_question_retrived,no_question_per_page);
			System.out.println("=================test6====page_questions========"+page_questions);
			mysession.removeAttribute(SESSION_MODEL_PER_PAGE);
	                mysession.setAttribute(SESSION_MODEL_PER_PAGE, page_questions);
                       if (page_questions.size()!=0)
                       {
                       		String lastElement_obj=obj.lastElement().toString();
		       		String lastElement_page_questions=page_questions.lastElement().toString();
                       		String firstElement_obj=obj.firstElement().toString();//nayna
		       		String firstElement_page_questions=page_questions.firstElement().toString();//nayna
                       		if( no_question_per_page == No_Ques ||No_Ques<no_question_per_page||no_question_per_page == 0||lastElement_page_questions.equals(lastElement_obj))
	               		{// start if1
					if((obj.size()>no_question_per_page && no_question_per_page != 0) && lastElement_page_questions.equals(lastElement_obj))
			  		{
						System.out.println("=================last=======");
							strButton ="\n<input class=\"sbttn\" name=\"add1\" tabIndex=\"2\" title=\"Previous Page\" type=\"button\" value=\"Previous Page\" onclick=\"Previous_onclick()\"/>"; 
                           				strButton +="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Submit\" type=\"button\" value=\"Preview\" onclick=\"submitPage()\"/>"; 
	                   				for(int i=0;i<page_questions.size();i++){
	                  						Vector vector1=(Vector)page_questions.elementAt(i);
	                  						vQuestions.addElement(vector1);
	                  	
	                  				}
	                   				StAloneTestXHtmlStructure s=new StAloneTestXHtmlStructure();
							String strHTML = s.generateHTML(page_questions,strButton,duration,true,total_ques,ques_per_page,total_time_left,no_question_retrived,no_question_available,serial_no,AllTotalTime,assessment_id,vQuesIdAns,AssessmentTitle,refreshassessment,timeparam);
                           				out.println(strHTML);
	               	   		}
	               	   		else
	               	   		{	
	                   				
						System.out.println("=================last====2===");
							strButton ="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Submit\" type=\"button\" value=\"Preview\" onclick=\"submitPage()\"/>"; 
	                   				for(int i=0;i<page_questions.size();i++){
	                  						Vector vector1=(Vector)page_questions.elementAt(i);
	                  						vQuestions.addElement(vector1);
	                  	
	                  				}
	                   				
	                   				StAloneTestXHtmlStructure s=new StAloneTestXHtmlStructure();
							String strHTML = s.generateHTML(page_questions,strButton,duration,true,total_ques,ques_per_page,total_time_left,no_question_retrived,no_question_available,serial_no,AllTotalTime,assessment_id,vQuesIdAns,AssessmentTitle,refreshassessment,timeparam);
                           				out.println(strHTML);
                           		}
                           
                        	}// end if1
                       		else{
					if(firstElement_obj.equals(firstElement_page_questions))
                           		{
						System.out.println("=================first====1===");
                            				
							strButton +="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>";
							strButton +="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Submit\" type=\"button\" value=\"Preview\" onclick=\"submitPage()\"/>"; 
                           				vQuestions.removeAllElements();//nayna20
                           				for(int i=0;i<page_questions.size();i++){
	                  						Vector vector1=(Vector)page_questions.elementAt(i);
	                  						vQuestions.addElement(vector1);
	                  	
	                  				}
                           				StAloneTestXHtmlStructure s=new StAloneTestXHtmlStructure();
							String strHTML = s.generateHTML(page_questions,strButton,duration,true,total_ques,ques_per_page,total_time_left,no_question_retrived,no_question_available,serial_no,AllTotalTime,assessment_id,vQuesIdAns,AssessmentTitle,refreshassessment,timeparam);
							out.println(strHTML); 
                           		}
                           		else{
						System.out.println("=================middle====1===");
                           				strButton +="\n<input class=\"sbttn\" name=\"add1\" tabIndex=\"2\" title=\"Previous Page\" type=\"button\" value=\"Previous Page\" onclick=\"Previous_onclick()\"/>"; 
                           				strButton +="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>";
							strButton +="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Submit\" type=\"button\" value=\"Preview\" onclick=\"submitPage()\"/>"; 
                           	
                           				for(int i=0;i<page_questions.size();i++){
	                  						Vector vector1=(Vector)page_questions.elementAt(i);
	                  						vQuestions.addElement(vector1);
	                  	
	                  				}
                           				StAloneTestXHtmlStructure s=new StAloneTestXHtmlStructure();
							String strHTML = s.generateHTML(page_questions,strButton,duration,true,total_ques,ques_per_page,total_time_left,no_question_retrived,no_question_available,serial_no,AllTotalTime,assessment_id,vQuesIdAns,AssessmentTitle,refreshassessment,timeparam);
                           				out.println(strHTML); 
                            		}
                      		}
                    }//end if page_questions.size()=0
                   else{
                   	 strButton ="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Ok\" type=\"button\" value=\"Ok\" onclick=\"ok_onclick()\"/>"; 
	                 StAloneTestXHtmlStructure s=new StAloneTestXHtmlStructure();
			 String strHTML = s.generateHTML(page_questions,strButton,duration,true,total_ques,ques_per_page,total_time_left,no_question_retrived,no_question_available,serial_no,AllTotalTime,assessment_id,vQuesIdAns,AssessmentTitle,refreshassessment,timeparam);
                         out.println(strHTML);
                      }
             }//end of if
             else{
             		if(vAssessmentDeffination.size()==0){
	                	
	       			questionAll=null;
	       		strButton ="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Ok\" type=\"button\" value=\"Ok\" onclick=\"ok_onclick()\"/>"; 
	                 
	       		StAloneTestXHtmlStructure s=new StAloneTestXHtmlStructure();
			String strHTML = s.generateHTML(questionAll,strButton,duration,true,total_ques,ques_per_page,total_time_left,no_question_retrived,no_question_available,serial_no,AllTotalTime,assessment_id,vQuesIdAns,AssessmentTitle,refreshassessment,timeparam);
                        out.println(strHTML);
	       		}
             }
          }//end previous_int if
	  		  
			  
 else if((previous_int!=1) && preview_param==null){
	 System.out.println("===else if((previous_int!=1) && preview_param==null)===");
		int total_ques1=Integer.parseInt(total_ques);
		int ques_per_page1=Integer.parseInt(ques_per_page);
		
        	if(vQuestions.size()==total_ques1 || (obj.size()<total_ques1 && obj.size()==vQuestions.size()))
        	{
        		int re=obj.size()%ques_per_page1;
			
			 int count=0;
            		 if(re!=0){
            			count=ques_per_page1+re;
            		}
            		else{
            		
            				count=2*ques_per_page1;
            		
            		}
			int count1=vQuestions.size()-count;
            		Vector vTemp=new Vector();
            		for(int i=count1;i<ques_per_page1+count1;i++){
	              		Vector vector2=(Vector)vQuestions.elementAt(i);
	              		vTemp.addElement(vector2);
	    		}
			serial_no=count1; 
	    		no_question_retrived=no_question_retrived-count;  
	    		
	    		if(total_ques1>2*ques_per_page1)
	    		{
	    			strButton ="\n<input class=\"sbttn\" name=\"add1\" tabIndex=\"2\" title=\"Previous Page\" type=\"button\" value=\"Previous Page\" onclick=\"Previous_onclick()\"/>"; 
                		strButton +="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>"; 
             
	    		}
	    		else{        			
	    			strButton ="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>"; 
            		}
	    		
	    		int length1=vQuestions.size();                		
	    		for(int j=0;j<length1-count1;j++){
	    			vQuestions.removeElementAt(count1);
	    		}
        		int length2=page_questions.size();
	    		for(int k=0;k<length2;k++){
	    			page_questions.removeElementAt(0);
	    		}
	   
	    		for(int i=0;i<vTemp.size();i++){
	             		Vector vector1=(Vector)vTemp.elementAt(i);
	             		page_questions.addElement(vector1);
	             		vQuestions.addElement(vector1);
	                  	
	     		} 
			StAloneTestXHtmlStructure s=new StAloneTestXHtmlStructure();
			String strHTML = s.generateHTML(page_questions,strButton,duration,true,total_ques,ques_per_page,total_time_left,no_question_retrived,no_question_available,serial_no,AllTotalTime,assessment_id,vQuesIdAns,AssessmentTitle,refreshassessment,timeparam);
                        out.println(strHTML); 
        		
        	}
        	else{
			int count1=vQuestions.size()-2*ques_per_page1;
            		Vector vTemp=new Vector();
            		for(int i=count1;i<ques_per_page1+count1;i++){
	              		Vector vector2=(Vector)vQuestions.elementAt(i);
	              		vTemp.addElement(vector2);
	    		}
	    		serial_no=count1;
	    		no_question_retrived=no_question_retrived-ques_per_page1;  
	    		no_question_retrived=count1;
	    		String firstElement_obj=obj.firstElement().toString();//nayna
                	String firstElement_page_questions=vTemp.firstElement().toString();//nayna
                	
	    	
	    		if(!firstElement_obj.equals(firstElement_page_questions))
	    		{
                      	   	
	    			strButton ="\n<input class=\"sbttn\" name=\"add1\" tabIndex=\"2\" title=\"Previous Page\" type=\"button\" value=\"Previous Page\" onclick=\"Previous_onclick()\"/>"; 
                		strButton +="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>"; 
             
	    		}
	    		else{        			
	    			strButton ="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>"; 
           		}
            		int length1=vQuestions.size();                		
	    		for(int j=0;j<length1-count1;j++){
	    			vQuestions.removeElementAt(count1);
	    		}
	    		int length2=page_questions.size();
	        	for(int k=0;k<length2;k++){
	    			page_questions.removeElementAt(0);
	        	}
	    	
	    		for(int i=0;i<vTemp.size();i++){
	             		Vector vector1=(Vector)vTemp.elementAt(i);
	             		page_questions.addElement(vector1);
	             		vQuestions.addElement(vector1);
	                  	
	     		} 
			StAloneTestXHtmlStructure s=new StAloneTestXHtmlStructure();
			String strHTML = s.generateHTML(page_questions,strButton,duration,true,total_ques,ques_per_page,total_time_left,no_question_retrived,no_question_available,serial_no,AllTotalTime,assessment_id,vQuesIdAns,AssessmentTitle,refreshassessment,timeparam);
                        out.println(strHTML);
        	}
           } //end previous_int else */
                         
	  
			                          
			   
	else{
		System.out.println("===else===");
		   int total_ques1=Integer.parseInt(total_ques);
		   int ques_per_page1=Integer.parseInt(ques_per_page);
			
		   
		   if(vQuestions.size()==total_ques1 || (obj.size()<total_ques1 && obj.size()==vQuestions.size()))
		   {
			   int re=obj.size()%ques_per_page1;
			
			   int count=0;
			   if(re==0){
				   count=ques_per_page1+re;
			   }
			   
			   else{
            		
				   count=2*ques_per_page1;
            		
			   }
			   int count1=vQuestions.size()-count;
			   Vector vTemp=new Vector();
			   for(int i=count1;i<ques_per_page1+count1;i++){
				   Vector vector2=(Vector)vQuestions.elementAt(i);
				   vTemp.addElement(vector2);
			   }
			   serial_no=count1; 
			   no_question_retrived=no_question_retrived-count;  
	    		
			   if(total_ques1>2*ques_per_page1)
			   {
				    strButton ="\n<input class=\"sbttn\" name=\"add1\" tabIndex=\"2\" title=\"Previous Page\" type=\"button\" value=\"Previous Page\" onclick=\"Previous_onclick()\"/>"; 
				   strButton +="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>"; 
             
			   }
			   else{
				   strButton ="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Submit\" type=\"button\" value=\"Preview\" onclick=\"submitPage()\"/>";  
			   }
	    		
			   int length1=vQuestions.size();                		
			   for(int j=0;j<length1-count1;j++){
				   vQuestions.removeElementAt(count1);
			   }
			   int length2=page_questions.size();
			   for(int k=0;k<length2;k++){
				   page_questions.removeElementAt(0);
			   }
	   
			   for(int i=0;i<vTemp.size();i++){
				   Vector vector1=(Vector)vTemp.elementAt(i);
				   page_questions.addElement(vector1);
				   vQuestions.addElement(vector1);
	                  	
			   } 
			   StAloneTestXHtmlStructure s=new StAloneTestXHtmlStructure();
			   String strHTML = s.generateHTML(page_questions,strButton,duration,true,total_ques,ques_per_page,total_time_left,no_question_retrived,no_question_available,serial_no,AllTotalTime,assessment_id,vQuesIdAns,AssessmentTitle,refreshassessment,timeparam);
			   out.println(strHTML); 
        		
		   }
		   else{
			   int count1=vQuestions.size()-2*ques_per_page1;
			   Vector vTemp=new Vector();
			   for(int i=count1;i<ques_per_page1+count1;i++){
				   Vector vector2=(Vector)vQuestions.elementAt(i);
				   vTemp.addElement(vector2);
			   }
			   serial_no=count1;
			   no_question_retrived=no_question_retrived-ques_per_page1;  
			   no_question_retrived=count1;
			   String firstElement_obj=obj.firstElement().toString();//nayna
			   String firstElement_page_questions=vTemp.firstElement().toString();//nayna
                	
	    	
			   if(!firstElement_obj.equals(firstElement_page_questions))
			   {
                      	   	
				   strButton ="\n<input class=\"sbttn\" name=\"add1\" tabIndex=\"2\" title=\"Previous Page\" type=\"button\" value=\"Previous Page\" onclick=\"Previous_onclick()\"/>"; 
				   strButton +="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>"; 
             
			   }
			   else{        			
				   strButton ="\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Submit\" type=\"button\" value=\"Preview\" onclick=\"submitPage()\"/>";  
			   }
			   int length1=vQuestions.size();                		
			   for(int j=0;j<length1-count1;j++){
				   vQuestions.removeElementAt(count1);
			   }
			   int length2=page_questions.size();
			   for(int k=0;k<length2;k++){
				   page_questions.removeElementAt(0);
			   }
	    	
			   for(int i=0;i<vTemp.size();i++){
				   Vector vector1=(Vector)vTemp.elementAt(i);
				   page_questions.addElement(vector1);
				   vQuestions.addElement(vector1);
	                  	
			   } 
			   StAloneTestXHtmlStructure s=new StAloneTestXHtmlStructure();
			   String strHTML = s.generateHTML(page_questions,strButton,duration,true,total_ques,ques_per_page,total_time_left,no_question_retrived,no_question_available,serial_no,AllTotalTime,assessment_id,vQuesIdAns,AssessmentTitle,refreshassessment,timeparam);
			   out.println(strHTML);
		   }
	} //end previous_int else */		   
			   
			   
	
  }//end of doGet	       


 public Vector getResponses(Vector vAns){
 
 	Vector v = new Vector();
 	for (int i=0;i<vAns.size(); ++i) {
 	    	 Vector v1 = (Vector)vAns.elementAt(i);
 	     	for (int m=0;m<v1.size(); m++){
 	      		v.addElement(v1.elementAt(m));
 	    	}
 	}
        return v;    
  }
 public  Vector getQuestionsPerPage(Vector questionAll,int no_question_retrived,int no_question_per_page){
        
            Vector v = new Vector();
            int m=0;
            for (int i=no_question_retrived;i<questionAll.size(); i++) {
       			v.addElement(questionAll.elementAt(i));
                	m=m+1;
              		if(no_question_per_page==m){
		     		break;
		         
		      	}
         	}
		System.out.println("=================test6====v========"+v);
        return v;    
 }

public void doPost(HttpServletRequest request, HttpServletResponse response)
        	throws IOException, ServletException {

		doGet(request, response);
	}
 }	    	
	