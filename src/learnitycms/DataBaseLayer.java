package learnitycms;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.sql.DataSource;

import org.grlea.log.DebugLevel;
import org.grlea.log.SimpleLogger;

import comv2.aunwesha.param.CoreAdminInitHostInfo;

public class DataBaseLayer
{
    public static final SimpleLogger log;
    public static DataSource ds1;
    
    public static Vector<String[]> getCourseName(final String s) {
        Connection connection = null;
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>();
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select a.course_id,a.course_name, c.book_marks,date_format(c.registration_date,'%d-%m-%Y'),date_format(c.access_allowed_till,'%d-%m-%Y'),a.ttimes,c.total_access_time from course_definition a, usergroup_course_registration c where c.student_id='" + s + "' and c.course_id=a.course_id ");
            System.out.println("select a.course_id,a.course_name, c.book_marks,date_format(c.registration_date,'%d-%m-%Y'),date_format(c.access_allowed_till,'%d-%m-%Y'),a.ttimes,c.total_access_time from course_definition a, usergroup_course_registration c where c.student_id='" + s + "' and c.course_id=a.course_id ");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6), executeQuery.getString(7) });
            }
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
                    executeQuery.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static String courseaccess(final String s, final String s2) {
        int int1 = 0;
        String string = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet executeQuery = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select sum(time_to_sec(logout_datetime)-time_to_sec(login_datetime))  from course_learner_login_info where student_id='" + s + "'and course_id='" + s2 + "' group by course_id");
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
            DataBaseLayer.log.debug("99999999999999999999999ffffffffffffff" + int1);
            final int n = int1 / 3600;
            final int n2 = int1 % 3600;
            string = Integer.toString(n) + ":" + Integer.toString(n2 / 60) + ":" + Integer.toString(n2 % 60);
            DataBaseLayer.log.debug("=================accesstime===========" + string);
        }
        catch (SQLException ex) {
            DataBaseLayer.log.debug("Inside NewDataBaseLayer courseaccess(), SQLException !!!!");
            DataBaseLayer.log.debug("Inside NewDataBaseLayer courseaccess(), the error message - " + ex.getMessage());
            DataBaseLayer.log.debug("Inside NewDataBaseLayer courseaccess(), the error message - " + ex.getErrorCode());
        }
        catch (Exception ex2) {
            DataBaseLayer.log.debug("Inside NewDataBaseLayer courseaccess(), Exception !!!!");
            ex2.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    executeQuery.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static Vector<String[]> getCourseDetails(final String s) {
        Connection connection = null;
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>();
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select cd.course_id,cd.course_name, date_format(cd.sdate,\"%d-%m-%Y\"),date_format(cd.edate,\"%d-%m-%Y\"),cd.cpoints,cd.cost,cd.type,cd.ttimes,cs.session_name from course_definition cd left join course_session cs  on (cd.session=cs.session_id) where course_id='" + s + "'");
            System.out.println("select cd.course_id,cd.course_name, date_format(cd.sdate,\"%d-%m-%Y\"),date_format(cd.edate,\"%d-%m-%Y\"),cd.cpoints,cd.cost,cd.type,cd.ttimes,cs.session_name from course_definition cd left join course_session cs  on (cd.session=cs.session_id) where course_id='" + s + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6), executeQuery.getString(7), executeQuery.getString(8), executeQuery.getString(9) });
            }
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
                    executeQuery.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static String returncoursename(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select course_name from course_definition where course_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String returnInstructors(final String s) {
        final String s2 = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        String s3 = s2 + "<div width=\"200px\" style=\"font-size:13px; font-weight:bold;position:absolute;left:50px;top:0px\">Name</div><div width=\"150px\" style=\"font-size:13px; font-weight:bold; position:absolute;left:270px;top:0px\">E-mail</div><div width=\"150px\" style=\"font-size:13px; font-weight:bold; position:absolute;left:500px;top:0px\">Label</div>";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select concat(s.first_name,' ', s.middle_name,' ', s.sname),s.email_id,if(c.show_association='Yes',c.association_label,'') from student_details s, course_definition cd, course_instructor_association c where c.course_id='" + s + "' and c.course_id=cd.course_id and c.user_id=s.student_id");
            int n = 0;
            while (executeQuery.next()) {
                String string = executeQuery.getString(1);
                String s4 = executeQuery.getString(2);
                String string2 = executeQuery.getString(3);
                if (string == null || string.equals("null")) {
                    string = "";
                }
                if (s4 == null || s4.equals("null")) {
                    s4 = "";
                }
                if (string2 == null || string2.equals("null")) {
                    string2 = "";
                }
                if (!s4.equals("")) {
                    s4 = "<a href=mailto:" + s4 + ">" + s4 + "<a>";
                }
                final int n2 = ++n * 15 + 5;
                s3 = s3 + "<div width=\"200px\" style=\"font-size:13px; position:absolute;left:50px;top:" + n2 + "px\">" + string + "</div><div width=\"150px\" style=\"font-size:13px; position:absolute;left:270px;top:" + n2 + "px\">" + s4 + "</div><div width=\"150px\" style=\"font-size:13px; position:absolute;left:500px;top:" + n2 + "px\">" + string2 + "</div>";
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return s3;
    }
    
    public static String returnins1(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select concat(ci.fname,' ', ci.mname,' ', ci.ename) from course_instructor ci,course_definition cd where course_id='" + s + "' and cd.intructor=ci.instructor_id");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String returnins2(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select concat(ci.fname,' ', ci.mname,' ', ci.ename) from course_instructor ci,course_definition cd where course_id='" + s + "' and cd.intructor1=ci.instructor_id");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String returninsemail1(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select ci.email from course_instructor ci,course_definition cd where course_id='" + s + "' and cd.intructor=ci.instructor_id");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String returninsemail2(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select ci.email from course_instructor ci,course_definition cd where course_id='" + s + "' and cd.intructor1=ci.instructor_id");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String returnstartdate(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select date_format(sdate,\"%d-%m-%Y \") from course_definition where course_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String returnenddate(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select date_format(edate,\"%M %e, %Y\") from course_definition where course_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String returnsession(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select cs.session_name from course_session cs,course_definition cd where course_id='" + s + "' and cd.session=cs.session_id");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static void InsertIntoCourseLearnerLoginInfo(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into course_learner_login_info(student_id,course_id,session_id,topic_id,login_datetime,logout_datetime)  values('" + s + "','" + s2 + "','" + s5 + "','" + s3 + "','" + s4 + " " + s6 + "','" + s4 + " " + s6 + "')");
        }
        catch (SQLException ex) {
            System.out.println("Inside NewDataBaseLayer InsertIntoCourseLearnerLoginInfo(), Error due to SQL exception!");
            ex.getErrorCode();
            System.out.println("The Error is :- " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println("Inside NewDataBaseLayer InsertIntoCourseLearnerLoginInfo(), exception!");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void updateCourseLoginInfo(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("update course_learner_login_info set logout_datetime = '" + s5 + " " + s4 + "' where session_id = '" + s3 + "'and student_id = '" + s + "' and topic_id= '" + s2 + "' and course_id='" + s6 + "' and logout_datetime = login_datetime");
        }
        catch (SQLException ex) {
            System.out.println("SQL error inside NewDataBaseLayer.updateCourseLoginInfo()");
            System.out.println("The error code is" + ex.getErrorCode());
            System.out.println("The error message is" + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println("General exception inside NewDataBaseLayer.updateCourseLoginInfo()");
            ex2.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized boolean insertBookmark(final String s, final String s2, final String s3) {
        Statement statement = null;
        boolean b = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("update  usergroup_course_registration set book_marks ='" + s3 + "' where course_id='" + s2 + "' and student_id ='" + s + "'");
            b = true;
        }
        catch (SQLException ex) {
            System.err.println("SQLException in insertBookmark" + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("Exception in insertBookmark" + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return b;
    }
    
    public static String getTopic(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("select book_marks from usergroup_course_registration where course_id='" + s + "'");
            executeQuery = statement.executeQuery("select book_marks from usergroup_course_registration where course_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static Vector<String[]> getCourseSchedule(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select a.course_id , a.schedule_id, a.schedule_name, date_format(a.sdate,\"%M %e, %Y \"),  a.stime, date_format(a.edate,\"%M %e, %Y \"), a.etime, b.location_name,a.comment,a.file_name,a.matType from course_schedule a LEFT JOIN course_location b ON a.location=b.location_id where course_id='" + s + "' ORDER BY  a.sdate");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6), executeQuery.getString(7), executeQuery.getString(8), executeQuery.getString(9), executeQuery.getString(10), executeQuery.getString(11) });
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static String[] getScheduleFile(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        final String[] array = new String[2];
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select file_name, file_name1  from course_definition where course_id='" + s + "'");
            if (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
                array[1] = executeQuery.getString(2);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static Vector<String[]> getCourseResouces(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select a.course_id , a.material_id, a.material_name, a.mtype, a.size, a.material, a.description  from course_material a  where a.course_id='" + s + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6), executeQuery.getString(7) });
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> selectNoticeInformation(final String s) {
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select notice_heading,date_format(postedon,\"%D %M %Y %r\"),body,attachments from anno_mgmt where course_id='" + s + "'ORDER BY postedon");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), null });
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        System.out.println("---------INSIDE DATABASELAYER selectNoticeInformation-->" + vector);
        return vector;
    }
    
    public static String selectNumAnnounce(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select count(*) from anno_mgmt where course_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static Vector<String[]> selectItemInformationCidAdmin(final String s) {
        Connection connection = null;
        final Vector<String[]> vector = new Vector<String[]>(4, 4);
        Statement statement = null;
        Statement statement2 = null;
        ResultSet executeQuery = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            final String string = "select a.gradebook from book_course_asso a where a.course='" + s + "'";
            System.out.println("---->>>>----selectItemInformationCidAdmin()-- " + string);
            executeQuery = statement.executeQuery(string);
            while (executeQuery.next()) {
                final String string2 = executeQuery.getString(1);
                System.out.println("---->>>>----selectItemInformationCidAdmin()-- select a.item_name,a.attempt,a.item_id,a.marks_scheme from item_mgmt a,book_item_mgmt b where a.item_id=b.item and b.book_id='" + string2 + "'");
                final ResultSet executeQuery2 = statement2.executeQuery("select a.item_name,a.attempt,a.item_id,a.marks_scheme from item_mgmt a,book_item_mgmt b where a.item_id=b.item and b.book_id='" + string2 + "'");
                while (executeQuery2.next()) {
                    vector.addElement(new String[] { executeQuery2.getString(1), executeQuery2.getString(2), executeQuery2.getString(3), executeQuery2.getString(4) });
                }
                executeQuery2.close();
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in TrainingDatabaseLayer.getItemList()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in TrainingDatabaseLayer.getItemList()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    statement2.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        System.out.println("--------------INSIDE DATABASELAYER selectItemInformationCidAdmin--> " + vector);
        return vector;
    }
    
    public static String[] selectColumnCidAdmin(final String s) {
        final String[] array = new String[5];
        Statement statement = null;
        Statement statement2 = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            final String string = "select a.gradebook from book_course_asso a where a.course='" + s + "'";
            System.out.println("---->>>>---- selectColumnCidAdmin()--" + string);
            executeQuery = statement.executeQuery(string);
            while (executeQuery.next()) {
                final String string2 = executeQuery.getString(1);
                System.out.println("---->>>>---- selectColumnCidAdmin()-- select count(*) from book_item_mgmt where book_id='" + string2 + "'");
                final ResultSet executeQuery2 = statement2.executeQuery("select count(*) from book_item_mgmt where book_id='" + string2 + "'");
                while (executeQuery2.next()) {
                    array[0] = executeQuery2.getString(1);
                }
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Error :");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Error :");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    statement2.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static String getPriviledge(final String s) {
        String string = null;
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("---->>>>----getPriviledge()-- select view from grade_view where course='" + s + "'");
            executeQuery = statement.executeQuery("select view from grade_view where course='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            statement.close();
            executeQuery.close();
            connection.close();
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Error :");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Error :");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String selectFullMarks(final String s) {
        Connection connection = null;
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            DataBaseLayer.log.entry("selectFullMarks()");
            statement = connection.createStatement();
            DataBaseLayer.log.verbose("select max_marks from item_mgmt where item_name='" + s + "'");
            executeQuery = statement.executeQuery("select max_marks from item_mgmt where item_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            statement.close();
            connection.close();
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getFullMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getFullMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String selectPassMarks(final String s) {
        Connection connection = null;
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            DataBaseLayer.log.entry("selectPassMarks()");
            statement = connection.createStatement();
            DataBaseLayer.log.verbose("select pass_marks from item_mgmt where item_name='" + s + "'");
            executeQuery = statement.executeQuery("select pass_marks from item_mgmt where item_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getPassMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getPassMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String selectLowMarks(final String s, final String s2, final String s3) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            DataBaseLayer.log.entry("selectLowMarks()");
            statement = connection.createStatement();
            DataBaseLayer.log.verbose("select min(marks) from grade_mgmt where course='" + s + "' and item='" + s2 + "' and attempt='" + s3 + "'");
            executeQuery = statement.executeQuery("select min(marks) from grade_mgmt where course='" + s + "' and item='" + s2 + "' and attempt='" + s3 + "'");
            System.out.println("select min(marks) from grade_mgmt where course='" + s + "' and item='" + s2 + "' and attempt='" + s3 + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getLowMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getLowMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String selectHighMarks(final String s, final String s2, final String s3) {
        Connection connection = null;
        String string = "";
        //final Vector vector = new Vector();
        Statement statement = null;
        ResultSet executeQuery = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            DataBaseLayer.log.entry("selectHighMarks()");
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select max(marks) from grade_mgmt where course='" + s + "' and item='" + s2 + "' and attempt='" + s3 + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getHighMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getHighMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String selectAvgMarks(final String s, final String s2, final String s3) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            DataBaseLayer.log.entry("selectAvgMarks()");
            statement = connection.createStatement();
            DataBaseLayer.log.verbose("select round(avg(marks)) from grade_mgmt where course='" + s + "' and item='" + s2 + "' and attempt='" + s3 + "'");
            executeQuery = statement.executeQuery("select round(avg(marks),2) from grade_mgmt where course='" + s + "' and item='" + s2 + "' and attempt='" + s3 + "'");
            while (executeQuery.next()) {
                DataBaseLayer.log.verbose("It is crossing the 1st for loop");
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getAvgMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getAvgMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static Vector<String> calculateSum(final String s, final String s2) {
        final Vector<String> vector = new Vector<String>(1, 1);
        final String[] array = new String[5];
        final int n = 1;
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            DataBaseLayer.log.entry("selectAvgMarks()");
            statement = connection.createStatement();
            DataBaseLayer.log.verbose("select round(avg(marks)) from grade_mgmt where course='" + s + "' and  attempt='" + s2 + "'");
            executeQuery = statement.executeQuery("select sum(marks) from grade_mgmt where course='" + s + "' and attempt='" + s2 + "' group by student");
            while (executeQuery.next()) {
                DataBaseLayer.log.verbose("It is crossing the 1st for loop");
                array[0] = executeQuery.getString(n);
                System.out.println("#####################" + array[0]);
                vector.addElement(array[0]);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getAvgMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getAvgMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static String selectStuName(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select concat(first_name,' ',middle_name,' ',sname) from student_details where student_id='" + s + "'");
            while (executeQuery.next()) {
                DataBaseLayer.log.verbose("It is crossing the 1st for loop");
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.selectMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.selectMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String selectMarks(final String s, final String s2, final String s3, final String s4) {
        String string = "0.0";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            DataBaseLayer.log.verbose("select marks from grade_mgmt where student='" + s + "' and course='" + s2 + "' and item='" + s3 + "' and attempt='" + s4 + "'");
            executeQuery = statement.executeQuery("select marks from grade_mgmt where student='" + s + "' and course='" + s2 + "' and item='" + s3 + "' and attempt='" + s4 + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.selectMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.selectMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static Vector<String> selectGradeInformation(final String s) {
        final Vector<String> vector = new Vector<String>(3, 3);
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            DataBaseLayer.log.verbose("select distinct a.grade_name,a.lower,a.upper from scale_scheme a,scale_mgmt b, item_mgmt c where a.scale_id=b.scale_id and b.scale_name=c.scale and c.item_name='" + s + "'");
            executeQuery = statement.executeQuery("select distinct a.grade_name,a.lower,a.upper from scale_scheme a,scale_mgmt b, item_mgmt c where a.scale_id=b.scale_id and b.scale_id=c.scale and c.item_id='" + s + "'");
            while (executeQuery.next()) {
                final String string = executeQuery.getString(1);
                final String string2 = executeQuery.getString(2);
                final String string3 = executeQuery.getString(3);
                vector.addElement(string);
                vector.addElement(string2);
                vector.addElement(string3);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in TrainingDatabaseLayer.getItemList()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in TrainingDatabaseLayer.getItemList()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static String selectGrade(final double n, final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select a.grade_name from scale_scheme a,item_mgmt b where a.upper>='" + n + "' and a.lower<='" + n + "' and a.scale_id=b.scale and b.item_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.selectWt()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.selectWt()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String selectOverallGrade(final String s, final double n) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select a.grade_name from scale_scheme a,book_course_asso bca,book_mgmt bm where a.upper>='" + n + "' and a.lower<='" + n + "' and bm.book_id=bca.gradebook and bm.scale=a.scale_id and bca.course='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.selectWt()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.selectWt()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String selectWt(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select a.weightage from book_item_mgmt a where a.item='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.selectWt()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.selectWt()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static double selectLowestOverall(final String s) {
        double double1 = 0.0;
        Connection connection = null;
        Statement statement = null;
        ResultSet executeQuery = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select min(overallmarks) from overall_marks where course_id='" + s + "'");
            while (executeQuery.next()) {
                double1 = executeQuery.getDouble(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return double1;
    }
    
    public static double selectHighestOverall(final String s) {
        double double1 = 0.0;
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select max(overallmarks) from overall_marks where course_id='" + s + "'");
            while (executeQuery.next()) {
                double1 = executeQuery.getDouble(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return double1;
    }
    
    public static double selectAvgOverall(final String s) {
        double double1 = 0.0;
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select avg(overallmarks) from overall_marks where course_id='" + s + "'");
            while (executeQuery.next()) {
                double1 = executeQuery.getDouble(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return double1;
    }
    
    public static Vector<String[]> getCourseAssignment(final String s) {
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select a.assignment_id,b.document_id ,a.title,a.description ,a.submission_last_date,b.document_name,b.size ,a.full_mark from assignment a,assignment_document b,assignment_course_association c,item_mgmt d where a.assignment_id=b.assignment_id AND a.assignment_id=c.assignment_id AND c.course_id='" + s + "' AND d.assignment_id = a.assignment_id AND d.category_name='assignment' and if(a.late_allowed_till is NULL,(datediff(end_avail_date,sysdate()))>0,(datediff(late_allowed_till,sysdate()))>0) order by a.submission_last_date");
            while (executeQuery.next()) {
                final String[] array = { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), null, null, null, null };
                final String string = executeQuery.getString(5);
                array[5] = executeQuery.getString(6);
                array[6] = executeQuery.getString(7);
                array[7] = executeQuery.getString(8);
                final ResultSet executeQuery2 = connection.createStatement().executeQuery("select date_format('" + string + "',\"%d-%m-%Y\")");
                executeQuery2.next();
                array[4] = executeQuery2.getString(1);
                vector.addElement(array);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> getCourseAssignmentDoc(final String s, final String s2, final String s3) {
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("===========Document Info==================");
            executeQuery = statement.executeQuery("select a.document_name,a.size ,a.submission_date,b.confirm_number,a.doc_id  from assignment_student_association a,assignment_confirmation b where a.student_id='" + s2 + "' AND a.course_id='" + s + "' AND a.assignment_id='" + s3 + "' and b.student_id=a.student_id and b.course_id=a.course_id and b.assignment_id=a.assignment_id and a.doc_id=b.doc_id");
            System.out.println("select a.document_name,a.size ,a.submission_date,b.confirm_number,a.doc_id  from assignment_student_association a,assignment_confirmation b where a.student_id='" + s2 + "' AND a.course_id='" + s + "' AND a.assignment_id='" + s3 + "' and b.student_id=a.student_id and b.course_id=a.course_id and b.assignment_id=a.assignment_id and a.doc_id=b.doc_id");
            while (executeQuery.next()) {
                final String[] array = new String[6];
                array[0] = executeQuery.getString(1);
                System.out.println("-----strBody[0]-------->" + array[0]);
                array[1] = executeQuery.getString(2);
                array[3] = executeQuery.getString(4);
                array[4] = executeQuery.getString(5);
                final ResultSet executeQuery2 = connection.createStatement().executeQuery("select date_format('" + executeQuery.getString(3) + "',\"%D %M %Y\")");
                executeQuery2.next();
                array[2] = executeQuery2.getString(1);
                System.out.println("------------------" + array[0]);
                vector.addElement(array);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static void submitAssignmentDocument(final String s, final String s2, final String s3, final String s4, final String s5) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("insert into assignment_student_association(student_id,course_id,assignment_id,document_name,submission_date,size) values('" + s2 + "','" + s + "','" + s3 + "','" + s5 + "',sysdate(),'" + s4 + "')");
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in submitAssignmentDocument.Rolling Back Operation");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static void updatecourse_confirmation(final String s, final String s2, final String s3, final String s4) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into assignment_confirmation(student_id,course_id,assignment_id,confirm_number) values ('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "')");
        }
        catch (SQLException ex) {
            System.out.println("Inside CourseDataBaseLayer updatecourse_confirmation(), Error due to SQL exception!");
            ex.getErrorCode();
            System.out.println("The Error is :- " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println("Inside NewDataBaseLayer course_confirmation(), exception!");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static void insertStatus(final String s, final String s2, final String s3) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into assignment_status(student_id,course_id,assignment_id,status) values('" + s + "','" + s2 + "','" + s3 + "','Not Checked')");
        }
        catch (SQLException ex) {
            System.out.println("Inside NewDataBaseLayer course_confirmation(), Error due to SQL exception!");
            ex.getErrorCode();
            System.out.println("The Error is :- " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println("Inside NewDataBaseLayer course_confirmation(), exception!");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static void deleteAssignmentDocument(final String s, final String s2, final String s3, final String s4, final String s5) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("delete from assignment_student_association where student_id='" + s2 + "' AND course_id='" + s + "' AND assignment_id='" + s3 + "'AND doc_id='" + s4 + "' AND document_name='" + s5 + "'");
            statement.executeUpdate("delete from assignment_confirmation where doc_id='" + s4 + "'");
            statement.executeUpdate("delete from assignment_status where student_id='" + s2 + "' AND course_id='" + s + "' AND assignment_id='" + s3 + "'");
            statement.executeUpdate("delete from assignment_returndoc where student_id='" + s2 + "' AND course_id='" + s + "' AND assignment_id='" + s3 + "'");
            statement.executeUpdate("delete from assignment_confirmation where student_id='" + s2 + "' AND course_id='" + s + "' AND assignment_id='" + s3 + "' and doc_id='" + s4 + "'");
            System.out.println("delete from assignment_student_association where student_id='" + s2 + "' AND course_id='" + s + "' AND assignment_id='" + s3 + "' AND doc_id='" + s4 + "'AND document_name='" + s5 + "'");
        }
        catch (SQLException ex) {
            System.out.println("Exception in deleteAssignmentDocument.Rolling Back Operation");
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static String isAssignmentLocked(final String s, final String s2, final String s3) {
        Statement statement = null;
        ResultSet executeQuery = null;
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select lock_status from assignment_status where assignment_id='" + s2 + "' and course_id='" + s + "' and student_id='" + s3 + "' ");
            if (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            System.out.println("Error due to SQL exception : isAssignmentLocked()");
            System.out.println("SQL exception : " + ex.toString());
        }
        catch (Exception ex2) {
            System.out.println("Error due to exception : isAssignmentLocked()");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String getFeedbackInfo(final String s, final String s2, final String s3) {
        Statement statement = null;
        ResultSet executeQuery = null;
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select status from assignment_status where student_id='" + s2 + "' AND course_id='" + s + "' AND assignment_id='" + s3 + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static Vector<String[]> getReturnDocInfo(final String s, final String s2, final String s3) {
        final Vector<String[]> vector = new Vector<String[]>();
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select returndoc,createdon from assignment_returndoc where student_id='" + s3 + "' AND course_id='" + s + "' AND assignment_id='" + s2 + "'");
            System.out.println("select returndoc,createdon from assignment_returndoc where student_id='" + s3 + "' AND course_id='" + s + "' AND assignment_id='" + s2 + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2) });
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> getFeedbackDetails(final String s, final String s2, final String s3) {
        final Vector<String[]> vector = new Vector<String[]>();
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select feedback from assignment_status where student_id='" + s2 + "' AND course_id='" + s + "' AND assignment_id='" + s3 + "'");
            System.out.println("select feedback from assignment_status where student_id='" + s2 + "' AND course_id='" + s + "' AND assignment_id='" + s3 + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1) });
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static synchronized String getDocFileName(final String s, final String s2) {
        Statement statement = null;
        ResultSet executeQuery = null;
        String string = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select document_name from assignment_student_association where assignment_id='" + s + "' AND doc_id='" + s2 + "'");
            while (executeQuery.next()) {
                System.out.println("after rs.next()");
                string = executeQuery.getString(1);
                System.out.println("rs.getString is:" + string);
            }
        }
        catch (SQLException ex) {
            System.out.println("Exception in getDocFileName.");
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return string;
    }
    
    public static String returnforumname(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select forum_name from forum where forum_id='" + s + "'");
            System.out.println("select forum_name from forum where forum_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static Vector<String[]> getSyllabus(final String s) {
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select topic_id,topic_name, description,course_id  from syllabus_details where course_id ='" + s + "' and (parent_id is null or parent_id='')");
            System.out.println("select topic_id,topic_name, description,course_id  from syllabus_details where course_id ='" + s + "' and (parent_id is null or parent_id='')");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4) });
            }
        }
        catch (SQLException nextException) {
            System.out.println("The Error Message - " + nextException.getMessage());
            System.out.println("The Error Code    - " + nextException.getErrorCode());
            while ((nextException = nextException.getNextException()) != null) {
                System.out.println("The Error message - " + nextException.getMessage());
                System.out.println("The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            System.out.println("The message - " + ex.toString());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> getSyllabus(final String s, final String s2) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select   topic_id,topic_name, description,course_id  from syllabus_details where course_id ='" + s + "' and parent_id='" + s2 + "'");
            System.out.println("select   topic_id,topic_name, description,course_id  from syllabus_details where course_id ='" + s + "' and parent_id='" + s2 + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4) });
            }
        }
        catch (SQLException nextException) {
            while ((nextException = nextException.getNextException()) != null) {}
        }
        catch (Exception ex) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> getCourseTopicResources(final String s, final String s2) {
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("select a.course_id , a.topic_id, a.topicmatid, a.mtype, a.file_name, a.description  from topic_material_details a  where a.course_id='" + s + "' and a.topic_id='" + s2 + "' and a.file_name!=\"\"");
            executeQuery = statement.executeQuery("select a.course_id , a.topic_id, a.topicmatid, a.mtype, a.file_name, a.description  from topic_material_details a  where a.course_id='" + s + "' and a.topic_id='" + s2 + "' and a.file_name!=\"\"");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6) });
            }
        }
        catch (SQLException ex) {
            System.out.println("The message - " + ex.toString());
        }
        catch (Exception ex2) {
            System.out.println("The message - " + ex2.toString());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static synchronized void deleteAllSchedule(final String s) {
        System.out.println("Inside DATABASELAYER");
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("delete from course_schedule where course_id='" + s + "'");
        }
        catch (SQLException ex) {
            System.err.println("sqlexception in deleteAllScheduleDetails " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in deleteAllScheduleDetails " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized boolean updateCourseShedule(final String s, final String s2) {
        Statement statement = null;
        boolean b = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("update  course_definition set file_name1 ='" + s + "' where course_id='" + s2 + "'");
            b = true;
        }
        catch (SQLException ex) {
            System.err.println("sqlexception in updateCourseShedule " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in updateCourseShedule " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return b;
    }
    
    public static synchronized String getCourseScheduleFile(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select file_name1 from course_definition where course_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            System.err.println("sqlexception in updateCourseShedule " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in updateCourseShedule " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static synchronized boolean insertSubTopicDetails(final String s, final String s2, final String s3, final String s4, final String s5) {
        Statement statement = null;
        final boolean b = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into syllabus_details (course_id, topic_id,  parent_id, topic_name,  description) values('" + s + "','" + s3 + "','" + s2 + "','" + s4 + "','" + s5 + "')");
        }
        catch (SQLException ex) {
            System.err.println("sqlexception in insertSubTopicDetails " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in insertSubTopicDetails " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return b;
    }
    
    public static synchronized boolean insertTopicDetails(final String s, final String s2, final String s3, final String s4) {
        Statement statement = null;
        final boolean b = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into syllabus_details (course_id, topic_id,  parent_id, topic_name,  description) values('" + s + "','" + s2 + "',null,'" + s3 + "','" + s4 + "')");
        }
        catch (SQLException ex) {
            System.err.println("sqlexception in insertTopicDetails " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in insertTopicDetails " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return b;
    }
    
    public static synchronized boolean updateTopicDetails(final String s, final String s2, final String s3, final String s4) {
        Statement statement = null;
        boolean b = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("update  syllabus_details set description='" + s4 + "',topic_name='" + s3 + "' where course_id='" + s + "' and topic_id='" + s2 + "'");
            b = true;
        }
        catch (SQLException ex) {
            System.err.println("sqlexception in updateTopicDetails " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in updateTopicDetails " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return b;
    }
    
    public static synchronized boolean deleteTopicDetails(final String s, final String s2) {
        Statement statement = null;
        boolean b = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            b = statement.execute("delete from topic_material_details where course_id ='" + s + "' and topic_id='" + s2 + "'");
            b = statement.execute("delete from syllabus_details where course_id ='" + s + "' and parent_id='" + s2 + "'");
            b = statement.execute("delete from syllabus_details where course_id ='" + s + "' and topic_id='" + s2 + "'");
        }
        catch (SQLException ex) {
            System.err.println("sqlexception in deleteTopicDetails " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in deleteTopicDetails " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return b;
    }
    
    public static synchronized String getNoticeBody(final String s, final String s2) {
        Statement statement = null;
        ResultSet executeQuery = null;
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select body from anno_mgmt where notice_heading='" + s + "' and course_id='" + s2 + "'");
            System.out.println("select body from anno_mgmt where notice_heading='" + s + "' and course_id='" + s2 + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            System.err.println("sqlexception in updateCourseShedule " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in updateCourseShedule " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        System.out.println("----------- Returning Body  ----------> " + string);
        return string;
    }
    
    public static synchronized boolean updateCourseAnnouncement(final String s, final String s2, final String s3) {
        Statement statement = null;
        boolean b = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("update  anno_mgmt set attachments ='" + s2 + "' where course_id='" + s3 + "' and notice_heading='" + s + "'");
            b = true;
        }
        catch (SQLException ex) {
            System.err.println("sqlexception in updateCourseShedule " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in updateCourseShedule " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return b;
    }
    
    public static synchronized boolean deleteAllDefinitionDetails() {
        System.out.println("===========INSIDE deleteAllDefinitionDetails DATABASELAYER===========");
        Statement statement = null;
        Statement statement2 = null;
        ResultSet executeQuery = null;
        boolean b = false;
        Connection connection = null;
        try {
            System.out.println("====INSIDE deleteAllDefinitionDetails TRY=======");
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            executeQuery = statement2.executeQuery("select course_id from course_definition");
            while (executeQuery.next()) {
                if (!IsCourseRegistered(executeQuery.getString(1))) {
                    statement.execute("delete from course_collection_asso where course_id='" + executeQuery.getString(1) + "'");
                    statement.execute("delete from collection_course where course_id='" + executeQuery.getString(1) + "'");
                    statement.execute("delete from course_schedule where course_id='" + executeQuery.getString(1) + "'");
                    statement.execute("delete from topic_material_details where course_id='" + executeQuery.getString(1) + "'");
                    statement.execute("delete from syllabus_details where course_id='" + executeQuery.getString(1) + "'");
                    statement.execute("delete from course_material where course_id='" + executeQuery.getString(1) + "'");
                    statement.execute("delete from anno_mgmt where course_id='" + executeQuery.getString(1) + "'");
                    statement.execute("delete from assignment_course_association where course_id='" + executeQuery.getString(1) + "'");
                    statement.execute("delete from course_definition where course_id='" + executeQuery.getString(1) + "'");
                    statement.execute("delete from book_course_asso where course='" + executeQuery.getString(1) + "'");
                    System.out.println("delete from course_collection_asso where course_id='" + executeQuery.getString(1) + "'");
                    System.out.println("delete from collection_course where course_id='" + executeQuery.getString(1) + "'");
                    System.out.println("delete from course_schedule where course_id='" + executeQuery.getString(1) + "'");
                    System.out.println("delete from topic_material_details where course_id='" + executeQuery.getString(1) + "'");
                    System.out.println("delete from syllabus_details where course_id='" + executeQuery.getString(1) + "'");
                    System.out.println("delete from course_material where course_id='" + executeQuery.getString(1) + "'");
                    System.out.println("delete from anno_mgmt where course_id='" + executeQuery.getString(1) + "'");
                    System.out.println("delete from assignment_course_association where course_id='" + executeQuery.getString(1) + "'");
                    System.out.println("delete from course_definition where course_id='" + executeQuery.getString(1) + "'");
                    System.out.println("delete from book_course_asso where course='" + executeQuery.getString(1) + "'");
                    b = true;
                }
                else {
                    b = false;
                }
            }
        }
        catch (SQLException ex) {
            System.err.println("sqlexception in deleteAllDefinitionDetails " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in deleteAllDefinitionDetails " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    statement2.close();
                    executeQuery.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        System.out.println("======l_bAuth===========" + b);
        return b;
    }
    
    public static synchronized boolean IsCourseRegistered(final String s) {
        Statement statement = null;
        String string = "";
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select student_id from usergroup_course_registration where course_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            System.out.println("Error due to SQL exception IsCourseRegistered: " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println(" error due to java.lang.exception IsCourseRegistered");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return !string.equals("");
    }
    
    public static String isCourseExist(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select course_id from syllabus_details where course_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            System.out.println("Exception in Course check" + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return string;
    }
    
    public static String isTopicExist(final String s, final String s2) {
        Statement statement = null;
        ResultSet executeQuery = null;
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select topic_id from syllabus_details where course_id='" + s + "' and topic_id='" + s2 + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            System.out.println("Exception in Topic check" + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return string;
    }
    
    public static synchronized boolean insertTopicDetailsForExcel(final String s, final String s2, final String s3, final String s4, final String s5) {
        Statement statement = null;
        final boolean b = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            System.out.println("insert into syllabus_details (course_id, topic_id,  parent_id, topic_name,  description) values('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "')");
            statement.execute("insert into syllabus_details (course_id, topic_id,  parent_id, topic_name,  description) values('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "')");
        }
        catch (SQLException ex) {
            System.err.println("sqlexception in insertTopicDetailsForExcel " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in insertTopicDetailsForExcel " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.commit();
                    connection.setAutoCommit(true);
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return b;
    }
    
    public static synchronized boolean updateCourseSyllabus(final String s, final String s2) {
        Statement statement = null;
        boolean b = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("update  course_definition set file_name ='" + s + "' where course_id='" + s2 + "'");
            b = true;
        }
        catch (SQLException ex) {
            System.err.print("sqlexception in updateCourseSyllabus " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in updateCourseSyllabus " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        System.out.println("===================SUCC==================" + b);
        return b;
    }
    
    public static synchronized boolean deleteSyllabusDetails(final String s, final String s2, final String s3) {
        Statement statement = null;
        boolean execute = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            execute = statement.execute("delete from topic_material_details where course_id='" + s + "' and topic_id='" + s2 + "' and topicmatid='" + s3 + "'");
        }
        catch (SQLException ex) {
            System.err.println("sqlexception in deleteSyllabusDetails " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in deleteSyllabusDetails " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.commit();
                    connection.setAutoCommit(true);
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return execute;
    }
    
    public static synchronized boolean insertSyllabusDetails(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Statement statement = null;
        final boolean b = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("insert into topic_material_details (course_id,topic_id,file_name,description,mtype,uploadedby,uploadedon) values('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "',sysdate())");
            statement.execute("insert into topic_material_details (course_id,topic_id,file_name,description,mtype,uploadedby,uploadedon) values('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "',sysdate())");
        }
        catch (SQLException ex) {
            System.err.println("sqlexception in insertTopicDetails " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in insertTopicDetails " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return b;
    }
    
    public static synchronized boolean updateSyllabusDetails(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        System.out.println("===========INSIDE updateSyllabusDetails======== ");
        Statement statement = null;
        boolean b = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("update topic_material_details set mtype ='" + s6 + "',description='" + s5 + "',uploadedby='" + s7 + "',uploadedon=sysdate() where course_id='" + s2 + "' and topic_id='" + s3 + "' and topicmatid ='" + s + "'");
            statement.execute("update topic_material_details set mtype ='" + s6 + "',description='" + s5 + "',uploadedby='" + s7 + "',uploadedon=sysdate() where course_id='" + s2 + "' and topic_id='" + s3 + "' and topicmatid ='" + s + "'");
            b = true;
        }
        catch (SQLException ex) {
            System.err.print("sqlexception in updateSyllabusDetails " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in updateSyllabusDetails " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return b;
    }
    
    public static String getMatTypeFromDB(final int n) {
        Statement statement = null;
        ResultSet executeQuery = null;
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("select mtype from topic_material_details where topicmatid='" + n + "'");
            executeQuery = statement.executeQuery("select mtype from topic_material_details where topicmatid='" + n + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            System.out.println("Exception in Course check" + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return string;
    }
    
    public static synchronized boolean updateMatNametoDB(final String s, final int n) {
        Statement statement = null;
        boolean b = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("update topic_material_details set file_name ='" + s + "' where topicmatid ='" + n + "'");
            statement.execute("update topic_material_details set file_name ='" + s + "' where topicmatid ='" + n + "'");
            b = true;
        }
        catch (SQLException ex) {
            System.err.print("sqlexception in insertURL " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in insertURL " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        System.out.println("=================SUCC===============" + b);
        return b;
    }
    
    public static synchronized boolean insertUserSCOInfo(final String s, final String s2) {
        boolean b = false;
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("select file_name from topic_material_details where course_id='" + s + "' and mtype='Learning Object'");
            final ResultSet executeQuery = statement.executeQuery("select file_name from topic_material_details where course_id='" + s + "' and mtype='Learning Object'");
            while (executeQuery.next()) {
                final String string = executeQuery.getString(1);
                System.out.println("-------------unit_id-----------------" + string);
                final Statement statement2 = connection.createStatement();
                System.out.println("select identifier from iteminfo where unit_id='" + string + "'");
                final ResultSet executeQuery2 = statement2.executeQuery("select identifier from iteminfo where unit_id='" + string + "'");
                while (executeQuery2.next()) {
                    final String string2 = executeQuery2.getString(1);
                    System.out.println("-------------sco_id-----------------" + string2);
                    final Statement statement3 = connection.createStatement();
                    if (!IfSCOIdExists(s, s2)) {
                        System.out.println("insert into userscoinfo (user_id,unit_id,sco_id) values ('" + s2 + "','" + s + "','" + string2 + "')");
                        statement3.execute("insert into userscoinfo (user_id,unit_id,sco_id) values ('" + s2 + "','" + s + "','" + string2 + "')");
                        b = true;
                    }
                    statement3.close();
                }
                statement2.close();
            }
        }
        catch (SQLException ex) {
            System.out.println("Exception in insertUserSCOInfo" + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return b;
    }
    
    public static synchronized boolean IfSCOIdExists(final String s, final String s2) {
        Statement statement = null;
        String string = "";
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("select sco_id from userscoinfo where user_id='" + s2 + "' and unit_id='" + s + "'");
            executeQuery = statement.executeQuery("select sco_id from userscoinfo where user_id='" + s2 + "' and unit_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            System.out.println("Error due to SQL exception IfSCOIdExists: " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println(" error due to java.lang.exception IfSCOIdExists");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return !string.equals("");
    }
    
    public static synchronized boolean deleteUserSCOInfo(final String s, final String s2) {
        boolean b = false;
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            if (IfSCOIdExists(s, s2)) {
                System.out.println("delete from userscoinfo where unit_id='" + s + "' and user_id='" + s2 + "'");
                statement.execute("delete from userscoinfo where unit_id='" + s + "' and user_id='" + s2 + "'");
                b = true;
            }
        }
        catch (SQLException ex) {
            System.out.println("Exception in deleteUserSCOInfo" + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return b;
    }
    
    public static synchronized boolean insertinUserSCOinfo(final String s, final String s2) {
        boolean b = false;
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("select student_id from usergroup_course_registration where course_id='" + s2 + "'");
            executeQuery = statement.executeQuery("select student_id from usergroup_course_registration where course_id='" + s2 + "'");
            while (executeQuery.next()) {
                final String string = executeQuery.getString(1);
                System.out.println("-------------unit_id-----------------" + s);
                final Statement statement2 = connection.createStatement();
                System.out.println("select identifier from iteminfo where unit_id='" + s + "'");
                final ResultSet executeQuery2 = statement2.executeQuery("select identifier from iteminfo where unit_id='" + s + "'");
                while (executeQuery2.next()) {
                    final String string2 = executeQuery2.getString(1);
                    System.out.println("-------------sco_id-----------------" + string2);
                    final Statement statement3 = connection.createStatement();
                    System.out.println("insert into userscoinfo (user_id,unit_id,sco_id) values ('" + string + "','" + s2 + "','" + string2 + "')");
                    statement3.execute("insert into userscoinfo (user_id,unit_id,sco_id) values ('" + string + "','" + s2 + "','" + string2 + "')");
                    b = true;
                    statement3.close();
                }
                statement2.close();
            }
        }
        catch (SQLException ex) {
            System.out.println("Exception in insertUserSCOInfo" + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return b;
    }
    
    public static void updateAssignmentDocument(final String s, final String s2, final String s3, final String s4) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("update assignment_document set document_name='" + s4 + "', size='" + s3 + "',date_uploaded=sysdate() where assignment_id='" + s + "' and document_id='" + s2 + "' ");
        }
        catch (SQLException ex) {
            System.out.println("Exception in addAssignmentDocument.Rolling Back Operation");
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static synchronized void ImportCourseDef(final String[] array, final String s) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into course_definition (course_name, type, sdate, edate, createdon, description, cpoints, ttimes, cost,session,createdby) values('" + array[0] + "','" + array[1] + "','" + array[2] + "','" + array[3] + "',sysdate(),'" + array[5] + "','" + array[6] + "','" + array[7] + "','" + array[8] + "','" + 0 + "','" + s + "')");
            statement.execute("insert into collection_course (course_id,collection_id) values('" + returnmaxcourse() + "','NULL')");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportTopicMaterial(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("insert into topic_material_details (course_id, topic_id, mtype,file_name, description, uploadedon,uploadedby) values('" + s + "','" + s2 + "','" + s3 + "','" + s5 + "','" + s4 + "',sysdate(),'" + s6 + "')");
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
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static String returnmaxcourse() {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select max(course_id) from course_definition");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static synchronized void ImportCourseSyllParent(final String s, final String[] array) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into syllabus_details (course_id, topic_id, parent_id, topic_name, description) values('" + s + "','" + array[0] + "',null,'" + array[2] + "','" + array[3] + "')");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportCourseSyllChild(final String s, final String[] array) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into syllabus_details (course_id, topic_id, parent_id, topic_name, description) values('" + s + "','" + array[0] + "','" + array[1] + "','" + array[2] + "','" + array[3] + "')");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportCourseSess(final String s, final String[] array) {
        Statement statement = null;
        Statement statement2 = null;
        Statement statement3 = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            statement3 = connection.createStatement();
            System.out.println("c_def[1] = " + array[1]);
            System.out.println("c_def[2] = " + array[2]);
            if (array[1] == null || array[1].equals("")) {
                array[1] = "0000-00-00";
            }
            if (array[2] == null || array[2].equals("")) {
                array[2] = "0000-00-00";
            }
            statement.execute("insert into course_session (session_name, sdate, edate, description,createdon) values('" + array[0] + "','" + array[1] + "','" + array[2] + "','" + array[3] + "',sysdate())");
            executeQuery = statement2.executeQuery("select max(session_id) from course_session");
            while (executeQuery.next()) {
                statement3.execute("update course_definition set session = '" + executeQuery.getString(1) + "' where course_id='" + s + "'");
            }
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
                    statement.close();
                    statement2.close();
                    statement3.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportCourseIns(final String s, final String[] array) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("c_def[0] = " + array[0]);
            System.out.println("insert into course_instructor_association(course_id, user_id, show_association, association_label, comment) values('" + s + "','" + array[0] + "','','','')");
            statement.execute("insert into course_instructor_association(course_id, user_id, show_association, association_label, comment) values('" + s + "','" + array[0] + "','','','')");
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
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void UpdateCourseIns(final String s, final String[] array) {
        Statement statement = null;
        Statement statement2 = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            executeQuery = statement2.executeQuery("select max(instructor_id) from course_instructor");
            while (executeQuery.next()) {
                statement.execute("update course_definition set intructor = '" + executeQuery.getString(1) + "' where course_id='" + s + "'");
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    statement2.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportCourseIns1(final String s, final String[] array) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("insert into course_instructor (fname,mname,ename,dob,sex,email,alternatemail,office_no,residence_no,mobile,stime,room,createdon,address) values('" + array[1] + "','" + array[2] + "','" + array[3] + "','" + array[4] + "','" + array[5] + "','" + array[6] + "','" + array[7] + "','" + array[8] + "','" + array[9] + "','" + array[10] + "','" + array[11] + "','" + array[12] + "','" + array[13] + "','" + array[14] + "')");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void UpdateCourseIns1(final String s, final String[] array) {
        Statement statement = null;
        Statement statement2 = null;
        Statement statement3 = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            statement3 = connection.createStatement();
            executeQuery = statement2.executeQuery("select max(instructor_id) from course_instructor");
            while (executeQuery.next()) {
                statement3.execute("update course_definition set intructor1 = '" + executeQuery.getString(1) + "' where course_id='" + s + "'");
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    statement2.close();
                    statement3.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportCourseLoc(final String[] array) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into course_location (location_name,description,address,type) values('" + array[0] + "','" + array[1] + "','" + array[2] + "','" + array[3] + "')");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportCourseSch(final String s, final String[] array) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into course_schedule (course_id, schedule_name, sdate, edate, stime,etime,comment,createdon,location,matType,size,file_name) values('" + s + "','" + array[0] + "','" + array[1] + "','" + array[2] + "','" + array[3] + "','" + array[4] + "','" + array[5] + "',sysdate(),'" + array[6] + "','" + array[7] + "','" + array[8] + "','" + array[9] + "')");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportCourseRes(final String s, final String[] array) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into course_material (course_id, material_name, mtype, size,material,description,uploadedon) values('" + s + "','" + array[0] + "','" + array[1] + "','" + array[2] + "','" + array[3] + "','" + array[4] + "',sysdate())");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportCourseAnno(final String s, final String s2, final String[] array) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("insert into anno_mgmt (course_id, notice_heading,body, attachments, postedon) values('" + s + "','" + s2 + "','" + array[0] + "','" + array[1] + "',sysdate())");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportCourseAssign(final String s, final String[] array) {
        Statement statement = null;
        Statement statement2 = null;
        Statement statement3 = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            statement3 = connection.createStatement();
            statement.execute("insert into assignment (title, submission_last_date,start_avail_date,end_avail_date,description,lateflag,late_allowed_till,penalty_for_late,full_mark,pass_mark) values('" + array[0] + "','" + array[1] + "','" + array[2] + "','" + array[3] + "','" + array[4] + "','" + array[5] + "','" + array[6] + "','" + array[7] + "','" + array[8] + "','" + array[9] + "')");
            executeQuery = statement2.executeQuery("select max(assignment_id) from assignment");
            while (executeQuery.next()) {
                System.out.println("============max assignment_id==========" + executeQuery.getString(1));
                statement3.execute("insert into assignment_document (assignment_id, document_name,size) values ('" + executeQuery.getString(1) + "','" + array[11] + "','" + array[12] + "')");
                statement3.execute("insert into assignment_course_association (course_id,assignment_id) values ('" + s + "','" + executeQuery.getString(1) + "')");
                statement3.execute("insert into item_mgmt (item_id, category_name, item_name) values ('" + executeQuery.getString(1) + "','assignment','" + array[0] + "')");
            }
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
                    statement.close();
                    statement2.close();
                    statement3.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportGScale(final String s) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into scale_mgmt (scale_name,updated_on) values('" + s + "',sysdate())");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportGScheme(final String s, final String s2, final String s3, final String s4) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("insert into scale_scheme (scale_id,grade_name,lower,upper,updated_on) values('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "',sysdate())");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static String returnmaxscale() {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select max(scale_id) from course_definition");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static synchronized void ImportGBookCourseAsso(final String s, final String s2) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into book_course_asso (course,gradebook,updated_on) values('" + s2 + "','" + s + "',sysdate())");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportGBook(final String s, final String s2) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into book_mgmt (book_name,scale,updated_on) values('" + s + "','" + s2 + "',sysdate())");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static String returnmaxgrade() {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select max(book_id) from book_mgmt");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String ImportGetCategoryId(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select category_id from category_mgmt where category_name = '" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException nextException) {
            while ((nextException = nextException.getNextException()) != null) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return string;
    }
    
    public static synchronized void ImportCategory(final String s) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("insert into category_mgmt (category_name,updated_on) values('" + s + "',sysdate())");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportGItem(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("insert into item_mgmt (category_name,item_name,max_marks,pass_marks,scale,attempt,updated_on) values('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "',sysdate())");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportGBookItem(final String s, final String s2, final String s3, final String s4, final String s5) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("insert into book_item_mgmt (book_id,category,item,schedule_date,weightage,updated_on) values('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "',sysdate())");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static String returnmaxitem() {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select max(item_id) from item_mgmt");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static synchronized void ImportGBookItemAssign(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("insert into assignment (title,submission_last_date,start_avail_date,end_avail_date,lateflag,late_allowed_till,penalty_for_late) values('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "')");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static String returnmaxassignment() {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select max(assignment_id) from assignment");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static synchronized void ImportGBookItemAssignCourseAsso(final String s, final String s2) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into assignment_course_association (assignment_id,course_id) values('" + s + "','" + s2 + "')");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void ImportGBookItemAssignDoc(final String s, final String s2, final String s3, final String s4) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("insert into assignment_document (assignment_id,document_id,document_name,size,date_uploaded) values('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "',sysdate())");
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static Vector<String> ImportGCatId() {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String> vector = new Vector<String>(5, 5);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select category_name from category_mgmt");
            while (executeQuery.next()) {
                vector.addElement(executeQuery.getString(1));
            }
        }
        catch (SQLException nextException) {
            while ((nextException = nextException.getNextException()) != null) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vector;
    }
    
    public static String returnmaxdocument() {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select max(document_id) from assignment_document");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static void updateReturnDoc(final String s, final String s2, final String s3, final String s4) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("insert into assignment_returndoc(course_id,assignment_id,student_id,returndoc,createdon)values('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "',sysdate())");
        }
        catch (SQLException ex) {
            System.out.println("Exception in updateReturnDoc.Rolling back operation");
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static void LockSubmittedAssignment(final String s, final String s2, final String s3) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("update assignment_status set lock_status='YES' where course_id='" + s + "' and assignment_id='" + s2 + "' and student_id='" + s3 + "'");
        }
        catch (SQLException ex) {
            System.out.println("Exception in deleteAssignmentDocument.Rolling back operation");
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static void setAssignmentGraded(final String s, final String s2, final String s3) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("update assignment_status set status='Graded' where course_id='" + s + "' AND assignment_id='" + s2 + "' AND student_id='" + s3 + "'");
        }
        catch (SQLException ex) {
            System.out.println("Exception in setAssignmentGraded.Rolling back operation");
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static String getFeedback(final String s, final String s2, final String s3) {
        Statement statement = null;
        ResultSet executeQuery = null;
        String string = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("select feedback from assignment_status where course_id='" + s + "' AND assignment_id='" + s2 + "' AND student_id='" + s3 + "'");
            executeQuery = statement.executeQuery("select feedback from assignment_status where course_id='" + s + "' AND assignment_id='" + s2 + "' AND student_id='" + s3 + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            System.out.println("Exception in getFeedback.");
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return string;
    }
    
    public static void updateAssignmentFeedback(final String s, final String s2, final String s3, final String s4) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("update assignment_status set status='Checked', feedback='" + s4 + "' where course_id='" + s + "' AND assignment_id='" + s2 + "' AND student_id='" + s3 + "'");
        }
        catch (SQLException ex) {
            System.out.println("Exception in updateAssignmentFeedback.Rolling back operation");
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static String getMaterialTypeFromDB(final int n, final int n2) {
        Statement statement = null;
        ResultSet executeQuery = null;
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("select mtype from course_material where material_id='" + n + "' and course_id='" + n2 + "'");
            executeQuery = statement.executeQuery("select mtype from course_material where material_id='" + n + "' and course_id='" + n2 + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            System.out.println("Exception in getMaterialTypeFromDB" + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return string;
    }
    
    public static synchronized boolean updateMaterialNametoDB(final String s, final int n, final int n2, final String s2) {
        Statement statement = null;
        boolean b = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("update course_material set material ='" + s + "',size='" + s2 + "' where material_id ='" + n + "' and course_id='" + n2 + "'");
            statement.execute("update course_material set material ='" + s + "',size='" + s2 + "' where material_id ='" + n + "' and course_id='" + n2 + "'");
            b = true;
        }
        catch (SQLException ex) {
            System.err.print("sqlexception in updateMaterialNametoDB " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in updateMaterialNametoDB " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        System.out.println("=================SUCC===============" + b);
        return b;
    }
    
    public static String[] exportCourseDefinition(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final String[] array = new String[10];
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select course_id, course_name, type,sdate, edate,createdon,description,cpoints,ttimes,cost from course_definition where course_id='" + s + "'");
            while (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
                array[1] = executeQuery.getString(2);
                array[2] = executeQuery.getString(3);
                array[3] = executeQuery.getString(4);
                array[4] = executeQuery.getString(5);
                array[5] = executeQuery.getString(6);
                array[6] = executeQuery.getString(7);
                array[7] = executeQuery.getString(8);
                array[8] = executeQuery.getString(9);
                array[9] = executeQuery.getString(10);
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static String[] exportCourseInstructor(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final String[] array = new String[15];
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select intructor from course_definition where course_id='" + s + "'");
            while (executeQuery.next()) {
                final String string = executeQuery.getString(1);
                if (string != null) {
                    final ResultSet executeQuery2 = statement.executeQuery("select instructor_id,fname,mname,ename,dob,sex,email,alternatemail,office_no,residence_no,mobile,stime,room,createdon,address from course_instructor where instructor_id='" + string + "'");
                    while (executeQuery2.next()) {
                        array[0] = executeQuery2.getString(1);
                        array[1] = executeQuery2.getString(2);
                        array[2] = executeQuery2.getString(3);
                        array[3] = executeQuery2.getString(4);
                        array[4] = executeQuery2.getString(5);
                        array[5] = executeQuery2.getString(6);
                        array[6] = executeQuery2.getString(7);
                        array[7] = executeQuery2.getString(8);
                        array[8] = executeQuery2.getString(9);
                        array[9] = executeQuery2.getString(10);
                        array[10] = executeQuery2.getString(11);
                        array[11] = executeQuery2.getString(12);
                        array[12] = executeQuery2.getString(13);
                        array[13] = executeQuery2.getString(14);
                        array[14] = executeQuery2.getString(15);
                    }
                }
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static Vector<String[]> exportCourseLocation(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select distinct(a.location_id),a.location_name,a.description,a.address,a.type,b.course_id,b.location from course_location a,course_schedule b where b.course_id='" + s + "' and b.location=a.location_id");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5) });
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static String[] exportCourseInstructor1(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final String[] array = new String[15];
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select intructor1 from course_definition where course_id='" + s + "'");
            while (executeQuery.next()) {
                final String string = executeQuery.getString(1);
                if (string != null) {
                    final ResultSet executeQuery2 = statement.executeQuery("select instructor_id,fname,mname,ename,dob,sex,email,alternatemail,office_no,residence_no,mobile,stime,room,createdon,address from course_instructor where instructor_id='" + string + "'");
                    while (executeQuery2.next()) {
                        array[0] = executeQuery2.getString(1);
                        array[1] = executeQuery2.getString(2);
                        array[2] = executeQuery2.getString(3);
                        array[3] = executeQuery2.getString(4);
                        array[4] = executeQuery2.getString(5);
                        array[5] = executeQuery2.getString(6);
                        array[6] = executeQuery2.getString(7);
                        array[7] = executeQuery2.getString(8);
                        array[8] = executeQuery2.getString(9);
                        array[9] = executeQuery2.getString(10);
                        array[10] = executeQuery2.getString(11);
                        array[11] = executeQuery2.getString(12);
                        array[12] = executeQuery2.getString(13);
                        array[13] = executeQuery2.getString(14);
                        array[14] = executeQuery2.getString(15);
                    }
                }
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static String[] exportCourseSession(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final String[] array = new String[6];
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            final Statement statement2 = connection.createStatement();
            executeQuery = statement.executeQuery("select session from course_definition where course_id='" + s + "'");
            while (executeQuery.next()) {
                final String string = executeQuery.getString(1);
                if (string != null) {
                    final ResultSet executeQuery2 = statement2.executeQuery("select session_id,session_name,sdate,edate,description,createdon from course_session where session_id='" + string + "'");
                    while (executeQuery2.next()) {
                        array[0] = executeQuery2.getString(1);
                        array[1] = executeQuery2.getString(2);
                        array[2] = executeQuery2.getString(3);
                        array[3] = executeQuery2.getString(4);
                        array[4] = executeQuery2.getString(5);
                        array[5] = executeQuery2.getString(6);
                    }
                }
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static Vector<String[]> exportCourseSchedule(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select a.schedule_id, a.schedule_name, date_format(a.sdate,'%Y-%m-%d'),date_format(a.edate,'%Y-%m-%d'), a.stime, a.etime ,a.comment,a.location,a.matType,a.size,a.file_name from course_schedule a  where a.course_id='" + s + "' ");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6), executeQuery.getString(7), executeQuery.getString(8), executeQuery.getString(9), executeQuery.getString(10), executeQuery.getString(11) });
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> exportCourseAssignment(final String s) {
        Statement statement = null;
        final Vector<String[]> vector = new Vector<String[]>(3, 3);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select a.assignment_id,b.course_id from course_definition b,assignment_course_association a where b.course_id='" + s + "' and a.course_id=b.course_id");
            while (executeQuery.next()) {
                final ResultSet executeQuery2 = connection.createStatement().executeQuery("select a.assignment_id, a.title, date_format(a.submission_last_date,'%Y-%m-%d'),date_format(a.start_avail_date,'%Y-%m-%d'),date_format(a.end_avail_date,'%Y-%m-%d'),a.description,a.lateflag,date_format(a.late_allowed_till,'%Y-%m-%d'),a.penalty_for_late,a.full_mark,a.pass_mark from assignment a where a.assignment_id='" + executeQuery.getString(1) + "' ");
                executeQuery2.next();
                vector.addElement(new String[] { executeQuery2.getString(1), executeQuery2.getString(2), executeQuery2.getString(3), executeQuery2.getString(4), executeQuery2.getString(5), executeQuery2.getString(6), executeQuery2.getString(7), executeQuery2.getString(8), executeQuery2.getString(9), executeQuery2.getString(10), executeQuery2.getString(11) });
            }
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
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> exportCourseAssignmentDocument(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(2, 2);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select a.document_id,a.document_name,size from assignment_document a where a.assignment_id='" + s + "' ");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3) });
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static String[] exportScheduleFile(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final String[] array = new String[2];
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select file_name, file_name1  from course_definition where course_id='" + s + "'");
            if (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
                array[1] = executeQuery.getString(2);
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static Vector<String[]> exportSyllabus(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select topic_id,topic_name, description  from syllabus_details where course_id ='" + s + "' and parent_id is null");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3) });
            }
        }
        catch (SQLException nextException) {
            while ((nextException = nextException.getNextException()) != null) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> exportSyllabusTopic(final String s, final String s2) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("select topic_id,topic_name, description  from syllabus_details where course_id ='" + s + "' and parent_id='" + s2 + "'");
            executeQuery = statement.executeQuery("select topic_id,topic_name, description  from syllabus_details where course_id ='" + s + "' and parent_id='" + s2 + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3) });
            }
        }
        catch (SQLException nextException) {
            while ((nextException = nextException.getNextException()) != null) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> exportTopicMaterial(final String s, final String s2) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select topicmatid,mtype,description,file_name  from topic_material_details where course_id ='" + s + "' and topic_id = '" + s2 + "' and (mtype='File' or mtype='Web URL')");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4) });
            }
        }
        catch (SQLException nextException) {
            while ((nextException = nextException.getNextException()) != null) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> exportCourseResource(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select material_id,material_name,mtype,size,material,description  from course_material where course_id ='" + s + "' and (mtype='File' or mtype='Web URL') ");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6) });
            }
        }
        catch (SQLException nextException) {
            while ((nextException = nextException.getNextException()) != null) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> exportCourseAnnounce(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select notice_heading,body,attachments  from anno_mgmt where course_id ='" + s + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3) });
            }
        }
        catch (SQLException nextException) {
            while ((nextException = nextException.getNextException()) != null) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vector;
    }
    
    public static String[] exportCourseGB(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final String[] array = new String[3];
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select gradebook from book_course_asso where course='" + s + "'");
            while (executeQuery.next()) {
                final String string = executeQuery.getString(1);
                if (string != null) {
                    final ResultSet executeQuery2 = statement.executeQuery("select book_id,book_name,scale from book_mgmt where book_id='" + string + "'");
                    while (executeQuery2.next()) {
                        array[0] = executeQuery2.getString(1);
                        array[1] = executeQuery2.getString(2);
                        array[2] = executeQuery2.getString(3);
                    }
                }
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static String[] exportCourseSc(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final String[] array = new String[2];
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select scale_id,scale_name  from scale_mgmt where scale_id='" + s + "'");
            if (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
                array[1] = executeQuery.getString(2);
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static Vector<String[]> exportScaleSch(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select grade_name,lower,upper  from scale_scheme where scale_id ='" + s + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3) });
            }
        }
        catch (SQLException nextException) {
            while ((nextException = nextException.getNextException()) != null) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> exportItemAssignmentDoc(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select document_id,document_name,size  from assignment_document where assignment_id ='" + s + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3) });
            }
        }
        catch (SQLException nextException) {
            while ((nextException = nextException.getNextException()) != null) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> exportCourseCategory(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            final Statement statement2 = connection.createStatement();
            executeQuery = statement.executeQuery("select category from book_item_mgmt where book_id='" + s + "' group by category");
            while (executeQuery.next()) {
                final ResultSet executeQuery2 = statement2.executeQuery("select category_id,category_name  from category_mgmt where category_id ='" + executeQuery.getString(1) + "'");
                while (executeQuery2.next()) {
                    vector.addElement(new String[] { executeQuery2.getString(1), executeQuery2.getString(2) });
                }
            }
        }
        catch (SQLException nextException) {
            while ((nextException = nextException.getNextException()) != null) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> exportCourseItem(final String s, final String s2) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(5, 5);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            final Statement statement2 = connection.createStatement();
            executeQuery = statement.executeQuery("select item from book_item_mgmt where book_id='" + s + "' and category = '" + s2 + "'");
            while (executeQuery.next()) {
                final ResultSet executeQuery2 = statement2.executeQuery("select item_id,item_name,max_marks,pass_marks,scale,attempt  from item_mgmt where item_id ='" + executeQuery.getString(1) + "'");
                while (executeQuery2.next()) {
                    vector.addElement(new String[] { executeQuery2.getString(1), executeQuery2.getString(2), executeQuery2.getString(3), executeQuery2.getString(4), executeQuery2.getString(5), executeQuery2.getString(6) });
                }
            }
        }
        catch (SQLException nextException) {
            while ((nextException = nextException.getNextException()) != null) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vector;
    }
    
    public static String[] exportBookItem(final String s, final String s2, final String s3) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final String[] array = new String[2];
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select schedule_date,weightage  from book_item_mgmt where book_id='" + s + "' and category='" + s2 + "' and item='" + s3 + "'");
            if (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
                array[1] = executeQuery.getString(2);
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static String[] exportBookItemAssignment(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final String[] array = new String[7];
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select title,submission_last_date,start_avail_date,end_avail_date,lateflag,late_allowed_till,penalty_for_late  from assignment where assignment_id='" + s + "'");
            if (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
                array[1] = executeQuery.getString(2);
                array[2] = executeQuery.getString(3);
                array[3] = executeQuery.getString(4);
                array[4] = executeQuery.getString(5);
                array[5] = executeQuery.getString(6);
                array[6] = executeQuery.getString(7);
            }
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
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static synchronized void insertViewPreviledge(final String s, final String s2) {
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select count(*) from grade_view where course='" + s + "'");
            int int1 = 0;
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
            if (int1 <= 0) {
                statement.execute("insert into grade_view values ('" + s + "','" + s2 + "')");
            }
            else {
                statement.executeUpdate("update grade_view set view = '" + s2 + "' where course = '" + s + "'");
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("SQLException: ");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception: ");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized String getViewPreviledge(final String s) {
        Statement statement = null;
        ResultSet set = null;
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery("select count(*) from grade_view where course='" + s + "'");
            int int1 = 0;
            while (set.next()) {
                int1 = set.getInt(1);
            }
            set.close();
            statement.close();
            if (int1 <= 0) {
                return null;
            }
            statement = connection.createStatement();
            set = statement.executeQuery("select view from grade_view where course = '" + s + "'");
            set.next();
            string = set.getString(1);
            set.close();
            statement.close();
            connection.close();
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Error :");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Error :");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    set.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static Vector<String[]> selectItemForCourse(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(2, 2);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            final Statement statement2 = connection.createStatement();
            executeQuery = statement.executeQuery("select a.gradebook from book_course_asso a where a.course='" + s + "'");
            while (executeQuery.next()) {
                final String string = executeQuery.getString(1);
                System.out.println("select a.item_id,a.item_name from item_mgmt a,book_item_mgmt b where a.item_id=b.item and b.book_id='" + string + "'");
                final ResultSet executeQuery2 = statement2.executeQuery("select a.item_id,a.item_name from item_mgmt a,book_item_mgmt b where a.item_id=b.item and b.book_id='" + string + "'");
                while (executeQuery2.next()) {
                    final String[] array = { executeQuery2.getString(1), null };
                    System.out.println("---- str[0]--" + array[0]);
                    array[1] = executeQuery2.getString(2);
                    System.out.println("---- str[1]--" + array[1]);
                    vector.addElement(array);
                }
            }
        }
        catch (SQLException ex) {
            System.out.println("Exception in DatabaseLayer.selectItemForCourse()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println("Exception in DatabaseLayer.selectItemForCourse()");
            System.out.println("The Error Message - " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static Vector<String[]> getStudentList(final String s) {
        final Vector<String[]> vector = new Vector<String[]>(3, 3);
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select a.student_id,concat(a.first_name,' ',a.middle_name,' ',a.sname) from student_details a,usergroup_course_registration c,user_role d,role e where c.course_id='" + s + "'  and c.student_id=a.student_id and a.student_id=d.user_id and d.role_id=e.role_id and e.title='STUDENT' ");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2) });
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in TrainingDatabaseLayer.getStudent()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in TrainingDatabaseLayer.getStudent()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static String getAttempt(final String s) {
        String string = "";
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("select attempt from item_mgmt where item_id='" + s + "'");
            final ResultSet executeQuery = statement.executeQuery("select attempt from item_mgmt where item_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getAttempt()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getAttempt()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String getMarks(final String s, final String s2, final String s3, final String s4) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("select marks from grade_mgmt where course='" + s + "' and item='" + s2 + "' and attempt='" + s4 + "' and student='" + s3 + "'");
            executeQuery = statement.executeQuery("select marks from grade_mgmt where course='" + s + "' and item='" + s2 + "' and attempt='" + s4 + "' and student='" + s3 + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static void addGradeMgmt(final String s, final String s2, final String s3, final String s4, String s5, final String s6, final String s7) {
        Statement statement = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            DataBaseLayer.log.entry("addGradeMgmt()");
            if (s5.equals("") || s6.equals("N")) {
                s5 = "0";
            }
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            set = statement.executeQuery("select max_marks from item_mgmt where item_id ='" + s2 + "'");
            set.next();
            final int int1 = Integer.parseInt(set.getString(1));
            final int int2 = Integer.parseInt(s5);
            if (0 <= int2 && int2 <= int1) {
                set = statement.executeQuery("select marks from grade_mgmt where course='" + s + "' and item='" + s2 + "' and attempt='" + s4 + "' and student='" + s3 + "'");
                if (set.next()) {
                    final String string = set.getString(1);
                    DataBaseLayer.log.verbose("from gradedatabaselayer marks::" + string);
                    DataBaseLayer.log.verbose("from gradedatabaselayer marks::" + string);
                    statement.execute("update grade_mgmt set marks = '" + s5 + "',status = '" + s6 + "',updated_by = '" + s7 + "',updated_on = sysdate() where course = '" + s + "' and item = '" + s2 + "' and student = '" + s3 + "' and attempt = '" + s4 + "'");
                }
                else {
                    statement.execute("insert into grade_mgmt(course,item, student, attempt,marks,status,updated_by,updated_on) values ('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "',sysdate())");
                    System.out.println("insert into grade_mgmt(course,item, student, attempt,marks,status,updated_by,updated_on) values ('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "',sysdate())");
                }
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.addGradeMgmt()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.addGradeMgmt()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    set.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static void insertOverallMarks(final String s, final String s2, final double n) {
        Statement statement = null;
        Statement statement2 = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            statement2 = connection.createStatement();
            statement = connection.createStatement();
            String string = "";
            executeQuery = statement.executeQuery("select overallmarks from overall_marks where course_id='" + s + "' and student_id='" + s2 + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            if (string.equals("")) {
                statement2.execute("insert into overall_marks(course_id, student_id,overallmarks) values ('" + s + "','" + s2 + "','" + n + "')");
            }
            else {
                statement2.execute("update overall_marks set overallmarks='" + n + "' where course_id = '" + s + "' and student_id ='" + s2 + "'");
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in DatabaseLayer.insertOverallMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in DatabaseLayer.insertOverallMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement2.close();
                    statement.close();
                    executeQuery.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static String[] selectLowMarks1(final String s, final String s2, final String s3) {
        final String[] array = new String[5];
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            DataBaseLayer.log.entry("selectLowMarks()");
            statement = connection.createStatement();
            System.out.println("select min(marks) from grade_mgmt where course='" + s + "' and item='" + s2 + "' and attempt='" + s3 + "'");
            executeQuery = statement.executeQuery("select min(marks) from grade_mgmt where course='" + s + "' and item='" + s2 + "' and attempt='" + s3 + "'");
            while (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getLowMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getLowMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static String[] selectHighMarks1(final String s, final String s2, final String s3) {
        final String[] array = new String[5];
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            DataBaseLayer.log.entry("selectHighMarks()");
            statement = connection.createStatement();
            DataBaseLayer.log.verbose("select min(marks) from grade_mgmt where course='" + s + "' and item='" + s2 + "' and attempt='" + s3 + "'");
            executeQuery = statement.executeQuery("select max(marks) from grade_mgmt where course='" + s + "' and item='" + s2 + "' and attempt='" + s3 + "'");
            while (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getHighMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getHighMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static String[] selectAvgMarks1(final String s, final String s2, final String s3) {
        final String[] array = new String[5];
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            DataBaseLayer.log.entry("selectAvgMarks()");
            statement = connection.createStatement();
            DataBaseLayer.log.verbose("select round(avg(marks)) from grade_mgmt where course='" + s + "' and item='" + s2 + "' and attempt='" + s3 + "'");
            executeQuery = statement.executeQuery("select round(avg(marks),2) from grade_mgmt where course='" + s + "' and item='" + s2 + "' and attempt='" + s3 + "'");
            while (executeQuery.next()) {
                DataBaseLayer.log.verbose("It is crossing the 1st for loop");
                array[0] = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getAvgMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getAvgMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static String[] selectFullMarks1(final String s) {
        final String[] array = new String[5];
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            DataBaseLayer.log.entry("selectFullMarks()");
            statement = connection.createStatement();
            DataBaseLayer.log.verbose("select max_marks from item_mgmt where item_name='" + s + "'");
            executeQuery = statement.executeQuery("select max_marks from item_mgmt where item_id='" + s + "'");
            while (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getFullMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getFullMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static String[] selectPassMarks1(final String s) {
        final String[] array = new String[5];
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            DataBaseLayer.log.entry("selectPassMarks()");
            statement = connection.createStatement();
            DataBaseLayer.log.verbose("select pass_marks from item_mgmt where item_name='" + s + "'");
            executeQuery = statement.executeQuery("select pass_marks from item_mgmt where item_id='" + s + "'");
            while (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getPassMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getPassMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static Vector<String> calculateSum1(final String s, final String s2) {
        final Vector<String> vector = new Vector<String>(1, 1);
        final String[] array = new String[5];
        final int n = 1;
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            DataBaseLayer.log.entry("selectAvgMarks()");
            statement = connection.createStatement();
            DataBaseLayer.log.verbose("select round(avg(marks)) from grade_mgmt where course='" + s + "' and  attempt='" + s2 + "'");
            executeQuery = statement.executeQuery("select sum(marks) from grade_mgmt where course='" + s + "' and attempt='" + s2 + "' group by student");
            while (executeQuery.next()) {
                DataBaseLayer.log.verbose("It is crossing the 1st for loop");
                array[0] = executeQuery.getString(n);
                System.out.println("#####################" + array[0]);
                vector.addElement(array[0]);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getAvgMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.getAvgMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static String[] selectWt1(final String s) {
        final String[] array = new String[5];
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select a.weightage from book_item_mgmt a where a.item='" + s + "'");
            while (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.selectWt()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.selectWt()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static String[] selectMarks1(final String s, final String s2, final String s3, final String s4) {
        final String[] array = new String[5];
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            DataBaseLayer.log.verbose("select marks from grade_mgmt where student='" + s + "' and course='" + s2 + "' and item='" + s3 + "' and attempt='" + s4 + "'");
            executeQuery = statement.executeQuery("select marks from grade_mgmt where student='" + s + "' and course='" + s2 + "' and item='" + s3 + "' and attempt='" + s4 + "'");
            while (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.selectMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Exception in GradeDatabaseLayer.selectMarks()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static Vector<String> selectStudentInformation(final String s) {
        final Vector<String> vector = new Vector<String>(3, 3);
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("---->>>>----selectStudentInformation()-- select distince student from grade_mgmt where course='" + s + "'");
            executeQuery = statement.executeQuery("select distinct student from grade_mgmt where course='" + s + "'");
            while (executeQuery.next()) {
                vector.addElement(executeQuery.getString(1));
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("---->>>>---- Exception in DataBaseLayer.selectStudentInformation()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("---->>>>---- Exception in DataBaseLayer.selectStudentInformation()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return vector;
    }
    
    public static boolean allAttempted(final String s, final String s2) {
        boolean b = false;
        Statement statement = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            DataBaseLayer.log.entry("allAttempted()");
            statement = connection.createStatement();
            set = statement.executeQuery("select a.gradebook from book_course_asso a where a.course='" + s2 + "'");
            set.next();
            final String string = set.getString(1);
            set = statement.executeQuery("select count(distinct(b.item)) from grade_mgmt a, book_item_mgmt b where a.student='" + s + "' and b.book_id ='" + string + "' and a.item=b.item and a.course='" + s2 + "'");
            set.next();
            final int int1 = set.getInt(1);
            System.out.println("**************no_of_item===" + int1);
            set = statement.executeQuery("select count(item) from book_item_mgmt where book_id='" + string + "'");
            set.next();
            final int int2 = set.getInt(1);
            System.out.println("**************book_item===" + int2);
            DataBaseLayer.log.verbose("value of items and attempted****************:" + int2 + "," + int1);
            if (int1 == int2) {
                b = true;
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Error :");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Error :");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    set.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        System.out.println("###########################" + b);
        return b;
    }
    
    public static String getOverallGrade(final Double n, final String s) {
        String string = "";
        Statement statement = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery("select a.gradebook from book_course_asso a where a.course='" + s + "'");
            set.next();
            final String string2 = set.getString(1);
            System.out.println("---->>>>---- select distinct(a.grade_name) from scale_scheme a, scale_mgmt b, book_mgmt c where c.book_id='" + string2 + "' and c.scale =a.scale_id and a.lower<='" + n + "' and a.upper>='" + n + "'");
            set = statement.executeQuery("select distinct(a.grade_name) from scale_scheme a, scale_mgmt b, book_mgmt c where c.book_id='" + string2 + "' and c.scale =a.scale_id and a.lower<='" + n + "' and a.upper>='" + n + "'");
            set.next();
            string = set.getString(1);
        }
        catch (SQLException ex) {
            DataBaseLayer.log.fatal("Error :");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        catch (Exception ex2) {
            DataBaseLayer.log.fatal("Error :");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex2);
        }
        finally {
            if (connection != null) {
                try {
                    set.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String getLObjectName(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select unit_name from unit_details where unit_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static synchronized boolean insertGroupCourseReg(final String s, final String s2, final String s3, final String s4, final String s5) {
        boolean b = false;
        Statement statement = null;
        Statement statement2 = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement2 = connection.createStatement();
            statement = connection.createStatement();
            System.out.println("select student_id from student_group_association where group_id='" + s2 + "'");
            executeQuery = statement2.executeQuery("select student_id from student_group_association where group_id='" + s2 + "'");
            while (executeQuery.next()) {
                final String string = executeQuery.getString(1);
                System.out.println("-------------student_id-----------------" + string);
                System.out.println("insert into usergroup_course_registration(course_id, student_id, registration_date,registration_by,access_allowed_till,total_access_time,modification_date) values ('" + s + "','" + string + "',sysdate(),'" + s5 + "','" + s4 + "','" + s3 + "',sysdate())");
                statement.execute("insert into usergroup_course_registration(course_id, student_id, registration_date,registration_by,access_allowed_till,total_access_time,modification_date) values ('" + s + "','" + string + "',sysdate(),'" + s5 + "','" + s4 + "','" + s3 + "',sysdate())");
                b = true;
            }
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
                    statement.close();
                    statement2.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return b;
    }
    
    public static synchronized boolean modifyGroupCourseReg(final String s, final String s2, final String s3, final String s4, final String s5) {
        boolean b = false;
        Statement statement = null;
        Statement statement2 = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement2 = connection.createStatement();
            statement = connection.createStatement();
            System.out.println("select student_id from student_group_association where group_id='" + s2 + "'");
            executeQuery = statement2.executeQuery("select student_id from student_group_association where group_id='" + s2 + "'");
            while (executeQuery.next()) {
                final String string = executeQuery.getString(1);
                System.out.println("-------------student_id-----------------" + string);
                System.out.println("update usergroup_course_registration set access_allowed_till='" + s4 + "',total_access_time='" + s3 + "', modification_date=sysdate() where student_id='" + string + "' and course_id='" + s + "'");
                statement.execute("update usergroup_course_registration set access_allowed_till='" + s4 + "',total_access_time='" + s3 + "', modification_date=sysdate() where student_id='" + string + "' and course_id='" + s + "'");
                b = true;
            }
        }
        catch (SQLException ex) {
            System.out.println("Exception in modifyGroupCourseReg" + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    statement2.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return b;
    }
    
    public static String getMaterialTypeFromDBforSchedule(final int n, final int n2) {
        Statement statement = null;
        ResultSet executeQuery = null;
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("select matType from course_schedule where schedule_id='" + n + "' and course_id='" + n2 + "'");
            executeQuery = statement.executeQuery("select matType from course_schedule where schedule_id='" + n + "' and course_id='" + n2 + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            System.out.println("Exception in getMaterialTypeFromDBforSchedule" + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return string;
    }
    
    public static synchronized boolean updateMaterialNametoDBForSchedule(final String s, final int n, final int n2, final String s2) {
        Statement statement = null;
        boolean b = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("update course_schedule set file_name ='" + s + "',size='" + s2 + "' where schedule_id ='" + n + "' and course_id='" + n2 + "'");
            statement.execute("update course_schedule set file_name ='" + s + "',size='" + s2 + "' where schedule_id ='" + n + "' and course_id='" + n2 + "'");
            b = true;
        }
        catch (SQLException ex) {
            System.err.print("sqlexception in updateMaterialNametoDBForSchedule " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in updateMaterialNametoDBForSchedule " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        System.out.println("=================SUCC===============" + b);
        return b;
    }
    
    public static String getMarksScheme(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("select i.marks_scheme from item_mgmt i where i.item_id='" + s + "'");
            executeQuery = statement.executeQuery("select i.marks_scheme from item_mgmt i where i.item_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            System.out.println("Exception in getMaterialTypeFromDBforSchedule" + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return string;
    }
    
    public static String returncoursedesc(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select description from course_definition where course_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static String[] CourseDefinitionDetails(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        final String[] array = new String[11];
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select course_name, description,intructor,intructor1,type,cpoints,ttimes,cost,date_format(sdate,\"%d-%m-%Y\"),date_format(edate,\"%d-%m-%Y\"),session from course_definition where course_id='" + s + "'");
            if (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
                array[1] = executeQuery.getString(2);
                array[2] = executeQuery.getString(3);
                array[3] = executeQuery.getString(4);
                array[4] = executeQuery.getString(5);
                array[5] = executeQuery.getString(6);
                array[6] = executeQuery.getString(7);
                array[7] = executeQuery.getString(8);
                array[8] = executeQuery.getString(9);
                array[9] = executeQuery.getString(10);
                array[10] = executeQuery.getString(11);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static synchronized void UpdateCourseDetailsByTeacher(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("update course_definition set description='" + s2 + "',intructor='" + s3 + "',intructor1='" + s4 + "',type='" + s5 + "',cpoints='" + s6 + "',ttimes='" + s7 + "',cost='" + s8 + "',sdate='" + s9 + "',edate='" + s10 + "',session='" + s11 + "' where course_id='" + s + "'");
        }
        catch (SQLException ex) {
            System.out.println("SQL error inside NewDataBaseLayer.UpdateCourseDetailsByTeacher()");
            System.out.println("The error code is" + ex.getErrorCode());
            System.out.println("The error message is" + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println("General exception inside NewDataBaseLayer.UpdateCourseDetailsByTeacher()");
            ex2.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void insertAssignmentByTeacher(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, String s9, String s10, String s11, final String s12) {
        if (s9 == null || s9.equals("") || s9.equals("null")) {
            s9 = "0";
        }
        if (s10 == null || s10.equals("") || s10.equals("null")) {
            s10 = "0";
        }
        if (s11 == null || s11.equals("") || s11.equals("null")) {
            s11 = "0";
        }
        Statement statement = null;
        Statement statement2 = null;
        Statement statement3 = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            statement3 = connection.createStatement();
            statement.execute("insert into assignment(title,description,submission_last_date,start_avail_date,end_avail_date,lateflag,late_allowed_till,penalty_for_late,full_mark,pass_mark) values('" + s2 + "','" + s3 + "'," + s4 + "," + s5 + "," + s6 + ",'" + s7 + "'," + s8 + ",'" + s9 + "','" + s10 + "','" + s11 + "') ");
            executeQuery = statement2.executeQuery("select max(assignment_id) from assignment");
            while (executeQuery.next()) {
                System.out.println("==============max assignment_id======" + executeQuery.getString(1));
                statement.execute("insert into item_mgmt(category_name,item_name,updated_by,updated_on,assignment_id) values('assignment','" + s2 + "','" + s12 + "',sysdate(),'" + executeQuery.getString(1) + "') ");
                statement3.execute("insert into assignment_course_association(assignment_id,course_id) values('" + executeQuery.getString(1) + "','" + s + "')");
            }
        }
        catch (SQLException ex) {
            System.out.println("SQL error inside NewDataBaseLayer.insertAssignmentByTeacher()");
            System.out.println("The error code is" + ex.getErrorCode());
            System.out.println("The error message is" + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println("General exception inside NewDataBaseLayer.insertAssignmentByTeacher()");
            ex2.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    statement2.close();
                    statement3.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized boolean IsDocIdExists(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        boolean b = false;
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select document_id from assignment_document where assignment_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            b = (string != null && !string.equals("") && !string.equals("null"));
        }
        catch (SQLException ex) {
            System.err.println("sqlexception in IsDocIdExists " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in IsDocIdExists " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    if (executeQuery != null) {
                        executeQuery.close();
                    }
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return b;
    }
    
    public static void insertDocumenttoAssignment(final String s) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("insert into assignment_document(assignment_id,date_uploaded)  values('" + s + "',sysdate())");
        }
        catch (SQLException ex) {
            System.out.println("Inside NewDataBaseLayer insertDocumenttoAssignment, Error due to SQL exception!");
            ex.getErrorCode();
            System.out.println("The Error is :- " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println("Inside NewDataBaseLayer insertDocumenttoAssignment, exception!");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized String getDocumentIdFromAssignment(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select document_id from assignment_document where assignment_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            System.err.println("sqlexception in getDocumentIdFromAssignment " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.err.println("exception in getDocumentIdFromAssignment " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    if (executeQuery != null) {
                        executeQuery.close();
                    }
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static void insertMaterialTeacherDetails(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("insert into course_material (course_id,material_name,mtype,size,material,uploadedby,uploadedon,description) values('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "',sysdate(),'" + s7 + "')");
        }
        catch (SQLException ex) {
            System.out.println("Inside DataBaseLayer insertMaterialTeacherDetails, Error due to SQL exception!");
            ex.getErrorCode();
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            System.out.println("Inside DataBaseLayer insertMaterialTeacherDetails, exception!");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void updateMaterialTeacherDetails(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.execute("update course_material set material_name ='" + s2 + "',description='" + s6 + "',mtype='" + s3 + "',size ='" + s4 + "',material='" + s5 + "',uploadedby='" + s8 + "',uploadedon= sysdate() where course_id='" + s + "' and material_id ='" + s7 + "'");
        }
        catch (SQLException ex) {
            System.out.println("SQL error inside DataBaseLayer.updateMaterialTeacherDetails()");
            System.out.println("The error code is" + ex.getErrorCode());
            System.out.println("The error message is" + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println("General exception inside DataBaseLayer.updateMaterialTeacherDetails()");
            ex2.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static void deleteMaterialTeacherDetails(final String s, final String s2) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("delete from course_material where course_id ='" + s + "' and material_id='" + s2 + "'");
        }
        catch (SQLException ex) {
            System.out.println("SQLException in deleteMaterialTeacherDetails");
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            System.out.println("Exception in deleteMaterialTeacherDetails");
            ex2.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized String[] getInstructorData(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        //final Vector vector = new Vector();
        final String[] array = new String[15];
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("-------------------select * from student_details where student_id='" + s + "'");
            executeQuery = statement.executeQuery("select * from student_details where student_id='" + s + "'");
            while (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
                array[1] = executeQuery.getString(2);
                array[2] = executeQuery.getString(3);
                if (array[2] != null && (array[2].equals("M") || array[2].equals("m"))) {
                    array[2] = "Male";
                }
                else if (array[2] != null && (array[2].equals("F") || array[2].equals("f"))) {
                    array[2] = "Female";
                }
                else {
                    array[2] = "";
                }
                array[3] = executeQuery.getString(4);
                array[4] = executeQuery.getString(5);
                array[5] = executeQuery.getString(6);
                array[6] = executeQuery.getString(7);
                array[7] = executeQuery.getString(8);
                array[8] = executeQuery.getString(9);
                array[9] = executeQuery.getString(10);
                array[10] = executeQuery.getString(11);
                array[11] = executeQuery.getString(12);
                array[12] = executeQuery.getString(13);
                if (array[12] == null) {
                    array[12] = "";
                }
                else {
                    array[12] = "<a href=mailto:" + array[12] + ">" + array[12] + "<a>";
                }
                array[13] = executeQuery.getString(14);
                array[14] = executeQuery.getString(15);
                if (array[14] != null || array[14].equals("T") || array[14].equals("t")) {
                    array[14] = "Yes";
                }
                else {
                    array[14] = "No";
                }
            }
        }
        catch (SQLException ex) {
            System.out.println("Error due to SQL exception : " + ex.getMessage());
            System.out.println(" error from DataBaseLayer.getUserData()");
        }
        catch (Exception ex2) {
            System.out.println(" error due to java.lang.exception");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    executeQuery.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    static String getDropdownSqlQuery(final String s, final String s2) {
        String string = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet executeQuery = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select value from content where interface_id='" + s + "' and part_id='" + s2 + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            System.out.println("=====update======s_query==========" + string);
            executeQuery.close();
            connection.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    executeQuery.close();
                    statement.close();
                    connection.close();
                }
                catch (Exception ex2) {}
            }
        }
        return string;
    }
    
    public static Vector<String> returnpagedropdown(final String s) {
        System.out.println("===============sql_query in databaselayer======" + s);
        Connection connection = null;
        final Vector<String> vector = new Vector<String>();
        Statement statement = null;
        ResultSet executeQuery = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            vector.addElement("0");
            vector.addElement("Choose One");
            statement = connection.createStatement();
            executeQuery = statement.executeQuery(s);
            while (executeQuery.next()) {
                vector.addElement(executeQuery.getString(1));
                vector.addElement(executeQuery.getString(2));
            }
            executeQuery.close();
            statement.close();
            connection.close();
        }
        catch (SQLException ex2) {}
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    executeQuery.close();
                    statement.close();
                    connection.close();
                }
                catch (Exception ex3) {}
            }
        }
        return vector;
    }
    
    public static String[] getSyllabusFile(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        final String[] array = new String[2];
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select file_name, file_name1  from course_definition where course_id='" + s + "'");
            if (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
                array[1] = executeQuery.getString(2);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return array;
    }
    
    public static synchronized boolean CheckAssignmentSubmissionLastDate(final String s) {
        Statement statement = null;
        int int1 = -1;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("select datediff(submission_last_date,sysdate()) from assignment where assignment_id='" + s + "'");
            executeQuery = statement.executeQuery("select datediff(submission_last_date,sysdate()) from assignment where assignment_id='" + s + "'");
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            System.out.println("Error due to SQL exception IfSCOIdExists: " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println(" error due to java.lang.exception IfSCOIdExists");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return int1 >= 0;
    }
    
    public static String createUnit(final String s, final String s2, final String s3) {
        Statement statement = null;
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            final Statement statement2 = connection.createStatement();
            final Statement statement3 = connection.createStatement();
            statement.execute("insert into unit_details (unit_name) values('" + s + "')");
            final ResultSet executeQuery = statement2.executeQuery("select max(unit_id) from unit_details");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            statement3.execute("insert into unit_creation_details(unit_id,date_created,created_by,date_modified,modified_by,status,email_id,controll,self_regis,confirmation_reqd,confirmed_by) values ('" + string + "',sysdate(),'" + s2 + "',sysdate(),'" + s2 + "','Active','','" + s3 + "','F','F','')");
            statement3.execute("insert into home_page_type(unit_id,type) values ('" + string + "','Status')");
        }
        catch (SQLException ex) {
            System.out.println("Inside DataBaseLayer createUnit, Error due to SQL exception!");
            ex.getErrorCode();
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            System.out.println("Inside DataBaseLayer createUnit, exception!");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return string;
    }
    
    public static boolean insertCourse(final String s, final String s2, final String s3, final String s4, final String s5) {
        boolean b = true;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select * from " + s + " where unit_id ='" + s2 + "'");
            if (executeQuery.next()) {
                statement.execute("delete from " + s + " where unit_id = '" + s2 + "'");
            }
            final PreparedStatement prepareStatement = connection.prepareStatement("insert into " + s + " values(? ,? ,? ,?, ?, ?)");
            prepareStatement.setString(1, s2);
            final File file = new File(s3);
            final FileInputStream fileInputStream = new FileInputStream(file);
            prepareStatement.setAsciiStream(2, fileInputStream, (int)file.length());
            prepareStatement.setString(3, s4);
            prepareStatement.setString(4, s5);
            prepareStatement.setString(5, file.getName());
            prepareStatement.setLong(6, file.length());
            prepareStatement.execute();
            prepareStatement.close();
            fileInputStream.close();
            executeQuery.close();
            statement.close();
        }
        catch (Exception ex) {
            b = false;
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return b;
    }
    
    public static boolean insertContent(final String s, final String s2, final String s3, final String s4) {
        boolean b = true;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select * from " + s + " where unit_id = '" + s3 + "' and file_name = '" + s2 + "'");
            if (executeQuery.next()) {
                statement.execute("delete from " + s + " where unit_id = '" + s3 + "' and file_name = '" + s2 + "'");
            }
            executeQuery.close();
            statement.close();
            final Statement statement2 = connection.createStatement();
            statement2.executeUpdate("insert into " + s + " values('" + s3 + "','" + s2 + "',sysdate(),'" + s4 + "')");
            executeQuery.close();
            statement2.close();
        }
        catch (Exception ex) {
            b = false;
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return b;
    }
    
    public static String getUnitName(final String s) {
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select unit_name from unit_details where unit_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            statement.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return string;
    }
    
    public static String[] getTopicIdStatus(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        final String[] array = new String[6];
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            System.out.println("select Course_syllabus,Course_schedule,Course_resource,Course_announcemnt,Course_assignmnt,Course_gradebook from course_component where Course_Id='" + s + "'");
            executeQuery = statement.executeQuery("select Course_syllabus,Course_schedule,Course_resource,Course_announcemnt,Course_assignmnt,Course_gradebook from course_component where Course_Id='" + s + "'");
            while (executeQuery.next()) {
                array[0] = executeQuery.getString(1);
                array[1] = executeQuery.getString(2);
                array[2] = executeQuery.getString(3);
                array[3] = executeQuery.getString(4);
                array[4] = executeQuery.getString(5);
                array[5] = executeQuery.getString(6);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        finally {
            try {
                if (connection != null) {
                    if (statement != null) {
                        statement.close();
                    }
                    if (executeQuery != null) {
                        executeQuery.close();
                    }
                    connection.close();
                }
            }
            catch (Exception ex3) {}
        }
        return array;
    }
    
    static {
        log = new SimpleLogger((Class<DataBaseLayer>)DataBaseLayer.class, true);
        DataBaseLayer.ds1 = CoreAdminInitHostInfo.ds1;
    }
}
