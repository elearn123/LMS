package learnityasmtserver.assessmentportal.standaloneasmt;

import org.grlea.log.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.*;
import learnityasmtserver.assessmentportal.dbconnection.*;
import org.apache.ecs.*;
import org.apache.ecs.html.*;
import java.util.*;

public class StResultSectionHTML extends HttpServlet
{
    public static final SimpleLogger log;
    private static final String SESSION_MODEL_ANS = "SESSION_MODEL_ANS";
    private static final String SESSION_MODEL_PER_PAGE = "SESSION_MODEL_PER_PAGE";
    private static final String SESSION_MODEL_QUESTIONS = "SESSION_MODEL_QUESTIONS";
    private static final String SESSION_MODEL_PROGRESS_QUESTIONS = "SESSION_MODEL_PROGRESS_QUESTIONSS";
    private static final String SESSION_MODEL_TIME = "SESSION_MODEL_TIME";
    private static final String SESSION_TEMPANS = "SESSION_TEMPANS";
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final HttpSession mysession = request.getSession(true);
        final String student_id = (String)mysession.getAttribute("student_id");
        StResultSectionHTML.log.debug("student_id=doget=" + student_id);
        if (student_id != null) {
            this.getResult(request, response, out, student_id);
        }
    }
    
    public void getResult(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out, final String student_id) throws IOException, ServletException {
        final HttpSession mysession = request.getSession(true);
        final String total_ques = request.getParameter("total_ques");
        final String model_id = request.getParameter("assessment_id1");
        double totalScore = 0.0;
        int no_correctAns = 0;
        int no_incorrectAns = 0;
        double Marks = 0.0;
        int iScore = 0;
        int q_attempted = 0;
        final ReportDataBaseLayer ob1 = new ReportDataBaseLayer();
        Vector vSessionQuestions = new Vector();
        vSessionQuestions = (Vector)mysession.getAttribute("SESSION_MODEL_QUESTIONS");
        Vector vSessionQuestions2 = new Vector();
        vSessionQuestions2 = (Vector)mysession.getAttribute("SESSION_MODEL_PROGRESS_QUESTIONSS");
        Vector allobj = new Vector();
        if (vSessionQuestions == null) {
            allobj = vSessionQuestions2;
        }
        else {
            allobj = vSessionQuestions;
        }
        Vector v = null;
        if (allobj == null) {
            v = new Vector();
        }
        else {
            v = allobj;
        }
        mysession.removeAttribute("SESSION_MODEL_QUESTIONS");
        final Vector vTempAns = (Vector)mysession.getAttribute("SESSION_TEMPANS");
        mysession.removeAttribute("SESSION_TEMPANS");
        final int pass_marks = ob1.gPassMarks(model_id);
        final String total_no_of_question_retrived = request.getParameter("total_no_of_question_retrived");
        final String ques_per_page = request.getParameter("ques_per_page");
        final String total_time = request.getParameter("duration");
        final int no_question_available = Integer.parseInt(total_no_of_question_retrived);
        final String model_test_name = request.getParameter("AssessmentTitle");
        int No_Ques = 0;
        int no_question_per_page = 0;
        int Total_No_Ques = 0;
        if (total_ques != null && !total_ques.equals("")) {
            Total_No_Ques = Integer.parseInt(total_ques);
        }
        if (total_no_of_question_retrived != null && !total_no_of_question_retrived.equals("")) {
            No_Ques = Integer.parseInt(total_no_of_question_retrived);
        }
        if (ques_per_page != null && !ques_per_page.equals("")) {
            no_question_per_page = Integer.parseInt(ques_per_page);
        }
        else {
            no_question_per_page = No_Ques;
        }
        final Calendar calendar = new GregorianCalendar();
        final Date trialTime = new Date();
        calendar.setTime(trialTime);
        final String[] months = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        final Random rnd = new Random();
        int rand = rnd.nextInt();
        if (rand < 1) {
            rand *= -1;
        }
        final String confirmation_id = "" + rand;
        final String student_name = ob1.studentName(student_id);
        final String strTime = calendar.get(10) + ":" + calendar.get(12) + ":" + calendar.get(13);
        final String strDate = months[calendar.get(2)] + " " + calendar.get(5) + ", " + calendar.get(1);
        Object o = mysession.getAttribute("SESSION_MODEL_ANS");
        Vector vAns = null;
        if (o == null) {
            vAns = new Vector();
        }
        else {
            vAns = (Vector)o;
        }
        if (vTempAns != null) {
            vAns.addElement(vTempAns.elementAt(0));
        }
        final Vector vA = new Vector();
        final Vector page_questions = allobj;
        for (int i = 0; i < page_questions.size(); ++i) {
            final Vector questionMatter = (Vector) page_questions.elementAt(i);
            final Vector res = (Vector) questionMatter.elementAt(0);
            final Vector question = (Vector) questionMatter.elementAt(1);
            final Integer iTitle = (Integer) res.elementAt(0);
            String strQuesNo = "";
            String str = "";
            switch (iTitle) {
                case 0: {
                    strQuesNo = (String) question.elementAt(0);
                    str = request.getParameter(strQuesNo);
                    vA.addElement(str);
                    break;
                }
                case 1: {
                    strQuesNo = (String) question.elementAt(0);
                    final String[] str2 = request.getParameterValues(strQuesNo);
                    vA.addElement(str2);
                    break;
                }
                case 2: {
                    strQuesNo = (String) question.elementAt(0);
                    str = request.getParameter(strQuesNo);
                    vA.addElement(str);
                    break;
                }
                case 3: {
                    strQuesNo = (String) question.elementAt(0);
                    str = request.getParameter(strQuesNo);
                    vA.addElement(str);
                    break;
                }
                case 4: {
                    final String[] alp = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
                    final Vector vOption = (Vector) question.elementAt(2);
                    final Vector leftoption = (Vector) vOption.elementAt(0);
                    strQuesNo = (String) question.elementAt(0);
                    final Vector vmq = new Vector();
                    final String[] str3 = new String[leftoption.size()];
                    final String[] str4 = new String[leftoption.size()];
                    for (int i2 = 0; i2 < leftoption.size(); ++i2) {
                        final String noalp = alp[i2];
                        final String type1 = "l" + strQuesNo + noalp;
                        final String type2 = "r" + strQuesNo + noalp;
                        str3[i2] = request.getParameter(type1);
                        str4[i2] = request.getParameter(type2);
                    }
                    vmq.addElement(str3);
                    vmq.addElement(str4);
                    vA.addElement(vmq);
                    break;
                }
            }
        }
        vAns.addElement(vA);
        mysession.setAttribute("SESSION_MODEL_ANS", (Object)vAns);
        o = mysession.getAttribute("SESSION_MODEL_ANS");
        final String javaScript = "\n function Quit_onclick() {\n \t\tif( confirm(\"Do you wish to quit?\") ) {\n \t\t\twindow.close(); \n \t\t}\n \t}\n function print_onclick() {\n \t\twindow.print();\n \t}";
        final Html html1 = new Html();
        final Head head1 = new Head();
        final Form form = new Form().setName("frm").setMethod("post");
        final Link link = new Link();
        final Link link2 = new Link();
        final Script script1 = new Script();
        final Script script2 = new Script();
        link.setHref("../coreadmin/js/Assessmentportal.css");
        link2.setHref("../coreadmin/js/calendar1.js");
        link.setRel("stylesheet");
        link.addElement((Element)script1);
        final Font font1 = new Font();
        final Font font2 = new Font().setSize(4).setColor("#CC0000");
        final Font font3 = new Font();
        font1.addElement((Element)new Em().addElement("Powered BY &nbsp;&nbsp;"));
        font2.addElement("LearnITy&#8482;&nbsp;&nbsp;");
        font3.addElement((Element)new Em().addElement("Assessment Server"));
        head1.addElement((Element)new Script().setLanguage("JavaScript").addElement(javaScript));
        final Input inputButton11 = new Input();
        final Input inputButton2 = new Input();
        final Div maindiv = new Div();
        maindiv.setID("maindiv");
        form.addElement((Element)maindiv);
        maindiv.addElement((Element)new NOBR().addElement(new Div().addElement(inputButton11.setName("Print").setType("button").setValue("Print").setClass("sbutton")).setID("printbuttonTestPAge")).addElement(new Div().addElement(inputButton2.setName("Quit").setType("button").setValue("Quit").setClass("sbutton")).setID("QuitbuttonTestPAge")).addElement((Element)new BR()));
        final Body body1 = new Body();
        final Div divtop1 = new Div();
        final Div divtop2 = new Div();
        final Div divtop3 = new Div();
        maindiv.addElement(divtop1.addElement((Element)new Center().addElement((Element)font1).addElement((Element)font2).addElement((Element)font3)).setID("divtop1")).addElement(divtop2.addElement("Result Section").setID("divtop2")).addElement(new IMG().setSrc("../coreadmin/images/logo.gif").setClass("organistaionlogoresult"));
        final Div headerdiv1 = new Div();
        final Div headerdiv2 = new Div();
        final Div headerdiv3 = new Div();
        final Div headerdiv4 = new Div();
        final Div headerdiv5 = new Div();
        final Div headerdiv6 = new Div();
        maindiv.addElement(divtop3.setID("divtop3"));
        divtop3.addElement(headerdiv1.addElement("<B>User Id : </B>").addElement(student_id).setID("headerdiv1")).addElement(headerdiv2.addElement("<B>User Name  : </B>").addElement(student_name).setID("headerdiv2")).addElement(headerdiv3.addElement("<B>Confirmation no. : </B>").addElement("" + rand).setID("headerdiv3")).addElement(headerdiv4.addElement("<B>Assessment Title : </B>").addElement(model_test_name).setID("headerdiv4")).addElement(headerdiv5.addElement("<B>Date : </B>").addElement(strDate).setID("headerdiv5")).addElement(headerdiv6.addElement("<B>Time : </B> ").addElement(strTime).setID("headerdiv6"));
        final Div testsummaryheading = new Div();
        final Div testsummary = new Div();
        final Div testsummary2 = new Div();
        final Div testsummary3 = new Div();
        final Div testsummary4 = new Div();
        final Div testsummary5 = new Div();
        final Div testsummary6 = new Div();
        final Div testsummary7 = new Div();
        final Div testsummary8 = new Div();
        final Div testsummary9 = new Div();
        final Div testsummary10 = new Div();
        final Div testsummary11 = new Div();
        maindiv.addElement(testsummaryheading.addElement("Test Summary").setID("testsummaryheading")).addElement(testsummary.setID("testsummary"));
        final Div itembody = new Div();
        int no_of_ques_attempted = 0;
        final String time_started = (String)mysession.getAttribute("SESSION_MODEL_TIME");
        final String test_id = ob1.getTestID(student_id, model_id);
        ob1.modifyTestDetails(student_id, test_id, strTime);
        ob1.updateAssessmentProgressStatus(student_id, model_id, strDate, strTime, test_id);
        Vector vAllAns = null;
        if (o == null) {
            vAllAns = new Vector();
        }
        else {
            vAllAns = (Vector)o;
        }
        for (int j = 0; j < vAllAns.size(); ++j) {
            Vector vAs = null;
            vAs = this.getResponses(vAllAns);
            final Vector vAnsIdText = new Vector();
            String ans_text_fib = "";
            String ans_mq = "";
            for (int k = 0; k < v.size(); ++k) {
                final Vector vQues = (Vector) v.elementAt(k);
                final Vector vType = (Vector) vQues.elementAt(0);
                final Vector vQuesOptions = (Vector) vQues.elementAt(1);
                final Vector vOptions = (Vector) vQuesOptions.elementAt(2);
                final Integer case_type = (Integer) vType.elementAt(0);
                if (case_type != 4) {
                    if (case_type != 1) {
                        final Vector vAnsText = new Vector();
                        if (case_type != 2) {
                            String ans_id = (String) vAs.elementAt(k);
                            if (ans_id == null) {
                                ans_id = "";
                            }
                            if (ans_id.equals("") || ans_id.equals("null")) {
                                vAnsText.addElement(ans_id);
                                vAnsText.addElement(ans_id);
                                vAnsIdText.addElement(vAnsText);
                            }
                            else {
                                for (int m = 0; m < vOptions.size(); ++m) {
                                    final Vector vOptions2 = (Vector) vOptions.elementAt(m);
                                    final Character ans_id2 = (Character) vOptions2.elementAt(0);
                                    final String ans_id1_str = ans_id2.toString();
                                    if (ans_id.equals(ans_id1_str)) {
                                        final String ans_txt = (String) vOptions2.elementAt(1);
                                        vAnsText.addElement(ans_id2);
                                        vAnsText.addElement(ans_txt);
                                        vAnsIdText.addElement(vAnsText);
                                    }
                                }
                            }
                        }
                        else {
                            ans_text_fib = (String) vAs.elementAt(k);
                            if (!ans_text_fib.equals("")) {
                                vAnsText.addElement(ans_text_fib);
                                vAnsText.addElement(ans_text_fib);
                                vAnsIdText.addElement(vAnsText);
                            }
                            else {
                                ans_text_fib = "";
                                vAnsText.addElement(ans_text_fib);
                                vAnsText.addElement(ans_text_fib);
                                vAnsIdText.addElement(vAnsText);
                            }
                        }
                    }
                    else {
                        String ans_id3 = null;
                        final String[] AnsStr = (String[]) vAs.elementAt(k);
                        if (AnsStr != null) {
                            final Vector vAnsText2 = new Vector();
                            for (int r = 0; r < AnsStr.length; ++r) {
                                ans_id3 = AnsStr[r];
                                final Vector vAnsText3 = new Vector();
                                for (int l = 0; l < vOptions.size(); ++l) {
                                    final Vector vOptions3 = (Vector) vOptions.elementAt(l);
                                    final Character ans_id4 = (Character) vOptions3.elementAt(0);
                                    final String ans_id1_str2 = ans_id4.toString();
                                    if (ans_id3.equals(ans_id1_str2)) {
                                        final String ans_txt2 = (String) vOptions3.elementAt(1);
                                        vAnsText2.addElement(ans_txt2);
                                    }
                                }
                            }
                            vAnsIdText.addElement(vAnsText2);
                        }
                        else {
                            final Vector vAnsText4 = new Vector();
                            final Vector vAnsText5 = new Vector();
                            vAnsText4.addElement(ans_id3);
                            vAnsText4.addElement(ans_id3);
                            vAnsText5.addElement(vAnsText4);
                            vAnsIdText.addElement(vAnsText5);
                        }
                    }
                }
                else {
                    final Vector vMqGivenAns = (Vector) vAs.elementAt(k);
                    StResultSectionHTML.log.debug("==vMqGivenAns============" + vMqGivenAns);
                    final String[] leftpAns11 = (String[]) vMqGivenAns.elementAt(0);
                    final String[] rightAns11 = (String[]) vMqGivenAns.elementAt(1);
                    for (int r2 = 0; r2 < leftpAns11.length; ++r2) {
                        StResultSectionHTML.log.debug("==ans_mq=====1=======" + ans_mq);
                        StResultSectionHTML.log.debug("==leftpAns11[r1]============" + leftpAns11[r2]);
                        StResultSectionHTML.log.debug("==rightAns11[r1]============" + rightAns11[r2]);
                        ans_mq = ans_mq + "\n " + leftpAns11[r2] + " - " + rightAns11[r2] + ",";
                    }
                    StResultSectionHTML.log.debug("==ans_mq====2========" + ans_mq);
                }
            }
            int count = 0;
            final int total_questions = v.size();
            final int total_responses = vAs.size();
            final int m2 = total_questions - total_responses;
            if (total_questions > total_responses) {
                for (int k2 = 0; k2 < m2; ++k2) {
                    vAs.addElement("");
                }
            }
            for (int j2 = 0; j2 < v.size(); ++j2) {
                final Vector questionMatter2 = (Vector) v.elementAt(j2);
                final Vector res2 = (Vector) questionMatter2.elementAt(0);
                final Vector question2 = (Vector) questionMatter2.elementAt(1);
                final Integer iTitle2 = (Integer) res2.elementAt(0);
                String item_response = "";
                Div div = null;
                final int k3 = j2 + 1;
                ++count;
                final Integer case_type2 = (Integer) res2.elementAt(0);
                Vector vAnswer = new Vector();
                String ans_text = "";
                if (case_type2 != 1) {
                    if (case_type2 != 2) {
                        vAnswer = (Vector) vAnsIdText.elementAt(j2);
                        ans_text = (String) vAnswer.elementAt(1);
                    }
                    else {
                        vAnswer = (Vector) vAnsIdText.elementAt(j2);
                        ans_text = (String) vAnswer.elementAt(1);
                    }
                }
                else {
                    final String[] item_responses = (String[]) vAs.elementAt(j2);
                    if (item_responses != null) {
                        vAnswer = (Vector) vAnsIdText.elementAt(j2);
                        final String[] ans_texts = new String[vAnswer.size()];
                        for (int l2 = 0; l2 < vAnswer.size(); ++l2) {
                            ans_texts[l2] = (String) vAnswer.elementAt(l2);
                        }
                        ans_text = this.convert(ans_texts);
                    }
                    else {
                        ans_text = "";
                    }
                }
                switch (iTitle2) {
                    case 0: {
                        item_response = (String) vAs.elementAt(j2);
                        iScore = Integer.parseInt((String) res2.elementAt(1));
                        Marks += iScore;
                        String srtNegscore = "";
                        if (res2.size() > 3) {
                            srtNegscore = (String) res2.elementAt(3);
                        }
                        final String strCorrectAns = (String) question2.elementAt(3);
                        if (item_response != null) {
                            if (!item_response.equals("")) {
                                ++q_attempted;
                                if (strCorrectAns.equals(item_response)) {
                                    totalScore += iScore;
                                    ++no_correctAns;
                                }
                                else {
                                    ++no_incorrectAns;
                                    if (!srtNegscore.equals("")) {
                                        final double intNegscore = Double.parseDouble(srtNegscore);
                                        totalScore += intNegscore;
                                    }
                                }
                            }
                        }
                        div = this.MultipleChoiceHTML(res2, question2, item_response, ans_text, k3, student_id, strTime, test_id, totalScore, no_correctAns, no_incorrectAns, Marks);
                        itembody.addElement((Element)div);
                        break;
                    }
                    case 1: {
                        final String[] item_responses2 = (String[]) vAs.elementAt(j2);
                        iScore = Integer.parseInt((String) res2.elementAt(1));
                        Marks += iScore;
                        String srtNegscore = "";
                        if (res2.size() > 3) {
                            srtNegscore = (String) res2.elementAt(3);
                        }
                        final Vector vCorrectAns = (Vector) question2.elementAt(3);
                        boolean b = false;
                        boolean c = false;
                        final Vector vItemResponses = new Vector();
                        if (item_responses2 != null) {
                            ++q_attempted;
                            for (int j3 = 0; j3 < item_responses2.length; ++j3) {
                                vItemResponses.addElement(item_responses2[j3]);
                            }
                            if (vItemResponses.containsAll(vCorrectAns) && vCorrectAns.size() == vItemResponses.size()) {
                                b = true;
                            }
                        }
                        else {
                            c = true;
                        }
                        if (b) {
                            ++no_correctAns;
                            totalScore += iScore;
                        }
                        else if (!c) {
                            ++no_incorrectAns;
                            if (!srtNegscore.equals("")) {
                                final double intNegscore2 = Double.parseDouble(srtNegscore);
                                totalScore += intNegscore2;
                            }
                        }
                        div = this.MultipleResponseHTML(res2, question2, item_responses2, ans_text, k3, student_id, strTime, test_id, totalScore, no_correctAns, no_incorrectAns, Marks);
                        itembody.addElement((Element)div);
                        break;
                    }
                    case 2: {
                        item_response = (String) vAs.elementAt(j2);
                        iScore = Integer.parseInt((String) res2.elementAt(1));
                        Marks += iScore;
                        String srtNegscore = "";
                        if (res2.size() > 3) {
                            srtNegscore = (String) res2.elementAt(3);
                        }
                        final Vector multiAns = (Vector) question2.elementAt(2);
                        final String strCorrectAns = (String) multiAns.elementAt(0);
                        if (item_response != null) {
                            if (!item_response.equals("")) {
                                ++q_attempted;
                                if (strCorrectAns.equals(item_response)) {
                                    totalScore += iScore;
                                    ++no_correctAns;
                                }
                                else {
                                    ++no_incorrectAns;
                                    if (!srtNegscore.equals("")) {
                                        final double intNegscore3 = Double.parseDouble(srtNegscore);
                                        totalScore += intNegscore3;
                                    }
                                }
                            }
                        }
                        div = this.FillBlanks(res2, question2, item_response, ans_text, k3, student_id, strTime, test_id, totalScore, no_correctAns, no_incorrectAns, Marks);
                        itembody.addElement((Element)div);
                        break;
                    }
                    case 3: {
                        item_response = (String) vAs.elementAt(j2);
                        iScore = Integer.parseInt((String) res2.elementAt(1));
                        Marks += iScore;
                        String srtNegscore = "";
                        if (res2.size() > 3) {
                            srtNegscore = (String) res2.elementAt(3);
                        }
                        final String strCorrectAns = (String) question2.elementAt(3);
                        if (item_response != null) {
                            if (!item_response.equals("")) {
                                ++q_attempted;
                                if (strCorrectAns.equals(item_response)) {
                                    totalScore += iScore;
                                    ++no_correctAns;
                                }
                                else {
                                    ++no_incorrectAns;
                                    if (!srtNegscore.equals("")) {
                                        final double intNegscore3 = Double.parseDouble(srtNegscore);
                                        totalScore += intNegscore3;
                                    }
                                }
                            }
                        }
                        div = this.MultipleChoiceHTML(res2, question2, item_response, ans_text, k3, student_id, strTime, test_id, totalScore, no_correctAns, no_incorrectAns, Marks);
                        itembody.addElement((Element)div);
                        break;
                    }
                    case 4: {
                        final Vector vMqGivenAns2 = (Vector) vAs.elementAt(j2);
                        StResultSectionHTML.log.debug("==vMqGivenAns============" + vMqGivenAns2);
                        final String[] leftpAns2 = (String[]) vMqGivenAns2.elementAt(0);
                        final String[] rightAns2 = (String[]) vMqGivenAns2.elementAt(1);
                        iScore = Integer.parseInt((String) res2.elementAt(1));
                        StResultSectionHTML.log.debug("===iScore======" + iScore);
                        Marks += iScore;
                        String srtNegscore = "";
                        if (res2.size() > 3) {
                            srtNegscore = (String) res2.elementAt(3);
                            StResultSectionHTML.log.debug("===srtNegscore======" + srtNegscore);
                        }
                        final Vector vCorrectAnsmq = (Vector) question2.elementAt(3);
                        StResultSectionHTML.log.debug("===vCorrectAnsmq======" + vCorrectAnsmq);
                        boolean b2 = true;
                        boolean c2 = false;
                        for (int r3 = 0; r3 < leftpAns2.length; ++r3) {
                            StResultSectionHTML.log.debug("==leftpAns11[r1]============" + leftpAns2[r3]);
                            StResultSectionHTML.log.debug("==rightAns11[r1]============" + rightAns2[r3]);
                            if (!leftpAns2[r3].equals("0")) {
                                c2 = true;
                            }
                            if (!rightAns2[r3].equals("0")) {
                                c2 = true;
                            }
                            for (int r4 = 0; r4 < leftpAns2.length; ++r4) {
                                final String[] coAns11 = (String[]) vCorrectAnsmq.elementAt(r4);
                                StResultSectionHTML.log.debug("==coAns11[0]============" + coAns11[0]);
                                StResultSectionHTML.log.debug("==coAns11[1]============" + coAns11[1]);
                                if (leftpAns2[r3].equals(coAns11[0])) {
                                    b2 = rightAns2[r3].equals(coAns11[1]);
                                }
                            }
                            if (!b2) {
                                break;
                            }
                            StResultSectionHTML.log.debug("==========b1=================" + b2);
                        }
                        if (c2) {
                            ++q_attempted;
                            if (b2) {
                                ++no_correctAns;
                                totalScore += iScore;
                            }
                            else {
                                ++no_incorrectAns;
                                if (!srtNegscore.equals("")) {
                                    final double intNegscore4 = Double.parseDouble(srtNegscore);
                                    totalScore += intNegscore4;
                                }
                            }
                        }
                        div = this.MatchingQuesHTML(res2, question2, vMqGivenAns2, ans_mq, k3, student_id, strTime, test_id, totalScore, no_correctAns, no_incorrectAns, Marks);
                        itembody.addElement((Element)div);
                        break;
                    }
                }
            }
            if (count == No_Ques) {
                vAs.removeAllElements();
                break;
            }
        }
        vAllAns.removeAllElements();
        v.removeAllElements();
        vAns.removeAllElements();
        vA.removeAllElements();
        mysession.removeAttribute("SESSION_MODEL_ANS");
        no_of_ques_attempted = no_correctAns + no_incorrectAns;
        mysession.removeAttribute("SESSION_MODEL_TIME");
        mysession.removeAttribute("SESSION_MODEL_PER_PAGE");
        String status = "";
        if (totalScore >= pass_marks) {
            status = "Passed";
        }
        else {
            status = "Failed";
        }
        ob1.updateTestDetails(student_id, test_id, confirmation_id, q_attempted, no_correctAns, Marks, totalScore, status);
        testsummary.addElement(testsummary2.addElement("<B>Total No. of questions  : </B> ").setID("testsummary1")).addElement(testsummary3.addElement("<B>No. of questions attempted  : </B>").setID("testsummary2")).addElement(testsummary4.addElement("<B>No. of correct answers   : </B>").setID("testsummary3")).addElement(testsummary5.addElement("<B>Full Marks   : </B>").setID("testsummary4")).addElement(testsummary6.addElement("<B>Marks Obtained   : </B>").setID("testsummary5")).addElement(testsummary7.addElement("" + total_no_of_question_retrived).setID("testsummary6")).addElement(testsummary8.addElement("" + q_attempted).setID("testsummary7")).addElement(testsummary9.addElement("" + no_correctAns).setID("testsummary8")).addElement(testsummary10.addElement("" + Marks).setID("testsummary9")).addElement(testsummary11.addElement("" + totalScore).setID("testsummary10"));
        no_of_ques_attempted = 0;
        maindiv.addElement((Element)new Div().addElement((Element)new BR()));
        maindiv.addElement((Element)new Div().addElement((Element)new BR()));
        maindiv.addElement((Element)new Div().addElement((Element)new BR()));
        maindiv.addElement(new Div().addElement("Item Summary").addElement(new Div().addElement(new Div().addElement("SL No.").setID("ItemId")).addElement(new Div().addElement("Item Text").setID("ItemText")).addElement(new Div().addElement("Item Answer").setID("ItemAnswer")).addElement(new Div().addElement("Answer Given").setID("AunswerGiven")).addElement(new Div().addElement("Item Marks").setID("ItemMarks")).addElement(new Div().addElement("Marks Obtained").setID("ItemObtainedMarks")).addElement(new Div().addElement("Feedback").setID("Feedback")).setID("ItemSummaryHeading")).addElement(itembody.setID("ItemSummaryRowBody")).setID("ItemSummaryBody"));
        inputButton11.setOnClick("print_onclick();");
        inputButton2.setOnClick("Quit_onclick();");
        head1.addElement((Element)script2);
        head1.addElement((Element)link);
        head1.addElement((Element)link2);
        html1.addElement((Element)head1);
        body1.addElement((Element)form);
        html1.addElement((Element)body1);
        out.println(html1.toString());
    }
    
    public Div MultipleChoiceHTML(final Vector response, final Vector question, String item_response, final String ans_text, final int no, final String student_id, final String strTime, final String test_id, Double totalScore, int no_correctAns, int no_incorrectAns, Double Marks) {
        final Div div1 = new Div();
        div1.setID("ItemSummaryRow");
        final String strQuesId = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector multiAns = (Vector)question.elementAt(2);
        String strCorrectAns = (String) question.elementAt(3);
        final Vector vFeedback = (Vector) response.elementAt(2);
        String strCorrectFeedback = "";
        String strIncorrectFeedback = "";
        if (vFeedback != null) {
            for (int i = 0; i < vFeedback.size(); ++i) {
                final Vector vFeedbackItem = (Vector) vFeedback.elementAt(i);
                if (vFeedbackItem != null) {
                    final String strFeedback = (String) vFeedbackItem.elementAt(0);
                    final String strFor = (String) vFeedbackItem.elementAt(1);
                    final String strView = (String) vFeedbackItem.elementAt(2);
                    if (strFor.equals("Correct") && strView.equals("Candidate")) {
                        strCorrectFeedback = strFeedback;
                    }
                    if (strFor.equals("Incorrect") && strView.equals("Candidate")) {
                        strIncorrectFeedback = strFeedback;
                    }
                }
            }
        }
        final String cScore = (String)response.elementAt(1);
        final int iScore = Integer.parseInt((String)response.elementAt(1));
        Marks += new Double(iScore);
        String srtNegscore = "";
        if (response.size() > 3) {
            srtNegscore = (String)response.elementAt(3);
        }
        String strFeedBack11 = "";
        String strCorrectIncorrect = "";
        String strScore = "";
        double item_score = 0.0;
        if (item_response == null) {
            item_response = "";
        }
        if (item_response.equals("null") || item_response.equals("")) {
            strFeedBack11 = "Unattempted";
            strScore += "X";
        }
        else if (strCorrectAns.equals(item_response)) {
            strFeedBack11 = strCorrectFeedback;
            strCorrectIncorrect = "Correct";
            strScore += cScore;
            totalScore += new Double(iScore);
            item_score = iScore;
            ++no_correctAns;
        }
        else {
            strFeedBack11 = strIncorrectFeedback;
            strCorrectIncorrect = "Incorrect";
            ++no_incorrectAns;
            if (!srtNegscore.equals("")) {
                final double intNegscore = Double.parseDouble(srtNegscore);
                totalScore += intNegscore;
                strScore += srtNegscore;
                item_score = intNegscore;
            }
            else {
                strScore += "X";
            }
        }
        for (int j = 0; j < multiAns.size(); ++j) {
            final Vector label = (Vector) multiAns.elementAt(j);
            final Character cLabel = (Character) label.elementAt(0);
            final String strAns = (String) label.lastElement();
            if (strCorrectAns.equals(cLabel.toString())) {
                strCorrectAns = strAns;
                break;
            }
        }
        final ReportDataBaseLayer ob1 = new ReportDataBaseLayer();
        ob1.addDetailsQues(student_id, test_id, strTime, strQuesId, strQues, strCorrectAns, item_response, ans_text, strFeedBack11, cScore, item_score);
        div1.addElement(new Div().addElement("" + no).setID("ItemId1")).addElement(new Div().addElement(strQues).setID("ItemText1")).addElement(new Div().addElement(strCorrectAns).setID("ItemAnswer1")).addElement(new Div().addElement(ans_text).setID("AunswerGiven1")).addElement(new Div().addElement(cScore).setID("ItemMarks1")).addElement(new Div().addElement(strScore).setID("ItemObtainedMarks1")).addElement(new Div().addElement(strFeedBack11).setID("Feedback1"));
        return div1;
    }
    
    public Div FillBlanks(final Vector response, final Vector question, final String item_response, final String ans_text, final int no, final String student_id, final String strTime, final String test_id, Double totalScore, int no_correctAns, int no_incorrectAns, Double Marks) {
        final Div div1 = new Div();
        div1.setID("ItemSummaryRow");
        final StringBuffer strHTML = new StringBuffer();
        final String strQuesId = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector multiAns = (Vector)question.elementAt(2);
        final String strCorrectAns = (String) multiAns.elementAt(0);
        String strCase = "";
        if (multiAns.size() >= 2) {
            strCase = (String) multiAns.elementAt(2);
        }
        final Vector vFeedback = (Vector) response.elementAt(2);
        String strCorrectFeedback = "";
        String strIncorrectFeedback = "";
        if (vFeedback != null) {
            for (int i = 0; i < vFeedback.size(); ++i) {
                final Vector vFeedbackItem = (Vector) vFeedback.elementAt(i);
                if (vFeedbackItem != null) {
                    final String strFeedback = (String) vFeedbackItem.elementAt(0);
                    final String strFor = (String) vFeedbackItem.elementAt(1);
                    final String strView = (String) vFeedbackItem.elementAt(2);
                    if (strFor.equals("Correct") && strView.equals("Candidate")) {
                        strCorrectFeedback = strFeedback;
                    }
                    if (strFor.equals("Incorrect") && strView.equals("Candidate")) {
                        strIncorrectFeedback = strFeedback;
                    }
                }
            }
        }
        final String cScore = (String)response.elementAt(1);
        final int iScore = Integer.parseInt((String)response.elementAt(1));
        Marks += new Double(iScore);;
        String srtNegscore = "";
        if (response.size() > 3) {
            srtNegscore = (String)response.elementAt(3);
        }
        String strCorrectIncorrect = "";
        String strFeedBack11 = "";
        String strScore = "";
        double item_score = 0.0;
        if (strCase.equalsIgnoreCase("No")) {
            if (item_response == null || item_response.equals("")) {
                strFeedBack11 = "Unattempted";
                strScore += "X";
            }
            else if (item_response.trim().equalsIgnoreCase(strCorrectAns.trim())) {
                strFeedBack11 = strCorrectFeedback;
                strCorrectIncorrect = "Correct";
                strScore += cScore;
                totalScore += new Double(iScore);;
                item_score = iScore;
                ++no_correctAns;
            }
            else {
                strFeedBack11 = strIncorrectFeedback;
                strCorrectIncorrect = "Incorrect";
                ++no_incorrectAns;
                if (!srtNegscore.equals("")) {
                    final double intNegscore = Double.parseDouble(srtNegscore);
                    totalScore += intNegscore;
                    strScore += srtNegscore;
                    item_score = intNegscore;
                }
                else {
                    strScore += "X";
                }
            }
        }
        else if (item_response == null || item_response.equals("")) {
            strFeedBack11 = "Unattempted";
            strScore += "X";
        }
        else if (strCorrectAns.equals(item_response)) {
            strFeedBack11 = strCorrectFeedback;
            strCorrectIncorrect = "Correct";
            strScore += cScore;
            totalScore += new Double(iScore);;
            item_score = iScore;
            ++no_correctAns;
        }
        else {
            strFeedBack11 = strIncorrectFeedback;
            strCorrectIncorrect = "Incorrect";
            ++no_incorrectAns;
            if (!srtNegscore.equals("")) {
                final double intNegscore = Double.parseDouble(srtNegscore);
                totalScore += intNegscore;
                strScore += srtNegscore;
                item_score = intNegscore;
            }
            else {
                strScore += "X";
            }
        }
        final ReportDataBaseLayer ob1 = new ReportDataBaseLayer();
        ob1.addDetailsQues(student_id, test_id, strTime, strQuesId, strQues, strCorrectAns, item_response, ans_text, strFeedBack11, cScore, item_score);
        div1.addElement(new Div().addElement("" + no).setID("ItemId1")).addElement(new Div().addElement(strQues).setID("ItemText1")).addElement(new Div().addElement(strCorrectAns).setID("ItemAnswer1")).addElement(new Div().addElement(ans_text).setID("AunswerGiven1")).addElement(new Div().addElement(cScore).setID("ItemMarks1")).addElement(new Div().addElement(strScore).setID("ItemObtainedMarks1")).addElement(new Div().addElement(strFeedBack11).setID("Feedback1"));
        return div1;
    }
    
    public Div MultipleResponseHTML(final Vector response, final Vector question, final String[] item_responses, final String ans_text, final int no, final String student_id, final String strTime, final String test_id, Double totalScore, int no_correctAns, int no_incorrectAns, Double Marks) {
        final Div div1 = new Div();
        div1.setID("ItemSummaryRow");
        final String strQuesId = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector multiAns = (Vector)question.elementAt(2);
        final Vector vCorrectAns = (Vector)question.elementAt(3);
        final Vector vFeedback = (Vector) response.elementAt(2);
        String strCorrectFeedback = "";
        String strIncorrectFeedback = "";
        String multipleItemResponse = "";
        if (vFeedback != null) {
            for (int i = 0; i < vFeedback.size(); ++i) {
                final Vector vFeedbackItem = (Vector) vFeedback.elementAt(i);
                if (vFeedbackItem != null) {
                    final String strFeedback = (String) vFeedbackItem.elementAt(0);
                    final String strFor = (String) vFeedbackItem.elementAt(1);
                    final String strView = (String) vFeedbackItem.elementAt(2);
                    if (strFor.equals("Correct") && strView.equals("Candidate")) {
                        strCorrectFeedback = strFeedback;
                    }
                    if (strFor.equals("Incorrect") && strView.equals("Candidate")) {
                        strIncorrectFeedback = strFeedback;
                    }
                }
            }
        }
        final String cScore = (String)response.elementAt(1);
        final int iScore = Integer.parseInt((String)response.elementAt(1));
        Marks += new Double(iScore);
        String srtNegscore = "";
        if (response.size() > 3) {
            srtNegscore = (String)response.elementAt(3);
        }
        String strFeedBack11 = "";
        String strCorrectIncorrect = "";
        String strScore = "";
        double item_score = 0.0;
        boolean b = false;
        boolean c = false;
        final Vector vItemResponses = new Vector();
        if (item_responses != null) {
            for (int j = 0; j < item_responses.length; ++j) {
                vItemResponses.addElement(item_responses[j]);
            }
            if (vItemResponses.containsAll(vCorrectAns) && vCorrectAns.size() == vItemResponses.size()) {
                b = true;
            }
        }
        else {
            strFeedBack11 = "Unattempted";
            strScore += "X";
            c = true;
        }
        if (b) {
            strFeedBack11 = strCorrectFeedback;
            strCorrectIncorrect = "Correct";
            ++no_correctAns;
            totalScore += new Double(iScore);
            item_score = iScore;
            strScore += cScore;
        }
        else if (!c) {
            strFeedBack11 = strIncorrectFeedback;
            strCorrectIncorrect = "Incorrect";
            ++no_incorrectAns;
            if (!srtNegscore.equals("")) {
                final double intNegscore = Double.parseDouble(srtNegscore);
                totalScore += intNegscore;
                item_score = intNegscore;
                strScore += srtNegscore;
            }
            else {
                strScore += "X";
            }
        }
        String strCorrectAns = "";
        for (int k = 0; k < vCorrectAns.size(); ++k) {
            String strLabel = "";
            final String strCorrectAns2 = (String) vCorrectAns.elementAt(k);
            for (int l = 0; l < multiAns.size(); ++l) {
                final Vector label = (Vector) multiAns.elementAt(l);
                final Character cLabel = (Character) label.elementAt(0);
                final String strAns = (String) label.elementAt(1);
                if (strCorrectAns2.equals(cLabel.toString())) {
                    strLabel = cLabel.toString();
                    strCorrectAns = strCorrectAns + strAns + ", \n";
                }
            }
        }
        if (item_responses != null && !item_responses.equals("")) {
            multipleItemResponse = this.convert(item_responses);
        }
        final ReportDataBaseLayer ob1 = new ReportDataBaseLayer();
        ob1.addDetailsQues(student_id, test_id, strTime, strQuesId, strQues, strCorrectAns, multipleItemResponse, ans_text, strFeedBack11, cScore, item_score);
        div1.addElement(new Div().addElement("" + no).setID("ItemId1")).addElement(new Div().addElement(strQues).setID("ItemText1")).addElement(new Div().addElement(strCorrectAns).setID("ItemAnswer1")).addElement(new Div().addElement(ans_text).setID("AunswerGiven1")).addElement(new Div().addElement(cScore).setID("ItemMarks1")).addElement(new Div().addElement(strScore).setID("ItemObtainedMarks1")).addElement(new Div().addElement(strFeedBack11).setID("Feedback1"));
        return div1;
    }
    
    public Div MatchingQuesHTML(final Vector response, final Vector question, final Vector item_responses, final String ans_text, final int no, final String student_id, final String strTime, final String test_id, Double totalScore, int no_correctAns, int no_incorrectAns, Double Marks) {
        final Div div1 = new Div();
        div1.setID("ItemSummaryRow");
        final String strQuesId = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector multiAns = (Vector)question.elementAt(2);
        final Vector vCorrectAns = (Vector)question.elementAt(3);
        final Vector vFeedback = (Vector) response.elementAt(2);
        String strCorrectFeedback = "";
        String strIncorrectFeedback = "";
        final String multipleItemResponse = "";
        if (vFeedback != null) {
            for (int i = 0; i < vFeedback.size(); ++i) {
                final Vector vFeedbackItem = (Vector) vFeedback.elementAt(i);
                if (vFeedbackItem != null) {
                    final String strFeedback = (String) vFeedbackItem.elementAt(0);
                    final String strFor = (String) vFeedbackItem.elementAt(1);
                    final String strView = (String) vFeedbackItem.elementAt(2);
                    if (strFor.equals("Correct") && strView.equals("Candidate")) {
                        strCorrectFeedback = strFeedback;
                    }
                    if (strFor.equals("Incorrect") && strView.equals("Candidate")) {
                        strIncorrectFeedback = strFeedback;
                    }
                }
            }
        }
        final String cScore = (String)response.elementAt(1);
        final int iScore = Integer.parseInt((String)response.elementAt(1));
        Marks += new Double(iScore);
        String srtNegscore = "";
        if (response.size() > 3) {
            srtNegscore = (String)response.elementAt(3);
        }
        String strFeedBack11 = "";
        String strCorrectIncorrect = "";
        String strScore = "";
        double item_score = 0.0;
        boolean b1 = false;
        boolean c1 = false;
        final String[] leftpAns11 = (String[]) item_responses.elementAt(0);
        final String[] rightAns11 = (String[]) item_responses.elementAt(1);
        for (int r1 = 0; r1 < leftpAns11.length; ++r1) {
            if (!leftpAns11[r1].equals("0")) {
                c1 = true;
            }
            if (!rightAns11[r1].equals("0")) {
                c1 = true;
            }
            for (int r2 = 0; r2 < vCorrectAns.size(); ++r2) {
                final String[] coAns11 = (String[]) vCorrectAns.elementAt(r2);
                if (leftpAns11[r1].equals(coAns11[0])) {
                    b1 = rightAns11[r1].equals(coAns11[1]);
                }
            }
            if (!b1) {
                break;
            }
        }
        if (c1) {
            if (b1) {
                strFeedBack11 = strCorrectFeedback;
                strCorrectIncorrect = "Correct";
                strScore += cScore;
                totalScore += new Double(iScore);
                item_score = iScore;
                ++no_correctAns;
            }
            else {
                strFeedBack11 = strIncorrectFeedback;
                strCorrectIncorrect = "Incorrect";
                ++no_incorrectAns;
                if (!srtNegscore.equals("")) {
                    final double intNegscore = Double.parseDouble(srtNegscore);
                    totalScore += intNegscore;
                    strScore += srtNegscore;
                    item_score = intNegscore;
                }
                else {
                    strScore += "X";
                }
            }
        }
        else {
            strFeedBack11 = "Unattempted";
            strScore += "X";
        }
        String strCorrectAns = "";
        for (int r3 = 0; r3 < vCorrectAns.size(); ++r3) {
            final String[] a = (String[]) vCorrectAns.elementAt(r3);
            strCorrectAns = strCorrectAns + "\n " + a[0] + " - " + a[1] + ",";
        }
        final ReportDataBaseLayer ob1 = new ReportDataBaseLayer();
        ob1.addDetailsQues(student_id, test_id, strTime, strQuesId, strQues, strCorrectAns, multipleItemResponse, ans_text, strFeedBack11, cScore, item_score);
        div1.addElement(new Div().addElement("" + no).setID("ItemId1")).addElement(new Div().addElement(strQues).setID("ItemText1")).addElement(new Div().addElement(strCorrectAns).setID("ItemAnswer1")).addElement(new Div().addElement(ans_text).setID("AunswerGiven1")).addElement(new Div().addElement(cScore).setID("ItemMarks1")).addElement(new Div().addElement(strScore).setID("ItemObtainedMarks1")).addElement(new Div().addElement(strFeedBack11).setID("Feedback1"));
        return div1;
    }
    
    public Vector getResponses(final Vector vAns) {
        final Vector v = new Vector();
        for (int i = 0; i < vAns.size(); ++i) {
            final Vector v2 = (Vector) vAns.elementAt(i);
            for (int m = 0; m < v2.size(); ++m) {
                v.addElement(v2.elementAt(m));
            }
        }
        return v;
    }
    
    public String convert(final String[] ForMR) {
        String str3 = "";
        for (int t = 0; t < ForMR.length; ++t) {
            if (t == ForMR.length - 1) {
                str3 += ForMR[t];
            }
            else {
                str3 = str3 + ForMR[t] + ",";
            }
        }
        return str3;
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    static {
        log = new SimpleLogger((Class)StResultSectionHTML.class, true);
    }
}
