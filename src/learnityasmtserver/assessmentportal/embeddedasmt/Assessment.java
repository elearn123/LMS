package learnityasmtserver.assessmentportal.embeddedasmt;

import learnityInit.*;
import learnityasmtserver.assessmentportal.dbconnection.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import oracle.xml.parser.v2.*;
import org.grlea.log.*;
import java.net.*;
import org.w3c.dom.*;

public class Assessment extends HttpServlet
{
    public static final SimpleLogger log;
    private static final String SESSION_NAME = "SESSION_NAME";
    private static final String SESSION_ANS = "SESSION_ANS";
    private static final String SESSION_TEMPANS = "SESSION_TEMPANS";
    private static final String SESSION_SECTION_NAME = "SESSION_SECTION_NAME";
    private static final String SESSION_FILE_NAME = "SESSION_FILE_NAME";
    private static final String SESSION_ASSESSMENT_NAME = "SESSION_ASSESSMENT_NAME";
    private static final String SESSION_TIME = "SESSION_TIME";
    private static final String unit_id = "unit_id";
    private static final String identifierassessment = "identifierassessment";
    private static final String BROWSER = "BROWSER";
    Vector vAssessment;
    
    public Assessment() {
        this.vAssessment = null;
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final PrintWriter out = response.getWriter();
        final HttpSession mysession = request.getSession(true);
        String fileName = request.getParameter("s");
        String cid = request.getParameter("cid");
        System.out.println("cid===" + cid);
        String identifier = request.getParameter("identifier");
        System.out.println("identifier===" + identifier);
        final String ServerDocumentPath = Host.getServerDocumentPath();
        final Calendar calendar = new GregorianCalendar();
        final Date trialTime = new Date();
        calendar.setTime(trialTime);
        final String strTime2 = calendar.get(11) + ":" + calendar.get(12) + ":" + calendar.get(13);
        if (fileName != null) {
            mysession.removeAttribute("SESSION_ANS");
            mysession.removeAttribute("SESSION_NAME");
            mysession.removeAttribute("SESSION_SECTION_NAME");
            mysession.removeAttribute("SESSION_ASSESSMENT_NAME");
            mysession.removeAttribute("SESSION_FILE_NAME");
            mysession.removeAttribute("SESSION_TIME");
            mysession.removeAttribute("unit_id");
            mysession.setAttribute("SESSION_FILE_NAME", (Object)fileName);
            mysession.setAttribute("unit_id", (Object)cid);
        }
        if (identifier != null) {
            mysession.removeAttribute("identifierassessment");
            mysession.setAttribute("identifierassessment", (Object)identifier);
        }
        final Vector obj = (Vector)mysession.getAttribute("SESSION_NAME");
        final Vector vTempAns = (Vector)mysession.getAttribute("SESSION_TEMPANS");
        mysession.removeAttribute("SESSION_TEMPANS");
        identifier = (String)mysession.getAttribute("identifierassessment");
        System.out.println("identifier=111111111==" + identifier);
        final String BROWSER_TYPE = "ie";
        if (BROWSER_TYPE != null) {
            mysession.removeAttribute("BROWSER");
            mysession.setAttribute("BROWSER", (Object)BROWSER_TYPE);
        }
        Vector<Vector<Vector>> v = null;
        if (obj == null) {
            v = new Vector();
        }
        else {
            v = obj;
        }
        int iSec = 0;
        final String secId = request.getParameter("secID");
        final String course_ids = request.getParameter("course_id");
        if (secId != null) {
            iSec = Integer.parseInt(secId);
        }
        fileName = (String)mysession.getAttribute("SESSION_FILE_NAME");
        cid = (String)mysession.getAttribute("unit_id");
        final AssessmentInfo asmt = new AssessmentInfo(course_ids);
        fileName = ServerDocumentPath + File.separator + cid + File.separator + fileName;
        System.out.println("fileName==2=" + fileName);
        final XMLDocument doc = this.HTMLParser(fileName);
        if (secId == null) {
            final NodeList nlasmt = doc.getElementsByTagName("assessment");
            final Element assessment = (Element)nlasmt.item(0);
            this.vAssessment = asmt.printHtml(assessment, 0);
            final Vector vAsmtExtention = asmt.getAssessmentExtention(doc, iSec);
            final Vector vAmtResult = asmt.getAssessmentFeedback(doc, iSec);
            final Vector oAssessment = (Vector)mysession.getAttribute("SESSION_ASSESSMENT_NAME");
            Vector vAssessmentName = null;
            this.vAssessment.addElement(vAmtResult);
            this.vAssessment.addElement(vAsmtExtention);
            if (oAssessment == null) {
                vAssessmentName = new Vector();
            }
            else {
                vAssessmentName = oAssessment;
            }
            vAssessmentName.addElement(this.vAssessment);
            mysession.setAttribute("SESSION_ASSESSMENT_NAME", (Object)vAssessmentName);
        }
        final NodeList nl = doc.getElementsByTagName("section");
        final Element section = (Element)nl.item(iSec);
        final Section s = new Section(course_ids);
        final Vector vSectionREsult = s.getSectiontFeedback(section, iSec);
        final Vector vSectionExtention = s.getSectionExtention(section, iSec);
        final Vector<Vector> attributes = this.printElementAttributes(section);
        final Vector vSection = new Vector();
        final Vector vTime = new Vector();
        ++iSec;
        for (int j = 0; j < attributes.size(); ++j) {
            final Vector<String> attrib = attributes.elementAt(j);
            final String attrvalue = attrib.elementAt(1);
            vSection.addElement(attrvalue);
            vTime.addElement(strTime2);
        }
        vSection.addElement(vSectionREsult);
        vSection.addElement(vSectionExtention);
        final Vector oSection = (Vector)mysession.getAttribute("SESSION_SECTION_NAME");
        final Vector oSectionTime = (Vector)mysession.getAttribute("SESSION_TIME");
        Vector<Vector> vSectionName = null;
        Vector vSectionTime = null;
        if (oSection == null) {
            vSectionName = new Vector();
            vSectionTime = new Vector();
        }
        else {
            vSectionName = oSection;
            vSectionTime = oSectionTime;
        }
        vSectionName.addElement(vSection);
        vSectionTime.addElement(vTime);
        mysession.setAttribute("SESSION_TIME", (Object)vSectionTime);
        mysession.setAttribute("SESSION_SECTION_NAME", (Object)vSectionName);
        final OpenXML x = new OpenXML();
        final Vector vQuestions = x.openXML(section);
        v.addElement(vQuestions);
        mysession.setAttribute("SESSION_NAME", (Object)v);
        final Vector<String> strSectionName = vSectionName.elementAt(iSec - 1);
        final String Individual_Section_Id = strSectionName.elementAt(1);
        final String user_id = (String)mysession.getAttribute("student_id");
        final String unit_id = (String)mysession.getAttribute("unit_id");
        final Hashtable<String, String> hashIdentifier = (Hashtable)mysession.getAttribute("SESSION_COURSE_ITEM");
        final String sco_id = hashIdentifier.get(unit_id);
        final String qb_id = this.objectbankCheck(section);
        String assessment_type = "";
        if (qb_id != null) {
            assessment_type = "ob";
        }
        else {
            assessment_type = "asmt";
        }
        final XMLDocument manifest = EmbeddedAsmtDataBaseLayer.parse(unit_id, "csformat");
        final ImsManifestMeta ims = new ImsManifestMeta(manifest);
        final String assessment_id = ims.getLearningResourceType(identifier);
        if (iSec == 1) {
            EmbeddedAsmtDataBaseLayer.AssessmentUsermodel(user_id, unit_id, identifier, assessment_id, assessment_type, strTime2);
            EmbeddedAsmtDataBaseLayer.AssessmentSectionData(Individual_Section_Id, strTime2);
        }
        else if (iSec != 0) {
            EmbeddedAsmtDataBaseLayer.updateAssessmentSectionData(strTime2);
            EmbeddedAsmtDataBaseLayer.AssessmentSectionData(Individual_Section_Id, strTime2);
        }
        final String strResult = request.getParameter("secResult");
        if (strResult == null) {
            if (secId != null) {
                final Object o = mysession.getAttribute("SESSION_ANS");
                Vector vAns = null;
                if (o == null) {
                    vAns = new Vector();
                }
                else {
                    vAns = (Vector)o;
                }
                final Vector<Vector> questionAll = (v.elementAt(iSec - 2)).elementAt(0);
                final Vector vA = new Vector();
                for (int i = 0; i < questionAll.size(); ++i) {
                    final Vector<Vector> questionMatter = questionAll.elementAt(i);
                    final Vector<Integer> res = questionMatter.elementAt(0);
                    final Vector<String> question = questionMatter.elementAt(1);
                    final Integer iTitle = res.elementAt(0);
                    String strQuesNo = "";
                    String str = "";
                    switch (iTitle) {
                        case 0: {
                            strQuesNo = question.elementAt(0);
                            str = request.getParameter(strQuesNo);
                            vA.addElement(str);
                            break;
                        }
                        case 1: {
                            strQuesNo = question.elementAt(0);
                            final String[] str2 = request.getParameterValues(strQuesNo);
                            vA.addElement(str2);
                            break;
                        }
                        case 2: {
                            strQuesNo = question.elementAt(0);
                            str = request.getParameter(strQuesNo);
                            vA.addElement(str);
                            break;
                        }
                        case 3: {
                            strQuesNo = question.elementAt(0);
                            str = request.getParameter(strQuesNo);
                            vA.addElement(str);
                            break;
                        }
                        case 4: {
                            strQuesNo = question.elementAt(0);
                            str = request.getParameter(strQuesNo);
                            vA.addElement(str);
                            break;
                        }
                        case 5: {
                            strQuesNo = question.elementAt(0);
                            str = request.getParameter("q" + strQuesNo);
                            vA.addElement(str);
                            break;
                        }
                        case 6: {
                            strQuesNo = question.elementAt(0);
                            vA.addElement(str);
                            break;
                        }
                    }
                }
                vAns.addElement(vA);
                mysession.setAttribute("SESSION_ANS", (Object)vAns);
            }
        }
        else if (secId != null) {
            final Object o = mysession.getAttribute("SESSION_ANS");
            Vector vAns = null;
            if (o == null) {
                vAns = new Vector();
            }
            else {
                vAns = (Vector)o;
            }
            if (vTempAns != null) {
                vAns.addElement(vTempAns.elementAt(0));
            }
            mysession.setAttribute("SESSION_ANS", (Object)vAns);
        }
        final String strHTML = s.printHtml(section, iSec, nl, this.vAssessment, vQuestions, BROWSER_TYPE);
        out.println(strHTML);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    public XMLDocument HTMLParser(final String argv) {
        XMLDocument doc = null;
        try {
            final DOMParser parser = new DOMParser();
            final URL url = this.createURL(argv);
            parser.parse(url);
            doc = parser.getDocument();
        }
        catch (Exception e) {
            Assessment.log.fatal("Show warrings : " + e.toString());
            Assessment.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        return doc;
    }
    
    public URL createURL(final String fileName) {
        URL url = null;
        try {
            url = new URL(fileName);
        }
        catch (MalformedURLException ex) {
            final File f = new File(fileName);
            try {
                String path = f.getAbsolutePath();
                final String fs = System.getProperty("file.separator");
                if (fs.length() == 1) {
                    final char sep = fs.charAt(0);
                    if (sep != '/') {
                        path = path.replace(sep, '/');
                    }
                    if (path.charAt(0) != '/') {
                        path = '/' + path;
                    }
                }
                path = "file://" + path;
                url = new URL(path);
            }
            catch (MalformedURLException e) {
                Assessment.log.fatal("Cannot create url for: " + fileName);
                System.exit(0);
            }
        }
        return url;
    }
    
    public Vector printElementAttributes(final Node doc) {
        NamedNodeMap nnm = null;
        String attrname = "";
        String attrval = "";
        if (doc != null) {
            nnm = doc.getAttributes();
        }
        final Vector attributes = new Vector();
        if (nnm != null) {
            for (int i = 0; i < nnm.getLength(); ++i) {
                final Vector attrib = new Vector();
                final Node n = nnm.item(i);
                attrname = n.getNodeName();
                attrval = n.getNodeValue();
                attrib.addElement(attrname);
                attrib.addElement(attrval);
                attributes.addElement(attrib);
            }
        }
        return attributes;
    }
    
    public String objectbankCheck(final Element section) {
        String ob_check = null;
        NodeList nl = section.getElementsByTagName("selection_ordering");
        if (nl.getLength() == 0) {
            return null;
        }
        nl = ((Element)nl.item(0)).getElementsByTagName("selection");
        if (nl.getLength() == 0) {
            return null;
        }
        nl = ((Element)nl.item(0)).getElementsByTagName("sourcebank_ref");
        if (nl.getLength() == 0) {
            return null;
        }
        final Node n3 = nl.item(0);
        if (n3.hasChildNodes()) {
            final Node n2 = n3.getFirstChild();
            ob_check = n2.getNodeValue();
            return ob_check;
        }
        return null;
    }
    
    static {
        log = new SimpleLogger((Class)Assessment.class, true);
    }
}
