package calendar.calendar;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class CalendarMain extends HttpServlet
{
    private static final String CONTENT_TYPE = "text/html";
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        response.setContentType("text/html");
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
        final Calendar calendarnew = new GregorianCalendar();
        final Date trialTime = new Date();
        calendarnew.setTime(trialTime);
        final String[] strcalendarname = CalDataBaseLayer.getCalendarName(student_id, "P");
        if (strcalendarname == null) {
            strcalendarname[0] = "0";
        }
        out.println("\t<frameset cols=\"250,*\" border=\"1\" bordercolor=\"#990000\" frameborder=\"1\" framespacing=\"1\">");
        out.println("   \t<frame src=\"./calendar.calendar.CalendarList?caltype=0 \" name=\"calendarleft\" marginwidth=8 marginheight=8 bordercolor=\"#990000\">");
        out.println("<frameset rows=\"40,*\" border=\"1\" bordercolor=\"#990000\"  frameborder=\"0\" framespacing=\"0\">");
        out.println("\t<frame src=\"./calendar.calendar.CalendarTop\" scrolling=\"no\" marginheight=\"1\" bordercolor=\"#990000\">");
        out.println("\t<frame src=\"./calendar.calendar.MyCalendar?change=Y&caltype=0 \" name=\"calendardisplay\" bordercolor=\"#990000\">");
        out.println("\t</frameset>");
        out.println("\t</frameset>");
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
