package learnityforum;

import org.grlea.log.*;
import javax.sql.*;
import java.util.*;
import java.sql.*;
import comv2.aunwesha.param.*;

public class ForumDataBaseLayer
{
    public static final SimpleLogger log;
    public static DataSource ds;
    
    public static Vector<String[]> getTree(final String s, final String s2) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(7, 7);
        Connection connection = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            System.out.println("=============getTree==========select thread_title,created_by,created_on,forum_name,thread_id,forum_id,thread_id from forum_thread where forum_id ='" + s + "' and thread_id='" + s2 + "' and parent_thread=0");
            executeQuery = statement.executeQuery("select thread_title,created_by,created_on,forum_name,thread_id,forum_id,thread_id from forum_thread where forum_id ='" + s + "' and thread_id='" + s2 + "' and parent_thread=0");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6), executeQuery.getString(7) });
            }
        }
        catch (SQLException nextException) {
            ForumDataBaseLayer.log.debug("SQLException in getTree - " + nextException);
            while ((nextException = nextException.getNextException()) != null) {
                ForumDataBaseLayer.log.debug("The Error message - " + nextException.getMessage());
                ForumDataBaseLayer.log.debug("The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            ForumDataBaseLayer.log.debug("Exception in getTree - " + ex);
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
    
    public static Vector<String[]> getTreeReply(final String s, final String s2) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(7, 7);
        Connection connection = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            System.out.println("=========getTreeReply=========select thread_title,created_by,created_on,forum_name,parent_thread,forum_id,thread_id from forum_thread where forum_id ='" + s + "' and parent_thread='" + s2 + "' and thread_id<>parent_thread");
            executeQuery = statement.executeQuery("select thread_title,created_by,created_on,forum_name,parent_thread,forum_id,thread_id from forum_thread where forum_id ='" + s + "' and parent_thread='" + s2 + "' and thread_id<>parent_thread");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6), executeQuery.getString(7) });
            }
        }
        catch (SQLException nextException) {
            ForumDataBaseLayer.log.debug("SQLException in getTreeReply- " + nextException);
            while ((nextException = nextException.getNextException()) != null) {
                ForumDataBaseLayer.log.debug("The Error message - " + nextException.getMessage());
                ForumDataBaseLayer.log.debug("The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            ForumDataBaseLayer.log.debug("Exception in getTreeReply - " + ex);
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
    
    public static Vector getAllMessages(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(6, 6);
        Connection connection = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            final String string = "select a.forum_name,a.message,a.thread_title,concat(b.first_name,' ',b.middle_name,' ',b.sname),date_format(a.created_on,'%d-%m-%Y %H:%i:%S'),a.attachments,a.created_by from forum_message_body a,student_details b where a.forum_id='" + s + "' and a.created_by=b.student_id";
            ForumDataBaseLayer.log.debug(string);
            executeQuery = statement.executeQuery(string);
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6), executeQuery.getString(7) });
            }
        }
        catch (SQLException nextException) {
            ForumDataBaseLayer.log.debug("SQLException in getAllMessages - " + nextException);
            while ((nextException = nextException.getNextException()) != null) {
                ForumDataBaseLayer.log.debug("The Error message - " + nextException.getMessage());
                ForumDataBaseLayer.log.debug("The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            ForumDataBaseLayer.log.debug("Exception in getAllMessages - " + ex);
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
    
    public static String returnforumname(final String s) {
        String string = "";
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select forum_name from forum where forum_id='" + s + "'");
            ForumDataBaseLayer.log.debug("select forum_name from forum where forum_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException nextException) {
            ForumDataBaseLayer.log.debug("SQLException in returnforumname - " + nextException);
            while ((nextException = nextException.getNextException()) != null) {
                ForumDataBaseLayer.log.debug("The Error message - " + nextException.getMessage());
                ForumDataBaseLayer.log.debug("The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            ForumDataBaseLayer.log.debug("Exception in returnforumname - " + ex);
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
    
    public static Vector<String[]> getForumName(final String s) {
        Connection connection = null;
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>();
        final String[] array = new String[5];
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            final String string = "select c.forum_id, a.forum_name,a.start_date,date_format(a.last_message_posted,\"%d-%m-%Y %H:%i\"),a.no_of_message from forum a, user_forum_association c where c.student_id='" + s + "' and c.forum_id=a.forum_id and c.registered_by != 'null'";
            ForumDataBaseLayer.log.debug("#############sql###############" + string);
            executeQuery = statement.executeQuery(string);
            while (executeQuery.next()) {
                final String[] array2 = { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5) };
                ForumDataBaseLayer.log.debug("==str[0]== " + array2[0] + " ==str[1]== " + array2[1]);
                vector.addElement(array2);
            }
            ForumDataBaseLayer.log.debug("   THE VECTOR in db  ::" + vector + " size " + vector.size());
        }
        catch (SQLException nextException) {
            ForumDataBaseLayer.log.debug("SQLException in getForumName - " + nextException);
            while ((nextException = nextException.getNextException()) != null) {
                ForumDataBaseLayer.log.debug("The Error message - " + nextException.getMessage());
                ForumDataBaseLayer.log.debug("The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            ForumDataBaseLayer.log.debug("Exception in getForumName - " + ex);
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
    
    public static Vector<String> getforummessage(final String s) {
        final Vector<String> vector = new Vector<String>(3, 3);
        Connection connection = null;
        Statement statement = null;
        ResultSet executeQuery = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select count(no_of_message),max(created_on),sum(no_of_views) from forum_thread  where forum_name='" + s + "' ");
            while (executeQuery.next()) {
                final String string = executeQuery.getString(1);
                final String string2 = executeQuery.getString(2);
                final String string3 = executeQuery.getString(3);
                vector.addElement(string);
                vector.addElement(string2);
                vector.addElement(string3);
            }
        }
        catch (SQLException nextException) {
            ForumDataBaseLayer.log.debug("SQLException in getforummessage - " + nextException);
            while ((nextException = nextException.getNextException()) != null) {
                ForumDataBaseLayer.log.debug("The Error message - " + nextException.getMessage());
                ForumDataBaseLayer.log.debug("The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            ForumDataBaseLayer.log.debug("Exception in getforummessage " + ex);
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
    
    public static String postmade(final String s) {
        String string = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet executeQuery = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select sum(no_of_message) from forum_thread where forum_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            executeQuery.close();
            statement.close();
            connection.close();
        }
        catch (SQLException nextException) {
            ForumDataBaseLayer.log.debug("SQLException in postmade - " + nextException);
            while ((nextException = nextException.getNextException()) != null) {
                ForumDataBaseLayer.log.debug("The Error message - " + nextException.getMessage());
                ForumDataBaseLayer.log.debug("The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            ForumDataBaseLayer.log.debug("Exception in postmade " + ex);
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
    
    public static String thread1(final String s) {
        String string = null;
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select sum(no_of_message) from forum_thread where forum_id='" + s + "' and parent_thread=0");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException nextException) {
            ForumDataBaseLayer.log.debug("SQLException in thread1 - " + nextException);
            while ((nextException = nextException.getNextException()) != null) {
                ForumDataBaseLayer.log.debug("The Error message - " + nextException.getMessage());
                ForumDataBaseLayer.log.debug("The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            ForumDataBaseLayer.log.debug("Exception in thread1 " + ex);
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
    
    public static Vector getForumThreadInfo(final String s, final String s2) {
        Connection connection = null;
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String[]> vector = new Vector<String[]>(2, 2);
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select reply_id,reply_title from forum_reply where forum_name = '" + s + "' AND thread_title='" + s2 + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2) });
            }
        }
        catch (SQLException nextException) {
            ForumDataBaseLayer.log.debug("SQLException in getForumThreadInfo - " + nextException);
            while ((nextException = nextException.getNextException()) != null) {
                ForumDataBaseLayer.log.debug("The Error message - " + nextException.getMessage());
                ForumDataBaseLayer.log.debug("The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            ForumDataBaseLayer.log.debug("Exception in getForumThreadInfo - " + ex);
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
    
    public static Vector<String[]> getThreadInfo(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        final Vector<String[]> vector = new Vector<String[]>(7, 7);
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select a.forum_name,b.thread_id,b.thread_title,b.created_by,b.no_of_message,b.no_of_views,date_format(b.created_on,'%d-%m-%Y %H:%i:%S') from forum a,forum_thread b where a.forum_id='" + s + "' and a.forum_name=b.forum_name and parent_thread=0");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6), executeQuery.getString(7) });
            }
        }
        catch (SQLException nextException) {
            ForumDataBaseLayer.log.debug("SQLException in getThreadInfo - " + nextException);
            while ((nextException = nextException.getNextException()) != null) {
                ForumDataBaseLayer.log.debug("The Error message - " + nextException.getMessage());
                ForumDataBaseLayer.log.debug("The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            ForumDataBaseLayer.log.debug("Exception in getThreadInfo - " + ex);
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
    
    public static Vector getnoofmessage(final String s, final String s2) {
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        final Vector<String[]> vector = new Vector<String[]>(2, 2);
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            executeQuery = statement.executeQuery("select thread_title,forum_name from forum_thread where forum_name = '" + s + "' AND parent_thread='" + s2 + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2) });
            }
        }
        catch (SQLException nextException) {
            ForumDataBaseLayer.log.debug("SQLException in getnoofmessage - " + nextException);
            while ((nextException = nextException.getNextException()) != null) {
                ForumDataBaseLayer.log.debug("The Error message - " + nextException.getMessage());
                ForumDataBaseLayer.log.debug("The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            ForumDataBaseLayer.log.debug("Exception in getnoofmessage - " + ex);
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
    
    public static String getForumNameFromThread(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        String string = "";
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            final String string2 = "SELECT forum_name from forum_thread where thread_id='" + s + "'";
            ForumDataBaseLayer.log.debug(string2);
            executeQuery = statement.executeQuery(string2);
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            connection.close();
        }
        catch (SQLException ex) {
            ForumDataBaseLayer.log.debug("SQLException in getForumNameFromThread " + ex);
        }
        catch (Exception ex2) {
            ForumDataBaseLayer.log.debug("Exception in getForumNameFromThread " + ex2);
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
    
    public static synchronized void addReply(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            final String string = "insert into forum_thread(parent_thread,forum_name,created_by,created_on,thread_title,forum_id,no_of_message,no_of_views) values ('" + s + "','" + s2 + "','" + s3 + "',sysdate(),'" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "')";
            ForumDataBaseLayer.log.debug("=======addReply===" + string);
            statement.execute(string);
        }
        catch (SQLException ex) {
            ForumDataBaseLayer.log.debug("SQLException in addReply " + ex);
        }
        catch (Exception ex2) {
            ForumDataBaseLayer.log.debug("Exception in addReply " + ex2);
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
    
    public static synchronized void addMessage(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("insert into forum_thread(thread_title,no_of_message,created_by,created_on,no_of_views,forum_name,forum_id,parent_thread) values ('" + s + "','" + s2 + "','" + s3 + "',sysdate(),'" + s4 + "','" + s5 + "','" + s6 + "',0)");
            System.out.println("SQL FOR ADDING THREAD----------->insert into forum_thread(thread_title,no_of_message,created_by,created_on,no_of_views,forum_name,forum_id,parent_thread) values ('" + s + "','" + s2 + "','" + s3 + "',sysdate(),'" + s4 + "','" + s5 + "','" + s6 + "',0)");
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
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static Vector getAllMessages(final String s, final String s2) {
        Connection connection = null;
        final Vector<String[]> vector = new Vector<String[]>(6, 6);
        Statement statement = null;
        ResultSet executeQuery = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            System.out.println("select a.forum_name,a.message,a.thread_title,a.created_by,date_format(a.created_on,'%d-%m-%Y %H:%i:%S'),a.attachments from forum_message_body a,forum_thread b where a.created_on = b.created_on and a.forum_name='" + s + "' and b.thread_id = '" + s2 + "'");
            executeQuery = statement.executeQuery("select a.forum_name,a.message,a.thread_title,a.created_by,date_format(a.created_on,'%d-%m-%Y %H:%i:%S'),a.attachments from forum_message_body a,forum_thread b where a.created_on = b.created_on and a.forum_name='" + s + "' and b.thread_id = '" + s2 + "'");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6) });
            }
        }
        catch (SQLException ex) {
            ForumDataBaseLayer.log.debug("SQLException in getAllMessages " + ex);
        }
        catch (Exception ex2) {
            ForumDataBaseLayer.log.debug("Exception in getAllMessages " + ex2);
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
    
    public static synchronized void addMessageBody(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        PreparedStatement prepareStatement = null;
        Connection connection = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            connection.setAutoCommit(false);
            final String s7 = "insert into forum_message_body(message,forum_name,thread_title,attachments,created_by,forum_id,created_on) values (?,?,?,?,?,?,sysdate())";
            System.out.println(s7);
            prepareStatement = connection.prepareStatement(s7);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.setString(3, s3);
            prepareStatement.setString(4, s4);
            prepareStatement.setString(5, s5);
            prepareStatement.setString(6, s6);
            prepareStatement.executeUpdate();
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
                    prepareStatement.close();
                    connection.commit();
                    connection.setAutoCommit(true);
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized boolean updateForumpost(final String s) {
        boolean b = false;
        Statement statement = null;
        Connection connection = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            final String string = "update forum set last_message_posted=sysdate() where forum_id ='" + s + "'";
            System.out.println(string);
            statement.execute(string);
            b = true;
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
                    statement.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return b;
    }
    
    public static synchronized void updatenoofmessage(final String s) {
        Connection connection = null;
        Statement statement = null;
        Statement statement2 = null;
        ResultSet executeQuery = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            final String string = "select no_of_message from forum where forum_id='" + s + "'";
            System.out.println(string);
            executeQuery = statement.executeQuery(string);
            if (executeQuery.next()) {
                final String string2 = "update forum SET no_of_message='" + (Integer.parseInt(executeQuery.getString(1)) + 1) + "' where forum_id='" + s + "'";
                System.out.println(string2);
                statement2.executeUpdate(string2);
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
                    statement2.close();
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized String getThreadTitle(final String s) {
        String string = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet executeQuery = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            final String string2 = "select thread_title from forum_thread where thread_id='" + s + "'";
            ForumDataBaseLayer.log.debug(string2);
            executeQuery = statement.executeQuery(string2);
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            ForumDataBaseLayer.log.debug("SQLException in getThreadTitle " + ex);
        }
        catch (Exception ex2) {
            ForumDataBaseLayer.log.debug("Exception in getThreadTitle " + ex2);
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
    
    public static synchronized String getThreadId(final String s) {
        String string = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet executeQuery = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            final String string2 = "select thread_id from forum_thread where thread_title='" + s + "'";
            ForumDataBaseLayer.log.debug(string2);
            executeQuery = statement.executeQuery(string2);
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            ForumDataBaseLayer.log.debug("SQLException in getThreadId " + ex);
        }
        catch (Exception ex2) {
            ForumDataBaseLayer.log.debug("Exception in getThreadId " + ex2);
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
    
    public static byte[] getUserPhoto(final String s) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        byte[] bytes = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            prepareStatement = connection.prepareStatement("SELECT photo FROM student_details WHERE student_id = ?");
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                System.out.println("rs has next");
                final Blob blob = executeQuery.getBlob(1);
                final int n = (int)blob.length();
                System.out.println("length is:" + n);
                bytes = blob.getBytes(1L, n);
            }
            System.out.println("Completed.Retreiving..");
        }
        catch (SQLException ex) {
            ForumDataBaseLayer.log.debug("SQLException in getUserPhoto " + ex);
        }
        catch (Exception ex2) {
            ForumDataBaseLayer.log.debug("Exception in getUserPhoto " + ex2);
        }
        finally {
            if (connection != null) {
                try {
                    prepareStatement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return bytes;
    }
    
    public static synchronized boolean clearForum(final String s) {
        Connection connection = null;
        boolean b = false;
        Statement statement = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            final String string = "delete from forum_thread where forum_id ='" + s + "'";
            ForumDataBaseLayer.log.debug(string);
            statement.execute(string);
            final String string2 = "delete from forum_message_body where forum_id ='" + s + "'";
            ForumDataBaseLayer.log.debug(string2);
            statement.execute(string2);
            final String string3 = "delete from forum_reply where forum_id ='" + s + "'";
            ForumDataBaseLayer.log.debug(string3);
            statement.execute(string3);
            final String string4 = "update forum set no_of_message='0' where forum_id = '" + s + "'";
            ForumDataBaseLayer.log.debug(string4);
            statement.execute(string4);
            b = true;
        }
        catch (SQLException ex) {
            ForumDataBaseLayer.log.debug("SQLException in clearForum " + ex);
        }
        catch (Exception ex2) {
            ForumDataBaseLayer.log.debug("Exception in clearForum " + ex2);
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
    
    public static synchronized String getDynamicInformationByUser() {
        Statement statement = null;
        ResultSet executeQuery = null;
        String string = "";
        Connection connection = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            final String s = "select ifnull(sec_to_time(sum((unix_timestamp(sysdate()))  - (unix_timestamp(c.logged_in_at)))),'0') from student_details b join forum_dynamic_information c on b.student_id = c.student_id join forum d on c.forum_id = d.forum_id";
            ForumDataBaseLayer.log.debug(s);
            executeQuery = statement.executeQuery(s);
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            ForumDataBaseLayer.log.debug("SQLException in getDynamicInformationByUser " + ex);
        }
        catch (Exception ex2) {
            ForumDataBaseLayer.log.debug("Exception in getDynamicInformationByUser " + ex2);
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
    
    public static synchronized void removeForumDynamicInfo(final String s, final String s2) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            final String string = "delete from forum_dynamic_information where student_id='" + s + "' and session_id='" + s2 + "'";
            ForumDataBaseLayer.log.debug(string);
            statement.execute(string);
        }
        catch (SQLException ex) {
            ForumDataBaseLayer.log.debug("SQLException in removeForumDynamicInfo " + ex);
        }
        catch (Exception ex2) {
            ForumDataBaseLayer.log.debug("Exception in removeForumDynamicInfo " + ex2);
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
    
    public static synchronized String getThreadId1(final String s) {
        String string = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet executeQuery = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            final String string2 = "select thread_id from forum_thread where forum_id='" + s + "'";
            ForumDataBaseLayer.log.debug(string2);
            executeQuery = statement.executeQuery(string2);
            if (executeQuery.next()) {
                string = executeQuery.getString(1);
                ForumDataBaseLayer.log.debug("threadId===getThreadId1====" + string);
            }
        }
        catch (SQLException ex) {
            ForumDataBaseLayer.log.debug("SQLException in getThreadId " + ex);
        }
        catch (Exception ex2) {
            ForumDataBaseLayer.log.debug("Exception in getThreadId " + ex2);
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
    
    public static synchronized boolean unregisterAllForum(final String s) {
        Connection connection = null;
        boolean b = false;
        Statement statement = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            final String string = "delete from user_forum_association where forum_id ='" + s + "'";
            ForumDataBaseLayer.log.debug(string);
            statement.execute(string);
            b = true;
        }
        catch (SQLException ex) {
            ForumDataBaseLayer.log.debug("SQLException in unregisterAllForum " + ex);
        }
        catch (Exception ex2) {
            ForumDataBaseLayer.log.debug("Exception in unregisterAllForum " + ex2);
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
    
    public static synchronized boolean unregisterAllForumGroup(final String s) {
        Connection connection = null;
        boolean b = false;
        Statement statement = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            final String string = "delete from group_forum_association where forum_id ='" + s + "'";
            ForumDataBaseLayer.log.debug(string);
            statement.execute(string);
            b = true;
        }
        catch (SQLException ex) {
            ForumDataBaseLayer.log.debug("SQLException in unregisterAllForumGroup " + ex);
        }
        catch (Exception ex2) {
            ForumDataBaseLayer.log.debug("Exception in unregisterAllForumGroup " + ex2);
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
    
    public static Vector<String[]> getThreadMessages(final String s, final String s2) {
        Connection connection = null;
        final Vector<String[]> vector = new Vector<String[]>(7, 7);
        Statement statement = null;
        ResultSet executeQuery = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            System.out.println("==============select a.forum_name,a.message,a.thread_title,concat(c.first_name,' ',c.middle_name,' ',c.sname),date_format(a.created_on,'%d-%m-%Y %H:%i:%S'),a.attachments,c.photo from forum_message_body a,forum_thread b,student_details c where a.created_on = b.created_on and a.forum_id='" + s + "' and b.thread_id = '" + s2 + "' and a.created_by=c.student_id");
            executeQuery = statement.executeQuery("select a.forum_name,a.message,a.thread_title,a.created_by,date_format(a.created_on,'%d-%m-%Y %H:%i:%S'),a.attachments,concat(c.first_name,' ',c.middle_name,' ',c.sname) from forum_message_body a,forum_thread b,student_details c where a.created_on = b.created_on and a.forum_id='" + s + "' and b.thread_id = '" + s2 + "' and a.created_by=c.student_id");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getString(5), executeQuery.getString(6), executeQuery.getString(7) });
            }
        }
        catch (SQLException ex) {
            ForumDataBaseLayer.log.debug("SQLException in getThreadMessages " + ex);
        }
        catch (Exception ex2) {
            ForumDataBaseLayer.log.debug("Exception in getThreadMessages " + ex2);
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
    
    public static void ExecuteInsertSqlQuery(final String s, final String s2, final String s3, final String s4) {
        Statement statement = null;
        Statement statement2 = null;
        Statement statement3 = null;
        ResultSet executeQuery = null;
        Connection connection = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            statement3 = connection.createStatement();
            statement.execute(s);
            executeQuery = statement2.executeQuery("select student_id from student_group_association where group_id='" + s2 + "'");
            while (executeQuery.next()) {
                statement3.execute("insert into user_forum_association values('" + executeQuery.getString(1) + "','" + s3 + "','" + s4 + "')");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
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
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static void ExecuteDeleteSqlQuery(final String s, final String s2, final String s3) {
        Connection connection = null;
        Statement statement = null;
        Statement statement2 = null;
        Statement statement3 = null;
        ResultSet executeQuery = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            statement3 = connection.createStatement();
            statement.execute(s);
            executeQuery = statement2.executeQuery("select student_id from student_group_association where group_id='" + s2 + "'");
            while (executeQuery.next()) {
                statement3.execute("delete from user_forum_association where forum_id='" + s3 + "' and student_id='" + executeQuery.getString(1) + "' ");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
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
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static synchronized void updateForumAllMessageViews(final String s) {
        Connection connection = null;
        Statement statement = null;
        Statement statement2 = null;
        Statement statement3 = null;
        ResultSet executeQuery = null;
        ResultSet executeQuery2 = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement2 = connection.createStatement();
            statement3 = connection.createStatement();
            statement = connection.createStatement();
            System.out.println("select thread_id from forum_thread where forum_id='" + s + "'");
            executeQuery = statement.executeQuery("select thread_id from forum_thread where forum_id='" + s + "' ");
            while (executeQuery.next()) {
                final String string = executeQuery.getString(1);
                System.out.println("threadId==================" + string);
                System.out.println("select no_of_views from forum_thread where forum_name='" + s + "' AND thread_title='" + string + "'");
                executeQuery2 = statement2.executeQuery("select no_of_views from forum_thread where forum_id='" + s + "' AND thread_id='" + string + "'");
                while (executeQuery2.next()) {
                    final String string2 = executeQuery2.getString(1);
                    System.out.println("total_view=========================" + string2);
                    statement3.executeUpdate("update forum_thread SET no_of_views='" + (Integer.parseInt(string2) + 1) + "' where forum_id='" + s + "' AND thread_id='" + string + "'");
                }
            }
        }
        catch (SQLException ex) {
            System.out.println("SQL error inside NewDataBaseLayer.updateForumAllMessageViews()");
            System.out.println("The error code is" + ex.getErrorCode());
            System.out.println("The error message is" + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println("General exception inside NewDataBaseLayer.updateForumAllMessageViews()");
            ex2.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement3.close();
                    statement2.close();
                    statement.close();
                    if (executeQuery2 != null) {
                        executeQuery2.close();
                    }
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void updateForumMessageViews(final String s, final String s2) {
        Connection connection = null;
        Statement statement = null;
        Statement statement2 = null;
        ResultSet executeQuery = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            executeQuery = statement.executeQuery("select no_of_views from forum_thread where forum_id='" + s + "' AND thread_id='" + s2 + "'");
            if (executeQuery.next()) {
                statement2.executeUpdate("update forum_thread SET no_of_views='" + (Integer.parseInt(executeQuery.getString(1)) + 1) + "' where forum_id='" + s + "' AND thread_id='" + s2 + "'");
            }
        }
        catch (SQLException ex) {
            System.out.println("SQL error inside NewDataBaseLayer.updateForumMessageViews()");
            System.out.println("The error code is" + ex.getErrorCode());
            System.out.println("The error message is" + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println("General exception inside NewDataBaseLayer.updateForumMessageViews()");
            ex2.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    statement2.close();
                    statement.close();
                    executeQuery.close();
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static Vector<String> getAllMessagesThreadWise(final String s) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String> vector = new Vector<String>(8, 8);
        Connection connection = null;
        try {
            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            final String string = "select a.forum_name,a.message,a.thread_title,concat(b.first_name,' ',b.middle_name,' ',b.sname),date_format(a.created_on,'%d-%m-%Y %H:%i:%S'),a.attachments,a.created_by,c.thread_id from forum_message_body a,student_details b,forum_thread c  where a.forum_id='" + s + "' and a.created_by=b.student_id and a.forum_id=c.forum_id and a.message_id=c.thread_id  and c.parent_thread='0'";
            System.out.println("=============sql=========" + string);
            executeQuery = statement.executeQuery(string);
            while (executeQuery.next()) {
                final String[] array = new String[8];
                vector.addElement(executeQuery.getString(1));
                vector.addElement(executeQuery.getString(2));
                vector.addElement(executeQuery.getString(3));
                vector.addElement(executeQuery.getString(4));
                vector.addElement(executeQuery.getString(5));
                vector.addElement(executeQuery.getString(6));
                vector.addElement(executeQuery.getString(7));
                vector.addElement(executeQuery.getString(8));
                final String string2 = executeQuery.getString(8);
                System.out.println("================parent_id=======" + string2);
                final Vector<String> allMessagesThreadWise = getAllMessagesThreadWise(s, string2, connection);
                for (int i = 0; i < allMessagesThreadWise.size(); i += 8) {
                    vector.addElement(allMessagesThreadWise.elementAt(i));
                    vector.addElement(allMessagesThreadWise.elementAt(i + 1));
                    vector.addElement(allMessagesThreadWise.elementAt(i + 2));
                    vector.addElement(allMessagesThreadWise.elementAt(i + 3));
                    vector.addElement(allMessagesThreadWise.elementAt(i + 4));
                    vector.addElement(allMessagesThreadWise.elementAt(i + 5));
                    vector.addElement(allMessagesThreadWise.elementAt(i + 6));
                    vector.addElement(allMessagesThreadWise.elementAt(i + 7));
                }
            }
        }
        catch (SQLException nextException) {
            nextException.printStackTrace();
            ForumDataBaseLayer.log.debug("SQLException in getAllMessages - " + nextException);
            while ((nextException = nextException.getNextException()) != null) {
                ForumDataBaseLayer.log.debug("The Error message - " + nextException.getMessage());
                ForumDataBaseLayer.log.debug("The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            ForumDataBaseLayer.log.debug("Exception in getAllMessages - " + ex);
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
        System.out.println("===================vCourseList=============" + vector);
        return vector;
    }
    
    public static Vector<String> getAllMessagesThreadWise(final String s, final String s2, Connection conn) {
        Statement statement = null;
        ResultSet executeQuery = null;
        final Vector<String> vector = new Vector<String>(8, 8);
        Connection connection = conn;
        try {
//            connection = ForumDataBaseLayer.ds.getConnection();
            statement = connection.createStatement();
            final String string = "select a.forum_name,a.message,a.thread_title,concat(b.first_name,' ',b.middle_name,' ',b.sname),date_format(a.created_on,'%d-%m-%Y %H:%i:%S'),a.attachments,a.created_by,c.thread_id from forum_message_body a,student_details b,forum_thread c  where a.forum_id='" + s + "' and a.created_by=b.student_id and a.forum_id=c.forum_id and a.message_id=c.thread_id  and c.parent_thread='" + s2 + "'";
            ForumDataBaseLayer.log.debug(string);
            executeQuery = statement.executeQuery(string);
            while (executeQuery.next()) {
                final String[] array = new String[8];
                vector.addElement(executeQuery.getString(1));
                vector.addElement(executeQuery.getString(2));
                vector.addElement(executeQuery.getString(3));
                vector.addElement(executeQuery.getString(4));
                vector.addElement(executeQuery.getString(5));
                vector.addElement(executeQuery.getString(6));
                vector.addElement(executeQuery.getString(7));
                vector.addElement(executeQuery.getString(8));
                System.out.println("===============vList in child=========" + vector);
                final String string2 = executeQuery.getString(8);
                System.out.println("==============parent_thread========" + string2);
                final Vector<String> allMessagesThreadWise = getAllMessagesThreadWise(s, string2, conn);
                for (int i = 0; i < allMessagesThreadWise.size(); i += 8) {
                    vector.addElement(allMessagesThreadWise.elementAt(i));
                    vector.addElement(allMessagesThreadWise.elementAt(i + 1));
                    vector.addElement(allMessagesThreadWise.elementAt(i + 2));
                    vector.addElement(allMessagesThreadWise.elementAt(i + 3));
                    vector.addElement(allMessagesThreadWise.elementAt(i + 4));
                    vector.addElement(allMessagesThreadWise.elementAt(i + 5));
                    vector.addElement(allMessagesThreadWise.elementAt(i + 6));
                    vector.addElement(allMessagesThreadWise.elementAt(i + 7));
                }
            }
        }
        catch (SQLException nextException) {
            ForumDataBaseLayer.log.debug("SQLException in getAllMessages - " + nextException);
            while ((nextException = nextException.getNextException()) != null) {
                ForumDataBaseLayer.log.debug("The Error message - " + nextException.getMessage());
                ForumDataBaseLayer.log.debug("The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            ForumDataBaseLayer.log.debug("Exception in getAllMessages - " + ex);
        }
        finally {
            if (connection != null) {
                try {
                    statement.close();
                    executeQuery.close();
//                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        System.out.println("=================vList in child===1111==" + vector);
        return vector;
    }
    
    static {
        log = new SimpleLogger((Class)ForumDataBaseLayer.class, true);
        ForumDataBaseLayer.ds = CoreAdminInitHostInfo.ds1;
    }
}
