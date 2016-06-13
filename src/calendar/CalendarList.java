package calendar.calendar;

import org.grlea.log.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import org.apache.ecs.*;
import org.apache.ecs.html.*;
import java.util.*;

public class CalendarList extends HttpServlet
{
    private static String CONTENT_TYPE;
    private static final SimpleLogger log;
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        response.setContentType(CalendarList.CONTENT_TYPE);
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
        String viewType = request.getParameter("function");
        String strDateType = request.getParameter("date");
        final String caltype = request.getParameter("caltype");
        final String calname = request.getParameter("calname");
        String strcalendar_id = "";
        int setYear = 0;
        int setMonth = 0;
        int setDate = 0;
        String presentday = "";
        String pastday = "";
        String futureday = "";
        final Calendar calendarnew = new GregorianCalendar();
        System.out.println("==========calendarnew========" + calendarnew.get(2));
        final String[] months = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        String[] strThreeyears = null;
        String[] strThreeyears2 = null;
        String[] strThreeyears3 = null;
        final SampleDate sampledate = new SampleDate();
        final SampleDate sd = new SampleDate();
        if (viewType == null && strDateType == null) {
            strThreeyears = sampledate.getNextPreviousCurrentDate(calendarnew);
            strThreeyears2 = sd.getNextPreviousCurrentMonth(calendarnew);
            strThreeyears3 = sampledate.getNextPreviousCurrentDate1(calendarnew);
            CalendarList.log.entry("getResult()");
            System.out.println("**************strThreeyears0*****************" + strThreeyears2[0]);
            System.out.println("**************strThreeyears1*****************" + strThreeyears2[1]);
            System.out.println("**************strThreeyears2*****************" + strThreeyears2[2]);
            strDateType = strThreeyears[1];
            final SampleDate sd2 = new SampleDate();
            final Calendar present = sd2.parseDate(strThreeyears2[1]);
            presentday = months[sd2.getMonth(present)] + ", " + sd2.getYear(present);
            CalendarList.log.verbose("***presentday*****" + presentday);
            final Calendar past = sd2.parseDate(strThreeyears2[0]);
            pastday = months[sd2.getMonth(past)] + ", " + sd2.getYear(past);
            CalendarList.log.verbose("***pastday*****" + pastday);
            final Calendar future = sd2.parseDate(strThreeyears2[2]);
            futureday = months[sd2.getMonth(future)] + ", " + sd2.getYear(future);
            CalendarList.log.verbose("***futureday*****" + futureday);
            viewType = "dayview";
            setYear = sampledate.getYear(calendarnew);
            setMonth = sampledate.getMonth(calendarnew);
            setDate = sampledate.getDays(calendarnew);
        }
        else if (viewType.equals("dayview")) {
            final Calendar oCalendar = sampledate.parseDate(strDateType);
            strThreeyears = sampledate.getNextPreviousCurrentDate(oCalendar);
            strThreeyears2 = sd.getNextPreviousCurrentMonth(oCalendar);
            strThreeyears3 = sampledate.getNextPreviousCurrentDate1(oCalendar);
            final SampleDate sd3 = new SampleDate();
            final Calendar present2 = sd3.parseDate(strThreeyears2[1]);
            presentday = months[sd3.getMonth(present2)] + ", " + sd3.getYear(present2);
            final Calendar past2 = sd3.parseDate(strThreeyears2[0]);
            pastday = months[sd3.getMonth(past2)] + ", " + sd3.getYear(past2);
            final Calendar future2 = sd3.parseDate(strThreeyears2[2]);
            futureday = months[sd3.getMonth(future2)] + ", " + sd3.getYear(future2);
            setYear = sampledate.getYear(oCalendar);
            setMonth = sampledate.getMonth(oCalendar);
            setDate = sampledate.getDays(oCalendar);
        }
        else if (viewType.equals("weekview")) {
            final Calendar oCalendar = sampledate.parseDate(strDateType);
            strThreeyears = sampledate.getNextPreviousCurrentWeek(oCalendar);
            strThreeyears2 = sd.getNextPreviousCurrentMonth(oCalendar);
            strThreeyears3 = sampledate.getNextPreviousCurrentWeek1(oCalendar);
            setYear = sampledate.getYear(oCalendar);
            setMonth = sampledate.getMonth(oCalendar);
            setDate = sampledate.getDays(oCalendar);
            final SampleDate sd3 = new SampleDate();
            final Calendar present2 = sd3.parseDate(strThreeyears2[1]);
            presentday = months[sd3.getMonth(present2)] + ", " + sd3.getYear(present2);
            final Calendar past2 = sd3.parseDate(strThreeyears2[0]);
            pastday = months[sd3.getMonth(past2)] + ", " + sd3.getYear(past2);
            final Calendar future2 = sd3.parseDate(strThreeyears2[2]);
            futureday = months[sd3.getMonth(future2)] + ", " + sd3.getYear(future2);
        }
        else if (viewType.equals("monthview")) {
            final Calendar oCalendar = sampledate.parseDate(strDateType);
            strThreeyears = sampledate.getNextPreviousCurrentMonth(oCalendar);
            strThreeyears2 = sd.getNextPreviousCurrentMonth(oCalendar);
            strThreeyears3 = sampledate.getNextPreviousCurrentMonth1(oCalendar);
            setYear = sampledate.getYear(oCalendar);
            setMonth = sampledate.getMonth(oCalendar);
            setDate = sampledate.getDays(oCalendar);
            final SampleDate sd3 = new SampleDate();
            final Calendar present2 = sd3.parseDate(strThreeyears2[1]);
            presentday = months[sd3.getMonth(present2)] + ", " + sd3.getYear(present2);
            final Calendar past2 = sd3.parseDate(strThreeyears2[0]);
            pastday = months[sd3.getMonth(past2)] + ", " + sd3.getYear(past2);
            final Calendar future2 = sd3.parseDate(strThreeyears2[2]);
            futureday = months[sd3.getMonth(future2)] + ", " + sd3.getYear(future2);
        }
        else if (viewType.equals("yearview")) {
            final Calendar oCalendar = sampledate.parseDate(strDateType);
            strThreeyears = sampledate.getNextPreviousCurrentYear(oCalendar);
            strThreeyears2 = sd.getNextPreviousCurrentYear(oCalendar);
            strThreeyears3 = sampledate.getNextPreviousCurrentYear1(oCalendar);
            setYear = sampledate.getYear(oCalendar);
            setMonth = sampledate.getMonth(oCalendar);
            setDate = sampledate.getDays(oCalendar);
            final SampleDate sd3 = new SampleDate();
            final Calendar present2 = sd3.parseDate(strThreeyears2[1]);
            presentday = months[sd3.getMonth(present2)] + ", " + sd3.getYear(present2);
            final Calendar past2 = sd3.parseDate(strThreeyears2[0]);
            pastday = months[sd3.getMonth(past2)] + ", " + sd3.getYear(past2);
            final Calendar future2 = sd3.parseDate(strThreeyears2[2]);
            futureday = months[sd3.getMonth(future2)] + ", " + sd3.getYear(future2);
        }
        final String[] strCalendar = { "Personal ", "Shared " };
        final Vector<String[]> vCalendarName = CalDataBaseLayer.publicPersonalCalendarName(student_id, "P");
        final Vector<String[]> vCalendarName2 = CalDataBaseLayer.publicSharedCalendarName(student_id, "S");
        final Option[] option1 = new Option[strCalendar.length];
        Option[] option2 = null;
        final Select calendarType = new Select("calendarType").setClassId("drpdwn");
        final Select canlendarname = new Select("canlendarname").setClassId("drpdwn");
        int valType = 0;
        if (caltype == null) {
            option2 = new Option[vCalendarName.size()];
            for (int i = 0; i < vCalendarName.size(); ++i) {
                final String[] strCalendarName = vCalendarName.elementAt(i);
                if (i == 0) {
                    option2[i] = new Option(strCalendarName[0]).addElement(strCalendarName[1]).setSelected(true);
                    strcalendar_id = strCalendarName[0];
                }
                else {
                    option2[i] = new Option(strCalendarName[0]).addElement(strCalendarName[1]);
                }
                canlendarname.addElement((Element)option2[i]);
            }
        }
        else {
            valType = Integer.parseInt(caltype);
            if (valType == 0) {
                option2 = new Option[vCalendarName.size()];
                for (int i = 0; i < vCalendarName.size(); ++i) {
                    final String[] strCalendarName = vCalendarName.elementAt(i);
                    if (strCalendarName[0].equals(calname)) {
                        option2[i] = new Option(strCalendarName[0]).addElement(strCalendarName[1]).setSelected(true);
                        strcalendar_id = strCalendarName[0];
                    }
                    else {
                        option2[i] = new Option(strCalendarName[0]).addElement(strCalendarName[1]);
                    }
                    canlendarname.addElement((Element)option2[i]);
                }
            }
            else {
                option2 = new Option[vCalendarName2.size()];
                for (int i = 0; i < vCalendarName2.size(); ++i) {
                    final String[] strCalendarName = vCalendarName2.elementAt(i);
                    if (strCalendarName[0].equals(calname)) {
                        option2[i] = new Option(strCalendarName[0]).addElement(strCalendarName[1]).setSelected(true);
                        strcalendar_id = strCalendarName[0];
                    }
                    else {
                        option2[i] = new Option(strCalendarName[0]).addElement(strCalendarName[1]);
                    }
                    canlendarname.addElement((Element)option2[i]);
                }
            }
        }
        for (int i = 0; i < strCalendar.length; ++i) {
            calendarType.addElement((Element)(option1[i] = new Option("" + i).addElement(strCalendar[i])));
        }
        option1[valType].setSelected(true);
        final String javascript = "\n   \tfunction calendarMethod(viewType , stryear)\t\n \t{\n  \tvar cal = document.frm.calendarType.value;\t \n  \tvar nm =0  \n if(document.frm.canlendarname == null){ \n } \n else { \n  \t nm = document.frm.canlendarname.value;\t \n } \n     window.parent.frames[0].document.location.href=\"./calendar.calendar.CalendarList?function=dayview&date=\"+stryear+\"&caltype=\"+cal+\"&calname=\"+nm;\n    \twindow.parent.frames[2].document.location.href=\"./calendar.calendar.MyCalendar?function=\"+viewType+\"&date=\"+stryear+\"&caltype=\"+cal+\"&calname=\"+nm;\n   }\n function changemonth()  \n {\t\n  \tvar cal = document.frm.calendarType.value;\t \n  \tvar nm =0  \n if(document.frm.canlendarname == null){ \n } \n else { \n  \t nm = document.frm.canlendarname.value;\t \n } \n  \tvar strdate =document.frm.syear.value + document.frm.smonth.value +document.frm.sday.value;\n     window.parent.frames[0].document.location.href=\"./calendar.calendar.CalendarList?function=dayview&date=\"+strdate+\"&caltype=\"+cal+\"&calname=\"+nm;\n     window.parent.frames[2].document.location.href=\"./calendar.calendar.MyCalendar?function=dayview&date=\"+strdate;\n   }\n function calendar_onchange() \n {\t\n  \tvar strdate = '" + strDateType + "'  " + "\n  \tvar strcalendar_id = '" + strcalendar_id + "'  " + "\n  \tvar cal = document.frm.calendarType.value;\t " + "\n  \tvar nm = strcalendar_id " + "\n    \twindow.parent.frames[0].document.location.href=\"./calendar.calendar.CalendarList?change=Y&function=dayview&date=\"+strdate+\"&caltype=\"+cal+\"&calname=\"+nm;" + "\n    \twindow.parent.frames[2].document.location.href=\"./calendar.calendar.MyCalendar?function=dayview&date=\"+strdate+\"&caltype=\"+cal;" + "\n   }" + "\n function calendarname_onchange()  " + "\n {\t" + "\n  \tvar cal = document.frm.calendarType.value;\t " + "\n  \tvar nm =0  " + "\n if(document.frm.canlendarname == null){ " + "\n } " + "\n else { " + "\n  \t nm = document.frm.canlendarname.value;\t " + "\n } " + "\n  \tvar strdate = '" + strDateType + "'  " + "\n    \twindow.parent.frames[2].document.location.href=\"./calendar.calendar.MyCalendar?function=dayview&date=\"+strdate+\"&caltype=\"+cal+\"&calname=\"+nm;" + "\n   }" + "\n function addCalendar()\t" + "\n {\t" + "\n  \tvar strdate = '" + strDateType + "'  " + "\n        \twindow.parent.frames[2].document.location.href=\"./calendar.calendar.CalendarEnteryForm?function=dayview&date=\"+strdate;" + "\n   }" + "\n function addManageCalendar()\t" + "\n {\t" + "\n  \tvar strdate = '" + strDateType + "'  " + "\n        \twindow.parent.frames[2].document.location.href=\"./calendar.calendar.ManageCalendar?function=dayview&date=\"+strdate;" + "\n   }";
        final Input btnGo = new Input().setType("button").setClassId("drpdwn").setName("btngo").setValue("Go");
        btnGo.setOnClick("changemonth()");
        final Input btnAddPCal = new Input().setClassId("pbttn").setName("addPersonal").setType("button").setValue("Add Entery");
        final Input btnManageCal = new Input().setClassId("pbttn").setName("managePersonal").setType("button").setValue("Manage Personal Calendar").setSize(25);
        btnAddPCal.setOnClick("addCalendar()");
        btnManageCal.setOnClick("addManageCalendar()");
        calendarType.setOnChange("calendar_onchange()");
        canlendarname.setOnChange("calendarname_onchange()");
        final TD td = new TD();
        if (vCalendarName.size() > 0 && valType == 0) {
            td.addElement((Element)canlendarname);
        }
        else if (vCalendarName2.size() > 0 && valType == 1) {
            td.addElement((Element)canlendarname);
        }
        else {
            td.addElement("&nbsp;");
        }
        final Table tblCalendarName = new Table().setBorder(0).setCellPadding(0).setCellSpacing(0).setWidth("100%").addElement((Element)new TR().addElement((Element)new TD().setAlign("center").setColSpan(2).addElement((Element)new B().addElement((Element)new Font().setFace("verdana").setSize(-2).addElement("View Calendar"))))).addElement((Element)new TR().addElement((Element)new TD().addElement((Element)calendarType)).addElement((Element)td));
        final Html html = new Html();
        final Head head = new Head().addElement((Element)new Title("Calendar Details Window")).addElement((Element)new Link().setHref("../js/stylesheet.css").setRel("stylesheet"));
        final Script script = new Script().setLanguage("javascript").addElement(javascript);
        head.addElement((Element)script);
        final Body body = new Body().setBgColor("#FFFFFF");
        final Form form = new Form().setName("frm").setMethod("post");
        final Table tblList = new Table().setBorder(0).setCellPadding(0).setCellSpacing(0).setWidth("95%").addElement((Element)new TR().addElement((Element)new TD().setAlign("center").addElement((Element)new B().addElement((Element)new Font().setFace("verdana").setSize(-2).addElement((Element)new A().setHref("javascript:calendarMethod('dayview','" + strThreeyears[1] + "');").addElement("dd"))))).addElement((Element)new TD().setAlign("center").addElement((Element)new B().addElement((Element)new Font().setFace("verdana").setSize(-2).addElement((Element)new A().setHref("javascript:calendarMethod('weekview','" + strThreeyears[1] + "');").addElement("wk"))))).addElement((Element)new TD().setAlign("center").addElement((Element)new B().addElement((Element)new Font().setFace("verdana").setSize(-2).addElement((Element)new A().setHref("javascript:calendarMethod('monthview','" + strThreeyears[1] + "');").addElement("mm"))))).addElement((Element)new TD().setAlign("center").addElement((Element)new B().addElement((Element)new Font().setFace("verdana").setSize(-2).addElement((Element)new A().setHref("javascript:calendarMethod('yearview','" + strThreeyears[1] + "');").addElement("yy")))))).addElement((Element)new TR().addElement((Element)new TD().setColSpan(4).addElement("&nbsp"))).addElement((Element)new TR().addElement((Element)new TD().setColSpan(3).addElement((Element)new CalTableExtension().addCalendarMonth(sd.monthData1(strThreeyears2[0]), pastday, -2))).addElement((Element)new TD().setAlign("center").addElement((Element)new A().setHref("javascript:calendarMethod('" + viewType + "','" + strThreeyears2[0] + "');").addElement("<img id=\"prev-image\" height=\"19\" width=\"22\" border=\"0\" alt=\"Previous\" src=\"../coreadmin/images/up.gif\">")))).addElement((Element)new TR().addElement((Element)new TD().setColSpan(4).addElement((Element)new CalTableExtension().addCalendarMonth(sd.monthData1(strThreeyears2[1]), presentday, -2)))).addElement((Element)new TR().addElement((Element)new TD().setColSpan(3).addElement((Element)new CalTableExtension().addCalendarMonth(sd.monthData1(strThreeyears2[2]), futureday, -2))).addElement((Element)new TD().setAlign("center").addElement((Element)new A().setHref("javascript:calendarMethod('" + viewType + "','" + strThreeyears2[2] + "');").addElement("<img id=\"prev-image\" height=\"19\" width=\"22\" border=\"0\" alt=\"Next\" src=\"../coreadmin/images/dn.gif\">")))).addElement((Element)new TR().addElement((Element)new TD().setColSpan(4).addElement((Element)new BR()).addElement((Element)tblCalendarName))).addElement((Element)new TR().addElement((Element)new TD().setColSpan(4).setAlign("center").addElement((Element)new BR()).addElement((Element)btnManageCal).addElement("&nbsp;")));
        form.addElement((Element)tblList);
        body.addElement((Element)form);
        html.addElement((Element)head);
        html.addElement((Element)body);
        out.print(html.toString());
        out.close();
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    static {
        CalendarList.CONTENT_TYPE = "text/html";
        log = new SimpleLogger((Class)CalendarList.class);
    }
}
