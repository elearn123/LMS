package sc.sce;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import learnitysce.SceDataBaseLayer;

public class sceTabManagement extends HttpServlet
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
        final String strID = request.getParameter("ID");
        final String scename = SceDataBaseLayer.getSceName(strID);
        final String name = SceDataBaseLayer.getMentorName(strID);
        final String browser = request.getParameter("browser");
        final String chat_checked = SceDataBaseLayer.getChatChecked(strID);
        final String video_checked = SceDataBaseLayer.getVideoChecked(strID);
        final String d_sharing_checked = SceDataBaseLayer.getDesktopSharingChecked(strID);
        final String strSession = request.getParameter("Session");
        String str1 = "";
        //final Vector Variable = SceDataBaseLayer.getName();
        
        /*if (Variable != null) {
            for (int i = 0; i < Variable.size(); i += 2) {
                final String[] srttgoia = Variable.elementAt(i);
                str1 = srttgoia[0];
                final String str2 = srttgoia[1];
                final String str3 = srttgoia[2];
            }
        }*/
        final String javaScript = "\n \tfunction Quit1() {\n \t\tif( confirm(\"Do you wish to quit?\") ) {\n             location.href=\"./sc.sce.DeleteDynamicInfo?sce_id=" + strID + "\";" + "\n \t\t\twindow.parent.close(); " + "\n \t\t}" + "\n \t}";
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Synchronous Collaboration</title>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
        out.println("<link rel=stylesheet href=\"../css/template.css\" >");
        out.println("</link>");
        out.println("<SCRIPT type=\"text/javascript\">function Quit(){if( confirm(\"Do you wish to quit?\") ) {location.href=\"./sc.sce.DeleteDynamicInfo?sce_id=" + strID + "\";window.parent.close();}}</SCRIPT>");
        out.println("</head>");
        out.println("<body bgcolor=\"#FFFFFF\" text=\"#000000\">");
        out.println("<div id=\"AunLogo\">");
        out.println("<img id=\"AunLogo\" border=\"0\" src=\"../images/Aunwesha_logo.gif\">");
        out.println("</div>");
        out.println("<div id=\"companyname\">");
        out.println("Aunwesha Knowledge Technologies Private Limited ");
        out.println("</div>");
        out.println("<div id=\"SyncColl\">");
        out.println("SCE NAME : " + scename);
        out.println("</div>");
        if (name.equals(student_id)) {
            if (chat_checked.equals("T") || video_checked.equals("T")) {
                out.println("<div id=\"ChatAndVideoDiv\">");
                out.println("<a href=\"sc.sce.ChatAndVideoForVlm?ID=" + strID + "&browser=" + browser + "&Session=" + strSession + "\" target=\"SceMainFrame\">Chat and Video</a>");
                out.println("</div>");
            }
            out.println("<div id=\"PollsAndQuizDivforMentor\">");
            out.println("<a href=\"sc.sce.PollsAdministration?ID=" + strID + "\" target=\"SceMainFrame\">Polls And Quizes</a>");
            out.println("</div>");
            out.println("<div id=\"QuitSce\">");
            out.println("<a onclick=\"javascript:Quit()\">Quit</a>");
            out.println("</div>");
        }
        else {
            if (chat_checked.equals("T") || video_checked.equals("T")) {
                out.println("<div id=\"ChatAndVideoDiv\">");
                out.println("<a href=\"sc.sce.ChatAndVideoForVlm?ID=" + strID + "&browser=" + browser + "&Session=" + strSession + "\" target=\"SceMainFrame\">Chat and Video</a>");
                out.println("</div>");
            }
            if (d_sharing_checked.equals("T")) {
                out.println("<div id=\"DesktopSharingDiv\">");
                out.println("<a href=\"sc.sce.SceDesktopSharing?ID=" + strID + "\" target=\"SceMainFrame\">Desktop Sharing</a>");
                out.println("</div>");
            }
            out.println("<div id=\"PollsAndQuizDiv\">");
            out.println("<a href=\"sc.sce.PollsAdministration?ID=" + strID + "\" target=\"SceMainFrame\">Polls And Quizes</a>");
            out.println("</div>");
            out.println("<div id=\"QuitSce\">");
            out.println("<a onclick=\"javascript:Quit()\" target=\"SceMainFrame\">Quit</a>");
            out.println("</div>");
        }
        out.println("</body>");
        out.println("</html>");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
