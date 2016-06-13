package learnityasmtserver.assessmentportal.standaloneasmt;

import org.grlea.log.*;
import org.xml.sax.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import java.util.*;

public class Retrive_Item
{
    public static final SimpleLogger log;
    Node nScore;
    String strHTML;
    
    public Retrive_Item() {
        this.nScore = null;
        this.strHTML = "";
    }
    
    public Vector RetriveItem(final Vector vId, final Vector vtype, final Vector vtext) {
        final Vector v = null;
        final Vector QV = new Vector();
        Vector questionAll = new Vector();
        if (vtype != null) {
            questionAll = this.printHtml(vId, vtype, vtext);
        }
        return questionAll;
    }
    
    public Vector printHtml(final Vector<String> vId, final Vector<String> vtype, final Vector<InputStream> vtext) {
        final Vector questionAll = new Vector();
        if (vtype != null) {
            for (int i = 0; i < vtext.size(); ++i) {
                int iQuesType = 0;
                final String Qid = vId.elementAt(i);
                final int strqno = Integer.parseInt(Qid);
                final InputStream qText = vtext.elementAt(i);
                final String qType = vtype.elementAt(i);
                if (qType != null) {
                    if (qType.equals("Multiple Choice")) {
                        iQuesType = 0;
                    }
                    if (qType.equals("Multiple Response")) {
                        iQuesType = 1;
                    }
                    if (qType.equals("Fill in Blank")) {
                        iQuesType = 2;
                    }
                    if (qType.equals("True False")) {
                        iQuesType = 3;
                    }
                    if (qType.equals("Matching Question")) {
                        iQuesType = 4;
                    }
                }
                Vector questionMatter = new Vector();
                switch (iQuesType) {
                    case 0: {
                        questionMatter = this.openMCI(qText, strqno);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 1: {
                        questionMatter = this.openMRI(qText, strqno);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 2: {
                        questionMatter = this.openFIB(qText, strqno);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 3: {
                        questionMatter = this.openTF(qText, strqno);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                    case 4: {
                        questionMatter = this.openMQ(qText, strqno);
                        questionAll.addElement(questionMatter);
                        break;
                    }
                }
            }
        }
        return questionAll;
    }
    
    public Vector openMCI(final InputStream qText, final int strqno) {
        final Vector questionMatter = new Vector();
        try {
            final Vector question = new Vector();
            final Vector response = new Vector();
            Vector rnd_ans = null;
            Vector multiAns1 = new Vector();
            String strCorrectAns = "";
            final InputStreamReader input_reader = new InputStreamReader(qText, "UTF-8");
            final InputSource input_source = new InputSource(input_reader);
            input_source.setEncoding("UTF-8");
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document d = db.parse(input_source);
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
            question.addElement(no1);
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
            Retrive_Item.log.debug("Exception==" + ex);
        }
        return questionMatter;
    }
    
    public Vector openMRI(final InputStream qText, final int strqno) {
        final Vector questionMatter = new Vector();
        try {
            final Vector question = new Vector();
            final Vector response = new Vector();
            Vector rnd_ans = null;
            Vector multiAns1 = new Vector();
            final String strCorrectAns = "";
            Vector vCorrectAns = new Vector();
            final InputStreamReader input_reader = new InputStreamReader(qText, "UTF-8");
            final InputSource input_source = new InputSource(input_reader);
            input_source.setEncoding("UTF-8");
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document d = db.parse(input_source);
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
            question.addElement(no1);
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
    
    public Vector openFIB(final InputStream qText, final int strqno) {
        final Vector questionMatter = new Vector();
        try {
            final Vector question = new Vector();
            final Vector response = new Vector();
            final Vector rnd_ans = null;
            final String strCorrectAns = "";
            final InputStreamReader input_reader = new InputStreamReader(qText, "UTF-8");
            final InputSource input_source = new InputSource(input_reader);
            input_source.setEncoding("UTF-8");
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document d = db.parse(input_source);
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
            question.addElement(no1);
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
    
    public Vector openTF(final InputStream qText, final int strqno) {
        final Vector questionMatter = new Vector();
        try {
            final Vector question = new Vector();
            final Vector response = new Vector();
            final Vector rnd_ans = null;
            Vector multiAns1 = new Vector();
            String strCorrectAns = "";
            final InputStreamReader input_reader = new InputStreamReader(qText, "UTF-8");
            final InputSource input_source = new InputSource(input_reader);
            input_source.setEncoding("UTF-8");
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document d = db.parse(input_source);
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
            question.addElement(no1);
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
    
    public Vector openMQ(final InputStream qText, final int strqno) {
        final Vector questionMatter = new Vector();
        try {
            final Vector question = new Vector();
            final Vector response = new Vector();
            final Vector vOption = new Vector();
            Vector leftoption = new Vector();
            Vector rightoption = new Vector();
            Vector vCorrectAns = new Vector();
            Vector vFeedback = new Vector();
            final InputStreamReader input_reader = new InputStreamReader(qText, "UTF-8");
            final InputSource input_source = new InputSource(input_reader);
            input_source.setEncoding("UTF-8");
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document d = db.parse(input_source);
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
            Retrive_Item.log.debug("==before call getcorrectmatchMQ==" + vCorrectAns);
            vCorrectAns = this.getcorrectmatchMQ(d);
            Retrive_Item.log.debug("==after call getcorrectmatchMQ==" + vCorrectAns);
            question.addElement(vCorrectAns);
            final NodeList n3 = d.getElementsByTagName("item");
            final Node n4 = n3.item(0);
            final Vector<Vector<String>> attributesMCI = this.printElementAttributes(n4);
            final Vector<String> attribMCI = attributesMCI.elementAt(0);
            final String attrnameMCI = attribMCI.elementAt(0);
            question.addElement(no1);
            final String[] strScore = this.scoreMQ(d);
            response.addElement(4);
            response.addElement(strScore[0]);
            vFeedback = this.feedBacks(d);
            response.addElement(vFeedback);
            response.addElement(strScore[1]);
            Retrive_Item.log.debug("vFeedback==mq==" + vFeedback);
            questionMatter.addElement(response);
            questionMatter.addElement(question);
            Retrive_Item.log.debug("response==mq==" + response);
            Retrive_Item.log.debug("question===mq=" + question);
            Retrive_Item.log.debug("questionMatter=mq===" + questionMatter);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Retrive_Item.log.debug("Exception==" + ex);
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
                Retrive_Item.log.debug(" left op l1[0]==" + l1[0] + "  l1[1]==" + l1[1]);
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
                    Retrive_Item.log.debug("right op l1[0]==" + l1[0] + "  l1[1]==" + l1[1]);
                }
            }
        }
        return rightoption;
    }
    
    public Vector getcorrectmatchMQ(final Document d) {
        final Vector<String[]> cAns = new Vector();
        final NodeList nlist1 = d.getElementsByTagName("varequal");
        final NodeList nlist2 = d.getElementsByTagName("setvar");
        final NodeList nlist3 = d.getElementsByTagName("mattext");
        for (int j = 0; j < nlist1.getLength(); ++j) {
            final String[] l1 = new String[2];
            Node n2 = nlist1.item(j);
            Retrive_Item.log.debug("=========1====" + n2.getNodeName());
            Retrive_Item.log.debug("=========2====");
            n2 = nlist2.item(j);
            final Element Epnode = (Element)n2;
            String attribute2 = Epnode.getAttribute("varname");
            Retrive_Item.log.debug("=========3====" + attribute2);
            if (attribute2.equals("Respondus_Correct")) {
                final Element Epnode2 = (Element)n2;
                attribute2 = Epnode2.getAttribute("respident");
                Retrive_Item.log.debug("=========4====" + attribute2);
                final String op = nlist1.item(j).getFirstChild().getNodeValue();
                Retrive_Item.log.debug("=========5====" + op);
                int exist = 0;
                for (int ja = 0; ja < nlist3.getLength(); ++ja) {
                    final Node n3 = nlist3.item(ja);
                    final Node PNode = nlist3.item(ja).getParentNode().getParentNode();
                    final String Pnodename = PNode.getNodeName();
                    Retrive_Item.log.debug("=========6===" + Pnodename);
                    final Element Epnode3 = (Element)PNode;
                    final String attribute3 = Epnode3.getAttribute("ident");
                    Retrive_Item.log.debug("=========7===" + attribute3);
                    if (Pnodename.equals("response_lid") && attribute2.equals(attribute3)) {
                        Retrive_Item.log.debug("=========8==");
                        l1[0] = nlist3.item(ja).getFirstChild().getNodeValue();
                        l1[1] = op;
                    }
                }
                for (int jj = 0; jj < cAns.size(); ++jj) {
                    final String[] leftOP = cAns.elementAt(jj);
                    Retrive_Item.log.debug("=========9==" + leftOP[0]);
                    Retrive_Item.log.debug("=========10=" + l1[0]);
                    if (leftOP[0].equals(l1[0])) {
                        exist = 1;
                    }
                }
                if (exist != 1) {
                    cAns.addElement(l1);
                    Retrive_Item.log.debug("co ans l1[0]==" + l1[0] + "  l1[1]==" + l1[1]);
                }
            }
        }
        Retrive_Item.log.debug("=cAns==mq==" + cAns);
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
        Retrive_Item.log.debug("strScore[0]==" + strScore[0] + "  strScore[1]==" + strScore[1]);
        return strScore;
    }
    
    public String score(final Document d) {
        final NodeList m = d.getElementsByTagName("presentation");
        final Node m2 = m.item(0);
        final NodeList s = d.getElementsByTagName("setvar");
        final String strScore = s.item(0).getFirstChild().getNodeValue();
        return strScore;
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
    
    public Vector<String> correctAnsFIB(Node n) {
        final Vector<String> ans = new Vector();
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
    
    public String correctAnsMCI(final Document d) {
        String strCorrectAns = "";
        final Node nResponse = d.getElementsByTagName("varequal").item(0);
        final Node childResponse = nResponse.getFirstChild();
        strCorrectAns = childResponse.getNodeValue();
        return strCorrectAns;
    }
    
    public Vector feedBacks(final Document doc) {
        final Vector<Vector<String>> vFeedback = new Vector<Vector<String>>();
        final NodeList itemfeedbacks = doc.getElementsByTagName("itemfeedback");
        final NodeList n45 = doc.getElementsByTagName("mattext");
        if (itemfeedbacks.getLength() != 0) {
            for (int k1 = 0; k1 < itemfeedbacks.getLength(); ++k1) {
                final Node nItemfeedback = itemfeedbacks.item(k1);
                final Vector<String> vFeedbackItem = new Vector<String>();
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
    
    public String item_response_selection(final Node doc) {
        String shuffle = null;
        final Node flow = ((Element)doc).getElementsByTagName("flow").item(0);
        final Node res_lid = ((Element)flow).getElementsByTagName("response_lid").item(0);
        final Node render_ch = ((Element)res_lid).getElementsByTagName("render_choice").item(0);
        shuffle = ((Element)render_ch).getAttribute("shuffle");
        return shuffle;
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
    
    public Vector rand(final Vector questionAll, final int iNo) {
        int remain = questionAll.size();
        final int[] n = new int[remain];
        final int[] result = new int[iNo];
        final Vector v = new Vector();
        for (int i = 0; i < questionAll.size(); ++i) {
            n[i] = i;
        }
        final Random rdm = new Random(System.currentTimeMillis());
        for (int j = 0; j < iNo; ++j) {
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
    
    public boolean isInteger(final String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
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
    
    static {
        log = new SimpleLogger((Class)Retrive_Item.class, true);
    }
}
