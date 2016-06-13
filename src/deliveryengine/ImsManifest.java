package deliveryengine;

import org.w3c.dom.*;
import java.util.*;

public class ImsManifest
{
    Document manifest;
    String userID;
    String courseID;
    
    ImsManifest(final Document doc) {
        this.manifest = null;
        this.userID = null;
        this.courseID = null;
        this.manifest = doc;
    }
    
    ImsManifest(final Document doc, final String userid, final String courseid) {
        this.manifest = null;
        this.userID = null;
        this.courseID = null;
        this.manifest = doc;
        this.userID = userid;
        this.courseID = courseid;
    }
    
    public String getIdentifierForFirstItemSkippingNoContent(final String ls) {
        final NodeList nl = this.getOrganization(ls).getElementsByTagName("item");
        NamedNodeMap nnm = null;
        int count = 0;
        boolean flag = true;
        String strIdentifier = null;
        while (count < nl.getLength() && flag) {
            nnm = nl.item(count).getAttributes();
            for (int i = 0; i < nnm.getLength(); ++i) {
                if (nnm.item(i).getNodeName().equals("identifierref")) {
                    for (int j = 0; j < nnm.getLength(); ++j) {
                        if (nnm.item(j).getNodeName().equals("identifier")) {
                            strIdentifier = nnm.item(j).getNodeValue();
                        }
                    }
                    flag = false;
                    break;
                }
            }
            ++count;
        }
        return strIdentifier;
    }
    
    public String getIdentifierForLastItemSkippingNoContent(final String ls) {
        final NodeList nl = this.getOrganization(ls).getElementsByTagName("item");
        NamedNodeMap nnm = null;
        int count = nl.getLength() - 1;
        boolean flag = true;
        String strIdentifier = null;
        while (count >= 0 && flag) {
            nnm = nl.item(count).getAttributes();
            for (int i = 0; i < nnm.getLength(); ++i) {
                if (nnm.item(i).getNodeName().equals("identifierref")) {
                    for (int j = 0; j < nnm.getLength(); ++j) {
                        if (nnm.item(j).getNodeName().equals("identifier")) {
                            strIdentifier = nnm.item(j).getNodeValue();
                        }
                    }
                    flag = false;
                    break;
                }
            }
            --count;
        }
        return strIdentifier;
    }
    
    public String getResourceIdentifierForItemRef(final String strItemRef, final String ls) {
        final NodeList nl = this.getOrganization(ls).getElementsByTagName("item");
        Node attrNode = null;
        String strResourceIdentifier = null;
        for (int i = 0; i < nl.getLength(); ++i) {
            attrNode = ((Element)nl.item(i)).getAttributeNode("identifier");
            if (attrNode.getNodeValue().equals(strItemRef)) {
                final Node attr = ((Element)nl.item(i)).getAttributeNode("identifierref");
                if (attr != null) {
                    strResourceIdentifier = attr.getNodeValue();
                }
            }
        }
        return strResourceIdentifier;
    }
    
    public String getIdentifierOfNextNodeSkippingNoContent(final String strIdentifier, final String ls) {
        String strIdentifierOfNextNode = null;
        final NodeList nl = this.getOrganization(ls).getElementsByTagName("item");
        Node attrNodeFirst = null;
        Node attrNode = null;
        int i = 0;
        while (i < nl.getLength()) {
            attrNodeFirst = ((Element)nl.item(i)).getAttributeNode("identifier");
            if (attrNodeFirst.getNodeValue().equals(strIdentifier)) {
                if (i + 1 < nl.getLength()) {
                    for (int j = i + 1; j < nl.getLength(); ++j) {
                        attrNode = ((Element)nl.item(j)).getAttributeNode("identifierref");
                        if (attrNode != null) {
                            strIdentifierOfNextNode = ((Element)nl.item(j)).getAttributeNode("identifier").getNodeValue();
                            if (!this.checkPrerequisites(this.userID, this.courseID, strIdentifierOfNextNode)) {
                                break;
                            }
                            if (j >= nl.getLength() - 1) {
                                for (j = 0; j < nl.getLength(); ++j) {
                                    attrNode = ((Element)nl.item(j)).getAttributeNode("identifierref");
                                    if (attrNode != null) {
                                        strIdentifierOfNextNode = ((Element)nl.item(j)).getAttributeNode("identifier").getNodeValue();
                                        if (!this.checkPrerequisites(this.userID, this.courseID, strIdentifierOfNextNode)) {
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                for (int j = 0; j < nl.getLength(); ++j) {
                    attrNode = ((Element)nl.item(j)).getAttributeNode("identifierref");
                    if (attrNode != null) {
                        strIdentifierOfNextNode = ((Element)nl.item(j)).getAttributeNode("identifier").getNodeValue();
                        if (!this.checkPrerequisites(this.userID, this.courseID, strIdentifierOfNextNode)) {
                            break;
                        }
                    }
                }
                break;
            }
            else {
                ++i;
            }
        }
        return strIdentifierOfNextNode;
    }
    
    public String getIdentifierOfPreviousNodeSkippingNoContent(final String strIdentifier, final String ls) {
        String strIdentifierOfPreviousNode = null;
        final NodeList nl = this.getOrganization(ls).getElementsByTagName("item");
        Node attrNodeFirst = null;
        Node attrNode = null;
        int i = 0;
        while (i < nl.getLength()) {
            attrNodeFirst = ((Element)nl.item(i)).getAttributeNode("identifier");
            if (attrNodeFirst.getNodeValue().equals(strIdentifier)) {
                if (i - 1 > 0) {
                    for (int j = i - 1; j >= 0; --j) {
                        attrNode = ((Element)nl.item(j)).getAttributeNode("identifierref");
                        if (attrNode != null) {
                            strIdentifierOfPreviousNode = ((Element)nl.item(j)).getAttributeNode("identifier").getNodeValue();
                            if (!this.checkPrerequisites(this.userID, this.courseID, strIdentifierOfPreviousNode)) {
                                break;
                            }
                            if (j <= 0) {
                                for (j = nl.getLength() - 1; j >= 0; --j) {
                                    attrNode = ((Element)nl.item(j)).getAttributeNode("identifierref");
                                    if (attrNode != null) {
                                        strIdentifierOfPreviousNode = ((Element)nl.item(j)).getAttributeNode("identifier").getNodeValue();
                                        if (!this.checkPrerequisites(this.userID, this.courseID, strIdentifierOfPreviousNode)) {
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                for (int j = nl.getLength() - 1; j >= 0; --j) {
                    attrNode = ((Element)nl.item(j)).getAttributeNode("identifierref");
                    if (attrNode != null) {
                        strIdentifierOfPreviousNode = ((Element)nl.item(j)).getAttributeNode("identifier").getNodeValue();
                        if (!this.checkPrerequisites(this.userID, this.courseID, strIdentifierOfPreviousNode)) {
                            break;
                        }
                    }
                }
                break;
            }
            else {
                ++i;
            }
        }
        return strIdentifierOfPreviousNode;
    }
    
    public String getIdentifierOfTopNodeSkippingNoContent(final String strIdentifier, final String ls) {
        String strIdentifierOfTopNode = null;
        final NodeList nl = this.getOrganization(ls).getElementsByTagName("item");
        Node attrNodeFirst = null;
        Node attrNode = null;
        for (int i = 0; i < nl.getLength(); ++i) {
            attrNodeFirst = ((Element)nl.item(i)).getAttributeNode("identifier");
            if (attrNodeFirst.getNodeValue().equals(strIdentifier)) {
                attrNode = ((Element)nl.item(i).getParentNode()).getAttributeNode("identifier");
                strIdentifierOfTopNode = attrNode.getNodeValue();
                break;
            }
        }
        return strIdentifierOfTopNode;
    }
    
    public String getResourceLocation(final String strResRef) {
        String strLocation = null;
        final NodeList nl = this.manifest.getElementsByTagName("resources");
        NodeList nListResource = null;
        NamedNodeMap nnm1 = null;
        for (int i = 0; i < nl.getLength(); ++i) {
            nListResource = ((Element)nl.item(i)).getElementsByTagName("resource");
            for (int j = 0; j < nListResource.getLength(); ++j) {
                nnm1 = nListResource.item(j).getAttributes();
                for (int k = 0; k < nnm1.getLength(); ++k) {
                    if (nnm1.item(k).getNodeName().equals("identifier") && nnm1.item(k).getNodeValue().equals(strResRef)) {
                        for (int r = 0; r < nnm1.getLength(); ++r) {
                            if (nnm1.item(r).getNodeName().equalsIgnoreCase("href")) {
                                strLocation = nnm1.item(r).getNodeValue();
                            }
                        }
                    }
                }
            }
        }
        return strLocation;
    }
    
    public Element getOrganization(final String ls) {
        String defaultOrg = null;
        Element n = null;
        final NodeList nl = this.manifest.getElementsByTagName("organization");
        for (int i = 0; i < nl.getLength(); ++i) {
            final Element el = (Element)nl.item(i);
            if (el.getAttributeNode("identifier").getValue().equals(ls)) {
                n = el;
                break;
            }
        }
        if (n == null) {
            defaultOrg = ((Element)this.manifest.getElementsByTagName("organizations").item(0)).getAttributeNode("default").getValue();
            for (int i = 0; i < nl.getLength(); ++i) {
                final Element el = (Element)nl.item(i);
                if (el.getAttributeNode("identifier").getValue().equals(defaultOrg)) {
                    n = el;
                    break;
                }
            }
        }
        if (n == null) {
            final Element el2 = n = (Element)nl.item(0);
        }
        return n;
    }
    
    public boolean checkPrerequisites(final String userID, final String courseID, final String identifier) {
        StringTokenizer st = null;
        final String strPrerequisites = DataBaseLayer.selectPrerequisites1(userID, courseID, identifier);
        if (!strPrerequisites.equals("") && strPrerequisites != null) {
            if (strPrerequisites.indexOf("<>") > -1) {
                st = new StringTokenizer(strPrerequisites, "<>");
                if (st.countTokens() != 2) {
                    return false;
                }
                final String iden = st.nextToken().trim();
                final String cond = st.nextToken().trim();
                if (DataBaseLayer.getStatus(userID, courseID, iden).trim().equalsIgnoreCase("not attempted")) {
                    return true;
                }
                try {
                    final int dep = Integer.parseInt(cond);
                    final int req = Integer.parseInt(DataBaseLayer.getMarks(userID, courseID, iden));
                    return req == dep;
                }
                catch (NumberFormatException nfe) {
                    return false;
                }
            }
            if (strPrerequisites.indexOf("<=") > -1) {
                st = new StringTokenizer(strPrerequisites, "<=");
                if (st.countTokens() != 2) {
                    return false;
                }
                final String iden = st.nextToken().trim();
                final String cond = st.nextToken().trim();
                if (DataBaseLayer.getStatus(userID, courseID, iden).trim().equalsIgnoreCase("not attempted")) {
                    return true;
                }
                try {
                    final int dep = Integer.parseInt(cond);
                    final int req = Integer.parseInt(DataBaseLayer.getMarks(userID, courseID, iden));
                    return req > dep;
                }
                catch (NumberFormatException nfe) {
                    return false;
                }
            }
            if (strPrerequisites.indexOf(">=") > -1) {
                st = new StringTokenizer(strPrerequisites, ">=");
                if (st.countTokens() != 2) {
                    return false;
                }
                final String iden = st.nextToken().trim();
                final String cond = st.nextToken().trim();
                if (DataBaseLayer.getStatus(userID, courseID, iden).trim().equalsIgnoreCase("not attempted")) {
                    return true;
                }
                try {
                    final int dep = Integer.parseInt(cond);
                    final int req = Integer.parseInt(DataBaseLayer.getMarks(userID, courseID, iden));
                    return req < dep;
                }
                catch (NumberFormatException nfe) {
                    return false;
                }
            }
            if (strPrerequisites.indexOf("=") > -1) {
                st = new StringTokenizer(strPrerequisites, "=");
                if (st.countTokens() != 2) {
                    return false;
                }
                final String iden = st.nextToken().trim();
                final String cond = st.nextToken().trim();
                if (DataBaseLayer.getStatus(userID, courseID, iden).trim().equalsIgnoreCase("not attempted")) {
                    return true;
                }
                try {
                    final int dep = Integer.parseInt(cond);
                    final int req = Integer.parseInt(DataBaseLayer.getMarks(userID, courseID, iden));
                    return req != dep;
                }
                catch (NumberFormatException nfe) {
                    return false;
                }
            }
            if (strPrerequisites.indexOf(">") > -1) {
                st = new StringTokenizer(strPrerequisites, ">");
                if (st.countTokens() != 2) {
                    return false;
                }
                final String iden = st.nextToken().trim();
                final String cond = st.nextToken().trim();
                if (DataBaseLayer.getStatus(userID, courseID, iden).trim().equalsIgnoreCase("not attempted")) {
                    return true;
                }
                try {
                    final int dep = Integer.parseInt(cond);
                    final int req = Integer.parseInt(DataBaseLayer.getMarks(userID, courseID, iden));
                    return req <= dep;
                }
                catch (NumberFormatException nfe) {
                    return false;
                }
            }
            if (strPrerequisites.indexOf("<") > -1) {
                st = new StringTokenizer(strPrerequisites, "<");
                if (st.countTokens() != 2) {
                    return false;
                }
                final String iden = st.nextToken().trim();
                final String cond = st.nextToken().trim();
                if (DataBaseLayer.getStatus(userID, courseID, iden).trim().equalsIgnoreCase("not attempted")) {
                    return true;
                }
                try {
                    final int dep = Integer.parseInt(cond);
                    final int req = Integer.parseInt(DataBaseLayer.getMarks(userID, courseID, iden));
                    return req >= dep;
                }
                catch (NumberFormatException nfe) {
                    return false;
                }
            }
            final String LessonStatus = DataBaseLayer.getStatus(userID, courseID, strPrerequisites).trim();
            return LessonStatus.toLowerCase().equals("incomplete") || LessonStatus.toLowerCase().equals("not attempted") || LessonStatus.toLowerCase().equals("fail");
        }
        return false;
    }
    
    public String getResourseRef(final String identifier, final String ls) {
        final NodeList nl = this.getOrganization(ls).getElementsByTagName("item");
        NamedNodeMap nnm = null;
        int count = 0;
        boolean flag = true;
        String resourseid = null;
        String strIdentifier = null;
        while (count < nl.getLength() && flag) {
            nnm = nl.item(count).getAttributes();
            for (int i = 0; i < nnm.getLength(); ++i) {
                System.out.println("-->>>----ImsManifest---identifierref-- " + nnm.item(i).getNodeName().equals("identifierref"));
                if (nnm.item(i).getNodeName().equals("identifierref")) {
                    final String r = nnm.item(i).getNodeValue();
                    System.out.println("r======" + r);
                    for (int j = 0; j < nnm.getLength(); ++j) {
                        System.out.println("-->>>----ImsManifest---identifier-- " + nnm.item(j).getNodeName().equals("identifier"));
                        if (nnm.item(j).getNodeName().equals("identifier")) {
                            strIdentifier = nnm.item(j).getNodeValue();
                        }
                        if (strIdentifier.equals(identifier)) {
                            resourseid = r;
                        }
                    }
                    flag = false;
                    break;
                }
            }
            ++count;
        }
        System.out.println("resourseid======" + resourseid);
        return resourseid;
    }
}
