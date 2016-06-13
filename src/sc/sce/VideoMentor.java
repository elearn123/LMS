package sc.sce;

import org.grlea.log.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.*;

public class VideoMentor extends HttpServlet
{
    private static final SimpleLogger log;
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        String reflectorip = "";
        reflectorip = request.getParameter("reflectorip");
        String clientIp = request.getRemoteAddr();
        final String clientDirectIp = request.getRemoteAddr();
        final String outputport = request.getParameter("outputport");
        final String input_method = request.getParameter("input_method");
        final String outputfile = request.getParameter("outputfile");
        final String mode = request.getParameter("mode");
        final PrintWriter out = response.getWriter();
        if (reflectorip == null) {
            reflectorip = "";
        }
        if (!reflectorip.equals("")) {
            clientIp = reflectorip;
        }
        final String browser = request.getParameter("browser");
        final String script1 = "\n\tfunction go_onclick(){\n\tvar clientIp=document.frm.clientIp.value;\n\tdocument.frm.method = \"post\";\n\tdocument.frm.action=\"./sc.sce.video?clientIp=\"+clientIp+\"&reflectorip=" + reflectorip + "&outputport=" + outputport + "&input_method=" + input_method + "&outputfile=" + outputfile + "\";" + "\n\tdocument.frm.submit();" + "\n\t}";
        out.println("<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\"> ");
        out.println("<html><head><title>Aunwesha Knowledge Technologies Pvt. Ltd.</title>");
        out.println("<link rel=stylesheet href=\"../css/template.css\" >");
        out.println("</link>");
        out.println("</head>");
        out.println("<script language=\"JavaScript\" >" + script1 + "</script></head>");
        out.println("<body>");
        if (browser.equals("ie4up")) {
            out.println("<div id=mentorvideo>");
            out.println("<div id=mentorvideotitle>");
            out.println("LearnITy Mentor Video");
            out.println("</div>");
            out.println("<div id=mentorvideobody>");
            if (mode.equals("D")) {
                out.println("<OBJECT classid=\"clsid:E23FE9C6-778E-49D4-B537-38FCDE4887D8\" codebase=\"./cab/axvlc.cab\" id=\"vlc\" events=\"True\">");
                out.println("<param name=\"MRL\" value=\"udp:@" + clientDirectIp + ":" + outputport + "\" />");
                out.println("<param name=\"ShowDisplay\" value=\"True\" />");
                out.println("<param name=\"AutoLoop\" value=\"False\" />");
                out.println("<param name=\"AutoPlay\" value=\"False\" />");
                out.println("</OBJECT>");
            }
            else if (reflectorip.equals("")) {
                out.println("<OBJECT classid=\"clsid:E23FE9C6-778E-49D4-B537-38FCDE4887D8\" codebase=\"./cab/axvlc.cab\" id=\"vlc\" events=\"True\">");
                out.println("<param name=\"MRL\" value=\"udp:@" + clientIp + ":" + outputport + "\" />");
                out.println("<param name=\"ShowDisplay\" value=\"True\" />");
                out.println("<param name=\"AutoLoop\" value=\"False\" />");
                out.println("<param name=\"AutoPlay\" value=\"False\" />");
                out.println("</OBJECT>");
            }
            else if (input_method.equals("rtsp")) {
                out.println("<OBJECT classid=\"clsid:E23FE9C6-778E-49D4-B537-38FCDE4887D8\" codebase=\"cab/axvlc.cab\" id=\"vlc\" events=\"True\">");
                out.println("<param name=\"MRL\" value=\"rtsp:@" + clientIp + ":" + outputport + "/" + outputfile + "\" />");
                out.println("<param name=\"ShowDisplay\" value=\"True\" />");
                out.println("<param name=\"AutoLoop\" value=\"False\" />");
                out.println("<param name=\"AutoPlay\" value=\"False\" />");
                out.println("</OBJECT>");
            }
            else {
                out.println("<OBJECT classid=\"clsid:E23FE9C6-778E-49D4-B537-38FCDE4887D8\" codebase=\"./cab/axvlc.cab\" id=\"vlc\" events=\"True\">");
                out.println("<param name=\"MRL\" value=\"http:@" + clientIp + ":" + outputport + "\" />");
                out.println("<param name=\"ShowDisplay\" value=\"True\" />");
                out.println("<param name=\"AutoLoop\" value=\"False\" />");
                out.println("<param name=\"AutoPlay\" value=\"False\" />");
                out.println("</OBJECT>");
            }
            out.println("</div>");
            out.println("<div id=mentorplaystopfull>");
            out.println("<a href=\"javascript:;\" onclick='document.vlc.play()'>Play</a>");
            out.println("<a href=\"javascript:;\" onclick='document.vlc.stop()'>Stop</a>");
            out.println("<a href=\"javascript:;\" onclick='document.vlc.fullscreen()'>Fullscreen</a>");
            out.println("</div>");
            out.println("</div>");
        }
        else {
            out.println("<div id=mentorvideo>");
            out.println("<div id=mentorvideotitle>");
            out.println("LearnITy Mentor Video");
            out.println("</div>");
            out.println("<div id=mentorplaystopfull>");
            out.println("<a href=\"javascript:;\" onclick='document.video.play()'>Play</a>");
            out.println("<a href=\"javascript:;\" onclick='document.video.stop()'>Stop</a>");
            out.println("<a href=\"javascript:;\" onclick='document.video.fullscreen()'>Fullscreen</a>");
            out.println("</div>");
            out.println("<div id=mentorvideobody>");
            if (mode.equals("D")) {
                VideoMentor.log.debug("==================udp:@" + clientIp + ":" + outputport);
                out.println("<embed type=\"application/x-vlc-plugin\"  name=\"video\"  autoplay=\"no\" loop=\"no\" target=\"udp:@" + clientIp + ":" + outputport + "\" HEIGHT=\"250\" WIDTH=\"370\" />");
            }
            else if (reflectorip.equals("")) {
                out.println("<embed type=\"application/x-vlc-plugin\"  name=\"video\"  autoplay=\"no\" loop=\"no\" target=\"udp:@" + clientIp + ":" + outputport + "\" HEIGHT=\"600\" WIDTH=\"355\" />");
            }
            else if (input_method.equals("rtsp")) {
                out.println("<embed type=\"application/x-vlc-plugin\"  name=\"video\"  autoplay=\"no\" loop=\"no\" target=\"rtsp:@" + clientIp + ":" + outputport + "/" + outputfile + "\" HEIGHT=\"600\" WIDTH=\"355\" />");
            }
            else {
                out.println("<embed type=\"application/x-vlc-plugin\"  name=\"video\"  autoplay=\"no\" loop=\"no\" target=\"http:@" + clientIp + ":" + outputport + "\" HEIGHT=\"600\" WIDTH=\"355\" />");
            }
            out.println("</div>");
            out.println("</div>");
        }
        out.println("</body>");
        out.println("</html>");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    static {
        log = new SimpleLogger((Class)VideoMentor.class);
    }
}
