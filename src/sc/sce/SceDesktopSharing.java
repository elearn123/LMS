package sc.sce;

import org.grlea.log.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class SceDesktopSharing extends HttpServlet
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
        out.println("<html>");
        out.println("<head>");
        out.println("<body bgcolor=\"#FFFFFF\" text=\"#000000\">");
        out.println("<div id=\"desktopt\">");
        out.println("<iframe name=\"desktop\" frameborder=\"0\"  width=\"1000\" height=\"620\" src=\"./sc.sce.DesktopSharingIntranet?ID=" + strID + "\">");
        out.println("</iframe>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    static {
        log = new SimpleLogger((Class)SceDesktopSharing.class);
    }
}
