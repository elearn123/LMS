package learnitysce;

import java.io.*;
import javax.servlet.*;
import org.apache.ecs.*;
import org.apache.ecs.html.*;
import javax.servlet.http.*;
import java.util.*;

public class StudentPolls extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        final String strPrmAddModDel = request.getParameter("prmAddModify");
        this.getResult(request, response, out);
    }
    
    public void getResult(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out) throws IOException, ServletException {
        final Calendar calendar = new GregorianCalendar();
        final Date trialTime = new Date();
        calendar.setTime(trialTime);
        final String[] months = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        final int mon = calendar.get(2) + 1;
        final String strTime = calendar.get(10) + ":" + calendar.get(12);
        final String strDate = months[calendar.get(2)] + " " + calendar.get(5) + ", " + calendar.get(1);
        final HttpSession mysession = request.getSession(false);
        final String student_id = (String)mysession.getAttribute("student_id");
        final String UnitID = (String)mysession.getAttribute("sce_id");
        System.out.println("============UnitID===========" + UnitID);
        final String pollid = SceDataBaseLayer.GetPollId(UnitID, "Activated");
        final String status = SceDataBaseLayer.Getnotsubmittedpollstatus(UnitID, student_id);
        System.out.println("==================status============" + status);
        String publish_status = "";
        final Vector published_poll = SceDataBaseLayer.getPublishedPoll(UnitID, "Published");
        if (published_poll.size() != 0) {
            publish_status = "published";
        }
        final String javaScript = "var index = 0;\n var rowId = 0;\n\tfunction Poll_Submit() {\n\t\tdocument.frm.method=\"post\";\n             document.frm.action=\"./dashboard.chart3?id=pollchart1&argument1=\"+document.frm.Qchoices.value+\"\";\n             document.frm.target=\"ChartFrame\";\n\t\tdocument.frm.submit();\n\t}\n\tfunction body_onload() {\n\tif(\"" + status + "\"==\"Activated\"){" + "\n\tlocation.href=\"learnitysce.PollQuestions?ID=" + UnitID + "&status=" + status + "\";" + "\n\t}" + "\n\t}" + "\n\tfunction Poll_Refresh() {" + "\n  if(\"" + publish_status + "\"==\"published\") {" + "\n\t\tdocument.frm.method=\"post\";" + "\n\t\tdocument.frm.action = \"./interfaceenginev2.PortalServlet?IID=StudentPublishPolls\";" + "\n            document.frm.target=\"scePnQStudentPublishPoll\";" + "\n\t\tdocument.frm.submit();" + "\n       }" + "\n\tif(\"" + status + "\"==\"Activated\"){" + "\n\tlocation.href=\"learnitysce.PollQuestions?ID=" + UnitID + "&status=" + status + "\";" + "\n\t}else{" + "\n\tlocation.href=\"learnitysce.StudentPolls?ID=" + UnitID + "\";" + "\n\t}" + "\n\t}";
        final Body body = new Body();
        final Form form = new Form();
        form.setMethod("Post");
        form.setName("frm");
        final Input inputButton1 = new Input();
        final Div BodyPart = new Div();
        BodyPart.setID("pollresultbg");
        final Div PollingMessage = new Div();
        PollingMessage.setID("PollingMessage");
        final Table tblList = new Table();
        final Table table2 = new Table();
        final P p = new P();
        final Input inputButton2 = new Input();
        inputButton2.setOnClick("Poll_Submit();");
        final Html html = new Html().addElement((Element)new Head().addElement((Element)new Title("StudentPoll")).addElement((Element)new Link().setRel("stylesheet").setType("text/css").setHref("../coreadmin/css/stylesheet.css")).addElement((Element)new Script().setLanguage("JavaScript").setSrc("../js/check.js")).addElement((Element)new Script().setLanguage("JavaScript").addElement(javaScript)));
        BodyPart.addElement(new Div().addElement("Polls AND Quizzes: ").setID("title2"));
        PollingMessage.addElement(new Div().addElement("No Poll and Quiz is Activated that is not yet submitted.").setID("pollmessage"));
        PollingMessage.addElement((Element)new Table().setAlign("center").setBorder(0).setCellPadding(0).setCellSpacing(0).addElement((Element)new TR().addElement((Element)new TD().addElement((Element)inputButton1.setClassId("sbttn").setName("refresh").setTabindex(15).setTitleValue("Refresh").setType("button").setValue("Refresh")))));
        BodyPart.addElement((Element)PollingMessage);
        form.addElement((Element)BodyPart);
        body.addElement((Element)form);
        body.setOnLoad("body_onload();");
        html.addElement((Element)body);
        inputButton1.setOnClick("Poll_Refresh();");
        out.println(html.toString());
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
