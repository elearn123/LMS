package calendar.calendar;

import org.grlea.log.*;
import javax.sql.*;
import java.util.*;
import java.sql.*;
import java.io.*;
import comv2.aunwesha.param.*;

public class CalDataBaseLayer
{
    private static final SimpleLogger log;
    public static DataSource ds;
    
    public static synchronized Vector<String[]> publicPersonalCalendarName(final String s_id, final String strtype) {
        final Vector<String[]> vPublicEvent = new Vector<String[]>(5, 5);
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select calendar_id , calendar_name from calendar_keywords where createdby ='" + s_id + "' and  cal_type= '" + strtype + "'");
            while (resultset.next()) {
                final String[] strcal = new String[2];
                final int cal_id = resultset.getInt(1);
                strcal[0] = new Integer(cal_id).toString();
                strcal[1] = resultset.getString(2);
                vPublicEvent.addElement(strcal);
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            CalDataBaseLayer.log.entry("publicPersonalCalendarName()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.publicPersonalCalendarName()");
            CalDataBaseLayer.log.error("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            CalDataBaseLayer.log.entry("publicPersonalCalendarName()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.publicPersonalCalendarName()");
            CalDataBaseLayer.log.error("The Error Message - " + exception.getMessage());
        }
        return vPublicEvent;
    }
    
    public static synchronized Vector<String[]> publicSharedCalendarName(final String s_id, final String strtype) {
        final Vector<String[]> vPublicEvent = new Vector<String[]>(5, 5);
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            final Statement statement1 = oConn.createStatement();
            final ResultSet oRset = statement1.executeQuery("select calendar_id  from calendar_authorization where student_id ='" + s_id + "'");
            while (oRset.next()) {
                final Statement statement2 = oConn.createStatement();
                final int c_id = oRset.getInt(1);
                final ResultSet resultset = statement2.executeQuery("select calendar_id , calendar_name from calendar_keywords where calendar_id =" + c_id);
                while (resultset.next()) {
                    final String[] strcal = new String[2];
                    final int cal_id = resultset.getInt(1);
                    strcal[0] = new Integer(cal_id).toString();
                    strcal[1] = resultset.getString(2);
                    vPublicEvent.addElement(strcal);
                }
                statement2.close();
            }
            oRset.close();
            statement1.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            CalDataBaseLayer.log.entry("publicSharedCalendarName()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.publicSharedCalendarName()");
            CalDataBaseLayer.log.error("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            CalDataBaseLayer.log.entry("publicSharedCalendarName()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.publicSharedCalendarName()");
            CalDataBaseLayer.log.error("The Error Message - " + exception.getMessage());
        }
        return vPublicEvent;
    }
    
    public static synchronized Vector publicPersonalCalendarNames(final String strtype) {
        final Vector vPublicEvent = new Vector(5, 5);
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select  calendar_name from calendar_keywords where  cal_type='" + strtype + "'");
            while (resultset.next()) {
                vPublicEvent.addElement(resultset.getString(1));
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            CalDataBaseLayer.log.entry("publicPersonalCalendarNames()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.publicPersonalCalendarNames()");
            CalDataBaseLayer.log.error("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            CalDataBaseLayer.log.entry("publicPersonalCalendarNames()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.publicPersonalCalendarNames()");
            CalDataBaseLayer.log.error("The Error Message - " + exception.getMessage());
        }
        return vPublicEvent;
    }
    
    public static synchronized Vector getPersonalCalendarList(final String strtype) {
        final Vector vPublicEvent = new Vector(5, 5);
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select  calendar_desc from calendar_events where  public='" + strtype + "'");
            while (resultset.next()) {
                vPublicEvent.addElement(resultset.getString(1));
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            CalDataBaseLayer.log.entry("getPersonalCalendarList()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.getPersonalCalendarList()");
            CalDataBaseLayer.log.error("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            CalDataBaseLayer.log.entry("getPersonalCalendarList()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.getPersonalCalendarList()");
            CalDataBaseLayer.log.error("The Error Message - " + exception.getMessage());
        }
        return vPublicEvent;
    }
    
    public static synchronized String getCalendarName(final int cal_id) {
        String strCalname = null;
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select calendar_name from calendar_keywords where  calendar_id =" + cal_id);
            while (resultset.next()) {
                strCalname = resultset.getString(1);
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            CalDataBaseLayer.log.entry("getCalendarName()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.getCalendarName()");
            CalDataBaseLayer.log.error("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            CalDataBaseLayer.log.entry("getCalendarName()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.getCalendarName()");
            CalDataBaseLayer.log.error("The Error Message - " + exception.getMessage());
        }
        return strCalname;
    }
    
    public static synchronized String[] getCalendarName(final String s_id, final String p) {
        final String[] strcal = new String[2];
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select min(calendar_id),calendar_name from calendar_keywords where createdby ='" + s_id + "' and cal_type='" + p + "' GROUP BY cal_type");
            while (resultset.next()) {
                final int calendar_id = resultset.getInt(1);
                strcal[0] = "" + calendar_id;
                strcal[1] = resultset.getString(2);
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            CalDataBaseLayer.log.entry("getCalendarName()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.getCalendarName[]()");
            CalDataBaseLayer.log.error("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            CalDataBaseLayer.log.entry("getCalendarName()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.getCalendarName[]()");
            CalDataBaseLayer.log.error("The Error Message - " + exception.getMessage());
        }
        return strcal;
    }
    
    public static synchronized Vector getPersonalDataList(final String e_id, final String t) {
        final Vector vCalendar = new Vector(3, 3);
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            final Statement oStmt = oConn.createStatement();
            final int id = Integer.parseInt(e_id);
            System.out.println("--------1 0f 3--------------------select  event_id,calendar_id,calendar_desc,startdate ,time_format(starttime,\"%h%i%p\"),repeat_type,enddate,time_format(endtime,\"%h%i%p\"),reminder_type from   calendar_events where event_id =" + id + " and public='" + t + "'");
            final ResultSet oRset = oStmt.executeQuery("select  event_id,calendar_id,calendar_desc,startdate ,time_format(starttime,\"%h%i%p\"),repeat_type,enddate,time_format(endtime,\"%h%i%p\"),reminder_type from   calendar_events where event_id =" + id + " and public='" + t + "'");
            while (oRset.next()) {
                vCalendar.addElement("" + oRset.getInt(1));
                vCalendar.addElement("" + oRset.getInt(2));
                vCalendar.addElement(oRset.getString(3));
                vCalendar.addElement(oRset.getString(4));
                vCalendar.addElement(oRset.getString(5));
                final String[] strRepeat = { "0", "0", "0", "0" };
                strRepeat[0] = oRset.getString(6);
                final Statement oStmt2 = oConn.createStatement();
                System.out.println("----------2 of 3----select repeating_every,repeating_month,repeating_day from calendar_repeat where event_id =" + id);
                final ResultSet oRset2 = oStmt2.executeQuery("select repeating_every,repeating_month,repeating_day from calendar_repeat where event_id =" + id);
                while (oRset2.next()) {
                    strRepeat[1] = oRset2.getString(1);
                    strRepeat[2] = oRset2.getString(2);
                    strRepeat[3] = oRset2.getString(3);
                }
                vCalendar.addElement(strRepeat);
                oRset2.close();
                oStmt2.close();
                vCalendar.addElement(oRset.getString(7));
                vCalendar.addElement(oRset.getString(8));
                final String[] strRiminder = { "0", "0", "0" };
                strRiminder[0] = oRset.getString(9);
                final Statement oStmt3 = oConn.createStatement();
                System.out.println("---------3 of 3---------------select reminder_f,reminder_e from calendar_reminder where event_id =" + id);
                final ResultSet oRset3 = oStmt3.executeQuery("select reminder_f,reminder_e from calendar_reminder where event_id =" + id);
                while (oRset3.next()) {
                    strRiminder[1] = oRset3.getString(1);
                    strRiminder[2] = oRset3.getString(2);
                }
                vCalendar.addElement(strRiminder);
                oRset2.close();
                oStmt2.close();
            }
            oRset.close();
            oStmt.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            CalDataBaseLayer.log.entry("getPersonalDataList()-----------:" + sqlexception);
            sqlexception.printStackTrace();
        }
        catch (Exception exception) {
            CalDataBaseLayer.log.entry("getPersonalDataList()-----------:" + exception);
            exception.printStackTrace();
        }
        return vCalendar;
    }
    
    public static synchronized Vector<String[]> dayviewInfo(final int c_id, final String dt, final String s_id) {
        final Vector<String[]> vCalendar = new Vector<String[]>(3, 3);
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select  event_id ,time_format(starttime,\"%h:%i %p\"),calendar_desc,enddate,time_format(endtime,\"%h:%i %p\") from   calendar_events where startdate ='" + dt + "' and createdby='" + s_id + "'   and calendar_id=" + c_id + " order by starttime");
            while (oRset.next()) {
                final String[] strdesc = { "" + oRset.getInt(1), oRset.getString(2), oRset.getString(3), oRset.getString(4), oRset.getString(5) };
                vCalendar.addElement(strdesc);
            }
            oStmt.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            CalDataBaseLayer.log.entry("dayviewInfo()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.dayviewInfo()");
            CalDataBaseLayer.log.error("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            CalDataBaseLayer.log.entry("dayviewInfo()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.dayviewInfo()");
            CalDataBaseLayer.log.error("The Error Message - " + exception.getMessage());
        }
        return vCalendar;
    }
    
    public static synchronized Vector<String[]> dayviewInfo(final int c_id, final String dt) {
        final Vector<String[]> vCalendar = new Vector<String[]>(3, 3);
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            final Statement oStmt = oConn.createStatement();
            System.out.println("======select  event_id ,time_format(starttime,\"%h:%i %p\"),calendar_desc,enddate,time_format(endtime,\"%h:%i %p\") from   calendar_events where startdate ='" + dt + "'   and calendar_id=" + c_id + " order by starttime");
            final ResultSet oRset = oStmt.executeQuery("select  event_id ,time_format(starttime,\"%h:%i %p\"),calendar_desc,enddate,time_format(endtime,\"%h:%i %p\") from   calendar_events where startdate ='" + dt + "'   and calendar_id=" + c_id + " order by starttime");
            while (oRset.next()) {
                final String[] strdesc = { "" + oRset.getInt(1), oRset.getString(2), oRset.getString(3), oRset.getString(4), oRset.getString(5) };
                vCalendar.addElement(strdesc);
            }
            oStmt.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            CalDataBaseLayer.log.entry("dayviewInfo()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.dayviewInfo()");
            CalDataBaseLayer.log.error("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            CalDataBaseLayer.log.entry("dayviewInfo()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.dayviewInfo()");
            CalDataBaseLayer.log.error("The Error Message - " + exception.getMessage());
        }
        return vCalendar;
    }
    
    public static synchronized void deleteCalendarName(final String event_id) {
        final int id = Integer.parseInt(event_id);
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            final Statement oStmt3 = oConn.createStatement();
            oStmt3.execute("delete from calendar_reminder where  event_id=" + id);
            oStmt2.execute("delete from calendar_reminder where  event_id=" + id);
            oStmt.execute("delete from calendar_events where  event_id=" + id);
            oStmt.close();
            oStmt2.close();
            oStmt3.close();
            oConn.commit();
            oConn.setAutoCommit(true);
            oConn.close();
        }
        catch (SQLException sqlexception) {
            CalDataBaseLayer.log.entry("deleteCalendarName()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.deleteCalendarName()");
            CalDataBaseLayer.log.error("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            CalDataBaseLayer.log.entry("deleteCalendarName()");
            CalDataBaseLayer.log.error("Exception in CalDataBaseLayer.deleteCalendarName()");
            CalDataBaseLayer.log.error("The Error Message - " + exception.getMessage());
        }
    }
    
    public static synchronized boolean updateCalendarName(final String event_id, final String c_id, final String s_id, final String sDate, final String sTime, final String eDate, final String eTime, final String stbody, final Vector<String> vRepeat, final Vector<String> vReminder, final int condition) {
        boolean count = false;
        final int e_id = Integer.parseInt(event_id);
        final int cid = Integer.parseInt(c_id);
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            final Statement statement1 = oConn.createStatement();
            switch (condition) {
                case 0: {
                    oStmt.execute("update calendar_events set calendar_id =" + cid + ",createdby='" + s_id + "',date_modified=sysdate(),startdate='" + sDate + "',starttime ='" + sTime + "',enddate='" + eDate + "',endtime ='" + eTime + "',calendar_desc ='" + stbody + "' where event_id =" + e_id);
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
                    oStmt.execute("update calendar_events set calendar_id =" + cid + ",createdby='" + s_id + "',date_modified=sysdate(),startdate='" + sDate + "',starttime ='" + sTime + "',enddate='" + eDate + "',endtime ='" + eTime + "',calendar_desc ='" + stbody + "',repeat_type = '" + r + "' where event_id =" + e_id);
                    statement1.execute("update calendar_repeat set repeating_every=" + bb + ",repeating_day=" + mm + ",repeating_month =" + aa + " where event_id =" + e_id);
                    break;
                }
                case 2: {
                    final String r = vReminder.elementAt(0);
                    final String br2 = vReminder.elementAt(1);
                    final String af2 = vReminder.elementAt(2);
                    final int bb2 = Integer.parseInt(br2);
                    final int aa = Integer.parseInt(af2);
                    oStmt.execute("update calendar_events set calendar_id =" + cid + ",createdby='" + s_id + "',date_modified=sysdate(),startdate='" + sDate + "',starttime ='" + sTime + "',enddate='" + eDate + "',endtime ='" + eTime + "',calendar_desc ='" + stbody + "',reminder_type = '" + r + "' where event_id =" + e_id);
                    oStmt.execute("update calendar_reminder set reminder_f=" + bb2 + ",reminder_e=" + aa + " where event_id =" + e_id);
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
                    oStmt.execute("update calendar_events set calendar_id =" + cid + ",createdby='" + s_id + "',date_modified=sysdate(),startdate='" + sDate + "',starttime ='" + sTime + "',enddate='" + eDate + "',endtime ='" + eTime + "',calendar_desc ='" + stbody + "',reminder_type = '" + rim + "',repeat_type = '" + r + "' where event_id =" + e_id);
                    statement1.execute("update calendar_repeat set repeating_every=" + bb + ",repeating_day=" + mm + ",repeating_month =" + aa + " where event_id =" + e_id);
                    oStmt.execute("update calendar_reminder set reminder_f=" + rbb + ",reminder_e=" + raa + " where event_id =" + e_id);
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
            System.out.println("Exception in CalDataBaseLayer.updateCalendarName()");
            System.out.println("The Error Message - " + e.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in CalDataBaseLayer.updateCalendarName()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        return count;
    }
    
    public static synchronized boolean setCalendarName(final String c_id, final String s_id, final String sDate, final String sTime, final String eDate, final String eTime, final String stbody, final Vector<String> vRepeat, final Vector<String> vReminder, final int condition) {
        final int id = Integer.parseInt(c_id);
        boolean count = false;
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            final Statement statement1 = oConn.createStatement();
            System.out.println("condition-------:" + condition);
            switch (condition) {
                case 0: {
                    System.out.println("-----0-----------insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc) values (" + id + ",'" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "')");
                    oStmt.execute("insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc) values (" + id + ",'" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "')");
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
                    System.out.println("----1---1--------------insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc,repeat_type) values (" + id + ",'" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "','" + r + "')");
                    oStmt.execute("insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc,repeat_type) values (" + id + ",'" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "','" + r + "')");
                    final ResultSet resultset = statement1.executeQuery("select max(event_id) from calendar_events");
                    resultset.next();
                    final int eventid = resultset.getInt(1);
                    System.out.println("----1-----2------------------insert into calendar_repeat(event_id,repeating_every,repeating_day,repeating_month) values (" + eventid + ", " + bb + "," + mm + " ," + aa + ")");
                    oStmt.execute("insert into calendar_repeat(event_id,repeating_every,repeating_day,repeating_month) values (" + eventid + ", " + bb + "," + mm + " ," + aa + ")");
                    break;
                }
                case 2: {
                    final String r = vReminder.elementAt(0);
                    final String br2 = vReminder.elementAt(1);
                    final String af2 = vReminder.elementAt(2);
                    final int bb2 = Integer.parseInt(br2);
                    final int aa = Integer.parseInt(af2);
                    System.out.println("-----2---1------------insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc,reminder_type) values (" + id + ",'" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "','" + r + "')");
                    oStmt.execute("insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc,reminder_type) values (" + id + ",'" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "','" + r + "')");
                    final ResultSet resultset = statement1.executeQuery("select max(event_id) from calendar_events");
                    resultset.next();
                    final int eventid = resultset.getInt(1);
                    System.out.println("-----2---2------------insert into calendar_reminder(event_id,reminder_f,reminder_e) values (" + eventid + ", " + bb2 + " ," + aa + ")");
                    oStmt.execute("insert into calendar_reminder(event_id,reminder_f,reminder_e) values (" + eventid + ", " + bb2 + " ," + aa + ")");
                    break;
                }
                case 3: {
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
                    final String rim = vReminder.elementAt(0);
                    final String rimbb = vReminder.elementAt(1);
                    final String rimaa = vReminder.elementAt(2);
                    final int rbb = Integer.parseInt(rimbb);
                    final int raa = Integer.parseInt(rimaa);
                    System.out.println("-----3------1-------insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc,repeat_type,reminder_type) values (" + id + ",'" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "','" + r + "','" + rim + "')");
                    oStmt.execute("insert into calendar_events(calendar_id,createdby,date_created,date_modified,startdate,starttime,enddate,endtime,calendar_desc,repeat_type,reminder_type) values (" + id + ",'" + s_id + "',sysdate(),sysdate(),'" + sDate + "','" + sTime + "','" + eDate + "','" + eTime + "','" + stbody + "','" + r + "','" + rim + "')");
                    final ResultSet resultset2 = statement1.executeQuery("select max(event_id) from calendar_events");
                    resultset2.next();
                    final int eventid2 = resultset2.getInt(1);
                    oStmt.execute("insert into calendar_reminder(event_id,reminder_f,reminder_e) values (" + eventid2 + ", " + rbb + " ," + raa + ")");
                    oStmt.execute("insert into calendar_repeat(event_id,repeating_every,repeating_day,repeating_month) values (" + eventid2 + ", " + bb + "," + mm + " ," + aa + ")");
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
            CalDataBaseLayer.log.debug("The Error Message - " + e);
            e.printStackTrace();
        }
        catch (Exception ex) {
            CalDataBaseLayer.log.debug("The Error Message - " + ex);
            ex.printStackTrace();
        }
        return count;
    }
    
    public static synchronized Vector getCalendarDetailsList(final String s_id) {
        Vector vCourseList = null;
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            vCourseList = new Vector();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select calendar_id, calendar_name, date_format(date_created,\"%M %e, %Y %H:%i\"),   date_format(date_modified,\"%M %e, %Y %H:%i\"), status  from calendar_keywords where cal_type ='P'  and createdby ='" + s_id + "'");
            while (oRset.next()) {
                final Vector vCourse = new Vector();
                vCourse.addElement(oRset.getString(1));
                vCourse.addElement(oRset.getString(2));
                vCourse.addElement(oRset.getString(3));
                vCourse.addElement(oRset.getString(4));
                vCourse.addElement(oRset.getString(5));
                vCourseList.addElement(vCourse);
            }
            oRset.close();
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            CalDataBaseLayer.log.entry("getCalendarDetailsList()");
            CalDataBaseLayer.log.error("getCalendarDetailsList: error due to SQL exception");
        }
        catch (Exception ex) {
            CalDataBaseLayer.log.entry("getCalendarDetailsList()");
            CalDataBaseLayer.log.error("getCalendarDetailsList: error due to SQL exception");
            ex.printStackTrace();
            CalDataBaseLayer.log.error(" printStack is :: " + ex.getMessage());
        }
        return vCourseList;
    }
    
    public static synchronized boolean addCalendarName(final String strCourseId, final String strCourseName, final String strStatus, final String strCreatedBy, final PrintWriter out) {
        boolean count = false;
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            oStmt.execute("insert into calendar_keywords(calendar_name,date_created,createdby,modified_by,date_modified,status,cal_type) values ('" + strCourseName + "',sysdate(),'" + strCreatedBy + "','" + strCreatedBy + "',sysdate(),'" + strStatus + "','P')");
            oStmt.close();
            oConn.commit();
            oConn.setAutoCommit(true);
            count = false;
            oConn.close();
        }
        catch (SQLException e) {
            System.out.println("Exception in CalDataBaseLayer.addCalendarName()");
            System.out.println("The Error Message - " + e.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in CalDataBaseLayer.addCalendarName()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        return count;
    }
    
    public static synchronized boolean modifyCalendar(final String strCourseId, final String strCourseName, final String strStatus, final String strModBy) {
        int id = 0;
        if (strCourseId != null) {
            id = Integer.parseInt(strCourseId);
        }
        boolean count = false;
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            oStmt.execute("update calendar_keywords set calendar_name = '" + strCourseName + "', modified_by = '" + strModBy + "', date_modified = sysdate(), status = '" + strStatus + "' where calendar_id = " + id);
            oStmt.close();
            oConn.commit();
            oConn.setAutoCommit(true);
            count = true;
            oConn.close();
        }
        catch (SQLException e) {
            System.out.println("Exception in CalDataBaseLayer.modifyCalendar()");
            System.out.println("The Error Message - " + e.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in CalDataBaseLayer.modifyCalendar()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        return count;
    }
    
    public static synchronized boolean deleteCalendar(final String strCourseId, final String s_id, final PrintWriter out) {
        boolean count = false;
        int id = 0;
        if (strCourseId != null) {
            id = Integer.parseInt(strCourseId);
        }
        try {
            final Connection oConn = CalDataBaseLayer.ds.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            oStmt.execute("delete from calendar_keywords where  createdby='" + s_id + "' and  calendar_id = " + id);
            oStmt.close();
            oConn.commit();
            oConn.setAutoCommit(true);
            count = true;
            oConn.close();
        }
        catch (SQLException e) {
            System.out.println("Exception in CalDataBaseLayer.deleteCalendar()");
            System.out.println("The Error Message - " + e.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in CalDataBaseLayer.deleteCalendar()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        return count;
    }
    
    public static void errorMessage(final int errCode, final PrintWriter out) {
        out.println("<font color=#ff0000>* ");
        switch (errCode) {
            case 1216: {
                out.println("No referrenced data.");
                CalDataBaseLayer.log.error("No referrenced data.");
                break;
            }
            case 1217: {
                out.println("You might have deleted a record that has a referrence related to it, or you might have violated a check constraint. The delete operation has been terminated.");
                CalDataBaseLayer.log.error("You might have deleted a record that has a referrence related to it, or you might have violated a check constraint. The delete operation has been terminated.");
                break;
            }
            case 1062: {
                out.println("Insertion not possible. Duplicate entry. The INSERT statement has been terminated.");
                CalDataBaseLayer.log.error("Insertion not possible. Duplicate entry. The INSERT statement has been terminated.");
                break;
            }
            case 1203: {
                out.println("Too many user connected to the database. Please try after some time.");
                CalDataBaseLayer.log.error("Too many user connected to the database. Please try after some time.");
                break;
            }
            case 1196: {
                out.println("Warring rollback not completed.");
                CalDataBaseLayer.log.error("Warring rollback not completed.");
                break;
            }
            case 1171: {
                out.println("Id cnnot be null.");
                CalDataBaseLayer.log.error("Id cnnot be null.");
                break;
            }
            case 218: {
                out.println("Database Error. Contact database administrator.");
                CalDataBaseLayer.log.error("Database Error. Contact database administrator.");
                break;
            }
            default: {
                out.println(" Database Error. Contact database administrator1.");
                CalDataBaseLayer.log.error(" Database Error. Contact database administrator1.");
                break;
            }
        }
        out.println("</font>");
        out.println("<BR>");
    }
    
    public static synchronized boolean getCalendardate(final String str) {
        String str2 = "";
        String str3 = "";
        boolean flag = false;
        Connection oConn = null;
        if (!str.equals("")) {
            final String str4 = str.substring(0, 4);
            final String str5 = str.substring(4);
            final String str6 = str5.substring(0, 2);
            final String str7 = str5.substring(2);
            str2 = str4 + "-" + str6 + "-" + str7;
            str3 = str4 + str6 + str7;
        }
        try {
            oConn = CalDataBaseLayer.ds.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select startdate, enddate from calendar_events where startdate='" + str2 + "' or enddate='" + str2 + "'");
            while (oRset.next()) {
                flag = true;
            }
            if (!flag) {
                final Statement oStmt2 = oConn.createStatement();
                final ResultSet oRset2 = oStmt2.executeQuery("select startdate, enddate from calendar_events");
                while (oRset2.next()) {
                    String s1 = oRset2.getString(1);
                    s1 = ((s1 == null) ? "" : s1);
                    String s2 = oRset2.getString(2);
                    s2 = ((s2 == null) ? "" : s2);
                    if (!s1.equals("") && !s2.equals("")) {
                        final String str8 = s1.substring(0, s1.indexOf(45));
                        final String str9 = s1.substring(s1.indexOf(45) + 1);
                        final String str10 = str9.substring(0, str9.indexOf(45));
                        final String str11 = str9.substring(str9.indexOf(45) + 1);
                        s1 = str8 + str10 + str11;
                        System.out.println("******s1********" + s1);
                        final int s3 = Integer.parseInt(s1);
                        final String str12 = s2.substring(0, s2.indexOf(45));
                        final String str13 = s2.substring(s2.indexOf(45) + 1);
                        final String str14 = str13.substring(0, str13.indexOf(45));
                        final String str15 = str13.substring(str13.indexOf(45) + 1);
                        s2 = str12 + str14 + str15;
                        System.out.println("******s2********" + s2);
                        final int s4 = Integer.parseInt(s2);
                        if (str3.equals("")) {
                            continue;
                        }
                        final int str16 = Integer.parseInt(str3);
                        if (str16 <= s3 || str16 >= s4) {
                            continue;
                        }
                        flag = true;
                    }
                }
                oRset2.close();
                oStmt2.close();
            }
            oRset.close();
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            CalDataBaseLayer.log.entry("getCalendardate()");
            CalDataBaseLayer.log.error("getCalendarDetailsList: error due to SQL exception");
        }
        catch (Exception ex) {
            CalDataBaseLayer.log.entry("getCalendardate()");
            CalDataBaseLayer.log.error("getCalendarDetailsList: error due to SQL exception");
            ex.printStackTrace();
            CalDataBaseLayer.log.error(" printStack is :: " + ex.getMessage());
        }
        return flag;
    }
    
    static {
        log = new SimpleLogger((Class)CalDataBaseLayer.class);
        CalDataBaseLayer.ds = CoreAdminInitHostInfo.ds1;
    }
}
