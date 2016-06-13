package learnityadmin;

// import org.w3c.dom.*;
import oracle.xml.parser.v2.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import comv2.aunwesha.param.*;
import javax.sql.DataSource;
import java.text.*;
import org.grlea.log.*;
import javax.imageio.stream.FileImageInputStream;
import learnityInit.Host;
/**
 * Title:        LearnITy
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:      Aunwesha Knowledge Technologies Pvt. Ltd.
 * @author 		shibaji and partha
 * @version 	 3.0
 */

public class DataBaseLayer {
	 private static final SimpleLogger log = new SimpleLogger(DataBaseLayer.class);
	 public static DataSource ds=CoreAdminInitHostInfo.ds;
	 public static DataSource ds1=CoreAdminInitHostInfo.ds1;
	 
	 
	 public static boolean CheckExcelStudentExists(String stuUsrId) {
		 Statement  oStmt=null;
		 ResultSet  oRset =null ;
		 boolean flag=false;
		 String str="";

		 Connection oConn = null;
		 try	{
			 oConn = ds1.getConnection();
      		//checkConnection();
			 oStmt = oConn.createStatement();
      		
			 oRset=oStmt.executeQuery("select student_id from student_details where student_id='"+stuUsrId+"' ");	
		
			 if(oRset.next()) {
				 str = oRset.getString(1);
			 }
		    
			
			 
		 }
		 catch (SQLException e){
			 System.out.println(" CheckExcelStudentExists"+e.getMessage());
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
		 }
		 catch (Exception ex){
			 System.out.println(" error due to java.lang.exception");
			 ex.printStackTrace();
      		
		 }
		 finally{
			 if(oConn!=null)
			 {
				 try
				 {
					 oStmt.close();
					 oConn.close();
					 oRset.close();
				 }
				 catch(SQLException e)
				 {
				 }
			
			
			 }
		
		 }	
    	
		 if(str.equals(""))            
		 {
			 flag=false;
		 }
		 else
		 {
			 flag=true;
		 }	
    	
		 return flag;
	 }		
		
	 public static boolean CheckValidUserRow(String stuUsrId,String password,String studentFname,String studentSname,String accountStatus) {
		 Statement  oStmt=null;
		 ResultSet  oRset;
		 boolean flag=true;
		 String str="";
	    
		
	    
    	
		 if((stuUsrId.equals("")) && (password.equals("")) && (studentFname.equals("")) && (studentSname.equals("")) && (accountStatus.equals("")))            
		 {
			 flag=false;
		 }
			
    	
	   
		 return flag;
	 }
	 		
	 public static synchronized void  insertStudentDetailsfrmExcelSheet(String studentId,String password,String studentFname,String studentMname,String studentSname,String accountStatus,String created_by,String age,String experience,String education,String gender,String email,String department,String selfregistration)
	 {
      	
		 Statement  oStmt=null;
		 Statement statement = null;
		 ResultSet  oRset;
		 String group_name="";
		 PreparedStatement pstmt1 = null;			
		 Connection oConn = null;
		 try
		 {
			 oConn = ds1.getConnection();
			 oStmt = oConn.createStatement();
 				//oConn.setAutoCommit(false);
			 statement = oConn.createStatement();
		    
		    
			    	
			    
			 pstmt1 = oConn.prepareStatement("insert into student_details (student_id, sname, "+
					 "first_name, middle_name,account_status,age,experience,edu_status,gender,email_id,department,strself) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			    
			 pstmt1.setString(1,studentId);
			 pstmt1.setString(2,studentSname);
			 pstmt1.setString(3,studentFname);
			 pstmt1.setString(4,studentMname);
			 pstmt1.setString(5,accountStatus);
			 pstmt1.setString(6,age);
			 pstmt1.setString(7,experience);
			 pstmt1.setString(8,education);
			 pstmt1.setString(9,gender);
			 pstmt1.setString(10,email);
			 pstmt1.setString(11,department);
			 pstmt1.setString(12,selfregistration);
			 pstmt1.executeUpdate();
			 oStmt.execute("insert into student_creation_details (student_id,student_created_by,date_student_created,last_modification_date) values('"+studentId+"','"+created_by+"',sysdate(),sysdate())");
			 oStmt.execute("insert into student_password (student_id, password) values('"+studentId+"','"+password+"')");
						
			// updateStudentSerialNo(studentId);

		 }
		 catch(SQLException sqlexception)
		 {
			 System.out.println("Exception in insertStudentDetailsfrmExcelSheet()");
			 System.out.println("The Error Message - " + sqlexception.getMessage());
		 }
		 catch(Exception ex)
		 {
			 System.out.println("Exception in insertStudentDetailsfrmExcelSheet()");
			 System.out.println("The Error Message - " + ex.getMessage());
		 }
		 finally{
			 if(oConn!=null)
			 {
				 try
				 {
					 oStmt.close();
					 pstmt1.close();
			    		 oConn.close();
				 }
				 catch(SQLException e)
				 {
				 }
			
			
			 }
		
		 }	
	    
	 }     
	 
	 public static synchronized void  updateStudentDetailsfrmExcelSheet(String studentId,String password,String studentFname,String studentMname,String studentSname,String accountStatus,String age,String experience,String education,String gender,String email,String department,String selfregistration)
	 {
      	
		 Statement  oStmt=null;
		 ResultSet  oRset;
		 String group_name="";
		 Connection oConn = null;			
		 Statement statement = null;
		 PreparedStatement pstmt1 = null;
		 try
		 {
			 oConn = ds1.getConnection();
			 oStmt = oConn.createStatement();
 				//oConn.setAutoCommit(false);
			 statement = oConn.createStatement();
		    
			    
			 pstmt1 = oConn.prepareStatement("update student_details set sname=?, "+
					 "first_name=?, middle_name=?,account_status=?,age=?,experience=?,edu_status=?,gender=?,email_id=?,department=?,strself=?  where student_id=?");
			    
			
			
			 pstmt1.setString(1,studentSname);
			 pstmt1.setString(2,studentFname);
			
			 pstmt1.setString(3,studentMname);
			 pstmt1.setString(4,accountStatus);
			 pstmt1.setString(5,age);
			 pstmt1.setString(6,experience);
			 pstmt1.setString(7,education);
			 pstmt1.setString(8,gender);
			 pstmt1.setString(9,email);
			 pstmt1.setString(10,department);
			 pstmt1.setString(11,selfregistration);
			 pstmt1.setString(12,studentId);   
			 			 
			 pstmt1.executeUpdate();
			 
			 System.out.println("Age inside DBLayer ----------------> "+age);
			 System.out.println("update student_details set sname=?, first_name=?, middle_name=?,account_status=?,age=?  where student_id=?");
			 
			 oStmt.execute("update student_creation_details set last_modification_date=sysdate() where student_id='"+studentId+"'");
			 oStmt.execute("update student_password set password='"+password+"' where student_id='"+studentId+"'");
						
						
			

		 }
		 catch(SQLException sqlexception)
		 {
			 System.out.println("Exception in updateStudentDetailsfrmExcelSheet()");
			 System.out.println("The Error Message - " + sqlexception.getMessage());
		 }
		 catch(Exception ex)
		 {
			 System.out.println("Exception in updateStudentDetailsfrmExcelSheet()");
			 System.out.println("The Error Message - " + ex.getMessage());
		 }
		 finally{
			 if(oConn!=null)
			 {
				 try
				 {
					 oStmt.close();
					 pstmt1.close();
					 oConn.close();
				 }
				 catch(SQLException e)
				 {
				 }
			
			
			 }
		
		 }	
					
	 }
	 public static synchronized void  insertTempStudentDetailsfrmExcelSheet(String stuUsrId,String password,String studentFname,String studentMname,String studentSname,String accountStatus,String age,String experience,String education,String gender,String email,String department,String selfregistration)
		 {
      	
			 Statement  oStmt=null;
			 ResultSet  oRset;
			 ResultSet  oRset1;
			 String str="";
			 String str1="";
			 Connection oConn = null;
			 PreparedStatement pstmt1 = null;
			// Statement statement = null;
			 try
			 {
				 oConn = ds1.getConnection();
				 oStmt = oConn.createStatement();
 				//oConn.setAutoCommit(false);
				 // statement = oConn.createStatement();
		    
		
				 	
			    
				 pstmt1 = oConn.prepareStatement("insert into temp_student_details (student_id,password, sname, "+
						 "first_name, middle_name,account_status,age,experience,edu_status,gender,email_id,department,strself) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			    
				 pstmt1.setString(1,stuUsrId);
				 pstmt1.setString(2,password);
				 pstmt1.setString(3,studentSname);

				 pstmt1.setString(4,studentFname);
				 pstmt1.setString(5,studentMname);
				 pstmt1.setString(6,accountStatus);
				 pstmt1.setString(7,age);
				 pstmt1.setString(8,experience);
				 pstmt1.setString(9,education);
				 pstmt1.setString(10,gender);
				 pstmt1.setString(11,email);
				 pstmt1.setString(12,department);
				 pstmt1.setString(13,selfregistration);
				 pstmt1.executeUpdate();
			
				
				

			 }
			 catch(SQLException sqlexception)
			 {
				 System.out.println("Exception in insertTempStudentDetailsfrmExcelSheet()");
				 System.out.println("The Error Message - " + sqlexception.getMessage());
			 }
			 catch(Exception ex)
			 {
				 System.out.println("Exception in insertTempStudentDetailsfrmExcelSheet()");
				 System.out.println("The Error Message - " + ex.getMessage());
			 }
			 finally{
				 if(oConn!=null)
				 {
					 try
					 {
						 oStmt.close();
						 pstmt1.close();
						 oConn.close();
					 }
					 catch(SQLException e)
					 {
					 }
			
			
				 }
		
			 }	
	
		 }			
		 
		 /*=====================================Nayna======================================*/
		 public synchronized static String getAdminstratorPreviledge(String id) {
		
			 Statement  oStmt=null;
			 ResultSet  oRset=null ;
			 //ResultSet  oRset=null ;
			 String strPreviledge = "";
			 Connection oConn = null;
			 try {
				 oConn = ds1.getConnection();
				 oStmt = oConn.createStatement();
				 oRset=oStmt.executeQuery("select count(*) from administrator_previlege_management where user_id=\'"+id+"\'");
				 int  l_i=0;
				 while(oRset.next())	{
					 l_i = oRset.getInt(1);
				 }
				 oRset.close();
				 oStmt.close();
				 if(l_i <= 0) {
					
					 return null;
				 }
				 oStmt = oConn.createStatement();
				 oRset = oStmt.executeQuery("select previledge from administrator_previlege_management "+
						 "where user_id = '"+id+"'");
				
				 strPreviledge = oRset.getString(1);
				 
			 }
			 catch (SQLException e) {
				 System.out.println(e.toString());
			 }
			 catch (Exception ex) {
				 System.out.println(ex.toString());
			 }
			 finally{
				 if(oConn!=null)
				 {
					 try
					 {
						 oStmt.close();
						 oRset.close();
						 oConn.close();
					 }
					 catch(SQLException e)
					 {
					 }
			
			
				 }
		
			 }	
			 return strPreviledge ;
		 }
		 public synchronized static Vector getManifest(String course_id) {
			 Statement  oStmt=null;
			 ResultSet  oRset =null;
			 Vector v=new Vector();
			 Connection oConn = null;
			 try {
				 oConn = ds1.getConnection();
				 oStmt = oConn.createStatement();
				 oRset=oStmt.executeQuery("select csf from csformat where unit_id='" +course_id+ "'");
				 if(oRset.next()) {
					 v.addElement(oRset.getAsciiStream(1));
				 }
				 
			 }
			 catch (SQLException e) {
				 System.out.println("Error due to SQL exception : parse()");
				 System.out.println("SQL exception : "+e.toString());
			 }
			 catch (Exception ex) {
				 System.out.println("Error due to exception : parse()");
				 ex.printStackTrace();
				 System.out.println(" printStack is :: " + ex.getMessage());
			 }
			 finally{
				 if(oConn!=null)
				 {
					 try
					 {
						 oStmt.close();
						 oRset.close();
						 oConn.close();
					 }
					 catch(SQLException e)
					 {
					 }
			
			
				 }
		
			 }
  
			 return v ;
		 }
		 public static boolean insertCourse(String table,String course_id,String content,String name,String strSize,String user_id, String strdate) {
		
			 boolean status = true ;
			 Connection oConn = null;
			 Statement stmt = null;
			 ResultSet rset=null;
			 PreparedStatement ps = null;
			 try {
				 oConn = ds1.getConnection();
				 stmt=oConn.createStatement();
				 rset = stmt.executeQuery("select unit_id from "+table+" where unit_id ='"+course_id+"'");
				 if (rset.next()) {
					 stmt.execute("delete from "+table+" where unit_id = '"+course_id+"'");
				 }	
				 String pquery = "insert into "+table+" values(? ,? ,?,?, ?, ?)";
				 ps =  oConn.prepareStatement(pquery);
				 ps.setString(1,course_id);
				 InputStream sis = new StringBufferInputStream(content);
				 ps.setString(2, content);	
				 ps.setString(3,strdate);
				 ps.setString(4,user_id);
				 ps.setString(5,name);
				 ps.setString(6,strSize);
				 ps.execute();
				
			 }
			 catch(Exception e) {
				 status = false ;
				 e.printStackTrace();
			 }
			 finally{
				 if(oConn!=null)
				 {
					 try
					 {
						 ps.close();
						 rset.close();
						 stmt.close();
						 oConn.close();
					 }
					 catch(SQLException e)
					 {
					 }
			
			
				 }
		
			 }
			 return status;
		 }
	
		 public synchronized static XMLDocument parse(String course_id,String table) {

			 BufferedReader rd = null;
			 XMLDocument theXMLDoc=null;
			 Connection oConn = null;
			 Statement oStmt = null;
			 ResultSet oRset =null;
			 try {
				 oConn = ds1.getConnection();
				 oStmt = oConn.createStatement();
				 oRset=oStmt.executeQuery("select * from "+table+" where unit_id='" +course_id+ "'");
				 if(oRset.next()) {
					 InputStream l_lobinput  = oRset.getAsciiStream(2);
					 rd =new BufferedReader(new InputStreamReader(l_lobinput));
					 DOMParser theParser=new DOMParser();
					 theParser.setValidationMode(false);
					 theParser.parse(rd);
					 theXMLDoc = theParser.getDocument();
					 rd.close();
				 }
				 
			 }
			 catch (SQLException e) {
				 System.out.println("Error due to SQL exception : parse()");
				 System.out.println("SQL exception : "+e.toString());
			 }
			 catch (Exception ex) {
				 System.out.println("Error due to exception : parse()");
				 ex.printStackTrace();
				 System.out.println(" printStack is :: " + ex.getMessage());
			 }
			 finally{
				 if(oConn!=null)
				 {
					 try
					 {
						 oRset.close();
						 oStmt.close();
						 oConn.close();
					 }
					 catch(SQLException e)
					 {
					 }
			
			
				 }
		
			 }
   
			 return theXMLDoc ;
		 }
		 
		 public synchronized static void inserttemp_mcontentobj_resource(String resource_id, String element_type, String element_value, String content_type, String adl_type, String file_last_uploaded, String sizefile) {
			 Statement  oStmt=null;
			 Connection oConn = null;
			 if(file_last_uploaded==null)
				 file_last_uploaded="";
// 				 file_last_uploaded="0000-00-00";
			 if(sizefile==null)
				 sizefile="";
	
			 try {
				 oConn = ds1.getConnection();
				 oConn.setAutoCommit(false);
				 oStmt = oConn.createStatement();
// 				 System.out.println("insert into temp_mcontentobj_resource (resource_id,element_type,element_value,content_type,adl_type,file_last_uploaded,sizefile) values('"+resource_id+"','"+element_type+"','"+element_value+"','"+content_type+"','"+adl_type+"','"+file_last_uploaded+"','"+sizefile+"')");
				 oStmt.execute("insert into temp_mcontentobj_resource (resource_id,element_type,element_value,content_type,adl_type,file_last_uploaded,sizefile) values('"+resource_id+"','"+element_type+"','"+element_value+"','"+content_type+"','"+adl_type+"','"+file_last_uploaded+"','"+sizefile+"')");
			
				 oConn.commit();
				 oConn.setAutoCommit(true);
				 

			 }
			 catch (SQLException e) {
				 System.out.println("......................................BATCH USER UPLOAD................."+e.getMessage());
			 }
			 catch (Exception ex) {
			
			 }
			 finally{
				 if(oConn!=null)
				 {
					 try
					 {
						 oStmt.close();
						 oConn.close();
					 }
					 catch(SQLException e)
					 {
					 }
			
			
				 }
		
			 }
		
		 }
		 public synchronized static void deletetemp_mcontentobj_resource() {
			 Connection oConn = null;
			 Statement statement = null;
			 try {
				 oConn = ds1.getConnection();
				 statement = oConn.createStatement();
				 boolean flag = statement.execute("delete from temp_mcontentobj_resource");
				 statement.close();
				 oConn.close();

			 }
			 catch (SQLException e) {
				 System.out.println("......................................BATCH USER UPLOAD................."+e.getMessage());
			 }
			 catch (Exception ex) {
			
			 }
			 finally{
				 if(oConn!=null)
				 {
					 try
					 {
						 statement.close();
						 oConn.close();
					 }
					 catch(SQLException e)
					 {
					 }
			
			
				 }
		
			 }
		
		 }
		 
		/* public synchronized static void inserttemp_mcontentobj_organization(String item,String item_title,String content_object,String file_last_uploaded,String sizeof_file) {
			 Statement  oStmt=null;
			 Connection oConn = null;
			 if(file_last_uploaded==null)
				 file_last_uploaded="";
// 				 file_last_uploaded="0000-00-00";
			 if(sizeof_file==null)
				 sizeof_file="";
	
			 try {
				 oConn = ds1.getConnection();
				 oConn.setAutoCommit(false);
				 oStmt = oConn.createStatement();
				 System.out.println("insert into temp_mcontentobj_organization (item,item_title,content_object,file_last_uploaded,sizeof_file) values('"+item+"','"+item_title+"','"+content_object+"','"+file_last_uploaded+"','"+sizeof_file+"')");
				 oStmt.execute("insert into temp_mcontentobj_organization (item,item_title,content_object,file_last_uploaded,sizeof_file) values('"+item+"','"+item_title+"','"+content_object+"','"+file_last_uploaded+"','"+sizeof_file+"')");
			
				 oConn.commit();
				 oConn.setAutoCommit(true);
				

			 }
			 catch (SQLException e) {
				 System.out.println("......................................BATCH USER UPLOAD................."+e.getMessage());
			 }
			 catch (Exception ex) {
			
			 }
			 finally{
				 if(oConn!=null)
				 {
					 try
					 {
						 oStmt.close();
						 oConn.close();
					 }
					 catch(SQLException e)
					 {
					 }
			
			
				 }
		
			 }
		
}*/
		 
		 public synchronized static void inserttemp_mcontentobj_organization(String item,String item_title,String content_object,String file_last_uploaded,String sizeof_file,int sequence) {
			 Statement  oStmt=null;
			 Connection oConn = null;
			 if(file_last_uploaded==null)
				 file_last_uploaded="";
// 				 file_last_uploaded="0000-00-00";
			 if(sizeof_file==null)
				 sizeof_file="";
	
			 try {
				 oConn = ds1.getConnection();
				 oConn.setAutoCommit(false);
				 oStmt = oConn.createStatement();
				 System.out.println("insert into temp_mcontentobj_organization (item,item_title,content_object,file_last_uploaded,sizeof_file,sequence) values('"+item+"','"+item_title+"','"+content_object+"','"+file_last_uploaded+"','"+sizeof_file+"','"+sequence+"')");
				 oStmt.execute("insert into temp_mcontentobj_organization (item,item_title,content_object,file_last_uploaded,sizeof_file,sequence) values('"+item+"','"+item_title+"','"+content_object+"','"+file_last_uploaded+"','"+sizeof_file+"','"+sequence+"')");
			
				 oConn.commit();
				 oConn.setAutoCommit(true);
				

			 }
			 catch (SQLException e) {
				 System.out.println("......................................BATCH USER UPLOAD................."+e.getMessage());
			 }
			 catch (Exception ex) {
			
			 }
			 finally{
				 if(oConn!=null)
				 {
					 try
					 {
						 oStmt.close();
						 oConn.close();
					 }
					 catch(SQLException e)
					 {
					 }
			
			
				 }
		
			 }
		
		 }
		 public synchronized static void deletetemp_mcontentobj_organization() {
			 Connection oConn = null;
			 Statement statement = null;
			 try {
				 oConn = ds1.getConnection();
				 statement = oConn.createStatement();
				 boolean flag = statement.execute("delete from temp_mcontentobj_organization");
				 

			 }
			 catch (SQLException e) {
				 System.out.println("......................................BATCH USER UPLOAD................."+e.getMessage());
			 }
			 catch (Exception ex) {
			
			 }
			 finally{
				 if(oConn!=null)
				 {
					 try
					 {
						 statement.close();
						 oConn.close();
					 }
					 catch(SQLException e)
					 {
					 }
			
			
				 }
		
			 }
		
		 }
	public synchronized static void addQualifierDetails(String a_strLQualifier, String strCreatedBy) {
		Connection oConn = null;
		Statement  oStmt=null;
		 try	{
			oConn = ds1.getConnection();
			oStmt=oConn.createStatement();
			oStmt.execute("insert into  learning_qualifier(lqualifier,created_date,created_by,last_modification_date) "+
						"values ('"+a_strLQualifier+"',sysdate(),'"+strCreatedBy+"',sysdate())");
			
		}
		catch (SQLException e) {
// 				errorMessage(e.getErrorCode());
		}
		catch (Exception ex) {
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
	}
	public static void insertContent(String table, String strFile, String unitId, String uploadBy) {
		
		Connection oConn = null;
		Statement statement = null;
		Statement stmt = null;
		Statement stmt2 = null;
		ResultSet rset=null;
		try {
			oConn = ds1.getConnection();
			stmt=oConn.createStatement();
			statement=oConn.createStatement();
			stmt2=oConn.createStatement();
			
			rset = stmt.executeQuery("select * from "+table+" where unit_id = '"+unitId+"' and file_name = '"+strFile+"'");
			if (rset.next()) {
				statement.execute("delete from "+table+" where unit_id = '"+unitId+"' and file_name = '"+strFile+"'");
			}
			
			stmt2.executeUpdate("insert into "+table+" values('"+unitId+"','"+strFile+"',sysdate(),'"+uploadBy+"')");
			
			
			//stmt=oConn.createStatement();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					rset.close();
					stmt.close();
					statement.close();
					stmt2.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
		
	}
	public static void deleteContent(String table, String strFilename, String unitId) {
		
		//boolean status = true;
		Connection oConn = null;
		Statement stmt = null;
		ResultSet rset=null;
		try {
			oConn = ds1.getConnection();
			stmt=oConn.createStatement();
			rset = stmt.executeQuery("select * from "+table+" where unit_id = '"+unitId+"' and file_name = '"+strFilename+"'");
			if (rset.next()) {
				stmt.execute("delete from "+table+" where unit_id = '"+unitId+"' and file_name = '"+strFilename+"'");
			}
			
		}
		catch(Exception e) {
			//status = false ;
			e.printStackTrace();
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					rset.close();
					stmt.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
		//return(status);
	}
	public static synchronized String checkunitRegistration(String str1)
	{
    	
		String as=null;
		Connection oConn = null;
		ResultSet resultset=null;
		ResultSet resultset1=null;
		Statement statement = null;
		try
		{
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			for(resultset = statement.executeQuery("select a.student_id from unit_student_association a  where a.unit_id='"+str1+"' "); resultset.next(); )
			{
				as = resultset.getString(1);
			}
			if(as==null){
				for(resultset1 = statement.executeQuery("select a.group_id from unit_group_association a  where a.unit_id='"+str1+"' "); resultset1.next(); )
				{
					as = resultset1.getString(1);
              
				}
            	
			}
			
		}
		catch(SQLException sqlexception)
		{
			System.out.println("Exception in DataBaseLayer.getStudentRegistration()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
		}
		catch(Exception exception)
		{
			System.out.println("Exception in DataBaseLayer.getStudentRegistration()");
			System.out.println("The Error Message - " + exception.getMessage());
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					statement.close();
					oConn.close();
					resultset1.close();
					resultset1.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
     
		return as;
	}
	
	
	public synchronized static void inserttemp_checkunit_consistency(String filename,String resourceid) {
		Statement  oStmt=null;
		Connection oConn = null;
		try {
			oConn = ds1.getConnection();
			oConn.setAutoCommit(false);
			oStmt = oConn.createStatement();
			System.out.println("insert into temp_checkunit_consistency (filename,resource_id) values('"+filename+"','"+resourceid+"')");
			oStmt.execute("insert into temp_checkunit_consistency (filename,resource_id) values('"+filename+"','"+resourceid+"')");
			
			oConn.commit();
			oConn.setAutoCommit(true);
			

		}
		catch (SQLException e) {
			System.out.println("......................................BATCH USER UPLOAD................."+e.getMessage());
		}
		catch (Exception ex) {
			
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
		
	}
	public synchronized static void deletetemp_checkunit_consistency() {
		Connection oConn = null; 
		Statement statement = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			boolean flag = statement.execute("delete from temp_checkunit_consistency");
			

		}
		catch (SQLException e) {
			System.out.println("......................................BATCH USER UPLOAD................."+e.getMessage());
		}
		catch (Exception ex) {
			
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					statement.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
		
	}
	
	
	/*============================================================================================*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	////////////////////////////jayanta////////////////////////////////
	public static Vector getEntry(String c_id)
	{
		
		Vector vCourseList = new Vector(7,7);
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset = null;
		try
		{
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			System.out.println("----getEntry(String c_id)--------select entry_name,cat_description,parent_name,catalog_name,cat_id,entryid,entity_type from catalog_entry_details where cat_id ='"+c_id+"' and parent_name='NULL'");
			oRset = statement.executeQuery("select entry_name,cat_description,parent_name,catalog_name,cat_id,entryid,entity_type from catalog_entry_details where cat_id ='"+c_id+"' and parent_name='NULL'");
			//ResultSet oRset = statement.executeQuery("select entry_name,cat_description,parent_name,catalog_name,cat_id,entryid,entity_type from catalog_entry_details where cat_id ='"+c_id+"' and parent_name='NULL'");
			//System.out.println("oRset.size()"+oRset);
			while(oRset.next()){
				String a[] = new String[7];
				System.out.println("---------111-----------------");
				a[0] = oRset.getString(1);//entry_name
				a[1] = oRset.getString(2);// cat_description
				a[2] = oRset.getString(3);//parent_name
				a[3] = oRset.getString(4);//catalog_name
				a[4] = oRset.getString(5);//cat_id
				a[5] = oRset.getString(6);//entryid
				a[6] = oRset.getString(7);//entity_type
            	        	
				vCourseList.addElement(a);  
            	
			}
			//oConn.close();
		}
        
		catch(SQLException sqlexception)
		{           
			System.out.println("The Error Message - " + sqlexception.getMessage());
			System.out.println("The Error Code    - " + sqlexception.getErrorCode());
			while((sqlexception = sqlexception.getNextException()) != null) 
			{
				System.out.println("The Error message - " + sqlexception.getMessage());
				System.out.println("The Error code is - " + sqlexception.getErrorCode());
			}
		}
		catch(Exception exception)
		{            
			exception.printStackTrace();
			System.out.println("The message - " + exception.toString());
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					statement.close();
					oConn.close();
					oRset.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
		System.out.println("from the database layer at end "+vCourseList.size());
		return vCourseList;
	}	
	
	public static Vector getEntry(String c_id,String p_id)
	{
		
		Vector vCourseList = new Vector(7,7);
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset = null;
		try
		{
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			System.out.println("------------select entry_name,cat_description,parent_name,catalog_name,cat_id,entryid,entity_type from catalog_entry_details where cat_id ='"+c_id+"' and parent_name='"+p_id+"' ");
			oRset = statement.executeQuery("select entry_name,cat_description,parent_name,catalog_name,cat_id,entryid,entity_type from catalog_entry_details where cat_id ='"+c_id+"' and parent_name='"+p_id+"' ");
			//ResultSet oRset = statement.executeQuery("select entry_name,cat_description,parent_name,catalog_name,cat_id,entryid,entity_type from catalog_entry_details where cat_id ='"+c_id+"' and parent_name='"+p_id+"' and entity_type = 'null'");
			while(oRset.next()){
				String str[] = new String[7];
				str[0] = oRset.getString(1);//entry_name
				str[1] = oRset.getString(2);//cat_description
				str[2] = oRset.getString(3);//parent_name
				str[3] = oRset.getString(4);//catalog_name
				str[4] = oRset.getString(5);//cat_id 
				str[5] = oRset.getString(6);//entryid
				str[6] = oRset.getString(7);  //entity_type   	        
				vCourseList.addElement(str);            	
			}
			//oConn.close();
		}
		catch(SQLException sqlexception)
		{           
			System.out.println("The Error Message - " + sqlexception.getMessage());
			System.out.println("The Error Code    - " + sqlexception.getErrorCode());
			while((sqlexception = sqlexception.getNextException()) != null) 
			{
				System.out.println("The Error message - " + sqlexception.getMessage());
				System.out.println("The Error code is - " + sqlexception.getErrorCode());
			}
		}
		catch(Exception exception)
		{            
			exception.printStackTrace();
			System.out.println("The message - " + exception.toString());
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					statement.close();
					oConn.close();
					oRset.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
		return vCourseList;
	}	
		 
		 
// 		 public static synchronized Vector getcatinfo(String entry_id)
// 		 {
//     	//DBConnectionManager connMgr = DBConnectionManager.getInstance();
//         //Connection oConn = connMgr.getConnection("mysql");
// 			 Vector vNoteid=new Vector(2, 2);
// 			 Statement oStmt;
// 			 try{
// 				 Connection oConn = ds1.getConnection();
// 				 oStmt = oConn.createStatement();
// 				 for(ResultSet oRset = oStmt.executeQuery("select cat_id,catalog_name,cat_description	 from catalog_details where cat_id='"+cat_id+"'");oRset.next();)
// 				 {
// 					 String catid =  oRset.getString(1);
// 					 String catname = oRset.getString(2);
// 					 String cat_description=oRset.getString(3);
// 					 vNoteid.addElement(catid);
// 					 vNoteid.addElement(catname);
// 					 vNoteid.addElement(cat_description);
// 				 }
// 				 oStmt.close();	
// 				 oConn.close();
// 			 }
// 			 catch(SQLException ex)
// 			 {
	//           	
// 				 ex.printStackTrace();
// 				 return vNoteid;
// 			 }
// 			 return vNoteid;
// 		 }  	
		 
		 
		 
	public static Vector getEntry2(String entry_id)
	{
		//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
		Vector vCourseList = new Vector(7,7);
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset=null;
		try
		{
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
//            System.out.println("select   topic_id,topic_name, description,course_id  from syllabus_details where course_id ='"+c_id+"' and parent_id='"+p_id+"'");
			System.out.println("----------------select entry_name,cat_description,parent_name,catalog_name,cat_id,entryid,entity_type from catalog_entry_details where entryid ='"+entry_id+"' ");
			oRset = statement.executeQuery("select entry_name,cat_description,parent_name,catalog_name,cat_id,entryid,entity_type from catalog_entry_details where entryid ='"+entry_id+"'  ");
				 //ResultSet oRset = statement.executeQuery("select entry_name,cat_description,parent_name,catalog_name,cat_id,entryid,entity_type from catalog_entry_details where entryid ='"+entry_id+"'  and entity_type = 'null'");
			while(oRset.next()){
				String str[] = new String[7];
				str[0] = oRset.getString(1);//entry_name
				str[1] = oRset.getString(2);
				String parent_name = oRset.getString(3);
				if((parent_name==null) || (parent_name.equals("null")) || (parent_name.equals("NULL")))
					parent_name="";
				str[2] = parent_name;
				str[3] = oRset.getString(4);
				str[4] = oRset.getString(5); 
				str[5] = oRset.getString(6);
				str[6] = oRset.getString(7);     	        
				vCourseList.addElement(str);            	
			}
			//oConn.close();
		}
		catch(SQLException sqlexception)
		{           
			System.out.println("The Error Message - " + sqlexception.getMessage());
			System.out.println("The Error Code    - " + sqlexception.getErrorCode());
			while((sqlexception = sqlexception.getNextException()) != null) 
			{
				System.out.println("The Error message - " + sqlexception.getMessage());
				System.out.println("The Error code is - " + sqlexception.getErrorCode());
			}
		}
		catch(Exception exception)
		{            
			exception.printStackTrace();
			System.out.println("The message - " + exception.toString());
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					statement.close();
					oConn.close();
					oRset.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
      //  //connMgr.freeConnection("mysql", oConn);
		return vCourseList;
	}
		 	
		 
		 
		 
	public synchronized static Vector getCourseDetailsList1() {
		
		Statement  oStmt=null;
		ResultSet  oRset =null ;
		Vector vCourseList = null;
		Connection oConn = null;
		Statement oStmt1 = null;
		ResultSet oRset1=null;
    	//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
    	
		try	{ 
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oRset=oStmt.executeQuery("select count(*)  from unit_details, unit_creation_details where unit_details.unit_id=unit_creation_details.unit_id");
			int  l_i=0;
			while(oRset.next())	{
				l_i = oRset.getInt(1);
			}
			oRset.close();
			oStmt.close();
			if(l_i <= 0)
				return null;
			vCourseList = new Vector();
			oStmt = oConn.createStatement();
			oRset=oStmt.executeQuery("select a.unit_id, a.unit_name, date_format(b.date_created,\"%M %e, %Y %H:%i\"), "+
					"b.created_By, b.modified_by, date_format(b.date_modified,\"%M %e, %Y %H:%i\"), b.status, "+
					"b.email_id, b.forum_name, b.chat_name, b.calender_name, b.controll"+
					"  from unit_details a, unit_creation_details b where a.unit_id=b.unit_id");
			while(oRset.next()) {
				Vector vCourse = new Vector();
				vCourse.addElement(oRset.getString(1));
				vCourse.addElement(oRset.getString(2));
				vCourse.addElement(oRset.getString(3));
				vCourse.addElement(oRset.getString(4));
				vCourse.addElement(oRset.getString(5));
				vCourse.addElement(oRset.getString(6));
				vCourse.addElement(oRset.getString(7));
				oStmt1 = oConn.createStatement();
				oRset1 = oStmt1.executeQuery("select count(*)  from unit_student_association where unit_id = '"+oRset.getString(1)+"'");

				oRset1.next();
				vCourse.addElement(oRset1.getString(1));
				vCourse.addElement(oRset.getString(8));
				vCourse.addElement(oRset.getString(9));
				vCourse.addElement(oRset.getString(10));
				vCourse.addElement(oRset.getString(11));
				vCourse.addElement(oRset.getString(12));
				vCourseList.addElement(vCourse);
				
				
			}
			
		}
		catch (SQLException e) {
			System.out.println("getCourseDetailsList1: error due to SQL exception");
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
		}
		catch (Exception ex) {
			System.out.println("getCourseDetailsList1: error due to SQL exception");
			ex.printStackTrace();
			System.out.println(" printStack is :: " + ex.getMessage());
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oRset.close();
					oStmt.close();
					oConn.close();
					oRset1.close();
					oStmt1.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
    	//connMgr.freeConnection("mysql", oConn);
		return vCourseList;
	}
		 
		 
		 
		 
	public static  Vector getForumName()
	{
        
        //DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
		Vector vCourseList = new Vector(2,2);
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset=null;
		try
		{

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			oRset = statement.executeQuery("select forum_id, forum_name from forum ");
			while(oRset.next()){
				String str[] = new String[2];
				str[0] = oRset.getString(1);
				str[1] = oRset.getString(2);
				vCourseList.addElement(str);
			}
            	//vCourseList.addElement(str);
            	
			
		}
		catch(SQLException sqlexception)
		{
			sqlexception.printStackTrace();
		}
        
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oConn.close();
					statement.close();
					oRset.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
      //  //connMgr.freeConnection("mysql", oConn);
		return vCourseList;
	}	
		 
		 
	public synchronized static Vector selectSynchronousEnvironmentForRegis() {
		
		Statement oStmt=null;
		ResultSet  oRset =null ;
		Vector SCList = new Vector();
    	
		/* For Checking valid Registration */
    							
		Calendar calendarnow = new GregorianCalendar();
		java.util.Date trialTime1 = new java.util.Date();
		calendarnow.setTime(trialTime1);
    	
		String strTime1 = calendarnow.get(Calendar.HOUR_OF_DAY)+":"+ calendarnow.get(Calendar.MINUTE)+":"+ calendarnow.get(Calendar.SECOND);
		String strDate1 = calendarnow.get(Calendar.DATE)+"-"+ (calendarnow.get(Calendar.MONTH)+1)+"-"+ calendarnow.get(Calendar.YEAR);
		
		String strCurrent=strDate1+" "+strTime1;
		String myFormatString = "dd-MM-yyyy HH:mm:ss"; 
		SimpleDateFormat df = new SimpleDateFormat(myFormatString);
		Connection oConn = null;
		//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
		
		try	{
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oRset=oStmt.executeQuery("select a.id, a.name, a.start, a.end,a.type from synchronous_collaboration a");
			
			while(oRset.next()) {
				Vector vSC = new Vector();
				
				String strStart = oRset.getString(3);
				String strEnd = oRset.getString(4);
				String strType = oRset.getString(5);
				java.util.Date start = null;
				java.util.Date end = null;
				if(!strStart.equals("")) {
					start = df.parse(strStart);
				}
				if(!strEnd.equals("")) {
					end = df.parse(strEnd);	
				}
	   			
				java.util.Date current = df.parse(strCurrent);					
				
				if(strType.equals("R"))
				{
					if (strStart.equals("") || strEnd.equals("")) {
						vSC.addElement(oRset.getString(1));
						vSC.addElement(oRset.getString(2));
						SCList.addElement(vSC);
					}
					else {
						if (current.after(start) && current.before(end))
						{			
							vSC.addElement(oRset.getString(1));
							vSC.addElement(oRset.getString(2));
							SCList.addElement(vSC);
						}
					}
				}
				else
				{
					if (strStart.equals("") || strEnd.equals("")) {
						vSC.addElement(oRset.getString(1));
						vSC.addElement(oRset.getString(2));
						SCList.addElement(vSC);
					}
					else {
						if (current.before(start))
						{
							vSC.addElement(oRset.getString(1));
							vSC.addElement(oRset.getString(2));	
							SCList.addElement(vSC);
						}
						else if(current.after(start) && current.before(end))
						{
							vSC.addElement(oRset.getString(1));
							vSC.addElement(oRset.getString(2));
							SCList.addElement(vSC);
						}
					}
				}
				//SCList.addElement(vSC);
			}
					
			
		}
		catch (SQLException e) {
      		
			System.err.print("SQLException: ");
			System.err.println(e.getMessage());
		}
		catch (Exception ex) {
      		
			System.err.print("SQLException: ");
			System.err.println(ex.getMessage());
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
    	//connMgr.freeConnection("mysql", oConn);
		return SCList;
	}
		 
	public static  Vector getCourseFullList()
	{
        
        //DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
		Vector vCourseList = new Vector(2,2);
		Connection oConn = null;
		ResultSet oRset =null;
		Statement statement = null;
		try
		{
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			oRset = statement.executeQuery("select course_id, course_name from course_definition ");
			while(oRset.next()){
				String str[] = new String[2];
				str[0] = oRset.getString(1);
				str[1] = oRset.getString(2);
				vCourseList.addElement(str);
			}
			
		}
		catch(SQLException sqlexception)
		{
			sqlexception.printStackTrace();
		}
        
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					statement.close();
					oConn.close();
					oRset.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
      //  //connMgr.freeConnection("mysql", oConn);
		return vCourseList;
	}		

	/*-------------31122008------------------------*/
	public synchronized static void addRptdesign(String report_id,String report_file_size, String rptdesign_file_name) {
		
		Statement  oStmt=null;
		Statement  oStmt1;	
		Connection oConn = null;
		try{
			oConn = ds1.getConnection();
			oStmt=oConn.createStatement();
			oStmt1=oConn.createStatement();
			
			
			PreparedStatement pstmt1 = oConn.prepareStatement("update report_rptdetails set rptdesign_file_name=?, "+
					"report_file_size=? where report_id=?");
			    
			
			
			pstmt1.setString(1,rptdesign_file_name);
			pstmt1.setString(2,report_file_size);
			
			pstmt1.setString(3,report_id);
			                          
			pstmt1.executeUpdate();
			
			
    		
			 	   
		}catch(SQLException e){
    	   	
			e.printStackTrace();
			try{
			 //oConn.rollback();
			}catch(Exception re){
    	   	
				re.printStackTrace();
			}
    	   	
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oConn.close();   
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
	 	
	}
	
	/*
	public static Vector getFileName(String module)
	{
		
       // String str[]=new String[2];
	Vector vSrcFile = new Vector(2,2);
	Statement  oStmt=null;
	try {
	Connection oConn = ds1.getConnection();
	oStmt = oConn.createStatement();
	for(ResultSet oRset = oStmt.executeQuery("select a.rptdesign_file_name,a.report_file from report_rptdetails a where a.report_module='"+module+"'");oRset.next();)
	{
	Vector cols = new Vector();
	cols.addElement(oRset.getString(1));
	cols.addElement(oRset.getAsciiStream(2));
			//cols.addElement(oRset.getAsciiStream(3));
	vSrcFile.addElement(cols);
	            
}
	oStmt.close();
}
	catch (SQLException sqlexception) {
	System.out.println("Exception in BuildDatabaseLayer.getClassDetails()");
	System.out.println("The Error Message - " + sqlexception.getMessage());
}
	catch (Exception ex) {
	System.out.println("Exception in BuildDatabaseLayer.getClassDetails()");
	System.out.println("The Error Message - " + ex.getMessage());
}
		//connMgr.freeConnection("mysql", oConn);
	return vSrcFile;		
}

	public static synchronized Vector getreportid(String module)
	{
      
	Vector vector = new Vector();
	try
	{
	Connection oConn = ds1.getConnection();
	Statement statement = oConn.createStatement();
            //String as[];
	for(ResultSet oRset = statement.executeQuery("select * from report_rptdetails where report_module='"+module+"'");oRset.next();)
	{
	String as[] = new String[7];
	as[0]=oRset.getString(1);
             // as.addElement(oRset.getString(2));
	as[1]=oRset.getString(2);
	as[2]=oRset.getString(3);
	as[3]=oRset.getString(4);
	as[4]=oRset.getString(5);
	as[5]=oRset.getString(6);
	as[6]=oRset.getString(7);
              
	vector.addElement(as);
}

	statement.close();
}
	catch(SQLException sqlexception)
	{
	System.out.println("Exception in ReportEngineDatabaseLayer.getreportid()");
	System.out.println("The Error Message - " + sqlexception.getMessage());
}
	catch(Exception exception)
	{
	System.out.println("Exception in ReportEngineDatabaseLayer.getreportid()");
	System.out.println("The Error Message - " + exception.getMessage());
}
		//connMgr.freeConnection("mysql", oConn);
	return vector;
}    
	*/
	
	
	
	public synchronized static void addSubEntryCatalog(String cat_id,String New_Entri_Name_text,String Entri_Name_text,String New_Entri_Desc_text) {
		Statement  oStmt=null;
		Statement  oStmt1=null;
		
		Connection oConn = null;
		//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql"); 
		
		try {	
			oConn = ds1.getConnection();
			oConn.setAutoCommit(false);
			oStmt = oConn.createStatement();
			
			String cat_name="";
			if(cat_id!=null)
			{
				
						
				System.out.println("-------------------insert into catalog_entry_details (cat_id,catalog_name,entry_name,parent_name,cat_description) values('"+cat_id+"','"+cat_name+"','"+New_Entri_Name_text+"','"+Entri_Name_text+"','"+New_Entri_Desc_text+"')");		
				oStmt.execute("insert into catalog_entry_details (cat_id,catalog_name,entry_name,parent_name,cat_description) values('"+cat_id+"','"+cat_name+"','"+New_Entri_Name_text+"','"+Entri_Name_text+"','"+New_Entri_Desc_text+"')");
			}
			oConn.commit();
			oConn.setAutoCommit(true);
			

		}
		catch (SQLException e) {
			e.printStackTrace();
	   
		}
		catch (Exception ex) {
			ex.printStackTrace();
	    
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					if(oStmt!=null)
					oStmt.close();
					
					
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
		//connMgr.freeConnection("mysql", oConn);

	}

	public synchronized static void addEntryCatalog(String cat_id,String Entri_Name_text,String parent_name,String Entri_Desc_text){
		Statement  oStmt=null;
		Statement  oStmt1=null;
		ResultSet oRset=null;
		Connection oConn = null;
		//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql"); 
		
		try {	
			oConn = ds1.getConnection();
			oConn.setAutoCommit(false);
			oStmt = oConn.createStatement();
			oStmt1 = oConn.createStatement();
			String cat_name="";			
			if(cat_id!=null)
			{
				System.out.println("--------select catalog_name from catalog_details where cat_id='"+cat_id+"'")	;
				oRset=oStmt1.executeQuery("select catalog_name from catalog_details where cat_id='"+cat_id+"'")	;
				if(oRset.next())
				{
					cat_name=oRset.getString(1);
				}
				System.out.println("--------cat_name------"+cat_name);
				System.out.println("--------insert into catalog_entry_details (cat_id,catalog_name,entry_name,parent_name,cat_description) values('"+cat_id+"','"+cat_name+"','"+Entri_Name_text+"','"+parent_name+"','"+Entri_Desc_text+"')");				
				oStmt.execute("insert into catalog_entry_details (cat_id,catalog_name,entry_name,parent_name,cat_description) values('"+cat_id+"','"+cat_name+"','"+Entri_Name_text+"','"+parent_name+"','"+Entri_Desc_text+"')");
			}
			oConn.commit();
			oConn.setAutoCommit(true);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
	  
		}
		catch (Exception ex) {
			ex.printStackTrace();
	  
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oStmt1.close();
					oRset.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
		//connMgr.freeConnection("mysql", oConn);

	} 
	
	/*-------------end 31122008------------------------*/
	
	
	/*------------------080109-----------------------*/
	
	public synchronized static void  AssociateEntries( String cat_id,String Entri_Name_text,String course_id,String New_Entri_Desc_text,String user_id,String entryid )
	{
		Statement  oStmt=null;
		String course_name="";
		Statement oStmt2 = null;
		Statement oStmt3 = null;
		Statement oStmt4 = null;
		String cat_name="";
		ResultSet oRset2 =null;
		ResultSet oRset3 =null;
		ResultSet oRset4 =null;
	//    boolean count = false;
	    
	//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
		Connection oConn =null;
		try {
			oConn = ds1.getConnection();
			//oConn.setAutoCommit(false);
			oStmt = oConn.createStatement();
			oStmt2 = oConn.createStatement();
			oStmt3 = oConn.createStatement();
			oStmt4 = oConn.createStatement();
			
			log.debug("-------11-----------select  course_name from course_definition  where  course_id='"+course_id+"'");
			oRset2 = oStmt2.executeQuery("select  course_name from course_definition  where  course_id='"+course_id+"'");
			while(oRset2.next())
			{
				course_name = oRset2.getString(1);				
			}
			
			log.debug("--------22------select catalog_name from catalog_details where cat_id='"+cat_id+"'")	;
			oRset3=oStmt3.executeQuery("select catalog_name from catalog_details where cat_id='"+cat_id+"'")	;
			if(oRset3.next())
			{
				cat_name=oRset3.getString(1);
			}
			log.debug("--------33------select entryid from catalog_entry_details  where cat_id='"+cat_id+"' and entry_name='"+Entri_Name_text+"'")	;
			oRset4=oStmt4.executeQuery("select entryid from catalog_entry_details  where cat_id='"+cat_id+"' and entry_name='"+Entri_Name_text+"'")	;
			if(oRset4.next())
			{
				//entryid=oRset4.getString(1);
			}
			
			log.debug("------44-----insert into catalog_entry_entity(catalog_name,	entry_name,	entity_type,	entity_name,	entity_description,	created_by,created_on,entryid,cat_id) 		values ('"+cat_name+"','"+Entri_Name_text+"','course','"+course_id+"','"+New_Entri_Desc_text+"','"+user_id+"',sysdate(),'"+entryid+"','"+cat_id+"')");
			oStmt.execute("insert into catalog_entry_entity(catalog_name,entry_name,entity_type,entity_name,entity_description,created_by,created_on,entryid,cat_id) values ('"+cat_name+"','"+Entri_Name_text+"','course','"+course_id+"','"+New_Entri_Desc_text+"','"+user_id+"',sysdate(),'"+entryid+"','"+cat_id+"')");
			
			
			
//			oConn.commit();
			//oConn.setAutoCommit(true);
			oConn.close();
	//        count = false;
		}
		catch (SQLException e) {
			try{
				e.printStackTrace();
				oConn.rollback();
			}
			catch(SQLException e1){}
	    
		}
		catch (Exception ex) {
			try{
				ex.printStackTrace();
				oConn.rollback();
			}
			catch(SQLException ex1){}
	     
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oStmt2.close();
					oStmt3.close();
					oStmt4.close();
					oRset2.close();
					oRset3.close();  
					oRset4.close();  
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
  //  //connMgr.freeConnection("mysql", oConn);
   //return count ;
	}
	
	
	
	
	
	/*---------------end ---080109------------------------*/
	/*--------------- ---090109------------------------*/	
	
	public synchronized static void addEntryCatalog(String Entri_id){
		Statement  oStmt=null;
		Statement  oStmt1=null;
		ResultSet oRset=null;
		Connection oConn = null;
		Statement oStmt2 = null;
		//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql"); 
		
		try {	
			oConn = ds1.getConnection();
			oConn.setAutoCommit(false);
			oStmt = oConn.createStatement();
			oStmt1 = oConn.createStatement();
			oStmt2 = oConn.createStatement();
			String cat_id="";
			String course_id="";			
			if(Entri_id!=null)
			{
				
				
				System.out.println("--------************---------stsrt------------**************------------------------------");
				System.out.println("-------------delete from catalog_temp");
				oStmt2.execute("delete from catalog_temp");
				System.out.println("--------select cat_id,entity_name from catalog_entry_entity where entryid='"+Entri_id+"'")	;
				oRset=oStmt1.executeQuery("select cat_id,entity_name from catalog_entry_entity where entryid='"+Entri_id+"'")	;
				if(oRset.next())
				{
					cat_id=oRset.getString(1);
					course_id=oRset.getString(2);
				}
				System.out.println("--------cat_id------"+cat_id);
				System.out.println("--------course_id------"+course_id);
				if(course_id!=null && cat_id!=null)
				{
				
				
					System.out.println("--------insert into catalog_temp (cat_id,entry_id,course_id) values('"+cat_id+"','"+Entri_id+"','"+course_id+"')");				
					oStmt.execute("insert into catalog_temp (cat_id,entry_id,course_id) values('"+cat_id+"','"+Entri_id+"','"+course_id+"')");
					System.out.println("--------************---------end------------**************------------------------------");
				}
			}
			oConn.commit();
			oConn.setAutoCommit(true);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			try{
				oConn.rollback();
			}
			catch(SQLException e1){}
	  
		}
		catch (Exception ex) {
			ex.printStackTrace();
			try{
				oConn.rollback();
			}
			catch(SQLException ex1){}
	  
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oStmt1.close();
					oStmt2.close();
					oRset.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
		//connMgr.freeConnection("mysql", oConn);

	} 
	
	/*---------------end ---090109------------------------*/
	
	
	
	
	
	
	public static synchronized String  changePassword(String OldPassworddata,String NewPassworddata,String RetypePassworddata,String user_id)
	{
      	
		Statement  oStmt=null;
		Statement  oStmt2=null;
		Connection oConn = null;
		String oldpass="";
		ResultSet rs=null;
		String val="";
		try
		{
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt2 = oConn.createStatement();
						    
			
			rs=oStmt2.executeQuery("select password from student_password where student_id='"+user_id+"'");
			while(rs.next())
			{
				oldpass=rs.getString(1);
			}
			System.out.println("----------oldpass-------------"+oldpass);
			if(OldPassworddata.equals(oldpass))
			{
				System.out.println("----------password-------------update student_password set password='"+NewPassworddata+"' where student_id='"+user_id+"'");
				oStmt.execute("update student_password set password='"+NewPassworddata+"' where student_id='"+user_id+"'");
				
			}
			else	{		
				val="no";		
			}

		}
		catch(SQLException sqlexception)
		{
			System.out.println("changePassword()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			sqlexception.printStackTrace();
		}
		catch(Exception ex)
		{
			System.out.println("changePassword()");
			System.out.println("The Error Message - " + ex.getMessage());
			ex.printStackTrace();
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					 
						
					oStmt.close();
					oStmt2.close();
					oConn.close();
					rs.close();
		
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
		return val;			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//public synchronized static Vector  getUserData(String user_id) {
	public synchronized static String[]  getUserData(String user_id) {
		Statement  oStmt=null;
		ResultSet  oRset =null;
		Vector VUserData=new Vector();
		String str[]=new String[15];
		Connection oConn = null;	
		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			System.out.println("-------------------select * from student_details where student_id='"+user_id+"'");
			oRset = oStmt.executeQuery("select * from student_details where student_id='"+user_id+"'");
			
			while(oRset.next())
			{
// 			VUserData.addElement(oRset.getString(1));//sid
// 			VUserData.addElement(oRset.getString(2));//lname
// 			VUserData.addElement(oRset.getString(3));//gender
// 			VUserData.addElement(oRset.getString(4));//age
// 			VUserData.addElement(oRset.getString(5));//edu_status
// 			VUserData.addElement(oRset.getString(6));//experiance
// 			VUserData.addElement( oRset.getString(7));//q_prefered
// 			VUserData.addElement(oRset.getString(8));//media
// 			VUserData.addElement(oRset.getString(9));//lstyle
// 			VUserData.addElement(oRset.getString(10));//fname
// 			VUserData.addElement( oRset.getString(11));//mname
// 			VUserData.addElement(oRset.getString(12));//department
// 			VUserData.addElement(oRset.getString(13));//emailId
// 			VUserData.addElement(oRset.getString(14));//accountStatus
// 			VUserData.addElement(oRset.getString(15));//self_regis
						
				str[0] =  oRset.getString(1);
				str[1] =  oRset.getString(2);
				str[2] =  oRset.getString(3);
				str[3] =  oRset.getString(4);
				str[4] =  oRset.getString(5);
				str[5] =  oRset.getString(6);
				str[6] =  oRset.getString(7);
				str[7] =  oRset.getString(8);
				str[8] =  oRset.getString(9);
				str[9] =  oRset.getString(10);
				str[10] =  oRset.getString(11);
				str[11] =  oRset.getString(12);
				str[12] =  oRset.getString(13);
				str[13] =  oRset.getString(14);
				str[14] =  oRset.getString(15);
			
			}
			
			
		}
		catch (SQLException e) {
			System.out.println("Error due to SQL exception : "+e.getMessage());
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
			System.out.println(" error from DataBaseLayer.getUserData()");
			e.printStackTrace();
		}
		catch (Exception ex) {
			System.out.println(" error due to java.lang.exception");
			ex.printStackTrace();
			System.out.println(" printStack is :: " + ex.getMessage());
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oRset.close();
					oStmt.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
    	//connMgr.freeConnection("mysql", oConn);
		//return VUserData;
		return str;
	}
	
	
	
	
	//user_id,FNamedata,MNamedata,LNamedata,Agedata,Experiencedata,EduQuilidata,Emaildata,Genderdate,QuestionPreferreddate,MediaPreferreddata,AccountStatusdata,PermitSelfRegistrationdata//
	public synchronized static void updateStudentDetails(String user_id,String FNamedata,String MNamedata,String LNamedata,String Agedata,String Experiencedata,String EduQuilidata,String Emaildata,String Genderdate,String QuestionPreferreddate,String MediaPreferreddata,String AccountStatusdata,String PermitSelfRegistrationdata) {
		
		Statement  oStmt=null;
		Statement  oStmt2=null;
		boolean succ =false ;
		Connection oConn = null;
		
		try {
			oConn = ds1.getConnection();
			oConn.setAutoCommit(false);
			oStmt = oConn.createStatement();
			oStmt2 = oConn.createStatement();
			System.out.println("------1111------------------update  student_details set sname ='"+LNamedata+"',gender='"+Genderdate+"',age='"+Agedata+"', "+
					"edu_status='"+EduQuilidata+"',experience = '"+Experiencedata+"',question_preffered= '"+QuestionPreferreddate+"', "+
					"media_preffered= '"+MediaPreferreddata+"',email_id = '"+Emaildata+"',"+
					"first_name='"+FNamedata+"',middle_name='"+MNamedata+"', account_status = '"+AccountStatusdata+"', strself = '"+PermitSelfRegistrationdata+"' "+
					"where student_id='"+user_id+"'");				
									
			
			oStmt.execute("update  student_details set sname ='"+LNamedata+"',gender='"+Genderdate+"',age='"+Agedata+"', "+
					"edu_status='"+EduQuilidata+"',experience = '"+Experiencedata+"',question_preffered= '"+QuestionPreferreddate+"', "+
					"media_preffered= '"+MediaPreferreddata+"',email_id = '"+Emaildata+"',"+
					"first_name='"+FNamedata+"',middle_name='"+MNamedata+"', account_status = '"+AccountStatusdata+"', strself = '"+PermitSelfRegistrationdata+"' "+
					"where student_id='"+user_id+"'");
	
									
			System.out.println("------2222-----------update student_creation_details set last_modification_date = sysdate() where student_id='"+user_id+"'");
			oStmt2.execute("update student_creation_details set last_modification_date = sysdate() where student_id='"+user_id+"'");
			
			
			System.out.println("Completed Storing");
			
			oConn.commit();
			oConn.setAutoCommit(true);
			
			succ = true ;
		}
		catch (SQLException e) {
			
			System.out.println("Error in updateStudentDetails() learnityadmin");
			e.printStackTrace();
			
		}
		catch (Exception ex) {
			System.out.println("Error in updateStudentDetails() learnityadmin");
			ex.printStackTrace();
			
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
	  //  //connMgr.freeConnection("mysql", oConn);
		//return succ ;
	}
	
	
	
	public synchronized static String[]  getCourseInfo(String entry_id,String course_id,String user_id) {
		Statement  oStmt=null;
		Statement oStmt2=null;
		Statement oStmt3=null;
		ResultSet  oRset=null ;
		ResultSet oRset2=null;
		ResultSet oRset3=null;
		Vector VUserData=new Vector();
		String str[]=new String[7];
		Connection oConn = null;		
		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt2 = oConn.createStatement();
			oStmt3 = oConn.createStatement();
			System.out.println("-------------------SELECT d.catalog_name, c.entry_name, a.course_name, a.edate, a.description, a.confirmation_reqd "+		
					"FROM course_definition a,catalog_entry_entity b,catalog_entry_details c,catalog_details d "+		
					"where a.course_id=b.entity_name 	and b.entryid=c.entryid 	and c.cat_id=d.cat_id	and a.course_id='"+course_id+"' and c.entryid='"+entry_id+"' ");
			
			oRset = oStmt.executeQuery("SELECT d.catalog_name, c.entry_name, a.course_name, a.edate, a.description, a.confirmation_reqd "+		
					"FROM course_definition a,catalog_entry_entity b,catalog_entry_details c,catalog_details d "+		
					"where a.course_id=b.entity_name 	and b.entryid=c.entryid 	and c.cat_id=d.cat_id	and a.course_id='"+course_id+"' and c.entryid='"+entry_id+"' ");
			
			while(oRset.next())
			{

						
				str[0] =  oRset.getString(1);
				str[1] =  oRset.getString(2);
				str[2] =  oRset.getString(3);
				str[3] =  oRset.getString(4);
				str[4] =  oRset.getString(5);
				str[5] =  oRset.getString(6);
				
			
			}
			System.out.println("-------------SELECT *  FROM user_course_registration "+		
					"where 	course_id='"+course_id+"' and student_id='"+user_id+"' ");
			str[6] ="Not Registered";
			
			
			System.out.println("-------------------SELECT status  FROM confirmation_requisition "+		
					"where 	id='"+course_id+"' and user_id='"+user_id+"'  and entity_type='course'");
			oRset3 = oStmt3.executeQuery("SELECT status  FROM confirmation_requisition "+		
					"where 	id='"+course_id+"' and user_id='"+user_id+"'  and entity_type='course'");
			if(oRset3.next())
				str[6] =oRset3.getString(1);
			
			if(str[6].equals("Not Registered"))
			{
				oRset2 = oStmt2.executeQuery("SELECT *  FROM user_course_registration "+		
						"where 	course_id='"+course_id+"' and student_id='"+user_id+"' ");
				
				if(oRset2.next())
					str[6] ="Confirmed";
				oRset2.close();
			}
			
				
			
			
			
			
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch (Exception ex) {
			
			ex.printStackTrace();
			
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oRset.close();
					oStmt.close();
					
					oStmt2.close();
					oRset3.close();
					oStmt3.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
    	//connMgr.freeConnection("mysql", oConn);
		//return VUserData;
		System.out.println("data---db-----"+str[0]);
		System.out.println("data---db-----"+str[1]);
		System.out.println("data----db----"+str[2]);
		System.out.println("data----db----"+str[3]);
		System.out.println("data---db-----"+str[4]);
		System.out.println("data----db----"+str[5]);
		System.out.println("data----db----"+str[6]);
		System.out.println("str[6]------------------"+str[6]);
		return str;
	}
	
	
	
	
	public synchronized static String checkConfirmation(String strCourseId) {
		
	
		Statement  oStmt=null;
		ResultSet  oRset=null;
		String 	flag = null;
		Connection oConn = null;
		try	{
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			
			System.out.println("------------------------select ifnull(confirmation_reqd,\"null\") from course_definition  where course_id = '"+strCourseId+"'");
			oRset = oStmt.executeQuery("select ifnull(confirmation_reqd,\"null\") from course_definition  where course_id = '"+strCourseId+"'");
			if (oRset.next()) {
				flag = oRset.getString(1);
			}
			
		}
		catch (SQLException e) {
			System.out.println("Error due to SQL exception : "+e.getMessage());
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
		}
		catch (Exception ex) {
			System.out.println(" error due to java.lang.exception");
			ex.printStackTrace();
			System.out.println(" printStack is :: " + ex.getMessage());
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oRset.close();
					oStmt.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
    	//connMgr.freeConnection("mysql", oConn);
		
		return flag;
	}
	
	public synchronized static void sentForConfirmation(String user_id,String course_id) {
	
		Statement  oStmt=null;
		Connection oConn = null;
    
		try	{
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
					//if (strTotalTime.equals("") && strValidTill.equals("")) {
						
			System.out.println("------------------------insert into confirmation_requisition (id, user_id, "+
					"status,entity_type) values ('"+course_id+"', '"+user_id+"','Pending','course')");
			oStmt.executeUpdate("insert into confirmation_requisition (id, user_id, "+
					"status,entity_type) values ('"+course_id+"', '"+user_id+"','Pending','course')");
					//}
// 					if ((strTotalTime.equals("")) && (!strValidTill.equals(""))) {
// 						oStmt.executeUpdate("insert into confirmation_requisition (id, user_id, "+
// 								"access_allowed_till, total_access_time, status,entity_type) values ('"+strCourseId+"','"+strUsrId+"','"+strValidTill+"',null,'Pending','U')");
// 					}
// 					if ((!strTotalTime.equals("")) && (strValidTill.equals(""))) {
// 						int iTotalTime = Integer.parseInt(strTotalTime);
// 						oStmt.executeUpdate("insert into confirmation_requisition (id,user_id, "+
// 								"access_allowed_till, total_access_time, status,entity_type) values ('"+strCourseId+"','"+strUsrId+"',null,"+iTotalTime+",'Pending','U')");
// 					}
// 					if ((!strTotalTime.equals("")) && (!strValidTill.equals(""))) {
// 						int iTotalTime = Integer.parseInt(strTotalTime);
// 						oStmt.executeUpdate("insert into confirmation_requisition (id, user_id, "+
// 								"access_allowed_till, total_access_time, status,entity_type) values ('"+strCourseId+"','"+strUsrId+"','"+strValidTill+"',"+iTotalTime+",'Pending','U')");
// 					}

					
		}
		catch (SQLException e) {
			System.out.println("Error due to SQL exception in sentForConfirmation : "+e.getMessage());
			e.printStackTrace();
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
		}
		catch (Exception ex) {
			System.out.println(" error due to java.lang.exception in sentForConfirmation");
			ex.printStackTrace();
			System.out.println(" printStack is :: " + ex.getMessage());
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
    	////connMgr.freeConnection("mysql", oConn);
		//return count;
	
	}
			
			
			
			
			
			
			
			
			
	public synchronized static int insertCourseUserAssociationSelf(String user_id,String course_id) {
		int count = 0;
		Statement  oStmt=null;
		ResultSet  oRset=null;
		Statement oStmt2 = null;
		Statement oStmt3 = null;
		Connection oConn = null;
    	
		try	{
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt2 = oConn.createStatement();
			oStmt3 = oConn.createStatement();
			System.out.println("-------------------------select * from user_course_registration where student_id = '"+user_id+"' and course_id = '"+course_id+"'");
			oRset = oStmt.executeQuery("select * from user_course_registration where student_id = '"+user_id+"' and course_id = '"+course_id+"'");
			if (oRset.next()) {
           		
						//errorMessage(1063, out);
						//oStmt2.execute("delete from user_course_registration where student_id = '"+user_id+"' and course_id = '"+course_id+"'");
				return count;
			}
			else{				
				System.out.println("------------insert into user_course_registration (student_id, course_id, modification_date) "+
						"values ('"+user_id+"','"+course_id+"',sysdate())");
				count=oStmt3.executeUpdate("insert into user_course_registration (student_id, course_id, modification_date) "+
						"values ('"+user_id+"','"+course_id+"',sysdate())");
					

			}
					
			oStmt = oConn.createStatement();
					
		}
		catch (SQLException e) {
			System.out.println("Error due to SQL exception : "+e.getMessage());
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
		}
		catch (Exception ex) {
			System.out.println(" error due to java.lang.exception");
			ex.printStackTrace();
			System.out.println(" printStack is :: " + ex.getMessage());
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oRset.close();
					oStmt.close();
					oStmt3.close();
					oStmt2.close();
					oConn.close();
							
							
				}
				catch(SQLException e)
				{
				}			
			}		
		}
    	//connMgr.freeConnection("mysql", oConn);
		return count;
	}
			
			
			
			
	public static synchronized void ImportMatricef(String c_def[],String createdby)
	{
		Connection oConn =null;
		Statement statement1 = null;
		try {
			oConn = ds1.getConnection();
			oConn.setAutoCommit(false);
			statement1 = oConn.createStatement();
					
					
					//statement1.execute("insert into matricsmgmt(id, title, description,textdata,numberdata,numberdata2,numberdata3,legenddata1,legenddata2,legenddata3,type,subtype,width,height,yaxis,xaxis,color,transpose,stacked,dimension) values('"+c_def[0]+"','"+c_def[1]+"','"+c_def[2]+"','"+c_def[3]+"',sysdate(),'"+c_def[5]+"','"+c_def[6]+"','"+c_def[7]+"','"+c_def[8]+"','"+0+"','"+createdby+"')"); 
			statement1.execute("insert into matricsmgmt(id,title,description,textdata,numberdata,numberdata2,numberdata3,legenddata1,legenddata2,legenddata3,type,subtype,width,height,xaxis,yaxis,color,transpose,stacked,dimension) values ('"+c_def[0]+"','"+c_def[1]+"','"+c_def[2]+"','"+c_def[3]+"','"+c_def[4]+"','"+c_def[5]+"','"+c_def[6]+"','"+c_def[7]+"','"+c_def[8]+"','"+c_def[9]+"','"+c_def[10]+"','"+c_def[11]+"','"+c_def[12]+"','"+c_def[13]+"','"+c_def[14]+"','"+c_def[15]+"','"+c_def[16]+"','"+c_def[17]+"','"+c_def[18]+"','TwoDimensionalwithdepth')");
// 					String c_id =  returnmaxcourse();
// 					statement1.execute("insert into collection_course (course_id,collection_id) values('"+c_id+"','NULL')");
// 					statement1.close();
			oConn.commit();
			oConn.setAutoCommit(true);
					
		}
		catch(SQLException sqlexception)
		{
			sqlexception.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					statement1.close();
							
					oConn.close();
							
							
							
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
      
	}	
			
			
			
	public synchronized static void deletetemp_catalogentry(String cat_id,String Entri_Name_text) {
		ResultSet oRset4=null;
		Connection oConn = null;
		Statement statement = null;
		Statement oStmt = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			oStmt = oConn.createStatement();
			String entryid="";
			log.debug("--------33------select entryid from catalog_entry_details  where cat_id='"+cat_id+"' and entry_name='"+Entri_Name_text+"'")	;
			oRset4=oStmt.executeQuery("select entryid from catalog_entry_details  where cat_id='"+cat_id+"' and entry_name='"+Entri_Name_text+"'")	;
			if(oRset4.next())
			{
				entryid=oRset4.getString(1);
			}
					
			log.debug("------------delete from catalog_entry_details where entryid='"+entryid+"' ");
			boolean flag = statement.execute("delete from catalog_entry_details where entryid='"+entryid+"' ");
					

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					statement.close();
					oStmt.close();
					oConn.close();
					oRset4.close();
							
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
		
	}	
	public synchronized static void update_catalogentry(String entryid,String entryname,String entrydescription){
			 
		Connection oConn = null;
		Statement statement = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
					
					
					
			log.debug("------------update catalog_entry_details  set entry_name='"+entryname+"' , cat_description='"+entrydescription+"'	where entryid='"+entryid+"' ");
					
			statement.execute("update catalog_entry_details  set entry_name='"+entryname+"' , cat_description='"+entrydescription+"'	where entryid='"+entryid+"' ");
			statement.close();
					
			
					

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oConn.close();
							
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
		
	}
			
			
			
			
			
			
	public synchronized static Vector getDateTimeRequest(String course_id,String student_id) {
		
		Statement  oStmt=null;
		ResultSet  oRset=null;
		Vector vDateTime = null;
		Connection oConn = null;
    	
    	
		try	{
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			vDateTime = new Vector();
			System.out.println("------------>select access_allowed_till,total_access_time from confirmation_requisition where id ='"+course_id+"' and user_id='"+student_id+"' and entity_type='course'");
			oRset = oStmt.executeQuery("select access_allowed_till,total_access_time from confirmation_requisition where id ='"+course_id+"' and user_id='"+student_id+"' and entity_type='course'");
			if (oRset.next()) {
				vDateTime.addElement(oRset.getString(1));
				vDateTime.addElement(oRset.getString(2));
			}
					

		}
		catch (SQLException e) {
			System.out.println("Error due to SQL exception : "+e.getMessage());
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
		}
		catch (Exception ex) {
			System.out.println(" error due to java.lang.exception");
			ex.printStackTrace();
			System.out.println(" printStack is :: " + ex.getMessage());
		}
    	//connMgr.freeConnection("mysql", oConn);
		finally{
			if(oConn!=null)
			{
				try
				{
					oRset.close();
					oStmt.close();
					oConn.close();
							
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
				
		return vDateTime;
	}
		
			
			
			
			
			
	public synchronized static boolean insertCourseUserAssociation(String strUsrId, String strCourseId, String strCreatedBy, String strValidTill, String strTotalTime) {
		boolean flag = false;
		Statement  oStmt=null;
		Statement  oStmt1=null;
		ResultSet  oRset=null;
		Connection oConn = null;
    	//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
    	
		try	{
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt1 = oConn.createStatement();
					
			System.out.println("------------------select * from user_course_registration where student_id = '"+strUsrId+"' and course_id = '"+strCourseId+"'");
			oRset = oStmt.executeQuery("select * from user_course_registration where student_id = '"+strUsrId+"' and course_id = '"+strCourseId+"'");
			if (oRset.next()) {
				System.out.println("----------------delete from user_course_registration where student_id = '"+strUsrId+"' and course_id = '"+strCourseId+"'");
				oStmt1.execute("delete from user_course_registration where student_id = '"+strUsrId+"' and course_id = '"+strCourseId+"'");
						//errorMessage(1063, out);
            	//connMgr.freeConnection("mysql", oConn);
				
			}
			
			oStmt1.execute("insert into user_course_registration (student_id, course_id, registration_date, "+
					"registration_by,modification_date) values ('"+strUsrId+"','"+strCourseId+
					"',sysdate(),'"+strCreatedBy+"',sysdate())");
			
			if (strTotalTime.equals("") && strValidTill.equals("")) {
				System.out.println("---------------insert into user_course_registration (student_id, course_id, registration_date, "+
						"registration_by, access_allowed_till, total_access_time) values ('"+strUsrId+"','"+strCourseId+
						"',sysdate(),'"+strCreatedBy+"',null,null)");
										 
				flag=oStmt1.execute("insert into usergroup_course_registration (student_id, course_id, registration_date, "+
						"registration_by, access_allowed_till, total_access_time) values ('"+strUsrId+"','"+strCourseId+
						"',sysdate(),'"+strCreatedBy+"',null,null)");
				
				
				
										 
    								 
                           								 
			}
			if ((strTotalTime.equals("")) && (!strValidTill.equals(""))) {
				flag=oStmt1.execute("insert into usergroup_course_registration (student_id, course_id, registration_date, "+
						"registration_by, access_allowed_till, total_access_time) values ('"+strUsrId+"','"+strCourseId+
						"',sysdate(),'"+strCreatedBy+"','"+strValidTill+"',null)");
    								 
    								 
    				
						
    								 
    								 
			}
			if ((!strTotalTime.equals("")) && (strValidTill.equals(""))) {
				int iTotalTime = Integer.parseInt(strTotalTime);
				flag=oStmt1.execute("insert into usergroup_course_registration (student_id, course_id, registration_date, "+
						"registration_by, access_allowed_till, total_access_time) values ('"+strUsrId+"','"+strCourseId+
						"',sysdate(),'"+strCreatedBy+"',null,"+iTotalTime+")");
    								 
						
    								 
    								 
			}
			if ((!strTotalTime.equals("")) && (!strValidTill.equals(""))) {
				int iTotalTime = Integer.parseInt(strTotalTime);
				flag=oStmt1.execute("insert into usergroup_course_registration (student_id, course_id, registration_date, "+
						"registration_by, access_allowed_till, total_access_time) values ('"+strUsrId+"','"+strCourseId+
						"',sysdate(),'"+strCreatedBy+"','"+strValidTill+"',"+iTotalTime+")");
    								 
    								 
							
    								 
    								 
    								 
			}

					
		}
		catch (SQLException e) {
			e.printStackTrace();
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
		}
		catch (Exception ex) {
					//System.out.println(" error due to java.lang.exception");
			ex.printStackTrace();
					//System.out.println(" printStack is :: " + ex.getMessage());
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oStmt1.close();
					oConn.close();
					oRset.close();
							
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
				
    	//connMgr.freeConnection("mysql", oConn);
		return flag;
	}
	
	public synchronized static boolean insertCourseUserAssociation2(String strUsrId, String strCourseId,	String strCreatedBy, String strValidTill, String strTotalTime) {
		boolean flag = false;
		Statement  oStmt=null;
		Statement  oStmt1=null;
		Statement  oStmt2=null;
		ResultSet  oRset=null;
		Connection oConn = null;
    	//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
    	
		try	{
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt1 = oConn.createStatement();
			oStmt2 = oConn.createStatement();
					
			System.out.println("------------------select * from user_course_registration where student_id = '"+strUsrId+"' and course_id = '"+strCourseId+"'");
			oRset = oStmt.executeQuery("select * from user_course_registration where student_id = '"+strUsrId+"' and course_id = '"+strCourseId+"'");
			if (oRset.next()) {
// 				System.out.println("----------------delete from user_course_registration where student_id = '"+strUsrId+"' and course_id = '"+strCourseId+"'");
// 				oStmt1.execute("delete from user_course_registration where student_id = '"+strUsrId+"' and course_id = '"+strCourseId+"'");
						//errorMessage(1063, out);
            	//connMgr.freeConnection("mysql", oConn);
				
			}
			else{
			
				oStmt1.execute("insert into user_course_registration (student_id, course_id, registration_date, "+
						"registration_by,modification_date) values ('"+strUsrId+"','"+strCourseId+
						"',sysdate(),'"+strCreatedBy+"',sysdate())");
			}
			
			
			if (strTotalTime.equals("") && strValidTill.equals("")) {
				System.out.println("---------------insert into usergroup_course_registration (student_id, course_id, registration_date, "+
						"registration_by, access_allowed_till, total_access_time) values ('"+strUsrId+"','"+strCourseId+
						"',sysdate(),'"+strCreatedBy+"',null,null)");
										 
				flag=oStmt1.execute("insert into usergroup_course_registration (student_id, course_id, registration_date, "+
						"registration_by, access_allowed_till, total_access_time) values ('"+strUsrId+"','"+strCourseId+
						"',sysdate(),'"+strCreatedBy+"',null,null)");
				
				
				
										 
    								 
                           								 
			}
			if ((strTotalTime.equals("")) && (!strValidTill.equals(""))) {
				flag=oStmt1.execute("insert into usergroup_course_registration (student_id, course_id, registration_date, "+
						"registration_by, access_allowed_till, total_access_time) values ('"+strUsrId+"','"+strCourseId+
						"',sysdate(),'"+strCreatedBy+"','"+strValidTill+"',null)");
    								 
    								 
    				
						
    								 
    								 
			}
			if ((!strTotalTime.equals("")) && (strValidTill.equals(""))) {
				int iTotalTime = Integer.parseInt(strTotalTime);
				flag=oStmt1.execute("insert into usergroup_course_registration (student_id, course_id, registration_date, "+
						"registration_by, access_allowed_till, total_access_time) values ('"+strUsrId+"','"+strCourseId+
						"',sysdate(),'"+strCreatedBy+"',null,"+iTotalTime+")");
    								 
						
    								 
    								 
			}
			if ((!strTotalTime.equals("")) && (!strValidTill.equals(""))) {
				int iTotalTime = Integer.parseInt(strTotalTime);
				flag=oStmt1.execute("insert into usergroup_course_registration (student_id, course_id, registration_date, "+
						"registration_by, access_allowed_till, total_access_time) values ('"+strUsrId+"','"+strCourseId+
						"',sysdate(),'"+strCreatedBy+"','"+strValidTill+"',"+iTotalTime+")");
    								 
    								 
							
    								 
    								 
    								 
			}
			
			log.debug("---------------update confirmation_requisition set status='Confirmed' where id ='"+strCourseId+"' and user_id='"+strUsrId+"' and entity_type='course'");
			oStmt2.execute("update confirmation_requisition set status='Confirmed' where id ='"+strCourseId+"' and user_id='"+strUsrId+"' and entity_type='course'");

			
			oStmt.close();
			oStmt1.close();
			oStmt2.close();
			oConn.close();
			oRset.close();
					
		}
		catch (SQLException e) {
			e.printStackTrace();
			try{
				oConn.rollback();
			}
			catch(Exception re){
    	   	
				re.printStackTrace();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			try{
				oConn.rollback();
			}
			catch(Exception re){
    	   	
				re.printStackTrace();
			}
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oStmt1.close();
					oStmt2.close();
					oConn.close();
					oRset.close();
							
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
				
    	//connMgr.freeConnection("mysql", oConn);
		return flag;
	}
	
	
	public synchronized static boolean confirmedRequest(String strCourseId,String strUser_id,String e_type) 
	{
		
		boolean flag = false;
		Statement  oStmt=null;
		Connection oConn = null;
		//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
    	
		try	{
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt.execute("update confirmation_requisition set status='Confirmed' where id ='"+strCourseId+"' and user_id='"+strUser_id+"' and entity_type='"+e_type+"'");
			log.debug("update confirmation_requisition set status='Confirmed' where id ='"+strCourseId+"' and user_id='"+strUser_id+"' and entity_type='"+e_type+"'");	
			flag = true;
		}
		catch (SQLException e) {
			System.out.println("Error due to SQL exception : "+e.getMessage());
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
		}
		catch (Exception ex) {
			System.out.println(" error due to java.lang.exception");
			ex.printStackTrace();
			System.out.println(" printStack is :: " + ex.getMessage());
		}
				
					 
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}	 
					 
					 
					 
					 
					 
    	//connMgr.freeConnection("mysql", oConn);
		return flag;
	}
			
	public synchronized static void CancelConfirmation(String user_id,String course_id) {
	
		Statement  oStmt=null;
		Connection oConn = null;
    
		try	{
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
					//if (strTotalTime.equals("") && strValidTill.equals("")) {
						
			System.out.println("------------------------delete from confirmation_requisition where id='"+course_id+"' and  user_id ='"+user_id+"')");
			oStmt.execute("delete from confirmation_requisition where id='"+course_id+"' and  user_id ='"+user_id+"' and entity_type='course'");
					//}


					
		}
		catch (SQLException e) {					
			e.printStackTrace();      		
		}
		catch (Exception ex) {					
			ex.printStackTrace();					
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
    	////connMgr.freeConnection("mysql", oConn);
		//return count;
	
	}
			
			
			
			
	public  synchronized static void CancelSelfConfirmation(String strUsrId, String strCourseId) {
		int count = 0;
		Statement  oStmt=null;
		Statement  oStmt1=null;
		ResultSet  oRset=null;
		Connection oConn = null;
    	    	
		try	{
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt1 = oConn.createStatement();
			oRset = oStmt.executeQuery("select * from user_course_registration where student_id = '"+strUsrId+"' and course_id = '"+strCourseId+"'");
			while(oRset.next()) {
				System.out.println("---------------delete from user_course_registration where student_id = '"+strUsrId+"' and course_id = '"+strCourseId+"'");
				
				oStmt1.execute("delete from user_course_registration where student_id = '"+strUsrId+"' and course_id = '"+strCourseId+"'");
				
						//return count;
			}
			oStmt1.execute("delete from usergroup_course_registration where student_id = '"+strUsrId+"' and course_id = '"+strCourseId+"'");
			System.out.println("---------------delete from confirmation_requisition where user_id = '"+strUsrId+"' and id = '"+strCourseId+"' and entity_type='course' ");
			oStmt1.execute("delete from confirmation_requisition where user_id = '"+strUsrId+"' and id = '"+strCourseId+"' and entity_type='course' ");
										
		}
		catch (SQLException e) {
			e.printStackTrace();
      		
		}
		catch (Exception ex) {
			ex.printStackTrace();
					
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oStmt1.close();
					oConn.close();
					oRset.close();
							
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
				
    
				//return count;
	}
	

			
			
	public synchronized static boolean denyRequest(String strCourseId,String strUser_id ) 
	{
		
		boolean flag = false;
		Statement  oStmt;
    	
    	//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
    	
		try	{
			Connection oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			System.out.println("--------------------update confirmation_requisition set status='Denied' where id ='"+strCourseId+"' and user_id='"+strUser_id+"' and entity_type='course'");
			oStmt.execute("update confirmation_requisition set status='Denied' where id ='"+strCourseId+"' and user_id='"+strUser_id+"' and entity_type='course'");
			oStmt.close();
			oConn.close();
			flag = true;
		}
		catch (SQLException e) {
			e.printStackTrace();
					//System.out.println("Error due to SQL exception : "+e.getMessage());
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
		}
		catch (Exception ex) {
					//System.out.println(" error due to java.lang.exception");
			ex.printStackTrace();
					//System.out.println(" printStack is :: " + ex.getMessage());
		}
    	//connMgr.freeConnection("mysql", oConn);
		return flag;
	}
	
				
// 			/*public synchronized static boolean CancelconfirmedRequest(String strCourseId,String strUser_id,String e_type) 
// 			{
	// 		
// 				boolean flag = false;
// 				Statement  oStmt=null;
// 				Connection oConn = null;
// 		//DBConnectionManager connMgr = DBConnectionManager.getInstance();
//         //Connection oConn = connMgr.getConnection("mysql");
	//     	
// 				try	{
// 					oConn = ds1.getConnection();
// 					oStmt = oConn.createStatement();
// 					oStmt.execute("update confirmation_requisition set status='Pending' where id ='"+strCourseId+"' and user_id='"+strUser_id+"' and entity_type='"+e_type+"'");
	// 					
// 					flag = true;
// 				}
// 				catch (SQLException e) {
// 					e.printStackTrace();
// 				}
// 				catch (Exception ex) {
// 					ex.printStackTrace();					
// 				}
	// 				
	// 					 
// 				finally{
// 					if(oConn!=null)
// 					{
// 						try
// 						{
// 							oStmt.close();
// 							oConn.close();
// 						}
// 						catch(SQLException e)
// 						{
// 						}
	// 			
	// 			
// 					}
	// 		
// 				}	 
	// 					*/ 
	// 					 
	// 					 
	// 					 
	// 					 
//     	//connMgr.freeConnection("mysql", oConn);
// 				return flag;
// 			}	
	//     
	////////////////////////////end jayanta////////////////////////////////
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	/****************************************** Partha for Image Upload *********************/
			
			public synchronized static void updateStudentPhoto(String student_id,InputStream is,int size) {
		
		Statement  oStmt=null;
		
		Connection oConn = null;
		
		
		try {
			oConn = ds1.getConnection();
			oConn.setAutoCommit(false);
			oStmt = oConn.createStatement();
	
			
			System.out.println("student_id==updateStudentPhoto==="+student_id);
				
				
			PreparedStatement pstmt = oConn.prepareStatement("update student_details SET photo= ? where student_id=?");
			pstmt.setBinaryStream( 1, is, size);
			pstmt.setString(2,student_id);
			pstmt.executeUpdate();
				
			
			
			oConn.commit();
			oConn.setAutoCommit(true);
			
			
		}
		catch (SQLException e) {
			System.out.println("==SQLException===");
			e.printStackTrace();
		}
		catch (Exception ex) {
			System.out.println("==Exception===");
			ex.printStackTrace();
		}
	  //  //connMgr.freeConnection("mysql", oConn);
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}	 
					 
			}			
			
			
			/***************************** End of Partha for Image Upload ***********************/			
		
		public static synchronized void updateLogout(String student_id,String session_id,String strDate,String strTime)
			{
				Connection oConn = null;
				Statement statement =null; 
				try
				{

					oConn = ds1.getConnection();
					statement = oConn.createStatement();
			//System.out.println("update student_login_time set logged_out_at = '" + strDate + " " + strTime + "' where session_id = '" + session_id + "' and student_id = '" + student_id + "' ");
					statement.execute("update student_login_time set logged_out_at = '" + strDate + " " + strTime + "' where session_id = '" + session_id + "' and student_id = '" + student_id + "' ");
// 			statement.close();
			
				}
				catch(SQLException sqlexception)
				{
					System.out.println("SQL error inside DataBaseLayer.updateCourseLoginInfo()");
					System.out.println("The error code is" + sqlexception.getErrorCode());
					System.out.println("The error message   updateLogout is" + sqlexception.getMessage());
				}
				catch(Exception exception)
				{
					System.out.println("General exception inside DataBaseLayer.updateCourseLoginInfo()");
					exception.printStackTrace();
				}
				finally{
					if(oConn!=null)
					{
						try{
							statement.close();
							oConn.close();}catch(SQLException e){}
					}
				}
			}	
	/////////////////////////// Nayna  //////////////////////////////////
	public synchronized static Vector getCourseGroupAssociation1(String strUnitId) {
		
		Statement  oStmt=null;
    		ResultSet  oRset=null;
    		Vector vGroupList = null;
		Connection oConn = null;
		System.out.println("strUnitId ======createDropDownScormInitGroup==dblayer========="+strUnitId);
    		try	{
      			oConn = ds1.getConnection();
      			oStmt = oConn.createStatement();
			oRset=oStmt.executeQuery("select count(*)  from unit_group_association where unit_id='"+strUnitId+"'");
			int  l_i=0;
			while(oRset.next())	{
				l_i = oRset.getInt(1);
			}
      			oRset.close();
      			oStmt.close();
			if(l_i <= 0) {
				return null;
			}
			vGroupList = new Vector();
      			oStmt = oConn.createStatement();
    			oRset=oStmt.executeQuery("select a.group_id, b.group_name, date_format(a.date_registration,\"%M %e, %Y %H:%i\"), "+
    								// "concat(c.user_first_name,' ', c.user_middle_name,' ',c.user_last_name), "+
    								 "date_format(a.access_allowed_till,\"%M %e, %Y\"), "+
    								 "date_format(a.access_allowed_till,\"%d/%m/%Y\"), "+
    								 "a.total_access_time "+
					"from unit_group_association a, student_group b where unit_id='"+strUnitId+"' "+
    								 "and a.group_id=b.group_id");
			while(oRset.next()) {
				Vector vGroup = new Vector();
				vGroup.addElement(oRset.getString(1));
				vGroup.addElement(oRset.getString(2));
				vGroup.addElement(oRset.getString(3));
				vGroup.addElement(oRset.getString(4));
				vGroup.addElement(oRset.getString(5));
				vGroup.addElement(oRset.getString(6));
				//vGroup.addElement(oRset.getString(7));
				vGroupList.addElement(vGroup);
			}
			System.out.println("vGroupList ======createDropDownScormInitGroup==dblayer========="+vGroupList);
    	}
    	catch (SQLException e) {
		System.out.println(" error due to SQL exception"+e.getMessage());
    	}
    	catch (Exception ex) {
      		System.out.println(" error due to java.lang.exception");
      		ex.printStackTrace();
      		System.out.println(" printStack is :: " + ex.getMessage());
    	}
	finally{
		if(oConn!=null)
		{
			try{
				oRset.close();
				oStmt.close();
				oConn.close();}catch(SQLException e){}
		}
	}
		return vGroupList;
}
public synchronized static Vector getCourseUserAssociation1(String strUnitId) {
	Statement  oStmt=null;
	ResultSet  oRset=null;
	Vector vCourseList = null;
	Connection oConn =null;
	try	{
		oConn = ds1.getConnection();
		oStmt = oConn.createStatement();
		oRset=oStmt.executeQuery("select count(*)  from unit_student_association where unit_id='"+strUnitId+"'");
		int  l_i=0;
		while(oRset.next())	{
			l_i = oRset.getInt(1);
		}
		oRset.close();
		oStmt.close();
		if(l_i <= 0) {
				
			return null;
		}
		vCourseList = new Vector();
		oStmt = oConn.createStatement();
		oRset=oStmt.executeQuery("select a.student_id, concat(b.first_name,' ',b.middle_name,' ',b.sname), date_format(a.date_registration,\"%M %e, %Y %H:%i\"), "+
				//"concat(c.user_first_name,' ', c.user_middle_name,' ',c.user_last_name), "+
				"date_format(a.access_allowed_till,\"%M %e, %Y\"), "+
				"date_format(a.access_allowed_till,\"%d/%m/%Y\"), "+
				"a.total_access_time "+
				"from unit_student_association a, student_details b where a.unit_id='"+strUnitId+"' "+
				"and a.student_id=b.student_id");
		while(oRset.next()) {
			Vector vCourse = new Vector();
			vCourse.addElement(oRset.getString(1));
			vCourse.addElement(oRset.getString(2));
			vCourse.addElement(oRset.getString(3));
			vCourse.addElement(oRset.getString(4));
			vCourse.addElement(oRset.getString(5));
			vCourse.addElement(oRset.getString(6));
			//vCourse.addElement(oRset.getString(7));
			String strGrs = "";
			Statement oStmt1 = oConn.createStatement();
			ResultSet oRset1=oStmt1.executeQuery("select a.group_name from student_group a, student_group_association b where b.student_id='"+oRset.getString(1)+"' "+
					"and b.group_id = a.group_id");
			while(oRset1.next()) {
				strGrs += oRset1.getString(1)+", ";
			}
			vCourse.addElement(strGrs);
			vCourseList.addElement(vCourse);

			oRset1.close();
			oStmt1.close();
		}
	}
	catch (SQLException e) {
		System.out.println("Error due to SQL exception : "+e.getMessage());
	}
	catch (Exception ex) {
		System.out.println(" error due to java.lang.exception");
		ex.printStackTrace();
		System.out.println(" printStack is :: " + ex.getMessage());
	}
	finally{
		if(oConn!=null)
		{
			try{
				oRset.close();
				oStmt.close();
				oConn.close();}catch(SQLException e){}
		}
	}
	return vCourseList;
}	
public static synchronized void createTableScormInitialization(String unit_id, String user_id) {
	Statement  oStmt=null;
    	
	Statement  oStmt2=null;
	ResultSet  oRset2=null;
	Statement statement=null;
	Connection oConn = null;
	try {
		oConn = ds1.getConnection();
		oStmt = oConn.createStatement();
    		
		oStmt.execute("delete from temp_scorm_initialization;");
		oStmt.close();
		oStmt2 = oConn.createStatement();
		
		oRset2=oStmt2.executeQuery("select a.sco_id, b.title, a.lessonstatus, a.lessonmode, a.entry1, a.exit1, a.totaltime from userscoinfo a, iteminfo b where a.user_id = '"+user_id+"' and a.unit_id='"+unit_id+"' and a.sco_id=b.identifier and a.unit_id=b.unit_id");
		while(oRset2.next()) {
				 
			String SCOID=oRset2.getString(1);
			String Title=oRset2.getString(2);
			String LessonStatus=oRset2.getString(3);
			String LessonMode=oRset2.getString(4);
			String Entry=oRset2.getString(5);
			String Exit=oRset2.getString(6);
			String TotalTime=oRset2.getString(7);
			if(SCOID==null){SCOID="";}
			if(Title==null){Title="";}
			if(LessonStatus==null){LessonStatus="";}
			if(LessonMode==null){LessonMode="";}
			if(Entry==null){Entry="";}
			if(Exit==null){Exit="";}
			if(TotalTime==null){TotalTime="";}
			
			statement = oConn.createStatement();
			System.out.println("insert into temp_scorm_initialization(scoid,title,lessonstatus,lessonmode,exit1,entry1,totaltime) values ('"+SCOID+"','"+Title+"','"+LessonStatus+"','"+LessonMode+"','"+Entry+"','"+Exit+"','"+TotalTime+"')");	
			boolean flag = statement.execute("insert into temp_scorm_initialization(scoid,title,lessonstatus,lessonmode,exit1,entry1,totaltime)"+ 
					" values ('"+SCOID+"','"+Title+"','"+LessonStatus+"','"+LessonMode+"','"+Entry+"','"+Exit+"','"+TotalTime+"')");
			
		}
			
		
			
      		
	}
	catch (SQLException e) {
		log.debug("createTableScormInitialization: error due to SQL exception"+e);
      		
	} 
	catch (Exception ex) {
		log.debug("*****************error due to java.lang.exception****************");
		ex.printStackTrace();
		log.debug("createTableScormInitialization printStack is :: " + ex.getMessage());
	}     	
	finally{
			
		if(oConn!=null)
		{
			try{
				oRset2.close();
				
				oStmt2.close();
				statement.close();
				oConn.close();}catch(SQLException e){}
		}
	}
} 	
  public synchronized static void SCOInitialize2(String strUsrId, String strUnitId,String strsco, String strMode, String strEntry, String strSuspend, String strStatus, String strCredit, String strExit, String strLaunch, String strTime) {

	  Statement  oStmt=null;
	  Connection oConn =null;
	  try	{
		  oConn = ds1.getConnection();
		  oStmt = oConn.createStatement();
		  System.out.println("update userscoinfo set lessonmode='"+ strMode +"', entry1='"+ strEntry +"', credit='"+ strCredit +"', lessonstatus='"+ strStatus +"', launch='"+ strLaunch +"', exit1='"+ strExit +"', totaltime='"+ strTime +"' where user_id='"+strUsrId+"' and unit_id='"+strUnitId+"' and sco_id='"+strsco+"'");
		  oStmt.execute("update userscoinfo set lessonmode='"+ strMode +"', entry1='"+ strEntry +"', credit='"+ strCredit +"', lessonstatus='"+ strStatus +"', launch='"+ strLaunch +"', exit1='"+ strExit +"', totaltime='"+ strTime +"' where user_id='"+strUsrId+"' and unit_id='"+strUnitId+"' and sco_id='"+strsco+"'");
		 
	  }
	  catch (SQLException e) {
		  System.out.println(" error due to SQL exception");
      		
	  }
	  catch (Exception ex) {
		  System.out.println(" error due to java.lang.exception");
		  ex.printStackTrace();
		  System.out.println(" printStack is :: " + ex.getMessage());
	  }
	  finally{
			
		  if(oConn!=null)
		  {
			  try{
				  oStmt.close();
				  oConn.close();
				  }catch(SQLException e){}
		  }
	  }	
  } 			
			
  public synchronized static Vector getUserGroupDetailsList(String strGrId) {
		
	  Statement  oStmt=null;
	  ResultSet  oRset=null;
	  Connection oConn =null;
	  Vector vGroupList = null;
    	
	  try	{
		  oConn = ds1.getConnection();
		  oStmt = oConn.createStatement();
		  oRset=oStmt.executeQuery("select count(*) from student_group_association where group_id = '"+strGrId+"'");
		  int  l_i=0;
		  while(oRset.next())	{
			  l_i = oRset.getInt(1);
		  }
		  oRset.close();
		  oStmt.close();
		  if(l_i <= 0) {
			  return null;
		  }
		  vGroupList = new Vector();
		  oStmt = oConn.createStatement();
		  oRset=oStmt.executeQuery("select a.student_id, concat(b.first_name,' ', b.middle_name,' ',b.sname) "+
				  "from student_group_association a, student_details b where a.group_id = '"+strGrId+"' and a.student_id=b.student_id");
		  while(oRset.next()) {
			  Vector vGroup = new Vector();
			  vGroup.addElement(oRset.getString(1));
			  vGroup.addElement(oRset.getString(2));
			  vGroupList.addElement(vGroup);
		  }
		 
	  }
	  catch (SQLException e) {
		  System.out.println(" error due to SQL exception");
	  }
	  catch (Exception ex) {
		  System.out.println(" error due to java.lang.exception");
		  ex.printStackTrace();
		  System.out.println(" printStack is :: " + ex.getMessage());
	  }
	  finally{
			
		  if(oConn!=null)
		  {
			  try{
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  }catch(SQLException e){}
		  }
	  }	
	  return vGroupList;
  }
  public synchronized static boolean isExistStudent(String strUsrId, String strCourseId) {
		
	  boolean flag = false;
	  Statement  oStmt=null;
	  ResultSet  oRset=null;
	  Connection oConn =null;
	  try	{
		  oConn = ds1.getConnection();
		  oStmt = oConn.createStatement();
		  oRset = oStmt.executeQuery("select * from unit_student_association where student_id = '"+strUsrId+"' and unit_id = '"+strCourseId+"'");
		  if (oRset.next()) {
			  flag = true;
		  }
		 

	  }
	  catch (SQLException e) {
		  System.out.println("Error due to SQL exception : "+e.getMessage());
      		
	  }
	  catch (Exception ex) {
		  System.out.println(" error due to java.lang.exception");
		  ex.printStackTrace();
		  System.out.println(" printStack is :: " + ex.getMessage());
	  }
	  finally{
			
		  if(oConn!=null)
		  {
			  try{
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  }catch(SQLException e){}
		  }
	  }	
	  return flag;
  }
  public synchronized static void SCOInitialize(String strUsrId, String strUnitId, String strMode, String strEntry, String strSuspend, String strStatus, String strCredit, String strExit, String strLaunch, String strTime) {

	  Statement  oStmt=null;
	  Connection oConn =null;
	  try	{
		  oConn = ds1.getConnection();
		  oStmt = oConn.createStatement();
		  System.out.println("update userscoinfo set lessonmode='"+ strMode +"',entry1='"+ strEntry +"', credit='"+ strCredit +"', lessonstatus='"+ strStatus +"', launch='"+ strLaunch +"', exit1='"+ strExit +"', totaltime='"+ strTime +"' where user_id='"+strUsrId+"' and unit_id='"+strUnitId+"'");
		  oStmt.execute("update userscoinfo set lessonmode='"+ strMode +"',entry1='"+ strEntry +"', credit='"+ strCredit +"', lessonstatus='"+ strStatus +"', launch='"+ strLaunch +"', exit1='"+ strExit +"', totaltime='"+ strTime +"' where user_id='"+strUsrId+"' and unit_id='"+strUnitId+"'");
		  oStmt.close();
		  oConn.close();
	  }
	  catch (SQLException e) {
		  System.out.println(" error due to SQL exception");
      		
	  }
	  catch (Exception ex) {
		  System.out.println(" error due to java.lang.exception");
		  ex.printStackTrace();
		  System.out.println(" printStack is :: " + ex.getMessage());
	  }
  } 
	////////////////////////////////end Nayna//////////////////////////////////////	
  	
  
  /*============================nayna 08.04.09====start=======================================*/
  public static byte[] getUserPhoto(String userId){
	  
	  byte[] _blob=null;
	  Connection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs =null;
// 	  System.out.println("userId=="+userId);
	  try{
		  conn = ds1.getConnection();	
		  pstmt = conn.prepareStatement("SELECT photo FROM student_details WHERE student_id = ?");
		  pstmt.setString(1, userId);
		  rs = pstmt.executeQuery();
		  if(rs.next()) {
// 			  System.out.println("rs has next");
			  Blob blob = rs.getBlob(1);
// 			  System.out.println("blob=="+blob);
			  if(blob!=null){
			  	int length = (int)blob.length();
// 			  	System.out.println("length is:"+length);
			  	_blob = blob.getBytes(1, length);
			  }
// 			  System.out.println("_blob is:"+_blob);
		  }
		  System.out.println("Completed.Retreiving..");
		//conn.close();
 
	  }catch(SQLException ex) {
		  System.out.println("SQLException in getUserPhoto "+ex.getMessage());
		  ex.printStackTrace();
	  }
	  catch(Exception e) {
		  System.out.println("Exception in getUserPhoto "+e.getMessage());
		  e.printStackTrace();
	  }
	  finally{
		  try{
			  pstmt.close();
			  rs.close();		
			  conn.close();}catch(SQLException e){}
	  }
	  return _blob;
  }
  public synchronized static Vector getUserDetails() {
	  Statement  oStmt=null;
	  Statement  oStmt1=null;
	  ResultSet  oRset =null;
	  ResultSet  oRset1 =null;
	  Vector<Vector<String>> v=new Vector<Vector<String>>();
	  Connection oConn = null;
	  try {
		  oConn = ds1.getConnection();
		  oStmt = oConn.createStatement();
		  oStmt1 = oConn.createStatement();
// 		  oRset=oStmt.executeQuery("select a.student_id,b.password,a.first_name,a.middle_name,a.sname,"+
// 				  " a.gender,a.account_status,c.role_id,d.date_student_created,d.student_created_by,d.last_modification_date from student_details a,"+
// 				  " student_password b,user_role c,student_creation_details d"+
// 				  "  where a.student_id=b.student_id and a.student_id=c.user_id and a.student_id=d.student_id");
		  
		  oRset=oStmt.executeQuery("select a.student_id,b.password,a.first_name,a.middle_name,a.sname,"+
				  " a.gender,a.account_status,d.date_student_created,d.student_created_by,d.last_modification_date from student_details a,"+
				  " student_password b,student_creation_details d"+
				  "  where a.student_id=b.student_id and a.student_id=d.student_id");
		  
		  while(oRset.next()) {
			  Vector<String> v1=new Vector<String>();
			  String id=oRset.getString(1);
			  v1.addElement(id);
			  v1.addElement(oRset.getString(2));
			  v1.addElement(oRset.getString(3));
			  v1.addElement(oRset.getString(4));
			  v1.addElement(oRset.getString(5));
			  v1.addElement(oRset.getString(6));
			  v1.addElement(oRset.getString(7));
			  v1.addElement(oRset.getString(8));
			  v1.addElement(oRset.getString(9));
			  v1.addElement(oRset.getString(10));

			  oRset1=oStmt1.executeQuery("select role_id from user_role where user_id='"+id+"'");
			  if(oRset1.next()){
				  v1.addElement(oRset1.getString(1));
			  }
			  else{
				  v1.addElement("role is not defined yet");
			  }
			  v.addElement(v1);
		  }
				 
	  }
	  catch (SQLException e) {
		  System.out.println("Error due to SQL exception : getUserDetails()");
		  System.out.println("SQL exception : "+e.toString());
	  }
	  catch (Exception ex) {
		  System.out.println("Error due to exception : getUserDetails()");
		  ex.printStackTrace();
		  System.out.println(" printStack is :: " + ex.getMessage());
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  oStmt.close();
				  oRset.close();
				  oConn.close();
			  }
			  catch(SQLException e)
			  {
			  }
			
			
		  }
		
	  }
	  return v ;
  }
  public synchronized static Vector<Vector<String>> getGroupDetails() {
	  Statement  oStmt=null;
	  ResultSet  oRset =null;
	  Vector<Vector<String>> v=new Vector<Vector<String>>();
	  Connection oConn = null;
	  try {
		  oConn = ds1.getConnection();
		  oStmt = oConn.createStatement();
		  oRset=oStmt.executeQuery("select a.group_id,a.group_name,a.group_created_date,a.group_created_by"+
				  " from student_group a");
				  
		  while(oRset.next()) {
			  Vector<String> v1=new Vector<String>();
			  v1.addElement(oRset.getString(1));
			  v1.addElement(oRset.getString(2));
			  v1.addElement(oRset.getString(3));
			  v1.addElement(oRset.getString(4));
			  
			  v.addElement(v1);
		  }
				 
	  }
	  catch (SQLException e) {
		  System.out.println("Error due to SQL exception : parse()");
		  System.out.println("SQL exception : "+e.toString());
	  }
	  catch (Exception ex) {
		  System.out.println("Error due to exception : parse()");
		  ex.printStackTrace();
		  System.out.println(" printStack is :: " + ex.getMessage());
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  oStmt.close();
				  oRset.close();
				  oConn.close();
			  }
			  catch(SQLException e)
			  {
			  }
			
			
		  }
		
	  }
	  return v ;
  }	
  public synchronized static Vector<String> getGroupUserRegDetails(String groupid) {
	  Statement  oStmt=null;
	  ResultSet  oRset =null;
	  Vector<String> v1=new Vector<String>();
	  Connection oConn = null;
	  try {
		  oConn = ds1.getConnection();
		  oStmt = oConn.createStatement();
		  oRset=oStmt.executeQuery("select student_id from student_group_association where group_id='"+groupid+"'");
				  
		  while(oRset.next()) {
			  v1.addElement(oRset.getString(1));
		  }
				 
	  }
	  catch (SQLException e) {
		  System.out.println("Error due to SQL exception : parse()");
		  System.out.println("SQL exception : "+e.toString());
	  }
	  catch (Exception ex) {
		  System.out.println("Error due to exception : parse()");
		  ex.printStackTrace();
		  System.out.println(" printStack is :: " + ex.getMessage());
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  oStmt.close();
				  oRset.close();
				  oConn.close();
			  }
			  catch(SQLException e)
			  {
			  }
			
			
		  }
		
	  }
	  return v1 ;
  }			
  public synchronized static Vector getUnitDetails() {
	  Statement  oStmt=null;
	  ResultSet  oRset =null;
	  Vector v=new Vector();
	  Connection oConn = null;
	  try {
		  oConn = ds1.getConnection();
		  oStmt = oConn.createStatement();
		  oRset=oStmt.executeQuery("select a.unit_id,a.unit_name,b.status,b.controll"+
				 " from unit_details a,unit_creation_details b"+
				  " where a.unit_id=b.unit_id");
				  
		  while(oRset.next()) {
			  Vector v1=new Vector();
			  v1.addElement(oRset.getString(1));
			  v1.addElement(oRset.getString(2));
			  v1.addElement(oRset.getString(3));
			  v1.addElement(oRset.getString(4));
			  
			  v.addElement(v1);
		  }
				 
	  }
	  catch (SQLException e) {
		  System.out.println("Error due to SQL exception : parse()");
		  System.out.println("SQL exception : "+e.toString());
	  }
	  catch (Exception ex) {
		  System.out.println("Error due to exception : parse()");
		  ex.printStackTrace();
		  System.out.println(" printStack is :: " + ex.getMessage());
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  oStmt.close();
				  oRset.close();
				  oConn.close();
			  }
			  catch(SQLException e)
			  {
			  }
			
			
		  }
		
	  }
	  return v ;
  }	
  public synchronized static Vector getUnitUserRegistration(String unitid) {
	  Statement  oStmt=null;
	  ResultSet  oRset =null;
	  Vector v=new Vector();
	  Connection oConn = null;
	  try {
		  oConn = ds1.getConnection();
		  oStmt = oConn.createStatement();
		  oRset=oStmt.executeQuery("select student_id,access_allowed_till,total_access_time from unit_student_association"+
				  " where unit_id='"+unitid+"'");
				  
		  while(oRset.next()) {
			  Vector v1=new Vector();
			  v1.addElement(oRset.getString(1));
			  v1.addElement(oRset.getString(2));
			  v1.addElement(oRset.getString(3));		  
			  v.addElement(v1);
		  }
				 
	  }
	  catch (SQLException e) {
		  System.out.println("Error due to SQL exception : parse()");
		  System.out.println("SQL exception : "+e.toString());
	  }
	  catch (Exception ex) {
		  System.out.println("Error due to exception : parse()");
		  ex.printStackTrace();
		  System.out.println(" printStack is :: " + ex.getMessage());
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  oStmt.close();
				  oRset.close();
				  oConn.close();
			  }
			  catch(SQLException e)
			  {
			  }
			
			
		  }
		
	  }
	  return v ;
  }	
  public synchronized static Vector getUnitGroupRegistration(String unitid) {
	  Statement  oStmt=null;
	  ResultSet  oRset =null;
	  Vector v=new Vector();
	  Connection oConn = null;
	  try {
		  oConn = ds1.getConnection();
		  oStmt = oConn.createStatement();
		  oRset=oStmt.executeQuery("select group_id,access_allowed_till,total_access_time from "+
				  " unit_group_association where unit_id='"+unitid+"'");
				  
		  while(oRset.next()) {
			  Vector v1=new Vector();
			  v1.addElement(oRset.getString(1));
			  v1.addElement(oRset.getString(2));
			  v1.addElement(oRset.getString(3));		  
			  v.addElement(v1);
		  }
				 
	  }
	  catch (SQLException e) {
		  System.out.println("Error due to SQL exception : parse()");
		  System.out.println("SQL exception : "+e.toString());
	  }
	  catch (Exception ex) {
		  System.out.println("Error due to exception : parse()");
		  ex.printStackTrace();
		  System.out.println(" printStack is :: " + ex.getMessage());
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  oStmt.close();
				  oRset.close();
				  oConn.close();
			  }
			  catch(SQLException e)
			  {
			  }
			
			
		  }
		
	  }
	  return v ;
  }	
  
  	//////////////////////////////////////////SUBIR//////////////////////////////////////////////
  
	/**
	* 	@deprecated  As of release 7.0, replaced by {@link #getUnitDetails()}
	*/
	
	@Deprecated public static Vector<Vector<String>> getUnitInfo() {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Vector<Vector<String>> unitDetails = new Vector<Vector<String>>();		
			
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select a.course_id, a.course_name,b.status, b.controll, b.created_by, b.date_created, b.modified_by, b.date_modified, b.self_regis, b.confirmation_reqd, b.confirmed_by"+
					" from course a,course_creation_details b"+
					" where a.course_id = b.course_id");
			while(rs.next()) {
				Vector<String> v1=new Vector<String>();
				v1.addElement(rs.getString(1));
				v1.addElement(rs.getString(2));
				v1.addElement(rs.getString(3));
				v1.addElement(rs.getString(4));
				v1.addElement(rs.getString(5));
				v1.addElement(rs.getString(6));
				v1.addElement(rs.getString(7));
				v1.addElement(rs.getString(8));
				v1.addElement(rs.getString(9));
				v1.addElement(rs.getString(10));
				v1.addElement(rs.getString(11));
					
				unitDetails.addElement(v1);
			}
					
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
					
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getUnitInfo()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getUnitInfo()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getUnitInfo()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getUnitInfo()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return unitDetails;		
	}

	/**
	* 	@deprecated  As of release 7.0, replaced by {@link #getUnitGroupRegistration(String)}
	*/
	
	@Deprecated public static Vector<Vector<String>> getUnitGroupAssnInfo(String unitid) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Vector<Vector<String>> unitGroupDetails = new Vector<Vector<String>>();		
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select group_id,access_allowed_till,total_access_time from "+
					" course_group_association where course_id='"+unitid+"'");
			while(rs.next()) {
				
				Vector<String> v1=new Vector<String>();
				v1.addElement(rs.getString(1));
				v1.addElement(rs.getString(2));
				v1.addElement(rs.getString(3));		  
				unitGroupDetails.addElement(v1);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getUnitGroupAssnInfo()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getUnitGroupAssnInfo()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getUnitGroupAssnInfo()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getUnitGroupAssnInfo()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return unitGroupDetails;		
	}
 
	/**
	* 	@deprecated  As of release 7.0, replaced by {@link #getUnitUserRegistration(String)}
	*/
	
	@Deprecated public static Vector<Vector<String>> getUnitUserRegnInfo(String unitid) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Vector<Vector<String>> unitUserDetails = new Vector<Vector<String>>();		
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select student_id,access_allowed_till,total_access_time from course_student_association"+
					" where course_id='"+unitid+"'");
			while(rs.next()) {
				
				Vector<String> v1=new Vector<String>();
				v1.addElement(rs.getString(1));
				v1.addElement(rs.getString(2));
				v1.addElement(rs.getString(3));		  
				unitUserDetails.addElement(v1);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getUnitUserRegnInfo()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getUnitUserRegnInfo()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getUnitUserRegnInfo()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getUnitUserRegnInfo()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return unitUserDetails;		
	}
	
	public static Vector getForumInfo() {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Vector forumDetails = new Vector();		
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select forum_id, forum_name, no_of_thread, no_of_message, last_message_posted, start_date, strself, confirm_by, confirmation_reqd, created_by from "+
					" forum");
			while(rs.next()) {
				
				String[] str = new String[10];
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = rs.getString(3);
				str[3] = rs.getString(4);
				str[4] = rs.getString(5);
				str[5] = rs.getString(6);
				str[6] = rs.getString(7);
				str[7] = rs.getString(8);
				str[8] = rs.getString(9);
				str[9] = rs.getString(10);
						  
				forumDetails.addElement(str);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getForumInfo()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getForumInfo()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getForumInfo()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getForumInfo()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return forumDetails;		
	}
	
	public static Vector<String[]> getCourseInfo() {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Vector<String[]> courseDetails = new Vector<String[]>();		
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select course_id, course_name, type, session, sdate, edate, createdby, createdon, description, cpoints, ttimes, cost, intructor, intructor1, file_name, file_name1 from "+
					" course_definition");
			while(rs.next()) {
				
				String[] str = new String[16];
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = rs.getString(3);
				str[3] = rs.getString(4);
				str[4] = rs.getString(5);
				str[5] = rs.getString(6);
				str[6] = rs.getString(7);
				str[7] = rs.getString(8);
				str[8] = rs.getString(9);
				str[9] = rs.getString(10);
				str[10] = rs.getString(11);
				str[11] = rs.getString(12);
				str[12] = rs.getString(13);
				str[13] = rs.getString(14);
				str[14] = rs.getString(15);
				str[15] = rs.getString(16);
						  
				courseDetails.addElement(str);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getCourseInfo()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getCourseInfo()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getCourseInfo()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getCourseInfo()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return courseDetails;		
	}
	
	public static Vector getForumRegnInfo(String forumid) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Vector forumRegnDetails = new Vector();		
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			//System.out.println("select user_id, registered_by, no_of_posts, no_of_views, last_posted_on, last_viewed_on"+
					//" from forum_registration_report where forum_id = '"+forumid+"'");
			rs = stmt.executeQuery("select user_id, registered_by, no_of_posts, no_of_views, last_posted_on, last_viewed_on"+
					" from forum_registration_report where forum_id = '"+forumid+"'");
			while(rs.next()) {
				
				String[] str = new String[6];
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = rs.getString(3);
				str[3] = rs.getString(4);
				str[4] = rs.getString(5);
				str[5] = rs.getString(6);
				
				forumRegnDetails.addElement(str);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getForumRegnInfo()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getForumRegnInfo()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getForumRegnInfo()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getForumRegnInfo()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return forumRegnDetails;		
	}
	
// 	public static Vector getForumRegnInfo() {
// 			
// 		Connection conn = null;
// 		Statement  stmt = null;
// 		ResultSet rs = null;
// 		Vector forumIDs = new Vector();		
// 		
// 		try {
// 			conn = ds1.getConnection();
// 			stmt = conn.createStatement();
// 			rs = stmt.executeQuery("select distinct forum_id"+
// 					" from forum_registration_report");
// 			while(rs.next()) {
// 				
// 				forumIDs.addElement(rs.getString(1));
// 			}
// 				
// 			rs.close();
// 			rs = null;
// 			stmt.close();
// 			stmt = null;
// 			conn.close();
// 			conn = null;
// 				
// 		} catch (SQLException sqlexception) {
// 			System.out.println("SQLException in DatabaseLayer.getForumRegnInfo()");
// 			System.out.println("The Error Message - " + sqlexception.getMessage());
// 			log.debug("SQLException in DatabaseLayer.getForumRegnInfo()"+ sqlexception);
// 		} catch (Exception ex) {
// 			System.out.println("Exception in DatabaseLayer.getForumRegnInfo()");
// 			System.out.println("The Error Message - " + ex.getMessage());
// 			log.debug("Exception in DatabaseLayer.getForumRegnInfo()"+ ex);
// 		} finally {
// 			if (rs != null) {
// 				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
// 				rs = null;
// 			}
// 			if (stmt != null) {
// 				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
// 				stmt = null;
// 			}
// 			if(conn != null) {
// 				try {
// 					conn.close();
// 				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
// 			}
// 		}
// 		return forumIDs;		
// 	}
	
	public static Vector<Vector<String>> getCourseRegnInfo() {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Vector<Vector<String>> courseIDs = new Vector<Vector<String>>();		
		Vector<String> courseIDsUserRegn = new Vector<String>();
		Vector<String> courseIDsGroupRegn = new Vector<String>();
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select distinct course_id"+
					" from user_course_registration");
			while(rs.next()) {
				
				courseIDsUserRegn.addElement(rs.getString(1));
				
			}
			courseIDs.addElement(courseIDsUserRegn);
			rs = stmt.executeQuery("select distinct course_id"+
					" from group_course_registration");
			while(rs.next()) {
				
				courseIDsGroupRegn.addElement(rs.getString(1));
				
			}
			courseIDs.addElement(courseIDsGroupRegn);	
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getCourseRegnInfo()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getCourseRegnInfo()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getCourseRegnInfo()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getCourseRegnInfo()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return courseIDs;		
	}
	
	public static Vector<String[]> getCourseUserRegnInfo(String courseid) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Vector<String[]> courseRegnDetails = new Vector<String[]>();		
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			//System.out.println("select student_id, registration_by, registration_date, modification_date"+
					//" from user_course_registration where course_id = '"+courseid+"'");
			rs = stmt.executeQuery("select student_id, registration_by, registration_date, modification_date"+
					" from user_course_registration where course_id = '"+courseid+"'");
			while(rs.next()) {
				
				String[] str = new String[4];
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = rs.getString(3);
				str[3] = rs.getString(4);
				
				courseRegnDetails.addElement(str);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getCourseUserRegnInfo()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getCourseUserRegnInfo()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getCourseUserRegnInfo()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getCourseUserRegnInfo()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return courseRegnDetails;		
	}
	
	public static Vector<String[]> getCourseGroupRegnInfo(String courseid) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Vector<String[]> courseGroupRegnDetails = new Vector<String[]>();		
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			//System.out.println("select group_id, registration_by, registration_date, access_allowed_till, total_access_time, modification_date"+
					//" from group_course_registration where course_id = '"+courseid+"'");
			rs = stmt.executeQuery("select group_id, registration_by, registration_date, access_allowed_till, total_access_time, modification_date"+
					" from group_course_registration where course_id = '"+courseid+"'");
			while(rs.next()) {
				
				String[] str = new String[6];
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = rs.getString(3);
				str[3] = rs.getString(4);
				str[4] = rs.getString(3);
				str[5] = rs.getString(4);
				
				courseGroupRegnDetails.addElement(str);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getCourseGroupRegnInfo()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getCourseGroupRegnInfo()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getCourseGroupRegnInfo()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getCourseGroupRegnInfo()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return courseGroupRegnDetails;		
	}
	
	public static Vector<String[]> getCourseCollectionInfo() {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Vector<String[]> collectionDetails = new Vector<String[]>();		
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select collection_id, collection_name, description, modifiedby, lastmodified from "+
					" course_collection_mgmt");
			while(rs.next()) {
				
				String[] str = new String[5];
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = rs.getString(3);
				str[3] = rs.getString(4);
				str[4] = rs.getString(5);
					  
				collectionDetails.addElement(str);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getCourseCollectionInfo()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getCourseCollectionInfo()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getCourseCollectionInfo()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getCourseCollectionInfo()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return collectionDetails;		
	}
	
	public static Vector<String[]> getCourseCollectionAssnInfo(String collectionid) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Vector<String[]> courseCollectionAssnDetails = new Vector<String[]>();		
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			System.out.println("select course_id, associatedby, dateassociated"+
					" from course_collection_asso where collection_id = '"+collectionid+"'");
			rs = stmt.executeQuery("select course_id, associatedby, dateassociated"+
					" from course_collection_asso where collection_id = '"+collectionid+"'");
			while(rs.next()) {
				
				String[] str = new String[3];
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = rs.getString(3);
				
				courseCollectionAssnDetails.addElement(str);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getCourseCollectionAssnInfo()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getCourseCollectionAssnInfo()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getCourseCollectionAssnInfo()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getCourseCollectionAssnInfo()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return courseCollectionAssnDetails;		
	}
	
	/*==  Import ==*/
	
	/**
	* 	@deprecated  As of release 7.0, replaced by {@link importData(ArrayList<String[]>, ArrayList<String[]>, Hashtable<String, ArrayList>, ArrayList<String[]>, Hashtable<String, ArrayList>, ArrayList<String[]>, Hashtable<String, ArrayList>, Hashtable<String, ArrayList>)}
	*/
	
	@Deprecated protected static boolean importSystemData(ArrayList<String[]> arUser, ArrayList<String[]> arGroup, Hashtable<String, ArrayList> htUserGroup, ArrayList<String[]> arUnit, Hashtable<String, ArrayList> htUnitUser, ArrayList<String[]> arCourse, Hashtable<String, ArrayList> htCourseUser, Hashtable<String, ArrayList> htCourseGroup) {
			
		Connection conn = null;
		PreparedStatement  pstmt = null;
		Statement  stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			conn = ds1.getConnection();
			conn.setAutoCommit(false);
			
			String userInsertStmt1 = "insert into student_details (student_id, sname, first_name, middle_name, gender, account_status) values(?,?,?,?,?,?)";
			String userInsertStmt2 = "insert into student_password (student_id, password) values(?,?)";
			String userInsertStmt3 = "insert into student_creation_details (student_id, date_student_created, student_created_by, last_modification_date) values(?,?,?,?)";
			String userInsertStmt4 = "insert into user_role (user_id, role_id) values(?,?)";
			String grouprInsertStmt = "insert into student_group (group_id,group_name, group_created_date, group_created_by) values(?,?,?,?)";
			String grouprUserInsertStmt = "insert into student_group_association (group_id, student_id) values(?,?)";
			String unitInsertStmt1 = "insert into course (course_id,course_name) values(?,?)";
			String unitInsertStmt2 = "insert into course_creation_details (course_id, controll, status, created_by, date_created, modified_by, date_modified, self_regis, confirmation_reqd, confirmed_by) values(?,?,?,?,?,?,?,?,?,?)";
			String unitUserInsertStmt = "insert into course_student_association (course_id, student_id, access_allowed_till) values(?,?,?)";
			String courseInsertStmt = "insert into course_definition (course_name, type, session, sdate, edate, createdby, createdon, description, cpoints, ttimes, cost, intructor, intructor1, file_name, file_name1) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String courseUserInsertStmt = "insert into user_course_registration (course_id, student_id, registration_by, registration_date, modification_date) values(?,?,?,?,?)";
			String courseGroupInsertStmt = "insert into group_course_registration (course_id, group_id, registration_by, registration_date, access_allowed_till, modification_date) values(?,?,?,?,?,?)";
			
			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(userInsertStmt1);
			for(String[] strUser : arUser) {
				//System.out.println("User ID: "+strUser[2]);
				if(strUser[2] != null && !strUser[2].equals("")) {
					if(!exists("student_details", "student_id", strUser[2])){
						pstmt.setString(1,strUser[2]);
						pstmt.setString(2,strUser[6]);
						pstmt.setString(3,strUser[0]);
						pstmt.setString(4,strUser[3]);
						pstmt.setString(5,strUser[1]);
						pstmt.setString(6,strUser[7]);
						pstmt.executeUpdate();
					}
				}
			}
			
			pstmt = conn.prepareStatement(userInsertStmt2);
			for(String[] strUser : arUser) {
				if(strUser[2] != null && !strUser[2].equals("")) {
					if(!exists("student_password", "student_id", strUser[2])){
						pstmt.setString(1,strUser[2]);
						pstmt.setString(2,strUser[4]);
						pstmt.executeUpdate();
					}
				}
			}
			
			pstmt = conn.prepareStatement(userInsertStmt3);
			for(String[] strUser : arUser) {
				if(strUser[2] != null && !strUser[2].equals("")) {
					if(!exists("student_creation_details", "student_id", strUser[2])){
						pstmt.setString(1,strUser[2]);
						pstmt.setString(2,strUser[8]);
						pstmt.setString(3,strUser[9]);
						pstmt.setString(4,strUser[10]);
						pstmt.executeUpdate();
					}
				}
			}
			
			pstmt = conn.prepareStatement(userInsertStmt4);
			for(String[] strUser : arUser) {
				if(strUser[5] != null && !strUser[5].equals("") && strUser[2] != null && !strUser[2].equals("")) {
					if(!exists("user_role", "user_id", strUser[2])){	
						pstmt.setString(1,strUser[2]);
						pstmt.setString(2,strUser[5]);
						pstmt.executeUpdate();
					}
				}
			}
			
			Hashtable htGroup = new Hashtable();
			pstmt = conn.prepareStatement(grouprInsertStmt);
			for(String[] strGroup : arGroup) {
				if(strGroup[0] != null && !strGroup[0].equals("")) {
					pstmt.setString(1,strGroup[0]);
					pstmt.setString(2,strGroup[1]);
					pstmt.setString(3,strGroup[2]);
					pstmt.setString(4,strGroup[3]);
					pstmt.executeUpdate();
					rs = stmt.executeQuery("select LAST_INSERT_ID() from student_group");
					rs.next();
					int maxgroupid = rs.getInt(1);
					//System.out.println("======Old Group ID: " +strGroup[0]+ " New Group ID: "+maxgroupid);
					htGroup.put(strGroup[0], maxgroupid);
				}
				
			}
			
			pstmt = conn.prepareStatement(grouprUserInsertStmt);
			Set s = htUserGroup.keySet();
			Iterator itr = s.iterator();
			while(itr.hasNext()) {
				String groupid = (String)itr.next();
				System.out.println("**groupid==data=:"+groupid);
				Integer newgroupid = (Integer)htGroup.get(groupid);
				if(newgroupid != null ) {
					for(String[] strUser : (ArrayList<String[]>)htUserGroup.get(groupid)) {
						if(strUser[0] != null && !strUser[0].equals("") ) {
							if(!exists(newgroupid, strUser[0])){
								System.out.println("===New Group Id:"+newgroupid+": User Id:"+strUser[0]);
// 								pstmt.setInt(1,newgroupid);	
								pstmt.setString(1,groupid);	
								pstmt.setString(2,strUser[0]);
								pstmt.executeUpdate();
							}
						}
					}
				}
			}
			
			Hashtable htUnit = new Hashtable();
			pstmt = conn.prepareStatement(unitInsertStmt1);
			for(String[] strUnit : arUnit) {
				if(strUnit[0] != null && !strUnit[0].equals("")) {
					pstmt.setString(1,strUnit[0]);
					pstmt.setString(2,strUnit[1]);
					pstmt.executeUpdate();
					rs = stmt.executeQuery("select LAST_INSERT_ID() from course");
					rs.next();
					int maxunitid = rs.getInt(1);
					//System.out.println("======Old Unit ID: " +strUnit[0]+ " New Unit ID: "+maxunitid);
					htUnit.put(strUnit[0], maxunitid);
				}
			}
			
			pstmt = conn.prepareStatement(unitInsertStmt2);
			for(String[] strUnit : arUnit) {
				if(strUnit[0] != null && !strUnit[0].equals("")) {
					Integer newunitid = (Integer)htUnit.get(strUnit[0]);
					if(!exists("course_creation_details", "course_id", newunitid)){	
// 						pstmt.setInt(1,newunitid);
						pstmt.setString(1,strUnit[0]);
						pstmt.setString(2,strUnit[2]);
						pstmt.setString(3,strUnit[3]);
						pstmt.setString(4,strUnit[4]);
						pstmt.setString(5,strUnit[5]);
						pstmt.setString(6,strUnit[6]);
						pstmt.setString(7,strUnit[7]);
						pstmt.setString(8,strUnit[8]);
						pstmt.setString(9,strUnit[9]);
						pstmt.setString(10,strUnit[10]);
						pstmt.executeUpdate();
					}
				}
			}
			
			pstmt = conn.prepareStatement(unitUserInsertStmt);
			Set sUU = htUnitUser.keySet();
			Iterator itrUU = sUU.iterator();
			while(itrUU.hasNext()) {
				String unitid = (String)itrUU.next();
				Integer newunitid = (Integer)htUnit.get(unitid);
				if(newunitid != null ) {
					for(String[] strUser : (ArrayList<String[]>)htUnitUser.get(unitid)) {
						if(strUser[0] != null && !strUser[0].equals("") ) {
							if(!exists("course_student_association", "course_id", "student_id", newunitid, strUser[0])){
								pstmt.setInt(1,newunitid);	
								pstmt.setString(2,strUser[0]);
								pstmt.setString(3,strUser[1]);	
								pstmt.executeUpdate();
							}
						}
					}
				}
			}
			
			Hashtable htCourse = new Hashtable();
			pstmt = conn.prepareStatement(courseInsertStmt);
			for(String[] strCourse : arCourse) {
				if(strCourse[0] != null && !strCourse[0].equals("")) {
					pstmt.setString(1,strCourse[1]);
					pstmt.setString(2,strCourse[15]);
					pstmt.setString(3,strCourse[12]);
					pstmt.setString(4,strCourse[13]);
					pstmt.setString(5,strCourse[6]);
					pstmt.setString(6,strCourse[3]);
					pstmt.setString(7,strCourse[4]);
					pstmt.setString(8,strCourse[5]);
					pstmt.setString(9,strCourse[2]);
					pstmt.setInt(10,Integer.parseInt(strCourse[14]));
					pstmt.setInt(11,Integer.parseInt(strCourse[11]));
					pstmt.setString(12,strCourse[9]);
					pstmt.setString(13,strCourse[10]);
					pstmt.setString(14,strCourse[7]);
					pstmt.setString(15,strCourse[8]);
					pstmt.executeUpdate();
					rs = stmt.executeQuery("select LAST_INSERT_ID() from course_definition");
					rs.next();
					int maxcourseid = rs.getInt(1);
					//System.out.println("======Old Course ID: " +strCourse[0]+ " New Course ID: "+maxcourseid);
					htCourse.put(strCourse[0], maxcourseid);
				}
			}
			
			pstmt = conn.prepareStatement(courseUserInsertStmt);
			Set sCU = htCourseUser.keySet();
			Iterator itrCU = sCU.iterator();
			while(itrCU.hasNext()) {
				String courseid = (String)itrCU.next();
				Integer newcourseid = (Integer)htCourse.get(courseid);
				if(newcourseid != null ) {
					for(String[] strUser : (ArrayList<String[]>)htCourseUser.get(courseid)) {
						if(strUser[0] != null && !strUser[0].equals("") ) {
							if(!exists("user_course_registration", "course_id", "student_id", newcourseid, strUser[0])){
								pstmt.setInt(1,newcourseid);	
								pstmt.setString(2,strUser[0]);
								pstmt.setString(3,strUser[2]);
								pstmt.setString(4,strUser[3]);
								pstmt.setString(5,strUser[1]);
								pstmt.executeUpdate();
							}
						}
					}
				}
			}
			
			pstmt = conn.prepareStatement(courseGroupInsertStmt);
			Set sCG = htCourseGroup.keySet();
			Iterator itrCG = sCG.iterator();
			while(itrCG.hasNext()) {
				String courseid = (String)itrCG.next();
				Integer newcourseid = (Integer)htCourse.get(courseid);
				if(newcourseid != null ) {
					for(String[] strGroup : (ArrayList<String[]>)htCourseGroup.get(courseid)) {
						if(strGroup[0] != null && !strGroup[0].equals("") ) {
							if(!exists("group_course_registration", "course_id", "group_id", newcourseid, strGroup[0])){
								pstmt.setInt(1,newcourseid);	
								pstmt.setInt(2,(Integer)htGroup.get(strGroup[0]));
								pstmt.setString(3,strGroup[2]);
								pstmt.setString(4,strGroup[3]);
								pstmt.setString(5,strGroup[1]);
								pstmt.setString(6,strGroup[4]);
								pstmt.executeUpdate();
							}
						}
					}
				}
			}
			
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			stmt.close();
			stmt = null;
			conn.commit();
			conn.setAutoCommit(true);
			conn.close();
			conn = null;
			flag = true;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.importSystemData()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.importSystemData()"+ sqlexception);
			sqlexception.printStackTrace();
			try {
				conn.rollback();
			} catch(Exception e) {}
			flag = false;
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.importSystemData()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.importSystemData()"+ ex);
			flag = false;
			ex.printStackTrace();
		} finally {
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				pstmt = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return flag;		
	}
	
	protected static boolean importData(ArrayList<String[]> arUser, ArrayList<String[]> arGroup, Hashtable<String, ArrayList> htUserGroup, ArrayList<String[]> arUnit, Hashtable<String, ArrayList> htUnitUser, ArrayList<String[]> arCourse, Hashtable<String, ArrayList> htCourseUser, Hashtable<String, ArrayList> htCourseGroup,ArrayList<String[]> aForum,ArrayList<String[]> aForumThread,ArrayList<String[]> aForumUReg,ArrayList<String[]> aForumGrReg,ArrayList<String[]> aNotice) {
			
		Connection conn = null;
		PreparedStatement  pstmt = null;
		Statement  stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			conn = ds1.getConnection();
			conn.setAutoCommit(false);
			
			String userInsertStmt1 = "insert into student_details (student_id, sname, first_name, middle_name, gender, account_status) values(?,?,?,?,?,?)";
			String userInsertStmt2 = "insert into student_password (student_id, password) values(?,?)";
			String userInsertStmt3 = "insert into student_creation_details (student_id, date_student_created, student_created_by, last_modification_date) values(?,?,?,?)";
			String userInsertStmt4 = "insert into user_role (user_id, role_id) values(?,?)";
			String grouprInsertStmt = "insert into student_group (group_id,group_name, group_created_date, group_created_by) values(?,?,?,?)";
			String grouprUserInsertStmt = "insert into student_group_association (group_id, student_id) values(?,?)";
			String unitInsertStmt1 = "insert into unit_details (unit_id,unit_name) values(?,?)";
			String unitInsertStmt2 = "insert into unit_creation_details (unit_id, controll, status, created_by, date_created, modified_by, date_modified, self_regis, confirmation_reqd, confirmed_by) values(?,?,?,?,?,?,?,?,?,?)";
			String unitUserInsertStmt = "insert into unit_student_association (unit_id, student_id, access_allowed_till) values(?,?,?)";
			String courseInsertStmt = "insert into course_definition (course_name, type, session, sdate, edate, createdby, createdon, description, cpoints, ttimes, cost, intructor, intructor1, file_name, file_name1) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String courseUserInsertStmt = "insert into user_course_registration (course_id, student_id, registration_by, registration_date, modification_date) values(?,?,?,?,?)";
			String courseGroupInsertStmt = "insert into group_course_registration (course_id, group_id, registration_by, registration_date, access_allowed_till, modification_date) values(?,?,?,?,?,?)";
			
			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(userInsertStmt1);
			for(String[] strUser : arUser) {
				System.out.println("User ID: "+strUser[2]);
				System.out.println("photo: "+strUser[11]);
				if(strUser[2] != null && !strUser[2].equals("")) {
					if(!exists("student_details", "student_id", strUser[2])){
						pstmt.setString(1,strUser[2]);
						pstmt.setString(2,strUser[6]);
						pstmt.setString(3,strUser[0]);
						pstmt.setString(4,strUser[3]);
						pstmt.setString(5,strUser[1]);
						pstmt.setString(6,strUser[7]);
						pstmt.executeUpdate();
					}
					
				}
			}
			
			pstmt = conn.prepareStatement(userInsertStmt2);
			for(String[] strUser : arUser) {
				System.out.println("strUser[4]: "+strUser[4]);
				if(strUser[2] != null && !strUser[2].equals("")) {
					if(!exists("student_password", "student_id", strUser[2])){
						pstmt.setString(1,strUser[2]);
						pstmt.setString(2,strUser[4]);
						pstmt.executeUpdate();
					}
				}
			}
			
			pstmt = conn.prepareStatement(userInsertStmt3);
			for(String[] strUser : arUser) {
				if(strUser[2] != null && !strUser[2].equals("")) {
					System.out.println("strUser[9]: "+strUser[9]);
					if(!exists("student_creation_details", "student_id", strUser[2])){
						pstmt.setString(1,strUser[2]);
						pstmt.setString(2,strUser[8]);
						pstmt.setString(3,strUser[9]);
						pstmt.setString(4,strUser[10]);
						pstmt.executeUpdate();
					}
				}
			}
			
			pstmt = conn.prepareStatement(userInsertStmt4);
			for(String[] strUser : arUser) {
				System.out.println("strUser[5]: "+strUser[5]);
				if(strUser[5] != null && !strUser[5].equals("") && !strUser[5].equals("role is not defined yet") && strUser[2] != null && !strUser[2].equals("")) {
					if(!exists("user_role", "user_id", strUser[2])){	
						pstmt.setString(1,strUser[2]);
						pstmt.setString(2,strUser[5]);
						pstmt.executeUpdate();
					}
				}
			}
			Hashtable htGroup = new Hashtable();
			pstmt = conn.prepareStatement(grouprInsertStmt);
			for(String[] strGroup : arGroup) {
				if(strGroup[0] != null && !strGroup[0].equals("")) {
					pstmt.setString(1,strGroup[0]);
					pstmt.setString(2,strGroup[1]);
					pstmt.setString(3,strGroup[2]);
					pstmt.setString(4,strGroup[3]);
					pstmt.executeUpdate();
					rs = stmt.executeQuery("select LAST_INSERT_ID() from student_group");
					rs.next();
					int maxgroupid = rs.getInt(1);
					//System.out.println("======Old Group ID: " +strGroup[0]+ " New Group ID: "+maxgroupid);
					htGroup.put(strGroup[0], maxgroupid);
				}
				
			}
			pstmt = conn.prepareStatement(grouprUserInsertStmt);
			Set s = htUserGroup.keySet();
			Iterator itr = s.iterator();
			while(itr.hasNext()) {
				String groupid = (String)itr.next();
				System.out.println("**groupid==data=:"+groupid);
				Integer newgroupid = (Integer)htGroup.get(groupid);
				if(newgroupid != null ) {
					for(String[] strUser : (ArrayList<String[]>)htUserGroup.get(groupid)) {
						if(strUser[0] != null && !strUser[0].equals("") ) {
							if(!exists(newgroupid, strUser[0])){
								System.out.println("===New Group Id:"+newgroupid+": User Id:"+strUser[0]);
// 								pstmt.setInt(1,newgroupid);	
								pstmt.setString(1,groupid);	
								pstmt.setString(2,strUser[0]);
								pstmt.executeUpdate();
							}
						}
					}
				}
			}
			Hashtable htUnit = new Hashtable();
			pstmt = conn.prepareStatement(unitInsertStmt1);
			for(String[] strUnit : arUnit) {
				if(strUnit[0] != null && !strUnit[0].equals("")) {
					pstmt.setString(1,strUnit[0]);
					pstmt.setString(2,strUnit[1]);
					pstmt.executeUpdate();
					rs = stmt.executeQuery("select LAST_INSERT_ID() from unit_details");
					rs.next();
					int maxunitid = rs.getInt(1);
					//System.out.println("======Old Unit ID: " +strUnit[0]+ " New Unit ID: "+maxunitid);
					htUnit.put(strUnit[0], maxunitid);
				}
			}
			pstmt = conn.prepareStatement(unitInsertStmt2);
			for(String[] strUnit : arUnit) {
				if(strUnit[0] != null && !strUnit[0].equals("")) {
					Integer newunitid = (Integer)htUnit.get(strUnit[0]);
					if(!exists("unit_creation_details", "unit_id", newunitid)){	
// 						pstmt.setInt(1,newunitid);
						pstmt.setString(1,strUnit[0]);
						pstmt.setString(2,strUnit[2]);
						pstmt.setString(3,strUnit[3]);
						pstmt.setString(4,strUnit[4]);
						pstmt.setString(5,strUnit[5]);
						pstmt.setString(6,strUnit[6]);
						pstmt.setString(7,strUnit[7]);
						pstmt.setString(8,strUnit[8]);
						pstmt.setString(9,strUnit[9]);
						pstmt.setString(10,strUnit[10]);
						pstmt.executeUpdate();
					}
				}
			}
			pstmt = conn.prepareStatement(unitUserInsertStmt);
			Set sUU = htUnitUser.keySet();
			Iterator itrUU = sUU.iterator();
			while(itrUU.hasNext()) {
				String unitid = (String)itrUU.next();
				Integer newunitid = (Integer)htUnit.get(unitid);
				if(newunitid != null ) {
					for(String[] strUser : (ArrayList<String[]>)htUnitUser.get(unitid)) {
						if(strUser[0] != null && !strUser[0].equals("") ) {
							if(!exists("unit_student_association", "unit_id", "student_id", newunitid, strUser[0])){
								pstmt.setInt(1,newunitid);	
								pstmt.setString(2,strUser[0]);
								pstmt.setString(3,strUser[1]);	
								pstmt.executeUpdate();
							}
						}
					}
				}
			}
			Hashtable htCourse = new Hashtable();
			pstmt = conn.prepareStatement(courseInsertStmt);
			for(String[] strCourse : arCourse) {
				if(strCourse[0] != null && !strCourse[0].equals("")) {
					pstmt.setString(1,strCourse[1]);
					pstmt.setString(2,strCourse[15]);
					pstmt.setString(3,strCourse[12]);
					pstmt.setString(4,strCourse[13]);
					pstmt.setString(5,strCourse[6]);
					pstmt.setString(6,strCourse[3]);
					pstmt.setString(7,strCourse[4]);
					pstmt.setString(8,strCourse[5]);
					pstmt.setString(9,strCourse[2]);
					pstmt.setInt(10,Integer.parseInt(strCourse[14]));
					pstmt.setInt(11,Integer.parseInt(strCourse[11]));
					pstmt.setString(12,strCourse[9]);
					pstmt.setString(13,strCourse[10]);
					pstmt.setString(14,strCourse[7]);
					pstmt.setString(15,strCourse[8]);
					pstmt.executeUpdate();
					rs = stmt.executeQuery("select LAST_INSERT_ID() from course_definition");
					rs.next();
					int maxcourseid = rs.getInt(1);
					//System.out.println("======Old Course ID: " +strCourse[0]+ " New Course ID: "+maxcourseid);
					htCourse.put(strCourse[0], maxcourseid);
				}
			}
			pstmt = conn.prepareStatement(courseUserInsertStmt);
			Set sCU = htCourseUser.keySet();
			Iterator itrCU = sCU.iterator();
			while(itrCU.hasNext()) {
				String courseid = (String)itrCU.next();
				Integer newcourseid = (Integer)htCourse.get(courseid);
				if(newcourseid != null ) {
					for(String[] strUser : (ArrayList<String[]>)htCourseUser.get(courseid)) {
						if(strUser[0] != null && !strUser[0].equals("") ) {
							if(!exists("user_course_registration", "course_id", "student_id", newcourseid, strUser[0])){
								pstmt.setInt(1,newcourseid);	
								pstmt.setString(2,strUser[0]);
								pstmt.setString(3,strUser[2]);
								pstmt.setString(4,strUser[3]);
								pstmt.setString(5,strUser[1]);
								pstmt.executeUpdate();
							}
						}
					}
				}
			}
			pstmt = conn.prepareStatement(courseGroupInsertStmt);
			Set sCG = htCourseGroup.keySet();
			Iterator itrCG = sCG.iterator();
			while(itrCG.hasNext()) {
				String courseid = (String)itrCG.next();
				Integer newcourseid = (Integer)htCourse.get(courseid);
				if(newcourseid != null ) {
					for(String[] strGroup : (ArrayList<String[]>)htCourseGroup.get(courseid)) {
						if(strGroup[0] != null && !strGroup[0].equals("") ) {
							if(!exists("group_course_registration", "course_id", "group_id", newcourseid, strGroup[0])){
								pstmt.setInt(1,newcourseid);	
								pstmt.setInt(2,(Integer)htGroup.get(strGroup[0]));
								pstmt.setString(3,strGroup[2]);
								pstmt.setString(4,strGroup[3]);
								pstmt.setString(5,strGroup[1]);
								pstmt.setString(6,strGroup[4]);
								pstmt.executeUpdate();
							}
						}
					}
				}
			}
			pstmt = conn.prepareStatement("insert into forum (forum_id, forum_name, no_of_thread, no_of_message,  created_by,start_date,strself,confirm_by,confirmation_reqd) values(?,?,?,?,?,?,?,?,?)");
			for(String[] strForum : aForum) {
				if(strForum[0] != null && !strForum[0].equals("")) {
					pstmt.setString(1,strForum[0]);
					pstmt.setString(2,strForum[1]);
					pstmt.setString(3,strForum[2]);
					pstmt.setString(4,strForum[3]);
// 					pstmt.setString(5,strForum[4]);
					pstmt.setString(5,strForum[5]);
					pstmt.setString(6,strForum[6]);
					pstmt.setString(7,strForum[7]);
					pstmt.setString(8,strForum[8]);
					pstmt.setString(9,strForum[9]);
					pstmt.executeUpdate();
					
				}
			}
			pstmt = conn.prepareStatement("insert into forum_thread (forum_id, forum_name, thread_id, thread_title, no_of_message, created_by,created_on,no_of_views,parent_thread) values(?,?,?,?,?,?,?,?,?)");
			for(String[] strForumThread : aForumThread) {
				if(strForumThread[0] != null && !strForumThread[0].equals("")) {
					pstmt.setString(1,strForumThread[0]);
					pstmt.setString(2,strForumThread[1]);
					pstmt.setString(3,strForumThread[2]);
					pstmt.setString(4,strForumThread[5]);
					pstmt.setString(5,"1");
					pstmt.setString(6,strForumThread[3]);
					pstmt.setString(7,strForumThread[4]);
					pstmt.setString(8,strForumThread[6]);
					pstmt.setString(9,strForumThread[7]);
					pstmt.executeUpdate();
					
				}
			}
			pstmt = conn.prepareStatement("insert into forum_message_body (forum_id, forum_name, message_id, thread_title, message, created_by,created_on,attachments) values(?,?,?,?,?,?,?,?)");
			for(String[] strForumThread : aForumThread) {
				if(strForumThread[0] != null && !strForumThread[0].equals("")) {
					pstmt.setString(1,strForumThread[0]);
					pstmt.setString(2,strForumThread[1]);
					pstmt.setString(3,strForumThread[2]);
					pstmt.setString(4,strForumThread[5]);
					pstmt.setString(5,strForumThread[8]);
					pstmt.setString(6,strForumThread[3]);
					pstmt.setString(7,strForumThread[4]);
					if(!strForumThread[9].equals("none")){
						pstmt.setString(8,strForumThread[9]);
					}
					else{
						pstmt.setString(8,"");
					}
					pstmt.executeUpdate();
					
				}
			}
			pstmt = conn.prepareStatement("insert into user_forum_association (forum_id, student_id,registered_by) values(?,?,?)");
			for(String[] strForumUReg : aForumUReg) {
				if(strForumUReg[0] != null && !strForumUReg[0].equals("")) {
					if(!exists("user_forum_association", "forum_id", "student_id", strForumUReg[0], strForumUReg[1])){
							pstmt.setString(1, strForumUReg[0]);	
							pstmt.setString(2, strForumUReg[1]);
							pstmt.setString(3, strForumUReg[2]);
							pstmt.executeUpdate();
					}
				}
			}
			pstmt = conn.prepareStatement("insert into group_forum_association (forum_id, group_id,registered_by) values(?,?,?)");
			for(String[] strForumGrReg : aForumGrReg) {
				if(strForumGrReg[0] != null && !strForumGrReg[0].equals("")) {
					if(!exists("group_forum_association", "forum_id", "group_id", strForumGrReg[0], strForumGrReg[1])){
						pstmt.setString(1, strForumGrReg[0]);	
						pstmt.setString(2, strForumGrReg[1]);
						pstmt.setString(3, strForumGrReg[2]);
						pstmt.executeUpdate();
					}
				}
			}
			pstmt = conn.prepareStatement("insert into bulletin_board (notice_id,heading,student_id,posted_on,group_id,status,body,attachments) values(?,?,?,?,?,?,?,?)");
			for(String[] strNotice : aNotice) {
				if(strNotice[0] != null && !strNotice[0].equals("")) {
					pstmt.setString(1,strNotice[0]);
					pstmt.setString(2,strNotice[1]);
					pstmt.setString(3,strNotice[2]);
					pstmt.setString(4,strNotice[3]);
					pstmt.setString(5,strNotice[4]);
					pstmt.setString(6,strNotice[5]);
					pstmt.setString(7,strNotice[6]);
					if(!strNotice[7].equals("none")){
						pstmt.setString(8,strNotice[7]);
					}
					else{
						pstmt.setString(8,"");
					}
					pstmt.executeUpdate();
					
				}
			}
			if(rs!=null)
			rs.close();
			rs = null;
			if(pstmt!=null)
			pstmt.close();
			pstmt = null;
			if(stmt!=null)
			stmt.close();
			stmt = null;
			conn.commit();
			conn.setAutoCommit(true);
			conn.close();
			conn = null;
			flag = true;	
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.importData()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.importData()"+ sqlexception);
			sqlexception.printStackTrace();
			try {
				conn.rollback();
			} catch(Exception e) {}
			flag = false;
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.importData()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.importData()"+ ex);
			flag = false;
			ex.printStackTrace();
		} finally {
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				pstmt = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return flag;		
	}
	
	public static boolean exists(int groupid, String userid) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		boolean flag = false;	
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from student_group_association where group_id = "+groupid+" and student_id = '"+userid+"'");
			while(rs.next()) {
				
				flag = true;
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.exists()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.exists()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.exists()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.exists()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return flag;		
	}
	
	public static boolean exists(String tablename, String fieldname, String fieldvalue) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		boolean flag = false;	
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from "+tablename+" where "+fieldname+" = '"+fieldvalue+"'");
			while(rs.next()) {
				
				flag = true;
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.exists()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.exists()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.exists()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.exists()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return flag;		
	}
	
	public static boolean exists(String tablename, String fieldname, int fieldvalue) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		boolean flag = false;	
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from "+tablename+" where "+fieldname+" = "+fieldvalue+"");
			while(rs.next()) {
				
				flag = true;
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.exists()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.exists()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.exists()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.exists()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return flag;		
	}
	
	public static boolean exists(String tablename, String field1, String field2, String field1value, String field2value) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		boolean flag = false;	
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from "+tablename+" where "+field1+" = '"+field1value+"' and  "+field2+" = '"+field2value+"'");
			while(rs.next()) {
				
				flag = true;
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.exists()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.exists()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.exists()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.exists()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return flag;		
	}
	
	public static boolean exists(String tablename, String field1, String field2, int field1value, String field2value) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		boolean flag = false;	
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from "+tablename+" where "+field1+" = "+field1value+" and  "+field2+" = '"+field2value+"'");
			while(rs.next()) {
				
				flag = true;
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.exists()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.exists()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.exists()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.exists()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return flag;		
	}
	
  //////////////////////////////////////////SUBIR//////////////////////////////////////////////
  /*======================================== Nayna =======================================*/
	public synchronized static void updateStudentPhoto(ArrayList<String[]> arUser) {
		
		Statement  oStmt=null;
		
		Connection oConn = null;
		
		
		try {
			oConn = ds1.getConnection();
			oConn.setAutoCommit(false);
			oStmt = oConn.createStatement();
			
			for(String[] strUser : arUser) {
				System.out.println("User ID: "+strUser[2]);
				System.out.println("photo: "+strUser[11]);
				if(strUser[2] != null && !strUser[2].equals("")) {
					
					
					
					if(strUser[11].equals("yes")){
					String strLocation = Host.getAdminPath();
					File inFile=new File(strLocation+File.separator+"image222.jpg");
					InputStream inStream= new FileInputStream(inFile);
					int size =(int)(inFile.length());
// 					System.out.println("size====="+size);
					
					PreparedStatement pstmt = oConn.prepareStatement("update student_details SET photo= ? where student_id=?");
					pstmt.setBinaryStream( 1, inStream, size);
					pstmt.setString(2,strUser[2]);
					pstmt.executeUpdate();
				
			
					}
					
				}
			}
			
			
			
			oConn.commit();
			oConn.setAutoCommit(true);
			
			
		}
		catch (SQLException e) {
			System.out.println("==SQLException===");
			e.printStackTrace();
		}
		catch (Exception ex) {
			System.out.println("==Exception===");
			ex.printStackTrace();
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					oStmt.close();
					oConn.close();
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}	 
					 
	}
	public static Vector<String[]> getForumInformation() {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Vector<String[]> forumDetails = new Vector<String[]>();		
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select a.forum_id, a.forum_name, a.no_of_message,count(b.thread_id),a.last_message_posted,a.created_by,a.start_date,a.strself,a.confirm_by,a.confirmation_reqd"+
					" from forum a,forum_thread b"+
					" where a.forum_id=b.forum_id and b.parent_thread='0' group by forum_id");
			while(rs.next()) {
				
				String[] str = new String[10];
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = rs.getString(3);
				str[3] = rs.getString(4);
				str[4] = rs.getString(5);
				str[5] = rs.getString(6);
				str[6] = rs.getString(7);
				str[7] = rs.getString(8);
				str[8] = rs.getString(9);
				str[9] = rs.getString(10);
				
				forumDetails.addElement(str);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getForumInformation()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getForumInformation()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getForumInformation()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getForumInformation()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return forumDetails;		
	}			
	public static Vector<String[]> getForumsThread(String forumid) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Statement  stmt1 = null;
		ResultSet rs1 = null;
		Vector<String[]> forumDetails = new Vector<String[]>();		
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			rs = stmt.executeQuery("select thread_id, thread_title"+
					" from forum_thread"+
					" where forum_id='"+forumid+"' and parent_thread='0'");
			while(rs.next()) {
				
				String[] str = new String[6];
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				int noofmessage=1;
				String parent_thread=str[0];
				String created_on="";
				String created_by="";
				String last_posted_mid="";
				//System.out.println("parent_thread===1===:"+parent_thread+"    noofmessage=="+noofmessage);
				do{
					rs1 = stmt1.executeQuery("select thread_id,created_on,created_by from forum_thread where forum_id='"+forumid+"' and parent_thread='"+parent_thread+"'");
					
					if(rs1.next()){
						noofmessage=noofmessage+1;
						parent_thread=rs1.getString(1);
						created_on=rs1.getString(2);
						created_by=rs1.getString(3);
						last_posted_mid=parent_thread;
					}
					else{
						parent_thread="";
					}
					//System.out.println("parent_thread===2===:"+parent_thread+"    noofmessage=="+noofmessage);
				}
				while(!parent_thread.equals(""));
				str[2] = created_on;	
				str[3] = created_by;
				Integer s=(Integer)noofmessage;	
				str[4] =s.toString();
				str[5] =last_posted_mid;
				forumDetails.addElement(str);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getForumsThread()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getForumsThread()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getForumsThread()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getForumsThread()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return forumDetails;		
	}	
	public static Vector<String[]> getForumsMessage(String forumid,String threadid) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Statement  stmt1 = null;
		ResultSet rs1 = null;
		Vector<String[]> forumDetails = new Vector<String[]>();		
		String parent_thread="0";
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			do{
// 				System.out.println("threadid===1===:"+threadid);
				rs = stmt.executeQuery("select a.created_by, a.created_on, a.message, a.attachments,a.thread_title, b.no_of_views"+
						" from forum_message_body a,forum_thread b "+
						" where a.forum_id=b.forum_id and a.message_id=b.thread_id and a.forum_id='"+forumid+"' and a.message_id='"+threadid+"'");
				while(rs.next()) {
				
					String[] str = new String[8];
					str[0] = threadid;
					str[1] = rs.getString(1);
					str[2] = rs.getString(2);
					str[3] = rs.getString(3);
					str[4] = rs.getString(4);
					str[5] = rs.getString(5);
					str[6] = rs.getString(6);
					str[7] = parent_thread;
					forumDetails.addElement(str);
				}
				parent_thread=threadid;
				rs1 = stmt1.executeQuery("select thread_id from forum_thread where forum_id='"+forumid+"' and parent_thread='"+threadid+"'");
					
				if(rs1.next()){
					threadid=rs1.getString(1);
					
				}
				else{
					threadid="";
				}
// 				System.out.println("threadid===2===:"+threadid);
			}
			while(!threadid.equals(""));
			
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getForumsMessage()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getForumsMessage()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getForumsMessage()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getForumsMessage()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return forumDetails;		
	}					
	public static Vector<String[]> getForumUserRegnInfo(String forumid) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Vector<String[]> forumIDs = new Vector<String[]>();		
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select student_id, registered_by"+
					" from user_forum_association where forum_id='"+forumid+"'");
			while(rs.next()) {
				String[] str = new String[2];
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				forumIDs.addElement(str);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getForumUserRegnInfo()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getForumUserRegnInfo()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getForumUserRegnInfo()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getForumUserRegnInfo()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return forumIDs;		
	}
	public static Vector<String[]> getForumGroupRegnInfo(String forumid) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Vector<String[]> forumIDs = new Vector<String[]>();		
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select group_id, registered_by"+
					" from group_forum_association where forum_id='"+forumid+"'");
			while(rs.next()) {
				String[] str = new String[2];
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				forumIDs.addElement(str);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getForumGroupRegnInfo()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getForumGroupRegnInfo()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getForumGroupRegnInfo()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getForumGroupRegnInfo()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return forumIDs;		
	}
	
	public static Vector<String[]> getNoticeInformation() {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		Vector<String[]> forumDetails = new Vector<String[]>();		
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select notice_id,heading,body,student_id,posted_on,attachments,status,group_id from bulletin_board");
			while(rs.next()) {
				
				String[] str = new String[9];
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = rs.getString(3);
				str[3] = rs.getString(4);
				str[4] = rs.getString(5);
				str[5] = rs.getString(6);
				str[6] = rs.getString(7);
				str[7] = rs.getString(8);
				
				forumDetails.addElement(str);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getNoticeInformation()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getNoticeInformation()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getNoticeInformation()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getNoticeInformation()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return forumDetails;		
	}			
	/*====================================Nayna end=====================================*/
	
	
	public static String getSelfRegsStatus(String strUserID) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		String strSelfRegsStatus="";
		String strStudentID="";	
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			log.debug("select student_id,strself from student_details where student_id='"+strUserID+"'");
			rs = stmt.executeQuery("select student_id,strself from student_details where student_id='"+strUserID+"'");
			while(rs.next()) 
			{
				strStudentID = rs.getString(1);
				strSelfRegsStatus = rs.getString(2);
				log.debug("**************strStudentID**********************"+strStudentID);
				log.debug("**************strSelfRegsStatus**********************"+strSelfRegsStatus);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getSelfRegsStatus()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getSelfRegsStatus()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getSelfRegsStatus()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getSelfRegsStatus()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return strSelfRegsStatus;		
	}
	
	public static String getCourseSelfRegsStatus(String strCourse) {
			
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		String strCourseSelfRegsStatus="";
		String strCourseID="";	
		
		try {
			conn = ds1.getConnection();
			stmt = conn.createStatement();
			log.debug("select course_id,self_regis from course_definition where course_id='"+strCourse+"'");
			rs = stmt.executeQuery("select course_id,self_regis from course_definition where course_id='"+strCourse+"'");
			while(rs.next()) 
			{
				strCourseID = rs.getString(1);
				strCourseSelfRegsStatus = rs.getString(2);
				log.debug("**************strCourseID**********************"+strCourseID);
				log.debug("**************strCourseSelfRegsStatus**********************"+strCourseSelfRegsStatus);
			}
				
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;
				
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in DatabaseLayer.getCourseSelfRegsStatus()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			log.debug("SQLException in DatabaseLayer.getCourseSelfRegsStatus()"+ sqlexception);
		} catch (Exception ex) {
			System.out.println("Exception in DatabaseLayer.getCourseSelfRegsStatus()");
			System.out.println("The Error Message - " + ex.getMessage());
			log.debug("Exception in DatabaseLayer.getCourseSelfRegsStatus()"+ ex);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { log.debug("Cannot close ResultSet"+ e); }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { log.debug("Cannot close Statement"+ e); }
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ce) {log.debug("Cannot close Connection"+ ce);}
			}
		}
		return strCourseSelfRegsStatus;		
	}
	
	
	/********************************** Partha 0n 06.02.10*************************/
	public synchronized static Vector getDateTimeRequestForUnit(String unit_id,String student_id) {
		
		Statement  oStmt=null;
		ResultSet  oRset=null;
		Vector vDateTime = null;
		Connection oConn = null;
    	
    	
		try	{
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			vDateTime = new Vector();
			System.out.println("------------select access_allowed_till,total_access_time from confirmation_requisition where id ='"+unit_id+"' and user_id='"+student_id+"' and entity_type='unit'");
			oRset = oStmt.executeQuery("select access_allowed_till,total_access_time from confirmation_requisition where id ='"+unit_id+"' and user_id='"+student_id+"' and entity_type='unit'");
			if (oRset.next()) {
				vDateTime.addElement(oRset.getString(1));
				vDateTime.addElement(oRset.getString(2));
			}
					

		}
		catch (SQLException e) {
			System.out.println("Error due to SQL exception : "+e.getMessage());
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
		}
		catch (Exception ex) {
			System.out.println(" error due to java.lang.exception");
			ex.printStackTrace();
			System.out.println(" printStack is :: " + ex.getMessage());
		}
    	//connMgr.freeConnection("mysql", oConn);
		finally{
			if(oConn!=null)
			{
				try
				{
					oRset.close();
					oStmt.close();
					oConn.close();
							
				}
				catch(SQLException e)
				{
				}
			
			
			}
		
		}
				
		return vDateTime;
			}
	
	
			public synchronized static String insertUnitUserAssociation(String strUsrId, String unit_id,String strCreatedBy, String strValidTill, String strTotalTime) {
				String msg="";
				Statement  oStmt=null;
				Statement  oStmt1=null;
				ResultSet  oRset=null;
				Connection oConn = null;
    	//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
    	
				try	{
					oConn = ds1.getConnection();
					oStmt = oConn.createStatement();
					oStmt1 = oConn.createStatement();
					
					System.out.println("------------------select * from unit_student_association where student_id = '"+strUsrId+"' and unit_id = '"+unit_id+"'");
					oRset = oStmt.executeQuery("select * from unit_student_association where student_id = '"+strUsrId+"' and unit_id = '"+unit_id+"'");
					if (oRset.next()) {
						msg="Already Registered";
					}
					else
					{
						if (strTotalTime.equals("") && strValidTill.equals("")) {
										 
							oStmt1.executeUpdate("insert into unit_student_association (student_id, unit_id,date_registration, "+
									"registered_by, access_allowed_till, total_access_time) values ('"+strUsrId+"','"+unit_id+
									"',sysdate(),'"+strCreatedBy+"',null,null)");
										 
							msg="Successfully Registered";			 
                           								 
						}
						if ((strTotalTime.equals("")) && (!strValidTill.equals(""))) {
							oStmt1.executeUpdate("insert into unit_student_association (student_id, unit_id, date_registration, "+
									"registered_by, access_allowed_till, total_access_time) values ('"+strUsrId+"','"+unit_id+
									"',sysdate(),'"+strCreatedBy+"','"+strValidTill+"',null)");
    								 
							msg="Successfully Registered";			 
    				
						
    								 
    								 
						}
						if ((!strTotalTime.equals("")) && (strValidTill.equals(""))) {
							int iTotalTime = Integer.parseInt(strTotalTime);
							oStmt1.executeUpdate("insert into unit_student_association (student_id, unit_id, date_registration, "+
									"registered_by, access_allowed_till, total_access_time) values ('"+strUsrId+"','"+unit_id+
									"',sysdate(),'"+strCreatedBy+"',null,"+iTotalTime+")");
    								 
							msg="Successfully Registered";	
    								 
    								 
						}
						if ((!strTotalTime.equals("")) && (!strValidTill.equals(""))) {
							int iTotalTime = Integer.parseInt(strTotalTime);
							oStmt1.executeUpdate("insert into unit_student_association (student_id, unit_id, date_registration, "+
									"registered_by, access_allowed_till, total_access_time) values ('"+strUsrId+"','"+unit_id+
									"',sysdate(),'"+strCreatedBy+"','"+strValidTill+"',"+iTotalTime+")");
    								 
    								 
							msg="Successfully Registered";		
    								 
    								 
    								 
						}
					}

					
				}
				catch (SQLException e) {
					e.printStackTrace();
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
				}
				catch (Exception ex) {
					//System.out.println(" error due to java.lang.exception");
					ex.printStackTrace();
					//System.out.println(" printStack is :: " + ex.getMessage());
				}
				finally{
					if(oConn!=null)
					{
						try
						{
							if(oStmt!=null)
								oStmt.close();
							if(oStmt1!=null)
								oStmt1.close();
							if(oRset!=null)
								oRset.close();
							oConn.close();
					
							
						}
						catch(SQLException e)
						{
						}
			
			
					}
		
				}
				
    	//connMgr.freeConnection("mysql", oConn);
				return msg;
			}
	
			public synchronized static String denyUnitRequest(String unit_id,String strUser_id ) 
			{
		
				String msg="";
				Statement  oStmt=null;
				Connection oConn=null;
    	
    	//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
    	
				try	{
					oConn = ds1.getConnection();
					oStmt = oConn.createStatement();
					System.out.println("--------------------update confirmation_requisition set status='Denied' where id ='"+unit_id+"' and user_id='"+strUser_id+"' and entity_type='unit'");
					oStmt.execute("update confirmation_requisition set status='Denied' where id ='"+unit_id+"' and user_id='"+strUser_id+"' and entity_type='unit'");
			
					msg = "Requisition denied";
				}
				catch (SQLException e) {
					e.printStackTrace();
					//System.out.println("Error due to SQL exception : "+e.getMessage());
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
				}
				catch (Exception ex) {
					//System.out.println(" error due to java.lang.exception");
					ex.printStackTrace();
					//System.out.println(" printStack is :: " + ex.getMessage());
				}
				finally
				{
					try
					{
						if(oStmt!=null)
							oStmt.close();
						if(oConn!=null)
							oConn.close();
					}
					catch(Exception e){}
				}
    	//connMgr.freeConnection("mysql", oConn);
				return msg;
			}
	
	
	
			public synchronized static void deleteUnitUserAssociation(String strUsrId, String unit_id) {
		
				Statement  oStmt=null;
				Statement  oStmt1=null;
				ResultSet  oRset=null;
				Connection oConn = null;
    	//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
    	
				try	{
					oConn = ds1.getConnection();
					oStmt = oConn.createStatement();
					oStmt1 = oConn.createStatement();
					
					System.out.println("------------------select * from unit_student_association where student_id = '"+strUsrId+"' and unit_id = '"+unit_id+"'");
					oRset = oStmt.executeQuery("select * from unit_student_association where student_id = '"+strUsrId+"' and unit_id = '"+unit_id+"'");
					if (oRset.next()) {
						oStmt1.executeUpdate("delete from unit_student_association where student_id='"+strUsrId+"' and unit_id='"+unit_id+"'");
					}

					
				}
				catch (SQLException e) {
					e.printStackTrace();
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
				}
				catch (Exception ex) {
					//System.out.println(" error due to java.lang.exception");
					ex.printStackTrace();
					//System.out.println(" printStack is :: " + ex.getMessage());
				}
				finally{
					if(oConn!=null)
					{
						try
						{
							if(oStmt!=null)
								oStmt.close();
							if(oStmt1!=null)
								oStmt1.close();
							if(oRset!=null)
								oRset.close();
							oConn.close();
					
							
						}
						catch(SQLException e)
						{
						}
			
			
					}
		
				}
				
    	//connMgr.freeConnection("mysql", oConn);
		
			}
			
			
			public synchronized static void deleteCourseUserAssociation(String strUsrId, String course_id) {
		
				Statement  oStmt=null;
				Statement  oStmt1=null;
				ResultSet  oRset=null;
				Connection oConn = null;
    	//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
    	
				try	{
					oConn = ds1.getConnection();
					oStmt = oConn.createStatement();
					oStmt1 = oConn.createStatement();
					
					System.out.println("------------------select * from usergroup_course_registration where student_id = '"+strUsrId+"' and course_id = '"+course_id+"'");
					oRset = oStmt.executeQuery("select * from usergroup_course_registration where student_id = '"+strUsrId+"' and course_id = '"+course_id+"'");
					if (oRset.next()) {
						oStmt1.executeUpdate("delete from usergroup_course_registration where student_id='"+strUsrId+"' and course_id='"+course_id+"'");
						oStmt1.executeUpdate("delete from user_course_registration where student_id='"+strUsrId+"' and course_id='"+course_id+"'");
					}

					
				}
				catch (SQLException e) {
					e.printStackTrace();
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
				}
				catch (Exception ex) {
					//System.out.println(" error due to java.lang.exception");
					ex.printStackTrace();
					//System.out.println(" printStack is :: " + ex.getMessage());
				}
				finally{
					if(oConn!=null)
					{
						try
						{
							if(oStmt!=null)
								oStmt.close();
							if(oStmt1!=null)
								oStmt1.close();
							if(oRset!=null)
								oRset.close();
							oConn.close();
					
							
						}
						catch(SQLException e)
						{
						}
			
			
					}
		
				}
				
    	//connMgr.freeConnection("mysql", oConn);
		
			}
			
			
			public synchronized static String getCourseSelfConfirmationStatus(String course_id,String student_id) {
		
				Statement  oStmt=null;
				ResultSet  oRset=null;
				String status = "";
				Connection oConn = null;
    	
    	
				try	{
					oConn = ds1.getConnection();
					oStmt = oConn.createStatement();
					
					
					oRset = oStmt.executeQuery("select status from confirmation_requisition where id ='"+course_id+"' and user_id='"+student_id+"' and entity_type='course'");
					if (oRset.next()) {
						status = oRset.getString(1);
						
					}
					

				}
				catch (SQLException e) {
					System.out.println("Error due to SQL exception : "+e.getMessage());
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
				}
				catch (Exception ex) {
					System.out.println(" error due to java.lang.exception");
					ex.printStackTrace();
					System.out.println(" printStack is :: " + ex.getMessage());
				}
    	//connMgr.freeConnection("mysql", oConn);
				finally{
					if(oConn!=null)
					{
						try
						{
							oRset.close();
							oStmt.close();
							oConn.close();
							
						}
						catch(SQLException e)
						{
						}
			
			
					}
		
				}
				
				return status;
			}
			
			
			public static byte[] getUserPhotoForProfile(String userId){
		
		//DBConnectionManager connMgr = DBConnectionManager.getInstance();
       		//Connection conn = connMgr.getConnection("mysql");
				byte[] _blob=null;
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs =null;
				
				
				ByteArrayOutputStream output = null;
				byte[] buf = null;
				String dirName = Host.getPortalImagePath() ;
				System.out.println("======dirName================="+dirName);
// 				File file = new File("../images/male.gif");
				
				try{
					conn = ds1.getConnection();	
					pstmt = conn.prepareStatement("SELECT photo FROM student_details WHERE student_id = ?");
					pstmt.setString(1, userId);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						Blob blob = rs.getBlob(1);
						System.out.println("rs has next");
						if(blob!=null)
						{
							int length = (int)blob.length();
							System.out.println("length is:"+length);
							_blob = blob.getBytes(1, length);
							System.out.println("Completed.Retreiving..");
						}
// 						
						else
						{
								// Addded on 06-Nov-2015 by Diptendu
							String defaultImage = "/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAHkAeQMBIgACEQEDEQH/xAAbAAEAAgMBAQAAAAAAAAAAAAAABQYBAgQDB//EADcQAAICAQEEBwQJBQEAAAAAAAABAgMEEQUhMVEGEiIyQWGxQnGR0RMjMzRSYnOhwTVTcoHhFP/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD7IAAAAAGTDem98CC2ltxxk6sJp+Dt09EBOTlGEetOSiubeh4POxE9Hk1a8usU622y6blbOU5PxkaAXquyFq61c4zXOL1Nyhwk4TUoNxkuDi9GTOz9u2QahmduGvfS3r38wLEDEZRnFShJSjJaprg0ZAAAAAAAAAABvTe+HiBCdIs5wSw6no2tZvy5FePXKueRk22y11nNvf4cjyAAAAAAJno9nOu5Ytj7Fj7Gr7r/AOljKIpOMlKL0aeqZeKLVdRXavbipfEDcAAAAAAAA0u+xsX5H6G5njufAChIHtl0vHybapcYza+R4gAAAAAAuWyf6bjf4Ip0IynJQgtZSeiXmXimtVU11r2IqPwA3AAAAAAAAAAEN0hwHbD/ANdUW5wWk4rxXMrhfeJCbT2GrHK3D0jLi63uT93ICug9Lse6ifVuqlB+aPMADaqudslGqEpt+EVqTezthSclZm7o/wBpPe/ewNej2DKVizLV2Yv6vX2nz/0WHgtNRFKMVGKSSWiSAAAAAAAAAGTDainKTSSWrbOfOzKsKl2XPjujFcZFWz9oX5s9bH1YeFceC+YE7k7dxat1Wt0vy7l8SMu2/lzf1ca614aLV/FkUAOqzaWZYtJ5E2nxS0SOfry62ur15moA6qto5lS0ryJxXLdodlO38uH2kK7F57n+xEgC0Yu3cW5qNutMvPeviSikmk4tNPhoUM68HaF+FL6p61+MHwfyAuIOfBzKs2n6Sp713ovimdAAAADS+6GPTO616QgtWble6SZbnbHFh3Ydqfm/BARebl2ZmRK2z3RX4VyPAAAAAAAAAAAAAOjBy7MO+NtXD2o/iXIuOPdDJphdU9YTWq+RRib6N5bjbLFm+zPWUPJgWEAAYnKNcHOfditWUe22V1s7Z96b1Zadv3fRbNmk9JTaiv5/ZMqbAAAAAAAAAAAAAABvRbKm6Fse9CSkmaAC91zjZXCyPCcVJe5mSP2Db9Ns6Cb1dbcH6r1JECv9KLN9FPk5Nft8yCJfpN9+r/SXqyIAAAAAAAAAAAAAAAAAnui9vayKeaU/4J8rPRj7/Z+k/VFlA//Z";
							_blob = org.apache.commons.codec.binary.Base64.decodeBase64(defaultImage.getBytes());

							/*							
							FileImageInputStream input = new FileImageInputStream(new File(dirName+"male.gif"));
							output = new ByteArrayOutputStream();
							buf = new byte[512];
							
							int numBytesRead = 0;
							while ((numBytesRead = input.read(buf)) != -1) 
							{
								output.write(buf, 0, numBytesRead);
							}

							_blob = output.toByteArray();
							output.close();
							input.close();
							System.out.println("from images");
*/							
						}
			
			
					}
					
		//conn.close();
 
				}catch(SQLException ex) {
					System.out.println("SQLException in getUserPhoto "+ex.getMessage());
					ex.printStackTrace();
				}
				catch(Exception e) {
					System.out.println("Exception in getUserPhoto "+e.getMessage());
					e.printStackTrace();
				}
				finally{
					try{
						pstmt.close();
						rs.close();		
						conn.close();}catch(SQLException e){}
				}
				System.out.println("------------- Returning-------------> "+_blob);
				return _blob;
			}
			
	                
			
			public synchronized static Vector getStudentDetails(String student_id) {
				Statement  oStmt=null;
				ResultSet  oRset =null;
				Vector v=new Vector();
				Connection oConn = null;
				try {
					oConn = ds1.getConnection();
					oStmt = oConn.createStatement();
					oRset=oStmt.executeQuery("select student_id,concat(first_name,' ',middle_name,' ',sname),gender,age,experience,edu_status,account_status,department,email_id,strself from student_details where student_id='" +student_id+ "'");
					System.out.println("select student_id,concat(first_name,' ',middle_name,' ',sname),gender,age,experience,edu_status,account_status,department,email_id,strself from student_details where student_id='" +student_id+ "'");
					if(oRset.next()) {
						String student =  oRset.getString(1);
						if(student==null)
							student="";
// 						String rank = oRset.getString(2);
						String name = oRset.getString(2);
						String gender = oRset.getString(3);
						String age = oRset.getString(4);
						String exp = oRset.getString(5);
						String edu = oRset.getString(6);
						String account_status = oRset.getString(7);
						String department = oRset.getString(8);
						String email_id = oRset.getString(9);
						String strself = oRset.getString(10);
						
// 						if(rank==null)
// 							rank="";
						if(name==null)
							name="";
						if(gender==null)
							gender="";
						if(age==null || age.equals(""))
							age="<font color=silver><i>No Age Specified.</i></font>";
						if(exp==null || exp.equals("")){
							exp="<font color=silver><i>No Experience Specified.</i></font>";}
						if(edu==null || edu.equals("")){
							edu="<font color=silver><i>No Qualification Specified.</i></font>";}
						if(account_status==null)
							account_status="";
						if(department==null || department.equals("")){
							department="<font color=silver><i>Not Yet Assigned.</i></font>";}
						if(email_id==null || email_id.equals("")){
							email_id="<font color=silver><i>No Email Specified.</i></font>";}
						
						if(strself==null)
							strself="";
						if(strself.equals('T'))
							strself="True";
						else
							strself="False";
						if(gender.equals("M"))
							gender="Male";
						else 
							gender="Female";
						
						v.addElement(student);
						//v.addElement(rank);
						v.addElement(name);
						v.addElement(gender);
						v.addElement(age);
						v.addElement(exp);
						v.addElement(edu);
						v.addElement(account_status);
						v.addElement(department);
						v.addElement(email_id);
						v.addElement(strself);
						
					}
				 
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				catch (Exception ex) {
					ex.printStackTrace();
					
				}
				finally{
					if(oConn!=null)
					{
						try
						{
							oStmt.close();
//							oRset.next();    
							oRset.close();
							oConn.close();
						}
						catch(SQLException e)
						{
						}
			
			
					}
		
				}
  
				return v ;
			}
			
			
			/************************************** End of Partha on 06.02.10 ***************/
		/*====================================Nayna Start====================================*/		
			public static synchronized String updateStudentEmailId(String studentId,String dept_id)
			{
      	
				Statement  oStmt=null;
				Statement  oStmt1=null;
				ResultSet  oRset1=null;
				Connection oConn = null;
				String status="1";
				String dept_name="";
				String email="";
				try
				{
					oConn = ds1.getConnection();
					oStmt = oConn.createStatement();
					oStmt1 = oConn.createStatement();
					oRset1=oStmt1.executeQuery("select dept_name from department_master where dept_id='"+dept_id+"' ");	
					if(oRset1.next()) {
						dept_name = oRset1.getString(1);
					}
					email=studentId+"."+dept_name+"@cmelms.edu";
			//System.out.println("update student_details set email_id='"+email+"' where student_id='"+studentId+"'"); 		
					oStmt.execute("update student_details set email_id='"+email+"' where student_id='"+studentId+"'");
			
				}
				catch(SQLException sqlexception)
				{
					status="0";
					System.out.println("Exception in updateStudentEmailId()");
					System.out.println("The Error Message - " + sqlexception.getMessage());
			
				}
				catch(Exception ex)
				{
					status="0";
					System.out.println("Exception in updateStudentEmailId()");
					System.out.println("The Error Message - " + ex.getMessage());
				}
				finally{
					if(oConn!=null)
					{
						try
						{
							oStmt.close();
							oConn.close();
							oStmt1.close();
							oRset1.close();
						}
						catch(SQLException e)
						{
							e.printStackTrace();
						}				
					}
		
				}
				return status;	
					
			}	
			/*====================================Nayna 05.03.2010====================================*/
			public static synchronized void updateStudentSerialNo(String studentId)
			{
      	
				Statement  oStmt=null;
				Statement  oStmt1=null;
				ResultSet  oRset1=null;
				Connection oConn = null;
				
				int preslno=0;
				int slno=0;
				try
				{
					oConn = ds1.getConnection();
					oStmt = oConn.createStatement();
					oStmt1 = oConn.createStatement();
					oRset1=oStmt1.executeQuery("select max(serial_no) from student_details");	
					if(oRset1.next()) {
						preslno = oRset1.getInt(1);
					}
					slno=preslno+1;		
					oStmt.execute("update student_details set serial_no='"+slno+"' where student_id='"+studentId+"'");
			
				}
				catch(SQLException sqlexception)
				{
					
					System.out.println("Exception in updateStudentSerialNo()");
					System.out.println("The Error Message - " + sqlexception.getMessage());
			
				}
				catch(Exception ex)
				{
					
					System.out.println("Exception in updateStudentSerialNo()");
					System.out.println("The Error Message - " + ex.getMessage());
				}
				finally{
					if(oConn!=null)
					{
						try
						{
							oStmt.close();
							oConn.close();
							oStmt1.close();
							oRset1.close();
						}
						catch(SQLException e)
						{
							e.printStackTrace();
						}				
					}
		
				}
				
					
			}		
							
			public static synchronized void updateallStudentSerialNo()
			{
      	
				Statement  oStmt=null;
				Statement  oStmt1=null;
				ResultSet  oRset1=null;
				Connection oConn = null;
				int slno=0;
				try
				{
					oConn = ds1.getConnection();
					oStmt = oConn.createStatement();
					oStmt1 = oConn.createStatement();
					oRset1=oStmt1.executeQuery("select student_id from student_details");	
					while(oRset1.next()) {
						String  student_id= oRset1.getString(1);
						slno=slno+1;
						//System.out.println("update student_details set serial_no='"+slno+"' where student_id='"+student_id+"'");
						oStmt.execute("update student_details set serial_no='"+slno+"' where student_id='"+student_id+"'");
					}			
				}
				catch(SQLException sqlexception)
				{
					
					System.out.println("Exception in updateallStudentSerialNo()");
					System.out.println("The Error Message - " + sqlexception.getMessage());
			
				}
				catch(Exception ex)
				{
					
					System.out.println("Exception in updateallStudentSerialNo()");
					System.out.println("The Error Message - " + ex.getMessage());
				}
				finally{
					if(oConn!=null)
					{
						try
						{
							oStmt.close();
							oConn.close();
							oStmt1.close();
							oRset1.close();
						}
						catch(SQLException e)
						{
							e.printStackTrace();
						}				
					}
		
				}
				
					
			}
			/*====================================Nayna end====================================*/						
			/**************************Start Soma 0n 12.11.2010**************************/
					
			public synchronized static Vector getDateTimeRequestForForum(String forum_id,String student_id) {
		
				Statement  oStmt=null;
				ResultSet  oRset=null;
				Vector vDateTime = null;
				Connection oConn = null;
    	
    	
				try	{
					oConn = ds1.getConnection();
					oStmt = oConn.createStatement();
					vDateTime = new Vector();
					System.out.println("------------select access_allowed_till,total_access_time from confirmation_requisition where id ='"+forum_id+"' and user_id='"+student_id+"' and entity_type='forum'");
					oRset = oStmt.executeQuery("select access_allowed_till,total_access_time from confirmation_requisition where id ='"+forum_id+"' and user_id='"+student_id+"' and entity_type='forum'");
					if (oRset.next()) {
						vDateTime.addElement(oRset.getString(1));
						vDateTime.addElement(oRset.getString(2));
					}
					

				}
				catch (SQLException e) {
					System.out.println("Error due to SQL exception inside getDateTimeRequestForForum: "+e.getMessage());
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
				}
				catch (Exception ex) {
					System.out.println(" error due to java.lang.exception inside getDateTimeRequestForForum");
					ex.printStackTrace();
					System.out.println(" printStack is :: " + ex.getMessage());
				}
    	//connMgr.freeConnection("mysql", oConn);
				finally{
					if(oConn!=null)
					{
						try
						{
							oRset.close();
							oStmt.close();
							oConn.close();
							
						}
						catch(SQLException e)
						{
						}
			
			
					}
		
				}
				
				return vDateTime;
		}
		
		public synchronized static String insertForumUserAssociation(String strUsrId, String forum_id,String strCreatedBy,String strValidTill,String strTotalTime) {
			String msg="";
			Statement  oStmt=null;
			Statement  oStmt1=null;
			ResultSet  oRset=null;
			Connection oConn = null;
    	//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
    	
			try	{
				oConn = ds1.getConnection();
				oStmt = oConn.createStatement();
				oStmt1 = oConn.createStatement();
					
				System.out.println("------------------select * from user_forum_association where student_id = '"+strUsrId+"' and forum_id = '"+forum_id+"'");
				oRset = oStmt.executeQuery("select * from user_forum_association where student_id = '"+strUsrId+"' and forum_id = '"+forum_id+"'");
				if (oRset.next()) {
					msg="Already Registered";
				}
				else
				{
			
					if (strTotalTime.equals("") && strValidTill.equals("")) {				 
						oStmt1.executeUpdate("insert into user_forum_association (student_id, forum_id,registered_by, access_allowed_till, total_access_time) values ('"+strUsrId+"','"+forum_id+"','"+strCreatedBy+"',null,null)");
										 
						msg="Successfully Registered";			 
                           								 
					}
				}

					
			}
			catch (SQLException e) {
				e.printStackTrace();
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
			}
			catch (Exception ex) {
					//System.out.println(" error due to java.lang.exception");
				ex.printStackTrace();
					//System.out.println(" printStack is :: " + ex.getMessage());
			}
			finally{
				if(oConn!=null)
				{
					try
					{
						if(oStmt!=null)
							oStmt.close();
						if(oStmt1!=null)
							oStmt1.close();
						if(oRset!=null)
							oRset.close();
						oConn.close();
					
							
					}
					catch(SQLException e)
					{
					}
			
			
				}
		
			}
				
    	//connMgr.freeConnection("mysql", oConn);
			return msg;
		}
		
		public synchronized static boolean confirmedForumRequest(String strCourseId,String strUser_id,String e_type) 
		{
		
			boolean flag = false;
			Statement  oStmt=null;
			Connection oConn = null;
		//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
    	
			try	{
				oConn = ds1.getConnection();
				oStmt = oConn.createStatement();
				oStmt.execute("update confirmation_requisition set status='Confirmed' where id ='"+strCourseId+"' and user_id='"+strUser_id+"' and entity_type='"+e_type+"'");
				log.debug("update confirmation_requisition set status='Confirmed' where id ='"+strCourseId+"' and user_id='"+strUser_id+"' and entity_type='"+e_type+"'");	
				flag = true;
			}
			catch (SQLException e) {
				System.out.println("Error due to SQL exception : "+e.getMessage());
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
			}
			catch (Exception ex) {
				System.out.println(" error due to java.lang.exception");
				ex.printStackTrace();
				System.out.println(" printStack is :: " + ex.getMessage());
			}
				
					 
			finally{
				if(oConn!=null)
				{
					try
					{
						oStmt.close();
						oConn.close();
					}
					catch(SQLException e)
					{
					}
			
			
				}
		
			}	 
					 
					 
					 
					 
					 
    	//connMgr.freeConnection("mysql", oConn);
			return flag;
		}
		
		public synchronized static void deleteForumUserAssociation(String strUsrId, String forum_id) {
		
			Statement  oStmt=null;
			Statement  oStmt1=null;
			ResultSet  oRset=null;
			Connection oConn = null;
   
			try	{
				oConn = ds1.getConnection();
				oStmt = oConn.createStatement();
				oStmt1 = oConn.createStatement();
					
				System.out.println("------------------select * from user_forum_association where student_id = '"+strUsrId+"' and forum_id = '"+forum_id+"'");
				oRset = oStmt.executeQuery("select * from user_forum_association where student_id = '"+strUsrId+"' and forum_id = '"+forum_id+"'");
				if (oRset.next()) {
					oStmt1.executeUpdate("delete from user_forum_association where student_id='"+strUsrId+"' and forum_id='"+forum_id+"'");
				}

					
			}
			catch (SQLException e) {
				e.printStackTrace();
      		
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			finally{
				if(oConn!=null)
				{
					try
					{
						if(oStmt!=null)
							oStmt.close();
						if(oStmt1!=null)
							oStmt1.close();
						if(oRset!=null)
							oRset.close();
						oConn.close();
					
							
					}
					catch(SQLException e)
					{
					}
			
			
				}
		
			}
				
    	//connMgr.freeConnection("mysql", oConn);
		
		}
		
		public synchronized static String denyForumRequest(String forum_id,String strUser_id) 
		{
		
			String msg="";
			Statement  oStmt=null;
			Connection oConn=null;
    	
    	//DBConnectionManager connMgr = DBConnectionManager.getInstance();
        //Connection oConn = connMgr.getConnection("mysql");
    	
			try	{
				oConn = ds1.getConnection();
				oStmt = oConn.createStatement();
				System.out.println("--------------------update confirmation_requisition set status='Denied' where id ='"+forum_id+"' and user_id='"+strUser_id+"' and entity_type='forum'");
				oStmt.execute("update confirmation_requisition set status='Denied' where id ='"+forum_id+"' and user_id='"+strUser_id+"' and entity_type='forum'");
			
				msg = "Requisition denied";
			}
			catch (SQLException e) {
				e.printStackTrace();
					//System.out.println("Error due to SQL exception : "+e.getMessage());
      		//int errCode=e.getErrorCode();
      		//String errMessage = e.getMessage();
			}
			catch (Exception ex) {
					//System.out.println(" error due to java.lang.exception");
				ex.printStackTrace();
					//System.out.println(" printStack is :: " + ex.getMessage());
			}
			finally
			{
				try
				{
					if(oStmt!=null)
						oStmt.close();
					if(oConn!=null)
						oConn.close();
				}
				catch(Exception e){}
			}
    	//connMgr.freeConnection("mysql", oConn);
			return msg;
		}

		public synchronized static boolean saveHomePageContent(String unitId,
				String homePageContent) {
			Statement oStmt = null;
			boolean isSuccess = false;
			Connection oConn = null;
			try {
				oConn = ds1.getConnection();
				oStmt = oConn.createStatement();
	/*
				oStmt.executeUpdate("delete from home_page_management where unit_id='"
						+ unitId + "'");

				int insertedRecord = oStmt
						.executeUpdate("insert into home_page_management(unit_id,home_title) values ('"
								+ unitId + "','" + homePageContent + "')");
				
	*/			
				String queryString =  "INSERT INTO home_page_management (unit_id, home_title) VALUES('"
						+ unitId + "','" + homePageContent + "') "
						+ "ON DUPLICATE KEY UPDATE home_title=VALUES(home_title)";
				int insertedRecord = oStmt.executeUpdate(queryString);
				
				if (insertedRecord > 0) {
					isSuccess = true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();

			} finally {
				if (oConn != null) {
					try {
						oStmt.close();
						oConn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			}

			return isSuccess;
		}

		public synchronized static String fetchHomePageContent(String unitId) {
			Statement oStmt = null;
			Connection oConn = null;
			ResultSet oRset = null;
			String existingHomePageContent = "";
			try {
				oConn = ds1.getConnection();
				oStmt = oConn.createStatement();

				oRset = oStmt
						.executeQuery("select h.home_title from home_page_management h where h.unit_id = '"
								+ unitId + "'");
				if (oRset != null && oRset.next()) {
					existingHomePageContent = oRset.getString(1);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();

			} finally {
				if (oConn != null) {
					try {
						oRset.close();
						oStmt.close();
						oConn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			}

			return existingHomePageContent;
		}
}
