package learnitycms;

import org.grlea.log.*;
import javax.servlet.http.*;
import javax.servlet.*;
import learnityInit.*;
import org.directwebremoting.*;
import org.directwebremoting.io.*;
import interfaceenginev2.*;
import jxl.*;
import java.util.*;

import org.apache.axis.encoding.Base64;
import org.apache.xerces.parsers.*;
import java.util.zip.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.adl.samplerte.server.*;
import org.xml.sax.*;
import java.io.*;

public class cms
{
    public static final SimpleLogger log;
    
    public String setCourseName() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        return DataBaseLayer.returncoursename((String)session.getAttribute("course_id"));
    }
    
    public String getCourseId() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        return (String)session.getAttribute("course_id");
    }
    
    public String getInstructors() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        return DataBaseLayer.returnInstructors((String)session.getAttribute("course_id"));
    }
    
    public String getInstructor1() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        return DataBaseLayer.returnins1((String)session.getAttribute("course_id"));
    }
    
    public String getInstructor2() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        return DataBaseLayer.returnins2((String)session.getAttribute("course_id"));
    }
    
    public String getInstructor1Email() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        return DataBaseLayer.returninsemail1((String)session.getAttribute("course_id"));
    }
    
    public String getInstructor2Email() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        return DataBaseLayer.returninsemail2((String)session.getAttribute("course_id"));
    }
    
    public String getStartDate() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        String s3 = "";
        final Vector<String[]> courseDetails = DataBaseLayer.getCourseDetails(s2);
        System.out.println("--------Size Of Vector-------" + courseDetails.size());
        System.out.println("   THE VECTOR For Courses  ::" + courseDetails);
        for (int i = 0; i < courseDetails.size(); ++i) {
            s3 = courseDetails.elementAt(i)[2];
        }
        return s3;
    }
    
    public String getEndDate() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        String s3 = "";
        final Vector<String[]> courseDetails = DataBaseLayer.getCourseDetails(s2);
        System.out.println("--------Size Of Vector-------" + courseDetails.size());
        System.out.println("   THE VECTOR For Courses  ::" + courseDetails);
        for (int i = 0; i < courseDetails.size(); ++i) {
            s3 = courseDetails.elementAt(i)[3];
        }
        return s3;
    }
    
    public String getCreditPoints() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        String s3 = "";
        final Vector<String[]> courseDetails = DataBaseLayer.getCourseDetails(s2);
        System.out.println("--------Size Of Vector-------" + courseDetails.size());
        System.out.println("   THE VECTOR For Courses  ::" + courseDetails);
        for (int i = 0; i < courseDetails.size(); ++i) {
            s3 = courseDetails.elementAt(i)[4];
        }
        return s3;
    }
    
    public String getCost() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        String s3 = "";
        final Vector<String[]> courseDetails = DataBaseLayer.getCourseDetails(s2);
        System.out.println("--------Size Of Vector-------" + courseDetails.size());
        System.out.println("   THE VECTOR For Courses  ::" + courseDetails);
        for (int i = 0; i < courseDetails.size(); ++i) {
            s3 = courseDetails.elementAt(i)[5];
        }
        return s3;
    }
    
    public String getType() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        String s3 = "";
        final Vector<String[]> courseDetails = DataBaseLayer.getCourseDetails(s2);
        System.out.println("--------Size Of Vector-------" + courseDetails.size());
        System.out.println("   THE VECTOR For Courses  ::" + courseDetails);
        for (int i = 0; i < courseDetails.size(); ++i) {
            s3 = courseDetails.elementAt(i)[6];
        }
        return s3;
    }
    
    public String getTotalWeeks() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        String s3 = "";
        final Vector<String[]> courseDetails = DataBaseLayer.getCourseDetails(s2);
        System.out.println("--------Size Of Vector-------" + courseDetails.size());
        System.out.println("   THE VECTOR For Courses  ::" + courseDetails);
        for (int i = 0; i < courseDetails.size(); ++i) {
            s3 = courseDetails.elementAt(i)[7];
        }
        return s3;
    }
    
    public String getSession() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        String s3 = "";
        final Vector<String[]> courseDetails = DataBaseLayer.getCourseDetails(s2);
        System.out.println("--------Size Of Vector-------" + courseDetails.size());
        System.out.println("   THE VECTOR For Courses  ::" + courseDetails);
        for (int i = 0; i < courseDetails.size(); ++i) {
            s3 = courseDetails.elementAt(i)[8];
        }
        return s3;
    }
    
    public void insertHomeDetails() throws ServletException, IOException {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        final String string = gregorianCalendar.get(11) + ":" + gregorianCalendar.get(12) + ":" + gregorianCalendar.get(13);
        final String string2 = gregorianCalendar.get(1) + "-" + (gregorianCalendar.get(2) + 1) + "-" + gregorianCalendar.get(5);
        final String s = "Home";
        final HttpSession session = WebContextFactory.get().getSession();
        final String s2 = (String)session.getAttribute("student_id");
        final String s3 = (String)session.getAttribute("course_id");
        final String s4 = (String)session.getAttribute("topic_id");
        System.out.println("--------------Topic Id for Home from Session----------->" + s4);
        final String s5 = "Home";
        DataBaseLayer.insertBookmark(s2, s3, s5);
        if (s4.equals("Home")) {
            DataBaseLayer.InsertIntoCourseLearnerLoginInfo(s2, s3, s4, string2, session.getId(), string);
        }
        else {
            DataBaseLayer.updateCourseLoginInfo(s2, s4, session.getId(), string, string2, s3);
            DataBaseLayer.InsertIntoCourseLearnerLoginInfo(s2, s3, s5, string2, session.getId(), string);
        }
        session.removeAttribute("topic_id");
        session.setAttribute("topic_id", (Object)s);
    }
    
    public void insertSyllabusDetails() throws ServletException, IOException {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        final String string = gregorianCalendar.get(11) + ":" + gregorianCalendar.get(12) + ":" + gregorianCalendar.get(13);
        final String string2 = gregorianCalendar.get(1) + "-" + (gregorianCalendar.get(2) + 1) + "-" + gregorianCalendar.get(5);
        final String s = "Syllabus";
        final HttpSession session = WebContextFactory.get().getSession();
        final String s2 = (String)session.getAttribute("student_id");
        final String s3 = (String)session.getAttribute("course_id");
        final String s4 = (String)session.getAttribute("topic_id");
        System.out.println("--------------Topic Id for Syllabus from Session------------>" + s4);
        final String s5 = "Syllabus";
        DataBaseLayer.insertBookmark(s2, s3, s5);
        if (s4.equals("Syllabus")) {
            DataBaseLayer.InsertIntoCourseLearnerLoginInfo(s2, s3, s4, string2, session.getId(), string);
        }
        else {
            DataBaseLayer.updateCourseLoginInfo(s2, s4, session.getId(), string, string2, s3);
            DataBaseLayer.InsertIntoCourseLearnerLoginInfo(s2, s3, s5, string2, session.getId(), string);
        }
        session.removeAttribute("topic_id");
        session.setAttribute("topic_id", (Object)s);
    }
    
    public void insertScheduleDetails() throws ServletException, IOException {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        final String string = gregorianCalendar.get(11) + ":" + gregorianCalendar.get(12) + ":" + gregorianCalendar.get(13);
        final String string2 = gregorianCalendar.get(1) + "-" + (gregorianCalendar.get(2) + 1) + "-" + gregorianCalendar.get(5);
        final String s = "Schedule";
        final HttpSession session = WebContextFactory.get().getSession();
        final String s2 = (String)session.getAttribute("student_id");
        final String s3 = (String)session.getAttribute("course_id");
        final String s4 = (String)session.getAttribute("topic_id");
        System.out.println("--------------Topic Id for Schedule from Session------------>" + s4);
        final String s5 = "Schedule";
        DataBaseLayer.insertBookmark(s2, s3, s5);
        if (s4.equals("Schedule")) {
            DataBaseLayer.InsertIntoCourseLearnerLoginInfo(s2, s3, s4, string2, session.getId(), string);
        }
        else {
            DataBaseLayer.updateCourseLoginInfo(s2, s4, session.getId(), string, string2, s3);
            DataBaseLayer.InsertIntoCourseLearnerLoginInfo(s2, s3, s5, string2, session.getId(), string);
        }
        session.removeAttribute("topic_id");
        session.setAttribute("topic_id", (Object)s);
    }
    
    public void insertResourceDetails() throws ServletException, IOException {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        final String string = gregorianCalendar.get(11) + ":" + gregorianCalendar.get(12) + ":" + gregorianCalendar.get(13);
        final String string2 = gregorianCalendar.get(1) + "-" + (gregorianCalendar.get(2) + 1) + "-" + gregorianCalendar.get(5);
        final HttpSession session = WebContextFactory.get().getSession();
        final String s = (String)session.getAttribute("student_id");
        final String s2 = (String)session.getAttribute("course_id");
        final String s3 = (String)session.getAttribute("topic_id");
        System.out.println("--------------Topic Id for Resource from Session------------>" + s3);
        final String s4 = "Resource";
        final String s5 = "Resource";
        DataBaseLayer.insertBookmark(s, s2, s4);
        if (s3.equals("Resource")) {
            DataBaseLayer.InsertIntoCourseLearnerLoginInfo(s, s2, s3, string2, session.getId(), string);
        }
        else {
            DataBaseLayer.updateCourseLoginInfo(s, s3, session.getId(), string, string2, s2);
            DataBaseLayer.InsertIntoCourseLearnerLoginInfo(s, s2, s4, string2, session.getId(), string);
        }
        session.removeAttribute("topic_id");
        session.setAttribute("topic_id", (Object)s5);
    }
    
    public void insertAnnouncementDetails() throws ServletException, IOException {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        final String string = gregorianCalendar.get(11) + ":" + gregorianCalendar.get(12) + ":" + gregorianCalendar.get(13);
        final String string2 = gregorianCalendar.get(1) + "-" + (gregorianCalendar.get(2) + 1) + "-" + gregorianCalendar.get(5);
        final HttpSession session = WebContextFactory.get().getSession();
        final String s = (String)session.getAttribute("student_id");
        final String s2 = (String)session.getAttribute("course_id");
        final String s3 = (String)session.getAttribute("topic_id");
        System.out.println("--------------Topic Id for Announcements from Session------------>" + s3);
        final String s4 = "Announcements";
        final String s5 = "Announcements";
        DataBaseLayer.insertBookmark(s, s2, s4);
        if (s3.equals("Announcements")) {
            DataBaseLayer.InsertIntoCourseLearnerLoginInfo(s, s2, s3, string2, session.getId(), string);
        }
        else {
            DataBaseLayer.updateCourseLoginInfo(s, s3, session.getId(), string, string2, s2);
            DataBaseLayer.InsertIntoCourseLearnerLoginInfo(s, s2, s4, string2, session.getId(), string);
        }
        session.removeAttribute("topic_id");
        session.setAttribute("topic_id", (Object)s5);
    }
    
    public void insertAssignmentDetails() throws ServletException, IOException {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        final String string = gregorianCalendar.get(11) + ":" + gregorianCalendar.get(12) + ":" + gregorianCalendar.get(13);
        final String string2 = gregorianCalendar.get(1) + "-" + (gregorianCalendar.get(2) + 1) + "-" + gregorianCalendar.get(5);
        final HttpSession session = WebContextFactory.get().getSession();
        final String s = (String)session.getAttribute("student_id");
        final String s2 = (String)session.getAttribute("course_id");
        final String s3 = (String)session.getAttribute("topic_id");
        System.out.println("--------------Topic Id for Assignment from Session------------>" + s3);
        final String s4 = "Assignment";
        final String s5 = "Assignment";
        DataBaseLayer.insertBookmark(s, s2, s4);
        if (s3.equals("Assignment")) {
            DataBaseLayer.InsertIntoCourseLearnerLoginInfo(s, s2, s3, string2, session.getId(), string);
        }
        else {
            DataBaseLayer.updateCourseLoginInfo(s, s3, session.getId(), string, string2, s2);
            DataBaseLayer.InsertIntoCourseLearnerLoginInfo(s, s2, s4, string2, session.getId(), string);
        }
        session.removeAttribute("topic_id");
        session.setAttribute("topic_id", (Object)s5);
    }
    
    public void insertGradeBookDetails() throws ServletException, IOException {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        final String string = gregorianCalendar.get(11) + ":" + gregorianCalendar.get(12) + ":" + gregorianCalendar.get(13);
        final String string2 = gregorianCalendar.get(1) + "-" + (gregorianCalendar.get(2) + 1) + "-" + gregorianCalendar.get(5);
        final HttpSession session = WebContextFactory.get().getSession();
        final String s = (String)session.getAttribute("student_id");
        final String s2 = (String)session.getAttribute("course_id");
        final String s3 = (String)session.getAttribute("topic_id");
        System.out.println("--------------Topic Id for GradeBook from Session------------>" + s3);
        final String s4 = "GradeBook";
        final String s5 = "GradeBook";
        DataBaseLayer.insertBookmark(s, s2, s4);
        if (s3.equals("GradeBook")) {
            DataBaseLayer.InsertIntoCourseLearnerLoginInfo(s, s2, s3, string2, session.getId(), string);
        }
        else {
            DataBaseLayer.updateCourseLoginInfo(s, s3, session.getId(), string, string2, s2);
            DataBaseLayer.InsertIntoCourseLearnerLoginInfo(s, s2, s4, string2, session.getId(), string);
        }
        session.removeAttribute("topic_id");
        session.setAttribute("topic_id", (Object)s5);
    }
    
    public String returnTopicId() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("course_id");
        final String s2 = (String)session.getAttribute("topic_id");
        System.out.println("--------------Topic Id return ----------->" + s2);
        return s2;
    }
    
    public String getScheduleFileName() {
        final HttpSession session = WebContextFactory.get().getSession();
       // final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        final String[] scheduleFile = DataBaseLayer.getScheduleFile(s2);
        if (scheduleFile[1] == null) {
            scheduleFile[1] = "";
        }
        System.out.println("---------  FILE NAME ----------->  " + scheduleFile[1]);
        final String string = Host.getServerCoursePathEngine() + "schedule/" + s2 + "/" + scheduleFile[1];
        System.out.println("---------------  File Path  -------> " + string);
        return "<a href=\"" + string + "\" target=\"_blank\">" + scheduleFile[1] + "</a>";
    }
    
    public String CourseGradebookDetails() {
        String s = "";
        final HttpSession session = WebContextFactory.get().getSession();
        final String s2 = (String)session.getAttribute("user_id");
        final String s3 = (String)session.getAttribute("course_id");
        String s4 = "";
        String s5 = "";
        final Vector<String> vector = new Vector<String>();
        vector.addElement("Lowest");
        final Vector<String> vector2 = new Vector<String>();
        vector2.addElement("Highest");
        final Vector<String> vector3 = new Vector<String>();
        vector3.addElement("Average");
        final Vector<String> vector4 = new Vector<String>();
        vector4.addElement("Full Marks");
        double n = 0.0;
        double n2 = 0.0;
        final Vector<String> vector5 = new Vector<String>();
        vector5.addElement("Pass Marks");
        double n3 = 0.0;
        final String selectStuName = DataBaseLayer.selectStuName(s2);
        final Vector<String> vector6 = new Vector<String>();
        vector6.addElement(selectStuName);
        double n4 = 0.0;
        double n5 = 0.0;
        double n6 = 0.0;
        //final Vector vector7 = new Vector(10, 10);
        //final Vector vector8 = new Vector();
        DataBaseLayer.selectColumnCidAdmin(s3);
        final Vector<String[]> selectItemInformationCidAdmin = DataBaseLayer.selectItemInformationCidAdmin(s3);
        System.out.println("---------VECTOR ITEMS------> " + selectItemInformationCidAdmin);
        System.out.println("---------VECTOR ITEMS SIZE------> " + selectItemInformationCidAdmin.size());
        String priviledge = DataBaseLayer.getPriviledge(s3);
        System.out.println("---------GETPRIVILEDGE------> " + priviledge);
        if (priviledge == null || priviledge.equals("null")) {
            priviledge = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
        }
        if (selectItemInformationCidAdmin.size() != 0) {
            for (int i = 0; i < selectItemInformationCidAdmin.size(); ++i) {
                final String[] array = (String[])selectItemInformationCidAdmin.elementAt(i);
                System.out.println("----strGradebook----" + array);
                final String s6 = array[0];
                System.out.println("itemname----->" + s6);
                final int int1 = Integer.parseInt(array[1]);
                System.out.println("no_of_attempts--------->" + int1);
                final String s7 = array[2];
                System.out.println("item_id------------->" + s7);
                final String s8 = array[3];
                System.out.println("item_marks_scheme------------->" + s8);
                final String selectFullMarks = DataBaseLayer.selectFullMarks(s7);
                final double double1 = Double.parseDouble(selectFullMarks);
                n2 += Double.parseDouble(selectFullMarks);
                final String selectWt = DataBaseLayer.selectWt(s7);
                final double double2 = Double.parseDouble(selectWt);
                n += Double.parseDouble(selectWt);
                n3 += Double.parseDouble(DataBaseLayer.selectPassMarks(s7)) * double2 / double1;
                System.out.println("-------- GRADE --------------> " + DataBaseLayer.selectGradeInformation(s7));
                if (s8 != null && !s8.equals("0") && !s8.equals("null")) {
                    if (!s8.equals("")) {
                        if (s8.equals("Best Of")) {
                            for (int j = 0; j < int1; ++j) {
                                final int n7 = j + 1;
                                s4 = s4 + "<td class=\"tdStyle1\">" + s6 + "</td>";
                                s5 = s5 + "<td class=\"tdStyle_attempt\">" + n7 + "</td>";
                                final String string = "attempt" + n7;
                                String selectWt2 = DataBaseLayer.selectWt(s7);
                                if (selectWt2 == null || selectWt2.equals("null") || selectWt2.equals("")) {
                                    selectWt2 = "0.0";
                                }
                                final double double3 = Double.parseDouble(selectWt2);
                                String selectFullMarks2 = DataBaseLayer.selectFullMarks(s7);
                                if (selectFullMarks2 == null || selectFullMarks2.equals("null") || selectFullMarks2.equals("")) {
                                    selectFullMarks2 = "0.0";
                                }
                                final double double4 = Double.parseDouble(selectFullMarks2);
                                vector4.addElement(selectWt2);
                                String selectLowMarks = DataBaseLayer.selectLowMarks(s3, s7, string);
                                if (selectLowMarks == null || selectLowMarks.equals("null") || selectLowMarks.equals("")) {
                                    selectLowMarks = "0.0";
                                }
                                vector.addElement(new Double((Double.parseDouble(selectLowMarks) * double3 / double4)).toString());
                                String selectHighMarks = DataBaseLayer.selectHighMarks(s3, s7, string);
                                if (selectHighMarks == null || selectHighMarks.equals("null") || selectHighMarks.equals("")) {
                                    selectHighMarks = "0.0";
                                }
                                vector2.addElement(new Double((Double.parseDouble(selectHighMarks) * double3 / double4)).toString());
                                String selectAvgMarks = DataBaseLayer.selectAvgMarks(s3, s7, string);
                                if (selectAvgMarks == null || selectAvgMarks.equals("null") || selectAvgMarks.equals("")) {
                                    selectAvgMarks = "0.0";
                                }
                                vector3.addElement(new Double((Double.parseDouble(selectAvgMarks) * double3 / double4)).toString());
                                String selectPassMarks = DataBaseLayer.selectPassMarks(s7);
                                if (selectPassMarks == null || selectPassMarks.equals("null") || selectPassMarks.equals("")) {
                                    selectPassMarks = "0.0";
                                }
                                vector5.addElement(new Double((Double.parseDouble(selectPassMarks) * double3 / double4)).toString());
                                String selectMarks = DataBaseLayer.selectMarks(s2, s3, s7, string);
                                if (selectMarks == null || selectMarks.equals("null") || selectMarks.equals("")) {
                                    selectMarks = "0.0";
                                }
                                System.out.println("--------ORIGINAL MARKS--------> " + selectMarks);
                                final double n8 = Double.parseDouble(selectMarks) * double3 / double4;
                                String selectGrade = DataBaseLayer.selectGrade(n8 * 100.0 / double3, s7);
                                if (selectGrade == null || selectGrade.equals("null")) {
                                    selectGrade = "";
                                }
                                if (priviledge.charAt(3) == 'T') {
                                    vector6.addElement(n8 + "/" + selectGrade + "");
                                }
                                else {
                                    vector6.addElement(new Double(n8).toString());
                                }
                                System.out.println("-------------ITEM WISE MARKS---> " + n8);
                                if (n8 > n6) {
                                    n6 = n8;
                                    System.out.println("-----temp_marks------> " + n6);
                                }
                                n4 = n6;
                                System.out.println("-------------E1---------> " + n4);
                            }
                        }
                        else {
                            cms.log.debug("=====================no_of_attempts===============" + int1);
                            for (int k = 0; k < int1; ++k) {
                                final int n9 = k + 1;
                                s4 = s4 + "<td class=\"tdStyle1\">" + s6 + "</td>";
                                s5 = s5 + "<td class=\"tdStyle_attempt\" width=\"150\">" + n9 + "</td>";
                                final String string2 = "attempt" + n9;
                                String selectWt3 = DataBaseLayer.selectWt(s7);
                                if (selectWt3 == null || selectWt3.equals("null")) {
                                    selectWt3 = "0.0";
                                }
                                final double double5 = Double.parseDouble(selectWt3);
                                cms.log.debug("-------- WEIGHT --------> " + selectWt3);
                                String selectFullMarks3 = DataBaseLayer.selectFullMarks(s7);
                                if (selectFullMarks3 == null || selectFullMarks3.equals("null")) {
                                    selectFullMarks3 = "0.0";
                                }
                                final double double6 = Double.parseDouble(selectFullMarks3);
                                vector4.addElement(selectWt3);
                                String selectLowMarks2 = DataBaseLayer.selectLowMarks(s3, s7, string2);
                                if (selectLowMarks2 == null || selectLowMarks2.equals("null")) {
                                    selectLowMarks2 = "0.0";
                                }
                                vector.addElement(new Double(Double.parseDouble(selectLowMarks2) * double5 / double6).toString());
                                String selectHighMarks2 = DataBaseLayer.selectHighMarks(s3, s7, string2);
                                if (selectHighMarks2 == null || selectHighMarks2.equals("null")) {
                                    selectHighMarks2 = "0.0";
                                }
                                vector2.addElement(new Double(Double.parseDouble(selectHighMarks2) * double5 / double6).toString());
                                String selectAvgMarks2 = DataBaseLayer.selectAvgMarks(s3, s7, string2);
                                if (selectAvgMarks2 == null || selectAvgMarks2.equals("null")) {
                                    selectAvgMarks2 = "0.0";
                                }
                                vector3.addElement(new Double(Double.parseDouble(selectAvgMarks2) * double5 / double6).toString());
                                String selectPassMarks2 = DataBaseLayer.selectPassMarks(s7);
                                if (selectPassMarks2 == null || selectPassMarks2.equals("null")) {
                                    selectPassMarks2 = "0.0";
                                }
                                vector5.addElement(new Double(Double.parseDouble(selectPassMarks2) * double5 / double6).toString());
                                String selectMarks2 = DataBaseLayer.selectMarks(s2, s3, s7, string2);
                                if (selectMarks2 == null || selectMarks2.equals("null")) {
                                    selectMarks2 = "0.0";
                                }
                                final double n10 = Double.parseDouble(selectMarks2) * double5 / double6;
                                String selectGrade2 = DataBaseLayer.selectGrade(n10 * 100.0 / double5, s7);
                                if (selectGrade2 == null || selectGrade2.equals("null")) {
                                    selectGrade2 = "";
                                }
                                if (priviledge.charAt(3) == 'T') {
                                    vector6.addElement(n10 + "/" + selectGrade2 + "");
                                }
                                else {
                                    vector6.addElement((String)new Double(n10).toString());
                                }
                                n4 = (n4 + n10) / n9;
                                cms.log.debug("-------------E1 in Average Condition---------> " + n4);
                            }
                            cms.log.debug("==================after for===============");
                        }
                    }
                }
                cms.log.debug("==================after if===============");
                n5 += n4;
                n4 = 0.0;
                cms.log.debug("---------OVERALL MARKS -----> " + n5);
            }
        }
        final double n11 = DataBaseLayer.selectLowestOverall(s3) * n / n2;
        final double n12 = DataBaseLayer.selectHighestOverall(s3) * n / n2;
        final double n13 = DataBaseLayer.selectAvgOverall(s3) * n / n2;
        final String selectOverallGrade = DataBaseLayer.selectOverallGrade(s3, n5 * 100.0 / n);
        vector.addElement(new Double(n11).toString());
        vector2.addElement(new Double(n12).toString());
        vector3.addElement(new Double(n13).toString());
        vector4.addElement(new Double(n).toString());
        vector5.addElement(new Double(n3).toString());
        if (priviledge.charAt(3) == 'T') {
            vector6.addElement(n5 + "/" + selectOverallGrade + "");
        }
        else {
            vector6.addElement(new Double(n5).toString());
        }
        if (priviledge.charAt(6) == 'T') {
            String s9 = s + "<tr class=\"tdlowest\">";
            for (int l = 0; l < vector.size(); ++l) {
                if (vector.elementAt(l).equals("0.0")) {
                    s9 += "<td >--</td>";
                }
                else {
                    s9 = s9 + "<td >" + (Object)vector.elementAt(l) + "</td>";
                }
            }
            s = s9 + "</tr>";
        }
        if (priviledge.charAt(9) == 'T') {
            String s10 = s + "<tr class=\"tdhighest\">";
            for (int n14 = 0; n14 < vector2.size(); ++n14) {
                if (vector2.elementAt(n14).equals("0.0")) {
                    s10 += "<td >--</td>";
                }
                else {
                    s10 = s10 + "<td>" + (Object)vector2.elementAt(n14) + "</td>";
                }
            }
            s = s10 + "</tr>";
        }
        if (priviledge.charAt(12) == 'T') {
            String s11 = s + "<tr class=\"tdaverage\">";
            for (int n15 = 0; n15 < vector3.size(); ++n15) {
                if (vector3.elementAt(n15).equals("0.0")) {
                    s11 += "<td >--</td>";
                }
                else {
                    s11 = s11 + "<td>" + (Object)vector3.elementAt(n15) + "</td>";
                }
            }
            s = s11 + "</tr>";
        }
        if (priviledge.charAt(15) == 'T') {
            String s12 = s + "<tr class=\"tdfullmarks\">";
            for (int n16 = 0; n16 < vector4.size(); ++n16) {
                if (vector4.elementAt(n16).equals("0.0")) {
                    s12 += "<td >--</td>";
                }
                else {
                    s12 = s12 + "<td>" + (Object)vector4.elementAt(n16) + "</td>";
                }
            }
            s = s12 + "</tr>";
        }
        if (priviledge.charAt(18) == 'T') {
            String s13 = s + "<tr class=\"tdpassmarks\">";
            for (int n17 = 0; n17 < vector5.size(); ++n17) {
                if (vector5.elementAt(n17).equals("0.0")) {
                    s13 += "<td >--</td>";
                }
                else {
                    s13 = s13 + "<td>" + (Object)vector5.elementAt(n17) + "</td>";
                }
            }
            s = s13 + "</tr>";
        }
        if (priviledge.charAt(0) == 'T') {
            String s14 = s + "<tr class=\"tdmarks\">";
            for (int n18 = 0; n18 < vector6.size(); ++n18) {
                if (vector6.elementAt(n18).equals("0.0")) {
                    s14 += "<td >--</td>";
                }
                else {
                    s14 = s14 + "<td>" + (Object)vector6.elementAt(n18) + "</td>";
                }
            }
            s = s14 + "</tr>";
        }
        return "<table class=\"totTab2\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\"  width=\"450\" ><tr><td class=\"tdStyle1\">Items</td>" + s4 + "<td class=\"tdStyle\">Overall</td></tr><tr><td class=\"tdStyle1\">Attempt</td>" + s5 + "</tr>" + s + "</table>";
    }
    
    public void removeSessionDocId() throws ServletException, IOException {
        cms.log.debug("------------INSIDE removeSessionDocId --------- ");
        final HttpSession session = WebContextFactory.get().getSession();
        session.removeAttribute("assign_id");
        session.removeAttribute("doc_id");
        session.removeAttribute("doc_name");
    }
    
    public String setSessiondocid(final String s, final String s2) throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s3 = (String)session.getAttribute("user_id");
        session.setAttribute("assign_id", (Object)s);
        session.setAttribute("doc_id", (Object)s2);
        return "";
    }
    
    public void setSessionDocName(final String s, final String s2) throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        System.out.println("========================doc_name============" + s);
        System.out.println("========================document_id============" + s2);
        session.setAttribute("doc_name", (Object)s);
        session.setAttribute("document_id", (Object)s2);
    }
    
    public String returnAssgnId() throws ServletException, IOException {
        final String s = (String)WebContextFactory.get().getSession().getAttribute("assign_id");
        cms.log.debug("--------------Assignment Id return ----------->" + s);
        return s;
    }
    
    public String returnDocName() {
        final String s = (String)WebContextFactory.get().getSession().getAttribute("doc_name");
        cms.log.debug("--------------doc_name return ----------->" + s);
        return s;
    }
    
    public void deleteDocument() throws ServletException, IOException {
        final WebContext value = WebContextFactory.get();
        final HttpSession session = value.getSession();
        value.getHttpServletRequest();
        value.getHttpServletResponse();
        cms.log.debug("--------INSIDE DELETE DOCUMENT-----");
        final String s = (String)session.getAttribute("course_id");
        //final String s2 = (String)session.getAttribute("course_name");
        final String s3 = (String)session.getAttribute("user_id");
        final String s4 = (String)session.getAttribute("assign_id");
        cms.log.debug("--------INSIDE DELETE DOCUMENT ASS ID-----" + s4);
        final String s5 = (String)session.getAttribute("document_id");
        cms.log.debug("--------INSIDE DELETE DOCUMENT DOC ID-----" + s5);
        final String s6 = (String)session.getAttribute("doc_name");
        DataBaseLayer.deleteAssignmentDocument(s, s3, s4, s5, s6);
        final File file = new File(Host.getServerCoursePath() + File.separatorChar + "assignment" + File.separatorChar + "submitted" + File.separatorChar + s4 + File.separatorChar + s3 + File.separatorChar + s6);
        if (file != null) {
            file.delete();
        }
    }
    
    public String checkLockStatus() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        return DataBaseLayer.isAssignmentLocked((String)session.getAttribute("course_id"), (String)session.getAttribute("assign_id"), (String)session.getAttribute("user_id"));
    }
    
    public String ReturnDocInfo() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        final String s = (String)session.getAttribute("course_id");
        final String s2 = (String)session.getAttribute("assign_id");
        final String s3 = (String)session.getAttribute("user_id");
        String string = "";
        final Vector<String[]> returnDocInfo = DataBaseLayer.getReturnDocInfo(s, s2, s3);
        for (int i = 0; i < returnDocInfo.size(); ++i) {
            final String[] array = returnDocInfo.elementAt(i);
            final String s4 = array[0];
            cms.log.debug("returndoc----->" + s4);
            string = string + "<table  border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"tdstylefeed\" width=\"130\">" + array[1] + "</td>" + "<td class=\"tdstylefeed\" width=\"85\"><a href=\"" + (Host.getServerCoursePathEngine() + "assignment/feedback/" + s2 + "/" + s3 + "/" + s4) + "\">" + s4 + "</td>" + "</tr></table>";
        }
        cms.log.debug("-----------table----->" + string);
        return string;
    }
    
    public String FeedbackInfo() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        final String feedbackInfo = DataBaseLayer.getFeedbackInfo((String)session.getAttribute("course_id"), (String)session.getAttribute("user_id"), (String)session.getAttribute("assign_id"));
        cms.log.debug("-----------vstatus----->" + feedbackInfo);
        return feedbackInfo;
    }
    
    public String FeedbackDetails() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        final String s = (String)session.getAttribute("course_id");
        final String s2 = (String)session.getAttribute("assign_id");
        final String s3 = (String)session.getAttribute("user_id");
        String s4 = "";
        final Vector<String[]> feedbackDetails = DataBaseLayer.getFeedbackDetails(s, s3, s2);
        for (int i = 0; i < feedbackDetails.size(); ++i) {
            s4 = feedbackDetails.elementAt(i)[0];
        }
        cms.log.debug("feedback----->" + s4);
        return s4;
    }
    
    public String treeConstruct() {
        final String tree = this.createTree((String)WebContextFactory.get().getSession().getAttribute("course_id"));
        System.out.println("========outString===" + tree);
        return tree;
    }
    
    public String createTree(final String s) {
        final Vector<String[]> syllabus = DataBaseLayer.getSyllabus(s);
        String string = "\n<ul>";
        for (int i = 0; i < syllabus.size(); ++i) {
            final String[] array = syllabus.elementAt(i);
            System.out.println("=====s1[0]=======" + array[0]);
            System.out.println("=====s1[1]=======" + array[1]);
            final String string2 = Integer.toString(i + 1);
            System.out.println("===========NO==========" + string2);
            final String treeprint = treeprint(s, array, string2);
            System.out.println("=======str2=========" + treeprint);
            string += treeprint;
            System.out.println("=======str1=========" + string);
        }
        return string + "\n</ul>";
    }
    
    private static String treeprint(final String s, final String[] array, final String s2) {
        final Vector<String[]> syllabus = DataBaseLayer.getSyllabus(s, array[0]);
        String s4;
        if (syllabus.size() > 0) {
            String s3 = "\n<li data=\"key: '" + array[0] + "',tooltip: '" + array[1] + "', description: '" + array[2] + "'\">" + array[1] + "\n<ul>";
            for (int i = 0; i < syllabus.size(); ++i) {
                s3 += treeprint(s, syllabus.elementAt(i), s2, Integer.toString(i + 1));
            }
            s4 = s3 + "\n</ul>";
        }
        else {
            s4 = "\n<li data=\"key: '" + array[0] + "',tooltip: '" + array[1] + "', description: '" + array[2] + "'\">" + array[1];
        }
        return s4;
    }
    
    private static String treeprint(final String s, final String[] array, final String s2, final String s3) {
        final String string = s2 + "." + s3;
        final Vector<String[]> syllabus = DataBaseLayer.getSyllabus(s, array[0]);
        String s5;
        if (syllabus.size() > 0) {
            String s4 = "\n<li data=\"key: '" + array[0] + "',tooltip: '" + array[1] + "', description: '" + array[2] + "'\">" + array[1] + "\n<ul>";
            for (int i = 0; i < syllabus.size(); ++i) {
                s4 += treeprint(s, syllabus.elementAt(i), string, Integer.toString(i + 1));
            }
            s5 = s4 + "\n</ul>";
        }
        else {
            s5 = "\n<li data=\"key: '" + array[0] + "',tooltip: '" + array[1] + "', description: '" + array[2] + "'\">" + array[1];
        }
        return s5;
    }
    
    public String getSyllabusFileName() {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        final String[] syllabusFile = DataBaseLayer.getSyllabusFile(s2);
        if (syllabusFile[0] == null) {
            syllabusFile[0] = "";
        }
        System.out.println("---------  FILE NAME ----------->  " + syllabusFile[0]);
        final String string = Host.getServerCoursePathEngine() + "SyllabusAttachment/" + s2 + "/" + syllabusFile[0];
        System.out.println("---------------  File Path  -------> " + string);
        return "<a href=\"" + string + "\" target=\"_blank\">" + syllabusFile[0] + "</a>";
    }
    
    public void deleteAllScheduleDetails(final String s) {
        cms.log.debug("Inside CMS.java");
        DataBaseLayer.deleteAllSchedule(s);
    }
    
    public String UploadFile(final String s, final FileTransfer fileTransfer) {
        String s2 = "";
        WebContextFactory.get().getSession();
        final String serverCoursePath = Host.getServerCoursePath();
        cms.log.debug("===============strLocation=========" + serverCoursePath);
        String string = serverCoursePath;
        final String[] array = { "schedule", s, null };
        cms.log.debug("dir name is:" + string);
        for (int i = 0; i < 2; ++i) {
            if (string == null) {
                s2 = "Please supply uploadDir parameter";
            }
            string = string + File.separator + array[i];
            final File file = new File(string);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        //final FileUploaderPojo fileUploaderPojo = new FileUploaderPojo(fileTransfer);
        cms.log.debug("==============MimeType==========" + FileUploaderPojo.getMimeType());
        final InputStream inputStream = FileUploaderPojo.getInputStream();
        cms.log.debug("==============is=============" + inputStream);
        final String filename = FileUploaderPojo.getFilename();
        cms.log.debug("==============filename=============" + filename);
        final String string2 = string + File.separator + filename;
        cms.log.debug("==============dirName==========" + string2);
        try {
            cms.log.debug("dirName===" + string2);
            final FileOutputStream fileOutputStream = new FileOutputStream(string2);
            final byte[] array2 = new byte[1024];
            int read;
            while (inputStream != null && (read = inputStream.read(array2)) != -1) {
                fileOutputStream.write(array2, 0, read);
            }
            new PrintStream(fileOutputStream).close();
        }
        catch (IOException ex2) {}
        catch (Exception ex) {
            System.err.println("=========error=======" + ex.toString());
        }
        DataBaseLayer.updateCourseShedule(filename, s);
        return s2;
    }
    
    public FileTransfer downloadFile(final String s) throws Exception {
        final String courseScheduleFile = DataBaseLayer.getCourseScheduleFile(s);
        cms.log.debug("==================filename=== in Download=========" + courseScheduleFile);
        final String serverCoursePath = Host.getServerCoursePath();
        cms.log.debug("===============strLocation=========" + serverCoursePath);
        final String s2 = serverCoursePath;
        System.out.println("course_id----------------> " + s);
        cms.log.debug("dir name is 1:" + s2);
        final String string = s2 + "schedule" + File.separator + s + File.separator + courseScheduleFile;
        cms.log.debug("dir name is 3:" + string);
        final FileInputStream fileInputStream = new FileInputStream(string);
        cms.log.debug("=============>>>> Schedule File: " + fileInputStream);
        fileInputStream.close();
        //final FileDownloaderPojo fileDownloaderPojo = new FileDownloaderPojo((InputStream)fileInputStream, "", courseScheduleFile);
        cms.log.debug("=============>>>> Returning Schedule File: " + FileDownloaderPojo.returnFileFormat());
        return FileDownloaderPojo.returnFileFormat();
    }
    
    public String returnScheduleFilename(final String s) throws Exception {
        final String courseScheduleFile = DataBaseLayer.getCourseScheduleFile(s);
        cms.log.debug("==================filename=== in returnScheduleFilename=========" + courseScheduleFile);
        return courseScheduleFile;
    }
    
    public String setSessioncourseId(final String s) throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s2 = (String)session.getAttribute("user_id");
        session.setAttribute("course_id", (Object)s);
        return "";
    }
    
    public String setSessionTopId(final String s) throws ServletException, IOException {
        final WebContext value = WebContextFactory.get();
        cms.log.debug("================wctx1============" + value);
        value.getSession().setAttribute("top_id", (Object)s);
        return "";
    }
    
    public void addCourse(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) throws IOException, ServletException {
        final String s7 = (String)WebContextFactory.get().getSession().getAttribute("course_id");
        if (s4 != null && !s4.equals("")) {
            DataBaseLayer.insertSubTopicDetails(s7, s, s4, s5, s6);
        }
        else {
            DataBaseLayer.insertTopicDetails(s7, s, s2, s3);
        }
        cms.log.debug("Course_id=" + s7 + "  strTopic =" + s + " strSubTopic =" + s4);
    }
    
    public void modifyCourse(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) throws IOException, ServletException {
        DataBaseLayer.updateTopicDetails((String)WebContextFactory.get().getSession().getAttribute("course_id"), s, s2, s3);
    }
    
    public void deleteCourse(final String s) throws IOException, ServletException {
        DataBaseLayer.deleteTopicDetails((String)WebContextFactory.get().getSession().getAttribute("course_id"), s);
    }
    
    public String showNoticeInfo(final String s, final String s2) throws IOException, ServletException {
        WebContextFactory.get();
        final String noticeBody = DataBaseLayer.getNoticeBody(s, s2);
        cms.log.debug("-------- notice_heading -----------> " + s);
        cms.log.debug("-------- course_id -----------> " + s2);
        cms.log.debug("-------- notice_body -----------> " + noticeBody);
        if (noticeBody.equals("") || noticeBody.equals(null)) {
            return "No Content Body Is Found For This Announcement.";
        }
        return noticeBody;
    }
    
    public String AttachFile(final String s, final String s2, final FileTransfer fileTransfer) {
        String s3 = "";
        WebContextFactory.get().getSession();
        final String serverCoursePath = Host.getServerCoursePath();
        cms.log.debug("===============strLocation=========" + serverCoursePath);
        String string = serverCoursePath;
        final String[] array = { "Announcements", s2, null };
        cms.log.debug("dir name is:" + string);
        for (int i = 0; i < 2; ++i) {
            if (string == null) {
                s3 = "Please supply uploadDir parameter";
            }
            string = string + File.separator + array[i];
            final File file = new File(string);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        //final FileUploaderPojo fileUploaderPojo = new FileUploaderPojo(fileTransfer);
        cms.log.debug("==============MimeType==========" + FileUploaderPojo.getMimeType());
        final InputStream inputStream = FileUploaderPojo.getInputStream();
        cms.log.debug("==============is=============" + inputStream);
        final String filename = FileUploaderPojo.getFilename();
        cms.log.debug("==============filename=============" + filename);
        final String string2 = string + File.separator + filename;
        final String s4 = "";
        cms.log.debug("==============dirName==========" + string2);
        try {
            cms.log.debug("dirName===" + string2);
            final FileOutputStream fileOutputStream = new FileOutputStream(string2);
            final byte[] array2 = new byte[1024];
            try {
                int read;
                while (inputStream != null && (read = inputStream.read(array2)) != -1) {
                    cms.log.debug("=======test1======");
                    fileOutputStream.write(array2, 0, read);
                }
            }
            catch (IOException ex2) {}
            cms.log.debug("=======test2======");
            final PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(s4);
            printStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        DataBaseLayer.updateCourseAnnouncement(s, filename, s2);
        return s3;
    }
    
    public String deleteAllCourse() {
        cms.log.debug("===========INSIDE deleteAllCourse===========");
        final boolean deleteAllDefinitionDetails = DataBaseLayer.deleteAllDefinitionDetails();
        cms.log.debug("+++++++FLAG++++++" + deleteAllDefinitionDetails);
        String s;
        if (!deleteAllDefinitionDetails) {
            s = "Some Users or Groups Are Registered With This Course. Unregister Them and Then Delete.";
        }
        else {
            s = "Successfully Deleted";
        }
        return s;
    }
    
    public String UploadExcelSyllabus(final String s, final FileTransfer fileTransfer) {
        String s2 = "";
        //final String s3 = (String)WebContextFactory.get().getSession().getAttribute("user_id");
        //final String s4 = (s3 == null) ? "" : s3;
        final Vector<String> vector = new Vector<String>();
        final String adminPath = Host.getAdminPath();
        if (adminPath == null) {
            s2 = "Please supply uploadDir parameter";
        }
        final File file = new File(adminPath);
        if (!file.exists()) {
            file.mkdir();
        }
        //final FileUploaderPojo fileUploaderPojo = new FileUploaderPojo(fileTransfer);
        cms.log.debug("MimeType======:" + FileUploaderPojo.getMimeType());
        final InputStream inputStream = FileUploaderPojo.getInputStream();
        final String string = adminPath + File.separator + FileUploaderPojo.getFilename();
        try {
            cms.log.debug("dirName===" + string);
            final FileOutputStream fileOutputStream = new FileOutputStream(string);
            final byte[] array = new byte[1024];
            int read;
            while (inputStream != null && (read = inputStream.read(array)) != -1) {
                fileOutputStream.write(array, 0, read);
            }
            new PrintStream(fileOutputStream).close();
            final Workbook workbook = Workbook.getWorkbook(new File(string));
            for (int i = 0; i < 1; ++i) {
                final Sheet sheet = workbook.getSheet(i);
                sheet.getColumns();
                for (int rows = sheet.getRows(), j = 1; j < rows; ++j) {
                    final Cell cell = sheet.getCell(0, j);
                    final Cell cell2 = sheet.getCell(1, j);
                    final Cell cell3 = sheet.getCell(2, j);
                    final Cell cell4 = sheet.getCell(3, j);
                    final String contents = cell.getContents();
                    final String contents2 = cell2.getContents();
                    final String contents3 = cell3.getContents();
                    final String contents4 = cell4.getContents();
                    cms.log.debug("topicId============================>> " + contents);
                    cms.log.debug("parentId============================>> " + contents2);
                    cms.log.debug("topicName============================>> " + contents3);
                    cms.log.debug("desc============================>> " + contents4);
                    vector.addElement(contents);
                    vector.addElement(contents2);
                    vector.addElement(contents3);
                    vector.addElement(contents4);
                }
                workbook.close();
            }
        }
        catch (Exception ex) {
            System.err.println("=========error=======" + ex.toString());
        }
        cms.log.debug("................................SIZE VECTOR/////////////" + vector);
        for (int k = 0; k < vector.size(); k += 4) {
            final String s5 = vector.elementAt(k);
            final String s6 = vector.elementAt(k + 1);
            final String s7 = vector.elementAt(k + 2);
            final String s8 = vector.elementAt(k + 3);
            cms.log.debug("Topic Id----------->" + s5);
            cms.log.debug("Parent Id----------->" + s6);
            cms.log.debug("Topic Name----------->" + s7);
            final String courseExist = DataBaseLayer.isCourseExist(s);
            cms.log.debug("======checkcourse========" + courseExist);
            final String topicExist = DataBaseLayer.isTopicExist(s, s5);
            if (!courseExist.equals("") || courseExist != null) {
                if (topicExist.equals("") || topicExist == null || topicExist.equals("null")) {
                    DataBaseLayer.insertTopicDetailsForExcel(s, s5, s6, s7, s8);
                }
                else {
                    DataBaseLayer.updateTopicDetails(s, s5, s7, s8);
                }
            }
        }
        return s2;
    }
    
    public String AttachFileSyllabus(final String s, final FileTransfer fileTransfer) {
        String s2 = "";
        WebContextFactory.get().getSession();
        final String serverCoursePath = Host.getServerCoursePath();
        cms.log.debug("===============strLocation=========" + serverCoursePath);
        String string = serverCoursePath;
        final String[] array = { "SyllabusAttachment", s, null };
        cms.log.debug("dir name is:" + string);
        for (int i = 0; i < 2; ++i) {
            if (string == null) {}
            string = string + File.separator + array[i];
            final File file = new File(string);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        //final FileUploaderPojo fileUploaderPojo = new FileUploaderPojo(fileTransfer);
        cms.log.debug("==============MimeType==========" + FileUploaderPojo.getMimeType());
        final InputStream inputStream = FileUploaderPojo.getInputStream();
        cms.log.debug("==============is=============" + inputStream);
        final String filename = FileUploaderPojo.getFilename();
        cms.log.debug("==============filename=============" + filename);
        final String string2 = string + File.separator + filename;
        final String s3 = "";
        cms.log.debug("==============dirName==========" + string2);
        try {
            cms.log.debug("dirName===" + string2);
            final FileOutputStream fileOutputStream = new FileOutputStream(string2);
            final byte[] array2 = new byte[1024];
            try {
                int read;
                while (inputStream != null && (read = inputStream.read(array2)) != -1) {
                    cms.log.debug("=======test1======");
                    fileOutputStream.write(array2, 0, read);
                }
            }
            catch (IOException ex2) {}
            cms.log.debug("=======test2======");
            final PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(s3);
            printStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (DataBaseLayer.updateCourseSyllabus(filename, s)) {
            s2 = "Successfully Added";
        }
        return s2;
    }
    
    public String getMatType(final int n) {
        cms.log.debug("===========INSIDE getMatType===========");
        final String matTypeFromDB = DataBaseLayer.getMatTypeFromDB(n);
        cms.log.debug("+++++++mType++++++" + matTypeFromDB);
        return matTypeFromDB;
    }
    
    public String insertURL(final String s, final int n) {
        cms.log.debug("INSIDE insertURL------------------->");
        String s2 = "";
        if (DataBaseLayer.updateMatNametoDB(s, n)) {
            s2 = "Successfully Added";
        }
        return s2;
    }
    
    public String AttachFileSyllabusEdit(final int n, final FileTransfer fileTransfer) {
        String s = "";
        final HttpSession session = WebContextFactory.get().getSession();
        final String s2 = (String)session.getAttribute("course_id");
        final String s3 = (String)session.getAttribute("top_id");
        cms.log.debug("===============topic_id=============" + s3);
        final String serverCoursePath = Host.getServerCoursePath();
        cms.log.debug("===============strLocation=========" + serverCoursePath);
        String string = serverCoursePath;
        final String[] array = { "syllabus", s2, s3 };
        cms.log.debug("dir name is:" + string);
        for (int i = 0; i < 3; ++i) {
            if (string == null) {}
            string = string + File.separator + array[i];
            final File file = new File(string);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        //final FileUploaderPojo fileUploaderPojo = new FileUploaderPojo(fileTransfer);
        cms.log.debug("==============MimeType==========" + FileUploaderPojo.getMimeType());
        final InputStream inputStream = FileUploaderPojo.getInputStream();
        cms.log.debug("==============is=============" + inputStream);
        final String filename = FileUploaderPojo.getFilename();
        cms.log.debug("==============filename=============" + filename);
        final String string2 = string + File.separator + filename;
        final String s4 = "";
        cms.log.debug("==============dirName==========" + string2);
        try {
            cms.log.debug("dirName===" + string2);
            final FileOutputStream fileOutputStream = new FileOutputStream(string2);
            final byte[] array2 = new byte[1024];
            try {
                int read;
                while (inputStream != null && (read = inputStream.read(array2)) != -1) {
                    cms.log.debug("=======test1======");
                    fileOutputStream.write(array2, 0, read);
                }
            }
            catch (IOException ex2) {}
            cms.log.debug("=======test2======");
            final PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(s4);
            printStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (DataBaseLayer.updateMatNametoDB(filename, n)) {
            s = "Successfully Added";
        }
        return s;
    }
    
    public String insertLO(final String s, final int n) {
        cms.log.debug("INSIDE insertURL------------------->");
        String s2 = "";
        if (DataBaseLayer.updateMatNametoDB(s, n)) {
            s2 = "Successfully Added";
        }
        return s2;
    }
    
    public void InsertUserSCOInfo(final String s) {
        final String s2 = (String)WebContextFactory.get().getSession().getAttribute("course_id");
        cms.log.debug("INSIDE InsertUserSCOInfo------------------->");
        DataBaseLayer.insertinUserSCOinfo(s, s2);
    }
    
    public String AssignmentDocumentUpload(final String s, final String s2, final FileTransfer fileTransfer) {
        final String s3 = "";
        //final String s4 = (String)WebContextFactory.get().getSession().getAttribute("user_id");
        //final String s5 = (s4 == null) ? "" : s4;
        final String serverCoursePath = Host.getServerCoursePath();
        final String[] array = { "assignment", s2 };
        String string = serverCoursePath;
        for (int i = 0; i < 2; ++i) {
            string = string + File.separator + array[i];
            final File file = new File(string);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        //final FileUploaderPojo fileUploaderPojo = new FileUploaderPojo(fileTransfer);
        cms.log.debug("MimeType======:" + FileUploaderPojo.getMimeType());
        final InputStream inputStream = FileUploaderPojo.getInputStream();
        final String filename = FileUploaderPojo.getFilename();
        final String string2 = string + File.separator + filename;
        try {
            cms.log.debug("dirName===" + string2);
            final FileOutputStream fileOutputStream = new FileOutputStream(string2);
            final byte[] array2 = new byte[1024];
            int read;
            while (inputStream != null && (read = inputStream.read(array2)) != -1) {
                fileOutputStream.write(array2, 0, read);
            }
            new PrintStream(fileOutputStream).close();
        }
        catch (IOException ex2) {}
        catch (Exception ex) {
            System.err.println("=========error=======" + ex.toString());
        }
        DataBaseLayer.updateAssignmentDocument(s, s2, Long.valueOf(new File(string2).length()).toString(), filename);
        return s3;
    }
    
    public String AssignmentDocumentUploadFromPortal(final FileTransfer fileTransfer) {
        final String s = "";
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s2 = (String)session.getAttribute("user_id");
        //final String s3 = (s2 == null) ? "" : s2;
        int nextInt = new Random().nextInt();
        if (nextInt < 1) {
            nextInt *= -1;
            cms.log.debug("random no is k=" + nextInt);
        }
        final String string = Integer.toString(nextInt);
        final String s4 = (String)session.getAttribute("user_id");
        cms.log.debug("----------STUDENT ID -----------> " + s4);
        final String s5 = (String)session.getAttribute("course_id");
        cms.log.debug("----------COURSE ID -----------> " + s5);
        final String s6 = (String)session.getAttribute("assign_id");
        cms.log.debug("----------ASSIGNMENT ID -----------> " + s6);
        cms.log.debug("----------DOCUMENT ID -----------> " + (String)session.getAttribute("doc_id"));
        cms.log.debug("----------COURSE NAME -----------> " + DataBaseLayer.returncoursename(s5));
        final String serverCoursePath = Host.getServerCoursePath();
        final String[] array = { "assignment", "submitted", s6, s4 };
        String string2 = serverCoursePath;
        for (int i = 0; i < 4; ++i) {
            string2 = string2 + File.separator + array[i];
            final File file = new File(string2);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        //final FileUploaderPojo fileUploaderPojo = new FileUploaderPojo(fileTransfer);
        cms.log.debug("MimeType======:" + FileUploaderPojo.getMimeType());
        final InputStream inputStream = FileUploaderPojo.getInputStream();
        final String filename = FileUploaderPojo.getFilename();
        final String string3 = string2 + File.separator + filename;
        try {
            cms.log.debug("dirName===" + string3);
            final FileOutputStream fileOutputStream = new FileOutputStream(string3);
            final byte[] array2 = new byte[1024];
            int read;
            while (inputStream != null && (read = inputStream.read(array2)) != -1) {
                fileOutputStream.write(array2, 0, read);
            }
            new PrintStream(fileOutputStream).close();
        }
        catch (IOException ex2) {}
        catch (Exception ex) {
            System.err.println("=========error=======" + ex.toString());
        }
        DataBaseLayer.submitAssignmentDocument(s5, s4, s6, Long.valueOf(new File(string3).length()).toString(), filename);
        DataBaseLayer.updatecourse_confirmation(s4, s5, s6, string);
        DataBaseLayer.insertStatus(s4, s5, s6);
        session.removeAttribute("assign_id");
        session.removeAttribute("doc_id");
        session.removeAttribute("doc_name");
        return s;
    }
    
    public FileTransfer downloadAssgnFromPortal(final String s) throws Exception {
        final HttpSession session = WebContextFactory.get().getSession();
        final String s2 = (String)session.getAttribute("assign_id");
        //final String s3 = (String)session.getAttribute("document_id");
        final String s4 = (String)session.getAttribute("user_id");
        cms.log.debug("==================doc_name=== in Download=========" + s);
        final String serverCoursePath = Host.getServerCoursePath();
        cms.log.debug("===============strLocation=========" + serverCoursePath);
        String string = serverCoursePath;
        final String[] array = { "assignment", "submitted", s2, s4 };
        cms.log.debug("dir name is:" + string);
        for (int i = 0; i < 4; ++i) {
            string = string + File.separator + array[i];
        }
        final String string2 = string + File.separator + s;
        cms.log.debug("===============dirName=========" + string2);
        //final FileDownloaderPojo fileDownloaderPojo = new FileDownloaderPojo((InputStream)new FileInputStream(string2), "", s);
        return FileDownloaderPojo.returnFileFormat();
    }
    
    public String UploadDocument(final String s, final String s2, final String s3, final String s4, final FileTransfer fileTransfer) {
        String s5 = "";
        WebContextFactory.get().getSession();
        final String serverCoursePath = Host.getServerCoursePath();
        cms.log.debug("===============strLocation=========" + serverCoursePath);
        String string = serverCoursePath;
        final String[] array = { "assignment", "feedback", s2, s3 };
        cms.log.debug("dir name is:" + string);
        for (int i = 0; i < 4; ++i) {
            if (string == null) {
                s5 = "Please supply uploadDir parameter";
            }
            string = string + File.separator + array[i];
            final File file = new File(string);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        //final FileUploaderPojo fileUploaderPojo = new FileUploaderPojo(fileTransfer);
        cms.log.debug("==============MimeType==========" + FileUploaderPojo.getMimeType());
        final InputStream inputStream = FileUploaderPojo.getInputStream();
        cms.log.debug("==============is=============" + inputStream);
        final String filename = FileUploaderPojo.getFilename();
        cms.log.debug("==============filename=============" + filename);
        final String string2 = string + File.separator + filename;
        final String s6 = "";
        cms.log.debug("==============dirName==========" + string2);
        try {
            cms.log.debug("dirName===" + string2);
            final FileOutputStream fileOutputStream = new FileOutputStream(string2);
            final byte[] array2 = new byte[1024];
            try {
                int read;
                while (inputStream != null && (read = inputStream.read(array2)) != -1) {
                    cms.log.debug("=======test1======");
                    fileOutputStream.write(array2, 0, read);
                }
            }
            catch (IOException ex2) {}
            cms.log.debug("=======test2======");
            final PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(s6);
            printStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        DataBaseLayer.updateReturnDoc(s, s2, s3, filename);
        return s5;
    }
    
    public FileTransfer DownloadDocument(final String s, final String s2, final String s3, final String s4) throws Exception {
        final String docFileName = DataBaseLayer.getDocFileName(s2, s4);
        cms.log.debug("==================filename=== in Download=========" + docFileName);
        final String serverCoursePath = Host.getServerCoursePath();
        cms.log.debug("===============strLocation=========" + serverCoursePath);
        String string = serverCoursePath;
        final String[] array = { "assignment", "submitted", s2, s3 };
        cms.log.debug("dir name is:" + string);
        for (int i = 0; i < 4; ++i) {
            string = string + File.separator + array[i];
        }
        //final FileDownloaderPojo fileDownloaderPojo = new FileDownloaderPojo((InputStream)new FileInputStream(string + File.separator + docFileName), "", docFileName);
        return FileDownloaderPojo.returnFileFormat();
    }
    
    public static void LockSubmittedAssignment(final String s, final String s2, final String s3) {
        DataBaseLayer.LockSubmittedAssignment(s, s2, s3);
    }
    
    public static void setAssignmentGraded(final String s, final String s2, final String s3) {
        DataBaseLayer.setAssignmentGraded(s, s2, s3);
    }
    
    public static String getFeedback(final String s, final String s2, final String s3) {
        String feedback = DataBaseLayer.getFeedback(s, s2, s3);
        cms.log.debug("===========feedback====" + feedback);
        if (feedback == null) {
            feedback = "";
        }
        return feedback;
    }
    
    public static void updateAssignmentFeedback(final String s, final String s2, final String s3, final String s4) {
        DataBaseLayer.updateAssignmentFeedback(s, s2, s3, s4);
    }
    
    public String getMaterialType(final int n, final int n2) {
        cms.log.debug("===========INSIDE getMatType===========");
        final String materialTypeFromDB = DataBaseLayer.getMaterialTypeFromDB(n, n2);
        cms.log.debug("+++++++mType++++++" + materialTypeFromDB);
        return materialTypeFromDB;
    }
    
    public String insertURLforResource(final String s, final int n, final int n2) {
        cms.log.debug("INSIDE insertURLforResource------------------->");
        String s2 = "";
        if (DataBaseLayer.updateMaterialNametoDB(s, n, n2, "")) {
            s2 = "Successfully Added";
        }
        return s2;
    }
    
    public String AttachFileforResource(final int n, final FileTransfer fileTransfer, final int n2) {
        String s = "";
        cms.log.debug("===============topic_id=============" + (String)WebContextFactory.get().getSession().getAttribute("top_id"));
        final String serverCoursePath = Host.getServerCoursePath();
        cms.log.debug("===============strLocation=========" + serverCoursePath);
        String string = serverCoursePath;
        final String[] array = { "resource", Integer.toString(n2), Integer.toString(n) };
        cms.log.debug("dir name is:" + string);
        for (int i = 0; i < 3; ++i) {
            if (string == null) {}
            string = string + File.separator + array[i];
            final File file = new File(string);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        //final FileUploaderPojo fileUploaderPojo = new FileUploaderPojo(fileTransfer);
        cms.log.debug("==============MimeType==========" + FileUploaderPojo.getMimeType());
        final InputStream inputStream = FileUploaderPojo.getInputStream();
        cms.log.debug("==============is=============" + inputStream);
        final String filename = FileUploaderPojo.getFilename();
        cms.log.debug("==============filename=============" + filename);
        final String string2 = string + File.separator + filename;
        final String s2 = "";
        cms.log.debug("==============dirName==========" + string2);
        try {
            cms.log.debug("dirName===" + string2);
            final FileOutputStream fileOutputStream = new FileOutputStream(string2);
            final byte[] array2 = new byte[1024];
            try {
                int read;
                while (inputStream != null && (read = inputStream.read(array2)) != -1) {
                    cms.log.debug("=======test1======");
                    fileOutputStream.write(array2, 0, read);
                }
            }
            catch (IOException ex2) {}
            cms.log.debug("=======test2======");
            final PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(s2);
            printStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (DataBaseLayer.updateMaterialNametoDB(filename, n, n2, Long.valueOf(new File(string2).length()).toString())) {
            s = "Successfully Added";
        }
        return s;
    }
    
    public String insertLOforResource(final String s, final int n, final int n2) {
        cms.log.debug("INSIDE insertLOforResource------------------->");
        String s2 = "";
        if (DataBaseLayer.updateMaterialNametoDB(s, n, n2, "")) {
            s2 = "Successfully Added";
        }
        return s2;
    }
    
    public String insertForumforResource(final String s, final int n, final int n2) {
        cms.log.debug("INSIDE insertLOforResource------------------->");
        String s2 = "";
        if (DataBaseLayer.updateMaterialNametoDB(s, n, n2, "")) {
            s2 = "Successfully Added";
        }
        return s2;
    }
    
    public String insertSCEforResource(final String s, final int n, final int n2) {
        cms.log.debug("INSIDE insertSCEforResource------------------->");
        String s2 = "";
        if (DataBaseLayer.updateMaterialNametoDB(s, n, n2, "")) {
            s2 = "Successfully Added";
        }
        return s2;
    }
    
    public static void CourseImportFile(final FileTransfer fileTransfer, String s, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9) {
        final String s10 = (String)WebContextFactory.get().getSession().getAttribute("user_id");
        cms.log.debug("=============instructor=========" + s2);
        if (s == null) {
            s = "false";
        }
        if (s2 == null) {
            s2 = "false";
        }
        if (s3 == null) {
            s3 = "false";
        }
        if (s8 == null) {
            s8 = "false";
        }
        if (s4 == null) {
            s4 = "false";
        }
        if (s5 == null) {
            s5 = "false";
        }
        if (s6 == null) {
            s6 = "false";
        }
        if (s7 == null) {
            s7 = "false";
        }
        if (s9 == null) {
            s9 = "false";
        }
        final String adminPath = Host.getAdminPath();
        final cms cms = new cms();
        cms.UnZip(adminPath + File.separatorChar + cms.UploadCourseXmlZip(fileTransfer));
        final String string = adminPath + File.separatorChar + "Course.xml";
        learnitycms.cms.log.debug("==========course_xml_main=========" + string);
        final DOMParser domParser = new DOMParser();
        try {
            learnitycms.cms.log.debug("===================parse Start============");
            domParser.parse(string);
            final Element documentElement = domParser.getDocument().getDocumentElement();
            final NodeList elementsByTagName = documentElement.getElementsByTagName("COURSE-INFO");
            if (elementsByTagName.getLength() != 0) {
                final NodeList childNodes = elementsByTagName.item(0).getChildNodes();
                final String[] array = new String[9];
                int n = 0;
                for (int i = 0; i < childNodes.getLength(); ++i) {
                    final Node item = childNodes.item(i);
                    if (item.getNodeName() != "#text") {
                        String nodeValue = "";
                        if (item.getFirstChild() != null) {
                            nodeValue = item.getFirstChild().getNodeValue();
                        }
                        array[n] = nodeValue;
                        ++n;
                    }
                }
                DataBaseLayer.ImportCourseDef(array, s10);
            }
            final String returnmaxcourse = DataBaseLayer.returnmaxcourse();
            if (s5.equals("true")) {
                final NodeList elementsByTagName2 = documentElement.getElementsByTagName("COURSE-SYLLABUS");
                if (elementsByTagName2.getLength() != 0) {
                    final NodeList childNodes2 = elementsByTagName2.item(0).getChildNodes();
                    for (int j = 0; j < childNodes2.getLength(); ++j) {
                        final Node item2 = childNodes2.item(j);
                        if (item2.getNodeName() != "#text" && item2.getNodeName() != "SYLLABUS-RESOURCE") {
                            final String[] array2 = new String[4];
                            final String attribute = ((Element)item2).getAttribute("id");
                            final String s11 = null;
                            String nodeValue2 = "";
                            String nodeValue3 = "";
                            array2[0] = attribute;
                            final NodeList childNodes3 = item2.getChildNodes();
                            for (int k = 0; k < childNodes3.getLength(); ++k) {
                                final Node item3 = childNodes3.item(k);
                                if (item3.getNodeName() == "item_name") {
                                    nodeValue2 = item3.getFirstChild().getNodeValue();
                                }
                                if (item3.getNodeName() == "description" && item3.getFirstChild() != null) {
                                    nodeValue3 = item3.getFirstChild().getNodeValue();
                                }
                                if (item3.getNodeName() == "material") {
                                    ((Element)item3).getAttribute("id");
                                    String nodeValue4 = "";
                                    String nodeValue5 = "";
                                    String nodeValue6 = "";
                                    final NodeList childNodes4 = item3.getChildNodes();
                                    for (int l = 0; l < childNodes4.getLength(); ++l) {
                                        final Node item4 = childNodes4.item(l);
                                        if (item4.getNodeName() == "mtype") {
                                            nodeValue4 = item4.getFirstChild().getNodeValue();
                                        }
                                        if (item4.getNodeName() == "description" && item4.getFirstChild() != null) {
                                            nodeValue5 = item4.getFirstChild().getNodeValue();
                                        }
                                        if (item4.getNodeName() == "file_name") {
                                            nodeValue6 = item4.getFirstChild().getNodeValue();
                                        }
                                        if (item4.getNodeName() == "material-resource") {
                                            final String attribute2 = ((Element)item4).getAttribute("id");
                                            final NodeList elementsByTagName3 = documentElement.getElementsByTagName("RESOURCE");
                                            for (int n2 = 0; n2 < elementsByTagName3.getLength(); ++n2) {
                                                final Node item5 = elementsByTagName3.item(n2);
                                                if (attribute2.equals(((Element)item5).getAttribute("id"))) {
                                                    final String nodeValue7 = item5.getChildNodes().item(1).getFirstChild().getNodeValue();
                                                    final String[] array3 = { "syllabus", returnmaxcourse, attribute };
                                                    String s12 = Host.getServerCoursePath();
                                                    for (int n3 = 0; n3 < 3; ++n3) {
                                                        if (s12 == null) {}
                                                        s12 = s12 + File.separator + array3[n3];
                                                        final File file = new File(s12);
                                                        if (!file.exists()) {
                                                            file.mkdir();
                                                        }
                                                    }
                                                    cms.copy(Host.getAdminPath() + File.separatorChar + nodeValue7, Host.getServerCoursePath() + "syllabus" + File.separatorChar + returnmaxcourse + File.separatorChar + attribute + File.separatorChar + nodeValue6);
                                                }
                                            }
                                        }
                                    }
                                    DataBaseLayer.ImportTopicMaterial(returnmaxcourse, attribute, nodeValue4, nodeValue5, nodeValue6, s10);
                                }
                                if (item3.getNodeName() == "item") {
                                    cms.CreateTree(returnmaxcourse, item3, attribute, documentElement);
                                }
                            }
                            array2[1] = s11;
                            array2[2] = nodeValue2;
                            array2[3] = nodeValue3;
                            DataBaseLayer.ImportCourseSyllParent(returnmaxcourse, array2);
                        }
                        if (item2.getNodeName() == "SYLLABUS-RESOURCE") {
                            final Element element = (Element)item2;
                            final String attribute3 = element.getAttribute("id");
                            final String attribute4 = element.getAttribute("name");
                            final NodeList elementsByTagName4 = documentElement.getElementsByTagName("RESOURCE");
                            for (int n4 = 0; n4 < elementsByTagName4.getLength(); ++n4) {
                                final Node item6 = elementsByTagName4.item(n4);
                                if (attribute3.equals(((Element)item6).getAttribute("id"))) {
                                    final String nodeValue8 = item6.getChildNodes().item(1).getFirstChild().getNodeValue();
                                    final String[] array4 = { "syllabus", returnmaxcourse };
                                    String s13 = Host.getServerCoursePath();
                                    for (int n5 = 0; n5 < 2; ++n5) {
                                        if (s13 == null) {}
                                        s13 = s13 + File.separator + array4[n5];
                                        final File file2 = new File(s13);
                                        if (!file2.exists()) {
                                            file2.mkdir();
                                        }
                                    }
                                    cms.copy(Host.getAdminPath() + File.separatorChar + nodeValue8, Host.getServerCoursePath() + "syllabus" + File.separatorChar + returnmaxcourse + File.separatorChar + attribute4);
                                    DataBaseLayer.updateCourseSyllabus(attribute4, returnmaxcourse);
                                }
                            }
                        }
                    }
                }
            }
            if (s.equals("true")) {
                final NodeList elementsByTagName5 = documentElement.getElementsByTagName("COURSE-SESSION");
                if (elementsByTagName5.getLength() != 0) {
                    final NodeList childNodes5 = elementsByTagName5.item(0).getChildNodes();
                    final String[] array5 = new String[6];
                    int n6 = 0;
                    for (int n7 = 0; n7 < childNodes5.getLength(); ++n7) {
                        final Node item7 = childNodes5.item(n7);
                        if (item7.getNodeName() != "#text" && item7.getNodeName() != "session_id") {
                            String nodeValue9 = "";
                            if (item7.getFirstChild() != null) {
                                nodeValue9 = item7.getFirstChild().getNodeValue();
                            }
                            array5[n6] = nodeValue9;
                            ++n6;
                        }
                    }
                    DataBaseLayer.ImportCourseSess(returnmaxcourse, array5);
                }
            }
            if (s2.equals("true")) {
                learnitycms.cms.log.debug("====================inside instructor=========");
                final NodeList elementsByTagName6 = documentElement.getElementsByTagName("COURSE-INSTRUCTOR");
                if (elementsByTagName6.getLength() != 0) {
                    final NodeList childNodes6 = elementsByTagName6.item(0).getChildNodes();
                    for (int n8 = 0; n8 < childNodes6.getLength(); ++n8) {
                        final Node item8 = childNodes6.item(n8);
                        if (item8.getNodeName() == "INSTRUCTOR") {
                            final NodeList childNodes7 = item8.getChildNodes();
                            final String[] array6 = new String[15];
                            int n9 = 0;
                            for (int n10 = 0; n10 < childNodes7.getLength(); ++n10) {
                                final Node item9 = childNodes7.item(n10);
                                if (item9.getNodeName() != "#text") {
                                    String nodeValue10 = "";
                                    if (item9.getFirstChild() != null) {
                                        nodeValue10 = item9.getFirstChild().getNodeValue();
                                        System.out.println("c_ins_value:" + nodeValue10);
                                    }
                                    array6[n9] = nodeValue10;
                                    ++n9;
                                }
                            }
                            DataBaseLayer.ImportCourseIns(returnmaxcourse, array6);
                        }
                        if (item8.getNodeName() == "INSTRUCTOR1") {
                            final NodeList childNodes8 = item8.getChildNodes();
                            final String[] array7 = new String[15];
                            final String s14 = "";
                            int n11 = 0;
                            for (int n12 = 0; n12 < childNodes8.getLength(); ++n12) {
                                final Node item10 = childNodes8.item(n12);
                                if (item10.getNodeName() != "#text") {
                                    String nodeValue11 = "";
                                    if (item10.getFirstChild() != null) {
                                        nodeValue11 = item10.getFirstChild().getNodeValue();
                                    }
                                    array7[n11] = nodeValue11;
                                    ++n11;
                                }
                            }
                            learnitycms.cms.log.debug("======ins_id========" + s14);
                            DataBaseLayer.ImportCourseIns(returnmaxcourse, array7);
                        }
                    }
                }
            }
            if (s3.equals("true")) {
                final NodeList elementsByTagName7 = documentElement.getElementsByTagName("COURSE-LOCATION");
                if (elementsByTagName7.getLength() != 0) {
                    final NodeList childNodes9 = elementsByTagName7.item(0).getChildNodes();
                    for (int n13 = 0; n13 < childNodes9.getLength(); ++n13) {
                        final Node item11 = childNodes9.item(n13);
                        final String[] array8 = new String[6];
                        int n14 = 0;
                        if (item11.getNodeName() != "#text") {
                            ((Element)item11).getAttribute("id");
                            final NodeList childNodes10 = item11.getChildNodes();
                            for (int n15 = 0; n15 < childNodes10.getLength(); ++n15) {
                                final Node item12 = childNodes10.item(n15);
                                if (item12.getNodeName() != "#text") {
                                    String nodeValue12 = "";
                                    if (item12.getFirstChild() != null) {
                                        nodeValue12 = item12.getFirstChild().getNodeValue();
                                    }
                                    array8[n14] = nodeValue12;
                                    ++n14;
                                }
                            }
                            DataBaseLayer.ImportCourseLoc(array8);
                        }
                    }
                }
            }
            if (s4.equals("true")) {
                final NodeList elementsByTagName8 = documentElement.getElementsByTagName("COURSE-SCHEDULE");
                if (elementsByTagName8.getLength() != 0) {
                    final NodeList childNodes11 = elementsByTagName8.item(0).getChildNodes();
                    for (int n16 = 0; n16 < childNodes11.getLength(); ++n16) {
                        final Node item13 = childNodes11.item(n16);
                        final String[] array9 = new String[11];
                        int n17 = 0;
                        if (item13.getNodeName() != "#text" && item13.getNodeName() != "SCHEDULE-RESOURCE") {
                            final String attribute5 = ((Element)item13).getAttribute("id");
                            final NodeList childNodes12 = item13.getChildNodes();
                            for (int n18 = 0; n18 < childNodes12.getLength(); ++n18) {
                                final Node item14 = childNodes12.item(n18);
                                if (item14.getNodeName() != "#text" && item14.getNodeName() != "material-schedule") {
                                    String nodeValue13 = "";
                                    if (item14.getFirstChild() != null) {
                                        nodeValue13 = item14.getFirstChild().getNodeValue();
                                    }
                                    array9[n17] = nodeValue13;
                                    ++n17;
                                }
                                if (item14.getNodeName() == "material-schedule") {
                                    final String attribute6 = ((Element)item14).getAttribute("id");
                                    final NodeList elementsByTagName9 = documentElement.getElementsByTagName("SCHEDULE");
                                    for (int n19 = 0; n19 < elementsByTagName9.getLength(); ++n19) {
                                        final Node item15 = elementsByTagName9.item(n19);
                                        if (attribute6.equals(((Element)item15).getAttribute("id"))) {
                                            final String nodeValue14 = item15.getChildNodes().item(1).getFirstChild().getNodeValue();
                                            final String[] array10 = { "schedule", returnmaxcourse, attribute5 };
                                            String s15 = Host.getServerCoursePath();
                                            for (int n20 = 0; n20 < 3; ++n20) {
                                                if (s15 == null) {}
                                                s15 = s15 + File.separator + array10[n20];
                                                final File file3 = new File(s15);
                                                if (!file3.exists()) {
                                                    file3.mkdir();
                                                }
                                            }
                                            cms.copy(Host.getAdminPath() + File.separatorChar + nodeValue14, Host.getServerCoursePath() + "schedule" + File.separatorChar + returnmaxcourse + File.separatorChar + attribute5 + File.separatorChar + array9[9]);
                                        }
                                    }
                                }
                            }
                            DataBaseLayer.ImportCourseSch(returnmaxcourse, array9);
                        }
                        if (item13.getNodeName() == "SCHEDULE-RESOURCE") {
                            final Element element2 = (Element)item13;
                            final String attribute7 = element2.getAttribute("id");
                            final String attribute8 = element2.getAttribute("name");
                            final NodeList elementsByTagName10 = documentElement.getElementsByTagName("RESOURCE");
                            for (int n21 = 0; n21 < elementsByTagName10.getLength(); ++n21) {
                                final Node item16 = elementsByTagName10.item(n21);
                                if (attribute7.equals(((Element)item16).getAttribute("id"))) {
                                    final String nodeValue15 = item16.getChildNodes().item(1).getFirstChild().getNodeValue();
                                    final String[] array11 = { "schedule", returnmaxcourse };
                                    String s16 = Host.getServerCoursePath();
                                    for (int n22 = 0; n22 < 2; ++n22) {
                                        if (s16 == null) {}
                                        s16 = s16 + File.separator + array11[n22];
                                        final File file4 = new File(s16);
                                        if (!file4.exists()) {
                                            file4.mkdir();
                                        }
                                    }
                                    cms.copy(Host.getAdminPath() + File.separatorChar + nodeValue15, Host.getServerCoursePath() + "schedule" + File.separatorChar + returnmaxcourse + File.separatorChar + attribute8);
                                    DataBaseLayer.updateCourseShedule(attribute8, returnmaxcourse);
                                }
                            }
                        }
                    }
                }
            }
            if (s6.equals("true")) {
                final NodeList elementsByTagName11 = documentElement.getElementsByTagName("COURSE-RESOURCE");
                if (elementsByTagName11.getLength() != 0) {
                    final NodeList childNodes13 = elementsByTagName11.item(0).getChildNodes();
                    for (int n23 = 0; n23 < childNodes13.getLength(); ++n23) {
                        final Node item17 = childNodes13.item(n23);
                        final String[] array12 = new String[5];
                        int n24 = 0;
                        if (item17.getNodeName() != "#text") {
                            final String attribute9 = ((Element)item17).getAttribute("id");
                            final NodeList childNodes14 = item17.getChildNodes();
                            String nodeValue16 = "";
                            for (int n25 = 0; n25 < childNodes14.getLength(); ++n25) {
                                final Node item18 = childNodes14.item(n25);
                                if (item18.getNodeName() != "#text" && item18.getNodeName() != "material-resource") {
                                    String nodeValue17 = "";
                                    if (item18.getFirstChild() != null) {
                                        nodeValue17 = item18.getFirstChild().getNodeValue();
                                    }
                                    if (item18.getNodeName() == "material") {
                                        learnitycms.cms.log.debug("==== material ===" + item18.getFirstChild());
                                        if (item18.getFirstChild() != null) {
                                            nodeValue16 = item18.getFirstChild().getNodeValue();
                                        }
                                    }
                                    array12[n24] = nodeValue17;
                                    ++n24;
                                }
                                if (item18.getNodeName() == "material-resource") {
                                    final String attribute10 = ((Element)item18).getAttribute("id");
                                    final NodeList elementsByTagName12 = documentElement.getElementsByTagName("RESOURCE");
                                    for (int n26 = 0; n26 < elementsByTagName12.getLength(); ++n26) {
                                        final Node item19 = elementsByTagName12.item(n26);
                                        if (attribute10.equals(((Element)item19).getAttribute("id"))) {
                                            final String nodeValue18 = item19.getChildNodes().item(1).getFirstChild().getNodeValue();
                                            final String[] array13 = { "resource", returnmaxcourse, attribute9 };
                                            String s17 = Host.getServerCoursePath();
                                            for (int n27 = 0; n27 < 3; ++n27) {
                                                if (s17 == null) {}
                                                s17 = s17 + File.separator + array13[n27];
                                                final File file5 = new File(s17);
                                                if (!file5.exists()) {
                                                    file5.mkdir();
                                                }
                                            }
                                            cms.copy(Host.getAdminPath() + File.separatorChar + nodeValue18, Host.getServerCoursePath() + "resource" + File.separatorChar + returnmaxcourse + File.separatorChar + attribute9 + File.separatorChar + nodeValue16);
                                        }
                                    }
                                }
                            }
                            DataBaseLayer.ImportCourseRes(returnmaxcourse, array12);
                        }
                    }
                }
            }
            if (s7.equals("true")) {
                final NodeList elementsByTagName13 = documentElement.getElementsByTagName("COURSE-ANNOUNCE");
                if (elementsByTagName13.getLength() != 0) {
                    final NodeList childNodes15 = elementsByTagName13.item(0).getChildNodes();
                    for (int n28 = 0; n28 < childNodes15.getLength(); ++n28) {
                        final Node item20 = childNodes15.item(n28);
                        final String[] array14 = new String[5];
                        int n29 = 0;
                        if (item20.getNodeName() != "#text") {
                            final String attribute11 = ((Element)item20).getAttribute("heading");
                            final NodeList childNodes16 = item20.getChildNodes();
                            String nodeValue19 = "";
                            for (int n30 = 0; n30 < childNodes16.getLength(); ++n30) {
                                final Node item21 = childNodes16.item(n30);
                                if (item21.getNodeName() != "#text" && item21.getNodeName() != "announcement-resource") {
                                    String nodeValue20 = "";
                                    if (item21.getFirstChild() != null) {
                                        nodeValue20 = item21.getFirstChild().getNodeValue();
                                    }
                                    if (item21.getNodeName() == "attachments" && item21.getFirstChild() != null) {
                                        nodeValue19 = item21.getFirstChild().getNodeValue();
                                    }
                                    array14[n29] = nodeValue20;
                                    ++n29;
                                }
                                if (item21.getNodeName() == "announcement-resource") {
                                    final String attribute12 = ((Element)item21).getAttribute("id");
                                    final NodeList elementsByTagName14 = documentElement.getElementsByTagName("RESOURCE");
                                    for (int n31 = 0; n31 < elementsByTagName14.getLength(); ++n31) {
                                        final Node item22 = elementsByTagName14.item(n31);
                                        if (attribute12.equals(((Element)item22).getAttribute("id"))) {
                                            final String nodeValue21 = item22.getChildNodes().item(1).getFirstChild().getNodeValue();
                                            final String[] array15 = { "Announcements", returnmaxcourse, null };
                                            String s18 = Host.getServerCoursePath();
                                            for (int n32 = 0; n32 < 2; ++n32) {
                                                if (s18 == null) {}
                                                s18 = s18 + File.separator + array15[n32];
                                                final File file6 = new File(s18);
                                                if (!file6.exists()) {
                                                    file6.mkdir();
                                                }
                                            }
                                            cms.copy(Host.getAdminPath() + File.separatorChar + nodeValue21, Host.getServerCoursePath() + "Announcements" + File.separatorChar + returnmaxcourse + File.separatorChar + nodeValue19);
                                        }
                                    }
                                }
                            }
                            DataBaseLayer.ImportCourseAnno(returnmaxcourse, attribute11, array14);
                        }
                    }
                }
            }
            if (s8.equals("true")) {
                final NodeList elementsByTagName15 = documentElement.getElementsByTagName("COURSE-ASSIGNMENT");
                if (elementsByTagName15.getLength() != 0) {
                    final NodeList childNodes17 = elementsByTagName15.item(0).getChildNodes();
                    for (int n33 = 0; n33 < childNodes17.getLength(); ++n33) {
                        final Node item23 = childNodes17.item(n33);
                        final String[] array16 = new String[13];
                        final String[] array17 = new String[13];
                        int n34 = 0;
                        int n35 = 0;
                        if (item23.getNodeName() != "#text") {
                            ((Element)item23).getAttribute("id");
                            final NodeList childNodes18 = item23.getChildNodes();
                            final NodeList childNodes19 = item23.getChildNodes();
                            for (int n36 = 0; n36 < childNodes18.getLength(); ++n36) {
                                final Node item24 = childNodes18.item(n36);
                                if (item24.getNodeName() != "#text" && item24.getNodeName() != "material-assignment") {
                                    String nodeValue22 = "";
                                    if (item24.getFirstChild() != null) {
                                        nodeValue22 = item24.getFirstChild().getNodeValue();
                                    }
                                    array16[n34] = nodeValue22;
                                    ++n34;
                                }
                                if (item24.getNodeName() == "material-assignment") {
                                    ((Element)item24).getAttribute("id");
                                    final NodeList elementsByTagName16 = documentElement.getElementsByTagName("ASSIGNMENT");
                                    for (int n37 = 0; n37 < elementsByTagName16.getLength(); ++n37) {
                                        ((Element)elementsByTagName16.item(n37)).getAttribute("id");
                                    }
                                }
                            }
                            DataBaseLayer.ImportCourseAssign(returnmaxcourse, array16);
                            final String returnmaxdocument = DataBaseLayer.returnmaxdocument();
                            for (int n38 = 0; n38 < childNodes19.getLength(); ++n38) {
                                final Node item25 = childNodes19.item(n38);
                                learnitycms.cms.log.debug("========1========");
                                if (item25.getNodeName() != "#text" && item25.getNodeName() != "material-assignment") {
                                    learnitycms.cms.log.debug("========2========");
                                    String nodeValue23 = "";
                                    if (item25.getFirstChild() != null) {
                                        learnitycms.cms.log.debug("========3========");
                                        nodeValue23 = item25.getFirstChild().getNodeValue();
                                    }
                                    array17[n35] = nodeValue23;
                                    ++n35;
                                }
                                if (item25.getNodeName() == "material-assignment") {
                                    learnitycms.cms.log.debug("========4========");
                                    final String attribute13 = ((Element)item25).getAttribute("id");
                                    final NodeList elementsByTagName17 = documentElement.getElementsByTagName("ASSIGNMENT");
                                    for (int n39 = 0; n39 < elementsByTagName17.getLength(); ++n39) {
                                        learnitycms.cms.log.debug("========5========");
                                        final Node item26 = elementsByTagName17.item(n39);
                                        if (attribute13.equals(((Element)item26).getAttribute("id"))) {
                                            final String nodeValue24 = item26.getChildNodes().item(1).getFirstChild().getNodeValue();
                                            learnitycms.cms.log.debug("url values are::" + nodeValue24);
                                            final String[] array18 = { "assignment", returnmaxdocument };
                                            String s19 = Host.getServerCoursePath();
                                            for (int n40 = 0; n40 < 2; ++n40) {
                                                if (s19 == null) {}
                                                s19 = s19 + File.separator + array18[n40];
                                                final File file7 = new File(s19);
                                                if (!file7.exists()) {
                                                    file7.mkdir();
                                                }
                                            }
                                            cms.copy(Host.getAdminPath() + File.separatorChar + nodeValue24, Host.getServerCoursePath() + "assignment" + File.separatorChar + returnmaxdocument + File.separatorChar + array16[11]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (s9.equals("true")) {
                final NodeList elementsByTagName18 = documentElement.getElementsByTagName("COURSE-GRADE");
                if (elementsByTagName18.getLength() != 0) {
                    final Node item27 = elementsByTagName18.item(0);
                    final Element element3 = (Element)item27;
                    final Vector<String> vector = new Vector<String>();
                    if (item27 != null) {
                        final NodeList childNodes20 = item27.getChildNodes();
                        for (int n41 = 0; n41 < childNodes20.getLength(); ++n41) {
                            final Node item28 = childNodes20.item(n41);
                            if (item28.getNodeName() == "grade-book") {
                                final Element element4 = (Element)item28;
                                element4.getAttribute("id");
                                final String attribute14 = element4.getAttribute("name");
                                String s20 = element4.getAttribute("scale");
                                final NodeList elementsByTagName19 = element3.getElementsByTagName("scale");
                                for (int n42 = 0; n42 < elementsByTagName19.getLength(); ++n42) {
                                    final Element element5 = (Element)elementsByTagName19.item(n42);
                                    if (s20.equals(element5.getAttribute("id"))) {
                                        DataBaseLayer.ImportGScale(element5.getAttribute("name"));
                                        s20 = DataBaseLayer.returnmaxscale();
                                        vector.addElement(s20);
                                        final NodeList elementsByTagName20 = element5.getElementsByTagName("grade");
                                        for (int n43 = 0; n43 < elementsByTagName20.getLength(); ++n43) {
                                            final Element element6 = (Element)elementsByTagName20.item(n43);
                                            DataBaseLayer.ImportGScheme(s20, element6.getAttribute("name"), element6.getAttribute("lower"), element6.getAttribute("upper"));
                                        }
                                    }
                                }
                                DataBaseLayer.ImportGBook(attribute14, s20);
                                final String returnmaxgrade = DataBaseLayer.returnmaxgrade();
                                DataBaseLayer.ImportGBookCourseAsso(returnmaxgrade, returnmaxcourse);
                                final NodeList childNodes21 = item28.getChildNodes();
                                for (int n44 = 0; n44 < childNodes21.getLength(); ++n44) {
                                    final Node item29 = childNodes21.item(n44);
                                    if (item29.getNodeName() == "category") {
                                        final Element element7 = (Element)item29;
                                        String s21 = element7.getAttribute("id");
                                        final String attribute15 = element7.getAttribute("name");
                                        String s22 = "no";
                                        final Vector<String> importGCatId = DataBaseLayer.ImportGCatId();
                                        if (importGCatId != null) {
                                            for (int n45 = 0; n45 < importGCatId.size(); ++n45) {
                                                if (attribute15.equals(importGCatId.elementAt(n45))) {
                                                    s21 = DataBaseLayer.ImportGetCategoryId(attribute15);
                                                    if (s21 == null) {
                                                        s21 = "";
                                                    }
                                                    if (s21.equals("")) {
                                                        s22 = "yes";
                                                    }
                                                }
                                            }
                                        }
                                        if (s22.equals("no")) {
                                            DataBaseLayer.ImportCategory(attribute15);
                                        }
                                        final NodeList elementsByTagName21 = element7.getElementsByTagName("item");
                                        for (int n46 = 0; n46 < elementsByTagName21.getLength(); ++n46) {
                                            final Node item30 = elementsByTagName21.item(n46);
                                            final Element element8 = (Element)item30;
                                            element8.getAttribute("id");
                                            final String attribute16 = element8.getAttribute("name");
                                            final String attribute17 = element8.getAttribute("max_marks");
                                            final String attribute18 = element8.getAttribute("pass_marks");
                                            final String attribute19 = element8.getAttribute("scale");
                                            for (int n47 = 0; n47 < elementsByTagName19.getLength(); ++n47) {
                                                final Element element9 = (Element)elementsByTagName19.item(n47);
                                                if (attribute19.equals(element9.getAttribute("id"))) {
                                                    final String attribute20 = element9.getAttribute("name");
                                                    boolean b = false;
                                                    if (vector != null) {
                                                        for (int n48 = 0; n48 < vector.size(); ++n48) {
                                                            if (attribute19.equals(vector.elementAt(n48))) {
                                                                b = true;
                                                            }
                                                        }
                                                    }
                                                    if (b) {
                                                        DataBaseLayer.ImportGScale(attribute20);
                                                    }
                                                    final String returnmaxscale = DataBaseLayer.returnmaxscale();
                                                    final NodeList elementsByTagName22 = element9.getElementsByTagName("grade");
                                                    for (int n49 = 0; n49 < elementsByTagName22.getLength(); ++n49) {
                                                        final Element element10 = (Element)elementsByTagName22.item(n49);
                                                        final String attribute21 = element10.getAttribute("name");
                                                        final String attribute22 = element10.getAttribute("lower");
                                                        final String attribute23 = element10.getAttribute("upper");
                                                        if (b) {
                                                            DataBaseLayer.ImportGScheme(returnmaxscale, attribute21, attribute22, attribute23);
                                                        }
                                                    }
                                                }
                                            }
                                            DataBaseLayer.ImportGItem(s21, attribute16, attribute17, attribute18, attribute19, element8.getAttribute("attempt"));
                                            final String returnmaxitem = DataBaseLayer.returnmaxitem();
                                            final NodeList childNodes22 = item30.getChildNodes();
                                            String nodeValue25 = "";
                                            String nodeValue26 = "";
                                            for (int n50 = 0; n50 < childNodes22.getLength(); ++n50) {
                                                final Node item31 = childNodes22.item(n50);
                                                if (item31.getNodeName() == "schedule_date") {
                                                    nodeValue25 = item31.getFirstChild().getNodeValue();
                                                }
                                                if (item31.getNodeName() == "weightage") {
                                                    nodeValue26 = item31.getFirstChild().getNodeValue();
                                                }
                                            }
                                            DataBaseLayer.ImportGBookItem(returnmaxgrade, s21, returnmaxitem, nodeValue25, nodeValue26);
                                            if (attribute15.equals("Assignment")) {
                                                final Element element11 = (Element)element7.getElementsByTagName("assignment").item(0);
                                                DataBaseLayer.ImportGBookItemAssign(element11.getAttribute("title"), element11.getAttribute("submission_last_date"), element11.getAttribute("start_avail_date"), element11.getAttribute("end_avail_date"), element11.getAttribute("lateflag"), element11.getAttribute("late_allowed_till"), element11.getAttribute("penalty_for_late"));
                                                final String returnmaxassignment = DataBaseLayer.returnmaxassignment();
                                                DataBaseLayer.ImportGBookItemAssignCourseAsso(returnmaxassignment, returnmaxcourse);
                                                final NodeList elementsByTagName23 = element11.getElementsByTagName("document");
                                                if (elementsByTagName23 != null) {
                                                    for (int n51 = 0; n51 < elementsByTagName23.getLength(); ++n51) {
                                                        final Element element12 = (Element)elementsByTagName23.item(n51);
                                                        final String attribute24 = element12.getAttribute("document_id");
                                                        final String attribute25 = element12.getAttribute("document_name");
                                                        DataBaseLayer.ImportGBookItemAssignDoc(returnmaxassignment, attribute24, attribute25, element12.getAttribute("size"));
                                                        final String attribute26 = ((Element)element12.getElementsByTagName("document-resource").item(0)).getAttribute("id");
                                                        final NodeList elementsByTagName24 = documentElement.getElementsByTagName("RESOURCE");
                                                        for (int n52 = 0; n52 < elementsByTagName24.getLength(); ++n52) {
                                                            final Node item32 = elementsByTagName24.item(n52);
                                                            if (attribute26.equals(((Element)item32).getAttribute("id"))) {
                                                                final String nodeValue27 = item32.getChildNodes().item(1).getFirstChild().getNodeValue();
                                                                final String[] array19 = { "assignment", attribute24, null };
                                                                String s23 = Host.getServerCoursePath();
                                                                for (int n53 = 0; n53 < 2; ++n53) {
                                                                    if (s23 == null) {}
                                                                    s23 = s23 + File.separator + array19[n53];
                                                                    final File file8 = new File(s23);
                                                                    if (!file8.exists()) {
                                                                        file8.mkdir();
                                                                    }
                                                                }
                                                                cms.copy(Host.getAdminPath() + File.separatorChar + nodeValue27, Host.getServerCoursePath() + "assignment" + File.separatorChar + attribute24 + File.separatorChar + attribute25);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (SAXException ex) {
            ex.printStackTrace();
        }
        catch (DOMException ex2) {
            ex2.printStackTrace();
        }
        catch (IOException ex3) {
            ex3.printStackTrace();
        }
    }
    
    public String UploadCourseXmlZip(final FileTransfer fileTransfer) {
        WebContextFactory.get().getSession();
        final String adminPath = Host.getAdminPath();
        cms.log.debug("===============strLocation=========" + adminPath);
        final String s = adminPath;
        //final FileUploaderPojo fileUploaderPojo = new FileUploaderPojo(fileTransfer);
        cms.log.debug("==============MimeType==========" + FileUploaderPojo.getMimeType());
        final InputStream inputStream = FileUploaderPojo.getInputStream();
        cms.log.debug("==============is=============" + inputStream);
        final String filename = FileUploaderPojo.getFilename();
        cms.log.debug("==============filename=============" + filename);
        final String string = s + File.separator + filename;
        final String s2 = "";
        cms.log.debug("==============dirName==========" + string);
        try {
            cms.log.debug("dirName===" + string);
            final FileOutputStream fileOutputStream = new FileOutputStream(string);
            final byte[] array = new byte[1024];
            try {
                int read;
                while (inputStream != null && (read = inputStream.read(array)) != -1) {
                    cms.log.debug("=======test1======");
                    fileOutputStream.write(array, 0, read);
                }
            }
            catch (IOException ex2) {}
            cms.log.debug("=======test2======");
            final PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(s2);
            printStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return filename;
    }
    
    public void UnZip(final String s) {
    	//final String s2 = (String)WebContextFactory.get().getSession().getAttribute("user_id");
        try {
            final String string = "" + Host.getAdminPath();
            final byte[] array = new byte[1024];
            final ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(s));
            for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
                final String name = zipEntry.getName();
                cms.log.debug("entryname " + name);
                final File file = new File(string + File.separator + name);
                if (zipEntry.isDirectory()) {
                    file.mkdirs();
                    zipInputStream.closeEntry();
                }
                else {
                    final String parent = file.getParent();
                    cms.log.debug("===========parent=============" + parent);
                    if (parent != null) {
                        final File file2 = new File(parent);
                        cms.log.debug("===============parentFile===============" + file2);
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                    }
                    final FileOutputStream fileOutputStream = new FileOutputStream(string + File.separator + name);
                    int read;
                    while ((read = zipInputStream.read(array, 0, 1024)) > -1) {
                        fileOutputStream.write(array, 0, read);
                    }
                    fileOutputStream.close();
                    zipInputStream.closeEntry();
                }
            }
            zipInputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        cms.log.debug("======================unzip completed=========");
    }
    
    void copy(final String s, final String s2) throws IOException {
        final File file = new File(s);
        final File file2 = new File(s2);
        final FileInputStream fileInputStream = new FileInputStream(file);
        final FileOutputStream fileOutputStream = new FileOutputStream(file2);
        final byte[] array = new byte[1024];
        int read;
        while ((read = fileInputStream.read(array)) > 0) {
            fileOutputStream.write(array, 0, read);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
    
    public void CreateTree(final String s, final Node node, final String s2, final Element element) throws IOException {
        final String s3 = (String)WebContextFactory.get().getSession().getAttribute("user_id");
        try {
            final String[] array = new String[4];
            final String attribute = ((Element)node).getAttribute("id");
            String nodeValue = "";
            String nodeValue2 = "";
            array[0] = attribute;
            final NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); ++i) {
                final Node item = childNodes.item(i);
                if (item.getNodeName() == "item_name") {
                    nodeValue = item.getFirstChild().getNodeValue();
                }
                if (item.getNodeName() == "description" && item.getFirstChild() != null) {
                    nodeValue2 = item.getFirstChild().getNodeValue();
                }
                if (item.getNodeName() == "material") {
                    ((Element)item).getAttribute("id");
                    String nodeValue3 = "";
                    String nodeValue4 = "";
                    String nodeValue5 = "";
                    final NodeList childNodes2 = item.getChildNodes();
                    for (int j = 0; j < childNodes2.getLength(); ++j) {
                        final Node item2 = childNodes2.item(j);
                        if (item2.getNodeName() == "mtype") {
                            nodeValue3 = item2.getFirstChild().getNodeValue();
                        }
                        if (item2.getNodeName() == "description" && item2.getFirstChild() != null) {
                            nodeValue4 = item2.getFirstChild().getNodeValue();
                        }
                        if (item2.getNodeName() == "file_name") {
                            nodeValue5 = item2.getFirstChild().getNodeValue();
                        }
                        if (item2.getNodeName() == "material-resource") {
                            final String attribute2 = ((Element)item2).getAttribute("id");
                            final NodeList elementsByTagName = element.getElementsByTagName("RESOURCE");
                            for (int k = 0; k < elementsByTagName.getLength(); ++k) {
                                final Node item3 = elementsByTagName.item(k);
                                if (attribute2.equals(((Element)item3).getAttribute("id"))) {
                                    final String nodeValue6 = item3.getChildNodes().item(1).getFirstChild().getNodeValue();
                                    final String[] array2 = { "syllabus", s, attribute };
                                    String s4 = Host.getServerCoursePath();
                                    for (int l = 0; l < 3; ++l) {
                                        if (s4 != null) {
                                            s4 = s4 + File.separator + array2[l];
                                            final File file = new File(s4);
                                            if (!file.exists()) {
                                                file.mkdir();
                                            }
                                        }
                                    }
                                    this.copy(Host.getAdminPath() + File.separatorChar + nodeValue6, Host.getServerCoursePath() + "syllabus" + File.separatorChar + s + File.separatorChar + attribute + File.separatorChar + nodeValue5);
                                }
                            }
                        }
                    }
                    DataBaseLayer.ImportTopicMaterial(s, attribute, nodeValue3, nodeValue4, nodeValue5, s3);
                }
                if (item.getNodeName() == "item") {
                    this.CreateTree(s, item, attribute, element);
                }
            }
            array[1] = s2;
            array[2] = nodeValue;
            array[3] = nodeValue2;
            DataBaseLayer.ImportCourseSyllChild(s, array);
        }
        catch (DOMException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
    }
    
    public static void SetCourseId(final String s) {
        WebContextFactory.get().getSession().setAttribute("course_id", (Object)s);
        cms.log.debug("set course_id =" + s);
    }
    
    public FileTransfer CourseExportFile(String s, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9) {
        int n = 1;
        final cms cms = new cms();
        final Vector<String> vector = new Vector<String>();
        final Vector<String> vector2 = new Vector<String>();
        final String s10 = "course.zip";
        vector.addElement("Course.xml");
        vector2.addElement(Host.getAdminPath() + File.separatorChar + "Course.xml");
        final String s11 = (String)WebContextFactory.get().getSession().getAttribute("course_id");
        learnitycms.cms.log.debug("get course_id =" + s11);
        learnitycms.cms.log.debug("Instructor =" + s2);
        if (s == null) {
            s = "F";
        }
        if (s.equals("true")) {
            s = "T";
        }
        if (s2 == null) {
            s2 = "F";
        }
        if (s2.equals("true")) {
            s2 = "T";
        }
        if (s3 == null) {
            s3 = "F";
        }
        if (s3.equals("true")) {
            s3 = "T";
        }
        if (s8 == null) {
            s8 = "F";
        }
        if (s8.equals("true")) {
            s8 = "T";
        }
        if (s4 == null) {
            s4 = "F";
        }
        if (s4.equals("true")) {
            s4 = "T";
        }
        if (s5 == null) {
            s5 = "F";
        }
        if (s5.equals("true")) {
            s5 = "T";
        }
        if (s6 == null) {
            s6 = "F";
        }
        if (s6.equals("true")) {
            s6 = "T";
        }
        if (s7 == null) {
            s7 = "F";
        }
        if (s7.equals("true")) {
            s7 = "T";
        }
        if (s9 == null) {
            s9 = "F";
        }
        if (s9.equals("true")) {
            s9 = "T";
        }
        learnitycms.cms.log.debug("session = " + s);
        learnitycms.cms.log.debug("instructor = " + s2);
        learnitycms.cms.log.debug("location = " + s3);
        learnitycms.cms.log.debug("assign1 = " + s8);
        learnitycms.cms.log.debug("schedule = " + s4);
        learnitycms.cms.log.debug("syllabus = " + s5);
        learnitycms.cms.log.debug("resources = " + s6);
        learnitycms.cms.log.debug("announcement = " + s7);
        learnitycms.cms.log.debug("gradebook = " + s9);
        final String string = Host.getAdminPath() + File.separatorChar + "Course.xml";
        learnitycms.cms.log.debug("attachmentname==" + string);
        /*try {
            final ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(Host.getAdminPath() + File.separatorChar + s10));
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }*/
        try {
            final Document document = DOMImplementationImpl.getDOMImplementation().createDocument(null, "COURSE", null);
            final Element documentElement = document.getDocumentElement();
            documentElement.setAttribute("id", s11);
            learnitycms.cms.log.debug("course_id::" + s11);
            final Element element = document.createElement("RESOURCES");
            final Element element2 = document.createElement("COURSE-INFO");
            final String[] array = { "course_id", "course_name", "type", "sdate", "edate", "createdon", "description", "cpoints", "ttimes", "cost" };
            final String[] exportCourseDefinition = DataBaseLayer.exportCourseDefinition(s11);
            if (exportCourseDefinition != null) {
                for (int i = 1; i < array.length; ++i) {
                    learnitycms.cms.log.debug("course def   " + exportCourseDefinition[i]);
                    final Element element3 = document.createElement(array[i]);
                    element3.appendChild(document.createTextNode(exportCourseDefinition[i]));
                    element2.appendChild(element3);
                }
            }
            documentElement.appendChild(element2);
            if (s2.equals("T")) {
                final Element element4 = document.createElement("COURSE-INSTRUCTOR");
                final Element element5 = document.createElement("INSTRUCTOR");
                final String[] array2 = { "instructor_id", "fname", "mname", "ename", "dob", "sex", "email", "alternatemail", "office_no", "residence_no", "mobile", "stime", "room", "createdon", "address" };
                final String[] exportCourseInstructor = DataBaseLayer.exportCourseInstructor(s11);
                if (exportCourseInstructor != null) {
                    for (int j = 0; j < exportCourseInstructor.length; ++j) {
                        final Element element6 = document.createElement(array2[j]);
                        element6.appendChild(document.createTextNode(exportCourseInstructor[j]));
                        element5.appendChild(element6);
                    }
                }
                element4.appendChild(element5);
                final Element element7 = document.createElement("INSTRUCTOR1");
                final String[] array3 = { "instructor_id", "fname", "mname", "ename", "dob", "sex", "email", "alternatemail", "office_no", "residence_no", "mobile", "stime", "room", "createdon", "address" };
                final String[] exportCourseInstructor2 = DataBaseLayer.exportCourseInstructor1(s11);
                if (exportCourseInstructor2 != null) {
                    for (int k = 0; k < exportCourseInstructor2.length; ++k) {
                        final Element element8 = document.createElement(array3[k]);
                        element8.appendChild(document.createTextNode(exportCourseInstructor2[k]));
                        element7.appendChild(element8);
                    }
                }
                element4.appendChild(element7);
                documentElement.appendChild(element4);
            }
            if (s3.equals("T")) {
                final Element element9 = document.createElement("COURSE-LOCATION");
                final Vector<String[]> exportCourseLocation = DataBaseLayer.exportCourseLocation(s11);
                if (exportCourseLocation != null) {
                    for (int l = 0; l < exportCourseLocation.size(); ++l) {
                        final String[] array4 = exportCourseLocation.elementAt(l);
                        final Element element10 = document.createElement("LOCATION");
                        element10.setAttribute("id", array4[0]);
                        final String[] array5 = { "location_id", "location_title", "location_desc", "location_address", "location_type" };
                        for (int n2 = 1; n2 < array4.length; ++n2) {
                            final Element element11 = document.createElement(array5[n2]);
                            element11.appendChild(document.createTextNode(array4[n2]));
                            element10.appendChild(element11);
                        }
                        element9.appendChild(element10);
                    }
                }
                documentElement.appendChild(element9);
            }
            if (s.equals("T")) {
                final Element element12 = document.createElement("COURSE-SESSION");
                final String[] array6 = { "session_id", "session_name", "sdate", "edate", "description", "createdon" };
                final String[] exportCourseSession = DataBaseLayer.exportCourseSession(s11);
                if (exportCourseSession != null) {
                    for (int n3 = 0; n3 < exportCourseSession.length; ++n3) {
                        final Element element13 = document.createElement(array6[n3]);
                        element13.appendChild(document.createTextNode(exportCourseSession[n3]));
                        element12.appendChild(element13);
                    }
                }
                documentElement.appendChild(element12);
            }
            if (s4.equals("T")) {
                final Element element14 = document.createElement("COURSE-SCHEDULE");
                final String[] exportScheduleFile = DataBaseLayer.exportScheduleFile(s11);
                if (exportScheduleFile[1] != null) {
                    final File file = new File(Host.getServerCoursePath() + File.separatorChar + "schedule" + File.separatorChar + s11 + File.separatorChar + exportScheduleFile[1]);
                    learnitycms.cms.log.debug("check =" + file);
                    if (file.exists()) {
                        final Element element15 = document.createElement("SCHEDULE-RESOURCE");
                        element15.setAttribute("id", "R00" + n);
                        element15.setAttribute("name", exportScheduleFile[1]);
                        element14.appendChild(element15);
                        final Element element16 = document.createElement("RESOURCE");
                        element16.setAttribute("id", "R00" + n);
                        final Element element17 = document.createElement("URL");
                        element17.appendChild(document.createTextNode(exportScheduleFile[1]));
                        element16.appendChild(element17);
                        element.appendChild(element16);
                        vector.addElement(exportScheduleFile[1]);
                        learnitycms.cms.log.debug("strUnitId =" + vector);
                        vector2.addElement(Host.getAdminPath() + File.separatorChar + exportScheduleFile[1]);
                        cms.copy(Host.getServerCoursePath() + File.separatorChar + "schedule" + File.separatorChar + s11 + File.separatorChar + exportScheduleFile[1], Host.getAdminPath() + File.separatorChar + exportScheduleFile[1]);
                        ++n;
                    }
                }
                final Vector<String[]> exportCourseSchedule = DataBaseLayer.exportCourseSchedule(s11);
                if (exportCourseSchedule != null) {
                    for (int n4 = 0; n4 < exportCourseSchedule.size(); ++n4) {
                        final String[] array7 = { "schedule_id", "schedule_name", "sdate", "edate", "stime", "etime", "comment", "location", "mtype", "size", "material" };
                        final String[] array8 = exportCourseSchedule.elementAt(n4);
                        final Element element18 = document.createElement("SCHEDULE");
                        element18.setAttribute("id", array8[0]);
                        for (int n5 = 1; n5 < array8.length; ++n5) {
                            final Element element19 = document.createElement(array7[n5]);
                            element19.appendChild(document.createTextNode(array8[n5]));
                            element18.appendChild(element19);
                        }
                        if (array8[8].equals("File") && new File(Host.getServerCoursePath() + "schedule" + File.separatorChar + s11 + File.separatorChar + array8[0] + File.separatorChar + array8[10]).exists()) {
                            final Element element20 = document.createElement("material-schedule");
                            element20.setAttribute("id", "R00" + n);
                            element18.appendChild(element20);
                            final Element element21 = document.createElement("SCHEDULE");
                            element21.setAttribute("id", "R00" + n);
                            final Element element22 = document.createElement("URL");
                            element22.appendChild(document.createTextNode("schedule" + File.separatorChar + array8[0] + File.separatorChar + array8[10]));
                            element21.appendChild(element22);
                            element.appendChild(element21);
                            vector.addElement("schedule" + File.separatorChar + array8[0] + File.separatorChar + array8[10]);
                            vector2.addElement(Host.getAdminPath() + File.separatorChar + "schedule" + File.separatorChar + array8[0] + File.separatorChar + array8[10]);
                            final String[] array9 = { "schedule", array8[0], null };
                            String s12 = Host.getAdminPath();
                            for (int n6 = 0; n6 < 2; ++n6) {
                                if (s12 != null) {}
                                s12 = s12 + File.separator + array9[n6];
                                final File file2 = new File(s12);
                                if (!file2.exists()) {
                                    file2.mkdir();
                                }
                            }
                            final String string2 = Host.getServerCoursePath() + "schedule" + File.separatorChar + s11 + File.separatorChar + array8[0] + File.separatorChar + array8[10];
                            final String string3 = Host.getAdminPath() + File.separatorChar + "schedule" + File.separatorChar + array8[0] + File.separatorChar + array8[10];
                            learnitycms.cms.log.debug("src_path::" + string2);
                            learnitycms.cms.log.debug("dst_path::" + string3);
                            cms.copy(string2, string3);
                            ++n;
                        }
                        element14.appendChild(element18);
                    }
                }
                documentElement.appendChild(element14);
            }
            if (s5.equals("T")) {
                final Element element23 = document.createElement("COURSE-SYLLABUS");
                final String[] exportScheduleFile2 = DataBaseLayer.exportScheduleFile(s11);
                if (exportScheduleFile2[0] != null && new File(Host.getServerCoursePath() + File.separatorChar + "syllabus" + File.separatorChar + s11 + File.separatorChar + exportScheduleFile2[0]).exists()) {
                    final Element element24 = document.createElement("SYLLABUS-RESOURCE");
                    element24.setAttribute("id", "R00" + n);
                    element24.setAttribute("name", exportScheduleFile2[0]);
                    element23.appendChild(element24);
                    final Element element25 = document.createElement("RESOURCE");
                    element25.setAttribute("id", "R00" + n);
                    final Element element26 = document.createElement("URL");
                    element26.appendChild(document.createTextNode(exportScheduleFile2[0]));
                    element25.appendChild(element26);
                    element.appendChild(element25);
                    vector.addElement(exportScheduleFile2[0]);
                    vector2.addElement(Host.getAdminPath() + File.separatorChar + exportScheduleFile2[0]);
                    cms.copy(Host.getServerCoursePath() + File.separatorChar + "syllabus" + File.separatorChar + s11 + File.separatorChar + exportScheduleFile2[0], Host.getAdminPath() + File.separatorChar + exportScheduleFile2[0]);
                    ++n;
                }
                final Vector<String[]> exportSyllabus = DataBaseLayer.exportSyllabus(s11);
                if (exportSyllabus != null) {
                    for (int n7 = 0; n7 < exportSyllabus.size(); ++n7) {
                        final String[] array10 = { "item_id", "item_name", "description" };
                        final String[] array11 = exportSyllabus.elementAt(n7);
                        final Element element27 = document.createElement("item");
                        element27.setAttribute("id", array11[0]);
                        for (int n8 = 1; n8 < array11.length; ++n8) {
                            final Element element28 = document.createElement(array10[n8]);
                            element28.appendChild(document.createTextNode(array11[n8]));
                            element27.appendChild(element28);
                        }
                        final Vector<String[]> exportTopicMaterial = DataBaseLayer.exportTopicMaterial(s11, array11[0]);
                        if (exportTopicMaterial != null) {
                            for (int n9 = 0; n9 < exportTopicMaterial.size(); ++n9) {
                                final String[] array12 = { "topicmatid", "mtype", "description", "file_name" };
                                final String[] array13 = exportTopicMaterial.elementAt(n9);
                                final Element element29 = document.createElement("material");
                                element29.setAttribute("id", array13[0]);
                                for (int n10 = 1; n10 < array13.length; ++n10) {
                                    final Element element30 = document.createElement(array12[n10]);
                                    element30.appendChild(document.createTextNode(array13[n10]));
                                    element29.appendChild(element30);
                                }
                                if (array13[1].equals("File") && new File(Host.getServerCoursePath() + "syllabus" + File.separatorChar + s11 + File.separatorChar + array11[0] + File.separatorChar + array13[3]).exists()) {
                                    final Element element31 = document.createElement("material-resource");
                                    element31.setAttribute("id", "R00" + n);
                                    element29.appendChild(element31);
                                    final Element element32 = document.createElement("RESOURCE");
                                    element32.setAttribute("id", "R00" + n);
                                    final Element element33 = document.createElement("URL");
                                    element33.appendChild(document.createTextNode("syllabus" + File.separatorChar + array11[0] + File.separatorChar + array13[3]));
                                    element32.appendChild(element33);
                                    element.appendChild(element32);
                                    vector.addElement("syllabus" + File.separatorChar + array11[0] + File.separatorChar + array13[3]);
                                    vector2.addElement(Host.getAdminPath() + File.separatorChar + "syllabus" + File.separatorChar + array11[0] + File.separatorChar + array13[3]);
                                    final String[] array14 = { "syllabus", array11[0], null };
                                    String s13 = Host.getAdminPath();
                                    for (int n11 = 0; n11 < 2; ++n11) {
                                        if (s13 != null) {}
                                        s13 = s13 + File.separator + array14[n11];
                                        final File file3 = new File(s13);
                                        if (!file3.exists()) {
                                            file3.mkdir();
                                        }
                                    }
                                    final String string4 = Host.getServerCoursePath() + "syllabus" + File.separatorChar + s11 + File.separatorChar + array11[0] + File.separatorChar + array13[3];
                                    final String string5 = Host.getAdminPath() + File.separatorChar + "syllabus" + File.separatorChar + array11[0] + File.separatorChar + array13[3];
                                    learnitycms.cms.log.debug("src_path::" + string4);
                                    learnitycms.cms.log.debug("dst_path::" + string5);
                                    cms.copy(string4, string5);
                                    ++n;
                                }
                                element27.appendChild(element29);
                            }
                        }
                        if (DataBaseLayer.exportSyllabusTopic(s11, array11[0]).size() != 0) {
                            cms.createTree(s11, array11[0], element27, element, document, vector, vector2, n);
                        }
                        element23.appendChild(element27);
                    }
                }
                documentElement.appendChild(element23);
            }
            if (s6.equals("T")) {
                final Element element34 = document.createElement("COURSE-RESOURCE");
                final Vector<String[]> exportCourseResource = DataBaseLayer.exportCourseResource(s11);
                if (exportCourseResource != null) {
                    for (int n12 = 0; n12 < exportCourseResource.size(); ++n12) {
                        final String[] array15 = { "material_id", "material_name", "mtype", "size", "material", "description" };
                        final String[] array16 = exportCourseResource.elementAt(n12);
                        final Element element35 = document.createElement("material");
                        element35.setAttribute("id", array16[0]);
                        for (int n13 = 1; n13 < array16.length; ++n13) {
                            final Element element36 = document.createElement(array15[n13]);
                            element36.appendChild(document.createTextNode(array16[n13]));
                            element35.appendChild(element36);
                        }
                        if (array16[2].equals("File") && new File(Host.getServerCoursePath() + "resource" + File.separatorChar + s11 + File.separatorChar + array16[0] + File.separatorChar + array16[4]).exists()) {
                            final Element element37 = document.createElement("material-resource");
                            element37.setAttribute("id", "R00" + n);
                            element35.appendChild(element37);
                            final Element element38 = document.createElement("RESOURCE");
                            element38.setAttribute("id", "R00" + n);
                            final Element element39 = document.createElement("URL");
                            element39.appendChild(document.createTextNode("resource" + File.separatorChar + array16[0] + File.separatorChar + array16[4]));
                            element38.appendChild(element39);
                            element.appendChild(element38);
                            vector.addElement("resource" + File.separatorChar + array16[0] + File.separatorChar + array16[4]);
                            vector2.addElement(Host.getAdminPath() + File.separatorChar + "resource" + File.separatorChar + array16[0] + File.separatorChar + array16[4]);
                            final String[] array17 = { "resource", array16[0], null };
                            String s14 = Host.getAdminPath();
                            for (int n14 = 0; n14 < 2; ++n14) {
                                if (s14 != null) {}
                                s14 = s14 + File.separator + array17[n14];
                                final File file4 = new File(s14);
                                if (!file4.exists()) {
                                    file4.mkdir();
                                }
                            }
                            final String string6 = Host.getServerCoursePath() + "resource" + File.separatorChar + s11 + File.separatorChar + array16[0] + File.separatorChar + array16[4];
                            final String string7 = Host.getAdminPath() + File.separatorChar + "resource" + File.separatorChar + array16[0] + File.separatorChar + array16[4];
                            learnitycms.cms.log.debug("src_path::" + string6);
                            learnitycms.cms.log.debug("dst_path::" + string7);
                            cms.copy(string6, string7);
                            ++n;
                        }
                        element34.appendChild(element35);
                    }
                }
                documentElement.appendChild(element34);
            }
            if (s7.equals("T")) {
                final Element element40 = document.createElement("COURSE-ANNOUNCE");
                final Vector<String[]> exportCourseAnnounce = DataBaseLayer.exportCourseAnnounce(s11);
                if (exportCourseAnnounce != null) {
                    for (int n15 = 0; n15 < exportCourseAnnounce.size(); ++n15) {
                        final String[] array18 = { "heading", "body", "attachments" };
                        final String[] array19 = exportCourseAnnounce.elementAt(n15);
                        final Element element41 = document.createElement("announcement");
                        element41.setAttribute("heading", array19[0]);
                        for (int n16 = 1; n16 < array19.length; ++n16) {
                            final Element element42 = document.createElement(array18[n16]);
                            element42.appendChild(document.createTextNode(array19[n16]));
                            element41.appendChild(element42);
                        }
                        if (array19[2] != null && !array19[2].equals("")) {
                            final Element element43 = document.createElement("announcement-resource");
                            element43.setAttribute("id", "R00" + n);
                            element41.appendChild(element43);
                            final Element element44 = document.createElement("RESOURCE");
                            element44.setAttribute("id", "R00" + n);
                            final Element element45 = document.createElement("URL");
                            element45.appendChild(document.createTextNode("Announcements" + File.separatorChar + array19[2]));
                            element44.appendChild(element45);
                            element.appendChild(element44);
                            vector.addElement("Announcements" + File.separatorChar + array19[2]);
                            vector2.addElement(Host.getAdminPath() + File.separatorChar + "Announcements" + File.separatorChar + array19[2]);
                            learnitycms.cms.log.debug("attachment data::" + array19[2]);
                            final String[] array20 = { "Announcements", null, null };
                            String s15 = Host.getAdminPath();
                            for (int n17 = 0; n17 < 1; ++n17) {
                                if (s15 != null) {}
                                s15 = s15 + File.separator + array20[n17];
                                final File file5 = new File(s15);
                                if (!file5.exists()) {
                                    file5.mkdir();
                                }
                            }
                            final String string8 = Host.getServerCoursePath() + "Announcements" + File.separatorChar + s11 + File.separatorChar + array19[2];
                            final String string9 = Host.getAdminPath() + File.separatorChar + "Announcements" + File.separatorChar + array19[2];
                            learnitycms.cms.log.debug("src_path::" + string8);
                            learnitycms.cms.log.debug("dst_path::" + string9);
                            cms.copy(string8, string9);
                            ++n;
                        }
                        element40.appendChild(element41);
                    }
                }
                documentElement.appendChild(element40);
            }
            if (s8.equals("T")) {
                learnitycms.cms.log.debug("============================inside assignment");
                final Element element46 = document.createElement("COURSE-ASSIGNMENT");
                final Vector<String[]> exportCourseAssignment = DataBaseLayer.exportCourseAssignment(s11);
                learnitycms.cms.log.debug("==========size====" + exportCourseAssignment.size());
                if (exportCourseAssignment != null) {
                    for (int n18 = 0; n18 < exportCourseAssignment.size(); ++n18) {
                        final String[] array21 = { "assignment_id", "title", "submission_last_date", "start_avail_date", "end_avail_date", "description", "lateflag", "late_allowed_till", "penalty_for_late", "full_mark", "pass_mark" };
                        final String[] array22 = exportCourseAssignment.elementAt(n18);
                        final Element element47 = document.createElement("ASSIGNMENT");
                        element47.setAttribute("id", array22[0]);
                        for (int n19 = 1; n19 < array22.length; ++n19) {
                            final Element element48 = document.createElement(array21[n19]);
                            element48.appendChild(document.createTextNode(array22[n19]));
                            element47.appendChild(element48);
                        }
                        final Vector<String[]> exportCourseAssignmentDocument = DataBaseLayer.exportCourseAssignmentDocument(array22[0]);
                        for (int n20 = 0; n20 < exportCourseAssignmentDocument.size(); ++n20) {
                            final String[] array23 = { "document_id", "document_name", "document_size" };
                            final String[] array24 = exportCourseAssignmentDocument.elementAt(n20);
                            document.createElement("DOCUMENT");
                            for (int n21 = 0; n21 < array24.length; ++n21) {
                                final Element element49 = document.createElement(array23[n21]);
                                element49.appendChild(document.createTextNode(array24[n21]));
                                element47.appendChild(element49);
                            }
                            if (!array24[0].equals("null")) {
                                learnitycms.cms.log.debug("================strDoc[0]========" + array24[0]);
                                if (new File(Host.getServerCoursePath() + "assignment" + File.separatorChar + array24[0] + File.separatorChar + array24[1]).exists()) {
                                    final Element element50 = document.createElement("material-assignment");
                                    element50.setAttribute("id", "R00" + n);
                                    element47.appendChild(element50);
                                    final Element element51 = document.createElement("ASSIGNMENT");
                                    element51.setAttribute("id", "R00" + n);
                                    final Element element52 = document.createElement("URL");
                                    element52.appendChild(document.createTextNode("assignment" + File.separatorChar + array24[0] + File.separatorChar + array24[1]));
                                    element51.appendChild(element52);
                                    element.appendChild(element51);
                                    vector.addElement("assignment" + File.separatorChar + array24[0] + File.separatorChar + array24[1]);
                                    vector2.addElement(Host.getAdminPath() + File.separatorChar + "assignment" + File.separatorChar + array24[0] + File.separatorChar + array24[1]);
                                    learnitycms.cms.log.debug("===============strUnitId in assignment====" + vector);
                                    final String[] array25 = { "assignment", array24[0], null };
                                    String s16 = Host.getAdminPath();
                                    for (int n22 = 0; n22 < 2; ++n22) {
                                        if (s16 != null) {}
                                        s16 = s16 + File.separator + array25[n22];
                                        final File file6 = new File(s16);
                                        if (!file6.exists()) {
                                            file6.mkdir();
                                        }
                                    }
                                    final String string10 = Host.getServerCoursePath() + "assignment" + File.separatorChar + array24[0] + File.separatorChar + array24[1];
                                    final String string11 = Host.getAdminPath() + File.separatorChar + "assignment" + File.separatorChar + array24[0] + File.separatorChar + array24[1];
                                    learnitycms.cms.log.debug("src_path::" + string10);
                                    learnitycms.cms.log.debug("dst_path::" + string11);
                                    cms.copy(string10, string11);
                                    ++n;
                                }
                            }
                        }
                        element46.appendChild(element47);
                    }
                }
                documentElement.appendChild(element46);
            }
            if (s9.equals("T")) {
                final Element element53 = document.createElement("COURSE-GRADE");
                final Element element54 = document.createElement("grade-scale");
                final Element element55 = document.createElement("grade-book");
                final Vector<String> vector3 = new Vector<String>();
                final String[] array26 = { "id", "name", "scale" };
                final String[] exportCourseGB = DataBaseLayer.exportCourseGB(s11);
                learnitycms.cms.log.debug("===== grade book===" + exportCourseGB[0]);
                if (exportCourseGB[0] != null && exportCourseGB.length != 0) {
                    for (int n23 = 0; n23 < exportCourseGB.length; ++n23) {
                        element55.setAttribute(array26[n23], exportCourseGB[n23]);
                    }
                    if (exportCourseGB[2] != null) {
                        final String[] exportCourseSc = DataBaseLayer.exportCourseSc(exportCourseGB[2]);
                        final String[] array27 = { "id", "name" };
                        final Element element56 = document.createElement("scale");
                        for (int n24 = 0; n24 < exportCourseSc.length; ++n24) {
                            element56.setAttribute(array27[n24], exportCourseSc[n24]);
                        }
                        final Vector<String[]> exportScaleSch = DataBaseLayer.exportScaleSch(exportCourseGB[2]);
                        if (exportScaleSch != null) {
                            for (int n25 = 0; n25 < exportScaleSch.size(); ++n25) {
                                final String[] array28 = exportScaleSch.elementAt(n25);
                                final String[] array29 = { "name", "lower", "upper" };
                                final Element element57 = document.createElement("grade");
                                for (int n26 = 0; n26 < array29.length; ++n26) {
                                    element57.setAttribute(array29[n26], array28[n26]);
                                }
                                element56.appendChild(element57);
                            }
                        }
                        vector3.addElement(exportCourseGB[2]);
                        element54.appendChild(element56);
                    }
                    if (exportCourseGB[0] != null) {
                        final Vector<String[]> exportCourseCategory = DataBaseLayer.exportCourseCategory(exportCourseGB[0]);
                        if (exportCourseCategory != null) {
                            for (int n27 = 0; n27 < exportCourseCategory.size(); ++n27) {
                                final String[] array30 = exportCourseCategory.elementAt(n27);
                                final String[] array31 = { "id", "name" };
                                final Element element58 = document.createElement("category");
                                for (int n28 = 0; n28 < array30.length; ++n28) {
                                    element58.setAttribute(array31[n28], array30[n28]);
                                }
                                if (array30[0] != null) {
                                    final Vector<String[]> exportCourseItem = DataBaseLayer.exportCourseItem(exportCourseGB[0], array30[0]);
                                    for (int n29 = 0; n29 < exportCourseItem.size(); ++n29) {
                                        final String[] array32 = exportCourseItem.elementAt(n29);
                                        final String[] array33 = { "id", "name", "max_marks", "pass_marks", "scale", "attempt" };
                                        final Element element59 = document.createElement("item");
                                        for (int n30 = 0; n30 < array33.length; ++n30) {
                                            element59.setAttribute(array33[n30], array32[n30]);
                                        }
                                        if (array32[0] != null) {
                                            final String[] array34 = { "schedule_date", "weightage" };
                                            final String[] exportBookItem = DataBaseLayer.exportBookItem(exportCourseGB[0], array30[0], array32[0]);
                                            for (int n31 = 0; n31 < array34.length; ++n31) {
                                                final Element element60 = document.createElement(array34[n31]);
                                                element60.appendChild(document.createTextNode(exportBookItem[n31]));
                                                element59.appendChild(element60);
                                            }
                                        }
                                        if ("Assignment".equals(array30[1])) {
                                            final String[] exportBookItemAssignment = DataBaseLayer.exportBookItemAssignment(array32[0]);
                                            final String[] array35 = { "title", "submission_last_date", "start_avail_date", "end_avail_date", "lateflag", "late_allowed_till", "penalty_for_late" };
                                            final Element element61 = document.createElement("assignment");
                                            for (int n32 = 0; n32 < array35.length; ++n32) {
                                                element61.setAttribute(array35[n32], exportBookItemAssignment[n32]);
                                            }
                                            final Vector<String[]> exportItemAssignmentDoc = DataBaseLayer.exportItemAssignmentDoc(array32[0]);
                                            if (exportItemAssignmentDoc != null) {
                                                for (int n33 = 0; n33 < exportItemAssignmentDoc.size(); ++n33) {
                                                    final String[] array36 = { "document_id", "document_name", "size" };
                                                    final String[] array37 = exportItemAssignmentDoc.elementAt(n33);
                                                    final Element element62 = document.createElement("document");
                                                    for (int n34 = 0; n34 < array36.length; ++n34) {
                                                        element62.setAttribute(array36[n34], array37[n34]);
                                                    }
                                                    if (array37[1] != null) {
                                                        final Element element63 = document.createElement("document-resource");
                                                        element63.setAttribute("id", "R00" + n);
                                                        element62.appendChild(element63);
                                                        final Element element64 = document.createElement("RESOURCE");
                                                        element64.setAttribute("id", "R00" + n);
                                                        final Element element65 = document.createElement("URL");
                                                        element65.appendChild(document.createTextNode("Assignment" + File.separatorChar + array37[0] + File.separatorChar + array37[1]));
                                                        element64.appendChild(element65);
                                                        element.appendChild(element64);
                                                        vector.addElement("Assignment" + File.separatorChar + array37[0] + File.separatorChar + array37[1]);
                                                        vector2.addElement(Host.getAdminPath() + File.separatorChar + "Assignment" + File.separatorChar + array37[0] + File.separatorChar + array37[1]);
                                                        final String[] array38 = { "Assignment", array37[0], null };
                                                        String s17 = Host.getAdminPath();
                                                        for (int n35 = 0; n35 < 2; ++n35) {
                                                            if (s17 != null) {}
                                                            s17 = s17 + File.separator + array38[n35];
                                                            final File file7 = new File(s17);
                                                            if (!file7.exists()) {
                                                                file7.mkdir();
                                                            }
                                                        }
                                                        final String string12 = Host.getServerCoursePath() + "assignment" + File.separatorChar + File.separatorChar + array37[0] + File.separatorChar + array37[1];
                                                        final String string13 = Host.getAdminPath() + File.separatorChar + "Assignment" + File.separatorChar + array37[0] + File.separatorChar + array37[1];
                                                        learnitycms.cms.log.debug("src_path::" + string12);
                                                        learnitycms.cms.log.debug("dst_path::" + string13);
                                                        cms.copy(string12, string13);
                                                        ++n;
                                                    }
                                                    element61.appendChild(element62);
                                                }
                                            }
                                            element59.appendChild(element61);
                                        }
                                        if (array32[4] != null) {
                                            boolean b = false;
                                            if (vector3 != null) {
                                                for (int n36 = 0; n36 < vector3.size(); ++n36) {
                                                    if (vector3.elementAt(n36).equals(array32[4])) {
                                                        learnitycms.cms.log.debug("**********111********" + array32[4]);
                                                        b = true;
                                                    }
                                                    else {
                                                        learnitycms.cms.log.debug("**********222********" + array32[4]);
                                                        b = false;
                                                    }
                                                }
                                            }
                                            if (!b) {
                                                final String[] exportCourseSc2 = DataBaseLayer.exportCourseSc(array32[4]);
                                                final String[] array39 = { "id", "name" };
                                                final Element element66 = document.createElement("scale");
                                                for (int n37 = 0; n37 < exportCourseSc2.length; ++n37) {
                                                    element66.setAttribute(array39[n37], exportCourseSc2[n37]);
                                                    learnitycms.cms.log.debug("from flag::" + exportCourseSc2[n37]);
                                                }
                                                final Vector<String[]> exportScaleSch2 = DataBaseLayer.exportScaleSch(array32[4]);
                                                if (exportScaleSch2 != null) {
                                                    for (int n38 = 0; n38 < exportScaleSch2.size(); ++n38) {
                                                        final String[] array40 = exportScaleSch2.elementAt(n38);
                                                        final String[] array41 = { "name", "lower", "upper" };
                                                        final Element element67 = document.createElement("grade");
                                                        for (int n39 = 0; n39 < array41.length; ++n39) {
                                                            element67.setAttribute(array41[n39], array40[n39]);
                                                        }
                                                        element66.appendChild(element67);
                                                    }
                                                }
                                                vector3.addElement(array32[4]);
                                                element54.appendChild(element66);
                                            }
                                        }
                                        element58.appendChild(element59);
                                    }
                                }
                                element55.appendChild(element58);
                            }
                        }
                    }
                    element53.appendChild(element55);
                    element53.appendChild(element54);
                }
                documentElement.appendChild(element53);
            }
            documentElement.appendChild(element);
            new XMLSerializer((OutputStream)new FileOutputStream(string), new OutputFormat(document, "UTF-8", true)).serialize(document.getDocumentElement());
        }
        catch (DOMException ex2) {
            ex2.printStackTrace();
        }
        catch (IOException ex3) {
            ex3.printStackTrace();
        }
        final cms cms2 = new cms();
        //InputStream inputStream = null;
        try {
            cms2.makeZip(s10, new ZipOutputStream(new FileOutputStream(Host.getAdminPath() + File.separatorChar + s10)), vector, vector2, n);
            //inputStream = new FileInputStream(Host.getAdminPath() + File.separatorChar + s10);
        }
        catch (FileNotFoundException ex4) {
            ex4.printStackTrace();
        }
        //final FileDownloaderPojo fileDownloaderPojo = new FileDownloaderPojo(inputStream, "", "Course.zip");
        return FileDownloaderPojo.returnFileFormat();
    }
    
    public void makeZip(final String s, final ZipOutputStream zipOutputStream, final Vector<String> vector, final Vector<String> vector2, final int n) {
        cms.log.debug("name === " + s);
        cms.log.debug("The pathname:  " + (Host.getAdminPath() + File.separatorChar));
        cms.log.debug("strUnitId.size() =" + vector.size());
        cms.log.debug("strUnitId =" + vector);
        final byte[] array = new byte[18024];
        try {
            for (int i = 0; i < vector.size(); ++i) {
                zipOutputStream.setLevel(-1);
                final FileInputStream fileInputStream = new FileInputStream(vector2.elementAt(i));
                cms.log.debug("file name with path::" + vector2.elementAt(i));
                zipOutputStream.putNextEntry(new ZipEntry(vector.elementAt(i)));
                int read;
                while ((read = fileInputStream.read(array)) > 0) {
                    zipOutputStream.write(array, 0, read);
                }
                zipOutputStream.closeEntry();
                fileInputStream.close();
            }
            zipOutputStream.close();
        }
        catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        catch (FileNotFoundException ex2) {
            ex2.printStackTrace();
        }
        catch (IOException ex3) {
            ex3.printStackTrace();
        }
    }
    
    public void createTree(final String s, final String s2, final Element element, final Element element2, final Document document, final Vector<String> vector, final Vector<String> vector2, int n) {
        try {
            final Vector<String[]> exportSyllabusTopic = DataBaseLayer.exportSyllabusTopic(s, s2);
            if (exportSyllabusTopic != null) {
                for (int i = 0; i < exportSyllabusTopic.size(); ++i) {
                    final String[] array = { "item_id", "item_name", "description" };
                    final String[] array2 = exportSyllabusTopic.elementAt(i);
                    final Element element3 = document.createElement("item");
                    element3.setAttribute("id", array2[0]);
                    for (int j = 1; j < array2.length; ++j) {
                        final Element element4 = document.createElement(array[j]);
                        element4.appendChild(document.createTextNode(array2[j]));
                        element3.appendChild(element4);
                    }
                    final Vector<String[]> exportTopicMaterial = DataBaseLayer.exportTopicMaterial(s, array2[0]);
                    if (exportTopicMaterial != null) {
                        for (int k = 0; k < exportTopicMaterial.size(); ++k) {
                            final String[] array3 = { "topicmatid", "mtype", "description", "file_name" };
                            final String[] array4 = exportTopicMaterial.elementAt(k);
                            final Element element5 = document.createElement("material");
                            element5.setAttribute("id", array4[0]);
                            for (int l = 1; l < array4.length; ++l) {
                                final Element element6 = document.createElement(array3[l]);
                                element6.appendChild(document.createTextNode(array4[l]));
                                element5.appendChild(element6);
                            }
                            element3.appendChild(element5);
                            if (array4[1].equals("File") && new File(Host.getServerCoursePath() + "syllabus" + File.separatorChar + s + File.separatorChar + array2[0] + File.separatorChar + array4[3]).exists()) {
                                final Element element7 = document.createElement("material-resource");
                                element7.setAttribute("id", "R00" + n);
                                element5.appendChild(element7);
                                final Element element8 = document.createElement("RESOURCE");
                                element8.setAttribute("id", "R00" + n);
                                final Element element9 = document.createElement("URL");
                                element9.appendChild(document.createTextNode("syllabus" + File.separatorChar + array2[0] + File.separatorChar + array4[3]));
                                element8.appendChild(element9);
                                element2.appendChild(element8);
                                vector.addElement("syllabus" + File.separatorChar + array2[0] + File.separatorChar + array4[3]);
                                vector2.addElement(Host.getAdminPath() + File.separatorChar + "syllabus" + File.separatorChar + array2[0] + File.separatorChar + array4[3]);
                                final String[] array5 = { "syllabus", array2[0], null };
                                String s3 = Host.getAdminPath();
                                for (int n2 = 0; n2 < 2; ++n2) {
                                    s3 = s3 + File.separator + array5[n2];
                                    final File file = new File(s3);
                                    if (!file.exists()) {
                                        file.mkdir();
                                    }
                                }
                                this.copy(Host.getServerCoursePath() + File.separatorChar + "syllabus" + File.separatorChar + s + File.separatorChar + array2[0] + File.separatorChar + array4[3], Host.getAdminPath() + File.separatorChar + "syllabus" + File.separatorChar + array2[0] + File.separatorChar + array4[3]);
                                ++n;
                            }
                        }
                    }
                    if (DataBaseLayer.exportSyllabusTopic(s, s2).size() != 0) {
                        this.createTree(s, array2[0], element3, element2, document, vector, vector2, n);
                    }
                    element.appendChild(element3);
                }
            }
        }
        catch (DOMException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
    }
    
    public String encodeFile(final String s) {
        String encode = "";
        //final DOMParser domParser = new DOMParser();
        try {
            cms.log.debug("   " + s);
            final byte[] file = this.readFile(s);
            //final StringBuffer sb = new StringBuffer();
            encode = Base64.encode(file);
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
        return encode;
    }
    
    public byte[] readFile(final String s) throws IOException {
        final File file = new File(s);
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        final int n = (int)file.length();
        final byte[] array = new byte[n];
        final int read = bufferedInputStream.read(array);
        bufferedInputStream.close();
        cms.log.debug("Read " + read + " and expected to read " + n);
        return array;
    }
    
    public static void insertViewPreviledge(final String s, final String s2) {
        DataBaseLayer.insertViewPreviledge(s, s2);
    }
    
    public static String getViewPreviledge(final String s) {
        final String viewPreviledge = DataBaseLayer.getViewPreviledge(s);
        return (viewPreviledge == null) ? "" : viewPreviledge;
    }
    
    public static String getItems(final String s) {
        String string = "<option value=0>Choose One</option>";
        final Vector<String[]> selectItemForCourse = DataBaseLayer.selectItemForCourse(s);
        if (selectItemForCourse != null) {
            for (int i = 0; i < selectItemForCourse.size(); ++i) {
                final String[] array = selectItemForCourse.elementAt(i);
                string = string + "<option value=" + array[0] + ">" + array[1] + "</option>";
            }
        }
        cms.log.debug("options Items = " + string);
        return string;
    }
    
    public static String getStudents(final String s) {
        String string = "<option value=0>Choose One</option>";
        final Vector<String[]> studentList = DataBaseLayer.getStudentList(s);
        if (studentList != null) {
            for (int i = 0; i < studentList.size(); ++i) {
                final String[] array = studentList.elementAt(i);
                string = string + "<option value=" + array[0] + ">" + array[1] + "</option>";
            }
        }
        cms.log.debug("options Students = " + string);
        return string;
    }
    
    public static String getAttempts(final String s) {
        String string = "<option value=0>Choose One</option>";
        final String attempt = DataBaseLayer.getAttempt(s);
        if (attempt != "") {
            for (int int1 = Integer.parseInt(attempt), i = 1; i <= int1; ++i) {
                final String string2 = "attempt" + i;
                string = string + "<option value=" + string2 + ">" + string2 + "</option>";
            }
        }
        cms.log.debug("options Attempt = " + string);
        return string;
    }
    
    public static String getMarks(final String s, final String s2, final String s3, final String s4) {
        return DataBaseLayer.getMarks(s, s2, s3, s4);
    }
    
    public static void addGradeMgmt(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        DataBaseLayer.addGradeMgmt(s, s2, s3, s4, s5, s6, (String)WebContextFactory.get().getSession().getAttribute("user_id"));
        //final Vector vector = new Vector(10, 10);
        double n = 0.0;
        double n2 = 0.0;
        double n3 = 0.0;
        int n4 = 0;
        final Vector<String[]> selectItemInformationCidAdmin = DataBaseLayer.selectItemInformationCidAdmin(s);
        for (int i = 0; i < selectItemInformationCidAdmin.size(); ++i) {
            final String[] array = selectItemInformationCidAdmin.elementAt(i);
            cms.log.debug("----strGradebook----" + array);
            cms.log.debug("itemname----->" + array[0]);
            final int int1 = Integer.parseInt(array[1]);
            cms.log.debug("no_of_attempts--------->" + int1);
            final String s7 = array[2];
            cms.log.debug("item_id------------->" + s7);
            final String s8 = array[3];
            cms.log.debug("item_marks_scheme------------->" + s8);
            if (s8.equals("Best Of")) {
                for (int j = 0; j < int1; ++j) {
                    final String string = "attempt" + (j + 1);
                    cms.log.debug("satt =" + string);
                    final double double1 = Double.parseDouble(DataBaseLayer.selectMarks(s3, s, s7, string));
                    cms.log.debug("--------ORIGINAL MARKS--------> " + double1);
                    if (double1 > n3) {
                        n3 = double1;
                        cms.log.debug("-----temp_marks------> " + n3);
                    }
                    n = n3;
                    cms.log.debug("-------------E1---------> " + n);
                }
            }
            if (s8.equals("Average")) {
                for (int k = 0; k < int1; ++k) {
                    final String selectMarks = DataBaseLayer.selectMarks(s3, s, s7, "attempt" + (k + 1));
                    final double double2 = Double.parseDouble(selectMarks);
                    if (double2 > 0.0) {
                        cms.log.debug("marks  = " + selectMarks);
                        n += double2;
                        ++n4;
                        cms.log.debug("divider inn = " + n4);
                    }
                }
                cms.log.debug("-------------E1 in Average Condition---------> " + n);
            }
            n2 = n;
        }
        if (n4 > 0) {
            n2 /= n4;
        }
        DataBaseLayer.insertOverallMarks(s, s3, n2);
    }
    
    public String getClassInfo(final String s) {
        //final String s2 = (String)WebContextFactory.get().getSession().getAttribute("user_id");
        if (s.equals("0") || s.equals("") || s == null) {
            return "<table></table>";
        }
        final Vector<String> vector = new Vector<String>(10, 10);
        int int1 = 0;
        final Vector<String> vector2 = new Vector<String>(10, 10);
        int int2 = 0;
        final Vector<String> vector3 = new Vector<String>(10, 10);
        int n = 0;
        final Vector<String> vector4 = new Vector<String>(10, 10);
        int n2 = 0;
        final Vector<String> vector5 = new Vector<String>(10, 10);
        int n3 = 0;
        final Vector<String[]> selectItemInformationCidAdmin = DataBaseLayer.selectItemInformationCidAdmin(s);
        final String[] selectColumnCidAdmin = DataBaseLayer.selectColumnCidAdmin(s);
        DataBaseLayer.getPriviledge(s);
        Document document = null;
        final DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
        try {
            instance.setValidating(true);
            document = instance.newDocumentBuilder().newDocument();
        }
        catch (ParserConfigurationException ex) {}
        final Element element = document.createElement("table");
        document.appendChild(element);
        element.setAttribute("border", "1");
        element.setAttribute("cellpadding", "0");
        element.setAttribute("cellspacing", "0");
        String html = "";
        int int3;
        if (selectColumnCidAdmin[0] != null) {
            int3 = Integer.parseInt(selectColumnCidAdmin[0]);
        }
        else {
            int3 = 1;
        }
        final Element element2 = document.createElement("tr");
        element.appendChild(element2);
        element2.setAttribute("bgcolor", "#40A0D0");
        element2.setAttribute("color", "#FFFFFF");
        final Element element3 = document.createElement("tr");
        element.appendChild(element3);
        element3.setAttribute("bgcolor", "#6AC9FF");
        element3.setAttribute("color", "#FFFFFF");
        final Element element4 = document.createElement("tr");
        element.appendChild(element4);
        element4.setAttribute("bgcolor", "#6AC9FF");
        element4.setAttribute("color", "#FFFFFF");
        final Element element5 = document.createElement("tr");
        element.appendChild(element5);
        element5.setAttribute("bgcolor", "#6AC9FF");
        element5.setAttribute("color", "#FFFFFF");
        final Element element6 = document.createElement("tr");
        element.appendChild(element6);
        element6.setAttribute("bgcolor", "#6AC9FF");
        element6.setAttribute("color", "#FFFFFF");
        final Element element7 = document.createElement("tr");
        element.appendChild(element7);
        element7.setAttribute("bgcolor", "#6AC9FF");
        element7.setAttribute("color", "#FFFFFF");
        final Element element8 = document.createElement("tr");
        element.appendChild(element8);
        element8.setAttribute("bgcolor", "#6AC9FF");
        element8.setAttribute("color", "#FFFFFF");
        cms.log.debug("---->>>>----itemtab.size()-- " + selectItemInformationCidAdmin.size());
        if (selectItemInformationCidAdmin.size() != 0) {
            final Element element9 = document.createElement("td");
            element9.setAttribute("class", "swb");
            element2.appendChild(element9);
            element9.appendChild(document.createTextNode("\t"));
            for (int i = 0; i < selectItemInformationCidAdmin.size(); ++i) {
                final Element element10 = document.createElement("td");
                element10.setAttribute("class", "swb");
                element2.appendChild(element10);
                element10.setAttribute("width", "" + 80 / int3 + "%");
                final String[] array = selectItemInformationCidAdmin.elementAt(i);
                cms.log.debug("---->>>>----items()-- " + array[0]);
                element10.setAttribute("colspan", array[1]);
                element10.appendChild(document.createTextNode(array[0]));
            }
        }
        final Element element11 = document.createElement("td");
        element11.setAttribute("width", "10%");
        element11.setAttribute("class", "swb");
        element2.appendChild(element11);
        element11.appendChild(document.createTextNode("Overall"));
        final Element element12 = document.createElement("td");
        element12.setAttribute("width", "10%");
        element12.setAttribute("class", "swb");
        element3.appendChild(element12);
        element12.appendChild(document.createTextNode("Attempt"));
        final Element element13 = document.createElement("td");
        element13.setAttribute("width", "10%");
        element13.setAttribute("class", "swb");
        element4.appendChild(element13);
        element13.appendChild(document.createTextNode("Lowest"));
        final Element element14 = document.createElement("td");
        element14.setAttribute("width", "10%");
        element14.setAttribute("class", "swb");
        element5.appendChild(element14);
        element14.appendChild(document.createTextNode("Highest"));
        final Element element15 = document.createElement("td");
        element15.setAttribute("width", "10%");
        element15.setAttribute("class", "swb");
        element6.appendChild(element15);
        element15.appendChild(document.createTextNode("Average"));
        final Element element16 = document.createElement("td");
        element16.setAttribute("width", "10%");
        element16.setAttribute("class", "swb");
        element7.appendChild(element16);
        element16.appendChild(document.createTextNode("Full Marks"));
        final Element element17 = document.createElement("td");
        element17.setAttribute("width", "10%");
        element17.setAttribute("class", "swb");
        element8.appendChild(element17);
        element17.appendChild(document.createTextNode("Pass Marks"));
        for (int j = 0; j < selectItemInformationCidAdmin.size(); ++j) {
            final String[] array2 = selectItemInformationCidAdmin.elementAt(j);
            final String[] selectFullMarks1 = DataBaseLayer.selectFullMarks1(array2[2]);
            final String[] selectPassMarks1 = DataBaseLayer.selectPassMarks1(array2[2]);
            if (selectFullMarks1[0] == null) {
                selectFullMarks1[0] = "--";
            }
            else {
                vector4.addElement(selectFullMarks1[0]);
            }
            if (selectPassMarks1[0] == null) {
                selectPassMarks1[0] = "--";
            }
            else {
                vector5.addElement(selectPassMarks1[0]);
            }
            final int int4 = Integer.parseInt(array2[1]);
            final float n4 = 80 / int3 / int4;
            for (int k = 1; k <= int4; ++k) {
                final String string = "attempt" + k;
                final String[] selectLowMarks1 = DataBaseLayer.selectLowMarks1(s, array2[2], string);
                final String[] selectHighMarks1 = DataBaseLayer.selectHighMarks1(s, array2[2], string);
                final String[] selectAvgMarks1 = DataBaseLayer.selectAvgMarks1(s, array2[2], string);
                if (selectLowMarks1[0] == null) {
                    selectLowMarks1[0] = "--";
                }
                else {
                    vector.addElement(selectLowMarks1[0]);
                }
                if (selectHighMarks1[0] == null) {
                    selectHighMarks1[0] = "--";
                }
                else {
                    vector2.addElement(selectHighMarks1[0]);
                }
                if (selectAvgMarks1[0] == null) {
                    selectAvgMarks1[0] = "--";
                }
                else {
                    vector3.addElement(selectAvgMarks1[0]);
                }
                final Element element18 = document.createElement("td");
                element.appendChild(element18);
                element18.setAttribute("width", n4 + "%");
                element18.setAttribute("align", "center");
                element18.setAttribute("class", "swb");
                element18.appendChild(document.createTextNode("" + k));
                element3.appendChild(element18);
                final Element element19 = document.createElement("td");
                element.appendChild(element19);
                element19.setAttribute("width", n4 + "%");
                element19.setAttribute("align", "center");
                element19.setAttribute("class", "swb");
                element19.appendChild(document.createTextNode(selectLowMarks1[0]));
                element4.appendChild(element19);
                final Element element20 = document.createElement("td");
                element.appendChild(element20);
                element20.setAttribute("width", n4 + "%");
                element20.setAttribute("align", "center");
                element20.setAttribute("class", "swb");
                element20.appendChild(document.createTextNode(selectHighMarks1[0]));
                element5.appendChild(element20);
                final Element element21 = document.createElement("td");
                element.appendChild(element21);
                element21.setAttribute("width", n4 + "%");
                element21.setAttribute("align", "center");
                element21.setAttribute("class", "swb");
                element21.appendChild(document.createTextNode(selectAvgMarks1[0]));
                element6.appendChild(element21);
                final Element element22 = document.createElement("td");
                element.appendChild(element22);
                element22.setAttribute("width", n4 + "%");
                element22.setAttribute("align", "center");
                element22.setAttribute("class", "swb");
                element22.appendChild(document.createTextNode(selectFullMarks1[0]));
                element7.appendChild(element22);
                final Element element23 = document.createElement("td");
                element.appendChild(element23);
                element23.setAttribute("width", n4 + "%");
                element23.setAttribute("align", "center");
                element23.setAttribute("class", "swb");
                element23.appendChild(document.createTextNode(selectPassMarks1[0]));
                element8.appendChild(element23);
            }
        }
        if (vector.size() != 0) {
            int1 = Integer.parseInt("" + (Object)vector.elementAt(0));
            for (int l = 1; l < vector.size(); ++l) {
                final int int5 = Integer.parseInt("" + (Object)vector.elementAt(l));
                if (int5 < int1) {
                    int1 = int5;
                }
            }
        }
        if (vector2.size() != 0) {
            int2 = Integer.parseInt("" + (Object)vector2.elementAt(0));
            for (int n5 = 1; n5 < vector2.size(); ++n5) {
                final int int6 = Integer.parseInt("" + (Object)vector2.elementAt(n5));
                if (int6 > int2) {
                    int2 = int6;
                }
            }
        }
        if (vector3.size() != 0) {
            int n6 = 0;
            for (int n7 = 0; n7 < vector3.size(); ++n7) {
                n += Integer.parseInt("" + (Object)vector2.elementAt(n7));
                ++n6;
            }
            n /= n6;
        }
        if (vector4.size() != 0) {
            for (int n8 = 0; n8 < vector4.size(); ++n8) {
                n2 += Integer.parseInt("" + (Object)vector4.elementAt(n8));
            }
        }
        if (vector5.size() != 0) {
            for (int n9 = 0; n9 < vector5.size(); ++n9) {
                n3 += Integer.parseInt("" + (Object)vector5.elementAt(n9));
            }
        }
        final Element element24 = document.createElement("td");
        element.appendChild(element24);
        element24.setAttribute("width", "10%");
        element24.setAttribute("align", "center");
        element24.setAttribute("class", "swb");
        element24.appendChild(document.createTextNode("\t"));
        element3.appendChild(element24);
        final Element element25 = document.createElement("td");
        element.appendChild(element25);
        element25.setAttribute("width", "10%");
        element25.setAttribute("align", "center");
        element25.setAttribute("class", "swb");
        element25.appendChild(document.createTextNode("" + int1));
        element4.appendChild(element25);
        final Element element26 = document.createElement("td");
        element.appendChild(element26);
        element26.setAttribute("width", "10%");
        element26.setAttribute("align", "center");
        element26.setAttribute("class", "swb");
        element26.appendChild(document.createTextNode("" + int2));
        element5.appendChild(element26);
        final Element element27 = document.createElement("td");
        element.appendChild(element27);
        element27.setAttribute("width", "10%");
        element27.setAttribute("align", "center");
        element27.setAttribute("class", "swb");
        element27.appendChild(document.createTextNode("" + n));
        element6.appendChild(element27);
        final Element element28 = document.createElement("td");
        element.appendChild(element28);
        element28.setAttribute("width", "10%");
        element28.setAttribute("align", "center");
        element28.setAttribute("class", "swb");
        element28.appendChild(document.createTextNode("" + n2));
        element7.appendChild(element28);
        final Element element29 = document.createElement("td");
        element.appendChild(element29);
        element29.setAttribute("width", "10%");
        element29.setAttribute("align", "center");
        element29.setAttribute("class", "swb");
        element29.appendChild(document.createTextNode("" + n3));
        element8.appendChild(element29);
        try {
            html = getHTML(document);
        }
        catch (Exception ex2) {}
        cms.log.debug("------>>>>----- " + html);
        return html;
    }
    
    public static final String getHTML(final Document document) throws Exception {
        try {
            final OutputFormat outputFormat = new OutputFormat(document);
            final StringWriter stringWriter = new StringWriter();
            final XMLSerializer xmlSerializer = new XMLSerializer((Writer)stringWriter, outputFormat);
            xmlSerializer.asDOMSerializer();
            xmlSerializer.serialize(document.getDocumentElement());
            return stringWriter.toString();
        }
        catch (Exception ex) {
            throw new Exception("XML to String Err: " + ex);
        }
    }
    
    public String studentGradeInfo(final String s) {
        //final String s2 = (String)WebContextFactory.get().getSession().getAttribute("user_id");
        if (s.equals("0") || s.equals("") || s == null) {
            return "<table></table>";
        }
        String s3 = "<table border='1' cellpadding='0' cellspacing='0'>";
        //final Vector vector = new Vector(10, 10);
        //final Vector vector2 = new Vector(10, 10);
        final Vector<String[]> selectItemInformationCidAdmin = DataBaseLayer.selectItemInformationCidAdmin(s);
        final String[] selectColumnCidAdmin = DataBaseLayer.selectColumnCidAdmin(s);
        int n = 0;
        int int1;
        if (selectColumnCidAdmin[0] != null) {
            int1 = Integer.parseInt(selectColumnCidAdmin[0]);
        }
        else {
            int1 = 0;
        }
        if (selectItemInformationCidAdmin.size() != 0) {
            final Vector<String> selectStudentInformation = DataBaseLayer.selectStudentInformation(s);
            for (int i = 0; i < selectStudentInformation.size(); ++i) {
                double n2 = 0.0;
                double n3 = 0.0;
                int int2 = 0;
                int int3 = 0;
                String s4 = s3 + "<tr bgcolor='#6AC9FF'><td width='10%' class='swb'><font color='#483D8B' size='2'>" + DataBaseLayer.selectStuName(selectStudentInformation.elementAt(i)) + "</font></td>";
                for (int j = 0; j < selectItemInformationCidAdmin.size(); ++j) {
                    final String[] array = selectItemInformationCidAdmin.elementAt(j);
                    System.out.println("temp1[" + j + "]" + array[j]);
                    final int int4 = Integer.parseInt(array[1]);
                    final float n4 = 80 / int1 / int4;
                    System.out.println("temp1[2] =" + array[2]);
                    final String marksScheme = DataBaseLayer.getMarksScheme(array[2]);
                    System.out.println("marks_scheme =" + marksScheme);
                    int n5 = 0;
                    int n6 = 0;
                    for (int k = 1; k <= int4; ++k) {
                        final String string = "attempt" + k;
                        String string2 = "";
                        final String[] selectMarks1 = DataBaseLayer.selectMarks1(selectStudentInformation.elementAt(i), s, array[2], string);
                        System.out.println("marks =" + selectMarks1);
                        if (selectMarks1[0] != null) {
                            final int n7 = Integer.parseInt(selectMarks1[0]) * 100 / Integer.parseInt(DataBaseLayer.selectFullMarks1(array[2])[0]);
                            final Vector<String> selectGradeInformation = DataBaseLayer.selectGradeInformation(array[2]);
                            if (selectGradeInformation.size() != 0) {
                                for (int l = 0; l < selectGradeInformation.size(); l += 3) {
                                    final int int5 = Integer.parseInt(selectGradeInformation.elementAt(l + 1));
                                    final int int6 = Integer.parseInt(selectGradeInformation.elementAt(l + 2));
                                    if (n7 >= int5 && n7 <= int6) {
                                        string2 = "" + selectGradeInformation.elementAt(l);
                                    }
                                }
                            }
                        }
                        if (selectMarks1[0] == null) {
                            selectMarks1[0] = "";
                        }
                        else {
                            final String[] selectWt1 = DataBaseLayer.selectWt1(array[2]);
                            final String[] selectFullMarks1 = DataBaseLayer.selectFullMarks1(array[2]);
                            final int int7 = Integer.parseInt(selectMarks1[0]);
                            int2 = Integer.parseInt(selectFullMarks1[0]);
                            int3 = Integer.parseInt(selectWt1[0]);
                            System.out.println("a ============= " + int7);
                            if (marksScheme.equals("Best Of")) {
                                if (n < int7 && int4 > 1) {
                                    n = int7;
                                }
                                else if (int4 == 1) {
                                    n = int7;
                                }
                            }
                            else if (marksScheme.equals("Average")) {
                                System.out.println("count0 =" + n6);
                                if (int7 > 0) {
                                    ++n6;
                                    System.out.println("count1 =" + n6);
                                    n5 += int7;
                                }
                            }
                        }
                        s4 = s4 + "<td width='" + n4 + "%' align='center' class='swb'><font color='#FFFFFF' size='2'><b>" + selectMarks1[0] + "/" + string2 + "</b></font></td>";
                    }
                    System.out.println("count2 ====" + n6);
                    if (n6 > 0) {
                        n = n5 / n6;
                        System.out.println("count3 ====" + n6);
                    }
                    System.out.println("indv_marks =" + n);
                    final double n8 = n * int3;
                    System.out.println("d1 ===" + n8);
                    final double n9 = n8 / int2;
                    System.out.println("g1 ===" + n9);
                    final double n10 = int2 * int3;
                    System.out.println("f1 ===" + n10);
                    System.out.println("ff1 ===" + n10 / 100.0);
                    n2 += n9;
                    System.out.println("e1 ===" + n2);
                    n3 += int3;
                    System.out.println("fullweighted ===" + n3);
                    n = 0;
                }
                if (DataBaseLayer.allAttempted(selectStudentInformation.elementAt(i), s)) {
                    System.out.println("e1 = " + n2);
                    final Double value = n2 * 100.0 / n3;
                    cms.log.debug("---->>>>----overallmarks-- " + value);
                    s3 = s4 + "<td width='10%'><font color='#FFFFFF' size='2'>" + value + "/" + DataBaseLayer.getOverallGrade(value, s) + "</font></td></tr><tr>";
                }
                else {
                    s3 = s4 + "<td width='10%' class='swb'><font color='#FFFFFF'' size='2'>Incomplete</font></td></tr><tr>";
                }
            }
            s3 += "</table>";
        }
        cms.log.debug("---->>>>---- studentGradeInfo()--" + s3);
        return s3;
    }
    
    public String getMaterialTypeForSchedule(final int n, final int n2) {
        cms.log.debug("===========INSIDE getMatType===========");
        final String materialTypeFromDBforSchedule = DataBaseLayer.getMaterialTypeFromDBforSchedule(n, n2);
        cms.log.debug("+++++++mType++++++" + materialTypeFromDBforSchedule);
        return materialTypeFromDBforSchedule;
    }
    
    public String insertURLforSchedule(final String s, final int n, final int n2) {
        cms.log.debug("INSIDE insertURLforResource------------------->");
        String s2 = "";
        if (DataBaseLayer.updateMaterialNametoDBForSchedule(s, n, n2, "")) {
            s2 = "Successfully Added";
        }
        return s2;
    }
    
    public String AttachFileforSchedule(final int n, final FileTransfer fileTransfer, final int n2) {
        String s = "";
        cms.log.debug("===============topic_id=============" + (String)WebContextFactory.get().getSession().getAttribute("top_id"));
        final String serverCoursePath = Host.getServerCoursePath();
        cms.log.debug("===============strLocation=========" + serverCoursePath);
        String string = serverCoursePath;
        final String[] array = { "schedule", Integer.toString(n2), Integer.toString(n) };
        cms.log.debug("dir name is:" + string);
        for (int i = 0; i < 3; ++i) {
            if (string == null) {}
            string = string + File.separator + array[i];
            final File file = new File(string);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        //final FileUploaderPojo fileUploaderPojo = new FileUploaderPojo(fileTransfer);
        cms.log.debug("==============MimeType==========" + FileUploaderPojo.getMimeType());
        final InputStream inputStream = FileUploaderPojo.getInputStream();
        cms.log.debug("==============is=============" + inputStream);
        final String filename = FileUploaderPojo.getFilename();
        cms.log.debug("==============filename=============" + filename);
        final String string2 = string + File.separator + filename;
        final String s2 = "";
        cms.log.debug("==============dirName==========" + string2);
        try {
            cms.log.debug("dirName===" + string2);
            final FileOutputStream fileOutputStream = new FileOutputStream(string2);
            final byte[] array2 = new byte[1024];
            try {
                int read;
                while (inputStream != null && (read = inputStream.read(array2)) != -1) {
                    cms.log.debug("=======test1======");
                    fileOutputStream.write(array2, 0, read);
                }
            }
            catch (IOException ex2) {}
            cms.log.debug("=======test2======");
            final PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(s2);
            printStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        final File file2 = new File(string2);
        file2.length();
        final int n3 = (int)file2.length();
        System.out.println("================size1===========in add schedule===" + n3);
        if (DataBaseLayer.updateMaterialNametoDBForSchedule(filename, n, n2, Integer.toString(n3))) {
            s = "Successfully Added";
        }
        return s;
    }
    
    public String insertLOforSchedule(final String s, final int n, final int n2) {
        cms.log.debug("INSIDE insertLOforResource------------------->");
        String s2 = "";
        if (DataBaseLayer.updateMaterialNametoDBForSchedule(s, n, n2, "")) {
            s2 = "Successfully Added";
        }
        return s2;
    }
    
    public String returnCourseDesc() throws ServletException, IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        return DataBaseLayer.returncoursedesc((String)session.getAttribute("course_id"));
    }
    
    public String[] CourseDefinitionDetails() {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        return DataBaseLayer.CourseDefinitionDetails((String)session.getAttribute("course_id"));
    }
    
    public void UpdateCourseDetailsByTeacher(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10) throws ServletException, IOException {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        new StringBuilder().append(gregorianCalendar.get(11)).append(":").append(gregorianCalendar.get(12)).append(":").append(gregorianCalendar.get(13)).toString();
        new StringBuilder().append(gregorianCalendar.get(1)).append("-").append(gregorianCalendar.get(2) + 1).append("-").append(gregorianCalendar.get(5)).toString();
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s11 = (String)session.getAttribute("student_id");
        DataBaseLayer.UpdateCourseDetailsByTeacher((String)session.getAttribute("course_id"), s, s2, s3, s4, s5, s6, s7, s8, s9, s10);
    }
    
    public String AssignmentDocumentTeacherUpload(final String s, final FileTransfer fileTransfer) {
        String s2 = "";
        //final String s3 = (String)WebContextFactory.get().getSession().getAttribute("user_id");
        //final String s4 = (s3 == null) ? "" : s3;
        if (!DataBaseLayer.IsDocIdExists(s)) {
            DataBaseLayer.insertDocumenttoAssignment(s);
            s2 = "Document Successfully Inserted";
        }
        final String documentIdFromAssignment = DataBaseLayer.getDocumentIdFromAssignment(s);
        final String serverCoursePath = Host.getServerCoursePath();
        final String[] array = { "assignment", documentIdFromAssignment };
        String string = serverCoursePath;
        for (int i = 0; i < 2; ++i) {
            string = string + File.separator + array[i];
            final File file = new File(string);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        //final FileUploaderPojo fileUploaderPojo = new FileUploaderPojo(fileTransfer);
        cms.log.debug("MimeType======:" + FileUploaderPojo.getMimeType());
        final InputStream inputStream = FileUploaderPojo.getInputStream();
        final String filename = FileUploaderPojo.getFilename();
        System.out.println("=================filename===========" + filename);
        final String string2 = string + File.separator + filename;
        try {
            cms.log.debug("dirName===" + string2);
            final FileOutputStream fileOutputStream = new FileOutputStream(string2);
            final byte[] array2 = new byte[1024];
            int read;
            while (inputStream != null && (read = inputStream.read(array2)) != -1) {
                fileOutputStream.write(array2, 0, read);
            }
            new PrintStream(fileOutputStream).close();
        }
        catch (IOException ex2) {}
        catch (Exception ex) {
            System.err.println("=========error=======" + ex.toString());
        }
        DataBaseLayer.updateAssignmentDocument(s, documentIdFromAssignment, Long.valueOf(new File(string2).length()).toString(), filename);
        if (s2.equals("")) {
            s2 = "Document Successfully Updated";
        }
        return s2;
    }
    
    public FileTransfer DownloadDocumentForTeacher(final String s, final String s2, final String s3) throws Exception {
    	//final String s4 = (String)WebContextFactory.get().getSession().getAttribute("course_id");
        final String docFileName = DataBaseLayer.getDocFileName(s, s3);
        cms.log.debug("==================filename=== in Download=========" + docFileName);
        final String serverCoursePath = Host.getServerCoursePath();
        cms.log.debug("===============strLocation=========" + serverCoursePath);
        String string = serverCoursePath;
        final String[] array = { "assignment", "submitted", s, s2 };
        cms.log.debug("dir name is:" + string);
        for (int i = 0; i < 4; ++i) {
            string = string + File.separator + array[i];
        }
        final String string2 = string + File.separator + docFileName;
        System.out.println(string2);
        //final FileDownloaderPojo fileDownloaderPojo = new FileDownloaderPojo((InputStream)new FileInputStream(string2), "", docFileName);
        return FileDownloaderPojo.returnFileFormat();
    }
    
    public String ReturnDocumentByTeacher(final String s, final String s2, final String s3, final FileTransfer fileTransfer) {
        String s4 = "";
        final String s5 = (String)WebContextFactory.get().getSession().getAttribute("course_id");
        final String serverCoursePath = Host.getServerCoursePath();
        cms.log.debug("===============strLocation=========" + serverCoursePath);
        String string = serverCoursePath;
        final String[] array = { "assignment", "feedback", s, s2 };
        cms.log.debug("dir name is:" + string);
        for (int i = 0; i < 4; ++i) {
            if (string == null) {
                s4 = "Please supply uploadDir parameter";
            }
            string = string + File.separator + array[i];
            final File file = new File(string);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        //final FileUploaderPojo fileUploaderPojo = new FileUploaderPojo(fileTransfer);
        cms.log.debug("==============MimeType==========" + FileUploaderPojo.getMimeType());
        final InputStream inputStream = FileUploaderPojo.getInputStream();
        cms.log.debug("==============is=============" + inputStream);
        final String filename = FileUploaderPojo.getFilename();
        cms.log.debug("==============filename=============" + filename);
        final String string2 = string + File.separator + filename;
        final String s6 = "";
        cms.log.debug("==============dirName==========" + string2);
        try {
            cms.log.debug("dirName===" + string2);
            final FileOutputStream fileOutputStream = new FileOutputStream(string2);
            final byte[] array2 = new byte[1024];
            try {
                int read;
                while (inputStream != null && (read = inputStream.read(array2)) != -1) {
                    cms.log.debug("=======test1======");
                    fileOutputStream.write(array2, 0, read);
                }
            }
            catch (IOException ex2) {}
            cms.log.debug("=======test2======");
            final PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(s6);
            printStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        DataBaseLayer.updateReturnDoc(s5, s, s2, filename);
        return s4;
    }
    
    public static void LockSubmittedAssignmentByTeacher(final String s, final String s2) {
        DataBaseLayer.LockSubmittedAssignment((String)WebContextFactory.get().getSession().getAttribute("course_id"), s, s2);
    }
    
    public static void setAssignmentGradedByTeacher(final String s, final String s2) {
        DataBaseLayer.setAssignmentGraded((String)WebContextFactory.get().getSession().getAttribute("course_id"), s, s2);
    }
    
    public static String getFeedbackForTeacher(final String s, final String s2) {
        String feedback = DataBaseLayer.getFeedback((String)WebContextFactory.get().getSession().getAttribute("course_id"), s, s2);
        cms.log.debug("===========feedback====" + feedback);
        if (feedback == null) {
            feedback = "";
        }
        System.out.println("===========feedback====" + feedback);
        return feedback;
    }
    
    public static void updateAssignmentFeedbackByTeacher(final String s, final String s2, final String s3) {
        DataBaseLayer.updateAssignmentFeedback((String)WebContextFactory.get().getSession().getAttribute("course_id"), s, s2, s3);
    }
    
    public static void setInstructorId(final String s) {
        WebContextFactory.get().getSession().setAttribute("InstructorId", (Object)s);
        System.out.println("InstructorId = " + s);
    }
    
    public String[] getInstructorInfo(final String s) {
    	//final String[] array = new String[15];
        final String[] instructorData = DataBaseLayer.getInstructorData((String)WebContextFactory.get().getSession().getAttribute("InstructorId"));
        System.out.println("----------------------------------------------");
        System.out.println("data--------" + instructorData[0]);
        System.out.println("data--------" + instructorData[1]);
        System.out.println("data--------" + instructorData[12]);
        return instructorData;
    }
    
    public String getUpdatedDropDown(final String s, final String s2, final String[] array) {
        String string = "";
        WebContextFactory.get().getSession();
        String s3 = DataBaseLayer.getDropdownSqlQuery(s, s2);
        for (int i = 0; i < array.length; i += 2) {
            s3 = s3.replace("%" + array[i] + "%", "'" + array[i + 1] + "'");
        }
        final Vector<String> returnpagedropdown = DataBaseLayer.returnpagedropdown(s3);
        for (int j = 0; j < returnpagedropdown.size(); j += 2) {
            string = string + "<option value=\"" + returnpagedropdown.elementAt(j) + "\">" + returnpagedropdown.elementAt(j + 1) + "</option>";
        }
        System.out.println("===================html================" + string);
        return string;
    }
    
    public String getClassInfoForPortal() {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        if (s2.equals("0") || s2.equals("") || s2 == null) {
            return "<table></table>";
        }
        final Vector<String> vector = new Vector<String>(10, 10);
        int int1 = 0;
        final Vector<String> vector2 = new Vector<String>(10, 10);
        int int2 = 0;
        final Vector<String> vector3 = new Vector<String>(10, 10);
        int n = 0;
        final Vector<String> vector4 = new Vector<String>(10, 10);
        int n2 = 0;
        final Vector<String> vector5 = new Vector<String>(10, 10);
        int n3 = 0;
        final Vector<String[]> selectItemInformationCidAdmin = DataBaseLayer.selectItemInformationCidAdmin(s2);
        final String[] selectColumnCidAdmin = DataBaseLayer.selectColumnCidAdmin(s2);
        DataBaseLayer.getPriviledge(s2);
        Document document = null;
        final DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
        try {
            instance.setValidating(true);
            document = instance.newDocumentBuilder().newDocument();
        }
        catch (ParserConfigurationException ex) {}
        final Element element = document.createElement("table");
        document.appendChild(element);
        element.setAttribute("border", "1");
        element.setAttribute("cellpadding", "0");
        element.setAttribute("cellspacing", "0");
        String html = "";
        int int3;
        if (selectColumnCidAdmin[0] != null) {
            int3 = Integer.parseInt(selectColumnCidAdmin[0]);
        }
        else {
            int3 = 1;
        }
        final Element element2 = document.createElement("tr");
        element.appendChild(element2);
        element2.setAttribute("bgcolor", "#40A0D0");
        element2.setAttribute("color", "#FFFFFF");
        final Element element3 = document.createElement("tr");
        element.appendChild(element3);
        element3.setAttribute("bgcolor", "#6AC9FF");
        element3.setAttribute("color", "#FFFFFF");
        final Element element4 = document.createElement("tr");
        element.appendChild(element4);
        element4.setAttribute("bgcolor", "#6AC9FF");
        element4.setAttribute("color", "#FFFFFF");
        final Element element5 = document.createElement("tr");
        element.appendChild(element5);
        element5.setAttribute("bgcolor", "#6AC9FF");
        element5.setAttribute("color", "#FFFFFF");
        final Element element6 = document.createElement("tr");
        element.appendChild(element6);
        element6.setAttribute("bgcolor", "#6AC9FF");
        element6.setAttribute("color", "#FFFFFF");
        final Element element7 = document.createElement("tr");
        element.appendChild(element7);
        element7.setAttribute("bgcolor", "#6AC9FF");
        element7.setAttribute("color", "#FFFFFF");
        final Element element8 = document.createElement("tr");
        element.appendChild(element8);
        element8.setAttribute("bgcolor", "#6AC9FF");
        element8.setAttribute("color", "#FFFFFF");
        cms.log.debug("---->>>>----itemtab.size()-- " + selectItemInformationCidAdmin.size());
        if (selectItemInformationCidAdmin.size() != 0) {
            final Element element9 = document.createElement("td");
            element9.setAttribute("class", "swb");
            element2.appendChild(element9);
            element9.appendChild(document.createTextNode("\t"));
            for (int i = 0; i < selectItemInformationCidAdmin.size(); ++i) {
                final Element element10 = document.createElement("td");
                element10.setAttribute("class", "swb");
                element2.appendChild(element10);
                element10.setAttribute("width", "" + 80 / int3 + "%");
                final String[] array = selectItemInformationCidAdmin.elementAt(i);
                cms.log.debug("---->>>>----items()-- " + array[0]);
                element10.setAttribute("colspan", array[1]);
                element10.appendChild(document.createTextNode(array[0]));
            }
        }
        final Element element11 = document.createElement("td");
        element11.setAttribute("width", "10%");
        element11.setAttribute("class", "swb");
        element2.appendChild(element11);
        element11.appendChild(document.createTextNode("Overall"));
        final Element element12 = document.createElement("td");
        element12.setAttribute("width", "10%");
        element12.setAttribute("class", "swb");
        element3.appendChild(element12);
        element12.appendChild(document.createTextNode("Attempt"));
        final Element element13 = document.createElement("td");
        element13.setAttribute("width", "10%");
        element13.setAttribute("class", "swb");
        element4.appendChild(element13);
        element13.appendChild(document.createTextNode("Lowest"));
        final Element element14 = document.createElement("td");
        element14.setAttribute("width", "10%");
        element14.setAttribute("class", "swb");
        element5.appendChild(element14);
        element14.appendChild(document.createTextNode("Highest"));
        final Element element15 = document.createElement("td");
        element15.setAttribute("width", "10%");
        element15.setAttribute("class", "swb");
        element6.appendChild(element15);
        element15.appendChild(document.createTextNode("Average"));
        final Element element16 = document.createElement("td");
        element16.setAttribute("width", "10%");
        element16.setAttribute("class", "swb");
        element7.appendChild(element16);
        element16.appendChild(document.createTextNode("Full Marks"));
        final Element element17 = document.createElement("td");
        element17.setAttribute("width", "10%");
        element17.setAttribute("class", "swb");
        element8.appendChild(element17);
        element17.appendChild(document.createTextNode("Pass Marks"));
        for (int j = 0; j < selectItemInformationCidAdmin.size(); ++j) {
            final String[] array2 = selectItemInformationCidAdmin.elementAt(j);
            final String[] selectFullMarks1 = DataBaseLayer.selectFullMarks1(array2[2]);
            final String[] selectPassMarks1 = DataBaseLayer.selectPassMarks1(array2[2]);
            if (selectFullMarks1[0] == null) {
                selectFullMarks1[0] = "--";
            }
            else {
                vector4.addElement(selectFullMarks1[0]);
            }
            if (selectPassMarks1[0] == null) {
                selectPassMarks1[0] = "--";
            }
            else {
                vector5.addElement(selectPassMarks1[0]);
            }
            final int int4 = Integer.parseInt(array2[1]);
            final float n4 = 80 / int3 / int4;
            for (int k = 1; k <= int4; ++k) {
                final String string = "attempt" + k;
                final String[] selectLowMarks1 = DataBaseLayer.selectLowMarks1(s2, array2[2], string);
                final String[] selectHighMarks1 = DataBaseLayer.selectHighMarks1(s2, array2[2], string);
                final String[] selectAvgMarks1 = DataBaseLayer.selectAvgMarks1(s2, array2[2], string);
                if (selectLowMarks1[0] == null) {
                    selectLowMarks1[0] = "--";
                }
                else {
                    vector.addElement(selectLowMarks1[0]);
                }
                if (selectHighMarks1[0] == null) {
                    selectHighMarks1[0] = "--";
                }
                else {
                    vector2.addElement(selectHighMarks1[0]);
                }
                if (selectAvgMarks1[0] == null) {
                    selectAvgMarks1[0] = "--";
                }
                else {
                    vector3.addElement(selectAvgMarks1[0]);
                }
                final Element element18 = document.createElement("td");
                element.appendChild(element18);
                element18.setAttribute("width", n4 + "%");
                element18.setAttribute("align", "center");
                element18.setAttribute("class", "swb");
                element18.appendChild(document.createTextNode("" + k));
                element3.appendChild(element18);
                final Element element19 = document.createElement("td");
                element.appendChild(element19);
                element19.setAttribute("width", n4 + "%");
                element19.setAttribute("align", "center");
                element19.setAttribute("class", "swb");
                element19.appendChild(document.createTextNode(selectLowMarks1[0]));
                element4.appendChild(element19);
                final Element element20 = document.createElement("td");
                element.appendChild(element20);
                element20.setAttribute("width", n4 + "%");
                element20.setAttribute("align", "center");
                element20.setAttribute("class", "swb");
                element20.appendChild(document.createTextNode(selectHighMarks1[0]));
                element5.appendChild(element20);
                final Element element21 = document.createElement("td");
                element.appendChild(element21);
                element21.setAttribute("width", n4 + "%");
                element21.setAttribute("align", "center");
                element21.setAttribute("class", "swb");
                element21.appendChild(document.createTextNode(selectAvgMarks1[0]));
                element6.appendChild(element21);
                final Element element22 = document.createElement("td");
                element.appendChild(element22);
                element22.setAttribute("width", n4 + "%");
                element22.setAttribute("align", "center");
                element22.setAttribute("class", "swb");
                element22.appendChild(document.createTextNode(selectFullMarks1[0]));
                element7.appendChild(element22);
                final Element element23 = document.createElement("td");
                element.appendChild(element23);
                element23.setAttribute("width", n4 + "%");
                element23.setAttribute("align", "center");
                element23.setAttribute("class", "swb");
                element23.appendChild(document.createTextNode(selectPassMarks1[0]));
                element8.appendChild(element23);
            }
        }
        if (vector.size() != 0) {
            int1 = Integer.parseInt("" + (Object)vector.elementAt(0));
            for (int l = 1; l < vector.size(); ++l) {
                final int int5 = Integer.parseInt("" + (Object)vector.elementAt(l));
                if (int5 < int1) {
                    int1 = int5;
                }
            }
        }
        if (vector2.size() != 0) {
            int2 = Integer.parseInt("" + (Object)vector2.elementAt(0));
            for (int n5 = 1; n5 < vector2.size(); ++n5) {
                final int int6 = Integer.parseInt("" + (Object)vector2.elementAt(n5));
                if (int6 > int2) {
                    int2 = int6;
                }
            }
        }
        if (vector3.size() != 0) {
            int n6 = 0;
            for (int n7 = 0; n7 < vector3.size(); ++n7) {
                n += Integer.parseInt("" + (Object)vector2.elementAt(n7));
                ++n6;
            }
            n /= n6;
        }
        if (vector4.size() != 0) {
            for (int n8 = 0; n8 < vector4.size(); ++n8) {
                n2 += Integer.parseInt("" + (Object)vector4.elementAt(n8));
            }
        }
        if (vector5.size() != 0) {
            for (int n9 = 0; n9 < vector5.size(); ++n9) {
                n3 += Integer.parseInt("" + (Object)vector5.elementAt(n9));
            }
        }
        final Element element24 = document.createElement("td");
        element.appendChild(element24);
        element24.setAttribute("width", "10%");
        element24.setAttribute("align", "center");
        element24.setAttribute("class", "swb");
        element24.appendChild(document.createTextNode("\t"));
        element3.appendChild(element24);
        final Element element25 = document.createElement("td");
        element.appendChild(element25);
        element25.setAttribute("width", "10%");
        element25.setAttribute("align", "center");
        element25.setAttribute("class", "swb");
        element25.appendChild(document.createTextNode("" + int1));
        element4.appendChild(element25);
        final Element element26 = document.createElement("td");
        element.appendChild(element26);
        element26.setAttribute("width", "10%");
        element26.setAttribute("align", "center");
        element26.setAttribute("class", "swb");
        element26.appendChild(document.createTextNode("" + int2));
        element5.appendChild(element26);
        final Element element27 = document.createElement("td");
        element.appendChild(element27);
        element27.setAttribute("width", "10%");
        element27.setAttribute("align", "center");
        element27.setAttribute("class", "swb");
        element27.appendChild(document.createTextNode("" + n));
        element6.appendChild(element27);
        final Element element28 = document.createElement("td");
        element.appendChild(element28);
        element28.setAttribute("width", "10%");
        element28.setAttribute("align", "center");
        element28.setAttribute("class", "swb");
        element28.appendChild(document.createTextNode("" + n2));
        element7.appendChild(element28);
        final Element element29 = document.createElement("td");
        element.appendChild(element29);
        element29.setAttribute("width", "10%");
        element29.setAttribute("align", "center");
        element29.setAttribute("class", "swb");
        element29.appendChild(document.createTextNode("" + n3));
        element8.appendChild(element29);
        try {
            html = getHTML(document);
        }
        catch (Exception ex2) {}
        cms.log.debug("------>>>>----- " + html);
        return html;
    }
    
    public String studentGradeInfoForPortal() {
        final HttpSession session = WebContextFactory.get().getSession();
       // final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        if (s2.equals("0") || s2.equals("") || s2 == null) {
            return "<table></table>";
        }
        String s3 = "<table border='1' cellpadding='0' cellspacing='0'>";
        //final Vector vector = new Vector(10, 10);
        //final Vector vector2 = new Vector(10, 10);
        final Vector<String[]> selectItemInformationCidAdmin = DataBaseLayer.selectItemInformationCidAdmin(s2);
        final String[] selectColumnCidAdmin = DataBaseLayer.selectColumnCidAdmin(s2);
        double n = 0.0;
        int int1;
        if (selectColumnCidAdmin[0] != null) {
            int1 = Integer.parseInt(selectColumnCidAdmin[0]);
        }
        else {
            int1 = 0;
        }
        if (selectItemInformationCidAdmin.size() != 0) {
            final Vector<String> selectStudentInformation = DataBaseLayer.selectStudentInformation(s2);
            for (int i = 0; i < selectStudentInformation.size(); ++i) {
                double n2 = 0.0;
                double n3 = 0.0;
                int int2 = 0;
                int int3 = 0;
                String s4 = s3 + "<tr bgcolor='#6AC9FF'><td width='10%' class='swb'><font color='#483D8B' size='2'>" + DataBaseLayer.selectStuName(selectStudentInformation.elementAt(i)) + "</font></td>";
                for (int j = 0; j < selectItemInformationCidAdmin.size(); ++j) {
                    final String[] array = selectItemInformationCidAdmin.elementAt(j);
                    System.out.println("temp1[" + j + "]" + array[j]);
                    final int int4 = Integer.parseInt(array[1]);
                    final float n4 = 80 / int1 / int4;
                    System.out.println("temp1[2] =" + array[2]);
                    final String marksScheme = DataBaseLayer.getMarksScheme(array[2]);
                    System.out.println("marks_scheme =" + marksScheme);
                    double n5 = 0.0;
                    int n6 = 0;
                    for (int k = 1; k <= int4; ++k) {
                        final String string = "attempt" + k;
                        String string2 = "";
                        final String[] selectMarks1 = DataBaseLayer.selectMarks1(selectStudentInformation.elementAt(i), s2, array[2], string);
                        System.out.println("marks =" + selectMarks1);
                        if (selectMarks1[0] != null) {
                            final int n7 = Integer.parseInt(selectMarks1[0]) * 100 / Integer.parseInt(DataBaseLayer.selectFullMarks1(array[2])[0]);
                            final Vector<String> selectGradeInformation = DataBaseLayer.selectGradeInformation(array[2]);
                            if (selectGradeInformation.size() != 0) {
                                for (int l = 0; l < selectGradeInformation.size(); l += 3) {
                                    final int int5 = Integer.parseInt(selectGradeInformation.elementAt(l + 1));
                                    final int int6 = Integer.parseInt(selectGradeInformation.elementAt(l + 2));
                                    if (n7 >= int5 && n7 <= int6) {
                                        string2 = "" + selectGradeInformation.elementAt(l);
                                    }
                                }
                            }
                        }
                        if (selectMarks1[0] == null) {
                            selectMarks1[0] = "";
                        }
                        else {
                            final String[] selectWt1 = DataBaseLayer.selectWt1(array[2]);
                            final String[] selectFullMarks1 = DataBaseLayer.selectFullMarks1(array[2]);
                            final int int7 = Integer.parseInt(selectMarks1[0]);
                            int2 = Integer.parseInt(selectFullMarks1[0]);
                            int3 = Integer.parseInt(selectWt1[0]);
                            System.out.println("a ============= " + int7);
                            if (marksScheme.equals("Best Of")) {
                                if (n < int7 && int4 > 1) {
                                    n = int7;
                                }
                                else if (int4 == 1) {
                                    n = int7;
                                }
                            }
                            else if (marksScheme.equals("Average")) {
                                System.out.println("count0 =" + n6);
                                if (int7 > 0) {
                                    ++n6;
                                    System.out.println("count1 =" + n6);
                                    n5 += int7;
                                    System.out.println("=========Total_Marks======1===" + n5);
                                }
                            }
                        }
                        s4 = s4 + "<td width='" + n4 + "%' align='center' class='swb'><font color='#FFFFFF' size='2'><b>" + selectMarks1[0] + "/" + string2 + "</b></font></td>";
                    }
                    System.out.println("=========Total_Marks======2===" + n5);
                    System.out.println("count2 ====" + n6);
                    if (n6 > 0) {
                        n = n5 / n6;
                        System.out.println("count3 ====" + n6);
                    }
                    System.out.println("indv_marks =" + n);
                    final double n8 = n * int3;
                    System.out.println("d1 ===" + n8);
                    final double n9 = n8 / int2;
                    System.out.println("g1 ===" + n9);
                    final double n10 = int2 * int3;
                    System.out.println("f1 ===" + n10);
                    System.out.println("ff1 ===" + n10 / 100.0);
                    n2 += n9;
                    System.out.println("e1 ===" + n2);
                    n3 += int3;
                    System.out.println("fullweighted ===" + n3);
                    n = 0.0;
                }
                if (DataBaseLayer.allAttempted(selectStudentInformation.elementAt(i), s2)) {
                    System.out.println("e1 = " + n2);
                    final Double value = n2 * 100.0 / n3;
                    cms.log.debug("---->>>>----overallmarks-- " + value);
                    s3 = s4 + "<td width='10%'><font color='#FFFFFF' size='2'>" + value + "/" + DataBaseLayer.getOverallGrade(value, s2) + "</font></td></tr><tr>";
                }
                else {
                    s3 = s4 + "<td width='10%' class='swb'><font color='#FFFFFF'' size='2'>Incomplete</font></td></tr><tr>";
                }
            }
            s3 += "</table>";
        }
        cms.log.debug("---->>>>---- studentGradeInfo()--" + s3);
        return s3;
    }
    
    public String getDownloadSyllabusFileName() {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        final String[] syllabusFile = DataBaseLayer.getSyllabusFile(s2);
        if (syllabusFile[0] == null) {
            syllabusFile[0] = "";
        }
        System.out.println("---------  FILE NAME ----------->  " + syllabusFile[0]);
        final String string = Host.getServerCoursePathEngine() + "SyllabusAttachment/" + s2 + "/" + syllabusFile[0];
        System.out.println("---------------  File Path  -------> " + string);
        final String string2 = "<table><tr><td><a href=\"" + string + "\">" + syllabusFile[0] + "</a></td></tr></table>";
        System.out.println("==================html========in getDownloadSyllabusFileName=" + string2);
        return string2;
    }
    
    public String CheckAssignmentSubmissionLastDate() {
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        //final String s2 = (s == null) ? "" : s;
        cms.log.debug("----------STUDENT ID -----------> " + (String)session.getAttribute("user_id"));
        final String s3 = (String)session.getAttribute("course_id");
        cms.log.debug("----------COURSE ID -----------> " + s3);
        final String s4 = (String)session.getAttribute("assign_id");
        cms.log.debug("----------ASSIGNMENT ID -----------> " + s4);
        cms.log.debug("----------DOCUMENT ID -----------> " + (String)session.getAttribute("doc_id"));
        cms.log.debug("----------COURSE NAME -----------> " + DataBaseLayer.returncoursename(s3));
        String s5;
        if (DataBaseLayer.CheckAssignmentSubmissionLastDate(s4)) {
            s5 = "Valid";
        }
        else {
            s5 = "Date Over";
        }
        return s5;
    }
    
    public String CreateandUploadUnit(final String courseName, final int n, final FileTransfer fileTransfer) {
        final DebugSwitch debugSwitch = new DebugSwitch();
        final String s = (String)WebContextFactory.get().getSession().getAttribute("user_id");
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        final String unit = DataBaseLayer.createUnit(courseName, s, "TT");
        final String[] array = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        //final int n2 = gregorianCalendar.get(2) + 1;
        final String string = gregorianCalendar.get(10) + ":" + gregorianCalendar.get(12);
        final String string2 = array[gregorianCalendar.get(2)] + " " + gregorianCalendar.get(5) + ", " + gregorianCalendar.get(1);
        new StringBuilder().append(string2).append(" ").append(string).toString();
        //final String s2 = new String();
        try {
            final String serverDocumentPath;
            final String s3 = serverDocumentPath = Host.getServerDocumentPath();
            if (debugSwitch.ON) {
                System.out.println("---111--->>>>>>>-----dir name is : " + serverDocumentPath);
            }
            if (serverDocumentPath == null) {}
            final String string3 = s3 + unit;
            if (debugSwitch.ON) {
                System.out.println("---222--->>>>>>>-----dir name is : " + string3);
            }
            //final File file = new File(string3);
            final File file2 = new File(string3);
            if (!file2.exists()) {
                file2.mkdir();
            }
            //final Object o = new FileUploaderPojo(fileTransfer);
            FileUploaderPojo.getMimeType();
            final InputStream inputStream = FileUploaderPojo.getInputStream();
            final String string4 = string3 + File.separator + FileUploaderPojo.getFilename();
            final String s4 = "";
            try {
                final File file3 = new File(string4);
                if (!file3.exists()) {
                    file3.createNewFile();
                }
                if (debugSwitch.ON) {
                    System.out.println("---333--->>>>>>>-----zip path+file is : " + string4);
                }
                System.out.println("====================zip path+file is : " + string4);
                final FileOutputStream fileOutputStream = new FileOutputStream(string4);
                final byte[] array2 = new byte[1024];
                try {
                    int read;
                    while (inputStream != null && (read = inputStream.read(array2)) != -1) {
                        fileOutputStream.write(array2, 0, read);
                    }
                    final PrintStream printStream = new PrintStream(fileOutputStream);
                    printStream.println(s4);
                    printStream.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                    if (debugSwitch.ON) {
                        System.out.println("--------->>>>>>>-----IOException : " + ex);
                    }
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                if (debugSwitch.ON) {
                    System.out.println("--------->>>>>>>-----Exception : " + ex2);
                }
            }
            //final LMSPackageHandler lmsPackageHandler = new LMSPackageHandler();
            if (LMSPackageHandler.findManifest(string4)) {
                if (debugSwitch.ON) {
                    System.out.println("-------->>>>>>>----- goes inside findManifest() path " + string4);
                }
                LMSPackageHandler.extract(string4, "imsmanifest.xml", string3);
                final String string5 = string3 + File.separatorChar + "imsmanifest.xml";
                DataBaseLayer.insertCourse("csformat", unit, string5, string2, s);
                if (debugSwitch.ON) {
                    System.out.println("-------->>>>>>>-----insertCourse() successful ");
                }
                final LMSManifestHandler lmsManifestHandler = new LMSManifestHandler();
                final InputSource setUpInputSource = this.setUpInputSource(string5);
                lmsManifestHandler.setCourseID(unit);
                lmsManifestHandler.setCourseName(courseName);
                lmsManifestHandler.setFileToParse(setUpInputSource);
                if (debugSwitch.ON) {
                    System.out.println("-------->>>>>>>-----inside processManifest() ... ");
                }
                lmsManifestHandler.processManifest();
                if (debugSwitch.ON) {
                    System.out.println("-------->>>>>>>-----processManifest() successful ");
                }
                try {
                    final String string6 = Host.getServerDocumentPath() + courseName + File.separatorChar + "sequence.obj";
                    if (debugSwitch.ON) {
                        System.out.println("-------->>>>>>>-----sequencingFileName : " + string6);
                    }
                    final File file4 = new File(string6);
                    final String parent = file4.getParent();
                    if (parent != null) {
                        final File file5 = new File(parent);
                        if (!file5.exists()) {
                            file5.mkdirs();
                        }
                    }
                    final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file4));
                    objectOutputStream.writeObject(lmsManifestHandler.getOrgsCopy());
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }
                catch (Exception ex3) {
                    cms.log.error("Error = " + ex3.toString());
                    ex3.printStackTrace();
                }
            }
            if (debugSwitch.ON) {
                System.out.println("----111.1---->>>>>>>-----get the zip file ... " + string4);
            }
            if (!this.getZipFiles(string4, courseName, unit).equals("")) {}
        }
        catch (Exception ex4) {
            ex4.toString();
            ex4.printStackTrace();
        }
        final String insertLO = this.insertLO(unit, n);
        this.InsertUserSCOInfo(unit);
        this.deleteDirectory(new File(Host.getServerDocumentPath() + unit + File.separatorChar + FileUploaderPojo.getFilename()));
        return insertLO;
    }
    
    public boolean deleteDirectory(final File file) {
        if (file.exists() && file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; ++i) {
                if (listFiles[i].isDirectory()) {
                    this.deleteDirectory(listFiles[i]);
                }
                else {
                    listFiles[i].delete();
                }
            }
        }
        return file.delete();
    }
    
    public String getZipFiles(final String s, final String s2, final String s3) {
        final String s4 = (String)WebContextFactory.get().getSession().getAttribute("user_id");
        String s5 = "";
        try {
            final String string = "" + Host.getServerDocumentPath() + s3 + File.separator;
            final byte[] array = new byte[1024];
            final ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(s));
            for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
                final String name = zipEntry.getName();
                System.out.println("entryname " + name);
                final File file = new File(string + name);
                if (zipEntry.isDirectory()) {
                    file.mkdirs();
                    zipInputStream.closeEntry();
                }
                else {
                    final String parent = file.getParent();
                    System.out.println("===========parent=============" + parent);
                    if (parent != null) {
                        final File file2 = new File(parent);
                        System.out.println("===============parentFile===============" + file2);
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                    }
                    final FileOutputStream fileOutputStream = new FileOutputStream(string + name);
                    int read;
                    while ((read = zipInputStream.read(array, 0, 1024)) > -1) {
                        fileOutputStream.write(array, 0, read);
                    }
                    DataBaseLayer.insertContent("content_management_object", file.getName(), s3, s4);
                    fileOutputStream.close();
                    zipInputStream.closeEntry();
                }
            }
            zipInputStream.close();
        }
        catch (Exception ex) {
            s5 = "Unit Import Failed";
            ex.printStackTrace();
        }
        return s5;
    }
    
    public InputSource setUpInputSource(final String s) {
    	//final InputSource inputSource = new InputSource();
        return this.setupFileSource(s);
    }
    
    public InputSource setupFileSource(final String s) {
        final DebugSwitch debugSwitch = new DebugSwitch();
        cms.log.entry("InputSource setupFileSource()");
        try {
            final File file = new File(s);
            if (file.isFile()) {
                return new InputSource(new FileReader(file));
            }
            if (debugSwitch.ON) {
                System.out.println("--------->>>>>>>-----not a file : " + s);
            }
        }
        catch (NullPointerException ex) {
            if (debugSwitch.ON) {
                System.out.println("-------->>>>>>>-----setupFileSource() : NullPointerException : ");
            }
            cms.log.error("Null pointer exception" + ex);
        }
        catch (SecurityException ex2) {
            if (debugSwitch.ON) {
                System.out.println("-------->>>>>>>-----setupFileSource() : SecurityException : ");
            }
            cms.log.error("Security Exception" + ex2);
        }
        catch (FileNotFoundException ex3) {
            if (debugSwitch.ON) {
                System.out.println("-------->>>>>>>-----setupFileSource() : FileNotFoundException : ");
            }
            cms.log.error("File Not Found Exception" + ex3);
        }
        return new InputSource();
    }
    
    public String UploadUnit(final String courseID, final FileTransfer fileTransfer) {
        String s = "Unit Imported Successfully";
        final DebugSwitch debugSwitch = new DebugSwitch();
        final String s2 = (String)WebContextFactory.get().getSession().getAttribute("user_id");
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        final String[] array = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        //final int n = gregorianCalendar.get(2) + 1;
        final String string = gregorianCalendar.get(10) + ":" + gregorianCalendar.get(12);
        final String string2 = array[gregorianCalendar.get(2)] + " " + gregorianCalendar.get(5) + ", " + gregorianCalendar.get(1);
        new StringBuilder().append(string2).append(" ").append(string).toString();
        //final String s3 = new String();
        final String unitName = DataBaseLayer.getUnitName(courseID);
        try {
            final String serverDocumentPath;
            final String s4 = serverDocumentPath = Host.getServerDocumentPath();
            if (debugSwitch.ON) {
                System.out.println("---111--->>>>>>>-----dir name is : " + serverDocumentPath);
            }
            if (serverDocumentPath == null) {
                s = "Please supply uploadDir parameter";
            }
            final String string3 = s4 + courseID;
            if (debugSwitch.ON) {
                System.out.println("---222--->>>>>>>-----dir name is : " + string3);
            }
            //final File file = new File(string3);
            final File file2 = new File(string3);
            if (!file2.exists()) {
                file2.mkdir();
            }
            //final Object o = new FileUploaderPojo(fileTransfer);
            FileUploaderPojo.getMimeType();
            final InputStream inputStream = FileUploaderPojo.getInputStream();
            final String string4 = string3 + File.separator + FileUploaderPojo.getFilename();
            final String s5 = "";
            try {
                final File file3 = new File(string4);
                if (!file3.exists()) {
                    file3.createNewFile();
                }
                if (debugSwitch.ON) {
                    System.out.println("---333--->>>>>>>-----zip path+file is : " + string4);
                }
                final FileOutputStream fileOutputStream = new FileOutputStream(string4);
                final byte[] array2 = new byte[1024];
                try {
                    int read;
                    while (inputStream != null && (read = inputStream.read(array2)) != -1) {
                        fileOutputStream.write(array2, 0, read);
                    }
                    final PrintStream printStream = new PrintStream(fileOutputStream);
                    printStream.println(s5);
                    printStream.close();
                }
                catch (IOException ex) {
                    s = "Unit Import Failed";
                    if (debugSwitch.ON) {
                        System.out.println("--------->>>>>>>-----IOException : " + ex);
                    }
                }
            }
            catch (Exception ex2) {
                s = "Unit Import Failed";
                if (debugSwitch.ON) {
                    System.out.println("--------->>>>>>>-----Exception : " + ex2);
                }
            }
            final String s6 = unitName;
            //final LMSPackageHandler lmsPackageHandler = new LMSPackageHandler();
            if (LMSPackageHandler.findManifest(string4)) {
                if (debugSwitch.ON) {
                    System.out.println("-------->>>>>>>----- goes inside findManifest() path " + string4);
                }
                LMSPackageHandler.extract(string4, "imsmanifest.xml", string3);
                final String string5 = string3 + File.separatorChar + "imsmanifest.xml";
                DataBaseLayer.insertCourse("csformat", courseID, string5, string2, s2);
                if (debugSwitch.ON) {
                    System.out.println("-------->>>>>>>-----insertCourse() successful ");
                }
                final LMSManifestHandler lmsManifestHandler = new LMSManifestHandler();
                final InputSource setUpInputSource = this.setUpInputSource(string5);
                lmsManifestHandler.setCourseID(courseID);
                lmsManifestHandler.setCourseName(unitName);
                lmsManifestHandler.setFileToParse(setUpInputSource);
                if (debugSwitch.ON) {
                    System.out.println("-------->>>>>>>-----inside processManifest() ... ");
                }
                lmsManifestHandler.processManifest();
                if (debugSwitch.ON) {
                    System.out.println("-------->>>>>>>-----processManifest() successful ");
                }
                try {
                    final String string6 = Host.getServerDocumentPath() + s6 + File.separatorChar + "sequence.obj";
                    if (debugSwitch.ON) {
                        System.out.println("-------->>>>>>>-----sequencingFileName : " + string6);
                    }
                    final File file4 = new File(string6);
                    final String parent = file4.getParent();
                    if (parent != null) {
                        final File file5 = new File(parent);
                        if (!file5.exists()) {
                            file5.mkdirs();
                        }
                    }
                    final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file4));
                    objectOutputStream.writeObject(lmsManifestHandler.getOrgsCopy());
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }
                catch (Exception ex3) {
                    s = "Unit Import Failed";
                    cms.log.error("Error = " + ex3.toString());
                    ex3.printStackTrace();
                }
            }
            if (debugSwitch.ON) {
                System.out.println("----111.1---->>>>>>>-----get the zip file ... " + string4);
            }
            final String zipFiles = this.getZipFiles(string4, unitName, courseID);
            if (!zipFiles.equals("")) {
                s = zipFiles;
            }
        }
        catch (Exception ex4) {
            s = "Unit Import Failed";
            ex4.toString();
            ex4.printStackTrace();
        }
        this.deleteDirectory(new File(Host.getServerDocumentPath() + courseID + File.separatorChar + FileUploaderPojo.getFilename()));
        return s;
    }
    
    public Vector<String> CourseScheduleDetails() {
        Boolean b = true;
        final Vector<String> vector = new Vector<String>();
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        final Vector<String[]> courseSchedule = DataBaseLayer.getCourseSchedule(s2);
        if (courseSchedule.size() == 0) {
            //final String s3 = b ? "#FDE5AE" : "#EDEDED";
            vector.addElement("{sch_id:\"\",schedule_name:\"No Schedules Defined\",st_dt:\"\",st_time:\"\",end_dt:\"\",end_time:\"\",location:\"\",resource:\"\"}");
        }
        else {
            for (int i = 0; i < courseSchedule.size(); ++i) {
            	//final String s4 = b ? "#FDE5AE" : "#EDEDED";
                final String[] array = courseSchedule.elementAt(i);
                //final String s5 = array[1];
                final String s6 = array[2];
                System.out.println("strschedulename----->" + s6);
                final String s7 = array[3];
                System.out.println("strsdate--------->" + s7);
                final String s8 = array[4];
                System.out.println("strstime------------->" + s8);
                final String s9 = array[5];
                System.out.println("stredate------------->" + s9);
                final String s10 = array[6];
                System.out.println("stretime------------>" + s10);
                String s11 = array[7];
                if (s11 == null || s11.equals("null")) {
                    s11 = "";
                }
                System.out.println("strlocation------------>" + s11);
                String s12 = array[9];
                System.out.println("strfilename------------>" + s12);
                System.out.println("strmatType------------>" + array[10]);
                String s13 = "";
                System.out.println("-------------> File Name :  " + s12);
                if ("File".equals(array[10])) {
                    if (s12.equals("null") || s12 == null) {
                        s12 = "";
                        s13 = "";
                    }
                    else {
                        s13 = Host.getServerCoursePathEngine() + "schedule/" + s2 + "/" + array[1] + "/" + array[9];
                    }
                }
                if ("Learning Object".equals(array[10])) {
                    s13 = "javascript:lunchCourse(\\\"" + array[9] + "\\\");";
                }
                if ("Web URL".equals(array[10])) {
                    if (array[9].startsWith("http:")) {
                        s13 = array[9];
                    }
                    else {
                        array[9] = "http://" + array[9];
                        s13 = array[9];
                    }
                }
                vector.addElement("{sch_id:\"" + s2 + "\",schedule_name:\"" + s6 + "\",st_dt:\"" + s7 + "\",st_time:\"" + s8 + "\",end_dt:\"" + s9 + "\",end_time:\"" + s10 + "\",location:\"" + s11 + "\",resource:\"<a href=" + s13 + ">" + s12 + "</a>\"}");
                b = !b;
            }
        }
        return vector;
    }
    
    public Vector<String> CourseResourceDetails() {
        Boolean b = true;
        final Vector<String> vector = new Vector<String>();
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        final Vector<String[]> courseResouces = DataBaseLayer.getCourseResouces(s2);
        if (courseResouces.size() == 0) {
        	//final String s3 = b ? "#FDE5AE" : "#EDEDED";
            vector.addElement("{course_id:\"\",material_id:\"\",name:\"No Resources Defined\",material:\"\",type:\"\",desc:\"\"}");
        }
        else {
            for (int i = 0; i < courseResouces.size(); ++i) {
            	//final String s4 = b ? "#FDE5AE" : "#EDEDED";
                final String[] array = courseResouces.elementAt(i);
                final String s5 = array[1];
                System.out.println("strmatid----->" + s5);
                final String s6 = array[2];
                System.out.println("strmatname--------->" + s6);
                final String s7 = array[3];
                System.out.println("strmattype------------->" + s7);
                String s8 = array[5];
                System.out.println("strmat------------->" + s8);
                final String s9 = array[6];
                System.out.println("strdesc------------>" + s9);
                if ("File".equals(array[3])) {
                    String s10 = s8;
                    String string;
                    if (s8.equals("null") || s8 == null) {
                        s8 = "";
                        string = "";
                        s10 = "";
                    }
                    else {
                        string = Host.getServerCoursePathEngine() + "resource/" + array[0] + "/" + array[1] + "/" + array[5];
                        System.out.println("---------LINK---------->  " + string);
                    }
                    vector.addElement("{course_id:\"" + s2 + "\",material_id:\"" + s5 + "\",name:\"" + s6 + "\",material:\"<a href=\\\"" + string + "\\\" target=\\\"_blank\\\">" + s10 + "</a>\",type:\"" + s7 + "\",desc:\"" + s9 + "\"}");
                }
                if ("Learning Object".equals(array[3])) {
                    String lObjectName = "";
                    String string2;
                    if (s8.equals("null") || s8 == null) {
                        s8 = "";
                        string2 = "";
                    }
                    else {
                        lObjectName = DataBaseLayer.getLObjectName(array[5]);
                        string2 = "javascript:lunchCourse(" + array[5] + ");";
                    }
                    vector.addElement("{course_id:\"" + s2 + "\",material_id:\"" + s5 + "\",name:\"" + s6 + "\",material:\"<a href=\\\"" + string2 + "\\\" target=\\\"_blank\\\">" + lObjectName + "</a>\",type:\"" + s7 + "\",desc:\"" + s9 + "\"}");
                }
                if ("Web URL".equals(array[3])) {
                    String s11;
                    String s12;
                    if (s8.equals("null") || s8 == null) {
                        s8 = "";
                        s11 = "";
                        s12 = "";
                    }
                    else {
                        s12 = s8;
                        System.out.println("---------temp WEB URL---------->  " + s12);
                        if (array[5].startsWith("http:")) {
                            s11 = array[5];
                        }
                        else {
                            array[5] = "http://" + array[5];
                            s11 = array[5];
                        }
                        System.out.println("---------LINK WEB URL---------->  " + s11);
                    }
                    vector.addElement("{course_id:\"" + s2 + "\",material_id:\"" + s5 + "\",name:\"" + s6 + "\",material:\"<a href=\\\"" + s11 + "\\\" target=\\\"_blank\\\">" + s12 + "</a>\",type:\"" + s7 + "\",desc:\"" + s9 + "\"}");
                }
                if ("Forum".equals(array[3])) {
                    String string3;
                    String returnforumname;
                    if (s8.equals("null") || s8 == null) {
                        s8 = "";
                        string3 = "";
                        returnforumname = "";
                    }
                    else {
                        string3 = "javascript:forum(\\\"" + array[5] + "\\\",\\\"" + (returnforumname = DataBaseLayer.returnforumname(s8)) + "\\\");";
                    }
                    vector.addElement("{course_id:\"" + s2 + "\",material_id:\"" + s5 + "\",name:\"" + s6 + "\",material:\"<a href=\\\"" + string3 + "\\\" target=\\\"_blank\\\">" + returnforumname + "</a>\",type:\"" + s7 + "\",desc:\"" + s9 + "\"}");
                }
                if ("SCE".equals(array[3])) {
                    String string4;
                    String s13;
                    if (s8.equals("null") || s8 == null) {
                        string4 = "";
                        s13 = "";
                    }
                    else {
                        s13 = s8;
                        string4 = "javascript:sce('" + array[5] + "');";
                    }
                    vector.addElement("{course_id:\"" + s2 + "\",material_id:\"" + s5 + "\",name:\"" + s6 + "\",material:\"<a href=\\\"" + string4 + "\\\" target=\\\"_blank\\\">" + s13 + "</a>\",type:\"" + s7 + "\",desc:\"" + s9 + "\"}");
                }
                b = !b;
            }
        }
        return vector;
    }
    
    public Vector<String> CourseAnnouncementDetails() {
        System.out.println("@@@@@@@@@@@@@@@@@@@ INSIDE CourseAnnouncementDetails @@@@@@@@@@@@@@@@@@@@");
        Boolean b = true;
        Vector<String[]> selectNoticeInformation = null;
        final Vector<String> vector = new Vector<String>();
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        String selectNumAnnounce = "";
        if (s2 != null) {
            selectNoticeInformation = (Vector<String[]>)DataBaseLayer.selectNoticeInformation(s2);
            selectNumAnnounce = DataBaseLayer.selectNumAnnounce(s2);
        }
        if (!selectNumAnnounce.equals("")) {
            Integer.parseInt(selectNumAnnounce);
        }
        if (selectNoticeInformation.size() == 0) {
            //final String s3 = b ? "#FDE5AE" : "#EDEDED";
            vector.addElement("{notice_head:\"No Announcements Exist\",posted:\"\",attchmnt:\"\",body:\"\"}");
        }
        else {
            for (int i = 0; i < selectNoticeInformation.size(); ++i) {
                //final String s4 = b ? "#FDE5AE" : "#EDEDED";
                final int n = i + 1;
                final String[] array = selectNoticeInformation.elementAt(i);
                final String s5 = array[0];
                System.out.println("noticehead----->" + s5);
                final String s6 = array[1];
                System.out.println("postedon--------->" + s6);
                final String s7 = array[2];
                System.out.println("body------------->" + s7);
                String s8 = array[3];
                System.out.println("attachments------------->" + s8);
                if (s8 == null) {
                    s8 = "";
                }
                String string;
                if (s8.equals("")) {
                    string = "";
                }
                else {
                    string = Host.getServerCoursePathEngine() + "Announcements/" + s2 + "/" + s8;
                }
                vector.addElement("{notice_head:\"" + n + " | " + s5 + "\",posted:\"" + s6 + "\",attchmnt:\"<a href=\\\"" + string + "\\\" target=\\\"_blank\\\">" + s8 + "</a>\",body:\"" + s7 + "\"}");
                b = !b;
            }
        }
        return vector;
    }
    
    public Vector<String> CourseAssignmentDetails() {
        String string = "";
        Boolean b = true;
        final Vector<String> vector = new Vector<String>();
        final HttpSession session = WebContextFactory.get().getSession();
        //final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        cms.log.debug("=============course_id======" + s2);
        final Vector<String[]> courseAssignment = DataBaseLayer.getCourseAssignment(s2);
        System.out.println(courseAssignment);
        if (courseAssignment != null) {
            courseAssignment.size();
        }
        if (courseAssignment.size() == 0) {
            //final String s3 = b ? "#FDE5AE" : "#FFFFFF";
            string = "{assignment_id:\"\",title:\"No Assignments Exist\",desc:\"\",submissn_dt:\"\",file_name:\"\",assignmnt_size:\"\",full_marks:\"\"}";
            vector.addElement(string);
        }
        else {
            for (int i = 0; i < courseAssignment.size(); ++i) {
                //final String s4 = b ? "#FDE5AE" : "#FFFFFF";
                final String[] array = courseAssignment.elementAt(i);
                final String s5 = array[0];
                cms.log.debug("assign_id----->" + s5);
                final String s6 = array[2];
                cms.log.debug("title--------->" + s6);
                final String s7 = array[3];
                cms.log.debug("descr------------->" + s7);
                final String s8 = array[4];
                cms.log.debug("subdate------------->" + s8);
                final String s9 = array[5];
                cms.log.debug("docname------------->" + s9);
                final String s10 = array[6];
                cms.log.debug("size------------->" + s10);
                final String s11 = array[7];
                cms.log.debug("fullmarks------------->" + s11);
                final String string2 = Host.getServerCoursePathEngine() + "assignment/" + array[1] + "/" + s9;
                cms.log.debug("----------getServerCoursePathEngine----------> " + Host.getServerCoursePathEngine());
                string = "{assignment_id:\"" + s5 + "\",title:\"" + s6 + "\",desc:\"" + s7 + "\",submissn_dt:\"" + s8 + "\",file_name:\"<a href=\\\"" + string2 + "\\\">" + s9 + "</a>\",assignmnt_size:\"" + s10 + "\",full_marks:\"" + s11 + "\"}";
                vector.addElement(string);
                b = !b;
            }
        }
        return vector;
    }
    
    public String getDocId(final String s) {
        //final String s2 = (String)WebContextFactory.get().getSession().getAttribute("course_id");
        return DataBaseLayer.getDocumentIdFromAssignment(s);
    }
    
    public Vector<String> selectMaterial(final String s) {
        final String s2 = (String)WebContextFactory.get().getSession().getAttribute("course_id");
        final Vector<String[]> courseTopicResources = DataBaseLayer.getCourseTopicResources(s2, s);
        final Vector<String> vector = new Vector<String>();
        String s3 = "";
        for (int i = 0; i < courseTopicResources.size(); ++i) {
            final String[] array = courseTopicResources.elementAt(i);
            cms.log.debug("==============str[2]======" + array[2]);
            cms.log.debug("==============str[3]======" + array[3]);
            cms.log.debug("==============str[4]======" + array[4]);
            if ("Learning Object".equals(array[3])) {
                if (array[4] == null || array[4].equals("null")) {
                    array[4] = "";
                    s3 = "{material_id:\"\",material:\"No Materials Exist\",type:\"\",desc:\"\"}";
                    vector.addElement(s3);
                }
                else {
                    s3 = "{material_id:\"" + array[2] + "\",material:\"<a href=\\\"" + ("javascript:lunchCourse(" + array[4] + ");") + "\\\">" + DataBaseLayer.getLObjectName(array[4]) + "</a>\",type:\"" + array[3] + "\",desc:\"" + array[5] + "\"}";
                    vector.addElement(s3);
                }
            }
            else if ("Web URL".equals(array[3])) {
                if (array[4] == null || array[4].equals("null")) {
                    array[4] = "";
                    s3 = "{material_id:\"" + array[2] + "\",material:\"\",type:\"" + array[3] + "\",desc:\"" + array[5] + "\"}";
                    vector.addElement(s3);
                }
                else {
                    if (!array[4].startsWith("http:")) {
                        array[4] = "http://" + array[4];
                    }
                    s3 = "{material_id:\"" + array[2] + "\",material:\"<a href=\\\"" + ("javascript:openweburl('" + array[4] + "');") + "\\\">" + array[4] + "</a>\",type:\"" + array[3] + "\",desc:\"" + array[5] + "\"}";
                    vector.addElement(s3);
                }
            }
            else {
                cms.log.debug("==========file=====");
                if (array[4] == null || array[4].equals("null")) {
                    array[4] = "";
                    s3 = "{material_id:\"" + array[2] + "\",material:\"\",type:\"" + array[3] + "\",desc:\"" + array[5] + "\"}";
                    vector.addElement(s3);
                }
                else {
                    s3 = "{material_id:\"" + array[2] + "\",material:\"<a href=\\\"" + (Host.getServerCoursePathEngine() + "syllabus/" + s2 + "/" + s + "/" + array[4]) + "\\\" target=\\\"_blank\\\">" + array[4] + "</a>\",type:\"" + array[3] + "\",desc:\"" + array[5] + "\"}";
                    vector.addElement(s3);
                }
            }
        }
        System.out.println("strMaterial===" + s3);
        return vector;
    }
    
    public Vector<String> CourseAssignmentDetailsDocument() {
        cms.log.debug("---- INSIDE CourseAssignmentDetailsDocument ------");
        Boolean b = true;
        final HttpSession session = WebContextFactory.get().getSession();
        final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("course_id");
        final String s3 = (String)session.getAttribute("assign_id");
        final Vector<String> vector = new Vector<String>();
        final Vector<String[]> courseAssignmentDoc = DataBaseLayer.getCourseAssignmentDoc(s2, s, s3);
        System.out.println(courseAssignmentDoc);
        if (courseAssignmentDoc.size() == 0) {
            //final String s4 = b ? "#FDE5AE" : "#FFFFFF";
            vector.addElement("{doc_id:\"\",doc_name:\"No Documents Exist\",doc_size:\"\",date_uploaded:\"\",conf_no:\"\"}");
        }
        else {
            for (int i = 0; i < courseAssignmentDoc.size(); ++i) {
                final String s5 = b ? "#FDE5AE" : "#FFFFFF";
                final String[] array = courseAssignmentDoc.elementAt(i);
                final String s6 = array[0];
                cms.log.debug("doc_name----->" + s6);
                final String s7 = array[1];
                cms.log.debug("size--------->" + s7);
                final String s8 = array[2];
                cms.log.debug("subdate------------->" + s8);
                final String s9 = array[3];
                cms.log.debug("confirm_no------------->" + s9);
                final String s10 = array[4];
                cms.log.debug("document_id------------->" + s10);
                cms.log.debug("<table  border=\"0\" cellpadding=\"0\" cellspacing=\"1\"  width=\"785\" ><tr style=\"background-color:" + s5 + ";\"><td class=\"tdstyle1\"  width=\"38\"><input type=radio value=\"" + s6 + "\" name=checkbox1 onclick=\"javascript:checkbox_onclick1('" + s6 + "','" + s10 + "');\"></td>");
                vector.addElement("{doc_id:\"" + s10 + "\",doc_name:\"" + s6 + "\",doc_size:\"" + s7 + "\",date_uploaded:\"" + s8 + "\",conf_no:\"" + s9 + "\"}");
                b = !b;
            }
        }
        return vector;
    }
    
    public String getTopicIdStatus() {
        final String s = (String)WebContextFactory.get().getSession().getAttribute("course_id");
        System.out.println("=======Course_ID=======" + s);
        //final String[] array = new String[6];
        final String s2 = "";
        final String[] topicIdStatus = DataBaseLayer.getTopicIdStatus(s);
        System.out.println("========TopicDetails[0]==========" + topicIdStatus[0]);
        System.out.println("========TopicDetails[1]==========" + topicIdStatus[1]);
        System.out.println("========TopicDetails[2]==========" + topicIdStatus[2]);
        System.out.println("========TopicDetails[3]==========" + topicIdStatus[3]);
        System.out.println("========TopicDetails[4]==========" + topicIdStatus[4]);
        System.out.println("========TopicDetails[5]==========" + topicIdStatus[5]);
        final String s3 = topicIdStatus[0];
        final String s4 = topicIdStatus[1];
        final String s5 = topicIdStatus[2];
        final String s6 = topicIdStatus[3];
        final String s7 = topicIdStatus[4];
        final String s8 = topicIdStatus[5];
        String s9 = s2 + "<table><tr><td><input type=\"button\" id=\"button\" name=\"button\" value=\"Home\" onclick=\"javascript:home_onclick();\" style=\"cursor: pointer;background-color: #BAFAF9;font-weight:bold;font-family:verdana;font-size:13px;-moz-border-radius-topleft:10px;-moz-border-radius-topright:10px;border:1px solid #000000;border-bottom-color:black;border-bottom-width:1px;\"/></td>";
        if (s3.equals("T")) {
            s9 += "<td><input type=\"button\" id=\"button1\" name=\"button1\" value=\"Content\" onclick=\"javascript:syllabus_onclick();\" style=\"cursor: pointer;background-color: #BAFAF9;font-weight:bold;font-family:verdana;font-size:13px;-moz-border-radius-topleft:10px;-moz-border-radius-topright:10px;border:1px solid #000000;border-bottom-color:black;border-bottom-width:1px;\"/></td>";
        }
        if (s4.equals("T")) {
            s9 += "<td><input type=\"button\" id=\"button2\" name=\"button2\" value=\"Schedule\" onclick=\"javascript:schedule_onclick();\" style=\"cursor: pointer;background-color: #BAFAF9;font-weight:bold;font-family:verdana;font-size:13px;-moz-border-radius-topleft:10px;-moz-border-radius-topright:10px;border:1px solid #000000;border-bottom-color:black;border-bottom-width:1px;\"/></td>";
        }
        if (s5.equals("T")) {
            s9 += "<td><input type=\"button\" id=\"button3\" name=\"button3\" value=\"Resource\" onclick=\"javascript:resource_onclick();\" style=\"cursor: pointer;background-color: #BAFAF9;font-weight:bold;font-family:verdana;font-size:13px;-moz-border-radius-topleft:10px;-moz-border-radius-topright:10px;border:1px solid #000000;border-bottom-color:black;border-bottom-width:1px;\"/></td>";
        }
        if (s6.equals("T")) {
            s9 += "<td><input type=\"button\" id=\"button4\" name=\"button4\" value=\"Announcement\" onclick=\"javascript:announcement_onclick();\" style=\"cursor: pointer;background-color: #BAFAF9;font-weight:bold;font-family:verdana;font-size:13px;-moz-border-radius-topleft:10px;-moz-border-radius-topright:10px;border:1px solid #000000;border-bottom-color:black;border-bottom-width:1px;\"/></td>";
        }
        if (s7.equals("T")) {
            s9 += "<td><input type=\"button\" id=\"button5\" name=\"button5\" value=\"Assignment\" onclick=\"javascript:assignment_onclick();\" style=\"cursor: pointer;background-color: #BAFAF9;font-weight:bold;font-family:verdana;font-size:13px;-moz-border-radius-topleft:10px;-moz-border-radius-topright:10px;border:1px solid #000000;border-bottom-color:black;border-bottom-width:1px;\"/></td>";
        }
        if (s8.equals("T")) {
            s9 += "<td><input type=\"button\" id=\"button6\" name=\"button6\" value=\"Gradebook\" onclick=\"javascript:gradebook_onclick();\" style=\"cursor: pointer;background-color: #BAFAF9;font-weight:bold;font-family:verdana;font-size:13px;-moz-border-radius-topleft:10px;-moz-border-radius-topright:10px;border:1px solid #000000;border-bottom-color:black;border-bottom-width:1px;\"/></td>";
        }
        final String string = s9 + "<td><input type=\"button\" id=\"button7\" name=\"button7\" value=\"Quit\" onclick=\"javascript:quit_onclick();\" style=\"cursor: pointer;background-color: #BAFAF9;font-weight:bold;font-family:verdana;font-size:13px;-moz-border-radius-topleft:10px;-moz-border-radius-topright:10px;border:1px solid #000000;border-bottom-color:black;border-bottom-width:1px;\"></td></tr></table>";
        System.out.println("==========HTML=======" + string);
        return string;
    }
    
    public String[] getTopicIds() {
        final String s = (String)WebContextFactory.get().getSession().getAttribute("course_id");
        System.out.println("=======Course_ID=======" + s);
        //final String[] array = new String[6];
        return DataBaseLayer.getTopicIdStatus(s);
    }
    
    static {
        log = new SimpleLogger((Class<cms>)cms.class, true);
    }
    
    public class DebugSwitch
    {
        public boolean ON;
        
        public DebugSwitch() {
            this.ON = true;
        }
    }
}
