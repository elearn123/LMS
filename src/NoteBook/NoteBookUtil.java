package NoteBook;

import org.grlea.log.*;
import java.util.*;

import org.directwebremoting.*;
import javax.servlet.http.*;
import learnityInit.*;
import oracle.xml.parser.v2.*;
import org.w3c.dom.*;
import org.directwebremoting.io.*;
import interfaceenginev2.*;
import java.io.*;

public class NoteBookUtil
{
    public static final SimpleLogger log;
    
    public String createDropDown(final Vector<String[]> v, final String name, final String onchange, final String selectedItem) {
        String select = "";
        final String style = "BACKGROUND-COLOR: #d6e7ef; COLOR: #000066; FONT-FAMILY: tahoma,san-serif; FONT-SIZE: x-small";
        System.out.println("v===================createDropDown=============================" + v);
        System.out.println("name===================createDropDown=============================" + name);
        System.out.println("onchange===================createDropDown=============================" + onchange);
        System.out.println("selectedItem===================createDropDown=============================" + selectedItem);
        if (v.size() == 0) {
            select = "<select style='" + style + "' name='" + name + "' onChange='" + onchange + "'><option value='0'>[Choose One]</option></select>";
        }
        else {
            String options = "<option value='0'>[Choose One]</option>";
            for (int i = 0; i < v.size(); ++i) {
                final String[] str = v.elementAt(i);
                if (str[0].equals(selectedItem)) {
                    System.out.println("id =====" + str[0] + " selectedItem =========" + selectedItem);
                    options = options + "<option value='" + str[0] + "' selected='selected'>" + str[1] + "</option>";
                    final WebContext wctx1 = WebContextFactory.get();
                    final HttpSession mysession = wctx1.getSession();
                    final String user_id = (String)mysession.getAttribute("user_id");
                }
                else {
                    options = options + "<option value='" + str[0] + "'>" + str[1] + "</option>";
                }
            }
            select = "<select  name='" + name + "' style='" + style + "' onChange='" + onchange + "'>" + options + "</select>";
        }
        return select;
    }
    
    public String createDropDownCourse() {
        String html = "";
        final String option = "";
        final Vector<String[]> vcourse = new Vector<String[]>();
        final Vector<String> vcourse2 = new Vector<String>();
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        final String courseid = (String)mysession.getAttribute("unit_id");
        final Vector<String[]> vcourse3 = DataBaseLayer.getCourseDetails(user_id);
        for (int i = 0; i < vcourse3.size(); ++i) {
            final String[] str = vcourse3.elementAt(i);
            vcourse2.addElement(str[0]);
            vcourse.addElement(str);
        }
        System.out.println("vcourse======createDropDownCourse====1====" + vcourse);
        final Vector<String[]> vcourse4 = DataBaseLayer.getCoursegroupDetails(user_id);
        for (int j = 0; j < vcourse4.size(); ++j) {
            final String[] str2 = vcourse4.elementAt(j);
            if (vcourse2.contains(str2[0])) {
                System.out.println("=============if=========");
            }
            else {
                System.out.println("========else==============");
                vcourse2.addElement(str2[0]);
                vcourse.addElement(str2);
            }
        }
        final Vector<String[]> vcourse5 = DataBaseLayer.getUnitDetailsFromCourse(user_id);
        for (int k = 0; k < vcourse5.size(); ++k) {
            final String[] str3 = vcourse5.elementAt(k);
            vcourse2.addElement(str3[0]);
            vcourse.addElement(str3);
        }
        System.out.println("vcourse======createDropDownCourse====2====" + vcourse);
        html = this.createDropDown(vcourse, "course", "courseonchange()", courseid);
        System.out.println("select========createDropDownCourse=================== " + html);
        return html;
    }
    
    public String createDropDownUser(final String user) {
        String html = "";
        System.out.println("user " + user);
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String user_id = (String)mysession.getAttribute("user_id");
        if (user == null || user.equals("0")) {
            user_id = user_id;
        }
        final Vector vst = DataBaseLayer.getStudentIdName();
        html = this.createDropDown(vst, "user", "useronchange()", "0");
        System.out.println("select " + html);
        return html;
    }
    
    public String createDropDownUnit(final String user) {
        String html = "";
        final String option = "";
        System.out.println("user " + user);
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String user_id = (String)mysession.getAttribute("user_id");
        if (user == null || user.equals("0")) {
            user_id = user_id;
        }
        else {
            user_id = user;
        }
        final Vector vunit = DataBaseLayer.getSharedCourseDetails(user_id);
        html = this.createDropDown(vunit, "unit", "sharedcourseonchange()", "");
        System.out.println("select============= " + html);
        return html;
    }
    
    public void setTreeData(String course_id, String userid, String unit, String type) {
        System.out.println("====type=setTreeData===1===" + type);
        if (course_id == null) {
            course_id = "";
        }
        if (userid == null) {
            userid = "";
        }
        if (unit == null) {
            unit = "";
        }
        if (type == null) {
            type = "personal";
        }
        if (!unit.equals("0")) {
            course_id = unit;
        }
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        if (userid == null || userid.equals("0") || userid.equals("")) {
            userid = user_id;
        }
        System.out.println("====course_id=setTreeData==== " + course_id);
        System.out.println("====userid====setTreeData=== " + userid);
        System.out.println("====unit=setTreeData===== " + unit);
        System.out.println("====type=setTreeData====2==" + type);
        mysession.setAttribute("unit_id", (Object)course_id);
        mysession.setAttribute("userid", (Object)userid);
        mysession.setAttribute("unit", (Object)unit);
        mysession.setAttribute("type", (Object)type);
    }
    
    public String getNotes(final String title, final String identifier) {
        System.out.println("=================getNotes==============");
        System.out.println("getNotes==title== " + title + " ==identifier== " + identifier);
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String courseID = (String)mysession.getAttribute("unit_id");
        System.out.println("=====getNotes============courseID===========" + courseID);
        mysession.setAttribute("identifier", (Object)identifier);
        mysession.setAttribute("courseid", (Object)courseID);
        mysession.setAttribute("coursetitle", (Object)title);
        String type = (String)mysession.getAttribute("type");
        System.out.println("===Type========" + type);
        if (type == null) {
            type = "personal";
        }
        final String user_id = (String)mysession.getAttribute("user_id");
        System.out.println("===user_id== " + user_id);
        String stdID = (String)mysession.getAttribute("userid");
        System.out.println("===userid== " + stdID);
        if (stdID == null) {
            stdID = "0";
        }
        Vector<String> v = null;
        System.out.println("===Type========" + type);
        if (type.equals("personal")) {
            v = DataBaseLayer.getSharedSubjectnotepublish(user_id, courseID, identifier);
        }
        else {
            v = DataBaseLayer.getPublishedNotes(stdID, courseID, identifier);
        }
        System.out.println("=======vNotes==== " + v);
        String notes = "";
        if (v.size() > 0) {
            notes = "<br>Notes For the Topic <b>" + title + "</b> are : <br><br><ol>";
            for (int k = 0; k < v.size(); k += 5) {
                final String strNoteTitle = v.elementAt(k);
                final String strNoteDate = v.elementAt(k + 1);
                final String strNoteBody = v.elementAt(k + 2);
                final String attach = v.elementAt(k + 3);
                final Integer note_id = Integer.parseInt(v.elementAt(k + 4));
                final String link = "<li><a href='javascript:shownotedetail(\"" + courseID + "\",\"" + title + "\",\"" + strNoteDate + "\",\"" + note_id + "\",\"" + strNoteTitle + "\",\"" + strNoteBody + "\",\"" + attach + "\")' target='_self'>" + strNoteTitle + "</a><br></li>";
                notes += link;
            }
            notes += "</ol>";
        }
        return notes;
    }
    
    public void setNoteDetails(final String courseid, final String coursetitle, final String notedate, final String noteid, final String notetitle) {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        System.out.println("==courseid== " + courseid + " ==coursetitle== " + coursetitle + " ==notedate== " + notedate + " ==noteid== " + noteid + " ==notetitle== " + notetitle);
        mysession.setAttribute("courseid", (Object)courseid);
        mysession.setAttribute("coursetitle", (Object)coursetitle);
        mysession.setAttribute("notedate", (Object)notedate);
        mysession.setAttribute("noteid", (Object)noteid);
        mysession.setAttribute("notetitle", (Object)notetitle);
    }
    
    public String showUploadFileName() {
        String str = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        str = (String)mysession.getAttribute("uploadedfile");
        return str;
    }
    
    public String getUploadFileInfo() {
        String str = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        str = (String)mysession.getAttribute("uploadedfile");
        final String user_id = (String)mysession.getAttribute("user_id");
        final String path = Host.getServerDocumentPathEngine() + "attachments" + "/" + user_id + "/" + str;
        System.out.println("============ " + path);
        return path;
    }
    
    public void addNote(final String subject, final String notes) {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String courseid = (String)mysession.getAttribute("courseid");
        String coursetitle = (String)mysession.getAttribute("coursetitle");
        String notedate = (String)mysession.getAttribute("notedate");
        String noteid = (String)mysession.getAttribute("noteid");
        String notetitle = (String)mysession.getAttribute("notetitle");
        String user_id = (String)mysession.getAttribute("user_id");
        String identifier = (String)mysession.getAttribute("identifier");
        String attachment = (String)mysession.getAttribute("uploadedfile");
        if (courseid == null) {
            courseid = "";
        }
        if (coursetitle == null) {
            coursetitle = "";
        }
        if (notedate == null) {
            notedate = "";
        }
        if (noteid == null) {
            noteid = "";
        }
        if (notetitle == null) {
            notetitle = "";
        }
        if (user_id == null) {
            user_id = "";
        }
        if (identifier == null) {
            identifier = "";
        }
        if (attachment == null) {
            attachment = "";
        }
        System.out.println("==courseid== " + courseid + " ==coursetitle== " + coursetitle + " ==notedate== " + notedate + " ==noteid== " + noteid + " ==notetitle== " + notetitle);
        if (coursetitle.equals("")) {
            System.out.println("=================No Topic Selected==============");
        }
        else {
            int note_id = DataBaseLayer.addNoteDetails(courseid, user_id, coursetitle, notedate, subject, notes, attachment, identifier, "personal");
            	noteid = Integer.toString(note_id);
            	mysession.setAttribute("noteid", noteid);
        }
        mysession.removeAttribute("uploadedfile");
    }
    
    public String reloadNotes() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String courseid = (String)mysession.getAttribute("courseid");
        String user_id = (String)mysession.getAttribute("user_id");
        String identifier = (String)mysession.getAttribute("identifier");
        String notetitle = (String)mysession.getAttribute("notetitle");
        if (courseid == null) {
            courseid = "0";
        }
        if (user_id == null) {
            user_id = "";
        }
        if (identifier == null) {
            identifier = "";
        }
        if (notetitle == null) {
            notetitle = "";
        }
        final Vector<String> v = DataBaseLayer.getSharedSubjectnotepublish(user_id, courseid, identifier);
        System.out.println("=====reloadNotes==vNotes==== " + v);
        String notes = "";
        if (v.size() > 0) {
            notes = "<br>Notes For the Topic <b>" + notetitle + "</b> are : <br><br><ol>";
            for (int k = 0; k < v.size(); k += 5) {
                final String strNoteTitle = v.elementAt(k);
                final String strNoteDate = v.elementAt(k + 1);
                final String strNoteBody = v.elementAt(k + 2);
                final String attach = v.elementAt(k + 3);
                final Integer note_id = Integer.parseInt(v.elementAt(k + 4));
                final String link = "<li><a href='javascript:shownotedetail(\"" + courseid + "\",\"" + notetitle + "\",\"" + strNoteDate + "\",\"" + note_id + "\",\"" + strNoteTitle + "\",\"" + strNoteBody + "\",\"" + attach + "\")' target='_self'>" + strNoteTitle + "</a><br></li>";
                notes += link;
            }
            notes += "</ol>";
        }
        return notes;
    }
    
    public void removeNote() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String courseid = (String)mysession.getAttribute("courseid");
        String coursetitle = (String)mysession.getAttribute("coursetitle");
        String noteid = (String)mysession.getAttribute("noteid");
        String user_id = (String)mysession.getAttribute("user_id");
        String attachment = (String)mysession.getAttribute("uploadedfile");
        if (courseid == null) {
            courseid = "";
        }
        if (coursetitle == null) {
            coursetitle = "";
        }
        if (noteid == null) {
            noteid = "";
        }
        if (user_id == null) {
            user_id = "";
        }
        if (attachment == null) {
            attachment = "";
        }
        System.out.println("==courseid== " + courseid + " ==noteid== " + noteid);
        if (coursetitle.equals("")) {
            System.out.println("=================No Topic Selected==============");
        }
        else {
            DataBaseLayer.removeNoteIdDetails(courseid, user_id, noteid);
            if (!attachment.equals("")) {
                final String pathname = Host.getServerDocumentPath() + File.separatorChar + "attachments" + File.separatorChar + user_id + File.separatorChar + attachment;
                final File filedel = new File(pathname);
                filedel.delete();
            }
        }
    }
    
    public void editNote(final String subject, final String notes) {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String courseid = (String)mysession.getAttribute("courseid");
        String coursetitle = (String)mysession.getAttribute("coursetitle");
        String noteid = (String)mysession.getAttribute("noteid");
        String user_id = (String)mysession.getAttribute("user_id");
        String attachment = (String)mysession.getAttribute("uploadedfile");
        if (courseid == null) {
            courseid = "";
        }
        if (coursetitle == null) {
            coursetitle = "";
        }
        if (noteid == null) {
            noteid = "";
        }
        if (user_id == null) {
            user_id = "";
        }
        if (attachment == null) {
            attachment = "";
        }
        System.out.println("==courseid== " + courseid + " ==coursetitle== " + coursetitle + " ==noteid== " + noteid);
        if (coursetitle.equals("")) {
            System.out.println("=================No Topic Selected==============");
        }
        else {
            DataBaseLayer.modifyNotedetails(courseid, user_id, subject, notes, attachment, noteid);
        }
    }
    
    public void publishNote() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String courseid = (String)mysession.getAttribute("courseid");
        String coursetitle = (String)mysession.getAttribute("coursetitle");
        String noteid = (String)mysession.getAttribute("noteid");
        String user_id = (String)mysession.getAttribute("user_id");
        String attachment = (String)mysession.getAttribute("uploadedfile");
        final String identifier = (String)mysession.getAttribute("identifier");
        if (courseid == null) {
            courseid = "";
        }
        if (coursetitle == null) {
            coursetitle = "";
        }
        if (noteid == null) {
            noteid = "";
        }
        if (user_id == null) {
            user_id = "";
        }
        if (attachment == null) {
            attachment = "";
        }
        System.out.println("==courseid== " + courseid + " ==noteid== " + noteid + "===coursetitle== " + coursetitle + "===identifier== " + identifier);
        if (courseid.equals("")) {
            System.out.println("=================No Topic Selected==============");
        }
        else {
            DataBaseLayer.AddpublishNote(courseid, user_id, noteid, identifier, "publish");
        }
    }
    
    public String getAttachmentPath(final String note_id, final String attachment) {
        final String user = DataBaseLayer.getUser(note_id);
        final String path = Host.getServerDocumentPathEngine() + "attachments" + "/" + user + "/" + attachment;
        System.out.println("============ " + path);
        return path;
    }
    
    public String treeConstruct() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String courseid = (String)mysession.getAttribute("unit_id");
        String userId = (String)mysession.getAttribute("userid");
        String unit = (String)mysession.getAttribute("unit");
        String type = (String)mysession.getAttribute("type");
        System.out.println("======courseid===parse=====" + courseid);
        if (courseid == null) {
            courseid = "";
        }
        if (userId == null) {
            userId = "";
        }
        if (unit == null) {
            unit = "0";
        }
        if (type == null) {
            type = "personal";
        }
        if (!unit.equals("0")) {
            courseid = unit;
        }
        System.out.println("======courseid===treeConstruct==2===" + courseid);
        final XMLDocument manifest = DataBaseLayer.parse(courseid, "csformat");
        String strTreeNodes = "";
        String title = "";
        if (manifest == null) {
            System.out.println("========No Data Found=====treeConstruct=======");
        }
        else {
            final NodeList nl = manifest.getElementsByTagName("organization");
            if (nl.getLength() > 0) {
                String orgTitle = ((Element)nl.item(0)).getElementsByTagName("title").item(0).getFirstChild().getNodeValue();
                System.out.println("=====orgTitle==treeConstruct== " + orgTitle);
                try {
                    final byte[] result = orgTitle.getBytes();
                    orgTitle = new String(result, "utf-8");
                }
                catch (Exception e) {
                    System.out.println("Error:" + e);
                }
                title = title + "<br>" + orgTitle;
                System.out.println(orgTitle + "<br>");
                for (int i = 0; i < nl.getLength(); ++i) {
                    final Element el = (Element)nl.item(i);
                    strTreeNodes = this.showTreeInOutputStream(courseid, el);
                }
            }
        }
        System.out.println("=====strTreeNodes==treeConstruct=== " + strTreeNodes);
        return strTreeNodes;
    }
    
    public String showTreeInOutputStream(final String course_id, final Element el1) {
        String str = "";
        final NodeList nl = el1.getChildNodes();
        NamedNodeMap nnm = null;
        String tagname = null;
        Node nodeItem = null;
        Node resourceNode = null;
        String strIdentifier = null;
        final String resourseRef = null;
        String strItemTitle = null;
        final String strResourceLocation = null;
        str += "\n<ul>";
        if (nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); ++i) {
                nodeItem = nl.item(i);
                tagname = ((Element)nodeItem).getTagName();
                if (tagname.equalsIgnoreCase("item")) {
                    nnm = nodeItem.getAttributes();
                    for (int j = 0; j < nnm.getLength(); ++j) {
                        resourceNode = nnm.item(j);
                        if (resourceNode.getNodeName().equalsIgnoreCase("identifier")) {
                            strIdentifier = resourceNode.getNodeValue();
                        }
                    }
                    strItemTitle = nodeItem.getFirstChild().getFirstChild().getNodeValue();
                    System.out.println("strItemTitle=== " + strItemTitle);
                    System.out.println("strIdentifier=== " + strIdentifier);
                    final Element el2 = (Element)nl.item(i);
                    nodeItem = nl.item(0);
                    tagname = ((Element)nodeItem).getTagName();
                    if (tagname.equalsIgnoreCase("item")) {
                        str = str + "\n<li data=\"key: '" + strIdentifier + "',tooltip: '" + strItemTitle + "', description: '" + strItemTitle + "' \">" + strItemTitle;
                    }
                    else {
                        str = str + "\n<li data=\"key: '" + strIdentifier + "',tooltip: '" + strItemTitle + "', description: '" + strItemTitle + "' \">" + strItemTitle;
                    }
                    strItemTitle = null;
                    final String s1 = this.showTreeInOutputStream1(course_id, el2);
                    str = str + "\n " + s1;
                }
            }
        }
        str += "\n</ul>";
        return str;
    }
    
    public String showTreeInOutputStream1(final String course_id, final Element el1) {
        String str1 = "";
        final NodeList nl = el1.getChildNodes();
        NamedNodeMap nnm = null;
        String tagname = null;
        Node nodeItem = null;
        Node resourceNode = null;
        String strIdentifier = null;
        final String resourseRef = null;
        String strItemTitle = null;
        final String strResourceLocation = null;
        if (nl.getLength() > 0) {
            for (int i1 = 0; i1 < nl.getLength(); ++i1) {
                nodeItem = nl.item(i1);
                tagname = ((Element)nodeItem).getTagName();
                if (tagname.equalsIgnoreCase("item")) {
                    str1 += "\n<ul>";
                    for (int j = 0; j < nl.getLength(); ++j) {
                        nodeItem = nl.item(j);
                        tagname = ((Element)nodeItem).getTagName();
                        if (tagname.equalsIgnoreCase("item")) {
                            nnm = nodeItem.getAttributes();
                            for (int k = 0; k < nnm.getLength(); ++k) {
                                resourceNode = nnm.item(k);
                                if (resourceNode.getNodeName().equalsIgnoreCase("identifier")) {
                                    strIdentifier = resourceNode.getNodeValue();
                                }
                            }
                            strItemTitle = nodeItem.getFirstChild().getFirstChild().getNodeValue();
                            str1 = str1 + "\n<li data=\"key: '" + strIdentifier + "',tooltip: '" + strItemTitle + "', description: '" + strItemTitle + "'\">" + strItemTitle;
                            final Element el2 = (Element)nl.item(j);
                            strItemTitle = null;
                            final String s1 = this.showTreeInOutputStream1(course_id, el2);
                            str1 = str1 + "\n " + s1;
                        }
                    }
                    str1 += "\n</ul>";
                }
            }
        }
        else {
            str1 = str1 + "\n<li data=\"key: '" + strIdentifier + "',tooltip: '" + strItemTitle + "', description: '" + strItemTitle + "' \">" + strItemTitle;
        }
        return str1;
    }
    
    public String testnote(final String id) {
        return id;
    }
    
    public String createDropDownUnitadmin(final String user_id) {
        String html = "";
        System.out.println("user_id ===" + user_id);
        final Vector<Vector<String>> vunit = DataBaseLayer.getUnitDetailsFromCourse1(user_id);
        if (vunit.size() == 0) {
            html = "<option value='0'>[Choose One]</option>";
        }
        else {
            html += "<option value='0'>[Choose One]</option>";
            for (int i = 0; i < vunit.size(); ++i) {
                final Vector<String> vu = vunit.elementAt(i);
                final String id = vu.elementAt(0);
                final String unitname = vu.elementAt(1);
                html = html + "<option value='" + id + "'>" + unitname + "</option>";
            }
        }
        System.out.println("html ===" + html);
        return html;
    }
    
    public void setTreeDataadmin(String userid, String course_id) {
        if (course_id == null) {
            course_id = "";
        }
        if (userid == null) {
            userid = "";
        }
        System.out.println("====course_id= setTreeData= " + course_id);
        System.out.println("====userid= setTreeData= " + userid);
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        mysession.setAttribute("unit_id", (Object)course_id);
        mysession.setAttribute("userid", (Object)userid);
    }
    
    public String treeConstructadmin() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String courseid = (String)mysession.getAttribute("unit_id");
        String userId = (String)mysession.getAttribute("userid");
        System.out.println("======courseid===treeConstruct=====" + courseid);
        if (courseid == null) {
            courseid = "";
        }
        if (userId == null) {
            userId = "";
        }
        final XMLDocument manifest = DataBaseLayer.parse1(courseid, "csformat");
        String strTreeNodes = "";
        String title = "";
        if (manifest == null) {
            System.out.println("========No Data Found=====treeConstruct=======");
        }
        else {
            final NodeList nl = manifest.getElementsByTagName("organization");
            if (nl.getLength() > 0) {
                String orgTitle = ((Element)nl.item(0)).getElementsByTagName("title").item(0).getFirstChild().getNodeValue();
                System.out.println("=====orgTitle==treeConstruct== " + orgTitle);
                try {
                    final byte[] result = orgTitle.getBytes();
                    orgTitle = new String(result, "utf-8");
                }
                catch (Exception e) {
                    System.out.println("Error:" + e);
                }
                title = title + "<br>" + orgTitle;
                System.out.println(orgTitle + "<br>");
                for (int i = 0; i < nl.getLength(); ++i) {
                    final Element el = (Element)nl.item(i);
                    strTreeNodes = this.showTreeInOutputStreamadmin(courseid, el);
                }
            }
        }
        System.out.println("==xxx===strTreeNodes==treeConstruct=== " + strTreeNodes);
        return strTreeNodes;
    }
    
    public String showTreeInOutputStreamadmin(final String course_id, final Element el1) {
        String str = "";
        final NodeList nl = el1.getChildNodes();
        NamedNodeMap nnm = null;
        String tagname = null;
        Node nodeItem = null;
        Node resourceNode = null;
        String strIdentifier = null;
        final String resourseRef = null;
        String strItemTitle = null;
        final String strResourceLocation = null;
        str += "\n<ul>";
        System.out.println("======nl.getLength()=====showTreeInOutputStreamadmin=======" + nl.getLength());
        if (nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); ++i) {
                System.out.println("i=====showTreeInOutputStreamadmin====" + i);
                nodeItem = nl.item(i);
                tagname = ((Element)nodeItem).getTagName();
                System.out.println("tagname=====showTreeInOutputStreamadmin====" + tagname);
                if (tagname.equalsIgnoreCase("item")) {
                    nnm = nodeItem.getAttributes();
                    System.out.println("nnm.getLength()=====showTreeInOutputStreamadmin====" + nnm.getLength());
                    for (int j = 0; j < nnm.getLength(); ++j) {
                        System.out.println("j=====showTreeInOutputStreamadmin====" + j);
                        resourceNode = nnm.item(j);
                        if (resourceNode.getNodeName().equalsIgnoreCase("identifier")) {
                            strIdentifier = resourceNode.getNodeValue();
                        }
                    }
                    strItemTitle = nodeItem.getFirstChild().getFirstChild().getNodeValue();
                    System.out.println("strItemTitle=== " + strItemTitle);
                    System.out.println("strIdentifier=== " + strIdentifier);
                    final Element el2 = (Element)nl.item(i);
                    nodeItem = nl.item(0);
                    tagname = ((Element)nodeItem).getTagName();
                    if (tagname.equalsIgnoreCase("item")) {
                        str = str + "\n<li data=\"key: '" + strIdentifier + "',tooltip: '" + strItemTitle + "', description: '" + strItemTitle + "' \">" + strItemTitle;
                    }
                    else {
                        str = str + "\n<li data=\"key: '" + strIdentifier + "',tooltip: '" + strItemTitle + "', description: '" + strItemTitle + "' \">" + strItemTitle;
                    }
                    strItemTitle = null;
                    final String s1 = this.showTreeInOutputStreamadmin1(course_id, el2);
                    str = str + "\n " + s1;
                }
            }
        }
        str += "\n</ul>";
        System.out.println("==xxx===str==showTreeInOutputStreamadmin=== " + str);
        return str;
    }
    
    public String showTreeInOutputStreamadmin1(final String course_id, final Element el1) {
        String str1 = "";
        final NodeList nl = el1.getChildNodes();
        NamedNodeMap nnm = null;
        String tagname = null;
        Node nodeItem = null;
        Node resourceNode = null;
        String strIdentifier = null;
        final String resourseRef = null;
        String strItemTitle = null;
        final String strResourceLocation = null;
        System.out.println("======nl.getLength()=====showTreeInOutputStreamadmin1=======" + nl.getLength());
        if (nl.getLength() > 0) {
            for (int i1 = 0; i1 < nl.getLength(); ++i1) {
                nodeItem = nl.item(i1);
                tagname = ((Element)nodeItem).getTagName();
                System.out.println("tagname=====showTreeInOutputStreamadmin1====" + tagname);
                if (tagname.equalsIgnoreCase("item")) {
                    str1 += "\n<ul>";
                    for (int j = 0; j < nl.getLength(); ++j) {
                        System.out.println("i=====showTreeInOutputStreamadmin1====" + j);
                        nodeItem = nl.item(j);
                        tagname = ((Element)nodeItem).getTagName();
                        if (tagname.equalsIgnoreCase("item")) {
                            nnm = nodeItem.getAttributes();
                            System.out.println("nnm.getLength()=====showTreeInOutputStreamadmin1====" + nnm.getLength());
                            for (int k = 0; k < nnm.getLength(); ++k) {
                                System.out.println("j=====showTreeInOutputStreamadmin1====" + k);
                                resourceNode = nnm.item(k);
                                if (resourceNode.getNodeName().equalsIgnoreCase("identifier")) {
                                    strIdentifier = resourceNode.getNodeValue();
                                }
                            }
                            strItemTitle = nodeItem.getFirstChild().getFirstChild().getNodeValue();
                            str1 = str1 + "\n<li data=\"key: '" + strIdentifier + "',tooltip: '" + strItemTitle + "', description: '" + strItemTitle + "' \">" + strItemTitle;
                            final Element el2 = (Element)nl.item(j);
                            strItemTitle = null;
                            final String s1 = this.showTreeInOutputStreamadmin1(course_id, el2);
                            str1 = str1 + "\n " + s1;
                        }
                    }
                    str1 += "\n</ul>";
                }
            }
        }
        else {
            str1 = str1 + "\n<li data=\"key: '" + strIdentifier + "',tooltip: '" + strItemTitle + "', description: '" + strItemTitle + "' \">" + strItemTitle;
        }
        System.out.println("==xxx===str1==showTreeInOutputStreamadmin1=== " + str1);
        return str1;
    }
    
    public String getUnitName(final String unitid, final String userId) {
        String name = "";
        final boolean flag = DataBaseLayer.checkCourse1(unitid, userId);
        if (flag) {
            final String name2 = DataBaseLayer.getUnitName(unitid);
            name = "<a href=\"javascript:ff()\" style=\"text-decoration:none;\">General</a><br><br>" + name2;
        }
        else {
            name = "<br><br><font color=\"red\">The Notes Section of Selected Course  is not accessed yet by Selected User.</font>";
        }
        return name;
    }
    
    public String getDetailsNote1(final String UnitName, final String UserName) {
        String str = "";
        str += "<b>Notes</b><br><br>There are 0 note entries for The Topic General";
        return str;
    }
    
    public String getDetailsNote(final String title) {
        String str = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String UnitName = (String)mysession.getAttribute("unit_id");
        String UserName = (String)mysession.getAttribute("userid");
        System.out.println("======UnitName===treeConstruct=====" + UnitName);
        if (UnitName == null) {
            UnitName = "";
        }
        if (UserName == null) {
            UserName = "";
        }
        mysession.setAttribute("strItemTitle", (Object)title);
        str += "<b>Notes</b><br><br>";
        final Vector<String> vNoteBody = DataBaseLayer.getNoteSubject1(UserName, UnitName, title);
        final int size = vNoteBody.size() / 5;
        str = str + "There are " + size + " note entries for The Topic " + title + "<br><br>";
        for (int k = 0; k < vNoteBody.size(); k += 5) {
            final String strNoteTitle = vNoteBody.elementAt(k);
            final String strNoteDate = vNoteBody.elementAt(k + 1);
            final String strNoteBody = vNoteBody.elementAt(k + 2);
            final String attach = vNoteBody.elementAt(k + 3);
            final Integer note_id = Integer.parseInt(vNoteBody.elementAt(k + 4));
            str = str + "<a href=\"javascript:shownotes(" + note_id + ")\" style=\"text-decoration:none;\">" + strNoteTitle + "</a><br>";
        }
        return str;
    }
    
    public String[] getNoteDetails(final String note_id) {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String UnitName = (String)mysession.getAttribute("unit_id");
        String UserName = (String)mysession.getAttribute("userid");
        String title = (String)mysession.getAttribute("strItemTitle");
        System.out.println("======UnitName===treeConstruct=====" + UnitName);
        if (UnitName == null) {
            UnitName = "";
        }
        if (UserName == null) {
            UserName = "";
        }
        if (title == null) {
            title = "";
        }
        mysession.setAttribute("note_id", (Object)note_id);
        final Vector<String> vSharedBody = DataBaseLayer.getsubjectinnoteadmin1(UnitName, title, UserName, note_id);
        final String[] str = new String[4];
        final String s = Host.getServerDocumentPathEngine();
        System.out.println("s=getServerDocumentPathEngine==" + s);
        if (vSharedBody != null) {
            for (int k = 0; k < vSharedBody.size(); k += 5) {
                final String strNoteTitle = vSharedBody.elementAt(k);
                final String strNoteDate = vSharedBody.elementAt(k + 1);
                final String strNoteBody = vSharedBody.elementAt(k + 2);
                final String attach = vSharedBody.elementAt(k + 3);
                str[0] = "Date(yyyy:mm:dd) : " + strNoteDate;
                str[1] = strNoteTitle;
                str[2] = strNoteBody;
                final String p = s + "attachments" + "/" + UserName + "/" + attach;
                str[3] = "Attachments :<br><br><a href=\"" + p + "\" target=\"_blank\" style=\"text-decoration:none;\">" + attach + "</a>";
            }
        }
        return str;
    }
    
    public void deleteNote() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String UnitName = (String)mysession.getAttribute("unit_id");
        final String UserName = (String)mysession.getAttribute("userid");
        final String strNoteId = (String)mysession.getAttribute("note_id");
        DataBaseLayer.removeNoteIdDetails1(UnitName, UserName, strNoteId);
    }
    
    public void printNote() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String UnitName = (String)mysession.getAttribute("unit_id");
        final String UserName = (String)mysession.getAttribute("userid");
        final String strNoteId = (String)mysession.getAttribute("note_id");
        DataBaseLayer.AddpublishNote1(UnitName, UserName, strNoteId, "publish");
    }
    
    public FileTransfer saveNote() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String UnitName = (String)mysession.getAttribute("unit_id");
        final String UserName = (String)mysession.getAttribute("userid");
        final String title = (String)mysession.getAttribute("strItemTitle");
        final String strNoteId = (String)mysession.getAttribute("note_id");
        final Vector<String> vSharedBody = DataBaseLayer.getsubjectinnoteadmin1(UnitName, title, UserName, strNoteId);
        final String strNoteTitle = vSharedBody.elementAt(0);
        final String strNoteDate = vSharedBody.elementAt(1);
        final String strNoteBody = vSharedBody.elementAt(2);
        String attach = vSharedBody.elementAt(3);
        if (attach.equals("null")) {
            attach = "No Attachments";
        }
        final String dirName = "";
        final String source = "Note for Topic " + title + "\n\n" + "Note Date : " + strNoteDate + "\n" + "Note Subject : " + strNoteTitle + "\n" + "Note Body : " + strNoteBody + "\n" + "Attachment : " + attach + "\n";
        final InputStream sis = new StringBufferInputStream(source);
        final FileDownloaderPojo fd = new FileDownloaderPojo(sis, "", strNoteTitle + ".txt");
        return FileDownloaderPojo.returnFileFormat();
    }
    
    public String UploadAttachment(final FileTransfer file) {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String student_id = (String)mysession.getAttribute("user_id");
        if (student_id == null) {
            student_id = "0";
        }
        String dirName = "";
        final String strLocation = Host.getServerDocumentPath();
        System.out.println("===============strLocation=========" + strLocation);
        if (strLocation == null) {
            html = "Please supply uploadDir parameter";
            return html;
        }
        dirName = strLocation;
        final String[] str = { "attachments", student_id };
        File ufile = null;
        dirName = dirName + File.separator + str[0] + File.separator + str[1];
        System.out.println("dir name is:" + dirName);
        ufile = new File(dirName);
        if (!ufile.exists()) {
            ufile.mkdir();
        }
        final FileUploaderPojo fu = new FileUploaderPojo(file);
        final String MimeType = FileUploaderPojo.getMimeType();
        System.out.println("==============MimeType==========" + MimeType);
        final InputStream is = FileUploaderPojo.getInputStream();
        System.out.println("==============is=============" + is);
        final String filename = FileUploaderPojo.getFilename();
        System.out.println("==============filename=============" + filename);
        String filePath = dirName + File.separator + filename;
        File attachmentFile = new File(filePath);
        System.out.println("==============filePath==========" + filePath);
        try {
    		if (!attachmentFile.exists()) {
    			attachmentFile.createNewFile();
    		}
           final FileOutputStream fOut = new FileOutputStream(attachmentFile);
            int length = 0;
            final byte[] b = new byte[1024];
            while (is != null && (length = is.read(b)) != -1) {
            	fOut.write(b, 0, length);
            }
            fOut.flush();
            fOut.close();
        }
        catch (IOException ex) {}
        catch (Exception exp) {
            System.err.println("=========error=======" + exp.toString());
        }
        mysession.setAttribute("uploadedfile", (Object)filename);
        html = filename;
        return html;
    }
    
    public String createDropDownUserForSelected(final String user) {
        String html = "";
        System.out.println("user " + user);
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String user_id = (String)mysession.getAttribute("user_id");
        if (user == null || user.equals("0")) {
            user_id = user_id;
        }
        final Vector vst = DataBaseLayer.getStudentIdName();
        html = this.createDropDown(vst, "user", "useronchange()", user);
        System.out.println("select " + html);
        return html;
    }
    
    static {
        log = new SimpleLogger((Class)NoteBookUtil.class, true);
    }
}
