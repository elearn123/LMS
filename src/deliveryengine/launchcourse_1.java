package deliveryengine;

import org.grlea.log.*;
import org.directwebremoting.*;
import javax.servlet.http.*;
import org.w3c.dom.*;
import learnityInit.*;
import org.apache.commons.codec.binary.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.*;
import org.directwebremoting.io.*;
import org.adl.samplerte.server.*;
import org.xml.sax.*;
import java.util.zip.*;
import javax.xml.parsers.*;
import org.apache.xml.serialize.*;
import java.io.*;
import java.util.*;

import interfaceenginev2.*;
import learnityInit.index.*;

public class launchcourse_1
{
    public static final SimpleLogger log;
    Document manifest;
    static final int BUFFER = 2048;
    private ZipOutputStream zip;
    private FileOutputStream fileWriter;
    ArrayList bookMarkCache;
    
    public launchcourse_1() {
        this.manifest = null;
        this.zip = null;
        this.fileWriter = null;
        this.bookMarkCache = new ArrayList();
    }
    
    public String onselectTitle(final String s, final String s2) {
        String s3 = "false";
        final HttpSession session = WebContextFactory.get().getSession();
        final String courseID = (String)session.getAttribute("unit_id");
        if (courseID == null) {
            s3 = "true";
        }
        else {
            final String userID = (String)session.getAttribute("user_id");
            final String id = session.getId();
            final Hashtable hashtable = (Hashtable)session.getAttribute("SESSION_COURSE_ITEM");
            final Object value = hashtable.get("Identifier");
            final GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(new Date());
            final String string = gregorianCalendar.get(11) + ":" + gregorianCalendar.get(12) + ":" + gregorianCalendar.get(13);
            final String string2 = gregorianCalendar.get(1) + "-" + (gregorianCalendar.get(2) + 1) + "-" + gregorianCalendar.get(5);
            new StringBuilder().append(string2).append(" ").append(string).toString();
            hashtable.put("Identifier", s);
            session.setAttribute("SESSION_COURSE_ITEM", (Object)hashtable);
            final InitializeSCO initializeSCO = new InitializeSCO();
            if (value == null) {
                DataBaseLayer.InsertIntoLearnerLoginInfo(userID, courseID, s, string2, id, string);
                initializeSCO.SCOInitialize(userID, courseID, s);
                DataBaseLayer.Insertintoscouserinfo(userID, courseID, s, s2);
            }
            else {
                final String string3 = value.toString();
                DataBaseLayer.updateLoginInfo(userID, string3, id, string, string2, courseID);
                initializeSCO.SCOFinish(userID, courseID, string3);
                DataBaseLayer.InsertIntoLearnerLoginInfo(userID, courseID, s, string2, id, string);
                initializeSCO.SCOInitialize(userID, courseID, s);
                DataBaseLayer.Updateintoscouserinfo(userID, courseID, string3, s2);
                DataBaseLayer.Insertintoscouserinfo(userID, courseID, s, s2);
            }
        }
        return s3;
    }
    
    public String getIdentifier() {
        final HttpSession session = WebContextFactory.get().getSession();
        String s = (String)session.getAttribute("bookmarkidentifier");
        final String s2 = (String)session.getAttribute("bookmarkTopicTitle");
        if (s == null || s == "") {
            s = "0";
        }
        if (s != "0") {
            this.onselectTitle(s, s2);
        }
        return s;
    }
    
    public String checkCourseControl() {
        return DataBaseLayer.getCourseDetailsList((String)WebContextFactory.get().getSession().getAttribute("unit_id")).elementAt(5);
    }
    
    public String treeConstruct() {
        final long currentTimeMillis = System.currentTimeMillis();
        final HttpSession session = WebContextFactory.get().getSession();
        String s = (String)session.getAttribute("unit_id");
        final String s2 = (String)session.getAttribute("browser");
        final String s3 = (String)session.getAttribute("browserVersion");
        String s4 = (String)session.getAttribute("user_id");
        if (s == null) {
            s = "";
        }
        if (s4 == null) {
            s4 = "";
        }
        final String learning_Style = DataBaseLayer.getLearning_Style(s4);
        final Document parse = DataBaseLayer.parse(s, "csformat");
        final NodeList elementsByTagName = parse.getElementsByTagName("organization");
        Element element = null;
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            final Element element2 = (Element)elementsByTagName.item(i);
            if (element2.getAttributeNode("identifier").getValue().equals(learning_Style)) {
                element = element2;
                break;
            }
        }
        if (element == null) {
            final String value = ((Element)parse.getElementsByTagName("organizations").item(0)).getAttributeNode("default").getValue();
            System.out.println("defaultOrg = " + value);
            for (int j = 0; j < elementsByTagName.getLength(); ++j) {
                final Element element3 = (Element)elementsByTagName.item(j);
                if (element3.getAttributeNode("identifier").getValue().equals(value)) {
                    element = element3;
                    break;
                }
            }
        }
        if (element == null) {
            element = (Element)elementsByTagName.item(0);
        }
        String showTreeInOutputStream = "";
        String string = "<ul>\n";
        if (element != null) {
            final Element element4 = element;
            String nodeValue = element4.getElementsByTagName("title").item(0).getFirstChild().getNodeValue();
            System.out.println(" ======orgTitle===treeConstruct===== " + nodeValue);
            try {
                nodeValue = new String(nodeValue.getBytes(), "utf-8");
            }
            catch (Exception ex) {
                System.out.println("Error:" + ex);
            }
            string = string + "\n<li data=\"key: '" + nodeValue + "',tooltip: '" + nodeValue + "', description: '" + nodeValue + "' ,isFolder: true\">" + nodeValue;
            showTreeInOutputStream = this.showTreeInOutputStream(s, element4, parse);
        }
        System.out.println("time taken====" + (System.currentTimeMillis() - currentTimeMillis));
        return string + showTreeInOutputStream + "</ul>";
    }
    
    public String showTreeInOutputStream(final String s, final Element element, final Document document) {
        final String s2 = "";
        final NodeList childNodes = element.getChildNodes();
        String nodeValue = null;
        String nodeValue2 = null;
        String s3 = s2 + "\n<ul>";
        if (childNodes.getLength() > 0) {
            for (int i = 0; i < childNodes.getLength(); ++i) {
                final Node item = childNodes.item(i);
                final String nodeName = item.getNodeName();
                System.out.println("tagname==1= " + nodeName);
                if (nodeName.equalsIgnoreCase("item")) {
                    final NamedNodeMap attributes = item.getAttributes();
                    for (int j = 0; j < attributes.getLength(); ++j) {
                        final Node item2 = attributes.item(j);
                        if (item2.getNodeName().equalsIgnoreCase("identifier")) {
                            nodeValue = item2.getNodeValue();
                        }
                        if (item2.getNodeName().equalsIgnoreCase("identifierref")) {
                            nodeValue2 = item2.getNodeValue();
                        }
                    }
                    final int sequence = DataBaseLayer.getSequence(s, nodeValue);
                    final Element element2 = (Element)childNodes.item(i);
                    final String nodeValue3 = element2.getElementsByTagName("title").item(0).getFirstChild().getNodeValue();
                    System.out.println("strItemTitle=== " + nodeValue3);
                    System.out.println("strIdentifier==2= " + nodeValue);
                    System.out.println("resourseRef==if= " + nodeValue2);
                    final String nodeName2 = childNodes.item(i).getNodeName();
                    System.out.println("tagname====== " + nodeName2);
                    String s4;
                    if (nodeName2.equalsIgnoreCase("item")) {
                        if (nodeValue2 == null) {
                            System.out.println("resourseRef==if= " + nodeValue2);
                            s4 = s3 + "\n<li data=\"key: '" + nodeValue + "',tooltip: '" + nodeValue3 + "',url:'" + this.getResourseURL(nodeValue2, document, s) + "' , description: '" + nodeValue3 + "',sequence: '" + sequence + "', isFolder: false\">" + nodeValue3;
                        }
                        else {
                            System.out.println("resourseRef==else= " + nodeValue2);
                            s4 = s3 + "\n<li data=\"key: '" + nodeValue + "',tooltip: '" + nodeValue3 + "', url:'" + this.getResourseURL(nodeValue2, document, s) + "' ,description: '" + nodeValue3 + "',sequence: '" + sequence + "', isFolder: false \">" + nodeValue3;
                        }
                    }
                    else {
                        System.out.println("resourseRef= outer=else= " + nodeValue2);
                        s4 = s3 + "\n<li data=\"key: '" + nodeValue + "',tooltip: '" + nodeValue3 + "', url:'" + this.getResourseURL(nodeValue2, document, s) + "' ,description: '" + nodeValue3 + "',sequence: '" + sequence + "', isFolder: false \">" + nodeValue3;
                    }
                    s3 = s4 + "\n " + this.showTreeInOutputStream1(s, element2, document);
                }
            }
        }
        return s3 + "\n</ul>";
    }
    
    public String showTreeInOutputStream1(final String s, final Element element, final Document document) {
        String s2 = "";
        final NodeList childNodes = element.getChildNodes();
        String nodeValue = null;
        String nodeValue2 = null;
        final String s3 = null;
        final int n = 0;
        if (childNodes.getLength() > 0) {
            for (int i = 0; i < childNodes.getLength(); ++i) {
                if (childNodes.item(i).getNodeName().equalsIgnoreCase("item")) {
                    String s4 = s2 + "\n<ul>";
                    for (int j = 0; j < childNodes.getLength(); ++j) {
                        final Node item = childNodes.item(j);
                        if (item.getNodeName().equalsIgnoreCase("item")) {
                            final NamedNodeMap attributes = item.getAttributes();
                            for (int k = 0; k < attributes.getLength(); ++k) {
                                final Node item2 = attributes.item(k);
                                if (item2.getNodeName().equalsIgnoreCase("identifier")) {
                                    nodeValue = item2.getNodeValue();
                                }
                                if (item2.getNodeName().equalsIgnoreCase("identifierref")) {
                                    nodeValue2 = item2.getNodeValue();
                                }
                            }
                            final int sequence = DataBaseLayer.getSequence(s, nodeValue);
                            final Element element2 = (Element)childNodes.item(j);
                            final String nodeValue3 = element2.getElementsByTagName("title").item(0).getFirstChild().getNodeValue();
                            s4 = s4 + "\n<li data=\"key: '" + nodeValue + "',tooltip: '" + nodeValue3 + "',url: '" + this.getResourseURL(nodeValue2, document, s) + "' ,description: '" + nodeValue3 + "' ,sequence: '" + sequence + "' ,isFolder: false\">" + nodeValue3 + "\n " + this.showTreeInOutputStream1(s, element2, document);
                        }
                    }
                    s2 = s4 + "\n</ul>";
                }
            }
        }
        else {
            s2 = s2 + "\n<li data=\"key: '" + nodeValue + "',tooltip: '" + s3 + "', description: '" + s3 + "',sequence: '" + n + "', isFolder: true\">" + s3;
        }
        return s2;
    }
    
    public String getUnitName() {
        final HttpSession session = WebContextFactory.get().getSession();
        String s = (String)session.getAttribute("unit_id");
        final String s2 = (String)session.getAttribute("user_id");
        if (s == null) {
            s = "";
        }
        if (s2 == null) {}
        return "<b>" + DataBaseLayer.getUnitName(s) + "</b>";
    }
    
    public String getResourseURL(final String s, final Document document, final String s2) {
        final String s3 = "";
        boolean equalsIgnoreCase = false;
        final DebugSwitch debugSwitch = new DebugSwitch();
        if (document == null) {
            System.out.println("------->>>>>>>>>---------No Resource Data Found-----getResourseURL()------->>>>----");
        }
        else if (document.getElementsByTagName("resources").getLength() > 0) {
            final NodeList elementsByTagName = document.getElementsByTagName("resource");
            if (elementsByTagName.getLength() > 0) {
                for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                    final NamedNodeMap attributes = elementsByTagName.item(i).getAttributes();
                    String nodeValue = "";
                    for (int j = 0; j < attributes.getLength(); ++j) {
                        final Node item = attributes.item(j);
                        if (item.getNodeName().equalsIgnoreCase("href")) {
                            nodeValue = item.getNodeValue();
                        }
                        if (item.getNodeName().equalsIgnoreCase("identifier")) {
                            equalsIgnoreCase = item.getNodeValue().equalsIgnoreCase(s);
                        }
                        if (equalsIgnoreCase && !nodeValue.equals("") && nodeValue != null) {
                            Host.getServerDocumentPathEngine();
                            final String s4 = new String();
                            String s5;
                            if (nodeValue.startsWith("http://") || nodeValue.startsWith("https://") || nodeValue.startsWith("ftp://")) {
                                s5 = nodeValue;
                                System.out.println("------->>>>>>>>---inside if-----resourceRef----- " + s5);
                            }
                            else {
                                s5 = Host.getServerDocumentPathEngine() + s2 + "/" + nodeValue;
                                if (debugSwitch.ON) {
                                    System.out.println("------->>>>>>>>--------resourceRef----- " + s5);
                                }
                            }
                            if (s5.endsWith("asmt")) {
                                final String string = Host.getServerDocumentPathEngine() + s2 + "/" + nodeValue;
                                if (debugSwitch.ON) {
                                    System.out.println("------->>>>>>>>--------s----- " + string);
                                }
                                try {
                                    s5 = "./learnityasmtserver.assessmentportal.embeddedasmt.Assessment?s=" + new Base64().encode((Object)string);
                                }
                                catch (EncoderException ex) {}
                            }
                            return s5;
                        }
                    }
                }
            }
        }
        return s3;
    }
    
    public String UploadUnit(final String courseID, final String courseName, final FileTransfer fileTransfer) {
        String s = "Unit Imported Successfully";
        final DebugSwitch debugSwitch = new DebugSwitch();
        final String s2 = (String)WebContextFactory.get().getSession().getAttribute("user_id");
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        final String[] array = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        final int n = gregorianCalendar.get(2) + 1;
        final String string = gregorianCalendar.get(10) + ":" + gregorianCalendar.get(12);
        final String string2 = array[gregorianCalendar.get(2)] + " " + gregorianCalendar.get(5) + ", " + gregorianCalendar.get(1);
        new StringBuilder().append(string2).append(" ").append(string).toString();
        final String s3 = new String();
        try {
            final String serverDocumentPath;
            final String s4 = serverDocumentPath = Host.getServerDocumentPath();
            if (debugSwitch.ON) {
                System.out.println("---111--->>>>>>>-----dir name is : " + serverDocumentPath);
            }
            if (serverDocumentPath == null) {
                s = "Please supply uploadDir parameter";
            }
            final String string3 = s4 + courseID;
            if (debugSwitch.ON) {
                System.out.println("---222--->>>>>>>-----dir name is : " + string3);
            }
            final File file = new File(string3);
            final File file2 = new File(string3);
            if (!file2.exists()) {
                file2.mkdir();
            }
            final Object o = new FileUploaderPojo(fileTransfer);
            FileUploaderPojo.getMimeType();
            final InputStream inputStream = FileUploaderPojo.getInputStream();
            final String string4 = string3 + File.separator + FileUploaderPojo.getFilename();
            final String s5 = "";
            try {
                final File file3 = new File(string4);
                if (!file3.exists()) {
                    file3.createNewFile();
                }
                if (debugSwitch.ON) {
                    System.out.println("---333--->>>>>>>-----zip path+file is : " + string4);
                }
                final FileOutputStream fileOutputStream = new FileOutputStream(string4);
                final byte[] array2 = new byte[1024];
                try {
                    int read;
                    while (inputStream != null && (read = inputStream.read(array2)) != -1) {
                        fileOutputStream.write(array2, 0, read);
                    }
                    final PrintStream printStream = new PrintStream(fileOutputStream);
                    printStream.println(s5);
                    printStream.close();
                }
                catch (IOException ex) {
                    s = "Unit Import Failed";
                    if (debugSwitch.ON) {
                        System.out.println("--------->>>>>>>-----IOException : " + ex);
                    }
                }
            }
            catch (Exception ex2) {
                s = "Unit Import Failed";
                if (debugSwitch.ON) {
                    System.out.println("--------->>>>>>>-----Exception : " + ex2);
                }
            }
            final LMSPackageHandler lmsPackageHandler = new LMSPackageHandler();
            if (LMSPackageHandler.findManifest(string4)) {
                if (debugSwitch.ON) {
                    System.out.println("-------->>>>>>>----- goes inside findManifest() path " + string4);
                }
                LMSPackageHandler.extract(string4, "imsmanifest.xml", string3);
                final String string5 = string3 + File.separatorChar + "imsmanifest.xml";
                DataBaseLayer.insertCourse("csformat", courseID, string5, string2, s2,"");
                if (debugSwitch.ON) {
                    System.out.println("-------->>>>>>>-----insertCourse() successful ");
                }
                final LMSManifestHandler lmsManifestHandler = new LMSManifestHandler();
                final InputSource setUpInputSource = this.setUpInputSource(string5);
                lmsManifestHandler.setCourseID(courseID);
                lmsManifestHandler.setCourseName(courseName);
                lmsManifestHandler.setFileToParse(setUpInputSource);
                if (debugSwitch.ON) {
                    System.out.println("-------->>>>>>>-----inside processManifest() ... ");
                }
                lmsManifestHandler.processManifest();
                if (debugSwitch.ON) {
                    System.out.println("-------->>>>>>>-----processManifest() successful ");
                }
                try {
                    final String string6 = Host.getServerDocumentPath() + courseName + File.separatorChar + "sequence.obj";
                    if (debugSwitch.ON) {
                        System.out.println("-------->>>>>>>-----sequencingFileName : " + string6);
                    }
                    final File file4 = new File(string6);
                    final String parent = file4.getParent();
                    if (parent != null) {
                        final File file5 = new File(parent);
                        if (!file5.exists()) {
                            file5.mkdirs();
                        }
                    }
                    final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file4));
                    objectOutputStream.writeObject(lmsManifestHandler.getOrgsCopy());
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }
                catch (Exception ex3) {
                    s = "Unit Import Failed";
                    launchcourse_1.log.error("Error = " + ex3.toString());
                    ex3.printStackTrace();
                }
            }
            if (debugSwitch.ON) {
                System.out.println("----111.1---->>>>>>>-----get the zip file ... " + string4);
            }
            final String zipFiles = this.getZipFiles(string4, courseName, courseID);
            if (!zipFiles.equals("")) {
                s = zipFiles;
            }
        }
        catch (Exception ex4) {
            s = "Unit Import Failed";
            ex4.toString();
            ex4.printStackTrace();
        }
        this.deleteDirectory(new File(Host.getServerDocumentPath() + courseID + File.separatorChar + FileUploaderPojo.getFilename()));
        return s;
    }
    
    public InputSource setUpInputSource(final String s) {
        final InputSource inputSource = new InputSource();
        return this.setupFileSource(s);
    }
    
    public InputSource setupFileSource(final String s) {
        final DebugSwitch debugSwitch = new DebugSwitch();
        launchcourse_1.log.entry("InputSource setupFileSource()");
        try {
            final File file = new File(s);
            if (file.isFile()) {
                return new InputSource(new FileReader(file));
            }
            System.out.println("--------->>>>>>>-----not a file : " + s);
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
            System.out.println("-------->>>>>>>-----setupFileSource() : NullPointerException : ");
            launchcourse_1.log.error("Null pointer exception" + ex);
        }
        catch (SecurityException ex2) {
            ex2.printStackTrace();
            System.out.println("-------->>>>>>>-----setupFileSource() : SecurityException : ");
            launchcourse_1.log.error("Security Exception" + ex2);
        }
        catch (FileNotFoundException ex3) {
            ex3.printStackTrace();
            System.out.println("-------->>>>>>>-----setupFileSource() : FileNotFoundException : ");
            launchcourse_1.log.error("File Not Found Exception" + ex3);
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
            System.out.println("-------->>>>>>>-----setupFileSource() : FileNotFoundException : ");
            launchcourse_1.log.error("File Not Found Exception" + ex4);
        }
        return new InputSource();
    }
    
    public String getZipFiles(final String s, final String s2, final String s3) {
        final String s4 = (String)WebContextFactory.get().getSession().getAttribute("user_id");
        String s5 = "";
        try {
            final String string = "" + Host.getServerDocumentPath() + s3 + File.separator;
            final byte[] array = new byte[1024];
            final ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(s));
            for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
                final String name = zipEntry.getName();
                System.out.println("entryname " + name);
                final File file = new File(string + name);
                if (zipEntry.isDirectory()) {
                    file.mkdirs();
                    zipInputStream.closeEntry();
                }
                else {
                    final String parent = file.getParent();
                    System.out.println("===========parent=============" + parent);
                    if (parent != null) {
                        final File file2 = new File(parent);
                        System.out.println("===============parentFile===============" + file2);
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                    }
                    final FileOutputStream fileOutputStream = new FileOutputStream(string + name);
                    int read;
                    while ((read = zipInputStream.read(array, 0, 1024)) > -1) {
                        fileOutputStream.write(array, 0, read);
                    }
                    DataBaseLayer.insertContent("content_management_object", file.getName(), s3, s4,"0",0,"");
                    fileOutputStream.close();
                    zipInputStream.closeEntry();
                }
            }
            zipInputStream.close();
        }
        catch (Exception ex) {
            s5 = "Unit Import Failed";
            ex.printStackTrace();
        }
        return s5;
    }
    
    public String getNextResourceNode(final String s) {
        final HttpSession session = WebContextFactory.get().getSession();
        final String s2 = (String)session.getAttribute("unit_id");
        final String s3 = (String)session.getAttribute("user_id");
        //final DebugSwitch debugSwitch = new DebugSwitch();
        final Document parse = DataBaseLayer.parse(s2, "csformat");
        final String learning_Style = DataBaseLayer.getLearning_Style(s3);
        final ImsManifest imsManifest = new ImsManifest(parse, s3, s2);
        final Hashtable<String, String> hashtable = (Hashtable<String, String>)session.getAttribute("SESSION_COURSE_ITEM");
        final String value = hashtable.get(s2);
        System.out.println("======>>>>>>=======1==oIdentifier= " + (Object)value);
        String scoID = null;
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        final String string = gregorianCalendar.get(11) + ":" + gregorianCalendar.get(12) + ":" + gregorianCalendar.get(13);
        final String string2 = gregorianCalendar.get(1) + "-" + (gregorianCalendar.get(2) + 1) + "-" + gregorianCalendar.get(5);
        final InitializeSCO initializeSCO = new InitializeSCO();
        if (value == null) {
            if (s.equals("1")) {
                scoID = imsManifest.getIdentifierForFirstItemSkippingNoContent(learning_Style);
            }
            if (s.equals("2")) {
                scoID = imsManifest.getIdentifierForLastItemSkippingNoContent(learning_Style);
            }
            initializeSCO.SCOInitialize(s3, s2, scoID);
            DataBaseLayer.InsertIntoLearnerLoginInfo(s3, s2, scoID, string2, session.getId(), string);
            System.out.println("======>>>>>>=======2==identifier= " + scoID);
        }
        else {
            scoID = value.toString();
            DataBaseLayer.updateLoginInfo(s3, scoID, session.getId(), string, string2, s2);
            initializeSCO.SCOFinish(s3, s2, scoID);
            if (s.equals("1")) {
                scoID = imsManifest.getIdentifierOfNextNodeSkippingNoContent(scoID, learning_Style);
            }
            if (s.equals("2")) {
                scoID = imsManifest.getIdentifierOfPreviousNodeSkippingNoContent(scoID, learning_Style);
            }
            DataBaseLayer.InsertIntoLearnerLoginInfo(s3, s2, scoID, string2, session.getId(), string);
            initializeSCO.SCOInitialize(s3, s2, scoID);
            System.out.println("======>>>>>>=======3==identifier= " + scoID);
        }
        hashtable.put(s2, scoID);
        session.setAttribute("SESSION_COURSE_ITEM", (Object)hashtable);
        return scoID;
    }
    
    public String getNextResourceNode1(final String s) {
        final HttpSession session = WebContextFactory.get().getSession();
        final String s2 = (String)session.getAttribute("unit_id");
        final String s3 = (String)session.getAttribute("user_id");
        final DebugSwitch debugSwitch = new DebugSwitch();
        final Document parse = DataBaseLayer.parse(s2, "csformat");
        final String learning_Style = DataBaseLayer.getLearning_Style(s3);
        final ImsManifest imsManifest = new ImsManifest(parse, s3, s2);
        final Hashtable<String, String> hashtable = (Hashtable<String, String>)session.getAttribute("SESSION_COURSE_ITEM");
        final String value = hashtable.get(s2);
        System.out.println("======>>>>>>=======1==oIdentifier= " + (Object)value);
        String s4 = null;
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        final String string = gregorianCalendar.get(11) + ":" + gregorianCalendar.get(12) + ":" + gregorianCalendar.get(13);
        final String string2 = gregorianCalendar.get(1) + "-" + (gregorianCalendar.get(2) + 1) + "-" + gregorianCalendar.get(5);
        final InitializeSCO initializeSCO = new InitializeSCO();
        if (value == null) {
            if (s.equals("1")) {
                s4 = imsManifest.getIdentifierForFirstItemSkippingNoContent(learning_Style);
            }
            if (s.equals("2")) {
                s4 = imsManifest.getIdentifierForLastItemSkippingNoContent(learning_Style);
            }
            initializeSCO.SCOInitialize(s3, s2, s4);
            DataBaseLayer.InsertIntoLearnerLoginInfo(s3, s2, s4, string2, session.getId(), string);
            System.out.println("======>>>>>>=======2==identifier= " + s4);
        }
        else {
            s4 = value.toString();
            DataBaseLayer.updateLoginInfo(s3, s4, session.getId(), string, string2, s2);
            initializeSCO.SCOFinish(s3, s2, s4);
            if (s.equals("1")) {
                s4 = imsManifest.getIdentifierOfNextNodeSkippingNoContent(s4, learning_Style);
            }
            if (s.equals("2")) {
                s4 = imsManifest.getIdentifierOfPreviousNodeSkippingNoContent(s4, learning_Style);
            }
            DataBaseLayer.InsertIntoLearnerLoginInfo(s3, s2, s4, string2, session.getId(), string);
            initializeSCO.SCOInitialize(s3, s2, s4);
            System.out.println("======>>>>>>=======3==identifier= " + s4);
        }
        hashtable.put(s2, s4);
        session.setAttribute("SESSION_COURSE_ITEM", (Object)hashtable);
        final String resourseRef = imsManifest.getResourseRef(s4, learning_Style);
        System.out.println("======>>>>>>resourseref=======getNextResourceNode1()=== " + resourseRef);
        final String resourseURL = this.getResourseURL(resourseRef, parse, s2);
        System.out.println("======>>>>>>=======getNextResourceNode1()==URL= " + resourseURL);
        return resourseURL;
    }
    
    public String getItemTitle(final String s) {
        final String s2 = (String)WebContextFactory.get().getSession().getAttribute("unit_id");
        if (new DebugSwitch().ON) {
            System.out.println("------------->>>>>>>>>>>>>--------------- inside getItemTitle() ... ");
        }
        final Document parse = DataBaseLayer.parse(s2, "csformat");
        if (parse == null) {
            System.out.println("------->>>>>>>-----getItemTitle-----No Data Found--------");
        }
        else {
            final NodeList elementsByTagName = parse.getElementsByTagName("item");
            if (elementsByTagName.getLength() > 0) {
                final int n = 0;
                if (n < elementsByTagName.getLength()) {
                    return ((Element)elementsByTagName.item(n)).getFirstChild().getFirstChild().getNodeValue();
                }
            }
        }
        return "";
    }
    
    public void setUserSCODetails(final String s) {
        final HttpSession session = WebContextFactory.get().getSession();
        final String s2 = (String)session.getAttribute("unit_id");
        final String s3 = (String)session.getAttribute("user_id");
        final Document parse = DataBaseLayer.parse(s2, "csformat");
        if (new DebugSwitch().ON) {
            System.out.println("------------->>>>>>>>>>>>>--------------- inside setUserSCODetails() ... ");
        }
        DataBaseLayer.addUserSCODetails(s3, s2, s, this.getItemTitle(s), this.getResourseURL(s, parse, s2));
    }
    
    public String showHomePage() throws IOException {
        final HttpSession session = WebContextFactory.get().getSession();
        final String s = (String)session.getAttribute("unit_id");
        final String s2 = (String)session.getAttribute("user_id");
        final String learning_Style = DataBaseLayer.getLearning_Style(s2);
        final String statusHomePage = DataBaseLayer.getStatusHomePage(s);
        launchcourse_1.log.debug("************home_page_status*******************" + statusHomePage);
        String s3 = "";
        if (statusHomePage.equals("Empty")) {
            s3 = "<font color=\"#ff0000\"><center></center></font>\n";
            launchcourse_1.log.debug("************html*****1**************" + s3);
        }
        else if (statusHomePage.equals("Custom")) {
            s3 = "<font color=\"#ff0000\"><center>" + DataBaseLayer.getTitleHomePage(s) + "</center></font>";
            launchcourse_1.log.debug("************html******2*************" + s3);
        }
        else if (statusHomePage.equals("Status")) {
            final String courseName = DataBaseLayer.getCourseName(s);
            String s4 = "";
            String s5 = "Na";
            String s6 = "Na";
            final Vector<String[]> getusageInfo = DataBaseLayer.getusageInfo(s, s2);
            if (getusageInfo != null) {
                for (int i = 0; i < getusageInfo.size(); ++i) {
                    final String[] array = getusageInfo.elementAt(0);
                    s4 = array[0];
                    s5 = array[1];
                    s6 = array[2];
                    final String s7 = array[3];
                }
            }
            int n = 0;
            final Vector<Integer> userscoInfo = DataBaseLayer.getUserscoInfo(s, s2);
            if (userscoInfo != null) {
                for (int j = 0; j < userscoInfo.size(); j += 3) {
                    n += userscoInfo.elementAt(j + 1);
                }
            }
            final String string = "" + n + " Sec";
            final String noOfTimeUsed = DataBaseLayer.getNoOfTimeUsed(s, s2);
            Document document = null;
            final DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
            try {
                instance.setValidating(true);
                document = instance.newDocumentBuilder().newDocument();
            }
            catch (ParserConfigurationException ex) {}
            final Element element = document.createElement("div");
            document.appendChild(element);
            final Element element2 = document.createElement("table");
            element.appendChild(element2);
            final Element element3 = document.createElement("tr");
            element2.appendChild(element3);
            final Element element4 = document.createElement("td");
            element3.appendChild(element4);
            final Element element5 = document.createElement("div");
            element5.appendChild(document.createTextNode("Usage Information"));
            element5.setAttribute("style", "background-color:#907A53;color:black;text-align:center;font-size:14px;font-family;tohama,verdana;font-weight:bold;");
            element4.appendChild(element5);
            final Element element6 = document.createElement("tr");
            element2.appendChild(element6);
            final Element element7 = document.createElement("td");
            element6.appendChild(element7);
            final Element element8 = document.createElement("div");
            element8.appendChild(document.createTextNode("Unit Name"));
            element8.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
            element7.appendChild(element8);
            final Element element9 = document.createElement("td");
            element6.appendChild(element9);
            final Element element10 = document.createElement("div");
            element10.appendChild(document.createTextNode(courseName));
            element10.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal;");
            element9.appendChild(element10);
            final Element element11 = document.createElement("tr");
            element2.appendChild(element11);
            final Element element12 = document.createElement("td");
            element11.appendChild(element12);
            final Element element13 = document.createElement("div");
            element13.appendChild(document.createTextNode("Date Regstered"));
            element13.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
            element12.appendChild(element13);
            final Element element14 = document.createElement("td");
            element11.appendChild(element14);
            final Element element15 = document.createElement("div");
            element15.appendChild(document.createTextNode(s4));
            element15.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal;");
            element14.appendChild(element15);
            final Element element16 = document.createElement("tr");
            element2.appendChild(element16);
            final Element element17 = document.createElement("td");
            element16.appendChild(element17);
            final Element element18 = document.createElement("div");
            element18.appendChild(document.createTextNode("Access Allowed Till"));
            element18.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
            element17.appendChild(element18);
            final Element element19 = document.createElement("td");
            element16.appendChild(element19);
            final Element element20 = document.createElement("div");
            element20.appendChild(document.createTextNode(s5));
            element20.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal;");
            element19.appendChild(element20);
            final Element element21 = document.createElement("tr");
            element2.appendChild(element21);
            final Element element22 = document.createElement("td");
            element21.appendChild(element22);
            final Element element23 = document.createElement("div");
            element23.appendChild(document.createTextNode("Total Access Time"));
            element23.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
            element22.appendChild(element23);
            final Element element24 = document.createElement("td");
            element21.appendChild(element24);
            final Element element25 = document.createElement("div");
            element25.appendChild(document.createTextNode(s6));
            element25.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal;");
            element24.appendChild(element25);
            final Element element26 = document.createElement("tr");
            element2.appendChild(element26);
            final Element element27 = document.createElement("td");
            element26.appendChild(element27);
            final Element element28 = document.createElement("div");
            element28.appendChild(document.createTextNode("Time Used"));
            element28.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
            element27.appendChild(element28);
            final Element element29 = document.createElement("td");
            element26.appendChild(element29);
            final Element element30 = document.createElement("div");
            element30.appendChild(document.createTextNode(string));
            element30.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal;");
            element29.appendChild(element30);
            final Element element31 = document.createElement("tr");
            element2.appendChild(element31);
            final Element element32 = document.createElement("td");
            element31.appendChild(element32);
            final Element element33 = document.createElement("div");
            element33.appendChild(document.createTextNode("No of Time Accessed"));
            element33.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
            element32.appendChild(element33);
            final Element element34 = document.createElement("td");
            element31.appendChild(element34);
            final Element element35 = document.createElement("div");
            element35.appendChild(document.createTextNode(noOfTimeUsed));
            element35.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal;");
            element34.appendChild(element35);
            final Element element36 = document.createElement("table");
            element.appendChild(element36);
            final Element element37 = document.createElement("tr");
            element36.appendChild(element37);
            final Element element38 = document.createElement("td");
            element38.setAttribute("colspan", "2");
            element37.appendChild(element38);
            final Element element39 = document.createElement("div");
            element38.appendChild(element39);
            element39.appendChild(document.createTextNode("Unit Information"));
            element39.setAttribute("style", "background-color:#907A53;color:black;text-align:left;font-size:14px;font-family;tohama,verdana;font-weight:bold;");
            final int n2 = 0;
            Element element40 = null;
            final Document parse = DataBaseLayer.parse(s, "csformat");
            final NodeList elementsByTagName = parse.getElementsByTagName("organization");
            for (int k = 0; k < elementsByTagName.getLength(); ++k) {
                final Element element41 = (Element)elementsByTagName.item(k);
                if (element41.getAttributeNode("identifier").getValue().equals(learning_Style)) {
                    element40 = element41;
                    break;
                }
            }
            if (element40 == null) {
                final String value = ((Element)parse.getElementsByTagName("organizations").item(0)).getAttributeNode("default").getValue();
                for (int l = 0; l < elementsByTagName.getLength(); ++l) {
                    final Element element42 = (Element)elementsByTagName.item(l);
                    if (element42.getAttributeNode("identifier").getValue().equals(value)) {
                        element40 = element42;
                        break;
                    }
                }
            }
            if (element40 == null) {
                element40 = (Element)elementsByTagName.item(0);
            }
            if (element40 != null) {
                this.showIntroductionTree(element40, " ", s2, s, element36, document, parse);
            }
            try {
                s3 = getHTML(document);
            }
            catch (Exception ex2) {}
            new StringBuilder().append("").append(n2).toString();
        }
        else {
            s3 = "<font color=\"#ff0000\"><center></center></font>\n";
            launchcourse_1.log.debug("************html***3****************" + s3);
        }
        launchcourse_1.log.debug("************html***4****************" + s3);
        return s3;
    }
    
    public void showIntroductionTree(final Element element, String string, final String s, final String s2, final Element element2, final Document document, final Document document2) {
        final NodeList childNodes = element.getChildNodes();
        string += "    ";
        String nodeValue = null;
        System.out.println("========nl======" + childNodes + "==========nl.getLength()===" + childNodes.getLength());
        for (int i = 0; i < childNodes.getLength(); ++i) {
            final Node item = childNodes.item(i);
            System.out.println("========nodeItem==getNodeName====" + item.getNodeName());
            final String nodeName = item.getNodeName();
            System.out.println("=================tagname========" + nodeName);
            if (nodeName.equalsIgnoreCase("item")) {
                final NamedNodeMap attributes = item.getAttributes();
                for (int j = 0; j < attributes.getLength(); ++j) {
                    final Node item2 = attributes.item(j);
                    if (item2.getNodeName().equalsIgnoreCase("identifier")) {
                        nodeValue = item2.getNodeValue();
                        System.out.println("strIdentifier in 1 if============" + nodeValue);
                    }
                    if (item2.getNodeName().equalsIgnoreCase("identifierref")) {
                        item2.getNodeValue();
                    }
                }
                String nodeValue2 = ((Element)childNodes.item(i)).getElementsByTagName("title").item(0).getFirstChild().getNodeValue();
                try {
                    nodeValue2 = new String(nodeValue2.getBytes(), "utf-8");
                }
                catch (Exception ex) {
                    System.out.println("Error:" + ex);
                }
                if (!this.getResourseURL(nodeValue, document2, s2).equals("")) {
                    final Element element3 = document.createElement("tr");
                    element2.appendChild(element3);
                    final Element element4 = document.createElement("td");
                    element3.appendChild(element4);
                    final Element element5 = document.createElement("div");
                    element4.appendChild(element5);
                    element5.appendChild(document.createTextNode(string + nodeValue2));
                    element5.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
                    final Element element6 = document.createElement("td");
                    element3.appendChild(element6);
                    final Element element7 = document.createElement("div");
                    element6.appendChild(element5);
                    element7.appendChild(document.createTextNode(DataBaseLayer.getStatus(s, s2, nodeValue)));
                    element7.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal");
                }
                else {
                    final Element element8 = document.createElement("tr");
                    element2.appendChild(element8);
                    final Element element9 = document.createElement("td");
                    element8.appendChild(element9);
                    final Element element10 = document.createElement("div");
                    element9.appendChild(element10);
                    element10.appendChild(document.createTextNode(string + nodeValue2));
                    element10.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
                    final Element element11 = document.createElement("td");
                    element8.appendChild(element11);
                    final Element element12 = document.createElement("div");
                    element11.appendChild(element12);
                    element12.appendChild(document.createTextNode(DataBaseLayer.getStatus(s, s2, nodeValue)));
                    element12.setAttribute("style", "background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal");
                }
                this.showIntroductionTree((Element)childNodes.item(i), string, s, s2, element2, document, document2);
            }
        }
    }
    
    public static final String getHTML(final Document document) throws Exception {
        try {
            final OutputFormat outputFormat = new OutputFormat(document);
            final StringWriter stringWriter = new StringWriter();
            final XMLSerializer xmlSerializer = new XMLSerializer((Writer)stringWriter, outputFormat);
            xmlSerializer.asDOMSerializer();
            xmlSerializer.serialize(document.getDocumentElement());
            return stringWriter.toString();
        }
        catch (Exception ex) {
            throw new Exception("XML to String Err: " + ex.getMessage());
        }
    }
    
    public void setSearchKeyToSession(final String s) {
        WebContextFactory.get().getSession().setAttribute("SEARCH_CRITERIA", (Object)s);
    }
    
    public String getSearchResult() {
        String nodeValue = "";
        String nodeValue2 = "";
        final Hashtable hashtable = new Hashtable();
        final HttpSession session = WebContextFactory.get().getSession();
        final String s = (String)session.getAttribute("unit_id");
        final String s2 = (String)session.getAttribute("user_id");
        final Document parse = DataBaseLayer.parse(s, "csformat");
        final String s3 = (String)session.getAttribute("SEARCH_CRITERIA");
        final String string = "You searched for : " + s3 + ".";
        String s4;
        if (!s3.equals("") && s3 != null) {
            final Hashtable<String, Vector<String>> searchResultFromSCO = DataBaseLayer.getSearchResultFromSCO(s, s2, s3);
            s4 = string + "<br>" + searchResultFromSCO.size() + "results returned.<br>";
            final Iterator<String> iterator = searchResultFromSCO.keySet().iterator();
            int n = 1;
            while (iterator.hasNext()) {
                final Vector<String> vector = searchResultFromSCO.get(iterator.next());
                final String s5 = vector.elementAt(0);
                final String s6 = vector.elementAt(1);
                final NodeList elementsByTagName = parse.getElementsByTagName("item");
                if (elementsByTagName.getLength() > 0) {
                    for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                        final Node item = elementsByTagName.item(i);
                        if (item.getNodeName().equalsIgnoreCase("item")) {
                            final NamedNodeMap attributes = item.getAttributes();
                            for (int j = 0; j < attributes.getLength(); ++j) {
                                final Node item2 = attributes.item(j);
                                if (item2.getNodeName().equalsIgnoreCase("identifier")) {
                                    nodeValue = item2.getNodeValue();
                                }
                                if (item2.getNodeName().equalsIgnoreCase("identifierref")) {
                                    nodeValue2 = item2.getNodeValue();
                                }
                            }
                            if (s6.equalsIgnoreCase(nodeValue)) {
                                s4 = s4 + "<br>" + n + ". <a href=\"" + this.getResourseURL(nodeValue2, parse, s) + "\">" + s5 + "</a><br>";
                            }
                        }
                    }
                }
                ++n;
            }
        }
        else {
            s4 = "Please Enter A Valid Search String";
        }
        session.setAttribute("SEARCH_CRITERIA", (Object)"");
        return s4;
    }
    
    public String[] getEmbeddedAssessment(final String s) {
        final String[] array = new String[2];
        System.out.println("url==getEmbeddedAssessment=== " + s);
        final String substring = s.substring(s.indexOf(47) + 1);
        final String substring2 = substring.substring(substring.indexOf(47) + 1);
        final String substring3 = substring2.substring(substring2.indexOf(47) + 1);
        final String substring4 = substring3.substring(substring3.indexOf(47) + 1);
        final String s2 = (String)WebContextFactory.get().getSession().getAttribute("unit_id");
        System.out.println(" ======courseid===getEmbeddedAssessment===== " + s2);
        array[0] = substring4;
        array[1] = s2;
        return array;
    }
    
    public boolean deleteDirectory(final File file) {
        if (file.exists() && file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; ++i) {
                if (listFiles[i].isDirectory()) {
                    this.deleteDirectory(listFiles[i]);
                }
                else {
                    listFiles[i].delete();
                }
            }
        }
        return file.delete();
    }
    
    public void zipFolder(final String s, final String s2) {
        try {
            this.fileWriter = new FileOutputStream(s2);
            this.zip = new ZipOutputStream(this.fileWriter);
            this.addFolderToZip("", s);
        }
        catch (Exception ex) {
            System.out.println("Exception in zipFolder()");
            ex.printStackTrace();
            try {
                this.zip.flush();
                this.zip.close();
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        finally {
            try {
                this.zip.flush();
                this.zip.close();
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
        }
    }
    
    private void addToZip(final String s, final String s2) {
        final File file = new File(s2);
        if (file.isDirectory()) {
            this.addFolderToZip(s, s2);
        }
        else {
            final byte[] array = new byte[1024];
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(s2);
                if (s.equals("")) {
                    this.zip.putNextEntry(new ZipEntry(file.getName()));
                }
                else {
                    this.zip.putNextEntry(new ZipEntry(s + "/" + file.getName()));
                }
                int read;
                while ((read = fileInputStream.read(array)) > 0) {
                    this.zip.write(array, 0, read);
                }
            }
            catch (Exception ex) {
                System.out.println("Exception in addToZip()");
                ex.printStackTrace();
                try {
                    fileInputStream.close();
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
            finally {
                try {
                    fileInputStream.close();
                }
                catch (Exception ex3) {
                    ex3.printStackTrace();
                }
            }
        }
    }
    
    private void addFolderToZip(final String s, final String s2) {
        final String[] list = new File(s2).list();
        try {
            for (int i = 0; i < list.length; ++i) {
                if (new File(s2 + "/" + list[i]).isFile()) {
                    this.addToZip(s, s2 + "/" + list[i]);
                }
                else {
                    String string;
                    if (s.equals("")) {
                        string = list[i];
                    }
                    else {
                        string = s + "/" + list[i];
                    }
                    this.addToZip(string, s2 + "/" + list[i]);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Exception in addFolderToZip()");
            ex.printStackTrace();
        }
    }
    
    public void close() throws IOException {
        if (this.zip != null) {
            this.zip.close();
        }
    }
    
    public FileTransfer UnitExportFile(final String s) {
        String s2 = "";
        InputStream inputStream = null;
        if (!DataBaseLayer.isCourseContentExists(s)) {
            return null;
        }
        try {
            s2 = DataBaseLayer.getUnitName(s);
            s2 = s2.replaceAll("\\s+", "_");
            final String string = Host.getServerDocumentPath() + s2 + ".zip";
            System.out.println("------>>>>>>------destZipFile-- " + string);
            final String string2 = Host.getServerDocumentPath() + s + File.separatorChar;
            System.out.println("------>>>>>>------dirName-- " + string2);
            this.zipFolder(string2, string);
            System.out.println("------>>>>>>------zip creation successful-- ");
            inputStream = new FileInputStream(string);
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        final FileDownloaderPojo fileDownloaderPojo = new FileDownloaderPojo(inputStream, "", s2 + ".zip");
        return FileDownloaderPojo.returnFileFormat();
    }
    
    public String lunchCourseAll(final String s, final String s2, final String s3, final String s4, final String s5) {
        final HttpSession session = WebContextFactory.get().getSession();
        session.setAttribute("unit_id", (Object)s3);
        session.setAttribute("browser", (Object)s);
        session.setAttribute("browserVersion", (Object)s2);
        session.setAttribute("bookmarkidentifier", (Object)s4);
        session.setAttribute("bookmarkTopicTitle", (Object)s5);
        final String s6 = (String)session.getAttribute("user_id");
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        DataBaseLayer.insert_into_studynamicinfo(s6, session.getId(), gregorianCalendar.get(1) + "-" + (gregorianCalendar.get(2) + 1) + "-" + gregorianCalendar.get(5) + " " + (gregorianCalendar.get(11) + ":" + gregorianCalendar.get(12) + ":" + gregorianCalendar.get(13)), s3);
        session.setAttribute("SESSION_COURSE_ITEM", (Object)new Hashtable());
        return "";
    }
    
    public void createIndex(final String s) {
        new IndexFiles().createIndex(s);
    }
    
    public String getTotalUnit() {
        String s = "";
        final String s2 = "<select name=\"locomboselect\" onchange=\"combo_onclick()\">";
        final HttpSession session = WebContextFactory.get().getSession();
        final String s3 = (String)session.getAttribute("course_id");
        final String s4 = (String)session.getAttribute("unit_id");
        final Vector<String> totalUnit = DataBaseLayer.getTotalUnit((String)session.getAttribute("user_id"), s3);
        for (int i = 0; i < totalUnit.size(); i += 2) {
            final String s5 = totalUnit.elementAt(i);
            final String s6 = totalUnit.elementAt(i + 1);
            if (s4.equals(s5)) {
                s = s + "<option value=\"" + s5 + "\" selected=\"selected\">" + s6 + "</option>";
            }
            else {
                s = s + "<option value=\"" + s5 + "\">" + s6 + "</option>";
            }
        }
        return s2 + "<option value=\"0\">Choose One</option>" + s + "</select>";
    }
    
    public String setUnitID(final String s) {
        final String s2 = "";
        final HttpSession session = WebContextFactory.get().getSession();
        final String s3 = (String)session.getAttribute("course_id");
        session.setAttribute("unit_id", (Object)s);
        return s2;
    }
    
    public String setSessionSequence(final String s) {
        final String s2 = "";
        final HttpSession session = WebContextFactory.get().getSession();
        System.out.println("===============sequence===in session======" + s);
        session.setAttribute("sequence", (Object)s);
        return s2;
    }
    
    public String getNextItemNode(final String s) {
        final HttpSession session = WebContextFactory.get().getSession();
        final String courseid = (String)session.getAttribute("unit_id");
        final String userid = (String)session.getAttribute("user_id");
        String s2 = (String)session.getAttribute("sequence");
        System.out.println("===============sequence===== 11 ====" + s2);
        if (s2 == null || s2.equals("")) {
            s2 = "0";
        }
        System.out.println("===============sequence=========" + s2);
        final int maxSequence = DataBaseLayer.getMaxSequence(courseid);
        System.out.println("===============max_sequence======" + maxSequence);
        int int1 = Integer.parseInt(s2);
        final DebugSwitch debugSwitch = new DebugSwitch();
        final Document parse = DataBaseLayer.parse(courseid, "csformat");
        DataBaseLayer.getLearning_Style(userid);
        final ImsManifest imsManifest = new ImsManifest(parse, userid, courseid);
        final Hashtable hashtable = (Hashtable)session.getAttribute("SESSION_COURSE_ITEM");
        System.out.println("======>>>>>>=======1==oIdentifier= " + hashtable.get(courseid));
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        new StringBuilder().append(gregorianCalendar.get(11)).append(":").append(gregorianCalendar.get(12)).append(":").append(gregorianCalendar.get(13)).toString();
        new StringBuilder().append(gregorianCalendar.get(1)).append("-").append(gregorianCalendar.get(2) + 1).append("-").append(gregorianCalendar.get(5)).toString();
        String nextIdentifierFromSequence;
        if (int1 != maxSequence) {
            ++int1;
            System.out.println("===============seq===if===" + int1);
            nextIdentifierFromSequence = DataBaseLayer.getNextIdentifierFromSequence(courseid, int1);
        }
        else {
            System.out.println("===============seq===else===" + int1);
            nextIdentifierFromSequence = "End";
        }
        System.out.println("=========identifier======" + nextIdentifierFromSequence);
        this.setSessionSequence(Integer.toString(int1));
        hashtable.put(courseid, nextIdentifierFromSequence);
        session.setAttribute("SESSION_COURSE_ITEM", (Object)hashtable);
        return nextIdentifierFromSequence;
    }
    
    public String getPreviousItemNode(final String s) {
        final HttpSession session = WebContextFactory.get().getSession();
        final String courseid = (String)session.getAttribute("unit_id");
        final String userid = (String)session.getAttribute("user_id");
        String s2 = (String)session.getAttribute("sequence");
        System.out.println("===============sequence======" + s2);
        if (s2 == null || s2.equals("")) {
            s2 = "0";
        }
        System.out.println("===============max_sequence======" + DataBaseLayer.getMaxSequence(courseid));
        int int1 = Integer.parseInt(s2);
        final DebugSwitch debugSwitch = new DebugSwitch();
        final Document parse = DataBaseLayer.parse(courseid, "csformat");
        DataBaseLayer.getLearning_Style(userid);
        final ImsManifest imsManifest = new ImsManifest(parse, userid, courseid);
        final Hashtable hashtable = (Hashtable)session.getAttribute("SESSION_COURSE_ITEM");
        System.out.println("======>>>>>>=======1==oIdentifier= " + hashtable.get(courseid));
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        new StringBuilder().append(gregorianCalendar.get(11)).append(":").append(gregorianCalendar.get(12)).append(":").append(gregorianCalendar.get(13)).toString();
        new StringBuilder().append(gregorianCalendar.get(1)).append("-").append(gregorianCalendar.get(2) + 1).append("-").append(gregorianCalendar.get(5)).toString();
        String nextIdentifierFromSequence;
        if (int1 != 0) {
            --int1;
            nextIdentifierFromSequence = DataBaseLayer.getNextIdentifierFromSequence(courseid, int1);
        }
        else {
            nextIdentifierFromSequence = "First";
        }
        this.setSessionSequence(Integer.toString(int1));
        hashtable.put(courseid, nextIdentifierFromSequence);
        session.setAttribute("SESSION_COURSE_ITEM", (Object)hashtable);
        return nextIdentifierFromSequence;
    }
    
    public String getFirstItemNode(final String s) {
        final HttpSession session = WebContextFactory.get().getSession();
        final String courseid = (String)session.getAttribute("unit_id");
        final String userid = (String)session.getAttribute("user_id");
        final int n = 0;
        final DebugSwitch debugSwitch = new DebugSwitch();
        final Document parse = DataBaseLayer.parse(courseid, "csformat");
        DataBaseLayer.getLearning_Style(userid);
        final ImsManifest imsManifest = new ImsManifest(parse, userid, courseid);
        final Hashtable hashtable = (Hashtable)session.getAttribute("SESSION_COURSE_ITEM");
        System.out.println("======>>>>>>=======1==oIdentifier= " + hashtable.get(courseid));
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        new StringBuilder().append(gregorianCalendar.get(11)).append(":").append(gregorianCalendar.get(12)).append(":").append(gregorianCalendar.get(13)).toString();
        new StringBuilder().append(gregorianCalendar.get(1)).append("-").append(gregorianCalendar.get(2) + 1).append("-").append(gregorianCalendar.get(5)).toString();
        final String nextIdentifierFromSequence = DataBaseLayer.getNextIdentifierFromSequence(courseid, n);
        this.setSessionSequence(Integer.toString(n));
        hashtable.put(courseid, nextIdentifierFromSequence);
        session.setAttribute("SESSION_COURSE_ITEM", (Object)hashtable);
        return nextIdentifierFromSequence;
    }
    
    public String getLastItemNode(final String s) {
        final HttpSession session = WebContextFactory.get().getSession();
        final String courseid = (String)session.getAttribute("unit_id");
        final String userid = (String)session.getAttribute("user_id");
        final int maxSequence = DataBaseLayer.getMaxSequence(courseid);
        final DebugSwitch debugSwitch = new DebugSwitch();
        final Document parse = DataBaseLayer.parse(courseid, "csformat");
        DataBaseLayer.getLearning_Style(userid);
        final ImsManifest imsManifest = new ImsManifest(parse, userid, courseid);
        final Hashtable hashtable = (Hashtable)session.getAttribute("SESSION_COURSE_ITEM");
        System.out.println("======>>>>>>=======1==oIdentifier= " + hashtable.get(courseid));
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        new StringBuilder().append(gregorianCalendar.get(11)).append(":").append(gregorianCalendar.get(12)).append(":").append(gregorianCalendar.get(13)).toString();
        new StringBuilder().append(gregorianCalendar.get(1)).append("-").append(gregorianCalendar.get(2) + 1).append("-").append(gregorianCalendar.get(5)).toString();
        final String nextIdentifierFromSequence = DataBaseLayer.getNextIdentifierFromSequence(courseid, maxSequence);
        this.setSessionSequence(Integer.toString(maxSequence));
        hashtable.put(courseid, nextIdentifierFromSequence);
        session.setAttribute("SESSION_COURSE_ITEM", (Object)hashtable);
        return nextIdentifierFromSequence;
    }
    
    static {
        log = new SimpleLogger((Class)launchcourse_1.class, true);
    }
    
    public class DebugSwitch
    {
        public boolean ON;
        
        public DebugSwitch() {
            this.ON = true;
        }
    }
}
