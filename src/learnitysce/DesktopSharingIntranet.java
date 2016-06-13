package learnitysce;

import org.grlea.log.*;
import javax.servlet.*;
import java.util.*;

import javax.servlet.http.*;
import java.io.*;

public class DesktopSharingIntranet extends HttpServlet
{
    private static final SimpleLogger log;
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final HttpSession mysession = request.getSession(false);
        final String student_id = (String)mysession.getAttribute("student_id");
        final PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final String strID = (String)mysession.getAttribute("sce_id");
        String port = "";
        String ipAdd = "";
        final Vector<Vector<String>> HubIpPort = SceDataBaseLayer.getDesktopSharingPort(strID);
        System.out.println("HubIpPort.size()" + HubIpPort.size());
        if (HubIpPort != null) {
            for (int i = 0; i < HubIpPort.size(); ++i) {
                final Vector<String> vUnit = HubIpPort.elementAt(i);
                final String strId = vUnit.elementAt(0);
                final String strId2 = vUnit.elementAt(1);
                System.out.println("***********=" + strId);
                port = strId;
                ipAdd = strId2;
            }
        }
        System.out.println("Port==" + port + "=IP==" + ipAdd);
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Synchronous Collaboration</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"#FFFFFF\" text=\"#000000\">");
        out.println("<APPLET archive=\"VncViewer.jar\" code=\"VncViewer.class\"");
        out.println("                codebase=\"../html/\" mayscript=\"true\"");
        out.println("\t\twidth=\"1030\" height=\"770\">");
        out.println("<param name=PORT value=" + port + ">");
        out.println("<param name=HOST value=" + ipAdd + ">");
        out.println("\t</APPLET>");
        out.println("</body>");
        out.println("</html>");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    static {
        log = new SimpleLogger((Class)DesktopSharingIntranet.class);
    }
}
