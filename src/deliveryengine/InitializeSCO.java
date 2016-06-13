package deliveryengine;

import org.grlea.log.*;
import learnityInit.*;
import org.adl.datamodels.*;
import java.io.*;
import org.adl.datamodels.cmi.*;
import java.util.*;

public class InitializeSCO
{
    private static final SimpleLogger log;
    
    public void SCOInitialize(final String userID, final String courseID, final String scoID) {
        try {
            final String lessonMode = "";
            final boolean g_bSCOBrowse = lessonMode.equalsIgnoreCase("browse");
            System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu" + g_bSCOBrowse);
            System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuscoIDuuuuuuuuuuuuuuuuuuuuuuuuu" + scoID);
            final Vector<String> lesson = DataBaseLayer.getLessonStatus(userID, courseID, scoID);
            System.out.println("gggggggggggggggggggggggggg" + lesson.size());
            for (int i = 0; i < lesson.size(); ++i) {
                String lessonstatus = lesson.elementAt(i);
                System.out.println("lessonstatus===1===" + lessonstatus);
                if (lessonstatus.equals("not attempted")) {
                    lessonstatus = "incomplete";
                    DataBaseLayer.setLessonStatus(userID, courseID, scoID, lessonstatus);
                }
            }
        }
        catch (Exception exp) {
            InitializeSCO.log.entry("SCOInitialize()");
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" + exp.toString());
            InitializeSCO.log.error(exp.toString());
        }
    }
    
    public void SCOFinish(final String userID, final String courseID, final String scoID) {
        try {
            final String sessiontime = this.SCOReportSessionTime();
            System.out.println("+++++++++++++++++++++++++++++++sessiontime++++++++++++++++" + sessiontime);
            DataBaseLayer.setSessionTime(userID, courseID, scoID, sessiontime);
            final Vector<String> lesson = DataBaseLayer.getLessonStatus1(userID, courseID, scoID);
            System.out.println("gggggggggggggggggggggggggg" + lesson.size());
            for (int i = 0; i < lesson.size(); ++i) {
                String lessonstatus = lesson.elementAt(i);
                System.out.println("lessonstatus==2====" + lessonstatus);
                if (!lessonstatus.equals(null)) {
                    lessonstatus = "complete";
                    DataBaseLayer.setLessonStatus1(userID, courseID, scoID, lessonstatus);
                }
            }
            System.out.println("CALLED THE SCOFINISH FUNCTION ");
        }
        catch (Exception exp) {
            InitializeSCO.log.entry("SCOFinish()");
            InitializeSCO.log.error(exp.toString());
            exp.printStackTrace();
        }
    }
    
    public void setStatus(final String userID, final String courseID, final String scoID, final String status) {
        try {
            final String scoFile = Host.getRTEPath() + File.separatorChar + "SampleRTEFiles" + File.separatorChar + userID + File.separatorChar + courseID + File.separatorChar + scoID;
            InitializeSCO.log.entry("setStatus()");
            InitializeSCO.log.info("scoFile = " + scoFile);
            InitializeSCO.log.info("status = " + status);
            final FileInputStream fi = new FileInputStream(scoFile);
            final ObjectInputStream file_in = new ObjectInputStream(fi);
            final SCODataManager scoData = (SCODataManager)file_in.readObject();
            final CMICore lmsCore = scoData.getCore();
            lmsCore.setLessonStatus(status);
            InitializeSCO.log.entry("setStatus()");
            InitializeSCO.log.verbose("status1 = " + lmsCore.getLessonStatus().getValue());
            scoData.setCore(lmsCore);
            file_in.close();
            final FileOutputStream fo = new FileOutputStream(scoFile);
            final ObjectOutputStream out_file = new ObjectOutputStream(fo);
            out_file.writeObject(scoData);
            out_file.close();
            System.out.println("CALLED THE SETSTATUS FUNCTION ");
        }
        catch (Exception exp) {
            InitializeSCO.log.entry("setStatus()");
            InitializeSCO.log.error(exp.toString());
        }
    }
    
    public String MillisecondsToCMIDuration(final long n) {
        String hms = "";
        final String h = "000" + n / 3600000L;
        final long r1 = n % 3600000L;
        final String m = "0" + r1 / 60000L;
        final long r2 = r1 % 60000L;
        final String s = "0" + r2 / 1000L;
        final long r3 = r2 % 1000L;
        final String cs = "0" + Math.round(r3 / 10L);
        hms = h.substring(h.length() - 4) + ":" + m.substring(m.length() - 2) + ":";
        hms = hms + s.substring(s.length() - 2) + "." + cs.substring(cs.length() - 2);
        return hms;
    }
    
    public String SCOReportSessionTime() {
        final Date dtm = new Date();
        final long n = dtm.getTime();
        final String strSessionTime = this.MillisecondsToCMIDuration(n);
        return strSessionTime;
    }
    
    public void SCOSetStatusCompleted(final SCODataManager scoData) {
        final CMICore lmsCore = scoData.getCore();
        final String stat = "";
        final String lessonMode = lmsCore.getLessonMode().getValue();
        if (!lessonMode.equalsIgnoreCase("browse") && !stat.equalsIgnoreCase("completed") && !stat.equalsIgnoreCase("passed") && !stat.equalsIgnoreCase("failed")) {
            lmsCore.setLessonStatus("completed");
        }
    }
    
    static {
        log = new SimpleLogger((Class)InitializeSCO.class);
    }
}
