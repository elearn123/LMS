package learnityasmtserver.assessmentadmin.standaloneasmt;

import interfaceenginev2.EncryptedFileDownloaderPojo;
import interfaceenginev2.FileDownloaderPojo;
import interfaceenginev2.FileUploaderPojo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringBufferInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import learnityInit.Host;
import learnityasmtserver.assessmentadmin.dbconnection.DataBaseLayer;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.parsers.DOMParser;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.io.FileTransfer;
import org.grlea.log.DebugLevel;
import org.grlea.log.SimpleLogger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class StAsmtAdmin
{
    private static String algorithm;
    private static Key key;
    private static Cipher cipher;
    public static final SimpleLogger log;
    
    public String treeConstruct() {
        String str2 = "";
        final Vector<Vector<String>> vCname = DataBaseLayer.getCname();
        if (vCname.size() != 0) {
            str2 = this.createTree(vCname);
        }
        return str2;
    }
    
    public String createTree(final Vector<Vector<String>> vAllTopics) {
        String str1 = "";
        String str2 = "";
        String str3 = "";
        str1 = "\n<ul>";
        for (int j = 0; j < vAllTopics.size(); ++j) {
            str3 = treeprint(vAllTopics.elementAt(j));
            str2 += str3;
        }
        str1 += str2;
        str1 += "\n</ul>";
        return str1;
    }
    
    private static String treeprint(final Vector<String> vParent) {
        String str = "";
        final String id = vParent.elementAt(0);
        final String name = vParent.elementAt(1);
        final Vector<String[]> vChildList = DataBaseLayer.getCnames(name);
        if (vChildList.size() > 0) {
            str = str + "\n<li data=\"key: '" + id + "',tooltip: '" + name + "', description: '" + name + "' ,isFolder: true\">" + name;
            str += "\n<ul>";
            for (int i = 0; i < vChildList.size(); ++i) {
                str += treeprint(vChildList.elementAt(i));
            }
            str += "\n</ul>";
        }
        else {
            str = "\n<li data=\"key: '" + id + "',tooltip: '" + name + "', description: '" + name + "'\">" + name;
        }
        return str;
    }
    
    private static String treeprint(final String[] Parent) {
        String str = "";
        final Vector<String[]> vChildList = DataBaseLayer.getCnames(Parent[1]);
        if (vChildList.size() > 0) {
            str = str + "\n<li data=\"key: '" + Parent[0] + "',tooltip: '" + Parent[1] + "', description: '" + Parent[1] + "' ,isFolder: true\">" + Parent[1];
            str += "\n<ul>";
            for (int i = 0; i < vChildList.size(); ++i) {
                str += treeprint(vChildList.elementAt(i));
            }
            str += "\n</ul>";
        }
        else {
            str = "\n<li data=\"key: '" + Parent[0] + "',tooltip: '" + Parent[1] + "', description: '" + Parent[1] + "'\">" + Parent[1];
        }
        return str;
    }
    
    public String ClearAllPreviewResult(final String AssessTitleSelect) {
        final String message = "Successfully Cleared";
        final int AssessTitleSelect2 = Integer.parseInt(AssessTitleSelect);
        DataBaseLayer.deleteAllPreviewResult(AssessTitleSelect2);
        return message;
    }
    
    public String createTableAsmtRegistration(final String AssessTitleSelect, final String registerby) {
        final String s = "";
        String selectgroup = "";
        if (registerby.equals("All")) {
            selectgroup = "All";
        }
        if (registerby.equals("All")) {
            selectgroup = "All";
        }
        if (registerby.equals("user")) {
            selectgroup = "0";
        }
        if (!registerby.equals("group")) {
            DataBaseLayer.createTempAssessmentRegistration(AssessTitleSelect, selectgroup);
        }
        return s;
    }
    
    public String createTableAsmtRegistration(final String AssessTitleSelect, final String registerby, final String selectgroup) {
        final String s = "";
        DataBaseLayer.createTempAssessmentRegistration(AssessTitleSelect, selectgroup);
        return s;
    }
    
    public String createTableAsmtReportGeneration(final String AssessTitleSelect, final String equalPercentage, final String toPercentage) {
        final String s = "";
        final int full_marks = DataBaseLayer.selectFullMarks(AssessTitleSelect);
        DataBaseLayer.createtable1(AssessTitleSelect, equalPercentage, toPercentage, full_marks);
        return s;
    }
    
    public String createTableAsmtDetailsReport(final String AssessTitleSelect) {
        final String s = "";
        DataBaseLayer.createtableDetailsReport(AssessTitleSelect);
        return s;
    }
    
    public String createTableAsmtSummary(final String AssessTitleSelect) {
        final String s = "";
        DataBaseLayer.createTableSummary(AssessTitleSelect);
        return s;
    }
    
    public String deleteAllAssessmentUserRegistration(final String AssessmentSelect, String sendmail) {
        final String ssss = "";
        final Vector<Vector<String>> vUserList = DataBaseLayer.getAllAssessmentUser(AssessmentSelect);
        final String assessmenttitle = DataBaseLayer.getAssessmentTitle(AssessmentSelect);
        String s = "";
        final String s2 = "Unregistered from Assessment";
        System.out.println("=========1=============vUserList========" + vUserList);
        System.out.println("==========2============sendmail========" + sendmail);
        if (sendmail == null) {
            sendmail = "";
        }
        String sendmailfrom = "";
        final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
        final String key1 = "mailsendfrom";
        sendmailfrom = rb.getString(key1);
        String mailbodyheading = "";
        final String key2 = "mailbodyheading";
        mailbodyheading = rb.getString(key2);
        System.out.println("==========3============sendmailfrom========" + sendmailfrom);
        String server_title = DataBaseLayer.getMailServerTitle();
        System.out.println("==========4============server_title========" + server_title);
        if (server_title == null) {
            server_title = "";
        }
        final String s3 = mailbodyheading + "\r Dear Sir/Mam,                                                     \r \n" + "\r We are sorry to unregistar you for the online assessment test " + assessmenttitle + "\r\n";
        if (vUserList != null) {
            for (int i = 0; i < vUserList.size(); ++i) {
                final Vector<String> vUser = vUserList.elementAt(i);
                final String strUsrId = vUser.elementAt(0);
                System.out.println("=======5=====userid=======" + strUsrId);
                s = DataBaseLayer.getMailId(strUsrId);
                System.out.println("=======6======getMailId=======" + s);
                if (sendmail.equals("yes")) {
                    try {
                        final Properties properties = System.getProperties();
                        properties.put("mail.smtp.host", server_title);
                        final Session session = Session.getInstance(properties, null);
                        final MimeMessage mimemessage = new MimeMessage(session);
                        final String s4 = sendmailfrom;
                        System.out.println("======7======s=======" + s);
                        mimemessage.setFrom((Address)new InternetAddress(s4));
                        final InternetAddress[] ainternetaddress = { new InternetAddress(s) };
                        mimemessage.setRecipients(Message.RecipientType.TO, (Address[])ainternetaddress);
                        mimemessage.setSubject(s2);
                        mimemessage.setContent((Object)s3, "text/plain");
                        Transport.send((Message)mimemessage);
                    }
                    catch (Throwable throwable) {
                        System.out.println("Exception " + throwable.getMessage());
                    }
                }
            }
        }
        boolean flag = DataBaseLayer.deleteAllAssessmentUserRegistration(AssessmentSelect);
        if (flag==true) 
        	return "Successfully unregistered all users";
        else 
        	return "Error";
    }
    
    public String deleteAllAssessmentGroupRegistration(final String AssessmentSelect, String sendmail) {
        final String mess = "";
        String s = "";
        final String s2 = "Unregistered from Assessment";
        final String assessmenttitle = DataBaseLayer.getAssessmentTitle(AssessmentSelect);
        System.out.println("======================sendmail========" + sendmail);
        if (sendmail == null) {
            sendmail = "";
        }
        String sendmailfrom = "";
        final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
        final String key1 = "mailsendfrom";
        sendmailfrom = rb.getString(key1);
        String mailbodyheading = "";
        final String key2 = "mailbodyheading";
        mailbodyheading = rb.getString(key2);
        String server_title = DataBaseLayer.getMailServerTitle();
        if (server_title == null) {
            server_title = "";
        }
        final String s3 = mailbodyheading + "\r Dear Sir/Mam,                                                     \r \n" + "\r We are sorry to unregistar you for the online assessment test " + assessmenttitle + "\r\n";
        final Vector<Vector<String>> vGroupList = DataBaseLayer.getGroupDetailsList();
        if (vGroupList != null) {
            for (int p = 0; p < vGroupList.size(); ++p) {
                final Vector<String> vGroup = vGroupList.elementAt(p);
                final String strGrId = vGroup.elementAt(0);
                System.out.println("=========group========" + strGrId);
                final Vector<String> stuList = DataBaseLayer.getUserinGroup(strGrId);
                for (int j = 0; j < stuList.size(); ++j) {
                    final String stu = stuList.elementAt(j);
                    System.out.println("=========user========" + stu);
                    s = DataBaseLayer.getMailId(stu);
                    if (sendmail.equals("yes")) {
                        if (!server_title.equals("")) {
                            try {
                                final Properties properties = System.getProperties();
                                properties.put("mail.smtp.host", server_title);
                                final Session session = Session.getInstance(properties, null);
                                final MimeMessage mimemessage = new MimeMessage(session);
                                final String s4 = sendmailfrom;
                                mimemessage.setFrom((Address)new InternetAddress(s4));
                                final InternetAddress[] ainternetaddress = { new InternetAddress(s) };
                                mimemessage.setRecipients(Message.RecipientType.TO, (Address[])ainternetaddress);
                                mimemessage.setSubject(s2);
                                mimemessage.setContent((Object)s3, "text/plain");
                                Transport.send((Message)mimemessage);
                            }
                            catch (Throwable throwable) {
                                System.out.println("Exception " + throwable.getMessage());
                            }
                        }
                    }
                }
            }
        }
        DataBaseLayer.deleteAllAssessmentGroupRegistration(AssessmentSelect);
        return mess;
    }
    
    public String getAssessmentTitle(final String AssessTitleSelect) {
        String asmttitle = "";
        asmttitle = DataBaseLayer.getAssessmentTitle(AssessTitleSelect);
        System.out.println("=========1112======asmttitle=========" + asmttitle);
        return asmttitle;
    }
    
    public String UploadFile(final String qbid, final FileTransfer file) {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String dirName = "";
        final String strLocation = Host.getAdminPath();
        System.out.println("strLocation===" + strLocation);
        dirName = strLocation;
        File ufile = null;
        System.out.println("dir name is:" + dirName);
        if (dirName == null) {
            html = "Please supply uploadDir parameter";
        }
        dirName = strLocation + File.separatorChar + "ObjectBank" + qbid;
        ufile = new File(dirName);
        if (!ufile.exists()) {
            ufile.mkdir();
        }
        String thisLine = "";
        final FileUploaderPojo fu = new FileUploaderPojo(file);
        final String MimeType = FileUploaderPojo.getMimeType();
        final InputStream is = FileUploaderPojo.getInputStream();
        final String filename = FileUploaderPojo.getFilename();
        dirName = dirName + File.separator + filename;
        String content = "";
        try {
            final File f = new File(dirName);
            if (!f.exists()) {
                f.createNewFile();
            }
            final BufferedReader myInput = new BufferedReader(new InputStreamReader(is));
            while ((thisLine = myInput.readLine()) != null) {
                if (content.equals("")) {
                    content = thisLine;
                }
                else {
                    content = content + "\n" + thisLine;
                }
            }
            final FileOutputStream out = new FileOutputStream(dirName);
            final PrintStream p = new PrintStream(out);
            p.println(content);
            p.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String strSize = "";
        final File OBfile = new File(dirName);
        Long size2 = OBfile.length();
        System.out.println("size2===" + size2);
/*        strSize = DataBaseLayer.getSizeOfQB(qbid);
        Long QBsize = Long.parseLong(strSize);
        System.out.println("QBsize===" + QBsize);
*/        if (size2 != null) {
            final long l = size2;
            final Long size3 = size2 + l;
            strSize = size3.toString();
        }
        else {
            strSize = size2.toString();
        }
        try {
            DataBaseLayer.updateQuestionBank(qbid, strSize, filename);
            final Vector<Vector> vObject = this.ParseObjectBank(dirName);
            final Vector<Integer> vObject2 = vObject.elementAt(0);
            final Vector<String[]> vObject3 = vObject.elementAt(1);
            final Vector<String[]> vObject4 = vObject.elementAt(2);
            final Integer itemlength = vObject2.elementAt(0);
            final Vector<ArrayList> vObject5 = vObject.elementAt(3);
            for (int i = 0; i < itemlength; ++i) {
                String[] strObj2 = vObject3.elementAt(i);
                String[] strObj3 = vObject4.elementAt(i);
                final int count3 = 0;
                DataBaseLayer.addQuestionBankItems(qbid, strObj3[0], strObj2[0]);
                final ArrayList a1 = vObject5.elementAt(i);
                for (int i2 = 0; i2 < a1.size(); i2 += 2) {
                    final String flabel = a1.get(i2).toString();
                    final String fentry = a1.get(i2 + 1).toString();
                    DataBaseLayer.addQuestionMetadata(qbid, flabel, fentry);
                }
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        return html;
    }
    
    public String UploadDecryptedFile(final String qbid, final FileTransfer file) throws Exception {
        setUp();
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        String dirName = "";
        final String strLocation = dirName = Host.getAdminPath();
        File ufile = null;
        System.out.println("dir name is:" + dirName);
        if (dirName == null) {
            html = "Please supply uploadDir parameter";
        }
        dirName = strLocation + File.separatorChar + "ObjectBank" + qbid;
        ufile = new File(dirName);
        if (!ufile.exists()) {
            ufile.mkdir();
        }
        final String thisLine = "";
        final FileUploaderPojo fu = new FileUploaderPojo(file);
        final String MimeType = FileUploaderPojo.getMimeType();
        final InputStream is = FileUploaderPojo.getInputStream();
        final String filename = FileUploaderPojo.getFilename();
        dirName = dirName + File.separator + filename;
        StAsmtAdmin.log.debug("*******dirName*****************" + dirName);
        final String content = "";
        try {
            final File f = new File(dirName);
            if (!f.exists()) {
                f.createNewFile();
            }
            StAsmtAdmin.cipher.init(2, StAsmtAdmin.key);
            final CipherInputStream cis = new CipherInputStream(is, StAsmtAdmin.cipher);
            final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int bytesread = 0;
            byte[] data = new byte[4000];
            StAsmtAdmin.log.debug("********UploadDecryptedFile*********1************");
            while (true) {
                StAsmtAdmin.log.debug("********UploadDecryptedFile*********2*************");
                bytesread = cis.read(data);
                if (bytesread == -1) {
                    break;
                }
                buffer.write(data, 0, bytesread);
                final FileOutputStream fos = new FileOutputStream("/temp/b.txt");
                fos.write(data, 0, bytesread);
                StAsmtAdmin.log.debug("********UploadDecryptedFile*********3************");
            }
            data = buffer.toByteArray();
            final String recovered = decrypt(data);
            StAsmtAdmin.log.debug("*******recovered*************" + recovered);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String strSize = "";
        final File file_size = new File(dirName);
        Long size2 = file_size.length();
        size2 = Long.parseLong(DataBaseLayer.getSizeOfQB(qbid));
        System.out.println("size1===aaaa==" + size2);
        if (size2 != null) {
            final long l = size2;
            final Long size3 = size2 + l;
            strSize = size3.toString();
        }
        else {
            strSize = size2.toString();
        }
        try {
            DataBaseLayer.updateQuestionBank(qbid, strSize, filename);
            final Vector<Vector> vObject = this.ParseObjectBank(dirName);
            final Vector<Integer> vObject2 = vObject.elementAt(0);
            final Vector<String[]> vObject3 = vObject.elementAt(1);
            final Vector<String[]> vObject4 = vObject.elementAt(2);
            final Integer itemlength = vObject2.elementAt(0);
            final Vector<ArrayList> vObject5 = vObject.elementAt(3);
            for (int i = 0; i < itemlength; ++i) {
                String[] strObj2 = vObject3.elementAt(i);
                strObj2 = vObject4.elementAt(i);
                final int count3 = 0;
                DataBaseLayer.addQuestionBankItems(qbid, strObj2[0], strObj2[0]);
                final ArrayList a1 = vObject5.elementAt(i);
                for (int i2 = 0; i2 < a1.size(); i2 += 2) {
                    final String flabel = a1.get(i2).toString();
                    final String fentry = a1.get(i2 + 1).toString();
                    DataBaseLayer.addQuestionMetadata(qbid, flabel, fentry);
                }
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        return html;
    }
    
    public FileTransfer DownloadFile(final String qb_id) {
        InputStream in = null;
        String attachmentname = Host.getAdminPath();
        attachmentname = attachmentname + File.separatorChar + "ObjectBank.ob";
        try {
            final FileOutputStream fout = new FileOutputStream(attachmentname);
            final OutputStreamWriter out = new OutputStreamWriter(fout);
            final String ObjectBank = "ObjectBank";
            out.write("<?xml version='1.0' encoding='UTF-8'?>\n");
            out.write("<questestinterop>\r\n");
            out.write("<objectbank title=\"Object Bank\" ident=\"" + ObjectBank + "\">\r\n");
            out.write("<qticomment></qticomment>\n");
            final Vector<Vector> s2 = DataBaseLayer.getQuestionBankDetails(qb_id);
            final Vector<String[]> vObject2 = s2.elementAt(0);
            StAsmtAdmin.log.debug("************vObject2.size()********************" + vObject2.size());
            for (int i = 0; i < vObject2.size(); ++i) {
                final String[] idetails2 = vObject2.elementAt(i);
                StAsmtAdmin.log.debug("***********idetails2******************" + idetails2[0]);
                out.write(idetails2[0]);
            }
            out.write("</objectbank>\n");
            out.write("</questestinterop>\n");
            out.close();
            String strFileName = "";
            String strFileId = attachmentname;
            if (strFileId != null) {
                strFileId = strFileId.replace('\\', File.separatorChar);
                strFileId = (strFileName = strFileId.replace('/', File.separatorChar));
                final File f1 = new File(strFileId);
                in = new BufferedInputStream(new FileInputStream(f1));
            }
        }
        catch (IOException e) {
            StAsmtAdmin.log.fatal("The Error Message");
            StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        final FileDownloaderPojo fd = new FileDownloaderPojo(in, "", "ObjectBank.ob");
        return FileDownloaderPojo.returnFileFormat();
    }
    
    public FileTransfer DownloadEncryptedFile(final String qb_id) throws Exception {
        InputStream in = null;
        String attachmentname = Host.getAdminPath();
        attachmentname = attachmentname + File.separatorChar + "ObjectBank.ob";
        try {
            final FileOutputStream fout = new FileOutputStream(attachmentname);
            final OutputStreamWriter out = new OutputStreamWriter(fout);
            final String ObjectBank = "ObjectBank";
            out.write("<?xml version='1.0' encoding='UTF-8'?>\n");
            out.write("<questestinterop>\r\n");
            out.write("<objectbank title=\"Object Bank\" ident=\"" + ObjectBank + "\">\r\n");
            out.write("<qticomment></qticomment>\n");
            final Vector<Vector> s2 = DataBaseLayer.getQuestionBankDetails(qb_id);
            final Vector<String[]> vObject2 = s2.elementAt(0);
            StAsmtAdmin.log.debug("************vObject2.size()********************" + vObject2.size());
            for (int i = 0; i < vObject2.size(); ++i) {
                final String[] idetails2 = vObject2.elementAt(i);
                StAsmtAdmin.log.debug("***********idetails2******************" + idetails2[0]);
                out.write(idetails2[0]);
            }
            out.write("</objectbank>\n");
            out.write("</questestinterop>\n");
            out.close();
            String strFileName = "";
            String strFileId = attachmentname;
            if (strFileId != null) {
                strFileId = strFileId.replace('\\', File.separatorChar);
                strFileId = (strFileName = strFileId.replace('/', File.separatorChar));
                final File f1 = new File(strFileId);
                in = new BufferedInputStream(new FileInputStream(f1));
            }
        }
        catch (IOException e) {
            StAsmtAdmin.log.fatal("The Error Message");
            StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        final EncryptedFileDownloaderPojo fd = new EncryptedFileDownloaderPojo(in, "", "ObjectBank.ob");
        return EncryptedFileDownloaderPojo.returnFileFormat();
    }
    
    public Vector ParseObjectBank(final String ob_path) {
        final DOMParser parser = new DOMParser();
        final Vector vMetadata = new Vector();
        final Vector vItemlength = new Vector();
        final Vector vItemdetails = new Vector();
        final Vector vItemtitle = new Vector();
        final Vector vTopiclength = new Vector();
        final Vector vMetadata2 = new Vector();
        try {
            parser.parse(ob_path);
            final Document object_doc = parser.getDocument();
            final NodeList ob_item = object_doc.getElementsByTagName("item");
            final NodeList ob_qtimetadatafield = object_doc.getElementsByTagName("qtimetadatafield");
            final NodeList ob_flow = object_doc.getElementsByTagName("flow");
            final NodeList ob_response_str = object_doc.getElementsByTagName("response_str");
            final NodeList nl = object_doc.getElementsByTagName("qtimetadatafield");
            final NodeList nlLabel = object_doc.getElementsByTagName("fieldlabel");
            final NodeList nlEntery = object_doc.getElementsByTagName("fieldentry");
            Element item_ident = null;
            String ident_value = null;
            final int count = nl.getLength();
            final int count2 = nlLabel.getLength();
            final int count3 = nlEntery.getLength();
            vItemlength.addElement(ob_item.getLength());
            final Vector vMetadata3 = new Vector();
            for (int i1 = 0; i1 < ob_item.getLength(); ++i1) {
                final int count4 = 0;
                final String[] strItemtitle = { null };
                final String[] strItem = { null };
                item_ident = (Element)ob_item.item(i1);
                ident_value = item_ident.getAttribute("ident");
                final String tt = item_ident.getNodeName();
                final String ititle = item_ident.getAttribute("title");
                strItemtitle[0] = ititle;
                vItemtitle.addElement(strItemtitle);
                final Document doc111 = (Document)new DocumentImpl();
                final Element root = doc111.createElement("idetails");
                doc111.appendChild(root);
                final Node nd1 = doc111.importNode(ob_item.item(i1), true);
                root.appendChild(nd1);
                final OutputFormat format1 = new OutputFormat(doc111);
                final StringWriter stringOut1 = new StringWriter();
                final XMLSerializer serial1 = new XMLSerializer((Writer)stringOut1, format1);
                serial1.asDOMSerializer();
                serial1.serialize(doc111.getDocumentElement());
                final String xml11 = stringOut1.toString();
                final int kk1 = xml11.length();
                final String str1 = xml11.substring(49, kk1 - 11);
                strItem[0] = str1;
                vItemdetails.addElement(strItem);
                final ArrayList a = new ArrayList();
                for (int j = 0; j < count; ++j) {
                    final Node PNode = nlLabel.item(j).getParentNode().getParentNode().getParentNode().getParentNode();
                    final String Pnodename = PNode.getNodeName();
                    final Element Epnode = (Element)PNode;
                    final String attribute2 = Epnode.getAttribute("ident");
                    if (Pnodename.equals("item") && attribute2.equals(ident_value)) {
                        final String flebel = nlLabel.item(j).getFirstChild().getNodeValue();
                        final String fentry = nlEntery.item(j).getFirstChild().getNodeValue();
                        a.add(flebel);
                        a.add(fentry);
                    }
                }
                vMetadata3.addElement(a);
            }
            vMetadata.addElement(vItemlength);
            vMetadata.addElement(vItemdetails);
            vMetadata.addElement(vItemtitle);
            vMetadata.addElement(vMetadata3);
        }
        catch (SAXException e) {
            StAsmtAdmin.log.fatal("SAXException:");
            StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        catch (DOMException e2) {
            StAsmtAdmin.log.fatal("DOMException:");
            StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e2);
        }
        catch (IOException e3) {
            StAsmtAdmin.log.fatal("IOException:");
            StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e3);
        }
        return vMetadata;
    }
    
    public Vector ParseOB(final InputStream isOB) {
        final Vector vMetadata = new Vector();
        final Vector vItemlength = new Vector();
        final Vector vItemdetails = new Vector();
        final Vector vItemtitle = new Vector();
        final Vector vTopiclength = new Vector();
        final Vector vMetadata2 = new Vector();
        try {
            final InputStreamReader input_reader = new InputStreamReader(isOB, "UTF-8");
            final InputSource input_source = new InputSource(input_reader);
            input_source.setEncoding("UTF-8");
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document object_doc = db.parse(input_source);
            final NodeList ob_item = object_doc.getElementsByTagName("item");
            final NodeList ob_qtimetadatafield = object_doc.getElementsByTagName("qtimetadatafield");
            final NodeList ob_flow = object_doc.getElementsByTagName("flow");
            final NodeList ob_response_str = object_doc.getElementsByTagName("response_str");
            final NodeList nl = object_doc.getElementsByTagName("qtimetadatafield");
            final NodeList nlLabel = object_doc.getElementsByTagName("fieldlabel");
            final NodeList nlEntery = object_doc.getElementsByTagName("fieldentry");
            Element item_ident = null;
            String ident_value = null;
            final int count = nl.getLength();
            final int count2 = nlLabel.getLength();
            final int count3 = nlEntery.getLength();
            vItemlength.addElement(ob_item.getLength());
            final Vector vMetadata3 = new Vector();
            for (int i1 = 0; i1 < ob_item.getLength(); ++i1) {
                final int count4 = 0;
                final String[] strItemtitle = { null };
                final String[] strItem = { null };
                item_ident = (Element)ob_item.item(i1);
                ident_value = item_ident.getAttribute("ident");
                final String tt = item_ident.getNodeName();
                final String ititle = item_ident.getAttribute("title");
                strItemtitle[0] = ititle;
                vItemtitle.addElement(strItemtitle);
                final Document doc111 = (Document)new DocumentImpl();
                final Element root = doc111.createElement("idetails");
                doc111.appendChild(root);
                final Node nd1 = doc111.importNode(ob_item.item(i1), true);
                root.appendChild(nd1);
                final OutputFormat format1 = new OutputFormat(doc111);
                final StringWriter stringOut1 = new StringWriter();
                final XMLSerializer serial1 = new XMLSerializer((Writer)stringOut1, format1);
                serial1.asDOMSerializer();
                serial1.serialize(doc111.getDocumentElement());
                final String xml11 = stringOut1.toString();
                final int kk1 = xml11.length();
                final String str1 = xml11.substring(49, kk1 - 11);
                strItem[0] = str1;
                vItemdetails.addElement(strItem);
                final ArrayList a = new ArrayList();
                for (int j = 0; j < count; ++j) {
                    final Node PNode = nlLabel.item(j).getParentNode().getParentNode().getParentNode().getParentNode();
                    final String Pnodename = PNode.getNodeName();
                    final Element Epnode = (Element)PNode;
                    final String attribute2 = Epnode.getAttribute("ident");
                    if (Pnodename.equals("item") && attribute2.equals(ident_value)) {
                        final String flebel = nlLabel.item(j).getFirstChild().getNodeValue();
                        final String fentry = nlEntery.item(j).getFirstChild().getNodeValue();
                        a.add(flebel);
                        a.add(fentry);
                    }
                }
                vMetadata3.addElement(a);
            }
            vMetadata.addElement(vItemlength);
            vMetadata.addElement(vItemdetails);
            vMetadata.addElement(vItemtitle);
            vMetadata.addElement(vMetadata3);
        }
        catch (SAXException e) {
            StAsmtAdmin.log.fatal("SAXException:");
            StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        catch (DOMException e2) {
            StAsmtAdmin.log.fatal("DOMException:");
            StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e2);
        }
        catch (IOException e3) {
            StAsmtAdmin.log.fatal("IOException:");
            StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e3);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            StAsmtAdmin.log.debug("Exception==" + ex);
        }
        return vMetadata;
    }
    
    public String BulkUserRegistration(final String assessmentid, final FileTransfer file) {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String administrator_id = (String)mysession.getAttribute("user_id");
        String dirName = "";
        final String strLocation = dirName = Host.getAdminPath();
        File ufile = null;
        System.out.println("dir name is==:" + dirName);
        if (dirName == null) {
            html = "Please supply uploadDir parameter";
        }
        ufile = new File(dirName);
        if (!ufile.exists()) {
            ufile.mkdir();
        }
        final FileUploaderPojo fu = new FileUploaderPojo(file);
        final String MimeType = FileUploaderPojo.getMimeType();
        System.out.println("MimeType======:" + MimeType);
        final InputStream is = FileUploaderPojo.getInputStream();
        final String filename = FileUploaderPojo.getFilename();
        dirName = dirName + File.separator + filename;
        final String content = "";
        try {
            System.out.println("dirName===" + dirName);
            final FileOutputStream out = new FileOutputStream(dirName);
            int length = 0;
            final byte[] b = new byte[1024];
            try {
                while (is != null && (length = is.read(b)) != -1) {
                    out.write(b, 0, length);
                }
            }
            catch (IOException ex) {}
            final PrintStream p = new PrintStream(out);
            p.println(content);
            p.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            final Workbook w = Workbook.getWorkbook(new File(strLocation + File.separator + filename));
            for (int k = 0; k < 1; ++k) {
                final Sheet sheet = w.getSheet(k);
                final double numberb2 = 0.0;
                final int cols = sheet.getColumns();
                final int rows = sheet.getRows();
                Cell a1 = null;
                Cell a2 = null;
                Cell a3 = null;
                Cell a4 = null;
                Cell a5 = null;
                Cell a6 = null;
                String userId = "";
                String assessmentcode = "";
                String availableTime = "";
                String availableDate = "";
                String validtill = "";
                String accesstime = "";
                String df = "";
                String at = "";
                for (int i = 1; i < rows; ++i) {
                    a1 = sheet.getCell(0, i);
                    a2 = sheet.getCell(1, i);
                    a3 = sheet.getCell(2, i);
                    a4 = sheet.getCell(3, i);
                    a5 = sheet.getCell(4, i);
                    a6 = sheet.getCell(5, i);
                    userId = a1.getContents();
                    assessmentcode = a2.getContents();
                    availableDate = a3.getContents();
                    availableTime = a4.getContents();
                    validtill = a5.getContents();
                    accesstime = a6.getContents();
                    availableTime = ((availableTime == null) ? "" : availableTime);
                    availableDate = ((availableDate == null) ? "" : availableDate);
                    validtill = ((validtill == null) ? "" : validtill);
                    if (!availableDate.equals("")) {
                        if (availableDate.length() == 10) {
                            System.out.println(availableDate.charAt(2));
                            if (availableDate.charAt(2) != '/' && availableDate.charAt(5) != '/') {
                                availableDate = "";
                            }
                        }
                        else {
                            availableDate = "";
                        }
                    }
                    if (!validtill.equals("")) {
                        if (validtill.length() == 10) {
                            System.out.println(validtill.charAt(2));
                            if (validtill.charAt(2) != '/' && validtill.charAt(5) != '/') {
                                validtill = "";
                            }
                        }
                        else {
                            validtill = "";
                        }
                    }
                    if (!availableTime.equals("")) {
                        if (availableTime.length() == 8) {
                            if (availableTime.charAt(2) != ':' || availableTime.charAt(5) != ':') {
                                availableTime = "";
                            }
                        }
                        else {
                            availableTime = "";
                        }
                    }
                    if (!availableDate.equals("")) {
                        final String str11 = availableDate.substring(0, availableDate.indexOf(47));
                        final String str2 = availableDate.substring(availableDate.indexOf(47) + 1);
                        final String str3 = str2.substring(0, str2.indexOf(47));
                        final String str4 = str2.substring(str2.indexOf(47) + 1);
                        at = str4 + "-" + str3 + "-" + str11;
                    }
                    else {
                        at = "";
                    }
                    final String assessmentId = DataBaseLayer.getAssessmentId(assessmentcode);
                    if (!validtill.equals("")) {
                        final String str5 = validtill.substring(0, validtill.indexOf(47));
                        final String str6 = validtill.substring(validtill.indexOf(47) + 1);
                        final String str7 = str6.substring(0, str6.indexOf(47));
                        final String str8 = str6.substring(str6.indexOf(47) + 1);
                        df = str8 + "-" + str7 + "-" + str5;
                    }
                    else {
                        df = "";
                    }
                    System.out.println("=======test4======");
                    if (!DataBaseLayer.isUserRegistered(userId, assessmentcode)) {
                        System.out.println("userId ========1111111" + userId + "  assessmentid =========" + assessmentId);
                        System.out.println("at ========1111111" + at + "  availableTime =========" + availableTime + "\t\tdf==" + df);
                        if (!DataBaseLayer.isuserAssessmentExists(userId, assessmentcode) && availableTime != "" && at != "" && df != "") {
                            DataBaseLayer.insertAssessmentuserRegistration(userId, assessmentId, administrator_id, availableTime, at, df, accesstime);
                        }
                        else {
                            DataBaseLayer.insertTempAssessmentuserRegistration(userId, assessmentcode, administrator_id, a4.getContents(), a3.getContents(), a5.getContents(), accesstime);
                        }
                    }
                    else {
                        System.out.println("userId ========444444" + userId + "  course_id =========" + assessmentcode);
                        if (availableTime != "" && at != "" && df != "") {
                            DataBaseLayer.modifyAssessmentUserregistration(userId, assessmentId, administrator_id, availableTime, at, df, accesstime);
                        }
                        else {
                            DataBaseLayer.insertTempAssessmentuserRegistration(userId, assessmentcode, administrator_id, a4.getContents(), a3.getContents(), a5.getContents(), accesstime);
                        }
                    }
                }
                w.close();
            }
            System.out.println("=======test5======");
        }
        catch (Exception exp) {
            System.err.println("=========error=======" + exp.toString());
        }
        System.out.println("======after=====");
        return html;
    }
    
    public String BulkGroupRegistration(final String assessmentid, final FileTransfer file) {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String administrator_id = (String)mysession.getAttribute("user_id");
        String dirName = "";
        final String strLocation = dirName = Host.getAdminPath();
        File ufile = null;
        System.out.println("dir name is==:" + dirName);
        if (dirName == null) {
            html = "Please supply uploadDir parameter";
        }
        ufile = new File(dirName);
        if (!ufile.exists()) {
            ufile.mkdir();
        }
        final FileUploaderPojo fu = new FileUploaderPojo(file);
        final String MimeType = FileUploaderPojo.getMimeType();
        System.out.println("MimeType======:" + MimeType);
        final InputStream is = FileUploaderPojo.getInputStream();
        final String filename = FileUploaderPojo.getFilename();
        dirName = dirName + File.separator + filename;
        final String content = "";
        try {
            System.out.println("dirName===" + dirName);
            final FileOutputStream out = new FileOutputStream(dirName);
            int length = 0;
            final byte[] b = new byte[1024];
            try {
                while (is != null && (length = is.read(b)) != -1) {
                    out.write(b, 0, length);
                }
            }
            catch (IOException ex) {}
            final PrintStream p = new PrintStream(out);
            p.println(content);
            p.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("======before=====");
        try {
            final Workbook w = Workbook.getWorkbook(new File(strLocation + File.separator + filename));
            for (int k = 0; k < 1; ++k) {
                final Sheet sheet = w.getSheet(k);
                final double numberb2 = 0.0;
                final int cols = sheet.getColumns();
                final int rows = sheet.getRows();
                Cell a1 = null;
                Cell a2 = null;
                Cell a3 = null;
                Cell a4 = null;
                Cell a5 = null;
                Cell a6 = null;
                String groupId = "";
                String assessmentcode = "";
                String availableTime = "";
                String availableDate = "";
                String validtill = "";
                String accesstime = "";
                String df = "";
                String at = "";
                for (int i = 1; i < rows; ++i) {
                    a1 = sheet.getCell(0, i);
                    a2 = sheet.getCell(1, i);
                    a3 = sheet.getCell(2, i);
                    a4 = sheet.getCell(3, i);
                    a5 = sheet.getCell(4, i);
                    a6 = sheet.getCell(5, i);
                    groupId = a1.getContents();
                    assessmentcode = a2.getContents();
                    availableDate = a3.getContents();
                    availableTime = a4.getContents();
                    validtill = a5.getContents();
                    accesstime = a6.getContents();
                    availableTime = ((availableTime == null) ? "" : availableTime);
                    availableDate = ((availableDate == null) ? "" : availableDate);
                    validtill = ((validtill == null) ? "" : validtill);
                    if (!availableDate.equals("")) {
                        if (availableDate.length() == 10) {
                            System.out.println(availableDate.charAt(2));
                            if (availableDate.charAt(2) != '/' && availableDate.charAt(5) != '/') {
                                availableDate = "";
                            }
                        }
                        else {
                            availableDate = "";
                        }
                    }
                    if (!validtill.equals("")) {
                        if (validtill.length() == 10) {
                            System.out.println(validtill.charAt(2));
                            if (validtill.charAt(2) != '/' && validtill.charAt(5) != '/') {
                                validtill = "";
                            }
                        }
                        else {
                            validtill = "";
                        }
                    }
                    if (!availableTime.equals("")) {
                        if (availableTime.length() == 8) {
                            if (availableTime.charAt(2) != ':' || availableTime.charAt(5) != ':') {
                                availableTime = "";
                            }
                        }
                        else {
                            availableTime = "";
                        }
                    }
                    if (!availableDate.equals("")) {
                        final String str11 = availableDate.substring(0, availableDate.indexOf(47));
                        final String str2 = availableDate.substring(availableDate.indexOf(47) + 1);
                        final String str3 = str2.substring(0, str2.indexOf(47));
                        final String str4 = str2.substring(str2.indexOf(47) + 1);
                        at = str4 + "-" + str3 + "-" + str11;
                    }
                    else {
                        at = "";
                    }
                    final String assessmentId = DataBaseLayer.getAssessmentId(assessmentcode);
                    if (!validtill.equals("")) {
                        final String str5 = validtill.substring(0, validtill.indexOf(47));
                        final String str6 = validtill.substring(validtill.indexOf(47) + 1);
                        final String str7 = str6.substring(0, str6.indexOf(47));
                        final String str8 = str6.substring(str6.indexOf(47) + 1);
                        df = str8 + "-" + str7 + "-" + str5;
                    }
                    else {
                        df = "";
                    }
                    if (!DataBaseLayer.isGroupRegistered(groupId, assessmentcode)) {
                        System.out.println("isGroupAssessmentExists == " + DataBaseLayer.isGroupAssessmentExists(groupId, assessmentcode));
                        if (!DataBaseLayer.isGroupAssessmentExists(groupId, assessmentcode) && availableTime != "" && at != "" && df != "") {
                            DataBaseLayer.insertAssessmentGroupRegistration(groupId, assessmentId, administrator_id, availableTime, at, df, accesstime);
                        }
                        else {
                            DataBaseLayer.insertTempAssessmentGroupRegistration(groupId, assessmentcode, administrator_id, a4.getContents(), a3.getContents(), a5.getContents(), accesstime);
                        }
                    }
                    else {
                        System.out.println("groupId ========444444" + groupId + "  course_id =========" + assessmentcode);
                        if (availableTime != "" && at != "" && df != "") {
                            DataBaseLayer.modifyAssessmentGroupRegistration(groupId, assessmentId, administrator_id, availableTime, at, df, accesstime);
                        }
                        else {
                            DataBaseLayer.insertTempAssessmentGroupRegistration(groupId, assessmentcode, administrator_id, a4.getContents(), a3.getContents(), a5.getContents(), accesstime);
                        }
                    }
                }
                w.close();
            }
        }
        catch (Exception exp) {
            System.err.println("=========error=======" + exp.toString());
        }
        System.out.println("======after=====");
        return html;
    }
    
    public String createTableTopFiveToppers(final String assessment_id) {
        final String s = "";
        DataBaseLayer.generateTopFiveUserResult(assessment_id);
        return s;
    }
    
    public String createTempAsmtResultUserModel(final String assessment_id) {
        final String s = "";
        DataBaseLayer.getusermodelforAssessment(assessment_id);
        return s;
    }
    
    public String createTempAsmtResultScormModel(final String assessment_id) {
        final String s = "";
        DataBaseLayer.getUserScoInfoResult(assessment_id);
        return s;
    }
    
    public String updateScormTable(final String unit_id, final String assessment_id, final String user_id) {
        final String s = "";
        DataBaseLayer.updateScormTable(unit_id, assessment_id, user_id);
        return s;
    }
    
    public String updateScormTableAllUser(final String unit_id, final String assessment_id) {
        final String s = "";
        DataBaseLayer.updateScormTableAllUser(unit_id, assessment_id);
        return s;
    }
    
    public String updateScormTableAllAssess(final String unit_id, final String user_id) {
        final String s = "";
        DataBaseLayer.updateScormTableAllAssess(unit_id, user_id);
        return s;
    }
    
    public String updateScormTableAll(final String unit_id) {
        final String s = "";
        DataBaseLayer.updateScormTableAll(unit_id);
        return s;
    }
    
    public FileTransfer downloadEssayTypeAnswer(final String unit_id, final String assessment_id) {
        final Vector<String[]> vUnitList = DataBaseLayer.getReport(assessment_id, unit_id);
        String source = "";
        if (vUnitList != null) {
            for (int i = 0; i < vUnitList.size(); ++i) {
                final String[] str = vUnitList.elementAt(i);
                final String assessment_date = str[0];
                final String start_time = str[1];
                final String s_name = str[2];
                final String ques = str[3];
                final String ans = str[4];
                source = source + "\n[[Student Name]]" + s_name;
                source = source + "\n[[Date]]" + assessment_date;
                source = source + "\n[[Time]]" + start_time;
                source = source + "\n[[Question]]" + ques;
                source = source + "\n[[Answer]]" + ans;
                source += "\n              ";
                source += "\n              ";
            }
        }
        final InputStream sis = new StringBufferInputStream(source);
        final FileDownloaderPojo fd = new FileDownloaderPojo(sis, "", "record.txt");
        return FileDownloaderPojo.returnFileFormat();
    }
    
    public String getLoginId() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        System.out.println("======user_id=====" + user_id);
        return user_id;
    }
    
    public String createDropDownQBank() {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        System.out.println("======user_id=====" + user_id);
        String html = "";
        final Vector<Vector<String>> vqb = DataBaseLayer.getQbDetails(user_id);
        if (vqb.size() == 0) {
            html = "<option value='0'>[Choose One]</option>";
        }
        else {
            html += "<option value='0'>[Choose One]</option>";
            for (int i = 0; i < vqb.size(); ++i) {
                final Vector<String> vu = vqb.elementAt(i);
                final String id = vu.elementAt(0);
                final String qbname = vu.elementAt(1);
                html = html + "<option value='" + id + "'>" + qbname + "</option>";
            }
        }
        System.out.println("html ===" + html);
        return html;
    }
    
    public String ChangeGridSelectorQuery(final String interface_id, final String content_id, final String part_id, final String name, final String value) {
        String html = "";
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        System.out.println("================interface_id==========" + interface_id);
        System.out.println("================part_id==========" + part_id);
        System.out.println("================name==========" + name);
        System.out.println("================value==========" + value);
        String sql_query = DataBaseLayer.getSelectorQuery(interface_id, content_id, part_id);
        final String stringtoreplace = "%" + name + "%";
        final String stringreplacewith = "'" + value + "'";
        sql_query = sql_query.replace(stringtoreplace, stringreplacewith);
        System.out.println("==============sql_query in POJO=========" + sql_query);
        DataBaseLayer.updateselectoractiveQuery(interface_id, content_id, part_id, sql_query);
        html = DataBaseLayer.getSelectorActiveQuery(interface_id, content_id, part_id);
        return html;
    }
    
    public String createDropDownAssessmentDefinition(final String sql_query) {
        final WebContext wctx1 = WebContextFactory.get();
        final HttpSession mysession = wctx1.getSession();
        final String user_id = (String)mysession.getAttribute("user_id");
        System.out.println("======user_id=====" + user_id);
        String html = "";
        final Vector<Vector<String>> vqb = DataBaseLayer.getData(sql_query);
        if (vqb.size() == 0) {
            html = "<option value='0'>[Choose One]</option>";
        }
        else {
            html += "<option value='0'>[Choose One]</option>";
            for (int i = 0; i < vqb.size(); ++i) {
                final Vector<String> vu = vqb.elementAt(i);
                final String id = vu.elementAt(0);
                final String qbname = vu.elementAt(1);
                html = html + "<option value='" + id + "'>" + qbname + "</option>";
            }
        }
        System.out.println("html ===" + html);
        return html;
    }
    
    public void DownloadOBfromMainServer(final String qb_id) {
        try {
            final WebContext wctx1 = WebContextFactory.get();
            final HttpSession mysession = wctx1.getSession();
            final String user_id = (String)mysession.getAttribute("user_id");
            String strurl = null;
            StAsmtAdmin.log.debug("*******************strurl***************" + strurl);
            final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
            final String strkey = "MainServerOBDownloadURL";
            strurl = rb.getString(strkey);
            strurl = strurl + "?qb_id=" + qb_id;
            strurl = strurl.trim();
            StAsmtAdmin.log.debug("************strurl******************" + strurl);
            if (strurl != null) {
                final HttpClient client2 = new HttpClient();
                final HttpMethod method2 = (HttpMethod)new GetMethod(strurl);
                int statusCode = -1;
                final int attempt = 0;
                while (statusCode == -1) {
                    try {
                        statusCode = client2.executeMethod(method2);
                        StAsmtAdmin.log.debug("statusCode" + statusCode);
                    }
                    catch (Exception e) {
                        StAsmtAdmin.log.fatal("The Error Message");
                        StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
                    }
                }
                final InputStream res_str = method2.getResponseBodyAsStream();
                String attachmentname = Host.getAdminPath();
                attachmentname = attachmentname + File.separatorChar + "QB.ob";
                attachmentname = attachmentname.trim();
                StAsmtAdmin.log.debug("************attachmentname*********************" + attachmentname);
                final OutputStream oust = new FileOutputStream(attachmentname);
                try {
                    DataBaseLayer.deleteQuestionBankItems(qb_id);
                    DataBaseLayer.downloadQuestionBank(qb_id, user_id);
                    final Vector<Vector> vObject = this.ParseOB(res_str);
                    final Vector<Integer> vObject2 = vObject.elementAt(0);
                    final Vector<String[]> vObject3 = vObject.elementAt(1);
                    final Vector<String[]> vObject4 = vObject.elementAt(2);
                    final Integer itemlength = vObject2.elementAt(0);
                    final Vector<ArrayList> vObject5 = vObject.elementAt(3);
                    for (int i = 0; i < itemlength; ++i) {
                        String[] strObj2 = vObject3.elementAt(i);
                        strObj2 = vObject4.elementAt(i);
                        final int count3 = 0;
                        DataBaseLayer.addQuestionBankItems(qb_id, strObj2[0], strObj2[0]);
                        final ArrayList a1 = vObject5.elementAt(i);
                        for (int i2 = 0; i2 < a1.size(); i2 += 2) {
                            final String flabel = a1.get(i2).toString();
                            final String fentry = a1.get(i2 + 1).toString();
                            DataBaseLayer.addQuestionMetadata(qb_id, flabel, fentry);
                        }
                    }
                }
                catch (Exception e2) {
                    StAsmtAdmin.log.fatal("Exception:---");
                    StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e2);
                }
            }
        }
        catch (Exception e3) {
            StAsmtAdmin.log.fatal("The Error Message in DownloadOBfromMainServer");
            StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e3);
        }
    }
    
    public String DownloadGeneratedAsmtfromMainServer(final String asmt_id) {
        String message = "Assessment Successfully Downloaded";
        try {
            final WebContext wctx1 = WebContextFactory.get();
            final HttpSession mysession = wctx1.getSession();
            final String user_id = (String)mysession.getAttribute("user_id");
            String strurl = null;
            StAsmtAdmin.log.debug("*******************strurl***************" + strurl);
            final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
            final String strkey = "MainServerAsmtDownloadURL";
            strurl = rb.getString(strkey);
            strurl = strurl + "?asmt_id=" + asmt_id;
            strurl = strurl.trim();
            StAsmtAdmin.log.debug("************strurl******************" + strurl);
            if (strurl != null) {
                final HttpClient client2 = new HttpClient();
                final HttpMethod method2 = (HttpMethod)new GetMethod(strurl);
                int statusCode = -1;
                final int attempt = 0;
                while (statusCode == -1) {
                    try {
                        statusCode = client2.executeMethod(method2);
                        StAsmtAdmin.log.debug("statusCode" + statusCode);
                    }
                    catch (Exception e) {
                        StAsmtAdmin.log.fatal("The Error Message");
                        StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
                    }
                }
                final InputStream is = method2.getResponseBodyAsStream();
                try {
                    final String strGenerationType = DataBaseLayer.getAsmtGenerationType(asmt_id);
                    StAsmtAdmin.log.debug("************strGenerationType**********************" + strGenerationType);
                    if (strGenerationType.equals("Pregenerate")) {
                        String strDownloadedAsmt = "";
                        if (is != null) {
                            strDownloadedAsmt = this.convertStreamToString(is);
                        }
                        strDownloadedAsmt.trim();
                        StAsmtAdmin.log.debug("************strDownloadedAsmt*************1*********" + strDownloadedAsmt + "====");
                        if (!strDownloadedAsmt.equals("") && strDownloadedAsmt != null && !strDownloadedAsmt.equals("null") && !strDownloadedAsmt.equals("NotAvailable\n")) {
                            DataBaseLayer.downloadGeneratedAsmt(asmt_id, strDownloadedAsmt, user_id);
                            StAsmtAdmin.log.debug("********Assessment available**************");
                        }
                        else {
                            message = "Assessment is not available.";
                        }
                    }
                    else {
                        message = "This is not a pregenerated assessment.So, it is not possible to download.";
                    }
                }
                catch (Exception e2) {
                    StAsmtAdmin.log.fatal("Exception:---");
                    StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e2);
                }
                finally {
                    if (is != null) {
                        is.close();
                    }
                    method2.releaseConnection();
                }
            }
        }
        catch (Exception e3) {
            StAsmtAdmin.log.fatal("The Error Message in DownloadGeneratedAsmtfromMainServer");
            StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e3);
        }
        return message;
    }
    
    public void RefreshObjectBankList() {
        try {
            final WebContext wctx1 = WebContextFactory.get();
            final HttpSession mysession = wctx1.getSession();
            final String user_id = (String)mysession.getAttribute("user_id");
            String attachmentname = Host.getAdminPath();
            attachmentname = attachmentname + "/" + "thefile.csv";
            String strurl = null;
            StAsmtAdmin.log.debug("*******************strurl***************" + strurl);
            final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
            final String strkey = "MainServerOBListURL";
            strurl = rb.getString(strkey);
            strurl = strurl.trim();
            StAsmtAdmin.log.debug("************strurl******************" + strurl);
            if (strurl != null) {
                final HttpClient client2 = new HttpClient();
                final HttpMethod method2 = (HttpMethod)new GetMethod(strurl);
                int statusCode = -1;
                final int attempt = 0;
                while (statusCode == -1) {
                    try {
                        statusCode = client2.executeMethod(method2);
                        StAsmtAdmin.log.debug("statusCode" + statusCode);
                    }
                    catch (Exception e) {
                        StAsmtAdmin.log.fatal("The Error Message");
                        StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
                    }
                }
                final InputStream res_str = method2.getResponseBodyAsStream();
                StAsmtAdmin.log.debug("************attachmentname*********************" + attachmentname);
                final OutputStream uuu = new FileOutputStream(attachmentname);
                int len = 0;
                final byte[] buffer = new byte[1024];
                try {
                    while (res_str != null && (len = res_str.read(buffer)) != -1) {
                        uuu.write(buffer, 0, len);
                    }
                }
                finally {
                    if (res_str != null) {
                        res_str.close();
                    }
                    method2.releaseConnection();
                }
                final Vector<Vector> vQBList = this.readFile(attachmentname);
                final Vector vQBID = vQBList.elementAt(0);
                final Vector vQBName = vQBList.elementAt(1);
                StAsmtAdmin.log.debug("************vQBID*********************" + vQBID);
                StAsmtAdmin.log.debug("************vQBName*********************" + vQBName);
                DataBaseLayer.refreshQuestionBank(vQBID, vQBName, user_id);
            }
        }
        catch (Exception e2) {
            StAsmtAdmin.log.fatal("IOException in RefreshObjectBankList");
            StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e2);
        }
    }
    
    public void RefreshAssessmentList() {
        try {
            final WebContext wctx1 = WebContextFactory.get();
            final HttpSession mysession = wctx1.getSession();
            final String user_id = (String)mysession.getAttribute("user_id");
            String attachmentname = Host.getAdminPath();
            attachmentname = attachmentname + "/" + "thefile.csv";
            String strurl = null;
            StAsmtAdmin.log.debug("*******************strurl***************" + strurl);
            final ResourceBundle rb = ResourceBundle.getBundle("portal1", Locale.getDefault());
            final String strkey = "MainServerAsmtListURL";
            strurl = rb.getString(strkey);
            strurl = strurl.trim();
            StAsmtAdmin.log.debug("************strurl******************" + strurl);
            if (strurl != null) {
                final HttpClient client2 = new HttpClient();
                final HttpMethod method2 = (HttpMethod)new GetMethod(strurl);
                int statusCode = -1;
                final int attempt = 0;
                while (statusCode == -1) {
                    try {
                        statusCode = client2.executeMethod(method2);
                        StAsmtAdmin.log.debug("statusCode" + statusCode);
                    }
                    catch (Exception e) {
                        StAsmtAdmin.log.fatal("The Error Message");
                        StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
                    }
                }
                final InputStream res_str = method2.getResponseBodyAsStream();
                StAsmtAdmin.log.debug("************attachmentname*********************" + attachmentname);
                final OutputStream uuu = new FileOutputStream(attachmentname);
                int len = 0;
                final byte[] buffer = new byte[1024];
                try {
                    while (res_str != null && (len = res_str.read(buffer)) != -1) {
                        uuu.write(buffer, 0, len);
                    }
                }
                finally {
                    if (res_str != null) {
                        res_str.close();
                    }
                    method2.releaseConnection();
                }
                final Vector<Vector> vAsmtList = this.readAsmtDetails(attachmentname);
                final Vector vAsmtID = vAsmtList.elementAt(0);
                final Vector vAsmtCode = vAsmtList.elementAt(1);
                final Vector vAsmtName = vAsmtList.elementAt(2);
                final Vector vAsmtType = vAsmtList.elementAt(3);
                final Vector vAsmtDesc = vAsmtList.elementAt(4);
                final Vector vAsmtDuration = vAsmtList.elementAt(5);
                final Vector vAsmtPassMarks = vAsmtList.elementAt(6);
                final Vector vAsmtQuestionPerPage = vAsmtList.elementAt(7);
                final Vector vAsmtMaxTestTaken = vAsmtList.elementAt(8);
                final Vector vAsmtGenerationType = vAsmtList.elementAt(9);
                StAsmtAdmin.log.debug("************vAsmtID*********************" + vAsmtID);
                StAsmtAdmin.log.debug("************vAsmtCode*********************" + vAsmtCode);
                StAsmtAdmin.log.debug("************vAsmtName*********************" + vAsmtName);
                StAsmtAdmin.log.debug("************vAsmtType*********************" + vAsmtType);
                StAsmtAdmin.log.debug("************vAsmtDesc*********************" + vAsmtDesc);
                StAsmtAdmin.log.debug("************vAsmtDuration*********************" + vAsmtDuration);
                StAsmtAdmin.log.debug("************vAsmtPassMarks*********************" + vAsmtPassMarks);
                StAsmtAdmin.log.debug("************vAsmtQuestionPerPage*********************" + vAsmtQuestionPerPage);
                StAsmtAdmin.log.debug("************vAsmtMaxTestTaken*********************" + vAsmtMaxTestTaken);
                StAsmtAdmin.log.debug("************vAsmtGenerationType*********************" + vAsmtGenerationType);
                DataBaseLayer.refreshAssessmentList(vAsmtID, vAsmtCode, vAsmtName, vAsmtType, vAsmtDesc, vAsmtDuration, vAsmtPassMarks, vAsmtQuestionPerPage, vAsmtMaxTestTaken, vAsmtGenerationType, user_id);
            }
        }
        catch (Exception e2) {
            StAsmtAdmin.log.fatal("IOException in RefreshObjectBankList");
            StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e2);
        }
    }
    
    public void setResultProcessingPending(final String asmt_id) {
        try {
            DataBaseLayer.setResultProcessingStatus(asmt_id);
        }
        catch (Exception e) {
            StAsmtAdmin.log.fatal("The Error Message in setResultProcessingPending");
            StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
    }
    
    public String generateAssessment(final String strAssessmentID) {
        final String message = "Assessment Successfully generated";
        String strItems = "";
        try {
            final Vector vAssessmentDeffination = DataBaseLayer.getAssessmentDeffination(strAssessmentID);
            final Vector<Vector> vAsmtItems = this.getAllItems(vAssessmentDeffination);
            StAsmtAdmin.log.debug("**********vAsmtItems**************************" + vAsmtItems);
            String attachmentname = Host.getAdminPath();
            attachmentname = attachmentname + File.separatorChar + "QuestionPaper.asmt";
            final FileOutputStream fout = new FileOutputStream(attachmentname);
            final OutputStreamWriter out = new OutputStreamWriter(fout);
            final String StrAsmtIdent = "Asmt_1";
            final String strtag1 = "<?xml version='1.0' encoding='UTF-8'?>\n";
            final String strtag2 = "<questestinterop>\r\n";
            final String strtag3 = "<assessment title=\"Assessment\" ident=\"" + StrAsmtIdent + "\">\r\n";
            final String strtag4 = "<qticomment></qticomment>\n";
            strItems = strtag1 + strtag2 + strtag3 + strtag4;
            out.write("<?xml version='1.0' encoding='UTF-8'?>\n");
            out.write("<questestinterop>\r\n");
            out.write("<assessment title=\"Assessment\" ident=\"" + StrAsmtIdent + "\">\r\n");
            out.write("<qticomment></qticomment>\n");
            final Vector<String> vText = vAsmtItems.elementAt(2);
            StAsmtAdmin.log.debug("************vText.size()********************" + vText.size());
            for (int i = 0; i < vText.size(); ++i) {
                final String strAsmtItem = vText.elementAt(i);
                StAsmtAdmin.log.debug("***********strAsmtItem******************" + strAsmtItem);
                out.write(strAsmtItem);
                strItems = strItems + "\n" + strAsmtItem;
            }
            out.write("</assessment>\n");
            out.write("</questestinterop>\n");
            out.close();
            final String strtag5 = "</assessment>\n";
            final String strtag6 = "</questestinterop>\n";
            strItems = strItems + "\n" + strtag5 + strtag6;
            StAsmtAdmin.log.debug("***********strItems******************" + strItems);
            DataBaseLayer.generateAsmt(strAssessmentID, strItems);
        }
        catch (Exception e) {
            StAsmtAdmin.log.fatal("The Error Message in generateAssessment");
            StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        return message;
    }
    
    public Vector getItemsAsTopic(final String topicId, final String dlevel, final String No_questions_per_topic, final String q_type) {
        Vector vAllDetails = new Vector();
        try {
            final Vector vRandItemIdList1 = new Vector();
            Vector vRandItemIdList2 = new Vector();
            final Vector vItemIdList_dlevel = new Vector();
            final Vector vItemIdList = DataBaseLayer.getTestItemIDs(topicId, q_type);
            if (dlevel.equals("Any")) {
                vRandItemIdList2 = this.rand(vItemIdList);
            }
            else {
                DataBaseLayer.getItemId(vItemIdList, dlevel);
                vRandItemIdList2 = this.rand(vItemIdList_dlevel);
            }
            vItemIdList.removeAllElements();
            final int no_ques = Integer.parseInt(No_questions_per_topic);
            if (vRandItemIdList2.size() <= no_ques) {
                vAllDetails = DataBaseLayer.getTextAndType(vRandItemIdList2);
            }
            else {
                for (int m = 0; m < vRandItemIdList2.size(); ++m) {
                    vRandItemIdList1.addElement(vRandItemIdList2.elementAt(m));
                    vAllDetails = DataBaseLayer.getTextAndType(vRandItemIdList1);
                    if (vRandItemIdList1.size() == no_ques) {
                        break;
                    }
                }
            }
            StAsmtAdmin.log.debug("********vAllDetails********************" + vAllDetails);
        }
        catch (Exception lEx) {
            StAsmtAdmin.log.debug(lEx.toString() + " error in getItemsAsTopic method");
        }
        return vAllDetails;
    }
    
    public Vector getAllItems(final Vector<String[]> vAssessmentDeffination) {
        Vector AllQuestions = new Vector();
        final Vector AllTempQuestions = new Vector();
        try {
            for (int k = 0; k < vAssessmentDeffination.size(); ++k) {
                final String[] AssessmentInfo = vAssessmentDeffination.elementAt(k);
                final String topic_id = AssessmentInfo[0];
                final String no_of_questions = AssessmentInfo[1];
                final String degree_of_difficulty = AssessmentInfo[2];
                final String question_type = AssessmentInfo[3];
                final Vector TopicWiseItems = this.getItemsAsTopic(topic_id, degree_of_difficulty, no_of_questions, question_type);
                AllTempQuestions.addElement(TopicWiseItems);
            }
            AllQuestions = this.InAVector(AllTempQuestions);
            StAsmtAdmin.log.debug("************AllQuestions*******************" + AllQuestions);
        }
        catch (Exception lEx) {
            StAsmtAdmin.log.debug(lEx.toString() + " error to gets all items  in getAllItems method");
        }
        return AllQuestions;
    }
    
    public Vector InAVector(final Vector<Vector> vTempVector) {
        final Vector vActualVector = new Vector();
        try {
            for (int k = 0; k < vTempVector.size(); ++k) {
                final Vector v1 = vTempVector.elementAt(k);
                for (int m = 0; m < v1.size(); ++m) {
                    vActualVector.addElement(v1.elementAt(m));
                }
            }
        }
        catch (Exception lEx) {
            System.out.println(lEx.toString() + " error in InAVector function of AllStAloneTestItems");
        }
        return vActualVector;
    }
    
    public Vector rand(final Vector questionAll) {
        final int[] a = new int[questionAll.size()];
        for (int i = 0; i < a.length; ++i) {
            a[i] = i;
        }
        final Random rnd = new Random();
        int k = 0;
        for (int j = 0; j < a.length; ++j) {
            k = rnd.nextInt(a.length);
            final int t = a[k];
            a[k] = a[j];
            a[j] = t;
        }
        final Vector v = new Vector();
        for (int l = 0; l < a.length; ++l) {
            v.addElement(questionAll.elementAt(a[l]));
        }
        return v;
    }
    
    private static void setUp() throws Exception {
        StAsmtAdmin.key = KeyGenerator.getInstance(StAsmtAdmin.algorithm).generateKey();
        StAsmtAdmin.cipher = Cipher.getInstance(StAsmtAdmin.algorithm);
    }
    
    private static String decrypt(final byte[] encryptionBytes) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String recovered = null;
        try {
            StAsmtAdmin.cipher.init(2, StAsmtAdmin.key);
            final byte[] recoveredBytes = StAsmtAdmin.cipher.doFinal(encryptionBytes);
            recovered = new String(recoveredBytes);
        }
        catch (Exception e1) {
            StAsmtAdmin.log.fatal("Exception in decrypt method");
            StAsmtAdmin.log.dbe(DebugLevel.L1_FATAL, (Throwable)e1);
        }
        return recovered;
    }
    
    private static byte[] decrypt(final InputStream inFile) throws Exception {
        final String password = "super_secret";
        final FileOutputStream outFile = new FileOutputStream("/temp/obbbb.dcr");
        final PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        final SecretKey passwordKey = keyFactory.generateSecret(keySpec);
        final byte[] salt = new byte[8];
        inFile.read(salt);
        final int iterations = 100;
        final PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, iterations);
        final Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
        cipher.init(2, passwordKey, parameterSpec);
        final byte[] input = new byte[64];
        int bytesRead;
        while ((bytesRead = inFile.read(input)) != -1) {
            final byte[] output = cipher.update(input, 0, bytesRead);
            if (output != null) {
                outFile.write(output);
            }
        }
        final byte[] output = cipher.doFinal();
        if (output != null) {
            outFile.write(output);
        }
        inFile.close();
        outFile.flush();
        outFile.close();
        return output;
    }
    
    public static byte[] toBinArray(final String hexStr) {
        final byte[] bArray = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; ++i) {
            final byte firstNibble = Byte.parseByte(hexStr.substring(2 * i, 2 * i + 1), 16);
            final byte secondNibble = Byte.parseByte(hexStr.substring(2 * i + 1, 2 * i + 2), 16);
            final int finalByte = secondNibble | firstNibble << 4;
            bArray[i] = (byte)finalByte;
        }
        return bArray;
    }
    
    public static byte[] hexStringToByteArray(final String s) {
        final int len = s.length();
        final byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
    
    public Vector readFile(String attachmentname) {
        BufferedReader br = null;
        final Vector vQBList = new Vector();
        final Vector vQBID = new Vector();
        final Vector vQBName = new Vector();
        try {
            attachmentname = attachmentname.trim();
            StAsmtAdmin.log.debug("***readFile***********attachmentname*************" + attachmentname);
            br = new BufferedReader(new FileReader(attachmentname));
            String line = null;
            int i = 0;
            while ((line = br.readLine()) != null) {
                final String[] arr$;
                final String[] values = arr$ = line.split(",");
                for (final String strqb : arr$) {
                    StAsmtAdmin.log.debug("******************" + strqb);
                    if (i == 0) {
                        vQBID.addElement(strqb);
                        i = 1;
                    }
                    else if (i == 1) {
                        vQBName.addElement(strqb);
                        i = 0;
                    }
                    else {
                        StAsmtAdmin.log.debug("***no QB ID OR Name exist*****");
                    }
                }
                StAsmtAdmin.log.debug("*****************");
            }
            vQBList.addElement(vQBID);
            vQBList.addElement(vQBName);
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
        finally {
            try {
                if (br != null) {
                    br.close();
                }
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
            }
        }
        return vQBList;
    }
    
    public Vector readAsmtDetails(String attachmentname) {
        BufferedReader br = null;
        final Vector vAsmtList = new Vector();
        final Vector vAsmtID = new Vector();
        final Vector vAsmtCode = new Vector();
        final Vector vAsmtName = new Vector();
        final Vector vAsmtType = new Vector();
        final Vector vAsmtDesc = new Vector();
        final Vector vAsmtDuration = new Vector();
        final Vector vAsmtPassMarks = new Vector();
        final Vector vAsmtQuestionPerPage = new Vector();
        final Vector vAsmtMaxTestTaken = new Vector();
        final Vector vAsmtGenerationType = new Vector();
        try {
            attachmentname = attachmentname.trim();
            StAsmtAdmin.log.debug("***readAsmtDetails***********attachmentname*************" + attachmentname);
            br = new BufferedReader(new FileReader(attachmentname));
            String line = null;
            int i = 0;
            while ((line = br.readLine()) != null) {
                final String[] arr$;
                final String[] values = arr$ = line.split(",");
                for (final String strasmt : arr$) {
                    StAsmtAdmin.log.debug("******************" + strasmt);
                    if (i == 0) {
                        vAsmtID.addElement(strasmt);
                        i = 1;
                    }
                    else if (i == 1) {
                        vAsmtCode.addElement(strasmt);
                        i = 2;
                    }
                    else if (i == 2) {
                        vAsmtName.addElement(strasmt);
                        i = 3;
                    }
                    else if (i == 3) {
                        vAsmtType.addElement(strasmt);
                        i = 4;
                    }
                    else if (i == 4) {
                        vAsmtDesc.addElement(strasmt);
                        i = 5;
                    }
                    else if (i == 5) {
                        vAsmtDuration.addElement(strasmt);
                        i = 6;
                    }
                    else if (i == 6) {
                        vAsmtPassMarks.addElement(strasmt);
                        i = 7;
                    }
                    else if (i == 7) {
                        vAsmtQuestionPerPage.addElement(strasmt);
                        i = 8;
                    }
                    else if (i == 8) {
                        vAsmtMaxTestTaken.addElement(strasmt);
                        i = 9;
                    }
                    else if (i == 9) {
                        vAsmtGenerationType.addElement(strasmt);
                        i = 0;
                    }
                    else {
                        StAsmtAdmin.log.debug("***no Assessment ID Code OR Name exist*****");
                    }
                }
                StAsmtAdmin.log.debug("*****************");
            }
            vAsmtList.addElement(vAsmtID);
            vAsmtList.addElement(vAsmtCode);
            vAsmtList.addElement(vAsmtName);
            vAsmtList.addElement(vAsmtType);
            vAsmtList.addElement(vAsmtDesc);
            vAsmtList.addElement(vAsmtDuration);
            vAsmtList.addElement(vAsmtPassMarks);
            vAsmtList.addElement(vAsmtQuestionPerPage);
            vAsmtList.addElement(vAsmtMaxTestTaken);
            vAsmtList.addElement(vAsmtGenerationType);
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
        finally {
            try {
                if (br != null) {
                    br.close();
                }
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
            }
        }
        return vAsmtList;
    }
    
    public String convertStreamToString(final InputStream is) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        final StringBuilder sb = new StringBuilder();
        String strContent = "";
        String line = null;
        StAsmtAdmin.log.debug("********line***********" + line);
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                StAsmtAdmin.log.debug("*******sb****************" + (Object)sb);
            }
            strContent = sb.toString();
        }
        catch (IOException e) {
            StAsmtAdmin.log.debug("*******IOException****************");
            strContent = null;
            StAsmtAdmin.log.debug("*******strContent****************" + strContent);
            e.printStackTrace();
        }
        return strContent;
    }
    
    public InputStream StringToStream(final String text) {
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(text.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return is;
    }
    
    public static String updateMailServerStatus(final String configuration_id, final String status) {
        System.out.println("=========status======" + status);
        DataBaseLayer.updateMailServerStatus(configuration_id, status);
        return "";
    }
    
    public String[] getTotalStudentAsmtTitle(final String AssessTitleSelect) {
        String[] s = new String[2];
        s = DataBaseLayer.getTotalStudentAsmtTitle(AssessTitleSelect);
        return s;
    }
    
    static {
        StAsmtAdmin.algorithm = "DESede";
        StAsmtAdmin.key = null;
        StAsmtAdmin.cipher = null;
        log = new SimpleLogger((Class)StAsmtAdmin.class, true);
    }
}
