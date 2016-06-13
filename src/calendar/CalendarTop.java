package calendar.calendar;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import org.apache.ecs.*;
import org.apache.ecs.html.*;

public class CalendarTop extends HttpServlet
{
    private static final String CONTENT_TYPE = "text/html";
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        response.setContentType("text/html");
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setDateHeader("Experies", 0L);
        out = response.getWriter();
        if (!New_LoginAppl.checkLogin(request, out)) {
            return;
        }
        final HttpSession mysession = request.getSession(false);
        final String student_id = (String)mysession.getAttribute("student_id");
        if (student_id == null) {
            response.sendRedirect("../servlet/interfaceenginev2.PortalServlet?IID=LoginPage");
        }
        else {
            this.getResult(request, response, out, student_id);
        }
    }
    
    public void getResult(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out, final String student_id) throws IOException, ServletException {
        final String javascript = "\n   function help_onClick()\n  { \n     window.open('../html/calendarhelp.html','help','width=600,height=500,menu=no,scrollbars=yes, resizeable=yes');\n  }";
        final Html html = new Html().addElement((Element)new Head().addElement((Element)new Title("Notice Details Window")).addElement((Element)new Link().setHref("../coreadmin/js/stylesheet.css").setRel("stylesheet")).addElement((Element)new Script().setLanguage("javascript").addElement(javascript)));
        final Body body = new Body().setBgColor("#FFFFFF");
        final Form form = new Form().setMethod("post").setName("theForm");
        final Table tblCalendarTop = new Table().setBorder(0).setCellPadding(0).setCellSpacing(0).setWidth("100%").addElement((Element)new TBody().addElement((Element)new TR().addElement((Element)new TD().setBgColor("#669933").setHeight(15).setNoWrap(true).setRowSpan(2).addElement((Element)new Table().setCellPadding(0).setCellSpacing(0).setBorder(0).setWidth(160).addElement((Element)new TBody().addElement((Element)new TR().addElement((Element)new TD().setWidth(160))))).addElement(new IMG().setHeight(20).setWidth(20).setSrc("../coreadmin/images/SmallLogo.gif").setID("logo")).addElement((Element)new Font().setFontClass("sbh").addElement((Element)new B().addElement("&nbsp;&nbsp;Calendar ")))).addElement((Element)new TD().setBgColor("#669933").setVAlign("top").addElement(new IMG().setAlt("").setHeight(20).setWidth(20).setSrc("../coreadmin/images/w20x20.gif").setID("cti1"))).addElement((Element)new TD().setAlign("right").setWidth("75%").addElement((Element)new Font().addElement((Element)new B()))).addElement((Element)new TD().setAlign("right").setWidth("15%").addElement((Element)new Font().setFace("verdana").setSize(-1).addElement((Element)new A().setHref("javascript:help_onClick();").addElement("Help&nbsp;"))))).addElement((Element)new TR().addElement((Element)new TD().setBgColor("#669933").setColSpan(3).addElement((Element)new Table().setCellPadding(0).setCellSpacing(0).setBorder(0).addElement((Element)new TBody().addElement((Element)new TR().addElement((Element)new TD().setHeight(3))))))));
        form.addElement((Element)tblCalendarTop);
        body.addElement((Element)form);
        html.addElement((Element)body);
        out.print(html.toString());
        out.close();
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
