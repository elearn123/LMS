package learnitysce;

import org.apache.commons.net.telnet.*;
import java.io.*;
import java.util.*;

import org.grlea.log.*;

public class StreamingUtils
{
    public static final SimpleLogger log;
    private TelnetClient telnet;
    private InputStream in;
    private PrintStream outstream;
    
    public StreamingUtils() {
        this.telnet = new TelnetClient();
    }
    
    public String startServer(final String Unit, final String vlcip) {
        String serverStatus = "Server Started";
        try {
            final Vector<Vector<String>> scedetails = SceDataBaseLayer.getscevlcdetails(Unit);
            if (scedetails != null) {
                for (int i = 0; i < scedetails.size(); ++i) {
                    final Vector<String> vUnit = scedetails.elementAt(i);
                    final String inputport = vUnit.elementAt(0);
                    //final String inputmethod = vUnit.elementAt(1);
                    final String outputmethod = vUnit.elementAt(2);
                    final String ip = vUnit.elementAt(3);
                    final String outputPort = vUnit.elementAt(4);
                    final String recording = vUnit.elementAt(5);
                    final String enc = vUnit.elementAt(6);
                    final String video_transcoding = vUnit.elementAt(7);
                    final String video_bitrate = vUnit.elementAt(8);
                    final String scale = vUnit.elementAt(9);
                    System.out.println("recording=" + recording);
                    System.out.println("enc=" + enc);
                    final String student_input_port = SceDataBaseLayer.getstudentinputport(Unit);
                    this.sendCommand("new " + Unit + " broadcast enabled");
                    this.sendCommand("setup " + Unit + " input udp://@:" + inputport + "");
                    this.sendCommand("setup " + Unit + " output #duplicate{dst=mosaic-bridge{id=1,height=300,width=300}}");
                    this.sendCommand("new " + Unit + "_student broadcast enabled");
                    this.sendCommand("setup " + Unit + "_student input udp://@:" + student_input_port + "");
                    this.sendCommand("setup " + Unit + "_student output #duplicate{dst=mosaic-bridge{id=2,height=300,width=300}}");
                    this.sendCommand("new background broadcast enabled");
                    this.sendCommand("setup background input fake:");
                    this.sendCommand("setup background option mosaic-width=300");
                    this.sendCommand("setup background option mosaic-height=600");
                    this.sendCommand("setup background option mosaic-rows=2");
                    this.sendCommand("setup background option mosaic-cols=1");
                    this.sendCommand("setup background option mosaic-position=1");
                    this.sendCommand("setup background option mosaic-order=\"1,2\"");
                    this.sendCommand("setup background option mosaic-borderw=300");
                    this.sendCommand("setup background option mosaic-borderh=10");
                    this.sendCommand("setup background option fake-file=\"cone_360x270.png\"");
                    this.sendCommand("setup background option fake-width=300");
                    this.sendCommand("setup background option fake-height=600");
                    this.sendCommand("setup background option fake-fps=\"8\"");
                    this.sendCommand("setup background output #transcode{sfilter=mosaic,vcodec=" + video_transcoding + ",vb=" + video_bitrate + ",scale=" + scale + "}:bridge-in{id-offset=100}:duplicate{dst=std{access=" + outputmethod + ",mux=" + enc + ",dst=" + ip + ":" + outputPort + "}}");
                    System.out.println("--->>>--new " + Unit + " broadcast enabled");
                    System.out.println("--->>>--setup " + Unit + " input udp://@:" + inputport + "");
                    System.out.println("--->>>--setup " + Unit + " output #duplicate{dst=mosaic-bridge{id=1,height=300,width=300}}");
                    System.out.println("--->>>--new " + Unit + "_student broadcast enabled");
                    System.out.println("--->>>--setup " + Unit + "_student input udp://@:" + student_input_port + "");
                    System.out.println("--->>>--setup " + Unit + "_student output #duplicate{dst=mosaic-bridge{id=2,height=300,width=300}}");
                    System.out.println("--->>>--new background broadcast enabled");
                    System.out.println("--->>>--setup background input fake:");
                    System.out.println("--->>>--setup background option mosaic-width=300");
                    System.out.println("--->>>--setup background option mosaic-height=600");
                    System.out.println("--->>>--setup background option mosaic-rows=2");
                    System.out.println("--->>>--setup background option mosaic-cols=1");
                    System.out.println("--->>>--setup background option mosaic-position=1");
                    System.out.println("--->>>--setup background option mosaic-order=\"1,2\"");
                    System.out.println("--->>>--setup background option mosaic-borderw=300");
                    System.out.println("--->>>--setup background option mosaic-borderh=10");
                    System.out.println("--->>>--setup background option fake-file=\"cone_360x270.png\"");
                    System.out.println("--->>>--setup background option fake-width=300");
                    System.out.println("--->>>--setup background option fake-height=600");
                    System.out.println("--->>>--setup background option fake-fps=\"8\"");
                    System.out.println("--->>>--setup background output #transcode{sfilter=mosaic,vcodec=" + video_transcoding + ",vb=" + video_bitrate + ",scale=" + scale + "}:bridge-in{id-offset=100}:duplicate{dst=std{access=" + outputmethod + ",mux=" + enc + ",dst=" + ip + ":" + outputPort + "}}");
                    this.sendCommand("control background play");
                    this.sendCommand("control " + Unit + " play");
                    this.sendCommand("control " + Unit + "_student play");
                    System.out.println("--->>>--control background play");
                    System.out.println("--->>>--control " + Unit + " play");
                    System.out.println("--->>>--control " + Unit + "_student play");
                }
            }
        }
        catch (Exception e) {
            serverStatus = "Server Cannot be started";
            StreamingUtils.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        return serverStatus;
    }
    
    public String stopServer(final String Unit) {
        String serverStatus = "Server stopped";
        try {
            this.sendCommand("control " + Unit + "2 stop");
        }
        catch (Exception e) {
            StreamingUtils.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
            serverStatus = "Server is not stopped";
        }
        return serverStatus;
    }
    
    public String startStudent(final String Unit, final String vlcip) {
        String serverStatus = "Student Server Started";
        try {
            final Vector<Vector<String>> scedetailsstudent = SceDataBaseLayer.getscevlcdetailsForStudent(Unit);
            if (scedetailsstudent != null) {
                for (int i = 0; i < scedetailsstudent.size(); ++i) {
                    final Vector<String> vUnit = scedetailsstudent.elementAt(i);
                    final String inputport = vUnit.elementAt(0);
                    final String inputmethod = vUnit.elementAt(1);
                    final String outputmethod = vUnit.elementAt(2);
                    final String ip = vUnit.elementAt(3);
                    final String outputPort = vUnit.elementAt(4);
                    final String rec = vUnit.elementAt(5);
                    final String encap = vUnit.elementAt(6);
                    this.sendCommand("new student broadcast enabled");
                    this.sendCommand("setup student input " + outputmethod + "://@:" + outputPort + "");
                    if (inputmethod.equals("http")) {
                        if (rec.equals("T")) {
                            this.sendCommand("setup student output #duplicate{dst=std{access=file,mux=" + encap + ",url=" + Unit + "_student.avi},dst=std{access=http,mux=" + encap + ",url=" + ip + ":" + inputport + "}}");
                        }
                        else {
                            this.sendCommand("setup student output #duplicate{dst=std{access=http,mux=" + encap + ",url=" + ip + ":" + inputport + "}}");
                        }
                    }
                    else if (rec.equals("T")) {
                        this.sendCommand("setup student output #rtp{dst=" + ip + ",sdp=rtsp://" + ip + "/" + Unit + "student.sdp} :duplicate{dst=std{access=file,mux=" + encap + ",url=" + Unit + "_student.avi}}");
                    }
                    else {
                        this.sendCommand("setup student output #rtp{dst=" + ip + ",sdp=rtsp://" + ip + "/" + Unit + "student.sdp}");
                    }
                    this.sendCommand("control student play");
                }
            }
        }
        catch (Exception e) {
            serverStatus = "Student Server Not Started";
            StreamingUtils.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        return serverStatus;
    }
    
    public String Telnet_Connection(final String Unit, final String TelnetPort, final String vlcip, final String pass) {
        String serverStatus = "Server Connected";
        try {
            StreamingUtils.log.debug("------>>>>>>--------TelnetPort=" + TelnetPort);
            int PortTel = 4212;
            if (!TelnetPort.equals("")) {
                PortTel = Integer.parseInt(TelnetPort);
            }
            SceDataBaseLayer.insertservermgmt(Unit, TelnetPort, vlcip, pass);
            this.telnet.connect(vlcip, PortTel);
            this.in = this.telnet.getInputStream();
            this.outstream = new PrintStream(this.telnet.getOutputStream());
            this.readUntil("Password:");
            this.write(pass);
        }
        catch (Exception e) {
            serverStatus = "Server is not connected";
            StreamingUtils.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        return serverStatus;
    }
    
    public String Telnet_Disconnection() {
        String serverStatus = "Server Disconnected";
        try {
            this.telnet.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
            serverStatus = "Failed to disconnect";
            StreamingUtils.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        return serverStatus;
    }
    
    public String readUntil(final String pattern) {
        try {
            final char lastChar = pattern.charAt(pattern.length() - 1);
            final StringBuffer sb = new StringBuffer();
           // final boolean found = false;
            char ch = (char)this.in.read();
            while (true) {
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar && sb.toString().endsWith(pattern)) {
                    break;
                }
                ch = (char)this.in.read();
            }
            return sb.toString();
        }
        catch (Exception e) {
            StreamingUtils.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
            return null;
        }
    }
    
    public String sendCommand(final String command) {
        try {
            //final String prompt = ">";
            System.out.println("=============command==========" + command);
            this.write(command);
        }
        catch (Exception e) {
            System.out.println("====================exception in sendcommand=======" + e);
        }
        return null;
    }
    
    public void write(final String value) {
        try {
            if (this.outstream != null) {
                System.out.println("=================value=============" + value);
            }
            this.outstream.println(value);
            this.outstream.flush();
            System.out.println(value);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static {
        log = new SimpleLogger((Class<StreamingUtils>)StreamingUtils.class, true);
    }
}
