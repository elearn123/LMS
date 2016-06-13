package learnitysce;

import java.io.*;
import javax.servlet.*;
import org.apache.ecs.*;
import org.apache.ecs.html.*;
import java.util.*;
import javax.servlet.http.*;

public class PollResultRequest extends HttpServlet
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
        System.out.println("========student in pollrequest===" + student_id);
        final String SCEID = (String)mysession.getAttribute("sce_id");
        final String ChoiceText = request.getParameter("Qchoices");
        final String MChoiceText1 = request.getParameter("Qchoicem0");
        final String MChoiceText2 = request.getParameter("Qchoicem1");
        final String MChoiceText3 = request.getParameter("Qchoicem2");
        final String ActPollid = SceDataBaseLayer.GetPollId(SCEID, "Activated");
        final String pollid = request.getParameter("pollid");
        final String[] MChoiceSelected = { MChoiceText1, MChoiceText2, MChoiceText3 };
        String ChoiceID = "";
        boolean PollFlag = false;
        if (ActPollid.equals(pollid)) {
            if (ChoiceText != null) {
                ChoiceID = SceDataBaseLayer.GetChoiceId(pollid, ChoiceText);
                SceDataBaseLayer.PollingResults(pollid, ChoiceID, student_id);
            }
            else {
                for (int m = 0; m < MChoiceSelected.length; ++m) {
                    if (MChoiceSelected[m] != null) {
                        ChoiceID = SceDataBaseLayer.GetChoiceId(pollid, MChoiceSelected[m]);
                        SceDataBaseLayer.PollingResults(pollid, ChoiceID, student_id);
                    }
                }
                System.out.println("***************PollResultRequest_MC********************");
            }
        }
        else {
            PollFlag = true;
        }
        final String status = SceDataBaseLayer.Getpollstatus(SCEID, pollid);
        System.out.println("***************status_PollResultRequest*********************" + status);
        String javaScript = "var index = 0;\n var rowId = 0;";
        if (PollFlag) {
            javaScript += "\n alert (\"Active Poll Changed,your poll is not counted\")";
        }
        javaScript = javaScript + "\n\tfunction Poll_Refresh() {" + "\n\t\tdocument.frm.method=\"post\";" + "\n\t\tdocument.frm.action = \"./interfaceenginev2.PortalServlet?IID=PollsandQuiz\";" + "\n            document.frm.target=\"sceMainFrame\";" + "\n\t\tdocument.frm.submit();" + "\n\t}";
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
        final P p = new P();
        final Html html = new Html().addElement((Element)new Head().addElement((Element)new Title("StudentPoll")).addElement((Element)new Link().setHref("../coreadmin/css/stylesheet.css").setRel("stylesheet")).addElement((Element)new Script().setLanguage("JavaScript").setSrc("../js/check.js")).addElement((Element)new Script().setLanguage("JavaScript").addElement(javaScript)));
        BodyPart.addElement(new Div().addElement("Polls AND Quizzes: ").setID("title2"));
        PollingMessage.addElement(new Div().addElement("Refresh For Result,Not Yet Published ").setID("pollmessage"));
        PollingMessage.addElement((Element)new Table().setAlign("center").setBorder(0).setCellPadding(0).setCellSpacing(0).addElement((Element)new TR().addElement((Element)new TD().addElement((Element)inputButton1.setClassId("sbttn").setName("refresh").setTabindex(2).setTitleValue("Refresh").setType("button").setValue("Refresh")))));
        BodyPart.addElement((Element)PollingMessage);
        form.addElement((Element)BodyPart);
        body.addElement((Element)form);
        body.setOnLoad("scrollit(100);");
        html.addElement((Element)body);
        inputButton1.setOnClick("Poll_Refresh();");
        out.println(html.toString());
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
