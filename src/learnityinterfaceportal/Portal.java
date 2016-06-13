package learnityinterfaceportal;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;

import learnityasmtserver.assessmentportal.dbconnection.AsmtPortalDataBaseLayer;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.grlea.log.SimpleLogger;
/**
 * 
 * @author Shibaji  Chatterjee
 */
public class Portal 
{  
	public  static final SimpleLogger log = new SimpleLogger(Portal.class, true);// Create a SimpleLogger:

public String verifyUser(String userid,String password) throws ServletException, IOException
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession=wctx1.getSession();
		javax.servlet.http.HttpServletRequest     req =wctx1.getHttpServletRequest() ;
		javax.servlet.http.HttpServletResponse    res =wctx1.	getHttpServletResponse();
		System.out.println("======================before try in VerifyUser==========");
		int validity = 0;
		try{
			
			validity = DataBaseLayer.validate(userid, password);
		}catch(Exception e){}
		
		if (validity==1)
		{
			mysession = req.getSession(true);
			
			
			Calendar calendarnew = new GregorianCalendar();
			Date trialTime = new Date();
			calendarnew.setTime(trialTime);
			String months[]={"January","Feburary","March", "April", "May","June",
				"July","August","September","October","November","December"};

				String strTime = calendarnew.get(Calendar.HOUR_OF_DAY)+":"+ calendarnew.get(Calendar.MINUTE)+":"+ calendarnew.get(Calendar.SECOND);
	//String strDate = months[calendarnew.get(Calendar.MONTH)]+" "+ calendarnew.get(Calendar.DATE)+", "+ calendarnew.get(Calendar.YEAR); 
	
				String strDate = calendarnew.get(Calendar.YEAR)+"-"+(calendarnew.get(Calendar.MONTH)+1)+"-"+calendarnew.get(Calendar.DATE);
	
				String printedDate = months[calendarnew.get(Calendar.MONTH)]+" "+calendarnew.get(Calendar.DAY_OF_MONTH)+","+calendarnew.get(Calendar.YEAR);
	//System.out.println("=====================================pdate========"+printedDate);
				/*****************************************DATE AND TIME******************************************/    
                                
						String session_id = mysession.getId();

				String account_status =DataBaseLayer.getAccountStatus(userid);
				System.out.println("======================userid in VerifyUser=========="+userid);
				if (account_status.equalsIgnoreCase("Inactive"))
				{
					return "Inactive";			
				}
				else
				{
					if(!DataBaseLayer.isGroupExists(userid))
					{
						if (!DataBaseLayer.isTimeCompleted(userid))
						{
							return "CompleteTime";
						}
						else
						{
							mysession.setAttribute("user_id",userid);
							mysession.setAttribute("student_id",userid);
							DataBaseLayer.insertStudentLogin(session_id,userid,strTime, strDate);
					
							String role_tilte=DataBaseLayer.getUserRole(userid);
							System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+role_tilte);
							if(role_tilte==null)
								role_tilte = "";
					
					
							String url="";
							if(role_tilte.equals(""))
							{
								return "Norole";
							}
							else 
							{
								ResourceBundle rb = ResourceBundle.getBundle("portal1",Locale.getDefault());   
								String key1=role_tilte;
								String role_value = rb.getString(key1);
								System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>value>>>>>"+role_value);
								if(role_value==null) 
									role_value="";               
						    
								if(role_value.equals(""))
								{
									return "undefined";
								}
								else if(role_value.equals("ADMIN"))
								{
									return "Admin";
								}
								else if(role_value.equals("HOD"))
								{
									return "Hod";
								}
								
								else
								{
									return "Portal";
					
								}
							}
						}
					}
					else
					{
						if (!DataBaseLayer.isTimeCompletedgroup(userid))
						{
							return "CompleteTime";
						}
						else
						{
							mysession.setAttribute("user_id",userid);
							mysession.setAttribute("student_id",userid);
							DataBaseLayer.insertStudentLogin(session_id,userid,strTime, strDate);
					
							String role_tilte=DataBaseLayer.getUserRole(userid);
							System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+role_tilte);
							if(role_tilte==null)
								role_tilte = "";
					
					
							String url="";
							if(role_tilte.equals(""))
							{
								return "Norole";
							}
							else 
							{
								String key1=role_tilte;
								String role_value="";
								try {
								ResourceBundle rb = ResourceBundle.getBundle("portal1",Locale.getDefault());   
								role_value = rb.getString(key1);
								System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>value>>>>>"+role_value);
								if(role_value==null) 
									role_value="";               
								}
								catch (Exception ex2){System.out.println("portal1.properties file not found");}
						    
								if(role_value.equals(""))
								{
									return "undefined";
								}
								else if(role_value.equals("ADMIN"))
								{
									return "Admin";
								}
								else if(role_value.equals("HOD"))
								{
									return "Hod";
								}
								else
								{
									return "Portal";			
								}
							}
						}
						
					}	
				}
		}
				

		else
		{	
			mysession.setAttribute("user_id","");															
			return "Invalid";
		}
           
           
	}
	public String setUserInfo() {
		String html = "";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String loginno= DataBaseLayer.getLoginWelcome3(user_id);
		String createdate= DataBaseLayer.getLoginWelcome4(user_id);
		String systemaccessuser=DataBaseLayer.systemaccess(user_id);
		html="<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"100%\"><tr><td style=\"text-align:left;color:blue;font-weight:normal;font-family:verdana;font-size:8pt;\">Account created: </td><td style=\"text-align:left;color:black;font-weight:normal;font-family:verdana;font-size:8pt;\">"+createdate+"</td></tr><tr><td style=\"text-align:left;color:blue;font-weight:normal;font-family:verdana;font-size:8pt;\">No of times system accessed: </td><td style=\"text-align:left;color:black;font-weight:normal;font-family:verdana;font-size:8pt;\">"+loginno+"</td></tr><tr><td style=\"text-align:left;color:blue;font-weight:normal;font-family:verdana;font-size:8pt;\">Total time system accessed: </td><td style=\"text-align:left;color:black;font-weight:normal;font-family:verdana;font-size:8pt;\">"+systemaccessuser+"</td></tr></table>";
		return html;
	}
	
	public String getUserId() {
		String user = "";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		if(user_id==null || user_id.equals(""))
		{
			ScriptSession session = WebContextFactory.get().getScriptSession();
			ScriptBuffer script = new ScriptBuffer("window.location = \"./interfaceenginev2.PortalServlet?IID=LoginPage\";");
			session.addScript(script);
		}
                 
		return "";
		
	}
	
	public String timeshow()
	{
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		int hr=calendar.get(Calendar.HOUR_OF_DAY);
		int min=calendar.get(Calendar.MINUTE);
		int sec=calendar.get(Calendar.SECOND);
		
		String months[]={"January","Feburary","March", "April", "May","June",
			"July","August","September","October","November","December"};
			String strDate = months[calendar.get(Calendar.MONTH)]+" "+ calendar.get(Calendar.DATE)+", "+calendar.get(Calendar.YEAR);
		
			String hours="";
			if (hr>12)
			{
				hr=hr-12;
				hours=""+hr+ ":" +min+ ":" +sec+" p.m";
			}
			else
			{
				hours=""+hr+ ":" +min+ ":" +sec+" a.m";
			}
		
			String logintime=hours;
			
			String time = "";
			time = "<justify><font style=\"color: black;font: 14px verdana;font-weight:bold\">"+strDate+"<br>"+logintime+"</font></justify>";
			return time;
			
	}  
	
	public String setTitle(String title)
	{
			
		return title;			
	}
	  
	public String Welcome()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String loginwelcome = DataBaseLayer.getLoginWelcome(user_id);
		return loginwelcome;
	}
    
  

	public String LoObjectGrid()
	{

		getUserId();
		String html="";
		String innertable="";
		String course_id="";
		String total_access_time = null;  
		String total_time_accessed = null; 
		String course_status = null;       
		String overall_status = null;
		String course_name  ; //for course name
		String access_allowed_till; //for date till which the access is permitted
		String first_access_date_time; //for first access date and time
		String last_access_date_time;  //for last access date and time
		String topic_id = null;       	
		String dateregister=null;		
		String totaltime=null;
		String ss="";
		Boolean tt=false;
		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
                //mysession.setAttribute("student_id",user_id);
		Vector vCourseInfo =DataBaseLayer.getLoginInfo(user_id);
               
		for(int i =0 ; i <vCourseInfo.size() ; i = i +10)
		{
			Boolean b=true;			
			course_id = (String)vCourseInfo.elementAt(i);
			//System.out.println("course_id==LoObjectGrid===="+course_id);
			course_name = (String)vCourseInfo.elementAt(i+1);
			//System.out.println("course_name==LoObjectGrid===="+course_name);
			access_allowed_till = (String)vCourseInfo.elementAt(i+2);
			dateregister = (String)vCourseInfo.elementAt(i+3);
			last_access_date_time = (String)vCourseInfo.elementAt(i+4);
			topic_id = (String)vCourseInfo.elementAt(i+5);
			//System.out.println("topic_id==LoObjectGrid===="+topic_id);
			total_access_time =(String)vCourseInfo.elementAt(i+6);
			if(total_access_time == null )
			{
				ss="Not Applicable";
			}
			else{
				ss=total_access_time+"hrs";
			}   

			totaltime=DataBaseLayer.getRequisiteunitaccess(course_id);
			course_status = (String)vCourseInfo.elementAt(i+8);
			overall_status = (String)vCourseInfo.elementAt(i+9);
			//oracle.xml.parser.v2.XMLDocument manifest =DataBaseLayer.parse(course_id,"csformat");
			String unitaccess = DataBaseLayer.unitaccess(user_id,course_id);
			String  noodunitaccess=DataBaseLayer.nounitaccess(user_id,course_id);
			String strTopic_Title =null;
			strTopic_Title=DataBaseLayer.getScoTitle(topic_id,course_id);
			System.out.println("strTopic_Title==LoObjectGrid===="+strTopic_Title);
			try {
// 				PortalMgmt.servletfile.PortalImsManifest ims =  new PortalMgmt.servletfile.PortalImsManifest(manifest);
// 				if(topic_id != null)
// 				{
// 					strTopic_Title = ims.getItemTitle(topic_id);
// 					try{
// 						byte[] topictitle=strTopic_Title.getBytes();
// 						strTopic_Title=new String(topictitle,"utf-8");
// 					}catch(Exception e){System.out.println("Error:"+e);}
// 				}

				if (overall_status.equalsIgnoreCase("Active"))
				{
					if(DataBaseLayer.isPreUnitExists(course_id)) 
					{
						//System.out.println("b==LoObjectGrid==1=="+b);
						Vector preunit = DataBaseLayer.getPreUnitInfo(course_id);
                                                      
						//System.out.println("preunit==LoObjectGrid===="+preunit);
						for(int j=0; j<preunit.size(); j=j+2) {
	    		
							String preId = (String) preunit.elementAt(j);
							//System.out.println("preId==LoObjectGrid===="+preId);
							String UnId = (String) preunit.elementAt(j+1);
							//System.out.println("UnId==LoObjectGrid===="+UnId);
							if(!DataBaseLayer.isPreUnitCompleted(preId,user_id)) 
                                                        {
								//System.out.println("============isPreUnitCompleted===============");
								b=false;	
							}
						}
					}
					System.out.println("b==LoObjectGrid===="+b);
					if(b){
						if (topic_id!=null )
						{
							if (!strTopic_Title.equals("0" ))
							{
								System.out.println("strTopic_Title========vvvvvvv=========="+strTopic_Title);
							String color=(tt) ? "#EEE" : "#ffffff";
							innertable=innertable+
								"<tr style=\"background-color:"+color+";\"><td><a href=\"javascript:lunchCourse('"+course_id+"');\">"+course_name+"</a></td>"+
								"<td ><a href=\"javascript:resume2('"+course_id+"','"+topic_id+"','"+strTopic_Title+"');\">"+strTopic_Title+"</a></td>"+
								"<td>"+last_access_date_time+"</td>"+
								"<td >"+access_allowed_till+"</td>";
// 								"<td>"+ss+"</td>"+
// 								"<td>"+unitaccess+"</td>"+
// 								"<td>"+noodunitaccess+"</td></tr>";
							tt=!tt;	
							}
							else{
								String color=(tt) ? "#EEE" : "#ffffff";
								innertable=innertable+
										"<tr style=\"background-color:"+color+";\"><td><a href=\"javascript:lunchCourse('"+course_id+"');\">"+course_name+"</a></td>"+
										//"<td ><a href=\"javascript:resume2('"+course_id+"','"+topic_id+"','"+strTopic_Title+"');\">"+strTopic_Title+"</a></td>"+
										"<td></td>"+
										"<td>"+last_access_date_time+"</td>"+
										"<td >"+access_allowed_till+"</td>";
// 										"<td>"+ss+"</td>"+
// 										"<td>"+unitaccess+"</td>"+
// 										"<td>"+noodunitaccess+"</td></tr>";
								tt=!tt;	
							}	
						}
						else
						{	
							System.out.println("topic_id========else=========="+topic_id);
							String color=(tt) ? "#EEE" : "#ffffff";
							innertable=innertable+
								"<tr style=\"background-color:"+color+";\"><td><a href=\"javascript:lunchCourse('"+course_id+"');\">"+course_name+"</a></td>"+
								"<td >Not Accessed Yet</td>"+
								"<td >"+last_access_date_time+"</td>"+
								"<td>"+access_allowed_till+"</td>";
// 								"<td >"+ss+"</td>"+
// 								"<td >"+unitaccess+"</td>"+
// 								"<td >"+noodunitaccess+"</td></tr>";
							tt=!tt;			
						}
					}
					else{
						if (topic_id!=null )
						{
							String color=(tt) ? "#EEE" : "#ffffff";
							innertable=innertable+
// 									"<tr style=\"background-color:"+color+";\"><td><a href=\"javascript:lunchCourse('"+course_id+"');\">"+course_name+"</a></td>"+
// 									"<td ><a href=\"javascript:resume2('"+course_id+"','"+topic_id+"','"+strTopic_Title+"');\">"+strTopic_Title+"</a></td>"+
									"<tr style=\"background-color:"+color+";\"><td>"+course_name+"</td>"+
									"<td >"+strTopic_Title+"</td>"+
									"<td>"+last_access_date_time+"</td>"+
									"<td >"+access_allowed_till+"</td>";
// 									"<td>"+ss+"</td>"+
// 									"<td>"+unitaccess+"</td>"+
// 									"<td>"+noodunitaccess+"</td></tr>";
							tt=!tt;		
						}
						else
						{
							String color=(tt) ? "#EEE" : "#ffffff";
							innertable=innertable+
// 									"<tr style=\"background-color:"+color+";\"><td><a href=\"javascript:lunchCourse('"+course_id+"');\">"+course_name+"</a></td>"+
									"<tr style=\"background-color:"+color+";\"><td>"+course_name+"</td>"+
									"<td >Not Accessed Yet</td>"+
									"<td >"+last_access_date_time+"</td>"+
									"<td>"+access_allowed_till+"</td>";
// 									"<td >"+ss+"</td>"+
// 									"<td >"+unitaccess+"</td>"+
// 									"<td >"+noodunitaccess+"</td></tr>";
							tt=!tt;			
						}
					
					}
				}
			}												
			catch(Exception e){System.out.println("Error:"+e);}
		}	
		html="<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"660\" id=\"myScrollTable\">"+
				"<thead>"+
				"<tr><th>Unit Name</th><th>Book Mark</th><th>Last Accessed</th><th>Access Allowed Till</th></tr></thead>"+"<tbody>"+innertable+"</tbody></table>";
				 //  html=html+"<tbody class=\"scrollContent\"><tr><td>1</td></tr><tr><td>2</td></tr><tr><td>3</td></tr></tbody></table>";
 		return html;
	}
	
// 	public String selfRegistration() {
// 		String html = "";
// // 		WebContext wctx1 = WebContextFactory.get();
// // 		javax.servlet.http.HttpSession mysession = wctx1.getSession();
// // 		String user_id = (String) mysession.getAttribute("user_id");
// // 		String checkSelf = PortalMgmt.portal.NewDataBaseLayer.checkSelf(user_id);
// // 		if(checkSelf!=null){
// // 			if(checkSelf.equals("T")){
// 		// 				returnCollectionsforStudenthtml="<a href=\"javascript:selfRegistration_onClick()\"><img src=\"../images/R.jpg\"></a>";
// // 			}
// // 		}
// // 
// //                      
//  		return html;
// 	}	
	
	
	/***************************** Partha ************************/
	public String selfRegistration() {
		String html = "";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String checkSelf = DataBaseLayer.checkSelf(user_id);
		System.out.println("=========checkSelf============="+checkSelf);
		if(checkSelf!=null){
			if(checkSelf.equals("T")){
				html="<a href=\"javascript:selfRegistration_onClick()\"><img src=\"../coreadmin/images/R.jpg\"></a>";
			}
		}

                     
		return html;
	}			
			
			
	/****************************** End of Partha ***********************/

	public byte[]  getPhoto() {
		String html = "";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
	       byte[] buffer=DataBaseLayer.getUserPhoto(user_id);
               return buffer;
	}	
	public String UserLoginNo() {
		String html = "";
 		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
 		String countuser=DataBaseLayer.countuserlogin();
		html="<b>Users Currently Logged On</b>   :" +countuser;
                     
		return html;
	}
		
	
	
	/**************************         Changes by Indra for Course Management(Portal)       ********************************/ 
	
			
				
	public String returnCollections()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String col_id="";
		Vector vCollectionDetails = DataBaseLayer.getCollections(user_id);
		String uppertable="";
		if(vCollectionDetails.size()!=0)
		{
			int col=0;
			for(col = 0; col<vCollectionDetails.size();col=col+1)
			{
				System.out.println("returnCollections vCollectionDetails = "+vCollectionDetails);
				String strCol[] = (String[])vCollectionDetails.elementAt(col);
				System.out.println("---------------------------->strCol[0] = "+strCol[0]);
				System.out.println("---------------------------->strCol[1] = "+strCol[1]);
				uppertable+="<div id=\"coldiv\" style=\"position:absolute;left:"+(20+col*130)+"px\"><a href=\"javascript:collection_onclick('"+strCol[0]+"');\">"+strCol[1]+"</a></div>";	
				System.out.println("---------------UPPERTABLE----returnCollections----------> "+uppertable);
				
			}
			uppertable+="<div id=\"coldiv\" style=\"position:absolute;left:"+(20+col*130)+"px\"><a href=\"javascript:collection_onclick('default');\">Default</a></div>";		
	
		}else uppertable="";
		System.out.println("---------uppertable----------"+uppertable);
		return uppertable;
	}
			
	public String returnCollectionsforStudent()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String col_id="";
		Vector vCollectionDetails = DataBaseLayer.getCollections(user_id);
		String uppertable="";
		if(vCollectionDetails.size()!=0)
		{
			int col=0;
			for(col = 0; col<vCollectionDetails.size();col=col+1)
			{
				System.out.println("vCollectionDetails = "+vCollectionDetails);
				String strCol[] = (String[])vCollectionDetails.elementAt(col);
				System.out.println("---------------------------->strCol[0] = "+strCol[0]);
				System.out.println("---------------------------->strCol[1] = "+strCol[1]);
				uppertable+="<div id=\"coldiv\" style=\"position:absolute;left:"+(20+col*130)+"px\"><a href=\"javascript:course_collection_onclick('"+strCol[0]+"');\">"+strCol[1]+"</a></div>";	
				System.out.println("---------------UPPERTABLE--------------> "+uppertable);
				
			}
			uppertable+="<div id=\"coldiv\" style=\"position:absolute;left:"+(20+col*130)+"px\"><a href=\"javascript:course_collection_onclick('default');\">Default</a></div>";		
	
		}else uppertable="";
		System.out.println("---------uppertable----------"+uppertable);
		return uppertable;
	}		
	
	public void setSessionCollectionId(String collection_id)throws ServletException, IOException{
	 
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		mysession.setAttribute("collection_id",collection_id);					
	}
			
	public Vector courseGrid(String Status)
	{
		String html="";
		Vector Details=new Vector();
		String uppertable = "";
		String innertable=""; 
		Boolean tt = false;	
		String baseName = "portal1";
		String coursename="";                // 	parentId = document.getElementById('main');
// 	child =document.getElementById('priviledgeDiv');
// 	parentId.appendChild(child);
		String cbookmark="";
		String cdateregistered=null;
		String caccessalowedtime;
		String ctotaltimepermited;
		String ctotaltimeused;
		String key1 = "";
		//String noofpostmade="";
		ResourceBundle rb = ResourceBundle.getBundle("portal1",Locale.getDefault());   
		key1="coursename";
		coursename = rb.getString(key1);
		key1="cbookmark";
		cbookmark = rb.getString(key1);
		key1="cdateregistered";
		cdateregistered = rb.getString(key1);
		key1="caccessalowedtime";
		caccessalowedtime = rb.getString(key1);
		key1="ctotaltimepermited";
		ctotaltimepermited = rb.getString(key1);
		key1="ctotaltimeused";
		ctotaltimeused = rb.getString(key1);
		//key1="noofpostmade";
		//noofpostmade = rb.getString(key1);
		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String collection_id = (String) mysession.getAttribute("collection_id");
		System.out.println("collection_id = "+collection_id);
		
		collection_id = (collection_id==null)?"":collection_id;
		Vector vCourseList=null;
		if(collection_id.equals("default") || collection_id.equals("")){
			vCourseList=DataBaseLayer.getCourseName(user_id,Status);
		}
		else{
			vCourseList=DataBaseLayer.getCourseName(user_id,collection_id,Status);
		}
		System.out.println("--------Size Of Vector-------"+vCourseList.size());
		if(vCourseList.size()==0)
		{
			String color1=(tt) ? "#EEE" : "#ffffff";
			innertable=innertable+
					"<tr style=\"background-color:"+color1+";\">"+
					"<td>No Courses Registered</a></td>"+
					"<td> </td>"+
					"<td> </td>"+
					"</tr>";
			innertable="{course_id:\"No Courses Registered\",course_name:\"No Courses Registered\",bookmark:\"\",date_registered:\"\",access_allowed_till:\"\"}";
			System.out.println("innertable============"+innertable);
			Details.addElement(innertable);
		}
		else		
		{
			for(int k=0; k<vCourseList.size();k++)
			{
				String color1=(tt) ? "#EEE" : "#ffffff";
	
				String strCourse[] =(String[])vCourseList.elementAt(k);
				//Vector vMessageList=DataBaseLayer.getforummessage(strForum[1]);
					//String strmessage =Integer.toString(vMessageList.size());
				String strcoursename =strCourse[1];
				System.out.println("strcoursename----->"+strcoursename);
				String strbookmark =strCourse[2];
				System.out.println("bookmark--------->"+strbookmark);
				String strregdate =strCourse[3];
				System.out.println("strregdate------------->"+strregdate);
				//System.out.println(""+strmessage[0]+"=============="+strmessage[1]);
					
				String straccessallowedtill=strCourse[4];
				System.out.println("straccessallowedtill------------->"+straccessallowedtill);
				String strtotaltime=strCourse[6];
				System.out.println("totaltime------------>"+strtotaltime);
				String strtotaltimeused=DataBaseLayer.courseaccess(user_id,strCourse[0]);
				if(strtotaltimeused==null)
					strtotaltimeused="";
				/*String strtotaltimeused=strCourse[6];
				System.out.println("totaltimeused------------>"+strtotaltimeused);*/
				String mark_servlet="";
				String mark_servlet_home="Home";
				if(straccessallowedtill== null || straccessallowedtill.equals("No Limit"))
				{
					straccessallowedtill="Not Applicable";
				}  
				if(strtotaltime==null || strtotaltime.equals("0") || strtotaltime.equals("No Limit"))
				{
					strtotaltime="Not Applicable";
				}
				else{
					strtotaltime=strtotaltime+"hrs";
				} 
				System.out.println("straccessallowedtill---------strtotaltime--->"+straccessallowedtill+"      === "+strtotaltime);
				
				if(!DataBaseLayer.isCourseaccesstimeValid(strCourse[0],user_id))
				{
					System.out.println("++++++++++++++++++++++++++++++++++++++++");
					if(!DataBaseLayer.isCourseaccessDateValid(strCourse[0],user_id))
				{
					if(strCourse[2]!=null){
						if(strCourse[2].equals("Home"))
						{
							mark_servlet="Home";
						}
						else if(strCourse[2].equals("Syllabus"))
						{
							mark_servlet="Syllabus";
						}
	        	                                                                     
						else if(strCourse[2].equals("Schedule"))
						{
							mark_servlet="Schedule";
						}
						else if(strCourse[2].equals("Resource"))
						{
							mark_servlet="Resource";
						}
	        	                                                                      
						else if(strCourse[2].equals("Announcements"))
						{
							mark_servlet="Announcements";
						}
						else if(strCourse[2].equals("Assignment"))
						{
							mark_servlet="Assignment";
						}
						else if(strCourse[2].equals("GradeBook"))
						{
							mark_servlet="GradeBook";
						}
						System.out.println("------------------->MARK SERVLET----- GRADEBOOK---->"+mark_servlet);
// 						innertable=innertable+"<tr style=\"background-color:"+color1+";\"><td><a href=\"javascript:launchCoursemanagement('"+strCourse[0]+"','"+strCourse[1]+"','"+mark_servlet_home+"');\">"+strCourse[1]+"</a></td>"+
// 								"<td><a href=\"javascript:launchCoursemanagementBookmark('"+strCourse[0]+"','"+strCourse[1]+"','"+mark_servlet+"');\">"+strCourse[2]+"</td>"+
// 								"<td> "+strCourse[3]+"</td>"+
// 								"<td> "+straccessallowedtill+"</td>"+
// 								//"<td> "+strtotaltime+"</td>"+     // commented temporaryly for reuse
// 								//"<td> "+strtotaltimeused+"</td>"+ // commented temporaryly for reuse
// 						//"<td> "+strCourse[1]+"</td>"+
// 								"</tr>";
						innertable="{course_id:\""+strCourse[0]+"\",course_name:\"<a href='javascript:launchCoursemanagement("+strCourse[0]+")'>"+strCourse[1]+"</a>\",bookmark:\"<a href='javascript:launchCoursemanagementBookmark(\\\""+strCourse[0]+"\\\",\\\""+strCourse[1]+"\\\",\\\""+mark_servlet+"\\\")'>"+strCourse[2]+"</a>\",date_registered:\""+strCourse[3]+"\",access_allowed_till:\""+straccessallowedtill+"\"}";
						System.out.println("innertable============"+innertable);
						Details.addElement(innertable);
						tt=!tt;	
					}
					else
					{
						mark_servlet="Home";
						color1=(tt) ? "#EEE" : "#ffffff";
						innertable=innertable+
								"<tr style=\"background-color:"+color1+";\"><td><a href=\"javascript:launchCoursemanagement('"+strCourse[0]+"','"+strCourse[1]+"','"+mark_servlet+"');\">"+strCourse[1]+"</a></td>"+
								"<td >Not Accessed Yet</td>"+
								"<td >"+strCourse[3]+"</td>"+
								"<td>"+straccessallowedtill+"</td>"+
								//"<td >"+strtotaltime+"</td>"+			// commented temporaryly for reuse
								//"<td >"+strtotaltimeused+"</td></tr>";	// commented temporaryly for reuse
								"</tr>";
						innertable="{course_id:\""+strCourse[0]+"\",course_name:\"<a href='javascript:launchCoursemanagement("+strCourse[0]+")'>"+strCourse[1]+"</a>\",bookmark:\"Not Accessed Yet\",date_registered:\""+strCourse[3]+"\",access_allowed_till:\""+straccessallowedtill+"\"}";
						System.out.println("innertable============"+innertable);
						Details.addElement(innertable);
						tt=!tt;			
					}
				}
				}
			}
		}
		

		
		//String links="<font style='font-size:11px;font-family:Verdana;font-weight:bold'><a href='javascript:course_onclick()'>grid</a>&nbsp; | &nbsp; <a href='javascript:course_onclick()'>DashBoard</a></font><br>";
		
		/*html=uppertable+"<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"662\" id=\"myScrollTable\">"+
				"<thead>"+
				"<tr><th>"+coursename+"</th><th>"+cbookmark+"</th><th>"+cdateregistered+"</th>"+
				"<th>"+caccessalowedtime+"</th><th>"+ctotaltimepermited+"</th><th>"+ctotaltimeused+"</th>"+
				"</tr></thead>"+"<tbody>"+innertable+"</tbody></table>";*/
// 		html=uppertable+"<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"662\" id=\"myScrollTable\">"+
// 				"<thead>"+
// 				"<tr><th>"+coursename+"</th><th>"+cbookmark+"</th><th>"+cdateregistered+"</th>"+
// 				"<th>"+caccessalowedtime+"</th>"+
// 				"</tr></thead>"+"<tbody>"+innertable+"</tbody></table>";
		return Details;
	}
	
	
			
	
	public String setSessioncourseId(String course_id, String topic_id)throws ServletException, IOException{
	
	 
		String html = "";
		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		mysession.setAttribute("course_id",course_id);					
		mysession.setAttribute("topic_id",topic_id);	

		//return wctx1.forwardToString("/servlet/cms.coursemgmt.Launch_Course_Mgmt?cm_course_id=c123&topic_id=Home");
	
		//return wctx1.forwardToString("/servlet/cms.coursemgmt.a");
		return ""  ;
											
	}
	
	public void setSessioncourseIdBookmark(String course_id,String topic_id)throws ServletException, IOException{
	
	 
		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String student_id = (String) mysession.getAttribute("user_id");
		mysession.setAttribute("course_id",course_id);	
		mysession.setAttribute("topic_id",topic_id);	
				
		
		//return wctx1.forwardToString("/servlet/cms.coursemgmt.Launch_Course_Mgmt?cm_course_id=c123&topic_id=Home");
	
		//return wctx1.forwardToString("/servlet/cms.coursemgmt.a");
											
	}
	
	public String setSessionTopicId(String course_id, String topic_id)throws ServletException, IOException{
	
	 
		String html = "";
		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		mysession.setAttribute("course_id",course_id);					
		mysession.setAttribute("topic_id",topic_id);	

		//return wctx1.forwardToString("/servlet/cms.coursemgmt.Launch_Course_Mgmt?cm_course_id=c123&topic_id=Home");
	
		//return wctx1.forwardToString("/servlet/cms.coursemgmt.a");
		return ""  ;
											
	}
 
		
	/***************************         Changes by Indra for Course Management(Portal)   ---  End    *********************************************************/ 

// 	
	/**************************  Anindya  *******************************/ 		
	
	public Vector forumGrid()
	{
		String html="";
		String innertable="";
		Vector Details=new Vector();
		Boolean tt = false;	
		String baseName = "portal1";
		String forumname="";
		String noofmess="";
		String startdate="";
		String lastpost="";
		String noofviews="";
		String noofthreadscreated="";
		String noofpostmade="";
		ResourceBundle rb = ResourceBundle.getBundle("portal1",Locale.getDefault());   
		String key1= "forumname"; 
		forumname = rb.getString(key1);
		key1="noofmess";
		noofmess = rb.getString(key1);
		key1="startdate";
		startdate = rb.getString(key1);
		key1="lastpost";
		lastpost = rb.getString(key1);
		key1="noofviews";
		noofviews = rb.getString(key1);
		key1="noofthreadscreated";
		noofthreadscreated = rb.getString(key1);
		key1="noofpostmade";
		noofpostmade = rb.getString(key1);
		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		Vector vForumList=DataBaseLayer.getForumName(user_id);
		log.debug("   THE VECTOR   ::"+vForumList );
		if(vForumList.size()==0)
		{
			String color=(tt) ? "#EEE" : "#ffffff";
			innertable=innertable+
					"<tr style=\"background-color:"+color+";\">"+
					"<td>No Forum Registered</td>"+
					"<td> </td>"+
					"<td> </td>"+
					"</tr>";
			innertable="{forum_id:\"0\",forum_name:\"No Forum Registered\",sdate:\"\",last_post:\"\",no_of_messages:\"\",no_of_views:\"\",no_of_threads:\"\",no_of_post:\"\"}";
			Details.addElement(innertable);
		}
		else		
		{
			for(int k=0; k<vForumList.size();k++)
			{
				String color=(tt) ? "#EEE" : "#ffffff";
	
				String strForum[] =(String[])vForumList.elementAt(k);
				Vector vMessageList=DataBaseLayer.getforummessage(strForum[1]);
					//String strmessage =Integer.toString(vMessageList.size());
				String strmessage =(String)vMessageList.elementAt(0);
				System.out.println("MESSAGE----->"+strmessage);
				String strcreatedon = strForum[2];
				String lastpostdate = strForum[3];
				//String strcreatedon =(String)vMessageList.elementAt(1);
				System.out.println("CREATED ON--------->"+strcreatedon);
				String strnoofviews =(String)vMessageList.elementAt(2);
				System.out.println("NO OF VIEWS------------->"+strnoofviews);
				//System.out.println(""+strmessage[0]+"=============="+strmessage[1]);
				//String postmade1 = strForum[3];	
				String postmade1=DataBaseLayer.postmade(strForum[0]);
				System.out.println("POST MADE------------->"+postmade1);
				String nothread=DataBaseLayer.thread1(strForum[0]);
				System.out.println("NO OF THREAD------------>"+nothread);
				if(lastpostdate==null||lastpostdate.equals("00-00-0000 00:00"))
					lastpostdate="";
				System.out.println("LAST POST DATE------------>"+lastpostdate);
				if(strnoofviews==null)
					strnoofviews="";
				if(nothread==null)
					nothread="";
				if(postmade1==null)
					postmade1="";
				
// 				innertable=innertable+
// 						"<tr style=\"background-color:"+color+";\"><td><a href=\"javascript:forum('"+strForum[0]+"','"+strForum[1]+"');\">"+strForum[1]+"</a></td>"+
// 						"<td><b>"+strcreatedon+"</b></td>"+
// 						"<td><b>"+lastpostdate+"</b></td>"+
// 						"<td><b>"+strmessage+"</b></td>"+
// 						"<td><b>"+strnoofviews+"</b></td>"+
// 						"<td><b>"+nothread+"</b></td>"+
// 						"<td><b>"+postmade1+"</b></td>"+
// 						"</tr>";
				innertable="{forum_id:\""+strForum[0]+"\",forum_name:\"<a href='javascript:forum(\\\""+strForum[0]+"\\\",\\\""+strForum[1]+"\\\")'>"+strForum[1]+"</a>\",sdate:\""+strcreatedon+"\",last_post:\""+lastpostdate+"\",no_of_messages:\""+strmessage+"\",no_of_views:\""+strnoofviews+"\",no_of_threads:\""+nothread+"\",no_of_post:\""+postmade1+"\"}";
				Details.addElement(innertable);
				tt=!tt;	
			}
		}
		
		//String links="<font style='font-size:11px;font-family:Verdana;font-weight:bold'><a href='javascript:forum_onclick()'>grid</a>&nbsp; | &nbsp; <a href='javascript:forum_onclick()'>DashBoard</a></font><br>";
		
/*		html="<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"660\" id=\"myScrollTable\">"+
				"<thead>"+
				"<tr><th>"+forumname+"</th><th>"+startdate+"</th><th>"+lastpost+"</th>"+
				"<th>"+noofmess+"</th><th>"+noofviews+"</th><th>"+noofthreadscreated+"</th><th>"+noofpostmade+"</th>"+
				"</tr></thead>"+"<tbody>"+innertable+"</tbody></table>";*/  
		return Details;
	}
			
			
			
	public String viewDropDown()
	{
		String html = "";
		String option = "";
		option="<option value='v0'>[Choose One]</option>"+
				"<option value='v1'>Tree View</option>"+
				"<option value='v2'>Flat View</option>";
		html="<select>"+option+"</select>";
		return html;
	}

	public String TextColorDropDown()
	{
		String html = "";
		String option = "";
		option="<option value='tc0'>Dark Red</option>"+
				"<option value='tc1'>Red</option>"+
				"<option value='tc2'>Orange</option>"+
				"<option value='tc3'>Brown</option>"+
				"<option value='tc4'>Yellow</option>"+
				"<option value='tc5'>Green</option>"+
				"<option value='tc6'>Olive</option>"+
				"<option value='tc7'>Cyan</option>"+
				"<option value='tc8'>Blue</option>"+
				"<option value='tc9'>Dark Blue</option>"+
				"<option value='tc10'>Violet</option>"+
				"<option value='tc11'>White</option>"+
				"<option value='tc12'>Black</option>";
		html="<select>"+option+"</select>";
		return html;
	}
	
	public String FontDropDown()
	{
		String html = "";
		String option = "";
		option="<option value='f0'>Very Small</option>"+
				"<option value='f1'>Small</option>"+
				"<option value='f2'>Medium</option>"+
				"<option value='f3'>Large</option>"+
				"<option value='f4'>Very Large</option>";
		html="<select>"+option+"</select>";
		return html;
	}
	

	public String displayforumname()
	{
		String html = "";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String forum_id = (String) mysession.getAttribute("forum_id");
		System.out.println("FORUM ID------------------->"+forum_id);
		String strdisplayforum=DataBaseLayer.returnforumname(forum_id);
		System.out.println("FORUM NAME------------------->"+strdisplayforum);
		html=strdisplayforum;
		return html;
	}

	public String forumGrid2()
	{
		String html="";
		String innertable=""; 
		Boolean tt = false;	
		String baseName = "portal1";
		String forumname="";
		String thread="";
		String author="";
		String messages="";
		String views="";
		String lastpost="";
	
// 		ResourceBundle rb = ResourceBundle.getBundle("portal1",Locale.getDefault());
// 		String key1= "forumname"; 
// 		forumname = rb.getString(key1);
// 		key1="thread";
// 		thread = rb.getString(key1);
// 		key1="author";
// 		author = rb.getString(key1);
// 		key1="messages";
// 		messages = rb.getString(key1);
// 		key1="views";
// 		views = rb.getString(key1);
// 		key1="lastpost";
// 		lastpost = rb.getString(key1);
				
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String forum_id = (String) mysession.getAttribute("forum_id");
		String strdisplayforum=DataBaseLayer.returnforumname(forum_id);
		System.out.println("FORUM ID IN FORUM GRID--------->"+strdisplayforum);
		Vector vThreadInfo=DataBaseLayer.getThreadInfo(strdisplayforum);
		log.debug("   THE FORUMS INSIDE   ::"+vThreadInfo );
		if(vThreadInfo.size()==0)
		{
			String color=(tt) ? "#FFFFCC" : "#FFFFFF";
			innertable=innertable+
					"<tr style=\"background-color:"+color+";\">"+
					"<td><div style=\"width:750px;text-align:center;font-family:verdana;font-size:14px;color:black;\"><b>No Message Posted</b></a></td>"+
					"</tr>";
		}
		else		
		{
			for(int i=0;i<vThreadInfo.size();i++)
			{
				String color=(tt) ? "#FFFFCC" : "#CCCCCC";
			
				String str[]=new String[6];
				str=(String[])vThreadInfo.elementAt(i);
				Vector vtotalview=DataBaseLayer.getForumThreadInfo(strdisplayforum,str[1]);
	 		
				int totalview=vtotalview.size()+1;
				String TotView=Integer.toString(totalview);
	 		
				Vector vnomessage = DataBaseLayer.getnoofmessage(strdisplayforum,str[1]);
				int totnoofmessage = vnomessage.size()+1;
				String totmeassage = Integer.toString(totnoofmessage);

				innertable=innertable+
						"<tr style=\"background-color:"+color+";\"><td><div style=\"width:295px;text-align:center;font-family:verdana;font-size:13px;\"><a href=\"javascript:show_thread('"+str[1]+"','"+str[2]+"');\">"+str[2]+"</a></td>"+
						"<td><div style=\"width:126px;text-align:center;font-family:verdana;font-size:13px;\">"+str[3]+"</td>"+
						"<td><div style=\"width:78px;text-align:center;font-family:verdana;font-size:12px;\">"+totmeassage+"</td>"+
						"<td><div style=\"width:78px;text-align:center;font-family:verdana;font-size:12px;\">"+str[5]+"</td>"+
						"<td><div style=\"width:150px;text-align:center;font-family:verdana;font-size:12px;\">"+str[6]+"</td>"+
						"</tr>";
				tt=!tt;	

			}
		}
		String links="";
		//String links="<font style='font-size:4px;font-family:Verdana;font-weight:bold'></font><br>";
		html=links+"<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"660\" id=\"myScrollTable\">"+
				"<thead><tr></tr></thead>"+"<tbody>"+innertable+"</tbody></table>";
		return html;
	} 
	
	
	public String detailmessage()
	{
		String html="";
		String innertable=""; 
		Boolean tt = false;	
		String baseName = "portal1";
		String forumname="";
		String thread="";
		String author="";
		String messages="";
		String views="";
		String lastpost="";
	
// 		ResourceBundle rb = ResourceBundle.getBundle("portal1",Locale.getDefault());
// 		String key1= "forumname"; 
// 		forumname = rb.getString(key1);
// 		key1="thread";
// 		thread = rb.getString(key1);
// 		key1="author";
// 		author = rb.getString(key1);
// 		key1="messages";
// 		messages = rb.getString(key1);
// 		key1="views";
// 		views = rb.getString(key1);
// 		key1="lastpost";
// 		lastpost = rb.getString(key1);
				
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String forum_id = (String) mysession.getAttribute("forum_id");
		String strdisplayforum=DataBaseLayer.returnforumname(forum_id);
		System.out.println("FORUM ID IN FORUM GRID--------->"+strdisplayforum);
		Vector vThreadInfo=DataBaseLayer.getAllMessages(strdisplayforum);
		log.debug("   THE FORUMS INSIDE   ::"+vThreadInfo );
		if(vThreadInfo.size()==0)
		{
			String color=(tt) ? "#FFFFCC" : "#FFFFFF";
			innertable=innertable+
					"<tr style=\"background-color:"+color+";\">"+
					"<td><div style=\"width:750px;text-align:center;font-family:verdana;font-size:14px;color:black;\"><b>No Message Posted</b></a></td>"+
					"</tr>";
		}
		else		
		{
			for(int i=0;i<vThreadInfo.size();i++)
			{
				String color= "#E3E7E7" ;
			
				String str[]=new String[6];
				str=(String[])vThreadInfo.elementAt(i);
				//Vector vtotalview=DataBaseLayer.getForumThreadInfo(strdisplayforum,str[1]);
	 		
				//int totalview=vtotalview.size()+1;
				//String TotView=Integer.toString(totalview);
	 		
				//Vector vnomessage = DataBaseLayer.getnoofmessage(strdisplayforum,str[1]);
				//int totnoofmessage = vnomessage.size()+1;
				//String totmeassage = Integer.toString(totnoofmessage);

				innertable=innertable+
						"<tr style=\"background-color:"+color+";\"><td><div style=\"width:295px;text-align:center;font-family:verdana;font-size:13px;\"><b>Subject:</b>"+str[2]+"</td>"+
						"<td><div style=\"width:126px;text-align:center;font-family:verdana;font-size:13px;\">"+str[1]+"</td>"+
						"<td><div style=\"width:78px;text-align:center;font-family:verdana;font-size:12px;\">Posting Date: "+str[4]+"</td>"+
						"<td><div style=\"width:78px;text-align:center;font-family:verdana;font-size:12px;\">"+str[5]+"</td>"+
						"<td><div style=\"width:150px;text-align:center;font-family:verdana;font-size:12px;\">Posted By: "+str[3]+"</td>"+
						"</tr>";
				tt=!tt;	

			}
		}
		String links="";
		//String links="<font style='font-size:4px;font-family:Verdana;font-weight:bold'></font><br>";
		html=links+"<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"660\" id=\"myScrollTable\">"+
				"<thead><tr></tr></thead>"+"<tbody>"+innertable+"</tbody></table>";
		return html;
	}


	public void setForumId(String n)
	{
	
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		mysession.setAttribute("forum_id",n);
		System.out.println("SET IN FORUM ID---------------->"+n);
		String user_id = (String) mysession.getAttribute("user_id");
		if(user_id==null){user_id="";}
		String session_id = mysession.getId();
		String logintime = DataBaseLayer.getLoginTime(user_id,session_id);
		log.debug("========session_id,logintime===== "+session_id+" , "+logintime);
		DataBaseLayer.setForumDynamicInfo(user_id,session_id,logintime,n);
	
	}


	
	
	/*********************** Appended By Kunal On 03. 10. 2008 **************************************/ 
	public Vector getSceGrid() {
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		javax.servlet.http.HttpServletRequest request = wctx1.getHttpServletRequest();
		javax.servlet.http.HttpServletResponse response = wctx1.getHttpServletResponse();
		String user_id = (String) mysession.getAttribute("user_id");
		String sceGrid = "";
		Vector Details = new Vector();
		Boolean toggle = false;
		String color = "";
		Vector vSynchronousList = learnitysce.SceDataBaseLayer.getSynchronousList(user_id);
		if(vSynchronousList.size()< 1) {
			color= (toggle) ? "#EEE" : "#ffffff";
			sceGrid = sceGrid+"<tr>"+
					"<td style=\"background-color:"+color+";\">No Synchronous Collaboration Envirnoment Registered</td>"+
					"<td></td>"+
					"<td></td>"+
					"<td></td>"+
					"<td></td>"+
					"</tr>";
			toggle = !toggle;
		} else {
			Vector vSynName = new Vector();
			Vector v = null;
  		                                                   
			for(int s = 0; s<vSynchronousList.size(); s++)
			{
				Vector vSysList = (Vector)vSynchronousList.elementAt(s);
				vSynName.addElement((String)vSysList.elementAt(1));
			}
			String strContext = wctx1.getServletConfig().getServletContext().getServletContextName();
			String location = strContext+"/servlet/ChatServlet";
			location = location.trim();
			System.out.println("-----------Location-------->>>>>>>>----- "+location);
			try {
		  
				String strHost = request.getServerName();
				strHost=strHost.trim();
				System.out.println(strHost);
				int iPort = request.getServerPort();
				System.out.println(iPort);
				URL ur = new URL("http", strHost, iPort, location);
				URLConnection conn = ur.openConnection();
			                                                   
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setUseCaches(false);
				conn.setRequestProperty("Content-Type","application/x-java-serialized-object");
				ObjectOutputStream outputToServlet = new ObjectOutputStream(conn.getOutputStream());
				outputToServlet.writeObject(vSynName);
			
				outputToServlet.flush();
				outputToServlet.close();
			
				ObjectInputStream in = new ObjectInputStream(conn.getInputStream());
				v = (Vector)in.readObject();
				System.out.println("-----------Location-------->>>>>>>>----- " + v);
				in.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			for(int s=0; s<vSynchronousList.size(); s++) {
				Vector vSysList = (Vector)vSynchronousList.elementAt(s);
				String strType = (String)vSysList.elementAt(4);
				String strStart = (String)vSysList.elementAt(5);
				String strEnd = (String)vSysList.elementAt(6);
				String strSessionID = (String)vSysList.elementAt(7);
				String strRSession = (String)vSysList.elementAt(8);
				System.out.println("strStart=========="+strStart+"=============strEnd===="+strEnd);
				String reg_members = learnitysce.SceDataBaseLayer.SCERegisteredMembers((String)vSysList.elementAt(0));

				Calendar calendarnow = new GregorianCalendar();
				Date trialTime1 = new Date();
				calendarnow.setTime(trialTime1);
        	
				String strTime1 = calendarnow.get(Calendar.HOUR_OF_DAY)+":"+ calendarnow.get(Calendar.MINUTE)+":"+ calendarnow.get(Calendar.SECOND);
				String strDate1 = calendarnow.get(Calendar.YEAR)+"-"+ (calendarnow.get(Calendar.MONTH)+1)+"-"+ calendarnow.get(Calendar.DATE);
			
				String strCurrent=strDate1+" "+strTime1;
				System.out.println("strCurrent============"+strCurrent);
				String myFormatString = "yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat df = new SimpleDateFormat(myFormatString);
				color = (toggle) ? "#EEE" : "#ffffff";
				try
				{
					java.util.Date start = null;
					java.util.Date end = null;
					if(!strStart.equals("")) {
						start = df.parse(strStart);
						System.out.println("Start after parse======="+start);
					}
					if(!strEnd.equals("")) {
						end = df.parse(strEnd);
						System.out.println("End after parse======="+end);
					}
					Date current = df.parse(strCurrent);
					System.out.println("------strType--------->>>>>>>>>>>>>------------------"+strType);
				                                                        
					if(strType.equals("R")) {
						if (strStart.equals("") || strEnd.equals("")) {
							String RSECID = strRSession.substring(0,strRSession.indexOf('-'));
							String strRSession1 = strRSession.substring(strRSession.indexOf('-')+1);
								
						                                                                             						
							System.out.println("-------Available-------->>>>>>>>>>>>>------------------");
// 							sceGrid = sceGrid+"<tr style=\"background-color:"+color+";\">"+
// 									"<td><a href=\"javascript:chatLunchRecorded('"+RSECID+"','"+strRSession1+"');\">"+(String)vSysList.elementAt(1)+"</td>"+
// 									"<td>recorded</td>"+
// 									"<td>available</td>"+
// 									"<td>"+reg_members+"</td>"+
// 									"<td></td>"+
// 									"</tr>";
							sceGrid="{sce_id:\""+(String)vSysList.elementAt(0)+"\",sce_name:\"<a href='javascript:chatLunchRecorded(\\\""+RSECID+"\\\",\\\""+strRSession1+"\\\")'>"+(String)vSysList.elementAt(1)+"</a>\",type:\"recorded\",status:\"available\",no_of_reg_members:\""+reg_members+"\",no_of_members_online:\"\"}";
							Details.addElement(sceGrid);
						} else {
							if (current.after(start) && current.before(end)) {
								System.out.println("---Recorded----current.after(start) && current.before(end)-------->>>>>>>>>>>>>---current==-"+current+"---start=="+start+"---end=----"+end+"----------------------");
								String RSECID = strRSession.substring(0,strRSession.indexOf('-'));
								String strRSession1 = strRSession.substring(strRSession.indexOf('-')+1);
							
								System.out.println("-------Not Available-------->>>>>>>>>>>>>------------------");
// 								sceGrid = sceGrid+"<tr style=\"background-color:"+color+";\">"+
// 										"<td><a href=\"javascript:chatLunchRecorded('"+RSECID+"','"+strRSession1+"');\">"+(String)vSysList.elementAt(1)+"</td>"+
// 										"<td>recorded</td>"+
// 										"<td>available</td>"+
// 										"<td>"+reg_members+"</td>"+
// 										"<td></td>"+
// 										"</tr>";
								sceGrid="{sce_id:\""+(String)vSysList.elementAt(0)+"\",sce_name:\"<a href='javascript:chatLunchRecorded(\\\""+RSECID+"\\\",\\\""+strRSession1+"\\\")'>"+(String)vSysList.elementAt(1)+"</a>\",type:\"recorded\",status:\"available\",no_of_reg_members:\""+reg_members+"\",no_of_members_online:\"\"}";
								Details.addElement(sceGrid);
							}
						}
					} else {
						if (strStart.equals("") || strEnd.equals("")){
							String RSECID = strRSession.substring(0,strRSession.indexOf('-'));
							String strRSession1 = strRSession.substring(strRSession.indexOf('-')+1);
							                        	
// 							sceGrid = sceGrid+"<tr style=\"background-color:"+color+";\">"+
// 									"<td><a href=\"javascript:chatLunchRecorded('"+RSECID+"','"+strRSession1+"');\">"+(String)vSysList.elementAt(1)+"</td>"+
// 									"<td>recorded</td>"+
// 									"<td>available</td>"+
// 									"<td>"+reg_members+"</td>"+
// 									"<td></td>"+
// 									"</tr>";
							sceGrid="{sce_id:\""+(String)vSysList.elementAt(0)+"\",sce_name:\"<a href='javascript:chatLunchRecorded(\\\""+RSECID+"\\\",\\\""+strRSession1+"\\\")'>"+(String)vSysList.elementAt(1)+"</a>\",type:\"recorded\",status:\"available\",no_of_reg_members:\""+reg_members+"\",no_of_members_online:\"\"}";
							Details.addElement(sceGrid);
						} else {
							
							if (current.before(start)) {
								System.out.println("-------current.before(start)-------->>>>>>>>>>>>>---current==-"+current+"---start=="+start+"---end=----"+end+"----------------------");
// 								sceGrid = sceGrid+"<tr style=\"background-color:"+color+";\">"+
// 										"<td><a href=\"javascript:chatLunch('"+(String)vSysList.elementAt(0)+"','"+strSessionID+"');\">"+(String)vSysList.elementAt(1)+"</td>"+
// 										"<td>live</td>"+
// 										"<td>started</td>"+
// 										"<td>"+reg_members+"</td>"+
// 										"<td>"+(String)v.elementAt(s)+"</td>"+
// 										"</tr>";
								sceGrid="{sce_id:\""+(String)vSysList.elementAt(0)+"\",sce_name:\"<a href='javascript:chatLunch(\\\""+(String)vSysList.elementAt(0)+"\\\",\\\""+strSessionID+"\\\")'>"+(String)vSysList.elementAt(1)+"</a>\",type:\"Live\",status:\"started\",no_of_reg_members:\""+reg_members+"\",no_of_members_online:\""+(String)v.elementAt(s)+"\"}";
								Details.addElement(sceGrid);
								
							} else if(current.after(start) && current.before(end)) {
								System.out.println("-------current.after(start) && current.before(end)-------->>>>>>>>>>>>>------------------");
// 								sceGrid = sceGrid+"<tr style=\"background-color:"+color+";\">"+
// 										"<td><a href=\"javascript:chatLunch('"+(String)vSysList.elementAt(0)+"','"+strSessionID+"');\">"+(String)vSysList.elementAt(1)+"</td>"+
// 										"<td>live</td>"+
// 										"<td>started</td>"+
// 										"<td>"+reg_members+"</td>"+
// 										"<td>"+(String)v.elementAt(s)+"</td>"+
// 										"</tr>";
								sceGrid="{sce_id:\""+(String)vSysList.elementAt(0)+"\",sce_name:\"<a href='javascript:chatLunch(\\\""+(String)vSysList.elementAt(0)+"\\\",\\\""+strSessionID+"\\\")'>"+(String)vSysList.elementAt(1)+"</a>\",type:\"Live\",status:\"started\",no_of_reg_members:\""+reg_members+"\",no_of_members_online:\""+(String)v.elementAt(s)+"\"}";
								Details.addElement(sceGrid);
							} else if(current.after(end)) {
								
								System.out.println("-------current.after(end)-------->>>>>>>>>>>>>---current==-"+current+"---start=="+start+"---end=----"+end+"----");
// 								sceGrid = sceGrid+"<tr style=\"background-color:"+color+";\">"+
// 										"<td><a href=\"javascript:chatLunch('"+(String)vSysList.elementAt(0)+"','"+strSessionID+"');\">"+(String)vSysList.elementAt(1)+"</td>"+
// 										"<td>live</td>"+
// 										"<td>started</td>"+
// 										"<td>"+reg_members+"</td>"+
// 										"<td>"+(String)v.elementAt(s)+"</td>"+
// 										"</tr>";
								sceGrid="{sce_id:\""+(String)vSysList.elementAt(0)+"\",sce_name:\"<a href='javascript:chatLunch(\\\""+(String)vSysList.elementAt(0)+"\\\",\\\""+strSessionID+"\\\")'>"+(String)vSysList.elementAt(1)+"</a>\",type:\"Live\",status:\"started\",no_of_reg_members:\""+reg_members+"\",no_of_members_online:\""+(String)v.elementAt(s)+"\"}";
								Details.addElement(sceGrid);
							}
						}
					}
					
				} catch(Exception ex) {
					System.out.println("ParseException" + ex);
					ex.printStackTrace();
				}
			}
			toggle = !toggle;
		}
			                                                                                        			
		String html="<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"660\" id=\"myScrollTable\">"+
				"<thead><tr><td>SCE Name</td><td>Type</td><td>Status</td><td>No. of Registered Members</td><td>No. of Members Online</td></tr></thead>"+"<tbody>"+sceGrid+"</tbody></table>";
		return Details;
	}

			/************************** End Kunal 03.10.2008 ****************/	
	
	
/*=======================================Nayna 20.10.08==========================================*/

			public Vector AssessmentGrid()
			{
				String html="";
				String innertable="";
				Vector Details = new Vector();
				WebContext wctx1 = WebContextFactory.get();
				javax.servlet.http.HttpSession mysession = wctx1.getSession();
				String student_id = (String) mysession.getAttribute("user_id");
				Boolean tt=false;
				String color=(tt) ? "#EEE" : "#ffffff";
				AsmtPortalDataBaseLayer ob1=new AsmtPortalDataBaseLayer();
				Vector vAssessmentInfo=ob1.getAssessmentInfo1(student_id);
				Vector vAssessmentInfogroup=ob1.getAssessmentInfogroup(student_id);
				System.out.println("vAssessmentInfo========="+vAssessmentInfo);
				if(vAssessmentInfo.size()==0)
				{//1
					System.out.println("vAssessmentInfogroup========="+vAssessmentInfogroup);
					if(vAssessmentInfogroup.size()==0)
					{//2
			
					}// end 2
					else{ //4 
						Vector assessmentsearch=new Vector();
						System.out.println("assessmentsearch======"+assessmentsearch);
						System.out.println("assessmentsearch.size()======"+assessmentsearch.size());
							
						for(int i=0; i<vAssessmentInfogroup.size();i++)
						{
							
							String strAssess[] =(String[])vAssessmentInfogroup.elementAt(i);
							String availablitydate="";
							String valid_upto="";
							if (!strAssess[2].equals("")) {
								String str11 = strAssess[2].substring(0,strAssess[2].indexOf(' '));
								String str1 = str11.substring(0,str11.indexOf('-'));
								String str2 = str11.substring(str11.indexOf('-')+1);
								String str3 = str2.substring(0,str2.indexOf('-'));
								String str4 = str2.substring(str2.indexOf('-')+1);
								availablitydate = str4+"-"+str3+"-"+str1;
							}
							if (!strAssess[3].equals("")) {
								String str11 = strAssess[3].substring(0,strAssess[2].indexOf(' '));
								String str1 = str11.substring(0,str11.indexOf('-'));
								String str2 = str11.substring(str11.indexOf('-')+1);
								String str3 = str2.substring(0,str2.indexOf('-'));
								String str4 = str2.substring(str2.indexOf('-')+1);
								valid_upto = str4+"-"+str3+"-"+str1;
							}
							String no_of_ques=ob1.getTotalquesno(strAssess[0]);
							String status=ob1.getLastStatus(student_id,strAssess[0]);
							if(assessmentsearch.size()==0){
								if(status==null)
									status="";
// 								innertable=innertable+
// 										"<tr style=assessment\"background-color:"+color+";\">"+
// 										"<td><input type=\"radio\" name=\"checkbox\" value="+strAssess[0]+" onClick=\"check_onclick()\"> </td>"+
// 										"<td>"+strAssess[1]+"</td>"+
// 										"<td>"+availablitydate+"</td>"+
// 										"<td> "+valid_upto+"</td>"+
// 										"<td> "+strAssess[5]+"</td>"+
// 										"<td> "+strAssess[4]+"</td>"+
// 										"<td> "+no_of_ques+"</td>"+
// 										"<td> "+status+"</td>"+
// 										"</tr>";
								innertable="{assessment_id:\""+strAssess[0]+"\",title:\""+strAssess[1]+"\",available_date:\""+availablitydate+"\",access_allowed_till:\""+valid_upto+"\",pass_mark:\""+strAssess[5]+"\",test_time:\""+strAssess[4]+"\",no_of_question:\""+no_of_ques+"\",status:\""+status+"\"}";
								Details.addElement(innertable);
								assessmentsearch.addElement(strAssess[0]);
								System.out.println("assessmentsearch.size()==0======"+assessmentsearch);
							}
							else{
								if(!assessmentsearch.contains(strAssess[0])){
								
									if(status==null)
										status="";
// 									innertable=innertable+
// 										"<tr style=assessment\"background-color:"+color+";\">"+
// 										"<td><input type=\"radio\" name=\"checkbox\" value="+strAssess[0]+" onClick=\"check_onclick()\"> </td>"+
// 										"<td>"+strAssess[1]+"</td>"+
// 										"<td>"+availablitydate+"</td>"+
// 										"<td> "+valid_upto+"</td>"+
// 										"<td> "+strAssess[5]+"</td>"+
// 										"<td> "+strAssess[4]+"</td>"+
// 										"<td> "+no_of_ques+"</td>"+
// 										"<td> "+status+"</td>"+
// 										"</tr>";
									innertable="{assessment_id:\""+strAssess[0]+"\",title:\""+strAssess[1]+"\",available_date:\""+availablitydate+"\",access_allowed_till:\""+valid_upto+"\",pass_mark:\""+strAssess[5]+"\",test_time:\""+strAssess[4]+"\",no_of_question:\""+no_of_ques+"\",status:\""+status+"\"}";
									Details.addElement(innertable);
									assessmentsearch.addElement(strAssess[0]);
									System.out.println("assessmentsearch====else======"+assessmentsearch);
								}
							
							}
							tt=!tt;	
				
						}   //end for                 
					}// end 4 
				}//end 1
				else {//3
					if(vAssessmentInfogroup.size()==0)
					{//5
						
						for(int i=0; i<vAssessmentInfo.size();i++)
						{
							String strAssess[] =(String[])vAssessmentInfo.elementAt(i);
							String availablitydate="";
							String valid_upto="";
							if (!strAssess[2].equals("")) {
								String str11 = strAssess[2].substring(0,strAssess[2].indexOf(' '));
				//String str12 = strAssess[2].substring(strAssess[2].indexOf(' ')+1);
								String str1 = str11.substring(0,str11.indexOf('-'));
								String str2 = str11.substring(str11.indexOf('-')+1);
								String str3 = str2.substring(0,str2.indexOf('-'));
								String str4 = str2.substring(str2.indexOf('-')+1);
								availablitydate = str4+"-"+str3+"-"+str1;
							}
							if (!strAssess[3].equals("")) {
								String str11 = strAssess[3].substring(0,strAssess[2].indexOf(' '));
								String str1 = str11.substring(0,str11.indexOf('-'));
								String str2 = str11.substring(str11.indexOf('-')+1);
								String str3 = str2.substring(0,str2.indexOf('-'));
								String str4 = str2.substring(str2.indexOf('-')+1);
								valid_upto = str4+"-"+str3+"-"+str1;
							}
							String no_of_ques=ob1.getTotalquesno(strAssess[0]);
							String status=ob1.getLastStatus(student_id,strAssess[0]);
// 				
							if(status==null)
								status="";
// 							innertable=innertable+
// 									"<tr style=assessment\"background-color:"+color+";\">"+
// 									"<td><input type=\"radio\" name=\"checkbox\" value="+strAssess[0]+" onClick=\"check_onclick()\"> </td>"+
// 									"<td>"+strAssess[1]+"</td>"+
// 									"<td>"+availablitydate+"</td>"+
// 									"<td> "+valid_upto+"</td>"+
// 									"<td> "+strAssess[5]+"</td>"+
// 									"<td> "+strAssess[4]+"</td>"+
// 									"<td> "+no_of_ques+"</td>"+
// 									"<td> "+status+"</td>"+
// 									"</tr>";
							innertable="{assessment_id:\""+strAssess[0]+"\",title:\""+strAssess[1]+"\",available_date:\""+availablitydate+"\",access_allowed_till:\""+valid_upto+"\",pass_mark:\""+strAssess[5]+"\",test_time:\""+strAssess[4]+"\",no_of_question:\""+no_of_ques+"\",status:\""+status+"\"}";
							Details.addElement(innertable);
							tt=!tt;	
						}
				
					}// end 5
					else {//6
						int sizeass=vAssessmentInfogroup.size()+vAssessmentInfo.size();
			
						for(int i=0; i<sizeass;i++)
						{
							String strAssess[]=new String[8];
							int size11=vAssessmentInfo.size();
							if (i<size11) {
								strAssess =(String[])vAssessmentInfo.elementAt(i);
							}
							else{
								int j=i-vAssessmentInfo.size();
								strAssess =(String[])vAssessmentInfogroup.elementAt(j);
							}
							String availablitydate="";
							String valid_upto="";
							if (!strAssess[2].equals("")) {
								String str11 = strAssess[2].substring(0,strAssess[2].indexOf(' '));
				//String str12 = strAssess[2].substring(strAssess[2].indexOf(' ')+1);
								String str1 = str11.substring(0,str11.indexOf('-'));
								String str2 = str11.substring(str11.indexOf('-')+1);
								String str3 = str2.substring(0,str2.indexOf('-'));
								String str4 = str2.substring(str2.indexOf('-')+1);
								availablitydate = str4+"-"+str3+"-"+str1;
							}
							if (!strAssess[3].equals("")) {
								String str11 = strAssess[3].substring(0,strAssess[2].indexOf(' '));
								String str1 = str11.substring(0,str11.indexOf('-'));
								String str2 = str11.substring(str11.indexOf('-')+1);
								String str3 = str2.substring(0,str2.indexOf('-'));
								String str4 = str2.substring(str2.indexOf('-')+1);
								valid_upto = str4+"-"+str3+"-"+str1;
							}
							String no_of_ques=ob1.getTotalquesno(strAssess[0]);
							String status=ob1.getLastStatus(student_id,strAssess[0]);
// 							
							Vector assessmentsearch=new Vector();
							System.out.println("assessmentsearch======"+assessmentsearch);
							System.out.println("assessmentsearch.size()======"+assessmentsearch.size());
							
							if(assessmentsearch.size()==0){
								if(status==null)
									status="";
// 								innertable=innertable+
// 									"<tr style=assessment\"background-color:"+color+";\">"+
// 									"<td><input type=\"radio\" name=\"checkbox\" value="+strAssess[0]+" onClick=\"check_onclick()\"> </td>"+
// 									"<td>"+strAssess[1]+"</td>"+
// 									"<td>"+availablitydate+"</td>"+
// 									"<td> "+valid_upto+"</td>"+
// 									"<td> "+strAssess[5]+"</td>"+
// 									"<td> "+strAssess[4]+"</td>"+
// 									"<td> "+no_of_ques+"</td>"+
// 									"<td> "+status+"</td>"+
// 									"</tr>";
								innertable="{assessment_id:\""+strAssess[0]+"\",title:\""+strAssess[1]+"\",available_date:\""+availablitydate+"\",access_allowed_till:\""+valid_upto+"\",pass_mark:\""+strAssess[5]+"\",test_time:\""+strAssess[4]+"\",no_of_question:\""+no_of_ques+"\",status:\""+status+"\"}";
								Details.addElement(innertable);
								assessmentsearch.addElement(strAssess[0]);
								System.out.println("assessmentsearch.size()==0======"+assessmentsearch);
							}
							else{
								if(!assessmentsearch.contains(strAssess[0])){
								
									if(status==null)
										status="";
// 									innertable=innertable+
// 										"<tr style=assessment\"background-color:"+color+";\">"+
// 										"<td><input type=\"radio\" name=\"checkbox\" value="+strAssess[0]+" onClick=\"check_onclick()\"> </td>"+
// 										"<td>"+strAssess[1]+"</td>"+
// 										"<td>"+availablitydate+"</td>"+
// 										"<td> "+valid_upto+"</td>"+
// 										"<td> "+strAssess[5]+"</td>"+
// 										"<td> "+strAssess[4]+"</td>"+
// 										"<td> "+no_of_ques+"</td>"+
// 										"<td> "+status+"</td>"+
// 										"</tr>";
									innertable="{assessment_id:\""+strAssess[0]+"\",title:\""+strAssess[1]+"\",available_date:\""+availablitydate+"\",access_allowed_till:\""+valid_upto+"\",pass_mark:\""+strAssess[5]+"\",test_time:\""+strAssess[4]+"\",no_of_question:\""+no_of_ques+"\",status:\""+status+"\"}";
									Details.addElement(innertable);
									assessmentsearch.addElement(strAssess[0]);
									System.out.println("assessmentsearch====else======"+assessmentsearch);
								}
							
							}
						}		tt=!tt;	
						
					}//end 6
				}// end 3
	
	//Vector vInfo=asmtAvailablityCheck();
	//log.debug("vInfovInfo==========="+vInfo);
// 				html="<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"660\" id=\"myScrollTable\">"+
// 					"<thead>"+
// 					"<tr><th>Select</th><th>Title</th><th>Available from</th><th>Access Allowed Till</th><th>Pass Mark</th><th>Test Time (min)</th><th>No of question</th><th>Status</th></tr></thead>"+
// 						"<tbody>"+innertable+"</tbody></table>";
// 					"<div style=\"background-color:#FFFFFF;position:absolute;left :270px; top:322px;width:150px; height:25px;\"><input type=\"button\" name=\"Start\" value=\"StartTest\" onClick=\"start_onclick()\"></div>";
				
				return Details;
			}
			public void setCounter(String assessmentid)
			{
				WebContext wctx1 = WebContextFactory.get();
				javax.servlet.http.HttpSession mysession = wctx1.getSession();
				log.debug("==assessmentid== "+assessmentid);
				mysession.setAttribute("assessmentid",assessmentid);
	
			}

			public String[] asmtAvailablityCheck(String checkbox)
			{
				log.debug("===checkbox====checkbox==="+checkbox);
				///////////////////////////////////////////////////////
				Calendar calendar = new GregorianCalendar();
				Date trialTime = new Date();
				calendar.setTime(trialTime);

		//create array of string to hold days.
				String months[]={"January","Feburary","March", "April", "May","June",
					"July","August","September","October","November","December"};
					String mimute11=Integer.toString(calendar.get(Calendar.MINUTE));
					String mimute1="";
					if(mimute11.length()!=2)
						mimute1="0"+mimute11;
					else
						mimute1=mimute11;
					String sec11=Integer.toString(calendar.get(Calendar.SECOND));
					String sec1="";
					if(sec11.length()!=2)
						sec1="0"+sec11;
					else
						sec1=sec11;
					String strTime = calendar.get(Calendar.HOUR)+":"+ mimute1+":"+ sec1; 
		
					String strDate = months[calendar.get(Calendar.MONTH)]+" "+ calendar.get(Calendar.DATE)+", "+ calendar.get(Calendar.YEAR);
					String month11=Integer.toString(calendar.get(Calendar.MONTH)+1);
					String month="";
					if(month11.length()!=2)
						month="0"+month11;
					else
						month=month11;
					String date11=Integer.toString(calendar.get(Calendar.DATE));
					String date1="";
					if(date11.length()!=2)
						date1="0"+date11;
					else
						date1=date11;
					String strDate1 = calendar.get(Calendar.YEAR)+"-"+month+"-"+date1;
					String ampm=""+calendar.get(Calendar.AM_PM);
	
					/////////////////////////////////////////////////////
	
					WebContext wctx1 = WebContextFactory.get();
					javax.servlet.http.HttpSession mysession = wctx1.getSession();
					String student_id = (String) mysession.getAttribute("user_id");
		//String checkbox = (String) mysession.getAttribute("assessmentid");
		//String counter = (String) mysession.getAttribute("counter");
// 		log.debug("asmtAvailablityCheck==assessmentid== "+checkbox);
					String checkModelstatus="checkModelstatus";
					String avail_time="";
					String avail="";
					String valid="";
					String avail_date="";
					String valid_date="";
					String timeavailable="";
					AsmtPortalDataBaseLayer ob1=new AsmtPortalDataBaseLayer();
					Vector vAssessmentInfo=ob1.getAssessmentInfo1(student_id);
					if(checkbox!=null) {
						if(vAssessmentInfo.size()!=0) {
							Vector vAssessmentAvailablity=ob1.getAssessmentAvailablity(student_id,checkbox);
							if(vAssessmentAvailablity.size()!=0) {
								String AssessmentAvailablity[] =(String[])vAssessmentAvailablity.elementAt(0);
								avail_time=AssessmentAvailablity[0];
								avail_date=AssessmentAvailablity[1];
								avail=AssessmentAvailablity[2];
								valid_date=AssessmentAvailablity[3];
								valid=AssessmentAvailablity[4];
						//String stdate=strDate1+" "+"00:00:00";
								String avail_date11 = avail_date.substring(0,avail_date.indexOf(" "));
								int t1 =0;
								int t2 =0;
								int t11 =0;
								int t22 =0;
					
								if((avail.equals("Available")) && (avail_date11.equals(strDate1)))
								{
									if (!avail_time.equals("")) {
										String str1 = avail_time.substring(0,avail_time.indexOf(':'));
										String str2 = avail_time.substring(avail_time.indexOf(':')+1);
										String str3 = str2.substring(0,str2.indexOf(':'));
	        						
										String str11 = strTime.substring(0,strTime.indexOf(':'));
										String str22 = strTime.substring(strTime.indexOf(':')+1);
										String str33 = str22.substring(0,str22.indexOf(':'));
	        						
										t1 =Integer.parseInt(str1);
										t2 =Integer.parseInt(str3);
								
										int timeformat1 =Integer.parseInt(str11);
										if ((ampm.equals("1")) && (timeformat1<=12)) {
										t11 =Integer.parseInt(str11)+12;
										t22 =Integer.parseInt(str33);
										}
										else {
										t11 =Integer.parseInt(str11);
										t22 =Integer.parseInt(str33);
										}
								
									}	
							
								}
						
								if (t1<=t11) {
									if (t1<t11) {
										timeavailable="Available";
									}
									else{
										if (t2<=t22) 
										timeavailable="Available";
										else
										timeavailable="Not Available";
									}
								}
								if (t1>t11) {
									timeavailable="Not Available";
								}
						
						
							}
						}
					}
					Vector vAssessmentInfogroup=ob1.getAssessmentInfogroup(student_id);
					if(checkbox!=null) {
						if(vAssessmentInfogroup.size()!=0) {
							Vector vAssessmentAvailablity=ob1.getAssessmentGroupAvailablity(student_id,checkbox);
							if(vAssessmentAvailablity.size()!=0) {
								String AssessmentAvailablity[] =(String[])vAssessmentAvailablity.elementAt(0);
								avail_time=AssessmentAvailablity[0];
								avail_date=AssessmentAvailablity[1];
								avail=AssessmentAvailablity[2];
								valid_date=AssessmentAvailablity[3];
								valid=AssessmentAvailablity[4];
						//String stdate=strDate1+" "+"00:00:00";
								String avail_date11 = avail_date.substring(0,avail_date.indexOf(" "));
								int t1 =0;
								int t2 =0;
								int t11 =0;
								int t22 =0;
					
								if((avail.equals("Available")) && (avail_date11.equals(strDate1)))
								{
									if (!avail_time.equals("")) {
										String str1 = avail_time.substring(0,avail_time.indexOf(':'));
										String str2 = avail_time.substring(avail_time.indexOf(':')+1);
										String str3 = str2.substring(0,str2.indexOf(':'));
	        						
										String str11 = strTime.substring(0,strTime.indexOf(':'));
										String str22 = strTime.substring(strTime.indexOf(':')+1);
										String str33 = str22.substring(0,str22.indexOf(':'));
	        						
										t1 =Integer.parseInt(str1);
										t2 =Integer.parseInt(str3);
										int timeformat1 =Integer.parseInt(str11);
										if ((ampm.equals("1")) && (timeformat1<=12)) {
										t11 =Integer.parseInt(str11)+12;
										t22 =Integer.parseInt(str33);
										}
										else {
										t11 =Integer.parseInt(str11);
										t22 =Integer.parseInt(str33);
										}
									}	
							
								}
						
								if (t1<=t11) {
									if (t1<t11) {
										timeavailable="Available";
									}
									else{
										if (t2<=t22) 
										timeavailable="Available";
										else
										timeavailable="Not Available";
									}
								}
								if (t1>t11) {
									timeavailable="Not Available";
								}
						
						
							}
						}
					}
					String MaxtestTeken="";
					String nooftimesappeared="0";
					String astatus="";
					String save_state="";
					if(checkbox!=null) {
						MaxtestTeken=ob1.getMaxtestTaken(checkbox);
						nooftimesappeared=ob1.getMaxNoAppearedInTest(student_id,checkbox);
						Vector vv=ob1.isPreviousTestSubmitted(student_id,checkbox);
						if(vv.size()!=0) {
							astatus=(String)vv.elementAt(0);
							save_state=(String)vv.elementAt(1);
						}
					}
// 		log.debug("MaxtestTeken==1=="+MaxtestTeken);
// 		log.debug("save_state==1=="+save_state);
					if(save_state==null) 
						save_state="";
					else
						save_state="no";
// 		log.debug("save_state==2=="+save_state);
					String ms="You have already appeared for this test. You cannot appear for it again. [ Number of times appeared :- "+MaxtestTeken+" ]";
					String a1[]=new String[11];
		//a1[0]=checkModelstatus;
					a1[1]=timeavailable;
					a1[2]=avail;
					a1[3]=valid;
					a1[4]=MaxtestTeken;
					a1[5]=nooftimesappeared;
					a1[6]=ms;
		
					return a1;
			}

/*=======================================Nayna end==========================================*/
	
	
	
	
	public String CreateMenu(String s) {
		String html = "";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
           
html=s;
return html;
}
	
	
public String setUserIDinvalid() {
			String user = "";
			WebContext wctx1 = WebContextFactory.get();
			javax.servlet.http.HttpSession mysession = wctx1.getSession();
			String user_id = (String) mysession.getAttribute("user_id");
			DataBaseLayer.deleteUserLoginTime(user_id);
			String session_id = mysession.getId();
			
			
			Calendar calendarnew = new GregorianCalendar();
			Date trialTime = new Date();
			calendarnew.setTime(trialTime);
			String months[]={"January","Feburary","March", "April", "May","June",
				"July","August","September","October","November","December"};

				String strTime = calendarnew.get(Calendar.HOUR_OF_DAY)+":"+ calendarnew.get(Calendar.MINUTE)+":"+ calendarnew.get(Calendar.SECOND);
	//String strDate = months[calendarnew.get(Calendar.MONTH)]+" "+ calendarnew.get(Calendar.DATE)+", "+ calendarnew.get(Calendar.YEAR); 
	
				String strDate = calendarnew.get(Calendar.YEAR)+"-"+(calendarnew.get(Calendar.MONTH)+1)+"-"+calendarnew.get(Calendar.DATE);
	
				String printedDate = months[calendarnew.get(Calendar.MONTH)]+" "+calendarnew.get(Calendar.DAY_OF_MONTH)+","+calendarnew.get(Calendar.YEAR);
	//System.out.println("=====================================pdate========"+printedDate);
				/*****************************************DATE AND TIME******************************************/    
			DataBaseLayer.updateLogout(user_id,session_id,strDate,strTime);
			mysession.invalidate();
		      
//			ScriptSession session = WebContextFactory.get().getScriptSession();
//			ScriptBuffer script = new ScriptBuffer("window.location = \"./interfaceenginev2.PortalServlet?IID=LoginPage\";");
//			session.addScript(script);
		
              return "";		
		
	}	
	/******************************** Appended By Kunal 11-12-2008 ********************************/
	public String lunchCourseAll(String browser, String browserVersion, String course_name,String identifier,String strTopic_Title) {
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		mysession.setAttribute("unit_id",course_name);
		mysession.setAttribute("browser",browser);
		mysession.setAttribute("browserVersion",browserVersion);
		mysession.setAttribute("bookmarkidentifier",identifier);
		mysession.setAttribute("bookmarkTopicTitle",strTopic_Title);		
		String userId=(String)mysession.getAttribute("user_id");
		
		Calendar calendarnew = new GregorianCalendar(); 
		Date trialTime = new Date();
		calendarnew.setTime(trialTime);							
		String strTime = calendarnew.get(Calendar.HOUR_OF_DAY)+":"+ calendarnew.get(Calendar.MINUTE)+":"+ calendarnew.get(Calendar.SECOND); 
		String strDate = calendarnew.get(Calendar.YEAR)+"-"+(calendarnew.get(Calendar.MONTH)+1)+"-"+calendarnew.get(Calendar.DATE);							
		String session_id = mysession.getId(); 
		//System.out.println(" ======userId=====tttt=== " + userId);
		//System.out.println(" ======course_name====ttttt==== " + course_name);
		DataBaseLayer.insert_into_studynamicinfo(userId, session_id,strDate+" "+strTime,course_name);
		Hashtable hashIdentifier = new Hashtable();
		mysession.setAttribute("SESSION_COURSE_ITEM", hashIdentifier); 
		
		return "";
			}
	/******************************** End Kunal 11-12-2008 ***************************************/
/******************************** Partha on 19.12.08 ********************/
	public String setSessionSceId(String sce_id,String browser)throws ServletException, IOException{
	
	 
		String html = "";
		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		mysession.setAttribute("sce_id",sce_id);
		mysession.setAttribute("browser",browser);					
		
		return ""  ;
											
			}

/********************************End Partha on 19.12.08 ********************/
		
		
		/********************Surajit 13.01.09*******************/
	public String getLoginUserId() {
	String user = "";
	WebContext wctx1 = WebContextFactory.get();
	javax.servlet.http.HttpSession mysession = wctx1.getSession();
	String user_id = (String) mysession.getAttribute("user_id");
	System.out.println("user_id = "+user_id);
	return user_id;
		
		}	
		public String getLoginUserName() {
			String user = "";
			String student_id= getLoginUserId();
			user = DataBaseLayer.getStudentName(student_id);
			System.out.println("student_id = "+student_id+" user ="+user);
			return user;
		
		}
	public String getTrainingInfo(){
		
			String 	rs2 = "";
			String table = "<table width=\"700px\" border=\"1px solid\" bgcolor=\"#66CCFF\"style=\"color:#0000FF;border-color:#000000\"><tr><td>Event</td><td>START DATE</td><td>END DATE</td><td>Status</td>";
			String student_id= getLoginUserId();
			Vector strbody1=DataBaseLayer.getTrainingInfo(student_id);
			System.out.println("################## the size of the Vector:"+strbody1.size());                                     	
			for(int a=0;a<strbody1.size(); a=a+6){
				String stdate=parseDate((String)strbody1.elementAt(a+1));
				String eddate=parseDate((String)strbody1.elementAt(a+2));
				String atten=""+(String)strbody1.elementAt(a+3);
				String comp=""+(String)strbody1.elementAt(a+4);
				String sta=""+(String)strbody1.elementAt(a+5);
				if(atten.equals("Y"))
				{
					if(comp.equals("Y"))
						rs2="Completed";
					else
						rs2="Ongoing";
                              		
				}
				else
				{
					if(comp.equals("Y"))
						rs2="Did not Attend";
					else
					{
						if(sta.equals("notstarted")) 
							rs2="Not yet started";
						else 
							rs2="Ongoing but Did not Attend";
					}
				 
				}
			
				table+="<tr><td>"+(String)strbody1.elementAt(a)+"</td><td>"+stdate+"</td><td>"+eddate+"</td><td>"+rs2+"</td></tr>";
			}
			
			
			table+="</tr></table>";
			System.out.println("table ="+table);
			return table;		
		}
	
	private String parseDate(String s) 
		{                                   
			String s1 = s;			    
			if((s!=null)&&(s.length()>0))
			{
				String s2 = s1.substring(8, 10);
				String s3 = s1.substring(5, 7);
				String s4 = s1.substring(0, 4);
				String strdate =s2+"-"+s3+"-"+s4;					      	
				return strdate;
			}
			else
			{
				return "";
			}
		}
		/**********************************End Surajit 13.01.09************************/
			
		//////////////////////////Nayna Start////////////////////////////////////////////////	
			
public String CheckTimeCompleted() 
		{
			String user = "";
			String html="";
			WebContext wctx1 = WebContextFactory.get();
			javax.servlet.http.HttpSession mysession = wctx1.getSession();
			String student_id = (String) mysession.getAttribute("user_id");
			if(!DataBaseLayer.isGroupExists(student_id))
			{	
				if (!DataBaseLayer.isTimeCompleted(student_id))
				{
					html="Your Time Completed For Today";
					
				}
	
			}
			else
			{
				if (!DataBaseLayer.isTimeCompletedgroup(student_id))
				{
					html="Your Time Completed For Today";

				}
	
			}
		
			return html;
		}			
		public String updatePassword(String password) {
			String html = "";
			WebContext wctx1 = WebContextFactory.get();
			javax.servlet.http.HttpSession mysession = wctx1.getSession();
			String user_id = (String) mysession.getAttribute("user_id");
			DataBaseLayer.updateOldPassword(user_id,password);
			return html;
		}	
		
		
		public String getFirstLogin() {
			String html = "";
			WebContext wctx1 = WebContextFactory.get();
			javax.servlet.http.HttpSession mysession = wctx1.getSession();
			String user_id = (String) mysession.getAttribute("user_id");
			String login= DataBaseLayer.getFirstLogin(user_id);
			
			if(login.equals("same"))
			{
				html="first";
				return html;
			}
			else
			{
				return html;
			}
			
			
		}
		
		public String updatePassword(String OldPassworddata,String NewPassworddata,String RetypePassworddata)
		{
	        
			String val="";
			WebContext wctx1 = WebContextFactory.get();
			javax.servlet.http.HttpSession mysession = wctx1.getSession();
		
			String user_id = (String) mysession.getAttribute("user_id");				 		
			System.out.println("----------insertTempCourse(String entry_id)--NewPassworddata,RetypePassworddata-------------------------"+NewPassworddata+" , "+RetypePassworddata);
			if(NewPassworddata.equals(RetypePassworddata))	
			{		
				val=DataBaseLayer.changePassword(OldPassworddata,NewPassworddata,RetypePassworddata,user_id);
				
			}
			return val;
                      		
		}
	
		public String returnUserId()
		{
	        
			WebContext wctx1 = WebContextFactory.get();
			javax.servlet.http.HttpSession mysession = wctx1.getSession();
			String user_id = (String) mysession.getAttribute("user_id");				 		
			return  user_id;   		
		}	
		
		public String getRowDynaInfo()
		{
	        
			WebContext wctx1 = WebContextFactory.get();
			javax.servlet.http.HttpSession mysession = wctx1.getSession();
			String user_id = (String) mysession.getAttribute("user_id");				 		
			DataBaseLayer.getRowDynaInfo();
			return "";		
		}	
		public void setSessionRoleId()throws ServletException, IOException{
		
			WebContext wctx1 = WebContextFactory.get();
			javax.servlet.http.HttpSession mysession = wctx1.getSession();
			String user_id = (String) mysession.getAttribute("user_id");
			String course_id = (String) mysession.getAttribute("course_id");
			System.out.println("===========course_id==========="+course_id);
			String UserRole = DataBaseLayer.getUserRole(user_id);
			System.out.println("===========UserRole==========="+UserRole);
			mysession.removeAttribute("role_id");
			if(UserRole.equals("TEACHER")){
				if(DataBaseLayer.isInstructor(user_id,course_id)){
					System.out.println("===========DataBaseLayer.isInstructor(user_id,course_id) if==========="+DataBaseLayer.isInstructor(user_id,course_id));
					mysession.setAttribute("role_id","INSTRUCTOR");
					String role = (String) mysession.getAttribute("role_id");
					System.out.println("==============role after set if======"+role);

				}
				else{
					System.out.println("===========DataBaseLayer.isInstructor(user_id,course_id) else==========="+DataBaseLayer.isInstructor(user_id,course_id));
					mysession.setAttribute("role_id","TEACHER");
					String role = (String) mysession.getAttribute("role_id");
					System.out.println("==============role after set else======"+role);

				}
				
			}
			else if(UserRole.equals("STUDENT")){
				mysession.setAttribute("role_id","STUDENT");	
			}
			else if(UserRole.equals("CONTENTEDITOR")){
				if(DataBaseLayer.isInstructor(user_id,course_id)){
					mysession.setAttribute("role_id","INSTRUCTOR");
				}
				else{
					mysession.setAttribute("role_id","CONTENTEDITOR");
				}
			}
		}
		
		public void setSessionRoleId(String course_id)throws ServletException, IOException{
		
			WebContext wctx1 = WebContextFactory.get();
			javax.servlet.http.HttpSession mysession = wctx1.getSession();
			String user_id = (String) mysession.getAttribute("user_id");
			
			System.out.println("===========course_id==========="+course_id);
			String UserRole = DataBaseLayer.getUserRole(user_id);
			System.out.println("===========UserRole==========="+UserRole);
			mysession.removeAttribute("role_id");
			if(UserRole.equals("TEACHER")){
				if(DataBaseLayer.isInstructor(user_id,course_id)){
					System.out.println("===========DataBaseLayer.isInstructor(user_id,course_id) if==========="+DataBaseLayer.isInstructor(user_id,course_id));
					mysession.setAttribute("role_id","INSTRUCTOR");
					String role = (String) mysession.getAttribute("role_id");
					System.out.println("==============role after set if======"+role);

				}
				else{
					System.out.println("===========DataBaseLayer.isInstructor(user_id,course_id) else==========="+DataBaseLayer.isInstructor(user_id,course_id));
					mysession.setAttribute("role_id","TEACHER");
					String role = (String) mysession.getAttribute("role_id");
					System.out.println("==============role after set else======"+role);

				}
				
			}
			else if(UserRole.equals("STUDENT")){
				mysession.setAttribute("role_id","STUDENT");	
			}
			else if(UserRole.equals("CONTENTEDITOR")){
				if(DataBaseLayer.isInstructor(user_id,course_id)){
					mysession.setAttribute("role_id","INSTRUCTOR");
				}
				else{
					mysession.setAttribute("role_id","CONTENTEDITOR");
				}
			}
		}
		
		
		public void InvalidateRoleId()throws ServletException, IOException{
		
			WebContext wctx1 = WebContextFactory.get();
			javax.servlet.http.HttpSession mysession = wctx1.getSession();
			mysession.removeAttribute("role_id");
			
		}
		
		
		/********************** Parth on 290609 *************************/
				
public void setSessionNoticeId(String notice_id)
    {
        String html = "";
        System.out.println((new StringBuilder()).append("NOTICE ID------------->").append(notice_id).toString());
        WebContext wctx1 = WebContextFactory.get();
	javax.servlet.http.HttpSession mysession = wctx1.getSession();
        String user_id = (String)mysession.getAttribute("user_id");
        mysession.setAttribute("notice_id", notice_id);
    }				
				
				
		/********************* end of Partha 0n 290609**********************/
				
				
	/********************************* Partha on 06.02.10 ******************************/
				
	public String getUserIdFromSession()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		return user_id;
	}
				
	
	public String SelfRegisterUnit(String unit_id)
	{
		String html="";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		
		if((DataBaseLayer.checkConfirmation(unit_id)).equals("T"))
		{
        	
			html=DataBaseLayer.sentForConfirmation(user_id, unit_id);
		}
		else
		{
			if(!DataBaseLayer.isManifestExists(unit_id)) 
			{
				html="Manifest is not uploaded";
			}
			else
			{
				html=DataBaseLayer.insertCourseUserAssociationSelf(user_id, unit_id);
			}
		}
		
		
		return html;
	}
	
	
	
	public String SelfUnRegisterUnit(String unit_id)
	{
		String html="";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		
		html=DataBaseLayer.deleteCourseUserAssociation(user_id, unit_id);
		
		
		
		return html;
	}
	
	
	public String sendMail(String user_id)
	{
		String html="";
		String sendmailfrom="";
		ResourceBundle rb = ResourceBundle.getBundle("portal1",Locale.getDefault());
		String key1= "mailsendfrom"; 
		sendmailfrom = rb.getString(key1);
		System.out.println("sendmailfrom=====SendMailServlet==============="+sendmailfrom);
		
		String server_title = learnityasmtserver.assessmentadmin.dbconnection.DataBaseLayer.getMailServerTitle();
		System.out.println("===========server_title====1=SendMailServlet==="+server_title);
		if(server_title==null)
			server_title = "";
		System.out.println("===========server_title====2==SendMailServlet=="+server_title);
		if(server_title.equals(""))
		{
			html="No Active Mail Server Found";
		}
		else
		{
			String password = DataBaseLayer.getPassword(user_id);
			String s1 = "User Id and Password";
			if(password.equals(""))
			{
				html="User Id does not exists";
			}
			else
			{
				String s = DataBaseLayer.getMailId(user_id);
				String s12="";
				String s2 = "User Id : "+user_id+"\nPassword : "+password+"\n\n\n\n It is an auto generated mail. Do not reply";
				System.out.println("=============s2========111111==="+s2);
				try
				{	
					
					Properties properties = System.getProperties();
           // properties.put("mail.smtp.host", "www.aunwesha.com");
					properties.put("mail.smtp.host", server_title);
					Session session = Session.getInstance(properties, null);
					MimeMessage mimemessage = new MimeMessage(session);
					s12 = sendmailfrom;
					mimemessage.setFrom(new InternetAddress(s12));
					InternetAddress ainternetaddress[] = {
						new InternetAddress(s)
					};
					mimemessage.setRecipients(javax.mail.Message.RecipientType.TO, ainternetaddress);
					mimemessage.setSubject(s1);
					mimemessage.setContent(s2, "text/plain");
					Transport.send(mimemessage);
					html="Mail successfully sent to you email id.";
		
				}// try			
				catch(Exception e)
				{
					System.out.println("Exception e=="+e);
					e.printStackTrace();
				}
				catch(Throwable throwable)
				{
					html=throwable.getMessage();
					System.out.println("Exception "+throwable.getMessage());
					throwable.printStackTrace();
		
				}
			}
		}
		
		
		
		
		
		return html;
	}
				
	/********************************* end of Partha on 06.02.10 ************************/
	/************************** Partha on 16.03.10 ********************/
					
			
	public String returnDepartments()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String col_id="";
		Vector vCollectionDetails = DataBaseLayer.getDepartments(user_id);
		String uppertable="";
		if(vCollectionDetails.size()!=0)
		{
			int col=0;
			for(col = 0; col<vCollectionDetails.size();col=col+1)
			{
				System.out.println("returnCollections vCollectionDetails = "+vCollectionDetails);
				String strCol[] = (String[])vCollectionDetails.elementAt(col);
				System.out.println("---------------------------->strCol[0] = "+strCol[0]);
				System.out.println("---------------------------->strCol[1] = "+strCol[1]);
				uppertable+="<div id=\"coldiv\" style=\"position:absolute;left:"+(20+col*130)+"px\"><a href=\"javascript:department_onclick('"+strCol[0]+"');\">"+strCol[1]+"</a></div>";	
				System.out.println("---------------UPPERTABLE----returnCollections----------> "+uppertable);
				
			}
// 			uppertable+="<div id=\"coldiv\" style=\"position:absolute;left:"+(20+col*130)+"px\"><a href=\"javascript:collection_onclick('default');\">Default</a></div>";		
	
		}else uppertable="";
		System.out.println("---------uppertable----------"+uppertable);
		return uppertable;
	}		
			
			
	public void setSessionDepartmentId(String dept_id)throws ServletException, IOException{
	 
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		mysession.setAttribute("department_id",dept_id);					
	}
			
	
	public String returnDepartmentsForStudent()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String col_id="";
		Vector vCollectionDetails = DataBaseLayer.getDepartments(user_id);
		String uppertable="";
		if(vCollectionDetails.size()!=0)
		{
			int col=0;
			for(col = 0; col<vCollectionDetails.size();col=col+1)
			{
				System.out.println("returnCollections vCollectionDetails = "+vCollectionDetails);
				String strCol[] = (String[])vCollectionDetails.elementAt(col);
				System.out.println("---------------------------->strCol[0] = "+strCol[0]);
				System.out.println("---------------------------->strCol[1] = "+strCol[1]);
				uppertable+="<div id=\"coldiv\" style=\"position:absolute;left:"+(20+col*130)+"px\"><a href=\"javascript:departmentstudent_onclick('"+strCol[0]+"');\">"+strCol[1]+"</a></div>";	
				System.out.println("---------------UPPERTABLE----returnCollections----------> "+uppertable);
				
			}
// 			uppertable+="<div id=\"coldiv\" style=\"position:absolute;left:"+(20+col*130)+"px\"><a href=\"javascript:collection_onclick('default');\">Default</a></div>";		
	
		}else uppertable="";
		System.out.println("---------uppertable----------"+uppertable);
		return uppertable;
	}
			
	public String returnCollectionsFromDept(String dept_id)
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String col_id="";
		Vector vCollectionDetails = DataBaseLayer.getCollectionsFromDept(user_id,dept_id);
		String uppertable="";
		if(vCollectionDetails.size()!=0)
		{
			int col=0;
			for(col = 0; col<vCollectionDetails.size();col=col+1)
			{
				System.out.println("returnCollections vCollectionDetails = "+vCollectionDetails);
				String strCol[] = (String[])vCollectionDetails.elementAt(col);
				System.out.println("---------------------------->strCol[0] = "+strCol[0]);
				System.out.println("---------------------------->strCol[1] = "+strCol[1]);
				uppertable+="<div id=\"coldiv\" style=\"position:absolute;left:"+(20+col*130)+"px\"><a href=\"javascript:collection_onclick('"+strCol[0]+"');\">"+strCol[1]+"</a></div>";	
				System.out.println("---------------UPPERTABLE----returnCollections----------> "+uppertable);
				
			}
			uppertable+="<div id=\"coldiv\" style=\"position:absolute;left:"+(20+col*130)+"px\"><a href=\"javascript:collection_onclick('default');\">Default</a></div>";		
	
		}else uppertable="";
		System.out.println("---------uppertable----------"+uppertable);
		return uppertable;
	}
	
	public String returnCollectionsFromDeptForStudent(String dept_id)
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String col_id="";
		Vector vCollectionDetails = DataBaseLayer.getCollectionsFromDept(user_id,dept_id);
		String uppertable="";
		if(vCollectionDetails.size()!=0)
		{
			int col=0;
			for(col = 0; col<vCollectionDetails.size();col=col+1)
			{
				System.out.println("returnCollections vCollectionDetails = "+vCollectionDetails);
				String strCol[] = (String[])vCollectionDetails.elementAt(col);
				System.out.println("---------------------------->strCol[0] = "+strCol[0]);
				System.out.println("---------------------------->strCol[1] = "+strCol[1]);
				uppertable+="<div id=\"coldiv\" style=\"position:absolute;left:"+(20+col*130)+"px\"><a href=\"javascript:course_collection_onclick('"+strCol[0]+"');\">"+strCol[1]+"</a></div>";	
				System.out.println("---------------UPPERTABLE----returnCollections----------> "+uppertable);
				
			}
			uppertable+="<div id=\"coldiv\" style=\"position:absolute;left:"+(20+col*130)+"px\"><a href=\"javascript:course_collection_onclick('default');\">Default</a></div>";		
	
		}else uppertable="";
		System.out.println("---------uppertable----------"+uppertable);
		return uppertable;
	}
	
	/************************* end of Partha on 16.03.10 **************/
	/**********************Surajit For Interface Fragment**************/
	public Vector getUnits()
	{

		getUserId();
		String html="";
		String innertable="";
		String course_id="";
		String total_access_time = null;
		String total_time_accessed = null;
		String course_status = null;
		String overall_status = null;
		String course_name  ; //for course name
		String access_allowed_till; //for date till which the access is permitted
		String first_access_date_time; //for first access date and time
		String last_access_date_time;  //for last access date and time
		String topic_id = null;
		String dateregister=null;
		String totaltime=null;
		String ss="";
		Boolean tt=false;
		Vector Details = new Vector();
		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
                //mysession.setAttribute("student_id",user_id);
		Vector vCourseInfo =DataBaseLayer.getLoginInfo(user_id);
               
		for(int i =0 ; i <vCourseInfo.size() ; i = i +10)
		{
			Boolean b=true;
			course_id = (String)vCourseInfo.elementAt(i);
			//System.out.println("course_id==LoObjectGrid===="+course_id);
			course_name = (String)vCourseInfo.elementAt(i+1);
			//System.out.println("course_name==LoObjectGrid===="+course_name);
			access_allowed_till = (String)vCourseInfo.elementAt(i+2);
			dateregister = (String)vCourseInfo.elementAt(i+3);
			last_access_date_time = (String)vCourseInfo.elementAt(i+4);
			topic_id = (String)vCourseInfo.elementAt(i+5);
			//System.out.println("topic_id==LoObjectGrid===="+topic_id);
			total_access_time =(String)vCourseInfo.elementAt(i+6);
			if(total_access_time == null )
			{
				ss="Not Applicable";
			}
			else{
				ss=total_access_time+"hrs";
			}

			totaltime=DataBaseLayer.getRequisiteunitaccess(course_id);
			course_status = (String)vCourseInfo.elementAt(i+8);
			overall_status = (String)vCourseInfo.elementAt(i+9);
			//oracle.xml.parser.v2.XMLDocument manifest =DataBaseLayer.parse(course_id,"csformat");
			String unitaccess = DataBaseLayer.unitaccess(user_id,course_id);
			String  noodunitaccess=DataBaseLayer.nounitaccess(user_id,course_id);
			String strTopic_Title =null;
			strTopic_Title=DataBaseLayer.getScoTitle(topic_id,course_id);
			System.out.println("strTopic_Title==LoObjectGrid===="+strTopic_Title);
			try {
// 				PortalMgmt.servletfile.PortalImsManifest ims =  new PortalMgmt.servletfile.PortalImsManifest(manifest);
// 				if(topic_id != null)
// 				{
// 					strTopic_Title = ims.getItemTitle(topic_id);
// 					try{
// 						byte[] topictitle=strTopic_Title.getBytes();
// 						strTopic_Title=new String(topictitle,"utf-8");
// 					}catch(Exception e){System.out.println("Error:"+e);}
// 				}

				if (overall_status.equalsIgnoreCase("Active"))
				{
					if(DataBaseLayer.isPreUnitExists(course_id))
					{
						//System.out.println("b==LoObjectGrid==1=="+b);
						Vector preunit = DataBaseLayer.getPreUnitInfo(course_id);
                                                      
						//System.out.println("preunit==LoObjectGrid===="+preunit);
						for(int j=0; j<preunit.size(); j=j+2) {
	    		
							String preId = (String) preunit.elementAt(j);
							//System.out.println("preId==LoObjectGrid===="+preId);
							String UnId = (String) preunit.elementAt(j+1);
							//System.out.println("UnId==LoObjectGrid===="+UnId);
							if(!DataBaseLayer.isPreUnitCompleted(preId,user_id))
							{
								//System.out.println("============isPreUnitCompleted===============");
								b=false;
							}
						}
					}
					System.out.println("b==LoObjectGrid===="+b);

					if(b){
						if (topic_id!=null )
						{
							if (!strTopic_Title.equals("0" ))
							{
								System.out.println("strTopic_Title========vvvvvvv=========="+strTopic_Title);
								String color=(tt) ? "#EEE" : "#ffffff";
								/*innertable=innertable+
										"<tr style=\"background-color:"+color+";\"><td><a href=\"javascript:lunchCourse('"+course_id+"');\">"+course_name+"</a></td>"+
										"<td ><a href=\"javascript:resume2('"+course_id+"','"+topic_id+"','"+strTopic_Title+"');\">"+strTopic_Title+"</a></td>"+
										"<td>"+last_access_date_time+"</td>"+
								"<td >"+access_allowed_till+"</td>";*/
								innertable="{unit_id:\""+course_id+"\",unit_name:\"<a href='javascript:lunchCourse("+course_id+")'>"+course_name+"</a>\",bookmark:\"<a href='javascript:resume2(\\\""+course_id+"\\\",\\\""+topic_id+"\\\",\\\""+strTopic_Title+"\\\")'>"+strTopic_Title+"</a>\",last_accessed:\""+last_access_date_time+"\",access_allowed_till:\""+access_allowed_till+"\"}";
								System.out.println("innertable============"+innertable);
								Details.addElement(innertable);
// 								"<td>"+ss+"</td>"+
// 								"<td>"+unitaccess+"</td>"+
// 								"<td>"+noodunitaccess+"</td></tr>";
								tt=!tt;
							}
							else{
								String color=(tt) ? "#EEE" : "#ffffff";
								/*innertable=innertable+
										"<tr style=\"background-color:"+color+";\"><td><a href=\"javascript:lunchCourse('"+course_id+"');\">"+course_name+"</a></td>"+
										//"<td ><a href=\"javascript:resume2('"+course_id+"','"+topic_id+"','"+strTopic_Title+"');\">"+strTopic_Title+"</a></td>"+
										"<td></td>"+
										"<td>"+last_access_date_time+"</td>"+
										"<td >"+access_allowed_till+"</td>";*/
										innertable="{unit_id:\""+course_id+"\",unit_name:\"<a href='javascript:lunchCourse("+course_id+")'>"+course_name+"</a>\",bookmark:\"\",last_accessed:\""+last_access_date_time+"\",access_allowed_till:\""+access_allowed_till+"\"}";
										Details.addElement(innertable);
// 										"<td>"+ss+"</td>"+
// 										"<td>"+unitaccess+"</td>"+
// 										"<td>"+noodunitaccess+"</td></tr>";
								tt=!tt;
							}
						}
						else
						{
							System.out.println("topic_id========else=========="+topic_id);
							String color=(tt) ? "#EEE" : "#ffffff";
							/*innertable=innertable+
									"<tr style=\"background-color:"+color+";\"><td><a href=\"javascript:lunchCourse('"+course_id+"');\">"+course_name+"</a></td>"+
									"<td >Not Accessed Yet</td>"+
									"<td >"+last_access_date_time+"</td>"+
									"<td>"+access_allowed_till+"</td>";*/
							innertable="{unit_id:\""+course_id+"\",unit_name:\"<a href='javascript:lunchCourse("+course_id+")'>"+course_name+"</a>\",bookmark:\"Not Accessed Yet\",last_accessed:\""+last_access_date_time+"\",access_allowed_till:\""+access_allowed_till+"\"}";
							Details.addElement(innertable);
// 								"<td >"+ss+"</td>"+
// 								"<td >"+unitaccess+"</td>"+
// 								"<td >"+noodunitaccess+"</td></tr>";
							tt=!tt;
						}
					}
					else{
						if (topic_id!=null )
						{
							String color=(tt) ? "#EEE" : "#ffffff";
							/*innertable=innertable+
// 									"<tr style=\"background-color:"+color+";\"><td><a href=\"javascript:lunchCourse('"+course_id+"');\">"+course_name+"</a></td>"+
// 									"<td ><a href=\"javascript:resume2('"+course_id+"','"+topic_id+"','"+strTopic_Title+"');\">"+strTopic_Title+"</a></td>"+
									"<tr style=\"background-color:"+color+";\"><td>"+course_name+"</td>"+
									"<td >"+strTopic_Title+"</td>"+
									"<td>"+last_access_date_time+"</td>"+
									"<td >"+access_allowed_till+"</td>";*/
							innertable="{unit_id:\""+course_id+"\",unit_name:\""+course_name+"\",bookmark:\""+strTopic_Title+"\",last_accessed:\""+last_access_date_time+"\",access_allowed_till:\""+access_allowed_till+"\"}";
							Details.addElement(innertable);
// 									"<td>"+ss+"</td>"+
// 									"<td>"+unitaccess+"</td>"+
// 									"<td>"+noodunitaccess+"</td></tr>";
							tt=!tt;
						}
						else
						{
							String color=(tt) ? "#EEE" : "#ffffff";
							/*innertable=innertable+
// 									"<tr style=\"background-color:"+color+";\"><td><a href=\"javascript:lunchCourse('"+course_id+"');\">"+course_name+"</a></td>"+
									"<tr style=\"background-color:"+color+";\"><td>"+course_name+"</td>"+
									"<td >Not Accessed Yet</td>"+
									"<td >"+last_access_date_time+"</td>"+
									"<td>"+access_allowed_till+"</td>";*/
							innertable="{unit_id:\""+course_id+"\",unit_name:\"<a href='javascript:lunchCourse("+course_id+")'>"+course_name+"</a>\",bookmark:\"\",last_accessed:\""+last_access_date_time+"\",access_allowed_till:\""+access_allowed_till+"\"}";
							Details.addElement(innertable);
// 									"<td >"+ss+"</td>"+
// 									"<td >"+unitaccess+"</td>"+
// 									"<td >"+noodunitaccess+"</td></tr>";
							tt=!tt;
						}
					
					}
				}
			}
			catch(Exception e){System.out.println("Error:"+e);}
		}
/*		html="<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"660\" id=\"myScrollTable\">"+
				"<thead>"+
				"<tr><th>Unit Name</th><th>Book Mark</th><th>Last Accessed</th><th>Access Allowed Till</th></tr></thead>"+"<tbody>"+innertable+"</tbody></table>";*/
				 //  html=html+"<tbody class=\"scrollContent\"><tr><td>1</td></tr><tr><td>2</td></tr><tr><td>3</td></tr></tbody></table>";
		return Details;
	}

	public String getUserRole(){
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		return DataBaseLayer.getUserRole(user_id);
	}
	
	/**********************End Surajit For Interface Fragment**************/
			/********************** Start Soma for Forum Self registration ***************/
	
			public String SelfRegisterForum(String forum_id)
	{
		String html="";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		
		if((DataBaseLayer.checkforumConfirmation(forum_id)).equals("T"))
		{
        	
			html=DataBaseLayer.sentForForumConfirmation(user_id, forum_id);
		}
		else
		{
			html=DataBaseLayer.insertForumUserAssociationSelf(user_id, forum_id);
			
		}
		
		
		return html;
	}
	
	public String SelfUnRegisterForum(String forum_id)
	{
		String html="";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		
		html=DataBaseLayer.deleteForumUserAssociation(user_id, forum_id);
		
		
		
		return html;
	}
	
	
	/************************************SHIBAJI ADD********************************/
	public String[] newWelcome()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String[] loginwelcome=new String[2];
		loginwelcome = DataBaseLayer.getnewLoginWelcome(user_id);
		return loginwelcome;
	}		
	public Vector getCoursesforCorporateUser(){

		Vector Details=new Vector();
		String strInnerData = "";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		Vector vCourseList = DataBaseLayer.getCourseList(user_id);
		if(vCourseList.size()==0)
		{
			strInnerData="{course_id:\"No Courses Registered\",course_name:\"No Courses Registered\",date_registered:\"\",course_study:\"\"}";
			System.out.println("strInnerData============"+strInnerData);
			Details.addElement(strInnerData);
		}
		else
		{

			for(int k=0; k<vCourseList.size();k++)
			{
				String strCourse[] =(String[])vCourseList.elementAt(k);
				strInnerData="{course_id:\""+strCourse[0]+"\",course_name:\"<a href='javascript:launchCoursemanagement("+strCourse[0]+")'>"+strCourse[1]+"</a>\",date_registered:\""+strCourse[2]+"\",course_study:\""+strCourse[3]+"\"}";
				System.out.println("strInnerData============"+strInnerData);
				Details.addElement(strInnerData);
			}
		}
		return Details;
	}			
	
	
	
	//////////////////////  SJIBAJI ADD FOR PROGRAME/////////////////////
	
	
	/*public Vector getPrograme(){

		Vector Details=new Vector();
		String strInnerData = "";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		try
		{
			Vector vProgramList = new Vector();
			vProgramList=DataBaseLayer.ProgramList(user_id);
		System.out.println("vProgramList.size........................."+vProgramList.size());

		
		if(vProgramList.size()==0)
		{
			strInnerData="{program_name:\"No Programe Registered\",type:\"No Courses Registered\",date1:\"\",date2:\"\",attendence:\"\",feedback:\"\"}";
			System.out.println("strInnerData============"+strInnerData);
			Details.addElement(strInnerData);
		}
		else
		{
			System.out.println("vProgramList.size........................."+vProgramList.size());

			for(int k=0; k<vProgramList.size();k=k+5)
			{
				String strCourse1 =(String)vProgramList.elementAt(k);
				String strCourse2 =(String)vProgramList.elementAt(k+1);
				String strCourse3 =(String)vProgramList.elementAt(k+2);
				String strCourse4 =(String)vProgramList.elementAt(k+3);
				String strCourse5 =(String)vProgramList.elementAt(k+4);
				if(strCourse3.equals("EL"))
				{
					//strCourse1="<font color=\"red\">"+strCourse2+"</font>";
				}
				//strInnerData="{course_id:\""+strCourse[0]+"\",course_name:\"<a href='javascript:launchCoursemanagement("+strCourse[0]+")'>"+strCourse[1]+"</a>\",date_registered:\""+strCourse[2]+"\",course_study:\""+strCourse[3]+"\"}";
				strInnerData="{program_name:\""+strCourse2+"\",type:\""+strCourse3+"\",date1:\""+strCourse4+"\",date2:\""+strCourse5+"\",attendence:\"\",feedback:\"<a href=javascript:feedback(\'"+strCourse1+"\')>Feedback</a>\"}";

				System.out.println("strInnerData============"+strInnerData);
				Details.addElement(strInnerData);
			}
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return Details;
}		*/	
	
	
	public String getSurveyByPrograme(){

		Vector Details=new Vector();
	
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String program_id = (String) mysession.getAttribute("programid");
		String html="<table width=\"100%\" border=\"0\" cellpadding=\"1\" cellspacing=\"1\" bgcolor=\"#ffffff\">";
		try
		{
			
			Details=DataBaseLayer.getSurveyByPrograme(program_id);
			for(int i=0;i<Details.size();i=i+3)
			{
				String survey_item_id=(String)Details.elementAt(i);
				String survey_type=(String)Details.elementAt(i+1);
				String survey_text=(String)Details.elementAt(i+2);
				
				if(survey_type.equals("ET"))
				{
					html=html+"<tr><td colspan=\"2\" width=\"400\"><font size=\"2px\" family=\"verdana\">"+survey_text+"</font></td></tr><tr><td colspan=\"2\"><textarea  rows=\"2\" cols=\"20\" id=\"ET"+survey_item_id+"\" name=\"ET"+survey_item_id+"\"></textarea></td></tr>";
				}
				else
				{
					html=html+"<tr><td colspan=\"2\" width=\"400\"><font size=\"2px\" family=\"verdana\">"+survey_text+"</font></td></tr><tr><td><input type=\"radio\" name=\"MC"+survey_item_id+"\" value=\"Yes\" />Yes</td><td><input type=\"radio\" name=\"MC"+survey_item_id+"\" value=\"No\" />  No</td></tr>";
				}
				
			}
			html=html+"<tr><td colspan=\"2\"><input type=\"button\" name=\"feedback\" value=\"Submit\" onclick=\"feedback_onclick();\"/></td></tr></table>";
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return html;
	}			
	public String getProgrameName(){

		String name="";
	
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String program_id = (String) mysession.getAttribute("programid");
		try
		{
			name=DataBaseLayer.getProgrameName(program_id);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return name;
	}			
	
	public Vector getSurveyByProgrameElement(){

		Vector Details=new Vector();
	
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String program_id = (String) mysession.getAttribute("programid");
		
		try
		{
			Details=DataBaseLayer.getSurveyByPrograme(program_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Details;
	}	
			
	
	/************************* Partha on 16.01.2012 ******************************/
	
			public Vector getProgramUnits()
	{
	
		getUserId();
		String html="";
		String innertable="";
		String course_id="";
		String total_access_time = null;
		String total_time_accessed = null;
		String course_status = null;
		String overall_status = null;
		String course_name  ; //for course name
		String access_allowed_till; //for date till which the access is permitted
		String first_access_date_time; //for first access date and time
		String last_access_date_time;  //for last access date and time
		String topic_id = null;
		String dateregister=null;
		String totaltime=null;
		String ss="";
		Boolean tt=false;
		Vector Details = new Vector();
	
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String training_program_id = (String) mysession.getAttribute("training_program_id");
		String user_id = (String) mysession.getAttribute("user_id");
	//mysession.setAttribute("student_id",user_id);
		Vector vCourseInfo =DataBaseLayer.getTrainingProgramInfo(training_program_id,user_id);
	
		for(int i =0 ; i <vCourseInfo.size() ; i = i +4)
		{
			Boolean b=true;
			course_id = (String)vCourseInfo.elementAt(i);
							 //System.out.println("course_id==LoObjectGrid===="+course_id);
			course_name = (String)vCourseInfo.elementAt(i+1);
							 //System.out.println("course_name==LoObjectGrid===="+course_name);
			last_access_date_time = (String)vCourseInfo.elementAt(i+2);
			topic_id = (String)vCourseInfo.elementAt(i+3);
							 
							 
			String strTopic_Title =null;
			strTopic_Title=DataBaseLayer.getScoTitle(topic_id,course_id);
			System.out.println("strTopic_Title==LoObjectGrid===="+strTopic_Title);
			if(strTopic_Title.equals("0"))
				strTopic_Title="";
			try 
			{
				if(topic_id==null)
					topic_id="";
				System.out.println("topic_id============"+topic_id);
				if (topic_id.equals("") || topic_id.equals("0"))
				{
					String color=(tt) ? "#EEE" : "#ffffff";
					innertable="{unit_id:\""+course_id+"\",unit_name:\"<a href='javascript:lunchCourse("+course_id+")'>"+course_name+"</a>\",bookmark:\"\",last_accessed:\""+last_access_date_time+"\"}";
					Details.addElement(innertable);
					tt=!tt;
				}
				else
				{
					String color=(tt) ? "#EEE" : "#ffffff";
					innertable="{unit_id:\""+course_id+"\",unit_name:\"<a href='javascript:lunchCourse("+course_id+")'>"+course_name+"</a>\",bookmark:\""+strTopic_Title+"\",last_accessed:\""+last_access_date_time+"\"}";
					Details.addElement(innertable);
					tt=!tt;
				}
									  
												  
			}
												  
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("===========details==========="+Details);								  
		return Details;
	}
	
	public Vector getPrograme(){

		Vector Details=new Vector();
		String strInnerData = "";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		try
		{
			Vector vProgramList = new Vector();
			vProgramList=DataBaseLayer.ProgramList(user_id);
			System.out.println("vProgramList.size........................."+vProgramList.size());

		
			if(vProgramList.size()==0)
			{
				strInnerData="{program_name:\"No Programe Registered\",type:\"No Courses Registered\",date1:\"\",date2:\"\",attendence:\"\",feedback:\"\"}";
				System.out.println("strInnerData============"+strInnerData);
				Details.addElement(strInnerData);
			}
			else
			{
				System.out.println("vProgramList.size........................."+vProgramList.size());

				for(int k=0; k<vProgramList.size();k=k+5)
				{
					String strCourse1 =(String)vProgramList.elementAt(k);
					String strCourse2 =(String)vProgramList.elementAt(k+1);
					String strCourse3 =(String)vProgramList.elementAt(k+2);
					String strCourse4 =(String)vProgramList.elementAt(k+3);
					String strCourse5 =(String)vProgramList.elementAt(k+4);
					if(strCourse3.equals("EL"))
					{
					//strCourse1="<font color=\"red\">"+strCourse2+"</font>";
						strInnerData="{program_name:\"<a href=javascript:Unit_Onclick(\'"+strCourse1+"\')>"+strCourse2+"</a>\",type:\""+strCourse3+"\",date1:\""+strCourse4+"\",date2:\""+strCourse5+"\",attendence:\"\",feedback:\"<a href=javascript:feedback(\'"+strCourse1+"\')>Feedback</a>\"}";
					}
				//strInnerData="{course_id:\""+strCourse[0]+"\",course_name:\"<a href='javascript:launchCoursemanagement("+strCourse[0]+")'>"+strCourse[1]+"</a>\",date_registered:\""+strCourse[2]+"\",course_study:\""+strCourse[3]+"\"}";
					else
					{
						strInnerData="{program_name:\""+strCourse2+"\",type:\""+strCourse3+"\",date1:\""+strCourse4+"\",date2:\""+strCourse5+"\",attendence:\"\",feedback:\"<a href=javascript:feedback(\'"+strCourse1+"\')>Feedback</a>\"}";
					}

					System.out.println("strInnerData============"+strInnerData);
					Details.addElement(strInnerData);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return Details;
	}
	
	/************************* End of Partha on 16.01.2012 ************************/
	
	
}


