package learnityasmtserver.assessmentadmin.standaloneasmt;

import org.grlea.log.*;
import javax.servlet.http.*;
import learnityasmtserver.assessmentadmin.dbconnection.*;
import learnityInit.*;
import javax.servlet.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.*;
import java.util.*;
import java.io.*;

public class SendMailAll extends HttpServlet
{
    public static final SimpleLogger log;
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String assess_id = request.getParameter("assessment_id");
        System.out.println("=====assess_id======s2 in sm========" + assess_id);
        String student_id = "";
        final String s2 = request.getParameter("message");
        System.out.println("===========s2 in sm========" + s2);
        final Document document1 = new Document();
        final Vector<String> registered_students = DataBaseLayer.getStudentsForResult(assess_id);
        if (registered_students.size() != 0) {
            for (int j = 0; j < registered_students.size(); ++j) {
                student_id = registered_students.elementAt(j);
                String dirName = Host.getServerDocumentPath();
                System.out.println("==================dirName==========" + dirName);
                File uploadfile = null;
                final String[] str = { "assessmentresult", assess_id, student_id + File.separator };
                for (int i = 0; i < 3; ++i) {
                    if (dirName == null) {
                        throw new ServletException("Please supply uploadDir parameter");
                    }
                    dirName = dirName + File.separator + str[i];
                    uploadfile = new File(dirName);
                    if (!uploadfile.exists()) {
                        uploadfile.mkdir();
                    }
                }
                String assess_name = "";
                String student_name = "";
                String full_marks = "";
                String marks_obtained = "";
                String status = "";
                String user_id = "";
                final Vector<String> AssessDetails = DataBaseLayer.getAssessmentDetailResult(assess_id, student_id);
                if (AssessDetails.size() != 0) {
                    for (int k = 0; k < AssessDetails.size(); k += 6) {
                        assess_name = AssessDetails.elementAt(k);
                        student_name = AssessDetails.elementAt(k + 1);
                        full_marks = AssessDetails.elementAt(k + 2);
                        marks_obtained = AssessDetails.elementAt(k + 3);
                        status = AssessDetails.elementAt(k + 4);
                        user_id = AssessDetails.elementAt(k + 5);
                    }
                }
                try {
                    response.setContentType("application/pdf");
                    final PdfWriter pdf_writer = PdfWriter.getInstance(document1, (OutputStream)response.getOutputStream());
                    final PdfWriter pdf_writer2 = PdfWriter.getInstance(document1, (OutputStream)new FileOutputStream(dirName + "assessresult.pdf"));
                    final HeaderFooter header = new HeaderFooter(new Phrase("Result Summary\n"), false);
                    document1.setHeader(header);
                    document1.open();
                    final Font font8 = new Font(2, 10.0f);
                    final PdfPTable table = new PdfPTable(2);
                    table.addCell(new Phrase("Assessment Name", font8));
                    table.addCell(new Phrase(assess_name, font8));
                    table.addCell(new Phrase("User ID", font8));
                    table.addCell(new Phrase(user_id, font8));
                    table.addCell(new Phrase("User Name", font8));
                    table.addCell(new Phrase(student_name, font8));
                    table.addCell(new Phrase("Full Marks", font8));
                    table.addCell(new Phrase(full_marks, font8));
                    table.addCell(new Phrase("Marks Obtained", font8));
                    table.addCell(new Phrase(marks_obtained, font8));
                    table.addCell(new Phrase("Status", font8));
                    table.addCell(new Phrase(status, font8));
                    document1.add((Element)table);
                }
                catch (DocumentException de) {
                    de.printStackTrace();
                    System.err.println("document: " + de.getMessage());
                }
                catch (Exception e) {
                    throw new ServletException("Exception in Excel Sample Servlet" + e.getMessage());
                }
            }
        }
        response.sendRedirect("./learnityasmtserver.assessmentadmin.standaloneasmt.SendMailServletAll?AssessTitleSelect=" + assess_id + "&message=" + s2);
        document1.close();
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    static {
        log = new SimpleLogger((Class)SendMailAll.class, true);
    }
}
