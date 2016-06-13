package learnityforum;

import javax.servlet.*;
import learnityInit.*;
import com.oreilly.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;

public class UploadForumAttachment extends HttpServlet
{
    private String dirName;
    private String dirNameForum;
    private String sid;
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        final HttpSession mysession = request.getSession(false);
        final String baseName = "portal1";
        String maxlengthstring = "";
        final Locale dd = Locale.getDefault();
        final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
        final String key1 = "MaUploadLength";
        maxlengthstring = rb.getString(key1);
        final int maxlength = Integer.parseInt(maxlengthstring);
        final String student_id = (String)mysession.getAttribute("user_id");
        final String forum_id = (String)mysession.getAttribute("forum_id");
        System.out.println("forum_id is===========" + forum_id);
        final String contextName = request.getContextPath().substring(request.getContextPath().indexOf(47) + 1);
        final String conp = this.getServletContext().getRealPath("/");
        File file = new File(conp);
        final String attachroot = file.getParent();
        final String attachmentname = Host.getServerDocumentPath();
        this.dirNameForum = attachmentname + "attachments" + File.separatorChar + "attachmentForum";
        if (this.dirNameForum == null) {
            throw new ServletException("Please supply uploadDir parameter");
        }
        file = new File(this.dirNameForum);
        if (!file.exists()) {
            file.mkdir();
        }
        this.dirName = attachmentname + "attachments" + File.separatorChar + "attachmentForum" + File.separatorChar + forum_id;
        if (this.dirName == null) {
            throw new ServletException("Please supply uploadDir parameter");
        }
        file = new File(this.dirName);
        if (!file.exists()) {
            file.mkdir();
        }
        String s7 = null;
        try {
            final MultipartRequest multipartrequest = new MultipartRequest(request, this.dirName, maxlength);
            final Enumeration<String> enumeration = multipartrequest.getFileNames();
            while (enumeration.hasMoreElements()) {
                final String s2 = enumeration.nextElement();
                s7 = multipartrequest.getFilesystemName(s2);
                file = multipartrequest.getFile(s2);
            }
        }
        catch (IOException ioexception) {
            out.println(ioexception.toString() + " error reading or saving file");
        }
        mysession.setAttribute("forum_attachment", (Object)s7);
        response.sendRedirect("./interfaceengine.PortalServlet?IID=forumattachment");
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    public void destroy() {
    }
}
