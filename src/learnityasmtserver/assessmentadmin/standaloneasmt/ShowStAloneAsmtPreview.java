package learnityasmtserver.assessmentadmin.standaloneasmt;

import org.grlea.log.*;
import java.io.*;
import javax.servlet.*;
import learnityasmtserver.assessmentadmin.dbconnection.*;
import learnityasmtserver.assessmentportal.dbconnection.*;
import javax.servlet.http.*;
import java.util.*;

public class ShowStAloneAsmtPreview extends HttpServlet
{
    private static final SimpleLogger log;
    private static final String LOGIN_SESSION_NAME = "ADMIN_LOG_ON";
    private static final String student_id = "student_id";
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        String student_id1 = "";
        final PrintWriter out = response.getWriter();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        student_id1 = "superadmin";
        if (student_id1 != null) {
            this.getResult(request, response, out, student_id1);
        }
    }
    
    public void getResult(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out, String student_id2) throws IOException, ServletException {
        final HttpSession mysession = request.getSession(true);
        student_id2 = (String)mysession.getAttribute("user_id");
        System.out.println("student_id2==ShowStAloneAsmtPreview==" + student_id2);
        final String student_id3 = student_id2 + "asmtprv";
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
        if (student_id3 != null) {
            mysession.removeAttribute("student_id");
        }
        mysession.setAttribute("student_id", (Object)student_id3);
        final String checkbox = request.getParameter("checkbox");
        System.out.println("**********assessmentid(checkbox)************************" + checkbox);
        final Boolean flag1 = DataBaseLayer.checkStudentDetails(student_id3);
        final Boolean flag2 = DataBaseLayer.checkAsmtRegistration(student_id3);
        if (!flag1) {
            DataBaseLayer.insertStudentDetails(student_id3, student_id2);
        }
        if (!flag2) {
            DataBaseLayer.autoAsmtRegistration(student_id3, checkbox, student_id2);
        }
        System.out.println("student_id1=====" + student_id3);
        final ReportDataBaseLayer ob1 = new ReportDataBaseLayer();
        final Vector<String[]> vAssessmentInfo = ob1.getAssessmentInfo1(student_id3);
        System.out.println("**********checkbox(assessmentid)************************" + checkbox);
        //final String counter = request.getParameter("counter");
        final String checkModelstatus = "checkModelstatus";
        String avail_time = "";
        String avail = "";
        //String valid = "";
        String avail_date = "";
        //String valid_date = "";
        String timeavailable = "";
        final String lastquesindex = "0";
        if (checkbox != null && vAssessmentInfo.size() != 0) {
            final Vector<String[]> vAssessmentAvailablity = ReportDataBaseLayer.getAssessmentAvailablity(student_id3, checkbox);
            if (vAssessmentAvailablity.size() != 0) {
                final String[] AssessmentAvailablity = vAssessmentAvailablity.elementAt(0);
                avail_time = AssessmentAvailablity[0];
                avail_date = AssessmentAvailablity[1];
                avail = AssessmentAvailablity[2];
                //valid_date = AssessmentAvailablity[3];
                //valid = AssessmentAvailablity[4];
                final String stdate = strDate2 + " " + "00:00:00";
                int t1 = 0;
                int t2 = 0;
                int t3 = 0;
                int t4 = 0;
                if (avail.equals("Available") && avail_date.equals(stdate) && !avail_time.equals("")) {
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
        final Vector<String[]> vAssessmentInfogroup = ob1.getAssessmentInfogroup(student_id3);
        if (checkbox != null && vAssessmentInfogroup.size() != 0) {
            final Vector<String[]> vAssessmentAvailablity2 = ReportDataBaseLayer.getAssessmentGroupAvailablity(student_id3, checkbox);
            if (vAssessmentAvailablity2.size() != 0) {
                final String[] AssessmentAvailablity2 = vAssessmentAvailablity2.elementAt(0);
                avail_time = AssessmentAvailablity2[0];
                avail_date = AssessmentAvailablity2[1];
                avail = AssessmentAvailablity2[2];
                //valid_date = AssessmentAvailablity2[3];
                //valid = AssessmentAvailablity2[4];
                final String stdate2 = strDate2 + " " + "00:00:00";
                int t5 = 0;
                int t6 = 0;
                int t7 = 0;
                int t8 = 0;
                if (avail.equals("Available") && avail_date.equals(stdate2) && !avail_time.equals("")) {
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
        String MaxtestTeken = "";
        String nooftimesappeared = "0";
        int nooftimesappeared2 = 0;
        int MaxtestTeken2 = 0;
        String astatus = "";
        String save_state = "";
        if (checkbox != null) {
            MaxtestTeken = ob1.getMaxtestTaken(checkbox);
            nooftimesappeared = ob1.getMaxNoAppearedInTest(student_id3, checkbox);
            final Vector<String> vv = ob1.isPreviousTestSubmitted(student_id3, checkbox);
            if (vv.size() != 0) {
                astatus = vv.elementAt(0);
                save_state = vv.elementAt(1);
            }
            MaxtestTeken2 = Integer.parseInt(MaxtestTeken);
            nooftimesappeared2 = Integer.parseInt(nooftimesappeared);
        }
        System.out.println("MaxtestTeken1==1==" + MaxtestTeken2);
        System.out.println("nooftimesappeared1==1==" + nooftimesappeared2);
        System.out.println("save_state==1==" + save_state);
        if (save_state == null) {
            save_state = "";
        }
        else {
            save_state = "no";
        }
        System.out.println("save_state==2==" + save_state);
        //final String ms = "You have already appeared for this test. You cannot appear for it again. [ Number of times appeared :- " + MaxtestTeken + " ]";
        //final String script = "";
        final String browserName = request.getParameter("browserName");
        if (nooftimesappeared2 < MaxtestTeken2) {
            if (browserName != null && browserName.equals("Microsoft Internet Explorer")) {
                if (astatus.equals("") || astatus.equals("Submitted")) {
                    response.sendRedirect("./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTestHTML?newtest=start&checkModelstatus=" + checkModelstatus + "&assessment_id=" + checkbox + "#" + lastquesindex + "");
                }
                else if (save_state.equals("")) {
                    response.sendRedirect("./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTestHTML?newtest=start&save_state=no&checkModelstatus=" + checkModelstatus + "&assessment_id=" + checkbox + "#" + lastquesindex + "");
                }
                else {
                    response.sendRedirect("./learnityasmtserver.assessmentportal.standaloneasmt.GenerateTestHTML?newtest=start&checkModelstatus=" + checkModelstatus + "&assessment_id=" + checkbox + "#" + lastquesindex + "");
                }
            }
            else if (astatus.equals("") || astatus.equals("Submitted")) {
                response.sendRedirect("./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTest?newtest=start&checkModelstatus=" + checkModelstatus + "&assessment_id=" + checkbox + "#" + lastquesindex + "");
            }
            else if (save_state.equals("")) {
                response.sendRedirect("./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTest?newtest=start&save_state=no&checkModelstatus=" + checkModelstatus + "&assessment_id=" + checkbox + "#" + lastquesindex + "");
            }
            else {
                response.sendRedirect("./learnityasmtserver.assessmentportal.standaloneasmt.GenerateTestXHTML?newtest=start&checkModelstatus=" + checkModelstatus + "&assessment_id=" + checkbox + "#" + lastquesindex + "");
            }
        }
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    static {
        log = new SimpleLogger((Class)ShowStAloneAsmtPreview.class);
    }
}
