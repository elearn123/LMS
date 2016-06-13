package learnityasmtserver.assessmentportal.standaloneasmt;

import javax.servlet.http.*;
import learnityInit.*;
import learnityasmtserver.assessmentportal.dbconnection.*;
import javax.servlet.*;
import java.io.*;

public class Image extends HttpServlet
{
    public void doGet(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final ServletOutputStream out = res.getOutputStream();
        final String strLocation = Host.getAdminPath();
        System.out.println("==========strLocation====" + strLocation);
        res.setContentType("image/jpg");
        final byte[] buffer = ReportDataBaseLayer.getOrganizationLogo("logo", "LearnityPortal");
        final RandomAccessFile raf = new RandomAccessFile(strLocation + File.separator + "image1.jpg", "rw");
        raf.write(buffer);
        out.write(buffer);
    }
}
