package NoteBook;

import org.grlea.log.*;
import javax.sql.*;
import java.util.*;
import java.sql.*;
import oracle.xml.parser.v2.*;
import java.io.*;
import comv2.aunwesha.param.*;

public class DataBaseLayer
{
    public static final SimpleLogger log;
    public static DataSource ds;
    
    public static Vector<String[]> getCourseDetails(final String student_id) {
        final Vector<String[]> vcourse = new Vector<String[]>();
        Connection oConn = null;
        Statement statement = null;
        ResultSet resultset = null;
        Statement statement2 = null;
        ResultSet resultset2 = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            oConn.setAutoCommit(false);
            final String sql = "select student_id,unit_id,access_allowed_till,total_access_time,date_registration,registered_by from unit_student_association where student_id='" + student_id + "'";
            DataBaseLayer.log.debug(sql);
            statement = oConn.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                final String[] str = new String[2];
                final String strcourse_id = resultset.getString(2);
                System.out.println("strcourse_id=== " + strcourse_id);
                final String stuid = resultset.getString(1);
                System.out.println("stuid=== " + stuid);
                statement2 = oConn.createStatement();
                final String qry = "select unit_name from unit_details where unit_id='" + strcourse_id + "'";
                DataBaseLayer.log.debug(qry);
                resultset2 = statement2.executeQuery(qry);
                resultset2.next();
                final String strcourse_name = resultset2.getString(1);
                str[0] = strcourse_id;
                str[1] = strcourse_name;
                vcourse.addElement(str);
                resultset2.close();
                statement2.close();
            }
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.error("SQLException in etCourseDetails " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.error("Exception in getCourseDetails " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    resultset.close();
                    statement.close();
                    oConn.commit();
                    oConn.setAutoCommit(true);
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vcourse;
    }
    
    public static Vector<String[]> getUnitDetailsFromCourse(final String student_id) {
        final Vector<String[]> vcourse = new Vector<String[]>();
        Connection oConn = null;
        Statement statement = null;
        ResultSet resultset = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            oConn.setAutoCommit(false);
            final String sql = "select distinct a.file_name,b.unit_id,b.unit_name from topic_material_details a,unit_details b,usergroup_course_registration c where a.file_name=b.unit_id and a.course_id=c.course_id and c.student_id='" + student_id + "' and b.unit_id not in(select unit_id from unit_student_association where student_id='" + student_id + "') and b.unit_id not in(select unit_id from unit_group_association where student_id='" + student_id + "')";
            DataBaseLayer.log.debug(sql);
            statement = oConn.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                final String[] str = new String[2];
                final String strcourse_id = resultset.getString(2);
                System.out.println("strcourse_id=== " + strcourse_id);
                final String strcourse_name = resultset.getString(3);
                str[0] = strcourse_id;
                str[1] = strcourse_name;
                vcourse.addElement(str);
            }
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.error("SQLException in etCourseDetails " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.error("Exception in getCourseDetails " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    if (resultset != null) {
                        resultset.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    oConn.commit();
                    oConn.setAutoCommit(true);
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vcourse;
    }
    
    public static Vector getStudentIdName() {
        final Vector vstudent = new Vector();
        Connection oConn = null;
        Statement statement = null;
        ResultSet resultset = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final String sql = "SELECT student_id,concat(first_name,' ',middle_name,' ',sname) FROM student_details";
            DataBaseLayer.log.debug(sql);
            statement = oConn.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                final String[] str = new String[2];
                final String id = resultset.getString(1);
                final String name = resultset.getString(2);
                str[0] = id;
                str[1] = name;
                vstudent.addElement(str);
            }
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.error("SQLException in getStudentIdName " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.error("Exception in getStudentIdName " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    resultset.close();
                    statement.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vstudent;
    }
    
    public static Vector getSharedCourseDetails(final String student_id) {
        final Vector vcourse = new Vector();
        Connection oConn = null;
        Statement statement = null;
        ResultSet resultset = null;
        Statement statement2 = null;
        ResultSet resultset2 = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final String sql = "select distinct(unit_id) from user_notes_details where student_id='" + student_id + "' and note='publish'";
            System.out.println("======shared note sql==============" + sql);
            statement = oConn.createStatement();
            statement2 = oConn.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                final String[] str = new String[2];
                final String strcourse_id = resultset.getString(1);
                System.out.println("===strcourse_id============" + strcourse_id);
                final String qry = "select unit_name from unit_details where unit_id='" + strcourse_id + "'";
                System.out.println(qry);
                resultset2 = statement2.executeQuery(qry);
                while (resultset2.next()) {
                    final String strcourse_name = resultset2.getString(1);
                    str[0] = strcourse_id;
                    str[1] = strcourse_name;
                }
                vcourse.addElement(str);
            }
            statement2.close();
            if (resultset2 != null) {
                resultset2.close();
            }
            System.out.println("=vcourse=====getSharedCourseDetails======1==" + vcourse);
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
                    resultset.close();
                    statement.close();
                    oConn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("=vcourse=====getSharedCourseDetails========" + vcourse);
        return vcourse;
    }
    
    public static synchronized Vector<String> getSharedSubjectnotepublish(final String stdID, final String courseID, final String identifier) {
        Connection oConn = null;
        final Vector<String> vNoteid = new Vector<String>(3, 3);
        Statement oStmt = null;
        ResultSet oRset = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final String sql = "select subject,date_format(note_date,\"%Y-%m-%d\"),note_details,attachment,note_id from user_notes_details where student_id='" + stdID + "' and unit_id='" + courseID + "' and sco_id='" + identifier + "' ";
            DataBaseLayer.log.debug(sql);
            oStmt = oConn.createStatement();
            oRset = oStmt.executeQuery(sql);
            while (oRset.next()) {
                final String strstid = oRset.getString(1);
                final String strfname = oRset.getString(2);
                final String strmname = oRset.getString(3);
                final String strlname = oRset.getString(4);
                final int strnoteid = oRset.getInt(5);
                vNoteid.addElement(strstid);
                vNoteid.addElement(strfname);
                vNoteid.addElement(strmname);
                vNoteid.addElement(strlname);
                vNoteid.addElement(""+strnoteid);
            }
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.error("SQLException in getSharedSubjectnotepublish " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.error("Exception in getSharedSubjectnotepublish " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oRset.close();
                    oStmt.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vNoteid;
    }
    
    public static synchronized Vector<String> getPublishedNotes(final String stdID, final String courseID, final String identifier) {
        Connection oConn = null;
        final Vector<String> vNoteid = new Vector<String>(3, 3);
        Statement oStmt = null;
        ResultSet oRset = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final String sql = "select subject,date_format(note_date,\"%Y-%m-%d\"),note_details,attachment,note_id from user_notes_details where student_id='" + stdID + "' " + "and unit_id='" + courseID + "' and sco_id='" + identifier + "'  and note='publish'";
            DataBaseLayer.log.debug(sql);
            oStmt = oConn.createStatement();
            oRset = oStmt.executeQuery(sql);
            while (oRset.next()) {
                final String strstid = oRset.getString(1);
                final String strfname = oRset.getString(2);
                final String strmname = oRset.getString(3);
                final String strlname = oRset.getString(4);
                final int strnoteid = oRset.getInt(5);
                vNoteid.addElement(strstid);
                vNoteid.addElement(strfname);
                vNoteid.addElement(strmname);
                vNoteid.addElement(strlname);
                vNoteid.addElement(""+strnoteid);
            }
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.error("SQLException in getPublishedNotes " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.error("Exception in getPublishedNotes " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oRset.close();
                    oStmt.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vNoteid;
    }
    
    public static XMLDocument parse(final String s, final String s1) {
        final Object obj = null;
        XMLDocument xmldocument = null;
        Connection oConn = null;
        Statement statement = null;
        ResultSet resultset = null;
        System.out.println("======courseid===parse==2===" + s);
        try {
            oConn = DataBaseLayer.ds.getConnection();
            statement = oConn.createStatement();
            final String s2 = s1.toLowerCase();
            final String sql = "select csf from " + s2 + " where unit_id='" + s + "'";
            DataBaseLayer.log.debug(sql);
            resultset = statement.executeQuery(sql);
            if (resultset.next()) {
                final InputStream inputstream = resultset.getAsciiStream(1);
                final BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
                final DOMParser domparser = new DOMParser();
                domparser.setValidationMode(false);
                domparser.parse((Reader)bufferedreader);
                xmldocument = domparser.getDocument();
                bufferedreader.close();
            }
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
                    resultset.close();
                    statement.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return xmldocument;
    }
    
    public static int addNoteDetails(final String course_id, final String student_id, final String title, final String date1, final String subject, final String notedetails, final String strattach, final String identifier, final String note) {
        Connection oConn = null;
        Statement statement = null;
        ResultSet rs = null;
        int autoIncKeyFromApi = -1;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final String sql = "Insert into user_notes_details(unit_id, student_id, unit_title,note_details,note_date,note,attachment,subject,sco_id) values ('" + course_id + "','" + student_id + "','" + title + "',' " + notedetails + "',sysdate(),'" + note + "','" + strattach + "','" + subject + "','" + identifier + "')";
            statement = oConn.createStatement();
            DataBaseLayer.log.debug(sql);
            statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                autoIncKeyFromApi = rs.getInt(1);
            }
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in addNoteDetails() " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug("General exception in addNoteDetails() " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    statement.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return autoIncKeyFromApi;
    }
    
    public static void removeNoteIdDetails(final String course_id, final String student_id, final String note_id) {
        Connection oConn = null;
        Statement statement = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            statement = oConn.createStatement();
            final String sql = "delete from  user_notes_details where student_id='" + student_id + "' and unit_id='" + course_id + "' and note_id='" + note_id + "'";
            DataBaseLayer.log.debug(sql);
            final boolean flag = statement.execute(sql);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in removeNoteIdDetails() " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug("General exception in removeNoteIdDetails() " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    statement.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static void modifyNotedetails(final String course_id, final String student_id, final String subject, final String notedetails, final String strattach, final String note_id) {
        Connection oConn = null;
        Statement statement = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            statement = oConn.createStatement();
            final String sql = "update user_notes_details set note_details='" + notedetails + "',note_date=sysdate(),attachment='" + strattach + "',subject='" + subject + "' where student_id='" + student_id + "' and unit_id='" + course_id + "' and note_id='" + note_id + "'";
            DataBaseLayer.log.debug(sql);
            final boolean flag = statement.execute(sql);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in modifyNotedetails()");
            sqlexception.printStackTrace();
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug("General exception in modifyNotedetails()");
            exception.printStackTrace();
            DataBaseLayer.log.debug("The message - " + exception.toString());
        }
        finally {
            if (oConn != null) {
                try {
                    statement.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static void AddpublishNote(final String course_id, final String student_id, final String note_id, final String identifier, final String publish) {
        Connection oConn = null;
        Statement statement = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            statement = oConn.createStatement();
            final String sql = "update user_notes_details set note='" + publish + "' where student_id='" + student_id + "' and unit_id='" + course_id + "' and note_id='" + note_id + "' and sco_id='" + identifier + "'";
            System.out.println(sql);
            final boolean flag = statement.execute(sql);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in AddpublishNote() " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug("General exception in AddpublishNote() " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    statement.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static boolean publishNote(final String unitid, final String userId, final String note_id) {
        boolean yes = false;
        Connection oConn = null;
        Statement statement = null;
        ResultSet resultset = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            statement = oConn.createStatement();
            final String sql = "select note from user_notes_details where student_id='" + userId + "' and unit_id='" + unitid + "' and note_id='" + note_id + "'";
            DataBaseLayer.log.debug(sql);
            resultset = statement.executeQuery(sql);
            if (resultset.next()) {
                final String str = resultset.getString(1);
                if (str.equals("publish")) {
                    yes = true;
                }
            }
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in publishNote() " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug("General exception in publishNote() " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    resultset.close();
                    statement.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return yes;
    }
    
    public static boolean checkCourse(final String unitid, final String userId) {
        boolean yes = false;
        Connection oConn = null;
        Statement statement = null;
        ResultSet resultset = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            statement = oConn.createStatement();
            final String sql = "select student_id from user_notes_details where student_id='" + userId + "' and unit_id='" + unitid + "'";
            DataBaseLayer.log.debug(sql);
            resultset = statement.executeQuery(sql);
            if (resultset.next()) {
                yes = true;
            }
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in checkCourse() " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug("General exception in checkCourse() " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    resultset.close();
                    statement.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return yes;
    }
    
    public static synchronized Vector getNoteSubject(final String stdID, final String courseID, final String title) {
        final Vector vNoteid = new Vector(3, 3);
        Connection oConn = null;
        Statement oStmt = null;
        ResultSet oRset = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            oStmt = oConn.createStatement();
            final String sql = "select subject,date_format(note_date,\"%Y-%m-%d\"),note_details,attachment,note_id from user_notes_details where student_id='" + stdID + "' and unit_id='" + courseID + "' and unit_title='" + title + "' ";
            DataBaseLayer.log.debug(sql);
            oRset = oStmt.executeQuery(sql);
            while (oRset.next()) {
                final String strstid = oRset.getString(1);
                final String strfname = oRset.getString(2);
                final String strmname = oRset.getString(3);
                final String strlname = oRset.getString(4);
                final int strnoteid = oRset.getInt(5);
                vNoteid.addElement(strstid);
                vNoteid.addElement(strfname);
                vNoteid.addElement(strmname);
                vNoteid.addElement(strlname);
                vNoteid.addElement(strnoteid);
            }
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in getNoteSubject() " + sqlexception);
        }
        finally {
            if (oConn != null) {
                try {
                    oRset.close();
                    oStmt.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vNoteid;
    }
    
    public static synchronized Vector getsubjectinnoteadmin(final String notecourse_id, final String strtitle, final String stdID, final String note_id) {
        final Vector vNoteid = new Vector(3, 3);
        Connection oConn = null;
        Statement oStmt = null;
        ResultSet oRset = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            oStmt = oConn.createStatement();
            final String sql = "select subject,date_format(note_date,\"%Y-%m-%d\"),note_details,attachment,note_id from user_notes_details where unit_id='" + notecourse_id + "' and unit_title='" + strtitle + "' and student_id='" + stdID + "' and note_id='" + note_id + "' ";
            DataBaseLayer.log.debug(sql);
            oRset = oStmt.executeQuery(sql);
            while (oRset.next()) {
                final String strstid = oRset.getString(1);
                final String strfname = oRset.getString(2);
                final String strmname = oRset.getString(3);
                final String strlname = oRset.getString(4);
                final int strnoteid = oRset.getInt(5);
                vNoteid.addElement(strstid);
                vNoteid.addElement(strfname);
                vNoteid.addElement(strmname);
                vNoteid.addElement(strlname);
                vNoteid.addElement(strnoteid);
            }
            oStmt.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in getsubjectinnoteadmin() " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug("General exception in getsubjectinnoteadmin() " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oRset.close();
                    oStmt.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vNoteid;
    }
    
    public static synchronized String getUser(final String note_id) {
        String user = "";
        Connection oConn = null;
        Statement oStmt = null;
        ResultSet oRset = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            oStmt = oConn.createStatement();
            final String sql = "SELECT student_id FROM user_notes_details WHERE note_id='" + note_id + "' ";
            DataBaseLayer.log.debug(sql);
            oRset = oStmt.executeQuery(sql);
            while (oRset.next()) {
                user = oRset.getString(1);
            }
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("Exception in getUser() " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug("General exception in getUser() " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oRset.close();
                    oStmt.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return user;
    }
    
    public static Vector getCourseDetails1(final String student_id) {
        final Vector vstudent_id = new Vector(3, 3);
        final Vector vcourse = new Vector();
        Connection oConn = null;
        Statement statement = null;
        ResultSet resultset = null;
        Statement statement2 = null;
        ResultSet resultset2 = null;
        Statement statement3 = null;
        ResultSet resultset3 = null;
        Statement statement4 = null;
        ResultSet resultset4 = null;
        Statement statement5 = null;
        ResultSet resultset5 = null;
        Statement statement6 = null;
        ResultSet resultset6 = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            statement = oConn.createStatement();
            resultset = statement.executeQuery("select * from unit_student_association where student_id='" + student_id + "'");
            while (resultset.next()) {
                final Vector vstudent_id2 = new Vector(3, 3);
                final String strcourse_id = resultset.getString(2);
                statement2 = oConn.createStatement();
                resultset2 = statement2.executeQuery("select unit_name from unit_details where unit_id='" + strcourse_id + "'");
                resultset2.next();
                final String strcourse_name = resultset2.getString(1);
                vcourse.addElement(strcourse_id);
                vstudent_id2.addElement(strcourse_id);
                vstudent_id2.addElement(strcourse_name);
                vstudent_id.addElement(vstudent_id2);
                statement2.close();
                resultset2.close();
            }
            statement3 = oConn.createStatement();
            resultset3 = statement3.executeQuery("select b.student_id, a.unit_id, a.access_allowed_till, a.total_access_time, a.date_registration, a.registered_by from unit_group_association a, student_group_association b where a.group_id=b.group_id and b.student_id='" + student_id + "'");
            while (resultset3.next()) {
                final Vector vstudent_id2 = new Vector(3, 3);
                final String strcourse_id = resultset3.getString(2);
                statement4 = oConn.createStatement();
                resultset4 = statement4.executeQuery("select unit_name from unit_details where unit_id='" + strcourse_id + "'");
                resultset4.next();
                final String strcourse_name = resultset4.getString(1);
                if (!vcourse.contains(strcourse_id)) {
                    vcourse.addElement(strcourse_id);
                    vstudent_id2.addElement(strcourse_id);
                    vstudent_id2.addElement(strcourse_name);
                    vstudent_id.addElement(vstudent_id2);
                }
                statement4.close();
                resultset4.close();
            }
            statement5 = oConn.createStatement();
            System.out.println("Select b.student_id,a.file_name from topic_material_details a,usergroup_course_registration b where a.course_id=b.course_id and a.file_name not in (select unit_id from unit_student_association where student_id='" + student_id + "') and a.file_name not in (select ua.unit_id from unit_group_association ua, student_group_association sa where ua.group_id=sa.group_id and sa.student_id='" + student_id + "') and b.student_id='" + student_id + "'");
            resultset5 = statement5.executeQuery("Select b.student_id,a.file_name from topic_material_details a,usergroup_course_registration b where a.course_id=b.course_id and a.file_name not in (select unit_id from unit_student_association where student_id='" + student_id + "') and a.file_name not in (select ua.unit_id from unit_group_association ua, student_group_association sa where ua.group_id=sa.group_id and sa.student_id='" + student_id + "') and b.student_id='" + student_id + "'");
            while (resultset5.next()) {
                final Vector vstudent_id2 = new Vector(3, 3);
                final String strcourse_id = resultset5.getString(2);
                statement6 = oConn.createStatement();
                System.out.println("select unit_name from unit_details where unit_id='" + strcourse_id + "'");
                resultset6 = statement6.executeQuery("select unit_name from unit_details where unit_id='" + strcourse_id + "'");
                resultset6.next();
                final String strcourse_name = resultset6.getString(1);
                vcourse.addElement(strcourse_id);
                vstudent_id2.addElement(strcourse_id);
                vstudent_id2.addElement(strcourse_name);
                vstudent_id.addElement(vstudent_id2);
                System.out.println("vstudent_id==" + vstudent_id);
                statement6.close();
                resultset6.close();
            }
            statement5.close();
            resultset5.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.entry("getCourseDetails()");
            DataBaseLayer.log.error("Exception in getCourseDetails()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            DataBaseLayer.log.entry("getCourseDetails()");
            DataBaseLayer.log.error("Exception in getCourseDetails()");
            System.out.println("The Error Message - " + exception.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    statement.close();
                    resultset.close();
                    statement3.close();
                    resultset3.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vstudent_id;
    }
    
    public static XMLDocument parse1(final String s, final String s1) {
        final Object obj = null;
        XMLDocument xmldocument = null;
        Connection oConn = null;
        Statement statement = null;
        ResultSet resultset = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            statement = oConn.createStatement();
            final String s2 = s1.toLowerCase();
            System.out.println("select csf from " + s2 + " where unit_id='" + s + "'");
            resultset = statement.executeQuery("select csf from " + s2 + " where unit_id='" + s + "'");
            if (resultset.next()) {
                final InputStream inputstream = resultset.getAsciiStream(1);
                final BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
                final DOMParser domparser = new DOMParser();
                domparser.setValidationMode(false);
                domparser.parse((Reader)bufferedreader);
                xmldocument = domparser.getDocument();
                resultset.close();
                statement.close();
                bufferedreader.close();
            }
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.entry("parse()");
            DataBaseLayer.log.error(" error due to SQL exception inside NewDataBaseLayer.XMLDocument parse()");
            final int i = sqlexception.getErrorCode();
            final String s3 = sqlexception.getMessage();
            sqlexception.printStackTrace();
        }
        catch (Exception exception) {
            DataBaseLayer.log.entry("parse()");
            DataBaseLayer.log.error(" error due to java.lang.exception");
            exception.printStackTrace();
            DataBaseLayer.log.error(" printStack is :: " + exception.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    resultset.close();
                    statement.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return xmldocument;
    }
    
    public static String getUnitName(final String unitid) {
        Statement oStmt = null;
        ResultSet oRset = null;
        String strstid = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            oStmt = oConn.createStatement();
            oRset = oStmt.executeQuery("select unit_name from unit_details where unit_id='" + unitid + "'");
            while (oRset.next()) {
                strstid = oRset.getString(1);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if (oConn != null) {
                try {
                    oStmt.close();
                    oRset.close();
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return strstid;
    }
    
    public static boolean checkCourse1(final String unitid, final String userId) {
        boolean yes = false;
        Connection oConn = null;
        Statement statement = null;
        ResultSet resultset = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            statement = oConn.createStatement();
            resultset = statement.executeQuery("select student_id from user_notes_details where student_id='" + userId + "' and unit_id='" + unitid + "'");
            if (resultset.next()) {
                yes = true;
            }
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
                    resultset.close();
                    statement.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return yes;
    }
    
    public static synchronized Vector<String> getNoteSubject1(final String stdID, final String courseID, final String title) {
        final Vector<String> vNoteid = new Vector<String>(3, 3);
        Connection oConn = null;
        Statement oStmt = null;
        ResultSet oRset = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            oStmt = oConn.createStatement();
            oRset = oStmt.executeQuery("select subject,date_format(note_date,\"%Y-%m-%d\"),note_details,attachment,note_id from user_notes_details where student_id='" + stdID + "' and unit_id='" + courseID + "' and unit_title='" + title + "' ");
            while (oRset.next()) {
                final String strstid = oRset.getString(1);
                final String strfname = oRset.getString(2);
                final String strmname = oRset.getString(3);
                final String strlname = oRset.getString(4);
                final int strnoteid = oRset.getInt(5);
                vNoteid.addElement(strstid);
                vNoteid.addElement(strfname);
                vNoteid.addElement(strmname);
                vNoteid.addElement(strlname);
                vNoteid.addElement(""+strnoteid);
            }
            oStmt.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return vNoteid;
        }
        finally {
            if (oConn != null) {
                try {
                    oStmt.close();
                    oRset.close();
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vNoteid;
    }
    
    public static synchronized Vector<String> getsubjectinnoteadmin1(final String notecourse_id, final String strtitle, final String stdID, final String note_id) {
        final Vector<String> vNoteid = new Vector<String>(3, 3);
        Connection oConn = null;
        Statement oStmt = null;
        ResultSet oRset = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            oStmt = oConn.createStatement();
            oRset = oStmt.executeQuery("select subject,date_format(note_date,\"%Y-%m-%d\"),note_details,attachment,note_id from user_notes_details where unit_id='" + notecourse_id + "' and unit_title='" + strtitle + "' and student_id='" + stdID + "' and note_id='" + note_id + "' ");
            while (oRset.next()) {
                final String strstid = oRset.getString(1);
                final String strfname = oRset.getString(2);
                final String strmname = oRset.getString(3);
                final String strlname = oRset.getString(4);
                final int strnoteid = oRset.getInt(5);
                vNoteid.addElement(strstid);
                vNoteid.addElement(strfname);
                vNoteid.addElement(strmname);
                vNoteid.addElement(strlname);
                vNoteid.addElement(""+strnoteid);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return vNoteid;
        }
        finally {
            if (oConn != null) {
                try {
                    oStmt.close();
                    oRset.close();
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vNoteid;
    }
    
    public static void removeNoteIdDetails1(final String course_id, final String student_id, final String note_id) {
        Connection oConn = null;
        Statement statement = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            oConn.setAutoCommit(false);
            statement = oConn.createStatement();
            final boolean flag = statement.execute("delete from  user_notes_details where note_id='" + note_id + "' and unit_id='" + course_id + "'");
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception ");
            sqlexception.printStackTrace();
        }
        catch (Exception exception) {
            System.out.println("General exception ");
            exception.printStackTrace();
            System.out.println("The message - " + exception.toString());
        }
        finally {
            if (oConn != null) {
                try {
                    statement.close();
                    oConn.commit();
                    oConn.setAutoCommit(true);
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static void AddpublishNote1(final String course_id, final String student_id, final String note_id, final String publish) {
        Connection oConn = null;
        Statement statement = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            statement = oConn.createStatement();
            final boolean flag = statement.execute("update user_notes_details set note='" + publish + "' where student_id='" + student_id + "' and unit_id='" + course_id + "' and note_id='" + note_id + "'");
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception )");
            sqlexception.printStackTrace();
        }
        catch (Exception exception) {
            System.out.println("General exception ");
            exception.printStackTrace();
            System.out.println("The message - " + exception.toString());
        }
        finally {
            if (oConn != null) {
                try {
                    statement.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static Vector<String[]> getCoursegroupDetails(final String student_id) {
        final Vector<String[]> vcourse = new Vector<String[]>();
        Connection oConn = null;
        Statement statement = null;
        ResultSet resultset = null;
        Statement statement2 = null;
        ResultSet resultset2 = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final String sql = "select b.student_id, a.unit_id, a.access_allowed_till, a.total_access_time, a.date_registration, a.registered_by from unit_group_association a, student_group_association b where a.group_id=b.group_id and b.student_id='" + student_id + "'";
            System.out.println("=getCoursegroupDetails====" + sql);
            System.out.println("==============1========");
            statement = oConn.createStatement();
            System.out.println("==============2========");
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                final String[] str = new String[2];
                final String strcourse_id = resultset.getString(2);
                System.out.println("strcourse_id==getCoursegroupDetails=== " + strcourse_id);
                final String stuid = resultset.getString(1);
                System.out.println("stuid===getCoursegroupDetails== " + stuid);
                statement2 = oConn.createStatement();
                final String qry = "select unit_name from unit_details where unit_id='" + strcourse_id + "'";
                resultset2 = statement2.executeQuery(qry);
                resultset2.next();
                final String strcourse_name = resultset2.getString(1);
                str[0] = strcourse_id;
                str[1] = strcourse_name;
                vcourse.addElement(str);
                statement2.close();
                resultset2.close();
            }
            System.out.println("==============3========");
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.error("SQLException in etCourseDetails " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.error("Exception in getCourseDetails " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    statement.close();
                    resultset.close();
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vcourse;
    }
    
    public static Vector<Vector<String>> getUnitDetailsFromCourse1(final String student_id) {
        final Vector<Vector<String>> vcourse = new Vector<Vector<String>>();
        Connection oConn = null;
        Statement statement = null;
        ResultSet resultset = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            oConn.setAutoCommit(false);
//            final String sql = "select distinct a.file_name,b.unit_id,b.unit_name from topic_material_details a,unit_details b,usergroup_course_registration c where a.file_name=b.unit_id and a.course_id=c.course_id and c.student_id='" + student_id + "' and b.unit_id not in(select unit_id from unit_student_association where student_id='" + student_id + "') and b.unit_id not in(select unit_id from unit_group_association where student_id='" + student_id + "')";
            final String sql = "select a.unit_id, b.unit_name from unit_student_association a, unit_details b where a.student_id = '" + student_id + "' and a.access_allowed_till >= NOW() and a.unit_id = b.unit_id";
            DataBaseLayer.log.debug(sql);
            statement = oConn.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                final Vector<String> vstudent_id1 = new Vector<String>(3, 3);
                final String strcourse_id = resultset.getString(1);
                System.out.println("strcourse_id=== " + strcourse_id);
                final String strcourse_name = resultset.getString(2);
                vstudent_id1.addElement(strcourse_id);
                vstudent_id1.addElement(strcourse_name);
                vcourse.addElement(vstudent_id1);
            }
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.error("SQLException in etCourseDetails " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.error("Exception in getCourseDetails " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    if (resultset != null) {
                        resultset.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    oConn.commit();
                    oConn.setAutoCommit(true);
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vcourse;
    }
    
    static {
        log = new SimpleLogger((Class)DataBaseLayer.class, true);
        DataBaseLayer.ds = CoreAdminInitHostInfo.ds1;
    }

	

        
}
