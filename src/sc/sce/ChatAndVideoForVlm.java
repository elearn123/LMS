package sc.sce;

import org.grlea.log.*;
import javax.servlet.*;
import java.util.*;

import javax.servlet.http.*;
import java.io.*;

import learnitysce.SceDataBaseLayer;

public class ChatAndVideoForVlm extends HttpServlet
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
        final String browser = request.getParameter("browser");
        final String ledname = SceDataBaseLayer.getMentorName(strID);
        final String chat_checked = SceDataBaseLayer.getChatChecked(strID);
        final String video_checked = SceDataBaseLayer.getVideoChecked(strID);
        final Vector ChatList = (Vector)mysession.getAttribute("CL");
        ChatAndVideoForVlm.log.debug("********ChatList*******" + ChatList);
        ChatAndVideoForVlm.log.debug("********Chat************" + strID);
        final String strSession = request.getParameter("Session");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Synchronous Collaboration</title>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
        out.println("<link rel=stylesheet href=\"../css/template.css\" >");
        out.println("</link>");
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
        if (chat_checked.equals("T") && video_checked.equals("T")) {
            out.println("<div id=\"chatt\">");
            out.println("<iframe frameborder=\"0\" name=\"chat\" width=\"608\" height=\"620\" margin-top=\"0\" src=\"./sc.sce.applet?ID=" + strID + "&Session=" + strSession + "\" >");
            out.println("\t</APPLET>");
            out.println("</iframe>");
            out.println("</div>");
            final Vector VectorStreaming = SceDataBaseLayer.getStreamingDetails(strID);
            String reflectorip = "";
            String multicast = "";
            String output_port = "";
            String input_method = "";
            String outputfile = "";
            final String mentor_view = SceDataBaseLayer.GetMentorView(strID);
            String mentor_mode = "";
            if (VectorStreaming != null) {
                for (int i = 0; i < VectorStreaming.size(); ++i) {
                    final Vector vUnit = (Vector) VectorStreaming.elementAt(i);
                    outputfile = (String) vUnit.elementAt(0);
                    final String input_port = (String) vUnit.elementAt(6);
                    output_port = (String) vUnit.elementAt(7);
                    input_method = (String) vUnit.elementAt(15);
                    multicast = (String) vUnit.elementAt(19);
                    System.out.println("===========mentor_view===========" + mentor_view);
                    mentor_mode = (String) vUnit.elementAt(21);
                    if (multicast.equals("")) {
                        reflectorip = (String) vUnit.elementAt(16);
                        outputfile += ".sdp";
                    }
                }
            }
            System.out.println("===========output_port======" + output_port);
            out.println("<div id=\"mentort\">");
            out.println("<iframe frameborder=\"0\" name=\"mentor\" width=\"385\" height=\"650\" src=\"./sc.sce.VideoMentor?outputport=" + output_port + "&reflectorip=" + reflectorip + "&input_method=" + input_method + "&outputfile=" + outputfile + "&browser=" + browser + "&mode=" + mentor_mode + "\">");
            out.println("Mentor View");
            out.println("</iframe>");
            out.println("</div>");
        }
        out.println("</body>");
        out.println("</html>");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    static {
        log = new SimpleLogger((Class)ChatAndVideo.class);
    }
}
