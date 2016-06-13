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

import org.grlea.log.SimpleLogger;

public class PollsAndQuizes extends HttpServlet
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
        final String pollid = SceDataBaseLayer.GetPollId(strID, "Activated");
        System.out.println("=====pollid in pollandquiz=" + pollid);
        final String name = SceDataBaseLayer.getMentorName(strID);
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body bgcolor=\"#FFFFFF\" text=\"#000000\">");
        if (name.equals(student_id)) {
            out.println("<div id=\"pullt\">");
            out.println("<iframe name=\"pull\" frameborder=\"0\" width=\"470\" height=\"605\" src=\"./polls.Mentorpolls?ID=" + strID + "\">");
            out.println("Pull");
            out.println("</iframe>");
            out.println("</div>");
        }
        else {
            if (pollid == null || pollid.equals("null") || pollid.equals("")) {
                out.println("<div id=\"pullt\">");
                out.println("<iframe name=\"pull\" frameborder=\"0\" width=\"470\" height=\"305\" src=\"./polls.StudentPolls?ID=" + strID + "\">");
                out.println("Pull");
                out.println("</iframe>");
                out.println("</div>");
            }
            else {
                out.println("<div id=\"pullt\">");
                out.println("<iframe name=\"pull\" frameborder=\"0\" width=\"470\" height=\"305\" src=\"./polls.PollQuestions?ID=" + strID + "\">");
                out.println("Pull");
                out.println("</iframe>");
                out.println("</div>");
            }
            out.println("<div id=\"publishpullt\">");
            out.println("<iframe name=\"pull\" frameborder=\"0\" width=\"470\" height=\"305\" src=\"./polls.StudentPublishPolls?ID=" + strID + "\">");
            out.println("Pull");
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
        log = new SimpleLogger((Class)PollsAndQuizes.class);
    }
}
