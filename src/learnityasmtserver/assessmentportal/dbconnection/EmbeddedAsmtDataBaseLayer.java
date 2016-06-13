package learnityasmtserver.assessmentportal.dbconnection;

import javax.sql.*;
import oracle.xml.parser.v2.*;
import org.grlea.log.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import comv2.aunwesha.param.*;

public class EmbeddedAsmtDataBaseLayer
{
    private static final SimpleLogger log;
    public static DataSource ds;
    public static DataSource ds1;
    
    public static synchronized XMLDocument parse(final String course_id, final String table) {
        BufferedReader rd = null;
        XMLDocument theXMLDoc = null;
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select * from " + table + " where unit_id='" + course_id + "'");
            if (oRset.next()) {
                final InputStream l_lobinput = oRset.getAsciiStream(2);
                rd = new BufferedReader(new InputStreamReader(l_lobinput));
                final DOMParser theParser = new DOMParser();
                theParser.setValidationMode(false);
                theParser.parse((Reader)rd);
                theXMLDoc = theParser.getDocument();
                rd.close();
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Error due to SQL exception : parse()");
            EmbeddedAsmtDataBaseLayer.log.fatal("SQL exception : " + e.toString());
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        catch (Exception ex) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Error due to exception : parse()");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
            EmbeddedAsmtDataBaseLayer.log.fatal(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return theXMLDoc;
    }
    
    public static void AssessmentUsermodel(final String user_id, final String unit_id, final String sco_id, final String assessment_id, final String assessment_type, final String startTime) {
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("insert into assessment_usermodel(user_id,unit_id,sco_id,assessment_id,assessment_type,assessment_date,start_time,end_time)values('" + user_id + "','" + unit_id + "','" + sco_id + "','" + assessment_id + "','" + assessment_type + "',sysdate(),'" + startTime + "','')");
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("SQLException:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Exception:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static void AssessmentSectionData(final String Individual_Section_Id, final String startTime) {
        int assessment_data_record_id = 0;
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement oStmt1 = oConn.createStatement();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset1 = oStmt1.executeQuery("select max(assessment_data_record_id) from assessment_usermodel");
            while (oRset1.next()) {
                assessment_data_record_id = oRset1.getInt(1);
            }
            final boolean flag = statement.execute("insert into assessment_section_data(assessment_data_record_id,section_id,total_marks,marks_obtained,no_of_questions,no_of_questions_attempted,no_of_correct_ans,start_time,end_time)values('" + assessment_data_record_id + "','" + Individual_Section_Id + "','0','0','0','0','0','" + startTime + "','')");
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("SQLException:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Exception:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static synchronized void updateAssessmentSectionData(final String end_time) {
        int section_data_record_id = 0;
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement oStmt1 = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            final ResultSet oRset1 = oStmt1.executeQuery("select max(section_data_record_id) from assessment_section_data");
            while (oRset1.next()) {
                section_data_record_id = oRset1.getInt(1);
            }
            oStmt2.execute("update assessment_section_data set end_time='" + end_time + "' where section_data_record_id='" + section_data_record_id + "'");
            oStmt2.close();
        }
        catch (SQLException e) {
            EmbeddedAsmtDataBaseLayer.log.fatal("SQLException:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static Vector<String> getItemTypeItemText(final int assess_id) {
        Connection oConn = null;
        final Vector<String> vTextTitle = new Vector<String>();
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select a.assess_text, a.assess_title from qb_mgmt_item a where a.assess_id='" + assess_id + "'");
            while (oRset.next()) {
                final String assess_txt = oRset.getString(1);
                final String assess_title = oRset.getString(2);
                vTextTitle.addElement(assess_txt);
                vTextTitle.addElement(assess_title);
            }
            statement.close();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
            EmbeddedAsmtDataBaseLayer.log.debug("Exception==" + sqlexception);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            EmbeddedAsmtDataBaseLayer.log.debug("Exception==" + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vTextTitle;
    }
    
    public static Vector getAssess_id(final String qb_id) {
        Connection oConn = null;
        final Vector vAssess_id = new Vector();
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select a.assess_id from qb_mgmt_item a where a.qb_id='" + qb_id + "'");
            while (oRset.next()) {
                final Integer assess_id1 = oRset.getInt(1);
                final String assess_id2 = Integer.toString(assess_id1);
                vAssess_id.addElement(assess_id2);
            }
            statement.close();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
            EmbeddedAsmtDataBaseLayer.log.debug("Exception==" + sqlexception);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            EmbeddedAsmtDataBaseLayer.log.debug("Exception==" + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vAssess_id;
    }
    
    public static synchronized Vector getItemItemMetaData(final int assess_id) {
        Connection oConn = null;
        final Vector v = new Vector();
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("SELECT metadatafield_name, metadatafield_value FROM qb_metadata where  item_id='" + assess_id + "'");
            while (oRset.next()) {
                final String metadatafield_name = oRset.getString(1);
                final String metadatafield_value = oRset.getString(2);
                final Vector v2 = new Vector();
                v2.addElement(metadatafield_name);
                v2.addElement(metadatafield_value);
                v.addElement(v2);
            }
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.debug("Exception in modifyAssessmentMgmt()");
            EmbeddedAsmtDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            EmbeddedAsmtDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            EmbeddedAsmtDataBaseLayer.log.debug("getItemItemMetaData printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return v;
    }
    
    public String studentName(final String s_id) {
        Connection oConn = null;
        String s_name = "";
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset1 = statement.executeQuery("select concat(first_name,' ',middle_name,' ',sname) from student_details where student_id ='" + s_id + "'");
            if (oRset1.next()) {
                s_name = oRset1.getString(1);
            }
            statement.close();
            oRset1.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("SQLException:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Exception:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return s_name;
    }
    
    public static int GetInstance(final String s_id, final String u_id, final String sc_id, final String ass_id) {
        Connection oConn = null;
        int instance = 0;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset1 = statement.executeQuery("select instance from item_analysis where student_id ='" + s_id + "' and unit_id='" + u_id + "'and sco_id='" + sc_id + "' and assessment_id='" + ass_id + "' ");
            while (oRset1.next()) {
                instance = oRset1.getInt("instance");
            }
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("SQLException:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Exception:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return instance;
    }
    
    public static String[] getCourseStudentName(final String c_id, final String s_id) {
        Connection oConn = null;
        final String[] strName = new String[2];
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement1 = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final ResultSet resultset1 = statement1.executeQuery("select  concat(first_name,' ',middle_name,' ',sname) from student_details where student_id ='" + s_id + "'");
            EmbeddedAsmtDataBaseLayer.log.debug("========7==============");
            if (resultset1.next()) {
                EmbeddedAsmtDataBaseLayer.log.debug("=======8==============");
                strName[0] = resultset1.getString(1);
                EmbeddedAsmtDataBaseLayer.log.debug("========9==============" + strName[0]);
            }
            final ResultSet resultset2 = statement2.executeQuery("select unit_name from unit_details where unit_id = '" + c_id + "'");
            if (resultset2.next()) {
                EmbeddedAsmtDataBaseLayer.log.debug("========10==============");
                strName[1] = resultset2.getString(1);
                EmbeddedAsmtDataBaseLayer.log.debug("========11==============" + strName[1]);
            }
            statement1.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("SQLException:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Exception:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return strName;
    }
    
    public static String getLearning_Style(final String s) {
        String s2 = null;
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select learning_style from student_details where student_id='" + s + "'");
            while (resultset.next()) {
                s2 = resultset.getString(1);
            }
            resultset.close();
            statement.close();
        }
        catch (SQLException sqlexception) {
            final int i = sqlexception.getErrorCode();
            EmbeddedAsmtDataBaseLayer.log.fatal("Error due to SQL exception inside NewDataBaseLayer.getLearning_Style() : " + sqlexception.getMessage());
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("error due to java.lang.exception");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
            EmbeddedAsmtDataBaseLayer.log.fatal(" printStack is :: " + exception.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return s2;
    }
    
    public static void AssessmentUsage(final String s_id, final String u_id, final String ass_id, final String sec_id, final String start_time, final String end_time, final int instance) {
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("insert assessment_usage(student_id,unit_id,assessment_id,section_id,date, start_time,\tend_time,instance)values('" + s_id + "','" + u_id + "','" + ass_id + "','" + sec_id + "',sysdate(),'" + start_time + "','" + end_time + "' ,'" + instance + "')");
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("SQLException:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Exception:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static void ItemAnalysis(final String s_id, final String u_id, final String sc_id, final String ass_id, final String sec_id, final String ite_id, final String resul, final String resul_data, final double item_obtain_marks, final int instance) {
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("insert item_analysis(student_id,unit_id,sco_id,assessment_id,section_id,item_id,result,result_data,item_obtain_marks,date,instance)values('" + s_id + "','" + u_id + "','" + sc_id + "','" + ass_id + "','" + sec_id + "','" + ite_id + "','" + resul + "','" + resul_data + "','" + item_obtain_marks + "',sysdate(),'" + instance + "')");
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("SQLException:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Exception:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static void Assessment_item_data(final String itemId, final String strCorrectIncorrect, final String ans, final String item_score, final double item_obtain_marks, final String Individual_Section_Id) {
        int assessment_data_record_id = 0;
        int section_data_record_id = 0;
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement oStmt1 = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset1 = oStmt1.executeQuery("select max(assessment_data_record_id) from assessment_usermodel");
            while (oRset1.next()) {
                assessment_data_record_id = oRset1.getInt(1);
            }
            final ResultSet oRset2 = oStmt2.executeQuery("select section_data_record_id from assessment_section_data where assessment_data_record_id='" + assessment_data_record_id + "' and section_id='" + Individual_Section_Id + "'");
            while (oRset2.next()) {
                section_data_record_id = oRset2.getInt(1);
            }
            System.out.println("insert into assessment_item_data(section_data_record_id,item_id,result_status,result_data,item_marks,marks_obtained)values('" + section_data_record_id + "','" + itemId + "','" + strCorrectIncorrect + "','" + ans + "','" + item_score + "','" + item_obtain_marks + "')");
            final boolean flag = statement.execute("insert into assessment_item_data(section_data_record_id,item_id,result_status,result_data,item_marks,marks_obtained)values('" + section_data_record_id + "','" + itemId + "','" + strCorrectIncorrect + "','" + ans + "','" + item_score + "','" + item_obtain_marks + "')");
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("SQLException:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Exception:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static void essay_type_data(final String item_id, final String item_text, final String ans, final int total_mark, final String Individual_Section_Id) {
        int assessment_data_record_id = 0;
        int section_data_record_id = 0;
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement oStmt1 = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset1 = oStmt1.executeQuery("select max(assessment_data_record_id) from assessment_usermodel");
            while (oRset1.next()) {
                assessment_data_record_id = oRset1.getInt(1);
            }
            final ResultSet oRset2 = oStmt2.executeQuery("select section_data_record_id from assessment_section_data where assessment_data_record_id='" + assessment_data_record_id + "' and section_id='" + Individual_Section_Id + "'");
            while (oRset2.next()) {
                section_data_record_id = oRset2.getInt(1);
            }
            final boolean flag = statement.execute("insert into essay_type_data(section_data_record_id,item_id,item_text,result_data,item_marks,marks_obtained)values('" + section_data_record_id + "','" + item_id + "','" + item_text + "','" + ans + "','" + total_mark + "','0')");
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("SQLException:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Exception:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static synchronized void updateAssessmentUsermodel(final String user_id, final String end_time) {
        int assessment_data_record_id = 0;
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement oStmt1 = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            final ResultSet oRset1 = oStmt1.executeQuery("select max(assessment_data_record_id) from assessment_usermodel");
            while (oRset1.next()) {
                assessment_data_record_id = oRset1.getInt(1);
            }
            oStmt2.execute("update assessment_usermodel set end_time='" + end_time + "' where assessment_data_record_id='" + assessment_data_record_id + "' and user_id='" + user_id + "'");
            oStmt2.close();
        }
        catch (SQLException e) {
            EmbeddedAsmtDataBaseLayer.log.fatal("SQLException:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static synchronized void updateAllAssessmentSectionData(final String Individual_Section_Id, final int total_marks, final double marks_obtained, final int no_of_questions, final int no_of_questions_attempted, final int no_of_correct_ans) {
        int assessment_data_record_id = 0;
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement oStmt1 = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            final ResultSet oRset1 = oStmt1.executeQuery("select max(assessment_data_record_id) from assessment_usermodel");
            while (oRset1.next()) {
                assessment_data_record_id = oRset1.getInt(1);
            }
            oStmt2.execute("update assessment_section_data set total_marks='" + total_marks + "', marks_obtained='" + marks_obtained + "', no_of_questions='" + no_of_questions + "', no_of_questions_attempted='" + no_of_questions_attempted + "',no_of_correct_ans='" + no_of_correct_ans + "' where assessment_data_record_id='" + assessment_data_record_id + "' and section_id='" + Individual_Section_Id + "'");
            oStmt2.close();
        }
        catch (SQLException e) {
            EmbeddedAsmtDataBaseLayer.log.fatal("SQLException:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static synchronized void scormUpdation(final String c_id, final String s_id, final String t_id, final String marksobtained, final String totalmarks, final String passmarks, final String status) {
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("update userscoinfo set minscore = '" + passmarks + "' ,rawscore = '" + marksobtained + "' ,maxscore = '" + totalmarks + " ' ,lessonstatus = '" + status + " '  where user_id ='" + s_id + "' and unit_id = '" + c_id + "' and sco_id = '" + t_id + "'");
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Inside ReportDataBaseLayer scormUpdation(), Error due to SQL exception!");
            final int i = sqlexception.getErrorCode();
            final String s9 = sqlexception.getMessage();
            EmbeddedAsmtDataBaseLayer.log.fatal("The Error is :- " + s9);
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Inside NewDataBaseLayer InsertIntoLearnerLoginInfo(), exception!");
            EmbeddedAsmtDataBaseLayer.log.fatal(" printStack is :: " + exception.getMessage());
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static void confirmationResult(final String s_id, final String c_id, final String t_id, final String t_name, final String a_name, final int no, final int total, final double ob, final int cutValue, final String status) {
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("insert into confirmation_result(student_id,course_id,topic_id,topic_name,assessment_name,confirmation_id,total_marks,marks_obtained, pass_marks,status, login_datetime)values('" + s_id + "','" + c_id + "','" + t_id + "','" + t_name + "','" + a_name + "'," + no + "," + total + "," + ob + "," + cutValue + ",'" + status + "',sysdate())");
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("SQLException:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Exception:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    static {
        log = new SimpleLogger((Class)EmbeddedAsmtDataBaseLayer.class);
        EmbeddedAsmtDataBaseLayer.ds = CoreAdminInitHostInfo.ds;
        EmbeddedAsmtDataBaseLayer.ds1 = CoreAdminInitHostInfo.ds1;
    }
}
