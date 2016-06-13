package learnityasmtserver.assessmentportal.embeddedasmt;

import org.grlea.log.*;
import java.util.*;

import org.w3c.dom.*;

public class Section
{
    public static final SimpleLogger log;
    String course_id;
    String browser;
    
    public Section(final String c_id) {
        this.course_id = null;
        this.browser = null;
        this.course_id = c_id;
    }
    
    public Vector getSectionExtention(final Node doc, final int iSec) {
        final Element element = (Element)doc;
        final Hashtable hSectionExtention = new Hashtable();
        final NodeList nlSectionExt = element.getElementsByTagName("complation_message");
        if (nlSectionExt.getLength() > 0) {
            final String strSummary = nlSectionExt.item(0).getFirstChild().getNodeValue();
            hSectionExtention.put("complation_message", strSummary);
        }
        final NodeList nlSectionExtFb = element.getElementsByTagName("feedbackdetails_extention");
        if (nlSectionExtFb.getLength() > 0) {
            final NodeList nlChildenode = nlSectionExtFb.item(0).getChildNodes();
            for (int j = 0; j < nlChildenode.getLength(); ++j) {
                final String strSection = nlChildenode.item(j).getNodeName();
                if ("feedback_heading".equals(strSection)) {
                    final String strValue = nlChildenode.item(j).getFirstChild().getNodeValue();
                    hSectionExtention.put(strSection, strValue);
                }
                else if ("success_message".equals(strSection)) {
                    final String strValue = nlChildenode.item(j).getFirstChild().getNodeValue();
                    hSectionExtention.put(strSection, strValue);
                }
                else if ("navigation_link".equals(strSection)) {
                    final Node nNavigation = nlChildenode.item(j);
                    final Vector vNavigation = this.printElementAttributes(nNavigation);
                    hSectionExtention.put(strSection, vNavigation);
                }
                else if ("failuremessage".equals(strSection)) {
                    final Node nNavigation = nlChildenode.item(j);
                    final Vector vNavigation = this.printElementAttributes(nNavigation);
                    hSectionExtention.put("remediation_link", vNavigation);
                    final String strValue2 = nlChildenode.item(j).getFirstChild().getNodeValue();
                    hSectionExtention.put(strSection, strValue2);
                }
            }
        }
        final NodeList nlSectionExtsm = element.getElementsByTagName("section_summary_extention");
        if (nlSectionExtsm.getLength() > 0) {
            final NodeList nlSectionExt2 = element.getElementsByTagName("summary_heading");
            if (nlSectionExt2.getLength() > 0) {
                final String strSummary2 = nlSectionExt2.item(0).getFirstChild().getNodeValue();
                hSectionExtention.put("summary_heading", strSummary2);
            }
            final NodeList nlSectionExt3 = element.getElementsByTagName("section_summary_fields");
            if (nlSectionExt3.getLength() > 0) {
                final NodeList nlChildenode2 = nlSectionExt3.item(0).getChildNodes();
                for (int i = 0; i < nlChildenode2.getLength(); ++i) {
                    final String strSection2 = nlChildenode2.item(i).getNodeName();
                    if ("total_questions".equals(strSection2)) {
                        hSectionExtention.put(strSection2, nlChildenode2.item(i).getFirstChild().getNodeValue());
                    }
                    else if ("total_correct".equals(strSection2)) {
                        hSectionExtention.put(strSection2, nlChildenode2.item(i).getFirstChild().getNodeValue());
                    }
                    else if ("percent_correct".equals(strSection2)) {
                        hSectionExtention.put(strSection2, nlChildenode2.item(i).getFirstChild().getNodeValue());
                    }
                    else if ("total_incorrect".equals(strSection2)) {
                        hSectionExtention.put(strSection2, nlChildenode2.item(i).getFirstChild().getNodeValue());
                    }
                    else if ("percent_incorrect".equals(strSection2)) {
                        hSectionExtention.put(strSection2, nlChildenode2.item(i).getFirstChild().getNodeValue());
                    }
                    else if ("total_marks".equals(strSection2)) {
                        hSectionExtention.put(strSection2, nlChildenode2.item(i).getFirstChild().getNodeValue());
                    }
                    else if ("pass_marks".equals(strSection2)) {
                        hSectionExtention.put(strSection2, nlChildenode2.item(i).getFirstChild().getNodeValue());
                    }
                    else if ("obtained_marks".equals(strSection2)) {
                        hSectionExtention.put(strSection2, nlChildenode2.item(i).getFirstChild().getNodeValue());
                    }
                    else if ("percent_marks".equals(strSection2)) {
                        hSectionExtention.put(strSection2, nlChildenode2.item(i).getFirstChild().getNodeValue());
                    }
                    else if ("feedback_status".equals(strSection2)) {
                        hSectionExtention.put(strSection2, nlChildenode2.item(i).getFirstChild().getNodeValue());
                    }
                }
            }
            final NodeList nlSectionExt4 = element.getElementsByTagName("item_display");
            if (nlSectionExt4.getLength() > 0) {
                final NamedNodeMap nnmItem = nlSectionExt4.item(0).getAttributes();
                for (int k = 0; k < nnmItem.getLength(); ++k) {
                    final Node ng = nnmItem.item(k);
                    final String strNode = ng.getNodeName();
                    final String status = ng.getNodeValue();
                    hSectionExtention.put(strNode, status);
                }
            }
            final NodeList nlSectionExt5 = element.getElementsByTagName("itemfeedback_extention");
            if (nlSectionExt5.getLength() > 0) {
                final NodeList nlChildenode3 = nlSectionExt5.item(0).getChildNodes();
                for (int l = 0; l < nlChildenode3.getLength(); ++l) {
                    final String strSection3 = nlChildenode3.item(l).getNodeName();
                    if ("item_id".equals(strSection3)) {
                        hSectionExtention.put(strSection3, nlChildenode3.item(l).getFirstChild().getNodeValue());
                    }
                    else if ("item_name".equals(strSection3)) {
                        hSectionExtention.put(strSection3, nlChildenode3.item(l).getFirstChild().getNodeValue());
                    }
                    else if ("item_type".equals(strSection3)) {
                        hSectionExtention.put(strSection3, nlChildenode3.item(l).getFirstChild().getNodeValue());
                    }
                    else if ("item_answer".equals(strSection3)) {
                        hSectionExtention.put(strSection3, nlChildenode3.item(l).getFirstChild().getNodeValue());
                    }
                    else if ("item_marks".equals(strSection3)) {
                        hSectionExtention.put(strSection3, nlChildenode3.item(l).getFirstChild().getNodeValue());
                    }
                    else if ("item_obmarks".equals(strSection3)) {
                        hSectionExtention.put(strSection3, nlChildenode3.item(l).getFirstChild().getNodeValue());
                    }
                    else if ("item_correct".equals(strSection3)) {
                        hSectionExtention.put(strSection3, nlChildenode3.item(l).getFirstChild().getNodeValue());
                    }
                    else if ("item_incorrect".equals(strSection3)) {
                        hSectionExtention.put(strSection3, nlChildenode3.item(l).getFirstChild().getNodeValue());
                    }
                    else if ("item_feedback".equals(strSection3)) {
                        hSectionExtention.put(strSection3, nlChildenode3.item(l).getFirstChild().getNodeValue());
                    }
                }
            }
        }
        final Vector vSectionList = new Vector(1, 1);
        vSectionList.addElement(hSectionExtention);
        return vSectionList;
    }
    
    public Vector getSectiontFeedback(final Node element, final int iSec) {
        final Element doc = (Element)element;
        String ch1 = null;
        String ch2 = null;
        final Vector vResult = new Vector(3, 3);
        final NodeList nlsectionfeedback = doc.getElementsByTagName("sectionfeedback");
        final Node fb = nlsectionfeedback.item(0).getFirstChild().getFirstChild().getFirstChild();
        final Node fb2 = nlsectionfeedback.item(1).getFirstChild().getFirstChild().getFirstChild();
        if (fb != null) {
            ch1 = nlsectionfeedback.item(0).getFirstChild().getFirstChild().getFirstChild().getNodeValue();
        }
        else {
            ch1 = "You have passed in this section";
        }
        if (fb2 != null) {
            ch2 = nlsectionfeedback.item(1).getFirstChild().getFirstChild().getFirstChild().getNodeValue();
        }
        else {
            ch2 = "You have failed in this section";
        }
        vResult.addElement(ch1);
        vResult.addElement(ch2);
        return vResult;
    }
    
    public String printHtml(final Node section, final int iSec, final NodeList nl, final Vector vAssessment, final Vector vQuestions, final String BROWSER_TYPE) {
        String strHTML = "";
        final Element n = (Element)section;
        if (n != null) {
            final Vector<Vector<String>> attributes = this.printElementAttributes(n);
            final Vector vSection = new Vector();
            for (int j = 0; j < attributes.size(); ++j) {
                final Vector<String> attrib = attributes.elementAt(j);
                final String attrvalue = attrib.elementAt(1);
                vSection.addElement(attrvalue);
            }
            String duration = null;
            final NodeList nlDuration = n.getElementsByTagName("duration");
            if (nlDuration != null && nlDuration.getLength() > 0) {
                duration = nlDuration.item(0).getFirstChild().getNodeValue();
            }
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
            vSection.addElement(strTitle);
            boolean bRefresh = true;
            String strButton = "";
            String strButtonResult = "";
            if (nl.getLength() > 1 && iSec < nl.getLength()) {
                strButton = strButton + "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Next Section\" type=\"button\" value=\"Next Section\" onclick=\"submit_onclick(" + iSec + ")\"/>";
                strButtonResult = strButtonResult + "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\" Section Result\" type=\"button\" value=\"Section Result\" onclick=\"Resultsubmit_onclick(" + iSec + ")\"/>";
            }
            if (iSec == nl.getLength()) {
                strButton += "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\"Submit\" type=\"button\" value=\"Submit\" onclick=\"submitPage()\"/>";
                strButtonResult = strButtonResult + "\n<input class=\"sbttn\" name=\"add\" tabIndex=\"2\" title=\" Section Result\" type=\"button\" value=\"Section Result\" onclick=\"Resultsubmit1_onclick(" + iSec + ")\"/>";
            }
            if (duration != null && iSec < nl.getLength()) {
                bRefresh = true;
            }
            if (duration != null && iSec == nl.getLength()) {
                bRefresh = false;
            }
            final Vector vQAll = (Vector) vQuestions.elementAt(0);
            if (BROWSER_TYPE.equals("mf")) {
                final GenerateXHTML y = new GenerateXHTML(this.course_id);
                strHTML = y.generateHTML(vSection, vQAll, strButton, strButtonResult, duration, bRefresh, iSec, vAssessment);
            }
            else {
                final GenerateHTML y2 = new GenerateHTML(this.course_id);
                strHTML = y2.generateHTML(vSection, vQAll, strButton, strButtonResult, duration, bRefresh, iSec, vAssessment);
            }
        }
        return strHTML;
    }
    
    public Vector<Vector<String>> printElementAttributes(final Node doc) {
        String attrname = "";
        String attrval = "";
        final NamedNodeMap nnm = doc.getAttributes();
        final Vector<Vector<String>> attributes = new Vector<Vector<String>>();
        if (nnm != null) {
            for (int i = 0; i < nnm.getLength(); ++i) {
                final Vector<String> attrib = new Vector<String>();
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
    
    public Vector temp(final Vector QAll) {
        return QAll;
    }
    
    static {
        log = new SimpleLogger((Class)Section.class, true);
    }
}
