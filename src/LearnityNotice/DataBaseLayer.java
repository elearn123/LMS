package LearnityNotice;

import org.grlea.log.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;
import comv2.aunwesha.param.*;

public class DataBaseLayer
{
    public static final SimpleLogger log;
    public static DataSource ds;
    
    public static int validate(final String s, final String s1) throws SQLException {
        final Object obj = null;
        final Object obj2 = null;
        byte byte0 = 0;
        final Connection oConn = DataBaseLayer.ds.getConnection();
        try {
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select count(*) from student_password where student_id='" + s + "'");
            resultset.next();
            if (resultset.getInt(1) > 0) {
                final Statement statement2 = oConn.createStatement();
                final ResultSet resultset2 = statement2.executeQuery("select password from student_password where student_id ='" + s + "'");
                resultset2.next();
                final String s2 = resultset2.getString(1);
                if (s2.equals(s1)) {
                    byte0 = 1;
                }
                else {
                    byte0 = -2;
                }
                resultset2.close();
                statement2.close();
            }
            else {
                byte0 = -1;
            }
            resultset.close();
            statement.close();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
            DataBaseLayer.log.debug(" error due to SQL exception inside NewDataBaseLayer.validate()");
            DataBaseLayer.log.debug("Error due to SQLException , the error code - " + sqlexception.getErrorCode());
            DataBaseLayer.log.debug("Error message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug(" error due to java.lang.exception");
            exception.printStackTrace();
            DataBaseLayer.log.debug(" printStack is :: " + exception.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return byte0;
    }
    
    public static String getStudentName(final String s) {
        final String s2 = null;
        String s3 = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final Statement statement1 = oConn.createStatement();
            final ResultSet resultset1 = statement1.executeQuery("select account_status , concat(first_name,' ',middle_name,' ',sname) from student_details where student_id ='" + s + "'");
            if (resultset1.next()) {
                final String s4 = resultset1.getString(1);
                s3 = resultset1.getString(2);
            }
            oConn.close();
        }
        catch (NumberFormatException numberformatexception) {
            DataBaseLayer.log.debug("NumberFormatException excepiton in DataBaseLayer.getStudentName()");
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("SQL error inside DataBaseLayer.getStudentName()");
            DataBaseLayer.log.debug("sql exception" + sqlexception.toString());
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug("general exception in DataBaseLayer.getStudentName()" + exception.toString());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return s3;
    }
    
    public static String returnnoticename(final String notice_id) {
        String strHeading = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select  heading from bulletin_board where notice_id = '" + notice_id + "' ");
            System.out.println("SQL IN NOTICE---------->select heading from bulletin_board where notice_id = '" + notice_id + "'");
            while (resultset.next()) {
                strHeading = resultset.getString(1);
            }
            statement.close();
            resultset.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug(" Excemtion in returnnoticename(String s ) : " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug(" Excemtion in returnnoticename(String s ) : " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return strHeading;
    }
    
    public static String returnnoticebody(final String notice_id) {
        String strbody = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select  body from bulletin_board where notice_id = '" + notice_id + "' ");
            while (resultset.next()) {
                strbody = resultset.getString(1);
            }
            System.out.println("NOTICE BODY--------db----" + strbody);
            statement.close();
            resultset.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug(" Excemtion in returnnoticebody(String s ) : " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug(" Excemtion in returnnoticebody(String s ) : " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return strbody;
    }
    
    public static String returnnoticepostedby(final String notice_id) {
        String strstudentname = "";
        boolean flag = false;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final Statement statement = oConn.createStatement();
            System.out.println("--------------select concat(first_name,' ',middle_name,' ',sname) from student_details a,bulletin_board b where  a.student_id=b.student_id and b.notice_id = '" + notice_id + "'");
            final ResultSet resultset = statement.executeQuery("select concat(first_name,' ',middle_name,' ',sname) from student_details a,bulletin_board b where  a.student_id=b.student_id and b.notice_id = '" + notice_id + "'");
            while (resultset.next()) {
                strstudentname = resultset.getString(1);
                flag = true;
            }
            if (!flag) {
                System.out.println("----------------select concat(a.user_first_name,' ',a.user_middle_name,' ',a.user_last_name) from administrator_details a,bulletin_board b where a.user_id =b.student_id and b.notice_id = '" + notice_id + "'");
                final ResultSet rs = statement.executeQuery("select concat(a.user_first_name,' ',a.user_middle_name,' ',a.user_last_name) from administrator_details a,bulletin_board b where a.user_id =b.student_id and b.notice_id = '" + notice_id + "'");
                while (rs.next()) {
                    strstudentname = rs.getString(1);
                }
            }
            System.out.println("strstudentname-----------db----" + strstudentname);
            statement.close();
            resultset.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug(" Excemtion in returnnoticebody(String s ) : " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug(" Excemtion in returnnoticebody(String s ) : " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return strstudentname;
    }
    
    public static String returnnoticepostedon(final String notice_id) {
        String strdate = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select  posted_on from bulletin_board where notice_id = '" + notice_id + "' ");
            while (resultset.next()) {
                strdate = resultset.getString(1);
            }
            statement.close();
            resultset.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            System.out.println(" returnnoticepostedon(): " + sqlexception);
        }
        catch (Exception exception) {
            System.out.println(" returnnoticepostedon(): " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return strdate;
    }
    
    public static String returnAttachName(final String notice_id) {
        String attachname = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select  attachments from bulletin_board where notice_id = '" + notice_id + "' ");
            while (resultset.next()) {
                attachname = resultset.getString(1);
            }
            statement.close();
            resultset.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            System.out.println(" returnAttachName(): " + sqlexception);
            sqlexception.printStackTrace();
        }
        catch (Exception exception) {
            System.out.println(" returnnoticepostedon(): " + exception);
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
        return attachname;
    }
    
    public static String returnattachment(final String notice_id) {
        String strattachments = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select  attachments from bulletin_board where notice_id = '" + notice_id + "' ");
            while (resultset.next()) {
                strattachments = resultset.getString(1);
            }
            statement.close();
            resultset.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            System.out.println(" returnattachment(): " + sqlexception);
        }
        catch (Exception exception) {
            System.out.println(" returnattachment(): " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return strattachments;
    }
    
    public static void addNotice(final String noticeheading, final String noticebody, final String user_id, final String attachname, final String group) {
        Connection oConn = null;
        try {
            System.out.println("------------111111111111111-----------------------------noticeheading,noticebody,user_id,attachname" + noticeheading + " , " + noticebody + " , " + user_id + " , " + attachname);
            oConn = DataBaseLayer.ds.getConnection();
            final PreparedStatement pstmt1 = oConn.prepareStatement("insert into bulletin_board(student_id,heading,body,attachments,group_id,posted_on) values(?,?,?,?,?,sysdate())");
            pstmt1.setString(1, user_id);
            pstmt1.setString(2, noticeheading);
            pstmt1.setString(3, noticebody);
            pstmt1.setString(4, attachname);
            pstmt1.setString(5, group);
            pstmt1.executeUpdate();
            pstmt1.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("exception in addNotice()" + sqlexception.getMessage());
        }
        catch (Exception exception) {
            System.out.println("exception in addNotice()" + exception.getMessage());
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
    
    public static Vector<String> getNoticeInfo(final String user_id) {
        final Vector<String> vector = new Vector<String>();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select  a.student_id , a.heading, a.attachments,a.body,a.posted_on,a.notice_id from bulletin_board a where  a.group_id='0' and a.status = 'Active'  order by a.posted_on DESC");
            while (resultset.next()) {
                DataBaseLayer.log.debug("att-11-----------------------" + resultset.getString(3));
                final String[] as = new String[6];
                final String att = resultset.getString(3);
                final String a1 = resultset.getString(1);
                final String a2 = resultset.getString(2);
                final String a3 = resultset.getString(4);
                final String a4 = resultset.getString(6);
                String a5 = "";
                DataBaseLayer.log.debug("att-------new----------------" + att);
                DataBaseLayer.log.debug("att-22-----------------------" + att);
                if (att == null) {
                    as[2] = "";
                    a5 = "";
                }
                else {
                    DataBaseLayer.log.debug("att-3333-----------------------" + att);
                    as[2] = att;
                    a5 = att;
                }
                final String s = resultset.getString(5);
                final Statement statement2 = oConn.createStatement();
                final ResultSet resultset2 = statement2.executeQuery("select date_format('" + s + "',\"%D %M %Y\")");
                resultset2.next();
                as[4] = resultset2.getString(1);
                final String a6 = resultset2.getString(1);
                vector.addElement(a1);
                vector.addElement(a2);
                vector.addElement(a5);
                vector.addElement(a3);
                vector.addElement(a6);
                vector.addElement(a4);
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("SQLException in NoticeEngineDataBaseLayer.getNoticeInfo()");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
            sqlexception.printStackTrace();
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug("Exception in NoticeEngineDataBaseLayer.getNoticeInfo()");
            DataBaseLayer.log.debug("The Error Message - " + exception.getMessage());
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
        return vector;
    }
    
    public static Vector<String> getNoticeInfo1(final String user_id) {
        final Vector<String> vector = new Vector<String>();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select  a.student_id , a.heading, a.attachments,a.body,a.posted_on,a.notice_id from bulletin_board a,student_group_association b where a.group_id=b.group_id and b.student_id='" + user_id + "' and a.status = 'Active'  order by a.posted_on DESC");
            while (resultset.next()) {
                DataBaseLayer.log.debug("att-11-----------------------" + resultset.getString(3));
                final String[] as = new String[6];
                final String att = resultset.getString(3);
                final String a1 = resultset.getString(1);
                final String a2 = resultset.getString(2);
                final String a3 = resultset.getString(4);
                final String a4 = resultset.getString(6);
                String a5 = "";
                DataBaseLayer.log.debug("att-------new----------------" + att);
                DataBaseLayer.log.debug("att-22-----------------------" + att);
                if (att == null) {
                    as[2] = "";
                    a5 = "";
                }
                else {
                    DataBaseLayer.log.debug("att-3333-----------------------" + att);
                    as[2] = att;
                    a5 = att;
                }
                final String s = resultset.getString(5);
                final Statement statement2 = oConn.createStatement();
                final ResultSet resultset2 = statement2.executeQuery("select date_format('" + s + "',\"%D %M %Y\")");
                resultset2.next();
                as[4] = resultset2.getString(1);
                final String a6 = resultset2.getString(1);
                vector.addElement(a1);
                vector.addElement(a2);
                vector.addElement(a5);
                vector.addElement(a3);
                vector.addElement(a6);
                vector.addElement(a4);
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("SQLException in NoticeEngineDataBaseLayer.getNoticeInfo()");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
            sqlexception.printStackTrace();
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug("Exception in NoticeEngineDataBaseLayer.getNoticeInfo()");
            DataBaseLayer.log.debug("The Error Message - " + exception.getMessage());
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
        return vector;
    }
    
    public static Vector<String> getGroupDropDown() {
        final Vector<String> vector = new Vector<String>();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select group_id,group_name from student_group");
            while (resultset.next()) {
                final String g_id = resultset.getString(1);
                final String g_name = resultset.getString(2);
                vector.addElement(g_id);
                vector.addElement(g_name);
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug("SQLException in NoticeEngineDataBaseLayer.getNoticeInfo()");
            DataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
            sqlexception.printStackTrace();
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug("Exception in NoticeEngineDataBaseLayer.getNoticeInfo()");
            DataBaseLayer.log.debug("The Error Message - " + exception.getMessage());
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
        return vector;
    }
    
    public static String getCreatedBy(final String notice_id) {
        String createdby = "";
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds.getConnection();
            final Statement statement = oConn.createStatement();
            System.out.println("--------------111111111111-------->select student_id from bulletin_board where notice_id = '" + notice_id + "'");
            final ResultSet resultset = statement.executeQuery("select  student_id from bulletin_board where notice_id = '" + notice_id + "' ");
            while (resultset.next()) {
                createdby = resultset.getString(1);
            }
            statement.close();
            resultset.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.debug(" Excemtion in getCreatedBy(String s ) : " + sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.debug(" Excemtion in getCreatedBy(String s ) : " + exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return createdby;
    }
    
    static {
        log = new SimpleLogger((Class)DataBaseLayer.class, true);
        DataBaseLayer.ds = CoreAdminInitHostInfo.ds1;
    }
}
