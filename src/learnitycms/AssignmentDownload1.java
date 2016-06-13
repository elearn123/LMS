package learnitycms;

import learnityInit.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.*;

public class AssignmentDownload1 extends HttpServlet
{
    public void doGet(final HttpServletRequest req, final HttpServletResponse res) throws IOException, ServletException {
        final HttpSession mysession = req.getSession(true);
        final String assignmentId = (String)mysession.getAttribute("assign_id");
        final String doc_id = (String)mysession.getAttribute("document_id");
        final String fileName = (String)mysession.getAttribute("doc_name");
        final String student_id = (String)mysession.getAttribute("user_id");
        System.out.println("fileName:" + fileName + "  doc_id:" + doc_id);
        FileInputStream fIn = null;
        String path = Host.getServerCoursePath() + "assignment" + File.separator + "submitted" + File.separator + assignmentId + File.separator + student_id + File.separator + fileName;
        System.out.println("---------------######### PATH FOR DOWNLOADING DOCUMENT #########-------------" + path);
        final File f = new File(path);
        final ServletOutputStream sOut = res.getOutputStream();
        path = f.getPath();
        fIn = new FileInputStream(f);
        final String contentType = this.getServletContext().getMimeType(fileName);
        res.setContentType(contentType);
        res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        try {
            final byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fIn.read(buf)) > 0) {
                System.out.println("len is :" + len);
                sOut.write(buf, 0, len);
            }
            sOut.close();
        }
        catch (Exception excep) {
            System.out.println("Exception while writing file " + excep.toString());
        }
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
