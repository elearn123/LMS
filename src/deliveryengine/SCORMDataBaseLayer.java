package deliveryengine;

import org.grlea.log.*;
import javax.sql.*;
import org.adl.util.debug.*;
import java.sql.*;
import comv2.aunwesha.param.*;

public class SCORMDataBaseLayer
{
    private static boolean _Debug;
    private static final SimpleLogger log;
    public static DataSource ds;
    public static DataSource ds1;
    
    public String selectPrerequisites(final String userID, final String courseID, String scoID) {
        String LessonStatus = new String();
        while (true) {
            if (DebugIndicator.ON) {
                final String sqlSelectItem = "SELECT Prerequisites FROM UserSCOInfo WHERE UserID = ? AND CourseID = ? AND SCOID = ?";
                final String sqlSelectPrerequisites = "SELECT LessonStatus FROM UserSCOInfo WHERE UserID = ? AND CourseID = ? AND SCOID = ?";
                try {
                    final Connection conn = SCORMDataBaseLayer.ds1.getConnection();
                    final PreparedStatement rtestmtSelectItem = conn.prepareStatement(sqlSelectItem);
                    final PreparedStatement rtestmtSelectPrerequisites = conn.prepareStatement(sqlSelectPrerequisites);
                    if (SCORMDataBaseLayer._Debug) {
                        SCORMDataBaseLayer.log.entry("selectPrerequisites()");
                        SCORMDataBaseLayer.log.info("about to call item in RTEFile");
                        SCORMDataBaseLayer.log.info("userID: " + userID);
                        SCORMDataBaseLayer.log.info("courseID: " + courseID);
                    }
                    ResultSet itemRS = null;
                    ResultSet itemRS2 = null;
                    synchronized (rtestmtSelectItem) {
                        rtestmtSelectItem.setString(1, userID);
                        rtestmtSelectItem.setString(2, courseID);
                        rtestmtSelectItem.setString(3, scoID);
                        itemRS = rtestmtSelectItem.executeQuery();
                    }
                    if (SCORMDataBaseLayer._Debug) {
                        SCORMDataBaseLayer.log.entry("selectPrerequisites()");
                        SCORMDataBaseLayer.log.info("call to itemRS is complete");
                    }
                    while (itemRS.next()) {
                        scoID = itemRS.getString(1);
                        if (!scoID.equals("") && scoID != null) {
                            synchronized (rtestmtSelectPrerequisites) {
                                rtestmtSelectPrerequisites.setString(1, userID);
                                rtestmtSelectPrerequisites.setString(2, courseID);
                                rtestmtSelectPrerequisites.setString(3, scoID);
                                itemRS2 = rtestmtSelectPrerequisites.executeQuery();
                            }
                        }
                    }
                    if (itemRS2 != null && itemRS2.next()) {
                        LessonStatus = itemRS2.getString(1);
                        itemRS2.close();
                    }
                    itemRS.close();
                    rtestmtSelectItem.close();
                    rtestmtSelectPrerequisites.close();
                    conn.close();
                }
                catch (Exception e) {
                    if (SCORMDataBaseLayer._Debug) {
                        e.printStackTrace();
                    }
                }
                return LessonStatus;
            }
            continue;
        }
    }
    
    public String selectPrerequisites1(final String userID, final String courseID, final String scoID) {
        String strPrerequisites = new String();
        final String sqlSelectItem = "SELECT Prerequisites FROM UserSCOInfo WHERE UserID = ? AND CourseID = ? AND SCOID = ?";
        try {
            final Connection conn = SCORMDataBaseLayer.ds1.getConnection();
            final PreparedStatement rtestmtSelectItem = conn.prepareStatement(sqlSelectItem);
            ResultSet itemRS = null;
            synchronized (rtestmtSelectItem) {
                rtestmtSelectItem.setString(1, userID);
                rtestmtSelectItem.setString(2, courseID);
                rtestmtSelectItem.setString(3, scoID);
                itemRS = rtestmtSelectItem.executeQuery();
            }
            while (itemRS.next()) {
                strPrerequisites = itemRS.getString(1);
            }
            itemRS.close();
            rtestmtSelectItem.close();
            conn.close();
        }
        catch (Exception e) {
            if (SCORMDataBaseLayer._Debug) {
                e.printStackTrace();
            }
        }
        return strPrerequisites;
    }
    
    public String getStatus(final String userID, final String courseID, final String scoID) {
        String LessonStatus = new String();
        if (DebugIndicator.ON) {
            SCORMDataBaseLayer.log.entry("getStatus()");
            SCORMDataBaseLayer.log.info("****Select Prerequisites****");
        }
        final String rtestmtSelectItem = "SELECT LessonStatus FROM UserSCOInfo WHERE UserID = ? AND CourseID = ? AND SCOID = ?";
        try {
            final Connection conn = SCORMDataBaseLayer.ds1.getConnection();
            final PreparedStatement rtestmtLessonStatus = conn.prepareStatement(rtestmtSelectItem);
            if (SCORMDataBaseLayer._Debug) {
                SCORMDataBaseLayer.log.entry("getStatus()");
                SCORMDataBaseLayer.log.verbose("about to call item in RTEFile");
                SCORMDataBaseLayer.log.verbose("userID: " + userID);
                SCORMDataBaseLayer.log.verbose("courseID: " + courseID);
            }
            ResultSet itemRS = null;
            synchronized (rtestmtLessonStatus) {
                rtestmtLessonStatus.setString(1, userID);
                rtestmtLessonStatus.setString(2, courseID);
                rtestmtLessonStatus.setString(3, scoID);
                itemRS = rtestmtLessonStatus.executeQuery();
            }
            if (itemRS != null && itemRS.next()) {
                LessonStatus = itemRS.getString(1);
                itemRS.close();
            }
            rtestmtLessonStatus.close();
            conn.close();
        }
        catch (Exception e) {
            if (SCORMDataBaseLayer._Debug) {
                e.printStackTrace();
            }
        }
        return LessonStatus;
    }
    
    public String getMarks(final String userID, final String courseID, final String scoID) {
        String RawScore = new String();
        final String rtestmtSelectItem = "SELECT RawScore FROM UserSCOInfo WHERE UserID = ? AND CourseID = ? AND SCOID = ?";
        try {
            final Connection conn = SCORMDataBaseLayer.ds1.getConnection();
            final PreparedStatement rtestmtLessonStatus = conn.prepareStatement(rtestmtSelectItem);
            ResultSet itemRS = null;
            synchronized (rtestmtLessonStatus) {
                rtestmtLessonStatus.setString(1, userID);
                rtestmtLessonStatus.setString(2, courseID);
                rtestmtLessonStatus.setString(3, scoID);
                itemRS = rtestmtLessonStatus.executeQuery();
            }
            if (itemRS != null && itemRS.next()) {
                RawScore = itemRS.getString(1);
                itemRS.close();
            }
            rtestmtLessonStatus.close();
            conn.close();
        }
        catch (Exception e) {
            if (SCORMDataBaseLayer._Debug) {
                e.printStackTrace();
            }
        }
        return RawScore;
    }
    
    static {
        SCORMDataBaseLayer._Debug = DebugIndicator.ON;
        log = new SimpleLogger((Class)SCORMDataBaseLayer.class);
        SCORMDataBaseLayer.ds = CoreAdminInitHostInfo.ds;
        SCORMDataBaseLayer.ds1 = CoreAdminInitHostInfo.ds1;
    }
}
