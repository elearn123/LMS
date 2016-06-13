package deliveryengine;

import org.grlea.log.*;
import javax.sql.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import java.io.*;
import java.sql.*;
import org.adl.util.debug.*;
import java.util.*;
import comv2.aunwesha.param.*;

public class DataBaseLayer
{
    private static final SimpleLogger log;
    public static DataSource ds;
    public static DataSource ds1;
    private static boolean _Debug;
    
    public static Document parse(final String s, final String s2) {
        Object parse = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            s2.toLowerCase();
            System.out.println("=======select=======select csf from " + s2 + " where unit_id='" + s + "'");
            final ResultSet executeQuery = statement.executeQuery("select csf from " + s2 + " where unit_id='" + s + "'");
            if (executeQuery.next()) {
                final InputSource inputSource = new InputSource(new InputStreamReader(executeQuery.getAsciiStream(1), "UTF-8"));
                inputSource.setEncoding("UTF-8");
                parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
                executeQuery.close();
                statement.close();
            }
        }
        catch (SQLException ex) {
            DataBaseLayer.log.entry("parse()");
            DataBaseLayer.log.error(" error due to SQL exception inside DataBaseLayer.XMLDocument parse()");
            ex.getErrorCode();
            ex.getMessage();
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            DataBaseLayer.log.entry("parse()");
            DataBaseLayer.log.error(" error due to java.lang.exception");
            ex2.printStackTrace();
            DataBaseLayer.log.error(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {
                    ex3.printStackTrace();
                }
                catch (Exception ex4) {
                    ex4.printStackTrace();
                }
            }
        }
        System.out.println("==================xmldocument==============" + parse);
        return (Document)parse;
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
    
    public static void addUserSCODetails(final String s, final String s2, final String s3, final String s4, final String s5) {
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final String string = "insert into sco_user_info(student_id, unit_id,sco_id,sco_title,start_time) values ('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "',sysdate())";
            System.out.println("------->>>>>>>>-------addUserSCODetails()----- " + string);
            statement.execute(string);
            statement.close();
            final Statement statement2 = connection.createStatement();
            final String string2 = "insert into userscoinfo(user_id, unit_id, sco_id, launch, lessonstatus) values ('" + s + "','" + s2 + "','" + s3 + "','" + s5 + "','attempted')";
            System.out.println("------->>>>>>>>-------addUserSCODetails()----- " + string2);
            statement2.execute(string2);
            statement2.close();
        }
        catch (SQLException nextException) {
            DataBaseLayer.log.debug("------->>>>>>>>-------The Error Message - " + nextException.getMessage());
            DataBaseLayer.log.debug("------->>>>>>>>-------The Error Code    - " + nextException.getErrorCode());
            while ((nextException = nextException.getNextException()) != null) {
                DataBaseLayer.log.debug("------->>>>>>>>-------The Error message - " + nextException.getMessage());
                DataBaseLayer.log.debug("------->>>>>>>>-------The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            DataBaseLayer.log.debug("------->>>>>>>>-------General exception in DataBaseLayer.addUserSCODetails()----");
            ex.printStackTrace();
            DataBaseLayer.log.debug("------->>>>>>>>-------The message - " + ex.toString());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static synchronized void setLessonStatus(final String s, final String s2, final String s3, final String s4) {
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            System.out.println("update userscoinfo set lessonstatus='" + s4 + "' where user_id='" + s + "' and unit_id='" + s2 + "' and sco_id='" + s3 + "'");
            statement.execute("update userscoinfo set lessonstatus='" + s4 + "' where user_id='" + s + "' and unit_id='" + s2 + "' and sco_id='" + s3 + "'");
            statement.close();
        }
        catch (SQLException ex2) {
            System.out.println(" error due to SQL exception");
        }
        catch (Exception ex) {
            System.out.println(" error due to java.lang.exception");
            ex.printStackTrace();
            System.out.println(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static Vector<String> getLessonStatus1(final String s, final String s2, final String s3) {
        final Vector<String> vector = new Vector<String>(2, 2);
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select lessonstatus from userscoinfo where user_id='" + s + "' and  unit_id='" + s2 + "' and sco_id='" + s3 + "'");
            while (executeQuery.next()) {
                vector.addElement(executeQuery.getString(1));
            }
            executeQuery.close();
            statement.close();
        }
        catch (SQLException ex) {
            System.out.println("Error due to SQL exception inside DataBaseLayer.getUserscoInfo() getLessonStatus");
            ex.getErrorCode();
            ex.getMessage();
        }
        catch (Exception ex2) {
            System.out.println("Exception inside DataBaseLayer.getUserscoInfo() getLessonStatus");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
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
    
    public static synchronized void setLessonStatus1(final String s, final String s2, final String s3, final String s4) {
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            System.out.println("update userscoinfo set lessonstatus='" + s4 + "' where user_id='" + s + "' and unit_id='" + s2 + "'and sco_id='" + s3 + "'");
            statement.execute("update userscoinfo set lessonstatus='" + s4 + "' where user_id='" + s + "' and unit_id='" + s2 + "'and sco_id='" + s3 + "'");
            statement.close();
        }
        catch (SQLException ex2) {
            System.out.println(" error due to SQL exception");
        }
        catch (Exception ex) {
            System.out.println(" error due to java.lang.exception");
            ex.printStackTrace();
            System.out.println(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void setSessionTime(final String s, final String s2, final String s3, final String s4) {
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            statement.execute("update userscoinfo set sessiontime='" + s4 + "' where user_id='" + s + "' and unit_id='" + s2 + "'and sco_id='" + s3 + "'");
            statement.close();
        }
        catch (SQLException ex2) {
            System.out.println(" error due to SQL exception");
        }
        catch (Exception ex) {
            System.out.println(" error due to java.lang.exception");
            ex.printStackTrace();
            System.out.println(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static Vector<String> getLessonStatus(final String s, final String s2, final String s3) {
        Connection connection = null;
        final Vector<String> vector = new Vector<String>(2, 2);
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            System.out.println("select lessonstatus from userscoinfo where user_id='" + s + "'and  unit_id='" + s2 + "' and sco_id='" + s3 + "'");
            final ResultSet executeQuery = statement.executeQuery("select lessonstatus from userscoinfo where user_id='" + s + "'and  unit_id='" + s2 + "' and sco_id='" + s3 + "'");
            while (executeQuery.next()) {
                vector.addElement(executeQuery.getString(1));
            }
            executeQuery.close();
            statement.close();
        }
        catch (SQLException ex) {
            System.out.println("Error due to SQL exception inside DataBaseLayer.getUserscoInfo() getLessonStatus");
            ex.getErrorCode();
            ex.getMessage();
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            System.out.println("Exception inside DataBaseLayer.getUserscoInfo() getLessonStatus");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
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
    
    public static void InsertIntoLearnerLoginInfo(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            final Statement statement = connection.createStatement();
            System.out.println("insert into learner_login_info(student_id,unit_id,session_id,topic_id,login_datetime,logout_datetime)  values('" + s + "','" + s2 + "','" + s5 + "','" + s3 + "','" + s4 + " " + s6 + "','" + s4 + " " + s6 + "')");
            statement.execute("insert into learner_login_info(student_id,unit_id,session_id,topic_id,login_datetime,logout_datetime)  values('" + s + "','" + s2 + "','" + s5 + "','" + s3 + "','" + s4 + " " + s6 + "','" + s4 + " " + s6 + "')");
            statement.close();
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException ex) {
            System.out.println("Inside DataBaseLayer InsertIntoLearnerLoginInfo(), Error due to SQL exception!" + ex.getMessage());
            ex.getErrorCode();
            System.out.println("The Error is :- " + ex.getMessage());
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            System.out.println("Inside DataBaseLayer InsertIntoLearnerLoginInfo(), exception!");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void updateLoginInfo(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            statement.execute("update learner_login_info set logout_datetime = '" + s5 + " " + s4 + "' where session_id = '" + s3 + "'and student_id = '" + s + "' and topic_id= '" + s2 + "' and unit_id='" + s6 + "' and logout_datetime = login_datetime");
            statement.close();
        }
        catch (SQLException ex) {
            System.out.println("SQL error inside DataBaseLayer.updateLoginInfo()");
            System.out.println("The error code is" + ex.getErrorCode());
            System.out.println("The error message is" + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println("General exception inside DataBaseLayer.updateLoginInfo()");
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
    }
    
    public static synchronized String selectPrerequisites(final String s, final String s2, String string) {
        String string2 = new String();
        Connection connection = null;
        while (true) {
            if (DebugIndicator.ON) {
                final String s3 = "SELECT prerequisites FROM userscoinfo WHERE user_id = ? AND unit_id = ? AND sco_id = ?";
                final String s4 = "SELECT prerequisites FROM userscoinfo WHERE user_id = ? AND unit_id = ? AND sco_id = ?";
                try {
                    connection = DataBaseLayer.ds1.getConnection();
                    final PreparedStatement prepareStatement = connection.prepareStatement(s3);
                    final PreparedStatement prepareStatement2 = connection.prepareStatement(s4);
                    if (DataBaseLayer._Debug) {
                        DataBaseLayer.log.entry("selectPrerequisites()");
                        DataBaseLayer.log.info("about to call item in RTEFile");
                        DataBaseLayer.log.info("userID: " + s);
                        DataBaseLayer.log.info("courseID: " + s2);
                    }
                    ResultSet executeQuery = null;
                    ResultSet executeQuery2 = null;
                    synchronized (prepareStatement) {
                        prepareStatement.setString(1, s);
                        prepareStatement.setString(2, s2);
                        prepareStatement.setString(3, string);
                        executeQuery = prepareStatement.executeQuery();
                    }
                    if (DataBaseLayer._Debug) {
                        DataBaseLayer.log.entry("selectPrerequisites()");
                        DataBaseLayer.log.info("call to itemRS is complete");
                    }
                    while (executeQuery.next()) {
                        string = executeQuery.getString(1);
                        if (!string.equals("") && string != null) {
                            synchronized (prepareStatement2) {
                                prepareStatement2.setString(1, s);
                                prepareStatement2.setString(2, s2);
                                prepareStatement2.setString(3, string);
                                executeQuery2 = prepareStatement2.executeQuery();
                            }
                        }
                    }
                    if (executeQuery2 != null && executeQuery2.next()) {
                        string2 = executeQuery2.getString(1);
                        executeQuery2.close();
                    }
                    executeQuery.close();
                    prepareStatement.close();
                    prepareStatement2.close();
                    connection.close();
                }
                catch (Exception ex) {
                    if (DataBaseLayer._Debug) {
                        ex.printStackTrace();
                    }
                }
                finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        }
                        catch (SQLException ex2) {}
                    }
                }
                return string2;
            }
            continue;
        }
    }
    
    public static synchronized String selectPrerequisites1(final String s, final String s2, final String s3) {
        Connection connection = null;
        String string = new String();
        final String s4 = "SELECT prerequisites FROM userscoinfo WHERE user_id = ? AND unit_id = ? AND sco_id = ?";
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final PreparedStatement prepareStatement = connection.prepareStatement(s4);
            ResultSet executeQuery = null;
            synchronized (prepareStatement) {
                prepareStatement.setString(1, s);
                prepareStatement.setString(2, s2);
                prepareStatement.setString(3, s3);
                executeQuery = prepareStatement.executeQuery();
            }
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            executeQuery.close();
            prepareStatement.close();
            connection.close();
        }
        catch (Exception ex) {
            if (DataBaseLayer._Debug) {
                ex.printStackTrace();
            }
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
    
    public static synchronized String getStatus(final String s, final String s2, final String s3) {
        String string = new String();
        Connection connection = null;
        if (DebugIndicator.ON) {
            DataBaseLayer.log.entry("getStatus()");
            DataBaseLayer.log.info("****Select Prerequisites****");
        }
        final String s4 = "SELECT lessonstatus FROM userscoinfo WHERE user_id = ? AND unit_id = ? AND sco_id = ?";
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final PreparedStatement prepareStatement = connection.prepareStatement(s4);
            if (DataBaseLayer._Debug) {
                DataBaseLayer.log.entry("getStatus()");
                DataBaseLayer.log.verbose("about to call item in RTEFile");
                DataBaseLayer.log.verbose("userID: " + s);
                DataBaseLayer.log.verbose("courseID: " + s2);
            }
            ResultSet executeQuery = null;
            synchronized (prepareStatement) {
                prepareStatement.setString(1, s);
                prepareStatement.setString(2, s2);
                prepareStatement.setString(3, s3);
                executeQuery = prepareStatement.executeQuery();
            }
            if (executeQuery != null && executeQuery.next()) {
                string = executeQuery.getString(1);
                executeQuery.close();
            }
            prepareStatement.close();
            connection.close();
        }
        catch (Exception ex) {
            if (DataBaseLayer._Debug) {
                ex.printStackTrace();
            }
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
    
    public static synchronized String getMarks(final String s, final String s2, final String s3) {
        String string = new String();
        Connection connection = null;
        final String s4 = "SELECT rawscore FROM userscoinfo WHERE user_id = ? AND unit_id = ? AND sco_id = ?";
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final PreparedStatement prepareStatement = connection.prepareStatement(s4);
            ResultSet executeQuery = null;
            synchronized (prepareStatement) {
                prepareStatement.setString(1, s);
                prepareStatement.setString(2, s2);
                prepareStatement.setString(3, s3);
                executeQuery = prepareStatement.executeQuery();
            }
            if (executeQuery != null && executeQuery.next()) {
                string = executeQuery.getString(1);
                executeQuery.close();
            }
            prepareStatement.close();
            connection.close();
        }
        catch (Exception ex) {
            if (DataBaseLayer._Debug) {
                ex.printStackTrace();
            }
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
    
    public static String getLearning_Style(final String s) {
        Connection connection = null;
        String string = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select learning_style from student_details where student_id='" + s + "'");
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            executeQuery.close();
            statement.close();
        }
        catch (SQLException ex) {
            System.out.println(" error due to SQL exception inside DataBaseLayer.getLearning_Style()");
            ex.getErrorCode();
            ex.getMessage();
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            System.out.println(" error due to java.lang.exception");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {
                    ex3.printStackTrace();
                }
                catch (Exception ex4) {
                    ex4.printStackTrace();
                }
            }
        }
        return string;
    }
    
    public static synchronized Vector<String> getCourseDetailsList(final String s) {
        Vector<String> vector = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select count(*)  from unit_details, unit_creation_details where unit_details.unit_id=unit_creation_details.unit_id and unit_details.unit_id='" + s + "'");
            int int1 = 0;
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
            executeQuery.close();
            statement.close();
            if (int1 <= 0) {
                return null;
            }
            vector = new Vector<String>();
            final Statement statement2 = connection.createStatement();
            final ResultSet executeQuery2 = statement2.executeQuery("select a.unit_id, b.email_id, b.forum_name, b.chat_name, b.calender_name, b.controll  from unit_details a, unit_creation_details b where a.unit_id=b.unit_id and a.unit_id='" + s + "'");
            if (executeQuery2.next()) {
                vector.addElement(executeQuery2.getString(1));
                vector.addElement(executeQuery2.getString(2));
                vector.addElement(executeQuery2.getString(3));
                vector.addElement(executeQuery2.getString(4));
                vector.addElement(executeQuery2.getString(5));
                vector.addElement(executeQuery2.getString(6));
            }
            executeQuery2.close();
            statement2.close();
        }
        catch (SQLException ex) {
            System.out.println("getCourseDetailsList1: error due to SQL exception");
            ex.getErrorCode();
            ex.getMessage();
        }
        catch (Exception ex2) {
            System.out.println("getCourseDetailsList1");
            System.out.println(" printStack is :: " + ex2.getMessage());
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
    
    public static void Insertintoscouserinfo(final String s, final String s2, final String s3, final String s4) {
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            final Statement statement = connection.createStatement();
            System.out.println("insert into sco_user_info(student_id,unit_id,sco_id,sco_title,start_time,end_time)  values('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "',sysdate(),'null')");
            statement.execute("insert into sco_user_info(student_id,unit_id,sco_id,sco_title,start_time,end_time)  values('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "',sysdate(),sysdate())");
            statement.close();
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException ex) {
            System.out.println("Inside DataBaseLayer Insertintoscouserinfo(), Error due to SQL exception!");
            ex.getErrorCode();
            System.out.println("The Error is :- " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println("Inside DataBaseLayer Insertintoscouserinfo(), exception!");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void Updateintoscouserinfo(final String s, final String s2, final String s3, final String s4) {
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            statement.execute("update sco_user_info set end_time = sysdate() where student_id = '" + s + "' and unit_id= '" + s2 + "' and sco_id='" + s3 + "' and start_time= end_time");
            statement.close();
        }
        catch (SQLException ex) {
            System.out.println("SQL error inside DataBaseLayer.Updateintoscouserinfo()");
            System.out.println("The error code is" + ex.getErrorCode());
            System.out.println("The error message is" + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println("General exception inside DataBaseLayer.Updateintoscouserinfo()");
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
    }
    
    public static synchronized int insertCourseUserAssociation(final String s, final String s2, final String s3, final String s4, final String s5) {
        final boolean b = false;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final Statement statement2 = connection.createStatement();
            if (s5.equals("") && s4.equals("")) {
                System.out.println("===1=======insert into unit_student_association (student_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "',null,null)");
                statement2.execute("insert into unit_student_association (student_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "',null,null)");
                final ResultSet executeQuery = statement.executeQuery("select unit_id,identifier,type,title,launch,prerequisites,sequence from iteminfo where unit_id='" + s2 + "'");
                while (executeQuery.next()) {
                    executeQuery.getString(1);
                    final String string = executeQuery.getString(2);
                    final String string2 = executeQuery.getString(3);
                    executeQuery.getString(4);
                    final String string3 = executeQuery.getString(5);
                    final String string4 = executeQuery.getString(6);
                    final int int1 = executeQuery.getInt(7);
                    final String s6 = "not attempted";
                    final PreparedStatement prepareStatement = connection.prepareStatement("insert into  userscoinfo(user_id,unit_id,sco_id,launch,prerequisites,sequence,type,lessonstatus) values(?,?,?,?,?,?,?,?)");
                    synchronized (prepareStatement) {
                        prepareStatement.setString(1, s);
                        prepareStatement.setString(2, s2);
                        prepareStatement.setString(3, string);
                        prepareStatement.setString(4, string3);
                        prepareStatement.setString(5, string4);
                        prepareStatement.setInt(6, int1);
                        prepareStatement.setString(7, string2);
                        prepareStatement.setString(8, s6);
                        prepareStatement.executeUpdate();
                    }
                }
            }
            if (s5.equals("") && !s4.equals("")) {
                System.out.println("===2==insert into unit_student_association (student_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "','" + s4 + "',null)");
                statement2.execute("insert into unit_student_association (student_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "'," + s4 + ",null)");
                final ResultSet executeQuery2 = statement.executeQuery("select unit_id,identifier,type,title,launch,prerequisites,sequence from iteminfo where unit_id='" + s2 + "'");
                while (executeQuery2.next()) {
                    executeQuery2.getString(1);
                    final String string5 = executeQuery2.getString(2);
                    final String string6 = executeQuery2.getString(3);
                    executeQuery2.getString(4);
                    final String string7 = executeQuery2.getString(5);
                    final String string8 = executeQuery2.getString(6);
                    final int int2 = executeQuery2.getInt(7);
                    final String s7 = "not attempted";
                    final PreparedStatement prepareStatement2 = connection.prepareStatement("insert into userscoinfo(user_id,unit_id,sco_id,launch,prerequisites,sequence,type,lessonstatus) values(?,?,?,?,?,?,?,?)");
                    synchronized (prepareStatement2) {
                        prepareStatement2.setString(1, s);
                        prepareStatement2.setString(2, s2);
                        prepareStatement2.setString(3, string5);
                        prepareStatement2.setString(4, string7);
                        prepareStatement2.setString(5, string8);
                        prepareStatement2.setInt(6, int2);
                        prepareStatement2.setString(7, string6);
                        prepareStatement2.setString(8, s7);
                        prepareStatement2.executeUpdate();
                    }
                }
            }
            if (!s5.equals("") && s4.equals("")) {
                final int int3 = Integer.parseInt(s5);
                System.out.println("==3==insert into unit_student_association (student_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "',null," + int3 + ")");
                statement2.execute("insert into unit_student_association (student_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "',null," + int3 + ")");
                final ResultSet executeQuery3 = statement.executeQuery("select unit_id,identifier,type,title,launch,prerequisites,sequence from iteminfo where unit_id='" + s2 + "'");
                while (executeQuery3.next()) {
                    executeQuery3.getString(1);
                    final String string9 = executeQuery3.getString(2);
                    final String string10 = executeQuery3.getString(3);
                    executeQuery3.getString(4);
                    final String string11 = executeQuery3.getString(5);
                    final String string12 = executeQuery3.getString(6);
                    final int int4 = executeQuery3.getInt(7);
                    final String s8 = "not attempted";
                    final PreparedStatement prepareStatement3 = connection.prepareStatement("insert into userscoinfo(user_id,unit_id,sco_id,launch,prerequisites,sequence,type,lessonstatus) values(?,?,?,?,?,?,?,?)");
                    synchronized (prepareStatement3) {
                        prepareStatement3.setString(1, s);
                        prepareStatement3.setString(2, s2);
                        prepareStatement3.setString(3, string9);
                        prepareStatement3.setString(4, string11);
                        prepareStatement3.setString(5, string12);
                        prepareStatement3.setInt(6, int4);
                        prepareStatement3.setString(7, string10);
                        prepareStatement3.setString(8, s8);
                        prepareStatement3.executeUpdate();
                    }
                }
            }
            if (!s5.equals("") && !s4.equals("")) {
                final int int5 = Integer.parseInt(s5);
                System.out.println("==4==insert into unit_student_association (student_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "','" + s4 + "'," + int5 + ")");
                statement2.execute("insert into unit_student_association (student_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "','" + s4 + "'," + int5 + ")");
                final ResultSet executeQuery4 = statement.executeQuery("select unit_id,identifier,type,title,launch,prerequisites,sequence from iteminfo where unit_id='" + s2 + "'");
                while (executeQuery4.next()) {
                    executeQuery4.getString(1);
                    final String string13 = executeQuery4.getString(2);
                    final String string14 = executeQuery4.getString(3);
                    executeQuery4.getString(4);
                    final String string15 = executeQuery4.getString(5);
                    final String string16 = executeQuery4.getString(6);
                    final int int6 = executeQuery4.getInt(7);
                    final String s9 = "not attempted";
                    final PreparedStatement prepareStatement4 = connection.prepareStatement("insert into userscoinfo(user_id,unit_id,sco_id,launch,prerequisites,sequence,type,lessonstatus) values( ?, ?, ?, ?, ?, ?, ?, ? )");
                    synchronized (prepareStatement4) {
                        prepareStatement4.setString(1, s);
                        prepareStatement4.setString(2, s2);
                        prepareStatement4.setString(3, string13);
                        prepareStatement4.setString(4, string15);
                        prepareStatement4.setString(5, string16);
                        prepareStatement4.setInt(6, int6);
                        prepareStatement4.setString(7, string14);
                        prepareStatement4.setString(8, s9);
                        prepareStatement4.executeUpdate();
                    }
                }
            }
            statement2.close();
        }
        catch (SQLException ex) {
            System.out.println("Error due to SQL exception : " + ex.getMessage());
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            System.out.println(" error due to java.lang.exception");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return b ? 1 : 0;
    }
    
    public static synchronized int modifyCourseUserAssociation(final String s, final String s2, final String s3, final String s4, final String s5) {
        int n = 0;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select * from unit_student_association where student_id = '" + s + "' and unit_id = '" + s2 + "'");
            if (executeQuery.next()) {
                statement.execute("delete from unit_student_association where student_id = '" + s + "' and unit_id = '" + s2 + "'");
            }
            executeQuery.close();
            statement.close();
            final Statement statement2 = connection.createStatement();
            if (s5.equals("") && s4.equals("")) {
                n = statement2.executeUpdate("insert into unit_student_association (student_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "',null,null)");
            }
            if (s5.equals("") && !s4.equals("")) {
                n = statement2.executeUpdate("insert into unit_student_association (student_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "'," + s4 + ",null)");
            }
            if (!s5.equals("") && s4.equals("")) {
                n = statement2.executeUpdate("insert into unit_student_association (student_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "',null," + Integer.parseInt(s5) + ")");
            }
            if (!s5.equals("") && !s4.equals("")) {
                n = statement2.executeUpdate("insert into unit_student_association (student_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "'," + s4 + "," + Integer.parseInt(s5) + ")");
            }
            statement2.close();
        }
        catch (SQLException ex) {
            System.out.println("Error due to SQL exception : " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println(" error due to java.lang.exception");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        return n;
    }
    
    public static synchronized boolean isForumExists(final String s) {
        boolean b = false;
        String string = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select forum_name from unit_creation_details where unit_id='" + s + "'");
            if (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            executeQuery.close();
            statement.close();
        }
        catch (SQLException ex) {
            System.out.println("Error due to SQL exception : parse()");
            System.out.println("SQL exception : " + ex.toString());
        }
        catch (Exception ex2) {
            System.out.println("Error due to exception : parse()");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
        if (string != null && !string.equals("null")) {
            b = true;
        }
        return b;
    }
    
    public static synchronized void registerForumGroup(final String s, final String s2, final String s3, final String s4) {
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            connection.setAutoCommit(false);
            final Statement statement = connection.createStatement();
            statement.execute("Insert into group_forum_association(group_id, forum_id, registered_by,date_registration) values ('" + s2 + "','" + s + "','" + s3 + "','" + s4 + "')");
            statement.close();
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException nextException) {
            System.out.println("Exception in ForumDatabaseLayer.registerForumGroup()");
            System.out.println("The Error Message - " + nextException.getMessage());
            System.out.println("The Error Code    - " + nextException.getErrorCode());
            while ((nextException = nextException.getNextException()) != null) {
                System.out.println("The Error message - " + nextException.getMessage());
                System.out.println("The Error code is - " + nextException.getErrorCode());
            }
        }
        catch (Exception ex) {
            System.out.println("General exception in ForumDatabaseLayer.registerForumGroup()");
            ex.printStackTrace();
            System.out.println("The message - " + ex.toString());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static synchronized void insertCourseGroupAssociation(final String s, final String s2, final String s3, final String s4, final String s5) {
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final Statement statement2 = connection.createStatement();
            if (s5.equals("") && s4.equals("")) {
                System.out.println("=============1==========insert into unit_group_association (group_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "',null,null)");
                statement.executeUpdate("insert into unit_group_association (group_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "',null,null)");
                final ResultSet executeQuery = statement2.executeQuery("select student_id from student_group_association where group_id='" + s + "'");
                while (executeQuery.next()) {
                    final String string = executeQuery.getString(1);
                    final ResultSet executeQuery2 = statement2.executeQuery("select unit_id,identifier,type,title,launch,prerequisites,sequence from iteminfo where unit_id='" + s2 + "'");
                    while (executeQuery2.next()) {
                        executeQuery2.getString(1);
                        final String string2 = executeQuery2.getString(2);
                        final String string3 = executeQuery2.getString(3);
                        executeQuery2.getString(4);
                        statement2.executeUpdate("insert into  userscoinfo(user_id,unit_id,sco_id,launch,prerequisites,sequence,type,lessonstatus) values('" + string + "','" + s2 + "','" + string2 + "','" + executeQuery2.getString(5) + "','" + executeQuery2.getString(6) + "'," + executeQuery2.getInt(7) + ",'" + string3 + "','" + "not attempted" + "')");
                    }
                }
            }
            if (s5.equals("") && !s4.equals("")) {
                System.out.println("===================2===============insert into unit_group_association (group_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "','" + s4 + "',null)");
                statement.executeUpdate("insert into unit_group_association (group_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "'," + s4 + ",null)");
                final ResultSet executeQuery3 = statement2.executeQuery("select student_id from student_group_association where group_id='" + s + "'");
                while (executeQuery3.next()) {
                    final String string4 = executeQuery3.getString(1);
                    final ResultSet executeQuery4 = statement2.executeQuery("select unit_id,identifier,type,title,launch,prerequisites,sequence from iteminfo where unit_id='" + s2 + "'");
                    while (executeQuery4.next()) {
                        executeQuery4.getString(1);
                        final String string5 = executeQuery4.getString(2);
                        final String string6 = executeQuery4.getString(3);
                        executeQuery4.getString(4);
                        statement2.executeUpdate("insert into  userscoinfo(user_id,unit_id,sco_id,launch,prerequisites,sequence,type,lessonstatus) values('" + string4 + "','" + s2 + "','" + string5 + "','" + executeQuery4.getString(5) + "','" + executeQuery4.getString(6) + "'," + executeQuery4.getInt(7) + ",'" + string6 + "','" + "not attempted" + "')");
                    }
                }
            }
            if (!s5.equals("") && s4.equals("")) {
                final int int1 = Integer.parseInt(s5);
                System.out.println("==========================3==============insert into unit_group_association (group_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "',null," + int1 + ")");
                statement.executeUpdate("insert into unit_group_association (group_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "',null," + int1 + ")");
                final ResultSet executeQuery5 = statement2.executeQuery("select student_id from student_group_association where group_id='" + s + "'");
                while (executeQuery5.next()) {
                    final String string7 = executeQuery5.getString(1);
                    final ResultSet executeQuery6 = statement2.executeQuery("select unit_id,identifier,type,title,launch,prerequisites,sequence from iteminfo where unit_id='" + s2 + "'");
                    while (executeQuery6.next()) {
                        executeQuery6.getString(1);
                        final String string8 = executeQuery6.getString(2);
                        final String string9 = executeQuery6.getString(3);
                        executeQuery6.getString(4);
                        statement2.executeUpdate("insert into  userscoinfo(user_id,unit_id,sco_id,launch,prerequisites,sequence,type,lessonstatus) values('" + string7 + "','" + s2 + "','" + string8 + "','" + executeQuery6.getString(5) + "','" + executeQuery6.getString(6) + "'," + executeQuery6.getInt(7) + ",'" + string9 + "','" + "not attempted" + "')");
                    }
                }
            }
            if (!s5.equals("") && !s4.equals("")) {
                final int int2 = Integer.parseInt(s5);
                System.out.println("======================4================insert into unit_group_association (group_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "','" + s4 + "'," + int2 + ")");
                statement.executeUpdate("insert into unit_group_association (group_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "'," + s4 + "," + int2 + ")");
                final ResultSet executeQuery7 = statement2.executeQuery("select student_id from student_group_association where group_id='" + s + "'");
                while (executeQuery7.next()) {
                    final String string10 = executeQuery7.getString(1);
                    final ResultSet executeQuery8 = statement2.executeQuery("select unit_id,identifier,type,title,launch,prerequisites,sequence from iteminfo where unit_id='" + s2 + "'");
                    while (executeQuery8.next()) {
                        executeQuery8.getString(1);
                        final String string11 = executeQuery8.getString(2);
                        final String string12 = executeQuery8.getString(3);
                        executeQuery8.getString(4);
                        statement2.executeUpdate("insert into  userscoinfo(user_id,unit_id,sco_id,launch,prerequisites,sequence,type,lessonstatus) values('" + string10 + "','" + s2 + "','" + string11 + "','" + executeQuery8.getString(5) + "','" + executeQuery8.getString(6) + "'," + executeQuery8.getInt(7) + ",'" + string12 + "','" + "not attempted" + "')");
                    }
                }
            }
            statement.close();
        }
        catch (SQLException ex) {
            System.out.println(" error due to SQL exception : " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println(" error due to java.lang.exception");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static synchronized void modifyCourseGroupAssociation(final String s, final String s2, final String s3, final String s4, final String s5) {
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select * from unit_group_association where group_id = '" + s + "' and unit_id = '" + s2 + "'");
            if (executeQuery.next()) {
                statement.execute("delete from unit_group_association where group_id = '" + s + "' and unit_id = '" + s2 + "'");
            }
            executeQuery.close();
            statement.close();
            final Statement statement2 = connection.createStatement();
            if (s5.equals("") && s4.equals("")) {
                statement2.executeUpdate("insert into unit_group_association (group_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "',null,null)");
            }
            if (s5.equals("") && !s4.equals("")) {
                statement2.executeUpdate("insert into unit_group_association (group_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "'," + s4 + ",null)");
            }
            if (!s5.equals("") && s4.equals("")) {
                statement2.executeUpdate("insert into unit_group_association (group_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "',null," + Integer.parseInt(s5) + ")");
            }
            if (!s5.equals("") && !s4.equals("")) {
                statement2.executeUpdate("insert into unit_group_association (group_id, unit_id, date_registration, registered_by, access_allowed_till, total_access_time) values ('" + s + "','" + s2 + "',sysdate(),'" + s3 + "'," + s4 + "," + Integer.parseInt(s5) + ")");
            }
            statement2.close();
        }
        catch (SQLException ex) {
            System.out.println(" error due to SQL exception : " + ex.getMessage());
        }
        catch (Exception ex2) {
            System.out.println(" error due to java.lang.exception");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static String getStatusHomePage(final String s) {
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final ResultSet executeQuery = connection.createStatement().executeQuery("select type from home_page_type where unit_id='" + s + "'");
            executeQuery.next();
            string = executeQuery.getString(1);
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
                catch (SQLException ex3) {
                    ex3.printStackTrace();
                }
                catch (Exception ex4) {
                    ex4.printStackTrace();
                }
            }
        }
        return string;
    }
    
    public static String getTitleHomePage(final String s) {
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final ResultSet executeQuery = connection.createStatement().executeQuery("select home_title from home_page_management where unit_id='" + s + "'");
            executeQuery.next();
            string = executeQuery.getString(1);
        }
        catch (SQLException ex5) {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {
                    ex3.printStackTrace();
                }
                catch (Exception ex4) {
                    ex4.printStackTrace();
                }
            }
        }
        return string;
    }
    
    public static String getCourseName(final String s) {
        String string = s;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final ResultSet executeQuery = connection.createStatement().executeQuery("select unit_name from unit_details where unit_id = '" + s + "'");
            if (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            else {
                string = s;
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
                    connection.close();
                }
                catch (SQLException ex3) {
                    ex3.printStackTrace();
                }
                catch (Exception ex4) {
                    ex4.printStackTrace();
                }
            }
        }
        return string;
    }
    
    public static Vector<String[]> getusageInfo(final String s, final String s2) {
        Connection connection = null;
        final Vector<String[]> vector = new Vector<String[]>();
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final ResultSet executeQuery = connection.createStatement().executeQuery("select  date_format(a.date_registration,'%d-%m-%Y'),a.access_allowed_till,a.total_access_time,date_format(b.date_created,'%d-%m-%Y') from  unit_student_association a,unit_creation_details b where a.unit_id='" + s + "' and a.student_id='" + s2 + "' and a.unit_id=b.unit_id");
            while (executeQuery.next()) {
                vector.addElement(new String[] { executeQuery.getString(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4) });
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
                    connection.close();
                }
                catch (SQLException ex3) {
                    ex3.printStackTrace();
                }
                catch (Exception ex4) {
                    ex4.printStackTrace();
                }
            }
        }
        return vector;
    }
    
    public static Vector<Integer> getUserscoInfo(final String s, final String s2) {
        Connection connection = null;
        final Vector<Integer> vector = new Vector<Integer>(4, 4);
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final ResultSet executeQuery = connection.createStatement().executeQuery("select sco_title,sum((TO_DAYS(end_time)-To_DAYS(start_time))*24*3600+(time_to_sec(end_time)-time_to_sec(start_time))), count(*) from sco_user_info where student_id='" + s2 + "'and unit_id='" + s + "' group by sco_id");
            while (executeQuery.next()) {
                vector.addElement(Integer.parseInt(executeQuery.getString(1)));
                vector.addElement(executeQuery.getInt(2));
                vector.addElement(Integer.parseInt(executeQuery.getString(3)));
            }
        }
        catch (SQLException ex) {
            System.out.println("Error due to SQL exception inside NewDataBaseLayer.getUserscoInfo() getUserscoInfo" + ex.getMessage());
            ex.getErrorCode();
            ex.getMessage();
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            System.out.println("Exception inside DataBaseLayer.getUserscoInfo() getUserscoInfo");
            ex2.printStackTrace();
            System.out.println(" printStack is :: " + ex2.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {
                    ex3.printStackTrace();
                }
                catch (Exception ex4) {
                    ex4.printStackTrace();
                }
            }
        }
        return vector;
    }
    
    public static String getNoOfTimeUsed(final String s, final String s2) {
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final ResultSet executeQuery = connection.createStatement().executeQuery("select count(distinct(session_id)) from learner_login_info where unit_id='" + s + "' and student_id = '" + s2 + "'");
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
                    connection.close();
                }
                catch (SQLException ex3) {
                    ex3.printStackTrace();
                }
            }
        }
        return string;
    }
    
    public static Hashtable<String, Vector<String>> getSearchResultFromSCO(final String s, final String s2, final String s3) {
        final Hashtable<String, Vector<String>> hashtable = new Hashtable<String, Vector<String>>();
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final String[] split = s3.split("\\s+");
            for (int i = 0; i < split.length; ++i) {
                System.out.println("--->>>--- select distinct a.title, a.identifier from iteminfo a, userscoinfo b where  a.unit_id=b.unit_id and b.unit_id='" + s + "' and b.user_id='" + s2 + "' " + " and a.type='sco' and a.identifier=b.sco_id and a.title like '%" + split[i] + "%'");
                final ResultSet executeQuery = statement.executeQuery("select distinct a.title, a.identifier from iteminfo a, userscoinfo b where  a.unit_id=b.unit_id and b.unit_id='" + s + "' and b.user_id='" + s2 + "' " + " and a.type='sco' and a.identifier=b.sco_id and a.title like '%" + split[i] + "%'");
                while (executeQuery.next()) {
                    final Vector<String> vector = new Vector<String>();
                    final String string = executeQuery.getString(1);
                    final String string2 = executeQuery.getString(2);
                    vector.addElement(string);
                    vector.addElement(string2);
                    final String string3 = string + string2;
                    if (hashtable.get(string3) == null) {
                        hashtable.put(string3, vector);
                    }
                }
            }
        }
        catch (SQLException ex) {
            System.out.println("------>>>>>>>>>-------" + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return hashtable;
    }
    
    public static synchronized void insert_into_studynamicinfo(final String s, final String s2, final String s3, final String s4) {
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select count(*) from student_dynamic_information where student_id ='" + s + "' and session_id = '" + s2 + "'");
            executeQuery.next();
            if (executeQuery.getInt(1) == 0) {
                connection.createStatement().execute("Insert into student_dynamic_information(student_id, session_id, logged_in_at, course_id) values ('" + s + "','" + s2 + "','" + s3 + "','" + s4 + "')");
            }
            else if (executeQuery.getInt(1) > 0) {
                final Statement statement2 = connection.createStatement();
                if (s4 != null) {
                    statement2.execute("update student_dynamic_information set course_id = '" + s4 + " 'where student_id ='" + s + "' and session_id = '" + s2 + "'");
                }
                else {
                    statement2.execute("update student_dynamic_information set course_id = null 'where student_id ='" + s + "' and session_id = '" + s2 + "'");
                }
            }
            executeQuery.close();
            statement.close();
        }
        catch (SQLException ex) {
            System.out.println("Inside DataBaseLayer, insert_into_studynamicinfo(), SQLException!!!!");
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            System.out.println("Inside DataBaseLayer, insert_into_studynamicinfo(), Exception!!!!");
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
    }
    
    public static boolean isCourseContentExists(final String s) {
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            if (connection.createStatement().executeQuery("select unit_id from csformat where unit_id = '" + s + "'").next()) {
                return true;
            }
            return false;
        }
        catch (SQLException ex) {
            System.out.println("------>>>>>>>>>-------" + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return true;
    }
    
    public static synchronized void setScorm(final String s, final String s2, final String s3, final String s4, final String s5) {
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            statement.execute("update userscoinfo set rawscore='" + s5 + "', maxscore='" + s4 + "' where user_id='" + s + "' and unit_id='" + s2 + "'and sco_id='" + s3 + "'");
            statement.close();
        }
        catch (SQLException ex2) {
            System.out.println(" error due to SQL exception");
        }
        catch (Exception ex) {
            System.out.println(" error due to java.lang.exception");
            ex.printStackTrace();
            System.out.println(" printStack is :: " + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex3) {}
            }
        }
    }
    
    public static Vector<String> getTotalUnit(final String s, final String s2) {
        final Vector<String> vector = new Vector<String>();
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select distinct(a.unit_id),a.unit_name from unit_details a,topic_material_details b,usergroup_course_registration c where c.student_id='" + s + "' and  b.course_id='" + s2 + "' and a.unit_id=b.file_name and b.course_id=c.course_id");
            while (executeQuery.next()) {
                vector.addElement(executeQuery.getString(1));
                vector.addElement(executeQuery.getString(2));
            }
            executeQuery.close();
            statement.close();
        }
        catch (SQLException ex) {
            System.out.println("Error due to SQL exception inside DataBaseLayer.getTotalUnit" + ex.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vector;
    }
    
    public static int getMaxSequence(final String s) {
        int int1 = 0;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("SELECT max(sequence) FROM iteminfo where unit_id='" + s + "'");
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
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
        return int1;
    }
    
    public static String getNextIdentifierFromSequence(final String s, final int n) {
        String string = "";
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("SELECT identifier FROM iteminfo where unit_id='" + s + "' and sequence='" + n + "'");
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
    
    public static int getSequence(final String s, final String s2) {
        int int1 = 0;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("SELECT sequence FROM iteminfo where unit_id='" + s + "' and identifier='" + s2 + "'");
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
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
        return int1;
    }
    
    public static synchronized Vector<Vector<String>> getUserGroupDetailsList(final String s) {
        Statement statement = null;
        ResultSet set = null;
        Vector<Vector<String>> vector = null;
        Connection connection = null;
        try {
            connection = DataBaseLayer.ds1.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery("select count(*) from student_group_association where group_id = '" + s + "'");
            int int1 = 0;
            while (set.next()) {
                int1 = set.getInt(1);
            }
            set.close();
            statement.close();
            if (int1 <= 0) {
                return null;
            }
            vector = new Vector<Vector<String>>();
            statement = connection.createStatement();
            set = statement.executeQuery("select a.student_id, concat(b.first_name,' ', b.middle_name,' ',b.sname) from student_group_association a, student_details b where a.group_id = '" + s + "' and a.student_id=b.student_id");
            while (set.next()) {
                final Vector<String> vector2 = new Vector<String>();
                vector2.addElement(set.getString(1));
                vector2.addElement(set.getString(2));
                vector.addElement(vector2);
            }
        }
        catch (SQLException ex2) {
            System.out.println(" error due to SQL exception");
        }
        catch (Exception ex) {
            System.out.println(" error due to java.lang.exception");
            ex.printStackTrace();
            System.out.println(" printStack is :: " + ex.getMessage());
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (set != null) {
                    set.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
            catch (SQLException ex3) {}
        }
        return vector;
    }
    
    static {
        log = new SimpleLogger((Class)DataBaseLayer.class);
        DataBaseLayer.ds = CoreAdminInitHostInfo.ds;
        DataBaseLayer.ds1 = CoreAdminInitHostInfo.ds1;
        DataBaseLayer._Debug = DebugIndicator.ON;
    }
}
