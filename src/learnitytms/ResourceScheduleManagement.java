package learnitytms;

import javax.servlet.*;
import org.apache.ecs.*;
import org.apache.ecs.html.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class ResourceScheduleManagement extends HttpServlet
{
    private static String CONTENT_TYPE;
    private static final String LOGIN_SESSION_NAME = "user_id";
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        response.setContentType(ResourceScheduleManagement.CONTENT_TYPE);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        out = response.getWriter();
        final HttpSession mysession = request.getSession(true);
        final Object obj = mysession.getAttribute("user_id");
        if (obj == null) {
            response.sendRedirect("../servlet/interfaceenginev2.PortalServlet?IID=LoginPage");
        }
        String viewType = request.getParameter("function");
        String strDateType = request.getParameter("date");
        final String caltype = request.getParameter("caltype");
        final String calname = request.getParameter("calname");
        System.out.println("viewType===" + viewType);
        System.out.println("strDateType===" + strDateType);
        System.out.println("caltype===" + caltype);
        System.out.println("calname===" + calname);
        final String[] vres = request.getParameterValues("resourceid_hidden");
        System.out.println("vres.size()******" + vres.length);
        System.out.println("vres===" + vres[0]);
        String strcalendar_id = "";
        String presentday = "";
        final Calendar calendarnew = new GregorianCalendar();
        final String[] months = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        String[] strThreeyears = null;
        String[] strThreeyears2 = null;
        final SampleDate sampledate = new SampleDate();
        final SampleDate sd = new SampleDate();
        if (viewType == null && strDateType == null) {
            strThreeyears = sampledate.getNextPreviousCurrentDate(calendarnew);
            strThreeyears2 = sd.getNextPreviousCurrentMonth(calendarnew);
            strDateType = strThreeyears[1];
            final SampleDate sd2 = new SampleDate();
            final Calendar present = sd2.parseDate(strThreeyears2[1]);
            presentday = months[sd2.getMonth(present)] + ", " + sd2.getYear(present);
            viewType = "dayview";
        }
        else if (viewType.equals("dayview")) {
            final Calendar oCalendar = sampledate.parseDate(strDateType);
            strThreeyears = sampledate.getNextPreviousCurrentDate(oCalendar);
            strThreeyears2 = sd.getNextPreviousCurrentMonth(oCalendar);
            final SampleDate sd3 = new SampleDate();
            final Calendar present2 = sd3.parseDate(strThreeyears2[1]);
            presentday = months[sd3.getMonth(present2)] + ", " + sd3.getYear(present2);
        }
        else if (viewType.equals("weekview")) {
            final Calendar oCalendar = sampledate.parseDate(strDateType);
            strThreeyears = sampledate.getNextPreviousCurrentWeek(oCalendar);
            strThreeyears2 = sd.getNextPreviousCurrentMonth(oCalendar);
            final SampleDate sd3 = new SampleDate();
            final Calendar present2 = sd3.parseDate(strThreeyears2[1]);
            presentday = months[sd3.getMonth(present2)] + ", " + sd3.getYear(present2);
        }
        else if (viewType.equals("monthview")) {
            final Calendar oCalendar = sampledate.parseDate(strDateType);
            strThreeyears = sampledate.getNextPreviousCurrentMonth(oCalendar);
            strThreeyears2 = sd.getNextPreviousCurrentMonth(oCalendar);
            final SampleDate sd3 = new SampleDate();
            final Calendar present2 = sd3.parseDate(strThreeyears2[1]);
            presentday = months[sd3.getMonth(present2)] + ", " + sd3.getYear(present2);
        }
        else if (viewType.equals("yearview")) {
            final Calendar oCalendar = sampledate.parseDate(strDateType);
            strThreeyears = sampledate.getNextPreviousCurrentYear(oCalendar);
            strThreeyears2 = sd.getNextPreviousCurrentYear(oCalendar);
            final SampleDate sd3 = new SampleDate();
            final Calendar present2 = sd3.parseDate(strThreeyears2[1]);
            presentday = months[sd3.getMonth(present2)] + ", " + sd3.getYear(present2);
        }
        final String[] strCalendar = { "Personal ", "Shared " };
        final Vector<String[]> vCalendarName = DataBaseLayer.publicPersonalCalendarName("", "P");
        final Vector<String[]> vCalendarName2 = DataBaseLayer.publicSharedCalendarName("", "S");
        final Option[] option1 = new Option[strCalendar.length];
        Option[] option2 = null;
        final Select calendarType = new Select();
        calendarType.setName("calendar1Type").setClassId("drpdwn");
        final Select canlendarname = new Select("canlendarname").setClassId("drpdwn");
        int valType = 0;
        if (caltype == null) {
            System.out.println("calname  vCalendarName =" + vCalendarName.size());
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
        final String javascript = "\n   \tfunction calendarMethod(viewType , stryear)\t\n \t{\n  \tvar nm =0  \n if(document.frm.canlendarname == null){ \n } \n else { \n  \t nm = document.frm.canlendarname.value;\t \n } \n\t\tdocument.frm.method=\"post\";\n\t\tdocument.frm.action=\"./learnitytms.ResourceScheduleManagement?function=dayview&date=\"+stryear+\"&calname=\"+nm;\n\t\tdocument.frm.submit();\n   }\n function changemonth()  \n {\t\n  \tvar cal = document.frm.calendar1Type.value;\t \n  \tvar nm =0  \n if(document.frm.canlendarname == null){ \n } \n else { \n  \t nm = document.frm.canlendarname.value;\t \n } \n  \tvar strdate =document.frm.syear.value + document.frm.smonth.value +document.frm.sday.value;\n     window.parent.frames[0].document.location.href=\"./learnitytms.ResourceScheduleManagement?function=dayview&date=\"+strdate+\"&caltype=\"+cal+\"&calname=\"+nm;\n   }\n function calendar_onchange() \n {\t\n  \tvar strdate = '" + strDateType + "'  " + "\n  \tvar strcalendar_id = '" + strcalendar_id + "'  " + "\n  \tvar cal = document.frm.calendar1Type.value;\t " + "\n  \tvar nm = strcalendar_id " + "\n    \twindow.parent.frames[0].document.location.href=\"./learnitytms.ResourceScheduleManagement?change=Y&function=dayview&date=\"+strdate+\"&caltype=\"+cal+\"&calname=\"+nm;" + "\n   }" + "\n function calendarname_onchange()  " + "\n {\t" + "\n  \tvar cal = document.frm.calendar1Type.value;\t " + "\n  \tvar nm =0  " + "\n if(document.frm.canlendarname == null){ " + "\n } " + "\n else { " + "\n  \t nm = document.frm.canlendarname.value;\t " + "\n } " + "\n  \tvar strdate = '" + strDateType + "'  " + "\n   }" + "\n function addCalendar()\t" + "\n {\t" + "\n  \tvar strdate = '" + strDateType + "'  " + "\n   }" + "\n function addManageCalendar()\t" + "\n {\t" + "\n  \tvar strdate = '" + strDateType + "'  " + "\n   }";
        final Input btnGo = new Input().setType("button").setClassId("drpdwn").setName("btngo").setValue("Go");
        btnGo.setOnClick("changemonth()");
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
        final Html html = new Html();
        final Head head = new Head().addElement((Element)new Title("Calendar Details Window")).addElement((Element)new Link().setHref("../js/stylesheet.css").setRel("stylesheet"));
        final Script script = new Script().setLanguage("javascript").addElement(javascript);
        head.addElement((Element)script);
        final Body body = new Body().setBgColor("#CCCCCC");
        final Form form = new Form().setName("frm").setMethod("post");
        final Table tblList = new Table().setCellPadding(0).setCellSpacing(0).setWidth("95%").setHeight("95%").addElement((Element)new TR().addElement((Element)new TD().setWidth("10%").setAlign("center").addElement((Element)new A().setHref("javascript:calendarMethod('" + viewType + "','" + strThreeyears2[0] + "');").addElement("<img id=\"prev-image\" height=\"19\" width=\"22\" border=\"0\" alt=\"Previous\" src=\"../images/up.gif\">"))).addElement((Element)new TD().setWidth("80%").setColSpan(4).addElement((Element)new ResourceCalTableExtension().addCalendarMonth(sd.monthData(strThreeyears2[1]), presentday, -2, vres))).addElement((Element)new TD().setWidth("10%").setAlign("center").addElement((Element)new A().setHref("javascript:calendarMethod('" + viewType + "','" + strThreeyears2[2] + "');").addElement("<img id=\"prev-image\" height=\"19\" width=\"22\" border=\"0\" alt=\"Next\" src=\"../images/dn.gif\">"))));
        for (int p = 0; p < vres.length; ++p) {
            form.addElement((Element)new Input().setType("hidden").setValue(vres[p]).setName("resourceid_hidden"));
        }
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
        ResourceScheduleManagement.CONTENT_TYPE = "text/html";
    }
}
