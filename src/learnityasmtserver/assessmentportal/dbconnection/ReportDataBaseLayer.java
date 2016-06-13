package learnityasmtserver.assessmentportal.dbconnection;

import javax.sql.*;
import java.util.*;
import org.grlea.log.*;


import java.io.InputStream;
import java.sql.*;
import comv2.aunwesha.param.*;

public class ReportDataBaseLayer
{
    private static final SimpleLogger log;
    public static DataSource ds;
    public static DataSource ds1;
    
    public static synchronized String getSkinInfo(final String str1) {
        String as = null;
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select a.skin_id from student_group a,student_group_association b  where a.group_id=b.group_id and b.student_id='" + str1 + "' ");
            while (resultset.next()) {
                as = resultset.getString(1);
            }
            statement.close();
            final Statement statement2 = oConn.createStatement();
            if (as == null) {
                final ResultSet resultset2 = statement2.executeQuery("select a.skin_id from student_skin a where a.student_id='" + str1 + "' ");
                while (resultset2.next()) {
                    as = resultset2.getString(1);
                }
            }
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.debug("Exception in ReportDataBaseLayer.getskin()");
            ReportDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            ReportDataBaseLayer.log.debug("Exception in ReportDataBaseLayer.getskin()");
            ReportDataBaseLayer.log.debug("The Error Message - " + exception.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return as;
    }
    
    public Vector<String[]> getAssessmentInfo1(final String student_id) {
        final Vector<String[]> vAssess = new Vector<String[]>();
        ReportDataBaseLayer.log.debug("student_id==getAssessmentInfo==" + student_id);
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            ReportDataBaseLayer.log.debug("select a.assessment_id,b.title,a.date_availability,a.registration_valid_till,b.duration,b.pass_marks,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)- TO_DAYS(sysdate())>=0),'Available','Date Over') from assessment_user_registration a, assessment_management b where a.assessment_id=b.assessment_id and a.student_id='" + student_id + "'");
            final ResultSet oRset = oStmt.executeQuery("select a.assessment_id,b.title,a.date_availability,a.registration_valid_till,b.duration,b.pass_marks,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)- TO_DAYS(sysdate())>=0),'Available','Date Over') from assessment_user_registration a, assessment_management b where a.assessment_id=b.assessment_id and a.student_id='" + student_id + "'");
            while (oRset.next()) {
                final String[] str = { oRset.getString(1), oRset.getString(2), oRset.getString(3), oRset.getString(4), oRset.getString(5), oRset.getString(6), oRset.getString(7), oRset.getString(8) };
                vAssess.addElement(str);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            ReportDataBaseLayer.log.debug("getAssessmentInfo: error due to SQL exception" + e);
        }
        catch (Exception e2) {
            ReportDataBaseLayer.log.debug("getAssessmentInfo: exception=" + e2);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vAssess;
    }
    
    public Vector<String[]> getAssessmentInfo(final String assessment_id) {
        final Vector<String[]> vTestInfo = new Vector<String[]>();
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            ReportDataBaseLayer.log.debug("select duration, question_perpage from assessment_management where assessment_id='" + assessment_id + "'");
            final ResultSet oRset = oStmt.executeQuery("select duration, question_perpage from assessment_management where assessment_id='" + assessment_id + "'");
            while (oRset.next()) {
                final String[] info = { oRset.getString(1), oRset.getString(2) };
                vTestInfo.addElement(info);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            ReportDataBaseLayer.log.debug("getAssessmentInfo: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("getAssessmentInfo printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vTestInfo;
    }
    
    public static Vector<String[]> getAssessmentAvailablity(final String strStudentId, final String assessment_id) {
        Connection oConn = null;
        final Vector<String[]> vAssess = new Vector<String[]>();
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            ReportDataBaseLayer.log.debug("select available_time,date_availability,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),registration_valid_till,if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)-TO_DAYS(sysdate())>=0),'Available','Date Over') from assessment_user_registration  where assessment_id='" + assessment_id + "' and student_id='" + strStudentId + "'");
            final ResultSet oRset = statement.executeQuery("select available_time,date_availability,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),registration_valid_till,if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)-TO_DAYS(sysdate())>=0),'Available','Date Over') from assessment_user_registration  where assessment_id='" + assessment_id + "' and student_id='" + strStudentId + "'");
            while (oRset.next()) {
                final String[] str = { oRset.getString(1), oRset.getString(2), oRset.getString(3), oRset.getString(4), oRset.getString(5) };
                vAssess.addElement(str);
            }
            statement.close();
            oRset.close();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vAssess;
    }
    
    public static Vector<String[]> getAssessmentGroupAvailablity(final String strStudentId, final String assessment_id) {
        Connection oConn = null;
        final Vector<String[]> vAssess = new Vector<String[]>();
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            ReportDataBaseLayer.log.debug("select a.available_time,a.date_availability,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),registration_valid_till,if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)-TO_DAYS(sysdate())>=0),'Available','Date Over') from assessment_group_registration a,student_group_association s where a.group_id=s.group_id and a.assessment_id='" + assessment_id + "' and s.student_id='" + strStudentId + "'");
            final ResultSet oRset = statement.executeQuery("select a.available_time,a.date_availability,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),registration_valid_till,if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)-TO_DAYS(sysdate())>=0),'Available','Date Over') from assessment_group_registration a,student_group_association s where a.group_id=s.group_id and a.assessment_id='" + assessment_id + "' and s.student_id='" + strStudentId + "'");
            while (oRset.next()) {
                final String[] str = { oRset.getString(1), oRset.getString(2), oRset.getString(3), oRset.getString(4), oRset.getString(5) };
                vAssess.addElement(str);
            }
            statement.close();
            oRset.close();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vAssess;
    }
    
    public String getMaxtestTaken(final String assessment_id) {
        String maxno = "";
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select max_no_test_taken from assessment_management where assessment_id='" + assessment_id + "'");
            while (oRset.next()) {
                maxno = oRset.getString(1);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e2) {
            ReportDataBaseLayer.log.debug("getMaxtestTaken: error due to SQL exception");
        }
        catch (Exception e1) {
            ReportDataBaseLayer.log.debug("getMaxtestTaken: error due to exception=" + e1);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return maxno;
    }
    
    public String getMaxNoAppearedInTest(final String student_id, final String assessment_id) {
        String maxno = "";
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select count(*) from assessment_progress_status where student_id='" + student_id + "' and assessment_id='" + assessment_id + "' and submition_status='Submitted'");
            while (oRset.next()) {
                maxno = oRset.getString(1);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e2) {
            ReportDataBaseLayer.log.debug("getMaxtestTaken: error due to SQL exception");
        }
        catch (Exception e1) {
            ReportDataBaseLayer.log.debug("getMaxtestTaken: error due to exception==" + e1);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return maxno;
    }
    
    public Vector<String[]> getAssessmentInfogroup(final String student_id) {
        final Vector<String[]> vAssess = new Vector<String[]>();
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            ReportDataBaseLayer.log.debug("getAssessmentInfogroup");
            ReportDataBaseLayer.log.debug("select a.assessment_id,b.title,a.date_availability,a.registration_valid_till,b.duration,b.pass_marks,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)-TO_DAYS(sysdate())>=0),'Available','Date Over') from assessment_group_registration a, student_group_association s,assessment_management b where a.group_id=s.group_id and a.assessment_id=b.assessment_id and s.student_id='" + student_id + "'");
            final ResultSet oRset = oStmt.executeQuery("select a.assessment_id,b.title,a.date_availability,a.registration_valid_till,b.duration,b.pass_marks,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)-TO_DAYS(sysdate())>=0),'Available','Date Over') from assessment_group_registration a, student_group_association s,assessment_management b where a.group_id=s.group_id and a.assessment_id=b.assessment_id and s.student_id='" + student_id + "'");
            while (oRset.next()) {
                final String[] str = { oRset.getString(1), oRset.getString(2), oRset.getString(3), oRset.getString(4), oRset.getString(5), oRset.getString(6), oRset.getString(7), oRset.getString(8) };
                vAssess.addElement(str);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e2) {
            ReportDataBaseLayer.log.debug("getAssessmentInfogroup: error due to SQL exception");
        }
        catch (Exception e1) {
            ReportDataBaseLayer.log.debug("getAssessmentInfogroup: error due to exception:" + e1);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vAssess;
    }
    
    public String getLastStatus(final String student_id, final String assessment_id) {
        String status = "";
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select status from stassessment_test_details where student_id='" + student_id + "' and assessment_id='" + assessment_id + "'");
            while (oRset.next()) {
                status = oRset.getString(1);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e2) {
            ReportDataBaseLayer.log.debug("getLastStatus: error due to SQL exception");
        }
        catch (Exception e1) {
            ReportDataBaseLayer.log.debug("getLastStatus: error due toexception:" + e1);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return status;
    }
    
    public String getTotalquesno(final String assessment_id) {
        int ques_no1 = 0;
        String ques_no2 = "";
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select no_of_questions from assessment_defination where assessment_id='" + assessment_id + "'");
            while (oRset.next()) {
                final int a = oRset.getInt(1);
                ques_no1 += a;
            }
            ques_no2 = Integer.toString(ques_no1);
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            ReportDataBaseLayer.log.debug("getTotalquesno: error due to SQL exception");
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("getAssessmentTitle printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return ques_no2;
    }
    
    public Vector<String> isPreviousTestSubmitted(final String student_id, final String checkback) {
        final Vector<String> vstatus = new Vector<String>();
        String astatus = "";
        String save_state = "";
        String test_id = "";
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select submition_status,saved_state,test_id from assessment_progress_status where student_id='" + student_id + "' and assessment_id='" + checkback + "'");
            while (oRset.next()) {
                astatus = oRset.getString(1);
                save_state = oRset.getString(2);
                test_id = oRset.getString(3);
            }
            vstatus.addElement(astatus);
            vstatus.addElement(save_state);
            vstatus.addElement(test_id);
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e2) {
            ReportDataBaseLayer.log.debug("isPreviousTestSubmitted: error due to SQL exception");
        }
        catch (Exception e1) {
            ReportDataBaseLayer.log.debug("isPreviousTestSubmitted: error due to exception:" + e1);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vstatus;
    }
    
    public void addProgressStatus(final String student_id, final String assessment_id, final String ques_per_page, final String strTime, final String total_time_left) {
        Connection oConn = null;
        try {
            final String test_id = this.getTestID(student_id, assessment_id);
            oConn = ReportDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            synchronized (this) {
                final boolean flag = statement.execute("insert into assessment_progress_status(test_id,student_id,assessment_id,ques_per_page,startdate,starttime,time_left,submition_status,instanceno) values ('" + test_id + "','" + student_id + "','" + assessment_id + "','" + ques_per_page + "',sysdate(),'" + strTime + "','" + total_time_left + "','Not Submitted','0')");
                statement.close();
            }
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.debug("Exception in addProgressStatus");
            ReportDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("addProgressStatus printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public void addTestDetails(final String student_id, final String assessment_id, final String strTime, final String total_ques, final String ques_per_page, final String total_time_left) {
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            synchronized (this) {
                final boolean flag = statement.execute("insert into stassessment_test_details(student_id,assessment_id,date,time_started,total_no_of_ques,no_of_ques_per_page,duration,submition_status) values ('" + student_id + "','" + assessment_id + "',sysdate(),'" + strTime + "','" + total_ques + "','" + ques_per_page + "','" + total_time_left + "','Not Submitted')");
                statement.close();
            }
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.debug("Exception in addTestDetails");
            ReportDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("addTestDetails printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public String getTestID(final String student_id, final String assessment_id) {
        String str1 = "";
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select max(test_id) from stassessment_test_details where student_id='" + student_id + "'  and assessment_id='" + assessment_id + "'");
            while (oRset.next()) {
                str1 = oRset.getString(1);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            ReportDataBaseLayer.log.debug("getTestID: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("getTestID printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return str1;
    }
    
    public Vector<String[]> getAssessmentDeffination(final String assessment_id) {
        final Vector<String[]> vTestInfo = new Vector<String[]>();
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select topic_id,no_of_questions,degree_of_difficulty,question_type from assessment_defination where assessment_id='" + assessment_id + "'");
            while (oRset.next()) {
                final String[] info = new String[4];
                final String topic_id = oRset.getString(1);
                final String no_of_questions = oRset.getString(2);
                final String degree_of_difficulty = oRset.getString(3);
                final String question_type = oRset.getString(4);
                info[0] = topic_id;
                info[1] = no_of_questions;
                info[2] = degree_of_difficulty;
                info[3] = question_type;
                vTestInfo.addElement(info);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            ReportDataBaseLayer.log.debug("getAssessmentDeffination: error due to SQL exception");
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("getAssessmentDeffination printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vTestInfo;
    }
    
    public static String getAssessmentTitle(final String assessment_id) {
        String str1 = null;
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select title from assessment_management where assessment_id='" + assessment_id + "'");
            while (oRset.next()) {
                str1 = oRset.getString(1);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            ReportDataBaseLayer.log.debug("getAssessmentTitle: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("getAssessmentTitle printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return str1;
    }
    
    public static Vector getTestItemIDs(final String topicId, final String qtype) {
        final Vector vTestInfo = new Vector();
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            ResultSet oRset;
            if (!qtype.equals("Any")) {
                oRset = oStmt.executeQuery("select q.assess_id from qb_mgmt_item q, qb_topic_association t where q.qb_id=t.qb_id and q.assess_title='" + qtype + "' and t.topic_id='" + topicId + "'");
            }
            else {
                oRset = oStmt.executeQuery("select q.assess_id from qb_mgmt_item q, qb_topic_association t where q.qb_id=t.qb_id and t.topic_id='" + topicId + "'");
            }
            while (oRset.next()) {
                final String assess_id = oRset.getString(1);
                vTestInfo.addElement(assess_id);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.debug("SQLException in getTestItemIDs()");
            ReportDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("getTestItemIDs printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vTestInfo;
    }
    
    public static Vector<String> getItemId(final Vector<String> vItemIdList, final String dlevel) {
        final Vector<String> vItemList = new Vector<String>();
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            for (int i = 0; i < vItemIdList.size(); ++i) {
                final String id = vItemIdList.elementAt(i);
                final ResultSet oRset = oStmt.executeQuery("select metadatafield_value from qb_metadata where metadatafield_name='levelofdifficulty' and item_id='" + id + "'");
                while (oRset.next()) {
                    final String metadatafield_value = oRset.getString(1);
                    if (metadatafield_value.equals(dlevel)) {
                        vItemList.addElement(id);
                    }
                }
            }
            oStmt.close();
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.debug("SQLException in getItemId()");
            ReportDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("getItemId printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vItemList;
    }
    
    public static Vector<Vector> getQuesTextAndType(final Vector<String> IDs) {
        final Vector<Vector> vAllDetails = new Vector<Vector>();
        final Vector<String> vId = new Vector<String>();
        final Vector<String> vTitle = new Vector<String>();
        final Vector<InputStream> vText = new Vector<InputStream>();
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement statement1 = oConn.createStatement();
            for (int i = 0; i < IDs.size(); ++i) {
                final String id = IDs.elementAt(i);
                ReportDataBaseLayer.log.debug("***********getQuesTextAndType***method*********************");
                String strDatabaseEncrptPassword = "";
                final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
                final String strkey = "DatabaseEncrptPassword";
                try {
                    strDatabaseEncrptPassword = rb.getString(strkey);
                }
                catch (Exception Ex) {
                    strDatabaseEncrptPassword = "no";
                }
                ReportDataBaseLayer.log.debug("************strDatabaseEncrptPassword******************" + strDatabaseEncrptPassword);
                if (strDatabaseEncrptPassword.equals("no") || strDatabaseEncrptPassword == "no") {
                    ReportDataBaseLayer.log.debug("select assess_title,assess_text from qb_mgmt_item where assess_id='" + id + "'");
                    final ResultSet resultset1 = statement1.executeQuery("select assess_title,assess_text from qb_mgmt_item where assess_id='" + id + "'");
                    while (resultset1.next()) {
                        vId.addElement(id);
                        vTitle.addElement(resultset1.getString(1));
                        vText.addElement(resultset1.getAsciiStream(2));
                    }
                }
                else {
                    ReportDataBaseLayer.log.debug("select assess_title,AES_DECRYPT(assess_text,'" + strDatabaseEncrptPassword + "') from qb_mgmt_item where assess_id='" + id + "'");
                    final ResultSet resultset1 = statement1.executeQuery("select assess_title,AES_DECRYPT(assess_text,'" + strDatabaseEncrptPassword + "') from qb_mgmt_item where assess_id='" + id + "'");
                    while (resultset1.next()) {
                        vId.addElement(id);
                        vTitle.addElement(resultset1.getString(1));
                        vText.addElement(resultset1.getAsciiStream(2));
                    }
                }
            }
            statement1.close();
            vAllDetails.addElement(vId);
            vAllDetails.addElement(vTitle);
            vAllDetails.addElement(vText);
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.debug("getTextAndType: error due to SQL exception" + sqlexception.getMessage());
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("getTextAndType printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vAllDetails;
    }
    
    public static Vector getTextAndType(final Vector<String> IDs) {
        final Vector<Vector> vAllDetails = new Vector<Vector>();
        final Vector<String> vId = new Vector<String>();
        final Vector<String> vTitle = new Vector<String>();
        final Vector<InputStream> vText = new Vector<InputStream>();
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement statement1 = oConn.createStatement();
            for (int i = 0; i < IDs.size(); ++i) {
                final String id = IDs.elementAt(i);
                ReportDataBaseLayer.log.debug("***********getTextAndType***method*********************");
                String strDatabaseEncrptPassword = "";
                final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
                final String strkey = "DatabaseEncrptPassword";
                strDatabaseEncrptPassword = rb.getString(strkey);
                ReportDataBaseLayer.log.debug("************strDatabaseEncrptPassword******************" + strDatabaseEncrptPassword);
                ReportDataBaseLayer.log.debug("select assess_title,AES_DECRYPT(assess_text,'" + strDatabaseEncrptPassword + "') from qb_mgmt_item where assess_id='" + id + "'");
                final ResultSet resultset1 = statement1.executeQuery("select assess_title,AES_DECRYPT(assess_text,'" + strDatabaseEncrptPassword + "') from qb_mgmt_item where assess_id='" + id + "'");
                while (resultset1.next()) {
                    vId.addElement(id);
                    vTitle.addElement(resultset1.getString(1));
                    vText.addElement(resultset1.getAsciiStream(2));
                }
            }
            statement1.close();
            vAllDetails.addElement(vId);
            vAllDetails.addElement(vTitle);
            vAllDetails.addElement(vText);
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.debug("getTextAndType: error due to SQL exception" + sqlexception.getMessage());
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("getTextAndType printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vAllDetails;
    }
    
    public static Vector<String> getAlrmDetailsvector() {
        final Vector<String> vAdministratorList = new Vector<String>();
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            //final Statement oStmt2 = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select alarm_name,time,message from alarm_master  where status='Active'");
            while (oRset.next()) {
                vAdministratorList.addElement(oRset.getString(1));
                vAdministratorList.addElement(oRset.getString(2));
                vAdministratorList.addElement(oRset.getString(3));
            }
        }
        catch (SQLException e) {
            ReportDataBaseLayer.log.debug(" error due to SQL exception");
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug(" error due to java.lang.exception");
            ex.printStackTrace();
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vAdministratorList;
    }
    
    public void modifyProgressData(final String student_id, final String assessment_id, final String autosave, final String total_time_left, final String test_id) {
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            synchronized (this) {
                final boolean flag = statement.execute("update assessment_progress_status set time_left='" + total_time_left + "',saved_state='" + autosave + "',instanceno=instanceno+1 where test_id='" + test_id + "' and student_id='" + student_id + "' and assessment_id='" + assessment_id + "'");
                statement.close();
            }
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.debug("SQLException in modifyProgressData");
            ReportDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("modifyProgressData printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public Vector getLeftTime(final String student_id, final String assessment_id, final String test_id) {
        final Vector v = new Vector();
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select time_left,ques_per_page from assessment_progress_status where student_id='" + student_id + "' and test_id='" + test_id + "' and submition_status='Not Submitted'");
            while (oRset.next()) {
                v.addElement(oRset.getInt(1));
                v.addElement(oRset.getString(1));
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            ReportDataBaseLayer.log.debug("getLeftTime: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("getLeftTime printStack is :: " + ex.getMessage());
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
    
    public void updateRestartDateTime(final String student_id, final String assessment_id, final String strTime, final String test_id) {
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            synchronized (this) {
                final boolean flag = statement.execute("update assessment_progress_status set restartdate=sysdate(),restarttime='" + strTime + "' where test_id='" + test_id + "' and student_id='" + student_id + "'");
                statement.close();
            }
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.debug("Exception in updateRestartDateTime");
            ReportDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("updateRestartDateTime printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public Vector getAutosaveText(final String student_id, final String test_id) {
        final Vector v = new Vector();
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select saved_state from assessment_progress_status where student_id='" + student_id + "' and test_id='" + test_id + "' and submition_status='Not Submitted'");
            while (oRset.next()) {
                v.addElement(oRset.getAsciiStream(1));
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            ReportDataBaseLayer.log.debug("getAutosaveText: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("getAutosaveText printStack is :: " + ex.getMessage());
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
    
    public void addDetailsQues(final String student_id, final String test_id, final String time_completed, final String question_id, final String question_text, final String correctAns, final String ans_data, final String ans_text, final String ans_status, final String strScore, final double item_obtain_marks) {
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final int testid = 0;
            oConn.setAutoCommit(false);
            final Statement statement3 = oConn.createStatement();
            System.out.println("insert into stassessment_question_details(student_id,test_id,question_id,ans_data,ans_status,item_marks,item_obtain_marks) values ('" + student_id + "','" + test_id + "','" + question_id + "','" + ans_data + "','" + ans_status + "','" + strScore + "','" + item_obtain_marks + "')");
            final boolean flag = statement3.execute("insert into stassessment_question_details(student_id,test_id,question_id,ans_data,ans_status,item_marks,item_obtain_marks) values ('" + student_id + "','" + test_id + "','" + question_id + "','" + ans_data + "','" + ans_status + "','" + strScore + "','" + item_obtain_marks + "')");
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.debug("Exception in addDetailsQues()");
            ReportDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("addDetailsQues printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public int gPassMarks(final String model_id) {
        int str1 = 0;
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select pass_marks from assessment_management where assessment_id='" + model_id + "'");
            while (oRset.next()) {
                str1 = oRset.getInt(1);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            ReportDataBaseLayer.log.debug("gPassMarks: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("gPassMarks printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return str1;
    }
    
    public String studentName(final String s_id) {
        String s_name = "";
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset1 = statement.executeQuery("select concat(first_name,' ',middle_name,' ',sname) from student_details where student_id ='" + s_id + "'");
            if (oRset1.next()) {
                s_name = oRset1.getString(1);
            }
            statement.close();
            oRset1.close();
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.fatal("SQLException:");
            ReportDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            ReportDataBaseLayer.log.fatal("Exception:");
            ReportDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
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
    
    public void modifyTestDetails(final String student_id, final String test_id, final String strTime) {
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            synchronized (this) {
                final boolean flag = statement.execute("update stassessment_test_details set time_completed = '" + strTime + "' where student_id = '" + student_id + "' and test_id = '" + test_id + "'");
                statement.close();
            }
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.debug("Exception in modifyTestDetails");
            ReportDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("modifyTestDetails printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public void updateAssessmentProgressStatus(final String student_id, final String assessment_id, final String strDate, final String strTime, final String test_id) {
        final String asmt_progress_id = "";
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            synchronized (this) {
                final boolean flag = statement.execute("update assessment_progress_status set submitdate=sysdate(),submittime='" + strTime + "',submition_status='Submitted' where test_id='" + test_id + "' and student_id='" + student_id + "'");
                statement.close();
            }
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.debug("Exception in updateAssessmentProgressStatus");
            ReportDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("updateAssessmentProgressStatus printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public void updateTestDetails(final String student_id, final String test_id, final String confirmation_id, final int no_of_ques_attempted, final int no_correctAns, final double Marks, final double totalScore, final String status) {
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            synchronized (this) {
                final boolean flag = statement.execute("update stassessment_test_details set confirmation_id='" + confirmation_id + "',no_of_ques_attempted = '" + no_of_ques_attempted + "',no_of_coans='" + no_correctAns + "',full_marks='" + Marks + "',marks_obtained='" + totalScore + "',status='" + status + "',submition_status='Submitted' where student_id = '" + student_id + "' and test_id = '" + test_id + "' and submition_status='Not Submitted'");
                statement.close();
            }
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.debug("Exception in updateTestDetails");
            ReportDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("*****************error due to java.lang.exception in updateTestDetails**************");
            ex.printStackTrace();
            ReportDataBaseLayer.log.debug("updateTestDetails printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static Vector selectname() {
        Vector vector = null;
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            vector = new Vector();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select a.org_name,a.org_logo,a.portalname from login_page_definition a");
            while (resultset.next()) {
                final String[] as = { resultset.getString(1), resultset.getString(2), resultset.getString(3) };
                vector.addElement(as);
            }
            statement.close();
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.debug("sqlexception===" + sqlexception);
        }
        catch (Exception exception) {
            ReportDataBaseLayer.log.debug("exception===" + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vector;
    }
    
    public void deleteprevioustest(final String student_id, final String checkback, final String pretest_id) {
        Connection oConn = null;
        try {
            oConn = ReportDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            ReportDataBaseLayer.log.debug("pretest_id===" + pretest_id);
            final boolean flag = statement.execute("delete from assessment_progress_status where test_id= '" + pretest_id + "'");
            final boolean flag2 = statement.execute("delete from stassessment_test_details where test_id= '" + pretest_id + "'");
            statement.close();
        }
        catch (SQLException sqlexception) {
            ReportDataBaseLayer.log.debug("sqlexception===" + sqlexception);
        }
        catch (Exception ex) {
            ReportDataBaseLayer.log.debug("exception===" + ex);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static byte[] getOrganizationLogo(final String resource_id, final String interface_id) {
        byte[] _blob = null;
        Connection conn = null;
        try {
            conn = ReportDataBaseLayer.ds1.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement("SELECT value FROM resource WHERE resource_id= = ? and interface_id= ? ");
            pstmt.setString(1, "logo");
            pstmt.setString(2, "LearnityPortal");
            final ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("rs has next");
                final Blob blob = rs.getBlob(1);
                final int length = (int)blob.length();
                System.out.println("length is:" + length);
                _blob = blob.getBytes(1L, length);
            }
            System.out.println("CgetOrganizationLogo===================");
            conn.close();
        }
        catch (SQLException ex) {
            System.out.println("SQLException in getOrganizationLogo " + ex.getMessage());
            ex.printStackTrace();
        }
        catch (Exception e) {
            System.out.println("Exception in getOrganizationLogo " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return _blob;
    }
    
    static {
        log = new SimpleLogger((Class)ReportDataBaseLayer.class);
        ReportDataBaseLayer.ds = CoreAdminInitHostInfo.ds;
        ReportDataBaseLayer.ds1 = CoreAdminInitHostInfo.ds1;
    }
}
