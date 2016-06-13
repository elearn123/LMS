package learnityasmtserver.assessmentadmin.standaloneasmt;

import org.grlea.log.*;
import javax.servlet.http.*;
import learnityInit.*;
import javax.servlet.*;
import learnityasmtserver.assessmentadmin.dbconnection.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.*;
import java.util.*;
import java.io.*;

public class SendMail extends HttpServlet
{
    public static final SimpleLogger log;
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String assess_id = request.getParameter("assessment_id");
        System.out.println("===SendMail=====assess_id=======" + assess_id);
        final String student_id = request.getParameter("student_id");
        System.out.println("===SendMail=====student_id=======" + student_id);
        final String s2 = request.getParameter("message");
        System.out.println("====SendMail=======s2 in sm========" + s2);
        String dirName = Host.getServerDocumentPath();
        System.out.println("====SendMail==============dirName==========" + dirName);
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
        //final String spfno = "";
        //final OutputStream out1 = null;
        final Document document1 = new Document();
        final Vector<String> AssessDetails = DataBaseLayer.getAssessmentDetailResult(assess_id, student_id);
        if (AssessDetails.size() != 0) {
            for (int j = 0; j < AssessDetails.size(); j += 6) {
                assess_name = AssessDetails.elementAt(j);
                student_name = AssessDetails.elementAt(j + 1);
                full_marks = AssessDetails.elementAt(j + 2);
                marks_obtained = AssessDetails.elementAt(j + 3);
                status = AssessDetails.elementAt(j + 4);
                user_id = AssessDetails.elementAt(j + 5);
            }
        }
        try {
//            response.setContentType("application/pdf");
            //final PdfWriter pdf_writer = PdfWriter.getInstance(document1, (OutputStream)response.getOutputStream());
            PdfWriter pdf_writer2 = PdfWriter.getInstance(document1, (OutputStream)new FileOutputStream(dirName + "assessresult.pdf"));
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
            System.err.println("Exception in Excel Sample Servlet" + e.getMessage());
        }
        document1.close();
        response.sendRedirect("./learnityasmtserver.assessmentadmin.standaloneasmt.SendMailServlet?AssessTitleSelect=" + assess_id + "&student_id=" + student_id + "&message=" + s2);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    static {
        log = new SimpleLogger((Class)SendMail.class, true);
    }
}
