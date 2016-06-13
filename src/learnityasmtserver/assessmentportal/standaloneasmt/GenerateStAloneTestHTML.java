package learnityasmtserver.assessmentportal.standaloneasmt;

import org.grlea.log.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.*;
import learnityasmtserver.assessmentportal.dbconnection.*;
import java.util.*;

public class GenerateStAloneTestHTML extends HttpServlet
{
    public static final SimpleLogger log;
    private static final String SESSION_MODEL_QUESTIONS = "SESSION_MODEL_QUESTIONS";
    private static final String SESSION_MODEL_ANS = "SESSION_MODEL_ANS";
    private static final String SESSION_MODEL_PER_PAGE = "SESSION_MODEL_PER_PAGE";
    private static final String SESSION_MODEL_TIME = "SESSION_MODEL_TIME";
    private static final String SESSION_MODEL_TEMPANS = "SESSION_MODEL_TEMPANS";
    private static final String ASSESSMENT_PROGRESS_ID = "ASSESSMENT_PROGRESS_ID";
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        final Vector obj = null;
        final Vector questionAll = null;
        final Vector page_questions = null;
        final Vector vQuestions = new Vector();
        final Vector vAnsTemp = new Vector();
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final HttpSession mysession = request.getSession(true);
        final String student_id = (String)mysession.getAttribute("student_id");
        GenerateStAloneTestHTML.log.debug("student_id=doget=" + student_id);
        if (student_id != null) {
            this.getResult(request, response, out, student_id, obj, questionAll, page_questions, vQuestions, vAnsTemp);
        }
    }
    
    public void getResult(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out, final String student_id, Vector obj, Vector questionAll, Vector<Vector<Vector>> page_questions, final Vector vQuestions, final Vector vAnsTemp) throws IOException, ServletException {
        final Calendar calendar = new GregorianCalendar();
        final Date trialTime = new Date();
        calendar.setTime(trialTime);
        final String[] months = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        final String strTime = calendar.get(10) + ":" + calendar.get(12) + ":" + calendar.get(13);
        final String strDate = months[calendar.get(2)] + " " + calendar.get(5) + ", " + calendar.get(1);
        final String str11 = strDate.substring(0, strDate.indexOf(44));
        final String str2 = str11.substring(0, str11.indexOf(32));
        final String str3 = str11.substring(str11.indexOf(32) + 1);
        final String str4 = strDate.substring(strDate.indexOf(44) + 1);
        final int l1 = months.length;
        Integer month1 = 0;
        for (int i11 = 0; i11 < l1; ++i11) {
            final String m = months[i11];
            if (str2.equals(m)) {
                month1 = i11 + 1;
                break;
            }
        }
        String s_month1 = Integer.toString(month1);
        if (month1 < 10) {
            s_month1 = "0" + s_month1;
        }
        final String df = str4 + "-" + s_month1 + "-" + str3;
        final HttpSession mysession = request.getSession(true);
        final String baseName = "portal1";
        String refresh = "";
        try {
            final Locale dd = Locale.getDefault();
            GenerateStAloneTestHTML.log.debug("INPUT LOCALE NAME IS " + Locale.getDefault());
            final double amt = 123456.78;
            GenerateStAloneTestHTML.log.debug("LANGUAGE" + dd.getLanguage());
            final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
            String key1 = "loginuser";
            key1 = "refreshassessment";
            refresh = rb.getString(key1);
        }
        catch (MissingResourceException e) {
            e.printStackTrace();
            GenerateStAloneTestHTML.log.debug("Exception:==" + e);
        }
        final ReportDataBaseLayer ob1 = new ReportDataBaseLayer();
        final String checkModelstatus = request.getParameter("checkModelstatus");
        final String newtest = request.getParameter("newtest");
        final String assessment_id = request.getParameter("assessment_id");
        final String save_state = request.getParameter("save_state");
        if (save_state != null && save_state.equals("no")) {
            final Vector<String> vv = ob1.isPreviousTestSubmitted(student_id, assessment_id);
            String pretest_id = "";
            if (vv.size() != 0) {
                pretest_id = vv.elementAt(2);
            }
            ob1.deleteprevioustest(student_id, assessment_id, pretest_id);
        }
        String timeparam = request.getParameter("disp2");
        if (timeparam != null) {
            timeparam = timeparam.trim();
        }
        System.out.println("timeparam=======1========" + timeparam);
        if (newtest != null && newtest.equals("start")) {
            vAnsTemp.removeAllElements();
        }
        Vector vAns = null;
        Vector vQuesIdAns = new Vector();
        final String serial_no_questions = request.getParameter("serial_no");
        int serial_no = 0;
        if (serial_no_questions != null && !serial_no_questions.equals("0")) {
            serial_no = Integer.parseInt(serial_no_questions);
        }
        else {
            serial_no = 0;
        }
        final String preview_param = request.getParameter("preview_param");
        if (checkModelstatus != null && preview_param == null) {
            mysession.removeAttribute("SESSION_MODEL_QUESTIONS");
            mysession.removeAttribute("SESSION_MODEL_ANS");
            mysession.removeAttribute("SESSION_MODEL_PER_PAGE");
            mysession.removeAttribute("SESSION_MODEL_TIME");
            mysession.removeAttribute("SESSION_MODEL_TEMPANS");
        }
        final String TestStartTime = (String)mysession.getAttribute("SESSION_MODEL_TIME");
        if (TestStartTime == null || TestStartTime.equals("")) {
            mysession.setAttribute("SESSION_MODEL_TIME", (Object)strTime);
        }
        String AssessmentTitle = "";
        String refreshassessment = "";
        if (refresh != null && !refresh.equals("")) {
            refreshassessment = "<meta http-equiv=\"refresh\" content=\"" + refresh + "; url=javascript:autosave()\" />";
        }
        final Vector<String[]> vAssessmentDeffination = ob1.getAssessmentDeffination(assessment_id);
        String total_ques = "";
        String total_time_left = "";
        String ques_per_page = "";
        if (assessment_id != null && checkModelstatus != null) {
            final Vector<String[]> vAssessmentInfo = ob1.getAssessmentInfo(assessment_id);
            final String[] AssessmentInfo1 = vAssessmentInfo.elementAt(0);
            total_time_left = AssessmentInfo1[0];
            ques_per_page = AssessmentInfo1[1];
            Integer totalques = 0;
            for (int k = 0; k < vAssessmentDeffination.size(); ++k) {
                final String[] AssessmentInfo2 = vAssessmentDeffination.elementAt(k);
                final String no_of_questions = AssessmentInfo2[1];
                final Integer totalques2 = Integer.parseInt(no_of_questions);
                totalques += totalques2;
            }
            total_ques = Integer.toString(totalques);
            AssessmentTitle = ReportDataBaseLayer.getAssessmentTitle(assessment_id);
        }
        else {
            total_time_left = request.getParameter("total_time_left");
            total_ques = request.getParameter("total_ques");
            AssessmentTitle = request.getParameter("AssessmentTitle");
        }
        final int totalquestion = Integer.parseInt(total_ques);
        obj = (Vector)mysession.getAttribute("SESSION_MODEL_QUESTIONS");
        int No_Ques = 0;
        if (total_ques != null && !total_ques.equals("")) {
            No_Ques = Integer.parseInt(total_ques);
        }
        int no_question_available = 0;
        final String total_ques_retrive = request.getParameter("total_no_of_question_retrived");
        if (total_ques_retrive != null && !total_ques_retrive.equals("")) {
            no_question_available = Integer.parseInt(total_ques_retrive);
        }
        else {
            no_question_available = 0;
        }
        int no_question_retrived = 0;
        final String no_of_question_retrived = request.getParameter("no_of_question_retrived");
        if (no_of_question_retrived != null && !no_of_question_retrived.equals("")) {
            no_question_retrived = Integer.parseInt(no_of_question_retrived);
        }
        int no_question_per_page = 0;
        if (newtest == null || !newtest.equals("start")) {
            ques_per_page = request.getParameter("ques_per_page");
        }
        if (ques_per_page != null && !ques_per_page.equals("")) {
            no_question_per_page = Integer.parseInt(ques_per_page);
        }
        else {
            no_question_per_page = No_Ques;
        }
        int duration = 0;
        if (total_time_left != null && !total_time_left.equals("")) {
            duration = Integer.parseInt(total_time_left);
        }
        else {
            duration = No_Ques;
        }
        final String previous = request.getParameter("previous");
        int previous_int = 0;
        if (previous != null) {
            previous_int = Integer.parseInt(previous);
        }
        String test_id = "";
        if (newtest != null && newtest.equals("start")) {
            ob1.addTestDetails(student_id, assessment_id, strTime, total_ques, ques_per_page, total_time_left);
            test_id = ob1.getTestID(student_id, assessment_id);
        }
        else {
            test_id = ob1.getTestID(student_id, assessment_id);
        }
        if (page_questions != null && checkModelstatus == null) {
            final Object o = mysession.getAttribute("SESSION_MODEL_ANS");
            final Vector vTempAns = (Vector)mysession.getAttribute("SESSION_MODEL_TEMPANS");
            mysession.removeAttribute("SESSION_MODEL_TEMPANS");
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
            final Vector vQ = new Vector();
            final Vector vT = new Vector();
            String IdMr = null;
            for (int j = 0; j < page_questions.size(); ++j) {
                final Vector<Vector> questionMatter = page_questions.elementAt(j);
                final Vector res = questionMatter.elementAt(0);
                final Vector question = questionMatter.elementAt(1);
                final Integer iTitle = (Integer) res.elementAt(0);
                String strQuesNo = "";
                String str5 = "";
                switch (iTitle) {
                    case 0: {
                        strQuesNo = (String) question.elementAt(0);
                        str5 = request.getParameter(strQuesNo);
                        vQ.addElement(strQuesNo);
                        vA.addElement(str5);
                        vT.addElement("MC");
                        break;
                    }
                    case 1: {
                        strQuesNo = (String) question.elementAt(0);
                        final String[] str6 = request.getParameterValues(strQuesNo);
                        IdMr = strQuesNo;
                        vQ.addElement(strQuesNo);
                        vA.addElement(str6);
                        vT.addElement("MR");
                        break;
                    }
                    case 2: {
                        strQuesNo = (String) question.elementAt(0);
                        str5 = request.getParameter(strQuesNo);
                        vQ.addElement(strQuesNo);
                        vA.addElement(str5);
                        vT.addElement("MC");
                        break;
                    }
                    case 3: {
                        strQuesNo = (String) question.elementAt(0);
                        str5 = request.getParameter(strQuesNo);
                        vQ.addElement(strQuesNo);
                        vA.addElement(str5);
                        vT.addElement("MC");
                        break;
                    }
                }
            }
            final Vector id_ans = new Vector();
            for (int i2 = 0; i2 < vA.size(); ++i2) {
                final String itemid = (String) vQ.elementAt(i2);
                id_ans.addElement(itemid);
                final String itypefans = (String) vT.elementAt(i2);
                String ansgiven = "";
                String[] ansgivenMr = new String[6];
                if (itypefans != null && itypefans.equals("MR")) {
                    ansgivenMr = (String[]) vA.elementAt(i2);
                    id_ans.addElement(ansgivenMr);
                }
                else {
                    ansgiven = (String) vA.elementAt(i2);
                    id_ans.addElement(ansgiven);
                }
            }
            vAnsTemp.addElement(id_ans);
            vQuesIdAns = this.getResponses(vAnsTemp);
            if (previous_int != 0) {
                vAns.removeElementAt(vAns.size() - 1);
            }
            else {
                vAns.addElement(vA);
            }
            mysession.setAttribute("SESSION_MODEL_ANS", (Object)vAns);
        }
        if (page_questions != null && preview_param != null) {
            final Object o = mysession.getAttribute("SESSION_MODEL_ANS");
            final Vector vTempAns = (Vector)mysession.getAttribute("SESSION_MODEL_TEMPANS");
            mysession.removeAttribute("SESSION_MODEL_TEMPANS");
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
            final Vector vQ = new Vector();
            final Vector vT = new Vector();
            String IdMr = null;
            for (int j = 0; j < page_questions.size(); ++j) {
                final Vector questionMatter = page_questions.elementAt(j);
                final Vector res = (Vector) questionMatter.elementAt(0);
                final Vector question = (Vector) questionMatter.elementAt(1);
                final Integer iTitle = (Integer) res.elementAt(0);
                String strQuesNo = "";
                String str5 = "";
                switch (iTitle) {
                    case 0: {
                        strQuesNo = (String) question.elementAt(0);
                        str5 = request.getParameter(strQuesNo);
                        vQ.addElement(strQuesNo);
                        final Vector vv2 = (Vector) vAns.lastElement();
                        final String temp_ans = (String) vv2.elementAt(j);
                        vA.addElement(temp_ans);
                        vT.addElement("MC");
                        break;
                    }
                    case 1: {
                        strQuesNo = (String) question.elementAt(0);
                        final String[] str7 = request.getParameterValues(strQuesNo);
                        IdMr = strQuesNo;
                        vQ.addElement(strQuesNo);
                        vA.addElement(str7);
                        vT.addElement("MR");
                        break;
                    }
                    case 2: {
                        strQuesNo = (String) question.elementAt(0);
                        str5 = request.getParameter(strQuesNo);
                        vQ.addElement(strQuesNo);
                        vA.addElement(str5);
                        vT.addElement("MC");
                        break;
                    }
                    case 3: {
                        strQuesNo = (String) question.elementAt(0);
                        str5 = request.getParameter(strQuesNo);
                        vQ.addElement(strQuesNo);
                        vA.addElement(str5);
                        vT.addElement("MC");
                        break;
                    }
                }
            }
            final Vector id_ans = new Vector();
            for (int i2 = 0; i2 < vA.size(); ++i2) {
                final String itemid = (String) vQ.elementAt(i2);
                id_ans.addElement(itemid);
                final String itypefans = (String) vT.elementAt(i2);
                String ansgiven = "";
                String[] ansgivenMr = new String[6];
                if (itypefans != null && itypefans.equals("MR")) {
                    ansgivenMr = (String[]) vA.elementAt(i2);
                    id_ans.addElement(ansgivenMr);
                }
                else {
                    ansgiven = (String) vA.elementAt(i2);
                    id_ans.addElement(ansgiven);
                }
            }
            vAnsTemp.addElement(id_ans);
            vQuesIdAns = this.getResponses(vAnsTemp);
            if (previous_int != 0) {
                vAns.removeElementAt(vAns.size() - 1);
            }
            else {
                vAns.addElement(vA);
            }
            mysession.setAttribute("SESSION_MODEL_ANS", (Object)vAns);
        }
        int AllTotalTime = 0;
        if (checkModelstatus != null) {
            if (total_time_left != null && !total_time_left.equals("")) {
                AllTotalTime = Integer.parseInt(total_time_left);
                timeparam = AllTotalTime + ":00";
            }
            else {
                AllTotalTime = No_Ques;
            }
        }
        else {
            final String All_total_time = request.getParameter("total_time");
            AllTotalTime = Integer.parseInt(All_total_time);
        }
        String strButton = "";
        if (previous_int != 1) {
            if (No_Ques != 0) {
                final AllStAloneTestItems AllItems = new AllStAloneTestItems();
                if (obj == null) {
                    questionAll = AllItems.getAllItems(vAssessmentDeffination);
                    mysession.removeAttribute("SESSION_MODEL_QUESTIONS");
                    mysession.setAttribute("SESSION_MODEL_QUESTIONS", (Object)questionAll);
                    obj = (Vector)mysession.getAttribute("SESSION_MODEL_QUESTIONS");
                    no_question_available = questionAll.size();
                    if (newtest != null && newtest.equals("start")) {
                        ob1.addProgressStatus(student_id, assessment_id, ques_per_page, strTime, total_time_left);
                        mysession.removeAttribute("ASSESSMENT_PROGRESS_ID");
                        mysession.setAttribute("ASSESSMENT_PROGRESS_ID", (Object)test_id);
                    }
                }
                page_questions = this.getQuestionsPerPage(obj, no_question_retrived, no_question_per_page);
                mysession.removeAttribute("SESSION_MODEL_PER_PAGE");
                mysession.setAttribute("SESSION_MODEL_PER_PAGE", (Object)page_questions);
                if (page_questions.size() != 0) {
                    final String lastElement_obj = obj.lastElement().toString();
                    final String lastElement_page_questions = page_questions.lastElement().toString();
                    final String firstElement_obj = obj.firstElement().toString();
                    final String firstElement_page_questions = page_questions.firstElement().toString();
                    if (no_question_per_page == No_Ques || No_Ques < no_question_per_page || no_question_per_page == 0 || lastElement_page_questions.equals(lastElement_obj)) {
                        if (obj.size() > no_question_per_page && no_question_per_page != 0 && lastElement_page_questions.equals(lastElement_obj)) {
                            strButton = "\n<input class=\"sbttn\" name=\"add1\" tabIndex=\"2\" title=\"Previous Page\" type=\"button\" value=\"Previous Page\" onclick=\"Previous_onclick()\"/>";
                            strButton += "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Submit\" type=\"button\" value=\"Preview\" onclick=\"submitPage()\"/>";
                            for (int i2 = 0; i2 < page_questions.size(); ++i2) {
                                final Vector vector1 = page_questions.elementAt(i2);
                                vQuestions.addElement(vector1);
                            }
                            final StAloneTestHtmlStructure s = new StAloneTestHtmlStructure();
                            final String strHTML = s.generateHTML(page_questions, strButton, duration, true, total_ques, ques_per_page, total_time_left, no_question_retrived, no_question_available, serial_no, AllTotalTime, assessment_id, vQuesIdAns, AssessmentTitle, refreshassessment, timeparam);
                            out.println(strHTML);
                        }
                        else {
                            strButton = "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Submit\" type=\"button\" value=\"Preview\" onclick=\"submitPage()\"/>";
                            for (int i2 = 0; i2 < page_questions.size(); ++i2) {
                                final Vector vector1 = page_questions.elementAt(i2);
                                vQuestions.addElement(vector1);
                            }
                            final StAloneTestHtmlStructure s = new StAloneTestHtmlStructure();
                            final String strHTML = s.generateHTML(page_questions, strButton, duration, true, total_ques, ques_per_page, total_time_left, no_question_retrived, no_question_available, serial_no, AllTotalTime, assessment_id, vQuesIdAns, AssessmentTitle, refreshassessment, timeparam);
                            out.println(strHTML);
                        }
                    }
                    else if (firstElement_obj.equals(firstElement_page_questions)) {
                        strButton += "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>";
                        strButton += "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Submit\" type=\"button\" value=\"Preview\" onclick=\"submitPage()\"/>";
                        vQuestions.removeAllElements();
                        for (int i2 = 0; i2 < page_questions.size(); ++i2) {
                            final Vector vector1 = page_questions.elementAt(i2);
                            vQuestions.addElement(vector1);
                        }
                        final StAloneTestHtmlStructure s = new StAloneTestHtmlStructure();
                        final String strHTML = s.generateHTML(page_questions, strButton, duration, true, total_ques, ques_per_page, total_time_left, no_question_retrived, no_question_available, serial_no, AllTotalTime, assessment_id, vQuesIdAns, AssessmentTitle, refreshassessment, timeparam);
                        out.println(strHTML);
                    }
                    else {
                        strButton += "\n<input class=\"sbttn\" name=\"add1\" tabIndex=\"2\" title=\"Previous Page\" type=\"button\" value=\"Previous Page\" onclick=\"Previous_onclick()\"/>";
                        strButton += "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>";
                        strButton += "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Submit\" type=\"button\" value=\"Preview\" onclick=\"submitPage()\"/>";
                        for (int i2 = 0; i2 < page_questions.size(); ++i2) {
                            final Vector vector1 = page_questions.elementAt(i2);
                            vQuestions.addElement(vector1);
                        }
                        final StAloneTestHtmlStructure s = new StAloneTestHtmlStructure();
                        final String strHTML = s.generateHTML(page_questions, strButton, duration, true, total_ques, ques_per_page, total_time_left, no_question_retrived, no_question_available, serial_no, AllTotalTime, assessment_id, vQuesIdAns, AssessmentTitle, refreshassessment, timeparam);
                        out.println(strHTML);
                    }
                }
                else {
                    strButton = "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Ok\" type=\"button\" value=\"Ok\" onclick=\"ok_onclick()\"/>";
                    final StAloneTestHtmlStructure s2 = new StAloneTestHtmlStructure();
                    final String strHTML2 = s2.generateHTML(page_questions, strButton, duration, true, total_ques, ques_per_page, total_time_left, no_question_retrived, no_question_available, serial_no, AllTotalTime, assessment_id, vQuesIdAns, AssessmentTitle, refreshassessment, timeparam);
                    out.println(strHTML2);
                }
            }
            else if (vAssessmentDeffination.size() == 0) {
                questionAll = null;
                strButton = "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Ok\" type=\"button\" value=\"Ok\" onclick=\"ok_onclick()\"/>";
                final StAloneTestHtmlStructure s3 = new StAloneTestHtmlStructure();
                final String strHTML3 = s3.generateHTML(questionAll, strButton, duration, true, total_ques, ques_per_page, total_time_left, no_question_retrived, no_question_available, serial_no, AllTotalTime, assessment_id, vQuesIdAns, AssessmentTitle, refreshassessment, timeparam);
                out.println(strHTML3);
            }
        }
        else if (previous_int != 1 && preview_param == null) {
            final int total_ques2 = Integer.parseInt(total_ques);
            final int ques_per_page2 = Integer.parseInt(ques_per_page);
            if (vQuestions.size() == total_ques2 || (obj.size() < total_ques2 && obj.size() == vQuestions.size())) {
                final int re = obj.size() % ques_per_page2;
                int count = 0;
                if (re != 0) {
                    count = ques_per_page2 + re;
                }
                else {
                    count = 2 * ques_per_page2;
                }
                final int count2 = vQuestions.size() - count;
                final Vector vTemp = new Vector();
                for (int i3 = count2; i3 < ques_per_page2 + count2; ++i3) {
                    final Vector vector2 = (Vector) vQuestions.elementAt(i3);
                    vTemp.addElement(vector2);
                }
                serial_no = count2;
                no_question_retrived -= count;
                if (total_ques2 > 2 * ques_per_page2) {
                    strButton = "\n<input class=\"sbttn\" name=\"add1\" tabIndex=\"2\" title=\"Previous Page\" type=\"button\" value=\"Previous Page\" onclick=\"Previous_onclick()\"/>";
                    strButton += "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>";
                }
                else {
                    strButton = "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>";
                }
                for (int length1 = vQuestions.size(), j2 = 0; j2 < length1 - count2; ++j2) {
                    vQuestions.removeElementAt(count2);
                }
                for (int length2 = page_questions.size(), k2 = 0; k2 < length2; ++k2) {
                    page_questions.removeElementAt(0);
                }
                for (int i4 = 0; i4 < vTemp.size(); ++i4) {
                    final Vector vector3 = (Vector) vTemp.elementAt(i4);
                    page_questions.addElement(vector3);
                    vQuestions.addElement(vector3);
                }
                final StAloneTestHtmlStructure s4 = new StAloneTestHtmlStructure();
                final String strHTML4 = s4.generateHTML(page_questions, strButton, duration, true, total_ques, ques_per_page, total_time_left, no_question_retrived, no_question_available, serial_no, AllTotalTime, assessment_id, vQuesIdAns, AssessmentTitle, refreshassessment, timeparam);
                out.println(strHTML4);
            }
            else {
                final int count3 = vQuestions.size() - 2 * ques_per_page2;
                final Vector vTemp2 = new Vector();
                for (int j = count3; j < ques_per_page2 + count3; ++j) {
                    final Vector vector4 = (Vector) vQuestions.elementAt(j);
                    vTemp2.addElement(vector4);
                }
                serial_no = count3;
                no_question_retrived -= ques_per_page2;
                no_question_retrived = count3;
                final String firstElement_obj2 = obj.firstElement().toString();
                final String firstElement_page_questions2 = vTemp2.firstElement().toString();
                if (!firstElement_obj2.equals(firstElement_page_questions2)) {
                    strButton = "\n<input class=\"sbttn\" name=\"add1\" tabIndex=\"2\" title=\"Previous Page\" type=\"button\" value=\"Previous Page\" onclick=\"Previous_onclick()\"/>";
                    strButton += "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>";
                }
                else {
                    strButton = "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>";
                }
                for (int length1 = vQuestions.size(), j2 = 0; j2 < length1 - count3; ++j2) {
                    vQuestions.removeElementAt(count3);
                }
                for (int length2 = page_questions.size(), k2 = 0; k2 < length2; ++k2) {
                    page_questions.removeElementAt(0);
                }
                for (int i4 = 0; i4 < vTemp2.size(); ++i4) {
                    final Vector vector3 = (Vector) vTemp2.elementAt(i4);
                    page_questions.addElement(vector3);
                    vQuestions.addElement(vector3);
                }
                final StAloneTestHtmlStructure s4 = new StAloneTestHtmlStructure();
                final String strHTML4 = s4.generateHTML(page_questions, strButton, duration, true, total_ques, ques_per_page, total_time_left, no_question_retrived, no_question_available, serial_no, AllTotalTime, assessment_id, vQuesIdAns, AssessmentTitle, refreshassessment, timeparam);
                out.println(strHTML4);
            }
        }
        else {
            final int total_ques2 = Integer.parseInt(total_ques);
            final int ques_per_page2 = Integer.parseInt(ques_per_page);
            if (vQuestions.size() == total_ques2 || (obj.size() < total_ques2 && obj.size() == vQuestions.size())) {
                final int re = obj.size() % ques_per_page2;
                int count = 0;
                if (re == 0) {
                    count = ques_per_page2 + re;
                }
                else {
                    count = 2 * ques_per_page2;
                }
                final int count2 = vQuestions.size() - count;
                final Vector vTemp = new Vector();
                for (int i3 = count2; i3 < ques_per_page2 + count2; ++i3) {
                    final Vector vector2 = (Vector) vQuestions.elementAt(i3);
                    vTemp.addElement(vector2);
                }
                serial_no = count2;
                no_question_retrived -= count;
                if (total_ques2 > 2 * ques_per_page2) {
                    strButton = "\n<input class=\"sbttn\" name=\"add1\" tabIndex=\"2\" title=\"Previous Page\" type=\"button\" value=\"Previous Page\" onclick=\"Previous_onclick()\"/>";
                    strButton += "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>";
                }
                else {
                    strButton = "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Submit\" type=\"button\" value=\"Preview\" onclick=\"submitPage()\"/>";
                }
                for (int length1 = vQuestions.size(), j2 = 0; j2 < length1 - count2; ++j2) {
                    vQuestions.removeElementAt(count2);
                }
                for (int length2 = page_questions.size(), k2 = 0; k2 < length2; ++k2) {
                    page_questions.removeElementAt(0);
                }
                for (int i4 = 0; i4 < vTemp.size(); ++i4) {
                    final Vector vector3 = (Vector) vTemp.elementAt(i4);
                    page_questions.addElement(vector3);
                    vQuestions.addElement(vector3);
                }
                final StAloneTestHtmlStructure s4 = new StAloneTestHtmlStructure();
                final String strHTML4 = s4.generateHTML(page_questions, strButton, duration, true, total_ques, ques_per_page, total_time_left, no_question_retrived, no_question_available, serial_no, AllTotalTime, assessment_id, vQuesIdAns, AssessmentTitle, refreshassessment, timeparam);
                out.println(strHTML4);
            }
            else {
                final int count3 = vQuestions.size() - 2 * ques_per_page2;
                final Vector vTemp2 = new Vector();
                for (int j = count3; j < ques_per_page2 + count3; ++j) {
                    final Vector vector4 = (Vector) vQuestions.elementAt(j);
                    vTemp2.addElement(vector4);
                }
                serial_no = count3;
                no_question_retrived -= ques_per_page2;
                no_question_retrived = count3;
                final String firstElement_obj2 = obj.firstElement().toString();
                final String firstElement_page_questions2 = vTemp2.firstElement().toString();
                if (!firstElement_obj2.equals(firstElement_page_questions2)) {
                    strButton = "\n<input class=\"sbttn\" name=\"add1\" tabIndex=\"2\" title=\"Previous Page\" type=\"button\" value=\"Previous Page\" onclick=\"Previous_onclick()\"/>";
                    strButton += "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Page\" type=\"button\" value=\"Next Page\" onclick=\"submit_onclick()\"/>";
                }
                else {
                    strButton = "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Submit\" type=\"button\" value=\"Preview\" onclick=\"submitPage()\"/>";
                }
                for (int length1 = vQuestions.size(), j2 = 0; j2 < length1 - count3; ++j2) {
                    vQuestions.removeElementAt(count3);
                }
                for (int length2 = page_questions.size(), k2 = 0; k2 < length2; ++k2) {
                    page_questions.removeElementAt(0);
                }
                for (int i4 = 0; i4 < vTemp2.size(); ++i4) {
                    final Vector vector3 = (Vector) vTemp2.elementAt(i4);
                    page_questions.addElement(vector3);
                    vQuestions.addElement(vector3);
                }
                final StAloneTestHtmlStructure s4 = new StAloneTestHtmlStructure();
                final String strHTML4 = s4.generateHTML(page_questions, strButton, duration, true, total_ques, ques_per_page, total_time_left, no_question_retrived, no_question_available, serial_no, AllTotalTime, assessment_id, vQuesIdAns, AssessmentTitle, refreshassessment, timeparam);
                out.println(strHTML4);
            }
        }
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
    
    public Vector getQuestionsPerPage(final Vector questionAll, final int no_question_retrived, final int no_question_per_page) {
        final Vector v = new Vector();
        int m = 0;
        for (int i = no_question_retrived; i < questionAll.size(); ++i) {
            v.addElement(questionAll.elementAt(i));
            ++m;
            if (no_question_per_page == m) {
                break;
            }
        }
        return v;
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    static {
        log = new SimpleLogger((Class)GenerateStAloneTestHTML.class, true);
    }
}
