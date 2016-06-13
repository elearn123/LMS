package sc.sce;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import learnitysce.SceDataBaseLayer;

public class SceAdministration extends HttpServlet
{
    private static final String LOGIN_SESSION_NAME = "ADMIN_LOG_ON";
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final PrintWriter out = response.getWriter();
        final HttpSession mysession = request.getSession(false);
        final String student_id = (String)mysession.getAttribute("student_id");
        final String ip = request.getRemoteAddr();
        final String strID = request.getParameter("ID");
        final String name = SceDataBaseLayer.getMentorName(strID);
        final String browser = request.getParameter("browser");
        final String strSession = request.getParameter("Session");
        SceDataBaseLayer.Insert_IP_Addr(strID, student_id, ip);
        out.println("<html>");
        out.println("<head><title>LearnITy Synchronous Collaboration</title></head>");
        out.println("<frameset rows=\"80,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\"> ");
        out.println("<frame name=\"TabFrame\" scrolling=\"NO\" noresize src=\"./sc.sce.sceTabManagement?ID=" + strID + "&browser=" + browser + "&Session=" + strSession + "\" >");
        out.println("<frame name=\"SceMainFrame\" scrolling=\"YES\"></frameset>");
        out.println("</frameset>");
        out.println("</html>");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
