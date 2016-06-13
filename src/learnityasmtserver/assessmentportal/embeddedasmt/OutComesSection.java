package learnityasmtserver.assessmentportal.embeddedasmt;

import org.grlea.log.*;
import org.apache.ecs.*;
import org.apache.ecs.html.*;
import learnityasmtserver.assessmentportal.utility.*;
import org.apache.ecs.xhtml.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;

public class OutComesSection extends HttpServlet
{
    public static final SimpleLogger log;
    private static final String SESSION_NAME = "SESSION_NAME";
    private static final String SESSION_ANS = "SESSION_ANS";
    private static final String SESSION_TEMPANS = "SESSION_TEMPANS";
    private static final String SESSION_SECTION_NAME = "SESSION_SECTION_NAME";
    private static final String SESSION_ASSESSMENT_NAME = "SESSION_ASSESSMENT_NAME";
    int totalques;
    int total;
    int totalnumber;
    int totalScore;
    int total_marks;
    String item_id;
    String item_name;
    boolean resultViewToUser;
    
    public OutComesSection() {
        this.totalques = 0;
        this.total = 0;
        this.totalnumber = 0;
        this.totalScore = 0;
        this.total_marks = 0;
        this.item_id = null;
        this.item_name = null;
        this.resultViewToUser = false;
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final PrintWriter out = response.getWriter();
        final HttpSession mysession = request.getSession(true);
        final Vector obj = (Vector)mysession.getAttribute("SESSION_NAME");
        final String BROWSER_TYPE = (String)mysession.getAttribute("BROWSER");
        OutComesSection.log.debug("***OutComesSection*****BROWSER_TYPE********" + BROWSER_TYPE);
        Hashtable hSectionExtention = null;
        String section_id = null;
        boolean checkEssay = false;
        Vector v = null;
        if (obj == null) {
            v = new Vector();
        }
        else {
            v = obj;
        }
        int iSec = 0;
        final String secId = request.getParameter("secID");
        final String sectionID = request.getParameter("sectionID");
        final String slide = request.getParameter("slide");
        Vector vAns = null;
        if (secId != null) {
            iSec = Integer.parseInt(secId);
        }
        if (secId != null) {
            final Object o = mysession.getAttribute("SESSION_ANS");
            if (o == null) {
                vAns = new Vector();
            }
            else {
                vAns = (Vector)o;
            }
            final Vector questionAll = (Vector) ((Vector) v.elementAt(iSec - 1)).elementAt(0);
            final Vector vA = new Vector();
            vAns = new Vector(3, 3);
            for (int i = 0; i < questionAll.size(); ++i) {
                final Vector questionMatter = (Vector) questionAll.elementAt(i);
                final Vector res = (Vector) questionMatter.elementAt(0);
                final Vector question = (Vector) questionMatter.elementAt(1);
                final String strTitle = "";
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
                        OutComesSection.log.debug("******outcomesSection************str********************* " + str);
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
                }
            }
            vAns.addElement(vA);
            mysession.setAttribute("SESSION_TEMPANS", (Object)vAns);
        }
        final Vector oSection = (Vector)mysession.getAttribute("SESSION_SECTION_NAME");
        Vector vSectionName = null;
        if (oSection == null) {
            vSectionName = new Vector();
        }
        else {
            vSectionName = oSection;
        }
        final StringBuffer strFeedbacktop = new StringBuffer();
        final StringBuffer strFeedback = new StringBuffer();
        final Calendar calendar = new GregorianCalendar();
        final Date trialTime = new Date();
        calendar.setTime(trialTime);
        final String[] months = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        final int mon = calendar.get(2) + 1;
        final String strTime = calendar.get(10) + ":" + calendar.get(12);
        final String strDate = months[calendar.get(2)] + " " + calendar.get(5) + ", " + calendar.get(1);
        String javaScript = "";
        final script sc = new script();
        final html html = new html().addElement((Element)new head().addElement((Element)new title("Test Report")).addElement((Element)sc.setLanguage("JavaScript")));
        strFeedbacktop.append("\n<body bgcolor = '#FFFFFF' background='hpbkgd.gif' onload =\"load();\">");
        strFeedbacktop.append("\n<br><form name='frm'>");
        strFeedbacktop.append("\n<center><font size=\"5\"><b><em>Powered by</em></b></font>");
        strFeedbacktop.append("\n<font size=\"5\" color=\"#CC0000\">LearnITy&#8482;</font><font size=\"5\"><b><em>Assessment Server</em></b></font></center>");
        strFeedbacktop.append("\n<br>");
        strFeedbacktop.append("\n<table cellPadding=0 cellSpacing=1 width=\"100%\">");
        strFeedbacktop.append("\n<tr bgColor=#336699>");
        strFeedbacktop.append("\n<td width=\"50%\" height =23><nobr>");
        strFeedbacktop.append("\n  <font color=white> Test Result  </font></nobr></td>");
        strFeedbacktop.append("\n<td width=\"25%\"><nobr>");
        strFeedbacktop.append("\n  <div align=\"center\"><font color=white style=\"BACKGROUND-COLOR: #336699\"> Date: " + strDate + "</font></div><nobr>");
        strFeedbacktop.append("\n</td>");
        strFeedbacktop.append("\n<td width=\"25%\"><nobr>");
        strFeedbacktop.append("\n  <div align=\"center\"><font color=white style=\"BACKGROUND-COLOR: #336699\"> Time: " + strTime + "</font></div></nobr>");
        strFeedbacktop.append("\n</td>");
        strFeedbacktop.append("\n</tr>");
        strFeedbacktop.append("\n</table>");
        strFeedbacktop.append("\n<br/>");
        String status = "";
        final int subTotal = 0;
        final int subScore = 0;
        String answer = null;
        for (int j = 0; j < vAns.size(); ++j) {
            final Vector questionAll2 = (Vector) ((Vector) v.elementAt(iSec - 1)).elementAt(0);
            final Vector vA2 = (Vector) vAns.elementAt(j);
            final Vector strSectionName = (Vector) vSectionName.elementAt(iSec - 1);
            final Vector vSectionResult = (Vector) strSectionName.elementAt(3);
            final Vector vSectionExtention = (Vector) strSectionName.elementAt(4);
            if (vSectionExtention != null) {
                hSectionExtention = (Hashtable) vSectionExtention.elementAt(0);
            }
            final String strSecPassed = (String) vSectionResult.elementAt(0);
            final String strSecFailed = (String) vSectionResult.elementAt(1);
            section_id = strSectionName.elementAt(1) + ". " + strSectionName.elementAt(0);
            final String strComplation_message = (String) hSectionExtention.get("complation_message");
            final tbody tbodySectionMid = new tbody();
            final table tblSectionMid = new table().setBorder(0).setCellPadding(0).setCellSpacing(0).setWidth("100%").addElement((Element)new Caption().setAlign("top").addElement((Element)new b().addElement((Element)new font().setColor("336699").addElement(strComplation_message))));
            final String strFeedback_heading = (String) hSectionExtention.get("feedback_heading");
            if (strFeedback_heading != null && !strFeedback_heading.equals("")) {
                tbodySectionMid.addElement((Element)new TRExtension().addUserForSection(strFeedback_heading, "" + strSectionName.elementAt(0)));
            }
            final String strItemSum = (String) hSectionExtention.get("itemfeedbackheading");
            final tbody tbodyItemSum = new tbody();
            final table tblItemSummary = new table().setBorder(1).setBorder("336699").setCellPadding(1).setCellSpacing(1).setWidth("100%").addElement((Element)new Caption().setAlign("top").addElement((Element)new b().addElement((Element)new font().setColor("336699").addElement(strItemSum))));
            boolean boolItem = false;
            final String strItem_id = (String) hSectionExtention.get("item_id");
            final String stritem_name = (String) hSectionExtention.get("item_name");
            final String stritem_type = (String) hSectionExtention.get("item_type");
            final String stritem_answer = (String) hSectionExtention.get("item_answer");
            final String item_obmarks = (String) hSectionExtention.get("item_obmarks");
            final String stritem_marks = (String) hSectionExtention.get("item_marks");
            final String stritem_feedback = (String) hSectionExtention.get("item_feedback");
            final tr trItem = new tr();
            if (strItem_id != null && !strItem_id.equals("")) {
                boolItem = true;
                trItem.addElement((Element)new td().setHeight(25).addElement((Element)new b().addElement(strItem_id)));
            }
            if (stritem_name != null && !stritem_name.equals("")) {
                boolItem = true;
                trItem.addElement((Element)new td().setHeight(25).addElement((Element)new b().addElement(stritem_name)));
            }
            if (stritem_type != null && !stritem_type.equals("")) {
                boolItem = true;
                trItem.addElement((Element)new td().setHeight(25).addElement((Element)new b().addElement(stritem_type)));
            }
            if (stritem_answer != null && !stritem_answer.equals("")) {
                boolItem = true;
                trItem.addElement((Element)new td().setHeight(25).addElement((Element)new b().addElement(stritem_answer)));
            }
            if (stritem_marks != null && !stritem_marks.equals("")) {
                boolItem = true;
                trItem.addElement((Element)new td().setHeight(25).addElement((Element)new b().addElement(stritem_marks)));
            }
            if (item_obmarks != null && !item_obmarks.equals("")) {
                boolItem = true;
                trItem.addElement((Element)new td().setHeight(25).addElement((Element)new b().addElement(item_obmarks)));
            }
            if (stritem_feedback != null && !stritem_feedback.equals("")) {
                boolItem = true;
                trItem.addElement((Element)new td().setHeight(25).addElement((Element)new b().addElement(stritem_feedback)));
            }
            tbodyItemSum.addElement((Element)trItem);
            this.totalques = 0;
            this.total = 0;
            this.totalnumber = 0;
            this.totalScore = 0;
            for (int k = 0; k < questionAll2.size(); ++k) {
                final Vector questionMatter2 = (Vector) questionAll2.elementAt(k);
                final Vector res2 = (Vector) questionMatter2.elementAt(0);
                final Vector question2 = (Vector) questionMatter2.elementAt(1);
                final String strTitle2 = "";
                final Integer iTitle2 = (Integer) res2.elementAt(0);
                String str3 = "";
                tr tr = null;
                final int l = k + 1;
                switch (iTitle2) {
                    case 0: {
                        str3 = (String) vA2.elementAt(k);
                        tr = this.MultipleChoiceHTML(res2, question2, str3, l, hSectionExtention);
                        if (boolItem) {
                            tbodyItemSum.addElement((Element)tr);
                            break;
                        }
                        break;
                    }
                    case 1: {
                        final String[] str4 = (String[]) vA2.elementAt(k);
                        tr = this.MultipleResponseHTML(res2, question2, str4, l, hSectionExtention);
                        if (boolItem) {
                            tbodyItemSum.addElement((Element)tr);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        str3 = (String) vA2.elementAt(k);
                        tr = this.FillBlanks(res2, question2, str3, l, hSectionExtention);
                        if (boolItem) {
                            tbodyItemSum.addElement((Element)tr);
                            break;
                        }
                        break;
                    }
                    case 3: {
                        str3 = (String) vA2.elementAt(k);
                        tr = this.MultipleChoiceHTML(res2, question2, str3, l, hSectionExtention);
                        if (boolItem) {
                            tbodyItemSum.addElement((Element)tr);
                            break;
                        }
                        break;
                    }
                    case 4: {
                        str3 = (String) vA2.elementAt(k);
                        tr = this.MultipleChoiceImage(res2, question2, str3, l, hSectionExtention);
                        if (boolItem) {
                            tbodyItemSum.addElement((Element)tr);
                            break;
                        }
                        break;
                    }
                    case 5: {
                        str3 = (String) vA2.elementAt(k);
                        checkEssay = true;
                        answer = str3;
                        tr = this.EssayType(res2, question2, str3, l, hSectionExtention);
                        if (boolItem) {
                            tbodyItemSum.addElement((Element)tr);
                            break;
                        }
                        break;
                    }
                    case 6: {
                        str3 = (String) vA2.elementAt(k);
                        tr = this.SliderType(res2, question2, str3, l, hSectionExtention);
                        if (boolItem) {
                            tbodyItemSum.addElement((Element)tr);
                            break;
                        }
                        break;
                    }
                }
            }
            tblItemSummary.addElement((Element)tbodyItemSum);
            status = ((Integer.parseInt((String) ((Vector) v.elementAt(iSec - 1)).elementAt(1)) <= this.totalnumber) ? strSecPassed : strSecFailed);
            final String summary_heading = (String) hSectionExtention.get("summary_heading");
            final tbody tbodySecSum = new tbody();
            final table tblSectionSummary = new table().setBorder(0).setCellPadding(1).setCellSpacing(1).setWidth("100%").addElement((Element)new Caption().setAlign("top").addElement((Element)new b().addElement((Element)new font().setColor("336699").addElement(summary_heading))));
            final String total_questions = (String) hSectionExtention.get("total_questions");
            final String total_correct = (String) hSectionExtention.get("total_correct");
            final String percent_correct = (String) hSectionExtention.get("percent_correct");
            final String total_incorrect = (String) hSectionExtention.get("total_incorrect");
            final String percent_incorrect = (String) hSectionExtention.get("percent_incorrect");
            final String total_no = (String) hSectionExtention.get("total_marks");
            final String pass_marks = (String) hSectionExtention.get("pass_marks");
            final String obtained_marks = (String) hSectionExtention.get("obtained_marks");
            final String percent_marks = (String) hSectionExtention.get("percent_marks");
            final String feedback_status = (String) hSectionExtention.get("feedback_status");
            if (total_questions != null && !total_questions.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<b>" + total_questions + "</b>", "" + questionAll2.size()));
            }
            if (total_questions != null && !total_questions.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<b>" + total_correct + "</b>", "" + this.total));
            }
            if (percent_correct != null && !percent_correct.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<b>" + percent_correct + "</b>", "" + Math.floor(this.total * 1.0 / questionAll2.size() * 100.0)));
            }
            if (total_incorrect != null && !total_incorrect.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<b>" + total_incorrect + "</b>", "" + (questionAll2.size() - this.total)));
            }
            if (percent_incorrect != null && !percent_incorrect.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<b>" + percent_incorrect + "</b>", "" + Math.floor((questionAll2.size() - this.total) * 1.0 / questionAll2.size() * 100.0)));
            }
            if (total_no != null && !total_no.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<b>" + total_no + "</b>", "" + this.totalScore));
            }
            if (pass_marks != null && !pass_marks.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<b>" + pass_marks + "</b>", "" + Integer.parseInt((String) ((Vector) v.elementAt(j)).elementAt(1))));
            }
            if (obtained_marks != null && !obtained_marks.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<b>" + obtained_marks + "</b>", "" + this.totalnumber));
            }
            if (percent_marks != null && !percent_marks.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<b>" + percent_marks + "</b>", "" + Math.floor(this.totalnumber * 1.0 / this.totalScore * 100.0)));
            }
            if (feedback_status != null && !feedback_status.equals("")) {
                tbodySecSum.addElement((Element)new TRExtension().addHeaderNameForAssessment("<b>" + feedback_status + "</b>", status));
            }
            tblSectionMid.addElement((Element)tbodySectionMid);
            tblSectionSummary.addElement((Element)tbodySecSum);
            strFeedback.append(tblSectionMid.toString());
            strFeedback.append(tblSectionSummary.toString());
            strFeedback.append("<br/>");
            strFeedback.append("<br/>");
            strFeedback.append("\n<table border=0 cellPadding=0 cellSpacing=0>");
            strFeedback.append("\n<tr>");
            if (sectionID != null) {
                strFeedback.append("\n<td>\n<INPUT class=sbttn name=add tabIndex=2 title=\"Result Summary\" type=\"button\" value=\"Result Summary\" onclick=\"result_onclick(" + iSec + ")\">");
                strFeedback.append("\n</td>");
            }
            else {
                strFeedback.append("\n<td>\n<INPUT class=sbttn name=add tabIndex=2 title=\" Next Section \" type=\"button\" value=\"Next Section\" onclick=\"submit_onclick(" + iSec + ")\">");
                strFeedback.append("\n</td>");
            }
            strFeedback.append("\n<td width=5></td>");
            strFeedback.append("\n</tr>");
            strFeedback.append("\n</table>");
            strFeedback.append("</form></body>");
        }
        if (BROWSER_TYPE.equals("mf")) {
            javaScript = "\n\tfunction load()\n \t{\n\t\t\tif (window.parent.code != null) {\n\t\t\t\twindow.parent.code.location.reload();\n\t\t\t}\n\t}\n    function result_onclick(i) {\n            document.location.href=\"learnityasmtserver.assessmentportal.embeddedasmt.OutComesXHTML?secResult=true&secID=\"+i;\n    }\n    function submit_onclick(i) {\n            document.location.href=\"learnityasmtserver.assessmentportal.embeddedasmt.AssessmentXHTML?secResult=true&secID=\"+i;\n    }";
            OutComesSection.log.debug("==================if=============mf============================================");
        }
        else {
            javaScript = "\n\tfunction load()\n \t{\n\t\t\tif (window.parent.code != null) {\n\t\t\t\twindow.parent.code.location.reload();\n\t\t\t}\n\t}\n    function result_onclick(i) {\n            document.location.href=\"learnityasmtserver.assessmentportal.embeddedasmt.OutComes?secResult=true&secID=\"+i;\n    }\n    function submit_onclick(i) {\n            document.location.href=\"learnityasmtserver.assessmentportal.embeddedasmt.Assessment?secResult=true&secID=\"+i;\n    }";
            OutComesSection.log.debug("==================else=============others=======ie=====================================");
        }
        strFeedbacktop.append(strFeedback.toString());
        sc.addElement(javaScript);
        html.addElement(strFeedbacktop.toString());
        out.println(html.toString());
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    public tr MultipleChoiceHTML(final Vector response, final Vector question, final String str, final int no, final Hashtable hSectionExtention) {
        final tr tr = new tr();
        final String strItem_id = (String) hSectionExtention.get("item_id");
        final String stritem_name = (String) hSectionExtention.get("item_name");
        final String stritem_type = (String) hSectionExtention.get("item_type");
        final String stritem_answer = (String) hSectionExtention.get("item_answer");
        final String item_obmarks = (String) hSectionExtention.get("item_obmarks");
        final String stritem_marks = (String) hSectionExtention.get("item_marks");
        final String stritem_feedback = (String) hSectionExtention.get("item_feedback");
        final String strQuesNo = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector multiAns = (Vector)question.elementAt(2);
        final int iScore = Integer.parseInt((String) response.elementAt(1));
        String strCorrectAns = (String) question.elementAt(3);
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
            ++this.total;
            this.totalnumber += iScore;
            strScore += iScore;
        }
        else {
            strFeedBack = strIncorrectFeedback;
            strCorrectIncorrect = "Incorrect";
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
            tr.addElement((Element)new td().setHeight(25).addElement("" + no));
        }
        if (stritem_name != null && !stritem_name.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strQues));
        }
        if (stritem_type != null && !stritem_type.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strCorrectIncorrect));
        }
        if (stritem_answer != null && !stritem_answer.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strCorrectAns));
        }
        if (stritem_marks != null && !stritem_marks.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement("" + iScore));
        }
        if (item_obmarks != null && !item_obmarks.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strScore));
        }
        if (stritem_feedback != null && !stritem_feedback.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strFeedBack));
        }
        return tr;
    }
    
    public tr SliderType(final Vector response, final Vector question, final String str, final int no, final Hashtable hSectionExtention) {
        final tr tr = new tr();
        final String strItem_id = (String) hSectionExtention.get("item_id");
        final String stritem_name = (String) hSectionExtention.get("item_name");
        final String stritem_type = (String) hSectionExtention.get("item_type");
        final String stritem_answer = (String) hSectionExtention.get("item_answer");
        final String item_obmarks = (String) hSectionExtention.get("item_obmarks");
        final String stritem_marks = (String) hSectionExtention.get("item_marks");
        final String stritem_feedback = (String) hSectionExtention.get("item_feedback");
        final String strQuesNo = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector multiAns = (Vector)question.elementAt(2);
        final int iScore = Integer.parseInt((String) response.elementAt(1));
        String strCorrectAns = (String) question.elementAt(3);
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
        String correct = "";
        for (int j = 0; j < multiAns.size(); ++j) {
            final Vector label1 = (Vector) multiAns.elementAt(j);
            final String cLabel1 = (String) label1.elementAt(0);
            final String strAns1 = (String) label1.lastElement();
            if (strCorrectAns.equals(cLabel1.toString())) {
                correct = strAns1;
            }
        }
        if (correct.equals(str)) {
            strFeedBack = strCorrectFeedback;
            strCorrectIncorrect = "Correct";
            ++this.total;
            this.totalnumber += iScore;
            strScore += iScore;
        }
        else {
            strFeedBack = strIncorrectFeedback;
            strCorrectIncorrect = "Incorrect";
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
            tr.addElement((Element)new td().setHeight(25).addElement("" + no));
        }
        if (stritem_name != null && !stritem_name.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strQues));
        }
        if (stritem_type != null && !stritem_type.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strCorrectIncorrect));
        }
        if (stritem_answer != null && !stritem_answer.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strCorrectAns));
        }
        if (stritem_marks != null && !stritem_marks.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement("" + iScore));
        }
        if (item_obmarks != null && !item_obmarks.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strScore));
        }
        if (stritem_feedback != null && !stritem_feedback.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strFeedBack));
        }
        return tr;
    }
    
    public tr MultipleResponseHTML(final Vector response, final Vector question, final String[] str1, final int no, final Hashtable hSectionExtention) {
        final tr tr = new tr();
        final String strQuesNo = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector multiAns = (Vector)question.elementAt(2);
        final Vector vCorrectAns = (Vector)question.elementAt(3);
        final int iScore = Integer.parseInt((String) response.elementAt(1));
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
        boolean b = false;
        if (str1 != null) {
            for (int j = 0; j < vCorrectAns.size(); ++j) {
                for (int k = 0; k < str1.length; ++k) {
                    final String str2 = (String) vCorrectAns.elementAt(j);
                    if (str2.equals(str1[k])) {
                        b = true;
                        break;
                    }
                    b = false;
                }
                if (!b) {
                    break;
                }
            }
        }
        if (b) {
            strFeedBack = strCorrectFeedback;
            strCorrectIncorrect = "Correct";
            ++this.total;
            this.totalnumber += iScore;
            strScore += iScore;
        }
        else {
            strFeedBack = strIncorrectFeedback;
            strCorrectIncorrect = "Incorrect";
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
        final String strItem_id = (String) hSectionExtention.get("item_id");
        final String stritem_name = (String) hSectionExtention.get("item_name");
        final String stritem_type = (String) hSectionExtention.get("item_type");
        final String stritem_answer = (String) hSectionExtention.get("item_answer");
        final String item_obmarks = (String) hSectionExtention.get("item_obmarks");
        final String stritem_marks = (String) hSectionExtention.get("item_marks");
        final String stritem_feedback = (String) hSectionExtention.get("item_feedback");
        if (strItem_id != null && !strItem_id.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement("" + no));
        }
        if (stritem_name != null && !stritem_name.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strQues));
        }
        if (stritem_type != null && !stritem_type.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strCorrectIncorrect));
        }
        if (stritem_answer != null && !stritem_answer.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strCorrectAns));
        }
        if (stritem_marks != null && !stritem_marks.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement("" + iScore));
        }
        if (item_obmarks != null && !item_obmarks.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strScore));
        }
        if (stritem_feedback != null && !stritem_feedback.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strFeedBack));
        }
        return tr;
    }
    
    public tr FillBlanks(final Vector response, final Vector question, final String str, final int no, final Hashtable hSectionExtention) {
        final tr tr = new tr();
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
        if (strCase.equalsIgnoreCase("No")) {
            if (strCorrectAns.trim().equalsIgnoreCase(str.trim())) {
                strFeedBack = strCorrectFeedback;
                strCorrectIncorrect = "Correct";
                ++this.total;
                this.totalnumber += iScore;
                strScore += iScore;
            }
            else {
                strFeedBack = strIncorrectFeedback;
                strCorrectIncorrect = "Incorrect";
                strScore += "X";
            }
        }
        else if (strCorrectAns.equals(str)) {
            strFeedBack = strCorrectFeedback;
            strCorrectIncorrect = "Correct";
            ++this.total;
            this.totalnumber += iScore;
            strScore += iScore;
        }
        else {
            strFeedBack = strIncorrectFeedback;
            strCorrectIncorrect = "Incorrect";
            strScore += "X";
        }
        final String strItem_id = (String) hSectionExtention.get("item_id");
        final String stritem_name = (String) hSectionExtention.get("item_name");
        final String stritem_type = (String) hSectionExtention.get("item_type");
        final String stritem_answer = (String) hSectionExtention.get("item_answer");
        final String item_obmarks = (String) hSectionExtention.get("item_obmarks");
        final String stritem_marks = (String) hSectionExtention.get("item_marks");
        final String stritem_feedback = (String) hSectionExtention.get("item_feedback");
        if (strItem_id != null && !strItem_id.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement("" + no));
        }
        if (stritem_name != null && !stritem_name.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strQues));
        }
        if (stritem_type != null && !stritem_type.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strCorrectIncorrect));
        }
        if (stritem_answer != null && !stritem_answer.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strCorrectAns));
        }
        if (stritem_marks != null && !stritem_marks.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement("" + iScore));
        }
        if (item_obmarks != null && !item_obmarks.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strScore));
        }
        if (stritem_feedback != null && !stritem_feedback.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strFeedBack));
        }
        return tr;
    }
    
    public tr EssayType(final Vector response, final Vector question, final String str, final int no, final Hashtable hSectionExtention) {
        final tr tr = new tr();
        final String strQuesNo = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final int iScore = Integer.parseInt((String) response.elementAt(1));
        this.totalScore += iScore;
        this.total_marks = iScore;
        this.item_id = strQuesNo;
        this.item_name = strQues;
        final String strItem_id = (String) hSectionExtention.get("item_id");
        final String stritem_name = (String) hSectionExtention.get("item_name");
        final String stritem_type = (String) hSectionExtention.get("item_type");
        final String stritem_answer = (String) hSectionExtention.get("item_answer");
        final String item_obmarks = (String) hSectionExtention.get("item_obmarks");
        final String stritem_marks = (String) hSectionExtention.get("item_marks");
        final String stritem_feedback = (String) hSectionExtention.get("item_feedback");
        if (strItem_id != null && !strItem_id.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement("" + no));
        }
        if (stritem_name != null && !stritem_name.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strQues));
        }
        if (stritem_type != null && !stritem_type.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement("NA"));
        }
        if (stritem_answer != null && !stritem_answer.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement("NA"));
        }
        if (stritem_marks != null && !stritem_marks.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement("" + iScore));
        }
        if (item_obmarks != null && !item_obmarks.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement("NA"));
        }
        if (stritem_feedback != null && !stritem_feedback.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement("NA"));
        }
        return tr;
    }
    
    public tr MultipleChoiceImage(final Vector response, final Vector question, final String str, final int no, final Hashtable hSectionExtention) {
        final tr tr = new tr();
        final String strItem_id = (String) hSectionExtention.get("item_id");
        final String stritem_name = (String) hSectionExtention.get("item_name");
        final String stritem_type = (String) hSectionExtention.get("item_type");
        final String stritem_answer = (String) hSectionExtention.get("item_answer");
        final String item_obmarks = (String) hSectionExtention.get("item_obmarks");
        final String stritem_marks = (String) hSectionExtention.get("item_marks");
        final String stritem_feedback = (String) hSectionExtention.get("item_feedback");
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
            ++this.total;
            this.totalnumber += iScore;
            strScore += iScore;
        }
        else {
            strFeedBack = strIncorrectFeedback;
            strCorrectIncorrect = "Incorrect";
            strScore += "X";
        }
        if (strItem_id != null && !strItem_id.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement("" + no));
        }
        if (stritem_name != null && !stritem_name.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strQues));
        }
        if (stritem_type != null && !stritem_type.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strCorrectIncorrect));
        }
        if (stritem_answer != null && !stritem_answer.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strCorrectAns));
        }
        if (stritem_marks != null && !stritem_marks.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement("" + iScore));
        }
        if (item_obmarks != null && !item_obmarks.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strScore));
        }
        if (stritem_feedback != null && !stritem_feedback.equals("")) {
            tr.addElement((Element)new td().setHeight(25).addElement(strFeedBack));
        }
        return tr;
    }
    
    static {
        log = new SimpleLogger((Class)OutComesSection.class, true);
    }
}
