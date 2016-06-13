package learnityasmtserver.assessmentadmin.dbconnection;

import javax.sql.*;
import java.util.*;
import org.grlea.log.*;
import java.sql.*;
import oracle.xml.parser.v2.*;
import java.io.*;
import java.text.*;
import comv2.aunwesha.param.*;

public class EmbeddedAsmtDataBaseLayer
{
    public static final SimpleLogger log;
    public static DataSource ds1;
    
    public static Vector<Vector<String>> getAssessmentList(final String c_id) {
        final Vector<Vector<String>> vListUsage = new Vector<Vector<String>>(3, 3);
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select assessment_data_record_id, assessment_id  from assessment_usermodel where unit_id ='" + c_id + "'");
            while (oRset.next()) {
                final Vector<String> vList = new Vector<String>(3, 3);
                final String assessment_data_record_id = oRset.getString(1);
                final String assessment_id = oRset.getString(2);
                vList.addElement(assessment_data_record_id);
                vList.addElement(assessment_id);
                if (vListUsage.size() != 0) {
                    final int a = vListUsage.size() - 1;
                    final Vector<String> v = vListUsage.elementAt(a);
                    final String a_id = v.elementAt(1);
                    if (a_id.equals(assessment_id)) {
                        continue;
                    }
                    vListUsage.addElement(vList);
                }
                else {
                    vListUsage.addElement(vList);
                }
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
        return vListUsage;
    }
    
    public static Vector course_StudentList(final String c_id) {
        final Vector vRegisteredList = new Vector(2, 2);
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select a.student_id  from unit_student_association a, unit_details b where  a.unit_id =b.unit_id and a.unit_id ='" + c_id + "'");
            while (resultset.next()) {
                final String[] strcal = new String[2];
                final String strid = resultset.getString(1);
                final ResultSet resultset2 = statement2.executeQuery("select   account_status ,concat(first_name,' ',middle_name,' ',sname) from student_details where student_id ='" + strid + "'");
                if (resultset2.next()) {
                    strcal[0] = strid;
                    strcal[1] = resultset2.getString(2);
                    vRegisteredList.addElement(strcal);
                }
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
        return vRegisteredList;
    }
    
    public static synchronized void updateScormTable(final String unit_id, final String assessment_id, final String student_id) {
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final Statement statement3 = oConn.createStatement();
            final Statement statement4 = oConn.createStatement();
            final Statement statement5 = oConn.createStatement();
            final Statement statement6 = oConn.createStatement();
            int sum = 0;
            int item_data_record_id = 0;
            int assessment_data_record_id = 0;
            int section_data_record_id = 0;
            String status = "";
            String assessmentid = "";
            ResultSet oRset5 = statement6.executeQuery("select assessment_id from assessment_usermodel where assessment_data_record_id='" + assessment_id + "'");
            if (oRset5.next()) {
                assessmentid = oRset5.getString(1);
            }
            final ResultSet oRset2 = statement2.executeQuery("select max(c.item_data_record_id) from assessment_usermodel a,assessment_section_data b, essay_type_data c where a.assessment_data_record_id=b.assessment_data_record_id and b.section_data_record_id=c.section_data_record_id and a.unit_id='" + unit_id + "'and a.user_id='" + student_id + "'");
            if (oRset2.next()) {
                item_data_record_id = Integer.parseInt(oRset2.getString(1));
            }
            final ResultSet oRset3 = statement3.executeQuery("select marks_obtained,section_data_record_id from essay_type_data where item_data_record_id='" + item_data_record_id + "'");
            if (oRset3.next()) {
                final int num = Integer.parseInt(oRset3.getString(1));
                section_data_record_id = Integer.parseInt(oRset3.getString(2));
                sum += num;
            }
            final ResultSet oRset4 = statement.executeQuery("select a.assessment_data_record_id from assessment_section_data a, essay_type_data b where a.section_data_record_id=b.section_data_record_id and item_data_record_id='" + item_data_record_id + "';");
            if (oRset4.next()) {
                assessment_data_record_id = Integer.parseInt(oRset4.getString(1));
            }
            oRset5 = statement4.executeQuery("select marks_obtained from assessment_section_data where assessment_data_record_id='" + assessment_data_record_id + "' and section_data_record_id!='" + section_data_record_id + "';");
            if (oRset5.next()) {
                final int num2 = Integer.parseInt(oRset5.getString(1));
                sum += num2;
            }
            final ResultSet oRset6 = statement5.executeQuery("select minscore from userscoinfo where user_id='" + student_id + "';");
            if (oRset6.next()) {
                final int MinScore = Integer.parseInt(oRset6.getString(1));
                if (MinScore <= sum) {
                    status = "Passed";
                }
                else {
                    status = "Failed";
                }
            }
            final boolean flag = statement.execute("update userscoinfo set rawscore = '" + sum + "',lessonstatus='" + status + "'  where user_id ='" + student_id + "' and unit_id = '" + unit_id + "'");
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Inside ReportDataBaseLayer updateScormTable, Error due to SQL exception!");
            final int i = sqlexception.getErrorCode();
            final String s9 = sqlexception.getMessage();
            EmbeddedAsmtDataBaseLayer.log.fatal("The Error is :- " + s9);
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Inside ReportDataBaseLayer updateScormTable, exception!");
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
    }
    
    public static synchronized void updateScormTableAllUser(final String unit_id, final String assessment_id) {
        final Vector<String> vUser = new Vector<String>();
        int cc = 0;
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select user_id from assessment_usermodel where unit_id ='" + unit_id + "'");
            while (oRset.next()) {
                final String userid = oRset.getString(1);
                if (vUser.size() != 0) {
                    for (int i = 0; i < vUser.size(); ++i) {
                        final String u_id = vUser.elementAt(i);
                        if (u_id.equals(userid)) {
                            cc = 1;
                        }
                    }
                    if (cc != 1) {
                        updateScormTable(unit_id, assessment_id, userid);
                    }
                }
                else {
                    updateScormTable(unit_id, assessment_id, userid);
                }
                vUser.addElement(userid);
            }
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Inside ReportDataBaseLayer updateScormTableAllUser, Error due to SQL exception!");
            final int j = sqlexception.getErrorCode();
            final String s9 = sqlexception.getMessage();
            EmbeddedAsmtDataBaseLayer.log.fatal("The Error is :- " + s9);
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Inside ReportDataBaseLayer updateScormTableAllUser, exception!");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
            EmbeddedAsmtDataBaseLayer.log.fatal(" printStack is updateScormTableAllUser :: " + exception.getMessage());
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
    
    public static synchronized void updateScormTableAllAssess(final String unit_id, final String student_id) {
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select assessment_data_record_id from assessment_usermodel where unit_id ='" + unit_id + "' and user_id='" + student_id + "'");
            while (oRset.next()) {
                final String assessment_id = oRset.getString(1);
                updateScormTable(unit_id, assessment_id, student_id);
            }
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Inside ReportDataBaseLayer updateScormTableAllAssess, Error due to SQL exception!");
            final int i = sqlexception.getErrorCode();
            final String s9 = sqlexception.getMessage();
            EmbeddedAsmtDataBaseLayer.log.fatal("The Error is :- " + s9);
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Inside ReportDataBaseLayer updateScormTableAllAssess, exception!");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
            EmbeddedAsmtDataBaseLayer.log.fatal(" printStack is updateScormTableAllAssess :: " + exception.getMessage());
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
    
    public static synchronized void updateScormTableAll(final String unit_id) {
        Connection oConn = null;
        final Vector<String> vUser = new Vector<String>();
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select assessment_data_record_id,user_id from assessment_usermodel where unit_id ='" + unit_id + "'");
            while (oRset.next()) {
                int cc = 0;
                final String assessment_id = oRset.getString(1);
                final String student_id = oRset.getString(2);
                if (vUser.size() != 0) {
                    for (int i = 0; i < vUser.size(); ++i) {
                        final String u_id = vUser.elementAt(i);
                        if (u_id.equals(student_id)) {
                            cc = 1;
                        }
                    }
                    if (cc != 1) {
                        updateScormTable(unit_id, assessment_id, student_id);
                    }
                }
                else {
                    updateScormTable(unit_id, assessment_id, student_id);
                }
                vUser.addElement(student_id);
            }
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Inside ReportDataBaseLayer updateScormTableAllAssess, Error due to SQL exception!");
            final int j = sqlexception.getErrorCode();
            final String s9 = sqlexception.getMessage();
            EmbeddedAsmtDataBaseLayer.log.fatal("The Error is :- " + s9);
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Inside ReportDataBaseLayer updateScormTableAllAssess, exception!");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
            EmbeddedAsmtDataBaseLayer.log.fatal(" printStack is updateScormTableAllAssess :: " + exception.getMessage());
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
    
    public static synchronized Vector getCourseDetailsList1() {
        Vector vCourseList = null;
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            Statement oStmt = oConn.createStatement();
            ResultSet oRset = oStmt.executeQuery("select count(*)  from unit_details, unit_creation_details where unit_details.unit_id=unit_creation_details.unit_id");
            int l_i = 0;
            while (oRset.next()) {
                l_i = oRset.getInt(1);
            }
            oRset.close();
            oStmt.close();
            if (l_i <= 0) {
                return null;
            }
            vCourseList = new Vector();
            oStmt = oConn.createStatement();
            oRset = oStmt.executeQuery("select a.unit_id, a.unit_name, date_format(b.date_created,\"%M %e, %Y %H:%i\"), b.created_By, b.modified_by, date_format(b.date_modified,\"%M %e, %Y %H:%i\"), b.status, b.email_id, b.forum_name, b.chat_name, b.calender_name, b.controll  from unit_details a, unit_creation_details b where a.unit_id=b.unit_id");
            while (oRset.next()) {
                final Vector vCourse = new Vector();
                vCourse.addElement(oRset.getString(1));
                vCourse.addElement(oRset.getString(2));
                vCourse.addElement(oRset.getString(3));
                vCourse.addElement(oRset.getString(4));
                vCourse.addElement(oRset.getString(5));
                vCourse.addElement(oRset.getString(6));
                vCourse.addElement(oRset.getString(7));
                final Statement oStmt2 = oConn.createStatement();
                final ResultSet oRset2 = oStmt2.executeQuery("select count(*)  from unit_student_association where unit_id = '" + oRset.getString(1) + "'");
                oRset2.next();
                vCourse.addElement(oRset2.getString(1));
                vCourse.addElement(oRset.getString(8));
                vCourse.addElement(oRset.getString(9));
                vCourse.addElement(oRset.getString(10));
                vCourse.addElement(oRset.getString(11));
                vCourse.addElement(oRset.getString(12));
                vCourseList.addElement(vCourse);
                oRset2.close();
                oStmt2.close();
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            EmbeddedAsmtDataBaseLayer.log.fatal("SQLException:");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        catch (Exception ex) {
            EmbeddedAsmtDataBaseLayer.log.fatal("getCourseDetailsList1: error due to SQL exception");
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
        return vCourseList;
    }
    
    public static Vector getEssayInfo(final String item_rc_id) {
        final Vector vListUsage = new Vector(3, 3);
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select item_text,result_data,item_marks from essay_type_data where item_data_record_id='" + item_rc_id + "'");
            while (oRset.next()) {
                vListUsage.addElement(oRset.getString(1));
                vListUsage.addElement(oRset.getString(2));
                vListUsage.addElement(oRset.getString(3));
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
        return vListUsage;
    }
    
    public static synchronized void essayUserModel(final int ob, final String item_rc_id) {
        int section_data_record_id = 0;
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select section_data_record_id from essay_type_data where item_data_record_id ='" + item_rc_id + "'");
            while (oRset.next()) {
                section_data_record_id = Integer.parseInt(oRset.getString(1));
            }
            final boolean flag = statement.execute("update assessment_section_data set marks_obtained = '" + ob + "', no_of_questions_attempted='1' where section_data_record_id = '" + section_data_record_id + " '");
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Inside NewDataBaseLayer essayQuestionUpdation(), Error due to SQL exception!");
            final int i = sqlexception.getErrorCode();
            final String s9 = sqlexception.getMessage();
            EmbeddedAsmtDataBaseLayer.log.fatal("The Error is :- " + s9);
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Inside NewDataBaseLayer essayQuestionUpdation(), exception!");
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
    }
    
    public static synchronized void essayQuestionUpdation(final int ob, final String item_rc_id) {
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("update essay_type_data set marks_obtained = " + ob + " where item_data_record_id = '" + item_rc_id + " '");
            statement.close();
        }
        catch (SQLException sqlexception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Inside NewDataBaseLayer essayQuestionUpdation(), Error due to SQL exception!");
            final int i = sqlexception.getErrorCode();
            final String s9 = sqlexception.getMessage();
            EmbeddedAsmtDataBaseLayer.log.fatal("The Error is :- " + s9);
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            EmbeddedAsmtDataBaseLayer.log.fatal("Inside NewDataBaseLayer essayQuestionUpdation(), exception!");
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
    }
    
    public static Vector getSectionList(final String c_id, final String assessment_data_record_id) {
        Connection oConn = null;
        final Vector vListUsage = new Vector(3, 3);
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select a.section_id from assessment_section_data a, essay_type_data b where a.section_data_record_id=b.section_data_record_id and a.assessment_data_record_id ='" + assessment_data_record_id + "'");
            while (oRset.next()) {
                final String s_id = oRset.getString(1);
                vListUsage.addElement(s_id);
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
        return vListUsage;
    }
    
    public static Vector getusermodelforAssessment(final String c_id) {
        Connection oConn = null;
        final Vector vassessmentdetails1 = new Vector(1, 1);
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select a.user_id, a.assessment_date,a.assessment_data_record_id,concat(b.first_name,' ',b.middle_name,' ',b.sname),assessment_id from assessment_usermodel a,student_details b where a.user_id=b.student_id and a.unit_id ='" + c_id + "'");
            while (oRset.next()) {
                final String user_id = oRset.getString(1);
                final String user_name = oRset.getString(4);
                final String assessment_date = oRset.getString(2);
                final String assessment_data_record_id = oRset.getString(3);
                final String assessment_id = oRset.getString(5);
                final ResultSet oRset2 = statement2.executeQuery("select section_id, total_marks, marks_obtained, no_of_questions, no_of_questions_attempted, no_of_correct_ans, section_data_record_id from assessment_section_data where assessment_data_record_id='" + assessment_data_record_id + "'");
                while (oRset2.next()) {
                    final String[] strcal1 = { user_id, null, assessment_date, oRset2.getString(4), oRset2.getString(5), oRset2.getString(6), oRset2.getString(2), oRset2.getString(3), null, user_name, null };
                    final String total_marks = oRset2.getString(2);
                    final int total_marks2 = Integer.parseInt(total_marks);
                    final String marks_obtained = oRset2.getString(3);
                    final double marks_obtained2 = Double.parseDouble(marks_obtained);
                    final double per = Double.valueOf(Math.floor(marks_obtained2 * 100.0 / total_marks2));
                    final String per2 = Double.toString(per);
                    strcal1[8] = per2;
                    strcal1[1] = oRset2.getString(1);
                    strcal1[10] = assessment_id;
                    vassessmentdetails1.addElement(strcal1);
                }
            }
            statement2.close();
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
        return vassessmentdetails1;
    }
    
    public static synchronized XMLDocument parse(final String course_id, final String table) {
        BufferedReader rd = null;
        XMLDocument theXMLDoc = null;
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select * from " + table + " where course_id='" + course_id + "'");
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
    
    public static Vector getUserScoInfoResult(final String s, final String s1, final String s2, final String s3) {
        Connection connection = null;
        final Vector vector = new Vector(2, 2);
        try {
            connection = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final Statement statement2 = connection.createStatement();
            if (s2 != null && !s2.equals("") && s3 != null && !s3.equals("")) {
                final String s4 = parseDate(s2);
                final String s5 = parseDate(s3);
                final ResultSet resultset3 = statement.executeQuery("select student_id, pass_marks,total_marks,marks_obtained,status,date_format( login_datetime,\"%M %e, %Y %H:%i\"),confirmation_id from confirmation_result where course_id ='" + s + "' and topic_id ='" + s1 + "' and login_datetime >'" + s4 + "' and login_datetime < '" + s5 + "'");
                while (resultset3.next()) {
                    final String[] as3 = new String[9];
                    final String s6 = resultset3.getString(1);
                    final ResultSet resultset2 = statement2.executeQuery("select   account_status ,concat(first_name,' ',middle_name,' ',sname),edu_status from student_details where student_id ='" + s6 + "'");
                    if (resultset2.next()) {
                        final String s7 = resultset2.getString(1);
                        as3[0] = s6;
                        as3[2] = resultset2.getString(2);
                        as3[1] = resultset2.getString(3);
                        as3[3] = resultset3.getString(2);
                        as3[4] = resultset3.getString(3);
                        as3[5] = resultset3.getString(4);
                        if (!as3[5].equals("")) {
                            final float f3 = Float.parseFloat(as3[5]);
                            final DecimalFormat decimalformat3 = new DecimalFormat("#.00");
                            final String s8 = decimalformat3.format(f3);
                            as3[5] = s8;
                        }
                        as3[6] = resultset3.getString(5);
                        as3[7] = resultset3.getString(6);
                        as3[8] = resultset3.getString(7);
                        vector.addElement(as3);
                    }
                }
            }
            else if ((s2 == null || s2.equals("")) && s3 != null && !s3.equals("")) {
                final String s9 = parseDate(s3);
                final ResultSet resultset3 = statement.executeQuery("select student_id, pass_marks,total_marks,marks_obtained,status,date_format( login_datetime,\"%M %e, %Y %H:%i\"),confirmation_id from confirmation_result where course_id ='" + s + "' and topic_id ='" + s1 + "' and  login_datetime < '" + s9 + "'");
                while (resultset3.next()) {
                    final String[] as2 = new String[9];
                    final String s10 = resultset3.getString(1);
                    final ResultSet resultset4 = statement2.executeQuery("select   account_status ,concat(first_name,' ',middle_name,' ',sname) ,edu_status from student_details where student_id ='" + s10 + "'");
                    if (resultset4.next()) {
                        final String s11 = resultset4.getString(1);
                        as2[0] = s10;
                        as2[2] = resultset4.getString(2);
                        as2[1] = resultset4.getString(3);
                        as2[3] = resultset3.getString(2);
                        as2[4] = resultset3.getString(3);
                        as2[5] = resultset3.getString(4);
                        if (!as2[5].equals("")) {
                            final float f2 = Float.parseFloat(as2[5]);
                            final DecimalFormat decimalformat2 = new DecimalFormat("#.00");
                            final String s12 = decimalformat2.format(f2);
                            as2[5] = s12;
                        }
                        as2[6] = resultset3.getString(5);
                        as2[7] = resultset3.getString(6);
                        as2[8] = resultset3.getString(7);
                        vector.addElement(as2);
                    }
                }
            }
            else if (s2 != null && !s2.equals("") && (s3 == null || s3.equals(""))) {
                final String s13 = parseDate(s2);
                final ResultSet resultset5 = statement.executeQuery("select student_id, pass_marks,total_marks,marks_obtained,status,date_format( login_datetime,\"%M %e, %Y %H:%i\"),confirmation_id from confirmation_result where course_id ='" + s + "' and topic_id ='" + s1 + "' and login_datetime >'" + s13 + "'");
                while (resultset5.next()) {
                    final String[] as3 = new String[9];
                    final String s14 = resultset5.getString(1);
                    final ResultSet resultset6 = statement2.executeQuery("select   account_status ,concat(first_name,' ',middle_name,' ',sname) ,edu_status from student_details where student_id ='" + s14 + "'");
                    if (resultset6.next()) {
                        final String s15 = resultset6.getString(1);
                        as3[0] = s14;
                        as3[2] = resultset6.getString(2);
                        as3[1] = resultset6.getString(3);
                        as3[3] = resultset5.getString(2);
                        as3[4] = resultset5.getString(3);
                        as3[5] = resultset5.getString(4);
                        if (!as3[5].equals("")) {
                            final float f3 = Float.parseFloat(as3[5]);
                            final DecimalFormat decimalformat3 = new DecimalFormat("#.00");
                            final String s16 = decimalformat3.format(f3);
                            as3[5] = s16;
                        }
                        as3[6] = resultset5.getString(5);
                        as3[7] = resultset5.getString(6);
                        as3[8] = resultset5.getString(7);
                        vector.addElement(as3);
                    }
                }
            }
            else if ((s2 == null || s2.equals("")) && (s3 == null || s3.equals(""))) {
                final ResultSet resultset7 = statement.executeQuery("select student_id, pass_marks,total_marks,marks_obtained,status,date_format( login_datetime,\"%M %e, %Y %H:%i\"),confirmation_id from confirmation_result where course_id ='" + s + "' and topic_id ='" + s1 + "'");
                while (resultset7.next()) {
                    final String[] as4 = new String[9];
                    final String s17 = resultset7.getString(1);
                    final ResultSet resultset8 = statement2.executeQuery("select account_status ,concat(first_name,' ',middle_name,' ',sname) ,edu_status from student_details where student_id ='" + s17 + "'");
                    if (resultset8.next()) {
                        final String s18 = resultset8.getString(1);
                        as4[0] = s17;
                        as4[2] = resultset8.getString(2);
                        as4[1] = resultset8.getString(3);
                        as4[3] = resultset7.getString(2);
                        as4[4] = resultset7.getString(3);
                        as4[5] = resultset7.getString(4);
                        if (!as4[5].equals("")) {
                            final float f4 = Float.parseFloat(as4[5]);
                            final DecimalFormat decimalformat4 = new DecimalFormat("#.00");
                            final String s19 = decimalformat4.format(f4);
                            as4[5] = s19;
                        }
                        as4[6] = resultset7.getString(5);
                        as4[7] = resultset7.getString(6);
                        as4[8] = resultset7.getString(7);
                        vector.addElement(as4);
                    }
                }
            }
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
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vector;
    }
    
    public static String parseDate(final String s) {
        final int end = s.length();
        final String s2 = s.substring(0, 2);
        final String s3 = s.substring(2, 5);
        final String s4 = s.substring(6, 10);
        final String s5 = s.substring(10, end);
        final String strDate = s4 + s3 + "-" + s2 + s5;
        return strDate;
    }
    
    public static String getCourseName(final String s) {
        String s2 = s;
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select unit_name from unit_details where unit_id = '" + s + "'");
            if (resultset.next()) {
                s2 = resultset.getString(1);
            }
            else {
                s2 = s;
            }
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
        return s2;
    }
    
    public static Vector getGrList(final String c_id) {
        Connection oConn = null;
        final Vector vRegisteredList = new Vector(2, 2);
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            EmbeddedAsmtDataBaseLayer.log.debug("select a.group_id ,a.group_name from student_group a,unit_group_association b where a.group_id=b.group_id and  b.unit_id='" + c_id + "'");
            final ResultSet resultset = statement.executeQuery("select a.group_id ,a.group_name from student_group a,unit_group_association b where a.group_id=b.group_id and  b.unit_id='" + c_id + "'");
            while (resultset.next()) {
                final String[] str = { resultset.getString(1), resultset.getString(2) };
                vRegisteredList.addElement(str);
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
        return vRegisteredList;
    }
    
    public static Vector getGrUserList(final String c_id, final String gr_id) {
        Connection oConn = null;
        final Vector vGroupUserList = new Vector();
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select a.student_id ,concat(a.first_name,' ',a.middle_name,' ',a.sname) from student_details a ,student_group_association b where b.student_id=a.student_id and b.group_id='" + gr_id + "'");
            while (resultset.next()) {
                final String[] str = { resultset.getString(1), resultset.getString(2) };
                vGroupUserList.addElement(str);
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
        return vGroupUserList;
    }
    
    public static Vector getUserList(final String c_id) {
        Connection oConn = null;
        final Vector vRegisteredList = new Vector(2, 2);
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select a.student_id ,concat(a.first_name,' ',a.middle_name,' ',a.sname) from student_details a ,unit_student_association b where b.student_id=a.student_id and b.unit_id='" + c_id + "'");
            while (resultset.next()) {
                final String[] str = { resultset.getString(1), resultset.getString(2) };
                vRegisteredList.addElement(str);
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
        return vRegisteredList;
    }
    
    public static Vector getInstanceList(final String user_id, final String unit_id, final String ass_ident, final String section_id) {
        Connection oConn = null;
        final Vector vRegisteredList = new Vector(2, 2);
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select a.instance from item_analysis a where student_id='" + user_id + "' and unit_id='" + unit_id + "' and assessment_id='" + ass_ident + "' and section_id='" + section_id + "' group by instance");
            while (resultset.next()) {
                final String[] str = { resultset.getString(1) };
                vRegisteredList.addElement(str);
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
        return vRegisteredList;
    }
    
    public static Vector getItemDetail(final String user_id, final String unit_id, final String ass_ident, final String section_id, final String ins) {
        Connection oConn = null;
        final Vector vItemList = new Vector(2, 2);
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select a.item_id,a.result_data,a.result from item_analysis a where student_id='" + user_id + "' and unit_id='" + unit_id + "' and assessment_id='" + ass_ident + "' and section_id='" + section_id + "' and instance='" + ins + "'");
            while (resultset.next()) {
                final String[] str = { resultset.getString(1), resultset.getString(2), resultset.getString(3) };
                vItemList.addElement(str);
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
        return vItemList;
    }
    
    public static String getQuestionBankId(final String item_id) {
        Connection oConn = null;
        String QuestionBank_Id = "";
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select a.qb_id from qb_mgmt_item a where a.assess_id='" + item_id + "'");
            while (oRset.next()) {
                QuestionBank_Id = oRset.getString(1);
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
        return QuestionBank_Id;
    }
    
    public static String getItemTot(final String user_id, final String unit_id, final String ass_ident, final String section_id, final String ins) {
        Connection oConn = null;
        String str = "";
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select count(item_id) from item_analysis  where student_id='" + user_id + "' and unit_id='" + unit_id + "' and assessment_id='" + ass_ident + "' and section_id='" + section_id + "' and instance='" + ins + "'");
            while (resultset.next()) {
                str = resultset.getString(1);
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
        return str;
    }
    
    public static String getItemAttempt(final String user_id, final String unit_id, final String ass_ident, final String section_id, final String ins) {
        Connection oConn = null;
        String str = "";
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select count(item_id) from item_analysis  where student_id='" + user_id + "' and unit_id='" + unit_id + "' and assessment_id='" + ass_ident + "' and section_id='" + section_id + "' and instance='" + ins + "' and (result='correct' OR result='incorrect')");
            while (resultset.next()) {
                str = resultset.getString(1);
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
        return str;
    }
    
    public static String getItemCorrect(final String user_id, final String unit_id, final String ass_ident, final String section_id, final String ins) {
        Connection oConn = null;
        String str = "";
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select count(item_id) from item_analysis  where student_id='" + user_id + "' and unit_id='" + unit_id + "' and assessment_id='" + ass_ident + "' and section_id='" + section_id + "' and instance='" + ins + "' and result='correct'");
            while (resultset.next()) {
                str = resultset.getString(1);
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
        return str;
    }
    
    public static String[] getUsage(final String s_id, final String u_id, final String ass_id, final String sec_id, final String ins) {
        Connection oConn = null;
        final String[] str = new String[5];
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select start_time,end_time,TIME_TO_SEC(start_time),TIME_TO_SEC(end_time),DATE_FORMAT(date,'%M %e,%Y') from assessment_usage  where student_id='" + s_id + "' and unit_id='" + u_id + "' and assessment_id='" + ass_id + "' and section_id='" + sec_id + "' and instance='" + ins + "'");
            while (resultset.next()) {
                str[0] = resultset.getString(1);
                str[1] = resultset.getString(2);
                str[2] = resultset.getString(3);
                str[3] = resultset.getString(4);
                str[4] = resultset.getString(5);
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
        return str;
    }
    
    public static Vector getItemTypeItemText(final int assess_id) {
        Connection oConn = null;
        final Vector vTextTitle = new Vector();
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
    
    public static synchronized String getItemObtainedMarks(final String item_id, final String instance, final String user_id, final String unit_id, final String section_id) {
        Connection oConn = null;
        String strMarksObtained = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select item_obtain_marks from item_analysis  where student_id='" + user_id + "' and unit_id='" + unit_id + "' and item_id='" + item_id + "' and instance='" + instance + "'");
            while (oRset.next()) {
                strMarksObtained = oRset.getString(1);
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
        return strMarksObtained;
    }
    
    public static synchronized String getItemObtainedMarks(final String item_id, final String instance, final String user_id) {
        Connection oConn = null;
        String strMarksObtained = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select item_obtain_marks from item_analysis  where student_id='" + user_id + "' and item_id='" + item_id + "' and instance='" + instance + "'");
            while (oRset.next()) {
                strMarksObtained = oRset.getString(1);
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
        return strMarksObtained;
    }
    
    public static Vector getInstanceListAllUser(final String unit_id, final String ass_ident, final String section_id) {
        Connection oConn = null;
        final Vector vRegisteredList = new Vector(2, 2);
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select a.instance from item_analysis a where unit_id='" + unit_id + "' and assessment_id='" + ass_ident + "' and section_id='" + section_id + "' group by instance");
            while (resultset.next()) {
                final String[] str = { resultset.getString(1) };
                vRegisteredList.addElement(str);
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
        return vRegisteredList;
    }
    
    public static Vector getItemId_analysis(final String unit_id, final String ass_ident, final String section_id, final String ins) {
        Connection oConn = null;
        final Vector vRegisteredList = new Vector(2, 2);
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final Statement statement3 = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select a.item_id from item_analysis a where unit_id='" + unit_id + "' and assessment_id='" + ass_ident + "' and section_id='" + section_id + "' and instance='" + ins + "' group by item_id");
            while (resultset.next()) {
                final String[] str = { resultset.getString(1), null, null };
                final ResultSet resultset2 = statement2.executeQuery("select count(item_id) from item_analysis  where unit_id='" + unit_id + "' and assessment_id='" + ass_ident + "' and section_id='" + section_id + "' and item_id='" + str[0] + "' and instance='" + ins + "' and (result='correct' OR result='incorrect')");
                while (resultset2.next()) {
                    str[1] = resultset2.getString(1);
                }
                final ResultSet resultset3 = statement3.executeQuery("select count(item_id) from item_analysis  where unit_id='" + unit_id + "' and assessment_id='" + ass_ident + "' and section_id='" + section_id + "' and item_id='" + str[0] + "' and instance='" + ins + "' and result='correct' ");
                while (resultset3.next()) {
                    str[2] = resultset3.getString(1);
                }
                vRegisteredList.addElement(str);
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
        return vRegisteredList;
    }
    
    public static int getNoCandidateAppeared(final String u_id, final String ass_id, final String sec_id, final String ins) {
        Connection oConn = null;
        String str = "";
        int count = 0;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select student_id from unit_student_association  where unit_id='" + u_id + "'");
            while (resultset.next()) {
                str = resultset.getString(1);
                final Statement statement2 = oConn.createStatement();
                final ResultSet resultset2 = statement2.executeQuery("select item_id from item_analysis  where student_id='" + str + "' and unit_id = '" + u_id + "' and assessment_id='" + ass_id + "' and section_id='" + sec_id + "' and instance='" + ins + "' group by instance");
                while (resultset2.next()) {
                    System.out.println("yes");
                    ++count;
                }
                statement2.close();
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
        return count;
    }
    
    public static String getItemAnswerChoice(final String unit_id, final String ass_ident, final String section_id, final String item_id, final String result_data, final String instance) {
        Connection oConn = null;
        String str = "";
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select count(item_id) from item_analysis  where unit_id='" + unit_id + "' and assessment_id='" + ass_ident + "' and section_id='" + section_id + "' and item_id='" + item_id + "' and instance='" + instance + "' and result_data='" + result_data + "'");
            while (resultset.next()) {
                str = resultset.getString(1);
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
        return str;
    }
    
    public static String getItemAnswerMR(final String unit_id, final String ass_ident, final String section_id, final String item_id, final String result_data, final String instance) {
        Connection oConn = null;
        String str = "";
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select count(item_id) from item_analysis  where unit_id='" + unit_id + "' and assessment_id='" + ass_ident + "' and section_id='" + section_id + "' and item_id='" + item_id + "' and instance='" + instance + "' and (result_data LIKE '" + result_data + "%' OR result_data LIKE '%" + result_data + "')");
            while (resultset.next()) {
                str = resultset.getString(1);
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
        return str;
    }
    
    public static Vector getInstanceListForGr(final String user_id, final String unit_id, final String ass_ident, final String section_id) {
        Connection oConn = null;
        final Vector vRegisteredList = new Vector(2, 2);
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select a.student_id from student_group_association a where a.group_id='" + user_id + "' ");
            while (resultset.next()) {
                final String stu_id = resultset.getString(1);
                final Statement statement2 = oConn.createStatement();
                final Vector str = new Vector();
                final ResultSet resultset2 = statement2.executeQuery("select a.instance from item_analysis a where student_id='" + stu_id + "' and unit_id='" + unit_id + "' and assessment_id='" + ass_ident + "' and section_id='" + section_id + "' group by instance");
                while (resultset2.next()) {
                    final String tmp = resultset2.getString(1);
                    str.addElement(tmp);
                }
                vRegisteredList.addElement(str);
                statement2.close();
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
        return vRegisteredList;
    }
    
    public static Vector getGrStudent(final String gr_id) {
        Connection oConn = null;
        final Vector vRegisteredList = new Vector(2, 2);
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select b.student_id ,concat(b.first_name,' ',b.middle_name,' ',b.sname) from student_group_association a,student_details b where a.student_id=b.student_id and  a.group_id='" + gr_id + "'");
            while (resultset.next()) {
                final String[] str = { resultset.getString(1), resultset.getString(2) };
                vRegisteredList.addElement(str);
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
        return vRegisteredList;
    }
    
    public static String getGroupMemberNo(final String gr_id) {
        Connection oConn = null;
        String str = "";
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select count(student_id) from student_group_association  where group_id='" + gr_id + "'");
            while (resultset.next()) {
                str = resultset.getString(1);
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
        return str;
    }
    
    public static int getNoCandidateAppearedGr(final String gr_id, final String u_id, final String ass_id, final String sec_id, final String ins) {
        Connection oConn = null;
        String str = "";
        int count = 0;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select student_id from student_group_association  where group_id='" + gr_id + "'");
            while (resultset.next()) {
                str = resultset.getString(1);
                final ResultSet resultset2 = statement2.executeQuery("select item_id from item_analysis  where student_id='" + str + "' and unit_id = '" + u_id + "' and assessment_id='" + ass_id + "' and section_id='" + sec_id + "' and instance='" + ins + "' group by instance");
                while (resultset2.next()) {
                    System.out.println("yes");
                    ++count;
                }
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
        return count;
    }
    
    public static synchronized String getItemMarks(final String item_id) {
        Connection oConn = null;
        String strMarks = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select item_marks from assessment_item_data  where item_id='" + item_id + "'");
            while (oRset.next()) {
                strMarks = oRset.getString(1);
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
        return strMarks;
    }
    
    public static Vector getInstanceListForGrWithoutSection(final String user_id, final String unit_id, final String ass_ident) {
        Connection oConn = null;
        final Vector vRegisteredList = new Vector(2, 2);
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select a.student_id from student_group_association a where a.group_id='" + user_id + "' ");
            while (resultset.next()) {
                final String stu_id = resultset.getString(1);
                System.out.println("student id from databaselayer::" + stu_id);
                final Statement statement2 = oConn.createStatement();
                final Vector str = new Vector();
                final ResultSet resultset2 = statement2.executeQuery("select a.instance from item_analysis a where student_id='" + stu_id + "' and unit_id='" + unit_id + "' and assessment_id='" + ass_ident + "' group by instance");
                while (resultset2.next()) {
                    final String tmp = resultset2.getString(1);
                    str.addElement(tmp);
                }
                vRegisteredList.addElement(str);
                statement2.close();
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
        return vRegisteredList;
    }
    
    public static Vector getItemDetail(final String user_id, final String unit_id, final String ass_ident, final String ins) {
        Connection oConn = null;
        final Vector vRegisteredList = new Vector(2, 2);
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select a.item_id,a.result_data,a.result,a.result_data from item_analysis a where student_id='" + user_id + "' and unit_id='" + unit_id + "' and assessment_id='" + ass_ident + "' and instance='" + ins + "'");
            while (resultset.next()) {
                final String[] str = { resultset.getString(1), resultset.getString(2), resultset.getString(3) };
                vRegisteredList.addElement(str);
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
        return vRegisteredList;
    }
    
    public static int isappeared(final String student_id, final String u_id, final String ass_ident, final String instance) {
        Connection oConn = null;
        final String str = "";
        int count = 0;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select item_id from item_analysis  where student_id='" + student_id + "' and unit_id = '" + u_id + "' and assessment_id='" + ass_ident + "' and instance='" + instance + "' group by instance");
            while (resultset.next()) {
                count = 1;
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
        return count;
    }
    
    public static int getNoCandidateAppearedGr(final String gr_id, final String u_id, final String ass_id, final String ins) {
        Connection oConn = null;
        String str = "";
        int count = 0;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select student_id from student_group_association  where group_id='" + gr_id + "'");
            while (resultset.next()) {
                str = resultset.getString(1);
                final ResultSet resultset2 = statement2.executeQuery("select item_id from item_analysis  where student_id='" + str + "' and unit_id = '" + u_id + "' and assessment_id='" + ass_id + "' and instance='" + ins + "' group by instance");
                while (resultset2.next()) {
                    System.out.println("yes");
                    ++count;
                }
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
        return count;
    }
    
    public static Vector getUserScoInfoResult(final String c_id) {
        Connection oConn = null;
        final Vector vRegisteredList = new Vector(2, 2);
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select user_id, sco_id,maxscore,rawscore,lessonstatus from userscoinfo where unit_id ='" + c_id + "' and maxscore !=\"\" and rawscore !=\"\"");
            while (resultset.next()) {
                final String[] strcal = new String[6];
                final String strid = resultset.getString(1);
                final ResultSet resultset2 = statement2.executeQuery("select   account_status ,concat(first_name,' ',middle_name,' ',sname) from student_details where student_id ='" + strid + "'");
                if (resultset2.next()) {
                    final String s = resultset2.getString(1);
                    strcal[0] = strid;
                    strcal[1] = resultset2.getString(2);
                    strcal[2] = resultset.getString(2);
                    strcal[3] = resultset.getString(3);
                    strcal[4] = resultset.getString(4);
                    if (!strcal[4].equals("")) {
                        final float ft = Float.parseFloat(strcal[4]);
                        final DecimalFormat df = new DecimalFormat("#.00");
                        final String strFloat = df.format(ft);
                        strcal[4] = strFloat;
                    }
                    strcal[5] = resultset.getString(5);
                    vRegisteredList.addElement(strcal);
                }
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
        return vRegisteredList;
    }
    
    public static Vector getUnitUsage(final String c_id) {
        Connection oConn = null;
        final Vector vListUsage = new Vector(3, 3);
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final Statement statement3 = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select student_id, count(*) from learner_login_info where unit_id ='" + c_id + "' group by student_id");
            while (oRset.next()) {
                final Vector vList = new Vector(3, 3);
                final String s_id = oRset.getString(1);
                final int count = oRset.getInt(2);
                String strTime = "";
                String s_name = "";
                final ResultSet resultset1 = statement2.executeQuery("select coalesce(sum(if(logout_datetime>=login_datetime,(to_days(logout_datetime)-to_days(login_datetime))*86400,0) + if(time_to_sec(logout_datetime)>= time_to_sec(login_datetime),time_to_sec(logout_datetime)-time_to_sec(login_datetime),(86400+time_to_sec(logout_datetime))-time_to_sec(login_datetime))),0) from learner_login_info where student_id = '" + s_id + "' and unit_id = '" + c_id + "'");
                if (resultset1.next()) {
                    final String strTimeSec = resultset1.getString(1);
                    final int dateTime = Integer.parseInt(strTimeSec);
                    final int hr = (int)Math.floor(dateTime / 3600);
                    final int rem = dateTime % 3600;
                    final int min = (int)Math.floor(rem / 60);
                    strTime = "" + hr + ":" + min;
                }
                final ResultSet oRset2 = statement3.executeQuery("select concat(first_name,' ',middle_name,' ',sname) from student_details where student_id ='" + s_id + "'");
                if (oRset2.next()) {
                    s_name = oRset2.getString(1);
                }
                vList.addElement(s_id);
                vList.addElement(s_name);
                vList.addElement(strTime);
                vList.addElement("" + count);
                vListUsage.addElement(vList);
            }
            statement.close();
            statement2.close();
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
        return vListUsage;
    }
    
    public static synchronized Vector getGroupDetailsList() {
        Vector vGroupList = null;
        Connection oConn = null;
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            Statement oStmt = oConn.createStatement();
            ResultSet oRset = oStmt.executeQuery("select count(*)  from student_group");
            int l_i = 0;
            while (oRset.next()) {
                l_i = oRset.getInt(1);
            }
            oRset.close();
            oStmt.close();
            if (l_i <= 0) {
                return null;
            }
            vGroupList = new Vector();
            oStmt = oConn.createStatement();
            oRset = oStmt.executeQuery("select a.group_id, a.group_name, date_format(a.group_created_date,\"%M %e, %Y %H:%i\"), concat(b.user_first_name,' ', b.user_middle_name,' ',b.user_last_name), date_format(a.last_modification_date,\"%M %e, %Y %H:%i\"), a.learning_style from student_group a, administrator_details b where a.group_created_by=b.user_id");
            while (oRset.next()) {
                final Vector vGroup = new Vector();
                vGroup.addElement(oRset.getString(1));
                vGroup.addElement(oRset.getString(2));
                vGroup.addElement(oRset.getString(3));
                vGroup.addElement(oRset.getString(4));
                vGroup.addElement(oRset.getString(5));
                final Statement oStmt2 = oConn.createStatement();
                final ResultSet oRset2 = oStmt2.executeQuery("select count(*) from student_group_association where group_id = '" + oRset.getString(1) + "'");
                oRset2.next();
                vGroup.addElement(oRset2.getString(1));
                vGroupList.addElement(vGroup);
                oRset2.close();
                oStmt2.close();
                vGroup.addElement(oRset.getString(6));
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            EmbeddedAsmtDataBaseLayer.log.fatal(" error due to SQL exception");
            EmbeddedAsmtDataBaseLayer.log.fatal(" error from getGroupDetailsList");
            EmbeddedAsmtDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        catch (Exception ex) {
            EmbeddedAsmtDataBaseLayer.log.fatal(" error due to java.lang.exception");
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
        return vGroupList;
    }
    
    public static Vector getReport(final String ass_id, final String unit_id) {
        Connection oConn = null;
        final Vector vListUsage = new Vector(3, 3);
        String ass_id2 = "";
        try {
            oConn = EmbeddedAsmtDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final ResultSet oRset1 = statement2.executeQuery("select assessment_id from assessment_usermodel where assessment_data_record_id='" + ass_id + "'");
            while (oRset1.next()) {
                ass_id2 = oRset1.getString(1);
            }
            final ResultSet oRset2 = statement.executeQuery("select a.assessment_date, a.start_time, concat(d.first_name,' ',d.middle_name,' ',d.sname), c.item_text, c.result_data from assessment_usermodel a,assessment_section_data b, essay_type_data c, student_details d where a.assessment_data_record_id=b.assessment_data_record_id and a.user_id=d.student_id and b.section_data_record_id=c.section_data_record_id and a.unit_id='" + unit_id + "' and a.assessment_id='" + ass_id2 + "';");
            while (oRset2.next()) {
                final String[] str = { oRset2.getString(1), oRset2.getString(2), oRset2.getString(3), oRset2.getString(4), oRset2.getString(5) };
                vListUsage.addElement(str);
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
        return vListUsage;
    }
    
    static {
        log = new SimpleLogger((Class)EmbeddedAsmtDataBaseLayer.class, true);
        EmbeddedAsmtDataBaseLayer.ds1 = CoreAdminInitHostInfo.ds1;
    }
}
