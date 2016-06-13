package learnityasmtserver.assessmentportal.standaloneasmt;

import org.grlea.log.*;
import java.util.*;

import org.apache.ecs.*;
import org.apache.ecs.xhtml.*;

public class Question_Generation
{
    public static final SimpleLogger log;
    
    public String MultipleChoiceHTML(final Vector response, final Vector question, final int no1, final String selectop, final String titleType) {
        final String no2 = String.valueOf(no1);
        final StringBuffer strHTML = new StringBuffer();
        final String strQuesNo = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector multiAns = (Vector)question.elementAt(2);
        final Vector vFeedback = (Vector) response.elementAt(2);
        String strCorrectFeedback = "";
        String strIncorrectFeedback = "";
        final String strbraces = ")  ";
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
        final table table3 = new table().setCellPadding(0).setCellSpacing(4).setWidth("100%");
        table3.addElement((Element)new tr().addElement((Element)new td().addElement(new a().setName(no2).addElement(no2).addElement(strbraces).addElement(strQues).setClass("QuestionOption"))));
        final tr ttrr1 = new tr();
        final String[] alp = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
        for (int j = 0; j < multiAns.size(); ++j) {
            final Vector label = (Vector) multiAns.elementAt(j);
            final Character cLabel = (Character) label.elementAt(0);
            final String strAns = (String) label.elementAt(1);
            final int q_no1 = j + 1;
            final String q_no2 = String.valueOf(q_no1);
            final String cLabel2 = cLabel.toString();
            if (selectop != null && selectop.equals(cLabel2)) {
                ttrr1.addElement((Element)new tr().addElement(new td().setWidth(490).setAlign("left").addElement("<input name=\"" + strQuesNo + "\" onclick=\"return lastattemptedquestion_onclick(" + no2 + ")\" type=\"radio\" value=\"" + cLabel.toString() + "\" checked=\"checked\" />").addElement(alp[j]).addElement(strbraces).addElement(strAns).setClass("AnswerOption")));
            }
            else {
                ttrr1.addElement((Element)new tr().addElement(new td().setWidth(490).setAlign("left").addElement("<input name=\"" + strQuesNo + "\" onclick=\"return lastattemptedquestion_onclick(" + no2 + ")\" type=\"radio\" value=\"" + cLabel.toString() + "\"/>").addElement(alp[j]).addElement(strbraces).addElement(strAns).setClass("AnswerOption")));
            }
        }
        table3.addElement((Element)new tr().addElement((Element)new td().setWidth(560).addElement((Element)new table().setBorder(0).setCellPadding(0).setCellSpacing(4).setWidth(607).addElement((Element)ttrr1))));
        return table3.toString();
    }
    
    public String MultipleResponseHTML(final Vector response, final Vector question, final int no1, final String[] selectop1) {
        final String no2 = String.valueOf(no1);
        final StringBuffer strHTML = new StringBuffer();
        final String strQuesNo = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector multiAns = (Vector)question.elementAt(2);
        final Vector vCorrectAns = (Vector)question.elementAt(3);
        final Vector vFeedback = (Vector) response.elementAt(2);
        String strCorrectFeedback = "";
        String strIncorrectFeedback = "";
        final String strbraces = ")  ";
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
        final table table3 = new table().setCellPadding(0).setCellSpacing(4).setWidth("100%");
        final table table2 = new table().setBorder(0).setCellPadding(0).setCellSpacing(4).setWidth(607);
        table3.addElement((Element)new tr().addElement(new td().addElement(no2).addElement(strbraces).addElement(strQues).setClass("QuestionOption")));
        table3.addElement((Element)new tr().addElement((Element)new td().setWidth(560).addElement((Element)table2)));
        final String[] alp = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
        for (int j = 0; j < multiAns.size(); ++j) {
            final int q_no1 = j + 1;
            final String q_no2 = String.valueOf(q_no1);
            final Vector label = (Vector) multiAns.elementAt(j);
            final Character cLabel = (Character) label.elementAt(0);
            final String strAns = (String) label.elementAt(1);
            final String cLabel2 = cLabel.toString();
            int checked = 0;
            if (selectop1 != null) {
                for (int k = 0; k < selectop1.length; ++k) {
                    final String check = selectop1[k];
                    if (check != null && check.equals(cLabel2)) {
                        checked = 1;
                    }
                }
            }
            if (checked != 1) {
                table2.addElement((Element)new tr().addElement(new td().setWidth(490).setAlign("left").addElement("<input name=\"" + strQuesNo + "\" onclick=\"return lastattemptedquestion_onclick(" + no2 + ")\" type=\"checkbox\" value=\"" + cLabel.toString() + "\"/>").addElement(alp[j]).addElement(strbraces).addElement(strAns).setClass("AnswerOption")));
            }
            else {
                table2.addElement((Element)new tr().addElement(new td().setWidth(490).setAlign("left").addElement("<input name=\"" + strQuesNo + "\" onclick=\"return lastattemptedquestion_onclick(" + no2 + ")\" type=\"checkbox\" value=\"" + cLabel.toString() + "\" checked=\"checked\"/>").addElement(alp[j]).addElement(strbraces).addElement(strAns).setClass("AnswerOption")));
            }
        }
        for (int l = 0; l < vCorrectAns.size(); ++l) {
            String strLabel = "";
            final String strCorrectAns = (String) vCorrectAns.elementAt(l);
            for (int m = 0; m < multiAns.size(); ++m) {
                final Vector label2 = (Vector) multiAns.elementAt(m);
                final Character cLabel3 = (Character) label2.elementAt(0);
                final String strAns2 = (String) label2.elementAt(1);
                if (strCorrectAns.equals(strAns2)) {
                    strLabel = cLabel3.toString();
                }
            }
        }
        return table3.toString();
    }
    
    public String FillBlanks(final Vector response, final Vector question, final int no1, final String selectop) {
        final String no2 = String.valueOf(no1);
        final StringBuffer strHTML = new StringBuffer();
        final String strQuesNo = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector multiAns = (Vector)question.elementAt(2);
        final Vector vFeedback = (Vector) response.elementAt(2);
        String strCorrectFeedback = "";
        String strIncorrectFeedback = "";
        final String strbraces = ")  ";
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
        final table table3 = new table().setCellPadding(0).setCellSpacing(4).setWidth("100%");
        table3.addElement((Element)new tr().addElement(new td().setWidth(560).addElement(no2).addElement(strbraces).addElement(strQues).setClass("QuestionOption")));
        if (selectop != null) {
            table3.addElement((Element)new tr().addElement((Element)new td().setWidth(490).addElement((Element)new table().setBorder(0).setCellPadding(0).setCellSpacing(4).setWidth(490).addElement((Element)new tr().addElement((Element)new td().setWidth(47).setAlign("right")).addElement(new td().setWidth(490).addElement("<input name=\"" + strQuesNo + "\" type=\"text\" onFocus=\"lastattemptedquestion_onclick(" + no2 + ");\" value=\"" + selectop + "\"/>").setClass("AnswerOption"))))));
        }
        else {
            table3.addElement((Element)new tr().addElement((Element)new td().setWidth(490).addElement((Element)new table().setBorder(0).setCellPadding(0).setCellSpacing(4).setWidth(490).addElement((Element)new tr().addElement((Element)new td().setWidth(47).setAlign("right")).addElement(new td().setWidth(490).addElement("<input name=\"" + strQuesNo + "\" type=\"text\" onFocus=\"lastattemptedquestion_onclick(" + no2 + ");\"/>").setClass("AnswerOption"))))));
        }
        return table3.toString();
    }
    
    public String MatchingQuestionHTML(final Vector response, final Vector question, final int no1, final Vector vSelecttop) {
        final String no2 = String.valueOf(no1);
        final StringBuffer strHTML = new StringBuffer();
        final String strQuesNo = (String) question.elementAt(0);
        final String strQues = (String) question.elementAt(1);
        final Vector vOption = (Vector)question.elementAt(2);
        final Vector leftoption = (Vector) vOption.elementAt(0);
        final Vector rightoption = (Vector) vOption.elementAt(1);
        final Vector vFeedback = (Vector) response.elementAt(2);
        String strCorrectFeedback = "";
        String strIncorrectFeedback = "";
        final String strbraces = ")  ";
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
        final table table3 = new table().setCellPadding(0).setCellSpacing(4).setWidth("100%");
        table3.addElement((Element)new tr().addElement(new td().addElement(no2).addElement(strbraces).addElement(strQues).setClass("QuestionOption")));
        final tr ttrr1 = new tr();
        final tr ttrr2 = new tr();
        final table tblematch = new table();
        final table tblematch2 = new table();
        final tr ttrr3 = new tr();
        final table ttdd11 = new table();
        final table ttdd2 = new table();
        ttrr1.addElement((Element)tblematch2);
        tblematch2.addElement((Element)ttrr3);
        ttrr1.addElement((Element)new td().addElement((Element)ttdd11)).addElement((Element)new td().addElement((Element)ttdd2));
        ttrr2.addElement((Element)tblematch);
        ttdd11.addElement(new tr().addElement("<u>Left Choice</u>").setClass("headingMatchingtype"));
        ttdd2.addElement(new tr().addElement("<u>Right Choice</u>").setClass("headingMatchingtype"));
        final String[] alp = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
        final String[] alp2 = { "L1", "L2", "L3", "L4", "L5", "L6", "L7", "L8", "L9", "L10" };
        final String[] alp3 = { "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R10" };
        String noalp = "";
        tblematch.addElement(new tr().addElement("Match Left and Right").setClass("headingMatchingtype"));
        for (int j = 0; j < leftoption.size(); ++j) {
            final String[] leftOP1 = (String[]) leftoption.elementAt(j);
            final option[] option1 = new option[leftoption.size() + 1];
            option1[0] = new option("0").addElement("Choose One");
            for (int j2 = 0; j2 < leftoption.size(); ++j2) {
                final String[] leftOP2 = (String[]) leftoption.elementAt(j2);
                option1[j2 + 1] = new option(leftOP2[1]).addElement(alp2[j2]);
                if (vSelecttop.size() != 0) {
                    final String[] leftAns = (String[]) vSelecttop.elementAt(0);
                    Question_Generation.log.debug("===leftOP[0]=========" + leftOP2[0]);
                    Question_Generation.log.debug("===leftOP[1]=========" + leftOP2[1]);
                    Question_Generation.log.debug("===leftAns=========" + leftAns[j]);
                    if (leftOP2[1].equals(leftAns[j])) {
                        option1[j2 + 1].setSelected(true);
                    }
                }
            }
            ttdd11.addElement(new tr().addElement(alp2[j]).addElement(" : ").addElement(leftOP1[1]).setClass("AnswerOptionl"));
            final String[] ritOP1 = (String[]) rightoption.elementAt(j);
            final option[] option2 = new option[rightoption.size() + 1];
            option2[0] = new option("0").addElement("Choose One");
            for (int j3 = 0; j3 < rightoption.size(); ++j3) {
                final String[] ritOP2 = (String[]) rightoption.elementAt(j3);
                option2[j3 + 1] = new option(ritOP2[1]).addElement(alp3[j3]);
                if (vSelecttop.size() != 0) {
                    final String[] rightAns = (String[]) vSelecttop.elementAt(1);
                    Question_Generation.log.debug("===ritOP[0]=========" + ritOP2[0]);
                    Question_Generation.log.debug("===ritOP[1]=========" + ritOP2[1]);
                    Question_Generation.log.debug("===rightAns=========" + rightAns[j]);
                    if (ritOP2[1].equals(rightAns[j])) {
                        option2[j3 + 1].setSelected(true);
                    }
                }
            }
            ttdd2.addElement(new tr().addElement(alp3[j]).addElement(" : ").addElement(ritOP1[1]).setClass("AnswerOptionr"));
            noalp = alp[j];
            final String type1 = "l" + strQuesNo + noalp;
            final String type2 = "r" + strQuesNo + noalp;
            tblematch.addElement((Element)new tr().addElement(new td().addElement(alp[j]).addElement(strbraces).addElement((Element)new select().setName(type1).setTabindex(1).addElement(option1)).setClass("AnswerOptionleft")).addElement((Element)new td()).addElement((Element)new td()).addElement((Element)new td()).addElement(new td().addElement((Element)new select().setName(type2).setTabindex(1).addElement(option2)).setClass("AnswerOptionright")));
        }
        table3.addElement((Element)new tr().addElement((Element)new td().setWidth(560).addElement((Element)new table().setBorder(0).setCellPadding(0).setCellSpacing(4).setWidth(607).addElement((Element)ttrr1)))).addElement((Element)new tr().addElement((Element)new td().setWidth(560).addElement((Element)new table().setBorder(0).setCellPadding(0).setCellSpacing(4).setWidth(607).addElement((Element)ttrr2))));
        return table3.toString();
    }
    
    static {
        log = new SimpleLogger((Class)Question_Generation.class, true);
    }
}
