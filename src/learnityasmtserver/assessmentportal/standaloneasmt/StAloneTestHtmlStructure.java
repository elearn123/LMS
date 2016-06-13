package learnityasmtserver.assessmentportal.standaloneasmt;

import org.grlea.log.*;
import org.apache.ecs.*;
import learnityasmtserver.assessmentportal.dbconnection.*;
import org.apache.ecs.html.*;
import java.util.*;

public class StAloneTestHtmlStructure
{
    public static final SimpleLogger log;
    
    public String generateHTML(final Vector<Vector<Vector>> questionAll, final String strButton1, final int duration, final boolean bRefresh, final String total_ques, final String ques_per_page, final String total_time_left, int no_of_question_retrived, final int total_no_of_question_retrived, int serial_no, final int total_time, final String assessment_id1, final Vector vQuesIdAns, final String AssessmentTitle, final String refreshassessment, final String timeparam) {
        final Calendar calendar = new GregorianCalendar();
        final Date trialTime = new Date();
        calendar.setTime(trialTime);
        final String[] months = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        final String strTime = calendar.get(10) + ":" + calendar.get(12);
        final String strDate = months[calendar.get(2)] + " " + calendar.get(5) + ", " + calendar.get(1);
        final StringBuffer strHTML = new StringBuffer();
        final Html html1 = new Html();
        final Head head1 = new Head();
        final Title title1 = new Title();
        final Link csslink = new Link().setRel("stylesheet");
        final Link csslink2 = new Link();
        csslink2.setRel("stylesheet");
        csslink2.setHref("../coreadmin/js/OnlineTesting.css");
        head1.addElement((Element)csslink2);
        final Script script1 = new Script().setLanguage("javascript");
        final Link link = new Link();
        link.setHref("../coreadmin/js/Assessmentportal.css");
        link.setRel("stylesheet");
        head1.addElement((Element)link);
        final Link l2 = new Link();
        final Body body1 = new Body();
        body1.setOnKeyDown("trapKey()");
        body1.setOnLoad("Down(" + duration + ")");
        final Form form1 = new Form();
        form1.setMethod("Post");
        form1.setID("frm");
        form1.setName("frm");
        final Table table1 = new Table().setCellPadding(0).setCellSpacing(1).setWidth("75%");
        final TR tr1 = new TR();
        final TD td1 = new TD().setWidth("50%").setHeight(23);
        final TD td2 = new TD().setWidth("25%");
        final TD td3 = new TD().setWidth("25%");
        final TR tr2 = new TR().setBgColor("#336699");
        final TD td4 = new TD().setColSpan("3").setWidth("25%");
        final TR tr3 = new TR().setBgColor("#336699");
        final TD td5 = new TD().setColSpan("3").setWidth("25%").setHeight(5);
        final TR tr4 = new TR();
        final TD td6 = new TD().setColSpan("3").setWidth("25%").setHeight(23);
        final TR tr5 = new TR().setBgColor("#336699");
        final TD td7 = new TD().setColSpan("3").setWidth("25%");
        final Table table2 = new Table().setBorder(0).setCellPadding(1).setCellSpacing(0).setWidth("75%");
        final TR tr6 = new TR();
        final TD td8 = new TD().setColSpan("3");
        final Table table3 = new Table().setCellPadding(0).setCellSpacing(4).setWidth("100%");
        final TR tr7 = new TR();
        final TD td9 = new TD().setAlign("center");
        final Table table4 = new Table();
        final TBody tbody1 = new TBody();
        final TR tr8 = new TR();
        final TD td10 = new TD().setWidth(140);
        final TD td11 = new TD().setWidth("100%");
        final Table table5 = new Table().setBorder(0).setCellPadding(3).setCellSpacing(4);
        final TR tr9 = new TR();
        final TD td12 = new TD();
        final TD td13 = new TD();
        final TD td14 = new TD().setWidth(5);
        final Table table6 = new Table().setBorder(0).setCellPadding(3).setCellSpacing(4);
        final TR tr10 = new TR();
        final TD td15 = new TD();
        final TD td16 = new TD();
        final TD td17 = new TD().setWidth(5);
        final Font font1 = new Font().setSize(3);
        final Font font2 = new Font().setSize(4).setColor("#CC0000");
        final Font font3 = new Font().setSize(3);
        script1.setSrc("../coreadmin/js/ansAssessmen.js");
        csslink.setHref("../coreadmin/js/stylesheet.css");
        body1.addElement((Element)new BR());
        body1.addElement((Element)new Center().addElement((Element)font1).addElement((Element)font2).addElement((Element)font3));
        font1.addElement((Element)new B());
        font1.addElement((Element)new Em().addElement("Powered BY &nbsp;&nbsp;"));
        font2.addElement("LearnITy&#8482;&nbsp;&nbsp;");
        font3.addElement((Element)new B());
        font3.addElement((Element)new Em().addElement("Assessment Server"));
        final Vector<String> getAlrmDetails = ReportDataBaseLayer.getAlrmDetailsvector();
        final String s = "";
        for (int i = 0; i < getAlrmDetails.size(); i += 3) {
            final String title2 = getAlrmDetails.elementAt(i);
            final String time = getAlrmDetails.elementAt(i + 1);
            final String mess = getAlrmDetails.elementAt(i + 2);
            int atime = Integer.parseInt(time);
            atime = atime * 1000 * 60;
            l2.addElement((Element)new Script().setLanguage("JavaScript").addElement("var " + title2 + "1=self.setInterval(\"" + title2 + "()\"," + atime + ");").addElement("function " + title2 + "() {").addElement(" alert('" + mess + "');").addElement("" + title2 + "1=window.clearInterval(" + title2 + "1)}"));
        }
        form1.addElement((Element)new Table().addElement((Element)new TR().addElement((Element)new TD().addElement(new IMG().setSrc("../coreadmin/images/logo.gif").setClass("organistaionlogo"))).addElement((Element)new TD().addElement((Element)table1))));
        table1.addElement(tr1.addElement((Element)td2.addElement((Element)new NOBR().addElement(new Div().addElement("&nbsp;Date:  " + strDate + "&nbsp;").setID("DateTime")))).addElement((Element)td3.addElement((Element)new NOBR().addElement(new Div().addElement("&nbsp;Time: " + strTime + "&nbsp;").setID("DateTime")))).setClass("AssessmentSectionHeading"));
        table1.addElement((Element)tr2.addElement((Element)td4.addElement((Element)new NOBR().addElement(new Div().setID("DivB")))));
        table1.addElement((Element)tr3.addElement((Element)td5));
        table1.addElement(tr4.addElement((Element)td6.addElement((Element)new NOBR().addElement((Element)new NOBR().addElement("<center>Time Left:&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"hidden\" name=\"beg2\" size=\"7\"  value=\"" + duration + "\"/>").addElement("<input type=\"text\" name=\"disp2\" size=\"9\" value=\"" + timeparam + "\"/>").addElement("&nbsp;&nbsp;&nbsp;&nbsp;minutes</center>")))).setClass("AssessmentSectionHeading"));
        table1.addElement((Element)tr5.addElement((Element)td7.addElement((Element)new NOBR().addElement(new Div().setID("DivB")))));
        table2.addElement((Element)tr6.addElement((Element)td8.addElement((Element)new HR().setSize(1))));
        final TR tr11 = new TR();
        final TD td18 = new TD();
        final TD td19 = new TD();
        final TD td20 = new TD();
        table2.addElement((Element)tr11.addElement((Element)td18).addElement((Element)td19).addElement((Element)td20));
        final TR tr12 = new TR();
        final TD td21 = new TD();
        final TD td22 = new TD().setWidth(490).setVAlign("top");
        final TD td23 = new TD();
        final TR tr13 = new TR();
        final TD td24 = new TD();
        final TD td25 = new TD();
        final TD td26 = new TD();
        final Div divQA = new Div();
        table2.addElement((Element)tr12.addElement((Element)td21).addElement((Element)td22.addElement(divQA.setID("QuestionAnswer"))).addElement((Element)td23));
        table2.addElement((Element)tr13.addElement((Element)td24).addElement((Element)td25).addElement((Element)td26));
        String strReturn = "";
        int no_question_per_page = 0;
        if (ques_per_page != null && !ques_per_page.equals("")) {
            no_question_per_page = Integer.parseInt(ques_per_page);
        }
        final Question_Generation generatedquestions = new Question_Generation();
        if (questionAll != null) {
            for (int j = 0; j < questionAll.size(); ++j) {
                final Vector<Vector> questionMatter = questionAll.elementAt(j);
                final Vector response = questionMatter.elementAt(0);
                final Vector question = questionMatter.elementAt(1);
                final Integer iTitle = (Integer) response.elementAt(0);
                final String strQuesNo = (String) question.elementAt(0);
                String selectop = "";
                String[] selectop2 = new String[6];
                Vector vSelecttop = new Vector();
                String titleType = "";
                int n = vQuesIdAns.size() - 2;
                while (n > -1) {
                    final String id = (String) vQuesIdAns.elementAt(n);
                    if (strQuesNo.equals(id)) {
                        if (iTitle == 4) {
                            vSelecttop = (Vector)vQuesIdAns.elementAt(n + 1);
                            break;
                        }
                        if (iTitle != 1) {
                            selectop = (String) vQuesIdAns.elementAt(n + 1);
                            break;
                        }
                        selectop2 = (String[])(Object)vQuesIdAns.elementAt(n + 1);
                        break;
                    }
                    else {
                        n -= 2;
                    }
                }
                switch (iTitle) {
                    case 0: {
                        titleType = "Multiple Choice";
                        strReturn = generatedquestions.MultipleChoiceHTML(response, question, serial_no + 1, selectop, titleType);
                        ++serial_no;
                        divQA.addElement(strReturn);
                        break;
                    }
                    case 1: {
                        strReturn = generatedquestions.MultipleResponseHTML(response, question, serial_no + 1, selectop2);
                        ++serial_no;
                        divQA.addElement(strReturn);
                        break;
                    }
                    case 2: {
                        strReturn = generatedquestions.FillBlanks(response, question, serial_no + 1, selectop);
                        ++serial_no;
                        divQA.addElement(strReturn);
                        break;
                    }
                    case 3: {
                        titleType = "True False";
                        strReturn = generatedquestions.MultipleChoiceHTML(response, question, serial_no + 1, selectop, titleType);
                        ++serial_no;
                        divQA.addElement(strReturn);
                        break;
                    }
                    case 4: {
                        titleType = "Matching Question";
                        strReturn = generatedquestions.MatchingQuestionHTML(response, question, serial_no + 1, vSelecttop);
                        ++serial_no;
                        divQA.addElement(strReturn);
                        break;
                    }
                }
                ++no_of_question_retrived;
                if (j == no_question_per_page - 1) {
                    break;
                }
            }
        }
        table4.addElement((Element)tbody1.addElement((Element)tr8.addElement((Element)td10.addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(140).setSrc("../images/T.gif"))).addElement((Element)td11.addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(1).setSrc("../images/T.gif")))));
        if (questionAll != null) {
            table5.addElement((Element)tr9.addElement((Element)td12.addElement(strButton1)).addElement((Element)td13).addElement((Element)td14)).setClass("submit_button");
        }
        else {
            table6.addElement(divQA.addElement("Questions is not Available, Please contact with your Database vendor.").setID("title"));
            table5.addElement((Element)tr9.addElement((Element)td12.addElement(strButton1)).addElement((Element)td13).addElement((Element)td14)).setClass("submit_button");
        }
        String javaScript1 = "";
        String javaScript2 = "";
        if (bRefresh) {
            javaScript1 = "\n //<![CDATA[\n  function submitPage()  {\n\t//window.document.frmsec.hddTimeLeft.value=timeleft\n\t//buildSectionString()\n\tsubmit_onclick();\n }\n  //]]>";
        }
        javaScript2 = "\n //<![CDATA[\n  function submitPage1() {\n\t\tdocument.getElementById('frm').method=\"post\";\n\t\tdocument.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.StResultSectionHTML?total_ques=" + total_ques + "&AssessmentTitle=" + AssessmentTitle + "&ques_per_page=" + ques_per_page + "&total_time_left=\"+timeleft+\"&no_of_question_retrived=" + no_of_question_retrived + "&total_no_of_question_retrived=" + total_no_of_question_retrived + "&duration=" + duration + "&serial_no=" + serial_no + "&total_time=" + total_time + "&assessment_id1=" + assessment_id1 + "\";" + "\n\t\tdocument.getElementById('frm').target=\"_self\";" + "\n\t\tdocument.getElementById('frm').submit();" + "\n\t}" + "\n\tvar returnValue =\"\";" + "\n  function autosave() {" + "\n \t\ttimeleft1 =Math.round(timeleftr)" + "\n\t\tdocument.getElementById('frm').method=\"post\";" + "\n\t\tdocument.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.GenerateTestHTML?returnValue1=\"+returnValue+\"&total_ques=" + total_ques + "&AssessmentTitle=" + AssessmentTitle + "&ques_per_page=" + ques_per_page + "&total_time_left=\"+timeleft1+\"&no_of_question_retrived=" + no_of_question_retrived + "&total_no_of_question_retrived=" + total_no_of_question_retrived + "&duration=" + duration + "&serial_no=0&total_time=" + total_time + "&assessment_id=" + assessment_id1 + "#\"+returnValue+\"\";" + "\n\t\tdocument.getElementById('frm').target=\"_self\";" + "\n\t\tdocument.getElementById('frm').submit();" + "\n\t}" + "\n  function lastattemptedquestion_onclick(radIndex){" + "\n\treturnValue = radIndex;" + "\n\t}" + "\n  function submitPage() {" + "\n \t\ttimeleft1 =Math.round(timeleftr)" + "\n\t\tdocument.getElementById('frm').method=\"post\";" + "\n\t\tdocument.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.PreviewResult?total_ques=" + total_ques + "&AssessmentTitle=" + AssessmentTitle + "&ques_per_page=" + ques_per_page + "&total_time_left=\"+timeleft1+\"&no_of_question_retrived=" + no_of_question_retrived + "&total_no_of_question_retrived=" + total_no_of_question_retrived + "&duration=" + duration + "&serial_no=" + serial_no + "&total_time=" + total_time + "&assessment_id1=" + assessment_id1 + "&returnValue1=\"+returnValue+\"\";" + "\n\t\tdocument.getElementById('frm').target=\"_self\";" + "\n\t\tdocument.getElementById('frm').submit();" + "\n }" + "\n  //]]>";
        final String javaScript3 = "\n //<![CDATA[\n    function submit_onclick() {\n \t\ttimeleft1 =Math.round(timeleftr)\n\t\tdocument.getElementById('frm').method=\"post\";\n\t        document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTestHTML?total_ques=" + total_ques + "&AssessmentTitle=" + AssessmentTitle + "&ques_per_page=" + ques_per_page + "&total_time_left=\"+timeleft1+\"&no_of_question_retrived=" + no_of_question_retrived + "&total_no_of_question_retrived=" + total_no_of_question_retrived + "&duration=" + duration + "&serial_no=" + serial_no + "&total_time=" + total_time + "&assessment_id1=" + assessment_id1 + "\";" + "\n\t\tdocument.getElementById('frm').target=\"_self\";" + "\n\t\tdocument.getElementById('frm').submit();" + "\n    }" + "\n  function Previous_onclick() {" + "\n \t\ttimeleft1 =Math.round(timeleftr)" + "\n\t\tdocument.getElementById('frm').method=\"post\";" + "\n\t        document.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTestHTML?total_ques=" + total_ques + "&AssessmentTitle=" + AssessmentTitle + "&ques_per_page=" + ques_per_page + "&total_time_left=\"+timeleft1+\"&no_of_question_retrived=" + no_of_question_retrived + "&total_no_of_question_retrived=" + total_no_of_question_retrived + "&duration=" + duration + "&serial_no=" + serial_no + "&total_time=" + total_time + "&assessment_id1=" + assessment_id1 + "&previous=1\";" + "\n\t\tdocument.getElementById('frm').target=\"_self\";" + "\n\t\tdocument.getElementById('frm').submit();" + "\n    }" + "\n  function ok_onclick() {" + "\n\tdocument.getElementById('frm').method=\"post\";" + "\n\tdocument.getElementById('frm').action=\"./learnityasmtserver.assessmentportal.standaloneasmt.StResultSectionHTML\";" + "\n \twindow.close();" + "\n}" + "\n  //]]>";
        final String quit = "\n function Quit_onclick() {\n \t\tif( confirm(\"Do you wish to quit?\") ) {\n \t\twindow.opener.location.reload();\n \t\t\twindow.parent.close(); \n \t\t}\n \t}";
        final String scriptfortime = "\n //<![CDATA[\n var timeleft\n var timeleftr\n var up,down\n   var min1,sec1\n  var cmin1,csec1,cmin2,csec2\n \ttimeleftatrefresh =Math.round(timeleftr)\n function Quit_onclick() {\n \t\tif( confirm(\"Do you wish to Cancel The Test?\") ) {\n \t\twindow.opener.location.reload();\n \t\t\twindow.parent.close(); \n \t\t}\n \t}\n function Minutes(data) {\n  for(var i=0;i<data.length;i++)\n if(data.substring(i,i+1)==\":\")\n break\n  return(data.substring(0,i)); }\n  function Seconds(data) {\n for(var i=0;i<data.length;i++)\n if(data.substring(i,i+1)==\":\")\n break\n  return(data.substring(i+1,data.length)); }\n function Display(min,sec) {\n   var disp;\n if(min<=9) disp=\" 0\";\n else disp=\" \";\n disp+=min+\":\";\n if(sec<=9) disp+=\"0\"+sec;\n else disp+=sec;\n return(disp); }\n function Up() {\n  cmin1=0;\n csec1=0;\n min1=0+Minutes(document.getElementById('frm').beg1.value);\n sec1=0+Seconds(document.getElementById('frm').beg1.value);\n UpRepeat(); }\n function UpRepeat() {\n    csec1++;\n    if(csec1==60) { csec1=0; cmin1++; }\n        document.getElementById('frm').disp1.value=Display(cmin1,csec1);\n  up=setTimeout(\"UpRepeat()\",1000); }\n var lefttime=\"" + duration + "\";" + "\n function Down(lefttime) {" + "\n timeleftr=lefttime" + "\n countdown()" + "\n cmin2=1*Minutes(document.getElementById('frm').disp2.value);" + "\n  csec2=0+Seconds(document.getElementById('frm').disp2.value);" + "\n DownRepeat(); }" + "\n function countdown()" + "\n{" + "\n sb();" + "\n  counter();" + "\n }" + "\n function sb()" + "\n{" + "\n if (timeleftr>0)" + "\n {                   timeleft =timeleftr;" + "\n  defaultStatus=\"TIME LEFT   : \"+timeleft+\" MINUTES\";" + "\n setTimeout(\"sb()\",300);" + "\n}" + "\n else" + "\n {" + "\n  alert(\"The Time Alloted for this Test has Elapsed. Please Click on OK button to submit your Test.\")" + "\n submitPage1()" + "\n }" + "\n  }" + "\n function counter()" + "\n{" + "\n timeleftr=timeleftr - 0.25;" + "\n setTimeout(\"counter()\",15000);" + "\n }" + "\n function DownRepeat() {" + "\n csec2--;" + "\n if(csec2==-1) { csec2=59; cmin2--; }" + "\n document.getElementById('frm').disp2.value=Display(cmin2,csec2);" + "\n  if((cmin2==0)&&(csec2==0)) document.getElementById('frm').disp2.value=Display(cmin2,csec2);" + "\n else" + "\n down=setTimeout(\"DownRepeat()\",1000); }" + "\n  //]]>";
        final Script javaScript4 = new Script().setLanguage("javascript").addElement(javaScript1);
        final Script javaScript5 = new Script().setLanguage("javascript").addElement(javaScript2);
        final Script javaScript6 = new Script().setLanguage("javascript").addElement(javaScript3);
        final Script script2 = new Script().setLanguage("javascript").addElement(scriptfortime);
        head1.addElement((Element)title1);
        head1.addElement((Element)csslink);
        if (refreshassessment != null && !refreshassessment.equals("") && duration > 1) {
            head1.addElement(refreshassessment);
        }
        head1.addElement((Element)script1);
        head1.addElement((Element)script2);
        form1.addElement((Element)l2);
        form1.addElement((Element)table2);
        form1.addElement((Element)table4);
        form1.addElement((Element)table5);
        form1.addElement((Element)javaScript5);
        form1.addElement((Element)javaScript6);
        body1.addElement((Element)form1);
        html1.addElement((Element)head1);
        html1.addElement((Element)body1);
        final String abcd = html1.toString();
        return abcd;
    }
    
    static {
        log = new SimpleLogger((Class)StAloneTestHtmlStructure.class, true);
    }
}
