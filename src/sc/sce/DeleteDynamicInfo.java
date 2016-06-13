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
        final String sce_id = req.getParameter("sce_id");
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
