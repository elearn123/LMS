package learnityasmtserver.assessmentportal.standaloneasmt;

import javax.servlet.http.*;
import javax.servlet.*;
import learnityasmtserver.assessmentportal.dbconnection.*;
import java.util.*;

import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;
import org.grlea.log.*;
import org.xml.sax.*;
import java.io.*;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class GenerateTestHTML extends HttpServlet
{
    public static final SimpleLogger log;
    private static final String SESSION_MODEL_ANS = "SESSION_MODEL_ANS";
    private static final String SESSION_MODEL_ANS1 = "SESSION_MODEL_ANS1";
    private static final String SESSION_MODEL_PER_PAGE = "SESSION_MODEL_PER_PAGE";
    private static final String SESSION_MODEL_QUESTIONS = "SESSION_MODEL_QUESTIONS";
    private static final String SESSION_MODEL_PROGRESS_QUESTIONS = "SESSION_MODEL_PROGRESS_QUESTIONSS";
    private static final String SESSION_MODEL_TIME = "SESSION_MODEL_TIME";
    private static final String SESSION_MODEL_TEMPANS = "SESSION_MODEL_TEMPANS";
    private static final String SESSION_TEMPANS = "SESSION_TEMPANS";
    private static final String ASSESSMENT_PROGRESS_ID = "ASSESSMENT_PROGRESS_ID";
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        final Vector obj = null;
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final HttpSession mysession = request.getSession(true);
        final String student_id = (String)mysession.getAttribute("student_id");
        System.out.println("student_id=doget=" + student_id);
        if (student_id != null) {
            this.getResult(request, response, out, student_id);
        }
    }
    
    public void getResult(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out, final String student_id) throws IOException, ServletException {
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
        String test_id = "";
        Vector vSessionQuestions = new Vector();
        vSessionQuestions = (Vector)mysession.getAttribute("SESSION_MODEL_QUESTIONS");
        Vector vSessionQuestions2 = new Vector();
        vSessionQuestions2 = (Vector)mysession.getAttribute("SESSION_MODEL_PROGRESS_QUESTIONSS");
        final String assessment_id = request.getParameter("assessment_id");
        final String newtest = request.getParameter("newtest");
        String timeparam = request.getParameter("disp2");
        if (timeparam != null) {
            timeparam = timeparam.trim();
        }
        System.out.println("timeparam=======2========" + timeparam);
        Vector questionAll = null;
        final ReportDataBaseLayer ob1 = new ReportDataBaseLayer();
        if (newtest != null && newtest.equals("start")) {
            test_id = ob1.getTestID(student_id, assessment_id);
            mysession.setAttribute("ASSESSMENT_PROGRESS_ID", (Object)test_id);
            questionAll = this.createQuestionAllStructure(student_id, assessment_id, test_id);
            mysession.setAttribute("SESSION_MODEL_PROGRESS_QUESTIONSS", (Object)questionAll);
        }
        else {
            test_id = (String)mysession.getAttribute("ASSESSMENT_PROGRESS_ID");
            if (vSessionQuestions == null) {
                questionAll = vSessionQuestions2;
            }
            else {
                questionAll = vSessionQuestions;
            }
        }
        System.out.println("questionAll====" + questionAll);
        final String browserName = request.getParameter("browserName");
        Vector vQAns = new Vector();
        if (newtest != null && newtest.equals("start")) {
            vQAns = this.getQuestionAnswerVector(student_id, assessment_id, test_id);
        }
        System.out.println("vQAns==2==" + vQAns);
        String returnValue1 = request.getParameter("returnValue1");
        if (returnValue1 == null || returnValue1.equals("")) {
            returnValue1 = "0";
        }
        String total_ques = request.getParameter("total_ques");
        System.out.println("**********total_ques***********" + total_ques);
        String AssessmentTitle = request.getParameter("AssessmentTitle");
        String ques_per_page = request.getParameter("ques_per_page");
        System.out.println("**********ques_per_page***********" + ques_per_page);
        final String total_time_left1 = request.getParameter("total_time_left");
        Integer total_time_left2 = 0;
        if (newtest == null) {
            total_time_left2 = Integer.parseInt(total_time_left1);
        }
        final String no_of_question_retrived1 = request.getParameter("no_of_question_retrived");
        Integer no_of_question_retrived2 = 0;
        if (newtest == null) {
            no_of_question_retrived2 = Integer.parseInt(no_of_question_retrived1);
        }
        final String total_no_of_question_retrived1 = request.getParameter("total_no_of_question_retrived");
        Integer total_no_of_question_retrived2 = 0;
        if (newtest == null) {
            total_no_of_question_retrived2 = Integer.parseInt(total_no_of_question_retrived1);
        }
        final String duration = request.getParameter("duration");
        final String serial_no1 = request.getParameter("serial_no");
        Integer serial_no2 = 0;
        if (newtest == null) {
            serial_no2 = Integer.parseInt(serial_no1);
        }
        final String total_time1 = request.getParameter("total_time");
        Integer total_time2 = 0;
        if (newtest == null) {
            total_time2 = Integer.parseInt(total_time1);
        }
        final String preview_param = request.getParameter("preview_param");
        final Vector vAns = new Vector();
        final Vector vSetAnstoSession = new Vector();
        final Vector vA = new Vector();
        final Vector vQ = new Vector();
        final Vector vT = new Vector();
        String IdMr = null;
        if (preview_param == null && questionAll != null) {
            for (int j = 0; j < questionAll.size(); ++j) {
                final Vector questionMatter = (Vector) questionAll.elementAt(j);
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
                        vA.addElement(str5);
                        vT.addElement("MC");
                        vSetAnstoSession.addElement(strQuesNo);
                        vSetAnstoSession.addElement(str5);
                        break;
                    }
                    case 1: {
                        strQuesNo = (String) question.elementAt(0);
                        final String[] str6 = request.getParameterValues(strQuesNo);
                        IdMr = strQuesNo;
                        vQ.addElement(strQuesNo);
                        vA.addElement(str6);
                        vT.addElement("MR");
                        vSetAnstoSession.addElement(strQuesNo);
                        vSetAnstoSession.addElement(str6);
                        break;
                    }
                    case 2: {
                        strQuesNo = (String) question.elementAt(0);
                        str5 = request.getParameter(strQuesNo);
                        vQ.addElement(strQuesNo);
                        vA.addElement(str5);
                        vT.addElement("MC");
                        vSetAnstoSession.addElement(strQuesNo);
                        vSetAnstoSession.addElement(str5);
                        break;
                    }
                    case 3: {
                        strQuesNo = (String) question.elementAt(0);
                        str5 = request.getParameter(strQuesNo);
                        vQ.addElement(strQuesNo);
                        vA.addElement(str5);
                        vT.addElement("MC");
                        vSetAnstoSession.addElement(strQuesNo);
                        vSetAnstoSession.addElement(str5);
                        break;
                    }
                    case 4: {
                        final Vector vOption = (Vector) question.elementAt(2);
                        final Vector leftoption = (Vector) vOption.elementAt(0);
                        final Vector vector1 = new Vector();
                        final String[] l2 = new String[leftoption.size()];
                        final String[] r1 = new String[leftoption.size()];
                        strQuesNo = (String) question.elementAt(0);
                        vQ.addElement(strQuesNo);
                        final String[] alp = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
                        final String[] alp2 = { "L1", "L2", "L3", "L4", "L5", "L6", "L7", "L8", "L9", "L10" };
                        final String[] alp3 = { "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R10" };
                        for (int i2 = 0; i2 < leftoption.size(); ++i2) {
                            final String noalp = alp[i2];
                            final String type1 = "l" + strQuesNo + noalp;
                            final String type2 = "r" + strQuesNo + noalp;
                            final String str7 = request.getParameter(type1);
                            System.out.println("===str11=========" + str11);
                            final String str8 = request.getParameter(type2);
                            System.out.println("===str21=========" + str8);
                            l2[i2] = str7;
                            r1[i2] = str8;
                        }
                        vector1.addElement(l2);
                        vector1.addElement(r1);
                        vA.addElement(vector1);
                        vT.addElement("MQ");
                        vSetAnstoSession.addElement(strQuesNo);
                        vSetAnstoSession.addElement(vector1);
                        break;
                    }
                }
            }
        }
        if (preview_param == null) {
            mysession.setAttribute("SESSION_MODEL_ANS1", (Object)vSetAnstoSession);
        }
        vAns.addElement(vA);
        Vector vQuesIdAns = new Vector();
        vQuesIdAns = (Vector)mysession.getAttribute("SESSION_MODEL_ANS1");
        System.out.println("vQuesIdAns==" + vQuesIdAns);
        if (newtest == null || !newtest.equals("start")) {
            if (preview_param == null) {
                final String autosave = this.createElement(questionAll, vQuesIdAns);
                final ReportDataBaseLayer ob2 = new ReportDataBaseLayer();
                ob2.modifyProgressData(student_id, assessment_id, autosave, total_time_left1, test_id);
            }
        }
        final String baseName = "portal1";
        String refresh = "";
        try {
            final Locale dd = Locale.getDefault();
            System.out.println("INPUT LOCALE NAME IS " + Locale.getDefault());
            final double amt = 123456.78;
            System.out.println("LANGUAGE" + dd.getLanguage());
            final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
            String key1 = "loginuser";
            key1 = "refreshassessment";
            refresh = rb.getString(key1);
        }
        catch (MissingResourceException e) {
            e.printStackTrace();
            System.out.println("Exception:==" + e);
        }
        String refreshassessment = "";
        if (refresh != null && !refresh.equals("")) {
            refreshassessment = "<meta http-equiv=\"refresh\" content=\"" + refresh + "; url=javascript:autosave()\" />";
        }
        final String strButton = "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Submit\" type=\"button\" value=\"Preview\" onclick=\"submitPage()\"/>";
        String strHTML = "";
        if (newtest != null && newtest.equals("start")) {
            final Vector vlefttime = ob1.getLeftTime(student_id, assessment_id, test_id);
            final int lefttime = (Integer) vlefttime.elementAt(0);
            ques_per_page = (String) vlefttime.elementAt(1);
            final Vector vAssessmentDeffination = ob1.getAssessmentDeffination(assessment_id);
            if (assessment_id != null) {
                final Vector vAssessmentInfo = ob1.getAssessmentInfo(assessment_id);
                final String[] AssessmentInfo1 = (String[]) vAssessmentInfo.elementAt(0);
                Integer totalques = 0;
                for (int k = 0; k < vAssessmentDeffination.size(); ++k) {
                    final String[] AssessmentInfo2 = (String[]) vAssessmentDeffination.elementAt(k);
                    final String no_of_questions = AssessmentInfo2[1];
                    final Integer totalques2 = Integer.parseInt(no_of_questions);
                    totalques += totalques2;
                }
                no_of_question_retrived2 = totalques;
                total_no_of_question_retrived2 = totalques;
                total_ques = Integer.toString(totalques);
                AssessmentTitle = ReportDataBaseLayer.getAssessmentTitle(assessment_id);
            }
            serial_no2 = 0;
            ob1.updateRestartDateTime(student_id, assessment_id, strTime, test_id);
            final StAloneTestHtmlStructure s = new StAloneTestHtmlStructure();
            strHTML = s.generateHTML(questionAll, strButton, lefttime, true, total_ques, ques_per_page, total_time_left1, no_of_question_retrived2, total_no_of_question_retrived2, serial_no2, total_time_left2, assessment_id, vQAns, AssessmentTitle, refreshassessment, timeparam);
        }
        else {
            final StAloneTestHtmlStructure s2 = new StAloneTestHtmlStructure();
            strHTML = s2.generateHTML(questionAll, strButton, total_time_left2, true, total_ques, ques_per_page, total_time_left1, no_of_question_retrived2, total_no_of_question_retrived2, serial_no2, total_time_left2, assessment_id, vQuesIdAns, AssessmentTitle, refreshassessment, timeparam);
        }
        out.println(strHTML);
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
    
    private String createElement(final Vector questionAll, final Vector vQuesIdAns) {
        String str1 = "";
        try {
            final Document doc = (Document)new DocumentImpl();
            final Element autosave = doc.createElement("AUTOSAVE");
            doc.appendChild(autosave);
            String ans_item = "{";
            String notans_item = "{";
            for (int i = 0; i < questionAll.size(); ++i) {
                final Vector questionMatter = (Vector) questionAll.elementAt(i);
                final Vector response = (Vector) questionMatter.elementAt(0);
                final Vector question = (Vector) questionMatter.elementAt(1);
                final String strQuesId = (String) question.elementAt(0);
                final Integer strQuestype = (Integer) response.elementAt(0);
                final int sequenc = i + 1;
                final String strsequenc = Integer.toString(sequenc);
                String titleType = "";
                switch (strQuestype) {
                    default: {
                        System.out.println("strQuestype=default=" + strQuestype);
                    }
                    case 0: {
                        titleType = "Multiple Choice";
                        System.out.println("titleType====" + titleType);
                        break;
                    }
                    case 1: {
                        titleType = "Multiple Response";
                        System.out.println("titleType====" + titleType);
                        break;
                    }
                    case 2: {
                        titleType = "Fill in Blank";
                        System.out.println("titleType====" + titleType);
                        break;
                    }
                    case 3: {
                        titleType = "True False";
                        System.out.println("titleType====" + titleType);
                        break;
                    }
                    case 4: {
                        titleType = "Matching Question";
                        System.out.println("titleType====" + titleType);
                        break;
                    }
                }
                final int n = i * 2;
                System.out.println("vQuesIdAns===" + vQuesIdAns);
                final String id = (String) vQuesIdAns.elementAt(n);
                String ansdata = "";
                if (strQuestype == 1) {
                    final String[] ansdata2 = (String[])vQuesIdAns.elementAt(n + 1);
                    if (ansdata2 != null) {
                        ansdata = this.convert(ansdata2) + ",";
                    }
                }
                else if (strQuestype == 4) {
                    final Vector v1 = (Vector)vQuesIdAns.elementAt(n + 1);
                    final String[] leftpAns11 = (String[]) v1.elementAt(0);
                    final String[] rightAns11 = (String[]) v1.elementAt(1);
                    String ansdata3 = "";
                    for (int r1 = 0; r1 < leftpAns11.length; ++r1) {
                        ansdata3 = (ansdata = ansdata3 + leftpAns11[r1] + "-" + rightAns11[r1] + ",");
                    }
                }
                else {
                    ansdata = (String) vQuesIdAns.elementAt(n + 1);
                }
                System.out.println("ansdata====" + ansdata);
                if (ansdata != null && strQuesId.equals(id)) {
                    ans_item = ans_item + "(" + strsequenc + "," + strQuesId + ")";
                    final Element itemstate = doc.createElement("ITEMSTATE");
                    autosave.appendChild(itemstate);
                    final Attr sequenceno = doc.createAttribute("SequenceNo");
                    sequenceno.setValue(strsequenc);
                    final Attr itemid = doc.createAttribute("ItemId");
                    itemid.setValue(strQuesId);
                    final Attr itemtype = doc.createAttribute("ItemType");
                    itemtype.setValue(titleType);
                    itemstate.setAttributeNode(itemtype);
                    itemstate.setAttributeNode(itemid);
                    itemstate.setAttributeNode(sequenceno);
                    final Element state = doc.createElement("STATE");
                    itemstate.appendChild(state);
                    if (strQuestype == 0) {
                        final Element choice = doc.createElement("CHOICE");
                        state.appendChild(choice);
                        final Attr ansid = doc.createAttribute("Id");
                        ansid.setValue(ansdata);
                        choice.setAttributeNode(ansid);
                        state.appendChild(choice);
                    }
                    if (strQuestype == 1) {
                        while (ansdata.length() > 1) {
                            System.out.println("ansdata.length()====" + ansdata.length());
                            final String check = ansdata.substring(0, ansdata.indexOf(44));
                            System.out.println("check====" + check);
                            ansdata = ansdata.substring(ansdata.indexOf(44) + 1);
                            final Element choice2 = doc.createElement("CHOICE");
                            state.appendChild(choice2);
                            final Attr ansid2 = doc.createAttribute("Id");
                            ansid2.setValue(check);
                            choice2.setAttributeNode(ansid2);
                            state.appendChild(choice2);
                        }
                    }
                    if (strQuestype == 2) {
                        final Element choice = doc.createElement("CHOICE");
                        state.appendChild(choice);
                        final Attr ansid = doc.createAttribute("Id");
                        ansid.setValue(ansdata);
                        choice.setAttributeNode(ansid);
                        state.appendChild(choice);
                    }
                    if (strQuestype == 3) {
                        final Element choice = doc.createElement("CHOICE");
                        state.appendChild(choice);
                        final Attr ansid = doc.createAttribute("Id");
                        ansid.setValue(ansdata);
                        choice.setAttributeNode(ansid);
                        state.appendChild(choice);
                    }
                    if (strQuestype == 4) {
                        while (ansdata.length() > 1) {
                            System.out.println("ansdata.length()====" + ansdata.length());
                            final String check = ansdata.substring(0, ansdata.indexOf(44));
                            System.out.println("check====" + check);
                            ansdata = ansdata.substring(ansdata.indexOf(44) + 1);
                            final Element choice2 = doc.createElement("CHOICE");
                            state.appendChild(choice2);
                            final Attr ansid2 = doc.createAttribute("Id");
                            ansid2.setValue(check);
                            choice2.setAttributeNode(ansid2);
                            state.appendChild(choice2);
                        }
                    }
                }
                else {
                    notans_item = notans_item + "(" + strsequenc + "," + strQuesId + ")";
                }
            }
            ans_item += "}";
            notans_item += "}";
            final Element attempted = doc.createElement("ATTEMPTED_ITEM");
            autosave.appendChild(attempted);
            final Element notattempted = doc.createElement("UNATTEMPTED_ITEM");
            autosave.appendChild(notattempted);
            final Text ans_item2 = doc.createTextNode(ans_item);
            attempted.appendChild(ans_item2);
            final Text notans_item2 = doc.createTextNode(notans_item);
            notattempted.appendChild(notans_item2);
            final OutputFormat format = new OutputFormat(doc);
            final StringWriter stringOut = new StringWriter();
            final XMLSerializer serial = new XMLSerializer((Writer)stringOut, format);
            serial.asDOMSerializer();
            serial.serialize(doc.getDocumentElement());
            final String xml = stringOut.toString();
            final int length = xml.length();
            str1 = xml.substring(39, length);
        }
        catch (Exception e) {
            GenerateTestHTML.log.fatal("Exception:");
            GenerateTestHTML.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        return str1;
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
    
    public Vector createQuestionAllStructure(final String student_id, final String assessment_id, final String test_id) {
        Vector questionall = new Vector();
        final Vector vItemIdd = new Vector();
        final Vector vItemId = new Vector();
        final ReportDataBaseLayer ob1 = new ReportDataBaseLayer();
        final Vector vtext = ob1.getAutosaveText(student_id, test_id);
        final InputStream autoasave_text = (InputStream) vtext.elementAt(0);
        try {
            final InputStreamReader input_reader = new InputStreamReader(autoasave_text, "UTF-8");
            final InputSource input_source = new InputSource(input_reader);
            input_source.setEncoding("UTF-8");
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document d = db.parse(input_source);
            final NodeList attemptedlist = d.getElementsByTagName("ATTEMPTED_ITEM");
            final NodeList unattemptedlist = d.getElementsByTagName("UNATTEMPTED_ITEM");
            String attempted = attemptedlist.item(0).getFirstChild().getNodeValue();
            String unattempted = unattemptedlist.item(0).getFirstChild().getNodeValue();
            final int i1 = attempted.length();
            final int i2 = unattempted.length();
            attempted = attempted.substring(1, i1 - 1);
            unattempted = unattempted.substring(1, i2 - 1);
            while (attempted.length() > 4) {
                String check1 = attempted.substring(0, attempted.indexOf(44));
                check1 = check1.substring(check1.indexOf(40) + 1);
                attempted = attempted.substring(attempted.indexOf(44) + 1);
                final String check2 = attempted.substring(0, attempted.indexOf(41));
                vItemIdd.addElement(check1);
                vItemIdd.addElement(check2);
            }
            while (unattempted.length() > 4) {
                String check1 = unattempted.substring(0, unattempted.indexOf(44));
                check1 = check1.substring(check1.indexOf(40) + 1);
                unattempted = unattempted.substring(unattempted.indexOf(44) + 1);
                final String check2 = unattempted.substring(0, unattempted.indexOf(41));
                vItemIdd.addElement(check1);
                vItemIdd.addElement(check2);
            }
            int s1 = 0;
            int s2 = 0;
            while (s1 < vItemIdd.size()) {
                s1 += 2;
                ++s2;
                final String s3 = Integer.toString(s2);
                for (int j = 0; j < vItemIdd.size(); j += 2) {
                    final String ss = (String) vItemIdd.elementAt(j);
                    final String ss2 = (String) vItemIdd.elementAt(j + 1);
                    if (ss.equals(s3)) {
                        vItemId.addElement(ss2);
                    }
                }
            }
            final Vector vAllDetails = ReportDataBaseLayer.getTextAndType(vItemId);
            final Vector vId = (Vector) vAllDetails.elementAt(0);
            final Vector vTitle = (Vector) vAllDetails.elementAt(1);
            final Vector vText = (Vector) vAllDetails.elementAt(2);
            final Retrive_Item rItem = new Retrive_Item();
            questionall = rItem.RetriveItem(vId, vTitle, vText);
        }
        catch (Exception e) {
            GenerateTestHTML.log.fatal("Exception:");
            GenerateTestHTML.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        return questionall;
    }
    
    public Vector getQuestionAnswerVector(final String student_id, final String asmt_id, final String test_id) {
        final Vector vQans = new Vector();
        final ReportDataBaseLayer ob1 = new ReportDataBaseLayer();
        final Vector vtext = ob1.getAutosaveText(student_id, test_id);
        final InputStream autoasave_text = (InputStream) vtext.elementAt(0);
        try {
            final InputStreamReader input_reader = new InputStreamReader(autoasave_text, "UTF-8");
            final InputSource input_source = new InputSource(input_reader);
            input_source.setEncoding("UTF-8");
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document d = db.parse(input_source);
            final NodeList itemstatelist = d.getElementsByTagName("ITEMSTATE");
            final int countNode = itemstatelist.getLength();
            System.out.println("countNode====" + countNode);
            for (int i = 0; i < countNode; ++i) {
                final Node itemstate = itemstatelist.item(i);
                final Element elementitemstate = (Element)itemstate;
                final String attribute1 = elementitemstate.getAttribute("ItemId");
                final String attribute2 = elementitemstate.getAttribute("ItemType");
                vQans.addElement(attribute1);
                if (attribute2.equals("Multiple Choice")) {
                    final Node state = itemstate.getFirstChild();
                    final String statename = state.getNodeName();
                    final Element elementstate = (Element)state;
                    final NodeList choicelist = elementstate.getElementsByTagName("CHOICE");
                    final Node choice = choicelist.item(0);
                    final Element elementchoice = (Element)choice;
                    final String attribute3 = elementchoice.getAttribute("Id");
                    System.out.println("attribute3====" + attribute3);
                    vQans.addElement(attribute3);
                }
                if (attribute2.equals("True False")) {
                    final Node state = itemstate.getFirstChild();
                    final String statename = state.getNodeName();
                    final Element elementstate = (Element)state;
                    final NodeList choicelist = elementstate.getElementsByTagName("CHOICE");
                    final Node choice = choicelist.item(0);
                    final Element elementchoice = (Element)choice;
                    final String attribute3 = elementchoice.getAttribute("Id");
                    vQans.addElement(attribute3);
                }
                if (attribute2.equals("Multiple Response")) {
                    final Node state = itemstate.getFirstChild();
                    final String statename = state.getNodeName();
                    final Element elementstate = (Element)state;
                    final NodeList choicelist = elementstate.getElementsByTagName("CHOICE");
                    final int countNode2 = choicelist.getLength();
                    final String[] attribute4 = new String[countNode2];
                    for (int i2 = 0; i2 < countNode2; ++i2) {
                        final Node choice2 = choicelist.item(i2);
                        final Element elementchoice2 = (Element)choice2;
                        attribute4[i2] = elementchoice2.getAttribute("Id");
                        System.out.println("attribute3[i1]====" + attribute4[i2]);
                    }
                    vQans.addElement(attribute4);
                }
                if (attribute2.equals("Fill in Blank")) {
                    final Node state = itemstate.getFirstChild();
                    final String statename = state.getNodeName();
                    final Element elementstate = (Element)state;
                    final NodeList choicelist = elementstate.getElementsByTagName("CHOICE");
                    final Node choice = choicelist.item(0);
                    final Element elementchoice = (Element)choice;
                    final String attribute3 = elementchoice.getAttribute("Id");
                    vQans.addElement(attribute3);
                }
                if (attribute2.equals("Matching Question")) {
                    final Node state = itemstate.getFirstChild();
                    final String statename = state.getNodeName();
                    final Element elementstate = (Element)state;
                    final NodeList choicelist = elementstate.getElementsByTagName("CHOICE");
                    final int countNode2 = choicelist.getLength();
                    final Vector v22 = new Vector();
                    final String[] l1 = new String[countNode2];
                    final String[] r1 = new String[countNode2];
                    for (int i3 = 0; i3 < countNode2; ++i3) {
                        final Node choice3 = choicelist.item(i3);
                        final Element elementchoice3 = (Element)choice3;
                        final String value = elementchoice3.getAttribute("Id");
                        l1[i3] = value.substring(0, value.indexOf(45));
                        System.out.println("l1[i1]==ssss==" + l1[i3]);
                        r1[i3] = value.substring(value.indexOf(45) + 1);
                        System.out.println("r1[i1]==ssss==" + r1[i3]);
                    }
                    v22.addElement(l1);
                    v22.addElement(r1);
                    vQans.addElement(v22);
                }
            }
        }
        catch (Exception e) {
            GenerateTestHTML.log.fatal("Exception:");
            GenerateTestHTML.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        System.out.println("vQans=============================" + vQans);
        return vQans;
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    static {
        log = new SimpleLogger((Class)GenerateTestHTML.class, true);
    }
}
