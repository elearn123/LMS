package learnityforum;

import learnityInit.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class Image extends HttpServlet
{
    public void doGet(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final HttpSession mysession = req.getSession(false);
        final ServletOutputStream out = res.getOutputStream();
        final String path = Host.getPortalImagePathEngine();
        final String attachmentPath = Host.getServerDocumentPathEngine();
        System.out.println("==========path====" + attachmentPath);
        mysession.setAttribute("attachmentPath", (Object)attachmentPath);
        final String user_id = req.getParameter("user_id");
        System.out.println("User id id:" + user_id);
        res.setContentType("image/jpg");
        final byte[] buffer = ForumDataBaseLayer.getUserPhoto(user_id);
        final RandomAccessFile raf = new RandomAccessFile(path + "image1.jpg", "rw");
        raf.write(buffer);
        out.write(buffer);
    }
}
