package learnitysce;

import javax.servlet.http.*;
import java.util.*;
import javax.servlet.*;
import java.io.*;

public class GetReflector extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final HttpSession mysession = request.getSession(false);
        final String student_id = (String)mysession.getAttribute("student_id");
        final ObjectOutputStream out = new ObjectOutputStream((OutputStream)response.getOutputStream());
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final String strId = request.getParameter("ID");
        final String strXindiceType = "";
        final String strXindicePort = "";
        final Vector Vg = SceDataBaseLayer.getReflectorDetails(strId);
        out.writeObject(Vg);
        out.flush();
        out.close();
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
