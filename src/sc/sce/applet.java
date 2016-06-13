package sc.sce;

import org.grlea.log.*;
import javax.servlet.*;
import java.util.*;
import javax.servlet.http.*;
import java.io.*;

import learnitysce.SceDataBaseLayer;

public class applet extends HttpServlet
{
    private static final SimpleLogger log;
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final HttpSession mysession = request.getSession(false);
        final String student_id = (String)mysession.getAttribute("student_id");
        String ppath = request.getContextPath();
        ppath += "/help.html";
        final PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final String strID = request.getParameter("ID");
        final String ledname = SceDataBaseLayer.getMentorName(strID);
        final Vector ChatList = (Vector)mysession.getAttribute("CL");
        applet.log.debug("********ChatList*******" + ChatList);
        applet.log.debug("********Chat************" + strID);
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
        out.println("\t\twin =window.open(\"\",\"presentation\",\"width=700,height=500,resizable=1,menubar=0,status=0,toolbar=0\");");
        out.println("\t}");
        out.println("\tfunction testOpenWindow() {");
        out.println("\t\treturn win.closed;");
        out.println("\t}");
        out.println("\tfunction doClose() {");
        out.println("\t\twindow.close();");
        out.println("\t}");
        out.println("\tfunction doHelp() {");
        out.println("var pp='" + ppath + "';");
        out.println("\t\twindow.open(pp,\"blank\",\"toolbar=no,width=500,height=500\");");
        out.println("\t}");
        out.println("\tfunction fnUnitId() {");
        out.println("\t\talert(\"Please Select a Unit\");");
        out.println("\t}");
        out.println("// -->");
        out.println("</script>");
        out.println("<body bgcolor=\"#FFFFFF\" text=\"#000000\">");
        out.println("<APPLET archive=\"SSC.jar,forms-1.0.5.jar,looks-1.3-snapshot.jar,commons-net-1.4.0.jar\" code=\"SCApplet.class\"");
        out.println("                codebase=\"../html/\" mayscript=\"true\"");
        out.println("\t\twidth=\"600\" height=\"600\">");
        out.println("\t</APPLET>");
        out.println("</body>");
        out.println("</html>");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    static {
        log = new SimpleLogger((Class)applet.class);
    }
}
