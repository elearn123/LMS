package learnityadmin;

import org.xml.sax.*;
import javax.xml.parsers.*;
import org.apache.commons.codec.binary.*;
import learnityInit.*;
//import java.util.*;
import java.util.Hashtable;
import java.util.ArrayList;
import org.w3c.dom.*;
import java.io.*;

public class SystemImport
{
    private Document dom;
    private String choice;
    private String inputfile;
    
    public SystemImport() {
    }
    
    public SystemImport(final String inputfile) {
        if (inputfile == null) {
            throw new NullPointerException();
        }
        this.inputfile = inputfile;
    }
    
    public SystemImport(final String choice, final String inputfile) {
        if (inputfile == null) {
            throw new NullPointerException();
        }
        if (choice == null) {
            throw new NullPointerException();
        }
        this.inputfile = inputfile;
        this.choice = choice;
    }
    
    public void execute() throws Exception {
        this.getDocument();
        this.parseDocument();
    }
    
    private void getDocument() {
        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder db = dbf.newDocumentBuilder();
            System.out.println("Source Path : " + this.getSrc());
            this.dom = db.parse(this.getSrc());
        }
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        catch (SAXException se) {
            se.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    private void parseDocument() throws Exception {
        final Element docEle = this.dom.getDocumentElement();
        final ArrayList<String[]> arUser = new ArrayList<String[]>();
        final ArrayList<String[]> arGroup = new ArrayList<String[]>();
        final ArrayList<String[]> arUnit = new ArrayList<String[]>();
        final ArrayList<String[]> arCourse = new ArrayList<String[]>();
        final ArrayList<String[]> arCollec = new ArrayList<String[]>();
        final NodeList nl = docEle.getElementsByTagName("users");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); ++i) {
                final Element el = (Element)nl.item(i);
                final NodeList userNodeList = el.getElementsByTagName("user");
                if (userNodeList != null && userNodeList.getLength() > 0) {
                    for (int user = 0; user < userNodeList.getLength(); ++user) {
                        final String[] strUser = new String[12];
                        final Element eleUser = (Element)userNodeList.item(user);
                        strUser[0] = eleUser.getAttribute("fname");
                        strUser[1] = eleUser.getAttribute("gender");
                        strUser[2] = eleUser.getAttribute("id");
                        strUser[3] = eleUser.getAttribute("mname");
                        strUser[4] = eleUser.getAttribute("password");
                        strUser[5] = eleUser.getAttribute("role");
                        strUser[6] = eleUser.getAttribute("sname");
                        strUser[7] = eleUser.getAttribute("status");
                        strUser[8] = eleUser.getAttribute("createdon");
                        strUser[9] = eleUser.getAttribute("createdby");
                        strUser[10] = eleUser.getAttribute("modifiedon");
                        final Element e1 = (Element)eleUser.getElementsByTagName("photo").item(0);
                        String strphoto = "";
                        strphoto = eleUser.getElementsByTagName("photo").item(0).getFirstChild().getNodeValue();
                        if (!strphoto.equals("none")) {
                            final byte[] b1 = strphoto.getBytes();
                            final byte[] b2 = Base64.decodeBase64(b1);
                            final String strLocation = Host.getAdminPath();
                            final FileOutputStream out = new FileOutputStream(strLocation + File.separator + "image222.jpg");
                            out.write(b2);
                            out.close();
                            strUser[11] = "yes";
                        }
                        else {
                            strUser[11] = "no";
                        }
                        arUser.add(strUser);
                    }
                }
            }
        }
        final NodeList nlGroups = docEle.getElementsByTagName("groups");
        if (nlGroups != null && nlGroups.getLength() > 0) {
            for (int j = 0; j < nlGroups.getLength(); ++j) {
                final Element el2 = (Element)nlGroups.item(j);
                final NodeList groupNodeList = el2.getElementsByTagName("group");
                if (groupNodeList != null && groupNodeList.getLength() > 0) {
                    for (int group = 0; group < groupNodeList.getLength(); ++group) {
                        final String[] strGroup = new String[4];
                        final Element eleGroup = (Element)groupNodeList.item(group);
                        strGroup[0] = eleGroup.getAttribute("id");
                        strGroup[1] = eleGroup.getAttribute("name");
                        strGroup[2] = eleGroup.getAttribute("createdon");
                        strGroup[3] = eleGroup.getAttribute("createdby");
                        arGroup.add(strGroup);
                    }
                }
            }
        }
        final NodeList nlUserGroups = docEle.getElementsByTagName("usergroupregistration");
        final Hashtable<String, ArrayList> htUserGroup = new Hashtable<String, ArrayList>();
        if (nlUserGroups != null && nlUserGroups.getLength() > 0) {
            for (int k = 0; k < nlUserGroups.getLength(); ++k) {
                final Element el3 = (Element)nlUserGroups.item(k);
                final NodeList groupNodeList2 = el3.getElementsByTagName("group");
                if (groupNodeList2 != null && groupNodeList2.getLength() > 0) {
                    for (int group2 = 0; group2 < groupNodeList2.getLength(); ++group2) {
                        final ArrayList<String[]> arGroupUser = new ArrayList<String[]>();
                        final Element eleGroup2 = (Element)groupNodeList2.item(group2);
                        final String groupid = eleGroup2.getAttribute("id");
                        System.out.println("**groupid===:" + groupid);
                        final NodeList userNodeList2 = eleGroup2.getElementsByTagName("user");
                        if (userNodeList2 != null && userNodeList2.getLength() > 0) {
                            for (int user2 = 0; user2 < userNodeList2.getLength(); ++user2) {
                                final String[] strUser2 = { null };
                                final Element eleUser2 = (Element)userNodeList2.item(user2);
                                strUser2[0] = eleUser2.getAttribute("id");
                                System.out.println("**User Id:" + strUser2[0]);
                                arGroupUser.add(strUser2);
                            }
                        }
                        htUserGroup.put(groupid, arGroupUser);
                    }
                }
            }
        }
        final NodeList nlUnits = docEle.getElementsByTagName("units");
        if (nlUnits != null && nlUnits.getLength() > 0) {
            for (int l = 0; l < nlUnits.getLength(); ++l) {
                final Element el4 = (Element)nlUnits.item(l);
                final NodeList unitNodeList = el4.getElementsByTagName("unit");
                if (unitNodeList != null && unitNodeList.getLength() > 0) {
                    for (int unit = 0; unit < unitNodeList.getLength(); ++unit) {
                        final String[] strUnit = new String[11];
                        final Element eleUnit = (Element)unitNodeList.item(unit);
                        strUnit[0] = eleUnit.getAttribute("id");
                        strUnit[1] = eleUnit.getAttribute("name");
                        strUnit[2] = eleUnit.getAttribute("control");
                        strUnit[3] = eleUnit.getAttribute("status");
                        strUnit[4] = eleUnit.getAttribute("createdby");
                        strUnit[5] = eleUnit.getAttribute("createdon");
                        strUnit[6] = eleUnit.getAttribute("modifiedby");
                        strUnit[7] = eleUnit.getAttribute("modifiedon");
                        strUnit[8] = eleUnit.getAttribute("selfreg");
                        strUnit[9] = eleUnit.getAttribute("confreqd");
                        strUnit[10] = eleUnit.getAttribute("confirmedby");
                        arUnit.add(strUnit);
                    }
                }
            }
        }
        final NodeList nlUnitUserRegn = docEle.getElementsByTagName("unituserregistration");
        final Hashtable<String, ArrayList> htUnitUser = new Hashtable<String, ArrayList>();
        if (nlUnitUserRegn != null && nlUnitUserRegn.getLength() > 0) {
            for (int m = 0; m < nlUnitUserRegn.getLength(); ++m) {
                final Element el5 = (Element)nlUnitUserRegn.item(m);
                final NodeList unitNodeList2 = el5.getElementsByTagName("unit");
                if (unitNodeList2 != null && unitNodeList2.getLength() > 0) {
                    for (int unit2 = 0; unit2 < unitNodeList2.getLength(); ++unit2) {
                        final ArrayList<String[]> arUnitUser = new ArrayList<String[]>();
                        final Element eleUnit2 = (Element)unitNodeList2.item(unit2);
                        final String unitid = eleUnit2.getAttribute("id");
                        final NodeList userNodeList3 = eleUnit2.getElementsByTagName("user");
                        if (userNodeList3 != null && userNodeList3.getLength() > 0) {
                            for (int user3 = 0; user3 < userNodeList3.getLength(); ++user3) {
                                final String[] strUser3 = new String[3];
                                final Element eleUser3 = (Element)userNodeList3.item(user3);
                                strUser3[0] = eleUser3.getAttribute("id");
                                strUser3[1] = eleUser3.getAttribute("accessallowedtill");
                                strUser3[1] = (strUser3[1].equals("") ? "0000-00-00 00:00:00" : strUser3[1]);
                                arUnitUser.add(strUser3);
                            }
                        }
                        htUnitUser.put(unitid, arUnitUser);
                    }
                }
            }
        }
        final NodeList nlCourses = docEle.getElementsByTagName("courses");
        if (nlCourses != null && nlCourses.getLength() > 0) {
            for (int i2 = 0; i2 < nlCourses.getLength(); ++i2) {
                final Node nodeCourses = nlCourses.item(i2);
                if (nodeCourses.getParentNode().getNodeName().equals("systemexport")) {
                    final Element el6 = (Element)nodeCourses;
                    final NodeList courseNodeList = el6.getElementsByTagName("course");
                    if (courseNodeList != null && courseNodeList.getLength() > 0) {
                        for (int course = 0; course < courseNodeList.getLength(); ++course) {
                            final String[] strCourse = new String[16];
                            final Element eleCourse = (Element)courseNodeList.item(course);
                            strCourse[0] = eleCourse.getAttribute("id");
                            strCourse[1] = eleCourse.getAttribute("name");
                            strCourse[2] = eleCourse.getAttribute("cpoints");
                            strCourse[3] = eleCourse.getAttribute("createdby");
                            strCourse[4] = eleCourse.getAttribute("createdon");
                            strCourse[4] = (strCourse[4].equals("") ? "0000-00-00 00:00:00" : strCourse[4]);
                            strCourse[5] = eleCourse.getAttribute("description");
                            strCourse[6] = eleCourse.getAttribute("enddate");
                            strCourse[6] = (strCourse[6].equals("") ? "0000-00-00 00:00:00" : strCourse[6]);
                            strCourse[7] = eleCourse.getAttribute("filename1");
                            strCourse[8] = eleCourse.getAttribute("filename2");
                            strCourse[9] = eleCourse.getAttribute("intructor1");
                            strCourse[10] = eleCourse.getAttribute("intructor2");
                            strCourse[11] = eleCourse.getAttribute("cost");
                            strCourse[11] = (strCourse[11].equals("") ? "0" : strCourse[11]);
                            strCourse[12] = eleCourse.getAttribute("session");
                            strCourse[13] = eleCourse.getAttribute("startdate");
                            strCourse[13] = (strCourse[13].equals("") ? "0000-00-00 00:00:00" : strCourse[13]);
                            strCourse[14] = eleCourse.getAttribute("ttimes");
                            strCourse[14] = (strCourse[14].equals("") ? "0" : strCourse[14]);
                            strCourse[15] = eleCourse.getAttribute("type");
                            arCourse.add(strCourse);
                        }
                    }
                }
            }
        }
        final NodeList nlCourseRegn = docEle.getElementsByTagName("courseregistration");
        final Hashtable<String, ArrayList> htCourseUser = new Hashtable<String, ArrayList>();
        final Hashtable<String, ArrayList> htCourseGroup = new Hashtable<String, ArrayList>();
        if (nlCourseRegn != null && nlCourseRegn.getLength() > 0) {
            for (int i3 = 0; i3 < nlCourseRegn.getLength(); ++i3) {
                final NodeList nlCourseUserRegn = docEle.getElementsByTagName("courseuserregistration");
                if (nlCourseUserRegn != null && nlCourseUserRegn.getLength() > 0) {
                    for (int j2 = 0; j2 < nlCourseUserRegn.getLength(); ++j2) {
                        final Element el7 = (Element)nlCourseUserRegn.item(j2);
                        final NodeList courseNodeList2 = el7.getElementsByTagName("course");
                        if (courseNodeList2 != null && courseNodeList2.getLength() > 0) {
                            for (int course2 = 0; course2 < courseNodeList2.getLength(); ++course2) {
                                final ArrayList<String[]> arCourseUser = new ArrayList<String[]>();
                                final Element eleCourse2 = (Element)courseNodeList2.item(course2);
                                final String courseid = eleCourse2.getAttribute("id");
                                final NodeList userNodeList4 = eleCourse2.getElementsByTagName("user");
                                if (userNodeList4 != null && userNodeList4.getLength() > 0) {
                                    for (int user4 = 0; user4 < userNodeList4.getLength(); ++user4) {
                                        final String[] strUser4 = new String[4];
                                        final Element eleUser4 = (Element)userNodeList4.item(user4);
                                        strUser4[0] = eleUser4.getAttribute("id");
                                        strUser4[1] = eleUser4.getAttribute("modifiedon");
                                        strUser4[1] = (strUser4[1].equals("") ? "0000-00-00 00:00:00" : strUser4[1]);
                                        strUser4[2] = eleUser4.getAttribute("registeredby");
                                        strUser4[3] = eleUser4.getAttribute("registeredon");
                                        strUser4[3] = (strUser4[3].equals("") ? "0000-00-00 00:00:00" : strUser4[3]);
                                        arCourseUser.add(strUser4);
                                    }
                                }
                                htCourseUser.put(courseid, arCourseUser);
                            }
                        }
                    }
                }
                final NodeList nlCourseGroupRegn = docEle.getElementsByTagName("coursegroupregistration");
                if (nlCourseGroupRegn != null && nlCourseGroupRegn.getLength() > 0) {
                    for (int j3 = 0; j3 < nlCourseGroupRegn.getLength(); ++j3) {
                        final Element el8 = (Element)nlCourseGroupRegn.item(j3);
                        final NodeList courseNodeList3 = el8.getElementsByTagName("course");
                        if (courseNodeList3 != null && courseNodeList3.getLength() > 0) {
                            for (int course3 = 0; course3 < courseNodeList3.getLength(); ++course3) {
                                final ArrayList<String[]> arCourseGroup = new ArrayList<String[]>();
                                final Element eleCourse3 = (Element)courseNodeList3.item(course3);
                                final String courseid2 = eleCourse3.getAttribute("id");
                                final NodeList groupNodeList3 = eleCourse3.getElementsByTagName("group");
                                if (groupNodeList3 != null && groupNodeList3.getLength() > 0) {
                                    for (int group3 = 0; group3 < groupNodeList3.getLength(); ++group3) {
                                        final String[] strGroup2 = new String[5];
                                        final Element eleGroup3 = (Element)groupNodeList3.item(group3);
                                        strGroup2[0] = eleGroup3.getAttribute("id");
                                        strGroup2[1] = eleGroup3.getAttribute("accessallowedtill");
                                        strGroup2[1] = (strGroup2[1].equals("") ? "0000-00-00 00:00:00" : strGroup2[1]);
                                        strGroup2[2] = eleGroup3.getAttribute("registeredby");
                                        strGroup2[3] = eleGroup3.getAttribute("registeredon");
                                        strGroup2[3] = (strGroup2[3].equals("") ? "0000-00-00 00:00:00" : strGroup2[3]);
                                        strGroup2[4] = eleGroup3.getAttribute("modifiedon");
                                        strGroup2[4] = (strGroup2[4].equals("") ? "0000-00-00 00:00:00" : strGroup2[4]);
                                        arCourseGroup.add(strGroup2);
                                    }
                                }
                                htCourseGroup.put(courseid2, arCourseGroup);
                            }
                        }
                    }
                }
            }
        }
        final ArrayList<String[]> aForum = new ArrayList<String[]>();
        final ArrayList<String[]> aForumThread = new ArrayList<String[]>();
        final NodeList nlistforums = docEle.getElementsByTagName("forums");
        if (nlistforums != null && nlistforums.getLength() > 0) {
            for (int i4 = 0; i4 < nlistforums.getLength(); ++i4) {
                final Element el8 = (Element)nlistforums.item(i4);
                final NodeList nlistforum = el8.getElementsByTagName("forum");
                if (nlistforum != null && nlistforum.getLength() > 0) {
                    for (int f = 0; f < nlistforum.getLength(); ++f) {
                        final String[] str = new String[10];
                        final Element nforum = (Element)nlistforum.item(f);
                        str[0] = nforum.getAttribute("id");
                        str[1] = nforum.getAttribute("name");
                        str[2] = nforum.getAttribute("noofthread");
                        str[3] = nforum.getAttribute("noofmessage");
                        str[4] = nforum.getAttribute("lastmessagegpostedat");
                        str[5] = nforum.getAttribute("createdby");
                        str[6] = nforum.getAttribute("startdate");
                        str[7] = nforum.getAttribute("selfregistration");
                        str[8] = nforum.getAttribute("confirmedby");
                        str[9] = nforum.getAttribute("confirmationrequired");
                        aForum.add(str);
                        final NodeList nlistthread = nforum.getElementsByTagName("thread");
                        if (nlistthread != null && nlistthread.getLength() > 0) {
                            for (int t = 0; t < nlistthread.getLength(); ++t) {
                                final Element nthread = (Element)nlistthread.item(t);
                                final NodeList nlistmessage = nthread.getElementsByTagName("message");
                                if (nlistmessage != null && nlistmessage.getLength() > 0) {
                                    for (int m2 = 0; m2 < nlistmessage.getLength(); ++m2) {
                                        final String[] strthread = new String[10];
                                        final Element nmessage = (Element)nlistmessage.item(m2);
                                        strthread[0] = str[0];
                                        strthread[1] = str[1];
                                        strthread[2] = nmessage.getAttribute("id");
                                        strthread[3] = nmessage.getAttribute("postedby");
                                        strthread[4] = nmessage.getAttribute("postedon");
                                        strthread[5] = nmessage.getAttribute("threadtitle");
                                        strthread[6] = nmessage.getAttribute("threadview");
                                        strthread[7] = nmessage.getAttribute("parentthread");
                                        String strmessage = "";
                                        strmessage = nmessage.getElementsByTagName("messagebody").item(0).getFirstChild().getNodeValue();
                                        strthread[8] = strmessage;
                                        final NodeList nlistattachments = nmessage.getElementsByTagName("attachments");
                                        final Element nattachments = (Element)nlistattachments.item(0);
                                        final NodeList nlistattachment = nattachments.getElementsByTagName("attachment");
                                        final Element nattachment = (Element)nlistattachment.item(0);
                                        if (nattachment != null) {
                                            final String fname = nattachment.getAttribute("filename");
                                            strthread[9] = fname;
                                            final String filecontent = nattachment.getElementsByTagName("filecontent").item(0).getFirstChild().getNodeValue();
                                            final byte[] b3 = filecontent.getBytes();
                                            final byte[] b4 = Base64.decodeBase64(b3);
                                            final String strLocation2 = Host.getServerDocumentPath();
                                            File ufile = null;
                                            final String dirName = strLocation2 + File.separator + "attachments" + File.separator + "attachmentForum" + File.separator + str[0];
                                            ufile = new File(dirName);
                                            if (!ufile.exists()) {
                                                ufile.mkdir();
                                            }
                                            final String filename = strLocation2 + File.separator + "attachments" + File.separator + "attachmentForum" + File.separator + str[0] + File.separator + fname;
                                            final FileOutputStream out2 = new FileOutputStream(filename);
                                            out2.write(b4);
                                            out2.close();
                                        }
                                        else {
                                            strthread[9] = "none";
                                        }
                                        aForumThread.add(strthread);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        final ArrayList<String[]> aForumUReg = new ArrayList<String[]>();
        final NodeList nlistforuserreg = docEle.getElementsByTagName("forumuserregistration");
        if (nlistforuserreg != null && nlistforuserreg.getLength() > 0) {
            final Element el9 = (Element)nlistforuserreg.item(0);
            final NodeList nlistforum2 = el9.getElementsByTagName("forum");
            if (nlistforum2 != null && nlistforum2.getLength() > 0) {
                for (int f2 = 0; f2 < nlistforum2.getLength(); ++f2) {
                    final Element nforumreg = (Element)nlistforum2.item(f2);
                    final String forimid = nforumreg.getAttribute("id");
                    final NodeList nuserlist = nforumreg.getElementsByTagName("user");
                    if (nuserlist != null && nuserlist.getLength() > 0) {
                        for (int f3 = 0; f3 < nuserlist.getLength(); ++f3) {
                            final Element user5 = (Element)nuserlist.item(f3);
                            final String[] str2 = { forimid, user5.getAttribute("id"), user5.getAttribute("registeredby") };
                            aForumUReg.add(str2);
                        }
                    }
                }
            }
        }
        final ArrayList<String[]> aForumGrReg = new ArrayList<String[]>();
        final NodeList nlistforgrreg = docEle.getElementsByTagName("forumgroupregistration");
        if (nlistforgrreg != null && nlistforgrreg.getLength() > 0) {
            final Element el10 = (Element)nlistforgrreg.item(0);
            final NodeList nlistforum3 = el10.getElementsByTagName("forum");
            if (nlistforum3 != null && nlistforum3.getLength() > 0) {
                for (int f4 = 0; f4 < nlistforum3.getLength(); ++f4) {
                    final Element nforumreg2 = (Element)nlistforum3.item(f4);
                    final String forimid2 = nforumreg2.getAttribute("id");
                    final NodeList nuserlist2 = nforumreg2.getElementsByTagName("group");
                    if (nuserlist2 != null && nuserlist2.getLength() > 0) {
                        for (int f5 = 0; f5 < nuserlist2.getLength(); ++f5) {
                            final Element group4 = (Element)nuserlist2.item(f5);
                            final String[] str3 = { forimid2, group4.getAttribute("id"), group4.getAttribute("registeredby") };
                            aForumGrReg.add(str3);
                        }
                    }
                }
            }
        }
        final ArrayList<String[]> aNotice = new ArrayList<String[]>();
        final NodeList nlistnotices = docEle.getElementsByTagName("notices");
        if (nlistnotices != null && nlistnotices.getLength() > 0) {
            for (int i5 = 0; i5 < nlistnotices.getLength(); ++i5) {
                final Element el11 = (Element)nlistnotices.item(i5);
                final NodeList nlistnotice = el11.getElementsByTagName("notice");
                if (nlistnotice != null && nlistnotice.getLength() > 0) {
                    for (int f6 = 0; f6 < nlistnotice.getLength(); ++f6) {
                        final String[] str2 = new String[8];
                        final Element nnotice = (Element)nlistnotice.item(f6);
                        str2[0] = nnotice.getAttribute("id");
                        str2[1] = nnotice.getAttribute("heading");
                        str2[2] = nnotice.getAttribute("postedby");
                        str2[3] = nnotice.getAttribute("postedon");
                        str2[4] = nnotice.getAttribute("groupid");
                        str2[5] = nnotice.getAttribute("status");
                        final NodeList nlistnoticebody = nnotice.getElementsByTagName("noticebody");
                        if (nlistnoticebody != null && nlistnoticebody.getLength() > 0) {
                            final String noticebody = nnotice.getElementsByTagName("noticebody").item(0).getFirstChild().getNodeValue();
                            str2[6] = noticebody;
                            final NodeList nlistattachments = nnotice.getElementsByTagName("attachments");
                            final Element nattachments = (Element)nlistattachments.item(0);
                            final NodeList nlistattachment = nattachments.getElementsByTagName("attachment");
                            final Element nattachment = (Element)nlistattachment.item(0);
                            if (nattachment != null) {
                                final String fname = nattachment.getAttribute("filename");
                                str2[7] = fname;
                                final String filecontent = nattachments.getElementsByTagName("attachment").item(0).getFirstChild().getNodeValue();
                                final byte[] b3 = filecontent.getBytes();
                                final byte[] b4 = Base64.decodeBase64(b3);
                                final String strLocation2 = Host.getServerDocumentPath();
                                File ufile = null;
                                final String dirName = strLocation2 + File.separator + "attachments" + File.separator + str2[2];
                                System.out.println("dirName===" + dirName);
                                ufile = new File(dirName);
                                if (!ufile.exists()) {
                                    ufile.mkdir();
                                }
                                final String filename = strLocation2 + File.separator + "attachments" + File.separator + str2[2] + File.separator + fname;
                                System.out.println("filename===" + filename);
                                final FileOutputStream out2 = new FileOutputStream(filename);
                                out2.write(b4);
                                out2.close();
                            }
                            else {
                                str2[7] = "none";
                            }
                            aNotice.add(str2);
                        }
                    }
                }
            }
        }
        DataBaseLayer.importData(arUser, arGroup, htUserGroup, arUnit, htUnitUser, arCourse, htCourseUser, htCourseGroup, aForum, aForumThread, aForumUReg, aForumGrReg, aNotice);
        DataBaseLayer.updateStudentPhoto(arUser);
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
    
    public void setSrc(final String inputfile) {
        this.inputfile = inputfile;
    }
    
    public void setSrc(final File inputfile) {
        this.inputfile = inputfile.toString();
    }
    
    public String getSrc() {
        return this.inputfile;
    }
    
    public String getSrc(final File inputfile) {
        return this.inputfile;
    }
}
