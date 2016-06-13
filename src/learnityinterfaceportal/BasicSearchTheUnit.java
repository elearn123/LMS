package learnityinterfaceportal;
import java.util.Vector;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
import org.apache.ecs.html.*;
import comv2.aunwesha.param.*;
import org.grlea.log.*;

public class BasicSearchTheUnit extends HttpServlet
{
	public static final SimpleLogger log = new SimpleLogger(BasicSearchTheUnit.class, true);
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException , ServletException
	{

		HttpSession mysession;
		String search_string=null;
		String moreInformation ="5";
		PrintWriter out = response.getWriter();			
		/************ Disabling caching in browser ********/
				response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		/**************************************************/
			
				response.setContentType("text/html");	   
			
		int count =0;    
		mysession=request.getSession(true);
		String student_id = (String)mysession.getAttribute("student_id");
		System.out.println("=========student_id========"+student_id);
		search_string = request.getParameter("thesearchstring");
		System.out.println("========================search_string============="+search_string);
		Vector vCourses = DataBaseLayer.getCourses(student_id);//Learning Objects
		Vector vNoticeList = DataBaseLayer.getNoticeInformation(student_id); // For Notice Board
           	
		Html html = new Html();
		Head head = new Head()
				.addElement(new Title("LearnITy&#8482; Search Engine"))
				.addElement(new Script()
				.setLanguage("JavaScript")
				.setSrc("../dwr/interface/Portal.js"))
				.addElement(new Script()
				.setLanguage("JavaScript")
				.setSrc("../dwr/engine.js"));
		Body body = new Body().setBgColor("#FFF7E5")
				.setAlink("990000")
				.setVlink("660000")
				.setLink("990000");    
		body.addElement(new Center()
				.addElement(new Font()
				.setColor("#CC0000")
				.setSize(4)
				.addElement(new B("Search Result"))))
				.addElement("<BR>");
	       
		head.addElement(new Link()
				.setHref("../js/stylesheet.css")
				.setRel("stylesheet"));
		String script =
				"\n  function launchLO(course_id) { "+
				"\n	var browserName = navigator.appName;"+
				"\n	var browserVersion = parseInt(navigator.appVersion);"+
				"\n	var browser;"+
				"\n	if(browserName == 'Microsoft Internet Explorer' && browserVersion >=4){ "+
				"\n		browser='ie4up';"+
				"\n	}"+
				"\n	else { "+
				"\n		browser='mf';"+
				"\n	} "+
				"\n	Portal.lunchCourseAll(browser,browserVersion,course_id,\"0\",\"0\",function (data) { "+
				
				"\n	});"+
				
// 				"\n     alert(\'Javascript    \'+u_id);"+
				"\n	window.open('./interfaceenginev2.PortalServlet?IID=DeliveryEngine','new','width=910,height=635,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no');"+
				"\n    }"+
				 
				"\n     function launchCoursemanagement(course_id,course_name,topic_id)"+
				"\n	{"+
				"\n		Portal.setSessionRoleId(course_id);"+
				"\n		window.open('./interfaceenginev2.PortalServlet?IID=Course','Course','width=850,height=690,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no');"+
				"\n		var topic_id=\"Home\";"+
				"\n		Portal.setSessioncourseId(course_id,topic_id,function(data) {"+
				
				"\n		});"+
				"\n	}"+
				"\n   function launchforum(strforum,strfname){"+
				"\n	Portal.setForumId(strforum,function(data) {"+
				"\n	});"+
				"\n     window.open('./interfaceenginev2.PortalServlet?IID=Forum','forum','width=900,height=670,status=yes,scrollbars=no,resizable=yes,toolbar=no,menubar=no');"+
				"\n   }"+
				"\n  function launchNotice(strnotice_id){"+
				"\n	Portal.setSessionNoticeId(strnotice_id,function(data) {"+
				"\n	});"+
				"\n	var browserName = navigator.appName;"+
				"\n	var browserVersion = parseInt(navigator.appVersion);"+
				"\n	var browser;"+
				"\n	if(browserName == 'Microsoft Internet Explorer' && browserVersion >=4){"+
				"\n		browser='ie4up';"+
				"\n     }"+
				"\n	else {"+
				"\n		browser='mf';"+
				"\n     }"+
				"\n	var n = strnotice_id;"+
				"\n	window.open('./interfaceenginev2.PortalServlet?IID=NoticeBoardView','NoticeBoardview','width=600,height=540,status=yes,scrollbars=no,resizable=yes,toolbar=no,menubar=no');"+
				"\n   }"+
				"\n  function chatLunch(id,sessionid) {"+
				"\n	var browserName = navigator.appName;"+
				"\n	var browserVersion = parseInt(navigator.appVersion);"+
				"\n	var browser;"+
				"\n	if(browserName == 'Microsoft Internet Explorer' && browserVersion >= 4) {"+
				"\n		browser = 'ie4up';"+
				"\n	}"+
				"\n	else {"+
				"\n		browser = 'mf';"+
				"\n	}"+
				"\n	Portal.setSessionSceId(id,browser,function(data) {"+
				"\n	});"+
				"\n	window.open('./interfaceenginev2.PortalServlet?IID=SCE','chatgroup','width=\"100%\",height=\"100%\",status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no');"+
				"\n	}"+
				
				"\n   function loadassessment(asessmentid){"+
				"\n	Portal.setCounter(asessmentid);"+		
				"\n	window.open('./interfaceenginev2.PortalServlet?IID=AssessmentDelivery','assessment','width=800,height=700,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no');"+
				"\n     }"+
				
				
				"\n	function LaunchUnit(course_id)"+
				"\n	{"+
				"\n	document.searchForm.course_name.value = course_id;"+
				"\n	document.searchForm.method =\"post\";"+
				"\n document.searchForm.action=\"./delivery.LaunchCourse\";"+
				"\n	window.open(\"\",\"new\",\"width=800,height=600,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no\");"+
				"\n	document.searchForm.target=\"new\";"+
				"\n	document.searchForm.submit();"+
				"\n	}"+
				"\n function LaunchNoticeInfo(student_name ,notice_heading)"+
				"\n {"+
				"\n  	var n_heading=notice_heading;"+
				"\n  	var s_name=student_name;"+
				"\n 	window.open('./notice.noticeinfo.NoticeDetails?heading='+n_heading+'&studentname='+s_name+'&noticeInfo="+moreInformation+"','_blank','width=600,height=450,menu=yes,scrollbars=yes');"+
	     			
				"\n }"+
				"\n function LaunchForumUnit(strforum,strdname)"+
				"\n {    var n=strforum;"+
				"\n      var n1=strdname;"+
				"\n      window.open('./forum.discussionforum.DiscussionForum?view_type=1&thread_id=ID0&message_id=ID0&strforum='+n+'&strfname='+n1,'_blank','width=800,height=600,status=yes,resizeable=no,toolbar=no,location=no');"+
                      
				"\n };";
	     			
		head.addElement(new Script().setLanguage("javascript").addElement(script));
		html.addElement(head);
		html.addElement(body);
		Form form = new Form().setName("searchForm");
		Input input_course_name = new Input().setType("hidden").setName("course_name");
		Input input_notice = new Input().setType("hidden").setName("notice_id");
		Input input_forum_id = new Input().setType("hidden").setName("forum_id");
		form.addElement(input_course_name)
			.addElement(input_notice)
					.addElement(input_forum_id);
		body.addElement(form);
		Table table = new Table(); 
		table.addElement(new TRExtension().addHeaderNamesForSearchUnit("Reference found in","Link to reference")); 
            
		for (int i =0; i < vCourses.size();i=i+2)
		{ 
			String course_stat_desc ="";
			String course_id = (String)vCourses.elementAt(i);
			String course_name = (String)vCourses.elementAt(i+1);
			String course_status = DataBaseLayer.checkRegistration(course_id,student_id);
			if (course_status.equalsIgnoreCase("A"))
				course_stat_desc = "Available";
			else if (course_status.equalsIgnoreCase("U"))
				course_stat_desc = "Unregistered";
			else if (course_status.equalsIgnoreCase("V"))
				course_stat_desc = "Student Account Inactive";
			else if (course_status.equalsIgnoreCase("I"))
				course_stat_desc = "Inactive";
			else if (course_status.equalsIgnoreCase("T"))
				course_stat_desc = "Time Over";
			else if (course_status.equalsIgnoreCase("D"))
				course_stat_desc = "Date Over";
								
			if(SearchCommon.SearchItem(course_name,search_string))
			{	
				count++;
				if(course_status.equalsIgnoreCase("A")){
					table.addElement(new TRExtension().addRowSearchUnitForCourseActiveOnlywithScript(count+".    &nbsp;&nbsp;Learning Object",course_id,course_name,"course_stat_desc","javascript:launchLO('"+course_id+"')"));				
				}
				else
				{
					table.addElement(new TRExtension().addRowSearchUnitForCourseActiveOnlywithScript(count+".    &nbsp;&nbsp;Learning Object",course_id,course_name,"course_stat_desc","javascript:launchLO('"+course_id+"')"));				
				}
			}
		}	
    
		System.out.println("vNoticeList size=================="+vNoticeList.size());
		for (int i =0; i < vNoticeList.size();i=i+2)
		{
			String srtBulletin =(String)vNoticeList.elementAt(i);
			String srtBulletin1 =(String)vNoticeList.elementAt(i+1);
			if(SearchCommon.SearchItem(srtBulletin1,search_string))
			{	
				count++;
				table.addElement(new TRExtension().addRowSearchForNoticeOnlywithscript(count+".    &nbsp;&nbsp;Notice Information ",srtBulletin,srtBulletin1,"javascript:launchNotice('"+srtBulletin+"')"));				
			}
		}	
			/*
		Vector vForumList = DataBaseLayer.getForumName1();
		System.out.println("vForumList.size()================= "+(String[])vForumList.elementAt(0)); 
		System.out.println("vForumList.size()================= "+(String[])vForumList.elementAt(1));      
		for(int i=0; i<vForumList.size(); i++)
		{    
		System.out.println("i================= "+i);        	
		String strbody[] = (String[])vForumList.elementAt(i);
		System.out.println(" Forum list strbody[0]: "+strbody[0]);
                
                  
		if(SearchCommon.SearchItem(strbody[0],search_string))
		{	
		System.out.println("=======search=========");   
     				
		String strforumid =null;
		vForumList=DataBaseLayer.getForumList(student_id); 
		for(int j=0; j<vForumList.size(); j=j+3)
		{
		strforumid =(String)vForumList.elementAt(j).toString();           	
		System.out.println("strforumid: "+strforumid);
				  		
		if(strforumid.equals(strbody[0]))
		{
		System.out.println("==================");
		count++;
		table.addElement(new TRExtension().addRowSearchForumUnit(count+".   &nbsp;&nbsp;Discussion Group ",strbody[0],strbody[1]));	
														
	}
				  				  						  							  		
	}					  						  		
		System.out.println("========2==========");     	
     				
	}
	}*/
		Vector vAssessmentList = DataBaseLayer.getAssessmentInformation(student_id);
		Vector v1=new Vector();
		for (int i =0; i < vAssessmentList.size();i=i+2)
		{
			
			String srtBulletin =(String)vAssessmentList.elementAt(i);
			String srtBulletin1 =(String)vAssessmentList.elementAt(i+1);
			System.out.println("========search_string===assessment======="+search_string); 
			System.out.println("========srtBulletin====assessment======"+srtBulletin); 
			System.out.println("========srtBulletin1====assessment======"+srtBulletin1); 
			if(SearchCommon.SearchItem(srtBulletin,search_string))
			{	
				if(v1.size()==0){
					v1.addElement(srtBulletin);
					count++;
					table.addElement(new TRExtension().addRowSearchForNoticeOnlywithscript(count+".    &nbsp;&nbsp;Assessment Information ",srtBulletin1,srtBulletin,"javascript:loadassessment('"+srtBulletin1+"')"));
				}
				else{
					if(!v1.contains(srtBulletin)){
						v1.addElement(srtBulletin);
						count++;
						table.addElement(new TRExtension().addRowSearchForNoticeOnlywithscript(count+".    &nbsp;&nbsp;Assessment Information ",srtBulletin1,srtBulletin,"javascript:loadassessment('"+srtBulletin1+"')"));				
					}
				}
			}
		}	
		Vector vScoList = DataBaseLayer.getScoInformation(student_id);
		Vector v2=new Vector();
		for (int i =0; i < vScoList.size();i=i+3)
		{
			
			String srtBulletin =(String)vScoList.elementAt(i);
			String srtBulletin1 =(String)vScoList.elementAt(i+1);
			String srtBulletin2 =(String)vScoList.elementAt(i+2);
			System.out.println("========search_string===sce======="+search_string); 
			System.out.println("========srtBulletin======sce===="+srtBulletin); 
			System.out.println("========srtBulletin1=====sce====="+srtBulletin1); 
			if(SearchCommon.SearchItem(srtBulletin1,search_string))
			{	
				if(v1.size()==0){
					v2.addElement(srtBulletin);
					count++;
					table.addElement(new TRExtension().addRowSearchForNoticeOnlywithscript(count+".    &nbsp;&nbsp;SCE Information ",srtBulletin,srtBulletin1,"javascript:chatLunch('"+srtBulletin+"','"+srtBulletin2+"')"));
				}
				else{
					if(!v2.contains(srtBulletin)){
						v2.addElement(srtBulletin);
						count++;
						table.addElement(new TRExtension().addRowSearchForNoticeOnlywithscript(count+".    &nbsp;&nbsp;SCE Information ",srtBulletin,srtBulletin1,"javascript:chatLunch('"+srtBulletin+"','"+srtBulletin2+"')"));				
					}
				}
			}
		}	
		Vector vCourseList = DataBaseLayer.getCourseInformation(student_id);
		Vector v3=new Vector();
		for (int i =0; i < vCourseList.size();i=i+2)
		{
			
			String srtBulletin =(String)vCourseList.elementAt(i);
			String srtBulletin1 =(String)vCourseList.elementAt(i+1);
			System.out.println("========search_string===course======="+search_string); 
			System.out.println("========srtBulletin======course===="+srtBulletin); 
			System.out.println("========srtBulletin1=====course====="+srtBulletin1); 
			if(SearchCommon.SearchItem(srtBulletin1,search_string))
			{	
				if(v3.size()==0){
					v3.addElement(srtBulletin);
					count++;
					table.addElement(new TRExtension().addRowSearchForNoticeOnlywithscript(count+".    &nbsp;&nbsp;Course Information ",srtBulletin,srtBulletin1,"javascript:launchCoursemanagement('"+srtBulletin+"','"+srtBulletin1+"','Home')"));
				}
				else{
					if(!v3.contains(srtBulletin)){
						v3.addElement(srtBulletin);
						count++;
						table.addElement(new TRExtension().addRowSearchForNoticeOnlywithscript(count+".    &nbsp;&nbsp;Course Information ",srtBulletin,srtBulletin1,"javascript:launchCoursemanagement('"+srtBulletin+"','"+srtBulletin1+"','Home')"));				
					}
				}
			}
		}
		Vector vForumList = DataBaseLayer.getForumInformation(student_id);
		Vector v4=new Vector();
		for (int i =0; i < vForumList.size();i=i+2)
		{
			
			String srtBulletin =(String)vForumList.elementAt(i);
			String srtBulletin1 =(String)vForumList.elementAt(i+1);
			System.out.println("========search_string===forum======="+search_string); 
			System.out.println("========srtBulletin====forum======"+srtBulletin); 
			System.out.println("========srtBulletin1====forum======"+srtBulletin1); 
			if(SearchCommon.SearchItem(srtBulletin1,search_string))
			{	
				if(v4.size()==0){
					v4.addElement(srtBulletin);
					count++;
					table.addElement(new TRExtension().addRowSearchForNoticeOnlywithscript(count+".    &nbsp;&nbsp;Forum Information ",srtBulletin,srtBulletin1,"javascript:launchforum('"+srtBulletin+"','"+srtBulletin1+"')"));
				}
				else{
					if(!v4.contains(srtBulletin)){
						v4.addElement(srtBulletin);
						count++;
						table.addElement(new TRExtension().addRowSearchForNoticeOnlywithscript(count+".    &nbsp;&nbsp;Forum Information ",srtBulletin,srtBulletin1,"javascript:launchforum('"+srtBulletin+"','"+srtBulletin1+"')"));				
					}
				}
			}
		}		
		if (count==0)
		{
			body.addElement(new Center()
					.addElement(new B("Sorry! No matching result found for your query!<BR>")));
		}
		if (count==1)
		{
			body.addElement(new Center().addElement(new B("Only 1 matching result found :<BR>")));
			body.addElement(new Center().addElement(table));
		}
		if (count>1)
		{
			body.addElement(new Center().addElement(new B("Your search for \""+search_string+"\" Matched \""+count+"\" Objects <BR>")));
			body.addElement(new Center().addElement(table));
		}
		out.println(html.toString());
		out.close();
	}	
          
	/**Process the HTTP Get request*/
			public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
			}        
  
        
}
		
	
    
   
        
    	
    	
  
     
