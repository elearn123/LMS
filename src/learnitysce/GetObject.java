package learnitysce;

import javax.servlet.http.*;
import java.util.*;
import javax.servlet.*;
import java.io.*;

public class GetObject extends HttpServlet
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
        final Vector v = SceDataBaseLayer.getSynchronousEnvironment(strId);
        v.addElement(student_id);
        v.addElement(this.getServletContext().getAttribute("port"));
        v.addElement(TimeZone.getDefault());
        v.addElement(strXindiceType);
        v.addElement(strXindicePort);
        final Vector vadd = SceDataBaseLayer.getSynchronousEnvironmentadd(strId);
        v.addElement(vadd.elementAt(0));
        final Vector VectorStreaming = SceDataBaseLayer.getStreamingDetails(strId);
        v.addElement(VectorStreaming);
        final Vector StreamingForStudent = SceDataBaseLayer.getStreamingDetailsForStudent(strId);
        if (StreamingForStudent.size() != 0) {
            v.addElement(StreamingForStudent);
        }
        System.out.println("v = **********" + v);
        out.writeObject(v);
        out.flush();
        out.close();
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
