package LearnityNotice;

import org.grlea.log.*;
import org.directwebremoting.*;
import javax.servlet.http.*;
import java.util.*;

import javax.servlet.*;
import learnityInit.*;
import org.directwebremoting.io.*;
import interfaceenginev2.*;
import java.io.*;

public class NoticePOJO
{
    public static final SimpleLogger log;
    
    public String getDropDown() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        String html = "<option value=\"0\">ALL</option>";
        final Vector<String> getDrop = DataBaseLayer.getGroupDropDown();
        for (int i = 0; i < getDrop.size(); i += 2) {
            final String g_id = getDrop.elementAt(i);
            final String g_name = getDrop.elementAt(i + 1);
            html = html + "<option value=\"" + g_id + "\">" + g_name + "</option>";
        }
        return html;
    }
    
    public Vector getNotice() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        final Vector<String> vNoticeInfo = DataBaseLayer.getNoticeInfo(user_id);
        NoticePOJO.log.debug("--------------------vNoticeInfo------------------" + vNoticeInfo);
        final String html = "";
        String innertable = "";
        final Vector<String> Details = new Vector<String>();
        Boolean tt = false;
        String student_id = "";
        String heading = "";
        String posted_on = "";
        String notice_id = "";
        try {
            for (int i = 0; i < vNoticeInfo.size(); i += 6) {
                NoticePOJO.log.debug("------------------------------------------");
                student_id = vNoticeInfo.elementAt(i);
                student_id = ((student_id != null) ? student_id : "");
                heading = vNoticeInfo.elementAt(i + 1);
                posted_on = vNoticeInfo.elementAt(i + 4);
                notice_id = vNoticeInfo.elementAt(i + 5);
                NoticePOJO.log.debug("-----student_id,-heading-,posted_on--11-------------" + student_id + " , " + heading + " , " + posted_on);
                String postedby = "";
                if (notice_id != null) {
                    postedby = DataBaseLayer.returnnoticepostedby(notice_id);
                    NoticePOJO.log.debug("postedby-------------------------" + postedby);
                    final String color = tt ? "#EEE" : "#ffffff";
                    innertable = "{notice_id:\"" + notice_id + "\",headings:\"<a href='javascript:lunchNotice(" + notice_id + ")'>" + heading + "</a>\",posted_by:\"" + postedby + "\",posted_on:\"" + posted_on + "\"}";
                    Details.addElement(innertable);
                    tt = !tt;
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error:" + e);
        }
        final Vector<String> vNoticeInfo2 = DataBaseLayer.getNoticeInfo1(user_id);
        for (int j = 0; j < vNoticeInfo2.size(); j += 6) {
            NoticePOJO.log.debug("------------------------------------------");
            student_id = vNoticeInfo2.elementAt(j);
            student_id = ((student_id != null) ? student_id : "");
            heading = vNoticeInfo2.elementAt(j + 1);
            posted_on = vNoticeInfo2.elementAt(j + 4);
            notice_id = vNoticeInfo2.elementAt(j + 5);
            NoticePOJO.log.debug("-----student_id,-heading-,posted_on--11-------------" + student_id + " , " + heading + " , " + posted_on);
            String postedby2 = "";
            if (notice_id != null) {
                postedby2 = DataBaseLayer.returnnoticepostedby(notice_id);
                NoticePOJO.log.debug("postedby-------------------------" + postedby2);
                final String color2 = tt ? "#EEE" : "#ffffff";
                innertable = "{notice_id:\"" + notice_id + "\",headings:\"<a href='javascript:lunchNotice(" + notice_id + ")'>" + heading + "</a>\",posted_by:\"" + postedby2 + "\",posted_on:\"" + posted_on + "\"}";
                Details.addElement(innertable);
                tt = !tt;
            }
        }
        final String script = "<script type=\"text/javascript\">var t= new ScrollableTable(document.getElementById('myScrollTable'),100) </script>";
        final String html2 = "<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"640px\"  style=\"position:absolute;\"  id=\"myScrollTable\"><thead><tr><th>Heading(s)</th><th>Posted By</th><th>Posted On</th><tbody>" + innertable + "</tbody></table>" + script;
        return Details;
    }
    
    public String setNoticeHead() throws ServletException, IOException {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        final String notice_id = (String)mysession.getAttribute("notice_id");
        html = DataBaseLayer.returnnoticename(notice_id);
        return html;
    }
    
    public String setNoticeBody() throws ServletException, IOException {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        final String notice_id = (String)mysession.getAttribute("notice_id");
        html = DataBaseLayer.returnnoticebody(notice_id);
        NoticePOJO.log.debug("html-----" + html);
        return html;
    }
    
    public String setNoticePostedBy() throws ServletException, IOException {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        final String notice_id = (String)mysession.getAttribute("notice_id");
        html = DataBaseLayer.returnnoticepostedby(notice_id);
        NoticePOJO.log.debug("html-----" + html);
        return html;
    }
    
    public String setNoticePostedOn() throws ServletException, IOException {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        final String notice_id = (String)mysession.getAttribute("notice_id");
        html = DataBaseLayer.returnnoticepostedon(notice_id);
        NoticePOJO.log.debug("html-----" + html);
        return html;
    }
    
    public String setAttachFileName() throws ServletException, IOException {
        String html = "";
        final String str = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        final String notice_id = (String)mysession.getAttribute("notice_id");
        String created_by = DataBaseLayer.getCreatedBy(notice_id);
        created_by = ((created_by != null) ? created_by : "");
        html = DataBaseLayer.returnAttachName(notice_id);
        html = ((html != null) ? html : "");
        final String path = Host.getServerDocumentPathEngine() + "attachments/" + created_by + "/" + html;
        System.out.println("============ " + path);
        html = "<a href=\"" + path + "\" target=\"notice_attach_frame\">" + html + "</a>";
        System.out.println("html-----" + html);
        return html;
    }
    
    public String addNewNotice(final String noticeheading, final String noticebody, final String group) {
        final String html = "";
        String user_id = "";
        String attachname = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        try {
            user_id = (String)mysession.getAttribute("user_id");
            attachname = (String)mysession.getAttribute("noticeattach");
        }
        catch (Exception e) {
            System.out.println("exception in  NoticePOJO.addNewNotice(String noticeheading,String noticebody)");
        }
        DataBaseLayer.addNotice(noticeheading, noticebody, user_id, attachname, group);
        mysession.removeAttribute("noticeattach");
        mysession.removeAttribute("notice_id");
        NoticePOJO.log.debug("html-----" + html);
        return html;
    }
    
    public String showUploadFileName() {
        String str = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        str = (String)mysession.getAttribute("noticeattach");
        System.out.println("-str------------------------" + str);
        return str;
    }
    
    public String showUploadFileName2() {
        String str = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        final String notice_id = (String)mysession.getAttribute("notice_id");
        System.out.println("-notice_id------------------------" + notice_id);
        str = DataBaseLayer.returnAttachName(notice_id);
        str = ((str != null) ? str : "");
        System.out.println("-str------------------------" + str);
        return str;
    }
    
    public String getUploadFileInfo() {
        String str = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        str = (String)mysession.getAttribute("noticeattach");
        final String user_id = (String)mysession.getAttribute("user_id");
        final String notice_id = (String)mysession.getAttribute("notice_id");
        String created_by = DataBaseLayer.getCreatedBy(notice_id);
        created_by = ((created_by != null) ? created_by : "");
        final String path = Host.getServerDocumentPathEngine() + "attachments/" + created_by + "/" + str;
        NoticePOJO.log.debug("============ " + path);
        return path;
    }
    
    public void setSessionNoticeId(final String notice_id) {
        final String html = "";
        System.out.println("NOTICE ID------------->" + notice_id);
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        mysession.setAttribute("notice_id", (Object)notice_id);
    }
    
    public String ShowAttachment(final FileTransfer file) {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String dirName = "";
        String stdID = (String)mysession.getAttribute("user_id");
        System.out.println("stdID------111-------------------------" + stdID);
        if (stdID == null) {
            stdID = "0";
        }
        File ufile = null;
        final String attachmentname = Host.getServerDocumentPath();
        final String getUtil = Host.getUtil();
        System.out.println("attachmentname------222-------------------------" + attachmentname);
        System.out.println("getUtil------222-------------------------" + getUtil);
        dirName = attachmentname + "attachments" + File.separatorChar + stdID;
        NoticePOJO.log.debug("======dirName==== " + dirName);
        if (dirName == null) {
            NoticePOJO.log.debug("Please supply uploadDir parameter");
        }
        else {
            ufile = new File(dirName);
            if (!ufile.exists()) {
                NoticePOJO.log.debug("=======creating directory====");
                ufile.mkdir();
            }
            final String thisLine = "";
            final FileUploaderPojo _tmp;
            final FileUploaderPojo fu = _tmp = new FileUploaderPojo(file);
            final String MimeType = FileUploaderPojo.getMimeType();
            System.out.println("==============MimeType==========" + MimeType);
            final FileUploaderPojo _tmp2 = fu;
            final InputStream is = FileUploaderPojo.getInputStream();
            System.out.println("==============is=============" + is);
            final FileUploaderPojo _tmp3 = fu;
            final String filename = FileUploaderPojo.getFilename();
            System.out.println("==============filename=============" + filename);
            dirName = dirName + File.separator + filename;
            final String content = "";
            System.out.println("==============dirName==========" + dirName);
            try {
                System.out.println("dirName===" + dirName);
                final FileOutputStream out = new FileOutputStream(dirName);
                int length = 0;
                final byte[] b = new byte[1024];
                while (is != null && (length = is.read(b)) != -1) {
                    out.write(b, 0, length);
                }
                final PrintStream p = new PrintStream(out);
                p.close();
            }
            catch (IOException ioexception) {
                NoticePOJO.log.debug(ioexception.toString() + " error reading or saving file");
            }
            mysession.setAttribute("noticeattach", (Object)filename);
            html = filename;
        }
        return html;
    }
    
    static {
        log = new SimpleLogger((Class)NoticePOJO.class, true);
    }
}
