package learnitysce;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class DeleteDynamicInfo extends HttpServlet
{
    String SESSION_COURSE_ITEM;
    
    public DeleteDynamicInfo() {
        this.SESSION_COURSE_ITEM = "SESSION_SCE_ITEM";
    }
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final PrintWriter out = resp.getWriter();
        if (!New_LoginAppl.checkLogin(req, out)) {
            return;
        }
        final HttpSession mysession = req.getSession(false);
        final String student_id = (String)mysession.getAttribute("student_id");
        final String sce_id = (String)mysession.getAttribute("sce_id");
        final String name = SceDataBaseLayer.getMentorName(sce_id);
        SceDataBaseLayer.delete_from_ipaddress(sce_id, student_id);
        if (name.equals(student_id)) {
            SceDataBaseLayer.delete_poll_info(sce_id);
        }
    }
    
    public void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
