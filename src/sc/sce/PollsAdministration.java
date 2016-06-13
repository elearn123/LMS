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

public class PollsAdministration extends HttpServlet
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
        final String name = SceDataBaseLayer.getMentorName(strID);
        System.out.println("========name===============" + name);
        final String pollid = SceDataBaseLayer.GetPollId(strID, "Activated");
        final String status = SceDataBaseLayer.Getpollstatus(strID, pollid);
        out.println("<html>");
        out.println("<head><title>LearnITy Synchronous Collaboration</title></head>");
        if (name.equals(student_id)) {
            out.println("<frameset cols=\"50%,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\"> ");
            out.println("<frame name=\"PollFrame\" scrolling=\"NO\" noresize src=\"./sc.sce.PollsAndQuizes?ID=" + strID + "\" >");
            out.println("<frameset rows=\"50%,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\"> ");
            out.println("<frame name=\"ResultFrame\" scrolling=\"NO\">");
            out.println("<frame name=\"ChartFrame\" scrolling=\"YES\">");
            out.println("</frameset>");
            out.println("</frameset>");
        }
        else {
            out.println("<frameset cols=\"50%,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\"> ");
            out.println("<frame name=\"PollFrame\" scrolling=\"NO\" noresize src=\"./sc.sce.PollsAndQuizes?ID=" + strID + "\" >");
            out.println("<frameset rows=\"50%,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\"> ");
            out.println("<frame name=\"ResultFrame\" scrolling=\"NO\">");
            out.println("<frame name=\"ChartFrame\" scrolling=\"YES\">");
            out.println("</frameset>");
            out.println("</frameset>");
        }
        out.println("</html>");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
