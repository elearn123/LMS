package calendar.calendar;

import org.grlea.log.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import org.apache.ecs.*;
import org.apache.ecs.html.*;
import java.util.*;

public class CalendarSharedView extends HttpServlet
{
    private static String CONTENT_TYPE;
    private static final SimpleLogger log;
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        response.setContentType(CalendarSharedView.CONTENT_TYPE);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        out = response.getWriter();
        if (!New_LoginAppl.checkLogin(request, out)) {
            return;
        }
        final HttpSession mysession = request.getSession(false);
        final String student_id = (String)mysession.getAttribute("student_id");
        final String strPrmAddModDel = request.getParameter("prmAddModify");
        if (student_id != null) {
            if (strPrmAddModDel != null) {
                final int iPrmAddModify = Integer.parseInt(strPrmAddModDel);
                switch (iPrmAddModify) {
                    case 0: {
                        this.addUser_Calendar(request, response, student_id, out);
                        break;
                    }
                    case 1: {
                        this.modifyUser_Calendar(request, response, student_id, out);
                        break;
                    }
                    case 2: {
                        this.deleteUser_Calendar(request, response, student_id, out);
                        break;
                    }
                }
            }
            this.getResult(request, response, out, student_id);
        }
        else {
            response.sendRedirect("../servlet/interfaceenginev2.PortalServlet?IID=LoginPage");
        }
    }
    
    public void getResult(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out, final String student_id) throws IOException, ServletException {
        String strRepeatType = "N";
        String strReminderckeck = "N";
        String viewType = request.getParameter("function");
        String strDateType = request.getParameter("date");
        final String event_id = request.getParameter("event_id");
        final String type_id = request.getParameter("type_id");
        final SampleDate sd = new SampleDate();
        Calendar present = null;
        Vector vPersonalDataList = null;
        if (strDateType == null) {
            final Calendar calendar = new GregorianCalendar();
            strDateType = sd.getPresentday(calendar);
            present = sd.parseDate(strDateType);
            viewType = "dayview";
        }
        else {
            present = sd.parseDate(strDateType);
        }
        if (event_id != null && type_id != null) {
            vPersonalDataList = CalDataBaseLayer.getPersonalDataList(event_id, type_id);
        }
        final int setYear = sd.getYear(present);
        final int setMonth = sd.getMonth(present);
        final int setDate = sd.getDays(present);
        String strmon = "";
        String strday = "";
        if (setMonth < 10) {
            strmon = "0" + setMonth;
        }
        else {
            strmon = "" + setMonth;
        }
        if (setDate < 10) {
            strday = "0" + setDate;
        }
        else {
            strday = "" + setDate;
        }
        final String strdate = "" + setYear + strmon + strday;
        final String[] hrmin = { "0000", "0100", "0115", "0130", "0145", "0200", "0215", "0230", "0245", "0300", "0315", "0330", "0345", "0400", "0415", "0430", "0445", "0500", "0515", "0530", "0545", "0600", "0615", "0630", "0645", "0700", "0715", "0730", "0745", "0800", "0815", "0830", "0845", "0900", "0915", "0930", "0945", "1000", "1015", "1030", "1045", "1100", "1115", "1130", "1145", "1200", "1215", "1230", "1245" };
        final String[] hrmin2 = { "---", "01:00", "01:15", "01:30", "01:45", "02:00", "02:15", "02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30", "04:45", "05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45" };
        final String[] AMPM = { "AM", "PM" };
        final String[] strRiminder = { "00", "300", "900", "1800", "2700", "3600", "7200", "10800", "21600", "43200", "86400", "172800", "259200", "345600", "432000", "518400", "604800", "691200", "777600", "864000", "950400", "1036800", "1123200", "1209600" };
        final String[] strRiminder2 = { "---", "5 minutes", "15 minutes", "30 minutes", "45 minutes", "1 hour", "2 hours", "3 hours", "6 hours", "12 hours", "1 day", "2 days", "3 days", "4 days", "5 days", "6 days", "7 days", "8 days", "9 days", "10 days", "11 days", "12 days", "13 days", "14 days" };
        final String[] month = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        final String[] days = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
        final String[] repeatEvery = { "Every ", "Every other", "Every third", "Every fourth" };
        final String[] repeatday = { "Day ", "Week", "Month", "Year", "Mon, Wed, Fri", "Tue &amp; Thu", "Mon Thru Fri", "Sat &amp; Sun" };
        final String[] repeatFirst = { "First", "Second", "Third", "Fourth", "Last" };
        final String[] strdays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        final String[] strMonthYear = { "month", "other month", "3 month", "4 month", "6 month", "year" };
        final Vector<String[]> vCalendarName = CalDataBaseLayer.publicSharedCalendarName(student_id, "S");
        final Select canlendarname = new Select("calendar_name").setClassId("drpdwn");
        Option[] option16 = new Option[vCalendarName.size()];
        final Option[] option2 = new Option[49];
        final Option[] option3 = new Option[49];
        final Option[] option4 = new Option[2];
        final Option[] option5 = new Option[2];
        final Option[] option6 = new Option[repeatEvery.length];
        final Option[] option7 = new Option[repeatday.length];
        final Option[] option8 = new Option[repeatFirst.length];
        final Option[] option9 = new Option[strdays.length];
        final Option[] option10 = new Option[strMonthYear.length];
        final Option[] option11 = new Option[strRiminder.length];
        final Option[] option12 = new Option[strRiminder2.length];
        final Option[] option13 = new Option[12];
        final Option[] option14 = new Option[31];
        final Option[] option15 = new Option[12];
        option16 = new Option[31];
        String strbody = "";
        final Input event_no = new Input().setType("hidden").setName("eventid");
        final Input btnSYear = new Input().setType("text").setName("syearmonday").setValue(setYear).setSize(4).setMaxlength(4);
        final Input btnYear = new Input().setType("text").setName("syear").setValue(setYear).setSize(4).setMaxlength(4);
        for (int i = 0; i < vCalendarName.size(); ++i) {
            final String[] strCalendarName = vCalendarName.elementAt(i);
            canlendarname.addElement((Element)(option16[i] = new Option(strCalendarName[0]).addElement(strCalendarName[1])));
        }
        for (int i = 0; i < repeatEvery.length; ++i) {
            final String strRow = "" + (i + 1);
            option6[i] = new Option(strRow).addElement(repeatEvery[i]);
        }
        for (int i = 0; i < repeatday.length; ++i) {
            final String strRow = "" + (i + 1);
            option7[i] = new Option(strRow).addElement(repeatday[i]);
        }
        for (int i = 0; i < repeatFirst.length; ++i) {
            final String strRow = "" + (i + 1);
            option8[i] = new Option(strRow).addElement(repeatFirst[i]);
        }
        for (int i = 0; i < strdays.length; ++i) {
            final String strRow = "" + (i + 1);
            option9[i] = new Option(strRow).addElement(strdays[i]);
        }
        for (int i = 0; i < strMonthYear.length; ++i) {
            final String strRow = "" + (i + 1);
            option10[i] = new Option(strRow).addElement(strMonthYear[i]);
        }
        for (int i = 0; i < strRiminder.length; ++i) {
            option11[i] = new Option(strRiminder[i]).addElement(strRiminder2[i]);
            option12[i] = new Option(strRiminder[i]).addElement(strRiminder2[i]);
        }
        for (int i = 0; i < hrmin.length; ++i) {
            option2[i] = new Option(hrmin[i]).addElement(hrmin2[i]);
            option3[i] = new Option(hrmin[i]).addElement(hrmin2[i]);
        }
        for (int i = 0; i < AMPM.length; ++i) {
            option4[i] = new Option(AMPM[i]).addElement(AMPM[i]);
            option5[i] = new Option(AMPM[i]).addElement(AMPM[i]);
        }
        for (int i = 0; i < month.length; ++i) {
            String strRow = "" + i;
            if (i < 9) {
                strRow = "0" + i;
            }
            option13[i] = new Option(strRow).addElement(month[i]);
            option15[i] = new Option(strRow).addElement(month[i]);
        }
        for (int i = 0; i < days.length; ++i) {
            final int row = i + 1;
            String strRow2 = "" + row;
            if (i < 9) {
                strRow2 = "0" + row;
            }
            option14[i] = new Option(strRow2).addElement(days[i]);
            option16[i] = new Option(strRow2).addElement(days[i]);
        }
        option13[setMonth].setSelected(true);
        option14[setDate - 1].setSelected(true);
        option15[setMonth].setSelected(true);
        option16[setDate - 1].setSelected(true);
        if (vPersonalDataList != null) {
            if (vPersonalDataList.size() > 0) {
                final String streventid = (String) vPersonalDataList.elementAt(0);
                event_no.setValue(streventid);
                final String strid = (String) vPersonalDataList.elementAt(1);
                for (int j = 0; j < vCalendarName.size(); ++j) {
                    final String[] strCalendarName2 = vCalendarName.elementAt(j);
                    if (strCalendarName2[0].equals(strid.trim())) {
                        option16[j].setSelected(true);
                    }
                }
                strbody = (String) vPersonalDataList.elementAt(2);
                final String strSTime = (String) vPersonalDataList.elementAt(4);
                final String[] starttime = this.parseDatetime(strSTime);
                final String[] strRepeat = (String[]) vPersonalDataList.elementAt(5);
                final String strEnddate = (String) vPersonalDataList.elementAt(6);
                strRepeatType = strRepeat[0];
                if (strRepeatType.equals("D")) {
                    final int everday = Integer.parseInt(strRepeat[1]);
                    option6[everday - 1].setSelected(true);
                    final int dayrepeat = Integer.parseInt(strRepeat[2]);
                    option7[dayrepeat - 1].setSelected(true);
                    if (strEnddate == null) {}
                }
                else if (strRepeatType.equals("M")) {
                    final int evermonth = Integer.parseInt(strRepeat[1]);
                    option8[evermonth - 1].setSelected(true);
                    final int weekdays = Integer.parseInt(strRepeat[3]);
                    option9[weekdays - 1].setSelected(true);
                    final int monthrepeat = Integer.parseInt(strRepeat[2]);
                    option10[monthrepeat - 1].setSelected(true);
                    if (strEnddate != null) {}
                }
                final String strEndtime = (String) vPersonalDataList.elementAt(7);
                final String[] endtime = this.parseDatetime(strEndtime);
                final String[] strReminder = (String[]) vPersonalDataList.elementAt(8);
                strReminderckeck = strReminder[0];
                if (strReminder[0].equals("Y")) {
                    for (int k = 0; k < strRiminder.length; ++k) {
                        if (strReminder[1].equals(strRiminder[k])) {
                            option11[k].setSelected(true);
                        }
                        if (strReminder[2].equals(strRiminder[k])) {
                            option12[k].setSelected(true);
                        }
                    }
                }
                for (int k = 0; k < hrmin.length; ++k) {
                    if (hrmin[k].equals(starttime[0].trim())) {
                        option2[k].setSelected(true);
                    }
                    if (hrmin[k].equals(endtime[0].trim())) {
                        option3[k].setSelected(true);
                    }
                }
                for (int k = 0; k < AMPM.length; ++k) {
                    if (AMPM[k].equals(starttime[1].trim())) {
                        option4[k].setSelected(true);
                    }
                    if (AMPM[k].equals(endtime[1].trim())) {
                        option5[k].setSelected(true);
                    }
                }
            }
        }
        final Input inputButton1 = new Input().setClassId("drpdwn").setName("addCalendar").setTabindex(2).setTitleValue("Add").setType("button").setValue("Add");
        final Input inputButton2 = new Input().setClassId("drpdwn").setName("modifyCalendar").setTabindex(2).setTitleValue("modify").setType("button").setValue("modify");
        final Input inputButton3 = new Input().setClassId("drpdwn").setName("deleteCalendar").setTabindex(2).setTitleValue("delete").setType("button").setValue("delete");
        final Input inputButton4 = new Input().setClassId("drpdwn").setName("cancelCalendar").setTabindex(2).setTitleValue("cancel").setType("button").setValue("cancel");
        inputButton1.setOnClick("add_onclick()");
        inputButton2.setOnClick("modify_onclick()");
        inputButton3.setOnClick("delete_onclick()");
        inputButton4.setOnClick("close_onclick('dayview','" + strdate + "')");
        final Table tbldropdown = new Table();
        tbldropdown.addElement((Element)new TR().setBgColor("#4d99e5").addElement((Element)new TD().setColSpan(2).addElement((Element)new B().addElement((Element)new Font().setFace("verdana").setSize(-2).setColor("#CDCDCD").addElement("Start  Date"))))).addElement((Element)new TR().addElement((Element)new TD().setColSpan(2).addElement((Element)new Select().setName("ssmonth").addElement(option15)).addElement((Element)new Select().setName("ssday").addElement(option16)).addElement((Element)btnSYear))).addElement((Element)new TR().setBgColor("#4d99e5").addElement((Element)new TD().addElement((Element)new B().addElement((Element)new Font().setFace("verdana").setSize(-2).setColor("#CDCDCD").addElement("Start Time")))).addElement((Element)new TD().addElement((Element)new B().addElement((Element)new Font().setColor("#CDCDCD").setSize(-2).setFace("verdana").addElement("End Time"))))).addElement((Element)new TR().addElement((Element)new TD().addElement((Element)new Select().setName("stime").addElement(option2)).addElement((Element)new Select().setName("sAMPM").addElement(option4))).addElement((Element)new TD().addElement((Element)new Select().setName("etime").addElement(option3)).addElement((Element)new Select().setName("eAMPM").addElement(option5))));
        final Table tblrepeat = new Table();
        tblrepeat.addElement((Element)new CalTRExtension().addRowUserForNoReminder("repeat", "N", "This event does not Repeat.")).addElement((Element)new CalTRExtension().addRowUserForReminder("repeat", "D", "Repeat", "everyday", option6, "", "dayrepeat", option7, "")).addElement((Element)new CalTRExtension().addRowUserForRepeat("repeat", "M", "Repeat on the ", "everymonth", option8, "weekdays", option9, "of the month every", "monthrepeat", option10, ""));
        final Table tblenddate = new Table();
        tblenddate.addElement((Element)new CalTRExtension().addRowUserForNoReminder("enddate", "no", "No Enddate")).addElement((Element)new CalTRExtension().addRowUserForUntil("enddate", "yes", "until", "umonth", option15, "uday", option16, btnYear));
        final Table tblreminder = new Table();
        tblreminder.addElement((Element)new CalTRExtension().addRowUserForNoReminder("reminder", "no", "Donot send a reminder ")).addElement((Element)new CalTRExtension().addRowUserForReminder("reminder", "yes", "Send a reminder", "breminder", option11, "before and", "areminder", option12, "before the schedule"));
        final String javascript = "\n function load(repeat , reminder) {\n   if(repeat =='D'){\n\t\t\tdocument.frm.repeat[1].checked=true;\n\t\t\t}\n  else if(repeat =='M'){\n\t\t\tdocument.frm.repeat[2].checked=true;\n\t\t\t}\n \telse {\n\t\t\tdocument.frm.repeat[0].checked=true;\n \t\t}\n   if(reminder =='Y'){\n\t\t\tdocument.frm.reminder[1].checked=true;\n\t\t\t}\n \telse {\n\t\t\tdocument.frm.reminder[0].checked=true;\n \t\t}\n\t\t\tdocument.frm.enddate[1].checked=true;\n\t\t\tCCA();\t\n\t}\n function add_onclick() {\n  \tvar strdate = '" + strdate + "'  " + "\n\t\t\tdocument.frm.method=\"post\";" + "\n\t\t\tdocument.frm.action = \"./calendar.calendar.CalendarSharedView?prmAddModify=0&function=dayview&date=\"+strdate;" + "\n\t\t\tdocument.frm.submit();" + "\n\t}" + "\n function modify_onclick() {" + "\n  \tvar strdate = '" + strdate + "'" + "\n\t\t\tdocument.frm.method=\"post\";" + "\n\t\t\tdocument.frm.action = \"./calendar.calendar.CalendarSharedView?prmAddModify=1&function=dayview&date=\"+strdate;" + "\n\t\t\tdocument.frm.submit();" + "\n\t}" + "\n function delete_onclick() {" + "\n  \tvar strdate = '" + strdate + "'  " + "\n\t\t\tdocument.frm.method=\"post\";" + "\n\t\t\tdocument.frm.action = \"./calendar.calendar.CalendarSharedView?prmAddModify=2&function=dayview&date=\"+strdate;" + "\n\t\t\tdocument.frm.submit();" + "\n\t}" + "\n function close_onclick(viewType ,stryear) {" + "\n var nm = document.frm.calendar_name.value; " + "\n            window.parent.frames[2].document.location.href=\"./calendar.calendar.MyCalendar?function=\"+viewType+\"&date=\"+stryear+\"&caltype=1&calname=\"+nm;" + "\n\t}";
        final Html html = new Html();
        final Head head = new Head().addElement((Element)new Title("Calendar Details Window")).addElement((Element)new Link().setHref("../coreadmin/js/stylesheet.css").setRel("stylesheet")).addElement((Element)new Script().setLanguage("javascript").setSrc("../coreadmin/js/check.js"));
        final Script script = new Script().setLanguage("javascript").addElement(javascript);
        head.addElement((Element)script);
        final Body body = new Body().setBgColor("#fffffe");
        final Form form = new Form().setName("frm").setMethod("post");
        final Table tbldata = new Table().setWidth("100%");
        tbldata.addElement((Element)new CalTRExtension().addRow("Calendar name", canlendarname));
        tbldata.addElement((Element)new TR().addElement((Element)new TD().addElement((Element)new CalTableExtension().addTextArea(strbody))).addElement((Element)new TD().addElement((Element)tbldropdown).addElement((Element)event_no)));
        tbldata.addElement((Element)new TR().addElement((Element)new TD().setColSpan(2).addElement((Element)new B().addElement((Element)new Font().setFace("verdana").setSize(-2).setColor("#4d99e5").addElement("Repeat :")))));
        tbldata.addElement((Element)new TR().addElement((Element)new TD().setColSpan(2).addElement((Element)tblrepeat)));
        tbldata.addElement((Element)new TR().addElement((Element)new TD().setColSpan(2).addElement((Element)new B().addElement((Element)new Font().setFace("verdana").setSize(-2).setColor("#4d66e5").addElement("&nbsp;&nbsp;&nbsp;&nbsp;End date :")))));
        tbldata.addElement((Element)new TR().addElement((Element)new TD().setColSpan(2).addElement((Element)tblenddate)));
        tbldata.addElement((Element)new TR().addElement((Element)new TD().setColSpan(2).addElement((Element)new B().addElement((Element)new Font().setColor("#4d99e5").setFace("verdana").setSize(-2).addElement("Reminder :")))));
        tbldata.addElement((Element)new TR().addElement((Element)new TD().setColSpan(2).addElement((Element)tblreminder)));
        tbldata.addElement((Element)new TR().addElement((Element)new TD().setColSpan(2).addElement("&nbsp")));
        tbldata.addElement((Element)new TR().addElement((Element)new TD().setColSpan(2).setAlign("center").addElement((Element)inputButton4)));
        form.addElement((Element)tbldata);
        body.addElement((Element)form);
        html.addElement((Element)head);
        html.addElement((Element)body);
        body.setOnLoad("scrollit(100);load('" + strRepeatType + "','" + strReminderckeck + "')");
        out.print(html.toString());
        out.close();
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    public void addUser_Calendar(final HttpServletRequest request, final HttpServletResponse response, final String student_id, final PrintWriter out1) throws IOException, ServletException {
        int condition = 0;
        final String strbody = request.getParameter("calendartext");
        final String calendar_id = request.getParameter("calendar_name");
        final String strSYear = request.getParameter("syearmonday");
        final String strMonth = request.getParameter("ssmonth");
        final String days = request.getParameter("ssday");
        final String sDateFormat = strSYear + "-" + strMonth + "-" + days;
        String eDateFormat = strSYear + "-" + strMonth + "-" + days;
        CalendarSharedView.log.entry("addUser_Calendar()");
        CalendarSharedView.log.verbose(" sDateFormat =" + sDateFormat);
        final String strSTime = request.getParameter("stime");
        String sTimeFormat = "";
        final String strsampm = request.getParameter("sAMPM");
        Vector vRepeat = null;
        Vector vReminder = null;
        if (strsampm.equals("AM")) {
            final String[] strTime = this.parseDateTimes(strSTime);
            sTimeFormat = strTime[0] + ":" + strTime[1] + ":" + "00";
        }
        else {
            final String[] strTime = this.parseDateTimes(strSTime);
            int timehr = Integer.parseInt(strTime[0]);
            timehr += 12;
            sTimeFormat = "" + timehr + ":" + strTime[1] + ":" + "00";
        }
        final String strEndTime = request.getParameter("etime");
        final String streampm = request.getParameter("eAMPM");
        String eTimeFormat = "";
        if (streampm.equals("AM")) {
            final String[] strTime2 = this.parseDateTimes(strEndTime);
            eTimeFormat = strTime2[0] + ":" + strTime2[1] + ":" + "00";
        }
        else {
            final String[] strTime2 = this.parseDateTimes(strEndTime);
            int timehr2 = Integer.parseInt(strTime2[0]);
            timehr2 += 12;
            eTimeFormat = "" + timehr2 + ":" + strTime2[1] + ":" + "00";
        }
        final String strRepeat = request.getParameter("repeat");
        final String strEnddate = request.getParameter("enddate");
        if (!strRepeat.equals("N")) {
            condition = 1;
            if (strRepeat.equals("D")) {
                vRepeat = new Vector(3, 3);
                final String everyday = request.getParameter("everyday");
                final String dayrepeat = request.getParameter("dayrepeat");
                vRepeat.addElement("D");
                vRepeat.addElement(everyday);
                vRepeat.addElement(dayrepeat);
            }
            else if (strRepeat.equals("M")) {
                vRepeat = new Vector(3, 3);
                final String monthrepeat = request.getParameter("monthrepeat");
                final String everymonth = request.getParameter("everymonth");
                final String weekdays = request.getParameter("weekdays");
                vRepeat.addElement("M");
                vRepeat.addElement(everymonth);
                vRepeat.addElement(weekdays);
                vRepeat.addElement(monthrepeat);
            }
            if (strEnddate.equals("yes")) {
                final String uday = request.getParameter("uday");
                final String umonth = request.getParameter("umonth");
                final String strYear = request.getParameter("syear");
                final String endDateFormat = eDateFormat = strYear + "-" + umonth + "-" + uday;
            }
            else {
                CalendarSharedView.log.entry("addUser_Calendar()");
                eDateFormat = null;
                CalendarSharedView.log.verbose(" eDateFormat" + eDateFormat);
            }
        }
        final String strReminder = request.getParameter("reminder");
        if (strReminder.equals("yes")) {
            vReminder = new Vector(3, 3);
            condition = 2;
            final String strBReminder = request.getParameter("breminder");
            final String strAReminder = request.getParameter("areminder");
            vReminder.addElement("Y");
            vReminder.addElement(strBReminder);
            vReminder.addElement(strAReminder);
        }
        if (strReminder.equals("yes") && !strRepeat.equals("N")) {
            condition = 3;
        }
        CalDataBaseLayer.setCalendarName(calendar_id, student_id, sDateFormat, sTimeFormat, eDateFormat, eTimeFormat, strbody, vRepeat, vReminder, condition);
    }
    
    public void modifyUser_Calendar(final HttpServletRequest request, final HttpServletResponse response, final String student_id, final PrintWriter out1) throws IOException, ServletException {
        final String event_id = request.getParameter("eventid");
        int condition = 0;
        final String strbody = request.getParameter("calendartext");
        final String calendar_id = request.getParameter("calendar_name");
        final String strSYear = request.getParameter("syearmonday");
        final String strMonth = request.getParameter("ssmonth");
        final String days = request.getParameter("ssday");
        final String sDateFormat = strSYear + "-" + strMonth + "-" + days;
        String eDateFormat = strSYear + "-" + strMonth + "-" + days;
        final String strSTime = request.getParameter("stime");
        String sTimeFormat = "";
        final String strsampm = request.getParameter("sAMPM");
        Vector vRepeat = null;
        Vector vReminder = null;
        if (strsampm.equals("AM")) {
            final String[] strTime = this.parseDateTimes(strSTime);
            sTimeFormat = strTime[0] + ":" + strTime[1] + ":" + "00";
        }
        else {
            final String[] strTime = this.parseDateTimes(strSTime);
            int timehr = Integer.parseInt(strTime[0]);
            timehr += 12;
            sTimeFormat = "" + timehr + ":" + strTime[1] + ":" + "00";
        }
        final String strEndTime = request.getParameter("etime");
        final String streampm = request.getParameter("eAMPM");
        String eTimeFormat = "";
        if (streampm.equals("AM")) {
            final String[] strTime2 = this.parseDateTimes(strEndTime);
            eTimeFormat = strTime2[0] + ":" + strTime2[1] + ":" + "00";
        }
        else {
            final String[] strTime2 = this.parseDateTimes(strEndTime);
            int timehr2 = Integer.parseInt(strTime2[0]);
            timehr2 += 12;
            eTimeFormat = "" + timehr2 + ":" + strTime2[1] + ":" + "00";
        }
        final String strRepeat = request.getParameter("repeat");
        final String strEnddate = request.getParameter("enddate");
        if (!strRepeat.equals("N")) {
            condition = 1;
            if (strRepeat.equals("D")) {
                vRepeat = new Vector(3, 3);
                final String everyday = request.getParameter("everyday");
                final String dayrepeat = request.getParameter("dayrepeat");
                vRepeat.addElement("D");
                vRepeat.addElement(everyday);
                vRepeat.addElement(dayrepeat);
            }
            else if (strRepeat.equals("M")) {
                vRepeat = new Vector(3, 3);
                final String monthrepeat = request.getParameter("monthrepeat");
                final String everymonth = request.getParameter("everymonth");
                final String weekdays = request.getParameter("weekdays");
                vRepeat.addElement("M");
                vRepeat.addElement(everymonth);
                vRepeat.addElement(weekdays);
                vRepeat.addElement(monthrepeat);
            }
            if (strEnddate.equals("yes")) {
                final String uday = request.getParameter("uday");
                final String umonth = request.getParameter("umonth");
                final String strYear = request.getParameter("syear");
                final String endDateFormat = eDateFormat = strYear + "-" + umonth + "-" + uday;
            }
            else {
                CalendarSharedView.log.entry("modifyUser_Calendar()");
                eDateFormat = null;
                CalendarSharedView.log.verbose(" eDateFormat" + eDateFormat);
            }
        }
        final String strReminder = request.getParameter("reminder");
        if (strReminder.equals("yes")) {
            vReminder = new Vector(3, 3);
            condition = 2;
            final String strBReminder = request.getParameter("breminder");
            final String strAReminder = request.getParameter("areminder");
            vReminder.addElement("Y");
            vReminder.addElement(strBReminder);
            vReminder.addElement(strAReminder);
        }
        if (strReminder.equals("yes") && !strRepeat.equals("N")) {
            condition = 3;
        }
        CalDataBaseLayer.updateCalendarName(event_id, calendar_id, student_id, sDateFormat, sTimeFormat, eDateFormat, eTimeFormat, strbody, vRepeat, vReminder, condition);
    }
    
    public void deleteUser_Calendar(final HttpServletRequest request, final HttpServletResponse response, final String strCreatedBy, final PrintWriter out1) throws IOException, ServletException {
        final String event_id = request.getParameter("eventid");
        CalDataBaseLayer.deleteCalendarName(event_id);
    }
    
    private String[] parseDatetime(final String s) {
        final String[] strTime = { s.substring(0, 4), s.substring(4, 6) };
        return strTime;
    }
    
    private String[] parseDateTimes(final String s) {
        final String[] strTime = { s.substring(0, 2), s.substring(2, 4) };
        return strTime;
    }
    
    private String[] parseDate(final String s) {
        final String[] strTime = { s.substring(0, 4), s.substring(5, 7), s.substring(8, 10) };
        return strTime;
    }
    
    static {
        CalendarSharedView.CONTENT_TYPE = "text/html";
        log = new SimpleLogger((Class)CalendarSharedView.class);
    }
}
