package learnityasmtserver.assessmentportal.embeddedasmt;

import org.grlea.log.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.*;

public class EassyTypeAnswerPreview extends HttpServlet
{
    public final SimpleLogger log;
    private static final String SESSION_NAME = "SESSION_NAME";
    
    public EassyTypeAnswerPreview() {
        this.log = new SimpleLogger((Class)EassyTypeAnswerPreview.class, true);
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final PrintWriter out = response.getWriter();
        final HttpSession mysession = request.getSession(true);
        final Object obj = mysession.getAttribute("SESSION_NAME");
        if (obj == null) {
            response.sendRedirect("../servlets/login.html");
        }
        else {
            final String strAdminId = obj.toString();
            if (strAdminId != null) {
                this.EassyAnswerContent(request, response, out, strAdminId);
            }
            else {
                response.sendRedirect("../servlets/Error.html");
            }
        }
    }
    
    public void EassyAnswerContent(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out, final String strAdminId) throws IOException, ServletException {
        final String noticedetails = request.getParameter("noticedetails");
        final StringBuffer strHTML = new StringBuffer();
        strHTML.append("<?xml version=\"1.0\"?>");
        strHTML.append("\n");
        strHTML.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1 plus MathML 2.0//EN\" \"http://www.w3.org/TR/MathML2/dtd/xhtml-math11-f.dtd\">");
        strHTML.append("\n<html xlink=\"http://www.w3.org/1999/xlink\" math=\"http://www.w3.org/1998/Math/MathML\" xmlns=\"http://www.w3.org/1999/xhtml\">");
        strHTML.append("\n    <head>");
        strHTML.append("\n    <title>Eassy Type Answer Preview </title>");
        strHTML.append("\n\t</head>");
        strHTML.append("\n\t<body bgcolor=\"#FAEBD7\">" + noticedetails + "</body>");
        strHTML.append("\n");
        strHTML.append("\n");
        strHTML.append("\n\t</html>");
        out.println(strHTML.toString());
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
