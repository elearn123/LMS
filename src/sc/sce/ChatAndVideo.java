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

import org.grlea.log.SimpleLogger;

public class ChatAndVideo extends HttpServlet
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
        ChatAndVideo.log.debug("********ChatList*******" + ChatList);
        ChatAndVideo.log.debug("********Chat************" + strID);
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
                        output_port = input_port;
                        outputfile += ".sdp";
                    }
                }
            }
            if (student_id.equals(ledname)) {
                if (mentor_view.equals("T")) {
                    out.println("<div id=\"mentort\">");
                    out.println("<iframe frameborder=\"0\" name=\"mentor\" width=\"382\" height=\"302\" src=\"./sc.sce.VideoMentor?outputport=" + output_port + "&reflectorip=" + reflectorip + "&input_method=" + input_method + "&outputfile=" + outputfile + "&browser=" + browser + "&mode=" + mentor_mode + "\">");
                    out.println("Mentor View");
                    out.println("</iframe>");
                    out.println("</div>");
                }
            }
            else {
                out.println("<div id=\"mentort\">");
                out.println("<iframe frameborder=\"0\" name=\"mentor\" width=\"382\" height=\"302\" src=\"./sc.sce.VideoMentor?outputport=" + output_port + "&reflectorip=" + reflectorip + "&input_method=" + input_method + "&outputfile=" + outputfile + "&browser=" + browser + "&mode=" + mentor_mode + "\">");
                out.println("Mentor View");
                out.println("</iframe>");
                out.println("</div>");
            }
            String StudentSCid = "";
            String Studentinput_port = "";
            String Studentoutput_port = "";
            String Studentinput_method = "";
            String StudentVLCIP = "";
            String studentmulticast = "";
            final String student_view = SceDataBaseLayer.getstudentinputport(strID);
            String student_mode = "";
            final Vector StudentStreaming = SceDataBaseLayer.getStreamingDetailsForStudent(strID);
            if (StudentStreaming != null) {
                for (int ii = 0; ii < StudentStreaming.size(); ++ii) {
                    final Vector<String> vUnit2 = (Vector<String>) StudentStreaming.elementAt(ii);
                    StudentSCid = vUnit2.elementAt(0);
                    final String Studentvideo_device = vUnit2.elementAt(1);
                    final String Studentaudio_device = vUnit2.elementAt(2);
                    final String Studentvideo_size = vUnit2.elementAt(3);
                    final String Studentlocal_view = vUnit2.elementAt(4);
                    final String Studentoutput_method = vUnit2.elementAt(5);
                    Studentinput_port = vUnit2.elementAt(6);
                    Studentoutput_port = vUnit2.elementAt(7);
                    final String Studentencapsulation = vUnit2.elementAt(8);
                    final String Studentvideo_transcoding = vUnit2.elementAt(9);
                    final String Studentvideo_bitrate = vUnit2.elementAt(10);
                    final String Studentscale = vUnit2.elementAt(11);
                    final String Studentaudio_transcoding = vUnit2.elementAt(12);
                    final String Studentaudio_bitrate = vUnit2.elementAt(13);
                    final String Studentchannels = vUnit2.elementAt(14);
                    Studentinput_method = vUnit2.elementAt(15);
                    StudentVLCIP = vUnit2.elementAt(16);
                    final String Studentttl = vUnit2.elementAt(17);
                    final String Studentfps = vUnit2.elementAt(18);
                    studentmulticast = vUnit2.elementAt(19);
                    student_mode = vUnit2.elementAt(21);
                    System.out.println("======================student_mode========" + student_mode);
                    if (!studentmulticast.equals("")) {
                        StudentVLCIP = studentmulticast;
                    }
                }
            }
            output_port = Studentoutput_port;
            if (studentmulticast.equals("")) {
                reflectorip = StudentVLCIP;
                input_method = Studentinput_method;
                output_port = Studentinput_port;
                outputfile = StudentSCid + "student.sdp";
            }
            if (!student_id.equals(ledname)) {
                if (student_view.equals("T")) {
                    out.println("<div id=\"returnt\">");
                    out.println("<iframe name=\"return\" frameborder=\"0\"  width=\"382\" height=\"302\" src=\"./sc.sce.VideoStudent?outputport=" + Studentoutput_port + "&reflectorip=" + reflectorip + "&input_method=" + input_method + "&outputfile=" + outputfile + "&browser=" + browser + "&mode=" + student_mode + "\">");
                    out.println("Return View");
                    out.println("</iframe>");
                    out.println("</div>");
                }
            }
            else {
                out.println("<div id=\"returnt\">");
                out.println("<iframe name=\"return\" frameborder=\"0\"  width=\"382\" height=\"302\" src=\"./sc.sce.VideoStudent?outputport=" + Studentoutput_port + "&reflectorip=" + reflectorip + "&input_method=" + input_method + "&outputfile=" + outputfile + "&browser=" + browser + "&mode=" + student_mode + "\">");
                out.println("Return View");
                out.println("</iframe>");
                out.println("</div>");
            }
        }
        if (chat_checked.equals("T") && !video_checked.equals("T")) {
            out.println("<div id=\"chatt\">");
            out.println("<iframe frameborder=\"0\" name=\"chat\" width=\"608\" height=\"620\" margin-top=\"0\" src=\"./sc.sce.applet?ID=" + strID + "&Session=" + strSession + "\" >");
            out.println("\t</APPLET>");
            out.println("</iframe>");
            out.println("</div>");
        }
        if (!chat_checked.equals("T") && video_checked.equals("T")) {
            final Vector VectorStreaming = SceDataBaseLayer.getStreamingDetails(strID);
            String reflectorip = "";
            String multicast = "";
            String output_port = "";
            String input_method = "";
            String outputfile = "";
            String mentor_view = "";
            String mentor_mode = "";
            if (VectorStreaming != null) {
                for (int i = 0; i < VectorStreaming.size(); ++i) {
                    final Vector<String> vUnit = (Vector<String>) VectorStreaming.elementAt(i);
                    outputfile = vUnit.elementAt(0);
                    final String input_port = vUnit.elementAt(6);
                    output_port = vUnit.elementAt(7);
                    input_method = vUnit.elementAt(15);
                    multicast = vUnit.elementAt(19);
                    mentor_view = vUnit.elementAt(20);
                    mentor_mode = vUnit.elementAt(21);
                    if (multicast.equals("")) {
                        reflectorip = vUnit.elementAt(16);
                        output_port = input_port;
                        outputfile += ".sdp";
                    }
                }
            }
            if (student_id.equals(ledname)) {
                if (mentor_view.equals("T")) {
                    out.println("<div id=\"mentort\">");
                    out.println("<iframe frameborder=\"0\" name=\"mentor\" width=\"382\" height=\"302\" src=\"./sc.sce.VideoMentor?outputport=" + output_port + "&reflectorip=" + reflectorip + "&input_method=" + input_method + "&outputfile=" + outputfile + "&browser=" + browser + "&mode=" + mentor_mode + "\">");
                    out.println("Mentor View");
                    out.println("</iframe>");
                    out.println("</div>");
                }
            }
            else {
                out.println("<div id=\"mentort\">");
                out.println("<iframe frameborder=\"0\" name=\"mentor\" width=\"382\" height=\"302\" src=\"./sc.sce.VideoMentor?outputport=" + output_port + "&reflectorip=" + reflectorip + "&input_method=" + input_method + "&outputfile=" + outputfile + "&browser=" + browser + "&mode=" + mentor_mode + "\">");
                out.println("Mentor View");
                out.println("</iframe>");
                out.println("</div>");
            }
            String StudentSCid = "";
            String Studentinput_port = "";
            String Studentoutput_port = "";
            String Studentinput_method = "";
            String StudentVLCIP = "";
            String studentmulticast = "";
            String student_view = "";
            String student_mode = "";
            final Vector StudentStreaming = SceDataBaseLayer.getStreamingDetailsForStudent(strID);
            if (StudentStreaming != null) {
                for (int ii = 0; ii < StudentStreaming.size(); ++ii) {
                    final Vector<String> vUnit2 = (Vector<String>) StudentStreaming.elementAt(ii);
                    StudentSCid = vUnit2.elementAt(0);
                    final String Studentvideo_device = vUnit2.elementAt(1);
                    final String Studentaudio_device = vUnit2.elementAt(2);
                    final String Studentvideo_size = vUnit2.elementAt(3);
                    final String Studentlocal_view = vUnit2.elementAt(4);
                    final String Studentoutput_method = vUnit2.elementAt(5);
                    Studentinput_port = vUnit2.elementAt(6);
                    Studentoutput_port = vUnit2.elementAt(7);
                    final String Studentencapsulation = vUnit2.elementAt(8);
                    final String Studentvideo_transcoding = vUnit2.elementAt(9);
                    final String Studentvideo_bitrate = vUnit2.elementAt(10);
                    final String Studentscale = vUnit2.elementAt(11);
                    final String Studentaudio_transcoding = vUnit2.elementAt(12);
                    final String Studentaudio_bitrate = vUnit2.elementAt(13);
                    final String Studentchannels = vUnit2.elementAt(14);
                    Studentinput_method = vUnit2.elementAt(15);
                    StudentVLCIP = vUnit2.elementAt(16);
                    final String Studentttl = vUnit2.elementAt(17);
                    final String Studentfps = vUnit2.elementAt(18);
                    studentmulticast = vUnit2.elementAt(19);
                    student_view = vUnit2.elementAt(20);
                    student_mode = vUnit2.elementAt(21);
                    if (!studentmulticast.equals("")) {
                        StudentVLCIP = studentmulticast;
                    }
                }
            }
            output_port = Studentoutput_port;
            if (studentmulticast.equals("")) {
                reflectorip = StudentVLCIP;
                input_method = Studentinput_method;
                output_port = Studentinput_port;
                outputfile = StudentSCid + "student.sdp";
            }
            if (!student_id.equals(ledname)) {
                if (student_view.equals("T")) {
                    out.println("<div id=\"returnt\">");
                    out.println("<iframe name=\"return\" frameborder=\"0\"  width=\"382\" height=\"302\" src=\"./sc.sce.VideoStudent?outputport=" + Studentoutput_port + "&reflectorip=" + reflectorip + "&input_method=" + input_method + "&outputfile=" + outputfile + "&browser=" + browser + "&mode=" + student_mode + "\">");
                    out.println("Return View");
                    out.println("</iframe>");
                    out.println("</div>");
                }
            }
            else {
                out.println("<div id=\"returnt\">");
                out.println("<iframe name=\"return\" frameborder=\"0\"  width=\"382\" height=\"302\" src=\"./sc.sce.VideoStudent?outputport=" + Studentoutput_port + "&reflectorip=" + reflectorip + "&input_method=" + input_method + "&outputfile=" + outputfile + "&browser=" + browser + "&mode=" + student_mode + "\">");
                out.println("Return View");
                out.println("</iframe>");
                out.println("</div>");
            }
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
