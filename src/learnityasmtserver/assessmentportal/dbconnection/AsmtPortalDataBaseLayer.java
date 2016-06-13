package learnityasmtserver.assessmentportal.dbconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.sql.DataSource;

import learnityinterfaceportal.DataBaseLayer;

import org.grlea.log.SimpleLogger;

import comv2.aunwesha.param.CoreAdminInitHostInfo;

public class AsmtPortalDataBaseLayer
{
    public static final SimpleLogger log;
    public static DataSource ds1;
    
    public Vector getAssessmentInfo1(final String s) {
        final Vector<String[]> vector = new Vector<String[]>();
        Connection connection = null;
        try {
            final String userRole = DataBaseLayer.getUserRole(s);
            connection = AsmtPortalDataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            AsmtPortalDataBaseLayer.log.debug("strUserRole======" + userRole);
            String s2 = "select a.assessment_id,b.title,a.date_availability,a.registration_valid_till,b.duration,b.pass_marks,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)- TO_DAYS(sysdate())>=0),'Available','Date Over') from assessment_user_registration a, assessment_management b where a.assessment_id=b.assessment_id and a.student_id='" + s + "'";
            if (userRole.equals("CORPORATEUSER")) {
                s2 = "SELECT p.assessment_id,c.title,p.date_availability,p.registration_valid_till,c.duration,c.pass_marks,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)-TO_DAYS(sysdate())>=0),'Available','Date Over') FROM program_assessment_association p, plan_program_association pl, plan_employee_association ple, assessment_management c WHERE pl.program_id=p.program_id AND ple.plan_id=pl.plan_id  AND c.assessment_id=p.assessment_id and ple.emp_id='" + s + "' group by p.assessment_id";
            }
            AsmtPortalDataBaseLayer.log.debug("sql for user association ========" + s2);
            final ResultSet executeQuery = statement.executeQuery(s2);
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6), executeQuery.getString(7), executeQuery.getString(8) });
            }
            executeQuery.close();
            statement.close();
            connection.close();
        }
        catch (SQLException ex) {
            AsmtPortalDataBaseLayer.log.debug("getAssessmentInfo: error due to SQL exception" + ex);
        }
        catch (Exception ex2) {
            AsmtPortalDataBaseLayer.log.debug("getAssessmentInfo: exception=" + ex2);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public Vector getAssessmentInfogroup(final String s) {
        final Vector<String[]> vector = new Vector<String[]>();
        Connection connection = null;
        try {
            final String userRole = DataBaseLayer.getUserRole(s);
            connection = AsmtPortalDataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            AsmtPortalDataBaseLayer.log.debug("getAssessmentInfogroup");
            AsmtPortalDataBaseLayer.log.debug("strUserRole======" + userRole);
            String s2 = "select a.assessment_id,b.title,a.date_availability,a.registration_valid_till,b.duration,b.pass_marks,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)-TO_DAYS(sysdate())>=0),'Available','Date Over') from assessment_group_registration a, student_group_association s,assessment_management b where a.group_id=s.group_id and a.assessment_id=b.assessment_id and s.student_id='" + s + "'";
            if (userRole.equals("CORPORATEUSER")) {
                s2 = "SELECT p.assessment_id, c.title,p.date_availability,p.registration_valid_till,c.duration,c.pass_marks,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)-TO_DAYS(sysdate())>=0),'Available','Date Over') FROM program_assessment_association p, plan_program_association pl, plan_group_association ple, assessment_management c, student_group_association s WHERE pl.program_id=p.program_id AND ple.plan_id=pl.plan_id AND s.student_id='" + s + "' AND c.assessment_id=p.assessment_id AND s.group_id=ple.group_id and p.assessment_id not in (SELECT p.assessment_id FROM program_assessment_association p, plan_program_association pl, plan_employee_association ple, assessment_management c WHERE pl.program_id=p.program_id AND ple.plan_id=pl.plan_id  AND c.assessment_id=p.assessment_id and ple.emp_id='" + s + "') group by p.assessment_id";
            }
            AsmtPortalDataBaseLayer.log.debug("sql========" + s2);
            final ResultSet executeQuery = statement.executeQuery(s2);
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6), executeQuery.getString(7), executeQuery.getString(8) });
            }
            executeQuery.close();
            statement.close();
            connection.close();
        }
        catch (SQLException ex2) {
            AsmtPortalDataBaseLayer.log.debug("getAssessmentInfogroup: error due to SQL exception");
        }
        catch (Exception ex) {
            AsmtPortalDataBaseLayer.log.debug("getAssessmentInfogroup: error due to exception:" + ex);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public Vector getAssessmentInfoCourse(final String s) {
        final Vector<String[]> vector = new Vector<String[]>();
        Connection connection = null;
        try {
            connection = AsmtPortalDataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            AsmtPortalDataBaseLayer.log.debug("getAssessmentInfogroup");
            System.out.println("select a.assessment_id,concat(b.title,' ','(', 'Under Batch:-', '', c.title,' ',')','<br>','(','Under Course:-', '', e.name,' ',')','</br>'),c.startdate,c.enddate,b.duration,b.pass_marks,if((startdate is not null) and (startdate > sysdate()),'Not Available','Available'),if((enddate is not null) and (TO_DAYS(enddate)-TO_DAYS(sysdate())>=0),'Available','Date Over') from course_assessment_association a, batch_user_asso s,assessment_management b,batchmanagement c,batch_course_asso d,coursemanagement e where a.courseid=d.courseid and s.batch_id=c.batch_id and a.assessment_id=b.assessment_id and  a.courseid=e.courseid and d.courseid=e.courseid and c.batch_id=d.batch_id and s.student_id='" + s + "'");
            final ResultSet executeQuery = statement.executeQuery("select a.assessment_id,concat(b.title,' ','(', 'Under Batch:-', '', c.title,' ',')','<br>','(','Under Course:-', '', e.name,' ',')','</br>'),c.startdate,c.enddate,b.duration,b.pass_marks,if((startdate is not null) and (startdate > sysdate()),'Not Available','Available'),if((enddate is not null) and (TO_DAYS(enddate)-TO_DAYS(sysdate())>=0),'Available','Date Over') from course_assessment_association a, batch_user_asso s,assessment_management b,batchmanagement c,batch_course_asso d,coursemanagement e where a.courseid=d.courseid and s.batch_id=c.batch_id and a.assessment_id=b.assessment_id and  a.courseid=e.courseid and d.courseid=e.courseid and c.batch_id=d.batch_id and s.student_id='" + s + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6), executeQuery.getString(7), executeQuery.getString(8) });
            }
            executeQuery.close();
            statement.close();
            connection.close();
        }
        catch (SQLException ex2) {
            AsmtPortalDataBaseLayer.log.debug("getAssessmentInfoCourse: error due to SQL exception");
        }
        catch (Exception ex) {
            AsmtPortalDataBaseLayer.log.debug("getAssessmentInfoCourse: error due to exception:" + ex);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public Vector getAssessmentInfoByTrainingPlan(final String s) {
        final Vector<String[]> vector = new Vector<String[]>();
        Connection connection = null;
        try {
            connection = AsmtPortalDataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            AsmtPortalDataBaseLayer.log.debug("getAssessmentInfoByTrainingPlan");
            System.out.println("select a.assessment_id,concat(b.title,' ','(', 'Under Training Plan:-', '', g.plan_title,' ',')','<br>','(', 'Under Batch:-', '', c.title,' ',')','<br>','(','Under Course:-', '', e.name,' ',')','</br>'),c.startdate,c.enddate,b.duration,b.pass_marks,if((startdate is not null) and (startdate > sysdate()),'Not Available','Available'),if((enddate is not null) and (TO_DAYS(enddate)-TO_DAYS(sysdate())>=0),'Available','Date Over') from course_assessment_association a, batch_user_asso s,assessment_management b,batchmanagement c,training_plan_course_asso d,coursemanagement e,training_plan_batch_asso f,training_plan_master g where a.courseid=d.course_id and s.batch_id=c.batch_id and a.assessment_id=b.assessment_id and  a.courseid=e.courseid and d.course_id=e.courseid and c.batch_id=f.batch_id and f.plan_id=d.plan_id and d.plan_id=g.plan_id and s.student_id='" + s + "'");
            final ResultSet executeQuery = statement.executeQuery("select a.assessment_id,concat(b.title,' ','(', 'Under Training Plan:-', '', g.plan_title,' ',')','<br>','(', 'Under Batch:-', '', c.title,' ',')','<br>','(','Under Course:-', '', e.name,' ',')','</br>'),c.startdate,c.enddate,b.duration,b.pass_marks,if((startdate is not null) and (startdate > sysdate()),'Not Available','Available'),if((enddate is not null) and (TO_DAYS(enddate)-TO_DAYS(sysdate())>=0),'Available','Date Over') from course_assessment_association a, batch_user_asso s,assessment_management b,batchmanagement c,training_plan_course_asso d,coursemanagement e,training_plan_batch_asso f,training_plan_master g where a.courseid=d.course_id and s.batch_id=c.batch_id and a.assessment_id=b.assessment_id and  a.courseid=e.courseid and d.course_id=e.courseid and c.batch_id=f.batch_id and f.plan_id=d.plan_id and d.plan_id=g.plan_id and s.student_id='" + s + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6), executeQuery.getString(7), executeQuery.getString(8) });
            }
            executeQuery.close();
            statement.close();
            connection.close();
        }
        catch (SQLException ex2) {
            AsmtPortalDataBaseLayer.log.debug("getAssessmentInfoByTrainingPlan: error due to SQL exception");
        }
        catch (Exception ex) {
            AsmtPortalDataBaseLayer.log.debug("getAssessmentInfoByTrainingPlan: error due to exception:" + ex);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public String getTotalquesno(final String s) {
        int n = 0;
        String string = "";
        Connection connection = null;
        try {
            connection = AsmtPortalDataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select no_of_questions from assessment_defination where assessment_id='" + s + "'");
            while (executeQuery.next()) {
                n += executeQuery.getInt(1);
            }
            string = Integer.toString(n);
            executeQuery.close();
            statement.close();
            connection.close();
        }
        catch (SQLException ex2) {
            AsmtPortalDataBaseLayer.log.debug("getTotalquesno: error due to SQL exception");
        }
        catch (Exception ex) {
            AsmtPortalDataBaseLayer.log.debug("*****************error due to java.lang.exception****************");
            ex.printStackTrace();
            AsmtPortalDataBaseLayer.log.debug("getAssessmentTitle printStack is :: " + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public String getLastStatus(final String s, final String s2) {
        String string = "";
        Connection connection = null;
        try {
            connection = AsmtPortalDataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select status from stassessment_test_details where student_id='" + s + "' and assessment_id='" + s2 + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            executeQuery.close();
            statement.close();
            connection.close();
        }
        catch (SQLException ex2) {
            AsmtPortalDataBaseLayer.log.debug("getLastStatus: error due to SQL exception");
        }
        catch (Exception ex) {
            AsmtPortalDataBaseLayer.log.debug("getLastStatus: error due toexception:" + ex);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static Vector<String[]> getAssessmentAvailablity(final String s, final String s2) {
        Connection connection = null;
        final Vector<String[]> vector = new Vector<String[]>();
        try {
            connection = AsmtPortalDataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            AsmtPortalDataBaseLayer.log.debug("select available_time,date_availability,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),registration_valid_till,if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)-TO_DAYS(sysdate())>=0),'Available','Date Over') from assessment_user_registration  where assessment_id='" + s2 + "' and student_id='" + s + "'");
            final ResultSet executeQuery = statement.executeQuery("select available_time,date_availability,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),registration_valid_till,if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)-TO_DAYS(sysdate())>=0),'Available','Date Over') from assessment_user_registration  where assessment_id='" + s2 + "' and student_id='" + s + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5) });
            }
            statement.close();
            executeQuery.close();
            connection.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> getAssessmentGroupAvailablity(final String s, final String s2) {
        Connection connection = null;
        final Vector<String[]> vector = new Vector<String[]>();
        try {
            connection = AsmtPortalDataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            AsmtPortalDataBaseLayer.log.debug("select a.available_time,a.date_availability,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),registration_valid_till,if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)-TO_DAYS(sysdate())>=0),'Available','Date Over') from assessment_group_registration a,student_group_association s where a.group_id=s.group_id and a.assessment_id='" + s2 + "' and s.student_id='" + s + "'");
            final ResultSet executeQuery = statement.executeQuery("select a.available_time,a.date_availability,if((date_availability is not null) and (date_availability > sysdate()),'Not Available','Available'),registration_valid_till,if((registration_valid_till is not null) and (TO_DAYS(registration_valid_till)-TO_DAYS(sysdate())>=0),'Available','Date Over') from assessment_group_registration a,student_group_association s where a.group_id=s.group_id and a.assessment_id='" + s2 + "' and s.student_id='" + s + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5) });
            }
            statement.close();
            executeQuery.close();
            connection.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public String getMaxtestTaken(final String s) {
        String string = "";
        Connection connection = null;
        try {
            connection = AsmtPortalDataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select max_no_test_taken from assessment_management where assessment_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            executeQuery.close();
            statement.close();
            connection.close();
        }
        catch (SQLException ex2) {
            AsmtPortalDataBaseLayer.log.debug("getMaxtestTaken: error due to SQL exception");
        }
        catch (Exception ex) {
            AsmtPortalDataBaseLayer.log.debug("getMaxtestTaken: error due to exception=" + ex);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public String getMaxNoAppearedInTest(final String s, final String s2) {
        String string = "";
        Connection connection = null;
        try {
            connection = AsmtPortalDataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select count(test_id) from assessment_progress_status where student_id='" + s + "' and assessment_id='" + s2 + "' and submition_status='Submitted'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            executeQuery.close();
            statement.close();
            connection.close();
        }
        catch (SQLException ex2) {
            AsmtPortalDataBaseLayer.log.debug("getMaxtestTaken: error due to SQL exception");
        }
        catch (Exception ex) {
            AsmtPortalDataBaseLayer.log.debug("getMaxtestTaken: error due to exception==" + ex);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public Vector<String> isPreviousTestSubmitted(final String s, final String s2) {
        final Vector<String> vector = new Vector<String>();
        String string = "";
        String string2 = "";
        String string3 = "";
        Connection connection = null;
        try {
            connection = AsmtPortalDataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select submition_status,saved_state,test_id from assessment_progress_status where student_id='" + s + "' and assessment_id='" + s2 + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
                string2 = executeQuery.getString(2);
                string3 = executeQuery.getString(3);
            }
            vector.addElement(string);
            vector.addElement(string2);
            vector.addElement(string3);
            executeQuery.close();
            statement.close();
            connection.close();
        }
        catch (SQLException ex2) {
            AsmtPortalDataBaseLayer.log.debug("isPreviousTestSubmitted: error due to SQL exception");
        }
        catch (Exception ex) {
            AsmtPortalDataBaseLayer.log.debug("isPreviousTestSubmitted: error due to exception:" + ex);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    static {
        log = new SimpleLogger((Class)AsmtPortalDataBaseLayer.class, true);
        AsmtPortalDataBaseLayer.ds1 = CoreAdminInitHostInfo.ds1;
    }
}
