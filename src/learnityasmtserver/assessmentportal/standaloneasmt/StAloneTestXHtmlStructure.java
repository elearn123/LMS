package learnityasmtserver.assessmentportal.standaloneasmt;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import learnityasmtserver.assessmentportal.dbconnection.ReportDataBaseLayer;

import org.apache.ecs.Doctype;
import org.apache.ecs.xhtml.b;
import org.apache.ecs.xhtml.body;
import org.apache.ecs.xhtml.br;
import org.apache.ecs.xhtml.center;
import org.apache.ecs.xhtml.div;
import org.apache.ecs.xhtml.em;
import org.apache.ecs.xhtml.font;
import org.apache.ecs.xhtml.form;
import org.apache.ecs.xhtml.head;
import org.apache.ecs.xhtml.hr;
import org.apache.ecs.xhtml.html;
import org.apache.ecs.xhtml.img;
import org.apache.ecs.xhtml.link;
import org.apache.ecs.xhtml.nobr;
import org.apache.ecs.xhtml.script;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.tbody;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.title;
import org.apache.ecs.xhtml.tr;
import org.grlea.log.SimpleLogger;
//import interfaceengine.*;

public class StAloneTestXHtmlStructure {
public static final SimpleLogger log = new SimpleLogger(StAloneTestXHtmlStructure.class, true);// Create a SimpleLogger:
	public String generateHTML(Vector questionAll,String strButton1,int duration,boolean bRefresh,String total_ques,String ques_per_page,String total_time_left,int no_of_question_retrived,int total_no_of_question_retrived,int serial_no,int total_time,String assessment_id1,Vector vQuesIdAns,String AssessmentTitle,String refreshassessment,String timeparam) {
		System.out.println("=========total_no_of_question_retrived===2222======"+total_no_of_question_retrived);
        Calendar calendar = new GregorianCalendar(); 
	Date trialTime = new Date();
	calendar.setTime(trialTime);
	String months[]={"January","Feburary","March", "April", "May","June",
						"July","August","September","October","November","December"};
     
	String strTime = calendar.get(Calendar.HOUR)+":"+ calendar.get(Calendar.MINUTE);
	String strDate = months[calendar.get(Calendar.MONTH)]+" "+ calendar.get(Calendar.DATE)+", "+ calendar.get(Calendar.YEAR); 
	StringBuffer strHTML = new StringBuffer();
     	

		Doctype doc1 =new Doctype("html","\"-//W3C//DTD XHTML 1.1 plus MathML 2.0//EN\"","\"http://www.w3.org/tr/MathML2/dtd/xhtml-math11-f.dtd\"");//030308
			html html1 = new html();
			html1.addAttribute("xlink","http://www.w3.org/1999/xlink");
			html1.addAttribute("math","http://www.w3.org/1998/Math/MathML");
			html1.addAttribute("xmlns","http://www.w3.org/1999/xhtml");
		head head1 = new head();
		title title1 = new title();
		link csslink = new link().setRel("stylesheet");
		link csslink1 = new link();
    			csslink1.setRel("stylesheet");
    			csslink1.setHref("../coreadmin/js/OnlineTesting.css");
    		head1.addElement(csslink1);
		script script1 = new script().setLanguage("javascript");
		link link=new link();
    		link.setHref("../coreadmin/js/Assessmentportal.css");
    		link.setRel("stylesheet");
    		 head1.addElement(link); 
		link l2=new link();
					
		body body1 = new body();
		body1.setOnKeyDown("trapKey()");
		//log.debug("duration==Down=="+duration);
		body1.setOnLoad("Down("+duration+")");
		form form1 = new form();
		form1.setMethod("Post");
		form1.setID("frm");
							
		form1.setName("frm");
		table table1 = new table().setCellPadding(0).setCellSpacing(1).setWidth("75%");
		tr tr1 = new tr();
		td td1 = new td().setWidth("50%").setHeight(23);
		td td2 = new td().setWidth("25%");
		td td3 = new td().setWidth("25%");
		
		tr tr2 = new tr().setBgColor("#336699");
		td td4 = new td().setColSpan("3").setWidth("25%");
		
		tr tr3 = new tr().setBgColor("#336699");
		td td5 = new td().setColSpan("3").setWidth("25%").setHeight(5);
		
		tr tr4 = new tr();
		td td6 = new td().setColSpan("3").setWidth("25%").setHeight(23);
		
		tr tr5 = new tr().setBgColor("#336699");
		td td7 = new td().setColSpan("3").setWidth("25%");
		
		table table2 = new table().setBorder(0).setCellPadding(1).setCellSpacing(0).setWidth("75%");
		tr tr6 = new tr();
		td td8 = new td().setColSpan("3");		
		
		table table3 = new table().setCellPadding(0).setCellSpacing(4).setWidth("100%");
		tr tr9 = new tr();
		td td14 = new td().setAlign("center"); 
		
		table table4 = new table();
		tbody tbody1 = new tbody();
		tr tr11 = new tr();
		td td19 = new td().setWidth(140);
		td td20 = new td().setWidth("100%");
		
		table table5 = new table().setBorder(0).setCellPadding(3).setCellSpacing(4);
		tr tr12 = new tr();
		td td21 = new td();
		td td22 = new td();
		td td23 = new td().setWidth(5);
		table table6 = new table().setBorder(0).setCellPadding(3).setCellSpacing(4);
		tr tr122 = new tr();
		td td212 = new td();
		td td222 = new td();
		td td232 = new td().setWidth(5);
		
		font font1 = new font().setSize(3);
		font font2 = new font().setSize(4).setColor("#CC0000");
		font font3 = new font().setSize(3);
		
		script1.setSrc("../coreadmin/js/ansAssessmen.js");
		csslink.setHref("../coreadmin/js/stylesheet.css");
		String strButton=strButton1;
		
        	
		body1.addElement(new br());
		
		body1.addElement(new center()
			.addElement(font1)
			.addElement(font2)
			.addElement(font3)
			);



		
		font1.addElement(new b());
		
		font1.addElement(new em()
			.addElement("Powered BY &nbsp;&nbsp;")
			);
		font2.addElement("LearnITy&#8482;&nbsp;&nbsp;");
		font3.addElement(new b());
		font3.addElement(new em()
			.addElement("Assessment Server")
			);
		
		
       Vector getAlrmDetails=ReportDataBaseLayer.getAlrmDetailsvector();
       String s="";
       for(int i=0;i<getAlrmDetails.size();i=i+3)
       {
         String title=(String)getAlrmDetails.elementAt(i);
         String time=(String)getAlrmDetails.elementAt(i+1);
         String mess=(String)getAlrmDetails.elementAt(i+2);
            int atime=Integer.parseInt(time);
            atime=atime*1000*60;
       	    l2.addElement(new script()
						.setLanguage("JavaScript")
					        .addElement("var "+title+"1=self.setInterval(\""+title+"()\","+atime+");")
                                                .addElement("function "+title+"() {")
                                                .addElement(" alert('"+mess+"');")
                                                .addElement(""+title+"1=window.clearInterval("+title+"1)}"));
       }

       //Vector Variable=ReportDataBaseLayer.selectname();
	/*
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

       String logo="./learnityasmtserver.assessmentportal.standaloneasmt.Image"; 
		form1.addElement(new table()
        			.addElement(new tr()
        				.addElement(new td()
						.addElement(new img()
        							//.setSrc("../images/IstLogo.jpg")
					       .setSrc("../coreadmin/images/logo.gif")
					      // .setSrc("./interfaceengine.ResourceImage?resource_id=logo&interface_id=LearnityPortal")
								.setClass("organistaionlogo")
								)
								
						)
        				.addElement(new td()
        						.addElement(table1)))
                                        //.setBorder("1")												
                                          );
		table1.addElement(tr1
			
			.addElement(td2
				.addElement(new nobr()
					.addElement(new div()
						  .addElement("&nbsp;Date:  "+strDate+"&nbsp;")
						   .setID("DateTime")
					)	
				)
			)
			.addElement(td3
				.addElement(new nobr()
					.addElement(new div()
						.addElement("&nbsp;Time: "+strTime+"&nbsp;")
						.setID("DateTime")
					)	
				)
			)
			.setClass("AssessmentSectionHeading")			
		);
		

        	
        	table1.addElement(tr2
        		.addElement(td4
        			.addElement(new nobr()
        				.addElement(new div()

						.setID("DivB")
					)	
        			)
        		)	
		);

		
		table1.addElement(tr3
        		.addElement(td5
        		)	
		);
		table1.addElement(tr4
        		.addElement(td6
        			.addElement(new nobr()
        				.addElement(new nobr()
        				.addElement("<center>Time Left:&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"hidden\" name=\"beg2\" size=\"7\"  value=\""+duration+"\"/>")
				.addElement("<input type=\"text\" name=\"disp2\" size=\"9\" value=\""+timeparam+"\"/>")
        				.addElement("&nbsp;&nbsp;&nbsp;&nbsp;minutes</center>")

        			)
        			)
        		)
        		.setClass("AssessmentSectionHeading")	
		);
			

        	
        	table1.addElement(tr5
        		.addElement(td7
        			.addElement(new nobr()
        				.addElement(new div()

						.setID("DivB")
					)	
        			)
        		)
        	);
        	
        
        	table2.addElement(tr6
        		.addElement(td8
        			.addElement(new hr()
        				.setSize(1)
        			)
        		)
        	);
        	tr tr7 = new tr();
		td td9 = new td();
		td td10 = new td();
		td td11 = new td();
		
        	table2.addElement(tr7
        		.addElement(td9
        		)
        		.addElement(td10
        		)
        		.addElement(td11
        		)
        	);
        	
		tr tr8 = new tr(); 
		td td12 = new td();
		td td13 = new td().setWidth(490).setVAlign("top");
		td td15 = new td();
		tr tr10 = new tr();
		td td16 = new td();
		td td17 = new td();
		td td18 = new td();
		div divQA = new div();
		
        	table2.addElement(tr8
        			.addElement(td12
        			)
        			.addElement(td13
        				
        					.addElement(divQA
        						.setID("QuestionAnswer")
        					)
        				
        			)
        			.addElement(td15
        			)
        	);
        	 table2.addElement(tr10
        		.addElement(td16
        		)
        		.addElement(td17
        		)
        		.addElement(td18
        		)
        	);      	        	
        	
        	
        	
        	    String strReturn = "";
        	    int no_question_per_page=0;
        	    if(ques_per_page != null && !ques_per_page.equals(""))
	            {
			no_question_per_page = Integer.parseInt(ques_per_page);
			
		    }
		    Question_Generation generatedquestions=new Question_Generation();
        	if (questionAll!=null){    
        	    for (int i = 0; i < questionAll.size(); ++i) {
		     Vector questionMatter = (Vector) questionAll.elementAt(i);
		     Vector response = (Vector) questionMatter.elementAt(0);
		     Vector question = (Vector) questionMatter.elementAt(1);
		     ///////////////////////////////////////////////////////
			/*Vector question=new Vector();
			Vector response=new Vector();
			Vector vOption=new Vector();
			Vector leftoption=new Vector();
			Vector rightoption=new Vector();
			Vector vCorrectAns=new Vector();
			Vector vFeedback=new Vector();
			String l1[]=new String[2];
			String l22[]=new String[2];
			String l3[]=new String[2];
				l1[0]="L111111";
			 l1[1]="West Bengal";
			 l22[0]="L2222222";
			 l22[1]="Bihar";
			 l3[0]="L333333";
			 l3[1]="Tripura";
			String r1[]=new String[2];
			String r2[]=new String[2];
			String r3[]=new String[2];
				r1[0]="R111111";
			 r1[1]="Patna";
			 r2[0]="R222222";
			 r2[1]="Agartala";
			 r3[0]="R33333";
			 r3[1]="Kolkata";
				question.addElement("1");
       				question.addElement("Match States with Capital");
				leftoption.addElement(l1);
				leftoption.addElement(l22);
				leftoption.addElement(l3);
				rightoption.addElement(r1);
				rightoption.addElement(r2);
				rightoption.addElement(r3);
				vOption.addElement(leftoption);
				vOption.addElement(rightoption);
			String a1[]=new String[2];
			String a2[]=new String[2];
			String a3[]=new String[2];
			 a1[0]=l1[0];
			 a1[1]=r3[0];
			 a2[0]=l22[0];
			 a2[1]=r1[0];
			 a3[0]=l3[0];
			 a3[1]=r2[0];
			vCorrectAns.addElement(a1);
			vCorrectAns.addElement(a2);
			vCorrectAns.addElement(a3);
			question.addElement(vOption);
			question.addElement(vCorrectAns);
			response.addElement(4);
        		response.addElement("3");
			response.addElement(vFeedback);
			response.addElement(".5");*/
		     //////////////////////////////////////////////////////
		     Integer iTitle = (Integer) response.elementAt(0);
		     String strQuesNo =(String) question.elementAt(0);
		     String selectop = "";
		     String selectop1[]=new String[6];
		     Vector vSelecttop=new Vector();
		     String titleType="";
		     for (int n=vQuesIdAns.size()-2; n >-1; n=n-2)
		    {
		    	String id=(String) vQuesIdAns.elementAt(n);
		    	if(strQuesNo.equals(id)){
				if(iTitle!=4){
		    			if(iTitle!=1){
		    				selectop=(String) vQuesIdAns.elementAt(n+1);
		    				break;
		    			}
		    			else{
		    				selectop1=(String[])vQuesIdAns.elementAt(n+1);
		    				break;
					}
				}
		    		else{
					vSelecttop=(Vector) vQuesIdAns.elementAt(n+1);
					break;
		    		}
			}
		    }
		     switch (iTitle.intValue()) {
		         case 0 :
		             	titleType="Multiple Choice";
		            	strReturn = generatedquestions.MultipleChoiceHTML(response, question,serial_no+1,selectop,titleType);
		                serial_no=serial_no+1;
		           	divQA.addElement(strReturn);			
		             break;
		         case 1 :
		             	strReturn = generatedquestions.MultipleResponseHTML(response, question,serial_no+1,selectop1);
		                serial_no=serial_no+1;
		             	divQA.addElement(strReturn);		
		             break;
		         case 2 :
		             	strReturn = generatedquestions.FillBlanks(response, question,serial_no+1,selectop);
		                serial_no=serial_no+1;
		             	divQA.addElement(strReturn);
		             break;
		         case 3 :
		              	titleType="True False";
		              	strReturn =generatedquestions.MultipleChoiceHTML(response, question,serial_no+1,selectop,titleType);
		              	serial_no=serial_no+1;
		              	divQA.addElement(strReturn);	
		             break;
			 case 4 :
		              	titleType="Matching Question";
				strReturn =generatedquestions.MatchingQuestionHTML(response, question,serial_no+1,vSelecttop);
		              	serial_no=serial_no+1;
				divQA.addElement(strReturn);
		             break;
		      
		     }
		     
		      no_of_question_retrived=no_of_question_retrived+1;
		      if(i==no_question_per_page-1){
		     	break;
		         
		      }
		        
		  }
		}
		   
		table4.addElement(tbody1
			.addElement(tr11
				.addElement(td19
					.addElement(new img()
						.setBorder(0)
						.setHeight(5)
						.setWidth(140)
						.setSrc("../images/T.gif")
				        )
				)
				.addElement(td20
					.addElement(new img()
						.setBorder(0)
						.setHeight(5)
						.setWidth(1)
						.setSrc("../images/T.gif")
				        )
				)
			)
		);
		if (questionAll!=null)
		{
		 table5.addElement(tr12
			.addElement(td21
				.addElement(strButton)
			)
			.addElement(td22

			)
			.addElement(td23
			)
		)
		.setClass("submit_button");
	       }
	        else{
	       
	       
		    table6.addElement(divQA
		    .addElement("Questions is not Available, Please contact with your Database vendor." ).setID("title"));
	            table5.addElement(tr12
			.addElement(td21
				.addElement(strButton)
			)
			.addElement(td22

			)
			.addElement(td23
			)
		)
	       .setClass("submit_button");
	       }
		
	 String javaScript1 ="";
	 String javaScript2 ="";	
	
	if (bRefresh) {
		 javaScript1 = "\n //<![CDATA["+
		 		"\n  function submitPage()  {"+
						"\n	//window.document.frmsec.hddTimeLeft.value=timeleft"+
						"\n	//buildSectionString()"+
						"\n	submit_onclick();"+
						"\n }"+
						"\n  //]]>";
	}
	 {
		 javaScript2 ="\n //<![CDATA["+
				  /*
				 "\n  function click(e) "+
				 "\n  {"+
				 "\n	var browsername=navigator.appName;"+
				  "\n			if (e.which == 2 || e.which == 3) {"+
				  "\n					alert(\"Operation not allowed\");"+
				 "\n					return false;"+
				 "\n				}"+
				 "\n  }"+

				"\n	document.captureEvents(Event.MOUSEDOWN);"+
				 "\n  document.onclick=click;"+ 
				  
				 "\n  function trapKey() {"+
				 "\n	var browsername=navigator.appName;"+
				 "\n			alert(\"Operation not allowed\");"+
				 "\n			return false;"+
				 "\n   }"+
				 "\n   document.captureEvents(Event.KEYPRESS);"+
				 "\n  document.onkeypress=trapKey;"+
				 */
				"\n  function submitPage1() {"+
						"\n		document.getElementById('frm').method=\"post\";"+
						"\n		document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.StResultSection?total_ques="+total_ques+"&AssessmentTitle="+AssessmentTitle+"&ques_per_page="+ques_per_page+"&total_time_left=\"+timeleft+\"&no_of_question_retrived="+no_of_question_retrived+"&total_no_of_question_retrived="+total_no_of_question_retrived+"&duration="+duration+"&serial_no="+serial_no+"&total_time="+total_time+"&assessment_id1="+assessment_id1+"\";"+
						"\n		document.getElementById('frm').target=\"_self\";"+
						"\n		document.getElementById('frm').submit();"+
						"\n	}"+
				"\n	var returnValue =\"\";"+
				"\n  function autosave() {"+
						"\n 		timeleft1 =Math.round(timeleftr)"+
						"\n		document.getElementById('frm').method=\"post\";"+
						"\n		document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.GenerateTestXHTML?returnValue1=\"+returnValue+\"&total_ques="+total_ques+"&AssessmentTitle="+AssessmentTitle+"&ques_per_page="+ques_per_page+"&total_time_left=\"+timeleft1+\"&no_of_question_retrived="+no_of_question_retrived+"&total_no_of_question_retrived="+total_no_of_question_retrived+"&duration="+duration+"&serial_no=0&total_time="+total_time+"&assessment_id="+assessment_id1+"#\"+returnValue+\"\";"+
						"\n		document.getElementById('frm').target=\"_self\";"+
						"\n		document.getElementById('frm').submit();"+
						"\n	}"+
				 "\n  function lastattemptedquestion_onclick(radIndex){"+
				 "\n	returnValue = radIndex;"+
				 "\n	}"+
				 "\n  function submitPage() {"+
				 "\n 		timeleft1 =Math.round(timeleftr)"+
// 				 "\n            alert(\""+total_no_of_question_retrived+"\");"+
				 "\n		document.getElementById('frm').method=\"post\";"+
				 "\n		document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.PreviewResult?total_ques="+total_ques+"&AssessmentTitle="+AssessmentTitle+"&ques_per_page="+ques_per_page+"&total_time_left=\"+timeleft1+\"&no_of_question_retrived="+no_of_question_retrived+"&total_no_of_question_retrived="+total_no_of_question_retrived+"&duration="+duration+"&serial_no="+serial_no+"&total_time="+total_time+"&assessment_id1="+assessment_id1+"&returnValue1=\"+returnValue+\"\";"+
				 "\n		document.getElementById('frm').target=\"_self\";"+
				 "\n		document.getElementById('frm').submit();"+
				 "\n }"+
				 "\n  //]]>";
	     }
	
	      String javaScript3 = "\n //<![CDATA["+
					"\n    function submit_onclick() {"+
						"\n 		timeleft1 =Math.round(timeleftr)"+
						"\n		document.getElementById('frm').method=\"post\";"+
						"\n	        document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTest?total_ques="+total_ques+"&AssessmentTitle="+AssessmentTitle+"&ques_per_page="+ques_per_page+"&total_time_left=\"+timeleft1+\"&no_of_question_retrived="+no_of_question_retrived+"&total_no_of_question_retrived="+total_no_of_question_retrived+"&duration="+duration+"&serial_no="+serial_no+"&total_time="+total_time+"&assessment_id1="+assessment_id1+"\";"+
						"\n		document.getElementById('frm').target=\"_self\";"+
						"\n		document.getElementById('frm').submit();"+
						"\n    }" +
						"\n  function Previous_onclick() {"+
						"\n 		timeleft1 =Math.round(timeleftr)"+
						"\n		document.getElementById('frm').method=\"post\";"+
						"\n	        document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTest?total_ques="+total_ques+"&AssessmentTitle="+AssessmentTitle+"&ques_per_page="+ques_per_page+"&total_time_left=\"+timeleft1+\"&no_of_question_retrived="+no_of_question_retrived+"&total_no_of_question_retrived="+total_no_of_question_retrived+"&duration="+duration+"&serial_no="+serial_no+"&total_time="+total_time+"&assessment_id1="+assessment_id1+"&previous=1\";"+
						"\n		document.getElementById('frm').target=\"_self\";"+
						"\n		document.getElementById('frm').submit();"+
						"\n    }" +
						"\n  function ok_onclick() {"+
						"\n	document.getElementById('frm').method=\"post\";"+
						"\n	document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.StResultSection\";"+
						"\n 	window.close();"+
						"\n}"+
						"\n  //]]>";
		
		String quit= "\n function Quit_onclick() {"+
					"\n 		if( confirm(\"Do you wish to quit?\") ) {"+
					"\n 		window.opener.location.reload();"+
					"\n 			window.parent.close(); "+
					"\n 		}"+
					"\n 	}";
		
		String scriptfortime="\n //<![CDATA["+ 
				"\n var timeleft"+
                              "\n var timeleftr"+
                              "\n var up,down"+
                              "\n   var min1,sec1"+
                              "\n  var cmin1,csec1,cmin2,csec2"+
			      "\n 	timeleftatrefresh =Math.round(timeleftr)"+
			      
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
// 			                                                        "\n {                   timeleft =Math.round(timeleftr)"+
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
                                                                        "\n  //]]>";				
		
		script javaScript11 = new script().setLanguage("javascript").addElement(javaScript1);
		script javaScript22 = new script().setLanguage("javascript").addElement(javaScript2);
		script javaScript33 = new script().setLanguage("javascript").addElement(javaScript3);
		script script2 = new script().setLanguage("javascript").addElement(scriptfortime);
		
		head1.addElement(title1);
		head1.addElement(csslink);
		if(refreshassessment != null && !refreshassessment.equals("")){
			if(duration>1)
				head1.addElement(refreshassessment);
		}
		head1.addElement(script1);
		head1.addElement(script2);
		
		form1.addElement(l2);
		form1.addElement(table2);
		form1.addElement(table4);
		form1.addElement(table5);
		form1.addElement(javaScript22);
		form1.addElement(javaScript33);
	
      	body1.addElement(form1);
	html1.addElement(head1);
	html1.addElement(body1);
	doc1.addElement(html1);
	
	String abcd = doc1.toString();
	return abcd;

  }
}