package sc.sce;

import org.grlea.log.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class SCEOffline extends HttpServlet
{
    private static final SimpleLogger log;
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final String strID = request.getParameter("ID");
        final String strSession = request.getParameter("Session");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Synchronous Collaboration</title>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
        out.println("</head>");
        out.println("<script language=\"JavaScript\">");
        out.println("<!--");
        out.println(" \tvar win;");
        out.println("\tfunction f() {");
        out.println("\t\tvar x ='" + strID + "';");
        out.println("\t\treturn x;");
        out.println("\t}");
        out.println("\tfunction getSession() {");
        out.println("\t\tvar x ='" + strSession + "';");
        out.println("\t\treturn x;");
        out.println("\t}");
        out.println("\tfunction openWindow() {");
        out.println("\t\twin =window.open(\"\",\"presentation\",\"width=600,height=500,resizable=1,menubar=0,status=0,toolbar=0\");");
        out.println("\t}");
        out.println("\tfunction testOpenWindow() {");
        out.println("\t\treturn win.closed;");
        out.println("\t}");
        out.println("\tfunction fnUnitId() {");
        out.println("\t\talert(\"Please Select a Unit\");");
        out.println("\t}");
        out.println("// -->");
        out.println("</script>");
        out.println("<body bgcolor=\"#FFFFFF\" text=\"#000000\">");
        out.println("<APPLET archive=\"SSCEOffline.jar,soap.jar\" code=\"SCApplet.class\"");
        out.println("                codebase=\"../html/\" mayscript=\"true\"");
        out.println("\t\twidth=\"700\" height=\"700\">");
        out.println("\t</APPLET>");
        out.println("</body>");
        out.println("</html>");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    static {
        log = new SimpleLogger((Class)SCEOffline.class);
    }
}
