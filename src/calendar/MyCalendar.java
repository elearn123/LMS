package calendar.calendar;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import org.apache.ecs.*;
import org.apache.ecs.html.*;
import java.util.*;

public class MyCalendar extends HttpServlet
{
    private static String CONTENT_TYPE;
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        response.setContentType(MyCalendar.CONTENT_TYPE);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        out = response.getWriter();
        if (!New_LoginAppl.checkLogin(request, out)) {
            return;
        }
        final HttpSession mysession = request.getSession(false);
        final String student_id = (String)mysession.getAttribute("student_id");
        if (student_id != null) {
            this.getResult(request, response, out, student_id);
        }
        else {
            response.sendRedirect("../servlet/interfaceenginev2.PortalServlet?IID=LoginPage");
        }
    }
    
    public void getResult(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out, final String student_id) throws IOException, ServletException {
        final String[] months = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        String viewType = request.getParameter("function");
        String strDateType = request.getParameter("date");
        final String caltype = request.getParameter("caltype");
        String calname = request.getParameter("calname");
        if (calname == null) {
            final int cal_id = Integer.parseInt(caltype);
            switch (cal_id) {
                case 0: {
                    final String[] strcalendarname = CalDataBaseLayer.getCalendarName(student_id, "P");
                    calname = strcalendarname[0];
                    break;
                }
                case 1: {
                    final Vector<String[]> vCalendarName1 = CalDataBaseLayer.publicSharedCalendarName(student_id, "S");
                    if (vCalendarName1.size() > 0) {
                        final String[] strCalendarName1 = vCalendarName1.elementAt(0);
                        calname = strCalendarName1[0];
                        break;
                    }
                    break;
                }
            }
        }
        final SampleDate sd = new SampleDate();
        Calendar present = null;
        if (strDateType == null) {
            final Calendar calendar = new GregorianCalendar();
            strDateType = sd.getPresentday(calendar);
            present = sd.parseDate(strDateType);
            viewType = "dayview";
        }
        else {
            present = sd.parseDate(strDateType);
        }
        final String javascript = "\n   \tfunction calendarMethod(viewType , stryear)\t\n \t{\n            window.parent.frames[0].document.location.href=\"./calendar.calendar.CalendarList?function=\"+viewType+\"&date=\"+stryear+\"&caltype=" + caltype + "&calname=" + calname + "\";" + "\n            window.parent.frames[2].document.location.href=\"./calendar.calendar.MyCalendar?function=\"+viewType+\"&date=\"+stryear+\"&caltype=" + caltype + "&calname=" + calname + "\";" + "\n   }" + "\n function addNewCalendar()\t" + "\n {\t" + "\n  \tvar strdate = '" + strDateType + "'  " + "\n        \twindow.parent.frames[2].document.location.href=\"./calendar.calendar.CalendarEnteryForm?function=dayview&date=\"+strdate;" + "\n   }" + "\n function weekdayview(strdate , e_id , p_id)\t" + "\n {\t" + "\n        \twindow.parent.frames[2].document.location.href=\"./calendar.calendar.CalendarEnteryForm?function=dayview&date=\"+strdate+\"&event_id=\"+e_id+\"&type_id=\"+p_id;" + "\n   }" + "\n function sharedweekdayview(strdate , e_id , p_id)\t" + "\n {\t" + "\n        \twindow.parent.frames[2].document.location.href=\"./calendar.calendar.CalendarSharedView?function=dayview&date=\"+strdate+\"&event_id=\"+e_id+\"&type_id=\"+p_id;" + "\n   }" + "\n function addCalendar(e_id, p_id)\t" + "\n {\t" + "\n  \tvar strdate = '" + strDateType + "'  " + "\n        \twindow.parent.frames[2].document.location.href=\"./calendar.calendar.CalendarEnteryForm?function=dayview&date=\"+strdate+\"&event_id=\"+e_id+\"&type_id=\"+p_id;" + "\n   }" + "\n function Quit(){" + "\n\twindow.parent.close();" + "\n\t}" + "\n function sharedCalendar(e_id, p_id)\t" + "\n {\t" + "\n  \tvar strdate = '" + strDateType + "'  " + "\n        \twindow.parent.frames[2].document.location.href=\"./calendar.calendar.CalendarSharedView?function=dayview&date=\"+strdate+\"&event_id=\"+e_id+\"&type_id=\"+p_id;" + "\n   }" + "\n   \tfunction editInfo()\t" + "\n \t{" + "\n   }";
        final Html html = new Html();
        final Head head = new Head().addElement((Element)new Title("Calendar Details Window")).addElement((Element)new Link().setHref("../coreadmin/js/stylesheetcal.css").setRel("stylesheet"));
        final Script script = new Script().setLanguage("javascript").addElement(javascript);
        head.addElement((Element)script);
        final Body body = new Body();
        final Form form = new Form().setName("frm").setMethod("post");
        final Input btnAddPCal = new Input().setClassId("pbttn").setName("addPersonal").setType("button").setValue("Add Entry");
        btnAddPCal.setOnClick("addNewCalendar()");
        final Table tblview = new Table().setWidth("100%");
        if (calname != null) {
            if (!calname.equals("")) {
                if (viewType.equals("dayview")) {
                    final int cal_type = Integer.parseInt(caltype);
                    switch (cal_type) {
                        case 0: {
                            String cal_name = "";
                            final int cal_id2 = Integer.parseInt(calname);
                            cal_name = CalDataBaseLayer.getCalendarName(cal_id2);
                            final String monthname = sd.getDate(present);
                            System.out.println("=================monthname=========" + monthname);
                            System.out.println("=================present=========" + present);
                            final String strDateFormat = sd.calDateFormat(present);
                            System.out.println("=================strDateFormat=========" + strDateFormat);
                            tblview.addElement((Element)new TR().setBgColor("#669933").addElement((Element)new TD().addElement((Element)new Font().setFace("verdana").setSize(-1).setColor("#FFFFFF").addElement((Element)new B().addElement("Personal Calendar:")))).addElement((Element)new TD().addElement((Element)new Font().setColor("#FFCC33").setFace("verdana").setSize(-1).addElement(cal_name).addElement("&nbsp;&nbsp;&nbsp;&nbsp;"))).addElement((Element)new TD().addElement((Element)new B().addElement((Element)new Font().setFace("verdana").setColor("#FFFFFF").setSize(-1).addElement("Dated:").addElement("&nbsp;&nbsp;&nbsp;&nbsp;")))).addElement((Element)new TD().addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFCC33").setFace("verdana").setSize(-1).addElement(monthname)))));
                            tblview.addElement((Element)new TR().setBgColor("#669933").addElement((Element)new TD().addElement((Element)new Font().setFace("verdana").setSize(-1).setColor("#FFFFFF").addElement((Element)new B().addElement("Start Time:&nbsp;End Time")))).addElement((Element)new TD().setColSpan(3).addElement((Element)new Font().setFace("verdana").setSize(-1).setColor("#FFFFFF").addElement((Element)new B().addElement("Calendar Events")))));
                            final Vector<String[]> vDayview = CalDataBaseLayer.dayviewInfo(cal_id2, strDateFormat, student_id);
                            for (int i = 0; i < vDayview.size(); ++i) {
                                final String[] strdayview = vDayview.elementAt(i);
                                tblview.addElement((Element)new CalTRExtension().addDayviewPersonal(strdayview[0], strdayview[1], strdayview[2], strdayview[4]));
                            }
                            tblview.addElement((Element)new TR().setAlign("center").addElement((Element)new TD().setColSpan(4).addElement("&nbsp;"))).addElement((Element)new TR().setAlign("center").addElement((Element)new TD().setColSpan(4).addElement("&nbsp;"))).addElement((Element)new TR().setAlign("center").addElement((Element)new TD().setColSpan(4).addElement("&nbsp;")));
                            final Vector vCalendarName2 = CalDataBaseLayer.publicPersonalCalendarName(student_id, "P");
                            if (vCalendarName2.size() > 0) {
                                tblview.addElement((Element)new TR().setAlign("center").addElement((Element)new TD().setColSpan(4).addElement((Element)btnAddPCal)));
                                break;
                            }
                            break;
                        }
                        case 1: {
                            final int cal_id3 = Integer.parseInt(calname);
                            final String cal_name2 = CalDataBaseLayer.getCalendarName(cal_id3);
                            final String monthname = sd.getDate(present);
                            final String strDateFormat = sd.calDateFormat(present);
                            tblview.addElement((Element)new TR().setBgColor("#669933").addElement((Element)new TD().addElement((Element)new NOBR().addElement((Element)new Font().setFace("verdana").setSize(-1).setColor("#FFFFFF").addElement((Element)new B().addElement("Shared Calendar:"))))).addElement((Element)new TD().addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFCC33").setFace("verdana").setSize(-1).addElement(cal_name2).addElement("&nbsp;&nbsp;&nbsp;&nbsp;")))).addElement((Element)new TD().addElement((Element)new NOBR().addElement((Element)new B().addElement((Element)new Font().setFace("verdana").setColor("#FFFFFF").setSize(-1).addElement("Dated:").addElement("&nbsp;&nbsp;&nbsp;&nbsp;"))))).addElement((Element)new TD().addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFCC33").setFace("verdana").setSize(-1).addElement(monthname)))));
                            tblview.addElement((Element)new TR().setBgColor("#669933").addElement((Element)new TD().addElement((Element)new NOBR().addElement((Element)new Font().setFace("verdana").setSize(-1).setColor("#FFFFFF").addElement((Element)new B().addElement("Start Time:&nbsp;End Time"))))).addElement((Element)new TD().setColSpan(3).addElement((Element)new NOBR().addElement((Element)new Font().setFace("verdana").setSize(-1).setColor("#FFFFFF").addElement((Element)new B().addElement("Calendar Events"))))));
                            final Vector<String[]> vDayviewShared = CalDataBaseLayer.dayviewInfo(cal_id3, strDateFormat);
                            for (int i = 0; i < vDayviewShared.size(); ++i) {
                                final String[] strdayview = vDayviewShared.elementAt(i);
                                tblview.addElement((Element)new CalTRExtension().addDayviewPublic(strdayview[0], strdayview[1], strdayview[2], strdayview[4]));
                            }
                            tblview.addElement((Element)new TR().setAlign("center").addElement((Element)new TD().setColSpan(4).addElement("&nbsp;"))).addElement((Element)new TR().setAlign("center").addElement((Element)new TD().setColSpan(4).addElement("&nbsp;"))).addElement((Element)new TR().setAlign("center").addElement((Element)new TD().setColSpan(4).addElement("&nbsp;")));
                            break;
                        }
                    }
                }
                else if (viewType.equals("weekview")) {
                    final int cal_type = Integer.parseInt(caltype);
                    final String weekofMonth = present.get(4) + " Week of " + months[sd.getMonth(present)] + ", " + sd.getYear(present);
                    final int cal_id2 = Integer.parseInt(calname);
                    final String cal_name3 = CalDataBaseLayer.getCalendarName(cal_id2);
                    switch (cal_type) {
                        case 0: {
                            tblview.addElement((Element)new TR().setBgColor("#6699933").addElement((Element)new TD().setColSpan(7).addElement((Element)new NOBR().addElement((Element)new Font().setFace("verdana").setSize(-1).setColor("#FFFFFF").addElement((Element)new B().addElement("Personal Calendar:&nbsp;&nbsp;&nbsp;&nbsp;")))).addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFCC33").setFace("verdana").setSize(-1).addElement(cal_name3).addElement("&nbsp;&nbsp;&nbsp;&nbsp;"))).addElement((Element)new NOBR().addElement((Element)new B().addElement((Element)new Font().setFace("verdana").setColor("#FFFFFF").setSize(-1).addElement("Dated:").addElement("&nbsp;&nbsp;&nbsp;&nbsp;")))).addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFCC33").setFace("verdana").setSize(-1).addElement(weekofMonth)))));
                            final Vector<String[]> vWeekdata = sd.weekData(strDateType);
                            this.headingTable(vWeekdata, tblview);
                            final TR tr = new TR().setAlign("center");
                            for (int i = 0; i < vWeekdata.size(); ++i) {
                                final TD td = new TD().setWidth(14).setVAlign("top").setNoWrap(true).setAlign("left").setHeight(15);
                                final String[] strWeek = vWeekdata.elementAt(i);
                                final String strdate = this.parseDate(strWeek[1]);
                                final Vector<String[]> vDayview2 = CalDataBaseLayer.dayviewInfo(cal_id2, strdate, student_id);
                                if (vDayview2.size() < 1) {
                                    td.addElement("&nbsp;");
                                }
                                for (int j = 0; j < vDayview2.size(); ++j) {
                                    final String row = "" + (j + 1);
                                    final String[] strdayview2 = vDayview2.elementAt(j);
                                    td.addElement((Element)new Font().setSize(-2).setFace("verdana").addElement(row).addElement("&nbsp;").addElement(strdayview2[1]));
                                    td.addElement((Element)new A().setHref("javascript:weekdayview('" + strWeek[1] + "','" + strdayview2[0] + "','F');").addElement((Element)new Font().setSize(-2).setFace("verdana").setColor("#0000FF").addElement("&nbsp;").addElement(strdayview2[2]))).addElement((Element)new BR());
                                }
                                tr.addElement((Element)td);
                            }
                            tblview.addElement((Element)tr);
                            break;
                        }
                        case 1: {
                            tblview.addElement((Element)new TR().setBgColor("#669933").addElement((Element)new TD().addElement((Element)new NOBR().addElement((Element)new Font().setFace("verdana").setSize(-1).setColor("#FFFFFF").addElement((Element)new B().addElement("Shared Calendar:"))))).addElement((Element)new TD().setColSpan(2).addElement((Element)new NOBR().addElement((Element)new B().addElement((Element)new Font().setColor("#FFCC33").setFace("verdana").setSize(-1).addElement(cal_name3).addElement("&nbsp;&nbsp;&nbsp;&nbsp;"))))).addElement((Element)new TD().addElement((Element)new NOBR().addElement((Element)new B().addElement((Element)new Font().setFace("verdana").setColor("#FFFFFF").setSize(-1).addElement("Dated:").addElement("&nbsp;&nbsp;&nbsp;&nbsp;"))))).addElement((Element)new TD().setColSpan(3).addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFCC33").setFace("verdana").setSize(-1).addElement((Element)new B().addElement(weekofMonth))))));
                            final Vector<String[]> vWeekdata2 = sd.weekData(strDateType);
                            this.headingTable(vWeekdata2, tblview);
                            final TR tr = new TR().setAlign("center");
                            for (int i = 0; i < vWeekdata2.size(); ++i) {
                                final TD td = new TD().setWidth(14).setVAlign("top").setNoWrap(true).setAlign("left").setHeight(15).addElement("&nbsp;");
                                final String[] strWeek = vWeekdata2.elementAt(i);
                                final String strdate = this.parseDate(strWeek[1]);
                                final Vector<String[]> vDayview2 = CalDataBaseLayer.dayviewInfo(cal_id2, strdate);
                                for (int j = 0; j < vDayview2.size(); ++j) {
                                    final String row = "" + (j + 1);
                                    final String[] strdayview2 = vDayview2.elementAt(j);
                                    td.addElement((Element)new Font().setSize(-2).setFace("verdana").addElement(row).addElement("&nbsp;").addElement(strdayview2[1]));
                                    td.addElement((Element)new A().setHref("javascript:sharedweekdayview('" + strWeek[1] + "','" + strdayview2[0] + "','T');").addElement((Element)new Font().setSize(-2).setFace("verdana").setColor("#0000FF").addElement(strdayview2[2]).addElement("&nbsp;"))).addElement((Element)new BR());
                                }
                                tr.addElement((Element)td);
                            }
                            tblview.addElement((Element)tr);
                            break;
                        }
                    }
                }
                else if (viewType.equals("monthview")) {
                    final String monthname2 = months[sd.getMonth(present)] + ", " + sd.getYear(present);
                    tblview.addElement((Element)new Caption().setAlign("top").addElement((Element)new B().addElement((Element)new Font().setColor("CC0000").setSize(-1).setFace("verdana").addElement(monthname2))));
                    final Vector vMonth = sd.monthData(strDateType);
                    tblview.setBorder(0).setCellPadding(0).addElement((Element)new CalTRExtension().addHeadingDays(" Sunday ", " Monday ", " Tuesday ", " Wednesday ", " Thursday ", " Friday ", " Saturday ", 3));
                    final String[] strMonth = (String[]) vMonth.elementAt(0);
                    for (int count = 1; count < vMonth.size(); ++count) {
                        final Vector vWeekdays = (Vector) vMonth.elementAt(count);
                        tblview.addElement((Element)new CalTRExtension().addWeekviewdays(vWeekdays, 2));
                    }
                }
                else if (viewType.equals("yearview")) {
                    final Vector vYearMonth = sd.yearData(strDateType);
                    final String monthname3 = "" + sd.getYear(present);
                    tblview.addElement((Element)new Caption().setAlign("top").addElement((Element)new B().addElement((Element)new Font().setSize(-1).setColor("CC0000").setFace("verdana").addElement(monthname3))));
                    tblview.setBorder(0);
                    for (int k = 0; k < 12; k += 3) {
                        final Vector vMonth2 = (Vector) vYearMonth.elementAt(k);
                        final Vector vMonth3 = (Vector) vYearMonth.elementAt(k + 1);
                        final Vector vMonth4 = (Vector) vYearMonth.elementAt(k + 2);
                        final String[] strmonthName = (String[]) vMonth2.elementAt(0);
                        final Vector vMonthName = (Vector) vMonth2.elementAt(1);
                        final String[] strmonthName2 = (String[]) vMonth3.elementAt(0);
                        final Vector vMonthName2 = (Vector) vMonth3.elementAt(1);
                        final String[] strmonthName3 = (String[]) vMonth4.elementAt(0);
                        final Vector vMonthName3 = (Vector) vMonth4.elementAt(1);
                        tblview.addElement((Element)new TR().addElement((Element)new TD().addElement((Element)new CalTableExtension().addCalendarMonth(vMonthName, strmonthName[0], 1))).addElement((Element)new TD().addElement((Element)new CalTableExtension().addCalendarMonth(vMonthName2, strmonthName2[0], 1))).addElement((Element)new TD().addElement((Element)new CalTableExtension().addCalendarMonth(vMonthName3, strmonthName3[0], 1))));
                    }
                }
            }
            else {
                tblview.addElement((Element)new TR().addElement((Element)new TD().addElement((Element)new Font().setFace("verdana").setSize(1).addElement("&nbsp; "))));
            }
        }
        else {
            tblview.addElement((Element)new TR().addElement((Element)new TD().addElement((Element)new Font().setFace("verdana").setSize(1).addElement("&nbsp;"))));
        }
        final Table quittable = new Table().setWidth("100%");
        quittable.addElement((Element)new TR().addElement((Element)new TD().setWidth("90%")).addElement((Element)new TD().addElement((Element)new A().setHref("JavaScript:Quit();").addElement("Quit"))));
        form.addElement((Element)quittable);
        form.addElement((Element)tblview);
        body.addElement((Element)form);
        html.addElement((Element)head);
        html.addElement((Element)body);
        out.print(html.toString());
        out.close();
    }
    
    private String parseDate(final String s) {
        final String s2 = s.substring(0, 4);
        final String s3 = s.substring(4, 6);
        final String s4 = s.substring(6, 8);
        final String strdate = s2 + "-" + s3 + "-" + s4;
        return strdate;
    }
    
    private void headingTable(final Vector vWeekdata, final Table tblview) {
        tblview.setBorder(0).addElement((Element)new CalTRExtension().addHeadingDays1(" Sunday ", " Monday ", " Tuesday ", " Wednesday ", " Thursday ", " Friday ", " Saturday ", 3));
        tblview.addElement((Element)new CalTRExtension().addWeekviewdate(vWeekdata));
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    static {
        MyCalendar.CONTENT_TYPE = "text/html";
    }
}
