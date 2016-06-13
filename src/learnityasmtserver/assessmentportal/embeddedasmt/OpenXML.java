package learnityasmtserver.assessmentportal.embeddedasmt;

import learnityasmtserver.assessmentportal.dbconnection.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;
import org.grlea.log.*;
import java.util.*;

import org.w3c.dom.*;

import java.io.*;
import javax.xml.parsers.*;

public class OpenXML
{
    public static final SimpleLogger log;
    Node nScore;
    String strHTML;
    Boolean ifobjectBank;
    
    public OpenXML() {
        this.nScore = null;
        this.strHTML = "";
        this.ifobjectBank = false;
    }
    
    public Vector openXML(final Element doc) {
        Vector v = null;
        final Vector QV = new Vector();
        Vector questionAll = new Vector();
        if (doc != null) {
            final String qb_id = this.objectbankCheck(doc);
            if (qb_id != null) {
                this.ifobjectBank = true;
                v = this.selectItemId(qb_id, doc);
                questionAll = this.printHtml(v);
            }
            else {
                questionAll = this.PrintAsmtHTML(doc);
            }
        }
        QV.addElement(questionAll);
        final String outcomes = this.outComes(doc);
        QV.addElement(outcomes);
        return QV;
    }
    
    public Vector printHtml(final Vector v) {
        final Vector questionAll = new Vector();
        if (v != null) {
            int strqno = 0;
            for (int i = 0; i < v.size(); ++i) {
                final String item_id1 = (String) v.elementAt(i);
                final int item_id2 = Integer.parseInt(item_id1);
                final Vector<String> vTextTitle = EmbeddedAsmtDataBaseLayer.getItemTypeItemText(item_id2);
                final String item_text = vTextTitle.elementAt(0);
                final String item_type = vTextTitle.elementAt(1);
                int iQuesType = 0;
                if (item_type != null) {
                    if (item_type.equals("Multiple Choice")) {
                        iQuesType = 0;
                    }
                    if (item_type.equals("Multiple Response")) {
                        iQuesType = 1;
                    }
                    if (item_type.equals("Fill in Blank")) {
                        iQuesType = 2;
                    }
                    if (item_type.equals("True False")) {
                        iQuesType = 3;
                    }
                    if (item_type.equals("Essay Type Question")) {
                        iQuesType = 5;
                    }
                    if (item_type.equals("Slider Test")) {
                        iQuesType = 6;
                    }
                    if (item_type.equals("Matching Question")) {
                        iQuesType = 7;
                    }
                }
                Vector questionMatter = new Vector();
                switch (iQuesType) {
                    case 0: {
                        ++strqno;
                        questionMatter = this.openMCI(item_text, item_id2);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 1: {
                        ++strqno;
                        questionMatter = this.openMRI(item_text, item_id2);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 2: {
                        ++strqno;
                        questionMatter = this.openFIB(item_text, item_id2);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 3: {
                        ++strqno;
                        questionMatter = this.openTF(item_text, item_id2);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 5: {
                        ++strqno;
                        questionMatter = this.openEssay(item_text, item_id2);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 6: {
                        ++strqno;
                        questionMatter = this.openSlide(item_text, item_id2);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 7: {
                        ++strqno;
                        questionMatter = this.openMQ(item_text, item_id2);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                }
            }
        }
        return questionAll;
    }
    
    public Vector selectItemId(final String qb_id, final Element doc) {
        Vector v = new Vector();
        final Vector vAssess_id = EmbeddedAsmtDataBaseLayer.getAssess_id(qb_id);
        final int i = this.selectionNo(doc);
        if ("Sequential".equals(this.orderSection(doc))) {
            if (i == -1) {
                v = vAssess_id;
            }
            else {
                v = this.sequence(vAssess_id, i);
            }
        }
        else if ("Random".equals(this.orderSection(doc))) {
            if (i == -1) {
                v = this.rand(vAssess_id);
            }
            else if (this.sectionMetaData1(doc) != 0) {
                final Vector temp = this.MetaData(vAssess_id, doc);
                System.out.println("=====test3====temp=======" + temp);
                v = this.rand(temp, i);
                System.out.println("=====test4======v ==1===" + v);
            }
            else {
                v = this.rand(vAssess_id, i);
                System.out.println("=====test4======v ==2===" + v);
            }
        }
        else if ("All".equals(this.orderSection(doc))) {
            v = vAssess_id;
        }
        else {
            v = vAssess_id;
        }
        return v;
    }
    
    public Vector MetaData(final Vector vAssess_id, final Element doc) {
        final Vector temp = new Vector();
        Vector vmetadata2 = this.sectionMetaData(doc);
        System.out.println("====vmetadata2=MetaData==" + vmetadata2);
        final String selectionType = (String) vmetadata2.elementAt(0);
        System.out.println("====selectionType===" + selectionType);
        for (int i3 = 0; i3 < vAssess_id.size(); ++i3) {
            final String item_id1 = (String) vAssess_id.elementAt(i3);
            System.out.println("====item_id1=MetaData==" + item_id1);
            final int item_id2 = Integer.parseInt(item_id1);
            final Vector vItemMetaData = EmbeddedAsmtDataBaseLayer.getItemItemMetaData(item_id2);
            System.out.println("==1==vItemMetaData==MetaData=" + vItemMetaData);
            boolean b = false;
            for (int i2 = 1; i2 < vmetadata2.size(); ++i2) {
                vmetadata2 = (Vector) vmetadata2.elementAt(i2);
                final String Mdname = (String) vmetadata2.elementAt(0);
                final String Mdoperator = (String) vmetadata2.elementAt(1);
                final String Mvalue = (String) vmetadata2.elementAt(2);
                if (selectionType.equals("and_selection")) {
                    System.out.println("strMdname = " + Mdname + " strMdoperator = " + Mdoperator + " strVal = " + Mvalue);
                    System.out.println("=2===vItemMetaData==MetaData=" + vItemMetaData);
                    b = this.itemMetaData(vItemMetaData, Mdname, Mdoperator, Mvalue);
                    if (!b) {
                        break;
                    }
                }
                if (selectionType.equals("or_selection")) {
                    b = this.itemMetaData(vItemMetaData, Mdname, Mdoperator, Mvalue);
                    if (b) {
                        break;
                    }
                }
                if (selectionType.equals("not_selection")) {
                    b = this.itemMetaData(vItemMetaData, Mdname, Mdoperator, Mvalue);
                    if (b) {
                        b &= false;
                        break;
                    }
                    b = !(false | b);
                }
            }
            System.out.println("==========b======MetaData=====" + b);
            if (b) {
                temp.addElement(item_id1);
                System.out.println("====temp===============nnnaaayyynnnaaa=======MetaData=====" + temp);
            }
        }
        return temp;
    }
    
    public Vector sectionMetaData(final Node selection) {
        final Vector vmetaData = new Vector();
        final NodeList nl = ((Element)selection).getElementsByTagName("selection_metadata");
        final Node e_parent = nl.item(0).getParentNode();
        final String e_p_name = e_parent.getNodeName();
        vmetaData.addElement(e_p_name);
        for (int i = 0; i < nl.getLength(); ++i) {
            final Element e = (Element)nl.item(i);
            final String strMdname = e.getAttribute("mdname");
            final String strMdoperator = e.getAttribute("mdoperator");
            final String strVal = nl.item(i).getFirstChild().getNodeValue();
            final Vector vmetaData2 = new Vector();
            vmetaData2.addElement(strMdname);
            vmetaData2.addElement(strMdoperator);
            vmetaData2.addElement(strVal);
            vmetaData.addElement(vmetaData2);
            System.out.println("====vmetaData===============sectionMetaData============" + vmetaData);
        }
        return vmetaData;
    }
    
    public int sectionMetaData1(final Node selection) {
        final Vector vmetaData = new Vector();
        final NodeList nl = ((Element)selection).getElementsByTagName("selection_metadata");
        System.out.println("==nl.getLength()=" + nl.getLength());
        return nl.getLength();
    }
    
    public boolean itemMetaData(final Vector vItemMetaData, final String Mdname, final String Mdoperator, final String Mvalue) {
        boolean b = false;
        System.out.println("=vItemMetaData======itemMetaData====" + vItemMetaData);
        System.out.println("=vItemMetaData.size()======itemMetaData====" + vItemMetaData.size());
        for (int n = 0; n < vItemMetaData.size(); ++n) {
            final Vector vItemMetaDatann = (Vector) vItemMetaData.elementAt(n);
            final String metadatafield_name = (String) vItemMetaDatann.elementAt(0);
            final String metadatafield_value = (String) vItemMetaDatann.elementAt(1);
            System.out.println("=metadatafield_name======itemMetaData====" + metadatafield_name);
            System.out.println("=metadatafield_value=====itemMetaData=====" + metadatafield_value);
            System.out.println("=Mdoperator======itemMetaData====" + Mdoperator);
            System.out.println("=Mdname======itemMetaData====" + Mdname);
            System.out.println("=Mvalue======itemMetaData====" + Mvalue);
            if (this.isInteger(Mvalue) && this.isInteger(metadatafield_value)) {
                if ("EQ".equals(Mdoperator) && Mdname.equals(metadatafield_name) && Integer.parseInt(Mvalue) == Integer.parseInt(metadatafield_value)) {
                    System.out.println("==========b=true======itemMetaData=====");
                    b = true;
                    break;
                }
                if ("NEQ".equals(Mdoperator) && Mdname.equals(metadatafield_name) && Integer.parseInt(Mvalue) != Integer.parseInt(metadatafield_value)) {
                    b = true;
                    break;
                }
                if ("LT".equals(Mdoperator) && Mdname.equals(metadatafield_name) && Integer.parseInt(Mvalue) < Integer.parseInt(metadatafield_value)) {
                    b = true;
                    break;
                }
                if ("LTE".equals(Mdoperator) && Mdname.equals(metadatafield_name) && Integer.parseInt(Mvalue) <= Integer.parseInt(metadatafield_value)) {
                    b = true;
                    break;
                }
                if ("GT".equals(Mdoperator) && Mdname.equals(metadatafield_name) && Integer.parseInt(Mvalue) > Integer.parseInt(metadatafield_value)) {
                    b = true;
                    break;
                }
                if ("GTE".equals(Mdoperator) && Mdname.equals(metadatafield_name) && Integer.parseInt(Mvalue) >= Integer.parseInt(metadatafield_value)) {
                    b = true;
                    break;
                }
            }
            else {
                if ("EQ".equals(Mdoperator) && Mdname.equals(metadatafield_name) && Mvalue.equals(metadatafield_value)) {
                    b = true;
                    break;
                }
                if ("NEQ".equals(Mdoperator) && Mdname.equals(metadatafield_name) && !Mvalue.equals(metadatafield_value)) {
                    b = true;
                    break;
                }
            }
        }
        System.out.println("==========b======itemMetaData=====" + b);
        return b;
    }
    
    public boolean isInteger(final String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    public Vector PrintAsmtHTML(final Element doc) {
        final Vector questionAll = new Vector();
        try {
            final NodeList nl = doc.getElementsByTagName("item");
            int strqno = 0;
            for (int r = 0; r < nl.getLength(); ++r) {
                final Node n = nl.item(r);
                final Document doc2 = (Document)new DocumentImpl();
                final Element root = doc2.createElement("idetails");
                doc2.appendChild(root);
                final Node nd1 = doc2.importNode(nl.item(r), true);
                root.appendChild(nd1);
                final OutputFormat format1 = new OutputFormat(doc2);
                final StringWriter stringOut1 = new StringWriter();
                final XMLSerializer serial1 = new XMLSerializer((Writer)stringOut1, format1);
                serial1.asDOMSerializer();
                serial1.serialize(doc2.getDocumentElement());
                final String xml11 = stringOut1.toString();
                final int kk1 = xml11.length();
                final String item_text = xml11.substring(49, kk1 - 11);
                final Vector<Vector<String>> attributes = this.printElementAttributes(n);
                final Vector<String> attrib = attributes.elementAt(0);
                final String item_type = attrib.elementAt(1);
                int iQuesType = 0;
                if (item_type != null) {
                    if (item_type.equals("Multiple Choice")) {
                        iQuesType = 0;
                    }
                    if (item_type.equals("Multiple Response")) {
                        iQuesType = 1;
                    }
                    if (item_type.equals("Fill in Blank")) {
                        iQuesType = 2;
                    }
                    if (item_type.equals("True False")) {
                        iQuesType = 3;
                    }
                    if (item_type.equals("Essay Type Question")) {
                        iQuesType = 5;
                    }
                    if (item_type.equals("Slider Test")) {
                        iQuesType = 6;
                    }
                    if (item_type.equals("Matching Question")) {
                        iQuesType = 7;
                    }
                }
                Vector questionMatter = new Vector();
                switch (iQuesType) {
                    case 0: {
                        ++strqno;
                        questionMatter = this.openMCI(item_text, strqno);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 1: {
                        ++strqno;
                        questionMatter = this.openMRI(item_text, strqno);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 2: {
                        ++strqno;
                        questionMatter = this.openFIB(item_text, strqno);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 3: {
                        ++strqno;
                        questionMatter = this.openTF(item_text, strqno);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 5: {
                        ++strqno;
                        questionMatter = this.openEssay(item_text, strqno);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 6: {
                        ++strqno;
                        questionMatter = this.openSlide(item_text, strqno);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 7: {
                        ++strqno;
                        questionMatter = this.openMQ(item_text, strqno);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                }
            }
        }
        catch (DOMException e) {
            OpenXML.log.fatal("DOMException:");
            OpenXML.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        catch (IOException e2) {
            OpenXML.log.fatal("IOException:");
            OpenXML.log.dbe(DebugLevel.L1_FATAL, (Throwable)e2);
        }
        return questionAll;
    }
    
    public Vector rand(final Vector questionAll) {
        System.out.println("=====test4======questionAll==1===" + questionAll);
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
    
    public Vector rand(final Vector questionAll, final int iNo) {
        System.out.println("=====test4======questionAll==1===" + questionAll);
        System.out.println("=====test4======questionAll==1===" + iNo);
        int remain = questionAll.size();
        final int[] n = new int[remain];
        final int[] result = new int[iNo];
        final Vector v = new Vector();
        for (int i = 0; i < questionAll.size(); ++i) {
            n[i] = i;
        }
        final Random rdm = new Random(System.currentTimeMillis());
        for (int j = 0; j < iNo; ++j) {
            System.out.println("=====rand======bbb===========");
            final int suffix = Math.abs(rdm.nextInt()) % remain;
            result[j] = n[suffix];
            n[suffix] = n[remain - 1];
            --remain;
        }
        for (int j = 0; j < iNo; ++j) {
            v.addElement(questionAll.elementAt(result[j]));
        }
        return v;
    }
    
    public int selectionNo(final Element section) {
        int iNo = -1;
        NodeList nl = section.getElementsByTagName("selection_ordering");
        if (nl.getLength() == 0) {
            return iNo;
        }
        if (nl.getLength() > 0) {
            nl = ((Element)nl.item(0)).getElementsByTagName("selection_number");
            if (nl.getLength() == 0) {
                return iNo;
            }
            iNo = Integer.parseInt(nl.item(0).getFirstChild().getNodeValue());
        }
        return iNo;
    }
    
    public String orderSection(final Element section) {
        String strOrder = null;
        NodeList nl = section.getElementsByTagName("selection_ordering");
        if (nl.getLength() == 0) {
            return null;
        }
        if (nl.getLength() > 0) {
            nl = ((Element)nl.item(0)).getElementsByTagName("order");
            if (nl.getLength() == 0) {
                return null;
            }
            final Vector<Vector<String>> vOrder = this.printElementAttributes(nl.item(0));
            final Vector<String> vOrderAttrib = vOrder.elementAt(0);
            final String strAttribValue = strOrder = vOrderAttrib.elementAt(1);
        }
        return strOrder;
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
    
    public Vector sequence(final Vector questionAll, final int iNo) {
        final Vector v = new Vector();
        for (int i = 0; i < iNo; ++i) {
            v.addElement(questionAll.elementAt(i));
        }
        return v;
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
    
    public String item_response_selection(final Node doc) {
        String shuffle = null;
        final Node flow = ((Element)doc).getElementsByTagName("flow").item(0);
        final Node res_lid = ((Element)flow).getElementsByTagName("response_lid").item(0);
        final Node render_ch = ((Element)res_lid).getElementsByTagName("render_choice").item(0);
        shuffle = ((Element)render_ch).getAttribute("shuffle");
        return shuffle;
    }
    
    public Vector feedBacks(final Document doc) {
        final Vector vFeedback = new Vector();
        final NodeList itemfeedbacks = doc.getElementsByTagName("itemfeedback");
        final NodeList n45 = doc.getElementsByTagName("mattext");
        if (itemfeedbacks.getLength() != 0) {
            for (int k1 = 0; k1 < itemfeedbacks.getLength(); ++k1) {
                final Node nItemfeedback = itemfeedbacks.item(k1);
                final Vector vFeedbackItem = new Vector();
                final Vector<Vector<String>> vAttributes = this.printElementAttributes(nItemfeedback);
                for (int i = 0; i < vAttributes.size(); ++i) {
                    final Vector<String> attrib = vAttributes.elementAt(i);
                    final String strFor = attrib.elementAt(1);
                    for (int i2 = 0; i2 < n45.getLength(); ++i2) {
                        final Node PNode = n45.item(i2).getParentNode().getParentNode().getParentNode();
                        final String Pnodename = PNode.getNodeName();
                        final Element Epnode = (Element)PNode;
                        final String attribute2 = Epnode.getAttribute("ident");
                        if (Pnodename.equals("itemfeedback") && attribute2.equals(strFor)) {
                            final String strAns = n45.item(i2).getFirstChild().getNodeValue();
                            vFeedbackItem.addElement(strAns);
                        }
                    }
                    vFeedbackItem.addElement(strFor);
                }
                vFeedback.addElement(vFeedbackItem);
            }
        }
        else {
            vFeedback.addElement(null);
        }
        return vFeedback;
    }
    
    public Vector correctAnsFIB(Node n) {
        final Vector ans = new Vector();
        final Vector<Vector<String>> attributes = this.printElementAttributes(n);
        for (int j = 0; j < attributes.size(); ++j) {
            final Vector<String> attrib = attributes.elementAt(j);
            final String attrname = attrib.elementAt(0);
            if (attrname.equals("case")) {
                ans.addElement(attrib.elementAt(1));
                break;
            }
        }
        do {
            n = n.getFirstChild();
        } while (n.getNodeName() != "#text");
        ans.insertElementAt(n.getNodeValue(), 0);
        System.out.println("Correct : " + ans);
        return ans;
    }
    
    public String score(final Document d) {
        final NodeList m = d.getElementsByTagName("presentation");
        final Node m2 = m.item(0);
        final NodeList s = d.getElementsByTagName("setvar");
        final String strScore = s.item(0).getFirstChild().getNodeValue();
        return strScore;
    }
    
    public Vector multipleAnsMCI(final Document d) {
        final NodeList nl1 = d.getElementsByTagName("mattext");
        final Vector multiAns1 = new Vector();
        final NodeList nl2 = d.getElementsByTagName("response_label");
        for (int j = 0; j < nl2.getLength(); ++j) {
            final Node n2 = nl2.item(j);
            final Vector label = new Vector();
            final Vector<Vector<String>> vAttributes = this.printElementAttributes(n2);
            for (int i = 0; i < vAttributes.size(); ++i) {
                final Vector<String> attrib = vAttributes.elementAt(i);
                final String strLabel = attrib.elementAt(1);
                final Character cLabel = new Character(strLabel.charAt(0));
                label.addElement(cLabel);
                for (int i2 = 0; i2 < nl1.getLength(); ++i2) {
                    final Node PNode = nl1.item(i2).getParentNode().getParentNode().getParentNode();
                    final String Pnodename = PNode.getNodeName();
                    final Element Epnode = (Element)PNode;
                    final String attribute2 = Epnode.getAttribute("ident");
                    if (Pnodename.equals("response_label") && attribute2.equals(strLabel)) {
                        final String strAns = nl1.item(i2).getFirstChild().getNodeValue();
                        label.addElement(strAns);
                    }
                }
            }
            multiAns1.addElement(label);
        }
        return multiAns1;
    }
    
    public Vector multipleAnsMRI(final Document d) {
        final Vector multiAns1 = new Vector();
        final NodeList nl = d.getElementsByTagName("response_label");
        final NodeList nl2 = d.getElementsByTagName("mattext");
        for (int j = 0; j < nl.getLength(); ++j) {
            final Node n2 = nl.item(j);
            final Vector label = new Vector();
            final Vector<Vector<String>> vAttributes = this.printElementAttributes(n2);
            for (int i = 0; i < vAttributes.size(); ++i) {
                final Vector<String> attrib = vAttributes.elementAt(i);
                final String strLabel = attrib.elementAt(1);
                final Character cLabel = new Character(strLabel.charAt(0));
                label.addElement(cLabel);
                for (int i2 = 0; i2 < nl2.getLength(); ++i2) {
                    final Node PNode = nl2.item(i2).getParentNode().getParentNode().getParentNode();
                    final String Pnodename = PNode.getNodeName();
                    final Element Epnode = (Element)PNode;
                    final String attribute2 = Epnode.getAttribute("ident");
                    if (Pnodename.equals("response_label") && attribute2.equals(strLabel)) {
                        final String strAns = nl2.item(i2).getFirstChild().getNodeValue();
                        label.addElement(strAns);
                    }
                }
            }
            multiAns1.addElement(label);
        }
        return multiAns1;
    }
    
    public Vector multipleAnsTF(final Document d) {
        final NodeList nl1 = d.getElementsByTagName("mattext");
        final Vector multiAns1 = new Vector();
        final NodeList nl2 = d.getElementsByTagName("response_label");
        for (int j = 0; j < nl2.getLength(); ++j) {
            final Node n2 = nl2.item(j);
            final Vector label = new Vector();
            final Vector<Vector<String>> vAttributes = this.printElementAttributes(n2);
            for (int i = 0; i < vAttributes.size(); ++i) {
                final Vector<String> attrib = vAttributes.elementAt(i);
                final String strLabel = attrib.elementAt(1);
                final Character cLabel = new Character(strLabel.charAt(0));
                label.addElement(cLabel);
                for (int i2 = 0; i2 < nl1.getLength(); ++i2) {
                    final Node PNode = nl1.item(i2).getParentNode().getParentNode().getParentNode();
                    final String Pnodename = PNode.getNodeName();
                    final Element Epnode = (Element)PNode;
                    final String attribute2 = Epnode.getAttribute("ident");
                    if (Pnodename.equals("response_label") && attribute2.equals(strLabel)) {
                        final String strAns = nl1.item(i2).getFirstChild().getNodeValue();
                        label.addElement(strAns);
                    }
                }
            }
            multiAns1.addElement(label);
        }
        return multiAns1;
    }
    
    public Vector multipleAnsSlide(final Document d) {
        final NodeList nl = d.getElementsByTagName("response_label");
        final Vector multiAns1 = new Vector();
        for (int j = 0; j < nl.getLength(); ++j) {
            final Node n2 = nl.item(j);
            final Vector label1 = new Vector();
            final Vector<Vector<String>> vAttributes = this.printElementAttributes(n2);
            final NodeList response_label = d.getElementsByTagName("response_label");
            final Vector<String> attrib = vAttributes.elementAt(0);
            final String strLabel = attrib.elementAt(1);
            label1.addElement(strLabel);
            final String Ans = response_label.item(j).getFirstChild().getNodeValue();
            label1.addElement(Ans);
            multiAns1.addElement(label1);
        }
        return multiAns1;
    }
    
    public String correctAnsMCI(final Document d) {
        String strCorrectAns = "";
        final Node nResponse = d.getElementsByTagName("varequal").item(0);
        final Node childResponse = nResponse.getFirstChild();
        strCorrectAns = childResponse.getNodeValue();
        return strCorrectAns;
    }
    
    public Vector correctAnsMRI(final Document d) {
        final Vector vCorrectAns = new Vector();
        final NodeList nResponses = d.getElementsByTagName("varequal");
        for (int k = 0; k < nResponses.getLength() / 2; ++k) {
            final Node nResponse = nResponses.item(k);
            final Node childResponse = nResponse.getFirstChild();
            final String strCorrectAns = childResponse.getNodeValue();
            vCorrectAns.addElement(strCorrectAns);
        }
        return vCorrectAns;
    }
    
    public String correctAnsTF(final Document d) {
        String strCorrectAns = "";
        final Node nResponse = d.getElementsByTagName("varequal").item(0);
        final Node childResponse = nResponse.getFirstChild();
        strCorrectAns = childResponse.getNodeValue();
        return strCorrectAns;
    }
    
    public String correctAnsSlide(final Document d) {
        String strCorrectAns = "";
        final Node nResponse = d.getElementsByTagName("varequal").item(0);
        final Node childResponse = nResponse.getFirstChild();
        strCorrectAns = childResponse.getNodeValue();
        return strCorrectAns;
    }
    
    public Vector openMCI(final String qText, final int strqno) {
        final Vector questionMatter = new Vector();
        try {
            final Vector question = new Vector();
            final Vector response = new Vector();
            Vector rnd_ans = null;
            Vector multiAns1 = new Vector();
            String strCorrectAns = "";
            final InputStream istream1 = new StringBufferInputStream(qText);
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document d = db.parse(istream1);
            NodeList item2 = d.getElementsByTagName("item");
            item2 = (NodeList) item2.item(0);
            final NodeList n11 = d.getElementsByTagName("presentation");
            final Node n2 = n11.item(0);
            final NodeList nl1 = d.getElementsByTagName("mattext");
            final NodeList nResprocessings = d.getElementsByTagName("resprocessing");
            final Node nResprocessing = nResprocessings.item(0);
            final Node child = n2.getFirstChild();
            final String[] strQuestion = new String[2];
            final String no1 = String.valueOf(strqno);
            strQuestion[0] = no1;
            strQuestion[1] = nl1.item(0).getFirstChild().getNodeValue();
            question.addElement(strQuestion[0]);
            question.addElement(strQuestion[1]);
            final String strScore = this.score(d);
            multiAns1 = this.multipleAnsMCI(d);
            if ("Yes".equals(this.item_response_selection(n2))) {
                rnd_ans = this.rand(multiAns1);
                question.addElement(rnd_ans);
            }
            else {
                question.addElement(multiAns1);
            }
            strCorrectAns = this.correctAnsMCI(d);
            question.addElement(strCorrectAns);
            final NodeList n3 = d.getElementsByTagName("item");
            final Node n4 = n3.item(0);
            final Vector<Vector<String>> attributesMCI = this.printElementAttributes(n4);
            final Vector<String> attribMCI = attributesMCI.elementAt(0);
            final String attrnameMCI = attribMCI.elementAt(0);
            if (this.ifobjectBank) {
                question.addElement(no1);
            }
            else if (attrnameMCI.equals("ident")) {
                final String attrvalMCI = attribMCI.elementAt(1);
                question.addElement(attrvalMCI);
            }
            response.addElement(new Integer(0));
            response.addElement(strScore);
            final Vector vFeedback = this.feedBacks(d);
            response.addElement(vFeedback);
            final Element nResprocessinge = (Element)nResprocessing;
            final NodeList respconditionlist = nResprocessinge.getElementsByTagName("respcondition");
            if (respconditionlist.getLength() > 1) {
                final Node respcondition = respconditionlist.item(1);
                final Element respconditione = (Element)respcondition;
                final NodeList setvarlist = respconditione.getElementsByTagName("setvar");
                final Node setvarn = setvarlist.item(0);
                final String strNmarks = setvarn.getFirstChild().getNodeValue();
                response.addElement(strNmarks);
            }
            questionMatter.addElement(response);
            questionMatter.addElement(question);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return questionMatter;
    }
    
    public Vector openMRI(final String qText, final int strqno) {
        final Vector questionMatter = new Vector();
        try {
            final Vector question = new Vector();
            final Vector response = new Vector();
            Vector rnd_ans = null;
            Vector multiAns1 = new Vector();
            final String strCorrectAns = "";
            Vector vCorrectAns = new Vector();
            final InputStream istream1 = new StringBufferInputStream(qText);
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document d = db.parse(istream1);
            NodeList item2 = d.getElementsByTagName("item");
            item2 = (NodeList) item2.item(0);
            final NodeList n11 = d.getElementsByTagName("presentation");
            final Node n2 = n11.item(0);
            final NodeList nl1 = d.getElementsByTagName("mattext");
            final NodeList nResprocessings = d.getElementsByTagName("resprocessing");
            final Node nResprocessing = nResprocessings.item(0);
            final Node child = n2.getFirstChild();
            final String[] strQuestion = new String[2];
            final String no1 = String.valueOf(strqno);
            strQuestion[0] = no1;
            strQuestion[1] = nl1.item(0).getFirstChild().getNodeValue();
            question.addElement(strQuestion[0]);
            question.addElement(strQuestion[1]);
            final String strScore = this.score(d);
            multiAns1 = this.multipleAnsMRI(d);
            if ("Yes".equals(this.item_response_selection(n2))) {
                rnd_ans = this.rand(multiAns1);
                question.addElement(rnd_ans);
            }
            else {
                question.addElement(multiAns1);
            }
            vCorrectAns = this.correctAnsMRI(d);
            question.addElement(vCorrectAns);
            final NodeList n3 = d.getElementsByTagName("item");
            final Node n4 = n3.item(0);
            final Vector<Vector<String>> attributesMR = this.printElementAttributes(n4);
            final Vector<String> attribMR = attributesMR.elementAt(0);
            final String attrnameMR = attribMR.elementAt(0);
            if (this.ifobjectBank) {
                question.addElement(no1);
            }
            else if (attrnameMR.equals("ident")) {
                final String attrvalMR = attribMR.elementAt(1);
                question.addElement(attrvalMR);
            }
            response.addElement(new Integer(1));
            response.addElement(strScore);
            final Vector vFeedback = this.feedBacks(d);
            response.addElement(vFeedback);
            final Element nResprocessinge = (Element)nResprocessing;
            final NodeList respconditionlist = nResprocessinge.getElementsByTagName("respcondition");
            if (respconditionlist.getLength() > 1) {
                final Node respcondition = respconditionlist.item(1);
                final Element respconditione = (Element)respcondition;
                final NodeList setvarlist = respconditione.getElementsByTagName("setvar");
                if (setvarlist.getLength() > 0) {
                    final Node setvarn = setvarlist.item(0);
                    final String strNmarks = setvarn.getFirstChild().getNodeValue();
                    response.addElement(strNmarks);
                }
            }
            questionMatter.addElement(response);
            questionMatter.addElement(question);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return questionMatter;
    }
    
    public Vector openFIB(final String qText, final int strqno) {
        final Vector questionMatter = new Vector();
        try {
            final Vector question = new Vector();
            final Vector response = new Vector();
            final Vector rnd_ans = null;
            final String strCorrectAns = "";
            final InputStream istream1 = new StringBufferInputStream(qText);
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document d = db.parse(istream1);
            NodeList item2 = d.getElementsByTagName("item");
            item2 = (NodeList) item2.item(0);
            final NodeList n11 = d.getElementsByTagName("presentation");
            final Node n2 = n11.item(0);
            final NodeList nl1 = d.getElementsByTagName("mattext");
            final NodeList render_fibs = d.getElementsByTagName("render_fib");
            final Node render_fib = render_fibs.item(0);
            String strType = "";
            String maxchars = "";
            final Vector<Vector<String>> attributes = this.printElementAttributes(render_fib);
            final String strScore = this.score(d);
            for (int j = 0; j < attributes.size(); ++j) {
                final Vector<String> attrib = attributes.elementAt(j);
                final String attrname = attrib.elementAt(0);
                if (attrname.equals("prompt")) {
                    strType = attrib.elementAt(1);
                }
                if (attrname.equals("maxchars")) {
                    maxchars = attrib.elementAt(1);
                }
            }
            final NodeList nResprocessings = d.getElementsByTagName("resprocessing");
            final Node nResprocessing = nResprocessings.item(0);
            final Node child = n2.getFirstChild();
            final String[] strQuestion = new String[2];
            final String no1 = String.valueOf(strqno);
            strQuestion[0] = no1;
            strQuestion[1] = nl1.item(0).getFirstChild().getNodeValue();
            question.addElement(strQuestion[0]);
            question.addElement(strQuestion[1]);
            final NodeList varequals = d.getElementsByTagName("varequal");
            final Node varequal = varequals.item(0);
            final Vector ans = this.correctAnsFIB(varequal);
            ans.insertElementAt(strType, 1);
            ans.addElement(maxchars);
            question.addElement(ans);
            final NodeList n3 = d.getElementsByTagName("item");
            final Node n4 = n3.item(0);
            final Vector<Vector<String>> attributesFIB = this.printElementAttributes(n4);
            final Vector<String> attribFIB = attributesFIB.elementAt(0);
            final String attrnameFIB = attribFIB.elementAt(0);
            if (this.ifobjectBank) {
                question.addElement(no1);
            }
            else if (attrnameFIB.equals("ident")) {
                final String attrvalFIB = attribFIB.elementAt(1);
                question.addElement(attrvalFIB);
            }
            response.addElement(new Integer(2));
            response.addElement(strScore);
            final Vector vFeedback = this.feedBacks(d);
            response.addElement(vFeedback);
            final Element nResprocessinge = (Element)nResprocessing;
            final NodeList respconditionlist = nResprocessinge.getElementsByTagName("respcondition");
            if (respconditionlist.getLength() > 1) {
                final Node respcondition = respconditionlist.item(1);
                final Element respconditione = (Element)respcondition;
                final NodeList setvarlist = respconditione.getElementsByTagName("setvar");
                final Node setvarn = setvarlist.item(0);
                final String strNmarks = setvarn.getFirstChild().getNodeValue();
                response.addElement(strNmarks);
            }
            questionMatter.addElement(response);
            questionMatter.addElement(question);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return questionMatter;
    }
    
    public Vector openTF(final String qText, final int strqno) {
        final Vector questionMatter = new Vector();
        try {
            final Vector question = new Vector();
            final Vector response = new Vector();
            final Vector rnd_ans = null;
            Vector multiAns1 = new Vector();
            String strCorrectAns = "";
            final InputStream istream1 = new StringBufferInputStream(qText);
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document d = db.parse(istream1);
            NodeList item2 = d.getElementsByTagName("item");
            item2 = (NodeList) item2.item(0);
            final NodeList n11 = d.getElementsByTagName("presentation");
            final Node n2 = n11.item(0);
            final NodeList nl1 = d.getElementsByTagName("mattext");
            final NodeList nResprocessings = d.getElementsByTagName("resprocessing");
            final Node nResprocessing = nResprocessings.item(0);
            final Node child = n2.getFirstChild();
            final String[] strQuestion = new String[2];
            final String no1 = String.valueOf(strqno);
            strQuestion[0] = no1;
            strQuestion[1] = nl1.item(0).getFirstChild().getNodeValue();
            question.addElement(strQuestion[0]);
            question.addElement(strQuestion[1]);
            final String strScore = this.score(d);
            multiAns1 = this.multipleAnsTF(d);
            question.addElement(multiAns1);
            strCorrectAns = this.correctAnsTF(d);
            question.addElement(strCorrectAns);
            final NodeList n3 = d.getElementsByTagName("item");
            final Node n4 = n3.item(0);
            final Vector<Vector<String>> attributesTF = this.printElementAttributes(n4);
            final Vector<String> attribTF = attributesTF.elementAt(0);
            final String attrnameTF = attribTF.elementAt(0);
            if (this.ifobjectBank) {
                question.addElement(no1);
            }
            else if (attrnameTF.equals("ident")) {
                final String attrvalTF = attribTF.elementAt(1);
                question.addElement(attrvalTF);
            }
            response.addElement(new Integer(3));
            response.addElement(strScore);
            final Vector vFeedback = this.feedBacks(d);
            response.addElement(vFeedback);
            final Element nResprocessinge = (Element)nResprocessing;
            final NodeList respconditionlist = nResprocessinge.getElementsByTagName("respcondition");
            if (respconditionlist.getLength() > 1) {
                final Node respcondition = respconditionlist.item(1);
                final Element respconditione = (Element)respcondition;
                final NodeList setvarlist = respconditione.getElementsByTagName("setvar");
                final Node setvarn = setvarlist.item(0);
                final String strNmarks = setvarn.getFirstChild().getNodeValue();
                response.addElement(strNmarks);
            }
            questionMatter.addElement(response);
            questionMatter.addElement(question);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return questionMatter;
    }
    
    public Vector openSlide(final String qText, final int strqno) {
        final Vector questionMatter = new Vector();
        try {
            final Vector question = new Vector();
            final Vector response = new Vector();
            final Vector rnd_ans = null;
            Vector multiAns1 = new Vector();
            String strCorrectAns = "";
            final InputStream istream1 = new StringBufferInputStream(qText);
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document d = db.parse(istream1);
            NodeList item2 = d.getElementsByTagName("item");
            item2 = (NodeList) item2.item(0);
            final NodeList n11 = d.getElementsByTagName("presentation");
            final Node n2 = n11.item(0);
            final NodeList nl1 = d.getElementsByTagName("mattext");
            final NodeList nResprocessings = d.getElementsByTagName("resprocessing");
            final Node nResprocessing = nResprocessings.item(0);
            final Node child = n2.getFirstChild();
            final String[] strQuestion = new String[2];
            final String no1 = String.valueOf(strqno);
            strQuestion[0] = no1;
            strQuestion[1] = nl1.item(0).getFirstChild().getNodeValue();
            question.addElement(strQuestion[0]);
            question.addElement(strQuestion[1]);
            final String strScore = this.score(d);
            multiAns1 = this.multipleAnsSlide(d);
            question.addElement(multiAns1);
            strCorrectAns = this.correctAnsSlide(d);
            question.addElement(strCorrectAns);
            final NodeList n3 = d.getElementsByTagName("item");
            final Node n4 = n3.item(0);
            final Vector<Vector<String>> attributesSlide = this.printElementAttributes(n4);
            final Vector<String> attribSlide = attributesSlide.elementAt(0);
            final String attrnameSlide = attribSlide.elementAt(0);
            if (this.ifobjectBank) {
                question.addElement(no1);
            }
            else if (attrnameSlide.equals("ident")) {
                final String attrvalSLide = attribSlide.elementAt(1);
                question.addElement(attrvalSLide);
            }
            response.addElement(new Integer(6));
            response.addElement(strScore);
            final Vector vFeedback = this.feedBacks(d);
            response.addElement(vFeedback);
            final Element nResprocessinge = (Element)nResprocessing;
            final NodeList respconditionlist = nResprocessinge.getElementsByTagName("respcondition");
            if (respconditionlist.getLength() > 1) {
                final Node respcondition = respconditionlist.item(1);
                final Element respconditione = (Element)respcondition;
                final NodeList setvarlist = respconditione.getElementsByTagName("setvar");
                final Node setvarn = setvarlist.item(0);
                final String strNmarks = setvarn.getFirstChild().getNodeValue();
                response.addElement(strNmarks);
            }
            questionMatter.addElement(response);
            questionMatter.addElement(question);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return questionMatter;
    }
    
    public Vector openEssay(final String qText, final int strqno) {
        final Vector questionMatter = new Vector();
        try {
            final Vector question = new Vector();
            final Vector response = new Vector();
            final Vector rnd_ans = null;
            final String strCorrectAns = "";
            final InputStream istream1 = new StringBufferInputStream(qText);
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document d = db.parse(istream1);
            NodeList item2 = d.getElementsByTagName("item");
            item2 = (NodeList) item2.item(0);
            final NodeList n11 = d.getElementsByTagName("presentation");
            final Node n2 = n11.item(0);
            final NodeList nl1 = d.getElementsByTagName("mattext");
            final NodeList nResprocessings = d.getElementsByTagName("resprocessing");
            final Node nResprocessing = nResprocessings.item(0);
            final Node child = n2.getFirstChild();
            final String[] strQuestion = new String[2];
            final String no1 = String.valueOf(strqno);
            strQuestion[0] = no1;
            strQuestion[1] = nl1.item(0).getFirstChild().getNodeValue();
            question.addElement(strQuestion[0]);
            question.addElement(strQuestion[1]);
            final String strScore = this.score(d);
            final NodeList n3 = d.getElementsByTagName("item");
            final Node n4 = n3.item(0);
            final Vector<Vector<String>> attributesEssay = this.printElementAttributes(n4);
            final Vector<String> attribEssay = attributesEssay.elementAt(0);
            final String attrnameEssay = attribEssay.elementAt(0);
            if (attrnameEssay.equals("ident")) {
                final String attrvalEssay = attribEssay.elementAt(1);
                question.addElement(attrvalEssay);
            }
            response.addElement(new Integer(5));
            response.addElement(strScore);
            final Vector vFeedback = this.feedBacks(d);
            response.addElement(vFeedback);
            questionMatter.addElement(response);
            questionMatter.addElement(question);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return questionMatter;
    }
    
    public Vector openMQ(final String qText, final int strqno) {
        final Vector questionMatter = new Vector();
        try {
            final Vector question = new Vector();
            final Vector response = new Vector();
            final Vector vOption = new Vector();
            Vector leftoption = new Vector();
            Vector rightoption = new Vector();
            Vector vCorrectAns = new Vector();
            Vector vFeedback = new Vector();
            final InputStream istream1 = new StringBufferInputStream(qText);
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document d = db.parse(istream1);
            NodeList item2 = d.getElementsByTagName("item");
            item2 = (NodeList) item2.item(0);
            final NodeList n11 = d.getElementsByTagName("presentation");
            final Node n2 = n11.item(0);
            final NodeList nl1 = d.getElementsByTagName("mattext");
            final NodeList nResprocessings = d.getElementsByTagName("resprocessing");
            final Node nResprocessing = nResprocessings.item(0);
            final Node child = n2.getFirstChild();
            final String[] strQuestion = new String[2];
            final String no1 = String.valueOf(strqno);
            strQuestion[0] = no1;
            strQuestion[1] = nl1.item(0).getFirstChild().getNodeValue();
            question.addElement(strQuestion[0]);
            question.addElement(strQuestion[1]);
            leftoption = this.getleftoption(d);
            rightoption = this.getrightoption(d);
            vOption.addElement(leftoption);
            vOption.addElement(rightoption);
            question.addElement(vOption);
            vCorrectAns = this.getcorrectmatchMQ(d);
            question.addElement(vCorrectAns);
            final NodeList n3 = d.getElementsByTagName("item");
            final Node n4 = n3.item(0);
            final Vector<Vector<String>> attributesMQ = this.printElementAttributes(n4);
            final Vector<String> attribMQ = attributesMQ.elementAt(0);
            final String attrnameMQ = attribMQ.elementAt(0);
            if (this.ifobjectBank) {
                question.addElement(no1);
            }
            else if (attrnameMQ.equals("ident")) {
                final String attrvalMQ = attribMQ.elementAt(1);
                question.addElement(attrvalMQ);
            }
            final String[] strScore = this.scoreMQ(d);
            response.addElement(7);
            response.addElement(strScore[0]);
            vFeedback = this.feedBacks(d);
            response.addElement(vFeedback);
            response.addElement(strScore[1]);
            questionMatter.addElement(response);
            questionMatter.addElement(question);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception==" + ex);
        }
        return questionMatter;
    }
    
    public Vector getleftoption(final Document d) {
        final Vector leftoption = new Vector();
        final NodeList n1 = d.getElementsByTagName("mattext");
        for (int j = 0; j < n1.getLength(); ++j) {
            final String[] l1 = new String[2];
            final Node n2 = n1.item(j);
            final Node PNode = n1.item(j).getParentNode().getParentNode();
            final String Pnodename = PNode.getNodeName();
            final Element Epnode = (Element)PNode;
            final String attribute2 = Epnode.getAttribute("ident");
            String op = "";
            if (Pnodename.equals("response_lid")) {
                op = n1.item(j).getFirstChild().getNodeValue();
                l1[0] = attribute2;
                l1[1] = op;
                leftoption.addElement(l1);
            }
        }
        return leftoption;
    }
    
    public Vector getrightoption(final Document d) {
        final Vector<String[]> rightoption = new Vector<String[]>();
        final NodeList n1 = d.getElementsByTagName("mattext");
        for (int j = 0; j < n1.getLength(); ++j) {
            final String[] l1 = new String[2];
            final Node n2 = n1.item(j);
            final Node PNode = n1.item(j).getParentNode().getParentNode();
            final String Pnodename = PNode.getNodeName();
            final Element Epnode = (Element)PNode;
            final String attribute2 = Epnode.getAttribute("ident");
            String op = "";
            if (Pnodename.equals("response_label")) {
                op = n1.item(j).getFirstChild().getNodeValue();
                l1[0] = attribute2;
                l1[1] = op;
                int exist = 0;
                for (int jj = 0; jj < rightoption.size(); ++jj) {
                    final String[] leftOP = rightoption.elementAt(jj);
                    if (leftOP[0].equals(l1[0])) {
                        exist = 1;
                    }
                }
                if (exist != 1) {
                    rightoption.addElement(l1);
                }
            }
        }
        return rightoption;
    }
    
    public Vector getcorrectmatchMQ(final Document d) {
        final Vector<String[]> cAns = new Vector<String[]>();
        final NodeList nlist1 = d.getElementsByTagName("varequal");
        final NodeList nlist2 = d.getElementsByTagName("setvar");
        final NodeList nlist3 = d.getElementsByTagName("mattext");
        for (int j = 0; j < nlist1.getLength(); ++j) {
            final String[] l1 = new String[2];
            Node n2 = nlist1.item(j);
            n2 = nlist2.item(j);
            final Element Epnode = (Element)n2;
            String attribute2 = Epnode.getAttribute("varname");
            if (attribute2.equals("Respondus_Correct")) {
                final Element Epnode2 = (Element)n2;
                attribute2 = Epnode2.getAttribute("respident");
                final String op = nlist1.item(j).getFirstChild().getNodeValue();
                int exist = 0;
                for (int ja = 0; ja < nlist3.getLength(); ++ja) {
                    final Node n3 = nlist3.item(ja);
                    final Node PNode = nlist3.item(ja).getParentNode().getParentNode();
                    final String Pnodename = PNode.getNodeName();
                    final Element Epnode3 = (Element)PNode;
                    final String attribute3 = Epnode3.getAttribute("ident");
                    if (Pnodename.equals("response_lid") && attribute2.equals(attribute3)) {
                        l1[0] = nlist3.item(ja).getFirstChild().getNodeValue();
                        l1[1] = op;
                    }
                }
                for (int jj = 0; jj < cAns.size(); ++jj) {
                    final String[] leftOP = cAns.elementAt(jj);
                    if (leftOP[0].equals(l1[0])) {
                        exist = 1;
                    }
                }
                if (exist != 1) {
                    cAns.addElement(l1);
                }
            }
        }
        return cAns;
    }
    
    public String[] scoreMQ(final Document d) {
        final String[] strScore = new String[2];
        String strCScore = "";
        String strIncScore = "";
        final NodeList s = d.getElementsByTagName("decvar");
        final Element ss = (Element)s.item(0);
        final Element sss = (Element)s.item(1);
        final String varname = ss.getAttribute("varname");
        if (varname.equals("Respondus_Correct")) {
            strCScore = ss.getAttribute("maxvalue");
        }
        final String varname2 = sss.getAttribute("varname");
        if (varname2.equals("Respondus_Incorrect")) {
            strIncScore = sss.getAttribute("maxvalue");
        }
        strScore[0] = strCScore;
        strScore[1] = strIncScore;
        return strScore;
    }
    
    static {
        log = new SimpleLogger((Class)OpenXML.class, true);
    }
}
