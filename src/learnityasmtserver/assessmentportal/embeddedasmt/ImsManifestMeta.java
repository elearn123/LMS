package learnityasmtserver.assessmentportal.embeddedasmt;

import oracle.xml.parser.v2.*;
import org.w3c.dom.*;
import java.util.*;
import org.grlea.log.*;

public class ImsManifestMeta
{
    public static final SimpleLogger log;
    XMLDocument manifest;
    
    public ImsManifestMeta(final XMLDocument doc) {
        this.manifest = null;
        this.manifest = doc;
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
        System.out.println("strResourceIdentifier==ImsManifestMeta=1===" + strResourceIdentifier);
        return strResourceIdentifier;
    }
    
    public String[] getResourceIdentifierForItemRef(final String ls) {
        final NodeList nl = this.getOrganization(ls).getElementsByTagName("item");
        Node attrNode = null;
        final String[] strResourceIdentifier = new String[2];
        boolean TEST = true;
        for (int i = 0; i < nl.getLength() && TEST; ++i) {
            attrNode = ((Element)nl.item(i)).getAttributeNode("identifier");
            if (attrNode != null) {
                final String strIdentifier = attrNode.getNodeValue();
                final Node attr = ((Element)nl.item(i)).getAttributeNode("identifierref");
                if (attr != null) {
                    strResourceIdentifier[0] = attr.getNodeValue();
                    strResourceIdentifier[1] = strIdentifier;
                    TEST = false;
                    break;
                }
            }
        }
        System.out.println("strResourceIdentifier==ImsManifestMeta=2===" + strResourceIdentifier);
        return strResourceIdentifier;
    }
    
    public String getResourceLocation(final String strResRef) {
        System.out.println("strResRef==ImsManifestMeta====" + strResRef);
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
        System.out.println("strLocation==ImsManifestMeta====" + strLocation);
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
        return n;
    }
    
    public Vector getLearningResourceList() {
        final Vector vLearner = new Vector(3, 3);
        final NodeList nl = this.manifest.getElementsByTagName("learningresourcetype");
        for (int loop = 0; loop < nl.getLength(); ++loop) {
            if (nl.item(loop).getFirstChild() != null) {
                final Vector vLearnIdentifier = new Vector(3, 3);
                final String strLearningResourceType = nl.item(loop).getFirstChild().getNodeValue();
                final Node nItem = nl.item(loop).getParentNode().getParentNode().getParentNode().getParentNode();
                final String title = nItem.getFirstChild().getFirstChild().getNodeValue();
                final String identifier = nItem.getAttributes().item(0).getNodeValue();
                vLearnIdentifier.addElement(strLearningResourceType);
                vLearnIdentifier.addElement(title);
                vLearnIdentifier.addElement(identifier);
                vLearner.addElement(vLearnIdentifier);
            }
        }
        return vLearner;
    }
    
    public String getLearningResourceType(final String identifier) {
        String title = "";
        try {
            final NodeList nls = this.manifest.getElementsByTagName("item");
            NamedNodeMap nnm = null;
            int count = 0;
            for (boolean flag = true; count < nls.getLength() && flag; ++count) {
                nnm = nls.item(count).getAttributes();
                for (int j = 0; j < nnm.getLength(); ++j) {
                    if (nnm.item(j).getNodeValue().equals(identifier)) {
                        title = nls.item(count).getFirstChild().getFirstChild().getNodeValue();
                        flag = false;
                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            ImsManifestMeta.log.fatal("Exception" + e.getMessage());
            ImsManifestMeta.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        return title;
    }
    
    static {
        log = new SimpleLogger((Class)ImsManifestMeta.class, true);
    }
}
