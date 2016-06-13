package learnitysce;

import org.apache.ecs.*;
import org.apache.ecs.html.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;

public class PollQuestions extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final PrintWriter out = response.getWriter();
        final HttpSession mysession = request.getSession(false);
        final String student_id = (String)mysession.getAttribute("student_id");
        final Calendar calendar = new GregorianCalendar();
        final Date trialTime = new Date();
        calendar.setTime(trialTime);
        final String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        final String strTime = calendar.get(10) + ":" + calendar.get(12);
        final String strDate = months[calendar.get(2)] + " " + calendar.get(5) + ", " + calendar.get(1);
        final String strPrmAddModDel = request.getParameter("prmAddModify");
        final String UnitID = (String)mysession.getAttribute("sce_id");
        final String pollid = SceDataBaseLayer.GetPollId(UnitID, "Activated");
        final String javaScript = " var index = 0;\n function CCA1(x){\n \tif (document.frm.checkbox[x].checked)\n \t\thL(document.frm.checkbox[x]);\n \telse\n \t\tdL(document.frm.checkbox[x]);\n }\n\tfunction test() {\n\t\tvar index = 0;\n\t\tfor (var i=0;i<document.frm.elements.length;i++){\n\t\t\tvar e = document.frm.elements[i];\n\t\t\tif (e.type=='radio'){\n\t\t\t\tindex++;\n\t\t\t}\n\t\t}\n\t\treturn index;\n\t}\n\tfunction findRow(){\n\t\tfor (var counter=0; counter<document.frm.checkbox.length; counter++) {\n\t\t\tif (document.frm.checkbox[counter].value == groupId) {\n\t\t\t\trowId = counter;\n\t\t\t}\n\t\t}\n\t\treturn true;\n\t}\n\n\tfunction checkEntries(){\n\t\tvar filledIn = false;\n\t\tvar n = test();\n\t\tif(n==1){\n\t\t\tif (document.frm.checkbox.checked == true) {\n\t\t\t\tfilledIn = true;\n\t\t\t\tindex = 0;\n\t\t\t}\n\t\t\tif (filledIn == false){\n\t\t\t\talert('You must select one Style');\n\t\t\t\treturn false;\n\t\t\t}\n\t\t}\n\t\tif(n>1){\n\t\t\tfor (var counter=0; counter<document.frm.checkbox.length; counter++)\n\t\t\t\tif (document.frm.checkbox[counter].checked == true) {\n\t\t\t\t\tfilledIn = true;\n\t\t\t\t\tindex = counter;\n\t\t\t\t}\n\t\t\tif (filledIn == false){\n\t\t\t\talert('You must select  one Style');\n\t\t\t\treturn false;\n\t\t\t}\n\t\t}\n\t\treturn true;\n\t}\n\n\tfunction Poll_Submit() {\n\t\tdocument.frm.method=\"post\";\n\t\tdocument.frm.action = \"learnitysce.PollResultRequest?ID=" + UnitID + "&pollid=" + pollid + "\";" + "\n\t\tdocument.frm.submit();" + "\n\t}" + "\n\tfunction Poll_Result_Refresh() {" + "\n\t\tdocument.frm.method=\"post\";" + "\n\t\tdocument.frm.action = \"./interfaceenginev2.PortalServlet?IID=PollsandQuiz\";" + "\n             document.frm.target=\"sceMainFrame\";" + "\n\t\tdocument.frm.submit();" + "\n\t}" + "\n\tfunction deleteLayout_onclick() {" + "\n\t\tvar i = test();" + "\n\t\tif(i==1) {" + "\n\t\t\tif(checkEntries()){" + "\n\t\t\t\tdoyou = confirm(\"Are you Sure to Delete The style?\"); //Your question." + "\n\t\t\t\tif (doyou == true) {" + "\n\t\t\t\tdocument.frm.method=\"post\";" + "\n\t\t\t\t\tdocument.frm.action = \"./PortalMgmt.PortalLayout.ImageManagement?prmAddModify=2&styleid=\"+document.frm.hiddenempid.value;" + "\n\t\t\tdocument.frm.encoding = \"multipart/form-data\";" + "\n\t\t\t\tdocument.frm.submit();" + "\n\t\t\t\t}" + "\n\t\t\t\telse {" + "\n\t\t\t\t}" + "\n\t\t\t}" + "\n\t\t}" + "\n\t\tif(i>1) {" + "\n\t\t\tfor(var counter=0; counter<document.frm.checkbox.length; counter++) {" + "\n\t\t\t\tif(document.frm.checkbox[counter].checked) {" + "\n\t\t\tif(checkEntries()){" + "\n\t\t\t\tdoyou = confirm(\"Are you Sure to Delete The Style?\"); //Your question." + "\n\t\t\t\tif (doyou == true) {" + "\n\t\t\t\tdocument.frm.method=\"post\";" + "\n\t\t\t\tdocument.frm.action = \"./PortalMgmt.PortalLayout.ImageManagement?prmAddModify=2&styleid=\"+document.frm.hiddenempid[counter].value;" + "\n\t\t\t\tdocument.frm.encoding = \"multipart/form-data\";" + "\n\t\t\t\tdocument.frm.submit();" + "\n\t\t\t\t}" + "\n\t\t\t\telse {" + "\n\t\t\t\t}" + "\n\t\t\t}" + "\n\t\t\t}" + "\n\t\t\t}" + "\n\t\t}" + "\n\t}" + "\n" + "\n\tfunction validate(){" + "\n\t\tif(!fnCheckNull(document.frm.styleid.value,\"Group Id\")){" + "\n\t\t\tdocument.frm.styleid.focus();" + "\n\t\t\treturn false;" + "\n\t\t}" + "\n\t\treturn true;" + "\n\t}" + "\n\tfunction checkbox_onclick() {" + "\n\t\tvar i = test();" + "\n\t\tif(i>1) {" + "\n\t\t\tfor(var counter=0; counter<document.frm.checkbox.length; counter++) {" + "\n\t\t\t\tif(document.frm.checkbox[counter].checked) {" + "\n\t\t\t\t\tdocument.frm.styleid.value = document.frm.hiddenempid[counter].value;" + "\n\t\t\t\t\tbreak;" + "\n\t\t\t\t}" + "\n\t\t\t}" + "\n\t\t}" + "\n\t\tif(i==1) {" + "\n\t\t\tdocument.frm.styleid.value = document.frm.hiddenempid.value;" + "\n\t\t}" + "\n\t}" + "\n" + "\n\tfunction load() {" + "\n\t\tif (window.parent.leftFrame1 != null) {" + "\n\t\t\twindow.parent.leftFrame1.location.reload();" + "\n\t\t}" + "\n }";
        final Input inputButton1 = new Input();
        inputButton1.setOnClick("Poll_Submit();");
        final Input inputButton2 = new Input();
        inputButton2.setOnClick("Poll_Result_Refresh();");
        final Html html = new Html().addElement((Element)new Head().addElement((Element)new Title("File Management")).addElement((Element)new Link().setHref("../coreadmin/css/stylesheet.css").setRel("stylesheet")).addElement((Element)new Script().setLanguage("JavaScript").setSrc("../js/check.js")).addElement((Element)new Script().setLanguage("JavaScript").setSrc("../js/picker.js")).addElement((Element)new Script().setLanguage("JavaScript").addElement(javaScript)));
        final Form form = new Form().setName("frm").setMethod("post");
        final Body body = new Body();
        final Input styleid = new Input().setType("text").setName("styleid").setClassId("PPRLabelText");
        final Table table = new Table();
        final Table table2 = new Table();
        final Table table3 = new Table();
        final Div BodyPart = new Div();
        BodyPart.setID("pollresultbg");
        final Div PollingMessage = new Div();
        PollingMessage.setID("PollingMessage");
        table3.addElement(new Div().addElement("Polls AND Quizzes: ").setID("title2"));
        Vector<String[]> vPolldetails = new Vector(4, 4);
        vPolldetails = SceDataBaseLayer.getPolldetails(pollid);
        Vector<String[]> vChoicedetails = new Vector<String[]>(3, 3);
        vChoicedetails = SceDataBaseLayer.getChoicedetails(pollid);
        if (vPolldetails != null & vChoicedetails != null) {
            for (int i = 0; i < vPolldetails.size(); ++i) {
                final String[] sPolldetails = vPolldetails.elementAt(i);
                System.out.println("************PollTitle*********************" + sPolldetails[0]);
                System.out.println("************polltext*********************" + sPolldetails[1]);
                System.out.println("************Question Type*********************" + sPolldetails[2]);
                final int m = i + 1;
                final String Qno = "Q" + m + ".";
                table2.addElement((Element)new TR().addElement((Element)new TD().addElement("<b>Poll Title:</b>").addElement(sPolldetails[0]).setClassId("pollquestiontitle")));
                table2.addElement((Element)new TR().addElement((Element)new TD().addElement(Qno + "" + sPolldetails[1]).setClassId("pollquestion")));
                for (int j = 0; j < vChoicedetails.size(); ++j) {
                    final String[] sChoicedetails = vChoicedetails.elementAt(j);
                    final String ChoiceID = SceDataBaseLayer.GetChoiceId(pollid, sChoicedetails[1]);
                    if (sPolldetails[2].equals("s")) {
                        table2.addElement((Element)new TR().addElement((Element)new TD().setClassId("pollchoices").addElement((Element)new Input().setType("radio").setName("Qchoices").setValue(sChoicedetails[1]).addElement(sChoicedetails[1]))));
                    }
                    else {
                        table2.addElement((Element)new TR().addElement((Element)new TD().setClassId("pollchoices").addElement((Element)new Input().setType("checkbox").setName("Qchoicem" + j).setValue(sChoicedetails[1]).addElement(sChoicedetails[1]))));
                    }
                }
            }
        }
        final Table table4 = new Table();
        if (!SceDataBaseLayer.checkSubmittedPoll(student_id, pollid)) {
            table4.addElement((Element)new Table().setAlign("center").setBorder(0).setCellPadding(0).setCellSpacing(0).addElement((Element)new TR().addElement((Element)new TD().addElement((Element)inputButton1.setClassId("sbttn").setName("addGrop").setTabindex(2).setTitleValue("Submit").setType("button").setValue("Submit"))).addElement((Element)new TD().setWidth(5))));
        }
        else {
            table4.addElement((Element)new Table().setAlign("center").setBorder(0).setCellPadding(0).setCellSpacing(0).addElement((Element)new TR().addElement((Element)new TD().addElement((Element)inputButton2.setClassId("sbttn").setName("refresh").setTabindex(2).setTitleValue("Refresh").setType("button").setValue("Refresh"))).addElement((Element)new TD().setWidth(5))));
        }
        BodyPart.addElement((Element)table3);
        table.addElement((Element)new TR().addElement((Element)new TD().addElement((Element)table2)));
        table.addElement((Element)new TR().addElement((Element)new TD().addElement((Element)table4)));
        PollingMessage.addElement((Element)table);
        BodyPart.addElement((Element)PollingMessage);
        form.addElement((Element)BodyPart);
        body.addElement((Element)form);
        html.addElement(body.setClass("bodyadmin"));
        out.print(html.toString());
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
