package LearnityNotice;

import org.grlea.log.*;
import learnityInit.*;
import com.oreilly.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.*;

public class UploadFile extends HttpServlet
{
    private static final SimpleLogger log;
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        final HttpSession mysession = request.getSession(false);
        String dirName = "";
        String stdID = (String)mysession.getAttribute("user_id");
        System.out.println("stdID------111-------------------------" + stdID);
        if (stdID == null) {
            stdID = "0";
        }
        final String conp = request.getRealPath("/");
        File file = new File(conp);
        final String attachmentname = Host.getServerDocumentPath();
        final String getUtil = Host.getUtil();
        System.out.println("attachmentname------222-------------------------" + attachmentname);
        System.out.println("getUtil------222-------------------------" + getUtil);
        dirName = attachmentname + "attachments" + File.separatorChar + stdID;
        UploadFile.log.debug("======dirName==== " + dirName);
        if (dirName == null) {
            UploadFile.log.debug("Please supply uploadDir parameter");
        }
        else {
            file = new File(dirName);
            if (!file.exists()) {
                UploadFile.log.debug("=======creating directory====");
                file.mkdir();
            }
            String s7 = null;
            try {
                final MultipartRequest multipartrequest = new MultipartRequest(request, dirName, 10485760);
                final Enumeration<String> enumeration = multipartrequest.getFileNames();
                while (enumeration.hasMoreElements()) {
                    final String s2 = enumeration.nextElement();
                    s7 = multipartrequest.getFilesystemName(s2);
                    file = multipartrequest.getFile(s2);
                }
            }
            catch (IOException ioexception) {
                UploadFile.log.debug(ioexception.toString() + " error reading or saving file");
            }
            mysession.setAttribute("noticeattach", (Object)s7);
            response.sendRedirect("./interfaceengine.PortalServlet?IID=LearnityNoticeBoardAttachment");
        }
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    static {
        log = new SimpleLogger((Class)UploadFile.class);
    }
}
