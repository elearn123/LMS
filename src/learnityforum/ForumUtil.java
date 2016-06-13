package learnityforum;

import org.grlea.log.*;
import org.directwebremoting.*;
import java.util.*;

import javax.servlet.http.*;
import learnityInit.*;
import org.directwebremoting.io.*;
import interfaceenginev2.*;
import java.io.*;

public class ForumUtil
{
    public static final SimpleLogger log;
    public static String strDisplayType;
    
    public String displayforumname() {
        final String s = (String)WebContextFactory.get().getSession().getAttribute("forum_id");
        System.out.println("FORUM ID------------------->" + s);
        final String returnforumname = ForumDataBaseLayer.returnforumname(s);
        System.out.println("FORUM NAME------------------->" + returnforumname);
        return returnforumname;
    }
    
    public String forumGrid() {
        String s = "";
        Boolean b = false;
        final ResourceBundle bundle = ResourceBundle.getBundle("portal1", Locale.getDefault());
        final String string = bundle.getString("forumname");
        final String string2 = bundle.getString("noofmess");
        final String string3 = bundle.getString("startdate");
        final String string4 = bundle.getString("lastpost");
        final String string5 = bundle.getString("noofviews");
        final String string6 = bundle.getString("noofthreadscreated");
        final String string7 = bundle.getString("noofpostmade");
        final Vector<String[]> forumName = ForumDataBaseLayer.getForumName((String)WebContextFactory.get().getSession().getAttribute("user_id"));
        ForumUtil.log.debug("   THE VECTOR   ::" + forumName);
        if (forumName.size() == 0) {
            s = s + "<tr style=\"background-color:" + (b ? "#F4F7D4" : "#D6F7D4") + ";\">" + "<td>No Forum Registered</a></td>" + "<td> </td>" + "<td> </td>" + "</tr>";
        }
        else {
            for (int i = 0; i < forumName.size(); ++i) {
                final String s2 = b ? "#F4F7D4" : "#D6F7D4";
                final String[] array = forumName.elementAt(i);
                final Vector<String> getforummessage = ForumDataBaseLayer.getforummessage(array[1]);
                final String s3 = getforummessage.elementAt(0);
                System.out.println("MESSAGE----->" + s3);
                final String s4 = array[2];
                String s5 = array[3];
                System.out.println("CREATED ON--------->" + s4);
                String s6 = getforummessage.elementAt(2);
                System.out.println("NO OF VIEWS------------->" + s6);
                String postmade = ForumDataBaseLayer.postmade(array[0]);
                System.out.println("POST MADE------------->" + postmade);
                String thread1 = ForumDataBaseLayer.thread1(array[0]);
                System.out.println("NO OF THREAD------------>" + thread1);
                if (s5 == null || s5.equals("00-00-0000 00:00")) {
                    s5 = "";
                }
                System.out.println("strForum[1]=====------------>" + array[1]);
                if (s6 == null) {
                    s6 = "";
                }
                if (thread1 == null) {
                    thread1 = "";
                }
                if (postmade == null) {
                    postmade = "";
                }
                s = s + "<tr style=\"background-color:" + s2 + ";\"><td><a href=\"javascript:forum('" + array[0] + "','" + array[1] + "');\"><b>" + array[1] + "</b></a></td>" + "<td><b>" + s4 + "</b></td>" + "<td><b>" + s5 + "</b></td>" + "<td><b>" + s3 + "</b></td>" + "<td><b>" + s6 + "</b></td>" + "<td><b>" + thread1 + "</b></td>" + "<td><b>" + postmade + "</b></td>" + "</tr>";
                b = !b;
            }
        }
        return "<font style='font-size:11px;font-family:Verdana;font-weight:bold'><a href='javascript:forum_onclick()'>grid</a>&nbsp; | &nbsp; <a href='javascript:forum_onclick()'>DashBoard</a></font><br>" + "<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"660\" id=\"myScrollTable\">" + "<thead>" + "<tr><th>" + string + "</th><th>" + string3 + "</th><th>" + string4 + "</th>" + "<th>" + string2 + "</th><th>" + string5 + "</th><th>" + string6 + "</th><th>" + string7 + "</th>" + "</tr></thead>" + "<tbody>" + s + "</tbody></table>";
    }
    
    public String forumGrid2() {
        String s = "";
        Boolean b = false;
        final HttpSession session = WebContextFactory.get().getSession();
        final String s2 = (String)session.getAttribute("user_id");
        final String s3 = (String)session.getAttribute("forum_id");
        final String returnforumname = ForumDataBaseLayer.returnforumname(s3);
        session.setAttribute("strdisplayforum", (Object)returnforumname);
        System.out.println("FORUM ID IN FORUM GRID--------->" + returnforumname);
        final Vector<String[]> threadInfo = ForumDataBaseLayer.getThreadInfo(s3);
        ForumUtil.log.debug("   THE FORUMS INSIDE   ::" + threadInfo);
        if (threadInfo.size() == 0) {
            s = s + "<tr style=\"background-color:" + (b ? "#FFFFCC" : "#FFFFFF") + ";\">" + "<td><div style=\"width:750px;text-align:center;font-family:verdana;font-size:14px;color:black;\"><b>No Message Posted</b></a></td>" + "</tr>";
        }
        else {
            for (int i = 0; i < threadInfo.size(); ++i) {
                final String s4 = b ? "#FFFFCC" : "#CCCCCC";
                final String[] array = new String[6];
                final String[] array2 = threadInfo.elementAt(i);
                Integer.toString(ForumDataBaseLayer.getForumThreadInfo(returnforumname, array2[1]).size() + 1);
                final String string = Integer.toString(ForumDataBaseLayer.getnoofmessage(returnforumname, array2[1]).size() + 1);
                System.out.println("str[2] =" + array2[2] + "  str[1] =" + array2[1]);
                s = s + "<tr style=\"background-color:" + s4 + ";\"><td><div style=\"width:295px;text-align:center;font-family:verdana;font-size:13px;\"><a href=\"javascript:show_thread('" + array2[1] + "','" + array2[2] + "');\">" + array2[2] + "</a></td>" + "<td><div style=\"width:126px;text-align:center;font-family:verdana;font-size:13px;\">" + array2[3] + "</td>" + "<td><div style=\"width:78px;text-align:center;font-family:verdana;font-size:12px;\">" + string + "</td>" + "<td><div style=\"width:78px;text-align:center;font-family:verdana;font-size:12px;\">" + array2[5] + "</td>" + "<td><div style=\"width:150px;text-align:center;font-family:verdana;font-size:12px;\">" + array2[6] + "</td>" + "</tr>";
                b = !b;
            }
        }
        return "" + "<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"660\" id=\"myScrollTable\">" + "<thead><tr></tr></thead>" + "<tbody>" + s + "</tbody></table>";
    }
    
    public String viewDropDown(final String s) {
        WebContextFactory.get().getSession().setAttribute("view_type", (Object)s);
        return s;
    }
    
    public String detailmessage() {
        String s = "";
        Boolean b = false;
        try {
            final HttpSession session = WebContextFactory.get().getSession();
            final String s2 = (String)session.getAttribute("user_id");
            final String s3 = (String)session.getAttribute("forum_id");
            ForumDataBaseLayer.returnforumname(s3);
            final String s4 = (String)session.getAttribute("selectItemId");
            String s5 = (String)session.getAttribute("thread_id");
            final String s6 = (String)session.getAttribute("replyId");
            final String s7 = (String)session.getAttribute("attachmentPath");
            final String string = Host.getServerDocumentPathEngine() + "attachments/" + "attachmentForum/" + s3;
            System.out.println("attachmentname in detailmessage===========" + string);
            if (s4 == null) {}
            if (s5 == null) {
                s5 = "";
            }
            if (session.getAttribute("thread_id") == null) {}
            System.out.println("threadId =" + s5);
            System.out.println("replyId =" + s6);
            ForumDataBaseLayer.updateForumAllMessageViews(s3);
            final Vector<String> allMessagesThreadWise = ForumDataBaseLayer.getAllMessagesThreadWise(s3);
            System.out.println("====================vThreadInfo==========" + allMessagesThreadWise);
            if (allMessagesThreadWise.size() == 0) {
                s = s + "<tr style=\"background-color:" + (b ? "#FFFFCC" : "#FFFFFF") + ";\">" + "<td><div style=\"width:750px;text-align:center;font-family:verdana;font-size:14px;color:black;\"><b>No Message Posted</b></a></td>" + "</tr>";
            }
            else {
                System.out.println("==========vThreadInfo=======" + allMessagesThreadWise);
                for (int i = 0; i < allMessagesThreadWise.size(); i += 8) {
                    final String s8 = "#E3E7E7";
                    final String s9 = allMessagesThreadWise.elementAt(i + 7);
                    s = s + "<tr><td  style=\"border-width:2px; border-style:solid; border-color:white;\"><div style=\"width:150px;height:200px;\"><table width=\"270px\"><tr style=\"background-color:" + s8 + ";\"><td><div style=\"width:270px;text-align:left;font-family:verdana;font-size:13px;\"><b>Subject:</b><font color=\"FF0000\">" + allMessagesThreadWise.elementAt(i + 2) + "<font></td></tr>" + "<tr style=\"background-color:" + s8 + ";\"><td><div style=\"width:270px;text-align:left;font-family:verdana;font-size:13px;\"><b>Posted By: </b><font color=\"FF0000\">" + allMessagesThreadWise.elementAt(i + 3) + "<font></td></tr>" + "<tr><td colspan=\"2\" width=\"100px\" height = \"100px\"><div><img width=\"100px\" height = \"100px\" alt=\"photo\" class=\"senderPhoto\" src=\"./learnityforum.Image?user_id=" + allMessagesThreadWise.elementAt(i + 6) + "\"></div></td></tr>" + "<tr style=\"background-color:" + s8 + ";\"><td><div style=\"width:270px;text-align:left;font-family:verdana;font-size:13px;\"><b>Posting Date: </b><font color=\"FF0000\">" + allMessagesThreadWise.elementAt(i + 4) + "<font></td></tr>" + "</table></div></td>" + "<td  style=\"border-width:2px; border-style:solid; border-color:white;\"><div style=\"width:250px;text-align:left;font-family:verdana;font-size:15px;\">" + allMessagesThreadWise.elementAt(i + 1) + "</td></tr>";
                    String s10 = allMessagesThreadWise.elementAt(i + 5);
                    if (s10 == null) {
                        s10 = "";
                    }
                    if (s10.equals("") || s10.equals("null")) {
                        s = s + "<tr>" + "<td colspan=\"2\" style=\"border-width:2px; border-style:solid; border-color:white;\"><div style=\"width:100%;text-align:left;font-family:verdana;font-size:16px;\">No Attachment:</td>" + "</tr>";
                    }
                    else {
                        s = s + "<tr>" + "<td colspan=\"2\" style=\"border-width:2px; border-style:solid; border-color:white;\"><div style=\"width:100%;text-align:left;font-family:verdana;font-size:16px;\">Attachment : <a href =\"" + string + "/" + s10 + "\" target=\"f_attach_win\">" + s10 + "</a></td>" + "</tr>";
                    }
                    s = s + "<tr>" + "<td colspan=\"2\" style=\"border-width:2px; border-style:solid; border-color:white;\"><div style=\"width:100%;text-align:center;font-family:verdana;font-size:16px;\"><input type=\"button\" value=\"Reply\" name=\"reply\" onclick=javascript:flat_reply(\"" + s9 + "\")></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"button\" value=\"Quote\" name=\"qoute\" onclick=javascript:flat_quote(\"" + s9 + "\")></input></td>" + "</tr>";
                    b = !b;
                    System.out.println("===============innertable==============" + s);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "" + "<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"760\" id=\"myScrollTable\">" + "<thead><tr></tr></thead>" + "<tbody>" + s + "</tbody></table>";
    }
    
    public void setParam(final String s, final String s2) {
        final HttpSession session = WebContextFactory.get().getSession();
        session.setAttribute("thread_id", (Object)s);
        ForumUtil.log.debug("===============thread_id===========" + s);
        session.setAttribute("replyId", (Object)s2);
        System.out.println(s);
        System.out.println(s2);
    }
    
    public void setSelectedIndex(final String s) {
        final HttpSession session = WebContextFactory.get().getSession();
        ForumUtil.log.debug("selectItemId== 1=" + s);
        session.setAttribute("selectItemId", (Object)s);
        session.setAttribute("replyId", (Object)"");
    }
    
    public void insertValues(final String s, final String s2, final String s3) {
        final HttpSession session = WebContextFactory.get().getSession();
        final String s4 = (String)session.getAttribute("user_id");
        final String s5 = (String)session.getAttribute("forum_id");
        final String returnforumname = ForumDataBaseLayer.returnforumname(s5);
        System.out.println("student_id   =" + s4);
        System.out.println("forum_id   =" + s5);
        System.out.println("forumname   =" + returnforumname);
        System.out.println("thread_title   =" + s);
        System.out.println("message   =" + s2);
        System.out.println("attach   =" + s3);
        final String s6 = "1";
        final String s7 = "0";
        System.out.println("ENTERS INTO INSERT VALUES FOR THREAD");
        ForumDataBaseLayer.addMessage(s, s6, s4, s7, returnforumname, s5);
        ForumDataBaseLayer.addMessageBody(s2, returnforumname, s, s3, s4, s5);
        ForumDataBaseLayer.updateForumpost(s5);
        ForumDataBaseLayer.updatenoofmessage(s5);
    }
    
    public String ShowAttachment(final FileTransfer fileTransfer) {
        final HttpSession session = WebContextFactory.get().getSession();
        final String s = (String)session.getAttribute("user_id");
        final String s2 = (String)session.getAttribute("forum_id");
        final String serverDocumentPath = Host.getServerDocumentPath();
        System.out.println("===============strLocation=========" + serverDocumentPath);
        String string = serverDocumentPath;
        final String[] array = { "attachments", "attachmentForum", s2 };
        System.out.println("dir name is:" + string);
        for (int i = 0; i < 3; ++i) {
            if (string == null) {}
            string = string + File.separator + array[i];
            final File file = new File(string);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        final FileUploaderPojo fileUploaderPojo = new FileUploaderPojo(fileTransfer);
        System.out.println("==============MimeType==========" + FileUploaderPojo.getMimeType());
        final InputStream inputStream = FileUploaderPojo.getInputStream();
        System.out.println("==============is=============" + inputStream);
        final String filename = FileUploaderPojo.getFilename();
        System.out.println("==============filename=============" + filename);
        final String string2 = string + File.separator + filename;
        System.out.println("==============dirName==========" + string2);
        try {
            System.out.println("dirName===" + string2);
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
        session.setAttribute("forum_attachment", (Object)filename);
        return filename;
    }
    
    public void destroyAttachment() {
        WebContextFactory.get().getSession().setAttribute("forum_attachment", (Object)"");
    }
    
    public void setSubject(final String s) {
        final HttpSession session = WebContextFactory.get().getSession();
        ForumUtil.log.debug("==============thread_id in setSubject======" + s);
        session.setAttribute("reply_thread_id", (Object)s);
    }
    
    public static String getSubject() {
        final HttpSession session = WebContextFactory.get().getSession();
        final String s = (String)session.getAttribute("thread_id");
        ForumUtil.log.debug("==============threadId in getSubject======" + s);
        String threadTitle = ForumDataBaseLayer.getThreadTitle(s);
        if (threadTitle == null) {
            threadTitle = "";
        }
        session.setAttribute("subject", (Object)threadTitle);
        return threadTitle;
    }
    
    public static void submitReply(String string, final String s) {
        final HttpSession session = WebContextFactory.get().getSession();
        final String s2 = (String)session.getAttribute("forum_id");
        final String s3 = (String)session.getAttribute("forum_attachment");
        final String s4 = (String)session.getAttribute("reply_thread_id");
        final String forumNameFromThread = ForumDataBaseLayer.getForumNameFromThread(s4);
        final String s5 = (String)session.getAttribute("user_id");
        final String s6 = (String)session.getAttribute("quote_id");
        final String s7 = "1";
        final String s8 = "0";
        if (s6 == "1") {
            string = "<blockquote>" + string + "</blockquote>";
        }
        ForumDataBaseLayer.addReply(s4, forumNameFromThread, s5, s, s2, s7, s8);
        ForumDataBaseLayer.addMessageBody(string, forumNameFromThread, s, s3, s5, s2);
        ForumDataBaseLayer.updateForumpost(s2);
        ForumDataBaseLayer.updatenoofmessage(s2);
        session.setAttribute("quote_id", (Object)"0");
    }
    
    public void setQuoteSubject(final String s) {
        final HttpSession session = WebContextFactory.get().getSession();
        session.setAttribute("thread_id", (Object)s);
        session.setAttribute("quote_id", (Object)"1");
    }
    
    public static void clearForum(final String s) {
        ForumDataBaseLayer.clearForum(s);
    }
    
    public static String refreshUserInfo() {
        String string = "";
        String dynamicInformationByUser = ForumDataBaseLayer.getDynamicInformationByUser();
        if (dynamicInformationByUser == null) {
            dynamicInformationByUser = "";
        }
        if (!dynamicInformationByUser.equals("")) {
            string = "Average login Time : " + dynamicInformationByUser;
        }
        return string;
    }
    
    public static void clearForumDynamicInfo() {
        ForumUtil.log.debug("===========================clearForumDynamicInfo===================");
        final HttpSession session = WebContextFactory.get().getSession();
        String s = (String)session.getAttribute("user_id");
        if (s == null) {
            s = "";
        }
        ForumDataBaseLayer.removeForumDynamicInfo(s, session.getId());
    }
    
    public String treeConstruct() {
        final HttpSession session = WebContextFactory.get().getSession();
        final String s = (String)session.getAttribute("forum_id");
        ForumDataBaseLayer.returnforumname(s);
        final String s2 = (String)session.getAttribute("selectItemId");
        String threadId1 = (String)session.getAttribute("thread_id");
        if (threadId1 == null) {
            threadId1 = ForumDataBaseLayer.getThreadId1(s);
            ForumUtil.log.debug("threadId====treeConstruct======if========" + threadId1);
        }
        final String tree = this.createTree(s, threadId1);
        System.out.println("str2====forum treeConstruct=======" + tree);
        return tree;
    }
    
    private String createTree(final String s, final String s2) {
        final Vector<String[]> tree = ForumDataBaseLayer.getTree(s, s2);
        String string = "\n<ul>";
        for (int i = 0; i < tree.size(); ++i) {
            string += treeprint(tree.elementAt(i));
        }
        final String string2 = string + "\n</ul>";
        System.out.println("==============str1===========" + string2);
        return string2;
    }
    
    private static String treeprint(final String[] array) {
        final String s = array[0];
        final String s2 = array[1];
        final String s3 = array[2];
        final String s4 = array[3];
        final String s5 = array[4];
        final String s6 = array[5];
        final String s7 = array[6];
        System.out.println("=============thread_id======" + s7);
        final Vector<String[]> treeReply = ForumDataBaseLayer.getTreeReply(s6, s7);
        String s9;
        if (treeReply.size() > 0) {
            String s8 = "\n<li data=\"key: '" + s7 + "',tooltip: '" + s + "', description: '" + s + "'\">" + s + "\n<ul>";
            for (int i = 0; i < treeReply.size(); ++i) {
                final String[] array2 = treeReply.elementAt(i);
                Integer.toString(i + 1);
                s8 += treeprint(array2);
            }
            s9 = s8 + "\n</ul>";
        }
        else {
            s9 = "\n<li data=\"key: '" + s7 + "',tooltip: '" + s + "', description: '" + s + "'\">" + s;
        }
        return s9;
    }
    
    public String detailmessage1(final String s) {
        String s2 = "";
        Boolean b = false;
        final HttpSession session = WebContextFactory.get().getSession();
        final String s3 = (String)session.getAttribute("user_id");
        final String s4 = (String)session.getAttribute("forum_id");
        ForumDataBaseLayer.returnforumname(s4);
        final String s5 = (String)session.getAttribute("selectItemId");
        String s6 = (String)session.getAttribute("thread_id");
        final String s7 = (String)session.getAttribute("replyId");
        final String s8 = (String)session.getAttribute("attachmentPath");
        final String string = Host.getServerDocumentPathEngine() + "attachments/" + "attachmentForum/" + s4;
        System.out.println("attachmentname===========" + string);
        if (s5 == null) {}
        if (s6 == null) {
            s6 = "";
        }
        if (session.getAttribute("thread_id") == null) {}
        ForumUtil.log.debug("threadId =1=" + s6);
        System.out.println("selectedthreadid =" + s);
        if (s != null) {
            s6 = s;
        }
        ForumUtil.log.debug("threadId =2=" + s6);
        ForumUtil.log.debug("replyId ==" + s7);
        ForumDataBaseLayer.updateForumMessageViews(s4, s);
        final Vector<String[]> threadMessages = ForumDataBaseLayer.getThreadMessages(s4, s);
        if (threadMessages.size() == 0) {
            s2 = s2 + "<tr style=\"background-color:" + (b ? "#FFFFCC" : "#FFFFFF") + ";\">" + "<td><div style=\"width:750px;text-align:center;font-family:verdana;font-size:14px;color:black;\"><b>No Message Posted</b></a></td>" + "</tr>";
        }
        else {
            for (int i = 0; i < threadMessages.size(); ++i) {
                final String s9 = "#E3E7E7";
                final String[] array = new String[7];
                final String[] array2 = threadMessages.elementAt(i);
                final String string2 = s2 + "<tr><td  style=\"border-width:2px; border-style:solid; border-color:white;\"><div style=\"width:100%;height:200px;\"><table><tr style=\"background-color:" + s9 + ";\"><td><div style=\"width:150px;text-align:left;font-family:verdana;font-size:13px;\"><b>Subject:</b><font color=\"FF0000\">" + array2[2] + "<font></td></tr>" + "<tr style=\"background-color:" + s9 + ";\"><td><div style=\"width:150px;text-align:left;font-family:verdana;font-size:13px;\"><b>Posted By: </b><font color=\"FF0000\">" + array2[6] + "<font></td></tr>" + "<tr><td colspan=\"2\" width=\"100px\" height = \"100px\"><div><img width=\"100px\" height = \"100px\" alt=\"photo\" class=\"senderPhoto\" src=\"./learnityforum.Image?user_id=" + array2[3] + "\"></div></td></tr>" + "<tr style=\"background-color:" + s9 + ";\"><td><div style=\"width:150px;text-align:left;font-family:verdana;font-size:13px;\"><b>Posting Date: </b><font color=\"FF0000\">" + array2[4] + "<font></td></tr>" + "</table></div></td>" + "<td  style=\"border-width:2px; border-style:solid; border-color:white;\"><div style=\"width:250px;text-align:left;font-family:verdana;font-size:15px;\">" + array2[1] + "</td></tr>";
                array2[5] = ((array2[5] == null) ? "" : array2[5]);
                String s10;
                if (array2[5].equals("") || array2[5] == null || array2[5].equals("null")) {
                    s10 = string2 + "<tr>" + "<td colspan=\"2\" style=\"border-width:2px; border-style:solid; border-color:white;\"><div style=\"width:100%;text-align:left;font-family:verdana;font-size:16px;\">No Attachment:</td>" + "</tr>";
                }
                else {
                    s10 = string2 + "<tr>" + "<td colspan=\"2\" style=\"border-width:2px; border-style:solid; border-color:white;\"><div style=\"width:100%;text-align:left;font-family:verdana;font-size:16px;\">Attachment : <a href =\"" + string + "/" + array2[5] + "\" target=\"f_attach_win\">" + array2[5] + "</a></td>" + "</tr>";
                }
                s2 = s10 + "<tr>" + "<td colspan=\"2\" style=\"border-width:2px; border-style:solid; border-color:white;\"><div style=\"width:100%;text-align:center;font-family:verdana;font-size:16px;\"><input type=\"button\" value=\"Reply\" name=\"reply\" onclick=javascript:flat_reply(\"" + s + "\")></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"button\" value=\"Quote\" name=\"qoute\" onclick=javascript:flat_quote(\"" + s + "\")></input></td>" + "</tr>";
                b = !b;
            }
            ForumUtil.log.debug("===========detailmessage1========" + s2);
        }
        return "" + "<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"760\" id=\"myScrollTable\">" + "<thead><tr></tr></thead>" + "<tbody>" + s2 + "</tbody></table>";
    }
    
    public static boolean unregisterAllForum(final String s) {
        return ForumDataBaseLayer.unregisterAllForum(s);
    }
    
    public static boolean unregisterAllForumGroup(final String s) {
        return ForumDataBaseLayer.unregisterAllForumGroup(s);
    }
    
    public String detailmessage2() {
        String s = "";
        Boolean b = false;
        final HttpSession session = WebContextFactory.get().getSession();
        final String s2 = (String)session.getAttribute("user_id");
        final String s3 = (String)session.getAttribute("forum_id");
        ForumDataBaseLayer.returnforumname(s3);
        final String s4 = (String)session.getAttribute("selectItemId");
        String s5 = (String)session.getAttribute("thread_id");
        ForumUtil.log.debug("=============threadId in detailmessage2======" + s5);
        final String s6 = (String)session.getAttribute("replyId");
        final String s7 = (String)session.getAttribute("attachmentPath");
        final String string = Host.getServerDocumentPathEngine() + "attachments/" + "attachmentForum/" + s3;
        if (s4 == null) {}
        if (s5 == null) {
            s5 = "";
        }
        if (session.getAttribute("thread_id") == null) {}
        ForumUtil.log.debug("threadId in detailmessage2=" + s5);
        ForumUtil.log.debug("threadId =2=" + s5);
        ForumUtil.log.debug("replyId ==" + s6);
        ForumDataBaseLayer.updateForumMessageViews(s3, s5);
        final Vector<String[]> threadMessages = ForumDataBaseLayer.getThreadMessages(s3, s5);
        if (threadMessages.size() == 0) {
            s = s + "<tr style=\"background-color:" + (b ? "#FFFFCC" : "#FFFFFF") + ";\">" + "<td><div style=\"width:750px;text-align:center;font-family:verdana;font-size:14px;color:black;\"><b>No Message Posted</b></a></td>" + "</tr>";
        }
        else {
            for (int i = 0; i < threadMessages.size(); ++i) {
                final String s8 = "#E3E7E7";
                final String[] array = new String[7];
                final String[] array2 = threadMessages.elementAt(i);
                final String threadId = ForumDataBaseLayer.getThreadId(array2[2]);
                final String string2 = s + "<tr><td  style=\"border-width:2px; border-style:solid; border-color:white;\"><div style=\"width:100%;height:200px;\"><table><tr style=\"background-color:" + s8 + ";\"><td><div style=\"width:150px;text-align:left;font-family:verdana;font-size:13px;\"><b>Subject:</b><font color=\"FF0000\">" + array2[2] + "<font></td></tr>" + "<tr style=\"background-color:" + s8 + ";\"><td><div style=\"width:150px;text-align:left;font-family:verdana;font-size:13px;\"><b>Posted By: </b><font color=\"FF0000\">" + array2[6] + "<font></td></tr>" + "<tr><td colspan=\"2\" width=\"100px\" height = \"100px\"><div><img width=\"100px\" height = \"100px\" alt=\"photo\" class=\"senderPhoto\" src=\"./learnityforum.Image?user_id=" + array2[3] + "\"></div></td></tr>" + "<tr style=\"background-color:" + s8 + ";\"><td><div style=\"width:150px;text-align:left;font-family:verdana;font-size:13px;\"><b>Posting Date: </b><font color=\"FF0000\">" + array2[4] + "<font></td></tr>" + "</table></div></td>" + "<td  style=\"border-width:2px; border-style:solid; border-color:white;\"><div style=\"width:250px;text-align:left;font-family:verdana;font-size:15px;\">" + array2[1] + "</td></tr>";
                array2[5] = ((array2[5] == null) ? "" : array2[5]);
                String s9;
                if (array2[5].equals("") || array2[5] == null || array2[5].equals("null")) {
                    s9 = string2 + "<tr>" + "<td colspan=\"2\" style=\"border-width:2px; border-style:solid; border-color:white;\"><div style=\"width:100%;text-align:left;font-family:verdana;font-size:16px;\">No Attachment:</td>" + "</tr>";
                }
                else {
                    s9 = string2 + "<tr>" + "<td colspan=\"2\" style=\"border-width:2px; border-style:solid; border-color:white;\"><div style=\"width:100%;text-align:left;font-family:verdana;font-size:16px;\">Attachment : <a href =\"" + string + "/" + array2[5] + "\" target=\"f_attach_win\">" + array2[5] + "</a></td>" + "</tr>";
                }
                s = s9 + "<tr>" + "<td colspan=\"2\" style=\"border-width:2px; border-style:solid; border-color:white;\"><div style=\"width:100%;text-align:center;font-family:verdana;font-size:16px;\"><input type=\"button\" value=\"Reply\" name=\"reply\" onclick=javascript:flat_reply(\"" + threadId + "\")></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"button\" value=\"Quote\" name=\"qoute\" onclick=javascript:flat_quote(\"" + threadId + "\")></input></td>" + "</tr>";
                b = !b;
            }
            ForumUtil.log.debug("===========detailmessage2========" + s);
        }
        return "" + "<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"760\" id=\"myScrollTable\">" + "<thead><tr></tr></thead>" + "<tbody>" + s + "</tbody></table>";
    }
    
    public String ShowAttachment() {
        final String s = (String)WebContextFactory.get().getSession().getAttribute("forum_attachment");
        System.out.println("forum_attachment   =  " + s);
        return s;
    }
    
    public static String getViewTypeFromSession() {
        String s = (String)WebContextFactory.get().getSession().getAttribute("view_type");
        if (s == null) {
            s = "";
        }
        return s;
    }
    
    public static String getThreadFromSession() {
        String s = (String)WebContextFactory.get().getSession().getAttribute("thread_id");
        if (s == null) {
            s = "";
        }
        return s;
    }
    
    public void setReplyThreadId(final String s) {
        final HttpSession session = WebContextFactory.get().getSession();
        ForumUtil.log.debug("============setReplyThreadId========" + s);
        session.setAttribute("reply_thread_id", (Object)s);
    }
    
    public static String getReplySubject() {
        final HttpSession session = WebContextFactory.get().getSession();
        final String s = (String)session.getAttribute("reply_thread_id");
        ForumUtil.log.debug("==============threadId in getSubject======" + s);
        String threadTitle = ForumDataBaseLayer.getThreadTitle(s);
        if (threadTitle == null) {
            threadTitle = "";
        }
        session.setAttribute("subject", (Object)threadTitle);
        return threadTitle;
    }
    
    static {
        log = new SimpleLogger((Class)ForumUtil.class, true);
        ForumUtil.strDisplayType = null;
    }
}
