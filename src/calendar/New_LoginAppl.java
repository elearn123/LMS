package calendar.calendar;

import org.apache.ecs.*;
import org.apache.ecs.html.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class New_LoginAppl extends HttpServlet
{
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public static boolean checkLogin(final HttpServletRequest request, final PrintWriter out) {
        final HttpSession mysession = request.getSession(false);
        if (mysession == null) {
            final Html html = new Html().addElement((Element)new Head().addElement((Element)new Title("Unauthorized"))).addElement((Element)new Body().setBackground("../images/backgnd_for_engine.jpg").setAlink("990000").setVlink("660000").setLink("990000").addElement((Element)new Div().addElement((Element)new Center().addElement((Element)new H1("Access Denied.")))).addElement((Element)new A().setHref("../html/login.html").addElement((Element)new Center("Logon to LearnITy&#8482;"))));
            out.print(html.toString());
            out.close();
            return false;
        }
        return true;
    }
    
    public static boolean checkLogin(final HttpServletRequest request, final ServletOutputStream out) throws IOException {
        final HttpSession mysession = request.getSession(false);
        if (mysession == null) {
            final Html html = new Html().addElement((Element)new Head().addElement((Element)new Title("Unauthorized"))).addElement((Element)new Body().setBgColor("C2C2C2").setAlink("990000").setVlink("660000").setLink("990000").addElement((Element)new Div().addElement((Element)new Center().addElement((Element)new H1("Access Denied.")))).addElement((Element)new A().setHref("../html/login.html").addElement((Element)new Center("Logon to LearnITy&#8482&nbsp;"))));
            out.print(html.toString());
            out.close();
            return false;
        }
        return true;
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        final PrintWriter out = response.getWriter();
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
