package calendar7;

import javax.sql.*;
import java.sql.*;
import org.grlea.log.*;
import java.util.*;
import comv2.aunwesha.param.*;

public class calDataBaseLayer
{
    private static final SimpleLogger log;
    public static DataSource ds1;
    
    public static synchronized String getMentorName(final String plan_id) {
        String Vcompo = null;
        try {
            final Connection oConn = calDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement1 = oConn.createStatement();
            final ResultSet oRset = statement1.executeQuery("select leds1ession from synchronous_collaboration where id='" + plan_id + "'");
            while (oRset.next()) {
                Vcompo = oRset.getString(1);
            }
            statement1.close();
            oConn.commit();
            oConn.setAutoCommit(true);
            oConn.close();
        }
        catch (SQLException sqlexception) {
            calDataBaseLayer.log.debug("Exception in SceDatabaseLayer.getMentorName()");
            calDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            calDataBaseLayer.log.debug("Exception in SceDatabaseLayer.selectPort()");
            calDataBaseLayer.log.debug("The Error Message - " + ex.getMessage());
        }
        return Vcompo;
    }
    
    public static void PollingDetails(final String poll_id, final String poll_title, final String poll_text, final String ques_type) {
        try {
            final Connection oConn = calDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("insert polling_details(pollid,polltitle,polltext,ismcchoice)values('" + poll_id + "','" + poll_title + "','" + poll_text + "','" + ques_type + "')");
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            calDataBaseLayer.log.fatal("Polling details SQLException:" + sqlexception.getMessage());
            calDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            calDataBaseLayer.log.fatal("Exception:" + exception.getMessage());
            calDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
    }
    
    public static synchronized Vector publicPersonalCalendarNames(final String strtype) {
        final Vector vPublicEvent = new Vector(5, 5);
        try {
            final Connection oConn = calDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select  calendar_name from calendar_keywords1 where  cal_type='" + strtype + "'");
            while (resultset.next()) {
                vPublicEvent.addElement(resultset.getString(1));
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            calDataBaseLayer.log.entry("publicPersonalCalendarNames()");
            calDataBaseLayer.log.error("Exception in CalDataBaseLayer.publicPersonalCalendarNames()");
            calDataBaseLayer.log.error("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            calDataBaseLayer.log.entry("publicPersonalCalendarNames()");
            calDataBaseLayer.log.error("Exception in CalDataBaseLayer.publicPersonalCalendarNames()");
            calDataBaseLayer.log.error("The Error Message - " + exception.getMessage());
        }
        return vPublicEvent;
    }
    
    public static synchronized Vector getCalendarDetailsList() {
        Vector vCourseList = null;
        try {
            final Connection oConn = calDataBaseLayer.ds1.getConnection();
            vCourseList = new Vector();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select calendar_id, calendar_name, date_format(date_created,\"%M %e, %Y %H:%i\"), createdby, modified_by, date_format(date_modified,\"%M %e, %Y %H:%i\"), status  from calendar_keywords1 where cal_type ='S'");
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
                final ResultSet oRset2 = oStmt2.executeQuery("select count(*)  from calendar_authorization where calendar_id = '" + oRset.getString(1) + "'");
                oRset2.next();
                vCourse.addElement(oRset2.getString(1));
                vCourseList.addElement(vCourse);
            }
            oRset.close();
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            calDataBaseLayer.log.entry("setCalendarName()");
            calDataBaseLayer.log.error("getCalendarDetailsList: error due to SQL exception");
            final int errCode = e.getErrorCode();
            final String errMessage = e.getMessage();
        }
        catch (Exception ex) {
            calDataBaseLayer.log.entry("setCalendarName()");
            calDataBaseLayer.log.error("getCalendarDetailsList: error due to SQL exception");
            ex.printStackTrace();
            calDataBaseLayer.log.error(" printStack is :: " + ex.getMessage());
        }
        return vCourseList;
    }
    
    public static synchronized Vector<String[]> getCalendars(final String strtype) {
        final Vector<String[]> vPublicEvent = new Vector<String[]>(5, 5);
        int i = 0;
        try {
            final Connection oConn = calDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            ResultSet resultset = null;
            final Statement statement2 = oConn.createStatement();
            ResultSet resultset2 = null;
            String s1 = null;
            final Vector vCalList = new Vector(3, 3);
            resultset = statement.executeQuery("select calendar_id  from calendar_authorization where usertype= '" + strtype + "' GROUP BY calendar_id");
            while (resultset.next()) {
                final int id = resultset.getInt(1);
                final String[] strcal = new String[2];
                final Statement statement3 = oConn.createStatement();
                final ResultSet resultset3 = statement3.executeQuery("select  calendar_name from calendar_keywords where  calendar_id =" + id);
                while (resultset3.next()) {
                    strcal[0] = "" + id;
                    strcal[1] = resultset3.getString(1);
                    vPublicEvent.addElement(strcal);
                    vCalList.addElement(strcal[0]);
                }
                if (i == 0) {
                    s1 = "'" + strcal[1] + "'";
                }
                else {
                    s1 = s1 + ",'" + strcal[1] + "'";
                }
                ++i;
                resultset3.close();
                statement3.close();
            }
            resultset2 = statement2.executeQuery("select calendar_id  from calendar_group_association group by calendar_id ");
            while (resultset2.next()) {
                final int id = resultset2.getInt(1);
                final String[] strcal = new String[2];
                final Statement statement3 = oConn.createStatement();
                final ResultSet resultset3 = statement3.executeQuery("select  calendar_name from calendar_keywords where  calendar_id =" + id + " and  calendar_name not in(" + s1 + ")");
                while (resultset3.next()) {
                    strcal[0] = "" + id;
                    strcal[1] = resultset3.getString(1);
                    vPublicEvent.addElement(strcal);
                }
                resultset3.close();
                statement3.close();
            }
            resultset.close();
            statement.close();
            resultset2.close();
            statement2.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return vPublicEvent;
    }
    
    public static synchronized boolean setCalendarName(final String c_id, final String s_id, final String sDate, final String sTime, final String eDate, final String eTime, final String stbody, final Vector<String> vRepeat, final Vector<String> vReminder, final int condition) {
        boolean count = false;
        System.out.println("----------condition----------" + condition);
        System.out.println("-----outside-----vRepeat----------" + vRepeat);
        System.out.println("-----outside-----vReminder--------" + vReminder);
        try {
            final Connection oConn = calDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final Statement statement1 = oConn.createStatement();
            String r = null;
            int size = 0;
            System.out.println("-----inside-----vRepeat----------" + vRepeat);
            System.out.println("-----inside-----vReminder--------" + vReminder);
            int bb = 0;
            int mm = 0;
            int aa = 0;
            switch (condition) {
                case 0: {
                    if (eDate == null) {
                        System.out.println("--0-->>>>----No End Date--setCalendarName()----insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,endtime,calendar_desc,public) values ('" + c_id + "','" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eTime + "','" + stbody + "','T')");
                        oStmt.execute("insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,endtime,calendar_desc,public) values ('" + c_id + "','" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eTime + "','" + stbody + "','T')");
                        break;
                    }
                    System.out.println("--0-->>>>----setCalendarName()----insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc,public) values ('" + c_id + "','" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "','T')");
                    oStmt.execute("insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc,public) values ('" + c_id + "','" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "','T')");
                    break;
                }
                case 1: {
                    bb = 0;
                    mm = 0;
                    aa = 0;
                    size = vRepeat.size();
                    if (size < 4) {
                        r = vRepeat.elementAt(0);
                        final String br = vRepeat.elementAt(1);
                        final String af = vRepeat.elementAt(2);
                        bb = Integer.parseInt(br);
                        aa = Integer.parseInt(af);
                    }
                    else {
                        r = vRepeat.elementAt(0);
                        final String br = vRepeat.elementAt(1);
                        final String mr = vRepeat.elementAt(2);
                        final String ar = vRepeat.elementAt(3);
                        bb = Integer.parseInt(br);
                        mm = Integer.parseInt(mr);
                        aa = Integer.parseInt(ar);
                    }
                    if (eDate == null) {
                        System.out.println("--1-->>>>----setCalendarName()----insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,endtime,calendar_desc,repeat_type,public) values ('" + c_id + "','" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eTime + "','" + stbody + "','" + r + "','T')");
                        oStmt.execute("insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,endtime,calendar_desc,repeat_type,public) values ('" + c_id + "','" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eTime + "','" + stbody + "','" + r + "','T')");
                    }
                    else {
                        System.out.println("--1-->>>>----setCalendarName()----insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc,repeat_type,public) values ('" + c_id + "','" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "','" + r + "','T')");
                        oStmt.execute("insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc,repeat_type,public) values ('" + c_id + "','" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "','" + r + "','T')");
                    }
                    final ResultSet resultset = statement1.executeQuery("select max(event_id) from calendar_events");
                    resultset.next();
                    final int eventid = resultset.getInt(1);
                    System.out.println("--1-->>>>----setCalendarName()----insert into calendar_repeat(event_id,repeating_every,repeating_day,repeating_month) values (" + eventid + ", " + bb + "," + mm + " ," + aa + ")");
                    oStmt.execute("insert into calendar_repeat(event_id,repeating_every,repeating_day,repeating_month) values (" + eventid + ", " + bb + "," + mm + " ," + aa + ")");
                    break;
                }
                case 2: {
                    r = vReminder.elementAt(0);
                    final String br2 = vReminder.elementAt(1);
                    final String af2 = vReminder.elementAt(2);
                    bb = Integer.parseInt(br2);
                    aa = Integer.parseInt(af2);
                    System.out.println("--2-->>>>----setCalendarName()----insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc,reminder_type,public) values ('" + c_id + "','" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "','" + r + "','T')");
                    oStmt.execute("insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc,reminder_type,public) values ('" + c_id + "','" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "','" + r + "','T')");
                    final ResultSet resultset = statement1.executeQuery("select max(event_id) from calendar_events");
                    resultset.next();
                    final int eventid = resultset.getInt(1);
                    System.out.println("--2-->>>>----setCalendarName()----insert into calendar_reminder(event_id,reminder_f,reminder_e) values (" + eventid + ", " + bb + " ," + aa + ")");
                    oStmt.execute("insert into calendar_reminder(event_id,reminder_f,reminder_e) values (" + eventid + ", " + bb + " ," + aa + ")");
                    break;
                }
                case 3: {
                    mm = 0;
                    size = vRepeat.size();
                    System.out.println(" size =" + size);
                    if (size < 4) {
                        r = vRepeat.elementAt(0);
                        final String br2 = vRepeat.elementAt(1);
                        final String af2 = vRepeat.elementAt(2);
                        bb = Integer.parseInt(br2);
                        aa = Integer.parseInt(af2);
                    }
                    else {
                        r = vRepeat.elementAt(0);
                        final String br2 = vRepeat.elementAt(1);
                        final String mr2 = vRepeat.elementAt(2);
                        final String ar2 = vRepeat.elementAt(3);
                        bb = Integer.parseInt(br2);
                        mm = Integer.parseInt(mr2);
                        aa = Integer.parseInt(ar2);
                    }
                    final String rim = vReminder.elementAt(0);
                    final String rimbb = vReminder.elementAt(1);
                    final String rimaa = vReminder.elementAt(2);
                    final int rbb = Integer.parseInt(rimbb);
                    final int raa = Integer.parseInt(rimaa);
                    if (eDate == null) {
                        System.out.println("--3-->>>>--No End date--setCalendarName()--insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,endtime,calendar_desc,repeat_type,reminder_type,public) values ('" + c_id + "','" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eTime + "','" + stbody + "','" + r + "','" + rim + "','T')");
                        oStmt.execute("insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,endtime,calendar_desc,repeat_type,reminder_type,public) values ('" + c_id + "','" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eTime + "','" + stbody + "','" + r + "','" + rim + "','T')");
                    }
                    else {
                        System.out.println("--3-->>>>----setCalendarName()--insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc,repeat_type,reminder_type,public) values ('" + c_id + "','" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "','" + r + "','" + rim + "','T')");
                        oStmt.execute("insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc,repeat_type,reminder_type,public) values ('" + c_id + "','" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "','" + r + "','" + rim + "','T')");
                    }
                    final ResultSet resultset = statement1.executeQuery("select max(event_id) from calendar_events");
                    resultset.next();
                    final int eventid = resultset.getInt(1);
                    System.out.println("--3-->>>>----setCalendarName()--insert into calendar_reminder(event_id,reminder_f,reminder_e) values (" + eventid + ", " + rbb + " ," + raa + ")");
                    System.out.println("--3-->>>>----setCalendarName()--insert into calendar_repeat(event_id,repeating_every,repeating_day,repeating_month) values (" + eventid + "," + bb + "," + mm + "," + aa + ")");
                    oStmt.execute("insert into calendar_reminder(event_id,reminder_f,reminder_e) values (" + eventid + ", " + rbb + " ," + raa + ")");
                    oStmt.execute("insert into calendar_repeat(event_id,repeating_every,repeating_day,repeating_month) values (" + eventid + "," + bb + "," + mm + "," + aa + ")");
                    break;
                }
            }
            oStmt.close();
            statement1.close();
            count = false;
            oConn.close();
        }
        catch (SQLException e) {
            System.out.println("Exception in calDataBaseLayer.setCalendarName()");
            System.out.println("--1-->>----The Error Message - " + e.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in calDataBaseLayer.setCalendarName()");
            System.out.println("--2-->>----The Error Message - " + ex.getMessage());
            ex.printStackTrace();
        }
        return count;
    }
    
    public static synchronized Vector<String> getCalendarDetails(final String event_id) {
        final Vector<String> vCalendar = new Vector();
        try {
            final Connection oConn = calDataBaseLayer.ds1.getConnection();
            Statement oStmt = oConn.createStatement();
            ResultSet oRset = oStmt.executeQuery("select calendar_id, date_created, startdate, starttime, enddate, endtime, calendar_desc, repeat_type, reminder_type from calendar_events where event_id='" + event_id + "'");
            while (oRset.next()) {
                vCalendar.addElement(oRset.getString(1));
                vCalendar.addElement(oRset.getString(2));
                vCalendar.addElement(oRset.getString(3));
                vCalendar.addElement(oRset.getString(4));
                vCalendar.addElement(oRset.getString(5));
                vCalendar.addElement(oRset.getString(6));
                vCalendar.addElement(oRset.getString(7));
                vCalendar.addElement(oRset.getString(8));
                vCalendar.addElement(oRset.getString(9));
            }
            System.out.println("--No end date---" + vCalendar.elementAt(4));
            oRset.close();
            oStmt.close();
            if (!vCalendar.elementAt(7).equalsIgnoreCase("N")) {
                oStmt = oConn.createStatement();
                if (vCalendar.elementAt(7).equalsIgnoreCase("M")) {
                    oRset = oStmt.executeQuery("select repeat_id, repeating_every, repeating_day, repeating_month from calendar_repeat where event_id='" + event_id + "'");
                    while (oRset.next()) {
                        vCalendar.addElement(oRset.getString(1));
                        vCalendar.addElement(oRset.getString(2));
                        vCalendar.addElement(oRset.getString(3));
                        vCalendar.addElement(oRset.getString(4));
                    }
                }
                if (vCalendar.elementAt(7).equalsIgnoreCase("D")) {
                    oRset = oStmt.executeQuery("select repeat_id, repeating_every, repeating_month from calendar_repeat where event_id='" + event_id + "'");
                    while (oRset.next()) {
                        vCalendar.addElement(oRset.getString(1));
                        vCalendar.addElement(oRset.getString(2));
                        vCalendar.addElement(oRset.getString(3));
                    }
                }
                oRset.close();
                oStmt.close();
            }
            oStmt = oConn.createStatement();
            oRset = oStmt.executeQuery("select reminder_id, reminder_f, reminder_e from calendar_reminder where event_id='" + event_id + "'");
            while (oRset.next()) {
                vCalendar.addElement(oRset.getString(1));
                vCalendar.addElement(oRset.getString(2));
                vCalendar.addElement(oRset.getString(3));
            }
            oRset.close();
            oStmt.close();
            oConn.close();
        }
        catch (SQLException sx) {
            sx.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return vCalendar;
    }
    
    public static synchronized boolean isRepeatExists(final int event_id) {
        boolean b = false;
        try {
            final Connection oConn = calDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement1 = oConn.createStatement();
            final ResultSet oRset = statement1.executeQuery("select repeat_id from calendar_repeat where event_id='" + event_id + "'");
            while (oRset.next()) {
                b = true;
            }
            statement1.close();
            oConn.commit();
            oConn.setAutoCommit(true);
            oConn.close();
        }
        catch (SQLException sqlexception) {
            calDataBaseLayer.log.debug("Exception in calDatabaseLayer.isRepeatExists()");
            calDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            calDataBaseLayer.log.debug("Exception in calDatabaseLayer.isRepeatExists()");
            calDataBaseLayer.log.debug("The Error Message - " + ex.getMessage());
        }
        return b;
    }
    
    public static synchronized boolean isRemainderExists(final int event_id) {
        boolean b = false;
        try {
            final Connection oConn = calDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement1 = oConn.createStatement();
            final ResultSet oRset = statement1.executeQuery("select reminder_id from calendar_reminder where event_id='" + event_id + "'");
            while (oRset.next()) {
                b = true;
            }
            statement1.close();
            oConn.commit();
            oConn.setAutoCommit(true);
            oConn.close();
        }
        catch (SQLException sqlexception) {
            calDataBaseLayer.log.debug("Exception in calDatabaseLayer.isRemainderExists()");
            calDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            calDataBaseLayer.log.debug("Exception in calDatabaseLayer.isRemainderExists()");
            calDataBaseLayer.log.debug("The Error Message - " + ex.getMessage());
        }
        return b;
    }
    
    public static synchronized boolean updateCalendarName(final String event_id, final String c_id, final String s_id, final String sDate, final String sTime, final String eDate, final String eTime, final String stbody, final Vector<String> vRepeat, final Vector<String> vReminder, final int condition) {
        System.out.println("condition--updateCalendarName-eDate--" + condition + "-----" + eDate);
        boolean count = false;
        final int e_id = Integer.parseInt(event_id);
        final int cid = Integer.parseInt(c_id);
        try {
            final Connection oConn = calDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            final Statement statement1 = oConn.createStatement();
            switch (condition) {
                case 0: {
                    if (eDate == null) {
                        oStmt.execute("update calendar_events set calendar_id =" + cid + ",createdby='" + s_id + "',date_modified=sysdate(),startdate='" + sDate + "',starttime ='" + sTime + "',enddate='0000-00-00',endtime ='" + eTime + "',calendar_desc ='" + stbody + "',repeat_type = 'N',reminder_type ='N' where event_id =" + e_id);
                    }
                    else {
                        oStmt.execute("update calendar_events set calendar_id =" + cid + ",createdby='" + s_id + "',date_modified=sysdate(),startdate='" + sDate + "',starttime ='" + sTime + "',enddate='" + eDate + "',endtime ='" + eTime + "',calendar_desc ='" + stbody + "',repeat_type = 'N',reminder_type ='N' where event_id =" + e_id);
                    }
                    if (isRepeatExists(e_id)) {
                        oStmt.execute("delete from calendar_repeat where  event_id=" + e_id);
                    }
                    if (isRemainderExists(e_id)) {
                        oStmt.execute("delete from calendar_reminder where  event_id=" + e_id);
                        break;
                    }
                    break;
                }
                case 1: {
                    String r = null;
                    final int size = vRepeat.size();
                    int bb = 0;
                    int mm = 0;
                    int aa = 0;
                    if (size < 4) {
                        r = vRepeat.elementAt(0);
                        final String br = vRepeat.elementAt(1);
                        final String af = vRepeat.elementAt(2);
                        bb = Integer.parseInt(br);
                        aa = Integer.parseInt(af);
                    }
                    else {
                        r = vRepeat.elementAt(0);
                        final String br = vRepeat.elementAt(1);
                        final String mr = vRepeat.elementAt(2);
                        final String ar = vRepeat.elementAt(3);
                        bb = Integer.parseInt(br);
                        mm = Integer.parseInt(mr);
                        aa = Integer.parseInt(ar);
                    }
                    if (eDate == null) {
                        oStmt.execute("update calendar_events set calendar_id =" + cid + ",createdby='" + s_id + "',date_modified=sysdate(),startdate='" + sDate + "',starttime ='" + sTime + "',enddate='0000-00-00',endtime ='" + eTime + "',calendar_desc ='" + stbody + "',repeat_type = '" + r + "',reminder_type ='N' where event_id =" + e_id);
                    }
                    else {
                        oStmt.execute("update calendar_events set calendar_id =" + cid + ",createdby='" + s_id + "',date_modified=sysdate(),startdate='" + sDate + "',starttime ='" + sTime + "',enddate='" + eDate + "',endtime ='" + eTime + "',calendar_desc ='" + stbody + "',repeat_type = '" + r + "',reminder_type ='N' where event_id =" + e_id);
                    }
                    if (isRepeatExists(e_id)) {
                        statement1.execute("update calendar_repeat set repeating_every=" + bb + ",repeating_day=" + mm + ",repeating_month =" + aa + " where event_id =" + e_id);
                    }
                    else {
                        statement1.execute("insert into calendar_repeat(event_id,repeating_every,repeating_day,repeating_month) values (" + e_id + ", " + bb + "," + mm + " ," + aa + ")");
                    }
                    if (isRemainderExists(e_id)) {
                        statement1.execute("delete from calendar_reminder where  event_id=" + e_id);
                        break;
                    }
                    break;
                }
                case 2: {
                    final String r = vReminder.elementAt(0);
                    final String br2 = vReminder.elementAt(1);
                    final String af2 = vReminder.elementAt(2);
                    final int bb2 = Integer.parseInt(br2);
                    final int aa = Integer.parseInt(af2);
                    if (eDate == null) {
                        oStmt.execute("update calendar_events set calendar_id =" + cid + ",createdby='" + s_id + "',date_modified=sysdate(),startdate='" + sDate + "',starttime ='" + sTime + "',enddate='0000-00-00',endtime ='" + eTime + "',calendar_desc ='" + stbody + "',reminder_type = '" + r + "',repeat_type = 'N' where event_id =" + e_id);
                    }
                    else {
                        oStmt.execute("update calendar_events set calendar_id =" + cid + ",createdby='" + s_id + "',date_modified=sysdate(),startdate='" + sDate + "',starttime ='" + sTime + "',enddate='" + eDate + "',endtime ='" + eTime + "',calendar_desc ='" + stbody + "',repeat_type = '" + r + "',repeat_type = 'N' where event_id =" + e_id);
                    }
                    if (isRepeatExists(e_id)) {
                        oStmt.execute("delete from calendar_repeat where  event_id=" + e_id);
                    }
                    if (isRemainderExists(e_id)) {
                        oStmt.execute("update calendar_reminder set reminder_f=" + bb2 + ",reminder_e=" + aa + " where event_id =" + e_id);
                        break;
                    }
                    System.out.println("--2-->>>>----setCalendarName()----insert into calendar_reminder(event_id,reminder_f,reminder_e) values (" + e_id + ", " + bb2 + " ," + aa + ")");
                    oStmt.execute("insert into calendar_reminder(event_id,reminder_f,reminder_e) values (" + e_id + ", " + bb2 + " ," + aa + ")");
                    break;
                }
                case 3: {
                    String r = null;
                    final int size = vRepeat.size();
                    System.out.println(" size =" + size);
                    int bb = 0;
                    int mm = 0;
                    int aa = 0;
                    if (size < 4) {
                        r = vRepeat.elementAt(0);
                        final String br = vRepeat.elementAt(1);
                        final String af = vRepeat.elementAt(2);
                        bb = Integer.parseInt(br);
                        aa = Integer.parseInt(af);
                    }
                    else {
                        r = vRepeat.elementAt(0);
                        final String br = vRepeat.elementAt(1);
                        final String mr = vRepeat.elementAt(2);
                        final String ar = vRepeat.elementAt(3);
                        bb = Integer.parseInt(br);
                        mm = Integer.parseInt(mr);
                        aa = Integer.parseInt(ar);
                    }
                    final String rim = vReminder.elementAt(0);
                    final String rimbb = vReminder.elementAt(1);
                    final String rimaa = vReminder.elementAt(2);
                    final int rbb = Integer.parseInt(rimbb);
                    final int raa = Integer.parseInt(rimaa);
                    if (eDate == null) {
                        oStmt.execute("update calendar_events set calendar_id =" + cid + ",createdby='" + s_id + "',date_modified=sysdate(),startdate='" + sDate + "',starttime ='" + sTime + "',enddate='0000-00-00',endtime ='" + eTime + "',calendar_desc ='" + stbody + "',reminder_type = '" + rim + "',repeat_type = '" + r + "' where event_id =" + e_id);
                    }
                    else {
                        oStmt.execute("update calendar_events set calendar_id =" + cid + ",createdby='" + s_id + "',date_modified=sysdate(),startdate='" + sDate + "',starttime ='" + sTime + "',enddate='" + eDate + "',endtime ='" + eTime + "',calendar_desc ='" + stbody + "',reminder_type = '" + rim + "',repeat_type = '" + r + "' where event_id =" + e_id);
                    }
                    if (isRepeatExists(e_id)) {
                        statement1.execute("update calendar_repeat set repeating_every=" + bb + ",repeating_day=" + mm + ",repeating_month =" + aa + " where event_id =" + e_id);
                    }
                    else {
                        statement1.execute("insert into calendar_repeat(event_id,repeating_every,repeating_day,repeating_month) values (" + e_id + ", " + bb + "," + mm + " ," + aa + ")");
                    }
                    if (isRemainderExists(e_id)) {
                        oStmt.execute("update calendar_reminder set reminder_f=" + rbb + ",reminder_e=" + raa + " where event_id =" + e_id);
                        break;
                    }
                    System.out.println("--2-->>>>----setCalendarName()----insert into calendar_reminder(event_id,reminder_f,reminder_e) values (" + e_id + ", " + rbb + " ," + raa + ")");
                    oStmt.execute("insert into calendar_reminder(event_id,reminder_f,reminder_e) values (" + e_id + ", " + rbb + " ," + raa + ")");
                    break;
                }
            }
            oStmt.close();
            statement1.close();
            oConn.commit();
            oConn.setAutoCommit(true);
            count = false;
            oConn.close();
        }
        catch (SQLException e) {
            System.out.println("Exception in calDataBaseLayer.updateCalendarName()");
            System.out.println("The Error Message - " + e.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in calDataBaseLayer.updateCalendarName()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        return count;
    }
    
    public static synchronized void deleteCalendarName(final String event_id) {
        final int id = Integer.parseInt(event_id);
        try {
            final Connection oConn = calDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            final Statement oStmt3 = oConn.createStatement();
            oStmt3.execute("delete from calendar_reminder where  event_id=" + id);
            oStmt2.execute("delete from calendar_repeat where  event_id=" + id);
            oStmt.execute("delete from calendar_events where  event_id=" + id);
            oStmt.close();
            oStmt2.close();
            oStmt3.close();
            oConn.commit();
            oConn.setAutoCommit(true);
            oConn.close();
        }
        catch (SQLException sqlexception) {
            calDataBaseLayer.log.entry("deleteCalendarName()");
            calDataBaseLayer.log.error("Exception in CalDataBaseLayer.deleteCalendarName()");
            calDataBaseLayer.log.error("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            calDataBaseLayer.log.entry("deleteCalendarName()");
            calDataBaseLayer.log.error("Exception in CalDataBaseLayer.deleteCalendarName()");
            calDataBaseLayer.log.error("The Error Message - " + exception.getMessage());
        }
    }
    
    static {
        log = new SimpleLogger((Class)calDataBaseLayer.class);
        calDataBaseLayer.ds1 = CoreAdminInitHostInfo.ds1;
    }
}
