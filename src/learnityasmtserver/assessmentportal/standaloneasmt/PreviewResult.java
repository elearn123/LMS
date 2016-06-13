package learnityasmtserver.assessmentportal.standaloneasmt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import learnityasmtserver.assessmentportal.utility.TRExtension;

import org.apache.ecs.html.B;
import org.apache.ecs.html.BR;
import org.apache.ecs.html.Body;
import org.apache.ecs.html.Caption;
import org.apache.ecs.html.Center;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.Em;
import org.apache.ecs.html.Font;
import org.apache.ecs.html.Form;
import org.apache.ecs.html.HR;
import org.apache.ecs.html.Head;
import org.apache.ecs.html.Html;
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.Link;
import org.apache.ecs.html.NOBR;
import org.apache.ecs.html.Script;
import org.apache.ecs.html.TBody;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;
import org.grlea.log.SimpleLogger;





public class PreviewResult extends HttpServlet 
{
	 public static final SimpleLogger log = new SimpleLogger(PreviewResult.class, true);// Create a SimpleLogger:
	 private static final String SESSION_MODEL_ANS = "SESSION_MODEL_ANS";
	 private static final String SESSION_MODEL_ANS1 = "SESSION_MODEL_ANS1";
	 private static final String SESSION_MODEL_PER_PAGE = "SESSION_MODEL_PER_PAGE";
	 private static final String SESSION_MODEL_QUESTIONS = "SESSION_MODEL_QUESTIONS";
	 private static final String SESSION_MODEL_PROGRESS_QUESTIONS = "SESSION_MODEL_PROGRESS_QUESTIONSS";
	 private static final String SESSION_MODEL_TIME="SESSION_MODEL_TIME";
	 private static final String SESSION_TEMPANS = "SESSION_TEMPANS";
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException
	 {
	 	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		response.setHeader("Pragma", "no-cache");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);
	        HttpSession mysession=request.getSession(true);
		String student_id = (String)mysession.getAttribute("student_id");
		log.debug("student_id=doget="+student_id);

		if(student_id!=null) {
			getResult(request, response, out,student_id);
		}
	}
public void getResult(HttpServletRequest request, HttpServletResponse response, PrintWriter out,String student_id)
    		throws IOException, ServletException {


		HttpSession mysession=request.getSession(true);
		String total_ques=request.getParameter("total_ques");
		String model_id=request.getParameter("assessment_id1");
		
		/*===========================================================================================*/
		///			Session Values						//////
		/*===========================================================================================*/
		Vector vSessionQuestions =new Vector();
		vSessionQuestions = (Vector) mysession.getAttribute(SESSION_MODEL_QUESTIONS);
		Vector vSessionAns=(Vector)mysession.getAttribute(SESSION_MODEL_ANS1);
		Vector vSessionQuestions1 =new Vector();
		vSessionQuestions1 = (Vector) mysession.getAttribute(SESSION_MODEL_PROGRESS_QUESTIONS);
		
		int ques_no=0;
		int attempt_count=0;
		int notattempt_count=0; 
		
		
		Vector allobj =new Vector();
		if (vSessionQuestions == null) 
			allobj = vSessionQuestions1;
		else 
			allobj = vSessionQuestions;
		
		
	        Vector v = null;
		if (allobj.size() == 0) 
		{
			v = new Vector();
		}
		else 
		{
			v= allobj;
		}
		Vector vTempAns = (Vector) mysession.getAttribute(SESSION_TEMPANS);
		
	         
	        String total_no_of_question_retrived=request.getParameter("total_no_of_question_retrived");
		System.out.println("======total_no_of_question_retrived=======3333==="+total_no_of_question_retrived);
	        String ques_per_page=request.getParameter("ques_per_page");
		
		
	        String total_time=request.getParameter("duration");
	        int no_question_available = Integer.parseInt(total_no_of_question_retrived);
	        String model_test_name=request.getParameter("AssessmentTitle");
		String serial_no=request.getParameter("serial_no");
		String no_of_question_retrived=request.getParameter("no_of_question_retrived");
		String total_time_left=request.getParameter("total_time_left");
		String timeparam=request.getParameter("disp2");
		System.out.println("timeparam=======3========"+timeparam);
		
		int duration=0;
		
		String strButton ="";
		String returnValue1=request.getParameter("returnValue1");
		if((returnValue1==null) || (returnValue1.equals("")))
			returnValue1="0";
		
		String answered_ques="";
		String not_answered_ques="";
	       
		int No_Ques =0;
	        int no_question_per_page=0;
	       
	        int Total_No_Ques=0;
		if(total_ques!=null && !total_ques.equals(""))
		{
	             Total_No_Ques = Integer.parseInt(total_ques);
	        }
	        
	       if(total_no_of_question_retrived!=null && !total_no_of_question_retrived.equals(""))
	        {
	                No_Ques = Integer.parseInt(total_no_of_question_retrived);
	        }
	        if(ques_per_page != null && !ques_per_page.equals(""))
	        {
			no_question_per_page = Integer.parseInt(ques_per_page);
		}
		else
	        {
	                no_question_per_page = No_Ques;
	 } 
	        Calendar calendar = new GregorianCalendar(); 
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		String months[]={"January","Feburary","March", "April", "May","June",
							"July","August","September","October","November","December"};
		
	      String strTime = calendar.get(Calendar.HOUR)+":"+ calendar.get(Calendar.MINUTE)+":"+ calendar.get(Calendar.SECOND); 
	      String strDate = months[calendar.get(Calendar.MONTH)]+" "+ calendar.get(Calendar.DATE)+", "+ calendar.get(Calendar.YEAR); 
	  
	   Vector vectorAns = new Vector();
	   Vector vAns = new Vector();
	   
	   Vector vTempQuestionAns = new Vector();
	   Vector vQAns = new Vector();
	   for (int i = 0; i < allobj.size(); ++i) {
		   Vector questionMatter = (Vector) allobj.elementAt(i);
		   Vector res = (Vector) questionMatter.elementAt(0);
		   Vector question = (Vector) questionMatter.elementAt(1);
		   Integer iTitle = (Integer) res.elementAt(0);
		   String strQuesNo = "";
		   String str = "";
		   
		   switch (iTitle.intValue()) {
			   case 0 :
				   
				   strQuesNo = (String) question.elementAt(0);
				   vQAns.addElement(strQuesNo);
				   str = request.getParameter(strQuesNo);
				
				   vQAns.addElement(str);
				   vectorAns.addElement(str);
				   if(str==null)
					   str="";

				   ques_no=ques_no+1;
				   if((str.equals("null")) || str.equals(""))
				   {
					   not_answered_ques=not_answered_ques+"&nbsp;&nbsp;<a href=javascript:go_anchor("+ques_no+")> "+ques_no+"</a>";
						
					   notattempt_count=notattempt_count+1;
				   }
				   else
				   {
					   answered_ques=answered_ques+"&nbsp;&nbsp;<a href=javascript:go_anchor("+ques_no+")> "+ques_no+"</a>";
						
					   attempt_count=attempt_count+1;
				   }   
				   
				   break;
			   case 1 :
				   strQuesNo = (String) question.elementAt(0);
				   vQAns.addElement(strQuesNo);
				   String str1[] = request.getParameterValues(strQuesNo);
			         
				   vQAns.addElement(str1);
				   vectorAns.addElement(str1);
				  
				   ques_no=ques_no+1;
				   
				   if(str1==null)
				   {
					   not_answered_ques=not_answered_ques+"&nbsp;&nbsp;<a href=javascript:go_anchor("+ques_no+")> "+ques_no+"</a>";
						
					   notattempt_count=notattempt_count+1;
				   }
				   else
				   {
					   answered_ques=answered_ques+"&nbsp;&nbsp;<a href=javascript:go_anchor("+ques_no+")> "+ques_no+"</a>";
						
					   attempt_count=attempt_count+1;
				   }   
				   break;
			   case 2 :
				   strQuesNo = (String) question.elementAt(0);
				   vQAns.addElement(strQuesNo);
				   str = request.getParameter(strQuesNo);
				   vQAns.addElement(str);
				   vectorAns.addElement(str);
				   if(str==null)
					   str="";
				  ques_no=ques_no+1;
				   if((str.equals("null")) || str.equals(""))
				   {
					   not_answered_ques=not_answered_ques+"&nbsp;&nbsp;<a href=javascript:go_anchor("+ques_no+")> "+ques_no+"</a>";
						
					   notattempt_count=notattempt_count+1;
				   }
				   else
				   {
					   answered_ques=answered_ques+"&nbsp;&nbsp;<a href=javascript:go_anchor("+ques_no+")> "+ques_no+"</a>";
						
					   attempt_count=attempt_count+1;
				   }   

				   break;
			   case 3 :
				   strQuesNo = (String) question.elementAt(0);
				   vQAns.addElement(strQuesNo);
				   str = request.getParameter(strQuesNo);
				   vQAns.addElement(str);
				   vectorAns.addElement(str);
				   if(str==null)
					   str="";

				   ques_no=ques_no+1;
				   if((str.equals("null")) || str.equals(""))
				   {
					   not_answered_ques=not_answered_ques+"&nbsp;&nbsp;<a href=javascript:go_anchor("+ques_no+")> "+ques_no+"</a>";
						
					   notattempt_count=notattempt_count+1;
				   }
				   else
				   {
					   answered_ques=answered_ques+"&nbsp;&nbsp;<a href=javascript:go_anchor("+ques_no+")> "+ques_no+"</a>";
						
					   attempt_count=attempt_count+1;
				   }   
				   
				   break;
			    case 4 :
				   Vector vOption = (Vector) question.elementAt(2);
      				   Vector leftoption = (Vector) vOption.elementAt(0);
				   Vector vector1=new Vector();
				   String l1[]=new String[leftoption.size()];
				   String r1[]=new String[leftoption.size()];
				   strQuesNo = (String) question.elementAt(0);
				   vQAns.addElement(strQuesNo);
				    boolean b = false; 
				   String alp[]={"A","B","C","D","E","F","G","H","I","J"};
				   String alp1[]={"L1","L2","L3","L4","L5","L6","L7","L8","L9","L10"};
				   String alp2[]={"R1","R2","R3","R4","R5","R6","R7","R8","R9","R10"};
				   for(int i22=0; i22<leftoption.size(); i22++) 
	    			   {
					log.debug("===b==1======="+b);
				   	String noalp=alp[i22];
          			   	String type1="l"+strQuesNo+noalp; 
				   	String type2="r"+strQuesNo+noalp; 
					String str11 = request.getParameter(type1);
					log.debug("===str11========="+str11);
					String str21 = request.getParameter(type2);
					log.debug("===str21========="+str21);
					l1[i22]=str11;
					 r1[i22]=str21;
					if(!str11.equals("0")) {
						 b = true; 
						 log.debug("===b==2======="+b);
						// l1[i22]=str11;
					}
					if(!str21.equals("0")) {
						 b = true; 
						 log.debug("===b==3======="+b);
						 //r1[i22]=str21;
					}
					log.debug("===b==4======="+b);
				   }
				   vector1.addElement(l1);
				   vector1.addElement(r1);
				   log.debug("===b==5======="+b);
				   ques_no=ques_no+1;
				   if(b==true) {
					 answered_ques=answered_ques+"&nbsp;&nbsp;<a href=javascript:go_anchor("+ques_no+")> "+ques_no+"</a>";
					 log.debug("===b==6======="+b);	
					 attempt_count=attempt_count+1;
				   } 
				   else
				   {
					 not_answered_ques=not_answered_ques+"&nbsp;&nbsp;<a href=javascript:go_anchor("+ques_no+")> "+ques_no+"</a>";
					 log.debug("===b==7======="+b);	
					 notattempt_count=notattempt_count+1;
				   }
				   vQAns.addElement(vector1);
				   vectorAns.addElement(vector1);
				
				   break;
			        
		   }//end of switch case
		   
	   }//end of for
	   vAns.addElement(vectorAns);
	   log.debug("===vAns======"+vAns);
	   mysession.removeAttribute(SESSION_MODEL_ANS1);
	   mysession.setAttribute(SESSION_MODEL_ANS1, vQAns);
	   
	   
	 mysession.setAttribute(SESSION_MODEL_ANS, vAns);
	 
	 if(total_time_left!=null && !total_time_left.equals(""))
	 {
		 duration = Integer.parseInt(total_time_left);
	 }
	 else
	 {
		 duration = No_Ques;
	 }
	 
	 String checkModelstatus="checkModelstatus";
	 
	 
	 
	 
	 String scriptfortime="\n //<![CDATA["+ 
			 "\n var timeleft"+
			 "\n var timeleftr"+
			 "\n var up,down"+
			 "\n   var min1,sec1"+
			 "\n  var cmin1,csec1,cmin2,csec2"+
			 "\n function Quit_onclick() {"+
			 "\n 		if( confirm(\"Do you wish to Cancel The Test?\") ) {"+
			 "\n 		window.opener.location.reload();"+
			 "\n 			window.parent.close(); "+
			 "\n 		}"+
			 "\n 	}"+
			 "\n function Minutes(data) {"+
			 "\n  for(var i=0;i<data.length;i++)"+ 
			 "\n if(data.substring(i,i+1)==\":\")"+ 
			 "\n break"+
			 "\n  return(data.substring(0,i)); }"+
			 "\n  function Seconds(data) {"+
			 "\n for(var i=0;i<data.length;i++)"+ 
			 "\n if(data.substring(i,i+1)==\":\")"+ 
			 "\n break"+
			 "\n  return(data.substring(i+1,data.length)); }"+
			 "\n function Display(min,sec) {"+
			 "\n   var disp;"+
			 "\n if(min<=9) disp=\" 0\";"+
			 "\n else disp=\" \";"+
			 "\n disp+=min+\":\";"+
			 "\n if(sec<=9) disp+=\"0\"+sec;"+
			 "\n else disp+=sec;"+
			 "\n return(disp); }"+
			 "\n function Up() {"+
			 "\n  cmin1=0;"+
			 "\n csec1=0;"+
			 "\n min1=0+Minutes(document.getElementById('frm').beg1.value);"+
			 "\n sec1=0+Seconds(document.getElementById('frm').beg1.value);"+
			 "\n UpRepeat(); }"+
			 "\n function UpRepeat() {"+
			 "\n    csec1++;"+
			 "\n    if(csec1==60) { csec1=0; cmin1++; }"+
			 "\n        document.getElementById('frm').disp1.value=Display(cmin1,csec1);"+
			 "\n  up=setTimeout(\"UpRepeat()\",1000); }"+
			 "\n var lefttime=\""+duration+"\";"+
			 "\n function Down(lefttime) {"+
			 "\n timeleftr=lefttime"+
			 "\n countdown()"+
			 "\n cmin2=1*Minutes(document.getElementById('frm').disp2.value);"+
			 "\n  csec2=0+Seconds(document.getElementById('frm').disp2.value);"+
			 "\n DownRepeat(); }"+
			 "\n function countdown()"+                   
			 "\n{"+
			 "\n sb();"+
			 "\n  counter();"+
			 "\n }"+
			 "\n function sb()"+
			 "\n{"+
			 "\n if (timeleftr>0)"+
// 			 "\n {                   timeleft =Math.round(timeleftr)"+
			 "\n {                   timeleft =timeleftr;"+
			 "\n  defaultStatus=\"TIME LEFT   : \"+timeleft+\" MINUTES\";"+
			 "\n setTimeout(\"sb()\",300);"+
			 "\n}"+
			 "\n else"+
			 "\n {"+
			 "\n  alert(\"The Time Alloted for this Test has Elapsed. Please Click on OK button to submit your Test.\")"+
			 "\n submitPage1()"+	
			 "\n }"+
			 "\n  }"+
			 "\n function counter()"+
			 "\n{"+
			 "\n timeleftr=timeleftr - 0.25;"+
			 "\n setTimeout(\"counter()\",15000);"+
			 "\n }"+
			 "\n function DownRepeat() {"+
			 "\n csec2--;"+
			 "\n if(csec2==-1) { csec2=59; cmin2--; }"+
			 "\n document.getElementById('frm').disp2.value=Display(cmin2,csec2);"+
			 "\n  if((cmin2==0)&&(csec2==0)) document.getElementById('frm').disp2.value=Display(cmin2,csec2);"+
			 "\n else"+
			 "\n down=setTimeout(\"DownRepeat()\",1000); }"+
			 
			 "\n	var browsername=navigator.appName;"+
			 "\n	var browsername=navigator.appName;"+
			 "\n  function click(e) "+
			 "\n  {"+
			 
			 "\n    if(browsername==\"Microsoft Internet Explorer\") {"+
			 "\n			if (event.button == 2 || event.button==3) {"+
			 "\n					alert(\"Operation not allowed\");"+
			 "\n					return false;"+
			 "\n				}"+
			 "\n			}"+
			 "\n    if(browsername==\"Netscape\") {"+
			 "\n			if (e.which == 2 || e.which == 3) {"+
			 "\n					alert(\"Operation not allowed\");"+
			 "\n					return false;"+
			 "\n				}"+
			 "\n			}"+
			 "\n  }"+
			 "\n  if (browsername==\"Netscape\") "+
			 "\n  {"+
			// "\n	document.captureEvents(Event.MOUSEDOWN);"+
			 //"\n    document.onclick=click;"+
			 "\n  }"+
			 "\n  if (browsername==\"Microsoft Internet Explorer\") "+
			 "\n  {"+
			 //"\n  document.onmousedown=click;"+
			 "\n  }"+
			 
			 "\n  function trapKey() {"+
			 
			 //"\n         alert(browsername);"+
			 "\n    if(browsername==\"Microsoft Internet Explorer\") {"+
			 "\n         alert(\"Operation not allowed\");"+
			 "\n         return false;"+
			 "\n         event.cancelBubble=true;"+
			 "\n			}"+
			 "\n    if(browsername==\"Netscape\") {"+
			 "\n			alert(\"Operation not allowed\");"+
			 "\n			return false;"+
			 "\n			}"+
			 "\n   }"+
			 "\n    if(browsername==\"Netscape\") {"+
			// "\n   document.captureEvents(Event.KEYPRESS);"+
			// "\n  document.onkeypress=trapKey;"+
			 "\n			}"+
			 "\n    if(browsername==\"Microsoft Internet Explorer\") {"+
			// "\n  document.onkeydown=trapKey;"+
			 "\n			}"+
			 
			 
			 "\n  //]]>";
	 
	 
			 String javaScript =
					
					"\n function submit_onclick() {"+
					"\n  	var browserName = navigator.appName;"+
                                	"\n  	var browserVersion = parseInt(navigator.appVersion);"+
					"\n	doyou = confirm(\"Are you sure to submit the test\"); //Your question."+
					"\n	if (doyou == true) {"+
					"\n		if(browserName == \"Microsoft Internet Explorer\" && browserVersion >=4) {"+
					//"\n			alert(\"ie\");"+
					"\n			document.getElementById('frm').method=\"post\";"+
					"\n			document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.StResultSectionHTML?total_ques="+total_ques+"&AssessmentTitle="+model_test_name+"&ques_per_page="+ques_per_page+"&no_of_question_retrived="+no_of_question_retrived+"&total_no_of_question_retrived="+total_no_of_question_retrived+"&duration="+total_time+"&serial_no="+serial_no+"&total_time="+total_time+"&assessment_id1="+model_id+"\";"+
					"\n			document.getElementById('frm').target=\"_self\";"+
					"\n			document.getElementById('frm').submit();"+
					"\n		}"+
					"\n		else{"+
					//"\n			alert(\"firefox\");"+
					"\n			document.getElementById('frm').method=\"post\";"+
					"\n			document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.StResultSection?total_ques="+total_ques+"&AssessmentTitle="+model_test_name+"&ques_per_page="+ques_per_page+"&no_of_question_retrived="+no_of_question_retrived+"&total_no_of_question_retrived="+total_no_of_question_retrived+"&duration="+total_time+"&serial_no="+serial_no+"&total_time="+total_time+"&assessment_id1="+model_id+"\";"+
					"\n			document.getElementById('frm').target=\"_self\";"+
					"\n			document.getElementById('frm').submit();"+
					"\n		}"+
					"\n	}"+
					"\n    else {"+
					"\n	}"+
					"\n 	}"+
					"\n  function submitPage1() {"+
					"\n  	var browserName = navigator.appName;"+
                                	"\n  	var browserVersion = parseInt(navigator.appVersion);"+
					"\n		if(browserName == \"Microsoft Internet Explorer\" && browserVersion >=4) {"+
					//"\n			alert(\"ie\");"+
					"\n			document.getElementById('frm').method=\"post\";"+
					"\n			document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.StResultSectionHTML?total_ques="+total_ques+"&AssessmentTitle="+model_test_name+"&ques_per_page="+ques_per_page+"&total_time_left=\"+timeleft+\"&no_of_question_retrived="+no_of_question_retrived+"&total_no_of_question_retrived="+total_no_of_question_retrived+"&duration="+duration+"&serial_no="+serial_no+"&total_time="+total_time+"&assessment_id1="+model_id+"\";"+
					"\n			document.getElementById('frm').target=\"_self\";"+
					"\n			document.getElementById('frm').submit();"+
					"\n		}"+
					"\n		else{"+
					//"\n			alert(\"firefox\");"+
					"\n			document.getElementById('frm').method=\"post\";"+
					"\n			document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.StResultSection?total_ques="+total_ques+"&AssessmentTitle="+model_test_name+"&ques_per_page="+ques_per_page+"&total_time_left=\"+timeleft+\"&no_of_question_retrived="+no_of_question_retrived+"&total_no_of_question_retrived="+total_no_of_question_retrived+"&duration="+duration+"&serial_no="+serial_no+"&total_time="+total_time+"&assessment_id1="+model_id+"\";"+
					"\n			document.getElementById('frm').target=\"_self\";"+
					"\n			document.getElementById('frm').submit();"+
					"\n		}"+
					"\n	}"+
					"\n function go_anchor(anchor) {"+
					"\n		var n = anchor;"+
					"\n	var checkModelstatus = \""+checkModelstatus+"\";"+
					"\n 		timeleft1 =Math.round(timeleftr)"+
// 					 "\n 		timeleft1 =timeleftr;"+
					"\n  	var browserName = navigator.appName;"+
                                	"\n  	var browserVersion = parseInt(navigator.appVersion);"+
					"\n		if(browserName == \"Microsoft Internet Explorer\" && browserVersion >=4) {"+
					"\n			document.getElementById('frm').method=\"post\";"+
					"\n			document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.GenerateTestHTML?checkModelstatus=\"+checkModelstatus+\"&AssessmentTitle="+model_test_name+"&preview_param=previewparam&previous=1&assessment_id="+model_id+"&no_of_question_retrived="+no_of_question_retrived+"&total_no_of_question_retrived="+total_no_of_question_retrived+"&serial_no=0&total_ques="+total_ques+"&duration="+duration+"&total_time="+total_time+"&ques_per_page="+ques_per_page+"&total_time_left=\"+timeleft1+\"#\"+n+\"\";"+
					"\n			document.getElementById('frm').target=\"_self\";"+
					"\n			document.getElementById('frm').submit();"+
					"\n		}"+
					"\n		else{"+
					"\n			document.getElementById('frm').method=\"post\";"+
					 "\n			document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.GenerateTestXHTML?checkModelstatus=\"+checkModelstatus+\"&AssessmentTitle="+model_test_name+"&preview_param=previewparam&previous=1&assessment_id="+model_id+"&no_of_question_retrived="+no_of_question_retrived+"&total_no_of_question_retrived="+total_no_of_question_retrived+"&serial_no=0&total_ques="+total_ques+"&duration="+duration+"&total_time="+total_time+"&ques_per_page="+ques_per_page+"&total_time_left=\"+timeleft1+\"#\"+n+\"\";"+
					"\n			document.getElementById('frm').target=\"_self\";"+
					"\n			document.getElementById('frm').submit();"+
					"\n		}"+
					"\n	}"+
					 "\n function cancel_onclick() {"+
					 //"\n		var n = anchor;"+
					 "\n	var checkModelstatus = \""+checkModelstatus+"\";"+
					 "\n 		timeleft1 =Math.round(timeleftr)"+
// 					 "\n 		timeleft1 =timeleftr;"+
					"\n  	var browserName = navigator.appName;"+
                                	"\n  	var browserVersion = parseInt(navigator.appVersion);"+
					"\n		if(browserName == \"Microsoft Internet Explorer\" && browserVersion >=4) {"+
					 "\n			document.getElementById('frm').method=\"post\";"+
					 "\n			document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.GenerateTestHTML?checkModelstatus=\"+checkModelstatus+\"&AssessmentTitle="+model_test_name+"&preview_param=previewparam&previous=1&assessment_id="+model_id+"&no_of_question_retrived="+no_of_question_retrived+"&total_no_of_question_retrived="+total_no_of_question_retrived+"&serial_no=0&total_ques="+total_ques+"&duration="+duration+"&total_time="+total_time+"&ques_per_page="+ques_per_page+"&total_time_left=\"+timeleft1+\"#"+returnValue1+"\";"+
					 "\n			document.getElementById('frm').target=\"_self\";"+
					 "\n			document.getElementById('frm').submit();"+
					"\n		}"+
					"\n		else{"+
					 "\n			document.getElementById('frm').method=\"post\";"+
					 "\n			document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.GenerateTestXHTML?checkModelstatus=\"+checkModelstatus+\"&AssessmentTitle="+model_test_name+"&preview_param=previewparam&previous=1&assessment_id="+model_id+"&no_of_question_retrived="+no_of_question_retrived+"&total_no_of_question_retrived="+total_no_of_question_retrived+"&serial_no=0&total_ques="+total_ques+"&duration="+duration+"&total_time="+total_time+"&ques_per_page="+ques_per_page+"&total_time_left=\"+timeleft1+\"#"+returnValue1+"\";"+
					 "\n			document.getElementById('frm').target=\"_self\";"+
					 "\n			document.getElementById('frm').submit();"+
					 "\n		}"+
					 "\n	}"+  
					"\n function Quit_onclick() {"+
					"\n 		window.print();"+
					"\n 	}";   
			 Script script2 = new Script().setLanguage("javascript").addElement(scriptfortime);
                Html html1 = new Html();
			
		Head head1 = new Head();
		Form form = new Form().setName("frm").setMethod("post");
		form.setID("frm");
		Link Link=new Link();
		Link link2=new Link();
		Script script1=new Script();
		Script script12=new Script();
		Link.setHref("../coreadmin/js/Assessmentportal.css");
                link2.setHref("../coreadmin/js/calendar1.js");
		Link.setRel("stylesheet");
		Link.addElement(script1);
		//Link csslink2 = new Link();
		//csslink2.setRel("stylesheet");
		//csslink2.setHref("../coreadmin/js/stylesheet.css");
		//head1.addElement(csslink2);
		Link csslink1 = new Link();
		csslink1.setRel("stylesheet");
		csslink1.setHref("../coreadmin/js/OnlineTesting.css");
		head1.addElement(csslink1);
		Font Font1 = new Font().setSize(3);
		Font Font2 = new Font().setSize(4).setColor("#CC0000");
		Font Font3 = new Font().setSize(3);
	        Font1.addElement(new B());
		Font1.addElement(new Em()
				.addElement("Powered BY &nbsp;&nbsp;")
				);
		Font2.addElement("LearnITy&#8482;&nbsp;&nbsp;");
		Font3.addElement(new B());
		Font3.addElement(new Em()
			.addElement("Assessment Server")
			);
			head1.addElement(new Script()
					.setLanguage("JavaScript")
					.addElement(javaScript));
			head1.addElement(script2);
	    	Input InputButton11 = new Input();
	    	Input InputButton12 = new Input();
	    	Input InputButton13 = new Input();					
		
	Body body1=new Body();
	body1.setOnLoad("Down("+duration+")");
	
	Table table1 = new Table().setCellPadding(0).setCellSpacing(1).setWidth("75%");
	Table table2 = new Table().setBorder(0).setCellPadding(1).setCellSpacing(0).setWidth("75%");
	
	
	
        Table tblTopInformation = new Table()
			.setAlign("center")
        		.setBorder(0)
		        .setCellPadding(0)
		        .setCellSpacing(0)		        
		        .setWidth("100%")
		        .addElement(new Caption()
		        .setAlign("top")
            	.addElement(new B()
            	.addElement(new Font()
         		.setColor("336699")
			.addElement("<center>PREVIEW</center>"))));
                
               
	Table previewsummary=new Table();
	TBody tbodypreview = new TBody();
	TBody tbodyAsmt = new TBody();
	
	Table tblAsmtSummary = new Table()
        		.setBorder(2)
		        .setCellPadding(2)
		        .setCellSpacing(2)		        
		        .setWidth("100%")
		        .addElement(new Caption()
		        .setAlign("top")
            	.addElement(new B()
            	.addElement(new Font()
         		.setColor("587699")
            	.addElement("Test Summary"))));
            	
            	
	   
	   form.addElement(new Center()
				  .addElement(Font1)
				  .addElement(Font2)
				  .addElement(Font3)
			  ); 
	 /*  
	Vector Variable=ReportDataBaseLayer.selectname();
	String logo="IstLogo.jpg";
        if(Variable!=null) 
        {
        	for(int i=0; i<Variable.size(); i= i+2)
        	{
  	       			String srttgoia[] = (String[])Variable.elementAt(i);
	    			//loginname = srttgoia[0];
	    		  	
	    		  	logo = srttgoia[1];
				//portalname = srttgoia[2];
	
	     	}
	}*/
	  //log.debug("logo=doget="+logo);    
	  form.addElement(new Table()
        			.addElement(new TR()
        				.addElement(new TD()
						.addElement(new IMG()
        							//.setSrc("../images/IstLogo.jpg")
				 				.setSrc("../coreadmin/images/logo.gif")
								.setClass("organistaionlogo2")
								)
								
						)
        				.addElement(new TD()
        						.addElement(table1)))
                                        //.setBorder("1")												
                                          );
		table1.addElement(new TR()
			
			.addElement(new TD()
				.addElement(new NOBR()
					.addElement(new Div()
						  .addElement("&nbsp;Date:  "+strDate+"&nbsp;")
						   .setID("DateTime")
					)	
				)
			)
			.addElement(new TD()
				.addElement(new NOBR()
					.addElement(new Div()
						.addElement("&nbsp;Time: "+strTime+"&nbsp;")
						.setID("DateTime")
					)	
				)
			)
			.setClass("AssessmentSectionHeading")			
		);
		

        	
		table1.addElement(new TR()
        		.addElement(new TD()
        			.addElement(new NOBR()
        				.addElement(new Div()

						.setID("DivB")
					)	
        			)
        		)	
		);

		
		table1.addElement(new TR()
        		.addElement(new TD()
        		)	
		);
		table1.addElement(new TR()
        		.addElement(new TD()
        			.addElement(new NOBR()
        				.addElement(new NOBR()
        				.addElement("<center>Time Left:&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"hidden\" name=\"beg2\" size=\"7\"  value=\""+duration+"\"/>")
				.addElement("<input type=\"text\" name=\"disp2\" size=\"9\" value=\""+timeparam+"\"/>")
        				.addElement("&nbsp;&nbsp;&nbsp;&nbsp;minutes</center>")

        			)
        			)
        		)
        		.setClass("AssessmentSectionHeading")	
		);
			

        	
		table1.addElement(new TR()
        		.addElement(new TD()
        			.addElement(new NOBR()
        				.addElement(new Div()

						.setID("DivB")
					)	
        			)
        		)
        	);
        	
        
		table2.addElement(new TR()
        		.addElement(new TD()
        			.addElement(new HR()
        				.setSize(1)
        			)
        		)
        	); 
	   
	   
	   
            	
			   	
			  String time_started=(String) mysession.getAttribute(SESSION_MODEL_TIME);
			 
			   tbodypreview.addElement(new TRExtension().addHeaderNameForAssessment("<B>Total No. of questions : </B>",""+total_no_of_question_retrived));
			   tbodypreview.addElement(new TRExtension().addHeaderNameForAssessment("<B>No. of questions attempted : </B>",""+attempt_count));
			   tbodypreview.addElement(new TRExtension().addHeaderNameForAssessment("<B>No. of questions not attempted : </B>",""+notattempt_count));
			   
			   Table table3=new Table()
					   .setBorder(1)
					   .addElement(new TR()
					   .setVAlign("top")
						      .addElement(new TD()
					                   
					                     .addElement("<B>Attempted Questions : </B>")
					                     
							     .setClass("tdattemptedlabel"))
						      .addElement(new TD()
							     .addElement(answered_ques)
							     .setClass("tdattemptedvalue"))
						      .setClass("trattempted"))
					   .addElement(new TR()
					   .setVAlign("top")
						      .addElement(new TD()
					                 
					                     .addElement("<B>Not Attempted Question : </B>")
					                     
							     .setClass("tdnotattemptedlabel"))
						      .addElement(new TD()
							     .addElement(not_answered_ques)
							     .setClass("tdnotattemptedvalue"))
					                     .setClass("trnotattempted"));
			   table3.setClass("tablePreview");
			   
					   
			   
	previewsummary.addElement(tbodypreview);

	
	form.addElement(new Div()
	       .addElement(new BR()
		   		));
	form.addElement(tblTopInformation);
	form.addElement(new Div()
	       .addElement(new BR()
		   		));
	
	form.addElement(previewsummary);

	
	form.addElement(new Div()
	       .addElement(new BR()
		   		));
	
	form.addElement(table3);
	form.addElement(new Div()
        	  		       .addElement(InputButton12
        	  		                       .setName("Submit")
							.setType("button")
							.setValue("Submit")
							.setClass("Submit"))
					.setID("PreviewSubmit")
				) 
			.addElement(new Div()
        	  		       .addElement(InputButton13
        	  		                       .setName("Cancel")
							.setType("button")
							.setValue("Cancel")
							.setClass("Cancel"))
					.setID("PreviewCancel")
				   );    
		   
	      
	       InputButton11.setOnClick("Quit_onclick();");
	        InputButton12.setOnClick("submit_onclick();");
		InputButton13.setOnClick("cancel_onclick();");
	       head1.addElement(script12);   
	       head1.addElement(Link);  
	       head1.addElement(link2);  
	       html1.addElement(head1);
	       body1.addElement(form);            
	       html1.addElement(body1);                 
	                    
	       out.println(html1.toString());
		  
}	       

 
 
 public void doPost(HttpServletRequest request, HttpServletResponse response)
        	throws IOException, ServletException {

		doGet(request, response);
	}
 }	    	
	