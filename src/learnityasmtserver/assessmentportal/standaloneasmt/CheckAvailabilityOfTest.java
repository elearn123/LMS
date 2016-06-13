package learnityasmtserver.assessmentportal.standaloneasmt;

import org.grlea.log.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.*;
import learnityasmtserver.assessmentportal.dbconnection.*;
import org.apache.ecs.*;
import org.apache.ecs.html.*;
import java.util.*;

public class CheckAvailabilityOfTest extends HttpServlet
{
    public static final SimpleLogger log;
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        final PrintWriter out = response.getWriter();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final String student_id = request.getParameter("student_id");
        CheckAvailabilityOfTest.log.debug("student_id=doget=" + student_id);
        if (student_id != null) {
            this.getResult(request, response, out, student_id);
        }
    }
    
    public void getResult(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out, final String student_id) throws IOException, ServletException {
        final String checkbox = request.getParameter("assessment_id");
        final String checkModelstatus = request.getParameter("checkModelstatus");
        final String newtest = request.getParameter("newtest");
        final String sumissionsatatus = request.getParameter("sumissionsatatus");
        final String browserName = request.getParameter("browserName");
        String MaxtestTeken = "0";
        String nooftimesappeared = "0";
        String astatus = "";
        String save_state = "";
        String pretest_id = "";
        final String lastquesindex = "0";
        int MaxtestTeken2 = 0;
        int nooftimesappeared2 = 0;
        final ReportDataBaseLayer ob1 = new ReportDataBaseLayer();
        if (checkbox != null) {
            MaxtestTeken = ob1.getMaxtestTaken(checkbox);
            nooftimesappeared = ob1.getMaxNoAppearedInTest(student_id, checkbox);
            final Vector<String> vv = ob1.isPreviousTestSubmitted(student_id, checkbox);
            if (vv.size() != 0) {
                astatus = vv.elementAt(0);
                save_state = vv.elementAt(1);
                pretest_id = vv.elementAt(2);
            }
            MaxtestTeken2 = Integer.parseInt(MaxtestTeken);
            nooftimesappeared2 = Integer.parseInt(nooftimesappeared);
        }
        if (save_state == null) {
            save_state = "";
        }
        final String javaScript = "\n function Quit_onclick() {\n \t\t\twindow.parent.close(); \n \t}";
        final Html html = new Html();
        final Head head = new Head();
        final Form form = new Form().setName("theForm").setMethod("post");
        final Link link = new Link();
        final Link link2 = new Link();
        final Input inputButton1 = new Input();
        final Script script12 = new Script();
        link.setHref("../coreadmin/js/OnlineTesting.css");
        link2.setHref("../coreadmin/js/calendar1.js");
        link.setRel("stylesheet");
        head.addElement((Element)new Script().setLanguage("JavaScript").addElement(javaScript));
        final Body body1 = new Body();
        final Div div1 = new Div();
        final Div div2 = new Div();
        div1.addElement(new Div().addElement((Element)inputButton1.setClassId("sbttn").setName("OK").setType("button").setValue("OK")).setID("Quitchecktestavailablity"));
        div2.addElement(new Div().addElement("You have already appeared for this test. You cannot appear for it again. [ Number of times appeared :- " + MaxtestTeken + " ]").setID("CheckAvailabilitytestInfo"));
        if (nooftimesappeared2 < MaxtestTeken2) {
            if (browserName != null && browserName.equals("Microsoft Internet Explorer")) {
                if (astatus.equals("") || astatus.equals("Submitted")) {
                    response.sendRedirect("./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTestHTML?newtest=start&checkModelstatus=" + checkModelstatus + "&assessment_id=" + checkbox + "#" + lastquesindex + "");
                }
                else if (save_state.equals("")) {
                    response.sendRedirect("./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTestHTML?newtest=start&save_state=no&checkModelstatus=" + checkModelstatus + "&assessment_id=" + checkbox + "#" + lastquesindex + "");
                }
                else {
                    response.sendRedirect("./learnityasmtserver.assessmentportal.standaloneasmt.GenerateTestHTML?newtest=start&checkModelstatus=" + checkModelstatus + "&assessment_id=" + checkbox + "#" + lastquesindex + "");
                }
            }
            else if (astatus.equals("") || astatus.equals("Submitted")) {
                response.sendRedirect("./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTest?newtest=start&checkModelstatus=" + checkModelstatus + "&assessment_id=" + checkbox + "#" + lastquesindex + "");
            }
            else if (save_state.equals("")) {
                response.sendRedirect("./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTest?newtest=start&save_state=no&checkModelstatus=" + checkModelstatus + "&assessment_id=" + checkbox + "#" + lastquesindex + "");
            }
            else {
                response.sendRedirect("./learnityasmtserver.assessmentportal.standaloneasmt.GenerateTestXHTML?newtest=start&checkModelstatus=" + checkModelstatus + "&assessment_id=" + checkbox + "#" + lastquesindex + "");
            }
        }
        else {
            form.addElement(new Div().addElement((Element)div1).addElement((Element)div2).setID("CheckAvailabilitytest"));
        }
        inputButton1.setOnClick("Quit_onclick();");
        body1.addElement((Element)form);
        head.addElement((Element)script12);
        head.addElement((Element)link);
        head.addElement((Element)link2);
        html.addElement((Element)head);
        html.addElement((Element)body1);
        out.println(html.toString());
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    static {
        log = new SimpleLogger((Class)CheckAvailabilityOfTest.class, true);
    }
}
