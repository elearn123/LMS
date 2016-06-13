package learnityasmtserver.assessmentadmin.dbconnection;

import javax.sql.*;
import org.grlea.log.*;
import java.util.*;
import java.sql.*;
import java.io.*;
import comv2.aunwesha.param.*;

public class DataBaseLayer
{
    public static final SimpleLogger log;
    public static DataSource ds;
    public static DataSource ds1;
    
    public static synchronized Vector<Vector<String>> getCname() {
        final Vector<Vector<String>> vQBList = new Vector<Vector<String>>();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select topic_id,subtopic_name from topic_management where topic_name=\"\";");
            while (oRset.next()) {
                final Vector<String> vQBList2 = new Vector<String>();
                vQBList2.addElement(oRset.getString(1));
                vQBList2.addElement(oRset.getString(2));
                vQBList.addElement(vQBList2);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getCourseDetailsList: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("getCourseDetailsList1: error due to SQL exception");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vQBList;
    }
    
    public static Vector<String[]> getCnames(final String parent) {
        final Vector<String[]> vLayoutList = new Vector<String[]>(5, 5);
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select topic_id,subtopic_name  from topic_management where topic_name='" + parent + "'");
            while (oRset.next()) {
                final String[] str = { oRset.getString(1), oRset.getString(2), null, null, null };
                vLayoutList.addElement(str);
            }
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
            DataBaseLayer.log.debug("The Error Code    - " + sqlexception.getErrorCode());
            while ((sqlexception = sqlexception.getNextException()) != null) {
                DataBaseLayer.log.debug("The Error message - " + sqlexception.getMessage());
                DataBaseLayer.log.debug("The Error code is - " + sqlexception.getErrorCode());
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            DataBaseLayer.log.debug("The message - " + exception.toString());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vLayoutList;
    }
    
    public static synchronized void addTopic(final String child) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("insert into topic_management(topic_name,subtopic_name) values ('','" + child + "')");
            statement.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in addTopic()");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("addTopic printStack is :: " + ex.getMessage());
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
    
    public static synchronized void addTopic(final String parent, final String child) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("insert into topic_management(topic_name,subtopic_name) values ('" + parent + "','" + child + "')");
            statement.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in addTopic()");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("addTopic printStack is :: " + ex.getMessage());
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
    
    public static synchronized void modifyTopic(final String p_id, final String parent, final String child) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("update topic_management set topic_name = '" + parent + "',subtopic_name='" + child + "' where topic_id = '" + p_id + "'");
            statement.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in modifyTopic()");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("modifyTopic printStack is :: " + ex.getMessage());
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
    
    public static synchronized void deleteTopic(final String p_id, final String child) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("delete from topic_management where topic_id= '" + p_id + "'");
            statement.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in productadmin.deleteProduct()");
            DataBaseLayer.log.debug("deleteTopic: The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("deleteTopic printStack is :: " + ex.getMessage());
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
    
    public static synchronized Vector getTopicList() {
        final Vector vList = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select topic_id,subtopic_name from topic_management order by subtopic_name");
            while (oRset.next()) {
                final Vector v = new Vector();
                v.addElement(oRset.getString(1));
                v.addElement(oRset.getString(2));
                vList.addElement(v);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getTopicList: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getTopicList printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vList;
    }
    
    public static synchronized Vector getQBList() {
        final Vector vList = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select qb_id,qb_name from qb_mgmt");
            while (oRset.next()) {
                final Vector v = new Vector();
                v.addElement(oRset.getString(1));
                v.addElement(oRset.getString(2));
                vList.addElement(v);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getQBList: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getQBList printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vList;
    }
    
    public static synchronized void AssociateQbTopic(final String strtopic, final String qb_id) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("insert into qb_topic_association(qb_id,topic_id) values ('" + qb_id + "','" + strtopic + "')");
            statement.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("AssociateQbTopic: The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("AssociateQbTopic printStack is :: " + ex.getMessage());
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
    
    public static synchronized void deleteAssociateQbTopic(final String checkbox1, final String topic) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("delete from qb_topic_association where qb_id= '" + checkbox1 + "' and topic_id='" + topic + "'");
            statement.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in deleteAssociateQbTopic()");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("deleteAssociateQbTopic printStack is :: " + ex.getMessage());
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
    
    public static synchronized void addAssessmentMgmt(final String assess_code, final String assess_title, final String assess_type, final String assess_desc, final String ques_perpage, final String duration, final String pass_marks, final String max_test_taken, final String dmin) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("insert into assessment_management(assessment_code,title,assessment_type,description,duration,pass_marks,question_perpage,max_no_test_taken,created_by,created_on) values ('" + assess_code + "','" + assess_title + "','" + assess_type + "','" + assess_desc + "','" + duration + "','" + pass_marks + "','" + ques_perpage + "','" + max_test_taken + "','" + dmin + "',sysdate())");
            statement.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in addAssessmentMgmt()");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("addAssessmentMgmt printStack is :: " + ex.getMessage());
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
    
    public static synchronized void modifyAssessmentMgmt(final int assessid, final String assess_code, final String assess_title, final String assess_type, final String assess_desc, final String ques_perpage, final String duration, final String pass_marks, final String max_test_taken, final String dmin) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("update assessment_management set assessment_code = '" + assess_code + "',title='" + assess_title + "',assessment_type='" + assess_type + "',description='" + assess_desc + "',question_perpage='" + ques_perpage + "' ,duration='" + duration + "',pass_marks='" + pass_marks + "',max_no_test_taken='" + max_test_taken + "' where assessment_id = '" + assessid + "'");
            statement.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in modifyAssessmentMgmt()");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("modifyAssessmentMgmt printStack is :: " + ex.getMessage());
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
    
    public static synchronized void deleteAssessmentMgmt(final String assessid) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("delete from assessment_management where assessment_id = '" + assessid + "'");
            statement.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in deleteAssessmentMgmt()");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("deleteAssessmentMgmt printStack is :: " + ex.getMessage());
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
    
    public static synchronized Vector getAssessment() {
        final Vector vModelTestList = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select assessment_id,title from assessment_management where assessment_type='signature' order by title");
            while (oRset.next()) {
                final Vector vmodellist = new Vector();
                vmodellist.addElement(oRset.getString(1));
                vmodellist.addElement(oRset.getString(2));
                vModelTestList.addElement(vmodellist);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getAssessment: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getAssessment printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vModelTestList;
    }
    
    public static synchronized Vector<String> getQuesBankId(final String topic_id) {
        final Vector<String> vQBList = new Vector<String>();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select qb_id from qb_topic_association where topic_id='" + topic_id + "'");
            while (oRset.next()) {
                final String qb_id = oRset.getString(1);
                vQBList.addElement(qb_id);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getQuesBankId: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getQuesBankId printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vQBList;
    }
    
    public static synchronized Vector<String> getItemList(final String quesbankid) {
        final Vector<String> vQBList = new Vector<String>();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select assess_id from qb_mgmt_item where qb_id='" + quesbankid + "'");
            while (oRset.next()) {
                final String assess_id = oRset.getString(1);
                vQBList.addElement(assess_id);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getItemList: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getItemList printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vQBList;
    }
    
    public static synchronized int getIteIDmsType(final String id, final String question_type) {
        int t = 0;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select assess_id from qb_mgmt_item where assess_id='" + id + "' and assess_title='" + question_type + "'");
            while (oRset.next()) {
                final String item_id = oRset.getString(1);
                if (item_id != null) {
                    t = 1;
                }
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getIteIDmsType: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getIteIDmsType printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return t;
    }
    
    public static synchronized int getIteIDmslevel(final String id, final String idlebel) {
        int t = 0;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final String dlevel = "levelofdifficulty";
            final ResultSet oRset = oStmt.executeQuery("select metadatafield_value from qb_metadata where metadatafield_name='" + dlevel + "' and item_id='" + id + "' ");
            while (oRset.next()) {
                final String metedatafield_value = oRset.getString(1);
                if (metedatafield_value.equals(idlebel)) {
                    t = 1;
                }
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getIteIDmslevel: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getIteIDmslevel printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return t;
    }
    
    public static synchronized void AssessmentDefination(final int assessment_id, final String select_topic, final String question_type, final int no_ques_per_topic, final String idlebel, final String admin) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("insert into assessment_defination(assessment_id,topic_id,no_of_questions,degree_of_difficulty,question_type,created_by,date) values ('" + assessment_id + "','" + select_topic + "','" + no_ques_per_topic + "','" + idlebel + "','" + question_type + "','" + admin + "',sysdate())");
            statement.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("AssessmentDefination: The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("AssessmentDefination printStack is :: " + ex.getMessage());
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
    
    public static synchronized void modifyAssessmentDefination(final int assessment_id, final String select_topic, final String question_type, final int no_ques_per_topic, final String idlebel, final String admin) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            final boolean flag = oStmt.execute("update assessment_defination  set topic_id = '" + select_topic + "', question_type='" + question_type + "', no_of_questions='" + no_ques_per_topic + "',degree_of_difficulty='" + idlebel + "',created_by='" + admin + "' where assessment_id = '" + assessment_id + "'");
            oStmt.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("SQLException in modifyAssessmentDefination");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("modifyAssessmentDefination printStack is :: " + ex.getMessage());
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
    
    public static synchronized boolean deleteAssessmentDefination(final int AssessTitleSelect1, final String topicId, final String idlebel, final String question_type) {
        boolean flag = false;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            flag = oStmt.execute("delete from assessment_defination where assessment_id = '" + AssessTitleSelect1 + "' and topic_id = '" + topicId + "' and degree_of_difficulty='" + idlebel + "' and question_type='" + question_type + "'");
            oStmt.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("deleteAssessmentDefination: Error due to SQL exception : " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("deleteAssessmentDefination printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return flag;
    }
    
    public static synchronized Vector getAssessmentList() {
        final Vector vList = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select assessment_id,title from assessment_management order by title ");
            while (oRset.next()) {
                final Vector v = new Vector();
                v.addElement(oRset.getString(1));
                v.addElement(oRset.getString(2));
                vList.addElement(v);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getAssessmentList: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getAssessmentList printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vList;
    }
    
    public static synchronized Vector getAssessmentLists() {
        final Vector vList = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            DataBaseLayer.log.debug("select assessment_id,assessment_code,title,assessment_type,description,duration,pass_marks,question_perpage,max_no_test_taken,asmt_generation_type from assessment_management order by title ");
            final ResultSet oRset = oStmt.executeQuery("select assessment_id,assessment_code,title,assessment_type,description,duration,pass_marks,question_perpage,max_no_test_taken,asmt_generation_type from assessment_management order by title ");
            while (oRset.next()) {
                final Vector v = new Vector();
                v.addElement(oRset.getString(1));
                v.addElement(oRset.getString(2));
                v.addElement(oRset.getString(3));
                v.addElement(oRset.getString(4));
                v.addElement(oRset.getString(5));
                v.addElement(oRset.getString(6));
                v.addElement(oRset.getString(7));
                v.addElement(oRset.getString(8));
                v.addElement(oRset.getString(9));
                v.addElement(oRset.getString(10));
                vList.addElement(v);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getAssessmentLists: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getAssessmentLists printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vList;
    }
    
    public static synchronized Vector getUserDetailsList() {
        final Vector vUserList = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select s.student_id,concat(s.first_name,' ',s.middle_name,' ',s.sname) from  student_details s order by s.first_name");
            while (oRset.next()) {
                final Vector vUserList2 = new Vector();
                vUserList2.addElement(oRset.getString(1));
                vUserList2.addElement(oRset.getString(2));
                vUserList.addElement(vUserList2);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getUserDetailsList: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getUserDetailsList printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vUserList;
    }
    
    public static Vector getVectorStudentDetailsList(final String emp_cat) {
        final Vector vGroupList = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select a.student_id,concat('SPF NO-',b.spfno,'/Name-',a.first_name,' ',a.middle_name,' ',a.sname) from student_details a,ubi_user_other_details b where a.student_id=b.student_id and b.emp_category='" + emp_cat + "' order by b.spfno");
            while (oRset.next()) {
                final Vector vGroup = new Vector();
                vGroup.addElement(oRset.getString(1));
                vGroup.addElement(oRset.getString(2));
                vGroupList.addElement(vGroup);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug(" error from getVectorStudentDetailsList " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug(" printStack is getVectorStudentDetailsList:: " + ex.getMessage());
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
    
    public static String getMailId(final String s) {
        String s2 = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select email_id from student_details where student_id='" + s + "'");
            while (resultset.next()) {
                s2 = resultset.getString(1);
            }
            statement.close();
            resultset.close();
        }
        catch (SQLException sqlexception) {
            final int i = sqlexception.getErrorCode();
            DataBaseLayer.log.fatal("Error due to SQL exception inside NewDataBaseLayer.getMailId() : " + sqlexception.getMessage());
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("error due to java.lang.exception");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
            DataBaseLayer.log.fatal(" printStack is :: " + exception.getMessage());
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
    
    public static String getAssessmentTitle(final String assessment_id) {
        String str1 = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select title from assessment_management where assessment_id='" + assessment_id + "'");
            while (oRset.next()) {
                str1 = oRset.getString(1);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getAssessmentTitle: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getAssessmentTitle printStack is :: " + ex.getMessage());
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
    
    public static String getMailServerTitle() {
        String s1 = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select title from mail_server_configuration where status='Active'");
            while (resultset.next()) {
                s1 = resultset.getString(1);
            }
            resultset.close();
            statement.close();
        }
        catch (SQLException sqlexception) {
            final int i = sqlexception.getErrorCode();
            DataBaseLayer.log.fatal("Error due to SQL exception inside NewDataBaseLayer.getMailServerTitle() : " + sqlexception.getMessage());
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("error due to java.lang.exception");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
            DataBaseLayer.log.fatal(" printStack is :: " + exception.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return s1;
    }
    
    public static synchronized String checkIfRegisteruser(final String strUsrId, final String AssessmentSelect) {
        String str1 = "No";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select a.assessment_id from student_group_association s,assessment_group_registration a where a.group_id=s.group_id and s.student_id='" + strUsrId + "' and a.assessment_id='" + AssessmentSelect + "'");
            while (oRset.next()) {
                str1 = "Yes";
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("checkIfRegisteruser: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("checkIfRegisteruser printStack is :: " + ex.getMessage());
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
    
    public static synchronized Vector getStudentInfo(final String student_id) {
        final Vector vGroupList = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select concat(s.first_name,' ',s.middle_name,' ',s.sname)  from student_details s where s.student_id='" + student_id + "'");
            while (oRset.next()) {
                vGroupList.addElement(oRset.getString(1));
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug(" error from getStudentInfo " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug(" printStack is getStudentInfo:: " + ex.getMessage());
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
    
    public static synchronized void addAssessmentUserRegistration(final String strUsrId, final String AssessmentSelect, final String strCreatedBy, final String strAvailabletime, final String strAvailablity1, final String strValidTill1, final String strTotalTime) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            oStmt.executeUpdate("insert into assessment_user_registration (student_id,assessment_id,date_registration,registered_by,available_time,date_availability,registration_valid_till,total_access_time) values ('" + strUsrId + "','" + AssessmentSelect + "',sysdate(),'" + strCreatedBy + "','" + strAvailabletime + "','" + strAvailablity1 + "','" + strValidTill1 + "','" + strTotalTime + "')");
            oStmt.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("addAssessmentUserRegistration: Error due to SQL exception in addAssessmentUserRegistration : " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("addAssessmentUserRegistration printStack is :: " + ex.getMessage());
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
    
    public static synchronized boolean insertStudentDetails(final String strUserId, final String strCreatedBy) {
        final boolean succ = false;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            oStmt.execute("insert into student_details (student_id, sname,first_name,middle_name,account_status) values('" + strUserId + "','" + strUserId + "', '" + strUserId + "','','Active')");
            oStmt.execute("insert into student_creation_details (student_id, date_student_created, student_created_by,last_modification_date) values('" + strUserId + "',sysdate(),'" + strCreatedBy + "',sysdate())");
            oStmt.execute("insert into student_password (student_id, password) values('" + strUserId + "','" + strUserId + "')");
            oConn.commit();
            oConn.setAutoCommit(true);
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("......................................insert................." + e.getMessage());
        }
        catch (Exception ex) {}
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return succ;
    }
    
    public static synchronized boolean checkStudentDetails(final String strUserId) {
        boolean flag = false;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select student_id from student_details a,all_preview_details b where student_id='" + strUserId + "' and a.student_id=b.user_id;");
            while (oRset.next()) {
                flag = true;
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("checkStudentDetails: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("checkStudentDetails: error due to  exception");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return flag;
    }
    
    public static synchronized boolean checkAsmtRegistration(final String strUserId) {
        boolean flag = false;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select student_id from assessment_user_registration a,all_preview_details b where a.student_id='" + strUserId + "' and a.student_id=b.user_id and a.assessment_id=b.preview_item_id and b.preview_type='asmtpreview';");
            while (oRset.next()) {
                flag = true;
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("checkAsmtRegistration: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("checkAsmtRegistration: error due to  exception");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return flag;
    }
    
    public static synchronized void autoAsmtRegistration(final String strUsrId, final String AssessmentSelect, final String strCreatedBy) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            oStmt.executeUpdate("insert into assessment_user_registration (student_id,assessment_id,date_registration,registered_by,available_time,date_availability,registration_valid_till,total_access_time) values ('" + strUsrId + "','" + AssessmentSelect + "',sysdate(),'" + strCreatedBy + "','10:00',sysdate(),sysdate(),'100')");
            oStmt.executeUpdate("insert into all_preview_details(user_id,preview_type,preview_item_id,preview_date) values ('" + strUsrId + "','asmtpreview','" + AssessmentSelect + "',sysdate())");
            oStmt.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("autoAsmtRegistration: Error due to SQL exception in autoAsmtRegistration : " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("autoAsmtRegistration printStack is :: " + ex.getMessage());
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
    
    public static synchronized void modifyAssessmentUserRegistration(final String strUsrId, final String AssessmentSelect, final String strCreatedBy, final String AvailableTime, final String strAvailablity1, final String strValidTill1, final String strTotalTime) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            final boolean flag = oStmt.execute("update assessment_user_registration  set date_registration = sysdate(), registered_by='" + strCreatedBy + "', available_time='" + AvailableTime + "', date_availability='" + strAvailablity1 + "',registration_valid_till='" + strValidTill1 + "',total_access_time='" + strTotalTime + "' where assessment_id = '" + AssessmentSelect + "' and student_id='" + strUsrId + "'");
            oStmt.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("SQLException in modifyAssessmentUserRegistration");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("modifyAssessmentUserRegistration printStack is :: " + ex.getMessage());
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
    
    public static synchronized boolean deleteAssessmentUserRegistration(final String strUsrId, final String AssessmentSelect) {
        boolean flag = false;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            flag = oStmt.execute("delete from assessment_user_registration  where assessment_id = '" + AssessmentSelect + "' and student_id='" + strUsrId + "'");
            oStmt.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("deleteAssessmentUserRegistration: Error due to SQL exception : " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("deleteAssessmentUserRegistration printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return flag;
    }
    
    public static synchronized Vector<Vector<String>> getAllAssessmentUser(final String ass_id) {
        Vector<Vector<String>> vCourseList = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            vCourseList = new Vector<Vector<String>>();
            final Statement oStmt = oConn.createStatement();
            System.out.println("select student_id from assessment_user_registration  where assessment_id='" + ass_id + "'");
            final ResultSet oRset = oStmt.executeQuery("select student_id from assessment_user_registration  where assessment_id='" + ass_id + "'");
            while (oRset.next()) {
                final Vector<String> vCourse = new Vector<String>();
                vCourse.addElement(oRset.getString(1));
                vCourseList.addElement(vCourse);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("Error due to SQL exception getAllAssessmentUser: " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is getAllAssessmentUser:: " + ex.getMessage());
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
    
    public static synchronized boolean deleteAllAssessmentUserRegistration(final String AssessmentSelect) {
        boolean flag = true;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            oStmt.execute("delete from assessment_user_registration  where assessment_id = '" + AssessmentSelect + "' ");
            oStmt.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("deleteAllAssessmentUserRegistration: Error due to SQL exception : " + e.getMessage());
            flag=false;
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("deleteAllAssessmentUserRegistration printStack is :: " + ex.getMessage());
            flag=false;
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return flag;
    }
    
    public static synchronized Vector<Vector<String>> getGroupDetailsList() {
        Vector<Vector<String>> vGroupList = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
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
            vGroupList = new Vector<Vector<String>>();
            oStmt = oConn.createStatement();
            oRset = oStmt.executeQuery("select a.group_id, a.group_name, date_format(a.group_created_date,\"%M %e, %Y %H:%i\"), concat(b.user_first_name,' ', b.user_middle_name,' ',b.user_last_name), date_format(a.last_modification_date,\"%M %e, %Y %H:%i\"), a.learning_style from student_group a, administrator_details b where a.group_created_by=b.user_id order by a.group_name");
            while (oRset.next()) {
                final Vector<String> vGroup = new Vector<String>();
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
            DataBaseLayer.log.debug(" error due to SQL exception");
            DataBaseLayer.log.debug(" error from getGroupDetailsList");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getGroupDetailsList printStack is :: " + ex.getMessage());
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
    
    public static synchronized String checkIfRegistergroup(final String strGroupId, final String AssessmentSelect) {
        String str1 = "No";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select a.assessment_id from student_group_association s,assessment_user_registration a where a.student_id=s.student_id and s.group_id='" + strGroupId + "' and a.assessment_id='" + AssessmentSelect + "'");
            while (oRset.next()) {
                str1 = "Yes";
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("checkIfRegistergroup: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("checkIfRegistergroup printStack is :: " + ex.getMessage());
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
    
    public static Vector<String> getUserinGroup(final String u_id) {
        final Vector<String> vCourseList = new Vector<String>(1, 1);
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select student_id from student_group_association where group_id = '" + u_id + "'");
            while (oRset.next()) {
                final String str0 = oRset.getString(1);
                vCourseList.addElement(str0);
            }
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug(" error from getUserinGroup " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is getUserinGroup:: " + ex.getMessage());
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
    
    public static synchronized void addAssessmentGroupRegistration(final String strGroupId, final String AssessmentSelect, final String strCreatedBy, final String AvailableTime, final String strAvailablity1, final String strValidTill1, final String strTotalTime) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            oStmt.executeUpdate("insert into assessment_group_registration (group_id,assessment_id,date_registration,registered_by,available_time,date_availability,registration_valid_till,total_access_time) values ('" + strGroupId + "','" + AssessmentSelect + "',sysdate(),'" + strCreatedBy + "','" + AvailableTime + "','" + strAvailablity1 + "','" + strValidTill1 + "','" + strTotalTime + "')");
            oStmt.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("addAssessmentGroupRegistration: Error due to SQL exception in addAssessmentGroupRegistration : " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("addAssessmentGroupRegistration printStack is :: " + ex.getMessage());
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
    
    public static synchronized void modifyAssessmentGroupRegistration(final String strGroupId, final String AssessmentSelect, final String strCreatedBy, final String AvailableTime, final String strAvailablity1, final String strValidTill1, final String strTotalTime) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            final boolean flag = oStmt.execute("update assessment_group_registration  set date_registration = sysdate(), registered_by='" + strCreatedBy + "', available_time='" + AvailableTime + "', date_availability='" + strAvailablity1 + "',registration_valid_till='" + strValidTill1 + "',total_access_time='" + strTotalTime + "' where assessment_id = '" + AssessmentSelect + "' and group_id='" + strGroupId + "'");
            oStmt.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("SQLException in modifyAssessmentGroupRegistration");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("modifyAssessmentGroupRegistration printStack is :: " + ex.getMessage());
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
    
    public static synchronized boolean deleteAssessmentGroupRegistration(final String strGroupId, final String AssessmentSelect) {
        boolean flag = false;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            flag = oStmt.execute("delete from assessment_group_registration  where assessment_id = '" + AssessmentSelect + "' and group_id='" + strGroupId + "'");
            oStmt.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("deleteAssessmentGroupRegistration: Error due to SQL exception : " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("deleteAssessmentGroupRegistration printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return flag;
    }
    
    public static synchronized boolean deleteAllAssessmentGroupRegistration(final String AssessmentSelect) {
        boolean flag = false;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            flag = oStmt.execute("delete from assessment_group_registration  where assessment_id = '" + AssessmentSelect + "' ");
            oStmt.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("deleteAllAssessmentGroupRegistration:Error due to SQL exception : " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("deleteAllAssessmentGroupRegistration printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return flag;
    }
    
    public static synchronized void addAlarm(final String alarmtitle, final String timeset, final String status, final String desc) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("Insert into alarm_master(alarm_name,time,status,message) values ('" + alarmtitle + "','" + timeset + "','" + status + "','" + desc + "')");
            statement.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException e) {}
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static synchronized void modifyalarm(final String id, final String alarmtitle, final String timeset, final String status, final String desc) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            DataBaseLayer.log.debug("update alarm_master set alarm_name = '" + alarmtitle + "', time = '" + timeset + "',status = '" + status + "',message='" + desc + "' where alarm_master_id = '" + id + "' ");
            oStmt.execute("update alarm_master set alarm_name = '" + alarmtitle + "', time = '" + timeset + "',status = '" + status + "',message='" + desc + "' where alarm_master_id = '" + id + "' ");
            oStmt.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("SQLException in modifyalarm");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception**in **modifyalarm************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("modifyalarm printStack is :: " + ex.getMessage());
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
    
    public static synchronized boolean deletealarm(final String id) {
        boolean l_bAuth = false;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            DataBaseLayer.log.debug("delete from alarm_master where alarm_master_id ='" + id + "'");
            oStmt.execute("delete from alarm_master where alarm_master_id ='" + id + "'");
            oStmt.close();
            l_bAuth = true;
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug(" error due to SQL exception==================DELETE ALARM" + e.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return l_bAuth;
    }
    
    public static synchronized int selectFullMarks(final String assessment_id) {
        double str1 = 0.0;
        int fullmarks = 0;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select full_marks from stassessment_test_details where assessment_id='" + assessment_id + "'");
            while (oRset.next()) {
                str1 = oRset.getDouble(1);
                if (str1 != 0.0) {
                    fullmarks = (int)str1;
                }
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("selectFullMarks: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("selectFullMarks printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return fullmarks;
    }
    
    public static synchronized void createtable1(final String AssessTitleSelect, final String equalPercentage, final String toPercentage, final int full_marks) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            oStmt.execute("delete from temp_result_report;");
            oStmt.close();
            final Statement oStmt2 = oConn.createStatement();
            if (equalPercentage.equals("0") && !toPercentage.equals("0")) {
                final int to_int = Integer.parseInt(toPercentage);
                final double percentage2 = Math.floor(full_marks * to_int * 1 / 100);
                final int ob_marks1 = (int)percentage2;
                System.out.println("select std.student_id,concat(std.first_name, ' ',std.middle_name,' ',std.sname),st.marks_obtained from stassessment_test_details st, student_details std where st.student_id=std.student_id and st.marks_obtained<'" + ob_marks1 + "' and st.assessment_id='" + AssessTitleSelect + "' order by st.marks_obtained desc");
                final ResultSet oRset2 = oStmt2.executeQuery("select std.student_id,concat(std.first_name, ' ',std.middle_name,' ',std.sname),st.marks_obtained from stassessment_test_details st, student_details std where st.student_id=std.student_id and st.marks_obtained<'" + ob_marks1 + "' and st.assessment_id='" + AssessTitleSelect + "' order by st.marks_obtained desc");
                while (oRset2.next()) {
                    final String student_id = oRset2.getString(1);
                    final String name = oRset2.getString(2);
                    final String marks_obtained = oRset2.getString(3);
                    final Statement statement = oConn.createStatement();
                    final boolean flag = statement.execute("insert into temp_result_report(student_id,name,marks_obtained) values ('" + student_id + "','" + name + "','" + marks_obtained + "')");
                    statement.close();
                }
            }
            if (!equalPercentage.equals("0") && toPercentage.equals("0")) {
                final int equal_int = Integer.parseInt(equalPercentage);
                final double percentage2 = Math.floor(full_marks * equal_int * 1 / 100);
                final int ob_marks2 = (int)percentage2;
                System.out.println("select std.student_id,concat(std.first_name, ' ',std.middle_name,' ',std.sname),st.marks_obtained from stassessment_test_details st, student_details std where st.student_id=std.student_id and st.marks_obtained='" + ob_marks2 + "' and st.assessment_id='" + AssessTitleSelect + "' order by st.marks_obtained desc");
                final ResultSet oRset2 = oStmt2.executeQuery("select std.student_id,concat(std.first_name, ' ',std.middle_name,' ',std.sname),st.marks_obtained from stassessment_test_details st, student_details std where st.student_id=std.student_id and st.marks_obtained='" + ob_marks2 + "' and st.assessment_id='" + AssessTitleSelect + "' order by st.marks_obtained desc");
                while (oRset2.next()) {
                    final String student_id = oRset2.getString(1);
                    final String name = oRset2.getString(2);
                    final String marks_obtained = oRset2.getString(3);
                    final Statement statement = oConn.createStatement();
                    final boolean flag = statement.execute("insert into temp_result_report(student_id,name,marks_obtained) values ('" + student_id + "','" + name + "','" + marks_obtained + "')");
                    statement.close();
                }
            }
            if (!equalPercentage.equals("0") && !toPercentage.equals("0")) {
                final int equal_int = Integer.parseInt(equalPercentage);
                final double percentage2 = Math.floor(full_marks * equal_int * 1 / 100);
                final int ob_marks2 = (int)percentage2;
                final int to_int2 = Integer.parseInt(toPercentage);
                final double percentage3 = Math.floor(full_marks * to_int2 * 1 / 100);
                final int ob_marks3 = (int)percentage3;
                System.out.println("select std.student_id,concat(std.first_name, ' ',std.middle_name,' ',std.sname),st.marks_obtained from stassessment_test_details st, student_details std where st.student_id=std.student_id and st.marks_obtained>='" + ob_marks2 + "' and st.marks_obtained<'" + ob_marks3 + "' and st.assessment_id='" + AssessTitleSelect + "' order by st.marks_obtained desc");
                final ResultSet oRset2 = oStmt2.executeQuery("select std.student_id,concat(std.first_name, ' ',std.middle_name,' ',std.sname),st.marks_obtained from stassessment_test_details st, student_details std where st.student_id=std.student_id and st.marks_obtained>='" + ob_marks2 + "' and st.marks_obtained<'" + ob_marks3 + "' and st.assessment_id='" + AssessTitleSelect + "' order by st.marks_obtained desc");
                while (oRset2.next()) {
                    final String student_id2 = oRset2.getString(1);
                    final String name2 = oRset2.getString(2);
                    final String marks_obtained2 = oRset2.getString(3);
                    final Statement statement = oConn.createStatement();
                    final boolean flag2 = statement.execute("insert into temp_result_report(student_id,name,marks_obtained) values ('" + student_id2 + "','" + name2 + "','" + marks_obtained2 + "')");
                    statement.close();
                }
            }
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("createtable1: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("createtable1 printStack is :: " + ex.getMessage());
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
    
    public static Vector getRegionCode() {
        final Vector v = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select region_master_id,concat('Region Code-',region_code,'/Region Name-',region_name) from region_master");
            while (oRset.next()) {
                final Vector v2 = new Vector();
                v2.addElement(oRset.getString(1));
                v2.addElement(oRset.getString(2));
                v.addElement(v2);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getRegionCode: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getRegionCode printStack is :: " + ex.getMessage());
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
    
    public static Vector getBranchCode(final String selectregion) {
        final Vector v = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select branch_master_id,concat('Branch Code-',branch_code,'/Branch Name-',branch_name) from branch_master where region_master_id='" + selectregion + "'");
            while (oRset.next()) {
                final Vector v2 = new Vector();
                v2.addElement(oRset.getString(1));
                v2.addElement(oRset.getString(2));
                v.addElement(v2);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getBranchCode: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getRegionCode printStack is :: " + ex.getMessage());
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
    
    public static Vector getuserDetailsInfo(final String shortedby) {
        final Vector vassessmentdetails1 = new Vector(1, 1);
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select report_id,student_id,student_name,date,time,number_of_ques,ques_attempted,number_of_correctans,total_marks,marks_obtained,percentage_marks_obtained,submition_status from temp_result_Details order by '" + shortedby + "'");
            while (oRset.next()) {
                final String slno = oRset.getString(1);
                final String student_id = oRset.getString(2);
                final String name = oRset.getString(3);
                final String date = oRset.getString(4);
                final String time = oRset.getString(5);
                final String no_of_questions = oRset.getString(6);
                final String no_of_questions_attempted = oRset.getString(7);
                final String no_of_correct_ans = oRset.getString(8);
                final String total_marks = oRset.getString(9);
                final String marks_obtained = oRset.getString(10);
                final String percentage_marks_obtained = oRset.getString(11);
                final String status = oRset.getString(12);
                final String[] strcal1 = { slno, student_id, name, date, time, no_of_questions, no_of_questions_attempted, no_of_correct_ans, total_marks, marks_obtained, percentage_marks_obtained, status };
                vassessmentdetails1.addElement(strcal1);
            }
            statement.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("getuserDetailsInfo: SQLException:" + sqlexception);
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getuserDetailsInfo printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vassessmentdetails1;
    }
    
    public static synchronized void createtableDetailsReport(final String AssessTitleSelect) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            oStmt.execute("delete from temp_result_Details;");
            oStmt.close();
            final Statement oStmt2 = oConn.createStatement();
            final ResultSet oRset2 = oStmt2.executeQuery("select std.student_id,concat(std.first_name, ' ',std.middle_name,'',std.sname),st.date,st.time_started,st.total_no_of_ques,st.no_of_ques_attempted,st.no_of_coans,st.full_marks,st.marks_obtained,(100*st.marks_obtained)/st.full_marks,st.submition_status from stassessment_test_details st,student_details std where st.student_id=std.student_id and st.assessment_id='" + AssessTitleSelect + "' order by std.student_id");
            while (oRset2.next()) {
                final String student_id = oRset2.getString(1);
                final String name = oRset2.getString(2);
                final String date = oRset2.getString(3);
                final String timestarted = oRset2.getString(4);
                String total_no_of_ques = oRset2.getString(5);
                if (total_no_of_ques == null) {
                    total_no_of_ques = "0";
                }
                String no_of_ques_attempted = oRset2.getString(6);
                if (no_of_ques_attempted == null) {
                    no_of_ques_attempted = "0";
                }
                String no_of_coans = oRset2.getString(7);
                if (no_of_coans == null) {
                    no_of_coans = "0";
                }
                String full_marks = oRset2.getString(8);
                if (full_marks == null) {
                    full_marks = "0.0";
                }
                final String marks_obtained1 = oRset2.getString(9);
                String marks_obtained2 = "";
                if (marks_obtained1 == null) {
                    marks_obtained2 = "0.0";
                }
                else {
                    marks_obtained2 = marks_obtained1;
                }
                String percentage_of_marks_obtained1 = oRset2.getString(10);
                if (percentage_of_marks_obtained1 == null) {
                    percentage_of_marks_obtained1 = "0.0";
                }
                final String submition_status = oRset2.getString(11);
                final Statement statement = oConn.createStatement();
                final boolean flag = statement.execute("insert into temp_result_Details(student_id,student_name,date,time,number_of_ques,ques_attempted,number_of_correctans,total_marks,marks_obtained,percentage_marks_obtained,submition_status) values ('" + student_id + "','" + name + "','" + date + "','" + timestarted + "','" + total_no_of_ques + "','" + no_of_ques_attempted + "','" + no_of_coans + "','" + full_marks + "','" + marks_obtained2 + "','" + percentage_of_marks_obtained1 + "','" + submition_status + "')");
                statement.close();
            }
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("createtableDetailsReport: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("createtableDetailsReport printStack is :: " + ex.getMessage());
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
    
    public static synchronized String getBranchName(final String selectbranch) {
        String str1 = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select branch_name from branch_master where branch_master_id='" + selectbranch + "'");
            while (oRset.next()) {
                str1 = oRset.getString(1);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getBranchName: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getBranchName printStack is :: " + ex.getMessage());
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
    
    public static synchronized String getRegionName(final String selectregion) {
        String str1 = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select region_name from region_master where region_master_id='" + selectregion + "'");
            while (oRset.next()) {
                str1 = oRset.getString(1);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getRegionName: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getRegionName printStack is :: " + ex.getMessage());
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
    
    public static synchronized void generateTopFiveUserResult(final String AssessTitleSelect) {
        final Vector vTestInfo = new Vector();
        final Vector v = new Vector();
        final int count = 0;
        Double pre_marks = 0.0;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt11 = oConn.createStatement();
            oStmt11.execute("delete from stassessment_topfive_result;");
            oStmt11.close();
            final Statement oStmt2 = oConn.createStatement();
            final Statement oStmt3 = oConn.createStatement();
            System.out.println("select std.student_id, std.full_marks, std.marks_obtained,std.test_id,std.submition_status,concat(d.first_name,' ',d.middle_name,' ',d.sname) from stassessment_test_details std,student_details d where std.student_id=d.student_id and std.assessment_id='" + AssessTitleSelect + "' order by std.marks_obtained desc");
            final ResultSet oRset = oStmt2.executeQuery("select std.student_id, std.full_marks, std.marks_obtained,std.test_id,std.submition_status,concat(d.first_name,' ',d.middle_name,' ',d.sname) from stassessment_test_details std,student_details d where std.student_id=d.student_id and std.assessment_id='" + AssessTitleSelect + "' order by std.marks_obtained desc");
            while (oRset.next()) {
                vTestInfo.addElement(oRset.getString(1));
                vTestInfo.addElement(oRset.getDouble(2));
                vTestInfo.addElement(oRset.getDouble(3));
                vTestInfo.addElement(oRset.getString(4));
                vTestInfo.addElement(oRset.getString(5));
                vTestInfo.addElement(oRset.getString(6));
            }
            System.out.println("=================vTestInfo.size()========" + vTestInfo.size());
            int i = 0;
            for (int j = 0; j < vTestInfo.size(); j += 6) {
                System.out.println("==========i====" + i + "    =======j===" + j);
                final Double obmarks = (Double) vTestInfo.elementAt(j + 2);
                System.out.println("obmarks==" + obmarks);
                System.out.println("pre_marks==" + pre_marks);
                if (obmarks.equals(pre_marks)) {
                    oStmt3.execute("insert into stassessment_topfive_result(assessment_id,student_id,test_id,name,full_marks,marks_obtained,submition_status) values('" + AssessTitleSelect + "','" + vTestInfo.elementAt(j) + "','" + vTestInfo.elementAt(j + 3) + "','" + vTestInfo.elementAt(j + 5) + "'," + vTestInfo.elementAt(j + 1) + "," + vTestInfo.elementAt(j + 2) + ",'" + vTestInfo.elementAt(j + 4) + "')");
                    pre_marks = obmarks;
                }
                else {
                    oStmt3.execute("insert into stassessment_topfive_result(assessment_id,student_id,test_id,name,full_marks,marks_obtained,submition_status) values('" + AssessTitleSelect + "','" + vTestInfo.elementAt(j) + "','" + vTestInfo.elementAt(j + 3) + "','" + vTestInfo.elementAt(j + 5) + "'," + vTestInfo.elementAt(j + 1) + "," + vTestInfo.elementAt(j + 2) + ",'" + vTestInfo.elementAt(j + 4) + "')");
                    ++i;
                    pre_marks = obmarks;
                }
                if (i > 4) {
                    break;
                }
            }
            oRset.close();
            oStmt2.close();
        }
        catch (SQLException e) {
            System.out.println("getTopFive: error due to SQL exception" + e);
            e.printStackTrace();
        }
        catch (Exception ex) {
            System.out.println("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            System.out.println(" printStack is :: " + ex.getMessage());
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
    
    public static synchronized void addQuestionBank(final String docId, final String title, final String size, final String name, final String admin) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            oStmt.executeUpdate("insert into qb_mgmt(qb_id,qb_name,file_name,file_size,date_upload,upload_by) values('" + docId + "','" + title + "','" + name + "','" + size + "',sysdate(),'" + admin + "')");
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.fatal("SQLException:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
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
    
    public static synchronized void addQuestionBankItems(final String docId, final String title, final String text) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            String strDatabaseEncrptPassword = "";
            final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
            final String strkey = "DatabaseEncrptPassword";
            try {
                strDatabaseEncrptPassword = rb.getString(strkey);
            }
            catch (Exception Ex) {
                strDatabaseEncrptPassword = "no";
            }
            DataBaseLayer.log.debug("************strDatabaseEncrptPassword******************" + strDatabaseEncrptPassword);
            if (strDatabaseEncrptPassword.equals("no") || strDatabaseEncrptPassword == "no") {
                final PreparedStatement oStmt = oConn.prepareStatement("insert into qb_mgmt_item(qb_id,assess_title,assess_text) values(?,?,?)");
                DataBaseLayer.log.debug("insert into qb_mgmt_item(qb_id,assess_title,assess_text) values(?,?,?)");
                oStmt.setString(1, docId);
                oStmt.setString(2, title);
                oStmt.setString(3, text);
                oStmt.execute();
            }
            else {
                final PreparedStatement oStmt = oConn.prepareStatement("insert into qb_mgmt_item(qb_id,assess_title,assess_text) values(?,?,AES_ENCRYPT(?,'" + strDatabaseEncrptPassword + "'))");
                DataBaseLayer.log.debug("insert into qb_mgmt_item(qb_id,assess_title,assess_text) values(?,?,AES_ENCRYPT(?,'" + strDatabaseEncrptPassword + "'))");
                oStmt.setString(1, docId);
                oStmt.setString(2, title);
                oStmt.setString(3, text);
                oStmt.execute();
            }
        }
        catch (SQLException e) {
            DataBaseLayer.log.fatal("SQLException:********addQuestionBankItems***************");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
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
    
    public static synchronized void addQuestionMetadata(final String docId, final String flabel, final String fentry) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            final ResultSet resultset = oStmt2.executeQuery("select max(assess_id) from qb_mgmt_item where qb_id='" + docId + "'");
            String item_id = "";
            while (resultset.next()) {
                item_id = resultset.getString(1);
            }
            oStmt.executeUpdate("insert into qb_metadata(qb_id,item_id,metadatafield_name,metadatafield_value) values('" + docId + "','" + item_id + "','" + flabel + "','" + fentry + "')");
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.fatal("SQLException:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
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
    
    public static synchronized String getSizeOfQB(final String qb_id) {
        String strsize = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select a.file_size from qb_mgmt a where a.qb_id='" + qb_id + "'");
            while (oRset.next()) {
                strsize = oRset.getString(1);
            }
            statement.close();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
            DataBaseLayer.log.debug("Exception==" + sqlexception);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            DataBaseLayer.log.debug("Exception==" + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return strsize;
    }
    
    public static synchronized void updateFileSize(final String ObjectBankSelect, final String strSize) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            oStmt.execute("update qb_mgmt set file_size='" + strSize + "' where qb_id='" + ObjectBankSelect + "'");
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.fatal("SQLException:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
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
    
    public static synchronized String getFileName(final String qid) {
        String file_name = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select file_name from qb_mgmt where qb_id='" + qid + "'");
            while (oRset.next()) {
                file_name = oRset.getString(1);
            }
            statement.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("SQLException:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("Exception:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return file_name;
    }
    
    public static synchronized void deleteQuestionBank(final String docId) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("delete from qb_mgmt where qb_id= '" + docId + "'");
            final boolean flag2 = statement.execute("delete from qb_mgmt_item where qb_id= '" + docId + "'");
            final boolean flag3 = statement.execute("delete from qb_metadata where qb_id= '" + docId + "'");
            statement.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in productadmin.deleteProduct()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in productadmin.deleteProduct()");
            System.out.println("The Error Message - " + ex.getMessage());
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
    
    public static synchronized void deleteQuestionBankItems(final String qbId) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            DataBaseLayer.log.debug("delete from qb_mgmt_item where qb_id= '" + qbId + "'");
            DataBaseLayer.log.debug("delete from qb_metadata where qb_id= '" + qbId + "'");
            final boolean flag1 = statement.execute("delete from qb_mgmt_item where qb_id= '" + qbId + "'");
            final boolean flag2 = statement.execute("delete from qb_metadata where qb_id= '" + qbId + "'");
            statement.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in productadmin.deleteQuestionBankItems()");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("Exception in productadmin.deleteQuestionBankItems()");
            DataBaseLayer.log.debug("The Error Message - " + ex.getMessage());
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
    
    public static synchronized Vector getQuestionBanks() {
        final Vector vQBList = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select qb_id,qb_name from qb_mgmt");
            while (oRset.next()) {
                final Vector vQBank = new Vector();
                vQBank.addElement(oRset.getString(1));
                vQBank.addElement(oRset.getString(2));
                vQBList.addElement(vQBank);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            System.out.println("error due to SQL exception");
        }
        catch (Exception ex) {
            System.out.println(" error due to SQL exception");
            ex.printStackTrace();
            System.out.println(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vQBList;
    }
    
    public static synchronized void deleteSelectedItem(final int item_id) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("delete from qb_mgmt_item where assess_id= '" + item_id + "'");
            statement.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in productadmin.deleteProduct()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in productadmin.deleteProduct()");
            System.out.println("The Error Message - " + ex.getMessage());
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
    
    public static void ModifySelectedItem(final int item_id, final String text) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            oStmt.execute("update qb_mgmt_item set assess_text='" + text + "' where assess_id='" + item_id + "'");
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.fatal("SQLException:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
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
    
    public static String getAssessmentId(final String assessmentcode) {
        String str = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            DataBaseLayer.log.debug("SELECT am.assessment_id FROM  assessment_management am WHERE assessment_code='" + assessmentcode + "'");
            final ResultSet oRset = oStmt.executeQuery("SELECT am.assessment_id FROM  assessment_management am WHERE assessment_code='" + assessmentcode + "'");
            if (oRset.next()) {
                str = oRset.getString(1);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug(" getAssessmentId" + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return str;
    }
    
    public static boolean isGroupRegistered(final String group_id, final String assessmentcode) {
        boolean flag = false;
        String str = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            DataBaseLayer.log.debug("SELECT a.group_id FROM assessment_group_registration a, assessment_management am WHERE am.assessment_id=a.assessment_id AND am.assessment_code='" + assessmentcode + "' AND a.group_id='" + group_id + "'");
            final ResultSet oRset = oStmt.executeQuery("SELECT a.group_id FROM assessment_group_registration a, assessment_management am WHERE am.assessment_id=a.assessment_id AND am.assessment_code='" + assessmentcode + "' AND a.group_id='" + group_id + "'");
            if (oRset.next()) {
                str = oRset.getString(1);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug(" isGroupRegistered" + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        flag = !str.equals("");
        return flag;
    }
    
    public static boolean isGroupAssessmentExists(final String groupId, final String assessmentcode) {
        boolean flag = false;
        String str = "";
        String assessmentTitle = "";
        String group_id = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            final Statement oStmt3 = oConn.createStatement();
            DataBaseLayer.log.debug("SELECT a.title FROM assessment_management a WHERE a.assessment_code='" + assessmentcode + "'");
            final ResultSet oRset = oStmt.executeQuery("SELECT a.title FROM assessment_management a WHERE a.assessment_code='" + assessmentcode + "'");
            if (oRset.next()) {
                assessmentTitle = oRset.getString(1);
            }
            DataBaseLayer.log.debug("select group_id from student_group where group_id='" + groupId + "'");
            final ResultSet oRset2 = oStmt2.executeQuery("select group_id from student_group where group_id='" + groupId + "' ");
            if (oRset2.next()) {
                group_id = oRset2.getString(1);
            }
            DataBaseLayer.log.debug("SELECT a.group_id FROM assessment_group_registration a, assessment_management am WHERE am.assessment_id=a.assessment_id AND am.assessment_code='" + assessmentcode + "' AND a.group_id='" + groupId + "'");
            final ResultSet oRset3 = oStmt.executeQuery("SELECT a.group_id FROM assessment_group_registration a, assessment_management am WHERE am.assessment_id=a.assessment_id AND am.assessment_code='" + assessmentcode + "' AND a.group_id='" + groupId + "'");
            if (oRset3.next()) {
                str = oRset3.getString(1);
            }
            DataBaseLayer.log.debug("===============assessmentTitle==========" + assessmentTitle);
            DataBaseLayer.log.debug("===============group_id==========" + group_id);
            DataBaseLayer.log.debug("===============str==========" + str);
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug(" isGroupAssessmentExists" + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        flag = (assessmentTitle.equals("") || group_id.equals("") || !str.equals(""));
        return flag;
    }
    
    public static synchronized void insertAssessmentGroupRegistration(final String groupId, final String assessmentcode, final String strCreatedBy, final String availableTime, final String availableDate, final String strValidTill, final String strTotalTime) {
        int count = 0;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            Statement oStmt = oConn.createStatement();
            oStmt = oConn.createStatement();
            System.out.println("insert into assessment_group_registration (group_id, assessment_id, date_registration, registered_by,available_time,date_availability,registration_valid_till, total_access_time) values ('" + groupId + "','" + assessmentcode + "',sysdate(),'" + strCreatedBy + "','" + availableTime + "','" + availableDate + "','" + strValidTill + "','" + strTotalTime + "')");
            count = oStmt.executeUpdate("insert into assessment_group_registration (group_id, assessment_id, date_registration, registered_by,available_time,date_availability,registration_valid_till, total_access_time) values ('" + groupId + "','" + assessmentcode + "',sysdate(),'" + strCreatedBy + "','" + availableTime + "','" + availableDate + "','" + strValidTill + "','" + strTotalTime + "')");
            oStmt.close();
        }
        catch (SQLException e) {
            System.out.println("Error due to SQL exception insertAssessmentGroupRegistration====: " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
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
    
    public static synchronized void insertTempAssessmentGroupRegistration(final String groupId, final String assessmentcode, final String strCreatedBy, final String availableTime, final String availableDate, final String strValidTill, final String strTotalTime) {
        int count = 0;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            Statement oStmt = oConn.createStatement();
            oStmt = oConn.createStatement();
            System.out.println("insert into temp_assessment_group_registration (group_id, assessment_id, date_registration, registered_by,available_time,date_availability,registration_valid_till, total_access_time) values ('" + groupId + "','" + assessmentcode + "',sysdate(),'" + strCreatedBy + "','" + availableTime + "','" + availableDate + "','" + strValidTill + "','" + strTotalTime + "')");
            count = oStmt.executeUpdate("insert into temp_assessment_group_registration (group_id, assessment_id, date_registration, registered_by,available_time,date_availability,registration_valid_till, total_access_time) values ('" + groupId + "','" + assessmentcode + "',sysdate(),'" + strCreatedBy + "','" + availableTime + "','" + availableDate + "','" + strValidTill + "','" + strTotalTime + "')");
            oStmt.close();
        }
        catch (SQLException e) {
            System.out.println("Error due to SQL exception insertTempAssessmentGroupRegistration====: " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
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
    
    public static synchronized void modifyAssessmentGroupRegistration(final String groupId, final String assessmentcode, final String strCreatedBy, final String availableTime, final String availableDate, final String strValidTill, final String strTotalTime, final PrintWriter out) {
        int count = 0;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            oStmt2.execute("DELETE FROM assessment_group_registration\tWHERE group_id='" + groupId + "' AND assessment_id='" + assessmentcode + "'");
            System.out.println("insert into assessment_group_registration (group_id, assessment_id, date_registration, registered_by,available_time,date_availability,registration_valid_till, total_access_time) values ('" + groupId + "','" + assessmentcode + "',sysdate(),'" + strCreatedBy + "','" + availableTime + "','" + availableDate + "','" + strValidTill + "','" + strTotalTime + "')");
            count = oStmt.executeUpdate("insert into assessment_group_registration (group_id, assessment_id, date_registration, registered_by,available_time,date_availability,registration_valid_till, total_access_time) values ('" + groupId + "','" + assessmentcode + "',sysdate(),'" + strCreatedBy + "','" + availableTime + "','" + availableDate + "','" + strValidTill + "','" + strTotalTime + "')");
            oStmt.close();
        }
        catch (SQLException e) {
            System.out.println("Error due to SQL exception insertAssessmentGroupRegistration====: " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
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
    
    public static synchronized void deleteTempGroupAssessment() {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            oStmt.execute("delete from temp_assessment_group_registration");
            oStmt.close();
        }
        catch (SQLException e) {
            System.out.println(" error due to SQL exception deleteTempGroupAssessment " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
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
    
    public static synchronized void deleteTempAssessment() {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            oStmt.execute("delete from temp_assessment_user_registration");
            oStmt.close();
        }
        catch (SQLException e) {
            System.out.println(" error due to SQL exception deleteTempAssessment " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
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
    
    public static boolean isUserRegistered(final String studentId, final String assessmentcode) {
        boolean flag = false;
        String str = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            DataBaseLayer.log.debug("SELECT a.student_id FROM assessment_user_registration a, assessment_management am WHERE am.assessment_id=a.assessment_id AND am.assessment_code='" + assessmentcode + "' AND a.student_id='" + studentId + "'");
            final ResultSet oRset = oStmt.executeQuery("SELECT a.student_id FROM assessment_user_registration a, assessment_management am WHERE am.assessment_id=a.assessment_id AND am.assessment_code='" + assessmentcode + "' AND a.student_id='" + studentId + "'");
            if (oRset.next()) {
                str = oRset.getString(1);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug(" isUserRegistered" + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        flag = !str.equals("");
        return flag;
    }
    
    public static boolean isuserAssessmentExists(final String studentId, final String assessmentcode) {
        boolean flag = false;
        String str = "";
        String assessmentTitle = "";
        String stu_name = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            final Statement oStmt3 = oConn.createStatement();
            DataBaseLayer.log.debug("SELECT a.title FROM assessment_management a WHERE a.assessment_code='" + assessmentcode + "'");
            final ResultSet oRset = oStmt.executeQuery("SELECT a.title FROM assessment_management a WHERE a.assessment_code='" + assessmentcode + "'");
            if (oRset.next()) {
                assessmentTitle = oRset.getString(1);
            }
            DataBaseLayer.log.debug("select student_id from student_details where student_id='" + studentId + "'");
            final ResultSet oRset2 = oStmt2.executeQuery("select student_id from student_details where student_id='" + studentId + "' ");
            if (oRset2.next()) {
                stu_name = oRset2.getString(1);
            }
            DataBaseLayer.log.debug("SELECT a.student_id FROM assessment_user_registration a, assessment_management am WHERE am.assessment_id=a.assessment_id AND am.assessment_code='" + assessmentcode + "' AND a.student_id='" + studentId + "'");
            final ResultSet oRset3 = oStmt.executeQuery("SELECT a.student_id FROM assessment_user_registration a, assessment_management am WHERE am.assessment_id=a.assessment_id AND am.assessment_code='" + assessmentcode + "' AND a.student_id='" + studentId + "'");
            if (oRset3.next()) {
                str = oRset3.getString(1);
            }
            DataBaseLayer.log.debug("===============assessmentTitle==========" + assessmentTitle);
            DataBaseLayer.log.debug("===============stu_name==========" + stu_name);
            DataBaseLayer.log.debug("===============str==========" + str);
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug(" isStudentGroupExists" + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        flag = (assessmentTitle.equals("") || stu_name.equals("") || !str.equals(""));
        DataBaseLayer.log.debug("===============flag==========" + flag);
        return flag;
    }
    
    public static synchronized void insertAssessmentuserRegistration(final String strUsrId, final String assessmentcode, final String strCreatedBy, final String availableTime, final String availableDate, final String strValidTill, final String strTotalTime) {
        int count = 0;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            Statement oStmt = oConn.createStatement();
            oStmt = oConn.createStatement();
            System.out.println("insert into assessment_user_registration (student_id, assessment_id, date_registration, registered_by,available_time,date_availability,registration_valid_till, total_access_time) values ('" + strUsrId + "','" + assessmentcode + "',sysdate(),'" + strCreatedBy + "','" + availableTime + "','" + availableDate + "','" + strValidTill + "','" + strTotalTime + "')");
            count = oStmt.executeUpdate("insert into assessment_user_registration (student_id, assessment_id, date_registration, registered_by,available_time,date_availability,registration_valid_till, total_access_time) values ('" + strUsrId + "','" + assessmentcode + "',sysdate(),'" + strCreatedBy + "','" + availableTime + "','" + availableDate + "','" + strValidTill + "','" + strTotalTime + "')");
            oStmt.close();
        }
        catch (SQLException e) {
            System.out.println("Error due to SQL exception insertAssessmentuserRegistration====: " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
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
    
    public static synchronized int insertTempAssessmentuserRegistration(final String strUsrId, final String assessmentcode, final String strCreatedBy, final String availableTime, final String availableDate, final String strValidTill, final String strTotalTime) {
        int count = 0;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            Statement oStmt = oConn.createStatement();
            oStmt = oConn.createStatement();
            System.out.println("insert into temp_assessment_user_registration (student_id, assessment_id, date_registration, registered_by,available_time,date_availability,registration_valid_till, total_access_time) values ('" + strUsrId + "','" + assessmentcode + "',sysdate(),'" + strCreatedBy + "','" + availableTime + "','" + availableDate + "','" + strValidTill + "','" + strTotalTime + "')");
            count = oStmt.executeUpdate("insert into temp_assessment_user_registration (student_id, assessment_id, date_registration, registered_by,available_time,date_availability,registration_valid_till, total_access_time) values ('" + strUsrId + "','" + assessmentcode + "',sysdate(),'" + strCreatedBy + "','" + availableTime + "','" + availableDate + "','" + strValidTill + "','" + strTotalTime + "')");
            oStmt.close();
        }
        catch (SQLException e) {
            System.out.println("Error due to SQL exception insertTempAssessmentuserRegistration====: " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return count;
    }
    
    public static synchronized void modifyAssessmentUserregistration(final String strUsrId, final String assessmentcode, final String strCreatedBy, final String availableTime, final String availableDate, final String strValidTill, final String strTotalTime) {
        int count = 0;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            oStmt2.execute("DELETE FROM assessment_user_registration WHERE student_id = '" + strUsrId + "' AND assessment_id='" + assessmentcode + "'");
            System.out.println("Modify Registration == insert into assessment_user_registration (student_id, assessment_id, date_registration, registered_by,available_time,date_availability,registration_valid_till, total_access_time) values ('" + strUsrId + "','" + assessmentcode + "',sysdate(),'" + strCreatedBy + "','" + availableTime + "','" + availableDate + "','" + strValidTill + "','" + strTotalTime + "')");
            count = oStmt.executeUpdate("insert into assessment_user_registration (student_id, assessment_id, date_registration, registered_by,available_time,date_availability,registration_valid_till, total_access_time) values ('" + strUsrId + "','" + assessmentcode + "',sysdate(),'" + strCreatedBy + "','" + availableTime + "','" + availableDate + "','" + strValidTill + "','" + strTotalTime + "')");
            oStmt.close();
        }
        catch (SQLException e) {
            System.out.println("Error due to SQL exception insertAssessmentuserRegistration====: " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
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
    
    public static synchronized Vector getAssessmentRegistrationInfo() {
        final Vector vTempAssessmentInfo = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            System.out.println("SELECT t.student_id, t.assessment_id, t.date_availability,t.available_time,t.registration_valid_till, t.total_access_time FROM temp_assessment_user_registration t");
            final ResultSet oRset = oStmt.executeQuery("SELECT t.student_id, t.assessment_id, t.date_availability,t.available_time,t.registration_valid_till, t.total_access_time FROM temp_assessment_user_registration t");
            while (oRset.next()) {
                vTempAssessmentInfo.addElement(oRset.getString(1));
                vTempAssessmentInfo.addElement(oRset.getString(2));
                vTempAssessmentInfo.addElement(oRset.getString(3));
                vTempAssessmentInfo.addElement(oRset.getString(4));
                vTempAssessmentInfo.addElement(oRset.getString(5));
                vTempAssessmentInfo.addElement(oRset.getString(6));
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            System.out.println(" error due to SQL exception getAssessmentRegistrationInfo " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vTempAssessmentInfo;
    }
    
    public static synchronized Vector getAssessmentGroupRegistrationInfo() {
        final Vector vTempAssessmentInfo = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            System.out.println("SELECT t.group_id, t.assessment_id, t.date_availability,t.available_time,t.registration_valid_till, t.total_access_time FROM temp_assessment_group_registration t");
            final ResultSet oRset = oStmt.executeQuery("SELECT t.group_id, t.assessment_id, t.date_availability,t.available_time,t.registration_valid_till, t.total_access_time FROM temp_assessment_group_registration t");
            while (oRset.next()) {
                vTempAssessmentInfo.addElement(oRset.getString(1));
                vTempAssessmentInfo.addElement(oRset.getString(2));
                vTempAssessmentInfo.addElement(oRset.getString(3));
                vTempAssessmentInfo.addElement(oRset.getString(4));
                vTempAssessmentInfo.addElement(oRset.getString(5));
                vTempAssessmentInfo.addElement(oRset.getString(6));
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            System.out.println(" error due to SQL exception getAssessmentGroupRegistrationInfo " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vTempAssessmentInfo;
    }
    
    public static Vector<String> getStudentsForResult(final String assessment_id) {
        final Vector<String> vAllDetails = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement1 = oConn.createStatement();
            final ResultSet resultset1 = statement1.executeQuery("SELECT distinct a.student_id from student_details a,assessment_user_registration b,student_group_association c,assessment_group_registration d where ((b.student_id=a.student_id and b.assessment_id='" + assessment_id + "') or (c.group_id=d.group_id and c.student_id=a.student_id and d.assessment_id='" + assessment_id + "')) and c.student_id<>b.student_id");
            while (resultset1.next()) {
                vAllDetails.addElement(resultset1.getString(1));
            }
            statement1.close();
            resultset1.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("getStudentsForResult: error due to SQL exception");
            DataBaseLayer.log.fatal("SQLException:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
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
    
    public static Vector<String> getAssessmentDetailResult(final String assessment_id, final String student_id) {
        final Vector<String> vAllDetails = new Vector<String>();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement1 = oConn.createStatement();
            final ResultSet resultset1 = statement1.executeQuery("select a.title, concat(b.first_name,' ',b.middle_name,'',b.sname), c.full_marks, c.marks_obtained, c.status, b.student_id  from assessment_management a, student_details b, stassessment_test_details c  where a.assessment_id=c.assessment_id and a.assessment_id='" + assessment_id + "' and b.student_id=c.student_id and  b.student_id='" + student_id + "'  ");
            while (resultset1.next()) {
                vAllDetails.addElement(resultset1.getString(1));
                vAllDetails.addElement(resultset1.getString(2));
                vAllDetails.addElement(resultset1.getString(3));
                vAllDetails.addElement(resultset1.getString(4));
                vAllDetails.addElement(resultset1.getString(5));
                vAllDetails.addElement(resultset1.getString(6));
            }
            statement1.close();
            resultset1.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("getAssessmentDetailResult: error due to SQL exception");
            DataBaseLayer.log.fatal("SQLException:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
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
    
    public static boolean IsStudentGiveTest(final String assess_id, final String student_id) {
        boolean yes = false;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            System.out.println("select test_id from stassessment_test_details where assessment_id='" + assess_id + "' and student_id='" + student_id + "'");
            final ResultSet resultset = statement.executeQuery("select test_id from stassessment_test_details where assessment_id='" + assess_id + "' and student_id='" + student_id + "'");
            if (resultset.next()) {
                yes = true;
            }
            statement.close();
            resultset.close();
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
        System.out.println("yes===" + yes);
        return yes;
    }
    
    public static synchronized void updateMailSentStatus(final String assess_id, final String student_id, final String status) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("update stassessment_test_details set mail_status='" + status + "' where assessment_id='" + assess_id + "' and student_id='" + student_id + "'");
            statement.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("Inside ReportDataBaseLayer updateMailSentStatus(), Error due to SQL exception!");
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("Inside NewDataBaseLayer updateMailSentStatus(), exception!");
            DataBaseLayer.log.fatal(" printStack is :: " + exception.getMessage());
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
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
    
    public static synchronized void createTableSummary(final String AssessTitleSelect) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            oStmt.execute("delete from temp_report_summary;");
            oStmt.close();
            final Statement oStmt2 = oConn.createStatement();
            final ResultSet oRset6 = oStmt2.executeQuery("select count(st.student_id) from stassessment_test_details st where st.marks_obtained=st.full_marks and st.assessment_id='" + AssessTitleSelect + "'");
            while (oRset6.next()) {
                final String c1 = oRset6.getString(1);
                final Statement statement = oConn.createStatement();
                final boolean flag = statement.execute("insert into temp_report_summary(annexure,percentage_marks,no_of_employee) values ('A','EMPLOYEES SECURED 100 PERCENT','" + c1 + "')");
                statement.close();
            }
            final String topfive = getTopFive(AssessTitleSelect);
            if (topfive != null) {
                final Statement statement = oConn.createStatement();
                final boolean flag = statement.execute("insert into temp_report_summary(annexure,percentage_marks,no_of_employee) values ('B','FIRST FIVE TOPPERS','" + topfive + "')");
                statement.close();
            }
            final Statement oStmt3 = oConn.createStatement();
            final ResultSet oRset2 = oStmt3.executeQuery("select count(st.student_id) from stassessment_test_details st where st.marks_obtained>=st.full_marks*75/100 and st.assessment_id='" + AssessTitleSelect + "'");
            while (oRset2.next()) {
                final String c2 = oRset2.getString(1);
                final Statement statement = oConn.createStatement();
                final boolean flag2 = statement.execute("insert into temp_report_summary(annexure,percentage_marks,no_of_employee) values ('C','75 PERCENT AND ABOVE','" + c2 + "')");
                statement.close();
            }
            final Statement oStmt4 = oConn.createStatement();
            final ResultSet oRset3 = oStmt4.executeQuery("select count(st.student_id) from stassessment_test_details st where st.marks_obtained>=st.full_marks*60/100 and st.marks_obtained<st.full_marks*75/100 and st.assessment_id='" + AssessTitleSelect + "'");
            while (oRset3.next()) {
                final String c2 = oRset3.getString(1);
                final Statement statement = oConn.createStatement();
                final boolean flag2 = statement.execute("insert into temp_report_summary(annexure,percentage_marks,no_of_employee) values ('D','60 PERCENT AND ABOVE BUT LESS THAN 75 PERCENT','" + c2 + "')");
                statement.close();
            }
            final Statement oStmt5 = oConn.createStatement();
            final ResultSet oRset4 = oStmt5.executeQuery("select count(st.student_id) from stassessment_test_details st where st.marks_obtained>=st.full_marks*50/100 and st.marks_obtained<st.full_marks*60/100 and st.assessment_id='" + AssessTitleSelect + "'");
            while (oRset4.next()) {
                final String c2 = oRset4.getString(1);
                final Statement statement = oConn.createStatement();
                final boolean flag2 = statement.execute("insert into temp_report_summary(annexure,percentage_marks,no_of_employee) values ('E','50 PERCENT AND ABOVE BUT LESS THAN 60 PERCENT','" + c2 + "')");
                statement.close();
            }
            final Statement oStmt6 = oConn.createStatement();
            final ResultSet oRset5 = oStmt6.executeQuery("select count(st.student_id) from stassessment_test_details st where st.marks_obtained<st.full_marks*50/100 and st.assessment_id='" + AssessTitleSelect + "'");
            while (oRset5.next()) {
                final String c2 = oRset5.getString(1);
                final Statement statement = oConn.createStatement();
                final boolean flag2 = statement.execute("insert into temp_report_summary(annexure,percentage_marks,no_of_employee) values ('F','LESS THAN 50 PERCENT','" + c2 + "')");
                statement.close();
            }
            oConn.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("createTableSummary: error due to SQL exception" + e);
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
    
    public static synchronized String getTopFive(final String AssessTitleSelect) {
        final Vector<String> vTestInfo = new Vector();
        final Vector<String> v = new Vector();
        int count = 0;
        String tf = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select std.student_id, std.test_id, std.date, std.time_started, std.total_no_of_ques, std.full_marks, std.marks_obtained from stassessment_test_details std where std.assessment_id='" + AssessTitleSelect + "' order by std.marks_obtained");
            while (oRset.next()) {
                final String obmarks = oRset.getString(7);
                DataBaseLayer.log.debug("obmarks==" + obmarks);
                if (obmarks != null) {
                    vTestInfo.addElement(obmarks);
                }
            }
            oRset.close();
            oStmt.close();
            DataBaseLayer.log.debug("vTestInfo.size()==" + vTestInfo.size());
            if (vTestInfo.size() >= 5) {
                int i = vTestInfo.size() - 1;
                while (count < 5) {
                    final String obmarks2 = vTestInfo.elementAt(i);
                    DataBaseLayer.log.debug("obmarks1==" + obmarks2);
                    if (i != vTestInfo.size() - 1) {
                        final String s1 = v.lastElement();
                        if (!obmarks2.equals(s1)) {
                            v.addElement(obmarks2);
                            ++count;
                        }
                        else {
                            v.addElement(obmarks2);
                        }
                    }
                    else {
                        v.addElement(obmarks2);
                        ++count;
                    }
                    --i;
                }
            }
            else {
                int i = vTestInfo.size() - 1;
                while (count < vTestInfo.size() - 1) {
                    final String obmarks2 = vTestInfo.elementAt(i);
                    DataBaseLayer.log.debug("obmarks1==" + obmarks2);
                    if (i != vTestInfo.size() - 1) {
                        final String s1 = v.lastElement();
                        if (!obmarks2.equals(s1)) {
                            v.addElement(obmarks2);
                            ++count;
                        }
                        else {
                            v.addElement(obmarks2);
                        }
                    }
                    else {
                        v.addElement(obmarks2);
                        ++count;
                    }
                    --i;
                }
            }
            final Integer vsize = v.size();
            tf = Integer.toString(vsize);
            oConn.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getTopFive: error due to SQL exception" + e);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return tf;
    }
    
    public static Vector getDesidnation() {
        final Vector v = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select distinct designation from ubi_user_other_details");
            while (oRset.next()) {
                final Vector v2 = new Vector();
                v2.addElement(oRset.getString(1));
                v.addElement(v2);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getDesidnation: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getDesidnation printStack is :: " + ex.getMessage());
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
    
    public static Vector getassessmnetRegiDetailsInfo(final String shortedby) {
        final Vector vassessmentdetails1 = new Vector(1, 1);
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select t.`report_id`,  t.`student_id`, t.`student_name`, t.`assessment_title`, t.`available_time`, t.`date_availability`, t.`access_allowed_till`, t.`total_access_time` from temp_aasessment_regis_report t order by '" + shortedby + "'");
            while (oRset.next()) {
                final String report_id = oRset.getString(1);
                final String student_id = oRset.getString(2);
                final String name = oRset.getString(3);
                final String assessment_title = oRset.getString(4);
                final String available_time = oRset.getString(5);
                final String date_availability = oRset.getString(6);
                final String access_allowed_till = oRset.getString(7);
                final String total_access_time = oRset.getString(8);
                final String[] strcal1 = { report_id, student_id, name, assessment_title, available_time, date_availability, access_allowed_till, total_access_time, null };
                vassessmentdetails1.addElement(strcal1);
            }
            statement.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("getuserDetailsInfo: SQLException:" + sqlexception);
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getuserDetailsInfo printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vassessmentdetails1;
    }
    
    public static synchronized void createTempAssessmentRegistration(final String AssessTitleSelect, final String selectgroup) {
        String sql = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            oStmt.execute("delete from temp_aasessment_regis_report;");
            oStmt.close();
            final Statement oStmt2 = oConn.createStatement();
            DataBaseLayer.log.debug("selectgroup===" + selectgroup);
            if (!selectgroup.equals("0") && !selectgroup.equals("All")) {
                sql = "select s.student_id,concat(s.first_name,' ',s.middle_name,' ',s.sname),a.title,ar.available_time,date_format(ar.date_availability,'%y-%m-%d'),date_format(ar.registration_valid_till,'%y-%m-%d'),ar.total_access_time from student_details s,assessment_group_registration ar, assessment_management a,student_group_association sg where  sg.group_id=ar.group_id and s.student_id=sg.student_id and a.assessment_id=ar.assessment_id and ar.assessment_id='" + AssessTitleSelect + "' and ar.group_id='" + selectgroup + "' order by s.student_id";
            }
            else if (selectgroup.equals("0") && !selectgroup.equals("All")) {
                sql = "select s.student_id,concat(s.first_name,' ',s.middle_name,' ',s.sname), a.title,ar.available_time,date_format(ar.date_availability,'%y-%m-%d'), date_format(ar.registration_valid_till,'%y-%m-%d'),ar.total_access_time   from student_details s,assessment_user_registration ar, assessment_management a  where s.student_id=ar.student_id and a.assessment_id=ar.assessment_id and ar.assessment_id='" + AssessTitleSelect + "'  order by s.student_id ";
            }
            else if (!selectgroup.equals("0") && selectgroup.equals("All")) {
                sql = "select s.student_id,concat(s.first_name,' ',s.middle_name,' ',s.sname),a.title,ar.available_time,date_format(ar.date_availability,'%y-%m-%d'), date_format(ar.registration_valid_till,'%y-%m-%d'),ar.total_access_time   from student_details s,assessment_user_registration ar, assessment_management a  where s.student_id=ar.student_id and a.assessment_id=ar.assessment_id and ar.assessment_id='" + AssessTitleSelect + "'  union " + "select s.student_id,concat(s.first_name,' ',s.middle_name,' ',s.sname),a.title,ar.available_time,date_format(ar.date_availability,'%y-%m-%d'),date_format(ar.registration_valid_till,'%y-%m-%d'),ar.total_access_time from student_details s, assessment_group_registration ar, assessment_management a,student_group_association sg where sg.group_id=ar.group_id and s.student_id=sg.student_id and a.assessment_id=ar.assessment_id and ar.assessment_id='" + AssessTitleSelect + "'";
            }
            DataBaseLayer.log.debug(sql);
            final ResultSet oRset2 = oStmt2.executeQuery(sql);
            while (oRset2.next()) {
                final String student_id = oRset2.getString(1);
                final String name = oRset2.getString(2);
                final String assessment_title = oRset2.getString(3);
                final String available_time = oRset2.getString(4);
                final String available_date = oRset2.getString(5);
                final String access_allowed_till = oRset2.getString(6);
                final String total_access_time = oRset2.getString(7);
                final Statement statement = oConn.createStatement();
                final boolean flag = statement.execute("insert into temp_aasessment_regis_report(student_id,student_name,assessment_title,available_time,date_availability,access_allowed_till,total_access_time) values ('" + student_id + "','" + name + "','" + assessment_title + "','" + available_time + "','" + available_date + "','" + access_allowed_till + "','" + total_access_time + "')");
                statement.close();
            }
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("createTempAssessmentRegistration: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("createTempAssessmentRegistration printStack is :: " + ex.getMessage());
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
    
    public static Vector<Vector> getQuestionBankDetails(final String qb_id) {
        final Vector vOBdetails = new Vector(3, 3);
        final Vector vOBdetails2 = new Vector(3, 3);
        final Vector vOBdetails3 = new Vector(3, 3);
        final Vector vOBdetails4 = new Vector(3, 3);
        final Vector vOBdetails5 = new Vector(3, 3);
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            String strDatabaseEncrptPassword = "";
            final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
            final String strkey = "DatabaseEncrptPassword";
            strDatabaseEncrptPassword = rb.getString(strkey);
            DataBaseLayer.log.debug("************strDatabaseEncrptPassword******************" + strDatabaseEncrptPassword);
            DataBaseLayer.log.debug("select assess_id from qb_mgmt_item where  qb_id ='" + qb_id + "'");
            final ResultSet resultset = statement.executeQuery("select assess_id from qb_mgmt_item where  qb_id ='" + qb_id + "'");
            while (resultset.next()) {
                final String[] idetails1 = { null };
                final String[] idetails2 = { null };
                final String qbankid = resultset.getString(1);
                idetails1[0] = qbankid;
                final int qbankid2 = Integer.parseInt(qbankid);
                DataBaseLayer.log.debug("select AES_DECRYPT(assess_text,'" + strDatabaseEncrptPassword + "')  from qb_mgmt_item where assess_id ='" + qbankid2 + "'");
                final ResultSet resultset2 = statement2.executeQuery("select AES_DECRYPT(assess_text,'" + strDatabaseEncrptPassword + "')  from qb_mgmt_item where assess_id ='" + qbankid2 + "'");
                if (resultset2.next()) {
                    final String atext = resultset2.getString(1);
                    idetails2[0] = atext;
                    vOBdetails2.addElement(idetails2);
                }
            }
            vOBdetails.addElement(vOBdetails2);
            statement.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("SQLException in getQuestionBankDetails method ");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("Exception in getQuestionBankDetails method");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vOBdetails;
    }
    
    public static synchronized boolean deleteAllPreviewResult(final int AssessTitleSelect1) {
        boolean flag = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        boolean flag5 = false;
        boolean flag6 = false;
        boolean flag7 = false;
        boolean flag8 = false;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final Statement oStmt = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("SELECT user_id FROM all_preview_details where preview_type='asmtpreview' and preview_item_id='" + AssessTitleSelect1 + "'");
            while (resultset.next()) {
                final String user_id = resultset.getString(1);
                flag = oStmt.execute("delete from assessment_progress_status where assessment_id = '" + AssessTitleSelect1 + "' and student_id = '" + user_id + "'");
                final ResultSet resultset2 = statement2.executeQuery("select test_id FROM stassessment_test_details where assessment_id='" + AssessTitleSelect1 + "' and student_id='" + user_id + "'");
                while (resultset2.next()) {
                    final String test_id = resultset2.getString(1);
                    flag3 = oStmt.execute("delete from stassessment_question_details where test_id = '" + test_id + "' and student_id = '" + user_id + "'");
                }
                flag2 = oStmt.execute("delete from stassessment_test_details where assessment_id = '" + AssessTitleSelect1 + "' and student_id = '" + user_id + "'");
                flag8 = oStmt.execute("delete from assessment_user_registration where student_id = '" + user_id + "' and assessment_id='" + AssessTitleSelect1 + "'");
                flag4 = oStmt.execute("delete from student_details where student_id = '" + user_id + "'");
                flag5 = oStmt.execute("delete from student_creation_details where student_id = '" + user_id + "'");
                flag6 = oStmt.execute("delete from student_password where student_id = '" + user_id + "'");
                flag7 = oStmt.execute("delete from all_preview_details where user_id = '" + user_id + "' and preview_type='asmtpreview' and preview_item_id='" + AssessTitleSelect1 + "'");
            }
            oStmt.close();
            statement.close();
            statement2.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("deleteAllPreviewResult: Error due to SQL exception : " + e.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("deleteAllPreviewResult printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return flag;
    }
    
    public static synchronized void updateQuestionBank(final String docId, final String size, final String name) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            String sql="update qb_mgmt set file_name = '" + name + "',file_size='" + size + "',refresh_on=NOW() where qb_id = '" + docId + "' ";
            oStmt.executeUpdate(sql);
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.fatal("SQLException:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
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
    
    public static void getusermodelforAssessment(final String c_id) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final Statement statement3 = oConn.createStatement();
            final Statement oStmt11 = oConn.createStatement();
            oStmt11.execute("delete from temp_asmtresult_user_model;");
            oStmt11.close();
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
                    double per = 0.0;
                    if (marks_obtained2 != 0.0) {
                        per = Double.valueOf(Math.floor(marks_obtained2 * 100.0 / total_marks2));
                    }
                    final String per2 = Double.toString(per);
                    strcal1[8] = per2;
                    strcal1[1] = oRset2.getString(1);
                    strcal1[10] = assessment_id;
                    System.out.println("insert into temp_asmtresult_user_model(student_id,student_name,assessment_type,test_name,section,testdate,no_of_questions,no_of_questions_attempted,no_of_correct_ans,total_marks,marks_obtained,percent_marks) values ('" + user_id + "','" + user_name + "',qtiassessment,'" + assessment_id + "','" + strcal1[1] + "','" + assessment_date + "','" + strcal1[3] + "','" + strcal1[4] + "','" + strcal1[5] + "','" + total_marks + "','" + marks_obtained + "','" + per + "')");
                    final boolean flag = statement3.execute("insert into temp_asmtresult_user_model(student_id,student_name,assessment_type,test_name,section,testdate,no_of_questions,no_of_questions_attempted,no_of_correct_ans,total_marks,marks_obtained,percent_marks) values ('" + user_id + "','" + user_name + "','qtiassessment','" + assessment_id + "','" + strcal1[1] + "','" + assessment_date + "','" + strcal1[3] + "','" + strcal1[4] + "','" + strcal1[5] + "','" + total_marks + "','" + marks_obtained + "','" + per + "')");
                }
                oRset2.close();
            }
            oRset.close();
            statement.close();
            statement2.close();
            statement3.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("SQLException:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("Exception:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
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
    
    public static void getUserScoInfoResult(final String c_id) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final Statement statement3 = oConn.createStatement();
            final Statement statement4 = oConn.createStatement();
            final Statement oStmt11 = oConn.createStatement();
            oStmt11.execute("delete from temp_asmtresult_scorm_model;");
            oStmt11.close();
            final ResultSet resultset = statement.executeQuery("select user_id, sco_id,maxscore,rawscore,lessonstatus from userscoinfo where unit_id ='" + c_id + "' and maxscore !=\"\" and rawscore !=\"\"");
            while (resultset.next()) {
                final String[] strcal = new String[6];
                final String strid = resultset.getString(1);
                String assessment_id = "";
                final ResultSet resultset2 = statement4.executeQuery("select assessment_id from assessment_usermodel where unit_id ='" + c_id + "'");
                if (resultset2.next()) {
                    assessment_id = resultset2.getString(1);
                }
                final ResultSet resultset3 = statement2.executeQuery("select   account_status ,concat(first_name,' ',middle_name,' ',sname) from student_details where student_id ='" + strid + "'");
                if (resultset3.next()) {
                    final String s = resultset3.getString(1);
                    strcal[0] = strid;
                    strcal[1] = resultset3.getString(2);
                    strcal[2] = resultset.getString(2);
                    strcal[3] = resultset.getString(3);
                    strcal[4] = resultset.getString(4);
                    strcal[5] = resultset.getString(5);
                }
                DataBaseLayer.log.debug("insert into temp_asmtresult_scorm_model(student_id,student_name,test_name,total_marks,marks_obtained,status) values ('" + strid + "','" + strcal[1] + "','" + assessment_id + "','" + strcal[3] + "','" + strcal[4] + "','" + strcal[5] + "')");
                final boolean flag = statement3.execute("insert into temp_asmtresult_scorm_model(student_id,student_name,test_name,total_marks,marks_obtained,status) values ('" + strid + "','" + strcal[1] + "','" + assessment_id + "','" + strcal[3] + "','" + strcal[4] + "','" + strcal[5] + "')");
                resultset3.close();
                resultset2.close();
            }
            resultset.close();
            statement.close();
            statement2.close();
            statement3.close();
            statement4.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("SQLException:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("Exception:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
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
    
    public static synchronized void updateScormTable(final String unit_id, final String assessment_id, final String student_id) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final Statement statement3 = oConn.createStatement();
            final Statement statement4 = oConn.createStatement();
            final Statement statement5 = oConn.createStatement();
            final Statement statement6 = oConn.createStatement();
            float sum = 0.0f;
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
            System.out.println("select max(c.item_data_record_id) from assessment_usermodel a,assessment_section_data b, essay_type_data c where a.assessment_data_record_id=b.assessment_data_record_id and b.section_data_record_id=c.section_data_record_id and a.unit_id='" + unit_id + "'and a.user_id='" + student_id + "'");
            if (oRset2.next()) {
                item_data_record_id = Integer.parseInt(oRset2.getString(1));
            }
            final ResultSet oRset3 = statement3.executeQuery("select marks_obtained,section_data_record_id from essay_type_data where item_data_record_id='" + item_data_record_id + "'");
            if (oRset3.next()) {
                final float num = Float.parseFloat(oRset3.getString(1));
                section_data_record_id = Integer.parseInt(oRset3.getString(2));
                sum += num;
            }
            final ResultSet oRset4 = statement.executeQuery("select a.assessment_data_record_id from assessment_section_data a, essay_type_data b where a.section_data_record_id=b.section_data_record_id and item_data_record_id='" + item_data_record_id + "';");
            if (oRset4.next()) {
                assessment_data_record_id = Integer.parseInt(oRset4.getString(1));
            }
            oRset5 = statement4.executeQuery("select marks_obtained from assessment_section_data where assessment_data_record_id='" + assessment_data_record_id + "' and section_data_record_id!='" + section_data_record_id + "';");
            if (oRset5.next()) {
                final float num2 = Float.parseFloat(oRset5.getString(1));
                sum += num2;
            }
            final ResultSet oRset6 = statement5.executeQuery("select minscore from userscoinfo where user_id='" + student_id + "' and unit_id='" + unit_id + "' ;");
            if (oRset6.next()) {
                final float MinScore = Float.parseFloat(oRset6.getString(1));
                System.out.println("MinScore===test7====" + MinScore);
                System.out.println("sum===test8====" + sum);
                if (MinScore <= sum) {
                    status = "Passed";
                }
                else {
                    status = "Failed";
                }
            }
            System.out.println("update userscoinfo set rawscore = '" + sum + "',lessonstatus='" + status + "'  where user_id ='" + student_id + "' and unit_id = '" + unit_id + "'");
            final boolean flag = statement.execute("update userscoinfo set rawscore = '" + sum + "',lessonstatus='" + status + "'  where user_id ='" + student_id + "' and unit_id = '" + unit_id + "'");
            statement.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("Inside DataBaseLayer updateScormTable, Error due to SQL exception!");
            final int i = sqlexception.getErrorCode();
            final String s9 = sqlexception.getMessage();
            DataBaseLayer.log.fatal("The Error is :- " + s9);
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("Inside DataBaseLayer updateScormTable, exception!");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
            DataBaseLayer.log.fatal(" printStack is :: " + exception.getMessage());
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
        Connection oConn = null;
        final Vector<String> vUser = new Vector();
        int cc = 0;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
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
            DataBaseLayer.log.fatal("Inside DataBaseLayer updateScormTableAllUser, Error due to SQL exception!");
            final int j = sqlexception.getErrorCode();
            final String s9 = sqlexception.getMessage();
            DataBaseLayer.log.fatal("The Error is :- " + s9);
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("Inside DataBaseLayer updateScormTableAllUser, exception!");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
            DataBaseLayer.log.fatal(" printStack is updateScormTableAllUser :: " + exception.getMessage());
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
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select assessment_data_record_id from assessment_usermodel where unit_id ='" + unit_id + "' and user_id='" + student_id + "'");
            while (oRset.next()) {
                final String assessment_id = oRset.getString(1);
                updateScormTable(unit_id, assessment_id, student_id);
            }
            statement.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("Inside DataBaseLayer updateScormTableAllAssess, Error due to SQL exception!");
            final int i = sqlexception.getErrorCode();
            final String s9 = sqlexception.getMessage();
            DataBaseLayer.log.fatal("The Error is :- " + s9);
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("Inside DataBaseLayer updateScormTableAllAssess, exception!");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
            DataBaseLayer.log.fatal(" printStack is updateScormTableAllAssess :: " + exception.getMessage());
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
        final Vector<String> vUser = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
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
            DataBaseLayer.log.fatal("Inside DataBaseLayer updateScormTableAllAssess, Error due to SQL exception!");
            final int j = sqlexception.getErrorCode();
            final String s9 = sqlexception.getMessage();
            DataBaseLayer.log.fatal("The Error is :- " + s9);
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("Inside DataBaseLayer updateScormTableAllAssess, exception!");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
            DataBaseLayer.log.fatal(" printStack is updateScormTableAllAssess :: " + exception.getMessage());
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
    
    public static Vector<String[]> getReport(final String ass_id, final String unit_id) {
        final Vector<String[]> vListUsage = new Vector<String[]>(3, 3);
        String ass_id2 = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
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
            DataBaseLayer.log.fatal("SQLException:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("Exception:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
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
    
    public static synchronized Vector<Vector<String>> getQbDetails(final String user_id) {
        final Vector<Vector<String>> vList = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select qb_id,qb_name from qb_mgmt where upload_by='" + user_id + "'");
            while (oRset.next()) {
                final Vector<String> v = new Vector<String>();
                v.addElement(oRset.getString(1));
                v.addElement(oRset.getString(2));
                vList.addElement(v);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getTopicList: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getTopicList printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vList;
    }
    
    public static synchronized String getSelectorQuery(final String interface_id, final String content_id, final String part_id) {
        String s_query = "";
        Connection oConn = null;
        Statement statement = null;
        ResultSet oRset = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            statement = oConn.createStatement();
            oRset = statement.executeQuery("select value from content where interface_id='" + interface_id + "' and content_id='" + content_id + "' and part_id='" + part_id + "'");
            while (oRset.next()) {
                s_query = oRset.getString(1);
            }
            System.out.println("=====update======s_query==========" + s_query);
            oRset.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        finally {
            if (oConn != null) {
                try {
                    oRset.close();
                    statement.close();
                    oConn.close();
                }
                catch (Exception ex) {}
            }
        }
        return s_query;
    }
    
    public static void updateselectoractiveQuery(final String interface_id, final String content_id, final String part_id, final String sql_query) {
        final ResultSet oRset = null;
        final String s_query = "";
        try {
            final Connection oConn = DataBaseLayer.ds1.getConnection();
            final PreparedStatement pstmt = oConn.prepareStatement("update content set active_value=? where interface_id=? and content_id=? and part_id=?");
            pstmt.setString(1, sql_query);
            pstmt.setString(2, interface_id);
            pstmt.setString(3, content_id);
            pstmt.setString(4, part_id);
            pstmt.execute();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
    }
    
    public static synchronized String getSelectorActiveQuery(final String interface_id, final String content_id, final String part_id) {
        String s_query = "";
        Connection oConn = null;
        Statement statement = null;
        ResultSet oRset = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            statement = oConn.createStatement();
            oRset = statement.executeQuery("select active_value from content where interface_id='" + interface_id + "' and content_id='" + content_id + "' and part_id='" + part_id + "'");
            while (oRset.next()) {
                s_query = oRset.getString(1);
            }
            System.out.println("=====update======s_query==========" + s_query);
            oRset.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        finally {
            if (oConn != null) {
                try {
                    oRset.close();
                    statement.close();
                    oConn.close();
                }
                catch (Exception ex) {}
            }
        }
        return s_query;
    }
    
    public static synchronized Vector<Vector<String>> getData(final String sql_query) {
        final Vector<Vector<String>> vList = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery(sql_query);
            while (oRset.next()) {
                final Vector<String> v = new Vector<String>();
                v.addElement(oRset.getString(1));
                v.addElement(oRset.getString(2));
                vList.addElement(v);
            }
            oRset.close();
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getTopicList: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getTopicList printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vList;
    }
    
    public static synchronized void refreshQuestionBank(final Vector<String> vQBID, final Vector<String> vQBName, final String user_id) {
        ResultSet oRset1 = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            for (int i = 0; i < vQBID.size(); ++i) {
                final String qbId = vQBID.elementAt(i);
                final String title = vQBName.elementAt(i);
                DataBaseLayer.log.debug("*********qbId***************" + qbId);
                DataBaseLayer.log.debug("*********title***************" + title);
                String qb_id = "";
                oRset1 = oStmt2.executeQuery("select qb_id from qb_mgmt where qb_id='" + qbId + "'");
                while (oRset1.next()) {
                    qb_id = oRset1.getString(1);
                    DataBaseLayer.log.debug("*********qb_id***************" + qb_id);
                }
                DataBaseLayer.log.debug("********oRset1.getFetchSize()*****************" + oRset1.getFetchSize());
                if (!qbId.equals(qb_id)) {
                    DataBaseLayer.log.debug("insert into qb_mgmt(qb_id,qb_name,refresh_on,refresh_by) values('" + qbId + "','" + title + "',sysdate(),'" + user_id + "')");
                    oStmt.executeUpdate("insert into qb_mgmt(qb_id,qb_name,refresh_on,refresh_by) values('" + qbId + "','" + title + "',sysdate(),'" + user_id + "')");
                }
                else {
                    DataBaseLayer.log.debug("Question Bank already exist");
                }
            }
            oStmt.close();
            oStmt2.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.fatal("SQLException:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
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
    
    public static synchronized void refreshAssessmentList(final Vector<String> vAsmtID, final Vector<String> vAsmtCode, final Vector<String> vAsmtTitle, final Vector<String> vAsmtType, final Vector<String> vAsmtDesc, final Vector<String> vAsmtDuration, final Vector<String> vAsmtPassMarks, final Vector<String> vAsmtQuestionPerPage, final Vector<String> vAsmtMaxTestTaken, final Vector<String> vAsmtGenerationType, final String user_id) {
        ResultSet oRset1 = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            for (int i = 0; i < vAsmtID.size(); ++i) {
                final String strAsmtId = vAsmtID.elementAt(i);
                final String strAsmtCode = vAsmtCode.elementAt(i);
                final String title = vAsmtTitle.elementAt(i);
                final String strAsmtType = vAsmtType.elementAt(i);
                final String strAsmtDesc = vAsmtDesc.elementAt(i);
                final String strAsmtDuration = vAsmtDuration.elementAt(i);
                final String strAsmtPassMarks = vAsmtPassMarks.elementAt(i);
                final String strAsmtQuestionPerPage = vAsmtQuestionPerPage.elementAt(i);
                final String strMaxNoTestTaken = vAsmtMaxTestTaken.elementAt(i);
                final String strAsmtGenerationType = vAsmtGenerationType.elementAt(i);
                DataBaseLayer.log.debug("*********strAsmtId***************" + strAsmtId);
                DataBaseLayer.log.debug("*********strAsmtCode***************" + strAsmtCode);
                DataBaseLayer.log.debug("*********title***************" + title);
                DataBaseLayer.log.debug("***********strAsmtType******************" + strAsmtType);
                DataBaseLayer.log.debug("***********strAsmtDesc******************" + strAsmtDesc);
                DataBaseLayer.log.debug("***********strAsmtDuration******************" + strAsmtDuration);
                DataBaseLayer.log.debug("***********strAsmtPassMarks******************" + strAsmtPassMarks);
                DataBaseLayer.log.debug("***********strAsmtQuestionPerPage******************" + strAsmtQuestionPerPage);
                DataBaseLayer.log.debug("***********strMaxNoTestTaken******************" + strMaxNoTestTaken);
                DataBaseLayer.log.debug("***********strAsmtGenerationType******************" + strAsmtGenerationType);
                String assessment_id = "";
                DataBaseLayer.log.debug("select assessment_id from assessment_management where assessment_id='" + strAsmtId + "'");
                oRset1 = oStmt2.executeQuery("select assessment_id from assessment_management where assessment_id='" + strAsmtId + "'");
                while (oRset1.next()) {
                    assessment_id = oRset1.getString(1);
                    DataBaseLayer.log.debug("*********assessment_id***************" + assessment_id);
                }
                DataBaseLayer.log.debug("********oRset1.getFetchSize()*****************" + oRset1.getFetchSize());
                if (!strAsmtId.equals(assessment_id)) {
                    DataBaseLayer.log.debug("insert into assessment_management(assessment_id,assessment_code,title,assessment_type,description,duration,pass_marks,question_perpage,max_no_test_taken,asmt_generation_type,refresh_on,refresh_by) values('" + strAsmtId + "','" + strAsmtCode + "','" + title + "','" + strAsmtType + "','" + strAsmtDesc + "','" + strAsmtDuration + "','" + strAsmtPassMarks + "','" + strAsmtQuestionPerPage + "','" + strMaxNoTestTaken + "','" + strAsmtGenerationType + "',sysdate(),'" + user_id + "')");
                    oStmt.executeUpdate("insert into assessment_management(assessment_id,assessment_code,title,assessment_type,description,duration,pass_marks,question_perpage,max_no_test_taken,asmt_generation_type,refresh_on,refresh_by) values('" + strAsmtId + "','" + strAsmtCode + "','" + title + "','" + strAsmtType + "','" + strAsmtDesc + "','" + strAsmtDuration + "','" + strAsmtPassMarks + "','" + strAsmtQuestionPerPage + "','" + strMaxNoTestTaken + "','" + strAsmtGenerationType + "',sysdate(),'" + user_id + "')");
                }
                else {
                    DataBaseLayer.log.debug("Assessment already exist");
                }
            }
            oStmt.close();
            oStmt2.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.fatal("SQLException:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
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
    
    public static synchronized void downloadQuestionBank(final String qbID, final String user) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            DataBaseLayer.log.debug("update qb_mgmt set download_on = sysdate(),download_by='" + user + "' where qb_id = '" + qbID + "'");
            oStmt.executeUpdate("update qb_mgmt set download_on = sysdate(),download_by='" + user + "' where qb_id = '" + qbID + "'");
            oStmt.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.fatal("SQLException:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
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
    
    public static synchronized void setResultProcessingStatus(final String asmt_id) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            DataBaseLayer.log.debug("update assessment_request_result_processing set result_processing_status = 'Pending' where assessment_id = '" + asmt_id + "'");
            final boolean flag = statement.execute("update assessment_request_result_processing set result_processing_status = 'Pending' where assessment_id = '" + asmt_id + "'");
            statement.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in setResultProcessingStatus()");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("setResultProcessingStatus printStack is :: " + ex.getMessage());
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
    
    public static Vector getAssessmentDeffination(final String assessment_id) {
        final Vector vTestInfo = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
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
            DataBaseLayer.log.debug("getAssessmentDeffination: error due to SQL exception");
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getAssessmentDeffination printStack is :: " + ex.getMessage());
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
    
    public static Vector getTestItemIDs(final String topicId, final String qtype) {
        final Vector vTestInfo = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
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
            DataBaseLayer.log.debug("SQLException in getTestItemIDs()");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getTestItemIDs printStack is :: " + ex.getMessage());
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
    
    public static Vector getTextAndType(final Vector<String> IDs) {
        final Vector vAllDetails = new Vector();
        final Vector vId = new Vector();
        final Vector vTitle = new Vector();
        final Vector vText = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement1 = oConn.createStatement();
            for (int i = 0; i < IDs.size(); ++i) {
                final String id = IDs.elementAt(i);
                DataBaseLayer.log.debug("***********getTextAndType***method*********************");
                String strDatabaseEncrptPassword = "";
                final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
                final String strkey = "DatabaseEncrptPassword";
                strDatabaseEncrptPassword = rb.getString(strkey);
                DataBaseLayer.log.debug("************strDatabaseEncrptPassword******************" + strDatabaseEncrptPassword);
                DataBaseLayer.log.debug("select assess_title,AES_DECRYPT(assess_text,'" + strDatabaseEncrptPassword + "') from qb_mgmt_item where assess_id='" + id + "'");
                final ResultSet resultset1 = statement1.executeQuery("select assess_title,AES_DECRYPT(assess_text,'" + strDatabaseEncrptPassword + "') from qb_mgmt_item where assess_id='" + id + "'");
                while (resultset1.next()) {
                    vId.addElement(id);
                    vTitle.addElement(resultset1.getString(1));
                    vText.addElement(resultset1.getString(2));
                }
            }
            statement1.close();
            vAllDetails.addElement(vId);
            vAllDetails.addElement(vTitle);
            vAllDetails.addElement(vText);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("getTextAndType: error due to SQL exception" + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getTextAndType printStack is :: " + ex.getMessage());
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
    
    public static Vector getItemId(final Vector<String> vItemIdList, final String dlevel) {
        final Vector vItemList = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
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
            DataBaseLayer.log.debug("SQLException in getItemId()");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getItemId printStack is :: " + ex.getMessage());
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
    
    public static synchronized void generateAsmt(final String asmt_id, final String strItems) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final String strAsmtGenerationStatus = "Yes";
            final int iasmt_id = Integer.parseInt(asmt_id);
            String strDatabaseEncrptPassword = "";
            final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
            final String strkey = "DatabaseEncrptPassword";
            strDatabaseEncrptPassword = rb.getString(strkey);
            DataBaseLayer.log.debug("************strDatabaseEncrptPassword******************" + strDatabaseEncrptPassword);
            DataBaseLayer.log.debug("update assessment_defination set generated_assessment = AES_ENCRYPT(?,'" + strDatabaseEncrptPassword + "'),asmt_generation_status =? where assessment_id =?");
            final PreparedStatement statement = oConn.prepareStatement("update assessment_defination set generated_assessment = AES_ENCRYPT(?,'" + strDatabaseEncrptPassword + "'),asmt_generation_status =? where assessment_id =?");
            statement.setString(1, strItems);
            statement.setString(2, strAsmtGenerationStatus);
            statement.setInt(3, iasmt_id);
            statement.executeUpdate();
            statement.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("SQL Exception in generateAsmt()");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("generateAsmt printStack is :: " + ex.getMessage());
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
    
    public static Vector getGeneratedAsmt(final String asmt_id) {
        final Vector vGeneratedasmtDetails = new Vector();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            String strDatabaseEncrptPassword = "";
            final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
            final String strkey = "DatabaseEncrptPassword";
            strDatabaseEncrptPassword = rb.getString(strkey);
            DataBaseLayer.log.debug("************strDatabaseEncrptPassword******************" + strDatabaseEncrptPassword);
            DataBaseLayer.log.debug("select AES_DECRYPT(generated_assessment,'" + strDatabaseEncrptPassword + "'),asmt_generation_status from assessment_defination where assessment_id ='" + asmt_id + "'");
            final ResultSet resultset = statement.executeQuery("select AES_DECRYPT(generated_assessment,'" + strDatabaseEncrptPassword + "'),asmt_generation_status from assessment_defination where assessment_id ='" + asmt_id + "'");
            if (resultset.next()) {
                final String strGeneratedAsmt = resultset.getString(1);
                final String strGeneration_Status = resultset.getString(2);
                vGeneratedasmtDetails.addElement(strGeneratedAsmt);
                vGeneratedasmtDetails.addElement(strGeneration_Status);
            }
            statement.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("SQLException in getGeneratedAsmt method ");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("Exception in getGeneratedAsmt method");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vGeneratedasmtDetails;
    }
    
    public static String getAsmtGenerationType(final String asmt_id) {
        String strGenerationType = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            DataBaseLayer.log.debug("select asmt_generation_type from assessment_management where assessment_id ='" + asmt_id + "'");
            final ResultSet resultset = statement.executeQuery("select asmt_generation_type from assessment_management where assessment_id ='" + asmt_id + "'");
            if (resultset.next()) {
                strGenerationType = resultset.getString(1);
            }
            statement.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("SQLException in getAsmtGenerationType method ");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("Exception in getAsmtGenerationType method");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return strGenerationType;
    }
    
    public static synchronized void downloadGeneratedAsmt(final String asmt_id, final String strDownloadedAsmt, final String user) {
        Connection oConn = null;
        try {
            String strDatabaseEncrptPassword = "";
            final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
            final String strkey = "DatabaseEncrptPassword";
            strDatabaseEncrptPassword = rb.getString(strkey);
            DataBaseLayer.log.debug("************strDatabaseEncrptPassword******************" + strDatabaseEncrptPassword);
            oConn = DataBaseLayer.ds1.getConnection();
            DataBaseLayer.log.debug("update assessment_management set download_on = sysdate(),download_by=?, downloaded_asmt = AES_ENCRYPT(?,'" + strDatabaseEncrptPassword + "') where assessment_id =?");
            final PreparedStatement statement = oConn.prepareStatement("update assessment_management set download_on = sysdate(),download_by=?, downloaded_asmt = AES_ENCRYPT(?,'" + strDatabaseEncrptPassword + "') where assessment_id =?");
            statement.setString(1, user);
            statement.setString(2, strDownloadedAsmt);
            statement.setString(3, asmt_id);
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.fatal("SQLException:");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
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
    
    public static void updateMailServerStatus(final String configuration_id, final String status) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        try {
            conn = DataBaseLayer.ds1.getConnection();
            pstmt1 = conn.prepareStatement("update mail_server_configuration set Status=? where configuration_id=?");
            pstmt1.setString(1, status);
            pstmt1.setString(2, configuration_id);
            pstmt1.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("SQLException in DataBaseLayer.updateMailServerStatus() " + e);
        }
        catch (Exception ex) {
            System.out.println("Exception in DataBaseLayer.updateMailServerStatus() " + ex);
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (SQLException ex2) {}
            }
            if (pstmt1 != null) {
                try {
                    pstmt1.close();
                    pstmt1 = null;
                }
                catch (SQLException e2) {
                    System.out.println("Cannot close pstmt1" + e2);
                }
            }
        }
    }
    
    public static String[] getTotalStudentAsmtTitle(final String assessment_id) {
        Statement oStmt = null;
        ResultSet oRset = null;
        Statement oStmt2 = null;
        ResultSet oRset2 = null;
        final String[] str1 = new String[2];
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oStmt = oConn.createStatement();
            oStmt2 = oConn.createStatement();
            oRset = oStmt.executeQuery("select title from assessment_management where assessment_id='" + assessment_id + "'");
            while (oRset.next()) {
                str1[0] = oRset.getString(1);
            }
            oRset2 = oStmt2.executeQuery("select count(student_id) from stassessment_test_details where assessment_id='" + assessment_id + "'");
            while (oRset2.next()) {
                str1[1] = oRset2.getString(1);
            }
        }
        catch (SQLException e) {
            DataBaseLayer.log.debug("getAssessmentTitle: error due to SQL exception" + e);
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            DataBaseLayer.log.debug("getAssessmentTitle printStack is :: " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                    oRset.close();
                    oStmt.close();
                    oRset2.close();
                    oStmt2.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return str1;
    }
    
    static {
        log = new SimpleLogger((Class)DataBaseLayer.class, true);
        DataBaseLayer.ds = CoreAdminInitHostInfo.ds;
        DataBaseLayer.ds1 = CoreAdminInitHostInfo.ds1;
    }
}
