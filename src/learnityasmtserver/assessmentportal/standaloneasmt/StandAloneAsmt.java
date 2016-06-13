package learnityasmtserver.assessmentportal.standaloneasmt;

import org.grlea.log.*;
import learnityasmtserver.assessmentportal.dbconnection.*;
import org.directwebremoting.*;
import javax.servlet.http.*;
import java.util.*;

public class StandAloneAsmt
{
    public static final SimpleLogger log;
    
    public String[] asmtAvailablityCheck() {
        final Calendar calendar = new GregorianCalendar();
        final Date trialTime = new Date();
        calendar.setTime(trialTime);
        final String[] months = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        final String mimute11 = Integer.toString(calendar.get(12));
        String mimute2 = "";
        if (mimute11.length() != 2) {
            mimute2 = "0" + mimute11;
        }
        else {
            mimute2 = mimute11;
        }
        final String sec11 = Integer.toString(calendar.get(13));
        String sec2 = "";
        if (sec11.length() != 2) {
            sec2 = "0" + sec11;
        }
        else {
            sec2 = sec11;
        }
        final String strTime = calendar.get(10) + ":" + mimute2 + ":" + sec2;
        final String strDate = months[calendar.get(2)] + " " + calendar.get(5) + ", " + calendar.get(1);
        final String month11 = Integer.toString(calendar.get(2) + 1);
        String month2 = "";
        if (month11.length() != 2) {
            month2 = "0" + month11;
        }
        else {
            month2 = month11;
        }
        final String date11 = Integer.toString(calendar.get(5));
        String date2 = "";
        if (date11.length() != 2) {
            date2 = "0" + date11;
        }
        else {
            date2 = date11;
        }
        final String strDate2 = calendar.get(1) + "-" + month2 + "-" + date2;
        final String ampm = "" + calendar.get(9);
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String student_id = (String)mysession.getAttribute("user_id");
        final String checkbox = (String)mysession.getAttribute("assessmentid");
        StandAloneAsmt.log.debug("asmtAvailablityCheck==assessmentid== " + checkbox);
        final String checkModelstatus = "checkModelstatus";
        String avail_time = "";
        String avail = "";
        String valid = "";
        String avail_date = "";
        String valid_date = "";
        String timeavailable = "";
        final AsmtPortalDataBaseLayer ob1 = new AsmtPortalDataBaseLayer();
        final Vector vAssessmentInfo = ob1.getAssessmentInfo1(student_id);
        if (checkbox != null && vAssessmentInfo.size() != 0) {
            final Vector<String[]> vAssessmentAvailablity = AsmtPortalDataBaseLayer.getAssessmentAvailablity(student_id, checkbox);
            if (vAssessmentAvailablity.size() != 0) {
                final String[] AssessmentAvailablity = vAssessmentAvailablity.elementAt(0);
                avail_time = AssessmentAvailablity[0];
                avail_date = AssessmentAvailablity[1];
                avail = AssessmentAvailablity[2];
                valid_date = AssessmentAvailablity[3];
                valid = AssessmentAvailablity[4];
                final String avail_date2 = avail_date.substring(0, avail_date.indexOf(" "));
                int t1 = 0;
                int t2 = 0;
                int t3 = 0;
                int t4 = 0;
                if (avail.equals("Available") && avail_date2.equals(strDate2) && !avail_time.equals("")) {
                    final String str1 = avail_time.substring(0, avail_time.indexOf(58));
                    final String str2 = avail_time.substring(avail_time.indexOf(58) + 1);
                    final String str3 = str2.substring(0, str2.indexOf(58));
                    final String str4 = strTime.substring(0, strTime.indexOf(58));
                    final String str5 = strTime.substring(strTime.indexOf(58) + 1);
                    final String str6 = str5.substring(0, str5.indexOf(58));
                    t1 = Integer.parseInt(str1);
                    t2 = Integer.parseInt(str3);
                    final int timeformat1 = Integer.parseInt(str4);
                    if (ampm.equals("1") && timeformat1 <= 12) {
                        t3 = Integer.parseInt(str4) + 12;
                        t4 = Integer.parseInt(str6);
                    }
                    else {
                        t3 = Integer.parseInt(str4);
                        t4 = Integer.parseInt(str6);
                    }
                }
                if (t1 <= t3) {
                    if (t1 < t3) {
                        timeavailable = "Available";
                    }
                    else if (t2 <= t4) {
                        timeavailable = "Available";
                    }
                    else {
                        timeavailable = "Not Available";
                    }
                }
                if (t1 > t3) {
                    timeavailable = "Not Available";
                }
            }
        }
        final Vector vAssessmentInfogroup = ob1.getAssessmentInfogroup(student_id);
        if (checkbox != null && vAssessmentInfogroup.size() != 0) {
            final Vector<String[]> vAssessmentAvailablity2 = AsmtPortalDataBaseLayer.getAssessmentGroupAvailablity(student_id, checkbox);
            if (vAssessmentAvailablity2.size() != 0) {
                final String[] AssessmentAvailablity2 = vAssessmentAvailablity2.elementAt(0);
                avail_time = AssessmentAvailablity2[0];
                avail_date = AssessmentAvailablity2[1];
                avail = AssessmentAvailablity2[2];
                valid_date = AssessmentAvailablity2[3];
                valid = AssessmentAvailablity2[4];
                final String avail_date3 = avail_date.substring(0, avail_date.indexOf(" "));
                int t5 = 0;
                int t6 = 0;
                int t7 = 0;
                int t8 = 0;
                if (avail.equals("Available") && avail_date3.equals(strDate2) && !avail_time.equals("")) {
                    final String str7 = avail_time.substring(0, avail_time.indexOf(58));
                    final String str8 = avail_time.substring(avail_time.indexOf(58) + 1);
                    final String str9 = str8.substring(0, str8.indexOf(58));
                    final String str10 = strTime.substring(0, strTime.indexOf(58));
                    final String str11 = strTime.substring(strTime.indexOf(58) + 1);
                    final String str12 = str11.substring(0, str11.indexOf(58));
                    t5 = Integer.parseInt(str7);
                    t6 = Integer.parseInt(str9);
                    final int timeformat2 = Integer.parseInt(str10);
                    if (ampm.equals("1") && timeformat2 <= 12) {
                        t7 = Integer.parseInt(str10) + 12;
                        t8 = Integer.parseInt(str12);
                    }
                    else {
                        t7 = Integer.parseInt(str10);
                        t8 = Integer.parseInt(str12);
                    }
                }
                if (t5 <= t7) {
                    if (t5 < t7) {
                        timeavailable = "Available";
                    }
                    else if (t6 <= t8) {
                        timeavailable = "Available";
                    }
                    else {
                        timeavailable = "Not Available";
                    }
                }
                if (t5 > t7) {
                    timeavailable = "Not Available";
                }
            }
        }
        StandAloneAsmt.log.debug("timeavailable==1==" + timeavailable);
        String MaxtestTeken = "";
        String nooftimesappeared = "0";
        String astatus = "";
        String save_state = "";
        String pretest_id = "";
        if (checkbox != null) {
            MaxtestTeken = ob1.getMaxtestTaken(checkbox);
            nooftimesappeared = ob1.getMaxNoAppearedInTest(student_id, checkbox);
            final Vector<String> vv = ob1.isPreviousTestSubmitted(student_id, checkbox);
            if (vv.size() != 0) {
                astatus = vv.elementAt(0);
                save_state = vv.elementAt(1);
                pretest_id = vv.elementAt(2);
            }
        }
        StandAloneAsmt.log.debug("save_state==1==" + save_state);
        if (save_state == null) {
            save_state = "";
        }
        else {
            save_state = "no";
        }
        StandAloneAsmt.log.debug("save_state==2==" + save_state);
        final String ms = "You have already appeared for this test. You cannot appear for it again. [ Number of times appeared :- " + MaxtestTeken + " ]";
        final String[] a1 = new String[12];
        a1[0] = checkModelstatus;
        StandAloneAsmt.log.debug("a1[0]==" + a1[0]);
        a1[1] = timeavailable;
        StandAloneAsmt.log.debug("a1[1]==" + a1[1]);
        a1[2] = avail;
        StandAloneAsmt.log.debug("a1[2]==" + a1[3]);
        a1[3] = valid;
        StandAloneAsmt.log.debug("a1[3]==" + a1[3]);
        a1[4] = MaxtestTeken;
        StandAloneAsmt.log.debug("a1[4]==" + a1[4]);
        a1[5] = nooftimesappeared;
        StandAloneAsmt.log.debug("a1[5]==" + a1[5]);
        a1[6] = ms;
        StandAloneAsmt.log.debug("a1[6]==" + a1[6]);
        a1[7] = astatus;
        StandAloneAsmt.log.debug("a1[7]==" + a1[7]);
        a1[8] = save_state;
        StandAloneAsmt.log.debug("a1[8]==" + a1[8]);
        a1[9] = student_id;
        StandAloneAsmt.log.debug("a1[9]==" + a1[9]);
        a1[10] = checkbox;
        StandAloneAsmt.log.debug("a1[10]==" + a1[10]);
        a1[11] = pretest_id;
        StandAloneAsmt.log.debug("a1[11]==" + a1[11]);
        return a1;
    }
    
    static {
        log = new SimpleLogger((Class)StandAloneAsmt.class, true);
    }
}
