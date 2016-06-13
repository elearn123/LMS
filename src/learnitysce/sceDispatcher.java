package learnitysce;

import org.grlea.log.*;
import java.text.*;
import org.directwebremoting.*;
import javax.servlet.http.*;
import java.net.*;
import java.util.*;

import javax.servlet.*;
import java.io.*;
import interfaceenginev2.*;

public class sceDispatcher
{
    public static final SimpleLogger log;
    private InputStream in;
    
    public String getSceGrid() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final HttpServletRequest request = wctx1.getHttpServletRequest();
        final HttpServletResponse response = wctx1.getHttpServletResponse();
        final String user_id = (String)mysession.getAttribute("user_id");
        String sceGrid = "";
        Boolean toggle = false;
        String color = "";
        final Vector<Vector<String>> vSynchronousList = SceDataBaseLayer.getSynchronousList(user_id);
        if (vSynchronousList.size() < 1) {
            color = (toggle ? "#EEE" : "#ffffff");
            sceGrid = sceGrid + "<tr style=\"background-color:" + color + ";\">" + "<td>No Synchronous Collaboration Envirnoment Registered</td>" + "<td></td>" + "<td></td>" + "<td></td>" + "<td></td>" + "</tr>";
            toggle = !toggle;
        }
        else {
            final Vector<String> vSynName = new Vector<String>();
            Vector v = null;
            for (int s = 0; s < vSynchronousList.size(); ++s) {
                final Vector<String> vSysList = vSynchronousList.elementAt(s);
                vSynName.addElement(vSysList.elementAt(1));
            }
            final String strContext = wctx1.getServletConfig().getServletContext().getServletContextName();
            String location = strContext + "/servlet/ChatServlet";
            location = location.trim();
            System.out.println("-----------Location-------->>>>>>>>----- " + location);
            try {
                String strHost = request.getServerName();
                strHost = strHost.trim();
                System.out.println(strHost);
                final int iPort = request.getServerPort();
                System.out.println(iPort);
                final URL ur = new URL("http", strHost, iPort, location);
                final URLConnection conn = ur.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setRequestProperty("Content-Type", "application/x-java-serialized-object");
                final ObjectOutputStream outputToServlet = new ObjectOutputStream(conn.getOutputStream());
                outputToServlet.writeObject(vSynName);
                outputToServlet.flush();
                outputToServlet.close();
                final ObjectInputStream in = new ObjectInputStream(conn.getInputStream());
                v = (Vector)in.readObject();
                System.out.println("-----------Location-------->>>>>>>>----- " + v);
                in.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            for (int s2 = 0; s2 < vSynchronousList.size(); ++s2) {
                final Vector<String> vSysList2 = vSynchronousList.elementAt(s2);
                final String strType = vSysList2.elementAt(4);
                final String strStart = vSysList2.elementAt(5);
                final String strEnd = vSysList2.elementAt(6);
                final String strSessionID = vSysList2.elementAt(7);
                final String strRSession = vSysList2.elementAt(8);
                final String reg_members = SceDataBaseLayer.SCERegisteredMembers(vSysList2.elementAt(0));
                final Calendar calendarnow = new GregorianCalendar();
                final Date trialTime1 = new Date();
                calendarnow.setTime(trialTime1);
                final String strTime1 = calendarnow.get(11) + ":" + calendarnow.get(12) + ":" + calendarnow.get(13);
                final String strDate1 = calendarnow.get(5) + "-" + (calendarnow.get(2) + 1) + "-" + calendarnow.get(1);
                final String strCurrent = strDate1 + " " + strTime1;
                final String myFormatString = "dd-MM-yyyy HH:mm:ss";
                final SimpleDateFormat df = new SimpleDateFormat(myFormatString);
                color = (toggle ? "#EEE" : "#ffffff");
                try {
                    Date start = null;
                    Date end = null;
                    if (!strStart.equals("")) {
                        start = df.parse(strStart);
                    }
                    if (!strEnd.equals("")) {
                        end = df.parse(strEnd);
                    }
                    final Date current = df.parse(strCurrent);
                    System.out.println("------strType--------->>>>>>>>>>>>>------------------" + strType);
                    if (strType.equals("R")) {
                        if (strStart.equals("") || strEnd.equals("")) {
                            final String RSECID = strRSession.substring(0, strRSession.indexOf(45));
                            final String strRSession2 = strRSession.substring(strRSession.indexOf(45) + 1);
                            System.out.println("-------Available-------->>>>>>>>>>>>>------------------");
                            sceGrid = sceGrid + "<tr style=\"background-color:" + color + ";\">" + "<td><div id='SceTableBodyRowcol1'><a href=\"javascript:chatLunchRecorded('" + RSECID + "','" + strRSession2 + "');\">" + vSysList2.elementAt(1) + "</div></td>" + "<td><div id='SceTableBodyRowcol2'>recorded</div></td>" + "<td><div id='SceTableBodyRowcol3'>available</div></td>" + "<td><div id='SceTableBodyRowcol4'>" + reg_members + "</div></td>" + "<td><div id='SceTableBodyRowcol5'></div></td>" + "</tr>";
                        }
                        else if (current.after(start) && current.before(end)) {
                            final String RSECID = strRSession.substring(0, strRSession.indexOf(45));
                            final String strRSession2 = strRSession.substring(strRSession.indexOf(45) + 1);
                            System.out.println("-------Not Available-------->>>>>>>>>>>>>------------------");
                            sceGrid = sceGrid + "<tr style=\"background-color:" + color + ";\">" + "<td><div id='SceTableBodyRowcol1'><a href=\"javascript:chatLunchRecorded('" + RSECID + "','" + strRSession2 + "');\">" + vSysList2.elementAt(1) + "</div></td>" + "<td><div id='SceTableBodyRowcol2'>recorded</div></td>" + "<td><div id='SceTableBodyRowcol3'>available</div></td>" + "<td><div id='SceTableBodyRowcol4'>" + reg_members + "</div></td>" + "<td><div id='SceTableBodyRowcol5'></div></td>" + "</tr>";
                        }
                    }
                    else if (strStart.equals("") || strEnd.equals("")) {
                        final String RSECID = strRSession.substring(0, strRSession.indexOf(45));
                        final String strRSession2 = strRSession.substring(strRSession.indexOf(45) + 1);
                        sceGrid = sceGrid + "<tr style=\"background-color:" + color + ";\">" + "<td><div id='SceTableBodyRowcol1'><a href=\"javascript:chatLunchRecorded('" + RSECID + "','" + strRSession2 + "');\">" + vSysList2.elementAt(1) + "</div></td>" + "<td><div id='SceTableBodyRowcol2'>recorded</div></td>" + "<td><div id='SceTableBodyRowcol3'>available</div></td>" + "<td><div id='SceTableBodyRowcol4'>" + reg_members + "</div></td>" + "<td><div id='SceTableBodyRowcol5'></div></td>" + "</tr>";
                    }
                    else if (current.before(start)) {
                        System.out.println("-------current.before(start)-------->>>>>>>>>>>>>------------------");
                        sceGrid = sceGrid + "<tr style=\"background-color:" + color + ";\">" + "<td><div id='SceTableBodyRowcol1'><a href=\"javascript:chatLunch('" + vSysList2.elementAt(0) + "','" + strSessionID + "');\">" + vSysList2.elementAt(1) + "</div></td>" + "<td><div id='SceTableBodyRowcol2'>live</div></td>" + "<td><div id='SceTableBodyRowcol3'>started</div></td>" + "<td><div id='SceTableBodyRowcol4'>" + reg_members + "</div></td>" + "<td><div id='SceTableBodyRowcol5'>" + v.elementAt(s2) + "</div></td>" + "</tr>";
                    }
                    else if (current.after(start) && current.before(end)) {
                        sceGrid = sceGrid + "<tr style=\"background-color:" + color + ";\">" + "<td><div id='SceTableBodyRowcol1'><a href=\"javascript:chatLunch('" + vSysList2.elementAt(0) + "','" + strSessionID + "');\">" + vSysList2.elementAt(1) + "</div></td>" + "<td><div id='SceTableBodyRowcol2'>live</div></td>" + "<td><div id='SceTableBodyRowcol3'>started</div></td>" + "<td><div id='SceTableBodyRowcol4'>" + reg_members + "</div></td>" + "<td><div id='SceTableBodyRowcol5'>" + v.elementAt(s2) + "</div></td>" + "</tr>";
                    }
                    else if (current.after(end)) {
                        sceGrid = sceGrid + "<tr style=\"background-color:" + color + ";\">" + "<td><div id='SceTableBodyRowcol1'><a href=\"javascript:chatLunch('" + vSysList2.elementAt(0) + "','" + strSessionID + "');\">" + vSysList2.elementAt(1) + "</div></td>" + "<td><div id='SceTableBodyRowcol2'>live</div></td>" + "<td><div id='SceTableBodyRowcol3'>started</div></td>" + "<td><div id='SceTableBodyRowcol4'>" + reg_members + "</div></td>" + "<td><div id='SceTableBodyRowcol5'>" + v.elementAt(s2) + "</div></td>" + "</tr>";
                    }
                }
                catch (Exception ex) {
                    System.out.println("ParseException" + ex);
                    ex.printStackTrace();
                }
            }
            toggle = !toggle;
        }
        final String html = "<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"660\" id=\"myScrollTable\"><thead><tr><td>SCE Name</td><td>Type</td><td>Status</td><td>No. of Registered Members</td><td>No. of Members Online</td></tr></thead><tbody>" + sceGrid + "</tbody></table>";
        return html;
    }
    
    public String getMentorResultView(String pollId) {
        String resultTable = "<table cellspacing=\"0\" width=\"40%\" border=\"1\" cellpadding=\"2\"><tr><td colspan=\"3\"><div style=\"background-color: #A52A2A; color: #FFCC00;text-align:center; font-family: tahoma,san-serif; font-size: 24px; font-weight: bold;width:480px;\">Polls AND Quizzes: </div></td></tr>";
        final WebContext wctx = WebContextFactory.get();
        final HttpSession mysession = wctx.getSession();
        final HttpServletRequest request = wctx.getHttpServletRequest();
        final HttpServletResponse response = wctx.getHttpServletResponse();
        String user_id = (String)mysession.getAttribute("user_id");
        String sceId = (String)mysession.getAttribute("sce_id");
        user_id = ((user_id == null) ? "" : user_id);
        sceId = ((sceId == null) ? "" : sceId);
        pollId = ((pollId == null) ? "" : pollId);
        if (sceId.equals("") || pollId.equals("")) {
            return "<div style=\"background-color: #876954; color: #FFCC00;text-align:center; font-family: tahoma,san-serif; font-size: 24px; font-weight: bold;height:160px;width:449px;left:30px;top:70px;position:absolute;\"><br>Synchronous Collaboration expired <br>or not initiated</div>";
        }
        try {
            Vector<Vector<String>> vResultsdetails = new Vector<Vector<String>>(4, 4);
            vResultsdetails = SceDataBaseLayer.getResultdetails(pollId);
            if (vResultsdetails == null) {
                return "<div style=\"background-color: #A52A2A; color: #FFCC00;text-align:center; font-family: tahoma,san-serif; font-size: 24px; font-weight: bold;height:160px;width:449px;left:60px;top:70px;position:absolute;\"><br>Synchronous Collaboration expired <br>or not initiated</div>";
            }
            final Vector<String> vResults1 = vResultsdetails.elementAt(0);
            final String polltitle = vResults1.elementAt(0);
            final Vector<String> vResults2 = vResultsdetails.elementAt(2);
            final Integer Alltotalvotes = Integer.parseInt(vResults2.lastElement());
            final String Alltotalvotes2 = Alltotalvotes.toString();
            final Vector<String> vResults3 = vResultsdetails.elementAt(1);
            resultTable = resultTable + "<tr><td colspan=\"3\"><div style=\"background-color:#CCCCCC;color:#6699CC;text-align:center;text-decoration:underline; font-family: tahoma,san-serif; font-size: 14px; font-weight: bold; height:30px;width:560px;display: table-cell;vertical-align: middle;\">Results for&nbsp;" + polltitle + "</div></td></tr>" + "\n<tr><td style=\"background-color: #FFCC00;color: #339999;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width:60%;\">Choice</td>" + "\n<td style=\"background-color: #FFCC00;color: #339999;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width:60%;\">Votes</td>" + "\n<td style=\"background-color: #FFCC00;color: #339999;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width:60%;\">Percentage</td></tr>";
            if (vResults3 != null) {
                String totalvotes2;
                Integer votepercentage;
                String choicetext;
                for (int k = 0; k < vResults3.size(); k += 2, resultTable = resultTable + "\n<tr><td style=\"background-color: #FFFFCC;color: #9999FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 60%;\">" + choicetext + "</td>" + "\n<td style=\"background-color: #FFFFCC;color: #9999FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 20%;\">" + totalvotes2 + "</td>" + "\n<td style=\"background-color: #FFFFCC;color: #9999FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 20%;\">" + votepercentage + "%</td></tr>", ++k) {
                    final Integer choiceid = Integer.parseInt(vResults3.elementAt(k));
                    final Integer totalvotes = Integer.parseInt(vResults3.elementAt(k + 1));
                    totalvotes2 = totalvotes.toString();
                    votepercentage = 0;
                    if (Alltotalvotes != 0) {
                        votepercentage = totalvotes * 100 / Alltotalvotes;
                    }
                    choicetext = vResults3.elementAt(k + 2);
                }
                if (Alltotalvotes != 0) {
                    resultTable = resultTable + "<tr><td style=\"background-color: #C0C0C0;color: #0099FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 60%;\">Total</td><td style=\"background-color: #C0C0C0;color: #0099FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 20%;\">" + Alltotalvotes2 + "</td>" + "<td style=\"background-color: #C0C0C0;color: #0099FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 20%;\">100%</td></div></tr>";
                }
                else {
                    resultTable = resultTable + "<tr><td style=\"background-color: #C0C0C0;color: #0099FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 60%;\">Total</td><td style=\"background-color: #C0C0C0;color: #0099FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 20%;\">" + Alltotalvotes2 + "</td>" + "<td style=\"background-color: #C0C0C0;color: #0099FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 20%;\">0%</td></div></tr>";
                }
            }
        }
        catch (Exception e) {
            return "<div style=\"background-color: #A52A2A; color: #FFCC00;text-align:center; font-family: tahoma,san-serif; font-size: 24px; font-weight: bold;height:160px;width:449px;left:60px;top:70px;position:absolute;\"><br>Synchronous Collaboration expired <br>or not initiated</div>";
        }
        return resultTable + "</table>";
    }
    
    public String AddPolls(final String pollid, final String PollTitle, final String Polltext, final String Choicetype, final String Choicetext1, final String Choicetext2, final String Choicetext3) {
        String sceid = "";
        final WebContext wctx = WebContextFactory.get();
        final HttpSession mysession = wctx.getSession();
        sceid = (String)mysession.getAttribute("sce_id");
        sceid = ((sceid == null) ? "" : sceid);
        SceDataBaseLayer.PollingDetails(pollid, PollTitle, Polltext, Choicetype);
        SceDataBaseLayer.PollingChoices(pollid, Choicetext1);
        SceDataBaseLayer.PollingChoices(pollid, Choicetext2);
        SceDataBaseLayer.PollingChoices(pollid, Choicetext3);
        SceDataBaseLayer.Pollstatus(sceid, pollid, "Inactivated");
        return "";
    }
    
    public String PollActivation(final String pollid) {
        final WebContext wctx = WebContextFactory.get();
        final HttpSession mysession = wctx.getSession();
        String sce_id = (String)mysession.getAttribute("sce_id");
        mysession.setAttribute("pollid", (Object)pollid);
        sce_id = ((sce_id == null) ? "" : sce_id);
        SceDataBaseLayer.modifypollstatus(sce_id, pollid, "Activated");
        SceDataBaseLayer.insertPollResult(pollid);
        return "";
    }
    
    public String PollPublished(final String pollid) {
        final WebContext wctx = WebContextFactory.get();
        final HttpSession mysession = wctx.getSession();
        String sce_id = (String)mysession.getAttribute("sce_id");
        sce_id = ((sce_id == null) ? "" : sce_id);
        SceDataBaseLayer.PublishedResult(sce_id, pollid, "Published");
        return "";
    }
    
    public String setSessionSceId(final String sce_id, final String browser) throws ServletException, IOException {
        final String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        mysession.setAttribute("sce_id", (Object)sce_id);
        mysession.setAttribute("browser", (Object)browser);
        return "";
    }
    
    public String setSceTitle() throws ServletException, IOException {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final HttpServletRequest req = wctx1.getHttpServletRequest();
        final String ip = req.getRemoteAddr();
        System.out.println("======================ip===============" + ip);
        final String sce_id = (String)mysession.getAttribute("sce_id");
        final String user_id = (String)mysession.getAttribute("user_id");
        final PortalEngine pe = new PortalEngine();
        pe.ChangeGridLoadQuery("PollsandQuiz", "scePnQDefinition", "sceid", sce_id);
        System.out.println("================sce_id=============" + sce_id);
        final String scename = SceDataBaseLayer.getSceName(sce_id);
        SceDataBaseLayer.Insert_IP_Addr(sce_id, user_id, ip);
        html = "<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" id=\"myScrollTable\"><thead><tr><td><b>SCE Title :</b></td><td><b>" + scename + "</b></td></tr></thead></table>";
        return html;
    }
    
    public String setScePnQPublishedPolls() throws ServletException, IOException {
        String html = "";
        String s = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String sce_id = (String)mysession.getAttribute("sce_id");
        final Vector published_poll = SceDataBaseLayer.getPublishedPoll(sce_id, "Published");
        if (published_poll.size() != 0) {
            for (int i = 0; i < published_poll.size(); i += 2) {
                s = s + "<tr><td><input type=\"radio\" name=\"Qchoices\" onclick=\"javascript:checkbox_onclick('" + published_poll.elementAt(i) + "')\" value=\"" + published_poll.elementAt(i) + "\"</td>" + "<td width=\"80%\">" + published_poll.elementAt(i + 1) + "</td></tr>";
            }
        }
        else {
            s += "<center>No Polls has been Published</center>";
        }
        html = "<table border=\"0\" id=\"myPublishTable\">" + s + "</table>";
        System.out.println("===========html=====in setScePnQPublishedPolls=======" + html);
        return html;
    }
    
    public String setSessionpubpollid(final String poll_id) throws ServletException, IOException {
        final String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        mysession.setAttribute("pubpoll_id", (Object)poll_id);
        return "";
    }
    
    public String setSceViewResult() throws ServletException, IOException {
        String html = "";
        String s = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String sce_id = (String)mysession.getAttribute("sce_id");
        final String pubpoll_id = (String)mysession.getAttribute("pubpoll_id");
        System.out.println("===========pubpoll_id========" + pubpoll_id);
        final Vector<Vector<String>> vResultsdetails = SceDataBaseLayer.getResultdetails(pubpoll_id);
        if (vResultsdetails.size() != 0) {
            String countvote = "";
            final Vector<String> vResults1 = vResultsdetails.elementAt(0);
            final String polltitle = vResults1.elementAt(0);
            final Vector<String> vResults2 = vResultsdetails.elementAt(2);
            final Integer Alltotalvotes = Integer.parseInt(vResults2.lastElement());
            if (Alltotalvotes != 0) {
                countvote = "100%";
            }
            else {
                countvote = "0%";
            }
            final String Alltotalvotes2 = Alltotalvotes.toString();
            System.out.println("*********PollResults_Alltotalvotes**********************" + Alltotalvotes);
            System.out.println("*********PollResults_polltitle**********************" + polltitle);
            final Vector<String> vResults3 = vResultsdetails.elementAt(1);
            s += "<tr><td>Choice</td><td>Votes</td><td>Percentage</td></tr>";
            if (vResults3 != null) {
                String totalvotes2;
                Integer votepercentage;
                String choicetext;
                for (int k = 0; k < vResults3.size(); k += 2, s = s + "<tr><td>" + choicetext + "</td><td>" + totalvotes2 + "</td><td>" + votepercentage + "%</td></tr>", ++k) {
                    System.out.println("********vResults2.size()************" + vResults3.size());
                    System.out.println("********vResults2************" + vResults3);
                    final Integer choiceid = Integer.parseInt(vResults3.elementAt(k));
                    System.out.println("*********PollResults_choiceid**********************" + choiceid);
                    final Integer totalvotes = Integer.parseInt(vResults3.elementAt(k + 1));
                    totalvotes2 = totalvotes.toString();
                    if (Alltotalvotes != 0) {
                        votepercentage = totalvotes * 100 / Alltotalvotes;
                    }
                    else {
                        votepercentage = 0;
                    }
                    System.out.println("*********PollResults_totalvotes**********************" + totalvotes);
                    choicetext = vResults3.elementAt(k + 2);
                    System.out.println("*********PollResults_choicetext**********************" + choicetext);
                }
            }
            s = s + "<tr><td>Total</td><td>" + Alltotalvotes2 + "</td><td>" + countvote + "</td></tr>";
        }
        else {
            s += "<center></center>";
        }
        html = "<table border=\"0\" width=\"100%\" id=\"myPollViewTable\">" + s + "</table>";
        System.out.println("===========html=====in setScePnQPublishedPolls=======" + html);
        return html;
    }
    
    public String setSessionPollId(final String pollid) {
        final WebContext wctx = WebContextFactory.get();
        final HttpSession mysession = wctx.getSession();
        String sce_id = (String)mysession.getAttribute("sce_id");
        mysession.setAttribute("pollid", (Object)pollid);
        sce_id = ((sce_id == null) ? "" : sce_id);
        final PortalEngine pe = new PortalEngine();
        pe.ChangeGridLoadQuery("PnQDefinition", "scePnQDefinitionGrid", "pollid", pollid);
        SceDataBaseLayer.modifypollstatus(sce_id, pollid, "Activated");
        SceDataBaseLayer.insertPollResult(pollid);
        return "";
    }
    
    public String getStudentResultView() {
        String resultTable = "<table cellspacing=\"0\" width=\"100%\" border=\"1\" cellpadding=\"2\">";
        final WebContext wctx = WebContextFactory.get();
        final HttpSession mysession = wctx.getSession();
        final HttpServletRequest request = wctx.getHttpServletRequest();
        final HttpServletResponse response = wctx.getHttpServletResponse();
        String user_id = (String)mysession.getAttribute("user_id");
        String sceId = (String)mysession.getAttribute("sce_id");
        String pollId = (String)mysession.getAttribute("pubpoll_id");
        user_id = ((user_id == null) ? "" : user_id);
        sceId = ((sceId == null) ? "" : sceId);
        pollId = ((pollId == null) ? "" : pollId);
        if (sceId.equals("") || pollId.equals("")) {
            return "<div style=\"background-color: #876954; color: #FFCC00;text-align:center; font-family: tahoma,san-serif; font-size: 24px; font-weight: bold;height:160px;width:449px;left:30px;top:70px;position:absolute;\"><br>Synchronous Collaboration expired <br>or not initiated</div>";
        }
        try {
            Vector<Vector<String>> vResultsdetails = new Vector<Vector<String>>(4, 4);
            vResultsdetails = SceDataBaseLayer.getResultdetails(pollId);
            if (vResultsdetails == null) {
                return "<div style=\"background-color: #A52A2A; color: #FFCC00;text-align:center; font-family: tahoma,san-serif; font-size: 24px; font-weight: bold;height:160px;width:449px;left:60px;top:70px;position:absolute;\"><br>Synchronous Collaboration expired <br>or not initiated</div>";
            }
            final Vector<String> vResults1 = vResultsdetails.elementAt(0);
            final String polltitle = vResults1.elementAt(0);
            final Vector<String> vResults2 = vResultsdetails.elementAt(2);
            final Integer Alltotalvotes = Integer.parseInt(vResults2.lastElement());
            final String Alltotalvotes2 = Alltotalvotes.toString();
            final Vector<String> vResults3 = vResultsdetails.elementAt(1);
            resultTable = resultTable + "<tr><td colspan=\"3\"><div style=\"background-color:#CCCCCC;color:#6699CC;text-align:center;text-decoration:underline; font-family: tahoma,san-serif; font-size: 14px; font-weight: bold; height:30px;width:560px;display: table-cell;vertical-align: middle;\">Results for&nbsp;" + polltitle + "</div></td></tr>" + "\n<tr><td style=\"background-color: #FFCC00;color: #339999;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width:60%;\">Choice</td>" + "\n<td style=\"background-color: #FFCC00;color: #339999;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width:60%;\">Votes</td>" + "\n<td style=\"background-color: #FFCC00;color: #339999;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width:60%;\">Percentage</td></tr>";
            if (vResults3 != null) {
                String totalvotes2;
                Integer votepercentage;
                String choicetext;
                for (int k = 0; k < vResults3.size(); k += 2, resultTable = resultTable + "\n<tr><td style=\"background-color: #FFFFCC;color: #9999FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 60%;\">" + choicetext + "</td>" + "\n<td style=\"background-color: #FFFFCC;color: #9999FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 20%;\">" + totalvotes2 + "</td>" + "\n<td style=\"background-color: #FFFFCC;color: #9999FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 20%;\">" + votepercentage + "%</td></tr>", ++k) {
                    final Integer choiceid = Integer.parseInt(vResults3.elementAt(k));
                    final Integer totalvotes = Integer.parseInt(vResults3.elementAt(k + 1));
                    totalvotes2 = totalvotes.toString();
                    votepercentage = 0;
                    if (Alltotalvotes != 0) {
                        votepercentage = totalvotes * 100 / Alltotalvotes;
                    }
                    choicetext = vResults3.elementAt(k + 2);
                }
                if (Alltotalvotes != 0) {
                    resultTable = resultTable + "<tr><td style=\"background-color: #C0C0C0;color: #0099FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 60%;\">Total</td><td style=\"background-color: #C0C0C0;color: #0099FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 20%;\">" + Alltotalvotes2 + "</td>" + "<td style=\"background-color: #C0C0C0;color: #0099FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 20%;\">100%</td></div></tr>";
                }
                else {
                    resultTable = resultTable + "<tr><td style=\"background-color: #C0C0C0;color: #0099FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 60%;\">Total</td><td style=\"background-color: #C0C0C0;color: #0099FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 20%;\">" + Alltotalvotes2 + "</td>" + "<td style=\"background-color: #C0C0C0;color: #0099FF;text-align: center; font-family: \"Times New Roman\", \"Times\", serif; font-size: 14px; font-weight: bold;width: 20%;\">0%</td></div></tr>";
                }
            }
        }
        catch (Exception e) {
            return "<div style=\"background-color: #A52A2A; color: #FFCC00;text-align:center; font-family: tahoma,san-serif; font-size: 24px; font-weight: bold;height:160px;width:449px;left:60px;top:70px;position:absolute;\"><br>Synchronous Collaboration expired <br>or not initiated</div>";
        }
        return resultTable + "</table>";
    }
    
    public String getSessionpubpollid() throws ServletException, IOException {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        html = (String)mysession.getAttribute("pubpoll_id");
        return html;
    }
    
    public String getChatChecked() throws ServletException, IOException {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        final String sce_id = (String)mysession.getAttribute("sce_id");
        final String check_value = SceDataBaseLayer.getChatChecked(sce_id);
        if (check_value.equals("T")) {
            html = "Y";
        }
        else {
            html = "N";
        }
        System.out.println("================html=================" + html);
        return html;
    }
    
    public String getDesktopSharingChecked() throws ServletException, IOException {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        final String sce_id = (String)mysession.getAttribute("sce_id");
        final String check_value = SceDataBaseLayer.getDesktopSharingChecked(sce_id);
        if (check_value.equals("T")) {
            html = "Y";
        }
        else {
            html = "N";
        }
        return html;
    }
    
    public String getVideoChecked() throws ServletException, IOException {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        final String sce_id = (String)mysession.getAttribute("sce_id");
        final String check_value = SceDataBaseLayer.getVideoChecked(sce_id);
        if (check_value.equals("T")) {
            html = "Y";
        }
        else {
            html = "N";
        }
        return html;
    }
    
    public String getActivePole() throws ServletException, IOException {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final HttpServletRequest req = wctx1.getHttpServletRequest();
        final String sce_id = (String)mysession.getAttribute("sce_id");
        final String user_id = (String)mysession.getAttribute("user_id");
        html = SceDataBaseLayer.GetActivePole(sce_id);
        return html;
    }
    
    static {
        log = new SimpleLogger((Class)sceDispatcher.class, true);
    }
}
