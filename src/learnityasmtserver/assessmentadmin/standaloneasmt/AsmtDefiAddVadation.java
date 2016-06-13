package learnityasmtserver.assessmentadmin.standaloneasmt;

import interfaceenginev2.*;
import org.grlea.log.*;

import comv2.aunwesha.lfutil.Pair;

import javax.servlet.http.*;
import learnityasmtserver.assessmentadmin.dbconnection.*;
import java.util.*;

public class AsmtDefiAddVadation implements ValidatorFunction
{
    public static final SimpleLogger log;
    
    public Pair<Boolean,String> validateadd(final HttpServletRequest request) {
        Boolean b = false;
        String message = "";
        //final String AssessTitleSelect = request.getParameter("assessment_id");
        //final int assessment_id = Integer.parseInt(AssessTitleSelect);
        final String no_questions = request.getParameter("no_of_questions");
        final int no_ques_per_topic = Integer.parseInt(no_questions);
        final String select_topic = request.getParameter("topic_id");
        final String idlebel = request.getParameter("degree_of_difficulty");
        final String question_type = request.getParameter("question_type");
        //final String admin = request.getParameter("current_login_user_id");
        final Vector<String> vQB_id = DataBaseLayer.getQuesBankId(select_topic);
        final int qbsize = vQB_id.size();
        if (qbsize != 0) {
            final Vector<String> vIList = new Vector<String>();
            for (int kk = 0; kk < vQB_id.size(); ++kk) {
                final String quesbankid = vQB_id.elementAt(kk);
                final Vector<String> vIList2 = DataBaseLayer.getItemList(quesbankid);
                for (int ii = 0; ii < vIList2.size(); ++ii) {
                    final String id = vIList2.elementAt(ii);
                    vIList.addElement(id);
                }
            }
            final int itemNo = vIList.size();
            if (itemNo >= no_ques_per_topic) {
                if (!question_type.equals("Any")) {
                    final Vector<String> vIList3 = new Vector<String>();
                    for (int k = 0; k < vIList.size(); ++k) {
                        final String id2 = vIList.elementAt(k);
                        int t = 0;
                        t = DataBaseLayer.getIteIDmsType(id2, question_type);
                        if (t != 0) {
                            vIList3.addElement(id2);
                        }
                    }
                    if (vIList3.size() >= no_ques_per_topic) {
                        if (!idlebel.equals("Any")) {
                            final Vector<String> vIList4 = new Vector<String>();
                            for (int k2 = 0; k2 < vIList3.size(); ++k2) {
                                final String id3 = vIList3.elementAt(k2);
                                int d = 0;
                                d = DataBaseLayer.getIteIDmslevel(id3, idlebel);
                                if (d != 0) {
                                    vIList4.addElement(id3);
                                }
                            }
                            if (vIList4.size() >= no_ques_per_topic) {
                                b = true;
                                message = "Successful";
                            }
                            else {
                                b = false;
                                message = "Total no.of questions available in selected topic when Question Type is " + question_type + " and Difficulty level is " + idlebel + " is " + vIList4.size();
                            }
                        }
                        else {
                            b = true;
                            message = "Successful";
                        }
                    }
                    else {
                        b = false;
                        message = "Total no.of questions available in selected topic when Question Type is " + question_type + " is " + vIList3.size();
                    }
                }
                else if (!idlebel.equals("Any")) {
                    final Vector<String> vIList5 = new Vector<String>();
                    for (int k3 = 0; k3 < vIList.size(); ++k3) {
                        final String id4 = vIList.elementAt(k3);
                        int d2 = 0;
                        d2 = DataBaseLayer.getIteIDmslevel(id4, idlebel);
                        if (d2 != 0) {
                            vIList5.addElement(id4);
                        }
                    }
                    if (vIList5.size() >= no_ques_per_topic) {
                        b = true;
                        message = "Successful";
                    }
                    else {
                        b = false;
                        message = "Total no.of questions available in selected topic when Question Type is Any and Difficulty level is " + idlebel + " is " + vIList5.size();
                    }
                }
                else {
                    b = true;
                    message = "Successful";
                }
            }
            else {
                b = false;
                message = "Total no.of questions available in selected topic is " + itemNo;
            }
        }
        else {
            b = false;
            message = "First Associates Selected topic with Question Bank";
        }
        System.out.println("b==add==" + b);
        return new Pair<Boolean,String>(b, message);   
        }
    
    public Pair<Boolean,String> validateedit(final HttpServletRequest request) {
        Boolean b = false;
        String message = "";
        //final String AssessTitleSelect = request.getParameter("assessment_id");
        //final int assessment_id = Integer.parseInt(AssessTitleSelect);
        final String no_questions = request.getParameter("no_of_questions");
        final int no_ques_per_topic = Integer.parseInt(no_questions);
        final String select_topic = request.getParameter("topic_id");
        final String idlebel = request.getParameter("degree_of_difficulty");
        final String question_type = request.getParameter("question_type");
        //final String admin = request.getParameter("current_login_user_id");
        final Vector<String> vQB_id = DataBaseLayer.getQuesBankId(select_topic);
        final int qbsize = vQB_id.size();
        if (qbsize != 0) {
            final Vector<String> vIList = new Vector<String>();
            for (int kk = 0; kk < vQB_id.size(); ++kk) {
                final String quesbankid = vQB_id.elementAt(kk);
                final Vector<String> vIList2 = DataBaseLayer.getItemList(quesbankid);
                for (int ii = 0; ii < vIList2.size(); ++ii) {
                    final String id = vIList2.elementAt(ii);
                    vIList.addElement(id);
                }
            }
            final int itemNo = vIList.size();
            if (itemNo >= no_ques_per_topic) {
                if (!question_type.equals("Any")) {
                    final Vector<String> vIList3 = new Vector<String>();
                    for (int k = 0; k < vIList.size(); ++k) {
                        final String id2 = vIList.elementAt(k);
                        int t = 0;
                        t = DataBaseLayer.getIteIDmsType(id2, question_type);
                        if (t != 0) {
                            vIList3.addElement(id2);
                        }
                    }
                    if (vIList3.size() >= no_ques_per_topic) {
                        if (!idlebel.equals("Any")) {
                            final Vector<String> vIList4 = new Vector<String>();
                            for (int k2 = 0; k2 < vIList3.size(); ++k2) {
                                final String id3 = vIList3.elementAt(k2);
                                int d = 0;
                                d = DataBaseLayer.getIteIDmslevel(id3, idlebel);
                                if (d != 0) {
                                    vIList4.addElement(id3);
                                }
                            }
                            if (vIList4.size() >= no_ques_per_topic) {
                                b = true;
                                message = "Successful";
                            }
                            else {
                                b = false;
                                message = "Total no.of questions available in selected topic when Question Type is " + question_type + " and Difficulty level is " + idlebel + " is " + vIList4.size();
                            }
                        }
                        else {
                            b = true;
                            message = "Successful";
                        }
                    }
                    else {
                        b = false;
                        message = "Total no.of questions available in selected topic when Question Type is " + question_type + " is " + vIList3.size();
                    }
                }
                else if (!idlebel.equals("Any")) {
                    final Vector<String> vIList5 = new Vector<String>();
                    for (int k3 = 0; k3 < vIList.size(); ++k3) {
                        final String id4 = vIList.elementAt(k3);
                        int d2 = 0;
                        d2 = DataBaseLayer.getIteIDmslevel(id4, idlebel);
                        if (d2 != 0) {
                            vIList5.addElement(id4);
                        }
                    }
                    if (vIList5.size() >= no_ques_per_topic) {
                        b = true;
                        message = "Successful";
                    }
                    else {
                        b = false;
                        message = "Total no.of questions available in selected topic when Question Type is Any and Difficulty level is " + idlebel + " is " + vIList5.size();
                    }
                }
                else {
                    b = true;
                    message = "Successful";
                }
            }
            else {
                b = false;
                message = "Total no.of questions available in selected topic is " + itemNo;
            }
        }
        else {
            b = false;
            message = "First Associates Selected topic with Question Bank";
        }
        System.out.println("b===edit=" + b);
        return new Pair<Boolean,String>(b, message);
  } 

	public Pair<Boolean,String> validatedelete(HttpServletRequest request)
	{return new Pair<Boolean,String>(false, "");}

    static {
        log = new SimpleLogger((Class)AsmtDefiAddVadation.class, true);
    }
}
