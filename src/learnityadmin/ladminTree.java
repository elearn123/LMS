package learnityadmin;

import java.io.*;
import java.util.*;
import org.directwebremoting.ScriptBuffer ;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.ScriptSession ;
import org.grlea.log.*;
import java.util.Locale;
import org.w3c.dom.*;
import com.oreilly.servlet.MultipartRequest;
import java.util.ResourceBundle;
import java.util.Properties;
import java.text.*;
import java.net.*;
import java.io.File;
import jxl.*;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import interfaceenginev2.FileUploaderPojo;
import interfaceenginev2.FileDownloaderPojo;
import org.directwebremoting.io.FileTransfer;
import org.xml.sax.InputSource;

/////////////////////////////////////////////////////////////////////
import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.SAXException;
import java.util.zip.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.Serializer;
import org.apache.xml.serialize.SerializerFactory;
import org.apache.xml.serialize.XMLSerializer;
import learnityInit.*;





/**
 * Aunwesha Knowledge Technologies Pvt ltd.
 * @author Kunal Chattopadhyay
 */
public class ladminTree  {


static Document course_main_doc = null;
static final int BUFFER = 2048;
private ZipOutputStream zip = null;
private FileOutputStream fileWriter = null;


	public String treeConstruct()
	{


		String outString = ""+
				"<ul>"+
				"<li data=\"title:'Notice Administration',isFolder: true\">Notice Administration"+
				"<ul>"+
				"<li data=\"title:'Notice Board',url:'./interfaceenginev2.PortalServlet?IID=LearnityNoticeBoard' ,isFolder: false\">Notice Board"+
				"<li data=\"title:'Learnity Notice Board View',url:'./interfaceenginev2.PortalServlet?IID=LearnityNoticeBoardview' ,isFolder: false\">Learnity Notice Board View"+
				"<li data=\"title:'Notice Management',url:'./interfaceenginev2.PortalServlet?IID=LearnityNoticeBoardAdmin' ,isFolder: false\">Notice Management"+
				"</ul>"+
				"</ul>";
// 				"<ul>"+
// 				"<li data=\"isFolder: true\">Notice Administration"+
// 				"<ul>"+
// 				"<li data=\"url:'./interfaceenginev2.PortalServlet?IID=LearnityNoticeBoard'\">Notice Board"+
// 				"<li data=\"url:'./interfaceenginev2.PortalServlet?IID=LearnityNoticeBoardview\">Learnity Notice Board View"+
// 				"<li data=\"url:'./interfaceenginev2.PortalServlet?IID=LearnityNoticeBoardAdmin\">Notice Administration"+
// 				"</ul>"+
// 				"</ul>";
		return outString;
	
	}
	public String isSessionAlive() {
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		user_id = (user_id == null) ? "" : user_id;
		if(user_id.equals("")) {
			mysession.invalidate();
			ScriptSession session = WebContextFactory.get().getScriptSession();
			ScriptBuffer script = new ScriptBuffer("window.location=\"./interfaceenginev2.PortalServlet?IID=LoginPage\";");
		}
		return "";
	}
	public String sessionInvalidate() {
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
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
		
			DataBaseLayer.updateLogout(user_id,session_id,strDate,strTime);
		
		mysession.invalidate();
		ScriptSession session = WebContextFactory.get().getScriptSession();
		ScriptBuffer script = new ScriptBuffer("window.location=\"./interfaceenginev2.PortalServlet?IID=LoginPage\";");
		return "";
	}

	
	
	public String BatchUserCreation(FileTransfer file) {
		
		String html = "";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		user_id = (user_id == null) ? "" : user_id;
					
		String dirName="";
		String strLocation = Host.getAdminPath();
		dirName=strLocation;
		File ufile=null;
		
		
		if(dirName == null)
			html="Please supply uploadDir parameter";

		ufile = new File(dirName);
		if(!ufile.exists())
			ufile.mkdir(); 
					
		FileOutputStream out; 
		PrintStream p;
		String thisLine="";
		
		FileUploaderPojo fu = new FileUploaderPojo(file);
		String MimeType = fu.getMimeType();
		System.out.println("MimeType======:"+MimeType);
		InputStream is = fu.getInputStream();
		String filename = fu.getFilename();
		dirName = dirName+File.separator+filename;
		
		
		
		try {
			
			System.out.println("dirName==="+dirName);
			out = new FileOutputStream(dirName);
			
			int length = 0;
			byte b[]= new byte[1024];
			
				while ((is != null) && ((length = is.read(b)) != -1)) 
				{
					
					out.write(b,0,length);
				}
			
						
			p = new PrintStream( out );
			
			p.close();
							
			
			Workbook w = Workbook.getWorkbook(new File(dirName));
	
		
		//for (int k = 0; k < w.getNumberOfSheets(); k++)
			for (int k = 0; k < 1; k++)
			{
				Sheet sheet = w.getSheet(k);
			//String stringa0 = "";
				double numberb2 = 0;
			//Date datec2 = null;
				int cols = sheet.getColumns();
				int rows = sheet.getRows();
				System.out.println("cols==="+cols);
				System.out.println("rows===="+rows);
				Cell a1=null;
				Cell a2=null;
				Cell a3=null;
				Cell a4=null;
				Cell a5=null;
				Cell a6=null;
				Cell a7=null;
				Cell a8=null;
				Cell a9=null;
				Cell a10=null;
				Cell a11=null;
				Cell a12=null;
				Cell a13=null;
			
	//	String str_name = "";
				String studentId = "";
				String password = "";
// 				String rank = "";
				String studentFname = "";
				String studentMname = "";
				String studentSname = "";
				String accountStatus = "";
				String age="";
				String experience="";
				String education="";
				String gender="";
				String email="";
				String department="";
				String selfregistration="";
				
				for(int i=1;i<rows;i++)
				{
			 
					a1 = sheet.getCell(0,i);				
					a2=  sheet.getCell(1,i);				
					a3=  sheet.getCell(2,i);				
					a4=  sheet.getCell(3,i);
					a5=  sheet.getCell(4,i);
					a6=  sheet.getCell(5,i);
					a7=  sheet.getCell(6,i);
					a8=  sheet.getCell(7,i);
					a9=  sheet.getCell(8,i);
					a10= sheet.getCell(9,i);
					a11= sheet.getCell(10,i);
					a12= sheet.getCell(11,i);
					a13= sheet.getCell(12,i);
					
					System.out.println("a1==="+a1);
					System.out.println("a2===="+a2);
					System.out.println("a3==="+a3);
					System.out.println("a4===="+a4);		
					System.out.println("a5==="+a5);
					System.out.println("a6===="+a6);
					System.out.println("a7===="+a7);
					System.out.println("a8===="+a8);
					System.out.println("a9==="+a9);
					System.out.println("a10===="+a10);		
					System.out.println("a11==="+a11);
					System.out.println("a12===="+a12);
					System.out.println("a13===="+a13);
					
					
					studentId = a1.getContents();
					password = a2.getContents();
					studentFname = a3.getContents();
					studentMname = a4.getContents();
					studentSname = a5.getContents();
					accountStatus = a6.getContents();
					age= a7.getContents();
					experience=a8.getContents();					
					education=a9.getContents();
					gender=a10.getContents();
					email=a11.getContents();
					department=a12.getContents();
					selfregistration=a13.getContents();
					
					System.out.println("studentId==============="+studentId);
					System.out.println("password================"+password);
					System.out.println("studentFname============"+studentFname);
					System.out.println("studentMname=============="+studentMname);		
					System.out.println("studentSname=============="+studentSname);
					System.out.println("accountStatus================"+accountStatus);
					System.out.println("age================"+age);
					System.out.println("experience================"+experience);
					System.out.println("education============"+education);
					System.out.println("gender============"+gender);
					System.out.println("email=============="+email);		
					System.out.println("department=============="+department);
					System.out.println("selfregistration================"+selfregistration);

					System.out.println("===============user_id=========="+user_id) ;
					
					if(!DataBaseLayer.CheckExcelStudentExists(studentId))
					{
						System.out.println("===============1==========");
						if(DataBaseLayer.CheckValidUserRow(studentId,password,studentFname,studentSname,accountStatus))
						{
							System.out.println("===============2==========");
							DataBaseLayer.insertStudentDetailsfrmExcelSheet(studentId,password,studentFname,studentMname,studentSname,accountStatus,user_id,age,experience,education,gender,email,department,selfregistration);
						}
						else
						{
							System.out.println("===============3==========");
							DataBaseLayer.insertTempStudentDetailsfrmExcelSheet(studentId,password,studentFname,studentMname,studentSname,accountStatus,age,experience,education,gender,email,department,selfregistration);
					
						}
					}
					else
					{
					
						if(DataBaseLayer.CheckValidUserRow(studentId,password,studentFname,studentSname,accountStatus))
						{
							System.out.println("===============4==========");
							DataBaseLayer.updateStudentDetailsfrmExcelSheet(studentId,password,studentFname,studentMname,studentSname,accountStatus,age,experience,education,gender,email,department,selfregistration);
						}
						else
						{
							System.out.println("===============5==========");
							DataBaseLayer.insertTempStudentDetailsfrmExcelSheet(studentId,password,studentFname,studentMname,studentSname,accountStatus,age,experience,education,gender,email,department,selfregistration);
					
						}
					
					}
				
				}
		
				w.close();
			}
		}
			catch (IOException ex) {
				
			}			
			catch(Exception exp)
			{
				System.err.println("=========error======="+exp.toString());
			}			
		
		
		
		
		
		return html;
	}
	
	/*=====================================Nayna======================================*/
	
	public FileTransfer downloadfromrepository(String unit_id)
	{

		Vector v1=DataBaseLayer.getManifest(unit_id);
		InputStream in= (InputStream)v1.elementAt(0);
		/*
		String strLocation = com.aunwesha.param.CoreAdminHost.getServerDocumentPath();
		try{

		File f1 = new File(strLocation+File.separatorChar+unit_id+File.separatorChar+"imsmanifest.xml");
		in = new FileInputStream(f1);
	
		}
		catch(IOException ioe){
		
		}*/

 		FileDownloaderPojo fd = new FileDownloaderPojo(in, "", "imsmanifest.xml");
		return fd.returnFileFormat();
	
	}
	public String uploadtorepository(String course_id,FileTransfer file) {
		String html = "";	
		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		System.out.println("user_id===adminuser==="+user_id);		
		String dirName="";
		String strLocation = Host.getAdminPath();
		dirName=strLocation;
		File ufile=null;
		System.out.println("dir name is==:"+dirName);
		
		if(dirName == null)
			html="Please supply uploadDir parameter";

		ufile = new File(dirName);
		if(!ufile.exists())
			ufile.mkdir(); 
					
		FileOutputStream out; 
		PrintStream p;
				
		FileUploaderPojo fu = new FileUploaderPojo(file);
		String MimeType = fu.getMimeType();
		System.out.println("MimeType======:"+MimeType);
		InputStream is = fu.getInputStream();
		String filename = fu.getFilename();
		dirName = dirName+File.separator+filename;
		String content = "";
		String thisLine = "";		
		try {
						
			File f;
			f=new File(dirName);
			if(!f.exists()){
				f.createNewFile();
			}
			BufferedReader myInput = new BufferedReader
					(new InputStreamReader(is));
			while ((thisLine = myInput.readLine()) != null) 
			{  
// 				System.out.println("thisLine==="+thisLine);
				if(content.equals(""))
				{
					content = thisLine;
				}
				else {
					content = content +"\n"+ thisLine;
				}
			}
			out = new FileOutputStream(dirName);

			System.out.println("bbbbbbbbbbbbbbbb");
			p = new PrintStream( out );
		
			p.println(content);

			p.close();
							
							
						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		String strSize="";
		File file_size=new File(dirName);
		Long size2=file_size.length();
		
		strSize=size2.toString();
		System.out.println("strSize======:"+strSize);
		
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		String months[]={"January","Feburary","March", "April", "May","June",
			"July","August","September","October","November","December"};

		String strTime = calendar.get(Calendar.HOUR)+":"+ calendar.get(Calendar.MINUTE);
		String strDate = months[calendar.get(Calendar.MONTH)]+" "+ calendar.get(Calendar.DATE)+", "+ calendar.get(Calendar.YEAR);

		boolean status = DataBaseLayer.insertCourse("csformat",course_id,content,"imsmanifest.xml",strSize,user_id,strDate);
		if (status==true)
			html="Successfully uploaded";
		else
			html="Upload failed";			
		return html;
	}
	
	public String createManageContentObjResourse(String unit_id){
 		String s="";
		Document xmlDoc = (Document)DataBaseLayer.parse(unit_id,"csformat");
		String strContentPath = Host.getServerDocumentPath();
		
		Vector vResources =getResourceLocation(xmlDoc);
		String[][] data = new String[vResources.size()][7];
		String months[]={"January","Feburary","March", "April", "May","June",
			"July","August","September","October","November","December"};
			Calendar calendar = new GregorianCalendar();
			String strTime = new String();
			String strDate = new String();
			int k = 0;
			for(int i = 0; i < vResources.size(); ++i) {
				Vector v = (Vector)vResources.elementAt(i);
				String item = (String)v.elementAt(0);			
				data[k][0] = item;
				data[k][1] = (String)v.elementAt(1);
				String title = (String)v.elementAt(2);
				int l = title.lastIndexOf('?');
				title = (l == -1) ? title : title.substring(0, l);
				if ( (title.startsWith("http://")) || (title.startsWith("https://")) || (title.startsWith("ftp://")) )
				{
					data[k][2] = title;	  				
					data[k][3] = (String)v.elementAt(3);
					data[k][4] = (String)v.elementAt(4);  	
				}
				 else {	
					 String dirName = strContentPath+File.separatorChar+unit_id+File.separatorChar+title;
					dirName = dirName.replace('\\',File.separatorChar);
					dirName = dirName.replace('/',File.separatorChar);				
					File is_exists_f = new File(dirName);
					data[k][2] = is_exists_f.getName();
					data[k][3] = (String)v.elementAt(3);
					data[k][4] = (String)v.elementAt(4);
					if(is_exists_f.exists()) {
						calendar.setTime(new Date(is_exists_f.lastModified()));
						strTime = calendar.get(Calendar.HOUR)+":"+ calendar.get(Calendar.MINUTE);
						strDate = months[calendar.get(Calendar.MONTH)]+" "+ calendar.get(Calendar.DATE)+", "+ calendar.get(Calendar.YEAR);
	
						data[k][5] = strDate+" "+strTime;
						data[k][6] = ""+is_exists_f.length();
					}
				}			
				k = k+1;
				
		}
		DataBaseLayer.deletetemp_mcontentobj_resource();
		for(int i1 = 0; i1 < vResources.size(); ++i1) {
			DataBaseLayer.inserttemp_mcontentobj_resource(data[i1][0],data[i1][1],data[i1][2],data[i1][3],data[i1][4],data[i1][5],data[i1][6]);
			
		}
 		return s;
	}
	public Vector getResourceLocation(Document manifest) {
		Vector vResource=new Vector();
		String strLocation = null;
		NodeList nl = manifest.getElementsByTagName("resources");
		NodeList nListResource = null;
		for(int i = 0; i < nl.getLength();i++) {
			nListResource = ((Element)nl.item(i)).getElementsByTagName("resource");
			for (int j=0; j< nListResource.getLength();j++) {
				NamedNodeMap nnm1 = nListResource.item(j).getAttributes();
				for (int k =0; k < nnm1.getLength();k++) {
					if (nnm1.item(k).getNodeName().equalsIgnoreCase("identifier")) {
						String type = new String();
						String adltype = new String();
						for (int r = 0; r < nnm1.getLength(); r++) {
							if (nnm1.item(r).getNodeName().equalsIgnoreCase("type")) {
								type = nnm1.item(r).getNodeValue();
							}
							if (nnm1.item(r).getNodeName().equalsIgnoreCase("adlcp:scormtype")) {
								adltype = nnm1.item(r).getNodeValue();
							}
							if (nnm1.item(r).getNodeName().equalsIgnoreCase("href")) {
								strLocation = nnm1.item(r).getNodeValue();
								if (strLocation != null) {
									Vector vResources = new Vector();
									vResources.addElement(nnm1.item(k).getNodeValue());
									vResources.addElement(new String("href"));
									vResources.addElement(strLocation);
									vResources.addElement(type);
									vResources.addElement(adltype);
									vResource.addElement(vResources);
								}
							}
						}
						NodeList resourcesChildren = nListResource.item(j).getChildNodes();
						for ( int n = 0; n < resourcesChildren.getLength(); n++ ) {
							if(resourcesChildren.item(n).getNodeName().equalsIgnoreCase("file")) {
								NamedNodeMap nnm2 = resourcesChildren.item(n).getAttributes();
								for (int r = 0; r < nnm2.getLength(); r++) {
									if (nnm2.item(r).getNodeName().equalsIgnoreCase("href")) {
										if (strLocation != null) {
										if (!strLocation.equalsIgnoreCase(nnm2.item(r).getNodeValue())) {
										Vector vResources = new Vector();		     									
										vResources.addElement(nnm1.item(k).getNodeValue());
										vResources.addElement(new String("file"));
										vResources.addElement(nnm2.item(r).getNodeValue());
										vResources.addElement(new String());
										vResources.addElement(new String());
										vResource.addElement(vResources);
										}
										}
										else {
										Vector vResources = new Vector();
										vResources.addElement(nnm1.item(k).getNodeValue());
										vResources.addElement(new String("file"));
										vResources.addElement(nnm2.item(r).getNodeValue());
										vResources.addElement(new String());
										vResources.addElement(new String());
										vResource.addElement(vResources);
										}
									}
								}	     						
							}
							else if(resourcesChildren.item(n).getNodeName().equalsIgnoreCase("dependency")) {
								NamedNodeMap nnm2 = resourcesChildren.item(n).getAttributes();
								for (int r = 0; r < nnm2.getLength(); r++) {
									if (nnm2.item(r).getNodeName().equalsIgnoreCase("identifierref")) {
										Vector vResources = new Vector();
										vResources.addElement(nnm1.item(k).getNodeValue());
										vResources.addElement(new String("dependency"));
										vResources.addElement(nnm2.item(r).getNodeValue());
										vResources.addElement(new String());
										vResources.addElement(new String());
										vResource.addElement(vResources);
									}
								}
							}
						}
					}
				}
			}
		}
		return vResource;
	}
	public Vector getOrganization(Document manifest) {
		Vector vOrganization = new Vector();
		NodeList nl = manifest.getElementsByTagName("organization");
		for(int i=0; i<nl.getLength();i++) {  
			Vector vOrganizationProp = new Vector();
			vOrganizationProp.addElement(((Element)nl.item(i)).getAttributeNode("identifier").getValue());
			vOrganizationProp.addElement(((nl.item(i)).getChildNodes()).item(0).getFirstChild().getNodeValue());	
			vOrganization.addElement(vOrganizationProp);
		}
		return vOrganization;
	}
	public String createDropDownManageUnitObject(String unit_id)
	{
		String html = "";
		
		Document xmlDoc = (Document)DataBaseLayer.parse(unit_id,"csformat");
		Vector vOrganization = getOrganization(xmlDoc);
		if(vOrganization.size()==0)
		{
			html="<option value='0'>[Choose One]</option>";
		}
		else
		{
			html="<option value='0'>[Choose One]</option>";
			for(int i=0; i<vOrganization.size(); i++) {
				Vector v = (Vector) vOrganization.elementAt(i);
				String OrgId = (String) v.elementAt(0);
				String OrgName = (String) v.elementAt(1);
				html +="<option value='"+OrgId+"'>"+OrgName+"</option>";
			}
		}
		
		System.out.println("html ==="+html);
		
		return html;
	}
	
	public String createManageContentObjOrganization(String unit_id){
		String s="";
		String strContentPath = Host.getServerDocumentPath();
		
		Document xmlDoc = (Document)DataBaseLayer.parse(unit_id,"csformat");
		
		Vector vOrganization =getOrganization(xmlDoc);
		String org = (String)((Vector)vOrganization.elementAt(0)).elementAt(0);
		Vector vResources = getTreeRootInOutputStream(org,xmlDoc);
		int size = 0;
		int size1 = 0;
		for(int i = 0; i < vResources.size(); ++i) {
			Vector v = (Vector)vResources.elementAt(i);
			size += ((Vector) v.elementAt(2)).size();
			size1=size1+1;
			
					
		}
		
		
		
		String[][] data  = new String[size][5];
		String[][] data1  = new String[size1][5];
		String months[]={"January","Feburary","March", "April", "May","June",
			"July","August","September","October","November","December"};
			Calendar calendar = new GregorianCalendar();
			String strTime = new String();
			String strDate = new String();
			int k = 0;
			int p = 0;
			for(int i = 0; i < vResources.size(); ++i) {
				Vector v = (Vector)vResources.elementAt(i);
				String item = (String)v.elementAt(0);
// 				System.out.println("===========item========"+item);
				String title = (String)v.elementAt(1);
// 				System.out.println("===========title========"+title);
				size = ((Vector) v.elementAt(2)).size();
// 				System.out.println("===========size===2====="+size);
				
// 				System.out.println("===========size1===2====="+size1);	
				
				/*************** Partha **************/
				
				
						data1[p][0] = item;
// 					System.out.println("data1[p][0]====="+data1[p][0]);
				data1[p][1] = title;
// 					System.out.println("data1[p][1]====="+data1[p][1]);
				data1[p][2] = (String)((Vector) v.elementAt(2)).elementAt(0);
					//System.out.println("data[k][2]===1=="+data[k][2]);
				int l1 = data1[p][2].lastIndexOf('?');
				data1[p][2] = (l1 == -1) ? data1[p][2] : data1[p][2].substring(0, l1);
					//System.out.println("data[k][2]==2==="+data[k][2]);
				String dirName = strContentPath+File.separatorChar+unit_id+File.separatorChar+data1[p][2];
					//System.out.println("=========dirName==============="+dirName);
				dirName = dirName.replace('\\',File.separatorChar);
				dirName = dirName.replace('/',File.separatorChar);
				File is_exists_f1 = new File(dirName);
				if(is_exists_f1.exists()) {
						//System.out.println("=========is_exists_f.exists================");
					calendar.setTime(new Date(is_exists_f1.lastModified()));
					strTime = calendar.get(Calendar.HOUR)+":"+ calendar.get(Calendar.MINUTE);
					strDate = months[calendar.get(Calendar.MONTH)]+" "+ calendar.get(Calendar.DATE)+", "+ calendar.get(Calendar.YEAR);
		
					data1[p][3] = strDate+" "+strTime;
					data1[p][4] = ""+is_exists_f1.length();
				}
				p = p+1;
				
				/**************** end of partha **************/
				
				
						for(int j=0; j<size; ++j) {
					data[k][0] = item;
// 					System.out.println("data[k][0]====="+data[k][0]);
					data[k][1] = title;
// 					System.out.println("data[k][1]====="+data[k][1]);
					data[k][2] = (String)((Vector) v.elementAt(2)).elementAt(j);
					//System.out.println("data[k][2]===1=="+data[k][2]);
					int l = data[k][2].lastIndexOf('?');
					data[k][2] = (l == -1) ? data[k][2] : data[k][2].substring(0, l);
					//System.out.println("data[k][2]==2==="+data[k][2]);
					//String dirName = strContentPath+File.separatorChar+unit_id+File.separatorChar+data[k][2];
					//System.out.println("=========dirName==============="+dirName);
					dirName = dirName.replace('\\',File.separatorChar);
					dirName = dirName.replace('/',File.separatorChar);
					File is_exists_f = new File(dirName);
					if(is_exists_f.exists()) {
						//System.out.println("=========is_exists_f.exists================");
						calendar.setTime(new Date(is_exists_f.lastModified()));
						strTime = calendar.get(Calendar.HOUR)+":"+ calendar.get(Calendar.MINUTE);
						strDate = months[calendar.get(Calendar.MONTH)]+" "+ calendar.get(Calendar.DATE)+", "+ calendar.get(Calendar.YEAR);
		
						data[k][3] = strDate+" "+strTime;
						data[k][4] = ""+is_exists_f.length();
					}
					k = k+1;
						}
			}
			DataBaseLayer.deletetemp_mcontentobj_organization();
			
			for(int i1 = 0; i1 <size1; ++i1) {
				
				DataBaseLayer.inserttemp_mcontentobj_organization(data1[i1][0],data1[i1][1],data1[i1][2],data1[i1][3],data1[i1][4],i1);
			
			}
			return s;
	}
	public Vector getTreeRootInOutputStream(String org,Document manifest) {

		Vector vTitle = new Vector();
		Element n = null;
		NodeList nl = manifest.getElementsByTagName("organization");     	
		for (int i=0; i<nl.getLength();i++) {    		
			Element el = (Element)nl.item(i);
			if(el.getAttributeNode("identifier").getValue().equals(org)) {
				n = el;
				break;
			}
		}
    	
		if (n != null) {	
			Element el = (Element)n;
			showTreeInOutputStream(el, vTitle,manifest);
		} 
		return vTitle;
	}
	public void showTreeInOutputStream(Element el1, Vector vTitle,Document xmlDoc) {

		NodeList nl = el1.getChildNodes();
		NamedNodeMap nnm = null;
		String tagname = null;
		Node nodeItem  = null;
		Node resourceNode = null;
		String strIdentifier = null;
		String resourseRef = null;
		String strItemTitle = null;
		String strResourceLocation = null;
		for(int i = 0; i < nl.getLength();i++) {
			nodeItem = nl.item(i);
			tagname = ((Element)nodeItem).getTagName();
			if (tagname.equalsIgnoreCase("item")) {
				nnm = nodeItem.getAttributes();
				for (int j=0;j<nnm.getLength();j++) {
					resourceNode = nnm.item(j);
					if (resourceNode.getNodeName().equalsIgnoreCase("identifier")) {
						strIdentifier = resourceNode.getNodeValue();
					}
					if (resourceNode.getNodeName().equalsIgnoreCase("identifierref")) {
						resourseRef = resourceNode.getNodeValue();     					
					}
				}
				strItemTitle = nodeItem.getFirstChild().getFirstChild().getNodeValue();
				Vector vTmp = new Vector();
				Vector vResource = new Vector();
				vTmp.addElement(strIdentifier);
				vTmp.addElement(strItemTitle);
				getResourceLocation(resourseRef, vResource,xmlDoc);
				vTmp.addElement(vResource);
				vTitle.addElement(vTmp);
     			
				Element el2 = (Element)nl.item(i);
				strResourceLocation = null;
				strItemTitle = null;
				showTreeInOutputStream(el2, vTitle,xmlDoc);
			}
		}
	}
	public void getResourceLocation(String strResRef, Vector vResource,Document manifest) {

		String strLocation = null;
		NodeList nl = manifest.getElementsByTagName("resources");
		NodeList nListResource = null;
		for(int i = 0; i < nl.getLength();i++) {
			nListResource = ((Element)nl.item(i)).getElementsByTagName("resource");
			for (int j=0; j< nListResource.getLength();j++) {
				NamedNodeMap nnm1 = nListResource.item(j).getAttributes();
				for (int k =0; k < nnm1.getLength();k++) {
					if (nnm1.item(k).getNodeName().equalsIgnoreCase("identifier") && nnm1.item(k).getNodeValue().equalsIgnoreCase(strResRef)) {
						for (int r = 0; r < nnm1.getLength(); r++) {
							if (nnm1.item(r).getNodeName().equalsIgnoreCase("href")) {
								strLocation = nnm1.item(r).getNodeValue();
								if (strLocation != null) {
									vResource.addElement(strLocation);
								}
							}
						}
						NodeList resourcesChildren = nListResource.item(j).getChildNodes();
						for ( int n = 0; n < resourcesChildren.getLength(); n++ ) {
							if(resourcesChildren.item(n).getNodeName().equalsIgnoreCase("file")) {
								NamedNodeMap nnm2 = resourcesChildren.item(n).getAttributes();
								for (int r = 0; r < nnm2.getLength(); r++) {
									if (nnm2.item(r).getNodeName().equalsIgnoreCase("href")) {
										if (strLocation != null) {
										if (!strLocation.equalsIgnoreCase(nnm2.item(r).getNodeValue())) {
										vResource.addElement(nnm2.item(r).getNodeValue());
										}
										}
										else {
										vResource.addElement(nnm2.item(r).getNodeValue());
										}
									}
								}	     						
							}
							else if(resourcesChildren.item(n).getNodeName().equalsIgnoreCase("dependency")) {
								NamedNodeMap nnm2 = resourcesChildren.item(n).getAttributes();
								for (int r = 0; r < nnm2.getLength(); r++) {
									if (nnm2.item(r).getNodeName().equalsIgnoreCase("identifierref")) {
										getResourceLocation(nnm2.item(r).getNodeValue(), vResource,manifest);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	public void setUnitId(String unit_id)
	{
		if(unit_id==null){unit_id="";}
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();

		mysession.setAttribute("mcobj_unitid",unit_id);
	}
	public void uploadtorepository1(FileTransfer file) {
// 		String html = "";	
		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String unit_id = (String) mysession.getAttribute("mcobj_unitid");
		if(unit_id==null)
			unit_id="";
		//System.out.println("mcobj_unitid===adminuser==="+unit_id);
		String user_id = (String) mysession.getAttribute("user_id");
		//System.out.println("user_id===adminuser==="+user_id);	
		
		String dirName="";
		String strLocation = Host.getServerDocumentPath();
		dirName=strLocation+File.separator+unit_id;
		File ufile=null;
		//System.out.println("dir name is==:"+dirName);
		
// 		if(dirName == null)
// 			html="Please supply uploadDir parameter";

		ufile = new File(dirName);
		if(!ufile.exists())
			ufile.mkdir(); 
					
		FileOutputStream out; 
		PrintStream p;
// 		String thisLine="";
		
		FileUploaderPojo fu = new FileUploaderPojo(file);
		String MimeType = fu.getMimeType();
		//System.out.println("MimeType======:"+MimeType);
		InputStream is = fu.getInputStream();
		String filename = fu.getFilename();
		//dirName = dirName+File.separator+unit_id+File.separator+filename;
		dirName = dirName+File.separator+filename;
		String content = "";
		
		
		try {
			
			//System.out.println("dirName==="+dirName);
			out = new FileOutputStream(dirName);
			int length = 0;
			byte b[]= new byte[1024];
			try 
			{
				while ((is != null) && ((length = is.read(b)) != -1)) 
				{
					out.write(b,0,length);
				}
			}
			catch (IOException ex) {
				
			}
			
			p = new PrintStream( out );
		
			p.println(content);

			p.close();
							
							
						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		String strSize="";
		File file_size=new File(dirName);
		Long size2=file_size.length();
		
		strSize=size2.toString();
		//System.out.println("strSize======:"+strSize);
		
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		String months[]={"January","Feburary","March", "April", "May","June",
			"July","August","September","October","November","December"};

			String strTime = calendar.get(Calendar.HOUR)+":"+ calendar.get(Calendar.MINUTE);
			String strDate = months[calendar.get(Calendar.MONTH)]+" "+ calendar.get(Calendar.DATE)+", "+ calendar.get(Calendar.YEAR);

		
		if(unit_id.equals("")) {
			uploadQualifier(dirName,filename,user_id);

		}
		else {
			executeCommmand(filename,unit_id,user_id);
		}
		
		
			
// 	return html;
	}
	public void uploadQualifier(String strDir, String strFile, String strUploadBy) {
		
		try {
			File f1 = new File(strDir+File.separatorChar+strFile);
			BufferedReader buffy = new BufferedReader(
					new FileReader (f1));
			while(buffy.ready()) {
				String str = buffy.readLine();
				DataBaseLayer.addQualifierDetails(str, strUploadBy);
			}
			buffy.close();
			f1.delete();

		}
		catch(Exception exp) {
	    	
			System.out.println("File not Found");
		}
	}
	public void executeCommmand(String filename,String strUnitId,String uploadBy) {
		DataBaseLayer.insertContent("content_management_object",filename,strUnitId,uploadBy);
		
	}
	public String deleteManageContentObjects(String strFilename) {
		String html="";	 
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String strUnitId = (String) mysession.getAttribute("mcobj_unitid");
		//System.out.println("strUnitId======:"+strUnitId);
		//System.out.println("strFilename======:"+strFilename);
		if(strUnitId==null)
			strUnitId="";
		String str1111=DataBaseLayer.checkunitRegistration(strUnitId);
		//System.out.println("str1111======:"+str1111);
		String strContentPath = Host.getServerDocumentPath();
		//System.out.println("strContentPath======:"+strContentPath);
		if(str1111!=null){
			html="You can not delete the Unit." +
					"\n You must unregister the user from Unit.**";      		
		}
		else{
			executedeleteCommmand(strUnitId,strFilename,strContentPath);
		}
		return html;
	}
	public void executedeleteCommmand(String strUnitId, String strFilename, String path) {
		//boolean flag = false;
    	
		File f1 = new File(path+File.separatorChar+strUnitId+File.separatorChar+strFilename);
		if(f1.exists()) {
			f1.delete();
			//flag = true;
		}
		DataBaseLayer.deleteContent("content_management_object",strFilename,strUnitId);
    	
		//return flag;
	}
	public FileTransfer downloadfromrepository1(String filename)
	{
		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String strUnitId = (String) mysession.getAttribute("mcobj_unitid");
		//System.out.println("strUnitId======"+strUnitId);
		String strContentPath = Host.getServerDocumentPath();
		//System.out.println("strContentPath======"+strContentPath);
		String dirName=strContentPath+File.separator+strUnitId+File.separator+filename;
		//System.out.println("dirName======"+dirName);
		File f1 = new File(dirName);
		InputStream in=new StringBufferInputStream("source");;
		try{
			if(f1.exists()) {
				//System.out.println("-------f1------------"+f1);
				in = new BufferedInputStream (new FileInputStream(f1));
			}
		}
		catch(Exception e){
		}
		//System.out.println("filename======"+filename);		
		FileDownloaderPojo fd = new FileDownloaderPojo(in, "", filename);
		return fd.returnFileFormat();
	
	}
	public void CheckUnitConsistency() {
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String strUnitId = (String) mysession.getAttribute("mcobj_unitid");
		String org = (String) mysession.getAttribute("mcobj_orgid");
		Document xmlDoc = (Document)DataBaseLayer.parse(strUnitId,"csformat");
		String strContentPath = Host.getServerDocumentPath();
		String[] cols = {"File Name", "Referred in Resource ID"}; 			
		String[][] data = null;
		Vector vHTTPError = new Vector();
		int datasize=0;
		if(xmlDoc != null) {
			Vector vResources = getTreeRootInOutputStream(org,xmlDoc);
			int size = 0;
			for(int i = 0; i < vResources.size(); ++i) {
				Vector v = (Vector)vResources.elementAt(i);
				size += ((Vector) v.elementAt(2)).size();
				
			}			
			
			Vector vFilesNotFound = new Vector();
			for(int i = 0; i < vResources.size(); ++i) {
				Vector v = (Vector)vResources.elementAt(i);
				String item = (String)v.elementAt(0);
				size = ((Vector) v.elementAt(2)).size();
				
				for(int j=0; j<size; ++j) {
					String f = (String)((Vector) v.elementAt(2)).elementAt(j);
					if ( (f.startsWith("http://")) ||
										(f.startsWith("https://")) ||
										(f.startsWith("ftp://")) ) {   
						/*
						Vector vError = new Vector();	
						URL url = new URL(f);
					    
						try {
							HttpURLConnection con = (HttpURLConnection) url.openConnection();    
							con.connect();
							int responseCode = con.getResponseCode();
							if ((responseCode>= 400) && (responseCode>= 599)) {
								vError.addElement(f);
								vError.addElement(""+responseCode);
								vError.addElement(item);
								vHTTPError.addElement(vError);
							}
							con.disconnect();
						}
						catch(UnknownHostException e) {
							vError.addElement(f);
							vError.addElement("11001");
							vError.addElement(item);
							vHTTPError.addElement(vError);
						}*/
						
						                 
										}
										else {
						// Create the altered location (with decoded url)
										int l = f.lastIndexOf('?');
										f = (l == -1) ? f : f.substring(0, l);           						           
										String dirName = strContentPath+File.separatorChar+strUnitId+File.separatorChar+f;
										f = f.replace('\\',File.separatorChar);
										f = f.replace('/',File.separatorChar);
										dirName = dirName.replace('\\',File.separatorChar);
										dirName = dirName.replace('/',File.separatorChar);
										File is_exists_f = new File(dirName);
						
										if(!is_exists_f.exists()) {
										Vector vFileNotFound = new Vector();
										vFileNotFound.addElement(is_exists_f.getName());
										vFileNotFound.addElement(item);
										vFilesNotFound.addElement(vFileNotFound);
										}					           
										}					
				}
			}
			
			if((vFilesNotFound != null) && (vFilesNotFound.size()>0)) {
				datasize=vFilesNotFound.size();
				data = new String[vFilesNotFound.size()][2];
				for(int i=0; i<vFilesNotFound.size(); ++i) {
					data[i][0] = (String)((Vector)vFilesNotFound.elementAt(i)).elementAt(0);
					data[i][1] = (String)((Vector)vFilesNotFound.elementAt(i)).elementAt(1);
				}
				
			}
		}
		DataBaseLayer.deletetemp_checkunit_consistency();
		for(int i1 = 0; i1 <datasize; ++i1) {
			DataBaseLayer.inserttemp_checkunit_consistency(data[i1][0],data[i1][1]);
			
		}
		/*
		String[] cols1 = {"URL", "HTTP Error Code", "Referred in Resource ID"}; 			
		String[][] data1 = null;
		if((vHTTPError != null) && (vHTTPError.size()>0)) {
			data1 = new String[vHTTPError.size()][3];
			for(int i=0; i<vHTTPError.size(); ++i) {
				data1[i][0] = (String)((Vector)vHTTPError.elementAt(i)).elementAt(0);
				data1[i][1] = (String)((Vector)vHTTPError.elementAt(i)).elementAt(1);
				data1[i][2] = (String)((Vector)vHTTPError.elementAt(i)).elementAt(2);
			}
			
		}*/
	}
	public void setOrganization(String orgid)
	{
		if(orgid==null){orgid="";}
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();

		mysession.setAttribute("mcobj_orgid",orgid);
	}
	public String copyfromanotherunit(String strFile,String strUnitId1) {
		String html="";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String strUnitId = (String) mysession.getAttribute("mcobj_unitid");
		String strContentPath = Host.getServerDocumentPath();
		String strAdminId = (String) mysession.getAttribute("user_id");
		System.out.println("strAdminId===adminuser==="+strAdminId);	
		if(!executeCommmand(strUnitId,strFile,strContentPath,strUnitId1,strAdminId)) {
			html = "* The system cannot find the file specified";
		}
		System.out.println("html===html==="+html);	
		return html;
	}
	public boolean executeCommmand(String strUnitId, String strFile, String path, String strUnitId1,String uploadBy) {
		boolean flag = false;
		File f1 = new File(path+File.separatorChar+strUnitId1+File.separatorChar+strFile);
		if(f1.exists()) {
			System.out.println("=========================");
			try {
				FileInputStream in = new FileInputStream(f1);
				String strOutputFile = path+File.separatorChar+strUnitId+File.separatorChar+strFile;
				FileOutputStream out = new FileOutputStream(strOutputFile, false);
				int len = 0;
				byte buffer[]= new byte[1024];
				try {
					while ((in != null) && ((len = in.read(buffer)) != -1)) {
						out.write(buffer,0,len);
					}
				}
				finally {
					if (in != null) in.close();
				}

				DataBaseLayer.insertContent("content_management_object",strFile,strUnitId,uploadBy);
				flag = true;
			}
			catch (FileNotFoundException fn) {
    			System.out.println(fn.toString());
// 				log.error(fn.toString());
			}
			catch (Exception exp) {
    			System.out.println(exp.toString());
// 				log.error(exp.toString());
			}
		}
		else {
			flag = false;
		}
		return flag;
	}
	
	public void uploadQualifier(FileTransfer file) {
		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		System.out.println("user_id===adminuser==="+user_id);		
		String dirName="";
		String strLocation = Host.getAdminPath();
		dirName=strLocation;
		File ufile=null;
		System.out.println("dir name is==:"+dirName);
		/*
		if(dirName == null)
			html="Please supply uploadDir parameter";*/

		ufile = new File(dirName);
		if(!ufile.exists())
			ufile.mkdir(); 
					
		FileOutputStream out; 
		PrintStream p;
				
		FileUploaderPojo fu = new FileUploaderPojo(file);
		String MimeType = fu.getMimeType();
		System.out.println("MimeType======:"+MimeType);
		InputStream is = fu.getInputStream();
		String filename = fu.getFilename();
		dirName = dirName+File.separator+filename;
		String content = "";
		String thisLine = "";		
		try {
						
			File f;
			f=new File(dirName);
			if(!f.exists()){
				f.createNewFile();
			}
			BufferedReader myInput = new BufferedReader
					(new InputStreamReader(is));
			while ((thisLine = myInput.readLine()) != null) 
			{  
 				System.out.println("thisLine==="+thisLine);
				DataBaseLayer.addQualifierDetails(thisLine, user_id);
				if(content.equals(""))
				{
					content = thisLine;
				}
				else {
					content = content +"\n"+ thisLine;
				}
			}
			out = new FileOutputStream(dirName);

			System.out.println("bbbbbbbbbbbbbbbb");
			p = new PrintStream( out );
		
			p.println(content);

			p.close();
							
							
						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/*================================================================================*/
	
	
	
	/*=====================================Jayanta======================================*/
	
	public void setTreeDataacatalog(String cat_id)
	{
		if(cat_id==null){cat_id="";}
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		System.out.println("==cat_id===cccc========"+cat_id);
		mysession.setAttribute("catalogid",cat_id);
	}
	
	public void setTreeDataacatalogEntry(String Entri_Name_text)
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String catalogid=(String)mysession.getAttribute("catalogid");
		if(catalogid==null){catalogid="";}
		if(Entri_Name_text==null){Entri_Name_text="";}
		System.out.println("==cat_id===setTreeDataacatalogEntry(String Entri_Name_text)========"+catalogid);
		System.out.println("==entry_id===setTreeDataacatalogEntry(String Entri_Name_text)========"+Entri_Name_text);
		mysession.setAttribute("entry_id",Entri_Name_text);
	}
	public String treeConstruct1()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String catalogid=(String)mysession.getAttribute("catalogid");
		System.out.println("==catalogid===bbbbbb========"+catalogid);
		String outString=createTree(catalogid);
		//String outString=
		System.out.println("=====1========="+outString);
		return outString;
	
	}

	public String createTree(String catalog_id)
	{
		System.out.println("==in createTree()=======1======="+catalog_id);
		Vector vAllTopics = DataBaseLayer.getEntry(catalog_id);
		System.out.println("vAllTopics.size=======1======="+vAllTopics.size());
		String str1 = "";
		String str2 = "";
		str1 = "\n<ul>";
        
		for(int j=0;j<vAllTopics.size();j++)
		{
			String s1[]=(String[])vAllTopics.elementAt(j);
			System.out.println("==in createTree()=======s1[]=,j====="+s1+" , "+j);
			
			String no=Integer.toString(j+1);
			System.out.println("==in createTree()======catalog_id======"+catalog_id);
			
			str2= treeprint(catalog_id,s1,no);
			str1 +=str2;
		}
		str1 += "\n</ul>";
		System.out.println("==str1==2=========="+str1);
		return str1;
	
	} 
	private static String treeprint(String catalog_id,String[] s1, String keyno)
	{
		String str=""; 
		Vector vChildList = DataBaseLayer.getEntry(catalog_id,s1[0]);
		System.out.println("s1[0]=======2======="+s1[0]);
		System.out.println("vChildList=======3======="+vChildList);
		if(vChildList.size()>0){
			str="\n<li data=\"key: '"+s1[5]+"',tooltip: '"+s1[0]+"', description: '"+s1[2]+"' \">"+s1[0];
			//str="\n<li data=\"key: '"+s1[5]+"',tooltip: '"+s1[0]+"', description: '"+s1[2]+"' ,isFolder: true\">"+s1[0];
			
			//str="\n<li data=\"key: '"+s1[5]+"',data=\"entryid: '"+s1[6]+"',tooltip: '"+s1[0]+"', description: '"+s1[2]+"' ,isFolder: true\">"+s1[0];
			//  avabe ke sombhob?????????????????
			
			
			str +="\n<ul>";
			for(int i=0; i<vChildList.size(); i++)
			{
				String s2[]=(String[])vChildList.elementAt(i);
				String no=Integer.toString(i+1);
				str += treeprint(catalog_id,s2,keyno,no);
			}
			str +="\n</ul>";
		}
		else
		{
			str="\n<li data=\"key: '"+s1[5]+"',tooltip: '"+s1[0]+"', description: '"+s1[2]+"'\">"+s1[0];

		}
		System.out.println("==str====3========"+str);
		return str;
	      
	}
	private static String treeprint(String catalog_id,String[] s2,String keyno,String kno)
	{
		String no1=keyno+"."+kno;
		String str=""; 
		Vector vChildList =DataBaseLayer.getEntry(catalog_id,s2[0]);

		if(vChildList.size()>0){
			str="\n<li data=\"key: '"+s2[5]+"',tooltip: '"+s2[0]+"', description: '"+s2[2]+"'\">"+s2[0];
			//str="\n<li data=\"key: '"+s2[5]+"',tooltip: '"+s2[0]+"', description: '"+s2[2]+"', isFolder: true\">"+s2[0];
			

			str +="\n<ul>";
			for(int i=0; i<vChildList.size(); i++)
			{
				String s3[]=(String[])vChildList.elementAt(i);
				String no=Integer.toString(i+1);
				str += treeprint(catalog_id,s3,no1,no);
			}
			str +="\n</ul>";
		}
		else
		{
			str="\n<li data=\"key: '"+s2[5]+"',tooltip: '"+s2[0]+"', description: '"+s2[2]+"'\">"+s2[0];

		}
		System.out.println("==str===4========="+str);
		return str; 
	} 				

	
	
	public String[] getcatinfo(String entry_id)
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		mysession.setAttribute("entryid",entry_id);
// 		String catalogid=(String)mysession.getAttribute("catalogid");
// 		if(catalogid==null){catalogid="";}
		String str[]=new String[3];
		//System.out.println("---------------catalogid------------now----------------------------------------------"+catalogid);
		Vector v1 =DataBaseLayer.getEntry2(entry_id);
		//System.out.println("catalog_id--------"+str[0]);
		String strMaterial="<table width=\"100%\">";
		for(int i=0;i<v1.size();i++)
		{
			str=(String[])v1.elementAt(i);
			
			System.out.println("aaaaaaaaaaaaaaaa--111111111------"+v1.elementAt(i));
			
		}
		System.out.println("data--------"+str[0]);
		System.out.println("data--------"+str[1]);
		System.out.println("data--------"+str[2]);
		return str;
	
	}  	
		
	
	public String[] getcatinfo2(String entry_id)
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String str[]=new String[3];
		DataBaseLayer.addEntryCatalog(entry_id);
		Vector v1 =DataBaseLayer.getEntry2(entry_id);
		//System.out.println("catalog_id--------"+str[0]);
		String strMaterial="<table width=\"100%\">";
		for(int i=0;i<v1.size();i++)
		{
			str=(String[])v1.elementAt(i);
			
			System.out.println("aaaaaaaaaaaaaaaa--111111111------"+v1.elementAt(i));
			
		}
		System.out.println("data--------"+str[0]);
		System.out.println("data--------"+str[1]);
		System.out.println("data--------"+str[2]);
		return str;
	
	}  	
	
	public void add_entry(String cat_id,String Entri_Name_text,String Entri_Desc_text,String New_Entri_Name_text,String New_Entri_Desc_text)
	{
	        
	        	
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String catalogid=(String)mysession.getAttribute("catalogid");
		String user_id = (String) mysession.getAttribute("user_id");
		String parent_name="NULL";
		//String parent_name=(String)mysession.getAttribute("catalogid");
			
			
		System.out.println("add_entry cat_id,Entri_Name_text,New_Entri_Name_text : -"+cat_id+" , "+Entri_Name_text+","+New_Entri_Name_text);	
		if((New_Entri_Name_text !=null)&&(!(New_Entri_Name_text.equals("")))){
	       		
			System.out.println("=========================if==========");					
			DataBaseLayer.addSubEntryCatalog(cat_id,New_Entri_Name_text,Entri_Name_text,New_Entri_Desc_text);
                      		
		}
        	//System.out.println("entryname :"+entryname+ " |entrydescription:"+entrydescription +" Type:"+entrytype +" catalogname:"+catalogname);
		else{      
			System.out.println("=========================else==========");						//cat_id,Entri_Name_text,Entri_Desc_text,New_Entri_Name_text,New_Entri_Desc_text   
			DataBaseLayer.addEntryCatalog(cat_id,Entri_Name_text,parent_name,Entri_Desc_text);
                
		}
		System.out.println("============end===============");
	}
	
	
	public void setSessionCatalogId(String cat_id)
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		mysession.setAttribute("catalogid",cat_id);					

											
	}
	
	
	
	
	
	public String getNewEntry(String etype)
	{
		String Options = "";
		System.out.println("etype :"+etype);
		
		if(etype!= null && etype.equals("unit"))
		{
		   
			Vector vUnitList = DataBaseLayer.getCourseDetailsList1();
			if(vUnitList==null) {
// 				option3 = new Option[1];
// 				option3[0] = new Option("0").addElement("[Choose One]");
				Options+="<option value='0'>[Choose One]</option>";
			}
			else {
// 				option3 = new Option[vUnitList.size()+1];
// 				option3[0] = new Option("0").addElement("[Choose One]");
				Options+="<option value='0'>[Choose One]</option>";
				for(int i=0; i<vUnitList.size(); i++) {
					Vector vUnit = (Vector) vUnitList.elementAt(i);
					String strId = (String) vUnit.elementAt(0);
					String strName = (String) vUnit.elementAt(1);
// 					option3[i+1] = new Option(strName).addElement(strName);
					Options+="<option value='"+strName+"'>"+strName+"</option>";
				}
			}
		}
		else if(etype!= null && etype.equals("forum"))
		{
			Vector vList = DataBaseLayer.getForumName();
			if(vList==null) {
// 				option3 = new Option[1];
// 				option3[0] = new Option("0").addElement("[Choose One]");
				Options+="<option value='0'>[Choose One]</option>";
			}
			else {
// 				option3 = new Option[vList.size()+1];
// 				option3[0] = new Option("0").addElement("[Choose One]");
				Options+="<option value='0'>[Choose One]</option>";
			    
				for(int i=0; i<vList.size(); i++) {
					String strUsrId[] = (String [])vList.elementAt(i);
					//option3[i+1] = new Option(strUsrId[1]).addElement(strUsrId[1]);
					Options+="<option value='"+strUsrId[1]+"'>"+strUsrId[1]+"</option>";                         
				}
			}
		}
		else if(etype!= null && etype.equals("sce"))
		{
			Vector vUnitList = DataBaseLayer.selectSynchronousEnvironmentForRegis();
			if(vUnitList==null) {
// 				option3 = new Option[1];
// 				option3[0] = new Option("0").addElement("[Choose One]");
				Options+="<option value='0'>[Choose One]</option>";
			}
			else {
// 				option3 = new Option[vUnitList.size()+1];
// 				option3[0] = new Option("0").addElement("[Choose One]");
				Options+="<option value='0'>[Choose One]</option>";
				for(int i=0; i<vUnitList.size(); i++) {
					Vector vUnit = (Vector) vUnitList.elementAt(i);
					String strId = (String) vUnit.elementAt(0);
					String strName = (String) vUnit.elementAt(1);
					//option3[i+1] = new Option(strName).addElement(strName);
					Options+="<option value='"+strName+"'>"+strName+"</option>";  
				}
			}
		}
		
		
		else if(etype!= null && etype.equals("course"))
		{
			Vector vList = DataBaseLayer.getCourseFullList();
			if(vList==null) {
// 				option3 = new Option[1];
// 				option3[0] = new Option("0").addElement("[Choose One]");
				Options+="<option value='0'>[Choose One]</option>";
			}
			else {
// 				option3 = new Option[vList.size()+1];
// 				option3[0] = new Option("0").addElement("[Choose One]");
				Options+="<option value='0'>[Choose One]</option>";
			    
				for(int i=0; i<vList.size(); i++) {
					String strUsrId[] = (String [])vList.elementAt(i);
					/*option3[i+1] = new Option(strUsrId[1]).addElement(strUsrId[1]);     */   
					Options+="<option value='"+strUsrId[1]+"'>"+strUsrId[1]+"</option>";                   
				}
			}
		}		
		
		else
		{
// 			option3 = new Option[1];
// 			option3[0] = new Option("0").addElement("[Choose One]");
			Options+="<option value='0'>[Choose One]</option>";
		}
		
		
		return Options;
		
	}  	
	
	
	/*  ------------------- 31122008   --------------------------- */
	public void UploadFile(String rptid,FileTransfer file) {
		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
				
		String dirName="";
		String strLocation = "";
		
		ResourceBundle rb = ResourceBundle.getBundle("portal",Locale.getDefault());
		String key1= "reportpath"; 
		strLocation = rb.getString(key1);
		
		dirName=strLocation;
		File ufile=null;
		System.out.println("dir name is:"+dirName);
		String html="";
		if(dirName == null)
			html="Please supply uploadDir parameter";

		ufile = new File(dirName);
		if(!ufile.exists())
			ufile.mkdir(); 
					
		FileOutputStream out; 
		PrintStream p;
		String thisLine="";
		
		FileUploaderPojo fu = new FileUploaderPojo(file);
		String MimeType = fu.getMimeType();
		InputStream is = fu.getInputStream();
		String filename = fu.getFilename();
		dirName = dirName+File.separator+filename;
		String content = "";
		
		
		try {
					
			File f;
			f=new File(dirName);
			if(!f.exists()){
				f.createNewFile();
			}
			BufferedReader myInput = new BufferedReader
					(new InputStreamReader(is));
			while ((thisLine = myInput.readLine()) != null) 
			{  
 				
				if(content.equals(""))
				{
					content = thisLine;
				}
				else {
					content = content +"\n"+ thisLine;
				}
			}
			out = new FileOutputStream(dirName);

                        
			p = new PrintStream( out );
		
			p.println(content);

			p.close();
							
							
						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		String strSize="";
		File file_size=new File(dirName);
		Long size2=file_size.length();
		strSize=size2.toString();
		System.out.println("=======5============"+strSize);
		DataBaseLayer.addRptdesign(rptid,strSize,filename);
		
	}
	/*---------after-080109------------------*/
	
			
			
	public void AssociateCourse(String cat_id,String Entri_Name_text,String course_id,String New_Entri_Desc_text)
	{
	        
	        	
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String catalogid=(String)mysession.getAttribute("catalogid");
		String user_id = (String) mysession.getAttribute("user_id");
		String entryid = (String) mysession.getAttribute("entryid");
				
		System.out.println("cat_id,Entri_Name_text,New_Entri_Name_text,entryid : -"+cat_id+" , "+Entri_Name_text+","+New_Entri_Desc_text+" , "+entryid);	
				//if((New_Entri_Name_text !=null)&&(!(New_Entri_Name_text.equals("")))){
	       		
							
		DataBaseLayer.AssociateEntries(cat_id,Entri_Name_text,course_id,New_Entri_Desc_text,user_id,entryid);
                      		
				//}
        	
// 				else{      
		// 						   
// 					DataBaseLayer.AssociateEntries(cat_id,Entri_Name_text,parent_name,Entri_Desc_text);
		//                 
// 				}
	}
	/*-----------end 080109--------------------*/
			
			
			
			
			
			
			
			
			
			
	
	/*----------- 090109--------------------*/
			
			
	public void insertTempCourse(String entry_id)
	{
	        
	        			 		
		System.out.println("----------insertTempCourse(String entry_id)---------------------------");			
		DataBaseLayer.addEntryCatalog(entry_id);
                      		
	}
			
	/*-----------end 090109--------------------*/
	
	
	
	
	
	
	
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
	
	
	
	public String[] getUserInfo()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String str[]=new String[15];
		try
		{
		
		
		
		//Vector v1 =DataBaseLayer.getUserData(user_id);
		str =DataBaseLayer.getUserData(user_id);
		System.out.println("----------------------------------------------");
		}
		catch(Exception e){e.printStackTrace();}		
		return str;
	
	}  	
	
	
	
	
	
	
	
	public void ModifyUser(String FNamedata,String MNamedata,String LNamedata,String Agedata,String Experiencedata,String EduQuilidata,String Emaildata,String Genderdate,String QuestionPreferreddate,String MediaPreferreddata,String AccountStatusdata,String PermitSelfRegistrationdata)
	{
	        
	        	
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
			
			
			
							
		DataBaseLayer.updateStudentDetails(user_id,FNamedata,MNamedata,LNamedata,Agedata,Experiencedata,EduQuilidata,Emaildata,Genderdate,QuestionPreferreddate,MediaPreferreddata,AccountStatusdata,PermitSelfRegistrationdata);
                      		
		//}
        	
// 		else{      
// 						//cat_id,Entri_Name_text,Entri_Desc_text,New_Entri_Name_text,New_Entri_Desc_text   
// 			DataBaseLayer.addEntryCatalog(cat_id,Entri_Name_text,parent_name,Entri_Desc_text);
		//                 
// 		}
	}
	
	
	
	public void setSessionCourseId(String course_id)
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		mysession.setAttribute("course_id",course_id);					
		//System.out.println("course_id-------------------"+course_id);
											
	}
	
	
	
	public String[] getCourseData()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String course_id = (String) mysession.getAttribute("course_id");
		String entry_id = (String) mysession.getAttribute("entryid");
		System.out.println("----------entry_id------------------------------------"+entry_id);
		
		String str[]=new String[7];
		
		try
		{
			str =DataBaseLayer.getCourseInfo(entry_id, course_id, user_id);
			System.out.println("----------entry_id------------------------------------"+entry_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		

		System.out.println("data--------"+str[0]);
		System.out.println("data--------"+str[1]);
		System.out.println("data--------"+str[2]);
		System.out.println("data--------"+str[3]);
		System.out.println("data--------"+str[4]);
		System.out.println("data--------"+str[5]);
		System.out.println("data--------"+str[6]);
		return str;
	
	}  	
	
	
	
	
	
	
	
	public void addCourse_User_Association()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String course_id = (String) mysession.getAttribute("course_id");
		String entry_id = (String) mysession.getAttribute("entryid");
		
		
        
		if((DataBaseLayer.checkConfirmation(course_id)).equals("Yes"))
		{
        	
			DataBaseLayer.sentForConfirmation(user_id, course_id);
		}
		else
		{

	       			
			DataBaseLayer.insertCourseUserAssociationSelf(user_id, course_id);

		}
			
	}
	
		
	public String[] getCourseRegistrationData()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String course_id = (String) mysession.getAttribute("course_id");
		String entry_id = (String) mysession.getAttribute("entryid");
		System.out.println("----------entry_id------------------------------------"+entry_id);
		
		String str[]=new String[3];
		str[0]=user_id;
		str[1]=course_id;
		str[2]=entry_id;
			
		

		System.out.println("data--------"+str[0]);
		System.out.println("data--------"+str[1]);
		System.out.println("data--------"+str[2]);
		return str;
	
	}  	
		
		
		
		
		//public void UploadDashFile(FileTransfer file) {
		
			
			
						
	//////////////////////////////////////////
						
	public static void UploadDashFile(FileTransfer file_zip)
	{

	
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
				
				
		String html="";
		String temp_dir = Host.getAdminPath();
		ladminTree c_m_s=new ladminTree();
		String filename = c_m_s.UploadCourseXmlZip(file_zip);
		c_m_s.UnZip(temp_dir+File.separatorChar+filename);
		
	 
	
		String course_xml_main = temp_dir+File.separatorChar+"metric.xml";
		System.out.println("==========course_xml_main========="+course_xml_main);
		Document course_main_doc = null;	
		DOMParser parser = new DOMParser();
		try
		{ 
			System.out.println("=============11======parse Start============");   
			parser.parse(course_xml_main);
			course_main_doc = parser.getDocument();
			Element course_root = course_main_doc.getDocumentElement();
			System.out.println("==================22222222============");   
			NodeList c_def_nl = course_main_doc.getElementsByTagName("Metrics");
					//NodeList c_def_n2 = course_main_doc.getElementsByTagName("Metrics");
					
					
			course_main_doc.getDocumentElement ().normalize ();
					 
			Element e = (Element)c_def_nl.item(0);
			System.out.println("==================22222222==333333=========="); 
			String size = e.getAttribute("Size");
			System.out.println("========2.1  2.1====== size of data ========"+size);
			Integer sizeofdata = Integer.parseInt(size);
	       
					
			for(int j=0;j<sizeofdata;j++){
				NodeList c_def_nl2 = course_root.getElementsByTagName("Metric"+j+"");
					
					
					
				System.out.println("==================333==c_def_nl=========="+c_def_nl2.getLength()); 
				if(c_def_nl2.getLength()!=0){
					System.out.println("==================44444==c_def_nl2=========="); 
					Node c_def_node = c_def_nl2.item(0);
					NodeList c_def_data_nl = c_def_node.getChildNodes();
					String course_def_data[] = new String[21]; 
					int c_def_size = 0;
					for(int i=0;i<c_def_data_nl.getLength();++i){
	        	
						Node c_def_data_node = c_def_data_nl.item(i);
						if((c_def_data_node.getNodeName())!="#text"){
							String c_def_value = "";
							if(c_def_data_node.getFirstChild()!=null){
								c_def_value = c_def_data_node.getFirstChild().getNodeValue();
							}
							course_def_data[c_def_size] = c_def_value;
							c_def_size++;
						}
					}
					System.out.println("========55===========parse Start============");   
					DataBaseLayer.ImportMatricef(course_def_data,user_id);
				}
					//String c_id = DataBaseLayer.returnmaxcourse();	
		
			}
		
		
		
		}
		catch (SAXException e) {
					//System.out.println(e);
			e.printStackTrace();
		} 
		catch (DOMException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
	
	
	
	
	
	
	
	}



			
	public String UploadCourseXmlZip(FileTransfer file) {

		String html = "";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();

		String dirName="";
		String strLocation = Host.getAdminPath();
		System.out.println("===============strLocation========="+strLocation);
		dirName=strLocation;
			


		FileOutputStream out;
		PrintStream p;

		String thisLine="";

		FileUploaderPojo fu = new FileUploaderPojo(file);
		String MimeType = fu.getMimeType();
		System.out.println("==============MimeType=========="+MimeType);
		InputStream is = fu.getInputStream();
		System.out.println("==============is============="+is);
		String filename = fu.getFilename();
		System.out.println("==============filename============="+filename);
		dirName = dirName+File.separator+filename;

		String content = "";
		System.out.println("==============dirName=========="+dirName);
		try {

			System.out.println("dirName==="+dirName);
			out = new FileOutputStream(dirName);

			int length = 0;
			byte b[]= new byte[1024];
			try
			{
				while ((is != null) && ((length = is.read(b)) != -1))
				{
					System.out.println("=======test1======");
					out.write(b,0,length);
				}
			}
			catch (IOException ex) {

			}

			System.out.println("=======test2======");


			p = new PrintStream( out );

			p.println(content);

			p.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return filename;
	}

	
			
	public void UnZip(String filename) {
	 	
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String uploadBy = (String)mysession.getAttribute("user_id");
		try
		{
			String destinationname =  ""+ Host.getAdminPath();
			byte[] buf = new byte[1024];
			ZipInputStream zipinputstream = null;
			ZipEntry zipentry;
			zipinputstream = new ZipInputStream(
					new FileInputStream(filename));

			zipentry = zipinputstream.getNextEntry();
			while (zipentry != null) { 
              
				String entryName = zipentry.getName();
				System.out.println("entryname "+entryName);
				int n;
				FileOutputStream fileoutputstream;
                
				File newFile = new File(destinationname+File.separator+entryName);
				if(zipentry.isDirectory()) {
					newFile.mkdirs();
					zipinputstream.closeEntry();
					zipentry = zipinputstream.getNextEntry();
					continue;
				}
				String parent = newFile.getParent();
				System.out.println("===========parent============="+parent);
				if( parent != null ) {
					java.io.File parentFile = new java.io.File(parent);
					System.out.println("===============parentFile==============="+parentFile);
					if ( !parentFile.exists() ) {
			
						parentFile.mkdirs();
					}
				} 
		
               
				fileoutputstream = new FileOutputStream(
						destinationname+File.separator+entryName);             

				while ((n = zipinputstream.read(buf, 0, 1024)) > -1)
					fileoutputstream.write(buf, 0, n);
			     
				fileoutputstream.close(); 
				zipinputstream.closeEntry();
				zipentry = zipinputstream.getNextEntry();

			}//while

			zipinputstream.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	     
		System.out.println("======================unzip completed=========");
	}     
     
     
			
			
			
			
	////////////////////////////////////////////////////
			
				
			
		
		//}
		
		
		
	public void delete_entry(String cat_id,String Entri_Name_text,String Entri_Desc_text,String New_Entri_Name_text,String New_Entri_Desc_text)
	{
	        
	        	
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String catalogid=(String)mysession.getAttribute("catalogid");
		String parent_name="NULL";
					
			
		System.out.println("cat_id,Entri_Name_text,New_Entri_Name_text : -"+cat_id+" , "+Entri_Name_text+","+New_Entri_Name_text);	
				
							
		DataBaseLayer.deletetemp_catalogentry(cat_id,Entri_Name_text);
                      		
				        	
				
	}
	public void modify_entry(String entryname,String entrydescription)
	{
	        
	        	
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String entryid=(String)mysession.getAttribute("entryid");
				
					
			
							
		DataBaseLayer.update_catalogentry(entryid,entryname,entrydescription);
                      		
				        	
				
	}
		
		
			
			
			
			
			
	public void confirm_registration(String course_id,String student_id)
	{
				
		WebContext wctx1 = WebContextFactory.get();
				
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String)mysession.getAttribute("user_id");
				
		if(course_id != null){
					
			Vector vDateTime = DataBaseLayer.getDateTimeRequest(course_id,student_id);
	        	
						//log.info("DateTime : "+ vDateTime);
			String strValidTill = "";
			String strTotalTime = "";
			for(int k=0; k<vDateTime.size(); k=k+2) {
	        		
				strValidTill = (String) vDateTime.elementAt(0);
				strTotalTime = (String) vDateTime.elementAt(1);
	        					//log.info(strTotalTime + strValidTill);
				String df = strValidTill;
				if(strValidTill == null)
				{
					df = "";
				}
				if(strTotalTime == null)
				{
					strTotalTime = "";
				}
		       					//log.info("|"+strTotalTime+"|" + strValidTill);
			      		        
			       
				DataBaseLayer.insertCourseUserAssociation2(student_id, course_id, user_id,df, strTotalTime);
// 				DataBaseLayer.confirmedRequest(course_id,student_id,"course");
			}
					//}
		}
		
	}
	public void confirm_selfregistration()
	{
				
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String course_id = (String) mysession.getAttribute("course_id");
		String entry_id = (String) mysession.getAttribute("entryid");
		System.out.println("-------------------------------");
		System.out.println("confirm_selfregistration()-----------------");
		System.out.println("-----------course_id--------------------"+course_id);
		try
		{
			if(course_id != null){
				System.out.println("---------11--course_id--------------------"+course_id);
				Vector vDateTime = DataBaseLayer.getDateTimeRequest(course_id,user_id);
	        	
				System.out.println("-------suze--------------"+vDateTime.size());	//log.info("DateTime : "+ vDateTime);
				String strValidTill = "";
				String strTotalTime = "";
				String df = strValidTill;
				for(int k=0; k<vDateTime.size(); k=k+2) {
					System.out.println("-----22------course_id--------------------"+course_id);
					strValidTill = (String) vDateTime.elementAt(0);
					strTotalTime = (String) vDateTime.elementAt(1);
	        					//log.info(strTotalTime + strValidTill);
						
					if(strValidTill == null)
					{
						df = "";
					}
					if(strTotalTime == null)
					{
						strTotalTime = "";
					}
		       					//log.info("|"+strTotalTime+"|" + strValidTill);
			      		        
					System.out.println("------33-----course_id--------------------"+course_id);
						
				}
				DataBaseLayer.insertCourseUserAssociation2(user_id, course_id, user_id,df, strTotalTime);
// 				DataBaseLayer.confirmedRequest(course_id,user_id,"course");
					
					//}
			}
				
				
				
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
			
	public void cancel_request()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String course_id = (String) mysession.getAttribute("course_id");
		String entry_id = (String) mysession.getAttribute("entryid");
		System.out.println("cancel_request()-----------------");
        
// 		if((DataBaseLayer.checkConfirmation(course_id)).equals("Yes"))
// 		{
        	
			DataBaseLayer.CancelConfirmation(user_id, course_id);
// 		}
		// 				
	}
	public void cancel_SelfRegistration()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String course_id = (String) mysession.getAttribute("course_id");
		String entry_id = (String) mysession.getAttribute("entryid");
		System.out.println("cancel_SelfRegistration()-----------------");
        
		if((DataBaseLayer.checkConfirmation(course_id)).equals("No"))
		{
        	
			DataBaseLayer.CancelSelfConfirmation(user_id, course_id);
		}
		// 				
	}
			
			
			
	public void deny_registration(String course_id,String user_id)
	{
		System.out.println("course_id------------------"+course_id);
		System.out.println("user_id------------------"+user_id);
		if(course_id != null){
					//for(int i=0; i<strCourseIds.length; ++i) {
	        	
// 						String user_id= strCourseIds[i].substring(strCourseIds[i].indexOf('-')+1);
// 						String course_id= strCourseIds[i].substring(0,strCourseIds[i].indexOf('-'));
			// 	        	
			DataBaseLayer.denyRequest(course_id,user_id);
					//}
		}
		
	}
	/*=====================================End Jayanta===========================================*/
	
	/****************************** Partha for Image Upload ***************************/
			
			public String UserImageUpload(String student_id,FileTransfer file) {
		
		String html = "";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		user_id = (user_id == null) ? "" : user_id;
					
		String dirName="";
		String strLocation = Host.getAdminPath();
		dirName=strLocation;
		File ufile=null;
		
		
		if(dirName == null)
			html="Please supply uploadDir parameter";

		ufile = new File(dirName);
		if(!ufile.exists())
			ufile.mkdir(); 
					
		
		FileOutputStream out;
		PrintStream p;
		String thisLine="";
		FileUploaderPojo fu = new FileUploaderPojo(file);
		String MimeType = fu.getMimeType();
		
		InputStream is = fu.getInputStream();
		String filename = fu.getFilename();
		dirName = dirName+File.separator+filename;
		//int size = (int)(dirName.length());
		 
		try {
	
			System.out.println("dirName==="+dirName);
			out = new FileOutputStream(dirName);
	
			int length = 0;
			byte b[]= new byte[1024];
	
			while ((is != null) && ((length = is.read(b)) != -1))
			{
	
				out.write(b,0,length);
			}
	
	
	
	
			p = new PrintStream( out );
	
			p.close();
			File inFile=new File(dirName);
			InputStream inStream= new FileInputStream(inFile);
			int size =(int)(inFile.length());
			DataBaseLayer.updateStudentPhoto(student_id,inStream,size);
	
	
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		catch(Exception exp)
		{
			System.err.println("=========error======="+exp.toString());
		}
		
		
		
		
		
		return html;
			}			
			
			
 //}			
			
			/************************** End of Partha for Image Upload *************************/		
	
	/*=================================Nayna===================================*/	
	public void setScormUnitId(String unit_id)
	{
		if(unit_id==null){unit_id="";}
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		System.err.println("=========unit_id======="+unit_id);
		mysession.setAttribute("scorm_unitid",unit_id);
	}	
	public String createDropDownScormInitGroup(String unit_id)
	{
		String html = "";
		System.out.println("unit_id ======createDropDownScormInitGroup==========="+unit_id);
		Vector vGroupList = (Vector)DataBaseLayer.getCourseGroupAssociation1(unit_id);
		System.out.println("vGroupList ======createDropDownScormInitGroup==========="+vGroupList);
		if(vGroupList==null){
			html="<option value='0'>[Choose One]</option>";
		}
		else{
			if(vGroupList.size()==0)
			{
				html="<option value='0'>[Choose One]</option>";
			}
			else
			{
				html="<option value='0'>[Choose One]</option>";
				for(int i=0; i<vGroupList.size(); i++) {
					Vector vGroup = (Vector) vGroupList.elementAt(i);
					String strGrId = (String) vGroup.elementAt(0);
					String strGrName = (String) vGroup.elementAt(1);
					html +="<option value='"+strGrId+"'>"+strGrName+"</option>";
				}
			}
		}
		System.out.println("html ==="+html);
		
		return html;
	}		
	public String createDropDownScormInitUser(String unit_id)
	{
		String html = "";
		System.out.println("unit_id ======createDropDownScormInitUser==========="+unit_id);
		Vector vGroupList = (Vector)DataBaseLayer.getCourseUserAssociation1(unit_id);
		if(vGroupList==null){
			html="<option value='0'>[Choose One]</option>";
		}
		else{
			if(vGroupList.size()==0)
			{
				html="<option value='0'>[Choose One]</option>";
			}
			else
			{
				html="<option value='0'>[Choose One]</option>";
				for(int i=0; i<vGroupList.size(); i++) {
					Vector vGroup = (Vector) vGroupList.elementAt(i);
					String strGrId = (String) vGroup.elementAt(0);
					String strGrName = (String) vGroup.elementAt(1);
					html +="<option value='"+strGrId+"'>"+strGrName+"</option>";
				}
			}
		}
		System.out.println("html ==="+html);
		
		return html;
	}	
	public void createTableScormInitialization(String unit_id, String user_id){
		DataBaseLayer.createTableScormInitialization(unit_id,user_id);
		
	}
	public void modifyUserScormInitialize(String unit_id, String user_id, String scoid, String mode, String entry, String suspend, String status1, String credit, String exit, String launch, String totaltime){
		if (totaltime == null || totaltime.equals("")) {
			totaltime = "00:00:00.0";
		}
		DataBaseLayer.SCOInitialize2(user_id, unit_id, scoid, mode, entry, suspend, status1, credit, exit, launch, totaltime);	
		
	}
	public void modifyGroupScormInitialize(String unit_id, String user_id, String mode, String entry, String suspend, String status1, String credit, String exit, String launch, String totaltime){
		if (totaltime == null || totaltime.equals("")) {
			totaltime = "00:00:00.0";
		}
		System.out.println("group_id=="+user_id+"   unit_id=="+unit_id);
		Vector vUserFromGroup = DataBaseLayer.getUserGroupDetailsList(user_id);
		if((vUserFromGroup != null) && (vUserFromGroup.size() > 0)) {
			for(int i=0; i<vUserFromGroup.size(); i++) {
				Vector vUser = (Vector) vUserFromGroup.elementAt(i);
				String strUsrId = (String) vUser.elementAt(0);
				System.out.println("unit_id=="+unit_id+"   strUsrId=="+strUsrId);
				if (!DataBaseLayer.isExistStudent(strUsrId, unit_id)) {
					System.out.println("==not==exits============");
					DataBaseLayer.SCOInitialize(strUsrId, unit_id, mode, entry, suspend, status1, credit, exit, launch, totaltime);	
				}
			} 
		}
		
	}
	public void modifyAllUserScormInitialize(String unit_id, String mode, String entry, String suspend, String status1, String credit, String exit, String launch, String totaltime){
		if (totaltime == null || totaltime.equals("")) {
			totaltime = "00:00:00.0";
		}
		System.out.println("unit_id=="+unit_id);
		Vector vUserList = DataBaseLayer.getCourseUserAssociation1(unit_id);
		if(vUserList != null) {
			for(int i=0; i<vUserList.size(); i++) {
				Vector vUser = (Vector) vUserList.elementAt(i);
				String strUsrId = (String) vUser.elementAt(0);
				System.out.println("strUsrId=="+strUsrId+"   unit_id=="+unit_id);
				DataBaseLayer.SCOInitialize(strUsrId, unit_id, mode, entry, suspend, status1, credit, exit, launch, totaltime);
			}
		}
		
	}
	/*===================================nayna 08.04.09======start===============================*/	
				

	public FileTransfer exportUSerData(String str){
		
		InputStream sis  = null;
		String strLocation = Host.getAdminPath();
		SystemExport se = new SystemExport(str, strLocation, strLocation +File.separator+ "SystemExport.zip");
		
		try {
			se.export();       
			String zip1 = strLocation +File.separator+ "SystemExport.zip";
			sis  = new FileInputStream(zip1);
		}     
		catch (FileNotFoundException f) {
			f.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		FileDownloaderPojo fd = new FileDownloaderPojo(sis, "","SystemExport.zip");
		return fd.returnFileFormat(); 
	}
	
	public void importData(FileTransfer file){
		
		String strLocation = Host.getAdminPath();
		String dirName="";
		dirName=strLocation;
		File ufile=null;
		ufile = new File(dirName);
		if(!ufile.exists())
			ufile.mkdir(); 
		FileOutputStream out; 
		PrintStream p;
		FileUploaderPojo fu = new FileUploaderPojo(file);
		String MimeType = fu.getMimeType();
		InputStream is = fu.getInputStream();
		String filename = fu.getFilename();
		dirName = dirName+File.separator+filename;
		File file1 = new File(dirName);
		boolean b1 = file1.delete();
		String content = "";
		try {
			out = new FileOutputStream(dirName);
			int length = 0;
			byte b[]= new byte[1024];
			try 
			{
				while ((is != null) && ((length = is.read(b)) != -1)) 
				{
					out.write(b,0,length);
				}
			}
			catch (IOException ex) {
				
			}
			p = new PrintStream( out );		
			p.println(content);
			p.close();		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		SystemImport si = new SystemImport(dirName);
 		try {
			si.execute();       
		}     
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getSelfRegistrationStatus()
	{
	        
		String strSelfRegsStatus="";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		
		String user_id = (String) mysession.getAttribute("user_id");				 		
		System.out.println("----------user_id-------------------------"+user_id);
		strSelfRegsStatus=DataBaseLayer.getSelfRegsStatus(user_id);
		System.out.println("----------strSelfRegsStatus-------------------------"+strSelfRegsStatus);
		return strSelfRegsStatus;
                      		
	}
	
	public String getCourseSelfRegistrationStatus(String strCourse)
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String strCourseSelfRegsStatus=DataBaseLayer.getCourseSelfRegsStatus(strCourse);
		System.out.println("----------strCourseSelfRegsStatus-------------------------"+strCourseSelfRegsStatus);
		return strCourseSelfRegsStatus;
                      		
	}
	
	/************************************ Partha on 06.02.10 *************************/
			public String confirmunit_registration(String unit_id,String student_id)
	{
		String msg="";
				
		WebContext wctx1 = WebContextFactory.get();
				
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String)mysession.getAttribute("user_id");
				
		if(unit_id != null){
					
			Vector vDateTime = DataBaseLayer.getDateTimeRequestForUnit(unit_id,student_id);
	        	
						//log.info("DateTime : "+ vDateTime);
			String strValidTill = "";
			String strTotalTime = "";
			for(int k=0; k<vDateTime.size(); k=k+2) {
	        		
				strValidTill = (String) vDateTime.elementAt(0);
				strTotalTime = (String) vDateTime.elementAt(1);
	        					//log.info(strTotalTime + strValidTill);
				String df = strValidTill;
				if(strValidTill == null)
				{
					df = "";
				}
				if(strTotalTime == null)
				{
					strTotalTime = "";
				}
		       					//log.info("|"+strTotalTime+"|" + strValidTill);
			      		        
			       
				msg=DataBaseLayer.insertUnitUserAssociation(student_id, unit_id, user_id,df, strTotalTime);
				DataBaseLayer.confirmedRequest(unit_id,student_id,"unit");
			}
					//}
		}
		return msg;
	}
	
	
	public String denyunit_registration(String unit_id,String user_id)
	{
		String msg="";
		
		if(unit_id != null){
			msg=DataBaseLayer.denyUnitRequest(unit_id,user_id);
					
		}
		return msg;
	}
	
	
	
	public String updateStatus(String unit_id,String student_id,String status)
	{
		String msg="";		
		WebContext wctx1 = WebContextFactory.get();
				
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String)mysession.getAttribute("user_id");
		if(status.equals("Denied"))
		{		
			Vector vDateTime = DataBaseLayer.getDateTimeRequestForUnit(unit_id,student_id);
	        	
						//log.info("DateTime : "+ vDateTime);
			String strValidTill = "";
			String strTotalTime = "";
			for(int k=0; k<vDateTime.size(); k=k+2) {
	        		
				strValidTill = (String) vDateTime.elementAt(0);
				strTotalTime = (String) vDateTime.elementAt(1);
	        					//log.info(strTotalTime + strValidTill);
				String df = strValidTill;
				if(strValidTill == null)
				{
					df = "";
				}
				if(strTotalTime == null)
				{
					strTotalTime = "";
				}
		       					//log.info("|"+strTotalTime+"|" + strValidTill);
			      		        
			       
				msg=DataBaseLayer.insertUnitUserAssociation(student_id, unit_id, user_id,df, strTotalTime);
				DataBaseLayer.confirmedRequest(unit_id,student_id,"unit");
			}
					//}
		}
		else
		{
			DataBaseLayer.deleteUnitUserAssociation(student_id,unit_id);
			msg=DataBaseLayer.denyUnitRequest(unit_id,student_id);
		}
		return msg;
	}
	
	public String getUserIdFromSession()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		
		return user_id;
	
	}  	
	
	public String updateStatusCourse(String course_id,String student_id,String status)
	{
		System.out.println("==================INSIDE updateStatusCourse======================");
		String msg="";		
		WebContext wctx1 = WebContextFactory.get();
				
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String)mysession.getAttribute("user_id");
		if(status.equals("Denied"))
		{		
			
			System.out.println("==================INSIDE IF======================");
			Vector vDateTime = DataBaseLayer.getDateTimeRequest(course_id,student_id);
	        	
						//log.info("DateTime : "+ vDateTime);
			String strValidTill = "";
			String strTotalTime = "";
			for(int k=0; k<vDateTime.size(); k=k+2) {
	        		
				strValidTill = (String) vDateTime.elementAt(0);
				strTotalTime = (String) vDateTime.elementAt(1);
				System.out.println(strTotalTime + strValidTill);
				String df = strValidTill;
				if(strValidTill == null)
				{
					df = "";
					System.out.println("==========DF============== "+df);
				}
				if(strTotalTime == null)
				{
					strTotalTime = "";
					System.out.println("==========STR TOTAL TIME============== "+strTotalTime);
				}
				System.out.println("|"+strTotalTime+"|" + strValidTill);
			      		        
			       
				DataBaseLayer.insertCourseUserAssociation(student_id, course_id, user_id,df, strTotalTime);
 				DataBaseLayer.confirmedRequest(course_id,student_id,"course");
			}
					//}
		}
		else
		{
			DataBaseLayer.deleteCourseUserAssociation(student_id,course_id);
			DataBaseLayer.denyRequest(course_id,student_id);
		}
		return msg;
	}
	
	
	public String getCourseSelfConfirmationStatus(String course_id)
	{
	        
		String html="";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		
		String user_id = (String) mysession.getAttribute("user_id");				 		
		System.out.println("----------user_id-------------------------"+user_id);
		html=DataBaseLayer.getCourseSelfConfirmationStatus(course_id,user_id);
		
		return html;
                      		
	}
	
	public void cancel_SelfRegistration(String course_id)
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		
		String entry_id = (String) mysession.getAttribute("entryid");
		System.out.println("cancel_SelfRegistration()-----------------");
        
// 		if((DataBaseLayer.checkConfirmation(course_id)).equals("No"))
// 		{
        	
			DataBaseLayer.CancelSelfConfirmation(user_id, course_id);
// 		}
		// 				
	}
	
	public void cancel_request(String course_id)
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		
		String entry_id = (String) mysession.getAttribute("entryid");
		System.out.println("cancel_request()-----------------");
        
// 		if((DataBaseLayer.checkConfirmation(course_id)).equals("Yes"))
// 		{
//         	
			DataBaseLayer.CancelConfirmation(user_id, course_id);
// 		}
		// 				
	}
	
	
	public void setSessionStudentIdtoUpdate(String student_id)
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		mysession.setAttribute("studentid_to_update",student_id);					

											
	}
	
	
	public String getSessionStudentIdtoUpdate()
	{
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String html = (String)mysession.getAttribute("studentid_to_update");					
		return html;
											
	}
	
	public String getStudentDetails()
	{
// 		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String student_id = (String)mysession.getAttribute("studentid_to_update");					
		System.out.println("===========Student ID========"+student_id);

		String innertable="<table>";
		Vector getStudentDetailsVector = DataBaseLayer.getStudentDetails(student_id);
		System.out.println("===========getStudentDetailsVector========"+getStudentDetailsVector);
		System.out.println("===========getStudentDetailsVector.size()========"+getStudentDetailsVector.size());		
		for(int i=0;i<getStudentDetailsVector.size();i=i+11)
		{
			System.out.println("===================innertable===0000000========="+innertable);
			innertable = innertable + "<tr><td>User Id</td><td>:</td><td>"+ (String)getStudentDetailsVector.elementAt(i) +"</td></tr>"+
					//"<tr><td>Rank/Designation : </td><td>"+ (String)getStudentDetailsVector.elementAt(i+1) +"</td></tr>"+
					"<tr><td>Name</td><td>:</td><td>"+ (String)getStudentDetailsVector.elementAt(i+1) +"</td></tr>"+
					"<tr><td>Gender</td><td>:</td><td>"+ (String)getStudentDetailsVector.elementAt(i+2) +"</td></tr>"+
					"<tr><td>Age</td><td>:</td><td>"+ (String)getStudentDetailsVector.elementAt(i+3) +"</td></tr>"+
					"<tr><td>Experience</td><td>:</td><td>"+ (String)getStudentDetailsVector.elementAt(i+4) +"</td></tr>"+
					"<tr><td>Education</td><td>:</td><td>"+ (String)getStudentDetailsVector.elementAt(i+5) +"</td></tr>"+
					"<tr><td>Department</td><td>:</td><td>"+ (String)getStudentDetailsVector.elementAt(i+7) +"</td></tr>"+
					"<tr><td>Email</td><td>:</td><td>"+ (String)getStudentDetailsVector.elementAt(i+8) +"</td></tr>"+
					"<tr><td>Account Status</td><td>:</td><td>"+ (String)getStudentDetailsVector.elementAt(i+6) +"</td></tr>"+
					"<tr><td>Self Registration</td><td>:</td><td>"+ (String)getStudentDetailsVector.elementAt(i+9) +"</td></tr>";
			System.out.println("===================innertable===1111111========="+innertable);
		}
		innertable = innertable+"</table>";
		System.out.println("===================innertable===2222222========="+innertable);
		return innertable;
											
	}
	
	public byte[]  getPhoto() {
		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String student_id = (String)mysession.getAttribute("studentid_to_update");
		byte[] buffer=DataBaseLayer.getUserPhotoForProfile(student_id);
		return buffer;
	}
	
	
	public String UserImageUpload(FileTransfer file) {
		
		String html = "";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String student_id = (String)mysession.getAttribute("studentid_to_update");
		user_id = (user_id == null) ? "" : user_id;
		student_id = (student_id == null) ? "" : student_id;
					
		String dirName="";
		String strLocation = Host.getAdminPath();
		dirName=strLocation;
		File ufile=null;
		
		
		if(dirName == null)
			html="Please supply uploadDir parameter";

		ufile = new File(dirName);
		if(!ufile.exists())
			ufile.mkdir(); 
					
		
		FileOutputStream out;
		PrintStream p;
		String thisLine="";
		FileUploaderPojo fu = new FileUploaderPojo(file);
		String MimeType = fu.getMimeType();
		
		InputStream is = fu.getInputStream();
		String filename = fu.getFilename();
		dirName = dirName+File.separator+filename;
		//int size = (int)(dirName.length());
		 
		try {
	
			System.out.println("dirName==="+dirName);
			out = new FileOutputStream(dirName);
	
			int length = 0;
			byte b[]= new byte[1024];
	
			while ((is != null) && ((length = is.read(b)) != -1))
			{
	
				out.write(b,0,length);
			}
	
	
	
	
			p = new PrintStream( out );
	
			p.close();
			File inFile=new File(dirName);
			InputStream inStream= new FileInputStream(inFile);
			int size =(int)(inFile.length());
			DataBaseLayer.updateStudentPhoto(student_id,inStream,size);
	
	
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		catch(Exception exp)
		{
			System.err.println("=========error======="+exp.toString());
		}
		
		
		
		
		
		return html;
	}	
	
	public byte[]  getPortalUserPhoto() {
		
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String student_id = (String)mysession.getAttribute("user_id");
		byte[] buffer=DataBaseLayer.getUserPhotoForProfile(student_id);
		return buffer;
	}
	
	
	
	public String PortalUserImageUpload(FileTransfer file) {
		
		String html = "";
		WebContext wctx1 = WebContextFactory.get();
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String) mysession.getAttribute("user_id");
		String student_id = (String)mysession.getAttribute("user_id");
		user_id = (user_id == null) ? "" : user_id;
		student_id = (student_id == null) ? "" : student_id;
					
		String dirName="";
		String strLocation = Host.getAdminPath();
		dirName=strLocation;
		File ufile=null;
		
		
		if(dirName == null)
			html="Please supply uploadDir parameter";

		ufile = new File(dirName);
		if(!ufile.exists())
			ufile.mkdir(); 
					
		
		FileOutputStream out;
		PrintStream p;
		String thisLine="";
		FileUploaderPojo fu = new FileUploaderPojo(file);
		String MimeType = fu.getMimeType();
		
		InputStream is = fu.getInputStream();
		String filename = fu.getFilename();
		dirName = dirName+File.separator+filename;
		//int size = (int)(dirName.length());
		 
		try {
	
			System.out.println("dirName==="+dirName);
			out = new FileOutputStream(dirName);
	
			int length = 0;
			byte b[]= new byte[1024];
	
			while ((is != null) && ((length = is.read(b)) != -1))
			{
	
				out.write(b,0,length);
			}
	
	
	
	
			p = new PrintStream( out );
	
			p.close();
			File inFile=new File(dirName);
			InputStream inStream= new FileInputStream(inFile);
			int size =(int)(inFile.length());
			DataBaseLayer.updateStudentPhoto(student_id,inStream,size);
	
	
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		catch(Exception exp)
		{
			System.err.println("=========error======="+exp.toString());
		}
		return html;
	}	
	
	/******************************** end of Partha on 06.02.10 ************************/
			
			
			
	/******************************** Start Soma on 12.11.2010  ***********************/
	public String updateForumStatus(String forum_id,String student_id,String status)
	{
		String msg="";		
		WebContext wctx1 = WebContextFactory.get();
				
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String)mysession.getAttribute("user_id");
		if(status.equals("Denied"))
		{		
			Vector vDateTime = DataBaseLayer.getDateTimeRequestForForum(forum_id,student_id);
	        	
						//log.info("DateTime : "+ vDateTime);
			String strValidTill = "";
			String strTotalTime = "";
			for(int k=0; k<vDateTime.size(); k=k+2) {
	        		
				strValidTill = (String) vDateTime.elementAt(0);
				strTotalTime = (String) vDateTime.elementAt(1);
	        					//log.info(strTotalTime + strValidTill);
				String df = strValidTill;
				if(strValidTill == null)
				{
					df = "";
				}
				if(strTotalTime == null)
				{
					strTotalTime = "";
				}
		       					//log.info("|"+strTotalTime+"|" + strValidTill);
			      		        
			       
				msg=DataBaseLayer.insertForumUserAssociation(student_id, forum_id, user_id,df,strTotalTime);
				DataBaseLayer.confirmedForumRequest(forum_id,student_id,"forum");
			//}
					}
		}
		else
		{
			DataBaseLayer.deleteForumUserAssociation(student_id,forum_id);
			msg=DataBaseLayer.denyForumRequest(forum_id,student_id);
		}
		return msg;
	}

	public String confirmforum_registration(String forum_id,String student_id)
	{
		String msg="";
				
		WebContext wctx1 = WebContextFactory.get();
				
		javax.servlet.http.HttpSession mysession = wctx1.getSession();
		String user_id = (String)mysession.getAttribute("user_id");
				
		if(forum_id != null){
					
			Vector vDateTime = DataBaseLayer.getDateTimeRequestForForum(forum_id,student_id);
	        	
						//log.info("DateTime : "+ vDateTime);
			String strValidTill = "";
			String strTotalTime = "";
			for(int k=0; k<vDateTime.size(); k=k+2) {
	        		
				strValidTill = (String) vDateTime.elementAt(0);
				strTotalTime = (String) vDateTime.elementAt(1);
	        					//log.info(strTotalTime + strValidTill);
				String df = strValidTill;
				if(strValidTill == null)
				{
					df = "";
				}
				if(strTotalTime == null)
				{
					strTotalTime = "";
				}
		       					//log.info("|"+strTotalTime+"|" + strValidTill);
			      		        
			       
				msg=DataBaseLayer.insertForumUserAssociation(student_id, forum_id, user_id,df, strTotalTime);
				DataBaseLayer.confirmedForumRequest(forum_id,student_id,"forum");
			}
					//}
		}
		return msg;
	}
	public String denyforum_registration(String forum_id,String user_id)
	{
		String msg="";
		
		if(forum_id != null){
			msg=DataBaseLayer.denyForumRequest(forum_id,user_id);
					
		}
		return msg;
	}
	
	public String getUnitHomePageContent(String unitId) {
		return DataBaseLayer.fetchHomePageContent(unitId);
	}

	public String saveUnitHomePageContent(String unitId, String homePageContent) {
		boolean isSucess = DataBaseLayer.saveHomePageContent(unitId,
				homePageContent);
		if (isSucess) {
			return "Successfully Saved";
		}else{
			return "Failed To save!!";
		}
	}
	
}