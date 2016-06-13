package learnityasmtserver.assessmentportal.embeddedasmt;

import org.grlea.log.*;
import learnityasmtserver.assessmentportal.dbconnection.*;
import org.apache.ecs.*;
import learnityasmtserver.assessmentportal.utility.*;
import util.*;
import util.Base64;

import javax.servlet.http.*;
import oracle.xml.parser.v2.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import org.apache.ecs.html.*;

public class OutComes extends HttpServlet
{
    public static final SimpleLogger log;
    private static final String SESSION_NAME = "SESSION_NAME";
    private static final String SESSION_ANS = "SESSION_ANS";
    private static final String SESSION_SECTION_NAME = "SESSION_SECTION_NAME";
    private static final String SESSION_ASSESSMENT_NAME = "SESSION_ASSESSMENT_NAME";
    private static final String SESSION_TEMPANS = "SESSION_TEMPANS";
    private static final String SESSION_TIME = "SESSION_TIME";
    private static final String SESSION_FILE_NAME = "SESSION_FILE_NAME";
    String MC_strCorrectIncorrect;
    String TR_strCorrectIncorrect;
    String MCI_strCorrectIncorrect;
    String MR_strCorrectIncorrect;
    String FIB_strCorrectIncorrect;
    String ST_strCorrectIncorrect;
    String MQ_strCorrectIncorrect;
    int totalques;
    int total;
    double totalnumber;
    int totalScore;
    int total_marks;
    String item_id;
    String item_name;
    boolean resultViewToUser;
    
    public OutComes() {
        this.totalques = 0;
        this.total = 0;
        this.totalnumber = 0.0;
        this.totalScore = 0;
        this.total_marks = 0;
        this.item_id = null;
        this.item_name = null;
        this.resultViewToUser = true;
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/xhtml+xml");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final PrintWriter out = response.getWriter();
        final HttpSession mysession = request.getSession(true);
        final String contextName = request.getContextPath().substring(request.getContextPath().indexOf(47) + 1);
        final Vector obj = (Vector)mysession.getAttribute("SESSION_NAME");
        final String student_id = (String)mysession.getAttribute("student_id");
        final String course_id = (String)mysession.getAttribute("unit_id");
        final String identifier = (String)mysession.getAttribute("identifierassessment");
        System.out.println("identifier==outcomesxhtml==nnnn==" + identifier);
        final Hashtable<String, String> hashIdentifier = (Hashtable)mysession.getAttribute("SESSION_COURSE_ITEM");
        final String identifier2 = hashIdentifier.get(course_id);
        Hashtable hAsmttTable = null;
        Hashtable hSectionExtention = null;
        final EmbeddedAsmtDataBaseLayer ob1 = new EmbeddedAsmtDataBaseLayer();
        int asmtTotalQuestion = 0;
        int asmtTotalAtmt = 0;
        int asmtCorrect = 0;
        int asmtMaxScore = 0;
        double asmtScore = 0.0;
        int asmtcutValue = 0;
        String Individual_Section_Id = null;
        String section_id = null;
        String section_time = null;
        String Sectiontms = null;
        String LastSection = null;
        boolean checkEssay = false;
        Vector v = null;
        if (obj == null) {
            v = new Vector();
        }
        else {
            v = obj;
        }
        final Vector vTempAns = (Vector)mysession.getAttribute("SESSION_TEMPANS");
        mysession.removeAttribute("SESSION_TEMPANS");
        final String strResult = request.getParameter("secResult");
        final Vector vAssessment = (Vector)mysession.getAttribute("SESSION_ASSESSMENT_NAME");
        String strPassed = "";
        String strFailed = "";
        final String assessment1_id = (String) ((Vector) ((Vector) vAssessment.elementAt(0)).elementAt(0)).elementAt(1);
        final int instance_count = EmbeddedAsmtDataBaseLayer.GetInstance(student_id, course_id, identifier, assessment1_id);
        final int instance_icrement = instance_count + 1;
        final String[] strCourseAndStudent = EmbeddedAsmtDataBaseLayer.getCourseStudentName(course_id, student_id);
        final XMLDocument manifest = EmbeddedAsmtDataBaseLayer.parse(course_id, "csformat");
        final ImsManifestMeta ims = new ImsManifestMeta(manifest);
        if (vAssessment != null) {
            final Vector vAsmtResult = (Vector) ((Vector) vAssessment.elementAt(0)).elementAt(3);
            strPassed = (String) vAsmtResult.elementAt(0);
            strFailed = (String) vAsmtResult.elementAt(1);
            final Vector vAsmtExtention = (Vector) ((Vector) vAssessment.elementAt(0)).elementAt(4);
            hAsmttTable = (Hashtable) vAsmtExtention.elementAt(0);
        }
        int iSec = 0;
        final String secId = request.getParameter("secID");
        final String slide = request.getParameter("slide");
        if (secId != null) {
            iSec = Integer.parseInt(secId);
        }
        if (strResult == null) {
            if (secId != null) {
                final Object o = mysession.getAttribute("SESSION_ANS");
                Vector vAns = null;
                if (o == null) {
                    vAns = new Vector();
                }
                else {
                    vAns = (Vector)o;
                }
                final Vector questionAll = (Vector) ((Vector) v.elementAt(iSec - 1)).elementAt(0);
                final Vector vA = new Vector();
                for (int i = 0; i < questionAll.size(); ++i) {
                    final Vector questionMatter = (Vector) questionAll.elementAt(i);
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
                            strQuesNo = (String) question.elementAt(0);
                            str = request.getParameter(strQuesNo);
                            vA.addElement(str);
                            break;
                        }
                        case 5: {
                            strQuesNo = (String) question.elementAt(0);
                            str = request.getParameter("q" + strQuesNo);
                            checkEssay = true;
                            vA.addElement(str);
                            break;
                        }
                        case 6: {
                            strQuesNo = (String) question.elementAt(0);
                            str = slide;
                            vA.addElement(str);
                            break;
                        }
                        case 7: {
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
                mysession.setAttribute("SESSION_ANS", (Object)vAns);
            }
        }
        else if (secId != null) {
            final Object o = mysession.getAttribute("SESSION_ANS");
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
            mysession.setAttribute("SESSION_ANS", (Object)vAns);
        }
        final Object o = mysession.getAttribute("SESSION_ANS");
        Vector vAns = null;
        if (obj == null) {
            vAns = new Vector();
        }
        else {
            vAns = (Vector)o;
        }
        final Vector oSection = (Vector)mysession.getAttribute("SESSION_SECTION_NAME");
        final Vector oSectionTime = (Vector)mysession.getAttribute("SESSION_TIME");
        Vector vSectionName = null;
        Vector vSectionTime = null;
        if (oSection == null) {
            vSectionName = new Vector();
            vSectionTime = new Vector();
        }
        else {
            vSectionName = oSection;
            vSectionTime = oSectionTime;
        }
        mysession.removeAttribute("SESSION_ANS");
        mysession.removeAttribute("SESSION_NAME");
        mysession.removeAttribute("SESSION_SECTION_NAME");
        mysession.removeAttribute("SESSION_ASSESSMENT_NAME");
        mysession.removeAttribute("SESSION_TIME");
        final StringBuffer strFeedbacktop = new StringBuffer();
        final StringBuffer strdiv = new StringBuffer();
        final StringBuffer strFeedback = new StringBuffer();
        final StringBuffer strAssessmentSummary = new StringBuffer();
        final Calendar calendar = new GregorianCalendar();
        final Date trialTime = new Date();
        calendar.setTime(trialTime);
        final String[] months = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        final Random rnd = new Random();
        int rand = rnd.nextInt();
        if (rand < 1) {
            rand *= -1;
        }
        final String ls = EmbeddedAsmtDataBaseLayer.getLearning_Style(student_id);
        final String strTime = calendar.get(11) + ":" + calendar.get(12) + ":" + calendar.get(13);
        final String strTimeFull = calendar.get(11) + ":" + calendar.get(12) + ":" + calendar.get(13);
        final String strDate = months[calendar.get(2)] + " " + calendar.get(5) + ", " + calendar.get(1);
        String javaScript = "";
        final String student_name = ob1.studentName(student_id);
        final Doctype doc1 = new Doctype("html", "\"-//W3C//DTD XHTML 1.0 Strict//EN\"", "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"");
        final Script sc = new Script();
        final Html html1 = new Html();
        html1.addAttribute("xlink", "http://www.w3.org/1999/xlink");
        html1.addAttribute("math", "http://www.w3.org/1998/Math/MathML");
        html1.addAttribute("xmlns", "http://www.w3.org/1999/xhtml");
        final Head head1 = new Head();
        html1.addElement((Element)head1.addElement((Element)new Title("Test Report")).addElement((Element)sc.setLanguage("JavaScript")));
        final String strCompliationMsg = (String)(String)hAsmttTable.get("result_message");
        strdiv.append("\n  <style type=\"text/css\" id=\"internalStyle\">");
        strdiv.append("\n  #resultSection {");
        strdiv.append("\n  height:25");
        strdiv.append("\n  width:639px;");
        strdiv.append("\n  overflow:auto;");
        strdiv.append("\n\t}");
        strdiv.append("\n\t</style>");
        head1.addElement(strdiv.toString());
        strFeedbacktop.append("\n<body bgcolor = '#FFFFFF' background='hpbkgd.gif' onload =\"load();\">");
        strFeedbacktop.append("\n<br/>");
        strFeedbacktop.append("\n<center><Font size=\"5\"><B><em>Powered by</em></B></Font>");
        strFeedbacktop.append("\n<Font size=\"5\" color=\"#CC0000\">LearnITy&#8482;</Font><Font size=\"5\"><B><em>Assessment Server</em></B></Font></center>");
        strFeedbacktop.append("\n<br/>");
        strFeedbacktop.append("\n<br/>");
        if (strCompliationMsg != null && !strCompliationMsg.equals("")) {
            strFeedbacktop.append("\n<center><left><Font size=\"3\" color=\"#2374bb\"><B>" + strCompliationMsg + "</B></Font></left></center>");
            strFeedbacktop.append("\n<br/>");
        }
        String strFeedHeading = (String)hAsmttTable.get("feedback_info");
        if (strFeedHeading == null) {
            strFeedHeading = "";
        }
        final TBody tbodyInfo = new TBody();
        final Table tblTopInformation = new Table().setBorder(0).setCellPadding(0).setCellSpacing(0).setWidth("100%").addElement((Element)new Caption().setAlign("top").addElement((Element)new B().addElement((Element)new Font().setColor("336699").addElement(strFeedHeading))));
        final String assessment_name = ims.getLearningResourceType(identifier);
        final String cname = (String)hAsmttTable.get("cname");
        if (cname != null && cname.equals("true")) {
            tbodyInfo.addElement((Element)new TRExtension().addRowUserForAsmt("<B>User ID</B>", student_id, "<B>User Name</B>", student_name));
        }
        if (cname != null && cname.equals("true")) {
            tbodyInfo.addElement((Element)new TRExtension().addRowUserForAsmt("<B>Unit Name</B>", strCourseAndStudent[1], "<B>Date: </B>" + strDate, "<B>Time:</B> " + strTime));
        }
        if (cname != null && cname.equals("true")) {
            tbodyInfo.addElement((Element)new TRExtension().addRowUserForAsmt("<B>Assessment Name</B>", assessment_name, "<B>Confirmation no. </B>", "" + rand));
        }
        String status = "";
        int subTotal = 0;
        double subScore = 0.0;
        String answer = null;
        final String feedbackheading = (String)hAsmttTable.get("feedbackheading");
        final TBody tbodySectionTop = new TBody();
        final Table tblSectionTop = new Table().setBorder(0).setCellPadding(0).setCellSpacing(0);
        tbodySectionTop.addElement((Element)new TR().addElement((Element)new TD().setHeight(25).setColSpan(2).setAlign("center").setBgColor("#336699").addElement(feedbackheading)));
        for (int j = 0; j < vAns.size(); ++j) {
            final Vector questionAll2 = (Vector) ((Vector) v.elementAt(j)).elementAt(0);
            final Vector vA2 = (Vector) vAns.elementAt(j);
            final Vector strSectionName = (Vector) vSectionName.elementAt(j);
            final Vector strSectionTime = (Vector) vSectionTime.elementAt(j);
            if (j == vAns.size() - 1) {
                final Vector onlyLastSection = (Vector) vSectionName.elementAt(j);
                LastSection = (String) onlyLastSection.elementAt(1);
            }
            final Vector vSectionResult = (Vector) strSectionName.elementAt(3);
            final Vector vSectionExtention = (Vector) strSectionName.elementAt(4);
            if (vSectionExtention != null) {
                hSectionExtention = (Hashtable) vSectionExtention.elementAt(0);
            }
            final String strSecPassed = (String) vSectionResult.elementAt(0);
            final String strSecFailed = (String) vSectionResult.elementAt(1);
            section_id = strSectionName.elementAt(1) + ". " + strSectionName.elementAt(0);
            final String sec_timeall = (String) strSectionTime.elementAt(1);
            Individual_Section_Id = (String) strSectionName.elementAt(1);
            section_time = (String) strSectionTime.elementAt(1);
            if (j + 1 <= vAns.size() - 1) {
                final Vector strSectionTime2 = (Vector) vSectionTime.elementAt(j + 1);
                Sectiontms = (String) strSectionTime2.elementAt(1);
            }
            final TBody tbodySectionMid = new TBody();
            final Table tblSectionMid = new Table().setBorder(0).setCellPadding(0).setCellSpacing(0).setWidth("100%");
            final String strComplation_message = (String) hSectionExtention.get("complation_message");
            if (strComplation_message != null && !strComplation_message.equals("")) {
                tbodySectionMid.addElement((Element)new TR().addElement((Element)new TD().setHeight(25).setColSpan(2).setAlign("center").addElement(strComplation_message)));
            }
            final String strFeedback_heading = (String) hSectionExtention.get("feedback_heading");
            if (strFeedback_heading != null && !strFeedback_heading.equals("")) {
                tbodySectionMid.addElement((Element)new TRExtension().addUserForSection(strFeedback_heading, "" + strSectionName.elementAt(0)));
            }
            final String strItemSum = (String) hSectionExtention.get("itemfeedbackheading");
            final TBody tbodyItemSum = new TBody();
            final Table tblItemSummary = new Table().setBorder(1).setCellPadding(1).setCellSpacing(1).addElement((Element)new Caption().setAlign("top").addElement((Element)new B().addElement((Element)new Font().setColor("336699").addElement(strItemSum))));
            boolean boolItem = false;
            final String strItem_id = (String) hSectionExtention.get("item_id");
            final String stritem_name = (String) hSectionExtention.get("item_name");
            final String stritem_type = (String) hSectionExtention.get("item_type");
            final String stritem_answer = (String) hSectionExtention.get("item_answer");
            final String item_obmarks = (String) hSectionExtention.get("item_obmarks");
            final String stritem_marks = (String) hSectionExtention.get("item_marks");
            final String stritem_feedback = (String) hSectionExtention.get("item_feedback");
            final TR trItem = new TR();
            if (strItem_id != null && !strItem_id.equals("")) {
                boolItem = true;
                trItem.addElement((Element)new TD().setHeight(25).addElement((Element)new B().addElement(strItem_id)));
            }
            if (stritem_name != null && !stritem_name.equals("")) {
                boolItem = true;
                trItem.addElement((Element)new TD().setHeight(25).addElement((Element)new B().addElement(stritem_name)));
            }
            if (stritem_type != null && !stritem_type.equals("")) {
                boolItem = true;
                trItem.addElement((Element)new TD().setHeight(25).addElement((Element)new B().addElement(stritem_type)));
            }
            if (stritem_answer != null && !stritem_answer.equals("")) {
                boolItem = true;
                trItem.addElement((Element)new TD().setHeight(25).addElement((Element)new B().addElement(stritem_answer)));
            }
            if (stritem_marks != null && !stritem_marks.equals("")) {
                boolItem = true;
                trItem.addElement((Element)new TD().setHeight(25).addElement((Element)new B().addElement(stritem_marks)));
            }
            if (item_obmarks != null && !item_obmarks.equals("")) {
                boolItem = true;
                trItem.addElement((Element)new TD().setHeight(25).addElement((Element)new B().addElement(item_obmarks)));
            }
            if (stritem_feedback != null && !stritem_feedback.equals("")) {
                boolItem = true;
                trItem.addElement((Element)new TD().setHeight(25).addElement((Element)new B().addElement(stritem_feedback)));
            }
            tbodyItemSum.addElement((Element)trItem);
            this.totalques = 0;
            this.total = 0;
            this.totalnumber = 0.0;
            this.totalScore = 0;
            if (j == vAns.size() - 1 && vSectionName != null) {
                EmbeddedAsmtDataBaseLayer.AssessmentUsage(student_id, course_id, assessment1_id, Individual_Section_Id, section_time, strTimeFull, instance_icrement);
            }
            else {
                EmbeddedAsmtDataBaseLayer.AssessmentUsage(student_id, course_id, assessment1_id, Individual_Section_Id, section_time, Sectiontms, instance_icrement);
            }
            int attempted_ques = 0;
            for (int k = 0; k < questionAll2.size(); ++k) {
                final Vector questionMatter2 = (Vector) questionAll2.elementAt(k);
                final Vector res2 = (Vector) questionMatter2.elementAt(0);
                final Vector question2 = (Vector) questionMatter2.elementAt(1);
                final String item_score = (String) res2.elementAt(1);
                final Integer iTitle2 = (Integer) res2.elementAt(0);
                String str5 = "";
                String ans = "";
                String ans_mq = "";
                Vector vItemAns = new Vector();
                TR TR = null;
                final int l = k + 1;
                double item_obtain_marks = 0.0;
                switch (iTitle2) {
                    case 0: {
                        str5 = (String) vA2.elementAt(k);
                        TR = this.MultipleChoiceHTML(res2, question2, str5, l, hSectionExtention);
                        final String itemMC = (String) question2.elementAt(4);
                        vItemAns = (Vector) question2.elementAt(2);
                        for (int i3 = 0; i3 < vItemAns.size(); ++i3) {
                            final Vector an = (Vector) vItemAns.elementAt(i3);
                            final Character str6 = (Character) an.elementAt(0);
                            final String str7 = str6.toString();
                            if (str7.equals(str5)) {
                                ans = (String) an.elementAt(1);
                            }
                        }
                        if (this.MC_strCorrectIncorrect.equals("correct")) {
                            item_obtain_marks = Double.parseDouble((String) res2.elementAt(1));
                        }
                        if (str5 != null && !str5.equals("") && this.MC_strCorrectIncorrect.equals("incorrect") && res2.size() > 3) {
                            item_obtain_marks = Double.parseDouble((String) res2.elementAt(3));
                        }
                        if (str5 != null && !str5.equals("")) {
                            EmbeddedAsmtDataBaseLayer.ItemAnalysis(student_id, course_id, identifier, assessment1_id, Individual_Section_Id, itemMC, this.MC_strCorrectIncorrect, str5, item_obtain_marks, instance_icrement);
                            EmbeddedAsmtDataBaseLayer.Assessment_item_data(itemMC, this.MC_strCorrectIncorrect, ans, item_score, item_obtain_marks, Individual_Section_Id);
                            ++attempted_ques;
                        }
                        else {
                            EmbeddedAsmtDataBaseLayer.ItemAnalysis(student_id, course_id, identifier, assessment1_id, Individual_Section_Id, itemMC, this.MC_strCorrectIncorrect = "NotAttemped", str5, item_obtain_marks, instance_icrement);
                            EmbeddedAsmtDataBaseLayer.Assessment_item_data(itemMC, this.MC_strCorrectIncorrect, ans, item_score, item_obtain_marks, Individual_Section_Id);
                        }
                        if (boolItem) {
                            tbodyItemSum.addElement((Element)TR);
                            break;
                        }
                        break;
                    }
                    case 1: {
                        final String[] str8 = (String[]) vA2.elementAt(k);
                        TR = this.MultipleResponseHTML(res2, question2, str8, l, hSectionExtention);
                        final String itemMR = (String) question2.elementAt(4);
                        vItemAns = (Vector) question2.elementAt(2);
                        String ans1_c = "";
                        if (str8 != null && !str8.equals("")) {
                            final String[] ans2 = new String[str8.length];
                            for (int i4 = 0; i4 < str8.length; ++i4) {
                                final String astr = str8[i4];
                                for (int i5 = 0; i5 < vItemAns.size(); ++i5) {
                                    final Vector an2 = (Vector) vItemAns.elementAt(i5);
                                    final Character str9 = (Character) an2.elementAt(0);
                                    final String str10 = str9.toString();
                                    if (str10.equals(astr)) {
                                        ans2[i4] = (String) an2.elementAt(1);
                                    }
                                }
                            }
                            ans1_c = this.convert(ans2);
                        }
                        if (this.MR_strCorrectIncorrect.equals("correct")) {
                            item_obtain_marks = Double.parseDouble((String) res2.elementAt(1));
                        }
                        if (str8 != null && !str8.equals("") && this.MR_strCorrectIncorrect.equals("incorrect") && res2.size() > 3) {
                            item_obtain_marks = Double.parseDouble((String) res2.elementAt(3));
                        }
                        if (str8 != null && !str8.equals("")) {
                            final String str11 = this.convert(str8);
                            EmbeddedAsmtDataBaseLayer.ItemAnalysis(student_id, course_id, identifier, assessment1_id, Individual_Section_Id, itemMR, this.MR_strCorrectIncorrect, str11, item_obtain_marks, instance_icrement);
                            EmbeddedAsmtDataBaseLayer.Assessment_item_data(itemMR, this.MR_strCorrectIncorrect, ans1_c, item_score, item_obtain_marks, Individual_Section_Id);
                            ++attempted_ques;
                        }
                        else {
                            this.MR_strCorrectIncorrect = "NotAttemped";
                            final String str12 = null;
                            EmbeddedAsmtDataBaseLayer.ItemAnalysis(student_id, course_id, identifier, assessment1_id, Individual_Section_Id, itemMR, this.MR_strCorrectIncorrect, str12, item_obtain_marks, instance_icrement);
                            EmbeddedAsmtDataBaseLayer.Assessment_item_data(itemMR, this.MR_strCorrectIncorrect, ans1_c, item_score, item_obtain_marks, Individual_Section_Id);
                        }
                        if (boolItem) {
                            tbodyItemSum.addElement((Element)TR);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        str5 = (String) vA2.elementAt(k);
                        final String strTime2 = calendar.get(10) + ":" + calendar.get(12);
                        TR = this.FillBlanks(res2, question2, str5, l, hSectionExtention);
                        final String itemfib = (String) question2.elementAt(3);
                        vItemAns = (Vector) question2.elementAt(2);
                        ans = (String) vItemAns.elementAt(0);
                        if (this.FIB_strCorrectIncorrect.equals("correct")) {
                            item_obtain_marks = Double.parseDouble((String) res2.elementAt(1));
                        }
                        if (str5 != null && !str5.equals("") && this.FIB_strCorrectIncorrect.equals("incorrect") && res2.size() > 3) {
                            item_obtain_marks = Double.parseDouble((String) res2.elementAt(3));
                        }
                        if (str5 != null && !str5.equals("")) {
                            EmbeddedAsmtDataBaseLayer.ItemAnalysis(student_id, course_id, identifier, assessment1_id, Individual_Section_Id, itemfib, this.FIB_strCorrectIncorrect, str5, item_obtain_marks, instance_icrement);
                            EmbeddedAsmtDataBaseLayer.Assessment_item_data(itemfib, this.FIB_strCorrectIncorrect, ans, item_score, item_obtain_marks, Individual_Section_Id);
                            ++attempted_ques;
                        }
                        else {
                            EmbeddedAsmtDataBaseLayer.ItemAnalysis(student_id, course_id, identifier, assessment1_id, Individual_Section_Id, itemfib, this.FIB_strCorrectIncorrect = "NotAttemped", str5, item_obtain_marks, instance_icrement);
                            EmbeddedAsmtDataBaseLayer.Assessment_item_data(itemfib, this.FIB_strCorrectIncorrect, ans, item_score, item_obtain_marks, Individual_Section_Id);
                        }
                        if (boolItem) {
                            tbodyItemSum.addElement((Element)TR);
                            break;
                        }
                        break;
                    }
                    case 3: {
                        str5 = (String) vA2.elementAt(k);
                        TR = this.MultipleChoiceHTML(res2, question2, str5, l, hSectionExtention);
                        final String itemTF = (String) question2.elementAt(4);
                        vItemAns = (Vector) question2.elementAt(2);
                        for (int i6 = 0; i6 < vItemAns.size(); ++i6) {
                            final Vector an2 = (Vector) vItemAns.elementAt(i6);
                            final Character str9 = (Character) an2.elementAt(0);
                            final String str10 = str9.toString();
                            if (str10.equals(str5)) {
                                ans = (String) an2.elementAt(1);
                            }
                        }
                        if (this.MC_strCorrectIncorrect.equals("correct")) {
                            item_obtain_marks = Double.parseDouble((String) res2.elementAt(1));
                        }
                        if (str5 != null && !str5.equals("") && this.MC_strCorrectIncorrect.equals("incorrect") && res2.size() > 3) {
                            item_obtain_marks = Double.parseDouble((String) res2.elementAt(3));
                        }
                        if (str5 != null && !str5.equals("")) {
                            EmbeddedAsmtDataBaseLayer.ItemAnalysis(student_id, course_id, identifier, assessment1_id, Individual_Section_Id, itemTF, this.MC_strCorrectIncorrect, str5, item_obtain_marks, instance_icrement);
                            EmbeddedAsmtDataBaseLayer.Assessment_item_data(itemTF, this.MC_strCorrectIncorrect, ans, item_score, item_obtain_marks, Individual_Section_Id);
                            ++attempted_ques;
                        }
                        else {
                            EmbeddedAsmtDataBaseLayer.ItemAnalysis(student_id, course_id, identifier, assessment1_id, Individual_Section_Id, itemTF, this.MC_strCorrectIncorrect = "NotAttemped", str5, item_obtain_marks, instance_icrement);
                            EmbeddedAsmtDataBaseLayer.Assessment_item_data(itemTF, this.MC_strCorrectIncorrect, ans, item_score, item_obtain_marks, Individual_Section_Id);
                        }
                        if (boolItem) {
                            tbodyItemSum.addElement((Element)TR);
                            break;
                        }
                        break;
                    }
                    case 4: {
                        str5 = (String) vA2.elementAt(k);
                        TR = this.MultipleChoiceImage(res2, question2, str5, l, hSectionExtention);
                        final String itemMCI = (String) question2.elementAt(4);
                        vItemAns = (Vector) question2.elementAt(2);
                        for (int i7 = 0; i7 < vItemAns.size(); ++i7) {
                            final Vector an3 = (Vector) vItemAns.elementAt(i7);
                            final Character str13 = (Character) an3.elementAt(0);
                            final String str14 = str13.toString();
                            if (str14.equals(str5)) {
                                ans = (String) an3.elementAt(1);
                            }
                        }
                        if (this.MCI_strCorrectIncorrect.equals("correct")) {
                            item_obtain_marks = Integer.parseInt((String) res2.elementAt(1));
                        }
                        if (str5 != null && !str5.equals("")) {
                            EmbeddedAsmtDataBaseLayer.ItemAnalysis(student_id, course_id, identifier, assessment1_id, Individual_Section_Id, itemMCI, this.MCI_strCorrectIncorrect, str5, item_obtain_marks, instance_icrement);
                            EmbeddedAsmtDataBaseLayer.Assessment_item_data(itemMCI, this.MCI_strCorrectIncorrect, ans, item_score, item_obtain_marks, Individual_Section_Id);
                            ++attempted_ques;
                        }
                        else {
                            EmbeddedAsmtDataBaseLayer.ItemAnalysis(student_id, course_id, identifier, assessment1_id, Individual_Section_Id, itemMCI, this.MCI_strCorrectIncorrect = "NotAttemped", str5, item_obtain_marks, instance_icrement);
                            EmbeddedAsmtDataBaseLayer.Assessment_item_data(itemMCI, this.MCI_strCorrectIncorrect, ans, item_score, item_obtain_marks, Individual_Section_Id);
                        }
                        if (boolItem) {
                            tbodyItemSum.addElement((Element)TR);
                            break;
                        }
                        break;
                    }
                    case 5: {
                        str5 = (String) vA2.elementAt(k);
                        checkEssay = true;
                        answer = str5;
                        this.item_id = (String) question2.elementAt(0);
                        this.item_name = (String) question2.elementAt(1);
                        final String temp_mark = (String) res2.elementAt(1);
                        final int total_mark = Integer.parseInt(temp_mark);
                        final String assessment_id = ims.getLearningResourceType(identifier);
                        EmbeddedAsmtDataBaseLayer.essay_type_data(this.item_id, this.item_name, str5, total_mark, Individual_Section_Id);
                        TR = this.EssayType(res2, question2, str5, l, hSectionExtention);
                        if (boolItem) {
                            tbodyItemSum.addElement((Element)TR);
                            break;
                        }
                        break;
                    }
                    case 6: {
                        str5 = (String) vA2.elementAt(k);
                        TR = this.SliderType(res2, question2, str5, l, hSectionExtention);
                        final String itemST = (String) question2.elementAt(4);
                        vItemAns = (Vector) question2.elementAt(2);
                        if (this.ST_strCorrectIncorrect.equals("correct")) {
                            item_obtain_marks = Double.parseDouble((String) res2.elementAt(1));
                        }
                        if (str5 != null && !str5.equals("") && !str5.equals("NaN") && this.ST_strCorrectIncorrect.equals("incorrect") && res2.size() > 3) {
                            item_obtain_marks = Double.parseDouble((String) res2.elementAt(3));
                        }
                        if (str5 != null && !str5.equals("") && !str5.equals("NaN")) {
                            EmbeddedAsmtDataBaseLayer.ItemAnalysis(student_id, course_id, identifier, assessment1_id, Individual_Section_Id, itemST, this.ST_strCorrectIncorrect, str5, item_obtain_marks, instance_icrement);
                            ++attempted_ques;
                        }
                        else {
                            EmbeddedAsmtDataBaseLayer.ItemAnalysis(student_id, course_id, identifier, assessment1_id, Individual_Section_Id, itemST, this.ST_strCorrectIncorrect = "NotAttemped", str5, item_obtain_marks, instance_icrement);
                        }
                        if (boolItem) {
                            tbodyItemSum.addElement((Element)TR);
                            break;
                        }
                        break;
                    }
                    case 7: {
                        final Vector vMqGivenAns = (Vector) vA2.elementAt(k);
                        final String[] leftpAns11 = (String[]) vMqGivenAns.elementAt(0);
                        final String[] rightAns11 = (String[]) vMqGivenAns.elementAt(1);
                        for (int r1 = 0; r1 < leftpAns11.length; ++r1) {
                            ans_mq = ans_mq + "\r\n " + leftpAns11[r1] + " - " + rightAns11[r1] + ",";
                        }
                        final int iScore = Integer.parseInt((String) res2.elementAt(1));
                        String srtNegscore = "";
                        if (res2.size() > 3) {
                            srtNegscore = (String) res2.elementAt(3);
                        }
                        final Vector vCorrectAnsmq = (Vector) question2.elementAt(3);
                        final String itemMQ = (String) question2.elementAt(4);
                        boolean b1 = true;
                        boolean c1 = false;
                        for (int r2 = 0; r2 < leftpAns11.length; ++r2) {
                            if (!leftpAns11[r2].equals("0")) {
                                c1 = true;
                            }
                            if (!rightAns11[r2].equals("0")) {
                                c1 = true;
                            }
                            for (int r3 = 0; r3 < vCorrectAnsmq.size(); ++r3) {
                                final String[] coAns11 = (String[]) vCorrectAnsmq.elementAt(r3);
                                if (leftpAns11[r2].equals(coAns11[0])) {
                                    b1 = rightAns11[r2].equals(coAns11[1]);
                                }
                            }
                            if (!b1) {
                                break;
                            }
                        }
                        if (c1) {
                            ++attempted_ques;
                            if (b1) {
                                item_obtain_marks += iScore;
                                this.MQ_strCorrectIncorrect = "correct";
                            }
                            else {
                                if (!srtNegscore.equals("")) {
                                    final double intNegscore = Double.parseDouble(srtNegscore);
                                    item_obtain_marks += intNegscore;
                                }
                                this.MQ_strCorrectIncorrect = "incorrect";
                            }
                            EmbeddedAsmtDataBaseLayer.ItemAnalysis(student_id, course_id, identifier, assessment1_id, Individual_Section_Id, itemMQ, this.MQ_strCorrectIncorrect, ans_mq, item_obtain_marks, instance_icrement);
                            EmbeddedAsmtDataBaseLayer.Assessment_item_data(itemMQ, this.MQ_strCorrectIncorrect, ans_mq, item_score, item_obtain_marks, Individual_Section_Id);
                        }
                        else {
                            EmbeddedAsmtDataBaseLayer.ItemAnalysis(student_id, course_id, identifier, assessment1_id, Individual_Section_Id, itemMQ, this.MQ_strCorrectIncorrect = "NotAttemped", ans_mq, item_obtain_marks, instance_icrement);
                            EmbeddedAsmtDataBaseLayer.Assessment_item_data(itemMQ, this.MQ_strCorrectIncorrect, ans_mq, item_score, item_obtain_marks, Individual_Section_Id);
                        }
                        TR = this.MatchingQuesHTML(res2, question2, vMqGivenAns, ans_mq, l, hSectionExtention);
                        if (boolItem) {
                            tbodyItemSum.addElement((Element)TR);
                            break;
                        }
                        break;
                    }
                }
            }
            tblItemSummary.addElement((Element)tbodyItemSum);
            OutComes.log.debug("========v==============" + v);
            status = ((Integer.parseInt((String) ((Vector) v.elementAt(j)).elementAt(1)) <= this.totalnumber) ? strSecPassed : strSecFailed);
            OutComes.log.debug("========status==============" + status);
            final String summary_heading = (String)hSectionExtention.get("summary_heading");
            final TBody tbodySecSum = new TBody();
            final Table tblSectionSummary = new Table().setBorder(0).setCellPadding(1).setCellSpacing(1).setWidth("100%").addElement((Element)new Caption().setAlign("top").addElement((Element)new B().addElement((Element)new Font().setColor("336699").addElement(summary_heading))));
            final String total_questions = (String)hSectionExtention.get("total_questions");
            final String total_correct = (String)hSectionExtention.get("total_correct");
            final String percent_correct = (String)hSectionExtention.get("percent_correct");
            final String total_incorrect = (String)hSectionExtention.get("total_incorrect");
            final String percent_incorrect = (String)hSectionExtention.get("percent_incorrect");
            final String total_no = (String)hSectionExtention.get("total_marks");
            final String pass_marks = (String)hSectionExtention.get("pass_marks");
            final String obtained_marks = (String)hSectionExtention.get("obtained_marks");
            final String percent_marks = (String)hSectionExtention.get("percent_marks");
            final String feedback_status = (String)hSectionExtention.get("feedback_status");
            if (total_questions != null && !total_questions.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + total_questions + "</B>", "" + questionAll2.size()));
            }
            if (total_questions != null && !total_questions.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + total_correct + "</B>", "" + this.total));
            }
            if (percent_correct != null && !percent_correct.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + percent_correct + "</B>", "" + Math.floor(this.total * 1.0 / questionAll2.size() * 100.0)));
            }
            if (total_incorrect != null && !total_incorrect.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + total_incorrect + "</B>", "" + (questionAll2.size() - this.total)));
            }
            if (percent_incorrect != null && !percent_incorrect.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + percent_incorrect + "</B>", "" + Math.floor((questionAll2.size() - this.total) * 1.0 / questionAll2.size() * 100.0)));
            }
            if (total_no != null && !total_no.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + total_no + "</B>", "" + this.totalScore));
            }
            if (pass_marks != null && !pass_marks.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + pass_marks + "</B>", "" + Integer.parseInt((String) ((Vector) v.elementAt(j)).elementAt(1))));
            }
            if (obtained_marks != null && !obtained_marks.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + obtained_marks + "</B>", "" + this.totalnumber));
            }
            if (percent_marks != null && !percent_marks.equals("")) {
                if (this.totalnumber >= 0.0) {
                    tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + percent_marks + "</B>", "" + Math.floor(this.totalnumber * 1.0 / this.totalScore * 100.0)));
                }
                else {
                    tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + percent_marks + "</B>", ""));
                }
            }
            if (feedback_status != null && !feedback_status.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + feedback_status + "</B>", status));
            }
            status = ((Integer.parseInt((String) ((Vector) v.elementAt(j)).elementAt(1)) <= this.totalnumber) ? "passed" : "failed");
            this.totalques += questionAll2.size();
            if (Integer.parseInt((String) ((Vector) v.elementAt(j)).elementAt(1)) <= this.totalnumber) {
                final String[] strResourceRef = ims.getResourceIdentifierForItemRef(ls);
                final String location = Base64.encode(ims.getResourceLocation(strResourceRef[0]));
                final String new_identifier = strResourceRef[1];
                final String strSuccess_msg = (String)hSectionExtention.get("success_message");
                final TR trSMS = new TR();
                if (strSuccess_msg != null && !strSuccess_msg.equals("")) {
                    trSMS.addElement((Element)new TD().setHeight(25).addElement(strSuccess_msg));
                }
                final Vector vNavigation = (Vector)hSectionExtention.get("navigation_link");
                if (vNavigation != null) {
                    final String[] strNavigation = new String[vNavigation.size()];
                    for (int m = 0; m < vNavigation.size(); ++m) {
                        final Vector vLabel = (Vector) vNavigation.elementAt(m);
                        final String strLabel = (String) vLabel.elementAt(0);
                        strNavigation[m] = (String) vLabel.elementAt(1);
                    }
                    final String strSuccesslink = strNavigation[0];
                    final String strSuccesslabel = strNavigation[1];
                    if (strSuccesslink != null && !strSuccesslink.equals("") && strSuccesslink != null && !strSuccesslink.equals("")) {
                        trSMS.addElement((Element)new TD().setHeight(25).addElement((Element)new NOBR().addElement("<a href=\"./delivery.SequencingEngine?resource='" + location + "'&identifier='" + Base64.encode(new_identifier) + "\" target=\"Content\" ><Font size=2>" + strSuccesslabel + "</Font></a>")));
                    }
                    tbodySectionMid.addElement((Element)trSMS);
                }
            }
            else {
                final String[] strResourceRef = ims.getResourceIdentifierForItemRef(ls);
                final String location = Base64.encode(ims.getResourceLocation(strResourceRef[0]));
                final String new_identifier = strResourceRef[1];
                final String strfailuremessage = (String)hSectionExtention.get("failuremessage");
                final TR trSMS = new TR();
                if (strfailuremessage != null && !strfailuremessage.equals("")) {
                    trSMS.addElement((Element)new TD().setHeight(25).addElement(strfailuremessage));
                }
                final Vector vNavigation = (Vector)hSectionExtention.get("remediation_link");
                if (vNavigation != null) {
                    final String[] strNavigation = new String[vNavigation.size()];
                    for (int m = 0; m < vNavigation.size(); ++m) {
                        final Vector vLabel = (Vector) vNavigation.elementAt(m);
                        final String strLabel = (String) vLabel.elementAt(0);
                        strNavigation[m] = (String) vLabel.elementAt(1);
                    }
                    if (strNavigation[0] != null && !strNavigation[0].equals("") && strNavigation[1] != null && !strNavigation[1].equals("")) {
                        trSMS.addElement((Element)new TD().setHeight(25).addElement((Element)new NOBR().addElement("<a href=\"./delivery.SequencingEngine?resource='" + location + "'&identifier='" + Base64.encode(new_identifier) + "\" target=\"Content\" ><Font size=2>" + strNavigation[1] + "</Font></a>")));
                    }
                }
                tbodySectionMid.addElement((Element)trSMS);
            }
            tblSectionMid.addElement((Element)tbodySectionMid);
            tbodySectionTop.addElement((Element)new TR().addElement((Element)new TD().setHeight(25).setColSpan(2).addElement("<br/>")));
            tbodySectionTop.addElement((Element)new TR().addElement((Element)new TD().setHeight(25).setColSpan(2).addElement((Element)tblSectionMid)));
            tblSectionSummary.addElement((Element)tbodySecSum);
            tbodySectionTop.addElement((Element)new TR().addElement((Element)new TD().setHeight(25).setColSpan(2).addElement((Element)tblSectionSummary)));
            tbodySectionTop.addElement((Element)new TR().addElement((Element)new TD().setHeight(25).setColSpan(2).addElement((Element)tblItemSummary)));
            tblSectionTop.addElement((Element)tbodySectionTop);
            asmtTotalQuestion += this.totalques;
            asmtTotalAtmt += this.totalques;
            asmtCorrect += this.total;
            asmtMaxScore += this.totalScore;
            asmtScore += this.totalnumber;
            asmtcutValue += Integer.parseInt((String) ((Vector) v.elementAt(j)).elementAt(1));
            final int totalnoofquestion = this.totalques;
            final int attempt = this.totalques;
            final int correctans = this.total;
            final int maxscore = this.totalScore;
            subTotal += this.totalScore;
            subScore += this.totalnumber;
            final int score = (int)Math.floor(this.totalnumber * 1.0 / this.totalScore * 100.0);
            final String collection = contextName + "_" + student_id + "_" + course_id;
            final String assessment_id2 = ims.getLearningResourceType(identifier);
            EmbeddedAsmtDataBaseLayer.updateAssessmentUsermodel(student_id, strTime);
            EmbeddedAsmtDataBaseLayer.updateAssessmentSectionData(strTime);
            EmbeddedAsmtDataBaseLayer.updateAllAssessmentSectionData(Individual_Section_Id, this.totalScore, this.totalnumber, totalnoofquestion, attempted_ques, correctans);
            String strSectionid = "";
            if (strSectionName.size() > 1) {
                strSectionid = (String) strSectionName.elementAt(1);
            }
            if (subTotal > 100) {
                subScore = Double.valueOf(Math.floor(subScore * 1.0 / subTotal * 100.0));
                subTotal = 100;
            }
        }
        final String strAssessmentCutValue = (String) ((Vector) vAssessment.elementAt(0)).elementAt(1);
        if (strAssessmentCutValue != null && !strAssessmentCutValue.equals("")) {
            asmtcutValue = Integer.parseInt(strAssessmentCutValue);
        }
        final String assessment_id3 = ims.getLearningResourceType(identifier);
        final String a_name = (String) ((Vector) ((Vector) vAssessment.elementAt(0)).elementAt(0)).elementAt(0);
        status = ((asmtcutValue <= asmtScore) ? "Passed" : "Failed");
        EmbeddedAsmtDataBaseLayer.scormUpdation(course_id, student_id, identifier, "" + subScore, "" + subTotal, "" + asmtcutValue, status);
        EmbeddedAsmtDataBaseLayer.confirmationResult(student_id, course_id, identifier, assessment_id3, a_name, rand, asmtMaxScore, asmtScore, asmtcutValue, status);
        if (this.resultViewToUser) {
            status = ((asmtcutValue <= asmtScore) ? strPassed : strFailed);
            if (asmtcutValue <= asmtScore) {
                final String[] strResourceRef2 = ims.getResourceIdentifierForItemRef(ls);
                final String location2 = Base64.encode(ims.getResourceLocation(strResourceRef2[0]));
                final String new_identifier2 = strResourceRef2[1];
                final String strSuccess_msg2 = (String)hAsmttTable.get("success_msg");
                final TR trSMS2 = new TR();
                if (strSuccess_msg2 != null && !strSuccess_msg2.equals("")) {
                    trSMS2.addElement((Element)new TD().setColSpan(2).setHeight(25).addElement(strSuccess_msg2));
                }
                final String strSuccesslink2 = (String)hAsmttTable.get("successlink");
                final String strSuccesslabel2 = (String)hAsmttTable.get("successlabel");
                if (strSuccesslink2 != null && !strSuccesslink2.equals("") && strSuccesslink2 != null && !strSuccesslink2.equals("")) {
                    trSMS2.addElement((Element)new TD().setColSpan(2).setHeight(25).addElement((Element)new NOBR().addElement("<a href=\"./delivery.SequencingEngine?resource='" + location2 + "'&identifier='" + Base64.encode(new_identifier2) + "\" target=\"Content\" ><Font size=2>Click here to go on the top page</Font></a>")));
                }
                tbodyInfo.addElement((Element)trSMS2);
            }
            else {
                final String strfailuremessage2 = (String)hAsmttTable.get("failuremessage");
                final TR trFailSMS = new TR();
                if (strfailuremessage2 != null && !strfailuremessage2.equals("")) {
                    trFailSMS.addElement((Element)new TD().setColSpan(2).setHeight(25).addElement(strfailuremessage2));
                }
                final String strretake_link = (String)hAsmttTable.get("retake_link");
                final String strretake_label = (String)hAsmttTable.get("retake_label");
                final String location3 = Base64.encode(ims.getResourceLocation(ims.getResourceIdentifierForItemRef(identifier, ls)));
                if (strretake_link != null && strretake_link.equals("true")) {
                    trFailSMS.addElement((Element)new TD().setColSpan(2).setHeight(25).addElement((Element)new NOBR().addElement("<a href=\"./delivery.SequencingEngine?resource='" + location3 + "'&identifier='" + Base64.encode(identifier) + "\" target=\"Content\" ><Font size=2>" + strretake_label + "</Font></a>")));
                }
                tbodyInfo.addElement((Element)trFailSMS);
                final String strremediation_label = (String)hAsmttTable.get("remediation_label");
                final String strremediation_link = (String)hAsmttTable.get("remediation_link");
                if (strremediation_link != null && !strremediation_link.equals("") && strremediation_label != null && !strremediation_label.equals("")) {
                    tbodyInfo.addElement((Element)new TR().addElement((Element)new TD().setColSpan(2).setHeight(25).addElement((Element)new NOBR().addElement("<a href=\"./delivery.SequencingEngine?resource='" + strremediation_link + "'&identifier='" + Base64.encode(strremediation_link) + "\" target=\"Content\" ><Font size=2>Click here to go on the top page</Font></a>"))));
                }
            }
            final String strgrade_description = (String)hAsmttTable.get("grade_description");
            final String gradation_text = (String)hAsmttTable.get("gradation_text");
            String strGradeType = "X";
            final double gradePersent = asmtScore * 100.0 / asmtMaxScore;
            final Vector vGradation_Scheme = (Vector)hAsmttTable.get("gradation_scheme");
            if (vGradation_Scheme != null) {
                for (int k2 = 0; k2 < vGradation_Scheme.size(); ++k2) {
                    final String[] strGrade = (String[]) vGradation_Scheme.elementAt(k2);
                    final double db1 = Double.parseDouble(strGrade[1]);
                    final double db2 = Double.parseDouble(strGrade[2]);
                    if (gradePersent < db2 && gradePersent > db1) {
                        strGradeType = strGrade[0];
                    }
                }
            }
            if (strgrade_description != null && strgrade_description.equals("true")) {
                tbodyInfo.addElement((Element)new TR().addElement((Element)new TD().setColSpan(2).setHeight(25).addElement((Element)new NOBR().addElement((Element)new B().addElement(gradation_text)))).addElement((Element)new TD().setColSpan(2).setHeight(25).addElement((Element)new NOBR().addElement(strGradeType))));
            }
        }
        final String summary_heading2 = (String)hAsmttTable.get("summary_heading");
        final TBody tbodyAsmt = new TBody();
        final Table tblAsmtSummary = new Table().setBorder(2).setCellPadding(2).setCellSpacing(2).setWidth("100%").addElement((Element)new Caption().setAlign("top").addElement((Element)new B().addElement((Element)new Font().setColor("336699").addElement(summary_heading2))));
        final String total_mark2 = (String)(String)hAsmttTable.get("total_marks");
        final String pass_marks2 = (String)hAsmttTable.get("pass_marks");
        final String obtained_marks2 = (String)hAsmttTable.get("obtained_marks");
        final String percent_marks2 = (String)hAsmttTable.get("percent_marks");
        final String assessment_status = (String)hAsmttTable.get("assessment_status");
        if (total_mark2 != null && !total_mark2.equals("")) {
            tbodyAsmt.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + total_mark2 + "</B>", "" + asmtMaxScore));
        }
        if (pass_marks2 != null && !pass_marks2.equals("")) {
            tbodyAsmt.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + pass_marks2 + "</B>", "" + asmtcutValue));
        }
        if (obtained_marks2 != null && !obtained_marks2.equals("")) {
            tbodyAsmt.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + obtained_marks2 + "</B>", "" + asmtScore));
        }
        if (percent_marks2 != null && !percent_marks2.equals("")) {
            if (asmtScore >= 0.0) {
                tbodyAsmt.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + percent_marks2 + "</B>", "" + Math.floor(asmtScore * 1.0 / asmtMaxScore * 100.0)));
            }
            else {
                tbodyAsmt.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + percent_marks2 + "</B>", ""));
            }
        }
        if (assessment_status != null && !assessment_status.equals("")) {
            tbodyAsmt.addElement((Element)new TRExtension().addHeaderNameForAssessment("<B>" + assessment_status + "</B>", status));
        }
        tblAsmtSummary.addElement((Element)tbodyAsmt);
        javaScript = "\n\tfunction load()\n \t{\n\t\t\tif (window.parent.code != null) {\n\t\t\t\twindow.parent.code.location.reload();\n\t\t\t}\n\t}";
        tblTopInformation.addElement((Element)tbodyInfo);
        strAssessmentSummary.append(strFeedback.toString());
        strFeedbacktop.append(tblTopInformation.toString());
        strFeedbacktop.append(tblAsmtSummary.toString());
        strFeedbacktop.append("<br/>");
        strFeedbacktop.append(tblSectionTop.toString());
        strFeedbacktop.append("<br/>");
        strFeedbacktop.append(strAssessmentSummary.toString());
        sc.addElement(javaScript);
        strFeedbacktop.append("</body>");
        html1.addElement(strFeedbacktop.toString());
        while (true) {
            if (asmtcutValue > asmtScore) {
                doc1.addElement((Element)html1);
                out.println(doc1.toString());
                return;
            }
            continue;
        }
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    public TR MultipleChoiceHTML(final Vector response, final Vector question, final String str, final int no, final Hashtable hSectionExtention) {
        final TR TR = new TR();
        final Div div1 = new Div();
        div1.setID("resultSection");
        final String strItem_id = (String)hSectionExtention.get("item_id");
        final String stritem_name = (String)hSectionExtention.get("item_name");
        final String stritem_type = (String)hSectionExtention.get("item_type");
        final String stritem_answer = (String)hSectionExtention.get("item_answer");
        final String item_obmarks = (String)hSectionExtention.get("item_obmarks");
        final String stritem_marks = (String)hSectionExtention.get("item_marks");
        final String stritem_feedback = (String)hSectionExtention.get("item_feedback");
        final String strQuesNo = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector multiAns = (Vector)question.elementAt(2);
        final int iScore = Integer.parseInt((String) response.elementAt(1));
        String strCorrectAns = (String) question.elementAt(3);
        final Vector vFeedback = (Vector)response.elementAt(2);
        String srtNegscore = "";
        if (response.size() > 3) {
            srtNegscore = (String) response.elementAt(3);
        }
        String strCorrectFeedback = "";
        String strIncorrectFeedback = "";
        this.totalScore += iScore;
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
        String strFeedBack = "";
        String strCorrectIncorrect = "";
        String strScore = "";
        if (str != null && !str.equals("")) {
            if (strCorrectAns.equals(str)) {
                strFeedBack = strCorrectFeedback;
                strCorrectIncorrect = "Correct";
                this.MC_strCorrectIncorrect = "correct";
                ++this.total;
                this.totalnumber += iScore;
                strScore += iScore;
            }
            else {
                strFeedBack = strIncorrectFeedback;
                strCorrectIncorrect = "Incorrect";
                this.MC_strCorrectIncorrect = "incorrect";
                if (!srtNegscore.equals("")) {
                    final double intNegscore = Double.parseDouble(srtNegscore);
                    this.totalnumber += intNegscore;
                    strScore += srtNegscore;
                }
                else {
                    strScore += "X";
                }
            }
        }
        else {
            strCorrectIncorrect = "NotAttemped";
            strScore += "X";
        }
        for (int j = 0; j < multiAns.size(); ++j) {
            final Vector label = (Vector) multiAns.elementAt(j);
            final Character cLabel = (Character) label.elementAt(0);
            final String strAns = (String) label.lastElement();
            if (strCorrectAns.equals(cLabel.toString())) {
                strCorrectAns = "(" + strCorrectAns + ")- " + strAns;
                break;
            }
        }
        if (strItem_id != null && !strItem_id.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("" + no));
        }
        if (stritem_name != null && !stritem_name.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).setWidth("640px").addElement((Element)div1.addElement(strQues)));
        }
        if (stritem_type != null && !stritem_type.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strCorrectIncorrect));
        }
        if (stritem_answer != null && !stritem_answer.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strCorrectAns));
        }
        if (stritem_marks != null && !stritem_marks.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("" + iScore));
        }
        if (item_obmarks != null && !item_obmarks.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strScore));
        }
        if (stritem_feedback != null && !stritem_feedback.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strFeedBack));
        }
        return TR;
    }
    
    public TR SliderType(final Vector response, final Vector question, final String str, final int no, final Hashtable hSectionExtention) {
        final TR TR = new TR();
        final Div div1 = new Div();
        div1.setID("resultSection");
        final String strItem_id = (String)hSectionExtention.get("item_id");
        final String stritem_name = (String)hSectionExtention.get("item_name");
        final String stritem_type = (String)hSectionExtention.get("item_type");
        final String stritem_answer = (String)hSectionExtention.get("item_answer");
        final String item_obmarks = (String)hSectionExtention.get("item_obmarks");
        final String stritem_marks = (String)hSectionExtention.get("item_marks");
        final String stritem_feedback = (String)hSectionExtention.get("item_feedback");
        final String strQuesNo = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector multiAns = (Vector)question.elementAt(2);
        final int iScore = Integer.parseInt((String) response.elementAt(1));
        String strCorrectAns = (String) question.elementAt(3);
        final Vector vFeedback = (Vector)response.elementAt(2);
        String srtNegscore = "";
        if (response.size() > 3) {
            srtNegscore = (String) response.elementAt(3);
        }
        OutComes.log.debug("srtNegscore=======" + srtNegscore);
        String strCorrectFeedback = "";
        String strIncorrectFeedback = "";
        this.totalScore += iScore;
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
        String strFeedBack = "";
        String strCorrectIncorrect = "";
        String strScore = "";
        String correct = "";
        for (int j = 0; j < multiAns.size(); ++j) {
            final Vector label1 = (Vector) multiAns.elementAt(j);
            final String cLabel1 = (String) label1.elementAt(0);
            final String strAns1 = (String) label1.lastElement();
            if (strCorrectAns.equals(cLabel1.toString())) {
                correct = strAns1;
            }
        }
        if (str != null && !str.equals("") && !str.equals("NaN")) {
            if (correct.equals(str)) {
                strFeedBack = strCorrectFeedback;
                strCorrectIncorrect = "Correct";
                this.ST_strCorrectIncorrect = "correct";
                ++this.total;
                this.totalnumber += iScore;
                strScore += iScore;
            }
            else {
                strFeedBack = strIncorrectFeedback;
                strCorrectIncorrect = "Incorrect";
                this.ST_strCorrectIncorrect = "incorrect";
                if (!srtNegscore.equals("")) {
                    final double intNegscore = Double.parseDouble(srtNegscore);
                    this.totalnumber += intNegscore;
                    strScore += srtNegscore;
                }
                else {
                    strScore += "X";
                }
            }
        }
        else {
            strCorrectIncorrect = "NotAttemped";
            strScore += "X";
        }
        for (int j = 0; j < multiAns.size(); ++j) {
            final Vector label2 = (Vector) multiAns.elementAt(j);
            final String cLabel2 = (String) label2.elementAt(0);
            final String strAns2 = (String) label2.lastElement();
            if (correct.equals(str)) {
                strCorrectAns = "(" + correct + ")- " + str;
                break;
            }
        }
        if (strItem_id != null && !strItem_id.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("" + no));
        }
        if (stritem_name != null && !stritem_name.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).setWidth("640px").addElement((Element)div1.addElement(strQues)));
        }
        if (stritem_type != null && !stritem_type.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strCorrectIncorrect));
        }
        if (stritem_answer != null && !stritem_answer.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strCorrectAns));
        }
        if (stritem_marks != null && !stritem_marks.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("" + iScore));
        }
        if (item_obmarks != null && !item_obmarks.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strScore));
        }
        if (stritem_feedback != null && !stritem_feedback.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strFeedBack));
        }
        return TR;
    }
    
    public TR MultipleResponseHTML(final Vector response, final Vector question, final String[] str1, final int no, final Hashtable hSectionExtention) {
        final TR TR = new TR();
        final Div div1 = new Div();
        div1.setID("resultSection");
        final String strQuesNo = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector multiAns = (Vector)question.elementAt(2);
        final Vector vCorrectAns = (Vector)question.elementAt(3);
        final int iScore = Integer.parseInt((String) response.elementAt(1));
        final Vector vFeedback = (Vector)response.elementAt(2);
        String srtNegscore = "";
        if (response.size() > 3) {
            srtNegscore = (String) response.elementAt(3);
        }
        String strCorrectFeedback = "";
        String strIncorrectFeedback = "";
        this.totalScore += iScore;
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
        String strFeedBack = "";
        String strCorrectIncorrect = "";
        String strScore = "";
        boolean B = false;
        if (str1 != null) {
            for (int j = 0; j < vCorrectAns.size(); ++j) {
                for (int k = 0; k < str1.length; ++k) {
                    final String str2 = (String) vCorrectAns.elementAt(j);
                    if (str2.equals(str1[k])) {
                        B = true;
                        break;
                    }
                    B = false;
                }
                if (!B) {
                    break;
                }
            }
            if (B) {
                strFeedBack = strCorrectFeedback;
                strCorrectIncorrect = "Correct";
                this.MR_strCorrectIncorrect = "correct";
                ++this.total;
                this.totalnumber += iScore;
                strScore += iScore;
            }
            else {
                strFeedBack = strIncorrectFeedback;
                strCorrectIncorrect = "Incorrect";
                this.MR_strCorrectIncorrect = "incorrect";
                if (!srtNegscore.equals("")) {
                    final double intNegscore = Double.parseDouble(srtNegscore);
                    this.totalnumber += intNegscore;
                    strScore += srtNegscore;
                }
                else {
                    strScore += "X";
                }
            }
        }
        else {
            strCorrectIncorrect = "NotAttemped";
            strScore += "X";
        }
        String strCorrectAns = "";
        for (int l = 0; l < vCorrectAns.size(); ++l) {
            String strLabel = "";
            final String strCorrectAns2 = (String) vCorrectAns.elementAt(l);
            for (int m = 0; m < multiAns.size(); ++m) {
                final Vector label = (Vector) multiAns.elementAt(m);
                final Character cLabel = (Character) label.elementAt(0);
                final String strAns = (String) label.elementAt(1);
                if (strCorrectAns2.equals(cLabel.toString())) {
                    strLabel = cLabel.toString();
                    strCorrectAns = strCorrectAns + "(" + strCorrectAns2 + ") -" + strAns + ", \n";
                }
            }
        }
        final String strItem_id = (String)hSectionExtention.get("item_id");
        final String stritem_name = (String)hSectionExtention.get("item_name");
        final String stritem_type = (String)hSectionExtention.get("item_type");
        final String stritem_answer = (String)hSectionExtention.get("item_answer");
        final String item_obmarks = (String)hSectionExtention.get("item_obmarks");
        final String stritem_marks = (String)hSectionExtention.get("item_marks");
        final String stritem_feedback = (String)hSectionExtention.get("item_feedback");
        if (strItem_id != null && !strItem_id.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("" + no));
        }
        if (stritem_name != null && !stritem_name.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).setWidth("640px").addElement((Element)div1.addElement(strQues)));
        }
        if (stritem_type != null && !stritem_type.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strCorrectIncorrect));
        }
        if (stritem_answer != null && !stritem_answer.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strCorrectAns));
        }
        if (stritem_marks != null && !stritem_marks.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("" + iScore));
        }
        if (item_obmarks != null && !item_obmarks.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strScore));
        }
        if (stritem_feedback != null && !stritem_feedback.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strFeedBack));
        }
        return TR;
    }
    
    public TR FillBlanks(final Vector response, final Vector question, final String str, final int no, final Hashtable hSectionExtention) {
        final TR TR = new TR();
        final Div div1 = new Div();
        div1.setID("resultSection");
        final StringBuffer strHTML = new StringBuffer();
        final String strQuesNo = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector multiAns = (Vector)question.elementAt(2);
        final int iScore = Integer.parseInt((String) response.elementAt(1));
        final String strCorrectAns = (String) multiAns.elementAt(0);
        String strCase = "";
        if (multiAns.size() >= 2) {
            strCase = (String) multiAns.elementAt(2);
        }
        final Vector vFeedback = (Vector)response.elementAt(2);
        String srtNegscore = "";
        if (response.size() > 3) {
            srtNegscore = (String) response.elementAt(3);
        }
        String strCorrectFeedback = "";
        String strIncorrectFeedback = "";
        this.totalScore += iScore;
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
        String strFeedBack = "";
        String strCorrectIncorrect = "";
        String strScore = "";
        if (str != null && !str.equals("")) {
            if (strCase.equalsIgnoreCase("No")) {
                if (strCorrectAns.trim().equalsIgnoreCase(str.trim())) {
                    strFeedBack = strCorrectFeedback;
                    strCorrectIncorrect = "Correct";
                    ++this.total;
                    this.totalnumber += iScore;
                    strScore += iScore;
                    this.FIB_strCorrectIncorrect = "correct";
                }
                else {
                    strFeedBack = strIncorrectFeedback;
                    strCorrectIncorrect = "Incorrect";
                    if (!srtNegscore.equals("")) {
                        final double intNegscore = Double.parseDouble(srtNegscore);
                        this.totalnumber += intNegscore;
                        strScore += srtNegscore;
                    }
                    else {
                        strScore += "X";
                    }
                    this.FIB_strCorrectIncorrect = "incorrect";
                }
            }
            else if (strCorrectAns.equals(str)) {
                strFeedBack = strCorrectFeedback;
                strCorrectIncorrect = "Correct";
                this.FIB_strCorrectIncorrect = "correct";
                ++this.total;
                this.totalnumber += iScore;
                strScore += iScore;
            }
            else {
                strFeedBack = strIncorrectFeedback;
                strCorrectIncorrect = "Incorrect";
                this.FIB_strCorrectIncorrect = "incorrect";
                strScore += "X";
            }
        }
        else {
            strCorrectIncorrect = "NotAttemped";
            strScore += "X";
        }
        final String strItem_id = (String)hSectionExtention.get("item_id");
        final String stritem_name = (String)hSectionExtention.get("item_name");
        final String stritem_type = (String)hSectionExtention.get("item_type");
        final String stritem_answer = (String)hSectionExtention.get("item_answer");
        final String item_obmarks = (String)hSectionExtention.get("item_obmarks");
        final String stritem_marks = (String)hSectionExtention.get("item_marks");
        final String stritem_feedback = (String)hSectionExtention.get("item_feedback");
        if (strItem_id != null && !strItem_id.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("" + no));
        }
        if (stritem_name != null && !stritem_name.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).setWidth("640px").addElement((Element)div1.addElement(strQues)));
        }
        if (stritem_type != null && !stritem_type.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strCorrectIncorrect));
        }
        if (stritem_answer != null && !stritem_answer.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strCorrectAns));
        }
        if (stritem_marks != null && !stritem_marks.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("" + iScore));
        }
        if (item_obmarks != null && !item_obmarks.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strScore));
        }
        if (stritem_feedback != null && !stritem_feedback.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strFeedBack));
        }
        return TR;
    }
    
    public TR EssayType(final Vector response, final Vector question, final String str, final int no, final Hashtable hSectionExtention) {
        final TR TR = new TR();
        final String strQuesNo = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final int iScore = Integer.parseInt((String) response.elementAt(1));
        this.totalScore += iScore;
        this.total_marks = iScore;
        this.item_id = strQuesNo;
        this.item_name = strQues;
        final String strItem_id = (String)hSectionExtention.get("item_id");
        final String stritem_name = (String)hSectionExtention.get("item_name");
        final String stritem_type = (String)hSectionExtention.get("item_type");
        final String stritem_answer = (String)hSectionExtention.get("item_answer");
        final String item_obmarks = (String)hSectionExtention.get("item_obmarks");
        final String stritem_marks = (String)hSectionExtention.get("item_marks");
        final String stritem_feedback = (String)hSectionExtention.get("item_feedback");
        if (strItem_id != null && !strItem_id.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("" + no));
        }
        if (stritem_name != null && !stritem_name.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).setWidth("640px").addElement(strQues));
        }
        if (stritem_type != null && !stritem_type.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("NA"));
        }
        if (stritem_answer != null && !stritem_answer.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("NA"));
        }
        if (stritem_marks != null && !stritem_marks.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("" + iScore));
        }
        if (item_obmarks != null && !item_obmarks.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("NA"));
        }
        if (stritem_feedback != null && !stritem_feedback.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("NA"));
        }
        return TR;
    }
    
    public TR MultipleChoiceImage(final Vector response, final Vector question, final String str, final int no, final Hashtable hSectionExtention) {
        final TR TR = new TR();
        final Div div1 = new Div();
        div1.setID("resultSection");
        final String strItem_id = (String)hSectionExtention.get("item_id");
        final String stritem_name = (String)hSectionExtention.get("item_name");
        final String stritem_type = (String)hSectionExtention.get("item_type");
        final String stritem_answer = (String)hSectionExtention.get("item_answer");
        final String item_obmarks = (String)hSectionExtention.get("item_obmarks");
        final String stritem_marks = (String)hSectionExtention.get("item_marks");
        final String stritem_feedback = (String)hSectionExtention.get("item_feedback");
        final StringBuffer strHTML = new StringBuffer();
        final String strQuesNo = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector multiAns = (Vector)question.elementAt(2);
        final int iScore = Integer.parseInt((String) response.elementAt(1));
        final String strCorrectAns = (String) question.elementAt(3);
        final Vector vFeedback = (Vector)response.elementAt(2);
        String strCorrectFeedback = "";
        String strIncorrectFeedback = "";
        this.totalScore += iScore;
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
        String strFeedBack = "";
        String strCorrectIncorrect = "";
        String strScore = "";
        if (strCorrectAns.equals(str)) {
            strFeedBack = strCorrectFeedback;
            strCorrectIncorrect = "Correct";
            this.MCI_strCorrectIncorrect = "correct";
            ++this.total;
            this.totalnumber += iScore;
            strScore += iScore;
        }
        else {
            strFeedBack = strIncorrectFeedback;
            strCorrectIncorrect = "Incorrect";
            this.MCI_strCorrectIncorrect = "incorrect";
            strScore += "X";
        }
        if (strItem_id != null && !strItem_id.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("" + no));
        }
        if (stritem_name != null && !stritem_name.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).setWidth("640px").addElement((Element)div1.addElement(strQues)));
        }
        if (stritem_type != null && !stritem_type.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strCorrectIncorrect));
        }
        if (stritem_answer != null && !stritem_answer.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strCorrectAns));
        }
        if (stritem_marks != null && !stritem_marks.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("" + iScore));
        }
        if (item_obmarks != null && !item_obmarks.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strScore));
        }
        if (stritem_feedback != null && !stritem_feedback.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strFeedBack));
        }
        return TR;
    }
    
    public TR MatchingQuesHTML(final Vector response, final Vector question, final Vector item_responses, final String ans_text, final int no, final Hashtable hSectionExtention) {
        final TR TR = new TR();
        final Div div1 = new Div();
        final String strItem_id = (String)hSectionExtention.get("item_id");
        final String stritem_name = (String)hSectionExtention.get("item_name");
        final String stritem_type = (String)hSectionExtention.get("item_type");
        final String stritem_answer = (String)hSectionExtention.get("item_answer");
        final String item_obmarks = (String)hSectionExtention.get("item_obmarks");
        final String stritem_marks = (String)hSectionExtention.get("item_marks");
        final String stritem_feedback = (String)hSectionExtention.get("item_feedback");
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
        this.totalScore += iScore;
        String srtNegscore = "";
        if (response.size() > 3) {
            srtNegscore = (String)response.elementAt(3);
        }
        String strFeedBack = "";
        String strCorrectIncorrect = "";
        String strScore = "";
        final double item_score = 0.0;
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
            System.out.println("==========b1=================" + b1);
            if (!b1) {
                break;
            }
        }
        if (c1) {
            if (b1) {
                strFeedBack = strCorrectFeedback;
                strCorrectIncorrect = "Correct";
                this.MQ_strCorrectIncorrect = "correct";
                ++this.total;
                this.totalnumber += iScore;
                strScore += iScore;
            }
            else {
                strFeedBack = strIncorrectFeedback;
                strCorrectIncorrect = "Incorrect";
                this.MQ_strCorrectIncorrect = "incorrect";
                if (!srtNegscore.equals("")) {
                    final double intNegscore = Double.parseDouble(srtNegscore);
                    this.totalnumber += intNegscore;
                    strScore += srtNegscore;
                }
                else {
                    strScore += "X";
                }
            }
        }
        else {
            strCorrectIncorrect = "NotAttemped";
            strScore += "X";
        }
        String strCorrectAns = "";
        for (int r3 = 0; r3 < vCorrectAns.size(); ++r3) {
            final String[] a = (String[]) vCorrectAns.elementAt(r3);
            strCorrectAns = strCorrectAns + "\r\n " + a[0] + " - " + a[1] + ",";
        }
        if (strItem_id != null && !strItem_id.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("" + no));
        }
        if (stritem_name != null && !stritem_name.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).setWidth("640px").addElement((Element)div1.addElement(strQues)));
        }
        if (stritem_type != null && !stritem_type.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strCorrectIncorrect));
        }
        if (stritem_answer != null && !stritem_answer.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strCorrectAns));
        }
        if (stritem_marks != null && !stritem_marks.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement("" + iScore));
        }
        if (item_obmarks != null && !item_obmarks.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strScore));
        }
        if (stritem_feedback != null && !stritem_feedback.equals("")) {
            TR.addElement((Element)new TD().setHeight(25).addElement(strFeedBack));
        }
        return TR;
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
    
    static {
        log = new SimpleLogger((Class)OutComes.class, true);
    }
}
