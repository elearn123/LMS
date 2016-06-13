package learnityadmin;

//import java.util.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.commons.codec.binary.*;
import learnityInit.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.apache.xml.serialize.*;
import learnityInit.util.*;
import java.io.*;

public class SystemExport extends Cleaner
{
    private Document dom;
    private String choice;
    private String outputfile;
    private String outputdir;
    
    public SystemExport() {
    }
    
    public SystemExport(final String choice, final String outputdir, final String outputfile) {
        if (choice == null) {
            throw new NullPointerException();
        }
        this.choice = choice;
        this.outputfile = outputfile;
        this.outputdir = outputdir;
    }
    
    public void export() throws Exception {
        this.getDocument();
        this.createDOM();
        this.generateXML();
    }
    
    private void createDOM() {
        final Vector<Vector<String>> vUserDetails = DataBaseLayer.getUserDetails();
        try {
            final Element systemexport = this.dom.createElement("systemexport");
            this.dom.appendChild(systemexport);
            if ((this.choice.charAt(0) == 'T' || this.choice.charAt(11) == 'T') && vUserDetails != null && vUserDetails.size() != 0) {
                final Element users = this.dom.createElement("users");
                int noofuser = 0;
                if (vUserDetails != null) {
                    noofuser = vUserDetails.size();
                }
                final String s1 = Integer.toString(noofuser);
                users.setAttribute("noofusers", s1);
                systemexport.appendChild(users);
                for (int i = 0; i < noofuser; ++i) {
                    final Element user = this.dom.createElement("user");
                    final Vector<String> v = vUserDetails.elementAt(i);
                    final String userid = v.elementAt(0);
                    final String password = v.elementAt(1);
                    final String first_name = v.elementAt(2);
                    final String middle_name = v.elementAt(3);
                    final String sname = v.elementAt(4);
                    final String gender = v.elementAt(5);
                    final String account_status = v.elementAt(6);
                    final String date_student_created = v.elementAt(7);
                    final String student_created_by = v.elementAt(8);
                    final String last_modification_date = v.elementAt(9);
                    final String role = v.elementAt(10);
                    user.setAttribute("id", userid);
                    user.setAttribute("password", password);
                    user.setAttribute("fname", first_name);
                    user.setAttribute("mname", middle_name);
                    user.setAttribute("sname", sname);
                    user.setAttribute("gender", gender);
                    user.setAttribute("status", account_status);
                    user.setAttribute("role", role);
                    user.setAttribute("createdon", date_student_created);
                    user.setAttribute("createdby", student_created_by);
                    user.setAttribute("modifiedon", last_modification_date);
                    final Element photoelement = this.dom.createElement("photo");
                    final byte[] b1 = DataBaseLayer.getUserPhoto(userid);
                    if (b1 != null) {
                        final byte[] b2 = Base64.encodeBase64(b1);
                        final String photo1 = new String(b2);
                        System.out.println("photo1=====" + photo1);
                        final Text ptextnode = this.dom.createCDATASection(photo1);
                        photoelement.appendChild(ptextnode);
                    }
                    else {
                        final Text ptextnode2 = this.dom.createCDATASection("none");
                        photoelement.appendChild(ptextnode2);
                    }
                    user.appendChild(photoelement);
                    users.appendChild(user);
                }
            }
            if (this.choice.charAt(1) == 'T' || this.choice.charAt(11) == 'T') {
                final Vector<Vector<String>> v2 = DataBaseLayer.getGroupDetails();
                if (v2 != null && v2.size() != 0) {
                    final Element groups = this.dom.createElement("groups");
                    int noofgroup = 0;
                    if (v2 != null) {
                        noofgroup = v2.size();
                    }
                    final String s2 = Integer.toString(noofgroup);
                    groups.setAttribute("noofgroups", s2);
                    systemexport.appendChild(groups);
                    for (int i2 = 0; i2 < noofgroup; ++i2) {
                        final Element group = this.dom.createElement("group");
                        final Vector<String> v3 = v2.elementAt(i2);
                        final String groupid = v3.elementAt(0);
                        final String groupname = v3.elementAt(1);
                        final String groupcreatedon = v3.elementAt(2);
                        final String groupcreatedby = v3.elementAt(3);
                        group.setAttribute("id", groupid);
                        group.setAttribute("name", groupname);
                        group.setAttribute("createdon", groupcreatedon);
                        group.setAttribute("createdby", groupcreatedby);
                        groups.appendChild(group);
                    }
                }
            }
            if (this.choice.charAt(6) == 'T' || this.choice.charAt(11) == 'T') {
                final Vector<Vector<String>> v2 = DataBaseLayer.getGroupDetails();
                if (v2 != null && v2.size() != 0) {
                    int noofgroup2 = 0;
                    if (v2 != null) {
                        noofgroup2 = v2.size();
                    }
                    final Element usergroupreg = this.dom.createElement("usergroupregistration");
                    systemexport.appendChild(usergroupreg);
                    for (int i3 = 0; i3 < noofgroup2; ++i3) {
                        final Element group2 = this.dom.createElement("group");
                        final Vector<String> v = v2.elementAt(i3);
                        final String groupid2 = v.elementAt(0);
                        final Vector<String> v4 = DataBaseLayer.getGroupUserRegDetails(groupid2);
                        if (v4 != null && v4.size() != 0) {
                            int noofgroup3 = 0;
                            if (v4 != null) {
                                noofgroup3 = v4.size();
                            }
                            final String s3 = Integer.toString(noofgroup3);
                            group2.setAttribute("id", groupid2);
                            group2.setAttribute("noofusersregistered", s3);
                            usergroupreg.appendChild(group2);
                            for (int i4 = 0; i4 < noofgroup3; ++i4) {
                                final Element user2 = this.dom.createElement("user");
                                final String userid2 = v4.elementAt(i4);
                                user2.setAttribute("id", userid2);
                                group2.appendChild(user2);
                            }
                        }
                    }
                }
            }
            if (this.choice.charAt(2) == 'T' || this.choice.charAt(11) == 'T') {
                final Vector<Vector<String>> v2 = DataBaseLayer.getUnitInfo();
                if (v2 != null && v2.size() != 0) {
                    final Element units = this.dom.createElement("units");
                    int noofunit = 0;
                    if (v2 != null) {
                        noofunit = v2.size();
                    }
                    final String s2 = Integer.toString(noofunit);
                    units.setAttribute("noofunits", s2);
                    systemexport.appendChild(units);
                    for (int i2 = 0; i2 < noofunit; ++i2) {
                        final Element unit = this.dom.createElement("unit");
                        final Vector<String> v3 = v2.elementAt(i2);
                        final String unitid = v3.elementAt(0);
                        final String unitname = v3.elementAt(1);
                        final String status = v3.elementAt(2);
                        final String control = v3.elementAt(3);
                        final String createdby = v3.elementAt(4);
                        final String createdon = v3.elementAt(5);
                        final String modifiedby = v3.elementAt(6);
                        final String modifiedon = v3.elementAt(7);
                        final String selfreg = v3.elementAt(8);
                        final String confreqd = v3.elementAt(9);
                        final String confirmedby = v3.elementAt(10);
                        unit.setAttribute("id", unitid);
                        unit.setAttribute("name", unitname);
                        unit.setAttribute("status", status);
                        unit.setAttribute("control", control);
                        unit.setAttribute("createdby", createdby);
                        unit.setAttribute("createdon", createdon);
                        unit.setAttribute("modifiedby", modifiedby);
                        unit.setAttribute("modifiedon", modifiedon);
                        unit.setAttribute("selfreg", selfreg);
                        unit.setAttribute("confreqd", confreqd);
                        unit.setAttribute("confirmedby", confirmedby);
                        units.appendChild(unit);
                    }
                }
            }
            if (this.choice.charAt(7) == 'T' || this.choice.charAt(11) == 'T') {
                final Vector<Vector<String>> v2 = DataBaseLayer.getUnitInfo();
                if (v2 != null && v2.size() != 0) {
                    int noofunit2 = 0;
                    if (v2 != null) {
                        noofunit2 = v2.size();
                    }
                    final Element unitreguer = this.dom.createElement("unituserregistration");
                    systemexport.appendChild(unitreguer);
                    for (int i3 = 0; i3 < noofunit2; ++i3) {
                        final Element unit2 = this.dom.createElement("unit");
                        final Vector<String> v = v2.elementAt(i3);
                        final String unitid2 = v.elementAt(0);
                        final Vector<Vector<String>> v4 = DataBaseLayer.getUnitUserRegnInfo(unitid2);
                        if (v4 != null && v4.size() != 0) {
                            int noofuser2 = 0;
                            if (v4 != null) {
                                noofuser2 = v4.size();
                            }
                            final String s3 = Integer.toString(noofuser2);
                            unit2.setAttribute("id", unitid2);
                            unit2.setAttribute("noofusersregistered", s3);
                            unitreguer.appendChild(unit2);
                            for (int i4 = 0; i4 < noofuser2; ++i4) {
                                final Vector<String> v5 = v4.elementAt(i4);
                                final Element user3 = this.dom.createElement("user");
                                final String userid3 = v5.elementAt(0);
                                final String access_allowed_till = v5.elementAt(1);
                                final String total_access_time = v5.elementAt(2);
                                user3.setAttribute("id", userid3);
                                user3.setAttribute("accessallowedtill", access_allowed_till);
                                user3.setAttribute("totalaccesstime", total_access_time);
                                unit2.appendChild(user3);
                            }
                        }
                        final Element unit3 = this.dom.createElement("unit");
                        final Vector<Vector<String>> v6 = DataBaseLayer.getUnitGroupAssnInfo(unitid2);
                        if (v6 != null && v6.size() != 0) {
                            final Element unitgroupreg = this.dom.createElement("unitgroupregistration");
                            systemexport.appendChild(unitgroupreg);
                            int noofgroup4 = 0;
                            if (v6 != null) {
                                noofgroup4 = v6.size();
                            }
                            final String s4 = Integer.toString(noofgroup4);
                            unit3.setAttribute("id", unitid2);
                            unit3.setAttribute("noofgroupsregistered", s4);
                            unitgroupreg.appendChild(unit3);
                            for (int i5 = 0; i5 < noofgroup4; ++i5) {
                                final Vector<String> v7 = v6.elementAt(i5);
                                final Element group3 = this.dom.createElement("group");
                                final String groupid3 = v7.elementAt(0);
                                final String access_allowed_till2 = v7.elementAt(1);
                                final String total_access_time2 = v7.elementAt(2);
                                group3.setAttribute("id", groupid3);
                                group3.setAttribute("accessallowedtill", access_allowed_till2);
                                group3.setAttribute("totalaccesstime", total_access_time2);
                                unit3.appendChild(group3);
                            }
                        }
                    }
                }
            }
            if (this.choice.charAt(3) == 'T' || this.choice.charAt(11) == 'T') {
                final Vector<String[]> v2 = DataBaseLayer.getForumInformation();
                if (v2 != null && v2.size() != 0) {
                    final Element forums = this.dom.createElement("forums");
                    int noofforum = 0;
                    if (v2 != null) {
                        noofforum = v2.size();
                    }
                    final String s2 = Integer.toString(noofforum);
                    forums.setAttribute("noofforums", s2);
                    systemexport.appendChild(forums);
                    for (int i2 = 0; i2 < noofforum; ++i2) {
                        final Element forum = this.dom.createElement("forum");
                        final String[] st = v2.elementAt(i2);
                        final String forumid = st[0];
                        final String forumname = st[1];
                        final String noofmessage = st[2];
                        final String noofthread = st[3];
                        forum.setAttribute("id", forumid);
                        forum.setAttribute("name", forumname);
                        forum.setAttribute("noofthread", noofthread);
                        forum.setAttribute("noofmessage", noofmessage);
                        forum.setAttribute("lastmessagegpostedate", st[4]);
                        forum.setAttribute("createdby", st[5]);
                        forum.setAttribute("startdate", st[6]);
                        forum.setAttribute("selfregistration", st[7]);
                        forum.setAttribute("confirmedby", st[8]);
                        forum.setAttribute("confirmationrequired", st[9]);
                        forums.appendChild(forum);
                        final Vector<String[]> v5 = DataBaseLayer.getForumsThread(forumid);
                        for (int i6 = 0; i6 < v5.size(); ++i6) {
                            final Element thread = this.dom.createElement("thread");
                            final String[] st2 = v5.elementAt(i6);
                            final String threadid = st2[0];
                            final String threadtitle = st2[1];
                            final String createdon2 = st2[2];
                            final String createdby2 = st2[3];
                            final String noofmessage2 = st2[4];
                            final String lastpostmessageid = st2[5];
                            thread.setAttribute("id", threadid);
                            thread.setAttribute("title", threadtitle);
                            thread.setAttribute("noofmessage", noofmessage2);
                            thread.setAttribute("lastpostdate", createdon2);
                            thread.setAttribute("lastpostmessageid", lastpostmessageid);
                            thread.setAttribute("lastpostby", createdby2);
                            forum.appendChild(thread);
                            final Vector<String[]> v8 = DataBaseLayer.getForumsMessage(forumid, threadid);
                            for (int i7 = 0; i7 < v8.size(); ++i7) {
                                final String[] st3 = v8.elementAt(i7);
                                final String messageid = st3[0];
                                final String created_by = st3[1];
                                final String created_on = st3[2];
                                final String message1 = st3[3];
                                final String attachments1 = st3[4];
                                final Element message2 = this.dom.createElement("message");
                                message2.setAttribute("id", messageid);
                                message2.setAttribute("postedby", created_by);
                                message2.setAttribute("postedon", created_on);
                                message2.setAttribute("threadtitle", st3[5]);
                                message2.setAttribute("threadview", st3[6]);
                                message2.setAttribute("parentthread", st3[7]);
                                thread.appendChild(message2);
                                if (message1 != null) {
                                    final Element messagebody = this.dom.createElement("messagebody");
                                    final Text messagebody2 = this.dom.createCDATASection(message1);
                                    messagebody.appendChild(messagebody2);
                                    message2.appendChild(messagebody);
                                }
                                final Element attachments2 = this.dom.createElement("attachments");
                                message2.appendChild(attachments2);
                                if (attachments1 != null && !attachments1.equals("")) {
                                    final Element attachment = this.dom.createElement("attachment");
                                    attachment.setAttribute("filename", attachments1);
                                    attachments2.appendChild(attachment);
                                    final String strLocation = Host.getServerDocumentPath();
                                    final String filename = strLocation + File.separator + "attachments" + File.separator + "attachmentForum" + File.separator + forumid + File.separator + attachments1;
                                    byte[] b3 = null;
                                    b3 = BytesFromFile(new File(filename));
                                    final byte[] b4 = Base64.encodeBase64(b3);
                                    final String attachmentcontent = new String(b4);
                                    final Element filecontent = this.dom.createElement("filecontent");
                                    final Text filecontent2 = this.dom.createTextNode(attachmentcontent);
                                    attachment.appendChild(filecontent);
                                    filecontent.appendChild(filecontent2);
                                }
                            }
                        }
                    }
                }
            }
            if (this.choice.charAt(8) == 'T' || this.choice.charAt(11) == 'T') {
                final Vector<String[]> v2 = DataBaseLayer.getForumInformation();
                if (v2 != null && v2.size() != 0) {
                    int noofforum2 = 0;
                    if (v2 != null) {
                        noofforum2 = v2.size();
                    }
                    final Element forumreguser = this.dom.createElement("forumuserregistration");
                    systemexport.appendChild(forumreguser);
                    for (int i3 = 0; i3 < noofforum2; ++i3) {
                        final Element forum2 = this.dom.createElement("forum");
                        String[] string2 = v2.elementAt(i3);
                        final String forumid2 = string2[0];
                        final Vector<String[]> v4 = DataBaseLayer.getForumUserRegnInfo(forumid2);
                        if (v4 != null && v4.size() != 0) {
                            int noofuser2 = 0;
                            if (v4 != null) {
                                noofuser2 = v4.size();
                            }
                            final String s3 = Integer.toString(noofuser2);
                            forum2.setAttribute("id", forumid2);
                            forum2.setAttribute("noofusersregistered", s3);
                            forumreguser.appendChild(forum2);
                            for (int i4 = 0; i4 < noofuser2; ++i4) {
                                string2 = v4.elementAt(i4);
                                final Element user3 = this.dom.createElement("user");
                                final String userid3 = string2[0];
                                final String registeredby = string2[1];
                                user3.setAttribute("id", userid3);
                                user3.setAttribute("registeredby", registeredby);
                                forum2.appendChild(user3);
                            }
                        }
                        final Element forum3 = this.dom.createElement("forum");
                        final Vector<String[]> v6 = DataBaseLayer.getForumGroupRegnInfo(forumid2);
                        if (v6 != null && v6.size() != 0) {
                            final Element forumgroupreg = this.dom.createElement("forumgroupregistration");
                            systemexport.appendChild(forumgroupreg);
                            int noofgroup4 = 0;
                            if (v6 != null) {
                                noofgroup4 = v6.size();
                            }
                            final String s4 = Integer.toString(noofgroup4);
                            forum3.setAttribute("id", forumid2);
                            forum3.setAttribute("noofgroupsregistered", s4);
                            forumgroupreg.appendChild(forum3);
                            for (int i5 = 0; i5 < noofgroup4; ++i5) {
                                final String[] string3 = v6.elementAt(i5);
                                final Element group3 = this.dom.createElement("group");
                                final String groupid3 = string3[0];
                                final String registeredby2 = string3[1];
                                group3.setAttribute("id", groupid3);
                                group3.setAttribute("registeredby", registeredby2);
                                forum3.appendChild(group3);
                            }
                        }
                    }
                }
            }
            if (this.choice.charAt(14) == 'T' || this.choice.charAt(11) == 'T') {
                final Vector<String[]> v2 = DataBaseLayer.getNoticeInformation();
                if (v2 != null && v2.size() != 0) {
                    final Element notices = this.dom.createElement("notices");
                    int noofnotice = 0;
                    if (v2 != null) {
                        noofnotice = v2.size();
                    }
                    final String s2 = Integer.toString(noofnotice);
                    notices.setAttribute("noofnotice", s2);
                    systemexport.appendChild(notices);
                    for (int i2 = 0; i2 < noofnotice; ++i2) {
                        final Element notice = this.dom.createElement("notice");
                        final String[] st = v2.elementAt(i2);
                        final String notice_id = st[0];
                        final String heading = st[1];
                        final String body = st[2];
                        final String student_id = st[3];
                        final String posted_on = st[4];
                        final String attachments3 = st[5];
                        final String status2 = st[6];
                        final String group_id = st[7];
                        notice.setAttribute("id", notice_id);
                        notice.setAttribute("heading", heading);
                        notice.setAttribute("postedby", student_id);
                        notice.setAttribute("postedon", posted_on);
                        notice.setAttribute("groupid", group_id);
                        notice.setAttribute("status", status2);
                        notices.appendChild(notice);
                        if (body != null) {
                            final Element noticebody = this.dom.createElement("noticebody");
                            final Text body2 = this.dom.createCDATASection(body);
                            noticebody.appendChild(body2);
                            notice.appendChild(noticebody);
                        }
                        final Element attachments4 = this.dom.createElement("attachments");
                        notice.appendChild(attachments4);
                        if (attachments3 != null && !attachments3.equals("")) {
                            final Element attachment2 = this.dom.createElement("attachment");
                            attachment2.setAttribute("filename", attachments3);
                            attachments4.appendChild(attachment2);
                            final String strLocation2 = Host.getServerDocumentPath();
                            final String filename2 = strLocation2 + File.separator + "attachments" + File.separator + student_id + File.separator + attachments3;
                            byte[] b5 = null;
                            b5 = BytesFromFile(new File(filename2));
                            final byte[] b6 = Base64.encodeBase64(b5);
                            final String attachmentcontent2 = new String(b6);
                            final Text filecontent3 = this.dom.createTextNode(attachmentcontent2);
                            attachment2.appendChild(filecontent3);
                        }
                    }
                }
            }
            if (this.choice.charAt(4) == 'T' || this.choice.charAt(11) == 'T') {
                final Vector<String[]> courseInfo = DataBaseLayer.getCourseInfo();
                final Element courses = this.dom.createElement("courses");
                int noofcourses = 0;
                if (courseInfo != null && courseInfo.size() != 0) {
                    noofcourses = courseInfo.size();
                    final String no_of_courses = Integer.toString(noofcourses);
                    courses.setAttribute("noofcourses", no_of_courses);
                    systemexport.appendChild(courses);
                    for (int c = 0; c < noofcourses; ++c) {
                        final Element course = this.dom.createElement("course");
                        final String[] courseArray = courseInfo.elementAt(c);
                        final String courseid = courseArray[0];
                        final String coursename = courseArray[1];
                        final String type = courseArray[2];
                        final String session = courseArray[3];
                        final String sdate = courseArray[4];
                        final String edate = courseArray[5];
                        final String createdby3 = courseArray[6];
                        final String createdon3 = courseArray[7];
                        final String description = courseArray[8];
                        final String cpoints = courseArray[9];
                        final String ttimes = courseArray[10];
                        final String cost = courseArray[11];
                        final String intructor1 = courseArray[12];
                        final String intructor2 = courseArray[13];
                        final String file_name1 = courseArray[14];
                        final String file_name2 = courseArray[15];
                        course.setAttribute("id", courseid);
                        course.setAttribute("name", coursename);
                        course.setAttribute("type", type);
                        course.setAttribute("session", session);
                        course.setAttribute("startdate", sdate);
                        course.setAttribute("enddate", edate);
                        course.setAttribute("createdby", createdby3);
                        course.setAttribute("createdon", createdon3);
                        course.setAttribute("description", description);
                        course.setAttribute("cpoints", cpoints);
                        course.setAttribute("ttimes", ttimes);
                        course.setAttribute("cost", cost);
                        course.setAttribute("intructor1", intructor1);
                        course.setAttribute("intructor2", intructor2);
                        course.setAttribute("filename1", file_name1);
                        course.setAttribute("filename2", file_name2);
                        courses.appendChild(course);
                    }
                }
            }
            if (this.choice.charAt(9) == 'T' || this.choice.charAt(11) == 'T') {
                final Vector<Vector<String>> totalCourseRegnInfo = DataBaseLayer.getCourseRegnInfo();
                if (totalCourseRegnInfo != null && totalCourseRegnInfo.size() != 0) {
                    final Element courseRegistration = this.dom.createElement("courseregistration");
                    systemexport.appendChild(courseRegistration);
                    final Vector<String> totalCourseUserRegnInfo = totalCourseRegnInfo.elementAt(0);
                    if (totalCourseUserRegnInfo != null && totalCourseUserRegnInfo.size() != 0) {
                        final Element courseUserRegistration = this.dom.createElement("courseuserregistration");
                        final int nooftotalcourseuserregn = totalCourseUserRegnInfo.size();
                        final String no_of_total_course_user_regn = Integer.toString(nooftotalcourseuserregn);
                        courseUserRegistration.setAttribute("noofcoursesregistered", no_of_total_course_user_regn);
                        courseRegistration.appendChild(courseUserRegistration);
                        for (int cu = 0; cu < nooftotalcourseuserregn; ++cu) {
                            final String courseid = totalCourseUserRegnInfo.elementAt(cu);
                            final Vector<String[]> courseUserRegnInfo = DataBaseLayer.getCourseUserRegnInfo(courseid);
                            int noofcoursesregistered = 0;
                            if (courseUserRegnInfo != null && courseUserRegnInfo.size() != 0) {
                                noofcoursesregistered = courseUserRegnInfo.size();
                                final String noofusersregistered = Integer.toString(noofcoursesregistered);
                                final Element course2 = this.dom.createElement("course");
                                course2.setAttribute("id", courseid);
                                course2.setAttribute("noofusersregistered", noofusersregistered);
                                courseUserRegistration.appendChild(course2);
                                for (int cr = 0; cr < noofcoursesregistered; ++cr) {
                                    final Element user4 = this.dom.createElement("user");
                                    final String[] userCourseArray = courseUserRegnInfo.elementAt(cr);
                                    final String userid4 = userCourseArray[0];
                                    final String registeredby3 = userCourseArray[1];
                                    final String registeredon = userCourseArray[2];
                                    final String modifiedon2 = userCourseArray[3];
                                    user4.setAttribute("id", userid4);
                                    user4.setAttribute("registeredby", registeredby3);
                                    user4.setAttribute("registeredon", registeredon);
                                    user4.setAttribute("modifiedon", modifiedon2);
                                    course2.appendChild(user4);
                                }
                            }
                        }
                    }
                    final Vector<String> totalCourseGroupRegnInfo = totalCourseRegnInfo.elementAt(1);
                    if (totalCourseGroupRegnInfo != null && totalCourseGroupRegnInfo.size() != 0) {
                        final Element courseGroupRegistration = this.dom.createElement("coursegroupregistration");
                        final int nooftotalcoursegroupregn = totalCourseGroupRegnInfo.size();
                        final String no_of_total_course_group_regn = Integer.toString(nooftotalcoursegroupregn);
                        courseGroupRegistration.setAttribute("noofcoursesregistered", no_of_total_course_group_regn);
                        courseRegistration.appendChild(courseGroupRegistration);
                        for (int cg = 0; cg < nooftotalcoursegroupregn; ++cg) {
                            final String courseid2 = totalCourseGroupRegnInfo.elementAt(cg);
                            final Vector<String[]> courseGroupRegnInfo = DataBaseLayer.getCourseGroupRegnInfo(courseid2);
                            int noofgroupssregistered = 0;
                            if (courseGroupRegnInfo != null && courseGroupRegnInfo.size() != 0) {
                                noofgroupssregistered = courseGroupRegnInfo.size();
                                final String noofgroupsregistered = Integer.toString(noofgroupssregistered);
                                final Element course3 = this.dom.createElement("course");
                                course3.setAttribute("id", courseid2);
                                course3.setAttribute("noofgroupsregistered", noofgroupsregistered);
                                courseGroupRegistration.appendChild(course3);
                                for (int gr = 0; gr < noofgroupssregistered; ++gr) {
                                    final Element group4 = this.dom.createElement("group");
                                    final String[] groupCourseArray = courseGroupRegnInfo.elementAt(gr);
                                    final String groupid3 = groupCourseArray[0];
                                    final String registeredby2 = groupCourseArray[1];
                                    final String registeredon2 = groupCourseArray[2];
                                    final String accessallowedtill = groupCourseArray[3];
                                    final String totalaccesstime = groupCourseArray[4];
                                    final String modifiedon3 = groupCourseArray[5];
                                    group4.setAttribute("id", groupid3);
                                    group4.setAttribute("registeredby", registeredby2);
                                    group4.setAttribute("registeredon", registeredon2);
                                    group4.setAttribute("accessallowedtill", accessallowedtill);
                                    group4.setAttribute("totalaccesstime", totalaccesstime);
                                    group4.setAttribute("modifiedon", modifiedon3);
                                    course3.appendChild(group4);
                                }
                            }
                        }
                    }
                }
            }
            if (this.choice.charAt(5) == 'T' || this.choice.charAt(11) == 'T') {
                final Vector<String[]> courseCollectionInfo = DataBaseLayer.getCourseCollectionInfo();
                final Element collections = this.dom.createElement("collections");
                int noofcollections = 0;
                if (courseCollectionInfo != null && courseCollectionInfo.size() != 0) {
                    noofcollections = courseCollectionInfo.size();
                    final String no_of_collections = Integer.toString(noofcollections);
                    collections.setAttribute("noofcollections", no_of_collections);
                    systemexport.appendChild(collections);
                    for (int c = 0; c < noofcollections; ++c) {
                        final Element collection = this.dom.createElement("collection");
                        final String[] collectionArray = courseCollectionInfo.elementAt(c);
                        final String collection_id = collectionArray[0];
                        final String collection_name = collectionArray[1];
                        final String description2 = collectionArray[2];
                        final String modifiedby2 = collectionArray[3];
                        final String lastmodified = collectionArray[4];
                        collection.setAttribute("id", collection_id);
                        collection.setAttribute("name", collection_name);
                        collection.setAttribute("description", description2);
                        collection.setAttribute("modifiedby", modifiedby2);
                        collection.setAttribute("lastmodified", lastmodified);
                        collections.appendChild(collection);
                        final Vector<String[]> courseCollectionAssnInfo = DataBaseLayer.getCourseCollectionAssnInfo(collection_id);
                        final Element courses2 = this.dom.createElement("courses");
                        int noofcourses2 = 0;
                        if (courseCollectionAssnInfo != null && courseCollectionAssnInfo.size() != 0) {
                            noofcourses2 = courseCollectionAssnInfo.size();
                            final String no_of_courses2 = Integer.toString(noofcourses2);
                            courses2.setAttribute("noofcourses", no_of_courses2);
                            collection.appendChild(courses2);
                            for (int co = 0; co < noofcourses2; ++co) {
                                final Element course4 = this.dom.createElement("course");
                                final String[] courseArray2 = courseCollectionAssnInfo.elementAt(co);
                                final String course_id = courseArray2[0];
                                final String associatedby = courseArray2[1];
                                final String associatedon = courseArray2[2];
                                course4.setAttribute("id", course_id);
                                course4.setAttribute("associatedby", associatedby);
                                course4.setAttribute("associatedon", associatedon);
                                courses2.appendChild(course4);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("Exception:");
            e.printStackTrace();
        }
    }
    
    private void getDocument() {
        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = dbf.newDocumentBuilder();
            this.dom = builder.newDocument();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    
    private void generateXML() throws Exception {
        final OutputFormat format = new OutputFormat(this.dom);
        format.setLineWidth(200);
        format.setIndenting(true);
        format.setIndent(2);
        final StringWriter sr = new StringWriter();
        final XMLSerializer serial = new XMLSerializer((Writer)sr, format);
        serial.asDOMSerializer();
        serial.serialize(this.dom);
        final String str1 = sr.toString();
        final File f1 = new File(this.outputfile);
        final String dest = this.outputdir + File.separator + this.outputfile.substring(this.outputfile.lastIndexOf(File.separator) + 1, this.outputfile.lastIndexOf("."));
        System.out.println("DIR : " + dest);
        final File outDir = new File(dest);
        final String generatedxml = dest + File.separator + "systemexport.xml";
        if (this.deleteDirectory(outDir)) {
            System.out.println("*****The contents of the temp directory " + outDir + " Deleted");
        }
        if (this.deleteDirectory(f1)) {
            System.out.println("*****The file " + f1 + " Deleted");
        }
        if (!outDir.exists()) {
            outDir.mkdir();
        }
        final OutputStream os = new FileOutputStream(generatedxml);
        final byte[] buf = str1.getBytes();
        os.write(buf);
        os.close();
        final String srcFolder = dest;
        final FolderZiper fz = new FolderZiper();
        fz.zipFolder(srcFolder, this.outputfile);
        fz.close();
    }
    
    public static byte[] BytesFromFile(final File file) throws IOException {
        final InputStream is = new FileInputStream(file);
        final long length = file.length();
        if (length > 2147483647L) {
            System.out.println("Sorry! Your given file is too large.");
            System.exit(0);
        }
        final byte[] bytes = new byte[(int)length];
        int offset = 0;
        for (int numRead = 0; offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0; offset += numRead) {}
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        is.close();
        return bytes;
    }
}
