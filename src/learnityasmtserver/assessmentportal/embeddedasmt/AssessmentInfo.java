package learnityasmtserver.assessmentportal.embeddedasmt;

import org.grlea.log.*;
import oracle.xml.parser.v2.*;
import java.util.*;
import org.w3c.dom.*;

public class AssessmentInfo
{
    public final SimpleLogger log;
    String course_id;
    
    public AssessmentInfo(final String c_id) {
        this.log = new SimpleLogger((Class)AssessmentInfo.class, true);
        this.course_id = null;
        this.course_id = c_id;
    }
    
    public Vector getAssessmentExtention(final XMLDocument element, final int iSec) {
        final Hashtable hAsmttTable = new Hashtable();
        final Vector vListAssessment = new Vector(1, 1);
        final NodeList nlAssessmentext = element.getElementsByTagName("assessment_extention");
        if (nlAssessmentext != null && nlAssessmentext.getLength() > 0) {
            final NamedNodeMap nnmextention = nlAssessmentext.item(0).getAttributes();
            if (nnmextention != null) {
                final Node nSMSTitle = nnmextention.getNamedItem("result_message");
                if (nSMSTitle != null) {
                    final String strMessage = nSMSTitle.getNodeValue();
                    hAsmttTable.put("result_message", strMessage);
                }
            }
            final Node nFeedback = nlAssessmentext.item(0).getFirstChild();
            final NodeList nlFeedback = nFeedback.getChildNodes();
            for (int i = 0; i < nlFeedback.getLength(); ++i) {
                final Node nfb = nlFeedback.item(i);
                if ("feedback_info".equals(nfb.getNodeName())) {
                    final String strFeedback = nfb.getFirstChild().getNodeValue();
                    hAsmttTable.put("feedback_info", strFeedback);
                }
                else if ("success_msg".equals(nfb.getNodeName())) {
                    final Vector<Vector> vAttrib = this.printElementAttributes(nfb);
                    for (int j = 0; j < vAttrib.size(); ++j) {
                        final Vector<String> vList = vAttrib.elementAt(j);
                        final String name = vList.elementAt(0);
                        final String value = vList.elementAt(1);
                        hAsmttTable.put(name, value);
                    }
                    final String strFeedback2 = nfb.getFirstChild().getNodeValue();
                    hAsmttTable.put("success_msg", strFeedback2);
                }
                else if ("navigation_link".equals(nfb.getNodeName())) {
                    final Vector<Vector> vAttrib2 = this.printElementAttributes(nfb);
                    for (int k = 0; k < vAttrib2.size(); ++k) {
                        final Vector<String> vList = vAttrib2.elementAt(k);
                        final String name = vList.elementAt(0);
                        final String value = vList.elementAt(1);
                        hAsmttTable.put(name, value);
                    }
                }
                else if ("failuremessage".equals(nfb.getNodeName())) {
                    final Vector<Vector> vAttrib3 = this.printElementAttributes(nfb);
                    for (int j = 0; j < vAttrib3.size(); ++j) {
                        final Vector<String> vList = vAttrib3.elementAt(j);
                        final String name = vList.elementAt(0);
                        final String value = vList.elementAt(1);
                        hAsmttTable.put(name, value);
                    }
                    final String strFeedback2 = nfb.getFirstChild().getNodeValue();
                    hAsmttTable.put("failuremessage", strFeedback2);
                }
            }
            final Node nGradation = nFeedback.getNextSibling();
            final NamedNodeMap nnmGrd = nGradation.getAttributes();
            final String strdesc = nnmGrd.getNamedItem("grade_description").getNodeValue();
            hAsmttTable.put("grade_description", strdesc);
            if (strdesc.equals("true")) {
                final Node ngradationtext = nGradation.getFirstChild().getFirstChild();
                final String strMessage2 = ngradationtext.getNodeValue();
                hAsmttTable.put("gradation_text", strMessage2);
                final Node nodeScheme = nGradation.getFirstChild().getNextSibling();
                final NodeList nlGradation = nodeScheme.getChildNodes();
                final Vector vGradeScheme = new Vector(3, 3);
                for (int l = 0; l < nlGradation.getLength(); ++l) {
                    final Node ngrade = nlGradation.item(l);
                    final NamedNodeMap nnmngrade = ngrade.getAttributes();
                    final String[] obj = new String[3];
                    for (int m = 0; m < nnmngrade.getLength(); ++m) {
                        final Node ng = nnmngrade.item(m);
                        obj[m] = ng.getNodeValue();
                    }
                    vGradeScheme.addElement(obj);
                }
                hAsmttTable.put("gradation_scheme", vGradeScheme);
            }
            final Node nAssessmentSummary = nGradation.getNextSibling();
            final NodeList nlsummary_heading = element.getElementsByTagName("summary_heading");
            if (nlsummary_heading.getLength() > 0) {
                final String strSummary = nlsummary_heading.item(0).getFirstChild().getNodeValue();
                hAsmttTable.put("summary_heading", strSummary);
            }
            final NodeList nlsuccessmail = element.getElementsByTagName("successmail");
            if (nlsuccessmail.getLength() > 0) {
                final String strSummary2 = nlsuccessmail.item(0).getFirstChild().getNodeValue();
                hAsmttTable.put("successmail", strSummary2);
            }
            final NodeList nlfailuremail = element.getElementsByTagName("failuremail");
            if (nlfailuremail.getLength() > 0) {
                final String strSummary3 = nlfailuremail.item(0).getFirstChild().getNodeValue();
                hAsmttTable.put("failuremail", strSummary3);
            }
            final NodeList nlsummary_fields = element.getElementsByTagName("summary_fields");
            if (nlsummary_fields.getLength() > 0) {
                final NodeList nlFields = nlsummary_fields.item(0).getChildNodes();
                for (int i2 = 0; i2 < nlFields.getLength(); ++i2) {
                    final String strNodeName = nlFields.item(i2).getNodeName();
                    final String strSummary4 = nlFields.item(i2).getFirstChild().getNodeValue();
                    hAsmttTable.put(strNodeName, strSummary4);
                }
            }
            final NodeList nlsection_display = element.getElementsByTagName("section_display");
            if (nlsection_display.getLength() > 0) {
                final NamedNodeMap nnmsumFields = nlsection_display.item(0).getAttributes();
                for (int k2 = 0; k2 < nnmsumFields.getLength(); ++k2) {
                    final Node ng2 = nnmsumFields.item(k2);
                    final String strNode = ng2.getNodeName();
                    final String status = ng2.getNodeValue();
                    hAsmttTable.put(strNode, status);
                }
            }
        }
        vListAssessment.addElement(hAsmttTable);
        return vListAssessment;
    }
    
    public Vector getAssessmentFeedback(final XMLDocument element, final int iSec) {
        String ch1 = null;
        String ch2 = null;
        final Vector vResult = new Vector(3, 3);
        final NodeList nlsectionfeedback = element.getElementsByTagName("assessfeedback");
        final Node fb = nlsectionfeedback.item(0).getFirstChild().getFirstChild().getFirstChild();
        final Node fb2 = nlsectionfeedback.item(1).getFirstChild().getFirstChild().getFirstChild();
        if (fb != null) {
            ch1 = nlsectionfeedback.item(0).getFirstChild().getFirstChild().getFirstChild().getNodeValue();
        }
        else {
            ch1 = "You have passed in this test";
        }
        if (fb2 != null) {
            ch2 = nlsectionfeedback.item(1).getFirstChild().getFirstChild().getFirstChild().getNodeValue();
        }
        else {
            ch2 = "You have failed in this test";
        }
        vResult.addElement(ch1);
        vResult.addElement(ch2);
        return vResult;
    }
    
    public Vector printHtml(final Node AssessmentInfo, final int iSec) {
        final Vector vAssessment = new Vector();
        final Element n = (Element)AssessmentInfo;
        if (n != null) {
            final Vector<Vector> attributes = this.printElementAttributes(n);
            final Vector vAsmtTitle = new Vector(3, 3);
            for (int j = 0; j < attributes.size(); ++j) {
                final Vector<String> attrib = attributes.elementAt(j);
                final String attrname = attrib.elementAt(0);
                final String attrvalue = attrib.elementAt(1);
                vAsmtTitle.addElement(attrvalue);
            }
            vAssessment.addElement(vAsmtTitle);
            final String cutValue = this.outComes(n);
            vAssessment.addElement(cutValue);
            String duration = null;
            final NodeList nlDuration = n.getElementsByTagName("duration");
            if (nlDuration != null && nlDuration.getLength() > 0) {
                duration = nlDuration.item(0).getFirstChild().getNodeValue();
            }
            vAssessment.addElement(duration);
            final NodeList objectives = n.getElementsByTagName("objectives");
            String strTitle = "";
            if (objectives != null && objectives.getLength() > 0) {
                final String strView = ((Element)objectives.item(0)).getAttribute("view");
                if (strView.equals("Candidate")) {
                    final Node nObj = objectives.item(0);
                    Node childResponse = nObj.getFirstChild();
                    do {
                        childResponse = childResponse.getFirstChild();
                    } while (childResponse.getNodeName() != "#text");
                    strTitle = childResponse.getNodeValue();
                }
            }
        }
        return vAssessment;
    }
    
    public Vector printElementAttributes(final Node doc) {
        String attrname = "";
        String attrval = "";
        final NamedNodeMap nnm = doc.getAttributes();
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
    
    public String outComes(final Element sec) {
        String cutvalue = null;
        final NodeList nl = sec.getElementsByTagName("outcomes_processing");
        if (nl != null) {
            final Element n = (Element)nl.item(0);
            final NodeList decvar = n.getElementsByTagName("decvar");
            cutvalue = this.elementAttributes(decvar.item(0));
        }
        return cutvalue;
    }
    
    public String elementAttributes(final Node doc) {
        String attrname = "";
        String attrval = "";
        final NamedNodeMap nnm = doc.getAttributes();
        if (nnm != null) {
            for (int i = 0; i < nnm.getLength(); ++i) {
                final Node n = nnm.item(i);
                attrname = n.getNodeName();
                attrval = n.getNodeValue();
                if (attrname.equals("cutvalue")) {
                    break;
                }
            }
        }
        return attrval;
    }
}
