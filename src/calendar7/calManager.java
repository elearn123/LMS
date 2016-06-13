package calendar7;

import org.grlea.log.*;
import java.util.*;

import org.directwebremoting.*;
import javax.servlet.http.*;
import java.util.regex.*;

public class calManager
{
    public static final SimpleLogger log;
    
    public String getCalendars() {
        final Vector<String[]> vCalendarName = calDataBaseLayer.getCalendars("S");
        String strOptions = "<option value='0'>[Choose One]</option>";
        for (int i = 0; i < vCalendarName.size(); ++i) {
            final String[] strCalendarName = vCalendarName.elementAt(i);
            strOptions = strOptions + "<option value='" + strCalendarName[0] + "'>" + strCalendarName[1] + "</option>";
        }
        return strOptions;
    }
    
    public String getCurrentEvent() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final HttpServletRequest request = wctx1.getHttpServletRequest();
        final HttpServletResponse response = wctx1.getHttpServletResponse();
        return (String)mysession.getAttribute("event_id");
    }
    
    public void addCalendarEntry(final String strCalendar, String strStartDate, final String strStartTime, final String strStartMeridian, final String strEndTime, final String strEndMeridian, String strEndDate, final String strBody, final String[] strRepeat, final String strRepeatType, final String[] strReminder, final String strReminderType, final String condition) {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final HttpServletRequest request = wctx1.getHttpServletRequest();
        final HttpServletResponse response = wctx1.getHttpServletResponse();
        final String user_id = (String)mysession.getAttribute("user_id");
        Vector vRepeat = null;
        Vector vReminder = null;
        String sTimeFormat = null;
        String eTimeFormat = null;
        if (!strRepeatType.equals("N")) {
            if (strRepeatType.equals("D")) {
                vRepeat = new Vector(3, 3);
                vRepeat.addElement(strRepeat[0]);
                vRepeat.addElement(strRepeat[1]);
                vRepeat.addElement(strRepeat[2]);
            }
            else if (strRepeatType.equals("M")) {
                vRepeat = new Vector(4, 3);
                vRepeat.addElement(strRepeat[0]);
                vRepeat.addElement(strRepeat[1]);
                vRepeat.addElement(strRepeat[2]);
                vRepeat.addElement(strRepeat[3]);
            }
        }
        final Pattern p = Pattern.compile("[/]+");
        final String[] result = p.split(strStartDate);
        strStartDate = result[2] + "-" + result[0] + "-" + result[1];
        if (!strEndDate.equals("")) {
            final String[] resultEnd = p.split(strEndDate);
            strEndDate = resultEnd[2] + "-" + resultEnd[0] + "-" + resultEnd[1];
        }
        else {
            strEndDate = null;
        }
        if (strReminderType.equals("yes")) {
            vReminder = new Vector(3, 3);
            vReminder.addElement(strReminder[0]);
            vReminder.addElement(strReminder[1]);
            vReminder.addElement(strReminder[2]);
        }
        if (strStartMeridian.equals("AM")) {
            final String[] strTime = this.parseDateTimes(strStartTime);
            sTimeFormat = strTime[0] + ":" + strTime[1] + ":" + "00";
        }
        else {
            final String[] strTime = this.parseDateTimes(strStartTime);
            int timehr = Integer.parseInt(strTime[0]);
            timehr += 12;
            sTimeFormat = "" + timehr + ":" + strTime[1] + ":" + "00";
        }
        if (strEndMeridian.equals("AM")) {
            final String[] strTime = this.parseDateTimes(strEndTime);
            eTimeFormat = strTime[0] + ":" + strTime[1] + ":" + "00";
        }
        else {
            final String[] strTime = this.parseDateTimes(strEndTime);
            int timehr = Integer.parseInt(strTime[0]);
            timehr += 12;
            eTimeFormat = "" + timehr + ":" + strTime[1] + ":" + "00";
        }
        calDataBaseLayer.setCalendarName(strCalendar, user_id, strStartDate, sTimeFormat, strEndDate, eTimeFormat, strBody, vRepeat, vReminder, Integer.parseInt(condition));
    }
    
    private String[] parseDateTimes(final String s) {
        final String[] strTime = { s.substring(0, 2), s.substring(2, 4) };
        return strTime;
    }
    
    public void setCurrentEvent(final String event_type, final String event_id) {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        mysession.setAttribute("event_type", (Object)event_type);
        if (event_type.equals("2")) {
            mysession.setAttribute("event_id", (Object)event_id);
        }
    }
    
    public String[] getCalendarDetails() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String event_id = (String)mysession.getAttribute("event_id");
        final Vector<String> vCalendar = calDataBaseLayer.getCalendarDetails(event_id);
        System.out.println("vCalendar===" + vCalendar);
        final String[] strCalendar = new String[20];
        final Pattern p = Pattern.compile("[:]+");
        strCalendar[0] = vCalendar.elementAt(0);
        System.out.println("strCalendar[0]===" + strCalendar[0]);
        strCalendar[1] = vCalendar.elementAt(1);
        System.out.println("strCalendar[1]===" + strCalendar[1]);
        final String strStartDate = vCalendar.elementAt(2);
        final Pattern pstd = Pattern.compile("[-]+");
        String[] stdResult = pstd.split(strStartDate);
        strCalendar[2] = stdResult[1] + "/" + stdResult[2] + "/" + stdResult[0];
        System.out.println("strCalendar[2]===" + strCalendar[2]);
        String[] result = p.split(vCalendar.elementAt(3));
        int timehr = Integer.parseInt(result[0]);
        int hr = timehr % 12;
        final String sTimeMeridian = timehr / 12 + "";
        strCalendar[3] = (sTimeMeridian.equalsIgnoreCase("1") ? "PM" : "AM");
        System.out.println("strCalendar[3]===" + strCalendar[3]);
        strCalendar[4] = ((hr < 10) ? ("0" + hr + result[1]) : (hr + result[1]));
        System.out.println("strCalendar[4]===" + strCalendar[4]);
        final String strEndDate = (vCalendar.elementAt(4) == null) ? "" : vCalendar.elementAt(4);
        if (vCalendar.elementAt(4) != null) {
            stdResult = pstd.split(strEndDate);
            strCalendar[5] = stdResult[1] + "/" + stdResult[2] + "/" + stdResult[0];
        }
        else {
            strCalendar[5] = "";
        }
        System.out.println("strCalendar[5]===" + strCalendar[5]);
        result = p.split(vCalendar.elementAt(5));
        timehr = Integer.parseInt(result[0]);
        hr = timehr % 12;
        final String eTimeMeridian = timehr / 12 + "";
        strCalendar[6] = (eTimeMeridian.equalsIgnoreCase("1") ? "PM" : "AM");
        System.out.println("strCalendar[6]===" + strCalendar[6]);
        strCalendar[7] = ((hr < 10) ? ("0" + hr + result[1]) : (hr + result[1]));
        System.out.println("strCalendar[7]===" + strCalendar[7]);
        strCalendar[8] = vCalendar.elementAt(6);
        System.out.println("strCalendar[8]===" + strCalendar[8]);
        strCalendar[9] = vCalendar.elementAt(7);
        System.out.println("strCalendar[9]===" + strCalendar[9]);
        strCalendar[10] = vCalendar.elementAt(8);
        System.out.println("strCalendar[10]===" + strCalendar[10]);
        if (!strCalendar[9].equalsIgnoreCase("N")) {
            if (strCalendar[9].equalsIgnoreCase("D")) {
                strCalendar[11] = vCalendar.elementAt(9);
                System.out.println("strCalendar[11]==1=" + strCalendar[11]);
                strCalendar[12] = vCalendar.elementAt(10);
                System.out.println("strCalendar[12]==1=" + strCalendar[13]);
                strCalendar[13] = vCalendar.elementAt(11);
                System.out.println("strCalendar[13]==1=" + strCalendar[13]);
                if (!strCalendar[10].equalsIgnoreCase("N")) {
                    strCalendar[15] = vCalendar.elementAt(12);
                    System.out.println("strCalendar[15]=1==" + strCalendar[15]);
                    strCalendar[16] = vCalendar.elementAt(13);
                    System.out.println("strCalendar[16]=1==" + strCalendar[16]);
                    strCalendar[17] = vCalendar.elementAt(14);
                    System.out.println("strCalendar[17]=1==" + strCalendar[17]);
                }
            }
            else {
                strCalendar[11] = vCalendar.elementAt(9);
                System.out.println("strCalendar[11]=2==" + strCalendar[11]);
                strCalendar[12] = vCalendar.elementAt(10);
                System.out.println("strCalendar[12]==2=" + strCalendar[13]);
                strCalendar[13] = vCalendar.elementAt(11);
                System.out.println("strCalendar[13]=2==" + strCalendar[13]);
                strCalendar[14] = vCalendar.elementAt(12);
                System.out.println("strCalendar[14]=2==" + strCalendar[14]);
                if (!strCalendar[10].equalsIgnoreCase("N")) {
                    strCalendar[15] = vCalendar.elementAt(13);
                    System.out.println("strCalendar[15]==2=" + strCalendar[15]);
                    strCalendar[16] = vCalendar.elementAt(14);
                    System.out.println("strCalendar[16]=2==" + strCalendar[16]);
                    strCalendar[17] = vCalendar.elementAt(15);
                    System.out.println("strCalendar[17]=2==" + strCalendar[17]);
                }
            }
        }
        return strCalendar;
    }
    
    public String getEventType() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final HttpServletRequest request = wctx1.getHttpServletRequest();
        final HttpServletResponse response = wctx1.getHttpServletResponse();
        return (String)mysession.getAttribute("event_type");
    }
    
    public void modifyCalendarEntry(final String strCalendar, String strStartDate, final String strStartTime, final String strStartMeridian, final String strEndTime, final String strEndMeridian, String strEndDate, final String strBody, final String[] strRepeat, final String strRepeatType, final String[] strReminder, final String strReminderType, final String condition) {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final HttpServletRequest request = wctx1.getHttpServletRequest();
        final HttpServletResponse response = wctx1.getHttpServletResponse();
        final String user_id = (String)mysession.getAttribute("user_id");
        final String event_id = (String)mysession.getAttribute("event_id");
        Vector vRepeat = null;
        Vector vReminder = null;
        String sTimeFormat = null;
        String eTimeFormat = null;
        if (!strRepeatType.equals("N")) {
            if (strRepeatType.equals("D")) {
                vRepeat = new Vector(3, 3);
                vRepeat.addElement(strRepeat[0]);
                vRepeat.addElement(strRepeat[1]);
                vRepeat.addElement(strRepeat[2]);
            }
            else if (strRepeatType.equals("M")) {
                vRepeat = new Vector(4, 3);
                vRepeat.addElement(strRepeat[0]);
                vRepeat.addElement(strRepeat[1]);
                vRepeat.addElement(strRepeat[2]);
                vRepeat.addElement(strRepeat[3]);
            }
        }
        final Pattern p = Pattern.compile("[/]+");
        final String[] result = p.split(strStartDate);
        strStartDate = result[2] + "-" + result[0] + "-" + result[1];
        if (!strEndDate.equals("")) {
            final String[] resultEnd = p.split(strEndDate);
            strEndDate = resultEnd[2] + "-" + resultEnd[0] + "-" + resultEnd[1];
        }
        else {
            strEndDate = null;
        }
        if (strReminderType.equals("yes")) {
            vReminder = new Vector(3, 3);
            vReminder.addElement(strReminder[0]);
            vReminder.addElement(strReminder[1]);
            vReminder.addElement(strReminder[2]);
        }
        if (strStartMeridian.equals("AM")) {
            final String[] strTime = this.parseDateTimes(strStartTime);
            sTimeFormat = strTime[0] + ":" + strTime[1] + ":" + "00";
        }
        else {
            final String[] strTime = this.parseDateTimes(strStartTime);
            int timehr = Integer.parseInt(strTime[0]);
            timehr += 12;
            sTimeFormat = "" + timehr + ":" + strTime[1] + ":" + "00";
        }
        if (strEndMeridian.equals("AM")) {
            final String[] strTime = this.parseDateTimes(strEndTime);
            eTimeFormat = strTime[0] + ":" + strTime[1] + ":" + "00";
        }
        else {
            final String[] strTime = this.parseDateTimes(strEndTime);
            int timehr = Integer.parseInt(strTime[0]);
            timehr += 12;
            eTimeFormat = "" + timehr + ":" + strTime[1] + ":" + "00";
        }
        System.out.println("-modify-->>>---event_id------" + event_id);
        System.out.println("-modify-->>>---strCalendar---" + strCalendar);
        System.out.println("-modify-->>>---user_id-------" + user_id);
        System.out.println("-modify-->>>---strStartDate--" + strStartDate);
        System.out.println("-modify-->>>---sTimeFormat---" + sTimeFormat);
        System.out.println("-modify-->>>---strEndDate----" + strEndDate);
        System.out.println("-modify-->>>---eTimeFormat---" + eTimeFormat);
        System.out.println("-modify-->>>---strBody-------" + strBody);
        System.out.println("-modify-->>>---vRepeat-------" + vRepeat);
        System.out.println("-modify-->>>---vReminder-----" + vReminder);
        calDataBaseLayer.updateCalendarName(event_id, strCalendar, user_id, strStartDate, sTimeFormat, strEndDate, eTimeFormat, strBody, vRepeat, vReminder, Integer.parseInt(condition));
    }
    
    public void deleteCalendarEntry() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final HttpServletRequest request = wctx1.getHttpServletRequest();
        final HttpServletResponse response = wctx1.getHttpServletResponse();
        final String event_id = (String)mysession.getAttribute("event_id");
        calDataBaseLayer.deleteCalendarName(event_id);
    }
    
    static {
        log = new SimpleLogger((Class)calManager.class, true);
    }
}
