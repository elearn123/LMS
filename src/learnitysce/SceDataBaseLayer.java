package learnitysce;

import javax.sql.*;
import java.sql.*;
import org.grlea.log.*;
import java.util.*;

import comv2.aunwesha.param.*;

public class SceDataBaseLayer
{
    private static final SimpleLogger log;
    public static DataSource ds1;
    
    public static synchronized String getMentorName(final String plan_id) {
        String Vcompo = null;
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement1 = oConn.createStatement();
            final ResultSet oRset = statement1.executeQuery("select ledsession from synchronous_collaboration where id='" + plan_id + "'");
            while (oRset.next()) {
                Vcompo = oRset.getString(1);
            }
            statement1.close();
            oConn.commit();
            oConn.setAutoCommit(true);
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.debug("Exception in SceDatabaseLayer.getMentorName()");
            SceDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug("Exception in SceDatabaseLayer.selectPort()");
            SceDataBaseLayer.log.debug("The Error Message - " + ex.getMessage());
        }
        return Vcompo;
    }
    
    public static synchronized String getChatChecked(final String strSCEId) {
        Statement oStmt = null;
        ResultSet oRset = null;
        String flag = null;
        Connection oConn = null;
        try {
            oConn = SceDataBaseLayer.ds1.getConnection();
            oStmt = oConn.createStatement();
            oRset = oStmt.executeQuery("select ifnull(chat,\"null\") from synchronous_collaboration where id = '" + strSCEId + "'");
            if (oRset.next()) {
                flag = oRset.getString(1);
            }
            if (flag == null) {
                flag = "F";
            }
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("Error due to SQL exception : " + e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug(" error due to java.lang.exception");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
            SceDataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        finally {
            try {
                if (oRset != null) {
                    oRset.close();
                }
                if (oStmt != null) {
                    oStmt.close();
                }
                if (oConn != null) {
                    oConn.close();
                }
            }
            catch (Exception ex2) {}
        }
        return flag;
    }
    
    public static synchronized String getDesktopSharingChecked(final String strSCEId) {
        Statement oStmt = null;
        ResultSet oRset = null;
        String flag = null;
        Connection oConn = null;
        try {
            oConn = SceDataBaseLayer.ds1.getConnection();
            oStmt = oConn.createStatement();
            oRset = oStmt.executeQuery("select ifnull(desktop,\"null\") from synchronous_collaboration where id = '" + strSCEId + "'");
            if (oRset.next()) {
                flag = oRset.getString(1);
            }
            if (flag == null) {
                flag = "F";
            }
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("Error due to SQL exception : " + e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug(" error due to java.lang.exception");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
            SceDataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        finally {
            try {
                if (oRset != null) {
                    oRset.close();
                }
                if (oStmt != null) {
                    oStmt.close();
                }
                if (oConn != null) {
                    oConn.close();
                }
            }
            catch (Exception ex2) {}
        }
        return flag;
    }
    
    public static synchronized String getVideoChecked(final String strSCEId) {
        Statement oStmt = null;
        ResultSet oRset = null;
        String flag = null;
        Connection oConn = null;
        try {
            oConn = SceDataBaseLayer.ds1.getConnection();
            oStmt = oConn.createStatement();
            oRset = oStmt.executeQuery("select ifnull(video,\"null\") from synchronous_collaboration where id = '" + strSCEId + "'");
            if (oRset.next()) {
                flag = oRset.getString(1);
            }
            if (flag == null) {
                flag = "F";
            }
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("Error due to SQL exception : " + e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug(" error due to java.lang.exception");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
            SceDataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        finally {
            try {
                if (oRset != null) {
                    oRset.close();
                }
                if (oStmt != null) {
                    oStmt.close();
                }
                if (oConn != null) {
                    oConn.close();
                }
            }
            catch (Exception ex2) {}
        }
        return flag;
    }
    
    public static synchronized Vector<Vector<String>> getStreamingDetails(final String unitID) {
        final Vector<Vector<String>> SCList = new Vector<Vector<String>>();
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            String dststring = "dst=std{access=udp,mux=ts,url=";
            String dststring2 = "";
            System.out.println("select a.sc_id, a.video_device,a.audio_device,a.video_size,a.local_view,a.output_method,a.input_port,a.output_port,a.encapsulation,a.video_transcoding,a.video_bitrate,a.scale,a.audio_transcoding,a.audio_bitrate,a.channels,a.input_method,b.ip,a.ttl,a.fps,a.multicast from sce_streaming_conf a,streaming_server_mgmt b where a.sc_id='" + unitID + "' and b.sc_id=a.sc_id ");
            final ResultSet oRset = oStmt.executeQuery("select a.sc_id, a.video_device,a.audio_device,a.video_size,a.local_view,a.output_method,a.input_port,a.output_port,a.encapsulation,a.video_transcoding,a.video_bitrate,a.scale,a.audio_transcoding,a.audio_bitrate,a.channels,a.input_method,b.ip,a.ttl,a.fps,a.multicast,c.mentor_view,a.mode from sce_streaming_conf a,streaming_server_mgmt b,synchronous_collaboration c where a.sc_id='" + unitID + "' and b.sc_id=a.sc_id and c.id=a.sc_id");
            while (oRset.next()) {
                final Vector<String> vSC = new Vector<String>();
                vSC.addElement(oRset.getString(1));
                vSC.addElement(oRset.getString(2));
                vSC.addElement(oRset.getString(3));
                vSC.addElement(oRset.getString(4));
                vSC.addElement(oRset.getString(5));
                vSC.addElement(oRset.getString(6));
                vSC.addElement(oRset.getString(7));
                vSC.addElement(oRset.getString(8));
                vSC.addElement(oRset.getString(9));
                vSC.addElement(oRset.getString(10));
                vSC.addElement(oRset.getString(11));
                vSC.addElement(oRset.getString(12));
                vSC.addElement(oRset.getString(13));
                vSC.addElement(oRset.getString(14));
                vSC.addElement(oRset.getString(15));
                vSC.addElement(oRset.getString(16));
                vSC.addElement(oRset.getString(17));
                vSC.addElement(oRset.getString(18));
                vSC.addElement(oRset.getString(19));
                vSC.addElement(oRset.getString(20));
                vSC.addElement(oRset.getString(21));
                vSC.addElement(oRset.getString(22));
                final ResultSet oRset2 = oStmt2.executeQuery("select ipaddress from student_ipaddress_association where sce_id='" + unitID + "'");
                while (oRset2.next()) {
                    dststring = dststring + dststring2 + oRset2.getString(1) + ":" + oRset.getString(8) + "},";
                    dststring2 = "dst=std{access=udp,mux=ts,url=";
                }
                vSC.addElement(dststring);
                SCList.addElement(vSC);
            }
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("SQLException: ");
            SceDataBaseLayer.log.debug(e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug("SQLException: ");
            SceDataBaseLayer.log.debug(ex.getMessage());
        }
        return SCList;
    }
    
    public static String GetMentorView(final String sce_id) {
        String mentor_view = "";
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset1 = statement.executeQuery("select mentor_view from synchronous_collaboration where id ='" + sce_id + "'");
            while (oRset1.next()) {
                mentor_view = oRset1.getString(1);
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("SQLException:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        return mentor_view;
    }
    
    public static Vector<Vector<String>> getSynchronousList(final String student_id) {
        final Vector<Vector<String>> vSynchronousList = new Vector<Vector<String>>();
        Vector<String> vSCE = null;
        Vector<String> vSCE2 = null;
        int i = 0;
        String sc_id = null;
        String sce_id = null;
        String s17 = null;
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select a.id , b.name, b.status, b.start, b.end, b.type, b.session_id,rsession  from sc_user_association a ,synchronous_collaboration b where a.user_id ='" + student_id + "' and a.id=b.id");
            while (oRset.next()) {
                sc_id = oRset.getString(1);
                final String sc_name = oRset.getString(2);
                vSCE = new Vector<String>(10, 10);
                vSCE.addElement(sc_id);
                vSCE.addElement(sc_name);
                final Statement statement2 = oConn.createStatement();
                final ResultSet resultset1 = statement2.executeQuery("select count(*) from sc_user_association where id ='" + sc_id + "'");
                while (resultset1.next()) {
                    vSCE.addElement(resultset1.getString(1));
                    vSCE.addElement(oRset.getString(3));
                    vSCE.addElement(oRset.getString(6));
                    vSCE.addElement(oRset.getString(4));
                    vSCE.addElement(oRset.getString(5));
                    vSCE.addElement(oRset.getString(7));
                    vSCE.addElement(oRset.getString(8));
                    vSynchronousList.addElement(vSCE);
                }
                if (i == 0) {
                    s17 = "'" + sc_id + "'";
                }
                else {
                    s17 = s17 + ",'" + sc_id + "'";
                }
                ++i;
            }
            final Statement statement3 = oConn.createStatement();
            final ResultSet resultset2 = statement3.executeQuery("select a.sc_id, a.group_id, b.name, b.status, b.start, b.end, b.type, b.session_id,rsession,c.student_id from sc_group_association a , synchronous_collaboration b, student_group_association c where c.group_id =a.group_id and c.student_id='" + student_id + "' and a.sc_id=b.id and a.sc_id not in (" + s17 + ")");
            while (resultset2.next()) {
                sce_id = resultset2.getString(1);
                final String s2 = resultset2.getString(3);
                if (vSynchronousList.isEmpty()) {
                    vSCE2 = new Vector<String>(10, 10);
                    vSCE2.addElement(sce_id);
                    vSCE2.addElement(s2);
                    final Statement statement4 = oConn.createStatement();
                    final ResultSet resultset3 = statement4.executeQuery("select count(*) from sc_group_association where sc_id ='" + sce_id + "'");
                    while (resultset3.next()) {
                        vSCE2.addElement(resultset3.getString(1));
                        vSCE2.addElement(resultset2.getString(4));
                        vSCE2.addElement(resultset2.getString(7));
                        vSCE2.addElement(resultset2.getString(5));
                        vSCE2.addElement(resultset2.getString(6));
                        vSCE2.addElement(resultset2.getString(8));
                        vSCE2.addElement(resultset2.getString(9));
                        vSynchronousList.addElement(vSCE2);
                    }
                }
                for (int s3 = 0; s3 < vSynchronousList.size(); ++s3) {
                    final Vector vSysList = (Vector) vSynchronousList.elementAt(s3);
                    if (vSysList.contains(sce_id)) {
                        SceDataBaseLayer.log.debug("contains");
                    }
                    else {
                        vSCE2 = new Vector(10, 10);
                        vSCE2.addElement(sce_id);
                        vSCE2.addElement(s2);
                        final Statement statement5 = oConn.createStatement();
                        final ResultSet resultset4 = statement5.executeQuery("select count(*) from sc_group_association where sc_id ='" + sce_id + "'");
                        while (resultset4.next()) {
                            vSCE2.addElement(resultset4.getString(1));
                            vSCE2.addElement(resultset2.getString(4));
                            vSCE2.addElement(resultset2.getString(7));
                            vSCE2.addElement(resultset2.getString(5));
                            vSCE2.addElement(resultset2.getString(6));
                            vSCE2.addElement(resultset2.getString(8));
                            vSCE2.addElement(resultset2.getString(9));
                            vSynchronousList.addElement(vSCE2);
                        }
                        s17 = s17 + ",'" + sce_id + "'";
                        resultset4.close();
                        statement5.close();
                    }
                }
            }
            resultset2.close();
            statement3.close();
            oRset.close();
            statement.close();
            oConn.commit();
            oConn.setAutoCommit(true);
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        return vSynchronousList;
    }
    
    public static String SCERegisteredMembers(final String sc_id) {
        int count = 0;
        String reg_members = "";
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final Statement statement3 = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select count(*) from sc_user_association where id='" + sc_id + "'");
            while (resultset.next()) {
                count += resultset.getInt(1);
            }
            final ResultSet resultset2 = statement2.executeQuery("select group_id from sc_group_association where sc_id='" + sc_id + "'");
            while (resultset2.next()) {
                final ResultSet resultset3 = statement3.executeQuery("select count(*) from student_group_association where group_id='" + resultset2.getString(1) + "'");
                while (resultset3.next()) {
                    count += resultset3.getInt(1);
                }
                statement3.close();
                resultset3.close();
            }
            reg_members = Integer.toString(count);
            statement.close();
            resultset.close();
            statement2.close();
            resultset2.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.debug("Error due to SQL exception inside NewDataBaseLayer.SCERegisteredMembers() : " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.debug(" error due to java.lang.exception SCERegisteredMembers");
            exception.printStackTrace();
            SceDataBaseLayer.log.debug(" printStack is :: " + exception.getMessage());
        }
        return reg_members;
    }
    
    public static void PollingDetails(final String poll_id, final String poll_title, final String poll_text, final String ques_type) {
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("insert polling_details(pollid,polltitle,polltext,ismcchoice)values('" + poll_id + "','" + poll_title + "','" + poll_text + "','" + ques_type + "')");
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("Polling details SQLException:" + sqlexception.getMessage());
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:" + exception.getMessage());
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
    }
    
    public static void PollingChoices(final String poll_id, final String choice_text) {
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            if (!choice_text.equals("")) {
                final boolean flag = statement.execute("insert polling_choices(pollid,choicetext)values('" + poll_id + "','" + choice_text + "')");
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("Polling Choice SQLException:" + sqlexception.getMessage());
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:" + exception.getMessage());
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
    }
    
    public static void Pollstatus(final String sce_id, final String poll_id, final String re_status) {
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final boolean flag = statement.execute("insert poll_sce(sceid,pollid,resultstatus)values('" + sce_id + "','" + poll_id + "','" + re_status + "')");
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("Poll Status SQLException:" + sqlexception.getMessage());
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
    }
    
    public static synchronized boolean modifypollstatus(final String sce_id, final String poll_id, final String re_status) {
        boolean flag = false;
        String resultstatus1 = "";
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            final Statement statement1 = oConn.createStatement();
            final ResultSet oRset1 = statement1.executeQuery("select resultstatus from poll_sce");
            while (oRset1.next()) {
                resultstatus1 = oRset1.getString(1);
                System.out.println("******************STSTUS*********************************" + resultstatus1);
                if (resultstatus1.equals("Activated")) {
                    System.out.println("******************checkactivated*********************************");
                    System.out.println("----->>>>>------111----update poll_sce set resultstatus = 'Inactivated' where sceid = '" + sce_id + "'and resultstatus='" + resultstatus1 + "'");
                    oStmt.execute("update poll_sce set resultstatus = 'Inactivated' where sceid = '" + sce_id + "'and resultstatus='" + resultstatus1 + "'");
                }
            }
            oStmt.close();
            statement1.close();
            final Statement oStmt2 = oConn.createStatement();
            System.out.println("----->>>>>------222----update poll_sce set resultstatus = '" + re_status + "' where sceid = '" + sce_id + "' and pollid='" + poll_id + "'");
            flag = oStmt2.execute("update poll_sce set resultstatus = '" + re_status + "' where sceid = '" + sce_id + "' and pollid='" + poll_id + "'");
            System.out.println("----->>>>>------222-----------" + flag);
            oConn.commit();
            oConn.setAutoCommit(true);
            oStmt2.close();
            oConn.close();
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("modifypollstatus SQLException: " + e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug("modifypollstatus Exception: ");
            SceDataBaseLayer.log.debug(ex.getMessage());
        }
        return flag;
    }
    
    public static void insertPollResult(final String poll_id) {
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final ResultSet oRset = statement.executeQuery("select choiceid from polling_choices where pollid='" + poll_id + "'");
            while (oRset.next()) {
                statement2.execute("insert into polling_results(pollid,choiceid,totalvotes) values('" + poll_id + "','" + oRset.getString(1) + "','0')");
            }
            statement.close();
            oRset.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("Polling Choice SQLException:" + sqlexception.getMessage());
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:" + exception.getMessage());
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
    }
    
    public static synchronized boolean PublishedResult(final String sce_id, final String poll_id, final String re_status) {
        boolean flag = false;
        final String resultstatus1 = "";
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            flag = oStmt.execute("update poll_sce set resultstatus = '" + re_status + "' where sceid = '" + sce_id + "' and pollid='" + poll_id + "'");
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("Publish result SQLException: " + e.getMessage());
            SceDataBaseLayer.log.debug(e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug("Publish result Exception: ");
            SceDataBaseLayer.log.debug(ex.getMessage());
        }
        return flag;
    }
    
    public static synchronized String getSceName(final String sce_id) {
        String Vcompo = null;
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement1 = oConn.createStatement();
            final ResultSet oRset = statement1.executeQuery("select name from synchronous_collaboration where id='" + sce_id + "'");
            while (oRset.next()) {
                Vcompo = oRset.getString(1);
            }
            statement1.close();
            oConn.commit();
            oConn.setAutoCommit(true);
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.debug("Exception in SceDatabaseLayer.getSceName()");
            SceDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug("Exception in SceDatabaseLayer.selectPort()");
            SceDataBaseLayer.log.debug("The Error Message - " + ex.getMessage());
        }
        return Vcompo;
    }
    
    public static Vector getPublishedPoll(final String sce_id, final String p_status) {
        final Vector vChoices = new Vector();
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select a.pollid,b.polltitle from poll_sce a,polling_details b where a.sceid ='" + sce_id + "' and a.resultstatus='" + p_status + "' and a.pollid=b.pollid");
            while (resultset.next()) {
                final String Qchoice = resultset.getString(1);
                final String Qchoice2 = resultset.getString(2);
                vChoices.addElement(Qchoice);
                vChoices.addElement(Qchoice2);
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("SQLException:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        return vChoices;
    }
    
    public static synchronized void delete_poll_info(final String sceId) {
        final boolean flag = false;
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select pollid from poll_sce where sceid='" + sceId + "'");
            while (oRset.next()) {
                oStmt2.execute("delete from polling_details where pollid='" + oRset.getString(1) + "'");
                oStmt2.execute("delete from polling_choices where pollid='" + oRset.getString(1) + "'");
                oStmt2.execute("delete from polling_results where pollid='" + oRset.getString(1) + "'");
            }
            oStmt2.execute("delete from poll_sce where sceid='" + sceId + "'");
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("Error due to SQL exception : " + e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug(" error due to java.lang.exception");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
            SceDataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
    }
    
    public static Vector<Vector<String>> getResultdetails(final String poll_id) {
        final Vector<Vector<String>> vResults = new Vector<Vector<String>>(4, 4);
        final String[] sResults = new String[2];
        final int[] sResults2 = new int[2];
        final Vector<String> vResults2 = new Vector<String>(1, 1);
        final Vector<String> vResults3 = new Vector<String>(3, 3);
        final Vector<String> vResults4 = new Vector<String>(1, 1);
        int alltotalvotes = 0;
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement statement1 = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final Statement statement3 = oConn.createStatement();
            final ResultSet resultset1 = statement1.executeQuery("select polltitle from polling_details where pollid='" + poll_id + "'");
            if (resultset1.next()) {
                sResults[0] = resultset1.getString(1);
                vResults2.addElement(resultset1.getString(1));
                System.out.println("************getResultdetails_polltitle*************************************" + sResults[0]);
            }
            final ResultSet resultset2 = statement2.executeQuery("select choiceid,sum(totalvotes) from polling_results where pollid='" + poll_id + "' group by choiceid");
            while (resultset2.next()) {
                if (resultset2.getInt(1) != 0) {
                    sResults2[0] = resultset2.getInt(1);
                }
                sResults2[1] = resultset2.getInt(2);
                vResults3.addElement(""+resultset2.getInt(1));
                vResults3.addElement(""+resultset2.getInt(2));
                alltotalvotes += resultset2.getInt(2);
                final ResultSet resultset3 = statement3.executeQuery("select choicetext from polling_choices where pollid='" + poll_id + "' and choiceid='" + sResults2[0] + "'");
                if (resultset3.next()) {
                    sResults[1] = resultset3.getString(1);
                    vResults3.addElement(resultset3.getString(1));
                }
            }
            vResults4.addElement(""+alltotalvotes);
            vResults.addElement(vResults2);
            vResults.addElement(vResults3);
            vResults.addElement(vResults4);
            statement1.close();
            statement2.close();
            statement3.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("SQLException:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        return vResults;
    }
    
    public static synchronized boolean delete_from_ipaddress(final String sceId, final String student_id) {
        boolean flag = false;
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            flag = oStmt.execute("delete from student_ipaddress_association where sce_id='" + sceId + "' and student_id='" + student_id + "'");
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("Error due to SQL exception : " + e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug(" error due to java.lang.exception");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
            SceDataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        return flag;
    }
    
    public static synchronized Vector<Vector<String>> getDesktopSharingPort(final String plan_id) {
        final Vector<Vector<String>> Vcompo = new Vector<Vector<String>>();
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement1 = oConn.createStatement();
            System.out.println("select port_number,ip from desktop_sharing_port where sc_id='" + plan_id + "'");
            final ResultSet oRset = statement1.executeQuery("select port_number,ip from desktop_sharing_port where sc_id='" + plan_id + "'");
            while (oRset.next()) {
                final Vector<String> vSC = new Vector<String>();
                vSC.addElement(oRset.getString(1));
                vSC.addElement(oRset.getString(2));
                System.out.println("oRset.getString(1)=" + oRset.getString(1));
                System.out.println("oRset.getString(2)=" + oRset.getString(2));
                Vcompo.addElement(vSC);
            }
            statement1.close();
            oConn.commit();
            oConn.setAutoCommit(true);
            oConn.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in SceDatabaseLayer.getMentorName()" + sqlexception);
            SceDataBaseLayer.log.debug("Exception in SceDatabaseLayer.getDesktopSharingPort()");
            SceDataBaseLayer.log.debug("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in SceDatabaseLayer.selectPort()" + ex);
            SceDataBaseLayer.log.debug("Exception in SceDatabaseLayer.getDesktopSharingPort()");
            SceDataBaseLayer.log.debug("The Error Message - " + ex.getMessage());
        }
        System.out.println("Vcompo.size========" + Vcompo.size());
        return Vcompo;
    }
    
    public static String GetPollId(final String sce_id, final String p_status) {
        String pollingid = "";
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset1 = statement.executeQuery("select pollid from poll_sce where sceid ='" + sce_id + "' and resultstatus='" + p_status + "'");
            while (oRset1.next()) {
                pollingid = oRset1.getString(1);
                System.out.println("*******************GetPollId*********************" + pollingid);
            }
            statement.close();
            oRset1.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("SQLException:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        return pollingid;
    }
    
    public static String Getpollstatus(final String sce_id, final String poll_id) {
        String status = "";
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset1 = statement.executeQuery("select resultstatus from poll_sce where sceid ='" + sce_id + "' and pollid='" + poll_id + "'");
            while (oRset1.next()) {
                status = oRset1.getString(1);
                System.out.println("*******************GetPollStstus*********************" + status);
            }
            statement.close();
            oRset1.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("SQLException:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        return status;
    }
    
    public static synchronized Vector<Vector<String>> getscevlcdetails(final String unitID) {
        final Vector<Vector<String>> SCList = new Vector<Vector<String>>();
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select a.input_port,a.input_method,a.output_method,b.ip,a.output_port,a.recording,a.encapsulation,video_transcoding,video_bitrate,scale from sce_streaming_conf a,streaming_server_mgmt b where a.sc_id='" + unitID + "' and b.sc_id=a.sc_id ");
            while (oRset.next()) {
                final Vector<String> vSC = new Vector<String>();
                vSC.addElement(oRset.getString(1));
                vSC.addElement(oRset.getString(2));
                vSC.addElement(oRset.getString(3));
                vSC.addElement(oRset.getString(4));
                vSC.addElement(oRset.getString(5));
                vSC.addElement(oRset.getString(6));
                vSC.addElement(oRset.getString(7));
                vSC.addElement(oRset.getString(8));
                vSC.addElement(oRset.getString(9));
                vSC.addElement(oRset.getString(10));
                SCList.addElement(vSC);
            }
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("SQLException: ");
            SceDataBaseLayer.log.debug(e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug("SQLException: ");
            SceDataBaseLayer.log.debug(ex.getMessage());
        }
        return SCList;
    }
    
    public static synchronized Vector<Vector<String>> getscevlcdetailsForStudent(final String unitID) {
        final Vector<Vector<String>> SCList = new Vector<Vector<String>>();
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select a.input_port,a.input_method,a.output_method,b.ip,a.output_port,a.recording,a.encapsulation from sce_streaming_conf_for_student a,streaming_server_mgmt b where a.sc_id='" + unitID + "' and b.sc_id=a.sc_id ");
            while (oRset.next()) {
                final Vector<String> vSC = new Vector<String>();
                vSC.addElement(oRset.getString(1));
                vSC.addElement(oRset.getString(2));
                vSC.addElement(oRset.getString(3));
                vSC.addElement(oRset.getString(4));
                vSC.addElement(oRset.getString(5));
                vSC.addElement(oRset.getString(6));
                vSC.addElement(oRset.getString(7));
                SCList.addElement(vSC);
            }
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("SQLException: ");
            SceDataBaseLayer.log.debug(e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug("SQLException: ");
            SceDataBaseLayer.log.debug(ex.getMessage());
        }
        return SCList;
    }
    
    public static synchronized boolean insertservermgmt(final String strId, final String TelnetPort, final String vlcip, final String pass) {
        boolean flag = false;
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            final ResultSet oRset = oStmt2.executeQuery("select count(*)  from streaming_server_mgmt where sc_id='" + strId + "'");
            int l_i = 0;
            while (oRset.next()) {
                l_i = oRset.getInt(1);
            }
            if (l_i <= 0) {
                flag = oStmt.execute("insert into streaming_server_mgmt (sc_id, port_number, ip, password ) values ('" + strId + "','" + TelnetPort + "','" + vlcip + "','" + pass + "')");
            }
            else {
                System.out.println("update streaming_server_mgmt set port_number = '" + TelnetPort + "', ip = '" + vlcip + "', password = '" + pass + "' where sc_id = '" + strId + "'");
                flag = oStmt.execute("update streaming_server_mgmt set port_number = '" + TelnetPort + "', ip = '" + vlcip + "', password = '" + pass + "' where sc_id = '" + strId + "'");
            }
            oConn.commit();
            oConn.setAutoCommit(true);
            oStmt2.close();
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("insertservermgmt SQLException: " + e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug("insertservermgmt Exception: ");
            SceDataBaseLayer.log.debug(ex.getMessage());
        }
        return flag;
    }
    
    public static synchronized Vector getSynchronousEnvironment(final String strId) {
        Vector vCourse = null;
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            vCourse = new Vector();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select a.id , a.name ,  a.chat, if(a.sw<=>\"T\",\"yes\",\"no\"), if(a.ledsession<=>\"\" OR a.ledsession<=>NULL,\"no\",a.ledsession), if(a.sp<=>\"T\" OR a.sp<=>NULL,\"yes\",\"no\"), if(a.cobrowse<=>\"T\" OR a.cobrowse<=>NULL,\"yes\",\"no\"), if(a.sound<=>\"T\" OR a.sound<=>NULL,\"yes\",\"no\"), if(a.recordsession<=>\"T\" OR a.recordsession<=>NULL,\"yes\",\"no\"), if(a.desktop<=>\"T\" OR a.desktop<=>NULL,\"yes\",\"no\") from synchronous_collaboration a where a.id = '" + strId + "'");
            if (oRset.next()) {
                vCourse.addElement(oRset.getString(1));
                vCourse.addElement(oRset.getString(2));
                vCourse.addElement(oRset.getString(3));
                vCourse.addElement(oRset.getString(4));
                vCourse.addElement(oRset.getString(5));
                vCourse.addElement(oRset.getString(6));
                vCourse.addElement(oRset.getString(7));
                vCourse.addElement(oRset.getString(8));
                vCourse.addElement(oRset.getString(9));
                vCourse.addElement(oRset.getString(10));
            }
            oRset.close();
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            System.out.println("Error due to SQL exception in delivery.NewDataBaseLayer.getSynchronousEnvironment()" + e.toString());
            e.printStackTrace();
        }
        catch (Exception ex) {
            System.out.println("Error due to exception in delivery.NewDataBaseLayer.getSynchronousEnvironment()");
            ex.printStackTrace();
            System.out.println("printStack is :: " + ex.toString());
        }
        return vCourse;
    }
    
    public static synchronized Vector getSynchronousEnvironmentadd(final String strId) {
        Vector vCourseadd = null;
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            vCourseadd = new Vector();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select  if(a.video<=>\"T\" OR a.video<=>NULL,\"yes\",\"no\") from synchronous_collaboration a where a.id = '" + strId + "'");
            if (oRset.next()) {
                vCourseadd.addElement(oRset.getString(1));
            }
            oRset.close();
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            System.out.println("Error due to SQL exception in delivery.NewDataBaseLayer.getSynchronousEnvironment()" + e.toString());
            e.printStackTrace();
        }
        catch (Exception ex) {
            System.out.println("Error due to exception in delivery.NewDataBaseLayer.getSynchronousEnvironment()");
            ex.printStackTrace();
            System.out.println("printStack is :: " + ex.toString());
        }
        return vCourseadd;
    }
    
    public static synchronized Vector getStreamingDetailsForStudent(final String unitID) {
        final Vector SCList = new Vector();
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            String dststring = "dst=std{access=udp,mux=ts,url=";
            String dststring2 = "";
            final ResultSet oRset = oStmt.executeQuery("select a.sc_id, a.video_device,a.audio_device,a.video_size,a.local_view,a.output_method,a.input_port,a.output_port,a.encapsulation,a.video_transcoding,a.video_bitrate,a.scale,a.audio_transcoding,a.audio_bitrate,a.channels,a.input_method,b.ip,a.ttl,a.fps,a.multicast,c.student_view,a.mode from sce_streaming_conf_for_student a,streaming_server_mgmt b,synchronous_collaboration c where a.sc_id='" + unitID + "' and b.sc_id=a.sc_id and c.id=a.sc_id");
            while (oRset.next()) {
                final Vector vSC = new Vector();
                vSC.addElement(oRset.getString(1));
                vSC.addElement(oRset.getString(2));
                vSC.addElement(oRset.getString(3));
                vSC.addElement(oRset.getString(4));
                vSC.addElement(oRset.getString(5));
                vSC.addElement(oRset.getString(6));
                vSC.addElement(oRset.getString(7));
                vSC.addElement(oRset.getString(8));
                vSC.addElement(oRset.getString(9));
                vSC.addElement(oRset.getString(10));
                vSC.addElement(oRset.getString(11));
                vSC.addElement(oRset.getString(12));
                vSC.addElement(oRset.getString(13));
                vSC.addElement(oRset.getString(14));
                vSC.addElement(oRset.getString(15));
                vSC.addElement(oRset.getString(16));
                vSC.addElement(oRset.getString(17));
                vSC.addElement(oRset.getString(18));
                vSC.addElement(oRset.getString(19));
                vSC.addElement(oRset.getString(20));
                vSC.addElement(oRset.getString(21));
                vSC.addElement(oRset.getString(22));
                final ResultSet oRset2 = oStmt2.executeQuery("select ipaddress from student_ipaddress_association where sce_id='" + unitID + "'");
                while (oRset2.next()) {
                    dststring = dststring + dststring2 + oRset2.getString(1) + ":" + oRset.getString(8) + "},";
                    dststring2 = "dst=std{access=udp,mux=ts,url=";
                }
                vSC.addElement(dststring);
                SCList.addElement(vSC);
            }
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("SQLException: ");
            SceDataBaseLayer.log.debug(e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug("SQLException: ");
            SceDataBaseLayer.log.debug(ex.getMessage());
        }
        return SCList;
    }
    
    public static synchronized Vector getIpaddress(final String unitID) {
        final Vector vSC = new Vector();
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            String dststring = "dst=std{access=udp,mux=ts,url=";
            String dststring2 = "";
            final ResultSet oRset = oStmt.executeQuery("select a.sc_id, a.video_device,a.audio_device,a.video_size,a.local_view,a.output_method,a.input_port,a.output_port,a.encapsulation,a.video_transcoding,a.video_bitrate,a.scale,a.audio_transcoding,a.audio_bitrate,a.channels,a.input_method,b.ip,a.ttl,a.fps,a.multicast,c.mentor_view,a.mode from sce_streaming_conf a,streaming_server_mgmt b,synchronous_collaboration c where a.sc_id='" + unitID + "' and b.sc_id=a.sc_id and c.id=a.sc_id");
            while (oRset.next()) {
                final ResultSet oRset2 = oStmt2.executeQuery("select ipaddress from student_ipaddress_association where sce_id='" + unitID + "'");
                while (oRset2.next()) {
                    dststring = dststring + dststring2 + oRset2.getString(1) + ":" + oRset.getString(8) + "},";
                    dststring2 = "dst=std{access=udp,mux=ts,url=";
                }
                vSC.addElement(dststring);
            }
            oStmt.close();
            oRset.close();
            oConn.close();
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("SQLException: ");
            SceDataBaseLayer.log.debug(e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug("SQLException: ");
            SceDataBaseLayer.log.debug(ex.getMessage());
        }
        return vSC;
    }
    
    public static synchronized Vector getReflectorDetails(final String unitID) {
        final Vector vSC = new Vector();
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select sc_id, port_number, ip, mode, VorF from mentor_port where sc_id='" + unitID + "'");
            while (oRset.next()) {
                vSC.insertElementAt(oRset.getString(5), 0);
                vSC.insertElementAt(oRset.getString(4), 0);
                vSC.insertElementAt(oRset.getString(3), 0);
                vSC.insertElementAt(oRset.getString(2), 0);
            }
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("SQLException: ");
            SceDataBaseLayer.log.debug(e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug("SQLException: ");
            SceDataBaseLayer.log.debug(ex.getMessage());
        }
        return vSC;
    }
    
    public static synchronized boolean insertPollDefinition(final String sce_id, final String polltitle, final String polltext, final String ismcchoice) {
        final boolean succ = false;
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            final Statement oStmt3 = oConn.createStatement();
            oStmt.execute("insert into polling_details(polltitle,polltext,ismcchoice) values('" + polltitle + "','" + polltext + "','" + ismcchoice + "')");
            final ResultSet oRset = oStmt2.executeQuery("select max(pollid) from polling_details");
            while (oRset.next()) {
                oStmt3.execute("insert into poll_sce (pollid,sceid,resultstatus) values('" + oRset.getString(1) + "','" + sce_id + "','Inactivated')");
            }
            oStmt3.close();
            oStmt2.close();
            oRset.close();
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            System.err.println("sqlexception in insertPollDefinition " + e.getMessage());
        }
        catch (Exception ex) {
            System.err.println("exception in insertPollDefinition " + ex.getMessage());
        }
        return succ;
    }
    
    public static synchronized boolean insertPollChoice(final String pollid, final String choicetext) {
        final boolean succ = false;
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final Statement oStmt2 = oConn.createStatement();
            final Statement oStmt3 = oConn.createStatement();
            oStmt.execute("insert into polling_choices (pollid,choicetext) values('" + pollid + "','" + choicetext + "')");
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            System.err.println("sqlexception in insertPollDefinition " + e.getMessage());
        }
        catch (Exception ex) {
            System.err.println("exception in insertPollDefinition " + ex.getMessage());
        }
        return succ;
    }
    
    public static Vector<String[]> getPolldetails(final String poll_id) {
        final Vector<String[]> vpolls = new Vector<String[]>(3, 3);
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select polltitle,polltext,ismcchoice from polling_details where pollid='" + poll_id + "'");
            while (resultset.next()) {
                final String[] Qpoll = { resultset.getString(1), resultset.getString(2), resultset.getString(3) };
                vpolls.addElement(Qpoll);
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("getPolldetails SQLException:" + sqlexception.getMessage());
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        return vpolls;
    }
    
    public static Vector<String[]> getChoicedetails(final String poll_id) {
        final Vector<String[]> vChoices = new Vector(2, 2);
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select choiceid  ,choicetext from polling_choices where pollid='" + poll_id + "'");
            while (resultset.next()) {
                final String[] Qchoice = { resultset.getString(1), resultset.getString(2) };
                vChoices.addElement(Qchoice);
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("SQLException:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        return vChoices;
    }
    
    public static String GetChoiceId(final String poll_id, final String choice_text) {
        String choiceid = "";
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet oRset1 = statement.executeQuery("select choiceid from polling_choices where pollid ='" + poll_id + "' and choicetext='" + choice_text + "'");
            while (oRset1.next()) {
                choiceid = oRset1.getString(1);
                System.out.println("*******************GetChoiceid*********************" + choiceid);
            }
            statement.close();
            oRset1.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("SQLException:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        return choiceid;
    }
    
    public static synchronized boolean checkSubmittedPoll(final String st_id, final String pollid) {
        boolean flag = false;
        int i = 0;
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select count(totalvotes) from polling_results where pollid='" + pollid + "' and user_id='" + st_id + "'");
            oRset.next();
            i = oRset.getInt(1);
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("Error due to SQL exception : " + e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug(" error due to java.lang.exception");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
            SceDataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
        flag = (i != 0);
        return flag;
    }
    
    public static void PollingResults(final String poll_id, final String choice_id, final String st_id) {
        int i = 0;
        final int k = 1;
        boolean flag = false;
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement statement1 = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final Statement statement3 = oConn.createStatement();
            final ResultSet resultset2 = statement2.executeQuery("select count(totalvotes) from polling_results where pollid='" + poll_id + "' and choiceid='" + choice_id + "' and user_id='" + st_id + "'");
            resultset2.next();
            i = resultset2.getInt(1);
            if (i == 0) {
                flag = statement3.execute("insert into polling_results(pollid,choiceid,totalvotes,user_id) values('" + poll_id + "','" + choice_id + "',1,'" + st_id + "')");
            }
            statement1.close();
            statement2.close();
            statement3.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("Polling Result SQLException:" + sqlexception.getMessage());
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
    }
    
    public static synchronized void Insert_IP_Addr(final String SceId, final String StudentId, final String ip) {
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            Statement oStmt = oConn.createStatement();
            oStmt = oConn.createStatement();
            oStmt.executeUpdate("insert into student_ipaddress_association (sce_id, student_id, ipaddress) values ('" + SceId + "', '" + StudentId + "','" + ip + "')");
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("Error due to SQL exception : " + e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug(" error due to java.lang.exception");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
            SceDataBaseLayer.log.debug(" printStack is :: " + ex.getMessage());
        }
    }
    
    public static synchronized String getstudentinputport(final String unitID) {
        String input_port = "";
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select a.input_port from sce_streaming_conf_for_student a where a.sc_id='" + unitID + "' ");
            while (oRset.next()) {
                input_port = oRset.getString(1);
            }
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            SceDataBaseLayer.log.debug("SQLException: ");
            SceDataBaseLayer.log.debug(e.getMessage());
        }
        catch (Exception ex) {
            SceDataBaseLayer.log.debug("SQLException: ");
            SceDataBaseLayer.log.debug(ex.getMessage());
        }
        return input_port;
    }
    
    public static String GetActivePole(final String sce_id) {
        String status = "";
        Statement statement = null;
        ResultSet oRset1 = null;
        Connection oConn = null;
        try {
            oConn = SceDataBaseLayer.ds1.getConnection();
            statement = oConn.createStatement();
            oRset1 = statement.executeQuery("select pollid from poll_sce where sceid ='" + sce_id + "' and resultstatus='Activated'");
            while (oRset1.next()) {
                status = oRset1.getString(1);
                System.out.println("*******************GetPollStstus*********************" + status);
            }
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("SQLException:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            try {
                if (oRset1 != null) {
                    oRset1.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (oConn != null) {
                    oConn.close();
                }
            }
            catch (Exception ex) {}
        }
        return status;
    }
    
    public static String Getnotsubmittedpollstatus(final String sce_id, final String user_id) {
        String status = "Inactivated";
        Connection oConn = null;
        Statement statement = null;
        Statement statement2 = null;
        ResultSet oRset1 = null;
        ResultSet oRset2 = null;
        try {
            oConn = SceDataBaseLayer.ds1.getConnection();
            statement = oConn.createStatement();
            statement2 = oConn.createStatement();
            oRset2 = statement2.executeQuery("select pollid from poll_sce where sceid='" + sce_id + "' and resultstatus='Activated'");
            while (oRset2.next()) {
                System.out.println("select choiceid from polling_results where pollid='" + oRset2.getString(1) + "' and user_id='" + user_id + "'");
                oRset1 = statement.executeQuery("select choiceid from polling_results where pollid='" + oRset2.getString(1) + "' and user_id='" + user_id + "'");
                if (oRset1.next()) {
                    status = "Submitted";
                }
                else {
                    status = "Activated";
                }
            }
            System.out.println("*******************GetPollStstus*********************" + status);
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("SQLException:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            try {
                if (oRset1 != null) {
                    oRset1.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (oConn != null) {
                    oConn.close();
                }
            }
            catch (Exception ex) {}
        }
        return status;
    }
    
    public static Vector getResultdetailsNew(final String poll_id) {
        final Vector vResults = new Vector(4, 4);
        final String[] sResults = new String[2];
        final int[] sResults2 = new int[2];
        final Vector vResults2 = new Vector(1, 1);
        final Vector vResults3 = new Vector(3, 3);
        final Vector vResults4 = new Vector(1, 1);
        int alltotalvotes = 0;
        try {
            final Connection oConn = SceDataBaseLayer.ds1.getConnection();
            final Statement statement1 = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final Statement statement3 = oConn.createStatement();
            final ResultSet resultset1 = statement1.executeQuery("select polltitle from polling_details where pollid='" + poll_id + "'");
            if (resultset1.next()) {
                sResults[0] = resultset1.getString(1);
                vResults2.addElement(resultset1.getString(1));
                System.out.println("************getResultdetails_polltitle*************************************" + sResults[0]);
            }
            final ResultSet resultset2 = statement2.executeQuery("select a.choiceid,sum(b.totalvotes) from polling_choices a left join polling_results b on (a.pollid=b.pollid) where a.pollid='" + poll_id + "' group by a.choiceid");
            while (resultset2.next()) {
                if (resultset2.getInt(1) != 0) {
                    sResults2[0] = resultset2.getInt(1);
                }
                sResults2[1] = resultset2.getInt(2);
                vResults3.addElement(resultset2.getInt(1));
                vResults3.addElement(resultset2.getInt(2));
                alltotalvotes += resultset2.getInt(2);
                final ResultSet resultset3 = statement3.executeQuery("select choicetext from polling_choices where pollid='" + poll_id + "' and choiceid='" + sResults2[0] + "'");
                if (resultset3.next()) {
                    sResults[1] = resultset3.getString(1);
                    vResults3.addElement(resultset3.getString(1));
                }
            }
            vResults4.addElement(alltotalvotes);
            vResults.addElement(vResults2);
            vResults.addElement(vResults3);
            vResults.addElement(vResults4);
            statement1.close();
            statement2.close();
            statement3.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            SceDataBaseLayer.log.fatal("SQLException:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            SceDataBaseLayer.log.fatal("Exception:");
            SceDataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        return vResults;
    }
    
    static {
        log = new SimpleLogger((Class)SceDataBaseLayer.class);
        SceDataBaseLayer.ds1 = CoreAdminInitHostInfo.ds1;
    }
}
